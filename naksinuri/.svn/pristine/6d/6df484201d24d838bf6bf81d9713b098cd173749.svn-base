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

import org.springframework.stereotype.Repository;

import egovframework.cti.mbrhstry.service.CtiMyHistoryVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ctiMyHistoryDAO")
public class CtiMyHistoryDAO extends EgovAbstractDAO {

	public CtiMyHistoryVO get_edu_mbrhstry_info(CtiMyHistoryVO CtiMyHistoryVO) {
		return (CtiMyHistoryVO) select("ctiMyHistoryDAO.get_edu_mbrhstry_info",CtiMyHistoryVO);
	}
	
	public List<CtiMyHistoryVO> get_edu_mbrhstry_list(CtiMyHistoryVO CtiMyHistoryVO) {
		return (List<CtiMyHistoryVO>) list("ctiMyHistoryDAO.get_edu_mbrhstry_list",CtiMyHistoryVO);
	}
	
}
