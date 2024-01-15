package JDBC;

import java.util.Properties;
import java.io.*;
public class PropertiesTest {
    public static void main(String[] args) {
        Properties prop = new Properties();

        //Properties객체로 데이터를 저장하고 읽어올 파일 정보가 저장된 file객체 생성
        File f = new File("JDBCTest/src/main/resources/config/dbinfo.properties");
        FileInputStream fin = null;

        try {
            //파일 읽기
            fin = new FileInputStream(f);
            prop.load(fin);

            //출력
            System.out.println("driver => " + prop.getProperty("driver"));
            System.out.println("url => " + prop.getProperty("url"));
            System.out.println("user => " + prop.getProperty("user"));
            System.out.println("pass => " + prop.getProperty("pass"));
        } catch (Exception e) {
        } finally {
            if(fin != null) try {fin.close();} catch (IOException e) {}
        }
    }
}
