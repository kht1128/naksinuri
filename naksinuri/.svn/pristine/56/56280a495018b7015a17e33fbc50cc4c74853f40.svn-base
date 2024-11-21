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
package egovframework.eduadm.main.service;

import java.util.List;

import egovframework.educenter.service.EduCprBplcVO;
import egovframework.educenter.service.MbrHpChngVO;

public interface EduCenterService {	
	//정보조회 - 방문방법  -----------
	public List<EduCategoryVisitInfVO> get_edu_category_visit_inf_list(EduCategoryVisitInfVO eduCategoryVisitInfVO) throws Exception;
	//정보조죄 - 사전교육알림
	public List<EduCategoryRmndrInfVO> get_edu_category_rmndr_inf_list(EduCategoryRmndrInfVO eduCategoryRmndrInfVO) throws Exception;
	//온라인교육신청(심사) 
	public List<EduMbrRemindersVO> get_edu_mbr_reminders_list(EduMbrRemindersVO eduMbrRemindersVO) throws Exception;
	public int get_edu_mbr_reminders_list_totcnt(EduMbrRemindersVO eduMbrRemindersVO) throws Exception;
	public EduMbrRemindersVO get_edu_mbr_reminders_info(EduMbrRemindersVO eduMbrRemindersVO) throws Exception;
	public void set_edu_mbr_reminders_lock_cmplt(EduMbrRemindersVO eduMbrRemindersVO) throws Exception;
	public void set_edu_mbr_reminders_recovery(EduMbrRemindersVO eduMbrRemindersVO) throws Exception;
	public void remove_edu_mbr_reminders(EduMbrRemindersVO eduMbrRemindersVO) throws Exception;
	public void del_edu_mbr_reminders(EduMbrRemindersVO eduMbrRemindersVO) throws Exception;
	public void set_edu_mbr_reminders_memo_mod(EduMbrRemindersVO eduMbrRemindersVO) throws Exception;//메모저장
	//문자신청리스트
	public List<EduAdmSmsRequstVO> get_edu_sms_request_list(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception;
	public int get_edu_sms_request_list_totcnt(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception;
	public EduAdmSmsRequstVO get_edu_sms_request_info(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception;
	public void set_edu_sms_request_recovery(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception;
	public void remove_edu_sms_request(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception;
	public void del_edu_sms_request(EduAdmSmsRequstVO eduAdmSmsRequstVO) throws Exception;
	//본인명의 휴대폰 미소지자 정보 변경 신청서 리스트
	public List<EduAdmMbrHpChngVO> get_mbr_hp_chng_list(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) throws Exception;
	public int get_mbr_hp_chng_list_totcnt(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) throws Exception;
	public void set_mbr_hp_chng(EduAdmMbrHpChngVO eduAdmMbrHpChngVO) throws Exception;
	//법인사업장 교육책임자 지정 확인서 리스트
	public List<EduCprBplcVO> get_cpr_bplc_list(EduAdmCprBplcVO eduAdmCprBplcVO) throws Exception;
	public int get_cpr_bplc_list_totcnt(EduAdmCprBplcVO eduAdmCprBplcVO) throws Exception;
	public EduAdmCprBplcVO get_edu_cpr_bplc_info(EduAdmCprBplcVO eduAdmCprBplcVO) throws Exception;
	public void set_edu_cpr_bplc(EduAdmCprBplcVO eduAdmCprBplcVO) throws Exception;
	

}
