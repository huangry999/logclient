package tech.hry.logclient;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.List;

@Getter
@Setter
public class LogServiceClientConf {
    public static final String DEFAULT_APP_NAME = "log";
    public static final SimpleDateFormat DEFAULT_LOG_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    /**
     * 本应用的APP Name，ES中的索引
     */
    private String appName = DEFAULT_APP_NAME;
    /**
     * 时间格式化方法
     */
    private SimpleDateFormat logTimeFormat = DEFAULT_LOG_TIME_FORMAT;
    private String grpcHost;
    private int grpcIp;
    private int grpcTimeoutMs;
    private List<LogFilter> globalFilters;
    private List<LogAspect> globalAspects;
    private LogStrategy overFlowStrategy;
    private LogStrategy failStrategy;
    /**
     * 线程内的切面
     * 注：ThreadLocal无法用在父子线程和线程池的场景
     */
    private final ThreadLocal<LogAspect> aspectThreadLocal = new ThreadLocal<>();
}
