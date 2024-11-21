package egovframework.adm.main.web;

import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.adm.main.service.AdmMainService;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.error.service.ErrorService;
import egovframework.all.error.service.ErrorVO;
import egovframework.all.log.service.LogMemberModifyVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class AdmMainController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(AdmMainController.class);
	
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	@Resource(name = "errorService")
	private ErrorService errorService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "admMainService")
	private AdmMainService admMainService;
	
	
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	
	
	//관리자 숨겨진 처리 구간..
	@RequestMapping(value = "/adm/masterDummyExcute.do" )
	public Object adm_master_dummy_excute(@RequestParam(value="master",required=true) String master, @RequestParam(value="id",required=false) String id, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		boolean isAllow = true;
		String returnUrl = "";
		StringBuilder log_dscrp = new StringBuilder();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_LV_ID()==null || !loginVO.getMBR_LV_ID().equals("1")) {
			LOGGER.debug("접근이 불가능한 계정이 시도함...");
			log_dscrp.append("접근이 불가능한 계정이 시도하여 거부됨..");
			returnUrl = "adm/error/page_400";
			isAllow = false;
    	}
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_NM = loginVO.getMBR_NM();
    	String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
    	log_dscrp.append("[이름:"+MASTER_MBR_NM+"(아이디"+MASTER_MBR_ID+")]");
    	if(master==null || !master.equals("ok")) {
    		LOGGER.debug("비정상적인 접근을 시도함...");
			log_dscrp.append("비정상적인 접근을 시도하여 거부됨..");
			returnUrl = "adm/error/page_400";
			isAllow = false;
    	}
		if(isAllow) {
    		try {
    			StringBuilder resultStr = new StringBuilder();
    			resultStr.append("[기능처리시작]\n");
    			EduMemberVO eduMemberVO = new EduMemberVO();
    			eduMemberVO.setNotUsedPagination(true);
    			/*List<EduMemberVO> list =  eduMemberService.get_edu_member_master_list(eduMemberVO);
    			for(EduMemberVO item : list) {
    				if(item.getMBR_SN().equals("1") || item.getMBR_SN().equals("2") || item.getMBR_SN().equals("3")) {
    					
    				} else {
	    				//String updsql = " UPDATE MBR_TB SET MBR_PWD = '"+EgovFileScrty.encryptPassword(item.getMBR_DSCRP(), item.getMBR_ID())+"' WHERE MBR_ID = '"+item.getMBR_ID()+"';";
    					String updsql = " UPDATE MBR_TB SET MBR_PWD = '"+EgovFileScrty.encryptPassword(item.getMBR_ID(), item.getMBR_ID())+"' WHERE MBR_ID = '"+item.getMBR_ID()+"';";
	    				resultStr.append(updsql+"\n");
    				}
    			}*/ 
    			if(id!=null && id.length()!=0) { 
	    			EgovMap mEgovMap = new EgovMap();
	    			mEgovMap.put("sqlmap_id", id);
	    			admMainService.adm_master_dummy_excute(mEgovMap);
    			}
    			resultStr.append("[기능처리완료]\n");
    			response.setContentType("text/html; charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			out.println(resultStr.toString());
    			out.flush();    			
    			returnUrl = null;	
    		} catch(Exception e) {
    			returnUrl = "adm/error/page_400";
    		}
		}
    	try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG("");
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF("");
			mEduLogRecordVO.setTBL_SN("");
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}		    	
		return returnUrl;
	}
	
	
	

	//관리자(종합) 메인페이지 - 메인관리자 로그인은 seadm member 에서 처리
	@RequestMapping(value = "/adm/index.do" )
	public String adm_index(HttpServletRequest request, ModelMap model) throws Exception {
		//return "adm/main/index";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
    	if(loginVO!=null) {
	    	return "redirect:/seadm/index.do";
    	}
		return "redirect:/seadm/index.do";
	}
	
	
	//관리자(종합) 장시간 미사용으로 강제 로그아웃  ------------------------------------------------
    @RequestMapping(value="/adm/lock/memberLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
 
    	StringBuilder log_dscrp = new StringBuilder();
    	StringBuilder tbl_inf = new StringBuilder();
    	StringBuilder tbl_sn = new StringBuilder();
    	log_dscrp.append("[관리자-장시간 미사용으로 인한-강제 로그아웃]");
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
		}
    	request.getSession().setAttribute("LoginVO", null);
    	return "redirect:/adm/index.do";
    }
	
	
	//관리자(교육센터) 접근권한없음 ------------------------------------------------
	@RequestMapping(value = "/adm/error/unauth.do")
	public String adm_error_unauth(HttpServletRequest request, ModelMap model) throws Exception {
		return "adm/error/unauth";
	}
		
	//관리자(공통) 시스템 로그기록 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/log/listSys{addWebLink}.do")
	public Object adm_log_list_sys(boolean isExcelDownLoad, @ModelAttribute("errorVO") ErrorVO errorVO,
			@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, ModelMap model) throws Exception {


		/** EgovPropertyService */
		//logRecordVO.setPageUnit(propertiesService.getInt("pageUnit"));
		//logRecordVO.setPageSize(propertiesService.getInt("pageSize")); 

		errorVO.setERR_SERVLET_NM("action");
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(errorVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(errorVO.getPageUnit());
		paginationInfo.setPageSize(errorVO.getPageSize());
		
		errorVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		errorVO.setLastIndex(paginationInfo.getLastRecordIndex());
		errorVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		
		if(isExcelDownLoad) {
			errorVO.setNotUsedPagination(true);
		}
		
		List<ErrorVO> list = errorService.get_error_list(errorVO);		
		int totCnt = errorService.get_error_list_totcnt(errorVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchKeyword",errorVO.getSearchKeyword());
		model.addAttribute("searchStrDate",errorVO.getSearchStrDate());
		model.addAttribute("searchEndDate",errorVO.getSearchEndDate());
		model.addAttribute("addWebLink",addWebLink);
		if(isExcelDownLoad) {
			return (ModelMap)model;
		} else {
			return "adm/log/list_sys";
		}
	}
	
	//관리자(공통) 접속자 로그기록 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/log/listRec{addWebLink}.do")
	public Object adm_log_list_rec(boolean isExcelDownLoad, @ModelAttribute("logRecordVO") LogRecordVO logRecordVO,
			@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
				
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(logRecordVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(logRecordVO.getPageUnit());
		paginationInfo.setPageSize(logRecordVO.getPageSize());

		logRecordVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		logRecordVO.setLastIndex(paginationInfo.getLastRecordIndex());
		logRecordVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	
		
		if(isExcelDownLoad) {
			logRecordVO.setNotUsedPagination(true);
		}
		String currentDate = mPublicUtils.currentTime("yyyy-MM-dd");
		String currentMonth = mPublicUtils.currentTime("yyyy-MM");
		if(logRecordVO.getSearchStrDate() == null || logRecordVO.getSearchStrDate().equals("")){
			logRecordVO.setSearchStrDate(currentMonth+"-01");
		}
		if(logRecordVO.getSearchEndDate() == null || logRecordVO.getSearchEndDate().equals("")){
			logRecordVO.setSearchEndDate(currentDate);
		}
		model.addAttribute("searchStrDate",logRecordVO.getSearchStrDate());
		model.addAttribute("searchEndDate",logRecordVO.getSearchEndDate());
		logRecordVO.setSearchStrDate(logRecordVO.getSearchStrDate() + " 00:00:00");
		logRecordVO.setSearchEndDate(logRecordVO.getSearchEndDate() + " 23:59:59");
				
		List<LogRecordVO> list = logRecordService.get_log_list(logRecordVO);		
		int totCnt = logRecordService.get_log_list_totcnt(logRecordVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchKeyword",logRecordVO.getSearchKeyword());
		model.addAttribute("addWebLink",addWebLink);
		if(isExcelDownLoad) {
			return (ModelMap)model;
		} else {
			return "adm/log/list_rec";
		}
	}
	
	//관리자(공통) 회원정보수정 로그기록 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/log/listMbrMod{addWebLink}.do")
	public Object adm_log_list_mbr_mod(boolean isExcelDownLoad, @ModelAttribute("logMemberModifyVO") LogMemberModifyVO mLogMemberModifyVO,
			@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, ModelMap model) throws Exception {
		 
				
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mLogMemberModifyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mLogMemberModifyVO.getPageUnit());
		paginationInfo.setPageSize(mLogMemberModifyVO.getPageSize());

		mLogMemberModifyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mLogMemberModifyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mLogMemberModifyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	
		

		if(isExcelDownLoad) {
			mLogMemberModifyVO.setNotUsedPagination(true);
		}
		
		List<LogMemberModifyVO> list = logRecordService.get_log_mbr_mod_list(mLogMemberModifyVO);		
		int totCnt = logRecordService.get_log_mbr_mod_list_totcnt(mLogMemberModifyVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchKeyword",mLogMemberModifyVO.getSearchKeyword());
		model.addAttribute("searchStrDate",mLogMemberModifyVO.getSearchStrDate());
		model.addAttribute("searchEndDate",mLogMemberModifyVO.getSearchEndDate());
		model.addAttribute("addWebLink",addWebLink);
		
		//활성중인 코드값	
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setUSE_AT("Y");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_all_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_all_cd",list_all_cd);
		}
		if(isExcelDownLoad) {
			return (ModelMap)model;
		} else {
			return "adm/log/list_mbr_mod";
		}
	}
		
	//관리자(공통) 회원정보수정 내역정보 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/log/viewMbrMod.do")
	public Object ajax_adm_log_list_mbr_mod_view_detail(@ModelAttribute("logMemberModifyVO") LogMemberModifyVO mLogMemberModifyVO,
		HttpServletRequest request, ModelMap model) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		LogMemberModifyVO info = logRecordService.get_log_mbr_mod_view_detail(mLogMemberModifyVO);		
		Map<String, String> info_old = new HashMap<String, String>();
		Map<String, String> info_new = new HashMap<String, String>();
		if(info.getOLD_DATA()!=null && info.getOLD_DATA().length()!=0) info_old = mapper.readValue(info.getOLD_DATA(), Map.class);
		if(info.getNEW_DATA()!=null && info.getNEW_DATA().length()!=0) info_new = mapper.readValue(info.getNEW_DATA(), Map.class);
		
		//활성중인 코드값	
		{
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setUSE_AT("Y");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_all_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_all_cd",list_all_cd);
		}
		
		model.addAttribute("info",info);
		model.addAttribute("info_old",info_old);
		model.addAttribute("info_new",info_new);	
		
		return "adm/log/view_mbr_mod_detail";
		
	}
	//관리자(공통) 회원정보수정 로그기록 ajax 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/log/ajaxListMbrMod.do")
	public Object ajax_adm_log_list_mbr_mod_list(@ModelAttribute("logMemberModifyVO") LogMemberModifyVO mLogMemberModifyVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		 				
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mLogMemberModifyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mLogMemberModifyVO.getPageUnit());
		paginationInfo.setPageSize(mLogMemberModifyVO.getPageSize());

		mLogMemberModifyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mLogMemberModifyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mLogMemberModifyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	
		
		List<LogMemberModifyVO> list = logRecordService.get_log_mbr_mod_list(mLogMemberModifyVO);		
		int totCnt = logRecordService.get_log_mbr_mod_list_totcnt(mLogMemberModifyVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("pageUnit",mLogMemberModifyVO.getPageUnit());
		model.addAttribute("MBR_ID",mLogMemberModifyVO.getMBR_ID());
		
		return "adm/log/ajax_list_mbr_mod";
	}
	
	
	//관리자(공통) - 카테고리 관리 매니저 ------------------------------------------------
	@RequestMapping(value = "/adm/category/manager.do")
	public ModelAndView ajax_seadm_board_category_manager (@RequestParam(value="CD_MASTER_ID", required = false)String CD_MASTER_ID,
			@RequestParam(value="TITLE", required = false)String TITLE,
			ModelMap model) throws Exception {
		
		ModelAndView mModelAndView = new ModelAndView();
		if(CD_MASTER_ID==null || CD_MASTER_ID.length()==0) {
			TITLE = "";
			CD_MASTER_ID = "";
			mModelAndView.setViewName("/adm/error/modal_400");
		} else {			
			CodeSetVO codeSetVO = new CodeSetVO();
			codeSetVO.setCD_MASTER_ID(CD_MASTER_ID);
			List<CodeSetVO> list_category = codeSetService.get_codeset_list(codeSetVO);
			mModelAndView.addObject("list_category",list_category);
			if(CD_MASTER_ID.equals("CID00010")) {
				mModelAndView.setViewName("/adm/category/category_file_manager_modal");
			} else {
				mModelAndView.setViewName("/adm/error/modal_400");	
			}
		}
		
		mModelAndView.addObject("TITLE",TITLE);
		mModelAndView.addObject("CD_MASTER_ID",CD_MASTER_ID);
		
		return mModelAndView;
	}
	
	//관리자(공통) - 카테고리 관리 매니저 처리부 ------------------------------------------------
	@RequestMapping(value = "/adm/category/manager/update_act.do")
	public String ajax_seadm_board_category_manager_update_act(@RequestParam(value="CD_MASTER_ID", required = false) String CD_MASTER_ID,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		PublicUtils mPublicUtils = new PublicUtils();
		
		StringBuilder log_msg = new StringBuilder();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		JSONObject data = new JSONObject();
		if(CD_MASTER_ID==null || CD_MASTER_ID.length()==0) {
			data.put("error", "1");
			data.put("msg", "비정상적인 접근입니다.");
			log_msg.append("[비정상적인 접근으로 거부됨]");
		} else {		
						
			if(CD_MASTER_ID.equals("CID00010")) {
				for(int i=1; i<=5; i++) {
					String sysVal = (String) request.getParameter("SYS0000"+i);
					CodeSetVO updateCodeSetVO = new CodeSetVO();
					updateCodeSetVO.setCD_MASTER_ID(CD_MASTER_ID);
					updateCodeSetVO.setCD_ID("SYS0000"+i);
					updateCodeSetVO.setCD_DES(sysVal);							
					codeSetService.set_codeset_mod(updateCodeSetVO);
				}
				
				log_msg.append(".처리완료");
				
				data.put("error", "0");
				data.put("msg", "정상적으로 처리 되었습니다.");
				
			} else {
				log_msg.append(".처리할 정보가 부족함");
				
				data.put("error", "1");
				data.put("msg", "처리 가능한 정보가 없어 실패하였습니다.");
			}
			
		}
		log_dscrp.append("[관리자-카테고리관리매니저-적용하기]");
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


