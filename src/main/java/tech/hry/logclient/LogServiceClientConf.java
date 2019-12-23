package tech.hry.logclient;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LogServiceClientConf {

    private String grpcHost;
    private int grpcIp;
    private int grpcTimeoutMs;
    private List<LogFilter> filters;
    private LogStrategy overFlowStrategy;
    private LogStrategy failStrategy;
}
