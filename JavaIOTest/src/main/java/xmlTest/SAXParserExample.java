package xmlTest;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXParserExample {
    public static void main(String[] args) {
        try {
            // XML 파일 로딩
            File xmlFile = new File("D:\\D_setting\\intelij_workspace\\HighJava\\JavaIOTest\\src\\main\\java\\xmlTest\\example.xml");

            // SAX 파서 생성
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // 이벤트 핸들러 정의
            DefaultHandler handler = new DefaultHandler() {
                boolean itemFlag = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("item")) {
                        itemFlag = true;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (itemFlag) {
                        System.out.println("Item: " + new String(ch, start, length));
                        itemFlag = false;
                    }
                }
            };

            // 파싱 실행
            saxParser.parse(xmlFile, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
