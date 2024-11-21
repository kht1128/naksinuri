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
package egovframework.cti.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.cti.main.service.CtiBoardVO;
import egovframework.cti.main.service.CtiMainService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ctiMainService")
public class CtiMainServiceImpl extends EgovAbstractServiceImpl implements CtiMainService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CtiMainServiceImpl.class);

	@Resource(name = "ctiMainDAO")
	private CtiMainDAO ctiMainDAO;

	@Override
	public List<NaksinuriZisikinVO> get_cti_zazu_list(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return ctiMainDAO.get_cti_zazu_list(naksinuriZisikinVO);
	}

	@Override
	public int get_cti_zazu_list_totcnt(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return ctiMainDAO.get_cti_zazu_list_totcnt(naksinuriZisikinVO);
	}

	@Override
	public List<CtiBoardVO> get_cti_manual_list(CtiBoardVO ctiBoardVO) throws Exception {
		return ctiMainDAO.get_cti_manual_list(ctiBoardVO);
	}

	@Override
	public void set_cti_manual_insert(CtiBoardVO insertCtiBoardVO) throws Exception {
		ctiMainDAO.set_cti_manual_insert(insertCtiBoardVO);
		
	}
	
	@Override
	public void set_cti_menual_mod(CtiBoardVO updateCtiBoardVO) throws Exception {
		ctiMainDAO.set_cti_menual_mod(updateCtiBoardVO);
		
	}

	@Override
	public void set_cti_menual_delete(CtiBoardVO deleteCtiBoardVO) throws Exception {
		ctiMainDAO.set_cti_menual_delete(deleteCtiBoardVO);
		
	}

	
		
}
