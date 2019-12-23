package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

/**
 * 日志策略
 */
public interface LogStrategy {

    /**
     * 日志策略
     *
     * @param log 日志
     */
    void handle(SaveLogRequest log) throws Exception;
}
