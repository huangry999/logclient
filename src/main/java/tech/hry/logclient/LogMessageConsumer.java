package tech.hry.logclient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class LogMessageConsumer implements Runnable {
    private final ExecutorService pool = Executors.newSingleThreadExecutor();

    void start() {
        pool.submit(this);
    }

    void shutdown(){
        pool.shutdown();
    }
}
