package JDBC;

import java.sql.*;
import java.util.Scanner;

//문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다
//        lprod_id가 큰 자료들을 출력하시오
public class JDBCTest02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("LPROD_ID 값을 입력하세요>> ");
        int lprod_id = sc.nextInt();

        //DB작업에 필요한 객체 변수 선언
        Connection conn = null;
        Statement stmt = null;

        //select문 안쓰면필요없음
        ResultSet rs = null;

        try{
            //1. 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //2. DB연결 => Connection객체 생성
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE",
                    "FLOYD",
                    "java"
            );

            String sql = "select LPROD_ID,LPROD_GU,LPROD_NM nm from LPROD where LPROD_ID > " + lprod_id;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            int cnt = 0;
            System.out.println("사용자가 입력한 값 : " + lprod_id);
            System.out.println("-----------------------------------------------");
            System.out.println("입력값보다 큰 값");
            System.out.println("-----------------------------------------------");
            while(rs.next()) {
                cnt++;
                System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));
                System.out.println("LPROD_GU : " + rs.getString(2));
                System.out.println("LPROD_NM : " + rs.getString("nm") );
                System.out.println("-----------------------------------------------");
            }
            if(cnt == 0) System.out.println("검색 데이터가 없습니다.");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (rs!=null) try{ rs.close();} catch (SQLException e) {}
            if (stmt!=null) try{ stmt.close();} catch (SQLException e) {}
            //Connection 객체는 매번 닫아주는게 좋다
            if (conn!=null) try{ conn.close();} catch (SQLException e) {}
        }
    }
}
