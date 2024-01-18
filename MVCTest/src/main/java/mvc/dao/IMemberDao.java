package mvc.dao;

import mvc.vo.MemberVo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 메서드 하나가 DB와 관련된 작업 1개를 수행하도록 작성한다.
 */
public interface IMemberDao {
    /**
     * MemberVo객체에 담겨진 자료를 insert하는 메서드
     *
     * @param memVo DB에 insert할 자료가 저장된 MemberVo객체
     * @return 작업 성공 : 1, 작업 실패 : 0
     */
    public int insertMember(MemberVo memVo);

    /**
     *  회원ID를 매개변수로 받아서 회원 정보를 delete하는 메서드
     * @param memId DB에 delete할 자료가 저장된 회원 ID
     * @return 작업 성공 : 1, 작업 실패 : 0
     */
    public int deleteMember(String memId);

    /**
     * MemberVo객체에 담겨진 자료를 update하는 메서드
     * @param memVo DB에 update할 자료가 저장된 MemberVo객체
     * @return 작업 성공 : 1, 작업 실패 : 0
     */
    public int updateMember(MemberVo memVo);

    /**
     * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
     * @return MemberVo객체가 저장된 List객체
     */
    public List<MemberVo> getAllMember();

    /**
     * 회원 ID를 입력받아서 회원ID의 개수를 체크하는 메서드
     * @param memId
     * @return 검색된 회원ID의 갯수
     */
    public int getMemberCount(String memId);

    /**
     * 회원 ID와 필드명 변경 데이터를 입력받아 쿼리를 보내는 메서드
     * @param param
     * @return
     */
    public int editMem(Map<String, String> param);

}
