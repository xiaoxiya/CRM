package chapter01;


/**
 * @author xiaoxiya
 * @date 2019/12/15 17:46
 * @describe 数据结构与算法分析java语言描述-第一章第1.3题
 * 只使用处理I/O的printDigit编写一种方法可以输出任意double型量(可以为负数)
 */
public class Item013 {

    public static void main(String[] args) {
        printNum(-0.0040567890);
    }
    /**
     * 处理整数部分
     */
    private static void printNum(double num) {
        if (num < 0 ) {
            System.out.print("-");
            num = Math.abs(num);
        }
        //获取整数部分
        long a = (long) num;
        //整数长度
        int intLength = String.valueOf(a).length();
        //小数部分长度
        int digitLength = 0;
        if (String.valueOf(num).contains(".")) {
            // 扣除小数点位数
            digitLength = String.valueOf(num).length() - intLength -1;
        }
        // 当 -intLength + 1 = digitLength时，可判定递归结束
        printDigit(num,  -intLength, digitLength);
    }

    /**
     * 处理小数部分
     */
    private static void printDigit(double num, int intLength, int digitLength) {
        //递归结束标记
        if (intLength == digitLength) {
            return;
        }
        intLength = intLength + 1;
        //pow 将第一个参数的值返回到第二个参数的幂
        long temp = (long) (num * Math.pow(10, intLength));
        long temp2 = temp % 10;
        System.out.print(temp2);
        //判断小数点位置
        if (intLength == 0) {
            System.out.print(".");
        }
        printDigit(num, intLength, digitLength);
    }
}
