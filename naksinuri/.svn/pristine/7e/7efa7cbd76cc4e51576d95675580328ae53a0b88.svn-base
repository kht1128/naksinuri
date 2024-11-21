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
package egovframework.eduadm.category.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eduCategoryService")
public class EduCategoryServiceImpl extends EgovAbstractServiceImpl implements EduCategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduCategoryServiceImpl.class);

	@Resource(name = "eduCategoryDAO")
	private EduCategoryDAO eduCategoryDAO;

	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	
	//교육카테고리 -----------
	@Override
	public List<EduCategoryVO> get_edu_category_list_1(EduCategoryVO eduCategoryVO) throws Exception {
		return eduCategoryDAO.get_edu_category_list_1(eduCategoryVO);
	}
	
	@Override
	public List<EduCategoryVO> get_edu_category_list_2(EduCategoryVO eduCategoryVO) throws Exception {
		return eduCategoryDAO.get_edu_category_list_2(eduCategoryVO);
	}

	@Override
	public EduCategoryVO get_edu_category_info(EduCategoryVO eduCategoryVO) throws Exception {
		return eduCategoryDAO.get_edu_category_info(eduCategoryVO);
	}
	
	@Override
	public EduCategoryVO get_edu_category_dtl_info(EduCategoryVO eduCategoryVO) throws Exception {
		return eduCategoryDAO.get_edu_category_dtl_info(eduCategoryVO);
	}

	@Override
	public String set_edu_category_reg_1(EduCategoryVO eduCategoryVO) throws Exception {
		return eduCategoryDAO.set_edu_category_reg_1(eduCategoryVO);
	}
	
	@Override
	public String set_edu_category_reg_2(EduCategoryVO eduCategoryVO) throws Exception {
		return eduCategoryDAO.set_edu_category_reg_2(eduCategoryVO);
	}

	@Override
	public void del_edu_category_1(EduCategoryVO eduCategoryVO) throws Exception {
		eduCategoryDAO.del_edu_category_1(eduCategoryVO);
	}
	@Override
	public void del_edu_category_2(EduCategoryVO eduCategoryVO) throws Exception {
		eduCategoryDAO.del_edu_category_2(eduCategoryVO);
	}

	@Override
	public void set_edu_category_mod_1(EduCategoryVO eduCategoryVO) throws Exception {
		eduCategoryDAO.set_edu_category_mod_1(eduCategoryVO);
	}
	
	@Override
	public void set_edu_category_mod_2(EduCategoryVO eduCategoryVO) throws Exception {
		eduCategoryDAO.set_edu_category_mod_2(eduCategoryVO);
	}

	@Override
	public void remove_edu_category_1(EduCategoryVO eduCategoryVO) throws Exception {
		eduCategoryDAO.remove_edu_category_1(eduCategoryVO);
	}

	@Override
	public void remove_edu_category_2(EduCategoryVO eduCategoryVO) throws Exception {
		eduCategoryDAO.remove_edu_category_2(eduCategoryVO);
	}
	
	
	//교육기관 -----------
	@Override
	public List<EduCategoryInsInfVO> get_edu_category_ins_inf_list(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception {
		return eduCategoryDAO.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
	}
	@Override
	public EduCategoryInsInfVO get_edu_category_ins_inf_info(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception {
		return eduCategoryDAO.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
	}

	@Override
	public int get_edu_category_ins_inf_list_totcnt(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception {
		return eduCategoryDAO.get_edu_category_ins_inf_list_totcnt(eduCategoryInsInfVO);
	}

	@Override
	public void remove_edu_category_ins_inf(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception {
		eduCategoryDAO.remove_edu_category_ins_inf(eduCategoryInsInfVO);
	}

	@Override
	public void del_edu_category_ins_inf(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception {
		eduCategoryDAO.del_edu_category_ins_inf(eduCategoryInsInfVO);
	}

	@Override
	public void set_edu_category_ins_inf_mod(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception {
		eduCategoryDAO.set_edu_category_ins_inf_mod(eduCategoryInsInfVO);
	}

	@Override
	public String set_edu_category_ins_inf_reg(EduCategoryInsInfVO eduCategoryInsInfVO) throws Exception {
		return eduCategoryDAO.set_edu_category_ins_inf_reg(eduCategoryInsInfVO);
	}

}
