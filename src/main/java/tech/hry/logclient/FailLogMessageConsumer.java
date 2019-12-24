package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

class FailLogMessageConsumer extends LogMessageConsumer {
    private static final SkipStrategy defaultStrategy = new SkipStrategy();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
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
}
