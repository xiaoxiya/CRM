package com.xiaoxiya.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoxiya
 * @date 2021/1/25 20:22
 * @describe 基于数组的无锁栈实现
 */
public class LockFreeStackByArray<E> implements LockFreeStack<E>{

    final Object[] elements;

    final int capacity;

    //栈顶元素下标，初始值为-1
    AtomicInteger top = new AtomicInteger(-1);

    public LockFreeStackByArray(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }

    /**
     * 入栈
     * @param e
     * @return true：入栈成功
     */
    @Override
    public boolean push(E e) {
        //死循环确保多次CAS尝试后得到结果
        while (true) {
            //当前下标
            int currentTop = top.get();
            if (currentTop + 1 >= capacity) {
                System.out.println("空间已满");
                return false;
            } else {
                elements[currentTop + 1] = e;
                //CAS验证栈顶指针
                if (top.compareAndSet(currentTop, currentTop + 1)) {
                    return true;
                }
            }
        }
    }

    /**
     * 出栈
     * @return
     */
    @Override
    public E pop() {
        while (true) {
            //获取栈顶元素
            int currentTop = top.get();
            //栈为空
            if (currentTop == -1) {
                return null;
            } else {
                //CAS更新栈顶指针
                if (top.compareAndSet(currentTop, currentTop - 1)) {
                  return  (E) elements[currentTop];
                }
            }

        }
    }
}
