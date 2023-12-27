package GenericTest;

import org.example.Solution;

/**
*   - 제네릭 클래스 만드는 방법
*   - class 클래스명<제네릭타입글자> {
*       private 제내릭타입글자 변수명;    //변수선언에 제네릭을 사용할 경우
*       ...
*       제네릭타입글자 메서드명(매개변수들...) {    //메서드의 반환값에 제네릭을 사용할 경우
*           ...
*           return 값;
*       }
*       //매개변수를 지정할때 제네릭을 사용할 경우
*       반환값 자료형 메서드명(제네릭타입글자 변수명, ...) {
*           ...
*
*       }
*   }
*
*
* -- 제네릭타입글자(영어대문자 1글자 사용...)
* T => Type
* K => Key
* V => Value
* E => Element
* */

//제네릭을 사용하지 않을 경우
class NonGeneric {
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

//제네릭 사용
class MyGeneric<T> {
    private T value;    //변수에 사용

    public void setValue(T value) { //매개변수에 사용
        this.value = value;
    }
    
    public  T getValue() {      //반환값에 사용
        return value;
    }
}

public class GenericTest {
    public static void main(String[] args) {
        NonGeneric non1 = new NonGeneric();
        non1.setValue("안녕하세요.");

        NonGeneric non2 = new NonGeneric();
        non2.setValue(123);

        String rtn1 = (String) non1.getValue();
        int rtn2 = (int)non2.getValue();

        System.out.println(rtn1 + " " + rtn2);

        MyGeneric<String> my1 = new MyGeneric<String>();
        my1.setValue("우리나라");

        MyGeneric<Integer> my2 = new MyGeneric<Integer>();
        my2.setValue(500);

        String myRtn1 = my1.getValue();
        int myRtn2 = my2.getValue();

        System.out.println(myRtn1 + " " + myRtn2);


    }
}
