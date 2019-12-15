package chapter01;

import java.util.Arrays;

/**
 * @author xiaoxiya
 * @date 2019/12/14 19:37
 * @describe 数据结构与算法分析java语言描述-第一章第1.1题
 */
public class Item011 {

    /**
     * 书中建议的第一种方式
     * 通过排序将某个数据以递减的顺序排列，选出第k个元素
     */
    private static int getKMaxNum1(int[] arr, int k) {
        sortArrayByDesc(arr);
        return arr[k-1];
    }

    /**
     * 冒泡排序
     */
    private static void sortArrayByDesc(int[] arrays) {
        int n = arrays.length;
        boolean isSort = true;
        for (int i = n - 1; i > 0 && isSort; i--) {
            for (int j = 0; j < i; j++) {
                if (arrays[j] < arrays[j + 1]) {
                    isSort = false;
                    int temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }
    }

    /**
     * 书中建议的第二种方式
     * 以递减排序的方式将前k个元素存入数据
     * 如果新元素小于数据中的第k个元素，不处理
     * 否则将其放入正确位置
     */
    private static int getKMaxNum2(int arr[], int k) {
        //前k个元素存入新数组
        int newArr[] = Arrays.copyOf(arr, k);
        //递减排序
        sortArrayByDesc(newArr);
        //更新元素操作
        for (int i = k; i < arr.length - k; i++) {
            if (arr[i] > newArr[k-1]) {
                newArr[k-1] = arr[i];
                sortArrayByDesc(newArr);
            }
        }
        return newArr[k - 1];
    }


}
