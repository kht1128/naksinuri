package egovframework.educenter.myhistory.web;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.all.main.service.KakaoAlimTalkService;
import egovframework.all.main.service.KakaoAlimTalkVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.certificate.web.CreateCertificateToHtmlData;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.educenter.board.service.EduCenterBoardService;
import egovframework.educenter.myhistory.service.MyHistoryService;
import egovframework.educenter.myhistory.service.MyHistoryVO;
import egovframework.educenter.service.EduCenterMainService;
import egovframework.educenter.service.EduCenterMainVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsVO;
import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;
import egovframework.naksinuri_original.let.naksinuri.web.NaksiBoardController;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.seadm.analytics.service.AnalyticsAdmService;
import egovframework.seadm.analytics.service.AnalyticsAdmVO;
import egovframework.utils.PublicUtils;
import egovframework.utils.EgovDateUtil;
import egovframework.utils.EgovDateUtil.RETURN_MIN_TYPE;
import egovframework.utils.EgovStringUtil;


@Controller
@EnableWebMvc
public class EduCenterMyHistoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterMyHistoryController.class);
	
	/** LogRecordService */
	@Resource(name = "logRecordService")
	private  LogRecordService logRecordService;
	
	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private  EduMemberService eduMemberService;
		
	/** EduCategoryService */
	@Resource(name = "myHistoryService")
	private MyHistoryService myHistoryService;
	
	/** EduCenterMainService */
	@Resource(name = "eduCenterMainService")
	private  EduCenterMainService eduCenterMainService;
	
	/** EduCertificateService */
	@Resource(name = "eduCertificateService")
	private  EduCertificateService eduCertificateService;
	
	/** EduCurriculumService */
	@Resource(name = "eduCurriculumService")
	private EduCurriculumService eduCurriculumService;
	
	/** EgovEducenterService */
	@Resource(name = "eduCenterService")
	private EduCenterService eduCenterService;
	
	/** EduMyHistoryService */
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;

	@Resource(name = "AnalyticsAdmService")
	private AnalyticsAdmService analyticsAdmService;
	
	/** BoardService */
	@Resource(name = "eduCenterBoardService")
	private EduCenterBoardService eduCenterBoardService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "eduCategoryService")
	private EduCategoryService eduCategoryService;
	
	@Resource(name="NaksinuriService")
	private NaksinuriService service;
	
	@Resource(name = "smsManagerService")
	SmsManagerService smsManagerService;
	
	@Resource(name="kakaoAlimTalkService")
	private KakaoAlimTalkService kakaoAlimTalkService;
	
	//나의강의실 - 수강현황 - 리스트 ------------------------------------------------
	@RequestMapping(value = "/educenter/mbrhstry/list.do")
	public String educenter_mbrhstry_list(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/error/login";
		}
		
		//회원 정보 조회
		EduMemberVO eduMemberVO = new EduMemberVO();
		eduMemberVO.setMBR_ID(loginVO.getMBR_ID());
		eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
		model.addAttribute("memberInfo",eduMemberVO);
		//
		
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_cd",list_mbr_cd);
		}
		//사업자구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00006");
	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_license_se_cd",list_position_cd);
		}
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		//설문조사 노출여부(관리자가 설정)
		{
			
		}
		
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myHistoryVO.getPageUnit());
		paginationInfo.setPageSize(myHistoryVO.getPageSize());

		myHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		myHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		myHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		myHistoryVO.setLRNNG_ST("ok");//대기,승인 건만 출력
		
		List<MyHistoryVO> list = myHistoryService.get_educenter_mbrhstry_list(myHistoryVO);		
		int totCnt = myHistoryService.get_educenter_mbrhstry_list_totcnt(myHistoryVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("list",list);
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)나의강의실-수강현황");
			if(loginVO != null){
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch(Exception e) { 
			LOGGER.debug("[fail analytics record] "+e.toString());
		}
		//-----------------------------------------------------------
		
		
		return "educenter/mbrhstry/list";
	}
	//나의강의실 - 수강현황 - 리스트 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/mbrhstry/list.do")
	public String educenter_mbrhstry_list_mobile(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
				
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/mobile/error/login";
		}
		
		//회원 정보 조회
		EduMemberVO eduMemberVO = new EduMemberVO();
		eduMemberVO.setMBR_ID(loginVO.getMBR_ID());
		eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
		model.addAttribute("memberInfo",eduMemberVO);
		//
		
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_cd",list_mbr_cd);
		}
		//사업자구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00006");
	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_license_se_cd",list_position_cd);
		}
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myHistoryVO.getPageUnit());
		paginationInfo.setPageSize(myHistoryVO.getPageSize());

		myHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		myHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		myHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		myHistoryVO.setLRNNG_ST("ok");//대기,승인 건만 출력
		
		List<MyHistoryVO> list = myHistoryService.get_educenter_mbrhstry_list(myHistoryVO);
		int totCnt = myHistoryService.get_educenter_mbrhstry_list_totcnt(myHistoryVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("list",list);	
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)나의강의실-수강현황");
			if(loginVO != null){
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch(Exception e) { 
			LOGGER.debug("[fail analytics record] "+e.toString());
		}
		//-----------------------------------------------------------
		
		return "educenter/mobile/mbrhstry/list";
	}
	//나의강의실 - 수강현황 - 상세리스트  ------------------------------------------------
	@RequestMapping(value = "/educenter/mbrhstry/listDtl.do")
	public String educenter_mbrhstry_listDtl(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/error/login";
		}
		
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());//로그인 되어 있다 가정
		myHistoryVO.setNotUsedPagination(true);//페이징 처리 안함
			
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myHistoryVO.getPageUnit());
		paginationInfo.setPageSize(myHistoryVO.getPageSize());

		myHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		myHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		myHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<MyHistoryVO> list = myHistoryService.get_educenter_mbrhstry_dtl_list(myHistoryVO);
		
		int totCnt = myHistoryService.get_educenter_mbrhstry_dtl_list_totcnt(myHistoryVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		//교육과정 정보
		EduCenterMainVO eduCenterMainVO = new EduCenterMainVO();
		eduCenterMainVO.setCRS_SN(myHistoryVO.getCRS_SN());
		if(myHistoryVO.getCRS_SN()==null || myHistoryVO.getCRS_SN().length()==0) {
			Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "비정상적인 접근으로 거부되었습니다.");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			LOGGER.debug("비 정상적인 접근으로 불가");
			return "redirect:/educenter/mbrhstry/list.do";
		}		
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		model.addAttribute("parentInfo",eduCenterMainVO);		
		if(eduCenterMainVO==null || eduCenterMainVO.getCRS_SN()==null || eduCenterMainVO.getCRS_SN().length()==0) {
			Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "해당 교육은 존재하지 않거나 종료되어 더 이상 수강 하실 수 없습니다.");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			LOGGER.debug("교육정보가 없으므로 수강불가");
			return "redirect:/educenter/mbrhstry/list.do";
		} else {
			PublicUtils.RETURN_COMPARE_TYPE dateStart = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(eduCenterMainVO.getCRS_STR_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss" 
					);
			PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss" 
					);
			
			if(dateStart.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)) {//교육기간 전
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("message", "해당 교육의 수강기간이 아닙니다.");
				redirectAttributes.addFlashAttribute("alert_data",postMap);
				LOGGER.debug("교육기간 전은 수강불가");
				return "redirect:/educenter/mbrhstry/list.do";
			} else if (dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {//교육기간 후
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("message", "해당 교육은 종료되어 더 이상 수강 하실 수 없습니다.");
				redirectAttributes.addFlashAttribute("alert_data",postMap);
				LOGGER.debug("교육기간이 종료되어 수강불가");
				return "redirect:/educenter/mbrhstry/list.do";
			}
		}
		
		//교육과정 커리큘럼 상세 정보
		EduCenterMainVO eduDtlCenterMainVO = new EduCenterMainVO();
		eduDtlCenterMainVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
		eduDtlCenterMainVO.setCRS_SN(myHistoryVO.getCRS_SN());
		eduDtlCenterMainVO.setSTEP_ST(eduCenterMainVO.getSTEP_ST());
		eduDtlCenterMainVO.setNotUsedPagination(true);
		List<EduCenterMainVO> curriculum_dtl_list = eduCenterMainService.get_educenter_curriculum_dtl_list(eduDtlCenterMainVO);
		model.addAttribute("curriculum_dtl_list",curriculum_dtl_list);
		
		//수강내역(메인) 정보
		MyHistoryVO parentInfo2 = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
		model.addAttribute("parentInfo2",parentInfo2);
				
		if(eduCenterMainVO==null || parentInfo2==null) {
			return "educenter/mbrhstry/list";
		}
		
		return "educenter/mbrhstry/list_dtl";
	}
	//나의강의실 - 수강현황 - 상세리스트  -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/mbrhstry/listDtl.do")
	public String educenter_mbrhstry_listDtl_mobile(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
				
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/mobile/error/login";
		}
		
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());//로그인 되어 있다 가정
		myHistoryVO.setNotUsedPagination(true);//페이징 처리 안함
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myHistoryVO.getPageUnit());
		paginationInfo.setPageSize(myHistoryVO.getPageSize());

		myHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		myHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		myHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<MyHistoryVO> list = myHistoryService.get_educenter_mbrhstry_dtl_list(myHistoryVO);
		
		int totCnt = myHistoryService.get_educenter_mbrhstry_dtl_list_totcnt(myHistoryVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		//교육과정 정보
		EduCenterMainVO eduCenterMainVO = new EduCenterMainVO();
		eduCenterMainVO.setCRS_SN(myHistoryVO.getCRS_SN());
		if(myHistoryVO.getCRS_SN()==null || myHistoryVO.getCRS_SN().length()==0) {
			Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "비정상적인 접근으로 거부되었습니다.");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			LOGGER.debug("비 정상적인 접근으로 불가");
			return "redirect:/educenter/mbrhstry/list.do";
		}	
		eduCenterMainVO = eduCenterMainService.get_educenter_curriculum_info(eduCenterMainVO);
		model.addAttribute("parentInfo",eduCenterMainVO);		
		if(eduCenterMainVO==null || eduCenterMainVO.getCRS_SN()==null || eduCenterMainVO.getCRS_SN().length()==0) {
			Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "해당 교육은 존재하지 않거나 종료되어 더 이상 수강 하실 수 없습니다.");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			LOGGER.debug("교육정보가 없으므로 수강불가");
			return "redirect:/educenter/m/mbrhstry/list.do";
		} else {
			PublicUtils.RETURN_COMPARE_TYPE dateStart = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(eduCenterMainVO.getCRS_STR_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss" 
					);
			PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
					mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
					mPublicUtils.changePatternString(eduCenterMainVO.getCRS_END_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss" 
					);
			if(dateStart.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)) {//교육기간 전
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("message", "해당 교육의 수강기간이 아닙니다.");
				redirectAttributes.addFlashAttribute("alert_data",postMap);
				LOGGER.debug("교육기간 전은 수강불가");
				return "redirect:/educenter/m/mbrhstry/list.do";
			} else if(dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {//교육기간 후
				Map<String, Object> postMap = new HashMap<String,Object>();
				postMap.put("message", "해당 교육은 종료되어 더 이상 수강 하실 수 없습니다.");
				redirectAttributes.addFlashAttribute("alert_data",postMap);
				LOGGER.debug("교육기간이 종료되어 수강불가");
				return "redirect:/educenter/m/mbrhstry/list.do";
			}
		}
				
		//교육과정 커리큘럼 상세 정보
		EduCenterMainVO eduDtlCenterMainVO = new EduCenterMainVO();
		eduDtlCenterMainVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
		eduDtlCenterMainVO.setCRS_SN(myHistoryVO.getCRS_SN());
		eduDtlCenterMainVO.setSTEP_ST(eduCenterMainVO.getSTEP_ST());
		eduDtlCenterMainVO.setNotUsedPagination(true);
		List<EduCenterMainVO> curriculum_dtl_list = eduCenterMainService.get_educenter_curriculum_dtl_list(eduDtlCenterMainVO);
		model.addAttribute("curriculum_dtl_list",curriculum_dtl_list);
		
		//수강내역(메인) 정보
		MyHistoryVO parentInfo2 = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
		model.addAttribute("parentInfo2",parentInfo2);
		
		if(eduCenterMainVO==null || parentInfo2==null) {
			return "educenter/mbrhstry/list";
		}
		
		return "educenter/mobile/mbrhstry/list_dtl";
	}
	//나의강의실 - 수강현황 - 상세리스트 - 동영상플레이어  ------------------------------------------------
	@RequestMapping(value = "/educenter/mbrhstry/videoplayer.do", method = RequestMethod.POST)
	public ModelAndView educenter_mbrhstry_videoplayer(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {	
		
		EgovStringUtil egovStringUtil = new EgovStringUtil();
		egovStringUtil.AES128Util();
		
		ModelAndView mModelAndView = new ModelAndView();
		String returnUrl = "educenter/popup/videoplayer";
		
		/***
	 	 * 웹접근성 포커스 관련추가
	 	 **/
		String focusIndex = "";
		focusIndex = request.getParameter("focusindex");
		model.addAttribute("focus_index",focusIndex);
		/*웹접근성 포커스 관련추가 */
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			returnUrl = "educenter/error/login";
		} else {
			myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
			
			//교과목(메인) 정보
			MyHistoryVO parentInfo = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
			mModelAndView.addObject("parentInfo",parentInfo);
			
			//교과목(상세)
			MyHistoryVO info = myHistoryService.get_educenter_mbrhstry_dtl_info(myHistoryVO);
			mModelAndView.addObject("info",info);
			
			//교과목 리스트 정보
			myHistoryVO.setNotUsedPagination(true);
			List<MyHistoryVO> list_dtl = myHistoryService.get_educenter_mbrhstry_dtl_list(myHistoryVO);
			mModelAndView.addObject("list_dtl",list_dtl);
			
			String PLAY_LOCK_CD = egovStringUtil.makeScrtyKey(myHistoryVO.getHMBR_DTL_SN());
			
			//동영상 중복실행방지 처리
			
			if(!info.getLRNNG_PRGRS().equals("1.0")) {
				//처음 켰을 때 (play_lock_cd가 없을때)
				if(parentInfo.getPLAY_LOCK_CD() == null || parentInfo.getPLAY_LOCK_CD().equals("") ){
					MyHistoryVO overlayLockPlayMyHistoryVO = new MyHistoryVO();
	//				overlayLockPlayMyHistoryVO.setPLAY_LOCK_CD(overlayLockPlayMyHistoryVO.getUniqKey("PLAY"));
					overlayLockPlayMyHistoryVO.setPLAY_LOCK_CD(PLAY_LOCK_CD);
					overlayLockPlayMyHistoryVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
					overlayLockPlayMyHistoryVO.setCRS_SN(myHistoryVO.getCRS_SN());
					overlayLockPlayMyHistoryVO.setMBR_ID(myHistoryVO.getMBR_ID());
					myHistoryService.set_educenter_mbrhstry_playlockcode_update(overlayLockPlayMyHistoryVO);
					model.addAttribute("PLAY_LOCK_CD",overlayLockPlayMyHistoryVO.getPLAY_LOCK_CD());	
				} else {
					//중복 실행 됐을 때 (다른 영상을 같이 켰을 경우)
					if(!PLAY_LOCK_CD.equals(parentInfo.getPLAY_LOCK_CD())) {
						model.addAttribute("PLAY_LOCK_YN", "Y");
					} else {
						model.addAttribute("PLAY_LOCK_YN", "N");
					}				
				}
			}
			
			
			//End of 동영상 중복실행방지 처리			
			
		}
		mModelAndView.setViewName(returnUrl);
		return mModelAndView;
	}	
	//나의강의실 - 수강현황 - 상세리스트 - 동영상플레이어  -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/mbrhstry/videoplayer.do", method = RequestMethod.POST)
	public ModelAndView educenter_mbrhstry_videoplayer_mobile(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {	
		
		EgovStringUtil egovStringUtil = new EgovStringUtil();
		egovStringUtil.AES128Util();
		
		ModelAndView mModelAndView = new ModelAndView();
		String returnUrl = "educenter/mobile/popup/videoplayer";
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			returnUrl = "educenter/mobile/error/login";
		} else {
			myHistoryVO.setMBR_ID(loginVO.getMBR_ID());//로그인 되어 있다 가정
			
			//교과목(메인) 정보
			MyHistoryVO parentInfo = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
			mModelAndView.addObject("parentInfo",parentInfo);
			
			//교과목(상세)
			MyHistoryVO info = myHistoryService.get_educenter_mbrhstry_dtl_info(myHistoryVO);
			mModelAndView.addObject("info",info);
			
			//교과목 리스트 정보
			myHistoryVO.setNotUsedPagination(true);
			List<MyHistoryVO> list_dtl = myHistoryService.get_educenter_mbrhstry_dtl_list(myHistoryVO);
			mModelAndView.addObject("list_dtl",list_dtl);
			
			String PLAY_LOCK_CD = egovStringUtil.makeScrtyKey(myHistoryVO.getHMBR_DTL_SN());
			
			//동영상 중복실행방지 처리
			
			if(!info.getLRNNG_PRGRS().equals("1.0")) {
				//처음 켰을 때 (play_lock_cd가 없을때)
				if(parentInfo.getPLAY_LOCK_CD() == null || parentInfo.getPLAY_LOCK_CD().equals("") ){
					MyHistoryVO overlayLockPlayMyHistoryVO = new MyHistoryVO();
	//				overlayLockPlayMyHistoryVO.setPLAY_LOCK_CD(overlayLockPlayMyHistoryVO.getUniqKey("PLAY"));
					overlayLockPlayMyHistoryVO.setPLAY_LOCK_CD(PLAY_LOCK_CD);
					overlayLockPlayMyHistoryVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
					overlayLockPlayMyHistoryVO.setCRS_SN(myHistoryVO.getCRS_SN());
					overlayLockPlayMyHistoryVO.setMBR_ID(myHistoryVO.getMBR_ID());
					myHistoryService.set_educenter_mbrhstry_playlockcode_update(overlayLockPlayMyHistoryVO);
					model.addAttribute("PLAY_LOCK_CD",overlayLockPlayMyHistoryVO.getPLAY_LOCK_CD());	
				} else {
					//중복 실행 됐을 때 (다른 영상을 같이 켰을 경우)
					if(!PLAY_LOCK_CD.equals(parentInfo.getPLAY_LOCK_CD())) {
						model.addAttribute("PLAY_LOCK_YN", "Y");
					} else {
						model.addAttribute("PLAY_LOCK_YN", "N");
					}				
				}
			}
						
/*			//동영상 중복실행방지 처리
			MyHistoryVO overlayLockPlayMyHistoryVO = new MyHistoryVO();
			overlayLockPlayMyHistoryVO.setPLAY_LOCK_CD(overlayLockPlayMyHistoryVO.getUniqKey("PLAY"));
			overlayLockPlayMyHistoryVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
			overlayLockPlayMyHistoryVO.setCRS_SN(myHistoryVO.getCRS_SN());
			overlayLockPlayMyHistoryVO.setMBR_ID(myHistoryVO.getMBR_ID());
			myHistoryService.set_educenter_mbrhstry_playlockcode_update(overlayLockPlayMyHistoryVO);
			model.addAttribute("PLAY_LOCK_CD",overlayLockPlayMyHistoryVO.getPLAY_LOCK_CD());
			//End of 동영상 중복실행방지 처리
*/						
		}
		mModelAndView.setViewName(returnUrl);
		return mModelAndView;
	}	
	
	@RequestMapping(value = "/educenter/mbrhstry/ajaxUpdateEduCheck.do")
	public @ResponseBody String educenter_mbrhstry_ajax(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, HttpServletRequest request) throws Exception {
		EgovStringUtil egovStringUtil = new EgovStringUtil();
		egovStringUtil.AES128Util();
		
		String PLAY_LOCK_CD = egovStringUtil.makeScrtyKey(myHistoryVO.getHMBR_DTL_SN());
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		//세션 없는 경우 처리
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			String EXTERNAL_MBR_ID = (String)request.getSession().getAttribute("EXTERNAL_MBR_ID");
			myHistoryVO.setMBR_ID(EXTERNAL_MBR_ID);
		} else {
			myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		}
		
		myHistoryVO.setPLAY_LOCK_CD(PLAY_LOCK_CD);
		
		myHistoryService.set_educenter_mbrhstry_playlockcode_update(myHistoryVO);
		
		return null;
	}		
	//외부 링크 URL - 동영상플레이어  -- PC/모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/mbrhstry/external/play.do", method = RequestMethod.GET)
	public Object educenter_mbrhstry_videoplayer_external(@RequestParam("key") String key, HttpServletRequest request, ModelMap model) throws Exception {
		
		EgovStringUtil egovStringUtil = new EgovStringUtil();
		egovStringUtil.AES128Util();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		/* 키생성 
		String test = key;
		System.out.println(test = EgovFileScrty.encode(key));
		System.out.println(test);
		System.out.println(test = EgovFileScrty.decode(test));
		System.out.println(test);
		
		// SE1CUkRfMjAwNzE0MTAwNDExNTcwMA
		// HMBRD_2007141004115700
	  
		*/
		LOGGER.debug(EgovFileScrty.encode(key));
				
		boolean isException = false;
		String error_msg = "";
		log_dscrp.append("[교육센터사용자-외부링크-동영상수강]");
				
		String returnUrl = "educenter/popup/videoplayer_external";
		PublicUtils mPublicUtils = new PublicUtils();
		if(mPublicUtils.isMobileDevice(request)) {
			log_dscrp.append("[모바일]");
			returnUrl = "educenter/mobile/popup/videoplayer_external";
		} else {
			log_dscrp.append("[PC]");
		}
		EduMemberVO mEduMemberVO = new EduMemberVO();
		MyHistoryVO myHistoryVO = new MyHistoryVO();
		MyHistoryVO chkDtlMyHistoryVO = new MyHistoryVO();
		if(key!=null && key.length()!=0) {
			String HMBR_DTL_SN = EgovFileScrty.decode(key);
			log_msg.append("[HMBR_DTL_SN="+HMBR_DTL_SN+",key="+key+"]");
			LOGGER.debug("HMBR_DTL_SN = " + HMBR_DTL_SN);
			chkDtlMyHistoryVO.setHMBR_DTL_SN(HMBR_DTL_SN);
			chkDtlMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_info(chkDtlMyHistoryVO);
			if(chkDtlMyHistoryVO!=null && chkDtlMyHistoryVO.getHMBR_DTL_SN()!=null && chkDtlMyHistoryVO.getHMBR_DTL_SN().length()!=0) {
				if(chkDtlMyHistoryVO.getLRNNG_CMPLT_DTL_ST().equals("1")) {
					log_dscrp.append("[이수완료된동영상]");
					
					myHistoryVO.setMBR_ID(mEduMemberVO.getMBR_ID());
					myHistoryVO.setCRS_SN(chkDtlMyHistoryVO.getCRS_SN());
					myHistoryVO.setHMBR_SN(chkDtlMyHistoryVO.getHMBR_SN());
					//교과목(메인) 정보
					MyHistoryVO parentInfo = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
					model.addAttribute("parentInfo",parentInfo);
					
					//교과목(상세)
					model.addAttribute("info",chkDtlMyHistoryVO);
					
					//교과목 리스트 정보
					myHistoryVO.setNotUsedPagination(true);
					List<MyHistoryVO> list_dtl = myHistoryService.get_educenter_mbrhstry_dtl_list(myHistoryVO);
					model.addAttribute("list_dtl",list_dtl);
								
					//동영상 중복실행방지 처리
					String PLAY_LOCK_CD = "";
//					boolean isInitExternalPlay = false;
//					String dateInitExternalPlay = "";
//					String keyInitExternalPlay = "";
//					try { 
//						isInitExternalPlay = (boolean)request.getSession().getAttribute("isInitExternalPlay"+key);
//						dateInitExternalPlay = (String)request.getSession().getAttribute("dateInitExternalPlay"+key);
//						keyInitExternalPlay = (String)request.getSession().getAttribute("keyInitExternalPlay"+key);
//					} catch(Exception e) { 
//						LOGGER.debug("화면내 자체갱신으로 중복키를 재발급 여부 판단 , error : " + e.toString());
//					} finally {
//						request.getSession().removeAttribute("isInitExternalPlay"+key);
//						request.getSession().removeAttribute("dateInitExternalPlay"+key);
//						request.getSession().removeAttribute("keyInitExternalPlay"+key);
//					}
//					if(isInitExternalPlay) {
//						log_dscrp.append("[화면내 자체갱신으로 중복키를 재발급하지않음("+dateInitExternalPlay+")("+keyInitExternalPlay+")]");
//						PLAY_LOCK_CD = keyInitExternalPlay;
//					} else {
//						MyHistoryVO overlayLockPlayMyHistoryVO = new MyHistoryVO();
//						PLAY_LOCK_CD = overlayLockPlayMyHistoryVO.getUniqKey("PLAY");
//					}
//					MyHistoryVO overlayLockPlayMyHistoryVO = new MyHistoryVO();
//					overlayLockPlayMyHistoryVO.setPLAY_LOCK_CD(PLAY_LOCK_CD);
//					overlayLockPlayMyHistoryVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
//					overlayLockPlayMyHistoryVO.setCRS_SN(myHistoryVO.getCRS_SN());
//					overlayLockPlayMyHistoryVO.setMBR_ID(myHistoryVO.getMBR_ID());
//					myHistoryService.set_educenter_mbrhstry_playlockcode_update(overlayLockPlayMyHistoryVO);
//					model.addAttribute("PLAY_LOCK_CD",PLAY_LOCK_CD);
//					//End of 동영상 중복실행방지 처리
					
					model.addAttribute("key",key);
//					error_msg = chkDtlMyHistoryVO.getCRS_DTL_NM()+"<br>["+chkDtlMyHistoryVO.getTRN_NM()+"]<br>"+"위 교과목은 시청이 완료되었습니다.";
					isException = false;
				} else {
					mEduMemberVO.setMBR_ID(chkDtlMyHistoryVO.getMBR_ID());
					mEduMemberVO = eduMemberService.get_edu_member_info(mEduMemberVO);
					if(mEduMemberVO!=null && mEduMemberVO.getMBR_SN()!=null && mEduMemberVO.getMBR_SN().length()!=0) {					
						
						myHistoryVO.setMBR_ID(mEduMemberVO.getMBR_ID());
						myHistoryVO.setCRS_SN(chkDtlMyHistoryVO.getCRS_SN());
						myHistoryVO.setHMBR_SN(chkDtlMyHistoryVO.getHMBR_SN());
						//교과목(메인) 정보
						MyHistoryVO parentInfo = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
						model.addAttribute("parentInfo",parentInfo);
						model.addAttribute("surveyUrl","/educenter/mbrhstry/external/survey.do?key=" + EgovFileScrty.encode(parentInfo.getHMBR_SN()+","+parentInfo.getCRS_SN()));
						
						//교과목(상세)
						model.addAttribute("info",chkDtlMyHistoryVO);
						
						//교과목 리스트 정보
						myHistoryVO.setNotUsedPagination(true);
						List<MyHistoryVO> list_dtl = myHistoryService.get_educenter_mbrhstry_dtl_list(myHistoryVO);
						model.addAttribute("list_dtl",list_dtl);
									
						//동영상 중복실행방지 처리
//						String PLAY_LOCK_CD = "";
//						boolean isUpdatePlayLockCd = false;
//						boolean isInitExternalPlay = false;
//						String dateInitExternalPlay = "";
//						String keyInitExternalPlay = "";
//						try { 
//							isInitExternalPlay = (boolean)request.getSession().getAttribute("isInitExternalPlay"+key);
//							dateInitExternalPlay = (String)request.getSession().getAttribute("dateInitExternalPlay"+key);
//							keyInitExternalPlay = (String)request.getSession().getAttribute("keyInitExternalPlay"+key);
//						} catch(Exception e) { 
//							LOGGER.debug("화면내 자체갱신으로 중복키를 재발급 여부 판단 , error : " + e.toString());
//						} finally {
//							request.getSession().removeAttribute("isInitExternalPlay"+key);
//							request.getSession().removeAttribute("dateInitExternalPlay"+key);
//							request.getSession().removeAttribute("keyInitExternalPlay"+key);
//						}
//						if(isInitExternalPlay) {
//							log_dscrp.append("[화면내 자체갱신으로 중복키를 재발급하지않음("+dateInitExternalPlay+")("+keyInitExternalPlay+")]");
//							PLAY_LOCK_CD = keyInitExternalPlay;
//						} else {
//							MyHistoryVO overlayLockPlayMyHistoryVO = new MyHistoryVO();
//							PLAY_LOCK_CD = overlayLockPlayMyHistoryVO.getUniqKey("PLAY");
//							log_dscrp.append("[중복키를 발급("+PLAY_LOCK_CD+")]");
//							isUpdatePlayLockCd = true;
//						}
						// 몇초내 업데이트 체크 후 업데이트 방지
						parentInfo = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
						
						String PLAY_LOCK_CD = egovStringUtil.makeScrtyKey(HMBR_DTL_SN);
						
						//mbr_id 세션에 저장
						request.getSession().setAttribute("EXTERNAL_MBR_ID", parentInfo.getMBR_ID());
						
						//동영상 중복실행방지 처리
						
						//처음 켰을 때 (play_lock_cd가 없을때)
						if(parentInfo.getPLAY_LOCK_CD() == null || parentInfo.getPLAY_LOCK_CD().equals("") ){
							MyHistoryVO overlayLockPlayMyHistoryVO = new MyHistoryVO();
//							overlayLockPlayMyHistoryVO.setPLAY_LOCK_CD(overlayLockPlayMyHistoryVO.getUniqKey("PLAY"));
							overlayLockPlayMyHistoryVO.setPLAY_LOCK_CD(PLAY_LOCK_CD);
							overlayLockPlayMyHistoryVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
							overlayLockPlayMyHistoryVO.setCRS_SN(myHistoryVO.getCRS_SN());
							overlayLockPlayMyHistoryVO.setMBR_ID(myHistoryVO.getMBR_ID());
							myHistoryService.set_educenter_mbrhstry_playlockcode_update(overlayLockPlayMyHistoryVO);
							model.addAttribute("PLAY_LOCK_CD",overlayLockPlayMyHistoryVO.getPLAY_LOCK_CD());	
						} else {
							//중복 실행 됐을 때 (다른 영상을 같이 켰을 경우)
							if(!PLAY_LOCK_CD.equals(parentInfo.getPLAY_LOCK_CD())) {
								model.addAttribute("PLAY_LOCK_YN", "Y");
							} else {
								model.addAttribute("PLAY_LOCK_YN", "N");
							}				
						}							

						model.addAttribute("PLAY_LOCK_CD",PLAY_LOCK_CD);
						//End of 동영상 중복실행방지 처리
						model.addAttribute("key",key);
						
					} else {
						log_dscrp.append("[사용자정보를알수없음]");
						error_msg = "이수 가능한 사용자가 아닙니다.";
						isException = true;
					}
				}
			} else {				
				log_dscrp.append("[동영상정보없음]");
				error_msg = "수강 가능한 교과목 정보가 없습니다.";
				isException = true;
			}
		} else {
			log_dscrp.append("[비정상적인접근]");
			isException = true;
		}
		if(isException) {
			model.addAttribute("error_msg", error_msg);
			returnUrl = "cmmn/error";
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkDtlMyHistoryVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(mEduMemberVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(mEduMemberVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}	
		
		return returnUrl;
	}	
	//나의강의실 - 수강현황 - 상세리스트 - 동영상플레이어 - 플레이시간(이어보기) 갱신 저장 ------------------------------------------------
	@RequestMapping(value="/educenter/mbrhstry/ajaxUpdateEduContinueTime.do", method = RequestMethod.POST)
	public @ResponseBody String edu_mbrhstry_ajaxUpdateEduContinueTime(@RequestParam(value="key",required=false) String key,
			@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		PublicUtils mPublicUtils = new PublicUtils();
		EgovStringUtil egovStringUtil = new EgovStringUtil();
		EgovDateUtil egovDateUtil = new EgovDateUtil(); 
		egovStringUtil.AES128Util();
		
		JSONObject data = new JSONObject();	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		String LOGIN_MBR_ID = "";
		String LOGIN_MBR_LV_ID = "";
		
		log_dscrp.append("[교육센터사용자-동영상플레이어-업데이트처리]");
		
		try {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			
			String EXTERNAL_MBR_ID = (String)request.getSession().getAttribute("EXTERNAL_MBR_ID");
			
			//외부링크시 키 검증하여 임시로그인 처리
			boolean isUnSessionTempMemberLogin = false;
			boolean isTempMemberLogin = false;
			if(key != null && key.length() != 0){
				String HMBR_DTL_SN = EgovFileScrty.decode(key);
				if(myHistoryVO.getHMBR_DTL_SN().equals(HMBR_DTL_SN)) {
					LOGGER.debug("키 정보가 일치하여 임시 로그인 처리 진행");
					isTempMemberLogin = true;
					EduMemberVO mEduMemberVO = new EduMemberVO();
//					mEduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
					if(loginVO==null || loginVO.getMBR_ID()==null){
						mEduMemberVO.setMBR_ID(EXTERNAL_MBR_ID);
					} else {
						mEduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
					}					
					mEduMemberVO = eduMemberService.get_edu_member_info(mEduMemberVO);
					if(mEduMemberVO!=null && mEduMemberVO.getMBR_SN()!=null && mEduMemberVO.getMBR_SN().length()!=0) {	
						loginVO = new LoginVO();
						loginVO.setMBR_ID(mEduMemberVO.getMBR_ID());
						loginVO.setMBR_LV_ID(mEduMemberVO.getMBR_LV_ID());	
					} else {
						LOGGER.debug("회원정보가없어처리불가");
					}
				}
			} else { //세션이 끊긴 경우를 대비하여 처리
				if(loginVO==null || loginVO.getMBR_ID()==null) {
					LOGGER.debug("세션 정보가 존재하지 않아 임시 로그인 처리 진행");
					isUnSessionTempMemberLogin = true;
					EduMemberVO mEduMemberVO = new EduMemberVO();
//					mEduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
					if(loginVO==null || loginVO.getMBR_ID()==null){
						mEduMemberVO.setMBR_ID(EXTERNAL_MBR_ID);
					} else {
						mEduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
					}	
					
					mEduMemberVO = eduMemberService.get_edu_member_info(mEduMemberVO);
					if(mEduMemberVO!=null && mEduMemberVO.getMBR_SN()!=null && mEduMemberVO.getMBR_SN().length()!=0) {	
						loginVO = new LoginVO();
						loginVO.setMBR_ID(mEduMemberVO.getMBR_ID());
						loginVO.setMBR_LV_ID(mEduMemberVO.getMBR_LV_ID());	
					} else {
						LOGGER.debug("회원정보가없어처리불가");
					}
				}
			}
						
			//End of 외부링크시 키 검증하여 임시로그인 처리
			if(loginVO==null || loginVO.getMBR_ID()==null) {
				data.put("error", "1");
				data.put("msg", "비정상적인 접근입니다.");
			} else {
				
				LOGIN_MBR_ID = loginVO.getMBR_ID();
				LOGIN_MBR_LV_ID = loginVO.getMBR_LV_ID();
				
				//로그인아이디
				myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
				
				//검증
				MyHistoryVO chkMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_info(myHistoryVO);
				if(chkMyHistoryVO.getHMBR_DTL_SN()==null || chkMyHistoryVO.getHMBR_DTL_SN().length()==0) {
					data.put("error", "1");
					data.put("msg", "존재하지 않는 정보입니다.");
				} else {
					boolean isNotRecordLog = false;
					String keyCheck = egovStringUtil.removeScrtyKey(chkMyHistoryVO.getPLAY_LOCK_CD());
					String PLAY_LOCK_CD = myHistoryVO.getHMBR_DTL_SN();
					if(!PLAY_LOCK_CD.equals(keyCheck)) {
						data.put("error", "3");
						data.put("msg", "동영상 수강 중 중복실행이 감지되어 현재 화면은 종료됩니다.\n동영상 플레이어를 하나 이상 실행 하실 수 없으며 한 과목당 하나씩 동영상을 수강하셔야 합니다.");
						
						log_dscrp.append("[외부링크");							
						if(mPublicUtils.isMobileDevice(request)) {
							log_dscrp.append("-모바일");
						} else {
							log_dscrp.append("-PC");
						}
						log_dscrp.append("]");	
						log_dscrp.append("[중복실행이 의심되어 반영 안함][(REQ)"+egovStringUtil.removeScrtyKey(chkMyHistoryVO.getPLAY_LOCK_CD())+",(DB)"+egovStringUtil.removeScrtyKey(myHistoryVO.getPLAY_LOCK_CD())+"]");
						
					} else {
						
						if(isTempMemberLogin) {
							log_dscrp.append("[외부링크");							
							if(mPublicUtils.isMobileDevice(request)) {
								log_dscrp.append("-모바일");
							} else {
								log_dscrp.append("-PC");
							}
							log_dscrp.append("]");
						}
						if(isUnSessionTempMemberLogin) {
							log_dscrp.append("[세션부족:강제로그인처리");							
							if(mPublicUtils.isMobileDevice(request)) {
								log_dscrp.append("-모바일");
							} else {
								log_dscrp.append("-PC");
							}
							log_dscrp.append("]");
						}
						
						
						String vldVrfcKey = (String)request.getSession().getAttribute("VLD_VRFC_KEY");
						if(egovStringUtil.isEmpty(vldVrfcKey)) {
							
							data.put("error", "99");
							data.put("msg", "유효하지 않은 접근입니다.");
							
						} else {
							
							
							//[보안점검수정][START]####################################################			
						    //한번저장한 값이 있을 경우 요청한 값과 동일한지 체크하여 동일한 경우는 
							boolean isCheck = false;
							String LIST_KEY = myHistoryVO.getCRS_SN() + "_" + myHistoryVO.getHMBR_SN() + "_" + myHistoryVO.getHMBR_DTL_SN();
							List<String> CHECK_LIST = (List<String>)request.getSession().getAttribute(LIST_KEY);
							if (CHECK_LIST != null) {
								for (int i = 0 ; i < CHECK_LIST.size() ; i++) {
									String temp = CHECK_LIST.get(i);
									if (myHistoryVO.getTRN_CUR_TIME() != null && !myHistoryVO.getTRN_CUR_TIME().equals("") && myHistoryVO.getTRN_CUR_TIME().equals(temp)) {
										isCheck = true;
									}
								}
							}
							if (isCheck) {
								data.put("error", "99");
								data.put("msg", "유효하지 않은 접근입니다.");
								
							} else {
								if (CHECK_LIST == null) {
									CHECK_LIST = new ArrayList<String>();
								}
								if (myHistoryVO.getTRN_CUR_TIME() != null && !myHistoryVO.getTRN_CUR_TIME().equals("")) {
								    CHECK_LIST.add(myHistoryVO.getTRN_CUR_TIME());
								}
								request.getSession().setAttribute(LIST_KEY, CHECK_LIST);
							//[보안점검수정][END]####################################################
							
							    String vldVrfcKey2 = (String)request.getSession().getAttribute("VLD_VRFC_KEY2");
//							if(!egovStringUtil.isEmpty(vldVrfcKey2)) {
								String inner3DateTime= egovDateUtil.getTimeStringBeforeOrAfterMinute(vldVrfcKey2, "yyyy-MM-dd HH:mm:ss", 1, "yyyy-MM-dd HH:mm:ss", RETURN_MIN_TYPE.after);
								PublicUtils.RETURN_COMPARE_TYPE dateStart1 = mPublicUtils.dateCompare(
										mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
										inner3DateTime, "yyyy-MM-dd HH:mm:ss" 
										);
								
//								if(dateStart1.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)) {
//									data.put("error", "98");
//									data.put("msg", "유효하지 않은 접근입니다.");									
//								} else {
								String beforeDateTime = egovDateUtil.getTimeStringBeforeOrAfterMinute(vldVrfcKey, "yyyy-MM-dd HH:mm:ss", 5, "yyyy-MM-dd HH:mm:ss", RETURN_MIN_TYPE.after);
								PublicUtils.RETURN_COMPARE_TYPE dateStart = mPublicUtils.dateCompare(
										mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
										beforeDateTime, "yyyy-MM-dd HH:mm:ss" 
										);
								
								if(dateStart.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)) {
									myHistoryVO.setTRN_CUR_TIME(String.valueOf(Integer.parseInt(chkMyHistoryVO.getTRN_CUR_TIME()) + 180));
								} else {
									myHistoryVO.setTRN_CUR_TIME("0");
								}
								
								if( Integer.parseInt(chkMyHistoryVO.getTRN_MAX_TIME()) <= Integer.parseInt(myHistoryVO.getTRN_CUR_TIME()) ) {
									if(chkMyHistoryVO.getLRNNG_CMPLT_DTL_ST().equals("1")) {
										log_dscrp.append("[교육센터사용자-교육이수처리-이미완료된상태]");
										data.put("refresh", "0");
										
										tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
										tbl_sn.append(myHistoryVO.getHMBR_DTL_SN()+",");
																								
									} else {
										log_dscrp.append("[교육센터사용자-교육이수처리-새로운완료처리]");
										myHistoryVO.setLRNNG_PRGRS("1.0");
										myHistoryVO.setLRNNG_CUR_TIME(chkMyHistoryVO.getLRNNG_MAX_TIME());
										myHistoryVO.setLRNNG_CUR_POINT(chkMyHistoryVO.getLRNNG_MAX_POINT());
										myHistoryService.set_educenter_mbrhstry_dtl_cmplt(myHistoryVO);
										
										tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
										tbl_sn.append(myHistoryVO.getHMBR_DTL_SN()+",");
										
										//메인 업데이트
										MyHistoryVO updateMyHistoryVO = new MyHistoryVO();
										updateMyHistoryVO.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
										updateMyHistoryVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
										updateMyHistoryVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
										myHistoryService.set_educenter_mbrhstry_cur_cmplt_up(updateMyHistoryVO);
										
										//점수반영
										float temp_calc_count = 0;
										float temp_calc_sum_time = 0;
										float temp_calc_sum_point = 0;
										float temp_calc_sum_percent = 0;
										MyHistoryVO researchMyHistoryVO = new MyHistoryVO();
										researchMyHistoryVO.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
										researchMyHistoryVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
										researchMyHistoryVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
										researchMyHistoryVO.setNotUsedPagination(true);
										List<MyHistoryVO> updChkMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_list(researchMyHistoryVO);
										for(MyHistoryVO upditem : updChkMyHistoryVO) {
											temp_calc_sum_time += Float.parseFloat(upditem.getLRNNG_CUR_TIME());
											temp_calc_sum_point += Float.parseFloat(upditem.getLRNNG_CUR_POINT());
											temp_calc_sum_percent += Float.parseFloat(upditem.getLRNNG_PRGRS());
											temp_calc_count++;
										}
										MyHistoryVO updateMyHistoryVO2 = new MyHistoryVO();
										updateMyHistoryVO2.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
										updateMyHistoryVO2.setCRS_SN(chkMyHistoryVO.getCRS_SN());
										updateMyHistoryVO2.setMBR_ID(chkMyHistoryVO.getMBR_ID());
										updateMyHistoryVO2.setLRNNG_TOT_TIME(String.valueOf(temp_calc_sum_time));
										updateMyHistoryVO2.setLRNNG_TOT_POINT(String.valueOf(temp_calc_sum_point));
										updateMyHistoryVO2.setLRNNG_PRGRS(String.valueOf(temp_calc_sum_percent/temp_calc_count));
										updateMyHistoryVO2.setUPD_MBR_ID(loginVO.getMBR_ID());
										updateMyHistoryVO2.setPLAY_LOCK_CD("clear");
										
										myHistoryService.set_educenter_mbrhstry_mod(updateMyHistoryVO2);
										
										log_dscrp.append("[나의교육과정메인점수반영]");
										tbl_inf.append("EDU_MBR_HSTRY_TB,");
										tbl_sn.append(chkMyHistoryVO.getHMBR_SN()+",");
																	
										data.put("refresh", "1");
										
										/*if(myHistoryVO.getCRS_SN().isEmpty() || myHistoryVO.getCRS_SN().length() == 0){
											return "eduadm/error/page_400";
										}*/
										
									}
								} else {
									//isNotRecordLog = true;
									if( Integer.parseInt(myHistoryVO.getTRN_CUR_TIME()) <= Integer.parseInt(chkMyHistoryVO.getTRN_CUR_TIME()) ) {
										log_dscrp.append("[교육센터사용자-교육이수진행-이어보기저장안함:이전시간보다작음]");
										data.put("refresh", "0");
									} else {
										log_dscrp.append("[교육센터사용자-교육이수진행-이어보기저장]");
										//이어보기 시간 저장
										float TRN_CUR_TIME = Float.parseFloat(myHistoryVO.getTRN_CUR_TIME());
										float TRN_MAX_TIME = Float.parseFloat(chkMyHistoryVO.getTRN_MAX_TIME());
										float temp_calc_time = TRN_CUR_TIME/TRN_MAX_TIME;
										myHistoryVO.setLRNNG_PRGRS(String.valueOf(temp_calc_time));
										/*
										//점수배분을 할지는 추후에.
										if(temp_calc_time >= 1.0f) {
											myHistoryVO.setLRNNG_CMPLT_DTL_ST("1");
											myHistoryVO.setLRNNG_CUR_TIME(chkMyHistoryVO.getLRNNG_MAX_TIME());
											myHistoryVO.setLRNNG_CUR_POINT(chkMyHistoryVO.getLRNNG_MAX_POINT());
										} else if(temp_calc_time >= 0.8f) {
											myHistoryVO.setLRNNG_CUR_TIME(String.valueOf(Float.parseFloat(chkMyHistoryVO.getLRNNG_MAX_TIME())*0.8f));
											myHistoryVO.setLRNNG_CUR_POINT(String.valueOf(Float.parseFloat(chkMyHistoryVO.getLRNNG_MAX_POINT())*0.8f));
										} else if(temp_calc_time >= 0.5f) {
											myHistoryVO.setLRNNG_CUR_TIME(String.valueOf(Float.parseFloat(chkMyHistoryVO.getLRNNG_MAX_TIME())*0.5f));
											myHistoryVO.setLRNNG_CUR_POINT(String.valueOf(Float.parseFloat(chkMyHistoryVO.getLRNNG_MAX_POINT())*0.5f));
										} else {
											myHistoryVO.setLRNNG_CUR_TIME("0");
											myHistoryVO.setLRNNG_CUR_POINT("0");
										} 
										*/
										myHistoryService.set_educenter_chng_continue_time(myHistoryVO);
										
										if(temp_calc_time >= 1.0f && !chkMyHistoryVO.getLRNNG_CMPLT_DTL_ST().equals("1")) {
											
											/*  과연 필요한 것인가...? 위에꺼 안되면 그때 적용해보자.
											isNotRecordLog = false;
											
											log_dscrp.append("[교육센터사용자-교육이수처리-이어보기-이수완료]");
											
											myHistoryVO.setLRNNG_PRGRS("1.0");
											myHistoryVO.setLRNNG_CUR_TIME(chkMyHistoryVO.getLRNNG_MAX_TIME());
											myHistoryVO.setLRNNG_CUR_POINT(chkMyHistoryVO.getLRNNG_MAX_POINT());
											myHistoryService.set_educenter_mbrhstry_dtl_cmplt(myHistoryVO);
											
											tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
											tbl_sn.append(myHistoryVO.getHMBR_DTL_SN()+",");
											
											//메인 업데이트
											MyHistoryVO updateMyHistoryVO = new MyHistoryVO();
											updateMyHistoryVO.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
											updateMyHistoryVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
											updateMyHistoryVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
											myHistoryService.set_educenter_mbrhstry_cur_cmplt_up(updateMyHistoryVO);
											*/
										}
										
										
										//점수반영
										float temp_calc_count = 0;
										float temp_calc_sum_time = 0;
										float temp_calc_sum_point = 0;
										float temp_calc_sum_percent = 0;
										MyHistoryVO researchMyHistoryVO = new MyHistoryVO();
										researchMyHistoryVO.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
										researchMyHistoryVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
										researchMyHistoryVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
										researchMyHistoryVO.setNotUsedPagination(true);
										List<MyHistoryVO> updChkMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_list(researchMyHistoryVO);
										for(MyHistoryVO upditem : updChkMyHistoryVO) {
											temp_calc_sum_time += Float.parseFloat(upditem.getLRNNG_CUR_TIME());
											temp_calc_sum_point += Float.parseFloat(upditem.getLRNNG_CUR_POINT());
											temp_calc_sum_percent += Float.parseFloat(upditem.getLRNNG_PRGRS());
											temp_calc_count++;
										}
										MyHistoryVO updateMyHistoryVO2 = new MyHistoryVO();
										updateMyHistoryVO2.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
										updateMyHistoryVO2.setCRS_SN(chkMyHistoryVO.getCRS_SN());
		 								updateMyHistoryVO2.setMBR_ID(chkMyHistoryVO.getMBR_ID());
										updateMyHistoryVO2.setLRNNG_TOT_TIME(String.valueOf(temp_calc_sum_time));
										updateMyHistoryVO2.setLRNNG_TOT_POINT(String.valueOf(temp_calc_sum_point));
										updateMyHistoryVO2.setLRNNG_PRGRS(String.valueOf(temp_calc_sum_percent/temp_calc_count));
										updateMyHistoryVO2.setUPD_MBR_ID(loginVO.getMBR_ID());
										
										myHistoryService.set_educenter_mbrhstry_mod(updateMyHistoryVO2);
										
										log_dscrp.append("[나의교육과정메인점수반영]");
										tbl_inf.append("EDU_MBR_HSTRY_TB,");
										tbl_sn.append(chkMyHistoryVO.getHMBR_SN()+",");
										
										data.put("refresh", "1");
									}
								}
								
								data.put("error", "0");
								data.put("msg", "정상적으로 업데이트 되었습니다.");
								
								request.getSession().setAttribute("VLD_VRFC_KEY2", mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
								
								
								
								
								try { //온라인교육 자동 이수 처리가 필요한 교육을 위한 처리 구간 - 이수증 자동 발급
									//교육정보
									EduMyHistoryVO chkEduMyHistoryVO = new EduMyHistoryVO();
									chkEduMyHistoryVO.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
									chkEduMyHistoryVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
									chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(chkEduMyHistoryVO);
									//교육과정정보
									EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
									eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
									eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
									//
									if(eduCurriculumVO.getCRS_GRP_CD().equals("CIDE00000000000") && !chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1") 
											&& Float.parseFloat(chkEduMyHistoryVO.getLRNNG_PRGRS()) >= 1.0f) {
										log_dscrp.append("[자동이수처리구간:시작]");
										
										boolean isCmpltCntUpdate = true;
										boolean isException = false;	
									
										//회원정보
										EduMemberVO eduMemberVO = new EduMemberVO();
										eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
										eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
										
										//발급기관정보
										EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
										eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
										eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
										
										//이수증 발급 진행
										EduCertificateVO eduCertificateVO = new EduCertificateVO();
										eduCertificateVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
										eduCertificateVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
										eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
										EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
										if(existInfo == null || existInfo.getCRTF_SN() == null || existInfo.getCRTF_SN().length() == 0) {
											//점수 재계산
											int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());//교과목이수종합시간
											int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());//교과목이수종합점수
											int HMBR_INPUT_TIME = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_TIME());//최대인정교육시간
											int HMBR_INPUT_POINT = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_POINT());//최대인정교육점수
											int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
											int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
											chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
											chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
											chkEduMyHistoryVO.setTMPR_LRNNG_CMPLT_DT(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
											
											
											//새로 발급할 이수증
											eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());
											eduCertificateVO.setREG_MBR_ID(loginVO.getMBR_ID());
											eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
											String insertId = eduCertificateVO.getCRTF_CD();
											//회원부가상세정보
											EduMemberVO eduMemberDtlVO = new EduMemberVO();
											eduMemberDtlVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
											eduMemberDtlVO.setUSE_AT("Y");
											eduMemberDtlVO.setHIDE_AT("N");
											List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
											try {
												eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
														request,
														propertiesService,
														eduMemberVO,
														list_mbr_dtl,
														eduCertificateVO,
														chkEduMyHistoryVO,
														eduCurriculumVO,
														eduCategoryInsInfVO).toDocument());
												eduCertificateService.set_edu_certificate_reg(eduCertificateVO);
											} catch(Exception e) {
												log_dscrp.append("[이수증발급실패!!]");
												log_msg.append("이수증발급실패("+e.toString()+")");
												isException = true;
												isCmpltCntUpdate = false;
											}
											LOGGER.debug("새로운 이수증 발급 : " + insertId);
											log_dscrp.append("[새로운 이수증 발급 : " + insertId+"]");
										}
																	
										if(isCmpltCntUpdate && !isException) {
											//이수완료처리
											chkEduMyHistoryVO.setLRNNG_CMPLT_ST("1");
											eduMyHistoryService.set_edu_mbrhstry_mod(chkEduMyHistoryVO);
											//커리큘럼에 업데이트
											EduCurriculumVO updateEduCurriculumVO = new EduCurriculumVO();
											updateEduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
											eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_up(updateEduCurriculumVO);
											//내정보에 온라인교육이수 처리 
											eduMemberVO.setMBR_HMBR_ONLINE_SN(chkEduMyHistoryVO.getHMBR_SN());
											eduMemberVO.setMBR_HMBR_ONLINE_ST("Y");
											eduMemberService.set_edu_member_mod(eduMemberVO);
											
											//카카오 알림톡 발송
											String[] parseAddr = eduMemberVO.getMBR_ADDR1().split(" ");
												//이수증발급정보 재호출
											EduCertificateVO reEduCertificateVO = new EduCertificateVO();
											reEduCertificateVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
											reEduCertificateVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
											reEduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
											EduCertificateVO reEduExistInfo = eduCertificateService.get_edu_certificate_info(reEduCertificateVO);	
											//교육정보 재호출
											EduMyHistoryVO reChkEduMyHistoryVO = new EduMyHistoryVO();
											reChkEduMyHistoryVO.setHMBR_SN(chkMyHistoryVO.getHMBR_SN());
											reChkEduMyHistoryVO.setCRS_SN(chkMyHistoryVO.getCRS_SN());
											reChkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(reChkEduMyHistoryVO);
		//									//카카오 알림톡 발송
		//									String eduStrDt = eduCurriculumVO.getCRS_STR_DT(); 
		//									String eduEndDt = eduCurriculumVO.getCRS_END_DT(); 
		//									KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
		//									kakaoAlimTalkVO.setTelNum(eduMemberVO.getMBR_HP());//연락처
		//									//(알림톡필수)
		//									String complEdycIssuNo = reEduExistInfo.getCRTF_CD();
		//									kakaoAlimTalkVO.setComplEdycIssuNo(complEdycIssuNo);//이수증발급번호
		//									kakaoAlimTalkVO.setCmpletNm(eduMemberVO.getMBR_NM());//성명
		//									kakaoAlimTalkVO.setCmpletBrdt(eduMemberVO.getMBR_BIRTH());//생년월일
		//									kakaoAlimTalkVO.setCmpletAdres(eduMemberVO.getMBR_ADDR1());//주소
		//									kakaoAlimTalkVO.setEduNm(reEduExistInfo.getCRS_NM()	);
		//									if(eduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")){ // 신규재개자 전문교육
		//										kakaoAlimTalkVO.setEduStrDt(eduStrDt.replace(".0", ""));//교육일시
		//										kakaoAlimTalkVO.setEduEndDt(eduEndDt.replace(".0", ""));//교육일시
		//										kakaoAlimTalkVO.setTemplateId("KKO_000007");//이수증발급
		//									} else {
		//										kakaoAlimTalkVO.setEduYmd(eduStrDt.replace(".0", "") + " ~ " + eduEndDt.replace(".0", ""));//교육일시
		//										if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010300")){ // 낚시어선 전문교육
		//											kakaoAlimTalkVO.setTemplateId("KKO_000005");//이수증발급
		//										} else if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")){ // 낚시터 전문교육
		//											kakaoAlimTalkVO.setTemplateId("KKO_000006");//이수증발급
		//										}
		//									}
		//									kakaoAlimTalkVO.setAsync(true);//동기화 전송 여부(true:비동기,false:동기[기본값])
		//									JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
		//									//End of 카카오 알림톡 발송
											
										}							
										log_dscrp.append("[자동이수처리구간:종료]");
										
										//관리자가 설문조사 막았을 경우
										String crs_sv_st = eduCurriculumVO.getCRS_SV_ST();	//0:미사용, 1:사용
										if(crs_sv_st.equals("1")){	//설문조사가 사용중이면
											data.put("lrnng_cmplt_st", "1");
										} else {
											data.put("lrnng_cmplt_st", "0");
										}
										
									} else {
										data.put("lrnng_cmplt_st", "0");	
									}
									
								} catch(Exception e) {
									LOGGER.debug("[fail process] " +e.toString());
									log_dscrp.append("[자동이수처리구간:에러]");
									data.put("lrnng_cmplt_st", "0");
								}
//								}	
//	                        }
							//[보안점검수정][START]####################################################			
							}
							//[보안점검수정][END]####################################################		
						} //egovStringUtil.isEmpty(vldVrfcKey)
					}//End of playlock
					
					if(!isNotRecordLog) {
						try {	
							/**
							 * LOG RECORED (로그기록)
							 * */
							LogRecordVO mEduLogRecordVO = new LogRecordVO();
							log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(myHistoryVO));
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
				} // chkMyHistoryVO.getHMBR_DTL_SN()==null || chkMyHistoryVO.getHMBR_DTL_SN().length()==0
			} //loginVO==null || loginVO.getMBR_ID()==null
						
		} catch(Exception e) {
			LOGGER.debug("error",e);
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(myHistoryVO));
				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(LOGIN_MBR_ID);
				mEduLogRecordVO.setMBR_LV(LOGIN_MBR_LV_ID);
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO,request);
			} catch(Exception e2) {
				LOGGER.debug("[fail log record] "+e2.toString());
			}	
			
		}
		
		
		/** 로그 확인용 추가 2021.08.03 ***********************************/
		try {	
			/**
			 * LOG_EUD_TB (로그기록)
			 * */
			LogRecordVO eduLogVO = new LogRecordVO();
			log_msg.append(eduLogVO.encodingFromObjectToJson(myHistoryVO));
			eduLogVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			eduLogVO.setMBR_ID(LOGIN_MBR_ID);
			eduLogVO.setCRS_SN(myHistoryVO.getCRS_SN());
			eduLogVO.setTRN_CUR_TIME(myHistoryVO.getTRN_CUR_TIME());
			eduLogVO.setHMBR_DTL_SN(myHistoryVO.getHMBR_DTL_SN());
			eduLogVO.setLOG_DSCRP(log_dscrp.toString());
			eduLogVO.setTBL_INF(tbl_inf.toString());
			eduLogVO.setTBL_SN(tbl_sn.toString());
			eduLogVO.setLOG_MSG(log_msg.toString());
			logRecordService.set_log_edu_data(eduLogVO,request);
		} catch(Exception e2) {
			LOGGER.debug("[fail log record] "+e2.toString());
		}
		/************************************************************/
		
		
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

	//나의강의실 - 수강현황 - 상세리스트 - 동영상플레이어 - 플레이시간(이어보기) 갱신 저장 ------------------------------------------------
	@RequestMapping(value="/educenter/mbrhstry/ajaxUpdateUdtDate.do", method = RequestMethod.POST)
	public @ResponseBody String edu_mbrhstry_ajaxUpdateUdtDate(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {
		
		PublicUtils mPublicUtils = new PublicUtils();
		EgovStringUtil egovStringUtil = new EgovStringUtil();
		EgovDateUtil egovDateUtil = new EgovDateUtil();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		JSONObject data = new JSONObject();
		
		log_dscrp.append("[교육센터사용자-동영상플레이어-처음접근-마지막업데이트일자 업데이트처리]");
		tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
		tbl_sn.append(myHistoryVO.getHMBR_DTL_SN()+",");
		MyHistoryVO chkMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_info(myHistoryVO);
		
		chkMyHistoryVO.setUPD_DT(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
		myHistoryService.set_educenter_chng_continue_time(chkMyHistoryVO);
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(myHistoryVO));
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
	//나의강의실 - 증명서발급 페이지 ------------------------------------------------
	@RequestMapping(value = "/educenter/mbrhstry/listCrtf.do")
	public String educenter_mbrhstry_listCrtf(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/error/login";
		}
		
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_cd",list_mbr_cd);
		}
		//사업자구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00006");
	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_license_se_cd",list_position_cd);
		}
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		myHistoryVO.setLRNNG_CMPLT_ST("YYYYY");
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myHistoryVO.getPageUnit());
		paginationInfo.setPageSize(myHistoryVO.getPageSize());

		myHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		myHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		myHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<MyHistoryVO> list = myHistoryService.get_educenter_mbrhstry_list(myHistoryVO);
		
		int totCnt = myHistoryService.get_educenter_mbrhstry_list_totcnt(myHistoryVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("list",list);
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)나의강의실-증명서발급");
			if(loginVO != null){
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch(Exception e) { 
			LOGGER.debug("[fail analytics record] "+e.toString());
		}
		//-----------------------------------------------------------
		
		
		return "educenter/mbrhstry/list_crtf";
	}
	//나의강의실 - 증명서발급 페이지 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/mbrhstry/listCrtf.do")
	public String educenter_mbrhstry_listCrtf_mobile(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/mobile/error/login";
		}
		
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_cd",list_mbr_cd);
		}
		//사업자구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00006");
	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_license_se_cd",list_position_cd);
		}
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		myHistoryVO.setLRNNG_CMPLT_ST("YYYYY");
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myHistoryVO.getPageUnit());
		paginationInfo.setPageSize(myHistoryVO.getPageSize());

		myHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		myHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		myHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<MyHistoryVO> list = myHistoryService.get_educenter_mbrhstry_list(myHistoryVO);
		
		int totCnt = myHistoryService.get_educenter_mbrhstry_list_totcnt(myHistoryVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("list",list);
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)나의강의실-증명서발급");
			if(loginVO != null){
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch(Exception e) { 
			LOGGER.debug("[fail analytics record] "+e.toString());
		}
		//-----------------------------------------------------------
		
		return "educenter/mobile/mbrhstry/list_crtf";
	}
	//나의강의실 - 증명서발급 - 발급내역 페이지  ------------------------------------------------
	@RequestMapping(value = "/educenter/mbrhstry/listCrtfDtl.do")
	public String educenter_mbrhstry_listCrtfDtl(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
				
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/error/login";
		}
		
		EduCertificateVO eduCertificateVO = new EduCertificateVO();
		eduCertificateVO.setMBR_ID(loginVO.getMBR_ID());
		eduCertificateVO.setCRS_SN(myHistoryVO.getCRS_SN());
		eduCertificateVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
		
		EduCertificateVO parentInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		model.addAttribute("parentInfo", parentInfo);
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myHistoryVO.getPageUnit());
		paginationInfo.setPageSize(myHistoryVO.getPageSize());

		eduCertificateVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCertificateVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCertificateVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EduCertificateVO> list = eduCertificateService.get_edu_certificate_dtl_list(eduCertificateVO);
		
		int totCnt = eduCertificateService.get_edu_certificate_dtl_list_totcnt(eduCertificateVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("list",list);
		
		return "educenter/mbrhstry/list_crtf_dtl";
	}
	//나의강의실 - 증명서발급 - 발급내역 페이지  -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/mbrhstry/listCrtfDtl.do")
	public String educenter_mbrhstry_listCrtfDtl_mobile(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
				
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/mobile/error/login";
		}
		
		EduCertificateVO eduCertificateVO = new EduCertificateVO();
		eduCertificateVO.setMBR_ID(loginVO.getMBR_ID());
		eduCertificateVO.setCRS_SN(myHistoryVO.getCRS_SN());
		eduCertificateVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
		
		EduCertificateVO parentInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		model.addAttribute("parentInfo", parentInfo);
		
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myHistoryVO.getPageUnit());
		paginationInfo.setPageSize(myHistoryVO.getPageSize());

		eduCertificateVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCertificateVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCertificateVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EduCertificateVO> list = eduCertificateService.get_edu_certificate_dtl_list(eduCertificateVO);
		
		int totCnt = eduCertificateService.get_edu_certificate_dtl_list_totcnt(eduCertificateVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("list",list);
		
		return "educenter/mobile/mbrhstry/list_crtf_dtl";
	}
	
	
	
	//나의강의실 - 증명서발급 - 열람(다운로드) 페이지  ------------------------------------------------
	@RequestMapping(value = "/educenter/mbrhstry/viewCrtf.do")
	public String educenter_mbrhstry_viewCrtf(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			return "educenter/error/login";
		}
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터사용자-증명서발급-출력하기]");
		
		eduCertificateVO.setMBR_ID(loginVO.getMBR_ID());

		EduCertificateVO info = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		model.addAttribute("info",info);
		
		if(info == null || info.getCRTF_SN() == null || info.getCRTF_SN().length() == 0) {
			//상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		
		//이수증 데이터가 없을 경우 재발급한다.
		try {
			//회원정보
			EduMemberVO eduMemberVO = new EduMemberVO();
			eduMemberVO.setMBR_ID(info.getMBR_ID());
			eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
			log_dscrp.append("[이름:"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+")]");
			//회원부가상세정보
			EduMemberVO eduMemberDtlVO = new EduMemberVO();
			eduMemberDtlVO.setMBR_ID(info.getMBR_ID());
			eduMemberDtlVO.setUSE_AT("Y");
			eduMemberDtlVO.setHIDE_AT("N");
			List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
			//교육과정정보
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(info.getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
			newInstanceItem.setCRS_SN(info.getCRS_SN());
			newInstanceItem.setHMBR_SN(info.getHMBR_SN());
			newInstanceItem.setLRNNG_CMPLT_ST("1");
			newInstanceItem.setUSE_ST("1");
			newInstanceItem.setDEL_ST("0");
			EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);			
			//점수 재계산
			/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
			chkEduMyHistoryVO.setHMBR_INPUT_TIME(eduCurriculumVO.getCRS_TOT_TIME());
			chkEduMyHistoryVO.setHMBR_INPUT_POINT(eduCurriculumVO.getCRS_TOT_POINT());
			/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
			int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());//교과목이수종합시간
			int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());//교과목이수종합점수
			int HMBR_INPUT_TIME = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_TIME());//최대인정교육시간
			int HMBR_INPUT_POINT = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_POINT());//최대인정교육점수
			int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
			int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
			chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
			chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
			/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
			newInstanceItem.setHMBR_INPUT_TIME(String.valueOf(HMBR_INPUT_TIME));
			newInstanceItem.setHMBR_INPUT_POINT(String.valueOf(HMBR_INPUT_POINT));
			newInstanceItem.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
			newInstanceItem.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
			/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
			//발급기관정보
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
			eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
			//새로 발급할 이수증
			eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());
			eduCertificateVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			String insertId = eduCertificateVO.getCRTF_CD();
			info.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
					request,
					propertiesService,
					eduMemberVO,
					list_mbr_dtl,
					eduCertificateVO,
					chkEduMyHistoryVO,
					eduCurriculumVO,
					eduCategoryInsInfVO).toDocument());
			eduCertificateService.set_edu_certificate_mod(info);
			log_msg.append("|이수증데이터재발급:성공("+insertId+")");
			log_dscrp.append("|이수증데이터재발급:성공");
		} catch(Exception e) {
			log_dscrp.append("|이수증데이터재발급:에러");
			log_msg.append("|이수증데이터재발급:에러("+e.toString()+")");
		}
		//
		log_dscrp.append("|발급사유:"+eduCertificateVO.getCRTF_TYPE_ST());
		
		//발급완료처리하기
		eduCertificateService.set_edu_certificate_issue_record_dtl_cmplt(eduCertificateVO);
		
		tbl_inf.append("EDU_CRTF_DTL_TB,");
		tbl_sn.append(eduCertificateVO.getCRTF_DTL_SN()+",");
		
		try {
			model.addAttribute("html_header", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/header.html").toString());
		} catch(Exception e) {
			model.addAttribute("html_header","");
		}
		try {
			model.addAttribute("html_footer", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/footer.html").toString());
		} catch(Exception e) {
			model.addAttribute("html_footer","");
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCertificateVO));
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
		
		return "eduadm/certificate/view";
	}
	//나의강의실 - 증명서발급 - 열람(다운로드) 페이지 (비회원) ------------------------------------------------
	@RequestMapping(value = "/educenter/mbrhstry/viewCrtfNonMbr.do")
	public String educenter_mbrhstry_viewCrtfNonMbr(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		
		String userid = "";
		String username = "";
		String userlevel = "";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			userid = "비회원 본인";
			userlevel = "10";
			username = "";
		} else {
			userid = loginVO.getMBR_ID();
			userlevel = loginVO.getMBR_LV_ID();
			username = loginVO.getMBR_NM();
		}
				
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터사용자-증명서발급-출력하기]");
		log_dscrp.append("[이름:"+username+"(아이디:"+userid+")]");
		
		EduCertificateVO info = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		model.addAttribute("info",info);
		
		if(info == null || info.getCRTF_SN() == null || info.getCRTF_SN().length() == 0) {
			//상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		//발급완료처리하기
		eduCertificateService.set_edu_certificate_issue_record_dtl_cmplt(eduCertificateVO);
		
		tbl_inf.append("EDU_CRTF_DTL_TB,");
		tbl_sn.append(eduCertificateVO.getCRTF_DTL_SN()+",");
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(eduCertificateVO));
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(userid);
			mEduLogRecordVO.setMBR_LV(userlevel);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}	
		
		return "eduadm/certificate/view";
	}
	//나의강의실 - 증명서발급 - 발급요청 페이지  ------------------------------------------------
	@RequestMapping(value="/educenter/mbrhstry/issueCrtf.do", method = RequestMethod.POST)
	public ModelAndView educenter_mbrhstry_issueCrtf(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {	
		
		ModelAndView mModelAndView = new ModelAndView();
		String responseUrl = "educenter/popup/issue_crtf";
		
		EduCertificateVO eduCertificateVO = new EduCertificateVO();
		
		String userid = "";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			//비회원이 조회하는 경우
			//responseUrl = "educenter/error/login";
		} else {			
			userid = loginVO.getMBR_ID();
		}
		
		if(myHistoryVO.getHMBR_MBR_TYPE()!=null && myHistoryVO.getHMBR_MBR_TYPE().equals("0")) { //비회원이 조회하는 경우
			eduCertificateVO.setMBR_ID("");
		} else {
			eduCertificateVO.setMBR_ID(userid);
		}
		
		eduCertificateVO.setCRS_SN(myHistoryVO.getCRS_SN());
		eduCertificateVO.setHMBR_SN(myHistoryVO.getHMBR_SN());			
		EduCertificateVO info = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		mModelAndView.addObject("info",info);
		
		mModelAndView.setViewName(responseUrl);
		return mModelAndView;
	}	
	//나의강의실 - 증명서발급 - 발급요청 처리 로직  ------------------------------------------------
	@RequestMapping(value="/educenter/mbrhstry/issueCrtf_act.do", method = RequestMethod.POST)
	public @ResponseBody String educenter_mbrhstry_issueCrtf_act(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		JSONObject data = new JSONObject();	
		try {
			/*
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			if(loginVO==null || loginVO.getMBR_ID()==null) {
				data.put("error", "1");
				data.put("msg", "비정상적인 접근입니다.");
			} else {
			*/
				String userid = "";
				String userlevel = "";
				String username = "";
				LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
				if(loginVO==null || loginVO.getMBR_ID()==null) {
					//비회원이 발급받는 경우
					userid = "비회원 본인";
					userlevel = "10";
					username = "";
				} else {
					//회원이 발급받는 경우
					userid = loginVO.getMBR_ID();
					userlevel = loginVO.getMBR_LV_ID();
					username = loginVO.getMBR_NM();
				}
			
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();
				log_dscrp.append("[교육센터사용자-증명서발급-발급요청]");
				log_dscrp.append("[이름:"+username+"(아이디:"+userid+")]");
				
				EduCertificateVO info = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
				if(info == null || info.getCRTF_SN() == null || info.getCRTF_SN().length() == 0) {
					data.put("error", "1");
					data.put("msg", "발급 요청이 가능한 수강정보가 없습니다.");
					log_dscrp.append("[발급가능한 수강정보가 없음]");
				} else {
					
					if(info.getDEL_ST().equals("1")) {
						data.put("error", "1");
						data.put("msg", "삭제 된 이수증으로 발급 요청이 거부되었습니다.");
						log_dscrp.append("[삭제 된 이수증으로 발급 거부함]");
					} else {
						//이수증 발급시 내역남기기
						EduCertificateVO newEduCertificateVO = info;
						newEduCertificateVO.setCRTF_TYPE_ST(eduCertificateVO.getCRTF_TYPE_ST());
						newEduCertificateVO.setREG_MBR_ID(userid);
						newEduCertificateVO.setUPD_MBR_ID(userid);
						String insertId = eduCertificateService.set_edu_certificate_issue_record_dtl_reg(newEduCertificateVO);
							
						tbl_inf.append("EDU_CRTF_DTL_TB,");
						tbl_sn.append(insertId+",");
						
						data.put("refresh", "1");
						data.put("error", "0");
						data.put("msg", "정상적으로 발급 되었습니다.");
					}
				}
				try {	
					/**
					 * LOG RECORED (로그기록)
					 * */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(eduCertificateVO));
					mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
					mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
					mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
					mEduLogRecordVO.setMBR_ID(userid);
					mEduLogRecordVO.setMBR_LV(userlevel);
					mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
					logRecordService.set_log_data(mEduLogRecordVO,request);
				} catch(Exception e) {
					LOGGER.debug("[fail log record] "+e.toString());
				}	
			//}
		} catch(Exception e) {
			LOGGER.debug("error",e);
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	//나의강의실 - 증명서발급 - 이수내역조회 페이지 (비회원이수증조회) ------------------------------------------------
	@RequestMapping(value="/educenter/mbrhstry/searchCrtf.do", method = RequestMethod.POST)
	public ModelAndView educenter_mbrhstry_searchCrtf(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {	
		
		ModelAndView mModelAndView = new ModelAndView();
		String responseUrl = "educenter/popup/search_crtf";
		mModelAndView.setViewName(responseUrl);
		return mModelAndView;
	}
	
	//나의강의실 - 증명서발급 - 이수내역조회 처리 로직 (비회원이수증조회)  ------------------------------------------------
	@RequestMapping(value="/educenter/mbrhstry/searchCrtf_act.do", method = RequestMethod.POST)
	public ModelAndView educenter_mbrhstry_searchCrtf_act(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		ModelAndView mModelAndView = new ModelAndView();
		String responseUrl = "educenter/mbrhstry/list_crtf_fshsk2018_ajax";
		myHistoryVO.setNotUsedPagination(true);
		List<MyHistoryVO> list = myHistoryService.get_educenter_mbrhstry_list(myHistoryVO);			
		mModelAndView.addObject("list",list);
		mModelAndView.setViewName(responseUrl);
		return mModelAndView;
	}
	
	//나의강의실 - 증명서발급 - 이수내역조회 페이지 (비회원이수증조회) 모바일 ------------------------------------------------
	@RequestMapping(value="/educenter/m/mbrhstry/searchCrtf.do", method = RequestMethod.POST)
	public ModelAndView educenter_mbrhstry_searchCrtf_mobile(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, ModelMap model) throws Exception {	
		
		ModelAndView mModelAndView = new ModelAndView();
		String responseUrl = "educenter/mobile/popup/search_crtf";
		mModelAndView.setViewName(responseUrl);
		return mModelAndView;
	}
	
	//나의강의실 - 증명서발급 - 이수내역조회 처리 로직 (비회원이수증조회) 모바일 ------------------------------------------------
	@RequestMapping(value="/educenter/m/mbrhstry/searchCrtf_act.do", method = RequestMethod.POST)
	public ModelAndView educenter_mbrhstry_searchCrtf_act_mobile(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		ModelAndView mModelAndView = new ModelAndView();
		String responseUrl = "educenter/mobile/mbrhstry/list_crtf_fshsk2018_ajax";
		myHistoryVO.setNotUsedPagination(true);
		List<MyHistoryVO> list = myHistoryService.get_educenter_mbrhstry_list(myHistoryVO);			
		mModelAndView.addObject("list",list);
		mModelAndView.setViewName(responseUrl);
		return mModelAndView;
	}
	
	//동영상 플레이어 이어보기 시간 검증
	@RequestMapping(value="/educenter/mbrhstry/ajaxCheckEduContinueTime.do")
	public String educenter_mbrhstry_ajaxCheckEduContinueTime(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, @RequestParam(value="key", required=false)String key) throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		PublicUtils mPublicUtils = new PublicUtils();

		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(key!=null && key.length()!=0) {
		   //외부링크시 키 검증하여 임시로그인 처리
		   String HMBR_DTL_SN = EgovFileScrty.decode(key);
		   if(myHistoryVO.getHMBR_DTL_SN().equals(HMBR_DTL_SN)) {
		      LOGGER.debug("키 정보가 일치하여 임시 로그인 처리 진행");
		   } else {
		      myHistoryVO.setMBR_ID("");
		   }
		   //End of 외부링크시 키 검증하여 임시로그인 처리
		} else {
		   myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
		}
		
		request.getSession().setAttribute("VLD_VRFC_KEY", mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
		
		myHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_info(myHistoryVO);
		if(myHistoryVO == null || myHistoryVO.getHMBR_DTL_SN() == null || myHistoryVO.getHMBR_DTL_SN().equals("")) {
			map.put("error", "비정상적인 접근이 확인되어 \n강의를 처음부터 시작합니다.");
			map.put("TRN_CUR_TIME", "0");
		}else {
			map.put("TRN_CUR_TIME", myHistoryVO.getTRN_CUR_TIME());
		}
		
		
		JSONObject json = new JSONObject(map);
		LOGGER.debug(json.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		return null;
	}
	
	//온라인교육 이수 설문조사
	@RequestMapping(value="/educenter/mbrhstry/survey_view.do")
	public String educenter_mbrhstry_survey_view(@ModelAttribute("surveyVO") SurveyVO surveyVO, 
			@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws Exception {
    
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());	//필수값 ID, CRS_SN
		myHistoryVO = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
		
		//관리자가 설문조사 막았을 경우
		EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
		/*if(myHistoryVO.getCRS_SN().isEmpty() || myHistoryVO.getCRS_SN().length() == 0){
			return "eduadm/error/page_400";
		}*/
		
		eduCurriculumVO.setCRS_SN(myHistoryVO.getCRS_SN());
		eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
		String crs_sv_st = eduCurriculumVO.getCRS_SV_ST();	//0:미사용, 1:사용
		
		if(myHistoryVO == null || myHistoryVO.getHMBR_SN() == null || myHistoryVO.getHMBR_SN().equals("") || crs_sv_st.equals("0")){
			
			return "redirect:/educenter/mbrhstry/list.do";
		} else {
			
			surveyVO.setSv_id(myHistoryVO.getCRS_SV_ID());
			SurveyVO info = this.service.select_survey(surveyVO);
		    List<SurveyVO> quest = this.service.select_surveyq_list(surveyVO);
		    List<SurveyVO> qitem = this.service.select_surveyqi_list(surveyVO);
		    List<SurveyVO> anscnt = this.service.survey_anscnt(surveyVO);
		    
		    EduCertificateVO eduCertificateVO = new EduCertificateVO();
		    eduCertificateVO.setCRS_SN(myHistoryVO.getCRS_SN());
		    eduCertificateVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
		    eduCertificateVO.setMBR_ID(myHistoryVO.getMBR_ID());
		    eduCertificateVO = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		    
		    /* 이수증 HTML 재생성 START */
		    // 회원정보
 			EduMemberVO eduMemberVO = new EduMemberVO();
 			eduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
 			eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
 			
 			// 회원부가상세정보
 			EduMemberVO eduMemberDtlVO = new EduMemberVO();
 			eduMemberDtlVO.setMBR_ID(myHistoryVO.getMBR_ID());
 			eduMemberDtlVO.setUSE_AT("Y");
 			eduMemberDtlVO.setHIDE_AT("N");
 			List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
 			
 			EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
 			newInstanceItem.setCRS_SN(myHistoryVO.getCRS_SN());
 			newInstanceItem.setHMBR_SN(myHistoryVO.getHMBR_SN());
 			newInstanceItem.setLRNNG_CMPLT_ST("1");
 			newInstanceItem.setUSE_ST("1");
 			newInstanceItem.setDEL_ST("0");
 			EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);
 			
 			//발급기관정보
 			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
 			eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
 			eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
 		    
 		    eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
 					request,
 					propertiesService,
 					eduMemberVO,
 					list_mbr_dtl,
 					eduCertificateVO,
 					chkEduMyHistoryVO,
 					eduCurriculumVO,
 					eduCategoryInsInfVO).toDocument());
 		    /* 이수증 HTML 재생성 END */
		    
		    model.addAttribute("item",eduCertificateVO);
		    
		    try {
				model.addAttribute("html_header", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/header.html").toString());
			} catch(Exception e) {
				model.addAttribute("html_header","");
			}
			try {
				model.addAttribute("html_footer", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/footer.html").toString());
			} catch(Exception e) {
				model.addAttribute("html_footer","");
			}
	
		    model.addAttribute("info", info);
		    model.addAttribute("quest", quest);
		    model.addAttribute("qitem", qitem);
		    model.addAttribute("aan", anscnt);
		    
		    model.addAttribute("myHistoryVO", myHistoryVO);
		}
		
	    return "educenter/mbrhstry/survey_view";
	}
	
	//온라인교육 이수 설문조사
	@RequestMapping(value="/educenter/m/mbrhstry/survey_view.do")
	public String educenter_m_mbrhstry_survey_view(@ModelAttribute("surveyVO") SurveyVO surveyVO, 
			@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) throws Exception {
    
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		myHistoryVO.setMBR_ID(loginVO.getMBR_ID());	//필수값 ID, CRS_SN
		myHistoryVO = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
		
		//관리자가 설문조사 막았을 경우
		EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
		/*if(myHistoryVO.getCRS_SN().isEmpty() || myHistoryVO.getCRS_SN().length() == 0){
			return "eduadm/error/page_400";
		}*/
		
		eduCurriculumVO.setCRS_SN(myHistoryVO.getCRS_SN());
		eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
		String crs_sv_st = eduCurriculumVO.getCRS_SV_ST();	//0:미사용, 1:사용
		
		if(myHistoryVO == null || myHistoryVO.getHMBR_SN() == null || myHistoryVO.getHMBR_SN().equals("") || crs_sv_st.equals("0")){
			
			return "redirect:/educenter/m/mbrhstry/list.do";
		} else {
			
			surveyVO.setSv_id(myHistoryVO.getCRS_SV_ID());
			SurveyVO info = this.service.select_survey(surveyVO);
		    List<SurveyVO> quest = this.service.select_surveyq_list(surveyVO);
		    List<SurveyVO> qitem = this.service.select_surveyqi_list(surveyVO);
		    List<SurveyVO> anscnt = this.service.survey_anscnt(surveyVO);
		    
		    EduCertificateVO eduCertificateVO = new EduCertificateVO();
		    eduCertificateVO.setCRS_SN(myHistoryVO.getCRS_SN());
		    eduCertificateVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
		    eduCertificateVO.setMBR_ID(myHistoryVO.getMBR_ID());
		    eduCertificateVO = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		    
		    /* 이수증 HTML 재생성 START */
		    // 회원정보
 			EduMemberVO eduMemberVO = new EduMemberVO();
 			eduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
 			eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
 			
 			// 회원부가상세정보
 			EduMemberVO eduMemberDtlVO = new EduMemberVO();
 			eduMemberDtlVO.setMBR_ID(myHistoryVO.getMBR_ID());
 			eduMemberDtlVO.setUSE_AT("Y");
 			eduMemberDtlVO.setHIDE_AT("N");
 			List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
 			
 			EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
 			newInstanceItem.setCRS_SN(myHistoryVO.getCRS_SN());
 			newInstanceItem.setHMBR_SN(myHistoryVO.getHMBR_SN());
 			newInstanceItem.setLRNNG_CMPLT_ST("1");
 			newInstanceItem.setUSE_ST("1");
 			newInstanceItem.setDEL_ST("0");
 			EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);
 			
 			//발급기관정보
 			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
 			eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
 			eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
 		    
 		    eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
 					request,
 					propertiesService,
 					eduMemberVO,
 					list_mbr_dtl,
 					eduCertificateVO,
 					chkEduMyHistoryVO,
 					eduCurriculumVO,
 					eduCategoryInsInfVO).toDocument());
 		    /* 이수증 HTML 재생성 END */
 		    
		    model.addAttribute("item",eduCertificateVO);
		    
		    try {
				model.addAttribute("html_header", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/header.html").toString());
			} catch(Exception e) {
				model.addAttribute("html_header","");
			}
			try {
				model.addAttribute("html_footer", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/footer.html").toString());
			} catch(Exception e) {
				model.addAttribute("html_footer","");
			}
	
		    model.addAttribute("info", info);
		    model.addAttribute("quest", quest);
		    model.addAttribute("qitem", qitem);
		    model.addAttribute("aan", anscnt);
		    
		    model.addAttribute("myHistoryVO", myHistoryVO);
		    
		}
		
	    return "educenter/mobile/mbrhstry/survey_view";
	}
	
	public static BufferedImage resize(InputStream inputStream, int width, int height) throws IOException{
		BufferedImage inputImage = ImageIO.read(inputStream);
		
		BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
		
		Graphics2D graphics2D = outputImage.createGraphics();
		graphics2D.drawImage(inputImage, 0, 0, width, height, null);
		graphics2D.dispose();
		
		graphics2D.setComposite(AlphaComposite.Src);
        //보간 관련
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //렌더링 관련
		graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        //안티엘리어싱 여부
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		return outputImage;
	}
	
	public static void formatConverter(String pngFile, String jpgFile) {
        try {

            File input = new File(pngFile);
            File output = new File(jpgFile);

            BufferedImage image = ImageIO.read(input);
            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_3BYTE_BGR);
            result.createGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            ImageIO.write(result, "jpg", output);

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	 /**
     * 캡쳐된 화면 서버 저장
     * @param request
     * @return
     * @throws Exception
     */
	@ResponseBody
	@RequestMapping(value="/imageCreate.do", method = RequestMethod.POST)
	public String createImage (@RequestParam HashMap<Object, Object> param, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		JSONObject data = new JSONObject();
		String binaryData = request.getParameter("imgSrc");
		String certificateSn = request.getParameter("certificateSn");
		String mbrId = request.getParameter("mbrId");
		
		FileOutputStream stream = null;
		try{
			if(binaryData == null || binaryData=="") {
				throw new Exception();    
			}
			binaryData = binaryData.replaceAll("data:image/png;base64,", "");
			byte[] file = Base64.decodeBase64(binaryData);
			stream = new FileOutputStream(propertiesService.getString("Globals.certificateImgPath").toString() + "/" + certificateSn + ".png");
			stream.write(file);
			stream.close();
			System.out.println(" -------------------- 파일 작성 완료 -------------------- ");
			
			// 리사이즈 START
			File resizeFile = new File(propertiesService.getString("Globals.certificateImgPath").toString() + "/" + certificateSn + ".png");
			InputStream inputStream = new FileInputStream(resizeFile);
			Image img = new ImageIcon(resizeFile.toString()).getImage();
			BufferedImage resizeImg = resize(inputStream, 500, 1100);
			ImageIO.write(resizeImg, "png", new File(propertiesService.getString("Globals.certificateImgPath").toString() + "/" + certificateSn + ".png"));
			// 리사이즈 END
			
			formatConverter(propertiesService.getString("Globals.certificateImgPath").toString() + "/" + certificateSn + ".png", 
								propertiesService.getString("Globals.certificateImgPath").toString() + "/" + certificateSn + ".jpg");
			
			// 이수증 이미지 문자전송 START
			EduCertificateVO eduCertificateVO = new EduCertificateVO();
			eduCertificateVO.setCRTF_SN(certificateSn);
			eduCertificateVO.setMBR_ID(mbrId);
		    eduCertificateVO = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		    
		    EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(eduCertificateVO.getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			
		    SmsSendVO smsData = new SmsSendVO();
			smsData.setSUBMSG("낚시전문교육 이수증 안내");																				// 제목
			smsData.setR_PHONE(eduCertificateVO.getMBR_HP()); 																			// 수신 번호
			smsData.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber")); 												// 발신 번호
			if(eduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002"))
				smsData.setMSG("낚시전문교육(21H) 수강이 완료되었습니다. 수고하셨습니다.");													// 내용
			else
				smsData.setMSG("낚시전문교육(4H) 수강이 완료되었습니다. 수고하셨습니다.");													// 내용
			
			smsData.setIMG_CNT(1);																										// 전송 이미지 갯수
			smsData.setIMG_PATH(propertiesService.getString("Globals.certificateImgPath").toString() + "/" + certificateSn + ".jpg");	// 전송 이미지 경로
			smsData.setIP(NaksiBoardController.getClientIpAddr(request));																// 발송 아이피
			smsData.setRSTKEY(smsData.getUniqRstKey());																					// 발송키
			smsManagerService.sendMms(smsData);
			// 이수증 이미지 문자전송 END
			data.put("error", "0");
			data.put("msg", "이수증 이미지를 정상적으로 전송했습니다.");
		}catch(Exception e){
			e.printStackTrace();
			data.put("error", "1");
			data.put("msg", "이수증 이미지 전송에 실패했습니다.");
			System.out.println(" -------------------- 파일이 정상적으로 넘어오지 않았습니다 -------------------- ");
		}finally{
			if(stream != null)
				stream.close();
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	//온라인교육 이수 설문조사 - 외부링크
	@RequestMapping(value="/educenter/mbrhstry/external/survey.do")
	public Object educenter_mbrhstry_external_survey(@RequestParam("key") String key, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		/*
		 	HMBR_2007143955115700,CRS_2003281533174020
		 	SE1CUl8yMDA3MTQzOTU1MTE1NzAwLENSU18yMDAzMjgxNTMzMTc0MDIw
		*/
		LOGGER.debug(EgovFileScrty.encode(key));
		
		LoginVO loginVO = null;
		
		boolean isException = false;
		String error_msg = "";
		log_dscrp.append("[교육센터사용자-외부링크-온라인교육설문조사]");
		
		String returnUrl = "educenter/mbrhstry/survey_view_external";
		PublicUtils mPublicUtils = new PublicUtils();
		if(mPublicUtils.isMobileDevice(request)) {
			log_dscrp.append("[모바일]");
			returnUrl = "educenter/mobile/mbrhstry/survey_view_external";
		}
		EduMemberVO mEduMemberVO = new EduMemberVO();
		MyHistoryVO myHistoryVO = new MyHistoryVO();
		if(key!=null && key.length()!=0) {
			String decodeKey = EgovFileScrty.decode(key);
			log_msg.append("[decodeKey="+decodeKey+"]");
			LOGGER.debug("decodeKey = " + decodeKey);
			if(decodeKey!=null && decodeKey.length()!=0) {
				String[] parseKey = decodeKey.split(",");
				String HMBR_SN = "";
				String CRS_SN = "";
				if(parseKey!=null && parseKey.length > 1) {
					HMBR_SN = parseKey[0];
					CRS_SN = parseKey[1];
				} 
				log_msg.append("[parseKey="+Arrays.toString(parseKey)+"]");				
				LOGGER.debug("HMBR_SN = " + HMBR_SN);
				LOGGER.debug("CRS_SN = " + CRS_SN);
				myHistoryVO.setCRS_SN(CRS_SN);
				myHistoryVO.setHMBR_SN(HMBR_SN);
				myHistoryVO = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
				if(myHistoryVO!=null && myHistoryVO.getHMBR_SN()!=null && myHistoryVO.getHMBR_SN().length()!=0) {
					mEduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
					mEduMemberVO = eduMemberService.get_edu_member_info(mEduMemberVO);
					if(mEduMemberVO!=null && mEduMemberVO.getMBR_SN()!=null && mEduMemberVO.getMBR_SN().length()!=0) {	
						loginVO = new LoginVO();
						loginVO.setMBR_ID(mEduMemberVO.getMBR_ID());
						loginVO.setMBR_LV_ID(mEduMemberVO.getMBR_LV_ID());	
											
						//관리자가 설문조사 막았을 경우
						EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
						if(myHistoryVO.getCRS_SN().isEmpty() || myHistoryVO.getCRS_SN().length() == 0){
							log_dscrp.append("[교육과정정보를알수없음]");
							isException = true;
						} else {
							eduCurriculumVO.setCRS_SN(myHistoryVO.getCRS_SN());
							eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
							if(myHistoryVO.getLRNNG_CMPLT_ST().equals("1")) {
								if(myHistoryVO.getHMBR_SV_ST().equals("1")) {
									log_dscrp.append("[이미온라인교육설문조사완료]");
									error_msg = eduCurriculumVO.getCRS_NM()+"<br>설문조사는 이미 완료하셨습니다.";
									isException = true;
								} else {
									String crs_sv_st = eduCurriculumVO.getCRS_SV_ST();//0:미사용, 1:사용							
									if(crs_sv_st.equals("0")){
										log_dscrp.append("[온라인교육설문조사를받지않는상태]");
										error_msg = "현재 온라인교육 설문조사를 진행하지 않는 교육과정입니다.";
										isException = true;
									} else {
										SurveyVO mSurveyVO = new SurveyVO();
										mSurveyVO.setSv_id(myHistoryVO.getCRS_SV_ID());
										mSurveyVO = this.service.select_survey(mSurveyVO);
									    List<SurveyVO> quest = this.service.select_surveyq_list(mSurveyVO);
									    List<SurveyVO> qitem = this.service.select_surveyqi_list(mSurveyVO);
									    List<SurveyVO> anscnt = this.service.survey_anscnt(mSurveyVO);
									    
									    EduCertificateVO eduCertificateVO = new EduCertificateVO();
									    eduCertificateVO.setCRS_SN(myHistoryVO.getCRS_SN());
									    eduCertificateVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
									    eduCertificateVO.setMBR_ID(myHistoryVO.getMBR_ID());
									    eduCertificateVO = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
									    
									    /* 이수증 HTML 재생성 START */
									    // 회원정보
							 			EduMemberVO eduMemberVO = new EduMemberVO();
							 			eduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
							 			eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
							 			
							 			// 회원부가상세정보
							 			EduMemberVO eduMemberDtlVO = new EduMemberVO();
							 			eduMemberDtlVO.setMBR_ID(myHistoryVO.getMBR_ID());
							 			eduMemberDtlVO.setUSE_AT("Y");
							 			eduMemberDtlVO.setHIDE_AT("N");
							 			List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
							 			
							 			EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
							 			newInstanceItem.setCRS_SN(myHistoryVO.getCRS_SN());
							 			newInstanceItem.setHMBR_SN(myHistoryVO.getHMBR_SN());
							 			newInstanceItem.setLRNNG_CMPLT_ST("1");
							 			newInstanceItem.setUSE_ST("1");
							 			newInstanceItem.setDEL_ST("0");
							 			EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);
							 			
							 			//발급기관정보
							 			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
							 			eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
							 			eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
							 		    
							 		    eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
							 					request,
							 					propertiesService,
							 					eduMemberVO,
							 					list_mbr_dtl,
							 					eduCertificateVO,
							 					chkEduMyHistoryVO,
							 					eduCurriculumVO,
							 					eduCategoryInsInfVO).toDocument());
							 		    /* 이수증 HTML 재생성 END */
									    
									    model.addAttribute("item",eduCertificateVO);
									    
									    try {
											model.addAttribute("html_header", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/header.html").toString());
										} catch(Exception e) {
											model.addAttribute("html_header","");
										}
										try {
											model.addAttribute("html_footer", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/footer.html").toString());
										} catch(Exception e) {
											model.addAttribute("html_footer","");
										}
								
									    model.addAttribute("info", mSurveyVO);
									    model.addAttribute("quest", quest);
									    model.addAttribute("qitem", qitem);
									    model.addAttribute("aan", anscnt);
									    
									    model.addAttribute("myHistoryVO", myHistoryVO);
									    
									    model.addAttribute("key",EgovFileScrty.encode(HMBR_SN+","+CRS_SN+","+loginVO.getMBR_ID()));
									    
									}
									
								}
							} else {
								log_dscrp.append("[미이수상태]");
								error_msg = eduCurriculumVO.getCRS_NM()+"<br>위 모든 강의를 수강를 수강완료 하신 뒤 설문조사를 진행 하실 수 있습니다.";
								isException = true;								
							}
						}
						
					} else {
						log_dscrp.append("[사용자정보를알수없음]");		
						isException = true;
					}
				} else {
					log_dscrp.append("[교육이수정보없음]");	
					isException = true;
				}
			} else {
				log_dscrp.append("[보안키가올바르지않음]");
				isException = true;
			}			
		} else {
			log_dscrp.append("[비정상적인접근]");
			isException = true;
		}
		if(isException) {
			model.addAttribute("error_msg", error_msg);
			returnUrl = "cmmn/error";
		}		
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(myHistoryVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(mEduMemberVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(mEduMemberVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}	
		
	    return returnUrl;
	}
	
	
	
	//동영상플레이어 - 1분마다 로그남기기  ------------------------------------------------
	@RequestMapping(value="/educenter/mbrhstry/ajaxConnectionCheck.do", method = RequestMethod.POST)
	public @ResponseBody String edu_mbrhstry_ajaxConnectionCheck(@RequestParam(value="key",required=false) String key,
			@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();	
		
		request.getSession().setAttribute("VLD_VRFC_KEY", mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		String LOGIN_MBR_ID = "";
		String LOGIN_MBR_LV_ID = "";
		String userAgent = mPublicUtils.getclientUserAgent(request);
		if(mPublicUtils.isMobileDevice(request)) {
			log_dscrp.append("[모바일-교육센터사용자-연결확인]");
		} else {
			log_dscrp.append("[웹-교육센터사용자-연결확인]");
		}
		
		
		try {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			String EXTERNAL_MBR_ID = (String)request.getSession().getAttribute("EXTERNAL_MBR_ID");
			
			//외부링크시 키 검증하여 임시로그인 처리
			boolean isUnSessionTempMemberLogin = false;
			boolean isTempMemberLogin = false;
			if(key != null && key.length() != 0){
				String HMBR_DTL_SN = EgovFileScrty.decode(key);
				if(myHistoryVO.getHMBR_DTL_SN().equals(HMBR_DTL_SN)) {
					LOGGER.debug("ajaxConnectionCheckLogger : 키 정보가 일치하여 임시 로그인 처리 진행 (세션아이디 : "+EXTERNAL_MBR_ID+")");
					//log_dscrp.append("[키 정보 일치-임시 로그인 처리]");
					isTempMemberLogin = true;
					EduMemberVO mEduMemberVO = new EduMemberVO();
					if(loginVO==null || loginVO.getMBR_ID()==null){
						mEduMemberVO.setMBR_ID(EXTERNAL_MBR_ID);
					} else {
						if(myHistoryVO.getMBR_ID()==null || myHistoryVO.getMBR_ID().equals("")) {
							if(EXTERNAL_MBR_ID!=null && !EXTERNAL_MBR_ID.equals("")) {
								mEduMemberVO.setMBR_ID(EXTERNAL_MBR_ID);
							} else {
								LOGGER.debug("ajaxConnectionCheckLogger : 세션정보가 비워져 교육상세이력에서 아이디 정보 추출.");
								MyHistoryVO chkMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_info(myHistoryVO);
								if(chkMyHistoryVO.getHMBR_DTL_SN()!=null && chkMyHistoryVO.getHMBR_DTL_SN().length()!=0) {
									mEduMemberVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
								}							
							}
						} else {
							mEduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());	
						}
					}
					mEduMemberVO = eduMemberService.get_edu_member_info(mEduMemberVO);
					if(mEduMemberVO!=null && mEduMemberVO.getMBR_SN()!=null && mEduMemberVO.getMBR_SN().length()!=0) {	
						loginVO = new LoginVO();
						loginVO.setMBR_ID(mEduMemberVO.getMBR_ID());
						loginVO.setMBR_LV_ID(mEduMemberVO.getMBR_LV_ID());
					} else {
						LOGGER.debug("ajaxConnectionCheckLogger : 회원정보가없어처리불가");
						//log_dscrp.append("[회원정보가없어처리불가]");
					}
				} else { //세션이 끊긴 경우를 대비하여 처리
					if(loginVO==null || loginVO.getMBR_ID()==null) {
						LOGGER.debug("ajaxConnectionCheckLogger : 세션 정보가 존재하지 않아 임시 로그인 처리 진행");
						//log_dscrp.append("[세션 정보 없음-임시 로그인 처리]");
						isUnSessionTempMemberLogin = true;
						EduMemberVO mEduMemberVO = new EduMemberVO();
						if(loginVO==null || loginVO.getMBR_ID()==null){
							mEduMemberVO.setMBR_ID(EXTERNAL_MBR_ID);
						} else {
							if(myHistoryVO.getMBR_ID()==null || myHistoryVO.getMBR_ID().equals("")) {
								if(EXTERNAL_MBR_ID!=null && !EXTERNAL_MBR_ID.equals("")) {
									mEduMemberVO.setMBR_ID(EXTERNAL_MBR_ID);
								} else {
									LOGGER.debug("ajaxConnectionCheckLogger : 세션정보가 비워져 교육상세이력에서 아이디 정보 추출.");
									MyHistoryVO chkMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_info(myHistoryVO);
									if(chkMyHistoryVO.getHMBR_DTL_SN()!=null && chkMyHistoryVO.getHMBR_DTL_SN().length()!=0) {
										mEduMemberVO.setMBR_ID(chkMyHistoryVO.getMBR_ID());
									}							
								}
							} else {
								mEduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
							}
						}
						mEduMemberVO = eduMemberService.get_edu_member_info(mEduMemberVO);
						if(mEduMemberVO!=null && mEduMemberVO.getMBR_SN()!=null && mEduMemberVO.getMBR_SN().length()!=0) {	
							loginVO = new LoginVO();
							loginVO.setMBR_ID(mEduMemberVO.getMBR_ID());
							loginVO.setMBR_LV_ID(mEduMemberVO.getMBR_LV_ID());
						} else {
							LOGGER.debug("ajaxConnectionCheckLogger : 회원정보가없어처리불가");
							//log_dscrp.append("[회원정보가없어처리불가]");
						}
					}
				}
				
			} else {
				//log_dscrp.append("[key 정보 없음]");
			}
			//End of 외부링크시 키 검증하여 임시로그인 처리
			if(loginVO==null || loginVO.getMBR_ID()==null) {
				data.put("error", "1");
				data.put("msg", "비정상적인 접근입니다.");
			} else {
				LOGIN_MBR_ID = loginVO.getMBR_ID();
				LOGIN_MBR_LV_ID = loginVO.getMBR_LV_ID();
				//로그인아이디
				myHistoryVO.setMBR_ID(loginVO.getMBR_ID());
				
				//검증
				MyHistoryVO chkMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_info(myHistoryVO);
				if(chkMyHistoryVO.getHMBR_DTL_SN()==null || chkMyHistoryVO.getHMBR_DTL_SN().length()==0) {
					data.put("error", "1");
					data.put("msg", "존재하지 않는 정보입니다.");
				} else {
					boolean isNotRecordLog = false;
					
					
					if(isTempMemberLogin) {
						log_dscrp.append("[외부링크");							
						if(mPublicUtils.isMobileDevice(request)) {
							log_dscrp.append("-모바일");
						} else {
							log_dscrp.append("-PC");
						}
						log_dscrp.append("]");
					}
					
					if(isUnSessionTempMemberLogin) {
						log_dscrp.append("[세션부족:강제로그인처리");							
						if(mPublicUtils.isMobileDevice(request)) {
							log_dscrp.append("-모바일");
						} else {
							log_dscrp.append("-PC");
						}
						log_dscrp.append("]");
					}
				}
			}
			
			log_dscrp.append("[교육시간:" + myHistoryVO.getTRN_CUR_TIME() + "]");
			log_dscrp.append(myHistoryVO.getLOG_DTLS());
						
		} catch(Exception e) {
			LOGGER.debug("error",e);
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		
		
		/** 로그 확인용 추가 2021.08.03 ***********************************/
		try {	
			/**
			 * LOG_EUD_TB (로그기록)
			 * */
			LogRecordVO eduLogVO = new LogRecordVO();
			log_msg.append(eduLogVO.encodingFromObjectToJson(myHistoryVO));
			eduLogVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			eduLogVO.setMBR_ID(LOGIN_MBR_ID);
			eduLogVO.setCRS_SN(myHistoryVO.getCRS_SN());
			eduLogVO.setTRN_CUR_TIME(myHistoryVO.getTRN_CUR_TIME());
			eduLogVO.setHMBR_DTL_SN(myHistoryVO.getHMBR_DTL_SN());
			eduLogVO.setLOG_DSCRP(log_dscrp.toString());
			eduLogVO.setTBL_INF(tbl_inf.toString());
			eduLogVO.setTBL_SN(tbl_sn.toString());
			eduLogVO.setLOG_MSG(log_msg.toString());
			eduLogVO.setUSER_AGENT(userAgent);
			logRecordService.set_log_edu_data(eduLogVO,request);
		} catch(Exception e2) {
			LOGGER.debug("[fail log record] "+e2.toString());
		}
		/************************************************************/
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
}
