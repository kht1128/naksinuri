package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.FishjobReviewVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("FishjobReviewDAO")
public class FishjobReviewDAO extends EgovAbstractDAO{

	public List<FishjobReviewVO> get_reviewlist(FishjobReviewVO reviewVO) {
		return (List<FishjobReviewVO>) list ("get_reviewList",reviewVO);
	}

	public FishjobReviewVO insert_review(FishjobReviewVO reviewVO) {
		return (FishjobReviewVO) insert("insert_review",reviewVO);
	}

	public void delete_review(FishjobReviewVO reviewVO) {
		delete("delete_review",reviewVO);
	}

	public FishjobReviewVO revpass_find(FishjobReviewVO reviewVO) {
		return (FishjobReviewVO) select("get_revpass",reviewVO);
	}

	public FishjobReviewVO getrev_info(FishjobReviewVO reviewVO) {
		return (FishjobReviewVO) select("getrev_info",reviewVO);
	}

	public void update_review(FishjobReviewVO reviewVO) {
		update("update_review",reviewVO);
		
	}

	

}
