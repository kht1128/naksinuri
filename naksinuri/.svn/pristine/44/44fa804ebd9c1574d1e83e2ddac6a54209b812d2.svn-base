package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.ReportVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("FishjobReportDAO")
public class FishjobReportDAO extends EgovAbstractDAO{

	public void insert_report(ReportVO reportVO) {
		insert("insert_report",reportVO);
		
	}

	public List<ReportVO> report_list(ReportVO reportVO) {
		return (List<ReportVO>) list("report_list",reportVO);
		
	}

	public ReportVO report_findCorp(ReportVO reportVO) {
		return (ReportVO) select("report_findCorp",reportVO);
	}

	public List<ReportVO> ba_file(ReportVO reportVO) {
		return (List<ReportVO>) list("report_file",reportVO);
	}

	public void trash_list(ReportVO reportVO) {
		update("gotrash_report",reportVO);
		
	}

	public void restore_report(ReportVO reportVO) {	
		update("restore_report",reportVO);
	}

	public void delete_reportlist(ReportVO reportVO) {
		delete("delete_reportlist",reportVO);
		
	}

	public List<ReportVO> report_trash(ReportVO reportVO) {
		return (List<ReportVO>) list ("report_trash",reportVO);
	}

	
}
