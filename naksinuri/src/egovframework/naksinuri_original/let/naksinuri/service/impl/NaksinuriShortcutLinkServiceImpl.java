package egovframework.naksinuri_original.let.naksinuri.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriShortcutLinkService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriShortcutLinkVO;

@Service("NaksinuriShortcutLinkService")
public class NaksinuriShortcutLinkServiceImpl implements NaksinuriShortcutLinkService {

	@Resource(name = "NaksinuriShortcutLinkDAO")
	private NaksinuriShortcutLinkDAO dao;
	
	@Override
	public NaksinuriShortcutLinkVO get_link(NaksinuriShortcutLinkVO staticVO) {
		return dao.get_link(staticVO);
	}

}
