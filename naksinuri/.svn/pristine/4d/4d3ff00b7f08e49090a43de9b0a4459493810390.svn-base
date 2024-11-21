package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriTideService;
import egovframework.naksinuri_original.let.naksinuri.service.Tide_FCVO;
import egovframework.naksinuri_original.let.naksinuri.service.Tide_TMVO;

@Service("NaksinuriTideService")
public class NaksinuriTideServiceImpl implements NaksinuriTideService{
	
	@Resource(name = "NaksinuriTideDAO")
	private NaksinuriTideDAO dao;
	
	@Override
	public List<Tide_TMVO> tidetime_list(Tide_TMVO tide_tmVO) throws Exception {
		return dao.tidetime_list(tide_tmVO);
	}

	@Override
	public List<Tide_FCVO> tidefc_list(Tide_FCVO tide_fcVO) throws Exception {
		return dao.tidefc_list(tide_fcVO);
	}

}
