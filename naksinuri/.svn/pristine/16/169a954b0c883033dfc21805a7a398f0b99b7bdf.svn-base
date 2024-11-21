package egovframework.naksinuri_original.let.naksinuri.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngUtil;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksiCongressMbrVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksiCongressOwnVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAdminVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAnglingService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAnglingVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriMailVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticVO;
import egovframework.naksinuri_original.let.naksinuri.utils.ExcelRead;
import egovframework.naksinuri_original.let.naksinuri.utils.ExcelReadOption;
import egovframework.naksinuri_original.let.naksinuri.utils.NaksinuriUtils;
import egovframework.utils.EgovStringUtil;

@Controller
public class NaksiBoardController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(NaksiBoardController.class);
	
  private static final NaksinuriMailVO NaksinuriMailVO = null;

  @Resource(name="NaksinuriService")
  private NaksinuriService service;

  @Resource(name="NaksinuriNewsService")
  private NaksinuriNewsService newsService;

  @Resource(name="NaksinuriAnglingService")
  private NaksinuriAnglingService anglingService;

  @Resource(name="NaksinuriStaticService")
  private NaksinuriStatisticService service_statistic;

  @Resource(name="NaksinuriOriginalEgovFileMngService")
  private NaksinuriOriginalEgovFileMngService fileMngService;

  @Resource(name="NaksinuriOriginalEgovFileMngUtil")
  private NaksinuriOriginalEgovFileMngUtil fileUtil;
  
  @Resource(name="NaksinuriSmsService")
  private NaksinuriSmsService smsService;
  
  @Resource(name="NaksinuriEventService")
 private NaksinuriEventService eventService;

  public static String getClientIpAddr(HttpServletRequest request) { String ip = request.getHeader("X-Forwarded-For");

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

    return ip; }

  public static String getClientBrowser(HttpServletRequest request)
  {
    String browser = "";

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

    return browser;
  }

  public static String getClientOS(HttpServletRequest request) {
    String os = "";
    
    

    String user_os = request.getHeader("User-Agent");
    
    LOGGER.debug("User-Agent " + user_os);
    
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
    else if (user_os.indexOf("Nokia") > 0) 
      os = "MeeGo";
    else if (user_os.indexOf("Linux") > 0)
      os = "Linux"; 
    else 
      os = "Windows";
        
    return os;
  }

  @RequestMapping({"/{column}/{table}/list{mobile}.do"})
  public String list(@PathVariable("table") String table, @PathVariable("column") String column,  @PathVariable("mobile") String mobile, ModelMap model, String bo_cate, String bo_sido, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	 
    LOGGER.debug(">> 공통 NaksiBoardController - list : /" + column + "/" + table + "/list.do");

    String[] parseReferer = NaksinuriUtils.checkFunnels(request.getHeader("referer"), this.service_statistic.get_funnels());
    if (parseReferer != null) {
      LOGGER.debug("parseReferer label : " + parseReferer[0]);
      LOGGER.debug("parseReferer url : " + parseReferer[1]);
      LOGGER.debug("parseReferer keyword : " + parseReferer[2]);
      staticVO.setFunnels_label(parseReferer[0]);
      staticVO.setFunnels_url(parseReferer[1]);
      staticVO.setFunnels_keyword(parseReferer[2]);
    }

    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");
    LOGGER.debug("------------------------ table :: " +table);
    LOGGER.debug("------------------------ column :: " + column);
	
    model.addAttribute("table", table);
    model.addAttribute("column", column);

    if ((table.equals("junior")) || (table.equals("gosu")) || (table.equals("class"))) {
      if (boardVO.getBo_cate() == null)
        bo_cate = "민물";
      else {
        bo_cate = boardVO.getBo_cate();
      }
    }

    String[] column_arr = { "lesson", "info", "sosig", "policy", "share", "survey", "promotion" };
    String[] table_arr = { "junior", "gosu", "sense", "dignity", "binding", "class", "travel", "column", "campaign", "notice", "lab", "policy", "usage", "info" };
    int chk_con1 = 0;
    int chk_con2 = 0;
    for (int z = 0; z < column_arr.length; z++) {
      if (column_arr[z].contains(column)) {
        chk_con1 = 1;
      }

    }

    for (int j = 0; j < table_arr.length; j++) {
      if (table_arr[j].contains(table)) {
        chk_con2 = 1;
      }
    }

    if (chk_con1 == 0) {
      return "redirect:/incorrect_url.do";
    }
    if (chk_con2 == 0) {
      return "redirect:/incorrect_url.do";
    }

    LOGGER.debug("경로:" + request.getRequestURL());
    String category_type = "";
    String category_name = "";
    String category_group_type = "";
    String category_group_name = "";
    if (table.equals("junior")) {
      staticVO.setBo_name("초보탈출하기");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
      if (bo_cate.equals("바다")) {
        category_type = "sea";
        category_name = "바다낚시";
      } else {
        category_type = "river";
        category_name = "민물(붕어)낚시";
      }
    } else if (table.equals("gosu")) {
      staticVO.setBo_name("낚시고수되기");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
      if (bo_cate.equals("루어")) {
        category_type = "lure";
        category_name = "루어낚시";
      } else if (bo_cate.equals("바다")) {
        category_type = "sea";
        category_name = "바다낚시";
      } else {
        category_type = "river";
        category_name = "민물(붕어)낚시";
      }
    } else if (table.equals("sense")) {
      staticVO.setBo_name("낚시상식");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
	} else if (table.equals("dignity")) {
		staticVO.setBo_name("낚시의 품격");
		category_group_type = "fishing_school";
		category_group_name = "낚시교실";
	} else if (table.equals("binding")) {
      staticVO.setBo_name("채비필수묶음법");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
    } else if (table.equals("class")) {
      staticVO.setBo_name("어종별낚시교실");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
      if (bo_cate.equals("바다")) {
        category_type = "sea";
        category_name = "바다낚시";
      } else {
        category_type = "river";
        category_name = "민물낚시";
      }
    } else if (table.equals("travel")) {
      staticVO.setBo_name("낚시여행기");
      category_group_type = "community";
      category_group_name = "커뮤니티";
    } else if (table.equals("column")) {
      staticVO.setBo_name("낚시칼럼");
      category_group_type = "community";
      category_group_name = "커뮤니티";
    } else if (table.equals("notice")) {
      staticVO.setBo_name("공지사항");
      category_group_type = "fishing_sosig";
      category_group_name = "낚시소식";
    } else if (table.equals("policy")) {
      staticVO.setBo_name("낚시정책안내");
      category_group_type = "fishing_policy";
      category_group_name = "낚시정책";
    } else if (table.equals("usage")) {
      staticVO.setBo_name("낚시용품사용기");
      category_group_type = "community";
      category_group_name = "커뮤니티";
/*    } else if (table.equals("safe")) {
      staticVO.setBo_name("낚시안전");
      category_group_type = "";
      category_group_name = "";*/
    }else if (table.equals("info")) {
      staticVO.setBo_name("알림마당");
      category_group_type = "";
      category_group_name = "";
    } else {
      staticVO.setBo_name("사용자게시판");
    }

    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type(table);
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type(category_group_type);
    staticVO.setCategory_group_name(category_group_name);
    staticVO.setPath_type("web");
    staticVO.setCategory_type(category_type);
    staticVO.setCategory_name(category_name);

    this.service_statistic.get_statisticInfo(staticVO,request);

    model.addAttribute("bo_cate", bo_cate);
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type(table);
    boardVO.setBo_cate(bo_cate);

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    List list = this.service.select_list(boardVO);
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

    
 
    if(mobile.equals("mobile")){
   	 return "naksinuri_original/naksinuri/skin/gallery_list_mobile";
   }else{
	   return "naksinuri_original/naksinuri/skin/gallery_list";
   }

  
	    

    
  }

  @RequestMapping({"/incorrect_url.do"})
  public String incorrect_url(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return "naksinuri_original/naksinuri/incorrect_url";
  }

  @RequestMapping({"/sosig/gosi/list.do"})
  public String gosi_list(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - gosi_list : 낚시정책 - 지역별 낚시준수사항 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setPath(request.getRequestURL().toString());
    staticVO.setBo_name("지역별낚시준수사항");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("gosi");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setPath_type("web");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    return "naksinuri_original/naksinuri/sosig/gosi_list";
  }

  @RequestMapping({"/sosig/gosi/m/list.do"})
  public String gosi_mlist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - gosi_mlist : 낚시정책(모바일) - 지역별 낚시준수사항 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setPath(request.getRequestURL().toString());
    staticVO.setBo_name("지역별낚시준수사항");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("gosi");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setPath_type("mobile");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    return "naksinuri_original/naksinuri/sosig/m/gosi_list";
  }

