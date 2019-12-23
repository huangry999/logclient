package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FailLogMessageConsumer extends LogMessageConsumer {
    private static final SkipStrategy defaultStrategy = new SkipStrategy();

    @Override
    public void run() {
        SaveLogRequest log;
        try {
            log = LogMessageQueue.pollFail();
            LogStrategy strategy = LogServiceClient.getInstance().getConf().getFailStrategy();
            if (strategy == null) {
                defaultStrategy.handle(log);
            } else {
                strategy.handle(log);
            }
        } catch (Exception e) {
            //ignore
        }
    }
}
