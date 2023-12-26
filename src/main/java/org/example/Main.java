package org.example;

import sun.awt.windows.WPrinterJob;

import java.time.Clock;
import java.util.Collections;
import java.util.Objects;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        //Vector는 자바의 초기부터 지원하는 객체로 동기화 처리가 되어있다.
        //객체 생성
        Vector<Object> v1 = new Vector<Object>();

        System.out.println("처음 크기 : " + v1.size());

        //데이터 추가 하기 : add(추가할 데이터 )
        // 반환값 : 성공 true 실패 false
        v1.add("area");
        v1.add(new Integer(111));
        v1.add((Integer.valueOf(200)));
        v1.add(123);    //auto boxing => auto unboxing
        v1.add('a');
        v1.add(true);

        boolean a = v1.add(3.14);

        System.out.println("반환값 : " + a);
        System.out.println("현재 크기 : " + v1.size());

        System.out.println(v1);

        //데이터 추가하기 : add(index,데이터)
        //  => 'index'번째에 '데이터'를 끼워 넣는다.
        v1.add(1, "asd");
        System.out.println(v1);

        //데이터 꺼내오기 : get(index)
        //  => 'index'번째의 데이터를 꺼내서 반환한다.
        System.out.println("0번째 데이터 " + v1.get(0));

        //데이터 수정하기 : set(index , 새로운 데이터)
        //  =>'index'번째의 데이터를 '새로운 데이터'로 변경한다.
        v1.set(0, "home");
        System.out.println(v1.get(0));

        //데이터 삭제하기 : remove
        // => 삭제할 데이터를 찾아서 삭제한다 (삭제할 데이터가 여러개 이면 이중에 제일 첫번재로 검색된 자료가 삭제된다.
        // 반환값 : 삭제 성공(true), 삭제 실패(false)
        //삭제할 데이터가 정수형이거나 char형일 경우 반드시 객체로 변환후 사용
        v1.remove("asd");
        System.out.println(v1);

//        v1.remove(new Integer(123));
        v1.remove(Integer.valueOf(123));
        v1.remove(Character.valueOf('a'));
        System.out.println(v1);

        v1.clear();

        /*재네릭 타입 -> 클래스 내부에서 사용할 데이터의 타입을 객체를 생성할 때
        외부에서 지정해 주는 기법으로 객체를 선언할때
        * <> 괄호안에 그 객체의 내부에서 사용할 데이터의 타입을 지정해 주는 것을 말한다.

        - 이런 식으로 선언하게 되면 그 지정한 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없다
        - 이 때 제네릭으로 선언 될 수 있는 데이터 타입은 '클래스 형'으로 지정해야 한다.
            그래서  int는 Integer, boolean은 Boolean, char는 Character등으로 대체해서 사용해야 한다.
        - 제네릭 타입으로 선언하게 되면 데이터를 꺼내 올 때 별도의 형변환이 필요 없다.*/


        Vector<Integer> v2 = new Vector<Integer>();
        Vector<String> v3 = new Vector<String>();
        v3.add("AAA");
        v3.add("BBB");
        v3.add("CCC");
        v3.add("DDD");
        v3.add("EEE");

        Vector<String> v4 = new Vector<String>();
        v4.add("BBB");
        v4.add("DDD");

        System.out.println(v3);
        System.out.println(v4);

        for(String v : v3) {
            for(String q : v4) {
                if(q.contains(v)) System.out.println(q);
            }
        }

    }
}
