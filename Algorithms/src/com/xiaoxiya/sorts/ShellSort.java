package com.xiaoxiya.sorts;

import static com.xiaoxiya.sorts.SortUtils.less;

/**
 * @author xiaoxiya
 * @date 2020/7/19 17:24
 * @describe 希尔排序（缩减增量排序）
 */
public class ShellSort implements SortAlgorithm{


    @Override
    public <T extends Comparable<T>> T[] sort(T[] a) {
        int j;
        for (int gap = a.length/2; gap>0; gap/=3) {
            for (int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for (j = i; j >= gap && less(tmp, a[j - gap]); j -= gap) {
                    a[j] = a[j-gap];
                }
                a[j] = tmp;
            }
        }
        return a;
    }
}
