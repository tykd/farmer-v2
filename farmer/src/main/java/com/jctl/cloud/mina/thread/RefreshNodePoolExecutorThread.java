package com.jctl.cloud.mina.thread;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作为线程池处理并发业务
 * 线程池作为单例的  保证了并发业务时不受影响
 * Created by kay on 2017/2/28.
 */
@Service
public class RefreshNodePoolExecutorThread {

    private RefreshNodePoolExecutorThread() {
    }


    private volatile static ExecutorService nodePoolExecutor;

    public static ExecutorService getNodePoolExecutor() {
        if (nodePoolExecutor == null) {
            synchronized (ExecutorService.class) {
                if (nodePoolExecutor == null) {
                    nodePoolExecutor = new ThreadPoolExecutor(0, 20,
                            60L, TimeUnit.SECONDS,
                            new SynchronousQueue<Runnable>());
                }
            }
        }
        return nodePoolExecutor;
    }

//    //初始化一个池
//    private static ExecutorService nodePoolExecutor = new ThreadPoolExecutor(0, 20,
//            60L, TimeUnit.SECONDS,
//            new SynchronousQueue<Runnable>());
//
//    public static ExecutorService getNodePoolExecutor() {
//        return nodePoolExecutor;
//    }

}
