package com.xiaoxiya.streamapi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xiaoxiya
 * @date 2019/12/6 11:18
 * @describe
 */
public class Java8Stream {

    public static void main(String[] args) {
        //usingJava7();
        usingJava8();
    }

    void createStream() {
        //构建流的集中常见方法
        //1、Individual values
        Stream stream = Stream.of("a", "b", "c");
        //2、Arrays
        String[] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        //3、Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        //数值流的构造
        IntStream.of(new int[] {1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        //流转换为其他数据结构,一个 Stream 只可以使用一次，为了简洁而重复使用了数次
        //1、Array
        String[] strArray1 = (String[]) stream.toArray(String[]::new);
        //2、collection
        //List<String> list1 = (List<String>) stream.collect(Collectors.toList());
        //List<String> list2 = (List<String>) stream.collect(Collectors.toCollection(ArrayList::new));
        //Set set1 = (Set) stream.collect(Collectors.toSet());
        //Stack stack1 = (Stack) stream.collect(Collectors.toCollection(Stack::new));
        ////3、String
        //String str =   stream.collect(Collectors.joining()).toString();

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
    }

    static void usingJava7() {
        System.out.println("使用 Java7 ：");
        //计算空字符串
        List<String> strings = Arrays.asList("abc", "","bc", "efg", "abcd", "jkl");
        System.out.println("列表：" + strings);

        long count = getCountEmptuStringUsingJava7(strings);
        System.out.println("空字符数量为： " + count);

        count = getCountLength3UsingJava7(strings);
        System.out.println("字符串长度为3的数量为： " + count);

        //删除空字符串
        List<String> filtered = deleteEmptyStrignsUsingJava7(strings);
        System.out.println("筛选后的列表： " + filtered);

        //删除空字符串，并使用逗号把他们合并起来
        String mergedString = getMergedStringUsingJava7(strings, ", ");
        System.out.println("合并字符串： " + mergedString);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表： " + squaresList);

        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        System.out.println("列表： " + integers);
        System.out.println("列表重最大的数： " + getMax(integers));
        System.out.println("列表重最小的数： " + getMin(integers));
        System.out.println("所有数之和： " + getSum(integers));
        System.out.println("平均数： " + getAverage(integers));
        System.out.println("随机数： ");

        //输出10个随机数
        Random random = new Random();

        for (int i = 0; i < 10 ; i++) {
            System.out.println(random.nextInt());
        }
    }

    static void usingJava8() {
        System.out.println("使用 Java8 ：");
        //计算空字符串
        List<String> strings = Arrays.asList("abc", "","bc", "efg", "abcd", "jkl");

        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串数量为：" + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为3的数量为：" + count);

        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表：" + filtered);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串： "+ mergedString);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("平方数列表： " + squaresList);

        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        //并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为：" + count);
    }

    private static int getCountEmptuStringUsingJava7(List<String> strings) {
        int count = 0;
        for (String string : strings) {
            if (string.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings) {
        int count = 0;
        for (String string : strings) {
            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStrignsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<String>();
        for (String string : strings) {
            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squaresList = new ArrayList<Integer>();
        for (Integer number : numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());
            if (!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers) {
        int max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (number.intValue() > max) {
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers) {
        int min = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (number.intValue() < min) {
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List<Integer> numbers) {
        int sum = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers) {
        return getSum(numbers) / numbers.size();
    }
}
