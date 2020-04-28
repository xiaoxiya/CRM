package chapter03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author xiaoxiya
 * @date 2020/3/22 22:42
 * @describe 循环双端队列 自动扩容 头部下标指向的是队列中第一位元素，尾部下标指向的是下一个尾部元素插入的位置
 */
public class MyDeque<T> implements Iterable<T> {
    /**
     * 存储元素 数组方向假设为向右
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

    public MyDeque() {
        this.size = 0;
        this.theArray = (T[]) new Object[MIN_INITIAL_CAPACITY];
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 队列容量不够时扩容为原来的两倍
     */
    private void doubleCapacity() {
        int len = theArray.length;
        int head = this.front;
        //number of elements to the right of head
        int item = len - head;
        int newCapacity = len << 1;
        T[] a = (T[]) new Object[newCapacity];
        //将"head -> 数组尾部"的元素 复制在新数组的前面
        //等效于  System.arraycopy(theArray, head, a, 0, item);
        for(int i=head, j=0; i<len; i++,j++){
            a[j] = this.theArray[i];
        }
        //将"0 -> head"的元素 复制在新数组的后面
        //等效于  System.arraycopy(theArray, 0, a, item, head);
        for(int i=0, j=item; i<head; i++,j++){
            a[j] = this.theArray[i];
        }
        //初始化front,rear下标
        front = 0;
        rear = len;
        //内部数组指向 新扩容的数组
        theArray = a;

    }
    /**
     * 取模运算
     */
    private int getMode(int index) {
        int len = theArray.length;
        //由于队列下标逻辑上是循环的
        //逻辑下标小于零时
        if (index < 0)
            //加上当前数组长度
            index += len;
        //逻辑下标超过数组长度时
        if (index > len)
            index -= len;
        return index;
    }

    /**
     * 头部元素入队时，头部下标向左移动一位
     * @param e
     */
    public void addFirst(T e) {
        this.front = getMode(this.front - 1);
        theArray[this.front] = e;
        size++;
        if (this.rear == this.front) {
            doubleCapacity();
        }
    }

    /**
     * 尾部元素入队时，尾部下标向右移动一位
     */
    public void addLast(T e) {
        theArray[rear] = e;
        this.rear = getMode(this.rear + 1);

        size++;
        if (this.rear == this.front) {
            doubleCapacity();
        }
    }

    /**
     * 头部元素出队时，头部下标向右移动一位
     */
    public T removeFrist() {
        T result = theArray[this.front];
        if (result == null) {
            return null;
        }
        theArray[this.front] = null;
        this.front = getMode(this.front + 1);
        size--;
        return result;
    }

    /**
     *尾部元素出队时，尾部下标向左移动一位
     *
     */
    public T removeLast() {
        int idx = getMode(this.rear - 1) ;
        T result = theArray[idx];
        if (result == null) {
            return null;
        }
        theArray[idx] = null;
        this.rear = idx;
        size--;
        return result;
    }

    /**
     * 获取头部元素
     */
    public T getFrist() {
        return theArray[front];
    }

    /**
     * 获取尾部元素
     */
    public T getLast(){
        return theArray[getMode(rear -1)];
    }

    /**
     * 元素个数
     */
    private int size() {
        return this.size;
    }

    public boolean isEmpty() {
        //当且仅当 头尾下标相等时 队列为空
        return (front == rear);
    }

    /**
     * 删除特定位置元素
     * @param idx
     * @return true-删除元素靠近尾部
     *  false -删除元素靠近头部
     */
    private boolean delete(int idx) {
        Object[] elements = theArray;
        int head = front;
        int tail = rear;
        //当前下标 之前的元素个数
        int beforeCount  = getMode(idx - head);
        //当前下标 之后的元素个数
        int afterCount  = getMode(tail - idx);
        //将头部元素和被删除元素下标之间的元素右移
        if (beforeCount < afterCount) {
            // 头指针对应的数组下标  =< 被删除元素的数组下标 < 尾指针对应的数组下标
            if (head <= idx) {
                //将头部元素和被删除元素之间元素整体右移
                System.arraycopy(elements, head, theArray,head + 1, beforeCount);
            } else {
                //循环队列情况，存在溢出取余的情况，要复制两次
                //将数组从"0下标处"的元素整体向右平移一位，移动的元素个数为"从0到当前下标之间的元素个数"
                System.arraycopy(elements, 0, theArray, 1, idx);
                //将数组最尾部的数据设置到头部，防止被覆盖
                theArray[0] = (T) elements[elements.length - 1];
                //将数组尾部的数据整体向右平移一位
                System.arraycopy(elements, head, theArray, head +1, elements.length-1 - head);
            }
            theArray[head] = null;
            //头部下标 向右移动一位
            front = getMode(head + 1);
            size--;
            return false;
        } else {//将被删除元素下标和尾部元素之间的元素左移
            if (idx < tail) {
                System.arraycopy(elements, idx + 1, theArray, idx, afterCount);
            } else {
                //循环队列情况，存在溢出取余的情况，要复制两次
                //将数组从"当前下标处"的元素整体向左平移一位，移动的元素个数为"从当前下标到数组末尾的元素个数-1 ps：因为要去除掉被删除的元素"
                System.arraycopy(elements, idx + 1, theArray, idx, elements.length - idx-1);
                //将数组头部的元素设置到末尾
                theArray[elements.length - 1] = (T) elements[0];
                //将数组头部的数据整体向左平移一位，移动的元素个数为"从0到尾部下标之间的元素个数"
                System.arraycopy(elements, 1, theArray, 0, tail);
            }
            rear = getMode(tail - 1);
            size--;
            return true;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        /**
         * 当前迭代下标 ，从头部开始
         */
        private int current = front;
        /**
         * 结束下标，到尾部结束
         */
        private int last = rear;
        /**
         * 上一次返回的位置下标
         */
        private int lastRet;

        @Override
        public boolean hasNext() {
            return current != last;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw  new NoSuchElementException();
            }
            T result = theArray[current];
            //最近一次返回元素下标 = 当前迭代下标
            lastRet = current;
            //当前迭代下标
            current = getMode(current + 1);
            return result;
        }

        public void remove() {
            if (lastRet == -1) {
                System.out.println("迭代器状态异常: 可能在一次迭代中进行了多次remove操作");
                return;
            }
            //删除当前迭代下标的元素
            boolean isDelete = delete(current);
            if (isDelete) {
                this.current = getMode(this.current - 1);
            }
            //防止用户一次迭代（next调用）中多次remove方法，设置lastRet为-1
            lastRet = -1;
        }
    }

    public static void main(String[] args) {
        MyDeque<Integer> deque = new MyDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        //deque.removeFrist();
        //deque.removeLast();
        Iterator l = deque.iterator();
        l.next();
        l.remove();
        while (l.hasNext()) {
            System.out.print(l.next().toString() + ",");
        }
        System.out.println();
        System.out.println(deque.getFrist());
        System.out.println(deque.getLast());
    }

}
