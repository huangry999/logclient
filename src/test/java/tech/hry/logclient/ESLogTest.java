package tech.hry.logclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

@Slf4j
public class ESLogTest {

    @Test
    public void log() throws Exception {
        final File file = new File("D:\\test.log");

        LogServiceClient.setCallback(new LogServiceClientConfCallback() {
            @Override
            public LogServiceClientConf config() {
                LogServiceClientConf clientConf = new LogServiceClientConf();
                clientConf.setGrpcHost("94hry.tech");
                clientConf.setGrpcIp(9090);
                clientConf.setGrpcTimeoutMs(3000);
                clientConf.setFailStrategy(new AppendToFileStrategy(file));
                clientConf.setOverFlowStrategy(new AppendToFileStrategy(file));
                clientConf.setAppName("log");
                return clientConf;
            }
        });
        ESLog.getInstance().info("Test", "test info", "key", "logfun");
        log.debug("haha");
        Thread.sleep(400000000);
    }
}