package JavaIOTest;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
    D:/D_Other폴더에있는 펭귄.jpg파일을
    D:/D_Other/연습용 폴더에 복사본_펭귄.jpg파일로 복사하는 프로그램
 */
public class FileCopyDialogTest {
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
    public static void main(String[] args) {
        FileCopyDialogTest test = new FileCopyDialogTest();
//        File fi = new File("D:/D_Other/펭귄.jpg");
        File fi = test.chooseFile("OPEN");
        if(fi == null) {
            System.out.println("원본 파일을 선택하세요..");
            System.out.println("복사작업을 중지합니다..");
            return;
        }

        //파일 유무 체크
        if(!fi.exists()) {
            System.out.println(fi.getPath() + " 파일이 없습니다.");
            System.out.println("복사작업을 중지합니다..");
            return;
        }
        try {
            //원본파일을 읽어올 스트림객체 생성
            FileInputStream fin = new FileInputStream(fi);
            //대상파일 선택
            File targetFile = test.chooseFile("SAVE");
            if(targetFile == null) {
                System.out.println("대상 파일을 지정하지 않았습니다.");
                System.out.println("복사작업을 중지합니다...");
                return;
            }
            //대상 파일에 저장할 스트림객체 생성
            FileOutputStream fout = new FileOutputStream(targetFile);

            System.out.println("복사 작업 시작 ...");

            int c;
            while((c = fin.read()) != -1) {
                fout.write(c);
            }
            System.out.println("작업완료!");
            fin.close();
            fout.close();
        }catch (IOException e) {

        }





    }
}
