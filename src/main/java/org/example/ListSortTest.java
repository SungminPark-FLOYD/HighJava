package org.example;


import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
* 정렬과 관련된 interface는 Comparable, Comparator 이렇게 두 가지가 있다
* - Comparable은 Collection에 추가 되는 데이터 자체에 정렬 기준을 넣고 싶을 때
* 구현하는 인터페이스이다 (내부 정렬 기준)
* -  Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때
* 구현하는 인터페이스이다 (외부정렬기준)
*
*   - Comparable에서는 compareTo() 메서드를 재정의 해야 하고,
*      Comparator에서는 compare()메서드를 재정의 해야 한다.
*
*   - String 클래스, Wrapper클래스, Date클래스, File클래스에는 내부 정렬 기준이 이미 구현 되어 있다.
* (내부 정렬 기준은 오름차순으로 처리되도록 구현되어 있다.)
* */
public class ListSortTest{

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("일지매");
        list.add("홍길동");
        list.add("성춘향");
        list.add("변학도");
        list.add("이순신");

        //정렬
        Collections.sort(list);
        System.out.println(list);

        Collections.shuffle(list); //자로 섞기
        System.out.println(list);

        //외부 정렬 기준을 적용해서 정렬하기
        //형식 ) Collections.sort(리스트, 외부정렬기준 객체)
        Collections.sort(list, new Desc());
        System.out.println(list);
    }
}

//외부 정렬 기준 클래스 만들기 => 정렬 방식을 결정해 주는 class
//Comparator인터페이스를 구현해서 작성해야 한다.
class Desc implements Comparator<String> {

    //compare()매서드의 반환값의 의미
    //0은 두 값이 같다.
    //양수는 앞, 뒤의 순서를 바꾼다.
    //음수는 앞,뒤의 순서를 바꾸지 않는다.

    //오름차순일 경우 ==> 앞의 값이 크면 양수, 같으면 0
    //                  뒤의 값이 크면 음수를 반환하도록 구현하면 된다
    //String의 내부 정렬 기준은 오름차순으로 정렬하도록 되어있다 => Comparable을 implements했다

    @Override
    public int compare(String o1, String o2) {
        //내림차순으로 구하기
        if(o1.compareTo(o2) > 0) {
            return -1;
        }
        else if(o1.compareTo(o2) < 0) {
            return 1;
        }
        else return 0;
    }
}
