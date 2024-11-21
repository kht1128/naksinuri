package egovframework.naksinuri_original.let.naksinuri.web;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticVO;
import egovframework.utils.EgovStringUtil;


@Controller

public class NaksinuriEventController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NaksinuriEventController.class);
	
	@Resource(name="NaksinuriEventService")
	private NaksinuriEventService service;
	
	@Resource(name="NaksinuriStaticService")
	private NaksinuriStatisticService service_statistic;
	
	
	public static String getClientIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	 
	    return ip;
	}

	public static String getClientBrowser(HttpServletRequest request) {
		String browser = "";
		
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0) {
			browser = "IE";
		} else if (userAgent.indexOf("Edge") > 0) {
			browser = "Edge";
		} else if (userAgent.indexOf("Presto") > 0) {
			browser = "Opera";
		} else if (userAgent.indexOf("Firefox") > 0) {
			browser = "Firefox";
		} else if (userAgent.indexOf("Nokia") > 0) {
			browser = "NokiaBrowser";
		} else if (userAgent.indexOf("Safari") > 0) {
		 if (userAgent.indexOf("Chrome") > 0) {
			  browser = "Chrome";
			 } else {
			  browser = "Safari";
			 }
		}else{
			browser ="etc";
		}

	return browser;
	}
		
	public static String getClientOS(HttpServletRequest request) {
		String os = "";
		
		String user_os = request.getHeader("User-Agent");
		if (user_os.indexOf("iPhone") > 0 || user_os.indexOf("iPad") > 0) {
			os = "Mobile_IOS";
		} else if (user_os.indexOf("Android") > 0) {
			os = "Mobile_Android";
		} else if (user_os.indexOf("Windows Phone") > 0) {
			os = "Mobile_Window";
		} else if (user_os.indexOf("RIM") > 0) {
			os = "Mobile_RIM";
		} else if (user_os.indexOf("Mac") > 0) {
			os = "Mac";
		} else if (user_os.indexOf("Ubuntu") > 0) {
			os = "Ubuntu";
		} else if (user_os.indexOf("Nokia") > 0) {
			os = "MeeGo";
		} 
	return os;
	}
	
	@RequestMapping("/sosig/event/list.do")
	public String event_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,HttpServletRequest request,NaksinuriStatisticVO staticVO,
			HttpServletResponse response, ModelMap model) throws Exception{
		LOGGER.debug("NaksinuriEventController - event_list : 낚시소식 - 이벤트 ");

		eventVO.setPageUnit(12);
		eventVO.setPageInfo(model);
		eventVO.setPageUnit(eventVO.getPageUnit()); 

		staticVO.setBo_name("이벤트");
		staticVO.setPath(request.getRequestURL().toString());
		if(getClientOS(request) != ""){
			staticVO.setStatistic_os(getClientOS(request));
		}else{
			staticVO.setStatistic_os(System.getProperty("os.name"));
		}
		
		staticVO.setBrowser(getClientBrowser(request));
		staticVO.setClient_ip(getClientIpAddr(request));
		staticVO.setBo_type("event");
		//--------------------------------------------------
		// URL 세부 카테고리 분류 추가 - 2018.06.29
		//--------------------------------------------------
		staticVO.setCategory_group_type("fishing_sosig");
		staticVO.setCategory_group_name("낚시소식");
		staticVO.setPath_type("web");
		staticVO.setCategory_name("");
		staticVO.setCategory_type("");
		//--------------------------------------------------
		service_statistic.get_statisticInfo(staticVO,request);
		
		model.addAttribute("gallery_list",eventVO.getGallery_list());
		List<NaksinuriEventVO> list = service.getEventList(eventVO);
		 
	
		if(list.size() > 0)
			eventVO.setTotalPage(list.get(0).getTot_cnt());
	
		else
			eventVO.setTotalPage(0);
		
		if(list.size()>0){
		
		model.addAttribute("event_list", list);
		model.addAttribute("event_total", list.get(0).getTot_cnt());

	}
	
	return "naksinuri_original/naksinuri/sosig/event_list";			
		
}
	

			
	
	@RequestMapping("/sosig/event/m/list.do")
	public String event_list_mobile(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,HttpServletRequest request,NaksinuriStatisticVO staticVO,
			HttpServletResponse response, ModelMap model) throws Exception{
		LOGGER.debug("NaksinuriEventController - event_list_mobile : 낚시소식(모바일) - 이벤트 ");
		
		eventVO.setPageUnit(12);
		eventVO.setPageInfo(model);
		eventVO.setPageUnit(eventVO.getPageUnit()); 
		
		staticVO.setBo_name("이벤트");
		if(getClientOS(request) != ""){
			staticVO.setStatistic_os(getClientOS(request));
		}else{
			staticVO.setStatistic_os(System.getProperty("os.name"));
		}
		
		staticVO.setBrowser(getClientBrowser(request));
		staticVO.setClient_ip(getClientIpAddr(request));
		staticVO.setBo_type("event");
		staticVO.setPath(request.getRequestURL().toString());
		//--------------------------------------------------
		// URL 세부 카테고리 분류 추가 - 2018.06.29
		//--------------------------------------------------
		staticVO.setCategory_group_type("fishing_sosig");
		staticVO.setCategory_group_name("낚시소식");
		staticVO.setPath_type("mobile");
		staticVO.setCategory_name("");
		staticVO.setCategory_type("");
		//--------------------------------------------------
		service_statistic.get_statisticInfo(staticVO,request);
		
		model.addAttribute("gallery_list",eventVO.getGallery_list());
		List<NaksinuriEventVO> list = service.getEventList(eventVO);
		 
	
		if(list.size() > 0)
			eventVO.setTotalPage(list.get(0).getTot_cnt());
	
		else
			eventVO.setTotalPage(0);
		
		if(list.size()>0){
		
		model.addAttribute("event_list", list);
		model.addAttribute("event_total", list.get(0).getTot_cnt());
	
	

		
	}
	
	return "naksinuri_original/naksinuri/sosig/m/event_list";			
		
}			

	

	@RequestMapping("/sosig/event/fail.do")
	public String event_fail(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, 
			HttpServletRequest request,HttpServletResponse response, ModelMap model) 
			throws Exception{
		
		return "naksinuri_original/naksinuri/sosig/event_fail";
	}
	
