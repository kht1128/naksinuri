package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.Tide_FCVO;
import egovframework.naksinuri_original.let.naksinuri.service.Tide_TMVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriTideDAO")
public class NaksinuriTideDAO extends EgovAbstractDAO {

	public List<Tide_TMVO> tidetime_list(Tide_TMVO tide_tmVO) {
		return (List<Tide_TMVO>)list("tidetm_list",tide_tmVO);
	}

	public List<Tide_FCVO> tidefc_list(Tide_FCVO tide_fcVO) {
		return (List<Tide_FCVO>)list("tidefc_list",tide_fcVO);
	}
	
}
