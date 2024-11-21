package egovframework.educenter.service;

import java.util.List;

import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduMbrRemindersVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;

public interface EduCenterMainService {

	List<EduCenterMainVO> get_educenter_main_curriculum_list(EduCenterMainVO eduCenterMainVO) throws Exception;
    public int get_educenter_main_curriculum_list_cnt(EduCenterMainVO eduCenterMainVO) throws Exception;
	EduCenterMainVO get_educenter_main_curriculum_upcoming_info() throws Exception;
	EduCenterMainVO get_educenter_curriculum_info(EduCenterMainVO eduCenterMainVO) throws Exception;
	List<EduCenterMbrRemindersVO> get_educenter_rmndr_info_list(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception;
	List<EduCenterMainVO> get_educenter_curriculum_dtl_list(EduCenterMainVO eduCenterMainVO) throws Exception;
	EduTrainingDataVO get_educenter_data_info(EduTrainingDataVO eduTrainingDataVO) throws Exception;
	void set_educenter_curriculum_mbr_cur_cnt_up(EduCenterMainVO eduCenterMainVO) throws Exception;
	List<EduCategoryInsInfVO> get_educenter_category_ins_inf_list(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception;
	void set_educenter_mbr_rmndr_reg(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception;
	EduCenterMbrRemindersVO get_educenter_mbr_rmndr_info(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception;
	void set_educenter_curriculum_mbr_cur_cnt_down(EduCenterMainVO eduCenterMainVO) throws Exception;
	void set_educenter_curriculum_mbr_cmplt_cnt_down(EduCenterMainVO eduCenterMainVO) throws Exception;
	void set_educenter_mbr_rmndr_indvdl_st(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception;
	void set_educenter_sms_request(EduSmsRequstVO eduSmsRequstVO) throws Exception;
	void set_educenter_mbr_hp_chng_reg(MbrHpChngVO mbrHpChngVO) throws Exception;
	void set_educenter_cpr_bplc_reg(EduCprBplcVO eduCprBplcVO) throws Exception;
	void remove_eduCenter_mbr_reminders(EduCenterMbrRemindersVO eduCenterMbrRemindersVO) throws Exception;
	
	List<EduCenterMainVO> get_edu_list(EduCenterMainVO eduCenterMainVO);
	int get_edu_list_totcnt(EduCenterMainVO eduCenterMainVO);
}
