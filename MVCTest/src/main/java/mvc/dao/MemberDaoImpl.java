package mvc.dao;

import mvc.vo.MemberVo;
import util.DBUtil3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements IMemberDao{
    @Override
    public int insertMember(MemberVo memVo) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil3.getConnection();

            String sql = "insert into MYMEMBER(mem_id, mem_pass, mem_name, mem_tel, mem_addr) VALUES (?,?,?,?,?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memVo.getMem_id());
            pstmt.setString(2, memVo.getMem_pass());
            pstmt.setString(3, memVo.getMem_name());
            pstmt.setString(4, memVo.getMem_tel());
            pstmt.setString(5, memVo.getMem_addr());
            cnt = pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
        return cnt;
    }

    @Override
    public int deleteMember(String memId) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil3.getConnection();

            String sql = "delete MYMEMBER where MEM_ID = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            cnt = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
        return cnt;
    }

    @Override
    public int updateMember(MemberVo memVo) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil3.getConnection();

            String sql = "update MYMEMBER set " +
                    "MEM_PASS = ?," +
                    "MEM_NAME = ?," +
                    "MEM_TEL = ?," +
                    "MEM_ADDR = ?" +
                    "where MEM_ID = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memVo.getMem_pass());
            pstmt.setString(2, memVo.getMem_name());
            pstmt.setString(3, memVo.getMem_tel());
            pstmt.setString(4, memVo.getMem_addr());
            pstmt.setString(5, memVo.getMem_id());
            cnt = pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }

        return cnt;
    }

    @Override
    public List<MemberVo> getAllMember() {
        List<MemberVo> memList = null;

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil3.getConnection();

            String sql = "select * from MYMEMBER";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            memList = new ArrayList<MemberVo>();
            while(rs.next()){
                MemberVo memVo = new MemberVo();
                memVo.setMem_id(rs.getString("mem_id"));
                memVo.setMem_pass(rs.getString("mem_pass"));
                memVo.setMem_name(rs.getString("mem_name"));
                memVo.setMem_tel(rs.getString("mem_tel"));
                memVo.setMem_addr(rs.getString("mem_addr"));
                memList.add(memVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) try{rs.close();}catch (SQLException e) {}
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }
        return memList;
    }

    @Override
    public int getMemberCount(String memId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = 0;
        try {
            conn = DBUtil3.getConnection();

            String sql = " select count(*) cnt from MYMEMBER where MEM_ID = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            if(rs.next()) cnt = rs.getInt("cnt");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) try{rs.close();}catch (SQLException e) {}
            if (pstmt!=null) try{pstmt.close();}catch (SQLException e) {}
            if (conn!=null) try{conn.close();}catch (SQLException e) {}
        }

        return cnt;
    }
}
