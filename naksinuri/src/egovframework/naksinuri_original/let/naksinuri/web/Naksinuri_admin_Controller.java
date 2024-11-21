package egovframework.naksinuri_original.let.naksinuri.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngUtil;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.FishjobReportService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAdminVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAnglingService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAnglingVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriLogsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriLogsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriMainImgVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriUserMainImgService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.naksinuri_original.let.naksinuri.service.ReportVO;
import egovframework.naksinuri_original.let.naksinuri.service.SmsMngrVO;
import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;
import egovframework.naksinuri_original.let.naksinuri.utils.ExcelRead;
import egovframework.naksinuri_original.let.naksinuri.utils.ExcelReadOption;
import egovframework.naksinuri_original.let.naksinuri.utils.NaksinuriUtils;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicUtils;
import egovframework.com.cmm.service.EgovFileScrty;

@Controller
@RequestMapping({"/admin"})
public class Naksinuri_admin_Controller
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Naksinuri_admin_Controller.class);

  @Resource(name="loginService")
  private LoginService loginService;

  @Resource(name="NaksinuriGoneService")
  private NaksinuriGoneService service_gone;
  
  @Resource(name="NaksinuriLogsService")
  private NaksinuriLogsService service_logs;

  @Resource(name="NaksinuriEventService")
  private NaksinuriEventService eventService;

  @Resource(name="NaksinuriPolicyService")
  private NaksinuriPolicyService policyService;

  @Resource(name="NaksinuriNewsService")
  private NaksinuriNewsService newsService;

  @Resource(name="NaksinuriStaticService")
  private NaksinuriStatisticService service_statistic;

  @Resource(name="NaksinuriService")
  private NaksinuriService service;

  @Resource(name="NaksinuriUserMainImgService")
  private NaksinuriUserMainImgService mainimgService;

  @Resource(name="NaksinuriAnglingService")
  private NaksinuriAnglingService anglingService;

  @Resource(name="NaksinuriZisikinService")
  private NaksinuriZisikinService z_service;

  @Resource(name="NaksinuriQnAService")
  private NaksinuriQnAService qna_service;

  @Resource(name="NaksinuriSanupService")
  private NaksinuriSanupService service_sanup;

  @Resource(name="FishjobReportService")
  FishjobReportService service_report;

  @Resource(name="NaksinuriOriginalEgovFileMngUtil")
  private NaksinuriOriginalEgovFileMngUtil fileUtil;

  @Resource(name="NaksinuriOriginalEgovFileMngService")
  private NaksinuriOriginalEgovFileMngService fileMngService;
  
  @Resource(name="NaksinuriSmsService")
  private NaksinuriSmsService smsService;

  @RequestMapping(value={"/getUser_BrowserInfo.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> week(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    String day_type = request.getParameter("day_type");
    LOGGER.debug("getUser_BrowserInfo.do - week : day_type : " + day_type);
    if (day_type.equals("30"))
      day_type = "1 MONTH";
    else {
      day_type = "'" + day_type + "' day";
    }
    staticVO.setSearchType(day_type);
    List getBrowserInfo = this.service_statistic.getBrowserInfo(staticVO);

    return getBrowserInfo;
  }
  @RequestMapping(value={"/getUser_BrowserInfo1.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> day(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception { List getBrowserInfo = this.service_statistic.getBrowserInfoday(staticVO);

    return getBrowserInfo; } 
  @RequestMapping(value={"/getUser_BrowserInfo30.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> month(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    List getBrowserInfo = this.service_statistic.getBrowserInfomonth(staticVO);

    return getBrowserInfo;
  }
  @RequestMapping(value={"/getUser_BrowserInfomyself.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> myself(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));

    List getBrowserInfo = this.service_statistic.getBrowserInfomyself(staticVO);

    return getBrowserInfo;
  }
  @RequestMapping(value={"/getUser_OSInfo.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> getOSweek(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    List getOSInfo = this.service_statistic.getOSInfoweek(staticVO);

    return getOSInfo;
  }
  @RequestMapping(value={"/getUser_OSInfoday.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> getOSday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    List getOSInfo = this.service_statistic.getOSInfday(staticVO);

    return getOSInfo;
  }
  @RequestMapping(value={"/getUser_OSInfomonth.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> getOSmonth(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    List getOSInfo = this.service_statistic.getOSInfomonth(staticVO);

    return getOSInfo;
  }
  @RequestMapping(value={"/getUser_OSInfoself.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> getOSself(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));
    List getOSInfo = this.service_statistic.getOSInfoself(staticVO);

    return getOSInfo;
  }
  @RequestMapping(value={"/get_top10.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> fishjobcnt_perday2(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    String day_type = request.getParameter("day_type");
    LOGGER.debug("get_top10.do - fishjobcnt_perday2 : day_type : " + day_type);
    if (day_type.equals("30"))
      day_type = "1 MONTH";
    else {
      day_type = "'" + day_type + "' day";
    }
    staticVO.setSearchType(day_type);
    List board_top10 = this.service_statistic.get_boardtop10(staticVO);

    return board_top10;
  }

  @RequestMapping(value={"/get_top10_self.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> get_top10self(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    String start = request.getParameter("start");
    String end = request.getParameter("end");

    staticVO.setStart_dt(start);
    staticVO.setEnd_dt(end);

    List board_top10 = this.service_statistic.get_boardtop10_self(staticVO);

    return board_top10;
  }

  @RequestMapping(value={"/get_visitcnt.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public int visit1(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    String day_type = request.getParameter("day_type");
    LOGGER.debug("get_visitcnt.do - visit1 : day_type : " + day_type);
    if (day_type.equals("30"))
      day_type = "1 MONTH";
    else {
      day_type = "'" + day_type + "' day";
    }
    staticVO.setSearchType(day_type);

    NaksinuriStatisticVO visit_cnt = this.service_statistic.get_visitcnt(staticVO);
    return visit_cnt.getCnt();
  }

  @RequestMapping(value={"/get_visitcnt_self.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public int visit_self(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));

    NaksinuriStatisticVO visit_cnt = this.service_statistic.get_visitcnt_myself(staticVO);
    return visit_cnt.getCnt();
  }

  @RequestMapping(value={"/get_pagecnt.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public int pagecnt(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    String day_type = request.getParameter("day_type");
    LOGGER.debug("get_pagecnt.do - pagecnt : day_type : " + day_type);
    if (day_type.equals("30"))
      day_type = "1 MONTH";
    else {
      day_type = "'" + day_type + "' day";
    }
    staticVO.setSearchType(day_type);
    NaksinuriStatisticVO page_cnt = this.service_statistic.get_pagecnt(staticVO);

    return page_cnt.getCnt();
  }

  @RequestMapping(value={"/get_pagecnt_self.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public int pagecntself(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));
    NaksinuriStatisticVO page_cnt = this.service_statistic.get_pagecnt_myself(staticVO);

    return page_cnt.getCnt();
  }

  @RequestMapping(value={"/compare1.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public int compare1(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    String day_type = request.getParameter("day_type");
    staticVO.setHours(Integer.parseInt(day_type));

    NaksinuriStatisticVO compare_views = this.service_statistic.get_compareviews(staticVO);

    return compare_views.getCnt();
  }

  @RequestMapping(value={"/compare1_self.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public int compare1self(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));

    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

    Date FirstDate = format.parse(staticVO.getEnd_dt());
    Date SecondDate = format.parse(staticVO.getStart_dt());

    long calDate = FirstDate.getTime() - SecondDate.getTime();

    long calDateDays = calDate / 86400000L;

    calDateDays = Math.abs(calDateDays);
    String s = String.valueOf(calDateDays);

    staticVO.setDate_time(s);
    NaksinuriStatisticVO compare_views = this.service_statistic.get_compareviews(staticVO);

    return compare_views.getCnt();
  }

  @RequestMapping(value={"/compare2.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public int compare2(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    String day_type = request.getParameter("day_type");
    staticVO.setHours(Integer.parseInt(day_type));

    NaksinuriStatisticVO compare_views2 = this.service_statistic.get_compareviews2(staticVO);

    return compare_views2.getCnt();
  }

  @RequestMapping(value={"/compare2_self.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public int compare2self(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));

    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

    Date FirstDate = format.parse(staticVO.getEnd_dt());
    Date SecondDate = format.parse(staticVO.getStart_dt());

    long calDate = FirstDate.getTime() - SecondDate.getTime();

    long calDateDays = calDate / 86400000L;

    calDateDays = Math.abs(calDateDays);
    String s = String.valueOf(calDateDays);

    staticVO.setDate_time(s);

    NaksinuriStatisticVO compare_views2 = this.service_statistic.get_compareviews2(staticVO);

    return compare_views2.getCnt();
  }

  @RequestMapping(value={"/getInfoURL.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> fishjobcnt_perday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    String day_type = request.getParameter("day_type");
    staticVO.setSearchType(day_type);
    List getfishCntInfo = this.service_statistic.getfishCntInfo(staticVO);

    return getfishCntInfo;
  }
  @RequestMapping(value={"/getLessonURL.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> lessoncnt_perday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    String day_type = request.getParameter("day_type");
    staticVO.setSearchType(day_type);
    List getfishCntInfo = this.service_statistic.getlessonCntInfo(staticVO);

    return getfishCntInfo;
  }
  @RequestMapping(value={"/getShareURL.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> sharecnt_perday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception { String day_type = request.getParameter("day_type");
    staticVO.setSearchType(day_type);
    List getfishCntInfo = this.service_statistic.getshareCntInfo(staticVO);

    return getfishCntInfo; } 
  @RequestMapping(value={"/getSosigURL.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> sosigcnt_perday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    String day_type = request.getParameter("day_type");
    staticVO.setSearchType(day_type);
    List getfishCntInfo = this.service_statistic.getsosigCntInfo(staticVO);

    return getfishCntInfo;
  }
  @RequestMapping(value={"/getPolicyURL.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> policycnt_perday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception { String day_type = request.getParameter("day_type");
    staticVO.setSearchType(day_type);
    List getfishCntInfo = this.service_statistic.getpolicyCntInfo(staticVO);

    return getfishCntInfo; } 
  @RequestMapping(value={"/getSurveyURL.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> surveycnt_perday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    String day_type = request.getParameter("day_type");
    staticVO.setSearchType(day_type);
    List getfishCntInfo = this.service_statistic.getsurveyCntInfo(staticVO);

    return getfishCntInfo;
  }

  @RequestMapping(value={"/getInfoURLself.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> fishjobcnt_perday3(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));

    List getfishCntInfo;
    if (request.getParameter("type").equals("time"))
      getfishCntInfo = this.service_statistic.getfishCntInfoself_time(staticVO);
    else {
      getfishCntInfo = this.service_statistic.getfishCntInfoself(staticVO);
    }
    return getfishCntInfo;
  }
  @RequestMapping(value={"/getLessonURLself.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> lessoncnt_perday3(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));
    List getfishCntInfo;
    if (request.getParameter("type").equals("time"))
      getfishCntInfo = this.service_statistic.getlessonCntInfoself_time(staticVO);
    else {
      getfishCntInfo = this.service_statistic.getlessonCntInfoself(staticVO);
    }

    return getfishCntInfo;
  }
  @RequestMapping(value={"/getShareURLself.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> sharecnt_perday3(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));
    List getfishCntInfo;
    if (request.getParameter("type").equals("time"))
      getfishCntInfo = this.service_statistic.getshareCntInfoself_time(staticVO);
    else {
      getfishCntInfo = this.service_statistic.getshareCntInfoself(staticVO);
    }

    return getfishCntInfo;
  }
  @RequestMapping(value={"/getSosigURLself.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> sosigcnt_perday3(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));
    List getfishCntInfo;
    if (request.getParameter("type").equals("time"))
      getfishCntInfo = this.service_statistic.getsosigCntInfoself_time(staticVO);
    else {
      getfishCntInfo = this.service_statistic.getsosigCntInfoself(staticVO);
    }

    return getfishCntInfo;
  }
  @RequestMapping(value={"/getPolicyURLself.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> policycnt_perday3(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));
    List getfishCntInfo;
    if (request.getParameter("type").equals("time"))
      getfishCntInfo = this.service_statistic.getpolicyCntInfoself_time(staticVO);
    else {
      getfishCntInfo = this.service_statistic.getpolicyCntInfoself(staticVO);
    }

    return getfishCntInfo;
  }
  @RequestMapping(value={"/getSurveyURLself.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> surveycnt_perday3(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, HttpServletRequest request, ModelMap model) throws Exception {
    staticVO.setStart_dt(request.getParameter("start"));
    staticVO.setEnd_dt(request.getParameter("end"));
    List getfishCntInfo;
    if (request.getParameter("type").equals("time"))
      getfishCntInfo = this.service_statistic.getsurveyCntInfoself_time(staticVO);
    else {
      getfishCntInfo = this.service_statistic.getsurveyCntInfoself(staticVO);
    }

    return getfishCntInfo;
  }

  @RequestMapping({"/promotion/plocation/list.do"})
  public String promotion_list(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    GoneVO.setPageInfo(model);
    GoneVO.setPageUnit(GoneVO.getPageUnit());

    List list = this.service_gone.getListGone_admin(GoneVO);

    model.addAttribute("pageinfo", GoneVO);

    if (list.size() > 0)
      GoneVO.setTotalPage(((NaksinuriGoneVO)list.get(0)).getTot_cnt());
    else {
      GoneVO.setTotalPage(0);
    }
    model.addAttribute("sido", GoneVO.getSido());
    model.addAttribute("gugun", GoneVO.getGugun());

    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/promotion/plocation_list";
  }

  @RequestMapping({"/promotion/plocation/trash.do"})
  public String promotion_list_trash(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    GoneVO.setPageInfo(model);
    GoneVO.setPageUnit(GoneVO.getPageUnit());

    List list = this.service_gone.getTrashGone_admin(GoneVO);

    if (list.size() > 0)
      GoneVO.setTotalPage(((NaksinuriGoneVO)list.get(0)).getTot_cnt());
    else {
      GoneVO.setTotalPage(0);
    }
    model.addAttribute("sido", GoneVO.getSido());
    model.addAttribute("gugun", GoneVO.getGugun());

    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)list.get(0)).getTot_cnt()));
      model.addAttribute("pageinfo", GoneVO);
    }
    return "naksinuri_original/naksinuri/admin/promotion/plocation_trash";
  }

  @RequestMapping({"/promotion/plocation/view.do"})
  public String promotion_view(HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug(request.getServletPath());
    return "naksinuri_original/naksinuri/admin/promotion/plocation_view";
  }

  @RequestMapping({"/promotion/plocation/insert_data.do"})
  public String promotion_insert_data(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, HttpServletRequest request) throws Exception
  {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
    String x_name = request.getParameter("x_name");
    String x_rang = request.getParameter("x_rang");
    String x_area = request.getParameter("x_area");
    String x_person = request.getParameter("x_person");
    String x_purpose = request.getParameter("x_purpose");
    String x_related = request.getParameter("x_related");

    x_name = mEgovStringUtil.getHtmlStrCnvr(x_name);
    x_rang = mEgovStringUtil.getHtmlStrCnvr(x_rang);
    x_area = mEgovStringUtil.getHtmlStrCnvr(x_area);
    x_person = mEgovStringUtil.getHtmlStrCnvr(x_person);
    x_purpose = mEgovStringUtil.getHtmlStrCnvr(x_purpose);
    x_related = mEgovStringUtil.getHtmlStrCnvr(x_related);
    
    if(x_area.length() >50){
    	x_area = x_area.substring(0, 49);
    }

    GoneVO.setX_name(x_name);
    GoneVO.setX_rang(x_rang);
    GoneVO.setX_area(x_area);
    GoneVO.setX_person(x_person);
    GoneVO.setX_purpose(x_purpose);
    GoneVO.setX_related(x_related);

    this.service_gone.insert_data(GoneVO);
    return "redirect:/admin/promotion/plocation/list.do";
  }

  @RequestMapping({"/promotion/plocation/update_data.do"})
  public String promotion_update_data(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, HttpServletRequest request) throws Exception
  {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();  
	
    String x_name = request.getParameter("x_name");
    String x_rang = request.getParameter("x_rang");
    String x_area = request.getParameter("x_area");
    String x_person = request.getParameter("x_person");
    String x_purpose = request.getParameter("x_purpose");
    String x_related = request.getParameter("x_related");

    x_name = mEgovStringUtil.getHtmlStrCnvr(x_name);
    x_rang = mEgovStringUtil.getHtmlStrCnvr(x_rang);
    x_area = mEgovStringUtil.getHtmlStrCnvr(x_area);
    x_person = mEgovStringUtil.getHtmlStrCnvr(x_person);
    x_purpose = mEgovStringUtil.getHtmlStrCnvr(x_purpose);
    x_related = mEgovStringUtil.getHtmlStrCnvr(x_related);
    
    if(x_area.length() >50){
    	x_area = x_area.substring(0, 49);
    }

    GoneVO.setX_name(x_name);
    GoneVO.setX_rang(x_rang);
    GoneVO.setX_area(x_area);
    GoneVO.setX_person(x_person);
    GoneVO.setX_purpose(x_purpose);
    GoneVO.setX_related(x_related);

    this.service_gone.update_data(GoneVO);
    return "redirect:/admin/promotion/plocation/list.do";
  }

  @RequestMapping({"/promotion/plocation/promotion_findCorp.do"})
  public String promotion_findCorp(@ModelAttribute("GoneVO") NaksinuriGoneVO GoneVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriGoneVO info = this.service_gone.promotion_findCorp(GoneVO);

    model.addAttribute("info", info);

    return "naksinuri_original/naksinuri/admin/promotion/plocation_view";
  }

  @RequestMapping({"/promotion/plocation/delete_list_p.do"})
  public String deletePromotionArticle2(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("GoneVO") NaksinuriGoneVO GoneVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      GoneVO.setX_sn(strboIds[i]);
      this.service_gone.delete_list(GoneVO);
    }
    return "redirect:/admin/promotion/plocation/trash.do";
  }

  @RequestMapping({"/promotion/plocation/restore_p.do"})
  public String restorePromotionArticle2(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("GoneVO") NaksinuriGoneVO GoneVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      GoneVO.setX_sn(strboIds[i]);
      this.service_gone.restore_plocation(GoneVO);
    }
    return "redirect:/admin/promotion/plocation/trash.do";
  }

  @RequestMapping({"/promotion/plocation/gotrash_list_p.do"})
  public String deletePromotionArticle22(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("GoneVO") NaksinuriGoneVO GoneVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      GoneVO.setX_sn(strboIds[i]);
      this.service_gone.trash_list(GoneVO);
    }
    return "redirect:/admin/promotion/plocation/list.do";
  }

  @RequestMapping({"/info/report/gotrash_list.do"})
  public String report_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("reportVO") ReportVO reportVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      reportVO.setReport_sn(strboIds[i]);

      this.service_report.trash_list(reportVO);
    }
    return "redirect:/admin/info/report/list.do";
  }

  @RequestMapping({"/{column}/{table}/list.do"})
  public String list(@PathVariable("table") String table, @PathVariable("column") String column, String bo_cate, @ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    if ((table.equals("junior")) || (table.equals("gosu")) || (table.equals("class"))) {
      if (boardVO.getBo_cate() == null)
        bo_cate = "민물";
      else {
        bo_cate = boardVO.getBo_cate();
      }
    }

    boardVO.setBo_cate(bo_cate);
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type(table);

    if (table.equals("travel")) {
      boardVO.getBo_start_dt();
    }

    model.addAttribute("bo_cate", bo_cate);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

    //게시물 승인 제도 추가 - 2019.07.31
    List list = this.service.select_list_admin(boardVO);//기존 select_list -> select_list_admin 로 변경 하고 관련 소스 추가
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

    return "naksinuri_original/naksinuri/admin/" + column + "/" + table + "_list";
  }

  @RequestMapping({"/{column}/{table}/trash.do"})
  public String trash_list(@PathVariable("table") String table, @PathVariable("column") String column, String bo_cate, @ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LOGGER.debug("데이터복구관리센터 - " + column + " / " + table + " / trash.do");

    if ((table.equals("junior")) || (table.equals("gosu")) || (table.equals("class"))) {
      if (boardVO.getBo_cate() == null)
        bo_cate = "민물";
      else {
        bo_cate = boardVO.getBo_cate();
      }
    }

    boardVO.setBo_cate(bo_cate);
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type(table);

    model.addAttribute("bo_cate", bo_cate);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

    List list = this.service.trashBoard_list(boardVO);
    List noticlist = this.service.trashNotice_list(boardVO);

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

    return "naksinuri_original/naksinuri/admin/" + column + "/" + table + "_trash";
  }

  @RequestMapping({"/sosig/gosi/list.do"})
  public String gosi_list(String bo_sido, @ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("gosi");

    model.addAttribute("bo_sido", bo_sido);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

    List list = this.service.select_list(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/gosi_list";
  }

  @RequestMapping({"/sosig/congress/list.do"})
  public String congresslist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO,   HttpServletRequest request, HttpServletResponse response) throws Exception {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("congress");

    List list = this.service.congress_list(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    model.addAttribute("bo_start_dt", boardVO.getBo_start_dt());
    model.addAttribute("bo_end_dt", boardVO.getBo_end_dt());

    model.addAttribute("is_entry_y", boardVO.getIs_entry_y());

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
  /*
    NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
    HttpSession session = request.getSession();    
    
    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
    insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
    insertlogsVO.setUser_id(loginVO.getId());
    insertlogsVO.setActions("낚시대회 조회");
    this.service_logs.insertLogs(insertlogsVO);
*/
    return "naksinuri_original/naksinuri/admin/sosig/congress_list";
  }

  @RequestMapping({"/sosig/congress/trash.do"})
  public String congresslist_trash(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("congress");

    List list = this.service.congress_trash(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

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

    return "naksinuri_original/naksinuri/admin/sosig/congress_trash";
  }

  @RequestMapping({"/sosig/congress/endlist.do"})
  public String conendlist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("congress");
    boardVO.setSeachAdmin(true);

    List list = this.service.congress_endlist(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    model.addAttribute("bo_start_dt", boardVO.getBo_start_dt());
    model.addAttribute("bo_end_dt", boardVO.getBo_end_dt());
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

    return "naksinuri_original/naksinuri/admin/sosig/congress_endlist";
  }

  @RequestMapping({"/sosig/congress/endtrash.do"})
  public String conendlist_trash(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("congress");

    List list = this.service.congress_endtrash(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

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

    return "naksinuri_original/naksinuri/admin/sosig/congress_endtrash";
  }

  @RequestMapping({"/{column}/{table}/view.do"})
  public String view(@PathVariable("table") String table, @PathVariable("column") String column, HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug("Naksinuri_admin_Controller ----- view()");
    LOGGER.debug(request.getServletPath());
    return "naksinuri_original/naksinuri/admin/" + column + "/" + table + "_view";
  }

  @RequestMapping({"/{column}/{table}/insert_data.do"})
  public String insert_data(@PathVariable("table") String table, @PathVariable("column") String column, MultipartHttpServletRequest multiRequest, HttpServletRequest request, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
    LOGGER.debug("insert_data() -> /" + column + "/" + table + "/insert_data.do");

    int pdfImgFileCnt = 0;
    try { 
    	pdfImgFileCnt = Integer.parseInt(multiRequest.getParameter("pdfImgFileCnt"));
    } catch (NumberFormatException e) {
    	pdfImgFileCnt = 0;
    	LOGGER.debug("[fail NumberFormatException]");
    } catch (Exception localException) {
    	pdfImgFileCnt = 0;
    	LOGGER.debug("[fail empy pdfImgFileCnt] "+localException.toString());
    }
    String mimg = "mimg";
    String simg = "simg";
    String movfile = "movfile";
    String pdfimgfile = "pdfimgfile_";

    String _mainFileId = "";
    String _subFileId = "";
    String _atchFileId = "";
    String _movFileId = "";
    String _pdfimgFileId = "";

    List _result = null;
    List _result2 = null;
    List _result3 = null;
    List _result4 = null;
    List _result5 = null;

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    Map sfile = multiRequest.getFileMap();
    Map mofilemap = multiRequest.getFileMap();
    Map pdfimgfilemap = multiRequest.getFileMap();

    mfile.clear();
    sfile.clear();
    mofilemap.clear();
    pdfimgfilemap.clear();

    if (files.get(mimg) != null) {
      mfile.put(mimg, files.get(mimg));
      files.remove(mimg);
    }

    if (files.get(simg) != null) {
      sfile.put(simg, files.get(simg));
      files.remove(simg);
    }

    if (files.get(movfile) != null) {
      mofilemap.put(movfile, files.get(movfile));
      files.remove(movfile);
    }

    for (int i = 1; i <= pdfImgFileCnt; i++) {
      String paramkey = pdfimgfile + i;
      if (files.get(paramkey) != null) {
        pdfimgfilemap.put(paramkey, files.get(paramkey));
        files.remove(paramkey);
      }
      if(pdfImgFileCnt > 100) break;
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

    if (!mofilemap.isEmpty()) {
      _result4 = this.fileUtil.parseFileInf2_naksinuri_original(mofilemap, "MOV_", 0, "", "");
      _movFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result4);
    }

    if (!pdfimgfilemap.isEmpty()) {
      _result5 = this.fileUtil.parseFileInf_naksinuri_original(pdfimgfilemap, "NAK_", 0, "", "");
      for (int i = 0; i < _result5.size(); i++) {
        if (((NaksinuriOriginalFileVO)_result5.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }
      _pdfimgFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result5);
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

    boardVO.setBo_pdf_img_file(_pdfimgFileId);
    boardVO.setBo_main_img(_mainFileId);
    boardVO.setBo_sub_img(_subFileId);
    boardVO.setBo_mov_file(_movFileId);
    boardVO.setBo_atch_file(_atchFileId);

    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    String bo_info = request.getParameter("bo_info");
    String bo_email1 = request.getParameter("bo_email1");
    String bo_email2 = request.getParameter("bo_email2");
    String host = request.getParameter("host");
    String organizer = request.getParameter("organizer");
    String cg_location = request.getParameter("cg_location");
    String reg_mb_id = request.getParameter("reg_mb_id");
    String bo_content = request.getParameter("bo_content");
    String entry_notice = request.getParameter("entry_notice");
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
      
    if (cg_location != null) {
    	cg_location = mEgovStringUtil.getHtmlStrCnvr(cg_location);
    	boardVO.setCg_location(cg_location);
    }
    if (bo_info != null) {
    	bo_info = mEgovStringUtil.getHtmlStrCnvr(bo_info);
    	boardVO.setBo_info(bo_info);
    }
    if (bo_pass != null) {
    	bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
    	boardVO.setBo_pass(bo_pass);
    }
    if (bo_name != null) {
    	bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
    	boardVO.setBo_name(bo_name);
    }
    if (bo_subject != null) {
//    	bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
    	boardVO.setBo_subject(bo_subject);
    }
    if (bo_content != null) {
//    	bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
    	boardVO.setBo_content(bo_content);
    }
    if (organizer != null) {
    	organizer = mEgovStringUtil.getHtmlStrCnvr(organizer);
    	boardVO.setOrganizer(organizer);
    }
    if (host != null) {
    	host = mEgovStringUtil.getHtmlStrCnvr(host);
    	boardVO.setHost(host);
    }
    if (host != null) {
    	host = mEgovStringUtil.getHtmlStrCnvr(host);
    	boardVO.setHost(host);
    }
    if (bo_email1 != null) {
    	bo_email1 = mEgovStringUtil.getHtmlStrCnvr(bo_email1);
    	boardVO.setBo_email1(bo_email1);
    }
    if (bo_email2 != null) {
    	bo_email2 = mEgovStringUtil.getHtmlStrCnvr(bo_email2);
    	boardVO.setBo_email2(bo_email2);
    }
    if (entry_notice != null) {
    	entry_notice = mEgovStringUtil.getHtmlStrCnvr(entry_notice);
    	boardVO.setEntry_notice(entry_notice);
    }
        
    if(boardVO.getCg_location() != null && boardVO.getCg_location().length() != 0){//
    	boardVO.setCg_location(mEgovStringUtil.getHtmlStrCnvr(boardVO.getCg_location()));
    }
    if(boardVO.getBo_info() != null && boardVO.getBo_info().length() != 0){//
    	boardVO.setBo_info(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_info()));
    }
    if(boardVO.getBo_pass() != null && boardVO.getBo_pass().length() != 0){//
    	boardVO.setBo_pass(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_pass()));
    }
    if(boardVO.getBo_name() != null && boardVO.getBo_name().length() != 0){//
    	boardVO.setBo_name(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_name()));
    }
    if(boardVO.getBo_subject() != null && boardVO.getBo_subject().length() != 0){//명칭
    	boardVO.setBo_subject(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_subject()));
    }
    if(boardVO.getBo_content() != null && boardVO.getBo_content().length() != 0){//내용
    	boardVO.setBo_content(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_content()));
    }
    if(boardVO.getOrganizer() != null && boardVO.getOrganizer().length() != 0){//주최
    	boardVO.setOrganizer(mEgovStringUtil.getHtmlStrCnvr(boardVO.getOrganizer()));
    }
    if(boardVO.getHost() != null && boardVO.getHost().length() != 0){//주관
    	boardVO.setHost(mEgovStringUtil.getHtmlStrCnvr(boardVO.getHost()));
    }
    if(boardVO.getBo_email1() != null && boardVO.getBo_email1().length() != 0){//이메일
    	boardVO.setBo_email1(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_email1()));
    }
    if(boardVO.getBo_email2() != null && boardVO.getBo_email2().length() != 0){//이메일
    	boardVO.setBo_email2(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_email2()));
    }
    if(boardVO.getEntry_notice() != null && boardVO.getEntry_notice().length() != 0){//참가신청 안내문구
    	boardVO.setEntry_notice(mEgovStringUtil.getHtmlStrCnvr(boardVO.getEntry_notice()));
    }
    

    this.service.insert_data(boardVO);

    if ((table.equals("junior")) || (table.equals("gosu")) || (table.equals("class")))
    {
      String bo_cate = "";
      String bo_cate_param = request.getParameter("bo_cate");

      if ((bo_cate_param.equals("바다")) && (table.equals("junior")))
        bo_cate = "바다";
      else if ((bo_cate_param.equals("민물")) && (table.equals("junior")))
        bo_cate = "민물";
      else if ((bo_cate_param.equals("루어")) && (table.equals("junior"))) {
        bo_cate = "루어";
      }

      model.addAttribute("bo_cate", bo_cate);
    }

    if (table.equals("congress")) {
      String end_date = request.getParameter("bo_end_dt");
      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
      Calendar today = new GregorianCalendar();
      String formatted_date = formatter.format(today.getTime());
      String bo_end_date = end_date.replace("-", "");

      int date_to_int = Integer.parseInt(formatted_date);
      int db_to_int = Integer.parseInt(bo_end_date);

      if (date_to_int >= db_to_int)
        return "redirect:/admin/sosig/congress/endlist.do";
      if (date_to_int < db_to_int) {
        return "redirect:/admin/sosig/congress/list.do";
      }
    }

    if (table.equals("campaign")) {
      String end_date = request.getParameter("bo_end_dt");
      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
      Calendar today = new GregorianCalendar();
      String formatted_date = formatter.format(today.getTime());
      String bo_end_date = end_date.replace("-", "");

      int date_to_int = Integer.parseInt(formatted_date);
      int db_to_int = Integer.parseInt(bo_end_date);

      if (date_to_int >= db_to_int)
        return "redirect:/admin/promotion/campaign/endlist.do";
      if (date_to_int < db_to_int) {
        return "redirect:/admin/promotion/campaign/list.do";
      }

    }

    return "redirect:/admin/" + column + "/" + table + "/list.do";
  }

  @RequestMapping({"/info/lab/insert_data.do"})
  public String _labinsert_data(MultipartHttpServletRequest multiRequest, SessionStatus status, HttpServletRequest request, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult bindingResult, ModelMap model)
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
      _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
    }

    if (!sfile.isEmpty()) {
      _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
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

    boardVO.setBo_main_img(_mainFileId);
    boardVO.setBo_sub_img(_subFileId);
    boardVO.setBo_atch_file(_atchFileId);
    
    String bo_subject = request.getParameter("bo_subject");
	String bo_content = request.getParameter("bo_content");
	String bo_name = request.getParameter("bo_name");
	String bo_pass = request.getParameter("bo_pass");
	
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	if (bo_subject != null) {
		bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
		boardVO.setBo_subject(bo_subject);
	}
	if (bo_content != null) {
		bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
		boardVO.setBo_content(bo_content);
	}
	if (bo_name != null) {
		bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
		boardVO.setBo_name(bo_name);
	}
	if (bo_pass != null) {
		bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
		boardVO.setBo_pass(bo_pass);
	}

    this.service.insert_lab_data(boardVO);

    return "redirect:/admin/info/lab/list.do";
  }

  @RequestMapping({"/info/adminset/view.do"})
  public String adminsetv(@ModelAttribute("adminVO") NaksinuriAdminVO adminVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriAdminVO info = this.service.adminset_findCorp(adminVO);
    info.setEmail_pw(EgovFileScrty.encryptPassword(info.getEmail_pw(), info.getEmail()));
    model.addAttribute("info", info);

    return "naksinuri_original/naksinuri/admin/info/adminset";
  }

  @RequestMapping({"/info/adminset/update.do"})
  public String adminseti(@ModelAttribute("adminVO") NaksinuriAdminVO adminVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String id = request.getParameter("gmail");
    String pw = request.getParameter("gmail_pw");
    String ai = request.getParameter("idx");

    adminVO.setIdx(ai);
    adminVO.setEmail(id);
    
//    adminVO.setEmail_pw(EgovFileScrty.encryptPassword(pw, id));
    adminVO.setEmail_pw(pw);

    this.service.adminset_update(adminVO);
    
    /* *************************************** */
    NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
    HttpSession session = request.getSession();    
    
    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
    insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
    insertlogsVO.setUser_id(loginVO.getMBR_ID());
    insertlogsVO.setActions("관리자메일설정");
    this.service_logs.insertLogs(insertlogsVO);
    /* *************************************** */

    return "redirect:/admin/info/adminset/view.do";
  }

  @RequestMapping({"/{column}/{table}/delete_list.do"})
  public String deleteBoard(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.delete_boardlist(boardVO);
    }
    if ((table.equals("junior")) || (table.equals("gosu")) || (table.equals("class"))) {
      String bo_cate = "";
      String bo_cate_param = request.getParameter("bo_cate");

      if (bo_cate_param.equals("바다"))
        bo_cate = "바다";
      else if (bo_cate_param.equals("민물"))
        bo_cate = "민물";
      else if (bo_cate_param.equals("루어")) {
        bo_cate = "루어";
      }
      model.addAttribute("bo_cate", bo_cate);
    }

    return "redirect:/admin/" + column + "/" + table + "/trash.do";
  }

  @RequestMapping({"/sosig/congress/enddelete_list.do"})
  public String deleteBoard_congress(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.delete_boardlist(boardVO);
    }

    return "redirect:/admin/sosig/congress/endtrash.do";
  }

  @RequestMapping({"/promotion/campaign/delete_endlist.do"})
  public String deleteBoard_campaign(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.delete_boardlist(boardVO);
    }

    return "redirect:/admin/promotion/campaign/endtrash.do";
  }

  @RequestMapping({"/{column}/{table}/restore.do"})
  public String restoreBoard(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.restore_boardlist(boardVO);
    }

    if ((table.equals("junior")) || (table.equals("gosu")) || (table.equals("class"))) {
      String bo_cate = "";
      String bo_cate_param = request.getParameter("bo_cate");

      if (bo_cate_param.equals("바다"))
        bo_cate = "바다";
      else if (bo_cate_param.equals("민물"))
        bo_cate = "민물";
      else if (bo_cate_param.equals("루어")) {
        bo_cate = "루어";
      }

      model.addAttribute("bo_cate", bo_cate);
    }

    return "redirect:/admin/" + column + "/" + table + "/trash.do";
  }

  @RequestMapping({"/sosig/congress/endrestore.do"})
  public String restoreCongress(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.restore_boardlist(boardVO);
    }

    return "redirect:/admin/sosig/congress/endtrash.do";
  }

  @RequestMapping({"/promotion/campaign/endrestore.do"})
  public String restoreCampaign(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.restore_boardlist(boardVO);
    }

    return "redirect:/admin/promotion/campaign/endtrash.do";
  }

  @RequestMapping({"/info/report/restore.do"})
  public String restore_report(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("reportVO") ReportVO reportVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      reportVO.setReport_sn(strboIds[i]);
      this.service_report.restore_report(reportVO);
    }
    return "redirect:/admin/info/report/trash.do";
  }

  @RequestMapping({"/promotion/qna/restore.do"})
  public String restoreBoard_qna(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      qnaVO.setQna_no(strboIds[i]);
      this.qna_service.restore_qnalist(qnaVO);
    }
    String qna_type = "";
    String qna_type_param = request.getParameter("qna_type");

    if (qna_type_param.equals("낚시관리및제도일반"))
      qna_type = "낚시관리및제도일반";
    else if (qna_type_param.equals("낚시터"))
      qna_type = "낚시터";
    else if (qna_type_param.equals("낚시어선"))
      qna_type = "낚시어선";
    else if (qna_type_param.equals("낚시도구및미끼"))
      qna_type = "낚시도구및미끼";
    else if (qna_type_param.equals("유어장"))
      qna_type = "유어장";
    else if (qna_type_param.equals("참고자료")) {
      qna_type = "참고자료";
    }

    model.addAttribute("qna_type", qna_type);

    return "redirect:/admin/promotion/qna/trash.do";
  }

  @RequestMapping({"/{column}/{table}/gotrash_list.do"})
  public String gotrashBoard(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("table") String table, @PathVariable("column") String column)
    throws Exception
  {
	  NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
      HttpSession session = request.getSession();    
      
      LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
      insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
      insertlogsVO.setUser_id(loginVO.getMBR_ID());
      
      
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.gotrash_boardlist(boardVO);
      
      insertlogsVO.setActions("공모전/이벤트 삭제(gotrash_boardlist) ; " + boardVO.getBo_sn());
      this.service_logs.insertLogs(insertlogsVO);
      
      
    }

    if ((table.equals("junior")) || (table.equals("gosu")) || (table.equals("class"))) {
      String bo_cate = "";
      String bo_cate_param = request.getParameter("bo_cate");

      if (bo_cate_param.equals("바다"))
        bo_cate = "바다";
      else if (bo_cate_param.equals("민물"))
        bo_cate = "민물";
      else if (bo_cate_param.equals("루어")) {
        bo_cate = "루어";
      }

      model.addAttribute("bo_cate", bo_cate);
      
      
      
    }

    return "redirect:/admin/" + column + "/" + table + "/list.do";
  }

  @RequestMapping({"/sosig/congress/gotrash_endlist.do"})
  public String gotrashcongress(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.gotrash_boardlist(boardVO);
    }

    return "redirect:/admin/sosig/congress/endlist.do";
  }

  @RequestMapping({"/promotion/campaign/gotrash_endlist.do"})
  public String gotrashcampaign(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      boardVO.setBo_sn(strboIds[i]);
      this.service.gotrash_boardlist(boardVO);
    }

    return "redirect:/admin/promotion/campaign/endlist.do";
  }

  @RequestMapping({"/{column}/{table}/board_findCorp.do"})
  public String board_findCorp(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, @PathVariable("table") String table, @PathVariable("column") String column, ModelMap model)
    throws Exception
  {
    LOGGER.debug(" Naksinuri_admin_Controller - board_findCorp() ... /" + column + "/" + table + "/board_findCorp.do ");

    model.addAttribute("table", table);
    model.addAttribute("column", column);
        
    BoardVO info = this.service.board_findCorp(boardVO);
    BoardVO bsimg = this.service.bsimg(boardVO);
    BoardVO bmimg = this.service.bmimg(boardVO);
    BoardVO movfile = this.service.movfile(boardVO);
    List pdfimgfile = this.service.pdfimgfile(boardVO);
    BoardVO bo_sido = this.service.getbo_sido(boardVO);
    List filelist = this.service.ba_file(boardVO);

    List list = this.service.select_list_comment(boardVO);

    if (list.size() > 0) {
      model.addAttribute("comment_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("bo_sido", bo_sido);
    model.addAttribute("info", info);
    model.addAttribute("bsimg", bsimg);
    model.addAttribute("bmimg", bmimg);
    model.addAttribute("movfile", movfile);
    model.addAttribute("pdfimgfile", pdfimgfile);
    model.addAttribute("filelist", filelist);

    return "naksinuri_original/naksinuri/admin/" + column + "/" + table + "_view";
  }

  @RequestMapping({"/info/report/board_findCorp.do"})
  public String go_reportview(@ModelAttribute("reportVO") ReportVO reportVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    ReportVO info = this.service_report.report_findCorp(reportVO);
    List filelist = this.service_report.ba_file(reportVO);

    model.addAttribute("info", info);
    model.addAttribute("filelist", filelist);

    return "naksinuri_original/naksinuri/admin/info/report_view";
  }

  @RequestMapping({"/{column}/{table}/update_data.do"})
  public String update_data(@PathVariable("table") String table, @PathVariable("column") String column, MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
    LOGGER.debug("Naksinuri_admin_controller - update_data() : /" + column + "/" + table + "/update_data.do");
    LOGGER.debug("youtubelink >>>>>>>>>>>>>>>>>  " + boardVO.getBo_youtubelink());

    int pdfImgFileCnt = 0;
    try { 
    	pdfImgFileCnt = Integer.parseInt(multiRequest.getParameter("pdfImgFileCnt"));
    } catch (NumberFormatException e) {
    	pdfImgFileCnt = 0;
    	LOGGER.debug("[fail NumberFormatException]");
    } catch (Exception localException) {
    	pdfImgFileCnt = 0;
    	LOGGER.debug("[fail empy pdfImgFileCnt] "+localException.toString());
    }
    String _mainFileId = boardVO.getBo_main_img();
    String _subFileId = boardVO.getBo_sub_img();
    String _atchFileId = boardVO.getBo_atch_file();
    String _movFileId = boardVO.getBo_mov_file();
    String _pdfimgFileId = boardVO.getBo_pdf_img_file();
    
    /*
    if (table.equals("congress")) {
        LOGGER.debug("......... 낚시대회 ");
        if(_mainFileId!=null && _mainFileId.length()!=0) {
        	LOGGER.debug("포스터 이미지가 삭제되었는지 검증:");
	        BoardVO bmimg = this.service.bmimg(boardVO);
	        if(bmimg==null || bmimg.getBo_sn()==null) {
	        	LOGGER.debug("이미지 없음");
	        	_mainFileId = " ";	        	
	        } else {
	        	LOGGER.debug("이미지 있음");
	        }
        }
    }
    */

    String bo_main_img = "mimg";
    String bo_sub_img = "simg";
    String movfile = "movfile";
    String pdfimgfile = "pdfimgfile_";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    Map sfile = multiRequest.getFileMap();
    Map mofilemap = multiRequest.getFileMap();
    Map pdfimgfilemap = multiRequest.getFileMap();

    List _result = null;
    List _result2 = null;
    List _result3 = null;
    List _result4 = null;
    List _result5 = null;

    mfile.clear();
    sfile.clear();
    mofilemap.clear();
    pdfimgfilemap.clear();

    if (files.get(bo_main_img) != null) {
      mfile.put(bo_main_img, files.get(bo_main_img));
      files.remove(bo_main_img);
    }

    if (files.get(bo_sub_img) != null) {
      sfile.put(bo_sub_img, files.get(bo_sub_img));
      files.remove(bo_sub_img);
    }

    if (files.get(movfile) != null) {
      mofilemap.put(movfile, files.get(movfile));
      files.remove(movfile);
    }

    for (int i = 1; i <= pdfImgFileCnt; i++) {
      String paramkey = pdfimgfile + i;
      if (files.get(paramkey) != null) {
        pdfimgfilemap.put(paramkey, files.get(paramkey));
        files.remove(paramkey);
      }
    }

    if (!pdfimgfilemap.isEmpty()) {
      if (("".equals(_pdfimgFileId)) || (_pdfimgFileId == null)) {
        _result5 = this.fileUtil.parseFileInf_naksinuri_original(pdfimgfilemap, "NAK_", 0, "", "");
        for (int i = 0; i < _result5.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result5.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        _pdfimgFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result5);
      }
      else {
        NaksinuriOriginalFileVO fvo = new NaksinuriOriginalFileVO();
        fvo.setAtchFileId(_pdfimgFileId);
        int cnt = this.fileMngService.getMaxFileSN_naksinuri_original(fvo);
        _result5 = this.fileUtil.parseFileInf_naksinuri_original(pdfimgfilemap, "NAK_", cnt, _pdfimgFileId, "");
        for (int i = 0; i < _result5.size(); i++) {
          if (((NaksinuriOriginalFileVO)_result5.get(i)).atchFileId.equals("ext_error")) {
            LOGGER.debug("파일에러");
            return "redirect:/error/ext/warn.do";
          }
        }
        this.fileMngService.updateFileInfs_naksinuri_original(_result5);
      }

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

    if (!mofilemap.isEmpty()) {
      if (("".equals(_movFileId)) || (_movFileId == null)) {
        _result4 = this.fileUtil.parseFileInf2_naksinuri_original(mofilemap, "MOV_", 0, "", "");
        _movFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result4);
      } else if ((!"".equals(_movFileId)) || (_movFileId != null)) {
        _result4 = this.fileUtil.parseFileInf2_naksinuri_original(mofilemap, "MOV", 0, _movFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result4);
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

    boardVO.setBo_pdf_img_file(_pdfimgFileId);
    boardVO.setBo_main_img(_mainFileId);
    boardVO.setBo_sub_img(_subFileId);
    boardVO.setBo_mov_file(_movFileId);
    boardVO.setBo_atch_file(_atchFileId);

    String bo_name = request.getParameter("bo_name");
    String bo_pass = request.getParameter("bo_pass");
    String bo_subject = request.getParameter("bo_subject");
    String bo_info = request.getParameter("bo_info");
    String bo_email1 = request.getParameter("bo_email1");
    String bo_email2 = request.getParameter("bo_email2");
    String host = request.getParameter("host");
    String organizer = request.getParameter("organizer");
    String cg_location = request.getParameter("cg_location");
    String reg_mb_id = request.getParameter("reg_mb_id");
    String bo_content = request.getParameter("bo_content");
    String entry_notice = request.getParameter("entry_notice");
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
      
    if (cg_location != null) {
    	cg_location = mEgovStringUtil.getHtmlStrCnvr(cg_location);
    	boardVO.setCg_location(cg_location);
    }
    if (bo_info != null) {
    	bo_info = mEgovStringUtil.getHtmlStrCnvr(bo_info);
    	boardVO.setBo_info(bo_info);
    }
    if (bo_pass != null) {
    	bo_pass = mEgovStringUtil.getHtmlStrCnvr(bo_pass);
    	boardVO.setBo_pass(bo_pass);
    }
    if (bo_name != null) {
    	bo_name = mEgovStringUtil.getHtmlStrCnvr(bo_name);
    	boardVO.setBo_name(bo_name);
    }
    if (bo_subject != null) {
//    	bo_subject = mEgovStringUtil.getHtmlStrCnvr(bo_subject);
    	boardVO.setBo_subject(bo_subject);
    }
    if (bo_content != null) {
//    	bo_content = mEgovStringUtil.getHtmlStrCnvr(bo_content);
    	boardVO.setBo_content(bo_content);
    }
    if (organizer != null) {
    	organizer = mEgovStringUtil.getHtmlStrCnvr(organizer);
    	boardVO.setOrganizer(organizer);
    }
    if (host != null) {
    	host = mEgovStringUtil.getHtmlStrCnvr(host);
    	boardVO.setHost(host);
    }
    if (host != null) {
    	host = mEgovStringUtil.getHtmlStrCnvr(host);
    	boardVO.setHost(host);
    }
    if (bo_email1 != null) {
    	bo_email1 = mEgovStringUtil.getHtmlStrCnvr(bo_email1);
    	boardVO.setBo_email1(bo_email1);
    }
    if (bo_email2 != null) {
    	bo_email2 = mEgovStringUtil.getHtmlStrCnvr(bo_email2);
    	boardVO.setBo_email2(bo_email2);
    }
    if (entry_notice != null) {
    	entry_notice = mEgovStringUtil.getHtmlStrCnvr(entry_notice);
    	boardVO.setEntry_notice(entry_notice);
    }
        
    if(boardVO.getCg_location() != null && boardVO.getCg_location().length() != 0){//
    	boardVO.setCg_location(mEgovStringUtil.getHtmlStrCnvr(boardVO.getCg_location()));
    }
    if(boardVO.getBo_info() != null && boardVO.getBo_info().length() != 0){//
    	boardVO.setBo_info(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_info()));
    }
    if(boardVO.getBo_pass() != null && boardVO.getBo_pass().length() != 0){//
    	boardVO.setBo_pass(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_pass()));
    }
    if(boardVO.getBo_name() != null && boardVO.getBo_name().length() != 0){//
    	boardVO.setBo_name(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_name()));
    }
    if(boardVO.getBo_subject() != null && boardVO.getBo_subject().length() != 0){//명칭
    	boardVO.setBo_subject(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_subject()));
    }
    if(boardVO.getBo_content() != null && boardVO.getBo_content().length() != 0){//내용
    	boardVO.setBo_content(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_content()));
    }
    if(boardVO.getOrganizer() != null && boardVO.getOrganizer().length() != 0){//주최
    	boardVO.setOrganizer(mEgovStringUtil.getHtmlStrCnvr(boardVO.getOrganizer()));
    }
    if(boardVO.getHost() != null && boardVO.getHost().length() != 0){//주관
    	boardVO.setHost(mEgovStringUtil.getHtmlStrCnvr(boardVO.getHost()));
    }
    if(boardVO.getBo_email1() != null && boardVO.getBo_email1().length() != 0){//이메일
    	boardVO.setBo_email1(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_email1()));
    }
    if(boardVO.getBo_email2() != null && boardVO.getBo_email2().length() != 0){//이메일
    	boardVO.setBo_email2(mEgovStringUtil.getHtmlStrCnvr(boardVO.getBo_email2()));
    }
    if(boardVO.getEntry_notice() != null && boardVO.getEntry_notice().length() != 0){//참가신청 안내문구
    	boardVO.setEntry_notice(mEgovStringUtil.getHtmlStrCnvr(boardVO.getEntry_notice()));
    }
    this.service.update_data(boardVO);
    
    /* *************************************** */
    NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
    HttpSession session = request.getSession();    
    
    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
    insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
    insertlogsVO.setUser_id(loginVO.getMBR_ID());
    insertlogsVO.setActions("공모전 수정(update_data.do) " + bo_name + " bo_sn = " + boardVO.getBo_sn());
    this.service_logs.insertLogs(insertlogsVO);
    /* *************************************** */

    return "redirect:/admin/" + column + "/" + table + "/list.do";
  }

  @RequestMapping({"/share/zisik/list.do"})
  public String z_list(@ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    zisikinVO.setPageInfo(model);
    zisikinVO.setPageUnit(zisikinVO.getPageUnit());
    model.addAttribute("searchText", request.getParameter("searchText"));
    model.addAttribute("searchType", request.getParameter("searchType"));

    List z_list = this.z_service.getListZisik(zisikinVO);

    if (z_list.size() > 0)
      zisikinVO.setTotalPage(((NaksinuriZisikinVO)z_list.get(0)).getTot_cnt());
    else {
      zisikinVO.setTotalPage(0);
    }
    if (z_list.size() > 0) {
      model.addAttribute("select_list", z_list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriZisikinVO)z_list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/share/zisik_list";
  }

  @RequestMapping({"/share/zisik/trash.do"})
  public String zt_list(@ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    zisikinVO.setPageInfo(model);
    zisikinVO.setPageUnit(zisikinVO.getPageUnit());
    model.addAttribute("searchText", request.getParameter("searchText"));
    model.addAttribute("searchType", request.getParameter("searchType"));

    List z_list = this.z_service.getTrashZisik(zisikinVO);

    if (z_list.size() > 0)
      zisikinVO.setTotalPage(((NaksinuriZisikinVO)z_list.get(0)).getTot_cnt());
    else {
      zisikinVO.setTotalPage(0);
    }
    if (z_list.size() > 0) {
      model.addAttribute("select_list", z_list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriZisikinVO)z_list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/share/zisik_trash";
  }

  @RequestMapping({"/share/zisik/view.do"})
  public String z_view(HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug(request.getServletPath());
    return "naksinuri_original/naksinuri/admin/share/zisik_view";
  }

  @RequestMapping({"/share/zisik/insertz.do"})
  public String insert_z(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, 
		  BindingResult bindingResult, ModelMap model) throws Exception {
	  
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  if(zisikinVO.getNuri_q_writer() != null && zisikinVO.getNuri_q_writer().length() != 0){//글쓴이
		  zisikinVO.setNuri_q_writer(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_q_writer()));
	  }
	  if(zisikinVO.getNuri_q_subject() != null && zisikinVO.getNuri_q_subject().length() != 0){//제목
		  zisikinVO.setNuri_q_subject(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_q_subject()));
	  }
	  if(zisikinVO.getNuri_q_content() != null && zisikinVO.getNuri_q_content().length() != 0){//질문
		  zisikinVO.setNuri_q_content(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_q_content()));
	  }
	  if(zisikinVO.getNuri_q_pass() != null && zisikinVO.getNuri_q_pass().length() != 0){//질문
		  zisikinVO.setNuri_q_pass(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_q_pass()));
	  }
	  
	  this.z_service.insert_zisik(zisikinVO);

	  return "redirect:/admin/share/zisik/list.do";
  }

  @RequestMapping({"/share/zisik/deletez.do"})
  public String delete_z(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      zisikinVO.setNuri_q_num(strboIds[i]);
      this.z_service.delete_zisik(zisikinVO);
    }
    return "redirect:/admin/share/zisik/trash.do";
  }

  @RequestMapping({"/share/zisik/restorez.do"})
  public String restore_z(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      zisikinVO.setNuri_q_num(strboIds[i]);
      this.z_service.restore_zisik(zisikinVO);
    }
    return "redirect:/admin/share/zisik/trash.do";
  }

  @RequestMapping({"/share/zisik/gotrash_zisik.do"})
  public String delete_zt(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      zisikinVO.setNuri_q_num(strboIds[i]);
      this.z_service.gotrash_zisik(zisikinVO);
    }
    return "redirect:/admin/share/zisik/list.do";
  }

  @RequestMapping({"/share/zisik/findz.do"})
  public String find_z(@ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriZisikinVO info = this.z_service.zisik_find(zisikinVO);

    List list = this.z_service.getListAnswer(zisikinVO);

    if (list.size() > 0) {
      model.addAttribute("answer_list", list);
    }

    model.addAttribute("info", info);

    return "naksinuri_original/naksinuri/admin/share/zisik_view";
  }

  @RequestMapping({"/share/zisik/updatez.do"})
  public String update_z(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	if(zisikinVO.getNuri_q_writer() != null && zisikinVO.getNuri_q_writer().length() != 0){//글쓴이
	 zisikinVO.setNuri_q_writer(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_q_writer()));
	}
	if(zisikinVO.getNuri_q_subject() != null && zisikinVO.getNuri_q_subject().length() != 0){//제목
	 zisikinVO.setNuri_q_subject(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_q_subject()));
	}
	if(zisikinVO.getNuri_q_content() != null && zisikinVO.getNuri_q_content().length() != 0){//질문
	 zisikinVO.setNuri_q_content(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_q_content()));
	}
	if(zisikinVO.getNuri_q_pass() != null && zisikinVO.getNuri_q_pass().length() != 0){//질문
	 zisikinVO.setNuri_q_pass(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_q_pass()));
	}
    this.z_service.update_zisik(zisikinVO);

    return "redirect:/admin/share/zisik/list.do";
  }

  @RequestMapping({"/share/zisik/answer.do"})
  public String answer_zisik(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
    NaksinuriZisikinVO info = this.z_service.ans_find(zisikinVO);

    model.addAttribute("qnum", zisikinVO.getNuri_q_num());
    model.addAttribute("qcon", zisikinVO.getNuri_q_content());
    model.addAttribute("info", info);

    return "naksinuri_original/naksinuri/admin/share/zisik_answer";
  }

  @RequestMapping({"/share/zisik/inserta.do"})
  public String insert_a(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
    if(zisikinVO.getNuri_a_writer() != null && zisikinVO.getNuri_a_writer().length() != 0){//글쓴이
	 zisikinVO.setNuri_a_writer(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_a_writer()));
	}
	if(zisikinVO.getNuri_a_subject() != null && zisikinVO.getNuri_a_subject().length() != 0){//제목
	 zisikinVO.setNuri_a_subject(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_a_subject()));
	}
	if(zisikinVO.getNuri_a_content() != null && zisikinVO.getNuri_a_content().length() != 0){//질문
	 zisikinVO.setNuri_a_content(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_a_content()));
	}
	if(zisikinVO.getNuri_a_pass() != null && zisikinVO.getNuri_a_pass().length() != 0){//질문
	 zisikinVO.setNuri_a_pass(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_a_pass()));
	}
    this.z_service.insert_ans(zisikinVO);

    return "redirect:/admin/share/zisik/list.do";
  }

  @RequestMapping({"/share/zisik/deletea.do"})
  public String delete_a(@ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    this.z_service.delete_ans(zisikinVO);
    return "forward:/admin/share/zisik/findz.do";
  }

  @RequestMapping({"/share/zisik/finda.do"})
  public String find_a(@ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriZisikinVO info = this.z_service.ans_find(zisikinVO);

    model.addAttribute("info", info);

    return "naksinuri_original/naksinuri/admin/share/zisik_view";
  }

  @RequestMapping({"/share/zisik/updatea.do"})
  public String update_a(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("zisikinVO") NaksinuriZisikinVO zisikinVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
    if(zisikinVO.getNuri_a_writer() != null && zisikinVO.getNuri_a_writer().length() != 0){//글쓴이
	 zisikinVO.setNuri_a_writer(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_a_writer()));
	}
	if(zisikinVO.getNuri_a_subject() != null && zisikinVO.getNuri_a_subject().length() != 0){//제목
	 zisikinVO.setNuri_a_subject(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_a_subject()));
	}
	if(zisikinVO.getNuri_a_content() != null && zisikinVO.getNuri_a_content().length() != 0){//질문
	 zisikinVO.setNuri_a_content(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_a_content()));
	}
	if(zisikinVO.getNuri_a_pass() != null && zisikinVO.getNuri_a_pass().length() != 0){//질문
	 zisikinVO.setNuri_a_pass(mEgovStringUtil.getHtmlStrCnvr(zisikinVO.getNuri_a_pass()));
	}
    this.z_service.update_ans(zisikinVO);

    return "forward:/admin/share/zisik/findz.do";
  }

  @RequestMapping({"/promotion/qna/list.do"})
  public String qnalist(@ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, String qna_type, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    if (qna_type == null) {
      qnaVO.setQna_type("낚시관리및제도일반");
    }

    qna_type = qnaVO.getQna_type();

    LOGGER.debug("출력:" + qna_type);

    model.addAttribute("qna_type", qna_type);
    qnaVO.setPageInfo(model);
    qnaVO.setPageUnit(qnaVO.getPageUnit());
    model.addAttribute("searchText", request.getParameter("searchText"));
    model.addAttribute("searchType", request.getParameter("searchType"));

    List list = this.qna_service.getListQnA(qnaVO);

    if (list.size() > 0)
      qnaVO.setTotalPage(((NaksinuriQnAVO)list.get(0)).getTot_cnt());
    else {
      qnaVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriQnAVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/share/qna_list";
  }

  @RequestMapping({"/promotion/qna/trash.do"})
  public String qnatrash(@ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, String qna_type, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    if (qna_type == null) {
      qnaVO.setQna_type("낚시관리및제도일반");
    }

    qna_type = qnaVO.getQna_type();

    LOGGER.debug("출력:" + qna_type);

    model.addAttribute("qna_type", qna_type);
    qnaVO.setPageInfo(model);
    qnaVO.setPageUnit(qnaVO.getPageUnit());
    model.addAttribute("searchText", request.getParameter("searchText"));
    model.addAttribute("searchType", request.getParameter("searchType"));

    List list = this.qna_service.getTrashQnA(qnaVO);

    if (list.size() > 0)
      qnaVO.setTotalPage(((NaksinuriQnAVO)list.get(0)).getTot_cnt());
    else {
      qnaVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriQnAVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/share/qna_trash";
  }

  @RequestMapping({"/promotion/qna/view.do"})
  public String qnaview(@ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return "naksinuri_original/naksinuri/admin/share/qna_view";
  }

  @RequestMapping({"/promotion/qna/insert.do"})
  public String qnainsert(MultipartHttpServletRequest multiRequest, HttpServletRequest request, SessionStatus status, @ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
    String qna_ques = request.getParameter("qna_ques");
    qna_ques = mEgovStringUtil.getHtmlStrCnvr(qna_ques);
    qnaVO.setQna_ques(qna_ques);

    this.qna_service.insert_qna(qnaVO);
    String qna_type = "";
    String qna_type_param = request.getParameter("qna_type");

    if (qna_type_param.equals("낚시관리및제도일반"))
      qna_type = "낚시관리및제도일반";
    else if (qna_type_param.equals("낚시터"))
      qna_type = "낚시터";
    else if (qna_type_param.equals("낚시어선"))
      qna_type = "낚시어선";
    else if (qna_type_param.equals("낚시도구및미끼"))
      qna_type = "낚시도구및미끼";
    else if (qna_type_param.equals("유어장"))
      qna_type = "유어장";
    else if (qna_type_param.equals("참고자료")) {
      qna_type = "참고자료";
    }

    model.addAttribute("qna_type", qna_type);

    return "redirect:/admin/promotion/qna/list.do";
  }

  @RequestMapping({"/promotion/qna/update.do"})
  public String qnaupdate(MultipartHttpServletRequest multiRequest, HttpServletRequest request, SessionStatus status, @ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    String qna_ques = request.getParameter("qna_ques");
    qna_ques = mEgovStringUtil.getHtmlStrCnvr(qna_ques);
    qnaVO.setQna_ques(qna_ques);

    this.qna_service.update_qna(qnaVO);

    return "redirect:/admin/promotion/qna/list.do";
  }

  @RequestMapping({"/promotion/qna/find.do"})
  public String qnafind(@ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriQnAVO info = this.qna_service.qna_find(qnaVO);

    List list = this.qna_service.getListAnswer(qnaVO);

    if (list.size() > 0) {
      model.addAttribute("answer_list", list);
    }

    model.addAttribute("info", info);

    return "naksinuri_original/naksinuri/admin/share/qna_view";
  }

  @RequestMapping({"/promotion/qna/delete_list.do"})
  public String qnadelete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      qnaVO.setQna_no(strboIds[i]);
      this.qna_service.delete_qna(qnaVO);
    }

    String qna_type = "";
    String qna_type_param = request.getParameter("qna_type");

    if (qna_type_param.equals("낚시관리및제도일반"))
      qna_type = "낚시관리및제도일반";
    else if (qna_type_param.equals("낚시터"))
      qna_type = "낚시터";
    else if (qna_type_param.equals("낚시어선"))
      qna_type = "낚시어선";
    else if (qna_type_param.equals("낚시도구및미끼"))
      qna_type = "낚시도구및미끼";
    else if (qna_type_param.equals("유어장"))
      qna_type = "유어장";
    else if (qna_type_param.equals("참고자료")) {
      qna_type = "참고자료";
    }

    model.addAttribute("qna_type", qna_type);

    return "redirect:/admin/promotion/qna/trash.do";
  }

  @RequestMapping({"/promotion/qna/gotrash.do"})
  public String qna_gotrash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("qnaVO") NaksinuriQnAVO qnaVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      qnaVO.setQna_no(strboIds[i]);
      this.qna_service.gotrash_qna(qnaVO);
    }

    String qna_type = "";
    String qna_type_param = request.getParameter("qna_type");

    if (qna_type_param.equals("낚시관리및제도일반"))
      qna_type = "낚시관리및제도일반";
    else if (qna_type_param.equals("낚시터"))
      qna_type = "낚시터";
    else if (qna_type_param.equals("낚시어선"))
      qna_type = "낚시어선";
    else if (qna_type_param.equals("낚시도구및미끼"))
      qna_type = "낚시도구및미끼";
    else if (qna_type_param.equals("유어장"))
      qna_type = "유어장";
    else if (qna_type_param.equals("참고자료")) {
      qna_type = "참고자료";
    }

    model.addAttribute("qna_type", qna_type);

    return "redirect:/admin/promotion/qna/list.do";
  }

  @RequestMapping({"/promotion/campaign/list.do"})
  public String campaign_list(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());

    boardVO.setBo_type("campaign");

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

    return "naksinuri_original/naksinuri/admin/promotion/campaign_list";
  }

  @RequestMapping({"/promotion/campaign/endlist.do"})
  public String campaign_listend(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());

    boardVO.setBo_type("campaign");

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

    return "naksinuri_original/naksinuri/admin/promotion/campaign_endlist";
  }

  @RequestMapping({"/promotion/campaign/trash.do"})
  public String campaign_trash(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());

    boardVO.setBo_type("campaign");

    List list = this.service.campaignTrash(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/promotion/campaign_trash";
  }

  @RequestMapping({"/promotion/campaign/endtrash.do"})
  public String campaign_endtrash(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());

    boardVO.setBo_type("campaign");

    List list = this.service.campaignendTrash(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/promotion/campaign_endtrash";
  }

  @RequestMapping({"/promotion/info/list.do"})
  public String info_list(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());

    boardVO.setBo_type("info");
    
    List noticlist = this.service.noticemark_list(boardVO);
    List list = this.service.select_list_admin(boardVO);

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
    return "naksinuri_original/naksinuri/admin/promotion/info_list";
  }
  
  @RequestMapping({"/sosig/event/list.do"})
  public String event_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getAllEventList(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/event_list";
  }

  @RequestMapping({"/sosig/yevent/list.do"})
  public String yevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getEventList(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/yevent_list";
  }

  @RequestMapping({"/sosig/nevent/list.do"})
  public String nevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getendEventList(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/nevent_list";
  }

  @RequestMapping({"/sosig/aevent/list.do"})
  public String aevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getancEventList(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/aevent_list";
  }

  @RequestMapping({"/sosig/event/trash.do"})
  public String event_list_trash(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getAllEventTrash(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/event_trash";
  }

  @RequestMapping({"/sosig/yevent/trash.do"})
  public String yevent_list_trash(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getEventTrash(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/yevent_trash";
  }

  @RequestMapping({"/sosig/nevent/trash.do"})
  public String nevent_list_trash(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getendEventTrash(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/nevent_trash";
  }

  @RequestMapping({"/sosig/aevent/trash.do"})
  public String aevent_list_trash(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getancEventTrash(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/aevent_trash";
  }

  @RequestMapping({"/sosig/event/insert_data.do"})
  public String insert_data(MultipartHttpServletRequest multiRequest, HttpServletRequest request, SessionStatus status, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, BindingResult bindingResult, ModelMap model)
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

    eventVO.setEvn_main_img(_mainFileId);
    eventVO.setEvn_sub_img(_subFileId);
    eventVO.setEvn_atch_file(_atchFileId);
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String evn_writer = request.getParameter("evn_writer");
    String evn_pass = request.getParameter("evn_pass");
    String evn_subject = request.getParameter("evn_subject");

    evn_writer = mEgovStringUtil.getHtmlStrCnvr(evn_writer);
    evn_pass = mEgovStringUtil.getHtmlStrCnvr(evn_pass);
    evn_subject = mEgovStringUtil.getHtmlStrCnvr(evn_subject);

    eventVO.setEvn_writer(evn_writer);
    eventVO.setEvn_pass(evn_pass);
    eventVO.setEvn_subject(evn_subject);

    this.eventService.insert_data(eventVO);

    String end_date = request.getParameter("evn_enddate");
    if (end_date != null) {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
      Calendar today = new GregorianCalendar();
      String formatted_date = formatter.format(today.getTime());
      String evn_end_date = end_date.replace("-", "");

      int date_to_int = Integer.parseInt(formatted_date);
      int db_to_int = Integer.parseInt(evn_end_date);

      if (date_to_int > db_to_int)
        return "redirect:/admin/sosig/nevent/list.do";
      if (date_to_int <= db_to_int) {
        return "redirect:/admin/sosig/yevent/list.do";
      }
    }

    return "redirect:/admin/sosig/aevent/list.do";
  }

  @RequestMapping({"/sosig/event/event_findCorp.do"})
  public String event_findCorp(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriEventVO info = this.eventService.event_findCorp(eventVO);
    NaksinuriEventVO esimg = this.eventService.esimg(eventVO);
    NaksinuriEventVO emimg = this.eventService.emimg(eventVO);
    List filelist = this.eventService.ea_file(eventVO);

    model.addAttribute("info", info);
    model.addAttribute("esimg", esimg);
    model.addAttribute("emimg", emimg);
    model.addAttribute("filelist", filelist);

    List comment_list = this.eventService.select_event_comment(eventVO);

    if (comment_list.size() > 0) {
      model.addAttribute("comment_list", comment_list);
    }

    return "naksinuri_original/naksinuri/admin/sosig/event_view";
  }

  @RequestMapping({"/sosig/event/aevent_findCorp.do"})
  public String aevent_findCorp(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriEventVO info = this.eventService.event_findCorp(eventVO);
    NaksinuriEventVO esimg = this.eventService.esimg(eventVO);
    NaksinuriEventVO emimg = this.eventService.emimg(eventVO);
    List filelist = this.eventService.ea_file(eventVO);

    model.addAttribute("info", info);
    model.addAttribute("esimg", esimg);
    model.addAttribute("emimg", emimg);
    model.addAttribute("filelist", filelist);

    List comment_list = this.eventService.select_event_comment(eventVO);

    if (comment_list.size() > 0) {
      model.addAttribute("comment_list", comment_list);
    }

    return "naksinuri_original/naksinuri/admin/sosig/aevent_view";
  }

  @RequestMapping({"/sosig/yevent/delete_list.do"})
  public String event_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.deleteEvent(eventVO);
    }
    return "redirect:/admin/sosig/yevent/trash.do";
  }

  @RequestMapping({"/sosig/yevent/restore.do"})
  public String yevent_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.restoreEvent(eventVO);
    }
    return "redirect:/admin/sosig/yevent/trash.do";
  }

  @RequestMapping({"/sosig/yevent/gotrash_list.do"})
  public String event_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.trashEvent(eventVO);
    }
    return "redirect:/admin/sosig/yevent/list.do";
  }

  @RequestMapping({"/sosig/event/delete_list.do"})
  public String eventall_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.deleteEvent(eventVO);
    }
    return "redirect:/admin/sosig/event/trash.do";
  }

  @RequestMapping({"/sosig/event/restore.do"})
  public String event_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.restoreEvent(eventVO);
    }
    return "redirect:/admin/sosig/event/trash.do";
  }

  @RequestMapping({"/sosig/event/gotrash_list.do"})
  public String eventall_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.trashEvent(eventVO);
    }
    return "redirect:/admin/sosig/event/list.do";
  }

  @RequestMapping({"/sosig/aevent/delete_list.do"})
  public String aevent_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.deleteEvent(eventVO);
    }
    return "redirect:/admin/sosig/aevent/trash.do";
  }

  @RequestMapping({"/sosig/aevent/restore.do"})
  public String aevent_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.restoreEvent(eventVO);
    }
    return "redirect:/admin/sosig/aevent/trash.do";
  }

  @RequestMapping({"/sosig/aevent/gotrash_list.do"})
  public String aevent_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.trashEvent(eventVO);
    }
    return "redirect:/admin/sosig/aevent/list.do";
  }

  @RequestMapping({"/sosig/nevent/delete_list.do"})
  public String nevent_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.deleteEvent(eventVO);
    }
    return "redirect:/admin/sosig/nevent/trash.do";
  }

  @RequestMapping({"/sosig/nevent/restore.do"})
  public String nevent_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.restoreEvent(eventVO);
    }
    return "redirect:/admin/sosig/nevent/trash.do";
  }

  @RequestMapping({"/sosig/nevent/gotrash_list.do"})
  public String nevent_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.trashEvent(eventVO);
    }
    return "redirect:/admin/sosig/nevent/list.do";
  }

  @RequestMapping({"/gongmo/event/list.do"})
  public String gevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getAllEventgongmoList(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/gongmo/event_list";
  }

  @RequestMapping({"/gongmo/yevent/list.do"})
  public String gyevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getEventgongmoList(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/gongmo/yevent_list";
  }

  @RequestMapping({"/gongmo/nevent/list.do"})
  public String gnevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getendEventgongmoList(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/gongmo/nevent_list";
  }

  @RequestMapping({"/gongmo/aevent/list.do"})
  public String gaevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getancEventgongmoList(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/gongmo/aevent_list";
  }

  @RequestMapping({"/gongmo/event/trash.do"})
  public String gevent_list_trash(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getAllEventgongmoTrash(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/gongmo/event_trash";
  }

  @RequestMapping({"/gongmo/yevent/trash.do"})
  public String gyevent_list_trash(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getEventgongmoTrash(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/gongmo/yevent_trash";
  }

  @RequestMapping({"/gongmo/nevent/trash.do"})
  public String gnevent_list_trash(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getendEventgongmoTrash(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/gongmo/nevent_trash";
  }

  @RequestMapping({"/gongmo/aevent/trash.do"})
  public String gaevent_list_trash(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    eventVO.setPageInfo(model);
    eventVO.setPageUnit(eventVO.getPageUnit());

    List list = this.eventService.getancEventgongmoTrash(eventVO);

    if (list.size() > 0)
      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
    else {
      eventVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("event_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/gongmo/aevent_trash";
  }

  @RequestMapping({"/gongmo/event/insert_data.do"})
  public String ginsert_data(MultipartHttpServletRequest multiRequest, HttpServletRequest request, SessionStatus status, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, BindingResult bindingResult, ModelMap model)
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

    eventVO.setEvn_main_img(_mainFileId);
    eventVO.setEvn_sub_img(_subFileId);
    eventVO.setEvn_atch_file(_atchFileId);

    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String evn_writer = request.getParameter("evn_writer");
    String evn_subject = request.getParameter("evn_subject");
    String evn_pass = request.getParameter("evn_pass");
    String evn_content = request.getParameter("evn_content");
    
    if (evn_writer != null) {
    	evn_writer = mEgovStringUtil.getHtmlStrCnvr(evn_writer);
    	eventVO.setEvn_writer(evn_writer);
    }
    if (evn_subject != null) {
    	evn_subject = mEgovStringUtil.getHtmlStrCnvr(evn_subject);
    	eventVO.setEvn_subject(evn_subject);
    }
    if (evn_pass != null) {
    	evn_pass = mEgovStringUtil.getHtmlStrCnvr(evn_pass);
    	eventVO.setEvn_pass(evn_pass);
    }
    if (evn_content != null) {
    	evn_content = mEgovStringUtil.getHtmlStrCnvr(evn_content);
    	eventVO.setEvn_content(evn_content);
    }
        
    if(eventVO.getEvn_writer() != null && eventVO.getEvn_writer().length() != 0){
    	eventVO.setEvn_writer(mEgovStringUtil.getHtmlStrCnvr(eventVO.getEvn_writer()));
    }
    if(eventVO.getEvn_subject() != null && eventVO.getEvn_subject().length() != 0){
    	eventVO.setEvn_subject(mEgovStringUtil.getHtmlStrCnvr(eventVO.getEvn_subject()));
    }
    if(eventVO.getEvn_pass() != null && eventVO.getEvn_pass().length() != 0){
    	eventVO.setEvn_pass(mEgovStringUtil.getHtmlStrCnvr(eventVO.getEvn_pass()));
    }
    if(eventVO.getEvn_content() != null && eventVO.getEvn_content().length() != 0){
    	eventVO.setEvn_content(mEgovStringUtil.getHtmlStrCnvr(eventVO.getEvn_content()));
    }

    this.eventService.insert_data_gongmo(eventVO);

    String end_date = request.getParameter("evn_enddate");
    if (end_date != null) {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
      Calendar today = new GregorianCalendar();
      String formatted_date = formatter.format(today.getTime());
      String evn_end_date = end_date.replace("-", "");

      int date_to_int = Integer.parseInt(formatted_date);
      int db_to_int = Integer.parseInt(evn_end_date);

      if (date_to_int > db_to_int)
        return "redirect:/admin/gongmo/nevent/list.do";
      if (date_to_int <= db_to_int) {
        return "redirect:/admin/gongmo/yevent/list.do";
      }
    }

    return "redirect:/admin/gongmo/aevent/list.do";
  }
  
  
  @RequestMapping({"/gongmo/event/update_data.do"})
  public String gevnupdate_data(MultipartHttpServletRequest multiRequest, HttpServletRequest request, SessionStatus status, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
    String _mainFileId = eventVO.getEvn_main_img();
    String _subFileId = eventVO.getEvn_sub_img();
    String _atchFileId = eventVO.getEvn_atch_file();

    String evn_main_img = "mimg";
    String evn_sub_img = "simg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    Map sfile = multiRequest.getFileMap();

    List _result = null;
    List _result2 = null;
    List _result3 = null;

    mfile.clear();
    sfile.clear();

    if (files.get(evn_main_img) != null) {
      mfile.put(evn_main_img, files.get(evn_main_img));
      files.remove(evn_main_img);
    }

    if (files.get(evn_sub_img) != null) {
      sfile.put(evn_sub_img, files.get(evn_sub_img));
      files.remove(evn_sub_img);
    }

    if (!mfile.isEmpty()) {
      if (("".equals(_mainFileId)) || (_mainFileId == null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
        eventVO.setEvn_main_img(_mainFileId);
      } else if ((!"".equals(_mainFileId)) || (_mainFileId != null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, _mainFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }
    }
    if (!sfile.isEmpty())
    {
      if (("".equals(_subFileId)) || (_subFileId == null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
        _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
        eventVO.setEvn_sub_img(_subFileId);
      } else if ((!"".equals(_subFileId)) || (_subFileId != null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, _subFileId, "");
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

        eventVO.setEvn_atch_file(_atchFileId);
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

    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String evn_writer = request.getParameter("evn_writer");
    String evn_subject = request.getParameter("evn_subject");
    String evn_pass = request.getParameter("evn_pass");
    String evn_content = request.getParameter("evn_content");
    
    if (evn_writer != null) {
    	evn_writer = mEgovStringUtil.getHtmlStrCnvr(evn_writer);
    	eventVO.setEvn_writer(evn_writer);
    }
    if (evn_subject != null) {
    	evn_subject = mEgovStringUtil.getHtmlStrCnvr(evn_subject);
    	eventVO.setEvn_subject(evn_subject);
    }
    if (evn_pass != null) {
    	evn_pass = mEgovStringUtil.getHtmlStrCnvr(evn_pass);
    	eventVO.setEvn_pass(evn_pass);
    }
    if (evn_content != null) {
    	evn_content = mEgovStringUtil.getHtmlStrCnvr(evn_content);
    	eventVO.setEvn_content(evn_content);
    }
        
    if(eventVO.getEvn_writer() != null && eventVO.getEvn_writer().length() != 0){
    	eventVO.setEvn_writer(mEgovStringUtil.getHtmlStrCnvr(eventVO.getEvn_writer()));
    }
    if(eventVO.getEvn_subject() != null && eventVO.getEvn_subject().length() != 0){
    	eventVO.setEvn_subject(mEgovStringUtil.getHtmlStrCnvr(eventVO.getEvn_subject()));
    }
    if(eventVO.getEvn_pass() != null && eventVO.getEvn_pass().length() != 0){
    	eventVO.setEvn_pass(mEgovStringUtil.getHtmlStrCnvr(eventVO.getEvn_pass()));
    }
    if(eventVO.getEvn_content() != null && eventVO.getEvn_content().length() != 0){
    	eventVO.setEvn_content(mEgovStringUtil.getHtmlStrCnvr(eventVO.getEvn_content()));
    }

    this.eventService.update_data_gongmo(eventVO);
    return "redirect:/admin/gongmo/event/list.do";
  }
  
  

  @RequestMapping({"/gongmo/event/event_findCorp.do"})
  public String gevent_findCorp(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriEventVO info = this.eventService.event_gongmo_findCorp(eventVO);
    NaksinuriEventVO esimg = this.eventService.esimg_gongmo(eventVO);
    NaksinuriEventVO emimg = this.eventService.emimg_gongmo(eventVO);
    List filelist = this.eventService.ea_file_gongmo(eventVO);

    model.addAttribute("info", info);
    model.addAttribute("esimg", esimg);
    model.addAttribute("emimg", emimg);
    model.addAttribute("filelist", filelist);

    return "naksinuri_original/naksinuri/admin/gongmo/event_view";
  }

  @RequestMapping({"/gongmo/event/aevent_findCorp.do"})
  public String gaevent_findCorp(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriEventVO info = this.eventService.event_gongmo_findCorp(eventVO);
    NaksinuriEventVO esimg = this.eventService.esimg_gongmo(eventVO);
    NaksinuriEventVO emimg = this.eventService.emimg_gongmo(eventVO);
    List filelist = this.eventService.ea_file_gongmo(eventVO);

    model.addAttribute("info", info);
    model.addAttribute("esimg", esimg);
    model.addAttribute("emimg", emimg);
    model.addAttribute("filelist", filelist);

    return "naksinuri_original/naksinuri/admin/gongmo/aevent_view";
  }

  @RequestMapping({"/gongmo/yevent/delete_list.do"})
  public String gevent_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.deleteEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/yevent/trash.do";
  }

  @RequestMapping({"/gongmo/yevent/restore.do"})
  public String gyevent_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.restoreEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/yevent/trash.do";
  }

  @RequestMapping({"/gongmo/yevent/gotrash_list.do"})
  public String gevent_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.trashEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/yevent/list.do";
  }

  @RequestMapping({"/gongmo/event/delete_list.do"})
  public String geventall_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.deleteEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/event/trash.do";
  }

  @RequestMapping({"/gongmo/event/restore.do"})
  public String gevent_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.restoreEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/event/trash.do";
  }

  @RequestMapping({"/gongmo/event/gotrash_list.do"})
  public String geventall_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.trashEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/event/list.do";
  }

  @RequestMapping({"/gongmo/aevent/delete_list.do"})
  public String gaevent_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.deleteEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/aevent/trash.do";
  }

  @RequestMapping({"/gongmo/aevent/restore.do"})
  public String gaevent_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.restoreEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/aevent/trash.do";
  }

  @RequestMapping({"/gongmo/aevent/gotrash_list.do"})
  public String gaevent_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.trashEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/aevent/list.do";
  }

  @RequestMapping({"/gongmo/nevent/delete_list.do"})
  public String gnevent_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.deleteEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/nevent/trash.do";
  }

  @RequestMapping({"/gongmo/nevent/restore.do"})
  public String gnevent_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.restoreEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/nevent/trash.do";
  }

  @RequestMapping({"/gongmo/nevent/gotrash_list.do"})
  public String gnevent_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      eventVO.setEvn_no(strboIds[i]);
      this.eventService.trashEvent_gongmo(eventVO);
    }
    return "redirect:/admin/gongmo/nevent/list.do";
  }

  @RequestMapping({"/sosig/event/eco_delete.do"})
  public String eco_delete(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String evn_no = request.getParameter("evn_no");

    this.eventService.eco_delete(eventVO);

    model.addAttribute("evn_no", evn_no);
    return "naksinuri_original/naksinuri/admin/sosig/eco_del";
  }

  @RequestMapping({"/{column}/{table}/co_delete.do"})
  public String co_delete(@PathVariable("table") String table, @PathVariable("column") String column, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    model.addAttribute("table", table);
    model.addAttribute("column", column);

    String bo_sn = request.getParameter("bo_sn");

    this.service.co_delete(boardVO);

    model.addAttribute("bo_sn", bo_sn);
    return "naksinuri_original/naksinuri/admin/co_del";
  }

  @RequestMapping({"/sosig/event/update_data.do"})
  public String evnupdate_data(MultipartHttpServletRequest multiRequest, HttpServletRequest request, SessionStatus status, @ModelAttribute("eventVO") NaksinuriEventVO eventVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
    String _mainFileId = eventVO.getEvn_main_img();
    String _subFileId = eventVO.getEvn_sub_img();
    String _atchFileId = eventVO.getEvn_atch_file();

    String evn_main_img = "mimg";
    String evn_sub_img = "simg";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    Map sfile = multiRequest.getFileMap();

    List _result = null;
    List _result2 = null;
    List _result3 = null;

    mfile.clear();
    sfile.clear();

    if (files.get(evn_main_img) != null) {
      mfile.put(evn_main_img, files.get(evn_main_img));
      files.remove(evn_main_img);
    }

    if (files.get(evn_sub_img) != null) {
      sfile.put(evn_sub_img, files.get(evn_sub_img));
      files.remove(evn_sub_img);
    }

    if (!mfile.isEmpty()) {
      if (("".equals(_mainFileId)) || (_mainFileId == null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
        eventVO.setEvn_main_img(_mainFileId);
      } else if ((!"".equals(_mainFileId)) || (_mainFileId != null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, _mainFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }
    }
    if (!sfile.isEmpty())
    {
      if (("".equals(_subFileId)) || (_subFileId == null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, "", "");
        _subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result2);
        eventVO.setEvn_sub_img(_subFileId);
      } else if ((!"".equals(_subFileId)) || (_subFileId != null)) {
        _result2 = this.fileUtil.parseFileInf_naksinuri_original(sfile, "NAK_", 0, _subFileId, "");
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

        eventVO.setEvn_atch_file(_atchFileId);
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
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();

    String evn_writer = request.getParameter("evn_writer");
    String evn_pass = request.getParameter("evn_pass");
    String evn_subject = request.getParameter("evn_subject");

    evn_writer = mEgovStringUtil.getHtmlStrCnvr(evn_writer);
    evn_pass = mEgovStringUtil.getHtmlStrCnvr(evn_pass);
    evn_subject = mEgovStringUtil.getHtmlStrCnvr(evn_subject);

    eventVO.setEvn_writer(evn_writer);
    eventVO.setEvn_pass(evn_pass);
    eventVO.setEvn_subject(evn_subject);

    this.eventService.update_data(eventVO);
    return "redirect:/admin/sosig/event/list.do";
  }

  @RequestMapping({"/info/industry/list.do"})
  public String list_industry(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    SanupVO.setPageInfo(model);
    SanupVO.setPageUnit(SanupVO.getPageUnit());

    SanupVO.setSeachAdmin(true);
    
    SanupVO.setNotUsedPagination(true);
    List list = this.service_sanup.getListIndustry(SanupVO);

    model.addAttribute("searchText", request.getParameter("searchText"));
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
    return "naksinuri_original/naksinuri/admin/info/industry_list";
  }

  @RequestMapping({"/info/industry/trash.do"})
  public String trslist_industry(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    SanupVO.setPageInfo(model);
    SanupVO.setPageUnit(SanupVO.getPageUnit());

    List list = this.service_sanup.get_trashListIndustry(SanupVO);

    model.addAttribute("searchText", request.getParameter("searchText"));
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
    return "naksinuri_original/naksinuri/admin/info/industry_trash";
  }

  @RequestMapping({"/info/industry/write.do"})
  public String industry_write(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, ModelMap model) throws Exception {
    return "naksinuri_original/naksinuri/admin/info/industry_write";
  }

  @RequestMapping({"/info/industry/ind_del.do"})
  public String ind_del(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      SanupVO.setSan_sn(strboIds[i]);
      this.service_sanup.ind_delete(SanupVO);
    }
    return "redirect:/admin/info/industry/trash.do";
  }

  @RequestMapping({"/info/industry/ind_restore.do"})
  public String ind_rst(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      SanupVO.setSan_sn(strboIds[i]);
      this.service_sanup.ind_restore(SanupVO);
    }
    return "redirect:/admin/info/industry/trash.do";
  }

  @RequestMapping({"/info/industry/gotrash_ind.do"})
  public String gotrash_ind(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      SanupVO.setSan_sn(strboIds[i]);
      this.service_sanup.gotrash_indlist(SanupVO);
    }
    return "redirect:/admin/info/industry/list.do";
  }

  @RequestMapping({"/info/industry/ind_insert.do"})
  public void ind_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    
    List result = null;
    List result2 = null;
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
      result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(result);
    }

    if (!files.isEmpty()) {
      result2 = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      atchFileId = this.fileMngService.insertFileInfs_naksinuri_original(result2);
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
    
    if(SanupVO.getSan_homepage() != null && SanupVO.getSan_homepage().length() != 0){//홈페이지
    	SanupVO.setSan_homepage(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_homepage()));
    }
    if(SanupVO.getSan_content() != null && SanupVO.getSan_content().length() != 0){//회사 소개글
    	SanupVO.setSan_content(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_content()));
    }
    if(SanupVO.getSan_address() != null && SanupVO.getSan_address().length() != 0){//상세주소
    	SanupVO.setSan_address(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_address()));
    }

    this.service_sanup.ind_insert(SanupVO);

      response.setCharacterEncoding("UTF-8"); 
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter writer = response.getWriter();
	  writer.println("<script type='text/javascript'>");
	  writer.println("alert('운영자 심의후 게시판 등록이 완료됩니다.')");
	  writer.println("location.href='/admin/info/industry/list.do'");
	  writer.println("</script>");
	  writer.flush();
    
  }

  @RequestMapping({"/info/industry/ind_find.do"})
  public String ind_find(@ModelAttribute("SanupVO") NaksinuriSanupVO SanupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriSanupVO info = this.service_sanup.ind_find(SanupVO);
    List list = this.service_sanup.ind_fsimg(SanupVO);

    NaksinuriSanupVO preview = SanupVO;
    preview.setSan_sn(SanupVO.getSan_sn());
    List<NaksinuriSanupVO> previewlist = this.service_sanup.previewlist_ind(preview);
        
    model.addAttribute("info", info);
    model.addAttribute("simglist", list);
    model.addAttribute("previewlist", previewlist);

    return "naksinuri_original/naksinuri/admin/info/industry_view";
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

    san_name = mEgovStringUtil.getHtmlStrCnvr(san_name);
    san_buisnessman = mEgovStringUtil.getHtmlStrCnvr(san_buisnessman);
    san_item = mEgovStringUtil.getHtmlStrCnvr(san_item);
    san_address3 = mEgovStringUtil.getHtmlStrCnvr(san_address3);

    SanupVO.setSan_name(san_name);
    SanupVO.setSan_buisnessman(san_buisnessman);
    SanupVO.setSan_item(san_item);
    SanupVO.setSan_address3(san_address3);
    
    if(SanupVO.getSan_homepage() != null && SanupVO.getSan_homepage().length() != 0){//홈페이지
    	SanupVO.setSan_homepage(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_homepage()));
    }
    if(SanupVO.getSan_content() != null && SanupVO.getSan_content().length() != 0){//회사 소개글
    	SanupVO.setSan_content(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_content()));
    }
    if(SanupVO.getSan_address() != null && SanupVO.getSan_address().length() != 0){//상세주소
    	SanupVO.setSan_address(mEgovStringUtil.getHtmlStrCnvr(SanupVO.getSan_address()));
    }

    this.service_sanup.ind_update(SanupVO);

    return "redirect:/admin/info/industry/list.do";
  }

  @RequestMapping({"/policy/customersound/list.do"})
  public String voc_list(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    policyVO.setPageInfo(model);
    policyVO.setPageUnit(policyVO.getPageUnit());

    List list = this.policyService.getVOCList(policyVO);

    if (list.size() > 0)
      policyVO.setTotalPage(((NaksinuriPolicyVO)list.get(0)).getTot_cnt());
    else {
      policyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("voc_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriPolicyVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/policy/customersound_list";
  }

  @RequestMapping({"/policy/customersound/trash.do"})
  public String voc_list_trash(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    policyVO.setPageInfo(model);
    policyVO.setPageUnit(policyVO.getPageUnit());

    List list = this.policyService.getVOCTrash(policyVO);

    if (list.size() > 0)
      policyVO.setTotalPage(((NaksinuriPolicyVO)list.get(0)).getTot_cnt());
    else {
      policyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("voc_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriPolicyVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/policy/customersound_trash";
  }

  @RequestMapping({"/policy/customersound/delete_list.do"})
  public String voc_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      policyVO.setVoc_no(strboIds[i]);
      this.policyService.deleteVOC(policyVO);
    }
    return "redirect:/admin/policy/customersound/trash.do";
  }

  @RequestMapping({"/policy/customersound/restore.do"})
  public String voc_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      policyVO.setVoc_no(strboIds[i]);
      this.policyService.restoreVOC(policyVO);
    }
    return "redirect:/admin/policy/customersound/trash.do";
  }

  @RequestMapping({"/policy/customersound/gotrash_list.do"})
  public String voc_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      policyVO.setVoc_no(strboIds[i]);
      this.policyService.trashVOC(policyVO);
    }
    return "redirect:/admin/policy/customersound/list.do";
  }

  @RequestMapping({"/policy/customersound/voc_findCorp.do"})
  public String voc_findCorp(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriPolicyVO info = this.policyService.voc_findCorp(policyVO);
    model.addAttribute("info", info);

    List filelist = this.policyService.va_file(policyVO);
    model.addAttribute("filelist", filelist);

    return "naksinuri_original/naksinuri/admin/policy/customersound_view";
  }
  
  @RequestMapping(value = "/policy/customersound/voc_answer_write_act.do", method = RequestMethod.POST)
  public @ResponseBody String voc_answer_write_act(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
	PublicUtils mPublicUtils = new PublicUtils();
	JSONObject dataObj = new JSONObject();
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	if(policyVO == null){
		dataObj.put("error", "0");
		dataObj.put("msg", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
	} else {
		if(policyVO.getVoc_no() == null || policyVO.getVoc_no().length() == 0){
			dataObj.put("error", "0");
			dataObj.put("msg", "누락된 정보가 있거나 정상적인 접근이 아닙니다.");
		} else {
			dataObj.put("error", "1");
			dataObj.put("msg", "정상작동.");
			if(policyVO.getVoc_answer_content() != null && policyVO.getVoc_answer_content().length() != 0){//답변ㄱ
				policyVO.setVoc_answer_content(mEgovStringUtil.getHtmlStrCnvr(policyVO.getVoc_answer_content()));
		    }
			policyService.voc_answer_write(policyVO);
		}
	}
	LOGGER.debug(dataObj.toString());
	response.setContentType("application/json;charset=utf-8");
	response.getWriter().print(dataObj);
	
	return null;
  }

  @RequestMapping({"/sosig/news/list.do"})
  public String news_list(@ModelAttribute("newsVO") NaksinuriNewsVO newsVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    newsVO.setPageInfo(model);
    newsVO.setPageUnit(newsVO.getPageUnit());

    model.addAttribute("searchType", newsVO.getSearchType());
    model.addAttribute("searchText", newsVO.getSearchText());
    List list = this.newsService.getNewsList_admin(newsVO);

    if (list.size() > 0)
      newsVO.setTotalPage(((NaksinuriNewsVO)list.get(0)).getTot_cnt());
    else {
      newsVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriNewsVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/news_list";
  }

  @RequestMapping({"/sosig/news/trash.do"})
  public String news_trash(@ModelAttribute("newsVO") NaksinuriNewsVO newsVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    newsVO.setPageInfo(model);
    newsVO.setPageUnit(newsVO.getPageUnit());

    model.addAttribute("searchType", newsVO.getSearchType());
    model.addAttribute("searchText", newsVO.getSearchText());
    List list = this.newsService.getNewstrash_admin(newsVO);

    if (list.size() > 0)
      newsVO.setTotalPage(((NaksinuriNewsVO)list.get(0)).getTot_cnt());
    else {
      newsVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriNewsVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/sosig/news_trash";
  }

  @RequestMapping({"/sosig/news/board_findCorp.do"})
  public String board_findCorp_news(@ModelAttribute("newsVO") NaksinuriNewsVO newsVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriNewsVO info = this.newsService.board_findCorp(newsVO);

    model.addAttribute("info", info);

    return "naksinuri_original/naksinuri/admin/sosig/news_view";
  }

  @RequestMapping({"/sosig/news/delete_list.do"})
  public String deleteBoardArticle2(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("newsVO") NaksinuriNewsVO newsVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      newsVO.setMid(strboIds[i]);
      this.newsService.delete_boardlist(newsVO);
    }

    return "redirect:/admin/sosig/news/trash.do";
  }

  @RequestMapping({"/sosig/news/gotrash_list.do"})
  public String deleteBoardArticle22(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("newsVO") NaksinuriNewsVO newsVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      newsVO.setMid(strboIds[i]);
      this.newsService.gotrash_boardlist(newsVO);
    }

    return "redirect:/admin/sosig/news/list.do";
  }

  @RequestMapping({"/sosig/news/update_data.do"})
  public String news_update(@ModelAttribute("newsVO") NaksinuriNewsVO newsVO, HttpServletRequest request) throws Exception {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	
    String writer_name = request.getParameter("writer_name");
    String title = request.getParameter("title");
    String link = request.getParameter("link");

    writer_name = mEgovStringUtil.getHtmlStrCnvr(writer_name);
    title = mEgovStringUtil.getHtmlStrCnvr(title);
    link = mEgovStringUtil.getHtmlStrCnvr(link);

    newsVO.setWriter_name(writer_name);
    newsVO.setTitle(title);
    newsVO.setLink(link);

    this.newsService.update_data(newsVO);
    return "redirect:/admin/sosig/news/list.do";
  }

  @RequestMapping({"/sosig/news/insert_data.do"})
  public String news_insert(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("newsVO") NaksinuriNewsVO newsVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();	
	
    String writer_name = request.getParameter("writer_name");
    String title = request.getParameter("title");
    String link = request.getParameter("link");

    writer_name = mEgovStringUtil.getHtmlStrCnvr(writer_name);
    title = mEgovStringUtil.getHtmlStrCnvr(title);
    link = mEgovStringUtil.getHtmlStrCnvr(link);

    newsVO.setWriter_name(writer_name);
    newsVO.setTitle(title);
    newsVO.setLink(link);

    this.newsService.insert_data(newsVO);

    return "redirect:/admin/sosig/news/list.do";
  }

  @RequestMapping({"/info/angling/list.do"})
  public String angling_list(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, String category)
    throws Exception
  {
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());

    if (anglingVO.getCategory() == null)
      category = "";
    else {
      category = anglingVO.getCategory();
    }

    model.addAttribute("category", category);
    anglingVO.setCategory(category);
    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAnglingList_admin(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/info/angling_list";
  }

  @RequestMapping({"/info/angling/trash.do"})
  public String angling_trash(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, String category)
    throws Exception
  {
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());

    if (anglingVO.getCategory() == null)
      category = "";
    else {
      category = anglingVO.getCategory();
    }

    model.addAttribute("category", category);
    anglingVO.setCategory(category);
    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAnglingtrash_admin(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/info/angling_trash";
  }

  @RequestMapping({"/info/angling/riverlist.do"})
  public String angling_river_list(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, String category)
    throws Exception
  {
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());

    String category_param = request.getParameter("category");
    anglingVO.setCategory(category_param);
    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAngling_river_List_admin(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    model.addAttribute("category", category_param);

    return "naksinuri_original/naksinuri/admin/info/angling_list";
  }

  @RequestMapping({"/info/angling/rivertrash.do"})
  public String angling_river_listt(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, String category)
    throws Exception
  {
    anglingVO.setPageInfo(model);
    anglingVO.setPageUnit(anglingVO.getPageUnit());

    if (anglingVO.getCategory() == null)
      category = "";
    else {
      category = anglingVO.getCategory();
    }

    model.addAttribute("category", category);
    anglingVO.setCategory(category);
    model.addAttribute("searchType", anglingVO.getSearchType());
    model.addAttribute("searchText", anglingVO.getSearchText());
    List list = this.anglingService.getAngling_river_trash_admin(anglingVO);

    if (list.size() > 0)
      anglingVO.setTotalPage(((NaksinuriAnglingVO)list.get(0)).getTot_cnt());
    else {
      anglingVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriAnglingVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/info/angling_trash";
  }

  @RequestMapping({"/info/lab/list.do"})
  public String lab_list(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());

    boardVO.setBo_type("lab");

    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    List list = this.service.select_lab_list(boardVO);

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/info/lab_list";
  }

  @RequestMapping({"/info/angling/board_findCorp.do"})
  public String board_findCorp_angling(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    NaksinuriAnglingVO info = this.anglingService.board_findCorp(anglingVO);

    model.addAttribute("info", info);

    return "naksinuri_original/naksinuri/admin/info/angling_view";
  }

  @RequestMapping({"/info/angling/delete_list.do"})
  public String delete_angling(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      anglingVO.setMid(strboIds[i]);
      this.anglingService.delete_boardlist(anglingVO);
    }
    String category = "";
    String isftv = request.getParameter("ftv");
    String category_param = request.getParameter("category");

    if (isftv.equals("1")) {
      category = "";
      model.addAttribute("category", category);
      return "redirect:/admin/info/angling/list.do";
    }
    if (category_param.equals("민물"))
      category = "민물";
    else if (category_param.equals("바다"))
      category = "바다";
    else if (category_param.equals("루어")) {
      category = "루어";
    }

    model.addAttribute("category", category);
    return "redirect:/admin/info/angling/riverlist.do";
  }

  @RequestMapping({"/info/angling/restore.do"})
  public String restore_angling(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      anglingVO.setMid(strboIds[i]);
      this.anglingService.restore_boardlist(anglingVO);
    }
    String category = "";
    String isftv = request.getParameter("ftv");
    String category_param = request.getParameter("category");

    if (isftv.equals("1")) {
      category = "";
      model.addAttribute("category", category);
      return "redirect:/admin/info/angling/trash.do";
    }
    if (category_param.equals("민물"))
      category = "민물";
    else if (category_param.equals("바다"))
      category = "바다";
    else if (category_param.equals("루어")) {
      category = "루어";
    }

    model.addAttribute("category", category);
    return "redirect:/admin/info/angling/rivertrash.do";
  }

  @RequestMapping({"/info/angling/delete_trash.do"})
  public String delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      anglingVO.setMid(strboIds[i]);
      this.anglingService.delete_trashlist(anglingVO);
    }

    String category = "";
    String isftv = request.getParameter("ftv");
    String category_param = request.getParameter("category");

    if (isftv.equals("1")) {
      category = "";
      model.addAttribute("category", category);
      return "redirect:/admin/info/angling/trash.do";
    }
    if (category_param.equals("민물"))
      category = "민물";
    else if (category_param.equals("바다"))
      category = "바다";
    else if (category_param.equals("루어")) {
      category = "루어";
    }

    model.addAttribute("category", category);
    return "redirect:/admin/info/angling/rivertrash.do";
  }

  @RequestMapping({"/info/report/delete_trash.do"})
  public String delete_report(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("reportVO") ReportVO reportVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      reportVO.setReport_sn(strboIds[i]);
      this.service_report.delete_reportlist(reportVO);
    }

    return "redirect:/admin/info/report/trash.do";
  }

  @RequestMapping({"/info/angling/update_data.do"})
  public String update_angling(@ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, HttpServletRequest request) throws Exception
  {
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();	    
	    
	  String title = request.getParameter("title");
	  String writer_name = request.getParameter("writer_name");
	  String body = request.getParameter("body");
	    
	  if (title != null) {
	  	title = mEgovStringUtil.getHtmlStrCnvr(title);
	  	anglingVO.setTitle(title);
	  }
	  if (writer_name != null) {
	  	writer_name = mEgovStringUtil.getHtmlStrCnvr(writer_name);
	  	anglingVO.setWriter_name(writer_name);
	  }
	  if (body != null) {
	  	body = mEgovStringUtil.getHtmlStrCnvr(body);
	  	anglingVO.setBody(body);
	  }
    this.anglingService.update_data(anglingVO);
    return "redirect:/admin/info/angling/list.do";
  }

  @RequestMapping({"/info/angling/insert_data.do"})
  public String angling_insert(MultipartHttpServletRequest multiRequest, HttpServletRequest request, SessionStatus status, @ModelAttribute("anglingVO") NaksinuriAnglingVO anglingVO, BindingResult bindingResult, ModelMap model)
    throws Exception
  {
	EgovStringUtil mEgovStringUtil = new EgovStringUtil();	

    String category = "";
    String category_param = request.getParameter("category");
    String isftv = request.getParameter("ftv");
    String title = request.getParameter("title");
    String writer_name = request.getParameter("writer_name");
    String body = request.getParameter("body");
    
    if (title != null) {
    	title = mEgovStringUtil.getHtmlStrCnvr(title);
    	anglingVO.setTitle(title);
      }
    if (writer_name != null) {
    	writer_name = mEgovStringUtil.getHtmlStrCnvr(writer_name);
    	anglingVO.setWriter_name(writer_name);
    }
    if (body != null) {
    	body = mEgovStringUtil.getHtmlStrCnvr(body);
    	anglingVO.setBody(body);
    }
    this.anglingService.insert_data(anglingVO);

    if (isftv.equals("1")) {
      category = "";
      model.addAttribute("category", category);
      return "redirect:/admin/info/angling/list.do";
    }if (isftv.equals("0")) {
      if (category_param.equals("민물"))
        category = "민물";
      else if (category_param.equals("바다"))
        category = "바다";
      else if (category_param.equals("루어"))
        category = "루어";
    }
    model.addAttribute("category", category);
    return "redirect:/admin/info/angling/riverlist.do";
  }

  @RequestMapping({"/survey/survey/delete_list.do"})
  public String survey_delete(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      surveyVO.setSv_id(strboIds[i]);
      this.service.delete_survey(surveyVO);
    }
    return "redirect:/admin/survey/survey/trash.do";
  }

  @RequestMapping({"/survey/survey/restore.do"})
  public String survey_restore(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      surveyVO.setSv_id(strboIds[i]);
      this.service.restore_survey(surveyVO);
    }
    return "redirect:/admin/survey/survey/trash.do";
  }

  @RequestMapping({"/survey/survey/gotrash_list.do"})
  public String survey_delete_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      surveyVO.setSv_id(strboIds[i]);
      this.service.gotrash_survey(surveyVO);
    }
    return "redirect:/admin/survey/survey/list.do";
  }

  @RequestMapping({"/survey/survey_ajax.do"})
  public String survey_ajax(ModelMap model, HttpServletRequest request)
  {
    String anID = request.getParameter("anID");
    String Type = request.getParameter("Type");
    model.addAttribute("anID", anID);
    model.addAttribute("Type", Type);
    return "/naksinuri_original/naksinuri/admin/survey/survey_ajax";
  }

  @RequestMapping({"/survey/survey/list.do"})
  public String survey_list(String bo_cate, @ModelAttribute("surveyVO") SurveyVO surveyVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    surveyVO.setPageInfo(model);
    surveyVO.setPageUnit(surveyVO.getPageUnit());

    model.addAttribute("searchType", surveyVO.getSearchType());
    model.addAttribute("searchText", surveyVO.getSearchText());

    List list = this.service.survey_select_list(surveyVO);

    if (list.size() > 0)
      surveyVO.setTotalPage(((SurveyVO)list.get(0)).getTot_cnt());
    else {
      surveyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((SurveyVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/survey/survey_list";
  }

  @RequestMapping({"/survey/survey/trash.do"})
  public String survey_trash(String bo_cate, @ModelAttribute("surveyVO") SurveyVO surveyVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    surveyVO.setPageInfo(model);
    surveyVO.setPageUnit(surveyVO.getPageUnit());

    model.addAttribute("searchType", surveyVO.getSearchType());
    model.addAttribute("searchText", surveyVO.getSearchText());

    List list = this.service.survey_select_trash(surveyVO);

    if (list.size() > 0)
      surveyVO.setTotalPage(((SurveyVO)list.get(0)).getTot_cnt());
    else {
      surveyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((SurveyVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/survey/survey_trash";
  }

  @RequestMapping({"/survey/survey/endlist.do"})
  public String survey_end_list(String bo_cate, @ModelAttribute("surveyVO") SurveyVO surveyVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    surveyVO.setPageInfo(model);
    surveyVO.setPageUnit(surveyVO.getPageUnit());

    model.addAttribute("searchType", surveyVO.getSearchType());
    model.addAttribute("searchText", surveyVO.getSearchText());

    List list = this.service.end_survey_select_list(surveyVO);

    if (list.size() > 0)
      surveyVO.setTotalPage(((SurveyVO)list.get(0)).getTot_cnt());
    else {
      surveyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((SurveyVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/survey/survey_endlist";
  }

  @RequestMapping({"/survey/survey/endtrash.do"})
  public String survey_end_trash(String bo_cate, @ModelAttribute("surveyVO") SurveyVO surveyVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    surveyVO.setPageInfo(model);
    surveyVO.setPageUnit(surveyVO.getPageUnit());

    model.addAttribute("searchType", surveyVO.getSearchType());
    model.addAttribute("searchText", surveyVO.getSearchText());

    List list = this.service.end_survey_select_trash(surveyVO);

    if (list.size() > 0)
      surveyVO.setTotalPage(((SurveyVO)list.get(0)).getTot_cnt());
    else {
      surveyVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((SurveyVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/survey/survey_endtrash";
  }

  @RequestMapping({"/survey/survey/findCorp.do"})
  public String survey_findCorp(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    SurveyVO info = this.service.select_survey(surveyVO);
    List quest = this.service.select_surveyq_list(surveyVO);
    List qitem = this.service.select_surveyqi_list(surveyVO);
    List anscnt = this.service.survey_anscnt(surveyVO);
    List answerole = this.service.survey_answerole_list(surveyVO);

    model.addAttribute("info", info);
    model.addAttribute("quest", quest);
    model.addAttribute("qitem", qitem);
    model.addAttribute("aan", anscnt);
    model.addAttribute("answerole", answerole);

    return "naksinuri_original/naksinuri/admin/survey/survey_view";
  }
  
  @RequestMapping({"/survey/survey/copy_data.do"})
  public String survey_copy_data(HttpServletRequest request, HttpServletResponse response, 
		  SessionStatus status, @ModelAttribute("surveyVO") SurveyVO surveyVO, BindingResult bindingResult, ModelMap model) throws Exception {
	  
	  SurveyVO copySurveyVO = new SurveyVO();
	  copySurveyVO.setSv_id(surveyVO.getSv_id());
	  copySurveyVO = this.service.select_survey(copySurveyVO);
	  
	  String ORI_SV_ID = copySurveyVO.getSv_id();
	  String ORI_SV_SUBJECT = copySurveyVO.getSv_subject();
	  
	  copySurveyVO.setSv_subject("(복사본)"+ORI_SV_SUBJECT);
	  this.service.insert_survey_data(copySurveyVO);
	  
	  SurveyVO findNewSvId = this.service.select_survey_subject(copySurveyVO);
	  copySurveyVO.setSv_id(findNewSvId.getSv_id());
	  String NEW_SV_ID = copySurveyVO.getSv_id();

	  List<SurveyVO> quest = this.service.select_surveyq_list(surveyVO);
	  
	  for (SurveyVO copyQuest : quest) {		  
		  copyQuest.setSq_order(copyQuest.getSq_order());
		  copyQuest.setSv_id(NEW_SV_ID);
		  copyQuest.setSvq_num(copyQuest.getSvq_num());
		  copyQuest.setSvq_mxcnt(copyQuest.getSvq_mxcnt());
		  copyQuest.setSvq_type(copyQuest.getSvq_type());
		  copyQuest.setSvq_subject(copyQuest.getSvq_subject());		  
		  this.service.insert_survey_quest(copyQuest);
		  
		  String ORI_SQ_ID  =  copyQuest.getSq_id();
		  String type = copyQuest.getSvq_type();
		  List<SurveyVO> qitem = this.service.select_surveyqi_list(surveyVO);
		 
		  SurveyVO findNewSqId = this.service.select_surveyq_subject(copyQuest);
		  String NEW_SQ_ID  =  findNewSqId.getSq_id();
		  
		  if (type.equals("O")) {
			  for(SurveyVO copyQItem : qitem){ 
				  String compare = copyQItem.getSq_id();
				  copyQItem.setSv_id(NEW_SV_ID);
				  copyQItem.setSq_id(compare);
				  copyQItem.setSvq_item_num(copyQItem.getSvq_item_num());
				  copyQItem.setSvq_item_txt(copyQItem.getSvq_item_txt());
				  copyQItem.setSqi_etc(copyQItem.getSqi_etc());
				  
				  if(ORI_SQ_ID.equals(compare)){
					  copyQItem.setSq_id(NEW_SQ_ID);
					  this.service.insert_survey_quest_item(copyQItem);	  					  					  
				  }
			  }
		  }
    }
	  return null;
  }
  
  @RequestMapping({"/survey/survey/insert_data.do"})
  public String survey_insert_data(MultipartHttpServletRequest multiRequest, HttpServletRequest request, HttpServletResponse response, 
		  SessionStatus status, @ModelAttribute("surveyVO") SurveyVO surveyVO, BindingResult bindingResult, ModelMap model) throws Exception {
    
	  String[] arrayStr = request.getParameterValues("anID");
	  String item_cnt = String.valueOf(arrayStr.length);
	  surveyVO.setSv_item_cnt(item_cnt);
	  
	  String reg_mb_id = request.getParameter("reg_mb_id");
	  String sv_subject = request.getParameter("sv_subject");
	    
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();

	  if (reg_mb_id != null) {
		  reg_mb_id = mEgovStringUtil.getHtmlStrCnvr(reg_mb_id);
		  if(reg_mb_id.length() > 20)
			  reg_mb_id = reg_mb_id.substring(0,19);
		  surveyVO.setReg_mb_id(reg_mb_id);
	  }
	  if (sv_subject != null) {
		  sv_subject = mEgovStringUtil.getHtmlStrCnvr(sv_subject);
		  surveyVO.setSv_subject(sv_subject);
	  }
	  
	  if(surveyVO.getReg_mb_id() != null && surveyVO.getReg_mb_id().length() != 0){//
		  surveyVO.setReg_mb_id(mEgovStringUtil.getHtmlStrCnvr(surveyVO.getReg_mb_id()));
	  }
	  if(surveyVO.getSv_subject() != null && surveyVO.getSv_subject().length() != 0){//
		  surveyVO.setSv_subject(mEgovStringUtil.getHtmlStrCnvr(surveyVO.getSv_subject()));
	  }
	  

	  this.service.insert_survey_data(surveyVO);
	  SurveyVO info = this.service.select_survey_subject(surveyVO);
	  surveyVO.setSv_id(info.getSv_id());

	  for (int i = 0; i < arrayStr.length; i++) {
		  String subject = request.getParameter("svq_subject" + arrayStr[i] + "");
		  String type = request.getParameter("svq_type" + arrayStr[i] + "");
		  String mxcnt = request.getParameter("svqi_mxsel" + arrayStr[i] + "");
		  
		  surveyVO.setSvq_subject(mEgovStringUtil.getHtmlStrCnvr(subject));
		  surveyVO.setSvq_type(type);
		  surveyVO.setSvq_mxcnt(mxcnt);
		  surveyVO.setSq_order(String.valueOf(i + 1));
		  surveyVO.setSvq_num(String.valueOf(i + 1));
		  this.service.insert_survey_quest(surveyVO);
		  if (type.equals("O")) {
			  String[] itemTxt = request.getParameterValues("svq_svqi_txt" + arrayStr[i] + "");
			  String[] sqi_etc = request.getParameterValues("svq_svqi_etc" + arrayStr[i] + "");
			  SurveyVO infoq = this.service.select_surveyq_subject(surveyVO);
			  surveyVO.setSq_id(infoq.getSq_id());
			  	for (int j = 0; j < itemTxt.length; j++) {
			  		surveyVO.setSvq_item_txt(mEgovStringUtil.getHtmlStrCnvr(itemTxt[j]));
			  		surveyVO.setSqi_etc(sqi_etc[j]);
			  		surveyVO.setSvq_item_num(String.valueOf(j + 1));
			  		this.service.insert_survey_quest_item(surveyVO);
			  	}
		  }
    }
    return "redirect:/admin/survey/survey/list.do";
  }
  
  @RequestMapping({"/survey/survey_update_ajax.do"})
  public String survey_update_ajax(@ModelAttribute("surveyVO") SurveyVO surveyVO, ModelMap model, HttpServletRequest request) throws Exception
  {	 
		String anID = request.getParameter("anID");
		String Type = request.getParameter("Type");

		model.addAttribute("anID", anID);
		model.addAttribute("Type", Type);

    return "/naksinuri_original/naksinuri/admin/survey/survey_update_ajax";
  }
  
  @RequestMapping({"/survey/survey/update_data.do"})
  public String survey_update_data(HttpServletRequest request, HttpServletResponse response, 
		  SessionStatus status, @ModelAttribute("surveyVO") SurveyVO surveyVO, BindingResult bindingResult, ModelMap model) throws Exception {
	  	
	  	SurveyVO info = this.service.select_survey(surveyVO);
	    List quest = this.service.select_surveyq_list(surveyVO);
	    List qitem = this.service.select_surveyqi_list(surveyVO);
	    List anscnt = this.service.survey_anscnt(surveyVO);
	
	    model.addAttribute("info", info);
	    model.addAttribute("quest", quest);
	    model.addAttribute("qitem", qitem);
	    model.addAttribute("aan", anscnt);
	    
	  return "naksinuri_original/naksinuri/admin/survey/survey_update";
  }
  
  @RequestMapping({"/survey/survey/update_act.do"})
  public String survey_update_act(HttpServletRequest request, HttpServletResponse response, 
		  SessionStatus status, @ModelAttribute("surveyVO") SurveyVO surveyVO, BindingResult bindingResult, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("LoginVO");
		EgovStringUtil mEgovStringUtil = new EgovStringUtil();
		surveyVO.setMod_mb_id(loginVO.getMBR_ID());
		//설문조사 메인정보 수정
		String sv_subject = request.getParameter("sv_subject");
		if (sv_subject != null) {
			sv_subject = mEgovStringUtil.getHtmlStrCnvr(sv_subject);
			surveyVO.setSv_subject(sv_subject);
		}
		if(surveyVO.getSv_subject() != null && surveyVO.getSv_subject().length() != 0){//
			surveyVO.setSv_subject(mEgovStringUtil.getHtmlStrCnvr(surveyVO.getSv_subject()));
		}
		
		this.service.survey_update_data(surveyVO);
		
		String[] arrayStr = request.getParameterValues("anID");
		String item_cnt = String.valueOf(arrayStr.length);
		surveyVO.setSv_item_cnt(item_cnt);
		
		//질문 삭제 시 비교 리스트
		ArrayList<String> allSqIdlList = new ArrayList<String>();
		//질문아이템 삭제 시 비교 리스트
		ArrayList<String> allSqiIdlList = new ArrayList<String>();
		
		List<SurveyVO> quest = this.service.select_surveyq_list(surveyVO);
		for(SurveyVO item : quest){
			String allSqId = item.getSq_id();
			allSqIdlList.add(allSqId);
		}
		List<SurveyVO> qitem = this.service.select_surveyqi_list(surveyVO);
		for(SurveyVO item : qitem){
			String allSqiId = item.getSqi_id();
			allSqiIdlList.add(allSqiId);
		}

		for (int i = 0; i < arrayStr.length; i++) {
			String sqId = request.getParameter("sq_id" + arrayStr[i] + "");
			String subject = request.getParameter("svq_subject" + arrayStr[i] + "");
			String type = request.getParameter("svq_type" + arrayStr[i] + "");
			String mxcnt = request.getParameter("svqi_mxsel" + arrayStr[i] + "");
			String[] sqiId = request.getParameterValues("svq_svqi_id" + arrayStr[i] + "");
			String[] itemTxt = request.getParameterValues("svq_svqi_txt" + arrayStr[i] + "");
			String[] sqi_etc = request.getParameterValues("svq_svqi_etc" + arrayStr[i] + "");

			surveyVO.setSvq_subject(mEgovStringUtil.getHtmlStrCnvr(subject));
			surveyVO.setSq_id(sqId);
			surveyVO.setSvq_type(type);
			surveyVO.setSvq_mxcnt(mxcnt);
			
			//기존 질문정보와 추가 질문정보 구분
			if (!sqId.equals("") && sqId.length() != 0) {
				//기존 정보에서 항목삭제한 질문 리스트에 담기
				if (sqId != "" && allSqIdlList.contains(sqId)) {
					allSqIdlList.remove(sqId);
				}
				this.service.survey_update_quest(surveyVO);
				if (type.equals("O")) { //객관식 일 때
					for (int j = 0; j < sqiId.length; j++) {
						surveyVO.setSqi_id(sqiId[j]);
						//기존 질문정보에 응답 추가한 경우
						if (sqiId[j].equals("") && sqiId[j].length() == 0) {
							surveyVO.setSvq_item_txt(mEgovStringUtil.getHtmlStrCnvr(itemTxt[j]));
							surveyVO.setSqi_etc(sqi_etc[j]);
							surveyVO.setSvq_item_num(String.valueOf(j + 1));
							this.service.insert_survey_quest_item(surveyVO);
						} else {
							surveyVO.setSvq_item_txt(mEgovStringUtil.getHtmlStrCnvr(itemTxt[j]));
							surveyVO.setSqi_etc(sqi_etc[j]);
							this.service.survey_update_quest_item(surveyVO);
						}
						//기존 정보에서 항목삭제한 응답 리스트에 담기
						if (sqiId[j] != "" && allSqiIdlList.contains(sqiId[j])) {
							allSqiIdlList.remove(sqiId[j]);
						}
					}
				}
			} else { //추가 질문정보
				surveyVO.setSq_order(String.valueOf(i + 1));
				surveyVO.setSvq_num(String.valueOf(i + 1));
				this.service.insert_survey_quest(surveyVO);
				if (type.equals("O")) {
					SurveyVO infoq = this.service.select_surveyq_subject(surveyVO);
					surveyVO.setSq_id(infoq.getSq_id());
					for (int j = 0; j < itemTxt.length; j++) {
						surveyVO.setSvq_item_txt(mEgovStringUtil.getHtmlStrCnvr(itemTxt[j]));
						surveyVO.setSqi_etc(sqi_etc[j]);
						surveyVO.setSvq_item_num(String.valueOf(j + 1));
						this.service.insert_survey_quest_item(surveyVO);
					}
				}
			}
		}
		//질문 삭제
		for (String delItem : allSqIdlList) {
			SurveyVO delsqId = new SurveyVO();
			delsqId.setSv_id(surveyVO.getSv_id());
			delsqId.setSq_id(delItem);
			this.service.delete_survey_quest(delsqId);
		}
		//질문 아이템 삭제
		for (String sqidelItem : allSqiIdlList) {
			SurveyVO delsqiId = new SurveyVO();
			delsqiId.setSqi_id(sqidelItem);
			this.service.delete_survey_questitem(delsqiId);
		}
		
	  return "redirect:/admin/survey/survey/list.do";
  }
  
  @RequestMapping({"/listExcelDownload.do"})
  public View listExcelDownload(HttpServletRequest req, HttpServletResponse resp, SurveyVO surveyVO, Model model)
    throws Exception
  {
    List qitem = this.service.select_surveyqi_list(surveyVO);
    List answerole = this.service.survey_answerole_list2(surveyVO);
    List quest = this.service.select_surveyq_list(surveyVO);
    List get_Excelitem = this.service.get_Excelitem(surveyVO);
    List anscnt = this.service.survey_anscnt(surveyVO);

    model.addAttribute("first_row", get_Excelitem);
    model.addAttribute("q_list", qitem);
    model.addAttribute("list", answerole);
    model.addAttribute("quest", quest);
    model.addAttribute("ann", anscnt);

    return new ListExcelView();
  }

  @RequestMapping(value={"/static_boardcnt.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<NaksinuriStatisticVO> get_boardcnt(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model) throws Exception
  {
    List list = this.service_statistic.static_boardcnt(staticVO);

    return list;
  }

  @RequestMapping({"/static/site/list.do"})
  public String static_list(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String day_type = staticVO.getSearchType();
    LOGGER.debug("/static/site/list.do - static_list , day_type : " + day_type);
    if (day_type == null) {
      day_type = "NULL day";
    }
    else if (day_type.equals("30"))
      day_type = "1 MONTH";
    else {
      day_type = "'" + day_type + "' day";
    }

    staticVO.setSearchType(day_type);

    staticVO.setHours(7);
    //NaksinuriStatisticVO visit_cnt = this.service_statistic.get_visitcnt(staticVO);
    //NaksinuriStatisticVO page_cnt = this.service_statistic.get_pagecnt(staticVO);

    //NaksinuriStatisticVO compare_views = this.service_statistic.get_compareviews(staticVO);
    //NaksinuriStatisticVO compare_views2 = this.service_statistic.get_compareviews2(staticVO);

    return "naksinuri_original/naksinuri/admin/static/site_list";
  }

  @RequestMapping({"/static/siteday/list.do"})
  public String static_daylist(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    staticVO.setHours(1);
    NaksinuriStatisticVO visit_cnt = this.service_statistic.get_visitcnt_day(staticVO);
    NaksinuriStatisticVO page_cnt = this.service_statistic.get_pagecnt_day(staticVO);
    List board_top10 = this.service_statistic.get_boardtop10_day(staticVO);
    NaksinuriStatisticVO compare_views = this.service_statistic.get_compareviews(staticVO);
    NaksinuriStatisticVO compare_views2 = this.service_statistic.get_compareviews2(staticVO);

    staticVO.setCnt(compare_views2.getCnt());
    model.addAttribute("compare_view2", Integer.valueOf(compare_views2.getCnt()));
    staticVO.setCnt(compare_views.getCnt());
    model.addAttribute("compare_view", Integer.valueOf(compare_views.getCnt()));
    model.addAttribute("board_top10", board_top10);
    staticVO.setCnt(visit_cnt.getCnt());
    model.addAttribute("visit_total", Integer.valueOf(visit_cnt.getCnt()));

    staticVO.setCnt(page_cnt.getCnt());
    model.addAttribute("page_total", Integer.valueOf(page_cnt.getCnt()));

    return "naksinuri_original/naksinuri/admin/static/site_listd";
  }

  @RequestMapping({"/static/sitemonth/list.do"})
  public String static_monthlist(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    staticVO.setHours(30);
    NaksinuriStatisticVO visit_cnt = this.service_statistic.get_visitcnt_month(staticVO);
    NaksinuriStatisticVO page_cnt = this.service_statistic.get_pagecnt_month(staticVO);
    List board_top10 = this.service_statistic.get_boardtop10_month(staticVO);

    NaksinuriStatisticVO compare_views = this.service_statistic.get_compareviews(staticVO);
    NaksinuriStatisticVO compare_views2 = this.service_statistic.get_compareviews2(staticVO);

    staticVO.setCnt(compare_views2.getCnt());
    model.addAttribute("compare_view2", Integer.valueOf(compare_views2.getCnt()));
    staticVO.setCnt(compare_views.getCnt());
    model.addAttribute("compare_view", Integer.valueOf(compare_views.getCnt()));

    model.addAttribute("board_top10", board_top10);
    staticVO.setCnt(visit_cnt.getCnt());
    model.addAttribute("visit_total", Integer.valueOf(visit_cnt.getCnt()));

    staticVO.setCnt(page_cnt.getCnt());
    model.addAttribute("page_total", Integer.valueOf(page_cnt.getCnt()));

    return "naksinuri_original/naksinuri/admin/static/site_listm";
  }

  @RequestMapping({"/static/sitemyself/list.do"})
  public String static_myselflist(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    NaksinuriStatisticVO visit_cnt = this.service_statistic.get_visitcnt_myself(staticVO);
    NaksinuriStatisticVO page_cnt = this.service_statistic.get_pagecnt_myself(staticVO);
    List board_top10 = this.service_statistic.get_boardtop10_self(staticVO);
    NaksinuriStatisticVO compare_views = this.service_statistic.get_compareviews(staticVO);
    NaksinuriStatisticVO compare_views2 = this.service_statistic.get_compareviews2(staticVO);

    staticVO.setCnt(compare_views2.getCnt());
    model.addAttribute("compare_view2", Integer.valueOf(compare_views2.getCnt()));
    staticVO.setCnt(compare_views.getCnt());
    model.addAttribute("compare_view", Integer.valueOf(compare_views.getCnt()));
    model.addAttribute("starts", request.getParameter("start_dt"));
    model.addAttribute("ends", request.getParameter("end_dt"));

    model.addAttribute("board_top10", board_top10);
    staticVO.setCnt(visit_cnt.getCnt());
    model.addAttribute("visit_total", Integer.valueOf(visit_cnt.getCnt()));

    staticVO.setCnt(page_cnt.getCnt());
    model.addAttribute("page_total", Integer.valueOf(page_cnt.getCnt()));

    return "naksinuri_original/naksinuri/admin/static/myselfsite_list";
  }

  @RequestMapping({"/static/invitos/list.do"})
  public String invitos_list(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    NaksinuriStatisticVO mobile_prcnt = this.service_statistic.getmobile_prcnt(staticVO);

    NaksinuriStatisticVO pc_prcnt = this.service_statistic.getpc_prcnt(staticVO);

    NaksinuriStatisticVO most_pc = this.service_statistic.getmost_pc(staticVO);
    NaksinuriStatisticVO most_mobile = this.service_statistic.getmost_mobile(staticVO);
    List pctop5 = this.service_statistic.getpcOStop5(staticVO);
    List mobiletop5 = this.service_statistic.getmobileOStop5(staticVO);

    if (most_pc == null) {
      LOGGER.debug("피시");
      model.addAttribute("most_pc", "최다 이용 PC OS정보가 없습니다.");
    } else {
      model.addAttribute("most_pc", most_pc.getStatistic_os());
    }

    if (most_mobile == null) {
      LOGGER.debug("최다 이용 모바일os 정보가 없습니다. ");
      model.addAttribute("most_mobile", "정보가 없습니다.");
    } else {
      model.addAttribute("most_mobile", most_mobile.getStatistic_os());
    }
    model.addAttribute("OSpc_list", pctop5);
    model.addAttribute("OSmobile_list", mobiletop5);
    model.addAttribute("mobile_prcnt", mobile_prcnt.getMobile_prcnt());
    model.addAttribute("pc_prcnt", pc_prcnt.getPc_prcnt());

    return "naksinuri_original/naksinuri/admin/static/invitos_list";
  }

  @RequestMapping({"/static/invitos/listd.do"})
  public String invitos_listday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    NaksinuriStatisticVO mobile_prcnt = this.service_statistic.getmobile_prcntday(staticVO);
    NaksinuriStatisticVO pc_prcnt = this.service_statistic.getpc_prcntday(staticVO);
    NaksinuriStatisticVO most_pc = this.service_statistic.getmost_pcday(staticVO);
    NaksinuriStatisticVO most_mobile = this.service_statistic.getmost_mobileday(staticVO);
    List pctop5 = this.service_statistic.getpcOStop5day(staticVO);
    List mobiletop5 = this.service_statistic.getmobileOStop5day(staticVO);

    if (most_pc == null) {
      LOGGER.debug("피시");
      model.addAttribute("most_pc", "최다 이용 PC OS정보가 없습니다.");
    } else {
      model.addAttribute("most_pc", most_pc.getStatistic_os());
    }

    if (most_mobile == null) {
      LOGGER.debug("최다 이용 모바일os 정보가 없습니다. ");
      model.addAttribute("most_mobile", "정보가 없습니다.");
    } else {
      model.addAttribute("most_mobile", most_mobile.getStatistic_os());
    }
    model.addAttribute("OSpc_list", pctop5);
    model.addAttribute("OSmobile_list", mobiletop5);
    model.addAttribute("mobile_prcnt", mobile_prcnt.getMobile_prcnt());
    model.addAttribute("pc_prcnt", pc_prcnt.getPc_prcnt());

    return "naksinuri_original/naksinuri/admin/static/invitos_listd";
  }

  @RequestMapping({"/static/invitos/listm.do"})
  public String invitos_listmonth(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    NaksinuriStatisticVO mobile_prcnt = this.service_statistic.getmobile_prcntmonth(staticVO);
    NaksinuriStatisticVO pc_prcnt = this.service_statistic.getpc_prcntmonth(staticVO);
    NaksinuriStatisticVO most_pc = this.service_statistic.getmost_pcmonth(staticVO);
    NaksinuriStatisticVO most_mobile = this.service_statistic.getmost_mobilemonth(staticVO);
    List pctop5 = this.service_statistic.getpcOStop5month(staticVO);
    List mobiletop5 = this.service_statistic.getmobileOStop5month(staticVO);

    if (most_pc == null) {
      LOGGER.debug("피시");
      model.addAttribute("most_pc", "최다 이용 PC OS정보가 없습니다.");
    } else {
      model.addAttribute("most_pc", most_pc.getStatistic_os());
    }

    if (most_mobile == null) {
      LOGGER.debug("최다 이용 모바일os 정보가 없습니다. ");
      model.addAttribute("most_mobile", "정보가 없습니다.");
    } else {
      model.addAttribute("most_mobile", most_mobile.getStatistic_os());
    }
    model.addAttribute("OSpc_list", pctop5);
    model.addAttribute("OSmobile_list", mobiletop5);
    model.addAttribute("mobile_prcnt", mobile_prcnt.getMobile_prcnt());
    model.addAttribute("pc_prcnt", pc_prcnt.getPc_prcnt());

    return "naksinuri_original/naksinuri/admin/static/invitos_listm";
  }

  @RequestMapping({"/static/invitos/listself.do"})
  public String os_myself(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String tmp = "데이터가 없습니다.";

    NaksinuriStatisticVO mobile_prcnt = this.service_statistic.getmobile_prcntself(staticVO);
    NaksinuriStatisticVO pc_prcnt = this.service_statistic.getpc_prcntself(staticVO);
    NaksinuriStatisticVO most_pc = this.service_statistic.getmost_pcself(staticVO);
    NaksinuriStatisticVO most_mobile = this.service_statistic.getmost_mobileself(staticVO);
    List pctop5 = this.service_statistic.getpcOStop5self(staticVO);
    List mobiletop5 = this.service_statistic.getmobileOStop5self(staticVO);

    model.addAttribute("starts", request.getParameter("start_dt"));
    model.addAttribute("ends", request.getParameter("end_dt"));

    if (pc_prcnt.getPc_prcnt() == null)
      model.addAttribute("pc_prcnt", tmp);
    else {
      model.addAttribute("pc_prcnt", pc_prcnt.getPc_prcnt());
    }

    if (mobile_prcnt.getMobile_prcnt() == null)
      model.addAttribute("mobile_prcnt", tmp);
    else {
      model.addAttribute("mobile_prcnt", mobile_prcnt.getMobile_prcnt());
    }

    if (most_mobile == null)
      model.addAttribute("most_mobile", tmp);
    else {
      model.addAttribute("most_mobile", most_mobile.getStatistic_os());
    }

    if (most_pc == null)
      model.addAttribute("most_pc", tmp);
    else {
      model.addAttribute("most_pc", most_pc.getStatistic_os());
    }

    model.addAttribute("OSpc_list", pctop5);
    model.addAttribute("OSmobile_list", mobiletop5);

    return "naksinuri_original/naksinuri/admin/static/invitos_listself";
  }

  @RequestMapping({"/static/invit/list.do"})
  public String invit_list(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    staticVO.setPageInfo(model);
    staticVO.setPageUnit(staticVO.getPageUnit());

    model.addAttribute("searchType", staticVO.getSearchType());
    model.addAttribute("searchText", staticVO.getSearchText());

    List invitlist = this.service_statistic.invitlist_week(staticVO);

    if (invitlist.size() > 0)
      staticVO.setTotalPage(((NaksinuriStatisticVO)invitlist.get(0)).getTot_cnt());
    else {
      staticVO.setTotalPage(0);
    }

    if (invitlist.size() > 0) {
      model.addAttribute("select_list", invitlist);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriStatisticVO)invitlist.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/static/invit_list";
  }

  @RequestMapping({"/static/invitday/list.do"})
  public String invit_listday(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    staticVO.setPageInfo(model);
    staticVO.setPageUnit(staticVO.getPageUnit());

    model.addAttribute("searchType", staticVO.getSearchType());
    model.addAttribute("searchText", staticVO.getSearchText());

    List invitlist = this.service_statistic.invitlist_day(staticVO);

    if (invitlist.size() > 0)
      staticVO.setTotalPage(((NaksinuriStatisticVO)invitlist.get(0)).getTot_cnt());
    else {
      staticVO.setTotalPage(0);
    }

    if (invitlist.size() > 0) {
      model.addAttribute("select_list", invitlist);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriStatisticVO)invitlist.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/static/invitday_list";
  }

  @RequestMapping({"/static/invitmonth/list.do"})
  public String invit_listmonth(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    staticVO.setPageInfo(model);
    staticVO.setPageUnit(staticVO.getPageUnit());

    model.addAttribute("searchType", staticVO.getSearchType());
    model.addAttribute("searchText", staticVO.getSearchText());

    List invitlist = this.service_statistic.invitlist_month(staticVO);

    if (invitlist.size() > 0)
      staticVO.setTotalPage(((NaksinuriStatisticVO)invitlist.get(0)).getTot_cnt());
    else {
      staticVO.setTotalPage(0);
    }

    if (invitlist.size() > 0) {
      model.addAttribute("select_list", invitlist);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriStatisticVO)invitlist.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/static/invitmonth_list";
  }

  @RequestMapping({"/static/invitself/list.do"})
  public String invit_listself(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    staticVO.setPageInfo(model);
    staticVO.setPageUnit(staticVO.getPageUnit());

    model.addAttribute("searchType", staticVO.getSearchType());
    model.addAttribute("searchText", staticVO.getSearchText());

    model.addAttribute("start_dt", request.getParameter("start_dt"));
    model.addAttribute("end_dt", request.getParameter("end_dt"));
    List invitlist = this.service_statistic.invitlist_self(staticVO);

    if (invitlist.size() > 0)
      staticVO.setTotalPage(((NaksinuriStatisticVO)invitlist.get(0)).getTot_cnt());
    else {
      staticVO.setTotalPage(0);
    }

    if (invitlist.size() > 0) {
      model.addAttribute("select_list", invitlist);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriStatisticVO)invitlist.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/static/invitself_list";
  }

  @RequestMapping({"/static/invadindex/list.do"})
  public String invadindex_list(String bo_cate, @ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return "naksinuri_original/naksinuri/admin/static/invadindex_list";
  }

  @RequestMapping({"/static/invadurl/list.do"})
  public String invadurl_list(String bo_cate, @ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    return "naksinuri_original/naksinuri/admin/static/invadurl_list";
  }

  @RequestMapping({"/static/likepage/list.do"})
  public String likepage_list(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    List list = this.service_statistic.board_top(staticVO);

    model.addAttribute("board_top", list);

    return "naksinuri_original/naksinuri/admin/static/likepage_list";
  }

  @RequestMapping({"/static/likepageday/list.do"})
  public String likepageday_list(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    List list = this.service_statistic.boardday_top(staticVO);

    model.addAttribute("board_top", list);

    return "naksinuri_original/naksinuri/admin/static/likepageday_list";
  }

  @RequestMapping({"/static/likepagemonth/list.do"})
  public String likepagemonth_list(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    List list = this.service_statistic.boardmonth_top(staticVO);

    model.addAttribute("board_top", list);

    return "naksinuri_original/naksinuri/admin/static/likepagemonth_list";
  }

  @RequestMapping({"/static/likepageself/list.do"})
  public String likepageself_list(@ModelAttribute("staticVO") NaksinuriStatisticVO staticVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    model.addAttribute("start_dt", request.getParameter("start_dt"));
    model.addAttribute("end_dt", request.getParameter("end_dt"));

    List list = this.service_statistic.boardself_top(staticVO);

    model.addAttribute("board_top", list);

    return "naksinuri_original/naksinuri/admin/static/likepageself_list";
  }

  @RequestMapping({"/info/mainimg/list.do"})
  public String mainimg_list(@ModelAttribute("mainimgVO") NaksinuriMainImgVO mainimgVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    mainimgVO.setPageInfo(model);
    mainimgVO.setPageUnit(mainimgVO.getPageUnit());

    model.addAttribute("searchType", mainimgVO.getSearchType());
    model.addAttribute("searchText", mainimgVO.getSearchText());

    List list = this.mainimgService.mainimg_list(mainimgVO);

    if (list.size() > 0)
      mainimgVO.setTotalPage(((NaksinuriMainImgVO)list.get(0)).getTot_cnt());
    else {
      mainimgVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriMainImgVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/info/mainimg_list";
  }

  @RequestMapping({"/info/mainimg/trash.do"})
  public String mainimg_trash(@ModelAttribute("mainimgVO") NaksinuriMainImgVO mainimgVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    mainimgVO.setPageInfo(model);
    mainimgVO.setPageUnit(mainimgVO.getPageUnit());

    model.addAttribute("searchType", mainimgVO.getSearchType());
    model.addAttribute("searchText", mainimgVO.getSearchText());

    List list = this.mainimgService.mainimg_trash(mainimgVO);

    if (list.size() > 0)
      mainimgVO.setTotalPage(((NaksinuriMainImgVO)list.get(0)).getTot_cnt());
    else {
      mainimgVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriMainImgVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/info/mainimg_trash";
  }

  @RequestMapping({"/info/mainimg/board_findCorp.do"})
  public String mainimg_find(@ModelAttribute("mainimgVO") NaksinuriMainImgVO mainimgVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
	  LOGGER.debug(">>>>>>>>>>>> board_findCorp");
    NaksinuriMainImgVO info = this.mainimgService.board_findCorp(mainimgVO);
    
    LOGGER.debug(">>>>>>>>>>>> mimg PC용이미지");
    NaksinuriMainImgVO mimg = this.mainimgService.mimg(mainimgVO);
    
    LOGGER.debug(">>>>>>>>>>>> mobile_mimg 모바일용용이미지");
    NaksinuriMainImgVO mobile_mimg = this.mainimgService.mobile_mimg(mainimgVO);
    
    LOGGER.debug(">>>>>>>>>>>> attach_img 첨부파일리스트");
    List filelist = this.mainimgService.attach_img(mainimgVO);
    
    //jhkj NaksinuriMainImgVO attach_img = this.mainimgService.mobile_mimg(mainimgVO);
    
    
    model.addAttribute("info", info);
    model.addAttribute("mimg", mimg);
    model.addAttribute("mobile_mimg", mobile_mimg);
    model.addAttribute("filelist", filelist);
    
    
    
    return "naksinuri_original/naksinuri/admin/info/mainimg_view";
  }

  @RequestMapping({"/info/mainimg/insert_data.do"})
  public String usermainimg_insert_data(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("mainimgVO") NaksinuriMainImgVO mainimgVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    String mimg = "mimg";
    String mobile_mimg = "mobile_mimg";
    String _mainFileId = "";
    String _subFileId = "";
    String _attachFileId = "";

    List _result = null;
    List _resultsub = null;
    List _resultattach = null;

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();
    Map mobilefile = multiRequest.getFileMap();

    mfile.clear();
    mobilefile.clear();

    if (files.get(mimg) != null) {
      mfile.put(mimg, files.get(mimg));
      files.remove(mimg);
    }

    if (!mfile.isEmpty()) {
      _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
      _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
    }
    
    //////////////////////////////////////////////////////
    if (files.get(mobile_mimg) != null) {
    	mobilefile.put(mobile_mimg, files.get(mobile_mimg));
        files.remove(mobile_mimg);
    }

    if (!mobilefile.isEmpty()) {
    	_resultsub = this.fileUtil.parseFileInf_naksinuri_original(mobilefile, "NAK_", 0, "", "");
    	_subFileId = this.fileMngService.insertFileInfs_naksinuri_original(_resultsub);
    }
      
    //////////////////////////////////////////////////////
    
    // multi attach
    if (!files.isEmpty())
    {
    	_resultattach = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
      for (int i = 0; i < _resultattach.size(); i++) {
        if (((NaksinuriOriginalFileVO)_resultattach.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      _attachFileId = this.fileMngService.insertFileInfs_naksinuri_original(_resultattach);
    }

    mainimgVO.setAtch_file(_attachFileId);
    
    //////////////////////////////////////

    
    
    LOGGER.debug(">>>>> " + _result);
    LOGGER.debug(">>>>> " + _mainFileId);

    mainimgVO.setImg_cont(_mainFileId);
    
    EgovStringUtil mEgovStringUtil = new EgovStringUtil();	

    String img_subject = request.getParameter("img_subject");
    img_subject = mEgovStringUtil.getHtmlStrCnvr(img_subject);
    mainimgVO.setImg_subject(img_subject);

    this.mainimgService.insert_imgdata(mainimgVO);

    return "redirect:/admin/info/mainimg/list.do";
  }

  @RequestMapping({"/info/mainimg/update_data.do"})
  public String update_imgdata(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("mainimgVO") NaksinuriMainImgVO mainimgVO, BindingResult bindingResult, ModelMap model, HttpServletRequest request)
    throws Exception
  {
    String _mainFileId = mainimgVO.getImg_cont();
    String mimg = "mimg";

    String m_mainFileId = mainimgVO.getM_img_cont();
    String m_mimg = "mobile_mimg";
    
    String attachFileId = mainimgVO.getAtch_file();
    String attach_mimg = "aaa";

    Map files = multiRequest.getFileMap();
    Map mfile = multiRequest.getFileMap();

    Map m_files = multiRequest.getFileMap();
    Map m_mfile = multiRequest.getFileMap();
    Map m_attachfile = multiRequest.getFileMap();

    List _result = null;
    List m_result = null;
    List attach_result = null;

    mfile.clear();
    m_mfile.clear();
    m_attachfile.clear();

    if (files.get(mimg) != null) {
      mfile.put(mimg, files.get(mimg));
      files.remove(mimg);
    }

    if (m_files.get(m_mimg) != null) {
      m_mfile.put(m_mimg, m_files.get(m_mimg));
      m_files.remove(m_mimg);
      files.remove(m_mimg);
    }

    if (!mfile.isEmpty()) {
      if (("".equals(_mainFileId)) || (_mainFileId == null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, "", "");
        _mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(_result);
        mainimgVO.setImg_cont(_mainFileId);
      } else if ((!"".equals(_mainFileId)) || (_mainFileId != null)) {
        _result = this.fileUtil.parseFileInf_naksinuri_original(mfile, "NAK_", 0, _mainFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(_result);
      }
    }

    if (!m_mfile.isEmpty()) {
      if (("".equals(m_mainFileId)) || (m_mainFileId == null)) {
        m_result = this.fileUtil.parseFileInf_naksinuri_original(m_mfile, "NAK_", 0, "", "");
        m_mainFileId = this.fileMngService.insertFileInfs_naksinuri_original(m_result);
        mainimgVO.setM_img_cont(m_mainFileId);
      } else if ((!"".equals(m_mainFileId)) || (m_mainFileId != null)) {
        m_result = this.fileUtil.parseFileInf_naksinuri_original(m_mfile, "NAK_", 0, m_mainFileId, "");
        this.fileMngService.updateFileInfs_naksinuri_original(m_result);
      }
    }
    
 // multi attach
    if (!files.isEmpty())
    {
    	if (("".equals(attachFileId)) || (attachFileId == null)) {
    		attach_result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
    		attachFileId = this.fileMngService.insertFileInfs_naksinuri_original(attach_result);
    		mainimgVO.setAtch_file(attachFileId);
    	} else if ((!"".equals(attachFileId)) || (attachFileId != null)) {
    		attach_result = this.fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, attachFileId, "");
    		this.fileMngService.updateFileInfs_naksinuri_original(attach_result);
    	}
    	
      for (int i = 0; i < attach_result.size(); i++) {
        if (((NaksinuriOriginalFileVO)attach_result.get(i)).atchFileId.equals("ext_error")) {
          LOGGER.debug("파일에러");
          return "redirect:/error/ext/warn.do";
        }
      }

      
    }

    EgovStringUtil mEgovStringUtil = new EgovStringUtil();
   
    String img_subject = request.getParameter("img_subject");
    img_subject = mEgovStringUtil.getHtmlStrCnvr(img_subject);
    mainimgVO.setImg_subject(img_subject);

    this.mainimgService.update_data(mainimgVO);

    return "redirect:/admin/info/mainimg/list.do";
  }

  @RequestMapping({"/info/mainimg/delete_list.do"})
  public String delete_mainimg(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("mainimgVO") NaksinuriMainImgVO mainimgVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      mainimgVO.setImg_no(strboIds[i]);
      this.mainimgService.delete_boardlist(mainimgVO);
    }

    return "redirect:/admin/info/mainimg/trash.do";
  }

  @RequestMapping({"/info/mainimg/restore.do"})
  public String restore_mainimg(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("mainimgVO") NaksinuriMainImgVO mainimgVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      mainimgVO.setImg_no(strboIds[i]);
      this.mainimgService.restore_boardlist(mainimgVO);
    }

    return "redirect:/admin/info/mainimg/trash.do";
  }

  @RequestMapping({"/info/mainimg/gotrash_list.do"})
  public String delete_mainimg_trash(@RequestParam("bo_sns") String bo_sns, @ModelAttribute("mainimgVO") NaksinuriMainImgVO mainimgVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    throws Exception
  {
    String[] strboIds = bo_sns.split(";");
    for (int i = 0; i < strboIds.length; i++) {
      mainimgVO.setImg_no(strboIds[i]);
      this.mainimgService.delete_boardtrash(mainimgVO);
    }

    return "redirect:/admin/info/mainimg/list.do";
  }

  @RequestMapping({"/sosig/gosi/view.do"})
  public String gosi_view(HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug(request.getServletPath());
    return "naksinuri_original/naksinuri/admin/sosig/gosi_view";
  }

  @RequestMapping({"/info/report/list.do"})
  public String report_list(@ModelAttribute("reportVO") ReportVO reportVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    reportVO.setPageInfo(model);
    reportVO.setPageUnit(reportVO.getPageUnit());

    List list = this.service_report.report_list(reportVO);

    if (list.size() > 0)
      reportVO.setTotalPage(((ReportVO)list.get(0)).getTot_cnt());
    else {
      reportVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((ReportVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/info/report_list";
  }

  @RequestMapping({"/info/report/trash.do"})
  public String report_trash(@ModelAttribute("reportVO") ReportVO reportVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    reportVO.setPageInfo(model);
    reportVO.setPageUnit(reportVO.getPageUnit());

    List list = this.service_report.report_trash(reportVO);

    if (list.size() > 0)
      reportVO.setTotalPage(((ReportVO)list.get(0)).getTot_cnt());
    else {
      reportVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((ReportVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/info/report_trash";
  }
  
  
  
  
  @RequestMapping({"/gongmo/gongmo/list.do"})
  public String gongmolist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("congress");

    List list = this.service.gongmo_list(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    model.addAttribute("bo_start_dt", boardVO.getBo_start_dt());
    model.addAttribute("bo_end_dt", boardVO.getBo_end_dt());

    model.addAttribute("is_entry_y", boardVO.getIs_entry_y());

    if (list.size() > 0)
      boardVO.setTotalPage(((BoardVO)list.get(0)).getTot_cnt());
    else {
      boardVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((BoardVO)list.get(0)).getTot_cnt()));
    } else {
      model.addAttribute("select_total", 0);
    }
    if (noticlist.size() > 0) {
      model.addAttribute("noticlist", noticlist);
    }

    return "naksinuri_original/naksinuri/admin/gongmo/gongmo_list";
  }
  
  
  @RequestMapping({"/gongmo/gongmo/trash.do"})
  public String gongmolist_trash(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("gongmo");

    List list = this.service.gongmo_trash(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

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

    return "naksinuri_original/naksinuri/admin/gongmo/gongmo_trash";
  }

  @RequestMapping({"/gongmo/gongmo/endlist.do"})
  public String gongmoendlist(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("gongmo");

    List list = this.service.gongmo_endlist(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());
    model.addAttribute("bo_start_dt", boardVO.getBo_start_dt());
    model.addAttribute("bo_end_dt", boardVO.getBo_end_dt());
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

    return "naksinuri_original/naksinuri/admin/gongmo/gongmo_endlist";
  }

  @RequestMapping({"/gongmo/gongmo/endtrash.do"})
  public String gongmoendlist_trash(ModelMap model, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    boardVO.setPageInfo(model);
    boardVO.setPageUnit(boardVO.getPageUnit());
    boardVO.setBo_type("gongmo");

    List list = this.service.gongmo_endtrash(boardVO);
    List noticlist = this.service.noticemark_list(boardVO);
    model.addAttribute("searchType", boardVO.getSearchType());
    model.addAttribute("searchText", boardVO.getSearchText());

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

    return "naksinuri_original/naksinuri/admin/gongmo/gongmo_endtrash";
  }
  
  /*
   * SMS 관리 관련
   * 
   */
  
  /**
   * 예약.
   * @param 
   * @return	"naksinuri_original/naksinuri/admin/sms/smsmngr/view"
   * @throws Exception
   */

  @RequestMapping({"/sms/smsmngr/view.do"})
  public String smsmngrview(HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug(request.getServletPath());
    return "naksinuri_original/naksinuri/admin/sms/smsmngr/view";
  }
  
  /**
   * 연락처.
   * @param 
   * @return	"naksinuri_original/naksinuri/admin/sms/smsmngr/contact"
   * @throws Exception
   */

  @RequestMapping({"/sms/smsmngr/contact.do"})
  public String smsmngrcontact(ModelMap model,HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug(request.getServletPath());
    
    NaksinuriSmsVO smsData = new NaksinuriSmsVO();
    
    
    model.addAttribute("sendnumber", smsData.getSmsSendNumber());
	
    return "naksinuri_original/naksinuri/admin/sms/smsmngr/contact";
  }
  
  @RequestMapping(value={"/sms/smsmngr/contactlist.do"})
  @ResponseBody
  public Map<String, Object> contactlist(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, ModelMap model, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  
	  int nPageUnit = 100;
	  
	  String error_num = "0";
	  String msg = "연락처조회 성공";
	  
	//pageIndex=1&searchText=aa&searchgroupname=7878787
	  
	  String searchgroupname = request.getParameter("searchgroupname");
	  String searchText = request.getParameter("searchText");
	  String isall = request.getParameter("isall");
	  
	  LOGGER.debug("isall = " + isall);
	  
	  if ( isall == null ) 
		  isall	 = "0";
 
		smsMngrVO.setIsAll(isall);	
	  
	  LOGGER.debug("contactlist.do  " + searchgroupname + " , " + searchText);
	  
	  if ( searchgroupname != null )
		  smsMngrVO.setGroupname(searchgroupname.trim());
	  
	  if ( searchText != null )
		  smsMngrVO.setSearchText(searchText.trim());
	  
	  smsMngrVO.setPageUnit(nPageUnit);
	
  
    List<SmsMngrVO> list = this.smsService.contact_list(smsMngrVO);
    
    int nTotalPage = 0;
    int pageIndex = 0;
    
    if (list.size() > 0) {
    	//smsMngrVO.setTotalPage(((SmsMngrVO)list.get(0)).getTot_cnt());
    	SmsMngrVO vo = (SmsMngrVO)list.get(0);
    	
    	smsMngrVO.setTotalPage(vo.getTot_cnt());
    	nTotalPage = vo.getTot_cnt();
    	pageIndex = smsMngrVO.getPageIndex();
    	LOGGER.debug("====  "  + vo.getTot_cnt());
    } else {
    	smsMngrVO.setTotalPage(0);
    	nTotalPage = 0;
    	pageIndex = 0;
    }
    
    data.put("error", error_num);
	data.put("msg", msg);
	
	data.put("lists", list);
	
	data.put("pageIndex", pageIndex);
	data.put("pageUnit", smsMngrVO.getPageUnit());
	data.put("totalPage", nTotalPage+"");
	data.put("searchText", smsMngrVO.getSearchText());
	data.put("countPerPage",smsMngrVO.getRecordCountPerPage());

    return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/contactinsert.do"})
  @ResponseBody
  public Map<String, Object> contactinsert(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String error_num = "0";
	  String msg = "연락처추가 성공";
	  
	  String name = request.getParameter("name");
	  String hp = request.getParameter("hp");
	  String groupname = request.getParameter("groupname");
    
	  name = mEgovStringUtil.getHtmlStrCnvr(name);
	  hp = mEgovStringUtil.getHtmlStrCnvr(hp);
	  groupname = mEgovStringUtil.getHtmlStrCnvr(groupname);
	  
	  smsMngrVO.setHp(hp);
	  
	  // 추가전 체크
	  SmsMngrVO dbdata = this.smsService.contact_select(smsMngrVO);
	  
	  if (dbdata == null ) {
		  // 없으니까 추가	 
		  smsMngrVO.setHp(hp);
		  smsMngrVO.setName(name);	  
		  smsMngrVO.setGroupname(groupname);
	  	 
		  this.smsService.contact_insert(smsMngrVO);
	  } else {
		  error_num = "1";
		  msg = "동일연락처 존재";
	  }
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/contactupdate.do"})
  @ResponseBody
  public Map<String, Object> contactupdate(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String error_num = "0";
	  String msg = "연락처변경 성공";
	  
	  String idx = request.getParameter("idx");
	  String name = request.getParameter("name");
	  String hp = request.getParameter("hp");
	  String group = request.getParameter("groupname");
    
	  idx = mEgovStringUtil.getHtmlStrCnvr(idx);
	  name = mEgovStringUtil.getHtmlStrCnvr(name);
	  hp = mEgovStringUtil.getHtmlStrCnvr(hp);
	  
	  /* *************************************** */
	    NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
	    HttpSession session = request.getSession();    
	    
	    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	    insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
	    insertlogsVO.setUser_id(loginVO.getMBR_ID());
	    insertlogsVO.setActions("연락처 변경 " + name);
	    this.service_logs.insertLogs(insertlogsVO);
	    /* *************************************** */
	  
	  
	  smsMngrVO.setName(name);
	  smsMngrVO.setHp(hp);
	  smsMngrVO.setGroupname(group);
	  smsMngrVO.setIdx(idx);
	  
	  //smsMngrVO.setReservation(reservation);
	  //smsMngrVO.setDatetime(datetime);
	  
	  
	  this.smsService.contact_update(smsMngrVO);
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/contactdelete.do"})
  @ResponseBody
  public Map<String, Object> contactdelete(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  
	  String error_num = "0";
	  String msg = "연락처삭제 성공";
	  
	  String idx = request.getParameter("idx");
	  String chks = request.getParameter("chks");
	  
	  /* *************************************** */
	    NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
	    HttpSession session = request.getSession();    
	    
	    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	    insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
	    insertlogsVO.setUser_id(loginVO.getMBR_ID());
	    insertlogsVO.setActions("연락처삭제 " + idx);
	    this.service_logs.insertLogs(insertlogsVO);
	    /* *************************************** */
	  
	  //idx = replaceTag(idx);
	  //chks = replaceTag(chks);
	  
	  if (chks != null) {
		  String[] words = chks.split(",");	    
	      
	      for (String wo : words ){
	          LOGGER.debug(wo);
	          if ( wo.equals(""))
	        	  continue;
	          
	          smsMngrVO.setIdx(wo);	          
	          
	          this.smsService.contact_delete(smsMngrVO);
	      }		  
	  } else {
		  smsMngrVO.setIdx(idx);		  
		  this.smsService.contact_delete(smsMngrVO);		  
	  }
      
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/contactgroupupdate.do"})
  @ResponseBody
  public Map<String, Object> contactgroupupdate(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  
	  String error_num = "0";
	  String msg = "연락처그룹정보 변경 성공";
	  
	  // chks=53,54&group=가족
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String group = request.getParameter("group");
	  String chks = request.getParameter("chks");
    
	  group = mEgovStringUtil.getHtmlStrCnvr(group);
	  chks = mEgovStringUtil.getHtmlStrCnvr(chks);
	  
	  String[] words = chks.split(",");	    
      
      for (String wo : words ){
          LOGGER.debug(wo);
          smsMngrVO.setIdx(wo);
          smsMngrVO.setGroupname(group);
          
          this.smsService.contact_update(smsMngrVO);
      }
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/ajax_upload_excel.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Map<String, Object> ajaxContactExcelUpload(MultipartHttpServletRequest multiRequest, SessionStatus status, @ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, 
  BindingResult bindingResult, ModelMap model, MultipartHttpServletRequest request) throws Exception
  {
	  boolean isExist = false;
	  List list = new ArrayList();
	  Map data = new HashMap();
	  
	  String existhp = "";
	  
	  String error_num = "0";
	  String msg = "연락처 일괄등록 성공";
	  
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
		      excelReadOption.setOutputColumns("A","B","C");
		      excelReadOption.setStartRow(3);
		      List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption,false);
		      
		      for(Map<String, String> article: excelContent){
		          LOGGER.debug(article.get("A"));
		          LOGGER.debug(article.get("B"));
		          LOGGER.debug(article.get("C"));
		          
		          String name = article.get("A");
		          String hp = article.get("B");
		          String groupname_org = article.get("C");		          
		          
		          String groupname = groupname_org;
		          
		          if ( groupname_org == null ) {
		        	  groupname = "미지정";
		          }
		          
		          if ( groupname_org.equals("") ) {
		        	  groupname = "미지정";
		          }
		          
		          smsMngrVO.setName(name);
		          smsMngrVO.setHp(hp);
		          smsMngrVO.setGroupname(groupname);
		          
		          SmsMngrVO findgroupVO = new SmsMngrVO();
		          findgroupVO.setHp(hp);
		          
		          SmsMngrVO contactinfo = this.smsService.contact_select(findgroupVO);
		          
		          if (contactinfo == null ) {
		        	  this.smsService.contact_insert(smsMngrVO);
		          } else {
		        	  LOGGER.debug("EXIST " + name + " , " + hp);
		        	  existhp += hp + ",";
		          }
		        	  
		          
		          // 그룹 찾아서 없으면 추가
		          if ( !groupname.equals("미지정") ) {  
			          SmsMngrVO findgroup = new SmsMngrVO();
			          
			          findgroup.setGroupname(groupname);		          
			          
			          SmsMngrVO groupinfo = this.smsService.group_select(findgroup);
			          
			          if (groupinfo == null ) {
			        	  this.smsService.group_insert(findgroup);
			          }	        	  
		          }
		      } 
		      
		      //data.put("lists", excelContent);
	      }
	      try {
	    	  destFile.delete();
	      } catch(Exception e) {
	    	  LOGGER.debug("[fail delete] "+e.toString());
	      }  
      }
	  NaksinuriUtils.scanParameters(request);
	  
	  if ( !existhp.equals("")) {
		  error_num = "2";
    	  msg = "중복연락처가 있습니다. " + existhp;
    	  
	  }
	  
	  data.put("error", error_num);
	  data.put("msg", msg);	  
	  
	  
	  
	  list.add(data);
	  
	  /* *************************************** */
	    NaksinuriLogsVO insertlogsVO = new NaksinuriLogsVO();
	    HttpSession session = request.getSession();    
	    
	    LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	    insertlogsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
	    insertlogsVO.setUser_id(loginVO.getMBR_ID());
	    insertlogsVO.setActions("SMS 연락처 업로드 엑셀파일 실행");
	    this.service_logs.insertLogs(insertlogsVO);
	    /* *************************************** */
	  return data;
  }
  
  
  
  /**
   * 그룹.
   * @param 
   * @return	"naksinuri_original/naksinuri/admin/sms/smsmngr/group"
   * @throws Exception
   */

  @RequestMapping({"/sms/smsmngr/group.do"})
  public String smsmngrgroup(ModelMap model, HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug(request.getServletPath());
    
    NaksinuriSmsVO smsData = new NaksinuriSmsVO();
    
    
    model.addAttribute("sendnumber", smsData.getSmsSendNumber());
    
    return "naksinuri_original/naksinuri/admin/sms/smsmngr/group";
  }
  
  @RequestMapping(value={"/sms/smsmngr/grouplist.do"})
  @ResponseBody
  public Map<String, Object> grouplist(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, ModelMap model, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  
	  int nPageUnit = 10;
	  
	  String error_num = "0";
	  String msg = "연락처조회 성공";
	  
	  String all = request.getParameter("all");
	  
	  LOGGER.debug("all = " + all);
	  
	  if ( all == null ) 
		  all = "0";
 
		smsMngrVO.setIsAll(all);		 
		smsMngrVO.setPageUnit(nPageUnit);
  
    List<SmsMngrVO> list = this.smsService.group_list(smsMngrVO);
    
    int nTotalPage = 0;
    int pageIndex = 0;
    
    if (list.size() > 0) {
    	//smsMngrVO.setTotalPage(((SmsMngrVO)list.get(0)).getTot_cnt());
    	SmsMngrVO vo = (SmsMngrVO)list.get(0);
    	
    	smsMngrVO.setTotalPage(vo.getTot_cnt());
    	nTotalPage = vo.getTot_cnt();
    	pageIndex = smsMngrVO.getPageIndex();
    	LOGGER.debug("====  "  + vo.getTot_cnt());
    } else {
    	smsMngrVO.setTotalPage(0);
    	nTotalPage = 0;
    	pageIndex = 0;
    }
    
    data.put("error", error_num);
	data.put("msg", msg);
	
	data.put("lists", list);
	
	data.put("pageIndex", pageIndex);
	data.put("pageUnit", smsMngrVO.getPageUnit());
	data.put("totalPage", nTotalPage+"");
	data.put("searchText", smsMngrVO.getSearchText());
	data.put("countPerPage",smsMngrVO.getRecordCountPerPage());

    return data;    
  }
  
  @RequestMapping(value={"/sms/smsmngr/groupinsert.do"})
  @ResponseBody
  public Map<String, Object> groupinsert(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String error_num = "0";
	  String msg = "그룹추가 성공";
	  
	  String group = request.getParameter("group");
    
	  group = mEgovStringUtil.getHtmlStrCnvr(group);
	  smsMngrVO.setGroupname(group);
	  
	  // 있나 체크
	  SmsMngrVO groupinfo = this.smsService.group_select(smsMngrVO);
	  
	  if (groupinfo == null ) {
		  this.smsService.group_insert(smsMngrVO);
	  } else {
		  error_num = "1";
		  msg = "동일그룹 존재";
	  }
	  
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/groupupdate.do"})
  @ResponseBody
  public Map<String, Object> groupupdate(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String error_num = "0";
	  String msg = "그룹변경 성공";
	  
	  String idx = request.getParameter("idx");
	  String group = request.getParameter("group");
   
	  group = mEgovStringUtil.getHtmlStrCnvr(group);
	  idx = mEgovStringUtil.getHtmlStrCnvr(idx);
	  
	  smsMngrVO.setIdx(idx);
	  ///////////////////////////////
	  // 변경전 이름가져온다.
	  SmsMngrVO dbdata = this.smsService.group_select(smsMngrVO);
	  
	  // 변경
	  smsMngrVO.setGroupname(group);
	  this.smsService.group_update(smsMngrVO);
	  
	 /// 변경 
	  // contact에서 해당 그룹명 미지정으로 변경
	  SmsMngrVO newdata = new SmsMngrVO();
	  newdata.setGroupname(group);
	  newdata.setSearchText(dbdata.getGroupname());
	  this.smsService.contact_update(newdata);
	  
	  //////////////////////////////////
    
	  
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/groupdelete.do"})
  @ResponseBody
  public Map<String, Object> groupdelete(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String error_num = "0";
	  String msg = "그룹삭제 성공";
	  
	  String idx = request.getParameter("idx");
	  
	  idx = mEgovStringUtil.getHtmlStrCnvr(idx);
    
	  smsMngrVO.setIdx(idx);
	  
	  // 삭제전 이름가져온다.
	  SmsMngrVO dbdata = this.smsService.group_select(smsMngrVO);
	  
	  // 삭제
	  this.smsService.group_delete(smsMngrVO);
	  
	  // contact에서 해당 그룹명 미지정으로 변경
	  SmsMngrVO newdata = new SmsMngrVO();
	  newdata.setGroupname("미지정");
	  newdata.setSearchText(dbdata.getGroupname());
	  this.smsService.contact_update(newdata);
	  
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  
  
  
  @RequestMapping(value={"/sms/smsmngr/mentlist.do"})
  @ResponseBody
  public List<SmsMngrVO> mentlist(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, ModelMap model, HttpServletRequest request) throws Exception
  {
    //List schedule = new ArrayList();
    List<SmsMngrVO> date_list = this.smsService.ment_list(smsMngrVO);

    return date_list;
  }
  
  @RequestMapping(value={"/sms/smsmngr/mentinsert.do"})
  @ResponseBody
  public Map<String, Object> mentinsert(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String error_num = "0";
	  String msg = "멘트추가 성공";
	  
	  String menttext = request.getParameter("menttext");
	  String title = request.getParameter("title");
    
	  menttext = mEgovStringUtil.getHtmlStrCnvr(menttext);
    
	  smsMngrVO.setMenttext(menttext);
	  smsMngrVO.setTitle(title);
	  
	  this.smsService.ment_insert(smsMngrVO);
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/mentupdate.do"})
  @ResponseBody
  public Map<String, Object> mentupdate(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String error_num = "0";
	  String msg = "멘트변경 성공";
	  
	  String idx = request.getParameter("idx");
	  String title = request.getParameter("title");
	  String menttext = request.getParameter("menttext");
    
	  menttext = mEgovStringUtil.getHtmlStrCnvr(menttext);
	  idx = mEgovStringUtil.getHtmlStrCnvr(idx);
    
	  smsMngrVO.setTitle(title);
	  smsMngrVO.setMenttext(menttext);
	  smsMngrVO.setIdx(idx);
	  
	  this.smsService.ment_update(smsMngrVO);
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/mentdelete.do"})
  @ResponseBody
  public Map<String, Object> mentdelete(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  EgovStringUtil mEgovStringUtil = new EgovStringUtil();
	  
	  String error_num = "0";
	  String msg = "멘트삭제 성공";
	  
	  String idx = request.getParameter("idx");
	  
	  idx = mEgovStringUtil.getHtmlStrCnvr(idx);
    
	  smsMngrVO.setIdx(idx);
	  
	  this.smsService.ment_delete(smsMngrVO);
	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping(value={"/sms/smsmngr/sms_write_send.do"})
  @ResponseBody
  public Map<String, Object> sms_write_send(@ModelAttribute("smsMngrVO") SmsMngrVO smsMngrVO, HttpServletRequest request) throws Exception
  {
	  Map data = new HashMap();
	  
	  String error_num = "0";
	  String msg = "SMS전송 성공";
	  
	  String send_list = request.getParameter("send_list");
	  String wr_message = request.getParameter("wr_message");
	  String wr_reply = request.getParameter("wr_reply");
	  
	  LOGGER.debug("회신번호 = " + wr_reply );
	  LOGGER.debug("보낼내용 = " + wr_message );
	  
	  // p,53,/p,53,60,/h,ㅁㅁㅁ:010-4532-3232/
	  
	  String[] list1 = send_list.split("/");
	  
	  
		for (int i = 0; i < list1.length; i++) {
			String[] list2 = list1[i].split(",");
			String target = list2[0].trim();
			
			if ( target.equals("p")) {
				for (int j = 1; j < list2.length; j++) {	
					String idx = list2[j];
					
					//LOGGER.debug("그룹전송 target idx = " + idx);		
										
					smsMngrVO.setIdx(idx);
				    SmsMngrVO contactinfo = this.smsService.contact_select(smsMngrVO);

				    
				    String name = contactinfo.getName();
				    String hp = contactinfo.getHp();
				    
				    LOGGER.debug("그룹전송 target name & hp = " + name + " , " + hp);
				    
				    NaksinuriSmsVO smsData = new NaksinuriSmsVO();
					//smsData.setMsg_type("MMS");
					smsData.setMsg(wr_message);
					smsData.setSubmsg("낚시누리 알림");
					smsData.setImg_cnt(0);
					smsData.setImg_path("");
					smsData.setM_point(0);
					smsData.setC_point(0);
					smsData.setApikey("");//사용안할듯.
					smsData.setRstkey(smsData.getUniqRstKey());
					smsData.setR_phone(hp);
					smsData.setS_phone(wr_reply);
					smsData.setIp(NaksiBoardController.getClientIpAddr(request));
					this.smsService.sendToSmsMngr(smsData);					
		    	}	    		
			} else {
				for (int j = 1; j < list2.length; j++) {	   
					//LOGGER.debug("개별전송 target idx = " + list2[j]);
					String[] list3 = list2[j].split(":");
					
					
					String name = list3[0];
				    String hp = list3[1];
				    
				    LOGGER.debug("개별전송 target name & hp = " + name + " , " + hp);
					
					NaksinuriSmsVO smsData = new NaksinuriSmsVO();
					//smsData.setMsg_type("MMS");
					smsData.setMsg(wr_message);
					smsData.setSubmsg("낚시누리 알림");
					smsData.setImg_cnt(0);
					smsData.setImg_path("");
					smsData.setM_point(0);
					smsData.setC_point(0);
					smsData.setApikey("");//사용안할듯.
					smsData.setRstkey(smsData.getUniqRstKey());
					smsData.setR_phone(hp);
					smsData.setS_phone(wr_reply);
					smsData.setIp(NaksiBoardController.getClientIpAddr(request));
					this.smsService.sendToSmsMngr(smsData);		    		
		    	}	    		
			}	     
		}
	    	  
	  data.put("error", error_num);
	  data.put("msg", msg);
    
	  return data;
  }
  
  @RequestMapping({"/logs/list.do"})
  public String logs_list(@ModelAttribute("logsVO") NaksinuriLogsVO logsVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	  String date_from = request.getParameter("date_from");
	  String date_to = request.getParameter("date_to");
	  
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
      Calendar today = new GregorianCalendar();
      String formatted_date = formatter.format(today.getTime());
      
      if ( date_from == null)
    	  logsVO.setDate_from(formatted_date);
      if ( date_to == null)
    	  logsVO.setDate_to(formatted_date);
      
	  
	  logsVO.setPageInfo(model);
	  logsVO.setPageUnit(logsVO.getPageUnit());

    model.addAttribute("searchType", logsVO.getSearchType());
    model.addAttribute("searchText", logsVO.getSearchText());
    
    model.addAttribute("date_from", logsVO.getDate_from());
    model.addAttribute("date_to", logsVO.getDate_to());
    
    LOGGER.debug("==============> date " + logsVO.getDate_from() + " ," + logsVO.getDate_to() + " ," + logsVO.getSearchText());
    
    logsVO.setConn_ip(NaksiBoardController.getClientIpAddr(request));
   

    List list = this.service_logs.getListLogs(logsVO);

    if (list.size() > 0)
    	logsVO.setTotalPage(((NaksinuriLogsVO)list.get(0)).getTot_cnt());
    else {
    	logsVO.setTotalPage(0);
    }
    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriLogsVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/logs/logs_list";
  }
  
  //낚시누리 - 정책홍보 - 낚시명예감시원
  @RequestMapping({"/admin/promotion/auditor/list.do"})
  public String admin_promotion_auditor_list(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, 
		  ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
    GoneVO.setPageInfo(model);
    GoneVO.setPageUnit(GoneVO.getPageUnit());

    List list = this.service_gone.getListGone_admin(GoneVO);

    model.addAttribute("pageinfo", GoneVO);

    if (list.size() > 0)
      GoneVO.setTotalPage(((NaksinuriGoneVO)list.get(0)).getTot_cnt());
    else {
      GoneVO.setTotalPage(0);
    }
    model.addAttribute("sido", GoneVO.getSido());
    model.addAttribute("gugun", GoneVO.getGugun());

    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)list.get(0)).getTot_cnt()));
    }

    return "naksinuri_original/naksinuri/admin/promotion/auditor_list";
  }
  
  //낚시누리 - 정책홍보 - 낚시명예감시원 - 삭제
  @RequestMapping({"/admin/promotion/auditor/trash.do"})
  public String admin_promotion_auditor_trash(@ModelAttribute("NaksinuriGoneVO") NaksinuriGoneVO GoneVO, ModelMap model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    GoneVO.setPageInfo(model);
    GoneVO.setPageUnit(GoneVO.getPageUnit());

    List list = this.service_gone.getTrashGone_admin(GoneVO);

    if (list.size() > 0)
      GoneVO.setTotalPage(((NaksinuriGoneVO)list.get(0)).getTot_cnt());
    else {
      GoneVO.setTotalPage(0);
    }
    model.addAttribute("sido", GoneVO.getSido());
    model.addAttribute("gugun", GoneVO.getGugun());

    if (list.size() > 0) {
      model.addAttribute("select_list", list);
      model.addAttribute("select_total", Integer.valueOf(((NaksinuriGoneVO)list.get(0)).getTot_cnt()));
      model.addAttribute("pageinfo", GoneVO);
    }
    return "naksinuri_original/naksinuri/admin/promotion/plocation_trash";
  }

  @RequestMapping({"/admin/promotion/auditor/view.do"})
  public String admin_promotion_auditor_view(HttpServletRequest request, HttpServletResponse response)
  {
    LOGGER.debug(request.getServletPath());
    return "naksinuri_original/naksinuri/admin/promotion/plocation_view";
  }
 
  
}