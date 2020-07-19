package com.xiaoxiya.sorts;

import java.util.Arrays;

/**
 * @author xiaoxiya
 * @date 2019/12/10 20:24
 * @describe 选择排序算法（O（n^2））
 */
public class SelectSort implements SortAlgorithm{

    static int[] sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            //N = nums.length
            //从未排序的序列list[i]到list[N-1]中找出最小值，并将位置记录在min
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                   min = j;
                }
            }
            //将未排序的最小值交换到有序部分的最后位置
            if (min != i) {
                int temp = nums[min];
                nums[min] = nums [i];
                nums[i] = temp;
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2, 4};
        sort(nums);
        Arrays.stream(nums).forEach(System.out::println);

        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        SelectSort selectionSort = new SelectSort();

        Integer[] sorted = selectionSort.sort(arr);

        SortUtils.print(sorted);

        String[] strings = {"c", "a", "e", "b", "d"};
        String[] sortedStrings = selectionSort.sort(strings);

        SortUtils.print(sortedStrings);
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (SortUtils.less(array[j], array[min])) {
                    min = j;
                }
            }
            if (min != i) {
                SortUtils.swap(array, i, min);
            }
        }
        return array;
    }
}
