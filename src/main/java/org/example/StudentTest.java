package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
        이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서
        초기화 처리를 한다. (총점은 3과목 점수의 합계로 초기화 한다.)

        이 Student객체는 List에 저장하여 관리한다.

        List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
        총점의 내림차순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부 정렬 기준 클래스를
        작성하여 정렬된 결과를 출력하시오.

        (단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.)
* */
public class StudentTest {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();

        list.add(new Student(1, "홍길동", 90, 90, 90));
        list.add(new Student(4, "김철수", 80, 80, 80));
        list.add(new Student(2, "리성윤", 60, 60, 60));
        list.add(new Student(3, "김성훈", 60, 80, 60));
        list.add(new Student(5, "김이훈", 60, 60, 60));
        list.add(new Student(6, "정철훈", 100, 60, 60));


        Collections.sort(list, new totalStu());

        for(Student stu : list) {   //기준 데이터를 구하기 위한 반복문
            int rank = 1;
            for(Student stu1 : list) {
                if (stu.getTotal() < stu1.getTotal()) {
                    rank ++;
                }
            }
            stu.setRank(rank);
        }

//      내부 정렬
        System.out.println("내부정렬");
        Collections.sort(list);
        for(Student st : list) {
            System.out.println(st);
        }

        //외부정렬
        System.out.println("외부 정렬");
        Collections.sort(list, new DescStu());
        for(Student st : list) {
            System.out.println(st);
        }
    }
}

class Student implements Comparable<Student> {
    int num;
    String name;
    int kor;
    int eng;
    int math;
    int total;
    BigDecimal avg;
    int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Student(int num, String name, int kor, int eng, int math) {
        this.num = num;
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.total = kor + eng + math;
        this.avg = BigDecimal.valueOf((kor + eng + math)/3.0);


    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
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

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return "Student{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", kor=" + kor +
                ", eng=" + eng +
                ", math=" + math +
                ", total=" + total +
                ", rank=" + rank +
                '}';
    }


    @Override
    public int compareTo(Student o1) {
        if(this.getNum() < o1.getNum()) return -1;
        else if(this.getNum() > o1.getNum()) return 1;
        else return 0;
    }

}

class  totalStu implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getTotal() < o2.getTotal()) return 1;
        else if(o1.getTotal() > o2.getTotal()) return -1;
        else return 0;
    }
}

class DescStu implements Comparator<Student> {
    CompareTotal c = new CompareTotal();
    @Override
    public int compare(Student o1, Student o2) {
        if(o2.getTotal() < o1.getTotal()) return -1;
        else if(o2.getTotal() > o1.getTotal()) return 1;
        else return c.compare(o1, o2);
        //Wrapper클래스 이용하기
        //내림차순이면 -1곱하기
        //return new Integer(o1.getNum()).compareTo(o2.getNum()) * -1;

        //Wrapper클래스 이용하기
        //return Integer.compare(o1.getNum(), o2.getNum()) * -1;
    }

}

class CompareTotal implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
