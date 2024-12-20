package egovframework.eduadm.member.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.member.service.AdmCntnAuthorIpVO;
import egovframework.adm.member.service.AdmMemberService;
import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsMentVO;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.all.main.service.KakaoAlimTalkService;
import egovframework.all.main.service.KakaoAlimTalkVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.com.utl.slm.EgovHttpSessionBindingListener;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.certificate.web.CreateCertificateToHtmlData;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduAdmMbrHpChngVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.main.service.EduMbrRemindersVO;
import egovframework.eduadm.member.service.EduExcelUploadVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.member.service.LogAdmAuthVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.naksinuri_original.let.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicFileMngUtil;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduMemberController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduMemberController.class);

	/** LoginService */
	@Resource(name = "loginService")
	private LoginService loginService;

	/** EgovLogService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	/** EgovEducenterService */
	@Resource(name = "eduCenterService")
	private EduCenterService eduCenterService;

	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;

	/** EduCategoryService */
	@Resource(name = "eduCategoryService")
	private EduCategoryService eduCategoryService;

	/** EduCurriculumService */
	@Resource(name = "eduCurriculumService")
	private EduCurriculumService eduCurriculumService;

	/** EduMyHistoryService */
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;

	/** EduTrainingDataService */
	@Resource(name = "eduTrainingDataService")
	private EduTrainingDataService eduTrainingDataService;

	/** EduCertificateService */
	@Resource(name = "eduCertificateService")
	private EduCertificateService eduCertificateService;

	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "smsManagerService")
	private SmsManagerService smsManagerService;

	@Resource(name = "admMemberService")
	private AdmMemberService admMemberService;

	@Resource(name = "kakaoAlimTalkService")
	private KakaoAlimTalkService kakaoAlimTalkService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	// 관리자(교육센터) 로그인 ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/login.do")
	public String edu_member_login(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, SessionStatus status,
			HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		/*
		 * if(loginVO!=null) { return "redirect:/eduadm/index.do"; }
		 */

		/*
		 * String ip = PublicUtils.getClientIpAddr(request); String path = "";
		 * 
		 * AdmCntnAuthorIpVO admCntnAuthorIpVO = new AdmCntnAuthorIpVO();
		 * admCntnAuthorIpVO.setCNTN_AUTHOR_IP(ip); admCntnAuthorIpVO =
		 * admMemberService.get_cntn_author_ip_info(admCntnAuthorIpVO); // 접속 권한
		 * IP 정보
		 * 
		 * StringBuilder log_dscrp = new StringBuilder(); StringBuilder log_msg
		 * = new StringBuilder(); StringBuilder tbl_inf = new StringBuilder();
		 * StringBuilder tbl_sn = new StringBuilder();
		 * 
		 * if(admCntnAuthorIpVO != null &&
		 * admCntnAuthorIpVO.getCNTN_AUTHOR_IP().length() != 0) {
		 * 
		 * log_dscrp.append("[ eduadm 관리자 페이지 " +
		 * admCntnAuthorIpVO.getCNTN_AUTHOR_IP() + " 접속 - " + ip + " ]"); path =
		 * "eduadm/member/login";
		 * 
		 * model.addAttribute("kcbokcert_cpid",propertiesService.getString(
		 * "KcbOkCert.cpid"));
		 * model.addAttribute("kcbokcert_licensepath",propertiesService.
		 * getString("KcbOkCert.licensePath"));
		 * model.addAttribute("kcbokcert_sitenm",propertiesService.getString(
		 * "KcbOkCert.siteNm"));
		 * model.addAttribute("kcbokcert_siteurl",propertiesService.getString(
		 * "KcbOkCert.siteUrl"));
		 * 
		 * } else { log_dscrp.append("[ 허용 ip외 eduadm 관리자 페이지 접속시도 - " + ip +
		 * " ]"); LOGGER.debug("허용 ip외 eduadm 관리자 페이지 접속시도  : " + ip); path =
		 * "redirect:/index.do"; } log_msg.append(admCntnAuthorIpVO);
		 * tbl_inf.append("CNTN_AUTHOR_IP_TB"); tbl_sn.append(ip);
		 * 
		 * try {
		 *//**
			 * LOG RECORED (로그기록)
			 **//*
				 * LogRecordVO mEduLogRecordVO = new LogRecordVO();
				 * mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.
				 * encodingFromObjectToJson(admCntnAuthorIpVO));
				 * mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				 * mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				 * mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				 * //mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
				 * //mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
				 * mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request
				 * )); logRecordService.set_log_data(mEduLogRecordVO,request); }
				 * catch(Exception e) { LOGGER.debug("[fail log record] "
				 * +e.toString()); }
				 * 
				 * 
				 * return path;
				 */

		model.addAttribute("kcbokcert_cpid", propertiesService.getString("KcbOkCert.cpid"));
		model.addAttribute("kcbokcert_licensepath", propertiesService.getString("KcbOkCert.licensePath"));
		model.addAttribute("kcbokcert_sitenm", propertiesService.getString("KcbOkCert.siteNm"));
		model.addAttribute("kcbokcert_siteurl", propertiesService.getString("KcbOkCert.siteUrl"));

		return "eduadm/member/login";
	}

	// 관리자(교육센터) 로그인 - 인증 -------------------------------------------------
	@RequestMapping(value = "/eduadm/member/login_act.do")
	public String edu_member_login_act(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		System.out.println("로그인세션_member");
		model.addAttribute("kcbokcert_cpid", propertiesService.getString("KcbOkCert.cpid"));
		model.addAttribute("kcbokcert_licensepath", propertiesService.getString("KcbOkCert.licensePath"));
		model.addAttribute("kcbokcert_sitenm", propertiesService.getString("KcbOkCert.siteNm"));
		model.addAttribute("kcbokcert_siteurl", propertiesService.getString("KcbOkCert.siteUrl"));

		if (loginVO == null || loginVO.getMBR_ID() == null || loginVO.getMBR_ID().length() == 0) {
			LOGGER.debug("비정상적인 접근");
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "비정상적인 접근으로 거부되었습니다.");
			postMap.put("return_url", "");
			postMap.put("type", "alert");
			postMap.put("title", "알림");
			postMap.put("closebtn", "확인");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/eduadm/member/login.do";
		}

		// 김현태 지자체 공휴일 차단 테스트
		if (loginVO.getMBR_ID().equals("htkim1128")) {
			System.out.println("지자체 로그인 체크 시작");

			Calendar now = Calendar.getInstance();
			int currentYear = now.get(Calendar.YEAR);
			int currentMonth = now.get(Calendar.MONTH) + 1;
			int currentDay = now.get(Calendar.DAY_OF_MONTH);

			String Year = Integer.toString(currentYear);
			String Month = Integer.toString(currentMonth);
			String Day = Integer.toString(currentDay);

			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=%2BXYGAVzjF7SyzCmlBHh%2FMbkrQT%2FFwRKSwc%2B9zNpSK%2BoMasrnlmzaYdULhj%2BwZz0UqExf6HMuW1COlF9ntbwF8w%3D%3D"); /*
																																	 * Service
																																	 * Key
																																	 */
			urlBuilder.append(
					"&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode(Year, "UTF-8")); /* 연 */
			urlBuilder.append(
					"&" + URLEncoder.encode("solMonth", "UTF-8") + "=" + URLEncoder.encode(Month, "UTF-8")); /* 월 */
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;

			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();

			// System.out.println(sb.toString()); api 반환값 프린트

			// API 응답 데이터를 XML로 파싱
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(sb.toString())));

			// <locdate> 요소 추출
			NodeList locdateElements = document.getElementsByTagName("locdate");
			List<String> locdateList = new ArrayList<>();

			for (int i = 0; i < locdateElements.getLength(); i++) {
				Element locdateElement = (Element) locdateElements.item(i);
				String locdateValue = locdateElement.getTextContent(); // locdate
																		// 값을
																		// 문자열로
																		// 가져옴
				locdateList.add(locdateValue);
			}

			// 현재 날짜와 비교
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String currentDate = Year + Month + Day;
			// SELECT * FROM mbr_tb where mbr_nm='김현태'; +지자체인경우
			/*
			 * for (String locdateValue : locdateList) { if
			 * (locdateValue.equals("20231009")) { System.out.println(
			 * "locdate와 현재 날짜가 일치합니다: " + locdateValue); } else {
			 * System.out.println("locdate와 현재 날짜가 일치하지 않습니다: " + locdateValue);
			 * } }
			 */
			String result = "weekday";

			for (String locdateValue : locdateList) {
				if (locdateValue.equals(currentDate)) {

					result = "holiday"; // 하나라도 일치하면 holiday로 변경
					break; // 일치하는 것을 찾았으므로 루프를 빠져나감
				}
			}

			System.out.println("오늘은: " + result);

			if (result.equals("holiday")) {

				System.out.println("휴일일경우 못지나감");

				Map<String, Object> postMap = new HashMap<String, Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
				postMap.put("message", "공휴일은 로그인이 불가합니다.<br>평일에 로그인 해주세요.");
				postMap.put("closebtn", "N");
				postMap.put("type", "");// alert
				postMap.put("timer", 0);// 0:없어지지않음
				model.addAttribute("alert_data", postMap);

				return "eduadm/member/login";

			}

			System.out.println("아닐경우 빠져나옴");
		}
		// 지자체 공휴일 차단 테스트 end

		System.out.println(loginVO.getMBR_LV_ID());// LV값이안넘어옴

		// 본인인증 후 ㅂㅁ 변경이 필요한지 검증
		LoginVO chkAdmLoginVO = loginService.actionChkAdmLogin(loginVO);
		if (chkAdmLoginVO != null && chkAdmLoginVO.getMBR_ID() != null && !chkAdmLoginVO.getMBR_ID().equals("")
				&& (chkAdmLoginVO.getMBR_PWD() == null || chkAdmLoginVO.getMBR_PWD().length() == 0)) {// ㅂㅁ가
																										// 없는
																										// 경우
																										// 본인인증
																										// 처리
			LOGGER.debug("본인인증 후 ㅂㅁ 변경이 필요한 관리자!");
			request.getSession().setAttribute("isAlertData", true);
			request.getSession().setAttribute("chkAdmLoginVO", chkAdmLoginVO);
			return "redirect:/adm/member/modifyAdmPwd.do";
		}
		loginVO.setMBR_PWD(EgovFileScrty.encryptPassword(loginVO.getMBR_PWD(), loginVO.getMBR_ID()));
		LoginVO resultVO = loginService.actionLogin(loginVO);

		// 로그인 시도 횟수 검증
		LoginVO retryLoginVO = loginService.retryLogin(loginVO);
		if (retryLoginVO == null) {// 잘못된 아이디일경우 '에러가 발생했습니다!'화면 나옴 =====
									// 2020.06.01 이수인팀장님 지시로 추가
			return "redirect:/eduadm/member/login.do";
		}
		// 관리자 계정 여부 확인
		if (retryLoginVO.getMBR_LV_ID().equals("10")) {
			LOGGER.debug("접근 할 권한이 없습니다.");
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "비정상적인 접근으로 거부 되었습니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/index.do";
		}
		//
		int retryCnt = 0;
		try {
			retryCnt = Integer.parseInt(retryLoginVO.getMBR_RETRY_LOGIN_CNT());
		} catch (Exception e) {
			LOGGER.debug("존재하지 않는 사용자 정보");
			retryCnt = 0;
		}
		LOGGER.debug("로그인 횟수 : " + retryCnt);
		long curtime = new Date().getTime();
		if (retryCnt >= 5) {
			String retryDt = retryLoginVO.getMBR_RETRY_LOGIN_DT();
			LOGGER.debug("로그인 시도 시간차 : " + (curtime - mPublicUtils.changeGetTime(retryDt, "yyyy-MM-dd HH:mm:ss")));
			int locktime = 10 * 60 * 1000;
			if (curtime - mPublicUtils.changeGetTime(retryDt, "yyyy-MM-dd HH:mm:ss") < locktime) {
				/*
				 * LOGGER.debug("로그인 횟수 5회 초과 10분 잠금"); Map<String, Object>
				 * postMap = new HashMap<String,Object>();
				 * postMap.put("return_url", ""); postMap.put("title", "");
				 * postMap.put("message", "로그인 시도 횟수 초과로 10분간	 로그인이 제한됩니다.");
				 * postMap.put("closebtn", "N"); postMap.put("type", "");//alert
				 * postMap.put("timer", locktime);//0:없어지지않음
				 * model.addAttribute("alert_data",postMap); return
				 * "eduadm/member/login";
				 */
				LOGGER.debug("로그인 횟수 5회 초과 관리자 ㅂㅁ 초기화");
				loginService.actionLoginLockClearPwd(retryLoginVO);
				Map<String, Object> postMap = new HashMap<String, Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
				postMap.put("message", "로그인 시도 횟수 초과로 접근이 차단되었습니다.<br>다음 로그인부터 본인인증을 통해 비밀번호를 재설정 해야 사용이 가능합니다.");
				postMap.put("closebtn", "N");
				postMap.put("type", "");// alert
				postMap.put("timer", locktime);// 0:없어지지않음
				model.addAttribute("alert_data", postMap);
			}
		}
		// End 로그인 시도 횟수 검증

		// 사용기간 체크
		if (retryLoginVO.getMBR_USG_DT() != null) {
			String MBR_USG_DT = retryLoginVO.getMBR_USG_DT();
			// int locktime = 10*60*1000;
			if (curtime >= mPublicUtils.changeGetTime(MBR_USG_DT, "yyyy-MM-dd HH:mm:ss")) {

				LOGGER.debug("사용기간 초과 관리자 : " + retryLoginVO.getMBR_ID());
				Map<String, Object> postMap = new HashMap<String, Object>();
				postMap.put("return_url", "");
				postMap.put("title", "");
				postMap.put("message", "사용기간이 지나 접근이 차단되었습니다.<br>관리자에게 문의하세요.");
				postMap.put("closebtn", "N");
				postMap.put("type", "");// alert
				// postMap.put("timer", locktime);//0:없어지지않음
				model.addAttribute("alert_data", postMap);
				return "eduadm/member/login";
			}
		}
		// End 사용기간 체크

		boolean loginPolicyYn = true;
		if (resultVO != null && resultVO.getMBR_ID() != null && !resultVO.getMBR_ID().equals("") && loginPolicyYn) {

			// 고광훈추가-로그인중복세션
			/*
			
			System.out.println("로그인 중복 세션 시작");

			System.out.println("resultVO.getMBR_ID():" + resultVO.getMBR_ID());

			LoginVO loginVO2 = (LoginVO) request.getSession().getAttribute("LoginVO");

			if (loginVO2 != null) {

				System.out.println("loginVO2:" + loginVO2.getMBR_ID());

				String id1 = resultVO.getMBR_ID();
				String id2 = loginVO2.getMBR_ID();

				if (id2.equals(id1)) {

					System.out.println("loginVO2_2:" + loginVO2.getMBR_ID());

					request.setAttribute("msg", "중복로그인 입니다. 확인해주세요");

					return "redirect:/eduadm/member/login.do";

				}

			}*/

			request.getSession().setAttribute("LoginVO", resultVO);
			EgovHttpSessionBindingListener listener = new EgovHttpSessionBindingListener();
			request.getSession().setAttribute(resultVO.getMBR_ID(), listener);

			System.out.println("테스트:" + resultVO.getMBR_ID());

			// 이관 된 기존회원으로 본인인증을 위한 회원정보 갱신이 필요한 사용자 검증
			if (resultVO.getMBR_PWD_ST().equals("1")) {
				LOGGER.debug("ㅂㅁ는 일치하나 본인인증 정보를 수집해야 하는 관리자!");
				request.getSession().setAttribute("isAlertData", true);
				request.getSession().setAttribute("chkAdmLoginVO", resultVO);
				return "redirect:/adm/member/modifyAdmInfo.do";
			}

			// kjw 센터장 본인인증 건너뜀
			if (resultVO.getMBR_ID().equals("fipa0851")) {
				LOGGER.debug("접속 시간 기록 ");

				request.getSession().setAttribute("LoginVO", resultVO);
				// 접속 시간 기록
				loginVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
				loginVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
				loginService.updateLoginHistory(loginVO);
				//

				return "redirect:/eduadm/index.do";
			}

			// 최고관리자 추가 - 계정 본인인증 건너뜀
			if (resultVO.getMBR_ID().equals("koy")) {
				LOGGER.debug("접속 시간 기록 ");

				request.getSession().setAttribute("LoginVO", resultVO);
				// 접속 시간 기록
				loginVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
				loginVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
				loginService.updateLoginHistory(loginVO);
				//

				return "redirect:/eduadm/index.do";
			}

			// 테스트 - smu 계정 본인인증 건너뜀
			if (resultVO.getMBR_ID().equals("dss427") || resultVO.getMBR_ID().equals("smucorp") ) {
				LOGGER.debug("접속 시간 기록 ");

				request.getSession().setAttribute("LoginVO", resultVO);
				// 접속 시간 기록) request.getSession().getAttribut
				loginVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
				loginVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
				loginService.updateLoginHistory(loginVO);
				//

				return "redirect:/eduadm/index.do";
			}

			request.getSession().setAttribute("crtfcLoginVO", resultVO);
			LOGGER.debug("정상");
			redirectAttributes.addFlashAttribute("isCheck", true);
			return "redirect:/eduadm/member/login.do";

		} else {

			// 로그인 실패 횟수 증가
			loginService.updateLoginRetry(loginVO);

			LOGGER.debug("로그인정보 불일치로 로그인 실패로 알림처리");
			retryCnt++;
			if (retryCnt > 5)
				retryCnt = 5;
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("return_url", "");
			postMap.put("title", "");
			postMap.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.<br><span class=\"red-600\">(로그인 실패 " + retryCnt
					+ "회)</span><br>로그인 5회 실패시, 해당 계정의 권한이 제한됩니다.");
			postMap.put("closebtn", "N");
			postMap.put("type", "");// alert
			// postMap.put("timer", 0);//0:없어지지않음
			model.addAttribute("alert_data", postMap);

			// model.addAttribute("message", "fail");
			return "eduadm/member/login";
		}
	}

	// 관리자(교육센터) 로그아웃 ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-로그아웃]");

		if (loginVO != null) {
			log_dscrp.append("[이름:" + loginVO.getMBR_NM() + "(아이디:" + loginVO.getMBR_ID() + ")]");
		} else {
			log_dscrp.append("[이미로그아웃상태]");
		}
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
		request.getSession().setAttribute("LoginVO", null);
		request.getSession().invalidate();
		// return "forward:/eduadm/member/login.do";
		return "redirect:/eduadm/member/login.do";
	}

	@RequestMapping(value = "/eduadm/member/change_pwd.do")
	public ModelAndView eduadm_member_change_pwd_view(@ModelAttribute("EduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, ModelMap model) throws Exception {

		ModelAndView mModelAndView = new ModelAndView();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || loginVO.getMBR_ID() == null || loginVO.getMBR_ID().length() == 0) {
			mModelAndView.setViewName("eduadm/error/page_400");
		} else {
			if (!eduMemberVO.getMBR_SCRTY_KEY()
					.equals(EgovFileScrty.security(eduMemberVO.getMBR_ID(), loginVO.getMBR_ID()))) {
				mModelAndView.setViewName("eduadm/error/page_400");
			} else {
				mModelAndView.setViewName("eduadm/member/modify_pwd");
				model.addAttribute("loginVO", loginVO);
			}
		}
		return mModelAndView;
	}

	// 비밀번호 수정
	@RequestMapping(value = "/eduadm/member/change_pwd_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_change_pwd_act(@ModelAttribute("EduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {
		String MBR_ID = eduMemberVO.getMBR_ID();
		String MBR_SN = eduMemberVO.getMBR_SN();
		String MBR_SCRTY_KEY = eduMemberVO.getMBR_SCRTY_KEY();

		JSONObject data = new JSONObject();
		if (eduMemberVO == null) {
			data.put("error", "2");
			data.put("msg", "비정상적인 접근입니다.");
		} else {
			if (MBR_ID == null || MBR_ID.length() == 0 || MBR_SN == null || MBR_SN.length() == 0
					|| MBR_SCRTY_KEY == null || MBR_SCRTY_KEY.length() == 0) {
				data.put("error", "2");
				data.put("msg", "비정상적인 접근입니다.");
			} else {
				String mbrPwd = eduMemberVO.getMBR_PWD();
				String mbrPwdChk = eduMemberVO.getMBR_PWD();
				if (mbrPwd.length() < 10 || mbrPwd.length() > 20) {
					data.put("error", "1");
					data.put("msg", "비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
				} else if (!mbrPwd
						.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{10,20}$")) {
					data.put("error", "1");
					data.put("msg", "비밀번호는 공백없이 영문,숫자,특수문자[@,!,%,*,#]를 혼합하여 입력해주세요.");
				} else if (!mbrPwd.equals(mbrPwdChk)) {
					data.put("error", "1");
					data.put("msg", "비밀번호와 비밀번호 확인이 다릅니다.");
				} else {
					LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
					if (!MBR_SCRTY_KEY.equals(EgovFileScrty.security(MBR_ID, loginVO.getMBR_ID()))) {
						data.put("error", "2");
						data.put("msg", "비정상적인 접근입니다.");
					} else {
						data.put("error", "0");
						data.put("msg", "비밀번호 변경이 가능합니다.");
						EduMemberVO eduMemberVO2 = new EduMemberVO();
						eduMemberVO.setMBR_ID(MBR_ID);
						eduMemberVO.setMBR_SN(MBR_SN);
						eduMemberVO.setMBR_PWD(EgovFileScrty.encryptPassword(mbrPwd, MBR_ID));

						eduMemberService.set_change_pwd(eduMemberVO);
					}
				}
			}
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 관리자회원관리-회원목록 리스트
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/listAdm.do")
	public String edu_member_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, SessionStatus status,
			HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		// 지자체, 해경 권한일 경우 막기
		if (loginVO.getMBR_POSITION_CD().equals("PCD0003") || loginVO.getMBR_POSITION_CD().equals("PCD0002")) {
			LOGGER.debug("교육센터 관리자페이지 - 접근권한 없음!!");
			return "redirect:/eduadm/error/unauth.do";
		}

		if ((loginVO.getMBR_LV_ID().equals("3") || loginVO.getMBR_LV_ID().equals("4"))) {
			LOGGER.debug("교육센터 관리자페이지 - 접근권한 없음!!");
			return "redirect:/eduadm/error/unauth.do";
		}

		System.out.println("테스트MBR_LV_ID:" + loginVO.getMBR_LV_ID());

		List<EduMemberVO> list = null;

		// 직급 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00003");
			mCodeSetVO.setUSE_AT("Y");
			List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_position_cd", list_position_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}
		// 교육기관목록
		{
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setUSE_ST("1");
			eduCategoryInsInfVO.setNotUsedPagination(true);
			List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService
					.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
			model.addAttribute("list_ins_info_cd", edu_category_ins_inf);
		}
		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_address_cd", list_address_cd);
		}
		// 지역 코드 조회 - 시군구
		List<CodeSetVO> list_address_signgu_cd = null;
		if (eduMemberVO != null && eduMemberVO.getMBR_SIDO_CD() != null && eduMemberVO.getMBR_SIDO_CD().length() != 0) {
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID(eduMemberVO.getMBR_SIDO_CD());
			list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		model.addAttribute("list_address_signgu_cd", list_address_signgu_cd);

		eduMemberVO.setMBR_LV_ID(loginVO.getMBR_LV_ID());

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduMemberVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduMemberVO.getPageUnit());
		paginationInfo.setPageSize(eduMemberVO.getPageSize());

		eduMemberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduMemberVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduMemberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		list = eduMemberService.get_edu_member_master_list(eduMemberVO);
		int totCnt = eduMemberService.get_edu_member_master_list_totcnt(eduMemberVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 검색용
		model.addAttribute("MBR_POSITION_CD", eduMemberVO.getMBR_POSITION_CD());
		model.addAttribute("MBR_TRGT_CD", eduMemberVO.getMBR_TRGT_CD());
		model.addAttribute("MBR_EDU_INS_CD", eduMemberVO.getMBR_EDU_INS_CD());
		model.addAttribute("MBR_SIDO_CD", eduMemberVO.getMBR_SIDO_CD());
		model.addAttribute("MBR_SIGNGU_CD", eduMemberVO.getMBR_SIGNGU_CD());
		model.addAttribute("MBR_ST", eduMemberVO.getMBR_ST());

		model.addAttribute("list", list);
		model.addAttribute("searchKeyword", eduMemberVO.getSearchKeyword());
		return "eduadm/member/list_adm";
	}

	// 관리자(교육센터) 회원관리-관리자권한이력 리스트
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/author_log.do")
	public Object edu_member_author_log(@ModelAttribute("logAdmAuthVO") LogAdmAuthVO logAdmAuthVO,
			boolean isExcelDownLoad, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		// 지자체, 해경 권한일 경우 막기
		if (loginVO.getMBR_POSITION_CD().equals("PCD0003") || loginVO.getMBR_POSITION_CD().equals("PCD0002")) {
			LOGGER.debug("교육센터 관리자페이지 - 접근권한 없음!!");
			return "redirect:/eduadm/error/unauth.do";
		}

		List<LogAdmAuthVO> list = null;

		/** pageing setting **/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(logAdmAuthVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(logAdmAuthVO.getPageUnit());
		paginationInfo.setPageSize(logAdmAuthVO.getPageSize());

		logAdmAuthVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		logAdmAuthVO.setLastIndex(paginationInfo.getLastRecordIndex());
		logAdmAuthVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if (isExcelDownLoad) {
			logAdmAuthVO.setNotUsedPagination(true);
		}

		list = eduMemberService.get_edu_member_author_log_list(logAdmAuthVO);
		int totCnt = eduMemberService.get_edu_member_author_log_list_totcnt(logAdmAuthVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 검색용
		model.addAttribute("searchKeyword", logAdmAuthVO.getSearchKeyword());
		model.addAttribute("searchStrDate", logAdmAuthVO.getSearchStrDate());
		model.addAttribute("searchEndDate", logAdmAuthVO.getSearchEndDate());
		model.addAttribute("list", list);

		if (isExcelDownLoad) {
			return (ModelMap) model;
		} else {
			return "eduadm/member/author_log";
		}
	}

	// 관리자 이용정보동의서(선택)
	@RequestMapping(value = "/eduadm/member/indvdl_info_view.do")
	public ModelAndView eduadm_member_indvdl_info_view(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, ModelMap model) throws Exception {

		ModelAndView mModelAndView = new ModelAndView();

		eduMemberVO = eduMemberService.get_edu_member_master_info(eduMemberVO);

		// model.addAttribute("id", eduMemberVO.getRMNDR_MBR_ID());
		// eduCenterMbrRemindersVO =
		// eduCenterMainService.get_educenter_mbr_rmndr_info(eduCenterMbrRemindersVO);
		model.addAttribute("info", eduMemberVO);

		mModelAndView.setViewName("eduadm/member/indvdl_info_view");

		return mModelAndView;
	}

	// 관리자(교육센터) 회원관리-회원목록 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/eduList{addWebLink}.do")
	public Object edu_member_edu_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, boolean isExcelDownLoad,
			@PathVariable("addWebLink") String addWebLink,
			// @RequestParam(value="searchYear",required=false) String
			// searchYear,
			SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {

		// if(!addWebLink.equals("2021") && !addWebLink.equals("2022") &&
		// !addWebLink.equals("2023") && !addWebLink.equals(""))
		// return "redirect:/incorrect_url.do";

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			return "eduadm/error/page_400";
		}

		if (loginVO.getMBR_POSITION_CD().equals("PCD0002") && !addWebLink.equals("")) {
			LOGGER.debug(" 교육대상자 전체보기 외 - 접근권한 없음!!");
			return "redirect:/eduadm/error/unauth.do";
		}

		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();
		String MASTER_MBR_TRGT_CD = loginVO.getMBR_TRGT_CD();

		// ----------------------------------------------------------------
		// 지역제한권한
		// ----------------------------------------------------------------
		// 지역 코드 조회 - 시도
		String MBR_SIDO_CD = "";
		List<CodeSetVO> list_address_cd = null;
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		// 지역 코드 조회 - 시군구
		String MBR_SIGNGU_CD = "";
		List<CodeSetVO> list_address_signgu_cd = null;
		if (MASTER_MBR_SIDO_CD == null || MASTER_MBR_SIDO_CD.length() == 0) {
			// 제한없음
			MBR_SIDO_CD = eduMemberVO.getMBR_SIDO_CD();
			eduMemberVO.setSIGNGU_CD(MBR_SIDO_CD);
		} else {
			MBR_SIDO_CD = MASTER_MBR_SIDO_CD;
			eduMemberVO.setSIDO_CD(MASTER_MBR_SIDO_CD);
		}
		if (MASTER_MBR_SIGNGU_CD == null || MASTER_MBR_SIGNGU_CD.length() == 0) {
			// 제한없음
			MBR_SIGNGU_CD = eduMemberVO.getMBR_SIGNGU_CD();
			eduMemberVO.setSIGNGU_CD(MBR_SIGNGU_CD);
		} else {
			MBR_SIGNGU_CD = MASTER_MBR_SIGNGU_CD;
			eduMemberVO.setSIGNGU_CD(MASTER_MBR_SIGNGU_CD);
		}
		if (MBR_SIDO_CD != null && MBR_SIDO_CD.length() != 0) {
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID(MBR_SIDO_CD);
			list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		model.addAttribute("MBR_SIDO_CD", MBR_SIDO_CD);
		model.addAttribute("MBR_SIGNGU_CD", MBR_SIGNGU_CD);
		model.addAttribute("list_address_cd", list_address_cd);
		model.addAttribute("list_address_signgu_cd", list_address_signgu_cd);
		// ----------------------------------------------------------------
		// 업종(교육대상)제한권한
		// ----------------------------------------------------------------
		String MBR_TRGT_CD = "";
		if (MASTER_MBR_TRGT_CD == null || MASTER_MBR_TRGT_CD.length() == 0) {
			// 제한없음
			MBR_TRGT_CD = eduMemberVO.getMBR_TRGT_CD();
			eduMemberVO.setDTL_CD(MBR_TRGT_CD);
		} else {
			MBR_TRGT_CD = MASTER_MBR_TRGT_CD;
			eduMemberVO.setDTL_CD(MASTER_MBR_TRGT_CD);
			eduMemberVO.setMBR_TRGT_CD(MASTER_MBR_TRGT_CD);
		}
		model.addAttribute("MBR_TRGT_CD", MBR_TRGT_CD);
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}
		// ----------------------------------------------------------------
		// 개별조건
		// ----------------------------------------------------------------
		if (MASTER_MBR_POSITION_CD.equals("PCD0002")) { // 해양경찰서
			eduMemberVO.setMBR_ST("Y");
		} else if (MASTER_MBR_POSITION_CD.equals("PCD0003")) { // 지자체
			// 지자체 관리주체건만 조회하도록...
			eduMemberVO.setMBR_REG_TYPE_CD(MASTER_MBR_POSITION_CD);
			eduMemberVO.setMBR_SIDO_CD(MASTER_MBR_SIDO_CD);
			eduMemberVO.setMBR_SIGNGU_CD(MASTER_MBR_SIGNGU_CD);
			eduMemberVO.setMBR_ST("Y");
		} else if (MASTER_MBR_POSITION_CD.equals("PCD0004")) { // 교육기관
			eduMemberVO.setMBR_ST("Y");
			/*
			 * if(eduMemberVO.getMBR_ST()!=null &&
			 * eduMemberVO.getMBR_ST().equals("ALL")) {
			 * eduMemberVO.setMBR_ST(""); }
			 */
		} else {// 제한없음 - 공단
			if (eduMemberVO.getMBR_ST() == null || eduMemberVO.getMBR_ST().length() == 0) {
				eduMemberVO.setMBR_ST("Y");
			} else if (eduMemberVO.getMBR_ST().equals("ALL")) {
				eduMemberVO.setMBR_ST("");
			}
		}
		// ----------------------------------------------------------------
		// 공통조건
		// ----------------------------------------------------------------
		eduMemberVO.setMBR_LV_ID("10");
		eduMemberVO.setSearchYear(addWebLink);
		eduMemberVO.setDEL_AT("N");
		eduMemberVO.setUSE_AT("Y");
		// ----------------------------------------------------------------
		// 직급 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00003");
			List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_position_cd", list_position_cd);
		}
		// 사업자구분코드
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00006");
			List<CodeSetVO> list_license_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_license_se_cd", list_license_se_cd);
		}
		// 낚시터업구분코드
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00007");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_fshlc_work_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_fshlc_work_cd", list_fshlc_work_cd);
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduMemberVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduMemberVO.getPageUnit());
		paginationInfo.setPageSize(eduMemberVO.getPageSize());

		eduMemberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduMemberVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduMemberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if (isExcelDownLoad) {
			eduMemberVO.setNotUsedPagination(true);
		}
		List<EduMemberVO> list = eduMemberService.get_edu_member_target_list_only(eduMemberVO);
		int totCnt = eduMemberService.get_edu_member_target_list_totcnt_only(eduMemberVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 취약점 대응을 위한 처리
		if (!isExcelDownLoad) {
			for (EduMemberVO item : list) {
				item.setMBR_SCRTY_KEY(EgovFileScrty.security(item.getMBR_ID(), loginVO.getMBR_ID()));
			}
		}
		// End of 취약점 대응을 위한 처리

		model.addAttribute("list", list);
		// 검색용조건(공통)
		model.addAttribute("searchKeyword", eduMemberVO.getSearchKeyword());
		model.addAttribute("addWebLink", addWebLink);
		model.addAttribute("YMD_NM", eduMemberVO.getYMD_NM());
		model.addAttribute("MBR_NM", eduMemberVO.getMBR_NM());
		model.addAttribute("MBR_HP", eduMemberVO.getMBR_HP());
		model.addAttribute("MBR_ST", eduMemberVO.getMBR_ST());
		model.addAttribute("MBR_ADDR_MERGE", eduMemberVO.getMBR_ADDR_MERGE());
		model.addAttribute("MBR_BIRTH", eduMemberVO.getMBR_BIRTH());
		model.addAttribute("MBR_TEL", eduMemberVO.getMBR_TEL());
		model.addAttribute("MBR_REG_TYPE_CD", eduMemberVO.getMBR_REG_TYPE_CD());
		model.addAttribute("DTL_NM", eduMemberVO.getDTL_NM());
		model.addAttribute("BUSINESS_NUM", eduMemberVO.getBUSINESS_NUM());
		model.addAttribute("REG_NUM_CD", eduMemberVO.getREG_NUM_CD());
		model.addAttribute("SHIP_LICENSE_STR_DT", eduMemberVO.getSHIP_LICENSE_STR_DT());
		model.addAttribute("SHIP_LICENSE_END_DT", eduMemberVO.getSHIP_LICENSE_END_DT());
		model.addAttribute("MBR_LRNNG_CMPLT_ST", eduMemberVO.getMBR_LRNNG_CMPLT_ST());
		model.addAttribute("MBR_LRNNG_ST", eduMemberVO.getMBR_LRNNG_ST());
		model.addAttribute("TYPE_GB", eduMemberVO.getTYPE_GB());
		model.addAttribute("MBR_FSHRBT_TYPE", eduMemberVO.getMBR_FSHRBT_TYPE());// 기존,신규,재개자
		model.addAttribute("MBR_EDU_RSPNBER_TYPE", eduMemberVO.getMBR_EDU_RSPNBER_TYPE());// 법인업자
																							// 대표자,
																							// 교육책임자
																							// 구분
		model.addAttribute("MBR_JOIN", eduMemberVO.getMBR_JOIN());// 비회원 자동 등록
																	// 여부(auto)
		// 검색용조건(낚시터용)
		model.addAttribute("SHIP_LICENSE", eduMemberVO.getSHIP_LICENSE());
		model.addAttribute("DTL_ADDR", eduMemberVO.getDTL_ADDR());
		model.addAttribute("FSHLC_APPLC", eduMemberVO.getFSHLC_APPLC());
		model.addAttribute("FSHLC_WORK_CD", eduMemberVO.getFSHLC_WORK_CD());
		// 검색용조건(낚시어선용)
		model.addAttribute("SHIP_CD", eduMemberVO.getSHIP_CD());
		model.addAttribute("SHIP_GRTG", eduMemberVO.getSHIP_GRTG());
		model.addAttribute("SHIP_PRLOAD", eduMemberVO.getSHIP_PRLOAD());
		model.addAttribute("SHIP_MAX_PSNGER", eduMemberVO.getSHIP_MAX_PSNGER());
		model.addAttribute("SHIP_MAX_CREW", eduMemberVO.getSHIP_MAX_CREW());
		model.addAttribute("DTL_LICENSE_CD", eduMemberVO.getDTL_LICENSE_CD());

		if (isExcelDownLoad) {
			return (ModelMap) model;
		} else {
			return "eduadm/member/list_edu_target";
		}
	}

	// 관리자(교육센터) 회원관리-회원목록 리스트 법인사업장 교육책임자 수정
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/rspnber_type_update.do")
	public @ResponseBody String edu_member_rspnber_type_update(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		/*
		 * if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
		 * return "eduadm/error/page_400"; }
		 */
		JSONObject data = new JSONObject();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		log_dscrp.append("[교육센터관리자-교육대상자관리-법인사업장 교육책임자 수정]");

		try {
			eduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			eduMemberService.set_edu_member_mod(eduMemberVO);

			data.put("error", "0");
			data.put("msg", "수정되었습니다.");

			log_dscrp.append(
					"[회원아이디:" + eduMemberVO.getMBR_ID() + " | 교육책임자구분:" + eduMemberVO.getMBR_EDU_RSPNBER_TYPE() + " ]");
			log_dscrp.append("[관리자아이디: " + loginVO.getMBR_ID() + "]");
			tbl_inf.append("MBR_TB");
		} catch (Exception e) {
			log_msg.append(e.toString());

			data.put("error", "1");
			data.put("msg", "잠시후 다시 시도해주세요.");
		}

		try {
			/**
			 * LOG RECORED (로그기록)
			 */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
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

		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);

		return null;
	}

	// 관리자(교육센터) 회원관리-회원목록 상세(수강내역) 리스트
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/listDtl.do")
	public String edu_member_listDtl(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, SessionStatus status, HttpServletRequest request,
			ModelMap model) throws Exception {

		if (eduMyHistoryVO.getMBR_ID() == null || eduMyHistoryVO.getMBR_ID().length() == 0) {
			return "eduadm/error/page_400";
		}

		List<EduMyHistoryVO> list = null;
		// eduMemberVO.setPageUnit(3);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduMyHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduMyHistoryVO.getPageUnit());
		paginationInfo.setPageSize(eduMyHistoryVO.getPageSize());

		eduMyHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduMyHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduMyHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		list = eduMyHistoryService.get_edu_mbrhstry_list(eduMyHistoryVO);

		List<EduMyHistoryVO> totCntList = eduMyHistoryService.get_edu_mbrhstry_list_totcnt(eduMyHistoryVO);
		paginationInfo.setTotalRecordCount(totCntList.size());
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("list", list);
		model.addAttribute("member", eduMemberVO);
		return "eduadm/member/list_dtl";
	}

	// 관리자(교육센터) 회원관리 - 회원자세히보기 ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/view.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_member_view(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		boolean isNotFoundMemberInfo = false;
		ModelAndView mModelAndView = new ModelAndView();
		EduMemberVO info = null;
		List<EduMemberVO> list_dtl = null;
		List<EduMemberVO> list_edu_target = null;
		if (eduMemberVO != null && eduMemberVO.getMBR_ID() != null && eduMemberVO.getMBR_ID().length() != 0) {
			info = eduMemberService.get_edu_member_info(eduMemberVO);
			list_dtl = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
			list_edu_target = eduMemberService.get_edu_member_target_all_list(eduMemberVO);

			if (info == null || info.getMBR_ID() == null || info.getMBR_ID().length() == 0) {
				isNotFoundMemberInfo = true;
			} else {
				info.setSearchYear(eduMemberVO.getSearchYear());
				// 지역 코드 조회 - 시도
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00004");
					List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
					mModelAndView.addObject("list_address_cd", list_address_cd);
				}
				// 지역 코드 조회 - 시군구
				List<CodeSetVO> list_address_signgu_cd = null;
				if (info.getMBR_SIDO_CD() != null && info.getMBR_SIDO_CD().length() != 0) {
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID(info.getMBR_SIDO_CD());
					list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
				}
				mModelAndView.addObject("list_address_signgu_cd", list_address_signgu_cd);
				//
				// 회원추가정보구분자
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00002");
					List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
					mModelAndView.addObject("list_target_se_cd", list_target_se_cd);
				}
				// 사업자구분코드
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00006");
					List<CodeSetVO> list_license_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
					mModelAndView.addObject("list_license_se_cd", list_license_se_cd);
				}
				// 낚시터업구분코드
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00007");
					mCodeSetVO.setHIDE_AT("N");
					List<CodeSetVO> list_fshlc_work_cd = codeSetService.get_codeset_list(mCodeSetVO);
					model.addAttribute("list_fshlc_work_cd", list_fshlc_work_cd);
				}
				// 직급 코드 조회
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00003");
					List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
					mModelAndView.addObject("list_position_cd", list_position_cd);
				}
			}

		} else {
			isNotFoundMemberInfo = true;
		}

		if (isNotFoundMemberInfo) {
			mModelAndView.setViewName("eduadm/error/not_found_member");
			mModelAndView.addObject("info", eduMemberVO);
		} else {
			mModelAndView.setViewName("eduadm/member/view");
			mModelAndView.addObject("info", info);
			mModelAndView.addObject("list_dtl", list_dtl);
			mModelAndView.addObject("list_edu_target", list_edu_target);
		}

		return mModelAndView;
	}

	// 관리자(교육센터) 교육신청(심사) - 처리하기
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/rmndr/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_member_rmndr_write(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@ModelAttribute("eduMbrRemindersVO") EduMbrRemindersVO eduMbrRemindersVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		ModelAndView mModelAndView = new ModelAndView();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			mModelAndView.setViewName("eduadm/error/page_400");
		} else {
			EduMbrRemindersVO info_rmndr = eduCenterService.get_edu_mbr_reminders_info(eduMbrRemindersVO);
			if (info_rmndr == null || info_rmndr.getRMNDR_SN() == null || info_rmndr.getRMNDR_SN().length() == 0) {
				// 정보없음
				mModelAndView.setViewName("eduadm/error/page_400");
			} else {
				EduCurriculumVO info_crs = null;
				String year = "";
				if (info_rmndr.getRMNDR_CRS_SN() != null && info_rmndr.getRMNDR_CRS_SN().length() != 0) {
					EduCurriculumVO chkEduCurriculumVO = new EduCurriculumVO();
					chkEduCurriculumVO.setCRS_SN(info_rmndr.getRMNDR_CRS_SN());
					info_crs = eduCurriculumService.get_edu_curriculum_info(chkEduCurriculumVO);
					if (info_crs != null && info_crs.getCRS_SN() != null && info_crs.getCRS_SN().length() != 0) {
						Calendar cal = mPublicUtils.changeGetCalendar(info_crs.getCRS_STR_DT().replace(".0", ""),
								"yyyy-MM-dd HH:mm:ss");
						year = String.valueOf(cal.get(Calendar.YEAR));
					}
				}
				eduMemberVO.setSearchYear(year);
				// 교육그룹 코드 조회(활성화)
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00005");
					mCodeSetVO.setHIDE_AT("N");
					List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
					model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
				}
				// 회원추가정보구분자
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00002");
					List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
					mModelAndView.addObject("list_target_se_cd", list_position_cd);
				}
				// 사업자구분코드
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00006");
					mCodeSetVO.setHIDE_AT("N");
					List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
					mModelAndView.addObject("list_license_se_cd", list_position_cd);
				}
				// 낚시터업구분코드
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00007");
					mCodeSetVO.setHIDE_AT("N");
					List<CodeSetVO> list_fshlc_work_cd = codeSetService.get_codeset_list(mCodeSetVO);
					model.addAttribute("list_fshlc_work_cd", list_fshlc_work_cd);
				}
				// 지역 코드 조회
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00004");
					List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
					mModelAndView.addObject("list_address_cd", list_address_cd);
				}
				eduMemberVO.setMBR_ID(eduMemberVO.getUniqKey("MID"));
				mModelAndView.addObject("info", eduMemberVO);
				mModelAndView.addObject("info_rmndr", info_rmndr);
				mModelAndView.addObject("info_crs", info_crs);
				mModelAndView.setViewName("eduadm/member/write_rmndr");
			}

		}
		return mModelAndView;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 글작성
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_member_write(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			mModelAndView.setViewName("eduadm/error/page_400");
		} else {
			// 회원추가정보구분자
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00002");
				List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
				mModelAndView.addObject("list_target_se_cd", list_target_se_cd);
			}
			// 사업자구분코드
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00006");
				mCodeSetVO.setHIDE_AT("N");
				List<CodeSetVO> list_license_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
				mModelAndView.addObject("list_license_se_cd", list_license_se_cd);
			}
			// 낚시터업구분코드
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00007");
				mCodeSetVO.setHIDE_AT("N");
				List<CodeSetVO> list_fshlc_work_cd = codeSetService.get_codeset_list(mCodeSetVO);
				model.addAttribute("list_fshlc_work_cd", list_fshlc_work_cd);
			}
			// 지역 코드 조회
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00004");
				List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
				mModelAndView.addObject("list_address_cd", list_address_cd);
			}
			// 직급 코드 조회
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00003");
				List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
				mModelAndView.addObject("list_position_cd", list_position_cd);
			}
			eduMemberVO.setMBR_ID(eduMemberVO.getUniqKey("MID"));
			mModelAndView.addObject("info", eduMemberVO);
			mModelAndView.setViewName("eduadm/member/write");
		}
		return mModelAndView;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 관리자 글작성
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/write_adm.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_member_adm_write(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			mModelAndView.setViewName("eduadm/error/page_400");
		} else {
			// 직급 코드 조회
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00003");
				mCodeSetVO.setUSE_AT("Y");
				List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
				mModelAndView.addObject("list_position_cd", list_position_cd);
			}
			// 대상구분 코드 조회
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00002");
				mCodeSetVO.setHIDE_AT("N");
				List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
				model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
			}
			// 교육기관목록
			{
				EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
				eduCategoryInsInfVO.setUSE_ST("1");
				eduCategoryInsInfVO.setNotUsedPagination(true);
				List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService
						.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
				mModelAndView.addObject("list_ins_info_cd", edu_category_ins_inf);
			}
			// 지역 코드 조회 - 시도
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00004");
				List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
				mModelAndView.addObject("list_address_cd", list_address_cd);
			}
			mModelAndView.setViewName("eduadm/member/write_adm");
		}
		return mModelAndView;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 글수정
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/modify.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_member_modify(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@RequestParam(value = "MHC_SN", required = false, defaultValue = "") String MHC_SN,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		ModelAndView mModelAndView = new ModelAndView();
		EduMemberVO info = null;
		List<EduMemberVO> list_dtl = null;
		List<EduMemberVO> list_edu_target = null;
		if (eduMemberVO.getMBR_ID() != null && eduMemberVO.getMBR_ID().length() != 0) {
			info = eduMemberService.get_edu_member_info(eduMemberVO);
			list_dtl = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
			EduMemberVO targetEduMemberVO = new EduMemberVO();
			targetEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
			list_edu_target = eduMemberService.get_edu_member_target_all_list(targetEduMemberVO);
		}

		if (info != null && info.getMBR_ID() != null && info.getMBR_ID().length() != 0) {

		} else {
			LOGGER.debug("비정상적인 접근(아이디 정보 없음)");
			mModelAndView.setViewName("eduadm/error/modal_page_400");
			return mModelAndView;
		}

		if (eduMemberVO != null && eduMemberVO.getSearchYear() != null)
			info.setSearchYear(eduMemberVO.getSearchYear());
		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_address_cd", list_address_cd);
		}
		// 지역 코드 조회 - 시군구
		List<CodeSetVO> list_address_signgu_cd = null;
		if (info.getMBR_SIDO_CD() != null && info.getMBR_SIDO_CD().length() != 0) {
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID(info.getMBR_SIDO_CD());
			list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		mModelAndView.addObject("list_address_signgu_cd", list_address_signgu_cd);
		//
		// 회원추가정보구분자
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_target_se_cd", list_target_se_cd);
		}
		// 사업자구분코드
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00006");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_license_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_license_se_cd", list_license_se_cd);
		}
		// 낚시터업구분코드
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00007");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_fshlc_work_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_fshlc_work_cd", list_fshlc_work_cd);
		}
		// 직급 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00003");
			List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_position_cd", list_position_cd);
		}
		mModelAndView.setViewName("eduadm/member/modify");

		// 취약점 대응을 위한 처리
		String MBR_SCRTY_KEY = EgovFileScrty.security(info.getMBR_ID(), loginVO.getMBR_ID());
		mModelAndView.addObject("MBR_SCRTY_KEY", MBR_SCRTY_KEY);
		if (!MBR_SCRTY_KEY.equals(eduMemberVO.getMBR_SCRTY_KEY())) {
			mModelAndView.setViewName("eduadm/error/modal_page_400");
			return mModelAndView;
		}
		// End 취약점 대응을 위한 처리

		mModelAndView.addObject("info", info);
		mModelAndView.addObject("list_dtl", list_dtl);
		mModelAndView.addObject("list_edu_target", list_edu_target);

		if (MHC_SN.length() > 0 && MHC_SN != null) {
			mModelAndView.addObject("MHC_SN", MHC_SN);
		}

		// 교육그룹 코드 조회(전체)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_all_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}
		// 신청가능한 교육 조회
		EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
		eduCurriculumVO.setUSE_ST("1");
		eduCurriculumVO.setCRS_ST("1");
		eduCurriculumVO.setDEL_ST("0");
		eduCurriculumVO.setLOCK_ST("0");
		eduCurriculumVO.setCRS_END_DT_GE_NOW("Y");
		List<EduCurriculumVO> edu_list = eduCurriculumService.get_edu_curriculum_list(eduCurriculumVO);
		mModelAndView.addObject("edu_list", edu_list);
		mModelAndView.addObject("edu_list_totcnt", edu_list.size());

		return mModelAndView;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 글수정
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/modify_adm.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_member_adm_modify(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		EduMemberVO info = null;
		if (eduMemberVO.getMBR_ID() != null && eduMemberVO.getMBR_ID().length() != 0) {
			info = eduMemberService.get_edu_member_info(eduMemberVO);
		}
		// 직급 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00003");
			mCodeSetVO.setUSE_AT("Y");
			List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_position_cd", list_position_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}
		// 교육기관목록
		{
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setUSE_ST("1");
			eduCategoryInsInfVO.setNotUsedPagination(true);
			List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService
					.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
			mModelAndView.addObject("list_ins_info_cd", edu_category_ins_inf);
		}
		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_address_cd", list_address_cd);
		}
		// 지역 코드 조회 - 시군구
		List<CodeSetVO> list_address_signgu_cd = null;
		if (info.getMBR_SIDO_CD() != null && info.getMBR_SIDO_CD().length() != 0) {
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID(info.getMBR_SIDO_CD());
			list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		mModelAndView.addObject("list_address_signgu_cd", list_address_signgu_cd);
		//
		mModelAndView.setViewName("eduadm/member/modify_adm");
		mModelAndView.addObject("info", info);
		return mModelAndView;
	}

	// 관리자(교육센터) 회원관리 - 회원목록(수강내역) 글수정
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/modifyDtl.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_member_modifyDtl(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		EduMyHistoryVO info = null;
		if (eduMyHistoryVO.getHMBR_SN() != null && eduMyHistoryVO.getHMBR_SN().length() != 0) {
			info = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
		}
		// 교육과정 정보
		EduCurriculumVO parentInfo = null;
		eduCurriculumVO.setTypeStr("");
		parentInfo = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/member/modify_dtl");
		mModelAndView.addObject("parentInfo", parentInfo);
		mModelAndView.addObject("info", info);
		return mModelAndView;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 - 회원추가 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_write_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@RequestParam(value = "writeType", required = false) String writeType, HttpServletRequest request,
			HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();

		JSONObject data = new JSONObject();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		log_dscrp.append("[교육센터관리자-회원관리-신규회원등록]");
		tbl_inf.append("MBR_TB");

		log_dscrp.append("[이름:" + eduMemberVO.getMBR_NM() + "(아이디:" + eduMemberVO.getMBR_ID() + ")]");

		int is_id_cnt = eduMemberService.get_id_search(eduMemberVO);
		if (is_id_cnt > 0) {
			log_dscrp.append("|등록실패:이미 등록 된 아이디]");

			data.put("error", "1");
			data.put("msg", "이미 등록 된 아이디 입니다.");

		} else {
			try {
				boolean isAddConfirm = false;
				if (eduMemberVO.getAddConfirmSubmit().equals("Y")) {
					log_dscrp.append("|중복을무시고하고강제등록");
					isAddConfirm = true;
				}

				List<EduMemberVO> list_overlap = eduMemberService.get_edu_member_check_overlap_list(eduMemberVO);
				if (list_overlap != null && list_overlap.size() > 0 && !isAddConfirm) { // 중복의심발생

					data.put("error", "2");
					data.put("msg", "중복이 의심되는 회원이 존재하여 확인이 필요합니다.");

					log_dscrp.append("|등록실패:중복의심회원존재");

					// 취약점 대응을 위한 처리
					for (EduMemberVO item : list_overlap) {
						item.setMBR_SCRTY_KEY(EgovFileScrty.security(item.getMBR_ID(), loginVO.getMBR_ID()));
					}
					// End of 취약점 대응을 위한 처리

					ObjectMapper mapper = new ObjectMapper();
					mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
					mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
					data.put("rawdata", mapper.writeValueAsString(list_overlap));

				} else { // 신규

					if (writeType != null && writeType.equals("rmndr")) {
						if (eduMemberVO.getLOG_UPD_MSG() == null || eduMemberVO.getLOG_UPD_MSG().length() == 0)
							eduMemberVO.setLOG_UPD_MSG("온라인교육신청자 신규 건");
					} else {
						if (eduMemberVO.getLOG_UPD_MSG() == null || eduMemberVO.getLOG_UPD_MSG().length() == 0)
							eduMemberVO.setLOG_UPD_MSG("사용자 신규 건");
					}

					eduMemberVO.setMBR_CD(eduMemberVO.getMBR_ID());
					eduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
					eduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
					if (eduMemberVO.getREG_TYPE_CD() == null || eduMemberVO.getREG_TYPE_CD().length() == 0) {
						eduMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);
					}
					if (MASTER_MBR_POSITION_CD.equals("PCD0003")) { // 담당자가 지자체
																	// 인 경우 ,
																	// 지자체가
																	// 관리주체가 된다.
						eduMemberVO.setMBR_REG_TYPE_CD(MASTER_MBR_POSITION_CD);
						eduMemberService.set_edu_member_trnsfer(eduMemberVO);
						log_dscrp.append("[회원관리주체지정(지자체)]");
					}
					eduMemberService.set_edu_member_reg(eduMemberVO);
					// 사용자사유로그기록
					{
						logRecordService.set_log_mbr_mod_data("MBR_TB", "신규", eduMemberVO.getLOG_UPD_MSG(),
								eduMemberVO.getMBR_ID(), eduMemberVO.getMBR_NM(), eduMemberVO, null, loginVO, request);
					}
					log_dscrp.append("|신규등록");

					// 회원상세정보
					try {
						int cntDtlSuccess = 0;
						int cntDtlFail = 0;
						String[] DTL_SNs = eduMemberVO.getDTL_SN().split(",", -1);
						String[] DTL_CDs = eduMemberVO.getDTL_CD().split(",", -1);
						String[] DTL_NMs = eduMemberVO.getDTL_NM().split(",", -1);
						String[] SIDO_CDs = eduMemberVO.getSIDO_CD().split(",", -1);
						String[] SIGNGU_CDs = eduMemberVO.getSIGNGU_CD().split(",", -1);
						String[] YMD_NMs = eduMemberVO.getYMD_NM().split(",", -1);
						String[] DTL_ADDRs = eduMemberVO.getDTL_ADDR().split(",", -1);
						String[] DTL_LICENSE_CDs = eduMemberVO.getDTL_LICENSE_CD().split(",", -1);
						String[] BUSINESS_NUMs = eduMemberVO.getBUSINESS_NUM().split(",", -1);
						String[] REG_NUM_CDs = eduMemberVO.getREG_NUM_CD().split(",", -1);
						String[] SHIP_CDs = eduMemberVO.getSHIP_CD().split(",", -1);
						String[] SHIP_GRTGs = eduMemberVO.getSHIP_GRTG().split(",", -1);
						String[] SHIP_PRLOADs = eduMemberVO.getSHIP_PRLOAD().split(",", -1);
						String[] SHIP_MAX_PSNGERs = eduMemberVO.getSHIP_MAX_PSNGER().split(",", -1);
						String[] SHIP_MAX_CREWs = eduMemberVO.getSHIP_MAX_CREW().split(",", -1);
						String[] SHIP_LICENSEs = eduMemberVO.getSHIP_LICENSE().split(",", -1);
						String[] SHIP_LICENSE_STR_DTs = eduMemberVO.getSHIP_LICENSE_STR_DT().split(",", -1);
						String[] SHIP_LICENSE_END_DTs = eduMemberVO.getSHIP_LICENSE_END_DT().split(",", -1);
						String[] FSHLC_APPLCs = eduMemberVO.getFSHLC_APPLC().split(",", -1);
						String[] FSHLC_WORK_CDs = eduMemberVO.getFSHLC_WORK_CD().split(",", -1);
						for (int i = 0; i < DTL_NMs.length; i++) {
							try {
								EduMemberVO eduDtlMemberVO = new EduMemberVO();
								eduDtlMemberVO.setMBR_CD(eduMemberVO.getMBR_ID());
								eduDtlMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
								eduDtlMemberVO.setMBR_NM(eduMemberVO.getMBR_NM());
								eduDtlMemberVO.setREG_TYPE_CD(eduMemberVO.getREG_TYPE_CD());
								eduDtlMemberVO.setDTL_CD(DTL_CDs[i]);
								eduDtlMemberVO.setDTL_NM(DTL_NMs[i]);
								eduDtlMemberVO.setSIDO_CD(SIDO_CDs[i]);
								eduDtlMemberVO.setSIGNGU_CD(SIGNGU_CDs[i]);
								eduDtlMemberVO.setYMD_NM(YMD_NMs[i]);
								eduDtlMemberVO.setDTL_ADDR(DTL_ADDRs[i]);
								eduDtlMemberVO.setDTL_LICENSE_CD(DTL_LICENSE_CDs[i]);
								eduDtlMemberVO.setBUSINESS_NUM(BUSINESS_NUMs[i]);
								eduDtlMemberVO.setREG_NUM_CD(REG_NUM_CDs[i]);
								eduDtlMemberVO.setSHIP_CD(SHIP_CDs[i]);
								eduDtlMemberVO.setSHIP_GRTG(SHIP_GRTGs[i]);
								eduDtlMemberVO.setSHIP_PRLOAD(SHIP_PRLOADs[i]);
								eduDtlMemberVO.setSHIP_MAX_PSNGER(SHIP_MAX_PSNGERs[i]);
								eduDtlMemberVO.setSHIP_MAX_CREW(SHIP_MAX_CREWs[i]);
								eduDtlMemberVO.setSHIP_LICENSE(SHIP_LICENSEs[i]);
								eduDtlMemberVO.setSHIP_LICENSE_STR_DT(SHIP_LICENSE_STR_DTs[i]);
								eduDtlMemberVO.setSHIP_LICENSE_END_DT(SHIP_LICENSE_END_DTs[i]);
								eduDtlMemberVO.setFSHLC_APPLC(FSHLC_APPLCs[i]);
								eduDtlMemberVO.setFSHLC_WORK_CD(FSHLC_WORK_CDs[i]);
								eduDtlMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
								eduDtlMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
								eduMemberService.set_edu_member_dtl_reg(eduDtlMemberVO);
								// 사용자사유로그기록
								{
									logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "추가",
											eduMemberVO.getLOG_UPD_MSG(), eduMemberVO.getMBR_ID(),
											eduMemberVO.getMBR_NM(), null, eduDtlMemberVO, loginVO, request);
								}
								cntDtlSuccess++;
							} catch (Exception e) {
								log_msg.append("상세정보추가등록에러" + e.toString());
								cntDtlFail++;
							}
						}
						log_dscrp.append("|상세정보추가등록(총:" + cntDtlSuccess + "건,실패:" + cntDtlFail + "건)");
					} catch (Exception e) {
						log_dscrp.append("|상세정보추가등록건없음");
					}

					// 교육수강신청 확인 및 추가
					String HMBR_SN = "";
					if (eduMemberVO.getCRS_SN() == null || eduMemberVO.getCRS_SN().length() == 0) {
						log_dscrp.append("|교육과정을선택하지않아서신청불가");
					} else {
						log_dscrp.append("|교육신청");

						List<String> externalVideoUrl = new ArrayList<>();// 외부
																			// 동영상
																			// 링크
																			// URL
						String domainUrl = request.getScheme() + "://" + request.getServerName() + ":"
								+ request.getServerPort();

						EduCurriculumVO chkEduCurriculumVO = new EduCurriculumVO();
						chkEduCurriculumVO.setCRS_SN(eduMemberVO.getCRS_SN());
						chkEduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(chkEduCurriculumVO);
						if (chkEduCurriculumVO == null || chkEduCurriculumVO.getCRS_SN() == null
								|| chkEduCurriculumVO.getCRS_SN().length() == 0) {
							log_dscrp.append("|실패:교육과정정보가존재하지않음");
						} else {

							// 커리큘럼 상세
							chkEduCurriculumVO.setNotUsedPagination(true);
							List<EduCurriculumVO> clildlist = eduCurriculumService
									.get_edu_curriculum_dtl_list(chkEduCurriculumVO);

							int HMBR_MAX_TIME = Integer.parseInt(chkEduCurriculumVO.getCRS_TOT_TIME())
									+ chkEduCurriculumVO.getSUM_TOT_TIME();
							int HMBR_MAX_POINT = Integer.parseInt(chkEduCurriculumVO.getCRS_TOT_POINT())
									+ chkEduCurriculumVO.getSUM_TOT_POINT();
							EduMyHistoryVO newEduMyHistoryVO = new EduMyHistoryVO();
							newEduMyHistoryVO.setPURCHASE_CMPLT_ST("1");// 결제(구매)
																		// 처리는
																		// 현재 보류
							newEduMyHistoryVO.setCRS_SN(chkEduCurriculumVO.getCRS_SN());
							newEduMyHistoryVO.setMBR_ID(eduMemberVO.getMBR_ID());
							newEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
							newEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							newEduMyHistoryVO.setHMBR_MAX_TIME(String.valueOf(HMBR_MAX_TIME));
							newEduMyHistoryVO.setHMBR_MAX_POINT(String.valueOf(HMBR_MAX_POINT));
							// 중복 검증 구간
							{
								boolean isOk = false;
								do {
									HMBR_SN = newEduMyHistoryVO.getUniqKey("HMBR");
									isOk = eduMyHistoryService.get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(HMBR_SN);
								} while (!isOk);
							}
							// End of 중복 검증 구간
							newEduMyHistoryVO.setHMBR_SN(HMBR_SN);

							// 메인 커리큘럼 정보 관련 나의이력 생성
							eduMyHistoryService.set_edu_mbrhstry_reg(newEduMyHistoryVO);

							log_dscrp.append("," + eduMemberVO.getMBR_NM() + "|수강생등록완료");
							tbl_inf.append("EDU_MBR_HSTRY_TB,");
							tbl_sn.append(HMBR_SN + ",");

							// 상세 커리큘럼 정보 만큼 나의상세이력생성
							int insertChildCount = 1;
							for (EduCurriculumVO crs_dtl : clildlist) {
								int insertSubCount = 0;
								String[] trnids = new String[1];
								if (crs_dtl != null && crs_dtl.getTRN_SN() != null && crs_dtl.getTRN_SN().length() != 0)
									trnids = crs_dtl.getTRN_SN().replaceAll("\\s", "").split(",");
								for (String TRN_SN : trnids) {
									String CRS_TOT_TIME = crs_dtl.getCRS_TOT_TIME();
									String CRS_TOT_POINT = crs_dtl.getCRS_TOT_POINT();
									if (TRN_SN == null)
										continue;
									if (insertSubCount != 0) {
										CRS_TOT_TIME = "0";
										CRS_TOT_POINT = "0";
									}

									EduTrainingDataVO eduTrainingDataVO = new EduTrainingDataVO();
									eduTrainingDataVO.setTRN_SN(TRN_SN);
									EduTrainingDataVO tdata = eduTrainingDataService
											.get_edu_data_info(eduTrainingDataVO);

									EduMyHistoryVO newDtlEduMyHistoryVO = new EduMyHistoryVO();
									String HMBR_DTL_SN = "";
									// 중복 검증 구간
									{
										boolean isOk = false;
										do {
											HMBR_DTL_SN = newDtlEduMyHistoryVO.getUniqKey("HMBRD");
											isOk = eduMyHistoryService
													.get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(HMBR_DTL_SN);
										} while (!isOk);
									}
									// End of 중복 검증 구간
									newDtlEduMyHistoryVO.setHMBR_ORD(String.valueOf(insertChildCount));
									newDtlEduMyHistoryVO.setTRN_MAX_TIME(tdata.getTRN_MAX_TIME());
									newDtlEduMyHistoryVO.setTRN_SN(TRN_SN);
									newDtlEduMyHistoryVO.setHMBR_SN(HMBR_SN);
									newDtlEduMyHistoryVO.setCRS_SN(crs_dtl.getCRS_SN());
									newDtlEduMyHistoryVO.setCRS_DTL_SN(crs_dtl.getCRS_DTL_SN());
									newDtlEduMyHistoryVO.setCAT_SN(crs_dtl.getCAT_SN());
									newDtlEduMyHistoryVO.setCAT_DTL_SN(crs_dtl.getCAT_DTL_SN());
									newDtlEduMyHistoryVO.setLRNNG_MAX_TIME(CRS_TOT_TIME);
									newDtlEduMyHistoryVO.setLRNNG_MAX_POINT(CRS_TOT_POINT);
									newDtlEduMyHistoryVO.setMBR_ID(eduMemberVO.getMBR_ID());
									newDtlEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
									newDtlEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
									newDtlEduMyHistoryVO.setHMBR_DTL_SN(HMBR_DTL_SN);
									eduMyHistoryService.set_edu_mbrhstry_reg_dtl(newDtlEduMyHistoryVO);

									// 외부 동영상 URL 생성
									String eduUrl = domainUrl + "/educenter/mbrhstry/external/play.do?key="
											+ EgovFileScrty.encode(HMBR_DTL_SN);
									externalVideoUrl.add(eduUrl);
									// End of 외부 동영상 URL 생성

									tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
									tbl_sn.append(HMBR_DTL_SN + ",");

									insertSubCount++;
								}
								insertChildCount++;
							}
							// 메인 커리큘럼에 회원 카운트 증가
							eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(chkEduCurriculumVO);

							// SMS자동발송(즉시)
							{
								String str_crs_dt = mPublicUtils.changePatternString(
										chkEduCurriculumVO.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss",
										"yyyy-MM-dd");
								String end_crs_dt = mPublicUtils.changePatternString(
										chkEduCurriculumVO.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss",
										"yyyy-MM-dd");
								String str_crs_str_time = mPublicUtils.changePatternString(
										chkEduCurriculumVO.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss",
										"HH:mm");
								String str_crs_end_time = mPublicUtils.changePatternString(
										chkEduCurriculumVO.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss",
										"HH:mm");

								String eduTime = "교육일시 : " + str_crs_dt + " " + str_crs_str_time + " ~ " + end_crs_dt
										+ " " + str_crs_end_time;
								/*
								 * if(chkEduCurriculumVO.getCRS_GRP_CD().equals(
								 * "CIDE00000000000")){ //온라인 교육이면 String today
								 * = mPublicUtils.currentTime("yyyy-MM-dd");
								 * eduTime = "교육일시 : " + today +
								 * " ~ 상시(집합교육 재개 시까지 한시 운영)"; }
								 */

								String add_msg = "";
								String ment_msg = "";

								SmsMentVO smsMentVO = new SmsMentVO();
								if (chkEduCurriculumVO.getTYPE_GB().equals("offline")) {// 오프라인교육
									add_msg = "낚시전문교육 수강신청 접수완료 안내(" + chkEduCurriculumVO.getCRS_NM() + ")" + "\n\n"
											+ eduTime + "\n" + "교육장소 : " + chkEduCurriculumVO.getCRS_PLACE() + "\n"
											+ "교육장주소지 : " + chkEduCurriculumVO.getCRS_ADDR() + "\n\n";
									if (chkEduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")) { // 낚시어선
																									// 신규,재개자교육
										smsMentVO.setSMS_MENT_SN("32");
									} else {
										if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")) {
											smsMentVO.setSMS_MENT_SN("34");
										} else {
											smsMentVO.setSMS_MENT_SN("34");
										}
									}
								} else {
									add_msg = "낚시전문교육 수강신청 접수완료 안내(" + chkEduCurriculumVO.getCRS_NM() + ")" + "\n\n"
											+ eduTime + "\n" + "교육명 : " + chkEduCurriculumVO.getCRS_PLACE() + "\n"
											+ "교육사이트주소 : " + chkEduCurriculumVO.getCRS_ADDR() + "\n\n";
									if (chkEduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")) { // 낚시어선
																									// 신규,재개자교육
										smsMentVO.setSMS_MENT_SN("32");
									} else {
										if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")) {
											smsMentVO.setSMS_MENT_SN("4");
										} else {
											smsMentVO.setSMS_MENT_SN("5");
										}
									}
									smsMentVO.setSMS_MENT_DTL_CD(chkEduCurriculumVO.getCRS_MBR_CD());// 낚시터=CIDN010200,낚시어선=CIDN010300
								}

								smsMentVO = smsManagerService.get_sms_ment_info(smsMentVO);
								if (smsMentVO != null && smsMentVO.getSMS_MENT_SN() != null
										&& smsMentVO.getSMS_MENT_SN().length() != 0) {
									ment_msg = smsMentVO.getSMS_MENT_CONT();
								}

								SmsSendVO newSmsSendVO = new SmsSendVO();
								// mSmsSendVO.setMSG_TYPE();//서비스에서 자동 처리
								// newSmsSendVO.setAPIKEY();//서비스에서 자동 처리
								// newSmsSendVO.setRSTKEY();//서비스에서 자동 처리
								newSmsSendVO.setMBR_ID(eduMemberVO.getMBR_ID());
								newSmsSendVO.setSMS_MENT_DTL_CD(chkEduCurriculumVO.getCRS_MBR_CD());
								newSmsSendVO.setMSG(add_msg + '\n' + ment_msg);
								// newSmsSendVO.setSTAT();//예약발송일때만5
								newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));// 발신번호
								newSmsSendVO.setR_PHONE(eduMemberVO.getMBR_HP());
								newSmsSendVO.setSUBMSG("낚시전문교육 수강신청 접수완료 안내");
								newSmsSendVO.setIMG_CNT(0);
								newSmsSendVO.setIMG_PATH("");
								newSmsSendVO.setREG_MBR_ID(MASTER_MBR_ID);
								newSmsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);
								newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
								smsManagerService.sendToMessage(newSmsSendVO);
								log_dscrp.append("|신청완료문자발송");

								if (chkEduCurriculumVO.getTYPE_GB().equals("online")) {
									// 카카오 알림톡 발송
									KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
									kakaoAlimTalkVO.setNTCN_TRSM_TEL(eduMemberVO.getMBR_HP());// 연락처
									// (알림톡필수)
									String allEduUrl = "";
									if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010300")) {
										// kakaoAlimTalkVO.setEduUrl1(externalVideoUrl.get(0));//교육동영상URL1
										// kakaoAlimTalkVO.setEduUrl2(externalVideoUrl.get(1));//교육동영상URL2
										// kakaoAlimTalkVO.setEduUrl3(externalVideoUrl.get(2));//교육동영상URL3
										// kakaoAlimTalkVO.setEduUrl4(externalVideoUrl.get(3));//교육동영상URL4
										// kakaoAlimTalkVO.setEduUrl5(externalVideoUrl.get(4));//교육동영상URL5
										// kakaoAlimTalkVO.setEduUrl6(externalVideoUrl.get(5));//교육동영상URL6
										if (chkEduCurriculumVO.getCRS_YEAR().equals("2024")) {
											allEduUrl += externalVideoUrl.get(0) + ",";
											allEduUrl += externalVideoUrl.get(1) + ",";
											allEduUrl += externalVideoUrl.get(2) + ",";
											allEduUrl += externalVideoUrl.get(3) + ",";
											kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000032");
										} else { //20241023 지금 여기타서 문제
											allEduUrl += externalVideoUrl.get(0) + ",";
											allEduUrl += externalVideoUrl.get(1) + ",";
											allEduUrl += externalVideoUrl.get(2) + ",";
											allEduUrl += externalVideoUrl.get(3) + ",";
											allEduUrl += externalVideoUrl.get(4) + ",";
											allEduUrl += externalVideoUrl.get(5) + ",";
											kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000001");// 낚시어선
																									// 전문교육신청
										}
									} else if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")) {
										// kakaoAlimTalkVO.setEduUrl1(externalVideoUrl.get(0));//교육동영상URL1
										// kakaoAlimTalkVO.setEduUrl2(externalVideoUrl.get(1));//교육동영상URL2
										// kakaoAlimTalkVO.setEduUrl3(externalVideoUrl.get(2));//교육동영상URL3
										// kakaoAlimTalkVO.setEduUrl4(externalVideoUrl.get(3));//교육동영상URL4
										allEduUrl += externalVideoUrl.get(0) + ",";
										allEduUrl += externalVideoUrl.get(1) + ",";
										allEduUrl += externalVideoUrl.get(2) + ",";
										allEduUrl += externalVideoUrl.get(3) + ",";
										if (chkEduCurriculumVO.getCRS_YEAR().equals("2023"))
											kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000021");// 낚시터
																									// 전문교육신청
										else
											kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000003");// 낚시터
																									// 전문교육신청
									}
									kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(chkEduCurriculumVO.getCRS_MBR_CD());// 낚시터
																											// 전문교육신청
									kakaoAlimTalkVO.setREG_ID(loginVO.getMBR_ID());
									String surveykey = EgovFileScrty
											.encode(HMBR_SN + "," + newEduMyHistoryVO.getCRS_SN());
									// kakaoAlimTalkVO.setSurveyUrl(domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey);//
									// 설문조사 url
									allEduUrl += domainUrl + "/educenter/mbrhstry/external/survey.do?key=" + surveykey;
									kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");// 동기화
																			// 전송
																			// 여부(true:비동기,false:동기[기본값])
									kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);
									// JSONObject result =
									// kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
									kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
									// End of 카카오 알림톡 발송

								}
							}

						}
					}
					//

					// 연도별교육대상자 확인 및 추가
					if (eduMemberVO.getSearchYear() == null || eduMemberVO.getSearchYear().length() == 0) {
						log_dscrp.append("|년도가지정되지않아교육대상자로등록할수없음");
					} else {
						EduMemberVO chkTargetEduMemberVO = new EduMemberVO();
						chkTargetEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
						chkTargetEduMemberVO.setTRGT_YEAR(eduMemberVO.getSearchYear());
						chkTargetEduMemberVO.setUSE_AT("Y");
						chkTargetEduMemberVO.setNotUsedPagination(true);
						List<EduMemberVO> chkTargetEduList = eduMemberService
								.get_edu_member_target_list(chkTargetEduMemberVO);
						if (chkTargetEduList == null || chkTargetEduList.size() == 0) {
							log_dscrp.append("|" + eduMemberVO.getSearchYear() + "년도 대상자추가완료");
							EduMemberVO newEduMemberVO = new EduMemberVO();
							newEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
							newEduMemberVO.setMBR_CD(eduMemberVO.getMBR_CD());
							newEduMemberVO.setMBR_NM(eduMemberVO.getMBR_NM());
							if (eduMemberVO.getREG_TYPE_CD() == null || eduMemberVO.getREG_TYPE_CD().length() == 0) {
								newEduMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);
							}
							newEduMemberVO.setTRGT_YEAR(eduMemberVO.getSearchYear());
							newEduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
							newEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
							if (eduMemberVO.getCRS_SN() != null && eduMemberVO.getCRS_SN().length() != 0) {
								newEduMemberVO.setCRS_SN(eduMemberVO.getCRS_SN());
								newEduMemberVO.setHMBR_SN(HMBR_SN);
							}
							eduMemberService.set_edu_member_target_reg(newEduMemberVO);
						} else {
							log_dscrp.append("|" + eduMemberVO.getSearchYear() + "년도 대상자로이미등록되어있음");
						}
					}
					//

					// 온라인수강신청자 확인처리
					if (eduMemberVO.getRMNDR_SN() != null && eduMemberVO.getRMNDR_SN().length() != 0) {
						EduMbrRemindersVO updEduMbrRemindersVO = new EduMbrRemindersVO();
						updEduMbrRemindersVO.setUPD_MBR_ID(MASTER_MBR_ID);
						updEduMbrRemindersVO.setRMNDR_SN(eduMemberVO.getRMNDR_SN());
						updEduMbrRemindersVO.setRMNDR_MBR_ID(eduMemberVO.getMBR_ID());
						eduCenterService.set_edu_mbr_reminders_lock_cmplt(updEduMbrRemindersVO);
						log_dscrp.append("|온라인수강신청건처리완료(RMNDR_SN:" + eduMemberVO.getRMNDR_SN() + ")");
					}
					//

					data.put("error", "0");
					data.put("msg", "새로운 회원이 등록되었습니다.");

				}

				log_dscrp.append("]");

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.debug("[fail load data] " + e.toString());
				log_dscrp.append("|등록실패:에러발생][" + eduMemberVO.getMBR_ID() + "]");
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		}

		try {
			/**
			 * LOG RECORED (로그기록)
			 */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
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

		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 - 관리자회원추가 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/write_adm_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_write_adm_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		log_dscrp.append("[교육센터관리자-회원관리-신규관리자회원등록]");
		tbl_inf.append("MBR_TB");

		int is_id_cnt = eduMemberService.get_id_search(eduMemberVO);
		if (is_id_cnt > 0) {
			log_dscrp.append("[등록실패:이미 등록 된 아이디][" + eduMemberVO.getMBR_ID() + "]");

			data.put("error", "1");
			data.put("msg", "이미 등록 된 아이디 입니다.");
		} else {
			try {

				// 직급 코드 조회
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00003");
				List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);

				log_dscrp.append("[이름:" + eduMemberVO.getMBR_NM() + "(아이디:" + eduMemberVO.getMBR_ID() + ")]");

				if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0007")) { // 통합관리자
					eduMemberVO.setMBR_GRP_1_ST("Y");
					eduMemberVO.setMBR_GRP_2_ST("Y");
					eduMemberVO.setMBR_GRP_3_ST("Y");
					eduMemberVO.setMBR_GRP_4_ST("Y");
					eduMemberVO.setMBR_LV_ID("2");
					log_dscrp.append("|2등급 그룹 등록|통합운영자 권한 부여");
				} else if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0005")) { // 낚시누리
					eduMemberVO.setMBR_GRP_1_ST("Y");
					eduMemberVO.setMBR_LV_ID("3");
					log_dscrp.append("|2등급 그룹 등록|낚시누리-공단운영자 권한 부여");
				} else if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0006")) { // 낚시전문교육
					eduMemberVO.setMBR_GRP_2_ST("Y");
					eduMemberVO.setMBR_LV_ID("3");
					log_dscrp.append("|2등급 그룹 등록|낚시전문교육-공단운영자 권한 부여");
				} else if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0002") // 해경
						|| eduMemberVO.getMBR_POSITION_CD().equals("PCD0003") // 지자체
						|| eduMemberVO.getMBR_POSITION_CD().equals("PCD0004") // 교육기관
				) {
					eduMemberVO.setMBR_GRP_2_ST("Y");
					eduMemberVO.setMBR_LV_ID("4");
					String CD_NM = "";
					for (int j = 0; j < list_position_cd.size(); j++) {
						CodeSetVO codeSetVO = (CodeSetVO) list_position_cd.get(j);
						if (codeSetVO.getCD_ID().equals(eduMemberVO.getMBR_POSITION_CD())) {
							CD_NM = codeSetVO.getCD_NM();
							break;
						}
					}
					log_dscrp.append("|2등급 그룹 등록|낚시전문교육-" + CD_NM + " 권한 부여");
				} else if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0009")) { // 낚시명예감시원
					// eduMemberVO.setMBR_GRP_2_ST("Y");
					eduMemberVO.setMBR_LV_ID("5");
					log_dscrp.append("|5등급 그룹 등록|낚시명예감시원-권한 부여");
				} else {
					log_dscrp.append("|그룹 등급 및 권한 부여 불가 조건");
				}

				log_dscrp.append("]");

				// 초기 ㅂㅁ 발급하지 않음 - 사용자가 수정가능.
				// eduMemberVO.setMBR_PWD(EgovFileScrty.encryptPassword(eduMemberVO.getMBR_PWD(),
				// eduMemberVO.getMBR_ID()));
				eduMemberVO.setREG_MBR_ID(loginVO.getMBR_ID());
				eduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				eduMemberService.set_edu_member_reg(eduMemberVO);
				// 사용자사유로그기록
				{
					EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
					logRecordService.set_log_mbr_mod_data("MBR_TB", "신규", "[관리자회원]" + eduMemberVO.getLOG_UPD_MSG(),
							realEduMemberVO.getMBR_ID(), realEduMemberVO.getMBR_NM(), realEduMemberVO, null, loginVO,
							request);
				}

				// *********************************************
				// 관리자 권한 기록
				// *********************************************
				LogAdmAuthVO logAdmAuthVO = new LogAdmAuthVO();

				// 권한명, 권한내용
				String AUTHOR_NM = "";
				String AUTHOR_CN = "";
				switch (eduMemberVO.getMBR_POSITION_CD()) {
				case "PCD0002":
					AUTHOR_NM = "해경";
					AUTHOR_CN = "전체 교육대상자 정보 조회";
					break;
				case "PCD0003":
					if (eduMemberVO.getMBR_SIGNGU_CD() != null && eduMemberVO.getMBR_SIGNGU_CD().length() != 0) {// 시군구
						if (eduMemberVO.getMBR_TRGT_CD().equals("CIDN010200")) {
							AUTHOR_NM = "지자체_시군구_낚시터 담당자";
							AUTHOR_CN = "해당 시군구 내 낚시터 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
						} else if (eduMemberVO.getMBR_TRGT_CD().equals("CIDN010300")) {
							AUTHOR_NM = "지자체_시군구_낚시어선 담당자";
							AUTHOR_CN = "해당 시군구 내 낚시어선 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
						} else {
							AUTHOR_NM = "지자체_시군구_통합 담당자";
							AUTHOR_CN = "해당 시군구 내 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
						}
					} else {// 시도
						if (eduMemberVO.getMBR_TRGT_CD().equals("CIDN010200")) {
							AUTHOR_NM = "지자체_시도_낚시터 담당자";
							AUTHOR_CN = "해당 시도 내 낚시터 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
						} else if (eduMemberVO.getMBR_TRGT_CD().equals("CIDN010300")) {
							AUTHOR_NM = "지자체_시도_낚시어선 담당자";
							AUTHOR_CN = "해당 시도 내 낚시어선 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
						} else {
							AUTHOR_NM = "지자체_시도_통합 담당자";
							AUTHOR_CN = "해당 시도 내 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
						}
					}
					break;
				case "PCD0004":
					AUTHOR_NM = "교육기관_통합";
					AUTHOR_CN = "해당 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
					break;
				case "PCD0005": // 낚시누리, 낚시전문교육은 현재 테이블에 정보가 없음.....참고할만한게 뭐가
								// 있을까요
					AUTHOR_NM = "낚시누리";
					AUTHOR_CN = "";
					break;
				case "PCD0006":
					AUTHOR_NM = "낚시전문교육";
					AUTHOR_CN = "";
					break;
				case "PCD0007":
					AUTHOR_NM = "통합관리자";
					AUTHOR_CN = "전체 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
					break;
				case "PCD0009":
					AUTHOR_NM = "낚시명예감시원";
					AUTHOR_CN = "활동보고서 입력,수정";
					break;
				default:
					AUTHOR_NM = "";
					AUTHOR_CN = "";
					break;
				}

				logAdmAuthVO.setREQST_NM(eduMemberVO.getMBR_NM());// 신청자
				logAdmAuthVO.setREQST_CN(eduMemberVO.getMBR_PURPS());// 신청내용
				logAdmAuthVO.setCONFM_NM(loginVO.getMBR_NM());// 승인자
				logAdmAuthVO.setCONFM_CN(eduMemberVO.getLOG_UPD_MSG());// 승인내용
				logAdmAuthVO.setCONFM_TYPE("권한 부여");// 승인구분
				logAdmAuthVO.setMBR_MSG(eduMemberVO.getMBR_DSCRP());// 사용자수기입력
				logAdmAuthVO.setMBR_LV(eduMemberVO.getMBR_LV_ID());// 권한레벨
				logAdmAuthVO.setAUTHOR_NM(AUTHOR_NM);// 권한명
				logAdmAuthVO.setAUTHOR_CN(AUTHOR_CN);// 권한내용
				logAdmAuthVO.setMBR_USG_DT(eduMemberVO.getMBR_USG_DT());// 관리자
																		// 계정
																		// 사용기간

				eduMemberService.set_edu_member_author_log(logAdmAuthVO);

				data.put("error", "0");
				data.put("msg", "새로운 회원이 등록되었습니다.");

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.debug("[fail load data1] " + e.toString());
				log_dscrp.append("[등록실패:에러발생][" + eduMemberVO.getMBR_ID() + "]");
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		}

		try {
			/**
			 * LOG RECORED (로그기록)
			 */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
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

		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 - 회원수정 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_modify_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@RequestParam(value = "writeType", required = false) String writeType,
			@RequestParam(value = "MHC_SN", required = false, defaultValue = "") String MHC_SN,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		log_dscrp.append("[교육센터관리자-회원관리-회원정보수정]");

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();

		PublicUtils mPublicUtils = new PublicUtils();

		JSONObject data = new JSONObject();
		// 검증
		EduMemberVO chkEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
		if (chkEduMemberVO.getMBR_ID() == null || chkEduMemberVO.getMBR_ID().length() == 0) {
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");

			log_dscrp.append("[존재하지 않는 회원정보를 요청함]");
		} else {

			// 취약점 대응을 위한 처리
			boolean isScrtyKeyRefuse = true;
			try {
				String memberScrtyKey = EgovFileScrty.security(eduMemberVO.getMBR_ID(), MASTER_MBR_ID);
				if (eduMemberVO.getMBR_SCRTY_KEY().equals(memberScrtyKey)) {
					isScrtyKeyRefuse = true;
				} else {
					isScrtyKeyRefuse = false;
				}
				LOGGER.debug("reqmemberScrtyKey : " + eduMemberVO.getMBR_SCRTY_KEY());
				LOGGER.debug("memberScrtyKey : " + memberScrtyKey);
			} catch (Exception e) {
				LOGGER.debug("uniqAdminScrtyKey null ");
				isScrtyKeyRefuse = false;
			}
			// End of 취약점 대응을 위한 처리

			if (!isScrtyKeyRefuse) {
				data.put("error", "1");
				data.put("msg", "비정상적인 접근으로 거부되었습니다.");
			} else {

				try {

					log_dscrp.append("[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")]");
					if (writeType != null && writeType.equals("rmndr")) {
						if (eduMemberVO.getLOG_UPD_MSG() == null || eduMemberVO.getLOG_UPD_MSG().length() == 0)
							eduMemberVO.setLOG_UPD_MSG("온라인교육신청자 업데이트 건");
					} else {
						if (eduMemberVO.getLOG_UPD_MSG() == null || eduMemberVO.getLOG_UPD_MSG().length() == 0)
							eduMemberVO.setLOG_UPD_MSG("사용자 업데이트 건");
					}

					if (chkEduMemberVO.getMBR_ST().equals("N")) {
						log_dscrp.append("|비활성화상태의 회원이므로 활성화 처리함");
						eduMemberVO.setMBR_ST("Y");
					}
					if (eduMemberVO.getREG_TYPE_CD() == null || eduMemberVO.getREG_TYPE_CD().length() == 0) {
						eduMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);
					}
					if (eduMemberVO.getAddConfirmSubmit() != null && eduMemberVO.getAddConfirmSubmit().equals("Y")
							&& (eduMemberVO.getMBR_ST() == null || eduMemberVO.getMBR_ST().length() == 0)) {
						eduMemberVO.setMBR_ST("Y");
					}
					if (MASTER_MBR_POSITION_CD.equals("PCD0003")) { // 담당자가 지자체
																	// 인 경우 ,
																	// 지자체가
																	// 관리주체가 된다.
						if (eduMemberVO.getMBR_ST().equals("N")) {// 삭제시 공단으로 이관
							eduMemberVO.setMBR_REG_TYPE_CD("");
							eduMemberService.set_edu_member_trnsfer(eduMemberVO);
							log_dscrp.append("[비활성화:회원관리주체변경(지자체=>공단)으로이관]");
						} else if (eduMemberVO.getMBR_ST().equals("Y")) {
							eduMemberVO.setMBR_REG_TYPE_CD(MASTER_MBR_POSITION_CD);
							eduMemberService.set_edu_member_trnsfer(eduMemberVO);
							log_dscrp.append("[회원관리주체변경(지자체)으로이관]");
						}
						eduMemberVO.setMBR_SIDO_CD(MASTER_MBR_SIDO_CD);
						eduMemberVO.setMBR_SIGNGU_CD(MASTER_MBR_SIGNGU_CD);
					}
					eduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
					eduMemberVO.setMBR_ADDR2_UPD_YN("Y");
					eduMemberService.set_edu_member_mod(eduMemberVO);

					EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
					// 사용자사유로그기록
					{
						logRecordService.set_log_mbr_mod_data("MBR_TB", "수정", eduMemberVO.getLOG_UPD_MSG(),
								realEduMemberVO.getMBR_ID(), realEduMemberVO.getMBR_NM(), chkEduMemberVO,
								realEduMemberVO, loginVO, request);
					}
					log_dscrp.append("|회원정보수정완료");

					// CTI로그기록
					if (eduMemberVO.getLOG_TYPE() != null && eduMemberVO.getLOG_TYPE().length() != 0) {
						if (eduMemberVO.getLOG_TYPE().equals("cti")) {
							// CTI LOG 기록
							logRecordService.set_log_cti_data("개인정보변경", log_dscrp.toString(), log_msg.toString(),
									eduMemberVO.getLOG_UPD_MSG(), chkEduMemberVO, realEduMemberVO, loginVO, request);
							// End of CTI LOG 기록
						}
					}
					// End of CTI로그기록

					// 회원상세정보
					int cntDtlSuccess = 0;
					int cntDtlFail = 0;
					int cntDtlInsert = 0;
					int cntDtlUpdate = 0;
					int cntDtlDelete = 0;
					try {
						String[] DTL_SNs = eduMemberVO.getDTL_SN().split(",", -1);
						String[] DTL_NMs = eduMemberVO.getDTL_NM().split(",", -1);
						String[] DTL_CDs = eduMemberVO.getDTL_CD().split(",", -1);
						String[] SIDO_CDs = eduMemberVO.getSIDO_CD().split(",", -1);
						String[] SIGNGU_CDs = eduMemberVO.getSIGNGU_CD().split(",", -1);
						String[] YMD_NMs = eduMemberVO.getYMD_NM().split(",", -1);
						String[] DTL_ADDRs = eduMemberVO.getDTL_ADDR().split(",", -1);
						String[] DTL_LICENSE_CDs = eduMemberVO.getDTL_LICENSE_CD().split(",", -1);
						String[] BUSINESS_NUMs = eduMemberVO.getBUSINESS_NUM().split(",", -1);
						String[] REG_NUM_CDs = eduMemberVO.getREG_NUM_CD().split(",", -1);
						String[] SHIP_CDs = eduMemberVO.getSHIP_CD().split(",", -1);
						String[] SHIP_GRTGs = eduMemberVO.getSHIP_GRTG().split(",", -1);
						String[] SHIP_PRLOADs = eduMemberVO.getSHIP_PRLOAD().split(",", -1);
						String[] SHIP_MAX_PSNGERs = eduMemberVO.getSHIP_MAX_PSNGER().split(",", -1);
						String[] SHIP_MAX_CREWs = eduMemberVO.getSHIP_MAX_CREW().split(",", -1);
						String[] SHIP_LICENSEs = eduMemberVO.getSHIP_LICENSE().split(",", -1);
						String[] SHIP_LICENSE_STR_DTs = eduMemberVO.getSHIP_LICENSE_STR_DT().split(",", -1);
						String[] SHIP_LICENSE_END_DTs = eduMemberVO.getSHIP_LICENSE_END_DT().split(",", -1);
						String[] FSHLC_APPLCs = eduMemberVO.getFSHLC_APPLC().split(",", -1);
						String[] FSHLC_WORK_CDs = eduMemberVO.getFSHLC_WORK_CD().split(",", -1);
						String[] USE_ATs = eduMemberVO.getUSE_AT().split(",", -1);
						HashMap<String, EduMemberVO> arrUpdDtlItem = new HashMap<String, EduMemberVO>();
						// 상세정보삭제구간
						eduMemberVO.setDTL_CD("");// 낚시터, 낚시어선 둘다 할 경우를 위해
						List<EduMemberVO> list_dtl = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
						if ((eduMemberVO.getRMNDR_SN() != null && eduMemberVO.getRMNDR_SN().length() != 0)
								|| (writeType != null && writeType.equals("eduTargetAdd"))) {
							// 온라인수강신청의 경우에는 삭제없이 추가만
							// 교육대상자 관리 - 교육대상자 등록하기도 삭제없이 추가만
						} else {
							try {
								for (EduMemberVO item_dtl : list_dtl) {
									boolean isDelete = true;
									for (int i = 0; i < DTL_NMs.length; i++) {
										if (item_dtl.getDTL_SN().equals(DTL_SNs[i])) {
											isDelete = false;
											arrUpdDtlItem.put("DTL_SN_" + item_dtl.getDTL_SN(), item_dtl);
										}
									}
									if (isDelete) {

										// 사용자사유로그기록
										{
											logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "삭제",
													eduMemberVO.getLOG_UPD_MSG(), item_dtl.getMBR_ID(),
													item_dtl.getMBR_NM(), item_dtl, null, loginVO, request);
										}
										eduMemberService.remove_edu_member_dtl(item_dtl);
										log_dscrp.append(
												"|" + item_dtl.getDTL_NM() + "(" + item_dtl.getDTL_SN() + ") 상세정보삭제");

										// CTI로그기록
										if (eduMemberVO.getLOG_TYPE() != null
												&& eduMemberVO.getLOG_TYPE().length() != 0) {
											if (eduMemberVO.getLOG_TYPE().equals("cti")) {
												// CTI LOG 기록
												logRecordService.set_log_cti_data("개인정보변경:상세정보삭제", log_dscrp.toString(),
														log_msg.toString(), eduMemberVO.getLOG_UPD_MSG(), item_dtl,
														null, loginVO, request);
												// End of CTI LOG 기록
											}
										}
										// End of CTI로그기록

										cntDtlDelete++;
										cntDtlSuccess++;
									}
								}
							} catch (Exception e) {
								log_msg.append("삭제실패에러:" + e.toString());
								log_dscrp.append("|상세정보삭제실패");
								cntDtlSuccess++;
							}
						}

						// 상세정보 추가 및 수정 구간
						for (int i = 0; i < DTL_NMs.length; i++) {
							try {
								EduMemberVO eduDtlMemberVO = new EduMemberVO();
								eduDtlMemberVO.setDTL_SN(DTL_SNs[i]);
								eduDtlMemberVO.setMBR_CD(eduMemberVO.getMBR_ID());
								eduDtlMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
								eduDtlMemberVO.setMBR_NM(eduMemberVO.getMBR_NM());
								eduDtlMemberVO.setDTL_CD(DTL_CDs[i]);
								eduDtlMemberVO.setDTL_NM(DTL_NMs[i]);
								eduDtlMemberVO.setSIDO_CD(SIDO_CDs[i]);
								eduDtlMemberVO.setSIGNGU_CD(SIGNGU_CDs[i]);
								eduDtlMemberVO.setYMD_NM(YMD_NMs[i]);
								eduDtlMemberVO.setDTL_ADDR(DTL_ADDRs[i]);
								eduDtlMemberVO.setDTL_LICENSE_CD(DTL_LICENSE_CDs[i]);
								eduDtlMemberVO.setBUSINESS_NUM(BUSINESS_NUMs[i]);
								eduDtlMemberVO.setREG_NUM_CD(REG_NUM_CDs[i]);
								eduDtlMemberVO.setSHIP_CD(SHIP_CDs[i]);
								eduDtlMemberVO.setSHIP_GRTG(SHIP_GRTGs[i]);
								eduDtlMemberVO.setSHIP_PRLOAD(SHIP_PRLOADs[i]);
								eduDtlMemberVO.setSHIP_MAX_PSNGER(SHIP_MAX_PSNGERs[i]);
								eduDtlMemberVO.setSHIP_MAX_CREW(SHIP_MAX_CREWs[i]);
								eduDtlMemberVO.setSHIP_LICENSE(SHIP_LICENSEs[i]);
								eduDtlMemberVO.setSHIP_LICENSE_STR_DT(SHIP_LICENSE_STR_DTs[i]);
								eduDtlMemberVO.setSHIP_LICENSE_END_DT(SHIP_LICENSE_END_DTs[i]);
								eduDtlMemberVO.setFSHLC_APPLC(FSHLC_APPLCs[i]);
								eduDtlMemberVO.setFSHLC_WORK_CD(FSHLC_WORK_CDs[i]);
								eduDtlMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
								eduDtlMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
								eduDtlMemberVO.setUSE_AT(USE_ATs[i]);
								if (DTL_SNs[i] == null || DTL_SNs[i].length() == 0) {
									log_dscrp.append("|" + eduDtlMemberVO.getDTL_NM() + " 상세정보추가");
									eduDtlMemberVO.setREG_TYPE_CD(eduMemberVO.getREG_TYPE_CD());
									eduMemberService.set_edu_member_dtl_reg(eduDtlMemberVO);
									// 사용자사유로그기록
									{
										logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "추가",
												eduMemberVO.getLOG_UPD_MSG(), eduDtlMemberVO.getMBR_ID(),
												eduDtlMemberVO.getMBR_NM(), null, eduDtlMemberVO, loginVO, request);
									}

									// CTI로그기록
									if (eduMemberVO.getLOG_TYPE() != null && eduMemberVO.getLOG_TYPE().length() != 0) {
										if (eduMemberVO.getLOG_TYPE().equals("cti")) {
											// CTI LOG 기록
											logRecordService.set_log_cti_data("개인정보변경:상세정보추가", log_dscrp.toString(),
													log_msg.toString(), eduMemberVO.getLOG_UPD_MSG(), null,
													eduDtlMemberVO, loginVO, request);
											// End of CTI LOG 기록
										}
									}
									// End of CTI로그기록

									cntDtlInsert++;
								} else {
									log_dscrp.append("|" + eduDtlMemberVO.getDTL_NM() + "(" + eduDtlMemberVO.getDTL_SN()
											+ ") 상세정보갱신");

									EduMemberVO chkUpdEduMemberVO = (EduMemberVO) arrUpdDtlItem
											.get("DTL_SN_" + DTL_SNs[i]);
									if (chkUpdEduMemberVO.getREG_TYPE_CD() != null
											&& chkUpdEduMemberVO.getREG_TYPE_CD().equals("PCD0003")) { // 지자체인
																										// 경우
																										// 변경하지
																										// 않는다.
										eduDtlMemberVO.setREG_TYPE_CD(chkUpdEduMemberVO.getREG_TYPE_CD());
									} else {
										if (MASTER_MBR_POSITION_CD.equals("PCD0003")) { // 담당자가
																						// 지자체
																						// 인
																						// 경우
																						// ,
																						// 지자체가
																						// 관리주체가
																						// 된다.
											eduDtlMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);
											log_dscrp.append("[상세정보회원관리주체변경(지자체)으로이관]");
										} else {
											eduDtlMemberVO.setREG_TYPE_CD(eduMemberVO.getREG_TYPE_CD());
										}
									}
									String updtBfe = chkUpdEduMemberVO.getSIDO_CD() + chkUpdEduMemberVO.getSIGNGU_CD();
									String updtAft = eduDtlMemberVO.getSIDO_CD() + eduDtlMemberVO.getSIGNGU_CD();
									if (!updtBfe.equals(updtAft)) {
										eduDtlMemberVO.setSIDO_CD_CHG_DT("1");
									}
									eduMemberService.set_edu_member_dtl_mod(eduDtlMemberVO);

									EduMemberVO realDtlEduMemberVO = eduMemberService
											.get_edu_member_dtl_info(eduDtlMemberVO);
									// 사용자사유로그기록
									{
										logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "수정",
												eduMemberVO.getLOG_UPD_MSG(), realDtlEduMemberVO.getMBR_ID(),
												realDtlEduMemberVO.getMBR_NM(), chkUpdEduMemberVO, realDtlEduMemberVO,
												loginVO, request);
									}

									// CTI로그기록
									if (eduMemberVO.getLOG_TYPE() != null && eduMemberVO.getLOG_TYPE().length() != 0) {
										if (eduMemberVO.getLOG_TYPE().equals("cti")) {
											// CTI LOG 기록
											logRecordService.set_log_cti_data("개인정보변경:상세정보수정", log_dscrp.toString(),
													log_msg.toString(), eduMemberVO.getLOG_UPD_MSG(), chkUpdEduMemberVO,
													realDtlEduMemberVO, loginVO, request);
											// End of CTI LOG 기록
										}
									}
									// End of CTI로그기록

									cntDtlUpdate++;
								}
								cntDtlSuccess++;
							} catch (Exception e) {
								// e.printStackTrace();
								log_msg.append("상세정보등록에러:" + e.toString());
								cntDtlFail++;
							}
						}

					} catch (NullPointerException en) {
						log_dscrp.append("|상세정보추가등록건없음(상세정보전체삭제)");
						List<EduMemberVO> list_dtl = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
						for (EduMemberVO item_dtl : list_dtl) {
							// 사용자사유로그기록
							{
								logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "삭제", eduMemberVO.getLOG_UPD_MSG(),
										item_dtl.getMBR_ID(), item_dtl.getMBR_NM(), item_dtl, null, loginVO, request);
							}
							eduMemberService.remove_edu_member_dtl(item_dtl);
							log_dscrp.append("|" + item_dtl.getDTL_NM() + "(" + item_dtl.getDTL_SN() + ") 상세정보삭제");

							// CTI로그기록
							if (eduMemberVO.getLOG_TYPE() != null && eduMemberVO.getLOG_TYPE().length() != 0) {
								if (eduMemberVO.getLOG_TYPE().equals("cti")) {
									// CTI LOG 기록
									logRecordService.set_log_cti_data("개인정보변경:상세정보삭제", log_dscrp.toString(),
											log_msg.toString(), eduMemberVO.getLOG_UPD_MSG(), item_dtl, null, loginVO,
											request);
									// End of CTI LOG 기록
								}
							}
							// End of CTI로그기록

							cntDtlDelete++;
							cntDtlSuccess++;
						}
						// eduMemberService.remove_edu_member_dtl(chkEduMemberVO);//전체
						// 삭제에서 개별 조회 후 삭제로 수정
					} catch (Exception e) {
						log_msg.append("상세정보처리에러:" + e.toString());
						log_dscrp.append("|상세정보처리에러");
					}
					log_dscrp.append("|상세정보등록(총:" + cntDtlSuccess + "건,실패:" + cntDtlFail + "건)(추가:" + cntDtlInsert
							+ ",변경:" + cntDtlUpdate + ",삭제:" + cntDtlDelete + ")");

					// 교육수강신청 확인 및 추가
					String HMBR_SN = "";
					if (eduMemberVO.getCRS_SN() == null || eduMemberVO.getCRS_SN().length() == 0) {
						log_dscrp.append("|교육과정을선택하지않아서신청불가");
					} else {
						log_dscrp.append("|교육과정신청");
						EduCurriculumVO chkEduCurriculumVO = new EduCurriculumVO();
						chkEduCurriculumVO.setCRS_SN(eduMemberVO.getCRS_SN());
						chkEduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(chkEduCurriculumVO);
						if (chkEduCurriculumVO == null || chkEduCurriculumVO.getCRS_SN() == null
								|| chkEduCurriculumVO.getCRS_SN().length() == 0) {
							log_dscrp.append("|실패:교육과정정보가존재하지않음");
						} else {
							EduMyHistoryVO chkEduMyHistoryVO = new EduMyHistoryVO();
							chkEduMyHistoryVO.setCRS_SN(eduMemberVO.getCRS_SN());
							chkEduMyHistoryVO.setMBR_ID(eduMemberVO.getMBR_ID());
							chkEduMyHistoryVO.setNotUsedPagination(true);
							List<EduMyHistoryVO> list_mbrhstry = eduMyHistoryService
									.get_edu_mbrhstry_list(chkEduMyHistoryVO);
							if (list_mbrhstry != null && list_mbrhstry.size() > 0) {
								log_dscrp.append("," + eduMemberVO.getMBR_NM() + "|이미등록된수강생으로처리안함");
							} else {
								List<String> externalVideoUrl = new ArrayList<>();// 외부
																					// 동영상
																					// 링크
																					// URL
								String domainUrl = request.getScheme() + "://" + request.getServerName() + ":"
										+ request.getServerPort();
								// 커리큘럼 상세
								chkEduCurriculumVO.setNotUsedPagination(true);
								List<EduCurriculumVO> clildlist = eduCurriculumService
										.get_edu_curriculum_dtl_list(chkEduCurriculumVO);

								int HMBR_MAX_TIME = Integer.parseInt(chkEduCurriculumVO.getCRS_TOT_TIME())
										+ chkEduCurriculumVO.getSUM_TOT_TIME();
								int HMBR_MAX_POINT = Integer.parseInt(chkEduCurriculumVO.getCRS_TOT_POINT())
										+ chkEduCurriculumVO.getSUM_TOT_POINT();
								EduMyHistoryVO newEduMyHistoryVO = new EduMyHistoryVO();
								newEduMyHistoryVO.setPURCHASE_CMPLT_ST("1");// 결제(구매)
																			// 처리는
																			// 현재
																			// 보류
								newEduMyHistoryVO.setCRS_SN(chkEduCurriculumVO.getCRS_SN());
								newEduMyHistoryVO.setMBR_ID(eduMemberVO.getMBR_ID());
								newEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
								newEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
								newEduMyHistoryVO.setHMBR_MAX_TIME(String.valueOf(HMBR_MAX_TIME));
								newEduMyHistoryVO.setHMBR_MAX_POINT(String.valueOf(HMBR_MAX_POINT));
								// 중복 검증 구간
								{
									boolean isOk = false;
									do {
										HMBR_SN = newEduMyHistoryVO.getUniqKey("HMBR");
										isOk = eduMyHistoryService.get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(HMBR_SN);
									} while (!isOk);
								}
								// End of 중복 검증 구간
								newEduMyHistoryVO.setHMBR_SN(HMBR_SN);

								// 메인 커리큘럼 정보 관련 나의이력 생성
								eduMyHistoryService.set_edu_mbrhstry_reg(newEduMyHistoryVO);

								log_dscrp.append("," + eduMemberVO.getMBR_NM() + "|수강생등록완료");
								tbl_inf.append("EDU_MBR_HSTRY_TB,");
								tbl_sn.append(HMBR_SN + ",");

								// 상세 커리큘럼 정보 만큼 나의상세이력생성
								int insertChildCount = 1;
								for (EduCurriculumVO crs_dtl : clildlist) {
									int insertSubCount = 0;
									String[] trnids = new String[1];
									if (crs_dtl != null && crs_dtl.getTRN_SN() != null
											&& crs_dtl.getTRN_SN().length() != 0)
										trnids = crs_dtl.getTRN_SN().replaceAll("\\s", "").split(",");
									for (String TRN_SN : trnids) {
										String CRS_TOT_TIME = crs_dtl.getCRS_TOT_TIME();
										String CRS_TOT_POINT = crs_dtl.getCRS_TOT_POINT();
										if (TRN_SN == null)
											continue;
										if (insertSubCount != 0) {
											CRS_TOT_TIME = "0";
											CRS_TOT_POINT = "0";
										}

										EduTrainingDataVO eduTrainingDataVO = new EduTrainingDataVO();
										eduTrainingDataVO.setTRN_SN(TRN_SN);
										EduTrainingDataVO tdata = eduTrainingDataService
												.get_edu_data_info(eduTrainingDataVO);

										EduMyHistoryVO newDtlEduMyHistoryVO = new EduMyHistoryVO();
										String HMBR_DTL_SN = "";
										// 중복 검증 구간
										{
											boolean isOk = false;
											do {
												HMBR_DTL_SN = newDtlEduMyHistoryVO.getUniqKey("HMBRD");
												isOk = eduMyHistoryService
														.get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(HMBR_DTL_SN);
											} while (!isOk);
										}
										// End of 중복 검증 구간
										newDtlEduMyHistoryVO.setHMBR_ORD(String.valueOf(insertChildCount));
										newDtlEduMyHistoryVO.setTRN_MAX_TIME(tdata.getTRN_MAX_TIME());
										newDtlEduMyHistoryVO.setTRN_SN(TRN_SN);
										newDtlEduMyHistoryVO.setHMBR_SN(HMBR_SN);
										newDtlEduMyHistoryVO.setCRS_SN(crs_dtl.getCRS_SN());
										newDtlEduMyHistoryVO.setCRS_DTL_SN(crs_dtl.getCRS_DTL_SN());
										newDtlEduMyHistoryVO.setCAT_SN(crs_dtl.getCAT_SN());
										newDtlEduMyHistoryVO.setCAT_DTL_SN(crs_dtl.getCAT_DTL_SN());
										newDtlEduMyHistoryVO.setLRNNG_MAX_TIME(CRS_TOT_TIME);
										newDtlEduMyHistoryVO.setLRNNG_MAX_POINT(CRS_TOT_POINT);
										newDtlEduMyHistoryVO.setMBR_ID(eduMemberVO.getMBR_ID());
										newDtlEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
										newDtlEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
										newDtlEduMyHistoryVO.setHMBR_DTL_SN(HMBR_DTL_SN);
										eduMyHistoryService.set_edu_mbrhstry_reg_dtl(newDtlEduMyHistoryVO);

										// 외부 동영상 URL 생성
										String eduUrl = domainUrl + "/educenter/mbrhstry/external/play.do?key="
												+ EgovFileScrty.encode(HMBR_DTL_SN);
										externalVideoUrl.add(eduUrl);
										// End of 외부 동영상 URL 생성

										tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
										tbl_sn.append(HMBR_DTL_SN + ",");

										insertSubCount++;
									}
									insertChildCount++;
								}
								// 메인 커리큘럼에 회원 카운트 증가
								eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(chkEduCurriculumVO);

								// SMS자동발송(즉시)
								{
									String str_crs_dt = mPublicUtils.changePatternString(
											chkEduCurriculumVO.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss",
											"yyyy-MM-dd");
									String end_crs_dt = mPublicUtils.changePatternString(
											chkEduCurriculumVO.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss",
											"yyyy-MM-dd");
									String str_crs_str_time = mPublicUtils.changePatternString(
											chkEduCurriculumVO.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss",
											"HH:mm");
									String str_crs_end_time = mPublicUtils.changePatternString(
											chkEduCurriculumVO.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss",
											"HH:mm");

									String eduTime = "교육일시 : " + str_crs_dt + " " + str_crs_str_time + " ~ "
											+ end_crs_dt + " " + str_crs_end_time;
									/*
									 * if(chkEduCurriculumVO.getCRS_GRP_CD().
									 * equals("CIDE00000000000")){ //온라인 교육이면
									 * String today =
									 * mPublicUtils.currentTime("yyyy-MM-dd");
									 * eduTime = "교육일시 : " + today +
									 * " ~ 상시(집합교육 재개 시까지 한시 운영)"; }
									 */

									String add_msg = "";
									String ment_msg = "";
									SmsMentVO smsMentVO = new SmsMentVO();

									if (chkEduCurriculumVO.getTYPE_GB().equals("offline")) {// 오프라인교육
										add_msg = "낚시전문교육 수강신청 접수완료 안내(" + chkEduCurriculumVO.getCRS_NM() + ")" + "\n\n"
												+ eduTime + "\n" + "교육장소 : " + chkEduCurriculumVO.getCRS_PLACE() + "\n"
												+ "교육장주소지 : " + chkEduCurriculumVO.getCRS_ADDR() + "\n\n";
										if (chkEduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")) { // 낚시어선
																										// 신규,재개자교육
											smsMentVO.setSMS_MENT_SN("32");
										} else {
											if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")) {
												smsMentVO.setSMS_MENT_SN("34");
											} else {
												smsMentVO.setSMS_MENT_SN("34");
											}
										}
									} else {
										add_msg = "낚시전문교육 수강신청 접수완료 안내(" + chkEduCurriculumVO.getCRS_NM() + ")" + "\n\n"
												+ eduTime + "\n" + "교육명 : " + chkEduCurriculumVO.getCRS_PLACE() + "\n"
												+ "교육사이트주소 : " + chkEduCurriculumVO.getCRS_ADDR() + "\n\n";
										if (chkEduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")) { // 낚시어선
																										// 신규,재개자교육
											smsMentVO.setSMS_MENT_SN("32");
										} else {
											if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")) {
												smsMentVO.setSMS_MENT_SN("4");
											} else {
												smsMentVO.setSMS_MENT_SN("5");
											}
										}
										smsMentVO.setSMS_MENT_DTL_CD(chkEduCurriculumVO.getCRS_MBR_CD());// 낚시터=CIDN010200,낚시어선=CIDN010300
									}

									smsMentVO = smsManagerService.get_sms_ment_info(smsMentVO);
									if (smsMentVO != null && smsMentVO.getSMS_MENT_SN() != null
											&& smsMentVO.getSMS_MENT_SN().length() != 0) {
										ment_msg = smsMentVO.getSMS_MENT_CONT();
									}

									SmsSendVO newSmsSendVO = new SmsSendVO();
									// mSmsSendVO.setMSG_TYPE();//서비스에서 자동 처리
									// newSmsSendVO.setAPIKEY();//서비스에서 자동 처리
									// newSmsSendVO.setRSTKEY();//서비스에서 자동 처리
									newSmsSendVO.setMBR_ID(eduMemberVO.getMBR_ID());
									newSmsSendVO.setSMS_MENT_DTL_CD(chkEduCurriculumVO.getCRS_MBR_CD());
									newSmsSendVO.setMSG(add_msg + '\n' + ment_msg);
									// newSmsSendVO.setSTAT();//예약발송일때만5
									newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));// 발신번호
									newSmsSendVO.setR_PHONE(eduMemberVO.getMBR_HP());
									newSmsSendVO.setSUBMSG("낚시전문교육 수강신청 접수완료 안내");
									newSmsSendVO.setIMG_CNT(0);
									newSmsSendVO.setIMG_PATH("");
									newSmsSendVO.setREG_MBR_ID(MASTER_MBR_ID);
									newSmsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);
									newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
									smsManagerService.sendToMessage(newSmsSendVO);
									log_dscrp.append("|신청완료문자발송");

									if (chkEduCurriculumVO.getTYPE_GB().equals("online")) {
										if (chkEduCurriculumVO.getEDU_APLY_NTCN_SNDNG_YN().equals("Y")) {
											// //카카오 알림톡 발송
											KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
											kakaoAlimTalkVO.setNTCN_TRSM_TEL(eduMemberVO.getMBR_HP());// 연락처
											// (알림톡필수)
											String allEduUrl = "";
											if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010300")) {
												// kakaoAlimTalkVO.setEduUrl1(externalVideoUrl.get(0));//교육동영상URL1
												// kakaoAlimTalkVO.setEduUrl2(externalVideoUrl.get(1));//교육동영상URL2
												// kakaoAlimTalkVO.setEduUrl3(externalVideoUrl.get(2));//교육동영상URL3
												// kakaoAlimTalkVO.setEduUrl4(externalVideoUrl.get(3));//교육동영상URL4
												// kakaoAlimTalkVO.setEduUrl5(externalVideoUrl.get(4));//교육동영상URL5
												// kakaoAlimTalkVO.setEduUrl6(externalVideoUrl.get(5));//교육동영상URL6
												String currentDt = mPublicUtils.currentTime("yyyy-MM");
												if (chkEduCurriculumVO.getCRS_YEAR().equals("2023")) {
													allEduUrl += externalVideoUrl.get(0) + ",";
													allEduUrl += externalVideoUrl.get(1) + ",";
													allEduUrl += externalVideoUrl.get(2) + ",";
													allEduUrl += externalVideoUrl.get(3) + ",";
													kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000019");// 낚시어선
																											// 전문교육신청
												} else if (chkEduCurriculumVO.getCRS_YEAR().equals("2024")) {// 24년
																												// 교육신청
																												// 카톡
																												// 자동발송
																												// 김현태추가
													// 교육신청서 오류 김현태 추가
													// System.out.println("externalVideoUrl1="+externalVideoUrl);
													allEduUrl += externalVideoUrl.get(0) + ",";
													allEduUrl += externalVideoUrl.get(1) + ",";
													allEduUrl += externalVideoUrl.get(2) + ",";
													allEduUrl += externalVideoUrl.get(3) + ",";
													kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000032");// 낚시어선
																											// 전문교육신청
												} else {
													allEduUrl += externalVideoUrl.get(0) + ",";
													allEduUrl += externalVideoUrl.get(1) + ",";
													allEduUrl += externalVideoUrl.get(2) + ",";
													allEduUrl += externalVideoUrl.get(3) + ",";
													allEduUrl += externalVideoUrl.get(4) + ",";
													allEduUrl += externalVideoUrl.get(5) + ",";
													kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000001");// 낚시어선
																											// 전문교육신청
												}
											} else if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")) {
												// kakaoAlimTalkVO.setEduUrl1(externalVideoUrl.get(0));//교육동영상URL1
												// kakaoAlimTalkVO.setEduUrl2(externalVideoUrl.get(1));//교육동영상URL2
												// kakaoAlimTalkVO.setEduUrl3(externalVideoUrl.get(2));//교육동영상URL3
												// kakaoAlimTalkVO.setEduUrl4(externalVideoUrl.get(3));//교육동영상URL4
												allEduUrl += externalVideoUrl.get(0) + ",";
												allEduUrl += externalVideoUrl.get(1) + ",";
												allEduUrl += externalVideoUrl.get(2) + ",";
												allEduUrl += externalVideoUrl.get(3) + ",";
												String currentDt = mPublicUtils.currentTime("yyyy-MM");
												if (chkEduCurriculumVO.getCRS_YEAR().equals("2023"))
													kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000021");// 낚시터
																											// 전문교육신청
												else if (chkEduCurriculumVO.getCRS_YEAR().equals("2024")) {// 24년
																											// 교육신청
																											// 카톡
																											// 자동발송
																											// 김현태추가
													// System.out.println("externalVideoUrl2="+externalVideoUrl);
													kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000034");
												} else
													kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000003");// 낚시터
																											// 전문교육신청
											}
											kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(chkEduCurriculumVO.getCRS_MBR_CD());// 낚시터
																													// 전문교육신청

											String surveykey = EgovFileScrty
													.encode(HMBR_SN + "," + newEduMyHistoryVO.getCRS_SN());
											// kakaoAlimTalkVO.setSurveyUrl(domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey);//
											// 설문조사 url
											allEduUrl += domainUrl + "/educenter/mbrhstry/external/survey.do?key="
													+ surveykey;
											kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");// 동기화
																					// 전송
																					// 여부(true:비동기,false:동기[기본값])
											kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);
											kakaoAlimTalkVO.setREG_ID(loginVO.getMBR_ID());
											// JSONObject result =
											// kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
											kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
											// End of 카카오 알림톡 발송
										}
									}

								}
							}
						}
					}
					//

					// 연도별교육대상자 확인 및 추가
					if (eduMemberVO.getSearchYear() == null || eduMemberVO.getSearchYear().length() == 0) {
						log_dscrp.append("|년도가지정되지않아교육대상자로등록할수없음");
					} else {
						EduMemberVO chkTargetEduMemberVO = new EduMemberVO();
						chkTargetEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
						chkTargetEduMemberVO.setTRGT_YEAR(eduMemberVO.getSearchYear());
						chkTargetEduMemberVO.setUSE_AT("Y");
						chkTargetEduMemberVO.setNotUsedPagination(true);
						List<EduMemberVO> chkTargetEduList = eduMemberService
								.get_edu_member_target_list(chkTargetEduMemberVO);
						if (chkTargetEduList == null || chkTargetEduList.size() == 0) {
							log_dscrp.append("|" + eduMemberVO.getSearchYear() + "년도 대상자추가완료");
							EduMemberVO newEduMemberVO = new EduMemberVO();
							newEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
							newEduMemberVO.setMBR_CD(eduMemberVO.getMBR_CD());
							newEduMemberVO.setMBR_NM(eduMemberVO.getMBR_NM());
							if (eduMemberVO.getREG_TYPE_CD() == null || eduMemberVO.getREG_TYPE_CD().length() == 0) {
								newEduMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);
							}
							newEduMemberVO.setTRGT_YEAR(eduMemberVO.getSearchYear());
							newEduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
							newEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
							if (eduMemberVO.getCRS_SN() != null && eduMemberVO.getCRS_SN().length() != 0) {
								newEduMemberVO.setCRS_SN(eduMemberVO.getCRS_SN());
								newEduMemberVO.setHMBR_SN(HMBR_SN);
							}
							eduMemberService.set_edu_member_target_reg(newEduMemberVO);
						} else {
							log_dscrp.append("|" + eduMemberVO.getSearchYear() + "년도 대상자로이미등록되어있음");

							boolean isExistTargetBean = false;
							EduMemberVO updEduMemberVO = new EduMemberVO();
							updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
							updEduMemberVO.setTRGT_YEAR(eduMemberVO.getSearchYear());
							updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
							updEduMemberVO.setCRS_SN_NULL("Y");
							List<EduMemberVO> chkTargetEduBeanList = eduMemberService
									.get_edu_member_target_all_list(updEduMemberVO);
							if (chkTargetEduBeanList == null || chkTargetEduBeanList.size() == 0) {
								isExistTargetBean = false;
							} else {
								isExistTargetBean = true;
							}
							if (isExistTargetBean) {
								log_dscrp.append(",|" + eduMemberVO.getSearchYear() + "년도 빈연도별수강내역에업데이트");

								updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
								updEduMemberVO.setTRGT_YEAR(eduMemberVO.getSearchYear());
								updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
								updEduMemberVO.setCRS_SN_NULL("Y");// CRS_SN
																	// null 일때
								updEduMemberVO.setCRS_SN_NOT_NULL("");// CRS_SN
																		// 일때
								updEduMemberVO.setHMBR_SN_NULL("Y");// HMBR_SN
																	// null 일때
								updEduMemberVO.setCRS_SN(eduMemberVO.getCRS_SN());
								updEduMemberVO.setHMBR_SN(HMBR_SN);
								if (eduMemberVO.getREG_TYPE_CD() == null
										|| eduMemberVO.getREG_TYPE_CD().length() == 0) {
									updEduMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);
								}
								eduMemberService.set_edu_member_target_mod(updEduMemberVO);
							} else {
								log_dscrp.append(",|" + eduMemberVO.getSearchYear() + "년도 연도별수강내역에신규추가");

								updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
								updEduMemberVO.setMBR_CD(chkEduMemberVO.getMBR_CD());
								updEduMemberVO.setTRGT_YEAR(eduMemberVO.getSearchYear());
								updEduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
								updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
								updEduMemberVO.setCRS_SN(eduMemberVO.getCRS_SN());
								updEduMemberVO.setHMBR_SN(HMBR_SN);
								if (eduMemberVO.getREG_TYPE_CD() == null
										|| eduMemberVO.getREG_TYPE_CD().length() == 0) {
									updEduMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);
								}
								eduMemberService.set_edu_member_target_reg(updEduMemberVO);
							}
						}
					}

					// 온라인수강신청자 확인처리
					if (eduMemberVO.getRMNDR_SN() != null && eduMemberVO.getRMNDR_SN().length() != 0) {
						EduMbrRemindersVO updEduMbrRemindersVO = new EduMbrRemindersVO();
						updEduMbrRemindersVO.setUPD_MBR_ID(MASTER_MBR_ID);
						updEduMbrRemindersVO.setRMNDR_SN(eduMemberVO.getRMNDR_SN());
						updEduMbrRemindersVO.setRMNDR_MBR_ID(eduMemberVO.getMBR_ID());
						eduCenterService.set_edu_mbr_reminders_lock_cmplt(updEduMbrRemindersVO);
						log_dscrp.append("|온라인수강신청건처리완료(RMNDR_SN:" + eduMemberVO.getRMNDR_SN() + ")");
					}
					//

					// 본인명의 휴대폰 변경
					if (MHC_SN.length() > 0 && MHC_SN != null) {
						EduAdmMbrHpChngVO eduAdmMbrHpChngVO = new EduAdmMbrHpChngVO();
						eduAdmMbrHpChngVO.setMHC_UPD_ID(MASTER_MBR_ID);
						eduAdmMbrHpChngVO.setMHC_SN(MHC_SN);
						eduCenterService.set_mbr_hp_chng(eduAdmMbrHpChngVO);

						log_dscrp.append("|본인명의 휴대폰 변경 처리완료(MHC_SN: " + eduAdmMbrHpChngVO.getMHC_SN() + ")");
						tbl_inf.append("MBR_HP_CHNG_TB, ");
						tbl_sn.append(eduAdmMbrHpChngVO.getMHC_SN() + ",");
					}
					//

					tbl_inf.append("MBR_TB,");
					tbl_sn.append(eduMemberVO.getMBR_SN() + ",");

					data.put("error", "0");
					data.put("msg", "회원 정보가 수정되었습니다.");

				} catch (Exception e) {
					LOGGER.debug("[fail process] " + e.toString());
					data.put("error", "1");
					data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
					log_dscrp.append("[에러발생|아이디:" + chkEduMemberVO.getMBR_ID() + "]");
				}

			}
		}

		try {
			/**
			 * LOG RECORED (로그기록)
			 */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO, request);
		} catch (Exception e) {
			LOGGER.debug("[fail log record] " + e.toString());
		}

		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 - 회원수정 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/modify_adm_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_modify_admin_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		log_dscrp.append("[교육센터관리자-회원관리-관리자회원정보수정]");

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();

		JSONObject data = new JSONObject();
		// 검증
		EduMemberVO chkEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);

		// 김현태 추가 취약점 조치
		if (loginVO.getMBR_LV_ID().equals("4") || loginVO.getMBR_LV_ID().equals("3")
				|| loginVO.getMBR_LV_ID().equals("2")) {
			// if (!loginVO.getMBR_LV_ID().equals("1")) {
			// System.out.println("김현태테스트1");
			String eduMemberPositionCd = eduMemberVO.getMBR_POSITION_CD();
			if (eduMemberPositionCd != null && eduMemberPositionCd.equals("PCD0007") || // 통합운영자
					eduMemberPositionCd != null && eduMemberPositionCd.equals("PCD0005") || // 공단운영자
					eduMemberPositionCd != null && eduMemberPositionCd.equals("PCD0006")) { // 공단운영자

				data.put("error", "1");
				data.put("msg", "잘못된 접근입니다.");
				// System.out.println("김현태테스트2");
				return null;

			}
		}
		// System.out.println("김현태테스2"+MASTER_MBR_LV_ID);
		// System.out.println("김현태테스트3");
		if (!MASTER_MBR_LV_ID.equals("1")) {
			System.out.println("김현태테스트3");
			return null;
		}

		// System.out.println("김현태테스트4");
		// 취약점 end

		if (chkEduMemberVO.getMBR_ID() == null || chkEduMemberVO.getMBR_ID().length() == 0) {
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");

			log_dscrp.append("[존재하지 않는 관리자회원정보를 요청함]");
		} else {
			try {
				// 직급 코드 조회
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00003");
				List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);

				log_dscrp.append("[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")]");

				if (MASTER_MBR_LV_ID.equals("1")) {
					boolean isUpdateAutuStatus = false;
					if (chkEduMemberVO.getMBR_POSITION_CD().equals(eduMemberVO.getMBR_POSITION_CD())) {
						// 변경사항 없음
						log_dscrp.append("|권한등급유지함");
					} else {
						// 변경사항 발생
						isUpdateAutuStatus = true;
						log_dscrp.append("|권한등급변경");
						if (chkEduMemberVO.getMBR_POSITION_CD().equals("PCD0007")) {
							log_dscrp.append("|(기존)2등급 그룹|통합운영자 권한 제거");
						} else if (chkEduMemberVO.getMBR_POSITION_CD().equals("PCD0005")) {
							log_dscrp.append("|(기존)2등급 그룹|낚시누리-공단운영자 권한 제거");
						} else if (chkEduMemberVO.getMBR_POSITION_CD().equals("PCD0006")) {
							log_dscrp.append("|(기존)2등급 그룹|낚시전문교육-공단운영자 권한 제거");
						} else if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0002") // 해경
								|| eduMemberVO.getMBR_POSITION_CD().equals("PCD0003") // 지자체
								|| eduMemberVO.getMBR_POSITION_CD().equals("PCD0004") // 교육기관
						) {
							String CD_NM = "";
							for (int j = 0; j < list_position_cd.size(); j++) {
								CodeSetVO codeSetVO = (CodeSetVO) list_position_cd.get(j);
								if (codeSetVO.getCD_ID().equals(eduMemberVO.getMBR_POSITION_CD())) {
									CD_NM = codeSetVO.getCD_NM();
									break;
								}
							}
							log_dscrp.append("|2등급 그룹 등록|낚시전문교육-" + CD_NM + " 권한 제거");
						} else {
							log_dscrp.append("|부여 된 그룹 등급 및 권한이 없는 상태");
						}
					}

					if (isUpdateAutuStatus) {
						eduMemberVO.setMBR_GRP_1_ST("N");
						eduMemberVO.setMBR_GRP_2_ST("N");
						eduMemberVO.setMBR_GRP_3_ST("N");
						eduMemberVO.setMBR_GRP_4_ST("N");
						eduMemberVO.setMBR_LV_ID("10");
						if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0007")) { // 통합관리자
							eduMemberVO.setMBR_GRP_1_ST("Y");
							eduMemberVO.setMBR_GRP_2_ST("Y");
							eduMemberVO.setMBR_GRP_3_ST("Y");
							eduMemberVO.setMBR_GRP_4_ST("Y");
							eduMemberVO.setMBR_LV_ID("2");

							if (!MASTER_MBR_LV_ID.equals("1")) {
								loginVO.setMBR_LV_ID("2");

								request.getSession().setAttribute("LoginVO", loginVO);
							}
							// System.out.println("김현태 테스트1");
							log_dscrp.append("|(변경)2등급 그룹 등록|통합운영자 권한 부여");
						} else if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0005")) { // 낚시누리
							eduMemberVO.setMBR_GRP_1_ST("Y");
							eduMemberVO.setMBR_LV_ID("3");

							System.out.println("김현태 테스트2");

							if (!MASTER_MBR_LV_ID.equals("1")) {
								loginVO.setMBR_LV_ID("3");
								request.getSession().setAttribute("LoginVO", loginVO);
							}

							log_dscrp.append("|(변경)2등급 그룹 등록|낚시누리-공단운영자 권한 부여");
						} else if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0006")) { // 낚시전문교육
							eduMemberVO.setMBR_GRP_2_ST("Y");
							eduMemberVO.setMBR_LV_ID("3");

							if (!MASTER_MBR_LV_ID.equals("1")) {
								loginVO.setMBR_LV_ID("3");
								request.getSession().setAttribute("LoginVO", loginVO);
							}
							log_dscrp.append("|(변경)2등급 그룹 등록|낚시전문교육-공단운영자 권한 부여");
						} else if (eduMemberVO.getMBR_POSITION_CD().equals("PCD0002") // 해경
								|| eduMemberVO.getMBR_POSITION_CD().equals("PCD0003") // 지자체
								|| eduMemberVO.getMBR_POSITION_CD().equals("PCD0004") // 교육기관
						) {
							eduMemberVO.setMBR_GRP_2_ST("Y");
							eduMemberVO.setMBR_LV_ID("4");
							// System.out.println("김현태 테스트3");

							if (!MASTER_MBR_LV_ID.equals("1")) {
								loginVO.setMBR_LV_ID("4");

								request.getSession().setAttribute("LoginVO", loginVO);
								request.getSession().setAttribute("EduMemberVO", eduMemberVO);
							}

							String CD_NM = "";
							for (int j = 0; j < list_position_cd.size(); j++) {
								CodeSetVO codeSetVO = (CodeSetVO) list_position_cd.get(j);
								if (codeSetVO.getCD_ID().equals(eduMemberVO.getMBR_POSITION_CD())) {
									CD_NM = codeSetVO.getCD_NM();
									break;
								}
							}
							log_dscrp.append("|(변경)2등급 그룹 등록|낚시전문교육-" + CD_NM + " 권한 부여");
						} else {
							log_dscrp.append("|그룹 등급 및 권한 부여 불가 조건");
						}

					}

					// ******************************************//
					// 사용여부, 권한 변경 수정이 있을 때 관리자 권한 기록하기 //
					// ******************************************//
					if (eduMemberVO.getMBR_PWD_CLEAR() != null && eduMemberVO.getMBR_PWD_CLEAR().length() != 0) {
						// 비밀번호 초기화일 경우 기록 안함
					} else {
						// 관리자권한기록
						LogAdmAuthVO logAdmAuthVO = new LogAdmAuthVO();

						// 권한명, 권한내용
						String AUTHOR_NM = "";
						String AUTHOR_CN = "";
						switch (eduMemberVO.getMBR_POSITION_CD()) {
						case "PCD0002":
							AUTHOR_NM = "해경";
							AUTHOR_CN = "전체 교육대상자 정보 조회";
							break;
						case "PCD0003":
							if (eduMemberVO.getMBR_SIGNGU_CD() != null
									&& eduMemberVO.getMBR_SIGNGU_CD().length() != 0) {// 시군구
								if (eduMemberVO.getMBR_TRGT_CD().equals("CIDN010200")) {
									AUTHOR_NM = "지자체_시군구_낚시터 담당자";
									AUTHOR_CN = "해당 시군구 내 낚시터 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
								} else if (eduMemberVO.getMBR_TRGT_CD().equals("CIDN010300")) {
									AUTHOR_NM = "지자체_시군구_낚시어선 담당자";
									AUTHOR_CN = "해당 시군구 내 낚시어선 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
								} else {
									AUTHOR_NM = "지자체_시군구_통합 담당자";
									AUTHOR_CN = "해당 시군구 내 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
								}
							} else {// 시도
								if (eduMemberVO.getMBR_TRGT_CD().equals("CIDN010200")) {
									AUTHOR_NM = "지자체_시도_낚시터 담당자";
									AUTHOR_CN = "해당 시도 내 낚시터 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
								} else if (eduMemberVO.getMBR_TRGT_CD().equals("CIDN010300")) {
									AUTHOR_NM = "지자체_시도_낚시어선 담당자";
									AUTHOR_CN = "해당 시도 내 낚시어선 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
								} else {
									AUTHOR_NM = "지자체_시도_통합 담당자";
									AUTHOR_CN = "해당 시도 내 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
								}
							}
							break;
						case "PCD0004":
							AUTHOR_NM = "교육기관_통합";
							AUTHOR_CN = "해당 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
							break;
						case "PCD0005": // 낚시누리, 낚시전문교육은 현재 테이블에 정보가
										// 없음.....참고할만한게 뭐가 있을까요
							AUTHOR_NM = "낚시누리";
							AUTHOR_CN = "";
							break;
						case "PCD0006":
							AUTHOR_NM = "낚시전문교육";
							AUTHOR_CN = "";
							break;
						case "PCD0007":
							AUTHOR_NM = "통합관리자";
							AUTHOR_CN = "전체 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
							break;
						default:
							AUTHOR_NM = "";
							AUTHOR_CN = "";
							break;
						}

						// 사용여부
						String CONFM_TYPE = "";
						if (!chkEduMemberVO.getMBR_ST().equals(eduMemberVO.getMBR_ST())) {// 기존
																							// 회원정보와
																							// 변경된
																							// 데이터가
																							// 같지
																							// 않을때
							switch (eduMemberVO.getMBR_ST()) {
							case "Y":
								CONFM_TYPE = "권한 부여";
								break;
							case "N":
								CONFM_TYPE = "권한 삭제";
								break;
							case "R":
								CONFM_TYPE = "권한 변경";
								break;
							case "F":
								CONFM_TYPE = "권한 변경";
								break;
							default:
								CONFM_TYPE = "권한 변경";
								break;
							}
						} else {// 사용여부 미 수정시
							CONFM_TYPE = "권한 변경";
						}

						// 권한레벨
						String MBR_LV_ID = "";
						if (eduMemberVO.getMBR_LV_ID() != null && eduMemberVO.getMBR_LV_ID().length() != 0) {
							MBR_LV_ID = eduMemberVO.getMBR_LV_ID();
						} else {
							MBR_LV_ID = chkEduMemberVO.getMBR_LV_ID();
						}

						logAdmAuthVO.setREQST_NM(chkEduMemberVO.getMBR_NM());// 신청자
						// logAdmAuthVO.setREQST_CN();//신청내용
						logAdmAuthVO.setCONFM_NM(loginVO.getMBR_NM());// 승인자
						logAdmAuthVO.setCONFM_CN(eduMemberVO.getLOG_UPD_MSG());// 승인내용
						logAdmAuthVO.setCONFM_TYPE(CONFM_TYPE);// 승인구분
						logAdmAuthVO.setMBR_MSG(eduMemberVO.getMBR_DSCRP());// 사용자수기입력
						logAdmAuthVO.setMBR_LV(MBR_LV_ID);// 권한레벨
						logAdmAuthVO.setAUTHOR_NM(AUTHOR_NM);// 권한명
						logAdmAuthVO.setAUTHOR_CN(AUTHOR_CN);// 권한내용

						eduMemberService.set_edu_member_author_log(logAdmAuthVO);
					}
					/***********************************************/

				} else {
					log_dscrp.append("|마스터권한이아니면권한부여를할수없음");
				}
				log_dscrp.append("]");

				eduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
				eduMemberService.set_edu_member_master_auth_mod(eduMemberVO);
				eduMemberService.set_edu_member_mod(eduMemberVO);

				log_dscrp.append("|관리자회원정보수정완료");

				// 사용자사유로그기록
				{
					EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
					logRecordService.set_log_mbr_mod_data("MBR_TB", "수정", "[관리자회원]" + eduMemberVO.getLOG_UPD_MSG(),
							realEduMemberVO.getMBR_ID(), chkEduMemberVO.getMBR_NM(), chkEduMemberVO, realEduMemberVO,
							loginVO, request);
				}
				tbl_inf.append("MBR_TB,");
				tbl_sn.append(eduMemberVO.getMBR_SN() + ",");

				data.put("error", "0");
				data.put("msg", "관리자 회원 정보가 수정되었습니다.");

			} catch (Exception e) {
				LOGGER.debug("[fail process] " + e.toString());
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
				log_dscrp.append("[에러발생|아이디:" + chkEduMemberVO.getMBR_ID() + "]");
			}
		}

		try {
			/**
			 * LOG RECORED (로그기록)
			 */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO, request);
		} catch (Exception e) {
			LOGGER.debug("[fail log record] " + e.toString());
		}

		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원관리 - 회원목록(수강내역) 수정 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/modifyDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_modifyDtl_act(
			@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO, HttpServletRequest request,
			HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		// 검증
		EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
		if (chkEduMyHistoryVO.getHMBR_SN() == null || chkEduMyHistoryVO.getHMBR_SN().length() == 0) {
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");
		} else {
			try {
				boolean isCmpltCntUpdate = true;
				boolean isException = false;
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder log_msg = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();
				log_dscrp.append("[교육센터관리자-회원관리-수강내역수정]");

				// 회원정보
				EduMemberVO memberInfo = new EduMemberVO();
				memberInfo.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
				memberInfo = eduMemberService.get_edu_member_info(memberInfo);
				if (memberInfo == null || memberInfo.getMBR_NM() == null || memberInfo.getMBR_NM().length() == 0) {
					log_dscrp.append("[존재하지않는회원(교육상세번호:" + chkEduMyHistoryVO.getHMBR_DTL_SN() + ")]");
				} else {
					log_dscrp.append("[이름:" + memberInfo.getMBR_NM() + "(아이디:" + memberInfo.getMBR_ID() + ",교육상세번호:"
							+ chkEduMyHistoryVO.getHMBR_SN() + ")]");
				}

				if (eduMyHistoryVO.getUSE_ST_CHK().equals("Y")) {// 사용함
					eduMyHistoryVO.setUSE_ST("1");
					eduMyHistoryVO.setDEL_ST("0");
					String DEL_ST = chkEduMyHistoryVO.getDEL_ST();
					if (DEL_ST.equals("1")) {// 삭제 -> 사용함 변경시 하위데이터도 같이 변경
						EduMyHistoryVO cloneEduMyHistoryVO = new EduMyHistoryVO();
						cloneEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
						cloneEduMyHistoryVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
						cloneEduMyHistoryVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						cloneEduMyHistoryVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
						cloneEduMyHistoryVO.setUSE_ST("1");
						cloneEduMyHistoryVO.setDEL_ST("0");
						eduMyHistoryService.set_edu_mbrhstry_mod_dtl(cloneEduMyHistoryVO);

						EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
						eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
						eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(eduCurriculumVO);

						log_dscrp.append("[나의교육과정:삭제 -> 사용함 변경시 하위데이터도 같이 변경]");
					}
				} else {// 사용안함
					eduMyHistoryVO.setUSE_ST("0");
				}

				// 수강신청 상태값
				if (chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals(eduMyHistoryVO.getLRNNG_CMPLT_ST())) {
					// 변경사항없음.
					LOGGER.debug("수강신청 상태 - 변경사항없음");
					if (eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { // 이수완료
						// 이수증 발급 진행
						EduCertificateVO eduCertificateVO = new EduCertificateVO();
						eduCertificateVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
						eduCertificateVO.setHMBR_SN(eduMyHistoryVO.getHMBR_SN());
						eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
						if (existInfo == null || existInfo.getCRTF_SN() == null
								|| existInfo.getCRTF_SN().length() == 0) {
							// 신규발급
							// 회원정보
							EduMemberVO eduMemberVO = new EduMemberVO();
							eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
							// 교육과정정보
							EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
							eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
							// 점수 재계산
							int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());// 교과목이수종합시간
							int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());// 교과목이수종합점수
							int HMBR_INPUT_TIME = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_TIME());// 최대인정교육시간
							int HMBR_INPUT_POINT = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_POINT());// 최대인정교육점수
							int HMBR_RCGNT_TIME = HMBR_INPUT_TIME + LRNNG_TOT_TIME;// 인정된
																					// 교육최종시간
							int HMBR_RCGNT_POINT = HMBR_INPUT_POINT + LRNNG_TOT_POINT;// 인정된
																						// 교육최종점수
							chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
							chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
							// 발급기관정보
							EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
							eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
							eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
							// 새로 발급할 이수증
							eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());
							eduCertificateVO.setREG_MBR_ID(loginVO.getMBR_ID());
							eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							String insertId = eduCertificateVO.getCRTF_CD();
							// 회원부가상세정보
							EduMemberVO eduMemberDtlVO = new EduMemberVO();
							eduMemberDtlVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							eduMemberDtlVO.setUSE_AT("Y");
							eduMemberDtlVO.setHIDE_AT("N");
							List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
							try {
								eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(request,
										propertiesService, eduMemberVO, list_mbr_dtl, eduCertificateVO,
										chkEduMyHistoryVO, eduCurriculumVO, eduCategoryInsInfVO).toDocument());
								eduCertificateService.set_edu_certificate_reg(eduCertificateVO);
							} catch (Exception e) {
								log_msg.append("이수증발급실패(" + e.toString() + ")");
								log_dscrp.append("[이수증발급실패!!]");
								isException = true;
							}
							LOGGER.debug("새로운 이수증 발급 : " + insertId);
							log_dscrp.append("[새로운 이수증 발급 : " + insertId + "]");
						} else {// 기존회원업데이트
							// 회원정보
							EduMemberVO eduMemberVO = new EduMemberVO();
							eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
							// 교육과정정보
							EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
							eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
							// 점수 재계산
							int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());// 교과목이수종합시간
							int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());// 교과목이수종합점수
							int HMBR_INPUT_TIME = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_TIME());// 최대인정교육시간
							int HMBR_INPUT_POINT = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_POINT());// 최대인정교육점수
							int HMBR_RCGNT_TIME = HMBR_INPUT_TIME + LRNNG_TOT_TIME;// 인정된
																					// 교육최종시간
							int HMBR_RCGNT_POINT = HMBR_INPUT_POINT + LRNNG_TOT_POINT;// 인정된
																						// 교육최종점수
							chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
							chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
							// 발급기관정보
							EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
							eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
							eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
							// 회원부가상세정보
							EduMemberVO eduMemberDtlVO = new EduMemberVO();
							eduMemberDtlVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							eduMemberDtlVO.setUSE_AT("Y");
							eduMemberDtlVO.setHIDE_AT("N");
							List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
							try {
								// 이수증 업데이트
								EduCertificateVO updateItem = new EduCertificateVO();
								updateItem.setCRTF_SN(existInfo.getCRTF_SN());
								updateItem.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
								updateItem.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(request, propertiesService,
										eduMemberVO, list_mbr_dtl, existInfo, chkEduMyHistoryVO, eduCurriculumVO,
										eduCategoryInsInfVO).toDocument());
								eduCertificateService.set_edu_certificate_mod(updateItem);
							} catch (Exception e) {
								log_msg.append("이수증업데이트실패(" + e.toString() + ")");
								log_dscrp.append("[이수증업데이트실패!!]");
								isException = true;
							}
							LOGGER.debug("기존 이수증 업데이트 : " + existInfo.getCRTF_CD());
							log_dscrp.append("[기존 이수증 업데이트 : " + existInfo.getCRTF_CD() + "]");
						}
						isCmpltCntUpdate = false;
					}
				} else {
					// 변경사항있음
					LOGGER.debug("수강신청 완료 - 변경사항있음");
					// 이수증 발급 진행
					EduCertificateVO eduCertificateVO = new EduCertificateVO();
					eduCertificateVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
					eduCertificateVO.setHMBR_SN(eduMyHistoryVO.getHMBR_SN());
					eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
					EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
					if (existInfo == null || existInfo.getCRTF_SN() == null || existInfo.getCRTF_SN().length() == 0) {
						if (eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { // 기타
																				// ->
																				// 이수완료
							// 점수 재계산
							int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());// 교과목이수종합시간
							int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());// 교과목이수종합점수
							int HMBR_INPUT_TIME = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_TIME());// 최대인정교육시간
							int HMBR_INPUT_POINT = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_POINT());// 최대인정교육점수
							int HMBR_RCGNT_TIME = HMBR_INPUT_TIME + LRNNG_TOT_TIME;// 인정된
																					// 교육최종시간
							int HMBR_RCGNT_POINT = HMBR_INPUT_POINT + LRNNG_TOT_POINT;// 인정된
																						// 교육최종점수
							chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
							chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
							// 회원정보
							EduMemberVO eduMemberVO = new EduMemberVO();
							eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
							// 교육과정정보
							EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
							eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
							// 발급기관정보
							EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
							eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
							eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
							// 새로 발급할 이수증
							eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());
							eduCertificateVO.setREG_MBR_ID(loginVO.getMBR_ID());
							eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							String insertId = "";
							// 회원부가상세정보
							EduMemberVO eduMemberDtlVO = new EduMemberVO();
							eduMemberDtlVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							eduMemberDtlVO.setUSE_AT("Y");
							eduMemberDtlVO.setHIDE_AT("N");
							List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
							try {
								eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(request,
										propertiesService, eduMemberVO, list_mbr_dtl, eduCertificateVO,
										chkEduMyHistoryVO, eduCurriculumVO, eduCategoryInsInfVO).toDocument());
								insertId = eduCertificateService.set_edu_certificate_reg(eduCertificateVO);
							} catch (Exception e) {
								log_msg.append("이수증발급실패(" + e.toString() + ")");
								log_dscrp.append("[이수증발급실패!!]");
								isException = true;
								isCmpltCntUpdate = false;
							}
							LOGGER.debug("새로운 이수증 발급 : " + insertId);
							log_dscrp.append("[새로운 이수증 발급 : " + insertId + "]");
						} else if (eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("2")) { // 기타
																						// ->
																						// 이수취소
							eduCertificateService.remove_edu_certificate(existInfo);
							eduCertificateService.remove_edu_certificate_dtl(existInfo);
							LOGGER.debug("기존 이수증 삭제(하위포함) : " + existInfo.getCRTF_CD());
							log_dscrp.append("[기존 이수증 삭제(하위포함) : " + existInfo.getCRTF_CD() + "]");
						}
					} else {
						// 이미 발급된 이수증
						if (eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { // 기타
																				// ->
																				// 이수완료
							// 이수증 발급 재시작
							eduCertificateService.set_edu_certificate_mod_use_unlock(existInfo);
							LOGGER.debug("이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
							log_dscrp.append("[이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN() + "]");
						} else { // 이수완료 -> 기타
							// 이수증 발급 중단
							// eduCertificateService.set_edu_certificate_mod_use_lock(existInfo);
							// LOGGER.debug("이미 발급된 이수증(발급중단) : " +
							// existInfo.getCRTF_SN());
							// log_dscrp.append("[이미 발급된 이수증(발급중단) : " +
							// existInfo.getCRTF_SN()+"]");

							// 기존 이수증 삭제 처리
							eduCertificateService.remove_edu_certificate(existInfo);
							eduCertificateService.remove_edu_certificate_dtl(existInfo);
							LOGGER.debug("기존 이수증 삭제(하위포함) : " + existInfo.getCRTF_CD());
							log_dscrp.append("[기존 이수증 삭제(하위포함) : " + existInfo.getCRTF_CD() + "]");
						}
					}
				}

				eduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				if (!isException)
					eduMyHistoryService.set_edu_mbrhstry_mod(eduMyHistoryVO);

				tbl_inf.append("EDU_MBR_HSTRY_TB,");
				tbl_sn.append(eduMyHistoryVO.getHMBR_SN() + ",");

				// 이수상태 변경
				if (chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals(eduMyHistoryVO.getLRNNG_CMPLT_ST())) {
					// 변경사항 없음.
				} else {
					// 변동사항 있음.
					// 커리큘럼 메인 갱신
					if (isCmpltCntUpdate) {
						EduCurriculumVO updateEduCurriculumVO = new EduCurriculumVO();
						updateEduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
						if (eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { // 대기
																				// ->
																				// 완료
																				// (직접변경하는
																				// 경우
																				// 최고점
																				// 처리)
							eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_up(updateEduCurriculumVO);
							log_dscrp.append("[커리큘럼 메인 갱신:대기 -> 완료 (직접변경하는 경우 최고점 처리)]");
						} else { // 완료 -> 대기
							eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_down(updateEduCurriculumVO);
							log_dscrp.append("[커리큘럼 메인 갱신:완료 -> 대기]");
						}
					}
				}

				data.put("error", "0");
				data.put("msg", "수강내역 정보가 수정되었습니다.");

				try {
					/**
					 * LOG RECORED (로그기록)
					 */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduMyHistoryVO));
					mEduLogRecordVO.setLOG_MSG(log_msg.toString());
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

			} catch (Exception e) {
				LOGGER.debug("[fail process] " + e.toString());
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원관리 - 회원목록 회원삭제 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_member_delete(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@RequestParam(value = "GNRL_MBER", required = false) String GNRL_MBER, HttpServletRequest request,
			HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			// 검증
			EduMemberVO chkEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
			if (chkEduMemberVO.getMBR_ID() == null || chkEduMemberVO.getMBR_ID().length() == 0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {

				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();

				String MBR_ST = chkEduMemberVO.getMBR_ST();
				String CONFM_TYPE = ""; // 관리자 권한 기록 테이블 승인구분

				if (eduMemberVO.getDEL_NOW() != null && eduMemberVO.getDEL_NOW().equals("Y"))
					MBR_ST = "N";// 삭제상태여부와관계없이바로삭제진행

				// 메인
				// MBR_ST.setUPD_MBR_ID(loginVO.getMBR_ID());//유저 필드에는 변경자 정보
				// 기록이 없음...
				if (MBR_ST.equals("N")) {

					// 사용자사유로그기록
					if (GNRL_MBER != null && GNRL_MBER.equals("Y")) {// GNRL_MBER
																		// 일반회원과
																		// 관리자
																		// 구분하기
																		// 위한 변수
																		// list_edu_target.jsp에서만
																		// 씀
						log_dscrp.append("[교육센터관리자-회원관리-회원삭제-실제데이터삭제]");
						log_dscrp.append(
								"[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")]");
						logRecordService.set_log_mbr_mod_data("MBR_TB", "실제데이터삭제", eduMemberVO.getLOG_UPD_MSG(),
								chkEduMemberVO.getMBR_ID(), chkEduMemberVO.getMBR_NM(), chkEduMemberVO, null, loginVO,
								request);
					} else {
						CONFM_TYPE = "실제 데이터 삭제";
						log_dscrp.append("[교육센터관리자-관리자계정관리-관리자회원삭제-실제데이터삭제]");
						log_dscrp.append(
								"[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")]");
						logRecordService.set_log_mbr_mod_data("MBR_TB", "[관리자회원]실제데이터삭제", eduMemberVO.getLOG_UPD_MSG(),
								chkEduMemberVO.getMBR_ID(), chkEduMemberVO.getMBR_NM(), chkEduMemberVO, null, loginVO,
								request);
					}

					List<EduMemberVO> list_dtl = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
					for (EduMemberVO item_dtl : list_dtl) {
						// 사용자사유로그기록
						if (GNRL_MBER != null && GNRL_MBER.equals("Y")) {
							logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "실제데이터삭제", eduMemberVO.getLOG_UPD_MSG(),
									item_dtl.getMBR_ID(), chkEduMemberVO.getMBR_NM(), item_dtl, null, loginVO, request);
						} else {
							logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "[관리자회원]실제데이터삭제",
									eduMemberVO.getLOG_UPD_MSG(), item_dtl.getMBR_ID(), chkEduMemberVO.getMBR_NM(),
									item_dtl, null, loginVO, request);
						}
					}
					eduMemberService.remove_edu_member(chkEduMemberVO);
					eduMemberService.remove_edu_member_dtl(chkEduMemberVO);
					eduMemberService.remove_edu_member_target(chkEduMemberVO);

					// 수강내역은 어떻게 할것인가...

				} else {
					// 사용자사유로그기록
					if (GNRL_MBER != null && GNRL_MBER.equals("Y")) {
						log_dscrp.append("[교육센터관리자-회원관리-회원삭제]");
						log_dscrp.append(
								"[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")]");
						logRecordService.set_log_mbr_mod_data("MBR_TB", "삭제(1단계)", eduMemberVO.getLOG_UPD_MSG(),
								chkEduMemberVO.getMBR_ID(), chkEduMemberVO.getMBR_NM(), chkEduMemberVO, null, loginVO,
								request);
					} else {
						CONFM_TYPE = "권한 삭제";
						log_dscrp.append("[교육센터관리자-관리자계정관리-관리자회원삭제]");
						log_dscrp.append(
								"[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")]");
						logRecordService.set_log_mbr_mod_data("MBR_TB", "[관리자회원]삭제(1단계)", eduMemberVO.getLOG_UPD_MSG(),
								chkEduMemberVO.getMBR_ID(), chkEduMemberVO.getMBR_NM(), chkEduMemberVO, null, loginVO,
								request);
					}
					eduMemberService.del_edu_member(eduMemberVO);

					/*
					 * 회원의 모든 수강내역도 같이 업데이트 반영이 되어야 겠지??
					 * 
					 * EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
					 * eduCurriculumVO.setMBR_ID(eduMemberVO.getMBR_ID());
					 * eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(
					 * eduCurriculumVO);
					 */

				}

				// *********************************************
				// *관리자 권한 기록
				// *********************************************
				if (GNRL_MBER != null && GNRL_MBER.equals("Y")) {// 일반회원 삭제

				} else {// 관리자 삭제

					LogAdmAuthVO logAdmAuthVO = new LogAdmAuthVO();

					// 권한명, 권한내용
					String AUTHOR_NM = "";
					String AUTHOR_CN = "";
					switch (chkEduMemberVO.getMBR_POSITION_CD()) {
					case "PCD0002":
						AUTHOR_NM = "해경";
						AUTHOR_CN = "전체 교육대상자 정보 조회";
						break;
					case "PCD0003":
						if (chkEduMemberVO.getMBR_SIGNGU_CD() != null
								&& chkEduMemberVO.getMBR_SIGNGU_CD().length() != 0) {// 시군구
							if (chkEduMemberVO.getMBR_TRGT_CD().equals("CIDN010200")) {
								AUTHOR_NM = "지자체_시군구_낚시터 담당자";
								AUTHOR_CN = "해당 시군구 내 낚시터 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
							} else if (chkEduMemberVO.getMBR_TRGT_CD().equals("CIDN010300")) {
								AUTHOR_NM = "지자체_시군구_낚시어선 담당자";
								AUTHOR_CN = "해당 시군구 내 낚시어선 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
							} else {
								AUTHOR_NM = "지자체_시군구_통합 담당자";
								AUTHOR_CN = "해당 시군구 내 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
							}
						} else {// 시도
							if (chkEduMemberVO.getMBR_TRGT_CD().equals("CIDN010200")) {
								AUTHOR_NM = "지자체_시도_낚시터 담당자";
								AUTHOR_CN = "해당 시도 내 낚시터 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
							} else if (chkEduMemberVO.getMBR_TRGT_CD().equals("CIDN010300")) {
								AUTHOR_NM = "지자체_시도_낚시어선 담당자";
								AUTHOR_CN = "해당 시도 내 낚시어선 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
							} else {
								AUTHOR_NM = "지자체_시도_통합 담당자";
								AUTHOR_CN = "해당 시도 내 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
							}
						}
						break;
					case "PCD0004":
						AUTHOR_NM = "교육기관_통합";
						AUTHOR_CN = "해당 통합 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
						break;
					case "PCD0005": // 낚시누리, 낚시전문교육은 현재 테이블에 정보가 없음.....참고할만한게
									// 뭐가 있을까요
						AUTHOR_NM = "낚시누리";
						AUTHOR_CN = "";
						break;
					case "PCD0006":
						AUTHOR_NM = "낚시전문교육";
						AUTHOR_CN = "";
						break;
					case "PCD0007":
						AUTHOR_NM = "통합관리자";
						AUTHOR_CN = "전체 교육대상자 정보 입력, 수정, 삭제, 변경, 출력 등";
						break;
					default:
						AUTHOR_NM = "";
						AUTHOR_CN = "";
						break;
					}

					logAdmAuthVO.setREQST_NM(chkEduMemberVO.getMBR_NM());// 신청자
					// logAdmAuthVO.setREQST_CN();//신청내용
					logAdmAuthVO.setCONFM_NM(loginVO.getMBR_NM());// 승인자
					logAdmAuthVO.setCONFM_CN(eduMemberVO.getLOG_UPD_MSG());// 승인내용
					logAdmAuthVO.setCONFM_TYPE(CONFM_TYPE);// 승인구분
					logAdmAuthVO.setMBR_MSG(eduMemberVO.getMBR_DSCRP());// 사용자수기입력
					logAdmAuthVO.setMBR_LV(chkEduMemberVO.getMBR_LV_ID());// 권한레벨
					logAdmAuthVO.setAUTHOR_NM(AUTHOR_NM);// 권한명
					logAdmAuthVO.setAUTHOR_CN(AUTHOR_CN);// 권한내용
					logAdmAuthVO.setMBR_USG_DT(chkEduMemberVO.getMBR_USG_DT());// 관리자
																				// 계정
																				// 사용기간

					eduMemberService.set_edu_member_author_log(logAdmAuthVO);
				}

				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");

				tbl_inf.append("MBR_TB,");
				tbl_sn.append(eduMemberVO.getMBR_SN() + ",");

				try {
					/**
					 * LOG RECORED (로그기록)
					 */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(chkEduMemberVO));
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

			}
		} catch (Exception e) {
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원관리 - 회원목록(수강내역) 삭제 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/deleteDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_member_deleteDtl(
			@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO, HttpServletRequest request,
			HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			// 검증
			EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
			if (chkEduMyHistoryVO.getHMBR_SN() == null || chkEduMyHistoryVO.getHMBR_SN().length() == 0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {

				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();

				String DEL_ST = chkEduMyHistoryVO.getDEL_ST();
				if (DEL_ST.equals("1")) {
					log_dscrp.append("[교육센터관리자-회원관리-수강내역-삭제-실제데이터삭제]");
				} else {
					log_dscrp.append("[교육센터관리자-회원관리-수강내역-삭제]");
				}

				// 회원정보
				EduMemberVO memberInfo = new EduMemberVO();
				memberInfo.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
				memberInfo = eduMemberService.get_edu_member_info(memberInfo);
				if (memberInfo == null || memberInfo.getMBR_NM() == null || memberInfo.getMBR_NM().length() == 0) {
					log_dscrp.append("[존재하지않는회원(교육상세번호:" + chkEduMyHistoryVO.getHMBR_DTL_SN() + ")]");
				} else {
					log_dscrp.append("[이름:" + memberInfo.getMBR_NM() + "(아이디:" + memberInfo.getMBR_ID() + ",교육상세번호:"
							+ chkEduMyHistoryVO.getHMBR_SN() + ")]");
				}

				// 메인
				eduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				if (DEL_ST.equals("1")) {
					eduMyHistoryService.remove_edu_mbrhstry(eduMyHistoryVO);
				} else {
					eduMyHistoryService.del_edu_mbrhstry(eduMyHistoryVO);

					EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
					eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
					eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(eduCurriculumVO);

					if (chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) {
						eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_down(eduCurriculumVO);
					}

				}

				tbl_inf.append("EDU_MBR_HSTRY_TB,");
				tbl_sn.append(eduMyHistoryVO.getHMBR_SN() + ",");

				// 상세
				eduMyHistoryVO.setNotUsedPagination(true);
				List<EduMyHistoryVO> clildList = eduMyHistoryService.get_edu_mbrhstry_dtl_list(eduMyHistoryVO);
				for (EduMyHistoryVO item : clildList) {
					EduMyHistoryVO cloneEduMyHistoryVO = item;
					cloneEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					if (DEL_ST.equals("1")) {
						eduMyHistoryService.remove_edu_mbrhstry_dtl(cloneEduMyHistoryVO);
					} else {
						eduMyHistoryService.del_edu_mbrhstry_dtl(cloneEduMyHistoryVO);
					}

					tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
					tbl_sn.append(eduMyHistoryVO.getHMBR_DTL_SN() + ",");
				}

				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");

				try {
					/**
					 * LOG RECORED (로그기록)
					 */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(chkEduMyHistoryVO));
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
			}
		} catch (Exception e) {
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원관리 - 회원목록(수강내역) 교과목 상세 리스트
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/listMbrDtl.do")
	public String edu_member_listMbrDtl(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, SessionStatus status,
			HttpServletRequest request, ModelMap model) throws Exception {

		List<EduMyHistoryVO> list = null;
		// eduMyHistoryVO.setPageUnit(2);
		try {
			/** pageing setting */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(eduMyHistoryVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(eduMyHistoryVO.getPageUnit());
			paginationInfo.setPageSize(eduMyHistoryVO.getPageSize());

			eduMyHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			eduMyHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
			eduMyHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			list = eduMyHistoryService.get_edu_mbrhstry_dtl_list(eduMyHistoryVO);

			int totCnt = eduMyHistoryService.get_edu_mbrhstry_dtl_list_totcnt(eduMyHistoryVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);

		} catch (Exception e) {
			// 상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		// 교육과정 정보
		EduCurriculumVO parentInfo = null;
		try {
			eduCurriculumVO.setTypeStr("");
			parentInfo = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail load data2] " + e.toString());
		}
		if (parentInfo == null) {
			// 상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		// 수강내역(메인) 정보
		EduMyHistoryVO parentInfo2 = null;
		try {
			eduCurriculumVO.setTypeStr("");
			parentInfo2 = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail load data3] " + e.toString());
		}
		if (parentInfo == null) {
			// 상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		model.addAttribute("parentInfo", parentInfo);
		model.addAttribute("parentInfo2", parentInfo2);
		model.addAttribute("list", list);
		return "eduadm/member/list_mbr_dtl";
	}

	// 관리자(교육센터) 대상년도 교육이수현황 리스트
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/ajaxEduTarget.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_data_member_edu_target_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {
		JSONObject data = new JSONObject();
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			if (loginVO.getMBR_POSITION_CD().equals("PCD0005") || loginVO.getMBR_POSITION_CD().equals("PCD0006")
					|| loginVO.getMBR_POSITION_CD().equals("PCD0007")) {
				data.put("isShow", "0");
			} else {
				data.put("isShow", "1");
			}

			List<EduMemberVO> list = eduMemberService.get_edu_member_target_all_list(eduMemberVO);

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			data.put("rawdata", mapper.writeValueAsString(list));

			data.put("error", "0");
			data.put("msg", "조회 성공");

		} catch (Exception e) {
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원상세정보 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/ajaxEduDtl.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_data_member_edu_dtl_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {
		JSONObject data = new JSONObject();
		try {

			List<EduMemberVO> list = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			data.put("rawdata", mapper.writeValueAsString(list));

			data.put("error", "0");
			data.put("msg", "조회 성공");

		} catch (Exception e) {
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 교육대상자 추가 화면 ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/edu/add.do", method = RequestMethod.POST)
	public ModelAndView seadm_member_auth_add(@RequestParam("modal_title") String modal_title,
			@ModelAttribute("eduMemberVO") EduMemberVO mEduMemberVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/member/member_edu_add");
		mModelAndView.addObject("EduMemberVO", mEduMemberVO);
		mModelAndView.addObject("modal_title", modal_title);
		return mModelAndView;
	}

	// 관리자(교육센터) 교육대상자(추가가능회원) 목록 가져오기
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/edu/ajaxlist.do", method = RequestMethod.POST)
	public ModelAndView ajax_member_list(@ModelAttribute("eduMemberVO") EduMemberVO mEduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		String returnUrl = "eduadm/member/member_ajax_list";
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			returnUrl = "eduadm/error/page_400";
		} else {
			String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
			if (MASTER_MBR_POSITION_CD.equals("PCD0002")) { // 해양경찰서

			} else if (MASTER_MBR_POSITION_CD.equals("PCD0003")) { // 지자체
				// 지자체 관리주체건만 조회하도록...
				mEduMemberVO.setMBR_REG_TYPE_CD(MASTER_MBR_POSITION_CD);
			} else if (MASTER_MBR_POSITION_CD.equals("PCD0004")) { // 교육기관

			} else {// 제한없음 - 공단

			}
			mEduMemberVO.setPageUnit(10);// 한번에 10명만 노출
			mEduMemberVO.setMBR_LV_ID("10");
			mEduMemberVO.setUSE_AT("Y");
			mEduMemberVO.setDEL_AT("N");

			List<EduMemberVO> list = null;
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mEduMemberVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mEduMemberVO.getPageUnit());
			paginationInfo.setPageSize(mEduMemberVO.getPageSize());
			mEduMemberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mEduMemberVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mEduMemberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			list = eduMemberService.get_edu_member_target_add_list(mEduMemberVO);
			int totCnt = eduMemberService.get_edu_member_target_add_list_totcnt(mEduMemberVO);
			paginationInfo.setTotalRecordCount(totCnt);

			mModelAndView.addObject("paginationInfo", paginationInfo);
			mModelAndView.addObject("list", list);
		}
		mModelAndView.setViewName(returnUrl);
		mModelAndView.addObject("mbrids", mEduMemberVO.getMBR_ID());
		return mModelAndView;
	}

	// 관리자(교육센터) 교육대상자 등록(추가) 처리 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/edu/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_auth_write(@ModelAttribute("eduMemberVO") EduMemberVO mEduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			String searchYear = mEduMemberVO.getSearchYear();

			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder log_msg = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();
			log_dscrp.append("[교육센터관리자-교육대상자(" + searchYear + ")-기존회원추가]");

			int successcnt = 0;
			int failcnt = 0;
			String MASTER_MBR_ID = loginVO.getMBR_ID();
			String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
			String MASTER_MBR_GRP_ID = loginVO.getMBR_GRP_ID();
			String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
			String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
			String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();

			log_msg.append("[아이디:" + MASTER_MBR_ID + "]");
			log_msg.append("[처리권한:" + MASTER_MBR_GRP_ID + "]");
			log_msg.append("[처리레벨:" + MASTER_MBR_LV_ID + "]");
			log_msg.append("[처리직급:" + MASTER_MBR_POSITION_CD + "]");
			log_msg.append("[관리지역(시도):" + MASTER_MBR_SIDO_CD + "]");
			log_msg.append("[관리지역(시군구):" + MASTER_MBR_SIGNGU_CD + "]");

			String[] mbrids = mEduMemberVO.getMBR_ID().replaceAll("\\s", "").split(",");
			for (int i = 0; i < mbrids.length; i++) {
				String traget_mbr_id = mbrids[i];
				LOGGER.debug("현재 회원 처리 중 : " + traget_mbr_id);
				EduMemberVO chkEduMemberVO = new EduMemberVO();
				chkEduMemberVO.setMBR_ID(traget_mbr_id);
				chkEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);
				log_dscrp.append("[MBR_ID:" + traget_mbr_id);
				if (chkEduMemberVO == null || chkEduMemberVO.getMBR_ID() == null
						|| chkEduMemberVO.getMBR_ID().length() == 0) {
					log_dscrp.append("[존재하지않는회원(아이디:" + traget_mbr_id + ")]");
					failcnt++;
				} else {

					log_dscrp.append("[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")]");

					// 회원관리주체에 대한 업데이트 진행
					String UPD_MBR_REG_TYPE_CD = "";
					String CHK_MBR_SIDO_CD = "";
					String CHK_MBR_SIGNGU_CD = "";
					if (MASTER_MBR_POSITION_CD.equals("PCD0002")) { // 해양경찰서

					} else if (MASTER_MBR_POSITION_CD.equals("PCD0003")) { // 지자체
						UPD_MBR_REG_TYPE_CD = MASTER_MBR_POSITION_CD;
						CHK_MBR_SIDO_CD = MASTER_MBR_SIDO_CD;
						CHK_MBR_SIGNGU_CD = MASTER_MBR_SIGNGU_CD;
						log_dscrp.append("|회원관리주체변경(지자체)로이관");
					} else if (MASTER_MBR_POSITION_CD.equals("PCD0004")) { // 교육기관

					} else {// 제한없음 - 공단

					}
					//

					EduMemberVO chkTargetEduMemberVO = new EduMemberVO();
					chkTargetEduMemberVO.setMBR_ID(traget_mbr_id);
					chkTargetEduMemberVO.setTRGT_YEAR(searchYear);
					chkTargetEduMemberVO.setUSE_AT("Y");
					chkTargetEduMemberVO.setNotUsedPagination(true);
					chkTargetEduMemberVO.setMBR_REG_TYPE_CD(UPD_MBR_REG_TYPE_CD);
					chkTargetEduMemberVO.setMBR_SIDO_CD(CHK_MBR_SIDO_CD);
					chkTargetEduMemberVO.setMBR_SIGNGU_CD(CHK_MBR_SIGNGU_CD);
					List<EduMemberVO> chkTargetEduList = eduMemberService
							.get_edu_member_target_list(chkTargetEduMemberVO);
					if (chkTargetEduList == null || chkTargetEduList.size() == 0) {
						log_dscrp.append("," + chkEduMemberVO.getMBR_NM() + "|추가완료");
						EduMemberVO newEduMemberVO = new EduMemberVO();
						newEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
						newEduMemberVO.setMBR_CD(chkEduMemberVO.getMBR_CD());
						newEduMemberVO.setMBR_NM(chkEduMemberVO.getMBR_NM());
						newEduMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);
						newEduMemberVO.setTRGT_YEAR(searchYear);
						newEduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
						newEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);

						successcnt++;
						eduMemberService.set_edu_member_target_reg(newEduMemberVO);
					} else {
						log_dscrp.append("," + chkEduMemberVO.getMBR_NM() + "|이미등록되어있어추가하지않음");
					}

					if (chkEduMemberVO.getMBR_ST().equals("N") || UPD_MBR_REG_TYPE_CD.length() != 0) {
						log_dscrp.append("|강제로 회원을 활성화 상태로 업데이트");
						EduMemberVO updEduMemberVO = new EduMemberVO();
						updEduMemberVO.setMBR_ID(traget_mbr_id);
						updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
						updEduMemberVO.setMBR_ST("Y");
						updEduMemberVO.setMBR_SIDO_CD(MASTER_MBR_SIDO_CD);
						updEduMemberVO.setMBR_SIGNGU_CD(MASTER_MBR_SIGNGU_CD);
						updEduMemberVO.setMBR_REG_TYPE_CD(UPD_MBR_REG_TYPE_CD);
						eduMemberService.set_edu_member_mod(updEduMemberVO);
					}

				}
				log_dscrp.append("]");
			}
			log_msg.append("[결과-성공:" + successcnt + "건,실패:" + failcnt + "건]");

			data.put("error", "0");
			data.put("msg", "추가되었습니다.");

			tbl_inf.append("MBR_TB");
			try {
				/**
				 * LOG RECORED (로그기록)
				 */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(mEduMemberVO));
				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
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

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 교육대상자 제거 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/edu/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_member_edu_delete(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			// 검증
			EduMemberVO chkEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
			if (chkEduMemberVO.getMBR_ID() == null || chkEduMemberVO.getMBR_ID().length() == 0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {

				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder log_msg = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();

				String MASTER_MBR_ID = loginVO.getMBR_ID();
				String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
				String MASTER_MBR_GRP_ID = loginVO.getMBR_GRP_ID();
				String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
				log_msg.append("[아이디:" + MASTER_MBR_ID + "]");
				log_msg.append("[처리권한:" + MASTER_MBR_GRP_ID + "]");
				log_msg.append("[처리레벨:" + MASTER_MBR_LV_ID + "]");
				log_msg.append("[처리직급:" + MASTER_MBR_POSITION_CD + "]");

				// 취약점 대응을 위한 처리
				boolean isScrtyKeyRefuse = true;
				try {
					String memberScrtyKey = EgovFileScrty.security(eduMemberVO.getMBR_ID(), MASTER_MBR_ID);
					if (eduMemberVO.getMBR_SCRTY_KEY().equals(memberScrtyKey)) {
						isScrtyKeyRefuse = true;
					} else {
						isScrtyKeyRefuse = false;
					}
					LOGGER.debug("reqmemberScrtyKey : " + eduMemberVO.getMBR_SCRTY_KEY());
					LOGGER.debug("memberScrtyKey : " + memberScrtyKey);
				} catch (Exception e) {
					LOGGER.debug("uniqAdminScrtyKey null ");
					isScrtyKeyRefuse = false;
				}
				// End of 취약점 대응을 위한 처리

				if (!isScrtyKeyRefuse) {
					data.put("error", "1");
					data.put("msg", "비정상적인 접근으로 거부되었습니다.");
				} else {

					String searchYear = eduMemberVO.getSearchYear();

					log_dscrp.append("[교육센터관리자-교육대상자(" + searchYear + ")-제거처리]");

					EduMemberVO chkTargetEduMemberVO = new EduMemberVO();
					chkTargetEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
					chkTargetEduMemberVO.setTRGT_YEAR(searchYear);
					chkTargetEduMemberVO.setNotUsedPagination(true);
					List<EduMemberVO> chkTargetEduList = eduMemberService
							.get_edu_member_target_list(chkTargetEduMemberVO);
					if (chkTargetEduList == null || chkTargetEduList.size() == 0) {
						log_dscrp.append("[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID()
								+ ")-제거대상이없음:제거불가]");
					} else {
						log_dscrp.append(
								"[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")-제거완료]");
						eduMemberService.remove_edu_member_target(chkTargetEduMemberVO);

					}

					if (MASTER_MBR_POSITION_CD.equals("PCD0003")) { // 담당자가 지자체
																	// 인 경우 ,
																	// 공단으로 이관
						eduMemberVO.setMBR_REG_TYPE_CD("");
						eduMemberService.set_edu_member_trnsfer(eduMemberVO);
						log_dscrp.append("[회원관리주체변경(지자체=>공단)으로이관]");
					}

					data.put("error", "0");
					data.put("msg", chkEduMemberVO.getMBR_NM() + "님은 교육대상자에서 제거 되었습니다.");

					tbl_inf.append("MBR_TB,");
					tbl_sn.append(eduMemberVO.getMBR_SN() + ",");

				}

				try {
					/**
					 * LOG RECORED (로그기록)
					 */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduMemberVO));
					mEduLogRecordVO.setLOG_MSG(log_msg.toString());
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

			}
		} catch (Exception e) {
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원(교육대상자) 메모하기 처리 로직
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/memo/update_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_member_memo_update_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			// 검증
			EduMemberVO chkEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
			if (chkEduMemberVO.getMBR_ID() == null || chkEduMemberVO.getMBR_ID().length() == 0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder log_msg = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();

				String MASTER_MBR_ID = loginVO.getMBR_ID();
				String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
				String MASTER_MBR_GRP_ID = loginVO.getMBR_GRP_ID();
				String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
				log_msg.append("[아이디:" + MASTER_MBR_ID + "]");
				log_msg.append("[처리권한:" + MASTER_MBR_GRP_ID + "]");
				log_msg.append("[처리레벨:" + MASTER_MBR_LV_ID + "]");
				log_msg.append("[처리직급:" + MASTER_MBR_POSITION_CD + "]");

				log_dscrp.append("[교육센터관리자-교육대상자-메모기록-처리]");
				log_dscrp.append("[이름:" + chkEduMemberVO.getMBR_NM() + "(아이디:" + chkEduMemberVO.getMBR_ID() + ")]");
				log_dscrp.append("[기존메모:" + chkEduMemberVO.getMBR_DSCRP() + "]");
				log_dscrp.append("[신규메모:" + eduMemberVO.getMBR_DSCRP() + "]");

				EduMemberVO updEduMemberVO = new EduMemberVO();
				updEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
				updEduMemberVO.setMBR_DSCRP(eduMemberVO.getMBR_DSCRP());
				eduMemberService.set_edu_member_memo_mod(updEduMemberVO);
				// 사용자사유로그기록
				{
					EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(updEduMemberVO);
					logRecordService.set_log_mbr_mod_data("MBR_TB", "수정", "메모수정", realEduMemberVO.getMBR_ID(),
							realEduMemberVO.getMBR_NM(), chkEduMemberVO, realEduMemberVO, loginVO, request);
				}
				data.put("error", "0");
				data.put("msg", chkEduMemberVO.getMBR_NM() + "님의 메모가 기록되었습니다.");

				tbl_inf.append("MBR_TB,");
				tbl_sn.append(eduMemberVO.getMBR_SN() + ",");

				try {
					/**
					 * LOG RECORED (로그기록)
					 */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduMemberVO));
					mEduLogRecordVO.setLOG_MSG(log_msg.toString());
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

			}
		} catch (Exception e) {
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 회원관리 - 관리자 접속기록
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/access_log.do")
	public Object edu_member_acces_log(boolean isExcelDownLoad, @ModelAttribute("logRecordVO") LogRecordVO logRecordVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		// 지자체, 해경 권한일 경우 막기
		if (loginVO.getMBR_POSITION_CD().equals("PCD0003") || loginVO.getMBR_POSITION_CD().equals("PCD0002")) {
			LOGGER.debug("교육센터 관리자페이지 - 접근권한 없음!!");
			return "redirect:/eduadm/error/unauth.do";
		}

		String currentDate = mPublicUtils.currentTime("yyyy-MM-dd");
		String currentMonth = mPublicUtils.currentTime("yyyy-MM");
		if (logRecordVO.getSearchStrDate() == null || logRecordVO.getSearchStrDate().equals("")) {
			logRecordVO.setSearchStrDate(currentMonth + "-01");
		}
		if (logRecordVO.getSearchEndDate() == null || logRecordVO.getSearchEndDate().equals("")) {
			logRecordVO.setSearchEndDate(currentDate);
		}
		model.addAttribute("searchStrDate", logRecordVO.getSearchStrDate());
		model.addAttribute("searchEndDate", logRecordVO.getSearchEndDate());

		logRecordVO.setSearchStrDate(logRecordVO.getSearchStrDate() + " 00:00:00");
		logRecordVO.setSearchEndDate(logRecordVO.getSearchEndDate() + " 23:59:59");

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(logRecordVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(logRecordVO.getPageUnit());
		paginationInfo.setPageSize(logRecordVO.getPageSize());

		logRecordVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		logRecordVO.setLastIndex(paginationInfo.getLastRecordIndex());
		logRecordVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if (isExcelDownLoad) {
			logRecordVO.setNotUsedPagination(true);
		}

		logRecordVO.setADM_ACCESS_LOG("[교육센터관리자-로");
		List<LogRecordVO> list = logRecordService.get_log_list(logRecordVO);
		int totCnt = logRecordService.get_log_list_totcnt(logRecordVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list", list);

		model.addAttribute("searchKeyword", logRecordVO.getSearchKeyword());

		if (isExcelDownLoad) {
			return (ModelMap) model;
		} else {
			return "eduadm/member/access_log";
		}
	}

	@RequestMapping(value = "/eduadm/member/loc_gov_upload.do")
	public String edu_member_loc_gov_upload(@ModelAttribute("eduExcelUploadVO") EduExcelUploadVO eduExcelUploadVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();
		String MASTER_MBR_TRGT_CD = loginVO.getMBR_TRGT_CD();

		/** pageing setting **/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduExcelUploadVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduExcelUploadVO.getPageUnit());
		paginationInfo.setPageSize(eduExcelUploadVO.getPageSize());

		eduExcelUploadVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduExcelUploadVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduExcelUploadVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 지자체 관리주체건만 조회
		eduExcelUploadVO.setMBR_REG_TYPE_CD(MASTER_MBR_POSITION_CD);
		eduExcelUploadVO.setMBR_SIDO_CD(MASTER_MBR_SIDO_CD);
		eduExcelUploadVO.setMBR_SIGNGU_CD(MASTER_MBR_SIGNGU_CD);
		eduExcelUploadVO.setMBR_ID(eduExcelUploadVO.getMBR_ID());
		eduExcelUploadVO.setMBR_ST("Y");
		if (MASTER_MBR_TRGT_CD != null && MASTER_MBR_TRGT_CD.length() != 0) {
			eduExcelUploadVO.setMBR_TRGT_CD(MASTER_MBR_TRGT_CD);
		}
		List<EduExcelUploadVO> upload_list = eduMemberService.get_edu_excel_upload_list(eduExcelUploadVO);// edu_excel_upload_tb

		int totCnt = eduMemberService.get_edu_excel_upload_list_totcnt(eduExcelUploadVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("upload_list", upload_list);
		model.addAttribute("MASTER_MBR_POSITION_CD", MASTER_MBR_POSITION_CD);

		return "eduadm/member/loc_gov_upload";
	}

	@RequestMapping(value = "/eduadm/member/loc_gov_adm_upload.do")
	public String edu_member_loc_gov_adm_upload(@ModelAttribute("eduExcelUploadVO") EduExcelUploadVO eduExcelUploadVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		// String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		// String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();

		/** pageing setting **/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduExcelUploadVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduExcelUploadVO.getPageUnit());
		paginationInfo.setPageSize(eduExcelUploadVO.getPageSize());

		eduExcelUploadVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduExcelUploadVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduExcelUploadVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		eduExcelUploadVO.setCONFM_REG_DT("NOT_NULL");

		List<EduExcelUploadVO> upload_list = eduMemberService.get_edu_excel_upload_list(eduExcelUploadVO);// edu_excel_upload_tb

		int totCnt = eduMemberService.get_edu_excel_upload_list_totcnt(eduExcelUploadVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("upload_list", upload_list);
		model.addAttribute("MASTER_MBR_POSITION_CD", MASTER_MBR_POSITION_CD);

		return "eduadm/member/loc_gov_adm_upload";
	}

	@RequestMapping(value = "/eduadm/member/loc_gov_upload_act.do")
	public String edu_member_loc_gov_upload_act(@ModelAttribute("eduExcelUploadVO") EduExcelUploadVO eduExcelUploadVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, MultipartHttpServletRequest multiRequest,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		// ModelAndView mModelAndView = new ModelAndView();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();

		response.setCharacterEncoding("UTF-8");
		try {

			// 엑셀 파일 업로드한 정보 가져오기
			MultipartFile file = null;
			Iterator<String> iterator = multiRequest.getFileNames();
			if (iterator.hasNext()) {
				file = multiRequest.getFile(iterator.next());
			}
			String _atchFileId = "";
			Map fresult = null;
			try {
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				fresult = new PublicFileMngUtil(fileMngService, fileUtil).chkFileUpdate(files, // 업로드
																								// 파일
																								// 목록
						_atchFileId, // 첨부파일아이디(ATCH_FILE_ID)
						"", // 첨부파일아이디 번호(FILE_SN)
						"EXCEL_", // 저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[] { "jpg", "jpge", "png", "gif", "bmp", // image
								"mp4", "avi", "wmv", // video
								"hwp", "doc", "xls", "xlsx", "csv", "txt", "pdf", "pptx", // document
								"mp3", "zip", // etc
						}, // 허용할 업로드 파일 확장자
						0, // 허용할 업로드 파일 사이즈
						"" // 파일저장위치(기본값:Globals.fileStorePath)
				);
				if (fresult.get("error").equals("1")) {
					LOGGER.debug("정상적인 거부");
				} else if (fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					model.addAttribute("page_back_cnt", "-3");
					return "/eduadm/error/page_back";
				} else { // 정상적으로 처리됨.
					_atchFileId = fresult.get("atchFileId").toString();
				}
			} catch (Exception e) {
				e.fillInStackTrace();
			}

			String eu_sn = _atchFileId;

			eduExcelUploadVO.setEU_SN(eu_sn);
			eduExcelUploadVO.setORIGNL_FILE_NM(file.getOriginalFilename());
			eduExcelUploadVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduMemberService.set_edu_excel_upload_reg(eduExcelUploadVO);// edu_excel_upload_tb

			try {
				excelUploadFile(file, eu_sn);
				data.put("error", "0");
				data.put("msg", "첨부하신 엑셀파일이 업로드 되었습니다.");
			} catch (Exception e) {
				data.put("error", "1");
				data.put("msg", "엑셀 양식이 올바르지 않거나<br>유효하지 않은 값이 포함되어<br>엑셀 업로드가 실패하였습니다.");

				EduExcelUploadVO delExcelUploadVO = new EduExcelUploadVO();
				delExcelUploadVO.setEU_SN(eu_sn);
				eduMemberService.set_edu_excel_upload_del(delExcelUploadVO);
				eduMemberService.set_edu_excel_upload_dtl_del(delExcelUploadVO);

			}

			// List<EduExcelUploadVO> list = excelUploadFile(file, eu_sn);c

			// mModelAndView.addObject("list", list);
			// mModelAndView.addObject("list_size", list.size());
			// mModelAndView.setViewName("/eduadm/member/loc_gov_ajax");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}

		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	@RequestMapping(value = "/eduadm/member/loc_gov_view.do")
	public ModelAndView eduadm_member_loc_gov_view(
			@ModelAttribute("eduExcelUploadVO") EduExcelUploadVO eduExcelUploadVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mModelAndView = new ModelAndView();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();
		String MASTER_MBR_TRGT_CD = loginVO.getMBR_TRGT_CD();

		LOGGER.debug("[지자체엑셀현행화:요청자] MBR_NM : " + loginVO.getMBR_NM() + " , MBR_POSITION_CD : " + MASTER_MBR_POSITION_CD
				+ " , MBR_SIDO_CD : " + MASTER_MBR_SIDO_CD + " , MBR_SIGNGU_CD : " + MASTER_MBR_SIGNGU_CD
				+ " , MASTER_MBR_TRGT_CD : " + MASTER_MBR_TRGT_CD);

		// 엑셀 업로드 상세 리스트
		eduExcelUploadVO.setNotUsedPagination(true);// 페이징처리 안함
		List<EduExcelUploadVO> list = eduMemberService.get_edu_excel_upload_dtl_list(eduExcelUploadVO);

		// 엑셀다운로드 원본 대상자 목록 조회
		EduMemberVO chkEduMemberVO = new EduMemberVO();
		if (MASTER_MBR_TRGT_CD != null && MASTER_MBR_TRGT_CD.length() != 0) {
			chkEduMemberVO.setDTL_CD(MASTER_MBR_TRGT_CD);
		}
		chkEduMemberVO.setMBR_REG_TYPE_CD(MASTER_MBR_POSITION_CD);
		chkEduMemberVO.setMBR_SIDO_CD(MASTER_MBR_SIDO_CD);
		chkEduMemberVO.setMBR_SIGNGU_CD(MASTER_MBR_SIGNGU_CD);
		chkEduMemberVO.setMBR_ST("Y");
		chkEduMemberVO.setMBR_LV_ID("10");
		// chkEduMemberVO.setSearchYear();//전체기준
		// chkEduMemberVO.setDEL_AT("N");
		// chkEduMemberVO.setUSE_AT("Y");
		chkEduMemberVO.setNotUsedPagination(true);
		List<EduMemberVO> list_excel_original = eduMemberService.get_edu_member_target_list_only(chkEduMemberVO);
		List<EduMemberVO> list_dtl_excel_original = new ArrayList<EduMemberVO>();
		for (EduMemberVO item : list_excel_original) {
			EduMemberVO chkDtlEduMemberVO = new EduMemberVO();
			chkDtlEduMemberVO.setMBR_ID(item.getMBR_ID());
			chkDtlEduMemberVO.setUSE_AT("Y");
			chkDtlEduMemberVO.setDTL_CD(MASTER_MBR_TRGT_CD);
			List<EduMemberVO> list_dtl = eduMemberService.get_edu_member_dtl_list(chkDtlEduMemberVO);// 회원상세정보
			for (EduMemberVO item_dtl : list_dtl) {
				item_dtl.setSIDO_NM(item.getSIDO_CD_NM());
				item_dtl.setSIGNGU_NM(item.getSIGNGU_CD_NM());
				item_dtl.setDTL_LICENSE_NM(item.getDTL_LICENSE_CD_NM());
				item_dtl.setMBR_BIRTH(item.getMBR_BIRTH());
				item_dtl.setMBR_HP(item.getMBR_HP());
				item_dtl.setMBR_TEL(item.getMBR_TEL());
				item_dtl.setMBR_ADDR1(item.getMBR_ADDR1());
				item_dtl.setMBR_ZIPCD(item.getMBR_ZIPCD());
				item_dtl.setMBR_EDU_RSPNBER_TYPE(item.getMBR_EDU_RSPNBER_TYPE());
				list_dtl_excel_original.add(item_dtl);
			}
		}
		// End of 엑셀다운로드 원본 대상자 목록 조회

		// null처리를 위해 가져옴
		EgovStringUtil egovStringUtil = new EgovStringUtil();

		// 엑셀 데이터와 DB 데이터가 같으면 담을 리스트
		ArrayList<String> equalDtlSnList = new ArrayList<String>();
		List<EduExcelUploadVO> equalList = new ArrayList<EduExcelUploadVO>();

		// 완벽 일치건 리스트
		ArrayList<String> resultListMatch = new ArrayList<String>();

		// 엑셀 업로드와 전체 교육대상자 검증
		for (EduExcelUploadVO excel_item : list) {// 엑셀 업로드 상세 리스트

			String iDTL_CD = (egovStringUtil.isEmpty(excel_item.getDTL_CD()) ? "" : excel_item.getDTL_CD());
			String iSIDO_NM = (egovStringUtil.isEmpty(excel_item.getSIDO_NM()) ? "" : excel_item.getSIDO_NM());
			String iSIGNGU_NM = (egovStringUtil.isEmpty(excel_item.getSIGNGU_NM()) ? "" : excel_item.getSIGNGU_NM());
			String iREG_NUM_CD = (egovStringUtil.isEmpty(excel_item.getREG_NUM_CD()) ? "" : excel_item.getREG_NUM_CD());
			String iSHIP_CD = (egovStringUtil.isEmpty(excel_item.getSHIP_CD()) ? "" : excel_item.getSHIP_CD());
			String iDTL_NM = (egovStringUtil.isEmpty(excel_item.getDTL_NM()) ? "" : excel_item.getDTL_NM());
			String iDTL_LICENSE_NM = (egovStringUtil.isEmpty(excel_item.getDTL_LICENSE_NM()) ? ""
					: excel_item.getDTL_LICENSE_NM());
			String iSHIP_LICENSE = (egovStringUtil.isEmpty(excel_item.getSHIP_LICENSE()) ? ""
					: excel_item.getSHIP_LICENSE());
			String iMBR_NM = (egovStringUtil.isEmpty(excel_item.getMBR_NM()) ? "" : excel_item.getMBR_NM());
			String iMBR_BIRTH = (egovStringUtil.isEmpty(excel_item.getMBR_BIRTH()) ? "" : excel_item.getMBR_BIRTH());
			String iMBR_HP = (egovStringUtil.isEmpty(excel_item.getMBR_HP()) ? "" : excel_item.getMBR_HP());
			String iMBR_ADDR = (egovStringUtil.isEmpty(excel_item.getMBR_ADDR()) ? "" : excel_item.getMBR_ADDR());
			String iMBR_TEL = (egovStringUtil.isEmpty(excel_item.getMBR_TEL()) ? "" : excel_item.getMBR_TEL());
			String iMBR_ZIPCD = (egovStringUtil.isEmpty(excel_item.getMBR_ZIPCD()) ? "" : excel_item.getMBR_ZIPCD());
			String iMBR_EDU_RSPNBER_TYPE = (egovStringUtil.isEmpty(excel_item.getMBR_EDU_RSPNBER_TYPE()) ? ""
					: excel_item.getMBR_EDU_RSPNBER_TYPE());

			LOGGER.debug("[지자체엑셀현행화:엑셀대상자] MBR_NM : " + iMBR_NM + " , MBR_BIRTH : " + iMBR_BIRTH + " , MBR_HP : "
					+ iMBR_HP + " , DTL_NM : " + iDTL_NM + " , DTL_CD: " + iDTL_CD);

			// 유사회원 담을 리스트
			List<EduExcelUploadVO> resultList2 = new ArrayList<EduExcelUploadVO>();

			// 회원 검증
			LoginVO chkLoginVO = new LoginVO();
			chkLoginVO.setMBR_NM(iMBR_NM);
			chkLoginVO.setMBR_BIRTH(iMBR_BIRTH);
			chkLoginVO.setMBR_HP(iMBR_HP);
			int cnt = loginService.searchAuthOverlayInfo(chkLoginVO);

			eduMemberVO.setMBR_NM(iMBR_NM);
			eduMemberVO.setMBR_BIRTH(iMBR_BIRTH);
			eduMemberVO.setDTL_NM(iDTL_NM);
			eduMemberVO.setMBR_HP(iMBR_HP);

			List<EduMemberVO> target_list = eduMemberService.get_edu_member_dtl_excel_compare(eduMemberVO);
			int dtl_cnt = target_list.size();

			if (cnt > 0) {
				LOGGER.debug("[지자체엑셀현행화:회원검증] 등록정보있음");
				excel_item.setMBR_JOIN_YN("Y");
			} else {
				LOGGER.debug("[지자체엑셀현행화:회원검증] 등록정보없음");
				excel_item.setMBR_JOIN_YN("N");
			}

			boolean isUpdate = true;
			for (EduMemberVO db_item : target_list) {

				String tDTL_SN = db_item.getDTL_SN();
				String tDTL_CD = (egovStringUtil.isEmpty(db_item.getDTL_CD()) ? "" : db_item.getDTL_CD());
				String tSIDO_NM = (egovStringUtil.isEmpty(db_item.getSIDO_NM()) ? "" : db_item.getSIDO_NM());
				String tSIGNGU_NM = (egovStringUtil.isEmpty(db_item.getSIGNGU_NM()) ? "" : db_item.getSIGNGU_NM());
				String tREG_NUM_CD = (egovStringUtil.isEmpty(db_item.getREG_NUM_CD()) ? "" : db_item.getREG_NUM_CD());
				String tSHIP_CD = (egovStringUtil.isEmpty(db_item.getSHIP_CD()) ? "" : db_item.getSHIP_CD());
				String tDTL_NM = (egovStringUtil.isEmpty(db_item.getDTL_NM()) ? "" : db_item.getDTL_NM());
				String tDTL_LICENSE_NM = (egovStringUtil.isEmpty(db_item.getDTL_LICENSE_NM()) ? ""
						: db_item.getDTL_LICENSE_NM());
				String tSHIP_LICENSE = (egovStringUtil.isEmpty(db_item.getSHIP_LICENSE()) ? ""
						: db_item.getSHIP_LICENSE());
				String tMBR_NM = (egovStringUtil.isEmpty(db_item.getMBR_NM()) ? "" : db_item.getMBR_NM());
				String tMBR_BIRTH = (egovStringUtil.isEmpty(db_item.getMBR_BIRTH()) ? "" : db_item.getMBR_BIRTH());
				String tMBR_HP = (egovStringUtil.isEmpty(db_item.getMBR_HP()) ? "" : db_item.getMBR_HP());
				String tMBR_ADDR = (egovStringUtil.isEmpty(db_item.getMBR_ADDR1()) ? "" : db_item.getMBR_ADDR1());
				String tMBR_TEL = (egovStringUtil.isEmpty(db_item.getMBR_TEL()) ? "" : db_item.getMBR_TEL());
				String tMBR_ZIPCD = (egovStringUtil.isEmpty(db_item.getMBR_ZIPCD()) ? "" : db_item.getMBR_ZIPCD());
				String tMBR_EDU_RSPNBER_TYPE = (egovStringUtil.isEmpty(db_item.getMBR_EDU_RSPNBER_TYPE()) ? ""
						: db_item.getMBR_EDU_RSPNBER_TYPE());
				String tMBR_ID = db_item.getMBR_ID();

				LOGGER.debug("[지자체엑셀현행화:DB대상자] MBR_NM : " + tMBR_NM + " , MBR_BIRTH : " + tMBR_BIRTH + " , MBR_HP : "
						+ tMBR_HP + " , DTL_NM : " + tDTL_NM + " , DTL_SN : " + tDTL_SN + " , DTL_CD: " + tDTL_CD);

				// 삭제건 처리를 위한 DTL번호 저장
				if (iMBR_NM.equals(tMBR_NM) && iMBR_BIRTH.equals(tMBR_BIRTH) && iMBR_HP.equals(tMBR_HP)
						&& iDTL_NM.equals(tDTL_NM)) {
					equalDtlSnList.add("DTL_SN" + tDTL_SN);
				}
				// End of 삭제건 처리를 위한 DTL번호 저장

				if (iDTL_CD.equals(tDTL_CD) && iSIDO_NM.equals(tSIDO_NM) && iSIGNGU_NM.equals(tSIGNGU_NM)
						&& iREG_NUM_CD.equals(tREG_NUM_CD) && iSHIP_CD.equals(tSHIP_CD) && iDTL_NM.equals(tDTL_NM)
						&& iDTL_LICENSE_NM.equals(tDTL_LICENSE_NM) && iSHIP_LICENSE.equals(tSHIP_LICENSE)
						&& iMBR_NM.equals(tMBR_NM) && iMBR_BIRTH.equals(tMBR_BIRTH) && iMBR_HP.equals(tMBR_HP)
						&& iMBR_ADDR.equals(tMBR_ADDR) && iMBR_TEL.equals(tMBR_TEL) && iMBR_ZIPCD.equals(tMBR_ZIPCD)
						&& iMBR_EDU_RSPNBER_TYPE.equals(tMBR_EDU_RSPNBER_TYPE)) {
					isUpdate = false;
					excel_item.setMBR_ID(tMBR_ID);
					excel_item.setDTL_SN(tDTL_SN);
					excel_item.setDTL_CD(tDTL_CD);
					equalList.add(excel_item);

					resultListMatch.add("DTL_SN" + tDTL_SN);

					resultList2.clear();

					LOGGER.debug("[지자체엑셀현행화:엑셀검증] 엑셀데이터와 DB데이터가 완벽히 일치하므로 제외");
					break;
				}

				for (EduExcelUploadVO eList : equalList) {

					if (((eList.getDTL_CD() != null) ? eList.getDTL_CD() : "").equals(iDTL_CD)
							&& eList.getSIDO_NM().equals(iSIDO_NM) && eList.getSIGNGU_NM().equals(iSIGNGU_NM)
							&& ((eList.getREG_NUM_CD() != null) ? eList.getREG_NUM_CD() : "").equals(iREG_NUM_CD)
							&& ((eList.getSHIP_CD() != null) ? eList.getSHIP_CD() : "").equals(iSHIP_CD)
							&& ((eList.getDTL_NM() != null) ? eList.getDTL_NM() : "").equals(iDTL_NM)
							&& eList.getDTL_LICENSE_NM().equals(iDTL_LICENSE_NM)
							&& ((eList.getSHIP_LICENSE() != null) ? eList.getSHIP_LICENSE() : "").equals(iSHIP_LICENSE)
							&& eList.getMBR_NM().equals(iMBR_NM) && eList.getMBR_BIRTH().equals(iMBR_BIRTH)
							&& eList.getMBR_HP().equals(iMBR_HP) && eList.getMBR_ADDR().equals(iMBR_ADDR)
							&& ((eList.getMBR_TEL() != null) ? eList.getMBR_TEL() : "").equals(iMBR_TEL)
							&& ((eList.getMBR_ZIPCD() != null) ? eList.getMBR_ZIPCD() : "").equals(iMBR_ZIPCD)
							&& ((eList.getMBR_EDU_RSPNBER_TYPE() != null) ? eList.getMBR_EDU_RSPNBER_TYPE() : "")
									.equals(iMBR_EDU_RSPNBER_TYPE))

					{
						isUpdate = false;
						LOGGER.debug("[지자체엑셀현행화:엑셀검증] eList : " + eList.getDTL_NM() + " , excel  "
								+ excel_item.getDTL_NM() + " = db " + db_item.getDTL_NM());
						LOGGER.debug("[지자체엑셀현행화:엑셀검증] excel " + excel_item.getDTL_LICENSE_NM() + " = db "
								+ db_item.getDTL_LICENSE_NM());
						LOGGER.debug("[지자체엑셀현행화:엑셀검증] 해당 DB데이터는 이미 일치건으로 처리됬으므로 제외");
						break;
					}
				}

				if (isUpdate) {

					EduExcelUploadVO confirmEduExcelUploadVO = new EduExcelUploadVO();

					confirmEduExcelUploadVO.setDTL_SN(tDTL_SN);
					confirmEduExcelUploadVO.setDTL_CD(tDTL_CD);
					confirmEduExcelUploadVO.setSIDO_NM(tSIDO_NM);
					confirmEduExcelUploadVO.setSIGNGU_NM(tSIGNGU_NM);
					confirmEduExcelUploadVO.setREG_NUM_CD(tREG_NUM_CD);
					confirmEduExcelUploadVO.setSHIP_CD(tSHIP_CD);
					confirmEduExcelUploadVO.setDTL_NM(tDTL_NM);
					confirmEduExcelUploadVO.setDTL_LICENSE_NM(tDTL_LICENSE_NM);
					confirmEduExcelUploadVO.setSHIP_LICENSE(tSHIP_LICENSE);
					confirmEduExcelUploadVO.setMBR_NM(tMBR_NM);
					confirmEduExcelUploadVO.setMBR_BIRTH(tMBR_BIRTH);
					confirmEduExcelUploadVO.setMBR_HP(tMBR_HP);
					confirmEduExcelUploadVO.setMBR_ADDR(tMBR_ADDR);
					confirmEduExcelUploadVO.setMBR_TEL(tMBR_TEL);
					confirmEduExcelUploadVO.setMBR_ZIPCD(tMBR_ZIPCD);
					confirmEduExcelUploadVO.setMBR_EDU_RSPNBER_TYPE(tMBR_EDU_RSPNBER_TYPE);
					confirmEduExcelUploadVO.setMBR_ID(tMBR_ID);

					LOGGER.debug("[지자체엑셀현행화:엑셀검증] 해당 DB데이터는 유사건으로 추가됨");

					resultList2.add(confirmEduExcelUploadVO);
				}

			}

			// 회원이나 mbr_dtl 정보가 없음
			if (cnt > 0 && dtl_cnt <= 0) {
				LOGGER.debug("[지자체엑셀현행화:회원인데 회원상세정보 없음]");
				String mbr_id = loginService.searchAuthOverlayInfoMbrId(chkLoginVO);
				excel_item.setMBR_ID(mbr_id);
				excel_item.setCHANGE_INFO("add");
			}

			excel_item.setResultList(resultList2);

		}

		// 완벽 일치건 유사건에서 제외 시키기
		for (EduExcelUploadVO excel_item : list) {
			List<EduExcelUploadVO> excel_dtl_list = excel_item.getResultList();
			List<EduExcelUploadVO> reMergeDtlList = new ArrayList<EduExcelUploadVO>();
			for (EduExcelUploadVO excel_dtl_item : excel_dtl_list) {
				// if(!resultListMatch.contains("DTL_SN"+excel_dtl_item.getDTL_SN()))
				// {//완벽 일치건 유사건에서 제외 해제 2021.02.16
				reMergeDtlList.add(excel_dtl_item);
				// }
			}
			excel_item.setResultList(reMergeDtlList);
		}
		// End of 완벽 일치건 유사건에서 제외 시키기

		// 엑셀다운로드 원본 대상자 목록 비교 처리 : 삭제건 확인
		LOGGER.debug(equalDtlSnList.toString());// 포함되어 있는 경우 삭제로 표현하지 않음.
		for (EduMemberVO orginl_item : list_dtl_excel_original) {
			boolean isAddDelete = true;
			String DTL_SN_KEY = "DTL_SN" + orginl_item.getDTL_SN();
			LOGGER.debug("[지자체엑셀현행화:삭제검증키확인] DTL_SN_KEY : " + DTL_SN_KEY);
			for (String dtl_sn_key : equalDtlSnList) {
				if (dtl_sn_key.equals(DTL_SN_KEY)) {
					LOGGER.debug("[지자체엑셀현행화:삭제처리안함]");
					isAddDelete = false;
					break;
				}
			}
			isAddDelete = false;// 삭제 기능 제거 2021.02.16
			if (isAddDelete) {

				EduExcelUploadVO delEduExcelUploadVO = new EduExcelUploadVO();
				delEduExcelUploadVO.setDTL_SN(orginl_item.getDTL_SN());
				delEduExcelUploadVO.setDTL_CD(orginl_item.getDTL_CD());
				delEduExcelUploadVO.setSIDO_NM(orginl_item.getSIDO_NM());
				delEduExcelUploadVO.setSIGNGU_NM(orginl_item.getSIGNGU_NM());
				delEduExcelUploadVO
						.setREG_NUM_CD((orginl_item.getREG_NUM_CD() != null) ? orginl_item.getREG_NUM_CD() : "");
				delEduExcelUploadVO.setSHIP_CD(orginl_item.getSHIP_CD());
				delEduExcelUploadVO.setDTL_NM(((orginl_item.getDTL_NM() != null) ? orginl_item.getDTL_NM() : ""));
				delEduExcelUploadVO.setDTL_LICENSE_NM(orginl_item.getDTL_LICENSE_NM());
				delEduExcelUploadVO.setSHIP_LICENSE(orginl_item.getSHIP_LICENSE());
				delEduExcelUploadVO.setMBR_NM(orginl_item.getMBR_NM());
				delEduExcelUploadVO.setMBR_BIRTH(orginl_item.getMBR_BIRTH());
				delEduExcelUploadVO.setMBR_HP(orginl_item.getMBR_HP());
				delEduExcelUploadVO.setMBR_ADDR(orginl_item.getMBR_ADDR1());
				delEduExcelUploadVO.setMBR_TEL((orginl_item.getMBR_TEL() != null) ? orginl_item.getMBR_TEL() : "");
				delEduExcelUploadVO
						.setMBR_ZIPCD((orginl_item.getMBR_ZIPCD() != null) ? orginl_item.getMBR_ZIPCD() : "");
				delEduExcelUploadVO.setMBR_EDU_RSPNBER_TYPE(
						(orginl_item.getMBR_EDU_RSPNBER_TYPE() != null) ? orginl_item.getMBR_EDU_RSPNBER_TYPE() : "");
				delEduExcelUploadVO.setMBR_ID(orginl_item.getMBR_ID());
				delEduExcelUploadVO.setCHANGE_INFO("delete");

				// eud_sn 을 가져오면 넣어도 되는데. 일단 보류
				// delEduExcelUploadVO.setEU_SN(eduExcelUploadVO.getEU_SN());
				// eduMemberService.set_edu_excel_upload_dtl_reg(delEduExcelUploadVO);

				LOGGER.debug("[지자체엑셀현행화:삭제검증] 해당 DB데이터는 삭제건으로 추가됨 ( MBR_ID : " + orginl_item.getMBR_ID()
						+ " , MBR_NM : " + orginl_item.getMBR_NM() + ", DTL_SN : " + orginl_item.getDTL_SN()
						+ " , DTL_NM : " + orginl_item.getDTL_NM() + " ) ");

				list.add(delEduExcelUploadVO);
			}
		}

		mModelAndView.addObject("list", list);
		mModelAndView.addObject("list_size", list.size());
		mModelAndView.addObject("EU_SN", eduExcelUploadVO.getEU_SN());
		mModelAndView.addObject("MBR_REG_TYPE_CD", eduExcelUploadVO.getMBR_REG_TYPE_CD());

		mModelAndView.setViewName("/eduadm/member/loc_gov_ajax");

		return mModelAndView;
	}

	// 지자체 업로드 관리자 확인
	@RequestMapping(value = "/eduadm/member/loc_gov_adm_view.do")
	public ModelAndView eduadm_member_loc_gov_adm_view(
			@ModelAttribute("eduExcelUploadVO") EduExcelUploadVO eduExcelUploadVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mModelAndView = new ModelAndView();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		// String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		// String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();

		// 엑셀 업로드 상세 리스트
		eduExcelUploadVO.setNotUsedPagination(true);// 페이징처리 안함
		List<EduExcelUploadVO> list = eduMemberService.get_edu_excel_upload_dtl_list(eduExcelUploadVO);

		for (EduExcelUploadVO excel_item : list) {

			if (excel_item.getCHANGE_INFO() != null && excel_item.getCHANGE_INFO().equals("newAdd")) {// 신규
				// excel_item.setResultList(resultList);
			} else if (excel_item.getCHANGE_INFO() != null && excel_item.getCHANGE_INFO().equals("add")) {// 추가

				EduMemberVO chkEduMemberVO = new EduMemberVO();
				chkEduMemberVO.setMBR_ID(excel_item.getMBR_ID());
				chkEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);

				List<EduExcelUploadVO> resultList = new ArrayList<EduExcelUploadVO>();
				EduExcelUploadVO confirmEduExcelUploadVO = new EduExcelUploadVO();

				// confirmEduExcelUploadVO.setMBR_NM(excel_item.getMBR_NM());
				// confirmEduExcelUploadVO.setMBR_BIRTH(excel_item.getMBR_BIRTH());
				// confirmEduExcelUploadVO.setMBR_HP(excel_item.getMBR_HP());
				// confirmEduExcelUploadVO.setMBR_ADDR(excel_item.getMBR_ADDR());
				// confirmEduExcelUploadVO.setMBR_TEL((excel_item.getMBR_TEL()!=null)?excel_item.getMBR_TEL():"");
				// confirmEduExcelUploadVO.setMBR_ZIPCD((excel_item.getMBR_ZIPCD()!=null)?excel_item.getMBR_ZIPCD():"");
				// confirmEduExcelUploadVO.setMBR_EDU_RSPNBER_TYPE((excel_item.getMBR_EDU_RSPNBER_TYPE()!=null)?excel_item.getMBR_EDU_RSPNBER_TYPE():"");
				// confirmEduExcelUploadVO.setMBR_ID(excel_item.getMBR_ID());

				confirmEduExcelUploadVO.setMBR_NM(chkEduMemberVO.getMBR_NM());
				confirmEduExcelUploadVO.setMBR_BIRTH(chkEduMemberVO.getMBR_BIRTH());
				confirmEduExcelUploadVO.setMBR_HP(chkEduMemberVO.getMBR_HP());
				confirmEduExcelUploadVO.setMBR_ADDR(chkEduMemberVO.getMBR_ADDR1() + chkEduMemberVO.getMBR_ADDR2());
				confirmEduExcelUploadVO
						.setMBR_TEL((chkEduMemberVO.getMBR_TEL() != null) ? chkEduMemberVO.getMBR_TEL() : "");
				confirmEduExcelUploadVO
						.setMBR_ZIPCD((chkEduMemberVO.getMBR_ZIPCD() != null) ? chkEduMemberVO.getMBR_ZIPCD() : "");
				confirmEduExcelUploadVO.setMBR_EDU_RSPNBER_TYPE((chkEduMemberVO.getMBR_EDU_RSPNBER_TYPE() != null)
						? chkEduMemberVO.getMBR_EDU_RSPNBER_TYPE() : "");
				confirmEduExcelUploadVO.setMBR_ID(chkEduMemberVO.getMBR_ID());

				resultList.add(confirmEduExcelUploadVO);
				excel_item.setResultList(resultList);
			} else if (excel_item.getCHANGE_INFO() != null && excel_item.getCHANGE_INFO().equals("change")) {// 변경
				try {
					EduMemberVO chkEduMemberVO = new EduMemberVO();
					chkEduMemberVO.setMBR_ID(excel_item.getMBR_ID());
					chkEduMemberVO.setDTL_SN(excel_item.getDTL_SN());
					chkEduMemberVO = eduMemberService.get_edu_member_dtl_info(chkEduMemberVO);

					List<EduExcelUploadVO> resultList = new ArrayList<EduExcelUploadVO>();
					EduExcelUploadVO confirmEduExcelUploadVO = new EduExcelUploadVO();

					confirmEduExcelUploadVO.setDTL_SN(chkEduMemberVO.getDTL_SN());

					// 지역 코드 조회 - 시도
					if (chkEduMemberVO.getSIDO_CD() != null && chkEduMemberVO.getSIDO_CD().length() != 0) {
						CodeSetVO mCodeSetVO = new CodeSetVO();
						mCodeSetVO.setCD_ID(chkEduMemberVO.getSIDO_CD());
						CodeSetVO item_category = codeSetService.get_codeset_info(mCodeSetVO);
						confirmEduExcelUploadVO.setSIDO_NM(item_category.getCD_NM());
					}

					// 지역 코드 조회 - 시군구
					if (chkEduMemberVO.getSIGNGU_CD() != null && chkEduMemberVO.getSIGNGU_CD().length() != 0) {
						CodeSetVO mCodeSetVO = new CodeSetVO();
						mCodeSetVO.setCD_ID(chkEduMemberVO.getSIGNGU_CD());
						CodeSetVO item_category = codeSetService.get_codeset_info(mCodeSetVO);
						confirmEduExcelUploadVO.setSIGNGU_NM(item_category.getCD_NM());
					}

					confirmEduExcelUploadVO.setREG_NUM_CD(
							(chkEduMemberVO.getREG_NUM_CD() != null) ? chkEduMemberVO.getREG_NUM_CD() : "");
					confirmEduExcelUploadVO.setSHIP_CD(chkEduMemberVO.getSHIP_CD());
					confirmEduExcelUploadVO
							.setDTL_NM(((chkEduMemberVO.getDTL_NM() != null) ? chkEduMemberVO.getDTL_NM() : ""));
					confirmEduExcelUploadVO.setDTL_LICENSE_CD(chkEduMemberVO.getDTL_LICENSE_CD());
					confirmEduExcelUploadVO.setSHIP_LICENSE(chkEduMemberVO.getSHIP_LICENSE());
					confirmEduExcelUploadVO.setMBR_NM(chkEduMemberVO.getMBR_NM());
					confirmEduExcelUploadVO.setMBR_BIRTH(chkEduMemberVO.getMBR_BIRTH());
					confirmEduExcelUploadVO.setMBR_HP(chkEduMemberVO.getMBR_HP());
					confirmEduExcelUploadVO.setMBR_ADDR(chkEduMemberVO.getMBR_ADDR1());
					confirmEduExcelUploadVO
							.setMBR_TEL((chkEduMemberVO.getMBR_TEL() != null) ? chkEduMemberVO.getMBR_TEL() : "");
					confirmEduExcelUploadVO
							.setMBR_ZIPCD((chkEduMemberVO.getMBR_ZIPCD() != null) ? chkEduMemberVO.getMBR_ZIPCD() : "");
					confirmEduExcelUploadVO.setMBR_EDU_RSPNBER_TYPE((chkEduMemberVO.getMBR_EDU_RSPNBER_TYPE() != null)
							? chkEduMemberVO.getMBR_EDU_RSPNBER_TYPE() : "");
					confirmEduExcelUploadVO.setMBR_ID(chkEduMemberVO.getMBR_ID());

					resultList.add(confirmEduExcelUploadVO);
					excel_item.setResultList(resultList);
				} catch (Exception e) {
					e.fillInStackTrace();
				}
			} else {

			}
		}

		mModelAndView.addObject("list", list);
		mModelAndView.addObject("list_size", list.size());
		mModelAndView.addObject("EU_SN", eduExcelUploadVO.getEU_SN());
		mModelAndView.addObject("EUD_SN", eduExcelUploadVO.getEUD_SN());
		// mModelAndView.addObject("MBR_REG_TYPE_CD",
		// eduExcelUploadVO.getMBR_REG_TYPE_CD());
		// MBR_REG_TYPE_CD = MASTER_MBR_POSITION_CD 같음, 로그인VO에서 꺼내오는게 보안면에서 나을듯?
		mModelAndView.addObject("MASTER_MBR_POSITION_CD", MASTER_MBR_POSITION_CD);
		mModelAndView.addObject("eduExcelUploadVO", eduMemberService.get_edu_excel_upload_info(eduExcelUploadVO));

		mModelAndView.setViewName("/eduadm/member/loc_gov_adm_ajax");

		return mModelAndView;
	}

	// 관리자(교육센터) 지자체명단 현행화 업로드 리스트 - 승인요청
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/loc_gov_request.do", method = RequestMethod.POST)
	public @ResponseBody String eduadm_member_loc_gov_request(
			@ModelAttribute("eduExcelUploadVO") EduExcelUploadVO eduExcelUploadVO,
			@RequestParam(value = "json_data", required = false) String json_data, HttpServletRequest request,
			HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();// 권한레벨(1:최고,2:공단,3:,4:지자체/해경/교육기관)
		String MASTER_MBR_GRP_ID = loginVO.getMBR_GRP_ID();
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();// 직급코드[CID00003]=>REG_TYPE_CD,MBR_REG_TYPE_CD
		String MASTER_MBR_TRGT_CD = loginVO.getMBR_TRGT_CD();// (관리자)교육대상 제한코드

		LOGGER.debug("########################");
		LOGGER.debug("MASTER_MBR_TRGT_CD : " + MASTER_MBR_TRGT_CD);

		if (MASTER_MBR_TRGT_CD != null && MASTER_MBR_TRGT_CD.length() != 0) {
			// eduExcelUploadVO.setDTL_CD(MASTER_MBR_TRGT_CD);
			LOGGER.debug("########################");
			LOGGER.debug("MASTER_MBR_TRGT_CD : " + MASTER_MBR_TRGT_CD);
		} else {
			// 지자체 관리자가 교육대상자 제한없을 시 값을 따로 받아야함!
			// 흠...
			LOGGER.debug("########################");
			LOGGER.debug("교육대상자....흠");
		}
		JSONObject data = new JSONObject();
		try {

			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder log_msg = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();

			if (json_data != null && json_data.length() != 0 && !json_data.equals("undefined")) {

				// JSON.stringify( )를 통하여 문자열로 변환 한 뒤 input태그에 담아 Form Submit을
				// 하면 XSS방지를 위하여 특수문자 변환이 이뤄진다.
				// .replace( ) 작업을 통하여 완전한 JSON String형태로 변환을 해준다.
				json_data = json_data.replaceAll("&quot;", "\"");

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(json_data);
				JSONArray newAdd_arr = (JSONArray) obj.get("newAdd");
				JSONArray change_arr = (JSONArray) obj.get("change");
				JSONArray add_arr = (JSONArray) obj.get("add");
				JSONArray del_arr = (JSONArray) obj.get("del");
				JSONArray db_arr = (JSONArray) obj.get("db");
				JSONArray changeNone_arr = (JSONArray) obj.get("changeNone");

				LOGGER.debug(json_data.toString());
				LOGGER.debug(newAdd_arr.toString());
				LOGGER.debug(change_arr.toString());
				LOGGER.debug(add_arr.toString());
				LOGGER.debug(del_arr.toString());
				LOGGER.debug(db_arr.toString());
				LOGGER.debug(changeNone_arr.toString());

				if (db_arr.size() != 0) {
					for (int i = 0; i < db_arr.size(); i++) {// 기존 DB데이터
						JSONObject db_obj = (JSONObject) db_arr.get(i);

						eduExcelUploadVO.setMBR_ID((String) db_obj.get("mbr_id"));
						eduExcelUploadVO.setEUD_SN((String) db_obj.get("eud_sn"));
						eduExcelUploadVO.setDTL_SN((String) db_obj.get("dtl_sn"));
						eduMemberService.set_edu_excel_upload_dtl_mod(eduExcelUploadVO);

						LOGGER.debug("mbr_id : " + (String) db_obj.get("mbr_id"));
						LOGGER.debug("edu_sn : " + (String) db_obj.get("eud_sn"));
					}
				}

				if (changeNone_arr.size() != 0) {
					for (int i = 0; i < changeNone_arr.size(); i++) {// 기존
																		// DB데이터-처리안함
						JSONObject db_obj = (JSONObject) changeNone_arr.get(i);

						eduExcelUploadVO.setMBR_ID((String) db_obj.get("mbr_id"));
						eduExcelUploadVO.setEUD_SN((String) db_obj.get("eud_sn"));
						eduExcelUploadVO.setDTL_SN((String) db_obj.get("dtl_sn"));
						eduExcelUploadVO.setCHANGE_INFO("none");// 변경 정보
						eduMemberService.set_edu_excel_upload_dtl_mod(eduExcelUploadVO);

						LOGGER.debug("mbr_id : " + (String) db_obj.get("mbr_id"));
						LOGGER.debug("edu_sn : " + (String) db_obj.get("eud_sn"));
					}
				}

				if (newAdd_arr.size() != 0) {
					for (int i = 0; i < newAdd_arr.size(); i++) {// 신규등록

						JSONObject newAdd_obj = (JSONObject) newAdd_arr.get(i);

						eduExcelUploadVO.setMBR_ID((String) newAdd_obj.get("mbr_id"));
						eduExcelUploadVO.setEUD_SN((String) newAdd_obj.get("eud_sn"));
						eduExcelUploadVO.setDTL_SN("");
						eduExcelUploadVO.setCHANGE_INFO("newAdd");// 변경 정보
						eduMemberService.set_edu_excel_upload_dtl_mod(eduExcelUploadVO);

						LOGGER.debug("mbr_id : " + (String) newAdd_obj.get("mbr_id"));
						LOGGER.debug("edu_sn : " + (String) newAdd_obj.get("eud_sn"));

					}
				}

				if (change_arr.size() != 0) {
					for (int i = 0; i < change_arr.size(); i++) {// 변경
						JSONObject change_obj = (JSONObject) change_arr.get(i);

						eduExcelUploadVO.setMBR_ID((String) change_obj.get("mbr_id"));
						eduExcelUploadVO.setEUD_SN((String) change_obj.get("eud_sn"));
						eduExcelUploadVO.setDTL_SN((String) change_obj.get("dtl_sn"));
						eduExcelUploadVO.setCHANGE_INFO("change");// 변경 정보
						eduMemberService.set_edu_excel_upload_dtl_mod(eduExcelUploadVO);

						LOGGER.debug("mbr_id : " + (String) change_obj.get("mbr_id"));
						LOGGER.debug("edu_sn : " + (String) change_obj.get("eud_sn"));
					}
				}

				if (add_arr.size() != 0) {
					for (int i = 0; i < add_arr.size(); i++) {// 추가
						JSONObject add_obj = (JSONObject) add_arr.get(i);

						eduExcelUploadVO.setMBR_ID((String) add_obj.get("mbr_id"));
						eduExcelUploadVO.setEUD_SN((String) add_obj.get("eud_sn"));
						eduExcelUploadVO.setDTL_SN("");
						eduExcelUploadVO.setCHANGE_INFO("add");// 변경 정보
						eduMemberService.set_edu_excel_upload_dtl_mod(eduExcelUploadVO);

						LOGGER.debug("mbr_id : " + (String) add_obj.get("mbr_id"));
						LOGGER.debug("edu_sn : " + (String) add_obj.get("eud_sn"));
					}
				}

				if (del_arr.size() != 0) {
					for (int i = 0; i < del_arr.size(); i++) {// 삭제 건 등록
						JSONObject del_obj = (JSONObject) del_arr.get(i);

						String MBR_ID = (String) del_obj.get("mbr_id");
						String DTL_SN = (String) del_obj.get("dtl_sn");

						LOGGER.debug("MBR_ID : " + MBR_ID);
						LOGGER.debug("DTL_SN : " + DTL_SN);

						EduMemberVO chkEduMemberVO = new EduMemberVO();
						chkEduMemberVO.setMBR_ID(MBR_ID);
						chkEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);

						EduMemberVO chkDtlEduMemberVO = new EduMemberVO();
						chkDtlEduMemberVO.setDTL_SN(DTL_SN);
						chkDtlEduMemberVO.setMBR_ID(MBR_ID);
						chkDtlEduMemberVO.setUSE_AT("Y");
						chkDtlEduMemberVO.setDTL_CD(MASTER_MBR_TRGT_CD);
						chkDtlEduMemberVO = eduMemberService.get_edu_member_dtl_info(chkDtlEduMemberVO);// 회원상세정보

						chkDtlEduMemberVO.setMBR_BIRTH(chkEduMemberVO.getMBR_BIRTH());
						chkDtlEduMemberVO.setMBR_HP(chkEduMemberVO.getMBR_HP());
						chkDtlEduMemberVO.setMBR_TEL(chkEduMemberVO.getMBR_TEL());
						chkDtlEduMemberVO.setMBR_ADDR1(chkEduMemberVO.getMBR_ADDR1());
						chkDtlEduMemberVO.setMBR_ZIPCD(chkEduMemberVO.getMBR_ZIPCD());
						chkDtlEduMemberVO.setMBR_EDU_RSPNBER_TYPE(chkEduMemberVO.getMBR_EDU_RSPNBER_TYPE());

						EduExcelUploadVO delEduExcelUploadVO = new EduExcelUploadVO();
						delEduExcelUploadVO.setDTL_SN(chkDtlEduMemberVO.getDTL_SN());
						delEduExcelUploadVO.setDTL_CD(chkDtlEduMemberVO.getDTL_CD());

						// 지역 코드 조회 - 시도
						{
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_ID(chkDtlEduMemberVO.getSIDO_CD());
							CodeSetVO item_category = codeSetService.get_codeset_info(mCodeSetVO);
							delEduExcelUploadVO.setSIDO_NM(item_category.getCD_NM());
						}

						// 지역 코드 조회 - 시군구
						{
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_ID(chkDtlEduMemberVO.getSIGNGU_CD());
							CodeSetVO item_category = codeSetService.get_codeset_info(mCodeSetVO);
							delEduExcelUploadVO.setSIGNGU_NM(item_category.getCD_NM());
						}

						// 사업자구분코드
						{
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_ID(chkDtlEduMemberVO.getDTL_LICENSE_CD());
							CodeSetVO item_category = codeSetService.get_codeset_info(mCodeSetVO);
							delEduExcelUploadVO.setDTL_LICENSE_NM(item_category.getCD_NM());
						}

						delEduExcelUploadVO.setREG_NUM_CD(
								(chkDtlEduMemberVO.getREG_NUM_CD() != null) ? chkDtlEduMemberVO.getREG_NUM_CD() : "");
						delEduExcelUploadVO.setSHIP_CD(chkDtlEduMemberVO.getSHIP_CD());
						delEduExcelUploadVO.setDTL_NM(
								((chkDtlEduMemberVO.getDTL_NM() != null) ? chkDtlEduMemberVO.getDTL_NM() : ""));
						delEduExcelUploadVO.setSHIP_LICENSE(chkDtlEduMemberVO.getSHIP_LICENSE());
						delEduExcelUploadVO.setMBR_NM(chkDtlEduMemberVO.getMBR_NM());
						delEduExcelUploadVO.setMBR_BIRTH(chkDtlEduMemberVO.getMBR_BIRTH());
						delEduExcelUploadVO.setMBR_HP(chkDtlEduMemberVO.getMBR_HP());
						delEduExcelUploadVO.setMBR_ADDR(chkDtlEduMemberVO.getMBR_ADDR1());
						delEduExcelUploadVO.setMBR_ZIPCD(
								(chkEduMemberVO.getMBR_ZIPCD() != null) ? chkEduMemberVO.getMBR_ZIPCD() : "");
						delEduExcelUploadVO.setMBR_EDU_RSPNBER_TYPE((chkEduMemberVO.getMBR_EDU_RSPNBER_TYPE() != null)
								? chkEduMemberVO.getMBR_EDU_RSPNBER_TYPE() : "");
						delEduExcelUploadVO.setMBR_TEL(
								(chkDtlEduMemberVO.getMBR_TEL() != null) ? chkDtlEduMemberVO.getMBR_TEL() : "");
						delEduExcelUploadVO.setMBR_ID(chkDtlEduMemberVO.getMBR_ID());
						delEduExcelUploadVO.setCHANGE_INFO("delete");
						delEduExcelUploadVO.setEU_SN(eduExcelUploadVO.getEU_SN());
						eduMemberService.set_edu_excel_upload_dtl_reg(delEduExcelUploadVO);

					}
				}

			}

			eduMemberService.set_edu_excel_upload_request(eduExcelUploadVO);
			data.put("error", 0);
			data.put("msg", "신청되었습니다.");

			log_msg.append("[아이디:" + MASTER_MBR_ID + "]");
			log_msg.append("[처리권한:" + MASTER_MBR_GRP_ID + "]");
			log_msg.append("[처리레벨:" + MASTER_MBR_LV_ID + "]");
			log_msg.append("[처리직급:" + MASTER_MBR_POSITION_CD + "]");
			log_msg.append("[교육대상:" + MASTER_MBR_TRGT_CD + "]");

			tbl_inf.append("EDU_EXCEL_UPLOAD_TB, EDU_EXCEL_UPLOAD_DTL_TB");
			tbl_sn.append(eduExcelUploadVO.getEU_SN());

			try {
				/**
				 * LOG RECORED (로그기록)
				 */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduExcelUploadVO));
				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
				mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO, request);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.debug("[fail log record] " + e.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	// 관리자(교육센터) 지자체명단 현행화 업로드 리스트 - 승인요청건처리
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/loc_gov_request_act.do", method = RequestMethod.POST)
	public @ResponseBody String eduadm_member_loc_gov_request_act(
			@ModelAttribute("eduExcelUploadVO") EduExcelUploadVO eduExcelUploadVO,
			@RequestParam(value = "chkedIds", required = false) String chkedIds,
			@RequestParam(value = "chkedMbrModifyIds", required = false) String chkedMbrModifyIds,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();

		try {

			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder log_msg = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();

			EduExcelUploadVO info = eduMemberService.get_edu_excel_upload_info(eduExcelUploadVO);
			List<EduExcelUploadVO> list = eduMemberService.get_edu_excel_upload_dtl_list(eduExcelUploadVO);
			// 대상구분 코드 조회
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00002");
				mCodeSetVO.setHIDE_AT("N");
				List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
				model.addAttribute("list_mbr_cd", list_mbr_cd);
			}

			// 사업자구분코드
			List<CodeSetVO> list_position_cd = null;
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00006");
				list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
				// model.addAttribute("list_license_se_cd",list_position_cd);
			}

			// 지역 코드 조회 - 시도
			List<CodeSetVO> list_address_cd = null;
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00004");
				list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
				model.addAttribute("list_address_cd", list_address_cd);
			}

			// 지역 코드 조회 - 시군구
			Map<String, List<CodeSetVO>> list_address_signgu_cd = new HashMap<String, List<CodeSetVO>>();
			for (CodeSetVO sido_item : list_address_cd) {
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID(sido_item.getCD_ID());// 시도 코드
				List<CodeSetVO> signgu_list = codeSetService.get_codeset_list(mCodeSetVO);// 시군구
																							// 리스트
				list_address_signgu_cd.put(sido_item.getCD_ID(), signgu_list);
			}

			String[] eudSns = chkedIds.replaceAll("\\s", "").split(",");
			String[] mbrModifyEudSn = chkedMbrModifyIds.replaceAll("\\s", "").split(",");

			EduMemberVO adminInfo = new EduMemberVO();
			adminInfo.setMBR_ID(info.getREG_MBR_ID());
			adminInfo = eduMemberService.get_edu_member_info(adminInfo);

			String MBR_REG_TYPE_CD = "PCD0003";// adminInfo.getMBR_POSITION_CD();

			for (EduExcelUploadVO item : list) {

				boolean isReject = true;
				for (String EUD_SN : eudSns) {
					if (item.getEUD_SN().equals(EUD_SN)) {
						isReject = false;
					}
				}
				if (isReject)
					continue;

				boolean isMbrModify = false;
				for (String EUD_SN : mbrModifyEudSn) {
					if (item.getEUD_SN().equals(EUD_SN)) {
						isMbrModify = true;
					}
				}

				EduMemberVO eduMemberVO = new EduMemberVO();

				if (item.getSIDO_NM() != null && item.getSIDO_NM().length() != 0) {
					for (CodeSetVO code : list_address_cd) {
						if (code.getCD_NM().contains(item.getSIDO_NM())) {
							eduMemberVO.setSIDO_CD(code.getCD_ID());// 시도 코드

							if (item.getSIGNGU_NM() != null && item.getSIGNGU_NM().length() != 0) {
								List<CodeSetVO> signgu_list = list_address_signgu_cd.get(code.getCD_ID());
								for (CodeSetVO signgu_item : signgu_list) {
									if (signgu_item.getCD_NM().contains(item.getSIGNGU_NM())) {
										eduMemberVO.setSIGNGU_CD(signgu_item.getCD_ID());// 시군구
																							// 코드
									}
								}
							}
						}
					}
				}

				eduMemberVO.setREG_NUM_CD(item.getREG_NUM_CD());// 신고번호(어선) /
																// 허가(등록)번호(낚시터)
				eduMemberVO.setSHIP_CD(item.getSHIP_CD()); // 어선부가정보-어선번호
				eduMemberVO.setDTL_NM(item.getDTL_NM()); // 어선명 / 낚시터명

				if (item.getDTL_LICENSE_NM() != null && item.getDTL_LICENSE_NM().length() != 0) {
					for (CodeSetVO code : list_position_cd) {
						if (code.getCD_NM().equals(item.getDTL_LICENSE_NM())) {
							eduMemberVO.setDTL_LICENSE_CD(code.getCD_ID());// 사업자구분코드(낚시어선업자
																			// 선주/선원
																			// ,
																			// 개인사업자
																			// ,
																			// 법인사업자
																			// ,
																			// 해기사면허
																			// 소지
																			// 여부)
						}
					}
				}

				eduMemberVO.setMBR_NM(item.getMBR_NM()); // 이름
				eduMemberVO.setDTL_ADDR(item.getMBR_ADDR()); // 소재지
				eduMemberVO.setSHIP_LICENSE(item.getSHIP_LICENSE());// 어선부가정보-해기사면허,낚시터부가정보-업구분
				eduMemberVO.setDTL_CD(item.getDTL_CD());// 구분(낚시터:CIDN010200/어선:CIDN010300)

				String MBR_ID = "";
				String MBR_NM = "";

				boolean isChkEduTrgt = false;
				if (item.getCHANGE_INFO() != null && item.getCHANGE_INFO().equals("newAdd")) {

					isChkEduTrgt = true;
					EduMemberVO newEduMemberVO = new EduMemberVO();
					newEduMemberVO.setMBR_NM(item.getMBR_NM());// 이름
					newEduMemberVO.setMBR_HP(item.getMBR_HP());// 연락처
					newEduMemberVO.setMBR_TEL(item.getMBR_TEL());// 일반전화
					newEduMemberVO.setMBR_BIRTH(item.getMBR_BIRTH());// 생년월일
					newEduMemberVO.setMBR_ADDR1(item.getMBR_ADDR());// 주소
					newEduMemberVO.setMBR_ZIPCD(item.getMBR_ZIPCD());// 우편번호
					newEduMemberVO.setMBR_EDU_RSPNBER_TYPE(item.getMBR_EDU_RSPNBER_TYPE());// 대표자,교육책임자
					newEduMemberVO.setMBR_REG_TYPE_CD(MBR_REG_TYPE_CD);// 등록구분코드(지자체,공단)
					newEduMemberVO.setMBR_ID(newEduMemberVO.getUniqKey("MID"));// 아이디
					newEduMemberVO.setGOV_MOD_DT("now");// 정보등록일자
					newEduMemberVO.setREG_MBR_ID(info.getMBR_ID());// 등록자아이디(엑셀업로드
																	// 회원)
					newEduMemberVO.setMBR_NCNM(item.getMBR_NM());// 닉네임
					eduMemberService.set_edu_member_reg(newEduMemberVO);

					MBR_ID = newEduMemberVO.getMBR_ID();
					MBR_NM = newEduMemberVO.getMBR_NM();

					eduMemberVO.setMBR_CD(newEduMemberVO.getMBR_CD());
					eduMemberVO.setMBR_ID(newEduMemberVO.getMBR_ID());
					eduMemberVO.setREG_MBR_ID(info.getREG_MBR_ID());
					eduMemberVO.setREG_TYPE_CD(MBR_REG_TYPE_CD);
					eduMemberVO.setGOV_MOD_DT("now");
					eduMemberService.set_edu_member_dtl_reg(eduMemberVO);

					// 사용자사유로그기록
					{
						EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
						logRecordService.set_log_mbr_mod_data("MBR_TB", "신규", "[지자체명단 현행화]",
								realEduMemberVO.getMBR_ID(), realEduMemberVO.getMBR_NM(), realEduMemberVO, null,
								loginVO, request);
					}

					item.setEUD_CONFM_ST("Y");

				} else if (item.getCHANGE_INFO() != null && item.getCHANGE_INFO().equals("change")) {

					EduMemberVO chkMemberVO = new EduMemberVO();
					chkMemberVO.setMBR_ID(item.getMBR_ID());
					chkMemberVO = eduMemberService.get_edu_member_info(chkMemberVO);

					isChkEduTrgt = true;
					EduMemberVO chkEduMemberVO = new EduMemberVO();
					chkEduMemberVO.setDTL_SN(item.getDTL_SN());
					chkEduMemberVO = eduMemberService.get_edu_member_dtl_info(chkEduMemberVO);

					MBR_ID = chkEduMemberVO.getMBR_ID();
					MBR_NM = chkEduMemberVO.getMBR_NM();

					EduMemberVO newEduMemberVO = new EduMemberVO();
					newEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());

					if (isMbrModify) {
						newEduMemberVO.setMBR_NM(item.getMBR_NM());// 이름
						newEduMemberVO.setMBR_HP(item.getMBR_HP());// 연락처
						newEduMemberVO.setMBR_TEL(item.getMBR_TEL());// 일반전화
						newEduMemberVO.setMBR_BIRTH(item.getMBR_BIRTH());// 생년월일
						newEduMemberVO.setMBR_ADDR1(item.getMBR_ADDR());// 주소
						newEduMemberVO.setMBR_ZIPCD(item.getMBR_ZIPCD());// 우편번호
					}

					newEduMemberVO.setMBR_REG_TYPE_CD(MBR_REG_TYPE_CD);
					newEduMemberVO.setGOV_MOD_DT("now");
					newEduMemberVO.setUPD_MBR_ID(info.getREG_MBR_ID());
					newEduMemberVO.setMBR_EDU_RSPNBER_TYPE(item.getMBR_EDU_RSPNBER_TYPE());
					newEduMemberVO.setMBR_INDVDL_INFO_DT(chkMemberVO.getMBR_INDVDL_INFO_DT());
					newEduMemberVO.setMBR_SIDO_CD(eduMemberVO.getSIDO_CD());
					newEduMemberVO.setMBR_SIGNGU_CD(eduMemberVO.getSIGNGU_CD());
					eduMemberService.set_edu_member_mod(newEduMemberVO);

					if (isMbrModify) {
						// 사용자사유로그기록
						{
							EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(newEduMemberVO);
							logRecordService.set_log_mbr_mod_data("MBR_TB", "수정", "[지자체명단 현행화]",
									realEduMemberVO.getMBR_ID(), realEduMemberVO.getMBR_NM(), chkEduMemberVO,
									newEduMemberVO, loginVO, request);
						}
					}

					eduMemberVO.setDTL_CD(chkEduMemberVO.getDTL_CD());// 구분(낚시터:CIDN010200/어선:CIDN010300)
					eduMemberVO.setREG_TYPE_CD(MBR_REG_TYPE_CD);
					eduMemberVO.setYMD_NM(chkEduMemberVO.getYMD_NM());
					eduMemberVO.setBUSINESS_NUM(chkEduMemberVO.getBUSINESS_NUM());
					eduMemberVO.setFSHLC_APPLC(chkEduMemberVO.getFSHLC_APPLC());
					eduMemberVO.setFSHLC_WORK_CD(chkEduMemberVO.getFSHLC_WORK_CD());
					eduMemberVO.setSHIP_GRTG(chkEduMemberVO.getSHIP_GRTG());
					eduMemberVO.setSHIP_PRLOAD(chkEduMemberVO.getSHIP_PRLOAD());
					eduMemberVO.setSHIP_MAX_PSNGER(chkEduMemberVO.getSHIP_MAX_PSNGER());
					eduMemberVO.setSHIP_MAX_CREW(chkEduMemberVO.getSHIP_MAX_CREW());
					eduMemberVO.setGOV_MOD_DT("now");
					eduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
					eduMemberVO.setDTL_SN(chkEduMemberVO.getDTL_SN());
					eduMemberVO.setUPD_MBR_ID(info.getREG_MBR_ID());
					eduMemberService.set_edu_member_dtl_mod(eduMemberVO);

					// 사용자사유로그기록
					{
						EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
						logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "수정", "[지자체명단 현행화]",
								realEduMemberVO.getMBR_ID(), realEduMemberVO.getMBR_NM(), chkEduMemberVO, eduMemberVO,
								loginVO, request);
					}

					item.setEUD_CONFM_ST("Y");

				} else if (item.getCHANGE_INFO() != null && item.getCHANGE_INFO().equals("add")) {

					isChkEduTrgt = true;

					EduMemberVO chkMemberVO = new EduMemberVO();
					chkMemberVO.setMBR_ID(item.getMBR_ID());
					chkMemberVO = eduMemberService.get_edu_member_info(chkMemberVO);

					MBR_ID = item.getMBR_ID();
					MBR_NM = item.getMBR_NM();

					EduMemberVO newEduMemberVO = new EduMemberVO();
					newEduMemberVO.setMBR_ID(item.getMBR_ID());

					if (isMbrModify) {
						newEduMemberVO.setMBR_NM(item.getMBR_NM());// 이름
						newEduMemberVO.setMBR_HP(item.getMBR_HP());// 연락처
						newEduMemberVO.setMBR_TEL(item.getMBR_TEL());// 일반전화
						newEduMemberVO.setMBR_BIRTH(item.getMBR_BIRTH());// 생년월일
						newEduMemberVO.setMBR_ADDR1(item.getMBR_ADDR());// 주소
						newEduMemberVO.setMBR_ZIPCD(item.getMBR_ZIPCD());// 우편번호
					}

					newEduMemberVO.setMBR_REG_TYPE_CD(MBR_REG_TYPE_CD);
					newEduMemberVO.setGOV_MOD_DT("now");
					newEduMemberVO.setUPD_MBR_ID(info.getREG_MBR_ID());
					newEduMemberVO.setMBR_EDU_RSPNBER_TYPE(item.getMBR_EDU_RSPNBER_TYPE());
					newEduMemberVO.setMBR_INDVDL_INFO_DT(chkMemberVO.getMBR_INDVDL_INFO_DT());
					newEduMemberVO.setMBR_SIDO_CD(eduMemberVO.getSIDO_CD());
					newEduMemberVO.setMBR_SIGNGU_CD(eduMemberVO.getSIGNGU_CD());
					eduMemberService.set_edu_member_mod(newEduMemberVO);

					eduMemberVO.setMBR_ID(item.getMBR_ID());
					eduMemberVO.setREG_MBR_ID(info.getREG_MBR_ID());
					eduMemberVO.setMBR_REG_TYPE_CD(MBR_REG_TYPE_CD);
					eduMemberService.set_edu_member_dtl_reg(eduMemberVO);

					// 사용자사유로그기록
					{
						logRecordService.set_log_mbr_mod_data("MBR_TB", "신규", "[지자체명단 현행화]", eduMemberVO.getMBR_ID(),
								eduMemberVO.getMBR_NM(), eduMemberVO, null, loginVO, request);
					}

					item.setEUD_CONFM_ST("Y");

				} else if (item.getCHANGE_INFO() != null && item.getCHANGE_INFO().equals("delete")) {

					isChkEduTrgt = false;

					eduMemberVO.setMBR_ID(item.getMBR_ID());
					eduMemberVO.setDTL_SN(item.getDTL_SN());
					eduMemberService.remove_edu_member_dtl(eduMemberVO);// 회원상세정보는
																		// 삭제함

					// 사용자사유로그기록
					{
						logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "삭제", "[지자체명단 현행화]",
								eduMemberVO.getMBR_ID(), eduMemberVO.getMBR_NM(), item, null, loginVO, request);
					}

					List<EduMemberVO> list_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberVO);
					if (list_dtl == null || list_dtl.size() <= 0) {

						EduMemberVO chkMemberVO = new EduMemberVO();
						chkMemberVO.setMBR_ID(item.getMBR_ID());
						chkMemberVO = eduMemberService.get_edu_member_info(chkMemberVO);

						EduMemberVO updEduMemberVO = new EduMemberVO();
						updEduMemberVO.setMBR_ID(item.getMBR_ID());
						updEduMemberVO.setMBR_ST("N");
						updEduMemberVO.setGOV_MOD_DT("now");
						updEduMemberVO.setUPD_MBR_ID(info.getREG_MBR_ID());
						updEduMemberVO.setMBR_REG_TYPE_CD(MBR_REG_TYPE_CD);
						updEduMemberVO.setMBR_INDVDL_INFO_DT(chkMemberVO.getMBR_INDVDL_INFO_DT());
						updEduMemberVO.setMBR_SIDO_CD(chkMemberVO.getMBR_SIDO_CD());
						updEduMemberVO.setMBR_SIGNGU_CD(chkMemberVO.getMBR_SIGNGU_CD());
						eduMemberService.set_edu_member_mod(updEduMemberVO);// 회원은
																			// 사용안함
																			// 처리

						// 사용자사유로그기록
						{
							EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
							logRecordService.set_log_mbr_mod_data("MBR_TB", "삭제(사용안함)", "[지자체명단 현행화]",
									realEduMemberVO.getMBR_ID(), realEduMemberVO.getMBR_NM(), eduMemberVO,
									realEduMemberVO, loginVO, request);
						}
					}

					item.setEUD_CONFM_ST("Y");

				} else if (item.getCHANGE_INFO() != null && item.getCHANGE_INFO().equals("none")) {

					// 처리안함 구간
					item.setEUD_CONFM_ST("N");

				} else { // 기존과 동일
					isChkEduTrgt = true;
					MBR_ID = item.getMBR_ID();
					MBR_NM = item.getMBR_NM();

					EduMemberVO chkMemberVO = new EduMemberVO();
					chkMemberVO.setMBR_ID(item.getMBR_ID());
					chkMemberVO = eduMemberService.get_edu_member_info(chkMemberVO);

					EduMemberVO chkEduMemberVO = new EduMemberVO();
					chkEduMemberVO.setDTL_SN(item.getDTL_SN());
					chkEduMemberVO.setMBR_ID(MBR_ID);
					chkEduMemberVO = eduMemberService.get_edu_member_dtl_info(chkEduMemberVO);
					EduMemberVO newEduMemberVO = new EduMemberVO();
					newEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());// 에러구간

					/*
					 * 변경할 필요 없음. if(isMbrModify) {
					 * newEduMemberVO.setMBR_NM(item.getMBR_NM());//이름
					 * newEduMemberVO.setMBR_HP(item.getMBR_HP());//연락처
					 * newEduMemberVO.setMBR_TEL(item.getMBR_TEL());//일반전화
					 * newEduMemberVO.setMBR_BIRTH(item.getMBR_BIRTH());//생년월일
					 * newEduMemberVO.setMBR_ADDR1(item.getMBR_ADDR());//주소
					 * newEduMemberVO.setMBR_ZIPCD(item.getMBR_ZIPCD());//우편번호 }
					 */

					newEduMemberVO.setMBR_REG_TYPE_CD(MBR_REG_TYPE_CD);
					newEduMemberVO.setGOV_MOD_DT("now");
					newEduMemberVO.setUPD_MBR_ID(info.getREG_MBR_ID());
					newEduMemberVO.setMBR_EDU_RSPNBER_TYPE(item.getMBR_EDU_RSPNBER_TYPE());
					newEduMemberVO.setMBR_INDVDL_INFO_DT(chkMemberVO.getMBR_INDVDL_INFO_DT());
					newEduMemberVO.setMBR_SIDO_CD(eduMemberVO.getSIDO_CD());
					newEduMemberVO.setMBR_SIGNGU_CD(eduMemberVO.getSIGNGU_CD());
					eduMemberService.set_edu_member_mod(newEduMemberVO);

					eduMemberVO.setREG_TYPE_CD(MBR_REG_TYPE_CD);
					eduMemberVO.setYMD_NM(chkEduMemberVO.getYMD_NM());
					eduMemberVO.setBUSINESS_NUM(chkEduMemberVO.getBUSINESS_NUM());
					eduMemberVO.setFSHLC_APPLC(chkEduMemberVO.getFSHLC_APPLC());
					eduMemberVO.setFSHLC_WORK_CD(chkEduMemberVO.getFSHLC_WORK_CD());
					eduMemberVO.setSHIP_GRTG(chkEduMemberVO.getSHIP_GRTG());
					eduMemberVO.setSHIP_PRLOAD(chkEduMemberVO.getSHIP_PRLOAD());
					eduMemberVO.setSHIP_MAX_PSNGER(chkEduMemberVO.getSHIP_MAX_PSNGER());
					eduMemberVO.setSHIP_MAX_CREW(chkEduMemberVO.getSHIP_MAX_CREW());
					eduMemberVO.setGOV_MOD_DT("now");
					eduMemberVO.setMBR_ZIPCD(item.getMBR_ZIPCD());
					eduMemberVO.setMBR_EDU_RSPNBER_TYPE(item.getMBR_EDU_RSPNBER_TYPE());
					eduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
					eduMemberVO.setDTL_SN(chkEduMemberVO.getDTL_SN());
					eduMemberVO.setUPD_MBR_ID(info.getREG_MBR_ID());
					eduMemberService.set_edu_member_dtl_mod(eduMemberVO);

					// 사용자사유로그기록
					{
						EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
						logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "수정", "[지자체명단 현행화]",
								realEduMemberVO.getMBR_ID(), realEduMemberVO.getMBR_NM(), chkEduMemberVO, eduMemberVO,
								loginVO, request);
					}

					item.setEUD_CONFM_ST("Y");
				}

				eduMemberService.set_edu_excel_upload_dtl_mod(item);

				// 해당 연도 대상자 추가
				if (isChkEduTrgt) {
					// info.getREG_DT();
					String info_dt = info.getCONFM_REG_DT().substring(0, 19);
					String TRGT_YEAR = EgovDateUtil.convertDate(info_dt, "yyyy-MM-dd HH:mm:ss", "yyyy", "");
					EduMemberVO updEduMemberVO = new EduMemberVO();
					updEduMemberVO.setMBR_ID(MBR_ID);
					updEduMemberVO.setMBR_NM(MBR_NM);
					updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
					List<EduMemberVO> chkTargetEduList = eduMemberService
							.get_edu_member_target_all_list(updEduMemberVO);
					if (chkTargetEduList.size() <= 0) {
						updEduMemberVO.setMBR_CD(MBR_ID);
						updEduMemberVO.setREG_MBR_ID(info.getREG_MBR_ID());
						updEduMemberVO.setUPD_MBR_ID(info.getREG_MBR_ID());
						updEduMemberVO.setREG_TYPE_CD(MBR_REG_TYPE_CD);
						eduMemberService.set_edu_member_target_reg(updEduMemberVO);
					}
				}
			}

			eduMemberService.set_edu_excel_upload_confm(eduExcelUploadVO);// upd_dt,
																			// confm_st
																			// 수정

			data.put("error", 0);
			data.put("msg", "반영되었습니다.");

			String MASTER_MBR_ID = loginVO.getMBR_ID();
			String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
			String MASTER_MBR_GRP_ID = loginVO.getMBR_GRP_ID();
			String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
			log_msg.append("[아이디:" + MASTER_MBR_ID + "]");
			log_msg.append("[처리권한:" + MASTER_MBR_GRP_ID + "]");
			log_msg.append("[처리레벨:" + MASTER_MBR_LV_ID + "]");
			log_msg.append("[처리직급:" + MASTER_MBR_POSITION_CD + "]");

			tbl_inf.append("MBR_ID, MBR_DTL_TB, EDU_EXCEL_UPLOAD_TB, EDU_EXCEL_UPLOAD_DTL_TB");
			tbl_sn.append(eduExcelUploadVO.getEU_SN());

			try {
				/**
				 * LOG RECORED (로그기록)
				 */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduExcelUploadVO));
				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
				mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO, request);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.debug("[fail log record] " + e.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;

	}

	// 관리자(교육센터) 지자체명단 현행화 업로드 리스트 - 삭제
	// ------------------------------------------------
	@RequestMapping(value = "/eduadm/member/loc_gov_delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String eduadm_member_loc_gov_delete_act(
			@ModelAttribute("eduExcelUploadVO") EduExcelUploadVO eduExcelUploadVO, HttpServletRequest request,
			HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			// 검증
			EduExcelUploadVO chkduExcelUploadVO = eduMemberService.get_edu_excel_upload_info(eduExcelUploadVO);
			if (chkduExcelUploadVO.getEU_SN() == null || chkduExcelUploadVO.getEU_SN().length() == 0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {

				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder log_msg = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();

				String MASTER_MBR_ID = loginVO.getMBR_ID();
				String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
				String MASTER_MBR_GRP_ID = loginVO.getMBR_GRP_ID();
				String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
				log_msg.append("[아이디:" + MASTER_MBR_ID + "]");
				log_msg.append("[처리권한:" + MASTER_MBR_GRP_ID + "]");
				log_msg.append("[처리레벨:" + MASTER_MBR_LV_ID + "]");
				log_msg.append("[처리직급:" + MASTER_MBR_POSITION_CD + "]");

				log_dscrp.append("[교육센터관리자-지자체 엑셀업로드 삭제처리]");

				eduMemberService.remove_edu_excel_upload_list(chkduExcelUploadVO);
				eduMemberService.remove_edu_excel_upload_dtl_list(chkduExcelUploadVO);

				data.put("error", "0");
				data.put("msg", chkduExcelUploadVO.getORIGNL_FILE_NM() + " 삭제되었습니다.");

				tbl_inf.append("EDU_EXCEL_UPLOAD_TB, EDU_EXCEL_UPLOAD_DTL_TB");
				tbl_sn.append(chkduExcelUploadVO.getEU_SN());

				try {
					/**
					 * LOG RECORED (로그기록)
					 */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkduExcelUploadVO));
					mEduLogRecordVO.setLOG_MSG(log_msg.toString());
					mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
					mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
					mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
					mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
					mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
					mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
					logRecordService.set_log_data(mEduLogRecordVO, request);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.debug("[fail log record] " + e.toString());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail process] " + e.toString());
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	/**
	 * 업로드한 엑셀파일을 리스트로 만들기
	 * 
	 * @param excelFile
	 * @return 생성한 리스트
	 */
	public List<EduExcelUploadVO> excelUploadFile(MultipartFile excelFile, String eu_sn) throws Exception {

		List<EduExcelUploadVO> list = new ArrayList<EduExcelUploadVO>();

		OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
		XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);

		// 첫번째 시트 불러오기
		XSSFSheet sheet = workbook.getSheetAt(0);

		int firstRow = 1;// 불러 올 첫 행의 번호

		for (int i = firstRow; i < sheet.getPhysicalNumberOfRows(); i++) {

			EduExcelUploadVO eduExcelUploadVO = new EduExcelUploadVO();
			XSSFRow row = sheet.getRow(i + 1);

			// 행이 존재하지 않으면 패스
			if (row == null)
				continue;

			// 행의 두번째 셀값(시도)이 빈값일 경우 건너뜀
			if (row.getCell(2).toString().length() == 0)
				continue;

			XSSFCell cell = null;
			String getStringValue = "";

			for (int j = 1; j < 16; j++) {
				cell = row.getCell(j);// 행의 2번째 열부터 가져오기

				// cell type check
				if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
					if (DateUtil.isCellDateFormatted(cell)) {
						Date tempDate = cell.getDateCellValue();
						getStringValue = new SimpleDateFormat("yyyy-MM-dd").format(tempDate);
					} else {
						getStringValue = Integer.toString((int) cell.getNumericCellValue()).trim();
					}
				} else {
					getStringValue = cell.getStringCellValue().trim();
				}

				if (cell != null) {
					switch (j) {
					case 1:
						if (getStringValue.contains("낚시터")) {
							getStringValue = "CIDN010200";
						} else if (getStringValue.contains("낚시어선")) {
							getStringValue = "CIDN010300";
						}
						eduExcelUploadVO.setDTL_CD(getStringValue);
						break;
					case 2:
						eduExcelUploadVO.setSIDO_NM(getStringValue);
						break;
					case 3:
						eduExcelUploadVO.setSIGNGU_NM(getStringValue);
						break;
					case 4:
						eduExcelUploadVO.setREG_NUM_CD(getStringValue);
						break;
					case 5:
						eduExcelUploadVO.setSHIP_CD(getStringValue);
						break;
					case 6:
						eduExcelUploadVO.setDTL_NM(getStringValue);
						break;
					case 7:
						eduExcelUploadVO.setDTL_LICENSE_NM(getStringValue);
						break;
					case 8:
						if (getStringValue.equals("대표자")) {
							getStringValue = "CEO";
						} else if (getStringValue.equals("교육책임자")) {
							getStringValue = "EDU_RSPNBER";
						}
						eduExcelUploadVO.setMBR_EDU_RSPNBER_TYPE(getStringValue);
						break;
					case 9:
						eduExcelUploadVO.setSHIP_LICENSE(getStringValue);
						break;
					case 10:
						eduExcelUploadVO.setMBR_NM(getStringValue);
						break;
					case 11:
						eduExcelUploadVO.setMBR_BIRTH(getStringValue);
						break;
					case 12: {
						int cnt = StringUtils.countMatches(getStringValue, "-");
						if (cnt > 3) {
							String[] parseStr = getStringValue.split("(\r|\n|\r\n|\n\r)");
							eduExcelUploadVO.setMBR_HP(parseStr[0]);
						} else {
							eduExcelUploadVO.setMBR_HP(getStringValue);
						}
					}
						break;
					case 13:
						eduExcelUploadVO.setMBR_ZIPCD(getStringValue);
						break;
					case 14:
						eduExcelUploadVO.setMBR_ADDR(getStringValue);
						break;
					// case 14:
					// eduExcelUploadVO.setMBR_ADDR2(cell.getStringCellValue());
					// break;
					case 15:
						eduExcelUploadVO.setMBR_TEL(getStringValue);
						break;
					default:
						break;
					}
				}
			}
			list.add(eduExcelUploadVO);
			eduExcelUploadVO.setEU_SN(eu_sn);
			eduMemberService.set_edu_excel_upload_dtl_reg(eduExcelUploadVO);

		}
		workbook.close();

		return list;
	}

}
