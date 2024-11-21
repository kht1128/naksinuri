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
package egovframework.adm.main.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.adm.main.service.AdmMainService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("admMainService")
public class AdmMainServiceImpl extends EgovAbstractServiceImpl implements AdmMainService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdmMainServiceImpl.class);

	@Resource(name = "admMainDAO")
	private AdmMainDAO admMainDAO;

	@Override
	public void adm_master_dummy_excute(EgovMap mEgovMap) throws Exception {
		admMainDAO.adm_master_dummy_excute(mEgovMap);
	}
	

}
