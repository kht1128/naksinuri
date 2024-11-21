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
package egovframework.cti.analysis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.cti.analysis.service.CtiAnalysisService;
import egovframework.cti.analysis.service.CtiAnalysisVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ctiAnalysisService")
public class CtiAnalysisServiceImpl extends EgovAbstractServiceImpl implements CtiAnalysisService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CtiAnalysisServiceImpl.class);

	@Resource(name = "ctiAnalysisDAO")
	private CtiAnalysisDAO ctiAnalysisDAO;

	@Override
	public int get_call_main_today_call_total() throws Exception {
		return ctiAnalysisDAO.get_call_main_today_call_total();
	}

	@Override
	public int get_call_main_today_call_drsc() throws Exception {
		return ctiAnalysisDAO.get_call_main_today_call_drsc();
	}

	@Override
	public int get_call_main_today_call_rspons() throws Exception {
		return ctiAnalysisDAO.get_call_main_today_call_rspons();
	}

	@Override
	public int get_call_main_today_call_cancel() throws Exception {
		return ctiAnalysisDAO.get_call_main_today_call_cancel();
	}

	@Override
	public List<CtiAnalysisVO> get_report_staff_call_total(CtiAnalysisVO ctiAnalysisVO) throws Exception {
		return (List<CtiAnalysisVO>) ctiAnalysisDAO.get_report_staff_call_total(ctiAnalysisVO);
	}

	@Override
	public int get_report_ivr_call_total_by_gubun_1(CtiAnalysisVO ctiAnalysisVO) throws Exception {
		return (int) ctiAnalysisDAO.get_report_ivr_call_total_by_gubun_1(ctiAnalysisVO);
	}

	@Override
	public List<CtiAnalysisVO> get_report_ivr_compare_call_total_by_mbr_id(CtiAnalysisVO ctiAnalysisVO)
			throws Exception {
		return (List<CtiAnalysisVO>) ctiAnalysisDAO.get_report_ivr_compare_call_total_by_mbr_id(ctiAnalysisVO);
	}

	@Override
	public List<CtiAnalysisVO> get_report_ivr_compare_call_total_detail_ivr_by_mbr_id(CtiAnalysisVO ctiAnalysisVO)
			throws Exception {
		return (List<CtiAnalysisVO>) ctiAnalysisDAO.get_report_ivr_compare_call_total_detail_ivr_by_mbr_id(ctiAnalysisVO);
	}

	@Override
	public List<CtiAnalysisVO> get_report_cvpl_call_total_by_mbr_id(CtiAnalysisVO ctiAnalysisVO) throws Exception {
		return (List<CtiAnalysisVO>) ctiAnalysisDAO.get_report_cvpl_call_total_by_mbr_id(ctiAnalysisVO);
	}
	
}
