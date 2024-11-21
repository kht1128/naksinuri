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
package egovframework.eduadm.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.eduadm.main.service.EduAdmCprBplcVO;
import egovframework.eduadm.main.service.EduAdmMbrHpChngVO;
import egovframework.eduadm.main.service.EduAdmSmsRequstVO;
import egovframework.eduadm.main.service.EduCategoryRmndrInfVO;
import egovframework.eduadm.main.service.EduCategoryVisitInfVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.main.service.EduMbrRemindersVO;
import egovframework.educenter.service.EduCprBplcVO;
import egovframework.educenter.service.MbrHpChngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eduCenterService")
public class EduCenterServiceImpl extends EgovAbstractServiceImpl implements EduCenterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterServiceImpl.class);

	@Resource(name = "educenterDAO")
	private EducenterDAO educenterDAO;

	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	
	//정보조회 - 방문방법 -----------
	@Override
	public List<EduCategoryVisitInfVO> get_edu_category_visit_inf_list(EduCategoryVisitInfVO eduCategoryVisitInfVO) throws Exception {
		return educenterDAO.get_edu_category_visit_inf_list(eduCategoryVisitInfVO);
	}
	
	//정보조회 - 사전교육알림
	@Override
	public List<EduCategoryRmndrInfVO> get_edu_category_rmndr_inf_list(EduCategoryRmndrInfVO eduCategoryRmndrInfVO) throws Exception {
		return educenterDAO.get_edu_category_rmndr_inf_list(eduCategoryRmndrInfVO);
	}
	
	//사전교육알림신청리스트
	@Override
	public List<EduMbrRemindersVO> get_edu_mbr_reminders_list(EduMbrRemindersVO eduMbrRemindersVO) throws Exception {
		return educenterDAO.get_edu_mbr_reminders_list(eduMbrRemindersVO);
	}
	@Override
	public int get_edu_mbr_reminders_list_totcnt(EduMbrRemindersVO eduMbrRemindersVO) throws Exception {
		return educenterDAO.get_edu_mbr_reminders_list_totcnt(eduMbrRemindersVO);
	}
	@Override
	public EduMbrRemindersVO get_edu_mbr_reminders_info(EduMbrRemindersVO eduMbrRemindersVO) throws Exception {
		return educenterDAO.get_edu_mbr_reminders_info(eduMbrRemindersVO);
	}
	@Override
	public void set_edu_mbr_reminders_lock_cmplt(EduMbrRemindersVO eduMbrRemindersVO) throws Exception {
		educenterDAO.set_edu_mbr_reminders_lock_cmplt(eduMbrRemindersVO);
	}
	@Override
	public void set_edu_mbr_reminders_recovery(EduMbrRemindersVO eduMbrRemindersVO) throws Exception {
		educenterDAO.set_edu_mbr_reminders_recovery(eduMbrRemindersVO);
	}
	@Override
	public void remove_edu_mbr_reminders(EduMbrRemindersVO eduMbrRemindersVO) throws Exception {
		educenterDAO.remove_edu_mbr_reminders(eduMbrRemindersVO);
	}
	@Override
	public void del_edu_mbr_reminders(EduMbrRemindersVO eduMbrRemindersVO) throws Exception {
		educenterDAO.del_edu_mbr_reminders(eduMbrRemindersVO);
	}

	@Override
	public List<EduAdmSmsRequstVO> get_edu_sms_request_list(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception {
		return educenterDAO.get_edu_sms_request_list(eduAdmSmsRequstVO);
	}
	@Override
	public int get_edu_sms_request_list_totcnt(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception {
		return educenterDAO.get_edu_sms_request_list_totcnt(eduAdmSmsRequstVO);
	}

	@Override
	public List<EduAdmMbrHpChngVO> get_mbr_hp_chng_list(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) throws Exception {
		return educenterDAO.get_mbr_hp_chng_list(eduAdmMbrHpChngVO);
	}

	@Override
	public int get_mbr_hp_chng_list_totcnt(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) throws Exception {
		return educenterDAO.get_mbr_hp_chng_list_totcnt(eduAdmMbrHpChngVO);
	}

	@Override
	public List<EduCprBplcVO> get_cpr_bplc_list(EduAdmCprBplcVO eduAdmCprBplcVO) throws Exception {
		return educenterDAO.get_cpr_bplc_list(eduAdmCprBplcVO);
	}

	@Override
	public int get_cpr_bplc_list_totcnt(EduAdmCprBplcVO eduAdmCprBplcVO) throws Exception {
		return educenterDAO.get_cpr_bplc_list_totcnt(eduAdmCprBplcVO);
	}

	@Override
	public EduAdmCprBplcVO get_edu_cpr_bplc_info(EduAdmCprBplcVO eduAdmCprBplcVO) throws Exception {
		return educenterDAO.get_edu_cpr_bplc_info(eduAdmCprBplcVO);
	}

	@Override
	public void set_edu_cpr_bplc(EduAdmCprBplcVO eduAdmCprBplcVO) throws Exception {
		educenterDAO.set_edu_cpr_bplc(eduAdmCprBplcVO);		
	}

	@Override
	public void set_mbr_hp_chng(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) throws Exception {
		educenterDAO.set_mbr_hp_chng(eduAdmMbrHpChngVO);
	}

	@Override
	public EduAdmSmsRequstVO get_edu_sms_request_info(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception {
		return educenterDAO.get_edu_sms_request_info(eduAdmSmsRequstVO);
	}

	@Override
	public void set_edu_sms_request_recovery(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception {
		educenterDAO.set_edu_sms_request_recovery(eduAdmSmsRequstVO);
	}

	@Override
	public void remove_edu_sms_request(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception {
		educenterDAO.remove_edu_sms_request(eduAdmSmsRequstVO);
	}

	@Override
	public void del_edu_sms_request(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception {
		educenterDAO.del_edu_sms_request(eduAdmSmsRequstVO);
	}

	@Override
	public void set_edu_mbr_reminders_memo_mod(EduMbrRemindersVO eduMbrRemindersVO) throws Exception {
		educenterDAO.set_edu_mbr_reminders_memo_mod(eduMbrRemindersVO);
	}
	

}
