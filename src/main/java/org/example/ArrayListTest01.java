package org.example;

import java.util.ArrayList;

public class ArrayListTest01 {
    public static void main(String[] args) {
        //ArrayList의 기본적인 사용법은 Vector와 같다

        ArrayList<Object> list1 = new ArrayList<Object>();

        //add()메서드를 이용해서 데이터를 추가한다.
        list1.add("aaa");
        list1.add("bbb");
        list1.add(123);
        list1.add(true);

        //get(index) 로 값 가져오기
        //add(index, value) 값 넣기
        //set(index,value)로 값 수정
        //remove(index / value)로 데이터 삭제
        //contains(비교 객체) : 리스트에 저장된 데이터 중에서 비교객체가 있으면 true / 없으면 false

        //indexOf(비교 객체)
        //lastIndexOf(비교객체) => List에 비교객체가 있으면 비교객체가 저장된 index값을 반환하고 없으면 -1을 반환한다.
        //indexOf()매서드는 검색 방향이 앞에서 뒤족으로 검색하고
        //lastIndexOf()매서드는 검색 방향이 뒤에서 앞쪽으로 검색한다
        //List에 비교객체가 많으면 첫번째로 찾아진 데이터의 위치값이 반환된다.
        //-toArray() => List안의 데이터를 배열로 변환해서 반환한다
        //          => 기본적으로 Object형으로 반환한다.

    }
}
