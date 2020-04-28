package chapter03;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author xiaoxiya
 * @date 2020/1/5 20:48
 * @describe ArrayList类的实现
 */
public class MyArrayList<T> implements Iterable<T> {

    private static final int DEALULT_CAPACITY = 10;

    private int theSize;

    private T [] theItems;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEALULT_CAPACITY);
    }

    private int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public T get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    public T set(int idx, T newVal) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, T x) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;
        theSize++;
    }

    public T remove(int idx) {
        T removedItem = theItems[idx];
        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removedItem;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }
        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];

        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public void addAll(Iterable<? extends T> items) {
       Iterator<? extends T> iterItems = items.iterator();
       while (iterItems.hasNext()) {
          add(iterItems.next());
       }
    }

    @Override
    public Iterator<T> iterator() {
        return new  ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw  new NoSuchElementException();
            }
            return theItems[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        MyArrayList<Integer> list1 = new MyArrayList<Integer>();
        list1.addAll(list);
        Iterator l = list1.iterator();
        while (l.hasNext()) {
            System.out.print(l.next().toString() + ",");
        }
        System.out.println();
    }
}
