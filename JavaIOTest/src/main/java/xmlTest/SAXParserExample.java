package xmlTest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserExample {
    public static void main(String[] args) {
        try {
            // XML 파일 로딩
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // 이벤트 핸들러 정의
            DefaultHandler handler = new DefaultHandler() {
                boolean inBook = false;
                StringBuilder data = new StringBuilder();

                /**
                 * startElement 메서드는 XML 문서의 시작 태그를 만날 때 호출됩니다.
                 * 여기서는 <book> 태그를 만나면 inBook 플래그를 활성화하여 현재 파싱 중인 책 정보에 대한 처리를 시작합니다.
                 * @param uri 네임스페이스 URI이거나,
                 *                   요소에 네임스페이스 URI가 없거나 네임스페이스인 경우
                 *                   처리가 수행되지 않습니다.
                 * @param localName 로컬 이름(접두사 없음) 또는
                 *                   네임스페이스 처리가 진행되지 않는 경우 빈 문자열
                 *                   수행.
                 * @param qName 정규화된 이름(접두사 포함) 또는
                 *        정규화된 이름을 사용할 수 없는 경우 빈 문자열입니다.
                 * @param attributes 요소에 첨부된 속성입니다. 만약에
                 *        속성이 없습니다. 비어 있어야 합니다.
                 *        속성 객체.
                 * @throws SAXException
                 */
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("book")) {
                        inBook = true;
                    }
                }

                /**
                 * characters 메서드는 현재 태그의 텍스트 데이터를 만날 때 호출됩니다.
                 * 여기서는 data StringBuilder에 텍스트 데이터를 추가합니다.
                 * @param ch 문자.
                 * @param start 문자 배열의 시작 위치입니다.
                 * @param length 에서 사용할 문자 수입니다.
                 *               문자 배열.
                 * @throws SAXException
                 */
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    data.append(new String(ch, start, length).trim());
                }

                /**
                 * endElement 메서드는 XML 문서의 끝 태그를 만날 때 호출됩니다.
                 * 여기서는 </book> 태그를 만나면 inBook 플래그를 비활성화하고,
                 * data StringBuilder에 저장된 텍스트 데이터를 출력합니다.
                 * 
                 * @param uri 네임스페이스 URI이거나,inBook이 활성화된 상태에서는
                 *        책의 각 속성 (author, title, genre 등)에 대한 정보를 출력합니다.
                 *        요소에 네임스페이스 URI가 없거나 네임스페이스인 경우
                 *        처리가 수행되지 않습니다.
                 * @param localName 로컬 이름(접두사 없음) 또는
                 *        네임스페이스 처리가 진행되지 않는 경우 빈 문자열
                 *        수행.
                 * @param qName 정규화된 이름(접두사 포함) 또는
                 *        정규화된 이름을 사용할 수 없는 경우 빈 문자열입니다.
                 * @throws SAXException
                 */
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("book")) {
                        inBook = false;

                        // 각 book 정보 출력
                        System.out.println("Book ID: " + data.toString());
                        data.setLength(0); // StringBuilder 초기화
                    } else if (inBook) {
                        System.out.println(qName + ": " + data.toString());
                        data.setLength(0); // StringBuilder 초기화
                    }
                }
            };

            // 파싱 실행
            saxParser.parse("./JavaIOTest/src/main/java/xmlTest/example.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
