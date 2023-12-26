package HashCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
    public static void main(String[] args) {
        /*Map : key와 value를 한 쌍으로 관리하는 객체
        * key값은 중복을 허용하지 않고 순서도 없다 => Set
        * value값은 중복을 허용한다*/

        HashMap<String, String> map = new HashMap<>();

        //자료 츄가 => put(k,v)
        map.put("name", "홍길동");
        map.put("addr", "대전");
        map.put("tell", "010-1234-1234");

        System.out.println(map);

        //자료 수정 : 같은 key값인 데이터를 추가하면 나중에 추가한 내용이 저장된다.
        map.put("addr", "집");
        System.out.println(map);

        //자료삭제 : remove(key) key값의 자료를 찾아 삭제
        //=> 반환값 : 삭제된 자료의 value값 / 삭제 실패시 null
//        String removeTel = map.remove("tell");
//        System.out.println(removeTel);

        //자료읽기 : get(key)
        //=>반환값 : 주어진 key값 과 짝이 되는 value값을 반환/ 실패시 null
        System.out.println(map.get("name"));
        System.out.println(map);

        //key값이 존재하는지 여부를 나타내는 메서드 => containsKey(key)
        //=> 해당 key값이 있으면 true, 없으면 false를 반환한다.
        System.out.println(map.containsKey("name"));
        
        //Map에 저장된 모든 데이터를 차례로 읽어와 사용하는 방법

        //1. 모든 key값을 읽어와 처리하기 => keySet()메서드 이용하기
        //keySet()메서드 => Map의 모든 key값들을 읽어와 Set형으로 반한다.
        Set<String> keySet = map.keySet();

        //방법1
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()) {
            String key = it.next();     //키 구하기
            String value = map.get(key);
            System.out.println(key + "==>" +value);
        }

        //방법2
        for(String key : keySet) {
            String val = map.get(key);
            System.out.println(key + "==>" +val);
        }

        //2. 모든 value값들만 읽어와 처리하기 ==> values메서드 이용
        for(String val : map.values()) {
            System.out.println(val );
        }
    }
}
