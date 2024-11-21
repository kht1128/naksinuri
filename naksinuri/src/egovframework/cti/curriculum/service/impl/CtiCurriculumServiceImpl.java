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
package egovframework.cti.curriculum.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.cti.curriculum.service.CtiCurriculumService;
import egovframework.cti.curriculum.service.CtiCurriculumVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ctiCurriculumService")
public class CtiCurriculumServiceImpl extends EgovAbstractServiceImpl implements CtiCurriculumService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CtiCurriculumServiceImpl.class);

	@Resource(name = "ctiCurriculumDAO")
	private CtiCurriculumDAO ctiCurriculumDAO;

	@Override
	public List<CtiCurriculumVO> get_edu_curriculum_list(CtiCurriculumVO ctiCurriculumVO) throws Exception {
		return ctiCurriculumDAO.get_edu_curriculum_list(ctiCurriculumVO);
	}
	
	@Override
	public int get_edu_curriculum_list_totcnt(CtiCurriculumVO ctiCurriculumVO) throws Exception {
		return ctiCurriculumDAO.get_edu_curriculum_list_totcnt(ctiCurriculumVO);
	}
	
	@Override
	public CtiCurriculumVO get_edu_curriculum_info(CtiCurriculumVO ctiCurriculumVO) throws Exception {
		return ctiCurriculumDAO.get_edu_curriculum_info(ctiCurriculumVO);
	}
		
}
