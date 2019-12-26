package tech.hry.logclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assume.assumeTrue;

@Slf4j
public class ESLogTest {

    @Test
    public void log() throws Exception {
        final File file = new File("D:\\test.log");

        ESLog.APP_NAME = "log";
        LogServiceClient.setCallback(new LogServiceClientConfCallback() {
            @Override
            public LogServiceClientConf config() {
                LogServiceClientConf clientConf = new LogServiceClientConf();
                clientConf.setGrpcHost("94hry.tech");
                clientConf.setGrpcIp(9090);
                clientConf.setGrpcTimeoutMs(3000);
                clientConf.setFailStrategy(new AppendToFileStrategy(file));
                clientConf.setOverFlowStrategy(new AppendToFileStrategy(file));
                return clientConf;
            }
        });
        ESLog.info("Test", "test info", "key", "logfun");
        log.debug("haha");
        Thread.sleep(4000);
    }
}