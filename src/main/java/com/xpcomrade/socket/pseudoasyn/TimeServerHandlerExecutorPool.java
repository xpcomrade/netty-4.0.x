package com.xpcomrade.socket.pseudoasyn;

import java.util.concurrent.*;

/**
 * Created by xpcomrade on 2015/10/30.
 */
public class TimeServerHandlerExecutorPool {

    private ExecutorService executorService;

    public TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize) {
        executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize)
        );
    }

    public Future execute (Runnable task) {
        return this.executorService.submit(task);
    }

}
