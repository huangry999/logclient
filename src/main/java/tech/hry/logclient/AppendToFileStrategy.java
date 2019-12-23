package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

import java.io.File;
import java.io.FileWriter;

public class AppendToFileStrategy implements LogStrategy {

    private final File file;

    public AppendToFileStrategy(File file) {
        this.file = file;
    }

    @Override
    public void handle(SaveLogRequest log) throws Exception {
        assert file.exists() || file.createNewFile();
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(System.lineSeparator());
            String logStr = String.format("%s [%s] %s: %s %s", log.getTime(), log.getLevel(), log.getTitle(), log.getMessage(), log.getTagsMap());
            writer.append(logStr);
        }
    }
}
