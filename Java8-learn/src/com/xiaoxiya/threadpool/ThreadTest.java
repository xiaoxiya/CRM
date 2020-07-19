package com.xiaoxiya.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xiaoxiya
 * @date 2020/7/5 15:52
 * @describe
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        //获取开始时间
        Long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<>();
        for (int i =0; i < 100000; i++) {
            Thread thread = new Thread() {
                public void run () {
                    list.add(random.nextInt());
                }
            };

            thread.start();
            //在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待，直到目标线程结束
            thread.join();
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(list.size());
    }
}
