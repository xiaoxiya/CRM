package com.xiaoxiya.sorts;

/**
 * @author xiaoxiya
 * @date 2019/12/4 20:59
 */
public interface SortAlgorithm {
    /**
     * 类型 T 必须实现 Comparable 接口，并且这个接口的类型是 T。只有这样，T 的实例之间才能相互比较大小
     * @param unsorted
     * @param <T>
     * @return
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted);

}
