package JavaIOTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {
    public static void main(String[] args) {
        //FileInputStream을 이용한 파일 내용 읽기
        try{
            //읽어올 파일을 매개변수로 받는 FileInputStream객체 생성

            //객체 생성 방법 1 => 읽어올 파일 정보를 문자열로 지정하기
            //FileInputStream fin = new FileInputStream("D:/D_Other/test.txt");

            //객체 생성 방법 2 => 읽어올 파일 정보를 File객체로 지정하기
            File file = new File("D:/D_Other/test.txt");
            FileInputStream fin = new FileInputStream(file);

            int c;  //읽어온 데이터를 저장할 변수
            while((c=fin.read()) != -1) {
                //읽어온 데이터를 화면에 출력하기
                System.out.print((char)c);
            }
            fin.close();

        }catch (IOException e) {
            System.out.println("입출력 오류");
        }
    }
}
