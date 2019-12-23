package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

public class SkipStrategy implements LogStrategy {
    @Override
    public void handle(SaveLogRequest log) throws Exception {
        //do nothing
    }
}
