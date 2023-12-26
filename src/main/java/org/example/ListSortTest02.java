package org.example;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
    public static void main(String[] args) {
        ArrayList<Member> memList = new ArrayList<Member>();

        memList.add(new Member(1,"홍길동", "010-1111-1111"));
        memList.add(new Member(5,"이순신", "010-2222-1111"));
        memList.add(new Member(9,"성춘향", "010-3333-1111"));
        memList.add(new Member(3,"강감찬", "010-4444-1111"));
        memList.add(new Member(6,"일지매", "010-5555-1111"));
        memList.add(new Member(2,"변학도", "010-6666-1111"));

        for(Member mem : memList) {
            System.out.println(mem);
        }
        System.out.println("내부 정렬 후");
        Collections.sort(memList);
        for(Member mem : memList) {
            System.out.println(mem);
        }

        System.out.println("외부 정렬 후");
        Collections.sort(memList, new SortNumDesc());
        for(Member mem : memList) {
            System.out.println(mem);
        }

        //member의 회원번호를 내림차순으로 정렬하는 외부 정렬 클래스를 작성하고
        //이 외부 정렬 기준을 적용해서 출력하시오
    }


}
//회원이름을 기준으로 오름차순 되도록 내부 정렬 기준을 추가해보기
//Comparable인터페이스를 구현해야 한다.
class Member implements Comparable<Member> {
    private int num;        //회원번호
    private String name;    //회원이름
    private String tel;     //전화번호

    public Member(int num, String name, String tel) {
        this.num = num;
        this.name = name;
        this.tel = tel;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Member{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    //회원 이름의 오름차순 정렬 기준 만들기
    @Override
    public int compareTo(Member o) {
        return this.getName().compareTo(o.getName());
    }
}
class SortNumDesc implements Comparator<Member> {

    @Override
    public int compare(Member o1, Member o2) {
        //내림차순으로 구하기
        if(o1.getNum() > o2.getNum()) {
            return -1;
        }
        else if(o1.getNum() < o2.getNum()) {
            return 1;
        }
        else return 0;
    }
}
