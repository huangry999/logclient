package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class OverFlowLogMessageConsumer extends LogMessageConsumer {
    private static final SkipStrategy defaultStrategy = new SkipStrategy();

    @Override
    public void run() {
        SaveLogRequest log = null;
        try {
            log = LogMessageQueue.pollOverFlow();
            LogStrategy strategy = LogServiceClient.getInstance().getConf().getOverFlowStrategy();
            if (strategy == null){
                defaultStrategy.handle(log);
            }else{
                strategy.handle(log);
            }
        } catch (Exception e) {
            LogMessageQueue.offerFail(log);
        }
    }
}
