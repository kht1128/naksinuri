package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.FishjobReportService;
import egovframework.naksinuri_original.let.naksinuri.service.ReportVO;

@Service("FishjobReportService")
public class FishjobReportServiceImpl implements FishjobReportService{
	@Resource(name="FishjobReportDAO")
	private FishjobReportDAO dao;

	@Override
	public void insert_report(ReportVO reportVO) {
		dao.insert_report(reportVO);
	}

	@Override
	public List<ReportVO> report_list(ReportVO reportVO) throws Exception {
		return dao.report_list(reportVO);
	}

	@Override
	public ReportVO report_findCorp(ReportVO reportVO) throws Exception {
		return dao.report_findCorp(reportVO);
	}

	@Override
	public List<ReportVO> ba_file(ReportVO reportVO) throws Exception {
		return dao.ba_file(reportVO);
	}

	@Override
	public void trash_list(ReportVO reportVO) throws Exception {
		dao.trash_list(reportVO);
		
	}

	@Override
	public void restore_report(ReportVO reportVO) throws Exception {
		dao.restore_report(reportVO);
	}

	@Override
	public void delete_reportlist(ReportVO reportVO) throws Exception {
		dao.delete_reportlist(reportVO);
		
	}

	@Override
	public List<ReportVO> report_trash(ReportVO reportVO) throws Exception {
		return dao.report_trash(reportVO);
	}
}
