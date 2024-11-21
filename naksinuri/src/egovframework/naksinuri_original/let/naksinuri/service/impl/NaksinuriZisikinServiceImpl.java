package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.com.cmm.service.impl.NaksinuriOriginalFileManageDAO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;

@Service("NaksinuriZisikinService")
public class NaksinuriZisikinServiceImpl implements NaksinuriZisikinService  {
	
	@Resource(name = "NaksinuriDAO")
    private NaksinuriDAO naksinuriDAO;
	
	@Resource(name="NaksinuriZisikinService")
	private NaksinuriZisikinService service;
	
	@Resource(name = "NaksinuriOriginalFileManageDAO")
	private NaksinuriOriginalFileManageDAO fileMngDAO;

	@Override
	public List<NaksinuriZisikinVO> getListZisik(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.getListZisik(naksinuriZisikinVO);
	}
	
	@Override
	public List<NaksinuriZisikinVO> getTrashZisik(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.getTrashZisik(naksinuriZisikinVO);
	}

	
	@Override
	public List<NaksinuriZisikinVO> getListZazu(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception{
		return naksinuriDAO.getListZazu(naksinuriZisikinVO);
	}
	
	@Override
	public void insertZazu(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception{
		naksinuriDAO.insertZazu(naksinuriZisikinVO);
	}
	
	@Override
	public void updateZazu(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception{
		naksinuriDAO.updateZazu(naksinuriZisikinVO);
	}
	
	@Override
	public void deleteZazu(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.deleteZazu(naksinuriZisikinVO);
	}

	@Override
	public NaksinuriZisikinVO nuri_find(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.nuri_find(naksinuriZisikinVO);
	}

	@Override
	public void zisik_view_count(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.zisik_view_count(naksinuriZisikinVO);
	}
	
	@Override
	public NaksinuriZisikinVO zisik_find(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.zisik_find(naksinuriZisikinVO);
	}

	@Override
	public List<NaksinuriZisikinVO> getListAnswer(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.getListAnswer(naksinuriZisikinVO);
	}

	@Override
	public NaksinuriZisikinVO zisik_next(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.zisik_next(naksinuriZisikinVO);
	}

	@Override
	public NaksinuriZisikinVO zisik_prev(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.zisik_prev(naksinuriZisikinVO);
	}

	@Override
	public void insert_zisik(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.insert_zisik(naksinuriZisikinVO);
	}
	
	@Override
	public void update_zisik(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.update_zisik(naksinuriZisikinVO);
	}
	
	@Override
	public void delete_zisik(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.delete_zisik(naksinuriZisikinVO);
	}
	
	@Override
	public NaksinuriZisikinVO zpass_find(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception{
		return naksinuriDAO.zpass_find(naksinuriZisikinVO);
	}

	@Override
	public NaksinuriZisikinVO ans_find(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.ans_find(naksinuriZisikinVO);
	}

	@Override
	public void insert_ans(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.insert_ans(naksinuriZisikinVO);
		
	}

	@Override
	public void update_ans(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.update_ans(naksinuriZisikinVO);
	}

	@Override
	public void delete_ans(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.delete_ans(naksinuriZisikinVO);
	}

	@Override
	public void gotrash_zisik(NaksinuriZisikinVO zisikinVO) throws Exception {
		naksinuriDAO.gotrash_zisik(zisikinVO);
		
	}

	@Override
	public List<NaksinuriZisikinVO> getTrashZazu(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		return naksinuriDAO.getTrashZazu(naksinuriZisikinVO);
	}

	@Override
	public void gotrashZazu(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.gotrashZazu(naksinuriZisikinVO);
		
	}

	@Override
	public void restore_zisik(NaksinuriZisikinVO zisikinVO) throws Exception {
		naksinuriDAO.restore_zisik(zisikinVO);
		
	}

	@Override
	public void restoreZazu(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception {
		naksinuriDAO.restoreZazu(naksinuriZisikinVO);
		
	}

	

}
