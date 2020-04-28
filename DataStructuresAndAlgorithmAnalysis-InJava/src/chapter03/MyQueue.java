package chapter03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author xiaoxiya
 * @date 2020/1/15 19:42
 * @describe 队列数组实现 循环队列 固定大小 只存储n-1个元素
 */
public class MyQueue<T> implements Iterable<T> {
    /**
     * 存储元素
     */
    private T [] theArray;
    /**
     * 队头元素(出队)
     */
    private int front;

    /**
     * 队尾元素(入队)
     */
    private int rear;
    /**
     * 队列中元素个数
     */
    private int size;

    private static final int MIN_INITIAL_CAPACITY = 8;

    public MyQueue() {
        this.size = 0;
        this.theArray = (T[]) new Object[MIN_INITIAL_CAPACITY];
        this.front = theArray.length-1;
        this.rear = theArray.length-1;
    }

    public MyQueue(int len) {
        this.size = 0;
        this.theArray = (T[]) new Object[len];
        this.front = theArray.length-1;
        this.rear = theArray.length-1;
    }

    /**
     * 入队列
     * @param e
     */
    public void add(T e) {
        int idx = this.rear;
        if (this.size == theArray.length) {
            System.out.println("队列已满");
            return;
        }
        this.rear = (idx + 1) % theArray.length;
        theArray[rear] = e;
        size++;
    }

    /**
     * 出队列
     */
    public T remove() {
        int idx = this.front;
        T result = theArray[idx];
        if (result == null) {
            return null;
        }
        this.front = (idx + 1) % theArray.length;
        theArray[front] = null;
        size--;
        return result;
    }

    /**
     * 元素个数
     */
    private int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {

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
            return theArray[current++];
        }

    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.remove();
        queue.add(4);
        Iterator l = queue.iterator();
        while (l.hasNext()) {
            System.out.print(l.next().toString() + ",");
        }
        System.out.println();
    }
}
