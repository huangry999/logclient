package tech.hry.logclient.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import tech.hry.logclient.ESLog;

import static ch.qos.logback.classic.Level.*;

public class ESAppender4LogBack extends AppenderBase<ILoggingEvent> {

    private Encoder<ILoggingEvent> encoder;

    public void setEncoder(Encoder<ILoggingEvent> encoder) {
        this.encoder = encoder;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        String msg;
        if (encoder != null && LayoutWrappingEncoder.class.isAssignableFrom(encoder.getClass())) {
            msg = ((LayoutWrappingEncoder<ILoggingEvent>) encoder).getLayout().doLayout(eventObject);
        } else {
            msg = eventObject.getFormattedMessage();
        }
        ESLog log = ESLog.getInstance();
        switch (eventObject.getLevel().levelInt) {
            case TRACE_INT:
            case ALL_INT:
                log.trace(null, msg);
                break;
            case DEBUG_INT:
                log.debug(null, msg);
                break;
            case INFO_INT:
                log.info(null, msg);
                break;
            case WARN_INT:
                log.warning(null, msg);
                break;
            case ERROR_INT:
                log.error(null, msg);
                break;
            case OFF_INT:
                log.service(null, msg);
            default:
                log.trace(null, msg);
                break;
        }
    }
}
