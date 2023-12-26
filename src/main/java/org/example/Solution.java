package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] numbers = new int[]{3,30,34,5,9};

        System.out.println(so.solution(numbers));
    }

    public String solution(int[] numbers) {
        String[] strNum = new String[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            strNum[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strNum, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1+o2);
            }
        });

        if(strNum[0].equals("0")) return "0";
        
        StringBuilder answer = new StringBuilder();

        for(int i  = 0; i < strNum.length; i++) {
            answer.append(strNum[i]);
        }
                
        return answer.toString();
    }
}
