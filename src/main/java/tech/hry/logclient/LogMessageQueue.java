package tech.hry.logclient;

import tech.hry.logclient.grpc.SaveLogRequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 存放日志消息
 */
class LogMessageQueue {
    private static final int maxLengthToSkip = 2 << 10;
    private static final BlockingQueue<SaveLogRequest> esMessageQueue = new LinkedBlockingQueue<>(2 << 10);
    private static final BlockingQueue<SaveLogRequest> overFlowMessageQueue = new LinkedBlockingQueue<>(2 << 11);
    private static final BlockingQueue<SaveLogRequest> failMessageQueue = new LinkedBlockingQueue<>(2 << 11);

    private final static EsLogMessageConsumer esLogMessageConsumer = new EsLogMessageConsumer();
    private final static FailLogMessageConsumer failLogMessageConsumer = new FailLogMessageConsumer();
    private final static OverFlowLogMessageConsumer overFlowLogMessageConsumer = new OverFlowLogMessageConsumer();

    static {
        esLogMessageConsumer.start();
        failLogMessageConsumer.start();
        overFlowLogMessageConsumer.start();
    }

    /**
     * 当处理出错的时候，添加日志到错误日志队列里
     * 注意：错误队列超过一定长度(2 << 10)会自动清空
     *
     * @param log 日志
     */
    static void offerFail(SaveLogRequest log) {
        if (log == null) {
            return;
        }
        if (failMessageQueue.size() >= maxLengthToSkip) {
            failMessageQueue.clear();
        }
        failMessageQueue.offer(log);
    }

    /**
     * 添加ES的日志到ES队列，当ES队列满时，将旧日志移动到溢出队列中
     * 注意：溢出队列超过一定长度(2 << 10)会自动清空
     *
     * @param log 日志
     */
    static void offerEs(BabyLog log) {
        if (log == null) {
            return;
        }
        boolean inserted = false;
        while (!inserted) {
            inserted = esMessageQueue.offer(log.convert());
            if (!inserted) {
                esMessageQueue.drainTo(overFlowMessageQueue);
                if (overFlowMessageQueue.size() >= maxLengthToSkip) {
                    overFlowMessageQueue.clear();
                }
            }
        }
    }

    /**
     * 阻塞试获取ES日志
     *
     * @return 日志
     */
    static SaveLogRequest pollEs() throws InterruptedException {
        return esMessageQueue.take();
    }

    /**
     * 阻塞试获取溢出队列日志
     * 注意：溢出队列超过一定长度(2 << 10)会自动清空
     *
     * @return 日志
     */
    static SaveLogRequest pollOverFlow() throws InterruptedException {
        return overFlowMessageQueue.take();
    }

    /**
     * 阻塞试获取错误队列日志
     * 注意：错误队列超过一定长度(2 << 10)会自动清空
     *
     * @return 日志
     */
    static SaveLogRequest pollFail() throws InterruptedException {
        return failMessageQueue.take();
    }
}
