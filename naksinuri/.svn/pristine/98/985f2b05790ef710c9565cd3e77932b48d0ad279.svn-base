package egovframework.all.error.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.all.error.service.ErrorService;
import egovframework.all.error.service.ErrorVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("errorService")
public class ErrorServiceImpl extends EgovAbstractServiceImpl implements ErrorService {

	@Resource(name = "errorDAO")
	private ErrorDAO errorDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	@Override
	public void set_error_reg(ErrorVO errorVO) throws Exception {
		errorDAO.set_error_reg(errorVO);
	}

	@Override
	public List<ErrorVO> get_error_list(ErrorVO errorVO) {
		return (List<ErrorVO>)errorDAO.get_error_list(errorVO);
	}

	@Override
	public int get_error_list_totcnt(ErrorVO errorVO) {
		return errorDAO.get_error_list_totcnt(errorVO);
	}

	
}
