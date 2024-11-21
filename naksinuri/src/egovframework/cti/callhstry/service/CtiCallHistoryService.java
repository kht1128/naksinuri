package egovframework.cti.callhstry.service;

import java.util.List;

public interface CtiCallHistoryService {

	public List<CtiCallHistoryVO> get_cti_callhstry_list(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	public List<CtiCallHistoryVO> get_cti_callhstry_excel_list(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	public int get_cti_callhstry_list_totcnt(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	public String set_cti_callhstry_reg(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	public CtiCallHistoryVO get_cti_callhstry_info(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	public void set_cti_callhstry_info_mod(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	public void remove_cti_callhstry_info(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	public CtiCallHistoryVO get_cti_callhstry_last_info(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	public CtiCallHistoryVO get_cti_callhstry_default_info(CtiCallHistoryVO ctiCallHistoryVO) throws Exception;
	
}
