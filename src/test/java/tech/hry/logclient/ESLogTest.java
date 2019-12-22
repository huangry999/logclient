package tech.hry.logclient;

import org.junit.Test;
import tech.hry.logclient.grpc.SaveLogResponse;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assume.assumeTrue;

public class ESLogTest {

    @Test
    public void log() throws Exception {
        final AtomicBoolean success = new AtomicBoolean(false);

        ESLog.APP_NAME = "log";
        LogServiceClient.setCallback(new LogServiceClientConfCallback() {
            @Override
            public LogServiceClientConf config() {
                LogServiceClientConf clientConf = new LogServiceClientConf();
                clientConf.setGrpcHost("94hry.tech");
                clientConf.setGrpcIp(9090);
                clientConf.setGrpcTimeoutMs(3000);
                clientConf.setSaveExceptionConsumer(new ThrowableCallback() {
                    @Override
                    public void exception(Throwable throwable) {
                        System.err.println(throwable);
                    }
                });
                clientConf.setSavedConsumer(new ResponseCallback() {
                    @Override
                    public void response(SaveLogResponse response) {
                        success.set(true);
                    }
                });
                return clientConf;
            }
        });
        ESLog.info("Test", "test info", "key", "logfun");
        Thread.sleep(4000);
        assumeTrue(success.get());
    }
}