package com.xiaoxiya.searchs;

/**
 * @author xiaoxiya
 * @date 2019/12/25 14:17
 * @describe
 */
public interface SearchAlgorithm {
    /**
     * 搜索算法
     * @param arr 数组
     * @param key 寻找的key
     * @param <T>
     * @return 返回找到的数组下标或者没有找到返回-1
     */
    <T extends Comparable<? super T>> int find(T[] arr, T key);
}
