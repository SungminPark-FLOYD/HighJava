package TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread{
    private Socket socket;
    private DataInputStream din;

    //생성자
    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            din = new DataInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (din != null){
            try {
                System.out.println(din.readUTF());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
