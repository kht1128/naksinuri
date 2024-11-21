package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.FishjobReviewService;
import egovframework.naksinuri_original.let.naksinuri.service.FishjobReviewVO;

@Service("FishjobReviewService")
public class FishjobReviewServiceImpl implements FishjobReviewService{
	@Resource(name="FishjobReviewDAO")
	private FishjobReviewDAO dao;

	@Override
	public List<FishjobReviewVO> get_reviewlist(FishjobReviewVO reviewVO) {
		return dao.get_reviewlist(reviewVO);
	}

	@Override
	public FishjobReviewVO insert_review(FishjobReviewVO reviewVO) {
		return dao.insert_review(reviewVO);
	}

	@Override
	public void delete_review(FishjobReviewVO reviewVO) {
		 dao.delete_review(reviewVO);
		
	}

	@Override
	public FishjobReviewVO revpass_find(FishjobReviewVO reviewVO) {
		return dao.revpass_find(reviewVO);
	}

	@Override
	public FishjobReviewVO getrev_info(FishjobReviewVO reviewVO) {
		return dao.getrev_info(reviewVO);
	}

	@Override
	public void update_review(FishjobReviewVO reviewVO) {
		 dao.update_review(reviewVO);
	}

}
