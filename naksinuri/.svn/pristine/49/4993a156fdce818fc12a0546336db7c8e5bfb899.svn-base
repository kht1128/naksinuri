package egovframework.educenter.board.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import egovframework.all.log.service.LogRecordService;
import egovframework.all.login.service.LoginVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.educenter.board.service.EduCenterBoardService;
import egovframework.educenter.board.service.EduCenterBoardVO;
import egovframework.educenter.myhistory.service.MyHistoryService;
import egovframework.educenter.service.EduCenterMainService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.seadm.analytics.service.AnalyticsAdmService;
import egovframework.seadm.analytics.service.AnalyticsAdmVO;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduCenterBoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterBoardController.class);
	
	/** LogRecordService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	/** EduCenterMainService */
	@Resource(name = "eduCenterMainService")
	private EduCenterMainService eduCenterMainService;
	
	/** MyHistoryService */
	@Resource(name = "myHistoryService")
	private MyHistoryService myHistoryService;
	
	/** BoardService */
	@Resource(name = "eduCenterBoardService")
	private EduCenterBoardService eduCenterBoardService;
	
	/** EgovEducenterService */
	@Resource(name = "eduCenterService")
	private EduCenterService eduCenterService;
	

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "AnalyticsAdmService")
	private AnalyticsAdmService analyticsAdmService;
		
	
	//교육센터-커뮤니티-공지사항 목록  ------------------------------------------------
	@RequestMapping(value = "/educenter/board/notice/list.do")
	public String educenter_board_notice(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		/** EgovPropertyService */
		eduCenterBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduCenterBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduCenterBoardVO.setBD_ID("board013");//교육센터 공지사항
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterBoardVO.getPageSize());

		eduCenterBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduCenterBoardVO> list = eduCenterBoardService.userBoardList(eduCenterBoardVO);		
		int totCnt = eduCenterBoardService.userBoardListTotCnt(eduCenterBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduCenterBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduCenterBoardVO.getSearchCondition().length()==0?"":eduCenterBoardVO.getSearchKeyword());
		
		//상단고정게시물 가져오기
		eduCenterBoardVO.setBD_TOP_FIX_ST("Y");
		eduCenterBoardVO.setNotUsedPagination(true);
		model.addAttribute("list_fixed", eduCenterBoardService.userBoardList(eduCenterBoardVO));
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-공지사항-목록");
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
		
		return "educenter/board/notice/list";
	}
	
	//교육센터-커뮤니티-공지사항 목록 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/board/notice/list.do")
	public String educenter_board_notice_mobile(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		/** EgovPropertyService */
		eduCenterBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduCenterBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduCenterBoardVO.setBD_ID("board013");//교육센터 공지사항
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterBoardVO.getPageSize());

		eduCenterBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduCenterBoardVO> list = eduCenterBoardService.userBoardList(eduCenterBoardVO);		
		int totCnt = eduCenterBoardService.userBoardListTotCnt(eduCenterBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduCenterBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduCenterBoardVO.getSearchCondition().length()==0?"":eduCenterBoardVO.getSearchKeyword());
		
		//상단고정게시물 가져오기
		eduCenterBoardVO.setBD_TOP_FIX_ST("Y");
		eduCenterBoardVO.setNotUsedPagination(true);
		model.addAttribute("list_fixed", eduCenterBoardService.userBoardList(eduCenterBoardVO));
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-공지사항-목록");
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
		
		return "educenter/mobile/board/notice/list";
	}
	
	

	//교육센터-커뮤니티-공지사항 뷰 페이지 ------------------------------------------------
	@RequestMapping(value = "/educenter/board/notice/view.do")
	public String educenter_board_notice_view(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO, 
			HttpServletRequest request, Model model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		eduCenterBoardVO.setBD_ID("board013");
		EduCenterBoardVO info = eduCenterBoardService.userBoardView(eduCenterBoardVO);
		if(info==null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-공지사항-상세페이지");
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
		
		return "educenter/board/notice/view";
	}
	
	//교육센터-커뮤니티-공지사항 뷰 페이지 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/board/notice/view.do")
	public String educenter_board_notice_view_mobile(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO, 
			HttpServletRequest request, Model model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		eduCenterBoardVO.setBD_ID("board013");
		EduCenterBoardVO info = eduCenterBoardService.userBoardView(eduCenterBoardVO);
		if(info==null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-공지사항-상세페이지");
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
		
		return "educenter/mobile/board/notice/view";
	}
	
	

	//교육센터-커뮤니티-자료실 목록  ------------------------------------------------
	@RequestMapping(value = "/educenter/board/file/list.do")
	public String educenter_board_file(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		/** EgovPropertyService */
		eduCenterBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduCenterBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduCenterBoardVO.setBD_ID("board015");
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterBoardVO.getPageSize());

		eduCenterBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduCenterBoardVO> list = eduCenterBoardService.userBoardList(eduCenterBoardVO);		
		int totCnt = eduCenterBoardService.userBoardListTotCnt(eduCenterBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduCenterBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduCenterBoardVO.getSearchCondition().length()==0?"":eduCenterBoardVO.getSearchKeyword());
		
		//상단고정게시물 가져오기
		eduCenterBoardVO.setBD_TOP_FIX_ST("Y");
		eduCenterBoardVO.setNotUsedPagination(true);
		model.addAttribute("list_fixed", eduCenterBoardService.userBoardList(eduCenterBoardVO));
			
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-자료실-목록");
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
		
		return "educenter/board/file/list";
	}
	
	//교육센터-커뮤니티-자료실 목록 -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/board/file/list.do")
	public String educenter_board_file_mobile(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		/** EgovPropertyService */
		eduCenterBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduCenterBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduCenterBoardVO.setBD_ID("board015");
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterBoardVO.getPageSize());

		eduCenterBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduCenterBoardVO> list = eduCenterBoardService.userBoardList(eduCenterBoardVO);		
		int totCnt = eduCenterBoardService.userBoardListTotCnt(eduCenterBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduCenterBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduCenterBoardVO.getSearchCondition().length()==0?"":eduCenterBoardVO.getSearchKeyword());
		
		//상단고정게시물 가져오기
		eduCenterBoardVO.setBD_TOP_FIX_ST("Y");
		eduCenterBoardVO.setNotUsedPagination(true);
		model.addAttribute("list_fixed", eduCenterBoardService.userBoardList(eduCenterBoardVO));
				
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-자료실-목록");
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
		
		return "educenter/mobile/board/file/list";
	}
	
	

	//교육센터-커뮤니티-자료실 뷰 페이지 ------------------------------------------------
	@RequestMapping(value = "/educenter/board/file/view.do")
	public String educenter_board_file_view(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO, 
			HttpServletRequest request, Model model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		eduCenterBoardVO.setBD_ID("board015");
		EduCenterBoardVO info = eduCenterBoardService.userBoardView(eduCenterBoardVO);
		if(info==null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-자료실-상세페이지");
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
		
		return "educenter/board/file/view";
	}
	
	//교육센터-커뮤니티-자료실 뷰 페이지 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/board/file/view.do")
	public String educenter_board_file_view_mobile(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO, 
			HttpServletRequest request, Model model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		eduCenterBoardVO.setBD_ID("board015");
		EduCenterBoardVO info = eduCenterBoardService.userBoardView(eduCenterBoardVO);
		if(info==null || info.getBD_SN()==null || info.getBD_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-자료실-상세페이지");
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
		
		return "educenter/mobile/board/file/view";
	}
	
	
	
	//교육센터-커뮤니티-QnA 목록  ------------------------------------------------
	@RequestMapping(value = "/educenter/board/qna/list.do")
	public String educenter_board_qna(HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-QnA-목록");
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
		
		return "educenter/board/qna/list";
	}
	
	//교육센터-커뮤니티-QnA 목록  -- 모바일 ------------------------------------------------
	@RequestMapping(value = "/educenter/m/board/qna/list.do")
	public String educenter_board_qna_mobile(HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-QnA-목록");
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
		
		return "educenter/mobile/board/qna/list";
	}
	
	
	@RequestMapping(value = "/educenter/board/faq/list.do")
	public String educenter_board_faq_list(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		/** EgovPropertyService */
		eduCenterBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduCenterBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduCenterBoardVO.setBD_ID("board003");
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterBoardVO.getPageSize());

		eduCenterBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduCenterBoardVO> list = eduCenterBoardService.userBoardList(eduCenterBoardVO);		
		int totCnt = eduCenterBoardService.userBoardListTotCnt(eduCenterBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduCenterBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduCenterBoardVO.getSearchCondition().length()==0?"":eduCenterBoardVO.getSearchKeyword());
		
				
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-FAQ-목록");
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
		
		return "educenter/board/faq/list";
	}
	
	
	@RequestMapping(value = "/educenter/m/board/faq/list.do")
	public String educenter_board_faq_list_mobile(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		/** EgovPropertyService */
		eduCenterBoardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduCenterBoardVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		eduCenterBoardVO.setBD_ID("board003");
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterBoardVO.getPageSize());

		eduCenterBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduCenterBoardVO> list = eduCenterBoardService.userBoardList(eduCenterBoardVO);		
		int totCnt = eduCenterBoardService.userBoardListTotCnt(eduCenterBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",eduCenterBoardVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduCenterBoardVO.getSearchCondition().length()==0?"":eduCenterBoardVO.getSearchKeyword());
		
				
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			analyticsAdmVO.setvisitpagenm("(교육센터)커뮤니티-FAQ-목록");
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
		
		return "educenter/mobile/board/faq/list";
	}
	
}
