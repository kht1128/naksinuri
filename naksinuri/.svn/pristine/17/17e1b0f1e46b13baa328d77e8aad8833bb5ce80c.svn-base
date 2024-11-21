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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eduTrainingDataService")
public class EduTrainingDataServiceImpl extends EgovAbstractServiceImpl implements EduTrainingDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduTrainingDataServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "eduTrainingDataDAO")
	private EduTrainingDataDAO eduTrainingDataDAO;
	// TODO mybatis 사용
	//  @Resource(name="sampleMapper")
	//	private SampleMapper sampleDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	//교육자료 -----------
	@Override
	public String set_edu_data_reg(EduTrainingDataVO eduTrainingDataVO) throws Exception {
		return eduTrainingDataDAO.set_edu_data_reg(eduTrainingDataVO);
	}
	
	@Override
	public void set_edu_data_mod(EduTrainingDataVO eduTrainingDataVO) throws Exception {
		eduTrainingDataDAO.set_edu_data_mod(eduTrainingDataVO);
	}

	@Override
	public List<EduTrainingDataVO> get_edu_data_list(EduTrainingDataVO eduTrainingDataVO) throws Exception {
		return eduTrainingDataDAO.get_edu_data_list(eduTrainingDataVO);
	}
	
	@Override
	public List<EduTrainingDataVO> get_edu_data_list(EduCategoryVO eduCategoryVO) throws Exception {
		return eduTrainingDataDAO.get_edu_data_list(eduCategoryVO);
	}

	@Override
	public EduTrainingDataVO get_edu_data_info(EduTrainingDataVO eduTrainingDataVO) throws Exception {
		return eduTrainingDataDAO.get_edu_data_info(eduTrainingDataVO);
	}

	@Override
	public void del_edu_data(EduTrainingDataVO eduTrainingDataVO) throws Exception {
		eduTrainingDataDAO.del_edu_data(eduTrainingDataVO);
	}

	@Override
	public int get_edu_data_list_totcnt(EduTrainingDataVO eduTrainingDataVO) throws Exception {
		return eduTrainingDataDAO.get_edu_data_list_totcnt(eduTrainingDataVO);
	}

	@Override
	public void remove_edu_data(EduTrainingDataVO eduTrainingDataVO) throws Exception {
		eduTrainingDataDAO.remove_edu_data(eduTrainingDataVO);
	}

}
