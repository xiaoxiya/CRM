package chapter07;


import java.util.Iterator;

/**
 * @author xiaoxiya
 * @date 2021/2/3 20:17
 * @describe 归并排序
 */
public class MergeSort {
    /**
     * mergeSort algorithm
     * @param a an array of Comparable items
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] tempArray = (T[]) new Comparable[a.length];
        mergeSort(a, tempArray, 0, a.length -1);
    }

    /**
     * Internal method that makes recursive calls
     * @param a an array of comparable items
     * @param tempArray an array to place the merged result
     * @param left the left-most index of subarray
     * @param right the right-most index of the subarray
     * @param <T>
     */
    private static <T extends Comparable<? super T>>  void mergeSort(T[] a, T[] tempArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tempArray, left, center);
            mergeSort(a, tempArray, center+1, right);
            merge(a, tempArray, left, center + 1, right);
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray
     * @param a an array of Comparable items
     * @param tempArray an array of place the merged result
     * @param leftPos the left-most index of subarray
     * @param rightPos the index of start of the second half
     * @param rightEnd the right-most index of the subarray
     * @param <T>
     */
    private static <T extends Comparable<? super T>>  void merge(T[] a, T[] tempArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tempArray[tempPos++] = a[leftPos++];
            } else {
                tempArray[tempPos++] = a[rightPos++];
            }
        }
        //右边数组已经全部结束，复制左边的数组到最后面
        while (leftPos <= leftEnd) {
            tempArray[tempPos++] = a[leftPos++];
        }
        //左边数组已经全部结束，复制右边的数组到最后面
        while (rightPos <= rightEnd) {
            tempArray[tempPos++] = a[rightPos++];
        }

        //copy tempArray back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tempArray[rightEnd];
        }
    }
}
