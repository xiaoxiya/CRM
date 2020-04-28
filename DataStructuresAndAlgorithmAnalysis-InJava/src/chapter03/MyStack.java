package chapter03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author xiaoxiya
 * @date 2020/1/14 19:33
 * @describe 栈的数组实现
 */
public class MyStack<T> implements Iterable<T> {


    /**
     * 初始大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 栈顶元素
     */
    private int top;


    /**
     * 存储元素
     */
    private T[] theStack ;

    public MyStack() {
        doClear();
    }

    private void doClear() {
        top = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * 元素个数
     */
    private int size() {
        return this.top;
    }

    public boolean isEmpty() {
        return this.top == 0;
    }

    public void clear() {
        doClear();
    }

    private void ensureCapacity(int newLength) {
        if (newLength < top) {
            return;
        }
        T[] oldStack = theStack;
        theStack = (T[]) new Object[newLength];

        for (int i = 0; i < size(); i++) {
            theStack[i] = oldStack[i];
        }
    }

    public void push(T x) {
        if (theStack.length == size()) {
            ensureCapacity(size() + 1);
        }
        theStack[top] = x;
        top++;
    }

    public  void pop() {
        T popItem = peek();
        top--;
    }

    /**
     * 获取栈顶元素
     * @return 栈顶元素
     */
    public T peek() {
        if (top == 0) return null;
        return theStack[top-1];
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {

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
            return theStack[current++];
        }

    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        //stack.push(2);
        //stack.push(3);
        //stack.push(4);
        //stack.push(5);
        //stack.push(6);
        //stack.push(7);
        //stack.push(8);
        //stack.push(9);
        //stack.push(10);
        //stack.push(11);
        Iterator l = stack.iterator();
        while (l.hasNext()) {
            System.out.print(l.next().toString() + ",");
        }
        System.out.println();

        stack.peek();

        Iterator l2 = stack.iterator();
        while (l2.hasNext()) {
            System.out.print(l2.next().toString() + ",");
        }
        System.out.println();

        stack.pop();
        if (stack.isEmpty()) {
            System.out.println("null");
        }
        stack.peek();

        Iterator l3 = stack.iterator();
        while (l3.hasNext()) {
            System.out.print(l3.next().toString() + ",");
        }


    }
}
