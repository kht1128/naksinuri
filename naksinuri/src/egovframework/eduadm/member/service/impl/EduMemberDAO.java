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

import org.springframework.stereotype.Repository;

import egovframework.eduadm.member.service.EduExcelUploadVO;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.member.service.LogAdmAuthVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("eduMemberDAO")
public class EduMemberDAO extends EgovAbstractDAO {

	public List<EduMemberVO> get_edu_member_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("get_edu_member_list",eduMemberVO);
	}

	public int get_edu_member_list_totcnt(EduMemberVO eduMemberVO) {
		return (int) select("get_edu_member_list_totcnt",eduMemberVO);
	}

	public EduMemberVO get_edu_member_info(EduMemberVO eduMemberVO) {
		return (EduMemberVO) select("get_edu_member_info",eduMemberVO);
	}

	public void remove_edu_member(EduMemberVO eduMemberVO) {
		update("remove_edu_member",eduMemberVO);
	}

	public void del_edu_member(EduMemberVO eduMemberVO) {
		update("del_edu_member",eduMemberVO);
	}

	public void set_edu_member_mod(EduMemberVO eduMemberVO) {
		update("set_edu_member_mod",eduMemberVO);
	}
	
	@SuppressWarnings("unchecked")
	public int get_id_search(EduMemberVO eduMemberVO) {
		 return (Integer)select("eduMember.get_id_search", eduMemberVO);
	}

	public String set_edu_member_reg(EduMemberVO eduMemberVO) {
		return (String) insert("set_edu_member_reg", eduMemberVO);
	}

	public void remove_edu_member_dtl(EduMemberVO eduMemberVO) {
		update("remove_edu_member_dtl",eduMemberVO);
	}

	public void set_edu_member_dtl_mod(EduMemberVO eduMemberVO) {
		update("set_edu_member_dtl_mod",eduMemberVO);
	}

	public String set_edu_member_dtl_reg(EduMemberVO eduMemberVO) {
		return (String) insert("set_edu_member_dtl_reg", eduMemberVO);
	}

	public int get_is_member_dtl(EduMemberVO eduMemberVO) {
		return (Integer)select("eduMember.get_is_member_dtl", eduMemberVO);
	}
	
	public List<EduMemberVO> get_edu_member_dtl_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_dtl_list",eduMemberVO);
	}
	
	public List<EduMemberVO> get_edu_member_dtl_all_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_dtl_all_list",eduMemberVO);
	}

	public List<EduMemberVO> get_edu_member_target_all_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_target_all_list",eduMemberVO);
	}

	public List<EduMemberVO> get_edu_member_target_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_target_list",eduMemberVO);
	}

	public int get_edu_member_target_list_totcnt(EduMemberVO eduMemberVO) {
		return (Integer)select("eduMember.get_edu_member_target_list_totcnt", eduMemberVO);
	}
	
	public List<EduMemberVO> get_edu_member_target_list_only(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_target_list_only",eduMemberVO);
	}

	public int get_edu_member_target_list_totcnt_only(EduMemberVO eduMemberVO) {
		return (Integer)select("eduMember.get_edu_member_target_list_totcnt_only", eduMemberVO);
	}

	public List<EduMemberVO> get_edu_member_add_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_add_list",eduMemberVO);
	}

	public int get_edu_member_add_list_totcnt(EduMemberVO eduMemberVO) {
		return (Integer)select("eduMember.get_edu_member_add_list_totcnt", eduMemberVO);
	}
	
	public List<EduMemberVO> get_edu_member_target_add_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_target_add_list",eduMemberVO);
	}

	public int get_edu_member_target_add_list_totcnt(EduMemberVO eduMemberVO) {
		return (Integer)select("eduMember.get_edu_member_target_add_list_totcnt", eduMemberVO);
	}

	public String set_edu_member_target_reg(EduMemberVO eduMemberVO) {
		return (String) insert("eduMember.set_edu_member_target_reg", eduMemberVO);
	}

	public void remove_edu_member_target(EduMemberVO eduMemberVO) {
		update("eduMember.remove_edu_member_target",eduMemberVO);
	}

	public void set_edu_member_target_mod(EduMemberVO eduMemberVO) {
		update("eduMember.set_edu_member_target_mod",eduMemberVO);
	}

	public List<EduMemberVO> get_edu_member_check_overlap_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_check_overlap_list",eduMemberVO);
	}
	
	public List<EduMemberVO> get_edu_member_check_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_check_list",eduMemberVO);
	}
	
	public List<EduMemberVO> get_edu_member_master_list(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_member_master_list",eduMemberVO);
	}

	public int get_edu_member_master_list_totcnt(EduMemberVO eduMemberVO) {
		return (int) select("eduMember.get_edu_member_master_list_totcnt",eduMemberVO);
	}

	public void set_edu_member_trnsfer(EduMemberVO eduMemberVO) {
		update("eduMember.set_edu_member_trnsfer",eduMemberVO);
	}

	public void set_edu_member_master_auth_mod(EduMemberVO eduMemberVO) {
		update("eduMember.set_edu_member_master_auth_mod",eduMemberVO);
	}

	public void set_edu_member_memo_mod(EduMemberVO eduMemberVO) {
		update("eduMember.set_edu_member_memo_mod",eduMemberVO);
	}

	public EduMemberVO get_edu_member_dtl_info(EduMemberVO eduMemberVO) {
		return (EduMemberVO) select("eduMember.get_edu_member_dtl_info",eduMemberVO);
	}

	public EduMemberVO get_edu_member_master_info(EduMemberVO eduMemberVO) {
		return (EduMemberVO) select("get_edu_member_master_info", eduMemberVO);
	}

	public List<LogAdmAuthVO> get_edu_member_author_log_list(LogAdmAuthVO logAdmAuthVO) {
		return (List<LogAdmAuthVO>) list("get_edu_member_author_log_list", logAdmAuthVO);
	}

	public int get_edu_member_author_log_list_totcnt(LogAdmAuthVO logAdmAuthVO) {
		return (int) select("get_edu_member_author_log_list_totcnt", logAdmAuthVO);
	}

	public void set_edu_member_author_log(LogAdmAuthVO logAdmAuthVO) {
		insert("set_edu_member_author_log", logAdmAuthVO);
	}

	public void set_edu_excel_upload_reg(EduExcelUploadVO eduExcelUploadVO) {
		insert("set_edu_excel_upload_reg", eduExcelUploadVO);
	}
	public void set_edu_excel_upload_dtl_reg(EduExcelUploadVO eduExcelUploadVO) {
		insert("set_edu_excel_upload_dtl_reg", eduExcelUploadVO);
	}

	@SuppressWarnings("unchecked")
	public List<EduExcelUploadVO> get_edu_excel_upload_list(EduExcelUploadVO eduExcelUploadVO) {
		return (List<EduExcelUploadVO>) list("get_edu_excel_upload_list", eduExcelUploadVO);
	}

	@SuppressWarnings("unchecked")
	public List<EduExcelUploadVO> get_edu_excel_upload_dtl_list(EduExcelUploadVO eduExcelUploadVO) {
		return (List<EduExcelUploadVO>) list("get_edu_excel_upload_dtl_list", eduExcelUploadVO);
	}

	@SuppressWarnings("unchecked")
	public List<EduMemberVO> get_edu_member_dtl_excel_compare(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("get_edu_member_dtl_excel_compare", eduMemberVO);
	}

	public void remove_edu_excel_upload_list(EduExcelUploadVO eduExcelUploadVO) {
		delete("remove_edu_excel_upload_list", eduExcelUploadVO);
	}
	public void remove_edu_excel_upload_dtl_list(EduExcelUploadVO eduExcelUploadVO) {
		delete("remove_edu_excel_upload_dtl_list", eduExcelUploadVO);
	}
	public EduExcelUploadVO get_edu_excel_upload_info(EduExcelUploadVO eduExcelUploadVO) {
		return (EduExcelUploadVO) select("get_edu_excel_upload_info", eduExcelUploadVO);
	}
	public int get_edu_excel_upload_list_totcnt(EduExcelUploadVO eduExcelUploadVO) {
		return (int) select("get_edu_excel_upload_list_totcnt", eduExcelUploadVO);
	}

	public void set_edu_excel_upload_request(EduExcelUploadVO eduExcelUploadVO) {
		update("set_edu_excel_upload_request", eduExcelUploadVO);
	}

	public void set_edu_excel_upload_dtl_mod(EduExcelUploadVO eduExcelUploadVO) {
		update("set_edu_excel_upload_dtl_mod", eduExcelUploadVO);
	}

	public void set_edu_excel_upload_confm(EduExcelUploadVO eduExcelUploadVO) {
		update("set_edu_excel_upload_confm", eduExcelUploadVO);
	}

	public void remove_edu_member_all_target(EduMemberVO delEduMemberVO) {
		delete("eduMember.remove_edu_member_all_target", delEduMemberVO);
	}
	
	public void set_change_pwd(EduMemberVO eduMemberVO) {
		update("set_change_pwd", eduMemberVO);
	}

	public void set_edu_excel_upload_del(EduExcelUploadVO excelUploadVO) {
		delete("set_edu_excel_upload_del", excelUploadVO);
	}

	public void set_edu_excel_upload_dtl_del(EduExcelUploadVO excelUploadVO) {
		delete("set_edu_excel_upload_dtl_del", excelUploadVO);
	}

	public List<EduMemberVO> get_api_edu_member_list(EduMemberVO eduMemberInfo) {
		return (List<EduMemberVO>) list("get_api_edu_member_list",eduMemberInfo);
	}

	public int get_api_edu_member_list_totcnt(EduMemberVO eduMemberInfo) {
		return (int) select("get_api_edu_member_list_totcnt", eduMemberInfo);
	}
	
	public void set_dpcn_member_dtl_mod(EduMemberVO eduMemberVO) {
		update("set_dpcn_member_dtl_mod",eduMemberVO);
	}
	
	public void set_dpcn_member_edu_trgt_mod(EduMemberVO eduMemberVO) {
		update("set_dpcn_member_edu_trgt_mod",eduMemberVO);
	}
	
	public void set_dpcn_member_edu_hstry_mod(EduMemberVO eduMemberVO) {
		update("set_dpcn_member_edu_hstry_mod",eduMemberVO);
	}
	
	public void set_dpcn_member_edu_hstry_dtl_mod(EduMemberVO eduMemberVO) {
		update("set_dpcn_member_edu_hstry_dtl_mod",eduMemberVO);
	}
	
	public void set_dpcn_member_edu_crtf_mod(EduMemberVO eduMemberVO) {
		update("set_dpcn_member_edu_crtf_mod",eduMemberVO);
	}
	
	public void set_dpcn_member_edu_crtf_dtl_mod(EduMemberVO eduMemberVO) {
		update("set_dpcn_member_edu_crtf_dtl_mod",eduMemberVO);
	}
	
	public void set_dpcn_member_survey_answer_mod(EduMemberVO eduMemberVO) {
		update("set_dpcn_member_survey_answer_mod",eduMemberVO);
	}
	
	public void remove_edu_member_hstry(EduMemberVO eduMemberVO) {
		delete("remove_edu_member_hstry",eduMemberVO);
	}
	
	public void remove_edu_member_hstry_dtl(EduMemberVO eduMemberVO) {
		delete("remove_edu_member_hstry_dtl",eduMemberVO);
	}
	
	public void remove_edu_crtf(EduMemberVO eduMemberVO) {
		delete("remove_edu_crtf",eduMemberVO);
	}
	
	public void remove_edu_crtf_dtl(EduMemberVO eduMemberVO) {
		delete("remove_edu_crtf_dtl",eduMemberVO);
	}
}
