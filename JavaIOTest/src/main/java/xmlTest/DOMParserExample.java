package xmlTest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMParserExample {
    public static void main(String[] args) {
        try {
            // XML 파일 로딩
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("D:\\D_setting\\intelij_workspace\\HighJava\\JavaIOTest\\src\\main\\java\\xmlTest\\example.xml");

            // 루트 엘리먼트 획득
            Element rootElement = document.getDocumentElement();

            // 자식 노드 목록 얻기
            NodeList nodeList = rootElement.getElementsByTagName("item");

            // 각 아이템 출력
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);
                String value = item.getTextContent();
                System.out.println("Item: " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
