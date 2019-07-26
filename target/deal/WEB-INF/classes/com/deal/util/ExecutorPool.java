package com.deal.util;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

/**
 * 线程池管理
 * @author zhipeng.xu
 *
 */
@Service
public class ExecutorPool {
	private ExecutorService exeService = Executors.newCachedThreadPool();
	
	
	private static class ThreadPoolHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static ExecutorPool instance = new ExecutorPool();
    }
	
	public static ExecutorPool getInstance() {
		return ThreadPoolHolder.instance;
	}

	public Future<?> submit(Runnable runnable) {
		return exeService.submit(runnable);
	}
}
