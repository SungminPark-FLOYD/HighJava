package Map;

import lombok.Data;

import java.util.*;

public class Hotel {
    /*문제) 호텔 객실을 관리하는 프로그램을 작성하시오.

   조건1)  호텔 객식을 나타내는 Room클래스는 방번호(int), 방종류, 투숙객이름 필드로 구성되어 있고
           방번호와 방종류는 다음과 같다.
           - 201~209 : 싱글룸
           - 301~309 : 더블룸
           - 401~409 : 스위트룸

   조건2) 전체 객실 관리는 Map을 이용한다.
          (Map의 key값은 방번호로 하고 value값은 Room의 인스턴스로 한다.)
          HotelTest 생성자에서는 방번호와 방종류를 초기화한다.
          Map에 방 정보를 미리 등록한다.*/
    Map<Integer, Room> roomList;
    Scanner sc;
    int[] singleRoom = new int[]{201, 202, 203, 204, 205, 206, 207, 208, 208};
    int[] doubleRoom = new int[]{301, 302, 303, 304, 305, 306, 307, 308, 308};
    int[] suiteRoom = new int[]{401, 402, 403, 404, 405, 406, 407, 408, 409};
    public Hotel() {
        roomList = new HashMap<Integer, Room>();
        
        for(int single : singleRoom) {
            roomList.put(single, new Room(single, "싱글룸", "-"));
        }
        for(int dou : doubleRoom) {
            roomList.put(dou, new Room(dou, "더블룸", "-"));
        }
        for(int suite : suiteRoom) {
            roomList.put(suite, new Room(suite, "스위트룸", "-"));
        }
        sc = new Scanner(System.in);
    }
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("호텔문을 열었습니다. 어서오십시요.");
        System.out.println("*********************************************");
        Hotel h = new Hotel();
        h.selectMenu();

    }

    private void selectMenu() {
        while (true) {
            int sel = sel();
            switch (sel) {
                case 1:
                    checkIn();
                    break;
                case 2:
                    checkOut();
                    break;
                case 3:
                    roomCondition();
                    break;
                case 4 :
                    return;
                default:
                    System.out.println("다시 입력해 주세요!!");
                    break;
            }
        }


    }

    private void roomCondition() {
        System.out.println("----------------------------------------------");
        System.out.println("		현재 객실 상태");
        System.out.println("----------------------------------------------");
        System.out.println("방 번호	 방 종류	 투숙객 이름");

        //방 리스트 정렬
        Collection<Room> list = roomList.values();
        ArrayList<Room> li = new ArrayList<>(list);
        Collections.sort(li);

        for(Room room : li) {
            int num = room.getRoomNum();
            String con = room.getRoomVal();
            String name = room.getCustomer();

            System.out.println(num + "\t\t"+ con + "\t" +name);
        }
    }

    private void checkOut() {
        int sel = checkOutSel();

        if(!roomList.containsKey(sel)) {
            System.out.println(sel + "호 객실은 존재하지 않습니다");
            selectMenu();
        }
        else if(roomList.get(sel).getCustomer().equals("-")) {
            System.out.println(sel + "호 객실에는 체크인 한 사람이 없습니다.");
            selectMenu();
        }
        String name = roomList.get(sel).getCustomer();
        roomList.get(sel).setCustomer("-");

        System.out.println(sel+ "객실의 " + name +"님 체크아웃을 완료하였습니다.");
    }

    private void checkIn() {
        int sel = checkinSel();

        if(!roomList.containsKey(sel)) {
            System.out.println(sel + "호 객실은 존재하지 않습니다");
            selectMenu();
        }
        else if(!roomList.get(sel).getCustomer().equals("-")) {
            System.out.println(sel + "호 객실은 이미 손님이 있습니다.");
            selectMenu();
        }

        System.out.println("누구를 체크인 하시겠습니까?");
        System.out.print("이름 입력 >> ");
        String name = sc.next();

        Room room = roomList.get(sel);
        room.setCustomer(name);
        roomList.put(sel, room);

        System.out.println("체크인이 완료되었습니다.");


    }

    private int sel() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("어떤 업무를 하시겠습니까?");
        System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
        System.out.println("-----------------------------------------------------------");
        System.out.print("선택>> ");
        return sc.nextInt();
    }

    private int checkinSel() {
        System.out.println("----------------------------------------------");
        System.out.println("   체크인 작업");
        System.out.println("----------------------------------------------");
        System.out.println(" * 201~209 : 싱글룸");
        System.out.println(" * 301~309 : 더블룸");
        System.out.println(" * 401~409 : 스위트룸");
        System.out.println("----------------------------------------------");
        System.out.print("방 번호 입력 >> ");
        return sc.nextInt();
    }

    private int checkOutSel() {
        System.out.println("----------------------------------------------");
        System.out.println("   체크아웃 작업");
        System.out.println("----------------------------------------------");
        System.out.println("체크아웃 할 방 번호를 입력하세요.");
        System.out.print("방번호 입력 >> ");
        return sc.nextInt();
    }


}
@Data
class Room implements Comparable<Room> {
    int roomNum;
    String roomVal;
    String customer;

    public Room(int roomNum, String roomVal, String customer) {
        this.roomNum = roomNum;
        this.roomVal = roomVal;
        this.customer = customer;
    }

    @Override
    public int compareTo(Room o) {
        if(this.getRoomNum() < o.getRoomNum()) return -1;
        else if(this.getRoomNum() > o.getRoomNum()) return 1;
        else return 0;
    }
}


