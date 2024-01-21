package mybatis.test;

import mybatis.vo.LprodVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//MyBatis를 이용하여 DB자료를 처리하는 순서 및 방법
public class lprodMybatisTest {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //1. MYBatis의 환경 설정 파일(mybatis-config.xml)을 읽어와서
        //  처리하여 SqlSesstionFactory객체를 생성한다.
        InputStream in = null;

        try {
            //1-1 환경 설정 파일을 읽어올 스트림 객체 생성
            in = Resources.getResourceAsStream(
                    "config/mabatis-config.xml");

            //1-2 환경설정 파일을 읽어와 환경 설정 작업을 진행한다.
                //(환경설정이 완료되면 SqlSessionFactory객체가 생성된다)
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (Exception e) {
            System.out.println("MyBatise 초기화 실패!!");
            e.printStackTrace();
        }finally {
            if(in != null) try {in.close();} catch (IOException e) {e.printStackTrace();}
        }

        lprodMybatisTest a = new lprodMybatisTest();
//        a.insertTest();
//        a.updateTest();
//        a.deleteTest();
//        a.selectListTest();
        a.selectTest();
    }

    //2. mapper에 등록된 SQL문 중 실행할 SQL문을 호출해서 원하는 작업을 수행한다.

    //2-1 insert 연습
    private void insertTest() {

        System.out.println("insert 작업 시작...");
        System.out.print("Lprod_ID 입력 >>");
        int lprodId = sc.nextInt();

        System.out.print("Lprod_GU 입력 >>");
        String lprodGu = sc.next();

        System.out.print("Lprod_NM 입력 >>");
        String lprodNm = sc.next();

        //입력받은 데이터를 VO에 저장한다
        LprodVo lvo = new LprodVo();
        lvo.setLprod_id(lprodId);
        lvo.setLprod_gu(lprodGu);
        lvo.setLprod_nm(lprodNm);

        //원하는 SQL문을 호출해서 실행하는 객체는 SqlSession객체이다
        //SqlSession객체는 SqlSessionFactory객체의 OpenSession()메서드를
        //이용하여 생성한다.
        //형식) SqlSessionfactory객체.openSession(논리값)
        // --> '논리값'이 true 이면 AutoCommit이 활성화 된 상태이고,
        //      '논리값'이 생략되거나 false이면 AutoCommit이 비활성화 된 상태이다.
        SqlSession session = null;
        try{
            session = sqlSessionFactory.openSession();

            //생성된 sqlSession을 이용하여 처리할 SQL문을 호출하여 실행한다.
            // insert처리하기 형식) SqlSesstion객체.insert("namespace속성값.id속성값",파라미터 클래스)
            //반환값 : 작업에 성공한 레코드수
            int insertCnt = session.insert("lprod.insertLprod", lvo);
            if(insertCnt > 0) {
                //SqlSession객체를 생성할 때 AutoCommit이 비 활성화된 상태일때는
                //commit을 직접 실행해야 한다
                session.commit();
                System.out.println("insert 작업 성공!!");
            }else System.out.println("insert 작업 실패~~");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
    }

    //2- 2 update연습
    private void updateTest(){
        System.out.println("update 작업 시작...");

        System.out.print("수정할 Lprod_GU 입력 >>");
        String lprodGu = sc.next();

        System.out.print("새로운 Lprod_ID 입력 >>");
        int lprodId = sc.nextInt();

        System.out.print("새로운 Lprod_NM 입력 >>");
        String lprodNm = sc.next();

        //입력받은 데이터를 VO에 저장한다
        LprodVo lvo = new LprodVo();
        lvo.setLprod_id(lprodId);
        lvo.setLprod_gu(lprodGu);
        lvo.setLprod_nm(lprodNm);

        SqlSession session = null;

        try {
            session = sqlSessionFactory.openSession();

            //생성된 SqlSession객체를 이용하여 update작업
            //형식) SqlSession객체.update("name속성값.id속성값", 파라미터 클래스)
            //반환값 : 작업에 성공한 레코드 수
            int updateCnt = session.update("lprod.updateLprod", lvo);
            if(updateCnt > 0){
                session.commit();
                System.out.println("update 작업 성공!!!");
            }else System.out.println("update 작업 실패~~~");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null) session.close();
        }
    }

    //2- 3 delete연습
    private void deleteTest() {
        System.out.println("delete 작업 시작...");

        System.out.print("삭제할 Lprod_GU 입력 >>");
        String lprodGu = sc.next();

        SqlSession session = null;

        try {
            session = sqlSessionFactory.openSession();

            //생성된 SqlSession객체를 이용하여 delete작업
            //형식) SqlSession객체.update("name속성값.id속성값", 파라미터 클래스)
            //반환값 : 작업에 성공한 레코드 수
            int deleteCnt = session.delete("lprod.deleteLprod", lprodGu);
            if(deleteCnt > 0){
                session.commit();
                System.out.println("delete 작업 성공!!!");
            }else System.out.println("delete 작업 실패~~~");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null) session.close();
        }

    }

    //2- 4 select 연습
    //1)select문의 처리 결과가 여러개의 레코드 일경우
    //형식)SqlSession객체.selectList("name속성값.id속성값",파라미터클래스);
    //selectList() 메서드는 검색된 각각의 레코드를 VO객체에 저장한 후
    //이 VO객체를 List에 추가해 주는 작업을 자동으로 수행한다.
    private void selectListTest(){
        System.out.println("selectList 작업 시작...");

        SqlSession session = null;
        List<LprodVo> list = null;

        try {
            session = sqlSessionFactory.openSession();

            list = new ArrayList<LprodVo>();

            list = session.selectList("lprod.getAllLprod");
            if (!list.isEmpty()){
                for(LprodVo vo : list){
                    System.out.println(vo.getLprod_id() + "\t" + vo.getLprod_gu() + "\t" + vo.getLprod_nm());
                }
            }
            else System.out.println("select작업 실패~~~");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session!=null) session.close();
        }
    }

    //2) select문을 실행한 결과가 1개의 레코드일때
    private void selectTest(){
        System.out.println("select작업 시작...");

        System.out.print("검색할 Lprod_GU 입력 >>");
        String lprodGu = sc.next();

        SqlSession session = null;

        try {
            session = sqlSessionFactory.openSession();

            //원하는 select문을 호출하여 실행힌다
            // ==> select문을 실행한 결과가 1개의 레코드인 경우에 selectOne()
//                메서드를 사용한다.
//                selectOne()메서드는 검색한 자료가 없으면 null을 반환한다.
            //형식) SqlSession객체.selectOne("namespace속성값.id속성값",파라미터 클래스)
            LprodVo lvo = session.selectOne("lprod.getLprod", lprodGu);

            if(lvo == null){
                System.out.println("검색한 데이터가 하나도 없습니다..");
                return;
            }
            else {
                System.out.println("검색결과");
                System.out.println(lvo.getLprod_id() + "\t" + lvo.getLprod_gu() + "\t" + lvo.getLprod_nm());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null) session.close();
        }

    }


}
