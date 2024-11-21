package egovframework.adm.member.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.servlet.support.RequestContextUtils;

import egovframework.adm.member.service.AdmMemberService;
import egovframework.adm.member.service.AdmMemberVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.AuthipinsmsVO;
import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.com.utl.slm.EgovHttpSessionBindingListener;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class AdmMemberController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdmMemberController.class);
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	@Resource(name = "admMemberService")
	private AdmMemberService memberService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "eduCategoryService")
	private EduCategoryService eduCategoryService;
	
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;

	
	//관리자 계정 신청전 동의하기 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/member/agree.do")
	public String edu_member_join_agree(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO, 
		SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		return "adm/member/join_agree";
	}
	
	//관리자 계정 서약서 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/member/pledge_agree.do")
	public String edu_member_join_agree2(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO, 
		SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		
		model.addAttribute("MBR_INDVDL_INFO_ST", mAdmMemberVO.getMBR_INDVDL_INFO_ST());
		return "adm/member/join_pledge_agree";
	}
	
	//관리자 계정 신청페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/member/join.do")
	public String edu_member_join(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO,
		SessionStatus status, HttpServletRequest request, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		
		if(mAdmMemberVO.getMBR_PLEDGE_ST()==null || mAdmMemberVO.getMBR_PLEDGE_ST().length()==0 || mAdmMemberVO.getMBR_PLEDGE_ST().equals("N") 
			|| mAdmMemberVO.getMBR_INDVDL_INFO_ST() == null || mAdmMemberVO.getMBR_INDVDL_INFO_ST().length() == 0) {
			LOGGER.debug("약관 동의를 하지 않은 비정상적인 접근으로 거부됨");
			
			Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "약관 동의가 확인되지 않은 비정상적인 접근으로 거부되었습니다.");
			postMap.put("return_url", "");
			postMap.put("type", "alert");
			postMap.put("title", "알림");
			postMap.put("closebtn", "확인");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			
			//return "adm/error/page_400";
			
			return "redirect:/adm/member/agree.do";
		}
		
		//직급 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00003");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_position_cd",list_position_cd);
		}
  		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		//교육기관목록
		{
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setUSE_ST("1");
			eduCategoryInsInfVO.setNotUsedPagination(true);
			List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
			model.addAttribute("list_ins_info_cd",edu_category_ins_inf);	
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_trgt_cd",list_mbr_cd);
		}
		
		model.addAttribute("MBR_PLEDGE_ST", mAdmMemberVO.getMBR_PLEDGE_ST());
		model.addAttribute("MBR_INDVDL_INFO_ST", mAdmMemberVO.getMBR_INDVDL_INFO_ST());
		
		return "adm/member/join";
	}
	
	//관리자 계정 신청 처리 로직 ------------------------------------------------
  	@RequestMapping(value="/adm/member/join_adm_act.do", method = RequestMethod.POST)
  	public String ajax_member_join_adm_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, 
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[관리자-계정생성요청-신규관리자회원임시등록]");
		tbl_inf.append("MBR_TB");
		
		try {
			
			//직급 코드 조회
  	  		CodeSetVO mCodeSetVO = new CodeSetVO();
  	  		mCodeSetVO.setCD_MASTER_ID("CID00003");
  	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			
  	  		String MBR_HP = eduMemberVO.getMBR_HP().replaceAll("-", "");
  	  		
  	  		eduMemberVO.setMBR_ST("R");//사용여부는 관리자 승인 전 상태로 처리 
  	  		
  	  		//기존방식
			//아이디는 휴대폰번호로 임시발급 , 본인인증 후 변경진행
//			eduMemberVO.setMBR_ID(MBR_HP);
			//초기 ㅂㅁ 발급하지 않음 - 사용자가 수정가능.
			//eduMemberVO.setMBR_PWD(EgovFileScrty.encryptPassword(eduMemberVO.getMBR_PWD(), eduMemberVO.getMBR_ID()));
			
			// 수정후
			// 아이디, ㅂㅁ 입력
			String mbr_id = eduMemberVO.getMBR_ID();
			String mbr_pwd = eduMemberVO.getMBR_PWD();
			
			eduMemberVO.setMBR_ID(mbr_id);
			eduMemberVO.setMBR_PWD(EgovFileScrty.encryptPassword(mbr_pwd, mbr_id));
			
			eduMemberVO.setREG_MBR_ID("guest");
			eduMemberVO.setUPD_MBR_ID("guest");
			
			log_dscrp.append("[관리자계정신청자:"+eduMemberVO.getMBR_NM()+"(연락처 및 아이디:"+MBR_HP+")");

  	  		int isExistCnt = eduMemberService.get_id_search(eduMemberVO);
			if(isExistCnt > 0) {
				data.put("error", "1");
				data.put("msg", "이미 신청한 정보가 있거나 중복되는 회원정보가 존재하여 계정 신청이 거절되었습니다.\n(자세한 사항은 관리자에게 문의해주세요.)");
				log_dscrp.append("|이미 중복 신청건이 존재하여 신청 거부");
			} else {
				if(eduMemberVO.getMBR_POSITION_CD().equals("PCD0007")) { //통합관리자
					eduMemberVO.setMBR_GRP_1_ST("Y");
					eduMemberVO.setMBR_GRP_2_ST("Y");
					eduMemberVO.setMBR_GRP_3_ST("Y");
					eduMemberVO.setMBR_GRP_4_ST("Y");
					eduMemberVO.setMBR_LV_ID("2");//신청서 등록 가능
					log_dscrp.append("|2등급 그룹 등록|통합운영자 권한 요청|거부됨");
				} else if(eduMemberVO.getMBR_POSITION_CD().equals("PCD0005")) { //낚시누리
					//eduMemberVO.setMBR_GRP_1_ST("Y");
					//eduMemberVO.setMBR_LV_ID("3");
					log_dscrp.append("|2등급 그룹 등록|낚시누리-공단운영자 권한 요청|거부됨");
				} else if(eduMemberVO.getMBR_POSITION_CD().equals("PCD0006")) { //낚시전문교육
					//eduMemberVO.setMBR_GRP_2_ST("Y");
					//eduMemberVO.setMBR_LV_ID("3");
					log_dscrp.append("|2등급 그룹 등록|낚시전문교육-공단운영자 권한 요청|거부됨");
				} else if(eduMemberVO.getMBR_POSITION_CD().equals("PCD0002") //해경
					|| eduMemberVO.getMBR_POSITION_CD().equals("PCD0003") //지자체
					|| eduMemberVO.getMBR_POSITION_CD().equals("PCD0004") //교육기관
				) { 
					eduMemberVO.setMBR_GRP_2_ST("Y");
					eduMemberVO.setMBR_LV_ID("4");
					String CD_NM = "";
					for(int j=0;j<list_position_cd.size(); j++) {
						CodeSetVO codeSetVO = (CodeSetVO)list_position_cd.get(j);
						if(codeSetVO.getCD_ID().equals(eduMemberVO.getMBR_POSITION_CD())) {
							CD_NM = codeSetVO.getCD_NM();
							break;
						}
					}
					log_dscrp.append("|2등급 그룹 등록|낚시전문교육-"+CD_NM+" 권한 부여");					
				} else {
					log_dscrp.append("|그룹 등급 및 권한 부여 불가 조건");
				}
				
				log_dscrp.append("]");
				
				eduMemberVO.setMBR_USG_DT(eduMemberVO.getMBR_USG_DT()+" 23:59:59");
								
				eduMemberService.set_edu_member_reg(eduMemberVO);
						
				data.put("error", "0");
				data.put("msg", "계정 신청이 완료되었습니다.\n최종 승인까지는 다소 시간이 소요될 수 있습니다.\n(승인 후 초기 아이디와 비밀번호는 휴대폰번호를 입력하시면 됩니다.)");	
			}
		} catch(NullPointerException e1) {
			LOGGER.debug("[fail NullPointerException] "+e1.toString());
			e1.printStackTrace();
		} catch(Exception e) {	
			LOGGER.debug("[fail process] "+e.toString());
			log_dscrp.append("[등록실패:에러발생]["+eduMemberVO.getMBR_ID()+"]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
			
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID("guest");
			//mEduLogRecordVO.setMBR_LV();
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
		
		LOGGER.debug(data.toString());
//		response.setContentType("application/json;charset=utf-8");
//		response.getWriter().print(data);
		return "redirect:/adm/index.do";
  	}
	
	//관리자 로그인 ------------------------------------------------ 메인관리자 로그인은 seadm member 에서 처리
	@RequestMapping(value = "/adm/member/login.do")
	public String edu_member_login(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO, 
		SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		
  		//return "adm/member/login";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
    	if(loginVO!=null) {
	    	return "redirect:/seadm/index.do";
    	}
		model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid"));
  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath"));
  		model.addAttribute("kcbokcert_sitenm",propertiesService.getString("KcbOkCert.siteNm"));
  		model.addAttribute("kcbokcert_siteurl",propertiesService.getString("KcbOkCert.siteUrl"));
		
		return "seadm/member/login";
	}
	
	//개발사전용 로그인 ------------------------------------------------ 메인관리자 로그인은 seadm member 에서 처리
	@RequestMapping(value = "/adm/member/login_pass.do")
	public String edu_member_login_pass(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO, 
		SessionStatus status, HttpServletRequest request, ModelMap model, @RequestParam(value="key",required=false) String key) throws Exception {
	
		PublicUtils mPublicUtils = new PublicUtils();
		
		if(key!=null && key.trim().length()>0 && key.equals("watosys.1.217.88.66")) {
			LOGGER.debug("일반 사용자 임시 로그인 시도 ("+mPublicUtils.getClientIpAddr(request)+")");
		} else {
			return "redirect:/seadm/index.do";
		}
		model.addAttribute("key", key);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
    	if(loginVO!=null) {
	    	return "redirect:/seadm/index.do";
    	}		
		return "adm/member/login";
	}
	
	// 관리자 로그인 - 본인인증 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/adm/member/login_crtfc_act.do", method = RequestMethod.POST)
	public String adm_member_login_crtfc_act(
			@RequestParam(value = "admin_page_name", required = false) String admin_page_name,
			RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();

		try {
			String certNum_req = "";
			String certNum_rst = "";
			boolean isAuthSms = true;

			try {
				isAuthSms = request.getSession().getAttribute("login.isAuthSms").toString().equals("Y");
				LOGGER.debug("isAuthSms : " + isAuthSms);
			} catch (Exception e) {
				LOGGER.debug("isAuthSms null ");
			}
			try {
				certNum_req = request.getSession().getAttribute("login.certNum.req").toString();
				LOGGER.debug("certNum_req : " + certNum_req);
			} catch (Exception e) {
				LOGGER.debug("certNum_req null ");
			}
			try {
				certNum_rst = request.getSession().getAttribute("login.certNum.rst").toString();
				LOGGER.debug("certNum_rst : " + certNum_rst);
			} catch (Exception e) {
				LOGGER.debug("certNum_rst null ");
			}

			if (isAuthSms) {// 본인인증했을 때

				if ((certNum_req == null || certNum_req.length() == 0 || certNum_rst.length() == 0)
						|| (!certNum_req.equals(certNum_rst))) {// 비정상

					LOGGER.debug("유효하지 않은 정보입니다.");
					Map<String, Object> postMap = new HashMap<String, Object>();
					postMap.put("message", "유효하지 않은 정보입니다.");
					postMap.put("return_url", "");
					redirectAttributes.addFlashAttribute("alert_data", postMap);
					return "redirect:/" + admin_page_name + "/member/login.do";

				} else {// 정상
					LOGGER.debug("정상 진입 ");

					// resultVO와 본인인증 비교
					String name = request.getParameter("name"); // 이름
					String phoneNo = request.getParameter("phoneNo"); // 전화번호
					String birthDay = request.getParameter("birthDay"); // 생년월일
					String gender = request.getParameter("gender"); // 성별

					String per_auth_type = request.getParameter("per_auth_type"); // 인증타입
					String per_auth = request.getParameter("per_auth"); // 인증키

					LoginVO loginVO = (LoginVO) request.getSession().getAttribute("crtfcLoginVO");

					if (loginVO.getMBR_NM().equals(name) && loginVO.getMBR_HP().equals(phoneNo) && loginVO.getMBR_BIRTH().equals(birthDay)) {
						LOGGER.debug("접속 시간 기록 ");

						request.getSession().setAttribute("LoginVO", loginVO);
						// 접속 시간 기록
						loginVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
						loginVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
						loginService.updateLoginHistory(loginVO);
						//

						// 관리자 비밀번호 변경일자 검증
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
						int now = Integer.parseInt(dateFormat.format(cal.getTime()));

						String threeMonthsLater = mPublicUtils
								.getCurrentPositionToAfterTargetMonth(loginVO.getMBR_PWD_DT(), 3).substring(0, 10);
						int month = Integer.parseInt(threeMonthsLater.replace("-", ""));

						String path = "";

						LOGGER.debug("들어온 페이지 " + admin_page_name);
						
						if (month <= now) {
							request.getSession().setAttribute("isNextStep", true);
							request.getSession().setAttribute("isLoginCrtfcAct", true);
							
							redirectAttributes.addFlashAttribute("birthDay",birthDay);
							redirectAttributes.addFlashAttribute("name",name);
							redirectAttributes.addFlashAttribute("phoneNo",phoneNo);
							redirectAttributes.addFlashAttribute("loginCrtfcActVO",loginVO);
							LOGGER.debug("비밀번호 변경 해야하는 관리자");
							
							path = "redirect:/adm/member/modifyAdmPwd.do";
						} else {
							path = "redirect:/" + admin_page_name + "/index.do";
						}
						//

						StringBuilder log_dscrp = new StringBuilder();
						StringBuilder tbl_inf = new StringBuilder();
						StringBuilder tbl_sn = new StringBuilder();

						String adm = "";
						switch (admin_page_name) {
						case "seadm":
							adm = "종합센터관리자";
							break;
						case "eduadm":
							adm = "교육센터관리자";
							break;
						case "cti":
							adm = "cti관리자";
							break;
						}

						LOGGER.debug("[" + adm + "-로그인]");
						log_dscrp.append("[" + adm + "-로그인]");
						try {
							/**
							 * LOG RECORED (로그기록)
							 */
							LogRecordVO mEduLogRecordVO = new LogRecordVO();
							mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(loginVO));
							mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
							mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
							mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
							mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
							mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
							mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
							logRecordService.set_log_data(mEduLogRecordVO, request);
						} catch (Exception e) {
							LOGGER.debug("[fail log record] " + e.toString());
						}
						return path;

					} else {
						
						LOGGER.debug("본인인증 정보가 입력하신 계정과 일치하지 않습니다.");
						Map<String, Object> postMap = new HashMap<String, Object>();
						postMap.put("message", "본인인증 정보가 입력하신 계정과 일치하지 않습니다.");
						postMap.put("return_url", "");
						redirectAttributes.addFlashAttribute("alert_data", postMap);
						return "redirect:/" + admin_page_name + "/member/login.do";
					}
				}

			} else {// 본인인증 안 했을 시

				LOGGER.debug("본인인증을 완료하지 않았습니다.");
				Map<String, Object> postMap = new HashMap<String, Object>();
				postMap.put("message", "본인인증을 완료하지 않았습니다.");
				postMap.put("return_url", "");
				redirectAttributes.addFlashAttribute("alert_data", postMap);
				return "redirect:/" + admin_page_name + "/member/login.do";
			}

		} catch (Exception e) {
			LOGGER.debug(e.toString());
			request.getSession().removeAttribute("joincheck.isAuthSms");
			request.getSession().removeAttribute("login.certNum.req");
			request.getSession().removeAttribute("login.certNum.res");
		}
		return null;
	}
	
	//관리자 본인인증 및 ㅂㅁ 변경 ------------------------------------------------
	@RequestMapping(value = "/adm/member/modifyAdmInfo.do")
	public String edu_member_master_chanage_info(@ModelAttribute("loginVO") LoginVO loginVO, RedirectAttributes redirectAttributes,
			@RequestParam(value="changeInfo",required=false) String changeInfo,
			@RequestParam(value="agree",required=false) String agree,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String returnUrl = "";
		
		String MASTER_MBR_ID = "";
		String MASTER_MBR_LV = "";
		LoginVO updLoginVO = new LoginVO();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
    	StringBuilder tbl_inf = new StringBuilder();
    	StringBuilder tbl_sn = new StringBuilder();
    	log_dscrp.append("[관리자 본인인증 정보 갱신]");
    	boolean isAllowOk = false;
		boolean isAlertData = false;
		try { 
			isAlertData = (boolean)request.getSession().getAttribute("isAlertData");
			request.getSession().removeAttribute("isAlertData");
			log_msg.append("[isAlertData:"+isAlertData+"]");
		} catch(Exception e) {
			request.getSession().removeAttribute("isAlertData");
			log_msg.append("[isAlertData is null]");
		}
		LoginVO chkAdmLoginVO = null;
		try { 
			chkAdmLoginVO = (LoginVO)request.getSession().getAttribute("chkAdmLoginVO");
			request.getSession().removeAttribute("chkAdmLoginVO");
			log_msg.append("[chkAdmLoginVO:"+chkAdmLoginVO+"]");
		} catch(Exception e) {
			request.getSession().removeAttribute("chkAdmLoginVO");
			log_msg.append("[chkAdmLoginVO is null]");
		}
		boolean isChangeInfo = false;
		try { 
			isChangeInfo = (boolean)request.getSession().getAttribute("isChangeInfo");
			request.getSession().removeAttribute("isChangeInfo");
			log_msg.append("[isChangeInfo:"+isChangeInfo+"]");
		} catch(Exception e) { 
			request.getSession().removeAttribute("isChangeInfo");
			log_msg.append("[isChangeInfo is null]");
		}
		LoginVO chkLoginVO = new LoginVO();
		String MBR_SN = "";
		try { 
			MBR_SN = (String)request.getSession().getAttribute("MBR_SN");
			request.getSession().removeAttribute("MBR_SN");
			log_msg.append("[MBR_SN:"+MBR_SN+"]");
			chkLoginVO.setMBR_SN(MBR_SN);
			chkLoginVO = loginService.searchSnInfo(chkLoginVO);			
		} catch(Exception e) { 
			request.getSession().removeAttribute("MBR_SN");
			log_msg.append("[MBR_SN is null]");
		}
		if(chkAdmLoginVO!=null) {
			log_dscrp.append("[이름:"+chkAdmLoginVO.getMBR_NM()+"(아이디:"+chkAdmLoginVO.getMBR_ID()+")]");
		} else {
			log_dscrp.append("[이름:"+chkLoginVO.getMBR_NM()+"(아이디:"+chkLoginVO.getMBR_ID()+")]");
		}
		LOGGER.debug("isAlertData = "+isAlertData);
		LOGGER.debug("isChangeInfo = "+isChangeInfo);
		LOGGER.debug("MBR_SN = "+MBR_SN);
		try {
			// 초기 진입
			if(isAlertData && chkAdmLoginVO!=null) {
				LOGGER.debug("본인인증을 위한 개인정보를 갱신해야 하는 관리자!!");
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
	        	postMap.put("message",  "본인인증을 위한 개인정보를 갱신해야 합니다.");
	        	postMap.put("closebtn", "N");
				postMap.put("type", "");//alert
				postMap.put("timer", 0);//0:없어지지않음(1000=1초)
				model.addAttribute("alert_data",postMap);
				//개인정보전달
		  		model.addAttribute("info",chkAdmLoginVO);
		  		model.addAttribute("MBR_SN",chkAdmLoginVO.getMBR_SN());
		  		//
		  		returnUrl = "adm/member/modify_adm_info";
		  		isAllowOk = true;
			} 
			// 정보갱신
			if(isChangeInfo && (changeInfo!=null && changeInfo.equals("ok")) && (agree!=null && agree.length()!=0)) {
				LOGGER.debug("정보갱신시작");
				log_dscrp.append("[본인인증을 위한 개인정보갱신]");
				try {
					loginVO.setMBR_SN(MBR_SN);
					loginService.updateChangeInfoFirst(loginVO);
					log_dscrp.append("[변경후][이름:"+loginVO.getMBR_NM()+"(아이디:"+chkLoginVO.getMBR_ID()+")]");
					
					Map<String, Object> postMap = new HashMap<String,Object>();
					postMap.put("return_url", "");
					postMap.put("title", "");
		        	postMap.put("message",  "새로운 로그인을 해주세요.");
		        	postMap.put("closebtn", "N");
					postMap.put("type", "");//alert
					postMap.put("timer", 0);//0:없어지지않음
					redirectAttributes.addFlashAttribute("alert_data",postMap);
					returnUrl = "redirect:/adm/member/login.do";
					isAllowOk = true;					
				} catch(Exception e) {
					LOGGER.debug("비정상처리오류");
					log_dscrp.append("[비정상처리오류]");
		  			log_msg.append("[비정상처리오류("+e.toString()+")]");
				}
			}
			// 기타 실패
			if(!isAllowOk) {
				LOGGER.debug("비정상적인 접근으로 불가!");
				log_dscrp.append("[비정상적인접근으로거부됨]");
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
	        	postMap.put("message",  "비정상적인 접근입니다.");
	        	postMap.put("closebtn", "N");
				postMap.put("type", "");//alert
				postMap.put("timer", 0);//0:없어지지않음
				redirectAttributes.addFlashAttribute("alert_data",postMap);
				returnUrl = "redirect:/adm/member/login.do";
			}
		} catch(Exception e) {
			LOGGER.debug("처리에러");
  			log_dscrp.append("[처리에러]");
  			log_msg.append("[처리에러("+e.toString()+")]");
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(loginVO));
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkAdmLoginVO));
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(updLoginVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}	
		return returnUrl;
	}
	
	//관리자 본인인증 및 ㅂㅁ 변경 ------------------------------------------------
	@RequestMapping(value = "/adm/member/modifyAdmPwd.do")
	public String edu_member_master_chanage_pwd(@ModelAttribute("loginVO") LoginVO loginVO, RedirectAttributes redirectAttributes,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		String returnUrl = "";
		
		String MASTER_MBR_ID = "";
		String MASTER_MBR_LV = "";
		LoginVO updLoginVO = new LoginVO();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
    	StringBuilder tbl_inf = new StringBuilder();
    	StringBuilder tbl_sn = new StringBuilder();
    	log_dscrp.append("[관리자 본인인증 및 ㅂㅁ 변경]");
		boolean isAllowOk = false;
		boolean isAlertData = false;
		try { 
			isAlertData = (boolean)request.getSession().getAttribute("isAlertData");
			request.getSession().removeAttribute("isAlertData");
			log_msg.append("[isAlertData:"+isAlertData+"]");
		} catch(Exception e) { 
			request.getSession().removeAttribute("isAlertData");
			log_msg.append("[isAlertData is null]");
		}
		LoginVO chkAdmLoginVO = null;
		try { 
			chkAdmLoginVO = (LoginVO)request.getSession().getAttribute("chkAdmLoginVO");
			request.getSession().removeAttribute("chkAdmLoginVO");
			log_msg.append("[chkAdmLoginVO:"+chkAdmLoginVO+"]");
		} catch(Exception e) {
			request.getSession().removeAttribute("chkAdmLoginVO");
			log_msg.append("[chkAdmLoginVO is null]");
		}
		boolean isNextStep = false;
		try { 
			isNextStep = (boolean)request.getSession().getAttribute("isNextStep");
			request.getSession().removeAttribute("isNextStep");
			log_msg.append("[isNextStep:"+isNextStep+"]");
		} catch(Exception e) { 
			request.getSession().removeAttribute("isNextStep");
			log_msg.append("[isNextStep is null]");
		}
		boolean isChangePwd = false;
		try { 
			isChangePwd = (boolean)request.getSession().getAttribute("isChangePwd");
			request.getSession().removeAttribute("isChangePwd");
			log_msg.append("[isChangePwd:"+isChangePwd+"]");
		} catch(Exception e) { 
			request.getSession().removeAttribute("isChangePwd");
			log_msg.append("[isChangePwd is null]");
		}
		LoginVO chkLoginVO = new LoginVO();
		String MBR_SN = "";
		try { 
			MBR_SN = (String)request.getSession().getAttribute("MBR_SN");
			request.getSession().removeAttribute("MBR_SN");
			log_msg.append("[MBR_SN:"+MBR_SN+"]");
			chkLoginVO.setMBR_SN(MBR_SN);
			chkLoginVO = loginService.searchSnInfo(chkLoginVO);
		} catch(Exception e) { 
			request.getSession().removeAttribute("MBR_SN");
			log_msg.append("[MBR_SN is null]");
		}
		if(chkAdmLoginVO!=null) {
			log_dscrp.append("[이름:"+chkAdmLoginVO.getMBR_NM()+"(아이디:"+chkAdmLoginVO.getMBR_ID()+")]");
		} else {
			if(chkLoginVO!=null) {
				log_dscrp.append("[이름:"+chkLoginVO.getMBR_NM()+"(아이디:"+chkLoginVO.getMBR_ID()+")]");
			} else if(loginVO!=null) {
				log_dscrp.append("[이름:"+loginVO.getMBR_NM()+"(아이디:"+loginVO.getMBR_ID()+")]");
			} else {
				log_dscrp.append("[알수없는회원정보]");
			}
		}
		
		LOGGER.debug("isAlertData = "+isAlertData);
		LOGGER.debug("isNextStep = "+isNextStep);
		LOGGER.debug("isChangePwd = "+isChangePwd);
		LOGGER.debug("MBR_SN = "+MBR_SN);
		try {
			// 초기 진입
			if(isAlertData && chkAdmLoginVO!=null) {
				LOGGER.debug("본인인증 후 ㅂㅁ 변경이 필요한 관리자!");
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
	        	postMap.put("message",  "본인인증 후 비밀번호를 변경해주세요.");
	        	postMap.put("closebtn", "N");
				postMap.put("type", "");//alert
				postMap.put("timer", 0);//0:없어지지않음(1000=1초)
				model.addAttribute("alert_data",postMap);
				//본인인증관련
				AuthipinsmsVO authipinsmsVO = new AuthipinsmsVO();
				authipinsmsVO.setIdtfycustomerid(propertiesService.getString("Idtfy.customerId"));
				authipinsmsVO.setIdtfyserviceno(propertiesService.getString("Idtfy.idNo"));
				authipinsmsVO.setIpincustomerid(propertiesService.getString("Ipin.customerId"));
				authipinsmsVO.setIpinserviceno(propertiesService.getString("Ipin.idNo"));		
				model.addAttribute("authipinsms", authipinsmsVO);
				model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid"));
		  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath"));
		  		model.addAttribute("kcbokcert_sitenm",propertiesService.getString("KcbOkCert.siteNm"));
		  		model.addAttribute("kcbokcert_siteurl",propertiesService.getString("KcbOkCert.siteUrl"));
				//
		  		model.addAttribute("MBR_ID",chkAdmLoginVO.getMBR_ID());
		  		returnUrl = "adm/member/modify_adm_pwd";
		  		isAllowOk = true;
			} 
			// 본인인증구간
			if(isNextStep) {
				LOGGER.debug("본인인증완료");
				log_dscrp.append("[본인인증완료]");
				try {
					
					//관리자 본인인증 후 비밀번호 3개월 변경
					String birthDay = "";
			  		String name = "";
			  		String phoneNo = "";
			  		
			  		boolean isLoginCrtfcAct = false;
			  		try { 
			  			isLoginCrtfcAct = (boolean)request.getSession().getAttribute("isLoginCrtfcAct");
			  			request.getSession().removeAttribute("isLoginCrtfcAct");
						log_msg.append("[isLoginCrtfcAct:"+isLoginCrtfcAct+"]");
					} catch(Exception e) { 
						request.getSession().removeAttribute("isLoginCrtfcAct");
						log_msg.append("[isLoginCrtfcAct is null]");
					}
			  		
			  		LoginVO realChkAdmLoginVO = null;
			  		
			  		if(isLoginCrtfcAct){
			  			
			  			Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
			  			birthDay = map.get("birthDay").toString();
			  			name = map.get("name").toString();
			  			phoneNo = map.get("phoneNo").toString();
			  			
			  			LoginVO loginCrtfcActVO = (LoginVO)map.get("loginCrtfcActVO");
			  			realChkAdmLoginVO = loginService.actionLogin(loginCrtfcActVO);
			  			
			  		} else {
			  			birthDay = request.getParameter("birthDay").toString().trim();
				  		name = request.getParameter("name").toString().trim();
				  		phoneNo = request.getParameter("phoneNo").toString().replaceAll("-", "").trim();
			  			realChkAdmLoginVO = loginService.actionChkAdmLogin(loginVO);
			  		}
			  		//
			  		
			  		log_dscrp.append("[이름:"+realChkAdmLoginVO.getMBR_NM()+"(아이디:"+realChkAdmLoginVO.getMBR_ID()+")]");
			  		
			  		String mbr_nm = realChkAdmLoginVO.getMBR_NM().trim();
			  		String mbr_birth = realChkAdmLoginVO.getMBR_BIRTH().trim();
			  		String mbr_hp = realChkAdmLoginVO.getMBR_HP().trim();
			  		
			  		if(realChkAdmLoginVO!=null && mbr_nm.equals(name) && mbr_birth.equals(birthDay) && mbr_hp.equals(phoneNo)) {
			  			LOGGER.debug("정보변경페이지정상이동");
			  			model.addAttribute("MBR_SN",realChkAdmLoginVO.getMBR_SN());
			  			model.addAttribute("info", realChkAdmLoginVO);
						returnUrl = "adm/member/modify_adm_pwd_change";	
			  		} else {
			  			LOGGER.debug("비정상적인접근으로정보변경페이지이동실패");
			  			log_dscrp.append("[비정상적인접근으로정보변경페이지이동실패]");
			  			returnUrl = "redirect:/adm/member/login.do";
			  		}
			  		isAllowOk = true;
				} catch(Exception e) {
					e.printStackTrace();
					LOGGER.debug("비정상처리오류");
		  			log_dscrp.append("[비정상처리오류("+e.toString()+")]");
		  			returnUrl = "redirect:/adm/member/login.do";
				}
			} 
			// 정보변경구간
			if(isChangePwd) {
				LOGGER.debug("정보변경");
				log_dscrp.append("[정보변경]");
				loginVO.setMBR_ST("Y");
				loginVO.setMBR_SN(MBR_SN);
				chkAdmLoginVO = loginService.searchSnInfo(loginVO);
				log_dscrp.append("[변경후][이름:"+chkAdmLoginVO.getMBR_NM()+"(아이디:"+loginVO.getMBR_ID()+")]");
				LOGGER.debug("[변경후][이름:"+chkAdmLoginVO.getMBR_NM()+"(아이디:"+loginVO.getMBR_ID()+")][비밀번호:"+loginVO.getMBR_PWD()+"]");
				boolean isRefuse = false;
				String mbrPwd = loginVO.getMBR_PWD();
				String msg = "";
  				if(mbrPwd.length() < 10 || mbrPwd.length() > 20){
  					LOGGER.debug("비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
  					msg = "비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.";
	  	  			isRefuse = true;
  				} else if(!mbrPwd.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{10,20}$")){
  					LOGGER.debug("비밀번호는 공백없이 영문,숫자,특수문자[@,!,%,*,#]를 혼합하여 입력해주세요.");
  					msg = "비밀번호는 공백없이 영문,숫자,특수문자[@,!,%,*,#]를 혼합하여 입력해주세요.";
	  	  			isRefuse = true;
  				} else {
  					LOGGER.debug("사용이 가능 한 아이디 입니다.");
  					msg = "사용이 가능 한 아이디 입니다.";
	  	  			isRefuse = false;
  				}
				if(isRefuse) {
					Map<String, Object> postMap = new HashMap<String,Object>();
					postMap.put("return_url", "");
					postMap.put("title", "");
		        	postMap.put("message",  msg);
		        	postMap.put("closebtn", "N");
					postMap.put("type", "");//alert
					postMap.put("timer", 0);//0:없어지지않음
					//redirectAttributes.addFlashAttribute("alert_data",postMap);
					model.addAttribute("alert_data", postMap);
					model.addAttribute("MBR_SN",chkAdmLoginVO.getMBR_SN());
		  			model.addAttribute("info", chkAdmLoginVO);
					returnUrl = "adm/member/modify_adm_pwd_change";	
					isAllowOk = true;
				} else {
					if(chkAdmLoginVO.getMBR_PWD_ST().equals("1")) {//최초 로그인이므로 아이디 변경
						LOGGER.debug("최초로그인으로아이디도변경함");
						log_dscrp.append("[최초로그인으로아이디도변경함]");
						updLoginVO.setMBR_SN(chkAdmLoginVO.getMBR_SN());
						updLoginVO.setMBR_ID(loginVO.getMBR_ID());
						updLoginVO.setMBR_PWD(EgovFileScrty.encryptPassword(loginVO.getMBR_PWD(), loginVO.getMBR_ID()));
						loginService.updatePasswordFirst(updLoginVO);
						MASTER_MBR_ID = loginVO.getMBR_ID();
						MASTER_MBR_LV = chkAdmLoginVO.getMBR_LV_ID();
					} else {//ㅂㅁ만 변경
						LOGGER.debug("ㅂㅁ만변경됨");
						log_dscrp.append("[ㅂㅁ만변경됨]");
						updLoginVO.setMBR_ID(chkAdmLoginVO.getMBR_ID());
						updLoginVO.setMBR_PWD(EgovFileScrty.encryptPassword(loginVO.getMBR_PWD(), chkAdmLoginVO.getMBR_ID()));
						loginService.updatePassword(updLoginVO);
						MASTER_MBR_ID = chkAdmLoginVO.getMBR_ID();
						MASTER_MBR_LV = chkAdmLoginVO.getMBR_LV_ID();
					}
					Map<String, Object> postMap = new HashMap<String,Object>();
					postMap.put("return_url", "");
					postMap.put("title", "");
		        	postMap.put("message",  "변경 된 정보로 로그인 해주세요.");
		        	postMap.put("closebtn", "N");
					postMap.put("type", "");//alert
					postMap.put("timer", 0);//0:없어지지않음
					redirectAttributes.addFlashAttribute("alert_data",postMap);
					returnUrl = "redirect:/adm/member/login.do";
					isAllowOk = true;
				}
			}
			// 기타 실패
			if(!isAllowOk) {
				LOGGER.debug("비정상적인 접근으로 불가!");
				log_dscrp.append("[비정상적인접근으로거부됨]");
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
	        	postMap.put("message",  "비정상적인 접근입니다.");
	        	postMap.put("closebtn", "N");
				postMap.put("type", "");//alert
				postMap.put("timer", 0);//0:없어지지않음
				redirectAttributes.addFlashAttribute("alert_data",postMap);
				returnUrl = "redirect:/adm/member/login.do";
			}
		} catch(Exception e) {
			LOGGER.debug("처리에러");
  			log_dscrp.append("[처리에러]");
  			log_msg.append("[처리에러("+e.toString()+")]");
		}
		LOGGER.debug("이동할페이지 : " + returnUrl);
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(loginVO));
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkAdmLoginVO));
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(updLoginVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}	
		return returnUrl;		
	}
	

	//관리자 로그인 - 인증  -------------------------------------------------
	@RequestMapping(value="/adm/member/login_act.do")
    public String edu_member_login_act(@ModelAttribute("loginVO") LoginVO loginVO,  
    		HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		//고광훈 추가 - 관리자 중복 로그인 처리
		EgovHttpSessionBindingListener listener = new EgovHttpSessionBindingListener();
		request.getSession().setAttribute( loginVO.getMBR_ID(), listener);		

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
			return "redirect:/adm/member/login.do";
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
	        	return "adm/member/login";
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
        	}
        }
        // End 로그인 시도 횟수 검증
		
		boolean loginPolicyYn = true;
        if (resultVO != null && resultVO.getMBR_ID() != null && !resultVO.getMBR_ID().equals("") && loginPolicyYn) {

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
          	return "redirect:/adm/member/login.do";
          	
        } else {
        	        	
        	//로그인 실패 횟수 증가
        	loginService.updateLoginRetry(loginVO);
        	
        	LOGGER.debug("로그인정보 불일치로 로그인 실패로 알림처리");
        	if(retryCnt > 5) retryCnt = 5;
    		Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("return_url", "");
			postMap.put("title", "");
        	postMap.put("message",  "아이디 또는 비밀번호가 일치하지 않습니다.<br><span class=\"red-600\">(로그인 실패 "+retryCnt+"회)</span><br>로그인 5회 실패시, 해당 계정의 권한이 제한됩니다.");
        	postMap.put("closebtn", "N");
			postMap.put("type", "");//alert
			postMap.put("timer", 0);//0:없어지지않음
			model.addAttribute("alert_data",postMap);
			
        	//model.addAttribute("message", "fail");
        	return "adm/member/login";
        }
    }	
	
	//관리자 로그인 - 개발사전용  -------------------------------------------------
	@RequestMapping(value="/adm/member/login_pass_act.do")
    public String edu_member_login_pass_act(@ModelAttribute("loginVO") LoginVO loginVO, @RequestParam(value="key",required=false) String key,
    		HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		if(key!=null && key.trim().length()>0 && key.equals("watosys.1.217.88.66")) {
			LOGGER.debug("일반 사용자 임시 로그인 시도 ("+mPublicUtils.getClientIpAddr(request)+")");
		} else {
			return "redirect:/seadm/index.do";
		}
		
		model.addAttribute("key", key);
	
		if(loginVO==null || loginVO.getMBR_ID()==null || loginVO.getMBR_ID().length()==0) {
			LOGGER.debug("비정상적인 접근");
			Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "비정상적인 접근으로 거부되었습니다.");
			postMap.put("return_url", "");
			postMap.put("type", "alert");
			postMap.put("title", "알림");
			postMap.put("closebtn", "확인");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			return "redirect:/adm/member/login_pass.do";
		}	
		
		loginVO.setMBR_PWD(EgovFileScrty.encryptPassword(loginVO.getMBR_PWD(), loginVO.getMBR_ID()));
		LoginVO resultVO = loginService.actionLogin(loginVO);
		// 로그인 시도 횟수 검증
        LoginVO retryLoginVO = loginService.retryLogin(loginVO);
        int retryCnt = 0;
        try {
        	retryCnt = Integer.parseInt(retryLoginVO.getMBR_RETRY_LOGIN_CNT());
        } catch(Exception e) { 
        	LOGGER.debug("존재하지 않는 사용자 정보");
        	retryCnt = 0;
        }
	   
		
		boolean loginPolicyYn = true;
        if (resultVO != null && resultVO.getMBR_ID() != null && !resultVO.getMBR_ID().equals("") && loginPolicyYn) {
      		
        	//관리자 계정 여부 확인
      		if (resultVO.getMBR_LV_ID().equals("10")) {
        			LOGGER.debug("접근 할 권한이 없습니다.");
        			Map<String, Object> postMap = new HashMap<String,Object>();
      			postMap.put("message", "비정상적인 접근으로 거부 되었습니다.");
      			redirectAttributes.addFlashAttribute("alert_data",postMap);
              	return "redirect:/index.do";
        		}
      		//
      		
      		//로그인정보 세션 저장
      		request.getSession().setAttribute("LoginVO", resultVO);
        	//접속 시간 기록
        	resultVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
        	resultVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
        	loginService.updateLoginHistory(resultVO);
        	//
        	
        	StringBuilder log_dscrp = new StringBuilder();
        	StringBuilder tbl_inf = new StringBuilder();
        	StringBuilder tbl_sn = new StringBuilder();
        	log_dscrp.append("[관리자메인-로그인]");
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
        	
        	//return "forward:/adm/index.do";
			return "redirect:/adm/index.do";
          	
        } else {
        	        	
        	//로그인 실패 횟수 증가
        	loginService.updateLoginRetry(loginVO);
        	
        	LOGGER.debug("로그인정보 불일치로 로그인 실패로 알림처리");
        	if(retryCnt > 5) retryCnt = 5;
    		Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("return_url", "");
			postMap.put("title", "");
        	postMap.put("message",  "아이디 또는 비밀번호가 일치하지 않습니다.<br><span class=\"red-600\">(로그인 실패 "+retryCnt+"회)</span><br>로그인 5회 실패시, 해당 계정의 권한이 제한됩니다.");
        	postMap.put("closebtn", "N");
			postMap.put("type", "");//alert
			postMap.put("timer", 0);//0:없어지지않음
			model.addAttribute("alert_data",postMap);
			
        	//model.addAttribute("message", "fail");
        	return "adm/member/login";
        }
    }
	//관리자(교육센터) 로그아웃  ------------------------------------------------
    @RequestMapping(value="/adm/member/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
    	
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");

    	StringBuilder log_dscrp = new StringBuilder();
    	StringBuilder tbl_inf = new StringBuilder();
    	StringBuilder tbl_sn = new StringBuilder();
    	log_dscrp.append("[관리자메인-로그아웃]");
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
    	//return "forward:/adm/member/login.do";
    	return "redirect:/adm/member/login.do";
    }
	
    
    /**
     * 권한 관리
     * */
    //관리자(종합센터) 권한관리 페이지 ------------------------------------------------
  	@RequestMapping(value = "/adm/member/list_lv.do")
  	public String memberListLevel(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
  		
  		mAdmMemberVO.setNotUsedPagination(true);
  		
  		//등급 그룹 조회
  		mAdmMemberVO.setMBR_LV_ID("2");
  		List<AdmMemberVO> list_lv2 = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		mAdmMemberVO.setMBR_LV_ID("3");
  		List<AdmMemberVO> list_lv3 = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		mAdmMemberVO.setMBR_LV_ID("4");
  		List<AdmMemberVO> list_lv4 = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		model.addAttribute("list_lv2",list_lv2);
  		model.addAttribute("list_lv3",list_lv3);
  		model.addAttribute("list_lv4",list_lv4);
  		
  		//직급 코드 조회
  		CodeSetVO mCodeSetVO = new CodeSetVO();
  		mCodeSetVO.setCD_MASTER_ID("CID00003");
  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
  		model.addAttribute("list_position_cd",list_position_cd);
  		
  		//종합센터
  		mAdmMemberVO.setMBR_POSITION_CD("not null");
  		mAdmMemberVO.setMBR_GRP_1_ST("Y");
  		mAdmMemberVO.setMBR_GRP_2_ST("");
  		mAdmMemberVO.setMBR_GRP_3_ST("");
  		mAdmMemberVO.setMBR_GRP_4_ST("");
  		
  		mAdmMemberVO.setMBR_LV_ID("2");
  		List<AdmMemberVO> list_lv2_g1_center = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		mAdmMemberVO.setMBR_LV_ID("3");
  		List<AdmMemberVO> list_lv3_g1_manager = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		mAdmMemberVO.setMBR_LV_ID("4");
  		List<AdmMemberVO> list_lv4_g1_outsider = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		model.addAttribute("list_lv2_g1_center",list_lv2_g1_center);
  		model.addAttribute("list_lv3_g1_manager",list_lv3_g1_manager);
  		model.addAttribute("list_lv4_g1_outsider",list_lv4_g1_outsider);
  		
  		//교육센터
  		mAdmMemberVO.setMBR_POSITION_CD("not null");
  		mAdmMemberVO.setMBR_GRP_1_ST("");
  		mAdmMemberVO.setMBR_GRP_2_ST("Y");
  		mAdmMemberVO.setMBR_GRP_3_ST("");
  		mAdmMemberVO.setMBR_GRP_4_ST("");
  		
  		mAdmMemberVO.setMBR_LV_ID("2");
  		List<AdmMemberVO> list_lv2_g2_center = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		mAdmMemberVO.setMBR_LV_ID("3");
  		List<AdmMemberVO> list_lv3_g2_manager = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		mAdmMemberVO.setMBR_LV_ID("4");
  		List<AdmMemberVO> list_lv4_g2_outsider = memberService.get_adm_member_auth_list(mAdmMemberVO);
  		
  		model.addAttribute("list_lv2_g2_center",list_lv2_g2_center);
  		model.addAttribute("list_lv3_g2_manager",list_lv3_g2_manager);
  		model.addAttribute("list_lv4_g2_outsider",list_lv4_g2_outsider);
  		
  		return "adm/member/list_lv";
  	}
  	
  	//관리자(종합센터) 권한관리 - 회원 추가 ------------------------------------------------
  	@RequestMapping(value = "/adm/member/auth/add.do", method = RequestMethod.POST)
  	public ModelAndView seadm_member_auth_add(@RequestParam("modal_title") String modal_title,
  			@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("adm/member/member_add");
  		mModelAndView.addObject("AdmMemberVO",mAdmMemberVO);
  		mModelAndView.addObject("modal_title",modal_title);
  		return mModelAndView;
  	}
  	
  	//관리자(종합센터) 권한관리 - 회원권한삭제 ------------------------------------------------
  	@RequestMapping(value = "/adm/member/auth/delete_act.do", method = RequestMethod.POST)
  	public @ResponseBody String seadm_member_auth_delete_act(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		JSONObject data = new JSONObject();	
  		try {
  			StringBuilder log_dscrp = new StringBuilder();
  			StringBuilder log_msg = new StringBuilder();
  			StringBuilder tbl_inf = new StringBuilder();
  			StringBuilder tbl_sn = new StringBuilder();			
  			
  			
  			AdmMemberVO chkAdmMemberVO = new AdmMemberVO();
  			chkAdmMemberVO.setMBR_ID(mAdmMemberVO.getMBR_ID());
  			chkAdmMemberVO = memberService.get_adm_member_auth_info(chkAdmMemberVO);
  			if(chkAdmMemberVO==null || chkAdmMemberVO.getMBR_ID()==null || chkAdmMemberVO.getMBR_ID().length()==0) {
  				data.put("error", "1");
  				data.put("msg", "존재하지 않는 회원입니다.");	
  			} else {
  				if(Integer.parseInt(chkAdmMemberVO.getMBR_LV_ID()) <= Integer.parseInt(loginVO.getMBR_LV_ID()) && !loginVO.getMBR_ID().equals("rhadmin")) {
  					data.put("error", "1");
  					data.put("msg", "나와 같은 등급이거나 높은 등급의 회원을 변경할 권한이 없습니다.");	
  				} else {
  					
  					log_msg.append(",(기존)MBR_LV ID:"+chkAdmMemberVO.getMBR_LV_ID()+
  							",MBR_POSITION_CD"+chkAdmMemberVO.getMBR_POSITION_CD()+
  							",MBR_GRP_1_ST"+chkAdmMemberVO.getMBR_GRP_1_ST()+
  							",MBR_GRP_2_ST"+chkAdmMemberVO.getMBR_GRP_2_ST()+
  							",MBR_GRP_3_ST"+chkAdmMemberVO.getMBR_GRP_3_ST()+
  							",MBR_GRP_4_ST"+chkAdmMemberVO.getMBR_GRP_4_ST()
  							);
  					
  					//직급 코드 조회
  		  	  		CodeSetVO mCodeSetVO = new CodeSetVO();
  		  	  		mCodeSetVO.setCD_MASTER_ID("CID00003");
  		  	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);

  		  	  		
  					log_dscrp.append("[종합센터관리자-권한관리-회원권한삭제]");
  					log_dscrp.append("[");
  					
  					log_msg.append("[MBR_ID:"+mAdmMemberVO.getMBR_ID());
  					log_dscrp.append("이름:"+mAdmMemberVO.getMBR_NM()+"(아이디:"+mAdmMemberVO.getMBR_ID()+")");
  					
  					String MBR_LV_ID = mAdmMemberVO.getMBR_LV_ID();
  					String MBR_GRP_ID = mAdmMemberVO.getMBR_GRP_ID();
  					String MBR_POSITION_CD = mAdmMemberVO.getMBR_POSITION_CD();
  					if(MBR_GRP_ID.equals("1")) { //종합센터
  						chkAdmMemberVO.setMBR_GRP_1_ST("N");
  						log_dscrp.append("|낚시누리");
  					} else if(MBR_GRP_ID.equals("2")) { //교육센터
  						log_dscrp.append("|낚시전문교육");
  						chkAdmMemberVO.setMBR_GRP_2_ST("N");
  					} else if(MBR_GRP_ID.equals("3")) { //
  						chkAdmMemberVO.setMBR_GRP_3_ST("N");
  						log_dscrp.append("|3");
  					} else if(MBR_GRP_ID.equals("4")) { //
  						chkAdmMemberVO.setMBR_GRP_4_ST("N");
  						log_dscrp.append("|4");
  					}
  					if(MBR_GRP_ID.equals("")) { 
  						if(MBR_LV_ID.equals("2")) {
  							log_dscrp.append("|1등급 그룹 모두 해제");	
  						} else if(MBR_LV_ID.equals("3")) {
  							log_dscrp.append("|2등급 그룹 모두 해제");
  						} else if(MBR_LV_ID.equals("4")) {
  							log_dscrp.append("|3등급 그룹 모두 해제");
  						}
  						chkAdmMemberVO.setMBR_LV_ID("10");
  						chkAdmMemberVO.setMBR_POSITION_CD("");
  						chkAdmMemberVO.setMBR_GRP_1_ST("N");
  						chkAdmMemberVO.setMBR_GRP_2_ST("N");
  						chkAdmMemberVO.setMBR_GRP_3_ST("N");
  						chkAdmMemberVO.setMBR_GRP_4_ST("N");	
  						log_dscrp.append("-하위 권한 모두 제거");
  					} else {
  						if(MBR_LV_ID.equals("4")) {
	  						String CD_NM = "";
	  						for(int j=0;j<list_position_cd.size(); j++) {
	  							CodeSetVO codeSetVO = (CodeSetVO)list_position_cd.get(j);
	  							if(codeSetVO.getCD_ID().equals(MBR_POSITION_CD)) {
	  								CD_NM = codeSetVO.getCD_NM();
	  								break;
	  							}
	  						}
	  						log_dscrp.append("-"+CD_NM+" 권한제거");
	  							
	  					} else {
	  						if(MBR_LV_ID.equals("2")) {
	  							log_dscrp.append("-총관리자 권한제거");	
	  						} else if(MBR_LV_ID.equals("3")) {
	  							log_dscrp.append("-공단운영자 권한제거");
	  						}
	  					}
  						chkAdmMemberVO.setMBR_POSITION_CD("");
  					}
  					
  					log_msg.append("=>(변경)MBR_LV ID:"+mAdmMemberVO.getMBR_LV_ID()+
  							",MBR_POSITION_CD"+chkAdmMemberVO.getMBR_POSITION_CD()+
  							",MBR_GRP_1_ST"+chkAdmMemberVO.getMBR_GRP_1_ST()+
  							",MBR_GRP_2_ST"+chkAdmMemberVO.getMBR_GRP_2_ST()+
  							",MBR_GRP_3_ST"+chkAdmMemberVO.getMBR_GRP_3_ST()+
  							",MBR_GRP_4_ST"+chkAdmMemberVO.getMBR_GRP_4_ST()+"]"
  							);
  					memberService.set_adm_member_auth_mod(chkAdmMemberVO);
  					
  					data.put("error", "0");
  					data.put("msg", "처리되었습니다.");		
  					
  					log_dscrp.append("]");
  					tbl_inf.append("MBR_TB");
  					tbl_sn.append(mAdmMemberVO.getMBR_SN());
  					try {	
  						/**
  						 * LOG RECORED (로그기록)
  						 * */
  						LogRecordVO mEduLogRecordVO = new LogRecordVO();
  						log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(mAdmMemberVO));
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
  					
  				}
  			}
  			
  		} catch(Exception e) {
  			data.put("error", "1");
  			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
  		}
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;		
  	}
  	
  	//관리자(종합센터) 권한관리 - 회원권한추가 - 회원 목록 가져오기 ------------------------------------------------
  	@RequestMapping(value = "/adm/member/util/ajaxlist.do", method = RequestMethod.POST)
  	public ModelAndView ajax_member_list(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO,
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		
  		mAdmMemberVO.setPageUnit(10);//한번에 10명만 노출
  		
  		List<AdmMemberVO> list = null;
  		String returnUrl = "adm/member/member_ajax_list";
  				
  		/** pageing setting */
  		PaginationInfo paginationInfo = new PaginationInfo();
  		paginationInfo.setCurrentPageNo(mAdmMemberVO.getPageIndex());
  		paginationInfo.setRecordCountPerPage(mAdmMemberVO.getPageUnit());
  		paginationInfo.setPageSize(mAdmMemberVO.getPageSize());

  		mAdmMemberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
  		mAdmMemberVO.setLastIndex(paginationInfo.getLastRecordIndex());
  		mAdmMemberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
  					
  		mAdmMemberVO.setMBR_ST("Y");
  		list = memberService.get_adm_member_auth_list(mAdmMemberVO);			
  		int totCnt = memberService.get_adm_member_auth_list_totcnt(mAdmMemberVO);
  		paginationInfo.setTotalRecordCount(totCnt);
  		
  		if(Integer.parseInt(mAdmMemberVO.getMBR_LV_ID()) <= Integer.parseInt(loginVO.getMBR_LV_ID()) && !loginVO.getMBR_ID().equals("rhadmin")) {
  			//같거나 높은 등급은 추가할 권한이 없음
  			list = null;
  			paginationInfo.setTotalRecordCount(0);
  		}
  		
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName(returnUrl);
  		mModelAndView.addObject("paginationInfo", paginationInfo);
  		mModelAndView.addObject("list",list);
  		mModelAndView.addObject("mbrids",mAdmMemberVO.getMBR_ID());
  		
  		return mModelAndView;
  	}
  	
  	//관리자(종합센터) 권한관리 - 회원권한추가 - 처리 로직 ------------------------------------------------
  	@RequestMapping(value="/adm/member/auth/write_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_member_auth_write(@ModelAttribute("AdmMemberVO") AdmMemberVO mAdmMemberVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		JSONObject data = new JSONObject();	
  		try {
  			
  			//직급 코드 조회
  	  		CodeSetVO mCodeSetVO = new CodeSetVO();
  	  		mCodeSetVO.setCD_MASTER_ID("CID00003");
  	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);

  	  		
  			StringBuilder log_dscrp = new StringBuilder();
  			StringBuilder log_msg = new StringBuilder();
  			StringBuilder tbl_inf = new StringBuilder();
  			StringBuilder tbl_sn = new StringBuilder();
  			log_dscrp.append("[종합센터관리자-권한관리-회원권한변경]");
  			
  			int successcnt = 0;
  			int failcnt = 0;
  			String MBR_LV_ID = mAdmMemberVO.getMBR_LV_ID();
  			String MBR_GRP_ID = mAdmMemberVO.getMBR_GRP_ID();
  			String MBR_POSITION_CD = mAdmMemberVO.getMBR_POSITION_CD();
  			log_msg.append("[처리할권한:"+MBR_GRP_ID+"]");
  			log_msg.append("[처리할레벨:"+MBR_LV_ID+"]");
  			log_msg.append("[처리할직급:"+MBR_POSITION_CD+"]");
  			
  			String[] mbrids = mAdmMemberVO.getMBR_ID().replaceAll("\\s","").split(",");
  			for(int i=0; i<mbrids.length; i++) {
  				String traget_mbr_id = mbrids[i];
  				LOGGER.debug("현재 회원 처리 중 : " + traget_mbr_id);
  				AdmMemberVO chkAdmMemberVO = new AdmMemberVO();
  				chkAdmMemberVO.setMBR_ID(traget_mbr_id);
  				chkAdmMemberVO = memberService.get_adm_member_auth_info(chkAdmMemberVO);
  				log_msg.append("[MBR_ID:"+traget_mbr_id);
  				log_dscrp.append("[");
  				if(chkAdmMemberVO==null || chkAdmMemberVO.getMBR_ID()==null || chkAdmMemberVO.getMBR_ID().length()==0) {
  					log_msg.append(",변경실패(존재하지않는회원)]");
  					failcnt++;
  				} else {
  					
  					String PREV_MBR_LV_ID = chkAdmMemberVO.getMBR_LV_ID(); 
  					String PREV_MBR_POSITION_CD = chkAdmMemberVO.getMBR_POSITION_CD();
  					String PREV_MBR_GRP_1_ST = chkAdmMemberVO.getMBR_GRP_1_ST();
  					String PREV_MBR_GRP_2_ST = chkAdmMemberVO.getMBR_GRP_2_ST();
  					String PREV_MBR_GRP_3_ST = chkAdmMemberVO.getMBR_GRP_3_ST();
  					String PREV_MBR_GRP_4_ST = chkAdmMemberVO.getMBR_GRP_4_ST();
  					
  					
  					log_dscrp.append("이름:"+chkAdmMemberVO+"(아이디:"+chkAdmMemberVO.getMBR_ID()+")");
  					chkAdmMemberVO.setMBR_LV_ID(MBR_LV_ID);
  					chkAdmMemberVO.setMBR_GRP_ID(MBR_GRP_ID);
  					if(MBR_GRP_ID.equals("1")) { //종합센터
  						log_dscrp.append("|낚시누리");
  						chkAdmMemberVO.setMBR_GRP_1_ST("Y");  						
  					} else if(MBR_GRP_ID.equals("2")) { //교육센터
  						chkAdmMemberVO.setMBR_GRP_2_ST("Y");
  						log_dscrp.append("|낚시전문교육");
  					} else if(MBR_GRP_ID.equals("3")) { //
  						chkAdmMemberVO.setMBR_GRP_3_ST("Y");
  						log_dscrp.append("|3");
  					} else if(MBR_GRP_ID.equals("4")) { //
  						chkAdmMemberVO.setMBR_GRP_4_ST("Y");
  						log_dscrp.append("|4");
  					}
  					if(MBR_GRP_ID.equals("")) { //새로운등급부여등록
  						if(MBR_LV_ID.equals("2")) {
  							log_dscrp.append("|1등급 그룹 등록");
  						} else if(MBR_LV_ID.equals("3")) {
  							log_dscrp.append("|2등급 그룹 등록");
  						} else if(MBR_LV_ID.equals("4")) {
  							log_dscrp.append("|3등급 그룹 등록");
  						}
  						chkAdmMemberVO.setMBR_POSITION_CD("");
  						
  						//기존 권한이 있을 경우 기록.
  						if(PREV_MBR_LV_ID.equals("2")) {
  							log_dscrp.append("(이전 1등급그룹해제");
  							if(PREV_MBR_LV_ID.equals("4")) {
  		  						String CD_NM = "";
  		  						for(int j=0;j<list_position_cd.size(); j++) {
  		  							CodeSetVO codeSetVO = (CodeSetVO)list_position_cd.get(j);
  		  							if(codeSetVO.getCD_ID().equals(PREV_MBR_POSITION_CD)) {
  		  								CD_NM = codeSetVO.getCD_NM();
  		  								break;
  		  							}
  		  						}
  		  						log_dscrp.append("-"+CD_NM+" 권한제거)");
  		  					} else {
  		  						if(PREV_MBR_LV_ID.equals("2")) {
  		  							log_dscrp.append("-총관리자 권한제거)");	
  		  						} else if(PREV_MBR_LV_ID.equals("3")) {
  		  							log_dscrp.append("-공단운영자 권한제거)");
  		  						}
  		  					}
  						} else if(PREV_MBR_LV_ID.equals("3")) {
  							log_dscrp.append("(이전 2등급그룹해제");
  							if(PREV_MBR_LV_ID.equals("4")) {
  		  						String CD_NM = "";
  		  						for(int j=0;j<list_position_cd.size(); j++) {
  		  							CodeSetVO codeSetVO = (CodeSetVO)list_position_cd.get(j);
  		  							if(codeSetVO.getCD_ID().equals(PREV_MBR_POSITION_CD)) {
  		  								CD_NM = codeSetVO.getCD_NM();
  		  								break;
  		  							}
  		  						}
  		  						log_dscrp.append("-"+CD_NM+" 권한제거)");
  		  					} else {
  		  						if(PREV_MBR_LV_ID.equals("2")) {
  		  							log_dscrp.append("-총관리자 권한제거)");	
  		  						} else if(PREV_MBR_LV_ID.equals("3")) {
  		  							log_dscrp.append("-공단운영자 권한제거)");
  		  						}
  		  					}
  						} else if(PREV_MBR_LV_ID.equals("4")) {
  							log_dscrp.append("(이전 3등급그룹해제");
  							if(PREV_MBR_LV_ID.equals("4")) {
  		  						String CD_NM = "";
  		  						for(int j=0;j<list_position_cd.size(); j++) {
  		  							CodeSetVO codeSetVO = (CodeSetVO)list_position_cd.get(j);
  		  							if(codeSetVO.getCD_ID().equals(PREV_MBR_POSITION_CD)) {
  		  								CD_NM = codeSetVO.getCD_NM();
  		  								break;
  		  							}
  		  						}
  		  						log_dscrp.append("-"+CD_NM+" 권한제거)");
  		  					} else {
  		  						if(PREV_MBR_LV_ID.equals("2")) {
  		  							log_dscrp.append("-총관리자 권한제거)");	
  		  						} else if(PREV_MBR_LV_ID.equals("3")) {
  		  							log_dscrp.append("-공단운영자 권한제거)");
  		  						}
  		  					}
  						} else {
  							log_dscrp.append("(신규)");
  						}
  						//
  						
  					} else { //세부권한부여
	  					if(MBR_LV_ID.equals("4")) {
	  						String CD_NM = "";
	  						for(int j=0;j<list_position_cd.size(); j++) {
	  							CodeSetVO codeSetVO = (CodeSetVO)list_position_cd.get(j);
	  							if(codeSetVO.getCD_ID().equals(MBR_POSITION_CD)) {
	  								CD_NM = codeSetVO.getCD_NM();
	  								break;
	  							}
	  						}
	  						log_dscrp.append("-"+CD_NM+" 권한부여");
	  						chkAdmMemberVO.setMBR_POSITION_CD(MBR_POSITION_CD);	
	  					} else {
	  						if(MBR_LV_ID.equals("2")) {
	  							log_dscrp.append("-총관리자 권한부여");	
	  						} else if(MBR_LV_ID.equals("3")) {
	  							log_dscrp.append("-공단운영자 권한부여");
	  						}
	  						chkAdmMemberVO.setMBR_POSITION_CD("default");
	  					}
  					}
  					log_msg.append(",(기존)MBR_LV ID:"+PREV_MBR_LV_ID+
  							",MBR_POSITION_CD"+PREV_MBR_POSITION_CD+
  							",MBR_GRP_1_ST"+PREV_MBR_GRP_1_ST+
  							",MBR_GRP_2_ST"+PREV_MBR_GRP_2_ST+
  							",MBR_GRP_3_ST"+PREV_MBR_GRP_3_ST+
  							",MBR_GRP_4_ST"+PREV_MBR_GRP_4_ST
  							);
  					log_msg.append("=>(변경)MBR_LV ID:"+mAdmMemberVO.getMBR_LV_ID()+
  							",MBR_POSITION_CD"+chkAdmMemberVO.getMBR_POSITION_CD()+
  							",MBR_GRP_1_ST"+chkAdmMemberVO.getMBR_GRP_1_ST()+
  							",MBR_GRP_2_ST"+chkAdmMemberVO.getMBR_GRP_2_ST()+
  							",MBR_GRP_3_ST"+chkAdmMemberVO.getMBR_GRP_3_ST()+
  							",MBR_GRP_4_ST"+chkAdmMemberVO.getMBR_GRP_4_ST()+"]"
  							);
  					successcnt++;
  					memberService.set_adm_member_auth_mod(chkAdmMemberVO);
  				}
  				log_dscrp.append("]");
  			}
  			log_msg.append("[결과-성공:"+successcnt+"건,실패:"+failcnt+"건]");
  			
  			data.put("error", "0");
  			data.put("msg", "추가되었습니다.");		
  			
  			tbl_inf.append("MBR_TB");
  			try {	
  				/**
  				 * LOG RECORED (로그기록)
  				 * */
  				LogRecordVO mEduLogRecordVO = new LogRecordVO();
  				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(mAdmMemberVO));
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
  			
  		} catch(Exception e) {
  			data.put("error", "1");
  			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
  		}
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
    
  	
  	//아이디 검증하기 & 취약한 패스워드 검증
  	@RequestMapping(value="/adm/member/searchId.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_member_search_id_act(@ModelAttribute("loginVO") LoginVO loginVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {
  		String MBR_SN = "";
  		try {
			MBR_SN = (String)request.getSession().getAttribute("MBR_SN");
		} catch (Exception e) {
			LOGGER.debug("empty MBR_SN");
			MBR_SN = "";
		}
  		
		LOGGER.debug("MBR_SN = " + MBR_SN);
  		JSONObject data = new JSONObject();
  		if(loginVO==null || loginVO.getMBR_ID().length()==0) {
  			data.put("error", "2");
  			data.put("msg", "비정상적인 접근입니다.");	
  		} else {
  			LoginVO chkLoginVO = loginService.searchId(loginVO);
  			if(MBR_SN==null || MBR_SN.length()==0) {
	  			if(chkLoginVO==null || chkLoginVO.getMBR_ID() == null || chkLoginVO.getMBR_ID().length()==0) {
	  				boolean isChangePwd = false;
	  				try { 
	  					isChangePwd = (boolean)request.getSession().getAttribute("isChangePwd");
	  				} catch(Exception e) { 

	  				}
	  				if(isChangePwd){
		  				String mbrPwd = loginVO.getMBR_PWD();
		  				if(mbrPwd.length() < 10 || mbrPwd.length() > 20){
		  					data.put("error", "1");
			  	  			data.put("msg", "비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
		  				} else if(!mbrPwd.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{10,20}$")){
		  					data.put("error", "1");
			  	  			data.put("msg", "비밀번호는 공백없이 영문,숫자,특수문자[@,!,%,*,#]를 혼합하여 입력해주세요.");
		  				} else {
		  					data.put("error", "0");
			  	  			data.put("msg", "사용이 가능 한 아이디 입니다.");
		  				}
	  				}else{
	  					data.put("error", "0");
		  	  			data.put("msg", "사용이 가능 한 아이디 입니다.");
	  				}
	  			} else {
	  				data.put("error", "1");
	  	  			data.put("msg", "중복되는 아이디가 존재합니다.");
	  			}
  			} else {
  				loginVO.setMBR_SN(MBR_SN);
  				LoginVO chkSnLoginVO = loginService.searchSnInfo(loginVO);
  				if(chkSnLoginVO==null || chkSnLoginVO.getMBR_ID() == null || chkSnLoginVO.getMBR_ID().length()==0) {
  					data.put("error", "3");
  		  			data.put("msg", "비정상적인 접근입니다.");	
  				} else {
  					if(chkLoginVO==null || chkLoginVO.getMBR_ID() == null || chkLoginVO.getMBR_ID().length()==0) {
  						
  						String mbrPwd = loginVO.getMBR_PWD();
  		  				if(mbrPwd.length() < 10 || mbrPwd.length() > 20){
  		  					data.put("error", "1");
  			  	  			data.put("msg", "비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
  		  				} else if(!mbrPwd.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{10,20}$")){
  		  					data.put("error", "1");
  			  	  			data.put("msg", "비밀번호는 공백없이 영문,숫자,특수문자[@,!,%,*,#]를 혼합하여 입력해주세요.");
  		  				} else {
  		  					data.put("error", "0");
  			  	  			data.put("msg", "사용이 가능 한 아이디 입니다.");
  		  				}	
  		  			} else {
  		  				if(chkLoginVO.getMBR_ID().equals(chkSnLoginVO.getMBR_ID())) {
	  		  				data.put("error", "0");
	  		  	  			data.put("msg", "현재 아이디를 유지합니다.");
  		  				} else {
	  		  				data.put("error", "1");
	  		  	  			data.put("msg", "중복되는 아이디가 존재합니다.");
  		  				}
  		  			}
  				}
  			}
  		}
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
  	
  	
  	//관리자회원 이름,생년월일,연락처 중복 검증하기
  	@RequestMapping(value="/adm/member/searchAuthOverlayInfo.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_member_search_master_info_overlay_act(@ModelAttribute("loginVO") LoginVO loginVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {
  		String MBR_SN = "";
		try { MBR_SN = (String)request.getSession().getAttribute("MBR_SN"); } catch(Exception e) { }
		LOGGER.debug("MBR_SN = " + MBR_SN);
  		JSONObject data = new JSONObject();
  		if(loginVO==null) {
  			data.put("error", "2");
  			data.put("msg", "비정상적인 접근입니다.");	
  		} else {
  			loginVO.setMBR_SN(MBR_SN);
			int cnt = loginService.searchAuthOverlayInfo(loginVO);
			if(cnt > 0) {
				data.put("error", "1");
  	  			data.put("msg", "이름,생년월일,연락처(휴대폰번호)가 중복되는 회원정보가 존재합니다.");
  			} else {
  				data.put("error", "0");
  	  			data.put("msg", "사용이 가능 한 회원정보 입니다.");	
  			}			
  		}
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
  	
  	
  	//개인정보 보호 서약서 뷰
  	@RequestMapping(value="/adm/member/pledge_agree_ajax.do", method = RequestMethod.POST)
  	public String pledge_agree_ajax(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
  			HttpServletRequest request, ModelMap model) throws Exception {
  		
  		return "adm/member/pledge_agree_ajax";
  	}
  	
  	//개인정보 보호 서약서 처리부
  	@RequestMapping(value="/adm/member/pledge_agree_ajax_act.do", method = RequestMethod.POST)
  	public @ResponseBody String pledge_agree_ajax_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
  			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
  		
  		Map<String,String> map = new HashMap<String, String>();
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		if(loginVO==null || loginVO.getMBR_ID()==null) {
  			LOGGER.debug("[사용자]개인정보 제3자 동의 팝업 출력 - 로그인정보 없음");
  			map.put("error", "0");
  			map.put("msg", "로그인이 필요한 서비스로 개인정보 제3자 제공 동의가 완료되지 않았습니다.");
  		} else {
  			try {
  				
  				if(eduMemberVO.getMBR_PLEDGE_ST() != null || eduMemberVO.getMBR_PLEDGE_ST().length() != 0 || eduMemberVO.getMBR_PLEDGE_ST().equals("N")){
  					eduMemberVO.setMBR_ID(loginVO.getMBR_ID());
  	  				eduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
  	  				eduMemberVO.setMBR_PLEDGE_ST(eduMemberVO.getMBR_PLEDGE_ST());
  	  				eduMemberService.set_edu_member_mod(eduMemberVO);
  	  				map.put("error", "1");
  	  				map.put("msg", "개인정보 보호 서약서를 제출하여 주셔서 감사합니다.");
  	  				request.getSession().setAttribute("MBR_PLEDGE_ST", eduMemberVO.getMBR_PLEDGE_ST());
  				} else {
  					map.put("error", "0");
  	  				map.put("msg", "일시적인 오류가 발생되었습니다.");
  				}
  				
  			}catch(Exception e) {
  				map.put("error", "0");
  				map.put("msg", "일시적인 오류가 발생되었습니다.");
  			}
  			
  		}
  		
  		JSONObject data = new JSONObject(map);
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
    
    
}


