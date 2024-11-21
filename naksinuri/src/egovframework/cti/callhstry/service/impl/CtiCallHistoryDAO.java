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

import org.springframework.stereotype.Repository;

import egovframework.cti.callhstry.service.CtiCallHistoryVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ctiCallHistoryDAO")
public class CtiCallHistoryDAO extends EgovAbstractDAO {

	public List<CtiCallHistoryVO> get_cti_callhstry_list(CtiCallHistoryVO ctiCallHistoryVO) {
		return (List<CtiCallHistoryVO>)list("ctiCallHistorySQL.get_cti_callhstry_list",ctiCallHistoryVO);
	}
	
	public List<CtiCallHistoryVO> get_cti_callhstry_excel_list(CtiCallHistoryVO ctiCallHistoryVO) {
		return (List<CtiCallHistoryVO>)list("ctiCallHistorySQL.get_cti_callhstry_excel_list",ctiCallHistoryVO);
	}

	public int get_cti_callhstry_list_totcnt(CtiCallHistoryVO ctiCallHistoryVO) {
		return (int)select("ctiCallHistorySQL.get_cti_callhstry_list_totcnt",ctiCallHistoryVO);
	}

	public String set_cti_callhstry_reg(CtiCallHistoryVO ctiCallHistoryVO) {
		return (String) insert("ctiCallHistorySQL.set_cti_callhstry_reg",ctiCallHistoryVO);
	}

	public CtiCallHistoryVO get_cti_callhstry_info(CtiCallHistoryVO ctiCallHistoryVO) {
		return (CtiCallHistoryVO)select("ctiCallHistorySQL.get_cti_callhstry_info",ctiCallHistoryVO);
	}

	public void set_cti_callhstry_info_mod(CtiCallHistoryVO ctiCallHistoryVO) {
		update("ctiCallHistorySQL.set_cti_callhstry_info_mod",ctiCallHistoryVO);
	}

	public void remove_cti_callhstry_info(CtiCallHistoryVO ctiCallHistoryVO) {
		delete("ctiCallHistorySQL.remove_cti_callhstry_info",ctiCallHistoryVO);
	}

	public CtiCallHistoryVO get_cti_callhstry_last_info(CtiCallHistoryVO ctiCallHistoryVO) {
		return (CtiCallHistoryVO)select("ctiCallHistorySQL.get_cti_callhstry_last_info",ctiCallHistoryVO);
	}

	public CtiCallHistoryVO get_cti_callhstry_default_info(CtiCallHistoryVO ctiCallHistoryVO) {
		return (CtiCallHistoryVO)select("ctiCallHistorySQL.get_cti_callhstry_default_info",ctiCallHistoryVO);
	}


}
