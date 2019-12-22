package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogResponse;

public interface ResponseCallback {

    void response(SaveLogResponse response);
}
