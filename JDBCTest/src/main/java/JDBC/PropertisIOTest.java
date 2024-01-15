package JDBC;

import java.io.*;
import java.util.Properties;

public class PropertisIOTest {
    public static void main(String[] args) {
        //properties객체를 이용한 데이터 입출력 예제
        Properties prop = new Properties();

        //Properties객체로 데이터를 저장하고 읽어올 파일 정보가 저장된 file객체 생성
        File f = new File("JDBCTest/src/main/resources/config/memo.properties");
        FileOutputStream fout = null;

        try {
            //저장할 내용을 Properties객체에 추가하기
            prop.setProperty("name", "홍길동");
            prop.setProperty("age", String.valueOf(25));
            prop.setProperty("addr", "대전시 중구 오류동");
            prop.setProperty("tel", "010-0000-0000");

            //Properties객체를 저장할 출력요요 스트림 객체 생성
            fout = new FileOutputStream(f);

            //저장하기
            prop.store(fout, "이것은 주석...");

            System.out.println("출력 작업 완료...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fout != null) try {fout.close();} catch (IOException e) {}
        }

        System.out.println("------------------------------------------------------------");

        //읽어온 정보를 저장할 Properties객체 생성
        Properties prop2 = new Properties();

        //읽기용 스트림 객체 변수 선언
        FileInputStream fin = null;

        try {
            //스트림객체 생성
            fin = new FileInputStream(f);
            //입력용 스트림을 이용하여 파일 내용을 읽어와 Properties객체에 저장하기
            //파일 내용을 읽어와 key값과 value값을 분류한 후 Properties객체에 추가한다.
            prop2.load(fin);

            //읽어온 정보를 출력해 보기
            System.out.println("name : " + prop2.getProperty("name"));
            System.out.println("age : " + prop2.getProperty("age"));
            System.out.println("tel : " + prop2.getProperty("tel"));
            System.out.println("addr : " + prop2.getProperty("addr"));
            System.out.println("읽기 작업 완료!!");

        } catch (Exception e) {
        } finally {
            if(fin != null) try {fin.close();} catch (IOException e) {}
        }
    }
}
