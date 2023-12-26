package HashCode;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setNum(1);
        p1.setName("홍길동");

        Person p2 = new Person();
        p2.setNum(1);
        p2.setName("홍길동");

        Person p3 = p1;

        System.out.println(p1.equals(p2));
        System.out.println(p1.equals(p3));

        HashSet<Person> hs = new HashSet<Person>();

        hs.add(p1);
        hs.add(p3);

        System.out.println(hs);


    }
}

/*equals메서드 : 두 객체의 내용이 같은지를 비교하는 메서드
* hashCode메서드 : 두 객체가 같은 객체인지 비교하는데 사용되는 메서드
*
* HashMap, Hashtable, HashSet과 같이 Hash로 시작하는 컬랙션 객체들은 객체의 의미상 같은지 비교를 위해
* hashCode메서드를  호출하여 비교한다
* 그래서 객체가 같은지 여부를 결정하려면 equals메서드와 hashCode메서드를 같이 재정의 해야 한다.
*
* hashCode메서드에서 사용하는 해싱 알고리즘은 서로 다른 객체들에 대해 같은 hashcode 값을
* 만들어 낼 수 있다.*/

class Person {
    private int num;
    private String name;

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

//    @Override
//    public boolean equals(Object obj) {
//        //주소값 검사
//        if(this == obj) return true;
//        if(obj == null) return false;
//        //클래스 검사
//        if(this.getClass() != obj.getClass()) return false;
//
//        //매개변수의 객체를 현재의 객체유형으로 형변환한다
//        Person that = (Person) obj;
//
//        //멤버 변수의 값들을 비교한다.
//        return this.num == that.num && Objects.equals(this.name, that.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(num, name);
//    }

    
    //generate에서 생성가능
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return num == person.num && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name);
    }
}
