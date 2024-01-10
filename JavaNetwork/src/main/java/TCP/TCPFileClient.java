package TCP;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.net.Socket;


public class TCPFileClient {
    private Socket socket;
    private BufferedInputStream bin;
    private BufferedOutputStream bout;
    private DataOutputStream dout;
    //서버에 접속하면 'd:/d_other/펭귄.jpg'파일을 서버로 전송한다.
    public static void main(String[] args) {
        new TCPFileClient().clientStart();
    }

    public void clientStart() {
        //전송할 파일 정보를 갖는 File객체 생성
//        File file = new File("d:/d_other/펭귄.jpg");
        File file = chooseFile("OPEN");
        if(file == null) {
            System.out.println("전송할 파일을 선택해야 합니다...");
            return;
        }
        String fileName = file.getName();       //파일이름 구하기

        if(!file.exists()) {
            //전송할 파일이 있는지 검사
            System.out.println(fileName + "파일이 없습니다...");
            System.out.println("파일 전송 작업을 중단합니다...");
            return;
        }

        try {
            socket = new Socket("localhost", 7777);

            System.out.println("파일 전송 시작...");

            //서버에 접속하면 첫번째로 '파일명'을 전송한다.
            dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(fileName);

            //파일 읽기용 스트림 객체 생성
            bin = new BufferedInputStream(new FileInputStream(file));
            //전송용 스트림 객체 생성
            bout = new BufferedOutputStream(socket.getOutputStream());

            //파일을 읽어서 Socket으로 전송(출력)하기
            byte[] temp = new byte[1024];
            int length = 0;
            while ((length = bin.read(temp)) != -1){
                bout.write(temp, 0, length);
            }
            bout.flush();

            System.out.println("파일 전송 완료...");
        }
        catch (Exception e) {
            System.out.println("파일 전송 실패~~");
            e.printStackTrace();
        }finally {
            //사용했던 스트림과 Socket 닫기
            if(dout!=null) try { dout.close();} catch (IOException e) {}
            if(bout!=null) try { bout.close();} catch (IOException e) {}
            if(bin!=null) try { bin.close();} catch (IOException e) {}
            if(socket!=null) try { socket.close();} catch (IOException e) {}
        }
    }
    public File chooseFile(String option){
        //Swing의 파일열기, 저장 창 연습

        JFileChooser chooser = new JFileChooser();

        //선택한 파일의 확장자 설정하기
        FileNameExtensionFilter txt =
                new FileNameExtensionFilter("text파일(*.txt)","txt");
        FileNameExtensionFilter img =
                new FileNameExtensionFilter("이미지파일",
                        new String[]{"png", "jpg", "gif"});
        FileNameExtensionFilter doc =
                new FileNameExtensionFilter("MS워드","doc", "docx");

        chooser.addChoosableFileFilter(txt);
        chooser.addChoosableFileFilter(img);
        chooser.addChoosableFileFilter(doc);

        //확장자 목록 중 기본적으로 선택될 확장자 지정하기
        chooser.setFileFilter(img);

        //Dialog창에 보여줄 기본 디렉토리(폴더) 설정
        chooser.setCurrentDirectory(new File("D:/D_Other"));
        int result;
        if("SAVE".equals(option.toUpperCase())){
            result = chooser.showSaveDialog(new Panel());      //저장용 창
        }else if("OPEN".equals(option.toUpperCase())) {
            result = chooser.showOpenDialog(new Panel());       //열기용 창
        }else {
            System.out.println("Option은 'Save'또는 'OPEN'만 가능합니다");
            return null;
        }
        File selectedFile = null;
        //dialog창에서 '열기' 또는 '저장 버튼을 눌렀을경우//
        if(result == JFileChooser.APPROVE_OPTION) {
            //Dialog창에서 선택한 파일 정보를 가져와
            //실제 '저장' 또는 '읽기'작업을 수행한다
            selectedFile = chooser.getSelectedFile();
        }
        return selectedFile;
    }
}
