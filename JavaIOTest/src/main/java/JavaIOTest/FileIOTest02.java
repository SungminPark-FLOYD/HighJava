package JavaIOTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileIOTest02 {
    public static void main(String[] args) {
        try{
            File file = new File("D:/D_Other/out.txt");
            
            //파일 출력용 스트림 객체 생성
            FileOutputStream fout = new FileOutputStream(file);
            
            for(char ch='A'; ch<='Z'; ch++) {
                fout.write(ch); //ch변수의 데이터를 파일로 출력한다
            }
            fout.write('\n');   //줄바꿈
            //문자열을 쓰려면 바이트 배열로 바꾼다
            String str = "HELLO!! => 안녕";
            byte[] bStrArr = str.getBytes("UTF-8"); //utf-8적어주는게 좋다
            fout.write(bStrArr);

            System.out.println("작업 완료!!");
            fout.close();//스트림 닫기
        }catch (IOException e) {
            
        }
    }
}
