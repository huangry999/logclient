package tech.hry.logclient;

import com.google.common.base.Strings;
import tech.hry.logclient.grpc.LogLevel;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ESLog {

    public static ESLog getInstance(){
        return new ESLog();
    }

    private ESLog() {
    }

    public void trace(String title, String message, Object... tags) {
        log(LogLevel.TRACE, title, message, tags);
    }

    public void debug(String title, String message, Object... tags) {
        log(LogLevel.DEBUG, title, message, tags);
    }

    public void info(String title, String message, Object... tags) {
        log(LogLevel.INFO, title, message, tags);
    }

    public void warning(String title, String message, Object... tags) {
        log(LogLevel.WARNING, title, message, tags);
    }

    public void error(String title, Throwable throwable, Object... tags) {
        log(LogLevel.ERROR, title, throwableToString(throwable), tags);
    }

    public void service(String title, Throwable throwable, Object... tags) {
        log(LogLevel.SERVICE, title, throwableToString(throwable), tags);
    }

    public void error(String title, String message, Object... tags) {
        log(LogLevel.ERROR, title, message, tags);
    }

    public void service(String title, String message, Object... tags) {
        log(LogLevel.SERVICE, title, message, tags);
    }

    private void log(LogLevel level, String title, String message, Object... tags) {
        BabyLog babyLog = new BabyLog();
        if (tags.length > 1) {
            for (int i = 0; i < tags.length; i += 2) {
                if (i + 1 < tags.length && tags[i] != null && tags[i + 1] != null) {
                    babyLog.getTags().put(tags[i].toString(), tags[i + 1].toString());
                }
            }
        }
        babyLog.setLogLevel(level == null ? LogLevel.TRACE : level);
        babyLog.setTitle(Strings.isNullOrEmpty(title) ? "" : title);
        babyLog.setMessage(Strings.isNullOrEmpty(message) ? "" : message);
        LogMessageQueue.offerEs(babyLog);
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
