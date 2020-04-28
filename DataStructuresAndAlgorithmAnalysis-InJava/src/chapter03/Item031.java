package chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaoxiya
 * @date 2020/1/13 23:15
 * @describe
 */
public class Item031 {

    public static <T> void printLots(List<T> L, List<Integer> P) {
        Iterator<T> iterL = L.iterator();
        Iterator<Integer> iterP = P.iterator();

        T itemL = null;
        Integer itemP = 0;
        int start = 0;

        while (iterL.hasNext() && iterP.hasNext()) {
            itemP = iterP.next();
            System.out.println("Looking for position " + itemP);
            while (start < itemP && iterL.hasNext()) {
                start++;
                itemL = iterL.next();
            }
            System.out.println(itemL);
        }
    }

    public static void main(String[] args) {
        List<String> l = new ArrayList<>(Arrays.asList("one", "two", "three", "four", "five", "six"));
        List<Integer> p = new ArrayList<>(Arrays.asList(1,  3, 4,  6));
        printLots(l, p);
    }
}
