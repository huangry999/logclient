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

        switch (eventObject.getLevel().levelInt) {
            case TRACE_INT:
            case ALL_INT:
                ESLog.trace(null, msg);
                break;
            case DEBUG_INT:
                ESLog.debug(null, msg);
                break;
            case INFO_INT:
                ESLog.info(null, msg);
                break;
            case WARN_INT:
                ESLog.warning(null, msg);
                break;
            case ERROR_INT:
                ESLog.error(null, msg);
                break;
            case OFF_INT:
                ESLog.service(null, msg);
            default:
                ESLog.trace(null, msg);
                break;
        }
    }
}
