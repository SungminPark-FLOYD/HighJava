package JavaIOTest;

import java.io.*;
import java.util.Scanner;

public class FileIOTest07 {
    public static void main(String[] args) {
        //사용자로부터 출력할 단을 입력받아 구구단을 파일로 출력하는 프로그램 작성하기
        //출력할 파일명 : 'd:d_other/gugudan2.txt'
        //문자기반 스트림을 이용해서 작성하시오

        FileWriter file = null;
        try {
            file = new FileWriter("D:/D_Other/gugudan2.txt");


            Scanner sc = new Scanner(System.in);
            System.out.print("출력할 단을 입력하세요>> ");
            int dan = sc.nextInt();
            for(int i = 1; i <= 9; i++) {
                String str = dan + " * " + i + " = " + dan*i + '\n';
                file.write(str);
            }
            System.out.println("작업 완료!!");

        }catch (IOException e) {

        } finally {
            if(file!= null) try{file.close();}catch (IOException e) {}
        }

    }
}
