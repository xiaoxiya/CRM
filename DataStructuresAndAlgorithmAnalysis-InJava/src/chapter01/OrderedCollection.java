package chapter01;


import java.util.Arrays;

/**
 * @author xiaoxiya
 * @date 2019/12/23 15:51
 * @describe 设计一个泛型类orderedCollection,他存储comparable的对象的集合（在数组中）
 * 以及改集合的当前大小。提供public方法isEmpty，makeEmpty，insert，remove，findMin和findMax。
 * findMin和findMax分别返回该集合中最小的和最大的Comparable对象的引用（集合为空，则返回null）
 */
public class OrderedCollection<T> {

    private Comparable[] elementData;

    private int size;

    public Comparable[] getElementData() {
        return elementData;
    }

    public void setElementData(Comparable[] elementData) {
        this.elementData = elementData;
    }

    public OrderedCollection(Comparable[] elementData, int size) {
        this.elementData = elementData;
        this.size = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空collection，元素个数为0
     */
    public void makeEmpty() {
        elementData = new Comparable[]{};
        size = 0;
    }

    public void insert(Comparable obj) {
        Comparable[] temp = new Comparable[size + 1];
        System.arraycopy(elementData, 0, temp, 0, size);
        temp[size] = obj;
        elementData = temp;
        size++;
    }

    public void remove(int index) {
        Comparable[] temp = new Comparable[size - 1];
         System.arraycopy(elementData, 0, temp, 0, size-1);
         System.arraycopy(elementData, index+1, temp, index, size-1-index);
        elementData = temp;
        size--;
    }

    public Comparable<T> findMin() {
        if (elementData == null || size == 0) {
            return null;
        }
        int min = 0;
        for (int i = 1; i < size; i++) {
            if (elementData[i].compareTo(elementData[min]) < 0) {
                min = i;
            }
        }
        return elementData[min];
    }

    public Comparable<T> findMax() {
        if (elementData == null || size == 0) {
            return  null;
        }
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (elementData[i].compareTo(elementData[max]) > 0) {
                max = i;
            }
        }
        return elementData[max];
    }

    public static void main(String[] args) {
        Comparable[] comparables = new Comparable[]{"9"};
        OrderedCollection<String> collection = new OrderedCollection<>(comparables, comparables.length);
        collection.insert("1");
        collection.insert("2");
        collection.insert("3");
        collection.insert("4");
        System.out.println(Arrays.toString(collection.getElementData()));
        collection.remove(0);
        System.out.println(Arrays.toString(collection.getElementData()));
        collection.remove(2);
        System.out.println(Arrays.toString(collection.getElementData()));
        boolean flag = collection.isEmpty();
        System.out.println("isEmpty： "+flag);
        System.out.println("min： "+collection.findMin());
        System.out.println("max： "+collection.findMax());
        collection.makeEmpty();
        boolean flag2 = collection.isEmpty();
        System.out.println("isEmpty： "+flag2);
    }
}
