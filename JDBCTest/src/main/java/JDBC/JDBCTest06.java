package JDBC;

//Statement객체를 사용하면 SQL injection 해킹에 노출될 수 있는 예제

import Util.DBUtil;

import java.sql.*;
import java.util.Scanner;

//계죄번호를 검색하는 프로그램
public class JDBCTest06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            System.out.println("=== 계좌 번호 검색하기 ===");
            System.out.print("검색할 계좌 번호 입력>> ");
            String bankNo = sc.nextLine();

            //Statement객체 이용하기
            //stmt를 썻을때 (' or 1=1 --)를 입력하면 전체 정보가 나온다. 1=1은 무조건 참이라서
            //' union select mem_id. mem_pass, mem_name, mem_bir from member -- 과같이 다른 정보도 불러올 수 있다

            String sql = "select * from bankInfo where bank_no = '"+ bankNo + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

//            String sql = "select * from bankInfo where bank_no = ?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, bankNo);
//            rs = pstmt.executeQuery();

            System.out.println("== 검색 결과 ==");
            System.out.println("계죄번호\t 은행이름\t 예금주명\t 개설날짜");
            System.out.println("--------------------------------------");
            while (rs.next()) {
                String no = rs.getString(1);
                String bName = rs.getString(2);
                String uName = rs.getString(3);
                String bDate = rs.getString(4);
                System.out.println(no + "\t" + bName + "\t" + uName + "\t" + bDate);

            }
            System.out.println("--------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) try{rs.close();}catch (SQLException e) {}
            if (stmt!=null) try{stmt.close();}catch (SQLException e) {}
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
    }
}
