/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.eduadm.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.eduadm.member.service.EduExcelUploadVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.member.service.LogAdmAuthVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eduMemberService")
public class EduMemberServiceImpl extends EgovAbstractServiceImpl implements EduMemberService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduMemberServiceImpl.class);

	@Resource(name = "eduMemberDAO")
	private EduMemberDAO eduMemberDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	@Override
	public List<EduMemberVO> get_edu_member_list(EduMemberVO eduMemberVO) {
		return eduMemberDAO.get_edu_member_list(eduMemberVO);
	}

	@Override
	public int get_edu_member_list_totcnt(EduMemberVO eduMemberVO) {
		return eduMemberDAO.get_edu_member_list_totcnt(eduMemberVO);
	}

	@Override
	public EduMemberVO get_edu_member_info(EduMemberVO eduMemberVO) {
		return eduMemberDAO.get_edu_member_info(eduMemberVO);
	}

	@Override
	public void remove_edu_member(EduMemberVO eduMemberVO) {
		eduMemberDAO.remove_edu_member(eduMemberVO);
	}

	@Override
	public void del_edu_member(EduMemberVO eduMemberVO) {
		eduMemberDAO.del_edu_member(eduMemberVO);
	}

	@Override
	public void set_edu_member_mod(EduMemberVO eduMemberVO) {
		eduMemberDAO.set_edu_member_mod(eduMemberVO);
	}

	@Override
	public int get_id_search(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_id_search(eduMemberVO);
	}

	@Override
	public String set_edu_member_reg(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.set_edu_member_reg(eduMemberVO);
	}

	@Override
	public void remove_edu_member_dtl(EduMemberVO eduMemberVO) {
		eduMemberDAO.remove_edu_member_dtl(eduMemberVO);
	}

	@Override
	public void set_edu_member_dtl_mod(EduMemberVO eduMemberVO) {
		eduMemberDAO.set_edu_member_dtl_mod(eduMemberVO);
	}

	@Override
	public String set_edu_member_dtl_reg(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.set_edu_member_dtl_reg(eduMemberVO);
	}

	@Override
	public int get_is_member_dtl(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_is_member_dtl(eduMemberVO);
	}
		
	@Override
	public List<EduMemberVO> get_edu_member_dtl_list(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_dtl_list(eduMemberVO);
	}
	
	@Override
	public List<EduMemberVO> get_edu_member_dtl_all_list(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_dtl_all_list(eduMemberVO);
	}

	@Override
	public List<EduMemberVO> get_edu_member_target_all_list(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_target_all_list(eduMemberVO);
	}

	@Override
	public List<EduMemberVO> get_edu_member_target_list(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_target_list(eduMemberVO);
	}

	@Override
	public int get_edu_member_target_list_totcnt(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_target_list_totcnt(eduMemberVO);
	}
	
	@Override
	public List<EduMemberVO> get_edu_member_target_list_only(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_target_list_only(eduMemberVO);
	}

	@Override
	public int get_edu_member_target_list_totcnt_only(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_target_list_totcnt_only(eduMemberVO);
	}

	@Override
	public List<EduMemberVO> get_edu_member_target_add_list(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_target_add_list(eduMemberVO);
	}

	@Override
	public int get_edu_member_target_add_list_totcnt(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_target_add_list_totcnt(eduMemberVO);
	}

	@Override
	public String set_edu_member_target_reg(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.set_edu_member_target_reg(eduMemberVO);
	}

	@Override
	public void remove_edu_member_target(EduMemberVO eduMemberVO) throws Exception {
		eduMemberDAO.remove_edu_member_target(eduMemberVO);
	}

	@Override
	public void set_edu_member_target_mod(EduMemberVO eduMemberVO) throws Exception {
		eduMemberDAO.set_edu_member_target_mod(eduMemberVO);
	}

	@Override
	public List<EduMemberVO> get_edu_member_check_overlap_list(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_check_overlap_list(eduMemberVO);
	}
	
	@Override
	public List<EduMemberVO> get_edu_member_check_list(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_check_list(eduMemberVO);
	}
	
	@Override
	public List<EduMemberVO> get_edu_member_master_list(EduMemberVO eduMemberVO) {
		return eduMemberDAO.get_edu_member_master_list(eduMemberVO);
	}

	@Override
	public int get_edu_member_master_list_totcnt(EduMemberVO eduMemberVO) {
		return eduMemberDAO.get_edu_member_master_list_totcnt(eduMemberVO);
	}

	@Override
	public void set_edu_member_trnsfer(EduMemberVO eduMemberVO) {
		eduMemberDAO.set_edu_member_trnsfer(eduMemberVO);
	}

	@Override
	public void set_edu_member_master_auth_mod(EduMemberVO eduMemberVO) throws Exception {
		eduMemberDAO.set_edu_member_master_auth_mod(eduMemberVO);
	}

	@Override
	public void set_edu_member_memo_mod(EduMemberVO eduMemberVO) throws Exception {
		eduMemberDAO.set_edu_member_memo_mod(eduMemberVO);
	}

	@Override
	public EduMemberVO get_edu_member_dtl_info(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_dtl_info(eduMemberVO);
	}

	@Override
	public EduMemberVO get_edu_member_master_info(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_master_info(eduMemberVO);
	}

	@Override
	public List<LogAdmAuthVO> get_edu_member_author_log_list(LogAdmAuthVO logAdmAuthVO) throws Exception {
		return eduMemberDAO.get_edu_member_author_log_list(logAdmAuthVO);
	}
	@Override
	public int get_edu_member_author_log_list_totcnt(LogAdmAuthVO logAdmAuthVO) throws Exception {
		return eduMemberDAO.get_edu_member_author_log_list_totcnt(logAdmAuthVO);
	}

	@Override
	public void set_edu_member_author_log(LogAdmAuthVO logAdmAuthVO) throws Exception {
		eduMemberDAO.set_edu_member_author_log(logAdmAuthVO);
	}

	@Override
	public void set_edu_excel_upload_reg(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		eduMemberDAO.set_edu_excel_upload_reg(eduExcelUploadVO);
	}

	@Override
	public void set_edu_excel_upload_dtl_reg(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		eduMemberDAO.set_edu_excel_upload_dtl_reg(eduExcelUploadVO);
	}

	@Override
	public List<EduExcelUploadVO> get_edu_excel_upload_list(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		return eduMemberDAO.get_edu_excel_upload_list(eduExcelUploadVO);
	}

	@Override
	public List<EduExcelUploadVO> get_edu_excel_upload_dtl_list(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		return eduMemberDAO.get_edu_excel_upload_dtl_list(eduExcelUploadVO);
	}

	@Override
	public List<EduMemberVO> get_edu_member_dtl_excel_compare(EduMemberVO eduMemberVO) throws Exception {
		return eduMemberDAO.get_edu_member_dtl_excel_compare(eduMemberVO);
	}

	@Override
	public void remove_edu_excel_upload_list(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		eduMemberDAO.remove_edu_excel_upload_list(eduExcelUploadVO);
	}
	@Override
	public void remove_edu_excel_upload_dtl_list(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		eduMemberDAO.remove_edu_excel_upload_dtl_list(eduExcelUploadVO);
	}
	@Override
	public EduExcelUploadVO get_edu_excel_upload_info(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		return eduMemberDAO.get_edu_excel_upload_info(eduExcelUploadVO);
	}

	@Override
	public int get_edu_excel_upload_list_totcnt(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		return eduMemberDAO.get_edu_excel_upload_list_totcnt(eduExcelUploadVO);
	}

	@Override
	public void set_edu_excel_upload_request(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		eduMemberDAO.set_edu_excel_upload_request(eduExcelUploadVO);
	}

	@Override
	public void set_edu_excel_upload_dtl_mod(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		eduMemberDAO.set_edu_excel_upload_dtl_mod(eduExcelUploadVO);
	}

	@Override
	public void set_edu_excel_upload_confm(EduExcelUploadVO eduExcelUploadVO) throws Exception {
		eduMemberDAO.set_edu_excel_upload_confm(eduExcelUploadVO);
	}

	@Override
	public void remove_edu_member_all_target(EduMemberVO delEduMemberVO) throws Exception {
		eduMemberDAO.remove_edu_member_all_target(delEduMemberVO);
	}
	
	@Override
	public void set_change_pwd(EduMemberVO eduMemberVO) throws Exception {
		eduMemberDAO.set_change_pwd(eduMemberVO);
	}

	@Override
	public void set_edu_excel_upload_del(EduExcelUploadVO excelUploadVO) throws Exception {
		eduMemberDAO.set_edu_excel_upload_del(excelUploadVO);
	}
	

	@Override
	public void set_edu_excel_upload_dtl_del(EduExcelUploadVO excelUploadVO) throws Exception {
		eduMemberDAO.set_edu_excel_upload_dtl_del(excelUploadVO);	
	}

	@Override
	public List<EduMemberVO> get_api_edu_member_list(EduMemberVO eduMemberInfo) {
		return eduMemberDAO.get_api_edu_member_list(eduMemberInfo);
	}

	@Override
	public int get_api_edu_member_list_totcnt(EduMemberVO eduMemberInfo) {
		return eduMemberDAO.get_api_edu_member_list_totcnt(eduMemberInfo);
	}
	
	@Override
	public void set_dpcn_member_dtl_mod(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.set_dpcn_member_dtl_mod(eduMemberVO);
	}
	
	@Override
	public void set_dpcn_member_edu_trgt_mod(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.set_dpcn_member_edu_trgt_mod(eduMemberVO);
	}
	
	@Override
	public void set_dpcn_member_edu_hstry_mod(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.set_dpcn_member_edu_hstry_mod(eduMemberVO);
	}
	
	@Override
	public void set_dpcn_member_edu_hstry_dtl_mod(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.set_dpcn_member_edu_hstry_dtl_mod(eduMemberVO);
	}
	
	@Override
	public void set_dpcn_member_edu_crtf_mod(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.set_dpcn_member_edu_crtf_mod(eduMemberVO);
	}
	
	@Override
	public void set_dpcn_member_edu_crtf_dtl_mod(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.set_dpcn_member_edu_crtf_dtl_mod(eduMemberVO);
	}
	
	@Override
	public void set_dpcn_member_survey_answer_mod(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.set_dpcn_member_survey_answer_mod(eduMemberVO);
	}
	
	@Override
	public void remove_edu_member_hstry(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.remove_edu_member_hstry(eduMemberVO);
	}
	
	@Override
	public void remove_edu_member_hstry_dtl(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.remove_edu_member_hstry_dtl(eduMemberVO);
	}
	
	@Override
	public void remove_edu_crtf(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.remove_edu_crtf(eduMemberVO);
	}
	
	@Override
	public void remove_edu_crtf_dtl(EduMemberVO eduMemberVO) throws Exception {
		// TODO Auto-generated method stub
		eduMemberDAO.remove_edu_crtf_dtl(eduMemberVO);
	}

}
