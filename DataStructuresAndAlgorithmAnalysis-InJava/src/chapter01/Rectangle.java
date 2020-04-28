package chapter01;

import java.util.Comparator;

/**
 * @author xiaoxiya
 * @date 2019/12/23 15:55
 * @describe 定义一个rectangle类，改类提供getLength和getWideth方法。利用图1-18中的findMax例程编写一种main方法，
 * 该方法创建一个rectangle数组并首先找出依面积最大的Rectangle对象，然后找出依周长最大的Rectangle对象。
 */
public class Rectangle {

    private int width;

    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String getName(){
        return "("+width+","+height+")";
    }

    /**
     * 获取面积
     * @return
     */
    public int getArea() {
        return width * height;
    }

    /**
     * 获取周长
     */
    public int getLength(){
        return (width+height)<<2;
    }

    /**
     * 图1-18中的findMax
     */
    public static <T> T findMax(T[] arr, Comparator<? super T> cmp) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (cmp.compare(arr[i], arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    /**
     * 长度比较器
     */
     static class LengthCompare implements Comparator<Rectangle> {
        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            if (o1.getLength() > o2.getLength()) {
                return 1;
            } else if (o1.getLength() == o2.getLength()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * 面积及比较器
     */
     static class AreaCompare implements Comparator<Rectangle> {
        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            if (o1.getArea() > o2.getArea()) {
                return 1;
            } else if (o1.getArea() == o2.getArea()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        Rectangle[] arr = new Rectangle[] {new Rectangle(5, 5) , new Rectangle(6, 4), new Rectangle(3, 8)};
        System.out.println(findMax(arr, new LengthCompare()).getName());
        System.out.println(findMax(arr, new AreaCompare()).getName());
    }

}
