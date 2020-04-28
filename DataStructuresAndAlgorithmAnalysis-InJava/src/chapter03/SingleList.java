package chapter03;

import java.util.*;

/**
 * @author xiaoxiya
 * @date 2020/4/14 20:29
 * @describe 单链表只含有头节点没有尾节点
 *
 */
public class SingleList {
    private static class Node<Object> {
        public Object data;
        public Node<Object> next;
        public Node(Object data, Node<Object> h) {
            this.data = data;
            this.next = h;
        }
        public Node() {
            this(null, null);
        }
        public Node(Object data) {
            this(data, null);
        }
    }

    /**
     * 链表大小
     */
    private int theSize;

    private Node<Object> head;

    void init() {
        theSize = 0;
        head = new Node<Object>();
        head.next = null;
    }

    public SingleList() {
        init();
    }

    public int size() {
        return theSize;
    }

    public void printSingleList() {
        Node<Object> p = head.next;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public boolean contains(Object x) {
        Node<Object> p = head.next;
        while (p != null ) {
            if (p.data.equals(x)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    public boolean add(Object x) {
        if (contains(x)) {
            return false;
        }
        Node<Object> q = new Node<Object>(x);
        q.next = head.next;
        head.next = q;
        theSize++;
        return true;
    }

    public boolean remove(Object x) {
        if (!contains(x)) {
            return false;
        } else {
            Node<Object> p = head.next;
            Node<Object> q = head;
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
