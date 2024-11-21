package egovframework.eduadm.trainingdata.service;

import java.util.List;

import egovframework.eduadm.category.service.EduCategoryVO;

public interface EduTrainingDataService {
	//교육자료 ----------- 
	public String set_edu_data_reg(EduTrainingDataVO eduTrainingDataVO) throws Exception;
	public List<EduTrainingDataVO> get_edu_data_list(EduTrainingDataVO eduTrainingDataVO) throws Exception;
	public List<EduTrainingDataVO> get_edu_data_list(EduCategoryVO eduCategoryVO) throws Exception;
	public EduTrainingDataVO get_edu_data_info(EduTrainingDataVO eduTrainingDataVO) throws Exception;
	public void set_edu_data_mod(EduTrainingDataVO eduTrainingDataVO) throws Exception;
	public void del_edu_data(EduTrainingDataVO eduTrainingDataVO) throws Exception;
	public int get_edu_data_list_totcnt(EduTrainingDataVO eduTrainingDataVO) throws Exception;
	public void remove_edu_data(EduTrainingDataVO eduTrainingDataVO) throws Exception;
}
