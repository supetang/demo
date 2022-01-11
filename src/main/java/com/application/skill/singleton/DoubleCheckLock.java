package com.application.skill.singleton;

/**
 * 设计模式值单例模式->懒汉模式
 *
 * @author tangchao*/
public class DoubleCheckLock {

    private DoubleCheckLock() {

    }

    private static final class InstanceHolder {
        static final DoubleCheckLock instance = new DoubleCheckLock();
    }

    public static DoubleCheckLock getInstance() {
        // 第一次检查
        // 同步
        // 多线程环境下可能会出现问题的地方
        return InstanceHolder.instance;
    }
}
