package DocTest;

//javaDoc 문서 만들기 예제 ==> 프로그램과 메뉴얼을 같이 만드는 방법

/**
 * @author 박성민
 * @version 1.0
 *
 * <br>
 * <p>
 *     파일명 : JavaDocTest.java<br>
 *     설 명 : JavaDoc문서 작성을 위한 연습용 interface
 *     <br><br>
 *
 *     변경 이력<br>
 *     <hr><br>
 *     변경 날짜 : 2024-01-16<br>
 *     변경 인 : 박성민<br>
 *     변경 내용 : 최초 생성<br>
 *     <hr><br>
 *
 * </p>
 */
public interface JavaDocTest {
    /**
     * 메서드명 : methodTest<br>
     * 설 명 : 반환값이 없는 메서드<br>
     *
     * @param a 첫번째 매개변수(정수형)
     * @param b 두번째 매개변수(정수형)
     */
    public void methodTest(int a, int b);

    /**
     * 메서드명 : methodAdd<br>
     * 설 명 : 반환값이 있는 메서드<br><br>
     * @param x 정수형 첫번째 데이터
     * @param y 정수형 두번째 데이터
     * @return 처리된 결과를 정수형으로 반환한다.
     */
    public int methodAdd(int x, int y);

    /**
     * 메서드명 : methodInput<br>
     * 설 명 : 매개변수가 없는 메서드<br>
     *
     * @return 정수형으로 반환한다
     */
    public int methodInput();
}
