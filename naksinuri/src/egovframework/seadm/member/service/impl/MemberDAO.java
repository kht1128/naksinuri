package egovframework.seadm.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.seadm.member.service.MemberVO;
import egovframework.seadm.member.service.UserDefaultVO;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("memberDAO")
public class MemberDAO extends EgovAbstractDAO {
	
	/**
	* 일반회원 데이터를 조회한다.
	*/
	@SuppressWarnings("unchecked")
	public List<MemberVO> memberList(UserDefaultVO userSearchVO){
        return (List<MemberVO>) list("memberDAO.memberList", userSearchVO);
    }
	 
	/**
	* 일반회원 데이터 갯수를 조회한다.
	*/
    public int memberListTotCnt(UserDefaultVO userSearchVO) {
    	
        return (Integer)select("mberManageDAO.memberListTotCnt", userSearchVO);
        
    }     

	@SuppressWarnings("unchecked")
	public List<MemberVO> selectMberList(UserDefaultVO userSearchVO){
		
        return (List<MemberVO>) list("memberDAO.selectMberList", userSearchVO);

    }
	
	@SuppressWarnings("unchecked")
	public MemberVO getmemberInfo(MemberVO memberVO){
		
        return (MemberVO) select("getmemberInfo", memberVO);

    }
	
	@SuppressWarnings("unchecked")
	public void memberInfoUpdate(MemberVO memberVO){
		update("memberInfoUpdate", memberVO);
    }
	
	@SuppressWarnings("unchecked")
	public void memberInfoDelete(MemberVO memberVO){
		update("memberInfoDelete", memberVO);
    }

	public EduMemberVO get_seadm_member_auth_info(EduMemberVO eduMemberVO) {
		return (EduMemberVO)select("get_seadm_member_auth_info",eduMemberVO);
	}

	public void set_seadm_member_auth_mod(EduMemberVO eduMemberVO) {
		update("set_seadm_member_auth_mod", eduMemberVO);
	}

	public List<EduMemberVO> get_seadm_member_auth_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>)list("get_seadm_member_auth_list",eduMemberVO);
	}

	public int get_seadm_member_auth_list_totcnt(EduMemberVO eduMemberVO) {
		return (int)select("get_seadm_member_auth_list_totcnt",eduMemberVO);
	}
	

}
