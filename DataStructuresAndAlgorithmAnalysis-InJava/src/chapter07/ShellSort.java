package chapter07;

/**
 * @author xiaoxiya
 * @date 2020/7/19 17:24
 * @describe 希尔排序（缩减增量排序）
 */
public class ShellSort {

    public static <T extends Comparable<? super T>> void shellSort(T[] a) {
        int j;
        //希尔增量排序
        for (int gap = a.length/2; gap>0; gap/=2) {
            //插入排序
            for (int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j-gap];
                }
                a[j] = tmp;
            }
        }
    }
}
