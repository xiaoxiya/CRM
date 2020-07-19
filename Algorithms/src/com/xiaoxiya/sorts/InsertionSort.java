package com.xiaoxiya.sorts;

import static com.xiaoxiya.sorts.SortUtils.*;

/**
 * @author xiaoxiya
 * @date 2020/7/19 16:52
 * @describe 插入排序java实现（O（n^2））
 */
public class InsertionSort implements SortAlgorithm{

    @Override
    public <T extends Comparable<T>> T[] sort(T[] a) {
        int j;
        for (int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for (j = p; j > 0 && less(tmp, a[j -1]); j--) {
                //寻找插入的位置
                a[j] = a[j - 1];
            }
            //插入数据
            a[j] = tmp;
        }
        return a;
    }
}
