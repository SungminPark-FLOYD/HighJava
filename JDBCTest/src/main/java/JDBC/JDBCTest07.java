package JDBC;

import Util.DBUtil;
import Util.DBUtil2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
        회원을 관리하는 프로그램을 작성하시오 (mymember)

        아래 메뉴의 기능을 모두 구현하시오(crud 기능 구현 연습)
        메뉴 예시
        1. 자료 추가 ==> insert(c)
        2. 자료 삭제 ==> delete(d)
        3. 자료 수정 ==> update(u)
        4. 전체 자료 출력 ==> select(r)
        0. 작업 끝

        조건
        1. 자료 추가에서 '회원ID'는 중복되지 않는다 (중복되면 다시 입력받기)
        2. 자료 삭제는 '회원ID'를 입력받아서 처리한다
        3. 자료 수정에서 '회원Id'는 변경되지 않는다.
 */
public class JDBCTest07 {
    Scanner sc = new Scanner(System.in);

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        new JDBCTest07().mainMenu();
    }

    public void mainMenu() {
        while (true) {
            int sel = displayMenu();
            switch (sel) {
                case 1:
                    insert();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    select();
                    break;
                case 5:
                    selUpdate();
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

    private void selUpdate() {
        System.out.println("== 회원 정보 수정 페이지 ==");
        select();
        System.out.println("수정할 회원 아이디를 입력하세요");
        //회원아이디 중복 체크
        String memId = null;
        while (true) {
            System.out.print("회원 아이디 >> ");
            memId = sc.next();
            if(!idChk(memId)) {
                System.out.println("존재하는 아이디 입니다.");
                break;
            }else System.out.println("존재하지 않는 아이디입니다.");
        }


        int sel = editMenu();
        MyMember myMember = new MyMember();
        myMember.setMemId(memId);
        switch (sel){
            case 1:
                System.out.print("비밀번호 입력");
                String memPass = sc.next();
                myMember.setMemPass(memPass);
                editMem(myMember, sel);
                break;
            case 2:
                System.out.print("이름 입력");
                String memName = sc.next();
                myMember.setMemName(memName);
                editMem(myMember, sel);
                break;
            case 3:
                System.out.print("전화번호 입력");
                String memTel = sc.next();
                myMember.setMemTel(memTel);
                editMem(myMember, sel);
                break;
            case 4:
                sc.nextLine();
                System.out.print("주소 입력");
                String memAddr = sc.nextLine();
                myMember.setMemAddr(memAddr);
                editMem(myMember, sel);
                break;
            default:
                System.out.println("다시 입력해 주세요");
        }
    }


    /*
    String fileName과 String updateData 필드를 선언 후 선택번호에 따라 값을 부여한 후에
    쿼리문에 그대로 적용하는 방법도 있다
    String sql = "update mymember " + fileName + " = ? where mem_id = ? ";

    */

    private void editMem(MyMember myMember, int sel) {
        try{
            conn = DBUtil.getConnection();

            String sql = " update MYMEMBER set ";
            if(sel == 1){
                sql += " MEM_PASS = ?  where mem_id = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, myMember.getMemPass());
                pstmt.setString(2, myMember.getMemId());
            } else if (sel == 2) {
                sql += " MEM_NAME = ? where MEM_ID = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, myMember.getMemName());
                pstmt.setString(2, myMember.getMemId());
            } else if (sel == 3) {
                sql += " MEM_TEL = ? where MEM_ID = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, myMember.getMemTel());
                pstmt.setString(2, myMember.getMemId());
            } else if (sel == 4) {
                sql += " MEM_ADDR = ? where MEM_ID = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, myMember.getMemAddr());
                pstmt.setString(2, myMember.getMemId());
            }

            int cnt = pstmt.executeUpdate();

            if(cnt > 0) System.out.println("회원 업데이트 성공!!");
            else System.out.println("회원 업데이트 실패~~~");



        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
    }


    private int editMenu() {
        System.out.println("== 수정 항목 선택 ==");
        System.out.println("        1. 비밀번호     ");
        System.out.println("        2. 이름     ");
        System.out.println("        3. 전화번호    ");
        System.out.println("        4. 주소 ");
        int sel = sc.nextInt();

        return sel;
    }

    //메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
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

    public void select() {
        System.out.println("== 회원 전체 리스트 ==");
        //전체 리스트 받아오는 함수 호출
        List<MyMember> myMemberList = memberList();
        System.out.println("회원 아이디\t회원 비밀번호\t회원 이름\t회원 전화번호\t회원 주소");
        System.out.println("----------------------------------------------------------");
        if(myMemberList.isEmpty()){
            System.out.println("회원정보가 없습니다.");
        }
        for(MyMember my : myMemberList) {
            String memId = my.getMemId();
            String memPass = my.getMemPass();
            String memName = my.getMemName();
            String memTel = my.getMemTel();
            String memAddr = my.getMemAddr();
            System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
        }
        System.out.println("----------------------------------------------------------");
    }



    public void insert() {
        System.out.println("== 회원정보 추가 메뉴 ==");
        //회원아이디 중복 체크
        boolean idChk = false;
        String memId = null;
        while (!idChk) {
            System.out.print("회원 아이디 >> ");
            memId = sc.next();
            idChk = idChk(memId);
            if(idChk) {
                System.out.println("사용 가능한 아이디 입니다.");
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
        MyMember myMember = new MyMember(memId,memPass,memName,memTel,memAddr);
        insertMem(myMember);

    }



    public void update() {

        System.out.println("== 회원 정보 수정 페이지 ==");
        select();
        System.out.println("수정할 회원 아이디를 입력하세요");
        //회원아이디 중복 체크
        String memId = null;
        while (true) {
            System.out.print("회원 아이디 >> ");
            memId = sc.next();
            if(!idChk(memId)) {
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

        MyMember myMember = new MyMember(memId, memPass,memName,memTel,memAddr);

        //회원정보 변경함수 호출
        updateMem(myMember);

    }


    public void delete() {
        System.out.println("== 회원 정보 삭제 페이지 ==");
        select();
        System.out.println("삭재할 회원 아이디를 입력하세요");
        //회원아이디 중복 체크
        String memId = null;
        while (true) {
            System.out.print("회원 아이디 >> ");
            memId = sc.next();
            if(!idChk(memId)) {
                System.out.println("존재하는 아이디 입니다.");
                break;
            }else System.out.println("존재하지 않는 아이디입니다.");
        }

        //회원정보 삭제함수 호출
        delteMem(memId);
    }
    //회원정보 삭제함수
    private void delteMem(String memId) {
        try{
            conn = DBUtil.getConnection();

            String sql = "delete MYMEMBER where MEM_ID = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            int cnt = pstmt.executeUpdate();

            if(cnt > 0) System.out.println("회원 삭제 성공!!");
            else System.out.println("회원 삭제 실패~~");

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
    }

    //전체 리스트 출력
    private List<MyMember> memberList() {
        List<MyMember> list = new ArrayList<MyMember>();
        try{
            conn = DBUtil2.getConnection();
//            conn = DBUtil.getConnection();

            String sql = "select * from MYMEMBER";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String memId = rs.getString("mem_id");
                String memPass = rs.getString("mem_pass");
                String memName = rs.getString("mem_name");
                String memTel = rs.getString("mem_tel");
                String memAddr = rs.getString("mem_addr");
                MyMember myMember = new MyMember(memId,memPass,memName, memTel,memAddr);
                list.add(myMember);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) try{rs.close();}catch (SQLException e) {}
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
        return list;
    }

    //아이디 중복체크 boolean값으로 반환하지않고 rs = pstmt.executeQuery();의 반환값만 보내서 체크하는것이 더 나을듯?
    public boolean idChk(String memId) {
        try {
            conn = DBUtil.getConnection();

            String sql = " select count(*) from MYMEMBER where MEM_ID = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            //0이상이면 중복
            rs.next();
            if(rs.getInt(1) > 0) return false;
            else return true;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) try{rs.close();}catch (SQLException e) {}
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
        return false;
    }
    //정보 넣기
    private void insertMem(MyMember myMember) {
        try{
            conn = DBUtil.getConnection();

            String sql = "insert into MYMEMBER(mem_id, mem_pass, mem_name, mem_tel, mem_addr) VALUES (?,?,?,?,?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, myMember.getMemId());
            pstmt.setString(2, myMember.getMemPass());
            pstmt.setString(3, myMember.getMemName());
            pstmt.setString(4, myMember.getMemTel());
            pstmt.setString(5, myMember.getMemAddr());
            int cnt = pstmt.executeUpdate();

            if(cnt > 0) System.out.println("회원 등록 성공!!");
            else System.out.println("회원 등록 실패~~~");

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }

    }
    //회원정보 업데이트
    private void updateMem(MyMember myMember) {
        try{
            conn = DBUtil.getConnection();

            String sql = "update MYMEMBER set " +
                    "MEM_PASS = ?," +
                    "MEM_NAME = ?," +
                    "MEM_TEL = ?," +
                    "MEM_ADDR = ?" +
                    "where MEM_ID = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, myMember.getMemPass());
            pstmt.setString(2, myMember.getMemName());
            pstmt.setString(3, myMember.getMemTel());
            pstmt.setString(4, myMember.getMemAddr());
            pstmt.setString(5, myMember.getMemId());
            int cnt = pstmt.executeUpdate();

            if(cnt > 0) System.out.println("회원 업데이트 성공!!");
            else System.out.println("회원 업데이트 실패~~~");


        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
    }
}

class MyMember {
    private String memId;
    private String memPass;
    private String memName;
    private String memTel;
    private String memAddr;

    public MyMember() {

    }
    public MyMember(String memId, String memPass, String memName, String memTel, String memAddr) {
        this.memId = memId;
        this.memPass = memPass;
        this.memName = memName;
        this.memTel = memTel;
        this.memAddr = memAddr;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemPass() {
        return memPass;
    }

    public void setMemPass(String memPass) {
        this.memPass = memPass;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemTel() {
        return memTel;
    }

    public void setMemTel(String memTel) {
        this.memTel = memTel;
    }

    public String getMemAddr() {
        return memAddr;
    }

    public void setMemAddr(String memAddr) {
        this.memAddr = memAddr;
    }

    @Override
    public String toString() {
        return "MyMember{" +
                "memId='" + memId + '\'' +
                ", memPass='" + memPass + '\'' +
                ", memName='" + memName + '\'' +
                ", memTel='" + memTel + '\'' +
                ", memAddr='" + memAddr + '\'' +
                '}';
    }
}
