package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAVO;



@Service("NaksinuriQnAService")
public class NaksinuriQnAServiceImpl implements NaksinuriQnAService{

	@Resource(name="NaksinuriQnADAO")
    private NaksinuriQnADAO dao;
	
	@Resource(name="NaksinuriQnAService")
	private NaksinuriQnAService service;

	@Override
	public List<NaksinuriQnAVO> getListQnA(NaksinuriQnAVO qnaVO) throws Exception {
		return dao.getListQnA(qnaVO);
	}

	@Override
	public NaksinuriQnAVO qna_find(NaksinuriQnAVO qnaVO) {
		return dao.qna_find(qnaVO);
	}

	@Override
	public List<NaksinuriQnAVO> getListAnswer(NaksinuriQnAVO qnaVO) throws Exception {
		return dao.getListAnswer(qnaVO);
	}

	@Override
	public void delete_qna(NaksinuriQnAVO qnaVO) throws Exception {
		dao.delete_qna(qnaVO);
		
	}

	@Override
	public void insert_qna(NaksinuriQnAVO qnaVO) throws Exception {
		dao.insert_qna(qnaVO);
		
	}

	@Override
	public void update_qna(NaksinuriQnAVO qnaVO) throws Exception {
		dao.update_qna(qnaVO);
		
	}

	@Override
	public void gotrash_qna(NaksinuriQnAVO qnaVO) throws Exception {
		dao.gotrash_qna(qnaVO);
		
	}

	@Override
	public List<NaksinuriQnAVO> getTrashQnA(NaksinuriQnAVO qnaVO) throws Exception {
		return dao.getTrashQnA(qnaVO);
	}

	@Override
	public void restore_qnalist(NaksinuriQnAVO qnaVO) throws Exception {
		dao.restore_qnalist(qnaVO);
		
	}
}
