package egovframework.eduadm.myhistory.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sun.mail.smtp.SMTPOutputStream;

import egovframework.adm.member.service.AdmMemberService;
import egovframework.adm.member.service.AdmMemberVO;
import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsMentVO;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.excel.AllExcelRead;
import egovframework.all.excel.AllExcelReadOption;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.all.main.service.KakaoAlimTalkService;
import egovframework.all.main.service.KakaoAlimTalkVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.com.cmm.service.FileVO;
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
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;
import egovframework.naksinuri_original.let.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduMyHistoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EduMyHistoryController.class);
	
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

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** multipart */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "smsManagerService")
	private SmsManagerService smsManagerService;
	
	@Resource(name="NaksinuriService")
	private NaksinuriService service;

	@Resource(name = "kakaoAlimTalkService")
	private KakaoAlimTalkService kakaoAlimTalkService;

	@Resource(name = "admMemberService")
	private AdmMemberService admMemberService;
	
	//관리자(교육센터) 수강내역(회원) 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/mbrhstry/list.do")
	public Object edu_history_list(boolean isExcelDownLoad, @ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO, 
		@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			return "eduadm/error/page_400";	
		} 
		//String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();
		String MASTER_MBR_TRGT_CD = loginVO.getMBR_TRGT_CD();
		
		List<EduMyHistoryVO> list = null;
		//String addedMbrIds = "";//이미 등록된 회원 아이디 목록
		StringBuffer addedMbrIds = new StringBuffer();
		
		//----------------------------------------------------------------
		// 지역제한권한
		//----------------------------------------------------------------
		//지역 코드 조회 - 시도
		String MBR_SIDO_CD = "";
		List<CodeSetVO> list_address_cd  = null;
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		//지역 코드 조회 - 시군구
		String MBR_SIGNGU_CD = "";
		List<CodeSetVO> list_address_signgu_cd = null;
		if(MASTER_MBR_SIDO_CD==null || MASTER_MBR_SIDO_CD.length()==0) {
			//제한없음
			MBR_SIDO_CD = eduMyHistoryVO.getHMBR_SIDO_CD();
			eduMyHistoryVO.setHMBR_SIDO_CD(MBR_SIDO_CD);
		} else {
			MBR_SIDO_CD = MASTER_MBR_SIDO_CD;
			eduMyHistoryVO.setHMBR_SIDO_CD(MASTER_MBR_SIDO_CD);
		}
		if(MASTER_MBR_SIGNGU_CD==null || MASTER_MBR_SIGNGU_CD.length()==0) {
			//제한없음
			MBR_SIGNGU_CD = eduMyHistoryVO.getHMBR_SIGNGU_CD();
			eduMyHistoryVO.setHMBR_SIGNGU_CD(MBR_SIGNGU_CD);
		} else {
			MBR_SIGNGU_CD = MASTER_MBR_SIGNGU_CD;
			eduMyHistoryVO.setHMBR_SIGNGU_CD(MASTER_MBR_SIGNGU_CD);
		}		
		if(MBR_SIDO_CD!=null && MBR_SIDO_CD.length()!=0){
			CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID(MBR_SIDO_CD);
	  		list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		model.addAttribute("MBR_SIDO_CD",MBR_SIDO_CD);
		model.addAttribute("MBR_SIGNGU_CD",MBR_SIGNGU_CD);
		model.addAttribute("list_address_cd",list_address_cd);
		model.addAttribute("list_address_signgu_cd",list_address_signgu_cd);
		//----------------------------------------------------------------
		// 업종(교육대상)제한권한
		//----------------------------------------------------------------
		String MBR_TRGT_CD = "";			
		if(MASTER_MBR_TRGT_CD==null || MASTER_MBR_TRGT_CD.length()==0) {
			//제한없음
			MBR_TRGT_CD = eduMyHistoryVO.getHMBR_MBR_TRGT_CD();
		} else {
			MBR_TRGT_CD = MASTER_MBR_TRGT_CD;
			eduMyHistoryVO.setHMBR_MBR_TRGT_CD(MASTER_MBR_TRGT_CD);
		}
		model.addAttribute("MBR_TRGT_CD",MBR_TRGT_CD);
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_trgt_cd",list_mbr_cd);
		}
		//----------------------------------------------------------------
		//사업자구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00006");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_license_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_license_se_cd",list_license_se_cd);
		}			
		//낚시터업구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00007");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_fshlc_work_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_fshlc_work_cd",list_fshlc_work_cd);
		}	
								
	
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduMyHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduMyHistoryVO.getPageUnit());
		paginationInfo.setPageSize(eduMyHistoryVO.getPageSize());

		eduMyHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduMyHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduMyHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(isExcelDownLoad) {
			eduMyHistoryVO.setNotUsedPagination(true);
		}
		
		list = eduMyHistoryService.get_edu_mbrhstry_list(eduMyHistoryVO);
		List<EduMyHistoryVO> totCntList = eduMyHistoryService.get_edu_mbrhstry_list_totcnt(eduMyHistoryVO);
		
		paginationInfo.setTotalRecordCount(totCntList.size());
		model.addAttribute("paginationInfo", paginationInfo);
		for(EduMyHistoryVO item : totCntList) {
			if(addedMbrIds.toString().length()!=0) addedMbrIds.append(",");
			addedMbrIds.append(item.getMBR_ID());
		}
		LOGGER.debug("등록된 ID : " + addedMbrIds.toString());
		
		//교육과정 정보
		EduCurriculumVO parentInfo = null;
		try {
			eduCurriculumVO.setTypeStr("");
			parentInfo = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			
		} catch(Exception e) {
			parentInfo = null;
		}
		if(parentInfo==null) {
			//상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		
		//취약점 대응을 위한 처리
		if(!isExcelDownLoad) {
			for(EduMyHistoryVO item : list) {
				item.setMBR_SCRTY_KEY(EgovFileScrty.security(item.getMBR_ID(), loginVO.getMBR_ID()));
			}	
		}
		//End of 취약점 대응을 위한 처리
		
		model.addAttribute("parentInfo",parentInfo);
		model.addAttribute("list",list);
		model.addAttribute("addedMbrIds",addedMbrIds.toString());
		//검색용(기존)
		model.addAttribute("searchOrderBy",eduMyHistoryVO.getSearchOrderBy());
		model.addAttribute("searchKeyword",eduMyHistoryVO.getSearchKeyword());
		model.addAttribute("fs_LRNNG_ST",eduMyHistoryVO.getLRNNG_ST());
		model.addAttribute("fs_LRNNG_CMPLT_ST",eduMyHistoryVO.getLRNNG_CMPLT_ST());
		//추가검색용
		model.addAttribute("HMBR_SIDO_CD",eduMyHistoryVO.getHMBR_SIDO_CD());
		model.addAttribute("HMBR_SIGNGU_CD",eduMyHistoryVO.getHMBR_SIGNGU_CD());
		model.addAttribute("HMBR_MBR_TRGT_CD",eduMyHistoryVO.getHMBR_MBR_TRGT_CD());
		model.addAttribute("HMBR_YMD_NM",eduMyHistoryVO.getHMBR_YMD_NM());
		model.addAttribute("MBR_NM",eduMyHistoryVO.getMBR_NM());
		model.addAttribute("MBR_HP",eduMyHistoryVO.getMBR_HP());
		model.addAttribute("MBR_ADDR_MERGE",eduMyHistoryVO.getMBR_ADDR_MERGE());
		model.addAttribute("MBR_BIRTH",eduMyHistoryVO.getMBR_BIRTH());
		model.addAttribute("MBR_TEL",eduMyHistoryVO.getMBR_TEL());
		model.addAttribute("MBR_REG_TYPE_CD",eduMyHistoryVO.getMBR_REG_TYPE_CD());
		model.addAttribute("HMBR_DTL_NM",eduMyHistoryVO.getHMBR_DTL_NM());
		model.addAttribute("HMBR_BUSINESS_NUM",eduMyHistoryVO.getHMBR_BUSINESS_NUM());
		model.addAttribute("HMBR_REG_NUM_CD",eduMyHistoryVO.getHMBR_REG_NUM_CD());
		model.addAttribute("HMBR_SHIP_LICENSE_STR_DT",eduMyHistoryVO.getHMBR_SHIP_LICENSE_STR_DT());
		model.addAttribute("HMBR_SHIP_LICENSE_END_DT",eduMyHistoryVO.getHMBR_SHIP_LICENSE_END_DT());
		model.addAttribute("HMBR_SHIP_LICENSE_END_DT",eduMyHistoryVO.getHMBR_SHIP_LICENSE_END_DT());
		//model.addAttribute("HMBR_FSHRBT_TYPE",eduMyHistoryVO.getHMBR_FSHRBT_TYPE());//기존,신규,재개자
		//검색용조건(낚시터용)
		model.addAttribute("HMBR_DTL_ADDR",eduMyHistoryVO.getHMBR_DTL_ADDR());
		model.addAttribute("HMBR_FSHLC_APPLC",eduMyHistoryVO.getHMBR_FSHLC_APPLC());
		model.addAttribute("HMBR_FSHLC_WORK_CD",eduMyHistoryVO.getHMBR_FSHLC_WORK_CD());
		//검색용조건(낚시어선용)
		model.addAttribute("HMBR_SHIP_LICENSE",eduMyHistoryVO.getHMBR_SHIP_LICENSE());
		model.addAttribute("HMBR_SHIP_CD",eduMyHistoryVO.getHMBR_SHIP_CD());
		model.addAttribute("HMBR_SHIP_GRTG",eduMyHistoryVO.getHMBR_SHIP_GRTG());
		model.addAttribute("HMBR_SHIP_PRLOAD",eduMyHistoryVO.getHMBR_SHIP_PRLOAD());
		model.addAttribute("HMBR_SHIP_MAX_PSNGER",eduMyHistoryVO.getHMBR_SHIP_MAX_PSNGER());
		model.addAttribute("HMBR_SHIP_MAX_CREW",eduMyHistoryVO.getHMBR_SHIP_MAX_CREW());
		model.addAttribute("HMBR_DTL_LICENSE_CD",eduMyHistoryVO.getHMBR_DTL_LICENSE_CD());
		model.addAttribute("REG_MBR_ID_ST",eduMyHistoryVO.getREG_MBR_ID_ST());
				
		if(isExcelDownLoad) {
			return (ModelMap)model;
		} else {
			return "eduadm/mbrhstry/list";
		}
		
	}	
	//관리자(교육센터) 수강내역(회원) 글쓰기(추가) ------------------------------------------------
	@RequestMapping(value = "/eduadm/mbrhstry/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_mbrhstry_write(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, @ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		
		String addedMbrIds = request.getParameter("addedMbrIds");
		
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/mbrhstry/write");
		mModelAndView.addObject("parentInfo",eduCurriculumVO);
		mModelAndView.addObject("addedMbrIds",addedMbrIds);
		return mModelAndView;
	}
	//관리자(교육센터) 수강내역(회원) 글쓰기 - 엑셀업로드(추가) ------------------------------------------------
	/*@RequestMapping(value = "/eduadm/mbrhstry/write_excel.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_mbrhstry_write_excel(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, @ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/mbrhstry/write_excel");
		mModelAndView.addObject("parentInfo",eduCurriculumVO);
		return mModelAndView;
	}*/
	//관리자(교육센터) 수강내역(회원) 글수정 ------------------------------------------------
	@RequestMapping(value = "/eduadm/mbrhstry/modify.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_mbrhstry_modify(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, @ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		EduMyHistoryVO info = null;
		if(eduMyHistoryVO.getHMBR_SN()!=null && eduMyHistoryVO.getHMBR_SN().length()!=0) {
			info = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
		}	
		//교육과정 정보
		EduCurriculumVO parentInfo = null;
		try {
			eduCurriculumVO.setTypeStr("");
			parentInfo = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
		} catch(Exception e) { 
			LOGGER.debug("[fail load data] "+e.toString());
		}
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/mbrhstry/modify");
		mModelAndView.addObject("parentInfo",parentInfo);
		mModelAndView.addObject("info",info);
		return mModelAndView;
	}
	//관리자(교육센터) 수강내역(회원) 등록 ------------------------------------------------
	@RequestMapping(value="/eduadm/mbrhstry/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_mbrhstry_write_act(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-수강내역-회원추가]");
		
		int insertCount = 0;
		int insertDtlCount = 0;
		int totcnt = 0;
		int failOverlapCnt = 0;
		int successcnt = 0;
			int failcnt = 0;
		try {
			if(eduMyHistoryVO.getMBR_ID()!=null) {
				
				String MASTER_MBR_ID = loginVO.getMBR_ID();
  	  			String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
  	  			String MASTER_MBR_GRP_ID = loginVO.getMBR_GRP_ID();
  	  			String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
  	  			log_msg.append("[아이디:"+MASTER_MBR_ID+"]");
  	  			log_msg.append("[처리권한:"+MASTER_MBR_GRP_ID+"]");
  	  			log_msg.append("[처리레벨:"+MASTER_MBR_LV_ID+"]");
  	  			log_msg.append("[처리직급:"+MASTER_MBR_POSITION_CD+"]");
				
				//커리큘럼 메인
				String CRS_SN = eduMyHistoryVO.getCRS_SN();
				EduCurriculumVO parentInfo = new EduCurriculumVO();
				parentInfo.setCRS_SN(CRS_SN);
				parentInfo = eduCurriculumService.get_edu_curriculum_info(parentInfo);
				//커리큘럼 상세
				parentInfo.setNotUsedPagination(true);
				List<EduCurriculumVO> clildlist = eduCurriculumService.get_edu_curriculum_dtl_list(parentInfo);
				log_dscrp.append("["+parentInfo.getCRS_NM()+","+parentInfo.getCAT_INS_NM()+"("+parentInfo.getCRS_STR_DT()+")]");
				
				String[] mbrids = eduMyHistoryVO.getMBR_ID().replaceAll("\\s","").split(",");
				for(int i=0; i<mbrids.length; i++) {
					String traget_mbr_id = mbrids[i];
					LOGGER.debug("현재 회원 처리 중 : " + traget_mbr_id);
										
					EduMemberVO chkEduMemberVO = new EduMemberVO();
	  				chkEduMemberVO.setMBR_ID(traget_mbr_id);
	  				chkEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);
	  				if(chkEduMemberVO==null || chkEduMemberVO.getMBR_ID()==null || chkEduMemberVO.getMBR_ID().length()==0) {
	  					log_dscrp.append("[존재하지않는회원(아이디:"+traget_mbr_id+")-수강생등록실패]");
	  					failcnt++;
	  				} else {
	  					
	  					log_dscrp.append("[이름:"+chkEduMemberVO.getMBR_NM()+"(아이디:"+traget_mbr_id+")");
	  					
	  					//StringBuilder externalVideoUrl = new StringBuilder();//외부 동영상 링크 URL
	  					List<String> externalVideoUrl = new ArrayList<>();
	  					String domainUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

	  					EduMyHistoryVO chkEduMyHistoryVO = new EduMyHistoryVO();
						chkEduMyHistoryVO.setCRS_SN(parentInfo.getCRS_SN());
						chkEduMyHistoryVO.setMBR_ID(traget_mbr_id);
						chkEduMyHistoryVO.setNotUsedPagination(true);
						List<EduMyHistoryVO> list_mbrhstry = eduMyHistoryService.get_edu_mbrhstry_list(chkEduMyHistoryVO);
						if(list_mbrhstry != null && list_mbrhstry.size() > 0 ) {
							log_msg.append(",이름:"+chkEduMemberVO.getMBR_NM()+"(아이디:"+traget_mbr_id+")|이미등록된수강생으로처리안함");
							log_dscrp.append("-이미등록된수강생으로처리안함");
							failOverlapCnt++;
						} else {
							log_dscrp.append("-수강생등록완료");							
		  					
							int HMBR_MAX_TIME = Integer.parseInt(parentInfo.getCRS_TOT_TIME()) + parentInfo.getSUM_TOT_TIME();
							int HMBR_MAX_POINT = Integer.parseInt(parentInfo.getCRS_TOT_POINT()) + parentInfo.getSUM_TOT_POINT();
							
							EduMyHistoryVO newEduMyHistoryVO = new EduMyHistoryVO();
							newEduMyHistoryVO.setPURCHASE_CMPLT_ST("1");//결제(구매) 처리는 현재 보류
							newEduMyHistoryVO.setCRS_SN(parentInfo.getCRS_SN());
							newEduMyHistoryVO.setMBR_ID(traget_mbr_id);
							newEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
							newEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							newEduMyHistoryVO.setHMBR_MAX_TIME(String.valueOf(HMBR_MAX_TIME));
							newEduMyHistoryVO.setHMBR_MAX_POINT(String.valueOf(HMBR_MAX_POINT));
							
							String HMBR_SN = "";
							//중복 검증 구간
							{
								boolean isOk = false;
								do {
									HMBR_SN = newEduMyHistoryVO.getUniqKey("HMBR");
									isOk = eduMyHistoryService.get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(HMBR_SN);
								} while(!isOk);
							}
							//End of 중복 검증 구간
							newEduMyHistoryVO.setHMBR_SN(HMBR_SN);
							
							//메인 커리큘럼 정보 관련 나의이력 생성
							eduMyHistoryService.set_edu_mbrhstry_reg(newEduMyHistoryVO);
							
							tbl_inf.append("EDU_MBR_HSTRY_TB,");
							tbl_sn.append(HMBR_SN+",");
							
							//상세 커리큘럼 정보 만큼 나의상세이력생성
							int insertChildCount = 1;
							for(EduCurriculumVO crs_dtl : clildlist) {
								int insertSubCount = 0;
								String[] trnids = new String[1];
								if(crs_dtl!=null && crs_dtl.getTRN_SN()!=null && crs_dtl.getTRN_SN().length()!=0)
									trnids = crs_dtl.getTRN_SN().replaceAll("\\s","").split(",");
								for(String TRN_SN : trnids) {
									String CRS_TOT_TIME = crs_dtl.getCRS_TOT_TIME();
									String CRS_TOT_POINT = crs_dtl.getCRS_TOT_POINT();
									if(TRN_SN==null) continue;
									if(insertSubCount!=0) {
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
									newDtlEduMyHistoryVO.setMBR_ID(traget_mbr_id);
									newDtlEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
									newDtlEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
									newDtlEduMyHistoryVO.setHMBR_DTL_SN(HMBR_DTL_SN);
									eduMyHistoryService.set_edu_mbrhstry_reg_dtl(newDtlEduMyHistoryVO);
									
									tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
									tbl_sn.append(HMBR_DTL_SN+",");
									
									//외부 동영상 URL 생성
									String eduUrl = domainUrl+"/educenter/mbrhstry/external/play.do?key="+EgovFileScrty.encode(HMBR_DTL_SN);
									externalVideoUrl.add(eduUrl);
									//End of 외부 동영상 URL 생성
									
									insertSubCount++;
									insertDtlCount++;	
								}
								insertChildCount++;
							}
							//메인 커리큘럼에 회원 카운트 증가
							eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(parentInfo);
							insertCount++;
							successcnt++;
							
							//연도별 수강내역에 교육과정 업데이트
							boolean isExistTargetBean = false;
							boolean isExistTargetCrs = false;
							String TRGT_YEAR = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0",""), "yyyy-MM-dd HH:mm:ss", "yyyy");
							log_dscrp.append("|"+TRGT_YEAR+"연도별이수내역추가");
							EduMemberVO updEduMemberVO = new EduMemberVO();
							updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
							updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
							updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
							updEduMemberVO.setCRS_SN_NULL("Y");
							List<EduMemberVO> chkTargetEduBeanList = eduMemberService.get_edu_member_target_all_list(updEduMemberVO);
							updEduMemberVO.setCRS_SN_NULL("");
							updEduMemberVO.setCRS_SN(newEduMyHistoryVO.getCRS_SN());
							updEduMemberVO.setHMBR_SN(newEduMyHistoryVO.getHMBR_SN());
							List<EduMemberVO> chkTargetEduList = eduMemberService.get_edu_member_target_all_list(updEduMemberVO);
							if(chkTargetEduBeanList==null || chkTargetEduBeanList.size() == 0) {
								isExistTargetBean = false;
							} else {
								isExistTargetBean = true;
							}
							if(chkTargetEduList==null || chkTargetEduList.size() == 0) {
								isExistTargetCrs = false;
							} else {
								isExistTargetCrs = true;
							}
							if(isExistTargetCrs) {
								log_dscrp.append("-기존연도별수강내역에업데이트");
								
								updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
								updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
								updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
								updEduMemberVO.setCRS_SN_NULL("");//CRS_SN null 일때
								updEduMemberVO.setCRS_SN_NOT_NULL("Y");//CRS_SN 일때
								updEduMemberVO.setCRS_SN(newEduMyHistoryVO.getCRS_SN());
								updEduMemberVO.setHMBR_SN(newEduMyHistoryVO.getHMBR_SN());
								
		  						eduMemberService.set_edu_member_target_mod(updEduMemberVO);
							} else {
								if(isExistTargetBean) {
									log_dscrp.append("-빈연도별수강내역에업데이트");
									
									updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
									updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
									updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
									updEduMemberVO.setCRS_SN_NULL("Y");//CRS_SN null 일때
									updEduMemberVO.setCRS_SN_NOT_NULL("");//CRS_SN 일때
									updEduMemberVO.setHMBR_SN_NULL("Y");//HMBR_SN null 일때
									updEduMemberVO.setCRS_SN(newEduMyHistoryVO.getCRS_SN());
									updEduMemberVO.setHMBR_SN(newEduMyHistoryVO.getHMBR_SN());
									
			  						eduMemberService.set_edu_member_target_mod(updEduMemberVO);
								} else {
									log_dscrp.append("-연도별수강내역에신규추가");
									
									updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
									updEduMemberVO.setMBR_CD(chkEduMemberVO.getMBR_CD());
									updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
									chkEduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
									updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
									updEduMemberVO.setCRS_SN(newEduMyHistoryVO.getCRS_SN());
									updEduMemberVO.setHMBR_SN(newEduMyHistoryVO.getHMBR_SN());
									
			  						eduMemberService.set_edu_member_target_reg(updEduMemberVO);
								}
							}
													
							//SMS자동발송(즉시)
							{
								String str_crs_dt = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
								String end_crs_dt = mPublicUtils.changePatternString(parentInfo.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
								String str_crs_str_time = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "HH:mm");
								String str_crs_end_time = mPublicUtils.changePatternString(parentInfo.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "HH:mm");
								
								String eduTime = "교육일시 : "+str_crs_dt+" "+str_crs_str_time+ " ~ " + end_crs_dt + " " + str_crs_end_time;
								/*
								if(parentInfo.getCRS_GRP_CD().equals("CIDE00000000000")){	//온라인 교육이면
									String today = mPublicUtils.currentTime("yyyy-MM-dd");
									eduTime = "교육일시 : " + today + " ~ 상시(집합교육 재개 시까지 한시 운영)";
								}
								*/
								
//								String add_msg = "낚시전문교육 수강신청 접수완료 안내("+parentInfo.getCRS_NM()+")"+"\n\n"
//										+ eduTime + "\n"
//										+ "교육장소 : "+parentInfo.getCRS_PLACE()+"\n"
//										+ "교육장주소지 : "+parentInfo.getCRS_ADDR()+"\n\n";
//								String ment_msg = "";
//								SmsMentVO smsMentVO = new SmsMentVO();
//								
//								if(parentInfo.getTYPE_GB().equals("offline")){//오프라인교육
//									if(parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) { //낚시어선 신규,재개자교육
//										smsMentVO.setSMS_MENT_SN("32");	
//									} else {
//										if(parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
//											smsMentVO.setSMS_MENT_SN("34");	
//										} else {
//											smsMentVO.setSMS_MENT_SN("34");
//										}
//									}	
//								} else {
//									if(parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) { //낚시어선 신규,재개자교육
//										smsMentVO.setSMS_MENT_SN("32");	
//									} else {
//										if(parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
//											smsMentVO.setSMS_MENT_SN("4");	
//										} else {
//											smsMentVO.setSMS_MENT_SN("5");
//										}
//									}
//									smsMentVO.setSMS_MENT_DTL_CD(parentInfo.getCRS_MBR_CD());//낚시터=CIDN010200,낚시어선=CIDN010300
//								}
								String add_msg = "";
								String ment_msg = "";
								
								SmsMentVO smsMentVO = new SmsMentVO();
								if(parentInfo.getTYPE_GB().equals("offline")){//오프라인교육
									add_msg = "낚시전문교육 수강신청 접수완료 안내("+parentInfo.getCRS_NM()+")"+"\n\n"
											+ eduTime + "\n"
											+ "교육장소 : "+parentInfo.getCRS_PLACE()+"\n"
											+ "교육장주소지 : "+parentInfo.getCRS_ADDR()+"\n\n";
									if(parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) { //낚시어선 신규,재개자교육
										smsMentVO.setSMS_MENT_SN("32");	
									} else {
										if(parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
											smsMentVO.setSMS_MENT_SN("34");	
										} else {
											smsMentVO.setSMS_MENT_SN("34");
										}
									}	
								} else {
									add_msg = "낚시전문교육 수강신청 접수완료 안내("+parentInfo.getCRS_NM()+")"+"\n\n"
											+ eduTime + "\n"
											+ "교육명 : "+parentInfo.getCRS_PLACE()+"\n"
											+ "교육사이트주소 : "+parentInfo.getCRS_ADDR()+"\n\n";
									if(parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) { //낚시어선 신규,재개자교육
										smsMentVO.setSMS_MENT_SN("32");	
									} else {
										if(parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
											smsMentVO.setSMS_MENT_SN("4");	
										} else {
											smsMentVO.setSMS_MENT_SN("5");
										}
									}
									smsMentVO.setSMS_MENT_DTL_CD(parentInfo.getCRS_MBR_CD());//낚시터=CIDN010200,낚시어선=CIDN010300
								}
								
								smsMentVO = smsManagerService.get_sms_ment_info(smsMentVO);	
								if(smsMentVO!=null && smsMentVO.getSMS_MENT_SN()!=null && smsMentVO.getSMS_MENT_SN().length()!=0 ) {
									ment_msg = smsMentVO.getSMS_MENT_CONT();
								}
								
								SmsSendVO newSmsSendVO = new SmsSendVO();
								//mSmsSendVO.setMSG_TYPE();//서비스에서 자동 처리
								//newSmsSendVO.setAPIKEY();//서비스에서 자동 처리
								//newSmsSendVO.setRSTKEY();//서비스에서 자동 처리
								newSmsSendVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
								newSmsSendVO.setSMS_MENT_DTL_CD(parentInfo.getCRS_MBR_CD());
								newSmsSendVO.setMSG(add_msg+'\n'+ment_msg);							
								//newSmsSendVO.setSTAT();//예약발송일때만5
								newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));//발신번호
								newSmsSendVO.setR_PHONE(chkEduMemberVO.getMBR_HP());
								newSmsSendVO.setSUBMSG("낚시전문교육 수강신청 접수완료 안내");
								newSmsSendVO.setIMG_CNT(0);
								newSmsSendVO.setIMG_PATH("");
								newSmsSendVO.setREG_MBR_ID(MASTER_MBR_ID);
								newSmsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);			
							    newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
								String rstMsg = smsManagerService.sendToMessage(newSmsSendVO);
								log_dscrp.append("-신청완료문자발송");
								
								if(parentInfo.getTYPE_GB().equals("online")){
									if(parentInfo.getEDU_APLY_NTCN_SNDNG_YN().equals("Y")) {
										//카카오 알림톡 발송
										KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
										kakaoAlimTalkVO.setNTCN_TRSM_TEL(chkEduMemberVO.getMBR_HP());//연락처
										//(알림톡필수)
										String allEduUrl = "";
										if(parentInfo.getCRS_MBR_CD().equals("CIDN010300")){
											if(parentInfo.getCRS_YEAR().equals("2023")){
												allEduUrl += externalVideoUrl.get(0) + ",";
												allEduUrl += externalVideoUrl.get(1) + ",";
												allEduUrl += externalVideoUrl.get(2) + ",";
												allEduUrl += externalVideoUrl.get(3) + ",";
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000019");//낚시어선 전문교육신청
											} else {
												allEduUrl += externalVideoUrl.get(0) + ",";
												allEduUrl += externalVideoUrl.get(1) + ",";
												allEduUrl += externalVideoUrl.get(2) + ",";
												allEduUrl += externalVideoUrl.get(3) + ",";
												allEduUrl += externalVideoUrl.get(4) + ",";
												allEduUrl += externalVideoUrl.get(5) + ",";
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000001");//낚시어선 전문교육신청
											}
										}
										else if(parentInfo.getCRS_MBR_CD().equals("CIDN010200")){
											allEduUrl += externalVideoUrl.get(0) + ",";
											allEduUrl += externalVideoUrl.get(1) + ",";
											allEduUrl += externalVideoUrl.get(2) + ",";
											allEduUrl += externalVideoUrl.get(3) + ",";
											if(parentInfo.getCRS_YEAR().equals("2023"))
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000021");//낚시터 전문교육신청
											else 
												kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000003");//낚시터 전문교육신청
										}
										kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(parentInfo.getCRS_MBR_CD());//낚시터 전문교육신청
										
										String surveykey = EgovFileScrty.encode(HMBR_SN+","+newEduMyHistoryVO.getCRS_SN());
	//									kakaoAlimTalkVO.setSurveyUrl(domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey);// 설문조사 url
										allEduUrl += domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey;
										kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
										kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);
										kakaoAlimTalkVO.setREG_ID(loginVO.getMBR_ID());
	//									JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
										kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
										//End of 카카오 알림톡 발송
									}
									
								}
								
							}
						}
						log_dscrp.append("]");				
	  				}					
	  				totcnt++;
				}
				
				log_msg.append("[결과-전체:"+totcnt+"건,성공:"+successcnt+"건,실패:"+failcnt+"건,실패(중복):"+failOverlapCnt+"건]");
				log_msg.append("[신규데이터:"+insertCount+"건]");
				log_msg.append("[상세데이터:"+insertDtlCount+"건]");
				
				data.put("error", "0");
				data.put("msg", "총 "+successcnt+"명(중복 "+failOverlapCnt+"건)의 수강생이 추가되었습니다.");				
				
			} else {
				log_msg.append("[처리불가:교육대상자미선택]");
				log_dscrp.append("[처리실패]");
				data.put("error", "1");
				data.put("msg", "교육대상자를 선택하지 않았습니다.");
			}
										
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail process] " +e.toString());		
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일부가 일시적으로 처리되지 못했습니다.<br>잠시후 다시 시도해주세요.<br>[결과-전체:"+totcnt+"건,성공:"+successcnt+"건,실패:"+failcnt+"건,실패(중복):"+failOverlapCnt+"건]의 수강생이 처리되었습니다.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMyHistoryVO));
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
	//관리자(교육센터) 수강내역(회원) 등록 - 엑셀업로드 로직 처리 ------------------------------------------------ 현재 미사용
	/*@RequestMapping(value="/eduadm/mbrhstry/write_excel_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_mbrhstry_write_excel_act(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiRequest,
			BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		LOGGER.debug("첨부파일 검증시작 -------------------------------");		
		String inputName = "UPLOAD_FILE_ATCH";
		Map allFiles = multiRequest.getFileMap();
		Map readFiles = multiRequest.getFileMap();
		readFiles.clear();
		if (allFiles.get(inputName) != null) {
			readFiles.put(inputName, allFiles.get(inputName));
			allFiles.remove(inputName);
			LOGGER.debug("파일타겟 : " + inputName);	
	    }
		if(readFiles==null || readFiles.isEmpty()) {
			data.put("error", "1");
			data.put("msg", "파일이 선택되지 않았습니다.");
			data.put("errCnt", 1);
			LOGGER.debug("파일이 선택되지 않았음.");	
		} else {
			LOGGER.debug("파일이 선택됨.");			
			boolean isAllowFile = true;
			List _result = this.fileUtil.parseFileInf(readFiles, "NAK_", 0, "", "");
			if(_result.size() > 0) {
				FileVO fileVO = (FileVO)_result.get(0);
				if (fileVO.atchFileId.equals("ext_error")) {
					LOGGER.debug("정상적인 파일이 아닌 경우");
					data.put("error", "1");
					data.put("msg", "정상적인 파일이 아닙니다.");
					data.put("errCnt", 1);
					isAllowFile = false;
				} else {
					LOGGER.debug("정상적인 파일인 경우");
					if( fileVO.getFileExtsn().equalsIgnoreCase("xls") || fileVO.getFileExtsn().equalsIgnoreCase("xlsx") 
					|| fileVO.getFileExtsn().equalsIgnoreCase("csv") ) {
						//허용할 파일
						
					} else {
						//허용하지 않는 파일
						isAllowFile = false;
						//data.put("error", "1");
						//data.put("msg", fileVO.getFileExtsn() + " 의 파일의 확장자는 허용되지 않습니다.");
						//data.put("errCnt", 1);
					}
					LOGGER.debug("파일확장자 : " + fileVO.getFileExtsn());
				}
				LOGGER.debug("파일첨부 검증결과 : " + isAllowFile);	
				if(isAllowFile) {
					LOGGER.debug("실제 파일 첨부 시작");
					File destFile = new File(fileVO.getOrignlFileNm());
					try{
						
						StringBuilder log_dscrp = new StringBuilder();
						StringBuilder log_msg = new StringBuilder();
						StringBuilder tbl_inf = new StringBuilder();
						StringBuilder tbl_sn = new StringBuilder();
						log_dscrp.append("[교육센터관리자-수강내역-회원추가-엑셀업로드]");
						
						//첨부파일 임시 저장
						MultipartFile excelFile = multiRequest.getFile(inputName);
						excelFile.transferTo(destFile);
						
						AllExcelReadOption excelReadOption = new AllExcelReadOption();
						excelReadOption.setFilePath(destFile.getAbsolutePath());
						excelReadOption.setOutputColumns("A","B","C","D","E","F","G","H","I","J","K","L","M");
						excelReadOption.setStartRow(2);
						
						//커리큘럼 메인 정보
						String CRS_SN = eduMyHistoryVO.getCRS_SN();
						EduCurriculumVO parentInfo = new EduCurriculumVO();
						parentInfo.setCRS_SN(CRS_SN);
						parentInfo = eduCurriculumService.get_edu_curriculum_info(parentInfo);
						//커리큘럼 상세 정보
						parentInfo.setNotUsedPagination(true);
						List<EduCurriculumVO> clildlist = eduCurriculumService.get_edu_curriculum_dtl_list(parentInfo);
						
						int insertCount = 0;
						int insertDtlCount = 0;						
						
						List<Map<String, String>>excelContent = AllExcelRead.read(excelReadOption,false);
		      			for(Map<String, String> article: excelContent) {
							//LOGGER.debug(article.get("A"));//nm
							//LOGGER.debug(article.get("B"));//희망업종
							//LOGGER.debug(article.get("C"));//희망지역
							//LOGGER.debug(article.get("D"));//아이디
							//LOGGER.debug(article.get("E"));//신청일
							//LOGGER.debug(article.get("F"));//edu_mid
							//LOGGER.debug(article.get("G"));//이수유무 - 0:이수전,1:이수완료,3:신청삭제
							//LOGGER.debug(article.get("H"));//삭제유무 - 0:정상,1:삭제
							//LOGGER.debug(article.get("I"));//대기자 - 1:정상,2:대기중
							//LOGGER.debug(article.get("J"));//대기자순번 - 
							//LOGGER.debug(article.get("K"));//방문방법  - 1:(1:개인별 집결(개인자동차, 대중교통),2:전세버스(서울 2,4호선 사당역))
		      				//											2:(1:자차,2:대중교통)
		      				//LOGGER.debug(article.get("L"));//방문타입 - 0:선택없음,1,2
		      				//LOGGER.debug(article.get("M"));//교육이수점수
							
							boolean isCmtMember = false;
		      				boolean isUseMember = true;
							String traget_mbr_id = article.get("D");//회원아이디
							//이수정보
							String article_m = article.get("M");
							if(article_m==null || article_m.trim().length()==0) {
								article_m = "0";
							}
							String LRNNG_ST = "0";
							String LRNNG_CMPLT_ST = "0";
							String USE_ST = "1";
							String DEL_ST = "0";
							if(article.get("I")!=null && article.get("I").equals("1")) {//수강자
								if(article.get("G").equals("1")) {//이수완료
									LRNNG_ST = "1";
									LRNNG_CMPLT_ST = "1";
									isCmtMember = true;
								} else if(article.get("G").equals("3")) {
									LRNNG_ST = "2";
									USE_ST = "0";
									DEL_ST = "1";
									isUseMember = false;
								} else {
									LRNNG_ST = "1";
								}
							} else {//대기자
								
							}
							
							String REG_DT = article.get("E");//신청일
							//방문방법
							String CAT_VISIT_SN = "";
							if(article.get("K")==null) {
								
							} else {
								if(article.get("K").equals("0")) {
									//선택없음
								} else if(article.get("K").equals("1")) {
									if(article.get("L").equals("1")) {
										CAT_VISIT_SN = "자차,대중교통";
									} else if(article.get("L").equals("2")) {
										CAT_VISIT_SN = "전세버스";
									}
								} else if(article.get("K").equals("2")) {
									if(article.get("L").equals("1")) {
										CAT_VISIT_SN = "자차";
									} else if(article.get("L").equals("2")) {
										CAT_VISIT_SN = "대중교통";
									}
								}
							}
							//희망지역
							String HOPE_AREA = "";
							if(article.get("C")!=null) {
								String[] areas = article.get("C").split(";");
								for(String s : areas) {
									if(HOPE_AREA.length()!=0) HOPE_AREA += ",";
									int num = Integer.parseInt(s.trim());
									switch (num) {
									case 1 : HOPE_AREA+="부산"; break;
									case 2 : HOPE_AREA+="인천"; break;
									case 3 : HOPE_AREA+="울산"; break;
									case 4 : HOPE_AREA+="경기"; break;
									case 5 : HOPE_AREA+="강원"; break;
									case 6 : HOPE_AREA+="충남"; break;
									case 7 : HOPE_AREA+="전북"; break;
									case 8 : HOPE_AREA+="전남"; break;
									case 9 : HOPE_AREA+="경북"; break;
									case 10 : HOPE_AREA+="경남"; break;
									case 11 : HOPE_AREA+="제주"; break;
									case 99 : HOPE_AREA+="미정"; break;
									default: break;
									}
								}
							}
							//희망업종
							String HOPE_INDST = "";
							if(article.get("B")!=null) {
								String[] indst = article.get("B").split(";");
								for(String s : indst) {
									if(HOPE_INDST.length()!=0) HOPE_INDST += ",";
									int num = Integer.parseInt(s.trim());
									switch (num) {
									case 1 : HOPE_INDST+="어선어업"; break;
									case 2 : HOPE_INDST+="낚시어선업"; break;
									case 3 : HOPE_INDST+="양식어업(해면)"; break;
									case 4 : HOPE_INDST+="양식어업(내수면)"; break;
									case 5 : HOPE_INDST+="어촌비지니스"; break;
									case 6 : HOPE_INDST+="수산물가공,유통"; break;
									case 7 : HOPE_INDST+="소금산업"; break;
									case 99 : HOPE_INDST+="기타(미정)"; break;								
									default: break;
									}
								}		
							}
							
							LOGGER.debug("현재 회원 처리 중 : " + traget_mbr_id);
							if(traget_mbr_id==null || traget_mbr_id.length()==0) {
								continue;
							}

							int HMBR_MAX_TIME = Integer.parseInt(parentInfo.getCRS_TOT_TIME()) + parentInfo.getSUM_TOT_TIME();
							int HMBR_MAX_POINT = Integer.parseInt(parentInfo.getCRS_TOT_POINT()) + parentInfo.getSUM_TOT_POINT();
							
							EduMyHistoryVO newEduMyHistoryVO = new EduMyHistoryVO();
							newEduMyHistoryVO.setPURCHASE_CMPLT_ST("1");//결제(구매) 처리는 현재 보류
							newEduMyHistoryVO.setCRS_SN(parentInfo.getCRS_SN());
							newEduMyHistoryVO.setMBR_ID(traget_mbr_id);
							newEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
							newEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							newEduMyHistoryVO.setHMBR_MAX_TIME(String.valueOf(HMBR_MAX_TIME));
							newEduMyHistoryVO.setHMBR_MAX_POINT(String.valueOf(HMBR_MAX_POINT));
							newEduMyHistoryVO.setHMBR_INPUT_TIME(article_m);
							//newEduMyHistoryVO.setHMBR_INPUT_TIME(String.valueOf(HMBR_MAX_TIME));
							newEduMyHistoryVO.setHMBR_INPUT_POINT(String.valueOf(HMBR_MAX_POINT));
							newEduMyHistoryVO.setLRNNG_ST(LRNNG_ST);
							newEduMyHistoryVO.setLRNNG_CMPLT_ST(LRNNG_CMPLT_ST);
							newEduMyHistoryVO.setREG_DT(REG_DT);
							newEduMyHistoryVO.setUPD_DT(REG_DT);
							newEduMyHistoryVO.setUSE_ST(USE_ST);
							newEduMyHistoryVO.setDEL_ST(DEL_ST);
							newEduMyHistoryVO.setCAT_VISIT_SN(CAT_VISIT_SN);
							newEduMyHistoryVO.setHOPE_AREA(HOPE_AREA);
							newEduMyHistoryVO.setHOPE_INDST(HOPE_INDST);
							//newEduMyHistoryVO.setLRNNG_PRGRS(lRNNG_PRGRS); 이건 어떻게
							
							
							String HMBR_SN = newEduMyHistoryVO.getUniqKey("HMBR");
							newEduMyHistoryVO.setHMBR_SN(HMBR_SN);
							
							//메인 커리큘럼 정보 관련 나의이력 생성
							eduMyHistoryService.set_edu_mbrhstry_reg(newEduMyHistoryVO);
							
							tbl_inf.append("EDU_MBR_HSTRY_TB,");
							tbl_sn.append(HMBR_SN+",");
							
							//상세 커리큘럼 정보 만큼 나의상세이력생성
							int insertChildCount = 1;
							for(EduCurriculumVO crs_dtl : clildlist) {
								int insertSubCount = 0;
								String[] trnids = new String[1];
								if(crs_dtl!=null && crs_dtl.getTRN_SN()!=null && crs_dtl.getTRN_SN().length()!=0)
									trnids = crs_dtl.getTRN_SN().replaceAll("\\s","").split(",");
								for(String TRN_SN : trnids) {
									String CRS_TOT_TIME = crs_dtl.getCRS_TOT_TIME();
									String CRS_TOT_POINT = crs_dtl.getCRS_TOT_POINT();
									if(TRN_SN==null) continue;
									if(insertSubCount!=0) {
										CRS_TOT_TIME = "0";
										CRS_TOT_POINT = "0";
									}
									
									EduTrainingDataVO eduTrainingDataVO = new EduTrainingDataVO();
									eduTrainingDataVO.setTRN_SN(TRN_SN);
									EduTrainingDataVO tdata = eduTrainingDataService.get_edu_data_info(eduTrainingDataVO);
									
									EduMyHistoryVO newDtlEduMyHistoryVO = new EduMyHistoryVO();
									String HMBR_DTL_SN = newDtlEduMyHistoryVO.getUniqKey("HMBRD");
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
									newDtlEduMyHistoryVO.setMBR_ID(traget_mbr_id);
									newDtlEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
									newDtlEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
									newDtlEduMyHistoryVO.setHMBR_DTL_SN(HMBR_DTL_SN);
									eduMyHistoryService.set_edu_mbrhstry_reg_dtl(newDtlEduMyHistoryVO);
									
									tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
									tbl_sn.append(HMBR_DTL_SN+",");
									
									insertSubCount++;
									insertDtlCount++;	
								}
								insertChildCount++;
							}
							
							//메인 커리큘럼에 회원 카운트 증가
							if(isUseMember) eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(parentInfo);
							if(isCmtMember) {
								eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_up(parentInfo);
								
								//나의교육정보 재호출
								EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newEduMyHistoryVO);
																
								//이수증 발급 진행
								EduCertificateVO eduCertificateVO = new EduCertificateVO();
								eduCertificateVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
								eduCertificateVO.setHMBR_SN(HMBR_SN);
								eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
								EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
								if(existInfo == null || existInfo.getCRTF_SN() == null || existInfo.getCRTF_SN().length() == 0) {
									//회원정보
									EduMemberVO eduMemberVO = new EduMemberVO();
									eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
									eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
									//회원부가상세정보
									EduMemberVO eduMemberDtlVO = new EduMemberVO();
									eduMemberDtlVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
									eduMemberDtlVO.setUSE_AT("Y");
									eduMemberDtlVO.setHIDE_AT("N");
									List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
									//교육과정정보
									EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
									eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
									eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
									//점수 재계산 - 입력 받은 값 그대로 반영
									int LRNNG_TOT_TIME = eduCurriculumVO.getSUM_TOT_TIME();//교과목이수종합시간
									int LRNNG_TOT_POINT = eduCurriculumVO.getSUM_TOT_POINT();//교과목이수종합점수
									int HMBR_INPUT_TIME = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_TIME());//입력받은교육시간
									int HMBR_INPUT_POINT = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_POINT());//입력받은교육점수
									int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
									int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
									chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
									chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
									/
									//점수 재계산 - 최고점수를 반영
									int LRNNG_TOT_TIME = eduCurriculumVO.getSUM_TOT_TIME();//교과목이수종합시간
									int LRNNG_TOT_POINT = eduCurriculumVO.getSUM_TOT_POINT();//교과목이수종합점수 
									int HMBR_INPUT_TIME = Integer.parseInt(eduCurriculumVO.getCRS_TOT_TIME());//최대인정교육시간
									int HMBR_INPUT_POINT = Integer.parseInt(eduCurriculumVO.getCRS_TOT_POINT());//최대인정교육점수
									int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
									int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
									chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
									chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
									/
									//발급기관정보
									EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
									eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
									eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
									//새로 발급할 이수증
									eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());
									eduCertificateVO.setREG_MBR_ID(loginVO.getMBR_ID());
									eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
									String insertId = eduCertificateVO.getCRTF_SN();
									try {
										eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
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
									}
									LOGGER.debug("새로운 이수증 발급 : " + insertId);
									log_dscrp.append("[새로운 이수증 발급 : " + insertId+"]");									 
								} else {
									//이수증 발급 재시작
									eduCertificateService.set_edu_certificate_mod_use_unlock(existInfo);
									LOGGER.debug("이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
									log_dscrp.append("[이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN()+"]");
								}
								
							}
							insertCount++;
							
						}
						
						data.put("error", "0");
						data.put("msg", "정상적으로 등록되었습니다.");
						data.put("errCnt", 0);
						
						log_dscrp.append("[신규데이터:"+insertCount+"건]");
						log_dscrp.append("[상세데이터:"+insertDtlCount+"건]");
						
						try {
							LogRecordVO mEduLogRecordVO = new LogRecordVO();
							log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMyHistoryVO));
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
						
					} catch(IllegalStateException | IOException e) {
						LOGGER.debug("첨부파일에 오류 발생.");							
						data.put("error", "1");
						data.put("msg", "첨부파일에 오류가 있습니다.");
						data.put("errCnt", 1);
					}					
				} else {
					LOGGER.debug("첨부에 허용하지 않는 파일.");							
					data.put("error", "1");
					data.put("msg", "첨부에 허용하지 않는 파일입니다.");
					data.put("errCnt", 1);
				}
			} else {
				LOGGER.debug("파일이 선택되지 않은 경우.");
				data.put("error", "1");
				data.put("msg", "파일이 선택되지 않았습니다.");
				data.put("errCnt", 1);		
			}			
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	*/
	//관리자(교육센터) 수강내역(회원) 수정 ------------------------------------------------
	@RequestMapping(value="/eduadm/mbrhstry/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_mbrhstry_modify_act(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
	
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-수강내역-회원수정]");
		//검증
		EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
		if(chkEduMyHistoryVO.getHMBR_SN()==null || chkEduMyHistoryVO.getHMBR_SN().length()==0) {
			log_msg.append("[처리불가:존재하지않는정보]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");
		} else {
			try {	
				boolean isCmpltCntUpdate = true;
				boolean isException = false;					
								
				log_msg.append("[MBR_ID="+chkEduMyHistoryVO.getMBR_ID());
				log_msg.append("|CRS_SN="+chkEduMyHistoryVO.getCRS_SN());
				log_msg.append("|HMBR_SN="+chkEduMyHistoryVO.getHMBR_SN());
				
				//교육과정정보
				EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
				eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
				eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
				log_dscrp.append("["+eduCurriculumVO.getCRS_NM()+","+eduCurriculumVO.getCAT_INS_NM()+"("+eduCurriculumVO.getCRS_STR_DT()+")]");
				
				//회원정보
				EduMemberVO eduMemberVO = new EduMemberVO();
				eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
				eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
				log_dscrp.append("[이름:"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+",교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");
				
				if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("0")){// 대기
					eduMyHistoryVO.setLRNNG_ST("0");
					eduMyHistoryVO.setLRNNG_CMPLT_ST("0");
				} else if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("1")){//가이수
					eduMyHistoryVO.setLRNNG_ST("0");
					eduMyHistoryVO.setLRNNG_CMPLT_ST("1");
				} else if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("2")){//이수완료
					eduMyHistoryVO.setLRNNG_ST("1");
					eduMyHistoryVO.setLRNNG_CMPLT_ST("1");
				} else if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("3")){//취소
					eduMyHistoryVO.setLRNNG_ST("2");
					eduMyHistoryVO.setLRNNG_CMPLT_ST("0");
				} else if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("4")){//보류
					eduMyHistoryVO.setLRNNG_ST("4");
					eduMyHistoryVO.setLRNNG_CMPLT_ST("0");
				} else if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("5")){//이수취소
					eduMyHistoryVO.setLRNNG_ST("0");
					eduMyHistoryVO.setLRNNG_CMPLT_ST("2");
				} else {// 값이 없을 경우 대기로 간주
					eduMyHistoryVO.setLRNNG_ST("0");
					eduMyHistoryVO.setLRNNG_CMPLT_ST("0");
				}
				
				if(!eduMyHistoryVO.getLRNNG_ST().equals(chkEduMyHistoryVO.getLRNNG_ST()) //신청상태
				|| !eduMyHistoryVO.getLRNNG_CMPLT_ST().equals(chkEduMyHistoryVO.getLRNNG_CMPLT_ST())) { //이수상태
					//둘 중 하나라도 상태 변경시 사용함으로 강제 변경함.
					log_dscrp.append("-상태가 변경되어 강제로 활성화");
					eduMyHistoryVO.setUSE_ST_CHK("Y");
				}
				
				if(eduMyHistoryVO.getUSE_ST_CHK().equals("Y")) {//사용함
					eduMyHistoryVO.setUSE_ST("1");
					eduMyHistoryVO.setDEL_ST("0");
					
					// 신청 상태를 대기로 전환
					/*if(chkEduMyHistoryVO.getLRNNG_ST().equals("2") || chkEduMyHistoryVO.getLRNNG_ST().equals("3") || chkEduMyHistoryVO.getLRNNG_ST().equals("4")) {
						log_dscrp.append("-신청상태를대기상태로전환");
						eduMyHistoryVO.setLRNNG_ST("0");
					}*/
					if(chkEduMyHistoryVO.getDEL_ST().equals("1")) {//삭제 -> 사용함 변경시 하위데이터도 같이 변경
						EduMyHistoryVO cloneEduMyHistoryVO = new EduMyHistoryVO();
						cloneEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
						cloneEduMyHistoryVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
						cloneEduMyHistoryVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						cloneEduMyHistoryVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
						cloneEduMyHistoryVO.setUSE_ST("1");
						cloneEduMyHistoryVO.setDEL_ST("0");
						eduMyHistoryService.set_edu_mbrhstry_mod_dtl(cloneEduMyHistoryVO);
						
						//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
						//eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
						eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(eduCurriculumVO);
						log_dscrp.append("-삭제=>사용함 변경시 하위데이터도 같이 변경");
						
						if(eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) {//사용함
							eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_up(eduCurriculumVO);
							log_dscrp.append("-삭제=>이수완료되어있으므로카운트증가");
							isCmpltCntUpdate = false;
						}
						
					} 
				} else {//사용안함
					eduMyHistoryVO.setUSE_ST("0");
					eduMyHistoryVO.setLRNNG_ST("2");
					eduMyHistoryVO.setLRNNG_CMPLT_ST("0");
					if(chkEduMyHistoryVO.getUSE_ST().equals("1")) {
						eduMyHistoryService.del_edu_mbrhstry(eduMyHistoryVO);
						//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
						//eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
						eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(eduCurriculumVO);
					}
				}
				
				
				//수강신청 상태값
				if(chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals(eduMyHistoryVO.getLRNNG_CMPLT_ST())) {
					//변경사항없음.
					LOGGER.debug("수강신청 상태 - 변경사항없음 : " + eduMyHistoryVO.getLRNNG_CMPLT_ST());
					if(eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { //이수완료						
						//이수증 발급 조회
						EduCertificateVO eduCertificateVO = new EduCertificateVO();
						eduCertificateVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
						eduCertificateVO.setHMBR_SN(eduMyHistoryVO.getHMBR_SN());
						eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
						if(existInfo == null || existInfo.getCRTF_SN() == null || existInfo.getCRTF_SN().length() == 0) {
							//신규발급
							//회원정보
							//EduMemberVO eduMemberVO = new EduMemberVO();
							//eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							//eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
							//교육과정정보
							//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
							//eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							//eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
							//점수 재계산
							int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());//교과목이수종합시간
							int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());//교과목이수종합점수
							int HMBR_INPUT_TIME = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_TIME());//최대인정교육시간
							int HMBR_INPUT_POINT = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_POINT());//최대인정교육점수
							int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
							int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
							chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
							chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
							
							//발급기관정보
							EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
							eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
							eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
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
								log_dscrp.append("-이수증발급실패");
								log_msg.append("|이수증발급실패("+e.toString()+")");
								isException = true;
							}
							LOGGER.debug("새로운 이수증 발급 : " + insertId);
							log_dscrp.append("-새로운이수증발급");
							log_msg.append("|새로운이수증발급("+insertId+")");
						} else {//기존회원업데이트								
							//회원정보
							//EduMemberVO eduMemberVO = new EduMemberVO();
							//eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							//eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
							//교육과정정보
							//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
							//eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							//eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
							//점수 재계산
							int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());//교과목이수종합시간
							int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());//교과목이수종합점수
							int HMBR_INPUT_TIME = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_TIME());//최대인정교육시간
							int HMBR_INPUT_POINT = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_POINT());//최대인정교육점수
							int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
							int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
							chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
							chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
							//발급기관정보
							EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
							eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
							eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
							//회원부가상세정보
							EduMemberVO eduMemberDtlVO = new EduMemberVO();
							eduMemberDtlVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							eduMemberDtlVO.setUSE_AT("Y");
							eduMemberDtlVO.setHIDE_AT("N");
							List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
							try {
								//이수증 업데이트
								EduCertificateVO updateItem = new EduCertificateVO();
								updateItem.setCRTF_SN(existInfo.getCRTF_SN());
								updateItem.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
								updateItem.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
										request,
										propertiesService,
										eduMemberVO,
										list_mbr_dtl,
										existInfo,
										chkEduMyHistoryVO,
										eduCurriculumVO,
										eduCategoryInsInfVO).toDocument());
								eduCertificateService.set_edu_certificate_mod(updateItem);
							} catch(Exception e) {
								log_dscrp.append("-이수증업데이트실패");
								log_msg.append("|이수증발급실패("+e.toString()+")");
								isException = true;
							}
							LOGGER.debug("기존 이수증 업데이트 : " + existInfo.getCRTF_CD());
							log_dscrp.append("-기존이수증업데이트");
							log_msg.append("|기존 이수증 업데이트(" + existInfo.getCRTF_CD()+")");
						}
						isCmpltCntUpdate = false;
					} 
				} else {
					//변경사항있음
					LOGGER.debug("수강신청 완료 - 변경사항있음");
					//이수증 발급 진행
					EduCertificateVO eduCertificateVO = new EduCertificateVO();
					eduCertificateVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
					eduCertificateVO.setHMBR_SN(eduMyHistoryVO.getHMBR_SN());
					eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
					EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
					if(existInfo == null || existInfo.getCRTF_SN() == null || existInfo.getCRTF_SN().length() == 0) {
						if(eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { //기타 -> 이수완료
							//회원정보
							//EduMemberVO eduMemberVO = new EduMemberVO();
							//eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							//eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
							//교육과정정보
							//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
							//eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							//eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
							//점수 재계산
							int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());//교과목이수종합시간
							int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());//교과목이수종합점수
							int HMBR_INPUT_TIME = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_TIME());//최대인정교육시간
							int HMBR_INPUT_POINT = Integer.parseInt(eduMyHistoryVO.getHMBR_INPUT_POINT());//최대인정교육점수
							int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
							int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
							chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
							chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
							chkEduMyHistoryVO.setTMPR_LRNNG_CMPLT_DT(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
							//발급기관정보
							EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
							eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
							eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
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
								log_dscrp.append("-이수증발급실패");
								log_msg.append("|이수증발급실패("+e.toString()+")");
								isException = true;
								isCmpltCntUpdate = false;
							}
							LOGGER.debug("새로운 이수증 발급 : " + insertId);
							log_dscrp.append("-새로운이수증발급");
							log_msg.append("|새로운 이수증 발급("+insertId+")");
						}
					} else {
						//이미 발급된 이수증
						if(eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { //기타 -> 이수완료
							//이수증 발급 재시작
							eduCertificateService.set_edu_certificate_mod_use_unlock(existInfo);
							LOGGER.debug("이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
							log_dscrp.append("-이미 발급된 이수증(발급재시작)");
							log_msg.append("|이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
						} else { //이수완료 -> 기타
							//이수증 발급 중단
							//eduCertificateService.set_edu_certificate_mod_use_lock(existInfo);
							//LOGGER.debug("이미 발급된 이수증(발급중단) : " + existInfo.getCRTF_SN());
							//log_dscrp.append("[이미 발급된 이수증(발급중단) : " + existInfo.getCRTF_SN()+"]");
							
							//기존 이수증 삭제 처리
							eduCertificateService.remove_edu_certificate(existInfo);
							eduCertificateService.remove_edu_certificate_dtl(existInfo);
							LOGGER.debug("기존 이수증 삭제(하위포함) : " + existInfo.getCRTF_CD());
							log_dscrp.append("-기존이수증삭제(하위포함)");
							log_msg.append("|기존 이수증 삭제(하위포함)(" + existInfo.getCRTF_SN()+")");
						}
					}
				}
				
				//설문조사 초기화 검증 구간
				/*if( !chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals(eduMyHistoryVO.getLRNNG_CMPLT_ST()) 
				&& !eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")
				&& chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")
				) {
					eduMyHistoryVO.setHMBR_SV_ST("0");//0:미참여, 1:참여
					//설문조사 삭제 추가하기
					
					log_dscrp.append("-설문조사 초기화");
					log_msg.append("|설문조사 초기화");
					
				}*/
				//End of 설문조사 초기화 검증 구간
				
				eduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				if(!isException) eduMyHistoryService.set_edu_mbrhstry_mod(eduMyHistoryVO);
				
				//사용자사유로그남기기
				{
					EduMyHistoryVO realEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
					logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB","수정",eduMyHistoryVO.getLOG_UPD_MSG(),chkEduMyHistoryVO.getMBR_ID(),chkEduMyHistoryVO.getMBR_NM(),chkEduMyHistoryVO,realEduMyHistoryVO,loginVO,request);
				}
				
				tbl_inf.append("EDU_MBR_HSTRY_TB,");
				tbl_sn.append(eduMyHistoryVO.getHMBR_SN()+",");					
				
				//교육과정정보
				//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
				//eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
				eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
				
				//연도별이수내역 체크
				String TRGT_YEAR = mPublicUtils.changePatternString(eduCurriculumVO.getCRS_STR_DT().replace(".0",""), "yyyy-MM-dd HH:mm:ss", "yyyy");
								
				//이수상태 변경
				if( chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals(eduMyHistoryVO.getLRNNG_CMPLT_ST()) ) {
					//변경사항 없음.
				} else {
					//변동사항 있음. 
					//커리큘럼 메인 갱신
					if(isCmpltCntUpdate) {
						EduCurriculumVO updateEduCurriculumVO = new EduCurriculumVO();
						updateEduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
						if(eduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { //대기 -> 완료 (직접변경하는 경우 최고점 처리)
							eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_up(updateEduCurriculumVO);
							log_dscrp.append("-대기=>완료");
							log_msg.append("|대기 -> 완료 (직접변경하는 경우 최고점 처리)");
							
							//년도별이수내역에 이수처리 완료 반영
							EduMemberVO updEduMemberVO = new EduMemberVO();
							updEduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
							updEduMemberVO.setCMPLT_MBR_ID(loginVO.getMBR_ID());
							updEduMemberVO.setCMPLT_ST("1");
							updEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							updEduMemberVO.setCRS_SN_NOT_NULL("Y");//CRS_SN 일때
							updEduMemberVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							updEduMemberVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
							
//							if(eduCurriculumVO.getTYPE_GB().equals("online")) {
//								//카카오 알림톡 발송
//								String[] parseAddr = eduMemberVO.getMBR_ADDR1().split(" ");
//								//이수증발급정보 재호출
//								EduCertificateVO reEduCertificateVO = new EduCertificateVO();
//								reEduCertificateVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
//								reEduCertificateVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
//								reEduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
//								EduCertificateVO reEduExistInfo = eduCertificateService.get_edu_certificate_info(reEduCertificateVO);	
//								//교육정보 재호출
//								String eduStrDt = eduCurriculumVO.getCRS_STR_DT(); 
//								String eduEndDt = eduCurriculumVO.getCRS_END_DT(); 
//								//카카오 알림톡 발송
//								KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
//								kakaoAlimTalkVO.setTelNum(eduMemberVO.getMBR_HP());//연락처
//								//(알림톡필수)
//								String complEdycIssuNo = reEduExistInfo.getCRTF_CD();
//								kakaoAlimTalkVO.setComplEdycIssuNo(complEdycIssuNo);//이수증발급번호
//								kakaoAlimTalkVO.setCmpletNm(eduMemberVO.getMBR_NM());//성명
//								kakaoAlimTalkVO.setCmpletBrdt(eduMemberVO.getMBR_BIRTH());//생년월일
//								kakaoAlimTalkVO.setCmpletAdres(eduMemberVO.getMBR_ADDR1());//주소
//								kakaoAlimTalkVO.setEduNm(reEduExistInfo.getCRS_NM()	);
//								if(eduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")){ // 신규재개자 전문교육
//									kakaoAlimTalkVO.setEduStrDt(eduStrDt.replace(".0", ""));//교육일시
//									kakaoAlimTalkVO.setEduEndDt(eduEndDt.replace(".0", ""));//교육일시
//									kakaoAlimTalkVO.setTemplateId("KKO_000007");//이수증발급
//								} else {
//									kakaoAlimTalkVO.setEduYmd(eduStrDt.replace(".0", "") + " ~ " + eduEndDt.replace(".0", ""));//교육일시
//									if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010300")){ // 낚시어선 전문교육
//										kakaoAlimTalkVO.setTemplateId("KKO_000005");//이수증발급
//									} else if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")){ // 낚시터 전문교육
//										kakaoAlimTalkVO.setTemplateId("KKO_000006");//이수증발급
//									}
//								}
//								kakaoAlimTalkVO.setAsync(true);//동기화 전송 여부(true:비동기,false:동기[기본값])
//								
//								JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
//							}
							
							eduMemberService.set_edu_member_target_mod(updEduMemberVO);
							
							log_dscrp.append("|"+TRGT_YEAR+"연도별이수내역에이수처리반영");
							
						} else { //완료 -> 대기
							if(chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) {
								// 취소, 보류, 이수취소 시 수강신청인원감소
								/*if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("3")){//취소
									eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(updateEduCurriculumVO);
								} else if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("4")){//보류
									eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(updateEduCurriculumVO);
								} else if(eduMyHistoryVO.getLRNNG_CHECK_ST().equals("5")){//이수취소
									eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(updateEduCurriculumVO);
								}*/
								
								eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_down(updateEduCurriculumVO);
								log_dscrp.append("|완료=>대기");
								log_msg.append("|완료 -> 대기");
																
								//년도별이수내역에 이수처리 취소 반영
								EduMemberVO updEduMemberVO = new EduMemberVO();
								updEduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
								updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
								updEduMemberVO.setCMPLT_ST("0");
								updEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
								updEduMemberVO.setCRS_SN_NOT_NULL("Y");//CRS_SN 일때
								updEduMemberVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
								updEduMemberVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
								eduMemberService.set_edu_member_target_mod(updEduMemberVO);
								
								log_dscrp.append("-"+TRGT_YEAR+"연도별이수내역에이수취소처리반영");
								log_msg.append("|"+TRGT_YEAR+"연도별이수내역에이수취소처리반영");
								
							} else {
								log_dscrp.append("-대기=>이수취소 또는 이수취소=>대기");
								log_msg.append("|대기 -> 이수취소 또는 이수취소 -> 대기");	
							}
						}	
					}
				}	
				
				log_dscrp.append("]");
				
				data.put("error", "0");
				data.put("msg", "수강내역 정보가 수정되었습니다.");				
							
			} catch(Exception e) {
				e.printStackTrace();
				LOGGER.debug("[fail process] " + e.toString());
				log_msg.append("[에러발생("+e.toString()+")]");
				log_dscrp.append("[처리실패]");
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMyHistoryVO));
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
	//관리자(교육센터) 수강내역(회원) 선택회원일괄이수처리 수정 로직 ------------------------------------------------
	@RequestMapping(value="/eduadm/mbrhstry/modify_all_cmplt_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_mbrhstry_modify_all_cmplt_act(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-수강내역-회원수정-선택회원일괄이수처리]");		
		//검증
		if(eduMyHistoryVO.getCRS_SN()==null || eduMyHistoryVO.getCRS_SN().length()==0) {
			log_msg.append("[처리불가:비정상적인접근]");
			log_dscrp.append("[처리불가:비정상적인접근]");
			data.put("error", "1");
			data.put("msg", "비정상적인 접근입니다.");
		} else {
 
			//교육과정정보
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			log_dscrp.append("["+eduCurriculumVO.getCRS_NM()+","+eduCurriculumVO.getCAT_INS_NM()+"("+eduCurriculumVO.getCRS_STR_DT()+")]");
			
			String parameter_chkedHMbrIds = request.getParameter("chkedHMbrIds");
			String[] chkedHMbrIds = parameter_chkedHMbrIds.replaceAll("\\s","").split(",");
			for(String hmbr_sn : chkedHMbrIds) {
				if(hmbr_sn==null) {
					log_dscrp.append("[비정상적인데이터(일련번호:"+hmbr_sn+")-처리실패]");
					log_msg.append("[비정상적인데이터:HMBR_SN="+hmbr_sn+"]");
					continue;					
				}
								
				//검증
				EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
				newInstanceItem.setCRS_SN(eduMyHistoryVO.getCRS_SN());
				newInstanceItem.setHMBR_SN(hmbr_sn);
				newInstanceItem.setLRNNG_CMPLT_ST("1");
				newInstanceItem.setUSE_ST("1");
				newInstanceItem.setDEL_ST("0");
				EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);
				if(chkEduMyHistoryVO.getHMBR_SN()==null || chkEduMyHistoryVO.getHMBR_SN().length()==0) {
					log_dscrp.append("[정보없음(일련번호:"+hmbr_sn+")]");
					log_msg.append("[정보없음:HMBR_SN="+hmbr_sn+"]");
					continue;
				} else {
					boolean isCmpltCntUpdate = true;
					boolean isException = false;	
					//회원정보
					EduMemberVO eduMemberVO = new EduMemberVO();
					eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
					eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
					if(eduMemberVO == null || eduMemberVO.getMBR_NM()==null || eduMemberVO.getMBR_NM().length()==0) {
						isException = true;
						isCmpltCntUpdate = false;
						log_dscrp.append("[존재하지않는회원(교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");
					} else {
						log_dscrp.append("[이름:"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+",교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");	
					}
					//이수상태 변경
					if( chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1") ) {
						//변경사항 없음.
						LOGGER.debug("이미 이수완료 - 변경사항없음");
						log_dscrp.append("-이미이수가완료된회원");
						log_msg.append("|HMBR_SN="+hmbr_sn+":이미이수가완료된회원");
					} else {
						//변동사항 있음.
						LOGGER.debug("이수완료 시작 - 변경사항있음");
						log_msg.append("|HMBR_SN="+hmbr_sn);
						//이수증 발급 진행
						EduCertificateVO eduCertificateVO = new EduCertificateVO();
						eduCertificateVO.setCRS_SN(newInstanceItem.getCRS_SN());
						eduCertificateVO.setHMBR_SN(newInstanceItem.getHMBR_SN());
						eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
						if(existInfo == null || existInfo.getCRTF_SN() == null || existInfo.getCRTF_SN().length() == 0) {	
							if(eduMemberVO != null && eduMemberVO.getMBR_NM()!=null && eduMemberVO.getMBR_NM().length()!=0) {
								//교육과정정보
								//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
								//eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
								//eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
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
								chkEduMyHistoryVO.setTMPR_LRNNG_CMPLT_DT(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
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
									log_dscrp.append("-새로운이수증발급");
									log_msg.append("|새로운이수증발급");
								} catch(Exception e) {
									e.printStackTrace();
									log_dscrp.append("-새로운이수증발급실패");
									log_msg.append("새로운이수증발급시오류발생("+e.toString()+")");
									isException = true;
									isCmpltCntUpdate = false;
								}
								LOGGER.debug("(HMBR_SN="+hmbr_sn+")새로운 이수증 발급 : " + insertId);
								log_msg.append("[(HMBR_SN="+hmbr_sn+")새로운 이수증 발급 : " + insertId);
							} else {
								log_dscrp.append("-새로운이수증발급실패");
								log_msg.append("|회원정보가없어새로운이수증발급실패");
							}
						} else {
							//이수증 발급 재시작
							if(eduMemberVO != null && eduMemberVO.getMBR_NM()!=null && eduMemberVO.getMBR_NM().length()!=0) {
//								//발급기관정보
//								EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
//								eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
//								eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
//								//회원부가상세정보
//								EduMemberVO eduMemberDtlVO = new EduMemberVO();
//								eduMemberDtlVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
//								eduMemberDtlVO.setUSE_AT("Y");
//								eduMemberDtlVO.setHIDE_AT("N");
//								List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
//								try {
//									eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
//											request,
//											propertiesService,
//											eduMemberVO,
//											list_mbr_dtl,
//											eduCertificateVO,
//											chkEduMyHistoryVO,
//											eduCurriculumVO,
//											eduCategoryInsInfVO).toDocument());
//									eduCertificateService.set_edu_certificate_mod_use_unlock(existInfo);
//									LOGGER.debug("(HMBR_SN="+hmbr_sn+")이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
//									log_dscrp.append("-이미발급된이수증(재발급재시작)");
//									log_msg.append("|이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
//									isCmpltCntUpdate = false;
//								} catch(Exception e) {
//									e.printStackTrace();
//									log_dscrp.append("-새로운이수증발급실패");
//									log_msg.append("새로운이수증발급시오류발생("+e.toString()+")");
//									isException = true;
//									isCmpltCntUpdate = false;
//								}
								eduCertificateService.set_edu_certificate_mod_use_unlock(existInfo);
								LOGGER.debug("(HMBR_SN="+hmbr_sn+")이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
								log_dscrp.append("-이미발급된이수증(재발급재시작)");
								log_msg.append("|이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
								isCmpltCntUpdate = false;
							} else {
								log_dscrp.append("-새로운이수증발급실패");
								log_msg.append("|회원정보가없어새로운이수증발급실패");
							}
						}
						
						newInstanceItem.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						newInstanceItem.setUPD_MBR_ID(loginVO.getMBR_ID());
						if(!isException) {
							//커리큘럼 메인 갱신
							EduCurriculumVO updateEduCurriculumVO = new EduCurriculumVO();
							updateEduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_up(updateEduCurriculumVO);
							log_dscrp.append("-교육과정에카운트반영");
							log_msg.append("|교육과정에카운트반영");
								
							if(chkEduMyHistoryVO.getDEL_ST().equals("1")) {
								eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(updateEduCurriculumVO);
								newInstanceItem.setLRNNG_ST("0");
								log_dscrp.append("-수강상태를대기로전환");
								log_msg.append("|수강상태를대기로전환");
							}
								
							EduMemberVO updEduMemberVO = new EduMemberVO();
							updEduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							updEduMemberVO.setCMPLT_MBR_ID(loginVO.getMBR_ID());
							updEduMemberVO.setCMPLT_ST("1");
							updEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
							updEduMemberVO.setCRS_SN_NOT_NULL("Y");//CRS_SN 일때
							updEduMemberVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							updEduMemberVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
							eduMemberService.set_edu_member_target_mod(updEduMemberVO);
							log_dscrp.append("-년도별이수내역에이수처리반영");
							log_msg.append("|년도별이수내역에이수처리반영");

							newInstanceItem.setLRNNG_ST("0");
							eduMyHistoryService.set_edu_mbrhstry_mod(newInstanceItem);
							log_dscrp.append("-이수완료처리");
							log_msg.append("|이수완료처리");
							
							//사용자사유로그남기기
							{
								EduMyHistoryVO realEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(chkEduMyHistoryVO);
								logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB","일괄가이수완료처리(이수증발급)",eduMyHistoryVO.getLOG_UPD_MSG(),chkEduMyHistoryVO.getMBR_ID(),chkEduMyHistoryVO.getMBR_NM(),chkEduMyHistoryVO,realEduMyHistoryVO,loginVO,request);
							}
							
						}
						
						tbl_inf.append("EDU_MBR_HSTRY_TB,");
						tbl_sn.append(newInstanceItem.getHMBR_SN()+",");
					}
					log_dscrp.append("]");
				}
			}
			data.put("error", "0");
			data.put("msg", "수강내역 정보가 수정되었습니다.");	
			
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
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
	//관리자(교육센터) 수강내역(회원) 선택회원일괄이수취소처리 수정 로직 ------------------------------------------------
	@RequestMapping(value="/eduadm/mbrhstry/modify_all_cmplt_cancel_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_mbrhstry_modify_all_cmplt_cancel_act(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-수강내역-회원수정-선택회원일괄이수취소처리]");
		//검증
		if(eduMyHistoryVO.getCRS_SN()==null || eduMyHistoryVO.getCRS_SN().length()==0) {
			log_msg.append("[처리불가:비정상적인접근]");
			log_dscrp.append("[처리불가:비정상적인접근]");
			data.put("error", "1");
			data.put("msg", "비정상적인 접근입니다.");
		} else {
			
			//교육과정정보
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			log_dscrp.append("["+eduCurriculumVO.getCRS_NM()+","+eduCurriculumVO.getCAT_INS_NM()+"("+eduCurriculumVO.getCRS_STR_DT()+")]");
			
			String parameter_chkedHMbrIds = request.getParameter("chkedHMbrIds");
			String[] chkedHMbrIds = parameter_chkedHMbrIds.replaceAll("\\s","").split(",");
			for(String hmbr_sn : chkedHMbrIds) {
				if(hmbr_sn==null) {
					log_dscrp.append("[비정상적인데이터(일련번호:"+hmbr_sn+")-처리실패]");
					log_msg.append("[비정상적인데이터:HMBR_SN="+hmbr_sn+"]");
					continue;					
				}
				//검증
				EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
				newInstanceItem.setCRS_SN(eduMyHistoryVO.getCRS_SN());
				newInstanceItem.setHMBR_SN(hmbr_sn);
				//newInstanceItem.setUSE_ST("1");
				//newInstanceItem.setDEL_ST("0");
				EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);
				if(chkEduMyHistoryVO.getHMBR_SN()==null || chkEduMyHistoryVO.getHMBR_SN().length()==0) {
					log_dscrp.append("[정보없음(일련번호:"+hmbr_sn+")]");
					log_msg.append("[정보없음:HMBR_SN="+hmbr_sn+"]");
					continue;
				} else {					
					//회원정보
					EduMemberVO eduMemberVO = new EduMemberVO();
					eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
					eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
					if(eduMemberVO == null || eduMemberVO.getMBR_NM()==null || eduMemberVO.getMBR_NM().length()==0) {
						log_dscrp.append("[존재하지않는회원(교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");
					} else {
						log_dscrp.append("[이름:"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+",교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");	
					}
					
					//이수상태 변경
					if( chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("0") ) {
						//변경사항 없음.
						LOGGER.debug("이미 이수취소된상태 - 변경사항없음");
						log_dscrp.append("-이미이수취소상태");
						log_msg.append("|HMBR_SN="+hmbr_sn+":이미이수취소된회원");
					} else {
						//변동사항 있음.
						LOGGER.debug("이수취소처리 시작 - 변경사항있음");
						log_dscrp.append("|HMBR_SN="+hmbr_sn+"|이수취소완료");
						tbl_inf.append("EDU_MBR_HSTRY_TB,");
						tbl_sn.append(newInstanceItem.getHMBR_SN()+",");
						newInstanceItem.setLRNNG_CMPLT_ST("0");
						newInstanceItem.setLRNNG_ST("0");
						newInstanceItem.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						newInstanceItem.setUPD_MBR_ID(loginVO.getMBR_ID());
						//newInstanceItem.setHMBR_SV_ST("0");	//설문지 참여도 취소
						eduMyHistoryService.set_edu_mbrhstry_mod(newInstanceItem);
						
						if(chkEduMyHistoryVO.getDEL_ST().equals("1")) {
							log_dscrp.append("-교육과정에서삭제된회원이므로카운트감소안함");
							log_msg.append("|교육과정-삭제된회원이므로카운트감소안함");
						} else {
							//교육과정정보카운트다운
							//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
							//eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_down(eduCurriculumVO);
							log_dscrp.append("-교육과정카운트감소");
							log_msg.append("|교육과정-카운트감소");
						}
						
						//년도별이수내역에 이수처리 취소 반영
						EduMemberVO updEduMemberVO = new EduMemberVO();
						updEduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						updEduMemberVO.setTRGT_YEAR(eduMyHistoryVO.getSearchYear());
						updEduMemberVO.setCMPLT_ST("0");
						updEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
						updEduMemberVO.setCRS_SN_NOT_NULL("Y");//CRS_SN 일때
						updEduMemberVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
						updEduMemberVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
						eduMemberService.set_edu_member_target_mod(updEduMemberVO);
						log_dscrp.append("-년도별이수내역에이수처리취소반영");
						log_msg.append("|년도별이수내역에이수처리취소반영");
						
						//기존 이수증 삭제 처리
						EduCertificateVO eduCertificateVO = new EduCertificateVO();
						eduCertificateVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
						eduCertificateVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
						eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
						if(existInfo == null || existInfo.getCRTF_SN() == null || existInfo.getCRTF_SN().length() == 0) {
							LOGGER.debug("보유한 이수증이 없음");
							log_dscrp.append("-발급된이수증이없음");
							log_msg.append("|발급된이수증이없음");
						} else {
							//취소 처리로 변환
							existInfo.setUSE_ST("0");
							existInfo.setDEL_ST("1");
							eduCertificateService.set_edu_certificate_mod(existInfo);
							LOGGER.debug("기존 이수증 취소 : " + existInfo.getCRTF_CD());
							log_dscrp.append("-이수증발급취소");
							log_msg.append("[취소된이수증코드:"+existInfo.getCRTF_CD()+"]");
							/*
							eduCertificateService.remove_edu_certificate(existInfo);
							eduCertificateService.remove_edu_certificate_dtl(existInfo);
							LOGGER.debug("기존 이수증 삭제(하위포함) : " + existInfo.getCRTF_CD());
							log_dscrp.append("|기존 이수증 삭제(하위포함)" + existInfo.getCRTF_CD());		
							log_msg.append("[삭제된이수증코드:"+existInfo.getCRTF_CD()+"]");
							*/
						}
						
						//사용자사유로그남기기
						{
							EduMyHistoryVO realEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(chkEduMyHistoryVO);
							logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB","일괄가이수취소처리(이수증발급취소)",eduMyHistoryVO.getLOG_UPD_MSG(),chkEduMyHistoryVO.getMBR_ID(),chkEduMyHistoryVO.getMBR_NM(),chkEduMyHistoryVO,realEduMyHistoryVO,loginVO,request);
						}
						
					}	
					log_dscrp.append("]");
				}
			}
			data.put("error", "0");
			data.put("msg", "이수취소처리가 완료되었습니다.");	
			
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
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
	//관리자(교육센터) 수강내역(회원) 선택회원일괄승인처리 수정 로직 ------------------------------------------------
	@RequestMapping(value="/eduadm/mbrhstry/modify_all_st_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_mbrhstry_modify_all_st_act(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		PublicUtils mPublicUtils = new PublicUtils();
		
		StringBuilder log_msg = new StringBuilder();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-수강내역-회원수정-선택회원일괄승인처리]");
		//검증
		if(eduMyHistoryVO.getCRS_SN()==null || eduMyHistoryVO.getCRS_SN().length()==0) {
			log_msg.append("[처리불가:비정상적인접근]");
			log_dscrp.append("[처리불가:비정상적인접근]");
			data.put("error", "1");
			data.put("msg", "비정상적인 접근입니다.");
		} else {
			
			int totcnt = 0;
			int successCnt = 0;
			int successOverlapCnt = 0;
			int failCnt = 0;
			int successCertificateCnt = 0;
			int failCertificateCnt = 0;
			String failMbrList = "";
			String failCertificateMbrList = "";
					
			//교육과정정보
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			log_dscrp.append("["+eduCurriculumVO.getCRS_NM()+","+eduCurriculumVO.getCAT_INS_NM()+"("+eduCurriculumVO.getCRS_STR_DT()+")]");
			
			String parameter_chkedHMbrIds = request.getParameter("chkedHMbrIds");
			String[] chkedHMbrIds = parameter_chkedHMbrIds.replaceAll("\\s","").split(",");
			for(String hmbr_sn : chkedHMbrIds) {
				if(hmbr_sn==null) {
					log_dscrp.append("[비정상적인데이터(일련번호:"+hmbr_sn+")-처리실패]");
					log_msg.append("[비정상적인데이터:HMBR_SN="+hmbr_sn+"]");
					failCnt++;
					continue;					
				}
				//검증
				EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
				newInstanceItem.setCRS_SN(eduMyHistoryVO.getCRS_SN());
				newInstanceItem.setHMBR_SN(hmbr_sn);
				newInstanceItem.setLRNNG_ST("1");
				newInstanceItem.setUSE_ST("1");
				newInstanceItem.setDEL_ST("0");
				EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);
				if(chkEduMyHistoryVO.getHMBR_SN()==null || chkEduMyHistoryVO.getHMBR_SN().length()==0) {
					log_dscrp.append("[정보없음(일련번호:"+hmbr_sn+")]");
					log_msg.append("[정보없음:HMBR_SN="+hmbr_sn+"]");
					failCnt++;
					continue;
				} else {
					
					//회원정보
					EduMemberVO eduMemberVO = new EduMemberVO();
					eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
					eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
					if(eduMemberVO == null || eduMemberVO.getMBR_NM()==null || eduMemberVO.getMBR_NM().length()==0) {
						log_dscrp.append("[존재하지않는회원(교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");
						failCnt++;
					} else {
						log_dscrp.append("[이름:"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+",교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");	
					
						//이수상태 변경
						if( chkEduMyHistoryVO.getLRNNG_ST().equals("1") && chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1") ) {
							//변경사항 없음.
							LOGGER.debug("이미 승인완료상태 - 변경사항없음");
							log_dscrp.append("-이미승인완료된상태");
							log_msg.append("|HMBR_SN="+hmbr_sn+":이미승인완료회원");
							successOverlapCnt++;
						} else {
							//변동사항 있음.
							LOGGER.debug("승인완료처리 시작 - 변경사항있음");
							log_dscrp.append("-승인완료처리");
							log_msg.append("|HMBR_SN="+hmbr_sn+"|승인완료처리");
							tbl_inf.append("EDU_MBR_HSTRY_TB,");
							tbl_sn.append(newInstanceItem.getHMBR_SN()+",");
							newInstanceItem.setLRNNG_ST("1");
							newInstanceItem.setLRNNG_CMPLT_ST("1");
							newInstanceItem.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							newInstanceItem.setUPD_MBR_ID(loginVO.getMBR_ID());
							newInstanceItem.setHMBR_SV_ST(chkEduMyHistoryVO.getHMBR_SV_ST());
							eduMyHistoryService.set_edu_mbrhstry_mod(newInstanceItem);
							
							if(!chkEduMyHistoryVO.getUSE_ST().equals("1") || !chkEduMyHistoryVO.getDEL_ST().equals("0")) {
								//수강신청인원으로 업데이트
								//커리큘럼 메인 갱신
								EduCurriculumVO updateEduCurriculumVO = new EduCurriculumVO();
								updateEduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
								eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(updateEduCurriculumVO);
								log_dscrp.append("-교육과정카운트반영");
								log_msg.append("|교육과정에카운트반영");
							}
							EduCurriculumVO updateEduCurriculumVO = new EduCurriculumVO();
							updateEduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_up(updateEduCurriculumVO);
							log_dscrp.append("-교육과정에이수완료카운트반영(");
							log_msg.append("|교육과정에이수완료카운트반영");
							
							//사용자사유로그남기기
							{
								EduMyHistoryVO realEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(chkEduMyHistoryVO);
								logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB","일괄승인처리(이수증외부출력가능)",eduMyHistoryVO.getLOG_UPD_MSG(),chkEduMyHistoryVO.getMBR_ID(),chkEduMyHistoryVO.getMBR_NM(),chkEduMyHistoryVO,realEduMyHistoryVO,loginVO,request);
							}
						
							successCnt++;
						}	
											
						try {
							//이수증 발급 진행
							EduCertificateVO eduCertificateVO = new EduCertificateVO();
							eduCertificateVO.setCRS_SN(newInstanceItem.getCRS_SN());
							eduCertificateVO.setHMBR_SN(newInstanceItem.getHMBR_SN());
							eduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							EduCertificateVO existInfo = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
							if(existInfo == null || existInfo.getCRTF_SN() == null || existInfo.getCRTF_SN().length() == 0) {	
								if(eduMemberVO != null && eduMemberVO.getMBR_NM()!=null && eduMemberVO.getMBR_NM().length()!=0) {
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
									chkEduMyHistoryVO.setTMPR_LRNNG_CMPLT_DT(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
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
										log_dscrp.append("-새로운이수증발급");
										log_msg.append("|새로운이수증발급");
									} catch(Exception e) {
										e.printStackTrace();
										log_dscrp.append("-새로운이수증발급실패");
										log_msg.append("새로운이수증발급시오류발생("+e.toString()+")");
									}
									LOGGER.debug("(HMBR_SN="+hmbr_sn+")새로운 이수증 발급 : " + insertId);
									log_msg.append("|(HMBR_SN="+hmbr_sn+")새로운 이수증 발급 : " + insertId);
								} else {
									log_dscrp.append("-새로운이수증발급실패");
									log_msg.append("|회원정보가없어새로운이수증발급실패");
								}
							} else {
								//이수증 발급 재시작
								eduCertificateService.set_edu_certificate_mod_use_unlock(existInfo);
								LOGGER.debug("(HMBR_SN="+hmbr_sn+")이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
								log_dscrp.append("-이미발급된이수증(재발급재시작)");
								log_msg.append("|이미 발급된 이수증(발급재시작) : " + existInfo.getCRTF_SN());
							}
							
							//카카오 알림톡 발송
							//카카오 알림톡 발송
							String[] parseAddr = eduMemberVO.getMBR_ADDR1().split(" ");
							//이수증발급정보 재호출
							EduCertificateVO reEduCertificateVO = new EduCertificateVO();
							reEduCertificateVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							reEduCertificateVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
							reEduCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							EduCertificateVO reEduExistInfo = eduCertificateService.get_edu_certificate_info(reEduCertificateVO);
							
//							String eduStrDt = eduCurriculumVO.getCRS_STR_DT(); 
//							String eduEndDt = eduCurriculumVO.getCRS_END_DT(); 
//							//카카오 알림톡 발송
//							KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
//							kakaoAlimTalkVO.setTelNum(eduMemberVO.getMBR_HP());//연락처
//							//(알림톡필수)
//							String complEdycIssuNo = reEduExistInfo.getCRTF_CD();
//							kakaoAlimTalkVO.setComplEdycIssuNo(complEdycIssuNo);//이수증발급번호
//							kakaoAlimTalkVO.setCmpletNm(eduMemberVO.getMBR_NM());//성명
//							kakaoAlimTalkVO.setCmpletBrdt(eduMemberVO.getMBR_BIRTH());//생년월일
//							kakaoAlimTalkVO.setCmpletAdres(eduMemberVO.getMBR_ADDR1());//주소
//							kakaoAlimTalkVO.setEduNm(reEduExistInfo.getCRS_NM()	);
//							if(eduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")){ // 신규재개자 전문교육
//								kakaoAlimTalkVO.setEduStrDt(eduStrDt.replace(".0", ""));//교육일시
//								kakaoAlimTalkVO.setEduEndDt(eduEndDt.replace(".0", ""));//교육일시
//								kakaoAlimTalkVO.setTemplateId("KKO_000007");//이수증발급
//							} else {
//								kakaoAlimTalkVO.setEduYmd(eduStrDt.replace(".0", "") + " ~ " + eduEndDt.replace(".0", ""));//교육일시
//								if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010300")){ // 낚시어선 전문교육
//									kakaoAlimTalkVO.setTemplateId("KKO_000005");//이수증발급
//								} else if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")){ // 낚시터 전문교육
//									kakaoAlimTalkVO.setTemplateId("KKO_000006");//이수증발급
//								}
//							}
//							
//							kakaoAlimTalkVO.setAsync(true);//동기화 전송 여부(true:비동기,false:동기[기본값])
//							
//							JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
							
							successCertificateCnt++;
						
						} catch(Exception e) {
							e.printStackTrace();
							log_dscrp.append("-새로운이수증발급실패(이수증1개이상)");
							log_msg.append("새로운이수증발급시오류발생("+e.toString()+")");
							failCertificateMbrList += eduMemberVO.getMBR_NM() + ",";
							failCertificateCnt++;
						}
						
					}
					log_dscrp.append("]");
				}
				totcnt++;
			}
		
			String returnMsg = "총 "+totcnt+" 건 중에서 "
					+ "이수완료(성공 : "+successCnt+" 건 , 중복 : "+successOverlapCnt+" 건 , 실패 : "+failCnt+" 건) "
					+ "이수증발급(성공 : "+successCertificateCnt+" 건 , 실패 : "+failCertificateCnt+" 건) "
					+ "이수증발급실패회원목록("+failCertificateMbrList+") "
					+ "의 수강내역 정보가 수정되었습니다.";
			
			data.put("error", "0");
			data.put("msg", returnMsg);	
			
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
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
	
	//관리자(교육센터) 수강내역(비회원) 일괄삭제 ------------------------------------------------
		@RequestMapping(value="/eduadm/mbrhstry/all_clear_act.do", method = RequestMethod.POST)
		public @ResponseBody String ajax_edu_mbrhstry_all_delete(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
				HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			PublicUtils mPublicUtils = new PublicUtils();
			JSONObject data = new JSONObject();	
			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder log_msg = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();
			log_dscrp.append("[교육센터관리자-수강내역-비회원 일괄삭제]");
			
			int totCnt = 0;
			int failCnt = 0;
			int successCnt = 0;
			
			try {
				// 교육과정정보
				EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
				eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
				eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
				log_dscrp.append("[" + eduCurriculumVO.getCRS_NM() + "," + eduCurriculumVO.getCAT_INS_NM() + "(" + eduCurriculumVO.getCRS_STR_DT() + ")]");
				
				String LOG_UPD_MSG = eduMyHistoryVO.getLOG_UPD_MSG();
				eduMyHistoryVO.setNotUsedPagination(true);
				List<EduMyHistoryVO> mbrHstryList = eduMyHistoryService.get_edu_mbrhstry_list(eduMyHistoryVO);
				for (EduMyHistoryVO delItem : mbrHstryList) {
					delItem.setCRS_SN(eduMyHistoryVO.getCRS_SN());
					
					String del_mbr_nm = delItem.getMBR_NM();
					if (del_mbr_nm == null || del_mbr_nm.length()==0) {
						try {
							delItem.setHMBR_SN(delItem.getHMBR_SN());
							delItem.setMBR_ID(delItem.getMBR_ID());
							eduMyHistoryService.remove_edu_mbrhstry(delItem);
							log_dscrp.append("|(성공)"+delItem.getMBR_ID());
							log_msg.append("|(성공)"+delItem.getMBR_ID());
								
							if (delItem.getLRNNG_ST().equals("0") || delItem.getLRNNG_ST().equals("1")) {
								eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(eduCurriculumVO);
								log_msg.append("-수강신청인원감소처리");
							}
							if (delItem.getLRNNG_CMPLT_ST().equals("1")) {
								eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_down(eduCurriculumVO);
								log_msg.append("-이수완료인원감소처리");
							}
							// 사용자사유로그남기기
							{
								String crs_dt_str = mPublicUtils.changePatternString(eduCurriculumVO.getCRS_STR_DT().replace(".0",""), "yyyy-MM-dd HH:mm:ss", "yyyy.MM.dd");
								String crs_dt_time_str = mPublicUtils.changePatternString(eduCurriculumVO.getCRS_STR_DT().replace(".0",""), "yyyy-MM-dd HH:mm:ss", "HH:mm");
								String crs_dt_time_end = mPublicUtils.changePatternString(eduCurriculumVO.getCRS_END_DT().replace(".0",""), "yyyy-MM-dd HH:mm:ss", "HH:mm");

								logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB", "비회원 일괄삭제["+delItem.getCAT_INS_NM()+"]"+eduCurriculumVO.getCRS_GRP_NM()+" "+delItem.getCRS_NM()+" (교육정보 "+crs_dt_str+" "+ crs_dt_time_str + "~" + crs_dt_time_end+" , "+eduCurriculumVO.getCRS_PLACE()+")"
										, LOG_UPD_MSG, delItem.getMBR_ID(), delItem.getMBR_NM(), delItem, null, loginVO, request);
							}
							successCnt++;							
						} catch(Exception e) {	
							log_dscrp.append("|(실패)"+delItem.getMBR_ID());
							log_msg.append("|(실패)"+delItem.getMBR_ID());
							failCnt++;
						}
					}	
					totCnt++;
				}
				//상세내역 삭제
				eduMyHistoryVO.setNotUsedPagination(true);
				eduMyHistoryVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
				List<EduMyHistoryVO> delDtlList = eduMyHistoryService.get_edu_mbrhstry_dtl_list(eduMyHistoryVO);
				for(EduMyHistoryVO dtlItem : delDtlList){
					EduMyHistoryVO dtlEduMyHistoryVO = dtlItem;
					dtlEduMyHistoryVO.setHMBR_DTL_SN(dtlItem.getHMBR_DTL_SN());
					if(dtlEduMyHistoryVO.getMBR_NM() == null || dtlEduMyHistoryVO.getMBR_NM().length()==0 ){
						eduMyHistoryService.remove_edu_mbrhstry_dtl(dtlEduMyHistoryVO);	
						log_dscrp.append("|상세내역삭제");
						log_msg.append("-상세내역삭제");
					}
				}
						
				tbl_inf.append("EDU_MBR_HSTRY_TB,");
				tbl_sn.append(eduMyHistoryVO.getHMBR_SN() + ",");
	
				data.put("error", "0");
				data.put("msg", "총 "+totCnt+" 건 중에서 성공:"+successCnt+"건, 실패:"+failCnt+"건의 비회원 정보가 처리 되었습니다.");
				
			} catch(Exception e) {
				log_dscrp.append("[처리실패]");
				log_msg.append("[에러발생("+e.toString()+")]");
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
				LOGGER.debug(e.toString());				
			}
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMyHistoryVO));
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
	
	//관리자(교육센터) 수강내역(회원) 삭제 ------------------------------------------------
	@RequestMapping(value="/eduadm/mbrhstry/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_mbrhstry_delete(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		EduMyHistoryVO chkEduMyHistoryVO = new EduMyHistoryVO();
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		
		JSONObject data = new JSONObject();	
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-수강내역-회원삭제");
		try {
			//검증
			chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
			if(chkEduMyHistoryVO.getHMBR_SN()==null || chkEduMyHistoryVO.getHMBR_SN().length()==0) {
				log_dscrp.append("[정보없음(일련번호:"+chkEduMyHistoryVO.getHMBR_SN()+")]");
				log_msg.append("[정보없음:HMBR_SN="+chkEduMyHistoryVO.getHMBR_SN()+"]");
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {				
						
				String USE_ST = chkEduMyHistoryVO.getUSE_ST();
				String DEL_ST = chkEduMyHistoryVO.getDEL_ST();

				String LOG_UPD_MSG = eduMyHistoryVO.getLOG_UPD_MSG();
				//사유 입력으로 바로 삭제 처리
				DEL_ST = "1";
				//
				
				if(DEL_ST.equals("1")) {
					log_dscrp.append("-실제데이터삭제]");
				} else {
					log_dscrp.append("-회원삭제]");
				}
				
				//교육과정정보
				EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
				eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
				eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
				log_dscrp.append("["+eduCurriculumVO.getCRS_NM()+","+eduCurriculumVO.getCAT_INS_NM()+"("+eduCurriculumVO.getCRS_STR_DT()+")]");
				

				//회원정보
				EduMemberVO eduMemberVO = new EduMemberVO();
				eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
				eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
				if(eduMemberVO == null || eduMemberVO.getMBR_NM()==null || eduMemberVO.getMBR_NM().length()==0) {
					log_dscrp.append("[존재하지않는회원(교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");
				} else {
					log_dscrp.append("[이름:"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+",교육번호:"+chkEduMyHistoryVO.getHMBR_SN()+")");	
				}
								
				log_msg.append("[MBR_ID:"+chkEduMyHistoryVO.getMBR_ID());
				log_msg.append(",CRS_SN:"+chkEduMyHistoryVO.getCRS_SN());
				log_msg.append(",HMBR_SN:"+chkEduMyHistoryVO.getHMBR_SN());
				
				//사유 입력으로 바로 삭제 처리를 위한 구간, DEL_ST = "1"; 주석시 이 부분도 같이 주석되어야 함.
				if(USE_ST.equals("1")) {					
					//삭제 단계 프리패스로 인한 커리큘럼 정보 업데이트
					//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
					//eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
					eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(eduCurriculumVO);
					log_dscrp.append("-수강신청인원감소처리");
					log_msg.append("|수강신청인원감소처리");			
					if(chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) {
						eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_down(eduCurriculumVO);
						log_dscrp.append("-이수완료인원감소처리");
						log_msg.append("|이수완료인원감소처리");
					}
				}
				//
				
				
				//메인
				eduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				if(DEL_ST.equals("1")) {					
					//교육과정정보
					//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
					//eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
					eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
					/*
					if(eduCurriculumVO.getCRS_TYPE().equals("default_online")) {
						//온라인 필수 교육 내역에서 삭제시 내정보 업데이트 
						EduMemberVO eduMemberVO = new EduMemberVO();
						eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						eduMemberVO.setMBR_HMBR_ONLINE_SN("(DEL)"+chkEduMyHistoryVO.getHMBR_SN());
						eduMemberVO.setMBR_HMBR_ONLINE_ST("N");
						eduMemberService.set_edu_member_mod(eduMemberVO);
						log_dscrp.append("|내정보온라인필수교육정보삭제");
					}
					*/
					//
					try {
						//연도별이수내역에서 제거
						String TRGT_YEAR = mPublicUtils.changePatternString(eduCurriculumVO.getCRS_STR_DT().replace(".0",""), "yyyy-MM-dd HH:mm:ss", "yyyy");
						log_dscrp.append("-"+TRGT_YEAR+"연도별이수내역제거");
						log_msg.append("|"+TRGT_YEAR+"연도별이수내역제거");
						EduMemberVO delEduMemberVO = new EduMemberVO();
						delEduMemberVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
						//delEduMemberVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
						delEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
						delEduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						eduMemberService.remove_edu_member_target(delEduMemberVO);
						
						EduMemberVO chkEduMemberVO = new EduMemberVO();
						chkEduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
						chkEduMemberVO.setTRGT_YEAR(eduMyHistoryVO.getSearchYear());
						List<EduMemberVO> chkTargetEduBeanList = eduMemberService.get_edu_member_target_all_list(chkEduMemberVO);
						if(chkTargetEduBeanList == null || chkTargetEduBeanList.size() == 0) {
							log_dscrp.append("-빈연도별이수내역생성");
							log_msg.append("|빈연도별이수내역생성");
							chkEduMemberVO.setMBR_CD(chkEduMyHistoryVO.getMBR_ID());
							chkEduMemberVO.setTRGT_YEAR(eduMyHistoryVO.getSearchYear());
							chkEduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
							chkEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
							eduMemberService.set_edu_member_target_reg(chkEduMemberVO);
						}
						
					} catch(Exception e) {
						LOGGER.debug("[fail][연도별이수내역에서 제거] "+e.toString());
					}
					//
					eduMyHistoryService.remove_edu_mbrhstry(eduMyHistoryVO);	
										
				} else {
					eduMyHistoryService.del_edu_mbrhstry(eduMyHistoryVO);
					//EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
					//eduCurriculumVO.setCRS_SN(eduMyHistoryVO.getCRS_SN());
					eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_down(eduCurriculumVO);
					log_dscrp.append("-수강신청인원감소처리");
					log_msg.append("|수강신청인원감소처리");
					if(chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) {
						eduCurriculumService.set_edu_curriculum_mbr_cmplt_cnt_down(eduCurriculumVO);
						log_dscrp.append("-이수완료인원감소처리");
						log_msg.append("|이수완료인원감소처리");
					}
				}
				
				//사용자사유로그남기기
				{
					EduMyHistoryVO realEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
					logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB","제거",LOG_UPD_MSG,chkEduMyHistoryVO.getMBR_ID(),chkEduMyHistoryVO.getMBR_NM(),chkEduMyHistoryVO,realEduMyHistoryVO,loginVO,request);
				}
				log_dscrp.append("]");
				
				tbl_inf.append("EDU_MBR_HSTRY_TB,");
				tbl_sn.append(eduMyHistoryVO.getHMBR_SN()+",");
				
				//상세
				eduMyHistoryVO.setNotUsedPagination(true);
				List<EduMyHistoryVO> clildList = eduMyHistoryService.get_edu_mbrhstry_dtl_list(eduMyHistoryVO);
				for(EduMyHistoryVO item : clildList) {
					EduMyHistoryVO cloneEduMyHistoryVO = item;
					cloneEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					if(DEL_ST.equals("1")) {
						eduMyHistoryService.remove_edu_mbrhstry_dtl(cloneEduMyHistoryVO);
					} else {
						eduMyHistoryService.del_edu_mbrhstry_dtl(cloneEduMyHistoryVO);
					}
					
					tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
					tbl_sn.append(eduMyHistoryVO.getHMBR_DTL_SN()+",");
				}
				
				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");
				
				
			}
		} catch(Exception e) {
			log_dscrp.append("[처리실패]");
			log_msg.append("[에러발생("+e.toString()+")]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			LOGGER.debug(e.toString());
			
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduMyHistoryVO));
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

	
	//회원조회 ------------------------------------------------
	@RequestMapping(value = "/eduadm/util/member/list.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_member_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		
		eduMemberVO.setPageUnit(10);//한번에 10명만 노출
		
		List<EduMemberVO> list = null;
		String returnUrl = "eduadm/mbrhstry/ajax_member_list";
		
		//이미 리스트에 추가되어 있는 아이디
		String addedMbrIds = request.getParameter("addedMbrIds");
		if(addedMbrIds!=null) {
			eduMemberVO.setMBR_ADDED_IDS(addedMbrIds.replaceAll("\\s","").split(","));
		}
		
		//추가한 아이디
		if(eduMemberVO.getMBR_ID()!=null) {
			eduMemberVO.setMBR_IDS(eduMemberVO.getMBR_ID().replaceAll("\\s","").split(","));
		}
				
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduMemberVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduMemberVO.getPageUnit());
		paginationInfo.setPageSize(eduMemberVO.getPageSize());

		eduMemberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduMemberVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduMemberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
					
		eduMemberVO.setMBR_ST("Y");
		list = eduMyHistoryService.get_edu_mbr_list_only_mbrhstry(eduMemberVO);			
		int totCnt = eduMyHistoryService.get_edu_mbr_list_only_mbrhstry_totcnt(eduMemberVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName(returnUrl);
		mModelAndView.addObject("paginationInfo", paginationInfo);
		mModelAndView.addObject("list",list);
		mModelAndView.addObject("mbrids",eduMemberVO.getMBR_ID());
		return mModelAndView;
	}
	
	
	
	//관리자(교육센터) 수강내역(회원) 상세 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/mbrhstry/listDtl.do")
	public String edu_history_listDtl(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO, 
		@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		
		List<EduMyHistoryVO> list = null;
		//eduMyHistoryVO.setPageUnit(2);
		try {
			/** pageing setting */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(eduMyHistoryVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(eduMyHistoryVO.getPageUnit());
			paginationInfo.setPageSize(eduMyHistoryVO.getPageSize());

			eduMyHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			eduMyHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
			eduMyHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			eduMyHistoryVO.setNotUsedPagination(true);
			list = eduMyHistoryService.get_edu_mbrhstry_dtl_list(eduMyHistoryVO);
			
			int totCnt = eduMyHistoryService.get_edu_mbrhstry_dtl_list_totcnt(eduMyHistoryVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
		} catch(Exception e) {
			
			//상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		//교육과정 정보
		EduCurriculumVO parentInfo = null;
		try {
			eduCurriculumVO.setTypeStr("");
			parentInfo = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
		} catch(Exception e) { 
			LOGGER.debug("[fail load data] "+e.toString());
		}
		if(parentInfo==null) {
			//상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		//수강내역(메인) 정보
		EduMyHistoryVO parentInfo2 = null;
		try {
			eduCurriculumVO.setTypeStr("");
			parentInfo2 = eduMyHistoryService.get_edu_mbrhstry_info(eduMyHistoryVO);
		} catch(Exception e) { 
			LOGGER.debug("[fail load data] "+e.toString());
		}
		if(parentInfo==null) {
			//상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_trgt_cd",list_mbr_cd);
		}

		model.addAttribute("parentInfo",parentInfo);
		model.addAttribute("parentInfo2",parentInfo2);
		model.addAttribute("list",list);
		return "eduadm/mbrhstry/list_dtl";
	}
	//관리자(교육센터) 수강내역(회원) 상세 글수정 ------------------------------------------------
	@RequestMapping(value = "/eduadm/mbrhstry/modifyDtl.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_mbrhstry_modifyDtl(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO, @ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		EduMyHistoryVO info = null;
		if(eduMyHistoryVO.getHMBR_SN()!=null && eduMyHistoryVO.getHMBR_SN().length()!=0) {
			info = eduMyHistoryService.get_edu_mbrhstry_info_dtl(eduMyHistoryVO);
		}	
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/mbrhstry/modify_dtl");
		mModelAndView.addObject("info",info);
		return mModelAndView;
	}
	//관리자(교육센터) 수강내역(회원) 상세 수정 ------------------------------------------------
	@RequestMapping(value="/eduadm/mbrhstry/modifyDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_mbrhstry_modifyDtl_act(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-수강내역-하위-교과목수정]");
		
		//검증
		EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info_dtl(eduMyHistoryVO);
		if(chkEduMyHistoryVO.getHMBR_DTL_SN()==null || chkEduMyHistoryVO.getHMBR_DTL_SN().length()==0) {
			log_msg.append("[처리불가:존재하지않는정보(교육상세번호:"+eduMyHistoryVO.getHMBR_DTL_SN()+")]");
			log_dscrp.append("[처리불가:존재하지않는정보(교육상세번호:"+eduMyHistoryVO.getHMBR_DTL_SN()+")]");
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");
		} else {
			try {
				
				//회원정보
				EduMemberVO eduMemberVO = new EduMemberVO();
				eduMemberVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
				eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
				if(eduMemberVO == null || eduMemberVO.getMBR_NM()==null || eduMemberVO.getMBR_NM().length()==0) {
					log_dscrp.append("[존재하지않는회원(교육상세번호:"+chkEduMyHistoryVO.getHMBR_DTL_SN()+")]");
				} else {
					log_dscrp.append("[이름:"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+",교육상세번호:"+chkEduMyHistoryVO.getHMBR_SN()+")]");	
				}
				
				boolean isUpdateLrnngTime = false;
				boolean isUpdateLrnngPoint = false;
				boolean isUpdateLrnng = false;
				//동영상 점수 반영
				if( chkEduMyHistoryVO.getTRN_CUR_TIME().equals(eduMyHistoryVO.getTRN_CUR_TIME()) ) {
					//변경사항 없음.
				} else { 
					//변동사항 있음.
					if(eduMyHistoryVO.getTRN_CUR_TIME()!=null && eduMyHistoryVO.getTRN_CUR_TIME().length()!=0) {
						float TRN_CUR_TIME = Float.parseFloat(eduMyHistoryVO.getTRN_CUR_TIME());
						float TRN_MAX_TIME = Float.parseFloat(chkEduMyHistoryVO.getTRN_MAX_TIME());
						float temp_calc_time = TRN_CUR_TIME/TRN_MAX_TIME;
						eduMyHistoryVO.setLRNNG_PRGRS(String.valueOf(temp_calc_time));
						//점수배분
						if(temp_calc_time >= 1.0f) {
							eduMyHistoryVO.setLRNNG_CMPLT_DTL_ST("1");
							eduMyHistoryVO.setLRNNG_CUR_TIME(chkEduMyHistoryVO.getLRNNG_MAX_TIME());
							eduMyHistoryVO.setLRNNG_CUR_POINT(chkEduMyHistoryVO.getLRNNG_MAX_POINT());
						} else if(temp_calc_time >= 0.8f) {
							eduMyHistoryVO.setLRNNG_CUR_TIME(String.valueOf(Float.parseFloat(chkEduMyHistoryVO.getLRNNG_MAX_TIME())*0.8f));
							eduMyHistoryVO.setLRNNG_CUR_POINT(String.valueOf(Float.parseFloat(chkEduMyHistoryVO.getLRNNG_MAX_POINT())*0.8f));
						} else if(temp_calc_time >= 0.5f) {
							eduMyHistoryVO.setLRNNG_CUR_TIME(String.valueOf(Float.parseFloat(chkEduMyHistoryVO.getLRNNG_MAX_TIME())*0.5f));
							eduMyHistoryVO.setLRNNG_CUR_POINT(String.valueOf(Float.parseFloat(chkEduMyHistoryVO.getLRNNG_MAX_POINT())*0.5f));
						} else {
							eduMyHistoryVO.setLRNNG_CUR_TIME("0");
							eduMyHistoryVO.setLRNNG_CUR_POINT("0");
						}
						isUpdateLrnng = true;
					}
					log_dscrp.append("[동영상점수변동사항있음]");
				}		
				//이수교육시간 변경시
				if( chkEduMyHistoryVO.getLRNNG_CUR_TIME().equals(eduMyHistoryVO.getLRNNG_CUR_TIME()) ) {
					//변경사항 없음.
				} else { 
					//변동사항 있음.
					if(eduMyHistoryVO.getLRNNG_CUR_TIME()!=null && eduMyHistoryVO.getLRNNG_CUR_TIME().length()!=0) {
						isUpdateLrnngTime = true;
						isUpdateLrnng = true;
						log_dscrp.append("[이수교육시간변동사항있음]");
					}
				}
				//이수교육점수 변경시
				if( chkEduMyHistoryVO.getLRNNG_CUR_POINT().equals(eduMyHistoryVO.getLRNNG_CUR_POINT()) ) {
					//변경사항 없음.
				} else { 
					//변동사항 있음.
					if(eduMyHistoryVO.getLRNNG_CUR_POINT()!=null && eduMyHistoryVO.getLRNNG_CUR_POINT().length()!=0) {
						isUpdateLrnngPoint = true;
						isUpdateLrnng = true;
						log_dscrp.append("[이수교육점수변동사항있음]");
					}
				}
				//이수상태 변경
				if( chkEduMyHistoryVO.getLRNNG_CMPLT_DTL_ST().equals(eduMyHistoryVO.getLRNNG_CMPLT_DTL_ST()) ) {
					//변경사항 없음.
				} else {
					//변동사항 있음.
					EduMyHistoryVO updateEduMyHistoryVO = new EduMyHistoryVO();
					updateEduMyHistoryVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
					updateEduMyHistoryVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
					updateEduMyHistoryVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
					if(eduMyHistoryVO.getLRNNG_CMPLT_DTL_ST().equals("1")) { //대기 -> 완료 (직접변경하는 경우 최고점 처리)
						eduMyHistoryService.set_edu_mbrhstry_cur_cmplt_up(updateEduMyHistoryVO);
						/*
						if(isUpdateLrnngTime)
							eduMyHistoryVO.setLRNNG_CUR_TIME(chkEduMyHistoryVO.getLRNNG_MAX_TIME());
						if(isUpdateLrnngPoint)
							eduMyHistoryVO.setLRNNG_CUR_POINT(chkEduMyHistoryVO.getLRNNG_MAX_POINT());
						*/
						log_dscrp.append("[이수상태:대기 -> 완료 (직접변경하는 경우 최고점 처리)]");
					} else { //완료 -> 대기
						eduMyHistoryService.set_edu_mbrhstry_cur_cmplt_down(updateEduMyHistoryVO);
						log_dscrp.append("[이수상태:완료 -> 대기]");
					}
				}
				//기본
				eduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				eduMyHistoryService.set_edu_mbrhstry_mod_dtl(eduMyHistoryVO);
				
				tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
				tbl_sn.append(eduMyHistoryVO.getHMBR_DTL_SN()+",");
				
				//수강자정보 메인 업데이트 (점수)
				if(isUpdateLrnng) {
					float temp_calc_count = 0;
					float temp_calc_sum_time = 0;
					float temp_calc_sum_point = 0;
					float temp_calc_sum_percent = 0;
					EduMyHistoryVO researchEduMyHistoryVO = new EduMyHistoryVO();
					researchEduMyHistoryVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
					researchEduMyHistoryVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
					researchEduMyHistoryVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
					researchEduMyHistoryVO.setNotUsedPagination(true);
					List<EduMyHistoryVO> updChkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_dtl_list(researchEduMyHistoryVO);
					for(EduMyHistoryVO upditem : updChkEduMyHistoryVO) {
						temp_calc_sum_time += Float.parseFloat(upditem.getLRNNG_CUR_TIME());
						temp_calc_sum_point += Float.parseFloat(upditem.getLRNNG_CUR_POINT());
						temp_calc_sum_percent += Float.parseFloat(upditem.getLRNNG_PRGRS());
						temp_calc_count++;
					}
					EduMyHistoryVO updateEduMyHistoryVO = new EduMyHistoryVO();
					updateEduMyHistoryVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
					updateEduMyHistoryVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
					updateEduMyHistoryVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
					updateEduMyHistoryVO.setLRNNG_TOT_TIME(String.valueOf(temp_calc_sum_time));
					updateEduMyHistoryVO.setLRNNG_TOT_POINT(String.valueOf(temp_calc_sum_point));
					updateEduMyHistoryVO.setLRNNG_PRGRS(String.valueOf(temp_calc_sum_percent/temp_calc_count));
					updateEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					eduMyHistoryService.set_edu_mbrhstry_mod(updateEduMyHistoryVO);
					
					log_dscrp.append("[수강자정보 메인 업데이트 (점수)]");
				}
				//
				
				data.put("error", "0");
				data.put("msg", "수강내역 정보가 수정되었습니다.");				
															
			} catch(Exception e) {
				LOGGER.debug("[fail process] "+e.toString());
				log_msg.append("[에러발생:처리실패("+e.toString()+")]");
				log_dscrp.append("[처리실패]");
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMyHistoryVO));
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
		data.put("errCnt", "0");
		data.put("errField", "");
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	
	
	//관리자(교육센터) 사전 출석부 출력 (새창)보기페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/mbrhstry/attendancebook/view.do")
	public String edu_certificate_popwin_view(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO, 
			@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, 
			SessionStatus status, HttpServletRequest request, ModelMap model) throws Exception {
		String returnUrl = "";
		PublicUtils mPublicUtils = new PublicUtils();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		StringBuilder mbr_msg = new StringBuilder();
		mbr_msg.append(eduMyHistoryVO.getLOG_UPD_MSG());//출력사유
		
		log_dscrp.append("[교육센터관리자-사전출석부(출력)열람하기]");		
			
		String MASTER_MBR_ID = "";
		String MASTER_MBR_LV_ID = "";
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			log_dscrp.append("[접근거부:비정상적인접근으로거부됨]");
			returnUrl = "eduadm/error/page_400";
		} else {
			MASTER_MBR_ID = loginVO.getMBR_ID();
			MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
			
			try {
				log_dscrp.append("[");		
				
				//교육과정 정보
				eduCurriculumVO.setTypeStr("");
				EduCurriculumVO parentInfo = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
				if(parentInfo==null) {
					log_dscrp.append("|존재하지 않는 교육정보");
				} else {	
					
					log_dscrp.append("["+parentInfo.getCRS_NM()+","+parentInfo.getCAT_INS_NM()+"("+parentInfo.getCRS_STR_DT()+")]");
					
					eduMyHistoryVO.setNotUsedPagination(true);
					List<EduMyHistoryVO> list = eduMyHistoryService.get_edu_mbrhstry_list(eduMyHistoryVO);	
					
					String CRS_STR_DT = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "MM 월 dd 일");
					StringBuilder html_table_data = new StringBuilder();
					
					int ADD_TABLE_MAX_CNT = 15;
					int IDX = 0;
					int MERGE_CNT = 0;
					int MBR_CUR_CNT = 0;
					int MBR_CMPLT_CNT = 0;
					
					String html_format = mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/book/attendance.html").toString();
					
					StringBuilder document = new StringBuilder();
					document.append(html_format);
					
					StringBuilder document_data_table = new StringBuilder();
					
					int list_max_cnt = list.size();
					for(int i=0; i<list.size(); i++) {
						log_dscrp.append("[");
						EduMyHistoryVO item = (EduMyHistoryVO)list.get(i);//교육생정보
						//회원정보조회
						EduMemberVO eduMemberVO = new EduMemberVO();
						eduMemberVO.setMBR_ID(item.getMBR_ID());
						EduMemberVO member_info = eduMemberService.get_edu_member_info(eduMemberVO);
						if(member_info==null || member_info.getMBR_SN()==null || member_info.getMBR_SN().length()==0) {
							log_dscrp.append("존재하지않는회원정보(아이디:"+item.getMBR_ID()+"]");
							continue;
						}
						LOGGER.debug("처리시작="+item.getMBR_NM());
						log_dscrp.append(item.getMBR_NM()+"(아이디:"+item.getMBR_ID()+")");
						if(item.getLRNNG_ST().equals("0") || item.getLRNNG_ST().equals("1")) {//교육신청자
							LOGGER.debug("교육신청완료");
							MBR_CUR_CNT++;
						} else {
							continue;
						}
						boolean isLrnngCmplt = false;
						if(item.getLRNNG_CMPLT_ST().equals("1")) {//교육이수완료자
							LOGGER.debug("교육이수완료");
							isLrnngCmplt = true;
							MBR_CMPLT_CNT++;
						}
						//회원상세정보조회
						eduMemberVO.setDTL_CD(parentInfo.getCRS_MBR_CD());
						List<EduMemberVO> member_dtl_list = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO);
						document_data_table.append("<tr>");
						document_data_table.append("<td>"+(++IDX)+"</td>");//일련번호
						if(member_dtl_list==null || member_dtl_list.size() <= 0 || member_dtl_list.get(0)==null || member_dtl_list.get(0).getDTL_SN()==null) {
							LOGGER.debug("회원상세정보 등록건없음");
							document_data_table.append("<td colspan=\"3\">등록정보없음</td>");//시도,시군구,낚시터명/낚시어선명,
						} else {
							document_data_table.append("<td>"+member_dtl_list.get(0).getSIDO_CD_NM()+"</td>");//시도
							document_data_table.append("<td>"+member_dtl_list.get(0).getSIGNGU_CD_NM()+"</td>");//시군구
							document_data_table.append("<td>"+member_dtl_list.get(0).getDTL_NM()+"</td>");//낚시어선명,낚시터명
						}
						document_data_table.append("<td>"+item.getMBR_NM()+"</td>");//교육대상자 이름
						document_data_table.append("<td>&nbsp;</td>");//서명란
						if(isLrnngCmplt) {
							//document_data_table.append("<td>○</td>");//이수여부란
							document_data_table.append("<td>&nbsp;</td>");//이수여부란
						} else {
							document_data_table.append("<td>&nbsp;</td>");//이수여부란
						}
						document_data_table.append("<td>&nbsp;</td>");//미이수여부란
						document_data_table.append("<td>&nbsp;</td>");//비고
						document_data_table.append("</tr>");
						MERGE_CNT++;
						//
						LOGGER.debug("[i : "+i+"][IDX : "+IDX+"][MERGE_CNT : "+MERGE_CNT+"][list_max_cnt : "+list_max_cnt+"][ADD_TABLE_MAX_CNT : "+ADD_TABLE_MAX_CNT+"]");
						boolean isMergeAndClear = false;	
						if(list_max_cnt < ADD_TABLE_MAX_CNT) {
							if(i >= list_max_cnt-1) {
								isMergeAndClear = true;
								LOGGER.debug("type b");
							}
						} else {
							if(i!=0 && (i+1)%ADD_TABLE_MAX_CNT == 0) {
								isMergeAndClear = true;
								LOGGER.debug("type c");
							}
						}
						if((i+1) >= list_max_cnt) {
							LOGGER.debug("type last");
							isMergeAndClear = true;
						} 						
						if(isMergeAndClear) {//ADD_TABLE_MAX_CNT(15)건씩 묶음 처리 후 담기 , 초기화
							LOGGER.debug("ADD_TABLE_MAX_CNT(15)건씩 묶음 처리 후 담기 , 초기화");
							if(MERGE_CNT < ADD_TABLE_MAX_CNT) {
								LOGGER.debug("빈자리 채우기("+(ADD_TABLE_MAX_CNT-MERGE_CNT)+")건");
								for(int m=MERGE_CNT; m<=ADD_TABLE_MAX_CNT; m++) {
									document_data_table.append("<tr>");
									document_data_table.append("<td>&nbsp;</td>");//일련번호
									document_data_table.append("<td>&nbsp;</td>");//시도
									document_data_table.append("<td>&nbsp;</td>");//시군구
									document_data_table.append("<td>&nbsp;</td>");//낚시어선명,낚시터명
									document_data_table.append("<td>&nbsp;</td>");//교육대상자 이름
									document_data_table.append("<td>&nbsp;</td>");//서명란
									document_data_table.append("<td>&nbsp;</td>");//이수여부란
									document_data_table.append("<td>&nbsp;</td>");//미이수여부란
									document_data_table.append("<td>&nbsp;</td>");//비고
									document_data_table.append("</tr>");
								}
							}							
							mPublicUtils.replaceAll(document, "[[CAT_INS_NM]]", parentInfo.getCAT_INS_NM());//교육기관명
							mPublicUtils.replaceAll(document, "[[CRS_NM]]", parentInfo.getCRS_NM());//교육과정명
							mPublicUtils.replaceAll(document, "[[CRS_STR_DT]]", CRS_STR_DT);//교육일자
							//mPublicUtils.replaceAll(document, "[[MBR_CUR_CNT]]", String.valueOf(MBR_CUR_CNT));//신청자수(현인원중)
							//mPublicUtils.replaceAll(document, "[[MBR_CMPLT_CNT]]", String.valueOf(MBR_CMPLT_CNT));//이수자수(현인원중)
							mPublicUtils.replaceAll(document, "[[MBR_CUR_CNT]]", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");//신청자수(현인원중)
							mPublicUtils.replaceAll(document, "[[MBR_CMPLT_CNT]]", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");//이수자수(현인원중)
							mPublicUtils.replaceAll(document, "[[DATA_TABLE]]", document_data_table.toString());//출석표	
							html_table_data.append(document.toString());
							//초기화
							document_data_table.setLength(0);
							document.setLength(0);
							document.append(html_format);							
							MBR_CUR_CNT = 0;
							MBR_CMPLT_CNT = 0;	
							MERGE_CNT = 0;
						}
						log_dscrp.append("]");
					}	
					
					model.addAttribute("html_table_data", html_table_data.toString());	
				}				
			} catch(Exception e) {
				log_dscrp.append("[에러발생("+e.toString()+")]");
				model.addAttribute("html_table_data","<div class=\"roll_book_group\" style=\"page-break-after:always;\"><h2 class=\"roll_book_tit\">출력 가능한 내용이 없습니다.</h2>");
			}
			try {
				model.addAttribute("html_header", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/book/header.html").toString());
			} catch(Exception e) {
				model.addAttribute("html_header", "");
			}
			try {
				model.addAttribute("html_footer", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/book/footer.html").toString());
			} catch(Exception e) {
				model.addAttribute("html_footer","");
			}	
			
			returnUrl = "eduadm/mbrhstry/popwin_view";
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMyHistoryVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_MSG(mbr_msg.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
		
		return returnUrl;
	}	
		
	//교육목록 > 수강자목록> 설문조사
	@RequestMapping(value="/eduadm/mbrhstry/survey_view.do")
	public String eduadm_mbrhstry_survey_view(@ModelAttribute("surveyVO") SurveyVO surveyVO,
			@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		surveyVO.setASI_CRS_SN(eduMyHistoryVO.getCRS_SN());
		surveyVO.setETC_1(eduMyHistoryVO.getCRS_SN());
		surveyVO.setSv_id(eduMyHistoryVO.getCRS_SV_ID());
		
		SurveyVO info = this.service.select_survey(surveyVO);
	    List<SurveyVO> quest = this.service.select_surveyq_list(surveyVO);
	    List<SurveyVO> qitem = this.service.select_surveyqi_list(surveyVO);
	    List<SurveyVO> anscnt = this.service.survey_anscnt(surveyVO);
	    List<SurveyVO> answerole = this.service.survey_answerole_list(surveyVO);
	    
	    model.addAttribute("info", info);
	    model.addAttribute("quest", quest);
	    model.addAttribute("qitem", qitem);
	    model.addAttribute("aan", anscnt);
	    model.addAttribute("answerole", answerole);
	    model.addAttribute("CRS_SN", eduMyHistoryVO.getCRS_SN());
		
		return "eduadm/mbrhstry/survey_view";
	}
	
	//관리자(공통) 문자 메세지 보내기 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/mbrhstry/send/kakao/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_eduadm_mbrhstry_send_kakao_write(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		mModelAndView.setViewName("/eduadm/mbrhstry/write_kakao");
		String mainCdCn = "";
		String subCdCn = "";
		if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000001")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000001");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN().replace("[EDU_URL]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>");
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000002");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시(1강) 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[3교시(2강) 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[3교시(3강) 교육URL]</span>")
					.replace("[EDU_URL6]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL7]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000019")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000019");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000020");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000003")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000003");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN().replace("[EDU_URL]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>");
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000004");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000021")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000021");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000004");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000008")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000008");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시(1강) 교육URL]</span>");
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000009");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[3교시(2강) 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[3교시(3강) 교육URL]</span>")
					.replace("[EDU_URL6]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL7]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000022")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000022");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000020");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000010")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000010");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000023")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000023");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000011") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000012")
					 || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000017") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000018")
					 	|| eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000024") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000025")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID(eduMyHistoryVO.getTEMPLATE_ID());
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN()
					.replace("[SURVEY_URL]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000013")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000013");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();

			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000014");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시(1강) 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[3교시(2강) 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[3교시(3강) 교육URL]</span>")
					.replace("[EDU_URL6]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL7]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000015")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000015");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000016");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		}
		
		
		List<AdmMemberVO> list_mbr = new ArrayList<AdmMemberVO>();
		String[] praseMbrIds = eduMyHistoryVO.getMBR_IDS().split(",");
		for(String mbr_id : praseMbrIds) {
			if(mbr_id==null) {
				continue;					
			}
			//검증
			AdmMemberVO chkAdmMemberVO = new AdmMemberVO();
			chkAdmMemberVO.setMBR_ID(mbr_id);
			chkAdmMemberVO.setMBR_ST("Y");
			chkAdmMemberVO = admMemberService.get_member_all_info(chkAdmMemberVO);
			if(chkAdmMemberVO==null || chkAdmMemberVO.getMBR_ID()==null || chkAdmMemberVO.getMBR_ID().length()==0) {
				
			} else {
				list_mbr.add(chkAdmMemberVO);
			}
		}
		
		mModelAndView.addObject("MBR_IDS", eduMyHistoryVO.getMBR_IDS());
		mModelAndView.addObject("CRS_SN", eduMyHistoryVO.getCRS_SN());
		
		mModelAndView.addObject("TEMPLATE_ID", eduMyHistoryVO.getTEMPLATE_ID());
		mModelAndView.addObject("KAKAO_MAIN_MENT_CONT", mainCdCn);
		mModelAndView.addObject("KAKAO_SUB_MENT_CONT", subCdCn);
		mModelAndView.addObject("list_mbr",list_mbr);
		
		StringBuilder log_msg = new StringBuilder();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[관리자-교육관리-교육목록-수강자목록-카카오알림톡미리보기(" + eduMyHistoryVO.getTEMPLATE_ID() + ")]");
		tbl_inf.append("ALL_CODE_SET_TB");
		tbl_sn.append("");
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mLogRecordVO = new LogRecordVO();
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mLogRecordVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mLogRecordVO, request);
		} catch(Exception e) {
		}
		
		return mModelAndView;
	}

	//관리자(공통) 문자 메세지 보내기 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/mbrhstry/all/send/kakao/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_eduadm_mbrhstry_all_send_kakao_write(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ModelAndView mModelAndView = new ModelAndView();
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		mModelAndView.setViewName("/eduadm/mbrhstry/write_all_kakao");
		String mainCdCn = "";
		String subCdCn = "";
		if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000001")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000001");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN().replace("[EDU_URL]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>");
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000002");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시(1강) 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[3교시(2강) 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[3교시(3강) 교육URL]</span>")
					.replace("[EDU_URL6]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL7]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000019")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000019");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000020");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000003")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000003");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN().replace("[EDU_URL]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>");
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000004");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000021")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000021");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000004");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000008")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000008");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시(1강) 교육URL]</span>");
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000009");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[3교시(2강) 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[3교시(3강) 교육URL]</span>")
					.replace("[EDU_URL6]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL7]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000022")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000022");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000020");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000010")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000010");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000023")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000023");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000011") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000012")
					 || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000017") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000018")
					 	|| eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000024") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000025")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID(eduMyHistoryVO.getTEMPLATE_ID());
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN()
					.replace("[SURVEY_URL]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000013")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000013");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();

			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000014");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시(1강) 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[3교시(2강) 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[3교시(3강) 교육URL]</span>")
					.replace("[EDU_URL6]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL7]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		} else if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000015")){
			CodeSetVO mCodeSetVO01 = new CodeSetVO();
			mCodeSetVO01.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO01.setCD_ID("KKO_000015");
			mCodeSetVO01 = codeSetService.get_codeset_info(mCodeSetVO01);
			mainCdCn = mCodeSetVO01.getCD_CN();
			
			CodeSetVO mCodeSetVO02 = new CodeSetVO();
			mCodeSetVO02.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO02.setCD_ID("KKO_000016");
			mCodeSetVO02 = codeSetService.get_codeset_info(mCodeSetVO02);
			subCdCn = mCodeSetVO02.getCD_CN()
					.replace("[EDU_URL1]", "<span style='color:#ff0000;'>[1교시 교육URL]</span>")
					.replace("[EDU_URL2]", "<span style='color:#ff0000;'>[2교시 교육URL]</span>")
					.replace("[EDU_URL3]", "<span style='color:#ff0000;'>[3교시 교육URL]</span>")
					.replace("[EDU_URL4]", "<span style='color:#ff0000;'>[4교시 교육URL]</span>")
					.replace("[EDU_URL5]", "<span style='color:#ff0000;'>[설문조사URL]</span>");
		}
		
		mModelAndView.addObject("mbrTotCnt", eduMyHistoryVO.getMBR_IDS());
		
		mModelAndView.addObject("CRS_SN", eduMyHistoryVO.getCRS_SN());
		mModelAndView.addObject("TEMPLATE_ID", eduMyHistoryVO.getTEMPLATE_ID());
		mModelAndView.addObject("KAKAO_MAIN_MENT_CONT", mainCdCn);
		mModelAndView.addObject("KAKAO_SUB_MENT_CONT", subCdCn);
		
		StringBuilder log_msg = new StringBuilder();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[관리자-교육관리-교육목록-수강자목록-카카오알림톡미리보기(" + eduMyHistoryVO.getTEMPLATE_ID() + ")]");
		tbl_inf.append("ALL_CODE_SET_TB");
		tbl_sn.append("");
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mLogRecordVO = new LogRecordVO();
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mLogRecordVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mLogRecordVO, request);
		} catch(Exception e) {
		}
		
		return mModelAndView;
	}
	
	@RequestMapping(value = "/eduadm/mbrhstry/sendKakao.do")
	public String ajax_send_kakao(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();
		if(eduMyHistoryVO.getMBR_IDS().contains(",")){
			String[] mbr_id = eduMyHistoryVO.getMBR_IDS().split(",");
			for(int i = 0 ; i < mbr_id.length ; i++){
				try{
					EduMyHistoryVO info = new EduMyHistoryVO();
					info.setMBR_ID(mbr_id[i]);
					info.setCRS_SN(eduMyHistoryVO.getCRS_SN());
					info = eduMyHistoryService.get_edu_mbrhstry_info(info);
					
					//List<String> externalVideoUrl = new ArrayList<>();//외부 동영상 링크 URL
					String externalVideoUrl = "";//외부 동영상 링크 URL
					
					String domainUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
					
					info.setNotUsedPagination(true);
					List<EduMyHistoryVO> item = eduMyHistoryService.get_edu_mbrhstry_dtl_list(info);
					for(int j = 0 ; j < item.size() ; j++){
						//외부 동영상 URL 생성
						String eduUrl = domainUrl+"/educenter/mbrhstry/external/play.do?key="+EgovFileScrty.encode(item.get(j).getHMBR_DTL_SN());
						if(item.get(j).getLRNNG_CMPLT_DTL_ST().equals("1"))
							externalVideoUrl += "[수강완료],";
						else
							externalVideoUrl += eduUrl + ",";
						//End of 외부 동영상 URL 생성
					}
					String surveykey = EgovFileScrty.encode(info.getHMBR_SN()+","+info.getCRS_SN());
					externalVideoUrl += domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey;
					//카카오 알림톡 발송
					KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
					kakaoAlimTalkVO.setNTCN_TRSM_TEL(info.getMBR_HP());//연락처
					kakaoAlimTalkVO.setREG_ID(loginVO.getMBR_ID());
					EduCurriculumVO curriculumVO = new EduCurriculumVO();
					curriculumVO.setCRS_SN(info.getCRS_SN());
					curriculumVO = eduCurriculumService.get_edu_curriculum_info(curriculumVO);
					if(curriculumVO.getTYPE_GB().equals("online")){
						kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID(eduMyHistoryVO.getTEMPLATE_ID());
						kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(curriculumVO.getCRS_MBR_CD());
						kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
						if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000005") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000006") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000007")){
							EduCertificateVO reEduCertificateVO = new EduCertificateVO();
							reEduCertificateVO.setCRS_SN(info.getCRS_SN());
							reEduCertificateVO.setHMBR_SN(info.getHMBR_SN());
							reEduCertificateVO.setMBR_ID(info.getMBR_ID());
							EduCertificateVO reEduExistInfo = eduCertificateService.get_edu_certificate_info(reEduCertificateVO);
							String eduStrDt = curriculumVO.getCRS_STR_DT(); 
							String eduEndDt = curriculumVO.getCRS_END_DT();
							String allEduUrl = "";
							String complEdycIssuNo = reEduExistInfo.getCRTF_CD();
							allEduUrl += complEdycIssuNo + "&&";//이수증발급번호
							allEduUrl += info.getMBR_NM() + "&&";//성명
							allEduUrl += info.getMBR_BIRTH() + "&&";//생년월일
							allEduUrl += info.getMBR_ADDR1() + "&&";//주소
							if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000007")){
								allEduUrl += eduStrDt.replace(".0", "") + "&&";//교육일시
								allEduUrl += eduEndDt.replace(".0", "") + "&&";//교육일시
							} else {
								allEduUrl += eduStrDt.replace(".0", "") + " ~ " + eduEndDt.replace(".0", "") + "&&";//교육일시
							}
							allEduUrl += curriculumVO.getCRS_NM();
							kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);//교육동영상URL
						} else {
							kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(externalVideoUrl);//교육동영상URL
						}
						kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
						data.put("error", "0");
						data.put("msg", "알림톡을 정상적으로 전송했습니다.");
					} else if(curriculumVO.getTYPE_GB().equals("offline")){
						kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID(eduMyHistoryVO.getTEMPLATE_ID());
						kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(curriculumVO.getCRS_MBR_CD());
						kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
						EduCertificateVO reEduCertificateVO = new EduCertificateVO();
						reEduCertificateVO.setCRS_SN(info.getCRS_SN());
						reEduCertificateVO.setHMBR_SN(info.getHMBR_SN());
						reEduCertificateVO.setMBR_ID(info.getMBR_ID());
						EduCertificateVO reEduExistInfo = eduCertificateService.get_edu_certificate_info(reEduCertificateVO);
						String eduStrDt = curriculumVO.getCRS_STR_DT(); 
						String eduEndDt = curriculumVO.getCRS_END_DT();
						String allEduUrl = "";
						String complEdycIssuNo = reEduExistInfo.getCRTF_CD();
						allEduUrl += complEdycIssuNo + "&&";//이수증발급번호
						allEduUrl += info.getMBR_NM() + "&&";//성명
						allEduUrl += info.getMBR_BIRTH() + "&&";//생년월일
						allEduUrl += info.getMBR_ADDR1() + "&&";//주소
						if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000007")){
							allEduUrl += eduStrDt.replace(".0", "") + "&&";//교육일시
							allEduUrl += eduEndDt.replace(".0", "") + "&&";//교육일시
						} else {
							allEduUrl += eduStrDt.replace(".0", "") + " ~ " + eduEndDt.replace(".0", "") + "&&";//교육일시
						}
						allEduUrl += curriculumVO.getCRS_NM();
						kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);//교육동영상URL
						kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
						data.put("error", "0");
						data.put("msg", "알림톡을 정상적으로 전송했습니다.");
					}
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		} else {
			EduMyHistoryVO info = new EduMyHistoryVO();
			info.setMBR_ID(eduMyHistoryVO.getMBR_IDS());
			info.setCRS_SN(eduMyHistoryVO.getCRS_SN());
			info = eduMyHistoryService.get_edu_mbrhstry_info(info);
			
			String externalVideoUrl = "";//외부 동영상 링크 URL
			String domainUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			
			info.setNotUsedPagination(true);
			List<EduMyHistoryVO> item = eduMyHistoryService.get_edu_mbrhstry_dtl_list(info);
			for(int j = 0 ; j < item.size() ; j++){
				//외부 동영상 URL 생성
				String eduUrl = domainUrl+"/educenter/mbrhstry/external/play.do?key="+EgovFileScrty.encode(item.get(j).getHMBR_DTL_SN());
				if(item.get(j).getLRNNG_CMPLT_DTL_ST().equals("1"))
					externalVideoUrl += "[수강완료],";
				else
					externalVideoUrl += eduUrl + ",";
				//End of 외부 동영상 URL 생성
			}
			String surveykey = EgovFileScrty.encode(info.getHMBR_SN()+","+info.getCRS_SN());
			externalVideoUrl += domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey;
			//카카오 알림톡 발송
			KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
			kakaoAlimTalkVO.setNTCN_TRSM_TEL(info.getMBR_HP());//연락처
			//(알림톡필수)
			EduCurriculumVO curriculumVO = new EduCurriculumVO();
			curriculumVO.setCRS_SN(info.getCRS_SN());
			curriculumVO = eduCurriculumService.get_edu_curriculum_info(curriculumVO);
			if(curriculumVO.getTYPE_GB().equals("online")){
				//(알림톡필수)
				kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID(eduMyHistoryVO.getTEMPLATE_ID());//낚시어선 전문교육신청
				//(알림톡필수)
				if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000005") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000006") || eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000007")){
					EduCertificateVO reEduCertificateVO = new EduCertificateVO();
					reEduCertificateVO.setCRS_SN(info.getCRS_SN());
					reEduCertificateVO.setHMBR_SN(info.getHMBR_SN());
					reEduCertificateVO.setMBR_ID(info.getMBR_ID());
					EduCertificateVO reEduExistInfo = eduCertificateService.get_edu_certificate_info(reEduCertificateVO);
					String eduStrDt = curriculumVO.getCRS_STR_DT(); 
					String eduEndDt = curriculumVO.getCRS_END_DT();
					String allEduUrl = "";
					String complEdycIssuNo = reEduExistInfo.getCRTF_CD();
					allEduUrl += complEdycIssuNo + "&&";//이수증발급번호
					allEduUrl += info.getMBR_NM() + "&&";//성명
					allEduUrl += info.getMBR_BIRTH() + "&&";//생년월일
					allEduUrl += info.getMBR_ADDR1() + "&&";//주소
					if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000007")){
						allEduUrl += eduStrDt.replace(".0", "") + "&&";//교육일시
						allEduUrl += eduEndDt.replace(".0", "") + "&&";//교육일시
					} else {
						allEduUrl += eduStrDt.replace(".0", "") + " ~ " + eduEndDt.replace(".0", "") + "&&";//교육일시
					}
					allEduUrl += curriculumVO.getCRS_NM();
					kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);//교육동영상URL
				} else {
					kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(externalVideoUrl);//교육동영상URL
				}
				kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(curriculumVO.getCRS_MBR_CD());//낚시터 전문교육신청
				kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
				kakaoAlimTalkVO.setREG_ID(loginVO.getMBR_ID());
				kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
				data.put("error", "0");
				data.put("msg", "알림톡을 정상적으로 전송했습니다.");	
			} else if(curriculumVO.getTYPE_GB().equals("offline")){
				kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID(eduMyHistoryVO.getTEMPLATE_ID());
				kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(curriculumVO.getCRS_MBR_CD());
				kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
				EduCertificateVO reEduCertificateVO = new EduCertificateVO();
				reEduCertificateVO.setCRS_SN(info.getCRS_SN());
				reEduCertificateVO.setHMBR_SN(info.getHMBR_SN());
				reEduCertificateVO.setMBR_ID(info.getMBR_ID());
				EduCertificateVO reEduExistInfo = eduCertificateService.get_edu_certificate_info(reEduCertificateVO);
				String eduStrDt = curriculumVO.getCRS_STR_DT(); 
				String eduEndDt = curriculumVO.getCRS_END_DT();
				String allEduUrl = "";
				String complEdycIssuNo = reEduExistInfo.getCRTF_CD();
				allEduUrl += complEdycIssuNo + "&&";//이수증발급번호
				allEduUrl += info.getMBR_NM() + "&&";//성명
				allEduUrl += info.getMBR_BIRTH() + "&&";//생년월일
				allEduUrl += info.getMBR_ADDR1() + "&&";//주소
				if(eduMyHistoryVO.getTEMPLATE_ID().equals("KKO_000007")){
					allEduUrl += eduStrDt.replace(".0", "") + "&&";//교육일시
					allEduUrl += eduEndDt.replace(".0", "") + "&&";//교육일시
				} else {
					allEduUrl += eduStrDt.replace(".0", "") + " ~ " + eduEndDt.replace(".0", "") + "&&";//교육일시
				}
				allEduUrl += curriculumVO.getCRS_NM();
				kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);//교육동영상URL
				kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
				data.put("error", "0");
				data.put("msg", "알림톡을 정상적으로 전송했습니다.");
			}
		}
		
		StringBuilder log_msg = new StringBuilder();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[관리자-교육관리-교육목록-수강자목록-카카오알림톡보내기]");
		tbl_inf.append("ALL_CODE_SET_TB");
		tbl_sn.append("");
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mLogRecordVO = new LogRecordVO();
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mLogRecordVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mLogRecordVO, request);
		} catch(Exception e) {
		}	
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		
		return null;
	}
	
	@RequestMapping(value = "/eduadm/mbrhstry/allSendKakao.do", method = RequestMethod.POST)
	public String ajax_all_send_kakao(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();
		
		String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();
		String MASTER_MBR_TRGT_CD = loginVO.getMBR_TRGT_CD();
		
		List<EduMyHistoryVO> list = null;
		
		//----------------------------------------------------------------
		// 지역제한권한
		//----------------------------------------------------------------
		//지역 코드 조회 - 시도
		String MBR_SIDO_CD = "";
		List<CodeSetVO> list_address_cd  = null;
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		//지역 코드 조회 - 시군구
		String MBR_SIGNGU_CD = "";
		List<CodeSetVO> list_address_signgu_cd = null;
		{
		CodeSetVO mCodeSetVO = new CodeSetVO();
		MBR_SIDO_CD = eduMyHistoryVO.getHMBR_SIDO_CD();
  		mCodeSetVO.setCD_MASTER_ID(MBR_SIDO_CD);
  		list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		//----------------------------------------------------------------
		// 업종(교육대상)제한권한
		//----------------------------------------------------------------
		String MBR_TRGT_CD = "";			
		if(MASTER_MBR_TRGT_CD==null || MASTER_MBR_TRGT_CD.length()==0) {
			//제한없음
			MBR_TRGT_CD = eduMyHistoryVO.getHMBR_MBR_TRGT_CD();
		} else {
			MBR_TRGT_CD = MASTER_MBR_TRGT_CD;
			eduMyHistoryVO.setHMBR_MBR_TRGT_CD(MASTER_MBR_TRGT_CD);
		}
		model.addAttribute("MBR_TRGT_CD",MBR_TRGT_CD);
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_trgt_cd",list_mbr_cd);
		}
		//----------------------------------------------------------------
		//사업자구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00006");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_license_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_license_se_cd",list_license_se_cd);
		}			
		//낚시터업구분코드
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00007");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_fshlc_work_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_fshlc_work_cd",list_fshlc_work_cd);
		}	
								
	
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduMyHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduMyHistoryVO.getPageUnit());
		paginationInfo.setPageSize(eduMyHistoryVO.getPageSize());

		eduMyHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduMyHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduMyHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		eduMyHistoryVO.setNotUsedPagination(true);
		
		list = eduMyHistoryService.get_edu_mbrhstry_list(eduMyHistoryVO);
