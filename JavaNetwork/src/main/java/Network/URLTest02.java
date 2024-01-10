package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {
    public static void main(String[] args) throws IOException {
        //URLConnection 클래스 ==>
        //  어플리케이션과 URL 같의 통신 연결을 위한 클래스

        //특정 서버의 정보와 파일 내용을 가져오는 예제
        URL url = new URL("https://www.google.com/index.html");

        //URLConnection객체 구하기(URL 객체를 통해서 구할 수 있다)
        URLConnection urlCon = url.openConnection();

        //Header정보 가져오기
        Map<String, List<String>> headerMap = urlCon.getHeaderFields();

        for(String headerKey : headerMap.keySet()) {
            System.out.println(headerKey + " : "  + headerMap.get(headerKey));
        }
        System.out.println("-------------------------------------------------");

        URLTest02 o = new URLTest02();
        o.URLTest(urlCon);
        o.URLOpenStream(url);


    }

    public void URLTest(URLConnection urlCon) throws IOException {
        //URL에 지정한 문서의 내용 가져오기 ==> index.html 문서의 내용 가져오기

        //방법1) URLConnection객체를 이용하는 방법

        //파일 내용을 가져오기 위한 스트림객체를 구한다
        InputStream in = urlCon.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        BufferedReader br = new BufferedReader(isr);

        //파일 내용을 읽어와 출력하기
        while (true) {
            String str = br.readLine();
            if(str == null) break;
            System.out.println(str);
        }
        System.out.println("-------------------------------------------------");

        br.close();
    }

    public void URLOpenStream(URL url) throws IOException {
        //방법2 => URL 객체의 openStream()메서드 이용하기
        InputStream in2 = url.openStream();
        BufferedReader br2 = new BufferedReader(
                new InputStreamReader(in2, "utf-8")
        );

        while (true) {
            String str2 = br2.readLine();
            if(str2 == null) break;
            System.out.println(str2);
        }

        br2.close();
    }
}
