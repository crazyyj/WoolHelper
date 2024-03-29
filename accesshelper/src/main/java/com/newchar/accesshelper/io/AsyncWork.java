package com.newchar.accesshelper.io;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author newChar
 * date 2021/8/24
 * @since 异步执行
 * @since 迭代版本，（以及描述）
 */
public final class AsyncWork {

    private static final int CORE_POOL_SIZE = 2;
    private static final int MAXIMUM_POOL_SIZE = 4;
    private static final long KEEP_ALIVE_TIME = 60L;

    private final ThreadPoolExecutor mThreadPoolExecutor;
    private static AsyncWork sAsyncWork;

    private AsyncWork() {
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(MAXIMUM_POOL_SIZE));
    }

    public static AsyncWork getInstance() {
        if (sAsyncWork == null) {
            synchronized (AsyncWork.class) {
                if (sAsyncWork == null) {
                    sAsyncWork = new AsyncWork();
                }
            }
        }
        return sAsyncWork;
    }

    public void execute(Runnable command) {
        mThreadPoolExecutor.execute(command);
    }

    public void stopAllTask() {
        if (!mThreadPoolExecutor.isShutdown()) {
            mThreadPoolExecutor.shutdown();
        }
    }

}
