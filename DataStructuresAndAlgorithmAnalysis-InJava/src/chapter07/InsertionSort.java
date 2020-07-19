package chapter07;

/**
 * @author xiaoxiya
 * @date 2020/7/19 16:52
 * @describe 插入排序（O（n^2））
 */
public class InsertionSort {

    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
        int j;
        for (int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j -1]) < 0; j--) {
                //寻找插入的位置
                a[j] = a[j - 1];
            }
            //插入数据
            a[j] = tmp;
        }
    }

}
