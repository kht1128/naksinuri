package egovframework.naksinuri_original.let.naksinuri.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriShortcutLinkService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriShortcutLinkVO;
import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;


@Controller
public class NaksinuriShortcutLink {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NaksinuriShortcutLink.class);
	
	@Resource(name="NaksinuriShortcutLinkService")
	private NaksinuriShortcutLinkService service_shortcutlink;
	
	
	public static boolean isMobileDevice(HttpServletRequest request) {
		boolean isok = false;		
		String user_os = request.getHeader("User-Agent");
		if (user_os.indexOf("iPhone") > 0 || user_os.indexOf("iPad") > 0) {
			isok = true;
		} else if (user_os.indexOf("Android") > 0) {
			isok = true;
		} else if (user_os.indexOf("Windows Phone") > 0) {
			isok = true;
		} else if (user_os.indexOf("Nokia") > 0) {
			isok = true;
		} else {
			isok = false;
		}
		return isok;
	}
	
	@RequestMapping("/shortcut/{uni_key}/link.do") 
	public String to_link_uniqueue(
			@ModelAttribute("eventVO") NaksinuriEventVO eventVO,
			@ModelAttribute("boardVO") BoardVO boardVO, 
			@ModelAttribute ("qnaVO") NaksinuriQnAVO qnaVO,
			@ModelAttribute ("surveyVO") SurveyVO surveyVO,
			ModelMap model,NaksinuriShortcutLinkVO staticVO,
			@PathVariable("uni_key") String uni_key, HttpServletRequest request, 
			RedirectAttributes redirectAttributes, HttpServletResponse response){
				
		// http://localhost:8181/shortcut/campaign1/link.do
		// http://localhost:8181/shortcut/qna1/link.do
		// http://localhost:8181/shortcut/notice/link.do
		/*
		  	NaksinuriShortcutLinkVO : 데이터박스
			NaksinuriShortcutLinkService -> NaksinuriShortcutLinkServiceImpl (override) -> NaksinuriShortcutLinkDAO (sql) -> NaksinuriShortcutLinkVO (return)
		*/
		LOGGER.debug("NaksinuriShortcutLink - to_link_uniqueue >> /shortcut/"+uni_key+"/link.do");
		
		String redirectUrl = "";
		
		staticVO.setUniq_key(uni_key);
		NaksinuriShortcutLinkVO list = service_shortcutlink.get_link(staticVO);
		
		if(list==null) {
			LOGGER.debug("데이터 없음");
		} else {
			LOGGER.debug("데이터 있음");
			LOGGER.debug("is_board_type : "+list.getIs_board_type());
			LOGGER.debug("bo_sn : "+list.getBo_sn());
			if(list.getIs_board_type() == 1) { //qna	
				LOGGER.debug(">> qna link");
				qnaVO.setPageIndex(list.getPage_num());
				qnaVO.setPageUnit(10);
				qnaVO.setQna_type(list.getQna_type());
				qnaVO.setShortcutlink(list.getBo_sn());	
				
				redirectAttributes.addFlashAttribute("qnaVO", qnaVO);	
			} else if(list.getIs_board_type() == 2) { //event ( 낚시소식 - 이벤트 )
				LOGGER.debug(">> event link");
				eventVO.setEvn_no(list.getBo_sn());
				redirectAttributes.addFlashAttribute("eventVO", eventVO);
			} else if(list.getIs_board_type() == 3) { //survey ( 커뮤니티 - 설문조사 )
				LOGGER.debug(">> survey link");
				surveyVO.setSv_id(list.getBo_sn());
				redirectAttributes.addFlashAttribute("surveyVO", surveyVO);		
			} else { 
				LOGGER.debug(">> default link");
				boardVO.setBo_sn(list.getBo_sn());
				redirectAttributes.addFlashAttribute(boardVO);
			}
			if(isMobileDevice(request)) {
				redirectUrl = "redirect:"+list.getUrl_mobile();
			} else {
				redirectUrl = "redirect:"+list.getUrl_web();
			}
		}	
		
		return redirectUrl;
		
	}
	
	
	
	
	
	/*
	@RequestMapping(value="/shortcut/link.do", method = RequestMethod.POST) 
	public String to_link(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpServletResponse response){
		
		// http://localhost:8181/shortcut/link.do?a=1
		
		String redirectUrl = "";
		
		LOGGER.debug("NaksinuriShortcutLink - to_link >> /shortcut/link.do");

		LOGGER.debug("- 파라미터 목록 --------------");
		HashMap<String,Object> getPostParameters = new HashMap<String,Object>();
		Enumeration<String> e = request.getParameterNames();
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			String[] data = request.getParameterValues(key);
			if(data!=null) {
				for(String eachdata : data) {
					LOGGER.debug(key + " = " + eachdata);		
					getPostParameters.put(key, eachdata);
				}
			}
		}
		LOGGER.debug("------------------------ ");
		
		if(getPostParameters.containsKey("a")) {
			HashMap<String,Object> postParameters = new HashMap<String,Object>();
			if(getPostParameters.get("a").equals("1")) {		
				LOGGER.debug("... 낚시관리 및 제도일반 링크");
				postParameters.put("pageIndex", "1");
				postParameters.put("pageUnit", "10");
				postParameters.put("qna_type", "낚시관리및제도일반");
				postParameters.put("s_pageUnit", "10");
				redirectAttributes.addFlashAttribute(postParameters);				
				redirectUrl = "/promotion/qna/list.do";
			} else if(getPostParameters.get("a").equals("2")) {
				LOGGER.debug("... 낚시캠페인 링크");
				boardVO.setBo_sn("460");
				redirectAttributes.addFlashAttribute(boardVO);
				redirectUrl = "/promotion/campaign/view.do";
			} else {
				
			}
		}		
		
		return "redirect:"+redirectUrl;
	}
	*/
	
}
