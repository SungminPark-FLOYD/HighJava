package mybatis.test;

import mybatis.vo.LprodVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import util.mybatisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//jdbctest05.java의 기능을 mybatis용으로 변환
//mapper파일명 = jdbc-mapper.xml
//               namespace속성값 : jdbc
public class JdbcToMybatis {
//    private static SqlSessionFactory sqlSessionFactory = null;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
//        InputStream in = null;
//        try {
//            //1-1 환경 설정 파일을 읽어올 스트림 객체 생성
//            in = Resources.getResourceAsStream(
//                    "config/mabatis-config.xml");
//
//            //1-2 환경설정 파일을 읽어와 환경 설정 작업을 진행한다.
//            //(환경설정이 완료되면 SqlSessionFactory객체가 생성된다)
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
//        } catch (Exception e) {
//            System.out.println("MyBatise 초기화 실패!!");
//            e.printStackTrace();
//        }finally {
//            if(in != null) try {in.close();} catch (IOException e) {e.printStackTrace();}
//        }



        JdbcToMybatis obj = new JdbcToMybatis();
        obj.menu();
    }

    private void menu() {
        while (true) {
            System.out.println("메뉴 : 1. 상품등록        2. 상품 리스트       0. 종료");
            int sel = sc.nextInt();

            switch (sel){
                case 1:
                    insertLprod();
                    break;
                case 2:
                    selectLprod();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다...");
                    return;
                default:
                    System.out.println("다시 입력해 주세요");
            }
        }

    }

    private void insertLprod() {
        System.out.println("insert 작업 시작...");


        String lprodGu = null;
        int cnt = 0;
        while (true){
            System.out.print("상품 분류 코드 입력(exP101) >> ");
            lprodGu = sc.next();
            cnt = idChk(lprodGu);
            if(cnt > 0) System.out.println("존재하는 정보입니다...");
            else break;
        }

        System.out.print("Lprod_nm 입력(ex컴퓨터제품) >> ");
        String lprodNm = sc.next();


        //입력받은 데이터를 VO에 저장한다
        LprodVo lvo = new LprodVo();
        lvo.setLprod_gu(lprodGu);
        lvo.setLprod_nm(lprodNm);

        SqlSession session = null;

        try {
            session = mybatisUtil.getSqlSession();

            int insertCnt = session.insert("jdbc.insertLprod", lvo);
            if(insertCnt > 0) {
                //SqlSession객체를 생성할 때 AutoCommit이 비 활성화된 상태일때는
                //commit을 직접 실행해야 한다
                session.commit();
                System.out.println("insert 작업 성공!!");
            }else System.out.println("insert 작업 실패~~");

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
    }

    private int idChk(String lprodGu){
        SqlSession session = null;
        int cnt = 0;
        try {
            session = mybatisUtil.getSqlSession();
            cnt = session.selectOne("jdbc.idChk", lprodGu);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {if(session != null) session.close();}

        return cnt;
    }

    private void selectLprod() {
        System.out.println("selectList 작업 시작...");

        SqlSession session = null;
        List<LprodVo> list = null;

        try {
            session = mybatisUtil.getSqlSession();

            list = new ArrayList<LprodVo>();

            list = session.selectList("jdbc.selectLprod");
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
}