//  @RequestMapping({"/{column}/{table}/like.do"})
//  public String like(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column)
//    throws Exception
//  {
//    String bo_sn = request.getParameter("bo_sn");
//    model.addAttribute("bo_sn", bo_sn);
//    model.addAttribute("table", table);
//    model.addAttribute("column", column);
//    this.service.like_update(boardVO);
//
//    return "naksinuri_original/naksinuri/skin/like";
//  }
  
	@RequestMapping(value = "/{column}/{table}/like.do")
	public String like(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, 
			@PathVariable("table") String table, @PathVariable("column") String column) throws Exception {
		JSONObject data = new JSONObject();
		String bo_sn = request.getParameter("bo_sn");
	    model.addAttribute("bo_sn", bo_sn);
	    model.addAttribute("table", table);
	    model.addAttribute("column", column);
	    this.service.like_update(boardVO);
		data.put("error", "0");
		data.put("msg", "해당 글을 추천하셨습니다.");
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}

  @RequestMapping({"/{column}/{table}/m/like.do"})
  public String mlike(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column)
    throws Exception
  {
    String bo_sn = request.getParameter("bo_sn");
    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("table", table);
    model.addAttribute("column", column);
    this.service.like_update(boardVO);

    return "naksinuri_original/naksinuri/skin/m/like";
  }

  @RequestMapping({"/{column}/{table}/view{mobile}.do"})
  public String board_findCorp(RedirectAttributes redirectAttributes, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column,@PathVariable("mobile") String mobile)
    throws Exception
  {	
    LOGGER.debug("/" + column + "/" + table + "/view.do");
    LOGGER.debug("공통 처리 : NaksiBoardContoller - board_findCorp ");

    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    String[] column_arr = { "lesson", "info", "sosig", "policy", "share", "survey", "promotion" };
    String[] table_arr = { "junior", "gosu", "sense", "dignity", "binding", "class", "travel", "column", "campaign", "notice", "lab", "policy", "usage", "safe", "info" };

    int chk_con1 = 0;
    int chk_con2 = 0;
    for (int z = 0; z < column_arr.length; z++) {
      if (column_arr[z].contains(column)) {
        chk_con1 = 1;
      }

    }

    for (int j = 0; j < table_arr.length; j++) {
      if (table_arr[j].contains(table)) {
        chk_con2 = 1;
      }
    }

    if (chk_con1 == 0) {
      return "redirect:/incorrect_url.do";
    }
    if (chk_con2 == 0) {
      return "redirect:/incorrect_url.do";
    }

    model.addAttribute("table", table);
    model.addAttribute("column", column);
    boardVO.setBo_type(table);

    this.service.view_update(boardVO);

    BoardVO info = this.service.board_findCorp(boardVO);
    
    
    String vidId = "";
    
    if(info!=null && info.getBo_youtubelink() !=null && info.getBo_youtubelink().length()!=0) {
    	String url = info.getBo_youtubelink();
    	
    	if(url.indexOf("youtube.com/watch?v=") != -1) {
    		//https://m.youtube.com/watch?v=e3S9KINoH2M
    		vidId = url.substring(url.indexOf("youtube.com/watch?v=") + 20);
    	} else if(url.indexOf("youtube.com/watch/?v=") != -1)//https://m.youtube.com/watch/?v=e3S9KINoH2M
        {
            vidId = url.substring(url.indexOf("youtube.com/watch/?v=") + 21);
        }
        else if(url.indexOf("youtu.be") != -1)
        {
            vidId = url.substring(url.indexOf("youtu.be") + 9);
        }
        else if(url.indexOf("www.youtube.com/embed/") != -1)
        {
            vidId = url.substring(url.indexOf("www.youtube.com/embed/") + 22);
        }
        else if(url.indexOf("?v=") != -1)// http://m.youtube.com/?v=tbBTNCfe1Bc
        {
            vidId = url.substring(url.indexOf("?v=")+3, 11);
        }
        else
        {
           
            vidId =  url;
        }
    	
    	info.setBo_youtubelink("https://www.youtube.com/embed/"+vidId);

    }
    
    model.addAttribute("info", info);

    if (request.getParameter("co_comment") != null) {
    	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
  
      String co_name = request.getParameter("co_name");
      String co_pass = request.getParameter("co_pass");
      String co_comment = request.getParameter("co_comment");
      
      if (co_name != null) {
        co_name = mEgovStringUtil.getHtmlStrCnvr(co_name);
        boardVO.setCo_name(co_name);
      }
      boolean isRefusePassword = false;
      if (co_pass != null) {
    	  Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9]).{10,})");
    	  Matcher matcher = pattern.matcher(co_pass);
    	  if(!matcher.matches()) {
    		  isRefusePassword = true;
    	  } else {
	    	co_pass = mEgovStringUtil.getHtmlStrCnvr(co_pass);
	        boardVO.setCo_pass(co_pass);
    	  }
      } else {
    	  isRefusePassword = true;
      }
      if(isRefusePassword) {
		  response.setCharacterEncoding("UTF-8"); 
		  response.setContentType("text/html; charset=UTF-8");
		  PrintWriter writer = response.getWriter();
		  writer.println("<script type='text/javascript'>");
		  writer.println("alert('비밀번호는 숫자와 영문자 조합으로 10자리 이상 사용해야 합니다.');");
		  writer.println("history.back();");
		  writer.println("</script>");
		  writer.flush();
		  return null;
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

    List filelist = this.service.ba_file(boardVO);

    if (filelist.size() > 0) {
      model.addAttribute("filelist", filelist);
    }
    
    //pdf
    List pdffilelist = this.service.pdfimgfile(boardVO);
    if (pdffilelist.size() > 0) {
      model.addAttribute("pdffilelist", pdffilelist);
    }
    

    BoardVO movfile = this.service.movfile(boardVO);
    model.addAttribute("movfile", movfile);

    List post_list = this.service.select_list_post(boardVO);
    if (post_list.size() > 0) {
      model.addAttribute("post_list", post_list);
    }

    List bobo_list = this.service.select_list_bobo(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("noticlist", noticlist);

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
    if(mobile.equals("mobile")){
      	 return "naksinuri_original/naksinuri/skin/gallery_view_mobile";
      }else{
   	    return "naksinuri_original/naksinuri/skin/gallery_view";
      } 
  }
  

  @RequestMapping({"/{column}/{table}/m/list.do"})
  public String mobilelist(@PathVariable("table") String table, @PathVariable("column") String column, ModelMap model, String bo_cate, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("/" + column + "/" + table + "/m/list.do");
    LOGGER.debug(">> NaksiBoardController - mobilelist ");

    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    boardVO.setPageUnit(10);
    boardVO.setPageInfo(model);

    model.addAttribute("table", table);
    model.addAttribute("column", column);

    if ((table.equals("junior")) || (table.equals("gosu")) || (table.equals("class"))) {
      if (boardVO.getBo_cate() == null)
        bo_cate = "민물";
      else {
        bo_cate = boardVO.getBo_cate();
      }
    }

    String[] column_arr = { "lesson", "info", "sosig", "policy", "share", "survey", "promotion" };
    String[] table_arr = { "junior", "gosu", "sense", "dignity", "binding", "class", "travel", "column", "campaign", "notice", "lab", "policy", "usage", "safe", "info" };
    int chk_con1 = 0;
    int chk_con2 = 0;
    for (int z = 0; z < column_arr.length; z++) {
      if (column_arr[z].contains(column)) {
        chk_con1 = 1;
      }

    }

    for (int j = 0; j < table_arr.length; j++) {
      if (table_arr[j].contains(table)) {
        chk_con2 = 1;
      }
    }

    if (chk_con1 == 0) {
      return "redirect:/incorrect_url.do";
    }
    if (chk_con2 == 0) {
      return "redirect:/incorrect_url.do";
    }

    LOGGER.debug("경로:" + request.getRequestURL());
    String category_type = "";
    String category_name = "";
    String category_group_type = "";
    String category_group_name = "";
    if (table.equals("junior")) {
      staticVO.setBo_name("초보탈출하기");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
      if (bo_cate.equals("바다")) {
        category_type = "sea";
        category_name = "바다낚시";
      } else {
        category_type = "river";
        category_name = "민물(붕어)낚시";
      }
    } else if (table.equals("gosu")) {
      staticVO.setBo_name("낚시고수되기");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
      if (bo_cate.equals("루어")) {
        category_type = "lure";
        category_name = "루어낚시";
      } else if (bo_cate.equals("바다")) {
        category_type = "sea";
        category_name = "바다낚시";
      } else {
        category_type = "river";
        category_name = "민물(붕어)낚시";
      }
    } else if (table.equals("sense")) {
      staticVO.setBo_name("낚시상식");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
    } else if (table.equals("dignity")) {
		staticVO.setBo_name("낚시의 품격");
		category_group_type = "fishing_school";
		category_group_name = "낚시교실";
	} else if (table.equals("binding")) {
      staticVO.setBo_name("채비필수묶음법");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
    } else if (table.equals("class")) {
      staticVO.setBo_name("어종별낚시교실");
      category_group_type = "fishing_school";
      category_group_name = "낚시교실";
      if (bo_cate.equals("바다")) {
        category_type = "sea";
        category_name = "바다낚시";
      } else {
        category_type = "river";
        category_name = "민물낚시";
      }
    } else if (table.equals("travel")) {
      staticVO.setBo_name("낚시여행기");
      category_group_type = "community";
      category_group_name = "커뮤니티";
    } else if (table.equals("column")) {
      staticVO.setBo_name("낚시칼럼");
      category_group_type = "community";
      category_group_name = "커뮤니티";
    } else if (table.equals("notice")) {
      staticVO.setBo_name("공지사항");
      category_group_type = "fishing_sosig";
      category_group_name = "낚시소식";
    } else if (table.equals("policy")) {
      staticVO.setBo_name("낚시정책안내");
      category_group_type = "fishing_policy";
      category_group_name = "낚시정책";
    } else if (table.equals("usage")) {
      staticVO.setBo_name("낚시용품사용기");
      category_group_type = "community";
      category_group_name = "커뮤니티";
    } else if (table.equals("info")) {
      staticVO.setBo_name("알람마당");
      category_group_type = "알람마당";
      category_group_name = "알람마당";
    } else if (table.equals("safe")) {
      staticVO.setBo_name("낚시안전");
      category_group_type = "";
      category_group_name = "";
    } else {
      staticVO.setBo_name("사용자게시판");
    }

    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type(table);
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type(category_group_type);
    staticVO.setCategory_group_name(category_group_name);
    staticVO.setPath_type("mobile");
    staticVO.setCategory_name(category_name);
    staticVO.setCategory_type(category_type);

    this.service_statistic.get_statisticInfo(staticVO,request);
    model.addAttribute("bo_cate", bo_cate);
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type(table);
    boardVO.setBo_cate(bo_cate);

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    List list = this.service.select_list(boardVO);
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

    return "naksinuri_original/naksinuri/skin/m/gallery_list";
  }

  @RequestMapping({"/{column}/{table}/m/view.do"})
  public String mobileboard_findCorp(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column)
    throws Exception
  {
    model.addAttribute("table", table);
    model.addAttribute("column", column);
    boardVO.setBo_type(table);

    String[] column_arr = { "lesson", "info", "sosig", "policy", "share", "survey", "promotion" };
    String[] table_arr = { "junior", "gosu", "sense", "dignity", "binding", "class", "travel", "column", "campaign", "notice", "lab", "policy", "usage", "safe", "info" };

    int chk_con1 = 0;
    int chk_con2 = 0;
    for (int z = 0; z < column_arr.length; z++) {
      if (column_arr[z].contains(column)) {
        chk_con1 = 1;
      }

    }

    for (int j = 0; j < table_arr.length; j++) {
      if (table_arr[j].contains(table)) {
        chk_con2 = 1;
      }
    }

    if (chk_con1 == 0) {
      return "redirect:/incorrect_url.do";
    }
    if (chk_con2 == 0) {
      return "redirect:/incorrect_url.do";
    }

    this.service.view_update(boardVO);

    BoardVO info = this.service.board_findCorp(boardVO);
    model.addAttribute("info", info);

    if (request.getParameter("co_comment") != null) {
      EgovStringUtil mEgovStringUtil = new EgovStringUtil();
      String co_name = request.getParameter("co_name");
      String co_pass = request.getParameter("co_pass");
      String co_comment = request.getParameter("co_comment");

      if (co_name != null) {
        co_name = mEgovStringUtil.getHtmlStrCnvr(co_name);
        boardVO.setCo_name(co_name);
      }
      boolean isRefusePassword = false;
      if (co_pass != null) {
    	  Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9]).{10,})");
    	  Matcher matcher = pattern.matcher(co_pass);
    	  if(!matcher.matches()) {
    		  isRefusePassword = true;
    	  } else {
    	    co_pass = mEgovStringUtil.getHtmlStrCnvr(co_pass);
	        boardVO.setCo_pass(co_pass);
    	  }
      } else {
    	  isRefusePassword = true;
      }
      if(isRefusePassword) {
		  response.setCharacterEncoding("UTF-8"); 
		  response.setContentType("text/html; charset=UTF-8");
		  PrintWriter writer = response.getWriter();
		  writer.println("<script type='text/javascript'>");
		  writer.println("alert('비밀번호는 숫자와 영문자 조합으로 10자리 이상 사용해야 합니다.');");
		  writer.println("history.back();");
		  writer.println("</script>");
		  writer.flush();
		  return null;
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

    List filelist = this.service.ba_file(boardVO);

    if (filelist.size() > 0) {
      model.addAttribute("filelist", filelist);
    }

    BoardVO movfile = this.service.movfile(boardVO);
    model.addAttribute("movfile", movfile);

    BoardVO next = this.service.select_next(boardVO);
    BoardVO prev = this.service.select_prev(boardVO);
    if (next != null) {
      model.addAttribute("next", next);
    }
    if (prev != null) {
      model.addAttribute("prev", prev);
    }

    return "naksinuri_original/naksinuri/skin/m/gallery_view";
  }

  @RequestMapping({"/{column}/{table}/co_delete.do"})
  public String co_delete(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, @PathVariable("table") String table, @PathVariable("column") String column, ModelMap model)
    throws Exception
  {
    String co_pass4 = request.getParameter("co_pass4");
    String bo_sn = request.getParameter("bo_sn");
    BoardVO spass = this.service.copass_find(boardVO);

    String rpass = spass.getCo_pass();

    if (!rpass.equals(co_pass4))
      model.addAttribute("fail", "1");
    else {
      this.service.co_delete(boardVO);
    }
    model.addAttribute("table", table);
    model.addAttribute("column", column);
    model.addAttribute("bo_sn", bo_sn);
    return "naksinuri_original/naksinuri/skin/co_del";
  }

  @RequestMapping({"/{column}/{table}/m/co_delete.do"})
  public String co_delete_mobile(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, @PathVariable("table") String table, @PathVariable("column") String column, ModelMap model)
    throws Exception
  {
    String co_pass4 = request.getParameter("co_pass4");
    String bo_sn = request.getParameter("bo_sn");
    BoardVO spass = this.service.copass_find(boardVO);

    String rpass = spass.getCo_pass();

    if (!rpass.equals(co_pass4))
      model.addAttribute("fail", "1");
    else {
      this.service.co_delete(boardVO);
    }
    model.addAttribute("table", table);
    model.addAttribute("column", column);
    model.addAttribute("bo_sn", bo_sn);
    return "naksinuri_original/naksinuri/skin/m/co_del";
  }

  @RequestMapping({"/sosig/congress/list.do"})
  public String congresslist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - congresslist : 낚시소식 - 낚시대회 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    boardVO.setPageUnit(16);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("congress");

    staticVO.setBo_name("낚시대회");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("congress");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("fishing_sosig");
    staticVO.setCategory_group_name("낚시소식");
    staticVO.setPath_type("web");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    List latest_list = this.service.latest_list(boardVO);
    List list = this.service.user_congress_list(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (latest_list.size() > 0) {
      model.addAttribute("latest_list", latest_list);
    }

    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));

    }

    if (noticlist.size() > 0) {
      model.addAttribute("noticlist", noticlist);
    }
    model.addAttribute("pageUnit", Integer.valueOf(boardVO.getPageUnit()));
    model.addAttribute("gallery_list", boardVO.getGallery_list());
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

    return "naksinuri_original/naksinuri/sosig/congress_list";
  }

  @RequestMapping({"/sosig/congress/m/list.do"})
  public String congresslist_mobile(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - congresslist_mobile : 낚시소식(모바일) - 낚시대회 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    staticVO.setBo_name("낚시대회");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("congress");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("fishing_sosig");
    staticVO.setCategory_group_name("낚시소식");
    staticVO.setPath_type("mobile");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    boardVO.setPageUnit(16);
    boardVO.setPageInfo(model);

    boardVO.setBo_type("congress");

    List latest_list = this.service.latest_list(boardVO);
    List list = this.service.user_congress_list(boardVO);
    //List list = this.service.campaign_list(boardVO);//왜 모바일은 이걸 보고 있는거??

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (latest_list.size() > 0) {
      model.addAttribute("latest_list", latest_list);
    }

    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));

    }

    model.addAttribute("pageUnit", Integer.valueOf(boardVO.getPageUnit()));
    model.addAttribute("gallery_list", boardVO.getGallery_list());
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

    return "naksinuri_original/naksinuri/sosig/m/congress_list";
  }

  @RequestMapping({"/sosig/congress/endlist.do"})
  public String conendlist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    boardVO.setPageUnit(16);
    boardVO.setPageInfo(model);

    boardVO.setBo_type("congress");

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    model.addAttribute("pageUnit", Integer.valueOf(boardVO.getPageUnit()));
    model.addAttribute("pageIndex", Integer.valueOf(boardVO.getPageIndex()));

    List latest_list = this.service.latest_list(boardVO);
    List list = this.service.congress_endlist(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);

    if (latest_list.size() > 0) {
      model.addAttribute("latest_list", latest_list);
    }
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

    return "naksinuri_original/naksinuri/sosig/congress_endlist";
  }

  @RequestMapping({"/sosig/congress/m/endlist.do"})
  public String conendlist_mobile(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    boardVO.setPageUnit(16);
    boardVO.setPageInfo(model);

    boardVO.setBo_type("congress");

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    model.addAttribute("pageUnit", Integer.valueOf(boardVO.getPageUnit()));
    model.addAttribute("pageIndex", Integer.valueOf(boardVO.getPageIndex()));
    //List list = this.service.campaign_endlist(boardVO);//모바일은 왜 ??
    List list = this.service.congress_endlist(boardVO);
    List latest_list = this.service.latest_list(boardVO);

    if (latest_list.size() > 0) {
      model.addAttribute("latest_list", latest_list);
    }

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/sosig/m/congress_endlist";
  }

  @RequestMapping({"/sosig/congress/view.do"})
  public String board_findCorp(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    boardVO.setBo_type("congress");

    this.service.view_update(boardVO);

    BoardVO info = this.service.congress_findCorp(boardVO);
    model.addAttribute("info", info);

    if (request.getParameter("co_comment") != null) {
      String co_name = request.getParameter("co_name");
      String co_pass = request.getParameter("co_pass");
      String co_comment = request.getParameter("co_comment");

      if (co_name != null) {
        co_name = mEgovStringUtil.getHtmlStrCnvr(co_name);
        boardVO.setCo_name(co_name);
      }
      boolean isRefusePassword = false;
      if (co_pass != null) {
    	  Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9]).{10,})");
    	  Matcher matcher = pattern.matcher(co_pass);
    	  if(!matcher.matches()) {
    		  isRefusePassword = true;
    	  } else {
    	  co_pass = mEgovStringUtil.getHtmlStrCnvr(co_pass);
    	  boardVO.setCo_pass(co_pass);
    	  }
      } else {
    	  isRefusePassword = true;
      }
      if(isRefusePassword) {
    	  response.setCharacterEncoding("UTF-8"); 
    	  response.setContentType("text/html; charset=UTF-8");
    	  PrintWriter writer = response.getWriter();
    	  writer.println("<script type='text/javascript'>");
    	  writer.println("alert('비밀번호는 숫자와 영문자 조합으로 10자리 이상 사용해야 합니다.');");
    	  writer.println("history.back();");
    	  writer.println("</script>");
    	  writer.flush();
    	  return null;
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
    }

    List bobo_list = this.service.select_list_bobo(boardVO);

    if (bobo_list.size() > 0) {
      boardVO.setTotalPage(((BoardVO)bobo_list.get(0)).getTot_cnt());
      model.addAttribute("bobo_list", bobo_list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)bobo_list.get(0)).getTot_cnt()));
    }
    List noticlist = this.service.noticemark_list(boardVO);
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
    return "naksinuri_original/naksinuri/sosig/congress_view";
  }

  @RequestMapping({"/sosig/congress/mbrwrite.do"})
  public String congress_mbrwrite(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    
    String return_page = "naksinuri_original/naksinuri/sosig/congressmbr_write";
    if (boardVO.getBo_sn().isEmpty()) {
    	return_page = "redirect:/incorrect_url.do";
    }
    boardVO.setBo_type("congress");
    
    BoardVO info = this.service.congress_findCorp(boardVO);
    model.addAttribute("info", info);
    
    
    /*
    String bo_sn = request.getParameter("bo_sn");
    String start_date = request.getParameter("start_date");
    String end_date = request.getParameter("end_date");
    String bo_writer = request.getParameter("bo_writer");
    String host = request.getParameter("host");
    String organizer = request.getParameter("organizer");
    String bo_subject = request.getParameter("bo_subject");
    String cnt = request.getParameter("cnt");
    String bo_phone = request.getParameter("bo_phone");
    String cg_account = request.getParameter("cg_account");
    String cg_account_name = request.getParameter("cg_account_name");
    
    String return_page = "naksinuri_original/naksinuri/sosig/congressmbr_write";
    if (bo_sn.isEmpty()) {
      return_page = "redirect:/incorrect_url.do";
    }

    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("bo_subject", bo_subject);
    model.addAttribute("start_date", start_date);
    model.addAttribute("end_date", end_date);
    model.addAttribute("bo_writer", bo_writer);
    model.addAttribute("host", host);
    model.addAttribute("organizer", organizer);
    model.addAttribute("cnt", cnt);
    model.addAttribute("bo_phone", bo_phone);
    model.addAttribute("cg_account", cg_account);
    model.addAttribute("cg_account_name", cg_account_name);
    */

    return return_page;
  }

  @RequestMapping({"/sosig/congress/m/mbrwrite.do"})
  public String congress_mbrwrite_m(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	
	  String return_page = "naksinuri_original/naksinuri/sosig/m/congressmbr_write";
	  if (boardVO.getBo_sn().isEmpty()) {
		  return_page = "redirect:/incorrect_url.do";
	  }
	  boardVO.setBo_type("congress");
    
	  BoardVO info = this.service.congress_findCorp(boardVO);
	  model.addAttribute("info", info);
	  
	  /*
	  
	    String bo_sn = request.getParameter("bo_sn");
	    String start_date = request.getParameter("start_date");
	    String end_date = request.getParameter("end_date");
	    String bo_writer = request.getParameter("bo_writer");
	    String host = request.getParameter("host");
	    String organizer = request.getParameter("organizer");
	    String bo_subject = request.getParameter("bo_subject");
	    String cnt = request.getParameter("cnt");
	    String bo_phone = request.getParameter("bo_phone");
	    String cg_account = request.getParameter("cg_account");
	    String cg_account_name = request.getParameter("cg_account_name");
	
	    String return_page = "naksinuri_original/naksinuri/sosig/m/congressmbr_write";
	    if (bo_sn.isEmpty()) {
	      return_page = "redirect:/incorrect_url.do";
	    }
	
	    model.addAttribute("bo_sn", bo_sn);
	    model.addAttribute("bo_subject", bo_subject);
	    model.addAttribute("start_date", start_date);
	    model.addAttribute("end_date", end_date);
	    model.addAttribute("bo_writer", bo_writer);
	    model.addAttribute("cnt", cnt);
	    model.addAttribute("host", host);
	    model.addAttribute("organizer", organizer);
	    model.addAttribute("bo_phone",bo_phone);
	    model.addAttribute("cg_account",cg_account);
	    model.addAttribute("cg_account_name",cg_account_name);
	    
	    */

    return return_page;
  }

  @RequestMapping({"/sosig/congress/m/view.do"})
  public String board_findCorp_mobile(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    boardVO.setBo_type("congress");

    this.service.view_update(boardVO);

    BoardVO info = this.service.congress_findCorp(boardVO);
    model.addAttribute("info", info);

    if (request.getParameter("co_comment") != null) {
      String co_name = request.getParameter("co_name");
      String co_pass = request.getParameter("co_pass");
      String co_comment = request.getParameter("co_comment");

      if (co_name != null) {
        co_name = mEgovStringUtil.getHtmlStrCnvr(co_name);
        boardVO.setCo_name(co_name);
      }
      boolean isRefusePassword = false;
      if (co_pass != null) {
    	  Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9]).{10,})");
    	  Matcher matcher = pattern.matcher(co_pass);
    	  if(!matcher.matches()) {
    		  isRefusePassword = true;
    	  } else {
	        co_pass = mEgovStringUtil.getHtmlStrCnvr(co_pass);
	        boardVO.setCo_pass(co_pass);
    	  }
      } else {
    	  isRefusePassword = true;
      }
      if(isRefusePassword) {
		  response.setCharacterEncoding("UTF-8"); 
		  response.setContentType("text/html; charset=UTF-8");
		  PrintWriter writer = response.getWriter();
		  writer.println("<script type='text/javascript'>");
		  writer.println("alert('비밀번호는 숫자와 영문자 조합으로 10자리 이상 사용해야 합니다.');");
		  writer.println("history.back();");
		  writer.println("</script>");
		  writer.flush();
		  return null;
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
    }

    BoardVO next = this.service.select_next(boardVO);
    BoardVO prev = this.service.select_prev(boardVO);
    if (next != null) {
      model.addAttribute("next", next);
    }
    if (prev != null) {
      model.addAttribute("prev", prev);
    }
    return "naksinuri_original/naksinuri/sosig/m/congress_view";
  }

  @RequestMapping({"/promotion/campaign/list.do"})
  public String campaignlist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    LOGGER.debug("NaksiBoardController - campaignlist : 낚시정책 - 낚시캠페인 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    String[] data;
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    boardVO.setPageUnit(16);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("campaign");
    staticVO.setPath(request.getRequestURL().toString());
    staticVO.setBo_name("낚시캠페인");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("campaign");
    //--------------------------------------------------
    // URL 세부 카테고리 분류 추가 - 2018.06.29
    //--------------------------------------------------
    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setPath_type("web");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");
    //--------------------------------------------------
    this.service_statistic.get_statisticInfo(staticVO,request);

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    List list = this.service.campaign_list(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));

    }
    
	return "naksinuri_original/naksinuri/promotion/campaign_list";

  }

  @RequestMapping({"/promotion/campaign/endlist.do"})
  public String campaignendlist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setPageInfo(model);
    boardVO.setBo_type("campaign");

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    List list = this.service.campaign_endlist(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/promotion/campaign_endlist";
  }

  @RequestMapping({"/promotion/campaign/m/list.do"})
  public String campaign_m(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - campaign_m : 낚시정책(모바일) - 낚시캠페인 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    String[] data;
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    boardVO.setPageUnit(16);
    boardVO.setPageInfo(model);
    boardVO.setBo_type("campaign");

    staticVO.setBo_name("낚시캠페인");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("campaign");

    staticVO.setCategory_group_type("fishing_policy");
    staticVO.setCategory_group_name("낚시정책");
    staticVO.setPath_type("mobile");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    List list = this.service.campaign_list(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/promotion/m/campaign_list";
  }

  @RequestMapping({"/promotion/campaign/m/endlist.do"})
  public String campaignendm(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, HttpServletResponse response)
    throws Exception
  {
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setPageInfo(model);
    boardVO.setBo_type("campaign");

    model.addAttribute("gallery_list", boardVO.getGallery_list());
    List list = this.service.campaign_endlist(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/promotion/m/campaign_endlist";
  }

  @RequestMapping({"/sosig/congress/write.do"})
  public String c_write(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, String column, HttpServletRequest request)
    throws Exception
  {
    return "naksinuri_original/naksinuri/sosig/congress_write";
  }

  @RequestMapping({"/sosig/congress/write2.do"})
  public String c_write2(@ModelAttribute("boardVO") BoardVO boardVO, RedirectAttributes redirectAttributes, ModelMap model, String column, HttpServletRequest request)
    throws Exception
  {
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util();  
			  
    String bo_sn = request.getParameter("bo_sn");
    
    if (bo_sn != "") {
      boardVO.setBo_sn(bo_sn);
      BoardVO info = this.service.board_findCorp(boardVO);
      
      if( boardVO==null || info==null 
      || boardVO.getBo_email()==null || info.getBo_email()==null || !info.getBo_email().equals(boardVO.getBo_email()) 
      || boardVO.getRegit_num()==null || info.getRegit_num()==null || !info.getRegit_num().equals(boardVO.getRegit_num()) ) {
    	  Map<String, Object> postMap = new HashMap<String,Object>();
    	  postMap.put("message", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
    	  redirectAttributes.addFlashAttribute("alert_data",postMap);
    	  LOGGER.debug("비 정상적인 접근");
    	  return "redirect:/index.do";
      }
      
      
      BoardVO bmimg = this.service.bmimg(boardVO);
      info.setBo_sn(egovStringUtil.makeScrtyKey(bo_sn));
      model.addAttribute("bo_sn", bo_sn);
      model.addAttribute("info", info);
      model.addAttribute("bmimg", bmimg);
    }
    return "naksinuri_original/naksinuri/sosig/congress_write";
  }

  @RequestMapping({"/sosig/congress/m/write2.do"})
  public String c_write2_m(@ModelAttribute("boardVO") BoardVO boardVO, RedirectAttributes redirectAttributes, ModelMap model, String column, HttpServletRequest request)
    throws Exception
  {
	  
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util();  
	
    String bo_sn = request.getParameter("bo_sn");
    if (bo_sn != "") {
      boardVO.setBo_sn(bo_sn);
      BoardVO info = this.service.board_findCorp(boardVO);
      
      if( boardVO==null || info==null 
	  || boardVO.getBo_email()==null || info.getBo_email()==null || !info.getBo_email().equals(boardVO.getBo_email()) 
      || boardVO.getRegit_num()==null || info.getRegit_num()==null || !info.getRegit_num().equals(boardVO.getRegit_num()) ) {
    	  Map<String, Object> postMap = new HashMap<String,Object>();
    	  postMap.put("message", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
    	  redirectAttributes.addFlashAttribute("alert_data",postMap);
    	  LOGGER.debug("비 정상적인 접근");
    	  return "redirect:/m/index.do";
      }
      
      BoardVO bmimg = this.service.bmimg(boardVO);
      info.setBo_sn(egovStringUtil.makeScrtyKey(bo_sn));       

      model.addAttribute("bo_sn", egovStringUtil.makeScrtyKey(bo_sn));
      model.addAttribute("info", info);
      model.addAttribute("bmimg", bmimg);
    }
    return "naksinuri_original/naksinuri/sosig/m/congress_write";
  }

  @RequestMapping({"/sosig/congress/m/write.do"})
  public String m_write(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, String column)
    throws Exception
  {
    return "naksinuri_original/naksinuri/sosig/m/congress_write";
  }

  @RequestMapping({"/{column}/{table}/write.do"})
  public String _write(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column)
    throws Exception
  {
    model.addAttribute("table", table);
    model.addAttribute("column", column);

    String[] column_arr = { "lesson", "info", "sosig", "policy", "share", "survey" };
    String[] table_arr = { "junior", "gosu", "sense", "binding", "class", "travel", "column", "notice", "lab", "policy", "usage" };
    int chk_con1 = 0;
    int chk_con2 = 0;
    for (int z = 0; z < column_arr.length; z++) {
      if (column_arr[z].contains(column)) {
        chk_con1 = 1;
      }

    }

    for (int j = 0; j < table_arr.length; j++) {
      if (table_arr[j].contains(table)) {
        chk_con2 = 1;
      }
    }

    if (chk_con1 == 0) {
      return "redirect:/incorrect_url.do";
    }
    if (chk_con2 == 0) {
      return "redirect:/incorrect_url.do";
    }

    return "naksinuri_original/naksinuri/" + column + "/" + table + "_write";
  }

  @RequestMapping({"/{column}/{table}/m/write.do"})
  public String _write_mobile(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column)
    throws Exception
  {
    model.addAttribute("table", table);
    model.addAttribute("column", column);

    String[] column_arr = { "lesson", "info", "sosig", "policy", "share", "survey" };
    String[] table_arr = { "junior", "gosu", "sense", "binding", "class", "travel", "column", "notice", "lab", "policy", "usage" };
    int chk_con1 = 0;
    int chk_con2 = 0;
    for (int z = 0; z < column_arr.length; z++) {
      if (column_arr[z].contains(column)) {
        chk_con1 = 1;
      }

    }

    for (int j = 0; j < table_arr.length; j++) {
      if (table_arr[j].contains(table)) {
        chk_con2 = 1;
      }
    }

    if (chk_con1 == 0) {
      return "redirect:/incorrect_url.do";
    }
    if (chk_con2 == 0) {
      return "redirect:/incorrect_url.do";
    }

    return "naksinuri_original/naksinuri/" + column + "/m/" + table + "_write";
  }

  @RequestMapping({"/sosig/congress/check.do"})
  public String register_congresscheck(@ModelAttribute("boardVO") NaksiCongressMbrVO congressMbrVO, ModelMap model, String column)
    throws Exception
  {
    return "naksinuri_original/naksinuri/sosig/congress_check";
  }

  @RequestMapping({"/sosig/congress/m/check.do"})
  public String register_congresscheck_m(@ModelAttribute("boardVO") NaksiCongressMbrVO congressMbrVO, ModelMap model, String column)
    throws Exception
  {
    return "naksinuri_original/naksinuri/sosig/m/congress_check";
  }

  @RequestMapping({"/sosig/congress/mbrcheck.do"})
  public String register_congressmbrcheck(@ModelAttribute("boardVO") NaksiCongressMbrVO congressMbrVO, ModelMap model, String column)
    throws Exception
  {
    return "naksinuri_original/naksinuri/sosig/congress_mbrcheck";
  }

  @RequestMapping({"/sosig/congress/m/mbrcheck.do"})
  public String register_congressmbrcheck_m(@ModelAttribute("boardVO") NaksiCongressMbrVO congressMbrVO, ModelMap model, String column)
    throws Exception
  {
    return "naksinuri_original/naksinuri/sosig/m/congress_mbrcheck";
  }

  @RequestMapping(value={"/mbrstatus_change.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<BoardVO> mbrstatus_change(@ModelAttribute("boardVO") NaksiCongressMbrVO congressVO, ModelMap model, HttpServletRequest request
		  ,@RequestParam(value="idx") String idx ,@RequestParam(value="status") String status ) throws Exception {
    
	if(status.equals("접수삭제")) {
		congressVO.setIs_delete(1);
		congressVO.setIs_hide(0);
	} else if(status.equals("접수숨김")) {
		congressVO.setIs_delete(0);
		congressVO.setIs_hide(1);
	} else {
		congressVO.setIs_delete(0);
		congressVO.setIs_hide(0);
	}
	  
    congressVO.setIdx(idx);
    congressVO.setMbr_status(status);

    this.service.update_mbr_status(congressVO);
    return null;
  }

  @RequestMapping({"/share/travel/updateview.do"})
  public String user_findcorp(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util();  	  
	
    LOGGER.debug("NaksiBoardContoller - user_findcorp");

    String pass_chk = request.getParameter("pass_chk");
    if (pass_chk == null) {
      return "redirect:/incorrect_url.do";
    }

    String bo_pass = request.getParameter("bo_pass");
    BoardVO spass = this.service.bopass_find(boardVO);
    if ((bo_pass == null) || (!spass.getBo_pass().equals(bo_pass))) {
      return "redirect:/incorrect_url.do";
    }

    String bo_sn = request.getParameter("bo_sn");
    BoardVO info = this.service.board_findCorp(boardVO);
    BoardVO bmimg = this.service.bmimg(boardVO);
    BoardVO bsimg = this.service.bsimg(boardVO);
    List filelist = this.service.ba_file(boardVO);
  
    info.setBo_sn(egovStringUtil.makeScrtyKey(info.getBo_sn()));  
    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("info", info);
    model.addAttribute("bmimg", bmimg);
    model.addAttribute("bsimg", bsimg);
    model.addAttribute("filelist", filelist);

    return "naksinuri_original/naksinuri/share/travel_write";
  }

  @RequestMapping({"/share/travel/m/updateview.do"})
  public String user_findcorp_mobile(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util();  		  
    String pass_chk = request.getParameter("pass_chk");
    if (pass_chk == null) {
      return "redirect:/incorrect_url.do";
    }

    String bo_pass = request.getParameter("bo_pass");
    BoardVO spass = this.service.bopass_find(boardVO);
    if ((bo_pass == null) || (!spass.getBo_pass().equals(bo_pass))) {
      return "redirect:/incorrect_url.do";
    }

    String bo_sn = request.getParameter("bo_sn");
    BoardVO info = this.service.board_findCorp(boardVO);
    BoardVO bmimg = this.service.bmimg(boardVO);
    BoardVO bsimg = this.service.bsimg(boardVO);
    List filelist = this.service.ba_file(boardVO);

    info.setBo_sn(egovStringUtil.makeScrtyKey(info.getBo_sn()));  
    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("info", info);
    model.addAttribute("bmimg", bmimg);
    model.addAttribute("bsimg", bsimg);
    model.addAttribute("filelist", filelist);

    return "naksinuri_original/naksinuri/share/m/travel_write";
  }

  @RequestMapping({"/share/usage/updateview.do"})
  public String user1_findcorp(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util();  	  
    String pass_chk = request.getParameter("pass_chk");
    if (pass_chk == null) {
      return "redirect:/incorrect_url.do";
    }

    String bo_pass = request.getParameter("bo_pass");
    BoardVO spass = this.service.bopass_find(boardVO);
    if ((bo_pass == null) || (!spass.getBo_pass().equals(bo_pass))) {
      return "redirect:/incorrect_url.do";
    }

    String bo_sn = request.getParameter("bo_sn");
    BoardVO info = this.service.board_findCorp(boardVO);
    BoardVO bmimg = this.service.bmimg(boardVO);
    BoardVO bsimg = this.service.bsimg(boardVO);
    List filelist = this.service.ba_file(boardVO);
    
    info.setBo_sn(egovStringUtil.makeScrtyKey(info.getBo_sn()));      
    model.addAttribute("info", info);
    model.addAttribute("bmimg", bmimg);
    model.addAttribute("bsimg", bsimg);
    model.addAttribute("filelist", filelist);
    model.addAttribute("bo_sn", bo_sn);

    return "naksinuri_original/naksinuri/share/usage_write";
  }

  @RequestMapping({"/sosig/congress/search.do"})
  public String search_register(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, 
		  RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

	String bo_email = request.getParameter("bo_email");
    String regit_num = request.getParameter("regit_num");
    String bo_pass = request.getParameter("bo_pass");
    boardVO.setBo_email(bo_email);
    boardVO.setRegit_num(regit_num);
    boardVO.setBo_pass(bo_pass);
    List<BoardVO> list = this.service.registered_congress_list(boardVO);
    

    if(list.size() == 0){
    	Map<String, Object> postMap = new HashMap<String,Object>();
  	  	postMap.put("message", "일치하는 내역이 없습니다. 입력하신 정보를 확인해주세요.");	
  	  	redirectAttributes.addFlashAttribute("alert_data",postMap);
  	  	LOGGER.debug("낚시대회 등록 내역조회 일치 정보 없음");
  	  	return "redirect:/sosig/congress/check.do";
    }

    model.addAttribute("list", list);
    return "naksinuri_original/naksinuri/sosig/congress_search";
  }

  @RequestMapping({"/sosig/congress/m/search.do"})
  public String search_register_m(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, 
		  RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String bo_email = request.getParameter("bo_email");
    String regit_num = request.getParameter("regit_num");
    String bo_pass = request.getParameter("bo_pass");
    boardVO.setBo_email(bo_email);
    boardVO.setRegit_num(regit_num);
    boardVO.setBo_pass(bo_pass);
    List list = this.service.registered_congress_list(boardVO);

    if(list.size() == 0){
    	Map<String, Object> postMap = new HashMap<String,Object>();
  	  	postMap.put("message", "일치하는 내역이 없습니다. 입력하신 정보를 확인해주세요.");	
  	  	redirectAttributes.addFlashAttribute("alert_data",postMap);
  	  	LOGGER.debug("낚시대회 등록 내역조회 일치 정보 없음");
  	  	return "redirect:/sosig/congress/m/check.do";
    }
    
    model.addAttribute("list", list);

    return "naksinuri_original/naksinuri/sosig/m/congress_search";
  }

  @RequestMapping({"/sosig/congress/mbrsearch.do"})
  public String search_mbr(@ModelAttribute("congressVO") NaksiCongressMbrVO congressVO, ModelMap model, 
		  RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    String mbr_hp = request.getParameter("mbr_hp");
    String mbr_name = request.getParameter("mbr_name");

    congressVO.setMbr_hp(mbr_hp);
    congressVO.setMbr_name(mbr_name);

    List list = this.service.participated_congress_list(congressVO);
    
    if(list.size() == 0){
    	Map<String, Object> postMap = new HashMap<String,Object>();
    	postMap.put("message", "일치하는 내역이 없습니다. 입력하신 정보를 확인해주세요.");
    	redirectAttributes.addFlashAttribute("alert_data",postMap);
    	LOGGER.debug("낚시대회 참가자 내역조회 일치 정보 없음");
    	return "redirect:/sosig/congress/mbrcheck.do";
    }

    model.addAttribute("list", list);

    return "naksinuri_original/naksinuri/sosig/congress_mbrsearch";
  }

  @RequestMapping({"/sosig/congress/mbr_detail.do"})
  public String mbr_detail(@ModelAttribute("congressVO") NaksiCongressMbrVO congressVO, RedirectAttributes redirectAttributes,
		  ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String idx = request.getParameter("idx");
    String bo_sn = request.getParameter("bo_sn");
    String detail = request.getParameter("detail");
    if(detail!=null&&detail.length()!=0&&detail.equals("1")) {
    	model.addAttribute("detail", "1");
    } else {
    	model.addAttribute("detail", "0");
    }

    congressVO.setIdx(idx);
    congressVO.setBo_sn(bo_sn);

    NaksiCongressMbrVO info = this.service.mbr_detail(congressVO);
    if(info == null) {
    	return "redirect:/incorrect_url.do";
    } else {
    	if( congressVO==null  
		|| congressVO.getMbr_group()==null || info.getMbr_group()==null || !info.getMbr_group().equals(congressVO.getMbr_group())
		) {
	    	Map<String, Object> postMap = new HashMap<String,Object>();
	    	postMap.put("message", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
	    	redirectAttributes.addFlashAttribute("alert_data",postMap);
	    	LOGGER.debug("비 정상적인 접근");
	    	return "redirect:/index.do";
	    }
    }
    
    NaksiCongressOwnVO congressOwnVO = new NaksiCongressOwnVO();
    congressOwnVO.setBo_sn(Integer.parseInt(bo_sn));
    congressOwnVO.setMbr_group(info.getMbr_group());
    
    NaksiCongressOwnVO info_own = this.service.own_detail(congressOwnVO);
    
    model.addAttribute("info", info);
    model.addAttribute("info_own", info_own);

    return "naksinuri_original/naksinuri/sosig/mbr_detail";
  }

  @RequestMapping({"/sosig/congress/m/mbr_detail.do"})
  public String mbr_detail_m(@ModelAttribute("congressVO") NaksiCongressMbrVO congressVO, RedirectAttributes redirectAttributes,
		  ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String idx = request.getParameter("idx");
    String bo_sn = request.getParameter("bo_sn");

    congressVO.setIdx(idx);
    congressVO.setBo_sn(bo_sn);

    NaksiCongressMbrVO info = this.service.mbr_detail(congressVO);
    if(info == null) {
    	return "redirect:/incorrect_url.do";
    } else {
    	if( congressVO==null  
		|| congressVO.getMbr_group()==null || info.getMbr_group()==null || !info.getMbr_group().equals(congressVO.getMbr_group())
		) {
	    	Map<String, Object> postMap = new HashMap<String,Object>();
	    	postMap.put("message", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
	    	redirectAttributes.addFlashAttribute("alert_data",postMap);
	    	LOGGER.debug("비 정상적인 접근");
	    	return "redirect:/index.do";
	    }
    }
    
    NaksiCongressOwnVO congressOwnVO = new NaksiCongressOwnVO(); 
    congressOwnVO.setBo_sn(Integer.parseInt(bo_sn));
    congressOwnVO.setMbr_group(info.getMbr_group());
    
    NaksiCongressOwnVO info_own = this.service.own_detail(congressOwnVO);
    
    model.addAttribute("info", info);
    model.addAttribute("info_own", info_own);

    return "naksinuri_original/naksinuri/sosig/m/mbr_detail";
  }

  @RequestMapping({"/sosig/congress/m/mbrsearch.do"})
  public String search_mbr_m(@ModelAttribute("congressVO") NaksiCongressMbrVO congressVO, ModelMap model, 
		  RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String mbr_hp = request.getParameter("mbr_hp");
    String mbr_name = request.getParameter("mbr_name");

    congressVO.setMbr_hp(mbr_hp);
    congressVO.setMbr_name(mbr_name);

    List list = this.service.participated_congress_list(congressVO);

    if(list.size() == 0){
    	Map<String, Object> postMap = new HashMap<String,Object>();
    	postMap.put("message", "일치하는 내역이 없습니다. 입력하신 정보를 확인해주세요.");
    	redirectAttributes.addFlashAttribute("alert_data",postMap);
    	LOGGER.debug("낚시대회 참가자 내역조회 일치 정보 없음");
    	return "redirect:/sosig/congress/m/mbrcheck.do";
    }
    
    model.addAttribute("list", list);

    return "naksinuri_original/naksinuri/sosig/m/congress_mbrsearch";
  }

  @RequestMapping({"/share/usage/m/updateview.do"})
  public String user1_findcorp_mobile(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util();  	  
    String pass_chk = request.getParameter("pass_chk");
    if (pass_chk == null) {
      return "redirect:/incorrect_url.do";
    }

    String bo_pass = request.getParameter("bo_pass");
    BoardVO spass = this.service.bopass_find(boardVO);
    if ((bo_pass == null) || (!spass.getBo_pass().equals(bo_pass))) {
      return "redirect:/incorrect_url.do";
    }

    String bo_sn = request.getParameter("bo_sn");
    BoardVO info = this.service.board_findCorp(boardVO);
    BoardVO bmimg = this.service.bmimg(boardVO);
    BoardVO bsimg = this.service.bsimg(boardVO);
    List filelist = this.service.ba_file(boardVO);

    info.setBo_sn(egovStringUtil.makeScrtyKey(info.getBo_sn()));
    model.addAttribute("info", info);
    model.addAttribute("bmimg", bmimg);
    model.addAttribute("bsimg", bsimg);
    model.addAttribute("filelist", filelist);
    model.addAttribute("bo_sn", bo_sn);

    return "naksinuri_original/naksinuri/share/m/usage_write";
  }

  @RequestMapping({"/share/column/updateview.do"})
  public String user2_findcorp(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util();
	
    String pass_chk = request.getParameter("pass_chk");
    if (pass_chk == null) {
      return "redirect:/incorrect_url.do";
    }

    String bo_pass = request.getParameter("bo_pass");
    BoardVO spass = this.service.bopass_find(boardVO);
    if ((bo_pass == null) || (!spass.getBo_pass().equals(bo_pass))) {
      return "redirect:/incorrect_url.do";
    }

    String bo_sn = request.getParameter("bo_sn");
    BoardVO info = this.service.board_findCorp(boardVO);
    BoardVO bmimg = this.service.bmimg(boardVO);
    BoardVO bsimg = this.service.bsimg(boardVO);
    List filelist = this.service.ba_file(boardVO);

    info.setBo_sn(egovStringUtil.makeScrtyKey(info.getBo_sn()));
    model.addAttribute("info", info);
    model.addAttribute("bmimg", bmimg);
    model.addAttribute("bsimg", bsimg);
    model.addAttribute("filelist", filelist);
    model.addAttribute("bo_sn", bo_sn);

    return "naksinuri_original/naksinuri/share/column_write";
  }

  @RequestMapping({"/share/column/m/updateview.do"})
  public String user2_findcorp_mobile(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util();
	
    String pass_chk = request.getParameter("pass_chk");
    if (pass_chk == null) {
      return "redirect:/incorrect_url.do";
    }

    String bo_pass = request.getParameter("bo_pass");
    BoardVO spass = this.service.bopass_find(boardVO);
    if ((bo_pass == null) || (!spass.getBo_pass().equals(bo_pass))) {
      return "redirect:/incorrect_url.do";
    }

    String bo_sn = request.getParameter("bo_sn");
    BoardVO info = this.service.board_findCorp(boardVO);
    BoardVO bmimg = this.service.bmimg(boardVO);
    BoardVO bsimg = this.service.bsimg(boardVO);
    List filelist = this.service.ba_file(boardVO);

    info.setBo_sn(egovStringUtil.makeScrtyKey(info.getBo_sn()));
    model.addAttribute("info", info);
    model.addAttribute("bmimg", bmimg);
    model.addAttribute("bsimg", bsimg);
    model.addAttribute("filelist", filelist);
    model.addAttribute("bo_sn", bo_sn);

    return "naksinuri_original/naksinuri/share/m/column_write";
  }

  @RequestMapping({"/{column}/{table}/insert_data.do"})
  public String user_insert(MultipartHttpServletRequest multiRequest, @PathVariable("table") String table, @PathVariable("column") String column, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
	String mimg = "mimg";
    String simg = "simg";
    String _mainFileId = "";
    String _subFileId = "";
    String _atchFileId = "";
    List _result = null;
    List _result2 = null;
    List _result3 = null;
    boardVO.setBo_type(table);

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

    if (!mfile.isEmpty())
    {
      _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      for (int i = 0; i < _result.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
    }

    if (!sfile.isEmpty())
    {
      _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
      for (int i = 0; i < _result2.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
    }

    if (!files.isEmpty())
    {
      _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < _result3.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);
    }

    boardVO.setBo_main_img(_mainFileId);
    boardVO.setBo_sub_img(_subFileId);
    boardVO.setBo_atch_file(_atchFileId);
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    String bo_content = request.getParameter("bo_content");
    String title1 = request.getParameter("title");

    if (bo_name != null) {
      bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
      boardVO.setBo_name(bo_name);
    }

    if (bo_pass != null) {
      bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
      boardVO.setBo_pass(bo_pass);
    }

    if (bo_subject != null) {
      bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
      boardVO.setBo_subject(bo_subject);
    }
    
    if (bo_content != null) {
    	bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
        boardVO.setBo_content(bo_content);
      }
    
    //게시물 승인 제도 추가 - 2019.07.31
    if( (column.equals("share") && table.equals("travel"))	//낚시여행기 
    	||(column.equals("share") && table.equals("usage")) //낚시용품 사용기
    	||(column.equals("share") && table.equals("column")) //낚시칼럼
    ) {
    	boardVO.setBo_trash("3");
//[보안점검수정][START]####################################################
    	this.service.insert_data(boardVO);
    }
        
    //this.service.insert_data(boardVO);
//[보안점검수정][END]####################################################
    
    return "redirect:/" + column + "/" + table + "/list.do";
  }

  @RequestMapping({"/sosig/congress/insert_data.do"})
  public String congress_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, NaksinuriAdminVO adminVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
		  
    String mimg = "mimg";
    String simg = "simg";
    String _mainFileId = "";
    String _subFileId = "";
    String _atchFileId = "";
    List _result = null;
    List _result2 = null;
    List _result3 = null;
    boardVO.setBo_type("congress");
    boardVO.setBo_trash("3");
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

    if (!mfile.isEmpty())
    {
      _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      for (int i = 0; i < _result.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
    }

    if (!sfile.isEmpty())
    {
      _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
      for (int i = 0; i < _result2.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
    }

    if (!files.isEmpty())
    {
      _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < _result3.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);
    }

    boardVO.setBo_main_img(_mainFileId);
    boardVO.setBo_sub_img(_subFileId);
    boardVO.setBo_atch_file(_atchFileId);
    String phone2 = request.getParameter("bo_phone2");
    String phone3 = request.getParameter("bo_phone3");
    String randstr = getRandomString(4);

    String c_char = randstr + phone2 + phone3;
    boardVO.setRegit_num(c_char);

    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    
    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    String bo_start_dt = request.getParameter("bo_start_dt");
    String bo_end_dt = request.getParameter("bo_end_dt");
    String bo_content = request.getParameter("bo_content");
    
    if (bo_name != null) {
      bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
      boardVO.setBo_name(bo_name);
    }
    if (bo_pass != null) {
      bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
      boardVO.setBo_pass(bo_pass);
    }
    if (bo_subject != null) {
      bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
      boardVO.setBo_subject(bo_subject);
    }
    if (bo_content != null) {
    	bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
	  boardVO.setBo_content(bo_content);
    }
    
    
    NaksinuriMailVO mailVO = new NaksinuriMailVO();
    String[] email_arr = { boardVO.getBo_email() };
    NaksinuriAdminVO admin_info = this.service.adminset_findCorp(adminVO);
    String admin_email = admin_info.getEmail();
    String admin_email_pw = admin_info.getEmail_pw();
    if (admin_email != "") {
      mailVO.setSmtpEmail(admin_email);
      mailVO.setSmtpEmailPw(admin_email_pw);
      mailVO.setFromMail(admin_email);
      mailVO.setFromMailNm("낚시누리");
      mailVO.setAddToMail(email_arr);
      mailVO.setMailMsg("\n대회기간 : " + bo_start_dt + "~" + bo_end_dt + "\n\n(" + bo_subject + ")의 접수번호 입니다 : " + c_char);
      mailVO.setTitle("안녕하세요 낚시누리 입니다. 등록하신 대회의 접수번호를 보내드립니다.");
      MailUtil.mailSend(mailVO);
    }
    
    this.service.congress_insert_data(boardVO);

    return "redirect:/sosig/congress/list.do";
  }

  private static String getRandomString(int length)
  {
    StringBuffer buffer = new StringBuffer();
    Random random = new Random();
    random.setSeed(System.currentTimeMillis());
    String[] chars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");

    for (int i = 0; i < length; i++)
    {
      buffer.append(chars[random.nextInt(chars.length)]);
    }
    return buffer.toString();
  }

  @RequestMapping({"/sosig/congress/mbrinsert_data.do"})
  public String congressmbr_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, 
		  @ModelAttribute("boardVO") BoardVO boardVO, 
		  @ModelAttribute("congressOwnVO") NaksiCongressOwnVO congressOwnVO, 
		  @ModelAttribute("congressmbrVO") NaksiCongressMbrVO congressmbrVO, 
		  BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	  boolean isSuccess = true;
	String return_page = "/sosig/congress/list.do";//"redirect:/sosig/congress/list.do";
	if (congressmbrVO.getBo_sn().isEmpty()) {
		return_page = "/incorrect_url.do";//return_page = "redirect:/incorrect_url.do";
		isSuccess = true;
		
	}
	boardVO.setBo_sn(congressmbrVO.getBo_sn());
	boardVO.setBo_type("congress");
	BoardVO info = this.service.congress_findCorp(boardVO);
	
	int own_deposit_amount = 0;//총입금금액
	int mbr_count = 0;//참가인원
	
	
	LOGGER.debug("------------------- 파라미터확인");
	LOGGER.debug("유입경로 : " +congressOwnVO.getOwn_inflow_path());
	LOGGER.debug("낚시경력 : "+congressOwnVO.getOwn_naksi_career());
	LOGGER.debug("참가이유 : " + congressOwnVO.getOwn_etc());

	
	//그룹코드
	Date today = new Date();
	SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
	String mbr_group = date.format(today) + getRandomString(4);
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
		
	//신청자 정보
	congressOwnVO.setRepre_title(info.getBo_subject());//대회명
	congressOwnVO.setRepre_writer(info.getBo_name());//주최자
	congressOwnVO.setMbr_group(mbr_group);//그룹코드
	
	String own_name = "";	
	if(congressOwnVO.getOwn_name() != null){
		own_name = mEgovStringUtil.getHtmlStrCnvr(congressOwnVO.getOwn_name());
	}
	String own_address = "";	
	if(congressOwnVO.getOwn_name() != null){
		own_address = mEgovStringUtil.getHtmlStrCnvr(congressOwnVO.getOwn_address());
	}
	String own_etc = "";	
	if(congressOwnVO.getOwn_name() != null){
		own_etc = mEgovStringUtil.getHtmlStrCnvr(congressOwnVO.getOwn_etc());
	}
	String own_attend_cause = "";	
	if(congressOwnVO.getOwn_name() != null){
		own_attend_cause = mEgovStringUtil.getHtmlStrCnvr(congressOwnVO.getOwn_attend_cause());
	}
	String own_repre_name = "";	
	if(congressOwnVO.getOwn_name() != null){
		own_repre_name = mEgovStringUtil.getHtmlStrCnvr(congressOwnVO.getOwn_repre_name());
	}
	
	LOGGER.debug("------------------- 1");
	
	congressOwnVO.setOwn_name(own_name);
	congressOwnVO.setOwn_address(own_address);
	congressOwnVO.setOwn_etc(own_etc);
	congressOwnVO.setOwn_attend_cause(own_attend_cause);
	congressOwnVO.setOwn_repre_name(own_repre_name);
	
	LOGGER.debug("------------------- 2");
	
	//참가자 공통 정보
	congressmbrVO.setRepre_title(info.getBo_subject());//대회명
	congressmbrVO.setRepre_writer(info.getBo_name());//주최자
	congressmbrVO.setMbr_group(mbr_group);//그룹코드
	congressmbrVO.setRepre_name(congressOwnVO.getOwn_repre_name());//입금자명
	congressmbrVO.setDepo_date(congressOwnVO.getOwn_depo_date());//입금예정일자
	congressmbrVO.setMbr_own_name(congressOwnVO.getOwn_name());//신청자명
	
	String mbr_name = mEgovStringUtil.getHtmlStrCnvr(congressmbrVO.getMbr_name());
	String etc = mEgovStringUtil.getHtmlStrCnvr(congressmbrVO.getEtc());
    String mbr_status = "접수대기";
    String[] ci_types = congressmbrVO.getCi_type().split(",");//참여유형선택
    String[] mbr_names = mbr_name.split(",");//참가자명
    String[] mbr_genders = congressmbrVO.getMbr_gender().split(",");//성별
    String[] mbr_births = congressmbrVO.getMbr_birth().split(",");//생년월일
    String[] mbr_hps = congressmbrVO.getMbr_hp().split(",");//연락처
    String[] mbr_addresss = mEgovStringUtil.getHtmlStrCnvr(congressmbrVO.getMbr_address()).split(",");//주소
    String[] mbr_relations = mEgovStringUtil.getHtmlStrCnvr(congressmbrVO.getMbr_relation()).split(",");//신청자와의 관계
    String[] mbr_belongto = mEgovStringUtil.getHtmlStrCnvr(congressmbrVO.getMbr_belongto()).split(",");//소속
    String[] jacket_offers = null;
    if(info.getIs_jacket_offer_y().equals("1")) {
    	jacket_offers = congressmbrVO.getJacket_offer().split(",");//구명조끼 지참여부
    }
    //String[] inflow_paths = congressmbrVO.getInflow_path().split(",");//유입경로
    //String[] attend_causes = congressmbrVO.getAttend_cause().split(",");//참가이유
    String[] naksi_careers = null;
    if(info.getIs_naksi_career_y().equals("1")) {
    	naksi_careers = congressmbrVO.getNaksi_career().split(",");//낚시경력
    }
    //String[] repre_names = congressmbrVO.getRepre_name().split(",");
    //String[] depo_dates = congressmbrVO.getDepo_date().split(",");
    String[] deposit_amounts = congressmbrVO.getDeposit_amount().split(",");//입금금액
    String[] etcs = etc.split(",");//비고
    
    //참가자 개별 정보
    for (int i = 0; i < mbr_names.length; i++) {
      congressmbrVO.setCi_type(ci_types[i]);
      congressmbrVO.setMbr_name(mbr_names[i]);
      congressmbrVO.setMbr_name(mbr_names[i]);
      congressmbrVO.setMbr_gender(mbr_genders[i]);
      congressmbrVO.setMbr_birth(mbr_births[i]);
      congressmbrVO.setMbr_relation(mbr_relations[i]);
      congressmbrVO.setMbr_address(mbr_addresss[i]);
      congressmbrVO.setEtc(etcs[i]);
      congressmbrVO.setMbr_hp(mbr_hps[i]);
      congressmbrVO.setDeposit_amount(deposit_amounts[i]);
      congressmbrVO.setMbr_status(mbr_status);
      congressmbrVO.setMbr_belongto(mbr_belongto[i]);
      if(info.getIs_jacket_offer_y().equals("1")) {
    	  congressmbrVO.setJacket_offer(jacket_offers[i]);
      } else {
    	  congressmbrVO.setJacket_offer("");
      }
      if(info.getIs_naksi_career_y().equals("1")) {
    	  congressmbrVO.setNaksi_career(naksi_careers[i]);
      } else {
    	  congressmbrVO.setNaksi_career("");
      }
      this.service.congressmbr_insert_data(congressmbrVO);
      
      if(deposit_amounts[i]!=null && deposit_amounts[i].length()!=0) {
    	  try {
    		  own_deposit_amount += Integer.parseInt(deposit_amounts[i]);
    		  if(own_deposit_amount < 0) {
    			  own_deposit_amount = 0;
    		  }
    	  } catch(Exception e) {
    		  own_deposit_amount = 0;
    	  }
      }      
      mbr_count++;
    }
    //총입금금액
    congressOwnVO.setOwn_deposit_amount(own_deposit_amount);
    this.service.congressOwn_insert_data(congressOwnVO);
    
    if(info.getIs_autosend_sms_y().equals("1")) {
        //신청자 한테 문자 발송
		smsService.sendToCongressCustomer(
			  congressOwnVO.getOwn_hp(),//받는사람 번호
			  info.getBo_subject(),//대회명
			  String.valueOf(congressOwnVO.getOwn_deposit_amount()),//입금금액
			  congressOwnVO.getOwn_repre_name(),//입금자명
			  info.getCg_account(),//입금계좌
			  info.getCg_account_name(),//은행명
			  info.getBo_phone(),//담당자 연락처
			  mbr_count,//총 참가인원
			  getClientIpAddr(request)//발송시도시 ip
			  );
	    
	    //담당자 한테 문자 발송
	    smsService.sendToCongressAdmin(info.getBo_phone(), congressmbrVO.getRepre_title(), mbr_name, mbr_count, getClientIpAddr(request));
	}
    
    if(isSuccess) {
	    response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('신청이 완료되었습니다.'); location.href='"+return_page+"'</script>");
		out.flush();
		return null;
    }
    
    return return_page;
  }

  @RequestMapping({"/sosig/congress/m/mbrinsert_data.do"})
  public String congressmbr_insert_m(MultipartHttpServletRequest multiRequest, SessionStatus status, 
		  @ModelAttribute("boardVO") BoardVO boardVO, 
		  @ModelAttribute("congressOwnVO") NaksiCongressOwnVO congressOwnVO,
		  @ModelAttribute("congressmbrVO") NaksiCongressMbrVO congressmbrVO, 
		  BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	  	boolean isSuccess = true;
		String return_page = "/sosig/congress/m/list.do";//"redirect:/sosig/congress/m/list.do";
		if (congressmbrVO.getBo_sn().isEmpty()) {
			return_page = "/incorrect_url.do";//"redirect:/incorrect_url.do";
			isSuccess = false;
		}
		boardVO.setBo_sn(congressmbrVO.getBo_sn());
		boardVO.setBo_type("congress");
		BoardVO info = this.service.congress_findCorp(boardVO);
		
		int own_deposit_amount = 0;//총입금금액
		int mbr_count = 0;//참가인원
		
		//그룹코드
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String mbr_group = date.format(today) + getRandomString(4);
			
		//신청자 정보
		congressOwnVO.setRepre_title(info.getBo_subject());//대회명
		congressOwnVO.setRepre_writer(info.getBo_name());//주최자
		congressOwnVO.setMbr_group(mbr_group);//그룹코드
		
		//참가자 공통 정보
		congressmbrVO.setRepre_title(info.getBo_subject());//대회명
		congressmbrVO.setRepre_writer(info.getBo_name());//주최자
		congressmbrVO.setMbr_group(mbr_group);//그룹코드
		congressmbrVO.setRepre_name(congressOwnVO.getOwn_repre_name());//입금자명
		congressmbrVO.setDepo_date(congressOwnVO.getOwn_depo_date());//입금예정일자
		congressmbrVO.setMbr_own_name(congressOwnVO.getOwn_name());//신청자명
		
		String mbr_name = congressmbrVO.getMbr_name();
		String etc = congressmbrVO.getEtc();
	    String mbr_status = "접수대기";
	    String[] ci_types = congressmbrVO.getCi_type().split(",");//참여유형선택
	    String[] mbr_names = mbr_name.split(",");//참가자명
	    String[] mbr_genders = congressmbrVO.getMbr_gender().split(",");//성별
	    String[] mbr_births = congressmbrVO.getMbr_birth().split(",");//생년월일
	    String[] mbr_hps = congressmbrVO.getMbr_hp().split(",");//연락처
	    String[] mbr_addresss = congressmbrVO.getMbr_address().split(",");//주소
	    String[] mbr_relations = congressmbrVO.getMbr_relation().split(",");//신청자와의 관계
	    String[] mbr_belongto = congressmbrVO.getMbr_belongto().split(",");//소속
	    String[] jacket_offers = null;
	    if(info.getIs_jacket_offer_y().equals("1")) {
	    	jacket_offers = congressmbrVO.getJacket_offer().split(",");//구명조끼 지참여부
	    }
	    //String[] inflow_paths = congressmbrVO.getInflow_path().split(",");//유입경로
	    //String[] attend_causes = congressmbrVO.getAttend_cause().split(",");//참가이유
	    String[] naksi_careers = null;
	    if(info.getIs_naksi_career_y().equals("1")) {
	    	naksi_careers = congressmbrVO.getNaksi_career().split(",");//낚시경력
	    }
	    //String[] repre_names = congressmbrVO.getRepre_name().split(",");
	    //String[] depo_dates = congressmbrVO.getDepo_date().split(",");
	    String[] deposit_amounts = congressmbrVO.getDeposit_amount().split(",");//입금금액
	    String[] etcs = etc.split(",");//비고
	    
	    //참가자 개별 정보
	    for (int i = 0; i < mbr_names.length; i++) {
	      congressmbrVO.setCi_type(ci_types[i]);
	      congressmbrVO.setMbr_name(mbr_names[i]);
	      congressmbrVO.setMbr_gender(mbr_genders[i]);
	      congressmbrVO.setMbr_birth(mbr_births[i]);
	      congressmbrVO.setMbr_relation(mbr_relations[i]);
	      congressmbrVO.setMbr_address(mbr_addresss[i]);
	      congressmbrVO.setEtc(etcs[i]);
	      congressmbrVO.setMbr_hp(mbr_hps[i]);
	      congressmbrVO.setDeposit_amount(deposit_amounts[i]);
	      congressmbrVO.setMbr_status(mbr_status);
	      congressmbrVO.setMbr_belongto(mbr_belongto[i]);
	      if(info.getIs_jacket_offer_y().equals("1")) {
	    	  congressmbrVO.setJacket_offer(jacket_offers[i]);
	      } else {
	    	  congressmbrVO.setJacket_offer("");
	      }
	      if(info.getIs_naksi_career_y().equals("1")) {
	    	  congressmbrVO.setNaksi_career(naksi_careers[i]);
	      } else {
	    	  congressmbrVO.setNaksi_career("");
	      }
	      this.service.congressmbr_insert_data(congressmbrVO);
	      
	      if(deposit_amounts[i]!=null && deposit_amounts[i].length()!=0) {
	    	  try {
	    		  own_deposit_amount += Integer.parseInt(deposit_amounts[i]);
	    		  if(own_deposit_amount < 0) {
	    			  own_deposit_amount = 0;
	    		  }
	    	  } catch(Exception e) { 
	    		  own_deposit_amount = 0;
	    	  }
	      }      
	      mbr_count++;
	    }
	    //총입금금액
	    congressOwnVO.setOwn_deposit_amount(own_deposit_amount);
	    this.service.congressOwn_insert_data(congressOwnVO);
	    
	    if(info.getIs_autosend_sms_y().equals("1")) {
		    //신청자 한테 문자 발송
			smsService.sendToCongressCustomer(
				  congressOwnVO.getOwn_hp(),//받는사람 번호
				  info.getBo_subject(),//대회명
				  String.valueOf(congressOwnVO.getOwn_deposit_amount()),//입금금액
				  congressOwnVO.getOwn_repre_name(),//입금자명
				  info.getCg_account(),//입금계좌
				  info.getCg_account_name(),//은행명
				  info.getBo_phone(),//담당자 연락처
				  mbr_count,//총 참가인원
				  getClientIpAddr(request)//발송시도시 ip
				  );
		    
		    //담당자 한테 문자 발송
		    smsService.sendToCongressAdmin(info.getBo_phone(), congressmbrVO.getRepre_title(), mbr_name, mbr_count, getClientIpAddr(request));
	    }
	    
	    if(isSuccess) {
		    response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('신청이 완료되었습니다.'); location.href='"+return_page+"'</script>");
			out.flush();
	    }
	    
    return return_page;
  }

  @RequestMapping({"/sosig/congress/m/insert_data.do"})
  public String m_congress_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    String mimg = "mimg";
    String simg = "simg";
    String _mainFileId = "";
    String _subFileId = "";
    String _atchFileId = "";
    List _result = null;
    List _result2 = null;
    List _result3 = null;
    boardVO.setBo_type("congress");
    boardVO.setBo_trash("3");
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

    if (!mfile.isEmpty())
    {
      _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      for (int i = 0; i < _result.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
    }

    if (!sfile.isEmpty())
    {
      _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
      for (int i = 0; i < _result2.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
    }

    if (!files.isEmpty())
    {
      _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < _result3.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);
    }

    boardVO.setBo_main_img(_mainFileId);
    boardVO.setBo_sub_img(_subFileId);
    boardVO.setBo_atch_file(_atchFileId);
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    
    String phone2 = request.getParameter("bo_phone2");
    String phone3 = request.getParameter("bo_phone3");
    String randstr = getRandomString(4);

    String c_char = phone2 + randstr + phone3;
    boardVO.setRegit_num(c_char);

    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    String bo_content = request.getParameter("bo_content");

    if (bo_name != null) {
      bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
      boardVO.setBo_name(bo_name);
    }

    if (bo_pass != null) {
      bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
      boardVO.setBo_pass(bo_pass);
    }

    if (bo_subject != null) {
      bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
      boardVO.setBo_subject(bo_subject);
    }
    
    if (bo_content != null) {
    	bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
      boardVO.setBo_content(bo_content);
    }

    NaksinuriMailVO mailVO = new NaksinuriMailVO();
    String[] email_arr = { boardVO.getBo_email() };
    mailVO.setFromMail("ino3255@gmail.com");
    mailVO.setFromMailNm("낚시누리");
    mailVO.setAddToMail(email_arr);
    mailVO.setMailMsg("(" + bo_subject + ")의 접수번호 입니다 : " + c_char);
    mailVO.setTitle("안녕하세요 낚시누리 입니다. 등록하신 대회의 접수번호를 보내드립니다.");

    MailUtil.mailSend(mailVO);

    this.service.congress_insert_data(boardVO);

    return "redirect:/sosig/congress/m/list.do";
  }

  @RequestMapping({"/{column}/{table}/update_data.do"})
  public String user_update(MultipartHttpServletRequest multiRequest, @PathVariable("table") String table, @PathVariable("column") String column, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	mEgovStringUtil.AES128Util();  
	
    boardVO.setBo_type(table);

    String _mainFileId = boardVO.getBo_main_img();
    String _subFileId = boardVO.getBo_sub_img();
    String _atchFileId = boardVO.getBo_atch_file();
    
    if(_mainFileId != null){
    	_mainFileId = mEgovStringUtil.getHtmlStrCnvr(_mainFileId).toUpperCase();
    	boardVO.setBo_main_img(_mainFileId);
    }
    if(_subFileId != null){
    	_subFileId = mEgovStringUtil.getHtmlStrCnvr(_subFileId).toUpperCase();
    	boardVO.setBo_sub_img(_subFileId);
    }
    if(_atchFileId != null){
    	_atchFileId = mEgovStringUtil.getHtmlStrCnvr(_atchFileId).toUpperCase();
    	boardVO.setBo_atch_file(_atchFileId);
    }

    String bo_main_img = "mimg";
    String bo_sub_img = "simg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    Map sfile = multiRequest.getFileMap();

    List _result = null;
    List _result2 = null;
    List _result3 = null;

    mfile.clear();
    sfile.clear();

    if (files.get(bo_main_img) != null) {
      mfile.put(bo_main_img, files.get(bo_main_img));
      files.remove(bo_main_img);
    }

    if (files.get(bo_sub_img) != null) {
      sfile.put(bo_sub_img, files.get(bo_sub_img));
      files.remove(bo_sub_img);
    }

    if (!mfile.isEmpty()) {
      if (("".equals(_mainFileId)) || (_mainFileId == null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
        boardVO.setBo_main_img(_mainFileId);
      } else if ((!"".equals(_mainFileId)) || (_mainFileId != null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, _mainFileId, "");
        for (int i = 0; i < _result.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }

    }

    if (!sfile.isEmpty())
    {
      if (("".equals(_subFileId)) || (_subFileId == null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
        _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
        boardVO.setBo_sub_img(_subFileId);
      } else if ((!"".equals(_subFileId)) || (_subFileId != null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, _subFileId, "");
        for (int i = 0; i < _result2.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result2);
      }

    }

    if (!files.isEmpty())
    {
      if (("".equals(_atchFileId)) || (_atchFileId == null)) {
        _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");

        for (int i = 0; i < _result3.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);

        boardVO.setBo_atch_file(_atchFileId);
      } else if ((!"".equals(_atchFileId)) || (_atchFileId != null))
      {
        NaksinuriOriginalFileVO fvo = new NaksinuriOriginalFileVO();
        fvo.setAtchFileId(_atchFileId);
        int cnt = this.fileMngService.getMaxFileSN_naksinuri_original(fvo);
        _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", cnt, _atchFileId, "");

        for (int i = 0; i < _result3.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result3);
      }

    }

    model.addAttribute("table", table);
    model.addAttribute("column", column);

    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    String bo_content = request.getParameter("bo_content");

    if (bo_name != null) {
      bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
      boardVO.setBo_name(bo_name);
    }

    if (bo_pass != null) {
      bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
      boardVO.setBo_pass(bo_pass);
    }

    if (bo_subject != null) {
      bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
      boardVO.setBo_subject(bo_subject);
    }
    
    if (bo_content != null) {
    	bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
      boardVO.setBo_content(bo_content);
    }
    boardVO.setBo_sn(mEgovStringUtil.removeScrtyKey(boardVO.getBo_sn()));    
    this.service.update_data(boardVO);

    return "redirect:/" + column + "/" + table + "/list.do";
  }

  @RequestMapping({"/sosig/congress/update_data.do"})
  public String user_update2(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
	  
	EgovStringUtil egovStringUtil = new EgovStringUtil();
	egovStringUtil.AES128Util(); 	
	
    boardVO.setBo_type("congress");

    String _mainFileId = boardVO.getBo_main_img();

    String bo_main_img = "mimg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();

    List _result = null;

    mfile.clear();

    if (files.get(bo_main_img) != null) {
      mfile.put(bo_main_img, files.get(bo_main_img));
      files.remove(bo_main_img);
    }

    if (!mfile.isEmpty()) {
      if (("".equals(_mainFileId)) || (_mainFileId == null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
        boardVO.setBo_main_img(_mainFileId);
      } else if ((!"".equals(_mainFileId)) || (_mainFileId != null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, _mainFileId, "");
        for (int i = 0; i < _result.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }

    }
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    String bo_content = request.getParameter("bo_content");
    

    if (bo_name != null) {
      bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
      boardVO.setBo_name(bo_name);
    }

    if (bo_pass != null) {
      bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
      boardVO.setBo_pass(bo_pass);
    }

    if (bo_subject != null) {
      bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
      boardVO.setBo_subject(bo_subject);
    }
    
    if (bo_content != null) {
    	bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
      boardVO.setBo_content(bo_content);
    }

    boardVO.setBo_sn(egovStringUtil.removeScrtyKey(boardVO.getBo_sn()));
    this.service.update_data(boardVO);

    return "redirect:/sosig/congress/list.do";
  }

  @RequestMapping({"/{column}/{table}/m/insert_data.do"})
  public String user_insert_mobile(MultipartHttpServletRequest multiRequest, @PathVariable("table") String table, @PathVariable("column") String column, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    String mimg = "mimg";
    String simg = "simg";
    String _mainFileId = "";
    String _subFileId = "";
    String _atchFileId = "";
    List _result = null;
    List _result2 = null;
    List _result3 = null;
    boardVO.setBo_type(table);

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

    if (!mfile.isEmpty())
    {
      _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      for (int i = 0; i < _result.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
    }

    if (!sfile.isEmpty())
    {
      _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
      for (int i = 0; i < _result2.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
    }

    if (!files.isEmpty())
    {
      _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < _result3.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);
    }

    boardVO.setBo_main_img(_mainFileId);
    boardVO.setBo_sub_img(_subFileId);
    boardVO.setBo_atch_file(_atchFileId);
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    if (bo_name != null) {
      bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
      boardVO.setBo_name(bo_name);
    }

    if (bo_pass != null) {
      bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
      boardVO.setBo_pass(bo_pass);
    }

    if (bo_subject != null) {
      bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
      boardVO.setBo_subject(bo_subject);
    }
    
    //게시물 승인 제도 추가 - 2019.07.31
    if( (column.equals("share") && table.equals("travel"))	//낚시여행기 
    	||(column.equals("share") && table.equals("usage")) //낚시용품 사용기
    	||(column.equals("share") && table.equals("column")) //낚시칼럼
    ) {
    	boardVO.setBo_trash("3");
    }

    this.service.insert_data(boardVO);

    return "redirect:/" + column + "/" + table + "/m/list.do";
  }

  @RequestMapping({"/{column}/{table}/m/update_data.do"})
  public String user_update_mobile(MultipartHttpServletRequest multiRequest, @PathVariable("table") String table, @PathVariable("column") String column, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    boardVO.setBo_type(table);

    String _mainFileId = boardVO.getBo_main_img();
    String _subFileId = boardVO.getBo_sub_img();
    String _atchFileId = boardVO.getBo_atch_file();

    String bo_main_img = "mimg";
    String bo_sub_img = "simg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    Map sfile = multiRequest.getFileMap();

    List _result = null;
    List _result2 = null;
    List _result3 = null;

    mfile.clear();
    sfile.clear();

    if (files.get(bo_main_img) != null) {
      mfile.put(bo_main_img, files.get(bo_main_img));
      files.remove(bo_main_img);
    }

    if (files.get(bo_sub_img) != null) {
      sfile.put(bo_sub_img, files.get(bo_sub_img));
      files.remove(bo_sub_img);
    }

    if (!mfile.isEmpty()) {
      if (("".equals(_mainFileId)) || (_mainFileId == null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        for (int i = 0; i < _result.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
        boardVO.setBo_main_img(_mainFileId);
      } else if ((!"".equals(_mainFileId)) || (_mainFileId != null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, _mainFileId, "");
        for (int i = 0; i < _result.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }

    }

    if (!sfile.isEmpty()) {
      if (("".equals(_subFileId)) || (_subFileId == null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
        for (int i = 0; i < _result2.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
        boardVO.setBo_main_img(_subFileId);
      } else if ((!"".equals(_subFileId)) || (_subFileId != null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, _subFileId, "");
        for (int i = 0; i < _result2.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result2.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result2);
      }

    }

    if (!files.isEmpty())
    {
      if (("".equals(_atchFileId)) || (_atchFileId == null)) {
        _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
        for (int i = 0; i < _result3.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result3);
        boardVO.setBo_atch_file(_atchFileId);
      } else if ((!"".equals(_atchFileId)) || (_atchFileId != null))
      {
        NaksinuriOriginalFileVO fvo = new NaksinuriOriginalFileVO();
        fvo.setAtchFileId(_atchFileId);
        int cnt = this.fileMngService.getMaxFileSN_naksinuri_original(fvo);
        _result3 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", cnt, _atchFileId, "");

        for (int i = 0; i < _result3.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result3.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }

        this.fileMngService.updateFileInfs_naksinuri_original(_result3);
      }

    }

    model.addAttribute("table", table);
    model.addAttribute("column", column);
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    String bo_content = request.getParameter("bo_content");

    if (bo_name != null) {
      bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
      boardVO.setBo_name(bo_name);
    }

    if (bo_pass != null) {
      bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
      boardVO.setBo_pass(bo_pass);
    }

    if (bo_subject != null) {
      bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
      boardVO.setBo_subject(bo_subject);
    }
    
    if (bo_content != null) {
    	bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
      boardVO.setBo_content(bo_content);
    }

    this.service.update_data(boardVO);

    return "redirect:/" + column + "/" + table + "/m/list.do";
  }

  @RequestMapping({"/{column}/{table}/delete_passcheck.do"})
  public String delete_travel_passcheck(@ModelAttribute("boardVO") BoardVO boardVO, @PathVariable("table") String table, @PathVariable("column") String column, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String bo_pass = request.getParameter("bo_pass");
    String bo_sn = request.getParameter("bo_sn");
    BoardVO spass = this.service.bopass_find(boardVO);

    String rpass = spass.getBo_pass();

    if (!rpass.equals(bo_pass)) {
      model.addAttribute("fail", "1");
    }
    else {
    	//[보안점검수정][START]####################################################
    	//this.service.delete_boardlist(boardVO);
    	if( (column.equals("share") && table.equals("travel"))	//낚시여행기 
    	    	||(column.equals("share") && table.equals("usage")) //낚시용품 사용기
    	    	||(column.equals("share") && table.equals("column")) //낚시칼럼
    	    ) {
    		this.service.delete_boardlist(boardVO);
    	} else {
    		model.addAttribute("fail", "2");
    	}
    	//[보안점검수정][END]####################################################
    }
    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("table", table);
    model.addAttribute("column", column);

    return "naksinuri_original/naksinuri/skin/delete_passcheck";
  }

  @RequestMapping({"/{column}/{table}/update_passcheck.do"})
  public String travel_update_passcheck(@ModelAttribute("boardVO") BoardVO boardVO, @PathVariable("table") String table, @PathVariable("column") String column, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	
    String bo_pass = request.getParameter("bo_pass");
    String bo_sn = request.getParameter("bo_sn");
    BoardVO spass = this.service.bopass_find(boardVO);

    String rpass = spass.getBo_pass();

    if (!rpass.equals(bo_pass)) {
      model.addAttribute("fail", "1");
    }
    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("table", table);
    model.addAttribute("column", column);
    model.addAttribute("pass_chk", Integer.valueOf(1));
    model.addAttribute("bo_pass", bo_pass);
    return "naksinuri_original/naksinuri/skin/update_passcheck";
  }

  @RequestMapping({"/{column}/{table}/m/update_passcheck.do"})
  public String travel_update_passcheck_mobile(@ModelAttribute("boardVO") BoardVO boardVO, @PathVariable("table") String table, @PathVariable("column") String column, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String bo_pass = request.getParameter("bo_pass");
    String bo_sn = request.getParameter("bo_sn");
    BoardVO spass = this.service.bopass_find(boardVO);

    String rpass = spass.getBo_pass();

    if (!rpass.equals(bo_pass)) {
      model.addAttribute("fail", "1");
    }
    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("table", table);
    model.addAttribute("column", column);
    model.addAttribute("bo_pass", bo_pass);
    return "naksinuri_original/naksinuri/skin/m/update_passcheck";
  }

  @RequestMapping({"/{column}/{table}/m/delete_passcheck.do"})
  public String delete_travel_passcheck_mobile(@ModelAttribute("boardVO") BoardVO boardVO, @PathVariable("table") String table, @PathVariable("column") String column, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String bo_pass = request.getParameter("bo_pass");
    String bo_sn = request.getParameter("bo_sn");
    BoardVO spass = this.service.bopass_find(boardVO);

    String rpass = spass.getBo_pass();

    if (!rpass.equals(bo_pass)) {
      model.addAttribute("fail", "1");
    }
    else {
      this.service.delete_boardlist(boardVO);
    }
    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("table", table);
    model.addAttribute("column", column);

    return "naksinuri_original/naksinuri/skin/m/delete_passcheck";
  }

 /* @RequestMapping({"/sosig/news/list.do"})
  public String news_list(@ModelAttribute("newsVO") NaksinuriNewsVO newsVO, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - news_list : 낚시소식 - 낚시뉴스 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    String[] data;
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    newsVO.setPageUnit(10);
    newsVO.setPageInfo(model);

    newsVO.setPageInfo(model);
    newsVO.setPageUnit(newsVO.getPageUnit());

    staticVO.setBo_name("낚시뉴스");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("news");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("fishing_sosig");
    staticVO.setCategory_group_name("낚시소식");
    staticVO.setPath_type("web");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    model.addAttribute("searchType", newsVO.getSearchType());
    model.addAttribute("searchText", newsVO.getSearchText());
    List list = this.newsService.getNewsList(newsVO);

    if (list.size() > 0)
      newsVO.setTotalPage(((NaksinuriNewsVO)list.get(0)).getTot_cnt());
    else {
      newsVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriNewsVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/sosig/news_list";
  }*/

  @RequestMapping({"/sosig/news/m/list.do"})
  public String news_list_mobile(@ModelAttribute("newsVO") NaksinuriNewsVO newsVO, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - news_list_mobile : 낚시소식(모바일) - 낚시뉴스 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    String[] data;
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    newsVO.setPageUnit(10);
    newsVO.setPageInfo(model);

    newsVO.setPageInfo(model);
    newsVO.setPageUnit(newsVO.getPageUnit());

    staticVO.setBo_name("낚시뉴스");
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("news");
    staticVO.setPath(request.getRequestURL().toString());

    staticVO.setCategory_group_type("fishing_sosig");
    staticVO.setCategory_group_name("낚시소식");
    staticVO.setPath_type("mobile");
    staticVO.setCategory_name("");
    staticVO.setCategory_type("");

    this.service_statistic.get_statisticInfo(staticVO,request);

    List list = this.newsService.getNewsList(newsVO);

    if (list.size() > 0)
      newsVO.setTotalPage(((NaksinuriNewsVO)list.get(0)).getTot_cnt());
    else {
      newsVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriNewsVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/sosig/m/news_list";
  }

  @RequestMapping({"/info/angling/list.do"})
  public String angling_list(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, String category, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - angling_list : 낚시정보 - 조황정보 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    anglingVO.setPageUnit(10);
    anglingVO.setPageInfo(model);

    if (anglingVO.getCategory() == null)
      category = "";
    else {
      category = anglingVO.getCategory();
    }

    staticVO.setBo_name("조황정보");

    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }

    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("angling");
    staticVO.setPath(request.getRequestURL().toString());

    String category_type = "ftv_live";
    String category_name = "FTV 실시간 조황정보";
    staticVO.setCategory_group_type("fishing_info");
    staticVO.setCategory_group_name("낚시정보");
    staticVO.setCategory_type(category_type);
    staticVO.setCategory_name(category_name);
    staticVO.setPath_type("web");

    this.service_statistic.get_statisticInfo(staticVO,request);

    model.addAttribute("category", category);
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());
    anglingVO.setCategory(category);

    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAnglingList(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/info/angling_list";
  }

  @RequestMapping({"/info/angling/riverlist.do"})
  public String angling_list_river(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, String category, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    anglingVO.setPageUnit(10);
    anglingVO.setPageInfo(model);

    model.addAttribute("category", category);
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());
    anglingVO.setCategory(category);

    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAng_river_list(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/info/angling_list";
  }

  @RequestMapping({"/info/angling/sealist.do"})
  public String angling_list_sea(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, String category, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    anglingVO.setPageUnit(10);
    anglingVO.setPageInfo(model);

    model.addAttribute("category", category);
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());
    anglingVO.setCategory(category);

    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAng_sea_list(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/info/angling_list";
  }

  @RequestMapping({"/info/angling/lurelist.do"})
  public String angling_list_lure(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, String category, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    anglingVO.setPageUnit(10);
    anglingVO.setPageInfo(model);

    model.addAttribute("category", category);
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());
    anglingVO.setCategory(category);

    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAng_lure_list(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/info/angling_list";
  }

  @RequestMapping({"/info/angling/m/list.do"})
  public String angling_list_mobile(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, String category, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("NaksiBoardController - angling_list_mobile : 낚시정보(모바일) - 조황정보 ");
    LOGGER.debug("- 파라미터 목록 --------------");
    Enumeration e = request.getParameterNames();
    while (e.hasMoreElements()) {
      String key = (String)e.nextElement();
      String[] data = request.getParameterValues(key);
      if (data != null) {
        for (String eachdata : data) {
          LOGGER.debug(key + " = " + eachdata);
        }
      }
    }
    LOGGER.debug("------------------------ ");

    anglingVO.setPageUnit(10);
    anglingVO.setPageInfo(model);

    if (anglingVO.getCategory() == null)
      category = "";
    else {
      category = anglingVO.getCategory();
    }

    String category_type = "ftv_live";
    String category_name = "FTV 실시간 조황정보";
    staticVO.setCategory_group_type("fishing_info");
    staticVO.setCategory_group_name("낚시정보");
    staticVO.setCategory_type(category_type);
    staticVO.setCategory_name(category_name);
    staticVO.setPath_type("mobile");

    staticVO.setBo_name("조황정보");
    staticVO.setPath(request.getRequestURL().toString());
    if (getClientOS(request) != "")
      staticVO.setStatistic_os(getClientOS(request));
    else {
      staticVO.setStatistic_os(System.getProperty("os.name"));
    }
    staticVO.setBrowser(getClientBrowser(request));
    staticVO.setClient_ip(getClientIpAddr(request));
    staticVO.setBo_type("angling");
    this.service_statistic.get_statisticInfo(staticVO,request);

    model.addAttribute("category", category);
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());
    anglingVO.setCategory(category);

    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());

    List list = this.anglingService.getAnglingList(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/info/m/angling_list";
  }

  @RequestMapping({"/info/angling/m/riverlist.do"})
  public String angling_list_river_mobile(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, String category, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    anglingVO.setPageUnit(10);
    anglingVO.setPageInfo(model);

    model.addAttribute("category", category);
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());
    anglingVO.setCategory(category);

    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAng_river_list(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/info/m/angling_list";
  }

  @RequestMapping({"/info/angling/m/sealist.do"})
  public String angling_list_sea_mobile(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, String category, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    anglingVO.setPageUnit(10);
    anglingVO.setPageInfo(model);

    model.addAttribute("category", category);
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());
    anglingVO.setCategory(category);

    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAng_sea_list(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/info/m/angling_list";
  }

  @RequestMapping({"/info/angling/m/lurelist.do"})
  public String angling_list_lure_mobile(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, String category, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    anglingVO.setPageUnit(10);
    anglingVO.setPageInfo(model);

    model.addAttribute("category", category);
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());
    anglingVO.setCategory(category);

    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAng_lure_list(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/info/m/angling_list";
  }

  @RequestMapping({"/sosig/congress/list_show.do"})
  public String list_show(@ModelAttribute("congressVO") NaksiCongressMbrVO congressVO, NaksinuriStatisticVO staticVO, 
		  @ModelAttribute("boardVO") BoardVO boardVO, RedirectAttributes redirectAttributes, ModelMap model, HttpServletRequest request, HttpServletResponse response
		  ,@RequestParam(value="bo_sn") String bo_sn
		  ) throws Exception 
  {
    LOGGER.debug("NaksiBoardController - list_show : 낚시소식 - 대회-등록내역조회-신청자현황보기");
    NaksinuriUtils.scanParameters(request);//파라미터 출력

    congressVO.setPageUnit(10);
    congressVO.setPageInfo(model);
    
    BoardVO info = this.service.board_findCorp(boardVO);
    if( boardVO==null || info==null 
	|| boardVO.getBo_email()==null || info.getBo_email()==null || !info.getBo_email().equals(boardVO.getBo_email()) 
	|| boardVO.getRegit_num()==null || info.getRegit_num()==null || !info.getRegit_num().equals(boardVO.getRegit_num()) ) {
    	Map<String, Object> postMap = new HashMap<String,Object>();
    	postMap.put("message", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
    	redirectAttributes.addFlashAttribute("alert_data",postMap);
    	LOGGER.debug("비 정상적인 접근");
    	return "redirect:/index.do";
    }
    

    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("searchStatus", congressVO.getSearchStatus());
    model.addAttribute("searchType", congressVO.getSearchType());
    model.addAttribute("searchText", congressVO.getSearchText());
    List list = this.service.getmbr_list(congressVO);
    
    if (list.size() > 0) {
      congressVO.setTotalPage(((NaksiCongressMbrVO)list.get(0)).getTot_cnt());
      //model.addAttribute("bo_sn", ((NaksiCongressMbrVO)list.get(0)).getBo_sn());
    } else {
      congressVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksiCongressMbrVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/sosig/popwin";
  }
  
  
  @RequestMapping({"/gongmo/event/list_show.do"})
  public String list_show_gongmo(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response
		  ) throws Exception 
  {
    LOGGER.debug("NaksiBoardController - list_show : 낚시안전 콘텐츠 공모전 - 이벤트 - 댓글이벤트 참여자 리스트 보기");

    List<NaksinuriEventVO> comment_list = eventService.select_event_comment(eventVO); 
    if(comment_list.size() > 0){
		model.addAttribute("comment_list", comment_list);
	}
    model.addAttribute("total_count", comment_list.size());
    model.addAttribute("evn_no", eventVO.getEvn_no());
    
    return "naksinuri_original/naksinuri/admin/gongmo/popwin";
  }

  

  @RequestMapping({"/info/angling/view.do"})
  public String angling_findCorp(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    this.anglingService.view_update(anglingVO);

    NaksinuriAnglingVO info = this.anglingService.board_findCorp(anglingVO);
    model.addAttribute("info", info);

    List bobo_list = this.anglingService.select_list_bobo(anglingVO);

    if (bobo_list.size() > 0) {
      anglingVO.setTotalPage(((NaksinuriAnglingVO)bobo_list.get(0)).getTot_cnt());
      model.addAttribute("bobo_list", bobo_list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)bobo_list.get(0)).getTot_cnt()));
    }

    NaksinuriAnglingVO next = this.anglingService.select_next(anglingVO);
    NaksinuriAnglingVO prev = this.anglingService.select_prev(anglingVO);
    if (next != null) {
      model.addAttribute("next", next);
    }
    if (prev != null) {
      model.addAttribute("prev", prev);
    }
    return "naksinuri_original/naksinuri/info/angling_view";
  }

  @RequestMapping({"/info/angling/m/view.do"})
  public String angling_findCorp_mobile(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    this.anglingService.view_update(anglingVO);

    NaksinuriAnglingVO info = this.anglingService.board_findCorp(anglingVO);
    model.addAttribute("info", info);

    NaksinuriAnglingVO next = this.anglingService.select_next(anglingVO);
    NaksinuriAnglingVO prev = this.anglingService.select_prev(anglingVO);
    if (next != null) {
      model.addAttribute("next", next);
    }
    if (prev != null) {
      model.addAttribute("prev", prev);
    }
    return "naksinuri_original/naksinuri/info/m/angling_view";
  }

  @RequestMapping(value={"/sosig/congress/fullcal.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public List<BoardVO> getsche(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request) throws Exception
  {
    //List schedule = new ArrayList();
    List<BoardVO> date_list = this.service.date_list(boardVO);

    for (BoardVO map : date_list) {
      HashMap schedule_box = new HashMap();
      schedule_box.put("title", map.getBo_subject());
      schedule_box.put("start", map.getBo_start_dt());
      schedule_box.put("end", map.getBo_end_dt());
    }

    return date_list;
  }

  
  @RequestMapping(value={"/sosig/congress/ajax_upload_excel.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Map<String, Object> ajaxCongressExcelUpload(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, 
  @ModelAttribute("congressOwnVO") NaksiCongressOwnVO congressOwnVO, @ModelAttribute("congressmbrVO") NaksiCongressMbrVO congressmbrVO, 
  BindingResult bindingResult, ModelMap model, MultipartHttpServletRequest request) throws Exception
  {
	  boolean isExist = false;
	  List list = new ArrayList();
	  Map data = new HashMap();
	  
	  String error_num = "0";
	  String msg = "참가자 등록 성공";
	  
	  MultipartFile excelFile = request.getFile("client_excel_f");
      if(excelFile==null || excelFile.isEmpty()){
    	  error_num = "1";
    	  msg = "파일이 선택되지 않았습니다.";
    	  
    	  isExist = true;
      } 
      String fileName = excelFile.getOriginalFilename();
      LOGGER.debug("파일있음! " + fileName);
      int pos = fileName.lastIndexOf( "." );
      String ext = fileName.substring( pos + 1 );
      if(!ext.equals("xls") && !ext.equals("xlsx")) {
    	  error_num = "1";
    	  msg = "엑셀파일만 가능합니다.\n엑셀파일 여부를 다시 확인해주세요.";
    	  
    	  isExist = true;
      }      
      if(!isExist) {
	      File destFile = new File(excelFile.getOriginalFilename());
	      try{
	          excelFile.transferTo(destFile);
	      }catch(IllegalStateException | IOException e){
	    	  error_num = "1";
	    	  msg = "엑셀파일을 업로드 중에 오류가 발생하였습니다.";
	    	  isExist = true;
	      }	      
	      if(!isExist) {
		      //Service 단에서 가져온 코드 
		      ExcelReadOption excelReadOption = new ExcelReadOption();
		      excelReadOption.setFilePath(destFile.getAbsolutePath());
		      excelReadOption.setOutputColumns("A","B","C","D","E","F","G","H","I","J","K");
		      excelReadOption.setStartRow(3);
		      List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption,false);
		      
		      for(Map<String, String> article: excelContent){
		          LOGGER.debug(article.get("A"));
		          LOGGER.debug(article.get("B"));
		          LOGGER.debug(article.get("C"));
		          LOGGER.debug(article.get("D"));
		          LOGGER.debug(article.get("E"));
		          LOGGER.debug(article.get("F"));
		          LOGGER.debug(article.get("G"));
		          LOGGER.debug(article.get("H"));
		          LOGGER.debug(article.get("I"));
		          LOGGER.debug(article.get("J"));
		          LOGGER.debug(article.get("K")==null?"-":article.get("K"));
		      } 
		      
		      data.put("lists", excelContent);
	      }
	      try {
	    	  destFile.delete();
	      } catch(Exception e) {
	    	  LOGGER.debug("[fail delete] "+e.toString());
	      }  
      }
	  NaksinuriUtils.scanParameters(request);
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
	  
	  
	  list.add(data);
	  return data;
  }
  
  @RequestMapping({"/congressMbrExcelDownload.do"})
  public View listExcelDownload(@RequestParam("excel_type") String excel_type, HttpServletRequest req, HttpServletResponse resp, BoardVO boardVO, NaksiCongressMbrVO congressVO, Model model)
    throws Exception
  {
	  if(excel_type!=null) {
		  BoardVO info = this.service.congress_findCorp(boardVO);
		  NaksiCongressOwnVO mNaksiCongressOwnVO = null;
		  List list = null;
		  if(excel_type.equals("1")) {
			  NaksiCongressOwnVO congressOwnVO = new NaksiCongressOwnVO();
			  congressOwnVO.setBo_sn(Integer.parseInt(congressVO.getBo_sn()));
			  list = this.service.getmbr_own_all_list(congressOwnVO);
			  model.addAttribute("owner", "Y");
		  } else {
			  list = this.service.getmbr_all_list(congressVO);
			  model.addAttribute("owner", "N");
		  }		  
		  String searchStatus = congressVO.getSearchStatus();
		  if(searchStatus==null || searchStatus.length()==0) {
			  model.addAttribute("searchStatus", "전체");
		  } else {
			  model.addAttribute("searchStatus", searchStatus);
		  }		
		  model.addAttribute("list", list);
		  model.addAttribute("info", info);		  
	  }
	  return new CongressMbrExcel();
  }
  @RequestMapping({"/congressMbrGongmoExcelDownload.do"})
  public View gongmolistExcelDownload(HttpServletRequest req, HttpServletResponse resp, BoardVO boardVO, NaksiCongressMbrVO congressVO, Model model)
    throws Exception
  {
		  BoardVO info = this.service.congress_findCorp(boardVO);
		  NaksiCongressOwnVO mNaksiCongressOwnVO = null;
		  List list = this.service.getmbr_all_list(congressVO);
		  String searchStatus = congressVO.getSearchStatus();
		  if(searchStatus==null || searchStatus.length()==0) {
			  model.addAttribute("searchStatus", "전체");
		  } else {
			  model.addAttribute("searchStatus", searchStatus);
		  }		
		  model.addAttribute("list", list);
		  model.addAttribute("info", info);		  
	  
	  return new CongressMbrGongmoExcel();
  }
  @RequestMapping({"/gongmoEeventExcelDownload.do"})
  public Object listExcelDownload_gongmo(HttpServletRequest req, HttpServletResponse resp, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, Model model)
    throws Exception
  {
	  
	  LOGGER.debug("evn_no >>>>  "+ eventVO.getEvn_no());
	  
	  NaksinuriEventVO info = eventService.event_gongmo_findCorp(eventVO);
	  if(info == null) {
		  return "redirect:/incorrect_url.do";
	  }
	  List<NaksinuriEventVO> comment_list = eventService.select_event_comment(eventVO);
	  
	  model.addAttribute("info", info); 
	  model.addAttribute("list", comment_list);
	  
	  return new GongmoEventExcel();
  }
  
  @RequestMapping(value={"/sosig/congress/conditioncal.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<BoardVO> getConditionsche(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request) throws Exception {
    LOGGER.debug("NaksiBoardController.java : /sosig/congress/conditioncal.do 낚시대회 조건 조회 ");
    LOGGER.debug("조건값 : " + boardVO.getSearch_str());
    List date_list = this.service.date_list_condition(boardVO);
    return date_list;
  }
  
  //공모전 - 참가신청관련 - 동의하기
  @RequestMapping(value={"/gongmo/gongmo/check.do","/gongmo/gongmo/m/check.do"})
  public String gongmo_check( @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	  String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	  String return_page = "naksinuri_original/naksinuri/gongmo/gongmo_check";
	  if(path.contains("/m/")) {
		  return_page = "naksinuri_original/naksinuri/gongmo/m/gongmo_check";
		  staticVO.setPath_type("mobile");
	  } else {
		  staticVO.setPath_type("web");
	  }
	  
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
	  staticVO.setCategory_group_type("gongmo");
	  staticVO.setCategory_group_name("접수 및 접수확인");
	  staticVO.setCategory_type("check");
	  staticVO.setCategory_name("공모전 접수/신청");	  
	  this.service_statistic.get_statisticInfo(staticVO,request);
	  
	  
	  boardVO.setBo_type("gongmo");
	  List list = this.service.gongmo_all_list(boardVO);
	  model.addAttribute("list", list);
	  if(boardVO.getBo_sn()!=null) {
		  BoardVO info = this.service.board_findCorp(boardVO);
		  model.addAttribute("info", info);
	  }
    return return_page;
  }
  
  //공모전 - 참가신청관련 - 참가신청서 양식
  @RequestMapping(value={"/gongmo/gongmo/mbrwrite.do","/gongmo/gongmo/m/mbrwrite.do"})
  public String gongmo_mbrwrite(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	  String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	  String return_page = "naksinuri_original/naksinuri/gongmo/gongmo_mbrwrite";
	  if(path.contains("/m/")) {
		  return_page = "naksinuri_original/naksinuri/gongmo/m/gongmo_mbrwrite";
	  } 
	  if(boardVO.getBo_sn()==null || boardVO.getBo_sn().length()==0) {
		  return "redirect:/incorrect_url.do";
	  }
	  boardVO.setBo_type("gongmo");
	  List list = this.service.gongmo_all_list(boardVO);
	  model.addAttribute("list", list);
	  if(boardVO.getBo_sn()!=null) {
		  BoardVO info = this.service.board_findCorp(boardVO);
		  model.addAttribute("info", info);
	  }
    return return_page;
  }
  
  //공모전 - 참가신청관련 - 참가신청서 수정하기
  @RequestMapping(value={"/gongmo/gongmo/mbrmodify.do","/gongmo/gongmo/m/mbrmodify.do"})
  public String gongmo_mbrmodify(@ModelAttribute("boardVO") BoardVO boardVO, @ModelAttribute("congressmbrVO") NaksiCongressMbrVO congressVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	  String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	  String return_page = "naksinuri_original/naksinuri/gongmo/gongmo_mbrwrite";
	  if(path.contains("/m/")) {
		  return_page = "naksinuri_original/naksinuri/gongmo/m/gongmo_mbrwrite";
	  } 
	  if(boardVO.getBo_sn()==null || boardVO.getBo_sn().length()==0 || congressVO==null || congressVO.getBo_sn().length()==0) {
		  return "redirect:/incorrect_url.do";
	  }
	  boardVO.setBo_type("gongmo");
	  List list = this.service.gongmo_all_list(boardVO);
	  model.addAttribute("list", list);
	  if(boardVO.getBo_sn()!=null) {
		  BoardVO info = this.service.board_findCorp(boardVO);
		  model.addAttribute("info", info);
	  }
	  if(congressVO.getBo_sn()!=null) {
		  NaksiCongressMbrVO info_gongmo = this.service.mbr_detail(congressVO);
		  model.addAttribute("info_gongmo", info_gongmo);
	  }
    return return_page;
  }
  
  //공모전 - 참가신청관련 - 참가신청서 등록
  @RequestMapping(value={"/gongmo/gongmo/mbrinsert_data.do","/gongmo/gongmo/m/mbrinsert_data.do"})
  public String gongmombr_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, @ModelAttribute("congressmbrVO") NaksiCongressMbrVO congressmbrVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
	String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	String return_page = "redirect:/gongmo/gongmo/check.do";
	if(path.contains("/m/")) {
		return_page = "redirect:/gongmo/gongmo/m/check.do";
	} 
	if (congressmbrVO.getBo_sn().isEmpty()) {
		return_page = "redirect:/incorrect_url.do";
	}
	boardVO.setBo_sn(congressmbrVO.getBo_sn());
	boardVO.setBo_type("congress");
	BoardVO info = this.service.congress_findCorp(boardVO);
	
	congressmbrVO.setRepre_title(info.getBo_subject());//대회명
	congressmbrVO.setRepre_writer(info.getBo_name());//주최자
	congressmbrVO.setMbr_own_name(congressmbrVO.getMbr_name());//신청자명=참가자명
	congressmbrVO.setMbr_status("접수대기");
	
	//그룹코드
  	Date today = new Date();
  	SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
  	String mbr_group = date.format(today) + getRandomString(4);
  	congressmbrVO.setMbr_group(mbr_group);
	
	String _atchFileId = "";
	List _result = null;
	Map files = multiRequest.getFileMap();
	if (!files.isEmpty())
    {
      _result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < _result.size(); i++) {
    	  NaksinuriOriginalFileVO fileVO = (NaksinuriOriginalFileVO)_result.get(i);
    	  if((fileVO.getFileExtsn().equalsIgnoreCase("jpge") || fileVO.getFileExtsn().equalsIgnoreCase("jpg")
    			  || fileVO.getFileExtsn().equalsIgnoreCase("wmv") || fileVO.getFileExtsn().equalsIgnoreCase("mp4")
    			  || fileVO.getFileExtsn().equalsIgnoreCase("avi") || fileVO.getFileExtsn().equalsIgnoreCase("mov")
    			  )) { //) && Integer.parseInt(fileVO.getFileMg()) < 32505856) { //용량 제한 해제
    		  if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
        		  LOGGER.debug("파일에러");
    			  return "redirect:/error/ext/warn.do";
        	  }  
    	  } else {
    		  LOGGER.debug("허용되지 않는 확장자");
    		  return "redirect:/error/ext/warn.do";
    	  }
      }
      _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
    }
	congressmbrVO.setBo_atch_file(_atchFileId);
	
	this.service.congressmbr_insert_data(congressmbrVO);
	
    /*
    if(info.getIs_autosend_sms_y().equals("1")) {
        //신청자 한테 문자 발송
		smsService.sendToCongressCustomer(
			  congressmbrVO.getMbr_hp(),//받는사람 번호
			  info.getBo_subject(),//대회명
			  "",
			  "",
			  "",//입금계좌
			  "",//은행명
			  info.getBo_phone(),//담당자 연락처
			  0,//총 참가인원
			  getClientIpAddr(request)//발송시도시 ip
			  );
	    
	    //담당자 한테 문자 발송
	    smsService.sendToCongressAdmin(info.getBo_phone(), congressmbrVO.getRepre_title(), congressmbrVO.getMbr_name(), 0, getClientIpAddr(request));
	}
	*/
    return return_page;
  }
  
  
  //공모전 - 참가신청관련 - 참가신청서 수정
  @RequestMapping(value={"/gongmo/gongmo/mbrupdate_data.do","/gongmo/gongmo/m/mbrupdate_data.do"})
  public String gongmombr_update(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, @ModelAttribute("congressmbrVO") NaksiCongressMbrVO congressmbrVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
	String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	String return_page = "redirect:/gongmo/gongmo/search.do";
	if(path.contains("/m/")) {
		return_page = "redirect:/gongmo/gongmo/m/search.do";
	} 
	if (congressmbrVO.getBo_sn().isEmpty()) {
		return_page = "redirect:/incorrect_url.do";
	}
	//BoardVO info = this.service.congress_findCorp(boardVO);
	congressmbrVO.setMbr_own_name(congressmbrVO.getMbr_name());//신청자명=참가자명
	
	String ori_atchFileId = congressmbrVO.getBo_atch_file();
	String _atchFileId = "";
	List _result = null;
	Map files = multiRequest.getFileMap();
	boolean isExistFile = false;
	if (!files.isEmpty())
    {
      _result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < _result.size(); i++) {
    	  NaksinuriOriginalFileVO fileVO = (NaksinuriOriginalFileVO)_result.get(i);
    	  if((fileVO.getFileExtsn().equalsIgnoreCase("jpge") || fileVO.getFileExtsn().equalsIgnoreCase("jpg")
    			  || fileVO.getFileExtsn().equalsIgnoreCase("wmv") || fileVO.getFileExtsn().equalsIgnoreCase("mp4")
    			  || fileVO.getFileExtsn().equalsIgnoreCase("avi") || fileVO.getFileExtsn().equalsIgnoreCase("mov")
    			  )) { //) && Integer.parseInt(fileVO.getFileMg()) < 32505856) { //용량 제한 해제
    		  if (((NaksinuriOriginalFileVO)_result.get(i)).atchFileId.equals("ext_error")) {
        		  LOGGER.debug("파일에러");
    			  return "redirect:/error/ext/warn.do";
        	  }  
    		  isExistFile = true;
    	  } else {
    		  LOGGER.debug("허용되지 않는 확장자");
    		  return "redirect:/error/ext/warn.do";
    	  }
      }  
      _atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
      if(_atchFileId!=null && _atchFileId.length()!=0) {
	      NaksinuriOriginalFileVO delfileVO = new NaksinuriOriginalFileVO();
	      delfileVO.setAtchFileId(ori_atchFileId);
	      delfileVO.setFileSn("0");
	      this.fileMngService.deleteFileInf_naksinuri_original(delfileVO);
      }
    }
	congressmbrVO.setBo_atch_file(_atchFileId);
	
	this.service.congressmbr_update_data(congressmbrVO);

    return return_page;
  }
  
  //공모전 - 참가신청관련 - 참가자리스트 ( 관리자페이지 와 공통사용 )
  @RequestMapping({"/gongmo/gongmo/list_show.do"})
  public String gongmo_list_show(@ModelAttribute("congressVO") NaksiCongressMbrVO congressVO, NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response
		  ,@RequestParam(value="bo_sn") String bo_sn
		  ) throws Exception 
  {
    LOGGER.debug("NaksiBoardController - gongmo_list_show : 공모전 - 참가자리스트");
    NaksinuriUtils.scanParameters(request);//파라미터 출력

    congressVO.setPageUnit(10);
    congressVO.setPageInfo(model);
    
    model.addAttribute("bo_sn", bo_sn);
    model.addAttribute("searchStatus", congressVO.getSearchStatus());
    model.addAttribute("searchType", congressVO.getSearchType());
    model.addAttribute("searchText", congressVO.getSearchText());
    List list = this.service.getmbr_list(congressVO);
    
    if (list.size() > 0) {
      congressVO.setTotalPage(((NaksiCongressMbrVO)list.get(0)).getTot_cnt());
      //model.addAttribute("bo_sn", ((NaksiCongressMbrVO)list.get(0)).getBo_sn());
    } else {
      congressVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksiCongressMbrVO)list.get(0)).getTot_cnt()));

    }

    return "naksinuri_original/naksinuri/gongmo/popwin";
  }
  
  //공모전 - 참가신청관련 - 참가자리스트 - 상세보기 ( 관리자페이지 와 공통사용 )
  @RequestMapping({"/gongmo/gongmo/mbr_detail.do"})
  public String gongmo_mbr_detail(@ModelAttribute("congressVO") NaksiCongressMbrVO congressVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String idx = request.getParameter("idx");
    String bo_sn = request.getParameter("bo_sn");

    congressVO.setIdx(idx);
    congressVO.setBo_sn(bo_sn);

    NaksiCongressMbrVO info = this.service.mbr_detail(congressVO);
    if(info == null) {
    	return "redirect:/incorrect_url.do";
    }
    
    NaksiCongressOwnVO congressOwnVO = new NaksiCongressOwnVO();
    congressOwnVO.setBo_sn(Integer.parseInt(bo_sn));
    congressOwnVO.setMbr_group(info.getMbr_group());
    
    NaksiCongressOwnVO info_own = this.service.own_detail(congressOwnVO);
    
    model.addAttribute("info", info);
    model.addAttribute("info_own", info_own);

    return "naksinuri_original/naksinuri/gongmo/gongmo_mbrdetail";
  }
  
  //공모전 - 접수확인 - 1단계
  @RequestMapping(value={"/gongmo/gongmo/search.do","/gongmo/gongmo/m/search.do"})
  public String register_gongmosearch(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, NaksinuriStatisticVO staticVO, HttpServletRequest request)
    throws Exception
  {
	  String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	  String return_page = "naksinuri_original/naksinuri/gongmo/gongmo_mbrsearch";
	  if(path.contains("/m/")) {
		  return_page = "naksinuri_original/naksinuri/gongmo/m/gongmo_mbrsearch";
		  staticVO.setPath_type("mobile");
	  } else {
		  staticVO.setPath_type("web");
	  }
	  
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
	  staticVO.setCategory_group_type("gongmo");
	  staticVO.setCategory_group_name("접수 및 접수확인");
	  staticVO.setCategory_type("search");
	  staticVO.setCategory_name("접수 확인");	  
	  this.service_statistic.get_statisticInfo(staticVO,request);
	  
	  
	  boardVO.setBo_type("gongmo");
	  List list = this.service.gongmo_all_list(boardVO);
	  model.addAttribute("list", list);
	  if(boardVO.getBo_sn()!=null) {
		  BoardVO info = this.service.board_findCorp(boardVO);
		  model.addAttribute("info", info);
	  }
	  return return_page;
  }
  
  //공모전 - 접수확인 - 조회
  @RequestMapping(value={"/gongmo/gongmo/mbrconfirm.do","/gongmo/gongmo/m/mbrconfirm.do"})
  public String gongmo_confirm_register(@ModelAttribute("boardVO") BoardVO boardVO, @ModelAttribute("congressVO") NaksiCongressMbrVO congressVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	  String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	  String return_page = "naksinuri_original/naksinuri/gongmo/gongmo_mbrconfirm";
	  if(path.contains("/m/")) {
		  return_page = "naksinuri_original/naksinuri/gongmo/m/gongmo_mbrconfirm";
	  } 
	  BoardVO info = this.service.board_findCorp(boardVO);
	  List list = this.service.mbr_confirm(congressVO);
	  if(congressVO.getBo_sn()==null || congressVO.getBo_sn().length()==0) {
		  return "redirect:/incorrect_url.do";
	  }
	  model.addAttribute("info", info);
	  model.addAttribute("list", list);	  
	  return return_page;
  }
  
  //공모전 - 파일검증
  @RequestMapping(value={"/gongmo/gongmo/ajax_check_upload_file.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Map<String, Object> ajaxCheckUploadFile(MultipartHttpServletRequest multiRequest, SessionStatus status, ModelMap model) 
    throws Exception
  {
	  boolean isPassed = true;
	  //List list = new ArrayList();
	  Map data = new HashMap();
	  
	  MultipartFile multipartFile = multiRequest.getFile("frm_file");
      if(multipartFile==null || multipartFile.isEmpty()){
    	  data.put("error", "1");
    	  data.put("msg", "파일이 선택되지 않았습니다.");
    	  isPassed = false;
      } 
      if(isPassed) {
	      String fileName = multipartFile.getOriginalFilename();
	      Long size = multipartFile.getSize();
	      int pos = fileName.lastIndexOf( "." );
	      String ext = fileName.substring( pos + 1 );
	      if(!ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("jpg")
		  && !ext.equalsIgnoreCase("wmv") && !ext.equalsIgnoreCase("mp4")
		  && !ext.equalsIgnoreCase("avi") && !ext.equalsIgnoreCase("mov")
		  ) {
	    	  data.put("error", "1");
	    	  data.put("msg", "허용되지 않은 파일입니다.\n(jpeg,jpg,wmv,mp4,avi,mov 만 가능합니다.)");
	    	  isPassed = false;
	      }      
	      if(size > 104857600) {
	    	  data.put("error", "1");
	    	  data.put("msg", "100MB를 초과하는 파일은 업로드 할 수 없습니다.");
	    	  isPassed = false;
	      }
      }
      if(isPassed) {
    	  data.put("error", "0");
    	  data.put("msg", "검증완료");
      }
      LOGGER.debug(data.toString());
	  return data;
  }

  
  
  
  //이미지 뷰보기 - 익스에서 .do 파일로 다운로드 되서 별도로 구현 
  @RequestMapping({"/show/image.do"})
  public String show_image(@ModelAttribute("FileVO") NaksinuriOriginalFileVO fvo, ModelMap model)
    throws Exception
  {
	  model.addAttribute("info", fvo);	  
	  return "naksinuri_original/main/show_image";
  }
  
}