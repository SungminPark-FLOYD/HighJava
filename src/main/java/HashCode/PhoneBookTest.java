package HashCode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class PhoneBookTest {
    /*문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고
	Map을 이용하여 '전화번호 정보'를 관리하는 프로그램을 작성하시오.
	(이 때 Map의 구조는 key값으로 '이름'데이터를 사용하고,
	 value값으로는 'Phone클래스의 인스턴스'로 한다.)
	 HashMap<String, Phone>

	아래의 메뉴를 처리하는 프로그램을 완성하시오*/


    private Map<String, Phone> map = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    //생성자
//    public PhoneBookTest() {
//        sc = new Scanner(System.in);
//        map = new HashMap<String, Phone>();
//    }
    public static void main(String[] args) {
        PhoneBookTest p = new PhoneBookTest();
        p.selectMenu();
    }

    private void selectMenu() {
        while (true) {
            System.out.println("메  뉴");
            System.out.println("1. 전화번호 등록");
            System.out.println("2. 전화번호 수정");
            System.out.println("3. 전화번호 삭제");
            System.out.println("4. 전화번호 검색");
            System.out.println("5. 전화번호 전체 출력");
            System.out.println("0. 프로그램 종료");
            System.out.println("------------------	");
            System.out.println("번호 입력 >> ");
            int sel = sc.nextInt();
            if(sel == 1) insertTel();
            else if(sel == 2) updateTel();
            else if(sel == 3) deleteTel();
            else if(sel == 4) searchTel();
            else if(sel == 5) printTel();
            else if(sel == 0) break;
            else System.out.println("다시 입력해 주세요!!");
        }
    }

    private void printTel() {
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();

        System.out.println("----------------------------------------");
        System.out.println("번호\t이름\t전화번호\t주소");
        int i = 1;
        while(it.hasNext()) {
            String key = it.next();
            Phone p = map.get(key);

            String name = p.getName();
            String tel = p.getTel();
            String addr = p.getAddr();

            System.out.println(i + "\t" + name + "\t" + tel + "\t" +addr);
            i++;
        }

        System.out.println("----------------------------------------");
        System.out.println("출력 끝...");
    }

    private void searchTel() {
        System.out.println("----------------------------------------");
        System.out.print("검색할 사람을 입력하세요 >> ");
        String str = sc.next();


        Phone p = map.get(str);

        String name = p.getName();
        String tel = p.getTel();
        String addr = p.getAddr();

        System.out.println("----------------------------------------");
        System.out.println("이름\t전화번호\t주소");
        System.out.println(name + "\t" + tel + "\t" +addr);
        System.out.println("----------------------------------------");
        System.out.println("출력 끝...");


    }

    private void deleteTel() {
        if(map.isEmpty())  {
            System.out.println("수정할 사람이 없습니다!!");
            selectMenu();
        }


        System.out.println("----------------------------------------");
        boolean upChk;
        String str;
        do {
            System.out.print("삭제할 사람을 입력하세요 >> ");
            str = sc.next();
            upChk = upOk(str);

//            if(map.containsKey(str)) {
//                System.out.println("존재하는 사람");
//            }

        }while(!upChk);

        map.remove(str);

        System.out.println("삭제 완료!");
        System.out.println("----------------------------------------");

    }

    private void updateTel() {
        if(map.isEmpty())  {
            System.out.println("수정할 사람이 없습니다!!");
            selectMenu();
        }

        boolean upChk;
        String str;
        do {
            System.out.print("수정할 사람을 입력하세요 >> ");
            str = sc.next();
            upChk = upOk(str);

        }while(!upChk);

        System.out.println("----------------------------------------");
        System.out.println("수정할 전화번호를 입력하세요");


        System.out.print("전화번호 >> ");
        String tel = sc.next();

        Phone update = map.get(str);

        update.setTel(tel);

        System.out.println("정보 수정 완료!!");
        System.out.println("----------------------------------------");

    }
    private boolean upOk(String value) {

        for(String val : map.keySet()) {
            if(val.equals(value))  {
                System.out.println("존재하는 정보 입니다.");
                return true;
            }
        }

        System.out.println("수정할 사람이 없습니다!!");
        return false;
    }
    private boolean valOk(String value) {
        if(map.isEmpty()) return true;

        for(String val : map.keySet()) {
            if(val.equals(value))  {
                System.out.println("존재하는 정보 입니다.");
                return false;
            }
        }
        return true;
    }
    private void insertTel() {


        System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
        boolean b;
        String name;
         do{
            System.out.print("이름 >>");
            name = sc.next();
            b = valOk(name);
        }while(!b);

        System.out.print("전화번호 >>");
        String tel = sc.next();
        b = valOk(tel);

        sc.nextLine();
        System.out.print("주소 >>");
        String addr = sc.nextLine();

//        phone.setName(name);
//        phone.setTel(tel);
//        phone.setAddr(addr);

        Phone phone = new Phone(name, tel, addr);
        map.put(name, phone);

        System.out.println("'" +name + "' " + "전화번호 등록 완료!!");



    }

}

@Data
class Phone {
    String name;
    String tel;
    String addr;

    public Phone(String name, String tel, String addr) {
        this.name = name;
        this.tel = tel;
        this.addr = addr;
    }
}
