package org.example;

import java.util.*;

public class ArrayListTest2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> list1 = new ArrayList<String>();
        list1.add(sc.next());
        list1.add(sc.next());
        list1.add(sc.next());
        list1.add(sc.next());
        list1.add(sc.next());

        String str = "";
        for(int i = 0; i < list1.size(); i++) {
            if(str.length() < list1.get(i).length()) {
                str = list1.get(i);
            }
        }

        System.out.println(str);
    }
}
