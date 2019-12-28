package tech.hry.logclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.AccessLevel;
import lombok.Getter;
import tech.hry.logclient.grpc.LogServiceGrpc;
import tech.hry.logclient.grpc.SaveLogRequest;
import tech.hry.logclient.grpc.SaveLogResponse;

import java.util.concurrent.TimeUnit;

public class LogServiceClient {
    private static volatile LogServiceClient instance;
    private static LogServiceClientConfCallback callback;
    private final LogServiceGrpc.LogServiceStub stub;
    @Getter(AccessLevel.PACKAGE)
    private final LogServiceClientConf conf;
    private final ManagedChannel mc;


    /**
     * 设置连接参数
     *
     * @param callback 参数
     */
    public static void setCallback(LogServiceClientConfCallback callback) {
        LogServiceClient.callback = callback;
    }

    /**
     * 获取连接单例
     *
     * @return 连接单例
     */
    public static LogServiceClient getInstance() throws Exception {
        if (instance != null) {
            return instance;
        }
        synchronized (LogServiceClient.class) {
            if (instance != null) {
                return instance;
            }
            if (callback == null) {
                throw new NullPointerException("Client config call back is null");
            }
            instance = new LogServiceClient(callback.config());
            return instance;
        }
    }

    private LogServiceClient(LogServiceClientConf conf) {
        this.conf = conf;
        mc = ManagedChannelBuilder.forAddress(conf.getGrpcHost(), conf.getGrpcIp())
                .usePlaintext()
                .build();
        if (conf.getGrpcTimeoutMs() > 0) {
            stub = LogServiceGrpc.newStub(mc).withDeadlineAfter(conf.getGrpcTimeoutMs(), TimeUnit.SECONDS);
        } else {
            stub = LogServiceGrpc.newStub(mc);
        }
        new EsLogMessageConsumer().start();
        new FailLogMessageConsumer().start();
        new OverFlowLogMessageConsumer().start();
    }

    private static class responseObserver implements StreamObserver<SaveLogResponse> {
        private final SaveLogRequest log;

        public responseObserver(SaveLogRequest log) {
            this.log = log;
        }

        @Override
        public void onNext(SaveLogResponse value) {
            //ignore
        }

        @Override
        public void onError(Throwable t) {
            LogMessageQueue.offerFail(log);
        }

        @Override
        public void onCompleted() {
            //ignore
        }
    }

    /**
     * 保存日志
     *
     * @param request 日志内容
     */
    public void save(SaveLogRequest request) {
        boolean accept = true;
        if (conf.getFilters() != null) {
            for (LogFilter filter : conf.getFilters()) {
                accept &= filter.accept(request);
            }
        }
        if (accept) {
            SaveLogRequest finalRequest = request;
            if (conf.getAspects() != null){
                for (LogAspect aspect : conf.getAspects()){
                    finalRequest = aspect.handle(finalRequest);
                }
            }
            stub.withDeadlineAfter(conf.getGrpcTimeoutMs(), TimeUnit.MILLISECONDS).store(finalRequest, new responseObserver(request));
        }
    }

    /**
     * 关闭连接
     */
    public static void shutdown() {
        if (instance != null) {
            instance.mc.shutdownNow();
        }
    }
}
