package JDBC;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

//문제 ) lprod_id값을 2개 입력받아서 두 값중 작은 값부터
// 큰 값 사이의 자료들을 출력하시오
public class JDBCTest03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[2];
        System.out.print("lprod_id값 입력: ");
        arr[0] = sc.nextInt();
        System.out.print("lprod_id값 입력: ");
        arr[1] = sc.nextInt();

        Arrays.sort(arr);
        
        //최소값 최대값뽑기
//        int min = Math.min(a,b);
//        int max = Math.max(a,b);
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE",
                    "FLOYD",
                    "java"
            );

            String sql = "select LPROD_ID,LPROD_GU,LPROD_NM from lprod where lprod_id between ";
            sql += arr[0];
            sql += " and ";
            sql += arr[1];

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            int cnt = 0;
            System.out.println("사용자가 입력한 값 : " + Arrays.toString(arr));
            System.out.println("-----------------------------------------------");
            System.out.println("입력값보다 큰 값");
            System.out.println("-----------------------------------------------");
            while(rs.next()) {
                cnt++;
                System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));
                System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
                System.out.println("LPROD_NM : " + rs.getString("LPROD_NM") );
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
