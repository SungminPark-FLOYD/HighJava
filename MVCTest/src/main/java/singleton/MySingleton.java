package singleton;


/*
    Singleton 패턴 => 객체가 1개만 만들어지게 하는 방법
    데이터를 공유하고싶을때
    외부에서 직접 생성 불가 -> new

    -Singleton 클래스 만드는 방법(필수 구성 요소)
    1. 자신 class에 참조값이 저장될 변수를 private static으로 선언한다.
    2. 모든 생성자의 접근 제한자를 private으로 한다.
    3. 자신 class의 인스턴스를 생성하고 반환하는 메서드를 public static으로 작성한다.
        (이 메서드의 이름은 보통 getInstance로 한다.)
 */
public class MySingleton {
    private static MySingleton instance = null;

    private MySingleton() {}

    public static MySingleton getInstance() {
        if(instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    //이 클래스가 처리할 내용
    public void displayTest() {
        System.out.println("싱글톤 클래스의 메서드");
    }


}
