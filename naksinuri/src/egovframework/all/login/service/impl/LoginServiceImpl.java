package egovframework.all.login.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
/*import egovframework.let.utl.fcc.service.EgovNumberUtil;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.let.utl.sim.service.EgovFileScrty;*/
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("loginService")
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService {

    @Resource(name="loginDAO")
    private LoginDAO loginDAO;
    
    /**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionLogin(LoginVO vo) throws Exception {
    	
    	// 1. 입력한 ㅂㅁ를 암호화한다.
    	String enpassword = vo.getMBR_PWD();
    	vo.setMBR_PWD(enpassword);
    	
    	// 2. 아이디와 암호화된 ㅂㅁ가 DB와 일치하는지 확인한다.
    	LoginVO loginVO = loginDAO.actionLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getMBR_ID().equals("") && (!loginVO.getMBR_PWD().equals(""))) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}
    	
    	return loginVO;
    }
    
    /**
	 * 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO searchId(LoginVO vo) throws Exception {

    	// 1. 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.
    	LoginVO loginVO = loginDAO.searchId(vo);
    	
    	// 2. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getMBR_ID().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}
    	
    	return loginVO;
    }
    
    /**
	 * 일반회원 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO selectIdFind(LoginVO loginVO) throws Exception {
    	/*
    	// 1. 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.
    	LoginVO loginVO = loginDAO.selectIdFind(vo);
    	
    	// 2. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getMBR_ID().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}
    	*/
    	return loginDAO.selectIdFind(loginVO);
    }
    
    /**
	 * 일반회원 패스워드를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO selectPwFind(LoginVO vo) throws Exception {

    	// 1. 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.
    	LoginVO loginVO = loginDAO.selectPwFind(vo);
    	
    	// 2. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getMBR_PWD().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}
    	
    	return loginVO;
    }
    
    
    
    /**
	 * ㅂㅁ를 찾는다.
	 * @param vo LoginVO
	 * @return boolean
	 * @exception Exception
	 */
    public boolean searchPassword(LoginVO vo) throws Exception {
    	
    	boolean result = true;
    	
    	// 1. 아이디, 이름, 이메일주소, ㅂㅁ 힌트, ㅂㅁ 정답이 DB와 일치하는 사용자 Password를 조회한다.
    	/*LoginVO loginVO = loginDAO.searchPassword(vo);
    	if (loginVO == null || loginVO.getPassword() == null || loginVO.getPassword().equals("")) {
    		return false;
    	}
    	
    	// 2. 임시 ㅂㅁ를 생성한다.(영+영+숫+영+영+숫=6자리)
    	String newpassword = "";
    	for (int i = 1; i <= 6; i++) {
    		// 영자
    		if (i % 3 != 0) {
    			newpassword += EgovStringUtil.getRandomStr('a', 'z');
    		// 숫자
    		} else {
    			newpassword += EgovNumberUtil.getRandomNum(0, 9);
    		}
    	}
    	
    	// 3. 임시 ㅂㅁ를 암호화하여 DB에 저장한다.
    	LoginVO pwVO = new LoginVO();
    	String enpassword = EgovFileScrty.encryptPassword(newpassword);
    	pwVO.setId(vo.getId());
    	pwVO.setPassword(enpassword);
    	pwVO.setUserSe(vo.getUserSe());
    	loginDAO.updatePassword(pwVO);*/
    	
    	return result;
    }

	@Override
	public LoginVO searchIdWithNullPwd(LoginVO loginVO) throws Exception {
		return loginDAO.searchIdWithNullPwd(loginVO);		
	}

	@Override
	public void updatePassword(LoginVO loginVO) throws Exception {
		loginDAO.updatePassword(loginVO);
	}

	/**
	 * 로그인시 접속 시간을 기록한다.
	 * @param vo LoginVO
	 * @return 
	 * @exception Exception
	 */
	@Override
	public void updateLoginHistory(LoginVO loginVO)  throws Exception {
		loginDAO.updateLoginHistory(loginVO);	
	}
	
	
	/**
	 * 로그인 시도 정보를 기록한다.
	 * @param vo LoginVO
	 * @return 
	 * @exception Exception
	 */
	@Override
	public void updateLoginRetry(LoginVO loginVO)  throws Exception {
		loginDAO.updateLoginRetry(loginVO);	
	}

	/**
	 * 로그인 시도 아이디 정보를 가져온다.
	 * @param vo LoginVO
	 * @return 
	 * @exception Exception
	 */
	@Override
	public LoginVO retryLogin(LoginVO loginVO)  throws Exception {
		return loginDAO.retryLogin(loginVO);	
	}
	
	/**
	 * 이름과 생년월일로 검증하여 로그인 한다.
	 * @param vo LoginVO
	 * @return 
	 * @exception Exception
	 */
	@Override
	public List<LoginVO> actionLoginNmBirth(LoginVO loginVO)  throws Exception {
		return loginDAO.actionLoginNmBirth(loginVO);	
	}

	@Override
	public LoginVO actionChkAdmLogin(LoginVO loginVO) throws Exception {
		return loginDAO.actionChkAdmLogin(loginVO);	
	}

	@Override
	public void updatePasswordFirst(LoginVO loginVO) throws Exception {
		loginDAO.updatePasswordFirst(loginVO);	
	}

	@Override
	public LoginVO searchSnInfo(LoginVO loginVO) throws Exception {
		return loginDAO.searchSnInfo(loginVO);	
	}

	@Override
	public void updateChangeInfoFirst(LoginVO loginVO) throws Exception {
		loginDAO.updateChangeInfoFirst(loginVO);	
	}

	@Override
	public int searchAuthOverlayInfo(LoginVO loginVO) {
		return loginDAO.searchAuthOverlayInfo(loginVO);
	}

	@Override
	public void actionLoginLockClearPwd(LoginVO loginVO) {
		loginDAO.actionLoginLockClearPwd(loginVO);
	}

	@Override
	public String searchAuthOverlayInfoMbrId(LoginVO loginVO) {
		return loginDAO.searchAuthOverlayInfoMbrId(loginVO);
	}
		
}