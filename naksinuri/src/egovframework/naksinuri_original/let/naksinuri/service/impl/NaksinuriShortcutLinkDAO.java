package egovframework.naksinuri_original.let.naksinuri.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriShortcutLinkVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriShortcutLinkDAO")
public class NaksinuriShortcutLinkDAO extends EgovAbstractDAO {
	
	public NaksinuriShortcutLinkVO get_link(NaksinuriShortcutLinkVO staticVO) {
		return (NaksinuriShortcutLinkVO) select("get_shortcut_link",staticVO);
	}
	
}
