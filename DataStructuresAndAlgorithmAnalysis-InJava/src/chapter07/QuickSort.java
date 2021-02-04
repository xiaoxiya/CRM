package chapter07;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiya
 * @date 2021/2/3 19:47
 * @describe 快速排序（时间复杂度NlogN）
 */
public class QuickSort {
    public static void sort(List<Integer> items) {
        if (items.size() > 1) {
            //小于切分点的数
            List<Integer> smaller = new ArrayList<>();
            //切分点
            List<Integer> same = new ArrayList<>();
            //大于切分点的数
            List<Integer> larger = new ArrayList<>();

            //中点判断大小（pivot）
            Integer chosenItem = items.get(items.size() / 2);

            for (Integer i : items) {
             if (i < chosenItem)   //小于切分点
                 smaller.add(i);
             else if (i > chosenItem) // 大于切分点
                 larger.add(i);
             else //找到切分点
                 same.add(i);
            }

            sort(smaller);
            sort(larger);

            items.clear();
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);
        }
    }

    private static <T extends  Comparable<? super T>>  void swapReference(T[] a, int i, int n) {
        T maxItem = a[i];
        a[i] = a[n];
        a[n] = maxItem;
    }

    /**
     * quickSort algorithm
     * @param a an array of Comparable items
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }


    private final static int CUTOFF = 10;

    /**
     * internal quickSort method that makes recursive calls.
     * uses median-of-three partitioning and a cutoff of 10.
     * @param a an array of comparable items
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right) {
        if (left + CUTOFF <= right) {
            T pivot = median3(a, left, right);

            //begin partitioning
            int i = left, j = right - 1;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0) {}
                while (a[--j].compareTo(pivot) < 0) {}
                if (i < j) {
                    swapReference(a, i, j);
                } else {
                    break;
                }
                //restore pivot
                swapReference(a, i, right - 1);
                //sort small elements
                quickSort(a, left, i -1);
                //sort large elements
                quickSort(a, i + 1, right);
            }
        } else {// do an insertion on the subarray
            InsertionSort.insertionSort(a);
        }
    }


    /**
     * 三数中值分割法
     * return median of left, center, and right
     * order these and hide the pivot
     * @param a
     * @param left
     * @param right
     */
    private static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {
        int center = ( left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            swapReference(a, left, center);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swapReference(a, left, right);
        }
        if (a[right].compareTo(a[center]) < 0) {
            swapReference(a, center, right);
        }
        //place pivot at position right - 1
        swapReference(a, center, right - 1);

        return a[right - 1];
    }

}
