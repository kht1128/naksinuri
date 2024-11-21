package egovframework.seadm.member.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.seadm.member.service.MemberService;
import egovframework.seadm.member.service.MemberVO;
import egovframework.seadm.member.service.UserDefaultVO;

@Service("MemberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService {
	
	/** memberDAO */
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	/**
	 * 회원 정보를 데이터베이스에서 읽어와 화면에 출력
	 */
	@Override
	public List<MemberVO> memberList(UserDefaultVO userSearchVO) {
		return memberDAO.memberList(userSearchVO);
	}
	
	/**
     * 회원정보 총 갯수를 조회한다.
     */
    @Override
	public int memberListTotCnt(UserDefaultVO userSearchVO) {
    	return memberDAO.memberListTotCnt(userSearchVO);
    }

    @Override
	public MemberVO getmemberInfo(MemberVO memberVO) {
    	return memberDAO.getmemberInfo(memberVO);
    }

    @Override
	public void memberInfoUpdate(MemberVO memberVO) {
    	memberDAO.memberInfoUpdate(memberVO);
    }

    @Override
	public void memberInfoDelete(MemberVO memberVO) {
    	memberDAO.memberInfoDelete(memberVO);
    }

	@Override
	public EduMemberVO get_seadm_member_auth_info(EduMemberVO eduMemberVO) throws Exception {
		return memberDAO.get_seadm_member_auth_info(eduMemberVO);		
	}

	@Override
	public void set_seadm_member_auth_mod(EduMemberVO eduMemberVO) throws Exception {
		memberDAO.set_seadm_member_auth_mod(eduMemberVO);
	}

	@Override
	public List<EduMemberVO> get_seadm_member_auth_list(EduMemberVO eduMemberVO) throws Exception {
		return memberDAO.get_seadm_member_auth_list(eduMemberVO);
	}

	@Override
	public int get_seadm_member_auth_list_totcnt(EduMemberVO eduMemberVO) throws Exception {
		return memberDAO.get_seadm_member_auth_list_totcnt(eduMemberVO);
	}

    
}
