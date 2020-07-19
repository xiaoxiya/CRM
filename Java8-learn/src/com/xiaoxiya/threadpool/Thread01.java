package com.xiaoxiya.threadpool;

/**
 * @author xiaoxiya
 * @date 2020/7/3 23:26
 * @describe
 */
public class Thread01 extends Thread {
    private String name;

    public Thread01(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(String.format("输入的名字为:%s",name));
    }

    public static void main(String[] args) {
        //线程调用方法
        //new  Thread01("线程，你好").start();

        Thread01 thread = new Thread01("tesss");

        thread.start();
        //方法级别的调用
        //new Thread01("方法级别的调用").run();
    }
}
