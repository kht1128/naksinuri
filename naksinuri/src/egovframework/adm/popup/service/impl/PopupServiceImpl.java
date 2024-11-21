package egovframework.adm.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.adm.popup.service.PopupService;
import egovframework.adm.popup.service.PopupVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("popupService")
public class PopupServiceImpl extends EgovAbstractServiceImpl implements PopupService {

	/** PopupDAO */
	@Resource(name="popupDAO")
	private PopupDAO popupDAO;
	
	@Override
	public List<PopupVO> get_seadm_popup_list(PopupVO popupVO) throws Exception {
		return popupDAO.get_seadm_popup_list(popupVO);
	}

	@Override
	public int get_seadm_popup_list_totcnt(PopupVO popupVO) throws Exception {
		return popupDAO.get_seadm_popup_list_totcnt(popupVO);
	}
	
	@Override
	public PopupVO get_seadm_poup_info(PopupVO popupVO) throws Exception {
		return popupDAO.get_seadm_poup_info(popupVO);
	}
	
	@Override
	public String set_seadm_pupup_info_reg(PopupVO popupVO) throws Exception {
		return popupDAO.set_seadm_pupup_info_reg(popupVO);
	}

	@Override
	public void remove_seadm_popup(PopupVO popupVO) throws Exception {
		popupDAO.remove_seadm_popup(popupVO);
	}

	@Override
	public void del_seadm_popup(PopupVO popupVO) throws Exception {
		popupDAO.del_seadm_popup(popupVO);
	}

	@Override
	public void set_seadm_pupup_info_mod(PopupVO popupVO) throws Exception {
		popupDAO.set_seadm_pupup_info_mod(popupVO);
	}

	@Override
	public void chkBoardAutoEnded() throws Exception {
		popupDAO.chkBoardAutoEnded();
	}
		
}
