package JavaIOTest;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ObjectIOTest03 {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            ObjectOutputStream oout = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("D:/D_Other/memObj.data"))
            );
            while(true) {
                System.out.println("저장할 Member정보를 입력하세요...");
                System.out.print("이름>> ");
                String name = scan.next();
                System.out.print("나이>> ");
                int age = scan.nextInt();
                scan.nextLine();
                System.out.print("주소 >> ");
                String addr = scan.nextLine();

                Member mem = new Member(name, age, addr);

                oout.writeObject(mem);

                System.out.print("계속하시겠습니까?(y/n) >> ");
                String ans = scan.next();

                if("n".equalsIgnoreCase(ans)) break;

            }
            oout.writeObject(null);

            oout.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
