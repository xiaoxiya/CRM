package com.xiaoxiya.lambda;

/**
 * @author xiaoxiya
 * @date 2019/12/5 20:55
 * @describe 匿名内部类的使用
 */
public class JavaAnoymous {

    public static void main(String[] args) {
        anonymousClass();
        new JavaAnoymous().notAnonymousClass();
    }

    /**
     * 匿名类的使用
     */
    public static void anonymousClass() {
        info(new Programmer() {
            @Override
            public void listLanguages() {
                System.out.println("Objective-C Swift Python Go Java");
            }

            @Override
            public void introduceMyself() {
                System.out.println("My Name is Jiaming Chen");
            }
        });
    }

    /**
     * 不使用匿名类的情况
     */
    public void notAnonymousClass() {
        info(new MyProgrammer());
    }

    static void info(Programmer programmer)
    {
        programmer.listLanguages();
        programmer.introduceMyself();
    }


    interface Programmer
    {
        void listLanguages();
        void introduceMyself();
    }

    class MyProgrammer implements Programmer
    {
        @Override
        public void listLanguages() {
            System.out.println("Objective-C Swift Python Go Java test");
        }

        @Override
        public void introduceMyself() {
            System.out.println("My Name is Jiaming Chen test");
        }
    }

}
