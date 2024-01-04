package JavaIOTest;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileIOTest03 {
    public static void main(String[] args) {
        //사용자로부터 출력할 단을 입력받아 구구단을 파일로 출력하는 프로그램 작성하기
        //출력할 파일명 : 'd:d_other/gugudan.txt'
        FileOutputStream fout = null;
        try {
            File file = new File("D:/D_Other/gugudan.txt");

            fout = new FileOutputStream(file);
            Scanner sc = new Scanner(System.in);
            System.out.print("출력할 단을 입력하세요>> ");
            int dan = sc.nextInt();
            for(int i = 1; i <= 9; i++) {
                String str = dan + " * " + i + " = " + dan*i + '\n';
                byte[] bStrArr = str.getBytes("UTF-8");
                fout.write(bStrArr);
            }
            System.out.println("작업 완료!!");

        }catch (IOException e) {

        }
        finally {
            if(fout!= null) try{fout.close();}catch (IOException e) {}
        }

    }
}
