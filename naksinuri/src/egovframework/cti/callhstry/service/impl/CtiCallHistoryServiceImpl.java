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
package egovframework.cti.callhstry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.cti.callhstry.service.CtiCallHistoryService;
import egovframework.cti.callhstry.service.CtiCallHistoryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ctiCallHistoryService")
public class CtiCallHistoryServiceImpl extends EgovAbstractServiceImpl implements CtiCallHistoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CtiCallHistoryServiceImpl.class);

	@Resource(name = "ctiCallHistoryDAO")
	private CtiCallHistoryDAO ctiCallHistoryDAO;

	//@Resource(name = "egovIdGnrService")
	//private EgovIdGnrService egovIdGnrService;

	@Override
	public List<CtiCallHistoryVO> get_cti_callhstry_list(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		return ctiCallHistoryDAO.get_cti_callhstry_list(ctiCallHistoryVO);
	}
	
	@Override
	public List<CtiCallHistoryVO> get_cti_callhstry_excel_list(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		return ctiCallHistoryDAO.get_cti_callhstry_excel_list(ctiCallHistoryVO);
	}

	@Override
	public int get_cti_callhstry_list_totcnt(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		return ctiCallHistoryDAO.get_cti_callhstry_list_totcnt(ctiCallHistoryVO);
	}

	@Override
	public String set_cti_callhstry_reg(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		return ctiCallHistoryDAO.set_cti_callhstry_reg(ctiCallHistoryVO);
	}

	@Override
	public CtiCallHistoryVO get_cti_callhstry_info(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		return ctiCallHistoryDAO.get_cti_callhstry_info(ctiCallHistoryVO);
	}

	@Override
	public void set_cti_callhstry_info_mod(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		ctiCallHistoryDAO.set_cti_callhstry_info_mod(ctiCallHistoryVO);
	}

	@Override
	public void remove_cti_callhstry_info(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		ctiCallHistoryDAO.remove_cti_callhstry_info(ctiCallHistoryVO);
	}

	@Override
	public CtiCallHistoryVO get_cti_callhstry_last_info(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		return ctiCallHistoryDAO.get_cti_callhstry_last_info(ctiCallHistoryVO);
	}
	
	@Override
	public CtiCallHistoryVO get_cti_callhstry_default_info(CtiCallHistoryVO ctiCallHistoryVO) throws Exception {
		return ctiCallHistoryDAO.get_cti_callhstry_default_info(ctiCallHistoryVO);
	}	
	
}
