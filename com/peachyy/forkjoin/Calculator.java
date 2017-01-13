package com.peachyy.forkjoin;

import java.util.concurrent.RecursiveTask;

public class Calculator extends RecursiveTask<Integer> {
 
    private static final int THRESHOLD = 10;
    private int start;
    private int end;
 
    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        if((end - start) < THRESHOLD){
            sum=calSingle();
        }else{
            int middle = (start + end) /2;
            Calculator task1 = new Calculator(start, middle);
            Calculator task2 = new Calculator(middle + 1, end);
            //首先拆分任务
            task2.fork();
            int i=task2.join();
            int i2=task1.invoke();
            invokeAll(task1,task2);
//            int i=task1.getRawResult();
//            int i2=task2.getRawResult();
            //聚合
            sum=i+i2;
        }
        return sum;
    }
    private int calSingle(){
        int sum=0;
        for(int i = start; i<= end;i++){
            sum += i;
        }
        return sum;
    }
 
}