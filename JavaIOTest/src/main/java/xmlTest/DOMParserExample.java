package xmlTest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMParserExample {
    public static void main(String[] args) {
        try {
            // XML 파일 로딩
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("./JavaIOTest/src/main/java/xmlTest/example.xml");

            // 루트 엘리먼트 획득
            Element rootElement = document.getDocumentElement();

            // book 엘리먼트 목록 얻기
            NodeList bookList = rootElement.getElementsByTagName("book");

            // 각 book 엘리먼트 순회
            for (int i = 0; i < bookList.getLength(); i++) {
                Element bookElement = (Element) bookList.item(i);
                String id = bookElement.getAttribute("id");
                String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                String genre = bookElement.getElementsByTagName("genre").item(0).getTextContent();
                double price = Double.parseDouble(bookElement.getElementsByTagName("price").item(0).getTextContent());
                String publishDate = bookElement.getElementsByTagName("publish_date").item(0).getTextContent();
                String description = bookElement.getElementsByTagName("description").item(0).getTextContent();

                // 각 book 정보 출력
                System.out.println("Book ID: " + id);
                System.out.println("Author: " + author);
                System.out.println("Title: " + title);
                System.out.println("Genre: " + genre);
                System.out.println("Price: " + price);
                System.out.println("Publish Date: " + publishDate);
                System.out.println("Description: " + description);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
