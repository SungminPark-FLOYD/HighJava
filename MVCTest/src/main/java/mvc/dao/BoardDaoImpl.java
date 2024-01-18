package mvc.dao;

import mvc.vo.BoardVo;
import util.DBUtil3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil3.getConnection();

            String sql = "select board_no, board_title, board_writer, board_cnt from jdbc_board order by board_no desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            boardList = new ArrayList<BoardVo>();

            while(rs.next()){
                BoardVo boardVo = new BoardVo();
                boardVo.setBoard_no(rs.getInt("board_no"));
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
                boardList.add(boardVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) try{rs.close();}catch (SQLException e) {}
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
        return boardList;
    }

    @Override
    public int insertBoard(BoardVo boardVo) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil3.getConnection();

            String sql = " insert into jdbc_board(board_no, board_title, board_writer, board_date, board_content) " +
                        " values (board_seq.nextVal, ?, ?, sysdate, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardVo.getBoard_title());
            pstmt.setString(2, boardVo.getBoard_writer());
            pstmt.setString(3, boardVo.getBoard_content());

            cnt = pstmt.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }

        return cnt;
    }

    @Override
    public BoardVo detailBaord(int boardNo) {
        BoardVo boardVo = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil3.getConnection();

            String sql = "select board_title, board_writer, board_content, to_char(board_date, 'yyyy-MM-dd') board_date, board_cnt " +
                    " from jdbc_board " +
                    " where board_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);
            rs = pstmt.executeQuery();

            if(rs.next()){
                boardVo = new BoardVo();
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_content(rs.getString("board_content"));
                boardVo.setBoard_date(rs.getString("board_date"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) try{rs.close();}catch (SQLException e) {}
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
        return boardVo;
    }

    @Override
    public int updateBoard(Map<String, String> param) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil3.getConnection();

            String sql = " update jdbc_board set" +
                    "    board_title = ?," +
                    "    board_content = ?," +
                    "    board_date = sysdate " +
                    " where board_no = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, param.get("board_title"));
            pstmt.setString(2, param.get("board_content"));
            pstmt.setString(3, param.get("board_no"));

            cnt = pstmt.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }

        return cnt;
    }

    @Override
    public int deleteBoard(int boardNo) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil3.getConnection();

            String sql = " delete jdbc_board where board_no = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            cnt = pstmt.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }

        return cnt;
    }

    @Override
    public List<BoardVo> searchBoard(String data) {
        List<BoardVo> boardList = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil3.getConnection();

            String sql = " select board_no, board_title, board_writer, board_cnt from jdbc_board " +
                         " where board_title like '%' || ? || '%'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, data);
            rs = pstmt.executeQuery();
            boardList = new ArrayList<BoardVo>();

            while(rs.next()){
                BoardVo boardVo = new BoardVo();
                boardVo.setBoard_no(rs.getInt("board_no"));
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
                boardList.add(boardVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) try{rs.close();}catch (SQLException e) {}
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
        return boardList;
    }

    @Override
    public int count(int boardNo) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil3.getConnection();

            String sql = " update jdbc_board set" +
                    "    board_cnt = board_cnt+1" +
                    " where board_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            cnt = pstmt.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }

        return cnt;
    }
}
