package egovframework.eduadm.curriculum.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface EduCurriculumService {
	//교육과정(커리큘럼) ----------- 
	public List<EduCurriculumVO> get_edu_curriculum_list(EduCurriculumVO eduCurriculumVO) throws Exception;
	public List<EduCurriculumVO> get_edu_curriculum_dtl_list(EduCurriculumVO eduCurriculumVO) throws Exception;
	public int get_edu_curriculum_list_totcnt(EduCurriculumVO eduCurriculumVO) throws Exception;
	public int get_edu_curriculum_dtl_list_totcnt(EduCurriculumVO eduCurriculumVO) throws Exception;
	public EduCurriculumVO get_edu_curriculum_info(EduCurriculumVO eduCurriculumVO) throws Exception;
	public EduCurriculumVO get_edu_curriculum_dtl_info(EduCurriculumVO eduCurriculumVO) throws Exception;
	public String set_edu_curriculum_reg(EduCurriculumVO eduCurriculumVO) throws Exception;
	public String set_edu_curriculum_reg_dtl(EduCurriculumVO eduCurriculumVO) throws Exception;
	public void set_edu_curriculum_mod(EduCurriculumVO eduCurriculumVO) throws Exception;
	public void set_edu_curriculum_mod_dtl(EduCurriculumVO eduCurriculumVO) throws Exception;
	public void del_edu_curriculum(EduCurriculumVO eduCurriculumVO) throws Exception;
	public void del_edu_curriculum_dtl(EduCurriculumVO eduCurriculumVO) throws Exception;
	
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public void set_edu_curriculum_mbr_cur_cnt_up(EduCurriculumVO eduCurriculumVO) throws Exception;
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public void set_edu_curriculum_mbr_cur_cnt_down(EduCurriculumVO eduCurriculumVO) throws Exception;
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public void set_edu_curriculum_mbr_cmplt_cnt_up(EduCurriculumVO updateEduCurriculumVO) throws Exception;
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public void set_edu_curriculum_mbr_cmplt_cnt_down(EduCurriculumVO updateEduCurriculumVO) throws Exception;
	
	public void remove_edu_curriculum(EduCurriculumVO eduCurriculumVO) throws Exception;
	public void remove_edu_curriculum_dtl(EduCurriculumVO eduCurriculumVO) throws Exception;
}
