package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.com.cmm.service.impl.NaksinuriOriginalFileManageDAO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyVO;

@Service("NaksinuriPolicyService")


public class NaksinuriPolicyServiceImpl implements NaksinuriPolicyService {

	
	
	@Resource(name="NaksinuriPolicyDAO")
	private NaksinuriPolicyDAO naksinuriPolicyDAO;
	
	@Resource(name="NaksinuriPolicyService")
	private NaksinuriPolicyService service;
	
	@Resource(name = "NaksinuriOriginalFileManageDAO")
	private NaksinuriOriginalFileManageDAO fileMngDAO;

	@Override
	public void insertVOC(NaksinuriPolicyVO policyVO) throws Exception {
		naksinuriPolicyDAO.insert_VOC(policyVO);
		
	}

	@Override
	public List<NaksinuriPolicyVO> getVOCList(NaksinuriPolicyVO policyVO)throws Exception {
		return naksinuriPolicyDAO.getVOCList(policyVO);
	}

	@Override
	public void deleteVOC(NaksinuriPolicyVO policyVO)throws Exception {
		naksinuriPolicyDAO.deleteVOC(policyVO);
		
	}

	@Override
	public NaksinuriPolicyVO voc_findCorp(NaksinuriPolicyVO policyVO)throws Exception {
		return naksinuriPolicyDAO.voc_findCorp(policyVO);
	}

	@Override
	public List<NaksinuriPolicyVO> va_file(NaksinuriPolicyVO policyVO)throws Exception  {
		return naksinuriPolicyDAO.va_file(policyVO);
	}

	@Override
	public void trashVOC(NaksinuriPolicyVO policyVO) throws Exception {
		naksinuriPolicyDAO.trashVOC(policyVO);
		
	}

	@Override
	public List<NaksinuriPolicyVO> getVOCTrash(NaksinuriPolicyVO policyVO) throws Exception {
		return naksinuriPolicyDAO.getVOCTrash(policyVO);
	}

	@Override
	public void restoreVOC(NaksinuriPolicyVO policyVO) throws Exception {
		naksinuriPolicyDAO.restoreVOC(policyVO);
		
	}
	
	@Override
	public void voc_answer_write(NaksinuriPolicyVO policyVO) throws Exception {
		naksinuriPolicyDAO.voc_answer_write(policyVO);
		
	}


	


}
