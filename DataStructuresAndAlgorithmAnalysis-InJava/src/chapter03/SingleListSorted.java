package chapter03;
import java.util.*;

/**
 * @author xiaoxiya
 * @date 2020/4/14 23:09
 * @describe 单链表 保持排序
 */
public class SingleListSorted {
    private static class Node<Comparable> {
        public Comparable data;
        public Node<Comparable> next;
        public Node(Comparable data, Node<Comparable> h) {
            this.data = data;
            this.next = h;
        }
        public Node() {
            this(null, null);
        }
        public Node(Comparable data) {
            this(data, null);
        }
    }

    /**
     * 链表大小
     */
    private int theSize;

    private Node<Comparable> head;

    void init() {
        theSize = 0;
        head = new Node<Comparable>();
        head.next = null;
    }

    public int size() {
        return theSize;
    }

    public boolean contains(Comparable x) {
        Node<Comparable> p = head.next;
        while (p != null && p.data.compareTo(x) <= 0) {
            if (x.equals(p.data)) {
                return true;
            } else {
                p = p.next;
            }
        }
        return false;
    }
    public void print() {
        Node<Comparable> p = head.next;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public boolean add(Comparable x) {
        if (contains(x)) {
            return false;
        }
        Node<Comparable> q = head.next;
        Node<Comparable> pre = head;
        while ( q != null && q.data.compareTo(x) < 0) {
            pre = q;
            q = q.next;
        }
        Node<Comparable> p = new Node<Comparable>(x);
            pre.next = p;
            p.next = q;
            theSize++;
        return true;
    }

    public boolean remove(Comparable x) {
        if (!contains(x)) {
            return false;
        } else {
            Node<Comparable> p = head.next;
            Node<Comparable> q = head;
            while (!p.data.equals(x)) {
                q = p;
                p = p.next;
            }
            q.next = p.next;
            theSize--;
        }
        return true;
    }
}
