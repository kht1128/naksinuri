package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriMainImgVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriUserMainImgService;

@Service("NaksinuriUserMainImgService")
public class NaksinuriUserMainImgServiceImple implements NaksinuriUserMainImgService {
	@Resource(name = "NaksinuriUserMainImgDAO")
    private NaksinuriUserMainImgDAO naksinuriDAO;

	@Override
	public List<NaksinuriMainImgVO> mainimg_list(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainimg_list(mainimgVO);
	}

	@Override
	public NaksinuriMainImgVO board_findCorp(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.board_findCorp(mainimgVO);
	}



	@Override
	public void insert_imgdata(NaksinuriMainImgVO mainimgVO) throws Exception {
	      naksinuriDAO.insert_imgdata(mainimgVO);
		
	}

	@Override
	public NaksinuriMainImgVO mimg(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mimg(mainimgVO);
	}

	@Override
	public void update_data(NaksinuriMainImgVO mainimgVO) throws Exception {
		naksinuriDAO.update_data(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mainimg1(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainimg1(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mainimg2(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainimg2(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mainimg3(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainimg3(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mainimg4(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainimg4(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> headimg(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.headimg(mainimgVO);
	}
	
	@Override
	public List<NaksinuriMainImgVO> mobile_headimg(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mobile_headimg(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mainbanner1(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainbanner1(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mainbanner2(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainbanner2(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mainbanner3(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainbanner3(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> rightbanner(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.rightbanner(mainimgVO);
	}

	@Override
	public void delete_boardlist(NaksinuriMainImgVO mainimgVO) throws Exception {
		naksinuriDAO.delete_boardlist(mainimgVO);
		
	}

	@Override
	public void delete_boardtrash(NaksinuriMainImgVO mainimgVO) throws Exception {
		naksinuriDAO.delete_boardtrash(mainimgVO);
		
	}

	@Override
	public List<NaksinuriMainImgVO> mainimg_trash(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mainimg_trash(mainimgVO);
	}

	@Override
	public void restore_boardlist(NaksinuriMainImgVO mainimgVO) throws Exception {
		naksinuriDAO.restore_boardlist(mainimgVO);
		
	}
		
	@Override
	public List<NaksinuriMainImgVO> attach_img(NaksinuriMainImgVO mainimgVO)  {
		return naksinuriDAO.attach_img(mainimgVO);
	}
	
	@Override
	public NaksinuriMainImgVO mobile_mimg(NaksinuriMainImgVO mainimgVO) throws Exception {
		return naksinuriDAO.mobile_mimg(mainimgVO);
	}
	
	@Override
	public List<NaksinuriMainImgVO> main_scrollimg(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.main_scrollimg(mainimgVO);
	}
	
	@Override
	public List<NaksinuriMainImgVO> main_popup(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.main_popup(mainimgVO);
	}
	

	@Override
	public List<NaksinuriMainImgVO> mobile_mainimg1(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mobile_mainimg1(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mobile_mainimg2(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mobile_mainimg2(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mobile_mainimg3(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mobile_mainimg3(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mobile_mainimg4(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mobile_mainimg4(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mobile_mainbanner1(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mobile_mainbanner1(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mobile_mainbanner2(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mobile_mainbanner2(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mobile_mainbanner3(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mobile_mainbanner3(mainimgVO);
	}

	@Override
	public List<NaksinuriMainImgVO> mobile_rightbanner(NaksinuriMainImgVO mainimgVO) {
		return naksinuriDAO.mobile_rightbanner(mainimgVO);
	}

	

	
	
}
