package tech.hry.logclient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogServiceClientConf {

    private String grpcHost;
    private int grpcIp;
    private int grpcTimeoutMs;
    private ThrowableCallback saveExceptionConsumer;
    private ResponseCallback savedConsumer;
}
