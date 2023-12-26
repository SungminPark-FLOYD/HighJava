package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListTest02{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        list.add(sc.next());
        list.add(sc.next());
        list.add(sc.next());
        list.add(sc.next());
        list.add(sc.next());

        for(String str : list) {
            if(str.charAt(0) == '김') {
                System.out.println("charAt : " + str);
            }
            if(str.startsWith("김")) {
                System.out.println("startsWith : " + str);
            }
            if(str.indexOf("김") == 0) {
                System.out.println("indexOf : " + str);
            }
        }

    }
}
