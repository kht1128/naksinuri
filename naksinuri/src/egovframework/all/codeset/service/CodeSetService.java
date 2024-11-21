package egovframework.all.codeset.service;

import java.util.List;

public interface CodeSetService {
	
	//코드 목록 가져오기
	List<CodeSetVO> get_codeset_list(CodeSetVO codeSetVO) throws Exception;
	//코드 정보 가져오기
	CodeSetVO get_codeset_info(CodeSetVO codeSetVO) throws Exception;
	//교육신청시 회원상세정보 지역 검증을 위한 전용
	CodeSetVO get_codeset_info_chk_mbr_dtl(CodeSetVO codeSetVO) throws Exception;
	void set_codeset_mod(CodeSetVO updateCodeSetVO) throws Exception;
	
	void set_codeset_insert(CodeSetVO insertCodeSetVO) throws Exception;
	
	void set_codeset_delete(CodeSetVO deleteCodeSetVO) throws Exception;
	
	void set_codeset_mod_for_all_master(CodeSetVO changeCodeSetVO) throws Exception;
	
}
