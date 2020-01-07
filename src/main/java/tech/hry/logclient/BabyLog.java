package tech.hry.logclient;

import lombok.Data;
import tech.hry.logclient.grpc.LogLevel;
import tech.hry.logclient.grpc.SaveLogRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
class BabyLog {
    private LogLevel logLevel;
    private String title;
    private String message;
    private Map<String, String> tags = new HashMap<>();
    private Date time = new Date();

    static BabyLog valueOf(SaveLogRequest src, LogServiceClientConf conf) {
        BabyLog babyLog = new BabyLog();
        babyLog.setMessage(src.getMessage());
        babyLog.setTitle(src.getTitle());
        babyLog.setTags(src.getTagsMap());
        babyLog.setLogLevel(src.getLevel());
        try {
            babyLog.setTime(LogServiceClient.getInstance().getConf().getLogTimeFormat().parse(src.getTime()));
        } catch (Exception e) {
            //ignore
        }
        return babyLog;
    }

    SaveLogRequest convert() {
        LogServiceClientConf conf = new LogServiceClientConf();
        try {
            conf = LogServiceClient.getInstance().getConf();
        } catch (Exception e) {
            //ignore
        }
        return SaveLogRequest.newBuilder()
                .setAppName(conf.getAppName())
                .setLevel(this.logLevel)
                .setMessage(this.message)
                .setTime(conf.getLogTimeFormat().format(this.time))
                .setTitle(this.title)
                .putAllTags(this.tags)
                .build();
    }
}
