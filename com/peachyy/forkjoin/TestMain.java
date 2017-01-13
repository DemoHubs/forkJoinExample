package com.peachyy.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/1/9.</p>
 */
public class TestMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Calculator calculator=new Calculator(0,500);

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> f=pool.submit(calculator);

        long start=System.currentTimeMillis();

        System.out.println(System.currentTimeMillis()-start+"MS");
        do{
            if(calculator.isCompletedNormally()) {
            System.out.println("计算完成 正在关闭Fork/Join池...");
            pool.shutdown();
        }
        }while(!calculator.isDone());
        System.out.println("计算结果为："+f.get());
        System.out.println("线程已经从另一个线程偷取到的时间数:"+pool.getStealCount());
        System.out.println("是否已经完成执行:"+pool.isTerminated());
        System.out.println("并行的级别："+pool.getParallelism());
        System.out.println("线程池的worker线程的数量："+pool.getPoolSize());

        System.out.println("执行的任务数："+pool.getQueuedTaskCount());
    }
}
