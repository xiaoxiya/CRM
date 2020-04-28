package chapter01;

import java.util.Arrays;

/**
 * @author xiaoxiya
 * @date 2019/12/23 14:33
 * @describe 设计一个泛型类Collection，它存储Object对象的集合（在数组中）
 * 以及该集合的当前大小。提供public方法，isEmpty、makeEmpty、insert、remove、和isPresent
 * isPresent（x）当且仅当在该集合中存在（由equals定义）等于x的一个object时返回true
 */
public class MyCollection<T> {

    private Object[] elementData;

    private int size;

    public MyCollection(Object[] elementData, int size) {
        this.elementData = elementData;
        this.size = size;
    }

    public Object[] getElementData() {
        return elementData;
    }

    public void setElementData(Object[] elementData) {
        this.elementData = elementData;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空collection，元素个数为0
     */
    public void makeEmpty() {
        elementData = new Object[]{};
        size = 0;
    }

    /**
     * 插入元素
     * @param obj
     */
    public void insert(T obj) {
        Object[] temp = new Object[size + 1];
        System.arraycopy(elementData, 0, temp, 0, size);
        temp[size] = obj;
        elementData = temp;
        size++;
    }

    /**
     * 根据序号移除元素
     * @param index 序号
     */
    public void remove(int index) {
       Object[] temp = new Object[size - 1];
       for (int i = 0; i < size - 1; i++) {
           temp[i] = elementData[i];
           if (i >= index) {
               temp[i] = elementData[i+1];
           }
       }
       // System.arraycopy(elementData, 0, temp, 0, length-1);
       // System.arraycopy(elementData, index+1, temp, index, length-1-index);

        //public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length)
        //其中：src表示源数组，srcPos表示源数组要复制的起始位置，
        // desc表示目标数组，destPos目的地数据中的起始位置,length表示要复制的长度。

        elementData = temp;
       size--;
    }

    public boolean isPresent(T obj) {
        if (elementData == null || obj == null) {
            return false;
        }
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && obj == elementData[i]){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Object[] objects = new Object[]{"9"};
        MyCollection<String> collection = new MyCollection<>(objects, objects.length);
        collection.insert("1");
        collection.insert("2");
        collection.insert("3");
        System.out.println(Arrays.toString(collection.getElementData()));
        collection.remove(0);
        System.out.println(collection.size);
        System.out.println(Arrays.toString(collection.getElementData()));
        boolean flag = collection.isEmpty();
        System.out.println("isEmpty : " + flag);
        boolean flag1 = collection.isPresent("1");
        System.out.println("isPresent : " + flag1);
        collection.makeEmpty();
        boolean flag2 = collection.isEmpty();
        System.out.println("isEmpty: " + flag2);
    }
}
