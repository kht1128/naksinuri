package egovframework.seadm.member.service;

import java.util.List;

import egovframework.seadm.member.service.UserDefaultVO;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.seadm.member.service.MemberVO;

public interface MemberService {
	
	/**
	 * 회원목록 조회
	 */
	public List<MemberVO> memberList(UserDefaultVO userSearchVO) throws Exception;
	
	/**
     * 회원목록 갯수 조회
     */
    public int memberListTotCnt(UserDefaultVO userSearchVO) throws Exception;

    // 회원상세정보 가져오기
    public MemberVO getmemberInfo(MemberVO memberVO) throws Exception;
    
    // 회원상세정보 수정
    public void memberInfoUpdate(MemberVO memberVO) throws Exception;
    // 회원상세정보 삭제
    public void memberInfoDelete(MemberVO memberVO) throws Exception;

    //권한관리 - 회원조회(대상조회)
	public EduMemberVO get_seadm_member_auth_info(EduMemberVO eduMemberVO) throws Exception;
	
	//권한관리 - 회원수정(권한부여)
	public void set_seadm_member_auth_mod(EduMemberVO eduMemberVO) throws Exception;
 
	//권한관리 - 회원목록 조회
	public List<EduMemberVO> get_seadm_member_auth_list(EduMemberVO eduMemberVO) throws Exception;
	public int get_seadm_member_auth_list_totcnt(EduMemberVO eduMemberVO) throws Exception;
    
}
