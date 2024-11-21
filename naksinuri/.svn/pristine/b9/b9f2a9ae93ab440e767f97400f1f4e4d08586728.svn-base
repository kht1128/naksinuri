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

import org.springframework.stereotype.Repository;

import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.main.service.EduAdmCprBplcVO;
import egovframework.eduadm.main.service.EduAdmMbrHpChngVO;
import egovframework.eduadm.main.service.EduAdmSmsRequstVO;
import egovframework.eduadm.main.service.EduCategoryRmndrInfVO;
import egovframework.eduadm.main.service.EduCategoryVisitInfVO;
import egovframework.eduadm.main.service.EduMbrRemindersVO;
import egovframework.educenter.service.EduCprBplcVO;
import egovframework.educenter.service.MbrHpChngVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("educenterDAO")
public class EducenterDAO extends EgovAbstractDAO {

	//정보조회 - 방문방법 -----------.
	public List<EduCategoryVisitInfVO> get_edu_category_visit_inf_list(EduCategoryVisitInfVO eduCategoryVisitInfVO) {
		return (List<EduCategoryVisitInfVO>) list("get_edu_category_visit_inf_list",eduCategoryVisitInfVO);
	}
	
	//정보조회 - 사전교육알림
	public List<EduCategoryRmndrInfVO> get_edu_category_rmndr_inf_list(EduCategoryRmndrInfVO eduCategoryRmndrInfVO) {
		return (List<EduCategoryRmndrInfVO>) list("get_edu_category_rmndr_inf_list",eduCategoryRmndrInfVO);
	}
	
	//사전교육알림 신청리스트
	public List<EduMbrRemindersVO> get_edu_mbr_reminders_list(EduMbrRemindersVO eduCenterMbrRemindersVO) {
		return (List<EduMbrRemindersVO>) list("get_edu_mbr_reminders_list",eduCenterMbrRemindersVO);
	}
	public int get_edu_mbr_reminders_list_totcnt(EduMbrRemindersVO eduCenterMbrRemindersVO) {
		return (int) select("get_edu_mbr_reminders_list_totcnt",eduCenterMbrRemindersVO);
	}
	public EduMbrRemindersVO get_edu_mbr_reminders_info(EduMbrRemindersVO eduMbrRemindersVO) {
		return (EduMbrRemindersVO) select("get_edu_mbr_reminders_info",eduMbrRemindersVO);
	}
	public void set_edu_mbr_reminders_lock_cmplt(EduMbrRemindersVO eduMbrRemindersVO) {
		update("set_edu_mbr_reminders_lock_cmplt",eduMbrRemindersVO);
	}
	public void set_edu_mbr_reminders_recovery(EduMbrRemindersVO eduMbrRemindersVO) {
		update("set_edu_mbr_reminders_recovery",eduMbrRemindersVO);
	}
	public void remove_edu_mbr_reminders(EduMbrRemindersVO eduMbrRemindersVO) {
		delete("remove_edu_mbr_reminders",eduMbrRemindersVO);
	}
	public void del_edu_mbr_reminders(EduMbrRemindersVO eduMbrRemindersVO) {
		update("del_edu_mbr_reminders",eduMbrRemindersVO);
	}

	@SuppressWarnings("unchecked")
	public List<EduAdmSmsRequstVO> get_edu_sms_request_list(EduAdmSmsRequstVO eduAdmSmsRequstVO) {
		return (List<EduAdmSmsRequstVO>) list("get_edu_sms_request_list", eduAdmSmsRequstVO);
	}
	public int get_edu_sms_request_list_totcnt(EduAdmSmsRequstVO eduAdmSmsRequstVO) {
		return (int) select("get_edu_sms_request_list_totcnt", eduAdmSmsRequstVO);
	}
	//본인명의 휴대폰 변경 미소지자 정보 변경 신청서 리스트
	@SuppressWarnings("unchecked")
	public List<EduAdmMbrHpChngVO> get_mbr_hp_chng_list(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) {
		return (List<EduAdmMbrHpChngVO>) list("get_mbr_hp_chng_list", eduAdmMbrHpChngVO);
	}

	public int get_mbr_hp_chng_list_totcnt(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) {
		return (int) select("get_mbr_hp_chng_list_totcnt", eduAdmMbrHpChngVO);
	}
	//법인사업장 교육책임자 지정 확인서 리스트
	@SuppressWarnings("unchecked")
	public List<EduCprBplcVO> get_cpr_bplc_list(EduAdmCprBplcVO eduAdmCprBplcVO) {
		return (List<EduCprBplcVO>) list("get_cpr_bplc_list", eduAdmCprBplcVO);
	}

	public int get_cpr_bplc_list_totcnt(EduAdmCprBplcVO EduAdmCprBplcVO) {
		return (int) select("get_cpr_bplc_list_totcnt", EduAdmCprBplcVO);
	}

	public EduAdmCprBplcVO get_edu_cpr_bplc_info(EduAdmCprBplcVO eduAdmCprBplcVO) {
		return (EduAdmCprBplcVO) select("get_edu_cpr_bplc_info", eduAdmCprBplcVO);
	}

	public void set_edu_cpr_bplc(EduAdmCprBplcVO eduAdmCprBplcVO) {
		update("set_edu_cpr_bplc", eduAdmCprBplcVO);
	}

	public void set_mbr_hp_chng(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) {
		update("set_mbr_hp_chng", eduAdmMbrHpChngVO);
	}

	public EduAdmSmsRequstVO get_edu_sms_request_info(EduAdmSmsRequstVO eduAdmSmsRequstVO) {
		return (EduAdmSmsRequstVO) select("get_edu_sms_request_info", eduAdmSmsRequstVO);
	}

	public void set_edu_sms_request_recovery(EduAdmSmsRequstVO eduAdmSmsRequstVO) {
		update("set_edu_sms_request_recovery", eduAdmSmsRequstVO);
	}

	public void remove_edu_sms_request(EduAdmSmsRequstVO eduAdmSmsRequstVO) {
		delete("remove_edu_sms_request", eduAdmSmsRequstVO);
	}

	public void del_edu_sms_request(EduAdmSmsRequstVO eduAdmSmsRequstVO) {
		update("del_edu_sms_request", eduAdmSmsRequstVO);
	}

	public void set_edu_mbr_reminders_memo_mod(EduMbrRemindersVO eduMbrRemindersVO) {
		update("set_edu_mbr_reminders_memo_mod", eduMbrRemindersVO);
	}

}
