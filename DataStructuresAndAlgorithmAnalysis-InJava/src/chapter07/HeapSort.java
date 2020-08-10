package chapter07;

import java.util.Arrays;

/**
 * @author xiaoxiya
 * @date 2020/8/9 18:05
 * @describe 堆排序
 */
public class HeapSort {
    public static <T extends  Comparable<? super T>> void heapsort(T[] a) {
        //构建堆结构
        for (int i = a.length / 2 - 1;i >= 0; i--) {
            perDown(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0 ; i--) {
            //将最大值放入数组最后面,前面的保持堆结构
            //数组最后面用于存放刚删除的元素
            swapReference(a, 0, i);
            perDown(a, 0, i);
        }
    }

    private static <T extends  Comparable<? super T>>  void swapReference(T[] a, int i, int n) {
        T maxItem = a[i];
        a[i] = a[n];
        a[n] = maxItem;
    }

    private static <T extends  Comparable<? super T>>  void perDown(T[] a, int i, int n) {
        int child;
        T tmp;
        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n-1 && a[child].compareTo(a[child + 1]) < 0) {
                child++;
            }
            if (tmp.compareTo(a[child]) < 0) {
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = tmp;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    public static void main(String[] args) {
        Integer[] heap = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        heapsort(heap);
        Arrays.stream(heap).forEach(System.out::println);
    }

}
