package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriQnADAO")
public class NaksinuriQnADAO extends EgovAbstractDAO{

	public List<NaksinuriQnAVO> getListQnA(NaksinuriQnAVO qnaVO) {
	    return (List<NaksinuriQnAVO>) list("qna_list", qnaVO); 
		
	}

	public NaksinuriQnAVO qna_find(NaksinuriQnAVO qnaVO) {
		return (NaksinuriQnAVO) select("qna_find",qnaVO);
	}

	public List<NaksinuriQnAVO> getListAnswer(NaksinuriQnAVO qnaVO) {
		return (List<NaksinuriQnAVO>) list("qna_answer_list",qnaVO);
	}

	public void delete_qna(NaksinuriQnAVO qnaVO) {
		 delete("delete_qna",qnaVO);
		
	}

	public void insert_qna(NaksinuriQnAVO qnaVO) {
		insert("insert_qna",qnaVO);
		
	}

	public void update_qna(NaksinuriQnAVO qnaVO) {
		update("update_qna",qnaVO);
		
	}

	public void gotrash_qna(NaksinuriQnAVO qnaVO) {
		update("gotrash_qna",qnaVO);
		
	}

	public List<NaksinuriQnAVO> getTrashQnA(NaksinuriQnAVO qnaVO) {
		return (List<NaksinuriQnAVO>) list("qna_trash",qnaVO);
	}

	public void restore_qnalist(NaksinuriQnAVO qnaVO) {
		update("restore_qna",qnaVO);
		
	}

	
}
