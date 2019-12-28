package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

/**
 * 日志的切面，可以在日志存库前进行一些修改
 */
public interface LogAspect {

    /**
     * 切面
     *
     * @param log 日志
     * @return 处理过后的日志
     */
    SaveLogRequest handle(SaveLogRequest log);
}
