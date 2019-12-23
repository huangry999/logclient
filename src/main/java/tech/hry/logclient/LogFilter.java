package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

public interface LogFilter {

    /**
     * 过滤器
     *
     * @param log 日志
     * @return 是否存库
     */
    boolean accept(SaveLogRequest log);
}
