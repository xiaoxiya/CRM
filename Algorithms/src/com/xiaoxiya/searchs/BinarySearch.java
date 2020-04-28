package com.xiaoxiya.searchs;

/**
 * @author xiaoxiya
 * @date 2019/12/25 14:23
 * @describe 二分查找法
 */
public class BinarySearch implements SearchAlgorithm{
    @Override
    public <T extends Comparable<? super T>> int find(T[] arr, T key) {
        int low = 0, high = arr.length - 1;
        while (low <= high){
            int mid = (low + high)/2;
            if (arr[mid].compareTo(key) < 0) {
                low = mid + 1;
            } else if (arr[mid].compareTo(key) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
