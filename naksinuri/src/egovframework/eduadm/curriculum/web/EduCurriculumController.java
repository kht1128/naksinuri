package egovframework.eduadm.curriculum.web;

import java.util.Enumeration;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduCategoryVisitInfVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicFileMngUtil;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduCurriculumController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(EduCurriculumController.class);
	
	/** EgovLogService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	/** EgovEducenterService */
	@Resource(name = "eduCenterService")
	private EduCenterService eduCenterService;
	
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
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name="NaksinuriService")
	  private NaksinuriService service;
	
	/** multipart */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;

	//관리자(교육센터) 교육커리큘럼 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/curriculum/list.do")
	public String edu_curriculum_list(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, HttpServletRequest request, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		
		boolean isKoreafcaMember = false;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			return "eduadm/error/page_400";	
		} else {
			if(loginVO.getMBR_POSITION_CD().equals("PCD0004")) {
				String[] koreafcaMbrIds = propertiesService.getString("koreafca.mbrIds").trim().split(",");
				if(koreafcaMbrIds!=null && koreafcaMbrIds.length!=0) {
					for (String mbrid : koreafcaMbrIds) {
						if(mbrid!=null && loginVO.getMBR_ID().equals(mbrid)) {
							isKoreafcaMember = true;
							break;
						}
					}
				}
			}
		}
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		//String MASTER_MBR_SIDO_CD = loginVO.getMBR_SIDO_CD();
		//String MASTER_MBR_SIGNGU_CD = loginVO.getMBR_SIGNGU_CD();
		String MASTER_MBR_TRGT_CD = loginVO.getMBR_TRGT_CD();
		String MASTER_MBR_EDU_INS_CD = loginVO.getMBR_EDU_INS_CD();
		
		//교육그룹 코드 조회(전체)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_all_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(비활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("Y");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_prev_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}
		//----------------------------------------------------------------
		// 업종(교육대상)제한권한
		//----------------------------------------------------------------
		String CRS_MBR_CD = "";			
		if(MASTER_MBR_TRGT_CD==null || MASTER_MBR_TRGT_CD.length()==0) {
			//제한없음
			CRS_MBR_CD = eduCurriculumVO.getCRS_MBR_CD();
			eduCurriculumVO.setCRS_MBR_CD(CRS_MBR_CD);
		} else {
			CRS_MBR_CD = MASTER_MBR_TRGT_CD;
			eduCurriculumVO.setCRS_MBR_CD(MASTER_MBR_TRGT_CD);
		}
		model.addAttribute("CRS_MBR_CD",CRS_MBR_CD);
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_trgt_cd",list_mbr_cd);
		}
		//----------------------------------------------------------------
		// 교육기관제한권한
		//----------------------------------------------------------------
		String CAT_INS_SN = "";
		if(MASTER_MBR_EDU_INS_CD==null || MASTER_MBR_EDU_INS_CD.length()==0) {
			//제한없음
			CAT_INS_SN = eduCurriculumVO.getCAT_INS_SN();
			eduCurriculumVO.setCAT_INS_SN(CAT_INS_SN);
		} else {
			CAT_INS_SN = MASTER_MBR_EDU_INS_CD;
			eduCurriculumVO.setCAT_INS_SN(MASTER_MBR_EDU_INS_CD);
		}
		model.addAttribute("CAT_INS_SN",CAT_INS_SN);
		//교육기관목록
		{
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setUSE_ST("1");
			eduCategoryInsInfVO.setNotUsedPagination(true);
			List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
			model.addAttribute("list_ins_info_cd",edu_category_ins_inf);	
		}
		//----------------------------------------------------------------
		// 기타개별필터
		//----------------------------------------------------------------
		if(MASTER_MBR_POSITION_CD.equals("PCD0002")) { //해양경찰서
			
		} else if(MASTER_MBR_POSITION_CD.equals("PCD0003")) { //지자체

		} else if(MASTER_MBR_POSITION_CD.equals("PCD0004")) { //교육기관
			eduCurriculumVO.setUSE_ST("1");
			eduCurriculumVO.setDEL_ST("0");
			//eduCurriculumVO.setCRS_STR_DT(mPublicUtils.currentTime("yyyy-01-01"));
			//eduCurriculumVO.setCRS_END_DT(mPublicUtils.currentTime("yyyy-12-31"));
		} else {//제한없음 - 공단
			
		}	
		//----------------------------------------------------------------
		// 공통조건
		//----------------------------------------------------------------
		model.addAttribute("LOCK_ST",eduCurriculumVO.getLOCK_ST());
		
		String searchYear = "";
		if(eduCurriculumVO.getSearchYear()!=null) {
			searchYear = eduCurriculumVO.getSearchYear();
		} else {
			searchYear = mPublicUtils.currentTime("YYYY");
			eduCurriculumVO.setSearchYear(searchYear);
		}
		//----------------------------------------------------------------
		List<EduCurriculumVO> curriculum_list = null;

		/** pageing setting */
		//eduCurriculumVO.setPageUnit(2);
		//eduCurriculumVO.setPageSize(5);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCurriculumVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCurriculumVO.getPageUnit());
		paginationInfo.setPageSize(eduCurriculumVO.getPageSize());

		eduCurriculumVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCurriculumVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCurriculumVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if(isKoreafcaMember) {
			eduCurriculumVO.setEXTRL_INSTT_RLS_CD(CAT_INS_SN);
		}		
		
		curriculum_list = eduCurriculumService.get_edu_curriculum_list(eduCurriculumVO);
		
		int totCnt = eduCurriculumService.get_edu_curriculum_list_totcnt(eduCurriculumVO);
		eduCurriculumVO.setTotalRecordCount(totCnt);			
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);	
		
		//model.addAttribute("frm_CRS_GRP_CD",eduCurriculumVO.getCRS_GRP_CD());
		model.addAttribute("frm_CRS_GRP_CD",eduCurriculumVO.getCRS_GRP_CD());
		//model.addAttribute("frm_CRS_GRP_CD_2",eduCurriculumVO.getCRS_GRP_CD());
		model.addAttribute("searchYear",searchYear);
		model.addAttribute("list",curriculum_list);
		
		model.addAttribute("searchStrDate",eduCurriculumVO.getSearchStrDate());
		model.addAttribute("searchEndDate",eduCurriculumVO.getSearchEndDate());
		
		//개인정보 보호 서약서
		String MBR_PLEDGE_ST = (String)request.getSession().getAttribute("MBR_PLEDGE_ST");
		if(MBR_PLEDGE_ST!=null && MBR_PLEDGE_ST.equals("N")){
			model.addAttribute("MBR_PLEDGE_ST", MBR_PLEDGE_ST);
		}
		
		return "eduadm/curriculum/list";
	}
	//관리자(교육센터) 교육커리큘럼 글쓰기 ------------------------------------------------
	@RequestMapping(value = "/eduadm/curriculum/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_curriculum_write(HttpServletRequest request, ModelMap model) throws Exception {	
		
		PublicUtils publicUtils = new PublicUtils();
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/curriculum/write");
		
		//searchYear 가져오기
		EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
		String CRS_YEAR = publicUtils.currentTime("YYYY");
		eduCurriculumVO.setCRS_YEAR(CRS_YEAR);
		mModelAndView.addObject("CRS_YEAR", eduCurriculumVO.getCRS_YEAR());
		
		
		//교육기관 목록 가져오기
		EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
		eduCategoryInsInfVO.setUSE_ST("1");
		eduCategoryInsInfVO.setNotUsedPagination(true);
		List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
		mModelAndView.addObject("edu_category_ins_inf",edu_category_ins_inf);	
		
		//방문방법 목록 가져오기
		EduCategoryVisitInfVO eduCategoryVisitInfVO = new EduCategoryVisitInfVO();
		eduCategoryVisitInfVO.setSearchKeyword("use_st");
		eduCategoryVisitInfVO.setUSE_ST("1");
		List<EduCategoryVisitInfVO> edu_category_visit_inf = eduCenterService.get_edu_category_visit_inf_list(eduCategoryVisitInfVO);
		mModelAndView.addObject("edu_category_visit_inf",edu_category_visit_inf);
		
		//교육그룹 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_edu_grp_cd",list_edu_grp_cd);
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_mbr_cd",list_mbr_cd);
		}
		//신청지역제한(낚시어선) 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00008");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_lock_area_ship_grp_cd",list_lock_area_grp_cd);
		}
		//신청지역제한(낚시터) 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00009");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_lock_area_house_grp_cd",list_lock_area_grp_cd);
		}
		
		return mModelAndView;
	}
	//관리자(교육센터) 교육커리큘럼 글수정 ------------------------------------------------
	@RequestMapping(value = "/eduadm/curriculum/modify.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_curriculum_modify(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, HttpServletRequest request, ModelMap model) throws Exception {	
		
		EduCurriculumVO info = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
		
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/curriculum/modify");
		mModelAndView.addObject("info",info);
		
		//첨부파일 갯수 확인 후 필요시 정보 업데이트
		String _atchFileId = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getCRS_SCHDL_FILE_SN());
		if(info.getCRS_SCHDL_FILE_SN()!=null && !info.getCRS_SCHDL_FILE_SN().equals(_atchFileId)) {
			info.setCRS_SCHDL_FILE_SN(_atchFileId);
			EduCurriculumVO updEduCurriculumVO = new EduCurriculumVO();
			updEduCurriculumVO.setCRS_SN(info.getCRS_SN());
			updEduCurriculumVO.setEXTRL_INSTT_RLS_CD(info.getEXTRL_INSTT_RLS_CD());
			updEduCurriculumVO.setCRS_SCHDL_FILE_SN(_atchFileId.length()==0?"NULL":_atchFileId);
			eduCurriculumService.set_edu_curriculum_mod(updEduCurriculumVO);	
		}
		//
		
		//교육기관 목록 가져오기
		EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
		eduCategoryInsInfVO.setUSE_ST("1");
		eduCategoryInsInfVO.setNotUsedPagination(true);
		List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
		mModelAndView.addObject("edu_category_ins_inf",edu_category_ins_inf);	
		
		//방문방법 목록 가져오기
		EduCategoryVisitInfVO eduCategoryVisitInfVO = new EduCategoryVisitInfVO();
		eduCategoryVisitInfVO.setSearchKeyword("use_st");
		eduCategoryVisitInfVO.setUSE_ST("1");
		List<EduCategoryVisitInfVO> edu_category_visit_inf = eduCenterService.get_edu_category_visit_inf_list(eduCategoryVisitInfVO);
		mModelAndView.addObject("edu_category_visit_inf",edu_category_visit_inf);
		
		//교육그룹 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_edu_grp_cd",list_edu_grp_cd);
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_mbr_cd",list_mbr_cd);
		}
		//신청지역제한(낚시어선) 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00008");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_lock_area_ship_grp_cd",list_lock_area_grp_cd);
		}
		//신청지역제한(낚시터) 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00009");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_lock_area_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_lock_area_house_grp_cd",list_lock_area_grp_cd);
		}
		//설문조사 리스트 가져오기
		{
			SurveyVO surveyVO = new SurveyVO();
			List<SurveyVO> survey_list = service.survey_select_list(surveyVO);
			mModelAndView.addObject("survey_list", survey_list);
		}
		return mModelAndView;
	}	
	//관리자(교육센터) 교육커리큘럼 등록 ------------------------------------------------
	@RequestMapping(value="/eduadm/curriculum/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_curriculum_write_act(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO,
			HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiRequest, 
			BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
					
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육목록-교육과정등록]");
		try {
			/*
			
			
			boolean isException = false;
			LOGGER.debug("첨부파일 검증시작 -------------------------------");		
			String inputName = "FILE_ATCH";
			Map allFiles = multiRequest.getFileMap();
			Map readFiles = multiRequest.getFileMap();
			readFiles.clear();
			if (!allFiles.isEmpty() && allFiles.get(inputName) != null) {
				readFiles.put(inputName, allFiles.get(inputName));
				allFiles.remove(inputName);
				LOGGER.debug("파일타겟 : " + inputName);	
		    }
			if(readFiles.isEmpty()) {
				data.put("error", "1");
				data.put("msg", "파일이 선택되지 않았습니다.");
				data.put("errCnt", 1);
				LOGGER.debug("파일이 선택되지 않았음.");	
			} else {
				LOGGER.debug("파일이 선택됨.");
				boolean isAllowFile = true;
				//파일이 있는 경우
				List _result = this.fileUtil.parseFileInf(readFiles, "NAK_", 0, "", "");
				if(_result.size() > 0) {
					for (int i = 0; i < _result.size(); i++) {
						FileVO fileVO = (FileVO)_result.get(i);
						if (fileVO.atchFileId.equals("ext_error")) {
							LOGGER.debug("정상적인 파일이 아닌 경우");
							data.put("error", "1");
							data.put("msg", "정상적인 파일이 아닙니다.");
							data.put("errCnt", 1);
							isAllowFile = false;
							isException = true;
						} else {
							LOGGER.debug("정상적인 파일인 경우");
							if(fileVO.getFileExtsn().equalsIgnoreCase("jpge") || fileVO.getFileExtsn().equalsIgnoreCase("jpg") || fileVO.getFileExtsn().equalsIgnoreCase("png") 
								|| fileVO.getFileExtsn().equalsIgnoreCase("gif") || fileVO.getFileExtsn().equalsIgnoreCase("bmp")) {
								//허용할 파일
							} else {
								data.put("error", "1");
								data.put("msg", fileVO.getFileExtsn() + " 의 파일의 확장자는 허용되지 않습니다.");
								data.put("errCnt", 1);
								isAllowFile = false;
								isException = true;
							}
							LOGGER.debug("파일타입 : " + fileVO.getFileExtsn());
						}
					}
					LOGGER.debug("파일첨부 검증결과 : " + isAllowFile);
					if(isAllowFile) {
						LOGGER.debug("실제 파일 첨부 시작");
						String _insertFileId = this.fileMngService.insertFileInfs(_result);
						eduCurriculumVO.setCRS_SCHDL_FILE_SN(_insertFileId);
					}
				}
			}	
			if(!isException) {
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();
								
				eduCurriculumVO.setREG_MBR_ID(loginVO.getMBR_ID());
				eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				if(eduCurriculumVO.getUSE_ST_CHK().equals("Y")) {
					eduCurriculumVO.setUSE_ST("1");
					eduCurriculumVO.setDEL_ST("0");
				} else {
					eduCurriculumVO.setUSE_ST("0");
				}
				String insertId = eduCurriculumVO.getUniqKey("CRS");
				eduCurriculumVO.setCRS_SN(insertId);
				
				eduCurriculumService.set_edu_curriculum_reg(eduCurriculumVO);
				
				log_dscrp.append("[교육센터관리자-교육목록-교육과정등록]");
				tbl_inf.append("EDU_CURRICULUM_TB,");
				tbl_sn.append(insertId+",");
				
				data.put("error", "0");
				data.put("msg", "새로운 교육과정이 등록되었습니다.");
				data.put("errCnt","0");
			
			
			*/		
			
			String _atchFileId = "";
			final Map<String, MultipartFile> files = multiRequest.getFileMap();			
			Map fresult = new PublicFileMngUtil(fileMngService,fileUtil).chkFileUpdate(
					files, //업로드 파일 목록
					_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
					"", //첨부파일아이디 번호(FILE_SN)
					"CRS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
					new String[]{
						"jpg","jpge","png","gif","bmp", //image
						//"mp4","avi","wmv", //video
						//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
						//"mp3","zip", //etc
					} , //허용할 업로드 파일 확장자 
					0, //허용할 업로드 파일 사이즈
					"" //파일저장위치(기본값:Globals.fileStorePath)
					);
			if(fresult.get("error").equals("1")) {
				LOGGER.debug("정상적인 거부");
			} else if(fresult.get("error").equals("2")) {
				LOGGER.debug("파일 검증 실패");
				model.addAttribute("page_back_cnt", "-3");
				return "/eduadm/error/page_back";
			} else { //정상적으로 처리됨.
				_atchFileId = fresult.get("atchFileId").toString();	
			}
		
			eduCurriculumVO.setCRS_SCHDL_FILE_SN(_atchFileId);
							
			eduCurriculumVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			if(eduCurriculumVO.getUSE_ST_CHK().equals("Y")) {
				eduCurriculumVO.setUSE_ST("1");
				eduCurriculumVO.setDEL_ST("0");
			} else {
				eduCurriculumVO.setUSE_ST("0");
			}
			if(eduCurriculumVO.getEDU_APLY_NTCN_SNDNG_YN_CHK().equals("Y")) {
				eduCurriculumVO.setEDU_APLY_NTCN_SNDNG_YN("Y");
			} else {
				eduCurriculumVO.setEDU_APLY_NTCN_SNDNG_YN("N");
			}			
			String insertId = eduCurriculumVO.getUniqKey("CRS");
			eduCurriculumVO.setCRS_SN(insertId);
			
			//XSS취약
			EgovStringUtil egovStringUtil = new EgovStringUtil();
			Enumeration enumetation = request.getParameterNames();
			 while (enumetation.hasMoreElements()) {
			      
				 String key = (String)enumetation.nextElement();
				 String[] parameterValue = request.getParameterValues(key);
			     if (parameterValue != null) {
					 switch(key){
						 case "CRS_NM" : eduCurriculumVO.setCRS_NM(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
						 case "CRS_PLACE" : eduCurriculumVO.setCRS_PLACE(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
						 case "CRS_DSCRP" : eduCurriculumVO.setCRS_DSCRP(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
						 case "CAT_INS_ADDR" : eduCurriculumVO.setCRS_ADDR(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
						 case "CRS_MBR" : eduCurriculumVO.setCRS_MBR(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					 }
			     } 
		    }
			
			eduCurriculumService.set_edu_curriculum_reg(eduCurriculumVO);
						
			log_dscrp.append("[게시물:"+eduCurriculumVO.getCRS_NM()+"(일련번호:"+insertId+")]");
			tbl_inf.append("EDU_CURRICULUM_TB,");
			tbl_sn.append(insertId+",");
			
			data.put("error", "0");
			data.put("msg", "새로운 교육과정이 등록되었습니다.");
			data.put("errCnt","0");
				
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCurriculumVO));
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
	//관리자(교육센터) 교육커리큘럼(상세) 교과목 등록 ------------------------------------------------
	@RequestMapping(value="/eduadm/curriculum/writeDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_curriculum_writeDtl_act(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO,
			HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiRequest, 
			BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육목록-교과목등록]");
		try {
			String _atchFileId = "";
			final Map<String, MultipartFile> files = multiRequest.getFileMap();	
			Map fresult = new PublicFileMngUtil(fileMngService,fileUtil).chkFileUpdate(
					files, //업로드 파일 목록
					_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
					"", //첨부파일아이디 번호(FILE_SN)
					"CRS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
					new String[]{
						"jpg","jpge","png","gif","bmp", //image
						"mp4","avi","wmv", //video
						"hwp","doc","xls","xlsx","csv","txt","pdf", //document
						"mp3","zip", //etc
					} , //허용할 업로드 파일 확장자 
					0, //허용할 업로드 파일 사이즈
					"" //파일저장위치(기본값:Globals.fileStorePath)
					);
			if(fresult.get("error").equals("1")) {
				LOGGER.debug("정상적인 거부");
			} else if(fresult.get("error").equals("2")) {
				LOGGER.debug("파일 검증 실패");
				model.addAttribute("page_back_cnt", "-3");
				return "/eduadm/error/page_back";
			} else { //정상적으로 처리됨.
				_atchFileId = fresult.get("atchFileId").toString();	
			}
			eduCurriculumVO.setCRS_DTL_FILE(_atchFileId);
			
			if(eduCurriculumVO.getTRN_SN()!=null) {
				String tmpStr = eduCurriculumVO.getTRN_SN().replaceAll("\\s+","");
				eduCurriculumVO.setTRN_SN(tmpStr);	
			}
			eduCurriculumVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_LV_ID());
			if(eduCurriculumVO.getUSE_ST_CHK().equals("Y")) {
				eduCurriculumVO.setUSE_ST("1");
				eduCurriculumVO.setDEL_ST("0");
			} else {
				eduCurriculumVO.setUSE_ST("0");
			}
			
			String insertId = eduCurriculumVO.getUniqKey("CRSD");
			eduCurriculumVO.setCRS_DTL_SN(insertId);
			
			eduCurriculumService.set_edu_curriculum_reg_dtl(eduCurriculumVO);
			
			log_dscrp.append("[게시물:"+eduCurriculumVO.getCRS_DTL_NM()+"(일련번호:"+insertId+")]");
			tbl_inf.append("EDU_CURRICULUM_DTL_TB,");
			tbl_sn.append(insertId+",");
			
			data.put("error", "0");
			data.put("msg", "새로운 교육과정이 등록되었습니다.");
			
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCurriculumVO));
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
	//관리자(교육센터) 교육커리큘럼 수정 ------------------------------------------------
	@RequestMapping(value="/eduadm/curriculum/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_curriculum_modify_act(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO,
			HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiRequest, 
			BindingResult bindingResult, ModelMap model) throws Exception {	
	
		EduCurriculumVO chkEduCurriculumVO = new EduCurriculumVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
	
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육목록-교육과정수정]");
		try {
			chkEduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			if(chkEduCurriculumVO == null || chkEduCurriculumVO.getCRS_SN() == null || chkEduCurriculumVO.getCRS_SN().length() == 0) {
				data.put("error", "1");
				data.put("msg", "처리 가능한 데이터가 없습니다.");
			} else {
				String _atchFileId = chkEduCurriculumVO.getCRS_SCHDL_FILE_SN();
				final Map<String, MultipartFile> files = multiRequest.getFileMap();			
				Map fresult = new PublicFileMngUtil(fileMngService,fileUtil).chkFileUpdate(
						files, //업로드 파일 목록
						_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
						"", //첨부파일아이디 번호(FILE_SN)
						"CRS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							//"mp4","avi","wmv", //video
							//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
							//"mp3","zip", //etc
						} , //허용할 업로드 파일 확장자 
						0, //허용할 업로드 파일 사이즈
						"" //파일저장위치(기본값:Globals.fileStorePath)
						);
				if(fresult.get("error").equals("1")) {
					LOGGER.debug("정상적인 거부");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					model.addAttribute("page_back_cnt", "-3");
					return "/eduadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId = fresult.get("atchFileId").toString();
				}
				eduCurriculumVO.setCRS_SCHDL_FILE_SN(_atchFileId);
								
				eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				if(eduCurriculumVO.getUSE_ST_CHK().equals("Y")) {
					eduCurriculumVO.setUSE_ST("1");
					eduCurriculumVO.setDEL_ST("0");
				} else {
					eduCurriculumVO.setUSE_ST("0");
				}
				if(eduCurriculumVO.getEDU_APLY_NTCN_SNDNG_YN_CHK().equals("Y")) {
					eduCurriculumVO.setEDU_APLY_NTCN_SNDNG_YN("Y");
				} else {
					eduCurriculumVO.setEDU_APLY_NTCN_SNDNG_YN("N");
				}				
				
				//XSS취약
				EgovStringUtil egovStringUtil = new EgovStringUtil();
				Enumeration enumetation = request.getParameterNames();
				 while (enumetation.hasMoreElements()) {
				      
					 String key = (String)enumetation.nextElement();
					 String[] parameterValue = request.getParameterValues(key);
				     if (parameterValue != null) {
						 switch(key){
							 case "CRS_NM" : eduCurriculumVO.setCRS_NM(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
							 case "CRS_PLACE" : eduCurriculumVO.setCRS_PLACE(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
							 case "CRS_DSCRP" : eduCurriculumVO.setCRS_DSCRP(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
							 case "CAT_INS_ADDR" : eduCurriculumVO.setCRS_ADDR(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
							 case "CRS_MBR" : eduCurriculumVO.setCRS_MBR(egovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
						 }
				     } 
			    }
				 
				eduCurriculumService.set_edu_curriculum_mod(eduCurriculumVO);
				
				log_dscrp.append("[게시물:"+chkEduCurriculumVO.getCRS_NM()+"(일련번호:"+chkEduCurriculumVO.getCRS_SN()+")]");
				tbl_inf.append("EDU_CURRICULUM_TB,");
				tbl_sn.append(eduCurriculumVO.getCRS_SN()+",");
				
				data.put("error", "0");
				data.put("msg", "교육과정 정보가 수정되었습니다.");
				
			}
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCurriculumVO));
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduCurriculumVO));
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
	//관리자(교육센터) 교육커리큘럼(상세)교과목 수정 ------------------------------------------------
	@RequestMapping(value="/eduadm/curriculum/modifyDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_curriculum_modifyDtl_act(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO,
			HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiRequest, 
			BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
	
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육목록-교과목수정]");
		try {	
						
			String _atchFileId = eduCurriculumVO.getCRS_DTL_FILE();
			final Map<String, MultipartFile> files = multiRequest.getFileMap();	
			Map fresult = new PublicFileMngUtil(fileMngService,fileUtil).chkFileUpdate(
					files, //업로드 파일 목록
					_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
					"", //첨부파일아이디 번호(FILE_SN)
					"CRS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
					new String[]{
						"jpg","jpge","png","gif","bmp", //image
						"mp4","avi","wmv", //video
						"hwp","doc","xls","xlsx","csv","txt","pdf", //document
						"mp3","zip", //etc
					} , //허용할 업로드 파일 확장자 
					0, //허용할 업로드 파일 사이즈
					"" //파일저장위치(기본값:Globals.fileStorePath)
					);
			if(fresult.get("error").equals("1")) {
				LOGGER.debug("정상적인 거부");
			} else if(fresult.get("error").equals("2")) {
				LOGGER.debug("파일 검증 실패");
				model.addAttribute("page_back_cnt", "-3");
				return "/eduadm/error/page_back";
			} else { //정상적으로 처리됨.
				_atchFileId = fresult.get("atchFileId").toString();	
			}
			eduCurriculumVO.setCRS_DTL_FILE(_atchFileId);
			
							
			String tmpStr = eduCurriculumVO.getTRN_SN().replaceAll("\\s+","");
			eduCurriculumVO.setTRN_SN(tmpStr);	
			eduCurriculumVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			if(eduCurriculumVO.getUSE_ST_CHK().equals("Y")) {
				eduCurriculumVO.setUSE_ST("1");
				eduCurriculumVO.setDEL_ST("0");
			} else {
				eduCurriculumVO.setUSE_ST("0");
			}
			eduCurriculumService.set_edu_curriculum_mod_dtl(eduCurriculumVO);
			
			log_dscrp.append("[게시물:"+eduCurriculumVO.getCRS_DTL_NM()+"(일련번호:"+eduCurriculumVO.getCRS_DTL_SN()+")]");
			tbl_inf.append("EDU_CURRICULUM_DTL_TB,");
			tbl_sn.append(eduCurriculumVO.getCRS_DTL_SN()+",");
			
			data.put("error", "0");
			data.put("msg", "교육과정 정보가 수정되었습니다.");
			
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCurriculumVO));
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
	//관리자(교육센터) 교육커리큘럼 삭제 ------------------------------------------------
	@RequestMapping(value="/eduadm/curriculum/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_curriculum_delete(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		EduCurriculumVO chkEduCurriculumVO = new EduCurriculumVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육목록-교육과정삭제]");
		try {
			chkEduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			if(chkEduCurriculumVO == null || chkEduCurriculumVO.getCRS_SN() == null || chkEduCurriculumVO.getCRS_SN().length() == 0) {
				data.put("error", "1");
				data.put("msg", "처리 가능한 데이터가 없습니다.");
			} else {
				
				
				String DEL_ST = chkEduCurriculumVO.getDEL_ST();
				if(DEL_ST.equals("1")) { //완전 삭제
					log_dscrp.append("-실제데이터삭제]");
					
					//메인
					eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_ID());			
					eduCurriculumService.remove_edu_curriculum(eduCurriculumVO);
					
					//상세
					EduCurriculumVO chkChildEduCurriculumVO = eduCurriculumVO;
					chkChildEduCurriculumVO.setNotUsedPagination(true);
					List<EduCurriculumVO> childList = eduCurriculumService.get_edu_curriculum_dtl_list(chkChildEduCurriculumVO);
					for(EduCurriculumVO item : childList) {
						item.setUPD_MBR_ID(loginVO.getMBR_ID());			
						eduCurriculumService.remove_edu_curriculum_dtl(item);
						tbl_inf.append("EDU_CURRICULUM_DTL_TB,");
						tbl_sn.append(item.getCRS_DTL_SN()+",");
					}
					
					//회원 교육대상자정보 일괄처리
					EduMemberVO delEduMemberVO = new EduMemberVO();
					delEduMemberVO.setCRS_SN(chkEduCurriculumVO.getCRS_SN());
					List<EduMemberVO> delTargetEduList = eduMemberService.get_edu_member_target_all_list(delEduMemberVO);
					if(delTargetEduList != null && delTargetEduList.size() > 0){
						eduMemberService.remove_edu_member_all_target(delEduMemberVO);
					}
					// End of 회원 교육대상자정보 일괄처리 
				
				} else {//처음 삭제
					log_dscrp.append("-교육과정삭제]");
					eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_ID());			
					eduCurriculumService.del_edu_curriculum(eduCurriculumVO);
					//상세는 하지 않아도 될듯??
				}
				
				log_dscrp.append("[게시물:"+chkEduCurriculumVO.getCRS_NM()+"(일련번호:"+chkEduCurriculumVO.getCRS_SN()+")]");
				tbl_inf.append("EDU_CURRICULUM_TB,");
				tbl_sn.append(eduCurriculumVO.getCRS_DTL_SN()+",");
				
				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");
				
			}
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("][처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduCurriculumVO));
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
	//관리자(교육센터) 교육커리큘럼(상세) 교과목 삭제 ------------------------------------------------
	@RequestMapping(value="/eduadm/curriculum/deleteDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_curriculum_deleteDtl(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		EduCurriculumVO chkEduCurriculumVO  = new EduCurriculumVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육목록-교과목삭제");
		try {
			chkEduCurriculumVO = eduCurriculumService.get_edu_curriculum_dtl_info(eduCurriculumVO);
			if(chkEduCurriculumVO == null || chkEduCurriculumVO.getCRS_DTL_SN() == null || chkEduCurriculumVO.getCRS_DTL_SN().length() == 0) {
				data.put("error", "1");
				data.put("msg", "처리 가능한 데이터가 없습니다.");
			} else {
				

				String DEL_ST = chkEduCurriculumVO.getDEL_ST();
				if(DEL_ST.equals("1")) { //완전 삭제
					log_dscrp.append("-실제데이터삭제]");
					//메인
					eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_ID());			
					eduCurriculumService.remove_edu_curriculum_dtl(eduCurriculumVO);
					
					if(eduCurriculumVO.getCRS_DTL_FILE()!=null && eduCurriculumVO.getCRS_DTL_FILE().length()!=0) {
						fileMngService.deleteAllDetailFileInfs(eduCurriculumVO.getCRS_DTL_FILE());
						fileMngService.deleteAllFileInf(eduCurriculumVO.getCRS_DTL_FILE());
					}
					
				} else {//처음 삭제
					log_dscrp.append("-교과목삭제]");
					eduCurriculumVO.setUPD_MBR_ID(loginVO.getMBR_ID());			
					eduCurriculumService.del_edu_curriculum_dtl(eduCurriculumVO);
				}
				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");
				
				log_dscrp.append("[게시물:"+chkEduCurriculumVO.getCRS_DTL_NM()+"(일련번호:"+chkEduCurriculumVO.getCRS_DTL_SN()+")]");
				tbl_inf.append("EDU_CURRICULUM_DTL_TB,");
				tbl_sn.append(eduCurriculumVO.getCRS_DTL_SN()+",");
				
			}			
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("][처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduCurriculumVO));
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
	//관리자(교육센터) 교육커리큘럼 복제 (복사) - 교과목도 자동생성 ------------------------------------------------
	@RequestMapping(value="/eduadm/curriculum/copy.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_curriculum_copy(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		EduCurriculumVO copyEduCurriculumVO = new EduCurriculumVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육목록-교육과정복제]");
		try {
			
			copyEduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			if(copyEduCurriculumVO==null || copyEduCurriculumVO.getCRS_SN()==null || copyEduCurriculumVO.getCRS_SN().length()==0) {
				data.put("error", "1");
				data.put("msg", "허용 된 접근이 아닙니다.");
			} else {
								
				String ORI_CRS_SN = copyEduCurriculumVO.getCRS_SN();
				String ORI_CRS_NM = copyEduCurriculumVO.getCRS_NM();
				//아래 복제 전에 첨부파일도 복제해야 한다...
				//
				
				copyEduCurriculumVO.setCRS_NM("(복사본)"+ORI_CRS_NM);
				copyEduCurriculumVO.setCRS_ST("0");//0:대기,1:승인(이수용),2:승인(발급용)
				copyEduCurriculumVO.setLOCK_ST("0");//0:해제,1:잠금
				copyEduCurriculumVO.setUSE_ST("1");//0:사용안함,1:사용함
				copyEduCurriculumVO.setDEL_ST("0");//0:삭제안함,1:삭제함
				copyEduCurriculumVO.setMBR_MAX_CNT("0");
				copyEduCurriculumVO.setMBR_CUR_CNT("0");
				copyEduCurriculumVO.setMBR_CMPLT_CNT("0");
				copyEduCurriculumVO.setCRS_ORD("1");
				
				String NEW_CRS_SN = copyEduCurriculumVO.getUniqKey("CRS");
				copyEduCurriculumVO.setCRS_SN(NEW_CRS_SN);
				eduCurriculumService.set_edu_curriculum_reg(copyEduCurriculumVO);
								
				log_dscrp.append("[원본 게시물:"+copyEduCurriculumVO.getCRS_NM()+"(일련번호:"+ORI_CRS_SN+")]");
				log_dscrp.append("[복사본 게시물:"+copyEduCurriculumVO.getCRS_NM()+"(일련번호:"+NEW_CRS_SN+")]");
				
				copyEduCurriculumVO.setCRS_SN(ORI_CRS_SN);
				copyEduCurriculumVO.setNotUsedPagination(true);
				List<EduCurriculumVO> subinfo = eduCurriculumService.get_edu_curriculum_dtl_list(copyEduCurriculumVO);
				for(EduCurriculumVO item_dtl : subinfo) {
					item_dtl.setCRS_SN(NEW_CRS_SN);					
					String insertId = eduCurriculumVO.getUniqKey("CRSD");
					item_dtl.setCRS_DTL_SN(insertId);					
					eduCurriculumService.set_edu_curriculum_reg_dtl(item_dtl);
					log_dscrp.append("[교과목-원본데이터:"+item_dtl.getCRS_DTL_SN()+"]");
					log_dscrp.append("[교과목-신규데이터:"+insertId+"]");
				}
				data.put("error", "0");
				data.put("msg", "교육과정이 복제(복사)되었습니다.");
			}
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(copyEduCurriculumVO));
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
	//관리자(교육센터) 교육커리큘럼(상세) 교과목 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/curriculum/listDtl.do")
	public String edu_curriculum_listDtl(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		//검증
		if(eduCurriculumVO.getCRS_SN()==null || eduCurriculumVO.getCRS_SN().length()==0) {
			return "redirect:/eduadm/curriculum/list.do";
		}
		//교육과정 정보
		EduCurriculumVO parentInfo = null;
		try {
			eduCurriculumVO.setTypeStr("");
			parentInfo = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
		} catch(Exception e) {
			LOGGER.debug("[fail load data] "+e.toString());
		}
		model.addAttribute("parentInfo",parentInfo);
		//
		List<EduTrainingDataVO> traning_data_list = null;
		List<EduCurriculumVO> curriculum_list = null;

		eduCurriculumVO.setSTEP_ST(parentInfo.getSTEP_ST());//정렬순서값
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCurriculumVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCurriculumVO.getPageUnit());
		paginationInfo.setPageSize(eduCurriculumVO.getPageSize());

		eduCurriculumVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCurriculumVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCurriculumVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		curriculum_list = eduCurriculumService.get_edu_curriculum_dtl_list(eduCurriculumVO);
		
		int totCnt = eduCurriculumService.get_edu_curriculum_dtl_list_totcnt(eduCurriculumVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		//교육자료 목록 가져오기
		EduTrainingDataVO eduTrainingDataVO = new EduTrainingDataVO();
		eduTrainingDataVO.setNotUsedPagination(true);
		traning_data_list = eduTrainingDataService.get_edu_data_list(eduTrainingDataVO);
			
		model.addAttribute("list",curriculum_list);
		model.addAttribute("traning_data_list",traning_data_list);
		return "eduadm/curriculum/list_dtl";
	}	
	//관리자(교육센터) 교육커리큘럼(상세) 교과목 글쓰기 ------------------------------------------------
	@RequestMapping(value = "/eduadm/curriculum/writeDtl.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_curriculum_writeDtl(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, HttpServletRequest request, ModelMap model) throws Exception {	
		
		
		List<EduTrainingDataVO> datalist = null;
		EduCategoryVO eduCategoryVO = new EduCategoryVO();
		eduCategoryVO.setSearchKeyword("use_st");
		eduCategoryVO.setUSE_ST("1");
		
		//교육카테고리 목록 가져오기
		List<EduCategoryVO> list1 = eduCategoryService.get_edu_category_list_1(eduCategoryVO);	
		
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/curriculum/write_dtl");
		mModelAndView.addObject("CRS_SN",eduCurriculumVO.getCRS_SN());
		mModelAndView.addObject("edu_category_1",list1);
		mModelAndView.addObject("edu_training_data_list",datalist);
		
		//교육기관 목록 가져오기
		EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
		eduCategoryInsInfVO.setUSE_ST("1");
		eduCategoryInsInfVO.setNotUsedPagination(true);
		List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
		mModelAndView.addObject("edu_category_ins_inf",edu_category_ins_inf);	
		
		//방문방법 목록 가져오기
		EduCategoryVisitInfVO eduCategoryVisitInfVO = new EduCategoryVisitInfVO();
		eduCategoryVisitInfVO.setSearchKeyword("use_st");
		eduCategoryVisitInfVO.setUSE_ST("1");
		List<EduCategoryVisitInfVO> edu_category_visit_inf = eduCenterService.get_edu_category_visit_inf_list(eduCategoryVisitInfVO);
		mModelAndView.addObject("edu_category_visit_inf",edu_category_visit_inf);
				
		return mModelAndView;
	}
	//관리자(교육센터) 교육커리큘럼(상세) 교과목 글수정 ------------------------------------------------
	@RequestMapping(value = "/eduadm/curriculum/modifyDtl.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_curriculum_modifyDtl(@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO, HttpServletRequest request, ModelMap model) throws Exception {	
		
		
		EduCurriculumVO info = eduCurriculumService.get_edu_curriculum_dtl_info(eduCurriculumVO);
		
		//교육카테고리 목록 가져오기
		EduCategoryVO eduCategoryVO = new EduCategoryVO();
		eduCategoryVO.setSearchKeyword("use_st");
		eduCategoryVO.setUSE_ST("1");				
		//교육카테고리 목록 가져오기
		List<EduCategoryVO> list1 = eduCategoryService.get_edu_category_list_1(eduCategoryVO);
		eduCategoryVO.setCAT_SN(info.getCAT_SN());
		List<EduCategoryVO> list2 = eduCategoryService.get_edu_category_list_2(eduCategoryVO);	
		
		//교육자료 목록 가져오기
		String trn_sn = info.getTRN_SN();
		if(trn_sn!=null && trn_sn.length()!=0) {
			eduCategoryVO.setTrn_sn_arr(trn_sn.split(","));
		} 
		eduCategoryVO.setCAT_DTL_SN(info.getCAT_DTL_SN());
		List<EduTrainingDataVO> datalist = eduTrainingDataService.get_edu_data_list(eduCategoryVO);
		
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/curriculum/modify_dtl");
		mModelAndView.addObject("info",info);
		mModelAndView.addObject("CRS_SN",eduCurriculumVO.getCRS_SN());
		mModelAndView.addObject("edu_category_1",list1);
		mModelAndView.addObject("edu_category_2",list2);
		mModelAndView.addObject("edu_training_data_list",datalist);
		
		//교육기관 목록 가져오기
		EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
		eduCategoryInsInfVO.setUSE_ST("1");
		eduCategoryInsInfVO.setNotUsedPagination(true);
		List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
		mModelAndView.addObject("edu_category_ins_inf",edu_category_ins_inf);	
		
		//방문방법 목록 가져오기
		EduCategoryVisitInfVO eduCategoryVisitInfVO = new EduCategoryVisitInfVO();
		eduCategoryVisitInfVO.setSearchKeyword("use_st");
		eduCategoryVisitInfVO.setUSE_ST("1");
		List<EduCategoryVisitInfVO> edu_category_visit_inf = eduCenterService.get_edu_category_visit_inf_list(eduCategoryVisitInfVO);
		mModelAndView.addObject("edu_category_visit_inf",edu_category_visit_inf);
				
		return mModelAndView;
	}
	
	
}


