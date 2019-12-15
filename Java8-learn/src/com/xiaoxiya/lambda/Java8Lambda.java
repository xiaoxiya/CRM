package com.xiaoxiya.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaoxiya
 * @date 2019/12/5 23:21
 * @describe java8 Lambda的使用
 */
public class Java8Lambda {
    public static void main(String[] args) {
        //beforeJava8();
        usingJava8();
    }

    static void beforeJava8() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Object-c");
        list.add("swift");
        list.add("Python");
        list.add("Java");
        //java7 排序，匿名内部类的使用
        Collections.sort(list ,new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        //java5新特性foreach循环
        for (String name : list) {
            System.out.println(name);
        }
    }
    static void usingJava8() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Object-c");
        list.add("swift");
        list.add("Python");
        list.add("Java");
        //写法1
        list.sort((String o1, String o2) ->{
            return o1.compareTo(o2);
        });
        list.forEach(System.out::println);
        //写法2
        list.sort((s1, s2) -> s1.compareTo(s2));
        list.forEach(System.out::println);
        //写法3
        list.sort(String::compareTo);
        list.forEach(System.out::println);
    }
}
