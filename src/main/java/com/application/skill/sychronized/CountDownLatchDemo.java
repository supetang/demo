package com.application.skill.sychronized;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
@Slf4j
public class CountDownLatchDemo {

    private static int total = 0;
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                    for (int j = 0; j < 1000; j++) {
                        total++;
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
