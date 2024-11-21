package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;

public interface FishjobReviewService {

	List<FishjobReviewVO> get_reviewlist(FishjobReviewVO reviewVO);

	FishjobReviewVO insert_review(FishjobReviewVO reviewVO);

	void delete_review(FishjobReviewVO reviewVO);

	FishjobReviewVO revpass_find(FishjobReviewVO reviewVO);

	FishjobReviewVO getrev_info(FishjobReviewVO reviewVO);

	void update_review(FishjobReviewVO reviewVO);

}
