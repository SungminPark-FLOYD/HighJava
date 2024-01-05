package xmlTest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;

public class DOMAddDataExample {
    public static void main(String[] args) {
        try {
            // XML 파일 로딩
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("./JavaIOTest/src/main/java/xmlTest/example.xml");

            // 새로운 book 엘리먼트 생성
            Element newBook = document.createElement("book");
            newBook.setAttribute("id", "bk113");

            Element author = document.createElement("author");
            author.setTextContent("남궁성");
            newBook.appendChild(author);

            Element title = document.createElement("title");
            title.setTextContent("자바의정석");
            newBook.appendChild(title);

            Element genre = document.createElement("genre");
            genre.setTextContent("IT");
            newBook.appendChild(genre);

            Element price = document.createElement("price");
            price.setTextContent("9.99");
            newBook.appendChild(price);

            Element publishDate = document.createElement("publish_date");
            publishDate.setTextContent("2024-01-05");
            newBook.appendChild(publishDate);

            Element description = document.createElement("description");
            description.setTextContent("자바의 모든것");
            newBook.appendChild(description);

            // 루트 엘리먼트에 새로운 book 추가
            Element rootElement = document.getDocumentElement();
            rootElement.appendChild(newBook);

            // 수정된 내용을 파일로 저장
            // 여러 방법 중 하나로 Transformer 사용
            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new java.io.File("./JavaIOTest/src/main/java/xmlTest/example.xml"));
            transformer.transform(source, result);

            System.out.println("데이터 추가 성공!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
