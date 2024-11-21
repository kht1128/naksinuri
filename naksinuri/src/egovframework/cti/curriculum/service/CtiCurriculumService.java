package egovframework.cti.curriculum.service;

import java.util.List;

public interface CtiCurriculumService {
	public List<CtiCurriculumVO> get_edu_curriculum_list(CtiCurriculumVO ctiCurriculumVO) throws Exception;
	public int get_edu_curriculum_list_totcnt(CtiCurriculumVO ctiCurriculumVO) throws Exception;
	public CtiCurriculumVO get_edu_curriculum_info(CtiCurriculumVO ctiCurriculumVO) throws Exception;
}
