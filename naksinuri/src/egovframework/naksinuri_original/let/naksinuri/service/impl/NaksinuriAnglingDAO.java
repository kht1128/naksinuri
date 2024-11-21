package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAnglingVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriAnglingDAO")
public class NaksinuriAnglingDAO extends EgovAbstractDAO{

	public List<NaksinuriAnglingVO> getAnglingList(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_list",anglingVO);
	}
	
	public List<NaksinuriAnglingVO> getAng_river_list(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_river_list",anglingVO);
	}

	public List<NaksinuriAnglingVO> getAng_sea_list(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_sea_list",anglingVO);
	}

	public List<NaksinuriAnglingVO> getAng_lure_list(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_lure_list",anglingVO);
	}

	public void view_update(NaksinuriAnglingVO anglingVO) {
		 update("angling_uphit", anglingVO);
	}

	public NaksinuriAnglingVO board_findCorp(NaksinuriAnglingVO anglingVO) {
		return (NaksinuriAnglingVO) select("angling_findCorp", anglingVO);
	}

	public List<NaksinuriAnglingVO> select_list_post(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("select_angling_post",anglingVO);
	}

	public NaksinuriAnglingVO select_next(NaksinuriAnglingVO anglingVO) {
		return (NaksinuriAnglingVO) select("angling_next",anglingVO);
	}

	public NaksinuriAnglingVO select_prev(NaksinuriAnglingVO anglingVO) {
		return (NaksinuriAnglingVO) select("angling_prev",anglingVO);
	}

	public List<NaksinuriAnglingVO> getAnglingList_admin(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_list_admin",anglingVO);
	}

	public List<NaksinuriAnglingVO> getAngling_river_List_admin(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_river_list_admin",anglingVO);
	}

//	public List<NaksinuriAnglingVO> getAngling_sea_List_admin(NaksinuriAnglingVO anglingVO) {
//		return (List<NaksinuriAnglingVO>) list("angling_sea_list_admin",anglingVO);
//	}
//
//	public List<NaksinuriAnglingVO> getAngling_lure_List_admin(NaksinuriAnglingVO anglingVO) {
//		return (List<NaksinuriAnglingVO>) list("angling_lure_list_admin",anglingVO);
//	}

	
	public List<NaksinuriAnglingVO> getAnglingtrash_admin(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_trash_admin",anglingVO);
	}

	
	public void delete_boardlist(NaksinuriAnglingVO anglingVO) {
		update("deleteAngling", anglingVO);
		
	}

	public void update_data(NaksinuriAnglingVO anglingVO) {
		update("updateAngling", anglingVO);
		
	}

	public void insert_data(NaksinuriAnglingVO anglingVO) {
		insert("insertAngling",anglingVO);
		
	}

	public List<NaksinuriAnglingVO> select_list_bobo(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_list_bobo",anglingVO);
	}

	public List<NaksinuriAnglingVO> getAngling_river_trash_admin(NaksinuriAnglingVO anglingVO) {
		return (List<NaksinuriAnglingVO>) list("angling_river_trash_admin",anglingVO);
	}

	public void delete_trashlist(NaksinuriAnglingVO anglingVO) {
		delete("delete_anglingtrash",anglingVO);
		
	}

	public void restore_anglinglist(NaksinuriAnglingVO anglingVO) {
		update("restore_anglinglist",anglingVO);
		
	}

	
	

}
