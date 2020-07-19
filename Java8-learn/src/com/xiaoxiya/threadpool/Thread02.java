package com.xiaoxiya.threadpool;

/**
 * @author xiaoxiya
 * @date 2020/7/3 23:26
 * @describe
 */
public class Thread02 implements Runnable {
    private String name;

    public Thread02(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(String.format("输入的名字为:%s",name));
    }

    public static void main(String[] args) {
        //方法级别的调用
        new Thread02("方法级别的调用").run();
    }
}
