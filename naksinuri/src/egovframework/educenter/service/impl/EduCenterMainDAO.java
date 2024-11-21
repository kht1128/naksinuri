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
package egovframework.educenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.main.service.EduMbrRemindersVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.educenter.service.EduCenterMainVO;
import egovframework.educenter.service.EduCenterMbrRemindersVO;
import egovframework.educenter.service.EduCprBplcVO;
import egovframework.educenter.service.EduSmsRequstVO;
import egovframework.educenter.service.MbrHpChngVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("eduCenterMainDAO")
public class EduCenterMainDAO extends EgovAbstractDAO {

	public List<EduCenterMainVO> get_educenter_main_curriculum_list(EduCenterMainVO eduCenterMainVO) {
		return (List<EduCenterMainVO>) list("get_educenter_main_curriculum_list",eduCenterMainVO);
	}
	
    public int get_educenter_main_curriculum_list_cnt(EduCenterMainVO eduCenterMainVO) {
        return (Integer)select("get_educenter_main_curriculum_list_cnt", eduCenterMainVO);
    }    	

	public EduCenterMainVO get_educenter_main_curriculum_upcoming_info() {
		return (EduCenterMainVO) select("get_educenter_main_curriculum_upcoming_info");
	}

	public EduCenterMainVO get_educenter_curriculum_info(EduCenterMainVO eduCenterMainVO) {
		return (EduCenterMainVO) select("get_educenter_curriculum_info",eduCenterMainVO);
	}
	
	public List<EduCenterMbrRemindersVO> get_educenter_rmndr_info_list(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) {
		return (List<EduCenterMbrRemindersVO>) list("get_educenter_rmndr_info_list", eduCenterMbrRemindersVO);
	}

	public List<EduCenterMainVO> get_educenter_curriculum_dtl_list(EduCenterMainVO eduCenterMainVO) {
		return (List<EduCenterMainVO>) list("get_educenter_curriculum_dtl_list",eduCenterMainVO);
	}

	public EduTrainingDataVO get_educenter_data_info(EduTrainingDataVO eduTrainingDataVO) {
		return (EduTrainingDataVO) select("get_edu_data_info",eduTrainingDataVO);
	}

	public void set_educenter_curriculum_mbr_cur_cnt_up(EduCenterMainVO eduCenterMainVO) {
		update("set_educenter_curriculum_mbr_cur_cnt_up",eduCenterMainVO);
	}

	public List<EduCategoryInsInfVO> get_educenter_category_ins_inf_list(EduCategoryInsInfVO eduCategoryInsInfVO) {
		return (List<EduCategoryInsInfVO>) list("get_educenter_category_ins_inf_list",eduCategoryInsInfVO);
	}

	public void set_educenter_mbr_rmndr_reg(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) {
		insert("set_educenter_mbr_rmndr_reg",eduCenterMbrRemindersVO);
	}

	public EduCenterMbrRemindersVO get_educenter_mbr_rmndr_info(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) {
		return (EduCenterMbrRemindersVO) select("get_educenter_mbr_rmndr_info",eduCenterMbrRemindersVO);
	}

	public void set_educenter_curriculum_mbr_cur_cnt_down(EduCenterMainVO eduCenterMainVO) {
		update("set_educenter_curriculum_mbr_cur_cnt_down",eduCenterMainVO);
	}

	public void set_educenter_curriculum_mbr_cmplt_cnt_down(EduCenterMainVO eduCenterMainVO) {
		update("set_educenter_curriculum_mbr_cmplt_cnt_down",eduCenterMainVO);
	}

	public void set_educenter_mbr_rmndr_indvdl_st(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) {
		update("set_educenter_mbr_rmndr_indvdl_st", eduCenterMbrRemindersVO);
	}

	public void set_educenter_sms_request(EduSmsRequstVO eduSmsRequstVO) {
		insert("set_educenter_sms_request", eduSmsRequstVO);
		
	}

	public void set_educenter_mbr_hp_chng_reg(MbrHpChngVO mbrHpChngVO) {
		insert("set_educenter_mbr_hp_chng_reg", mbrHpChngVO);
	}

	public void set_educenter_cpr_bplc_reg(EduCprBplcVO eduCprBplcVO) {
		insert("set_educenter_cpr_bplc_reg", eduCprBplcVO);
	}

	public void remove_eduCenter_mbr_reminders(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) {
		delete("remove_eduCenter_mbr_reminders",eduCenterMbrRemindersVO);
	}
	
	public List<EduCenterMainVO> get_edu_list(EduCenterMainVO eduCenterMainVO) {
		return (List<EduCenterMainVO>) list("get_edu_list",eduCenterMainVO);
	}

	public int get_edu_list_totcnt(EduCenterMainVO eduCenterMainVO) {
		// TODO Auto-generated method stub
		return (int)select("get_edu_list_totcnt",eduCenterMainVO);
	}
}
