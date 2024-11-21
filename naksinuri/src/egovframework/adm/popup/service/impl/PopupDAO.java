package egovframework.adm.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.adm.popup.service.PopupVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("popupDAO")
public class PopupDAO extends EgovAbstractDAO {

	public List<PopupVO> get_seadm_popup_list(PopupVO popupVO) {
		return (List<PopupVO>)list("admPopup.get_seadm_popup_list",popupVO);
	}

	public int get_seadm_popup_list_totcnt(PopupVO popupVO) {
		return (int)select("admPopup.get_seadm_popup_list_totcnt",popupVO);
	}
	
	public PopupVO get_seadm_poup_info(PopupVO popupVO) {
		return (PopupVO)select("admPopup.get_seadm_poup_info",popupVO);
	}
	
	public String set_seadm_pupup_info_reg(PopupVO popupVO) throws Exception {
		return (String)insert("admPopup.set_seadm_pupup_info_reg",popupVO);
	}

	public void remove_seadm_popup(PopupVO popupVO) {
		delete("admPopup.remove_seadm_popup",popupVO);
	}

	public void del_seadm_popup(PopupVO popupVO) {
		update("admPopup.del_seadm_popup",popupVO);
	}

	public void set_seadm_pupup_info_mod(PopupVO popupVO) {
		update("admPopup.set_seadm_pupup_info_mod",popupVO);
	}

	public void chkBoardAutoEnded() {
		update("admPopup.chkBoardAutoEnded");
	}

}
