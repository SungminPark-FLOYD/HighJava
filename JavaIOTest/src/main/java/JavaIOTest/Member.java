package JavaIOTest;

import java.io.Serializable;

public class Member implements Serializable {
    //transient => 키워드가 붙은 멤버변수는
    // 직렬화 대상에서 제외되어 저장되지 않는다

    private String name;
    private  int age;
    private  String addr;

    public Member(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
