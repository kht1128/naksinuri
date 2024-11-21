package egovframework.eduadm.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.eduadm.board.service.EduBoardService;
import egovframework.eduadm.board.service.EduBoardVO;
import egovframework.eduadm.main.service.EduAdmCprBplcVO;
import egovframework.eduadm.main.service.EduAdmMbrHpChngVO;
import egovframework.eduadm.main.service.EduAdmSmsRequstVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.main.service.EduMbrRemindersVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.educenter.service.EduCenterMainService;
import egovframework.educenter.service.EduCenterMbrRemindersVO;
import egovframework.educenter.service.EduCprBplcVO;
import egovframework.educenter.service.MbrHpChngVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicFileMngUtil;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduBoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EduBoardController.class);
	
	/** LogRecordService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	/** EgovEducenterService */
	@Resource(name = "eduCenterService")
	private EduCenterService eduCenterService;
	
	/** EduBoardService */
	@Resource(name = "eduBoardService")
	private EduBoardService eduBoardService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	
	 
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	/** EduCenterMainService */
	@Resource(name = "eduCenterMainService")
	private EduCenterMainService eduCenterMainService;
	
	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	//교육센터  - 공지사항 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/notice/list.do")
	public String eduadm_board_notice_list(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			LOGGER.debug("교육센터 관리자페이지는 무조건 로그인이 필요한 서비스!!");
			return "redirect:/eduadm/member/login.do";
		} else {
			
			//권한 및 그룹체크
			if(loginVO.getMBR_LV_ID().equals("1") //최상위등급,그룹제한없음 = 통합관리자
				|| ( loginVO.getMBR_LV_ID().equals("2") && loginVO.getMBR_GRP_2_ST().equals("Y") ) //1등급 = 총관리자
			) {
				LOGGER.debug("교육센터 관리자페이지 - 접근 허용!!");
			} else {
				LOGGER.debug("교육센터 관리자페이지 - 접근권한 없음!!");
				return "redirect:/eduadm/index.do";
			}
		}
		
		/** EgovPropertyService */
		eduBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduBoardVO.setBD_ID("board013");//교육센터 공지사항
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduBoardVO.getPageSize());

		eduBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduBoardVO> list = eduBoardService.boardAdmList(eduBoardVO);		
		int totCnt = eduBoardService.boardAdmListTotCnt(eduBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduBoardVO.getSearchCondition().length()==0?"":eduBoardVO.getSearchKeyword());
				
		return "eduadm/board/notice/list";
	}
	
	//교육센터  - FAQ 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/faq/list.do")
	public String eduadm_board_faq_list(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, ModelMap model) throws Exception {
		
		/** EgovPropertyService */
		eduBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduBoardVO.setBD_ID("board003");//교육센터 공지사항
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduBoardVO.getPageSize());

		eduBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduBoardVO> list = eduBoardService.boardAdmList(eduBoardVO);		
		int totCnt = eduBoardService.boardAdmListTotCnt(eduBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduBoardVO.getSearchCondition().length()==0?"":eduBoardVO.getSearchKeyword());
				
		return "eduadm/board/faq/list";
	}
	
	//교육센터  - 자료실 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/file/list.do")
	public String eduadm_board_file_list(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, ModelMap model) throws Exception {
		
		/** EgovPropertyService */
		eduBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduBoardVO.setBD_ID("board015");
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduBoardVO.getPageSize());

		eduBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduBoardVO> list = eduBoardService.boardAdmList(eduBoardVO);		
		int totCnt = eduBoardService.boardAdmListTotCnt(eduBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduBoardVO.getSearchCondition().length()==0?"":eduBoardVO.getSearchKeyword());
				
		return "eduadm/board/file/list";
	}
	
	//교육센터  - 안내사항(관리자) 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/admnotice/list.do")
	public String eduadm_board_admnotice_list(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, ModelMap model) throws Exception {
		
		/** EgovPropertyService */
		eduBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduBoardVO.setBD_ID("board018");//교육센터 안내사항(관리자)
		
		//직급 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00003");
	  		mCodeSetVO.setUSE_AT("Y");
	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_position_cd",list_position_cd);
		}
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduBoardVO.getPageSize());

		eduBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	
	
		
		List<EduBoardVO> list = eduBoardService.boardAdmList(eduBoardVO);		
		int totCnt = eduBoardService.boardAdmListTotCnt(eduBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduBoardVO.getSearchCondition().length()==0?"":eduBoardVO.getSearchKeyword());
		
		return "eduadm/board/admnotice/list";
	}
	//교육센터  - 안내사항 리스트 ------------------------------------------------
		@RequestMapping(value = "/eduadm/notice/list.do")
		public String eduadm_board_admnotice_list2(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, ModelMap model, HttpServletRequest request) throws Exception {
			
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			String mbr_position_cd = loginVO.getMBR_POSITION_CD();
			
			/** EgovPropertyService */
			eduBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
			eduBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
			
			eduBoardVO.setBD_ID("board018");//교육센터 안내사항
			eduBoardVO.setBD_TRGT_CD(mbr_position_cd);
			
			/** pageing setting */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(eduBoardVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(eduBoardVO.getPageUnit());
			paginationInfo.setPageSize(eduBoardVO.getPageSize());

			eduBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			eduBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
			eduBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			List<EduBoardVO> list = eduBoardService.boardAdmList(eduBoardVO);		
			int totCnt = eduBoardService.boardAdmListTotCnt(eduBoardVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("list",list);
			
			model.addAttribute("searchCondition",eduBoardVO.getSearchCondition());
			model.addAttribute("searchKeyword",eduBoardVO.getSearchCondition().length()==0?"":eduBoardVO.getSearchKeyword());
			
			return "eduadm/notice/list";
		}
	
	//교육센터  - 온라인교육신청(심사) 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/rmndr/list_{addWebLink}.do")
	public String eduadm_board_rmndr_list(@PathVariable("addWebLink") String addWebLink,
			@ModelAttribute("eduMbrRemindersVO") EduMbrRemindersVO eduMbrRemindersVO,		
			@RequestParam(value="frm_CMPLT_ST",required=false) String frm_CMPLT_ST, ModelMap model) throws Exception {
		
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
	  		mCodeSetVO.setHIDE_AT("N");
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
				
		eduMbrRemindersVO.setCMPLT_ST(frm_CMPLT_ST);
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduMbrRemindersVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduMbrRemindersVO.getPageUnit());
		paginationInfo.setPageSize(eduMbrRemindersVO.getPageSize());

		eduMbrRemindersVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduMbrRemindersVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduMbrRemindersVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		eduMbrRemindersVO.setTYPE_GB(addWebLink);//일반교육인지 신규재개자교육인지 메뉴 구분(online:일반, offline:신규재개자)
		String CRS_LAW_TYPE = "";
		if(addWebLink.equals("online")){
			CRS_LAW_TYPE = "default ";
		} else if(addWebLink.equals("offline")){
			CRS_LAW_TYPE = "CIDLAW002";
		}
		eduMbrRemindersVO.setCRS_LAW_TYPE(CRS_LAW_TYPE);//일반교육인지 신규재개자교육인지 구분
		
		List<EduMbrRemindersVO> list = eduCenterService.get_edu_mbr_reminders_list(eduMbrRemindersVO);
		int totCnt = eduCenterService.get_edu_mbr_reminders_list_totcnt(eduMbrRemindersVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchOrderBy",eduMbrRemindersVO.getSearchOrderBy());
		//model.addAttribute("searchKeyword",eduMbrRemindersVO.getSearchCondition().length()==0?"":eduMbrRemindersVO.getSearchKeyword());
		model.addAttribute("searchKeyword",eduMbrRemindersVO.getSearchKeyword());
		model.addAttribute("searchStrDate",eduMbrRemindersVO.getSearchStrDate());
		model.addAttribute("searchEndDate",eduMbrRemindersVO.getSearchEndDate());
		model.addAttribute("frm_CMPLT_ST",frm_CMPLT_ST);
		
		model.addAttribute("addWebLink",addWebLink);
		return "eduadm/board/rmndr/list_" + addWebLink.toLowerCase();
	}
	
	//교육대상자관리 - 온라인, 오프라인 교육 신청자 메모하기 처리 로직 ------------------------------------------------
  	@RequestMapping(value="/eduadm/board/rmndr/{addWebLink}_memo_update_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_edu_member_memo_update_act(@PathVariable("addWebLink") String addWebLink,
  			@ModelAttribute("eduMbrRemindersVO") EduMbrRemindersVO eduMbrRemindersVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		JSONObject data = new JSONObject();	
  		try {
  			//검증
  			EduMbrRemindersVO chkEduMbrRemindersVO = eduCenterService.get_edu_mbr_reminders_info(eduMbrRemindersVO);
  			if(chkEduMbrRemindersVO.getRMNDR_SN()==null || chkEduMbrRemindersVO.getRMNDR_SN().length()==0) {
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
  	  			log_msg.append("[아이디:"+MASTER_MBR_ID+"]");
  	  			log_msg.append("[처리권한:"+MASTER_MBR_GRP_ID+"]");
  	  			log_msg.append("[처리레벨:"+MASTER_MBR_LV_ID+"]");
  	  			log_msg.append("[처리직급:"+MASTER_MBR_POSITION_CD+"]");
  	  			
  	  			String section = "";
	  			if(addWebLink.equals("online")){
	  				section = "온라인교육신청자";
	  			} else {
	  				section = "오프라인교육신청자";
	  			}
  	  			
  				log_dscrp.append("[교육센터관리자-교육대상자관리-" + section + "-메모기록-처리]");
  				log_dscrp.append("[이름:"+chkEduMbrRemindersVO.getRMNDR_MBR_NM()+"(아이디:"+chkEduMbrRemindersVO.getRMNDR_MBR_ID()+")]");
  				log_dscrp.append("[기존메모:"+chkEduMbrRemindersVO.getRMNDR_DSCRP()+"]");
				log_dscrp.append("[신규메모:"+eduMbrRemindersVO.getRMNDR_DSCRP()+"]");
				
				EduMbrRemindersVO updEduMbrRemindersVO = new EduMbrRemindersVO();
				updEduMbrRemindersVO.setRMNDR_SN(eduMbrRemindersVO.getRMNDR_SN());
				updEduMbrRemindersVO.setRMNDR_DSCRP(eduMbrRemindersVO.getRMNDR_DSCRP());
  				eduCenterService.set_edu_mbr_reminders_memo_mod(updEduMbrRemindersVO);
  				//사용자사유로그기록
				{
	  				logRecordService.set_log_mbr_mod_data("MBR_RMNDR_TB","수정","메모수정",chkEduMbrRemindersVO.getRMNDR_SN(),chkEduMbrRemindersVO.getRMNDR_MBR_NM(),chkEduMbrRemindersVO,chkEduMbrRemindersVO,loginVO,request);
				}
  				data.put("error", "0");
  				data.put("msg", chkEduMbrRemindersVO.getRMNDR_MBR_NM()+"님의 메모가 기록되었습니다.");
  				
  				tbl_inf.append("MBR_RMNDR_TB,");
  				tbl_sn.append(chkEduMbrRemindersVO.getRMNDR_SN()+",");
  								
  				try {	
  					/**
  					 * LOG RECORED (로그기록)
  					 * */
  					LogRecordVO mEduLogRecordVO = new LogRecordVO();
  					log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduMbrRemindersVO));
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
  		} catch(Exception e) {
  			LOGGER.debug("[fail process] "+e.toString());
  			data.put("error", "1");
  			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
  		}
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
	
	//온라인 교육 신청자 이용정보동의서(선택)
	@RequestMapping(value = "/eduadm/board/rmndr/indvdl_info_view.do")
	public ModelAndView eduadm_board_rmndr_indvdl_info_view(@ModelAttribute("eduCenterMbrRemindersVO") EduCenterMbrRemindersVO eduCenterMbrRemindersVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		ModelAndView mModelAndView = new ModelAndView();
		
		model.addAttribute("id", eduCenterMbrRemindersVO.getRMNDR_MBR_ID());
		eduCenterMbrRemindersVO = eduCenterMainService.get_educenter_mbr_rmndr_info(eduCenterMbrRemindersVO);	
		model.addAttribute("info", eduCenterMbrRemindersVO);
		
		mModelAndView.setViewName("eduadm/board/rmndr/indvdl_info_view");
		
		return mModelAndView;
	}
	
	//교육센터  - 공지사항 뷰 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/notice/view.do")
	public String eduadm_board_notice_view(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			HttpServletRequest request, Model model) throws Exception {
		
		EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
		if(info == null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		return "eduadm/board/notice/view";
	}

	//교육센터  - 공지사항 뷰 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/notice/view.do")
	public String eduadm_board_notice_view2(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			HttpServletRequest request, Model model) throws Exception {
		
		EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
		if(info == null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		return "eduadm/notice/view";
	}
	
	//교육센터  - FAQ 뷰 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/faq/view.do")
	public String eduadm_board_faq_view(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			HttpServletRequest request, Model model) throws Exception {
		
		EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
		if(info == null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		return "eduadm/board/faq/view";
	}
	
	//교육센터  - 자료실 뷰 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/file/view.do")
	public String eduadm_board_file_view(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			HttpServletRequest request, Model model) throws Exception {
		
		EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
		if(info == null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		return "eduadm/board/file/view";
	}
	
	//교육센터  - 안내사항(관리자) 뷰 페이지 ------------------------------------------------
		@RequestMapping(value = "/eduadm/board/admnotice/view.do")
		public String eduadm_board_admnotice_view(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
				HttpServletRequest request, Model model) throws Exception {
			
			//직급 코드 조회
			{
		  		CodeSetVO mCodeSetVO = new CodeSetVO();
		  		mCodeSetVO.setCD_MASTER_ID("CID00003");
		  		mCodeSetVO.setUSE_AT("Y");
		  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
		  		model.addAttribute("list_position_cd",list_position_cd);
			}
			
			EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
			if(info == null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
				LOGGER.debug("존재 하지 않는 게시물을 요청");
				model.addAttribute("page_back_cnt", "-1");
				return "/eduadm/error/page_back";
			}
			model.addAttribute("info", info);
			
			return "eduadm/board/admnotice/view";
		}
	
	//교육센터  - 공지사항 글쓰기 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/notice/write.do")
	public String eduadm_board_notice_write(HttpServletRequest request, ModelMap model) throws Exception {
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", "");
		request.getSession().setAttribute("se2.atchFileId", "");
		//
		return "eduadm/board/notice/write";
	}
	
	//교육센터  - FAQ 글쓰기 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/faq/write.do")
	public String eduadm_board_faq_write(HttpServletRequest request, ModelMap model) throws Exception {
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", "");
		request.getSession().setAttribute("se2.atchFileId", "");
		//
		return "eduadm/board/faq/write";
	}
	
	//교육센터  - 자료실 글쓰기 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/file/write.do")
	public String eduadm_board_file_write(HttpServletRequest request, ModelMap model) throws Exception {
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", "");
		request.getSession().setAttribute("se2.atchFileId", "");
		//
		return "eduadm/board/file/write";
	}
	
	//교육센터  - 공지사항(관리자) 글쓰기 페이지 ------------------------------------------------
		@RequestMapping(value = "/eduadm/board/admnotice/write.do")
		public String eduadm_board_admnotice_write(HttpServletRequest request, ModelMap model) throws Exception {
			//스마트에디터2용 atchFileId 저장
			request.getSession().setAttribute("se2.bdsn", "");
			request.getSession().setAttribute("se2.atchFileId", "");
			
			//직급 코드 조회
			{
		  		CodeSetVO mCodeSetVO = new CodeSetVO();
		  		mCodeSetVO.setCD_MASTER_ID("CID00003");
		  		mCodeSetVO.setUSE_AT("Y");
		  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
		  		model.addAttribute("list_position_cd",list_position_cd);
			}
			//
			return "eduadm/board/admnotice/write";
		}
	
	
	//교육센터  - 공지사항 글수정 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/notice/modify.do")
	public String eduadm_board_notice_modify(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		String numId = (String)request.getParameter("selectedId");//나중에 없애자.
		
		if(numId==null || numId.length()==0) {
			numId = eduBoardVO.getBD_SN();
		} else {
			eduBoardVO.setBD_SN(numId);
		}
		EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
		
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", info.getBD_SN());
		request.getSession().setAttribute("se2.atchFileId", info.getBD_FILE_SE2());
		//
		
		//첨부파일 갯수 확인 후 필요시 정보 업데이트
		String _atchFileId = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getBD_FILE());
		if(info.getBD_FILE()!=null && !info.getBD_FILE().equals(_atchFileId)) {
			info.setBD_FILE(_atchFileId);
			eduBoardService.boardAdmUpdate(info);	
		}
		//

		model.addAttribute("info", info);
		
		return "eduadm/board/notice/modify";
	}
	
	//교육센터  - FAQ 글수정 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/faq/modify.do")
	public String eduadm_board_faq_modify(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		String numId = (String)request.getParameter("selectedId");//나중에 없애자.
		
		if(numId==null || numId.length()==0) {
			numId = eduBoardVO.getBD_SN();
		} else {
			eduBoardVO.setBD_SN(numId);
		}
		EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
		
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", info.getBD_SN());
		request.getSession().setAttribute("se2.atchFileId", info.getBD_FILE_SE2());
		//
		
		//첨부파일 갯수 확인 후 필요시 정보 업데이트
		String _atchFileId = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getBD_FILE());
		if(info.getBD_FILE()!=null && !info.getBD_FILE().equals(_atchFileId)) {
			info.setBD_FILE(_atchFileId);
			eduBoardService.boardAdmUpdate(info);	
		}
		//

		model.addAttribute("info", info);
		
		return "eduadm/board/faq/modify";
	}
	
	//교육센터  - 자료실 글수정 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/file/modify.do")
	public String eduadm_board_file_modify(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		String numId = (String)request.getParameter("selectedId");//나중에 없애자.
		
		if(numId==null || numId.length()==0) {
			numId = eduBoardVO.getBD_SN();
		} else {
			eduBoardVO.setBD_SN(numId);
		}
		EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
		
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", info.getBD_SN());
		request.getSession().setAttribute("se2.atchFileId", info.getBD_FILE_SE2());
		//
		
		//첨부파일 갯수 확인 후 필요시 정보 업데이트
		String _atchFileId = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getBD_FILE());
		if(info.getBD_FILE()!=null && !info.getBD_FILE().equals(_atchFileId)) {
			info.setBD_FILE(_atchFileId);
			eduBoardService.boardAdmUpdate(info);	
		}
		//

		model.addAttribute("info", info);
		
		return "eduadm/board/file/modify";
	}
	
	//교육센터  - 공지사항(관리자) 글수정 페이지 ------------------------------------------------
		@RequestMapping(value = "/eduadm/board/admnotice/modify.do")
		public String eduadm_board_admnotice_modify(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
				HttpServletRequest request, ModelMap model) throws Exception {
			
			String numId = (String)request.getParameter("selectedId");//나중에 없애자.
			
			if(numId==null || numId.length()==0) {
				numId = eduBoardVO.getBD_SN();
			} else {
				eduBoardVO.setBD_SN(numId);
			}
			EduBoardVO info = eduBoardService.boardAdmView(eduBoardVO);
			
			//스마트에디터2용 atchFileId 저장
			request.getSession().setAttribute("se2.bdsn", info.getBD_SN());
			request.getSession().setAttribute("se2.atchFileId", info.getBD_FILE_SE2());
			//
			//직급 코드 조회
			{
		  		CodeSetVO mCodeSetVO = new CodeSetVO();
		  		mCodeSetVO.setCD_MASTER_ID("CID00003");
		  		mCodeSetVO.setUSE_AT("Y");
		  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
		  		model.addAttribute("list_position_cd",list_position_cd);
			}
			
			//첨부파일 갯수 확인 후 필요시 정보 업데이트
			String _atchFileId = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getBD_FILE());
			if(info.getBD_FILE()!=null && !info.getBD_FILE().equals(_atchFileId)) {
				info.setBD_FILE(_atchFileId);
				eduBoardService.boardAdmUpdate(info);	
			}
			//

			model.addAttribute("info", info);
			
			return "eduadm/board/admnotice/modify";
		}
	
	//교육센터  - 공통 글쓰기 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/{addWebLink}/write_act.do")
	public String eduadm_board_notice_write_act(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			@PathVariable("addWebLink") String addWebLink,
			MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
		
		if(addWebLink==null || addWebLink.length()==0) { //잘못된URL접근
			model.addAttribute("page_back_cnt", "-3");
			return "/eduadm/error/page_back";
		} 
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
			
		String bd_file_se2 = eduBoardVO.getBD_FILE_SE2();
		bd_file_se2 = new PublicFileMngUtil(fileMngService, fileUtil).chkFileUpdateSe2(
				bd_file_se2, //스마트에디터 첨부파일 아이디 
				eduBoardVO.getBD_CONT() //스마트에디터 컨텐츠 내용(html)
				);
		eduBoardVO.setBD_FILE_SE2(bd_file_se2);	
		
		
		String _atchFileId = "";
		final Map<String, MultipartFile> files = multiRequest.getFileMap();			
		Map fresult = new PublicFileMngUtil(fileMngService,fileUtil).chkFileUpdate(
				files, //업로드 파일 목록
				_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
				"", //첨부파일아이디 번호(FILE_SN)
				"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
				new String[]{
					"jpg","jpge","png","gif","bmp", //image
					"mp4","avi","wmv", //video
					"hwp","doc","xls","xlsx","csv","txt","pdf","pptx", //document
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
		
		eduBoardVO.setBD_FILE(_atchFileId);
		eduBoardVO.setBD_MEMBER_ID(loginVO.getMBR_ID());
		
		if(addWebLink.equals("file")) {
			log_dscrp.append("[교육센터관리자-자료실-글쓰기]");
			eduBoardVO.setBD_ID("board015");
		} else if(addWebLink.equals("faq")) {
			log_dscrp.append("[교육센터관리자-FAQ-글쓰기]");
			eduBoardVO.setBD_ID("board003");
		}else if (addWebLink.equals("admnotice")) {
			log_dscrp.append("[교육센터관리자-관리자 페이지 공지사항-글쓰기]");
			eduBoardVO.setBD_ID("board018");
		} else  { //기본 notice
			log_dscrp.append("[교육센터관리자-공지사항-글쓰기]");
			eduBoardVO.setBD_ID("board013");
		}
		
		log_dscrp.append("[게시물 : "+eduBoardVO.getBD_TITLE()+"(일련번호:"+eduBoardVO.getBD_SN()+")]");
		
		//XSS 취약점
		EgovStringUtil esu = new EgovStringUtil();
		eduBoardVO.setBD_TITLE(esu.getHtmlStrCnvr(eduBoardVO.getBD_TITLE()));//제목
		eduBoardVO.setBD_CONT(esu.getHtmlStrCnvr(eduBoardVO.getBD_CONT()));//내용
		
		eduBoardService.boardAdmInsert(eduBoardVO);
		
		
		tbl_inf.append("ALL_BOARD_DATA_TB");

		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(eduBoardVO));
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
		
		return "redirect:/eduadm/board/"+addWebLink+"/list.do";
	}
	
	
	//교육센터  - 공통 글수정 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/{addWebLink}/modify_act.do")
	public String eduadm_board_notice_modify_act(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			@PathVariable("addWebLink") String addWebLink,
			MultipartHttpServletRequest multiRequest, HttpServletRequest request, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		
		if(addWebLink==null || addWebLink.length()==0) { //잘못된URL접근
			model.addAttribute("page_back_cnt", "-3");
			return "/eduadm/error/page_back";
		} 
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");		
		EduBoardVO chkBoardAdmVO = eduBoardService.boardAdmView(eduBoardVO);
		if(chkBoardAdmVO==null || chkBoardAdmVO.getBD_SN()==null || chkBoardAdmVO.getBD_SN().length()==0) {
			LOGGER.debug("존재하지 않는 게시물 진입..");
			model.addAttribute("page_back_cnt", "-3");
			return "/eduadm/error/page_back";
		} else {
			LOGGER.debug("정상적인 접근.");
			
			String bd_file_se2 = chkBoardAdmVO.getBD_FILE_SE2();
			bd_file_se2 = new PublicFileMngUtil(fileMngService, fileUtil).chkFileUpdateSe2(
					bd_file_se2, //스마트에디터 첨부파일 아이디 
					eduBoardVO.getBD_CONT() //스마트에디터 컨텐츠 내용(html)
					);
			eduBoardVO.setBD_FILE_SE2(bd_file_se2);	
			
			
			String _atchFileId = chkBoardAdmVO.getBD_FILE();
			PublicFileMngUtil mSealifeFileMngUtil = new PublicFileMngUtil(fileMngService,fileUtil);
			Map<String, MultipartFile> mainImgfiles = mSealifeFileMngUtil.extractinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_main_img",
					});
			Map<String, MultipartFile> atchfiles = mSealifeFileMngUtil.removeinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_main_img",
					});
			{//메인이미지
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						mainImgfiles, //업로드 파일 목록
						_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
						"0", //첨부파일아이디 번호(FILE_SN)
						"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							"mp4","avi","wmv", //video
							"hwp","doc","xls","xlsx","csv","txt","pdf","pptx", //document
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
					return "/seadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId = fresult.get("atchFileId").toString();	
				}
			}
			{//첨부파일
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						atchfiles, //업로드 파일 목록
						_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
						"", //첨부파일아이디 번호(FILE_SN)
						"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							"mp4","avi","wmv", //video
							"hwp","doc","xls","xlsx","csv","txt","pdf","pptx", //document
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
					return "/seadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId = fresult.get("atchFileId").toString();	
				}
			}
			
			eduBoardVO.setBD_FILE(_atchFileId);	
			eduBoardVO.setBD_MEMBER_ID(loginVO.getMBR_ID());
			
			//XSS 취약점
			EgovStringUtil esu = new EgovStringUtil();
			eduBoardVO.setBD_TITLE(esu.getHtmlStrCnvr(eduBoardVO.getBD_TITLE()));//제목
			eduBoardVO.setBD_CONT(esu.getHtmlStrCnvr(eduBoardVO.getBD_CONT()));//내용
			
			eduBoardService.boardAdmUpdate(eduBoardVO);
			
			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();
			tbl_inf.append("ALL_BOARD_DATA_TB");
			
			if(addWebLink.equals("file")) {
				log_dscrp.append("[교육센터관리자-자료실-글삭제]");
			} else if(addWebLink.equals("faq")) {
				log_dscrp.append("[교육센터관리자-FAQ-글삭제]");
			} else if (addWebLink.equals("admnotice")) {
				log_dscrp.append("[교육센터관리자-공지사항(관리자)-글수정]");
			} else { //기본 notice
				log_dscrp.append("[교육센터관리자-공지사항-글삭제]");
			}
			
			log_dscrp.append("[게시물 : "+chkBoardAdmVO.getBD_TITLE()+"(일련번호:"+eduBoardVO.getBD_SN()+")]");
						
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(eduBoardVO));
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
			
			return "redirect:/eduadm/board/"+addWebLink+"/list.do";
		}
		 
	}
	
	//교육센터  - 공통 글삭제 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/{addWebLink}/delete_act.do")
	public String eduadm_board_notice_delete_act(@ModelAttribute("eduBoardVO") EduBoardVO eduBoardVO, 
			@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, Model model) throws Exception {
		
		if(addWebLink==null || addWebLink.length()==0) { //잘못된URL접근
			model.addAttribute("page_back_cnt", "-3");
			return "/eduadm/error/page_back";
		} 
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		EduBoardVO chkEduBoardVO = eduBoardService.boardAdmView(eduBoardVO);
		if(chkEduBoardVO==null || chkEduBoardVO.getBD_SN()==null || chkEduBoardVO.getBD_SN().length()==0) {
			LOGGER.debug("존재하지 않는 게시물 진입..");
			model.addAttribute("page_back_cnt", "-3");
			return "/eduadm/error/page_back";
		} else {
			LOGGER.debug("정상적인 접근.");
			
			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();
			
			tbl_inf.append("ALL_BOARD_DATA_TB");
			
			if(addWebLink.equals("file")) {
				log_dscrp.append("[교육센터관리자-자료실-글삭제]");
			} else if(addWebLink.equals("faq")) {
				log_dscrp.append("[교육센터관리자-FAQ-글삭제]");
			} else if (addWebLink.equals("admnotice")) {
				log_dscrp.append("[교육센터관리자-공지사항(관리자)-글삭제]");
			} else { //기본 notice
				log_dscrp.append("[교육센터관리자-공지사항-글삭제]");
			}
			
			log_dscrp.append("[게시물 : "+chkEduBoardVO.getBD_TITLE()+"(일련번호:"+eduBoardVO.getBD_SN()+")]");
			
			String DEL_ST = chkEduBoardVO.getBD_ST();
			if(DEL_ST.equals("N")) { //완전 삭제
				log_dscrp.append("[실제데이터삭제]");
				
				eduBoardService.boardAdmRemove(chkEduBoardVO);
				if(chkEduBoardVO.getBD_FILE()!=null && chkEduBoardVO.getBD_FILE().length()!=0) {
					fileMngService.deleteAllDetailFileInfs(chkEduBoardVO.getBD_FILE());
					fileMngService.deleteAllFileInf(chkEduBoardVO.getBD_FILE());
				}
				if(chkEduBoardVO.getBD_FILE_SE2()!=null && chkEduBoardVO.getBD_FILE_SE2().length()!=0) {
					fileMngService.deleteAllDetailFileInfs(chkEduBoardVO.getBD_FILE_SE2());
					fileMngService.deleteAllFileInf(chkEduBoardVO.getBD_FILE_SE2());
				}
				
			} else {//처음 삭제
				log_dscrp.append("[1단계삭제]");
				eduBoardService.boardAdmDelete(chkEduBoardVO);
			}
			
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(eduBoardVO));
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
		return "redirect:/eduadm/board/"+addWebLink+"/list.do";
	}
	
	
	
	//교육센터  - 온라인교육신청(심사) - 선택회원일괄처리 수정 로직 ------------------------------------------------
	@RequestMapping(value="/eduadm/board/rmndr/modify_all_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_mbr_rmndr_modify_all_act(@ModelAttribute("eduMbrRemindersVO") EduMbrRemindersVO eduMbrRemindersVO,
			@RequestParam(value="chkedRMNDRIds",required=false) String chkedRMNDRIds,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
				
		StringBuilder log_msg = new StringBuilder();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-온라인교육신청(심사)-선택회원일괄처리]");
		tbl_inf.append("EDU_MBR_RMNDR_TB");
		
		if(chkedRMNDRIds != null) {
			String[] chkedHMbrIds = chkedRMNDRIds.replaceAll("\\s","").split(",");
			for(String rmndr_sn : chkedHMbrIds) {
				if(rmndr_sn==null) {
					log_msg.append("[비정상적인데이터:RMNDR_SN="+rmndr_sn+"]");
					continue;					
				}
				//검증
				EduMbrRemindersVO chkEduMbrRemindersVO = new EduMbrRemindersVO();
				chkEduMbrRemindersVO.setRMNDR_SN(rmndr_sn);
				chkEduMbrRemindersVO = eduCenterService.get_edu_mbr_reminders_info(chkEduMbrRemindersVO);
				if(chkEduMbrRemindersVO.getRMNDR_SN()==null || chkEduMbrRemindersVO.getRMNDR_SN().length()==0) {
					log_dscrp.append("[정보없음(일련번호:"+rmndr_sn+")-처리불가]");
					log_msg.append("[정보없음:HMBR_SN="+rmndr_sn+"]");
					continue;
				} else {
					eduCenterService.set_edu_mbr_reminders_lock_cmplt(chkEduMbrRemindersVO);
					log_dscrp.append("[이름:"+chkEduMbrRemindersVO.getRMNDR_MBR_NM()+"(아이디:"+chkEduMbrRemindersVO.getRMNDR_MBR_ID()+",일련번호:"+rmndr_sn+")-처리완료]");
					log_msg.append("[처리완료:RMNDR_SN="+rmndr_sn+",");
					
					LogRecordVO logtemp = new LogRecordVO();
					log_msg.append(logtemp.encodingFromObjectToJson(chkEduMbrRemindersVO)+"]");
				}
			}
			data.put("error", "0");
			data.put("msg", "선택항목에 대해 완료 처리되었습니다.");				
		} else {
			log_dscrp.append("[처리실패]");
			log_msg.append("[처리실패 null]");
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
	//교육센터  - 온라인교육신청(심사) - 신청자 삭제 ------------------------------------------------
	@RequestMapping(value="/eduadm/board/rmndr/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_mbr_rmndr_delete(@ModelAttribute("eduMbrRemindersVO") EduMbrRemindersVO eduMbrRemindersVO,
			@RequestParam(value="chkedRMNDRIds",required=false) String chkedRMNDRIds,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		LogRecordVO mEduLogRecordVO = new LogRecordVO();
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		EduMbrRemindersVO chkEduMbrRemindersVO = new EduMbrRemindersVO();
		
		log_dscrp.append("[교육센터관리자-온라인교육신청(심사)");
		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMbrRemindersVO));
		
		try {
			if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
				log_dscrp.append("[접근거부:비정상적인접근으로거부됨]");
				data.put("error", "1");
				data.put("msg", "허용된 접근이 아닙니다.");
			} else {
				
				String MASTER_MBR_ID = loginVO.getMBR_ID();
				
				if(chkedRMNDRIds!=null && chkedRMNDRIds.length()!=0) { //다중건
					log_dscrp.append("[일괄삭제요청]");
					tbl_inf.append("MBR_RMNDR_TB");
					
					String[] parseChkedESRSNIds = chkedRMNDRIds.replaceAll("\\s","").split(",");
					int totalCnt = parseChkedESRSNIds.length;
					int successCnt = 0;
					int failCnt = 0;
					for(String chkEsrSn : parseChkedESRSNIds) {
						
						eduMbrRemindersVO.setRMNDR_SN(chkEsrSn);
						chkEduMbrRemindersVO = eduCenterService.get_edu_mbr_reminders_info(eduMbrRemindersVO);
						log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduMbrRemindersVO));
						
						if(chkEduMbrRemindersVO.getRMNDR_SN()==null || chkEduMbrRemindersVO.getRMNDR_SN().length()==0) {
							log_dscrp.append("[정보없음(일련번호:"+eduMbrRemindersVO.getRMNDR_SN()+")-처리불가]");
							failCnt++;
						} else {
							chkEduMbrRemindersVO.setUPD_MBR_ID(MASTER_MBR_ID);
							log_dscrp.append("[이름:"+chkEduMbrRemindersVO.getRMNDR_MBR_NM()+"(아이디:"+chkEduMbrRemindersVO.getRMNDR_MBR_ID()+",일련번호:"+chkEduMbrRemindersVO.getRMNDR_SN()+")]");
							eduCenterService.remove_edu_mbr_reminders(chkEduMbrRemindersVO);
							successCnt++;						
						}
					}
					data.put("error", "0");
					data.put("msg", "전체 "+totalCnt+"건 중 (성공 "+successCnt+"건, 실패 "+failCnt+"건) 처리되었습니다.");
				} else {//단일건
					
					//검증
					chkEduMbrRemindersVO = eduCenterService.get_edu_mbr_reminders_info(eduMbrRemindersVO);
					log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduMbrRemindersVO));
					if(chkEduMbrRemindersVO.getRMNDR_SN()==null || chkEduMbrRemindersVO.getRMNDR_SN().length()==0) {
						data.put("error", "1");
						data.put("msg", "존재하지 않는 정보입니다.");
						log_dscrp.append("[정보없음(일련번호:"+eduMbrRemindersVO.getRMNDR_SN()+")-처리불가]");
					} else {
										
						tbl_inf.append("EDU_MBR_RMNDR_TB");
						tbl_sn.append(chkEduMbrRemindersVO.getRMNDR_SN());
						
						chkEduMbrRemindersVO.setUPD_MBR_ID(MASTER_MBR_ID);
						
						if(eduMbrRemindersVO.getUSE_ST().equals("1")) { //복구
							log_dscrp.append("-복구]");
							log_dscrp.append("[이름:"+chkEduMbrRemindersVO.getRMNDR_MBR_NM()+"(아이디:"+chkEduMbrRemindersVO.getRMNDR_MBR_ID()+",일련번호:"+chkEduMbrRemindersVO.getRMNDR_SN()+")]");
							eduCenterService.set_edu_mbr_reminders_recovery(chkEduMbrRemindersVO);
							
							data.put("error", "0");
							data.put("msg", "복구되었습니다.");
							
						} else {
							log_dscrp.append("-삭제]");
							log_dscrp.append("[이름:"+chkEduMbrRemindersVO.getRMNDR_MBR_NM()+"(아이디:"+chkEduMbrRemindersVO.getRMNDR_MBR_ID()+",일련번호:"+chkEduMbrRemindersVO.getRMNDR_SN()+")]");
							String DEL_ST = chkEduMbrRemindersVO.getDEL_ST();
							if(DEL_ST.equals("1")) { //완전 삭제
								log_dscrp.append("[실제데이터삭제]");						
								eduCenterService.remove_edu_mbr_reminders(chkEduMbrRemindersVO);
							} else {//처음 삭제
								log_dscrp.append("[1단계삭제]");
								eduCenterService.del_edu_mbr_reminders(chkEduMbrRemindersVO);			
							}
							
							data.put("error", "0");
							data.put("msg", "삭제되었습니다.");
						}
					}
				}
			}
			
		} catch(Exception e) {
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("][처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}		
			
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduMbrRemindersVO));
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
	
	//교육센터  - 온라인 교육 문자 알림 요청 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/board/esrequest/list.do")
	public Object eduadm_board_esrequest_list(boolean isExcelDownLoad, @ModelAttribute("eduAdmSmsRequstVO") EduAdmSmsRequstVO eduAdmSmsRequstVO,
			@RequestParam(value="frm_CMPLT_ST",required=false) String frm_CMPLT_ST,
			ModelMap model) throws Exception {
		
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
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_position_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_license_se_cd",list_position_cd);
		}
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		//mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		
				
		//eduMbrRemindersVO.setCMPLT_ST(frm_CMPLT_ST);
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduAdmSmsRequstVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduAdmSmsRequstVO.getPageUnit());
		paginationInfo.setPageSize(eduAdmSmsRequstVO.getPageSize());

		eduAdmSmsRequstVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduAdmSmsRequstVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduAdmSmsRequstVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduAdmSmsRequstVO> list = eduCenterService.get_edu_sms_request_list(eduAdmSmsRequstVO);		
		int totCnt = eduCenterService.get_edu_sms_request_list_totcnt(eduAdmSmsRequstVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduAdmSmsRequstVO.getSearchCondition());
		model.addAttribute("searchOrderBy", eduAdmSmsRequstVO.getSearchOrderBy());
		//model.addAttribute("searchKeyword",eduAdmSmsRequstVO.getSearchCondition().length()==0?"":eduAdmSmsRequstVO.getSearchKeyword());
		model.addAttribute("searchKeyword",eduAdmSmsRequstVO.getSearchKeyword());
		//model.addAttribute("frm_CMPLT_ST",frm_CMPLT_ST);
		if(isExcelDownLoad) {
			return (ModelMap)model;
		} else {
			return "eduadm/board/esrequest/list";
		}	
	}
	
	//교육센터  - 온라인 교육 문자 알림 요청 리스트 - 신청자 삭제 ------------------------------------------------
	@RequestMapping(value="/eduadm/board/esrequest/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_eduadm_board_esrequest_delete(@ModelAttribute("eduAdmSmsRequstVO") EduAdmSmsRequstVO eduAdmSmsRequstVO,
			@RequestParam(value="chkedESRSNIds",required=false) String chkedESRSNIds,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {			
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		LogRecordVO mEduLogRecordVO = new LogRecordVO();
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();

		log_dscrp.append("[교육센터관리자-온라인 교육 문자 알림 요청 신청자 삭제]");
		log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduAdmSmsRequstVO));
		
		try {
			if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
				log_dscrp.append("[접근거부:비정상적인접근으로거부됨]");
				data.put("error", "1");
				data.put("msg", "허용된 접근이 아닙니다.");
			} else {
				
				String MASTER_MBR_ID = loginVO.getMBR_ID();
			
				if(chkedESRSNIds!=null && chkedESRSNIds.length()!=0) { //다중건
					log_dscrp.append("[일괄삭제요청]");
					tbl_inf.append("EDU_SMS_REQUST_TB");
					
					String[] parseChkedESRSNIds = chkedESRSNIds.replaceAll("\\s","").split(",");
					int totalCnt = parseChkedESRSNIds.length;
					int successCnt = 0;
					int failCnt = 0;
					for(String chkEsrSn : parseChkedESRSNIds) {
						eduAdmSmsRequstVO.setESR_SN(chkEsrSn);
						EduAdmSmsRequstVO chkEduAdmSmsRequstVO = eduCenterService.get_edu_sms_request_info(eduAdmSmsRequstVO);
						log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduAdmSmsRequstVO));
						if(chkEduAdmSmsRequstVO.getESR_SN()==null || chkEduAdmSmsRequstVO.getESR_SN().length()==0) {
							log_dscrp.append("[정보없음(일련번호:"+eduAdmSmsRequstVO.getESR_SN()+")-처리불가]");
							failCnt++;
						} else {
							chkEduAdmSmsRequstVO.setESR_UPD_MBR_ID(MASTER_MBR_ID);
							log_dscrp.append("[이름:"+chkEduAdmSmsRequstVO.getESR_MBR_NM()+"(아이디:"+chkEduAdmSmsRequstVO.getESR_MBR_ID()+",일련번호:"+chkEduAdmSmsRequstVO.getESR_SN()+")]");
							eduCenterService.remove_edu_sms_request(chkEduAdmSmsRequstVO);
							successCnt++;						
						}
					}
					data.put("error", "0");
					data.put("msg", "전체 "+totalCnt+"건 중 (성공 "+successCnt+"건, 실패 "+failCnt+"건) 처리되었습니다.");
				} else {//단일건
					//검증
					EduAdmSmsRequstVO chkEduAdmSmsRequstVO = eduCenterService.get_edu_sms_request_info(eduAdmSmsRequstVO);
					log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduAdmSmsRequstVO));
					if(chkEduAdmSmsRequstVO.getESR_SN()==null || chkEduAdmSmsRequstVO.getESR_SN().length()==0) {
						data.put("error", "1");
						data.put("msg", "존재하지 않는 정보입니다.");
						log_dscrp.append("[정보없음(일련번호:"+eduAdmSmsRequstVO.getESR_SN()+")-처리불가]");
					} else {
										
						tbl_inf.append("EDU_SMS_REQUST_TB");
						tbl_sn.append(chkEduAdmSmsRequstVO.getESR_SN());
						
						chkEduAdmSmsRequstVO.setESR_UPD_MBR_ID(MASTER_MBR_ID);
						
						if(eduAdmSmsRequstVO.getESR_USE_ST().equals("1")) { //복구
							log_dscrp.append("[복구]");
							log_dscrp.append("[이름:"+chkEduAdmSmsRequstVO.getESR_MBR_NM()+"(아이디:"+chkEduAdmSmsRequstVO.getESR_MBR_ID()+",일련번호:"+chkEduAdmSmsRequstVO.getESR_SN()+")]");
							eduCenterService.set_edu_sms_request_recovery(chkEduAdmSmsRequstVO);
							
							data.put("error", "0");
							data.put("msg", "복구되었습니다.");
							
						} else {
							log_dscrp.append("[삭제]");
							log_dscrp.append("[이름:"+chkEduAdmSmsRequstVO.getESR_MBR_NM()+"(아이디:"+chkEduAdmSmsRequstVO.getESR_MBR_ID()+",일련번호:"+chkEduAdmSmsRequstVO.getESR_SN()+")]");
							String DEL_ST = chkEduAdmSmsRequstVO.getESR_DEL_ST();
							if(DEL_ST.equals("1")) { //완전 삭제
								log_dscrp.append("[실제데이터삭제]");						
								eduCenterService.remove_edu_sms_request(chkEduAdmSmsRequstVO);
							} else {//처음 삭제
								log_dscrp.append("[1단계삭제]");
								eduCenterService.del_edu_sms_request(chkEduAdmSmsRequstVO);			
							}
							
							data.put("error", "0");
							data.put("msg", "삭제되었습니다.");
						}
						
					}
				}
			}
		} catch(Exception e) {
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("][처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
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

	
	//본인명의 휴대폰 미소지자 정보 변경 신청서
	@RequestMapping(value = "/eduadm/board/hpchange/list.do")
	public String eduadm_board_hpchange_list(@ModelAttribute("eduAdmMbrHpChngVO") EduAdmMbrHpChngVO eduAdmMbrHpChngVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduAdmMbrHpChngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduAdmMbrHpChngVO.getPageUnit());
		paginationInfo.setPageSize(eduAdmMbrHpChngVO.getPageSize());

		eduAdmMbrHpChngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduAdmMbrHpChngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduAdmMbrHpChngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduAdmMbrHpChngVO> list = eduCenterService.get_mbr_hp_chng_list(eduAdmMbrHpChngVO);		
		int totCnt = eduCenterService.get_mbr_hp_chng_list_totcnt(eduAdmMbrHpChngVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		//취약점 대응을 위한 처리
		for(EduAdmMbrHpChngVO item : list) {
			item.setMBR_SCRTY_KEY(EgovFileScrty.security(item.getMHC_MBR_ID(), loginVO.getMBR_ID()));
		}
		//End of 취약점 대응을 위한 처리
		
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduAdmMbrHpChngVO.getSearchCondition());
		model.addAttribute("searchOrderBy", eduAdmMbrHpChngVO.getSearchOrderBy());
		model.addAttribute("searchKeyword",eduAdmMbrHpChngVO.getSearchKeyword());
		
		return "eduadm/board/hpchange/list";
	}
	
	//법인사업장 교욱책임자 지정 확인서
	@RequestMapping(value = "/eduadm/board/cpredu/list.do")
	public String eduadm_board_cpredu_list(@ModelAttribute("eduAdmCprBplcVO") EduAdmCprBplcVO eduAdmCprBplcVO,
			ModelMap model) throws Exception {
		
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduAdmCprBplcVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduAdmCprBplcVO.getPageUnit());
		paginationInfo.setPageSize(eduAdmCprBplcVO.getPageSize());

		eduAdmCprBplcVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduAdmCprBplcVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduAdmCprBplcVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduCprBplcVO> list = eduCenterService.get_cpr_bplc_list(eduAdmCprBplcVO);
		int totCnt = eduCenterService.get_cpr_bplc_list_totcnt(eduAdmCprBplcVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduAdmCprBplcVO.getSearchCondition());
		model.addAttribute("searchOrderBy", eduAdmCprBplcVO.getSearchOrderBy());
		model.addAttribute("searchKeyword",eduAdmCprBplcVO.getSearchKeyword());
		
		return "eduadm/board/cpredu/list";
	}
	
	//법인사업장 교욱책임자 지정 확인서
	@RequestMapping(value = "/eduadm/board/cpredu/write.do")
	public ModelAndView eduadm_board_cpredu_modify(@ModelAttribute("eduAdmCprBplcVO") EduAdmCprBplcVO eduAdmCprBplcVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		ModelAndView mModelAndView = new ModelAndView();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			mModelAndView.setViewName("eduadm/error/page_400");	
		} else {
			EduAdmCprBplcVO info_cpr = eduCenterService.get_edu_cpr_bplc_info(eduAdmCprBplcVO);
			if(info_cpr==null || info_cpr.getECB_SN()==null || info_cpr.getECB_SN().length()==0) {
				//정보없음
				mModelAndView.setViewName("eduadm/error/page_400");	
			} else {
				
		  		//지역 코드 조회
				{
			  		CodeSetVO mCodeSetVO = new CodeSetVO();
			  		mCodeSetVO.setCD_MASTER_ID("CID00004");
			  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
			  		mModelAndView.addObject("list_address_cd",list_address_cd);
				}
				
				mModelAndView.addObject("info_cpr", info_cpr);
				mModelAndView.setViewName("eduadm/board/cpredu/write");
			}
		}
		
		return mModelAndView;
	}
	
	//법인사업장 교욱책임자 지정 확인서
	@RequestMapping(value = "/eduadm/board/cpredu/write_act.do")
	public @ResponseBody String eduadm_board_cpredu_modify_act(@ModelAttribute("eduAdmCprBplcVO") EduAdmCprBplcVO eduAdmCprBplcVO,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-법인사업장 교육책임자-검증]");
		tbl_inf.append("MBR_TB");
		
		log_dscrp.append("[이름:"+eduAdmCprBplcVO.getECB_CPR_NM()+")]");
		
		/*int is_id_cnt = eduMemberService.get_id_search(eduMemberVO);
		if(is_id_cnt > 0) {
			log_dscrp.append("|등록실패:이미 등록 된 아이디]");
			
			data.put("error", "1");
			data.put("msg", "이미 등록 된 아이디 입니다.");
			
		} else {*/
		try {
			
			eduMemberVO.setMBR_NM(eduAdmCprBplcVO.getECB_CPR_NM());
			eduMemberVO.setMBR_BIRTH(eduAdmCprBplcVO.getECB_CPR_BRTHDY());
			eduMemberVO.setMBR_HP(eduAdmCprBplcVO.getECB_CPR_HP());
			
			List<EduMemberVO> list_overlap = eduMemberService.get_edu_member_check_overlap_list(eduMemberVO);
			if(list_overlap!=null && list_overlap.size() > 0 ) { //중복의심발생
				
				data.put("error", "2");
				data.put("msg", "법인대표자 정보와 유사한 정보를 가진 회원이 존재하여 확인이 필요합니다.");		
				
				log_dscrp.append("|검증하기:유사정보회원존재");
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
				mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
				data.put("rawdata", mapper.writeValueAsString(list_overlap));
			} else {
				data.put("error", "3");
				data.put("msg", "법인대표자 정보와 유사한 정보를 가진 회원이 존재하지 않습니다.");
				
				log_dscrp.append("|검증하기:유사정보회원 존재안함");
			}
		}catch(Exception e) {
			LOGGER.debug("[fail load data] "+e.toString());				
			log_dscrp.append("|검증실패:에러발생]["+eduMemberVO.getMBR_ID()+"]");				
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

	//관리자(교육센터) 법인사업장 교육책임자 - 검증 - 회원수정 로직 ----------------------------------------------
	@RequestMapping(value="/eduadm/board/cpredu/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_member_modify_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@ModelAttribute("eduAdmCprBplcVO") EduAdmCprBplcVO eduAdmCprBplcVO, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-법인사업장 교육책임자-회원정보수정]");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
		
		JSONObject data = new JSONObject();
		//검증 
		EduMemberVO chkEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
		if(chkEduMemberVO.getMBR_ID()==null || chkEduMemberVO.getMBR_ID().length()==0) {
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");
			
			log_dscrp.append("[존재하지 않는 회원정보를 요청함]");
		} else {
			try {
					
				log_dscrp.append("[이름:"+chkEduMemberVO.getMBR_NM()+"(아이디:"+chkEduMemberVO.getMBR_ID()+")]");
				eduMemberVO.setLOG_UPD_MSG("법인사업장 교육책임자 업데이트 건");
				
				if(chkEduMemberVO.getMBR_ST().equals("N")) {
					log_dscrp.append("|비활성화상태의 회원이므로 활성화 처리함");
					eduMemberVO.setMBR_ST("Y");
				}
				if(eduMemberVO.getREG_TYPE_CD()==null || eduMemberVO.getREG_TYPE_CD().length()==0) {
					eduMemberVO.setREG_TYPE_CD(MASTER_MBR_POSITION_CD);	
				}
				if(eduMemberVO.getAddConfirmSubmit()!=null && eduMemberVO.getAddConfirmSubmit().equals("Y") 
				&& (eduMemberVO.getMBR_ST()==null || eduMemberVO.getMBR_ST().length()==0)) {
					eduMemberVO.setMBR_ST("Y");
				}
				
				//교육책임자 정보로 변경
				eduMemberVO.setMBR_NM(eduAdmCprBplcVO.getECB_EDU_NM());
				eduMemberVO.setMBR_BIRTH(eduAdmCprBplcVO.getECB_EDU_BRTHDY());
				eduMemberVO.setMBR_HP(eduAdmCprBplcVO.getECB_EDU_HP());
				eduMemberVO.setMBR_ZIPCD(eduAdmCprBplcVO.getECB_EDU_ZIP());
				eduMemberVO.setMBR_ADDR1(eduAdmCprBplcVO.getECB_EDU_ADDR1());
				eduMemberVO.setMBR_ADDR2(eduAdmCprBplcVO.getECB_EDU_ADDR2());
				eduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
				
				eduMemberService.set_edu_member_mod(eduMemberVO);
				
				//현재정보로 교체한 일자,아이디
				eduAdmCprBplcVO.setECB_UPD_ID(MASTER_MBR_ID);
				eduAdmCprBplcVO.setECB_SN(request.getParameter("ECB_SN"));
				eduCenterService.set_edu_cpr_bplc(eduAdmCprBplcVO);
				
				//사용자사유로그기록
				{
					EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
					logRecordService.set_log_mbr_mod_data("MBR_TB","수정",eduMemberVO.getLOG_UPD_MSG(),realEduMemberVO.getMBR_ID(),realEduMemberVO.getMBR_NM(),chkEduMemberVO,realEduMemberVO,loginVO,request);
				}
				log_dscrp.append("|회원정보수정완료");
				
				
				tbl_inf.append("MBR_TB, EDU_CPR_BPLC_TB");
				tbl_sn.append(eduMemberVO.getMBR_SN() + ", " + request.getParameter("ECB_SN"));

				data.put("error", "0");
				data.put("msg", "회원 정보가 수정되었습니다.");				
				
			} catch(Exception e) {
				LOGGER.debug("[fail process] "+e.toString());				
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");				
				log_dscrp.append("[에러발생|아이디:"+chkEduMemberVO.getMBR_ID()+"]");	
			}
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
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
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



	
		

