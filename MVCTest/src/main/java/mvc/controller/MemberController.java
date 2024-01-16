package mvc.controller;

import mvc.service.IMemberService;
import mvc.service.MemberServiceImpl;
import mvc.vo.MemberVo;

import java.util.List;
import java.util.Scanner;

public class MemberController {
    //Service객체의 참조값이 저장될 변수 선언
    private IMemberService service;
    private Scanner sc;

    //생성자
    public MemberController() {
        sc = new Scanner(System.in);
        //service객체 생성
        service = new MemberServiceImpl();
    }
    public static void main(String[] args) {
        new MemberController().mainMenu();
    }

    //시작 메서드
    public void mainMenu() {
        while (true) {
            int sel = displayMenu();
            switch (sel) {
                case 1:
                    insertMember();
                    break;
                case 2:
                    deleteMember();
                    break;
                case 3:
                    updateMember();
                    break;
                case 4:
                    getAllMember();
                    break;
                case 5:
                   // selUpdate();
                    break;
                case 0:
                    System.out.println("프로그램 종료...");
                    return;
                default:
                    System.out.println("다시 입력해 주세요");
                    break;
            }
        }
    }
    private int displayMenu() {
        System.out.println("== 회원 관리 프로그램 ==");
        System.out.println("        1. 회원 정보 추가     ");
        System.out.println("        2. 회원 정보 삭제     ");
        System.out.println("        3. 회원 정보 수정     ");
        System.out.println("        4. 전체 회원 정보 출력 ");
        System.out.println("        5. 원하는 항목 수정 ");
        System.out.println("        0. 작업 끝       ");

        System.out.print("메뉴 번호 입력 >> ");
        int sel = sc.nextInt();
        return sel;
    }

    //insert
    private void insertMember() {
        System.out.println("== 회원정보 추가 메뉴 ==");
        //회원아이디 중복 체크
        String memId = null;
        while (true) {
            System.out.print("회원 아이디 >> ");
            memId = sc.next();
            int cnt = service.getMemberCount(memId);
            if(cnt == 0) {
                System.out.println("사용 가능한 아이디 입니다.");
                break;
            }else System.out.println("존재하는 아이디 입니다.");
        }
        System.out.print("회원 비밀번호 >> ");
        String memPass = sc.next();
        System.out.print("회원 이름 >>");
        String memName = sc.next();
        System.out.print("회원 전화번호 >> ");
        String memTel = sc.next();
        sc.nextLine();
        System.out.print("회원 주소 >> ");
        String memAddr = sc.nextLine();

        //회원가입
        MemberVo memVo = new MemberVo();
        memVo.setMem_id(memId);
        memVo.setMem_pass(memPass);
        memVo.setMem_name(memName);
        memVo.setMem_tel(memTel);
        memVo.setMem_addr(memAddr);

        int suc = service.insertMember(memVo);
        if(suc > 0) System.out.println("회원등록완료!!");
        else System.out.println("작업 실패~~~");

    }

    //delete
    private void deleteMember() {
        System.out.println("== 회원 정보 삭제 페이지 ==");
        //회원전체리스트 출력
        getAllMember();

        System.out.println("삭재할 회원 아이디를 입력하세요");
        //회원아이디 중복 체크
        String memId = null;
        while (true) {
            System.out.print("회원 아이디 >> ");
            memId = sc.next();
            int cnt = service.getMemberCount(memId);
            if(cnt > 0) {
                System.out.println("존재하는 아이디 입니다.");
                break;
            }else System.out.println("존재하지 않는 아이디입니다.");
        }

        //회원정보 삭제함수 호출
        int suc = service.deleteMember(memId);

        if(suc > 0) System.out.println("회원삭제완료!!");
        else System.out.println("작업 실패~~~");
    }

    //update
    private void updateMember() {
        System.out.println("== 회원 정보 수정 페이지 ==");
        getAllMember();
        System.out.println("수정할 회원 아이디를 입력하세요");
        //회원아이디 중복 체크
        String memId = null;
        while (true) {
            System.out.print("회원 아이디 >> ");
            memId = sc.next();
            int cnt = service.getMemberCount(memId);
            if(cnt > 0) {
                System.out.println("존재하는 아이디 입니다.");
                break;
            }else System.out.println("존재하지 않는 아이디입니다.");
        }

        System.out.print("회원 비밀번호 >> ");
        String memPass = sc.next();
        System.out.print("회원 이름 >>");
        String memName = sc.next();
        System.out.print("회원 전화번호 >> ");
        String memTel = sc.next();
        sc.nextLine();
        System.out.print("회원 주소 >> ");
        String memAddr = sc.nextLine();

        MemberVo memVo = new MemberVo();
        memVo.setMem_id(memId);
        memVo.setMem_pass(memPass);
        memVo.setMem_name(memName);
        memVo.setMem_tel(memTel);
        memVo.setMem_addr(memAddr);

        int suc = service.updateMember(memVo);

        if(suc > 0) System.out.println("회원업데이트완료!!");
        else System.out.println("작업 실패~~~");

    }
    //select
    private void getAllMember() {
        List<MemberVo> memList = service.getAllMember();

        System.out.println("회원 아이디\t회원 비밀번호\t회원 이름\t회원 전화번호\t회원 주소");
        System.out.println("----------------------------------------------------------");
        if(memList.isEmpty()){
            System.out.println("회원정보가 없습니다.");
        }
        for(MemberVo my : memList) {
            String memId = my.getMem_id();
            String memPass = my.getMem_pass();
            String memName = my.getMem_name();
            String memTel = my.getMem_tel();
            String memAddr = my.getMem_addr();
            System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
        }
        System.out.println("----------------------------------------------------------");
    }
}
