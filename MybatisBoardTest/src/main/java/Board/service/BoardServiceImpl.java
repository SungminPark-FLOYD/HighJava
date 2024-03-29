package Board.service;

import Board.dao.BoardDaoImpl;
import Board.dao.IBoardDao;
import Board.vo.BoardVo;

import java.util.List;
import java.util.Map;

public class BoardServiceImpl implements IBoardService{
    private static BoardServiceImpl instance = null;
    IBoardDao dao;
    private BoardServiceImpl(){
        dao = BoardDaoImpl.getInstance();
    }
    public static BoardServiceImpl getInstance() {
        if(instance == null) instance = new BoardServiceImpl();
        return instance;
    }
    @Override
    public List<BoardVo> getAllBoard() {
        return dao.getAllBoard();
    }

    @Override
    public int insertBoard(BoardVo boardVo) {
        return dao.insertBoard(boardVo);
    }

    @Override
    public BoardVo detailBaord(int boardNo) {
        //조회수 증가작업이 실패했을때
//        if(dao.count(boardNo) == 0) return null;

        return dao.detailBaord(boardNo);
    }

    @Override
    public int updateBoard(Map<String, String> param) {
        return dao.updateBoard(param);
    }

    @Override
    public int deleteBoard(int boardNo) {
        return dao.deleteBoard(boardNo);
    }

    @Override
    public List<BoardVo> searchBoard(String data) {
        return dao.searchBoard(data);
    }

    @Override
    public int count(int boardNo) {
        return dao.count(boardNo);
    }
}
