package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;

public interface NaksinuriPolicyService {

	/**관리자 고객의 소리 조회 및 삭제*/
	public List<NaksinuriPolicyVO> getVOCList(NaksinuriPolicyVO policyVO)throws Exception;
	
	public NaksinuriPolicyVO voc_findCorp(NaksinuriPolicyVO policyVO)throws Exception;
	
	public void deleteVOC(NaksinuriPolicyVO policyVO) throws Exception;
	
	
	/**사용자 고객의 소리 등록*/
	public void insertVOC(NaksinuriPolicyVO policyVO) throws Exception;

	public List<NaksinuriPolicyVO> va_file(NaksinuriPolicyVO policyVO) throws Exception ;

	public void trashVOC(NaksinuriPolicyVO policyVO) throws Exception;

	public List<NaksinuriPolicyVO> getVOCTrash(NaksinuriPolicyVO policyVO) throws Exception;

	public void restoreVOC(NaksinuriPolicyVO policyVO) throws Exception;

	public void voc_answer_write(NaksinuriPolicyVO policyVO) throws Exception;






	
	

}
