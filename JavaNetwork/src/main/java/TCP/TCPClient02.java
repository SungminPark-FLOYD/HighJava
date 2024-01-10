package TCP;

import java.io.IOException;
import java.net.Socket;

public class TCPClient02 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 7777);
            System.out.println("서버에 연결되었습니다....");

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
