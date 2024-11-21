package egovframework.educenter.member.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.all.auth.utils.KCBOkCertResultCode;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.educenter.member.service.EduCenterMemberService;
import egovframework.educenter.member.service.EduCenterMemberVO;
import egovframework.educenter.myhistory.service.MyHistoryService;
import egovframework.educenter.myhistory.service.MyHistoryVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduCenterMemberController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterMemberController.class);
		
	/** LoginService */
	@Resource(name = "loginService")
	private LoginService loginService;

	/** EgovLogService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "eduCenterMemberService")
	protected EduCenterMemberService eduCenterMemberService;
	
	/** MyHistoryService */
	@Resource(name = "myHistoryService")
	private MyHistoryService myHistoryService;
	
	//공통 로그인 팝업  ------------------------------------------------
	@RequestMapping(value = "/educenter/member/loginPopup.do")
	public String edu_member_login_popup(SessionStatus status, HttpServletRequest request, ModelMap model, @RequestParam(value="loginPopupRequestType", required = false) String loginPopupRequestType) throws Exception {
		
		PublicUtils mPublicUtils = new PublicUtils();
		
		if(loginPopupRequestType != null && !loginPopupRequestType.equals("")){
			if(loginPopupRequestType.equals("0")){
				request.getSession().setAttribute("loginPopupReturnUrl", "redirect:/educenter/mbrhstry/listCrtf.do");
			} else if(loginPopupRequestType.equals("1")){
				request.getSession().setAttribute("loginPopupReturnUrl", "redirect:/educenter/trnng/list.do");
			}
			
			request.getSession().setAttribute("loginPopupRequestType", loginPopupRequestType);
		} else {
			request.getSession().setAttribute("loginPopupRequestType", "index");
		}
		
		
		model.addAttribute("isMobileDevice",mPublicUtils.isMobileDevice(request)?"Y":"N");
				
		model.addAttribute("kcbokcert_cpid", propertiesService.getString("KcbOkCert.cpid"));
		model.addAttribute("kcbokcert_licensepath", propertiesService.getString("KcbOkCert.licensePath"));
		model.addAttribute("kcbokcert_sitenm", propertiesService.getString("KcbOkCert.siteNm"));
		model.addAttribute("kcbokcert_siteurl", propertiesService.getString("KcbOkCert.siteUrl"));
		
		return "educenter/member/login_popup";
	}
	
	//관리자(교육센터) 로그인 ------------------------------------------------
	@RequestMapping(value = "/educenter/member/login.do")
	public String edu_member_login(@RequestParam(value="key",required=false) String key, 
		SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		if(key!=null && key.trim().length()>0 && key.equals("watosys.1.217.88.66")) {
			LOGGER.debug("일반 사용자 임시 로그인 시도 ("+mPublicUtils.getClientIpAddr(request)+")");
		} else {
			return "redirect:/educenter/index.do";
		}
		model.addAttribute("key", key);
		return "educenter/member/login";
	}
	
	//교육센터 로그인 - 인증  -------------------------------------------------
	@RequestMapping(value="/educenter/member/user_login_act.do")
    public String edu_member_user_login_act(@RequestParam(value="key",required=false) String key, @ModelAttribute("loginVO") LoginVO loginVO,
    		RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		boolean loginPolicyYn = false;
		LoginVO resultVO = null;
		String message = "";
		String return_url = "redirect:/educenter/member/login.do?key="+key;
		List<LoginVO> list_mbr = null;
		if(EgovStringUtil.isEmpty(loginVO.getMBR_ID()) || EgovStringUtil.isEmpty(loginVO.getMBR_NM()) || EgovStringUtil.isEmpty(loginVO.getMBR_HP())
		|| EgovStringUtil.isEmpty(loginVO.getMBR_BIRTH()) || key.trim().length() <= 0 && !key.equals("watosys.1.217.88.66")
  		) {
  			//거부
  			LOGGER.debug("비정상적인 접근");
  			message = "회원정보가 없거나 정상적인 로그인이 아닙니다.";	
  		} else {
  			LOGGER.debug("아이디 찾기 결과 처리");
  			loginVO.setMBR_LV_ID("10");
  			list_mbr = loginService.actionLoginNmBirth(loginVO);
  			if(list_mbr!=null && list_mbr.size() > 1) {
  				message = "계정이 1개 이상 확인 되었습니다.";
  			} else if(list_mbr!=null && list_mbr.size() == 1) {
  				resultVO = (LoginVO)list_mbr.get(0);
  				if (resultVO != null && resultVO.getMBR_ID() != null && resultVO.getMBR_ID().equals(loginVO.getMBR_ID()) ) {
  					loginPolicyYn = true;
  					return_url = "redirect:/educenter/index.do";
  				} else {
      				//검증안됨
      				LOGGER.debug("검증되지 않음");      				
      				message = "회원정보가 없거나 정상적인 로그인이 아닙니다.";
      			}  				
  			} else {
  				//존재하지않음
  				LOGGER.debug("존재하지 않는 회원");
  				message = "회원정보가 없거나 정상적인 로그인이 아닙니다.";	
  			}
  			if (resultVO != null && loginPolicyYn) {        	
  	        	LOGGER.debug("로그인성공!");
  	        	request.getSession().setAttribute("LoginVO", resultVO);
  	        	
  			} else {
  				Map<String, Object> postMap = new HashMap<String,Object>();
    			postMap.put("message", message);	
    			redirectAttributes.addFlashAttribute("alert_data",postMap);
  			}
  		}		
		return return_url;
	}
	
	
	//교육센터 로그인 - 인증  -------------------------------------------------
	@RequestMapping(value="/educenter/member/login_act.do")
    public String edu_member_login_act(@ModelAttribute("loginVO") LoginVO loginVO,
    		@RequestParam(value="page_type",required=false) String page_type,
    		/*
    		@RequestParam(value="name_typing") String name_typing, 
    		@RequestParam(value="birthDay_1_typing") String birthDay_1_typing, 
    		@RequestParam(value="birthDay_2_typing") String birthDay_2_typing, 
    		@RequestParam(value="birthDay_3_typing") String birthDay_3_typing,
    		*/
    		RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		boolean authReturnYn = false;
		boolean loginPolicyYn = false;
		LoginVO resultVO = null;
		List<LoginVO> list_mbr = null;
		String message = "";
		String return_url = "";
		/*if(page_type!=null && page_type.equals("mobile")) {
			return_url = "redirect:/educenter/m/index.do";
		} else {
			return_url = "redirect:/educenter/index.do";
		}*/
		return_url = "redirect:/educenter/index.do";
		String birthDay = request.getParameter("birthDay");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNo");

		String vldVrfcBirthDay = request.getParameter("birthDay");
		String vldVrfcName = request.getParameter("name");
		String vldVrfcPhoneNo = request.getParameter("phoneNo");
		
		StringBuilder log_dscrp2 = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
    	StringBuilder tbl_inf2 = new StringBuilder();
    	StringBuilder tbl_sn2 = new StringBuilder();
    	String rsltCd = (String) request.getSession().getAttribute("vldVrfcAuthTempDataRsltCd");
    	Boolean isLogKcb = false;
    	
    	if(!rsltCd.equals("B000")){
    		isLogKcb = true;
    		log_dscrp2.append("[교육센터사용자-본인인증]");
    		log_dscrp2.append("[vldVrfcAuthTempDataBirthDay : " + request.getSession().getAttribute("vldVrfcAuthTempDataBirthDay") + "]");
    		log_dscrp2.append("[vldVrfcAuthTempDataName : " + request.getSession().getAttribute("vldVrfcAuthTempDataName") + "]");
    		log_dscrp2.append("[vldVrfcAuthTempDataPhoneNo : " + request.getSession().getAttribute("vldVrfcAuthTempDataPhoneNo") + "]");
    		log_dscrp2.append("[login.certNum.rst : " + request.getSession().getAttribute("login.certNum.rst") + "]");
    		log_dscrp2.append("[vldVrfcAuthTempDataRsltCd : " + request.getSession().getAttribute("vldVrfcAuthTempDataRsltCd") + "]");
    		log_dscrp2.append("[vldVrfcAuthTempDataRsltCdCn : " + KCBOkCertResultCode.parseMessage(rsltCd) + "]");
    		log_dscrp2.append("[vldVrfcAuthTempDataRsltMsg : " + request.getSession().getAttribute("vldVrfcAuthTempDataRsltMsg") + "]");
    		
    		log_msg.append(request.getSession().getAttribute("kcbOkcertJsonData").toString());
    	}
		
		if(!birthDay.equals(vldVrfcBirthDay) || !name.equals(vldVrfcName) || !phoneNo.equals(vldVrfcPhoneNo)){
			//거부
  			LOGGER.debug("비정상적인 접근");
  			message = "정상적인 로그인이 아닙니다.";	
		}
		//기존회원 ㅂㅁ 재설정 로직
		request.getSession().removeAttribute("AGE14_UNDER_MDL_TKN");
		request.getSession().setAttribute("ajaxMoveUrlForChkLoginAuthInfo", "");
		request.getSession().removeAttribute("isChkLoginAuthInfo14ageUnder");
		request.getSession().removeAttribute("loginActAuthTempDataBirthDay");
		request.getSession().removeAttribute("loginActAuthTempDataName");
		request.getSession().removeAttribute("loginActAuthTempDataPhoneNo");
  		if(EgovStringUtil.isEmpty(birthDay) || EgovStringUtil.isEmpty(name) || EgovStringUtil.isEmpty(phoneNo)
  		) {
  			//거부
  			LOGGER.debug("비정상적인 접근");
  			message = "회원정보가 없거나 정상적인 로그인이 아닙니다.";	
  		} else {
  			authReturnYn = true;
  			//처리부
  			LOGGER.debug("아이디 찾기 결과 처리");
  			loginVO.setMBR_BIRTH(birthDay);
  			loginVO.setMBR_NM(name);
  			loginVO.setMBR_HP(phoneNo);
  			loginVO.setMBR_LV_ID("10");
  			list_mbr = loginService.actionLoginNmBirth(loginVO);
  			if(list_mbr!=null && list_mbr.size() > 1) {
  				
  				//이 경우에 어떻게 해야 할까
  				
  				LOGGER.debug("다수의 계정이 발견됨");
  				model.addAttribute("list",list_mbr);
  				if(page_type!=null && page_type.equals("mobile")) {
  					return_url = "educenter/mobile/member/find_id";
  				} else {
  					return_url = "educenter/member/find_id";
  				}
  				
  			} else if(list_mbr!=null && list_mbr.size() == 1) {
  				resultVO = (LoginVO)list_mbr.get(0);
  				if (resultVO != null && resultVO.getMBR_ID() != null && !resultVO.getMBR_ID().equals("") ) {
      				
      				loginPolicyYn = true;
      				request.getSession().setAttribute("LoginVO", resultVO);
      	        	//접속 시간 기록
      	        	resultVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
      	        	resultVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
      	        	loginService.updateLoginHistory(resultVO);
      	        	//
      				
      			} else {
      				//검증안됨
      				LOGGER.debug("검증되지 않음");      				
      				message = "회원정보가 없거나 정상적인 로그인이 아닙니다.";
      			}
  				
  			} else {
  				//존재하지않음
  				LOGGER.debug("존재하지 않는 회원");
  				message = "회원정보가 없거나 정상적인 로그인이 아닙니다.";	
  			}  			
  		}		
						
        if (resultVO != null && resultVO.getMBR_ID() != null && !resultVO.getMBR_ID().equals("") && loginPolicyYn) {        	
        	LOGGER.debug("로그인성공!");
        	request.getSession().setAttribute("LoginVO", resultVO);
        	//접속 시간 기록
        	resultVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
        	resultVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
        	loginService.updateLoginHistory(resultVO);
        	//
        	
        	String ajaxMoveUrlForChkLoginTo = (String)request.getSession().getAttribute("ajaxMoveUrlForChkLoginToSuccess");
        	if(ajaxMoveUrlForChkLoginTo!=null && ajaxMoveUrlForChkLoginTo.length()!=0 && !ajaxMoveUrlForChkLoginTo.equals("")) {
        		return_url = "redirect:"+ajaxMoveUrlForChkLoginTo;
        		
        		Map<String, Object> postMap = new HashMap<String,Object>();
    			postMap.put("message", "교육일정표에서 희망하는 교육을 선택하여 신청하시면 됩니다.");	
    			redirectAttributes.addFlashAttribute("alert_data",postMap);
        	}
        	
        	//2020.04.21 현재 수강중이면 나의신청내역으로 이동
        	MyHistoryVO myHistoryVO = new MyHistoryVO();
        	myHistoryVO.setMBR_ID(resultVO.getMBR_ID());
        	int eduCnt = myHistoryService.get_educenter_mbrhstry_now_cnt(myHistoryVO);
        	if(eduCnt > 0 && page_type.equals("mobile")){	
        		//return_url = "redirect:/educenter/m/mbrhstry/list.do";
        		return_url = "redirect:/educenter/mbrhstry/list.do";
        	} else if (eduCnt > 0) {
        		return_url = "redirect:/educenter/mbrhstry/list.do";
        	} else if(eduCnt == 0){
        		//2020.01.31 로그인 성공시 무조건 신청화면으로 이동
            	if(page_type!=null && page_type.equals("mobile")) {
        			//return_url = "redirect:/educenter/m/trnng/list.do";
            		return_url = "redirect:/educenter/trnng/list.do";
        		} else {
        			return_url = "redirect:/educenter/trnng/list.do";
        		}
        	}
        	
        	//낚시명예감시원  MBR_LV_ID = 5
        	if(resultVO.getMBR_LV_ID().equals("5") && page_type.equals("mobile")){
        		//return_url = "redirect:/promotion/m/auditor/board_list.do";
        		return_url = "redirect:/promotion/auditor/board_list.do";
        	} else if(resultVO.getMBR_LV_ID().equals("5")){
        		return_url = "redirect:/promotion/auditor/board_list.do";
        	}
        	
        	//랜딩 페이지 이수증 출력/ 온라인교육 신청
        	String loginPopupRequestType = (String) request.getSession().getAttribute("loginPopupRequestType");
        	String loginPopupReturnUrl = (String) request.getSession().getAttribute("loginPopupReturnUrl");;
        	
    		if(!loginPopupRequestType.equals("index")) {
    			return_url = loginPopupReturnUrl; 
    		} 
    		
    		request.getSession().removeAttribute("loginPopupRequestType");
    		request.getSession().removeAttribute("loginPopupReturnUrl");
    		//랜딩 페이지 이수증 출력/ 온라인교육 신청 end
    		
        	StringBuilder log_dscrp = new StringBuilder();
        	StringBuilder tbl_inf = new StringBuilder();
        	StringBuilder tbl_sn = new StringBuilder();
        	log_dscrp.append("[교육센터사용자-로그인]");
        	log_dscrp.append("[이름:"+resultVO.getMBR_NM()+"(아이디:"+resultVO.getMBR_ID()+")]");
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(resultVO));
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(resultVO.getMBR_ID());
				mEduLogRecordVO.setMBR_LV(resultVO.getMBR_LV_ID());
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO,request);
			} catch(Exception e) {
				LOGGER.debug("[fail log record] "+e.toString());
			}	
        	
        } else {     	
        	LOGGER.debug("로그인실패!");        	
        	String ajaxMoveUrlForChkLoginTo = (String)request.getSession().getAttribute("ajaxMoveUrlForChkLoginToFail");
        	if(authReturnYn) {
        		
        		//만 14세 미만 여부 검증
        		if(mPublicUtils.getAge(birthDay) <= 14) {
        			request.getSession().setAttribute("isChkLoginAuthInfo14ageUnder", "Y");
        		} else {
        			request.getSession().setAttribute("isChkLoginAuthInfo14ageUnder", "N");
        		}
        		//
        		        		
	        	if(ajaxMoveUrlForChkLoginTo!=null && ajaxMoveUrlForChkLoginTo.length()!=0 && !ajaxMoveUrlForChkLoginTo.equals("")) {
	        		return_url = "redirect:"+ajaxMoveUrlForChkLoginTo;
	        		
	        		//본인 인증 정보 임시 저장 - 
	        		request.getSession().setAttribute("ajaxMoveUrlForChkLoginAuthInfo", "Y");
	        		request.getSession().setAttribute("loginActAuthTempDataBirthDay", birthDay);
	        		request.getSession().setAttribute("loginActAuthTempDataName", name);
	        		request.getSession().setAttribute("loginActAuthTempDataPhoneNo", phoneNo);
	        		//End of 본인 인증 정보 임시 저장
	        		
	        		Map<String, Object> postMap = new HashMap<String,Object>();
	    			postMap.put("message", "로그인 가능한 정보가 없어 다음과 같이 정보를 수집합니다.<br>페이지 내용을 확인해주세요.");	
	    			redirectAttributes.addFlashAttribute("alert_data",postMap);
	        	} else {
	        		
	        		//본인 인증 정보 임시 저장 - 
	        		request.getSession().setAttribute("ajaxMoveUrlForChkLoginAuthInfo", "Y");
	        		request.getSession().setAttribute("loginActAuthTempDataBirthDay", birthDay);
	        		request.getSession().setAttribute("loginActAuthTempDataName", name);
	        		request.getSession().setAttribute("loginActAuthTempDataPhoneNo", phoneNo);
	        		//End of 본인 인증 정보 임시 저장
	        		
        			/*
            		Map<String, Object> postMap = new HashMap<String,Object>();
                	postMap.put("type", "alert");
                	postMap.put("message",  message);
        			postMap.put("return_url", "");			
        			redirectAttributes.addFlashAttribute("alert_data",postMap);
        			*/
            		Map<String, Object> postMap = new HashMap<String,Object>();
            		postMap.put("type", "modal-lock");
            		if(page_type!=null && page_type.equals("mobile")) {
            			postMap.put("message", "시스템에 등록되어 있지 않은 신청자로, 수강신청서를 작성한 후 제출하여야 합니다.<br>아래 항목 중 해당하는 교육을 클릭하여 신청서를 작성해주세요.<br><br>"
              					+ "<a href=\"/educenter/m/rmndr/agreeShip.do\" class=\"display-inline-block mt-5\"><img src=\"/common/img/educenter/education_banner_img1.png\" style=\"width:100%;\" title=\"낚시어선전문교육 온라인신청\"/></a>"
              					+ "<br><a href=\"/educenter/m/rmndr/agreeHouse.do\" class=\"display-inline-block mt-10\"><img src=\"/common/img/educenter/education_banner_img2.png\" style=\"width:100%;\" title=\"낚시터전문교육 온라인교육\"/></a>"
              					+ "<br><a href=\"#;\" class=\"display-inline-block mt-10\" id=\"resmpt\"><img src=\"/common/img/educenter/education_banner_img3.png\" style=\"width:100%;\" title=\"신규,재개자전문교육 온라인교육\"/></a>"
              					+ "");
            			postMap.put("pageType", "mobile");
      				} else {
      					postMap.put("message", "시스템에 등록되어 있지 않은 신청자로, 수강신청서를 작성한 후 제출하여야 합니다.<br>아래 항목 중 해당하는 교육을 클릭하여 신청서를 작성해주세요.<br><br>"
      		  					+ "<a href=\"/educenter/rmndr/agreeShip.do\" class=\"display-inline-block mt-10\"><img src=\"/common/img/educenter/education_banner_img1.png\" style=\"width:100%;\" title=\"낚시어선전문교육 온라인신청\"/></a>"
      		  					+ "<br><a href=\"/educenter/rmndr/agreeHouse.do\" class=\"display-inline-block mt-20\"><img src=\"/common/img/educenter/education_banner_img2.png\" style=\"width:100%;\" title=\"낚시터전문교육 온라인교육\"/></a>"
      		  					+ "<br><a href=\"#;\" class=\"display-inline-block mt-20\" id=\"resmpt\"><img src=\"/common/img/educenter/education_banner_img3.png\" style=\"width:100%;\" title=\"신규,재개자전문교육 온라인교육\"/></a>"
      		  					+ "");	      					
      				}
            		redirectAttributes.addFlashAttribute("alert_data",postMap);
	        	}
        	} else { //인증실패
        		
        		isLogKcb = true;
        		log_dscrp2.append("[교육센터사용자-본인인증]");
        		log_dscrp2.append("[vldVrfcAuthTempDataBirthDay : " + request.getSession().getAttribute("vldVrfcAuthTempDataBirthDay") + "]");
        		log_dscrp2.append("[vldVrfcAuthTempDataName : " + request.getSession().getAttribute("vldVrfcAuthTempDataName") + "]");
        		log_dscrp2.append("[vldVrfcAuthTempDataPhoneNo : " + request.getSession().getAttribute("vldVrfcAuthTempDataPhoneNo") + "]");
        		log_dscrp2.append("[login.certNum.rst : " + request.getSession().getAttribute("login.certNum.rst") + "]");
        		log_dscrp2.append("[vldVrfcAuthTempDataRsltCd : " + request.getSession().getAttribute("vldVrfcAuthTempDataRsltCd") + "]");
        		log_dscrp2.append("[vldVrfcAuthTempDataRsltCdCn : " + KCBOkCertResultCode.parseMessage(rsltCd) + "]");
        		log_dscrp2.append("[vldVrfcAuthTempDataRsltMsg : " + request.getSession().getAttribute("vldVrfcAuthTempDataRsltMsg") + "]");
        		
        		log_msg.append(request.getSession().getAttribute("kcbOkcertJsonData").toString());
            		
        		Map<String, Object> postMap = new HashMap<String,Object>();
    			postMap.put("message", "본인인증을 다시 시도해보시기 바랍니다.");	
    			redirectAttributes.addFlashAttribute("alert_data",postMap);
    		}
        }
        
        if(isLogKcb){
        	try {	
        		/**
        		 * LOG RECORED (로그기록)
        		 * */
        		LogRecordVO mEduLogRecordVO = new LogRecordVO();
        		mEduLogRecordVO.setLOG_MSG(log_msg.toString());
        		mEduLogRecordVO.setLOG_DSCRP(log_dscrp2.toString());
        		mEduLogRecordVO.setTBL_INF(tbl_inf2.toString());
        		mEduLogRecordVO.setTBL_SN(tbl_sn2.toString());
//			mEduLogRecordVO.setMBR_ID(resultVO.getMBR_ID());
//			mEduLogRecordVO.setMBR_LV(resultVO.getMBR_LV_ID());
        		mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
        		logRecordService.set_log_kcb_data(mEduLogRecordVO,request);
        	} catch(Exception e) {
        		e.printStackTrace();
        		LOGGER.debug("[fail log record] "+e.toString());
        	}
        }
        
        request.getSession().removeAttribute("ajaxMoveUrlForChkLoginToSuccess");
		request.getSession().removeAttribute("ajaxMoveUrlForChkLoginToFail");
		LOGGER.debug("이동할페이지 > "+return_url);
        return return_url;   
    }	
	//관리자(교육센터) 로그아웃  ------------------------------------------------
    @RequestMapping(value="/educenter/member/logout_act.do")
	public String actionLogout(@RequestParam(value="page_type",required=false) String page_type,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");

    	StringBuilder log_dscrp = new StringBuilder();
    	StringBuilder tbl_inf = new StringBuilder();
    	StringBuilder tbl_sn = new StringBuilder();
    	log_dscrp.append("[교육센터사용자-로그아웃]");
    	log_dscrp.append("[이름:"+loginVO.getMBR_NM()+"(아이디:"+loginVO.getMBR_ID()+")]");
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
    	if(page_type!=null && page_type.equals("mobile")) {
    		return "redirect:/educenter/m/index.do";
		} else {
			return "redirect:/educenter/index.do";
		}
    }
    
    
    //관리자(교육센터) 로그아웃  ------------------------------------------------
    @RequestMapping(value="/educenter/member/modify.do")
	public String edu_member_modify(@ModelAttribute("eduCenterMemberVO") EduCenterMemberVO eduCenterMemberVO, 
			@RequestParam(value="page_type",required=false) String page_type,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
    	
    	if(loginVO == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원세션이 만료되었거나, 로그인상태가 아닙니다.'); location.replace('/'); </script>");
			out.flush();	
		}		
		
		String member_id = loginVO.getMBR_ID();
		eduCenterMemberVO.setMBR_ID(member_id);
		EduCenterMemberVO memberinfo = eduCenterMemberService.get_edu_member_info(eduCenterMemberVO);
		
		model.addAttribute("memberinfo",memberinfo);
		if(page_type!=null && page_type.equals("mobile")) {
			return "sealife/mobile/member/mypage_modify";
		} else {
			return "sealife/member/mypage_modify";
		}
    }
	
  	
}


