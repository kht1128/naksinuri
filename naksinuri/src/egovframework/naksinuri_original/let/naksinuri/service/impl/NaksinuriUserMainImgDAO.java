package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriMainImgVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriUserMainImgDAO")
public class NaksinuriUserMainImgDAO extends EgovAbstractDAO{

//	관리자화면 리스트로 확인
	public List<NaksinuriMainImgVO> mainimg_list(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainimg_list",mainimgVO);
	}

//	관리자화면 리스트에서 뷰로 이동할 때 쿼리
	public NaksinuriMainImgVO board_findCorp(NaksinuriMainImgVO mainimgVO) {
		return (NaksinuriMainImgVO) select("userMainimg_find",mainimgVO);
	}


	public String insert_imgdata(NaksinuriMainImgVO mainimgVO) {
		return (String)insert("insert_usermain_img",mainimgVO);
		
	}

	public NaksinuriMainImgVO mimg(NaksinuriMainImgVO mainimgVO) {
		return (NaksinuriMainImgVO) select("userMain_img",mainimgVO);
	}

	public void update_data(NaksinuriMainImgVO mainimgVO) {
		update("update_userMainImg",mainimgVO);
		
	}
	
	public void delete_boardtrash(NaksinuriMainImgVO mainimgVO) {
		update("gotrash_mainimg",mainimgVO);
	
}

	public List<NaksinuriMainImgVO> mainimg1(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainimg1",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mainimg2(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainimg2",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mainimg3(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainimg3",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mainimg4(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainimg4",mainimgVO);
	}

	public List<NaksinuriMainImgVO> headimg(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("headimg",mainimgVO);
	}
	
	public List<NaksinuriMainImgVO> mobile_headimg(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_headimg",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mainbanner1(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainbanner1",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mainbanner2(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainbanner2",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mainbanner3(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainbanner3",mainimgVO);
	}

	public List<NaksinuriMainImgVO> rightbanner(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("rightbanner",mainimgVO);
	}

	public void delete_boardlist(NaksinuriMainImgVO mainimgVO) {
			delete("delete_mainimg", mainimgVO);
		
	}

	public List<NaksinuriMainImgVO> mainimg_trash(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mainimg_trash",mainimgVO);
	}

	public void restore_boardlist(NaksinuriMainImgVO mainimgVO) {
		update("restore_mainimg",mainimgVO);
		
	}
	
	
	public List<NaksinuriMainImgVO> attach_img(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_userMain_attach",mainimgVO);
	}

	public NaksinuriMainImgVO mobile_mimg(NaksinuriMainImgVO mainimgVO) {
		return (NaksinuriMainImgVO) select("mobile_userMain_img",mainimgVO);
	}
	
	public List<NaksinuriMainImgVO> main_scrollimg(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("main_scrollimg",mainimgVO);
	}
	
	public List<NaksinuriMainImgVO> main_popup(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("main_popup",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mobile_mainimg1(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_mainimg1",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mobile_mainimg2(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_mainimg2",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mobile_mainimg3(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_mainimg3",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mobile_mainimg4(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_mainimg4",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mobile_mainbanner1(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_mainbanner1",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mobile_mainbanner2(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_mainbanner2",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mobile_mainbanner3(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_mainbanner3",mainimgVO);
	}

	public List<NaksinuriMainImgVO> mobile_rightbanner(NaksinuriMainImgVO mainimgVO) {
		return (List<NaksinuriMainImgVO>) list("mobile_rightbanner",mainimgVO);
	}

	



	

	
}
