package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.FishesVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("FishesDAO")
public class FishesDAO extends EgovAbstractDAO{

	public List<FishesVO> fish_list(FishesVO fishesVO) throws Exception{
		return (List<FishesVO>) list ("Fish_list", fishesVO); 
	}
	public List<FishesVO> fishPreview_list(FishesVO fishesVO) {
		return (List<FishesVO>) list ("FishPreview_list", fishesVO); 
	}
}
