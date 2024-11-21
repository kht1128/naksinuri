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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduMbrRemindersVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.educenter.service.EduCenterMainService;
import egovframework.educenter.service.EduCenterMainVO;
import egovframework.educenter.service.EduCenterMbrRemindersVO;
import egovframework.educenter.service.EduCprBplcVO;
import egovframework.educenter.service.EduSmsRequstVO;
import egovframework.educenter.service.MbrHpChngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eduCenterMainService")
public class EduCenterMainServiceImpl extends EgovAbstractServiceImpl implements EduCenterMainService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterMainServiceImpl.class);

	@Resource(name = "eduCenterMainDAO")
	private EduCenterMainDAO eduCenterMainDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;


	@Override
	public List<EduCenterMainVO> get_educenter_main_curriculum_list(EduCenterMainVO eduCenterMainVO) throws Exception {
		return eduCenterMainDAO.get_educenter_main_curriculum_list(eduCenterMainVO);
	}
	
    @Override
	public int get_educenter_main_curriculum_list_cnt(EduCenterMainVO eduCenterMainVO) throws Exception  {
    	return eduCenterMainDAO.get_educenter_main_curriculum_list_cnt(eduCenterMainVO);
    }


	@Override
	public EduCenterMainVO get_educenter_main_curriculum_upcoming_info() throws Exception {
		return eduCenterMainDAO.get_educenter_main_curriculum_upcoming_info();
	}


	@Override
	public EduCenterMainVO get_educenter_curriculum_info(EduCenterMainVO eduCenterMainVO) throws Exception {
		return eduCenterMainDAO.get_educenter_curriculum_info(eduCenterMainVO);
	}
	
	@Override
	public List<EduCenterMbrRemindersVO> get_educenter_rmndr_info_list(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception {
		return eduCenterMainDAO.get_educenter_rmndr_info_list(eduCenterMbrRemindersVO);
	}


	@Override
	public List<EduCenterMainVO> get_educenter_curriculum_dtl_list(EduCenterMainVO eduCenterMainVO) throws Exception {
		return eduCenterMainDAO.get_educenter_curriculum_dtl_list(eduCenterMainVO);
	}


	@Override
	public EduTrainingDataVO get_educenter_data_info(EduTrainingDataVO eduTrainingDataVO) throws Exception {
		return eduCenterMainDAO.get_educenter_data_info(eduTrainingDataVO);
	}


	@Override
	public void set_educenter_curriculum_mbr_cur_cnt_up(EduCenterMainVO eduCenterMainVO) throws Exception {
		eduCenterMainDAO.set_educenter_curriculum_mbr_cur_cnt_up(eduCenterMainVO);
	}


	@Override
	public List<EduCategoryInsInfVO> get_educenter_category_ins_inf_list(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception {
		return eduCenterMainDAO.get_educenter_category_ins_inf_list(eduCategoryInsInfVO);
	}


	@Override
	public void set_educenter_mbr_rmndr_reg(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception {
		eduCenterMainDAO.set_educenter_mbr_rmndr_reg(eduCenterMbrRemindersVO);
	}


	@Override
	public EduCenterMbrRemindersVO get_educenter_mbr_rmndr_info(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception {
		return eduCenterMainDAO.get_educenter_mbr_rmndr_info(eduCenterMbrRemindersVO);
	}


	@Override
	public void set_educenter_curriculum_mbr_cur_cnt_down(EduCenterMainVO eduCenterMainVO) throws Exception {
		eduCenterMainDAO.set_educenter_curriculum_mbr_cur_cnt_down(eduCenterMainVO);
	}


	@Override
	public void set_educenter_curriculum_mbr_cmplt_cnt_down(EduCenterMainVO eduCenterMainVO) throws Exception {
		eduCenterMainDAO.set_educenter_curriculum_mbr_cmplt_cnt_down(eduCenterMainVO);
	}

	@Override
	public void set_educenter_mbr_rmndr_indvdl_st(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception {
		eduCenterMainDAO.set_educenter_mbr_rmndr_indvdl_st(eduCenterMbrRemindersVO);
	}


	@Override
	public void set_educenter_sms_request(EduSmsRequstVO eduSmsRequstVO) throws Exception {
		eduCenterMainDAO.set_educenter_sms_request(eduSmsRequstVO);
	}


	@Override
	public void set_educenter_mbr_hp_chng_reg(MbrHpChngVO mbrHpChngVO) throws Exception {
		eduCenterMainDAO.set_educenter_mbr_hp_chng_reg(mbrHpChngVO);
	}


	@Override
	public void set_educenter_cpr_bplc_reg(EduCprBplcVO eduCprBplcVO) throws Exception {
		eduCenterMainDAO.set_educenter_cpr_bplc_reg(eduCprBplcVO);
	}

	@Override
	public void remove_eduCenter_mbr_reminders(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception {
		eduCenterMainDAO.remove_eduCenter_mbr_reminders(eduCenterMbrRemindersVO);
	}
	@Override
	public List<EduCenterMainVO> get_edu_list(EduCenterMainVO eduCenterMainVO) {
		return eduCenterMainDAO.get_edu_list(eduCenterMainVO);
	}

	@Override
	public int get_edu_list_totcnt(EduCenterMainVO eduCenterMainVO) {
		// TODO Auto-generated method stub
		return eduCenterMainDAO.get_edu_list_totcnt(eduCenterMainVO);
	}

}
