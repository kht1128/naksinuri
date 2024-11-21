package egovframework.eduadm.category.service;

import java.util.List;

public interface EduCategoryService {
	//교육카테고리 ----------- 
	public List<EduCategoryVO> get_edu_category_list_1(EduCategoryVO eduCategoryVO) throws Exception;
	public List<EduCategoryVO> get_edu_category_list_2(EduCategoryVO eduCategoryVO) throws Exception;
	public EduCategoryVO get_edu_category_info(EduCategoryVO eduCategoryVO) throws Exception;
	public EduCategoryVO get_edu_category_dtl_info(EduCategoryVO eduCategoryVO) throws Exception;
	public String set_edu_category_reg_1(EduCategoryVO eduCategoryVO) throws Exception;
	public String set_edu_category_reg_2(EduCategoryVO eduCategoryVO) throws Exception;
	public void del_edu_category_1(EduCategoryVO eduCategoryVO) throws Exception;
	public void del_edu_category_2(EduCategoryVO eduCategoryVO) throws Exception;
	public void set_edu_category_mod_1(EduCategoryVO eduCategoryVO) throws Exception;
	public void set_edu_category_mod_2(EduCategoryVO eduCategoryVO) throws Exception;
	public void remove_edu_category_1(EduCategoryVO eduCategoryVO) throws Exception;
	public void remove_edu_category_2(EduCategoryVO eduCategoryVO) throws Exception;
	//교육기관 카테고리 ----------- 
	public List<EduCategoryInsInfVO> get_edu_category_ins_inf_list(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception;
	public EduCategoryInsInfVO get_edu_category_ins_inf_info(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception;
	public int get_edu_category_ins_inf_list_totcnt(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception;
	public void remove_edu_category_ins_inf(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception;
	public void del_edu_category_ins_inf(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception;
	public void set_edu_category_ins_inf_mod(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception;
	public String set_edu_category_ins_inf_reg(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception;
}
