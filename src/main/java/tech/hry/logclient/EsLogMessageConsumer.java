package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

class EsLogMessageConsumer extends LogMessageConsumer {
    @Override
    public void run() {
        SaveLogRequest log = null;
        try {
            log = LogMessageQueue.pollEs();
            LogServiceClient.getInstance().save(log);
        } catch (Exception e) {
            LogMessageQueue.offerFail(log);
        }
    }
}
