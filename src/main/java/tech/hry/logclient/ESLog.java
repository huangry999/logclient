package tech.hry.logclient;

import tech.hry.logclient.grpc.LogLevel;
import tech.hry.logclient.grpc.SaveLogRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ESLog {
    private static final SimpleDateFormat logTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    /**
     * 本应用的APP Name，ES中的索引
     */
    public static String APP_NAME = "log";

    public static void trace(String title, String message, String... tags) {
        log(LogLevel.TRACE, title, message, tags);
    }

    public static void debug(String title, String message, String... tags) {
        log(LogLevel.DEBUG, title, message, tags);
    }

    public static void info(String title, String message, String... tags) {
        log(LogLevel.INFO, title, message, tags);
    }

    public static void warning(String title, String message, String... tags) {
        log(LogLevel.WARNING, title, message, tags);
    }

    public static void error(String title, Throwable throwable, String... tags) {
        log(LogLevel.ERROR, title, throwableToString(throwable), tags);
    }

    public static void service(String title, Throwable throwable, String... tags) {
        log(LogLevel.SERVICE, title, throwableToString(throwable), tags);
    }

    public static void error(String title, String message, String... tags) {
        log(LogLevel.ERROR, title, message, tags);
    }

    public static void service(String title, String message, String... tags) {
        log(LogLevel.SERVICE, title, message, tags);
    }

    private static void log(LogLevel level, String title, String message, String... tags) {
        SaveLogRequest.Builder builder = SaveLogRequest.newBuilder();
        for (int i = 0; i < tags.length; i += 2) {
            if (i + 1 < tags.length) {
                builder.putTags(tags[i], tags[i + 1]);
            }
        }
        SaveLogRequest saveLogRequest = builder
                .setAppName(APP_NAME)
                .setLevel(level)
                .setTitle(title)
                .setMessage(message)
                .setTime(logTimeFormat.format(new Date()))
                .build();
        LogMessageQueue.offerEs(saveLogRequest);
    }

    private static String throwableToString(Throwable throwable) {
        if (throwable != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            throwable.printStackTrace(writer);
            return stringWriter.toString();
        }
        return null;
    }
}
