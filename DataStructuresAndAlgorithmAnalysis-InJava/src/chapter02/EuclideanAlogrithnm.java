package chapter02;

/**
 * @author xiaoxiya
 * @date 2019/12/25 14:43
 * @describe 计算最大公因数的欧几里得算法 O(logN)
 */
public class EuclideanAlogrithnm {
    public static long gcd(long m, long n) {
        while (n != 0) {
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(gcd(100, 20));
        //System.out.println(gcd2(20, 5));
    }
}
