package egovframework.eduadm.myhistory.service;

import java.util.List;

import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface EduMyHistoryService {
	//수강내역 ----------- 
	public List<EduMemberVO> get_edu_mbr_list_only_mbrhstry(EduMemberVO eduMemberVO);
	public List<EduMyHistoryVO> get_edu_mbrhstry_list(EduMyHistoryVO eduMyHistoryVO);
	public List<EduMyHistoryVO> get_edu_mbrhstry_list_totcnt(EduMyHistoryVO eduMyHistoryVO);
	public List<EduMyHistoryVO> get_edu_mbrhstry_dtl_list(EduMyHistoryVO eduMyHistoryVO);
	public int get_edu_mbrhstry_dtl_list_totcnt(EduMyHistoryVO eduMyHistoryVO);
	public EduMyHistoryVO get_edu_mbrhstry_info(EduMyHistoryVO eduMyHistoryVO);
	public EduMyHistoryVO get_edu_mbrhstry_info_dtl(EduMyHistoryVO eduMyHistoryVO);
	public int get_edu_mbr_list_only_mbrhstry_totcnt(EduMemberVO eduMemberVO);
	public void set_edu_mbrhstry_mod(EduMyHistoryVO eduMyHistoryVO);
	public void set_edu_mbrhstry_mod_dtl(EduMyHistoryVO cloneEduMyHistoryVO);
	public void del_edu_mbrhstry(EduMyHistoryVO eduMyHistoryVO);
	public void del_edu_mbrhstry_dtl(EduMyHistoryVO eduMyHistoryVO);
	public String set_edu_mbrhstry_reg(EduMyHistoryVO eduMyHistoryVO);
	public String set_edu_mbrhstry_reg_dtl(EduMyHistoryVO eduMyHistoryVO);
	public void remove_edu_mbrhstry(EduMyHistoryVO eduMyHistoryVO);
	public void remove_edu_mbrhstry_dtl(EduMyHistoryVO eduMyHistoryVO);
	public void set_edu_mbrhstry_cur_cmplt_up(EduMyHistoryVO eduMyHistoryVO);
	public void set_edu_mbrhstry_cur_cmplt_down(EduMyHistoryVO eduMyHistoryVO);
	//지역별 교육이수율 현황 결과보고서
	public List<EgovMap> get_edu_mbrhstry_area_result(EgovMap prameters);
	public boolean get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(String HMBR_SN);
	public boolean get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(String HMBR_DTL_SN);
}
