package egovframework.naksinuri_original.let.naksinuri.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.popup.service.PopupService;
import egovframework.adm.popup.service.PopupVO;
import egovframework.adm.sms.service.SmsManagerService;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogMemberModifyVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.all.main.service.KakaoAlimTalkService;
import egovframework.all.main.service.KakaoAlimTalkVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.educenter.board.service.EduCenterBoardService;
import egovframework.educenter.board.service.EduCenterBoardVO;
import egovframework.educenter.member.service.EduCenterMemberService;
import egovframework.educenter.member.service.EduCenterMemberVO;
import egovframework.educenter.myhistory.service.MyHistoryVO;
import egovframework.educenter.service.EduCenterMainService;
import egovframework.educenter.service.EduCenterMainVO;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngUtil;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.FishesService;
import egovframework.naksinuri_original.let.naksinuri.service.FishesVO;
import egovframework.naksinuri_original.let.naksinuri.service.FishjobReportService;
import egovframework.naksinuri_original.let.naksinuri.service.FishjobReviewService;
import egovframework.naksinuri_original.let.naksinuri.service.FishjobReviewVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAdminVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAnglingVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriLogsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriLogsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriMailVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriMainImgVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriTideService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriTotalSearch;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriUserMainImgService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.naksinuri_original.let.naksinuri.service.ReportVO;
import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;
import egovframework.naksinuri_original.let.naksinuri.service.Tide_FCVO;
import egovframework.naksinuri_original.let.naksinuri.service.Tide_TMVO;
import egovframework.naksinuri_original.let.naksinuri.utils.NaksinuriUtils;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.EgovDateUtil;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicUtils;

@Controller
public class NaksinuriController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(NaksinuriController.class);
	
	/** LogRecordService */
	@Resource(name = "logRecordService")
	private  LogRecordService logRecordService;
	
	@Resource(name="NaksinuriLogsService")
	private NaksinuriLogsService service_logs;
	
	@Resource(name="loginService")
	private LoginService loginService;

	@Resource(name="NaksinuriUserMainImgService")
	private NaksinuriUserMainImgService service_mainimg;

	@Resource(name="NaksinuriSanupService")
	private NaksinuriSanupService service_sanup;

	@Resource(name="NaksinuriTotalSearch")
	private NaksinuriTotalSearch service_TotalSearch;

	@Resource(name="NaksinuriGoneService")
	private NaksinuriGoneService service_gone;

	@Resource(name="NaksinuriNewsService")
	private NaksinuriNewsService service_news;

	@Resource(name="NaksinuriTideService")
	private NaksinuriTideService service_tide;

	@Resource(name="NaksinuriStaticService")
	private NaksinuriStatisticService service_statistic;

	@Resource(name="FishjobReviewService")
	private FishjobReviewService service_review;

	@Resource(name="FishjobReportService")
	private FishjobReportService service_report;

	@Resource(name="NaksinuriService")
	private NaksinuriService service;

	@Resource(name="NaksinuriOriginalEgovFileMngService")
	private NaksinuriOriginalEgovFileMngService fileMngService;

	@Resource(name="NaksinuriOriginalEgovFileMngUtil")
	private NaksinuriOriginalEgovFileMngUtil fileUtil;

	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;	

	@Resource(name="FishesService")
	private FishesService fishesService;
  
	/** EduCenterMainService */
	@Resource(name = "eduCenterMainService")
	private EduCenterMainService eduCenterMainService;
	
	@Resource(name = "eduCenterBoardService")
	private EduCenterBoardService eduCenterBoardService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name="popupService")
	private PopupService popupService;
	
	/** EduMyHistoryService */
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;

	@Resource(name = "eduCenterMemberService")
	private EduCenterMemberService eduCenterMemberService;
	
	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private  EduMemberService eduMemberService;
	
	@Resource(name = "NaksinuriService")
	private NaksinuriService naksinuriService;
	
	/** EduCurriculumService */
	@Resource(name = "eduCurriculumService")
	private EduCurriculumService eduCurriculumService;

	/** EduCertificateService */
	@Resource(name = "eduCertificateService")
	private  EduCertificateService eduCertificateService;
	
	@Resource(name="kakaoAlimTalkService")
	private KakaoAlimTalkService kakaoAlimTalkService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "smsManagerService")
	SmsManagerService smsManagerService;
	
  public static String getClientIpAddr(HttpServletRequest request)
  {
	  String ip = "";
	  try {
		    ip = request.getHeader("X-Forwarded-For");
		
		    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
		      ip = request.getHeader("Proxy-Client-IP");
		    }
		    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
		      ip = request.getHeader("WL-Proxy-Client-IP");
		    }
		    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
		      ip = request.getHeader("HTTP_CLIENT_IP");
		    }
		    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
		      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		    }
		    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
		      ip = request.getRemoteAddr();
		    }
	  } catch(Exception e) {
		  LOGGER.debug("[fail process] " +e.toString());
	  }
    return ip;
  }
  public static String getClientBrowser(HttpServletRequest request) {
    String browser = "";
    try {
	    String userAgent = request.getHeader("User-Agent");
	    if ((userAgent.indexOf("Trident") > 0) || (userAgent.indexOf("MSIE") > 0))
	      browser = "IE";
	    else if (userAgent.indexOf("Edge") > 0)
	      browser = "Edge";
	    else if (userAgent.indexOf("Presto") > 0)
	      browser = "Opera";
	    else if (userAgent.indexOf("Firefox") > 0)
	      browser = "Firefox";
	    else if (userAgent.indexOf("Nokia") > 0)
	      browser = "NokiaBrowser";
	    else if (userAgent.indexOf("Safari") > 0) {
	      if (userAgent.indexOf("Chrome") > 0)
	        browser = "Chrome";
	      else
	        browser = "Safari";
	    }
	    else {
	      browser = "etc";
	    }
    } catch(Exception e) {
    	LOGGER.debug("[fail process] " +e.toString());
    }
    return browser;
  }

  public static String getClientOS(HttpServletRequest request) {
	  String os = "";
	  try {
	    String user_os = request.getHeader("User-Agent");
	    if ((user_os.indexOf("iPhone") > 0) || (user_os.indexOf("iPad") > 0))
	      os = "Mobile_IOS";
	    else if (user_os.indexOf("Android") > 0)
	      os = "Mobile_Android";
	    else if (user_os.indexOf("Windows Phone") > 0)
	      os = "Mobile_Window";
	    else if (user_os.indexOf("RIM") > 0)
	      os = "Mobile_RIM";
	    else if (user_os.indexOf("Mac") > 0)
	      os = "Mac";
	    else if (user_os.indexOf("Ubuntu") > 0)
	      os = "Ubuntu";
	    else if (user_os.indexOf("Nokia") > 0) {
	      os = "MeeGo";
	    }
	} catch(Exception e) {
		LOGGER.debug("[fail process] " +e.toString());
	}
    return os;
  }

  @RequestMapping({"/search/search_list.do"})
  public String total_search(@ModelAttribute("boardVO") BoardVO boardVO, NaksinuriVO naksiVO, NaksinuriSanupVO sanupVO, NaksinuriFileVO nakVO, NaksinuriAnglingVO anglingVO, NaksinuriNewsVO newsVO, NaksinuriEventVO eventVO, NaksinuriGoneVO goneVO, NaksinuriQnAVO qnaVO, NaksinuriZisikinVO zisikVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
  {
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
    String searchText = request.getParameter("searchText");
    searchText = mEgovStringUtil.getHtmlStrCnvr(searchText);
    boardVO.setSearchText(searchText);

    model.addAttribute("searchText", boardVO.getSearchText());

    // 초보탈출하기 정보
    BoardVO edu_total = this.service_TotalSearch.edu_total(boardVO);
    List edu_list = this.service_TotalSearch.edulist(boardVO);
    boardVO.setCnt(edu_total.getCnt());
    model.addAttribute("junior_total", Integer.valueOf(edu_total.getCnt()));
    model.addAttribute("junior_list", edu_list);

    // 낚시고수되기 정보
    BoardVO gosu_total = this.service_TotalSearch.gosu_total(boardVO);
    List gosu_list = this.service_TotalSearch.gosu_list(boardVO);
    boardVO.setCnt(gosu_total.getCnt());
    model.addAttribute("gosu_total", Integer.valueOf(gosu_total.getCnt()));
    model.addAttribute("gosu_list", gosu_list);

    // 낚시상식 정보
    BoardVO sense_total = this.service_TotalSearch.sense_total(boardVO);
    List sense_list = this.service_TotalSearch.sense_list(boardVO);
    boardVO.setCnt(sense_total.getCnt());
    model.addAttribute("sense_total", Integer.valueOf(sense_total.getCnt()));
    model.addAttribute("sense_list", sense_list);

    // 채비필수 묶음법 정보
    BoardVO binding_total = this.service_TotalSearch.binding_total(boardVO);
    List binding_list = this.service_TotalSearch.binding_list(boardVO);
    boardVO.setCnt(binding_total.getCnt());
    model.addAttribute("binding_total", Integer.valueOf(binding_total.getCnt()));
    model.addAttribute("binding_list", binding_list);

    // 어종별 낚시교실 정보
    BoardVO class_total = this.service_TotalSearch.class_total(boardVO);
    List class_list = this.service_TotalSearch.class_list(boardVO);
    boardVO.setCnt(class_total.getCnt());
    model.addAttribute("class_total", Integer.valueOf(class_total.getCnt()));
    model.addAttribute("class_list", class_list);

    // 낚시터 정보
   /* NaksinuriVO co_total = this.service_TotalSearch.co_total(naksiVO);
    List co_list = this.service_TotalSearch.co_list(nakVO);
    naksiVO.setCnt(co_total.getCnt());
    model.addAttribute("co_total", Integer.valueOf(co_total.getCnt()));
    model.addAttribute("co_list", co_list);*/

    // 산업 정보
    /*NaksinuriSanupVO san_total = this.service_TotalSearch.san_total(sanupVO);
    List san_list = this.service_TotalSearch.san_list(sanupVO);
    sanupVO.setCnt(san_total.getCnt());
    model.addAttribute("san_total", Integer.valueOf(san_total.getCnt()));
    model.addAttribute("san_list", san_list);*/

    // 조황정보
    /*NaksinuriAnglingVO ang_total = this.service_TotalSearch.ang_total(anglingVO);
    List ang_list = this.service_TotalSearch.ang_list(anglingVO);
    anglingVO.setCnt(ang_total.getCnt());
    model.addAttribute("ang_total", Integer.valueOf(ang_total.getCnt()));
    model.addAttribute("ang_list", ang_list);*/

    // 낚시연구소 정보
    /*BoardVO lab_total = this.service_TotalSearch.lab_total(boardVO);
    List lab_list = this.service_TotalSearch.lab_list(boardVO);
    boardVO.setCnt(lab_total.getCnt());
    model.addAttribute("lab_total", Integer.valueOf(lab_total.getCnt()));
    model.addAttribute("lab_list", lab_list);*/

    // 낚시 뉴스 정보
    /*NaksinuriNewsVO news_total = this.service_TotalSearch.news_total(newsVO);
    List news_list = this.service_TotalSearch.news_list(newsVO);
    newsVO.setCnt(news_total.getCnt());
    model.addAttribute("news_total", Integer.valueOf(news_total.getCnt()));
    model.addAttribute("news_list", news_list);*/

    // 공지사항 정보
    BoardVO notice_total = this.service_TotalSearch.notice_total(boardVO);
    List notice_list = this.service_TotalSearch.notice_list(boardVO);
    boardVO.setCnt(notice_total.getCnt());
    model.addAttribute("notice_total", Integer.valueOf(notice_total.getCnt()));
    model.addAttribute("notice_list", notice_list);

    // 낚시대회 정보
    /*BoardVO congress_total = this.service_TotalSearch.congress_total(boardVO);
    List congress_list = this.service_TotalSearch.congress_list(boardVO);
    boardVO.setCnt(congress_total.getCnt());
    model.addAttribute("congress_total", Integer.valueOf(congress_total.getCnt()));
    model.addAttribute("congress_list", congress_list);*/

    // 이벤트 정보
    NaksinuriEventVO event_total = this.service_TotalSearch.event_total(eventVO);
    List event_list = this.service_TotalSearch.event_list(eventVO);
    eventVO.setCnt(event_total.getCnt());
    model.addAttribute("event_total", Integer.valueOf(event_total.getCnt()));
    model.addAttribute("event_list", event_list);

    // 낚시 캠페인 정보
    BoardVO campaign_total = this.service_TotalSearch.campaign_total(boardVO);
    List campaign_list = this.service_TotalSearch.campaign_list(boardVO);
    boardVO.setCnt(campaign_total.getCnt());
    model.addAttribute("campaign_total", Integer.valueOf(campaign_total.getCnt()));
    model.addAttribute("campaign_list", campaign_list);

    // 낚시 금지구역 정보
    /*NaksinuriGoneVO plocation_total = this.service_TotalSearch.plocation_total(goneVO);
    List plocation_list = this.service_TotalSearch.plocation_list(goneVO);
    goneVO.setCnt(plocation_total.getCnt());
    model.addAttribute("plocation_total", Integer.valueOf(plocation_total.getCnt()));
    model.addAttribute("plocation_list", plocation_list);*/

    // 낚시 제한 구역 정보
    /*NaksinuriGoneVO llocation_total = this.service_TotalSearch.llocation_total(goneVO);
    List llocation_list = this.service_TotalSearch.llocation_list(goneVO);
    goneVO.setCnt(llocation_total.getCnt());
    model.addAttribute("llocation_total", Integer.valueOf(llocation_total.getCnt()));
    model.addAttribute("llocation_list", llocation_list);*/

    // 낚시 정책 정보
    BoardVO policy_total = this.service_TotalSearch.policy_total(boardVO);
    List policy_list = this.service_TotalSearch.policy_list(boardVO);
    boardVO.setCnt(policy_total.getCnt());
    model.addAttribute("policy_total", Integer.valueOf(policy_total.getCnt()));
    model.addAttribute("policy_list", policy_list);

    // 낚시 FAQ 정보
    NaksinuriQnAVO qna_total = this.service_TotalSearch.qna_total(qnaVO);
    List qna_list = this.service_TotalSearch.qna_list(qnaVO);
    qnaVO.setCnt(qna_total.getCnt());
    model.addAttribute("qna_total", Integer.valueOf(qna_total.getCnt()));
    model.addAttribute("qna_list", qna_list);

    // 낚시 여행기 정보
    /*BoardVO travel_total = this.service_TotalSearch.travel_total(boardVO);
    List travel_list = this.service_TotalSearch.travel_list(boardVO);
    boardVO.setCnt(travel_total.getCnt());
    model.addAttribute("travel_total", Integer.valueOf(travel_total.getCnt()));
    model.addAttribute("travel_list", travel_list);*/

    // 누리지식인 정보
    /*NaksinuriZisikinVO zisik_total = this.service_TotalSearch.zisik_total(zisikVO);
    List zisik_list = this.service_TotalSearch.zisik_list(zisikVO);
    zisikVO.setCnt(zisik_total.getCnt());
    model.addAttribute("zisik_total", Integer.valueOf(zisik_total.getCnt()));
    model.addAttribute("zisik_list", zisik_list);*/

    // Q & A 정보
    NaksinuriZisikinVO zazu_total = this.service_TotalSearch.zazu_total(zisikVO);
    List zazu_list = this.service_TotalSearch.zazu_list(zisikVO);
    zisikVO.setCnt(zazu_total.getCnt());
    model.addAttribute("zazu_total", Integer.valueOf(zazu_total.getCnt()));
    model.addAttribute("zazu_list", zazu_list);

    // 낚시용품 사용기 정보
    /*BoardVO usage_total = this.service_TotalSearch.usage_total(boardVO);
    List usage_list = this.service_TotalSearch.usage_list(boardVO);
    boardVO.setCnt(usage_total.getCnt());
    model.addAttribute("usage_total", Integer.valueOf(usage_total.getCnt()));
    model.addAttribute("usage_list", usage_list);*/

    // 낚시칼럼(자유게시판) 정보
    BoardVO column_total = this.service_TotalSearch.column_total(boardVO);
    List column_list = this.service_TotalSearch.column_list(boardVO);
    boardVO.setCnt(column_total.getCnt());
    model.addAttribute("column_total", Integer.valueOf(column_total.getCnt()));
    model.addAttribute("column_list", column_list);
    
    //낚시전문교육 통합검색
    EduCenterBoardVO eduCenterBoardVO = new EduCenterBoardVO();
    eduCenterBoardVO.setSearchKeyword(boardVO.getSearchText());
    
    //낚시전문교육 - 공지사항
    int edu_notice_total = service_TotalSearch.edu_notice_total(eduCenterBoardVO);
    List<EduCenterBoardVO> edu_notice_list = service_TotalSearch.edu_notice_list(eduCenterBoardVO);
    model.addAttribute("edu_notice_total", edu_notice_total);
    model.addAttribute("edu_notice_list", edu_notice_list);
    
    //낚시전문교육 - 공지사항 - faq
    int edu_faq_total = service_TotalSearch.edu_faq_total(eduCenterBoardVO);
    List<EduCenterBoardVO> edu_faq_list = service_TotalSearch.edu_faq_list(eduCenterBoardVO);
    model.addAttribute("edu_faq_total", edu_faq_total);
    model.addAttribute("edu_faq_list", edu_faq_list);
    
    //낚시전문교육 - 공지사항 - 자료실
    int edu_file_total = service_TotalSearch.edu_file_total(eduCenterBoardVO);
    List<EduCenterBoardVO> edu_file_list = service_TotalSearch.edu_file_list(eduCenterBoardVO);
    model.addAttribute("edu_file_total", edu_file_total);
    model.addAttribute("edu_file_list", edu_file_list);
    

    model.addAttribute("total_search_Cnt", 
    		Integer.valueOf(edu_total.getCnt() + gosu_total.getCnt() + sense_total.getCnt() + binding_total.getCnt() + class_total.getCnt() + 
    				notice_total.getCnt() + event_total.getCnt() + campaign_total.getCnt() + policy_total.getCnt() + qna_total.getCnt() + 
    				zazu_total.getCnt() + column_total.getCnt()) + edu_notice_total + edu_faq_total + edu_file_total);

    return "naksinuri_original/naksinuri/search/search_list";
  }

  @RequestMapping({"/search/m/search_list.do"})
  public String total_search_mobile(@ModelAttribute("boardVO") BoardVO boardVO, NaksinuriVO naksiVO, NaksinuriSanupVO sanupVO, NaksinuriFileVO nakVO, NaksinuriAnglingVO anglingVO, NaksinuriNewsVO newsVO, NaksinuriEventVO eventVO, NaksinuriGoneVO goneVO, NaksinuriQnAVO qnaVO, NaksinuriZisikinVO zisikVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
  {
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
    String searchText = request.getParameter("searchText");
    searchText = mEgovStringUtil.getHtmlStrCnvr(searchText);
    boardVO.setSearchText(searchText);

    model.addAttribute("searchText", boardVO.getSearchText());

    // 초보탈출하기 정보
    BoardVO edu_total = this.service_TotalSearch.edu_total(boardVO);
    List edu_list = this.service_TotalSearch.edulist(boardVO);
    boardVO.setCnt(edu_total.getCnt());
    model.addAttribute("junior_total", Integer.valueOf(edu_total.getCnt()));
    model.addAttribute("junior_list", edu_list);

    // 낚시고수되기 정보
    BoardVO gosu_total = this.service_TotalSearch.gosu_total(boardVO);
    List gosu_list = this.service_TotalSearch.gosu_list(boardVO);
    boardVO.setCnt(gosu_total.getCnt());
    model.addAttribute("gosu_total", Integer.valueOf(gosu_total.getCnt()));
    model.addAttribute("gosu_list", gosu_list);

    // 낚시상식 정보
    BoardVO sense_total = this.service_TotalSearch.sense_total(boardVO);
    List sense_list = this.service_TotalSearch.sense_list(boardVO);
    boardVO.setCnt(sense_total.getCnt());
    model.addAttribute("sense_total", Integer.valueOf(sense_total.getCnt()));
    model.addAttribute("sense_list", sense_list);

    // 채비필수 묶음법 정보
    BoardVO binding_total = this.service_TotalSearch.binding_total(boardVO);
    List binding_list = this.service_TotalSearch.binding_list(boardVO);
    boardVO.setCnt(binding_total.getCnt());
    model.addAttribute("binding_total", Integer.valueOf(binding_total.getCnt()));
    model.addAttribute("binding_list", binding_list);

    // 어종별 낚시교실 정
    BoardVO class_total = this.service_TotalSearch.class_total(boardVO);
    List class_list = this.service_TotalSearch.class_list(boardVO);
    boardVO.setCnt(class_total.getCnt());
    model.addAttribute("class_total", Integer.valueOf(class_total.getCnt()));
    model.addAttribute("class_list", class_list);

    NaksinuriVO co_total = this.service_TotalSearch.co_total(naksiVO);
    List co_list = this.service_TotalSearch.co_list(nakVO);
    naksiVO.setCnt(co_total.getCnt());
    model.addAttribute("co_total", Integer.valueOf(co_total.getCnt()));
    model.addAttribute("co_list", co_list);

    NaksinuriSanupVO san_total = this.service_TotalSearch.san_total(sanupVO);
    List san_list = this.service_TotalSearch.san_list(sanupVO);
    sanupVO.setCnt(san_total.getCnt());
    model.addAttribute("san_total", Integer.valueOf(san_total.getCnt()));
    model.addAttribute("san_list", san_list);

    NaksinuriAnglingVO ang_total = this.service_TotalSearch.ang_total(anglingVO);
    List ang_list = this.service_TotalSearch.ang_list(anglingVO);
    anglingVO.setCnt(ang_total.getCnt());
    model.addAttribute("ang_total", Integer.valueOf(ang_total.getCnt()));
    model.addAttribute("ang_list", ang_list);

    BoardVO lab_total = this.service_TotalSearch.lab_total(boardVO);
    List lab_list = this.service_TotalSearch.lab_list(boardVO);
    boardVO.setCnt(lab_total.getCnt());
    model.addAttribute("lab_total", Integer.valueOf(lab_total.getCnt()));
    model.addAttribute("lab_list", lab_list);

    NaksinuriNewsVO news_total = this.service_TotalSearch.news_total(newsVO);
    List news_list = this.service_TotalSearch.news_list(newsVO);
    newsVO.setCnt(news_total.getCnt());
    model.addAttribute("news_total", Integer.valueOf(news_total.getCnt()));
    model.addAttribute("news_list", news_list);

    BoardVO notice_total = this.service_TotalSearch.notice_total(boardVO);
    List notice_list = this.service_TotalSearch.notice_list(boardVO);
    boardVO.setCnt(news_total.getCnt());
    model.addAttribute("notice_total", Integer.valueOf(notice_total.getCnt()));
    model.addAttribute("notice_list", notice_list);

    BoardVO congress_total = this.service_TotalSearch.congress_total(boardVO);
    List congress_list = this.service_TotalSearch.congress_list(boardVO);
    boardVO.setCnt(congress_total.getCnt());
    model.addAttribute("congress_total", Integer.valueOf(congress_total.getCnt()));
    model.addAttribute("congress_list", congress_list);

    NaksinuriEventVO event_total = this.service_TotalSearch.event_total(eventVO);
    List event_list = this.service_TotalSearch.event_list(eventVO);
    eventVO.setCnt(event_total.getCnt());
    model.addAttribute("event_total", Integer.valueOf(event_total.getCnt()));
    model.addAttribute("event_list", event_list);

    BoardVO campaign_total = this.service_TotalSearch.campaign_total(boardVO);
    List campaign_list = this.service_TotalSearch.campaign_list(boardVO);
    boardVO.setCnt(campaign_total.getCnt());
    model.addAttribute("campaign_total", Integer.valueOf(campaign_total.getCnt()));
    model.addAttribute("campaign_list", campaign_list);

    NaksinuriGoneVO plocation_total = this.service_TotalSearch.plocation_total(goneVO);
    List plocation_list = this.service_TotalSearch.plocation_list(goneVO);
    goneVO.setCnt(plocation_total.getCnt());
    model.addAttribute("plocation_total", Integer.valueOf(plocation_total.getCnt()));
    model.addAttribute("plocation_list", plocation_list);

    NaksinuriGoneVO llocation_total = this.service_TotalSearch.llocation_total(goneVO);
    List llocation_list = this.service_TotalSearch.llocation_list(goneVO);
    goneVO.setCnt(llocation_total.getCnt());
    model.addAttribute("llocation_total", Integer.valueOf(llocation_total.getCnt()));
    model.addAttribute("llocation_list", llocation_list);

    BoardVO policy_total = this.service_TotalSearch.policy_total(boardVO);
    List policy_list = this.service_TotalSearch.policy_list(boardVO);
    boardVO.setCnt(policy_total.getCnt());
    model.addAttribute("policy_total", Integer.valueOf(policy_total.getCnt()));
    model.addAttribute("policy_list", policy_list);

    NaksinuriQnAVO qna_total = this.service_TotalSearch.qna_total(qnaVO);
    List qna_list = this.service_TotalSearch.qna_list(qnaVO);
    qnaVO.setCnt(qna_total.getCnt());
    model.addAttribute("qna_total", Integer.valueOf(qna_total.getCnt()));
    model.addAttribute("qna_list", qna_list);

    BoardVO travel_total = this.service_TotalSearch.travel_total(boardVO);
    List travel_list = this.service_TotalSearch.travel_list(boardVO);
    boardVO.setCnt(travel_total.getCnt());
    model.addAttribute("travel_total", Integer.valueOf(travel_total.getCnt()));
    model.addAttribute("travel_list", travel_list);

    NaksinuriZisikinVO zisik_total = this.service_TotalSearch.zisik_total(zisikVO);
    List zisik_list = this.service_TotalSearch.zisik_list(zisikVO);
    zisikVO.setCnt(zisik_total.getCnt());
    model.addAttribute("zisik_total", Integer.valueOf(zisik_total.getCnt()));
    model.addAttribute("zisik_list", zisik_list);

    NaksinuriZisikinVO zazu_total = this.service_TotalSearch.zazu_total(zisikVO);
    List zazu_list = this.service_TotalSearch.zazu_list(zisikVO);
    zisikVO.setCnt(zazu_total.getCnt());
    model.addAttribute("zazu_total", Integer.valueOf(zazu_total.getCnt()));
    model.addAttribute("zazu_list", zazu_list);

    BoardVO usage_total = this.service_TotalSearch.usage_total(boardVO);
    List usage_list = this.service_TotalSearch.usage_list(boardVO);
    boardVO.setCnt(usage_total.getCnt());
    model.addAttribute("usage_total", Integer.valueOf(usage_total.getCnt()));
    model.addAttribute("usage_list", usage_list);

    BoardVO column_total = this.service_TotalSearch.column_total(boardVO);
    List column_list = this.service_TotalSearch.column_list(boardVO);
    boardVO.setCnt(column_total.getCnt());
    model.addAttribute("column_total", Integer.valueOf(column_total.getCnt()));
    model.addAttribute("column_list", column_list);
    
    //낚시전문교육 통합검색
    EduCenterBoardVO eduCenterBoardVO = new EduCenterBoardVO();
    eduCenterBoardVO.setSearchKeyword(boardVO.getSearchText());
    
    //낚시전문교육 - 공지사항
    int edu_notice_total = service_TotalSearch.edu_notice_total(eduCenterBoardVO);
    List<EduCenterBoardVO> edu_notice_list = service_TotalSearch.edu_notice_list(eduCenterBoardVO);
    model.addAttribute("edu_notice_total", edu_notice_total);
    model.addAttribute("edu_notice_list", edu_notice_list);
    
    //낚시전문교육 - 공지사항 - faq
    int edu_faq_total = service_TotalSearch.edu_faq_total(eduCenterBoardVO);
    List<EduCenterBoardVO> edu_faq_list = service_TotalSearch.edu_faq_list(eduCenterBoardVO);
    model.addAttribute("edu_faq_total", edu_faq_total);
    model.addAttribute("edu_faq_list", edu_faq_list);
    
    //낚시전문교육 - 공지사항 - 자료실
    int edu_file_total = service_TotalSearch.edu_file_total(eduCenterBoardVO);
    List<EduCenterBoardVO> edu_file_list = service_TotalSearch.edu_file_list(eduCenterBoardVO);
    model.addAttribute("edu_file_total", edu_file_total);
    model.addAttribute("edu_file_list", edu_file_list);

    model.addAttribute("total_search_Cnt", 
    		Integer.valueOf(edu_total.getCnt() + gosu_total.getCnt() + sense_total.getCnt() + binding_total.getCnt() + class_total.getCnt() + co_total.getCnt() + 
    		san_total.getCnt() + ang_total.getCnt() + lab_total.getCnt() + news_total.getCnt() + notice_total.getCnt() + congress_total.getCnt() + event_total.getCnt() + 
    		campaign_total.getCnt() + campaign_total.getCnt() + plocation_total.getCnt() + llocation_total.getCnt() + policy_total.getCnt() + qna_total.getCnt() + travel_total.getCnt() + 
    		zisik_total.getCnt() + zazu_total.getCnt() + usage_total.getCnt() + column_total.getCnt()) + edu_notice_total + edu_faq_total + edu_file_total);

    return "naksinuri_original/naksinuri/search/m/search_list";
  }

  //메인화면 ------------------------------------------------
	@RequestMapping(value = "/intro.do")
	public String mainIntro( HttpServletRequest request,
			ModelMap model) throws Exception {
		
		model.addAttribute("isMobileDevice", (PublicUtils.isMobileDevice(request)?"Y":"N"));

		
		return "naksinuri_original/main/intro";
	}

		
	
	
	
	
  @RequestMapping({"/index.do"})
  public String index(@ModelAttribute("boardVO") BoardVO boardVO, NaksinuriVO naksinuriVO, NaksinuriNewsVO newsVO, NaksinuriStatisticVO staticVO, NaksinuriMainImgVO mainimgVO, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    LOGGER.debug("/index.do");
    LOGGER.debug(">> NaksiController - index : 웹 분기 ");

    String[] parseReferer = NaksinuriUtils.checkFunnels(request.getHeader("referer"), this.service_statistic.get_funnels());
    if (parseReferer != null) {
      LOGGER.debug(new StringBuilder().append("parseReferer label : ").append(parseReferer[0]).toString());
      LOGGER.debug(new StringBuilder().append("parseReferer url : ").append(parseReferer[1]).toString());
      LOGGER.debug(new StringBuilder().append("parseReferer keyword : ").append(parseReferer[2]).toString());
      staticVO.setFunnels_label(parseReferer[0]);
      staticVO.setFunnels_url(parseReferer[1]);
      staticVO.setFunnels_keyword(parseReferer[2]);
    }

    boardVO.setBoard1("travel");
    boardVO.setBoard2("column");
    

    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("main");
    staticVO.setPath(request.getRequestURL().toString());
    staticVO.setBo_name("메인");

    staticVO.setCategory_group_type("");
    staticVO.setCategory_group_name("");
    staticVO.setPath_type("web");
    staticVO.setCategory_type("");
    staticVO.setCategory_name("");

    this.service_statistic.get_statisticInfo(staticVO,request);
    
    List mainimg_scrollimg = this.service_mainimg.main_scrollimg(mainimgVO);
    List mainimg_main_popup = this.service_mainimg.main_popup(mainimgVO);

    List head_list = this.service_mainimg.headimg(mainimgVO);
    List mainimg_list1 = this.service_mainimg.mainimg1(mainimgVO);
    List mainimg_list2 = this.service_mainimg.mainimg2(mainimgVO);
    List mainimg_list3 = this.service_mainimg.mainimg3(mainimgVO);
    List mainimg_list4 = this.service_mainimg.mainimg4(mainimgVO);
    List main_banner1 = this.service_mainimg.mainbanner1(mainimgVO);
    List main_banner2 = this.service_mainimg.mainbanner2(mainimgVO);
    List main_banner3 = this.service_mainimg.mainbanner3(mainimgVO);
    List right_banner = this.service_mainimg.rightbanner(mainimgVO);
    List news = this.service_news.select_main_news(newsVO);
    List notice = this.service.select_main_notice(boardVO);
    List riv_angling = this.service.river_angling(boardVO);
    
    List sea_angling = this.service.sea_angling(boardVO);
    List main_naksi_congress = this.service.main_naksi_congress(boardVO);
    
    // 낚시캠페인
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("campaign");
    List campaign_list = this.service.campaign_list(boardVO);
    
    // 낚시정책
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("policy");
    List policy_list = this.service.select_list(boardVO);
    
    // 낚시정책
    boardVO.setPageUnit(6);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("policy");
    List policy_list2 = this.service.select_list(boardVO);

    // 알림마당
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("info");
    List info_list = this.service.select_list(boardVO);
    
    // 알림마당 ui/ux
    boardVO.setPageUnit(6);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("info");
    List info_list2 = this.service.select_list(boardVO);
    
    // 낚시누리 전문교육 공지사항
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("notice");
    List notice_list = this.service.select_list(boardVO);
    
    // 낚시누리 전문교육 공지사항 ui/ux
    boardVO.setPageUnit(6);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("notice");
    List notice_list2 = this.service.select_list(boardVO);

    // 낚시누리 낚시의 품격
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("dignity");
    List dignity_list = this.service.select_list(boardVO);
    
    //naksinuriVO.setPageUnit(1);
    //naksinuriVO.setFishing_type("boatfishing");
    //List choo_job_boatfishing = this.service.choo_job(naksinuriVO);
    List choo_job_boatfishing = null;
    {
    	NaksinuriFileVO naksinuriFileVO = new NaksinuriFileVO();
	    naksinuriFileVO.setPageUnit(1);
	    naksinuriFileVO.setPageInfo(model);
	    naksinuriFileVO.setPageUnit(naksinuriFileVO.getPageUnit());    
	    naksinuriFileVO.setSearchBoat("boatfishing");
	    choo_job_boatfishing = this.service.getListFishJob(naksinuriFileVO);
    }
    List choo_job_seafishing = null;
    {
	    NaksinuriFileVO naksinuriFileVO = new NaksinuriFileVO();
	    naksinuriFileVO.setPageUnit(1);
	    naksinuriFileVO.setPageInfo(model);
	    naksinuriFileVO.setPageUnit(naksinuriFileVO.getPageUnit());    
	    naksinuriFileVO.setSearchBoat("seafishing"); 
	    choo_job_seafishing = this.service.getListFishJob(naksinuriFileVO);
    }
    List choo_job_riverfishing = null;
    {
	    NaksinuriFileVO naksinuriFileVO = new NaksinuriFileVO();
	    naksinuriFileVO.setPageUnit(1);
	    naksinuriFileVO.setPageInfo(model);
	    naksinuriFileVO.setPageUnit(naksinuriFileVO.getPageUnit());    
	    naksinuriFileVO.setSearchBoat("riverfishing");   
	    choo_job_riverfishing = this.service.getListFishJob(naksinuriFileVO);
    }
    
    
    
    //naksinuriVO.setFishing_type("seafishing");
    //List choo_job_seafishing = this.service.choo_job(naksinuriVO);
    //naksinuriVO.setFishing_type("riverfishing");
   // List choo_job_riverfishing = this.service.choo_job(naksinuriVO);
    /*
    if (choo_job_boatfishing.size() > 0)
      naksinuriVO.setTot_cnt(((NaksinuriVO)choo_job_boatfishing.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTot_cnt(0);
    }
    */
    
    model.addAttribute("searchType", mainimgVO.getSearchType());
    model.addAttribute("searchText", mainimgVO.getSearchText());

    //List list = this.service_mainimg.mainimg_list(mainimgVO);
    
    LOGGER.debug("===============================");
    model.addAttribute("scrollimg", mainimg_scrollimg);
    model.addAttribute("popups", mainimg_main_popup);
    LOGGER.debug("===============================");
    
    
    //낚시전문교육 교육과정 목록
    PublicUtils mPublicUtils = new PublicUtils();
    Calendar mNextCalendar = mPublicUtils.changeGetCalendar(mPublicUtils.currentTime("yyyy-MM-dd"), "yyyy-MM-dd");
    mNextCalendar.add(Calendar.MONTH, 3);//현재 기준 3개월 뒤
    
    EduCenterMainVO mEduCenterMainVO = new EduCenterMainVO();
    mEduCenterMainVO.setUSE_ST("1");
    mEduCenterMainVO.setDEL_ST("0");
    mEduCenterMainVO.setLOCK_ST("0");
    mEduCenterMainVO.setNotUsedPagination(true);
    mEduCenterMainVO.setCRS_STR_DT(mPublicUtils.currentTime("yyyy-MM-01"));
    mEduCenterMainVO.setCRS_END_DT(mPublicUtils.changeCalendarToString(mNextCalendar,"yyyy-MM-01"));
    mEduCenterMainVO.setCRS_TYPE("default");
    mEduCenterMainVO.setOrderByStr("CRS_STR_DT");
    List<EduCenterMainVO> list_crs = eduCenterMainService.get_educenter_main_curriculum_list(mEduCenterMainVO);
    model.addAttribute("list_crs", list_crs);
    //
    //대상구분 코드 조회
  	{
  		CodeSetVO mCodeSetVO = new CodeSetVO();
  		mCodeSetVO.setCD_MASTER_ID("CID00002");
  		mCodeSetVO.setHIDE_AT("N");
  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
  		model.addAttribute("list_mbr_cd",list_mbr_cd);
  	}
  	
  	
  	
  	//공지사항
  	EduCenterBoardVO eduCenterBoardVO = new EduCenterBoardVO();
  	eduCenterBoardVO.setFirstIndex(0);
  	eduCenterBoardVO.setRecordCountPerPage(3);	
  	eduCenterBoardVO.setBD_ID("board013");//교육센터 공지사항
  	List<EduCenterBoardVO> list_notice = eduCenterBoardService.userBoardList(eduCenterBoardVO);	
  	model.addAttribute("list_edu_notice",list_notice);
  	//
  	
  	//중앙배너
	PopupVO popupVO = new PopupVO();
	popupVO.setPP_RANK("1");
	popupVO.setPP_HIDE_ST("N");
	popupVO.setChk_show_allow_poup(true);
	popupVO.setNotUsedPagination(true);		
	popupVO.setPP_TYPE("banner_center");
	popupVO.setPP_SEA("Y");
	List<PopupVO> list_banner_center = popupService.get_seadm_popup_list(popupVO);
	model.addAttribute("list_banner_center",list_banner_center);
    
	/* 상단 및 팝업 배너는 all/popup/view 에서 처리함.
	//상단배너
	//PopupVO popupVO = new PopupVO();
	popupVO.setPP_RANK("1");
	popupVO.setPP_HIDE_ST("N");
	popupVO.setChk_show_allow_poup(true);
	popupVO.setNotUsedPagination(true);		
	popupVO.setPP_TYPE("banner_top");
	popupVO.setPP_SEA("Y");
	List<PopupVO> list_banner_top = popupService.get_seadm_popup_list(popupVO);
	model.addAttribute("list_banner_top",list_banner_top);
	*/
		
    model.addAttribute("head_list", head_list);
    model.addAttribute("mid1_list", mainimg_list1);
    model.addAttribute("mid2_list", mainimg_list2);
    model.addAttribute("mid3_list", mainimg_list3);
    model.addAttribute("mid4_list", mainimg_list4);
    model.addAttribute("main_banner1", main_banner1);
    model.addAttribute("main_banner2", main_banner2);
    model.addAttribute("main_banner3", main_banner3);
    model.addAttribute("right_banner", right_banner);
    model.addAttribute("main_news", news);
    model.addAttribute("main_notice", notice);
    model.addAttribute("river_angling", riv_angling);
    model.addAttribute("sea_angling", sea_angling);
    model.addAttribute("main_naksi_congress", main_naksi_congress);
    model.addAttribute("choo_job_boatfishing", choo_job_boatfishing);
    model.addAttribute("choo_job_seafishing", choo_job_seafishing);
    model.addAttribute("choo_job_riverfishing", choo_job_riverfishing);
    
    model.addAttribute("campaign_list", campaign_list);
    model.addAttribute("policy_list", policy_list);
    model.addAttribute("policy_list2", policy_list2);
    model.addAttribute("info_list", info_list);
    model.addAttribute("info_list2", info_list2);
    model.addAttribute("notice_list", notice_list);
    model.addAttribute("notice_list2", notice_list2);
    model.addAttribute("dignity_list", dignity_list);
    //model.addAttribute("fitot", Integer.valueOf(naksinuriVO.getTot_cnt()));

    return "naksinuri_original/main/index";
  }
  
  
  
  
  
  
  
  
  
  @RequestMapping(value={"/mid_forecast.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Map<String, Object>> midfcXml(String areaCode, ModelMap model, HttpServletRequest request) throws Exception {
    areaCode = request.getParameter("Code");

    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

    Calendar midnight = new GregorianCalendar();
    Calendar today = new GregorianCalendar();
    Calendar yesterday1 = new GregorianCalendar();
    yesterday1.add(5, -1);
    SimpleDateFormat formatyesterday = new SimpleDateFormat("yyyyMMdd06");

    String timedata = formatyesterday.format(midnight.getTime());

    SimpleDateFormat formattoday = new SimpleDateFormat("yyyyMMddHH");

    String timedata1 = formattoday.format(today.getTime());

    Date currentTime = new Date();
    String dTime_do = "";
    String dTime = formatter.format(currentTime);
    String yesterday_date = formatter.format(yesterday1.getTime());

    int yesterday_int = Integer.parseInt(timedata);
    int today_int = Integer.parseInt(timedata1);

    LOGGER.debug(new StringBuilder().append("today_int").append(today_int).toString());
    LOGGER.debug(new StringBuilder().append("yesterday_int").append(yesterday_int).toString());
    LOGGER.debug(new StringBuilder().append("yesterday_date").append(yesterday_date).toString());
    if (today_int < yesterday_int) {
      LOGGER.debug("중기 기준 1800시");
      dTime_do = new StringBuilder().append(yesterday_date).append("1800").toString();
    }
    else {
      LOGGER.debug("중기 기준 0600시");
      dTime_do = new StringBuilder().append(dTime).append("0600").toString();
    }

    StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleLandWeather");
    urlBuilder.append(new StringBuilder().append("?").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D").toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=").append(URLEncoder.encode("m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("regId", "UTF-8")).append("=").append(URLEncoder.encode(areaCode, "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("tmFc", "UTF-8")).append("=").append(URLEncoder.encode(dTime_do, "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode("10", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode("1", "UTF-8")).toString());
    URL url = new URL(urlBuilder.toString());
    LOGGER.debug(new StringBuilder().append("중기예보url:").append(url).toString());
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    LOGGER.debug(new StringBuilder().append("Response code: ").append(conn.getResponseCode()).toString());
    InputStream inputstream = null;
    //InputStream errorstream = null;
    InputStreamReader inputstreamreader = null;
    //InputStreamReader errorStreamReader = null;
    BufferedReader rd = null; 
    String xml = "";
    try {
	    inputstream = conn.getInputStream();
	    //errorstream = conn.getErrorStream();
	    inputstreamreader = new InputStreamReader(inputstream);
	    //InputStreamReader errorStreamReader = new InputStreamReader(errorstream);
	    if ((conn.getResponseCode() >= 200) && (conn.getResponseCode() <= 300)) {
	      rd = new BufferedReader(inputstreamreader);
	    /*} else {
	      rd = new BufferedReader(errorStreamReader);*/
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	      sb.append(line);
	    }
	    xml = sb.toString();
	    
    } catch(IOException e1){
    	LOGGER.debug("fail IOException");
    } catch(Exception e) {
    	LOGGER.debug("fail Exception "+e.toString());
	} finally {
		try {
    		rd.close();
        } catch(IOException e1){
        	LOGGER.debug("fail InputStreamReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail InputStreamReader Exception "+e.toString());
    	}
	    /*try {
	    	errorStreamReader.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail ErrorStreamReader IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail ErrorStreamReader Exception "+e.toString());
		}*/
	    try {
	    	inputstreamreader.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail InputStreamReader IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail InputStreamReader Exception "+e.toString());
		}
	    /*try {
	    	errorstream.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail ErrorStream IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail ErrorStream Exception "+e.toString());
		}*/
	    try {
	    	inputstream.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail InputStream IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail InputStream Exception "+e.toString());
		}
	    
	}
    conn.disconnect();

    List list = new ArrayList();
    Map data = new HashMap();
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentbuilder = factory.newDocumentBuilder();
      InputStream is = new ByteArrayInputStream(xml.getBytes());
      Document doc = documentbuilder.parse(is);
      Element element = doc.getDocumentElement();

      NodeList list1 = element.getElementsByTagName("item");

      for (int i = 0; i < list1.getLength(); i++) {
        for (Node node = list1.item(i).getFirstChild(); node != null; node = node.getNextSibling())
        {
          try {
	          if (node.getNodeName().equals("wf1Am")) {
	            data.put("wf1Am", node.getTextContent().toString());
	          }
	
	          if (node.getNodeName().equals("wf1Pm"))
	          {
	            data.put("wf1Pm", node.getTextContent().toString());
	          }
          } catch(Exception e) {
        	  LOGGER.debug("[fail process] " +e.toString());
          }
          try {
	          if (node.getNodeName().equals("wf2Am")) {
	            data.put("wf2Am", node.getTextContent().toString());
	          }
	
	          if (node.getNodeName().equals("wf2Pm"))
	          {
	            data.put("wf2Pm", node.getTextContent().toString());
	          }
          } catch(Exception e) {
        	  LOGGER.debug("[fail process] " +e.toString());
          }
        	
          if (node.getNodeName().equals("wf3Am")) {
            data.put("wf3Am", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("wf3Pm"))
          {
            data.put("wf3Pm", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("wf4Am")) {
            data.put("wf4Am", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("wf4Pm")) {
            data.put("wf4Pm", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("wf5Am")) {
            data.put("wf5Am", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("wf5Pm")) {
            data.put("wf5Pm", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("wf6Am")) {
            data.put("wf6Am", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("wf6Pm")) {
            data.put("wf6Pm", node.getTextContent().toString());
          }
          if (node.getNodeName().equals("wf6Am")) {
            data.put("wf6Am", node.getTextContent().toString());
          }
          if (node.getNodeName().equals("wf7Am")) {
            data.put("wf7Am", node.getTextContent().toString());
          }
          if (node.getNodeName().equals("wf7Pm")) {
            data.put("wf7Pm", node.getTextContent().toString());
            list.add(data);
          }
        }

      }

      LOGGER.debug(new StringBuilder().append("중기예보 리스트").append(list).toString());
    } catch(Exception e) {
      LOGGER.debug(new StringBuilder().append("파싱에러").append(e.getMessage()).toString());
    }

    return list;
  }

  @RequestMapping({"/{column}/{table}/visit.do"})
  public String list_visit(@PathVariable("table") String table, @PathVariable("column") String column, String bo_cate, @ModelAttribute("boardVO") ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return new StringBuilder().append("naksinuri_original/naksinuri/admin/").append(column).append("/").append(table).append("_list").toString();
  }

  @RequestMapping(value={"/info/fishjob/Public_trvData.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Map<String, Object>> getpublic_trv(ModelMap model, HttpServletRequest request) {
    String keyword = request.getParameter("keyword");
    List list = new ArrayList();
    try {
	    StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList");
	    urlBuilder.append(new StringBuilder().append("?").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D").toString());
	    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=").append(URLEncoder.encode("m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D", "UTF-8")).toString());
	    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("MobileApp", "UTF-8")).append("=").append(URLEncoder.encode("naksinuri", "UTF-8")).toString());
	    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("MobileOS", "UTF-8")).append("=").append(URLEncoder.encode("ETC", "UTF-8")).toString());
	    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode("15", "UTF-8")).toString());
	    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode("1", "UTF-8")).toString());
	    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("areaCode", "UTF-8")).append("=").append(URLEncoder.encode(keyword, "UTF-8")).toString());
	    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("contentTypeId", "UTF-8")).append("=").append(URLEncoder.encode("12", "UTF-8")).toString());
	
	    URL url = new URL(urlBuilder.toString());
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	    LOGGER.debug(new StringBuilder().append("Response code: ").append(conn.getResponseCode()).toString());
	    
	    InputStream inputstream = null;
	    //InputStream errorstream = null;
	    InputStreamReader inputstreamreader = null;
	    //InputStreamReader errorStreamReader = null;
	    BufferedReader rd = null;
	    String xml = "";
	    try {
		    inputstream = conn.getInputStream();
		    //errorstream = conn.getErrorStream();
		    inputstreamreader = new InputStreamReader(inputstream);
		    //errorStreamReader = new InputStreamReader(errorstream);
		    if ((conn.getResponseCode() >= 200) && (conn.getResponseCode() <= 300)){
		      rd = new BufferedReader(inputstreamreader);
		    /*} else {
		      rd = new BufferedReader(errorStreamReader);*/
		    }
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = rd.readLine()) != null) {
		      sb.append(line);
		    }
	    } catch(IOException e1) {
	    	LOGGER.debug("fail IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail Exception "+e.toString());
	    } finally {
	    	try {
	    		rd.close();
	        } catch(IOException e1){
	        	LOGGER.debug("fail InputStreamReader IOException");
	        } catch(Exception e) {
	        	LOGGER.debug("fail InputStreamReader Exception "+e.toString());
	    	}
		    /*try {
		    	errorStreamReader.close();
		    } catch(IOException e1){
		    	LOGGER.debug("fail ErrorStreamReader IOException");
		    } catch(Exception e) {
		    	LOGGER.debug("fail ErrorStreamReader Exception "+e.toString());
			}*/
		    try {
		    	inputstreamreader.close();
		    } catch(IOException e1){
		    	LOGGER.debug("fail InputStreamReader IOException");
		    } catch(Exception e) {
		    	LOGGER.debug("fail InputStreamReader Exception "+e.toString());
			}
		    /*try {
		    	errorstream.close();
		    } catch(IOException e1){
		    	LOGGER.debug("fail ErrorStream IOException");
		    } catch(Exception e) {
		    	LOGGER.debug("fail ErrorStream Exception "+e.toString());
			}*/
		    try {
		    	inputstream.close();
		    } catch(IOException e1){
		    	LOGGER.debug("fail InputStream IOException");
		    } catch(Exception e) {
		    	LOGGER.debug("fail InputStream Exception "+e.toString());
			}
		}
	    conn.disconnect();
	    
	    try
	    {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      DocumentBuilder documentbuilder = factory.newDocumentBuilder();
	      InputStream is = new ByteArrayInputStream(xml.getBytes());
	      Document doc = documentbuilder.parse(is);
	      Element element = doc.getDocumentElement();
	
	      NodeList list1 = element.getElementsByTagName("item");
	
	      for (int i = 0; i < list1.getLength(); i++) {
	        Map data = new HashMap();
	        for (Node node = list1.item(i).getFirstChild(); node != null; node = node.getNextSibling())
	        {
	          if (node.getNodeName().equals("addr1")) {
	            data.put("addr1", node.getTextContent().toString());
	          }
	
	          if (node.getNodeName().equals("title")) {
	            data.put("title", node.getTextContent().toString());
	          }
	
	          if (node.getNodeName().equals("tel")) {
	            data.put("tel", node.getTextContent().toString());
	          }
	
	          if (node.getNodeName().equals("mapx")) {
	            data.put("mapx", node.getTextContent().toString());
	          }
	
	          if (node.getNodeName().equals("mapy")) {
	            data.put("mapy", node.getTextContent().toString());
	            list.add(data);
	          }
	
	        }
	
	      }
	
	      LOGGER.debug(new StringBuilder().append("관광정보리스트").append(list).toString());
	    } catch(Exception e) {
	      LOGGER.debug(new StringBuilder().append("파싱에러").append(e.getMessage()).toString());
	    }
    } catch(UnsupportedEncodingException e1) {
    	LOGGER.debug("[fail UnsupportedEncodingException] "+e1.toString());
    } catch(ProtocolException e2) {
    	LOGGER.debug("[fail ProtocolException] "+e2.toString());
    } catch(RuntimeException e3) {
    	LOGGER.debug("[fail RuntimeException] "+e3.toString());
    } catch(IOException e4) {
    	LOGGER.debug("[fail IOException] "+e4.toString());
    } catch (Exception e) {
    	LOGGER.debug("[fail Exception] "+e.toString());
	}
    
    return list;
  }

  @RequestMapping(value={"/info/fishjob/Public_foodData.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Map<String, Object>> getpublic_food(ModelMap model, HttpServletRequest request) throws Exception {
    String keyword = request.getParameter("keyword");

    StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList");
    urlBuilder.append(new StringBuilder().append("?").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D").toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=").append(URLEncoder.encode("m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("MobileApp", "UTF-8")).append("=").append(URLEncoder.encode("naksinuri", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("MobileOS", "UTF-8")).append("=").append(URLEncoder.encode("ETC", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode("15", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode("1", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("areaCode", "UTF-8")).append("=").append(URLEncoder.encode(keyword, "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("contentTypeId", "UTF-8")).append("=").append(URLEncoder.encode("39", "UTF-8")).toString());

    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    LOGGER.debug(new StringBuilder().append("Response code: ").append(conn.getResponseCode()).toString());
    
    InputStream inputstream = null;
    //InputStream errorstream = null;
    InputStreamReader inputstreamreader = null;
    //InputStreamReader errorStreamReader = null;
    BufferedReader rd = null;
    String xml = "";
    try {
	    inputstream = conn.getInputStream();
	    //errorstream = conn.getErrorStream();
	    inputstreamreader = new InputStreamReader(inputstream);
	    //errorStreamReader = new InputStreamReader(errorstream);
	    if ((conn.getResponseCode() >= 200) && (conn.getResponseCode() <= 300)) {
	      rd = new BufferedReader(inputstreamreader);
	    /*} else {
	      rd = new BufferedReader(errorStreamReader);*/
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	      sb.append(line);
	    }
	    xml = sb.toString();
    } catch(IOException e1) {
    	LOGGER.debug("fail IOException");
    } catch (Exception e) {
    	LOGGER.debug("fail Exception "+e.toString());
	} finally {
		try {
	    	rd.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail BufferedReader IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail BufferedReader Exception "+e.toString());
		}
	    /*try {
	    	errorStreamReader.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail ErrorStreamReader IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail ErrorStreamReader Exception "+e.toString());
		}*/
	    try {
	    	inputstreamreader.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail InputStreamReader IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail InputStreamReader Exception "+e.toString());
		}
	    /*try {
	    	errorstream.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail ErrorStream IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail ErrorStream Exception "+e.toString());
		}*/
	    try {
	    	inputstream.close();
	    } catch(IOException e1){
	    	LOGGER.debug("fail InputStream IOException");
	    } catch(Exception e) {
	    	LOGGER.debug("fail InputStream Exception "+e.toString());
		}
	}
    conn.disconnect();

    List list = new ArrayList();
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentbuilder = factory.newDocumentBuilder();
      InputStream is = new ByteArrayInputStream(xml.getBytes());
      Document doc = documentbuilder.parse(is);
      Element element = doc.getDocumentElement();

      NodeList list1 = element.getElementsByTagName("item");

      for (int i = 0; i < list1.getLength(); i++) {
        Map data = new HashMap();
        for (Node node = list1.item(i).getFirstChild(); node != null; node = node.getNextSibling())
        {
          if (node.getNodeName().equals("addr1")) {
            data.put("addr1", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("title")) {
            data.put("title", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("tel")) {
            data.put("tel", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("mapx")) {
            data.put("mapx", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("mapy")) {
            data.put("mapy", node.getTextContent().toString());
            list.add(data);
          }

        }

      }

      LOGGER.debug(new StringBuilder().append("음식점정보리스트").append(list).toString());
    } catch(Exception e) {
      LOGGER.debug(new StringBuilder().append("파싱에러").append(e.getMessage()).toString());
    }

    return list;
  }
  @RequestMapping(value={"/info/fishjob/Public_shopData.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Map<String, Object>> getpublic_shop(ModelMap model, HttpServletRequest request) throws Exception {
    String keyword = request.getParameter("keyword");

    StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList");
    urlBuilder.append(new StringBuilder().append("?").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D").toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=").append(URLEncoder.encode("m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("MobileApp", "UTF-8")).append("=").append(URLEncoder.encode("naksinuri", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("MobileOS", "UTF-8")).append("=").append(URLEncoder.encode("ETC", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode("15", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode("1", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("areaCode", "UTF-8")).append("=").append(URLEncoder.encode(keyword, "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("contentTypeId", "UTF-8")).append("=").append(URLEncoder.encode("38", "UTF-8")).toString());

    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    LOGGER.debug(new StringBuilder().append("Response code: ").append(conn.getResponseCode()).toString());
    
    InputStream inputstream = null;
    //InputStream errorstream = null;
    InputStreamReader inputstreamreader = null;
    //InputStreamReader errorStreamReader = null;
    BufferedReader rd = null;
    String xml = "";
    try {
	    inputstream = conn.getInputStream();
	    //errorstream = conn.getErrorStream();
	    inputstreamreader = new InputStreamReader(inputstream);
	    //errorStreamReader = new InputStreamReader(errorstream);
	    if ((conn.getResponseCode() >= 200) && (conn.getResponseCode() <= 300)) {
	      rd = new BufferedReader(inputstreamreader);
	    /*} else {
	      rd = new BufferedReader(errorStreamReader);*/
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	      sb.append(line);
	    }
	    xml = sb.toString();
    } catch(IOException e) {
    	LOGGER.debug("fail IOException");
    } catch(Exception e) {
    	LOGGER.debug("fail Exception "+e.toString());
    } finally {
    	try {
        	rd.close();
        } catch(IOException e1){
        	LOGGER.debug("fail BufferedReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail BufferedReader Exception "+e.toString());
    	}
        /*try {
        	errorStreamReader.close();
        } catch(IOException e1){
        	LOGGER.debug("fail ErrorStreamReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail ErrorStreamReader Exception "+e.toString());
    	}*/
        try {
        	inputstreamreader.close();
        } catch(IOException e1){
        	LOGGER.debug("fail InputStreamReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail InputStreamReader Exception "+e.toString());
    	}
        /*try {
        	errorstream.close();
        } catch(IOException e1){
        	LOGGER.debug("fail ErrorStream IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail ErrorStream Exception "+e.toString());
    	}*/
        try {
        	inputstream.close();
        } catch(IOException e1){
        	LOGGER.debug("fail InputStream IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail InputStream Exception "+e.toString());
    	}	
	}
    
    conn.disconnect();

    List list = new ArrayList();
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentbuilder = factory.newDocumentBuilder();
      InputStream is = new ByteArrayInputStream(xml.getBytes());
      Document doc = documentbuilder.parse(is);
      Element element = doc.getDocumentElement();

      NodeList list1 = element.getElementsByTagName("item");

      for (int i = 0; i < list1.getLength(); i++) {
        Map data = new HashMap();
        for (Node node = list1.item(i).getFirstChild(); node != null; node = node.getNextSibling())
        {
          if (node.getNodeName().equals("addr1")) {
            data.put("addr1", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("title")) {
            data.put("title", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("tel")) {
            data.put("tel", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("mapx")) {
            data.put("mapx", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("mapy")) {
            data.put("mapy", node.getTextContent().toString());
            list.add(data);
          }

        }

      }

      LOGGER.debug(new StringBuilder().append("쇼핑정보리스트").append(list).toString());
    } catch(Exception e) {
      LOGGER.debug(new StringBuilder().append("파싱에러").append(e.getMessage()).toString());
    }

    return list;
  }
  @RequestMapping(value={"/info/fishjob/Public_festivalData.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Map<String, Object>> getpublic_festival(ModelMap model, HttpServletRequest request) throws Exception {
    String keyword = request.getParameter("keyword");

    StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList");
    urlBuilder.append(new StringBuilder().append("?").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D").toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=").append(URLEncoder.encode("m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("MobileApp", "UTF-8")).append("=").append(URLEncoder.encode("naksinuri", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("MobileOS", "UTF-8")).append("=").append(URLEncoder.encode("ETC", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode("15", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode("1", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("areaCode", "UTF-8")).append("=").append(URLEncoder.encode(keyword, "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("contentTypeId", "UTF-8")).append("=").append(URLEncoder.encode("15", "UTF-8")).toString());

    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    LOGGER.debug(new StringBuilder().append("Response code: ").append(conn.getResponseCode()).toString());
    
    InputStream inputstream = null;
    //InputStream errorstream = null;
    InputStreamReader inputstreamreader = null;
    //InputStreamReader errorStreamReader = null;
    BufferedReader rd = null;
    String xml = "";
    try {
	    inputstream = conn.getInputStream();
	    //errorstream = conn.getErrorStream();
	    inputstreamreader = new InputStreamReader(inputstream);
	    //errorStreamReader = new InputStreamReader(errorstream);
	    if ((conn.getResponseCode() >= 200) && (conn.getResponseCode() <= 300)) {
	      rd = new BufferedReader(inputstreamreader);
	  	/*} else {
	      rd = new BufferedReader(errorStreamReader);*/
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	      sb.append(line);
	    }
	    xml = sb.toString();
    } catch(IOException e1) {
    	LOGGER.debug("fail IOException");
    } catch(Exception e) {
    	LOGGER.debug("fail Exception "+e.toString());
    } finally {
    	try {
        	rd.close();
        } catch(IOException e1){
        	LOGGER.debug("fail BufferedReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail BufferedReader Exception "+e.toString());
    	}
        /*try {
        	errorStreamReader.close();
        } catch(IOException e1){
        	LOGGER.debug("fail ErrorStreamReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail ErrorStreamReader Exception "+e.toString());
    	}*/
        try {
        	inputstreamreader.close();
        } catch(IOException e1){
        	LOGGER.debug("fail InputStreamReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail InputStreamReader Exception "+e.toString());
    	}
        /*try {
        	errorstream.close();
        } catch(IOException e1){
        	LOGGER.debug("fail ErrorStream IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail ErrorStream Exception "+e.toString());
    	}*/
        try {
        	inputstream.close();
        } catch(IOException e1){
        	LOGGER.debug("fail InputStream IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail InputStream Exception "+e.toString());
    	}
	}
    
    conn.disconnect();

    List list = new ArrayList();
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentbuilder = factory.newDocumentBuilder();
      InputStream is = new ByteArrayInputStream(xml.getBytes());
      Document doc = documentbuilder.parse(is);
      Element element = doc.getDocumentElement();

      NodeList list1 = element.getElementsByTagName("item");

      for (int i = 0; i < list1.getLength(); i++) {
        Map data = new HashMap();
        for (Node node = list1.item(i).getFirstChild(); node != null; node = node.getNextSibling())
        {
          if (node.getNodeName().equals("addr1")) {
            data.put("addr1", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("title")) {
            data.put("title", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("tel")) {
            data.put("tel", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("mapx")) {
            data.put("mapx", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("mapy")) {
            data.put("mapy", node.getTextContent().toString());
            list.add(data);
          }

        }

      }

      LOGGER.debug(new StringBuilder().append("축제정보리스트").append(list).toString());
    } catch(Exception e) {
      LOGGER.debug(new StringBuilder().append("파싱에러").append(e.getMessage()).toString());
    }

    return list;
  }

  public Map<String, Object> getGridxy(double v1, double v2) throws Exception
  {
    double RE = 6371.0087700000004D;
    double GRID = 5.0D;
    double SLAT1 = 30.0D;
    double SLAT2 = 60.0D;
    double OLON = 126.0D;
    double OLAT = 38.0D;
    double XO = 43.0D;
    double YO = 136.0D;
    double DEGRAD = 0.0174532925199433D;

    double re = RE / GRID;
    double slat1 = SLAT1 * DEGRAD;
    double slat2 = SLAT2 * DEGRAD;
    double olon = OLON * DEGRAD;
    double olat = OLAT * DEGRAD;

    double sn = Math.tan(0.7853981633974483D + slat2 * 0.5D) / Math.tan(0.7853981633974483D + slat1 * 0.5D);
    sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
    double sf = Math.tan(0.7853981633974483D + slat1 * 0.5D);
    sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
    double ro = Math.tan(0.7853981633974483D + olat * 0.5D);
    ro = re * sf / Math.pow(ro, sn);
    Map map = new HashMap();

    double ra = Math.tan(0.7853981633974483D + v1 * DEGRAD * 0.5D);
    ra = re * sf / Math.pow(ra, sn);
    double theta = v2 * DEGRAD - olon;
    if (theta > 3.141592653589793D)
      theta -= 6.283185307179586D;
    if (theta < -3.141592653589793D)
      theta += 6.283185307179586D;
    theta *= sn;
    map.put("lat", Double.valueOf(v1));
    map.put("lng", Double.valueOf(v2));

    map.put("x", Integer.valueOf((int)Math.floor(ra * Math.sin(theta) + XO + 0.5D)));
    map.put("y", Integer.valueOf((int)Math.floor(ro - ra * Math.cos(theta) + YO + 0.5D)));

    return map;
  }
  @RequestMapping(value={"/weatherinfo.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Map<String, Object>> getXml(String locate, ModelMap model, HttpServletRequest request) throws Exception { locate = request.getParameter("address");

    LOGGER.debug(locate);
    String location = locate;
    String json = "";
    StringBuilder sb = new StringBuilder();
    double v1 = 0.0D;
    double v2 = 0.0D;
    
    String addr = "http://maps.google.com/maps/api/geocode/json?address=";
    URL url = new URL(new StringBuilder().append(addr).append(URLEncoder.encode(location, "UTF-8")).toString());
    HttpURLConnection con = null;
    InputStream inputstream = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    try
    {
      con = (HttpURLConnection)url.openConnection();
      con.setConnectTimeout(10000);
      con.setUseCaches(false);
      inputstream = con.getInputStream();
      isr = new InputStreamReader(inputstream);
      br = new BufferedReader(isr);
      while (true)
      {
        String line = br.readLine();
        if (line == null)
          break;
        sb.append(line);
      }
    } catch(IOException e1) {
    	LOGGER.debug("[fail IOException]");
    } catch(Exception e) {
    	LOGGER.debug("[fail Exception] " +e.toString());
    } finally {
    	try {
      	  br.close();
        } catch(IOException e1) {
      	  LOGGER.debug("[fail BufferedReader IOException] ");
        } catch (Exception e) {
      	  LOGGER.debug("[fail BufferedReader Exception] "+e.toString());
        }
        try {
      	  isr.close();
        } catch(IOException e1) {
      	  LOGGER.debug("[fail InputStreamReader IOException] ");
        } catch (Exception e) {
      	  LOGGER.debug("[fail InputStreamReader Exception] "+e.toString());
        }
        try {
        	inputstream.close();
        } catch(IOException e1) {
      	  LOGGER.debug("[fail InputStream IOException] ");
        } catch (Exception e) {
      	  LOGGER.debug("[fail InputStream Exception] "+e.toString());
        }      
  	    con.disconnect();
	}
    json = sb.toString();
    JSONObject object = (JSONObject)JSONValue.parse(json);
    JSONArray array = (JSONArray)object.get("results");
    for (Iterator i$ = array.iterator(); i$.hasNext(); ) { Object o = i$.next();
      JSONObject object2 = (JSONObject)o;
      String lat = ((JSONObject)((JSONObject)object2.get("geometry")).get("location")).get("lat").toString();
      String lng = ((JSONObject)((JSONObject)object2.get("geometry")).get("location")).get("lng").toString();

      v1 = Double.parseDouble(lat);
      v2 = Double.parseDouble(lng);
    }

    Map map = getGridxy(v1, v2);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    SimpleDateFormat formattoday = new SimpleDateFormat("yyyyMMddHH");
    SimpleDateFormat formatyesterday = new SimpleDateFormat("yyyyMMdd05");
    Calendar yesterday = new GregorianCalendar();
    Calendar midnight = new GregorianCalendar();
    Calendar today = new GregorianCalendar();
    yesterday.add(5, -1);

    String timedata = formatyesterday.format(midnight.getTime());

    String timedata1 = formattoday.format(today.getTime());

    Date currentTime = new Date();
    String Time_do = "";
    String hour_do = "";
    String dTime = formatter.format(currentTime);
    String yesterday_ = formatter.format(yesterday.getTime());

    int yesterday_int = Integer.parseInt(timedata);
    int today_int = Integer.parseInt(timedata1);

    LOGGER.debug(new StringBuilder().append("어제날짜:").append(yesterday_).toString());
    LOGGER.debug(new StringBuilder().append("오늘날짜:").append(dTime).toString());
    if (today_int < yesterday_int) {
      LOGGER.debug("동네예보 api 예보기준시간은 저녁 8시 입니다.");
      Time_do = yesterday_;
      hour_do = "2000";
    }
    else {
      LOGGER.debug("동네예보 api 예보기준시간은 아침 5시 입니다.");
      Time_do = dTime;
      hour_do = "0500";
    }

    StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData");
    urlBuilder.append(new StringBuilder().append("?").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D").toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=").append(URLEncoder.encode("m2YpYhsVhHhcvvZfgf3Jok9Sb7lzHkx8xIFsBXY4%2Bc%2FNXcYQug0LhJ0Y3r0vXI0jxbainvbcNyMFO5J%2Frq3OEw%3D%3D", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("base_date", "UTF-8")).append("=").append(URLEncoder.encode(Time_do, "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("base_time", "UTF-8")).append("=").append(URLEncoder.encode(hour_do, "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode("300", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode("1", "UTF-8")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("nx", "UTF-8")).append("=").append(map.get("x")).toString());
    urlBuilder.append(new StringBuilder().append("&").append(URLEncoder.encode("ny", "UTF-8")).append("=").append(map.get("y")).toString());

    url = new URL(urlBuilder.toString());
    LOGGER.debug(new StringBuilder().append("1~3예보url:").append(url).toString());
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    LOGGER.debug(new StringBuilder().append("Response code: ").append(conn.getResponseCode()).toString());
    
    //InputStream errorstream = null;
    InputStreamReader inputstreamreader = null;
    //InputStreamReader errorStreamReader = null;
    BufferedReader rd = null;
    String xml = "";
    try {
	    inputstream = conn.getInputStream();
	    //errorstream = conn.getErrorStream();
	    inputstreamreader = new InputStreamReader(inputstream);
	    //errorStreamReader = new InputStreamReader(errorstream);
	    if ((conn.getResponseCode() >= 200) && (conn.getResponseCode() <= 300)) {
	      rd = new BufferedReader(inputstreamreader);
	  	/*} else {
	      rd = new BufferedReader(errorStreamReader);*/
	    }
	    StringBuilder sb1 = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	      sb1.append(line);
	    }
	    xml = sb1.toString();
    } catch(IOException e1) {
    	
    } catch(Exception e) {
    	
    } finally {
    	try {
        	rd.close();
        } catch(IOException e1){
        	LOGGER.debug("fail BufferedReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail BufferedReader Exception "+e.toString());
    	}
        /*try {
        	errorStreamReader.close();
        } catch(IOException e1){
        	LOGGER.debug("fail ErrorStreamReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail ErrorStreamReader Exception "+e.toString());
    	}*/
        try {
        	inputstreamreader.close();
        } catch(IOException e1){
        	LOGGER.debug("fail InputStreamReader IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail InputStreamReader Exception "+e.toString());
    	}
        /*try {
        	errorstream.close();
        } catch(IOException e1){
        	LOGGER.debug("fail ErrorStream IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail ErrorStream Exception "+e.toString());
    	}*/
        try {
        	inputstream.close();
        } catch(IOException e1){
        	LOGGER.debug("fail InputStream IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail InputStream Exception "+e.toString());
    	}
	}
    
    conn.disconnect();

    List return_list = new ArrayList();
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentbuilder = factory.newDocumentBuilder();
      inputstream = new ByteArrayInputStream(xml.getBytes());
      Document doc = documentbuilder.parse(inputstream);
      Element element = doc.getDocumentElement();

      NodeList list1 = element.getElementsByTagName("item");

      LOGGER.debug(new StringBuilder().append("날씨아이템길이:").append(list1.getLength()).toString());

      for (int i = 0; i < list1.getLength(); i++) {
        Map data = new HashMap();
        for (Node node = list1.item(i).getFirstChild(); node != null; node = node.getNextSibling())
        {
          if (node.getNodeName().equals("fcstDate")) {
            data.put("fcstDate", node.getTextContent().toString());
          }

          if (node.getNodeName().equals("category")) {
            data.put("category", node.getTextContent().toString());
          }
          if (node.getNodeName().equals("fcstTime")) {
            data.put("fcstTime", node.getTextContent().toString());
          }
          if (node.getNodeName().equals("fcstValue")) {
            data.put("fcstValue", node.getTextContent().toString());
            return_list.add(data);
          }

        }

      }

      LOGGER.debug(new StringBuilder().append("날씨리스트").append(return_list).toString());
    } catch(Exception e) {
      LOGGER.debug(new StringBuilder().append("파싱에러").append(e.getMessage()).toString());
    } finally {
    	try {
        	inputstream.close();
        } catch(IOException e1){
        	LOGGER.debug("fail InputStream IOException");
        } catch(Exception e) {
        	LOGGER.debug("fail InputStream Exception "+e.toString());
    	}
	}

    return return_list; }

  @RequestMapping(value={"/choochange.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriVO> seachange(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model)
    throws Exception
  {
    List choo_job = this.service.choo_job(naksinuriVO);

    if (choo_job.size() <= 0) {
      naksinuriVO.setPageIndex(1);
      choo_job = this.service.choo_job(naksinuriVO);
    }

    if (choo_job.size() > 0)
      naksinuriVO.setTot_cnt(((NaksinuriVO)choo_job.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTot_cnt(0);
    }
    model.addAttribute("fitot", Integer.valueOf(naksinuriVO.getTot_cnt()));

    return choo_job; } 
  @RequestMapping(value={"/noticechange.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<BoardVO> noticechange(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception { List notice = this.service.select_main_notice(boardVO);

    return notice; } 
  @RequestMapping(value={"/newschange.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriNewsVO> newschange(@ModelAttribute("newsVO") NaksinuriNewsVO newsVO, ModelMap model) throws Exception {
    List news = this.service_news.select_main_news(newsVO);

    return news;
  }
  @RequestMapping(value={"/tide_time.do"}, method={/*org.springframework.web.bind.annotation.RequestMethod.POST*/})
  @ResponseBody
  public List<Tide_TMVO> get_tidetm(@ModelAttribute("tide_tmVO") Tide_TMVO tide_tmVO, ModelMap model) throws Exception { List tm = this.service_tide.tidetime_list(tide_tmVO);

    return tm; }

  @RequestMapping(value={"/tide_forecast.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Tide_FCVO> get_tidefc(@ModelAttribute("tide_fcVO") Tide_FCVO tide_fcVO, ModelMap model) throws Exception {
    List fc = this.service_tide.tidefc_list(tide_fcVO);
    return fc;
  }

  @RequestMapping({"/m/index.do"})
  public String mobile_index(@ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, NaksinuriVO naksinuriVO, NaksinuriMainImgVO mainimgVO, NaksinuriNewsVO newsVO, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    LOGGER.debug(">> NaksinuriController - mobile_index : /m/index.do");

    boardVO.setBoard1("travel");
    boardVO.setBoard2("column");
    naksinuriVO.setPageUnit(2);

    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("main");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("");
    staticVO.setCategory_group_name("");
    staticVO.setPath_type("mobile");
    staticVO.setCategory_type("");
    staticVO.setCategory_name("");

    this.service_statistic.get_statisticInfo(staticVO,request);
    List head_list = this.service_mainimg.mobile_headimg(mainimgVO);
    List mainimg_list1 = this.service_mainimg.mobile_mainimg1(mainimgVO);
    List mainimg_list2 = this.service_mainimg.mobile_mainimg2(mainimgVO);
    List mainimg_list3 = this.service_mainimg.mobile_mainimg3(mainimgVO);
    List mainimg_list4 = this.service_mainimg.mobile_mainimg4(mainimgVO);
    List main_banner1 = this.service_mainimg.mobile_mainbanner1(mainimgVO);
    List main_banner2 = this.service_mainimg.mobile_mainbanner2(mainimgVO);
    List main_banner3 = this.service_mainimg.mobile_mainbanner3(mainimgVO);
    List right_banner = this.service_mainimg.mobile_rightbanner(mainimgVO);
    List news = this.service_news.select_main_news(newsVO);
    List notice = this.service.select_main_notice(boardVO);
    List riv_angling = this.service.river_anglingm(boardVO);
    List sea_angling = this.service.sea_anglingm(boardVO);
    List main_naksi_congress = this.service.main_naksi_congress(boardVO);
    
    // 낚시캠페인
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("campaign");
    List campaign_list = this.service.campaign_list(boardVO);
    
    // 낚시정책
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("policy");
    List policy_list = this.service.select_list(boardVO);
    
    // 낚시정책 ui/ux
    boardVO.setPageUnit(6);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("policy");
    List policy_list2 = this.service.select_list(boardVO);
    
    // 알림마당
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("info");
    List info_list = this.service.select_list(boardVO);
    
    // 알림마당 ui/ux
    boardVO.setPageUnit(6);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("info");
    List info_list2 = this.service.select_list(boardVO);
    
    // 공지사항
    boardVO.setPageUnit(4);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("notice");
    List notice_list = this.service.select_list(boardVO);
    
    // 공지사항 ui/ux
    boardVO.setPageUnit(6);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("notice");
    List notice_list2 = this.service.select_list(boardVO);
    
    //naksinuriVO.setPageUnit(1);
    //List choo_job_boatfishing = this.service.boatfishing_job(naksinuriVO);
    //List choo_job_riverfishing = this.service.riverfishing_job(naksinuriVO);
    //List choo_job_seafishing = this.service.seafishing_job(naksinuriVO);
    List choo_job_boatfishing = null;
    {
    	NaksinuriFileVO naksinuriFileVO = new NaksinuriFileVO();
	    naksinuriFileVO.setPageUnit(1);
	    naksinuriFileVO.setPageInfo(model);
	    naksinuriFileVO.setPageUnit(naksinuriFileVO.getPageUnit());    
	    naksinuriFileVO.setSearchBoat("boatfishing");
	    choo_job_boatfishing = this.service.getListFishJob(naksinuriFileVO);
    }
    List choo_job_seafishing = null;
    {
	    NaksinuriFileVO naksinuriFileVO = new NaksinuriFileVO();
	    naksinuriFileVO.setPageUnit(1);
	    naksinuriFileVO.setPageInfo(model);
	    naksinuriFileVO.setPageUnit(naksinuriFileVO.getPageUnit());    
	    naksinuriFileVO.setSearchBoat("seafishing"); 
	    choo_job_seafishing = this.service.getListFishJob(naksinuriFileVO);
    }
    List choo_job_riverfishing = null;
    {
	    NaksinuriFileVO naksinuriFileVO = new NaksinuriFileVO();
	    naksinuriFileVO.setPageUnit(1);
	    naksinuriFileVO.setPageInfo(model);
	    naksinuriFileVO.setPageUnit(naksinuriFileVO.getPageUnit());    
	    naksinuriFileVO.setSearchBoat("riverfishing");   
	    choo_job_riverfishing = this.service.getListFishJob(naksinuriFileVO);
    }
         
    
    List mainimg_scrollimg = this.service_mainimg.main_scrollimg(mainimgVO);
    List mainimg_main_popup = this.service_mainimg.main_popup(mainimgVO);

    /*
    if (boatfishing_job.size() > 0)
      naksinuriVO.setTot_cnt(((NaksinuriVO)boatfishing_job.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTot_cnt(0);
    }

    if (riverfishing_job.size() > 0)
      naksinuriVO.setTot_cnt(((NaksinuriVO)riverfishing_job.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTot_cnt(0);
    }

    if (seafishing_job.size() > 0)
      naksinuriVO.setTot_cnt(((NaksinuriVO)seafishing_job.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTot_cnt(0);
    }
	*/
    
    //낚시전문교육 교육과정 목록
    PublicUtils mPublicUtils = new PublicUtils();
    Calendar mNextCalendar = mPublicUtils.changeGetCalendar(mPublicUtils.currentTime("yyyy-MM-dd"), "yyyy-MM-dd");
    mNextCalendar.add(Calendar.MONTH, 3);//현재 기준 3개월 뒤
    
    EduCenterMainVO mEduCenterMainVO = new EduCenterMainVO();
    mEduCenterMainVO.setUSE_ST("1");
    mEduCenterMainVO.setDEL_ST("0");
    mEduCenterMainVO.setLOCK_ST("0");
    mEduCenterMainVO.setNotUsedPagination(true);
    mEduCenterMainVO.setCRS_STR_DT(mPublicUtils.currentTime("yyyy-MM-01"));
    mEduCenterMainVO.setCRS_END_DT(mPublicUtils.changeCalendarToString(mNextCalendar,"yyyy-MM-01"));
    mEduCenterMainVO.setCRS_TYPE("default");
    mEduCenterMainVO.setOrderByStr("CRS_STR_DT");
    List<EduCenterMainVO> list_crs = eduCenterMainService.get_educenter_main_curriculum_list(mEduCenterMainVO);
    model.addAttribute("list_crs", list_crs);
    //
    //대상구분 코드 조회
  	{
  		CodeSetVO mCodeSetVO = new CodeSetVO();
  		mCodeSetVO.setCD_MASTER_ID("CID00002");
  		mCodeSetVO.setHIDE_AT("N");
  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
  		model.addAttribute("list_mbr_cd",list_mbr_cd);
  	}
    //
  	
  	
  	
  	//공지사항
	EduCenterBoardVO eduCenterBoardVO = new EduCenterBoardVO();
	eduCenterBoardVO.setFirstIndex(0);
	eduCenterBoardVO.setRecordCountPerPage(3);	
	eduCenterBoardVO.setBD_ID("board013");//교육센터 공지사항
	List<EduCenterBoardVO> list_notice = eduCenterBoardService.userBoardList(eduCenterBoardVO);	
	model.addAttribute("list_edu_notice",list_notice);
	//
	
	//중앙배너
	PopupVO popupVO = new PopupVO();
	popupVO.setPP_RANK("1");
	popupVO.setPP_HIDE_ST("N");
	popupVO.setChk_show_allow_poup(true);
	popupVO.setNotUsedPagination(true);		
	popupVO.setPP_TYPE("banner_center");
	popupVO.setPP_SEA("Y");
	List<PopupVO> list_banner_center = popupService.get_seadm_popup_list(popupVO);
	model.addAttribute("list_banner_center",list_banner_center);
	//

	//상단배너
	//PopupVO popupVO = new PopupVO();
	popupVO.setPP_RANK("1");
	popupVO.setPP_HIDE_ST("N");
	popupVO.setChk_show_allow_poup(true);
	popupVO.setNotUsedPagination(true);		
	popupVO.setPP_TYPE("banner_top");
	popupVO.setPP_SEA("Y");
	List<PopupVO> list_banner_top = popupService.get_seadm_popup_list(popupVO);
	model.addAttribute("list_banner_top",list_banner_top);
		
    model.addAttribute("head_list", head_list);
    model.addAttribute("mid1_list", mainimg_list1);
    model.addAttribute("mid2_list", mainimg_list2);
    model.addAttribute("mid3_list", mainimg_list3);
    model.addAttribute("mid4_list", mainimg_list4);
    model.addAttribute("main_banner1", main_banner1);
    model.addAttribute("main_banner2", main_banner2);
    model.addAttribute("main_banner3", main_banner3);
    model.addAttribute("right_banner", right_banner);
    model.addAttribute("pageUnit", Integer.valueOf(2));
    model.addAttribute("main_news", news);
    model.addAttribute("main_notice", notice);
    model.addAttribute("river_angling", riv_angling);
    model.addAttribute("sea_angling", sea_angling);
    model.addAttribute("main_naksi_congress", main_naksi_congress);    
    model.addAttribute("choo_job_boatfishing", choo_job_boatfishing);
    model.addAttribute("choo_job_seafishing", choo_job_seafishing);
    model.addAttribute("choo_job_riverfishing", choo_job_riverfishing);
    //model.addAttribute("fitot", Integer.valueOf(naksinuriVO.getTot_cnt()));
    
    model.addAttribute("campaign_list", campaign_list);
    model.addAttribute("policy_list", policy_list);
    model.addAttribute("info_list", info_list);
    model.addAttribute("info_list2", info_list2);
    model.addAttribute("notice_list", notice_list);
    model.addAttribute("notice_list2", notice_list2);
    
    model.addAttribute("scrollimg", mainimg_scrollimg);
    model.addAttribute("popups", mainimg_main_popup);
    

    return "naksinuri_original/main/m/index";
  }

  @RequestMapping({"/info/login.do"})
  public String login(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model) throws Exception
  {
    return "/naksinuri_original/naksinuri/login";
  }
  @RequestMapping({"/info/fishjob/login.do"})
  public String flogin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model) throws Exception
  {
    return "naksinuri_original/naksinuri/info/fishjob_login";
  }
  @RequestMapping({"/info/m/fishjob/login.do"})
  public String mflogin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model) throws Exception
  {
    return "naksinuri_original/naksinuri/info/m/fishjob_login";
  }

  @RequestMapping({"/info/industry/login.do"})
  public String ilogin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model) throws Exception
  {
    return "naksinuri_original/naksinuri/info/industry_login";
  }
  @RequestMapping({"/info/m/industry/login.do"})
  public String milogin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model) throws Exception
  {
    return "naksinuri_original/naksinuri/info/m/industry_login";
  }

  @RequestMapping({"/gongmo/mof/intro.do"})
  public String mof_intro(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO,  HttpServletRequest request, NaksinuriStatisticVO staticVO, ModelMap model) throws Exception
  {
	
	staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_name("공모전");
    staticVO.setBo_type("gongmo");
    staticVO.setCategory_group_type("intro");
    staticVO.setCategory_group_name("소개");
    staticVO.setCategory_type("mof");
    staticVO.setCategory_name("낚시안전콘텐츠공모전");
    staticVO.setPath_type("web");
    this.service_statistic.get_statisticInfo(staticVO,request);
    
    
    return "naksinuri_original/naksinuri/gongmo/mof_intro";
  }

  @RequestMapping({"/gongmo/gm/intro.do"})
  public String gm_intro(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, ModelMap model)
    throws Exception
  {
	staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_name("공모전");
    staticVO.setBo_type("gongmo");
    staticVO.setCategory_group_type("intro");
    staticVO.setCategory_group_name("소개");
    staticVO.setCategory_type("gm");
    staticVO.setCategory_name("공모전소개");
    staticVO.setPath_type("web");
    this.service_statistic.get_statisticInfo(staticVO,request);
    
    return "naksinuri_original/naksinuri/gongmo/gm_intro";
  }

  @RequestMapping({"/gongmo/summary/intro.do"})
  public String summary_intro(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, ModelMap model)
    throws Exception
  {
	  
	  staticVO.setPath(request.getRequestURL().toString());
	    if (getClientOS(request) != "")
	      staticVO.setStatistic_os(getClientOS(request));
	    else {
	      staticVO.setStatistic_os(System.getProperty("os.name"));
	    }
	    staticVO.setBrowser(getClientBrowser(request));
	    staticVO.setClient_ip(getClientIpAddr(request));
	    staticVO.setBo_name("공모전");
	    staticVO.setBo_type("gongmo");
	    staticVO.setCategory_group_type("intro");
	    staticVO.setCategory_group_name("소개");
	    staticVO.setCategory_type("summary");
	    staticVO.setCategory_name("공모전요강");
	    staticVO.setPath_type("web");
	    this.service_statistic.get_statisticInfo(staticVO,request);
	    
    return "naksinuri_original/naksinuri/gongmo/summary_intro";
  }

  @RequestMapping({"/gongmo/mof/m/intro.do"})
  public String mof_intro_m(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model, HttpServletRequest request, NaksinuriStatisticVO staticVO)
    throws Exception
  {
	  
	staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_name("공모전");
    staticVO.setBo_type("gongmo");
    staticVO.setCategory_group_type("intro");
    staticVO.setCategory_group_name("소개");
    staticVO.setCategory_type("mof");
    staticVO.setCategory_name("낚시안전콘텐츠공모전");
    staticVO.setPath_type("mobile");
    this.service_statistic.get_statisticInfo(staticVO,request);
    
    return "naksinuri_original/naksinuri/gongmo/m/mof_intro";
  }

  @RequestMapping({"/gongmo/gm/m/intro.do"})
  public String gm_intro_m(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, ModelMap model)
    throws Exception
  {
	  staticVO.setPath(request.getRequestURL().toString());
	    if (getClientOS(request) != "")
	      staticVO.setStatistic_os(getClientOS(request));
	    else {
	      staticVO.setStatistic_os(System.getProperty("os.name"));
	    }
	    staticVO.setBrowser(getClientBrowser(request));
	    staticVO.setClient_ip(getClientIpAddr(request));
	    staticVO.setBo_name("공모전");
	    staticVO.setBo_type("gongmo");
	    staticVO.setCategory_group_type("intro");
	    staticVO.setCategory_group_name("소개");
	    staticVO.setCategory_type("gm");
	    staticVO.setCategory_name("공모전소개");
	    staticVO.setPath_type("mobile");
	    this.service_statistic.get_statisticInfo(staticVO,request);
	    
    return "naksinuri_original/naksinuri/gongmo/m/gm_intro";
  }

  @RequestMapping({"/gongmo/summary/m/intro.do"})
  public String summary_intro_m(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, ModelMap model)
    throws Exception
  {
	staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_name("공모전");
    staticVO.setBo_type("gongmo");
    staticVO.setCategory_group_type("intro");
    staticVO.setCategory_group_name("소개");
    staticVO.setCategory_type("summary");
    staticVO.setCategory_name("공모전요강");
    staticVO.setPath_type("mobile");
    this.service_statistic.get_statisticInfo(staticVO,request);
	  
	  
    return "naksinuri_original/naksinuri/gongmo/m/summary_intro";
  }

  @RequestMapping({"/info/seafishing.do"})
  public String seafishing(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model) throws Exception
  {
    fishesVO.setFishing_type("seafishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    StringBuffer RandKey = new StringBuffer();
    RandKey.append("9999");
    int[] array = {1,2,3,4,5,6,7,8,9,0}; 
    Random ran = new Random();
    ran.setSeed(System.currentTimeMillis());
    for(int i=0; i<5; i++) {
    	RandKey.append(array[ran.nextInt(10)]);
    }
    //삽입모드를 위한 랜덤키 생성
    model.addAttribute("RandKey", RandKey.toString());
    
    return "naksinuri_original/naksinuri/seafishing";
  }
  @RequestMapping({"/info/m/seafishing.do"})
  public String mseafishing(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model) throws Exception
  {
    fishesVO.setFishing_type("seafishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    StringBuffer RandKey = new StringBuffer();
    RandKey.append("9999");
    //String RandKey = "9999";
    int[] array = {1,2,3,4,5,6,7,8,9,0}; 
    Random ran = new Random();
    ran.setSeed(System.currentTimeMillis());
    for(int i=0; i<5; i++) {
  	  //RandKey += array[ran.nextInt(10)];
  	  RandKey.append(array[ran.nextInt(10)]);
    }
    //삽입모드를 위한 랜덤키 생성
    model.addAttribute("RandKey", RandKey.toString());
    
    return "naksinuri_original/naksinuri/info/m/seafishing";
  }
  
//[보안점검수정][START]####################################################  
/*
  @RequestMapping({"/info/boatfishing.do"})
  public String boatfishing(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model)
    throws Exception
  {

    fishesVO.setFishing_type("boatfishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    StringBuffer RandKey = new StringBuffer();
    RandKey.append("9999");
    int[] array = {1,2,3,4,5,6,7,8,9,0}; 
    Random ran = new Random();
    ran.setSeed(System.currentTimeMillis());
    for(int i=0; i<5; i++) {
    	RandKey.append(array[ran.nextInt(10)]);
    }
    //삽입모드를 위한 랜덤키 생성
    model.addAttribute("RandKey", RandKey.toString());
    
    return "naksinuri_original/naksinuri/boatfishing";
  }
  */
//[보안점검수정][END]####################################################
  
  
  @RequestMapping({"/info/m/boatfishing.do"})
  public String mboatfishing(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model)
    throws Exception
  {

    fishesVO.setFishing_type("boatfishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    StringBuffer RandKey = new StringBuffer();
    RandKey.append("9999");
    int[] array = {1,2,3,4,5,6,7,8,9,0}; 
    Random ran = new Random();
    ran.setSeed(System.currentTimeMillis());
    for(int i=0; i<5; i++) {
    	RandKey.append(array[ran.nextInt(10)]);
    }
    //삽입모드를 위한 랜덤키 생성
    model.addAttribute("RandKey", RandKey.toString());    
    
    return "naksinuri_original/naksinuri/info/m/boatfishing";
  }

  @RequestMapping({"/info/riverfishing.do"})
  public String riverfishing(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model) throws Exception {

    fishesVO.setFishing_type("riverfishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    StringBuffer RandKey = new StringBuffer();
    RandKey.append("9999");
    int[] array = {1,2,3,4,5,6,7,8,9,0}; 
    Random ran = new Random();
    ran.setSeed(System.currentTimeMillis());
    for(int i=0; i<5; i++) {
    	RandKey.append(array[ran.nextInt(10)]);
    }
    //삽입모드를 위한 랜덤키 생성
    model.addAttribute("RandKey", RandKey.toString());    
    
    return "naksinuri_original/naksinuri/riverfishing";
  }
  @RequestMapping({"/info/m/riverfishing.do"})
  public String mriverfishing(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model) throws Exception {

    fishesVO.setFishing_type("riverfishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    StringBuffer RandKey = new StringBuffer();
    RandKey.append("9999");
    int[] array = {1,2,3,4,5,6,7,8,9,0}; 
    Random ran = new Random();
    ran.setSeed(System.currentTimeMillis());
    for(int i=0; i<5; i++) {
    	RandKey.append(array[ran.nextInt(10)]);
    }
    //삽입모드를 위한 랜덤키 생성
    model.addAttribute("RandKey", RandKey.toString());    
    
    return "naksinuri_original/naksinuri/info/m/riverfishing";
  }

  @RequestMapping({"/info/confirm.do"})
  public String confirm(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
    NaksinuriVO nak = this.service.searchCo_nm(naksinuriVO);
    model.addAttribute("test", nak.getNak_id());
    return "naksinuri_original/naksinuri/confirm";
  }

  @RequestMapping({"/info/insertCorp.do"})
  public String insertCorp(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {

    List result = null;
    List result2 = null;
    String co_mimgsrc = "mimg";
    String atchFileId = "";
    String mainatchFileId = "";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();

    mfile.clear();
    if (files.get(co_mimgsrc) != null) {
      mfile.put(co_mimgsrc, files.get(co_mimgsrc));
      files.remove(co_mimgsrc);
    }

    if (!mfile.isEmpty())
    {
      result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      for (int i = 0; i < result2.size(); i++) {
        if (((NaksinuriOriginalFileVO)result2.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      mainatchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result2);
    }

    if (!files.isEmpty())
    {
      result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < result.size(); i++) {
        if (((NaksinuriOriginalFileVO)result.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result);
    }
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    naksinuriVO.setAtch_file_id(atchFileId);
    naksinuriVO.setCo_mimgsrc(mainatchFileId);

    String co_nm = request.getParameter("co_nm");
    //String co_ship_num1 = request.getParameter("co_ship_num1");
    //String co_ship_num2 = request.getParameter("co_ship_num2");
    String ceo_nm = request.getParameter("ceo_nm");
    String co_web = request.getParameter("co_web");
    String co_addr3 = request.getParameter("co_addr3");
    String co_addr2_3 = request.getParameter("co_addr2_3");
    String co_stm = request.getParameter("co_stm");
    String co_etm = request.getParameter("co_etm");
    String bo_size = request.getParameter("bo_size");
    String bo_spd = request.getParameter("bo_spd");
    String bo_psg = request.getParameter("bo_psg");

    if (co_nm != null) {
      co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
      naksinuriVO.setCo_nm(co_nm);
    }

    if (ceo_nm != null) {
      ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
      naksinuriVO.setCeo_nm(ceo_nm);
    }
    if (co_web != null) {
      co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
      naksinuriVO.setCo_web(co_web);
    }
    if (co_addr3 != null) {
      co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
      naksinuriVO.setCo_addr_3(co_addr3);
    }
    if (co_addr2_3 != null) {
      co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
      naksinuriVO.setCo_addr2_3(co_addr2_3);
    }
    if (co_stm != null) {
      co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
      naksinuriVO.setCo_stm(co_stm);
    }
    if (co_etm != null) {
      co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
      naksinuriVO.setCo_etm(co_etm);
    }
    if (bo_size != null) {
      bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
      naksinuriVO.setBo_size(bo_size);
    }
    if (bo_spd != null) {
      bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
      bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
    }
    if (bo_psg != null) {
      naksinuriVO.setBo_spd(bo_spd);
      naksinuriVO.setBo_psg(bo_psg);
    }

    this.service.insertCorp(naksinuriVO);
    model.addAttribute("resultMsg", "success.common.insert");
    //return "forward:/info/confirm.do";
    return "forward:/info/fishjob/result.do";
  }
  @RequestMapping({"/info/m/insertCorp.do"})
  public String minsertCorp(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    
    List result = null;
    List result2 = null;
    String co_mimgsrc = "mimg";
    String atchFileId = "";
    String mainatchFileId = "";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();

    mfile.clear();
    if (files.get(co_mimgsrc) != null) {
      mfile.put(co_mimgsrc, files.get(co_mimgsrc));
      files.remove(co_mimgsrc);
    }

    if (!mfile.isEmpty())
    {
      result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      for (int i = 0; i < result2.size(); i++) {
        if (((NaksinuriOriginalFileVO)result2.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      mainatchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result2);
    }

    if (!files.isEmpty())
    {
      result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < result.size(); i++) {
        if (((NaksinuriOriginalFileVO)result.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result);
    }

    naksinuriVO.setAtch_file_id(atchFileId);
    naksinuriVO.setCo_mimgsrc(mainatchFileId);
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String co_nm = request.getParameter("co_nm");
    //String co_ship_num1 = request.getParameter("co_ship_num1");
    //String co_ship_num2 = request.getParameter("co_ship_num2");
    String ceo_nm = request.getParameter("ceo_nm");
    String co_web = request.getParameter("co_web");
    String co_addr3 = request.getParameter("co_addr3");
    String co_addr2_3 = request.getParameter("co_addr2_3");
    String co_stm = request.getParameter("co_stm");
    String co_etm = request.getParameter("co_etm");
    String bo_size = request.getParameter("bo_size");
    String bo_spd = request.getParameter("bo_spd");
    String bo_psg = request.getParameter("bo_psg");

    if (co_nm != null) {
      co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
      naksinuriVO.setCo_nm(co_nm);
    }

    if (ceo_nm != null) {
      ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
      naksinuriVO.setCeo_nm(ceo_nm);
    }
    if (co_web != null) {
      co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
      naksinuriVO.setCo_web(co_web);
    }
    if (co_addr3 != null) {
      co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
      naksinuriVO.setCo_addr_3(co_addr3);
    }
    if (co_addr2_3 != null) {
      co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
      naksinuriVO.setCo_addr2_3(co_addr2_3);
    }
    if (co_stm != null) {
      co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
      naksinuriVO.setCo_stm(co_stm);
    }
    if (co_etm != null) {
      co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
      naksinuriVO.setCo_etm(co_etm);
    }
    if (bo_size != null) {
      bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
      naksinuriVO.setBo_size(bo_size);
    }
    if (bo_spd != null) {
      bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
      bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
    }
    if (bo_psg != null) {
      naksinuriVO.setBo_spd(bo_spd);
      naksinuriVO.setBo_psg(bo_psg);
    }

    this.service.insertCorp(naksinuriVO);
    model.addAttribute("resultMsg", "success.common.insert");
    //return "forward:/info/confirm.do";
    return "forward:/info/m/fishjob/result.do";
  }

  /** 프리뷰 **/
  @RequestMapping({"/info/insertCorpPreview.do"})
  public String insertCorpPreview(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, NaksinuriFileVO naksinurifileVO,BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {

    List result = null;
    List result2 = null;
    String co_mimgsrc = "mimg";
    String atchFileId = "";
    String mainatchFileId = "";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();

    mfile.clear();
    if (files.get(co_mimgsrc) != null) {
      mfile.put(co_mimgsrc, files.get(co_mimgsrc));
      files.remove(co_mimgsrc);
    }

    if (!mfile.isEmpty())
    {
      result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      for (int i = 0; i < result2.size(); i++) {
        if (((NaksinuriOriginalFileVO)result2.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }
      mainatchFileId = this.fileMngService.insertFileInfsPreview_naksinuri_original(result2);
    }

    if (!files.isEmpty())
    {
      result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < result.size(); i++) {
        if (((NaksinuriOriginalFileVO)result.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }
      atchFileId = this.fileMngService.insertFileInfsPreview_naksinuri_original(result);
    }

    naksinuriVO.setAtch_file_id(atchFileId);
    naksinuriVO.setCo_mimgsrc(mainatchFileId);
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String nak_id = request.getParameter("nak_id");
    String co_nm = request.getParameter("co_nm");
    //String co_ship_num1 = request.getParameter("co_ship_num1");
    //String co_ship_num2 = request.getParameter("co_ship_num2");
    String ceo_nm = request.getParameter("ceo_nm");
    String co_web = request.getParameter("co_web");
    String co_addr3 = request.getParameter("co_addr3");
    String co_addr2_3 = request.getParameter("co_addr2_3");
    String co_stm = request.getParameter("co_stm");
    String co_etm = request.getParameter("co_etm");
    String bo_size = request.getParameter("bo_size");
    String bo_spd = request.getParameter("bo_spd");
    String bo_psg = request.getParameter("bo_psg");
    String co_intro = request.getParameter("co_intro");
    String insid = null;

    String save_mode = request.getParameter("save_mode");
    String pnak_id = request.getParameter("pnak_id");

    String save_status = request.getParameter("save_status");
    String client_ipaddr = request.getParameter("client_ipaddr");
    
    naksinuriVO.setCo_addr(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr()));
    naksinuriVO.setCo_addr_3(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr_3()));
    naksinuriVO.setCo_addr2(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr2()));
    naksinuriVO.setNak_id(nak_id);
    naksinuriVO.setSave_status(save_status);
    naksinuriVO.setClient_ipaddr(client_ipaddr);
    naksinuriVO.setPnak_id(pnak_id);
    
    LOGGER.debug("=================================>>>save_mode : "+save_mode);      
    LOGGER.debug("=================================>>> "+pnak_id);
    
    if (co_nm != null) {
      co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
      naksinuriVO.setCo_nm(co_nm);
    }
    if (ceo_nm != null) {
      ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
      naksinuriVO.setCeo_nm(ceo_nm);
    }
    if (co_web != null) {
      co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
      naksinuriVO.setCo_web(co_web);
    }
    if (co_addr3 != null) {
      co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
      naksinuriVO.setCo_addr_3(co_addr3);
    }
    if (co_addr2_3 != null) {
      co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
      naksinuriVO.setCo_addr2_3(co_addr2_3);
    }
    if (co_stm != null) {
      co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
      naksinuriVO.setCo_stm(co_stm);
    }
    if (co_etm != null) {
      co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
      naksinuriVO.setCo_etm(co_etm);
    }
    if (bo_size != null) {
      bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
      naksinuriVO.setBo_size(bo_size);
    }
    if (bo_spd != null) {
      bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
      bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
    }
    if (bo_psg != null) {
      naksinuriVO.setBo_spd(bo_spd);
      naksinuriVO.setBo_psg(bo_psg);
    }
    if (co_intro != null) {
      co_intro = mEgovStringUtil.getHtmlStrCnvr(co_intro);
      naksinuriVO.setCo_intro(co_intro);
    }
    
    

    if(!"w".equals(save_mode)) {
  	  	LOGGER.debug("=================================>>> preview");
  	  	this.service.insertCorpPreview(naksinuriVO);
    } else {
  	  	LOGGER.debug("=================================> previewp");
  	  	this.service.insertCorpPreviewP(naksinuriVO);
    }
    

    //model.addAttribute("resultMsg", "success.common.insert");
    //return "forward:/info/fishjob/m/fish_Preview.do";    

	NaksinuriFileVO mimg = this.service.admin_mimg(naksinurifileVO);
	NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);
	if(!"w".equals(save_mode)) {
		naksinurifileVO.setAtch_file_id(resultVO.getAtch_file_id());
	}
	List<NaksinuriFileVO> list = this.service.admin_searchFile1(naksinurifileVO);
	if(!"w".equals(save_mode)) {
		fishesVO.setFishing_type(resultVO.getFishing_type());
	}
		List fishlist = this.fishesService.fish_list(fishesVO);

	//String[] sido = resultVO.getCo_addr2_2().split(" ");
	
	model.addAttribute("fishlist", fishlist);
	model.addAttribute("info", resultVO);
	//model.addAttribute("filelist", list);
	model.addAttribute("mimg", mimg);
	//model.addAttribute("sido", sido);

	/* 프리뷰 영역 */
	NaksinuriVO preview = this.service.modifyCorpPreview_admin(naksinuriVO);
	
	LOGGER.debug("88888888888888888888888888888888888888888");
	LOGGER.debug("88888888888888888888888888888888888888888");
	LOGGER.debug(preview.getNak_id());
	LOGGER.debug("88888888888888888888888888888888888888888");
	LOGGER.debug(pnak_id);
	LOGGER.debug("88888888888888888888888888888888888888888");
	LOGGER.debug("88888888888888888888888888888888888888888");
	
	NaksinuriFileVO getpreview_mimg = naksinurifileVO;
	getpreview_mimg.setNak_id(Integer.parseInt(preview.getNak_id()));
	
	NaksinuriFileVO pmimg = this.service.adminPreview_mimg(getpreview_mimg);
	naksinurifileVO.setAtch_filePreview_id(preview.getAtch_filePreview_id());
	List<NaksinuriFileVO> plist = this.service.adminPreview_searchFile1(naksinurifileVO);
	fishesVO.setFishing_type(preview.getFishing_type());
	List pfishlist = this.fishesService.fishPreview_list(fishesVO);

	NaksinuriVO previewfileid = preview;
	if(!"w".equals(save_mode)) {
		previewfileid.setOnak_id(resultVO.getNak_id());
	} else {
		previewfileid.setOnak_id(pnak_id);
	}	
	      
	previewfileid.setNak_id(preview.getNak_id());       
	
	String[] psido = preview.getCo_addr2_2().split(" ");
	
	model.addAttribute("pfishlist", pfishlist);
	model.addAttribute("preview", preview);
	//model.addAttribute("pfilelist", plist);
	model.addAttribute("pmimg", pmimg);
	model.addAttribute("psido", psido);

	/*임시파일테이블에 처리한다 */
	// 등록된 프리뷰를 지운다. - 파일에서 키를 중복체크한다. 답이 읍다.. 중복수정을 막는다.
  	this.service.deletefishDataPreview(previewfileid);	// naksinurisanup_preview
    this.service.deletefishFilePreview(previewfileid);	// naksinuri_files_preview
	

	String checkVal_01 ="";
	String checkVal_02 ="";
	
	NaksinuriFileVO previewfile = naksinurifileVO;
	
	String isdelCheck = request.getParameter("delcheck");
	String[] delCheck = request.getParameterValues("delcheck");

	LOGGER.debug("=================================> "+list.size());
	
	//원본파일데이터 호출 및 저장
	if(!"w".equals(save_mode)) {
		LOGGER.debug("=================================> org_action ");
		for(int i=0;i<list.size();i++){
			String checkData = "N";	
		
			if(isdelCheck != null) {
				for(int j=0; j<delCheck.length; j++) {
					checkVal_01 = delCheck[j];
					checkVal_02 = list.get(i).getAtch_file_id()+"_"+list.get(i).getFile_sn();
					if(checkVal_01.equals(checkVal_02)){
						checkData = "Y";
					}
				}
			}
			//중복아닌거 체크
			if(checkData == "N"){
				previewfile.setOid(resultVO.getNak_id());
				previewfile.setPid(preview.getNak_id());
				previewfile.setPtype("A");
				previewfile.setWsess(client_ipaddr);
				previewfile.setStat(save_status);
				previewfile.setAtch_file_id(list.get(i).getAtch_file_id());
				previewfile.setFile_sn(list.get(i).getFile_sn());
				previewfile.setFile_stre_cours(list.get(i).getFile_stre_cours());
				previewfile.setStre_file_nm(list.get(i).getStre_file_nm());
				previewfile.setOrignl_file_nm(list.get(i).getOrignl_file_nm());
				previewfile.setFile_extsn(list.get(i).getFile_extsn());
				previewfile.setFile_cn(list.get(i).getFile_cn());
				previewfile.setFile_size(list.get(i).getFile_size());
				previewfile.setFile_hit(list.get(i).getFile_hit());
				this.service.fishInsertPreview(previewfile);
			}
	     }
	}
	
	//프리뷰파일데이터 호출 및 저장
	for(int i=0;i<plist.size();i++){
  		if(!"w".equals(save_mode)) {
  			previewfile.setOid(resultVO.getNak_id());
  		} else {
  	  		previewfile.setOid(pnak_id);
  		}
		previewfile.setPid(preview.getNak_id());
		previewfile.setPtype("A");
		previewfile.setWsess(client_ipaddr);
		previewfile.setStat(save_status);
		previewfile.setAtch_file_id(plist.get(i).getAtch_file_id());
		previewfile.setFile_sn(plist.get(i).getFile_sn());
		previewfile.setFile_stre_cours(plist.get(i).getFile_stre_cours());
		previewfile.setStre_file_nm(plist.get(i).getStre_file_nm());
		previewfile.setOrignl_file_nm(plist.get(i).getOrignl_file_nm());
		previewfile.setFile_extsn(plist.get(i).getFile_extsn());
		previewfile.setFile_cn(plist.get(i).getFile_cn());
		previewfile.setFile_size(plist.get(i).getFile_size());
		previewfile.setFile_hit(plist.get(i).getFile_hit());
		this.service.fishInsertPreview(previewfile);
     }

	LOGGER.debug("##############################");
	LOGGER.debug("##############################");
	LOGGER.debug(preview.getNak_id());
	LOGGER.debug("##############################");
	LOGGER.debug("##############################");
	
	
	List prelist = this.service.Preview_searchFile(previewfileid);
	model.addAttribute("prefilelist", prelist);
	/*임시파일테이블에 처리완료 */


	if(save_status.equals("w")) {
		return "naksinuri_original/naksinuri/info/fishjob_viewPV";
	} else {
		return "redirect:/info/fishjob/result.do";
	}
  }


  @RequestMapping({"/info/m/insertCorpPreview.do"})
  public String minsertCorpPreview(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO,  FishesVO fishesVO, FishjobReviewVO reviewVO, NaksinuriFileVO naksinurifileVO,BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {

	    List result = null;
	    List result2 = null;
	    String co_mimgsrc = "mimg";
	    String atchFileId = "";
	    String mainatchFileId = "";

	    Map files = multiRequest.getFileMap();
	    Map mfile = multiRequest.getFileMap();

	    mfile.clear();
	    if (files.get(co_mimgsrc) != null) {
	      mfile.put(co_mimgsrc, files.get(co_mimgsrc));
	      files.remove(co_mimgsrc);
	    }

	    if (!mfile.isEmpty())
	    {
	      result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
	      for (int i = 0; i < result2.size(); i++) {
	        if (((NaksinuriOriginalFileVO)result2.get(i)).atchFileId.equals("ext_error")) {
	          LOGGER.debug("파일에러");
	          return "redirect:/error/ext/warn.do";
	        }
	      }
	      mainatchFileId = this.fileMngService.insertFileInfsPreview_naksinuri_original(result2);
	    }

	    if (!files.isEmpty())
	    {
	      result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
	      for (int i = 0; i < result.size(); i++) {
	        if (((NaksinuriOriginalFileVO)result.get(i)).atchFileId.equals("ext_error")) {
	          LOGGER.debug("파일에러");
	          return "redirect:/error/ext/warn.do";
	        }
	      }
	      atchFileId = this.fileMngService.insertFileInfsPreview_naksinuri_original(result);
	    }

	    naksinuriVO.setAtch_file_id(atchFileId);
	    naksinuriVO.setCo_mimgsrc(mainatchFileId);
	    
	    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

	    String nak_id = request.getParameter("nak_id");
	    String co_nm = request.getParameter("co_nm");
	    //String co_ship_num1 = request.getParameter("co_ship_num1");
	    //String co_ship_num2 = request.getParameter("co_ship_num2");
	    String ceo_nm = request.getParameter("ceo_nm");
	    String co_web = request.getParameter("co_web");
	    String co_addr3 = request.getParameter("co_addr3");
	    String co_addr2_3 = request.getParameter("co_addr2_3");
	    String co_stm = request.getParameter("co_stm");
	    String co_etm = request.getParameter("co_etm");
	    String bo_size = request.getParameter("bo_size");
	    String bo_spd = request.getParameter("bo_spd");
	    String bo_psg = request.getParameter("bo_psg");
	    String co_intro = request.getParameter("co_intro");
	    String insid = null;

	    String save_mode = request.getParameter("save_mode");
	    String pnak_id = request.getParameter("pnak_id");

	    String save_status = request.getParameter("save_status");
	    String client_ipaddr = request.getParameter("client_ipaddr");
	    
	    naksinuriVO.setNak_id(nak_id);
	    naksinuriVO.setSave_status(save_status);
	    naksinuriVO.setClient_ipaddr(client_ipaddr);
	    naksinuriVO.setPnak_id(pnak_id);
	    
	    LOGGER.debug("=================================>>>save_mode : "+save_mode);      
	    LOGGER.debug("=================================>>> "+pnak_id);
	    
	    if (co_nm != null) {
	      co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
	      naksinuriVO.setCo_nm(co_nm);
	    }
	    if (ceo_nm != null) {
	      ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
	      naksinuriVO.setCeo_nm(ceo_nm);
	    }
	    if (co_web != null) {
	      co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
	      naksinuriVO.setCo_web(co_web);
	    }
	    if (co_addr3 != null) {
	      co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
	      naksinuriVO.setCo_addr_3(co_addr3);
	    }
	    if (co_addr2_3 != null) {
	      co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
	      naksinuriVO.setCo_addr2_3(co_addr2_3);
	    }
	    if (co_stm != null) {
	      co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
	      naksinuriVO.setCo_stm(co_stm);
	    }
	    if (co_etm != null) {
	      co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
	      naksinuriVO.setCo_etm(co_etm);
	    }
	    if (bo_size != null) {
	      bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
	      naksinuriVO.setBo_size(bo_size);
	    }
	    if (bo_spd != null) {
	      bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
	      bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
	    }
	    if (bo_psg != null) {
	      naksinuriVO.setBo_spd(bo_spd);
	      naksinuriVO.setBo_psg(bo_psg);
	    }
	    if (co_intro != null) {
	      co_intro = mEgovStringUtil.getHtmlStrCnvr(co_intro);
	      naksinuriVO.setCo_intro(co_intro);
	    }

	    if(!"w".equals(save_mode)) {
	  	  	LOGGER.debug("=================================>>> preview");
	  	  	this.service.insertCorpPreview(naksinuriVO);
	    } else {
	  	  	LOGGER.debug("=================================> previewp");
	  	  	this.service.insertCorpPreviewP(naksinuriVO);
	    }
	    

	    //model.addAttribute("resultMsg", "success.common.insert");
	    //return "forward:/info/fishjob/m/fish_Preview.do";    

		NaksinuriFileVO mimg = this.service.admin_mimg(naksinurifileVO);
		NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);
		if(!"w".equals(save_mode)) {
			naksinurifileVO.setAtch_file_id(resultVO.getAtch_file_id());
		}
		List<NaksinuriFileVO> list = this.service.admin_searchFile1(naksinurifileVO);
		if(!"w".equals(save_mode)) {
			fishesVO.setFishing_type(resultVO.getFishing_type());
		}
			List fishlist = this.fishesService.fish_list(fishesVO);

		//String[] sido = resultVO.getCo_addr2_2().split(" ");
		
		model.addAttribute("fishlist", fishlist);
		model.addAttribute("info", resultVO);
		//model.addAttribute("filelist", list);
		model.addAttribute("mimg", mimg);
		//model.addAttribute("sido", sido);

		/* 프리뷰 영역 */
		NaksinuriVO preview = this.service.modifyCorpPreview_admin(naksinuriVO);
		
		LOGGER.debug("88888888888888888888888888888888888888888");
		LOGGER.debug("88888888888888888888888888888888888888888");
		LOGGER.debug(preview.getNak_id());
		LOGGER.debug("88888888888888888888888888888888888888888");
		LOGGER.debug(pnak_id);
		LOGGER.debug("88888888888888888888888888888888888888888");
		LOGGER.debug("88888888888888888888888888888888888888888");
		
		NaksinuriFileVO getpreview_mimg = naksinurifileVO;
		getpreview_mimg.setNak_id(Integer.parseInt(preview.getNak_id()));
		
		NaksinuriFileVO pmimg = this.service.adminPreview_mimg(getpreview_mimg);
		naksinurifileVO.setAtch_filePreview_id(preview.getAtch_filePreview_id());
		List<NaksinuriFileVO> plist = this.service.adminPreview_searchFile1(naksinurifileVO);
		fishesVO.setFishing_type(preview.getFishing_type());
		List pfishlist = this.fishesService.fishPreview_list(fishesVO);

		NaksinuriVO previewfileid = preview;
		if(!"w".equals(save_mode)) {
			previewfileid.setOnak_id(resultVO.getNak_id());
		} else {
			previewfileid.setOnak_id(pnak_id);
		}	
		      
		previewfileid.setNak_id(preview.getNak_id());       
		
		String[] psido = preview.getCo_addr2_2().split(" ");
		
		model.addAttribute("pfishlist", pfishlist);
		model.addAttribute("preview", preview);
		//model.addAttribute("pfilelist", plist);
		model.addAttribute("pmimg", pmimg);
		model.addAttribute("psido", psido);

		/*임시파일테이블에 처리한다 */
		// 등록된 프리뷰를 지운다. - 파일에서 키를 중복체크한다. 답이 읍다.. 중복수정을 막는다.
	  	this.service.deletefishDataPreview(previewfileid);	// naksinurisanup_preview
	    this.service.deletefishFilePreview(previewfileid);	// naksinuri_files_preview
		

		String checkVal_01 ="";
		String checkVal_02 ="";
		
		NaksinuriFileVO previewfile = naksinurifileVO;
		
		String isdelCheck = request.getParameter("delcheck");
		String[] delCheck = request.getParameterValues("delcheck");

		LOGGER.debug("=================================> "+list.size());
		
		//원본파일데이터 호출 및 저장
		if(!"w".equals(save_mode)) {
			LOGGER.debug("=================================> org_action ");
			for(int i=0;i<list.size();i++){
				String checkData = "N";	
			
				if(isdelCheck != null) {
					for(int j=0; j<delCheck.length; j++) {
						checkVal_01 = delCheck[j];
						checkVal_02 = list.get(i).getAtch_file_id()+"_"+list.get(i).getFile_sn();
						if(checkVal_01.equals(checkVal_02)){
							checkData = "Y";
						}
					}
				}
				//중복아닌거 체크
				if(checkData == "N"){
					previewfile.setOid(resultVO.getNak_id());
					previewfile.setPid(preview.getNak_id());
					previewfile.setPtype("A");
					previewfile.setWsess(client_ipaddr);
					previewfile.setStat(save_status);
					previewfile.setAtch_file_id(list.get(i).getAtch_file_id());
					previewfile.setFile_sn(list.get(i).getFile_sn());
					previewfile.setFile_stre_cours(list.get(i).getFile_stre_cours());
					previewfile.setStre_file_nm(list.get(i).getStre_file_nm());
					previewfile.setOrignl_file_nm(list.get(i).getOrignl_file_nm());
					previewfile.setFile_extsn(list.get(i).getFile_extsn());
					previewfile.setFile_cn(list.get(i).getFile_cn());
					previewfile.setFile_size(list.get(i).getFile_size());
					previewfile.setFile_hit(list.get(i).getFile_hit());
					this.service.fishInsertPreview(previewfile);
				}
		     }
		}
		
		//프리뷰파일데이터 호출 및 저장
		for(int i=0;i<plist.size();i++){
	  		if(!"w".equals(save_mode)) {
	  			previewfile.setOid(resultVO.getNak_id());
	  		} else {
	  	  		previewfile.setOid(pnak_id);
	  		}
			previewfile.setPid(preview.getNak_id());
			previewfile.setPtype("A");
			previewfile.setWsess(client_ipaddr);
			previewfile.setStat(save_status);
			previewfile.setAtch_file_id(plist.get(i).getAtch_file_id());
			previewfile.setFile_sn(plist.get(i).getFile_sn());
			previewfile.setFile_stre_cours(plist.get(i).getFile_stre_cours());
			previewfile.setStre_file_nm(plist.get(i).getStre_file_nm());
			previewfile.setOrignl_file_nm(plist.get(i).getOrignl_file_nm());
			previewfile.setFile_extsn(plist.get(i).getFile_extsn());
			previewfile.setFile_cn(plist.get(i).getFile_cn());
			previewfile.setFile_size(plist.get(i).getFile_size());
			previewfile.setFile_hit(plist.get(i).getFile_hit());
			this.service.fishInsertPreview(previewfile);
	     }

		LOGGER.debug("##############################");
		LOGGER.debug("##############################");
		LOGGER.debug(preview.getNak_id());
		LOGGER.debug("##############################");
		LOGGER.debug("##############################");
		
		
		List prelist = this.service.Preview_searchFile(previewfileid);
		model.addAttribute("prefilelist", prelist);
		/*임시파일테이블에 처리완료 */

	if(save_status.equals("w")) {
		return "naksinuri_original/naksinuri/info/m/fishjob_viewPV";
	} else {
		return "redirect:/info/m/fishjob/result.do";
	}

  }  
  
  @RequestMapping({"/info/insertCorpSanupPreview.do"})
  public String insertCorpSanupPreview(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, NaksinuriFileVO naksinurifileVO,BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
      List _result = null;
      List _result2 = null;
      String mainFileId = "";
      String atchFileId = "";
      String san_img = "mimg";
      
      Map mfile = multiRequest.getFileMap();
      Map files = multiRequest.getFileMap();
 
      mfile.clear();
      if (files.get(san_img) != null) {
        mfile.put(san_img, files.get(san_img));
        files.remove(san_img);
      }

      if (!mfile.isEmpty()) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
	    for (int i = 0; i < _result.size(); i++) {
	        if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
	          LOGGER.debug("파일에러");
	          return "redirect:/error/ext/warn.do";
	        }
	    }
	    mainFileId = this.fileMngService.insertFileInfsPreview_naksinuri_original(_result);
      }

      if (!files.isEmpty()) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
	    for (int i = 0; i < _result2.size(); i++) {
	        if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
	          LOGGER.debug("파일에러");
	          return "redirect:/error/ext/warn.do";
	        }
	    }
	    atchFileId = this.fileMngService.insertFileInfsPreview_naksinuri_original(_result2);
      }
 
      SanupVO.setSan_simg(atchFileId);
      SanupVO.setSan_img(mainFileId);
      
      EgovStringUtil mEgovStringUtil = new EgovStringUtil();

      String san_sn = request.getParameter("san_sn");
      String san_name = request.getParameter("san_name");
      String san_buisnessman = request.getParameter("san_buisnessman");
      String san_item = request.getParameter("san_item");
      String san_address3 = request.getParameter("san_address3");
      String san_content = request.getParameter("san_content");
      
      if(san_name != null){
    	  san_name = mEgovStringUtil.getHtmlStrCnvr(san_name);
    	  SanupVO.setSan_name(san_name);
      }
      if(san_buisnessman != null){
    	  san_buisnessman = mEgovStringUtil.getHtmlStrCnvr(san_buisnessman);
    	  SanupVO.setSan_buisnessman(san_buisnessman);
      }
      if(san_item != null){
    	  san_item = mEgovStringUtil.getHtmlStrCnvr(san_item);
    	  SanupVO.setSan_item(san_item);
      }
      if(san_address3 != null){
    	  san_address3 = mEgovStringUtil.getHtmlStrCnvr(san_address3);
    	  SanupVO.setSan_address3(san_address3);
      }
      if(san_content != null){
    	  san_content = mEgovStringUtil.getHtmlStrCnvr(san_content);
    	  SanupVO.setSan_content(san_content);
      }

      String save_status = request.getParameter("save_status");
      String client_ipaddr = request.getParameter("client_ipaddr");
      String save_mode = request.getParameter("save_mode");
      String psan_sn = request.getParameter("psan_sn");
      
      SanupVO.setSan_address(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_address()));
   	  SanupVO.setSan_sn(san_sn);    	  
   	  SanupVO.setPsan_sn(psan_sn);
      SanupVO.setSave_status(save_status);
      SanupVO.setClient_ipaddr(client_ipaddr);
      
      
      

      LOGGER.debug("=================================>>> save_mode = "+save_mode);      
      LOGGER.debug("=================================>>> "+psan_sn);      
      
      if(!"w".equals(save_mode)) {
    	  LOGGER.debug("=================================>>> preview");
    	  this.service_sanup.ind_insert_Preview(SanupVO);
      } else {
    	  LOGGER.debug("=================================> previewp");
          this.service_sanup.ind_insert_PreviewP(SanupVO);
      }
      
      LOGGER.debug("=================================> ind_find");
      NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
      
      LOGGER.debug("=================================> ind_fsimg");
      List<NaksinuriSanupVO> list = this.service_sanup.ind_fsimg(SanupVO);

      model.addAttribute("info", info);
      //model.addAttribute("simglist", list);
      
      LOGGER.debug("=================================> ind_find_Preview");
      NaksinuriSanupVO preview = this.service_sanup.ind_find_Preview(SanupVO);
      
      /*
       * 너무 일찍 불렀어 아래에서 불러야지 신청한지 모르고 또 하니  이전꺼까지 나오지 지운나음에 불러야지 아래로 이동했다.
      LOGGER.debug("=================================> ind_fsimg_Preview");
      List<NaksinuriSanupVO> plist = this.service_sanup.ind_fsimg_Preview(preview);
      LOGGER.debug("=================================>>> 쿼리후 plist size = "+plist.size());
      */ 
      
      
      model.addAttribute("preview", preview);
      //model.addAttribute("psimglist", plist);

  	
    /*임시파일테이블에 처리한다 */
  	// 등록된 프리뷰를 지운다. - 파일에서 키를 중복체크한다. 답이 읍다.. 중복수정을 막는다.
	NaksinuriSanupVO previewfileid = preview;
	if(!"w".equals(save_mode)) {
		previewfileid.setOsan_sn(SanupVO.getSan_sn());
	} else {
		previewfileid.setOsan_sn(psan_sn);
	}
	previewfileid.setSan_sn(preview.getSan_sn());   
	
  	this.service.deleteindDataPreview(previewfileid);	// naksinurisanup_preview
    this.service.deleteindFilePreview(previewfileid);	// naksinuri_files_preview
      	
	String isdelCheck = request.getParameter("delcheck");
 	String[] delCheck = request.getParameterValues("delcheck");

  	String checkVal_01 ="";
  	String checkVal_02 ="";

  	NaksinuriSanupVO previewfile = SanupVO;
  	
  	//원본파일데이터 호출 및 저장
  	
	if(!"w".equals(save_mode)) {
		LOGGER.debug("=================================> org action");
	 	for(int i=0;i<list.size();i++){
		  	String checkData = "N";
		  	if(isdelCheck != null) {
		  		for(int j=0; j<delCheck.length; j++) {
		
		  			checkVal_01 = delCheck[j];
		  			checkVal_02 = list.get(i).getAtch_file_id()+"_"+list.get(i).getFile_sn();
		
		  			if(checkVal_01.equals(checkVal_02)){
		  				checkData = "Y";
		  			}
		  		}
		  	}
	  		
	  		//중복아닌거 체크
	  		if(checkData == "N"){
	  			previewfile.setOid(info.getSan_sn());
	  			previewfile.setPid(preview.getSan_sn());
	  			previewfile.setPtype("B");
	  			previewfile.setWsess(client_ipaddr);
	  			previewfile.setStat(save_status);
	  			previewfile.setAtch_file_id(list.get(i).getAtch_file_id());
	  			previewfile.setFile_sn(list.get(i).getFile_sn());
	  			previewfile.setFile_stre_cours(list.get(i).getFile_stre_cours());
	  			previewfile.setStre_file_nm(list.get(i).getStre_file_nm());
	  			previewfile.setOrignl_file_nm(list.get(i).getOrignl_file_nm());
	  			previewfile.setFile_extsn(list.get(i).getFile_extsn());
	  			previewfile.setFile_cn(list.get(i).getFile_cn());
	  			previewfile.setFile_size(list.get(i).getFile_size());
	  			previewfile.setFile_hit(list.get(i).getFile_hit());
	  			this.service.indInsertPreview(previewfile);
	  		}
	    }
    }
  	
  	//프리뷰파일데이터 호출 및 저장
	
	 List<NaksinuriSanupVO> plist = this.service_sanup.ind_fsimg_Preview(SanupVO);
	
	LOGGER.debug("=================================>>> plist size = "+plist.size());    
	
	// plist가 2번씩 나옴.. 이유를 정확히 모르겠다..  sanupVO하나를 써서 그런가.. .일단 두번씩 나오므로... /2를 해서 화면에 뿌린다.. ㅡㅡ;
  	for(int i=0;i<plist.size();i++){
  		if(!"w".equals(save_mode)) {
  			previewfile.setOid(SanupVO.getSan_sn());
  		} else {
  	  		previewfile.setOid(psan_sn);
  		}
  		previewfile.setPid(preview.getSan_sn());
  		previewfile.setPtype("B");
  		previewfile.setWsess(client_ipaddr);
  		previewfile.setStat(save_status);
  		previewfile.setAtch_file_id(plist.get(i).getAtch_file_id());
  		previewfile.setFile_sn(plist.get(i).getFile_sn());
  		previewfile.setFile_stre_cours(plist.get(i).getFile_stre_cours());
  		previewfile.setStre_file_nm(plist.get(i).getStre_file_nm());
  		previewfile.setOrignl_file_nm(plist.get(i).getOrignl_file_nm());
  		previewfile.setFile_extsn(plist.get(i).getFile_extsn());
  		previewfile.setFile_cn(plist.get(i).getFile_cn());
  		previewfile.setFile_size(plist.get(i).getFile_size());
  		previewfile.setFile_hit(plist.get(i).getFile_hit());
  		this.service.indInsertPreview(previewfile);
  	}

  	List<NaksinuriSanupVO> prelist = this.service.Preview_searchFile2(previewfileid);
  	model.addAttribute("prefilelist", prelist);
  	/*임시파일테이블에 처리완료 */
    //List<String> newList = new ArrayList<String>(list); newList.addAll(plist);
	//model.addAttribute("newList", newList);
  	
	if(save_status.equals("w")) {
		return "naksinuri_original/naksinuri/info/industry_viewPV";
	} else {
		return "redirect:/info/industry/result.do";
	}
  }

  @RequestMapping({"/info/m/insertCorpSanupPreview.do"})
  public String minsertCorpSanupPreview(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
      List _result = null;
      List _result2 = null;
      String mainFileId = "";
      String atchFileId = "";
      String san_img = "mimg";
      
      Map mfile = multiRequest.getFileMap();
      Map files = multiRequest.getFileMap();
 
      mfile.clear();
      if (files.get(san_img) != null) {
        mfile.put(san_img, files.get(san_img));
        files.remove(san_img);
      }

      if (!mfile.isEmpty()) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
	    for (int i = 0; i < _result.size(); i++) {
	        if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
	          LOGGER.debug("파일에러");
	          return "redirect:/error/ext/warn.do";
	        }
	    }
	    mainFileId = this.fileMngService.insertFileInfsPreview_naksinuri_original(_result);
      }

      if (!files.isEmpty()) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
	    for (int i = 0; i < _result2.size(); i++) {
	        if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
	          LOGGER.debug("파일에러");
	          return "redirect:/error/ext/warn.do";
	        }
	    }
	    atchFileId = this.fileMngService.insertFileInfsPreview_naksinuri_original(_result2);
      }
      
      SanupVO.setSan_simg(atchFileId);
      SanupVO.setSan_img(mainFileId);
      EgovStringUtil mEgovStringUtil = new EgovStringUtil();

      String san_sn = request.getParameter("san_sn");
      String san_name = request.getParameter("san_name");
      String san_buisnessman = request.getParameter("san_buisnessman");
      String san_item = request.getParameter("san_item");
      String san_address3 = request.getParameter("san_address3");
      String san_content = request.getParameter("san_content");
      
      if(san_name != null){
    	  san_name = mEgovStringUtil.getHtmlStrCnvr(san_name);
    	  SanupVO.setSan_name(san_name);
      }
      if(san_buisnessman != null){
    	  san_buisnessman = mEgovStringUtil.getHtmlStrCnvr(san_buisnessman);
    	  SanupVO.setSan_buisnessman(san_buisnessman);
      }
      if(san_item != null){
    	  san_item = mEgovStringUtil.getHtmlStrCnvr(san_item);
    	  SanupVO.setSan_item(san_item);
      }
      if(san_address3 != null){
    	  san_address3 = mEgovStringUtil.getHtmlStrCnvr(san_address3);
    	  SanupVO.setSan_address3(san_address3);
      }
      if(san_content != null){
    	  san_content = mEgovStringUtil.getHtmlStrCnvr(san_content);
    	  SanupVO.setSan_content(san_content);
      }
      
      String save_status = request.getParameter("save_status");
      String client_ipaddr = request.getParameter("client_ipaddr");
      String save_mode = request.getParameter("save_mode");
      String psan_sn = request.getParameter("psan_sn");
      
   	  SanupVO.setSan_sn(san_sn);    	  
   	  SanupVO.setPsan_sn(psan_sn);
      SanupVO.setSave_status(save_status);
      SanupVO.setClient_ipaddr(client_ipaddr);

      LOGGER.debug("=================================>>> save_mode = "+save_mode);      
      LOGGER.debug("=================================>>> "+psan_sn);      
      
      if(!"w".equals(save_mode)) {
    	  LOGGER.debug("=================================>>> preview");
    	  this.service_sanup.ind_insert_Preview(SanupVO);
      } else {
    	  LOGGER.debug("=================================> previewp");
          this.service_sanup.ind_insert_PreviewP(SanupVO);
      }
      
      LOGGER.debug("=================================> ind_find START");
      NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
      
      LOGGER.debug("=================================> ind_fsimg START");
      List<NaksinuriSanupVO> list = this.service_sanup.ind_fsimg(SanupVO);

      model.addAttribute("info", info);
      //model.addAttribute("simglist", list);
      
      NaksinuriSanupVO preview = this.service_sanup.ind_find_Preview(SanupVO);
      LOGGER.debug("=================================> ind_fsimg_Preview START");
      
      
      
      model.addAttribute("preview", preview);
      //model.addAttribute("psimglist", plist);

  	
    /*임시파일테이블에 처리한다 */
  	// 등록된 프리뷰를 지운다. - 파일에서 키를 중복체크한다. 답이 읍다.. 중복수정을 막는다.
	NaksinuriSanupVO previewfileid = preview;
	if(!"w".equals(save_mode)) {
		previewfileid.setOsan_sn(SanupVO.getSan_sn());
	} else {
		previewfileid.setOsan_sn(psan_sn);
	}
	previewfileid.setSan_sn(preview.getSan_sn());   
	
  	this.service.deleteindDataPreview(previewfileid);	// naksinurisanup_preview
    this.service.deleteindFilePreview(previewfileid);	// naksinuri_files_preview
      	
	String isdelCheck = request.getParameter("delcheck");
 	String[] delCheck = request.getParameterValues("delcheck");

  	String checkVal_01 ="";
  	String checkVal_02 ="";

  	NaksinuriSanupVO previewfile = SanupVO;
  	
  	//원본파일데이터 호출 및 저장
  	
	if(!"w".equals(save_mode)) {
		LOGGER.debug("=================================> org action");
	 	for(int i=0;i<list.size();i++){
		  	String checkData = "N";
		  	if(isdelCheck != null) {
		  		for(int j=0; j<delCheck.length; j++) {
		
		  			checkVal_01 = delCheck[j];
		  			checkVal_02 = list.get(i).getAtch_file_id()+"_"+list.get(i).getFile_sn();
		
		  			if(checkVal_01.equals(checkVal_02)){
		  				checkData = "Y";
		  			}
		  		}
		  	}
	  		
	  		//중복아닌거 체크
	  		if(checkData == "N"){
	  			previewfile.setOid(info.getSan_sn());
	  			previewfile.setPid(preview.getSan_sn());
	  			previewfile.setPtype("B");
	  			previewfile.setWsess(client_ipaddr);
	  			previewfile.setStat(save_status);
	  			previewfile.setAtch_file_id(list.get(i).getAtch_file_id());
	  			previewfile.setFile_sn(list.get(i).getFile_sn());
	  			previewfile.setFile_stre_cours(list.get(i).getFile_stre_cours());
	  			previewfile.setStre_file_nm(list.get(i).getStre_file_nm());
	  			previewfile.setOrignl_file_nm(list.get(i).getOrignl_file_nm());
	  			previewfile.setFile_extsn(list.get(i).getFile_extsn());
	  			previewfile.setFile_cn(list.get(i).getFile_cn());
	  			previewfile.setFile_size(list.get(i).getFile_size());
	  			previewfile.setFile_hit(list.get(i).getFile_hit());
	  			this.service.indInsertPreview(previewfile);
	  		}
	    }
    }
  	
  	//프리뷰파일데이터 호출 및 저장
	
	List<NaksinuriSanupVO> plist = this.service_sanup.ind_fsimg_Preview(preview);
    LOGGER.debug("=================================> ind_fsimg_Preview END");
	
	LOGGER.debug("=================================>>> "+plist.size());    
	
	// plist가 2번씩 나옴.. 이유를 정확히 모르겠다..  sanupVO하나를 써서 그런가.. .일단 두번씩 나오므로... /2를 해서 화면에 뿌린다.. ㅡㅡ;
  	for(int i=0;i<plist.size();i++){
  		if(!"w".equals(save_mode)) {
  			previewfile.setOid(SanupVO.getSan_sn());
  		} else {
  	  		previewfile.setOid(psan_sn);
  		}
  		previewfile.setPid(preview.getSan_sn());
  		previewfile.setPtype("B");
  		previewfile.setWsess(client_ipaddr);
  		previewfile.setStat(save_status);
  		previewfile.setAtch_file_id(plist.get(i).getAtch_file_id());
  		previewfile.setFile_sn(plist.get(i).getFile_sn());
  		previewfile.setFile_stre_cours(plist.get(i).getFile_stre_cours());
  		previewfile.setStre_file_nm(plist.get(i).getStre_file_nm());
  		previewfile.setOrignl_file_nm(plist.get(i).getOrignl_file_nm());
  		previewfile.setFile_extsn(plist.get(i).getFile_extsn());
  		previewfile.setFile_cn(plist.get(i).getFile_cn());
  		previewfile.setFile_size(plist.get(i).getFile_size());
  		previewfile.setFile_hit(plist.get(i).getFile_hit());
  		this.service.indInsertPreview(previewfile);
  	}

  	List<NaksinuriSanupVO> prelist = this.service.Preview_searchFile2(previewfileid);
  	model.addAttribute("prefilelist", prelist);
  	/*임시파일테이블에 처리완료 */
    //List<String> newList = new ArrayList<String>(list); newList.addAll(plist);
	//model.addAttribute("newList", newList);
  	
	if(save_status.equals("w")) {
		return "naksinuri_original/naksinuri/info/m/industry_viewPV";
	} else {
		return "redirect:/info/m/industry/result.do";
	}     
      
  }
 
  
  /** 프리뷰 **/
  
  @RequestMapping({"/info/updateInfo.do"})
  public String updateInfo(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, BindingResult bindingResult, ModelMap model, String returnUrl, HttpServletRequest request)
    throws Exception
  {
    String atchFileId = naksinuriVO.getAtch_file_id();
    String mainatchFileId = naksinuriVO.getCo_mimgsrc();
    String co_mimgsrc = "mimg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    mfile.clear();
    if (files.get(co_mimgsrc) != null) {
      mfile.put(co_mimgsrc, files.get(co_mimgsrc));
      files.remove(co_mimgsrc);
    }

    if (!files.isEmpty())
    {
      if (("".equals(atchFileId)) || (atchFileId == null)) {
        List result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, atchFileId, "");
        for (int i = 0; i < result.size(); i++) {
          if (((NaksinuriOriginalFileVO)result.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result);
        naksinuriVO.setAtch_file_id(atchFileId);
      } else if ((!"".equals(atchFileId)) || (atchFileId != null))
      {
        NaksinuriOriginalFileVO fvo = new NaksinuriOriginalFileVO();
        fvo.setAtchFileId(atchFileId);
        int cnt = this.fileMngService.getMaxFileSN_naksinuri_original(fvo);
        List _result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", cnt, atchFileId, "");

        for (int i = 0; i < _result.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }

    }

    if (!mfile.isEmpty()) {
      if (("".equals(mainatchFileId)) || (mainatchFileId == null)) {
        List result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, mainatchFileId, "");
        for (int i = 0; i < result2.size(); i++) {
          if (((NaksinuriOriginalFileVO)result2.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        mainatchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result2);
        naksinuriVO.setCo_mimgsrc(mainatchFileId);
      } else if ((!"".equals(mainatchFileId)) || (mainatchFileId != null)) {
        NaksinuriOriginalFileVO fvo2 = new NaksinuriOriginalFileVO();
        fvo2.setAtchFileId(mainatchFileId);
        int cnt2 = this.fileMngService.getMaxFileSN_naksinuri_original(fvo2);
        List _result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", cnt2, mainatchFileId, "");
        for (int i = 0; i < _result2.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result2);
      }

    }
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    
    String co_nm = request.getParameter("co_nm");
    //String co_ship_num1 = request.getParameter("co_ship_num1");
    //String co_ship_num2 = request.getParameter("co_ship_num2");
    String ceo_nm = request.getParameter("ceo_nm");
    String co_web = request.getParameter("co_web");
    String co_addr3 = request.getParameter("co_addr3");
    String co_addr2_3 = request.getParameter("co_addr2_3");
    String co_stm = request.getParameter("co_stm");
    String co_etm = request.getParameter("co_etm");
    String bo_size = request.getParameter("bo_size");
    String bo_spd = request.getParameter("bo_spd");
    String bo_psg = request.getParameter("bo_psg");

    if (co_nm != null) {
      co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
      naksinuriVO.setCo_nm(co_nm);
    }

    if (ceo_nm != null) {
      ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
      naksinuriVO.setCeo_nm(ceo_nm);
    }
    if (co_web != null) {
      co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
      naksinuriVO.setCo_web(co_web);
    }
    if (co_addr3 != null) {
      co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
      naksinuriVO.setCo_addr_3(co_addr3);
    }
    if (co_addr2_3 != null) {
      co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
      naksinuriVO.setCo_addr2_3(co_addr2_3);
    }
    if (co_stm != null) {
      co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
      naksinuriVO.setCo_stm(co_stm);
    }
    if (co_etm != null) {
      co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
      naksinuriVO.setCo_etm(co_etm);
    }
    if (bo_size != null) {
      bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
      naksinuriVO.setBo_size(bo_size);
    }
    if (bo_spd != null) {
      bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
      bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
    }
    if (bo_psg != null) {
      naksinuriVO.setBo_spd(bo_spd);
      naksinuriVO.setBo_psg(bo_psg);
    }

    this.service.updateInfo(naksinuriVO);
    model.addAttribute("resultMsg", "success.common.update");
    //return "forward:/info/confirm.do";
    return "redirect:/info/fishjob/result.do";
  }
  @RequestMapping({"/info/m/updateInfo.do"})
  public String mupdateInfo(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, BindingResult bindingResult, ModelMap model, String returnUrl, HttpServletRequest request)
    throws Exception
  {
    String atchFileId = naksinuriVO.getAtch_file_id();
    String mainatchFileId = naksinuriVO.getCo_mimgsrc();
    String co_mimgsrc = "mimg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    mfile.clear();
    if (files.get(co_mimgsrc) != null) {
      mfile.put(co_mimgsrc, files.get(co_mimgsrc));
      files.remove(co_mimgsrc);
    }

    if (!files.isEmpty())
    {
      if (("".equals(atchFileId)) || (atchFileId == null)) {
        List result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, atchFileId, "");
        for (int i = 0; i < result.size(); i++) {
          if (((NaksinuriOriginalFileVO)result.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result);
        naksinuriVO.setAtch_file_id(atchFileId);
      } else if ((!"".equals(atchFileId)) || (atchFileId != null))
      {
        NaksinuriOriginalFileVO fvo = new NaksinuriOriginalFileVO();
        fvo.setAtchFileId(atchFileId);
        int cnt = this.fileMngService.getMaxFileSN_naksinuri_original(fvo);
        List _result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", cnt, atchFileId, "");

        for (int i = 0; i < _result.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }

    }

    if (!mfile.isEmpty()) {
      if (("".equals(mainatchFileId)) || (mainatchFileId == null)) {
        List result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, mainatchFileId, "");
        for (int i = 0; i < result2.size(); i++) {
          if (((NaksinuriOriginalFileVO)result2.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        mainatchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result2);
        naksinuriVO.setCo_mimgsrc(mainatchFileId);
      } else if ((!"".equals(mainatchFileId)) || (mainatchFileId != null)) {
        NaksinuriOriginalFileVO fvo2 = new NaksinuriOriginalFileVO();
        fvo2.setAtchFileId(mainatchFileId);
        int cnt2 = this.fileMngService.getMaxFileSN_naksinuri_original(fvo2);
        List _result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", cnt2, mainatchFileId, "");
        for (int i = 0; i < _result2.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result2);
      }

    }
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String co_nm = request.getParameter("co_nm");
    //String co_ship_num1 = request.getParameter("co_ship_num1");
    //String co_ship_num2 = request.getParameter("co_ship_num2");
    String ceo_nm = request.getParameter("ceo_nm");
    String co_web = request.getParameter("co_web");
    String co_addr3 = request.getParameter("co_addr3");
    String co_addr2_3 = request.getParameter("co_addr2_3");
    String co_stm = request.getParameter("co_stm");
    String co_etm = request.getParameter("co_etm");
    String bo_size = request.getParameter("bo_size");
    String bo_spd = request.getParameter("bo_spd");
    String bo_psg = request.getParameter("bo_psg");

    if (co_nm != null) {
      co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
      naksinuriVO.setCo_nm(co_nm);
    }

    if (ceo_nm != null) {
      ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
      naksinuriVO.setCeo_nm(ceo_nm);
    }
    if (co_web != null) {
      co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
      naksinuriVO.setCo_web(co_web);
    }
    if (co_addr3 != null) {
      co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
      naksinuriVO.setCo_addr_3(co_addr3);
    }
    if (co_addr2_3 != null) {
      co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
      naksinuriVO.setCo_addr2_3(co_addr2_3);
    }
    if (co_stm != null) {
      co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
      naksinuriVO.setCo_stm(co_stm);
    }
    if (co_etm != null) {
      co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
      naksinuriVO.setCo_etm(co_etm);
    }
    if (bo_size != null) {
      bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
      naksinuriVO.setBo_size(bo_size);
    }
    if (bo_spd != null) {
      bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
      bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
    }
    if (bo_psg != null) {
      naksinuriVO.setBo_spd(bo_spd);
      naksinuriVO.setBo_psg(bo_psg);
    }

    this.service.updateInfo(naksinuriVO);
    model.addAttribute("resultMsg", "success.common.update");
    //return "forward:/info/confirm.do";
    return "redirect:/info/m/fishjob/result.do";
  }
  
  /** kkh2400 **/
  /** kkh2400 search ajax **/
  @RequestMapping({"/info/fishjob/fish_search.do"})
  public String fish_search(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriVO resultVO = this.service.modifyCorp(naksinuriVO);

    boolean co_nmOk = true;
	JSONObject obj = new JSONObject();
    if ((resultVO != null) && (resultVO.getCo_nm() != null) && (!resultVO.getCo_nm().equals("")) && (co_nmOk))
    {
    	request.getSession().setAttribute("NaksinuriVO", resultVO);
		obj.put("is_data", resultVO.getNak_id());
    } else {
		obj.put("is_data", "N");
    }
	response.setContentType("text/html; charset=utf-8");
	response.getWriter().print(obj);
	return null;
  }  
  @RequestMapping({"/info/fishjob/fish_modify_check.do"})
  public String fish_modify_check(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriVO resultVO = this.service.modify_fish_check(naksinuriVO);

    boolean co_nmOk = true;
	JSONObject obj = new JSONObject();
    if ((resultVO != null) && (resultVO.getNak_id() != null) && (!resultVO.getNak_id().equals("")))
    {
    	request.getSession().setAttribute("NaksinuriVO", resultVO);
		obj.put("is_modify", resultVO.getNak_id());
    } else {
		obj.put("is_modify", "N");
    }
	response.setContentType("text/html; charset=utf-8");
	response.getWriter().print(obj);
	return null;
  }  
  
  /** kkh2400 search ajax **/
  @RequestMapping({"/info/industry/ind_search.do"})
  public String ind_search(@ModelAttribute("NaksinuriSanupVO") NaksinuriSanupVO naksinuriSanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {

    NaksinuriSanupVO resultVO = this.service.modifyCorpSanup(naksinuriSanupVO);

	boolean san_nameOk = true;
	JSONObject obj = new JSONObject();
    if ((resultVO != null) && (resultVO.getSan_buisnessman() != null) && (!resultVO.getSan_buisnessman().equals("")) && (san_nameOk))
    {
    	request.getSession().setAttribute("NaksinuriSanupVO", resultVO);
		obj.put("is_data", resultVO.getSan_sn());
    } else {
		obj.put("is_data", "N");
    }
	response.setContentType("text/html; charset=utf-8");
	response.getWriter().print(obj);
	return null;
  }
  @RequestMapping({"/info/industry/ind_modify_check.do"})
  public String modify_ind_check(@ModelAttribute("NaksinuriSanupVO") NaksinuriSanupVO naksinuriSanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {

    NaksinuriSanupVO resultVO = this.service.modify_ind_check(naksinuriSanupVO);

	boolean san_nameOk = true;
	JSONObject obj = new JSONObject();
    if ((resultVO != null) && (resultVO.getSan_sn() != null) && (!resultVO.getSan_sn().equals("")))
    {
    	request.getSession().setAttribute("NaksinuriSanupVO", resultVO);
		obj.put("is_modify", resultVO.getSan_sn());
    } else {
		obj.put("is_modify", "N");
    }
	response.setContentType("text/html; charset=utf-8");
	response.getWriter().print(obj);
	return null;
  }
  
  /** kkh2400 search view **/
  @RequestMapping({"/info/fishjob/fish_viewS.do"})
  public String fish_view(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, FishjobReviewVO reviewVO, NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    NaksinuriFileVO mimg = this.service.admin_mimg(naksinurifileVO);
    NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);
    naksinurifileVO.setAtch_file_id(resultVO.getAtch_file_id());
    List list = this.service.admin_searchFile1(naksinurifileVO);

    this.service.up_hit(naksinurifileVO);

    String[] sido = resultVO.getCo_addr2_2().split(" ");
    fishesVO.setFishing_type(resultVO.getFishing_type());
    List fishlist = this.fishesService.fish_list(fishesVO);
    List review_list = this.service_review.get_reviewlist(reviewVO);

    model.addAttribute("review_list", review_list);
    model.addAttribute("fishlist", fishlist);
    model.addAttribute("sido", sido);
    model.addAttribute("info", resultVO);
    model.addAttribute("filelist", list);
    model.addAttribute("mimg", mimg);

    return "naksinuri_original/naksinuri/info/fishjob_viewS";
  }
  @RequestMapping({"/info/fishjob/m/fish_viewS.do"})
  public String mfish_view(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, FishjobReviewVO reviewVO, NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    NaksinuriFileVO mimg = this.service.admin_mimg(naksinurifileVO);
    NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);
    naksinurifileVO.setAtch_file_id(resultVO.getAtch_file_id());
    List list = this.service.admin_searchFile1(naksinurifileVO);

    this.service.up_hit(naksinurifileVO);

    String[] sido = resultVO.getCo_addr2_2().split(" ");
    fishesVO.setFishing_type(resultVO.getFishing_type());
    List fishlist = this.fishesService.fish_list(fishesVO);
    List review_list = this.service_review.get_reviewlist(reviewVO);

    model.addAttribute("review_list", review_list);
    model.addAttribute("fishlist", fishlist);
    model.addAttribute("sido", sido);
    model.addAttribute("info", resultVO);
    model.addAttribute("filelist", list);
    model.addAttribute("mimg", mimg);

    return "naksinuri_original/naksinuri/info/m/fishjob_viewS";
  } 
  /*
  @RequestMapping({"/info/fishjob/fish_Preview.do"})
  public String fish_Preview(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, FishjobReviewVO reviewVO, NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
	NaksinuriFileVO mimg = this.service.admin_mimg(naksinurifileVO);
	NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);
	naksinurifileVO.setAtch_file_id(resultVO.getAtch_file_id());
	List list = this.service.admin_searchFile1(naksinurifileVO);
	
	LOGGER.debug("=======================================================");
	LOGGER.debug("=======================================================");
    LOGGER.debug(resultVO.getCo_nm());
	LOGGER.debug("=======================================================");
	LOGGER.debug(resultVO.getCeo_nm());
	LOGGER.debug("=======================================================");
	LOGGER.debug("=======================================================");
	//this.service.up_hit(naksinurifileVO);
	
	NaksinuriVO presultVO = this.service.modifyCorpPreview_admin(naksinuriVO);

	LOGGER.debug("#######################################################");
	LOGGER.debug("#######################################################");
    LOGGER.debug(request.getParameter("co_nm"));
	LOGGER.debug("#######################################################");
	//LOGGER.debug(presultVO.getCeo_nm());
	LOGGER.debug("#######################################################");
	LOGGER.debug("#######################################################");
	
	
	String[] sido = resultVO.getCo_addr2_2().split(" ");
	fishesVO.setFishing_type(resultVO.getFishing_type());
	List fishlist = this.fishesService.fish_list(fishesVO);
	List review_list = this.service_review.get_reviewlist(reviewVO);
	
	model.addAttribute("review_list", review_list);
	model.addAttribute("fishlist", fishlist);
	model.addAttribute("sido", sido);
	model.addAttribute("info", resultVO);
	//model.addAttribute("preview", presultVO);
	model.addAttribute("filelist", list);
	model.addAttribute("mimg", mimg);

    return "naksinuri_original/naksinuri/info/fishjob_viewPV";
  }   
  */
  /*
  @RequestMapping({"/info/fishjob/m/fish_Preview.do"})
  public String mfish_Preview(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, FishjobReviewVO reviewVO, NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    NaksinuriFileVO mimg = this.service.admin_mimg(naksinurifileVO);
    NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);
    naksinurifileVO.setAtch_file_id(resultVO.getAtch_file_id());
    List list = this.service.admin_searchFile1(naksinurifileVO);

    //this.service.up_hit(naksinurifileVO);

    String[] sido = resultVO.getCo_addr2_2().split(" ");
    fishesVO.setFishing_type(resultVO.getFishing_type());
    List fishlist = this.fishesService.fish_list(fishesVO);
    List review_list = this.service_review.get_reviewlist(reviewVO);

    model.addAttribute("review_list", review_list);
    model.addAttribute("fishlist", fishlist);
    model.addAttribute("sido", sido);
    model.addAttribute("info", resultVO);
    model.addAttribute("filelist", list);
    model.addAttribute("mimg", mimg);

    return "naksinuri_original/naksinuri/info/m/fishjob_viewPV";
  }
  */
  @RequestMapping({"/info/industry/ind_viewS.do"})
  public String ind_viewS(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {

    LOGGER.debug("=========================================");
    LOGGER.debug("=========================================");
    LOGGER.debug(request.getParameter("co_nm"));  
    LOGGER.debug("=========================================");
    LOGGER.debug("=========================================");	  
	  
	NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
    List list = this.service_sanup.ind_fsimg(SanupVO);

    //this.service_sanup.up_hit(SanupVO);

    model.addAttribute("info", info);
    model.addAttribute("simglist", list);

    return "naksinuri_original/naksinuri/info/industry_viewS";
  }  
  @RequestMapping({"/info/m/industry/ind_viewS.do"})
  public String mind_viewS(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {

    LOGGER.debug("=========================================");
    LOGGER.debug("=========================================");
    LOGGER.debug(request.getParameter("co_nm"));  
    LOGGER.debug("=========================================");
    LOGGER.debug("=========================================");	  
	  
	NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
    List list = this.service_sanup.ind_fsimg(SanupVO);

    //this.service_sanup.up_hit(SanupVO);

    model.addAttribute("info", info);
    model.addAttribute("simglist", list);

    return "naksinuri_original/naksinuri/info/m/industry_viewS";
  } 
  /*
  @RequestMapping({"/info/industry/ind_Preview.do"})
  public String ind_Preview(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
    List list = this.service_sanup.ind_fsimg_Preview(SanupVO);

    //this.service_sanup.up_hit(SanupVO);

    model.addAttribute("info", info);
    model.addAttribute("simglist", list);

    return "naksinuri_original/naksinuri/info/industry_viewPV";
  }    
  @RequestMapping({"/info/m/industry/ind_Preview.do"})
  public String mind_Preview(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriSanupVO info = this.service_sanup.ind_find_Preview(SanupVO);
    List list = this.service_sanup.ind_fsimg_Preview(SanupVO);

    //this.service_sanup.up_hit(SanupVO);

    model.addAttribute("info", info);
    model.addAttribute("simglist", list);

    return "naksinuri_original/naksinuri/info/m/industry_viewPV";
  }  
  */
  
  
  @RequestMapping({"/info/findCorp.do"})
  public String findCorp(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriVO resultVO = this.service.modifyCorp(naksinuriVO);

    boolean co_nmOk = true;

    if ((resultVO != null) && (resultVO.getCo_nm() != null) && (!resultVO.getCo_nm().equals("")) && (co_nmOk))
    {
      request.getSession().setAttribute("NaksinuriVO", resultVO);
      return "forward:/info/fishingtype.do";
    }

    model.addAttribute("message", "로그인 정보가 올바르지 않습니다.");
    return "naksinuri_original/naksinuri/fishjob/login";
  }
  @RequestMapping({"/info/m/findCorp.do"})
  public String mfindCorp(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriVO resultVO = this.service.modifyCorp(naksinuriVO);

    boolean co_nmOk = true;

    if ((resultVO != null) && (resultVO.getCo_nm() != null) && (!resultVO.getCo_nm().equals("")) && (co_nmOk))
    {
      request.getSession().setAttribute("NaksinuriVO", resultVO);
      return "forward:/info/m/fishingtype.do";
    }

    model.addAttribute("message", "로그인 정보가 올바르지 않습니다.");
    return "naksinuri_original/naksinuri/m/fishjob/login";
  }
  
  @RequestMapping({"/info/findSanupCorp.do"})
  public String findSanupCorp(@ModelAttribute("naksinuriSanupVO") NaksinuriSanupVO naksinuriSanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	NaksinuriSanupVO resultVO = this.service.modifyCorpSanup(naksinuriSanupVO);

    boolean co_nmOk = true;

    if ((resultVO != null) && (resultVO.getSan_buisnessman() != null) && (!resultVO.getSan_buisnessman().equals("")) && (co_nmOk))
    {
      request.getSession().setAttribute("naksinuriSanupVO", resultVO);
      return "forward:/info/industry_edit.do";
    }
    model.addAttribute("message", "로그인 정보가 올바르지 않습니다.");
    return "naksinuri_original/naksinuri/info/industry_login";
  }
  @RequestMapping({"/info/m/findSanupCorp.do"})
  public String mfindSanupCorp(@ModelAttribute("naksinuriSanupVO") NaksinuriSanupVO naksinuriSanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	NaksinuriSanupVO resultVO = this.service.modifyCorpSanup(naksinuriSanupVO);

    boolean co_nmOk = true;

    if ((resultVO != null) && (resultVO.getSan_buisnessman() != null) && (!resultVO.getSan_buisnessman().equals("")) && (co_nmOk))
    {
      request.getSession().setAttribute("naksinuriSanupVO", resultVO);
      return "forward:/info/m/industry_edit.do";
    }
    model.addAttribute("message", "로그인 정보가 올바르지 않습니다.");
    return "naksinuri_original/naksinuri/info/m/industry_login";
  }

  @RequestMapping({"/info/industry_edit.do"})
  public String industry_edit(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	throws Exception
  {
	NaksinuriSanupVO resultVO = this.service.modifyCorpSanup(SanupVO);
	List list = this.service_sanup.ind_fsimg(resultVO);
	
    model.addAttribute("info", resultVO);
    model.addAttribute("filelist", list);

    return "forward:/info/industry_modify.do";
  }
  @RequestMapping({"/info/m/industry_edit.do"})
  public String mindustry_edit(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	NaksinuriSanupVO resultVO = this.service.modifyCorpSanup(SanupVO);
	List list = this.service_sanup.ind_fsimg(resultVO);
	
    model.addAttribute("info", resultVO);
    model.addAttribute("filelist", list);

    return "forward:/info/m/industry_modify.do";
  }
  
  @RequestMapping({"/info/industry_modify.do"}) 
  public String industry_modify(@ModelAttribute("SanupVO") NaksinuriSanupVO naksinuriSanupVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/info/industry_modify";
  }
  @RequestMapping({"/info/m/industry_modify.do"}) 
  public String mindustry_modify(@ModelAttribute("SanupVO") NaksinuriSanupVO naksinuriSanupVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/info/m/industry_modify";
  }  
  
  
  
  
  
  
  @RequestMapping({"/info/fishingtype.do"})
  public String fishingtype(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, @ModelAttribute("naksinurifileVO") NaksinuriFileVO naksinurifileVO, FishesVO fishesVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriVO resultVO = this.service.modifyCorp(naksinuriVO);
    NaksinuriFileVO mimg = this.service.mimg(naksinurifileVO);
    List list = this.service.searchFile(naksinurifileVO);
    List fishlist = this.fishesService.fish_list(fishesVO);

    model.addAttribute("info", resultVO);
    model.addAttribute("mimg", mimg);
    model.addAttribute("filelist", list);
    model.addAttribute("fishlist", fishlist);

    LOGGER.debug("=========================================");
    LOGGER.debug("=========================================");
    LOGGER.debug(resultVO.getFishing_type());
    LOGGER.debug("=========================================");
    LOGGER.debug("=========================================");

    if (resultVO.getFishing_type().equals("boatfishing"))
      return "forward:/info/boatfishing.do";
    if (resultVO.getFishing_type().equals("riverfishing")) {
      return "forward:/info/riverfishing.do";
    }
    return "forward:/info/seafishing.do";
  }
  @RequestMapping({"/info/m/fishingtype.do"})
  public String mfishingtype(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, @ModelAttribute("naksinurifileVO") NaksinuriFileVO naksinurifileVO, FishesVO fishesVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriVO resultVO = this.service.modifyCorp(naksinuriVO);
    NaksinuriFileVO mimg = this.service.mimg(naksinurifileVO);
    List list = this.service.searchFile(naksinurifileVO);
    List fishlist = this.fishesService.fish_list(fishesVO);

    model.addAttribute("info", resultVO);
    model.addAttribute("mimg", mimg);
    model.addAttribute("filelist", list);
    model.addAttribute("fishlist", fishlist);
    
    StringBuffer RandKey = new StringBuffer();
    RandKey.append("9999");
    int[] array = {1,2,3,4,5,6,7,8,9,0}; 
    Random ran = new Random();
    ran.setSeed(System.currentTimeMillis());
    for(int i=0; i<5; i++) {
  	  RandKey.append(array[ran.nextInt(10)]);
    }
    //삽입모드를 위한 랜덤키 생성
    model.addAttribute("RandKey", RandKey.toString());    
    
    
    if (resultVO.getFishing_type().equals("boatfishing"))
      return "forward:/info/m/boatfishing.do";
    if (resultVO.getFishing_type().equals("riverfishing")) {
      return "forward:/info/m/riverfishing.do";
    }
    return "forward:/info/m/seafishing.do";
  }

  
  
  @RequestMapping({"/info/admin/admin.do"})
  public String admin_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model) throws Exception
  {
    //return "naksinuri_original/naksinuri/admin/admin";
	return "adm/error/back_400";
  }

  @RequestMapping({"/admin/admin/actionSecurityLogin.do"})
  public String actionSecurityLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	  /*
    LoginVO resultVO = this.loginService.actionLogin(loginVO);

    boolean loginPolicyYn = true;

    if ((resultVO != null) && (resultVO.getMBR_ID() != null) && (!resultVO.getMBR_ID().equals("")) && (loginPolicyYn)) {
      request.getSession().setAttribute("LoginVO", resultVO);
      UsernamePasswordAuthenticationFilter springSecurity = null;

      ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());

      Map beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
      if (beans.size() > 0)
        springSecurity = (UsernamePasswordAuthenticationFilter)beans.values().toArray()[0];
      else {
        throw new IllegalStateException("No AuthenticationProcessingFilter");
      }
      springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);

      //springSecurity.doFilter(new RequestWrapperForSecurity(request, new StringBuilder().append(resultVO.getUserSe()).append(resultVO.getId()).toString(), resultVO.getUniqId()), response, null);
      return "naksinuri_original/admin_index";
     
    }

    model.addAttribute("message", "로그인 정보가 올바르지 않습니다.");
    return "naksinuri_original/naksinuri/admin/admin";
    */
	  return "adm/error/back_400";
  }
  
  /*class RequestWrapperForSecurity extends HttpServletRequestWrapper {
		private String username = null;
		private String password = null;

		public RequestWrapperForSecurity(HttpServletRequest request, String username, String password) {
			super(request);

			this.username = username;
			this.password = password;
		}

		@Override
		public String getRequestURI() {
			return ((HttpServletRequest)super.getRequest()).getContextPath() + "/j_spring_security_check";
		}

		@Override
		public String getParameter(String name) {
	        if (name.equals("j_username")) {
	        	return username;
	        }

	        if (name.equals("j_password")) {
	        	return password;
	        }

	        return super.getParameter(name);
	    }
	}*/

  @RequestMapping({"/admin/admin/actionMain.do"})
  public String actionMain(ModelMap model)
    throws Exception
  {
    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    if (!isAuthenticated.booleanValue()) {
      model.addAttribute("message", "로그인 정보가 올바르지 않습니다.");
      return "naksinuri_original/naksinuri/admin/admin";
    }

    return "forward:/admin/sosig/aevent/list.do";
  }

  @RequestMapping({"/admin/admin/actionLogout.do"})
  public String actionLogout(HttpServletRequest request, ModelMap model)
    throws Exception
  {
	  /*
    request.getSession().setAttribute("LoginVO", null);

    return "redirect:/info/admin/admin.do";
    */
	  return "adm/error/back_400";
  }

  @RequestMapping({"/info/admin/dashboard.do"})
  public String dashboard_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    List list = this.service.getListNaksinuri_admin(naksinuriVO);

    model.addAttribute("admin_list", list);

    return "naksinuri_original/naksinuri/admin/dashboard";
  }

  @RequestMapping({"/info/admin/boatfinfo.do"})
  public String boatfinfo_admin(FishesVO fishesVO, ModelMap model) throws Exception {
    fishesVO.setFishing_type("boatfishing");
    List fishlist = this.fishesService.fish_list(fishesVO);

    model.addAttribute("fishlist", fishlist);

    return "naksinuri_original/naksinuri/admin/document_boatfinfo";
  }

  @RequestMapping({"/info/admin/boatfishing.do"})
  public String boatfishing_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
//	  if (co_nm != null) {
//	      co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
//	      naksinuriVO.setCo_nm(co_nm);
//	    }
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("boatfishing");
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    String co_nm = request.getParameter("co_nm");
    //String co_ship_num1 = request.getParameter("co_ship_num1");
    //String co_ship_num2 = request.getParameter("co_ship_num2");
    String ceo_nm = request.getParameter("ceo_nm");
    String co_web = request.getParameter("co_web");
    String co_addr3 = request.getParameter("co_addr3");
    String co_addr2_3 = request.getParameter("co_addr2_3");
    String co_stm = request.getParameter("co_stm");
    String co_etm = request.getParameter("co_etm");
    String bo_size = request.getParameter("bo_size");
    String bo_spd = request.getParameter("bo_spd");
    String bo_psg = request.getParameter("bo_psg");

    if (co_nm != null) {
      co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
      naksinuriVO.setCo_nm(co_nm);
    }

    if (ceo_nm != null) {
      ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
      naksinuriVO.setCeo_nm(ceo_nm);
    }
    if (co_web != null) {
      co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
      naksinuriVO.setCo_web(co_web);
    }
    if (co_addr3 != null) {
      co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
      naksinuriVO.setCo_addr_3(co_addr3);
    }
    if (co_addr2_3 != null) {
      co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
      naksinuriVO.setCo_addr2_3(co_addr2_3);
    }
    if (co_stm != null) {
      co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
      naksinuriVO.setCo_stm(co_stm);
    }
    if (co_etm != null) {
      co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
      naksinuriVO.setCo_etm(co_etm);
    }
    if (bo_size != null) {
      bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
      naksinuriVO.setBo_size(bo_size);
    }
    if (bo_spd != null) {
      bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
      bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
    }
    if (bo_psg != null) {
      naksinuriVO.setBo_spd(bo_spd);
      naksinuriVO.setBo_psg(bo_psg);
    }

    List list = this.service.getListNaksinuri_admin(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }
    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));
    return "naksinuri_original/naksinuri/admin/document_boatfishing";
  }
  
  
  @RequestMapping({"/admin/info/boatfishing/trash.do"})
  public String trsboatfishing_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("boatfishing");

    List list = this.service.trashBoatfishing_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));
    return "naksinuri_original/naksinuri/admin/boatfishing_trash";
  }

  @RequestMapping({"/info/admin/boatlatest.do"})
  public String boatlatest_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("boatfishing");

    List list = this.service.getListNaksinuri_latest(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));
    return "naksinuri_original/naksinuri/admin/document_boatlatest";
  }

  @RequestMapping({"/admin/info/boatlatest/trash.do"})
  public String trsboatlatest_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("boatfishing");

    List list = this.service.trashBoatlatest_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }
    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));
    return "naksinuri_original/naksinuri/admin/boatlatest_trash";
  }

  @RequestMapping({"/info/admin/boatKor.do"})
  public String boatKor_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("boatfishing");

    List list = this.service.getListNaksinuri_Kor(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }
    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/document_boatKor";
  }

  @RequestMapping({"/admin/info/boatKor/trash.do"})
  public String trsboatKor_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("boatfishing");

    List list = this.service.trashBoatKor_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }
    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/boatKor_trash";
  }

  @RequestMapping({"/info/admin/riverfinfo.do"})
  public String riverfinfo_admin(FishesVO fishesVO, ModelMap model) throws Exception
  {
    fishesVO.setFishing_type("riverfishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    return "naksinuri_original/naksinuri/admin/document_riverfinfo";
  }

  @RequestMapping({"/info/admin/riverfishing.do"})
  public String riverfishing_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("riverfishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.getListNaksinuri_admin(naksinuriVO);
    
    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }
    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/document_riverfishing";
  }

  @RequestMapping({"/info/admin/riverlatest.do"})
  public String riverlatest_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("riverfishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.getListNaksinuri_latest(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/document_riverlatest";
  }

  @RequestMapping({"/info/admin/riverKor.do"})
  public String riverKor_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("riverfishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.getListNaksinuri_Kor(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/document_riverKor";
  }

  @RequestMapping({"/admin/info/riverfishing/trash.do"})
  public String trsriverfishing_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("riverfishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.trashBoatfishing_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/riverfishing_trash";
  }

  @RequestMapping({"/admin/info/riverlatest/trash.do"})
  public String trsriverlatest_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("riverfishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.trashBoatlatest_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/riverlatest_trash";
  }

  @RequestMapping({"/admin/info/riverKor/trash.do"})
  public String trsriverKor_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("riverfishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.trashBoatKor_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/riverKor_trash";
  }

  @RequestMapping({"/info/admin/seafinfo.do"})
  public String seafinfo_admin(FishesVO fishesVO, ModelMap model)
    throws Exception
  {
    fishesVO.setFishing_type("seafishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    return "naksinuri_original/naksinuri/admin/document_seafinfo";
  }

  @RequestMapping({"/info/admin/seafishing.do"})
  public String seafinshing_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("seafishing");

    List list = this.service.getListNaksinuri_admin(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/document_seafishing";
  }

  @RequestMapping({"/info/admin/seaKor.do"})
  public String seaKor_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("seafishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.getListNaksinuri_Kor(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/document_seaKor";
  }

  @RequestMapping({"/info/admin/sealatest.do"})
  public String sealatest_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("seafishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.getListNaksinuri_latest(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
    	
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/document_sealatest";
  }

  @RequestMapping({"/admin/info/seafishing/trash.do"})
  public String trsseafishing_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("seafishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.trashBoatfishing_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/seafishing_trash";
  }

  @RequestMapping({"/admin/info/sealatest/trash.do"})
  public String trssealatest_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("seafishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.trashBoatlatest_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/sealatest_trash";
  }

  @RequestMapping({"/admin/info/seaKor/trash.do"})
  public String trsseaKor_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());
    naksinuriVO.setFishing_type("seafishing");
    List clist = new ArrayList();
    String[] search_info2 = null;

    if (naksinuriVO.getSearch_info() != null) {
      search_info2 = naksinuriVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriVO.setSearch_info2(clist);

    List list = this.service.trashBoatKor_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));

    return "naksinuri_original/naksinuri/admin/seaKor_trash";
  }

  @RequestMapping({"/info/admin/findCorp.do"})
  public String findCorp_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriFileVO mimg = this.service.admin_mimg2(naksinurifileVO);
    NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);

    naksinurifileVO.setAtch_file_id(mimg.getAtch_file_id());
    List list = this.service.admin_searchFile1(naksinurifileVO);
    
    NaksinuriVO preview = naksinuriVO;
    preview.setNak_id(naksinuriVO.getNak_id());
    List<NaksinuriVO> previewlist = this.service.previewlist(preview);

    model.addAttribute("info", resultVO);
    model.addAttribute("mimg", mimg);
    model.addAttribute("filelist", list);
    model.addAttribute("previewlist", previewlist);
    
    
    if (resultVO.getFishing_type().equals("boatfishing"))
      return "forward:/info/admin/boatfinfo.do";
    if (resultVO.getFishing_type().equals("riverfishing")) {
      return "forward:/info/admin/riverfinfo.do";
    }
    return "forward:/info/admin/seafinfo.do";
  }

  @RequestMapping({"/info/admin/updateInfo.do"})
  public String updateInfo_admin(MultipartHttpServletRequest multiRequest, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, BindingResult bindingResult, ModelMap model)
    throws Exception
  {

    String atchFileId = naksinuriVO.getAtch_file_id();
    String mainatchFileId = naksinuriVO.getCo_mimgsrc();
    String co_mimgsrc = "mimg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    mfile.clear();
    if (files.get(co_mimgsrc) != null) {
      mfile.put(co_mimgsrc, files.get(co_mimgsrc));
      files.remove(co_mimgsrc);
    }
    if ((!files.isEmpty()) || (!mfile.isEmpty())) {
      if (("".equals(atchFileId)) || (atchFileId == null)) {
        List result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, atchFileId, "");
        atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result);
        naksinuriVO.setAtch_file_id(atchFileId);
      } else if ((!"".equals(atchFileId)) || (atchFileId != null)) {
        NaksinuriOriginalFileVO fvo = new NaksinuriOriginalFileVO();
        fvo.setAtchFileId(atchFileId);
        int cnt = this.fileMngService.getMaxFileSN_naksinuri_original(fvo);
        List _result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", cnt, atchFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }

      if (("".equals(mainatchFileId)) || (mainatchFileId == null)) {
        List result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, mainatchFileId, "");
        mainatchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result2);
        naksinuriVO.setCo_mimgsrc(mainatchFileId);
      }
      else if ((!"".equals(mainatchFileId)) || (mainatchFileId != null)) {
        NaksinuriOriginalFileVO fvo2 = new NaksinuriOriginalFileVO();
        fvo2.setAtchFileId(mainatchFileId);
        int cnt2 = this.fileMngService.getMaxFileSN_naksinuri_original(fvo2);
        List _result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", cnt2, mainatchFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result2);
      }

    }
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    
    String co_nm = request.getParameter("co_nm");//상호명
    String ceo_nm = request.getParameter("ceo_nm");
    String co_web = request.getParameter("co_web");
    String co_addr1 = request.getParameter("co_addr1");
    String co_addr2 = request.getParameter("co_addr2");
    String co_addr3 = request.getParameter("co_addr_3");
    String co_addr2_1 = request.getParameter("co_addr2_1");
    String co_addr2_2 = request.getParameter("co_addr2_2");
    String co_addr2_3 = request.getParameter("co_addr2_3");
    String co_stm = request.getParameter("co_stm");
    String co_etm = request.getParameter("co_etm");
    String bo_size = request.getParameter("bo_size");
    String bo_spd = request.getParameter("bo_spd");
    String bo_psg = request.getParameter("bo_psg");
    String co_psg = request.getParameter("co_psg");
    String co_prctp = request.getParameter("co_prctp");
    String co_prc = request.getParameter("co_prc");
    String co_intro = request.getParameter("co_intro");
    String adres_la = request.getParameter("adres_la");
    String adres_lo = request.getParameter("adres_lo");

	if (co_nm != null) {
		co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
		naksinuriVO.setCo_nm(co_nm);
	}
	
    if (ceo_nm != null) {
    	ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
    	naksinuriVO.setCeo_nm(ceo_nm);
    }
    if (co_web != null) {
    	co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
    	naksinuriVO.setCo_web(co_web);
    }
    if (co_addr1 != null) {
    	co_addr1 = mEgovStringUtil.getHtmlStrCnvr(co_addr1);
    	naksinuriVO.setCo_addr_1(co_addr1);
    }
    if (co_addr2 != null) {
    	co_addr2 = mEgovStringUtil.getHtmlStrCnvr(co_addr2);
    	naksinuriVO.setCo_addr_2(co_addr2);
    }
    if (co_addr3 != null) {
    	co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
    	naksinuriVO.setCo_addr_3(co_addr3);
    }
    if (co_addr2_1 != null) {
    	co_addr2_1 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_1);
    	naksinuriVO.setCo_addr2_1(co_addr2_1);
    }
    if (co_addr2_2 != null) {
    	co_addr2_2 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_2);
    	naksinuriVO.setCo_addr2_2(co_addr2_2);
    }
    if (co_addr2_3 != null) {
    	co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
    	naksinuriVO.setCo_addr2_3(co_addr2_3);
    }
    if (co_stm != null) {
    	co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
    	naksinuriVO.setCo_stm(co_stm);
    }
    if (co_etm != null) {
    	co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
    	naksinuriVO.setCo_etm(co_etm);
    }
    if (bo_size != null) {
    	bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
    	naksinuriVO.setBo_size(bo_size);
    }
    if (co_psg != null) {
    	co_psg = mEgovStringUtil.getHtmlStrCnvr(co_psg);
    	naksinuriVO.setCo_psg(co_psg);
    }
    if (bo_spd != null) {
    	bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
    	naksinuriVO.setBo_spd(bo_spd);
    }
    if (bo_psg != null) {
    	bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
    	naksinuriVO.setBo_psg(bo_psg);
    }
    if (co_prctp != null) {
    	co_prctp = mEgovStringUtil.getHtmlStrCnvr(co_prctp);
    	naksinuriVO.setCo_prctp(co_prctp);
    }
    if (co_prc != null) {
    	co_prc = mEgovStringUtil.getHtmlStrCnvr(co_prc);
    	naksinuriVO.setCo_prc(co_prc);
    }
    if (co_intro != null) {
    	co_intro = mEgovStringUtil.getHtmlStrCnvr(co_intro);
    	naksinuriVO.setCo_intro(co_intro);
    }
    if (adres_la != null) {
    	adres_la = mEgovStringUtil.getHtmlStrCnvr(adres_la);
    	naksinuriVO.setAdres_la(adres_la);
    }
    if (adres_lo != null) {
    	adres_lo = mEgovStringUtil.getHtmlStrCnvr(adres_lo);
    	naksinuriVO.setAdres_lo(adres_lo);
    }
    
    
	if(naksinuriVO.getCo_nm() != null && naksinuriVO.getCo_nm().length() != 0){
		naksinuriVO.setCo_nm(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_nm()));
	}
	if(naksinuriVO.getCeo_nm() != null && naksinuriVO.getCeo_nm().length() != 0){
		naksinuriVO.setCeo_nm(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCeo_nm()));
	}
	if(naksinuriVO.getCo_web() != null && naksinuriVO.getCo_web().length() != 0){
		naksinuriVO.setCo_web(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_web()));
	}
	if(naksinuriVO.getCo_addr2_1() != null && naksinuriVO.getCo_addr2_1().length() != 0){
		naksinuriVO.setCo_addr2_1(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr2_1()));
	}
	if(naksinuriVO.getCo_addr2_2() != null && naksinuriVO.getCo_addr2_2().length() != 0){
		naksinuriVO.setCo_addr2_2(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr2_2()));
	}
	if(naksinuriVO.getCo_addr2_3() != null && naksinuriVO.getCo_addr2_3().length() != 0){
		naksinuriVO.setCo_addr2_3(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr2_3()));
	}
	if(naksinuriVO.getCo_addr_1() != null && naksinuriVO.getCo_addr_1().length() != 0){
		naksinuriVO.setCo_addr_1(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr_1()));
	}
	if(naksinuriVO.getCo_addr_2() != null && naksinuriVO.getCo_addr_2().length() != 0){
		naksinuriVO.setCo_addr_2(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr_2()));
	}
	if(naksinuriVO.getCo_addr_3() != null && naksinuriVO.getCo_addr_3().length() != 0){
		naksinuriVO.setCo_addr_3(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr_3()));
	}
	if(naksinuriVO.getCo_stm() != null && naksinuriVO.getCo_stm().length() != 0){
		naksinuriVO.setCo_stm(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_stm()));
	}
	if(naksinuriVO.getCo_etm() != null && naksinuriVO.getCo_etm().length() != 0){
		naksinuriVO.setCo_etm(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_etm()));
	}
	if(naksinuriVO.getBo_size() != null && naksinuriVO.getBo_size().length() != 0){
		naksinuriVO.setBo_size(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getBo_size()));
	}
	if(naksinuriVO.getBo_spd() != null && naksinuriVO.getBo_spd().length() != 0){
		naksinuriVO.setBo_spd(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getBo_spd()));
	}
	if(naksinuriVO.getBo_psg() != null && naksinuriVO.getBo_psg().length() != 0){
		naksinuriVO.setBo_psg(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getBo_psg()));
	}
	if(naksinuriVO.getCo_psg() != null && naksinuriVO.getCo_psg().length() != 0){
		naksinuriVO.setCo_psg(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_psg()));
	}    
    if(naksinuriVO.getCo_prctp() != null && naksinuriVO.getCo_prctp().length() != 0){//이용료(조건)
    	naksinuriVO.setCo_prctp(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_prctp()));
    }
    if(naksinuriVO.getCo_prc() != null && naksinuriVO.getCo_prc().length() != 0){//이용료(요금)
    	naksinuriVO.setCo_prc(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_prc()));
    }
    if(naksinuriVO.getCo_intro() != null && naksinuriVO.getCo_intro().length() != 0){
    	naksinuriVO.setCo_intro(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_intro()));
    }
    if(naksinuriVO.getAdres_la() != null && naksinuriVO.getAdres_la().length() != 0){
    	naksinuriVO.setAdres_la(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getAdres_la()));
    }
    if(naksinuriVO.getAdres_lo() != null && naksinuriVO.getAdres_lo().length() != 0){
    	naksinuriVO.setAdres_lo(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getAdres_lo()));
    }

    this.service.updateInfo(naksinuriVO);
    model.addAttribute("resultMsg", "success.common.update");
    
//    if(naksinuriVO.getCate_mod_st().equals("Y")){
//    	if (naksinuriVO.getFishing_type().equals("boatfishing")){
//    		return "redirect:/info/admin/boatfishing.do";
//    	}else if(naksinuriVO.getFishing_type().equals("riverfishing")){
//    		return "redirect:/info/admin/riverfishing.do";
//    	}else {
//    		return "redirect:/info/admin/seafishing.do";    	
//    	}
//    } else{
//    	return "forward:/info/admin/findCorp.do";    	
//    }
    if (naksinuriVO.getFishing_type().equals("boatfishing"))
        return "redirect:/info/admin/boatfishing.do";
      if (naksinuriVO.getFishing_type().equals("riverfishing"))
      {
        return "redirect:/info/admin/riverfishing.do";
      }

      return "redirect:/info/admin/seafishing.do";
  }

  @RequestMapping({"/info/admin/write_boat.do"})
  public String write_boat_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model)
    throws Exception
  {
    fishesVO.setFishing_type("boatfishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    return "naksinuri_original/naksinuri/admin/document_boatfinfo";
  }

  @RequestMapping({"/info/admin/write_river.do"})
  public String write_river_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model)
    throws Exception
  {
    fishesVO.setFishing_type("riverfishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    return "naksinuri_original/naksinuri/admin/document_riverfinfo";
  }

  @RequestMapping({"/info/admin/write_sea.do"})
  public String write_sea_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, ModelMap model)
    throws Exception
  {
    fishesVO.setFishing_type("seafishing");
    List fishlist = this.fishesService.fish_list(fishesVO);
    model.addAttribute("fishlist", fishlist);

    return "naksinuri_original/naksinuri/admin/document_seafinfo";
  }

  @RequestMapping({"/info/admin/insert.do"})
  public String insertCorp_admin(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    LOGGER.debug("insertCorp_admin() -> /info/admin/insert.do");

    List result = null;
    List result2 = null;
    String co_mimgsrc = "mimg";
    String atchFileId = "";
    String mainatchFileId = "";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();

    mfile.clear();
    if (files.get(co_mimgsrc) != null) {
      mfile.put(co_mimgsrc, files.get(co_mimgsrc));
      files.remove(co_mimgsrc);
    }

    if ((!files.isEmpty()) || (!mfile.isEmpty())) {
      result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      result2 = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result);
      mainatchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result2);
    }

    naksinuriVO.setAtch_file_id(atchFileId);
    naksinuriVO.setCo_mimgsrc(mainatchFileId);
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String co_nm = request.getParameter("co_nm");//상호명
    String ceo_nm = request.getParameter("ceo_nm");
    String co_web = request.getParameter("co_web");
    String co_addr1 = request.getParameter("co_addr1");
    String co_addr2 = request.getParameter("co_addr2");
    String co_addr3 = request.getParameter("co_addr_3");
    String co_addr2_1 = request.getParameter("co_addr2_1");
    String co_addr2_2 = request.getParameter("co_addr2_2");
    String co_addr2_3 = request.getParameter("co_addr2_3");
    String co_stm = request.getParameter("co_stm");
    String co_etm = request.getParameter("co_etm");
    String bo_size = request.getParameter("bo_size");
    String bo_spd = request.getParameter("bo_spd");
    String bo_psg = request.getParameter("bo_psg");
    String co_psg = request.getParameter("co_psg");
    String co_prctp = request.getParameter("co_prctp");
    String co_prc = request.getParameter("co_prc");
    String co_intro = request.getParameter("co_intro");
    String adres_lo = request.getParameter("adres_lo");
    String adres_la = request.getParameter("adres_la");

	if (co_nm != null) {
		co_nm = mEgovStringUtil.getHtmlStrCnvr(co_nm);
		naksinuriVO.setCo_nm(co_nm);
	}
	
    if (ceo_nm != null) {
    	ceo_nm = mEgovStringUtil.getHtmlStrCnvr(ceo_nm);
    	naksinuriVO.setCeo_nm(ceo_nm);
    }
    if (co_web != null) {
    	co_web = mEgovStringUtil.getHtmlStrCnvr(co_web);
    	naksinuriVO.setCo_web(co_web);
    }
    if (co_addr1 != null) {
    	co_addr1 = mEgovStringUtil.getHtmlStrCnvr(co_addr1);
    	naksinuriVO.setCo_addr_1(co_addr1);
    }
    if (co_addr2 != null) {
    	co_addr2 = mEgovStringUtil.getHtmlStrCnvr(co_addr2);
    	naksinuriVO.setCo_addr_2(co_addr2);
    }
    if (co_addr3 != null) {
    	co_addr3 = mEgovStringUtil.getHtmlStrCnvr(co_addr3);
    	naksinuriVO.setCo_addr_3(co_addr3);
    }
    if (co_addr2_1 != null) {
    	co_addr2_1 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_1);
    	naksinuriVO.setCo_addr2_1(co_addr2_1);
    }
    if (co_addr2_2 != null) {
    	co_addr2_2 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_2);
    	naksinuriVO.setCo_addr2_2(co_addr2_2);
    }
    if (co_addr2_3 != null) {
    	co_addr2_3 = mEgovStringUtil.getHtmlStrCnvr(co_addr2_3);
    	naksinuriVO.setCo_addr2_3(co_addr2_3);
    }
    if (co_stm != null) {
    	co_stm = mEgovStringUtil.getHtmlStrCnvr(co_stm);
    	naksinuriVO.setCo_stm(co_stm);
    }
    if (co_etm != null) {
    	co_etm = mEgovStringUtil.getHtmlStrCnvr(co_etm);
    	naksinuriVO.setCo_etm(co_etm);
    }
    if (bo_size != null) {
    	bo_size = mEgovStringUtil.getHtmlStrCnvr(bo_size);
    	naksinuriVO.setBo_size(bo_size);
    }
    if (co_psg != null) {
    	co_psg = mEgovStringUtil.getHtmlStrCnvr(co_psg);
    	naksinuriVO.setCo_psg(co_psg);
    }
    if (bo_spd != null) {
    	bo_spd = mEgovStringUtil.getHtmlStrCnvr(bo_spd);
    	naksinuriVO.setBo_spd(bo_spd);
    }
    if (bo_psg != null) {
    	bo_psg = mEgovStringUtil.getHtmlStrCnvr(bo_psg);
    	naksinuriVO.setBo_psg(bo_psg);
    }
    if (co_prctp != null) {
    	co_prctp = mEgovStringUtil.getHtmlStrCnvr(co_prctp);
    	naksinuriVO.setCo_prctp(co_prctp);
    }
    if (co_prc != null) {
    	co_prc = mEgovStringUtil.getHtmlStrCnvr(co_prc);
    	naksinuriVO.setCo_prc(co_prc);
    }
    if (co_intro != null) {
    	co_intro = mEgovStringUtil.getHtmlStrCnvr(co_intro);
    	naksinuriVO.setCo_intro(co_intro);
    }
    if (adres_lo != null) {
    	adres_lo = mEgovStringUtil.getHtmlStrCnvr(adres_lo);
    	naksinuriVO.setAdres_lo(adres_lo);
    }
    if (adres_la != null) {
    	adres_la = mEgovStringUtil.getHtmlStrCnvr(adres_la);
    	naksinuriVO.setAdres_la(adres_la);
    }

    
    
	if(naksinuriVO.getCo_nm() != null && naksinuriVO.getCo_nm().length() != 0){
		naksinuriVO.setCo_nm(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_nm()));
	}
	if(naksinuriVO.getCeo_nm() != null && naksinuriVO.getCeo_nm().length() != 0){
		naksinuriVO.setCeo_nm(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCeo_nm()));
	}
	if(naksinuriVO.getCo_web() != null && naksinuriVO.getCo_web().length() != 0){
		naksinuriVO.setCo_web(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_web()));
	}
	if(naksinuriVO.getCo_addr2_1() != null && naksinuriVO.getCo_addr2_1().length() != 0){
		naksinuriVO.setCo_addr2_1(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr2_1()));
	}
	if(naksinuriVO.getCo_addr2_2() != null && naksinuriVO.getCo_addr2_2().length() != 0){
		naksinuriVO.setCo_addr2_2(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr2_2()));
	}
	if(naksinuriVO.getCo_addr2_3() != null && naksinuriVO.getCo_addr2_3().length() != 0){
		naksinuriVO.setCo_addr2_3(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr2_3()));
	}
	if(naksinuriVO.getCo_addr_1() != null && naksinuriVO.getCo_addr_1().length() != 0){
		naksinuriVO.setCo_addr_1(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr_1()));
	}
	if(naksinuriVO.getCo_addr_2() != null && naksinuriVO.getCo_addr_2().length() != 0){
		naksinuriVO.setCo_addr_2(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr_2()));
	}
	if(naksinuriVO.getCo_addr_3() != null && naksinuriVO.getCo_addr_3().length() != 0){
		naksinuriVO.setCo_addr_3(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_addr_3()));
	}
	if(naksinuriVO.getCo_stm() != null && naksinuriVO.getCo_stm().length() != 0){
		naksinuriVO.setCo_stm(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_stm()));
	}
	if(naksinuriVO.getCo_etm() != null && naksinuriVO.getCo_etm().length() != 0){
		naksinuriVO.setCo_etm(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_etm()));
	}
	if(naksinuriVO.getBo_size() != null && naksinuriVO.getBo_size().length() != 0){
		naksinuriVO.setBo_size(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getBo_size()));
	}
	if(naksinuriVO.getBo_spd() != null && naksinuriVO.getBo_spd().length() != 0){
		naksinuriVO.setBo_spd(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getBo_spd()));
	}
	if(naksinuriVO.getBo_psg() != null && naksinuriVO.getBo_psg().length() != 0){
		naksinuriVO.setBo_psg(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getBo_psg()));
	}
	if(naksinuriVO.getCo_psg() != null && naksinuriVO.getCo_psg().length() != 0){
		naksinuriVO.setCo_psg(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_psg()));
	}    
    if(naksinuriVO.getCo_prctp() != null && naksinuriVO.getCo_prctp().length() != 0){//이용료(조건)
    	naksinuriVO.setCo_prctp(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_prctp()));
    }
    if(naksinuriVO.getCo_prc() != null && naksinuriVO.getCo_prc().length() != 0){//이용료(요금)
    	naksinuriVO.setCo_prc(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_prc()));
    }
    if(naksinuriVO.getCo_intro() != null && naksinuriVO.getCo_intro().length() != 0){
    	naksinuriVO.setCo_intro(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getCo_intro()));
    }
    if(naksinuriVO.getAdres_la() != null && naksinuriVO.getAdres_la().length() != 0){
    	naksinuriVO.setAdres_la(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getAdres_la()));
    }
    if(naksinuriVO.getAdres_lo() != null && naksinuriVO.getAdres_lo().length() != 0){
    	naksinuriVO.setAdres_lo(mEgovStringUtil.getHtmlStrCnvr(naksinuriVO.getAdres_lo()));
    }
    
    this.service.insertCorp(naksinuriVO);
    if (naksinuriVO.getFishing_type().equals("boatfishing"))
      return "redirect:/info/admin/boatfishing.do";
    if (naksinuriVO.getFishing_type().equals("riverfishing"))
    {
      return "redirect:/info/admin/riverfishing.do";
    }

    return "redirect:/info/admin/seafishing.do";
  }

  @RequestMapping({"/info/admin/deleteb.do"})
  public String deleteBoardArticleb(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.delete_list(naksinuriVO);
    }

    return "redirect:/admin/info/boatfishing/trash.do";
  }

  @RequestMapping({"/info/admin/deletes.do"})
  public String deleteBoardArticles(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.delete_list(naksinuriVO);
    }

    return "redirect:/admin/info/seafishing/trash.do";
  }

  @RequestMapping({"/info/admin/deleter.do"})
  public String deleteBoardArticler(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.delete_list(naksinuriVO);
    }

    return "redirect:/admin/info/riverfishing/trash.do";
  }

  @RequestMapping({"/info/admin/restoreb.do"})
  public String restoreBoardArticleb(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.restore_list(naksinuriVO);
    }

    return "redirect:/admin/info/boatfishing/trash.do";
  }

  @RequestMapping({"/info/admin/restores.do"})
  public String restoreBoardArticles(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.restore_list(naksinuriVO);
    }

    return "redirect:/admin/info/seafishing/trash.do";
  }

  @RequestMapping({"/info/admin/restorer.do"})
  public String restoreBoardArticler(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.restore_list(naksinuriVO);
    }

    return "redirect:/admin/info/riverfishing/trash.do";
  }

  @RequestMapping({"/info/admin/gotrash_fishinglist.do"})
  public String trsdeleteBoardArticleb(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.gotrashfishing_list(naksinuriVO);
    }

    return "redirect:/info/admin/boatfishing.do";
  }

  @RequestMapping({"/info/admin/gotrash_sealist.do"})
  public String deleteBoardArticlest(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.gotrashfishing_list(naksinuriVO);
    }

    return "redirect:/info/admin/seafishing.do";
  }

  @RequestMapping({"/info/admin/gotrash_riverlist.do"})
  public String deleteBoardArticlert(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      naksinuriVO.setNak_id(strboIds[i]);
      this.service.gotrashfishing_list(naksinuriVO);
    }

    return "redirect:/info/admin/riverfishing.do";
  }

  /*@RequestMapping({"/promotion/plocation/list.do"})
  public String plocation_list(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVo, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksinuriController - plocation_list : 낚시정책 - 낚시금지구역 - 낚시금지구역");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setBo_name("낚시금지구역");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("plocation");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setCategory_type("plocation");
    staticVO.setCategory_name("낚시금지구역");
    staticVO.setPath_type("web");

    this.service_statistic.get_statisticInfo(staticVO,request);

    GoneVo.setPageSize(3);
    String category = "1";
    GoneVo.setPageInfo(model);
    GoneVo.setX_category(category);

    List list = this.service_gone.getListGone(GoneVo);

    if (list.size() > 0)
      GoneVo.setTotalPage(((NaksinuriGoneVO)list.get(0)).getTot_cnt());
    else {
      GoneVo.setTotalPage(0);
    }
    model.addAttribute("sido", GoneVo.getSido());
    model.addAttribute("gugun", GoneVo.getGugun());

    if (list.size() > 0)
    {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)list.get(0)).getTot_cnt()));
    }
    return "naksinuri_original/naksinuri/promotion/plocation_list";
  }*/

  @RequestMapping({"/promotion/llocation/list.do"})
  public String llocation_list(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVo, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    LOGGER.debug("NaksinuriController - plocation_list : 낚시정책 - 낚시금지구역 - 낚시제한구역");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setBo_name("낚시제한구역");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("llocation");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setCategory_type("llocation");
    staticVO.setCategory_name("낚시제한구역");
    staticVO.setPath_type("web");

    this.service_statistic.get_statisticInfo(staticVO,request);

    GoneVo.setPageSize(3);
    String category = "2";
    GoneVo.setPageInfo(model);
    GoneVo.setPageUnit(GoneVo.getPageUnit());
    GoneVo.setX_category(category);

    List list = this.service_gone.getListGone(GoneVo);

    if (list.size() > 0)
      GoneVo.setTotalPage(((NaksinuriGoneVO)list.get(0)).getTot_cnt());
    else {
      GoneVo.setTotalPage(0);
    }
    model.addAttribute("sido", GoneVo.getSido());
    model.addAttribute("gugun", GoneVo.getGugun());

    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)list.get(0)).getTot_cnt()));
    }
    return "naksinuri_original/naksinuri/promotion/llocation_list";
  }

  @RequestMapping({"/promotion/auditor/list.do"})
  public String auditor_list(NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpSession session, ModelMap model)
  {
    LOGGER.debug("NaksinuriController - auditor_list : 낚시정책 - 낚시명예감시원 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");
    
    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
    if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
    	model.addAttribute("MBR_LV_ID", loginVO.getMBR_LV_ID());
	}
    
    staticVO.setBo_name("낚시명예감시원");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("auditor");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setPath_type("web");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    return "naksinuri_original/naksinuri/promotion/auditor_list";
  }

  @RequestMapping({"/promotion/auditor/m/list.do"})
  public String auditor_list_mobile(NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpSession session, ModelMap model)
  {
    LOGGER.debug("NaksinuriController - auditor_list_mobile : 낚시정책(모바일) - 낚시명예감시원 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
    if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
    	model.addAttribute("MBR_LV_ID", loginVO.getMBR_LV_ID());
	}
    
    staticVO.setBo_name("낚시명예감시원");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("auditor");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setPath_type("mobile");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    return "naksinuri_original/naksinuri/promotion/m/auditor_list";
  }
  
  //낚시명예감시원 활동보고서 리스트
  @RequestMapping({"/promotion/auditor/board_list.do"})
  public String auditor_board_list(@ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, 
		  HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	  if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
		  model.addAttribute("MBR_LV_ID", loginVO.getMBR_LV_ID());
	  } else {
		  LOGGER.debug("낚시명예감시원 활동보고서 리스트는 로그인이 필요한 서비스 입니다.");
		  return "redirect:/promotion/auditor/list.do";
	  }
	  
	  
	  /** pageing setting */
	  PaginationInfo paginationInfo = new PaginationInfo();
	  paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
	  paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
	  paginationInfo.setPageSize(boardVO.getPageSize());
	
	  boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	  boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
	  boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	
		
	  boardVO.setBo_type("auditor");
	  List<BoardVO> list = naksinuriService.select_list(boardVO);
	  int totCnt = list.size();
	  
	  paginationInfo.setTotalRecordCount(totCnt);
	  model.addAttribute("paginationInfo", paginationInfo);
	  model.addAttribute("list",list);
		
	  model.addAttribute("pageUnit",boardVO.getPageUnit());
	  
	 
	  /**start 통계반영 **/  
	  staticVO.setBo_name("낚시명예감시원 활동보고서");
	  staticVO.setPath(request.getRequestURL().toString());
	  if (getClientOS(request) != "") {
		  staticVO.setStatistic_os(getClientOS(request));
	  } else {
		  staticVO.setStatistic_os(System.getProperty("os.name"));
	  }
	  staticVO.setBrowser(getClientBrowser(request));
	  staticVO.setClient_ip(getClientIpAddr(request));
	  staticVO.setBo_type("auditor");

	  staticVO.setCategory_group_type("fishing_policy");
	  staticVO.setCategory_group_name("낚시정책");
	  staticVO.setPath_type("web");
	  staticVO.setCategory_name("");
	  staticVO.setCategory_type("");

	  this.service_statistic.get_statisticInfo(staticVO,request);
	  /**end 통계반영 **/

	  return "naksinuri_original/naksinuri/promotion/auditor_board_list";
  }
  
  //낚시명예감시원 활동보고서 리스트 모바일
  @RequestMapping({"/promotion/m/auditor/board_list.do"})
  public String mobile_auditor_board_list(@ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, 
		  HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	  if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
		  model.addAttribute("MBR_LV_ID", loginVO.getMBR_LV_ID());
	  } else {
		  LOGGER.debug("낚시명예감시원 활동보고서 리스트는 로그인이 필요한 서비스 입니다.");
		  return "redirect:/promotion/auditor/m/list.do";
	  }
	  
	  /** pageing setting */
	  PaginationInfo paginationInfo = new PaginationInfo();
	  paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
	  paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
	  paginationInfo.setPageSize(boardVO.getPageSize());
	
	  boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	  boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
	  boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	
		
	  boardVO.setBo_type("auditor");
	  List<BoardVO> list = naksinuriService.select_list(boardVO);
	  int totCnt = list.size();
	  
	  paginationInfo.setTotalRecordCount(totCnt);
	  model.addAttribute("paginationInfo", paginationInfo);
	  model.addAttribute("list",list);
		
	  model.addAttribute("pageUnit",boardVO.getPageUnit());
	  
	 
	  /**start 통계반영 **/  
	  staticVO.setBo_name("낚시명예감시원 활동보고서");
	  staticVO.setPath(request.getRequestURL().toString());
	  if (getClientOS(request) != "") {
		  staticVO.setStatistic_os(getClientOS(request));
	  } else {
		  staticVO.setStatistic_os(System.getProperty("os.name"));
	  }
	  staticVO.setBrowser(getClientBrowser(request));
	  staticVO.setClient_ip(getClientIpAddr(request));
	  staticVO.setBo_type("auditor");

	  staticVO.setCategory_group_type("fishing_policy");
	  staticVO.setCategory_group_name("낚시정책");
	  staticVO.setPath_type("mobile");
	  staticVO.setCategory_name("");
	  staticVO.setCategory_type("");

	  this.service_statistic.get_statisticInfo(staticVO,request);
	  /**end 통계반영 **/

	  return "naksinuri_original/naksinuri/promotion/m/auditor_board_list";
  }
  
  //낚시명예감시원 활동보고서 작성
  @RequestMapping(value = "/promotion/auditor/board_write.do")
  public String promotion_auditor_board_write(@ModelAttribute("boardVO") BoardVO boardVO, 
		  HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {	
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	  if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
		  
	  } else {
		  LOGGER.debug("낚시명예감시원 활동보고서 작성하기는 로그인이 필요한 서비스 입니다.");
		  return "redirect:/promotion/auditor/list.do";
	  }
	  
	  return "naksinuri_original/naksinuri/promotion/auditor_board_write";
  }
  
  //낚시명예감시원 활동보고서 작성 모바일
  @RequestMapping(value = "/promotion/m/auditor/board_write.do")
  public String mobile_promotion_auditor_board_write(@ModelAttribute("boardVO") BoardVO boardVO, 
		  HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {	
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	  if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
	  
	  } else {
		  LOGGER.debug("낚시명예감시원 활동보고서 작성하기는 로그인이 필요한 서비스 입니다.");
		  return "redirect:/promotion/auditor/m/list.do";
	  }
	  
	  return "naksinuri_original/naksinuri/promotion/m/auditor_board_write";
  }
  
  //낚시명예감시원 활동보고서 작성 로직
  @RequestMapping(value = "/promotion/auditor/board_write_act.do")
  public String promotion_auditor_board_write_act (RedirectAttributes redirectAttributes,
		  @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request,
		  MultipartHttpServletRequest multiRequest, ModelMap model, HttpSession session) throws Exception {	
		
	  String mimg = "mimg";
	  String simg = "simg";
	  String _mainFileId = "";
	  String _subFileId = "";
	  String _atchFileId = "";
	  List _result = null;
	  List _result2 = null;
	  List _result3 = null;
	  
	  Map files = multiRequest.getFileMap();
	  Map mfile = multiRequest.getFileMap();
	  Map sfile = multiRequest.getFileMap();

	  mfile.clear();
	  sfile.clear();

	  if (files.get(mimg) != null) {
		  mfile.put(mimg, files.get(mimg));
		  files.remove(mimg);
	  }
	
	  if (files.get(simg) != null) {
		  sfile.put(simg, files.get(simg));
		  files.remove(simg);
	  }
	
	  if (!mfile.isEmpty()) {
		  _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
		  for (int i = 0; i < _result.size(); i++) {
		    if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
		      LOGGER.debug("파일에러");
		      return "redirect:/error/ext/warn.do";
		    }
		  }
		  _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
	  }

	  if (!sfile.isEmpty()) {
		  _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
		  for (int i = 0; i < _result2.size(); i++) {
			  if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
				  LOGGER.debug("파일에러");
				  return "redirect:/error/ext/warn.do";
			  }
		  }
		  _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
	  }
	
	  if (!files.isEmpty()) {
		  _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
		  for (int i = 0; i < _result3.size(); i++) {
		    if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
		      LOGGER.debug("파일에러");
		      return "redirect:/error/ext/warn.do";
		    }
		  }
		  _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);
	  }
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	  boardVO.setBo_name(loginVO.getMBR_NM());
	  boardVO.setBo_phone(loginVO.getMBR_HP());
	  boardVO.setBo_email(loginVO.getMBR_EMAIL());
	  boardVO.setBo_member_id(loginVO.getMBR_ID());
	  
	  boardVO.setBo_type("auditor");
	  boardVO.setBo_cate("낚시명예감시원");
	  boardVO.setBo_trash("0");
	  boardVO.setBo_main_img(_mainFileId);
	  boardVO.setBo_sub_img(_subFileId);
	  boardVO.setBo_atch_file(_atchFileId);
	
	  this.service.congress_insert_data(boardVO);
	  
	  String returnUrl = "redirect:/promotion/auditor/board_list.do";
	  
	  return returnUrl;
	  
  }
  
  //낚시명예감시원 - 활동보고서 - 뷰
  @RequestMapping({"/promotion/auditor/board_view.do"})
  public String promotion_auditor_board_view(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, 
		  HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {
    
	  boardVO.setBo_type("auditor");
	  this.service.view_update(boardVO);

	  BoardVO info = this.service.congress_findCorp(boardVO);
	  model.addAttribute("info", info);
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
		if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
			model.addAttribute("MBR_ID", loginVO.getMBR_ID());
		}
	 /* if (info.getCo_comment() != null) {
		  String co_name = info.getCo_name();
		  String co_pass = info.getCo_pass();
		  String co_comment = info.getCo_comment();

	      if (co_name != null) {
	    	  co_name = mEgovStringUtil.getHtmlStrCnvr(co_name);
	    	  boardVO.setCo_name(co_name);
	      }
	
	      if (co_comment != null) {
	    	  co_comment = mEgovStringUtil.getHtmlStrCnvr(co_comment);
	    	  boardVO.setCo_comment(co_comment);
	      }
	      this.service.co_insert(boardVO);
	  }*/

	  List list = this.service.select_list_comment(boardVO);

	  if (list.size() > 0) {
		  model.addAttribute("comment_list", list);
	  }

	  List bobo_list = this.service.select_list_bobo(boardVO);

	  if (bobo_list.size() > 0) {
		  boardVO.setTotalPage(((BoardVO)bobo_list.get(0)).getTot_cnt());
		  model.addAttribute("bobo_list", bobo_list);
		  model.addAttribute("select_total", Integer.valueOf(((BoardVO)bobo_list.get(0)).getTot_cnt()));
	  }
	 

	  BoardVO next = this.service.select_next(boardVO);
	  BoardVO prev = this.service.select_prev(boardVO);
	  if (next != null) {
		  model.addAttribute("next", next);
	  }
	  if (prev != null) {
		  model.addAttribute("prev", prev);
	  }
	  
	  return "naksinuri_original/naksinuri/promotion/auditor_board_view";
  }
  
  //낚시명예감시원 - 활동보고서 - 뷰 - 모바일
  @RequestMapping({"/promotion/m/auditor/board_view.do"})
  public String mobile_promotion_auditor_board_view(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, 
		  HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {
    
	  boardVO.setBo_type("auditor");
	  this.service.view_update(boardVO);

	  BoardVO info = this.service.congress_findCorp(boardVO);
	  model.addAttribute("info", info);
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
		if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
			model.addAttribute("MBR_ID", loginVO.getMBR_ID());
		}
	 
	  BoardVO next = this.service.select_next(boardVO);
	  BoardVO prev = this.service.select_prev(boardVO);
	  if (next != null) {
		  model.addAttribute("next", next);
	  }
	  if (prev != null) {
		  model.addAttribute("prev", prev);
	  }
	  
	  return "naksinuri_original/naksinuri/promotion/m/auditor_board_view";
  }
  
  //낚시명예감시원 - 활동보고서 - 수정하기
  @RequestMapping({"/promotion/auditor/board_modify.do"})
  public String promotion_auditor_board_modify(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, 
		  HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {
    

	  BoardVO info = this.service.congress_findCorp(boardVO);
	  model.addAttribute("info", info);
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	  if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
		  model.addAttribute("MBR_ID", loginVO.getMBR_ID());
	  }
	 

	  return "naksinuri_original/naksinuri/promotion/auditor_board_modify";
  }
  
  //낚시명예감시원 - 활동보고서 - 수정하기 - 모바일
  @RequestMapping({"/promotion/m/auditor/board_modify.do"})
  public String mobile_promotion_auditor_board_modify(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, 
		  HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {

	  BoardVO info = this.service.congress_findCorp(boardVO);
	  model.addAttribute("info", info);
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	  if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
		  model.addAttribute("MBR_ID", loginVO.getMBR_ID());
	  }

	  return "naksinuri_original/naksinuri/promotion/m/auditor_board_modify";
  }
  
  //낚시명예감시원 - 활동보고서 - 수정하기 로직
  @RequestMapping({"/promotion/auditor/board_modify_act.do"})
  public String promotion_auditor_board_modify_act (@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request,
		  HttpServletResponse response, ModelMap model, HttpSession session, MultipartHttpServletRequest multiRequest) throws Exception {
	  
	  LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	  
	  String mimg = "mimg";
	  String simg = "simg";
	  String _mainFileId = "";
	  String _subFileId = "";
	  String _atchFileId = "";
	  List _result = null;
	  List _result2 = null;
	  List _result3 = null;
	  
	  Map files = multiRequest.getFileMap();
	  Map mfile = multiRequest.getFileMap();
	  Map sfile = multiRequest.getFileMap();

	  mfile.clear();
	  sfile.clear();

	  if (files.get(mimg) != null) {
		  mfile.put(mimg, files.get(mimg));
		  files.remove(mimg);
	  }
	
	  if (files.get(simg) != null) {
		  sfile.put(simg, files.get(simg));
		  files.remove(simg);
	  }
	
	  if (!mfile.isEmpty()) {
		  _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
		  for (int i = 0; i < _result.size(); i++) {
		    if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
		      LOGGER.debug("파일에러");
		      return "redirect:/error/ext/warn.do";
		    }
		  }
		  //_mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
	  }

	  if (!sfile.isEmpty()) {
		  _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
		  for (int i = 0; i < _result2.size(); i++) {
			  if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
				  LOGGER.debug("파일에러");
				  return "redirect:/error/ext/warn.do";
			  }
		  }
		  //_subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
	  }
	
	  if (!files.isEmpty()) {
		  _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
		  for (int i = 0; i < _result3.size(); i++) {
		    if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
		      LOGGER.debug("파일에러");
		      return "redirect:/error/ext/warn.do";
		    }
		  }
		  //_atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);
	  }
	  
	 /*
	  boardVO.setBo_name(loginVO.getMBR_NM());
	  boardVO.setBo_phone(loginVO.getMBR_HP());
	  boardVO.setBo_email(loginVO.getMBR_EMAIL());
	  boardVO.setBo_member_id(loginVO.getMBR_ID());*/
	  
	  boardVO.setBo_main_img(_mainFileId);
	  boardVO.setBo_sub_img(_subFileId);
	  boardVO.setBo_atch_file(_atchFileId);
	
	  //필수값
	  boardVO.setBo_cate("낚시명예감시원");
	  boardVO.setBo_type("auditor");
	  boardVO.setBo_name(loginVO.getMBR_NM());
	  
	  this.service.update_data(boardVO);
	  
	  String returnUrl = "redirect:/promotion/auditor/board_list.do";
	  
	  return returnUrl;

  }
  
  
  @RequestMapping({"/promotion/plocation/m/list.do"})
  public String plocation_list_mobile(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVo, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    LOGGER.debug("NaksinuriController - plocation_list_mobile : 낚시정책(모바일) - 낚시금지구역 - 낚시금지구역");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setBo_name("낚시금지구역");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("plocation");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setCategory_type("plocation");
    staticVO.setCategory_name("낚시금지구역");
    staticVO.setPath_type("mobile");

    this.service_statistic.get_statisticInfo(staticVO,request);

    String category = "1";
    GoneVo.setPageInfo(model);
    GoneVo.setPageUnit(GoneVo.getPageUnit());
    GoneVo.setX_category(category);

    List list = this.service_gone.getListGonemobile(GoneVo);

    if (list.size() > 0)
      GoneVo.setTotalPage(((NaksinuriGoneVO)list.get(0)).getTot_cnt());
    else {
      GoneVo.setTotalPage(0);
    }
    model.addAttribute("sido", GoneVo.getSido());
    model.addAttribute("gugun", GoneVo.getGugun());

    if (list.size() > 0)
    {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)list.get(0)).getTot_cnt()));
    }
    return "naksinuri_original/naksinuri/promotion/m/plocation_list";
  }

  @RequestMapping({"/promotion/llocation/m/list.do"})
  public String llocation_list_mobile(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVo, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    LOGGER.debug("NaksinuriController - llocation_list_mobile : 낚시정책(모바일) - 낚시금지구역 - 낚시제한구역");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setBo_name("낚시제한구역");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("llocation");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setCategory_type("llocation");
    staticVO.setCategory_name("낚시제한구역");
    staticVO.setPath_type("mobile");

    this.service_statistic.get_statisticInfo(staticVO,request);

    String category = "2";
    GoneVo.setPageInfo(model);
    GoneVo.setPageUnit(GoneVo.getPageUnit());
    GoneVo.setX_category(category);

    List list = this.service_gone.getListGonemobile(GoneVo);

    if (list.size() > 0)
      GoneVo.setTotalPage(((NaksinuriGoneVO)list.get(0)).getTot_cnt());
    else {
      GoneVo.setTotalPage(0);
    }
    model.addAttribute("sido", GoneVo.getSido());
    model.addAttribute("gugun", GoneVo.getGugun());

    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)list.get(0)).getTot_cnt()));
    }
    return "naksinuri_original/naksinuri/promotion/m/llocation_list";
  }

  @RequestMapping({"/error/ext/warn.do"})
  public String ext_error(HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug(request.getServletPath());
    return "naksinuri_original/naksinuri/ext_error";
  }

  @RequestMapping({"/info/fishjob/list.do"})
  public String list_fishjob(@ModelAttribute("naksinuriFileVO") NaksinuriFileVO naksinuriFileVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    LOGGER.debug("NaksinuriController - list_fishjob : 낚시정보 - 낚시터정보");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    naksinuriFileVO.setPageUnit(16);
    naksinuriFileVO.setPageInfo(model);
    naksinuriFileVO.setPageUnit(naksinuriFileVO.getPageUnit());
    String order_type = request.getParameter("searchType");
    if (order_type == null) {
      naksinuriFileVO.setSearchType("id");
      model.addAttribute("order_type", "id");
    } else {
      naksinuriFileVO.setSearchType(order_type);
      model.addAttribute("order_type", order_type);
    }

    staticVO.setBo_name("낚시터정보");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("fishjob");

    String category_type = "";
    String category_name = "";
    if (request.getParameter("searchBoat") != null) {
      category_type = "searchBoat";
      category_name = "선상낚시";
    } else if (request.getParameter("searchSea") != null) {
      category_type = "searchSea";
      category_name = "바다낚시";
    } else if (request.getParameter("searchRiver") != null) {
      category_type = "searchRiver";
      category_name = "민물낚시";
    } else {
      category_type = "all";
      category_name = "전체";
    }
    staticVO.setCategory_group_type("fishing_info");
    staticVO.setCategory_group_name("낚시정보");
    staticVO.setCategory_type(category_type);
    staticVO.setCategory_name(category_name);
    staticVO.setPath_type("web");

    this.service_statistic.get_statisticInfo(staticVO,request);
    List clist = new ArrayList();
    String[] search_info2 = null;
    if (naksinuriFileVO.getSearch_info() != null) {
      search_info2 = naksinuriFileVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
      model.addAttribute("search_info", naksinuriFileVO.getSearch_info());
    }
    naksinuriFileVO.setSearch_info2(clist);

    List list = this.service.getListFishJob(naksinuriFileVO);

    model.addAttribute("searchText1", request.getParameter("searchText1"));
    model.addAttribute("searchText2", request.getParameter("searchText2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchSido", request.getParameter("searchSido"));
    model.addAttribute("searchGugun", request.getParameter("searchGugun"));
    model.addAttribute("searchBoat", request.getParameter("searchBoat"));
    model.addAttribute("searchSea", request.getParameter("searchSea"));
    model.addAttribute("searchRiver", request.getParameter("searchRiver"));
    model.addAttribute("category_type", category_type);
    
    if (list.size() > 0) {
      naksinuriFileVO.setTotalPage(((NaksinuriFileVO)list.get(0)).getTot_cnt());
    }
    else
    {
      naksinuriFileVO.setTotalPage(0);
    }
    if (list.size() > 0)
    {
      model.addAttribute("fish_list", list);
      model.addAttribute("fish_total", Integer.valueOf(((NaksinuriFileVO)list.get(0)).getTot_cnt()));

      int cnt = ((NaksinuriFileVO)list.get(0)).getTot_cnt();
      int PageUnit = 16;
      int pagesize = Math.round(cnt / PageUnit) + 1;
      model.addAttribute("pagesize", Integer.valueOf(pagesize));
    }
    return "naksinuri_original/naksinuri/info/fishjob_list";
  }

  @RequestMapping({"/info/fishjob/view.do"})
  public String view_fishjob(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, FishjobReviewVO reviewVO, NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriFileVO mimg = this.service.admin_mimg(naksinurifileVO);
    NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);
    naksinurifileVO.setAtch_file_id(resultVO.getAtch_file_id());
    List list = this.service.admin_searchFile1(naksinurifileVO);

    this.service.up_hit(naksinurifileVO);

    String[] sido = resultVO.getCo_addr2_2().split(" ");
    fishesVO.setFishing_type(resultVO.getFishing_type());
    List fishlist = this.fishesService.fish_list(fishesVO);
    List review_list = this.service_review.get_reviewlist(reviewVO);

    model.addAttribute("review_list", review_list);
    model.addAttribute("fishlist", fishlist);
    model.addAttribute("sido", sido);
    model.addAttribute("info", resultVO);
    model.addAttribute("filelist", list);
    model.addAttribute("mimg", mimg);

    NaksinuriVO next = this.service.select_next(naksinuriVO);
    NaksinuriVO prev = this.service.select_prev(naksinuriVO);
    if (next != null) {
      model.addAttribute("next", next);
    }
    if (prev != null) {
      model.addAttribute("prev", prev);
    }

    return "naksinuri_original/naksinuri/info/fishjob_view";
  }

  @RequestMapping({"/info/fishjob/m/list.do"})
  public String fishjob_mobile_list(@ModelAttribute("naksinuriFileVO") NaksinuriFileVO naksinuriFileVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    LOGGER.debug("NaksinuriController - fishjob_mobile_list : 낚시정보(모바일) - 낚시터정보");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    naksinuriFileVO.setPageUnit(16);
    naksinuriFileVO.setPageInfo(model);
    naksinuriFileVO.setPageUnit(naksinuriFileVO.getPageUnit());

    String order_type = request.getParameter("searchType");
    if (order_type == null) {
      naksinuriFileVO.setSearchType("id");
      model.addAttribute("order_type", "id");
    } else {
      naksinuriFileVO.setSearchType(order_type);
      model.addAttribute("order_type", order_type);
    }

    String category_type = "";
    String category_name = "";
    if (request.getParameter("searchBoat") != null) {
      category_type = "searchBoat";
      category_name = "선상낚시";
    } else if (request.getParameter("searchSea") != null) {
      category_type = "searchSea";
      category_name = "바다낚시";
    } else if (request.getParameter("searchRiver") != null) {
      category_type = "searchRiver";
      category_name = "민물낚시";
    } else {
      category_type = "all";
      category_name = "전체";
    }
    staticVO.setCategory_group_type("fishing_info");
    staticVO.setCategory_group_name("낚시정보");
    staticVO.setCategory_type(category_type);
    staticVO.setCategory_name(category_name);
    staticVO.setPath_type("mobile");

    staticVO.setBo_name("낚시터정보");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("fishjob");
    this.service_statistic.get_statisticInfo(staticVO,request);

    List clist = new ArrayList();
    String[] search_info2 = null;
    if (naksinuriFileVO.getSearch_info() != null) {
      search_info2 = naksinuriFileVO.getSearch_info().split(",");
      for (int i = 0; i < search_info2.length; i++) {
        clist.add(search_info2[i]);
      }
    }
    naksinuriFileVO.setSearch_info2(clist);

    List list = this.service.getListFishJob(naksinuriFileVO);

    model.addAttribute("searchText1", request.getParameter("searchText1"));
    model.addAttribute("searchText2", request.getParameter("searchText2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchSido", request.getParameter("searchSido"));
    model.addAttribute("searchGugun", request.getParameter("searchGugun"));
    model.addAttribute("searchBoat", request.getParameter("searchBoat"));
    model.addAttribute("searchSea", request.getParameter("searchSea"));
    model.addAttribute("searchRiver", request.getParameter("searchRiver"));

    if (list.size() > 0) {
      naksinuriFileVO.setTotalPage(((NaksinuriFileVO)list.get(0)).getTot_cnt());
    } else
    {
      naksinuriFileVO.setTotalPage(0);
    }
    if (list.size() > 0)
    {
      model.addAttribute("fish_list", list);
      model.addAttribute("fish_total", Integer.valueOf(((NaksinuriFileVO)list.get(0)).getTot_cnt()));

      int cnt = ((NaksinuriFileVO)list.get(0)).getTot_cnt();
      int PageUnit = 16;
      int pagesize = Math.round(cnt / PageUnit) + 1;
      model.addAttribute("pagesize", Integer.valueOf(pagesize));
    }

    return "naksinuri_original/naksinuri/info/m/fishjob_list";
  }

  @RequestMapping({"/info/fishjob/m/view.do"})
  public String fishjob_mobile_view(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishjobReviewVO reviewVO, NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriFileVO mimg = this.service.admin_mimg(naksinurifileVO);
    NaksinuriVO resultVO = this.service.modifyCorp_admin(naksinuriVO);
    naksinurifileVO.setAtch_file_id(resultVO.getAtch_file_id());
    List list = this.service.admin_searchFile1(naksinurifileVO);
    String[] sido = resultVO.getCo_addr2_2().split(" ");
    List review_list = this.service_review.get_reviewlist(reviewVO);

    this.service.up_hit(naksinurifileVO);
    model.addAttribute("review_list", review_list);
    model.addAttribute("sido", sido);
    model.addAttribute("info", resultVO);
    model.addAttribute("filelist", list);
    model.addAttribute("mimg", mimg);

    NaksinuriVO next = this.service.select_next(naksinuriVO);
    NaksinuriVO prev = this.service.select_prev(naksinuriVO);
    if (next != null) {
      model.addAttribute("next", next);
    }
    if (prev != null) {
      model.addAttribute("prev", prev);
    }

    return "naksinuri_original/naksinuri/info/m/fishjob_view";
  }

	@RequestMapping(value="/info/fishCompany/list.do")
	public String list_fishCompany(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "naksinuri_original/naksinuri/info/fishCompany_list";
	}
	
	@RequestMapping(value="/info/oceanFshIdex/list.do")
	public String list_oceanFshIdex(HttpServletRequest request, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		String currentDate = mPublicUtils.currentTime("yyyy-MM-dd");
		{// 좋음지역
			NaksinuriVO naksinuriVO = new NaksinuriVO();
			naksinuriVO.setDate(currentDate);
			naksinuriVO.setTotal_score("good");
			List<NaksinuriVO> areaList = this.service.get_ocean_fsh_idex_area_list(naksinuriVO);
			model.addAttribute("goodAreaList", areaList);
		}
		{// 나쁨지역
			NaksinuriVO naksinuriVO = new NaksinuriVO();
			naksinuriVO.setDate(currentDate);
			naksinuriVO.setTotal_score("bad");
			List<NaksinuriVO> areaList = this.service.get_ocean_fsh_idex_area_list(naksinuriVO);
			model.addAttribute("badAreaList", areaList);
		}
		return "naksinuri_original/naksinuri/info/oceanFshIdex_list";
	}
  
	@RequestMapping(value="/info/{addWebLink}/listAjax.do", method = RequestMethod.POST)
	public @ResponseBody String listAjax_fishCompany(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, NaksinuriFileVO naksinuriFileVO, 
			@PathVariable("addWebLink") String addWebLink, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		JSONObject data = new JSONObject();
		if(addWebLink.equals("fishCompany")){
			LOGGER.debug("NaksinuriCon`troller - list_fishjob : 낚시정보 - 낚시컴퍼니정보");
		} else {
			LOGGER.debug("NaksinuriCon`troller - list_fish : 낚시정보 - 낚시산업정보");
		}
		LOGGER.debug("- 파라미터 목록 --------------");

		if (request.getParameter("searchBoat") != null && !request.getParameter("searchBoat").equals("")) {
			naksinuriFileVO.setSearchBoat("boatfishing"); 
		}
		if(request.getParameter("searchSea") != null && !request.getParameter("searchSea").equals("")){
			naksinuriFileVO.setSearchSea("seafishing"); 
		}
		if(request.getParameter("searchRiver") != null && !request.getParameter("searchRiver").equals("")){
			naksinuriFileVO.setSearchRiver("riverfishing"); 
		} 
		
		List<NaksinuriFileVO> companyList = new ArrayList<>();
		List<NaksinuriSanupVO> sanupList = new ArrayList<>();
		System.out.println("::::::::::: >>>>> " + naksinuriFileVO.getCmpnm_nm());
		if(addWebLink.equals("fishCompany")){
			naksinuriFileVO.setCo_nm(naksinuriFileVO.getCmpnm_nm());
			companyList = this.service.getListFishCompany(naksinuriFileVO);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			
			data.put("fish_list", mapper.writeValueAsString(companyList));

			
		} else {
			SanupVO.setNotUsedPagination(true);
			if(request.getParameter("sanupSelect") != null && !request.getParameter("sanupSelect").equals("")){
				if(request.getParameter("sanupSelect").equals("san_aag")){
					SanupVO.setSan_aag("협회/기관/단체");
				} else if (request.getParameter("sanupSelect").equals("san_zogu")){
					SanupVO.setSan_zogu("조구업체");
				} else if (request.getParameter("sanupSelect").equals("san_media")){
					SanupVO.setSan_media("미디어");
				} else if (request.getParameter("sanupSelect").equals("san_sell")){
					SanupVO.setSan_sell("판매점");
				} else if (request.getParameter("sanupSelect").equals("san_chool")){
					SanupVO.setSan_chool("출조점");
				} else {
					SanupVO.setSan_inprov("낚시정보제공");
				}
			}
			SanupVO.setSan_name(SanupVO.getCmpnm_nm());
			sanupList = this.service_sanup.getListIndustry(SanupVO);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			
			data.put("fish_list", mapper.writeValueAsString(sanupList));
		}
		data.put("searchText1", request.getParameter("searchText1"));
		data.put("co_nm", request.getParameter("co_nm"));
		data.put("searchType", request.getParameter("searchType"));
		data.put("searchSido", request.getParameter("searchSido"));
		data.put("searchGugun", request.getParameter("searchGugun"));
		data.put("searchBoat", request.getParameter("searchBoat"));
		data.put("searchSea", request.getParameter("searchSea"));
		data.put("searchRiver", request.getParameter("searchRiver"));
		data.put("addWebLink", addWebLink);
		
//		Map<String, Object> postMap = new HashMap<String,Object>();
//		postMap.put("return_url", "");
//		postMap.put("title", "");
//    	postMap.put("message",  "로드가 완료되었습니다.");
//    	postMap.put("closebtn", "N");
//		postMap.put("type", "");//alert
//		postMap.put("timer", 3000);//0:없어지지않음(1000=1초)
//		data.put("alert_data",postMap);


		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
    return null;
  }
	
	@RequestMapping(value="/info/oceanFshIdex/listAjax.do", method = RequestMethod.POST)
	public @ResponseBody String listAjax_oceanFshIdex(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		JSONObject data = new JSONObject();
		
		PublicUtils mPublicUtils = new PublicUtils();
		EgovDateUtil mEgovDateUtil = new EgovDateUtil();
		
		List<NaksinuriVO> oceanFshIdexList = new ArrayList<>();
		oceanFshIdexList = this.service.getListOceanFshIdex(naksinuriVO);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
				
		data.put("oceanFshIdex_list", mapper.writeValueAsString(oceanFshIdexList));
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	@RequestMapping(value = "/info/oceanFshIdex/infoAjax.do", method = RequestMethod.POST)
	public @ResponseBody String infoAjax_oceanFshIdex(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();
		JSONArray jsonData = new JSONArray(); //data
		JSONObject infoData = new JSONObject();
		
		naksinuriVO = this.service.getInfoOceanFshIdex(naksinuriVO);
		
		infoData.put("air_temp", naksinuriVO.getAir_temp());
		infoData.put("water_temp", naksinuriVO.getWater_temp());
		infoData.put("wave_height", naksinuriVO.getWave_height());
		infoData.put("total_score", naksinuriVO.getTotal_score());
		
		data.put("info", infoData);
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	@RequestMapping(value = "/info/oceanFshIdex/{addWebLink}InfoAjax.do", method = RequestMethod.POST)
	public @ResponseBody String infoAjax_oceanFshIdex2(@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, HttpServletResponse response, ModelMap model)
					throws Exception {
		NaksinuriVO naksinuriVO = new NaksinuriVO();
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();
		JSONArray jsonData = new JSONArray(); //data
		JSONObject infoData = new JSONObject();
		
		if(addWebLink.equals("next"))
			naksinuriVO.setDate(mPublicUtils.changePatternString(EgovDateUtil.addYearMonthDay(request.getParameter("date"), 0, 0, 1), "yyyyMMdd", "yyyy-MM-dd"));
		if(addWebLink.equals("prev"))
			naksinuriVO.setDate(mPublicUtils.changePatternString(EgovDateUtil.addYearMonthDay(request.getParameter("date"), 0, 0, -1), "yyyyMMdd", "yyyy-MM-dd"));
		
		naksinuriVO.setName(request.getParameter("name"));
		naksinuriVO = this.service.getInfoOceanFshIdex(naksinuriVO);
		
		if(naksinuriVO != null && naksinuriVO.getName() != null && !naksinuriVO.getName().equals("")){
			infoData.put("date", naksinuriVO.getDate());
			infoData.put("air_temp", naksinuriVO.getAir_temp());
			infoData.put("water_temp", naksinuriVO.getWater_temp());
			infoData.put("wave_height", naksinuriVO.getWave_height());
			infoData.put("total_score", naksinuriVO.getTotal_score());
			
			data.put("error", 0);
			data.put("info", infoData);
		} else {
			data.put("error", 1);
		}
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	@RequestMapping(value="/info/fishFshtbSttus/list.do")
	public String list_fishFshtbSttus(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
		// 연도별 낚시어선업 신고 현황
		{
			List<NaksinuriVO> list = this.service.get_yr_fshtb_sttus();
			model.addAttribute("yrFshtbList", list);
			model.addAttribute("yrFshtbListJson", mapper.writeValueAsString(list));
		}

		// 연도별 낚시어선업 신고 현황
		{
			List<NaksinuriVO> list = this.service.get_yr_fshtb_user_sttus();
			model.addAttribute("yrFshtbUserList", list);
			model.addAttribute("yrFshtbUserListJson", mapper.writeValueAsString(list));
		}
		
		// 연도별 낚시어선업 신고 현황
		{
			int settingYear = Integer.parseInt(mPublicUtils.currentTime("yyyy")) - 1;
			NaksinuriVO naksinuriVO = new NaksinuriVO();
			naksinuriVO.setYear(settingYear);
			List<NaksinuriVO> list = this.service.get_area_fshtb_sttus(naksinuriVO);
			model.addAttribute("areaFshtbList", list);
		}
		
		
		return "naksinuri_original/naksinuri/info/fishFshtbSttus_list";
	}
	
	@RequestMapping(value = "/info/{addWebLink}/ajaxView.do", method = RequestMethod.POST)
	public ModelAndView ajax_member_list(@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		String returnUrl = "naksinuri_original/naksinuri/info/fishFshtbSttus_view";
		ModelAndView mModelAndView = new ModelAndView();
		PublicUtils mPublicUtils = new PublicUtils();
		String title = "";
		String subTitle1 = "";
		String subTitle2 = "";
		List<NaksinuriVO> yearList  = new ArrayList<NaksinuriVO>();
		List<NaksinuriVO> totList  = new ArrayList<NaksinuriVO>();
		List<NaksinuriVO> list  = new ArrayList<NaksinuriVO>();
		int dataCnt = 0;
		// 낚시어선업 신고 현황
		if(addWebLink.equals("fshtbSttus")) {
			yearList = this.service.get_fshtb_year_list();
			title = "낚시어선업 신고 현황(" + yearList.get(0).getYear() + "-" + yearList.get(yearList.size()-1).getYear() + ")";
			dataCnt = yearList.size() + 1;
			
			totList = this.service.get_tot_area_fshtb_list();
			
			String[] years = new String[yearList.size()];
			int[] years2 = new int[yearList.size()];
			List list2 = new ArrayList<>();
			for(int i = 0 ; i < yearList.size() ; i++){
				if(i == 6)
					break;
				HashMap yearMap = new HashMap();
				years2[i] = yearList.get(i).getYear();
				years[i] = "y" + yearList.get(i).getYear();
				yearMap.put("yearA", "y" + yearList.get(i).getYear());
				yearMap.put("yearB", "y" + (i + 1));
				list2.add(yearMap);
			}
			NaksinuriVO naksinuriVO = new NaksinuriVO();
			naksinuriVO.setYears(list2);
			naksinuriVO.setYears2(years2);
			list = this.service.get_each_area_fshtb_sttus(naksinuriVO);
			/*for(int i = 0 ; i < list.size() ; i++){
				DecimalFormat decFormat = new DecimalFormat("###,###");
				list.get(i).setY1(decFormat.format(Integer.parseInt(list.get(i).getY1())));
			}*/
		} else if(addWebLink.equals("fshtbUser")) {
			title = "낚시어선업 이용객 수";
			subTitle1 = "낚시어선업 이용객 수(연도별)";
			subTitle2 = "낚시어선 이용객 수(시‧도별-연도별)";
			yearList = this.service.get_fshtb_user_year_list();
			totList = this.service.get_yr_fshtb_user_sttus();
			dataCnt = yearList.size() + 1;
			
			String[] years = new String[yearList.size()];
			int[] years2 = new int[yearList.size()];
			List list2 = new ArrayList<>();
			for(int i = 0 ; i < yearList.size() ; i++){
				if(i == 11)
					break;
				HashMap yearMap = new HashMap();
				years2[i] = yearList.get(i).getYear();
				years[i] = "y" + yearList.get(i).getYear();
				yearMap.put("yearA", "y" + yearList.get(i).getYear());
				yearMap.put("yearB", "y" + (i + 1));
				list2.add(yearMap);
			}
			NaksinuriVO naksinuriVO = new NaksinuriVO();
			naksinuriVO.setYears(list2);
			naksinuriVO.setYears2(years2);
			list = this.service.get_each_area_fshtb_user_sttus(naksinuriVO);
		} else if(addWebLink.equals("siDoFshtb")) {
			int currentYear = Integer.parseInt(mPublicUtils.currentTime("yyyy")) - 1;
			title = currentYear + "년 시‧도별 낚시어선업 규모별(톤급) 신고 현황";
			NaksinuriVO naksinuriVO = new NaksinuriVO();
			naksinuriVO.setYear(currentYear);
			totList = this.service.get_sido_fshtb_before_year_list(naksinuriVO);
			yearList = this.service.get_sido_fshtb_before_year_sttus(naksinuriVO);
			dataCnt = yearList.size() + 1;
		}
		
		
		mModelAndView.addObject("addWebLink", addWebLink);
		mModelAndView.addObject("title", title);
		mModelAndView.addObject("subTitle1", subTitle1);
		mModelAndView.addObject("subTitle2", subTitle2);
		mModelAndView.addObject("dataCnt", dataCnt);
		mModelAndView.addObject("yearList", yearList);
		mModelAndView.addObject("totList", totList);
		mModelAndView.addObject("list", list);
		mModelAndView.setViewName(returnUrl);
		return mModelAndView;
	}
	
	@RequestMapping(value="/info/{addWebLink}/view.do", method = RequestMethod.POST)
	public ModelAndView view_fishCompany(@ModelAttribute("naksinurifileVO") NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable("addWebLink") String addWebLink, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO) throws Exception{
		System.out.println("addWebLink ::::: >> " + addWebLink);
		System.out.println("SanupVO ::::: >> " + SanupVO.getSan_sn());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("naksinuri_original/naksinuri/info/fishCompany_listAjax");
		
		if(addWebLink.equals("fishCompany")){
			NaksinuriFileVO item = this.service.getInfoFishCompany(naksinurifileVO);
			modelAndView.addObject("item",item);
		} else {
			NaksinuriSanupVO item = this.service.getInfoIndustry(SanupVO);
			modelAndView.addObject("item",item);
		}
		
		modelAndView.addObject("addWebLink", addWebLink);
		
		
		
		
		return modelAndView;
	}
	
	@RequestMapping(value="/info/{addWebLink}/mapList.do", method = RequestMethod.POST)
	public ModelAndView view_mapList(@ModelAttribute("naksinurifileVO") NaksinuriFileVO naksinurifileVO, HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable("addWebLink") String addWebLink, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO) throws Exception{
		System.out.println("addWebLink ::::: >> " + addWebLink);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("naksinuri_original/naksinuri/info/fishCompany_mapList");
		
		if(addWebLink.equals("fishCompany")){
			List<NaksinuriFileVO> companyMapList = new ArrayList<>();
			companyMapList = this.service.getMapListFishCompany(naksinurifileVO);
			modelAndView.addObject("companyMapList",companyMapList);
		} else {
			List<NaksinuriSanupVO> sanupList = new ArrayList<>();
			sanupList = this.service_sanup.getMapListIndustry(SanupVO);
			modelAndView.addObject("sanupList",sanupList);
		}
		modelAndView.addObject("addWebLink", addWebLink);
		return modelAndView;
	}

  

  @RequestMapping({"/info/industry/write.do"}) 
  public String industry_write(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, ModelMap model) throws Exception {
      //삽입모드를 위한 랜덤키 생성
      StringBuffer RandKey = new StringBuffer();
      RandKey.append("9999");
      int[] array = {1,2,3,4,5,6,7,8,9,0}; 
      Random ran = new Random();
      ran.setSeed(System.currentTimeMillis());
      for(int i=0; i<5; i++) {
    	  RandKey.append(array[ran.nextInt(10)]);
      }
      //삽입모드를 위한 랜덤키 생성
      model.addAttribute("RandKey", RandKey.toString());

      return "naksinuri_original/naksinuri/info/industry_write2";
  }

  @RequestMapping({"/info/industry/m/write.do"})
  public String industry_write_mobile(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, ModelMap model)
    throws Exception
  {
      //삽입모드를 위한 랜덤키 생성
	  StringBuffer RandKey = new StringBuffer();
      RandKey.append("9999");
      int[] array = {1,2,3,4,5,6,7,8,9,0}; 
      Random ran = new Random();
      ran.setSeed(System.currentTimeMillis());
      for(int i=0; i<5; i++) {
    	  RandKey.append(array[ran.nextInt(10)]);
      }
      //삽입모드를 위한 랜덤키 생성
      model.addAttribute("RandKey", RandKey.toString());

    return "naksinuri_original/naksinuri/info/m/industry_write2";
  }

  @RequestMapping({"/info/industry/ind_insert.do"})
  public String ind_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    VerifyRecaptcha.setSecretKey("6Lf_0iQUAAAAAKsy_UkxMQGQq5BrkDgJ-D_WbPKL");
    String gRecaptchaResponse = request.getParameter("recaptcha");

    boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
    String result = "fail";
    if (verify) {
      result = "success";

      List _result = null;
      List _result2 = null;
      String mainFileId = "";
      String atchFileId = "";
      String san_img = "mimg";
      Map mfile = multiRequest.getFileMap();
      Map files = multiRequest.getFileMap();
      mfile.clear();
      if (files.get(san_img) != null) {
        mfile.put(san_img, files.get(san_img));
        files.remove(san_img);
      }

      if (!mfile.isEmpty()) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
      }

      if (!files.isEmpty()) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
        atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
      }
      SanupVO.setSan_img(mainFileId);
      SanupVO.setSan_simg(atchFileId);
      EgovStringUtil mEgovStringUtil = new EgovStringUtil();

      String san_name = request.getParameter("san_name");
      String san_buisnessman = request.getParameter("san_buisnessman");
      String san_item = request.getParameter("san_item");
      String san_address3 = request.getParameter("san_address3");
      String san_homepage = request.getParameter("san_homepage");
      
      if (san_name != null) {
    	  san_name = mEgovStringUtil.getHtmlStrCnvr(san_name);
    	  SanupVO.setSan_name(san_name);
      }
      if (san_buisnessman != null) {
    	  san_buisnessman = mEgovStringUtil.getHtmlStrCnvr(san_buisnessman);
    	  SanupVO.setSan_buisnessman(san_buisnessman);
      }
      if (san_item != null) {
    	  san_item = mEgovStringUtil.getHtmlStrCnvr(san_item);
    	  SanupVO.setSan_item(san_item);
      }
      if (san_address3 != null) {
    	  san_address3 = mEgovStringUtil.getHtmlStrCnvr(san_address3);
    	  SanupVO.setSan_address3(san_address3);
      }
      if (san_homepage != null) {
    	  san_homepage = mEgovStringUtil.getHtmlStrCnvr(san_homepage);
    	  SanupVO.setSan_homepage(san_homepage);
      }
          
      if(SanupVO.getSan_name() != null && SanupVO.getSan_name().length() != 0){
    	  SanupVO.setSan_name(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_name()));
      }
      if(SanupVO.getSan_buisnessman() != null && SanupVO.getSan_buisnessman().length() != 0){
    	  SanupVO.setSan_buisnessman(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_buisnessman()));
      }
      if(SanupVO.getSan_item() != null && SanupVO.getSan_item().length() != 0){
    	  SanupVO.setSan_item(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_item()));
      }
      if(SanupVO.getSan_address3() != null && SanupVO.getSan_address3().length() != 0){
    	  SanupVO.setSan_address3(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_address3()));
      }
      if(SanupVO.getSan_homepage() != null && SanupVO.getSan_homepage().length() != 0){
    	  SanupVO.setSan_homepage(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_homepage()));
      }

      this.service_sanup.ind_insert(SanupVO);
      return "redirect:/info/industry/result.do";      
   } else {
    	return "redirect:/info/industry/err.do";      
   }
  }

  @RequestMapping({"/info/industry/m/ind_insert.do"})
  public String ind_insert_mobile(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    VerifyRecaptcha.setSecretKey("6Lf_0iQUAAAAAKsy_UkxMQGQq5BrkDgJ-D_WbPKL");
    String gRecaptchaResponse = request.getParameter("recaptcha");

    boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
    String result = "fail";
    if (verify) {
      result = "success";

      List _result = null;
      List _result2 = null;
      String mainFileId = "";
      String atchFileId = "";
      String san_img = "mimg";
      Map mfile = multiRequest.getFileMap();
      Map files = multiRequest.getFileMap();
      mfile.clear();
      if (files.get(san_img) != null) {
        mfile.put(san_img, files.get(san_img));
        files.remove(san_img);
      }

      if (!mfile.isEmpty()) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
      }

      if (!files.isEmpty()) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
        atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
      }
      SanupVO.setSan_img(mainFileId);
      SanupVO.setSan_simg(atchFileId);
      EgovStringUtil mEgovStringUtil = new EgovStringUtil();

      String san_name = request.getParameter("san_name");
      String san_buisnessman = request.getParameter("san_buisnessman");
      String san_item = request.getParameter("san_item");
      String san_address3 = request.getParameter("san_address3");

      san_name = mEgovStringUtil.getHtmlStrCnvr(san_name);
      san_buisnessman = mEgovStringUtil.getHtmlStrCnvr(san_buisnessman);
      san_item = mEgovStringUtil.getHtmlStrCnvr(san_item);
      san_address3 = mEgovStringUtil.getHtmlStrCnvr(san_address3);

      SanupVO.setSan_name(san_name);
      SanupVO.setSan_buisnessman(san_buisnessman);
      SanupVO.setSan_item(san_item);
      SanupVO.setSan_address3(san_address3);

      this.service_sanup.ind_insert(SanupVO);
      return "redirect:/info/industry/m/result.do";
   } else {
    	return "redirect:/info/industry/m/err.do";      
   }
  }

  @RequestMapping({"/info/industry/ind_update.do"})
  public String ind_update(MultipartHttpServletRequest multiRequest, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, SessionStatus status, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String mainatchFileId = SanupVO.getSan_img();
    String atchFileId = SanupVO.getSan_simg();
    String san_img = "mimg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();

    List _result = null;
    List _result2 = null;

    mfile.clear();

    if (files.get(san_img) != null) {
      mfile.put(san_img, files.get(san_img));
      files.remove(san_img);
    }

    if (!mfile.isEmpty()) {
      if (("".equals(mainatchFileId)) || (mainatchFileId == null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        mainatchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
        SanupVO.setSan_img(mainatchFileId);
      } else if ((!"".equals(mainatchFileId)) || (mainatchFileId != null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, mainatchFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }
    }

    if (!files.isEmpty())
    {
      if (("".equals(atchFileId)) || (atchFileId == null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
        atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
        SanupVO.setSan_simg(atchFileId);
      } else if ((!"".equals(atchFileId)) || (atchFileId != null)) {
        NaksinuriOriginalFileVO fvo = new NaksinuriOriginalFileVO();
        fvo.setAtchFileId(atchFileId);
        int cnt = this.fileMngService.getMaxFileSN_naksinuri_original(fvo);
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", cnt, atchFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result2);
      }
    }
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String san_name = request.getParameter("san_name");
    String san_buisnessman = request.getParameter("san_buisnessman");
    String san_item = request.getParameter("san_item");
    String san_address3 = request.getParameter("san_address3");
    String san_homepage = request.getParameter("san_homepage");
    
    if (san_name != null) {
  	  san_name = mEgovStringUtil.getHtmlStrCnvr(san_name);
  	  SanupVO.setSan_name(san_name);
    }
    if (san_buisnessman != null) {
  	  san_buisnessman = mEgovStringUtil.getHtmlStrCnvr(san_buisnessman);
  	  SanupVO.setSan_buisnessman(san_buisnessman);
    }
    if (san_item != null) {
  	  san_item = mEgovStringUtil.getHtmlStrCnvr(san_item);
  	  SanupVO.setSan_item(san_item);
    }
    if (san_address3 != null) {
  	  san_address3 = mEgovStringUtil.getHtmlStrCnvr(san_address3);
  	  SanupVO.setSan_address3(san_address3);
    }
    if (san_homepage != null) {
  	  san_homepage = mEgovStringUtil.getHtmlStrCnvr(san_homepage);
  	  SanupVO.setSan_homepage(san_homepage);
    }
        
    if(SanupVO.getSan_name() != null && SanupVO.getSan_name().length() != 0){
  	  SanupVO.setSan_name(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_name()));
    }
    if(SanupVO.getSan_buisnessman() != null && SanupVO.getSan_buisnessman().length() != 0){
  	  SanupVO.setSan_buisnessman(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_buisnessman()));
    }
    if(SanupVO.getSan_item() != null && SanupVO.getSan_item().length() != 0){
  	  SanupVO.setSan_item(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_item()));
    }
    if(SanupVO.getSan_address3() != null && SanupVO.getSan_address3().length() != 0){
  	  SanupVO.setSan_address3(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_address3()));
    }
    if(SanupVO.getSan_homepage() != null && SanupVO.getSan_homepage().length() != 0){
  	  SanupVO.setSan_homepage(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_homepage()));
    }

    this.service_sanup.ind_update(SanupVO);

    //return "redirect:/info/industry/list.do";
    return "redirect:/info/industry/result.do";
  }

  @RequestMapping({"/info/industry/m/ind_update.do"})
  public String ind_update_mobile(MultipartHttpServletRequest multiRequest, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, SessionStatus status, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String mainatchFileId = SanupVO.getSan_img();
    String atchFileId = SanupVO.getSan_simg();
    String san_img = "mimg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();

    List _result = null;
    List _result2 = null;

    mfile.clear();

    if (files.get(san_img) != null) {
      mfile.put(san_img, files.get(san_img));
      files.remove(san_img);
    }

    if (!mfile.isEmpty()) {
      if (("".equals(mainatchFileId)) || (mainatchFileId == null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        mainatchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
        SanupVO.setSan_img(mainatchFileId);
      } else if ((!"".equals(mainatchFileId)) || (mainatchFileId != null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, mainatchFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }
    }

    if (!files.isEmpty())
    {
      if (("".equals(atchFileId)) || (atchFileId == null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
        atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
        SanupVO.setSan_simg(atchFileId);
      } else if ((!"".equals(atchFileId)) || (atchFileId != null)) {
        NaksinuriOriginalFileVO fvo = new NaksinuriOriginalFileVO();
        fvo.setAtchFileId(atchFileId);
        int cnt = this.fileMngService.getMaxFileSN_naksinuri_original(fvo);
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", cnt, atchFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result2);
      }
    }

    this.service_sanup.ind_update(SanupVO);

    //return "redirect:/info/industry/m/list.do";
    return "redirect:/info/industry/m/result.do";
  }

  @RequestMapping({"/info/industry/ind_find.do"})
  public String ind_find(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
    List list = this.service_sanup.ind_fsimg(SanupVO);

    this.service_sanup.up_hit(SanupVO);

    model.addAttribute("info", info);
    model.addAttribute("simglist", list);

    return "naksinuri_original/naksinuri/info/industry_view";
  }

  @RequestMapping({"/info/industry/m/ind_find.do"})
  public String ind_find_mobile(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	  LOGGER.debug(">>>>>>>>  ind_find");
	  
    NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
    
    LOGGER.debug(">>>>>>>>  ind_fsimg");
    List list = this.service_sanup.ind_fsimg(SanupVO);
    
    this.service_sanup.up_hit(SanupVO);
    model.addAttribute("info", info);
    model.addAttribute("simglist", list);

    return "naksinuri_original/naksinuri/info/m/industry_view";
  }

  @RequestMapping({"/info/industry/ind_edit.do"})
  public String ind_edit(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
    List list = this.service_sanup.ind_fsimg(SanupVO);

    model.addAttribute("info", info);
    model.addAttribute("simglist", list);

    return "naksinuri_original/naksinuri/info/industry_write";
  }

  @RequestMapping({"/survey/survey/list.do"})
  public String survey_list(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, String bo_cate, NaksinuriStatisticVO staticVO, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    LOGGER.debug("NaksinuriController - survey_list : 설문조사 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    String[] data;
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    surveyVO.setPageInfo(model);
    surveyVO.setPageUnit(surveyVO.getPageUnit());

    staticVO.setBo_name("설문조사");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("survey");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("survey");
    staticVO.setCategory_group_name("설문조사");
    staticVO.setPath_type("web");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    model.addAttribute("searchType", surveyVO.getSearchType());
    model.addAttribute("searchText", surveyVO.getSearchText());

    List list = this.service.survey_select_list_user(surveyVO);

    if (list.size() > 0)
      surveyVO.setTotalPage(((SurveyVO)list.get(0)).getTot_cnt());
    else {
      surveyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((SurveyVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/survey/survey_list";
  }
  
  @RequestMapping({"/info/industry/list.do"})
  public String list_industry(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    LOGGER.debug("NaksinuriController - list_industry : 낚시정보 - 낚시산업정보 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    SanupVO.setPageUnit(16);
    SanupVO.setPageInfo(model);
    SanupVO.setPageUnit(SanupVO.getPageUnit());

    String order_type = request.getParameter("searchType");

    if (order_type == null) {
      SanupVO.setSearchType("id");
      model.addAttribute("order_type", "id");
    } else {
      SanupVO.setSearchType(order_type);
      model.addAttribute("order_type", order_type);
    }

    staticVO.setBo_name("낚시산업정보");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("industry");
    staticVO.setPath(request.getRequestURL().toString());

    String category_type = "";
    String category_name = "";
    if (request.getParameter("san_aag") != null) {
      category_type = "san_aag";
      category_name = request.getParameter("san_aag").toString();
    } else if (request.getParameter("san_zogu") != null) {
      category_type = "san_zogu";
      category_name = request.getParameter("san_zogu").toString();
    } else if (request.getParameter("san_media") != null) {
      category_type = "san_media";
      category_name = request.getParameter("san_media").toString();
    } else if (request.getParameter("san_sell") != null) {
      category_type = "san_sell";
      category_name = request.getParameter("san_sell").toString();
    } else if (request.getParameter("san_chool") != null) {
      category_type = "san_chool";
      category_name = request.getParameter("san_chool").toString();
    } else if (request.getParameter("san_inprov") != null) {
      category_type = "san_inprov";
      category_name = request.getParameter("san_inprov").toString();
    } else {
      category_type = "all";
      category_name = "전체";
    }
    staticVO.setCategory_group_type("fishing_info");
    staticVO.setCategory_group_name("낚시정보");
    staticVO.setCategory_type(category_type);
    staticVO.setCategory_name(category_name);
    staticVO.setPath_type("web");

    this.service_statistic.get_statisticInfo(staticVO,request);

    SanupVO.setNotUsedPagination(false);
    List list = this.service_sanup.getListIndustry(SanupVO);

    model.addAttribute("searchText1", request.getParameter("searchText1"));
    model.addAttribute("searchText2", request.getParameter("searchText2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchSido", request.getParameter("searchSido"));
    model.addAttribute("searchGugun", request.getParameter("searchGugun"));
    model.addAttribute("san_aag", request.getParameter("san_aag"));
    model.addAttribute("san_zogu", request.getParameter("san_zogu"));
    model.addAttribute("san_media", request.getParameter("san_media"));
    model.addAttribute("san_sell", request.getParameter("san_sell"));
    model.addAttribute("san_chool", request.getParameter("san_chool"));
    model.addAttribute("san_inprov", request.getParameter("san_inprov"));
    model.addAttribute("category_type", category_type);
    
    if (list.size() > 0) {
      SanupVO.setTotalPage(((NaksinuriSanupVO)list.get(0)).getTot_cnt());
    }
    else
    {
      SanupVO.setTotalPage(0);
    }
    if (list.size() > 0)
    {
      model.addAttribute("ind_list", list);
      model.addAttribute("ind_total", Integer.valueOf(((NaksinuriSanupVO)list.get(0)).getTot_cnt()));

      int cnt = ((NaksinuriSanupVO)list.get(0)).getTot_cnt();
      int PageUnit = 16;
      int pagesize = Math.round(cnt / PageUnit) + 1;
      model.addAttribute("pagesize", Integer.valueOf(pagesize));
    }
    return "naksinuri_original/naksinuri/info/industry_list";
  }

  @RequestMapping({"/info/industry/m/list.do"})
  public String list_industry_mobile(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    LOGGER.debug("NaksinuriController - list_industry_mobile : 낚시정보(모바일) - 낚시산업정보 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    SanupVO.setPageUnit(16);
    SanupVO.setPageInfo(model);
    SanupVO.setPageUnit(SanupVO.getPageUnit());

    String order_type = request.getParameter("searchType");
    if (order_type == null) {
      SanupVO.setSearchType("id");
      model.addAttribute("order_type", "id");
    } else {
      SanupVO.setSearchType(order_type);
      model.addAttribute("order_type", order_type);
    }

    String category_type = "";
    String category_name = "";
    if (request.getParameter("san_aag") != null) {
      category_type = "san_aag";
      category_name = request.getParameter("san_aag").toString();
    } else if (request.getParameter("san_zogu") != null) {
      category_type = "san_zogu";
      category_name = request.getParameter("san_zogu").toString();
    } else if (request.getParameter("san_media") != null) {
      category_type = "san_media";
      category_name = request.getParameter("san_media").toString();
    } else if (request.getParameter("san_sell") != null) {
      category_type = "san_sell";
      category_name = request.getParameter("san_sell").toString();
    } else if (request.getParameter("san_chool") != null) {
      category_type = "san_chool";
      category_name = request.getParameter("san_chool").toString();
    } else if (request.getParameter("san_inprov") != null) {
      category_type = "san_inprov";
      category_name = request.getParameter("san_inprov").toString();
    } else {
      category_type = "all";
      category_name = "전체";
    }
    staticVO.setCategory_group_type("fishing_info");
    staticVO.setCategory_group_name("낚시정보");
    staticVO.setCategory_type(category_type);
    staticVO.setCategory_name(category_name);
    staticVO.setPath_type("mobile");

    staticVO.setBo_name("낚시산업정보");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("industry");
    staticVO.setPath(request.getRequestURL().toString());
    this.service_statistic.get_statisticInfo(staticVO,request);

    SanupVO.setNotUsedPagination(false);
    List list = this.service_sanup.getListIndustry(SanupVO);

    model.addAttribute("searchText1", request.getParameter("searchText1"));
    model.addAttribute("searchText2", request.getParameter("searchText2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchSido", request.getParameter("searchSido"));
    model.addAttribute("searchGugun", request.getParameter("searchGugun"));
    model.addAttribute("san_aag", request.getParameter("san_aag"));
    model.addAttribute("san_zogu", request.getParameter("san_zogu"));
    model.addAttribute("san_media", request.getParameter("san_media"));
    model.addAttribute("san_sell", request.getParameter("san_sell"));
    model.addAttribute("san_chool", request.getParameter("san_chool"));
    model.addAttribute("san_inprov", request.getParameter("san_inprov"));

    if (list.size() > 0) {
      SanupVO.setTotalPage(((NaksinuriSanupVO)list.get(0)).getTot_cnt());
    }
    else
    {
      SanupVO.setTotalPage(0);
    }
    if (list.size() > 0)
    {
      model.addAttribute("ind_list", list);
      model.addAttribute("ind_total", Integer.valueOf(((NaksinuriSanupVO)list.get(0)).getTot_cnt()));

      int cnt = ((NaksinuriSanupVO)list.get(0)).getTot_cnt();
      int PageUnit = 16;
      int pagesize = Math.round(cnt / PageUnit) + 1;
      model.addAttribute("pagesize", Integer.valueOf(pagesize));
    }
    return "naksinuri_original/naksinuri/info/m/industry_list";
  }

  @RequestMapping({"/survey/survey/m/list.do"})
  public String survey_list_mobile(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, String bo_cate, NaksinuriStatisticVO staticVO, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    LOGGER.debug("NaksinuriController - survey_list_mobile : 설문조사(모바일) ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    String[] data;
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    surveyVO.setPageInfo(model);
    surveyVO.setPageUnit(surveyVO.getPageUnit());

    staticVO.setBo_name("설문조사");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("survey");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("survey");
    staticVO.setCategory_group_name("설문조사");
    staticVO.setPath_type("mobile");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    model.addAttribute("searchType", surveyVO.getSearchType());
    model.addAttribute("searchText", surveyVO.getSearchText());

    List list = this.service.survey_select_list_user(surveyVO);

    if (list.size() > 0)
      surveyVO.setTotalPage(((SurveyVO)list.get(0)).getTot_cnt());
    else {
      surveyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((SurveyVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/survey/m/survey_list";
  }

  @RequestMapping({"/survey/survey/endlist.do"})
  public String end_survey_list(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, String bo_cate, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    surveyVO.setPageInfo(model);
    surveyVO.setPageUnit(surveyVO.getPageUnit());

    model.addAttribute("searchType", surveyVO.getSearchType());
    model.addAttribute("searchText", surveyVO.getSearchText());

    List list = this.service.end_survey_select_list_user(surveyVO);

    if (list.size() > 0)
      surveyVO.setTotalPage(((SurveyVO)list.get(0)).getTot_cnt());
    else {
      surveyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((SurveyVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/survey/survey_endlist";
  }

  @RequestMapping({"/survey/survey/m/endlist.do"})
  public String end_survey_list_mobile(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, String bo_cate, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    surveyVO.setPageInfo(model);
    surveyVO.setPageUnit(surveyVO.getPageUnit());

    model.addAttribute("searchType", surveyVO.getSearchType());
    model.addAttribute("searchText", surveyVO.getSearchText());

    List list = this.service.end_survey_select_list_user(surveyVO);

    if (list.size() > 0)
      surveyVO.setTotalPage(((SurveyVO)list.get(0)).getTot_cnt());
    else {
      surveyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((SurveyVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/survey/m/survey_endlist";
  }

  @RequestMapping({"/survey/survey/view.do"})
  public String survey_findCorp(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    SurveyVO info = this.service.select_survey(surveyVO);
    List quest = this.service.select_surveyq_list(surveyVO);
    List qitem = this.service.select_surveyqi_list(surveyVO);
    List anscnt = this.service.survey_anscnt(surveyVO);

    model.addAttribute("info", info);
    model.addAttribute("quest", quest);
    model.addAttribute("qitem", qitem);
    model.addAttribute("aan", anscnt);

    return "naksinuri_original/naksinuri/survey/survey_view";
  }

  @RequestMapping({"/survey/survey/result.do"})
  public String survey_result(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    SurveyVO info = this.service.select_survey(surveyVO);
    List quest = this.service.select_surveyq_list(surveyVO);
    List qitem = this.service.select_surveyqi_list(surveyVO);
    List anscnt = this.service.survey_anscnt(surveyVO);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

    Calendar today = new GregorianCalendar();

    String timedata1 = formatter.format(today.getTime());
    String enddt = info.getSv_end_dt();
    String enddt2 = enddt.replace("-", "");

    int end_dt = Integer.parseInt(enddt2);
    int today_int = Integer.parseInt(timedata1);

    if (info.getSv_show().equals("1")) {
      if (end_dt > today_int) {
        String msg = "해당 설문조사는 현재 결과를 조회할 수 없습니다.";
        model.addAttribute("msg", msg);
        model.addAttribute("url", "/survey/survey/list.do");
        return "naksinuri_original/naksinuri/survey/message";
      }
    }
    else if (info.getSv_show().equals("2")) {
      String msg = "해당 설문조사는 결과를 조회할 수 없습니다.";
      model.addAttribute("msg", msg);
      model.addAttribute("url", "/survey/survey/list.do");
      return "naksinuri_original/naksinuri/survey/message";
    }

    model.addAttribute("info", info);
    model.addAttribute("quest", quest);
    model.addAttribute("qitem", qitem);
    model.addAttribute("aan", anscnt);

    return "naksinuri_original/naksinuri/survey/survey_result";
  }

  @RequestMapping({"/survey/survey/m/result.do"})
  public String survey_result_mobile(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    SurveyVO info = this.service.select_survey(surveyVO);
    List quest = this.service.select_surveyq_list(surveyVO);
    List qitem = this.service.select_surveyqi_list(surveyVO);
    List anscnt = this.service.survey_anscnt(surveyVO);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

    Calendar today = new GregorianCalendar();

    String timedata1 = formatter.format(today.getTime());
    String enddt = info.getSv_end_dt();
    String enddt2 = enddt.replace("-", "");

    int end_dt = Integer.parseInt(enddt2);
    int today_int = Integer.parseInt(timedata1);

    if (info.getSv_show().equals("1")) {
      if (end_dt > today_int) {
        String msg = "해당 설문조사는 현재 결과를 조회할 수 없습니다.";
        model.addAttribute("msg", msg);
        model.addAttribute("url", "/survey/survey/m/list.do");
        return "naksinuri_original/naksinuri/survey/message";
      }
    }
    else if (info.getSv_show().equals("2")) {
      String msg = "해당 설문조사는 결과를 조회할 수 없습니다.";
      model.addAttribute("msg", msg);
      model.addAttribute("url", "/survey/survey/m/list.do");
      return "naksinuri_original/naksinuri/survey/message";
    }

    model.addAttribute("info", info);
    model.addAttribute("quest", quest);
    model.addAttribute("qitem", qitem);
    model.addAttribute("aan", anscnt);

    return "naksinuri_original/naksinuri/survey/m/survey_result";
  }

  @RequestMapping({"/survey/survey/m/view.do"})
  public String survey_findCorp_mobile(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    SurveyVO info = this.service.select_survey(surveyVO);
    List quest = this.service.select_surveyq_list(surveyVO);
    List qitem = this.service.select_surveyqi_list(surveyVO);
    List anscnt = this.service.survey_anscnt(surveyVO);

    model.addAttribute("info", info);
    model.addAttribute("quest", quest);
    model.addAttribute("qitem", qitem);
    model.addAttribute("aan", anscnt);

    return "naksinuri_original/naksinuri/survey/m/survey_view";
  }

  @RequestMapping({"/survey/survey/answer.do"})
  public String user_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("surveyVO") SurveyVO surveyVO,
		  @RequestParam(value="CRS_SN", required=false, defaultValue="") String CRS_SN,
		  @RequestParam(value="HMBR_SN", required=false, defaultValue="") String HMBR_SN,
		  @RequestParam(value="key",required=false) String key,
		  BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, ModelMap model) throws Exception 
  {
	
	StringBuilder log_dscrp = new StringBuilder();
	StringBuilder log_msg = new StringBuilder();
	StringBuilder tbl_inf = new StringBuilder();
	StringBuilder tbl_sn = new StringBuilder();
  
	String[] arrStr = request.getParameterValues("anscount");
	String[] svq_type = request.getParameterValues("svq_type");
	String[] sqi_count = request.getParameterValues("sqi_count");
	String[] mxcnt = request.getParameterValues("mxcnt");
	String[] sqq = request.getParameterValues("sq_id");
    
	//설문조사 정보 가져오기
	SurveyVO info = this.service.select_survey(surveyVO);
	
	//회원이면 ETC_2에 id넣기
    HttpSession session = request.getSession();
	LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	
	//외부링크시 키 검증하여 임시로그인 처리
	boolean isTempMemberLogin = false;
	String returnUrl = "redirect:/educenter/index.do";
	PublicUtils mPublicUtils = new PublicUtils();
	if(mPublicUtils.isMobileDevice(request)) {
		log_dscrp.append("[모바일]");
	}
	if(key!=null && key.length()!=0) {
		String decodeKey = EgovFileScrty.decode(key);
		log_msg.append("[decodeKey="+decodeKey+"]");
		LOGGER.debug("decodeKey = " + decodeKey);
		if(decodeKey!=null && decodeKey.length()!=0) {
			String[] parseKey = decodeKey.split(",");
			String keyHMBR_SN = "";
			String keyCRS_SN = "";
			String keyMBR_ID = "";
			if(parseKey!=null && parseKey.length > 2) {
				keyHMBR_SN = parseKey[0];
				keyCRS_SN = parseKey[1];
				keyMBR_ID = parseKey[2];
			} 
			log_msg.append("[parseKey="+Arrays.toString(parseKey)+"]");				
			LOGGER.debug("keyHMBR_SN = " + keyHMBR_SN);
			LOGGER.debug("keyCRS_SN = " + keyCRS_SN);
			LOGGER.debug("keyMBR_ID = " + keyMBR_ID);
			if(HMBR_SN.equals(keyHMBR_SN) && CRS_SN.equals(keyCRS_SN)) {
				LOGGER.debug("키 정보가 일치하여 임시 로그인 처리 진행");
				isTempMemberLogin = true;
				EduMemberVO mEduMemberVO = new EduMemberVO();
				mEduMemberVO.setMBR_ID(keyMBR_ID);
				mEduMemberVO = eduMemberService.get_edu_member_info(mEduMemberVO);
				if(mEduMemberVO!=null && mEduMemberVO.getMBR_SN()!=null && mEduMemberVO.getMBR_SN().length()!=0) {	
					LOGGER.debug("키 정보가 존재하여 임시 로그인 처리 진행");
					loginVO = new LoginVO();
					loginVO.setMBR_ID(mEduMemberVO.getMBR_ID());
					loginVO.setMBR_LV_ID(mEduMemberVO.getMBR_LV_ID());	
				} else {
					LOGGER.debug("회원정보가없어처리불가");
				}
			} else {
				LOGGER.debug("키 정보가 일치하지 않아 임시 로그인 처리 불가");
			}			
		}		
	}
	//End of 외부링크시 키 검증하여 임시로그인 처리
	
	if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
		surveyVO.setETC_2(loginVO.getMBR_ID());
	}
	
    if(CRS_SN!=null && CRS_SN.length()!=0 && HMBR_SN!=null && HMBR_SN.length()!=0){
    	surveyVO.setASI_CRS_SN(CRS_SN);
    	surveyVO.setASI_HMBR_SN(HMBR_SN);
    	surveyVO.setHMBR_SN(HMBR_SN);	//이수내역번호
		surveyVO.setETC_1(CRS_SN);		//교육번호
    }
    surveyVO.setSva_ip(getClientIpAddr(request));
    surveyVO.setSva_mb_ip(getClientIpAddr(request));
    SurveyVO used = this.service.check_survey_by_ip(surveyVO);
    if (used.getUsed() > 0) {
    	/*
         * 낚시누리 요청으로 중복아이피 차단 해제 - 2019.06.18 
      model.addAttribute("msg", "이미 설문에 참여하셨습니다.");
      model.addAttribute("url", "/index.do");

      return "naksinuri_original/naksinuri/survey/message";
      */
    	
    	if(info.getSV_LC().equals("online_edu")){//온라인교육 설문조사일 경우에만 중복차단
	    	//ip, hmbr_sn, crs_sn 값이 다 있으면 막음
	    	Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "이미 설문에 참여하셨습니다.");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			
			log_dscrp.append("[온라인 교육 설문에 이미 참여(MBR_ID: " + loginVO.getMBR_ID() + ", CRS_SN: " + CRS_SN + ", HMBR_SN: " + HMBR_SN + ")]");
			tbl_sn.append(used.getAsi_id());
			tbl_inf.append("NAKSINURI_SURVEY_ANSWER_INFO");
			
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(surveyVO));
				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO,request);
			} catch(Exception e) {
				LOGGER.debug("[fail log record] "+e.toString());
			}
			
			if(isTempMemberLogin) {//외부링크 키 검증을 통한 처리
				model.addAttribute("error_msg", "온라인 교육 설문에 이미 참여하였습니다.");
				return "cmmn/error";
			} else {
				return "redirect:/educenter/mbrhstry/list.do";
			}
    	}	
    }
    this.service.survey_insert_answer_info(surveyVO);

    SurveyVO ansno = this.service.survey_answfind(surveyVO);
    surveyVO.setAsi_id(ansno.getAsi_id());

    for (int i = 0; i < arrStr.length; i++) {
      surveyVO.setSvq_type(svq_type[i]);
      surveyVO.setSq_id(sqq[i]);
      if (surveyVO.getSvq_type().equals("O")) {
        for (int j = 0; j < sqi_count.length; j++) {
          int mx = Integer.parseInt(mxcnt[i]);

          if (mx == 1) {
            String[] sva_txt1 = request.getParameterValues(new StringBuilder().append("sva_etc").append(arrStr[i]).append("").toString());
            for (int k = 0; k < sva_txt1.length; k++) {
              if (sva_txt1[k] != null) {
                surveyVO.setSva_txt(sva_txt1[k]);
              }
            }
            String sqi_id = request.getParameter(new StringBuilder().append("sqi_id").append(sqi_count[j]).append("").toString());
            String sq_num = request.getParameter(new StringBuilder().append("sq_num").append(sqi_count[j]).append("").toString());
            surveyVO.setSqi_id(sqi_id);
            if ((sqi_id != null) && (surveyVO.getSq_id().equals(sq_num))) {
              surveyVO.setSq_id(sq_num);
              this.service.survey_insert_answer(surveyVO);
            }
          } else {
            String[] sqi_id = request.getParameterValues(new StringBuilder().append("sqi_id").append(sqi_count[j]).append("").toString());
            String sq_num = request.getParameter(new StringBuilder().append("sq_num").append(sqi_count[j]).append("").toString());
            if ((sqi_id != null) && (surveyVO.getSq_id().equals(sq_num))) {
              surveyVO.setSq_id(sq_num);
              for (int m = 0; m < sqi_id.length; m++) {
                String sva_txt = request.getParameter(new StringBuilder().append("sva_etc").append(arrStr[i]).append("_").append(sqi_count[m]).append("").toString());
                surveyVO.setSva_txt(sva_txt);
                surveyVO.setSqi_id(sqi_id[m]);
                this.service.survey_insert_answer(surveyVO);
              }
            }
          }
        }
      } else {
        String sva_txt = request.getParameter(new StringBuilder().append("sva_txt").append(arrStr[i]).toString());
        surveyVO.setSqi_id(null);
        surveyVO.setSva_txt(sva_txt);
        this.service.survey_insert_answer(surveyVO);
      }
    }
    /******************************************************************/
    //온라인교육 설문이면! 
    if(info.getSV_LC().equals("online_edu")){
    	
    	EduMyHistoryVO eduMyHistoryVO = new EduMyHistoryVO();
    	
    	try {
	    	eduMyHistoryVO.setHMBR_SV_ST("1");	//설문조사 참여:1, 미참여:0
	    	eduMyHistoryVO.setLRNNG_ST("1");	//신청상태 승인:1, 대기:0
	    	eduMyHistoryVO.setHMBR_SN(HMBR_SN);
	    	eduMyHistoryVO.setCRS_SN(CRS_SN);
	    	eduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
	    	eduMyHistoryVO.setMBR_ID(loginVO.getMBR_ID());
	    	//LRNNG_CMPLT_ST를 넣지 않으면 완료 일자, 아이디가 null이 됨
	    	eduMyHistoryVO.setLRNNG_CMPLT_ST("1");
	    	
	    	//설문조사 참여여부 업데이트
	    	eduMyHistoryService.set_edu_mbrhstry_mod(eduMyHistoryVO);
	    	
	    	log_dscrp.append("[설문조사 참여여부 업데이트 완료");
	    	LOGGER.debug("설문조사 참여여부 업데이트 완료");
	    	tbl_inf.append("EDU_MBR_HSTRY_TB, ");
	    	
	    	//mbr_edu_trgt_tb 자동 이수완료 처리
	    	EduCenterMemberVO eduCenterMemberVO = new EduCenterMemberVO();
	    	eduCenterMemberVO.setMBR_ID(loginVO.getMBR_ID());
	    	eduCenterMemberVO.setCRS_SN(CRS_SN);
	    	eduCenterMemberVO.setHMBR_SN(HMBR_SN);
	    	eduCenterMemberService.set_mbr_edu_trgt_mod(eduCenterMemberVO);
	    	
	    	//카카오 알림톡 발송
	    	EduMemberVO mEduMemberVO = new EduMemberVO();
	    	mEduMemberVO.setMBR_ID(loginVO.getMBR_ID());
			mEduMemberVO = eduMemberService.get_edu_member_info(mEduMemberVO);
			
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(CRS_SN);
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			
		    EduCertificateVO reEduCertificateVO = new EduCertificateVO();
			reEduCertificateVO.setCRS_SN(CRS_SN);
			reEduCertificateVO.setHMBR_SN(HMBR_SN);
			reEduCertificateVO.setMBR_ID(mEduMemberVO.getMBR_ID());
			EduCertificateVO reEduExistInfo = eduCertificateService.get_edu_certificate_info(reEduCertificateVO);
			String eduStrDt = eduCurriculumVO.getCRS_STR_DT(); 
			String eduEndDt = eduCurriculumVO.getCRS_END_DT(); 
			KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
			//(알림톡필수)
			String allEduUrl = "";
			String complEdycIssuNo = reEduExistInfo.getCRTF_CD();
			kakaoAlimTalkVO.setNTCN_TRSM_TEL(mEduMemberVO.getMBR_HP());//연락처
			allEduUrl += complEdycIssuNo + "&&";//이수증발급번호
			allEduUrl += mEduMemberVO.getMBR_NM() + "&&";//성명
			allEduUrl += mEduMemberVO.getMBR_BIRTH() + "&&";//생년월일
			allEduUrl += mEduMemberVO.getMBR_ADDR1() + "&&";//주소
			if(eduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")){ // 신규재개자 전문교육
				allEduUrl += eduStrDt.replace(".0", "") + "&&";//교육일시
				allEduUrl += eduEndDt.replace(".0", "") + "&&";//교육일시
				kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000007");//이수증발급
			} else {
				allEduUrl += eduStrDt.replace(".0", "") + " ~ " + eduEndDt.replace(".0", "") + "&&";//교육일시
				if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010300")){ // 낚시어선 전문교육
					kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000005");//이수증발급
				} else if(eduCurriculumVO.getCRS_MBR_CD().equals("CIDN010200")){ // 낚시터 전문교육
					kakaoAlimTalkVO.setNTCN_TRSM_TEMPLATE_ID("KKO_000006");//이수증발급
				}
			}
			allEduUrl += eduCurriculumVO.getCRS_NM();
			kakaoAlimTalkVO.setNTCN_TRSM_EDU_URL(allEduUrl);//이수증발급
			kakaoAlimTalkVO.setNTCN_TRSM_EDU_CD(eduCurriculumVO.getCRS_MBR_CD());//이수증발급
			kakaoAlimTalkVO.setNTCN_TRSM_ASYNC("T");//동기화 전송 여부(true:비동기,false:동기[기본값])
			kakaoAlimTalkVO.setREG_ID(loginVO.getMBR_ID());
//			JSONObject result = kakaoAlimTalkService.sendMessage(kakaoAlimTalkVO,propertiesService,codeSetService,smsManagerService);
			kakaoAlimTalkService.set_kakao_alimtalk(kakaoAlimTalkVO);
			//End of 카카오 알림톡 발송
	    	
	    	log_dscrp.append("-자동 이수완료 처리]");
	    	LOGGER.debug("설문조사 참여 후 자동 이수완료 처리");
	    	tbl_inf.append("MBR_EDU_TRGT_TB");
	    	
	    	Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "설문이 등록되었습니다 감사합니다.");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			
    	} catch (Exception e){
    		log_dscrp.append("[온라인 교육 설문조사 참여여부 업데이트 실패]");
    		tbl_inf.append("EDU_MBR_HSTRY_TB, MBR_EDU_TRGT_TB");
    		
    	}
    	
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(eduMyHistoryVO));
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
    	
		if(isTempMemberLogin) {//외부링크 키 검증을 통한 처리
			return returnUrl;
		} else {
			return "redirect:/educenter/mbrhstry/list.do";	
		}
    	
    }
    /******************************************************************/
    model.addAttribute("msg", "설문이 등록되었습니다 감사합니다.");
    model.addAttribute("url", "/index.do");
    
    return "naksinuri_original/naksinuri/survey/message";
  }

  @RequestMapping({"/survey/survey/m/answer.do"})
  public String muser_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("surveyVO") SurveyVO surveyVO, 
		  BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
	  
    String[] arrStr = request.getParameterValues("anscount");
    String[] svq_type = request.getParameterValues("svq_type");
    String[] sqi_count = request.getParameterValues("sqi_count");
    String[] mxcnt = request.getParameterValues("mxcnt");
    String[] sqq = request.getParameterValues("sq_id");
    
    String CRS_SN = request.getParameter("CRS_SN")==null?"":request.getParameter("CRS_SN");
    String HMBR_SN = request.getParameter("HMBR_SN")==null?"":request.getParameter("HMBR_SN");
   
    if(CRS_SN!=null && CRS_SN.length()!=0 && HMBR_SN!=null && HMBR_SN.length()!=0){
    	surveyVO.setASI_CRS_SN(request.getParameter("CRS_SN"));
    	surveyVO.setASI_HMBR_SN(request.getParameter("HMBR_SN"));
    	surveyVO.setHMBR_SN(HMBR_SN);	//이수내역번호
		surveyVO.setETC_1(CRS_SN);		//교육번호
    }
    surveyVO.setSva_ip(getClientIpAddr(request));
    surveyVO.setSva_mb_ip(getClientIpAddr(request));
    SurveyVO used = this.service.check_survey_by_ip(surveyVO);
    if (used.getUsed() > 0) {
    	/*
         * 낚시누리 요청으로 중복아이피 차단 해제 - 2019.06.18 
      model.addAttribute("msg", "이미 설문에 참여하셨습니다.");
      model.addAttribute("url", "/index.do");

      return "naksinuri_original/naksinuri/survey/message";
      */
    	
    	//ip, hmbr_sn, crs_sn 값이 다 있으면 막음
    	Map<String, Object> postMap = new HashMap<String,Object>();
		postMap.put("message", "이미 설문에 참여하셨습니다.");
		redirectAttributes.addFlashAttribute("alert_data",postMap);
    	
    	return "redirect:/educenter/m/mbrhstry/list.do";
    }
    this.service.survey_insert_answer_info(surveyVO);

    SurveyVO ansno = this.service.survey_answfind(surveyVO);
    surveyVO.setAsi_id(ansno.getAsi_id());

    //회원이면 ETC_2에 id넣기
    HttpSession session = request.getSession();
	LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
		surveyVO.setETC_2(loginVO.getMBR_ID());
	}
    
    for (int i = 0; i < arrStr.length; i++) {
      surveyVO.setSvq_type(svq_type[i]);
      surveyVO.setSq_id(sqq[i]);
      if (surveyVO.getSvq_type().equals("O")) {
        for (int j = 0; j < sqi_count.length; j++) {
          int mx = Integer.parseInt(mxcnt[i]);

          if (mx == 1) {
            String[] sva_txt1 = request.getParameterValues(new StringBuilder().append("sva_etc").append(arrStr[i]).append("").toString());
            for (int k = 0; k < sva_txt1.length; k++) {
              if (sva_txt1[k] != null) {
                surveyVO.setSva_txt(sva_txt1[k]);
              }
            }
            String sqi_id = request.getParameter(new StringBuilder().append("sqi_id").append(sqi_count[j]).append("").toString());
            String sq_num = request.getParameter(new StringBuilder().append("sq_num").append(sqi_count[j]).append("").toString());
            surveyVO.setSqi_id(sqi_id);
            if ((sqi_id != null) && (surveyVO.getSq_id().equals(sq_num))) {
              surveyVO.setSq_id(sq_num);
              this.service.survey_insert_answer(surveyVO);
            }
          } else {
            String[] sqi_id = request.getParameterValues(new StringBuilder().append("sqi_id").append(sqi_count[j]).append("").toString());
            String sq_num = request.getParameter(new StringBuilder().append("sq_num").append(sqi_count[j]).append("").toString());
            if ((sqi_id != null) && (surveyVO.getSq_id().equals(sq_num))) {
              surveyVO.setSq_id(sq_num);
              for (int m = 0; m < sqi_id.length; m++) {
                String sva_txt = request.getParameter(new StringBuilder().append("sva_etc").append(arrStr[i]).append("_").append(sqi_count[m]).append("").toString());
                surveyVO.setSva_txt(sva_txt);
                surveyVO.setSqi_id(sqi_id[m]);
                this.service.survey_insert_answer(surveyVO);
              }
            }
          }
        }
      } else {
        String sva_txt = request.getParameter(new StringBuilder().append("sva_txt").append(arrStr[i]).toString());
        surveyVO.setSqi_id(null);
        surveyVO.setSva_txt(sva_txt);
        this.service.survey_insert_answer(surveyVO);
      }
    }
    
    /******************************************************************/
    //온라인교육 설문이면! 
    SurveyVO info = this.service.select_survey(surveyVO);
    if(info.getSV_LC().equals("online_edu")){
    	
    	EduMyHistoryVO eduMyHistoryVO = new EduMyHistoryVO();
    	eduMyHistoryVO.setHMBR_SV_ST("1");	//설문조사 참여:1, 미참여:0
    	eduMyHistoryVO.setLRNNG_ST("2");	//대기:0, 가이수:1, 이수완료:2, 취소:3, 보류:4, 이수취소:5
    	eduMyHistoryVO.setHMBR_SN(HMBR_SN);
    	eduMyHistoryVO.setCRS_SN(CRS_SN);
    	eduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
    	eduMyHistoryVO.setMBR_ID(loginVO.getMBR_ID());
    	
    	//설문조사 참여여부 업데이트
    	eduMyHistoryService.set_edu_mbrhstry_mod(eduMyHistoryVO);
    	
    	Map<String, Object> postMap = new HashMap<String,Object>();
		postMap.put("message", "설문이 등록되었습니다 감사합니다.");
		redirectAttributes.addFlashAttribute("alert_data",postMap);
    	
    	return "redirect:/educenter/m/mbrhstry/list.do";
    }
    /******************************************************************/

    model.addAttribute("msg", "설문이 등록되었습니다 감사합니다.");
    model.addAttribute("url", "/index.do");

    return "naksinuri_original/naksinuri/survey/message";
  }

  @RequestMapping({"/sosig/lab/list.do"})
  public String lab_list(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - lab_list : 낚시정보 - 낚시연구소 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setBo_name("낚시연구소");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("lab");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("fishing_info");
    staticVO.setCategory_group_name("낚시정보");
    staticVO.setPath_type("web");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    boardVO.setPageUnit(10);
    boardVO.setPageInfo(model);

    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("lab");

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    List list = this.service.select_lab_list(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));

    }

    if (noticlist.size() > 0) {
      model.addAttribute("noticlist", noticlist);
    }

    return "naksinuri_original/naksinuri/sosig/lab_list";
  }

  @RequestMapping({"/sosig/lab/m/list.do"})
  public String lab_list_mobile(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksinuriController - lab_list_mobile : 낚시정보(모바일) - 낚시연구소 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(new StringBuilder().append(key).append(" = ").append(eachdata).toString());
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setBo_name("낚시연구소");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("lab");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("fishing_info");
    staticVO.setCategory_group_name("낚시정보");
    staticVO.setPath_type("mobile");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    boardVO.setPageUnit(10);
    boardVO.setPageInfo(model);

    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("lab");

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    List list = this.service.select_lab_list(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));

    }
    if (noticlist.size() > 0) {
      model.addAttribute("noticlist", noticlist);
    }

    return "naksinuri_original/naksinuri/sosig/m/lab_list";
  }

  @RequestMapping(value={"/promotion/plocation.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public List<NaksinuriGoneVO> plocation_data(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, ModelMap model) throws Exception {
    GoneVO.setPageSize(3);
    String category = "1";
    GoneVO.setPageInfo(model);
    GoneVO.setX_category(category);
    List plodata = this.service_gone.getListGone(GoneVO);
    if (plodata.size() > 0)
      GoneVO.setTotalPage(((NaksinuriGoneVO)plodata.get(0)).getTot_cnt());
    else {
      GoneVO.setTotalPage(0);
    }

    model.addAttribute("sido", GoneVO.getSido());
    model.addAttribute("gugun", GoneVO.getGugun());

    if (plodata.size() > 0) {
      model.addAttribute("select_list", plodata);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)plodata.get(0)).getTot_cnt()));

      int cnt = ((NaksinuriGoneVO)plodata.get(0)).getTot_cnt();
      int PageUnit = 10;
      int pagesize = Math.round(cnt / PageUnit) + 1;
      model.addAttribute("pagesize", Integer.valueOf(pagesize));
    }

    LOGGER.debug(new StringBuilder().append("결과물 :  ").append(plodata).toString());

    return plodata;
  }
  @RequestMapping(value={"/promotion/llocation.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public List<NaksinuriGoneVO> llocation_data(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, ModelMap model) throws Exception {
    GoneVO.setPageSize(3);
    String category = "2";
    GoneVO.setPageInfo(model);
    GoneVO.setX_category(category);
    List plodata = this.service_gone.getListGone(GoneVO);
    if (plodata.size() > 0)
      GoneVO.setTotalPage(((NaksinuriGoneVO)plodata.get(0)).getTot_cnt());
    else {
      GoneVO.setTotalPage(0);
    }

    model.addAttribute("sido", GoneVO.getSido());
    model.addAttribute("gugun", GoneVO.getGugun());

    if (plodata.size() > 0) {
      model.addAttribute("select_list", plodata);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)plodata.get(0)).getTot_cnt()));

      int cnt = ((NaksinuriGoneVO)plodata.get(0)).getTot_cnt();
      int PageUnit = 10;
      int pagesize = Math.round(cnt / PageUnit) + 1;
      model.addAttribute("pagesize", Integer.valueOf(pagesize));
    }

    LOGGER.debug(new StringBuilder().append("결과물 :  ").append(plodata).toString());

    return plodata;
  }
  @RequestMapping(value={"/promotion/m/plocation.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public List<NaksinuriGoneVO> mobile_plocation_data(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, ModelMap model) throws Exception {
    String category = "1";
    GoneVO.setX_category(category);
    List plodata = this.service_gone.getListGonemobile(GoneVO);

    model.addAttribute("sido", GoneVO.getSido());
    model.addAttribute("gugun", GoneVO.getGugun());

    if (plodata.size() > 0) {
      model.addAttribute("select_list", plodata);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)plodata.get(0)).getTot_cnt()));
    }

    LOGGER.debug(new StringBuilder().append("결과물 :  ").append(plodata).toString());

    return plodata;
  }
  @RequestMapping(value={"/promotion/m/llocation.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public List<NaksinuriGoneVO> mobile_llocation_data(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, ModelMap model) throws Exception {
    String category = "2";
    GoneVO.setX_category(category);
    List plodata = this.service_gone.getListGonemobile(GoneVO);

    model.addAttribute("sido", GoneVO.getSido());
    model.addAttribute("gugun", GoneVO.getGugun());

    LOGGER.debug(new StringBuilder().append("결과물 :  ").append(plodata).toString());

    return plodata;
  }

  @RequestMapping({"/sosig/lab/view.do"})
  public String lab_findCorp(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    boardVO.setBo_type("lab");

    this.service.view_update(boardVO);

    BoardVO info = this.service.board_findCorp(boardVO);
    model.addAttribute("info", info);

    if (request.getParameter("co_comment") != null) {
      String co_name = request.getParameter("co_name");
      String co_pass = request.getParameter("co_pass");
      String co_comment = request.getParameter("co_comment");

      if (co_name != null) {
        co_name = mEgovStringUtil.getHtmlStrCnvr(co_name);
        boardVO.setCo_name(co_name);
      }
      if (co_pass != null) {
    	  co_pass = mEgovStringUtil.getHtmlStrCnvr(co_pass);
        boardVO.setCo_pass(co_pass);
      }

      if (co_comment != null) {
        co_comment = mEgovStringUtil.getHtmlStrCnvr(co_comment);
        boardVO.setCo_comment(co_comment);
      }
      this.service.co_insert(boardVO);
    }

    List list = this.service.select_list_comment(boardVO);

    if (list.size() > 0) {
      model.addAttribute("comment_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));
    }

    List post_list = this.service.select_list_post(boardVO);
    if (post_list.size() > 0) {
      model.addAttribute("post_list", post_list);
    }

    List side_list = this.service.select_list_bobo(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);

    if (side_list.size() > 0)
      boardVO.setTotalPage(((BoardVO)side_list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (side_list.size() > 0) {
      model.addAttribute("select_list", side_list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)side_list.get(0)).getTot_cnt()));

    }

    if (noticlist.size() > 0) {
      model.addAttribute("noticlist", noticlist);
    }

    BoardVO next = this.service.select_next(boardVO);
    BoardVO prev = this.service.select_prev(boardVO);
    if (next != null) {
      model.addAttribute("next", next);
    }
    if (prev != null) {
      model.addAttribute("prev", prev);
    }
    return "naksinuri_original/naksinuri/sosig/lab_view";
  }
  
  @RequestMapping({"policy/privacy{addWebLink}.do"})
  public String privacy_year(@PathVariable("addWebLink") String addWebLink){
	  
    return "naksinuri_original/naksinuri/policy/privacy" + addWebLink;
  }
  @RequestMapping({"policy/m/privacy{addWebLink}.do"})
  public String privacy_mobile_year(@PathVariable("addWebLink") String addWebLink){
	  
    return "naksinuri_original/naksinuri/policy/m/privacy" + addWebLink;
  }

  @RequestMapping({"policy/copyright.do"})
  public String copyright()
  {
    return "naksinuri_original/naksinuri/policy/copyright";
  }

  @RequestMapping({"policy/m/copyright.do"})
  public String copyright_mobile()
  {
    return "naksinuri_original/naksinuri/policy/m/copyright";
  }

  /*@RequestMapping({"policy/customer_sound_list.do"})
  public String customer_sound_list(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	  policyVO.setPageInfo(model);
	  policyVO.setPageUnit(policyVO.getPageUnit());

	  List list = service.select_voc_list(policyVO);

	  if (list.size() > 0)
		  policyVO.setTotalPage(((NaksinuriPolicyVO)list.get(0)).getTot_cnt());
	  else {
		  policyVO.setTotalPage(0);
	  }
	  if (list.size() > 0) {
		  model.addAttribute("voc_list", list);
		  model.addAttribute("select_total", Integer.valueOf(((NaksinuriPolicyVO)list.get(0)).getTot_cnt()));
	  }

	  return "naksinuri_original/naksinuri/policy/customer_sound_list";
  }

  @RequestMapping({"policy/customer_sound_write.do"})
  public String customer_sound_write(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
		  throws Exception
  {
	  String co_nm = request.getParameter("co_nm");
	  model.addAttribute("co_nm", co_nm);

	  String siteKey = "6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-";
	  model.addAttribute("siteKey", siteKey);
	  
	  return "naksinuri_original/naksinuri/policy/customer_sound_write";
  }
  

	
	@RequestMapping({"policy/customer_sound_view.do"})
	  public String customer_sound_view(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
		NaksinuriPolicyVO info = service.select_voc_view(policyVO);
		model.addAttribute("info", info);
	
		 return "naksinuri_original/naksinuri/policy/customer_sound_view";
	  }

	@RequestMapping({ "policy/m/customer_sound_list.do" })
	public String customer_sound_mobile(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		policyVO.setPageInfo(model);
		policyVO.setPageUnit(policyVO.getPageUnit());

		List list = service.select_voc_list(policyVO);

		if (list.size() > 0)
			policyVO.setTotalPage(((NaksinuriPolicyVO) list.get(0)).getTot_cnt());
		else {
			policyVO.setTotalPage(0);
		}
		if (list.size() > 0) {
			model.addAttribute("voc_list", list);
			model.addAttribute("select_total", Integer.valueOf(((NaksinuriPolicyVO) list.get(0)).getTot_cnt()));
		}

		return "naksinuri_original/naksinuri/policy/m/customer_sound_list";
	}
	@RequestMapping({"policy/m/customer_sound_write.do"})
	  public String customer_sound_write_mobile(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
			  throws Exception
	  {
		  String co_nm = request.getParameter("co_nm");
		  model.addAttribute("co_nm", co_nm);

		  String siteKey = "6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-";
		  model.addAttribute("siteKey", siteKey);
		  
		  return "naksinuri_original/naksinuri/policy/m/customer_sound_write";
	  }
	
	@RequestMapping({"policy/m/customer_sound_view.do"})
	  public String customer_sound_view_mobile(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
		NaksinuriPolicyVO info = service.select_voc_view(policyVO);
		model.addAttribute("info", info);
	
		 return "naksinuri_original/naksinuri/policy/m/customer_sound_view";
	  }
	*/
  
//[보안점검수정][START]####################################################
  /*
  @RequestMapping({"/info/fishjobreport/write.do"})
  public String report_write(@ModelAttribute("reportVO") ReportVO reportVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String co_nm = request.getParameter("co_nm");
    model.addAttribute("co_nm", co_nm);

    String siteKey = "6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-";
    model.addAttribute("siteKey", siteKey);

    return "naksinuri_original/naksinuri/info/report_write";
  }
  */
//[보안점검수정][END]####################################################

  @RequestMapping({"/info/fishjobreport/m/write.do"})
  public String mobile_report_write(@ModelAttribute("reportVO") ReportVO reportVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String co_nm = request.getParameter("co_nm");
    model.addAttribute("co_nm", co_nm);

    String siteKey = "6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-";
    model.addAttribute("siteKey", siteKey);

    return "naksinuri_original/naksinuri/info/m/report_write";
  }

  @RequestMapping({"/info/fishjobreport/insert_data.do"})
  public String report_insert(@ModelAttribute("reportVO") ReportVO reportVO, MultipartHttpServletRequest multiRequest, 
		  HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
	 
	  EgovStringUtil EgovStringUtil = new EgovStringUtil();
	  reportVO.setReport_writer(EgovStringUtil.getHtmlStrCnvr(reportVO.getReport_writer())); 
	  reportVO.setReport_subject(EgovStringUtil.getHtmlStrCnvr(reportVO.getReport_subject()));	  
	  reportVO.setReport_cont(EgovStringUtil.getHtmlStrCnvr(reportVO.getReport_cont()));	  
	  
	  String _atchFileId = "";
	  List _result3 = null;
	  Map files = multiRequest.getFileMap();
	  if (!files.isEmpty()) {
		  _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
		  _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);
	  }
	  reportVO.setReport_atch_file(_atchFileId);
	  this.service_report.insert_report(reportVO);

    return "redirect:/info/fishjob/list.do";
  }

//[보안점검수정][START]####################################################
  /*
  @RequestMapping({"/info/fishjob/review.do"})
  public String go_review(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String rev_sn = request.getParameter("rev_sn");
    if (rev_sn != "") {
      FishjobReviewVO info = this.service_review.getrev_info(reviewVO);
      model.addAttribute("info", info);
    }

    String nak_id = request.getParameter("nak_id");
    model.addAttribute("nak_id", nak_id);
    String siteKey = "6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-";
    model.addAttribute("siteKey", siteKey);

    return "naksinuri_original/naksinuri/info/fishjob_review";
  }
  */
//[보안점검수정][END]####################################################
  
  @RequestMapping({"/info/fishjob/m/review.do"})
  public String go_review_mobile(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    String rev_sn = request.getParameter("rev_sn");
    if (rev_sn != "") {
      FishjobReviewVO info = this.service_review.getrev_info(reviewVO);
      model.addAttribute("info", info);
    }

    String nak_id = request.getParameter("nak_id");
    model.addAttribute("nak_id", nak_id);
    String siteKey = "6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-";
    model.addAttribute("siteKey", siteKey);

    return "naksinuri_original/naksinuri/info/m/fishjob_review";
  }

  @RequestMapping({"/info/review/update_info.do"})
  public String rev_update_info(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String rev_pass = request.getParameter("rev_pass");
    String rev_sn = request.getParameter("rev_sn1");
    reviewVO.setRev_sn(rev_sn);
    FishjobReviewVO getpass = this.service_review.revpass_find(reviewVO);

    String getpass_result = getpass.getRev_pass();

    if (!getpass_result.equals(rev_pass)) {
      model.addAttribute("fail", "1");
    }

    model.addAttribute("rev_sn", rev_sn);

    reviewVO.setNak_id(request.getParameter("nak_id"));
    model.addAttribute("nak_id", reviewVO.getNak_id());
    return "naksinuri_original/naksinuri/info/rev_udt";
  }

  @RequestMapping({"/info/review/m/update_info.do"})
  public String rev_update_infom(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String rev_pass = request.getParameter("rev_pass");
    String rev_sn = request.getParameter("rev_sn1");
    reviewVO.setRev_sn(rev_sn);
    FishjobReviewVO getpass = this.service_review.revpass_find(reviewVO);
    String getpass_result = getpass.getRev_pass();

    if (!getpass_result.equals(rev_pass)) {
      model.addAttribute("fail", "1");
    }

    model.addAttribute("rev_sn", rev_sn);

    reviewVO.setNak_id(request.getParameter("nak_id"));
    model.addAttribute("nak_id", reviewVO.getNak_id());
    return "naksinuri_original/naksinuri/info/m/rev_udt";
  }

  @RequestMapping({"/info/review/update_data.do"})
  public String update_review(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	EgovStringUtil EgovStringUtil = new EgovStringUtil();
	reviewVO.setRev_title(EgovStringUtil.getHtmlStrCnvr(reviewVO.getRev_title())); 
	reviewVO.setRev_cont(EgovStringUtil.getHtmlStrCnvr(reviewVO.getRev_cont())); 
    this.service_review.update_review(reviewVO);
    reviewVO.setNak_id(request.getParameter("nak_id"));
    model.addAttribute("nak_id", reviewVO.getNak_id());
    return "naksinuri_original/naksinuri/info/review_return";
  }

  @RequestMapping({"/info/review/m/update_data.do"})
  public String update_reviewm(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception {
	EgovStringUtil EgovStringUtil = new EgovStringUtil();
	reviewVO.setRev_title(EgovStringUtil.getHtmlStrCnvr(reviewVO.getRev_title())); 
	reviewVO.setRev_cont(EgovStringUtil.getHtmlStrCnvr(reviewVO.getRev_cont())); 
    this.service_review.update_review(reviewVO);
    reviewVO.setNak_id(request.getParameter("nak_id"));
    model.addAttribute("nak_id", reviewVO.getNak_id());
    return "naksinuri_original/naksinuri/info/m/review_return";
  }

  @RequestMapping({"/info/review/insert_data.do"})
  public String insert_review(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	EgovStringUtil EgovStringUtil = new EgovStringUtil();
	reviewVO.setRev_title(EgovStringUtil.getHtmlStrCnvr(reviewVO.getRev_title())); 
	reviewVO.setRev_cont(EgovStringUtil.getHtmlStrCnvr(reviewVO.getRev_cont())); 
    this.service_review.insert_review(reviewVO);
    reviewVO.setNak_id(request.getParameter("nak_id"));
    model.addAttribute("nak_id", reviewVO.getNak_id());
    return "naksinuri_original/naksinuri/info/review_return";
  }

  @RequestMapping({"/info/review/m/insert_data.do"})
  public String insert_reviewm(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	EgovStringUtil EgovStringUtil = new EgovStringUtil();
	reviewVO.setRev_title(EgovStringUtil.getHtmlStrCnvr(reviewVO.getRev_title())); 
	reviewVO.setRev_cont(EgovStringUtil.getHtmlStrCnvr(reviewVO.getRev_cont()));	  
    this.service_review.insert_review(reviewVO);
    reviewVO.setNak_id(request.getParameter("nak_id"));
    model.addAttribute("nak_id", reviewVO.getNak_id());
    return "naksinuri_original/naksinuri/info/m/review_return";
  }

  @RequestMapping({"/info/review/delete_data.do"})
  public String delete_review(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    String rev_pass = request.getParameter("rev_pass");
    String rev_sn = request.getParameter("rev_sn1");
    reviewVO.setRev_sn(rev_sn);

    FishjobReviewVO getpass = this.service_review.revpass_find(reviewVO);
    String getpass_result = getpass.getRev_pass();

    if (!getpass_result.equals(rev_pass))
      model.addAttribute("fail", "1");
    else {
      this.service_review.delete_review(reviewVO);
    }

    reviewVO.setNak_id(request.getParameter("nak_id"));
    model.addAttribute("nak_id", reviewVO.getNak_id());
    return "naksinuri_original/naksinuri/info/rev_del";
  }

  @RequestMapping({"/info/review/m/delete_data.do"})
  public String delete_reviewm(@ModelAttribute("reviewVO") FishjobReviewVO reviewVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    String rev_pass = request.getParameter("rev_pass");
    String rev_sn = request.getParameter("rev_sn1");
    reviewVO.setRev_sn(rev_sn);

    FishjobReviewVO getpass = this.service_review.revpass_find(reviewVO);
    String getpass_result = getpass.getRev_pass();

    if (!getpass_result.equals(rev_pass))
      model.addAttribute("fail", "1");
    else {
      this.service_review.delete_review(reviewVO);
    }

    reviewVO.setNak_id(request.getParameter("nak_id"));
    model.addAttribute("nak_id", reviewVO.getNak_id());
    return "naksinuri_original/naksinuri/info/m/rev_del";
  }

  @RequestMapping({"policy/agree.do"})
  public String agree()
  {
    return "naksinuri_original/naksinuri/policy/agree";
  }

  @RequestMapping({"policy/m/agree.do"})
  public String agree_mobile()
  {
    return "naksinuri_original/naksinuri/policy/m/agree";
  }

  @RequestMapping({"/index1.do"})
  public String index1() {
    return "naksinuri_original/main/index2";
  }

  @RequestMapping({"/index2.do"})
  public String index2() {
    return "naksinuri_original/main/index3";
  }

  @RequestMapping({"/index3.do"})
  public String index3() {
    return "naksinuri_original/main/index4";
  }

  @RequestMapping({"/index4.do"})
  public String index4() {
    return "naksinuri_original/main/index5";
  }

  @RequestMapping({"/info/fishjob/result.do"}) 
  public String industry_result(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/info/fishjob_result";
  }  
  @RequestMapping({"/info/m/fishjob/result.do"}) 
  public String mindustry_result(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/info/m/fishjob_result";
  }  
  @RequestMapping({"/info/industry/result.do"}) 
  public String industry_result(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/info/industry_result";
  }
  @RequestMapping({"/info/m/industry/result.do"}) 
  public String mindustry_result(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/info/m/industry_result";
  }
  @RequestMapping({"/info/industry/err.do"}) 
  public String rerr(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/info/industry_err";
  }  
  @RequestMapping({"/info/industry/m/err.do"}) 
  public String mrerr(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/info/m/industry_err";
  }  


  @RequestMapping({"/info/admin/fishingAM.do"})
  public String fishingAM_admin(@ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    naksinuriVO.setPageInfo(model);
    naksinuriVO.setPageUnit(naksinuriVO.getPageUnit());

    List list = this.service.getListNaksinuri_admin_preview_fish_list(naksinuriVO);

    if (list.size() > 0)
      naksinuriVO.setTotalPage(((NaksinuriVO)list.get(0)).getTot_cnt());
    else {
      naksinuriVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
      model.addAttribute("admin_total", Integer.valueOf(((NaksinuriVO)list.get(0)).getTot_cnt()));
    }
    model.addAttribute("searchDate1", request.getParameter("searchDate1"));
    model.addAttribute("searchDate2", request.getParameter("searchDate2"));
    model.addAttribute("searchType", request.getParameter("searchType"));
    model.addAttribute("searchText", request.getParameter("searchText"));
    return "naksinuri_original/naksinuri/admin/document_fishingAM";
  }
  @RequestMapping({"/info/admin/sanupAM.do"})
  public String sanupAM_admin(@ModelAttribute("naksinuriVO") NaksinuriSanupVO naksinuriSanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
	  naksinuriSanupVO.setPageInfo(model);
	  naksinuriSanupVO.setPageUnit(naksinuriSanupVO.getPageUnit());

    List list = this.service.getListNaksinuri_admin_preview_ind_list(naksinuriSanupVO); 

    if (list.size() > 0) {
      model.addAttribute("admin_list", list);
    }
    return "naksinuri_original/naksinuri/admin/document_industryAM"; 
  }

  
  @RequestMapping({"/info/admin/indPreview.do"})
  public String insertCorpSanupPreview(SessionStatus status, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
  	  throws Exception
  {
	  	/* 프리뷰 영역 */
	  	LOGGER.debug(">>>>>>>>>>  getListNaksinuri_admin_preview_ind_detail");
	  	NaksinuriSanupVO preview = this.service_sanup.getListNaksinuri_admin_preview_ind_detail(SanupVO);
	  	
	  	LOGGER.debug(">>>>>>>>>>  ind_fsimg_Preview");
	    List plist = this.service_sanup.ind_fsimg_Preview(SanupVO);

	  	NaksinuriSanupVO getpmimg = preview;
	  	getpmimg.setSan_img(preview.getSan_img());		
	  	
	  	LOGGER.debug(">>>>>>>>>>  adminPreview_mimg");
	  	NaksinuriSanupVO pmimg = this.service.adminPreview_mimg(getpmimg);
	    
	    model.addAttribute("preview", preview);
	    model.addAttribute("pmimg", pmimg);
	    model.addAttribute("psimglist", plist);

	  	/* 리얼영역 영역 */
		String preg_pid = preview.getPreg_pid();
		String San_sn = preview.getSan_sn();
		if(preg_pid != null  && preg_pid.length()<9) {
			NaksinuriSanupVO realdata = preview;
			realdata.setSan_sn(preg_pid);
			
			LOGGER.debug(">>>>>>>>>>  getListNaksinuri_admin_ind_detail");
		  	NaksinuriSanupVO info= this.service_sanup.getListNaksinuri_admin_ind_detail(realdata);
		  	
		  	NaksinuriSanupVO getorgpmimg = info;
		  	getorgpmimg.setSan_img(info.getSan_img());	
		  	
		  	LOGGER.debug(">>>>>>>>>>  adminPreview_mimg");
		  	NaksinuriSanupVO porgmimg = this.service.adminPreview_mimg(getorgpmimg);
		  	model.addAttribute("porgmimg", porgmimg);

		  	LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			LOGGER.debug(info.getSan_img());
			LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		}	    
	    
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("preview.getSan_sn = "+San_sn);
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		NaksinuriSanupVO previewfileid = preview;
		previewfileid.setOsan_sn(SanupVO.getSan_sn());      
		previewfileid.setSan_sn(San_sn);  		

	  	List<NaksinuriSanupVO> prelist = this.service.Preview_searchFile2(previewfileid);
	  	model.addAttribute("prefilelist", prelist);
		
		return "/naksinuri_original/naksinuri/admin/preview_ind";
  }

  
   
  @RequestMapping({"/info/admin/indPreviewUpdate.do"})
  public String indPreviewUpdate(SessionStatus status, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
  	  throws Exception
  {
	  	String param_san_sn = SanupVO.getSan_sn();
	  	
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰 영역 호출");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		/* 프리뷰 영역 */
	    NaksinuriSanupVO preview = this.service_sanup.getListNaksinuri_admin_preview_ind_detail(SanupVO);
	    // SELECT * FROM NAKSINURISANUP_PREVIEW WHERE PREG_STAT = 'r' and san_sn='131' order by san_sn
	    // preg_stat = r 조건 없앤다.

	      
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("리얼 영역 호출");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

	  	/* 리얼영역 영역 */
		String preg_pid = preview.getPreg_pid();
		String OSan_img = null;
		String OSan_simg = null;
		String nAtch_fid = null;	  	

		if(preg_pid != null  && preg_pid.length()<9) {
			NaksinuriSanupVO realdata = preview;
			realdata.setSan_sn(preg_pid);
		  	NaksinuriSanupVO info= this.service_sanup.getListNaksinuri_admin_ind_detail(realdata);
		  	
			OSan_img = info.getSan_img();
			OSan_simg = info.getSan_simg();
			
			nAtch_fid = info.getSan_simg();

		  	LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			LOGGER.debug(info.getSan_img());
			LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		}

	  	//model.addAttribute("preview", preview);
	    //model.addAttribute("psimglist", plist);
	    
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰데이터 호출");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

	  	NaksinuriSanupVO previewsave = preview;
		previewsave.setSan_divsion(preview.getSan_divsion());
		previewsave.setSan_aag(preview.getSan_aag());
		
		previewsave.setSan_zogu(preview.getSan_zogu());
		previewsave.setSan_media(preview.getSan_media());
		previewsave.setSan_sell(preview.getSan_sell());
		previewsave.setSan_chool(preview.getSan_chool());
		previewsave.setSan_inprov(preview.getSan_inprov());
		
		previewsave.setSan_buisnessman(preview.getSan_buisnessman());
		previewsave.setSan_name(preview.getSan_name());
		previewsave.setSan_tel(preview.getSan_tel());
		previewsave.setSan_content(preview.getSan_content());

		String San_img = preview.getSan_img();
		if(San_img != null) {
			previewsave.setSan_img(San_img);
		}
		else if(OSan_img != null) {
			previewsave.setSan_img(OSan_img);
		} 


		String San_simg = preview.getSan_simg();
		if(San_simg != null) {
			previewsave.setSan_simg(San_simg);
		}
		else if(OSan_simg != null) {
			previewsave.setSan_simg(OSan_simg);
		} 
		
		previewsave.setSan_address(preview.getSan_address());
		previewsave.setSan_address1(preview.getSan_address1());
		previewsave.setSan_address2(preview.getSan_address2());
		previewsave.setSan_address3(preview.getSan_address3());
		
		previewsave.setSan_item(preview.getSan_item());
		previewsave.setSan_phone(preview.getSan_phone());
		previewsave.setSan_homepage(preview.getSan_homepage());
		previewsave.setSan_latitude(preview.getSan_latitude());
		previewsave.setSan_hardness(preview.getSan_hardness());
		previewsave.setSan_list_yn(preview.getSan_list_yn());
		previewsave.setSan_date(preview.getSan_date());
		previewsave.setSan_update(preview.getSan_update());
		
		previewsave.setSan_tel1(preview.getSan_tel1());
		previewsave.setSan_tel2(preview.getSan_tel2());
		previewsave.setSan_tel3(preview.getSan_tel3());
		previewsave.setSan_phone1(preview.getSan_phone1());
		previewsave.setSan_phone2(preview.getSan_phone2());
		previewsave.setSan_phone3(preview.getSan_phone3());

		previewsave.setSan_trash(preview.getSan_trash());
		previewsave.setPreg_pid(preview.getPreg_pid());
		previewsave.setPreg_sess(preview.getPreg_sess());

		
		String newSansn= "0";
		if(preview.getSan_simg() != null) {
			nAtch_fid = preview.getSan_simg();
		}		
		String Osan_sn = preview.getPreg_pid();	//oid에 해당
		String san_sn = preview.getSan_sn(); //pid에 해당
		String wsess = preview.getPreg_sess(); //pid에 해당
		
		//데이터를 업 한다.
	  	//신규데이터는 int최대치의 가상숫자값을 줬다. 따라서 10자리가 넘으면 신규, 나머지는 업데이트로 판단한다.
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("san_sn 값 설정 Osan_sn = " + Osan_sn);
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		if(Osan_sn.length() > 8) {
			//0을 준 이유는.. 부모값처리를 위해서.. 인서트는 부모값이 없기 때문에.. 0을 줘서, 부모값 처리를 다시한다.
			newSansn = "0";
			this.service.indPreviewSaveI(previewsave);
		} else {
			//부모값을 전달받아, 자체값을 체크한다.  히스토리를 위해..
			newSansn = preview.getPreg_pid();
			previewsave.setSan_status("y");
			this.service.indPreviewSave(previewsave);
		}		

		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰 이미지 호출");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("san_sn = "+san_sn);
		LOGGER.debug("Osan_sn = "+Osan_sn);
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	
		NaksinuriSanupVO previewfileid = preview;
		previewfileid.setOsan_sn(Osan_sn);      
		previewfileid.setSan_sn(param_san_sn);  		

		LOGGER.debug(">>>>>>>>  Preview_searchFile2");
		List<NaksinuriSanupVO> prelist = this.service.Preview_searchFile2(previewfileid);
	  	//model.addAttribute("prefilelist", prelist);	
		//파일을 처리한다.
		
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰이미지 저장 prelist size = " + prelist.size());
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	  	
	  	if(prelist.size()>0) {
			//삭제
			//프리뷰임시테이블(_files_preview)에 저장된 값이 있다면...  lettnfiledetail의 부모값을 삭제한다.
	  		this.service.indfileOrgdelete(previewfileid);  //////////////////////////////  테스트를 위해 임시로 막음..

			NaksinuriSanupVO previewfilesave = preview;
			for(int i=0;i<prelist.size();i++){

				previewfilesave.setSan_sn(newSansn);
				previewfilesave.setAtch_file_id(nAtch_fid);
				previewfilesave.setFile_sn(Integer.toString(i));
				previewfilesave.setFile_stre_cours(prelist.get(i).getP_file_stre_cours());
				previewfilesave.setStre_file_nm(prelist.get(i).getP_stre_file_nm());
				previewfilesave.setOrignl_file_nm(prelist.get(i).getP_orignl_file_nm());
				previewfilesave.setFile_extsn(prelist.get(i).getP_file_extsn());
				previewfilesave.setFile_cn(prelist.get(i).getP_file_cn());
				previewfilesave.setFile_size(prelist.get(i).getP_file_size());
	
				//삽입
				//프리뷰임시테이블(_files_preview)의 값을 호출하였고,  루프중 첫번째값이 원래 파일아이디값이므로, 이를 기반으로  파일아이디값을 통일하고,
				// file_sn을 배열순서대로 0부터 맞춰준다.
				this.service.indfileOrgSave(previewfilesave);
			}
		}
			

	  	NaksinuriSanupVO delpreview = preview;
		delpreview.setOid(Osan_sn);
		delpreview.setPid(san_sn);
		delpreview.setWsess(wsess);
		
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰데이터 정리 Osan_sn , san_sn, wsess " + Osan_sn + ", " + san_sn + ", " + wsess + ", " + param_san_sn);
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//프리뷰데이터를 정리한다.
		//실제삭제가 아니라, 차후 수정을 위해 몇개필드의 갑을 없앤다. 필요한 값은 채워줘야 하므로 업데이트 한다.
		// file_sn을 배열순서대로 0부터 맞춰준다.
		this.service.delindpreviewData(delpreview);
		
		

		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰 파일 삭제");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		//프리뷰파일을 정리한다.
		//히스토리에서 파일내역까지 볼것은 아니므로... 해당 프리뷰의 모든 이미지를  삭제한다. 
		this.service.delindpreviewfile(delpreview);
		
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("자바스크립트 호출");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		NaksinuriSanupVO delpreviewreal = new NaksinuriSanupVO();
		delpreviewreal.setOsan_sn(Osan_sn);
		delpreviewreal.setSan_sn(param_san_sn);
		
		this.service.deleteindPreview(delpreviewreal);

		//return "/naksinuri_original/naksinuri/admin/preview_ind";
		/*
	  	response.setCharacterEncoding("EUC-KR");
	  	PrintWriter writer = response.getWriter();
	  	writer.println("<script type='text/javascript'>");
	  	writer.println("alert('처리되었습니다.');");
	  	writer.println("opener.location.reload();");
	  	writer.println("self.window.close();");
	  	writer.println("</script>");
	  	writer.flush();
	  	*/		
		
		model.addAttribute("message", "처리되었습니다.");
		return "naksinuri_original/naksinuri/alertForOpenWindow";
  }
  
  
  
  @RequestMapping({"/info/admin/fishPreview.do"})
  public String fishPreview_admin(SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, NaksinuriFileVO naksinurifileVO,BindingResult bindingResult, ModelMap model, HttpServletRequest request) 
	throws Exception
  {

		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug(request.getParameter("nak_id"));
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

	  	NaksinuriFileVO pmimg = this.service.adminPreview_mimg(naksinurifileVO);
		NaksinuriVO preview = this.service.getListNaksinuri_admin_preview_fish_detail(naksinuriVO);
		naksinurifileVO.setAtch_filePreview_id(preview.getAtch_filePreview_id());
		
		fishesVO.setFishing_type(preview.getFishing_type());
		List pfishlist = this.fishesService.fishPreview_list(fishesVO);

	  	/* 리얼영역 영역 */
		String preg_pid = preview.getPreg_pid();
		String OCo_mimgsrc = null;
		String OAtch_file_id = null;
	  	
		if(preg_pid != null && preg_pid.length()<9) {
			NaksinuriVO realdata = naksinuriVO;
			realdata.setNak_id(preg_pid);
		  	NaksinuriVO info= this.service.getListNaksinuri_admin_fish_detail(realdata);

			LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			LOGGER.debug("preg_pid.length = "+preg_pid.length());
			LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		  	OCo_mimgsrc = info.getCo_mimgsrc();
			//OAtch_file_id = info.getAtch_file_id();
			
		  	NaksinuriVO getorgpmimg = info;
		  	getorgpmimg.setCo_mimgsrc(OCo_mimgsrc);		
		  	NaksinuriVO porgmimg = this.service.adminPreview_fish_mimg(getorgpmimg);
		  	model.addAttribute("info", info);
		  	model.addAttribute("porgmimg", porgmimg);
		  	
		}		
		
		String[] psido = preview.getCo_addr2_2().split(" ");
		
		model.addAttribute("pfishlist", pfishlist);
		model.addAttribute("preview", preview);
		//model.addAttribute("pfilelist", plist);
		model.addAttribute("pmimg", pmimg);
		model.addAttribute("psido", psido);

		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("getNak_id - "+naksinuriVO.getNak_id());
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("preview getNak_id - "+preview.getNak_id());
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		NaksinuriVO previewfileid = preview;
		previewfileid.setOnak_id(preview.getPreg_pid());      
		previewfileid.setNak_id(preview.getNak_id());       

		List<NaksinuriVO> prelist = this.service.Preview_searchFile(previewfileid);
		model.addAttribute("prefilelist", prelist);
		
		return "/naksinuri_original/naksinuri/admin/preview_fish";
  }
  @RequestMapping({"/info/admin/fishPreviewUpdate.do"})
  public String fishPreviewUpdate(SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, NaksinuriFileVO naksinurifileVO,BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response) 
	throws Exception
  {

		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰 데이터 호출");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		//NaksinuriFileVO pmimg = this.service.adminPreview_mimg(naksinurifileVO);
		NaksinuriVO preview = this.service.getListNaksinuri_admin_preview_fish_detail(naksinuriVO);
		naksinurifileVO.setAtch_filePreview_id(preview.getAtch_filePreview_id());
		
		fishesVO.setFishing_type(preview.getFishing_type());

	  	/* 리얼영역 영역 */
		String preg_pid = preview.getPreg_pid();
		String Nak_id = preview.getNak_id();
		String OCo_mimgsrc = null;
		String OAtch_file_id = null;
		String nAtch_fid = null;
		
		/* *************************************** */
	    NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
	    HttpSession session = request.getSession();    
	    
	    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	    insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
	    insertlogsVO.setUser_id(loginVO.getMBR_ID());
	    insertlogsVO.setActions("낚시터/낚시산업정보 수정(fishPreviewUpdate.do) " + Nak_id + ", " + preg_pid);
	    this.service_logs.insertLogs(insertlogsVO);
	    /* *************************************** */
	  	
		if(preg_pid != null && preg_pid.length()<9) {
			NaksinuriVO realdata = naksinuriVO;
			realdata.setNak_id(preg_pid);
		  	NaksinuriVO info= this.service.getListNaksinuri_admin_fish_detail(realdata);

			LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		  	OCo_mimgsrc = info.getCo_mimgsrc();
			OAtch_file_id = info.getAtch_file_id();
			nAtch_fid = info.getAtch_file_id();
			
		  	NaksinuriVO getorgpmimg = info;
		  	getorgpmimg.setCo_mimgsrc(OCo_mimgsrc);		
		  	NaksinuriVO porgmimg = this.service.adminPreview_fish_mimg(getorgpmimg);
		  	model.addAttribute("info", info);
		  	model.addAttribute("porgmimg", porgmimg);
		  	
		}
		
		
		
		//model.addAttribute("pfishlist", pfishlist);
		//model.addAttribute("preview", preview);
		//model.addAttribute("pfilelist", plist);
		//model.addAttribute("pmimg", pmimg);
		//model.addAttribute("psido", psido);

		NaksinuriVO previewsave = preview;
		previewsave.setFishing_type(preview.getFishing_type());
		previewsave.setCo_nm(preview.getCo_nm());
		previewsave.setCo_ship_num1(preview.getCo_ship_num1());
		previewsave.setCo_ship_num2(preview.getCo_ship_num2());
		previewsave.setCo_info(preview.getCo_info());
		previewsave.setCeo_nm(preview.getCeo_nm());
		previewsave.setCo_phone(preview.getCo_phone());
		previewsave.setCo_phone1(preview.getCo_phone1());
		previewsave.setCo_phone2(preview.getCo_phone2());
		previewsave.setCo_phone3(preview.getCo_phone3());
		previewsave.setCo_hphone(preview.getCo_hphone());
		previewsave.setCo_hphone1(preview.getCo_hphone1());
		previewsave.setCo_hphone2(preview.getCo_hphone2());
		previewsave.setCo_hphone3(preview.getCo_hphone3());
		previewsave.setCo_2hphone(preview.getCo_hphone());
		previewsave.setCo_2hphone1(preview.getCo_2hphone1());
		previewsave.setCo_2hphone2(preview.getCo_2hphone2());
		previewsave.setCo_2hphone3(preview.getCo_2hphone3());

		previewsave.setBo_size(preview.getBo_size());
		previewsave.setBo_spd(preview.getBo_spd());
		previewsave.setBo_psg(preview.getBo_psg());
		previewsave.setCo_web(preview.getCo_web());
		previewsave.setCo_addr(preview.getCo_addr());
		previewsave.setCo_addr_1(preview.getCo_addr_1());
		previewsave.setCo_addr_2(preview.getCo_addr_2());
		previewsave.setCo_addr_3(preview.getCo_addr_3());
		
		previewsave.setCo_fish(preview.getCo_fish());
		previewsave.setCo_sido(preview.getCo_sido());
		previewsave.setCo_addr2(preview.getCo_addr2());
		previewsave.setCo_addr2_1(preview.getCo_addr2_1());
		previewsave.setCo_addr2_2(preview.getCo_addr2_2());
		previewsave.setCo_addr2_3(preview.getCo_addr2_3());
		
		previewsave.setCo_psg(preview.getCo_psg());
		previewsave.setCo_stm(preview.getCo_stm());
		previewsave.setCo_etm(preview.getCo_etm());
		previewsave.setCo_prc(preview.getCo_prc());
		previewsave.setCo_prctp(preview.getCo_prctp());
		previewsave.setCo_fct(preview.getCo_fct());
		previewsave.setCo_intro(preview.getCo_intro());
		
		String mimgSrc = preview.getCo_mimgsrc();
		if(mimgSrc != null) {
			previewsave.setCo_mimgsrc(mimgSrc);
		} 		
		else if(OCo_mimgsrc != null) {
			previewsave.setCo_mimgsrc(OCo_mimgsrc);
		}		
		
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("mimgSrc - "+mimgSrc);
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("OCo_mimgsrc - "+OCo_mimgsrc);
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		String atch_fild_id = preview.getAtch_file_id();
		if(atch_fild_id != null) {
			previewsave.setAtch_file_id(preview.getAtch_file_id());
		}
		else if(OAtch_file_id != null) {
			previewsave.setAtch_file_id(OAtch_file_id);
		}
		
		previewsave.setReg_date(preview.getReg_date());
		previewsave.setCo_credit(preview.getCo_credit());
		previewsave.setCo_trash(preview.getCo_trash());
		previewsave.setPreg_pid(preview.getPreg_pid());

		int newNakid= 0;
		if(preview.getAtch_file_id() != null) {
			nAtch_fid = preview.getAtch_file_id();
		}
		String Onak_id= preview.getPreg_pid();	//oid에 해당
		String nak_id = preview.getNak_id(); //pid에 해당
		String wsess = preview.getPreg_sess(); //pid에 해당
		
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("nak_id 설정");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//데이터를 업 한다.
	  	//신규데이터는 int최대치의 가상숫자값을 줬다. 따라서 10자리가 넘으면 신규, 나머지는 업데이트로 판단한다.
		if(preview.getPreg_pid().length() > 8) {
			//0을 준 이유는.. 부모값처리를 위해서.. 인서트는 부모값이 없기 때문에.. 0을 줘서, 부모값 처리를 다시한다.
			newNakid = 0;
			this.service.fishPreviewSaveI(previewsave);
		} else {
			//부모값을 전달받아, 자체값을 체크한다.  히스토리를 위해..
			newNakid = Integer.parseInt(preview.getPreg_pid());
			this.service.fishPreviewSave(previewsave);
		}		

		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰 이미지 호출");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		NaksinuriVO previewfileid = preview;
		previewfileid.setOnak_id(preview.getPreg_pid());      
		previewfileid.setNak_id(preview.getNak_id());       

		List<NaksinuriFileVO> prelist = this.service.Preview_searchFile(previewfileid);
		//model.addAttribute("prefilelist", prelist);		
		
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뮤 이미지 저장 ");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//파일을 처리한다.
		
		
		if(prelist.size()>0) {
			//삭제
			//프리뷰임시테이블(_files_preview)에 저장된 값이 있다면...  lettnfiledetail의 부모값을 삭제한다.
			this.service.fishfileOrgdelete(previewfileid);
		
			NaksinuriFileVO previewfilesave = naksinurifileVO;
			for(int i=0;i<prelist.size();i++){

				if(!nAtch_fid.equals(prelist.get(i).getP_atch_file_id())) {
					previewfilesave.setNak_id(newNakid);
					previewfilesave.setAtch_file_id(nAtch_fid);
					previewfilesave.setFile_sn(Integer.toString(i));
					previewfilesave.setFile_stre_cours(prelist.get(i).getP_file_stre_cours());
					previewfilesave.setStre_file_nm(prelist.get(i).getP_stre_file_nm());
					previewfilesave.setOrignl_file_nm(prelist.get(i).getP_orignl_file_nm());
					previewfilesave.setFile_extsn(prelist.get(i).getP_file_extsn());
					previewfilesave.setFile_cn(prelist.get(i).getP_file_cn());
					previewfilesave.setFile_size(prelist.get(i).getP_file_size());
		
					//삽입
					//프리뷰임시테이블(_files_preview)의 값을 호출하였고,  루프중 첫번째값이 원래 파일아이디값이므로, 이를 기반으로  파일아이디값을 통일하고,
					// file_sn을 배열순서대로 0부터 맞춰준다.
					this.service.fishfileOrgSave(previewfilesave);
				}
			}
		}
		
		NaksinuriVO delpreview = preview;
		delpreview.setOid(Onak_id);
		delpreview.setPid(nak_id);
		delpreview.setWsess(wsess);


		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰 데이터 정리");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");		
		//프리뷰데이터를 정리한다. - atch필드 지움, 키정보  업데이트한다
		//실제삭제가 아니라, 차후 수정을 위해 몇개필드의 갑을 없앤다. 필요한 값은 채워줘야 하므로 업데이트 한다.
		// file_sn을 배열순서대로 0부터 맞춰준다.
		this.service.delfishpreviewData(delpreview);

		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("프리뷰 파일 삭제");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//프리뷰파일을 정리한다. - row 삭제
		//프리뷰파일을 정리한다.
		//히스토리에서 파일내역까지 볼것은 아니므로... 해당 프리뷰의 모든 이미지를  삭제한다. 
		this.service.delfishpreviewfile(delpreview);
		
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.debug("자바스크립트 호출");
		LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//return "/naksinuri_original/naksinuri/admin/preview_fish";
		
		/*
	  	response.setCharacterEncoding("EUC-KR");
	  	PrintWriter writer = response.getWriter();
	  	writer.println("<script type='text/javascript'>");
	  	writer.println("alert('처리되었습니다.');");
	  	writer.println("opener.location.reload();");
	  	writer.println("self.window.close();");
	  	writer.println("</script>");
	  	writer.flush();
	  	*/
		
		model.addAttribute("message", "처리되었습니다.");
		return "naksinuri_original/naksinuri/alertForOpenWindow";
		
		
  }
 
  @RequestMapping({"/info/admin/indPreviewdel.do"})
  public String indPreviewdel(SessionStatus status, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
	throws Exception
  {
	  int san_sn = Integer.parseInt(request.getParameter("san_sn"));
	  
	  NaksinuriSanupVO deldata = SanupVO;
	  deldata.setSan_sn(Integer.toString(san_sn));
	  
	  this.service_sanup.delpreview_ind_data(deldata);
	  this.service_sanup.delpreview_ind_file(deldata);

	  /*
	  	response.setCharacterEncoding("EUC-KR");
	  	PrintWriter writer = response.getWriter();
	  	writer.println("<script type='text/javascript'>");
	  	writer.println("alert('처리되었습니다.');");
	  	writer.println("opener.location.reload();");
	  	writer.println("self.window.close();");
	  	writer.println("</script>");
	  	writer.flush();	 
	  	*/
	  model.addAttribute("message", "처리되었습니다.");
	  return "naksinuri_original/naksinuri/alertForOpenWindow";
	  
  }
  @RequestMapping({"/info/admin/fishPreviewdel.do"})
  public String fishPreviewdel(SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, NaksinuriFileVO naksinurifileVO,BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response) 
	throws Exception
  {
	  String nak_id = request.getParameter("nak_id");
	  
	  /* *************************************** */
	    NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
	    HttpSession session = request.getSession();    
	    
	    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	    insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
	    insertlogsVO.setUser_id(loginVO.getMBR_ID());
	    insertlogsVO.setActions("낚시터/낚시산업정보 삭제(fishPreviewdel.do) " + nak_id);
	    this.service_logs.insertLogs(insertlogsVO);
	    /* *************************************** */

	  NaksinuriVO deldata = naksinuriVO;
	  deldata.setNak_id(nak_id);
	  
	  this.service.delpreview_fish_data(deldata);
	  this.service.delpreview_fish_file(deldata);

	  /*
	  	response.setCharacterEncoding("EUC-KR");
	  	PrintWriter writer = response.getWriter();
	  	writer.println("<script type='text/javascript'>");
	  	writer.println("alert('처리되었습니다.');");
	  	writer.println("opener.location.reload();");
	  	writer.println("self.window.close();");
	  	writer.println("</script>");
	  	writer.flush();
	  */
	  
	  model.addAttribute("message", "처리되었습니다.");
	  return "naksinuri_original/naksinuri/alertForOpenWindow";
	  
  }
  
  @RequestMapping({"/info/admin/fish_history.do"})
  public void fish_history(SessionStatus status, @ModelAttribute("naksinuriVO") NaksinuriVO naksinuriVO, FishesVO fishesVO, NaksinuriFileVO naksinurifileVO,BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response) 
	throws Exception
  {
	  String nak_id = request.getParameter("nak_id");

	  NaksinuriVO getdata = naksinuriVO;
	  getdata.setNak_id(nak_id);
	  
	  NaksinuriVO hlist = this.service.preview_fish_list(getdata);

		JSONObject obj = new JSONObject();
	    obj.put("result", hlist);

		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(obj);
	  
	  
  }   
}
