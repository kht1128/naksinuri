package egovframework.all.login.service;

import java.util.List;

public interface LoginService {

	/**
	 * 일반 로그인을 처리한다
	 * @return LoginVO
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public LoginVO actionLogin(LoginVO vo)
	  throws Exception;

	/**
	 * 아이디를 조회한다.
	 * @return LoginVO
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public LoginVO searchId(LoginVO vo)
	  throws Exception;

	/**
	 * ㅂㅁ를 찾는다.
	 * @return boolean
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public boolean searchPassword(LoginVO vo)
	  throws Exception;
	
	/**
	 * 아이디 존재, ㅂㅁ null
	 * 기존 회원인지 찾는다.
	 * @return boolean
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public LoginVO searchIdWithNullPwd(LoginVO loginVO) throws Exception;

	/**
	 * ㅂㅁ 변경
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public void updatePassword(LoginVO loginVO) throws Exception;

	/**
	 * 아이디를 찾는다.( 이름,생년월일 로 만 )
	 * @return LoginVO
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public LoginVO selectIdFind(LoginVO loginVO) throws Exception;

	/**
	 * 로그인시 접속 시간을 기록한다.
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public void updateLoginHistory(LoginVO loginVO) throws Exception;

	/**
	 * 로그인 시도 정보를 기록한다.
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public void updateLoginRetry(LoginVO loginVO) throws Exception;

	/**
	 * 로그인 시도 아이디 정보를 가져온다.
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public LoginVO retryLogin(LoginVO loginVO) throws Exception;

	/**
	 * 이름과 생년월일로 검증하여 로그인 한다.
	 * 
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public List<LoginVO> actionLoginNmBirth(LoginVO loginVO) throws Exception;

	
	/**
	 * 관리자용 로그인 체크 (ㅂㅁ 보유 여부)
	 **/
	public LoginVO actionChkAdmLogin(LoginVO loginVO) throws Exception;
	
	/**
	 * 관리자용 최초 아이디,ㅂㅁ 변경
	 **/
	public void updatePasswordFirst(LoginVO loginVO) throws Exception;

	/**
	 * 관리자용 MBR_SN 으로 회원정보 검색
	 * */
	public LoginVO searchSnInfo(LoginVO loginVO) throws Exception;
	
	/**
	 * 관리자용 본인인증을 위한 개인정보 변경 이름,생년월일,휴대폰연락처
	 **/
	public void updateChangeInfoFirst(LoginVO loginVO) throws Exception;

	/**
	 * 관리자용 본인인증을 위한 개인정보 변경시 중복체크 (본인정보제외)
	 **/
	public int searchAuthOverlayInfo(LoginVO loginVO);

	/**
	 * ㅂㅁ 오류 횟수 초과시 차단 처리 (ㅂㅁ 초기화) 
	 **/
	public void actionLoginLockClearPwd(LoginVO loginVO);

	public String searchAuthOverlayInfoMbrId(LoginVO loginVO);

}