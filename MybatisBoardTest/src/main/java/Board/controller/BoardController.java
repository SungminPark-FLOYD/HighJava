package Board.controller;

import Board.service.IBoardService;
import Board.service.BoardServiceImpl;
import Board.vo.BoardVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BoardController {
    private IBoardService service;
    private Scanner sc;

    private BoardController() {
        service = BoardServiceImpl.getInstance();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new BoardController().mainMenu();
    }

    private void mainMenu() {
        while (true) {
            getAllList();
            selMenu();
        }
    }
    private void selMenu() {
        System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
        System.out.print("작업선택 >> ");
        int sel = sc.nextInt();
        switch (sel){
            case 1:
                insertBoard();
                break;
            case 2:
                detailBoard();
                break;
            case 3:
                searchBoard();
                break;
            case 0:
                System.out.println("게시판 프로그램 종료....");
                System.exit(0);
            default:
                System.out.println("다시 입력해 주세요!!");
        }
    }
    private void getAllList(){
        List<BoardVo> boardList = service.getAllBoard();
        System.out.println("-------------------------------------------------------------");
        System.out.println(" No\t        제 목            작성자 \t조회수");
        System.out.println("-------------------------------------------------------------");
        if(boardList.isEmpty()) {
            System.out.println("현재 게시글이 없습니다.");
            System.out.println("-------------------------------------------------------------");
            return;
        }
        for (BoardVo vo : boardList) {
            System.out.println(vo.getBoard_no() + "\t" +
                               vo.getBoard_title() + "\t\t" +
                               vo.getBoard_writer() + "\t" +
                               vo.getBoard_cnt());
        }
        System.out.println("-------------------------------------------------------------");
    }

    private void insertBoard(){
        System.out.println("새글 작성하기");
        System.out.println("-----------------------------------");
        System.out.print("- 제 목 : ");
        sc.nextLine();
        String title = sc.nextLine();
        System.out.print("- 작성자 : ");
        String writer = sc.nextLine();
        System.out.print("- 내 용 : ");
        String content = sc.nextLine();

        BoardVo boardVo = new BoardVo();
        boardVo.setBoard_title(title);
        boardVo.setBoard_writer(writer);
        boardVo.setBoard_content(content);

        int cnt = service.insertBoard(boardVo);

        if(cnt > 0) System.out.println("새글이 추가되었습니다.");
        else System.out.println("게시글 추가 실패~~~");

    }

    private void detailBoard() {
        System.out.print("보기를 원하는 게시물 번호 입력 >> ");
        int boardNo = sc.nextInt();
        printDetailBoard(boardNo);

        while (true){
            System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
            System.out.print("작업선택 >> ");
            int sel = sc.nextInt();
            switch (sel){
                case 1:
                    updateBoard(boardNo);
                    return;
                case 2:
                    deleteBoard(boardNo);
                    return;
                case 3:
                    return;
                default:
                    System.out.println("다시 입력해 주세요..");
            }
        }

    }

    private void printDetailBoard(int boardNo) {
        //조회수 먼저 올리기
        service.count(boardNo);
        //상세 게시판 출력
        BoardVo boardVo = service.detailBaord(boardNo);
        if(boardVo == null) {
            System.out.println("존재하지 않는 번호입니다.");
            mainMenu();
        }


        System.out.println(boardNo + "번글 내용");
        System.out.println("------------------------------------------------------------");
        System.out.println("- 제 목 : " + boardVo.getBoard_title());
        System.out.println("- 작성자 : " + boardVo.getBoard_writer());
        System.out.println("- 내 용 : " + boardVo.getBoard_content());
        System.out.println("- 작성일 : " + boardVo.getBoard_date());
        System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
        System.out.println("------------------------------------------------------------");
    }

    private void updateBoard(int boardNo) {
        Map<String, String> param = new HashMap<String, String>();
        sc.nextLine();
        System.out.println("수정 작업하기");
        System.out.println("-----------------------------------");
        System.out.print("- 제  목 : ");
        String title = sc.nextLine();
        System.out.print("- 내  용 : ");
        String content = sc.nextLine();

        String board_no = Integer.toString(boardNo);

        param.put("board_no", board_no);
        param.put("board_title", title);
        param.put("board_content", content);
        int cnt = service.updateBoard(param);

        if(cnt > 0) System.out.println(boardNo + "번글이 수정되었습니다.");
        else System.out.println("수정 실패~~~");
    }

    private void deleteBoard(int boardNo){
        sc.nextLine();
        int cnt = service.deleteBoard(boardNo);

        if(cnt > 0) System.out.println(boardNo + "번글이 삭제되었습니다.");
        else System.out.println("삭제 실패~~~");
    }




    private void searchBoard() {
        sc.nextLine();
        System.out.println("검색 작업");
        System.out.println("--------------------------------------------");
        System.out.print("- 검색할 제목 입력 : ");
        String data = sc.nextLine();
        if(data.trim().isEmpty()){
            return;
        }
        else {
            List<BoardVo> boardList = service.searchBoard(data);


            System.out.println("-------------------------------------------------------------");
            System.out.println(" No\t        제 목            작성자 \t조회수");
            System.out.println("-------------------------------------------------------------");
            if (boardList.isEmpty()) {
                System.out.println("검색 결과가 없습니다.");
                System.out.println("-------------------------------------------------------------");
                return;
            }
            for (BoardVo vo : boardList) {
                System.out.println(vo.getBoard_no() + "\t" +
                        vo.getBoard_title() + "\t\t" +
                        vo.getBoard_writer() + "\t" +
                        vo.getBoard_cnt());
            }
            System.out.println("-------------------------------------------------------------");

            selMenu();
        }
    }
}
