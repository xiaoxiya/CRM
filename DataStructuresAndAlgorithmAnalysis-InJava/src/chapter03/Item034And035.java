package chapter03;

import java.util.List;
import java.util.ListIterator;

/**
 * @author xiaoxiya
 * @date 2020/3/27 9:10
 * @describe 给定两个已经排序的表l1和来l2，实现l1 ∪ l2 l1 ∩ l2 只能用基本的表操作
 */
public class Item034And035 {
    /**
     * l1 ∩ l2
     * @param L1
     * @param L2
     * @param intersect
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void intersection(List<T> L1, List<T> L2, List<T> intersect) {
        ListIterator<T> listL1 = L1.listIterator();
        ListIterator<T> listL2 = L2.listIterator();
        T itemL1 = null, itemL2 = null;
        //获取每个列表中的第一项
        if (listL1.hasNext() && listL2.hasNext()) {
            itemL1 = listL1.next();
            itemL2 = listL2.next();
        }
        while (itemL1 != null && itemL2 != null) {
            int compareResult = itemL1.compareTo(itemL2);
            //l1和l2相同
            if (compareResult == 0) {
                intersect.add(itemL1);
                itemL1 = listL1.hasNext() ? listL1.next() : null;
                itemL2 = listL2.hasNext() ? listL2.next() : null;
            } else if (compareResult < 0) { //l1小于l2
                itemL1 = listL1.hasNext() ? listL1.next() : null;
            } else {
                itemL2 = listL2.hasNext() ? listL2.next() : null;
            }
        }
    }

    /**
     * l1 ∪ l2
     * @param L1
     * @param L2
     * @param result
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void union(List<T> L1, List<T> L2, List<T> result) {
        ListIterator<T> listL1 = L1.listIterator();
        ListIterator<T> listL2 = L2.listIterator();
        T itemL1 = null, itemL2 = null;
        //获取每个列表中的第一项
        if (listL1.hasNext() && listL2.hasNext()) {
            itemL1 = listL1.next();
            itemL2 = listL2.next();
        }
        while (itemL1 != null && itemL2 != null) {
            int compareResult = itemL1.compareTo(itemL2);
            //l1和l2相同
            if (compareResult == 0) {
                result.add(itemL1);
                itemL1 = listL1.hasNext() ? listL1.next() : null;
                itemL2 = listL2.hasNext() ? listL2.next() : null;
            } else if (compareResult < 0) { //l1小于l2
                result.add(itemL2);
                itemL1 = listL1.hasNext() ? listL1.next() : null;
            } else {
                result.add(itemL1);
                itemL2 = listL2.hasNext() ? listL2.next() : null;
            }
        }
    }


}
