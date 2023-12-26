package Set;

import java.util.*;

public class SetTest {
    public static void main(String[] args) {
        /*
        * - List와 Set의 차이점
        * 1. List :
        *  - 데이터의 순서(index)가 있다]
        *  - 중복되는 데이터를 저장할 수 있다
        * 2. Set
        *   - 데이터의 순서(index)가 없다
        *   - 중복되는 데이터를 저장할 수 없다
        */

        HashSet hs1 = new HashSet();

        //Set에 데이터를 추가할 대도 add()메서드를 이용한다.
        hs1.add(12);
        hs1.add("DD");
        hs1.add("AA");
        hs1.add(123);
        hs1.add("q");
        hs1.add(11);

        System.out.println(hs1);

        //Set에 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다
        boolean hashBool = hs1.add(123);
        System.out.println(hashBool);

        //Set의 데이터 수정
        //Set 에는 수정하는 명령이 따로 없기 때문에 수정하려는 데이터를 삭제한 후
        // 새로 추가해 주는 방법으로 처리한다.
        /*Set의 데이터 삭제하기 => remove(삭제할자료)
        *                      => 반환값 : 삭제성공(true), 삭제실패(false)
        *                     => clear() => 전체삭제
        * */

        //Set데이터 변경
        hs1.remove(123);
        System.out.println(hs1);
        hs1.add(123);
        System.out.println(hs1);

        //Set데이터들은 순서가 없기 때문에 List처럼 index로 데이터를 하나씩 불러올 수 없다
        //그래서 데이터를 하나씩 얻기 위해서는 Iterator형의 객체로 변환해야 한다.
        /*Set형의 데이터들을 Iterator형의 객체로 변환하는 메서드 ==> iterator()*/
        Iterator it = hs1.iterator();
       // System.out.println(it.next());

        //Iterator의 hasNext()메서드
        //  => Iterator의 포인터가 가리키는 곳의 다음번재에 데이터가 있는지 검사한다.
        //데이터가 있으면 true 없으면 false

        //Iterator의 next()메서드
        //  =>Iterator의 포인터를 다음 위치로 이동한 후 이동한 위체에 있는 데이터를 읽고 반환
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        //우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해보자
        //번호는 1번부터 16번짜기 있고 추첨할 인원은 3명이다
        //당첨자 번호를 출력하시오

        //시작값부터 종료값 사이의 정수형 난수 만드는 방법
        //(int)(Math.random() * (종료값 - 시작값 + 1) + 시작값)
        HashSet<Integer> testSet = new HashSet<>();
        while(testSet.size() < 6) {
            int random = (int)(Math.random() * 45 + 1);
            testSet.add(random);

        }
        System.out.println(testSet);

        //Set유형의 자료를 List형을 변환하기
        ArrayList<Integer> testList = new ArrayList<Integer>(testSet);
        Collections.sort(testList);
        System.out.println(testList);

    }
}
