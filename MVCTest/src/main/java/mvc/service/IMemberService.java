package mvc.service;

import mvc.vo.MemberVo;

import java.util.List;

/**
 * Service객체는 DAO에 만들어진 메서드를 원하는 작업에 맞게 호출하여
 * 실행 결과를 받아오고, 받아온 결과를 Controller에게 보내주는 역할을 한다.
 *
 * 우리 시간에는 DAO의 interface와 구조가 같게 만든다
 */
public interface IMemberService {
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
}
