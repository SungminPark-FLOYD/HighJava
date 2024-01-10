package TCP;

import jdk.internal.util.xml.impl.Input;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TCPClient01 {
    public static void main(String[] args) throws IOException {
        //현재 자신의 컴퓨터를 나타내는 방법
        //1) 원래의 IP주소 : 192.168.142.16
        //2) 지정된 IP주소 : 127.0.0.1 (내 컴퓨터를 나타낸다)
        //3) 원래의 컴퓨터 이름 :DESKTOP-6BKI5AP
        //4) 지정된 컴퓨터 이름: localhost


        System.out.println("서버에 연결을 시도합니다...");
        //서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
        //Socket객체는 생성이 완료되면 해당 IP주소와 Port번호로 연결 신호를 보낸다
        Socket socket = new Socket("localhost", 7777);

        //이 부분은 서버와 연결된 이후 처리할 내용을 기술하면 된다.
        System.out.println("서버에 연결되었습니다..");

        //서버에서 보낸 메시지를 받아서 출력하기

        //Socket을 이용하여 InputStream객체를 생성한다.
        //(Socket의 getInputStream()메서드 이용)
        InputStream in = socket.getInputStream();
        DataInputStream din = new DataInputStream(in);

        //서버가 보낸 메시지를 받아서 출력한다.
        System.out.println("서버에서 보낸 매시지 : " + din.readUTF());
        System.out.println();

        //스트림과 소켓닫기
        din.close();
        socket.close();


    }
}
