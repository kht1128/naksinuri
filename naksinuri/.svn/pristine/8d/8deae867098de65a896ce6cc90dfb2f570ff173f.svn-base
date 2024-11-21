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
package egovframework.eduadm.trainingdata.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("eduTrainingDataDAO")
public class EduTrainingDataDAO extends EgovAbstractDAO {

	//교육자료 -----------
	public String set_edu_data_reg(EduTrainingDataVO eduTrainingDataVO) {
		return (String)insert("set_edu_data_reg",eduTrainingDataVO);
	}

	public List<EduTrainingDataVO> get_edu_data_list(EduTrainingDataVO eduTrainingDataVO) {
		return (List<EduTrainingDataVO>) list("get_edu_data_list",eduTrainingDataVO);
	}
	
	public List<EduTrainingDataVO> get_edu_data_list(EduCategoryVO eduCategoryVO) {
		return (List<EduTrainingDataVO>) list("get_edu_data_list_from_category",eduCategoryVO);
	}

	public EduTrainingDataVO get_edu_data_info(EduTrainingDataVO eduTrainingDataVO) {
		return (EduTrainingDataVO) select("get_edu_data_info",eduTrainingDataVO);
	}

	public void set_edu_data_mod(EduTrainingDataVO eduTrainingDataVO) {
		update("set_edu_data_mod",eduTrainingDataVO);
	}

	public void del_edu_data(EduTrainingDataVO eduTrainingDataVO) {
		update("del_edu_data",eduTrainingDataVO);
	}

	public int get_edu_data_list_totcnt(EduTrainingDataVO eduTrainingDataVO) {
		return (int) select("get_edu_data_list_totcnt",eduTrainingDataVO);
	}

	public void remove_edu_data(EduTrainingDataVO eduTrainingDataVO) {
		delete("remove_edu_data",eduTrainingDataVO);
	}


}
