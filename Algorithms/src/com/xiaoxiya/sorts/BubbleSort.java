package com.xiaoxiya.sorts;

import static com.xiaoxiya.sorts.SortUtils.*;

/**
 * @author xiaoxiya
 * @date 2019/12/4 21:28
 * @describe  冒泡排序算法java实现
 */
public class BubbleSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int n = array.length;
        boolean hasSorted = false;
        for (int i = n - 1; i > 0 && !hasSorted; i--){
            hasSorted = true;
            for (int j = 0; j < i; j++) {
                if (less(array[j + 1], array[j])) {
                    hasSorted = false;
                    swap(array, j, j+1);
                }
            }
        }
        return array;
    }
    public static void main(String[] args) {
        Integer array[] = {1,2,6,5,8,4};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        print(array);

        String[] strings = {"c", "a", "e", "b", "d"};

        print(bubbleSort.sort(strings));
    }

}
