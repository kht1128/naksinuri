package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriPolicyDAO")
public class NaksinuriPolicyDAO extends EgovAbstractDAO{

	public String insert_VOC(NaksinuriPolicyVO policyVO) {
		
		return (String)insert("insert_VOC",policyVO);
		
	}

	public List<NaksinuriPolicyVO> getVOCList(NaksinuriPolicyVO policyVO) {
		return (List<NaksinuriPolicyVO>) list("voc_list",policyVO);
	}

	public void deleteVOC(NaksinuriPolicyVO policyVO) {
		delete("deleteVOC",policyVO);
		
	}

	public NaksinuriPolicyVO voc_findCorp(NaksinuriPolicyVO policyVO) {
		return(NaksinuriPolicyVO) select("voc_findCorp", policyVO);
		
	}

	public List<NaksinuriPolicyVO> va_file(NaksinuriPolicyVO policyVO) {
		return (List<NaksinuriPolicyVO>) list("va_file",policyVO);
	}

	public void trashVOC(NaksinuriPolicyVO policyVO) {
		update("gotrash_voc",policyVO);
		
	}
	public void restoreVOC(NaksinuriPolicyVO policyVO) {
		update("restore_voc",policyVO);
		
	}
	public void voc_answer_write(NaksinuriPolicyVO policyVO) {
		update("voc_answer_write",policyVO);
		
	}
	
	public List<NaksinuriPolicyVO> getVOCTrash(NaksinuriPolicyVO policyVO) {
		return  (List<NaksinuriPolicyVO>) list("voc_trash",policyVO);
	}






}
