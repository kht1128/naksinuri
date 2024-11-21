package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;

public interface NaksinuriQnAService {

	public List<NaksinuriQnAVO> getListQnA(NaksinuriQnAVO qnaVO) throws Exception;

	public NaksinuriQnAVO qna_find(NaksinuriQnAVO qnaVO) throws Exception;

	public List<NaksinuriQnAVO> getListAnswer(NaksinuriQnAVO qnaVO) throws Exception;

	public void delete_qna(NaksinuriQnAVO qnaVO) throws Exception;

	public void insert_qna(NaksinuriQnAVO qnaVO) throws Exception;

	public void update_qna(NaksinuriQnAVO qnaVO) throws Exception;

	public void gotrash_qna(NaksinuriQnAVO qnaVO) throws Exception;

	public List<NaksinuriQnAVO> getTrashQnA(NaksinuriQnAVO qnaVO) throws Exception;

	public void restore_qnalist(NaksinuriQnAVO qnaVO) throws Exception;
}
