package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriLogsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriLogsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriVO;



@Service("NaksinuriLogsService")
public class NaksinuriLogsServiceImpl implements NaksinuriLogsService{
	@Resource(name = "NaksinuriDAO")
    private NaksinuriDAO naksinuriDAO;
	
	@Resource(name="NaksinuriLogsService")
	private NaksinuriLogsService service;
	
	

	@Override
	public List<NaksinuriLogsVO> getListLogs(NaksinuriLogsVO LogsVO) throws Exception {
		return naksinuriDAO.getListLogs(LogsVO);
	}
	
	@Override
	public void deleteLogs(NaksinuriLogsVO LogsVO) throws Exception {
		naksinuriDAO.deleteLogs(LogsVO);
	}

	@Override
	public void insertLogs(NaksinuriLogsVO LogsVO) throws Exception {
		naksinuriDAO.insertLogs(LogsVO);
		
	}
}
