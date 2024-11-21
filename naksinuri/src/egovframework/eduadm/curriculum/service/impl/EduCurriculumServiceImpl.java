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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eduCurriculumService")
public class EduCurriculumServiceImpl extends EgovAbstractServiceImpl implements EduCurriculumService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduCurriculumServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "eduCurriculumDAO")
	private EduCurriculumDAO eduCurriculumDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	//교육과정(커리큘럼) -----------
	@Override
	public List<EduCurriculumVO> get_edu_curriculum_list(EduCurriculumVO eduCurriculumVO) throws Exception {
		return eduCurriculumDAO.get_edu_curriculum_list(eduCurriculumVO);
	}

	@Override
	public int get_edu_curriculum_list_totcnt(EduCurriculumVO eduCurriculumVO) throws Exception {
		return eduCurriculumDAO.get_edu_curriculum_list_totcnt(eduCurriculumVO);
	}
	
	@Override
	public List<EduCurriculumVO> get_edu_curriculum_dtl_list(EduCurriculumVO eduCurriculumVO) throws Exception {
		return eduCurriculumDAO.get_edu_curriculum_dtl_list(eduCurriculumVO);
	}

	@Override
	public int get_edu_curriculum_dtl_list_totcnt(EduCurriculumVO eduCurriculumVO) throws Exception {
		return eduCurriculumDAO.get_edu_curriculum_dtl_list_totcnt(eduCurriculumVO);
	}

	@Override
	public EduCurriculumVO get_edu_curriculum_info(EduCurriculumVO eduCurriculumVO) throws Exception {
		return eduCurriculumDAO.get_edu_curriculum_info(eduCurriculumVO);
	}
	
	@Override
	public EduCurriculumVO get_edu_curriculum_dtl_info(EduCurriculumVO eduCurriculumVO) throws Exception {
		return eduCurriculumDAO.get_edu_curriculum_dtl_info(eduCurriculumVO);
	}

	@Override
	public String set_edu_curriculum_reg(EduCurriculumVO eduCurriculumVO) throws Exception {
		return eduCurriculumDAO.set_edu_curriculum_reg(eduCurriculumVO);
	}
	
	@Override
	public String set_edu_curriculum_reg_dtl(EduCurriculumVO eduCurriculumVO) throws Exception {
		return eduCurriculumDAO.set_edu_curriculum_reg_dtl(eduCurriculumVO);
	}

	@Override
	public void set_edu_curriculum_mod(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.set_edu_curriculum_mod(eduCurriculumVO);
	}
	
	@Override
	public void set_edu_curriculum_mod_dtl(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.set_edu_curriculum_mod_dtl(eduCurriculumVO);
	}

	@Override
	public void del_edu_curriculum(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.del_edu_curriculum(eduCurriculumVO);
	}
	
	@Override
	public void del_edu_curriculum_dtl(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.del_edu_curriculum_dtl(eduCurriculumVO);
	}

	@Override
	public void set_edu_curriculum_mbr_cur_cnt_up(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.set_edu_curriculum_mbr_cur_cnt_up(eduCurriculumVO);
	}
	
	@Override
	public void set_edu_curriculum_mbr_cur_cnt_down(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.set_edu_curriculum_mbr_cur_cnt_down(eduCurriculumVO);
	}
	
	@Override
	public void set_edu_curriculum_mbr_cmplt_cnt_up(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.set_edu_curriculum_mbr_cmplt_cnt_up(eduCurriculumVO);
	}
	
	@Override
	public void set_edu_curriculum_mbr_cmplt_cnt_down(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.set_edu_curriculum_mbr_cmplt_cnt_down(eduCurriculumVO);
	}

	@Override
	public void remove_edu_curriculum(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.remove_edu_curriculum(eduCurriculumVO);
	}

	@Override
	public void remove_edu_curriculum_dtl(EduCurriculumVO eduCurriculumVO) throws Exception {
		eduCurriculumDAO.remove_edu_curriculum_dtl(eduCurriculumVO);
	}
	
}
