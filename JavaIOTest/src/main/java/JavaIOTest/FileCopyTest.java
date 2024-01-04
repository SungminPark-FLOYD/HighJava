package JavaIOTest;


import java.io.*;

/*
    D:/D_Other폴더에있는 펭귄.jpg파일을
    D:/D_Other/연습용 폴더에 복사본_펭귄.jpg파일로 복사하는 프로그램
 */
public class FileCopyTest {
    public static void main(String[] args) {

        File fi = new File("D:/D_Other/펭귄.jpg");
        File fo = new File("D:/D_Other/연습용/복사본_펭귄.jpg");

        //파일 유무 체크
        if(!fi.exists()) {
            System.out.println(fi.getPath() + " 파일이 없습니다.");
            System.out.println("복사작업을 중지합니다..");
            return;
        }
        try {
            //원본파일을 읽어올 스트림객체 생성
            FileInputStream fin = new FileInputStream(fi);
            //대상 파일에 저장할 스트림객체 생성
            FileOutputStream fout = new FileOutputStream(fo);

            System.out.println("복사 작업 시작 ...");

            int c;
            while((c = fin.read()) != -1) {
                fout.write(c);
            }
            System.out.println("작업완료!");
            fin.close();
            fout.close();
        }catch (IOException e) {

        }





    }
}
