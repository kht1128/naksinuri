package egovframework.all.login.service.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import egovframework.all.login.service.LoginVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends EgovAbstractDAO {
	
	/** log */
    protected static final Log LOG = LogFactory.getLog(LoginDAO.class);
    
	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionLogin(LoginVO vo) throws Exception {
    	return (LoginVO)selectByPk("loginDAO.actionLogin", vo);
    }

    /**
	 * 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO searchId(LoginVO vo) throws Exception {
    	
    	return (LoginVO)selectByPk("loginDAO.searchId", vo);
    }
    
    /**
	 * 일반회원아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO selectIdFind(LoginVO vo) throws Exception {
    	//return (LoginVO)selectByPk("loginDAO.selectIdFind", vo);
    	return (LoginVO) select("loginDAO.selectIdFind", vo);
    }
    
    /**
	 * 일반회원 ㅂㅁ를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO selectPwFind(LoginVO vo) throws Exception {
    	
    	return (LoginVO)selectByPk("loginDAO.selectPwFind", vo);
    }
    
    
    /**
	 * ㅂㅁ를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO searchPassword(LoginVO vo) throws Exception {
    	
    	return (LoginVO)selectByPk("loginDAO.searchPassword", vo);
    }
    
    /**
	 * 변경된 ㅂㅁ를 저장한다.
	 * @param vo LoginVO
	 * @exception Exception
	 */
    public void updatePassword(LoginVO vo) throws Exception {
    	update("loginDAO.updatePassword", vo);
    }

    /**
	 * 아이디 존재, ㅂㅁ null
	 * 기존 회원인지 찾는다.
	 * @param vo LoginVO
	 * @exception Exception
	 */
	public LoginVO searchIdWithNullPwd(LoginVO loginVO) {
		return (LoginVO) select("loginDAO.searchIdWithNullPwd",loginVO);
	}

	/**
	 * 로그인시 접속 시간을 기록한다.
	 * @param vo LoginVO
	 * @exception Exception
	 */
	public void updateLoginHistory(LoginVO loginVO) {
		update("loginDAO.updateLoginHistory", loginVO);
	}
	
	/**
	 * 로그인 시도 정보를 기록한다. 
	 * */
	public void updateLoginRetry(LoginVO loginVO) {
		update("loginDAO.updateLoginRetry", loginVO);
	}

	/**
	 * 로그인 시도시 아이디 정보를 가져온다. 
	 * */
	public LoginVO retryLogin(LoginVO loginVO) {
		return (LoginVO) select("loginDAO.retryLogin", loginVO);
	}

	/**
	 * 이름과 생년월일로 검증하여 로그인 한다.
	 * */
	public List<LoginVO> actionLoginNmBirth(LoginVO loginVO) {
		return (List<LoginVO>) list("loginDAO.actionLoginNmBirth", loginVO);
	}

	public LoginVO actionChkAdmLogin(LoginVO loginVO) {
		return (LoginVO) select("loginDAO.actionChkAdmLogin", loginVO);
	}

	public void updatePasswordFirst(LoginVO loginVO) {
		update("loginDAO.updatePasswordFirst", loginVO);
	}

	public LoginVO searchSnInfo(LoginVO loginVO) {
		return (LoginVO) select("loginDAO.searchSnInfo", loginVO);
	}

	public void updateChangeInfoFirst(LoginVO loginVO) {
		update("loginDAO.updateChangeInfoFirst", loginVO);
	}

	public int searchAuthOverlayInfo(LoginVO loginVO) {
		return (int) select("loginDAO.searchAuthOverlayInfo", loginVO);
	}

	public void actionLoginLockClearPwd(LoginVO loginVO) {
		update("loginDAO.actionLoginLockClearPwd", loginVO);
	}

	public String searchAuthOverlayInfoMbrId(LoginVO loginVO) {
		return (String) select("loginDAO.searchAuthOverlayInfoMbrId", loginVO);
	}

}
