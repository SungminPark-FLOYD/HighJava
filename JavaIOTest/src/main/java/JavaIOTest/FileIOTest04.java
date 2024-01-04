package JavaIOTest;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest04 {
    public static void main(String[] args) {
        //문자 기반의 스트림을 이용한 파일 내용 읽기
        try {
            //문자기반의 파일 입력용 스트림 객체 생성
            FileReader fr = new FileReader("D:/D_Other/test.txt");

            int c; //읽어온 데이터가 저장될 변수
            while((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
            fr.close();
        }
        catch (IOException e) {

        }
    }
}
