package JDBC;

import Util.DBUtil;

import java.sql.*;
import java.util.Scanner;

/*
    Lprod 테이블에 새로운 데이터 추가하기

    Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
    Lprod_id는 현재의 Lprod_id들 중에서 제일 큰 값보다 1 크가 헨다.

    입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 */
public class JDBCTest05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {

//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@localhost:1521:xe",
//                    "FLOYD",
//                    "java"
//            );
            conn = DBUtil.getConnection();


//            Lprod_id는 현재의 Lprod_id들 중에서 제일 큰 값보다 1 크게 한다.
//            String max = "select MAX(LPROD_ID)+1 maxnum from LPROD";
//            psmt = conn.prepareStatement(max);
//            rs = psmt.executeQuery();
//            int maxNum = 0;
//            if(rs.next()){
//                maxNum = rs.getInt("maxnum");
//            }
//            maxNum++;
            //혹시 덮어 쓰일수 있느지 다쓰고 닫아준다.
//            if(psmt != null) psmt.close();


            String idChk = "select count(*) from LPROD where LPROD_GU = ?";
            psmt = conn.prepareStatement(idChk);
            String lprodGu = "";
            boolean ok = false;
            while (!ok) {
                System.out.print("상품 분류 코드 입력(exP101) >> ");
                lprodGu = sc.next();
                psmt.setString(1, lprodGu);
                rs = psmt.executeQuery();
                rs.next();
                if (rs.getInt(1) != 0) {
                    System.out.println("존재하는 제품코드 입니다.");
                    ok = false;
                } else ok = true;
            }

            System.out.print("Lprod_nm 입력(ex컴퓨터제품) >> ");
            String lprodNm = sc.next();

            //데이터 추가
            //lprod_id는 테이블에 값이 하나도 없을 경우를 대비해 따로 빼서 계산한다.
            String sql = "insert into lprod (LPROD_ID, LPROD_GU, LPROD_NM)" +
                    "    values ((select MAX(LPROD_ID)+1 " +
                    "                  from LPROD), ?, ?)";

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, lprodGu);
            psmt.setString(2, lprodNm);

            int cnt = psmt.executeUpdate();


            if (cnt > 0) {
                System.out.println("데이터 입력 성공!");
            }else System.out.println("데이터 입력 실패!");

            String listSql = "select * from lprod ";
            psmt = conn.prepareStatement(listSql);
            rs = psmt.executeQuery();

            while (rs.next()){
                System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));
                System.out.println("LPROD_GU : " + rs.getString(2));
                System.out.println("LPROD_NM : " + rs.getString("LPROD_NM") );
                System.out.println("-----------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            if (psmt != null) try {
                psmt.close();
            } catch (SQLException e) {
            }
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
            }
            //Connection 객체는 매번 닫아주는게 좋다
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            }
        }


    }
}
