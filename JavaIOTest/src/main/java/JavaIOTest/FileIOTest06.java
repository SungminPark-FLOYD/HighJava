package JavaIOTest;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest06 {
    public static void main(String[] args) {
        //한글이 저장된 파일 읽어오기
        try {
//            FileReader fr = new FileReader("D:/D_Other/test_utf8.txt");
//            FileReader fr = new FileReader("D:/D_Other/test_ansi.txt");
            FileInputStream fin =
//                    new FileInputStream("D:/D_Other/test_utf8.txt");
                    new FileInputStream("D:/D_Other/test_ansi.txt");
            //기본 인코딩 방식으로 읽어온다
//            InputStreamReader isr = new InputStreamReader(fin);

            //인코딩 방식을 지정해서 읽어오기
            //인코딩 방식 예시
            //- MS949 ==> 윈도우의 한글 기본 인코딩 방식(ANSI)
            //- UTF-8 ==> 유니코드 UTF-8인코딩 방식
            //- US-ASCII ==> 영문전용 인코딩 방식
            InputStreamReader isr =
                    //대소문자 상관없이 사용가능
//                    new InputStreamReader(fin, "UTF-8");
                    new InputStreamReader(fin, "ms949");

            //저장한 파일인코딩 형식과 읽어오는 인코딩형식이 같아야 잘 출력된다.
            int c;

            while((c = isr.read())!= -1) {
                System.out.print((char)c);
            }
            isr.close();
        }catch (IOException e) {

        }
    }
}
