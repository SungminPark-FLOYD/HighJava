package singleton;

public class SingletonTest {
    public static void main(String[] args) {
        MySingleton sigle = MySingleton.getInstance();
        sigle.displayTest();

        MySingleton test2 = MySingleton.getInstance();
        MySingleton test3 = MySingleton.getInstance();

        System.out.println("test2 =>" + test2.toString());
        System.out.println("test3 =>" + test3.toString());
    }
}
