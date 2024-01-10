package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {
    public static void main(String[] args) {
        //ServerSocket객체를 생성하여 클라이언트의 접속을 기다리고,
        //클라이언트가 접속해 오면 Socket객체를 생성하여
        //메시지를 보내는 Thread와 메시지를 받는 Thread를 생성할 때 매개변수로 넣어준다.
        try {
            ServerSocket server = new ServerSocket(7777);

            System.out.println("서버가 준비되었습니다...");


            //접속 대기
            Socket socket = server.accept();

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
