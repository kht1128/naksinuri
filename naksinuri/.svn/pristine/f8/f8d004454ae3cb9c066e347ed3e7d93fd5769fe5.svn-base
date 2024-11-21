package egovframework.naksinuri_original.let.naksinuri.web;

import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.utils.EgovStringUtil;

@Controller
public class NaksinuriZisikinController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NaksinuriZisikinController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name="NaksinuriZisikinService")
	private NaksinuriZisikinService service;
	
	@Resource(name="NaksinuriQnAService")
	private NaksinuriQnAService qna_service; 
	
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
	//자주 묻는 질문 리스트 
	@RequestMapping("/share/nuri/list.do")
	public String zazu_list(@ModelAttribute ("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		
LOGGER.debug("NaksinuriZisikinController - zazu_list : 커뮤니티 - 자주묻는 낚시질문 ");
LOGGER.debug("- 파라미터 목록 --------------");
Enumeration<String> e = request.getParameterNames(); 
while(e.hasMoreElements()) {
	String key = e.nextElement();
	String[] data = request.getParameterValues(key);
	if(data!=null) {
		for(String eachdata : data) {
			LOGGER.debug(key + " = " + eachdata);		
		}
	}
}
LOGGER.debug("------------------------ ");
		
		
		staticVO.setBo_name("자주묻는질문");
		if(getClientOS(request) != ""){
			staticVO.setStatistic_os(getClientOS(request));
		}else{
			staticVO.setStatistic_os(System.getProperty("os.name"));
		}
		staticVO.setBrowser(getClientBrowser(request));
		staticVO.setClient_ip(getClientIpAddr(request));
		staticVO.setBo_type("zazu");
		staticVO.setPath(request.getRequestURL().toString());
		//--------------------------------------------------
		// URL 세부 카테고리 분류 추가 - 2018.06.29
		//--------------------------------------------------
		staticVO.setCategory_group_type("community");
		staticVO.setCategory_group_name("커뮤니티");
		staticVO.setPath_type("web");
		staticVO.setCategory_name("");
		staticVO.setCategory_type("");
		//--------------------------------------------------
		service_statistic.get_statisticInfo(staticVO,request);
		
		naksinuriZisikinVO.setPageUnit(20);
		naksinuriZisikinVO.setPageInfo(model);
		naksinuriZisikinVO.setPageUnit(naksinuriZisikinVO.getPageUnit());
		
		List<NaksinuriZisikinVO> list = service.getListZazu(naksinuriZisikinVO);
		
		if(list.size() >0 ){
			naksinuriZisikinVO.setTotalPage(list.get(0).getTot_cnt());
		}
		else
			naksinuriZisikinVO.setTotalPage(0);
		
		if(list.size()>0){
			
			model.addAttribute("zazu_list", list);
			model.addAttribute("zazu_gun", list.size());
			model.addAttribute("zazu_total", list.get(0).getTot_cnt());
			int cnt=list.get(0).getTot_cnt();
			int PageUnit = 20;
			int pagesize=Math.round(cnt/PageUnit)+1;
			model.addAttribute("pagesize",pagesize);
			
		}
		
		
		return "naksinuri_original/naksinuri/share/nuri_list";
	}
	
	
		
	
	

	
	
	
	@RequestMapping("/share/nuri/m/list.do")
	public String nuri_mobile_list(@ModelAttribute ("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		
LOGGER.debug("NaksinuriZisikinController - nuri_mobile_list : 커뮤니티(모바일) - 자주묻는 낚시질문 ");
LOGGER.debug("- 파라미터 목록 --------------");
Enumeration<String> e = request.getParameterNames(); 
while(e.hasMoreElements()) {
	String key = e.nextElement();
	String[] data = request.getParameterValues(key);
	if(data!=null) {
		for(String eachdata : data) {
			LOGGER.debug(key + " = " + eachdata);		
		}
	}
}
LOGGER.debug("------------------------ ");

		staticVO.setBo_name("자주묻는질문");
		if(getClientOS(request) != ""){
			staticVO.setStatistic_os(getClientOS(request));
		}else{
			staticVO.setStatistic_os(System.getProperty("os.name"));
		}
		staticVO.setBrowser(getClientBrowser(request));
		staticVO.setClient_ip(getClientIpAddr(request));
		staticVO.setBo_type("zazu");
		staticVO.setPath(request.getRequestURL().toString());
		//--------------------------------------------------
		// URL 세부 카테고리 분류 추가 - 2018.06.29
		//--------------------------------------------------
		staticVO.setCategory_group_type("community");
		staticVO.setCategory_group_name("커뮤니티");
		staticVO.setPath_type("mobile");
		staticVO.setCategory_name("");
		staticVO.setCategory_type("");
		//--------------------------------------------------
		service_statistic.get_statisticInfo(staticVO,request);
		
		naksinuriZisikinVO.setPageUnit(10);
		naksinuriZisikinVO.setPageInfo(model);
		naksinuriZisikinVO.setPageUnit(naksinuriZisikinVO.getPageUnit());
		
		List<NaksinuriZisikinVO> list = service.getListZazu(naksinuriZisikinVO);
		
		if(list.size() >0 ){
			naksinuriZisikinVO.setTotalPage(list.get(0).getTot_cnt());
		}
		else
			naksinuriZisikinVO.setTotalPage(0);
		
		if(list.size()>0){
			
			model.addAttribute("zazu_list", list);
			model.addAttribute("zazu_gun", list.size());
			model.addAttribute("zazu_total", list.get(0).getTot_cnt());
			int cnt=list.get(0).getTot_cnt();
			int PageUnit = 10;
			int pagesize=Math.round(cnt/PageUnit)+1;
			model.addAttribute("pagesize",pagesize);
			
		}
		
		return "naksinuri_original/naksinuri/share/m/nuri_list";
	}
	
	@RequestMapping("/share/nuri/insertzazu.do")
	public String insert_zazu(final MultipartHttpServletRequest multiRequest, 
			SessionStatus status, @ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,
			BindingResult bindingResult, ModelMap model  ) throws Exception{
		
		service.insertZazu(naksinuriZisikinVO);
		
		return "redirect:/share/nuri/list.do";	
	}
	
	
	//자주 묻는 질문 어드민 리스트 
  	@RequestMapping("/admin/share/nuri/list.do")
  	public String adm_zazu_list(@ModelAttribute ("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
  		
  		
  		
  		naksinuriZisikinVO.setPageUnit(20);
  		naksinuriZisikinVO.setPageInfo(model);
  		naksinuriZisikinVO.setPageUnit(naksinuriZisikinVO.getPageUnit());
  		
  		List<NaksinuriZisikinVO> list = service.getListZazu(naksinuriZisikinVO);
  		
  		if(list.size() >0 ){
  			naksinuriZisikinVO.setTotalPage(list.get(0).getTot_cnt());
  		}
  		else
  			naksinuriZisikinVO.setTotalPage(0);
  		
  		if(list.size()>0){
  			
  			model.addAttribute("zazu_list", list);
  			model.addAttribute("zazu_gun", list.size());
  			model.addAttribute("zazu_total", list.get(0).getTot_cnt());
  			int cnt=list.get(0).getTot_cnt();
  			int PageUnit = 20;
  			int pagesize=Math.round(cnt/PageUnit)+1;
  			model.addAttribute("pagesize",pagesize);
  			
  		}
  		
  		
  		return "naksinuri_original/naksinuri/admin/share/nuri_list";
  	}
  	
  //자주 묻는 질문 어드민 리스트 
  	@RequestMapping("/admin/share/nuri/trash.do")
  	public String adm_zazu_trash(@ModelAttribute ("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
  		
  		naksinuriZisikinVO.setPageUnit(20);
  		naksinuriZisikinVO.setPageInfo(model);
  		naksinuriZisikinVO.setPageUnit(naksinuriZisikinVO.getPageUnit());
  		
  		List<NaksinuriZisikinVO> list = service.getTrashZazu(naksinuriZisikinVO);
  		
  		if(list.size() >0 ){
  			naksinuriZisikinVO.setTotalPage(list.get(0).getTot_cnt());
  		}
  		else
  			naksinuriZisikinVO.setTotalPage(0);
  		
  		if(list.size()>0){
  			
  			model.addAttribute("zazu_list", list);
  			model.addAttribute("zazu_gun", list.size());
  			model.addAttribute("zazu_total", list.get(0).getTot_cnt());
  			int cnt=list.get(0).getTot_cnt();
  			int PageUnit = 20;
  			int pagesize=Math.round(cnt/PageUnit)+1;
  			model.addAttribute("pagesize",pagesize);
  			
  		}
  		
  		
  		return "naksinuri_original/naksinuri/admin/share/nuri_trash";
  	}
  	
  	@RequestMapping("/admin/share/nuri/find.do")
  	public String nuri_find(@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,
  			HttpServletRequest request, HttpServletResponse response,
  			ModelMap model) throws Exception{
  		
  		NaksinuriZisikinVO info = service.nuri_find(naksinuriZisikinVO);
  		
  		model.addAttribute("info",info);
  		
  		return "naksinuri_original/naksinuri/admin/share/nuri_view";
  		
  	}
  	
  	@RequestMapping("/admin/share/nuri/insertzazu.do")
	public String adm_insert_zazu(final MultipartHttpServletRequest multiRequest, 
			SessionStatus status, @ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,
			BindingResult bindingResult, ModelMap model  ) throws Exception{
		
  		EgovStringUtil egovStringUtil = new EgovStringUtil();
  		
  		if(naksinuriZisikinVO.getZazu_ques() != null && naksinuriZisikinVO.getZazu_ques().length() != 0){//질문
  			naksinuriZisikinVO.setZazu_ques(egovStringUtil.getHtmlStrCnvr(naksinuriZisikinVO.getZazu_ques()));
  		}
  		if(naksinuriZisikinVO.getZazu_answ() != null && naksinuriZisikinVO.getZazu_answ().length() != 0){//답변
  			naksinuriZisikinVO.setZazu_answ(egovStringUtil.getHtmlStrCnvr(naksinuriZisikinVO.getZazu_answ()));
  		}
  		
		service.insertZazu(naksinuriZisikinVO);
		
		return "redirect:/admin/share/nuri/list.do";	
	}
  	
  	@RequestMapping("/admin/share/nuri/updatezazu.do")
	public String adm_update_zazu(final MultipartHttpServletRequest multiRequest, 
			SessionStatus status, @ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,
			BindingResult bindingResult, ModelMap model  ) throws Exception{
  		EgovStringUtil egovStringUtil = new EgovStringUtil();
  		
  		if(naksinuriZisikinVO.getZazu_ques() != null && naksinuriZisikinVO.getZazu_ques().length() != 0){//질문
  			naksinuriZisikinVO.setZazu_ques(egovStringUtil.getHtmlStrCnvr(naksinuriZisikinVO.getZazu_ques()));
  		}
  		if(naksinuriZisikinVO.getZazu_answ() != null && naksinuriZisikinVO.getZazu_answ().length() != 0){//답변
  			naksinuriZisikinVO.setZazu_answ(egovStringUtil.getHtmlStrCnvr(naksinuriZisikinVO.getZazu_answ()));
  		}
		
		service.updateZazu(naksinuriZisikinVO);
		
		return "redirect:/admin/share/nuri/list.do";	
	}
  	
  	@RequestMapping("/admin/share/nuri/deletezazu.do")
  	public String adm_delete_zazu(@RequestParam("bo_sns") String bo_sns,@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
  		String [] strboIds = bo_sns.split(";");
  		for(int i=0; i<strboIds.length;i++){
  			naksinuriZisikinVO.setZazu_num(strboIds[i]);
  		service.deleteZazu(naksinuriZisikinVO);
  		}
  		return "redirect:/admin/share/nuri/trash.do";
  	}
  	
	@RequestMapping("/admin/share/nuri/restorezazu.do")
  	public String adm_restore_zazu(@RequestParam("bo_sns") String bo_sns,@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
  		String [] strboIds = bo_sns.split(";");
  		for(int i=0; i<strboIds.length;i++){
  			naksinuriZisikinVO.setZazu_num(strboIds[i]);
  		service.restoreZazu(naksinuriZisikinVO);
  		}
  		return "redirect:/admin/share/nuri/trash.do";
  	}
	@RequestMapping("/admin/share/nuri/gotrashzazu.do")
  	public String adm_trash_zazu(@RequestParam("bo_sns") String bo_sns,@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
  		String [] strboIds = bo_sns.split(";");
  		for(int i=0; i<strboIds.length;i++){
  			naksinuriZisikinVO.setZazu_num(strboIds[i]);
  		service.gotrashZazu(naksinuriZisikinVO);
  		}
  		return "redirect:/admin/share/nuri/list.do";
  	}
  
  //qna 
  	@RequestMapping("/promotion/qna/list.do")
  	public String qna_list(@ModelAttribute ("qnaVO") NaksinuriQnAVO qnaVO, NaksinuriStatisticVO staticVO,HttpServletRequest request, HttpServletResponse response,String qna_type, ModelMap model) throws Exception{
  		
  		
LOGGER.debug("NaksinuriZisikinController - qna_list : 낚시정책 - 낚시법 및 유어장 관련 질의회신 사례 ");
LOGGER.debug("- 파라미터 목록 --------------");
Enumeration<String> e = request.getParameterNames(); 
while(e.hasMoreElements()) {
	String key = e.nextElement();
	String[] data = request.getParameterValues(key);
	if(data!=null) {
		for(String eachdata : data) {
			LOGGER.debug(key + " = " + eachdata);		
		}
	}
}
LOGGER.debug("------------------------ ");
  		
  		
  		if(qnaVO.getQna_type() == null){
  			LOGGER.debug("-> null qnaVO ");
			qnaVO.setQna_type("낚시관리및제도일반");
		}
  		qna_type=qnaVO.getQna_type();
  		
  		
  		staticVO.setBo_name("낚시법및유어장관련질의회신사례");
  		if(getClientOS(request) != ""){
			staticVO.setStatistic_os(getClientOS(request));
		}else{
			staticVO.setStatistic_os(System.getProperty("os.name"));
		}
		staticVO.setClient_ip(getClientIpAddr(request));
		staticVO.setBrowser(getClientBrowser(request));
  		staticVO.setBo_type("qna");
  		staticVO.setPath(request.getRequestURL().toString());
  		
  		//--------------------------------------------------
		// URL 세부 카테고리 분류 추가 - 2018.06.29
		//--------------------------------------------------
  		String category_type = "";
		String category_name = qna_type;
  		if(qna_type.equals("낚시터")) {
  			category_type = "fishing_hole";
  		} else if(qna_type.equals("낚시어선")) {
  			category_type = "fishing_boats";
  		} else if(qna_type.equals("낚시도구및미끼")) {
  			category_type = "fishing_tackle_bait";
  		} else if(qna_type.equals("유어장")) {
  			category_type = "fishing_area";
  		} else if(qna_type.equals("참고자료")) {
  			category_type = "fishing_resources";
  		} else {//낚시관리및제도일반
  			category_type = "fishing_management_system";
  		}
  		staticVO.setCategory_group_type("fishing_policy");
		staticVO.setCategory_group_name("낚시정책");
		staticVO.setCategory_type(category_type);
		staticVO.setCategory_name(category_name);
		staticVO.setPath_type("web");
		//--------------------------------------------------
		
		service_statistic.get_statisticInfo(staticVO,request);
  		
  		
		
  		model.addAttribute("qna_type",qna_type);
  		
  		qnaVO.setPageInfo(model);
  		qnaVO.setPageUnit(qnaVO.getPageUnit());
  		
  		List<NaksinuriQnAVO> list = qna_service.getListQnA(qnaVO);
  		
  		if(list.size() >0 ){
  			qnaVO.setTotalPage(list.get(0).getTot_cnt());
  		}
  		else
  			qnaVO.setTotalPage(0);
  		
  		if(list.size()>0){
  			
  			//--------------------------------------------------
  			// 2018.07.18 바로가기 버튼 관련 추가
  			model.addAttribute("shortcutlink", qnaVO.getShortcutlink());
  			//--------------------------------------------------
  			model.addAttribute("select_list", list);
  			model.addAttribute("select_total", list.get(0).getTot_cnt());
  			
  			model.addAttribute("category_type", category_type);
  			
  		}
  		return "naksinuri_original/naksinuri/share/qna_list";
  	}
  	
	@RequestMapping("/promotion/qna/m/list.do")
  	public String qna_list_mobile(@ModelAttribute ("qnaVO") NaksinuriQnAVO qnaVO,NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception{
  		
LOGGER.debug("NaksinuriZisikinController - qna_list_mobile : 낚시정책(모바일) - 낚시법 및 유어장 관련 질의회신 사례 ");
LOGGER.debug("- 파라미터 목록 --------------");
Enumeration<String> e = request.getParameterNames(); 
while(e.hasMoreElements()) {
	String key = e.nextElement();
	String[] data = request.getParameterValues(key);
	if(data!=null) {
		for(String eachdata : data) {
			LOGGER.debug(key + " = " + eachdata);		
		}
	}
}
LOGGER.debug("------------------------ ");

		staticVO.setBo_name("낚시법및유어장관련질의회신사례");
		if(getClientOS(request) != ""){
			staticVO.setStatistic_os(getClientOS(request));
		}else{
			staticVO.setStatistic_os(System.getProperty("os.name"));
		}
		staticVO.setClient_ip(getClientIpAddr(request));
		staticVO.setBrowser(getClientBrowser(request));
		staticVO.setBo_type("qna");
		staticVO.setPath(request.getRequestURL().toString());
		
		String qna_type="";
		String qna_type_param =request.getParameter("qna_type");
		if(qna_type_param == null){
			qnaVO.setQna_type("낚시관리및제도일반");
			qna_type="낚시관리및제도일반";
		}else{
			qnaVO.setQna_type(qna_type_param);
			qna_type=qna_type_param;
		}
		
		//--------------------------------------------------
		// URL 세부 카테고리 분류 추가 - 2018.06.29
		//--------------------------------------------------
  		String category_type = "";
		String category_name = qna_type;
  		if(qna_type.equals("낚시터")) {
  			category_type = "fishing_hole";
  		} else if(qna_type.equals("낚시어선")) {
  			category_type = "fishing_boats";
  		} else if(qna_type.equals("낚시도구및미끼")) {
  			category_type = "fishing_tackle_bait";
  		} else if(qna_type.equals("유어장")) {
  			category_type = "fishing_area";
  		} else if(qna_type.equals("참고자료")) {
  			category_type = "fishing_resources";
  		} else {//낚시관리및제도일반
  			category_type = "fishing_management_system";
  		}
		staticVO.setCategory_type(category_type);
		staticVO.setCategory_name(category_name);
		staticVO.setPath_type("mobile");
		//--------------------------------------------------
		
		service_statistic.get_statisticInfo(staticVO,request);
		
		
  		
  		model.addAttribute("qna_type",qna_type);
  		qnaVO.setPageInfo(model);
  		qnaVO.setPageUnit(qnaVO.getPageUnit());
  		
  		List<NaksinuriQnAVO> list = qna_service.getListQnA(qnaVO);
  		
  		if(list.size() >0 ){
  			qnaVO.setTotalPage(list.get(0).getTot_cnt());
  		}
  		else
  			qnaVO.setTotalPage(0);
  		
  		if(list.size()>0){
  			
  			//--------------------------------------------------
  			// 2018.07.18 바로가기 버튼 관련 추가
  			model.addAttribute("shortcutlink", qnaVO.getShortcutlink());
  			//--------------------------------------------------
  			
  			model.addAttribute("select_list", list);
  			model.addAttribute("select_total", list.get(0).getTot_cnt());
  			
  		}
  		return "naksinuri_original/naksinuri/share/m/qna_list";
  	}
	//누리지식인 리스트 
	/*@RequestMapping("/share/zisik/list.do")
	public String zisik_list(@ModelAttribute ("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		
		staticVO.setBo_name("누리지식인");
		if(getClientOS(request) != ""){
			staticVO.setStatistic_os(getClientOS(request));
		}else{
			staticVO.setStatistic_os(System.getProperty("os.name"));
		}
		staticVO.setClient_ip(getClientIpAddr(request));
		staticVO.setBrowser(getClientBrowser(request));
		staticVO.setBo_type("nuri");
		staticVO.setPath(request.getRequestURL().toString());
		service_statistic.get_statisticInfo(staticVO,request);
		
		naksinuriZisikinVO.setPageInfo(model);
		naksinuriZisikinVO.setPageUnit(naksinuriZisikinVO.getPageUnit());
		
		List<NaksinuriZisikinVO> list = service.getListZisik(naksinuriZisikinVO);
		
		if(list.size() >0 ){
			naksinuriZisikinVO.setTotalPage(list.get(0).getTot_cnt());
		}
		else
			naksinuriZisikinVO.setTotalPage(0);
		
		if(list.size()>0){
			
			model.addAttribute("zisik_list", list);
			model.addAttribute("zisik_gun", list.size());
			model.addAttribute("zisik_total", list.get(0).getTot_cnt());
			
		}
		return "naksinuri_original/naksinuri/share/zisik_list";
	}
	
	//누리지식인 뷰
	@RequestMapping("/share/zisik/view.do")
	public String zisik_view(@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, HttpServletRequest request,HttpServletResponse response, ModelMap model) 
			throws Exception{
		
		//조회수 증가
		service.zisik_view_count(naksinuriZisikinVO);
		
		NaksinuriZisikinVO info = service.zisik_find(naksinuriZisikinVO);
		model.addAttribute("info",info);
		
		
		//답변 가져오기
		List<NaksinuriZisikinVO> list = service.getListAnswer(naksinuriZisikinVO);
		
		if(list.size()>0){
			model.addAttribute("answer_list",list);
		}
		// 이전글, 다음글 가져오기
		NaksinuriZisikinVO next = service.zisik_next(naksinuriZisikinVO);
		NaksinuriZisikinVO prev = service.zisik_prev(naksinuriZisikinVO);
		
		if(next != null){
			model.addAttribute("next", next);
		}
		if(prev != null){
			model.addAttribute("prev", prev);
		}
		
		return "naksinuri_original/naksinuri/share/zisik_view";
	}
	
	//누리지식인 뷰
		@RequestMapping("/share/zisik/write.do")
		public String zisik_write(@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, 
				HttpServletRequest request,HttpServletResponse response, ModelMap model) 
				throws Exception{
			
			String siteKey = "6Lf_0iQUAAAAAPOMJw2jXisV8os5uUe0cMPgk2m-";
			model.addAttribute("siteKey",siteKey);
			
			return "naksinuri_original/naksinuri/share/zisik_write";
		}
		
		@RequestMapping("/share/zisik/modify.do")
		public String zisik_modify(@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, 
				HttpServletRequest request,HttpServletResponse response, ModelMap model) 
				throws Exception{
			
			NaksinuriZisikinVO info = service.zisik_find(naksinuriZisikinVO);
			model.addAttribute("info",info);
			
			return "forward:/share/zisik/write.do";
		}
	
		@RequestMapping("/share/zisik/insert_zisik.do")
		public String insert_zisik(MultipartHttpServletRequest multiRequest,SessionStatus status, 
				@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,
				BindingResult bindingResult, ModelMap model) throws Exception{
			
			VerifyRecaptcha.setSecretKey("6Lf_0iQUAAAAAKsy_UkxMQGQq5BrkDgJ-D_WbPKL"); //secretKey 세팅
			String gRecaptchaResponse = request.getParameter("recaptcha"); //recapcha 파라미터 가져오기
				
		        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse); //리캡챠 인증 true,false 설공 실패 리턴
				String result = "fail";
		        if(verify){
		        	result = "success";
		        }
		
			
			service.insert_zisik(naksinuriZisikinVO);
			
			return "redirect:/share/zisik/list.do";
		}
		
		
		//누리지식인 뷰
		@RequestMapping("/share/zisik/fail.do")
		public String zisik_fail(@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO, 
				HttpServletRequest request,HttpServletResponse response, ModelMap model) 
				throws Exception{
			
			return "naksinuri_original/naksinuri/share/zisik_fail";
		}
		
		
		@RequestMapping("/share/zisik/update_zisik.do")
		public String update_zisik(MultipartHttpServletRequest multiRequest,SessionStatus status, 
				@ModelAttribute("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,
				BindingResult bindingResult, ModelMap model) throws Exception{
			
			String pconfirm = multiRequest.getParameter("nuri_q_pconfirm");
			
			NaksinuriZisikinVO pass=service.zpass_find(naksinuriZisikinVO);
			
			String rpass = pass.getNuri_q_pass();
			
			if(!rpass.equals(pconfirm)){
				return "redirect:/share/zisik/fail.do";
			}else{
				service.update_zisik(naksinuriZisikinVO);
				naksinuriZisikinVO.getNuri_q_num();
				return "forward:/share/zisik/view.do";
			}
		}
	
		
		
	//누리지식인 리스트 
		@RequestMapping("/share/zisik/m/list.do")
		public String zisik_mobile_list(@ModelAttribute ("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,NaksinuriStatisticVO staticVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)
				throws Exception{
			
			staticVO.setBo_name("누리지식인");
			if(getClientOS(request) != ""){
				staticVO.setStatistic_os(getClientOS(request));
			}else{
				staticVO.setStatistic_os(System.getProperty("os.name"));
			}
			staticVO.setBrowser(getClientBrowser(request));
			staticVO.setClient_ip(getClientIpAddr(request));
			staticVO.setBo_type("nuri");
			staticVO.setPath(request.getRequestURL().toString());
			service_statistic.get_statisticInfo(staticVO,request);
			
			naksinuriZisikinVO.setPageUnit(10);
			naksinuriZisikinVO.setPageInfo(model);
			naksinuriZisikinVO.setPageUnit(naksinuriZisikinVO.getPageUnit());
			
			List<NaksinuriZisikinVO> list = service.getListZisik(naksinuriZisikinVO);
			
			if(list.size() >0 ){
				naksinuriZisikinVO.setTotalPage(list.get(0).getTot_cnt());
			}
			else
				naksinuriZisikinVO.setTotalPage(0);
			
			if(list.size()>0){
				
				model.addAttribute("zisik_list", list);
				model.addAttribute("zisik_gun", list.size());
				model.addAttribute("zisik_total", list.get(0).getTot_cnt());
				int cnt=list.get(0).getTot_cnt();
				int PageUnit = 10;
				int pagesize=Math.round(cnt/PageUnit)+1;
				model.addAttribute("pagesize",pagesize);
				
			}
			return "naksinuri_original/naksinuri/share/m/zisik_list";
		}
		
		//누리지식인 뷰
		@RequestMapping("/share/zisik/m/view.do")
		public String zisik_mobile_view() throws Exception{
			return "naksinuri_original/naksinuri/share/m/zisik_view";
		}
	
*/
	
}
