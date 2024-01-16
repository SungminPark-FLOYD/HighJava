package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//JDBC드라이버를 로딩하고 DB에 접속하여 Connection객체를
//반환하는 메서드로 구성된 class 만들기
//(ResourceBundle 이용해서 설정하기)
public class DBUtil3 {
    private static ResourceBundle bundle;
    //정적(static) 초기화 블럭
    //static변수가 먼저 읽히고 static블럭안에 있는 문장을 읽는다
    //복잡한 계산식을 계산한 후에 초기화를 하고싶을때 사용한다.

    //클래스 로딩
    // => static변수 생성 => static변수가 기본값으로 초기화
    // static 변수의 명시적 초기화 => static초기화 블럭 실행

    // 객체 생성시
    // => 인스턴스 변수 생성 => 인스턴스변수의 기본값 초기화
    // => 인스턴스 변수의 명시적 초기화 => 인스턴스 초기화 블럭 실행
    // => 생성자 실행
    static {
        bundle = ResourceBundle.getBundle("config.dbinfo");
        try {
//            Class.forName(bundle.getString("driver"));
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (Exception e){
            System.out.println("드라이버 로딩 실패");
            e.printStackTrace();
        }
    }

    //인스턴스 초기화 블럭
    //인스턴스 초기화 블럭 이후에 생성자가 실행된다.
    {
        //클래스를 읽어들여서 메소드 영역에 클래스 정보를 저장할때 실행
        //객체를 int = a; 방식으로 선언하는 것을 명시적 초기화 라고한다.
        System.out.println("하이");
    }
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    bundle.getString("url"),
                    bundle.getString("user"),
                    bundle.getString("pass"));
        }catch (SQLException e) {
            System.out.println("DB 연결 실패~~~");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        DBUtil3 d = new DBUtil3();
    }
}
