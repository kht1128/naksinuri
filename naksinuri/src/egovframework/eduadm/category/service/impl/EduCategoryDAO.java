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

import org.springframework.stereotype.Repository;

import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("eduCategoryDAO")
public class EduCategoryDAO extends EgovAbstractDAO {

	//교육카테고리 -----------
	public List<EduCategoryVO> get_edu_category_list_1(EduCategoryVO eduCategoryVO) {
		return (List<EduCategoryVO>) list("get_edu_category_list_1",eduCategoryVO);
	}

	public List<EduCategoryVO> get_edu_category_list_2(EduCategoryVO eduCategoryVO) {
		return (List<EduCategoryVO>) list("get_edu_category_list_2",eduCategoryVO);
	}

	public EduCategoryVO get_edu_category_info(EduCategoryVO eduCategoryVO) {
		return (EduCategoryVO) select("get_edu_category_info",eduCategoryVO);
	}
	
	public EduCategoryVO get_edu_category_dtl_info(EduCategoryVO eduCategoryVO) {
		return (EduCategoryVO) select("get_edu_category_dtl_info",eduCategoryVO);
	}
	
	public String set_edu_category_reg_1(EduCategoryVO eduCategoryVO) {
		return (String)insert("set_edu_category_reg_1",eduCategoryVO);
	}
	
	public String set_edu_category_reg_2(EduCategoryVO eduCategoryVO) {
		return (String)insert("set_edu_category_reg_2",eduCategoryVO);
	}

	public void del_edu_category_1(EduCategoryVO eduCategoryVO) {
		update("del_edu_category_1",eduCategoryVO);
	}

	public void del_edu_category_2(EduCategoryVO eduCategoryVO) {
		update("del_edu_category_2",eduCategoryVO);
	}
	
	public void set_edu_category_mod_1(EduCategoryVO eduCategoryVO) {
		update("set_edu_category_mod_1",eduCategoryVO);
	}
	
	public void set_edu_category_mod_2(EduCategoryVO eduCategoryVO) {
		update("set_edu_category_mod_2",eduCategoryVO);
	}

	public void remove_edu_category_1(EduCategoryVO eduCategoryVO) {
		delete("remove_edu_category_1",eduCategoryVO);		
	}
	
	public void remove_edu_category_2(EduCategoryVO eduCategoryVO) {
		delete("remove_edu_category_2",eduCategoryVO);		
	}
	
	//교육기관 -----------
	public List<EduCategoryInsInfVO> get_edu_category_ins_inf_list(EduCategoryInsInfVO eduCategoryInsInfVO) {
		return (List<EduCategoryInsInfVO>) list("get_edu_category_ins_inf_list",eduCategoryInsInfVO);
	}
	public EduCategoryInsInfVO get_edu_category_ins_inf_info(EduCategoryInsInfVO eduCategoryInsInfVO) {
		return (EduCategoryInsInfVO) select("get_edu_category_ins_inf_info",eduCategoryInsInfVO);
	}
	
	public int get_edu_category_ins_inf_list_totcnt(EduCategoryInsInfVO eduCategoryInsInfVO) {
		return (int)select("get_edu_category_ins_inf_list_totcnt",eduCategoryInsInfVO);
	}

	public void remove_edu_category_ins_inf(EduCategoryInsInfVO eduCategoryInsInfVO) {
		delete("remove_edu_category_ins_inf",eduCategoryInsInfVO);		
	}

	public void del_edu_category_ins_inf(EduCategoryInsInfVO eduCategoryInsInfVO) {
		update("del_edu_category_ins_inf",eduCategoryInsInfVO);
	}

	public void set_edu_category_ins_inf_mod(EduCategoryInsInfVO eduCategoryInsInfVO) {
		update("set_edu_category_ins_inf_mod",eduCategoryInsInfVO);
	}

	public String set_edu_category_ins_inf_reg(EduCategoryInsInfVO eduCategoryInsInfVO) {
		return (String)insert("set_edu_category_ins_inf_reg",eduCategoryInsInfVO);
	}

}
