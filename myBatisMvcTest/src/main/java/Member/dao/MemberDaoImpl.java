package Member.dao;



import Member.vo.MemberVo;
import org.apache.ibatis.session.SqlSession;
import util.mybatisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberDaoImpl implements IMemberDao{
    private static MemberDaoImpl instance = null;

    private MemberDaoImpl() {}

    public static MemberDaoImpl getInstance() {
        if(instance == null) instance = new MemberDaoImpl();
        return instance;
    }
    @Override
    public int insertMember(MemberVo memVo) {
        int cnt = 0;

        SqlSession session = null;
        try {
            session = mybatisUtil.getSqlSession();

            cnt = session.insert("member.insertMember", memVo);
            if (cnt > 0) {
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return cnt;
    }

    @Override
    public int deleteMember(String memId) {
        int cnt = 0;
        SqlSession session = null;
        try {
            session = mybatisUtil.getSqlSession();

            cnt = session.delete("member.deleteMember", memId);
            if(cnt > 0) session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return cnt;
    }

    @Override
    public int updateMember(MemberVo memVo) {
        int cnt = 0;
        SqlSession session = null;
        try {
            session = mybatisUtil.getSqlSession();

            cnt = session.update("member.updateMember", memVo);
            if(cnt > 0) session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return cnt;
    }

    @Override
    public List<MemberVo> getAllMember() {
        List<MemberVo> memList = null;
        SqlSession session = null;

        try {
            session = mybatisUtil.getSqlSession();
            memList = new ArrayList<MemberVo>();
            memList = session.selectList("member.getAllMember");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return memList;
    }

    @Override
    public int getMemberCount(String memId) {

        int cnt = 0;
        SqlSession session = null;
        try {
            session = mybatisUtil.getSqlSession();

            cnt = session.selectOne("member.getMemberCount", memId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return cnt;
    }

    @Override
    public int editMem(Map<String, String> param) {

        int cnt = 0;
        SqlSession session = null;

        try {
           session = mybatisUtil.getSqlSession();

           cnt = session.update("member.editMem", param);
           if(cnt > 0) session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return cnt;
    }


}
