package TCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPServer01 {
    public static void main(String[] args) throws IOException {
        //TCP소켓 통신을 위해 ServerSocket객체를 생성한다.
        ServerSocket server = new ServerSocket(7777);
        System.out.println("접속을 기다립니다..");

        //accept()메서드
        //      => 클라이언트에서 연결요청이 들어 올 때까지 계속 기다린다.
        //      => 연결 요청이 들어오면 새로운 Socket객체를 생성해서
        //          클라이언트의 Socket과 연결한다.
        Socket socket = server.accept();

        //accept()메서드 이후의 소스는 연결이 완료되어야만 실행된다.
        System.out.println("클라이언트와 연결되었습니다...");
        System.out.println();

        System.out.println("상대방 컴퓨터의 정보");
        System.out.println("IP 주소 : " + socket.getInetAddress().getHostAddress());
        System.out.println("Port번호 : " + socket.getPort());
        System.out.println();

        System.out.println("현재 컴퓨터의 정보");
        System.out.println("IP주소 : " + socket.getLocalAddress());
        System.out.println("Port 번호 : " + socket.getLocalPort());
        System.out.println();

        //클라이언트에게 메시지 보내기
        // => Socket을 이용하여 OutputStream을 구성해서 보낸다.
        //      (Socket의 getOutputStream()메서드 이용)
        OutputStream out = socket.getOutputStream();
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeUTF("환영합니다. 어서오세요....");

        //소켓과 스트림 닫기
        dout.close();
        socket.close();
        server.close();
    }
}
