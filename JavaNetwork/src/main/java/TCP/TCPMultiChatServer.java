package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TCPMultiChatServer {
    //접속한 클라이언트 정보를 저장할 Map객체 선언
    //      => key값 : 접속한 사람 이름, value값 : 접속한 Socket객체
    private Map<String, Socket> clientMap;

    //생성자

    public TCPMultiChatServer() {
        //clientMap을 동기화 처리가 되도록 Map객체를 생성한다
        clientMap = Collections.synchronizedMap
                (new HashMap<String, Socket>());
    }

    public static void main(String[] args) {
        new TCPMultiChatServer().serverStart();
    }

    //시작메서드
    public void serverStart() {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(3333);
            System.out.println("서버가 시작 되었습니다...");

            while(true) {
                socket = server.accept();

                System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속했습니다...");
                System.out.println();

                ServerReceiver serverTh = new ServerReceiver(socket);
                serverTh.start();


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//시작 메서드 끝

    //clientMap에 저장된 전체 사용자에게 메시지를 전송하는 메서드
    private void sendToAll(String msg) {
        //clientMap의 데이터 개수만큼 반복
        for(String name : clientMap.keySet()) {
            try{
                //key값과 같이 저장된 Socket객체를 이용한 출력용 스트림 객체 생성
                DataOutputStream dout = new DataOutputStream(
                        clientMap.get(name).getOutputStream());
                dout.writeUTF(msg);
            }catch (Exception e) {

            }
        }
    }//sendToAll()메서드 끝...

    //---------------------------------------------------------------------------------
    //Inner Class로 서버에서 클라이언트가 보낸 메시지를 받아서
    // 접속된 모든 클라이언트들에게 보내는 Thread 클래스 작성
    //  => Inner Class로 작성하는 이유는 Out Class의
    //      멤버들을 자유롭게 사용하기 위해
    class ServerReceiver extends Thread {
        private Socket socket;
        private DataInputStream din;
        private DataOutputStream dout;

        public ServerReceiver(Socket socket) {
            this.socket = socket;
            try {
                //수신용
                din = new DataInputStream(this.socket.getInputStream());
                //송신용
                dout = new DataOutputStream(this.socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }//생성자 끝...

        @Override
        public void run() {
            String name = "";
            try {
                //클라이언트가 연결이 성공하면 첫번째 데이터로 '대화명'을 입력 받아
                //전송한다. 서버는 이'대화명'이 중복되는지 여부를 검사하여 그 결과를
                //클라이언트에게 응답으로 보낸다.

                //클라이언트가 보낸'대화명'이 중복되지 않을 때까지 반복
                while(true) {
                    name = din.readUTF();   //클라이언트가 보낸'대화명'받기

                    if(clientMap.containsKey(name)) {
                        //중복되면 메시지 보내기
                        dout.writeUTF("중복된 이름입니다.");
                    }else {
                        dout.writeUTF("OK");
                        break;
                    }
                }
                //접속한 사람의 대화명을 이용하여 다른 전체 클라이언트에게 대화방 참여 메시지
                //전송하기
                sendToAll("[" + name + "] 님이 대화방에 입장했습니다...");

                //대화명과 접속한 클라이언트의 Socket객체를 Map에 추가한다.
                clientMap.put(name, socket);

                System.out.println("현재 접속자 수: " + clientMap.size() + "명");

                //한 클라이언트가 보낸 메시지를 받아서 전체 클라이언트들에게 보내기
                while(din!=null) {
                    sendToAll(din.readUTF());
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                //finally영역이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미이다.

                //사용자 목록(clientMap)에서 해당 대화명을 삭제한다.
                clientMap.remove(name);

                sendToAll("[" + name + "] 님이 접속을 종료했습니다...");

                System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속을 종료했습니다...");

                System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
                System.out.println();
            }
        }
    }//ServerReceiver 끝...
}
