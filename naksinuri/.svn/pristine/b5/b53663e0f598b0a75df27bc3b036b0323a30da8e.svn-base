package egovframework.all.codeset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.all.codeset.service.CodeSetVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("codeSetDAO")
public class CodeSetDAO extends EgovAbstractDAO {

	public List<CodeSetVO> get_codeset_list(CodeSetVO codeSetVO) {
		return (List<CodeSetVO>)list("get_codeset_list",codeSetVO);
	}

	public CodeSetVO get_codeset_info(CodeSetVO codeSetVO) {
		return (CodeSetVO)select("get_codeset_info",codeSetVO);
	}

	public CodeSetVO get_codeset_info_chk_mbr_dtl(CodeSetVO codeSetVO) {
		return (CodeSetVO)select("get_codeset_info_chk_mbr_dtl",codeSetVO);
	}

	public void set_codeset_mod(CodeSetVO updateCodeSetVO) {
		update("set_codeset_mod",updateCodeSetVO);
	}

	public void set_codeset_insert(CodeSetVO insertCodeSetVO) {
		insert("set_codeset_insert",insertCodeSetVO);
		
	}

	public void set_codeset_delete(CodeSetVO deleteCodeSetVO) {
		delete("set_codeset_delete", deleteCodeSetVO);
	}

	public void set_codeset_mod_for_all_master(CodeSetVO changeCodeSetVO) {
		update("set_codeset_mod_for_all_master",changeCodeSetVO);
		
	}
	


}
