package Board.dao;

import Board.vo.BoardVo;

import java.util.List;
import java.util.Map;

public interface IBoardDao {

    /**
     * 전체 게시판을 출력하는 메소드
     * @return board_no, board_title, board_writer, board_cnt 를 반환한다.
     */
    public List<BoardVo> getAllBoard();

    /**
     * 게시판 작성 메서드
     * @param boardVo board_no.nextVal, ?, ?, sysdate, ?
     * @return 성공하면 1 실패하면 0
     */
    public int insertBoard(BoardVo boardVo);

    /**
     * 게시글 상세보기
     * @param boardNo 게시글 번호를 가져온다
     * @return board_title, board_writer, board_content, to_char('yyyy-MM-dd', board_date), board_cnt
     */
    public BoardVo detailBaord(int boardNo);

    /**
     * 제목과 내용을 입력받아서 게시글을 수정하는 메서드
     * @param param board_title, board_content, board_no
     * @return 성공하면 1 실패하면 0
     */
    public int updateBoard(Map<String, String> param);

    /**
     * 게시글 번호를 입력받아서 게시글을 삭제하는 메서드
     * @param boardNo 게시글 번호
     * @return 성공하면 1 실패하면 0
     */
    public int deleteBoard(int boardNo);

    /**
     * 게시글 제목을 입력받아서 관련 제목들을 검색하는 메서드
     * 공백이면 전체리스트를 출력한다?
     * @param data 검색어
     * @return board_no, board_title, board_writer, board_cnt
     */
    public List<BoardVo> searchBoard(String data);

    /**
     * 게시글 번호를 입력받아서 조회수를 올려주는 메서드
     * @param boardNo 게시글 번호
     * @return 성공하면 1 실패하면 0
     */
    public int count(int boardNo);

}
