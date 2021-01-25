package com.xiaoxiya.CAS;


/**
 * @author xiaoxiya
 * @date 2021/1/25 20:23
 * @describe 无锁实现的栈接口
 */
public interface LockFreeStack<T> {

    boolean push(T t);

    T pop();
}
