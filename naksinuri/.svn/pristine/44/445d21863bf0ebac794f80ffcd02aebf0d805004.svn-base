package egovframework.seadm.member.web;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.adm.member.service.AdmCntnAuthorIpVO;
import egovframework.adm.member.service.AdmMemberService;
import egovframework.adm.member.service.AdmMemberVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.seadm.member.service.MemberService;
import egovframework.seadm.member.service.MemberVO;
import egovframework.utils.PublicUtils;


@Controller
public class MemberController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	/** LoginService */
	@Resource(name = "loginService")
	private LoginService loginService;
	
	/** memberService */
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** EgovLogService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	@Resource(name = "admMemberService")
	private AdmMemberService admMemberService;

	
	//관리자 로그인 ------------------------------------------------ 
	@RequestMapping(value = "/seadm/member/login.do")
	public String edu_member_login(SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
    	if(loginVO!=null) {
	    	return "redirect:/seadm/index.do";
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
			
			log_dscrp.append("[ seadm 관리자 페이지 " + admCntnAuthorIpVO.getCNTN_AUTHOR_IP() + " 접속 - " + ip + " ]");
			path = "seadm/member/login";
			
			model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid"));
	  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath"));
	  		model.addAttribute("kcbokcert_sitenm",propertiesService.getString("KcbOkCert.siteNm"));
	  		model.addAttribute("kcbokcert_siteurl",propertiesService.getString("KcbOkCert.siteUrl"));
			
		} else {
			log_dscrp.append("[ 허용 ip외 seadm 관리자 페이지 접속시도 - " + ip + " ]");
			LOGGER.debug("허용 ip외 seadm 관리자 페이지 접속시도  : " + ip);
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
  		
  		return "seadm/member/login";
	}

	//관리자 로그인 - 인증  ------------------------------------------------- 
	@RequestMapping(value="/seadm/member/login_act.do")
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
			return "redirect:/seadm/member/login.do";
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
  		//
		loginVO.setMBR_PWD(EgovFileScrty.encryptPassword(loginVO.getMBR_PWD(), loginVO.getMBR_ID()));
		LoginVO resultVO = loginService.actionLogin(loginVO);
		
		// 로그인 시도 횟수 검증
        LoginVO retryLoginVO = loginService.retryLogin(loginVO);
        if(retryLoginVO == null){//잘못된 아이디일경우 '에러가 발생했습니다!'화면 나옴 ===== 2020.06.01 이수인팀장님 지시로 추가
			return "redirect:/seadm/member/login.do";
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
        	LOGGER.debug("[fail process][존재하지않는사용자정보] "+e.toString());
        }
	    LOGGER.debug("로그인 횟수 : " + retryCnt);
	    if(retryCnt >= 5) {
	    	long curtime = new Date().getTime();
        	String retryDt = retryLoginVO.getMBR_RETRY_LOGIN_DT();
        	LOGGER.debug("로그인 시도 시간차 : " + (curtime - mPublicUtils.changeGetTime(retryDt, "yyyy-MM-dd HH:mm:ss")));
        	int locktime = 10*60*1000;
        	if(curtime - mPublicUtils.changeGetTime(retryDt, "yyyy-MM-dd HH:mm:ss") < locktime) {
        		/*
        		LOGGER.debug("로그인 횟수 5회 초과 10분 잠금");
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
	        	postMap.put("message",  "로그인 시도 횟수 초과로 10분간 로그인이 제한됩니다.");
	        	postMap.put("closebtn", "N");
				postMap.put("type", "");//alert
				postMap.put("timer", locktime);//0:없어지지않음
				model.addAttribute("alert_data",postMap);
	        	return "seadm/member/login";
	        	*/
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
	        	return "seadm/member/login";
        	}
        }
        // End 로그인 시도 횟수 검증
		boolean loginPolicyYn = true;
        if (resultVO != null && resultVO.getMBR_ID() != null && !resultVO.getMBR_ID().equals("") && loginPolicyYn) {
        	AdmMemberVO admMemberVO = new AdmMemberVO();
        	admMemberVO.setMBR_ID(loginVO.getMBR_ID());
        	admMemberVO = admMemberService.get_adm_member_auth_info(admMemberVO);
        	
        	// 관리자 로그인시 접속 가능 아이피 기능 START
        	if(admMemberVO.getCONECT_POSBL_IP_USE_YN().equals("Y")){
        		String ip = PublicUtils.getClientIpAddr(request).trim();
        		boolean isPassIp = false;
        		if(admMemberVO.getCONECT_POSBL_IP() != null && !admMemberVO.getCONECT_POSBL_IP().equals("")){
        			if(!admMemberVO.getCONECT_POSBL_IP().contains(ip)){
        				isPassIp = false;
        			} else {
        				isPassIp = true;
        			}
        		}
        		if(!isPassIp){
        			int locktime = 10*60*1000;
        			Map<String, Object> postMap = new HashMap<String,Object>();
        			postMap.put("return_url", "");
        			postMap.put("title", "");
        			postMap.put("message",  "해당 아이피는 접근이 차단되었습니다.<br>자세한 사항은 관리자에게 문의해주세요.");
        			postMap.put("closebtn", "N");
        			postMap.put("type", "");//alert
        			postMap.put("timer", locktime);//0:없어지지않음
        			model.addAttribute("alert_data",postMap);
        			return "seadm/member/login";
        		}
        	}
        	// 관리자 로그인시 접속 가능 아이피 기능 END

        	//이관 된 기존회원으로 본인인증을 위한 회원정보 갱신이 필요한 사용자 검증
      		if (resultVO.getMBR_PWD_ST().equals("1")) {
      			LOGGER.debug("ㅂㅁ는 일치하나 본인인증 정보를 수집해야 하는 관리자!");
      			request.getSession().setAttribute("isAlertData", true);
    			request.getSession().setAttribute("chkAdmLoginVO", resultVO);
            	return "redirect:/adm/member/modifyAdmInfo.do";
      		}
      		
      		//kjw 센터장 본인인증 건너뜀
      		if(resultVO.getMBR_ID().equals("fipa0851")){
        		LOGGER.debug("접속 시간 기록 ");
        		
    			request.getSession().setAttribute("LoginVO", resultVO);
    			// 접속 시간 기록
    			loginVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
    			loginVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
    			loginService.updateLoginHistory(loginVO);
    			//
    			
        		return "redirect:/eduadm/index.do";
        	}
      		

      		//최고관리자 추가 - 계정 본인인증 건너뜀 
      		if(resultVO.getMBR_ID().equals("koy")){
        		LOGGER.debug("접속 시간 기록 ");
        		
    			request.getSession().setAttribute("LoginVO", resultVO);
    			// 접속 시간 기록
    			loginVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
    			loginVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
    			loginService.updateLoginHistory(loginVO);
    			//
    			
        		return "redirect:/eduadm/index.do";
        	}            		
      		      		
      		
      		
      		
      		//테스트 - smu 계정 본인인증 건너뜀 
      		if(resultVO.getMBR_ID().equals("dss427")){
        		LOGGER.debug("접속 시간 기록 ");
        		
    			request.getSession().setAttribute("LoginVO", resultVO);
    			// 접속 시간 기록
    			loginVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
    			loginVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
    			loginService.updateLoginHistory(loginVO);
    			//
    			
        		return "redirect:/eduadm/index.do";
        	}          		

      		request.getSession().setAttribute("crtfcLoginVO", resultVO);
          	LOGGER.debug("정상");
  			redirectAttributes.addFlashAttribute("isCheck",true);
          	return "redirect:/seadm/member/login.do";
          	
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
//			postMap.put("timer", 0);//0:없어지지않음
			model.addAttribute("alert_data",postMap);
			
        	return "seadm/member/login";
        }
        
        
    }	
	//관리자(교육센터) 로그아웃  ------------------------------------------------ 2018.12.28 jhkim
    @RequestMapping(value="/seadm/member/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
    	
    	StringBuilder log_dscrp = new StringBuilder();
    	StringBuilder tbl_inf = new StringBuilder();
    	StringBuilder tbl_sn = new StringBuilder();
    	log_dscrp.append("[낚시누리관리자-로그아웃]");
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
    	//return "forward:/seadm/member/login.do";
    	return "redirect:/seadm/member/login.do";
    }
	
	
	//관리자 회원관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/member/list.do")
	public String memberList(@ModelAttribute("userSearchVO") MemberVO userSearchVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		/** EgovPropertyService */
		userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		userSearchVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		userSearchVO.setMbrlvid(loginVO.getMBR_LV_ID());
		
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
		paginationInfo.setPageSize(userSearchVO.getPageSize());

		userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		model.addAttribute("resultList", memberService.memberList(userSearchVO));
		
		int totCnt = memberService.memberListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
				
		return "seadm/member/list";
	}
	
	
	//관리자 회원관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/member/modify.do")
	public String memberForm(@ModelAttribute("memberVO") MemberVO memberVO, @RequestParam("selectedId") String numId,
			HttpServletRequest request, HttpServletResponse response,  ModelMap model) throws Exception {
		
		memberVO.setMbrsn(numId);
		MemberVO mbr_info= memberService.getmemberInfo(memberVO);
		
		if(mbr_info == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('데이터가 존재하지 않습니다.'); history.back();</script>");
			out.flush();		
		}
		
		model.addAttribute("mbr_info", mbr_info);
				
		return "seadm/member/modify";
	}
	
	//관리자 회원관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/member/modify_act.do")
	public String modifyAction(@ModelAttribute("memberVO") MemberVO memberVO, 
			HttpServletRequest request, HttpServletResponse response,  ModelMap model) throws Exception {
		
		String usr_sn = request.getParameter("usr_sn");	// 회원아이디
		String usr_id = request.getParameter("usr_id");	// 회원아이디
		String usr_name = request.getParameter("usr_name");	// 회원명
		String usr_pwd = request.getParameter("usr_pwd");	// 회원ㅂㅁ
		String usr_pwd_re = request.getParameter("usr_pwd_re");	// 회원ㅂㅁ확인
		String usr_hp1 = request.getParameter("usr_hp1");	// 
		String usr_hp2 = request.getParameter("usr_hp2");	// 
		String usr_hp3 = request.getParameter("usr_hp3");	// 
		String usr_email1 = request.getParameter("usr_email1");	// 
		String usr_email2 = request.getParameter("usr_email2");	// 
		String usr_birth_data = request.getParameter("usr_birth");	// 
		String usr_birth = usr_birth_data.replace("-","");	// 
		String usr_sex = request.getParameter("usr_sex");	// 
		String usr_zipcode = request.getParameter("usr_zipcode");	// 
		String usr_addr1 = request.getParameter("usr_addr1");	// 
		String usr_addr2 = request.getParameter("usr_addr2");	// 
		
		String hopeloca1 = request.getParameter("hopeloca1");	// 희망지역 부산
		String hopeloca2 = request.getParameter("hopeloca2");	// 희망지역 인천
		String hopeloca3 = request.getParameter("hopeloca3");	// 희망지역 울산 
		String hopeloca4 = request.getParameter("hopeloca4");	// 희망지역 경기
		String hopeloca5 = request.getParameter("hopeloca5");	// 희망지역 강원
		String hopeloca6 = request.getParameter("hopeloca6");	// 희망지역 충남
		String hopeloca7 = request.getParameter("hopeloca7");	// 희망지역 전북
		String hopeloca8 = request.getParameter("hopeloca8");	// 희망지역 전남
		String hopeloca9 = request.getParameter("hopeloca9");	// 희망지역 경북
		String hopeloca10 = request.getParameter("hopeloca10");	// 희망지역 경남
		String hopeloca11 = request.getParameter("hopeloca11");	// 희망지역 제주
		String hopeloca12 = request.getParameter("hopeloca12");	// 희망지역 미정

		String hopejob1 = request.getParameter("hopejob1");	// 희망업종 어선어업
		String hopejob2 = request.getParameter("hopejob2");	// 희망업종 낚시어업
		String hopejob3 = request.getParameter("hopejob3");	// 희망업종 양식어업(해면)
		String hopejob4 = request.getParameter("hopejob4");	// 희망업종 양식어업(내수면)
		String hopejob5 = request.getParameter("hopejob5");	// 희망업종 어촌비지니스
		String hopejob6 = request.getParameter("hopejob6");	// 희망업종 수산물가공,유통
		String hopejob7 = request.getParameter("hopejob7");	// 희망업종 소금산업
		String hopejob8 = request.getParameter("hopejob8");	// 희망업종 기타(미정)
		
		//필수값체크
		if(usr_pwd.length()>0 && !usr_pwd.equals(usr_pwd_re)) {
			//ㅂㅁ 검증
			Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9]).{10,})");
			Matcher matcher = pattern.matcher(usr_pwd);
			if(!matcher.matches()) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('비밀번호는 숫자와 영문자 조합으로 10자리 이상 사용해야 합니다.');");
				writer.println("history.back();");
				writer.println("</script>");
				writer.flush();
			}
			//
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호와 비밀번호확인의 값이 서로 다릅니다.'); history.back(); </script>");
			out.flush();			
		}
		if(StringUtils.isEmpty(usr_hp2) || StringUtils.isEmpty(usr_hp3) ) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('연락처가 정상적으로 입력되지 않았습니다.'); history.back(); </script>");
			out.flush();			
		}		
		if(StringUtils.isEmpty(usr_email1) || StringUtils.isEmpty(usr_email2) ) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이메일주소가 정상적으로 입력되지 않았습니다.'); history.back(); </script>");
			out.flush();			
		}		
		
		//값 조합
		String usr_hp = usr_hp1+usr_hp2+usr_hp3;
		String usr_email = usr_email1+"@"+usr_email2;
		String hopelocate = "";
		if(!StringUtils.isEmpty(hopeloca1)) { hopelocate += hopeloca1+";"; }
		if(!StringUtils.isEmpty(hopeloca2)) { hopelocate += hopeloca2+";"; }
		if(!StringUtils.isEmpty(hopeloca3)) { hopelocate += hopeloca3+";"; }
		if(!StringUtils.isEmpty(hopeloca4)) { hopelocate += hopeloca4+";"; }
		if(!StringUtils.isEmpty(hopeloca5)) { hopelocate += hopeloca5+";"; }
		if(!StringUtils.isEmpty(hopeloca6)) { hopelocate += hopeloca6+";"; }
		if(!StringUtils.isEmpty(hopeloca7)) { hopelocate += hopeloca7+";"; }
		if(!StringUtils.isEmpty(hopeloca8)) { hopelocate += hopeloca8+";"; }
		if(!StringUtils.isEmpty(hopeloca9)) { hopelocate += hopeloca9+";"; }
		if(!StringUtils.isEmpty(hopeloca10)) { hopelocate += hopeloca10+";"; }
		if(!StringUtils.isEmpty(hopeloca11)) { hopelocate += hopeloca11+";"; }
		if(!StringUtils.isEmpty(hopeloca12)) { hopelocate += hopeloca12+";"; }
		
		String hopejob= "";
		if(!StringUtils.isEmpty(hopejob1)) { hopejob += hopejob1+";"; }
		if(!StringUtils.isEmpty(hopejob2)) { hopejob += hopejob2+";"; }
		if(!StringUtils.isEmpty(hopejob3)) { hopejob += hopejob3+";"; }
		if(!StringUtils.isEmpty(hopejob4)) { hopejob += hopejob4+";"; }
		if(!StringUtils.isEmpty(hopejob5)) { hopejob += hopejob5+";"; }
		if(!StringUtils.isEmpty(hopejob6)) { hopejob += hopejob6+";"; }
		if(!StringUtils.isEmpty(hopejob7)) { hopejob += hopejob7+";"; }
		if(!StringUtils.isEmpty(hopejob8)) { hopejob += hopejob8+";"; }
		
		//값 생성
		memberVO.setMbrsn(usr_sn);
		memberVO.setMbrid(usr_id);
		memberVO.setMbrnm(usr_name);
		if(usr_pwd.length()>0) {
			memberVO.setMbrpwd(EgovFileScrty.encryptPassword(usr_pwd, usr_id));
			//memberVO.setMbrpwd(usr_pwd);
		}
		memberVO.setMbrhp(usr_hp);
		memberVO.setMbremail(usr_email);
		memberVO.setMbraddr1(usr_addr1);
		memberVO.setMbraddr2(usr_addr2);
		memberVO.setMbrzipcd(usr_zipcode);
		memberVO.setMbrtypecd("2");
		memberVO.setMbrbirth(usr_birth);
		memberVO.setMbrsex(usr_sex);
		memberVO.setMbrzipcd(usr_zipcode);
		memberVO.setMbraddr1(usr_addr1);
		memberVO.setMbraddr2(usr_addr2);
		memberVO.setMbrhopezone(hopelocate);
		memberVO.setMbrhopebusiness(hopejob);
		memberVO.setMbrlastconipaddr(request.getRemoteAddr());
		
		memberService.memberInfoUpdate(memberVO);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//out.println("<script>alert('수정되었습니다.'); location.replace('/seadm/member/form.do?sn="+usr_sn+"');</script>");
		out.println("<script>alert('수정되었습니다.'); location.replace('/seadm/member/list.do');</script>");
		out.flush();			
		
		return null;
	}
	
	//관리자 회원관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/member/delete_act.do")
	public String deleteAction(@ModelAttribute("memberVO") MemberVO memberVO, @RequestParam("selectedId") String numId,
			HttpServletRequest request, HttpServletResponse response,  ModelMap model) throws Exception {
		
		if(numId == null || numId.trim().equals("") || numId.length()==0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('데이터가 존재하지 않습니다.'); history.back();</script>");
			out.flush();		
		}
		
		memberVO.setMbrsn(numId);
		memberService.memberInfoDelete(memberVO);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('삭제되었습니다.'); location.replace('/seadm/member/list.do');</script>");
		out.flush();			
		
		return null;
	}
	
	
	//관리자(종합센터) 권한관리 페이지 ------------------------------------------------
	@RequestMapping(value = "/seadm/member/list_lv.do")
	public String memberListLevel(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		eduMemberVO.setNotUsedPagination(true);
		
		//등급 그룹 조회
		eduMemberVO.setMBR_GRP_ID("");
		eduMemberVO.setMBR_POSITION_CD("");
		eduMemberVO.setMBR_GRP_1_ST("");
		eduMemberVO.setMBR_GRP_2_ST("");
		eduMemberVO.setMBR_GRP_3_ST("");
		eduMemberVO.setMBR_GRP_4_ST("");
				
		eduMemberVO.setMBR_LV_ID("2");
		List<EduMemberVO> list_lv2 = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("3");
		List<EduMemberVO> list_lv3 = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("4");
		List<EduMemberVO> list_lv4 = memberService.get_seadm_member_auth_list(eduMemberVO);
		model.addAttribute("list_lv2",list_lv2);
		model.addAttribute("list_lv3",list_lv3);
		model.addAttribute("list_lv4",list_lv4);
		
		//종합센터
		eduMemberVO.setMBR_GRP_ID("1");
		eduMemberVO.setMBR_POSITION_CD("");
		eduMemberVO.setMBR_GRP_1_ST("Y");
		eduMemberVO.setMBR_GRP_2_ST("");
		eduMemberVO.setMBR_GRP_3_ST("");
		eduMemberVO.setMBR_GRP_4_ST("");
		
		eduMemberVO.setMBR_LV_ID("2");
		List<EduMemberVO> list_lv2_g1_center = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("3");
		List<EduMemberVO> list_lv3_g1_manager = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("4");
		eduMemberVO.setMBR_POSITION_CD("1");
		List<EduMemberVO> list_lv4_g1_homestay = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_POSITION_CD("2");
		List<EduMemberVO> list_lv4_g1_doctor = memberService.get_seadm_member_auth_list(eduMemberVO);
		model.addAttribute("list_lv2_g1_center",list_lv2_g1_center);
		model.addAttribute("list_lv3_g1_manager",list_lv3_g1_manager);
		model.addAttribute("list_lv4_g1_homestay",list_lv4_g1_homestay);
		model.addAttribute("list_lv4_g1_doctor",list_lv4_g1_doctor);
		
		//박람회
		eduMemberVO.setMBR_GRP_ID("3");
		eduMemberVO.setMBR_POSITION_CD("");
		eduMemberVO.setMBR_GRP_1_ST("");
		eduMemberVO.setMBR_GRP_2_ST("");
		eduMemberVO.setMBR_GRP_3_ST("Y");
		eduMemberVO.setMBR_GRP_4_ST("");
		
		eduMemberVO.setMBR_LV_ID("2");
		List<EduMemberVO> list_lv2_g3_center = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("3");
		List<EduMemberVO> list_lv3_g3_manager = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("4");
		List<EduMemberVO> list_lv4_g3_outsider = memberService.get_seadm_member_auth_list(eduMemberVO);
		model.addAttribute("list_lv2_g3_center",list_lv2_g3_center);
		model.addAttribute("list_lv3_g3_manager",list_lv3_g3_manager);
		model.addAttribute("list_lv4_g3_outsider",list_lv4_g3_outsider);
		
		//교육센터
		eduMemberVO.setMBR_GRP_ID("2");
		eduMemberVO.setMBR_POSITION_CD("");
		eduMemberVO.setMBR_GRP_1_ST("");
		eduMemberVO.setMBR_GRP_2_ST("Y");
		eduMemberVO.setMBR_GRP_3_ST("");
		eduMemberVO.setMBR_GRP_4_ST("");
		
		eduMemberVO.setMBR_LV_ID("2");
		List<EduMemberVO> list_lv2_g2_center = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("3");
		List<EduMemberVO> list_lv3_g2_manager = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("4");
		List<EduMemberVO> list_lv4_g2_outsider = memberService.get_seadm_member_auth_list(eduMemberVO);
		model.addAttribute("list_lv2_g2_center",list_lv2_g2_center);
		model.addAttribute("list_lv3_g2_manager",list_lv3_g2_manager);
		model.addAttribute("list_lv4_g2_outsider",list_lv4_g2_outsider);
		
		//CTI
		eduMemberVO.setMBR_GRP_ID("4");
		eduMemberVO.setMBR_POSITION_CD("");
		eduMemberVO.setMBR_GRP_1_ST("");
		eduMemberVO.setMBR_GRP_2_ST("");
		eduMemberVO.setMBR_GRP_3_ST("");
		eduMemberVO.setMBR_GRP_4_ST("Y");
		
		eduMemberVO.setMBR_LV_ID("2");
		List<EduMemberVO> list_lv2_g4_center = memberService.get_seadm_member_auth_list(eduMemberVO);
		eduMemberVO.setMBR_LV_ID("3");
		List<EduMemberVO> list_lv3_g4_manager = memberService.get_seadm_member_auth_list(eduMemberVO);
		model.addAttribute("list_lv2_g4_center",list_lv2_g4_center);
		model.addAttribute("list_lv3_g4_manager",list_lv3_g4_manager);
		
		return "seadm/member/list_lv";
	}
	
	//관리자(종합센터) 권한관리 - 회원 추가 ------------------------------------------------
	@RequestMapping(value = "/seadm/member/auth/add.do", method = RequestMethod.POST)
	public ModelAndView seadm_member_auth_add(@RequestParam("modal_title") String modal_title,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("seadm/member/member_add");
		mModelAndView.addObject("MBR_LV_ID",eduMemberVO.getMBR_LV_ID());
		mModelAndView.addObject("MBR_GRP_ID",eduMemberVO.getMBR_GRP_ID());
		mModelAndView.addObject("MBR_POSITION_CD",eduMemberVO.getMBR_POSITION_CD());
		mModelAndView.addObject("modal_title",modal_title);
		return mModelAndView;
	}
	
	//관리자(종합센터) 권한관리 - 회원권한삭제 ------------------------------------------------
	@RequestMapping(value = "/seadm/member/auth/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String seadm_member_auth_delete_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();			
		
		log_dscrp.append("[종합센터관리자-권한관리-회원권한삭제]");
		
		try {						
			log_msg.append("[MBR_ID:"+eduMemberVO.getMBR_ID());
			tbl_inf.append("MBR_TB");
			tbl_sn.append(eduMemberVO.getMBR_SN());
			
			EduMemberVO chkEduMemberVO = new EduMemberVO();
			chkEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
			chkEduMemberVO = memberService.get_seadm_member_auth_info(chkEduMemberVO);
			if(chkEduMemberVO==null || chkEduMemberVO.getMBR_ID()==null || chkEduMemberVO.getMBR_ID().length()==0) {
				log_dscrp.append("[존재하지않는회원(아이디:"+eduMemberVO.getMBR_ID()+")]");
				data.put("error", "1");
				data.put("msg", "존재하지 않는 회원입니다.");	
			} else {
				log_dscrp.append("[이름:"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+")]");
				if(Integer.parseInt(chkEduMemberVO.getMBR_LV_ID()) <= Integer.parseInt(loginVO.getMBR_LV_ID()) && !loginVO.getMBR_ID().equals("rhadmin")) {
					data.put("error", "1");
					data.put("msg", "나와 같은 등급이거나 높은 등급의 회원을 변경할 권한이 없습니다.");	
					log_dscrp.append("[나와 같은 등급이거나 높은 등급의 회원을 변경할 권한이 없습니다.]");
				} else {
					log_dscrp.append("[");
					String MBR_GRP_ID = eduMemberVO.getMBR_GRP_ID();
					if(MBR_GRP_ID.equals("1")) { //종합센터
						chkEduMemberVO.setMBR_GRP_1_ST("N");
						//log_dscrp.append("낚시누리");
					} else if(MBR_GRP_ID.equals("2")) { //교육센터
						chkEduMemberVO.setMBR_GRP_2_ST("N");
						//log_dscrp.append("낚시전문교육");
					} else if(MBR_GRP_ID.equals("3")) { //박람회
						chkEduMemberVO.setMBR_GRP_3_ST("N");
						//log_dscrp.append("3");
					} else if(MBR_GRP_ID.equals("4")) { //CTI
						chkEduMemberVO.setMBR_GRP_4_ST("N");
						//log_dscrp.append("4");
					} else {
						chkEduMemberVO.setMBR_LV_ID("10");
						chkEduMemberVO.setMBR_GRP_ID("0");
						chkEduMemberVO.setMBR_POSITION_CD("0");
						chkEduMemberVO.setMBR_GRP_1_ST("N");
						chkEduMemberVO.setMBR_GRP_2_ST("N");
						chkEduMemberVO.setMBR_GRP_3_ST("N");
						chkEduMemberVO.setMBR_GRP_4_ST("N");	
					}
					log_msg.append(",(기존)MBR_LV ID:"+chkEduMemberVO.getMBR_LV_ID()+
							",MBR_POSITION_CD"+chkEduMemberVO.getMBR_POSITION_CD()+
							",MBR_GRP_1_ST"+chkEduMemberVO.getMBR_GRP_1_ST()+
							",MBR_GRP_2_ST"+chkEduMemberVO.getMBR_GRP_2_ST()+
							",MBR_GRP_3_ST"+chkEduMemberVO.getMBR_GRP_3_ST()+
							",MBR_GRP_4_ST"+chkEduMemberVO.getMBR_GRP_4_ST()
							);
					log_msg.append("=>(변경)MBR_LV ID:"+eduMemberVO.getMBR_LV_ID()+
							",MBR_POSITION_CD"+chkEduMemberVO.getMBR_POSITION_CD()+
							",MBR_GRP_1_ST"+chkEduMemberVO.getMBR_GRP_1_ST()+
							",MBR_GRP_2_ST"+chkEduMemberVO.getMBR_GRP_2_ST()+
							",MBR_GRP_3_ST"+chkEduMemberVO.getMBR_GRP_3_ST()+
							",MBR_GRP_4_ST"+chkEduMemberVO.getMBR_GRP_4_ST()+"]"
							);
					memberService.set_seadm_member_auth_mod(chkEduMemberVO);
					
					data.put("error", "0");
					data.put("msg", "처리되었습니다.");	
					
				}
			}
			
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[처리에러발생("+e.toString()+")");
			log_dscrp.append("[처리에러발생]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
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
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;		
	}
	
	//관리자(종합센터) 권한관리 - 회원권한추가 - 회원 목록 가져오기 ------------------------------------------------
	@RequestMapping(value = "/seadm/member/util/ajaxlist.do", method = RequestMethod.POST)
	public ModelAndView ajax_member_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		eduMemberVO.setPageUnit(10);//한번에 10명만 노출
		
		List<EduMemberVO> list = null;
		String returnUrl = "seadm/member/member_ajax_list";
				
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduMemberVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduMemberVO.getPageUnit());
		paginationInfo.setPageSize(eduMemberVO.getPageSize());

		eduMemberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduMemberVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduMemberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
					
		eduMemberVO.setMBR_ST("Y");
		list = memberService.get_seadm_member_auth_list(eduMemberVO);			
		int totCnt = memberService.get_seadm_member_auth_list_totcnt(eduMemberVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		if(Integer.parseInt(eduMemberVO.getMBR_LV_ID()) <= Integer.parseInt(loginVO.getMBR_LV_ID()) && !loginVO.getMBR_ID().equals("rhadmin")) {
			//같거나 높은 등급은 추가할 권한이 없음
			list = null;
			paginationInfo.setTotalRecordCount(0);
		}
		
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName(returnUrl);
		mModelAndView.addObject("paginationInfo", paginationInfo);
		mModelAndView.addObject("list",list);
		mModelAndView.addObject("mbrids",eduMemberVO.getMBR_ID());
		
		return mModelAndView;
	}
	
	//관리자(종합센터) 권한관리 - 회원권한추가 - 처리 로직 ------------------------------------------------
	@RequestMapping(value="/seadm/member/auth/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_auth_write(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[종합센터관리자-권한관리-회원권한추가]");
		try {
			
			int successcnt = 0;
			int failcnt = 0;
			String MBR_LV_ID = eduMemberVO.getMBR_LV_ID();
			String MBR_GRP_ID = eduMemberVO.getMBR_GRP_ID();
			String MBR_POSITION_CD = eduMemberVO.getMBR_POSITION_CD();
			log_msg.append("[처리할권한:"+MBR_GRP_ID+"]");
			log_msg.append("[처리할레벨:"+MBR_LV_ID+"]");
			log_msg.append("[처리할직급:"+MBR_POSITION_CD+"]");
			
			String[] mbrids = eduMemberVO.getMBR_ID().replaceAll("\\s","").split(",");
			for(int i=0; i<mbrids.length; i++) {
				String traget_mbr_id = mbrids[i];
				LOGGER.debug("현재 회원 처리 중 : " + traget_mbr_id);
				EduMemberVO chkEduMemberVO = new EduMemberVO();
				chkEduMemberVO.setMBR_ID(traget_mbr_id);
				chkEduMemberVO = memberService.get_seadm_member_auth_info(chkEduMemberVO);
				log_msg.append("[MBR_ID:"+traget_mbr_id);
				if(chkEduMemberVO==null || chkEduMemberVO.getMBR_ID()==null || chkEduMemberVO.getMBR_ID().length()==0) {
					log_msg.append(",변경실패(존재하지않는회원)]");
					log_dscrp.append("|존재하지않는회원(아이디:"+traget_mbr_id+")");
					failcnt++;
				} else {
					log_dscrp.append("|"+chkEduMemberVO.getMBR_NM()+"(아이디:"+chkEduMemberVO.getMBR_ID()+")");
					chkEduMemberVO.setMBR_LV_ID(MBR_LV_ID);
					chkEduMemberVO.setMBR_GRP_ID(MBR_GRP_ID);
					chkEduMemberVO.setMBR_POSITION_CD(MBR_POSITION_CD);
					if(MBR_GRP_ID.equals("1")) { //종합센터
						chkEduMemberVO.setMBR_GRP_1_ST("Y");
					} else if(MBR_GRP_ID.equals("2")) { //교육센터
						chkEduMemberVO.setMBR_GRP_2_ST("Y");
					} else if(MBR_GRP_ID.equals("3")) { //박람회
						chkEduMemberVO.setMBR_GRP_3_ST("Y");
					} else if(MBR_GRP_ID.equals("4")) { //CTI
						chkEduMemberVO.setMBR_GRP_4_ST("Y");
					} 
					log_msg.append(",(기존)MBR_LV ID:"+chkEduMemberVO.getMBR_LV_ID()+
							",MBR_POSITION_CD"+chkEduMemberVO.getMBR_POSITION_CD()+
							",MBR_GRP_1_ST"+chkEduMemberVO.getMBR_GRP_1_ST()+
							",MBR_GRP_2_ST"+chkEduMemberVO.getMBR_GRP_2_ST()+
							",MBR_GRP_3_ST"+chkEduMemberVO.getMBR_GRP_3_ST()+
							",MBR_GRP_4_ST"+chkEduMemberVO.getMBR_GRP_4_ST()
							);
					log_msg.append("=>(변경)MBR_LV ID:"+eduMemberVO.getMBR_LV_ID()+
							",MBR_POSITION_CD"+chkEduMemberVO.getMBR_POSITION_CD()+
							",MBR_GRP_1_ST"+chkEduMemberVO.getMBR_GRP_1_ST()+
							",MBR_GRP_2_ST"+chkEduMemberVO.getMBR_GRP_2_ST()+
							",MBR_GRP_3_ST"+chkEduMemberVO.getMBR_GRP_3_ST()+
							",MBR_GRP_4_ST"+chkEduMemberVO.getMBR_GRP_4_ST()+"]"
							);
					successcnt++;
					memberService.set_seadm_member_auth_mod(chkEduMemberVO);
				}
			}
			log_dscrp.append("[결과-성공:"+successcnt+"건,실패:"+failcnt+"건]");
			
			data.put("error", "0");
			data.put("msg", "추가되었습니다.");		
			
			tbl_inf.append("MBR_TB");
			
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
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
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	
}


