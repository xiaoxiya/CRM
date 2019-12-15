package chapter01;

import java.util.*;

/**
 * @author xiaoxiya
 * @date 2019/12/15 10:37
 * @describe 数据结构与算法分析java语言描述-第一章第1.2题
 * 编写一个程序求解字谜游戏问题
 * 找出二维数组中的单词，可能是横向，纵向，斜方向（任意方向）
 */
public class Item012 {
    static final char[][] letters = new char[][]{
            {'t', 'h', 'i', 's'},
            {'w', 'a', 't', 's'},
            {'o', 'a', 'h', 'g'},
            {'f', 'g', 'd', 't'}
    };
    static String[] dic = {"this", "two", "fat", "that"};
    //最小长度
    static int minLength = letters.length;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        //找出最小长度
        for (String str : dic) {
            if (str.length() < minLength) {
                minLength = str.length();
            }
        }

        //枚举所有字谜数组
        getAllChar(minLength);

        //校验是否在字谜中
        Set<String> set = new TreeSet<>();
        for (String str : list) {
            for (int i =0; i < dic.length; i++) {
                if (str.indexOf(dic[i]) != -1) {
                    set.add(dic[i]);
                }
            }
        }
        //4.打印单词
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            String next = it.next();
            System.out.print(next + " ");
        }
    }

    /**
     * 枚举字谜数组
     */
    private static void getAllChar(int minLength) {
        for (int i = 0; i < letters.length; i++) {
            int n = letters[i].length;
            //横向和纵向
            char[] arr1 = new char[n];
            char[] arr2 = new char[n];
            for (int j = 0; j < n; j++) {
                arr1[j] = letters[i][j];
                arr2[j] = letters[j][i];
            }
            list.add(String.valueOf(arr1));
            list.add(String.valueOf(arr2));
            //斜边
            if (i >= minLength-1) {
                //左上
                char[] arr3 = new char[i + 1];
                //左下
                char[] arr4 = new char[i + 1];
                //右上
                char[] arr5 = new char[i + 1];
                ////右下
                char[] arr6 = new char[i + 1];
                int k = 0, len = i, wid = letters.length - 1;
                while (len >= 0) {
                    arr3[k] = letters[k][len];
                    arr4[k] = letters[wid][len];
                    arr5[k] = letters[len][wid];
                    arr6[k] = letters[letters.length - 1 - len][wid];
                    k++;
                    len--;
                    wid--;
                }
                reverse(arr3);
                reverse(arr4);
                reverse(arr5);
                reverse(arr6);
            }
        }
    }

    /**
     * 反转字符
     */
    private static void reverse(char[] chars) {
        char[] str = new char[chars.length];
        int j = 0;
        for (int i = chars.length-1; i >= 0; i--) {
            str[j] = chars[i];
            j++;
        }
        list.add(String.valueOf(chars));
        list.add(String.valueOf(str));
    }

}
