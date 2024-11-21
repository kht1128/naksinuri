package egovframework.all.codeset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("codeSetService")
public class CodeSetServiceImpl extends EgovAbstractServiceImpl implements CodeSetService {

	/** MainDAO */
	@Resource(name = "codeSetDAO")
	private CodeSetDAO codeSetDAO;
	
	@Override
	public List<CodeSetVO> get_codeset_list(CodeSetVO codeSetVO) throws Exception {
		return codeSetDAO.get_codeset_list(codeSetVO);
	}

	@Override
	public CodeSetVO get_codeset_info(CodeSetVO codeSetVO) throws Exception {
		return codeSetDAO.get_codeset_info(codeSetVO);
	}

	@Override
	public CodeSetVO get_codeset_info_chk_mbr_dtl(CodeSetVO codeSetVO) {
		return codeSetDAO.get_codeset_info_chk_mbr_dtl(codeSetVO);
	}

	@Override
	public void set_codeset_mod(CodeSetVO updateCodeSetVO) throws Exception {
		codeSetDAO.set_codeset_mod(updateCodeSetVO);
	}

	@Override
	public void set_codeset_insert(CodeSetVO insertCodeSetVO) throws Exception {
		codeSetDAO.set_codeset_insert(insertCodeSetVO);
		
	}

	@Override
	public void set_codeset_delete(CodeSetVO deleteCodeSetVO) throws Exception {
		codeSetDAO.set_codeset_delete(deleteCodeSetVO);
		
	}

	@Override
	public void set_codeset_mod_for_all_master(CodeSetVO changeCodeSetVO) throws Exception {
		codeSetDAO.set_codeset_mod_for_all_master(changeCodeSetVO);
		
	}

	
}
