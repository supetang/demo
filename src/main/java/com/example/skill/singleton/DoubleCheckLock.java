package com.example.skill.singleton;

/**
 * 设计模式值单例模式->懒汉模式
 * */
public class DoubleCheckLock {

    private static DoubleCheckLock instance;

    private DoubleCheckLock() {

    }

    public static DoubleCheckLock getInstance() {
        // 第一次检查
        if (instance == null) {
            // 同步
            synchronized (DoubleCheckLock.class) {
                if (instance == null) {
                    // 多线程环境下可能会出现问题的地方
                    instance = new DoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
