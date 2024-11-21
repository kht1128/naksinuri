package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriVO;



@Service("NaksinuriGoneService")
public class NaksinuriGoneServiceImpl implements NaksinuriGoneService{
	@Resource(name = "NaksinuriDAO")
    private NaksinuriDAO naksinuriDAO;
	
	@Resource(name="NaksinuriGoneService")
	private NaksinuriGoneService service;

	@Override
	public List<NaksinuriGoneVO> getListGone(NaksinuriGoneVO GoneVO) throws Exception {
		return naksinuriDAO.getListGone(GoneVO);
	}
	
	@Override
	public List<NaksinuriGoneVO> getListGonemobile(NaksinuriGoneVO GoneVO) throws Exception {
		return naksinuriDAO.getListGonemobile(GoneVO);
	}

	@Override
	public void insert_data(NaksinuriGoneVO GoneVO) throws Exception {
		naksinuriDAO.insert_data_gone(GoneVO);
		
	}

	@Override
	public NaksinuriGoneVO promotion_findCorp(NaksinuriGoneVO GoneVO) throws Exception {
		return naksinuriDAO.promotion_findCorp(GoneVO);
	}

	@Override
	public void update_data(NaksinuriGoneVO GoneVO) throws Exception {
		naksinuriDAO.update_data_gone(GoneVO);
		
	}

	@Override
	public void delete_list(NaksinuriGoneVO GoneVO) throws Exception {
		naksinuriDAO.delete_list(GoneVO);
		
	}

	@Override
	public List<NaksinuriGoneVO> getListGone_admin(NaksinuriGoneVO GoneVO) throws Exception {
		return naksinuriDAO.getListGone_admin(GoneVO);
		
	}

	@Override
	public List<NaksinuriGoneVO> getTrashGone_admin(NaksinuriGoneVO goneVO) throws Exception {
		return naksinuriDAO.getTrashGone_admin(goneVO);
	}

	@Override
	public void trash_list(NaksinuriGoneVO goneVO) throws Exception {
		naksinuriDAO.trash_list(goneVO);
		
	}

	@Override
	public void restore_plocation(NaksinuriGoneVO goneVO) throws Exception {
		naksinuriDAO.restore_plocation(goneVO);
		
	}
	

}
