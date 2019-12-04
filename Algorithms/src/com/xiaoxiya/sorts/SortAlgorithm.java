package com.xiaoxiya.sorts;

/**
 * @author xiaoxiya
 * @date 2019/12/4 20:59
 */
public interface SortAlgorithm {

    <T extends Comparable<T>> T[] sort(T[] unsorted);

}
