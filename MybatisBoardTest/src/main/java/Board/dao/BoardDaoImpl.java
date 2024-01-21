package Board.dao;

import Board.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import util.mybatisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDaoImpl implements IBoardDao{
    private static BoardDaoImpl instance = null;
    private BoardDaoImpl(){}
    public static BoardDaoImpl getInstance() {
        if(instance == null) instance = new BoardDaoImpl();
        return instance;
    }
    @Override
    public List<BoardVo> getAllBoard() {
        List<BoardVo> boardList = null;

        SqlSession session = null;

        try {
            session = mybatisUtil.getSqlSession();
            boardList = new ArrayList<BoardVo>();
            boardList = session.selectList("board.getAllBoard");

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return boardList;
    }

    @Override
    public int insertBoard(BoardVo boardVo) {
        int cnt = 0;
        SqlSession session = null;
        try {
            session = mybatisUtil.getSqlSession();
            cnt = session.insert("board.insertBoard", boardVo);

            if(cnt > 0) session.commit();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return cnt;
    }

    @Override
    public BoardVo detailBaord(int boardNo) {
        BoardVo boardVo = null;
        SqlSession session = null;

        try {

            session = mybatisUtil.getSqlSession();
            boardVo = session.selectOne("board.detailBoard", boardNo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return boardVo;
    }

    @Override
    public int updateBoard(Map<String, String> param) {
        int cnt = 0;
        SqlSession session = null;

        try {
           session = mybatisUtil.getSqlSession();
           cnt = session.update("board.updateBoard", param);
           if(cnt > 0) session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return cnt;
    }

    @Override
    public int deleteBoard(int boardNo) {
        int cnt = 0;
        SqlSession session = null;

        try {
            session = mybatisUtil.getSqlSession();
            cnt = session.delete("board.deleteBoard", boardNo);
            if (cnt > 0) session.commit();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return cnt;
    }

    @Override
    public List<BoardVo> searchBoard(String data) {
        List<BoardVo> boardList = null;
       SqlSession session = null;

        try {
            session = mybatisUtil.getSqlSession();
            boardList = session.selectList("board.searchBoard", data);

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return boardList;
    }

    @Override
    public int count(int boardNo) {
        int cnt = 0;
        SqlSession session = null;

        try {
            session = mybatisUtil.getSqlSession();

            cnt = session.update("board.count", boardNo);
            if(cnt > 0) session.commit();

        }   catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return cnt;
    }
}
