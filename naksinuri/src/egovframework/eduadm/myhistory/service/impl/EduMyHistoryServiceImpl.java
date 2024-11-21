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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("eduMyHistoryService")
public class EduMyHistoryServiceImpl extends EgovAbstractServiceImpl implements EduMyHistoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduMyHistoryServiceImpl.class);

	@Resource(name = "eduMyHistoryDAO")
	private EduMyHistoryDAO eduMyHistoryDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	//수강내역 -----------
	@Override
	public List<EduMemberVO> get_edu_mbr_list_only_mbrhstry(EduMemberVO eduMemberVO) {
		return eduMyHistoryDAO.get_edu_mbr_list_only_mbrhstry(eduMemberVO);
	}
	
	@Override
	public List<EduMyHistoryVO> get_edu_mbrhstry_list(EduMyHistoryVO eduMyHistoryVO) {
		return eduMyHistoryDAO.get_edu_mbrhstry_list(eduMyHistoryVO);
	}

	@Override
	public List<EduMyHistoryVO> get_edu_mbrhstry_list_totcnt(EduMyHistoryVO eduMyHistoryVO) {
		return eduMyHistoryDAO.get_edu_mbrhstry_list_totcnt(eduMyHistoryVO);
	}
	
	@Override
	public List<EduMyHistoryVO> get_edu_mbrhstry_dtl_list(EduMyHistoryVO eduMyHistoryVO) {
		return eduMyHistoryDAO.get_edu_mbrhstry_dtl_list(eduMyHistoryVO);
	}

	@Override
	public int get_edu_mbrhstry_dtl_list_totcnt(EduMyHistoryVO eduMyHistoryVO) {
		return eduMyHistoryDAO.get_edu_mbrhstry_dtl_list_totcnt(eduMyHistoryVO);
	}

	@Override
	public EduMyHistoryVO get_edu_mbrhstry_info(EduMyHistoryVO eduMyHistoryVO) {
		return eduMyHistoryDAO.get_edu_mbrhstry_info(eduMyHistoryVO);
	}
	
	@Override
	public EduMyHistoryVO get_edu_mbrhstry_info_dtl(EduMyHistoryVO eduMyHistoryVO) {
		return eduMyHistoryDAO.get_edu_mbrhstry_info_dtl(eduMyHistoryVO);
	}

	@Override
	public int get_edu_mbr_list_only_mbrhstry_totcnt(EduMemberVO eduMemberVO) {
		return eduMyHistoryDAO.get_edu_mbr_list_only_mbrhstry_totcnt(eduMemberVO);
	}

	@Override
	public void set_edu_mbrhstry_mod(EduMyHistoryVO eduMyHistoryVO) {
		eduMyHistoryDAO.set_edu_mbrhstry_mod(eduMyHistoryVO);
	}
	
	@Override
	public void set_edu_mbrhstry_mod_dtl(EduMyHistoryVO eduMyHistoryVO) {
		eduMyHistoryDAO.set_edu_mbrhstry_mod_dtl(eduMyHistoryVO);
	}

	@Override
	public void del_edu_mbrhstry(EduMyHistoryVO eduMyHistoryVO) {
		eduMyHistoryDAO.del_edu_mbrhstry(eduMyHistoryVO);
	}
	
	@Override
	public void del_edu_mbrhstry_dtl(EduMyHistoryVO eduMyHistoryVO) {
		eduMyHistoryDAO.del_edu_mbrhstry_dtl(eduMyHistoryVO);
	}

	@Override
	public String set_edu_mbrhstry_reg(EduMyHistoryVO eduMyHistoryVO) {
		return eduMyHistoryDAO.set_edu_mbrhstry_reg(eduMyHistoryVO);
	}
	
	@Override
	public String set_edu_mbrhstry_reg_dtl(EduMyHistoryVO eduMyHistoryVO) {
		return eduMyHistoryDAO.set_edu_mbrhstry_reg_dtl(eduMyHistoryVO);
	}

	@Override
	public void remove_edu_mbrhstry(EduMyHistoryVO eduMyHistoryVO) {
		eduMyHistoryDAO.remove_edu_mbrhstry(eduMyHistoryVO);
	}
	
	@Override
	public void remove_edu_mbrhstry_dtl(EduMyHistoryVO eduMyHistoryVO) {
		eduMyHistoryDAO.remove_edu_mbrhstry_dtl(eduMyHistoryVO);
	}

	@Override
	public void set_edu_mbrhstry_cur_cmplt_up(EduMyHistoryVO eduMyHistoryVO) {
		eduMyHistoryDAO.set_edu_mbrhstry_cur_cmplt_up(eduMyHistoryVO);
	}
	
	@Override
	public void set_edu_mbrhstry_cur_cmplt_down(EduMyHistoryVO eduMyHistoryVO) {
		eduMyHistoryDAO.set_edu_mbrhstry_cur_cmplt_down(eduMyHistoryVO);
	}

	@Override
	public List<EgovMap> get_edu_mbrhstry_area_result(EgovMap prameters) {
		return eduMyHistoryDAO.get_edu_mbrhstry_area_result(prameters);
	}

	@Override
	public boolean get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(String HMBR_SN) {
		int cnt = eduMyHistoryDAO.get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(HMBR_SN);
		return cnt>0?false:true;
	}

	@Override
	public boolean get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(String HMBR_DTL_SN) {
		int cnt = eduMyHistoryDAO.get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(HMBR_DTL_SN);
		return cnt>0?false:true;
	}
	
}
