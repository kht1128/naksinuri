package egovframework.educenter.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.member.service.AdmMemberService;
import egovframework.adm.member.service.AdmMemberVO;
import egovframework.adm.popup.service.PopupService;
import egovframework.adm.popup.service.PopupVO;
import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsMentVO;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.AuthipinsmsVO;
import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.all.main.service.KakaoAlimTalkService;
import egovframework.all.main.service.KakaoAlimTalkVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.main.service.EduMbrRemindersVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.educenter.board.service.EduCenterBoardService;
import egovframework.educenter.board.service.EduCenterBoardVO;
import egovframework.educenter.member.service.EduCenterMemberService;
import egovframework.educenter.member.service.EduCenterMemberVO;
import egovframework.educenter.myhistory.service.MyHistoryService;
import egovframework.educenter.myhistory.service.MyHistoryVO;
import egovframework.educenter.service.EduCenterMainService;
import egovframework.educenter.service.EduCenterMainVO;
import egovframework.educenter.service.EduCenterMbrRemindersVO;
import egovframework.educenter.service.EduCprBplcVO;
import egovframework.educenter.service.EduSmsRequstVO;
import egovframework.educenter.service.MbrHpChngVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.seadm.analytics.service.AnalyticsAdmService;
import egovframework.seadm.analytics.service.AnalyticsAdmVO;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduCenterMainController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterMainController.class);

	/** LogRecordService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	@Resource(name = "eduCenterMemberService")
	private EduCenterMemberService eduCenterMemberService;

	/** EduCenterMainService */
	@Resource(name = "eduCenterMainService")
	private EduCenterMainService eduCenterMainService;

	/** MyHistoryService */
	@Resource(name = "myHistoryService")
	private MyHistoryService myHistoryService;

	/** EgovEducenterService */
	@Resource(name = "eduCenterService")
	private EduCenterService eduCenterService;

	/** BoardService */
	@Resource(name = "eduCenterBoardService")
	private EduCenterBoardService eduCenterBoardService;

	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;

	@Resource(name = "AnalyticsAdmService")
	private AnalyticsAdmService analyticsAdmService;

	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "eduCertificateService")
	private EduCertificateService eduCertificateService;

	@Resource(name = "smsManagerService")
	private SmsManagerService smsManagerService;

	@Resource(name = "admMemberService")
	private AdmMemberService admMemberService;

	@Resource(name = "popupService")
	private PopupService popupService;

	/** EduMyHistoryService */
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;

	/** EduCurriculumService */
	@Resource(name = "eduCurriculumService")
	private EduCurriculumService eduCurriculumService;

	@Resource(name = "kakaoAlimTalkService")
	private KakaoAlimTalkService kakaoAlimTalkService;

	@Resource(name = "eduTrainingDataService")
	private EduTrainingDataService eduTrainingDataService;

	@Resource(name = "loginService")
	private LoginService loginService;

	// 교육센터-메인화면 ------------------------------------------------
	@RequestMapping(value = "/educenter/index.do")
	public String mainIndex(@ModelAttribute("authipinsmsVO") AuthipinsmsVO authipinsmsVO, HttpServletRequest request,
			ModelMap model) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();

		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_cd", list_mbr_cd);
		}

		// 공지사항
		EduCenterBoardVO eduCenterBoardVO = new EduCenterBoardVO();
		eduCenterBoardVO.setFirstIndex(0);
		eduCenterBoardVO.setRecordCountPerPage(6);
		eduCenterBoardVO.setBD_ID("board013");// 교육센터 공지사항
		List<EduCenterBoardVO> list_notice = eduCenterBoardService.userBoardList(eduCenterBoardVO);
		model.addAttribute("list_notice", list_notice);
		//

		// 중앙배너
		PopupVO popupVO = new PopupVO();
		popupVO.setPP_RANK("1");
		popupVO.setPP_HIDE_ST("N");
		popupVO.setChk_show_allow_poup(true);
		popupVO.setNotUsedPagination(true);
		popupVO.setPP_TYPE("banner_center");
		popupVO.setPP_EDU("Y");
		List<PopupVO> list_banner_center = popupService.get_seadm_popup_list(popupVO);
		model.addAttribute("list_banner_center", list_banner_center);
		// End of 중앙배너

		// 모집기간 최신 1건 조회
		EduCenterMainVO curriculum_upcoming_info = eduCenterMainService.get_educenter_main_curriculum_upcoming_info();
		model.addAttribute("curriculum_upcoming_info", curriculum_upcoming_info);
		// 메인화면 교육정보 탭
		EduCenterMainVO sqlCurriculumListVO = new EduCenterMainVO();
		sqlCurriculumListVO.setNotUsedPagination(true);
		sqlCurriculumListVO.setCRS_STR_DT(mPublicUtils.currentTime("yyyy-01-01"));
		sqlCurriculumListVO.setCRS_END_DT(mPublicUtils.currentTime("yyyy-12-31"));
		sqlCurriculumListVO.setCRS_TYPE("default");
		List<EduCenterMainVO> curriculum_1_list = eduCenterMainService.get_educenter_main_curriculum_list(sqlCurriculumListVO);
		sqlCurriculumListVO.setCRS_TYPE("wknd_trnng");
		List<EduCenterMainVO> curriculum_2_list = eduCenterMainService.get_educenter_main_curriculum_list(sqlCurriculumListVO);
		sqlCurriculumListVO.setCRS_TYPE("fshsk_trnng");
		List<EduCenterMainVO> curriculum_3_list = eduCenterMainService.get_educenter_main_curriculum_list(sqlCurriculumListVO);
		model.addAttribute("curriculum_1_list", curriculum_1_list);
		model.addAttribute("curriculum_2_list", curriculum_2_list);
		model.addAttribute("curriculum_3_list", curriculum_3_list);
		//

		// 본인인증관련
		authipinsmsVO.setIdtfycustomerid(propertiesService.getString("Idtfy.customerId"));
		authipinsmsVO.setIdtfyserviceno(propertiesService.getString("Idtfy.idNo"));
		authipinsmsVO.setIpincustomerid(propertiesService.getString("Ipin.customerId"));
		authipinsmsVO.setIpinserviceno(propertiesService.getString("Ipin.idNo"));
		model.addAttribute("authipinsms", authipinsmsVO);
		model.addAttribute("kcbokcert_cpid", propertiesService.getString("KcbOkCert.cpid"));
		model.addAttribute("kcbokcert_licensepath", propertiesService.getString("KcbOkCert.licensePath"));
		model.addAttribute("kcbokcert_sitenm", propertiesService.getString("KcbOkCert.siteNm"));
		model.addAttribute("kcbokcert_siteurl", propertiesService.getString("KcbOkCert.siteUrl"));
		model.addAttribute("isMobile", mPublicUtils.isMobileDevice(request));
		
		//

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("메인페이지(교육센터)");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------
		/*
		 * Map<String, Object> postMap = new HashMap<String,Object>();
		 * postMap.put("type", "modal-lock");
		 * 
		 * postMap.put("message",
		 * "로그인이 가능한 정보가 없어 수집이 필요합니다.<br>아래 항목을 선택해주세요.<br><br>" +
		 * "<a href=\"/educenter/rmndr/agreeShip.do\" class=\"display-inline-block mt-10\"><img src=\"/common/img/educenter/education_banner_img1.png\"/></a>"
		 * +
		 * "<br><a href=\"/educenter/rmndr/agreeHouse.do\" class=\"display-inline-block mt-20\"><img src=\"/common/img/educenter/education_banner_img2.png\"/></a>"
		 * ); model.addAttribute("alert_data",postMap);
		 */
		return "educenter/index";
	}

	// 교육센터-메인화면(모바일) ------------------------------------------------
	@RequestMapping(value = "/educenter/m/index.do")
	public String mainIndex_mobile(@ModelAttribute("authipinsmsVO") AuthipinsmsVO authipinsmsVO,
			HttpServletRequest request, ModelMap model) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();

		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_cd", list_mbr_cd);
		}

		// 공지사항
		EduCenterBoardVO eduCenterBoardVO = new EduCenterBoardVO();
		eduCenterBoardVO.setFirstIndex(0);
		eduCenterBoardVO.setRecordCountPerPage(6);
		eduCenterBoardVO.setBD_ID("board013");// 교육센터 공지사항
		List<EduCenterBoardVO> list_notice = eduCenterBoardService.userBoardList(eduCenterBoardVO);
		model.addAttribute("list_notice", list_notice);
		//

		// 중앙배너
		PopupVO popupVO = new PopupVO();
		popupVO.setPP_RANK("1");
		popupVO.setPP_HIDE_ST("N");
		popupVO.setChk_show_allow_poup(true);
		popupVO.setNotUsedPagination(true);
		popupVO.setPP_TYPE("banner_center");
		popupVO.setPP_EDU("Y");
		List<PopupVO> list_banner_center = popupService.get_seadm_popup_list(popupVO);
		model.addAttribute("list_banner_center", list_banner_center);
		// End of 중앙배너

		// 모집기간 최신 1건 조회
		EduCenterMainVO curriculum_upcoming_info = eduCenterMainService.get_educenter_main_curriculum_upcoming_info();
		model.addAttribute("curriculum_upcoming_info", curriculum_upcoming_info);
		// 메인화면 교육정보 탭
		EduCenterMainVO sqlCurriculumListVO = new EduCenterMainVO();
		sqlCurriculumListVO.setNotUsedPagination(true);
		sqlCurriculumListVO.setCRS_STR_DT(mPublicUtils.currentTime("yyyy-01-01"));
		sqlCurriculumListVO.setCRS_END_DT(mPublicUtils.currentTime("yyyy-12-31"));
		sqlCurriculumListVO.setCRS_TYPE("default");
		List<EduCenterMainVO> curriculum_1_list = eduCenterMainService.get_educenter_main_curriculum_list(sqlCurriculumListVO);
		sqlCurriculumListVO.setCRS_TYPE("wknd_trnng");
		List<EduCenterMainVO> curriculum_2_list = eduCenterMainService.get_educenter_main_curriculum_list(sqlCurriculumListVO);
		sqlCurriculumListVO.setCRS_TYPE("fshsk_trnng");
		List<EduCenterMainVO> curriculum_3_list = eduCenterMainService.get_educenter_main_curriculum_list(sqlCurriculumListVO);
		model.addAttribute("curriculum_1_list", curriculum_1_list);
		model.addAttribute("curriculum_2_list", curriculum_2_list);
		model.addAttribute("curriculum_3_list", curriculum_3_list);

		// 본인인증관련
		authipinsmsVO.setIdtfycustomerid(propertiesService.getString("Idtfy.customerId"));
		authipinsmsVO.setIdtfyserviceno(propertiesService.getString("Idtfy.idNo"));
		authipinsmsVO.setIpincustomerid(propertiesService.getString("Ipin.customerId"));
		authipinsmsVO.setIpinserviceno(propertiesService.getString("Ipin.idNo"));
		model.addAttribute("authipinsms", authipinsmsVO);
		model.addAttribute("kcbokcert_cpid", propertiesService.getString("KcbOkCert.cpid"));
		model.addAttribute("kcbokcert_licensepath", propertiesService.getString("KcbOkCert.licensePath"));
		model.addAttribute("kcbokcert_sitenm", propertiesService.getString("KcbOkCert.siteNm"));
		model.addAttribute("kcbokcert_siteurl", propertiesService.getString("KcbOkCert.siteUrl"));
		//

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("메인페이지(교육센터)");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/index";
	}

	// 교육센터-종합교육-교육기관 목록 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/ins.do")
	public String educenter_trnng_ins(HttpServletRequest request, ModelMap model) throws Exception {

		EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
		eduCategoryInsInfVO.setNotUsedPagination(true);
		eduCategoryInsInfVO.setUSE_ST("1");
		List<EduCategoryInsInfVO> list = eduCenterMainService.get_educenter_category_ins_inf_list(eduCategoryInsInfVO);
		model.addAttribute("list", list);

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육기관");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/trnng/ins";
	}

	// 교육센터-종합교육-교육기관 목록 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/ins.do")
	public String educenter_trnng_ins_mobile(HttpServletRequest request, ModelMap model) throws Exception {

		EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
		eduCategoryInsInfVO.setNotUsedPagination(true);
		eduCategoryInsInfVO.setUSE_ST("1");
		List<EduCategoryInsInfVO> list = eduCenterMainService.get_educenter_category_ins_inf_list(eduCategoryInsInfVO);
		model.addAttribute("list", list);

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육기관");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/trnng/ins";
	}

	// 교육센터-온라인교육-교육신청 안내 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/info_default_online.do")
	public String educenter_trnng_info_default_online(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-온라인교육");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/trnng/info_default_online";
	}

	// 교육센터-종합교육-교육신청 안내 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/info_default_online.do")
	public String educenter_trnng_info_default_online_mobile(HttpServletRequest request, ModelMap model)
			throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-온라인교육");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/trnng/info_default_online";
	}

	// 교육센터-종합교육-교육신청 안내 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/info_default.do")
	public String educenter_trnng_info_default(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-종합교육");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/trnng/info_default";
	}

	// 교육센터-종합교육-교육신청 안내 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/info_default.do")
	public String educenter_trnng_info_default_mobile(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-종합교육");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/trnng/info_default";
	}

	// 교육센터-교육과정-교육신청 목록 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/list.do")
	public String educenter_trnng_list(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
			HttpServletRequest request, ModelMap model) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();
		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}
		
		String yearString = "";
		if(eduCenterMainVO.getYearString()!=null) {
			eduCenterMainVO.setCRS_YEAR(eduCenterMainVO.getYearString());
		} else {
			yearString = mPublicUtils.currentTime("YYYY");
			eduCenterMainVO.setCRS_YEAR(yearString);
		}
		
		eduCenterMainVO.setTYPE_GB("online");
		eduCenterMainVO.setNotUsedPagination(true);
		model.addAttribute("info", eduCenterMainVO);
		
		List<EduCenterMainVO> list = eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterMainVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterMainVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterMainVO.getPageSize());

		eduCenterMainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterMainVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterMainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		int totCnt = eduCenterMainService.get_educenter_main_curriculum_list_cnt(eduCenterMainVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		for(int i = 0 ; i < list.size() ; i++){
			PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"
					);
			PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss" 
					);
			
			if(list.get(i).getLOCK_ST().equals("1")){
				list.get(i).setEventClassName("lock");
			} else if(list.get(i).getLOCK_ST().equals("0")
					&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
					&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02) ){
				if(Integer.parseInt(list.get(i).getMBR_MAX_CNT())!=0 && Integer.parseInt(list.get(i).getMBR_CUR_CNT())+1 > Integer.parseInt(list.get(i).getMBR_MAX_CNT())) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("unlock");
				}
			} else {
				if(dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("last");
				}
			}
		}		
		
		
		model.addAttribute("list", list);

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육신청");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/trnng/list";
	}

	// 교육센터-교육과정-교육신청 목록 -- 모바일------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/list.do")
	public String educenter_trnng_list_mobile(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
			HttpServletRequest request, ModelMap model) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();
		
		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}
		
		String yearString = "";
		if(eduCenterMainVO.getYearString()!=null) {
			eduCenterMainVO.setCRS_YEAR(eduCenterMainVO.getYearString());
		} else {
			yearString = mPublicUtils.currentTime("YYYY");
			eduCenterMainVO.setCRS_YEAR(yearString);
		}

		eduCenterMainVO.setTYPE_GB("online");
		eduCenterMainVO.setNotUsedPagination(true);
		model.addAttribute("info", eduCenterMainVO);
		
		List<EduCenterMainVO> list = eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterMainVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterMainVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterMainVO.getPageSize());

		eduCenterMainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterMainVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterMainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	
		
		int totCnt = eduCenterMainService.get_educenter_main_curriculum_list_cnt(eduCenterMainVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		model.addAttribute("list", list);		
		
		for(int i = 0 ; i < list.size() ; i++){
			PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"
					);
			PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss" 
					);
			
			if(list.get(i).getLOCK_ST().equals("1")){
				list.get(i).setEventClassName("lock");
			} else if(list.get(i).getLOCK_ST().equals("0")
					&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
					&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02) ){
				if(Integer.parseInt(list.get(i).getMBR_MAX_CNT())!=0 && Integer.parseInt(list.get(i).getMBR_CUR_CNT())+1 > Integer.parseInt(list.get(i).getMBR_MAX_CNT())) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("unlock");
				}
			} else {
				if(dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("last");
				}
			}
		}

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육신청");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/trnng/list";
	}

	// 교육센터-교육과정-오프라인교육(낚시터)신청 목록
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/trnngoffline/fshlcList.do")
	public String educenter_trnng_offline_fshlc_list(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		model.addAttribute("search_word", eduCenterMainVO.getSearch_word());

		// 교육분류 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}

		PublicUtils mPublicUtils = new PublicUtils();
		String currentDate = mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss");
		String strDate = mPublicUtils.currentTime("yyyy");
		model.addAttribute("currentDate", currentDate);

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterMainVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterMainVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterMainVO.getPageSize());

		eduCenterMainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterMainVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterMainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		eduCenterMainVO.setCRS_STR_DT(currentDate);
		eduCenterMainVO.setRECRUIT_STR_DT(strDate);
		eduCenterMainVO.setCRS_MBR_CD("CIDN010200");

		List<EduCenterMainVO> list = eduCenterMainService.get_edu_list(eduCenterMainVO);
		int totCnt = eduCenterMainService.get_edu_list_totcnt(eduCenterMainVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		for (int i = 0; i < list.size(); i++) {
			PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
			PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");

			if (list.get(i).getLOCK_ST().equals("1")) {
				list.get(i).setEventClassName("lock");
			} else if (list.get(i).getLOCK_ST().equals("0")
					&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
					&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
				if (Integer.parseInt(list.get(i).getMBR_MAX_CNT()) != 0
						&& Integer.parseInt(list.get(i).getMBR_CUR_CNT()) + 1 > Integer
								.parseInt(list.get(i).getMBR_MAX_CNT())) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("unlock");
				}
			} else {
				if (dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("last");
				}
			}
		}
		model.addAttribute("list", list);

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육신청(오프라인)");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/trnngoffline/fshlcList";
	}

	// 교육센터-교육과정-오프라인교육(낚시터)신청 목록
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnngoffline/fshlcList.do")
	public String educenter_trnng_offline_fshlc_list_mobile(
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, HttpServletRequest request,
			ModelMap model) throws Exception {
		model.addAttribute("search_word", eduCenterMainVO.getSearch_word());

		// 교육분류 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}

		PublicUtils mPublicUtils = new PublicUtils();
		String currentDate = mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss");
		String strDate = mPublicUtils.currentTime("yyyy");
		model.addAttribute("currentDate", currentDate);

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterMainVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterMainVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterMainVO.getPageSize());

		eduCenterMainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterMainVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterMainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		eduCenterMainVO.setCRS_STR_DT(currentDate);
		eduCenterMainVO.setRECRUIT_STR_DT(strDate);
		eduCenterMainVO.setCRS_MBR_CD("CIDN010200");

		List<EduCenterMainVO> list = eduCenterMainService.get_edu_list(eduCenterMainVO);
		int totCnt = eduCenterMainService.get_edu_list_totcnt(eduCenterMainVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		for (int i = 0; i < list.size(); i++) {
			PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
			PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");

			if (list.get(i).getLOCK_ST().equals("1")) {
				list.get(i).setEventClassName("lock");
			} else if (list.get(i).getLOCK_ST().equals("0")
					&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
					&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
				if (Integer.parseInt(list.get(i).getMBR_MAX_CNT()) != 0
						&& Integer.parseInt(list.get(i).getMBR_CUR_CNT()) + 1 > Integer
								.parseInt(list.get(i).getMBR_MAX_CNT())) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("unlock");
				}
			} else {
				if (dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("last");
				}
			}
		}
		model.addAttribute("list", list);

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육신청(오프라인)");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/trnngoffline/fshlcList";
	}

	// 교육센터-교육과정-오프라인교육(낚시어)신청 목록
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/trnngoffline/fshhpList.do")
	public String educenter_trnng_offline_fshhp_list(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		model.addAttribute("search_word", eduCenterMainVO.getSearch_word());

		// 교육분류 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}

		PublicUtils mPublicUtils = new PublicUtils();
		String currentDate = mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss");
		String strDate = mPublicUtils.currentTime("yyyy");
		model.addAttribute("currentDate", currentDate);

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterMainVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterMainVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterMainVO.getPageSize());

		eduCenterMainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterMainVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterMainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		eduCenterMainVO.setCRS_STR_DT(currentDate);
		eduCenterMainVO.setRECRUIT_STR_DT(strDate);
		eduCenterMainVO.setCRS_MBR_CD("CIDN010300");
		eduCenterMainVO.setCRS_LAW_TYPE("CIDLAW002");

		List<EduCenterMainVO> list = eduCenterMainService.get_edu_list(eduCenterMainVO);
		int totCnt = eduCenterMainService.get_edu_list_totcnt(eduCenterMainVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		for (int i = 0; i < list.size(); i++) {
			PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
			PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");

			if (list.get(i).getLOCK_ST().equals("1")) {
				list.get(i).setEventClassName("lock");
			} else if (list.get(i).getLOCK_ST().equals("0")
					&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
					&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
				if (Integer.parseInt(list.get(i).getMBR_MAX_CNT()) != 0
						&& Integer.parseInt(list.get(i).getMBR_CUR_CNT()) + 1 > Integer
								.parseInt(list.get(i).getMBR_MAX_CNT())) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("unlock");
				}
			} else {
				if (dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("last");
				}
			}
		}
		model.addAttribute("list", list);

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육신청(오프라인)");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/trnngoffline/fshhpList";
	}
	
	
	// 교육센터-교육과정-오프라인교육(낚시어)신청 목록 김현태 추가
		// ------------------------------------------------
		@RequestMapping(value = "/educenter/trnngoffline/fshhpList2.do")
		public String educenter_trnng_offline_fshhp_list2(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
				HttpServletRequest request, ModelMap model) throws Exception {
			model.addAttribute("search_word", eduCenterMainVO.getSearch_word());

			// 교육분류 코드 조회(활성화)
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00005");
				mCodeSetVO.setHIDE_AT("N");
				List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
				model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
			}
			// 대상구분 코드 조회
			{
				CodeSetVO mCodeSetVO = new CodeSetVO();
				mCodeSetVO.setCD_MASTER_ID("CID00002");
				mCodeSetVO.setHIDE_AT("N");
				List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
				model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
			}

			PublicUtils mPublicUtils = new PublicUtils();
			String currentDate = mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss");
			String strDate = mPublicUtils.currentTime("yyyy");
			model.addAttribute("currentDate", currentDate);

			/** pageing setting */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(eduCenterMainVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(eduCenterMainVO.getPageUnit());
			paginationInfo.setPageSize(eduCenterMainVO.getPageSize());

			eduCenterMainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			eduCenterMainVO.setLastIndex(paginationInfo.getLastRecordIndex());
			eduCenterMainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			eduCenterMainVO.setCRS_STR_DT(currentDate);
			eduCenterMainVO.setRECRUIT_STR_DT(strDate);
			eduCenterMainVO.setCRS_MBR_CD("CIDN010300");
			eduCenterMainVO.setCRS_LAW_TYPE("default");

			List<EduCenterMainVO> list = eduCenterMainService.get_edu_list(eduCenterMainVO);
			int totCnt = eduCenterMainService.get_edu_list_totcnt(eduCenterMainVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);

			for (int i = 0; i < list.size(); i++) {
				PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
						mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
						mPublicUtils.changePatternString(list.get(i).getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
				PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
						mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
						mPublicUtils.changePatternString(list.get(i).getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");

				if (list.get(i).getLOCK_ST().equals("1")) {
					list.get(i).setEventClassName("lock");
				} else if (list.get(i).getLOCK_ST().equals("0")
						&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
						&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
					if (Integer.parseInt(list.get(i).getMBR_MAX_CNT()) != 0
							&& Integer.parseInt(list.get(i).getMBR_CUR_CNT()) + 1 > Integer
									.parseInt(list.get(i).getMBR_MAX_CNT())) {
						list.get(i).setEventClassName("lock");
					} else {
						list.get(i).setEventClassName("unlock");
					}
				} else {
					if (dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
						list.get(i).setEventClassName("lock");
					} else {
						list.get(i).setEventClassName("last");
					}
				}
			}
			model.addAttribute("list", list);

			// -----------------------------------------------------------
			// 접속 통계 반영
			// -----------------------------------------------------------
			try {
				LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
				AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
				analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육신청(오프라인)");
				if (loginVO != null) {
					analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
				}
				analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
				analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
				analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
				analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
				analyticsAdmService.insertAnalytics(analyticsAdmVO);
			} catch (Exception e) {
				LOGGER.debug("[fail analytics record] " + e.toString());
			}
			// -----------------------------------------------------------

			return "educenter/trnngoffline/fshhpList2";
		}

	// 교육센터-교육과정-오프라인교육(낚시어)신청 목록
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnngoffline/fshhpList.do")
	public String educenter_trnng_offline_fshhp_list_mobile(
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, HttpServletRequest request,
			ModelMap model) throws Exception {
		model.addAttribute("search_word", eduCenterMainVO.getSearch_word());

		// 교육분류 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_trgt_cd", list_mbr_cd);
		}

		PublicUtils mPublicUtils = new PublicUtils();
		String currentDate = mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss");
		String strDate = mPublicUtils.currentTime("yyyy");
		model.addAttribute("currentDate", currentDate);

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterMainVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterMainVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterMainVO.getPageSize());

		eduCenterMainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterMainVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterMainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		eduCenterMainVO.setCRS_STR_DT(currentDate);
		eduCenterMainVO.setRECRUIT_STR_DT(strDate);
		eduCenterMainVO.setCRS_MBR_CD("CIDN010300");

		List<EduCenterMainVO> list = eduCenterMainService.get_edu_list(eduCenterMainVO);
		int totCnt = eduCenterMainService.get_edu_list_totcnt(eduCenterMainVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		for (int i = 0; i < list.size(); i++) {
			PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
			PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(list.get(i).getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");

			if (list.get(i).getLOCK_ST().equals("1")) {
				list.get(i).setEventClassName("lock");
			} else if (list.get(i).getLOCK_ST().equals("0")
					&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
					&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
				if (Integer.parseInt(list.get(i).getMBR_MAX_CNT()) != 0
						&& Integer.parseInt(list.get(i).getMBR_CUR_CNT()) + 1 > Integer
								.parseInt(list.get(i).getMBR_MAX_CNT())) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("unlock");
				}
			} else {
				if (dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
					list.get(i).setEventClassName("lock");
				} else {
					list.get(i).setEventClassName("last");
				}
			}
		}
		model.addAttribute("list", list);

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-교육신청(오프라인)");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/trnngoffline/fshhpList";
	}

	// 교육센터-종합교육-교육신청 목록 (위로 통합)
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/list_default.do")
	public String educenter_trnng_list_default(HttpServletRequest request, ModelMap model) throws Exception {

		return "educenter/trnng/list_default";
	}

	// 교육센터-주말교육-교육신청 안내 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/info_wknd.do")
	public String educenter_trnng_info_wknd(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-주말교육");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/trnng/info_wknd";
	}

	// 교육센터-주말교육-교육신청 안내 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/info_wknd.do")
	public String educenter_trnng_info_wknd_mobile(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-주말교육");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/trnng/info_wknd";
	}

	// 교육센터-주말교육-교육신청 목록 (위로 통합)
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/list_wknd.do")
	public String educenter_trnng_list_wknd(HttpServletRequest request, ModelMap model) throws Exception {

		return "educenter/trnng/list_wknd";
	}

	// 교육센터-귀어창업기술교육-교육신청 안내 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/info_fshsk.do")
	public String educenter_trnng_info_fshsk(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-어업창업기술교육");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/trnng/info_fshsk";
	}

	// 교육센터-귀어창업기술교육-교육신청 안내 -- 모바일
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/info_fshsk.do")
	public String educenter_trnng_info_fshsk_mobile(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-어업창업기술교육");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/trnng/info_fshsk";
	}

	// 교육센터-귀어창업기술교육-교육신청 목록 (위로 통합)
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/list_fshsk.do")
	public String educenter_trnng_list_fshsk(HttpServletRequest request, ModelMap model) throws Exception {

		return "educenter/trnng/list_fshsk";
	}

	// 교육센터-교육정보 뷰페이지 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/view.do", method = RequestMethod.POST)
	public ModelAndView educenter_trnng_videoplayer(
			@RequestParam(value = "IS_TABLE_TR", required = false) String IS_TABLE_TR,
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, HttpServletRequest request,
			ModelMap model) throws Exception {

		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("educenter/popup/view_trnng");
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		mModelAndView.addObject("info", eduCenterMainVO);
		mModelAndView.addObject("IS_TABLE_TR", IS_TABLE_TR);
		mModelAndView.addObject("TYPE_BG", eduCenterMainVO.getTYPE_GB());

		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_mbr_cd", list_mbr_cd);
		}
		// 신청지역제한(낚시어선) 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00008");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_lock_area_ship_grp_cd", list_lock_area_grp_cd);
		}
		// 신청지역제한(낚시터) 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00009");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_lock_area_house_grp_cd", list_lock_area_grp_cd);
		}

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-상세정보 페이지");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return mModelAndView;
	}

	// 교육센터-교육정보 뷰페이지 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/view.do", method = RequestMethod.POST)
	public ModelAndView educenter_trnng_videoplayer_mobile(
			@RequestParam(value = "IS_TABLE_TR", required = false) String IS_TABLE_TR,
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, HttpServletRequest request,
			ModelMap model) throws Exception {

		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("educenter/mobile/popup/view_trnng");
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		mModelAndView.addObject("info", eduCenterMainVO);
		mModelAndView.addObject("IS_TABLE_TR", IS_TABLE_TR);

		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_mbr_cd", list_mbr_cd);
		}
		// 신청지역제한(낚시어선) 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00008");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_lock_area_ship_grp_cd", list_lock_area_grp_cd);
		}
		// 신청지역제한(낚시터) 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00009");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			mModelAndView.addObject("list_lock_area_house_grp_cd", list_lock_area_grp_cd);
		}

		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육정보-상세정보 페이지");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return mModelAndView;
	}

	// 교육센터-교육신청-동의하기 페이지 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/agree.do")
	public String educenter_trnng_agree(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || loginVO.getMBR_ID() == null) {
			// model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/error/login";
		}
		String referer = request.getHeader("Referer");
		if (referer == null) {
			referer = "/educenter/index.do";
		}
		if (eduCenterMainVO.getCRS_SN() == null || eduCenterMainVO.getCRS_SN().length() == 0) {
			// 비정상적인 접근
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			LOGGER.debug("비 정상적인 접근");
			return "redirect:/educenter/index.do";
		}

		// 교육과정 정보
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		model.addAttribute("info", eduCenterMainVO);

		if (eduCenterMainVO == null || eduCenterMainVO.getCRS_SN() == null
				|| eduCenterMainVO.getCRS_SN().length() == 0) {
			// 비정상적인 접근
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "올바른 교육정보가 아닙니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			LOGGER.debug("교육 임의 접근");
			return "redirect:/educenter/index.do";
		}

		// 모집기간 확인
		PublicUtils mmPublicUtils = new PublicUtils();
		PublicUtils.RETURN_COMPARE_TYPE dateStr = mmPublicUtils.dateCompare(
				mmPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
				mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
		PublicUtils.RETURN_COMPARE_TYPE dateEnd = mmPublicUtils.dateCompare(
				mmPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
				mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
		if (eduCenterMainVO.getLOCK_ST().equals("0") // 모집잠금해제상태 인 경우
				// &&
				// EgovDateUtil.getDaysDiff(mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_STR_DT(),
				// "yyyy-MM-dd HH:mm:ss.S", "yyyyMMdd"),EgovDateUtil.getToday())
				// >= 0 //모집시작일자
				// &&
				// EgovDateUtil.getDaysDiff(mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_END_DT(),
				// "yyyy-MM-dd HH:mm:ss.S", "yyyyMMdd"),EgovDateUtil.getToday())
				// <= 0 //모집종료일자
				&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
				&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
			if (Integer.parseInt(eduCenterMainVO.getMBR_MAX_CNT()) != 0 && Integer.parseInt(eduCenterMainVO.getMBR_CUR_CNT()) + 1 > Integer.parseInt(eduCenterMainVO.getMBR_MAX_CNT())) {
				// 신청받지 않음
				Map<String, Object> postMap = new HashMap<String, Object>();
				postMap.put("message", "해당 교육은 현재 모집마감 되었습니다.");
				redirectAttributes.addFlashAttribute("alert_data", postMap);
				LOGGER.debug("신청인원 마감!");
				return "redirect:/educenter/index.do";
			} else {
				LOGGER.debug("정상적인 교육신청 기간!");
			}
		} else {
			// 신청받지 않음
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "해당 교육은 현재 모집기간이 아닙니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			LOGGER.debug("신청받지 않는 상태");
			return "redirect:/educenter/index.do";
		}
		/*
		 * 기존에 락만 확인 if(eduCenterMainVO.getLOCK_ST().equals("1")) { //신청받지 않음
		 * Map<String, Object> postMap = new HashMap<String,Object>();
		 * postMap.put("message", "해당 교육과정은 신청접수가 중단되었습니다.");
		 * redirectAttributes.addFlashAttribute("alert_data",postMap);
		 * LOGGER.debug("신청받지 않는 상태"); return "redirect:/educenter/index.do"; }
		 */

		// //수강내역(메인) 정보
		// MyHistoryVO myHistoryVO = new MyHistoryVO();
		//// myHistoryVO.setCRS_SN(eduCenterMainVO.getCRS_SN());
		// myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		// myHistoryVO.setCRS_STR_DT(mmPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(),
		// "yyyy-MM-dd HH:mm:ss.S", "yyyy"));

		// List<MyHistoryVO> list_get_educenter_mbrhstry_info =
		// myHistoryService.get_educenter_mbrhstry_info_list(myHistoryVO);
		// myHistoryVO.setLRNNG_ST("1");
		// myHistoryVO.setLRNNG_CMPLT_ST("1");
		// myHistoryVO.setCRS_MBR_CD(eduCenterMainVO.getCRS_MBR_CD());
		// List<MyHistoryVO> list_get_educenter_mbrhstry_info2 =
		// myHistoryService.get_educenter_mbrhstry_info_list(myHistoryVO);
		// if(list_get_educenter_mbrhstry_info.size() > 0){
		// // 신청 교육 존재할 경우
		// MyHistoryVO myHistoryVO2 = new MyHistoryVO();
		// myHistoryVO2.setMBR_ID(loginVO.getMBR_ID());
		// myHistoryVO2.setCRS_STR_DT(mmPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(),
		// "yyyy-MM-dd HH:mm:ss.S", "yyyy"));
		// if(list_get_educenter_mbrhstry_info2.size() != 0){
		// // 이수완료 존재
		// String cmpltList="";
		// for(int i = 0 ; i < list_get_educenter_mbrhstry_info2.size() ; i++){
		// if(list_get_educenter_mbrhstry_info2.get(i).getLRNNG_ST().equals("1")
		// &&
		// list_get_educenter_mbrhstry_info2.get(i).getLRNNG_CMPLT_ST().equals("1")){
		// cmpltList += list_get_educenter_mbrhstry_info2.get(i).getCRS_NM() + "
		// (" +
		// mmPublicUtils.changePatternString(list_get_educenter_mbrhstry_info2.get(i).getLRNNG_CMPLT_DT(),
		// "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss") + ")";
		// }
		// cmpltList += "<br>";
		// }
		// Map<String, Object> postMap = new HashMap<String,Object>();
		// postMap.put("message", "이미 이수완료된 교육과정이므로 추가로 이수하지
		// 않아도됩니다.<br><br>*이수완료 리스트<br>" + cmpltList);
		// postMap.put("return_url", referer);
		// redirectAttributes.addFlashAttribute("alert_data",postMap);
		// return "redirect:"+ referer;
		// } else if (list_get_educenter_mbrhstry_info2.size()==0) {
		// String myEdu="";
		// for(int i = 0 ; i < list_get_educenter_mbrhstry_info.size() ; i++){
		// myEdu += list_get_educenter_mbrhstry_info.get(i).getCRS_SN() + "/";
		// }
		// Map<String, Object> postMap = new HashMap<String,Object>();
		// if(myEdu.contains(eduCenterMainVO.getCRS_SN())){
		// // 이수중인 교육 재신청
		// postMap.put("type", "isDelete");
		// postMap.put("message", "이미 수강중 이거나 신청 된 교육과정입니다. 이전 신청 정보 삭제 후 새로
		// 신청하시겠습니까?");
		// postMap.put("return_url", referer);
		// }else{
		// // 이수중인 교육 외 다른 교육 신청
		// postMap.put("type", "isDelete");
		// postMap.put("message", "이미 수강중 이거나 신청 된 다른 교육과정이 존재합니다. 이전 신청 정보 삭제 후
		// 새로 신청하시겠습니까?");
		// postMap.put("return_url", referer);
		// }
		// redirectAttributes.addFlashAttribute("alert_data",postMap);
		// return "educenter/trnng/agree";
		// }
		// }

		return "educenter/trnng/agree";
	}

	// 교육센터-교육신청-동의하기 페이지 -- 모바일
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/agree.do")
	public String educenter_trnng_agree_mobile(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || loginVO.getMBR_ID() == null) {
			// model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/mobile/error/login";
		}
		String referer = request.getHeader("Referer");
		if (referer == null) {
			referer = "/educenter/m/index.do";
		}
		if (eduCenterMainVO.getCRS_SN() == null || eduCenterMainVO.getCRS_SN().length() == 0) {
			// 비정상적인 접근
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			LOGGER.debug("비 정상적인 접근");
			return "redirect:/educenter/m/index.do";
		}

		// 교육과정 정보
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		model.addAttribute("info", eduCenterMainVO);

		if (eduCenterMainVO == null || eduCenterMainVO.getCRS_SN() == null
				|| eduCenterMainVO.getCRS_SN().length() == 0) {
			// 비정상적인 접근
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "올바른 교육정보가 아닙니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			LOGGER.debug("교육 임의 접근");
			return "redirect:/educenter/index.do";
		}

		// 모집기간 확인
		PublicUtils mmPublicUtils = new PublicUtils();
		PublicUtils.RETURN_COMPARE_TYPE dateStr = mmPublicUtils.dateCompare(
				mmPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
				mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
		PublicUtils.RETURN_COMPARE_TYPE dateEnd = mmPublicUtils.dateCompare(
				mmPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
				mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
		if (eduCenterMainVO.getLOCK_ST().equals("0") // 모집잠금해제상태 인 경우
				// &&
				// EgovDateUtil.getDaysDiff(mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_STR_DT(),
				// "yyyy-MM-dd HH:mm:ss.S", "yyyyMMdd"),EgovDateUtil.getToday())
				// >= 0 //모집시작일자
				// &&
				// EgovDateUtil.getDaysDiff(mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_END_DT(),
				// "yyyy-MM-dd HH:mm:ss.S", "yyyyMMdd"),EgovDateUtil.getToday())
				// <= 0 //모집종료일자
				&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
				&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
			if (Integer.parseInt(eduCenterMainVO.getMBR_MAX_CNT()) != 0
					&& Integer.parseInt(eduCenterMainVO.getMBR_CUR_CNT()) + 1 > Integer
							.parseInt(eduCenterMainVO.getMBR_MAX_CNT())) {
				Map<String, Object> postMap = new HashMap<String, Object>();
				postMap.put("message", "해당 교육은 현재 모집마감 되었습니다.");
				redirectAttributes.addFlashAttribute("alert_data", postMap);
				LOGGER.debug("신청인원 마감!");
				return "redirect:/educenter/m/index.do";
			} else {
				LOGGER.debug("정상적인 교육신청 기간!");
			}
		} else {
			// 신청받지 않음
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "해당 교육은 현재 모집기간이 아닙니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			LOGGER.debug("신청받지 않는 상태");
			return "redirect:/educenter/m/index.do";
		}
		/*
		 * if(eduCenterMainVO.getLOCK_ST().equals("1")) { //신청받지 않음 Map<String,
		 * Object> postMap = new HashMap<String,Object>();
		 * postMap.put("message", "해당 교육과정은 신청접수가 중단되었습니다.");
		 * redirectAttributes.addFlashAttribute("alert_data",postMap);
		 * LOGGER.debug("신청받지 않는 상태"); return "redirect:/educenter/m/index.do";
		 * }
		 */

		// 수강내역(메인) 정보
		MyHistoryVO myHistoryVO = new MyHistoryVO();
		// myHistoryVO.setCRS_SN(eduCenterMainVO.getCRS_SN());
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		myHistoryVO.setCRS_STR_DT(mmPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy"));

		// List<MyHistoryVO> list_get_educenter_mbrhstry_info =
		// myHistoryService.get_educenter_mbrhstry_info_list(myHistoryVO);
		// myHistoryVO.setLRNNG_ST("1");
		// myHistoryVO.setLRNNG_CMPLT_ST("1");
		// List<MyHistoryVO> list_get_educenter_mbrhstry_info2 =
		// myHistoryService.get_educenter_mbrhstry_info_list(myHistoryVO);
		// if(list_get_educenter_mbrhstry_info.size() > 0){
		// // 신청 교육 존재할 경우
		// MyHistoryVO myHistoryVO2 = new MyHistoryVO();
		// myHistoryVO2.setMBR_ID(loginVO.getMBR_ID());
		// myHistoryVO2.setCRS_STR_DT(mmPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(),
		// "yyyy-MM-dd HH:mm:ss.S", "yyyy"));
		// if(list_get_educenter_mbrhstry_info2.size() != 0){
		// // 이수완료 존재
		// String cmpltList="";
		// for(int i = 0 ; i < list_get_educenter_mbrhstry_info2.size() ; i++){
		// if(list_get_educenter_mbrhstry_info2.get(i).getLRNNG_ST().equals("1")
		// &&
		// list_get_educenter_mbrhstry_info2.get(i).getLRNNG_CMPLT_ST().equals("1")){
		// cmpltList += list_get_educenter_mbrhstry_info2.get(i).getCRS_NM() + "
		// (" +
		// mmPublicUtils.changePatternString(list_get_educenter_mbrhstry_info2.get(i).getLRNNG_CMPLT_DT(),
		// "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss") + ")";
		// }
		// cmpltList += "<br>";
		// }
		// Map<String, Object> postMap = new HashMap<String,Object>();
		// postMap.put("message", "이미 이수완료된 교육과정이므로 추가로 이수하지
		// 않아도됩니다.<br><br>*이수완료 리스트<br>" + cmpltList);
		// postMap.put("return_url", referer);
		// redirectAttributes.addFlashAttribute("alert_data",postMap);
		// return "redirect:"+ referer;
		// } else if (list_get_educenter_mbrhstry_info2.size()==0) {
		// String myEdu="";
		// for(int i = 0 ; i < list_get_educenter_mbrhstry_info.size() ; i++){
		// myEdu += list_get_educenter_mbrhstry_info.get(i).getCRS_SN() + "/";
		// }
		// Map<String, Object> postMap = new HashMap<String,Object>();
		// if(myEdu.contains(eduCenterMainVO.getCRS_SN())){
		// // 이수중인 교육 재신청
		// postMap.put("type", "isDelete");
		// postMap.put("message", "이미 수강중 이거나 신청 된 교육과정입니다. 이전 신청 정보 삭제 후 새로
		// 신청하시겠습니까?");
		// postMap.put("return_url", referer);
		// }else{
		// // 이수중인 교육 외 다른 교육 신청
		// postMap.put("type", "isDelete");
		// postMap.put("message", "이미 수강중 이거나 신청 된 다른 교육과정이 존재합니다. 이전 신청 정보 삭제 후
		// 새로 신청하시겠습니까?");
		// postMap.put("return_url", referer);
		// }
		// redirectAttributes.addFlashAttribute("alert_data",postMap);
		// return "educenter/mobile/trnng/agree";
		// }
		// }

		return "educenter/mobile/trnng/agree";
	}

	// 교육센터-교육신청-동의하기 페이지 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/checkEdu.do", method = RequestMethod.POST)
	public @ResponseBody ModelAndView ajax_educenter_trnng_check_edu(
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, RedirectAttributes redirectAttributes,
			HttpServletRequest request, ModelMap model, HttpServletResponse response) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();

		ModelAndView mModelAndView = new ModelAndView();
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_cd", list_mbr_cd);
		}
		if (mPublicUtils.isMobileDevice(request))
			mModelAndView.setViewName("educenter/mobile/popup/view_alert");
		else
			mModelAndView.setViewName("educenter/popup/view_alert");

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || loginVO.getMBR_ID() == null) {
			// 로그인이 필요한 서비스입니다
			mModelAndView.addObject("error", "99");
			mModelAndView.addObject("msg", "로그인이 필요한 서비스입니다.\n 로그인 이후 이용해주세요.");
			return mModelAndView;
		}
		if (eduCenterMainVO.getCRS_SN() == null || eduCenterMainVO.getCRS_SN().length() == 0) {
			// 비정상적인 접근
			// 누락된 정보가 있거나 정상적인 접근이 아닙니다.
			mModelAndView.addObject("error", "0");
			mModelAndView.addObject("msg", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
			LOGGER.debug("비정상적인 접근");
			return mModelAndView;
		}
		// 교육과정 정보
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		model.addAttribute("info", eduCenterMainVO);
		mModelAndView.addObject("info2", eduCenterMainVO);

		if (eduCenterMainVO == null || eduCenterMainVO.getCRS_SN() == null
				|| eduCenterMainVO.getCRS_SN().length() == 0) {
			// 비정상적인 접근
			// 올바른 교육정보가 아닙니다.
			LOGGER.debug("교육 임의 접근");
			mModelAndView.addObject("error", "0");
			mModelAndView.addObject("msg", "올바른 교육정보가 아닙니다.");
			return mModelAndView;
		}
		// 모집기간 확인
		PublicUtils mmPublicUtils = new PublicUtils();
		PublicUtils.RETURN_COMPARE_TYPE dateStr = mmPublicUtils.dateCompare(mmPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
		PublicUtils.RETURN_COMPARE_TYPE dateEnd = mmPublicUtils.dateCompare(mmPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",mmPublicUtils.changePatternString(eduCenterMainVO.getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
		if (eduCenterMainVO.getLOCK_ST().equals("0") // 모집잠금해제상태 인 경우
				&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
				&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
			if (Integer.parseInt(eduCenterMainVO.getMBR_MAX_CNT()) != 0 && Integer.parseInt(eduCenterMainVO.getMBR_CUR_CNT()) + 1 > Integer.parseInt(eduCenterMainVO.getMBR_MAX_CNT())) {
				// 신청받지 않음
				// 해당 교육은 현재 모집마감
				LOGGER.debug("신청인원 마감!");
				mModelAndView.addObject("error", "0");
				mModelAndView.addObject("msg", "해당 교육은 현재 모집마감 상태입니다.");
				return mModelAndView;
			} else {
				LOGGER.debug("정상적인 교육신청 기간!");
			}
		} else {
			// 신청받지 않음
			// 해당 교육은 현재 모집기간이 아닙니다.
			LOGGER.debug("신청받지 않는 상태");
			mModelAndView.addObject("error", "0");
			mModelAndView.addObject("msg", "해당 교육은 현재 모집기간이 아닙니다.");
			return mModelAndView;
		}
		// 수강내역(메인) 정보
		MyHistoryVO myHistoryVO = new MyHistoryVO();
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		if (eduCenterMainVO.getCRS_LAW_TYPE().equals("CIDLAW002")) {
			myHistoryVO.setCRS_STR_DT(mmPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(),"yyyy-MM-dd HH:mm:ss.S", "yyyy"));
		} else if (eduCenterMainVO.getCRS_LAW_TYPE().equals("default")) {
			myHistoryVO.setCRS_STR_DT(mmPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(),"yyyy-MM-dd HH:mm:ss.S", "yyyy-MM"));
		}
		myHistoryVO.setTYPE_GB(eduCenterMainVO.getTYPE_GB());
		myHistoryVO.setCRS_MBR_CD(eduCenterMainVO.getCRS_MBR_CD());

		// 신청중인 교육 리스트
		List<MyHistoryVO> list_get_educenter_mbrhstry_info = myHistoryService.get_educenter_mbrhstry_info_list(myHistoryVO);
		myHistoryVO.setLRNNG_ST("1");
		myHistoryVO.setLRNNG_CMPLT_ST("1");
		// 이수완료된 교육 리스트
		List<MyHistoryVO> list_get_educenter_mbrhstry_info2 = myHistoryService.get_educenter_mbrhstry_info_list(myHistoryVO);
		if (list_get_educenter_mbrhstry_info.size() > 0) {
			// 신청교육 존재 O
			MyHistoryVO myHistoryVO2 = new MyHistoryVO();
			myHistoryVO2.setMBR_ID(loginVO.getMBR_ID());
			myHistoryVO2.setCRS_STR_DT(mmPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(),"yyyy-MM-dd HH:mm:ss.S", "yyyy"));
			String myCrsSn = "";// 신청중인 교육 교육과정
			String myCrsLawType = "";// 신청중인 교육 교육유형(default - 일반교육, CIDLAW002 -
										// 신규재개자교육)
			String myCrsMbrCd = "";// 신청중인 교육 교육종류(CIDN0200 - 낚시터, CIDN0300 -
									// 낚시어선)
			String myTypeGb = "";// 신청중인 교육 교육종류(online - 온라인, offline - 오프라인)

			String myCrsSn2 = "";// 이수완료인 교육 교육과정
			String myCrsLawType2 = "";// 신청중인 교육 교육유형(default - 일반교육, CIDLAW002
										// - 신규재개자교육)
			String myCrsMbrCd2 = "";// 이수완료인 교육 교육대상
			for (int i = 0; i < list_get_educenter_mbrhstry_info.size(); i++) {
				myCrsSn += list_get_educenter_mbrhstry_info.get(i).getCRS_SN() + "/";
				myCrsLawType += list_get_educenter_mbrhstry_info.get(i).getCRS_LAW_TYPE() + "/";
				myTypeGb += list_get_educenter_mbrhstry_info.get(i).getTYPE_GB() + "/";
				if (list_get_educenter_mbrhstry_info.get(i).getCRS_LAW_TYPE().equals("default"))
					myCrsMbrCd += list_get_educenter_mbrhstry_info.get(i).getCRS_MBR_CD() + "/";
			}
			for (int i = 0; i < list_get_educenter_mbrhstry_info2.size(); i++) {
				myCrsSn2 += list_get_educenter_mbrhstry_info2.get(i).getCRS_SN() + "/";
				myCrsLawType2 += list_get_educenter_mbrhstry_info2.get(i).getCRS_LAW_TYPE() + "/";
			}
			//같은 년도 교육 들을려고 할 경우 ::교육불가
			for (int i = 0; i < list_get_educenter_mbrhstry_info.size(); i++) {
					String CRS_YEAR = mPublicUtils.changePatternString(list_get_educenter_mbrhstry_info.get(i).getCRS_STR_DT(),"yyyy-MM-dd HH:mm:ss","yyyy");
					if(eduCenterMainVO.getCRS_YEAR().equals(CRS_YEAR)){
						mModelAndView.addObject("alertType", "alert");
						mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
						mModelAndView.addObject("error", "0");
						mModelAndView.addObject("msg", "이미 해당년도 교육을 신청했습니다.");	
					} else {	
						if (list_get_educenter_mbrhstry_info2.size() == 0) {
							// 이수 완료 교육 존재 X
							if (myCrsSn.contains(eduCenterMainVO.getCRS_SN())) {
								// 이수중인 교육 재신청 :: 교육신청 불가
								mModelAndView.addObject("alertType", "alert");
								mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
								mModelAndView.addObject("error", "0");
								mModelAndView.addObject("msg", "이미 수강중인 교육과정입니다.");
							} else {
								// 이수중인 교육 외 다른 교육 신청
								if (eduCenterMainVO.getCRS_LAW_TYPE().equals("CIDLAW002")) {
									// 신청교육이 신규재개자 교육일 경우
									if (myCrsLawType.contains(eduCenterMainVO.getCRS_LAW_TYPE())) {
										// 이수중인 교육이 신규재개자 교육일 경우 :: 교육신청 불가
										mModelAndView.addObject("alertType", "alert");
										mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
										mModelAndView.addObject("error", "0");
										mModelAndView.addObject("msg", "이미 같은 종류의 교육을 신청중입니다.");
									} else {
										// 이수중인 교육 외 다른 교육 신청 :: 교육신청 가능
										mModelAndView.addObject("alertType", "application");
										mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
										mModelAndView.addObject("error", "0");
										mModelAndView.addObject("msg", "다음 교육을 신청하시겠습니까?");
									}
								} else if (eduCenterMainVO.getCRS_LAW_TYPE().equals("default")) {
									// 신청교육이 일반 교육일 경우
									if (myCrsMbrCd.contains(eduCenterMainVO.getCRS_MBR_CD())) {
										// 일반교육중 같은 교육종류의 교육을 신청할 경우
										if (myTypeGb.contains(eduCenterMainVO.getTYPE_GB())) {
											// 오프라인 교육일 경우 :: 기존 교육 삭제 후 교육신청
											mModelAndView.addObject("alertType", "confirm2");
											mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
											mModelAndView.addObject("error", "0");
											mModelAndView.addObject("msg", "기존 교육 삭제 후 교육을 삭제 후 새로 교육을 신청 하시겠습니까?");
										} else {
											// 온라인 교육일 경우 :: 교육 신청 불가
											mModelAndView.addObject("alertType", "alert");
											mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
											mModelAndView.addObject("error", "0");
											mModelAndView.addObject("msg", "이미 같은 종류의 교육을 신청중입니다.");
										}
									} else {
										// 이수중인 교육 외 다른 교육 신청 :: 교육신청 가능
										mModelAndView.addObject("alertType", "application");
										mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
										mModelAndView.addObject("error", "0");
										mModelAndView.addObject("msg", "다음 교육을 신청하시겠습니까?");
									}
								}

							}
						} else if (list_get_educenter_mbrhstry_info2.size() != 0) {
							// 이수완료 교육 존재 O
							if (myCrsSn2.contains(eduCenterMainVO.getCRS_SN())) {
								// 이수완료인 교육 재신청 :: 교육신청 불가
								mModelAndView.addObject("alertType", "alert");
								mModelAndView.addObject("info", list_get_educenter_mbrhstry_info2);
								mModelAndView.addObject("error", "0");
								mModelAndView.addObject("msg", "이미 이수완료된 교육과정입니다.");
							} else {
								if (eduCenterMainVO.getCRS_LAW_TYPE().equals("CIDLAW002")) {
									// // 신청교육이 신규재개자 교육일 경우
									if (myCrsLawType2.contains(eduCenterMainVO.getCRS_LAW_TYPE())) {
										// 이수완료된 신규재개자 교육이 존재할 경우 :: 교육신청 불가
										mModelAndView.addObject("alertType", "alert");
										mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
										mModelAndView.addObject("error", "0");
										mModelAndView.addObject("msg", "이미 같은 종류의 교육을 이수완료했습니다.");
									} else {
										// 이수완료된 신규재개자 교육이 존재하지 않을 경우
										if (myCrsLawType.contains(eduCenterMainVO.getCRS_LAW_TYPE())) {
											// 이수중인 교육이 신규재개자 교육일 경우 :: 교육신청 불가
											mModelAndView.addObject("alertType", "alert");
											mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
											mModelAndView.addObject("error", "0");
											mModelAndView.addObject("msg", "이미 같은 종류의 교육을 신청중입니다.");
										} else {
											// 이수중인 교육 외 다른 교육 신청 :: 교육신청 가능
											mModelAndView.addObject("alertType", "application");
											mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
											mModelAndView.addObject("error", "0");
											mModelAndView.addObject("msg", "다음 교육을 신청하시겠습니까?");
										}
									}
								} else {
									// 신청하려는 교육이 신규/재개자 교육 X
									for (int j = 0; j < list_get_educenter_mbrhstry_info2.size(); j++) {
										if (list_get_educenter_mbrhstry_info2.get(j).getCRS_LAW_TYPE().equals("default"))
											myCrsMbrCd2 += list_get_educenter_mbrhstry_info2.get(j).getCRS_MBR_CD() + "/";
									}						
									if (myCrsMbrCd2.contains(eduCenterMainVO.getCRS_MBR_CD())) {
										// 같은 교육대상의 교육 ( 낚시터 / 낚시어선 ) :: 교육신청 불가
										mModelAndView.addObject("alertType", "alert");
										mModelAndView.addObject("info", list_get_educenter_mbrhstry_info2);
										mModelAndView.addObject("error", "0");
										mModelAndView.addObject("msg", "이미 같은 종류의 교육을 이수완료했습니다.");
									} else {
										//다른 종류 교육인 경우
										if (myCrsSn.contains(eduCenterMainVO.getCRS_SN())) {
											// 이수중인 교육 재신청 :: 교육신청 불가
											mModelAndView.addObject("alertType", "alert");
											mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
											mModelAndView.addObject("error", "0");
											mModelAndView.addObject("msg", "이미 수강중인 교육과정입니다.");
										} else {
											//수강중인 교육이 아닌것
											if (myCrsMbrCd.contains(eduCenterMainVO.getCRS_MBR_CD())) {
												// 일반교육중 같은 교육종류의 교육을 신청할 경우 :: 교육신청 불가
												mModelAndView.addObject("alertType", "alert");
												mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
												mModelAndView.addObject("error", "0");
												mModelAndView.addObject("msg", "이미 같은 종류의 교육을 신청중입니다.");
											} else {										
												// 이수중인 교육 외 다른 교육 신청 :: 교육신청 가능
												mModelAndView.addObject("alertType", "application");
												mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
												mModelAndView.addObject("error", "0");
												mModelAndView.addObject("msg", "다음 교육을 신청하시겠습니까?");
											}
										}
									}
								}
							}
						}
					}
			}	
			return mModelAndView;	
		} else {
			// 신청교육 존재 X
			mModelAndView.addObject("alertType", "application");
			mModelAndView.addObject("error", "0");
			mModelAndView.addObject("msg", "다음 교육을 신청하시겠습니까?");
			return mModelAndView;
		}
	}

	// 교육센터-교육신청-신청서 페이지 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/write.do")
	public String educenter_trnng_write(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || loginVO.getMBR_ID() == null) {
			// model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/error/login";
		}
		if (eduCenterMainVO.getAgree() == null || (!eduCenterMainVO.getAgree().equals("1") && !eduCenterMainVO.getAgree().equals("2")) || eduCenterMainVO.getCRS_SN() == null || eduCenterMainVO.getCRS_SN().length() == 0) {
			String referer = request.getHeader("Referer");
			if (referer == null) {
				referer = "/educenter/index.do";
			}
			LOGGER.debug("referer : " + referer);
			return "redirect:" + referer;
		}

		// 교육과정 정보
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		model.addAttribute("info_crs", eduCenterMainVO);

		// 회원정보조회
		EduCenterMemberVO eduCenterMemberVO = new EduCenterMemberVO();
		eduCenterMemberVO.setMBR_ID(loginVO.getMBR_ID());
		eduCenterMemberVO = eduCenterMemberService.get_edu_member_info(eduCenterMemberVO);
		model.addAttribute("info_mbr", eduCenterMemberVO);
		eduCenterMemberVO.setDTL_CD(eduCenterMainVO.getCRS_MBR_CD());
		model.addAttribute("list_mbr_dtl", eduCenterMemberService.get_edu_member_dtl_all_list(eduCenterMemberVO));

		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_cd", list_mbr_cd);
		}
		// 사업자구분코드
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00006");
			List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_license_se_cd", list_position_cd);
		}
		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_address_cd", list_address_cd);
		}
		// 신청지역제한(낚시어선) 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00008");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_lock_area_ship_grp_cd", list_lock_area_grp_cd);
		}
		// 신청지역제한(낚시터) 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00009");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_lock_area_house_grp_cd", list_lock_area_grp_cd);
		}

		return "educenter/trnng/write";
	}

	// 교육센터-교육신청-신청서 페이지 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/trnng/write.do")
	public String educenter_trnng_write_mobile(@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || loginVO.getMBR_ID() == null) {
			// model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/mobile/error/login";
		}
		if (eduCenterMainVO.getAgree() == null || (!eduCenterMainVO.getAgree().equals("1") && !eduCenterMainVO.getAgree().equals("2")) || eduCenterMainVO.getCRS_SN() == null || eduCenterMainVO.getCRS_SN().length() == 0) {
			String referer = request.getHeader("Referer");
			if (referer == null) {
				referer = "/educenter/m/index.do";
			}
			LOGGER.debug("referer : " + referer);
			return "redirect:" + referer;
		}

		// 교육과정 정보
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		model.addAttribute("info_crs", eduCenterMainVO);

		// 회원정보조회
		EduCenterMemberVO eduCenterMemberVO = new EduCenterMemberVO();
		eduCenterMemberVO.setMBR_ID(loginVO.getMBR_ID());
		eduCenterMemberVO = eduCenterMemberService.get_edu_member_info(eduCenterMemberVO);
		model.addAttribute("info_mbr", eduCenterMemberVO);
		eduCenterMemberVO.setDTL_CD(eduCenterMainVO.getCRS_MBR_CD());
		model.addAttribute("list_mbr_dtl", eduCenterMemberService.get_edu_member_dtl_all_list(eduCenterMemberVO));

		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 대상구분 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00002");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_mbr_cd", list_mbr_cd);
		}
		// 사업자구분코드
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00006");
			List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_license_se_cd", list_position_cd);
		}
		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_address_cd", list_address_cd);
		}
		// 신청지역제한(낚시어선) 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00008");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_lock_area_ship_grp_cd", list_lock_area_grp_cd);
		}
		// 신청지역제한(낚시터) 코드 조회
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00009");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_lock_area_house_grp_cd", list_lock_area_grp_cd);
		}

		return "educenter/mobile/trnng/write";
	}

	// 교육센터-교육신청-신청 로직 처리 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String educenter_trnng_write_act(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			// 1. 로그인 체크 
			if (loginVO == null || loginVO.getMBR_ID() == null) {
				// model.addAttribute("message", "로그인이 필요한 서비스입니다.");
				data.put("error", "1");
				data.put("msg", "로그인이 필요한 서비스입니다.");			
			
			} else {
				List<String> externalVideoUrl = new ArrayList<>();
				//외부 동영상 링크 URL
				boolean isAllow = true;
				
				//[보안점검수정][START]####################################################
				// 2. 비정상적인 접근 체크
			    if (myHistoryVO.getMBR_ID() == null || "".equals(myHistoryVO.getMBR_ID())) {
					data.put("error", "1");
					data.put("msg", "비정상적인 접근방법 입니다.");
					isAllow = false;
			    }
			    //[보안점검수정][END]####################################################
			    // 3. 신청중인 교육 중복 체크
				MyHistoryVO myHistoryVO2 = new MyHistoryVO();
				myHistoryVO2.setCRS_SN(myHistoryVO.getCRS_SN());
				myHistoryVO2.setMBR_ID(loginVO.getMBR_ID());
				myHistoryVO2.setUSE_ST("1");
				myHistoryVO2.setDEL_ST("0");
				List<MyHistoryVO> list_get_educenter_mbrhstry_info_dup = myHistoryService.get_educenter_mbrhstry_info_list(myHistoryVO2);
				
				if (list_get_educenter_mbrhstry_info_dup.size() > 0) {
					// 중복 교육 존재
					LOGGER.debug("[교육신청]최종 교육신청불가 ");
					data.put("error", "3");
					data.put("msg", "이미 신청한 교육입니다.");
					isAllow = false;
				}
				// 4. 커리큘럼 메인
				String CRS_SN = myHistoryVO.getCRS_SN();
				EduCenterMainVO parentInfo = new EduCenterMainVO();
				parentInfo.setCRS_SN(CRS_SN);
				parentInfo = eduCenterMainService.get_educenter_curriculum_info(parentInfo);
				// 커리큘럼 존재 유무 판단
				if (parentInfo == null || parentInfo.getCRS_SN() == null || parentInfo.getCRS_SN().length() == 0) {
					data.put("error", "1");
					data.put("msg", "비정상적인 접근방법 입니다.");
					isAllow = false;
				}
				
				
				// 모집기간 확인
				String LRNNG_ST = "0";
				// 승인여부
				PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
						mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
						mPublicUtils.changePatternString(parentInfo.getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
				PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
						mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
						mPublicUtils.changePatternString(parentInfo.getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
				
				if (parentInfo.getLOCK_ST().equals("0") && !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02) && !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
					LOGGER.debug("[교육신청]정상적인 교육신청 기간!");
					// 모집인원을 초과하는 경우 보류상태(=대기자)로 등록
					if (Integer.parseInt(parentInfo.getMBR_MAX_CNT()) != 0 && Integer.parseInt(parentInfo.getMBR_CUR_CNT()) + 1 > Integer.parseInt(parentInfo.getMBR_MAX_CNT())) {
						LRNNG_ST = "4";
					}
				} else {
					LOGGER.debug("현재 모집기간이 아니거나 모집이 중단 되었습니다.");
					data.put("error", "1");
					data.put("msg", "현재 모집기간이 아니거나 모집이 중단 되었습니다.");
					isAllow = false;
				}
				
				MyHistoryVO myEduHistory = new MyHistoryVO();
				myEduHistory.setMBR_ID(loginVO.getMBR_ID());
				myEduHistory.setTYPE_GB(parentInfo.getTYPE_GB());
				myEduHistory.setCRS_MBR_CD(parentInfo.getCRS_MBR_CD());
				PublicUtils mmPublicUtils = new PublicUtils();
				
				// 지금 신청하는 교육과정이 신규재개자교육인지 이외인지 필요
				if (parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) {
					myEduHistory.setCRS_STR_DT(mmPublicUtils.changePatternString(parentInfo.getCRS_END_DT(),"yyyy-MM-dd HH:mm:ss.S", "yyyy"));
				} else if (parentInfo.getCRS_LAW_TYPE().equals("default")) {
					myEduHistory.setCRS_STR_DT(mmPublicUtils.changePatternString(parentInfo.getCRS_END_DT(),"yyyy-MM-dd HH:mm:ss.S", "yyyy-MM"));
				}

				// 신청중인 교육 리스트
				List<MyHistoryVO> list_get_educenter_mbrhstry_info = myHistoryService.get_educenter_mbrhstry_info_list(myEduHistory);
				myEduHistory.setLRNNG_ST("1");
				myEduHistory.setLRNNG_CMPLT_ST("1");
				// 이수완료된 교육 리스트
				List<MyHistoryVO> list_get_educenter_mbrhstry_info2 = myHistoryService.get_educenter_mbrhstry_info_list(myEduHistory);
				
				// 4. 현재 신청중인 교육 중복 비교
				if (list_get_educenter_mbrhstry_info.size() > 0) {
					
					String myCrsSn = "";// 신청중인 교육 교육과정
					String myCrsLawType = "";// 신청중인 교육 교육유형(default - 일반교육, CIDLAW002 - 신규재개자교육)
					String myCrsMbrCd = "";// 신청중인 교육 교육종류(CIDN0200 - 낚시터, CIDN0300 - 낚시어선)
					String myTypeGb = "";// 신청중인 교육 교육종류(online - 온라인, offline - 오프라인)
					
					for (int i = 0; i < list_get_educenter_mbrhstry_info.size(); i++) {
						String CRS_YEAR = mPublicUtils.changePatternString(list_get_educenter_mbrhstry_info.get(i).getCRS_STR_DT(),"yyyy-MM-dd HH:mm:ss","yyyy");
						myCrsSn += list_get_educenter_mbrhstry_info.get(i).getCRS_SN() + "/";
						myCrsLawType += list_get_educenter_mbrhstry_info.get(i).getCRS_LAW_TYPE() + "/";
						myTypeGb += list_get_educenter_mbrhstry_info.get(i).getTYPE_GB() + "/";
						
						if (list_get_educenter_mbrhstry_info.get(i).getCRS_LAW_TYPE().equals("default")) {
							myCrsMbrCd += list_get_educenter_mbrhstry_info.get(i).getCRS_MBR_CD() + "/";
						}
						
						if(parentInfo.getCRS_YEAR().equals(CRS_YEAR)){
							data.put("error", "3");
							data.put("msg", "이미 해당년도 교육을 신청했습니다.");
							isAllow = false;
						}
					}
					
					// 이수중인 교육 외 다른 교육 신청
					if (parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) {
						// 신청교육이 신규재개자 교육일 경우
						if (myCrsLawType.contains(parentInfo.getCRS_LAW_TYPE())) {
							// 이수중인 교육이 신규재개자 교육일 경우 :: 교육신청 불가
							data.put("error", "3");
							data.put("msg", "이미 같은 종류의 교육을 신청중입니다.");
							isAllow = false;
						}
					} else if (parentInfo.getCRS_LAW_TYPE().equals("default")) {
						// 신청교육이 일반 교육일 경우
						if (myCrsMbrCd.contains(parentInfo.getCRS_MBR_CD())) {
							// 일반교육중 같은 교육종류의 교육을 신청할 경우
							if (myTypeGb.contains(parentInfo.getTYPE_GB())) {
								data.put("error", "3");
								data.put("msg", "이미 같은 종류의 교육을 신청중입니다.");
								isAllow = false;
							}
						}
					}
				}
				
				// 5.완료 교육 중복 비교
				if (list_get_educenter_mbrhstry_info2.size() > 0) {
					String myCrsSn2 = "";// 이수완료인 교육 교육과정
					String myCrsLawType2 = "";// 신청중인 교육 교육유형(default - 일반교육, CIDLAW002 - 신규재개자교육)
					String myCrsMbrCd2 = "";// 이수완료인 교육 교육대상
					
					for (int i = 0; i < list_get_educenter_mbrhstry_info2.size(); i++) {
						myCrsSn2 += list_get_educenter_mbrhstry_info2.get(i).getCRS_SN() + "/";
						myCrsLawType2 += list_get_educenter_mbrhstry_info2.get(i).getCRS_LAW_TYPE() + "/";
					}
						
					//같은 년도 교육 들을려고 할 경우 ::교육불가
					for (int i = 0; i < list_get_educenter_mbrhstry_info2.size(); i++) {
						String CRS_YEAR = mPublicUtils.changePatternString(list_get_educenter_mbrhstry_info2.get(i).getCRS_STR_DT(),"yyyy-MM-dd HH:mm:ss","yyyy");
						if(parentInfo.getCRS_YEAR().equals(CRS_YEAR)){
							data.put("error", "3");
							data.put("msg", "이미 해당년도 교육을 신청했습니다.");
							isAllow = false;
						} else {
							// 이수완료 교육 존재 O
							if (myCrsSn2.contains(parentInfo.getCRS_SN())) {
								// 이수완료인 교육 재신청 :: 교육신청 불가
								data.put("error", "3");
								data.put("msg", "이미 이수완료된 교육과정입니다.");
								isAllow = false;
							} else {
								if (parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) {
									// // 신청교육이 신규재개자 교육일 경우
									if (myCrsLawType2.contains(parentInfo.getCRS_LAW_TYPE())) {
										// 이수완료된 신규재개자 교육이 존재할 경우 :: 교육신청 불가
										data.put("error", "3");
										data.put("msg", "이미 같은 종류의 교육을 이수완료했습니다.");
										isAllow = false;
									}
								} else {					
									if (myCrsMbrCd2.contains(parentInfo.getCRS_MBR_CD())) {
										// 같은 교육대상의 교육 ( 낚시터 / 낚시어선 ) :: 교육신청 불가
										data.put("error", "3");
										data.put("msg", "이미 같은 종류의 교육을 이수완료했습니다.");
										isAllow = false;
									}
								}
							}
						}
					}		
				}
				// 신청중인 교육과정 정보
				/*
				 * EduCenterMainVO eduCenterMainVO = new EduCenterMainVO();
				 * eduCenterMainVO.setCRS_SN(myHistoryVO.getCRS_SN());
				 * eduCenterMainVO =
				 * eduCenterMainService.get_educenter_curriculum_info(
				 * eduCenterMainVO);
				 */

				/*
				 * //수강내역 등록 중복 여부 확인 PublicUtils mmPublicUtils = new
				 * PublicUtils(); MyHistoryVO ChkOverlayMyHistoryVO = new
				 * MyHistoryVO(); //
				 * ChkOverlayMyHistoryVO.setCRS_SN(parentInfo.getCRS_SN());
				 * ChkOverlayMyHistoryVO.setMBR_ID(loginVO.getMBR_ID());
				 * ChkOverlayMyHistoryVO.setCRS_STR_DT(mmPublicUtils.
				 * changePatternString(eduCenterMainVO.getCRS_END_DT(),
				 * "yyyy-MM-dd HH:mm:ss.S", "yyyy")); List<MyHistoryVO>
				 * list_get_educenter_mbrhstry_info =
				 * myHistoryService.get_educenter_mbrhstry_info_list(
				 * ChkOverlayMyHistoryVO);
				 * if(list_get_educenter_mbrhstry_info.size() > 0){ // 신청 교육 존재할
				 * 경우 data.put("error", "2"); data.put("msg",
				 * "이미 수강중인 교육입니다. 기존 정보를 삭제후 새로 신청합니다."); for(int i = 0 ; i <
				 * list_get_educenter_mbrhstry_info.size() ; i++){ StringBuilder
				 * log_dscrp = new StringBuilder(); StringBuilder log_msg = new
				 * StringBuilder();
				 * 
				 * //연도별이수내역에서 제거 try { String TRGT_YEAR =
				 * mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().
				 * replace(".0",""), "yyyy-MM-dd HH:mm:ss", "yyyy");
				 * log_dscrp.append("|"+TRGT_YEAR+"연도별이수내역제거");
				 * log_msg.append("|"+TRGT_YEAR+"연도별이수내역제거"); EduMemberVO
				 * delEduMemberVO = new EduMemberVO();
				 * delEduMemberVO.setCRS_SN(list_get_educenter_mbrhstry_info.get
				 * (i).getCRS_SN());
				 * delEduMemberVO.setHMBR_SN(list_get_educenter_mbrhstry_info.
				 * get(i).getHMBR_SN()); delEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
				 * delEduMemberVO.setMBR_ID(list_get_educenter_mbrhstry_info.get
				 * (i).getMBR_ID());
				 * eduMemberService.remove_edu_member_target(delEduMemberVO);
				 * EduMemberVO chkEduMemberVO = new EduMemberVO();
				 * chkEduMemberVO.setMBR_ID(list_get_educenter_mbrhstry_info.get
				 * (i).getMBR_ID()); chkEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
				 * List<EduMemberVO> chkTargetEduBeanList =
				 * eduMemberService.get_edu_member_target_all_list(
				 * chkEduMemberVO); if(chkTargetEduBeanList == null ||
				 * chkTargetEduBeanList.size() == 0) {
				 * log_dscrp.append("-빈연도별이수내역생성");
				 * log_msg.append("|빈연도별이수내역생성");
				 * chkEduMemberVO.setMBR_CD(list_get_educenter_mbrhstry_info.get
				 * (i).getMBR_ID()); chkEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
				 * chkEduMemberVO.setREG_MBR_ID("admin");
				 * chkEduMemberVO.setUPD_MBR_ID("admin");
				 * eduMemberService.set_edu_member_target_reg(chkEduMemberVO); }
				 * } catch(Exception e) { LOGGER.debug("[fail][연도별이수내역제거] "
				 * +e.toString()); } // log_dscrp.append("]");
				 * myHistoryService.set_educenter_mbrhstry_cancel(
				 * list_get_educenter_mbrhstry_info.get(i)); EduMyHistoryVO
				 * eduMyHistoryVO = new EduMyHistoryVO();
				 * eduMyHistoryVO.setHMBR_SN(list_get_educenter_mbrhstry_info.
				 * get(i).getHMBR_SN());
				 * 
				 * eduMyHistoryService.remove_edu_mbrhstry(eduMyHistoryVO);
				 * eduMyHistoryService.del_edu_mbrhstry(eduMyHistoryVO);
				 * //EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
				 * //eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
				 * EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
				 * eduCurriculumVO.setCRS_SN(list_get_educenter_mbrhstry_info.
				 * get(i).getCRS_SN());
				 * eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(
				 * eduCurriculumVO); log_dscrp.append("-수강신청인원감소처리");
				 * log_msg.append("|수강신청인원감소처리");
				 * if(list_get_educenter_mbrhstry_info.get(i).getLRNNG_CMPLT_ST(
				 * ).equals("1")) {
				 * eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_down(
				 * eduCurriculumVO); log_dscrp.append("-이수완료인원감소처리");
				 * log_msg.append("|이수완료인원감소처리"); } try { // LOG RECORED (로그기록)
				 * LogRecordVO mEduLogRecordVO = new LogRecordVO();
				 * log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(
				 * list_get_educenter_mbrhstry_info.get(i)));
				 * mEduLogRecordVO.setLOG_MSG(log_msg.toString());
				 * mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				 * mEduLogRecordVO.setTBL_INF(
				 * "EDU_MBR_HSTRY_TB (EDU_MBR_HSTRY_DTL_TB)");
				 * mEduLogRecordVO.setTBL_SN("교육취소");
				 * mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
				 * mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
				 * mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request
				 * )); logRecordService.set_log_data(mEduLogRecordVO,request); }
				 * catch(Exception e) { LOGGER.debug("[fail log record] "
				 * +e.toString()); } } // isAllow = false; } //End 수강내역 등록 중복 여부
				 * 확인
				 */

				// 온라인 교육 신청 가능자 여부 조회
				if (isAllow && parentInfo.getCRS_GRP_CD().equals("CIDE00000000000")) { 
					// 온라인 교육  건
					LOGGER.debug("[교육신청]온라인 교육 신청건");
					// 교육이수건 확인
					String currentYear = mPublicUtils.currentTime("yyyy");
					String prevYear = String.valueOf(Integer.parseInt(currentYear) - 1);
					boolean isAllowCurrentTargetYear = true;
					boolean isAllowPrevTargetYear = true;
					EduMemberVO eduMemberVO = new EduMemberVO();
					eduMemberVO.setMBR_ID(loginVO.getMBR_ID());
					List<EduMemberVO> chkTargetEduBeanList = eduMemberService
							.get_edu_member_target_all_list(eduMemberVO);
					for (EduMemberVO subEduMemberVO : chkTargetEduBeanList) {
						if (subEduMemberVO.getCMPLT_ST().equals("1")) {

							if (subEduMemberVO.getMBR_TRGT_CD() == null)
								continue;

							if (parentInfo.getCRS_MBR_CD().equals("CIDN010300")
									&& subEduMemberVO.getMBR_TRGT_CD().equals("CIDN010300")) {
								// 낚시어선만  검증
								// 회원정보에서 교육이수자 검증
								if (subEduMemberVO.getTRGT_YEAR().equals(prevYear)) {
									// 이전년도 이수자 인 경우
									isAllowPrevTargetYear = false;
									// 이전년도 이수자인 경우 신청제한(온라인교육 확대시행으로 한시적 허용함 2020.03.29)
									LOGGER.debug("[교육신청]이전년도 이수자인 경우 신청제한(온라인교육 확대시행으로 한시적 허용함 2020.03.29)");
								} else if (subEduMemberVO.getTRGT_YEAR().equals(currentYear)) {
									// 금년도 이수자인 경우
									isAllowCurrentTargetYear = false;
									LOGGER.debug("[교육신청]금년도 이수자인 경우");
								}
								// End of 회원정보에서 교육이수자 검증
							} else if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")
									&& subEduMemberVO.getMBR_TRGT_CD().equals("CIDN010200")) {
								// 낚시터만 검증
								if (subEduMemberVO.getTRGT_YEAR().equals(currentYear)) {
									// 금년도 이수자인 경우
									// isAllowCurrentTargetYear = false;
									LOGGER.debug("[교육신청]금년도 이수자인 경우");
								}
							}
						}
					}

					///이전년도 이수자인 경우 신청제한(온라인교육 확대시행으로 한시적 허용함 2020.03.29) , 
					//추후 재적용시 주석 해제
					// if(!isAllowCurrentTargetYear || !isAllowPrevTargetYear) {
					// data.put("error", "3");
					// data.put("msg", "2019년도 교육 이수자는 교육대상자가 아닙니다.\n2020년도 집합교육이 재개 될 시 현장 교육을 이수하여 주시기 바랍니다.");
					// isAllow = false;
					// }

					// End of 교육이수건 확인
					// 회원상세정보 조회 검증
					boolean isAllowMbrDtlCd = false;
					EduCenterMemberVO eduCenterMemberVO = new EduCenterMemberVO();
					eduCenterMemberVO.setMBR_ID(loginVO.getMBR_ID());
					List<EduCenterMemberVO> list_mbr_dtl = eduCenterMemberService
							.get_edu_member_dtl_all_list(eduCenterMemberVO);
					if (list_mbr_dtl.size() > 0) {
						for (EduCenterMemberVO item_mbr_dtl : list_mbr_dtl) {
							if (item_mbr_dtl.getDTL_CD().equals(parentInfo.getCRS_MBR_CD())) { 
								// 교육과정의 낚시어선 또는 낚시터 조건에 매칭하는 경우
								LOGGER.debug("[회원상세정보] 교육대상그룹코드 조건과 일치(" + item_mbr_dtl.getDTL_CD() + ")");
								isAllowMbrDtlCd = true;
							} else {
								LOGGER.debug("[회원상세정보] 교육대상그룹코드 조건과 일치하지 않음(" + item_mbr_dtl.getDTL_CD() + ")");
							}
						}
					} else {// 상세정보가 없는 사용자의 경우 예외처리 (신청가능)
						LOGGER.debug("[회원상세정보]상세정보가 없는 사용자");
						isAllowMbrDtlCd = true;
					}
					// End of 회원상세정보 조회 검증
					// 신청제한지역설정 확인
					boolean isLockArea = true;
					String isLockMsg = "";
					String checkLockAreaCd = "";
					String checkLockAreaNm = "";
					if (parentInfo.getCRS_LOCK_AREA_ST().equals("1")) {
						if (parentInfo.getCRS_MBR_CD().equals("CIDN010300")) { 
							// 낚시어선 검증
							// 신청지역제한 코드 조회
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_MASTER_ID("CID00008");
							mCodeSetVO.setCD_ID(parentInfo.getCRS_LOCK_AREA_CD());
							List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
							for (CodeSetVO item_category : list_lock_area_grp_cd) {
								checkLockAreaCd = item_category.getCD_ID();
								checkLockAreaNm = item_category.getCD_DES();
							}
							isLockMsg = "해당 교육은 낚시어선 신고지에 속한 대상자만 신청 하실 수 있습니다.";
							LOGGER.debug("[교육신청]해당 교육은 낚시어선 신고지에 속한 대상자만 신청 하실 수 있습니다.");
						} else if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
							// 낚시터 검증
							// 신청지역제한 코드 조회
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_MASTER_ID("CID00009");
							mCodeSetVO.setCD_ID(parentInfo.getCRS_LOCK_AREA_CD());
							List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
							for (CodeSetVO item_category : list_lock_area_grp_cd) {
								checkLockAreaCd = item_category.getCD_ID();
								checkLockAreaNm = item_category.getCD_DES();
							}
							isLockMsg = "해당 교육은 낚시터 신고지에 속한 대상자만 신청 하실 수 있습니다.";
							LOGGER.debug("[교육신청]해당 교육은 낚시터 신고지에 속한 대상자만 신청 하실 수 있습니다.");
						}
						LOGGER.debug("[교육신청]지역제한시도 코드 : " + checkLockAreaCd + " , 지역제한시도 명 : " + checkLockAreaNm);
						// 회원상세정보 조회 검증
						if (list_mbr_dtl.size() > 0) {
							for (EduCenterMemberVO item_mbr_dtl : list_mbr_dtl) {
								if (item_mbr_dtl.getDTL_CD().equals(parentInfo.getCRS_MBR_CD())) { 
									// 교육과정의 낚시어선 또는 낚시터 조건에 매칭하는 경우
									LOGGER.debug("[회원상세정보] 나의 시도 : " + item_mbr_dtl.getSIDO_NM() + " , 나의 시군구 : "+ item_mbr_dtl.getSIGNGU_NM());
									if (checkLockAreaNm.contains(item_mbr_dtl.getSIDO_NM()) && checkLockAreaNm.contains(item_mbr_dtl.getSIGNGU_NM())) {
										LOGGER.debug("[회원상세정보]신청가능 - 시도,시군구 비교 둘다 일치");
										isLockArea = false;
										// isLockMsg = "신청가능함~~good";
										//이거 지우고 위에 주석해제
									} else if (checkLockAreaNm.contains(item_mbr_dtl.getSIDO_NM())) {
										LOGGER.debug("[회원상세정보]신청검증 - 시도 일치");
										CodeSetVO mCodeSetVO = new CodeSetVO();
										mCodeSetVO.setCD_MASTER_ID(checkLockAreaCd);
										List<CodeSetVO> list_lock_area_dtl_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
										if (list_lock_area_dtl_grp_cd.size() > 0) {
											for (CodeSetVO item_category : list_lock_area_dtl_grp_cd) {
												if (item_category.getCD_DES().contains(item_mbr_dtl.getSIDO_NM() + " " + item_mbr_dtl.getSIGNGU_NM())) {
													LOGGER.debug("[회원상세정보]신청가능 - 시군구 서브제한 비교 일치 ");
													isLockArea = false;
													// isLockMsg = "신청가능함~~시군구 비교 일치";
													//이거 지우고 위에 주석해제
												}
											}
										} else {
											LOGGER.debug("[회원상세정보]신청검증 - 시군구 서브제한 정보 없는 상태 ");
											CodeSetVO existCodeSetVO = new CodeSetVO();
											existCodeSetVO.setCD_DES(
													item_mbr_dtl.getSIDO_NM() + " " + item_mbr_dtl.getSIGNGU_NM());
											if (parentInfo.getCRS_MBR_CD().equals("CIDN010300")) { 
												// 낚시어선 검증
												existCodeSetVO.setCD_MASTER_ID("CID00008");
											} else if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")) { 
												// 낚시터 검증
												existCodeSetVO.setCD_MASTER_ID("CID00009");
											}
											existCodeSetVO = codeSetService
													.get_codeset_info_chk_mbr_dtl(existCodeSetVO);
											if (existCodeSetVO != null && existCodeSetVO.getCD_ID() != null
													&& existCodeSetVO.getCD_ID().length() != 0) {
												if (existCodeSetVO.getCD_MASTER_ID().equals(checkLockAreaCd)) {
													LOGGER.debug("[회원상세정보]신청가능 - 시군구 서브 비교 데이터 일치 ");
													isLockArea = false;
												}
											} else {
												LOGGER.debug("[회원상세정보]신청가능 - 시군구 서브 비교 데이터 없음 ");
												isLockArea = false;
												// isLockMsg = "신청가능함~~시도 비교
												// 일치";//이거 지우고 위에 주석해제
											}
										}
									}
								} else {
									LOGGER.debug("[회원상세정보] 교육대상그룹코드 조건과 일치하지 않음(" + item_mbr_dtl.getDTL_CD() + ") | 나의 시도 : " + item_mbr_dtl.getSIDO_NM() + " , 나의 시군구 : " + item_mbr_dtl.getSIGNGU_NM());
								}
							}
						} else {// 상세정보가 없는 사용자의 경우 예외처리 (신청가능)
							LOGGER.debug("[회원상세정보]상세정보가 없는 사용자의 경우 예외처리 (신청가능)");
							if (parentInfo.getCRS_MBR_CD().equals("CIDN010300")) { // 낚시어선
																					// 검증
								isLockArea = false;
								// isLockMsg = "신청가능함~~상세정보가 없어 예외처리";//이거 지우고
								// 위에 주석해제
							} else if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")) { // 낚시터
																							// 검증
								// LOGGER.debug("- 낚시터는 예외처리를 허용하지 않음.");
								isLockArea = false;
								// isLockMsg = "신청가능함~~상세정보가 없어 예외처리";//이거 지우고
								// 위에 주석해제
							}
						}
						// End of 회원상세정보 조회 검증
					} else {// 지역제한 적용안함
						if (isAllowMbrDtlCd) {
							LOGGER.debug("[교육신청]지역제한 적용안함 (신청가능)");
							isLockArea = false;
						} else {
							LOGGER.debug("[교육신청]회원상세정보가 교육대상그룹코드 조건과 불일치 하여 지역제한 해제가 불가함");
							if (parentInfo.getCRS_MBR_CD().equals("CIDN010300")) { // 낚시어선
								isLockMsg = "해당 교육은 낚시어선 신고지에 속한 대상자만 신청 하실 수 있습니다.";
							} else if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")) { // 낚시터
								isLockMsg = "해당 교육은 낚시터 신고지에 속한 대상자만 신청 하실 수 있습니다.";
							}
						}
					}
					if (isAllowPrevTargetYear) {/// 이전년도 미이수자의 경우 지역제한 해제
						if (isAllowMbrDtlCd) {
							LOGGER.debug("[교육신청]이전년도 미이수자의 경우 지역제한 해제 ");
							if (parentInfo.getCRS_MBR_CD().equals("CIDN010300")) { // 낚시어선
																					// 검증
								isLockArea = false;
								// isLockMsg = "신청가능함~~상세정보가 없어 예외처리";//이거 지우고
								// 위에 주석해제
							} else if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")) { // 낚시터
																							// 검증
								LOGGER.debug("- 낚시터는 예외처리를 허용하지 않음.");
							}
						} else {
							LOGGER.debug("[교육신청]회원상세정보가 교육대상그룹코드 조건과 불일치 하여 이전년도 미이수자의 경우 지역제한 해제가 불가함");
						}

					}
					if (isLockArea) {
						LOGGER.debug("[교육신청]최종 교육신청불가 ");
						data.put("error", "3");
						data.put("msg", isLockMsg);
						isAllow = false;
					}
					// 2020.04.05 ~ 2020.05.28 까지 주석되어 있었음,금일 해제 - 요청시 다시 주석.
					if (!isAllowCurrentTargetYear) {
						LOGGER.debug("[교육신청]"+currentYear+"년도 이수자는 더 이상 수강신청 불가 ");
						data.put("error", "3");
						data.put("msg", currentYear+"년도 이수자는 더 이상 수강신청을 하실 필요가 없습니다.");
						isAllow = false;
					}
					// End of 신청제한지역설정 확인
				}
				// End of 온라인 교육 신청 가능자 여부 조회
				if (isAllow) {

					StringBuilder log_dscrp = new StringBuilder();
					StringBuilder log_msg = new StringBuilder();

					// 커리큘럼 상세
					parentInfo.setNotUsedPagination(true);
					List<EduCenterMainVO> clildlist = eduCenterMainService.get_educenter_curriculum_dtl_list(parentInfo);

					if (parentInfo.getCRS_GRP_CD().equals("CIDE00000000000")) {// 온라인
																				// 교육
																				// 건
						LRNNG_ST = "0";// 온라인 교육은 대기자로 등록 되도록
					}

					int insertCount = 0;
					int insertDtlCount = 0;

					int HMBR_MAX_TIME = Integer.parseInt(parentInfo.getCRS_TOT_TIME()) + parentInfo.getSUM_TOT_TIME();
					int HMBR_MAX_POINT = Integer.parseInt(parentInfo.getCRS_TOT_POINT())+ parentInfo.getSUM_TOT_POINT();

					MyHistoryVO newMyHistoryVO = new MyHistoryVO();
					newMyHistoryVO.setPURCHASE_CMPLT_ST("1");// 결제(구매) 처리는 현재 보류
					newMyHistoryVO.setCRS_SN(parentInfo.getCRS_SN());
					newMyHistoryVO.setMBR_ID(loginVO.getMBR_ID());
					newMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
					newMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					newMyHistoryVO.setHMBR_MAX_TIME(String.valueOf(HMBR_MAX_TIME));
					newMyHistoryVO.setHMBR_MAX_POINT(String.valueOf(HMBR_MAX_POINT));
					newMyHistoryVO.setCAT_VISIT_SN(myHistoryVO.getCAT_VISIT_SN());
					newMyHistoryVO.setHOPE_AREA(myHistoryVO.getHOPE_AREA());
					newMyHistoryVO.setHOPE_INDST(myHistoryVO.getHOPE_INDST());
					newMyHistoryVO.setLRNNG_ST(LRNNG_ST);

					String HMBR_SN = "";
					// 중복 검증 구간
					{
						boolean isOk = false;
						do {
							HMBR_SN = newMyHistoryVO.getUniqKey("HMBR");
							isOk = eduMyHistoryService.get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(HMBR_SN);
						} while (!isOk);
					}
					// End of 중복 검증 구간
					newMyHistoryVO.setHMBR_SN(HMBR_SN);

					// 메인 커리큘럼 정보 관련 나의이력 생성
					myHistoryService.set_educenter_mbrhstry_reg(newMyHistoryVO);

					// 회원 상세 정보에 기존,신규,재개자 업데이트
					EduMemberVO eduMemberVO = new EduMemberVO();
					eduMemberVO.setMBR_FSHRBT_TYPE(myHistoryVO.getMBR_FSHRBT_TYPE());
					eduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					eduMemberVO.setMBR_ID(loginVO.getMBR_ID());
					eduMemberService.set_edu_member_mod(eduMemberVO);

					log_dscrp.append("[교육신청-수강생추가(회원직접)]");
					log_dscrp.append("[" + parentInfo.getCRS_NM() + "," + parentInfo.getCAT_INS_NM() + "("+ parentInfo.getCRS_STR_DT() + ")]");
					log_dscrp.append("[이름:" + loginVO.getMBR_NM() + "(아이디:" + loginVO.getMBR_ID() + ",교육번호:" + HMBR_SN + ")");
					log_dscrp.append("[교육수강구분 : " + myHistoryVO.getMBR_FSHRBT_TYPE() + "]");

					String domainUrl = request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort();

					// 상세 커리큘럼 정보 만큼 나의상세이력생성
					log_msg.append("(");
					int insertChildCount = 1;
					for (EduCenterMainVO crs_dtl : clildlist) {
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
							EduTrainingDataVO tdata = eduCenterMainService.get_educenter_data_info(eduTrainingDataVO);

							MyHistoryVO newDtlEduMyHistoryVO = new MyHistoryVO();
							String HMBR_DTL_SN = "";
							// 중복 검증 구간
							{
								boolean isOk = false;
								do {
									HMBR_DTL_SN = newDtlEduMyHistoryVO.getUniqKey("HMBRD");
									isOk = eduMyHistoryService.get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(HMBR_DTL_SN);
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
							newDtlEduMyHistoryVO.setMBR_ID(loginVO.getMBR_ID());
							newDtlEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
							newDtlEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							newDtlEduMyHistoryVO.setHMBR_DTL_SN(HMBR_DTL_SN);
							myHistoryService.set_educenter_mbrhstry_reg_dtl(newDtlEduMyHistoryVO);
							log_msg.append("HMBR_DTL_SN : " + HMBR_DTL_SN + ",");

							//외부 동영상 URL 생성
							String eduUrl = domainUrl+"/educenter/mbrhstry/external/play.do?key="+EgovFileScrty.encode(HMBR_DTL_SN);
							externalVideoUrl.add(eduUrl);
							//End of 외부 동영상 URL 생성

							insertSubCount++;
							insertDtlCount++;
						}
						insertChildCount++;
					}
					log_msg.append(")");

					log_dscrp.append("]");

					// 메인 커리큘럼에 회원 카운트 증가
					eduCenterMainService.set_educenter_curriculum_mbr_cur_cnt_up(parentInfo);

					{// 연도별이수내역에 추가 또는 업데이트

						String TRGT_YEAR = mPublicUtils.changePatternString(
								parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy");
						log_dscrp.append("|" + TRGT_YEAR + "연도별이수내역추가");
						EduMemberVO chkTargetEduMemberVO = new EduMemberVO();
						chkTargetEduMemberVO.setMBR_ID(loginVO.getMBR_ID());
						chkTargetEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
						chkTargetEduMemberVO.setUSE_AT("Y");
						chkTargetEduMemberVO.setNotUsedPagination(true);
						List<EduMemberVO> chkTargetEduList = eduMemberService
								.get_edu_member_target_list(chkTargetEduMemberVO);
						if (chkTargetEduList == null || chkTargetEduList.size() == 0) {
							log_dscrp.append("|" + TRGT_YEAR + "년도 대상자추가완료");
							EduMemberVO newEduMemberVO = new EduMemberVO();
							newEduMemberVO.setMBR_ID(loginVO.getMBR_ID());
							newEduMemberVO.setMBR_CD(loginVO.getMBR_CD());
							newEduMemberVO.setMBR_NM(loginVO.getMBR_NM());
							newEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
							newEduMemberVO.setREG_MBR_ID(loginVO.getMBR_ID());
							newEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							newEduMemberVO.setCRS_SN(parentInfo.getCRS_SN());
							newEduMemberVO.setHMBR_SN(HMBR_SN);
							eduMemberService.set_edu_member_target_reg(newEduMemberVO);
						} else {
							log_dscrp.append("|" + TRGT_YEAR + "년도 대상자로이미등록되어있음");

							boolean isExistTargetBean = false;
							EduMemberVO updEduMemberVO = new EduMemberVO();
							updEduMemberVO.setMBR_ID(loginVO.getMBR_ID());
							updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
							updEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							updEduMemberVO.setCRS_SN_NULL("Y");
							List<EduMemberVO> chkTargetEduBeanList = eduMemberService
									.get_edu_member_target_all_list(updEduMemberVO);
							if (chkTargetEduBeanList == null || chkTargetEduBeanList.size() == 0) {
								isExistTargetBean = false;
							} else {
								isExistTargetBean = true;
							}
							if (isExistTargetBean) {
								log_dscrp.append("|" + TRGT_YEAR + "년도 빈연도별수강내역에업데이트");

								updEduMemberVO.setMBR_ID(loginVO.getMBR_ID());
								updEduMemberVO.setMBR_NM(loginVO.getMBR_NM());
								updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
								updEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
								updEduMemberVO.setCRS_SN_NULL("Y");// CRS_SN
																	// null 일때
								updEduMemberVO.setCRS_SN_NOT_NULL("");// CRS_SN
																		// 일때
								updEduMemberVO.setHMBR_SN_NULL("Y");// HMBR_SN
																	// null 일때
								updEduMemberVO.setCRS_SN(parentInfo.getCRS_SN());
								updEduMemberVO.setHMBR_SN(HMBR_SN);
								eduMemberService.set_edu_member_target_mod(updEduMemberVO);
							} else {
								log_dscrp.append("|" + TRGT_YEAR + "년도 연도별수강내역에신규추가");

								updEduMemberVO.setMBR_ID(loginVO.getMBR_ID());
								updEduMemberVO.setMBR_NM(loginVO.getMBR_NM());
								updEduMemberVO.setMBR_CD(loginVO.getMBR_CD());
								updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
								updEduMemberVO.setREG_MBR_ID(loginVO.getMBR_ID());
								updEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
								updEduMemberVO.setCRS_SN(parentInfo.getCRS_SN());
								updEduMemberVO.setHMBR_SN(HMBR_SN);
								eduMemberService.set_edu_member_target_reg(updEduMemberVO);
							}
						}
					}
					// 교육 알림 신청
					{
						if (parentInfo.getCRS_MBR_CD().equals("CIDN010300")
								&& (myHistoryVO.getREQ_YN() != null && myHistoryVO.getREQ_YN().equals("Y"))) { // 낚시어선에
																												// 문자신청

							// 회원정보조회
							EduCenterMemberVO eduCenterMemberVO = new EduCenterMemberVO();
							eduCenterMemberVO.setMBR_ID(loginVO.getMBR_ID());
							eduCenterMemberVO = eduCenterMemberService.get_edu_member_info(eduCenterMemberVO);
							model.addAttribute("info_mbr", eduCenterMemberVO);
							eduCenterMemberVO.setDTL_CD(parentInfo.getCRS_MBR_CD());
							model.addAttribute("list_mbr_dtl",
									eduCenterMemberService.get_edu_member_dtl_all_list(eduCenterMemberVO));

							String LICENSE_CD = myHistoryVO.getDTL_LICENSE_CD();
							if (LICENSE_CD.equals("CIDL00001") || LICENSE_CD.equals("CIDL00002")) { // 개인,
																									// 법인사업자
								// 문자 안내 신청
								EduSmsRequstVO eduSmsRequstVO = new EduSmsRequstVO();
								eduSmsRequstVO.setESR_MBR_ID(loginVO.getMBR_ID());
								eduSmsRequstVO.setESR_MBR_HP(eduCenterMemberVO.getMBR_HP());
								eduSmsRequstVO.setESR_MBR_NM(eduCenterMemberVO.getMBR_NM());
								eduSmsRequstVO.setESR_DTL_CD(parentInfo.getCRS_MBR_CD());
								eduSmsRequstVO.setESR_DTL_LICENSE_CD(LICENSE_CD);
								eduSmsRequstVO.setESR_CRS_SN(myHistoryVO.getCRS_SN());
								eduSmsRequstVO.setESR_SIDO_CD(eduCenterMemberVO.getMBR_SIDO_CD());
								eduSmsRequstVO.setESR_SIGNGU_CD(eduCenterMemberVO.getMBR_SIGNGU_CD());
								eduSmsRequstVO.setESR_REG_MBR_ID(loginVO.getMBR_ID());
								eduSmsRequstVO.setESR_UPD_MBR_ID(loginVO.getMBR_ID());
								eduSmsRequstVO.setESR_TRGET(myHistoryVO.getREQ_YN());
								eduCenterMainService.set_educenter_sms_request(eduSmsRequstVO);

								log_dscrp.append("[교육 알림 문자 신청 : " + eduCenterMemberVO.getMBR_HP() + " ]");
							}
						}

					}
					// 사용자사유로그남기기
					{
						MyHistoryVO realMyHistoryVO = myHistoryService.get_educenter_mbrhstry_info(newMyHistoryVO);
						logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB", "신청", "[회원본인]",
								realMyHistoryVO.getMBR_ID(), realMyHistoryVO.getMBR_NM(), realMyHistoryVO, null,
								loginVO, request);
					}

					// SMS자동발송(즉시)
					{
						String str_crs_dt = mPublicUtils.changePatternString(
								parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
						String end_crs_dt = mPublicUtils.changePatternString(
								parentInfo.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
						String str_crs_str_time = mPublicUtils.changePatternString(
								parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "HH:mm");
						String str_crs_end_time = mPublicUtils.changePatternString(
								parentInfo.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "HH:mm");

						String eduTime = "교육일시 : " + str_crs_dt + " " + str_crs_str_time + " ~ " + end_crs_dt + " "
								+ str_crs_end_time;
						/*
						 * if(parentInfo.getCRS_GRP_CD().equals(
						 * "CIDE00000000000")){ //온라인 교육이면 String today =
						 * mPublicUtils.currentTime("yyyy-MM-dd"); eduTime =
						 * "교육일시 : " + today + " ~ 상시(집합교육 재개 시까지 한시 운영)"; }
						 */

						String add_msg = "";
						String ment_msg = "";
						SmsMentVO smsMentVO = new SmsMentVO();

						if (parentInfo.getTYPE_GB().equals("offline")) {// 오프라인교육
							add_msg = "낚시전문교육 수강신청 접수완료 안내(" + parentInfo.getCRS_NM() + ")" + "\n\n" + eduTime + "\n"
									+ "교육장소 : " + parentInfo.getCRS_PLACE() + "\n" + "교육장주소지 : " + parentInfo.getCRS_ADDR()
									+ "\n\n";
							if (parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) { 
								// 낚시어선 신규,재개자교육
								smsMentVO.setSMS_MENT_SN("32");
							} else {
								if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
									smsMentVO.setSMS_MENT_SN("34");
								} else {
									smsMentVO.setSMS_MENT_SN("34");
								}
							}

						} else {
							add_msg = "낚시전문교육 수강신청 접수완료 안내(" + parentInfo.getCRS_NM() + ")" + "\n\n" + eduTime + "\n"
									+ "교육명 : " + parentInfo.getCRS_PLACE() + "\n" + "교육사이트주소 : " + parentInfo.getCRS_ADDR()
									+ "\n\n";

							if (parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) { 
								// 낚시어선 신규,재개자교육
								smsMentVO.setSMS_MENT_SN("32");
							} else {
								if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
									smsMentVO.setSMS_MENT_SN("4");
								} else {
									smsMentVO.setSMS_MENT_SN("5");
								}
							}
							smsMentVO.setSMS_MENT_DTL_CD(parentInfo.getCRS_MBR_CD());// 낚시터=CIDN010200,낚시어선=CIDN010300
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
						newSmsSendVO.setMBR_ID(loginVO.getMBR_ID());
						newSmsSendVO.setSMS_MENT_DTL_CD(parentInfo.getCRS_MBR_CD());
						newSmsSendVO.setMSG(add_msg + '\n' + ment_msg);
						// newSmsSendVO.setSTAT();//예약발송일때만5
						newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));// 발신번호
						newSmsSendVO.setR_PHONE(loginVO.getMBR_HP());
						newSmsSendVO.setSUBMSG("낚시전문교육 수강신청 접수완료 안내");
						newSmsSendVO.setIMG_CNT(0);
						newSmsSendVO.setIMG_PATH("");
						newSmsSendVO.setREG_MBR_ID(loginVO.getMBR_ID());
						newSmsSendVO.setUPD_MBR_ID(loginVO.getMBR_ID());
						newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
						smsManagerService.sendToMessage(newSmsSendVO);
						log_dscrp.append("|신청완료문자발송");
						log_msg.append("|sms:" + add_msg + '\n' + ment_msg);

						if(parentInfo.getTYPE_GB().equals("online")){
							if(parentInfo.getEDU_APLY_NTCN_SNDNG_YN().equals("Y")){
								// 카카오 알림톡 발송
								KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
								kakaoAlimTalkVO.setNTCN_TRSM_TEL(loginVO.getMBR_HP());//연락처
								//(알림톡필수)
								String allEduUrl = "";
								if(parentInfo.getCRS_MBR_CD().equals("CIDN010300")){
									if(parentInfo.getCRS_YEAR().equals("2023")){
										allEduUrl += externalVideoUrl.get(0) + ",";
										allEduUrl += externalVideoUrl.get(1) + ",";
										allEduUrl += externalVideoUrl.get(2) + ",";
										allEduUrl += externalVideoUrl.get(3) + ",";
										kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000019");//낚시어선 전문교육신청
									}else if(parentInfo.getCRS_YEAR().equals("2024")){//24년 교육신청 카톡 자동발송 김현태추가
										//System.out.println("externalVideoUrl1="+externalVideoUrl);
										allEduUrl += externalVideoUrl.get(0) + ",";
										allEduUrl += externalVideoUrl.get(1) + ",";
										allEduUrl += externalVideoUrl.get(2) + ",";
										allEduUrl += externalVideoUrl.get(3) + ",";
										kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000032");//낚시어선 전문교육신청
									}
									else {
										//System.out.println("externalVideoUrl="+externalVideoUrl);
										allEduUrl += externalVideoUrl.get(0) + ",";
										allEduUrl += externalVideoUrl.get(1) + ",";
										allEduUrl += externalVideoUrl.get(2) + ",";
										allEduUrl += externalVideoUrl.get(3) + ",";
										//allEduUrl += externalVideoUrl.get(4) + ",";
										//allEduUrl += externalVideoUrl.get(5) + ",";
										kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000001");//낚시어선 전문교육신청
									}
								} else if(parentInfo.getCRS_MBR_CD().equals("CIDN010200")){
									allEduUrl += externalVideoUrl.get(0) + ",";
									allEduUrl += externalVideoUrl.get(1) + ",";
									allEduUrl += externalVideoUrl.get(2) + ",";
									allEduUrl += externalVideoUrl.get(3) + ",";
									if(parentInfo.getCRS_YEAR().equals("2023"))
										kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000021");//낚시터 전문교육신청
									else if(parentInfo.getCRS_YEAR().equals("2024")){//24년 교육신청 카톡 자동발송 김현태추가
										//System.out.println("externalVideoUrl2="+externalVideoUrl);
										kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000034");
									}//낚시터 전문교육신청
									else 
										kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000003");//낚시터 전문교육신청
								}
								kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(parentInfo.getCRS_MBR_CD());//낚시터 전문교육신청
								
								String surveykey = EgovFileScrty.encode(HMBR_SN+","+parentInfo.getCRS_SN());
	//							kakaoAlimTalkVO.setSurveyUrl(domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey);// 설문조사 url
								allEduUrl += domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey;
								kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
								kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);
								kakaoAlimTalkVO.setREG_ID(loginVO.getMBR_ID());
	//							JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
								kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
								//End of 카카오 알림톡 발송
							}
						}
					}

					data.put("error", "0");
					data.put("msg", "수강생이 추가되었습니다.");
					try {
						/**
						 * LOG RECORED (로그기록)
						 */
						LogRecordVO mEduLogRecordVO = new LogRecordVO();
						mEduLogRecordVO.setLOG_MSG(log_msg.toString());
						mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
						mEduLogRecordVO.setTBL_INF("EDU_MBR_HSTRY_TB (EDU_MBR_HSTRY_DTL_TB)");
						mEduLogRecordVO.setTBL_SN("교육신청");
						mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
						mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
						mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
						logRecordService.set_log_data(mEduLogRecordVO, request);
					} catch (Exception e) {
						LOGGER.debug("[fail log record] " + e.toString());
					}
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

	// 교육센터-교육신청-신청 취소 로직 처리 ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/cancel.do", method = RequestMethod.POST)
	public @ResponseBody String educenter_trnng_delete_act(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {

		return null;
	}
	
	// 교육센터-교육신청-신청 취소 로직 처리 ------------------------------------------------
	///educenter/trnng/cancel2.do
	//String educenter_trnng_delete_act2         2지우기
	@RequestMapping(value = "/educenter/trnng/cancel2.do", method = RequestMethod.POST)
	public @ResponseBody String educenter_trnng_delete_act2(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model)
			throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();
		

		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			if (loginVO == null || loginVO.getMBR_ID() == null) {
				// model.addAttribute("message", "로그인이 필요한 서비스입니다.");
				data.put("error", "1");
				data.put("msg", "로그인이 필요한 서비스입니다.");
			} else {
				boolean isAllow = true;
				
				
				//김현태 추가 취약점 조치
				if (loginVO.getMBR_LV_ID()!= "0") {
					data.put("error", "1");
					data.put("msg", "잘못된 접근입니다.");
					return null;
				}		

				//취약점 end
				
				// 커리큘럼 메인
				String CRS_SN = myHistoryVO.getCRS_SN();
				EduCenterMainVO parentInfo = new EduCenterMainVO();
				parentInfo.setCRS_SN(CRS_SN);
				parentInfo = eduCenterMainService.get_educenter_curriculum_info(parentInfo);
				// 커리큘럼 존재 유무 판단
				if (parentInfo == null || parentInfo.getCRS_SN() == null || parentInfo.getCRS_SN().length() == 0) {
					data.put("error", "1");
					data.put("msg", "비정상적인 접근방법 입니다.");
					isAllow = false;
				}
				// 수강내역 등록 내역 확인
				MyHistoryVO chkMyHistoryVO = new MyHistoryVO();
				chkMyHistoryVO.setCRS_SN(parentInfo.getCRS_SN());
				chkMyHistoryVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
				chkMyHistoryVO.setMBR_ID(loginVO.getMBR_ID());
				chkMyHistoryVO = myHistoryService.get_educenter_mbrhstry_info(chkMyHistoryVO);
				if (chkMyHistoryVO == null || chkMyHistoryVO.getHMBR_SN() == null || chkMyHistoryVO.getHMBR_SN().length() == 0) {
					data.put("error", "2");
					data.put("msg", "해당 교육에 신청정보가 없습니다.");
					isAllow = false;
				}
				// End 수강내역 등록 내역 확인
				if (isAllow) {

					StringBuilder log_dscrp = new StringBuilder();
					StringBuilder log_msg = new StringBuilder();

					log_dscrp.append("[교육취소-수강생취소(회원직접)-삭제됨]");
					log_dscrp.append("[" + parentInfo.getCRS_NM() + "," + parentInfo.getCAT_INS_NM() + "("+ parentInfo.getCRS_STR_DT() + ")]");
					log_dscrp.append("[이름:" + chkMyHistoryVO.getMBR_NM() + "(아이디:" + chkMyHistoryVO.getMBR_ID() + ",교육번호:" + chkMyHistoryVO.getHMBR_SN() + ")");

					String USE_ST = chkMyHistoryVO.getUSE_ST();

					// 사유 입력으로 바로 삭제 처리를 위한 구간, DEL_ST = "1"; 주석시 이 부분도 같이 주석되어야
					// 함.
					if (USE_ST.equals("1")) {
						// 삭제 단계 프리패스로 인한 커리큘럼 정보 업데이트
						EduCenterMainVO eduCenterMainVO = new EduCenterMainVO();
						eduCenterMainVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
						eduCenterMainService.set_educenter_curriculum_mbr_cur_cnt_down(eduCenterMainVO);
						log_dscrp.append("|수강신청인원감소처리");
						// 이수증 취소 처리
						if (chkMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) {
							eduCenterMainService.set_educenter_curriculum_mbr_cmplt_cnt_down(eduCenterMainVO);
							log_dscrp.append("|이수완료인원감소처리");
							EduCertificateVO eduCertificateVO = new EduCertificateVO();
							eduCertificateVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
							eduCertificateVO.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
							eduCertificateVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
							EduCertificateVO existInfo = eduCertificateService
									.get_edu_certificate_info(eduCertificateVO);
							if (existInfo == null || existInfo.getCRTF_SN() == null
									|| existInfo.getCRTF_SN().length() == 0) {
								log_dscrp.append("|발급된이수증취소처리실패");
							} else {
								// log_dscrp.append("|발급된이수증취소처리성공");
								// eduCertificateService.set_edu_certificate_mod_use_lock(existInfo);
								log_dscrp.append("|발급된이수증취소처리");
								eduCertificateService.remove_edu_certificate(existInfo);
								eduCertificateService.remove_edu_certificate_dtl(existInfo);
							}
						}
					}
					// 연도별이수내역에서 제거
					try {
						String TRGT_YEAR = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy");
						log_dscrp.append("|" + TRGT_YEAR + "연도별이수내역제거");
						log_msg.append("|" + TRGT_YEAR + "연도별이수내역제거");
						/*
						 * EduCenterMemberVO delEduCenterMemberVO = new
						 * EduCenterMemberVO();
						 * delEduCenterMemberVO.setCRS_SN(chkMyHistoryVO.
						 * getCRS_SN());
						 * delEduCenterMemberVO.setHMBR_SN(chkMyHistoryVO.
						 * getHMBR_SN());
						 * delEduCenterMemberVO.setTRGT_YEAR(TRGT_YEAR);
						 * delEduCenterMemberVO.setMBR_ID(chkMyHistoryVO.
						 * getMBR_ID());
						 * eduCenterMemberService.set_edu_member_target_clear(
						 * delEduCenterMemberVO);
						 */
						EduMemberVO delEduMemberVO = new EduMemberVO();
						delEduMemberVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
						delEduMemberVO.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
						delEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
						delEduMemberVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
						eduMemberService.remove_edu_member_target(delEduMemberVO);
						EduMemberVO chkEduMemberVO = new EduMemberVO();
						chkEduMemberVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
						chkEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
						List<EduMemberVO> chkTargetEduBeanList = eduMemberService
								.get_edu_member_target_all_list(chkEduMemberVO);
						if (chkTargetEduBeanList == null || chkTargetEduBeanList.size() == 0) {
							log_dscrp.append("-빈연도별이수내역생성");
							log_msg.append("|빈연도별이수내역생성");
							chkEduMemberVO.setMBR_CD(chkMyHistoryVO.getMBR_ID());
							chkEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
							chkEduMemberVO.setREG_MBR_ID("admin");
							chkEduMemberVO.setUPD_MBR_ID("admin");
							eduMemberService.set_edu_member_target_reg(chkEduMemberVO);
						}
					} catch (Exception e) {
						LOGGER.debug("[fail][연도별이수내역제거] " + e.toString());
					}
					//
					log_dscrp.append("]");

					myHistoryService.set_educenter_mbrhstry_cancel(chkMyHistoryVO);

					// 사용자사유로그남기기
					{
						MyHistoryVO realMyHistoryVO = myHistoryService.get_educenter_mbrhstry_info(chkMyHistoryVO);
						logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB", "취소", "[회원본인]삭제", chkMyHistoryVO.getMBR_ID(), chkMyHistoryVO.getMBR_NM(), chkMyHistoryVO, realMyHistoryVO, loginVO, request);
					}

					data.put("error", "0");
					data.put("msg", "수강신청이 취소 되었습니다.");
					try {
						/**
						 * LOG RECORED (로그기록)
						 */
						LogRecordVO mEduLogRecordVO = new LogRecordVO();
						log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkMyHistoryVO));
						mEduLogRecordVO.setLOG_MSG(log_msg.toString());
						mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
						mEduLogRecordVO.setTBL_INF("EDU_MBR_HSTRY_TB (EDU_MBR_HSTRY_DTL_TB)");
						mEduLogRecordVO.setTBL_SN("교육취소");
						mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
						mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
						mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
						logRecordService.set_log_data(mEduLogRecordVO, request);
					} catch (Exception e) {
						LOGGER.debug("[fail log record] " + e.toString());
					}
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
	

	// 교육센터-교육사전알림-신청 동의하기 페이지 ------------------------------------------------
	@RequestMapping(value = "/educenter/rmndr/agree{addWebLink}.do")
	public String educenter_rmndr_agree(@PathVariable("addWebLink") String addWebLink, HttpServletRequest request,
			RedirectAttributes redirectAttributes, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || loginVO.getMBR_ID() == null) {

			// 본인 인증 임시 저장 정보 호출
			String ajaxMoveUrlForChkLoginAuthInfo = (String) request.getSession()
					.getAttribute("ajaxMoveUrlForChkLoginAuthInfo");
			if (ajaxMoveUrlForChkLoginAuthInfo != null && ajaxMoveUrlForChkLoginAuthInfo.length() != 0 && !ajaxMoveUrlForChkLoginAuthInfo.equals("")) {
				model.addAttribute("IS_14AGE_UNDER", (String) request.getSession().getAttribute("isChkLoginAuthInfo14ageUnder"));
			}
			// End of 본인 인증 임시 저장 정보 호출

			// 본인인증관련
			model.addAttribute("kcbokcert_cpid", propertiesService.getString("KcbOkCert.cpid"));
			model.addAttribute("kcbokcert_licensepath", propertiesService.getString("KcbOkCert.licensePath"));
			model.addAttribute("kcbokcert_sitenm", propertiesService.getString("KcbOkCert.siteNm"));
			model.addAttribute("kcbokcert_siteurl", propertiesService.getString("KcbOkCert.siteUrl"));
			// End of 본인인증관련

		} else { // 비회원을 위한 서비스
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "비회원을 위한 서비스 입니다.<br>회원은 교육신청 메뉴를 이용해주세요.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/index.do";
		}

		model.addAttribute("addWebLink", addWebLink);
		return "educenter/rmndr/agree";
	}

	// 교육센터-교육사전알림-신청 동의하기 페이지 - 모바일
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/m/rmndr/agree{addWebLink}.do")
	public String educenter_rmndr_agree_mobile(@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null || loginVO.getMBR_ID() == null) {

			// 본인 인증 임시 저장 정보 호출
			String ajaxMoveUrlForChkLoginAuthInfo = (String) request.getSession()
					.getAttribute("ajaxMoveUrlForChkLoginAuthInfo");
			if (ajaxMoveUrlForChkLoginAuthInfo != null && ajaxMoveUrlForChkLoginAuthInfo.length() != 0 && !ajaxMoveUrlForChkLoginAuthInfo.equals("")) {
				model.addAttribute("IS_14AGE_UNDER",(String) request.getSession().getAttribute("isChkLoginAuthInfo14ageUnder"));
			}
			// End of 본인 인증 임시 저장 정보 호출

			// 본인인증관련
			model.addAttribute("kcbokcert_cpid", propertiesService.getString("KcbOkCert.cpid"));
			model.addAttribute("kcbokcert_licensepath", propertiesService.getString("KcbOkCert.licensePath"));
			model.addAttribute("kcbokcert_sitenm", propertiesService.getString("KcbOkCert.siteNm"));
			model.addAttribute("kcbokcert_siteurl", propertiesService.getString("KcbOkCert.siteUrl"));
			// End of 본인인증관련

		} else { // 비회원을 위한 서비스
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "비회원을 위한 서비스 입니다.<br>회원은 교육신청 메뉴를 이용해주세요.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/m/index.do";
		}

		model.addAttribute("addWebLink", addWebLink);
		return "educenter/mobile/rmndr/agree";
	}

	// 교육센터-교육수강신청(검토)-신청서 작성페이지
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/rmndr/write{addWebLink}.do")
	public String educenter_rmndr_write(@RequestParam(value = "agree", required = false) String agree,
			@PathVariable("addWebLink") String addWebLink, RedirectAttributes redirectAttributes,
			HttpServletRequest request, ModelMap model) throws Exception {

		if (agree == null || (!agree.equals("1") && !agree.equals("2"))) {
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "약관 동의는 필수입니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/rmndr/agree" + addWebLink + ".do";
		}

		if (addWebLink.equals("Ship")) {
			model.addAttribute("CRS_MBR_CD", "CIDN010300");
		} else if (addWebLink.equals("House")) {
			model.addAttribute("CRS_MBR_CD", "CIDN010200");
		} else if (addWebLink.equals("Resmpt")) {// 낚시어선 신규,재개자 교육
			model.addAttribute("CRS_MBR_CD", "CIDN010300");
			model.addAttribute("CRS_GRP_CD", "CIDE16119012309");// 교육그룹 - 전국
		}

		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 사업자구분코드
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00006");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_license_se_cd", list_position_cd);
		}
		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_address_cd", list_address_cd);
		}

		// 본인 인증 임시 저장 정보 호출
		String ajaxMoveUrlForChkLoginAuthInfo = (String) request.getSession()
				.getAttribute("ajaxMoveUrlForChkLoginAuthInfo");
		if (ajaxMoveUrlForChkLoginAuthInfo != null && ajaxMoveUrlForChkLoginAuthInfo.length() != 0&& !ajaxMoveUrlForChkLoginAuthInfo.equals("")) {
			String IS_14AGE_UNDER = (String) request.getSession().getAttribute("isChkLoginAuthInfo14ageUnder");
			model.addAttribute("IS_14AGE_UNDER", IS_14AGE_UNDER);
			model.addAttribute("MBR_BIRTH", (String) request.getSession().getAttribute("loginActAuthTempDataBirthDay"));
			model.addAttribute("MBR_NM", (String) request.getSession().getAttribute("loginActAuthTempDataName"));
			model.addAttribute("MBR_HP", (String) request.getSession().getAttribute("loginActAuthTempDataPhoneNo"));
			if (IS_14AGE_UNDER.equals("Y")) {
				// 법정대리인의 본인인증 완료여부 확인
				String AGE14_UNDER_MDL_TKN = (String) request.getSession().getAttribute("AGE14_UNDER_MDL_TKN");
				if (AGE14_UNDER_MDL_TKN != null && AGE14_UNDER_MDL_TKN.length() != 0) {
					request.getSession().removeAttribute("AGE14_UNDER_MDL_TKN");
					// 법정대리인 본인인증정보 전달
					request.getSession().setAttribute("PARNTS_MBR_BIRTH", (String) request.getParameter("birthDay"));
					request.getSession().setAttribute("PARNTS_MBR_NM", (String) request.getParameter("name"));
					request.getSession().setAttribute("PARNTS_MBR_HP", (String) request.getParameter("phoneNo"));
					request.getSession().setAttribute("PARNTS_MBR_RELATIONSHIP",(String) request.getParameter("parent_relationship"));
					// End of 법정대리인 본인인증정보 전달
				} else {
					Map<String, Object> postMap = new HashMap<String, Object>();
					postMap.put("message", "법정대리인의 본인인증이 완료되지 않았습니다.");
					redirectAttributes.addFlashAttribute("alert_data", postMap);
					return "redirect:/educenter/rmndr/agree" + addWebLink + ".do";
				}
				// End of 법정대리인의 본인인증 완료여부 확인
			}
			request.getSession().setAttribute("ajaxMoveUrlForChkLoginAuthInfo", "");
			// request.getSession().removeAttribute("isChkLoginAuthInfo14ageUnder");//가입
			// 후에 보호자 본인인증 확인 후 삭제
			// request.getSession().removeAttribute("loginActAuthTempDataBirthDay");//실명인증
			// 우회방지 - 처리부에서 세션 삭제
			// request.getSession().removeAttribute("loginActAuthTempDataName");//실명인증
			// 우회방지 - 처리부에서 세션 삭제
			// request.getSession().removeAttribute("loginActAuthTempDataPhoneNo");//실명인증
			// 우회방지 - 처리부에서 세션 삭제
		} else {
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "신청을 위한 본인인증이 완료되지 않았습니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/index.do";
		}
		// End of 본인 인증 임시 저장 정보 호출

		// model.addAttribute("agree", agree);
		String INDVDL_INFO_ST = request.getParameter("INDVDL_INFO_ST");
		model.addAttribute("INDVDL_INFO_ST", INDVDL_INFO_ST);
		return "educenter/rmndr/write_" + addWebLink.toLowerCase();
	}

	// 교육센터-교육수강신청(검토)-신청서 작성페이지
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/m/rmndr/write{addWebLink}.do")
	public String educenter_rmndr_write_mobile(@RequestParam(value = "agree", required = false) String agree,
			@PathVariable("addWebLink") String addWebLink, RedirectAttributes redirectAttributes,
			HttpServletRequest request, ModelMap model) throws Exception {

		if (agree == null || !agree.equals("1") && !agree.equals("2")) {
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "약관 동의는 필수입니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/m/rmndr/agree" + addWebLink + ".do";
		}

		if (addWebLink.equals("Ship")) {
			model.addAttribute("CRS_MBR_CD", "CIDN010300");
		} else if (addWebLink.equals("House")) {
			model.addAttribute("CRS_MBR_CD", "CIDN010200");
		} else if (addWebLink.equals("Resmpt")) {// 낚시어선 신규,재개자 교육
			model.addAttribute("CRS_MBR_CD", "CIDN010300");
			model.addAttribute("CRS_GRP_CD", "CIDE16119012309");// 교육그룹 - 전국
		}

		// 교육그룹 코드 조회(활성화)
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00005");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_edu_grp_cd", list_edu_grp_cd);
		}
		// 사업자구분코드
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00006");
			mCodeSetVO.setHIDE_AT("N");
			List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_license_se_cd", list_position_cd);
		}
		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_address_cd", list_address_cd);
		}

		// 본인 인증 임시 저장 정보 호출
		String ajaxMoveUrlForChkLoginAuthInfo = (String) request.getSession()
				.getAttribute("ajaxMoveUrlForChkLoginAuthInfo");
		if (ajaxMoveUrlForChkLoginAuthInfo != null && ajaxMoveUrlForChkLoginAuthInfo.length() != 0
				&& !ajaxMoveUrlForChkLoginAuthInfo.equals("")) {
			String IS_14AGE_UNDER = (String) request.getSession().getAttribute("isChkLoginAuthInfo14ageUnder");
			model.addAttribute("IS_14AGE_UNDER", IS_14AGE_UNDER);
			model.addAttribute("MBR_BIRTH", (String) request.getSession().getAttribute("loginActAuthTempDataBirthDay"));
			model.addAttribute("MBR_NM", (String) request.getSession().getAttribute("loginActAuthTempDataName"));
			model.addAttribute("MBR_HP", (String) request.getSession().getAttribute("loginActAuthTempDataPhoneNo"));
			if (IS_14AGE_UNDER.equals("Y")) {
				// 법정대리인의 본인인증 완료여부 확인
				String AGE14_UNDER_MDL_TKN = (String) request.getSession().getAttribute("AGE14_UNDER_MDL_TKN");
				if (AGE14_UNDER_MDL_TKN != null && AGE14_UNDER_MDL_TKN.length() != 0) {
					request.getSession().removeAttribute("AGE14_UNDER_MDL_TKN");
					// 법정대리인 본인인증정보 전달
					request.getSession().setAttribute("PARNTS_MBR_BIRTH", (String) request.getParameter("birthDay"));
					request.getSession().setAttribute("PARNTS_MBR_NM", (String) request.getParameter("name"));
					request.getSession().setAttribute("PARNTS_MBR_HP", (String) request.getParameter("phoneNo"));
					request.getSession().setAttribute("PARNTS_MBR_RELATIONSHIP",(String) request.getParameter("parent_relationship"));
					// End of 법정대리인 본인인증정보 전달
				} else {
					Map<String, Object> postMap = new HashMap<String, Object>();
					postMap.put("message", "법정대리인의 본인인증이 완료되지 않았습니다.");
					redirectAttributes.addFlashAttribute("alert_data", postMap);
					return "redirect:/educenter/m/rmndr/agree" + addWebLink + ".do";
				}
				// End of 법정대리인의 본인인증 완료여부 확인
			}
			request.getSession().setAttribute("ajaxMoveUrlForChkLoginAuthInfo", "");
			// request.getSession().removeAttribute("isChkLoginAuthInfo14ageUnder");//가입
			// 후에 보호자 본인인증 확인 후 삭제
			// request.getSession().removeAttribute("loginActAuthTempDataBirthDay");//실명인증
			// 우회방지 - 처리부에서 세션 삭제
			// request.getSession().removeAttribute("loginActAuthTempDataName");//실명인증
			// 우회방지 - 처리부에서 세션 삭제
			// request.getSession().removeAttribute("loginActAuthTempDataPhoneNo");//실명인증
			// 우회방지 - 처리부에서 세션 삭제
		} else {
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "신청을 위한 본인인증이 완료되지 않았습니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/index.do";
		}
		// End of 본인 인증 임시 저장 정보 호출

		// model.addAttribute("agree", agree);
		String INDVDL_INFO_ST = request.getParameter("INDVDL_INFO_ST");
		model.addAttribute("INDVDL_INFO_ST", INDVDL_INFO_ST);
		return "educenter/mobile/rmndr/write_" + addWebLink.toLowerCase();
	}

	// 교육센터-교육수강신청(검토)-신청정보 존재 여부 확인
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/rmndr/checkInfo.do", method = RequestMethod.POST)
	public @ResponseBody ModelAndView educenter_rmndr_check_info(
			@ModelAttribute("eduCenterMbrRemindersVO") EduCenterMbrRemindersVO eduCenterMbrRemindersVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model,
			HttpServletResponse response) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();

		ModelAndView mModelAndView = new ModelAndView();
		if (mPublicUtils.isMobileDevice(request))
			mModelAndView.setViewName("educenter/mobile/popup/view_alert");
		else
			mModelAndView.setViewName("educenter/popup/view_alert");

		String rmndr_mbr_nm = eduCenterMbrRemindersVO.getRMNDR_MBR_NM();
		String rmndr_mbr_hp = eduCenterMbrRemindersVO.getRMNDR_MBR_HP();
		String rmndr_mbr_birth = eduCenterMbrRemindersVO.getRMNDR_MBR_BIRTH();
		eduCenterMbrRemindersVO.setCMPLT_ST("N");//처리 완료자에 한하여 예외

		List<EduCenterMbrRemindersVO> list_get_educenter_mbrhstry_info = eduCenterMainService.get_educenter_rmndr_info_list(eduCenterMbrRemindersVO);
		if (list_get_educenter_mbrhstry_info.size() != 0) {
			for (int i = 0; i < list_get_educenter_mbrhstry_info.size(); i++) {
				if (eduCenterMbrRemindersVO.getRMNDR_CRS_SN()
						.equals(list_get_educenter_mbrhstry_info.get(i).getCRS_SN())) {
					mModelAndView.addObject("alertType", "alert");
					mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
					mModelAndView.addObject("msg", "이미 수강중 이거나 신청된 교육과정입니다.");
					return mModelAndView;
				}
			}
			mModelAndView.addObject("alertType", "confirm2");
			mModelAndView.addObject("info", list_get_educenter_mbrhstry_info);
			mModelAndView.addObject("msg", "이미 수강중 이거나 신청된 교육과정입니다.<br>이전 신청 정보 삭제 후 새로 신청하시겠습니까?");
			return mModelAndView;
		} else {
			// 이미 신청된 다른 교육과정이 존재하지않습니다.

		}

		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print("");
		return null;
	}

	// 교육센터-교육수강신청(검토)-신청서 기존 신청서 삭제
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/trnng/remove_act.do", method = RequestMethod.POST)
	public @ResponseBody String educenter_trnng_remove_act(
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, RedirectAttributes redirectAttributes,
			HttpServletRequest request, ModelMap model, HttpServletResponse response) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		JSONObject dataObj = new JSONObject();
		LogRecordVO mEduLogRecordVO = new LogRecordVO();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		// 수강내역(메인) 정보
		MyHistoryVO myHistoryVO = new MyHistoryVO();
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		if (eduCenterMainVO.getCRS_LAW_TYPE().equals("CIDLAW002")) {
			myHistoryVO.setCRS_STR_DT(
					mPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy"));
		} else if (eduCenterMainVO.getCRS_LAW_TYPE().equals("default")) {
			myHistoryVO.setCRS_STR_DT(mPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(),"yyyy-MM-dd HH:mm:ss.S", "yyyy-MM"));
		}
		myHistoryVO.setTYPE_GB(eduCenterMainVO.getTYPE_GB());
		myHistoryVO.setCRS_MBR_CD(eduCenterMainVO.getCRS_MBR_CD());

		// 신청중인 교육 리스트
		List<MyHistoryVO> myHistoryList = myHistoryService.get_educenter_mbrhstry_info_list(myHistoryVO);
		for (int i = 0; i < myHistoryList.size(); i++) {
			myHistoryService.set_educenter_mbrhstry_cancel(myHistoryList.get(i));
		}
		dataObj.put("error", "0");
		dataObj.put("msg", "정상적으로 삭제되었습니다. \n 계속 진행하시겠습니까?");

		try {
			/**
			 * LOG RECORED (로그기록)
			 */
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCenterMainVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID("GUEST");
			mEduLogRecordVO.setMBR_LV("1");
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO, request);
		} catch (Exception e) {
			LOGGER.debug("[fail log record] " + e.toString());
		}

		LOGGER.debug(dataObj.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(dataObj);
		return null;
	}

	// 교육센터-교육수강신청(검토)-신청서 기존 신청서 삭제
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/rmndr/remove_act.do", method = RequestMethod.POST)
	public @ResponseBody String educenter_rmndr_remove_act(
			@ModelAttribute("eduCenterMbrRemindersVO") EduCenterMbrRemindersVO eduCenterMbrRemindersVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model,
			HttpServletResponse response) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject dataObj = new JSONObject();
		LogRecordVO mEduLogRecordVO = new LogRecordVO();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		String rmndr_mbr_nm = eduCenterMbrRemindersVO.getRMNDR_MBR_NM();
		String rmndr_mbr_hp = eduCenterMbrRemindersVO.getRMNDR_MBR_HP();
		String rmndr_mbr_birth = eduCenterMbrRemindersVO.getRMNDR_MBR_BIRTH();

		eduCenterMainService.remove_eduCenter_mbr_reminders(eduCenterMbrRemindersVO);
		dataObj.put("error", "0");
		dataObj.put("msg", "삭제되었습니다.");

		try {
			/**
			 * LOG RECORED (로그기록)
			 */
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCenterMbrRemindersVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID("GUEST");
			mEduLogRecordVO.setMBR_LV("1");
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO, request);
		} catch (Exception e) {
			LOGGER.debug("[fail log record] " + e.toString());
		}

		LOGGER.debug(dataObj.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(dataObj);
		return null;
	}

	// 교육센터-교육수강신청(검토)-신청서 로직 처리
	// ------------------------------------------------
	@RequestMapping(value = "/educenter/rmndr/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String educenter_rmndr_write_act(
			@ModelAttribute("eduCenterMbrRemindersVO") EduCenterMbrRemindersVO eduCenterMbrRemindersVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "page_type", required = false) String page_type) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");

		EduMemberVO eduMemberVO = new EduMemberVO();
		eduMemberVO.setMBR_NM(eduCenterMbrRemindersVO.getRMNDR_MBR_NM());
		eduMemberVO.setMBR_BIRTH(eduCenterMbrRemindersVO.getRMNDR_MBR_BIRTH());
		eduMemberVO.setMBR_HP(eduCenterMbrRemindersVO.getRMNDR_MBR_HP());

		String MBR_ID = "";
		String REG_MBR_ID = "";
		String MBR_LV_ID = "";
		String MASTER_MBR_ID = eduMemberVO.getUniqKey("MID");
		String LICENSE_CD = eduCenterMbrRemindersVO.getRMNDR_DTL_LICENSE_CD();
		String DTL_CD = eduCenterMbrRemindersVO.getRMNDR_DTL_CD();

		boolean newCheck = false;

		List<EduMemberVO> list_overlap = eduMemberService.get_edu_member_check_list(eduMemberVO);
		//EduMemberVO list_overlap = eduMemberService.get_edu_member_info(eduMemberVO);
		if(list_overlap.size() > 0){
			String return_url = "";
			if (page_type != null && page_type.equals("mobile")) {
				return_url = "/educenter/m/index.do";
			} else {
				return_url = "/educenter/index.do";
			}
			data.put("return_url", return_url);
			response.getWriter().print(data);
			LOGGER.debug(data.toString());
			response.setContentType("application/json;charset=utf-8");
			return null;
		}
		if (DTL_CD.equals("CIDN010200")) { //낚시터일경우
			newCheck = true; // 중복 없으면
		} else {
			if (LICENSE_CD.equals("CIDL00001") || LICENSE_CD.equals("CIDL00002")) {
				newCheck = false;
			} else {
				newCheck = true; // 중복 없으면
			}
		}

		if (!newCheck) { // 개인, 법인, 개인정보 중복 있을 때
			if (loginVO == null || loginVO.getMBR_ID() == null) {
				MBR_ID = "";
				REG_MBR_ID = "guest";
				MBR_LV_ID = "";
			} else {
				MBR_ID = loginVO.getMBR_ID();
				REG_MBR_ID = loginVO.getMBR_ID();
				MBR_LV_ID = loginVO.getMBR_LV_ID();
			}

			log_dscrp.append("[온라인교육신청(검토)]");
			tbl_inf.append("EDU_MBR_RMNDR_TB");
			try {

				boolean isAllow = true;
				if (eduCenterMbrRemindersVO == null) {
					log_dscrp.append("[정보부족 등록실패");
					data.put("error", "1");
					data.put("msg", "교육수강신청(검토) 등록에 실패하였습니다.\n계속 될 경우 관리자에게 문의해주세요.");
					isAllow = false;
				}

				String vldVrfcAuthTempDataBirthDay = (String) request.getSession()
						.getAttribute("vldVrfcAuthTempDataBirthDay");
				String vldVrfcAuthTempDataName = (String) request.getSession().getAttribute("vldVrfcAuthTempDataName");
				String vldVrfcAuthTempDataPhoneNo = (String) request.getSession()
						.getAttribute("vldVrfcAuthTempDataPhoneNo");

				String rmndrBirth = (String) request.getSession().getAttribute("loginActAuthTempDataBirthDay");
				String rmndrNm = (String) request.getSession().getAttribute("loginActAuthTempDataName");
				String rmndrHP = (String) request.getSession().getAttribute("loginActAuthTempDataPhoneNo");

				if (!vldVrfcAuthTempDataBirthDay.equals(rmndrBirth) || !vldVrfcAuthTempDataName.equals(rmndrNm) || !vldVrfcAuthTempDataPhoneNo.equals(rmndrHP)) {
					log_dscrp.append("[비정상적인 접근 - 본인인증 변조");
					data.put("error", "1");
					data.put("msg", "비정상적인 접근입니다. \n본인인증을 다시 시도해주세요.");
					isAllow = false;
				} else {

					// 14세 미만인 경우 보호자 동의가 필요함(본인인증 완료여부 확인)
					String isChkLoginAuthInfo14ageUnder = (String) request.getSession()
							.getAttribute("isChkLoginAuthInfo14ageUnder");
					if (isChkLoginAuthInfo14ageUnder != null && isChkLoginAuthInfo14ageUnder.length() != 0
							&& isChkLoginAuthInfo14ageUnder.equals("Y")) {
						// 검증 단계
						String PARNTS_MBR_BIRTH = (String) request.getSession().getAttribute("PARNTS_MBR_BIRTH");
						String PARNTS_MBR_NM = (String) request.getSession().getAttribute("PARNTS_MBR_NM");
						String PARNTS_MBR_HP = (String) request.getSession().getAttribute("PARNTS_MBR_HP");
						String PARNTS_MBR_RELATIONSHIP = (String) request.getSession()
								.getAttribute("PARNTS_MBR_RELATIONSHIP");
						// 만 14세 미만 여부 검증
						if (mPublicUtils.getAge(PARNTS_MBR_BIRTH) <= 14) {
							log_dscrp.append("[14세미만보호자동의필요|14세미만자가시도");
							data.put("error", "14");
							data.put("msg", "법정대리인의 본인인증이 올바르지 않아 신청이 거부되었습니다.");
							isAllow = false;
						} else {
							if (eduCenterMbrRemindersVO.getRMNDR_MBR_BIRTH().equals(PARNTS_MBR_BIRTH)
									&& eduCenterMbrRemindersVO.getRMNDR_MBR_NM().equals(PARNTS_MBR_NM)
									&& eduCenterMbrRemindersVO.getRMNDR_MBR_HP().equals(PARNTS_MBR_HP)) {
								log_dscrp.append("[14세미만보호자동의필요|본인정보");
								data.put("error", "14");
								data.put("msg", "14세 미만은 보호자의 동의가 필요합니다.\n법정대리인을 본인으로 하실 수 없습니다.");
								isAllow = false;
							} else {
								log_dscrp.append("[14세미만가입진행]");
								eduCenterMbrRemindersVO.setPARNTS_MBR_BIRTH(PARNTS_MBR_BIRTH);
								eduCenterMbrRemindersVO.setPARNTS_MBR_NM(PARNTS_MBR_NM);
								eduCenterMbrRemindersVO.setPARNTS_MBR_HP(PARNTS_MBR_HP);
								eduCenterMbrRemindersVO.setPARNTS_MBR_RELATIONSHIP(PARNTS_MBR_RELATIONSHIP);
								eduCenterMbrRemindersVO.setUNDER_AGE_14_ST("Y");
							}
						}
						// End of 만 14세 미만 여부 검증
					} else if (isChkLoginAuthInfo14ageUnder != null && isChkLoginAuthInfo14ageUnder.length() != 0&& isChkLoginAuthInfo14ageUnder.equals("N")) {
						// 정상(성인) 실명인증 우회방지-세션에서 가져온 값 넣기

						if (eduCenterMbrRemindersVO.getRMNDR_MBR_BIRTH().equals(rmndrBirth)&& eduCenterMbrRemindersVO.getRMNDR_MBR_NM().equals(rmndrNm)&& eduCenterMbrRemindersVO.getRMNDR_MBR_HP().equals(rmndrHP)) {
							eduCenterMbrRemindersVO.setRMNDR_MBR_BIRTH(rmndrBirth);
							eduCenterMbrRemindersVO.setRMNDR_MBR_NM(rmndrNm);
							eduCenterMbrRemindersVO.setRMNDR_MBR_HP(rmndrHP);

						} else {
							log_dscrp.append("[비정상적인접근");
							data.put("error", "14");
							data.put("msg", "본인인증 정보와 맞지 않습니다.\n계속 될 경우 관리자에게 문의해주세요.");
							isAllow = false;
						}

						request.getSession().removeAttribute("loginActAuthTempDataBirthDay");
						request.getSession().removeAttribute("loginActAuthTempDataName");
						request.getSession().removeAttribute("loginActAuthTempDataPhoneNo");

					} else {
						log_dscrp.append("[비정상적인접근");
						data.put("error", "14");
						data.put("msg", "비정상적인 접근입니다.\n본인인증 후 이용해주세요.");
						isAllow = false;
					}

				}

				// 문자 안내 신청
				EduSmsRequstVO eduSmsRequstVO = new EduSmsRequstVO();

				// 커리큘럼 메인
				String CRS_SN = eduCenterMbrRemindersVO.getRMNDR_CRS_SN();
				EduCenterMainVO parentInfo = new EduCenterMainVO();
				parentInfo.setCRS_SN(CRS_SN);
				parentInfo = eduCenterMainService.get_educenter_curriculum_info(parentInfo);
				// 커리큘럼 존재 유무 판단
				if (parentInfo == null || parentInfo.getCRS_SN() == null || parentInfo.getCRS_SN().length() == 0) {
					data.put("error", "1");
					data.put("msg", "비정상적인 접근방법 입니다.");
					isAllow = false;
				}
				// 온라인 교육 신청 가능자 여부 조회
				if (isAllow && parentInfo.getCRS_GRP_CD().equals("CIDE00000000000")) { 
					// 온라인 교육 건
					// 신청제한지역설정 확인
					boolean isLockArea = true;
					String isLockMsg = "";
					String checkLockAreaCd = "";
					String checkLockAreaNm = "";
					if (parentInfo.getCRS_LOCK_AREA_ST().equals("1")) {
						if (parentInfo.getCRS_MBR_CD().equals("CIDN010300")) { // 낚시어선
																				// 검증
							// 신청지역제한 코드 조회
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_MASTER_ID("CID00008");
							mCodeSetVO.setCD_ID(parentInfo.getCRS_LOCK_AREA_CD());
							List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
							for (CodeSetVO item_category : list_lock_area_grp_cd) {
								checkLockAreaCd = item_category.getCD_ID();
								checkLockAreaNm = item_category.getCD_DES();
							}
							isLockMsg = "해당 교육은 낚시어선 신고지에 속한 대상자만 신청 하실 수 있습니다.";

						} else if (parentInfo.getCRS_MBR_CD().equals("CIDN010200")) { // 낚시터
																						// 검증
							// 신청지역제한 코드 조회
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_MASTER_ID("CID00009");
							mCodeSetVO.setCD_ID(parentInfo.getCRS_LOCK_AREA_CD());
							List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
							for (CodeSetVO item_category : list_lock_area_grp_cd) {
								checkLockAreaCd = item_category.getCD_ID();
								checkLockAreaNm = item_category.getCD_DES();
							}
							isLockMsg = "해당 교육은 낚시터 신고지에 속한 대상자만 신청 하실 수 있습니다.";
						}
						// 회원상세정보 조회 검증
						CodeSetVO category_sido = null;
						{
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_MASTER_ID("CID00004");
							mCodeSetVO.setCD_ID(eduCenterMbrRemindersVO.getRMNDR_SIDO_CD());
							category_sido = codeSetService.get_codeset_info(mCodeSetVO);
						}
						CodeSetVO category_signgu = null;
						{
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_MASTER_ID(eduCenterMbrRemindersVO.getRMNDR_SIDO_CD());
							mCodeSetVO.setCD_ID(eduCenterMbrRemindersVO.getRMNDR_SIGNGU_CD());
							category_signgu = codeSetService.get_codeset_info(mCodeSetVO);
						}
						if (checkLockAreaNm.contains(category_sido.getCD_NM())
								&& checkLockAreaNm.contains(category_signgu.getCD_NM())) {
							isLockArea = false;
							// isLockMsg = "신청가능함~~good";//이거 지우고 위에 주석해제
						} else if (checkLockAreaNm.contains(category_sido.getCD_NM())) {
							CodeSetVO mCodeSetVO = new CodeSetVO();
							mCodeSetVO.setCD_MASTER_ID(checkLockAreaCd);
							List<CodeSetVO> list_lock_area_dtl_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
							if (list_lock_area_dtl_grp_cd.size() > 0) {
								for (CodeSetVO item_category : list_lock_area_dtl_grp_cd) {
									if (item_category.getCD_DES()
											.contains(category_sido.getCD_NM() + " " + category_signgu.getCD_NM())) {
										isLockArea = false;
										// isLockMsg = "신청가능함~~시군구 비교 일치";//이거
										// 지우고 위에 주석해제
									}
								}
							} else {
								isLockArea = false;
								// isLockMsg = "신청가능함~~시도 비교 일치";//이거 지우고 위에
								// 주석해제
							}
						}
						// End of 회원상세정보 조회 검증
					} else { // 지역제한 검증 안함
						isLockArea = false;
					}

					// -----------------------------------
					// 신규회원은 검증 안함 2020.04.06
					// -----------------------------------
					isLockArea = false;
					// -----------------------------------
					// end 신규회원은 검증 안함 2020.04.06
					// -----------------------------------

					if (isLockArea) {
						data.put("error", "1");
						data.put("msg", isLockMsg);
						isAllow = false;
					}
					// End of 신청제한지역설정 확인

				}
				// End of 온라인 교육 신청 가능자 여부 조회

				if (isAllow) {

					// 온라인,오프라인 교육 신청자 ( 기존,신규,재개자도 업데이트 )
					eduCenterMbrRemindersVO.setTYPE_GB(parentInfo.getTYPE_GB());
					eduCenterMbrRemindersVO.setRMNDR_MBR_ID(MBR_ID);
					eduCenterMbrRemindersVO.setREG_MBR_ID(REG_MBR_ID);
					eduCenterMbrRemindersVO.setUPD_MBR_ID(REG_MBR_ID);
					eduCenterMbrRemindersVO.setCMPLT_ST("N");

					eduCenterMainService.set_educenter_mbr_rmndr_reg(eduCenterMbrRemindersVO);

					if (parentInfo.getCRS_MBR_CD().equals("CIDN010300") && (eduCenterMbrRemindersVO.getREQ_YN() != null
							&& eduCenterMbrRemindersVO.getREQ_YN().equals("Y"))) { 
						// 낚시어선에 문자신청 문자 안내 신청
						eduSmsRequstVO.setESR_MBR_ID(MBR_ID);
						eduSmsRequstVO.setESR_MBR_HP(eduCenterMbrRemindersVO.getRMNDR_MBR_HP());
						eduSmsRequstVO.setESR_MBR_NM(eduCenterMbrRemindersVO.getRMNDR_MBR_NM());
						eduSmsRequstVO.setESR_DTL_CD(parentInfo.getCRS_MBR_CD());
						eduSmsRequstVO.setESR_DTL_LICENSE_CD(eduCenterMbrRemindersVO.getRMNDR_DTL_LICENSE_CD());
						eduSmsRequstVO.setESR_SIDO_CD(eduCenterMbrRemindersVO.getRMNDR_SIDO_CD());
						eduSmsRequstVO.setESR_SIGNGU_CD(eduCenterMbrRemindersVO.getRMNDR_SIGNGU_CD());
						eduSmsRequstVO.setESR_REG_MBR_ID(REG_MBR_ID);
						eduSmsRequstVO.setESR_UPD_MBR_ID(REG_MBR_ID);
						eduSmsRequstVO.setESR_TRGET(eduCenterMbrRemindersVO.getREQ_YN());
						eduCenterMainService.set_educenter_sms_request(eduSmsRequstVO);

						log_dscrp.append("[교육 알림 문자 신청 : " + eduCenterMbrRemindersVO.getRMNDR_MBR_HP() + " ]");
					}
					data.put("error", "0");
					data.put("msg", "교육수강신청(검토) 등록이 완료 되었습니다.");
					data.put("errCnt", 0);
					data.put("return_url", "/educenter/index.do");
					data.put("auto_reg", "0");//비회원 자동등록 아닐때

					log_dscrp.append("[이름:" + eduCenterMbrRemindersVO.getRMNDR_MBR_NM() + "(아이디:"
							+ eduCenterMbrRemindersVO.getRMNDR_MBR_ID() + ",교육과정번호:"
							+ eduCenterMbrRemindersVO.getRMNDR_CRS_SN() + ") 등록완료");

					// 가입 후에 보호자 본인인증 확인 후 삭제
					request.getSession().removeAttribute("isChkLoginAuthInfo14ageUnder");
					//
					// 법정대리인의 본인인증 정보 삭제
					request.getSession().removeAttribute("AGE14_UNDER_MDL_TKN");
					request.getSession().removeAttribute("PARNTS_MBR_BIRTH");
					request.getSession().removeAttribute("PARNTS_MBR_NM");
					request.getSession().removeAttribute("PARNTS_MBR_HP");
					request.getSession().removeAttribute("PARNTS_MBR_RELATIONSHIP");
					// End of 법정대리인의 본인인증 정보 삭제
				}

			} catch (Exception e) {
				e.printStackTrace();
				log_msg.append("[처리에러]" + e.toString());
				log_dscrp.append("[처리실패]");
				LOGGER.debug("error", e);
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		} else {
			log_dscrp.append("[교육센터관리자-회원관리-신규회원등록]");
			tbl_inf.append("MBR_TB");

			log_dscrp.append("[이름:" + eduMemberVO.getMBR_NM() + "(아이디:" + MASTER_MBR_ID + ")]");

			int is_id_cnt = eduMemberService.get_id_search(eduMemberVO);
			if (is_id_cnt > 0) {
				log_dscrp.append("|등록실패:이미 등록 된 아이디]");

				data.put("error", "1");
				data.put("msg", "이미 등록 된 아이디 입니다.");

			} else {
				try {
					boolean isAddConfirm = false;

					eduMemberVO.setMBR_CD(MASTER_MBR_ID);
					eduMemberVO.setMBR_ID(MASTER_MBR_ID);
					eduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
					eduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
					eduMemberVO.setMBR_TEL(eduCenterMbrRemindersVO.getRMNDR_MBR_TEL());
					eduMemberVO.setMBR_ADDR1(eduCenterMbrRemindersVO.getRMNDR_MBR_ADDR1());
					eduMemberVO.setMBR_ADDR2(eduCenterMbrRemindersVO.getRMNDR_MBR_ADDR2());
					eduMemberVO.setMBR_ZIPCD(eduCenterMbrRemindersVO.getRMNDR_MBR_ZIP());
					eduMemberVO.setDTL_CD(eduCenterMbrRemindersVO.getRMNDR_DTL_CD());
					eduMemberVO.setCRS_SN(eduCenterMbrRemindersVO.getRMNDR_CRS_SN());
					eduMemberVO.setDTL_LICENSE_CD(eduCenterMbrRemindersVO.getRMNDR_DTL_LICENSE_CD());
					eduMemberVO.setDTL_NM(eduCenterMbrRemindersVO.getRMNDR_DTL_NM());
					eduMemberVO.setREG_NUM_CD(eduCenterMbrRemindersVO.getRMNDR_REG_NUM_CD());
					eduMemberVO.setBUSINESS_NUM(eduCenterMbrRemindersVO.getRMNDR_BUSINESS_NUM());
					eduMemberVO.setSIDO_CD(eduCenterMbrRemindersVO.getRMNDR_SIDO_CD());
					eduMemberVO.setSIGNGU_CD(eduCenterMbrRemindersVO.getRMNDR_SIGNGU_CD());
					eduMemberVO.setYMD_NM(eduCenterMbrRemindersVO.getRMNDR_YMD_NM());
					eduMemberVO.setSHIP_CD(eduCenterMbrRemindersVO.getRMNDR_SHIP_CD());
					eduMemberVO.setSHIP_LICENSE(eduCenterMbrRemindersVO.getRMNDR_SHIP_LICENSE());
					eduMemberVO.setSHIP_LICENSE_STR_DT(eduCenterMbrRemindersVO.getRMNDR_SHIP_LICENSE_STR_DT());
					eduMemberVO.setSHIP_LICENSE_END_DT(eduCenterMbrRemindersVO.getRMNDR_SHIP_LICENSE_END_DT());
					eduMemberVO.setREG_DT(eduCenterMbrRemindersVO.getREG_DT());
					eduMemberVO.setUPD_DT(eduCenterMbrRemindersVO.getUPD_DT());
					eduMemberVO.setCMPLT_ST(eduCenterMbrRemindersVO.getCMPLT_ST());
					eduMemberVO.setMBR_INDVDL_INFO_ST(eduCenterMbrRemindersVO.getINDVDL_INFO_ST());
					eduMemberVO.setPARNTS_MBR_HP(eduCenterMbrRemindersVO.getPARNTS_MBR_HP());
					eduMemberVO.setPARNTS_MBR_NM(eduCenterMbrRemindersVO.getPARNTS_MBR_NM());
					eduMemberVO.setPARNTS_MBR_BIRTH(eduCenterMbrRemindersVO.getPARNTS_MBR_BIRTH());
					eduMemberVO.setPARNTS_MBR_RELATIONSHIP(eduCenterMbrRemindersVO.getPARNTS_MBR_RELATIONSHIP());
					eduMemberVO.setTYPE_GB(eduCenterMbrRemindersVO.getTYPE_GB());
					eduMemberVO.setMBR_FSHRBT_TYPE(eduCenterMbrRemindersVO.getMBR_FSHRBT_TYPE());
					eduMemberVO.setMBR_FSHRBT_TYPE(eduCenterMbrRemindersVO.getMBR_FSHRBT_TYPE());
					eduMemberVO.setMBR_FSHRBT_TYPE(eduCenterMbrRemindersVO.getMBR_FSHRBT_TYPE());
					eduMemberVO.setMBR_JOIN("auto");

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
						try {
							eduMemberService.set_edu_member_dtl_reg(eduMemberVO);
							// 사용자사유로그기록
							{
								logRecordService.set_log_mbr_mod_data("MBR_DTL_TB", "추가", eduMemberVO.getLOG_UPD_MSG(),
										eduMemberVO.getMBR_ID(), eduMemberVO.getMBR_NM(), null, eduMemberVO, loginVO,
										request);
							}
							cntDtlSuccess++;
						} catch (Exception e) {
							log_msg.append("상세정보추가등록에러" + e.toString());
							cntDtlFail++;
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

						String year = "";

						EduCurriculumVO chkEduCurriculumVO = new EduCurriculumVO();
						chkEduCurriculumVO.setCRS_SN(eduMemberVO.getCRS_SN());
						chkEduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(chkEduCurriculumVO);
						if (chkEduCurriculumVO != null && chkEduCurriculumVO.getCRS_SN() != null
								&& chkEduCurriculumVO.getCRS_SN().length() != 0) {
							Calendar cal = mPublicUtils.changeGetCalendar(
									chkEduCurriculumVO.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss");
							year = String.valueOf(cal.get(Calendar.YEAR));
						}
						eduMemberVO.setSearchYear(year);

						if (chkEduCurriculumVO == null || chkEduCurriculumVO.getCRS_SN() == null
								|| chkEduCurriculumVO.getCRS_SN().length() == 0) {
							log_dscrp.append("|실패:교육과정정보가존재하지않음");
						} else {
							List<String> externalVideoUrl = new ArrayList<>();
							//외부 동영상 링크 URL
							// 커리큘럼 상세
							chkEduCurriculumVO.setNotUsedPagination(true);
							List<EduCurriculumVO> clildlist = eduCurriculumService.get_edu_curriculum_dtl_list(chkEduCurriculumVO);

							int HMBR_MAX_TIME = Integer.parseInt(chkEduCurriculumVO.getCRS_TOT_TIME())
									+ chkEduCurriculumVO.getSUM_TOT_TIME();
							int HMBR_MAX_POINT = Integer.parseInt(chkEduCurriculumVO.getCRS_TOT_POINT())
									+ chkEduCurriculumVO.getSUM_TOT_POINT();
							EduMyHistoryVO newEduMyHistoryVO = new EduMyHistoryVO();
							newEduMyHistoryVO.setPURCHASE_CMPLT_ST("1");
							// 결제(구매) 처리는 현재 보류
							newEduMyHistoryVO.setCRS_SN(chkEduCurriculumVO.getCRS_SN());
							newEduMyHistoryVO.setMBR_ID(eduMemberVO.getMBR_ID());
							newEduMyHistoryVO.setREG_MBR_ID(eduMemberVO.getMBR_ID());
							newEduMyHistoryVO.setUPD_MBR_ID(eduMemberVO.getMBR_ID());
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
							
							String domainUrl = request.getScheme() + "://" + request.getServerName() + ":"
									+ request.getServerPort();

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
									EduTrainingDataVO tdata = eduTrainingDataService.get_edu_data_info(eduTrainingDataVO);

									EduMyHistoryVO newDtlEduMyHistoryVO = new EduMyHistoryVO();
									String HMBR_DTL_SN = "";
									//중복 검증 구간
									{
										boolean isOk = false;
										do {
											HMBR_DTL_SN = newDtlEduMyHistoryVO.getUniqKey("HMBRD");
											isOk = eduMyHistoryService.get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(HMBR_DTL_SN);
										} while(!isOk);
									}
									//End of 중복 검증 구간							
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
									newDtlEduMyHistoryVO.setREG_MBR_ID(eduMemberVO.getMBR_ID());
									newDtlEduMyHistoryVO.setUPD_MBR_ID(eduMemberVO.getMBR_ID());
									newDtlEduMyHistoryVO.setHMBR_DTL_SN(HMBR_DTL_SN);
									eduMyHistoryService.set_edu_mbrhstry_reg_dtl(newDtlEduMyHistoryVO);

									//외부 동영상 URL 생성
									String eduUrl = domainUrl+"/educenter/mbrhstry/external/play.do?key="+EgovFileScrty.encode(HMBR_DTL_SN);
									externalVideoUrl.add(eduUrl);
									//End of 외부 동영상 URL 생성
									
									tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
									tbl_sn.append(HMBR_DTL_SN + ",");

									insertSubCount++;
								}
								insertChildCount++;
							}
							// 메인 커리큘럼에 회원 카운트 증가
							eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(chkEduCurriculumVO);

							{// 연도별이수내역에 추가 또는 업데이트
								// 커리큘럼 메인
								String CRS_SN = eduCenterMbrRemindersVO.getRMNDR_CRS_SN();
								EduCenterMainVO parentInfo = new EduCenterMainVO();
								parentInfo.setCRS_SN(CRS_SN);
								parentInfo = eduCenterMainService.get_educenter_curriculum_info(parentInfo);
								
								String TRGT_YEAR = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy");
								log_dscrp.append("|" + TRGT_YEAR + "연도별이수내역추가");
								EduMemberVO chkTargetEduMemberVO = new EduMemberVO();
								chkTargetEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
								chkTargetEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
								chkTargetEduMemberVO.setUSE_AT("Y");
								chkTargetEduMemberVO.setNotUsedPagination(true);
								List<EduMemberVO> chkTargetEduList = eduMemberService
										.get_edu_member_target_list(chkTargetEduMemberVO);
								if (chkTargetEduList == null || chkTargetEduList.size() == 0) {
									log_dscrp.append("|" + TRGT_YEAR + "년도 대상자추가완료");
									EduMemberVO newEduMemberVO = new EduMemberVO();
									newEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
									newEduMemberVO.setMBR_CD(eduMemberVO.getMBR_CD());
									newEduMemberVO.setMBR_NM(eduMemberVO.getMBR_NM());
									newEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
									newEduMemberVO.setREG_MBR_ID(eduMemberVO.getMBR_ID());
									newEduMemberVO.setUPD_MBR_ID(eduMemberVO.getMBR_ID());
									newEduMemberVO.setCRS_SN(eduMemberVO.getCRS_SN());
									newEduMemberVO.setHMBR_SN(HMBR_SN);
									eduMemberService.set_edu_member_target_reg(newEduMemberVO);
								} else {
									log_dscrp.append("|" + TRGT_YEAR + "년도 대상자로이미등록되어있음");

									boolean isExistTargetBean = false;
									EduMemberVO updEduMemberVO = new EduMemberVO();
									updEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
									updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
									updEduMemberVO.setUPD_MBR_ID(eduMemberVO.getMBR_ID());
									updEduMemberVO.setCRS_SN_NULL("Y");
									List<EduMemberVO> chkTargetEduBeanList = eduMemberService.get_edu_member_target_all_list(updEduMemberVO);
									if (chkTargetEduBeanList == null || chkTargetEduBeanList.size() == 0) {
										isExistTargetBean = false;
									} else {
										isExistTargetBean = true;
									}
									if (isExistTargetBean) {
										log_dscrp.append("|" + TRGT_YEAR + "년도 빈연도별수강내역에업데이트");

										updEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
										updEduMemberVO.setMBR_NM(eduMemberVO.getMBR_NM());
										updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
										updEduMemberVO.setUPD_MBR_ID(eduMemberVO.getMBR_ID());
										updEduMemberVO.setCRS_SN_NULL("Y");// CRS_SN
																			// null 일때
										updEduMemberVO.setCRS_SN_NOT_NULL("");// CRS_SN
																				// 일때
										updEduMemberVO.setHMBR_SN_NULL("Y");// HMBR_SN
																			// null 일때
										updEduMemberVO.setCRS_SN(eduMemberVO.getCRS_SN());
										updEduMemberVO.setHMBR_SN(HMBR_SN);
										eduMemberService.set_edu_member_target_mod(updEduMemberVO);
									} else {
										log_dscrp.append("|" + TRGT_YEAR + "년도 연도별수강내역에신규추가");

										updEduMemberVO.setMBR_ID(eduMemberVO.getMBR_ID());
										updEduMemberVO.setMBR_NM(eduMemberVO.getMBR_NM());
										updEduMemberVO.setMBR_CD(eduMemberVO.getMBR_CD());
										updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
										updEduMemberVO.setREG_MBR_ID(eduMemberVO.getMBR_ID());
										updEduMemberVO.setUPD_MBR_ID(eduMemberVO.getMBR_ID());
										updEduMemberVO.setCRS_SN(eduMemberVO.getCRS_SN());
										updEduMemberVO.setHMBR_SN(HMBR_SN);
										eduMemberService.set_edu_member_target_reg(updEduMemberVO);
									}
								}
							}
							
							
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

								String add_msg = "";
								String ment_msg = "";

								SmsMentVO smsMentVO = new SmsMentVO();
								if (chkEduCurriculumVO.getTYPE_GB().equals("offline")) {// 오프라인교육
									add_msg = "낚시전문교육 수강신청 접수완료 안내(" + chkEduCurriculumVO.getCRS_NM() + ")" + "\n\n"
											+ eduTime + "\n" + "교육장소 : " + chkEduCurriculumVO.getCRS_PLACE() + "\n"
											+ "교육장주소지 : " + chkEduCurriculumVO.getCRS_ADDR() + "\n\n";
									if (chkEduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")) { 
										// 낚시어선
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

									if (chkEduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")) { // 낚시어선 신규,재개자교육
										smsMentVO.setSMS_MENT_SN("32");
									} else {
										if (chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")) {
											smsMentVO.setSMS_MENT_SN("4");
										} else {
											smsMentVO.setSMS_MENT_SN("5");
										}
										SmsSendVO newSmsSendVO = new SmsSendVO();
										//mSmsSendVO.setMSG_TYPE();//서비스에서 자동 처리
										//newSmsSendVO.setAPIKEY();//서비스에서 자동 처리
										//newSmsSendVO.setRSTKEY();//서비스에서 자동 처리
										newSmsSendVO.setMBR_ID(eduMemberVO.getMBR_ID());
										newSmsSendVO.setSMS_MENT_DTL_CD(chkEduCurriculumVO.getCRS_MBR_CD());
										newSmsSendVO.setMSG(add_msg+'\n'+ment_msg);							
										//newSmsSendVO.setSTAT();//예약발송일때만5
										newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));//발신번호
										newSmsSendVO.setR_PHONE(eduMemberVO.getMBR_HP());
										newSmsSendVO.setSUBMSG("낚시전문교육 수강신청 접수완료 안내");
										newSmsSendVO.setIMG_CNT(0);
										newSmsSendVO.setIMG_PATH("");
										newSmsSendVO.setREG_MBR_ID(MASTER_MBR_ID);
										newSmsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);			
									    newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
										smsManagerService.sendToMessage(newSmsSendVO);
										log_dscrp.append("|신청완료문자발송");
										
										//카카오 알림톡 발송
										KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
										kakaoAlimTalkVO.setNTCN_TRSM_TEL(eduMemberVO.getMBR_HP());//연락처
//										//(알림톡필수)
										kakaoAlimTalkVO.setAsync(true);//동기화 전송 여부(true:비동기,false:동기[기본값])
										String allEduUrl = "";
										if(chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010300")){
											if(chkEduCurriculumVO.getCRS_YEAR().equals("2023")){
												allEduUrl += externalVideoUrl.get(0) + ",";
												allEduUrl += externalVideoUrl.get(1) + ",";
												allEduUrl += externalVideoUrl.get(2) + ",";
												allEduUrl += externalVideoUrl.get(3) + ",";
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000019");//낚시어선 전문교육신청
											}else if(chkEduCurriculumVO.getCRS_YEAR().equals("2024")){//24년 교육신청 카톡 자동발송 김현태추가
												//System.out.println("externalVideoUrl1="+externalVideoUrl);
												allEduUrl += externalVideoUrl.get(0) + ",";
												allEduUrl += externalVideoUrl.get(1) + ",";
												allEduUrl += externalVideoUrl.get(2) + ",";
												allEduUrl += externalVideoUrl.get(3) + ",";
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000032");//낚시어선 전문교육신청
											}else {
												allEduUrl += externalVideoUrl.get(0) + ",";
												allEduUrl += externalVideoUrl.get(1) + ",";
												allEduUrl += externalVideoUrl.get(2) + ",";
												allEduUrl += externalVideoUrl.get(3) + ",";
												allEduUrl += externalVideoUrl.get(4) + ",";
												allEduUrl += externalVideoUrl.get(5) + ",";
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000001");//낚시어선 전문교육신청
											}
										} else if(chkEduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")){
											allEduUrl += externalVideoUrl.get(0) + ",";
											allEduUrl += externalVideoUrl.get(1) + ",";
											allEduUrl += externalVideoUrl.get(2) + ",";
											allEduUrl += externalVideoUrl.get(3) + ",";
											if(chkEduCurriculumVO.getCRS_YEAR().equals("2023"))
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000021");//낚시터 전문교육신청
											else if(chkEduCurriculumVO.getCRS_YEAR().equals("2024")){//24년 교육신청 카톡 자동발송 김현태추가
												//System.out.println("externalVideoUrl2="+externalVideoUrl);
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000034");
											}
											else 
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000003");//낚시터 전문교육신청
										}
										kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(chkEduCurriculumVO.getCRS_MBR_CD());//낚시터 전문교육신청
										String surveykey = EgovFileScrty.encode(HMBR_SN+","+chkEduCurriculumVO.getCRS_SN());
										allEduUrl += domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey;
										kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);
										kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
										kakaoAlimTalkVO.setREG_ID(eduMemberVO.getMBR_ID());
										//(일반문자필수)
//										JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
										kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
										//End of 카카오 알림톡 발송
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

								// 카카오 알림톡 발송
//								KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
//								kakaoAlimTalkVO.setNTCN_TRSM_TEL(eduMemberVO.getMBR_HP());// 연락처
//								// //(알림톡필수)
//								kakaoAlimTalkVO.setTemplateId("KKO_000001");// 교육신청
//								kakaoAlimTalkVO.setAsync(true);// 동기화 전송 여부(true:비동기,false:동기[기본값])
//								// (일반문자필수)
//								kakaoAlimTalkVO.setSendWithSMS(true);// 일반 문자 동시 전송 여부
//								kakaoAlimTalkVO.setMberId(eduMemberVO.getMBR_ID());// 회원아이디
//								kakaoAlimTalkVO.setMberSendId(MASTER_MBR_ID);// 문자발송회원아이디
//								kakaoAlimTalkVO.setSmsSj("수산공익직불제 수강신청 접수완료 안내");
//								// 일반 문자 발송 제목
//								kakaoAlimTalkVO.setSmsTmplatCn(ment_msg);
//								// 일반 문자 템플릿 내용
//								kakaoAlimTalkVO.setSmsTmplatCd(chkEduCurriculumVO.getCRS_MBR_CD());// 일반문자템플릿코드
//								kakaoAlimTalkVO.setMberIp(mPublicUtils.getClientIpAddr(request));// 회원접속아이피
//								JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO, propertiesService,codeSetService, smsManagerService);
								// End of 카카오 알림톡 발송
							}

						}
					}

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
					// 온라인수강신청자 확인처리
					if (eduMemberVO.getRMNDR_SN() != null && eduMemberVO.getRMNDR_SN().length() != 0) {
						EduMbrRemindersVO updEduMbrRemindersVO = new EduMbrRemindersVO();
						updEduMbrRemindersVO.setUPD_MBR_ID(MASTER_MBR_ID);
						updEduMbrRemindersVO.setRMNDR_SN(eduMemberVO.getRMNDR_SN());
						updEduMbrRemindersVO.setRMNDR_MBR_ID(eduMemberVO.getMBR_ID());
						eduCenterService.set_edu_mbr_reminders_lock_cmplt(updEduMbrRemindersVO);
						log_dscrp.append("|온라인수강신청건처리완료(RMNDR_SN:" + eduMemberVO.getRMNDR_SN() + ")");
					}

					// 데이터 넣고 로그인화면으로 이동

					String return_url = "";
					List<LoginVO> list_mbr = null;
					LoginVO resultVO = new LoginVO();

					LOGGER.debug("아이디 찾기 결과 처리");
					resultVO.setMBR_BIRTH(eduCenterMbrRemindersVO.getRMNDR_MBR_BIRTH());
					resultVO.setMBR_NM(eduCenterMbrRemindersVO.getRMNDR_MBR_NM());
					resultVO.setMBR_HP(eduCenterMbrRemindersVO.getRMNDR_MBR_HP());
					resultVO.setMBR_LV_ID("10");
					list_mbr = loginService.actionLoginNmBirth(resultVO);

					resultVO = list_mbr.get(0);

					// 접속 시간 기록
					resultVO.setMBR_LAST_CON_TM(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
					resultVO.setMBR_LAST_CON_IPADDR(mPublicUtils.getClientIpAddr(request));
					loginService.updateLoginHistory(resultVO);

					request.getSession().setAttribute("LoginVO", resultVO);

					if (page_type != null && page_type.equals("mobile")) {
						return_url = "/educenter/m/index.do";
					} else {
						return_url = "/educenter/index.do";
					}
					
					data.put("auto_reg", "1");//비회원 자동등록
					data.put("error", "0");
					data.put("errCnt", 0);
					data.put("msg", "새로운 회원이 등록되었습니다.");
					data.put("return_url", return_url);

					log_dscrp.append("]");

				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.debug("[fail load data] " + e.toString());
					log_dscrp.append("|등록실패:에러발생][" + eduMemberVO.getMBR_ID() + "]");
					data.put("error", "1");
					data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
				}
			}
		}

		try {
			/**
			 * LOG RECORED (로그기록)
			 */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCenterMbrRemindersVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MBR_ID);
			mEduLogRecordVO.setMBR_LV(MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO, request);
		} catch (Exception e) {
			LOGGER.debug("[fail log record] " + e.toString());
		}
		response.getWriter().print(data);
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");

		return null;
	}

	/**
	 * 유틸리티
	 */
	/*
	 * //교육과정 정보 가져오기 private List<EduCenterMainVO> getCalendarTrnngList(String
	 * year, String month) { mPublicUtils mPublicUtils = new mPublicUtils(); try
	 * { String toDate = mPublicUtils.changePatternString(year+"-"+month+
	 * "-10 00:00:00","yyyy-MM-dd HH:mm:ss"); LOGGER.debug("toDate : " +
	 * toDate);
	 * 
	 * String todayBeforeMonth =
	 * mPublicUtils.getCurrentPositionToBeforeTargetMonth(toDate, 1);
	 * LOGGER.debug("todayBeforeMonth : " + todayBeforeMonth);
	 * 
	 * String todayAfterMonth =
	 * mPublicUtils.getCurrentPositionToAfterTargetMonth(toDate, 1);
	 * LOGGER.debug("todayAfterMonth : " + todayAfterMonth);
	 * 
	 * EduCenterMainVO eduCenterMainVO = new EduCenterMainVO();
	 * eduCenterMainVO.setNotUsedPagination(true);
	 * eduCenterMainVO.setRECRUIT_STR_DT(mPublicUtils.changePatternString(
	 * todayBeforeMonth,"yyyy-MM-dd"));
	 * eduCenterMainVO.setRECRUIT_END_DT(mPublicUtils.changePatternString(
	 * todayAfterMonth,"yyyy-MM-dd")); eduCenterMainVO.setCRS_TYPE("default");
	 * return
	 * eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);
	 * } catch(Exception e) { LOGGER.debug("[fail process] " +e.toString());
	 * return null; } }
	 */
	// 교육과정 캘린더 정보 가져오기 (통합)
	@RequestMapping(value = "/educenter/trnng/ajax_calendar_trnng.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_educenter_trnng_calendar(
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int startYear = cal.get(Calendar.YEAR) - 1;

		String recruitStart = startYear + "-11-01";
		String recruitEnd = year + "-12-31";

		start = mPublicUtils.getCurrentPositionToBeforeTargetMonth(start + " 00:00:00", 1);
		end = mPublicUtils.getCurrentPositionToAfterTargetMonth(end + " 00:00:00", 1);

		JSONArray dataArr = new JSONArray();
		if (start == null || start.length() == 0 || end == null || end.length() == 0) {
			LOGGER.debug("교육정보 캘린더 조회 비정상적인 접근");
		} else {
			LOGGER.debug("교육정보 캘린더 조회 시작");
			try {
				eduCenterMainVO.setNotUsedPagination(true);
				eduCenterMainVO.setRECRUIT_STR_DT(mPublicUtils.changePatternString(recruitStart, "yyyy-MM-dd"));
				eduCenterMainVO.setRECRUIT_END_DT(mPublicUtils.changePatternString(recruitEnd, "yyyy-MM-dd"));
				eduCenterMainVO.setCRS_STR_DT(mPublicUtils.changePatternString(start, "yyyy-MM-dd"));
				eduCenterMainVO.setCRS_END_DT(mPublicUtils.changePatternString(end, "yyyy-MM-dd"));
				eduCenterMainVO.setTYPE_GB(eduCenterMainVO.getTYPE_GB());

				List<EduCenterMainVO> list = eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);

				for (EduCenterMainVO item : list) {
					try {
						LOGGER.debug("교육과정 : " + item.getCRS_NM());
						PublicUtils mmPublicUtils = new PublicUtils();
						// 색상구분
						String CRS_TYPE = item.getCRS_MBR_CD().toLowerCase();
						if (item.getCRS_LAW_TYPE().equals("CIDLAW002")) {
							// 낚시어선 신규,재개자 교육
							CRS_TYPE = "resmpt";
						}

						PublicUtils.RETURN_COMPARE_TYPE dateStr = mmPublicUtils.dateCompare(
								mmPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
								mmPublicUtils.changePatternString(item.getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
						PublicUtils.RETURN_COMPARE_TYPE dateEnd = mmPublicUtils.dateCompare(
								mmPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
								mmPublicUtils.changePatternString(item.getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
						// 교육기간만 표시함
						String status = "";
						JSONObject rawdata = new JSONObject();
						if (item.getLOCK_ST().equals("1")) { 
							// 모집잠금해제상태 인 경우
							LOGGER.debug("모집 잠금!");
							rawdata.put("className", CRS_TYPE + "_edu" + " lock");
							rawdata.put("url", item.getCRS_SN());
							status = "lock";
						} else if (item.getLOCK_ST().equals("0") 
								// 모집기간 확인
								// &&
								// EgovDateUtil.getDaysDiff(mmPublicUtils.changePatternString(item.getRECRUIT_STR_DT(),
								// "yyyy-MM-dd HH:mm:ss.S",
								// "yyyyMMdd"),EgovDateUtil.getToday()) >= 0
								// //모집시작일자
								// &&
								// EgovDateUtil.getDaysDiff(mmPublicUtils.changePatternString(item.getRECRUIT_END_DT(),
								// "yyyy-MM-dd HH:mm:ss.S",
								// "yyyyMMdd"),EgovDateUtil.getToday()) <= 0
								// //모집종료일자
								&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
								&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
							LOGGER.debug("정상적인 교육신청 기간!");
							if (Integer.parseInt(item.getMBR_MAX_CNT()) != 0 && Integer.parseInt(item.getMBR_CUR_CNT())+ 1 > Integer.parseInt(item.getMBR_MAX_CNT())) {
								LOGGER.debug("이미 접수 인원이 초과한 경우");
								// rawdata.put("className", CRS_TYPE+"_edu" + "
								// mbrover");
								//대기자로등록가능
								rawdata.put("className", CRS_TYPE + "_edu lock");
								// 2020.01.05 대기접수 받지 않도록
								status = "lock";
							} else {
								LOGGER.debug("접수 가능 인원!");
								rawdata.put("className", CRS_TYPE + "_edu" + " unlock");
								status = "unlock";
							}
						} else {
							if (dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
								LOGGER.debug("접수기간이 지난 경우");
								rawdata.put("className", CRS_TYPE + "_edu lock");
								status = "lock";
							} else {
								LOGGER.debug("현재 모집기간이 아니거나 모집이 중단 되었습니다.");
								rawdata.put("className", CRS_TYPE + "_edu");
								status = "last";
							}
						}
						// 날짜표기
						String str_crs_dt = mPublicUtils.changePatternString(item.getCRS_STR_DT().replace(".0", ""),"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
						String end_crs_dt = mPublicUtils.changePatternString(item.getCRS_END_DT().replace(".0", ""),"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
						String str_crs_str_time = mPublicUtils.changePatternString(item.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "HH:mm");
						String str_crs_end_time = mPublicUtils.changePatternString(item.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "HH:mm");
						rawdata.put("status", status);
						rawdata.put("crs_sn", item.getCRS_SN());
						rawdata.put("title", item.getCRS_NM());
						rawdata.put("subtitle", item.getCRS_PLACE());
						rawdata.put("subtitle2", item.getCRS_GRP_CD_NM());
						rawdata.put("subinfo", str_crs_dt + " " + str_crs_str_time + "~" + str_crs_end_time);
						rawdata.put("subinfo2", end_crs_dt + " " + str_crs_str_time + "~" + str_crs_end_time);
						rawdata.put("start", item.getCRS_STR_DT());
						rawdata.put("end", item.getCRS_END_DT());
						if (item.getCRS_GRP_CD().equals("CIDE00000000000")) {
							rawdata.put("online", "Y");
						} else {
							rawdata.put("online", "N");
						}
						dataArr.add(rawdata);
						/*
						 * {//모집신청 기간표기 JSONObject rawdata = new JSONObject();
						 * if(item.getLOCK_ST().equals("0") //모집잠금해제상태 인 경우 &&
						 * EgovDateUtil.getDaysDiff(mmPublicUtils.
						 * changePatternString(item.getRECRUIT_STR_DT(),
						 * "yyyy-MM-dd HH:mm:ss.S",
						 * "yyyyMMdd"),EgovDateUtil.getToday()) >= 0 //모집시작일자 &&
						 * EgovDateUtil.getDaysDiff(mmPublicUtils.
						 * changePatternString(item.getRECRUIT_END_DT(),
						 * "yyyy-MM-dd HH:mm:ss.S",
						 * "yyyyMMdd"),EgovDateUtil.getToday()) <= 0 //모집종료일자 )
						 * { LOGGER.debug("정상적인 교육신청 기간!");
						 * rawdata.put("className", item.getCRS_TYPE() +
						 * " unlock"); rawdata.put("url", item.getCRS_SN()); }
						 * else { LOGGER.debug("현재 모집기간이 아니거나 모집이 중단 되었습니다.");
						 * rawdata.put("className", item.getCRS_TYPE());
						 * rawdata.put("url",
						 * "javascript:popupInfo('"+item.getCRS_SN()+"')"); }
						 * rawdata.put("title", item.getCRS_NM() + " 접수기간");
						 * rawdata.put("start", item.getRECRUIT_STR_DT());
						 * rawdata.put("end", item.getRECRUIT_END_DT());
						 * dataArr.add(rawdata); } {//교육기간 표기 JSONObject rawdata
						 * = new JSONObject(); rawdata.put("className",
						 * item.getCRS_TYPE()+"_edu"); rawdata.put("url",
						 * "javascript:popupInfo('"+item.getCRS_SN()+"')");
						 * rawdata.put("title", item.getCRS_NM() + " 교육기간");
						 * rawdata.put("start", item.getCRS_STR_DT());
						 * rawdata.put("end", item.getCRS_END_DT());
						 * dataArr.add(rawdata); }
						 */
					} catch (Exception e1) {
						LOGGER.debug("[fail process] put json , " + e1.toString());
					}
				}
			} catch (Exception e) {
				LOGGER.debug("[fail process] sql or data , " + e.toString());
			}
		}
		LOGGER.debug(dataArr.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(dataArr);
		return null;
	}

	// 온라인 전용 교육과정 리스트 가져오기 (통합)
	@RequestMapping(value = "/educenter/trnng/onlineEdu.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_educenter_trnng_online_edu(
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject dataObj = new JSONObject();

		String year = mPublicUtils.currentTime("yyyy");

		LOGGER.debug("온라인 교육정보 조회 시작");
		try {
			eduCenterMainVO.setNotUsedPagination(true);
			eduCenterMainVO.setCRS_GRP_CD("CIDE00000000000");// 온라인교육
			eduCenterMainVO.setLOCK_ST("0");
			eduCenterMainVO.setRECRUIT_STR_DT(mPublicUtils.changePatternString(year + "-01-01", "yyyy-MM-dd"));
			eduCenterMainVO.setRECRUIT_END_DT(mPublicUtils.changePatternString(year + "-12-31", "yyyy-MM-dd"));
			List<EduCenterMainVO> list = eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			dataObj.put("error", "0");
			dataObj.put("rawdata", mapper.writeValueAsString(list));
		} catch (Exception e) {
			LOGGER.debug("[fail process] sql or data , " + e.toString());
			dataObj.put("error", "1");
		}
		LOGGER.debug(dataObj.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(dataObj);
		return null;
	}

	// 메인화면 교육과정 테이블 정보 가져오기
	@RequestMapping(value = "/educenter/trnng/ajax_calendar_table.do", method = RequestMethod.POST)
	public ModelAndView educenter_trnng_ajax_calendar(
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, HttpServletRequest request,
			ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("educenter/trnng/view_table");
		String trnng_year = request.getParameter("trnng_year");
		String trnng_month = request.getParameter("trnng_month");
		if (trnng_year == null || trnng_year.length() == 0 || trnng_month == null || trnng_month.length() == 0) {
			LOGGER.debug("교육정보 캘린더 조회 비정상적인 접근");
		} else {
			eduCenterMainVO.setNotUsedPagination(true);
			eduCenterMainVO.setCRS_STR_DT(trnng_year + "-" + trnng_month);
			eduCenterMainVO.setSearchCondition("LIKE_CRS_STR_DT");
			eduCenterMainVO.setCRS_TYPE("default");
			{
				eduCenterMainVO.setCRS_MBR_CD("CIDN010300");
				List<EduCenterMainVO> list_default = eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);
				model.addAttribute("list_default_1", list_default);
			}
			{
				eduCenterMainVO.setCRS_MBR_CD("CIDN010200");
				List<EduCenterMainVO> list_default = eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);
				model.addAttribute("list_default_2", list_default);
			}
		}
		return mModelAndView;
	}

	// 메인화면 교육과정 테이블 정보 가져오기 - 모바일
	@RequestMapping(value = "/educenter/m/trnng/ajax_calendar_table.do", method = RequestMethod.POST)
	public ModelAndView educenter_trnng_ajax_calendar_mobile(
			@ModelAttribute("eduCenterMainVO") EduCenterMainVO eduCenterMainVO, HttpServletRequest request,
			ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("educenter/mobile/trnng/view_table");
		String trnng_year = request.getParameter("trnng_year");
		String trnng_month = request.getParameter("trnng_month");
		if (trnng_year == null || trnng_year.length() == 0 || trnng_month == null || trnng_month.length() == 0) {
			LOGGER.debug("교육정보 캘린더 조회 비정상적인 접근");
		} else {
			eduCenterMainVO.setNotUsedPagination(true);
			eduCenterMainVO.setCRS_STR_DT(trnng_year + "-" + trnng_month);
			eduCenterMainVO.setSearchCondition("LIKE_CRS_STR_DT");
			eduCenterMainVO.setCRS_TYPE("default");
			{
				eduCenterMainVO.setCRS_MBR_CD("CIDN010300");
				List<EduCenterMainVO> list_default = eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);
				model.addAttribute("list_default_1", list_default);
			}
			{
				eduCenterMainVO.setCRS_MBR_CD("CIDN010200");
				List<EduCenterMainVO> list_default = eduCenterMainService.get_educenter_main_curriculum_list(eduCenterMainVO);
				model.addAttribute("list_default_2", list_default);
			}
		}
		return mModelAndView;
	}

	// 교육센터-온라인수강교육안내-이용안내 ------------------------------------------------
	@RequestMapping(value = "/educenter/main/serviceOnlineGuide.do")
	public String educenter_main_service_online_guide(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)온라인수강교육안내-이용안내");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/main/service_online_guide";
	}

	// 교육센터-온라인수강교육안내-이용안내(모바일) ------------------------------------------------
	@RequestMapping(value = "/educenter/m/main/serviceOnlineGuide.do")
	public String educenter_main_service_online_guide_mobile(HttpServletRequest request, ModelMap model)
			throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)온라인수강교육안내-이용안내");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/main/service_online_guide";
	}

	// 교육센터-교육안내-이용안내 ------------------------------------------------
	@RequestMapping(value = "/educenter/main/serviceGuide.do")
	public String educenter_main_service_guide(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육안내-이용안내");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/main/service_guide";
	}

	// 교육센터-교육안내-이용안내(모바일) ------------------------------------------------
	@RequestMapping(value = "/educenter/m/main/serviceGuide.do")
	public String educenter_main_service_guide_mobile(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육안내-이용안내");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/main/service_guide";
	}

	// 교육센터-교육안내-교육과정안내 ------------------------------------------------
	@RequestMapping(value = "/educenter/main/curriculumGuide.do")
	public String educenter_main_curriculum_guide(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육안내-교육과정안내");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/main/curriculum_guide";
	}

	// 교육센터-교육안내-교육과정안내(모바일) ------------------------------------------------
	@RequestMapping(value = "/educenter/m/main/curriculumGuide.do")
	public String educenter_main_curriculum_guide_mobile(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육안내-교육과정안내");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/main/curriculum_guide";
	}

	// 교육센터-교육안내-낚시관리및육성법 ------------------------------------------------
	@RequestMapping(value = "/educenter/main/fshMnCltVt.do")
	public String educenter_main_fshmn_cltvt(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육안내-낚시관리및육성법");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/main/fshmn_cltvt";
	}

	// 교육센터-교육안내-낚시관리및육성법(모바일) ------------------------------------------------
	@RequestMapping(value = "/educenter/m/main/fshMnCltVt.do")
	public String educenter_main_fshmn_cltvt_mobile(HttpServletRequest request, ModelMap model) throws Exception {
		// -----------------------------------------------------------
		// 접속 통계 반영
		// -----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)교육안내-낚시관리및육성법");
			if (loginVO != null) {
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch (Exception e) {
			LOGGER.debug("[fail analytics record] " + e.toString());
		}
		// -----------------------------------------------------------

		return "educenter/mobile/main/fshmn_cltvt";
	}

	// 본인명의 휴대폰 미소지자 정보 변경 개인정보 이용
	// 동의서--------------------------------------------
	/*@RequestMapping(value = "/educenter/rmndr/hp_agree.do")
	public String educenter_rmndr_hp_agree(HttpServletRequest request, ModelMap model) throws Exception {
		String birthDay = request.getParameter("birthDay");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNo");

		model.addAttribute("birthDay", birthDay);
		model.addAttribute("name", name);
		model.addAttribute("phoneNo", phoneNo);

		return "educenter/rmndr/hp_agree";
	}

	// 본인명의 휴대폰 미소지자 정보 변경 개인정보 이용 동의서-모바일
	// ---------------------------------------
	@RequestMapping(value = "/educenter/m/rmndr/hp_agree.do")
	public String educenter_mobile_rmndr_hp_agree(HttpServletRequest request, ModelMap model) throws Exception {
		String birthDay = request.getParameter("birthDay");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNo");

		model.addAttribute("birthDay", birthDay);
		model.addAttribute("name", name);
		model.addAttribute("phoneNo", phoneNo);

		return "educenter/mobile/rmndr/hp_agree";
	}*/

	// 본인명의 휴대폰 미소지자 정보 변경 --------------------------------------------
	@RequestMapping(value = "/educenter/rmndr/hpreqstdoc.do")
	public String educenter_rmndr_hp_reqst_doc(@ModelAttribute("mbrHpChngVO") MbrHpChngVO mbrHpChngVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model) throws Exception {

		String birthDay = request.getParameter("birthDay");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNo");

		model.addAttribute("birthDay", birthDay);
		model.addAttribute("name", name);
		model.addAttribute("phoneNo", phoneNo);

		if (mbrHpChngVO.getMHC_INDVDL_INFO_ST() == null) {
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "약관 동의는 필수입니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/rmndr/hp_agree.do";
		}

		model.addAttribute("MHC_INDVDL_INFO_ST", mbrHpChngVO.getMHC_INDVDL_INFO_ST());

		return "educenter/rmndr/hpreqstdoc";
	}

	// 본인명의 휴대폰 미소지자 정보 변경 -모바일 ---------------------------------------
	@RequestMapping(value = "/educenter/m/rmndr/hpreqstdoc.do")
	public String educenter_mobile_rmndr_hp_reqst_doc(@ModelAttribute("mbrHpChngVO") MbrHpChngVO mbrHpChngVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model) throws Exception {

		String birthDay = request.getParameter("birthDay");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNo");

		model.addAttribute("birthDay", birthDay);
		model.addAttribute("name", name);
		model.addAttribute("phoneNo", phoneNo);

		if (mbrHpChngVO.getMHC_INDVDL_INFO_ST() == null) {
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "약관 동의는 필수입니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/mobile/rmndr/hp_agree.do";
		}

		model.addAttribute("MHC_INDVDL_INFO_ST", mbrHpChngVO.getMHC_INDVDL_INFO_ST());

		return "educenter/mobile/rmndr/hpreqstdoc";
	}

	@RequestMapping(value = "/educenter/rmndr/hpreqstdoc_act.do")
	public @ResponseBody String educenter_rmndr_hp_reqst_doc_act(@ModelAttribute("mbrHpChngVO") MbrHpChngVO mbrHpChngVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map<String, String> map = new HashMap<String, String>();

		try {

			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			if (loginVO != null) {
				mbrHpChngVO.setMHC_REG_ID(loginVO.getMBR_ID());
			} else {
				mbrHpChngVO.setMHC_REG_ID("guest");
			}

			AdmMemberVO admMemberVO = new AdmMemberVO();
			admMemberVO.setMBR_NM(mbrHpChngVO.getMHC_NM());
			admMemberVO.setMBR_HP(mbrHpChngVO.getMHC_HP());
			admMemberVO.setMBR_BIRTH(mbrHpChngVO.getMHC_BRTHDY());

			admMemberVO.setMBR_ST("Y");
			List<AdmMemberVO> list = admMemberService.get_member_all_info_list(admMemberVO);

			if (!list.isEmpty() && list.size() > 0) {
				mbrHpChngVO.setMHC_MBR_ID(list.get(0).getMBR_ID());
				// 리스트가 여러개 일 수 있으니 첫번째(먼저가입한) id만 가져옴
				mbrHpChngVO.setMHC_MBR_ID_CNT(list.size() + "");
			}
			eduCenterMainService.set_educenter_mbr_hp_chng_reg(mbrHpChngVO);

		} catch (Exception e) {
			map.put("error", "1");
			map.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}

		JSONObject data = new JSONObject(map);
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);

		return null;
	}

/*	// 법인사업장 교육책임자 지정 개인정보 이용 동의서--------------------------------------------
	@RequestMapping(value = "/educenter/rmndr/cpr_agree.do")
	public String educenter_rmndr_cpr_agree(HttpServletRequest request, ModelMap model) throws Exception {

		return "educenter/rmndr/cpr_agree";
	}

	// 법인사업장 교육책임자 지정 개인정보 이용 동의서-모바일 ---------------------------------------
	@RequestMapping(value = "/educenter/m/rmndr/cpr_agree.do")
	public String educenter_mobile_rmndr_cpr_agree(HttpServletRequest request, ModelMap model) throws Exception {

		return "educenter/mobile/rmndr/cpr_agree";
	}*/

	// 법인사업장 교육책임자 지정 확인서 --------------------------------------------
	@RequestMapping(value = "/educenter/rmndr/cpr_bplc.do")
	public String educenter_rmndr_cpr_bplc(@ModelAttribute("eduCprBplcVO") EduCprBplcVO eduCprBplcVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model) throws Exception {

		if (eduCprBplcVO.getECB_INDVDL_INFO_ST() == null) {
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "약관 동의는 필수입니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/rmndr/cpr_agree.do";
		}

		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_address_cd", list_address_cd);
		}

		model.addAttribute("ECB_DTL_CD", eduCprBplcVO.getECB_DTL_CD());
		model.addAttribute("ECB_INDVDL_INFO_ST", eduCprBplcVO.getECB_INDVDL_INFO_ST());

		return "educenter/rmndr/cpr_bplc";
	}

	// 법인사업장 교육책임자 지정 확인서 --------------------------------------------
	@RequestMapping(value = "/educenter/m/rmndr/cpr_bplc.do")
	public String educenter_mobile_rmndr_cpr_bplc(@ModelAttribute("eduCprBplcVO") EduCprBplcVO eduCprBplcVO,
			RedirectAttributes redirectAttributes, HttpServletRequest request, ModelMap model) throws Exception {

		if (eduCprBplcVO.getECB_INDVDL_INFO_ST() == null) {
			Map<String, Object> postMap = new HashMap<String, Object>();
			postMap.put("message", "약관 동의는 필수입니다.");
			redirectAttributes.addFlashAttribute("alert_data", postMap);
			return "redirect:/educenter/mobile/rmndr/cpr_agree.do";
		}

		// 지역 코드 조회 - 시도
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("CID00004");
			List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			model.addAttribute("list_address_cd", list_address_cd);
		}

		model.addAttribute("ECB_DTL_CD", eduCprBplcVO.getECB_DTL_CD());
		model.addAttribute("ECB_INDVDL_INFO_ST", eduCprBplcVO.getECB_INDVDL_INFO_ST());

		return "educenter/mobile/rmndr/cpr_bplc";
	}

	@RequestMapping(value = "/educenter/rmndr/cpr_bplc_act.do")
	public @ResponseBody String educenter_rmndr_cpr_bplc_act(@ModelAttribute("eduCprBplcVO") EduCprBplcVO eduCprBplcVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map<String, String> map = new HashMap<String, String>();

		try {
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
			if (loginVO != null) {
				eduCprBplcVO.setECB_REG_ID(loginVO.getMBR_ID());
			} else {
				eduCprBplcVO.setECB_REG_ID("guest");
			}

			eduCprBplcVO.setECB_DTL_LICENSE_CD("CIDL00002"); 
			// 법인업자 코드
			eduCenterMainService.set_educenter_cpr_bplc_reg(eduCprBplcVO);

		} catch (Exception e) {
			map.put("error", "1");
			map.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}

		JSONObject data = new JSONObject(map);
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);

		return null;
	}

}
