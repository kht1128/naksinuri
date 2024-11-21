package egovframework.cti.analysis.service;

import java.util.List;

public interface CtiAnalysisService {

	int get_call_main_today_call_total() throws Exception;
	int get_call_main_today_call_drsc() throws Exception;
	int get_call_main_today_call_rspons() throws Exception;
	int get_call_main_today_call_cancel() throws Exception;
	List<CtiAnalysisVO> get_report_staff_call_total(CtiAnalysisVO ctiAnalysisVO) throws Exception;
	int get_report_ivr_call_total_by_gubun_1(CtiAnalysisVO ctiAnalysisVO) throws Exception;
	List<CtiAnalysisVO> get_report_ivr_compare_call_total_by_mbr_id(CtiAnalysisVO ctiAnalysisVO) throws Exception;
	List<CtiAnalysisVO> get_report_ivr_compare_call_total_detail_ivr_by_mbr_id(CtiAnalysisVO ctiAnalysisVO) throws Exception;
	List<CtiAnalysisVO> get_report_cvpl_call_total_by_mbr_id(CtiAnalysisVO ctiAnalysisVO) throws Exception;

}
