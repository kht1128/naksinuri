package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.com.cmm.service.impl.NaksinuriOriginalFileManageDAO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriVO;



@Service("NaksinuriSanupService")
public class NaksinuriSanupServiceImpl implements NaksinuriSanupService{
	@Resource(name = "NaksinuriDAO")
    private NaksinuriDAO naksinuriDAO;
	
	@Resource(name="NaksinuriSanupService")
	private NaksinuriSanupService service_sanup;
	
	@Resource(name = "NaksinuriOriginalFileManageDAO")
	private NaksinuriOriginalFileManageDAO fileMngDAO;

	@Override
	public List<NaksinuriSanupVO> getListIndustry(NaksinuriSanupVO SanupVO) throws Exception {
		return naksinuriDAO.getListIndustry(SanupVO);
	}
	
	@Override
	public List<NaksinuriSanupVO> getMapListIndustry(NaksinuriSanupVO SanupVO) throws Exception {
		return naksinuriDAO.getMapListIndustry(SanupVO);
	}

	@Override
	public void ind_insert(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.ind_insert(SanupVO);		
	}
	@Override
	public void ind_insert_Preview(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.ind_insert_Preview(SanupVO);		
	}
	@Override
	public void ind_insert_PreviewP(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.ind_insert_PreviewP(SanupVO);		
	}

	@Override
	public NaksinuriSanupVO ind_find(NaksinuriSanupVO SanupVO) throws Exception {
		return naksinuriDAO.ind_find(SanupVO);
	}
	@Override
	public NaksinuriSanupVO ind_find_Preview(NaksinuriSanupVO SanupVO) throws Exception {
		return naksinuriDAO.ind_find_Preview(SanupVO);
	}
	
	@Override
	public void ind_update(NaksinuriSanupVO sanupVO) throws Exception{
		naksinuriDAO.ind_update(sanupVO);
	}
	@Override
	public void ind_delete(NaksinuriSanupVO sanupVO) throws Exception{
		naksinuriDAO.ind_delete(sanupVO);
	}

	@Override
	public void ind_restore(NaksinuriSanupVO sanupVO) throws Exception {
		naksinuriDAO.ind_restore(sanupVO);
		
	}
	
	@Override
	public List<NaksinuriSanupVO> ind_fsimg(NaksinuriSanupVO sanupVO) throws Exception {
		return naksinuriDAO.ind_fsimg(sanupVO);
	}
	@Override
	public List<NaksinuriSanupVO> ind_fsimg_Preview(NaksinuriSanupVO sanupVO) throws Exception {
		return naksinuriDAO.ind_fsimg_Preview(sanupVO);
	}

	@Override
	public void gotrash_indlist(NaksinuriSanupVO sanupVO) throws Exception {
		naksinuriDAO.gotrash_indlist(sanupVO);
	}

	@Override
	public List<NaksinuriSanupVO> get_trashListIndustry(NaksinuriSanupVO sanupVO) throws Exception {
		return naksinuriDAO.get_trashListIndustry(sanupVO);
	}

	@Override
	public void up_hit(NaksinuriSanupVO sanupVO) throws Exception {
		naksinuriDAO.industry_uphit(sanupVO);
		
	}

	@Override
	public void selectNaksinuriSanupInfo(NaksinuriSanupVO sanupVO) throws Exception {
		naksinuriDAO.selectNaksinuriSanupInfo(sanupVO);
	}

	@Override
	public List<NaksinuriSanupVO> getListNaksinuri_admin_preview_ind_list(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_preview_ind_list(naksinuriSanupVO);
	}	
	@Override
	public NaksinuriSanupVO getListNaksinuri_admin_preview_ind_detail(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_preview_ind_detail(naksinuriSanupVO); 
	}	 
	@Override
	public NaksinuriSanupVO getListNaksinuri_admin_ind_detail(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_ind_detail(naksinuriSanupVO); 
	}	 
	
	@Override
	public void delpreview_ind_data(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		naksinuriDAO.delpreview_ind_data(naksinuriSanupVO); 
	}	 
	@Override
	public void  delpreview_ind_file(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		naksinuriDAO.delpreview_ind_file(naksinuriSanupVO); 
	}	 

	@Override
	public List<NaksinuriSanupVO> previewlist_ind(NaksinuriSanupVO naksinuriSanupVO) {
		return naksinuriDAO.previewlist_ind(naksinuriSanupVO);
	}

	
}
