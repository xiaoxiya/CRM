package chapter03;

/**
 * @author xiaoxiya
 * @date 2020/3/26 20:21
 * @describe 使用单双链表来交换两个元素，只调整链
 */
public class Item032 {

    private class SinglyNode<T> {
        public T data;
        public SinglyNode<T> next;
    }

    /**
     * 通过单链表实现
     */
    public static void swapWithNext(SinglyNode  beforep) {
        SinglyNode p, afterp;
        p = beforep.next;
        afterp = p.next;

        p.next = afterp.next;
        beforep.next = afterp;
        afterp.next = p;
    }

    private class DoublyNode<T> {
        public T data;
        public DoublyNode<T> prev;
        public DoublyNode<T> next;
    }
    /**
     * 通过双向链表实现
     */
    public static void swapWithDoubleNext(DoublyNode p) {
        DoublyNode beforep, afterp;

        beforep = p.prev;
        afterp =p.next;

        p.next = afterp.next;
        beforep.next = afterp;
        afterp.next = p;
        p.next.prev = p;
        p.prev = afterp;
        afterp.prev =beforep;

    }

}
