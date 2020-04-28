package chapter03;

import java.util.*;

/**
 * @author xiaoxiya
 * @date 2020/1/6 21:04
 * @describe 双向链表得实现
 */
public class MyLinkedList<T> implements Iterable<T> {

    /**
     * 链表大小
     */
    private int theSize;
    /**
     * 自从构造依赖对链表所做改变的次数
     */
    private int modCount = 0;

    private Node<T> beginMarker;

    private Node<T> endMarker;

    private static class Node<T>{
        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T d,Node<T> p, Node<T> n) {
            this.data = d;
            this.prev = p;
            this.next = n;
        }
    }

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void doClear() {
        beginMarker = new Node<T>(null, null, null);
        endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, T x) {
        addBefore(getNode(idx, 0, size()) ,x);
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public T set(int idx, T newVal) {
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    private void addBefore(Node<T> p, T x) {
        Node<T> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private T remove(Node<T> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    public boolean contains(T x) {
        Node<T> p = beginMarker.next;
        while (p != endMarker && !(p.data.equals(x))) {
            p = p.next;
        }
        return p != endMarker;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    private Node<T> getNode(int idx, int lower, int upper) {
        Node<T> p;
        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException();
        }
        if (idx < size() / 2){
            p = beginMarker;
            for (int i =0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    public void removeAll(Iterable<? extends T> items) {
        T item, element;
        Iterator<? extends T> iterItems = items.iterator();
        while (iterItems.hasNext()) {
            item = iterItems.next();
            Iterator<? extends T> iterList = iterator();
            while (iterList.hasNext()) {
                element = iterList.next();
                if (element.equals(item)) {
                    iterList.remove();
                }
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T>  current = beginMarker.next;
        /**
         * 迭代期间集合被修改的情况
         * 将迭代器被构造时的链表的modCount存储
         */
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString() + ",");
        }
        System.out.println();
        System.out.println("****************");
        List<Integer> list1 = Arrays.asList(1,2,3,4);
        list.removeAll(list1);
        Iterator<Integer> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next().toString() + "*");
        }
        System.out.println(",");
    }

}
