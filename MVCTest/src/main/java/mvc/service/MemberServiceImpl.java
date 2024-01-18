package mvc.service;

import mvc.dao.IMemberDao;
import mvc.dao.MemberDaoImpl;
import mvc.vo.MemberVo;

import java.util.List;
import java.util.Map;

public class MemberServiceImpl implements IMemberService{
    //DAO객체의 참조값이 저장될 변수 선언
    private IMemberDao dao;
    private static MemberServiceImpl instance = null;
    //생성자
    private MemberServiceImpl() {
        //인터페이스에서 참조해서 객체를 가져온다
        dao = MemberDaoImpl.getInstance();//DAO객체 생성
    }
    public static MemberServiceImpl getInstance() {
        if(instance == null) instance = new MemberServiceImpl();
        return instance;
    }

    @Override
    public int insertMember(MemberVo memVo) {
        return dao.insertMember(memVo);
    }

    @Override
    public int deleteMember(String memId) {
        return dao.deleteMember(memId);
    }

    @Override
    public int updateMember(MemberVo memVo) {
        return dao.updateMember(memVo);
    }

    @Override
    public List<MemberVo> getAllMember() {
        return dao.getAllMember();
    }

    @Override
    public int getMemberCount(String memId) {
        return dao.getMemberCount(memId);
    }

    @Override
    public int editMem(Map<String, String> param) {
        return dao.editMem(param);
    }
}
