package TCP;

import com.sun.security.ntlm.Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TCPFileServer {
    //클라이언트가 보낸 파일을 받아서 'd:/d_other/upload'폴더에 저장한다.
    private ServerSocket server;
    private Socket socket;
    public BufferedInputStream bin;
    public BufferedOutputStream bout;
    private DataInputStream din;

    public static void main(String[] args) {
        new TCPFileServer().serverStart();
    }

    private void serverStart() {
        //저장할 폴더 정보를 갖는 File객체 생성
        File saveDir = new File("d:/d_other/upload");

        //저장할 폴더가 없으면 새로 생성한다
        if(!saveDir.exists()) {
            saveDir.mkdirs();
        }

        try{
            server = new ServerSocket(7777);
            System.out.println("서버가 준비되었습니다...");
            socket = server.accept();

            System.out.println("파일 저장 시작...");

            //클라이언트가 첫번째로 보내온 '파일명'을 받는다.
            din = new DataInputStream(socket.getInputStream());
            String fileName = din.readUTF();

            //저장할 폴더와 파일명을 지정하여 File객체 생성하기
            File saveFile = new File(saveDir, fileName);

            //수신용 스트림 객체 생성
            bin = new BufferedInputStream(socket.getInputStream());
            //파일 저장용 스트림 객체 생성
            bout = new BufferedOutputStream(
                    new FileOutputStream(saveFile));

            //Socket으로 수신(읽기)한 데이터를 파일로 출력하기
            byte[] temp = new byte[1024];
            int length = 0;
            while ((length = bin.read(temp)) != -1){
                bout.write(temp, 0, length);
            }
            bout.flush();

            System.out.println("파일 저장 완료...");
        }
        catch (Exception e) {
            System.out.println("파일 수신 작업 실패~~");
            e.printStackTrace();
        }finally {
            //사용했던 스트림과 Socket 닫기
            if(din!=null) try { din.close();} catch (IOException e) {}
            if(bout!=null) try { bout.close();} catch (IOException e) {}
            if(bin!=null) try { bin.close();} catch (IOException e) {}
            if(server!=null) try { server.close();} catch (IOException e) {}
        }
    }
}
