package egovframework.cti.member.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.adm.member.service.AdmCntnAuthorIpVO;
import egovframework.adm.member.service.AdmMemberService;
import egovframework.adm.member.service.AdmMemberVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.cti.member.service.CtiMemberService;
import egovframework.cti.member.service.CtiMemberVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.member.service.LogAdmAuthVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class CtiMemberController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CtiMemberController.class);
		
	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	@Resource(name = "ctiMemberService")
	private CtiMemberService ctiMemberService;
	
	@Resource(name = "admMemberService")
	private AdmMemberService admMemberService;
	
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	
	
	//관리자(CTI) 로그인 ------------------------------------------------
	@RequestMapping(value = "/cti/member/login.do")
	public String edu_member_login(SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
    	if(loginVO!=null) {
	    	return "redirect:/cti/index.do";
    	}
		
		/*String ip = PublicUtils.getClientIpAddr(request);
		String path = "";
		
		AdmCntnAuthorIpVO admCntnAuthorIpVO = new AdmCntnAuthorIpVO();
		admCntnAuthorIpVO.setCNTN_AUTHOR_IP(ip);
		admCntnAuthorIpVO = admMemberService.get_cntn_author_ip_info(admCntnAuthorIpVO);	// 접속 권한 IP 정보
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		if(admCntnAuthorIpVO != null && admCntnAuthorIpVO.getCNTN_AUTHOR_IP().length() != 0) {
			
			log_dscrp.append("[ cti 관리자 페이지 " + admCntnAuthorIpVO.getCNTN_AUTHOR_IP() + " 접속 - " + ip + " ]");
			path = "cti/member/login";
			
			model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid"));
	  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath"));
	  		model.addAttribute("kcbokcert_sitenm",propertiesService.getString("KcbOkCert.siteNm"));
	  		model.addAttribute("kcbokcert_siteurl",propertiesService.getString("KcbOkCert.siteUrl"));
			
		} else {
			log_dscrp.append("[ 허용 ip외 cti 관리자 페이지 접속시도 - " + ip + " ]");
			LOGGER.debug("허용 ip외 cti 관리자 페이지 접속시도  : " + ip);
			path = "redirect:/index.do";
		}
		log_msg.append(admCntnAuthorIpVO);
		tbl_inf.append("CNTN_AUTHOR_IP_TB");
		tbl_sn.append(ip);
		
		try {	
			*//**
			 * LOG RECORED (로그기록)
			 **//*
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(admCntnAuthorIpVO));
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			//mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			//mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
    	
  		
		return path;*/
    	
    	model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid"));
  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath"));
  		model.addAttribute("kcbokcert_sitenm",propertiesService.getString("KcbOkCert.siteNm"));
  		model.addAttribute("kcbokcert_siteurl",propertiesService.getString("KcbOkCert.siteUrl"));
  		
  		return "cti/member/login";
	}

	//관리자(CTI) 로그인 - 인증  -------------------------------------------------
	@RequestMapping(value="/cti/member/login_act.do")
    public String edu_member_login_act(@ModelAttribute("loginVO") LoginVO loginVO, 
    		HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		
		model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid"));
  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath"));
  		model.addAttribute("kcbokcert_sitenm",propertiesService.getString("KcbOkCert.siteNm"));
  		model.addAttribute("kcbokcert_siteurl",propertiesService.getString("KcbOkCert.siteUrl"));
		
  		if(loginVO==null || loginVO.getMBR_ID()==null || loginVO.getMBR_ID().length()==0) {
			LOGGER.debug("비정상적인 접근");
			Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "비정상적인 접근으로 거부되었습니다.");
			postMap.put("return_url", "");
			postMap.put("type", "alert");
			postMap.put("title", "알림");
			postMap.put("closebtn", "확인");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			return "redirect:/cti/member/login.do";
		}
		
		//본인인증 후 ㅂㅁ 변경이 필요한지 검증
		LoginVO chkAdmLoginVO = loginService.actionChkAdmLogin(loginVO);
		if (chkAdmLoginVO != null && chkAdmLoginVO.getMBR_ID() != null && !chkAdmLoginVO.getMBR_ID().equals("") 
		&& (chkAdmLoginVO.getMBR_PWD() == null || chkAdmLoginVO.getMBR_PWD().length()==0) ) {//ㅂㅁ가 없는 경우 본인인증 처리
			LOGGER.debug("본인인증 후 ㅂㅁ 변경이 필요한 관리자!");
			request.getSession().setAttribute("isAlertData", true);
			request.getSession().setAttribute("chkAdmLoginVO", chkAdmLoginVO);
        	return "redirect:/adm/member/modifyAdmPwd.do";
		}
		loginVO.setMBR_PWD(EgovFileScrty.encryptPassword(loginVO.getMBR_PWD(), loginVO.getMBR_ID()));
		LoginVO resultVO = loginService.actionLogin(loginVO);
		
    	// 로그인 시도 횟수 검증
        LoginVO retryLoginVO = loginService.retryLogin(loginVO);
        if(retryLoginVO == null){//잘못된 아이디일경우 '에러가 발생했습니다!'화면 나옴 ===== 2020.06.01 이수인팀장님 지시로 추가
        	return "redirect:/cti/member/login.do";
        }
	        //관리자 계정 여부 확인
	  		if (retryLoginVO.getMBR_LV_ID().equals("10")) {
	    			LOGGER.debug("접근 할 권한이 없습니다.");
	    			Map<String, Object> postMap = new HashMap<String,Object>();
	  			postMap.put("message", "비정상적인 접근으로 거부 되었습니다.");
	  			redirectAttributes.addFlashAttribute("alert_data",postMap);
	          	return "redirect:/index.do";
	    	}
	  		//  
        int retryCnt = 0;
        try {
        	retryCnt = Integer.parseInt(retryLoginVO.getMBR_RETRY_LOGIN_CNT());
        } catch(Exception e) { 
        	LOGGER.debug("존재하지 않는 사용자 정보");
        	retryCnt = 0;
        }
	    LOGGER.debug("로그인 횟수 : " + retryCnt);
	    long curtime = new Date().getTime();
	    if(retryCnt >= 5) {
        	String retryDt = retryLoginVO.getMBR_RETRY_LOGIN_DT();
        	LOGGER.debug("로그인 시도 시간차 : " + (curtime - mPublicUtils.changeGetTime(retryDt, "yyyy-MM-dd HH:mm:ss")));
        	int locktime = 10*60*1000;
        	if(curtime - mPublicUtils.changeGetTime(retryDt, "yyyy-MM-dd HH:mm:ss") < locktime) {
        		LOGGER.debug("로그인 횟수 5회 초과 관리자 ㅂㅁ 초기화");
        		loginService.actionLoginLockClearPwd(retryLoginVO);
        		Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
	        	postMap.put("message",  "로그인 시도 횟수 초과로 접근이 차단되었습니다.<br>다음 로그인부터 본인인증을 통해 비밀번호를 재설정 해야 사용이 가능합니다.");
	        	postMap.put("closebtn", "N");
				postMap.put("type", "");//alert
				postMap.put("timer", locktime);//0:없어지지않음
				model.addAttribute("alert_data",postMap);
        	}
        }
        // End 로그인 시도 횟수 검증
	    
	    boolean loginPolicyYn = true;
	    if (resultVO != null && resultVO.getMBR_ID() != null && !resultVO.getMBR_ID().equals("") && loginPolicyYn) {
        
	    	// CTI 계정 검증
		    CtiMemberVO ChkCtiMemberInfo = new CtiMemberVO();
		    ChkCtiMemberInfo.setMBR_ID(resultVO.getMBR_ID());
	  		ChkCtiMemberInfo = ctiMemberService.get_cti_staff_info(ChkCtiMemberInfo);
			if(ChkCtiMemberInfo!=null && ChkCtiMemberInfo.getMBR_ID()!=null && ChkCtiMemberInfo.getMBR_ID().length()!=0) {
				
				//이관 된 기존회원으로 본인인증을 위한 회원정보 갱신이 필요한 사용자 검증
	      		if (resultVO.getMBR_PWD_ST().equals("1")) {
	      			LOGGER.debug("ㅂㅁ는 일치하나 본인인증 정보를 수집해야 하는 관리자!");
	      			request.getSession().setAttribute("isAlertData", true);
	    			request.getSession().setAttribute("chkAdmLoginVO", resultVO);
	            	return "redirect:/adm/member/modifyAdmInfo.do";
	      		} 
	        	
	      		request.getSession().setAttribute("crtfcLoginVO", resultVO);
	          	LOGGER.debug("정상");
	  			redirectAttributes.addFlashAttribute("isCheck",true);
	          	return "redirect:/cti/member/login.do";
				
			} else {				
				LOGGER.debug("CTI 접근 권한(계정)이 없음");
				return "redirect:/cti/error/unauth.do";
			}
	    	        	
        } else {
        	        	
        	//로그인 실패 횟수 증가
        	loginService.updateLoginRetry(loginVO);
        	
        	LOGGER.debug("로그인정보 불일치로 로그인 실패로 알림처리");
        	retryCnt++;
        	if(retryCnt > 5) retryCnt = 5;
    		Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("return_url", "");
			postMap.put("title", "");
        	postMap.put("message",  "아이디 또는 비밀번호가 일치하지 않습니다.<br><span class=\"red-600\">(로그인 실패 "+retryCnt+"회)</span><br>로그인 5회 실패시, 해당 계정의 권한이 제한됩니다.");
        	postMap.put("closebtn", "N");
			postMap.put("type", "");//alert
			postMap.put("timer", 0);//0:없어지지않음
			model.addAttribute("alert_data",postMap);
			
        	return "cti/member/login";
        }
    }	
	//관리자(CTI) 로그아웃  ------------------------------------------------
    @RequestMapping(value="/cti/member/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
    	
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
    	
    	StringBuilder log_dscrp = new StringBuilder();
    	StringBuilder tbl_inf = new StringBuilder();
    	StringBuilder tbl_sn = new StringBuilder();
    	log_dscrp.append("[CTI-로그아웃]");
    	if(loginVO!=null) {
    		log_dscrp.append("[이름:"+loginVO.getMBR_NM()+"(아이디:"+loginVO.getMBR_ID()+")]");
    	} else {
    		log_dscrp.append("[이미로그아웃상태]");
    	}   
    	
    	try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(loginVO));
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
    	
    	request.getSession().setAttribute("LoginVO", null);
    	return "forward:/cti/member/login.do";
    }
    
    
	//관리자(CTI) 계정 관리 페이지 ------------------------------------------------
	@RequestMapping(value = "/cti/member/manager.do")
	public String edu_member_cti(@ModelAttribute("CtiMemberVO") CtiMemberVO mCtiMemberVO, 
		SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdmMemberVO AdmMemberVOInfo = new AdmMemberVO();
		AdmMemberVOInfo.setMBR_ID(mCtiMemberVO.getMBR_ID());
		AdmMemberVOInfo = admMemberService.get_adm_member_auth_info(AdmMemberVOInfo);
		model.addAttribute("info",AdmMemberVOInfo);
		
		CtiMemberVO CtiMemberInfo = ctiMemberService.get_cti_staff_info(mCtiMemberVO);
		model.addAttribute("ctiinfo",CtiMemberInfo);
		
		return "cti/member/modal_manager";		
	}
    
    //관리자(CTI) 계정추가 및 권한부여 처리 ------------------------------------------------
  	@RequestMapping(value="/cti/member/write_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_cti_member_write(@ModelAttribute("CtiMemberVO") CtiMemberVO mCtiMemberVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mEduLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		/** End of LOG RECORED (로그기록) 선언부 */
		
		log_dscrp.append("[CTI관리자-계정관리-추가]");
				
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
		
  		JSONObject data = new JSONObject();
		//검증
  		CtiMemberVO ChkCtiMemberInfo = ctiMemberService.get_cti_staff_info(mCtiMemberVO);
  		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(mCtiMemberVO));
  		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(ChkCtiMemberInfo));
		if(ChkCtiMemberInfo!=null && ChkCtiMemberInfo.getMBR_ID()!=null && ChkCtiMemberInfo.getMBR_ID().length()!=0) {
			data.put("error", "1");
			data.put("msg", "이미 추가 된 정보입니다.");			
			log_dscrp.append("[이미 등록된 정보가 있음]");
		} else {
			
			try {
	  			
	  			if(MASTER_MBR_LV_ID.equals("1")) {
	  					  					  				
	  				AdmMemberVO AdmMemberVOInfo = new AdmMemberVO();
	  				AdmMemberVOInfo.setMBR_ID(mCtiMemberVO.getMBR_ID());
	  				AdmMemberVOInfo = admMemberService.get_adm_member_auth_info(AdmMemberVOInfo);
	  				
	  				//CTI 계정 추가
	  				mCtiMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
	  				ctiMemberService.set_cti_staff_reg(mCtiMemberVO);
	  				
	  				//권한 부여
	  				EduMemberVO updEduMemberVO = new EduMemberVO();
	  				updEduMemberVO.setMBR_ID(mCtiMemberVO.getMBR_ID());
	  				updEduMemberVO.setMBR_GRP_4_ST("Y");
	  				eduMemberService.set_edu_member_master_auth_mod(updEduMemberVO);
	  					  				
	  				// 권한 기록 이력 남기기 -----------------------
	  				{						
						//권한명, 권한내용
						String AUTHOR_NM = "CTI";
						String AUTHOR_CN = "전체 교육대상자 정보 입력, 수정, 삭제, 변경 등";
						
						//사용여부 
						String CONFM_TYPE = "권한 부여";
						
						//관리자권한기록
						LogAdmAuthVO logAdmAuthVO = new LogAdmAuthVO();
		  				logAdmAuthVO.setREQST_NM(AdmMemberVOInfo.getMBR_NM());//신청자
	  					//logAdmAuthVO.setREQST_CN();//신청내용
	  					logAdmAuthVO.setCONFM_NM(loginVO.getMBR_NM());//승인자
	  					logAdmAuthVO.setCONFM_CN(mCtiMemberVO.getLOG_UPD_MSG());//승인내용
	  					logAdmAuthVO.setCONFM_TYPE(CONFM_TYPE);//승인구분
	  					logAdmAuthVO.setMBR_MSG("");//사용자수기입력
	  					logAdmAuthVO.setMBR_LV(AdmMemberVOInfo.getMBR_LV_ID());//권한레벨
	  					logAdmAuthVO.setAUTHOR_NM(AUTHOR_NM);//권한명
	  					logAdmAuthVO.setAUTHOR_CN(AUTHOR_CN);//권한내용  					
	  					eduMemberService.set_edu_member_author_log(logAdmAuthVO);
	  				}
  					// End of 권한 기록 이력 남기기 -----------------------
	  				
	  				data.put("error", "0");
					data.put("msg", "처리되었습니다.");
					
					tbl_inf.append("MBR_TB,");
					tbl_sn.append(mCtiMemberVO.getSTAFF_SN()+",");
	  	  			
	  			} else {
	  	  			log_dscrp.append("|마스터권한이아니면권한처리할수없음");
	  	  			
		  	  		data.put("error", "1");
					data.put("msg", "처리 권한이 없습니다.");
					
	  	  		}	  			
	  		
	  		} catch(Exception e) {
	  			LOGGER.debug("[fail process] "+e.toString());			
	  			data.put("error", "1");
	  			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
	  		}
			
		}
		
		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
  	}
  	
  	
  	//관리자(CTI) 계정추가 및 권한부여 처리 ------------------------------------------------
  	@RequestMapping(value="/cti/member/modify_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_cti_member_modify(@ModelAttribute("CtiMemberVO") CtiMemberVO mCtiMemberVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mEduLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		/** End of LOG RECORED (로그기록) 선언부 */
		
		log_dscrp.append("[CTI관리자-계정관리-변경]");
				
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
		
  		JSONObject data = new JSONObject();
		//검증
  		CtiMemberVO ChkCtiMemberInfo = ctiMemberService.get_cti_staff_info(mCtiMemberVO);
  		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(mCtiMemberVO));
  		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(ChkCtiMemberInfo));
		if(ChkCtiMemberInfo!=null && ChkCtiMemberInfo.getMBR_ID()!=null && ChkCtiMemberInfo.getMBR_ID().length()!=0) {
			try {
	  			
	  			if(MASTER_MBR_LV_ID.equals("1")) {
	  					  					  				
	  				AdmMemberVO AdmMemberVOInfo = new AdmMemberVO();
	  				AdmMemberVOInfo.setMBR_ID(mCtiMemberVO.getMBR_ID());
	  				AdmMemberVOInfo = admMemberService.get_adm_member_auth_info(AdmMemberVOInfo);
	  				
	  				//CTI 계정 변경
	  				mCtiMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
	  				ctiMemberService.set_cti_staff_mod(mCtiMemberVO);
	  					  					  				
	  				// 권한 기록 이력 남기기 -----------------------
	  				{						
						//권한명, 권한내용
						String AUTHOR_NM = "CTI";
						String AUTHOR_CN = "전체 교육대상자 정보 입력, 수정, 삭제, 변경 등";
						
						//사용여부 
						String CONFM_TYPE = "권한 변경";
						
						//관리자권한기록
						LogAdmAuthVO logAdmAuthVO = new LogAdmAuthVO();
		  				logAdmAuthVO.setREQST_NM(AdmMemberVOInfo.getMBR_NM());//신청자
	  					//logAdmAuthVO.setREQST_CN();//신청내용
	  					logAdmAuthVO.setCONFM_NM(loginVO.getMBR_NM());//승인자
	  					logAdmAuthVO.setCONFM_CN(mCtiMemberVO.getLOG_UPD_MSG());//승인내용
	  					logAdmAuthVO.setCONFM_TYPE(CONFM_TYPE);//승인구분
	  					logAdmAuthVO.setMBR_MSG("");//사용자수기입력
	  					logAdmAuthVO.setMBR_LV(AdmMemberVOInfo.getMBR_LV_ID());//권한레벨
	  					logAdmAuthVO.setAUTHOR_NM(AUTHOR_NM);//권한명
	  					logAdmAuthVO.setAUTHOR_CN(AUTHOR_CN);//권한내용  					
	  					eduMemberService.set_edu_member_author_log(logAdmAuthVO);
	  				}
  					// End of 권한 기록 이력 남기기 -----------------------
	  				
	  				data.put("error", "0");
					data.put("msg", "처리되었습니다.");
					
					tbl_inf.append("MBR_TB,");
					tbl_sn.append(mCtiMemberVO.getSTAFF_SN()+",");
	  	  			
	  			} else {
	  	  			log_dscrp.append("|마스터권한이아니면권한처리할수없음");
	  	  			
		  	  		data.put("error", "1");
					data.put("msg", "처리 권한이 없습니다.");
					
	  	  		}	  			
	  		
	  		} catch(Exception e) {
	  			LOGGER.debug("[fail process] "+e.toString());			
	  			data.put("error", "1");
	  			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
	  		}
		} else {
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");			
			log_dscrp.append("[존재하지 않는 회원정보를 요청함]");
		}
		
		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
  	}
    
 	//관리자(CTI) 계정삭제 및 권한삭제 처리 
  	@RequestMapping(value="/cti/member/delete_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_cti_member_delete(@ModelAttribute("CtiMemberVO") CtiMemberVO mCtiMemberVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mEduLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		/** End of LOG RECORED (로그기록) 선언부 */
		
		log_dscrp.append("[CTI관리자-계정관리-삭제]");
		
		
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
		
  		JSONObject data = new JSONObject();
		//검증
  		CtiMemberVO ChkCtiMemberInfo = ctiMemberService.get_cti_staff_info(mCtiMemberVO);
  		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(mCtiMemberVO));
  		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(ChkCtiMemberInfo));
		if(ChkCtiMemberInfo.getMBR_ID()==null || ChkCtiMemberInfo.getMBR_ID().length()==0) {
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");			
			log_dscrp.append("[존재하지 않는 회원정보를 요청함]");
		} else {
			
			try {
	  			
	  			if(MASTER_MBR_LV_ID.equals("1")) {
	  				
	  				log_dscrp.append("[실제삭제]");
	  					  				
	  				AdmMemberVO AdmMemberVOInfo = new AdmMemberVO();
	  				AdmMemberVOInfo.setMBR_ID(mCtiMemberVO.getMBR_ID());
	  				AdmMemberVOInfo = admMemberService.get_adm_member_auth_info(AdmMemberVOInfo);
	  				
	  				//CTI 계정 삭제
	  				ctiMemberService.remove_cti_staff(ChkCtiMemberInfo);
	  				
	  				//권한 삭제
	  				EduMemberVO updEduMemberVO = new EduMemberVO();
	  				updEduMemberVO.setMBR_ID(mCtiMemberVO.getMBR_ID());
	  				updEduMemberVO.setMBR_GRP_4_ST("N");
	  				eduMemberService.set_edu_member_master_auth_mod(updEduMemberVO);
	  					  				
	  				// 권한 기록 이력 남기기 -----------------------
	  				{						
						//권한명, 권한내용
						String AUTHOR_NM = "CTI";
						String AUTHOR_CN = "전체 교육대상자 정보 입력, 수정, 삭제, 변경 등";
						
						//사용여부 
						String CONFM_TYPE = "권한 삭제";
					
						//관리자권한기록
						LogAdmAuthVO logAdmAuthVO = new LogAdmAuthVO();
		  				logAdmAuthVO.setREQST_NM(AdmMemberVOInfo.getMBR_NM());//신청자
	  					//logAdmAuthVO.setREQST_CN();//신청내용
	  					logAdmAuthVO.setCONFM_NM(loginVO.getMBR_NM());//승인자
	  					logAdmAuthVO.setCONFM_CN(mCtiMemberVO.getLOG_UPD_MSG());//승인내용
	  					logAdmAuthVO.setCONFM_TYPE(CONFM_TYPE);//승인구분
	  					logAdmAuthVO.setMBR_MSG("");//사용자수기입력
	  					logAdmAuthVO.setMBR_LV(AdmMemberVOInfo.getMBR_LV_ID());//권한레벨
	  					logAdmAuthVO.setAUTHOR_NM(AUTHOR_NM);//권한명
	  					logAdmAuthVO.setAUTHOR_CN(AUTHOR_CN);//권한내용  					
	  					eduMemberService.set_edu_member_author_log(logAdmAuthVO);
	  				}
  					// End of 권한 기록 이력 남기기 -----------------------
	  				
	  				data.put("error", "0");
					data.put("msg", "처리되었습니다.");
					
					tbl_inf.append("MBR_TB,");
					tbl_sn.append(mCtiMemberVO.getSTAFF_SN()+",");
	  	  			
	  			} else {
	  	  			log_dscrp.append("|마스터권한이아니면권한처리할수없음");
	  	  			
		  	  		data.put("error", "1");
					data.put("msg", "처리 권한이 없습니다.");
					
	  	  		}	  			
	  		
	  		} catch(Exception e) {
	  			LOGGER.debug("[fail process] "+e.toString());			
	  			data.put("error", "1");
	  			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
	  		}
			
		}
		
		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
  	}
  	
  	//CTI 메인 회원조회 리스트 처리 로직 
  	@RequestMapping(value = "cti/member/ajax_detail.do", method = RequestMethod.POST)
  	public ModelAndView ajax_cti_member_detail_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		PublicUtils.scanPrintInfoWithParameters(request);  		
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/member/ajax_detail");
  	  		
  		if(eduMemberVO.getIS_JOIN_MBR().equals("N")) {//회원이 아닌 경우 아이디 기준 회원 검색 후 CTI 검색
  			EduMemberVO info = eduMemberService.get_edu_member_info(eduMemberVO); 			
  			mModelAndView.addObject("info",info);
  			
  			List<EduMemberVO> list = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
  			mModelAndView.addObject("list",list);
  			
  			//회원이 존재하지 않는 경우 CTI 정보로 반영함.
  			if(info==null || info.getMBR_ID()==null || info.getMBR_ID().trim().length()==0) {
  				LOGGER.debug("회원이 존재하지 않는 경우 CTI 정보로 반영함");
  				CtiMemberVO cti_info = new CtiMemberVO();
  				cti_info.setCTI_MBR_HP(eduMemberVO.getMBR_HP().replace("-", ""));
  				cti_info = (CtiMemberVO) ctiMemberService.get_cti_mbr_info(cti_info);
				mModelAndView.addObject("cti_info",cti_info);
				
				mModelAndView.addObject("list",null);
  			}
  			
  		} else {
  			EduMemberVO info = eduMemberService.get_edu_member_info(eduMemberVO); 			
  			mModelAndView.addObject("info",info);
  			
  			List<EduMemberVO> list = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
  			mModelAndView.addObject("list",list);
  		}
  		
  		mModelAndView.addObject("CALL_MBR_HP",eduMemberVO.getCALL_MBR_HP());
		mModelAndView.addObject("CUSTOM_UNIQ_KEY",eduMemberVO.getCUSTOM_UNIQ_KEY());
		mModelAndView.addObject("IS_JOIN_MBR",eduMemberVO.getIS_JOIN_MBR());
		
  		return mModelAndView;
  	}
  	
  	
  	//CTI : 회원정보 직접 수정
  	@RequestMapping(value = "/cti/member/ajax_modify.do", method = RequestMethod.POST)
  	public ModelAndView ajax_cti_member_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		  		
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/member/ajax_modify");
  		
  		//mModelAndView.addObject("list",list);
  		
  		return mModelAndView;
  	}
  	
  	
  	//CTI 전용회원(일반) 삭제 처리 
  	@RequestMapping(value="/cti/member/delete_user_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_cti_member_user_delete(@ModelAttribute("CtiMemberVO") CtiMemberVO mCtiMemberVO,
  		HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mEduLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		String LOG_UPD_MSG = "";
		/** End of LOG RECORED (로그기록) 선언부 */
		
		log_dscrp.append("[CTI-전용회원-삭제]");
		
		
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
		
  		JSONObject data = new JSONObject();
		//검증
  		CtiMemberVO ChkCtiMemberInfo = ctiMemberService.get_cti_mbr_info(mCtiMemberVO);
  		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(mCtiMemberVO));
  		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(ChkCtiMemberInfo));
		if(ChkCtiMemberInfo==null || ChkCtiMemberInfo.getCTI_MBR_SN()==null || ChkCtiMemberInfo.getCTI_MBR_SN().length()==0) {
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");			
			log_dscrp.append("[존재하지 않는 회원정보를 요청함]");
		} else {
			
			try {				
				
				LOG_UPD_MSG = mCtiMemberVO.getLOG_UPD_MSG();
				
				log_dscrp.append("[실제삭제]");
	  				
  				//CTI 회원 삭제
  				ctiMemberService.remove_cti_mbr(ChkCtiMemberInfo);
  				
  				data.put("error", "0");
				data.put("msg", "처리되었습니다.");
				
				tbl_inf.append("CTI_MBR_TB,");
				tbl_sn.append(mCtiMemberVO.getCTI_MBR_SN()+",");  	
			
				//CTI LOG 기록
				logRecordService.set_log_cti_data("CTI회원삭제", log_dscrp.toString(), log_msg.toString(), LOG_UPD_MSG, ChkCtiMemberInfo, null, loginVO, request);
				//End of CTI LOG 기록
				
	  		} catch(Exception e) {
	  			LOGGER.debug("[fail process] "+e.toString());			
	  			data.put("error", "1");
	  			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
	  		}
			
		}
		
		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			mEduLogRecordVO.setMBR_MSG(LOG_UPD_MSG);
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
  	}
  	  	
  	//CTI 전용 : 회원정보 수정
  	@RequestMapping(value = "/cti/member/modify_user.do", method = RequestMethod.POST)
  	public ModelAndView ajax_cti_member_modify_user(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
  		@RequestParam(value="PARENT_CUSTOM_UNIQ_KEY",required=false) String PARENT_CUSTOM_UNIQ_KEY,
  		HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		ModelAndView mModelAndView = new ModelAndView();
		EduMemberVO info = null;
		List<EduMemberVO> list_dtl = null;
		String memberScrtyKey = "";
		if(eduMemberVO.getMBR_ID()!=null && eduMemberVO.getMBR_ID().length()!=0) {
			info = eduMemberService.get_edu_member_info(eduMemberVO);
			list_dtl = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
			EduMemberVO targetEduMemberVO = new EduMemberVO();
			targetEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
			memberScrtyKey = EgovFileScrty.security(info.getMBR_ID(), loginVO.getMBR_ID());
			//info.setMBR_SCRTY_KEY(EgovFileScrty.security(info.getMBR_ID(), loginVO.getMBR_ID()));
			LOGGER.debug("memberId : " + eduMemberVO.getMBR_ID());
			LOGGER.debug("memberScrtyKey : " + info.getMBR_SCRTY_KEY());
		}	
		info.setSearchYear(eduMemberVO.getSearchYear());
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_address_cd",list_address_cd);
		}
		//지역 코드 조회 - 시군구
		List<CodeSetVO> list_address_signgu_cd = null;
		if(info.getMBR_SIDO_CD()!=null && info.getMBR_SIDO_CD().length()!=0) {
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID(info.getMBR_SIDO_CD());
	  		list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		mModelAndView.addObject("list_address_signgu_cd",list_address_signgu_cd);
		//
		//회원추가정보구분자
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_target_se_cd",list_target_se_cd);
		}
		//사업자구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00006");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_license_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_license_se_cd",list_license_se_cd);
		}
		//낚시터업구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00007");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_fshlc_work_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_fshlc_work_cd",list_fshlc_work_cd);
		}	
		//직급 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00003");
	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_position_cd",list_position_cd);
		}
		mModelAndView.setViewName("cti/member/ajax_modify_user");	
		mModelAndView.addObject("info",info);
		mModelAndView.addObject("list_dtl",list_dtl);
		
		/*
		if(MHC_SN.length() > 0 && MHC_SN != null){
			mModelAndView.addObject("MHC_SN", MHC_SN);
		}
		*/
  		
		mModelAndView.addObject("MBR_SCRTY_KEY",memberScrtyKey);
		mModelAndView.addObject("PARENT_CUSTOM_UNIQ_KEY",PARENT_CUSTOM_UNIQ_KEY);
		mModelAndView.addObject("CUSTOM_UNIQ_KEY",eduMemberVO.getCUSTOM_UNIQ_KEY());
		
  		return mModelAndView;
  	}
  	
}


