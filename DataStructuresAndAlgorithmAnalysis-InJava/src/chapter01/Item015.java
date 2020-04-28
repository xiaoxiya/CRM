package chapter01;

/**
 * @author xiaoxiya
 * @date 2019/12/17 19:36
 * @describe 习题1.5，编写一种递归方法，它返回数N的二进制表示中1的个数。
 */
public class Item015 {
    private static int ones(int n) {
        if (n < 2) {
            return n;
        }
        return n % 2 + ones(n / 2);
    }

    public static void main(String[] args) {
      int  one= ones(7);
        System.out.println(one);
    }
}
