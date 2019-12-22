package tech.hry.logclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import tech.hry.logclient.grpc.LogServiceGrpc;
import tech.hry.logclient.grpc.SaveLogRequest;
import tech.hry.logclient.grpc.SaveLogResponse;

import java.util.concurrent.TimeUnit;

public class LogServiceClient {
    private static volatile LogServiceClient instance;
    private static LogServiceClientConfCallback callback;
    private final LogServiceGrpc.LogServiceStub stub;
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
    public static LogServiceClient getInstance() {
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
    }

    private static class responseObserver implements StreamObserver<SaveLogResponse> {
        private final LogServiceClientConf conf;

        private responseObserver(LogServiceClientConf conf) {
            this.conf = conf;
        }

        @Override
        public void onNext(SaveLogResponse value) {
            if (conf.getSavedConsumer() != null) {
                conf.getSavedConsumer().accept(value);
            }
        }

        @Override
        public void onError(Throwable t) {
            if (conf.getSaveExceptionConsumer() != null) {
                conf.getSaveExceptionConsumer().accept(t);
            }
        }

        @Override
        public void onCompleted() {

        }
    }

    /**
     * 保存日志
     *
     * @param request 日志内容
     */
    public void save(SaveLogRequest request) {
        stub.withDeadlineAfter(conf.getGrpcTimeoutMs(), TimeUnit.MILLISECONDS).store(request, new responseObserver(conf));
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
