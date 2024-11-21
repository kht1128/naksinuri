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
package egovframework.cti.mbrhstry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.cti.mbrhstry.service.CtiMyHistoryService;
import egovframework.cti.mbrhstry.service.CtiMyHistoryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ctiMyHistoryService")
public class CtiMyHistoryServiceImpl extends EgovAbstractServiceImpl implements CtiMyHistoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CtiMyHistoryServiceImpl.class);

	@Resource(name = "ctiMyHistoryDAO")
	private CtiMyHistoryDAO ctiMyHistoryDAO;

	
	@Override
	public List<CtiMyHistoryVO> get_edu_mbrhstry_list(CtiMyHistoryVO CtiMyHistoryVO) {
		return ctiMyHistoryDAO.get_edu_mbrhstry_list(CtiMyHistoryVO);
	}
	
	@Override
	public CtiMyHistoryVO get_edu_mbrhstry_info(CtiMyHistoryVO CtiMyHistoryVO) {
		return ctiMyHistoryDAO.get_edu_mbrhstry_info(CtiMyHistoryVO);
	}
	
}