//	
//	@RequestMapping("/sosig/event/event_find.do")
//	public String find_event(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,
//			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
//			
//		NaksinuriEventVO info = service.event_findCorp(eventVO);
//		
//		
//		return "naksinuri_original/naksinuri/sosig/event_view";
//	}
	
//	종료이벤트 
	
	@RequestMapping("sosig/endevent/list.do")
	public String endevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception{
		
		eventVO.setPageUnit(20);
		eventVO.setPageInfo(model);
		eventVO.setPageUnit(eventVO.getPageUnit()); 
		
		model.addAttribute("gallery_list",eventVO.getGallery_list());
		List<NaksinuriEventVO> list = service.getendEventList(eventVO);
		 
	
		if(list.size() > 0){
			eventVO.setTotalPage(list.get(0).getTot_cnt());
	}
	else
			eventVO.setTotalPage(0);
		
	if(list.size()>0){
		
		model.addAttribute("endevent_list", list);
		model.addAttribute("event_total", list.get(0).getTot_cnt());
		int cnt=list.get(0).getTot_cnt();
		int PageUnit = 20;
		int pagesize=Math.round(cnt/PageUnit)+1;
		model.addAttribute("pagesize",pagesize);
		
	}
	
	return "naksinuri_original/naksinuri/sosig/endevent_list";
				
		
}
	
	@RequestMapping("sosig/endevent/m/list.do")
	public String endevent_list_mobile(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception{
		
		eventVO.setPageUnit(20);
		eventVO.setPageInfo(model);
		eventVO.setPageUnit(eventVO.getPageUnit()); 
		
		model.addAttribute("gallery_list",eventVO.getGallery_list());
		List<NaksinuriEventVO> list = service.getendEventList(eventVO);
		 
	
		if(list.size() > 0){
			eventVO.setTotalPage(list.get(0).getTot_cnt());
	}
	else
			eventVO.setTotalPage(0);
		
	if(list.size()>0){
		
		model.addAttribute("event_list", list);
		model.addAttribute("event_total", list.get(0).getTot_cnt());
		int cnt=list.get(0).getTot_cnt();
		int PageUnit = 20;
		int pagesize=Math.round(cnt/PageUnit)+1;
		model.addAttribute("pagesize",pagesize);
		
	}
	
	return "naksinuri_original/naksinuri/sosig/m/endevent_list";
				
		
}



//	당첨자발표
	@RequestMapping("/sosig/ancevent/list.do")
	public String ancevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception{
		
		eventVO.setPageUnit(20);
		eventVO.setPageInfo(model);
		eventVO.setPageUnit(eventVO.getPageUnit()); 
		
		List<NaksinuriEventVO> list = service.getancEventList(eventVO);
		 
	
		if(list.size() > 0){
			eventVO.setTotalPage(list.get(0).getTot_cnt());
	}
	else
			eventVO.setTotalPage(0);
		
	if(list.size()>0){
		
		model.addAttribute("ancevent_list", list);
		model.addAttribute("event_total", list.get(0).getTot_cnt());
		int cnt=list.get(0).getTot_cnt();
		int PageUnit = 20;
		int pagesize=Math.round(cnt/PageUnit)+1;
		model.addAttribute("pagesize",pagesize);
		
	}
	
	return "naksinuri_original/naksinuri/sosig/ancevent_list";
				
		
}
	
	
//	당첨자발표 모바일
	@RequestMapping("/sosig/ancevent/m/list.do")
	public String ancevent_list_mobile(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception{
		
		eventVO.setPageUnit(20);
		eventVO.setPageInfo(model);
		eventVO.setPageUnit(eventVO.getPageUnit()); 
		
		List<NaksinuriEventVO> list = service.getancEventList(eventVO);
		 
	
		if(list.size() > 0){
			eventVO.setTotalPage(list.get(0).getTot_cnt());
	}
	else
			eventVO.setTotalPage(0);
		
	if(list.size()>0){
		
		model.addAttribute("event_list", list);
		model.addAttribute("event_total", list.get(0).getTot_cnt());
		int cnt=list.get(0).getTot_cnt();
		int PageUnit = 20;
		int pagesize=Math.round(cnt/PageUnit)+1;
		model.addAttribute("pagesize",pagesize);
		
	}
	
	return "naksinuri_original/naksinuri/sosig/m/ancevent_list";
				
		
}
	
	@RequestMapping("/sosig/event/like.do")
	public String like(@ModelAttribute("eventVO") NaksinuriEventVO eventVO
			,HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
			String evn_no = request.getParameter("evn_no");
			model.addAttribute("evn_no",evn_no);
	
			service.like_update(eventVO);

		
			return "naksinuri_original/naksinuri/sosig/like";
	}
	
	@RequestMapping("/sosig/event/m/like.do")
	public String like_mobile(@ModelAttribute("eventVO") NaksinuriEventVO eventVO
			,HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
			String evn_no = request.getParameter("evn_no");
			model.addAttribute("evn_no",evn_no);
	
			service.like_update(eventVO);

		
			return "naksinuri_original/naksinuri/sosig/m/like";
	}
	
	@RequestMapping("/sosig/event/view.do")
	public String event_view(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request,HttpServletResponse response, ModelMap model) 
			throws Exception{
		EgovStringUtil mEgovStringUtil = new EgovStringUtil();
		//조회수 증가
		service.uphit(eventVO);
		
		NaksinuriEventVO info = service.event_findCorp(eventVO);
		model.addAttribute("info",info);
		
		
		if(request.getParameter("eco_content") !=null){
			String eco_name = request.getParameter("eco_name");
			String eco_pass = request.getParameter("eco_pass");
			String eco_content = request.getParameter("eco_content");
			
			eco_name = mEgovStringUtil.getHtmlStrCnvr(eco_name);
			eco_pass = mEgovStringUtil.getHtmlStrCnvr(eco_pass);
			
			boolean isRefusePassword = false;
			if (eco_pass != null) {
				Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9]).{10,})");
				Matcher matcher = pattern.matcher(eco_pass);
				if(!matcher.matches()) {
					isRefusePassword = true;
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
			
			eco_content = mEgovStringUtil.getHtmlStrCnvr(eco_content);
			
			eventVO.setEco_name(eco_name);
			eventVO.setEco_pass(eco_pass);
			eventVO.setEco_content(eco_content);
			
			service.eco_insert(eventVO);
		}
		
	
	
		
		//댓글 가져온다
		List<NaksinuriEventVO> comment_list = service.select_event_comment(eventVO);
		
		if(comment_list.size() > 0){
			model.addAttribute("comment_list", comment_list);
			
		}
		
		
	
		List<NaksinuriEventVO> list = service.getEventView(eventVO);
		
		//사이드 포스트 가져오기
		List<NaksinuriEventVO> post_list = service.select_event_post(eventVO);
		if(post_list.size() > 0){
			model.addAttribute("post_list", post_list);
		
		}
		
		
		if(list.size()>0){
			model.addAttribute("event_view",list);
		}
		// 이전글, 다음글 가져오기
		NaksinuriEventVO next = service.event_next(eventVO);
		NaksinuriEventVO prev = service.event_prev(eventVO);
		
		if(next != null){
			model.addAttribute("next", next);
		}
		if(prev != null){
			model.addAttribute("prev", prev);
		}
		
		return "naksinuri_original/naksinuri/sosig/event_view";
	}
	
	
	
	@RequestMapping("/sosig/event/m/view.do")
	public String event_view_mobile(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request,HttpServletResponse response, ModelMap model) 
			throws Exception{
		EgovStringUtil mEgovStringUtil = new EgovStringUtil();
		//조회수 증가
		service.uphit(eventVO);
		
		NaksinuriEventVO info = service.event_findCorp(eventVO);
		model.addAttribute("info",info);
		
		
		if(request.getParameter("eco_content") !=null){
			String eco_name = request.getParameter("eco_name");
			String eco_pass = request.getParameter("eco_pass");
			String eco_content = request.getParameter("eco_content");
			
			eco_name = mEgovStringUtil.getHtmlStrCnvr(eco_name);
			eco_pass = mEgovStringUtil.getHtmlStrCnvr(eco_pass);
			
			boolean isRefusePassword = false;
			if (eco_pass != null) {
				Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9]).{10,})");
				Matcher matcher = pattern.matcher(eco_pass);
				if(!matcher.matches()) {
					isRefusePassword = true;
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
			
			eco_content = mEgovStringUtil.getHtmlStrCnvr(eco_content);
			
			eventVO.setEco_name(eco_name);
			eventVO.setEco_pass(eco_pass);
			eventVO.setEco_content(eco_content);
			
			service.eco_insert(eventVO);
		}
		
	
	
		//댓글 가져온다
		List<NaksinuriEventVO> comment_list = service.select_event_comment(eventVO);
		
		if(comment_list.size() > 0){
			model.addAttribute("comment_list", comment_list);
			
		}
		
		
		// 이전글, 다음글 가져오기
		NaksinuriEventVO next = service.event_next(eventVO);
		NaksinuriEventVO prev = service.event_prev(eventVO);
		
		if(next != null){
			model.addAttribute("next", next);
		}
		if(prev != null){
			model.addAttribute("prev", prev);
		}
		
		return "naksinuri_original/naksinuri/sosig/m/event_view";
	}
	
	
	
	@RequestMapping("/sosig/event/eco_update.do")
	public String eco_update(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,
			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
			
			String eco_content_add = request.getParameter("eco_content_add");
			String eco_pass4 = request.getParameter("eco_pass4");
			String evn_no = request.getParameter("evn_no");
			NaksinuriEventVO spass = service.ecopass_find(eventVO);
			
			String rpass = spass.getEco_pass();
			
			if(!rpass.equals(eco_pass4)){
				model.addAttribute("fail","1");
			}else{
				eventVO.setEco_gongmo_hp(eco_content_add);
				service.eco_update(eventVO);
			}
			
			model.addAttribute("evn_no",evn_no);
		 return "naksinuri_original/naksinuri/sosig/eco_modify"; 
	}
	
	@RequestMapping("/sosig/event/m/eco_update.do")
	public String eco_update_m(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,
			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
			
			String eco_content_add = request.getParameter("eco_content_add");
			String eco_pass4 = request.getParameter("eco_pass4");
			String evn_no = request.getParameter("evn_no");
			NaksinuriEventVO spass = service.ecopass_find(eventVO);
			
			String rpass = spass.getEco_pass();
			
			if(!rpass.equals(eco_pass4)){
				model.addAttribute("fail","1");
			}else{
				eventVO.setEco_gongmo_hp(eco_content_add);
				service.eco_update(eventVO);
			}
			
			model.addAttribute("evn_no",evn_no);
		 return "naksinuri_original/naksinuri/sosig/m/eco_modify"; 
	}
	
	
	
	@RequestMapping("/sosig/event/m/eco_delete.do")
	public String eco_delete(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,
			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
			
			String eco_pass4 = request.getParameter("eco_pass4");
			String evn_no = request.getParameter("evn_no");
			NaksinuriEventVO spass = service.ecopass_find(eventVO);
			
			String rpass = spass.getEco_pass();
			
			if(!rpass.equals(eco_pass4)){
				model.addAttribute("fail","1");
			}else{
				service.eco_delete(eventVO);
			}
			
			model.addAttribute("evn_no",evn_no);
		 return "naksinuri_original/naksinuri/sosig/m/eco_del"; 
	}
	
	@RequestMapping("/sosig/event/eco_delete.do")
	public String eco_delete1(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,
			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
			
			String eco_pass4 = request.getParameter("eco_pass4");
			String evn_no = request.getParameter("evn_no");
			NaksinuriEventVO spass = service.ecopass_find(eventVO);
			
			String rpass = spass.getEco_pass();
			
			if(!rpass.equals(eco_pass4)){
				model.addAttribute("fail","1");
			}else{
				service.eco_delete(eventVO);
			}
			
			model.addAttribute("evn_no",evn_no);
		 return "naksinuri_original/naksinuri/sosig/eco_del"; 
	}
	
	
	
	@RequestMapping("/gongmo/event/m/eco_delete.do")
	public String eco_delete_gongmo_m(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,
			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
			
			String eco_pass4 = request.getParameter("eco_pass4");
			String evn_no = request.getParameter("evn_no");
			NaksinuriEventVO spass = service.ecopass_find(eventVO);
			
			String rpass = spass.getEco_pass();
			
			if(!rpass.equals(eco_pass4)){
				model.addAttribute("fail","1");
			}else{
				service.eco_delete(eventVO);
			}
			
			model.addAttribute("evn_no",evn_no);
		 return "naksinuri_original/naksinuri/gongmo/m/eco_del"; 
	}
	
	@RequestMapping("/gongmo/event/eco_delete.do")
	public String eco_delete_gongmo(@ModelAttribute("eventVO") NaksinuriEventVO eventVO,
			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
			
			String eco_pass4 = request.getParameter("eco_pass4");
			String evn_no = request.getParameter("evn_no");
			NaksinuriEventVO spass = service.ecopass_find(eventVO);
			
			String rpass = spass.getEco_pass();
			
			if(!rpass.equals(eco_pass4)){
				model.addAttribute("fail","1");
			}else{
				service.eco_delete(eventVO);
			}
			
			model.addAttribute("evn_no",evn_no);
		 return "naksinuri_original/naksinuri/gongmo/eco_del"; 
	}
	
	
	
	
	
	
	@RequestMapping({"/gongmo/event/list.do"})
	  public String gevent_list(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
	    LOGGER.debug("NaksinuriEventController - event_list : 공모전 - 이벤트 ");

	    eventVO.setPageUnit(12);
	    eventVO.setPageInfo(model);
	    eventVO.setPageUnit(eventVO.getPageUnit());

	    staticVO.setBo_name("공모전");
	    staticVO.setPath(request.getRequestURL().toString());
	    if (getClientOS(request) != "")
	      staticVO.setStatistic_os(getClientOS(request));
	    else {
	      staticVO.setStatistic_os(System.getProperty("os.name"));
	    }

	    staticVO.setBrowser(getClientBrowser(request));
	    staticVO.setClient_ip(getClientIpAddr(request));
	    staticVO.setBo_type("gongmo");

	    staticVO.setCategory_group_type("event");
	    staticVO.setCategory_group_name("이벤트");
	    staticVO.setPath_type("web");
	    staticVO.setCategory_name("진행중인 이벤트");
	    staticVO.setCategory_type("yevent");

	    this.service_statistic.get_statisticInfo(staticVO,request);

	    model.addAttribute("gallery_list", eventVO.getGallery_list());
	    List list = this.service.getEventgongmoList(eventVO);

	    if (list.size() > 0) {
	      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
	    }
	    else {
	      eventVO.setTotalPage(0);
	    }
	    if (list.size() > 0)
	    {
	      model.addAttribute("event_list", list);
	      model.addAttribute("event_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
	    }

	    return "naksinuri_original/naksinuri/gongmo/event_list";
	  }

	  @RequestMapping({"/gongmo/event/m/list.do"})
	  public String gevent_list_m(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
	    LOGGER.debug("NaksinuriEventController - event_list : 공모전 - 이벤트 ");

	    eventVO.setPageUnit(12);
	    eventVO.setPageInfo(model);
	    eventVO.setPageUnit(eventVO.getPageUnit());

	    staticVO.setBo_name("공모전");
	    staticVO.setPath(request.getRequestURL().toString());
	    if (getClientOS(request) != "")
	      staticVO.setStatistic_os(getClientOS(request));
	    else {
	      staticVO.setStatistic_os(System.getProperty("os.name"));
	    }

	    staticVO.setBrowser(getClientBrowser(request));
	    staticVO.setClient_ip(getClientIpAddr(request));
	    staticVO.setBo_type("gongmo");

	    staticVO.setCategory_group_type("event");
	    staticVO.setCategory_group_name("이벤트");
	    staticVO.setPath_type("mobile");
	    staticVO.setCategory_name("진행중인 이벤트");
	    staticVO.setCategory_type("yevent");

	    this.service_statistic.get_statisticInfo(staticVO,request);

	    model.addAttribute("gallery_list", eventVO.getGallery_list());
	    List list = this.service.getEventgongmoList(eventVO);

	    if (list.size() > 0) {
	      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
	    }
	    else {
	      eventVO.setTotalPage(0);
	    }
	    if (list.size() > 0)
	    {
	      model.addAttribute("event_list", list);
	      model.addAttribute("event_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
	    }

	    return "naksinuri_original/naksinuri/gongmo/m/event_list";
	  }

	  @RequestMapping({"/gongmo/endevent/list.do"})
	  public String gevent_list2(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
	    LOGGER.debug("NaksinuriEventController - event_list2 : 공모전 - 이벤트 ");

	    eventVO.setPageUnit(12);
	    eventVO.setPageInfo(model);
	    eventVO.setPageUnit(eventVO.getPageUnit());

	    staticVO.setBo_name("공모전");
	    staticVO.setPath(request.getRequestURL().toString());
	    if (getClientOS(request) != "")
	      staticVO.setStatistic_os(getClientOS(request));
	    else {
	      staticVO.setStatistic_os(System.getProperty("os.name"));
	    }

	    staticVO.setBrowser(getClientBrowser(request));
	    staticVO.setClient_ip(getClientIpAddr(request));
	    staticVO.setBo_type("gongmo");

	    staticVO.setCategory_group_type("event");
	    staticVO.setCategory_group_name("이벤트");
	    staticVO.setPath_type("web");
	    staticVO.setCategory_name("종료된 이벤트");
	    staticVO.setCategory_type("nevent");

	    this.service_statistic.get_statisticInfo(staticVO,request);

	    model.addAttribute("gallery_list", eventVO.getGallery_list());
	    List list = this.service.getEventgongmoendList(eventVO);

	    if (list.size() > 0) {
	      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
	    }
	    else {
	      eventVO.setTotalPage(0);
	    }
	    if (list.size() > 0)
	    {
	      model.addAttribute("event_list", list);
	      model.addAttribute("event_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
	    }

	    return "naksinuri_original/naksinuri/gongmo/endevent_list";
	  }

	  @RequestMapping({"/gongmo/endevent/m/list.do"})
	  public String gevent_list2_m(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, NaksinuriStatisticVO staticVO, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
	    LOGGER.debug("NaksinuriEventController - event_list2 : 공모전 - 이벤트 ");

	    eventVO.setPageUnit(12);
	    eventVO.setPageInfo(model);
	    eventVO.setPageUnit(eventVO.getPageUnit());

	    staticVO.setBo_name("공모전");
	    staticVO.setPath(request.getRequestURL().toString());
	    if (getClientOS(request) != "")
	      staticVO.setStatistic_os(getClientOS(request));
	    else {
	      staticVO.setStatistic_os(System.getProperty("os.name"));
	    }

	    staticVO.setBrowser(getClientBrowser(request));
	    staticVO.setClient_ip(getClientIpAddr(request));
	    staticVO.setBo_type("gongmo");

	    staticVO.setCategory_group_type("event");
	    staticVO.setCategory_group_name("이벤트");
	    staticVO.setPath_type("mobile");
	    staticVO.setCategory_name("종료된 이벤트");
	    staticVO.setCategory_type("nevent");

	    this.service_statistic.get_statisticInfo(staticVO,request);

	    model.addAttribute("gallery_list", eventVO.getGallery_list());
	    List list = this.service.getEventgongmoendList(eventVO);

	    if (list.size() > 0) {
	      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
	    }
	    else {
	      eventVO.setTotalPage(0);
	    }
	    if (list.size() > 0)
	    {
	      model.addAttribute("event_list", list);
	      model.addAttribute("event_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
	    }

	    return "naksinuri_original/naksinuri/gongmo/m/endevent_list";
	  }

	  
	  @RequestMapping({"/gongmo/ancevent/list.do"})
	  public String ancevent_list_gongmo(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
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
	    staticVO.setBo_type("gongmo");
	    staticVO.setBo_name("공모전");
	    staticVO.setCategory_group_type("event");
	    staticVO.setCategory_group_name("이벤트");
	    staticVO.setPath_type("web");
	    staticVO.setCategory_name("당첨자발표");
	    staticVO.setCategory_type("nevent");

	    this.service_statistic.get_statisticInfo(staticVO,request);
		  
		  
	    eventVO.setPageUnit(20);
	    eventVO.setPageInfo(model);
	    eventVO.setPageUnit(eventVO.getPageUnit());

	    List list = this.service.getancEventgongmoList(eventVO);

	    if (list.size() > 0) {
	      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
	    }
	    else {
	      eventVO.setTotalPage(0);
	    }
	    if (list.size() > 0)
	    {
	      model.addAttribute("ancevent_list", list);
	      model.addAttribute("event_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
	      int cnt = ((NaksinuriEventVO)list.get(0)).getTot_cnt();
	      int PageUnit = 20;
	      int pagesize = Math.round(cnt / PageUnit) + 1;
	      model.addAttribute("pagesize", Integer.valueOf(pagesize));
	    }

	    return "naksinuri_original/naksinuri/gongmo/ancevent_list";
	  }

	  @RequestMapping({"/gongmo/ancevent/m/list.do"})
	  public String ancevent_list_gongmo_m(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
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
		    staticVO.setBo_type("gongmo");
		    staticVO.setBo_name("공모전");
		    staticVO.setCategory_group_type("event");
		    staticVO.setCategory_group_name("이벤트");
		    staticVO.setPath_type("mobile");
		    staticVO.setCategory_name("당첨자발표");
		    staticVO.setCategory_type("nevent");

		    this.service_statistic.get_statisticInfo(staticVO,request);
		  
		  
	    eventVO.setPageUnit(20);
	    eventVO.setPageInfo(model);
	    eventVO.setPageUnit(eventVO.getPageUnit());

	    List list = this.service.getancEventgongmoList(eventVO);

	    if (list.size() > 0) {
	      eventVO.setTotalPage(((NaksinuriEventVO)list.get(0)).getTot_cnt());
	    }
	    else {
	      eventVO.setTotalPage(0);
	    }
	    if (list.size() > 0)
	    {
	      model.addAttribute("ancevent_list", list);
	      model.addAttribute("event_total", Integer.valueOf(((NaksinuriEventVO)list.get(0)).getTot_cnt()));
	      int cnt = ((NaksinuriEventVO)list.get(0)).getTot_cnt();
	      int PageUnit = 20;
	      int pagesize = Math.round(cnt / PageUnit) + 1;
	      model.addAttribute("pagesize", Integer.valueOf(pagesize));
	    }

	    return "naksinuri_original/naksinuri/gongmo/m/ancevent_list";
	  }

	  	  
	  @RequestMapping({"/gongmo/event/like.do"})
	  public String glike(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
	    String evn_no = request.getParameter("evn_no");
	    model.addAttribute("evn_no", evn_no);

	    this.service.like_update_gongmo(eventVO);

	    return "naksinuri_original/naksinuri/gongmo/like";
	  }

	  @RequestMapping({"/gongmo/event/m/like.do"})
	  public String glike_mobile(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
	    String evn_no = request.getParameter("evn_no");
	    model.addAttribute("evn_no", evn_no);

	    this.service.like_update_gongmo(eventVO);

	    return "naksinuri_original/naksinuri/gongmo/m/like";
	  }
	  
	  @RequestMapping({"/gongmo/event/view.do"})
	  public String gevent_view(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
		
		//조회수 증가  
		this.service.uphit_gongmo(eventVO);
	  		
		
	    NaksinuriEventVO info = this.service.event_gongmo_findCorp(eventVO);
	    model.addAttribute("info", info);
	    
	    //댓글 저장
	    if(eventVO!=null && eventVO.getEco_name()!=null && eventVO.getEco_name().length()!=0) {
	    	service.eco_insert(eventVO);
	    }
	  
	    
	    //댓글 가져온다
  		List<NaksinuriEventVO> comment_list = service.select_event_comment(eventVO);  		
  		if(comment_list.size() > 0){
  			model.addAttribute("comment_list", comment_list);
  		}

  		//공모전리스트
	    List post_list = this.service.select_gongmo_event_post(eventVO);
	    if (post_list.size() > 0) {
	      model.addAttribute("post_list", post_list);
	    }
	    
	    //공모전
	    List list = this.service.getEvent_gongmoView(eventVO);
	    if (list.size() > 0) {
	      model.addAttribute("event_view", list);
	    }

	    NaksinuriEventVO next = this.service.event_next_gongmo(eventVO);
	    NaksinuriEventVO prev = this.service.event_prev_gongmo(eventVO);

	    if (next != null) {
	      model.addAttribute("next", next);
	    }
	    if (prev != null) {
	      model.addAttribute("prev", prev);
	    }

	    return "naksinuri_original/naksinuri/gongmo/event_view";
	  }

	  @RequestMapping({"/gongmo/event/m/view.do"})
	  public String gevent_view_m(@ModelAttribute("eventVO") NaksinuriEventVO eventVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	    throws Exception
	  {
	    this.service.uphit_gongmo(eventVO);

	    NaksinuriEventVO info = this.service.event_gongmo_findCorp(eventVO);
	    model.addAttribute("info", info);
	   
	   
	    //댓글 저장
	    if(eventVO!=null && eventVO.getEco_name()!=null && eventVO.getEco_name().length()!=0) {
	    	service.eco_insert(eventVO);
	    }
	    /* 왜 이렇게 했을까?
	    if(request.getParameter("eco_name") !=null){
  			String eco_name = request.getParameter("eco_name");
  			String eco_pass = request.getParameter("eco_pass");
  			String eco_content = request.getParameter("eco_content");
  			
  			eco_name = mEgovStringUtil.getHtmlStrCnvr(eco_name);
  			eco_pass = mEgovStringUtil.getHtmlStrCnvr(eco_pass);
  			eco_content = mEgovStringUtil.getHtmlStrCnvr(eco_content);
  			
  			eventVO.setEco_name(eco_name);
  			eventVO.setEco_pass(eco_pass);
  			eventVO.setEco_content(eco_content);
  			
  			service.eco_insert(eventVO);
  		}
  		*/
	    
	    
	    //댓글 가져온다
  		List<NaksinuriEventVO> comment_list = service.select_event_comment(eventVO);
  		
  		if(comment_list.size() > 0){
  			model.addAttribute("comment_list", comment_list);
  		}

	    //공모전
	    List list = this.service.getEvent_gongmoView(eventVO);
	    if (list.size() > 0) {
	      model.addAttribute("event_view", list);
	    }

	    NaksinuriEventVO next = this.service.event_next_gongmo(eventVO);
	    NaksinuriEventVO prev = this.service.event_prev_gongmo(eventVO);

	    if (next != null) {
	      model.addAttribute("next", next);
	    }
	    if (prev != null) {
	      model.addAttribute("prev", prev);
	    }

	    return "naksinuri_original/naksinuri/gongmo/m/event_view";
	  }
	  
}
