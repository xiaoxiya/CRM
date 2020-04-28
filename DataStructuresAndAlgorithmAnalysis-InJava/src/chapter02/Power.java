package chapter02;

/**
 * @author xiaoxiya
 * @date 2019/12/25 14:57
 * @describe 幂运算改良版 O(logN)
 */
public class Power {
    public static long pow(long x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            return pow(x * x, n / 2);
        } else {
            return pow(x * x, n / 2) * x;
        }
    }

    public static double myPow(double x, int n) {
        if (n >= 0) {
            if (n == 0) {
                return 1;
            }
            return n % 2 == 0 ? myPow(x * x, n / 2) : myPow(x * x, n / 2) * x;
        } else {
            return  (1/x) * (1 / myPow(x, -n - 1));
        }
    }

    public static String longestPalindrome(String s) {
        if (s.length() <= 1)
            return s;

        return s;
    }

    public static void main(String[] args) {
        // -2147483648～2147483647
        System.out.println(myPow(1.00000,-2147483648));
        System.out.println(Math.pow(1.00000,-2147483648));
    }
}
