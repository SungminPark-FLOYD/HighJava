package TCP;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//이 클래스는 소켓을 통해서 메시지를 보내는 역할만 하는 쓰레드 클래스이다.
public class Sender extends Thread{
    private Socket soket;
    private DataOutputStream dout;

    private String name;
    private Scanner scan;

    //생성자
    public Sender(Socket socket) {
        this.soket = socket;
        scan = new Scanner(System.in);

        System.out.print("이름 입력 >> ");
        name = scan.nextLine();

        try {
            dout = new DataOutputStream(this.soket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(dout != null) {
            try {
                dout.writeUTF(name + " : " + scan.nextLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
