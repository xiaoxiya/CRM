package com.xiaoxiya.CAS;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author xiaoxiya
 * @date 2021/1/25 20:38
 * @describe
 */
public class LockFreeStackByLinked<E> implements LockFreeStack<E>{



    class ListNode<E> {
        public E item;
        public ListNode next;
        public ListNode(E item) {
            this.item = item;
        }
        public ListNode(E item, ListNode<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    //栈顶指针
    AtomicReference<ListNode<E>> top = new AtomicReference<ListNode<E>>();

    @Override
    public boolean push(E e) {
        ListNode<E> node = new ListNode<E>(e);

        while (true) {
            //当前栈顶节点
            ListNode<E> currentTop = top.get();
            //新的栈顶节点
            node.next = currentTop;
            //CAS更新栈顶指针
            if (top.compareAndSet(currentTop, node)) {
                return true;
            }

        }
    }

    @Override
    public E pop() {
        while (true) {
            ListNode<E> currentTop = top.get();
            //空栈
            if (currentTop == null) {
                return null;
            } else {
                //出栈后新的栈顶元素
                ListNode<E> node = currentTop.next;
                if (top.compareAndSet(currentTop, node)) {
                    return currentTop.item;
                }
            }
        }
    }


}