//		for(int i = 0 ; i < list.size() ; i++){
//		}
		for(int i = 0 ; i < list.size() ; i++){
			String externalVideoUrl = "";//외부 동영상 링크 URL
			String domainUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			list.get(i).setNotUsedPagination(true);
			List<EduMyHistoryVO> info = eduMyHistoryService.get_edu_mbrhstry_dtl_list(list.get(i));
			for(int j = 0 ; j < info.size() ; j++){
				//외부 동영상 URL 생성
				String eduUrl = domainUrl+"/educenter/mbrhstry/external/play.do?key="+EgovFileScrty.encode(info.get(j).getHMBR_DTL_SN());
				if(info.get(j).getLRNNG_CMPLT_DTL_ST().equals("1"))
					externalVideoUrl += "[수강완료],";
				else
					externalVideoUrl += eduUrl + ",";
				//End of 외부 동영상 URL 생성
			}
			String surveykey = domainUrl+"/educenter/mbrhstry/external/survey.do?key=" + EgovFileScrty.encode(list.get(i).getHMBR_SN()+","+list.get(i).getCRS_SN());// 설문조사 url
			externalVideoUrl += surveykey;
			//카카오 알림톡 발송
			KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
			kakaoAlimTalkVO.setREG_ID(loginVO.getMBR_ID());
			kakaoAlimTalkVO.setNTCN_TRSM_TEL(list.get(i).getMBR_HP());//연락처
			//(알림톡필수)
			kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(externalVideoUrl);//교육동영상URL
			
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(list.get(i).getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			// KKO_000001 : 낚시어선 전문교육안내, KKO_000003 : 낚시터 전문교육안내, KKO_000003 : 이수증발급
//			if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010300")){
//				kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000001");//낚시어선 전문교육신청CIDN010200
//			} else if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")){
//				kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000003");//낚시터 전문교육신청
//				
//			}
			kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID(eduMyHistoryVO.getTEMPLATE_ID());
			kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(eduCurriculumVO.getCRS_MBR_CD());
			kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
//			JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
			kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
			data.put("error", "0");
			data.put("msg", "알림톡을 정상적으로 전송했습니다.");	
		}
		//MID_2105218514203437,MID_2108279207153856,MID_2108277581152521
		
		StringBuilder log_msg = new StringBuilder();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[관리자-교육관리-교육목록-수강자목록-카카오알림톡 " + list.size() +"건 보내기]");
		tbl_inf.append("ALL_CODE_SET_TB");
		tbl_sn.append("");
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mLogRecordVO = new LogRecordVO();
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mLogRecordVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mLogRecordVO, request);
		} catch(Exception e) {
		}	
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		
		return null;
	}
	
}


