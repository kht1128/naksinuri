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
package egovframework.eduadm.curriculum.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("eduCurriculumDAO")
public class EduCurriculumDAO extends EgovAbstractDAO {

	//교육과정(커리큘럼) -----------
	public List<EduCurriculumVO> get_edu_curriculum_list(EduCurriculumVO eduCurriculumVO) {
		return (List<EduCurriculumVO>) list("get_edu_curriculum_list",eduCurriculumVO);
	}

	public int get_edu_curriculum_list_totcnt(EduCurriculumVO eduCurriculumVO) {
		return (int) select("get_edu_curriculum_list_totcnt",eduCurriculumVO);
	}
	
	public List<EduCurriculumVO> get_edu_curriculum_dtl_list(EduCurriculumVO eduCurriculumVO) {
		return (List<EduCurriculumVO>) list("get_edu_curriculum_dtl_list",eduCurriculumVO);
	}

	public int get_edu_curriculum_dtl_list_totcnt(EduCurriculumVO eduCurriculumVO) {
		return (int) select("get_edu_curriculum_dtl_list_totcnt",eduCurriculumVO);
	}

	public EduCurriculumVO get_edu_curriculum_info(EduCurriculumVO eduCurriculumVO) {
		return (EduCurriculumVO) select("get_edu_curriculum_info",eduCurriculumVO);
	}
	
	public EduCurriculumVO get_edu_curriculum_dtl_info(EduCurriculumVO eduCurriculumVO) {
		return (EduCurriculumVO) select("get_edu_curriculum_dtl_info",eduCurriculumVO);
	}

	public String set_edu_curriculum_reg(EduCurriculumVO eduCurriculumVO) {
		return (String)insert("set_edu_curriculum_reg",eduCurriculumVO);
	}
	
	public String set_edu_curriculum_reg_dtl(EduCurriculumVO eduCurriculumVO) {
		return (String)insert("set_edu_curriculum_reg_dtl",eduCurriculumVO);
	}

	public void set_edu_curriculum_mod(EduCurriculumVO eduCurriculumVO) {
		update("set_edu_curriculum_mod",eduCurriculumVO);
	}
	
	public void set_edu_curriculum_mod_dtl(EduCurriculumVO eduCurriculumVO) {
		update("set_edu_curriculum_mod_dtl",eduCurriculumVO);
	}

	public void del_edu_curriculum(EduCurriculumVO eduCurriculumVO) {
		update("del_edu_curriculum",eduCurriculumVO);
	}
	
	public void del_edu_curriculum_dtl(EduCurriculumVO eduCurriculumVO) {
		update("del_edu_curriculum_dtl",eduCurriculumVO);
	}

	public void set_edu_curriculum_mbr_cur_cnt_up(EduCurriculumVO eduCurriculumVO) {
		update("set_edu_curriculum_mbr_cur_cnt_up",eduCurriculumVO);
	}

	public void set_edu_curriculum_mbr_cur_cnt_down(EduCurriculumVO eduCurriculumVO) {
		update("set_edu_curriculum_mbr_cur_cnt_down",eduCurriculumVO);		
	}
	
	public void set_edu_curriculum_mbr_cmplt_cnt_up(EduCurriculumVO eduCurriculumVO) {
		update("set_edu_curriculum_mbr_cmplt_cnt_up",eduCurriculumVO);
	}

	public void set_edu_curriculum_mbr_cmplt_cnt_down(EduCurriculumVO eduCurriculumVO) {
		update("set_edu_curriculum_mbr_cmplt_cnt_down",eduCurriculumVO);		
	}

	public void remove_edu_curriculum(EduCurriculumVO eduCurriculumVO) {
		delete("remove_edu_curriculum",eduCurriculumVO);
	}

	public void remove_edu_curriculum_dtl(EduCurriculumVO eduCurriculumVO) {
		delete("remove_edu_curriculum_dtl",eduCurriculumVO);
	}

}
