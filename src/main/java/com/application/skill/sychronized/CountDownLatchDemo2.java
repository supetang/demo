package com.application.skill.sychronized;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 {

    private static int total = 0;
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                    for (int j = 0; j < 1000; j++) {
                        // 临界资源，线程不安全 同步安全处理的方式有两种
                        // TODO 代码同步快的实现 2022/01/07
                        synchronized (object){
                            total++;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(500);

        countDownLatch.countDown();

        Thread.sleep(2000);

        System.out.println(total);
    }
}
