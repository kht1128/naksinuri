package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.FishesService;
import egovframework.naksinuri_original.let.naksinuri.service.FishesVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;

@Service("FishesService")
public class FishesServiceImpl implements FishesService{
	@Resource(name = "FishesDAO")
	private FishesDAO fishesDAO;
	
	@Resource(name = "FishesService")
	private FishesService fishesSevice; 

	@Override
	public List<FishesVO> fish_list(FishesVO fishesVO) throws Exception {
		return fishesDAO.fish_list(fishesVO);
	}
	public List<FishesVO> fishPreview_list(FishesVO fishesVO) throws Exception {
		return fishesDAO.fishPreview_list(fishesVO);
	}

}
