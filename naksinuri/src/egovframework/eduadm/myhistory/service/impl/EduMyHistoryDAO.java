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
package egovframework.eduadm.myhistory.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("eduMyHistoryDAO")
public class EduMyHistoryDAO extends EgovAbstractDAO {

	//수강내역 -----------
	public List<EduMemberVO> get_edu_mbr_list_only_mbrhstry(EduMemberVO eduMemberVO) {
		return (List<EduMemberVO>) list("eduMember.get_edu_mbr_list_only_mbrhstry",eduMemberVO);
	}

	public List<EduMyHistoryVO> get_edu_mbrhstry_list(EduMyHistoryVO eduMyHistoryVO) {
		return (List<EduMyHistoryVO>) list("get_edu_mbrhstry_list",eduMyHistoryVO);
	}

	public List<EduMyHistoryVO> get_edu_mbrhstry_list_totcnt(EduMyHistoryVO eduMyHistoryVO) {
		return (List<EduMyHistoryVO>) list("get_edu_mbrhstry_list_totcnt",eduMyHistoryVO);
	}
	
	public List<EduMyHistoryVO> get_edu_mbrhstry_dtl_list(EduMyHistoryVO eduMyHistoryVO) {
		return (List<EduMyHistoryVO>) list("get_edu_mbrhstry_dtl_list",eduMyHistoryVO);
	}

	public int get_edu_mbrhstry_dtl_list_totcnt(EduMyHistoryVO eduMyHistoryVO) {
		return (int) select("get_edu_mbrhstry_dtl_list_totcnt",eduMyHistoryVO);
	}

	public EduMyHistoryVO get_edu_mbrhstry_info(EduMyHistoryVO eduMyHistoryVO) {
		return (EduMyHistoryVO) select("get_edu_mbrhstry_info",eduMyHistoryVO);
	}
	
	public EduMyHistoryVO get_edu_mbrhstry_info_dtl(EduMyHistoryVO eduMyHistoryVO) {
		return (EduMyHistoryVO) select("get_edu_mbrhstry_info_dtl",eduMyHistoryVO);
	}

	public int get_edu_mbr_list_only_mbrhstry_totcnt(EduMemberVO eduMemberVO) {
		return (int) select("eduMember.get_edu_mbr_list_only_mbrhstry_totcnt",eduMemberVO);
	}

	public void set_edu_mbrhstry_mod(EduMyHistoryVO eduMyHistoryVO) {
		update("set_edu_mbrhstry_mod",eduMyHistoryVO);
	}
	

	public void set_edu_mbrhstry_mod_dtl(EduMyHistoryVO eduMyHistoryVO) {
		update("set_edu_mbrhstry_mod_dtl",eduMyHistoryVO);
	}

	public void del_edu_mbrhstry(EduMyHistoryVO eduMyHistoryVO) {
		update("del_edu_mbrhstry",eduMyHistoryVO);
	}
	
	public void del_edu_mbrhstry_dtl(EduMyHistoryVO eduMyHistoryVO) {
		update("del_edu_mbrhstry_dtl",eduMyHistoryVO);
	}

	public String set_edu_mbrhstry_reg(EduMyHistoryVO eduMyHistoryVO) {
		return (String)insert("set_edu_mbrhstry_reg",eduMyHistoryVO);
	}

	public String set_edu_mbrhstry_reg_dtl(EduMyHistoryVO eduMyHistoryVO) {
		return (String)insert("set_edu_mbrhstry_reg_dtl",eduMyHistoryVO);
	}

	public void remove_edu_mbrhstry(EduMyHistoryVO eduMyHistoryVO) {
		delete("remove_edu_mbrhstry",eduMyHistoryVO);
	}

	public void remove_edu_mbrhstry_dtl(EduMyHistoryVO eduMyHistoryVO) {
		delete("remove_edu_mbrhstry_dtl",eduMyHistoryVO);
	}

	public void set_edu_mbrhstry_cur_cmplt_up(EduMyHistoryVO eduMyHistoryVO) {
		update("set_edu_mbrhstry_cur_cmplt_up",eduMyHistoryVO);
	}

	public void set_edu_mbrhstry_cur_cmplt_down(EduMyHistoryVO eduMyHistoryVO) {
		update("set_edu_mbrhstry_cur_cmplt_down",eduMyHistoryVO);
	}

	public List<EgovMap> get_edu_mbrhstry_area_result(EgovMap prameters) {
		return (List<EgovMap>)list("get_edu_mbrhstry_area_result",prameters);
	}

	public int get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(String HMBR_SN) {
		return (int) select("get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn",HMBR_SN);
	}

	public int get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(String HMBR_DTL_SN) {
		return (int) select("get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn",HMBR_DTL_SN);
	}

}
