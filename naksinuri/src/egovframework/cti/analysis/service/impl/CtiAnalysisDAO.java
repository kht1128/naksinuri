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

import org.springframework.stereotype.Repository;

import egovframework.cti.analysis.service.CtiAnalysisVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ctiAnalysisDAO")
public class CtiAnalysisDAO extends EgovAbstractDAO {

	public int get_call_main_today_call_total() {
		return (int) select("ctiAnalysisDAO.get_call_main_today_call_total");
	}

	public int get_call_main_today_call_drsc() {
		return (int) select("ctiAnalysisDAO.get_call_main_today_call_drsc");
	}

	public int get_call_main_today_call_rspons() {
		return (int) select("ctiAnalysisDAO.get_call_main_today_call_rspons");
	}

	public int get_call_main_today_call_cancel() {
		return (int) select("ctiAnalysisDAO.get_call_main_today_call_cancel");
	}

	public List<CtiAnalysisVO> get_report_staff_call_total(CtiAnalysisVO ctiAnalysisVO) {
		return (List<CtiAnalysisVO>) list("ctiAnalysisDAO.get_report_staff_call_total",ctiAnalysisVO);
	}

	public int get_report_ivr_call_total_by_gubun_1(CtiAnalysisVO ctiAnalysisVO) {
		return (int) select("ctiAnalysisDAO.get_report_ivr_call_total_by_gubun_1",ctiAnalysisVO);
	}

	public List<CtiAnalysisVO> get_report_ivr_compare_call_total_by_mbr_id(CtiAnalysisVO ctiAnalysisVO) {
		return (List<CtiAnalysisVO>) list("ctiAnalysisDAO.get_report_ivr_compare_call_total_by_mbr_id",ctiAnalysisVO);
	}

	public List<CtiAnalysisVO> get_report_ivr_compare_call_total_detail_ivr_by_mbr_id(CtiAnalysisVO ctiAnalysisVO) {
		return (List<CtiAnalysisVO>) list("ctiAnalysisDAO.get_report_ivr_compare_call_total_detail_ivr_by_mbr_id",ctiAnalysisVO);
	}

	public List<CtiAnalysisVO> get_report_cvpl_call_total_by_mbr_id(CtiAnalysisVO ctiAnalysisVO) {
		return (List<CtiAnalysisVO>) list("ctiAnalysisDAO.get_report_cvpl_call_total_by_mbr_id",ctiAnalysisVO);
	}
}
