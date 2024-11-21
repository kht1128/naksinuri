package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;

public interface FishesService {

	/**
	 *  낚시 종류 당 어종 조회
	 */
	public List<FishesVO> fish_list(FishesVO fishesVO) throws  Exception;
	public List<FishesVO> fishPreview_list(FishesVO fishesVO) throws  Exception;


}
