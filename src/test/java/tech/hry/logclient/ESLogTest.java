package tech.hry.logclient;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import static org.junit.Assume.*;

public class ESLogTest {

    @Test
    public void log() throws Exception {
        AtomicBoolean success = new AtomicBoolean(false);

        ESLog.APP_NAME = "log";
        LogServiceClient.setCallback(() -> {
            LogServiceClientConf clientConf = new LogServiceClientConf();
            clientConf.setGrpcHost("192.168.1.8");
            clientConf.setGrpcIp(9090);
            clientConf.setGrpcTimeoutMs(3000);
            clientConf.setSaveExceptionConsumer(e -> System.err.println(e.getMessage()));
            clientConf.setSavedConsumer(r -> success.set(true));
            return clientConf;
        });
        ESLog.info("Test", "test info", "key", "logfun");
        Thread.sleep(4000);
        assumeTrue(success.get());
    }
}