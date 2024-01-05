package JavaIOTest;

import lombok.Data;

import java.io.*;
import java.util.*;

public class PhoneBookTest {
    /*문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고
	Map을 이용하여 '전화번호 정보'를 관리하는 프로그램을 작성하시오.
	(이 때 Map의 구조는 key값으로 '이름'데이터를 사용하고,
	 value값으로는 'Phone클래스의 인스턴스'로 한다.)
	 HashMap<String, Phone>

	아래의 메뉴를 처리하는 프로그램을 완성하시오

	- 추가 조건
	1) 6. 전화번호 저장 메뉴를 추가하고 구현한다
	    (저장 파일명 : phoneBook.data)
	2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
	3) 프로그램을 종료할 때 Map의 데이터가 변경(데이터 추가 / 수정 / 삭제)되었으면
	   파일로 저장 후 종료*/



    private Map<String, Phone> map = new HashMap<>();
    //생성자에서 map = load(); 호출 null일 경우 대비해서 체크하기 (null이면 new HashMap<>();)
    private boolean chk = false;
    Scanner sc = new Scanner(System.in);

    //생성자
//    public PhoneBookTest() {
//        sc = new Scanner(System.in);
//        map = new HashMap<String, Phone>();
//    }
    public static void main(String[] args) {
        PhoneBookTest p = new PhoneBookTest();
        p.printList();
        p.selectMenu();
        p.lastSave();
    }

    private void lastSave() {
        if(chk == false) {
            System.out.println("수정사항 없음!!");
            return;
        }
        ObjectOutputStream oout = null;
        try {
             oout = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("D:/D_Other/phoneBook.data"))
            );

            for(String key : map.keySet()) {
                Phone p = map.get(key);
                p.setReg("저장완료");
                oout.writeObject(p);
            }
            //oout.writeObject(map);으로 map자체 저장 가능
            oout.writeObject(null);

            System.out.println("등록완료!!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(oout!=null) try {
                oout.close();
            }catch (IOException e) {}
        }
    }

    private void selectMenu() {
        while (true) {
            System.out.println("메  뉴");
            System.out.println("1. 전화번호 등록");
            System.out.println("2. 전화번호 수정");
            System.out.println("3. 전화번호 삭제");
            System.out.println("4. 전화번호 검색");
            System.out.println("5. 전화번호 전체 출력");
            System.out.println("6. 전화번호 저장");
            System.out.println("0. 프로그램 종료");
            System.out.println("------------------	");
            System.out.println("번호 입력 >> ");
            int sel = sc.nextInt();
            if(sel == 1) insertTel();
            else if(sel == 2) updateTel();
            else if(sel == 3) deleteTel();
            else if(sel == 4) searchTel();
            else if(sel == 5) printTel();
            else if(sel == 6) saveTel();
            else if(sel == 0) break;
            else System.out.println("다시 입력해 주세요!!");
        }
    }

    private void saveTel() {
        printTel();
        System.out.println("저장할 이름을 입력해 주세요!");
        String name = sc.next();
        Phone p = map.get(name);
        p.setReg("저장완료");
        try {
            ObjectOutputStream oout = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("D:/D_Other/phoneBook.data"))
            );

            oout.writeObject(p);
            oout.writeObject(null);

            System.out.println("등록완료!!");
            chk = false;
            oout.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void printTel() {
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();

        System.out.println("----------------------------------------");
        System.out.println("번호\t이름\t전화번호\t주소\t저장가능여부");
        int i = 1;
        while(it.hasNext()) {
            String key = it.next();
            Phone p = map.get(key);

            String name = p.getName();
            String tel = p.getTel();
            String addr = p.getAddr();
            String reg = p.getReg();

            System.out.println(i + "\t" + name + "\t" + tel + "\t" +addr + "\t" + reg);
            i++;
        }

        System.out.println("----------------------------------------");
        System.out.println("출력 끝...");
    }

    //시작할때 파일에 저장된 데이터 불러오기
    private HashMap<String, Phone> load() {
        HashMap<String, Phone> pMap = null;     //읽어온 데이터 저장될 변수
        //파일 유무 체크
        File file = new File("D:/D_Other/phoneBook.data");
        if(!file.exists()) {
            return null;
        }
        
        ObjectInputStream oin = null;

        try {
            oin = new ObjectInputStream((new BufferedInputStream(
                    new FileInputStream(file)
            )));
            //객체 하나하나 저장했을때
//            pMap = (HashMap<String, Phone>) oin.readObject();
            //Map전체를 한번에 저장했을때
            Object obj;
            //파일에서 읽어온 데이터가 저장될 map생성
            pMap = new HashMap<String, Phone>();
            while((obj = oin.readObject() )!= null) {
                Phone p = (Phone) obj;
                pMap.put(p.getName(), p);
            }
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if(oin!=null) try {oin.close();} catch (IOException e) {}
        }
        
        return pMap;
    }
    //정보가 저장된 파일 읽어오는 메소드
    private void printList() {
        try{
            //입력용 스트림 객체 생성
            ObjectInputStream oin = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream("D:/D_Other/phoneBook.data")
                    )
            );

            Object obj = null;      //읽어온 객체가 저장될 변수
            //readObject()메서드가 데이터를 끝까지 다 읽어오면 EOFException이 발생한다.
            while ((obj=oin.readObject()) != null) {
                //읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
                Phone p = (Phone) obj;
                map.put(p.getName(), p);
            }

            oin.close();
            System.out.println();
        }
        catch (EOFException e) {
            //다읽으면 메세지 띄우기
            //오류발생했을때
            System.out.println("EOF ==> 객체 읽기 작업 끝");
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

        printTel();
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
        chk = true;
        System.out.println("삭제 완료!");
        System.out.println("----------------------------------------");

    }

    private void updateTel() {
        if(map.isEmpty())  {
            System.out.println("수정할 사람이 없습니다!!");
            selectMenu();
        }
        printTel();
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
        chk = true;
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

        String reg = "저장가능";
        
        Phone phone = new Phone(name, tel, addr, reg);
        map.put(name, phone);
        chk = true;
        System.out.println("'" +name + "' " + "전화번호 등록 완료!!");



    }

}


class Phone implements Serializable {
    private static final long serialVersionUID = 5221231530269439163L;
    String name;
    String tel;
    String addr;
    String reg;

    public Phone(String name, String tel, String addr, String reg) {
        this.name = name;
        this.tel = tel;
        this.addr = addr;
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }
}
