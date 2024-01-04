package JavaIOTest;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferdIOTest {
    public static void main(String[] args) {
        try {
            //입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
            FileOutputStream fout =
                    new FileOutputStream("D:/D_Other/bufferTest.txt");

            //버퍼의 크기가 5인 버퍼 스트림 객체 생성
            //버퍼 스트림의 버퍼의 기본크기는 8Kb(8196byte)이다.
            BufferedOutputStream bout =
                    new BufferedOutputStream(fout, 5);
            for(char c='1'; c<= '9'; c++) {
                bout.write(c);
            }
            //bout.flush();   //쓰기 작업이 완료되면 버퍼에 남아있는 데이터를
                            //강제로 출력장치로 보내 출력시킨다

//            fout.close();
            bout.close();   //스트림 닫기    =>보조 스트림을 받으면
                            //      보조 스트림에서 사용한 기반이되는 스트림도 같이 닫힌다.

            System.out.println("작업 끝...");
        }catch (IOException e) {

        }
    }
}
