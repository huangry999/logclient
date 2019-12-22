package tech.hry.logclient;

import lombok.Getter;
import lombok.Setter;
import tech.hry.logclient.grpc.SaveLogResponse;

import java.util.function.Consumer;

@Getter
@Setter
public class LogServiceClientConf {

    private String grpcHost;
    private int grpcIp;
    private int grpcTimeoutMs;
    private Consumer<Throwable> saveExceptionConsumer;
    private Consumer<SaveLogResponse> savedConsumer;
}
