package Enum;

/**
 * enum(열거형) => 서로 관련있는 상수들의 집합을 나타낸다.
 *             => 클래스처럼 보이게 하는 상수
 * - 작성 방법
 *      열거형은 클래스처럼 독립된 java파일에 만들 수 있고,
 *      하나의 java파일에 클래스와 같이 만들수 있고,
 *      클래스의 내부에 내부 클래스처럼 만들 수 있다.
 *
 * - 열거형의 속성 및 메서드
 *  1) 열거형변수.name()        => 열거형 상수의 이름을 문자열로 반환한다.
 *  2) 열거형변수.ordinal()     => 열거형 상수가 정의된 순서값(index값)을 반환한다.(0부터 시작)
 *  3) 열거형이름.valueOf("열거형상수명")  => 인자값에 지정한 '열거형상수명'과
 *              일치하는 '열거형 상수'를 반환한다.
 *  4) 열거형이름.상수명         => 열거형이름.valueOf("상수명")메서드와 같다.
 *  5) 열거형이름.values()      => 열거형의 모든 상수들을 배열로 반환한다.
 *  -------------------------------------------------------------------
 *  - 열거형 선언하기
 *  방법1)
 *      enum 열거형이름 { 상수명1, 상수명2, ... }
 *  방법2)
 *      enum 열거형이름 {
 *          상수명1 (값들1, ...),
 *          상수명2(값들2, ...),
 *          ...
 *          상수명n(값들n, ...);
 *
 *          //'값들'이 저장될 변수 선언('값들' 갯수만큼  변수를 선언한다.)
 *          private 자료형이름 변수명1;
 *          private 자료형이름 변수명2;
 *          ...
 *
 *          //열거형의 생성자 작성한다.
 *          //=> 열거형의 생성자는 '열거형상수'에 값들을 셋팅하는 역할을 수행한다
 *          //=> 열거형 생성자의 접근 제한자는 묵시적으로 'private'이다.
 *
 *          //생성자의 매개변수는 '값들'과 갯수가 같고, 자료형이 맞아야 한다.
 *          private 열거형이름(자료형이름 변수명1, ...) {
 *              위에서 선언한 변수들을 초기화하는 작업을 주로 한다.
 *              ...
 *          }
 *
 *          //위에서 선언한 변수들을 외부에서 사용할 수 있도록 getter메서드를 작성한다.
 *      }
 */
public class EnumTest {
    public enum Color {
        RED,
        GREEN,
        BLUE
    }

    public enum  Count {
        ONE,
        TWO,
        THREE
    }

    public enum Season {
        //상수명(값들,...) 형식의 선언
        봄("3월부터 5월까지", 13),  //기간과 평균 온도 지정
        여름("6월부터 8월까지", 25),
        가을("9월부터 11월까지", 15),
        겨울("12월부터 2월까지", 1);

        //값들이 저장될 변수 선언
        private String span;
        private int temp;

        //생성자 : 묵시적으로 private
        Season(String months, int temp) {
            span = months;
            this.temp = temp;
        }

        //getter
        public String getSpan() {
            return span;
        }
        public int getTemp() {
            return temp;
        }
    }
    public static void main(String[] args) {

        /*System.out.println("RED : " + ConstTest.RED);
        System.out.println("Three : " + ConstTest.THREE);

        if(ConstTest.ONE == ConstTest.RED) {
            System.out.println("같다...");
        }
        else System.out.println("같지 않다..");*/

        Color myCol = Color.valueOf("GREEN");   //Color.GREEN과 같다.
        Count mycnt = Count.ONE;    //Count.valueOf("ONE");과 같다

        System.out.println(myCol.name());
        System.out.println(mycnt.name());

//        if(Count.TWO == Color.BLUE) => 다른 종류의 열거형까리 비교불가

        if(myCol == Color.BLUE) {
            System.out.println("같다..");
        }
        else System.out.println("같지 않다...");

        Season ss = Season.valueOf("봄");
        System.out.println(ss.name());
        System.out.println(ss.getSpan() + " " + ss.getTemp());

        //열거형이름.values() 예제
        for(Season time : Season.values()) {
            System.out.println(time.name() + " == " + time +
                    time.getSpan() + " : " + time.getTemp());
        }

        //열거형을 switch문에서 비교하기
        //case문에는 '상수명'만 기술해야한다.
        switch (myCol) {
            case RED:
                System.out.println("RED 입니다.");
                break;
            case GREEN:
                System.out.println("GREEN 입니다.");
                break;
            case BLUE:
                System.out.println("BLUE 입니다.");
                break;
        }
    }
}
