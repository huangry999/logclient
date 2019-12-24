package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

class OverFlowLogMessageConsumer extends LogMessageConsumer {
    private static final SkipStrategy defaultStrategy = new SkipStrategy();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            SaveLogRequest log = null;
            try {
                log = LogMessageQueue.pollOverFlow();
                LogStrategy strategy = LogServiceClient.getInstance().getConf().getOverFlowStrategy();
                if (strategy == null) {
                    defaultStrategy.handle(log);
                } else {
                    strategy.handle(log);
                }
            } catch (Exception e) {
                LogMessageQueue.offerFail(log);
            }
        }
    }
}
