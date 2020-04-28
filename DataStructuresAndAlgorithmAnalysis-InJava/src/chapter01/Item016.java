package chapter01;

/**
 * @author xiaoxiya
 * @date 2019/12/17 19:57
 * @describe 习题1.6， 字符串的去重全排列问题,第二个方法使用递归
 * 描述：比如输入abc，输出abc,acb,bca,cab,cba
 */
public class Item016 {
    public static void main(String[] args) {
        Item016 item016 = new Item016();
        item016.permute("abc");
    }

    public void permute(String str) {
        char[] ch = str.toCharArray();
        permute(ch, 0, ch.length-1);
    }

    private void permute(char[] str, int low, int high) {
        int len = str.length;
        //递归结束条件
        if (low == high) {
            String s = "";
            for (int i = 0; i < len; i++) {
                s += str[i];
            }
            System.out.println(s);
        } else {
            //第i个字符分别与它后面的字符交换就能得到新的排列
            for (int i = low; i < len; i++) {
                //判断是否需要交换
                if (!isSwap(str, low, i)) {
                    //交换两个字符
                    swap(str, low, i);
                    permute(str, low + 1, high);
                    //复原
                    swap(str, low, i);
                }
            }
        }
    }

    private static void swap(char[] str, int m, int n) {
        char temp = str[n];
        str[n] = str[m];
        str[m] = temp;
    }

    private static boolean isSwap(char[] str, int m, int n) {
        boolean flag = false;
        for (int i = m; i < n; i++) {
            if (str[i] == str[n]) {
                flag =true;
                break;
            }
        }
        return flag;
    }


}
