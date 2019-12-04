package com.xiaoxiya.sorts;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaoxiya
 * @date 2019/12/4 21:37
 * @describe 排序公用工具类
 */
public class SortUtils {

    static <T> boolean swap(T[] array, int x, int y) {
        T swap = array[x];
        array[x] = array[y];
        array[y] = swap;
        return true;
    }

    static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    static void print(List<?> toPrint) {
        toPrint.stream()
                .map(Object::toString)
                .map(str -> str + " ")
                .forEach(System.out::print);
        System.out.println();
    }

    static void print(Object[] toPrint) {
        System.out.println(Arrays.toString(toPrint));
    }

    static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
        while (left <= right) {
            swap(array, left++, right--);
        }
    }

}
