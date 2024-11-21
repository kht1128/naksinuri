package egovframework.all.sharelink.web;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.all.login.service.LoginVO;
import egovframework.all.sharelink.service.ShareLinkService;
import egovframework.all.sharelink.service.ShareLinkVO;
import egovframework.seadm.analytics.service.AnalyticsAdmService;
import egovframework.seadm.analytics.service.AnalyticsAdmVO;
import egovframework.utils.PublicUtils;


/**
 * @Class Name : ShareLinkController.java
 * @Description : ShareLink Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019.01.22           최초생성
 *
 * @author 개발팀
 * @since 2019. 01.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by jhkim All right reserved.
 */

@Controller
public class ShareLinkController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShareLinkController.class);

	
	@Resource(name="shareLinkService")
	private ShareLinkService shareLinkService;
	
	@Resource(name = "AnalyticsAdmService")
	private AnalyticsAdmService analyticsAdmService;
	
	
	//단축URL 연결
	@RequestMapping("/share/link/{sharekey}/view.do") 
	public String share_link_show(@PathVariable("sharekey") String sharekey, @ModelAttribute("shareLinkVO") ShareLinkVO shareLinkVO,
			HttpServletRequest request, RedirectAttributes redirectAttributes, HttpServletResponse response, ModelMap model) throws Exception {	
		PublicUtils mSealifeUtils = new PublicUtils();
		shareLinkVO.setLINK_UNIQ_ID(sharekey);
		ShareLinkVO chkShareLinkVO = shareLinkService.allow_all_sharelink_info(shareLinkVO);
		if(chkShareLinkVO==null || chkShareLinkVO.getLINK_URL()==null || chkShareLinkVO.getLINK_URL().length()==0) {
			//허용하지 않거나 없는 url
			LOGGER.debug("링크거부 : 허용하지 않거나 없는 url");
			Map<String, Object> postMap = new HashMap<String,Object>();
			postMap.put("message", "비정상적인 접근이거나 허용하지 않는 URL 입니다.<br>계속 될 경우 관리자에게 문의해주세요.");
			redirectAttributes.addFlashAttribute("alert_data",postMap);
			//경로 설정
			return "redirect:/index.do";
		} else {
			//정상
			LOGGER.debug("링크허용 : 정상 url");
			String ismobile = "N";
			if(mSealifeUtils.isMobileDevice(request)) {
				ismobile = "Y";
			} 
			model.addAttribute("ismobile",ismobile);
			model.addAttribute("info",chkShareLinkVO);
			return "/all/share/view";
		}	
	}
	

	//단축URL 생성 
	@RequestMapping(value = "/share/link/copy.do", method = RequestMethod.POST)
	public @ResponseBody String share_link_copy(@ModelAttribute("shareLinkVO") ShareLinkVO shareLinkVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		JSONObject data = new JSONObject();
		try {
			String LINK_UNIQ_ID = shareLinkVO.getUniqLinkUrlKey();
			String LINK_URL = "/share/link/"+LINK_UNIQ_ID+"/view.do";
			ShareLinkVO chkShareLinkVO = shareLinkService.get_all_sharelink_info(shareLinkVO);
			if(chkShareLinkVO==null || chkShareLinkVO.getLINK_URL()==null || chkShareLinkVO.getLINK_URL().length()==0) {
				String menuUrl = shareLinkVO.getLINK_PL1().replace("sealife", "");
				if(!"".equals(menuUrl)) {
					menuUrl = "/" + menuUrl;
				}
				//신규요청
				shareLinkVO.setLINK_UNIQ_ID(LINK_UNIQ_ID);
				shareLinkVO.setLINK_URL(LINK_URL);
				if(shareLinkVO.getLINK_PL4() != null && shareLinkVO.getLINK_PL4().length() != 0) {
					shareLinkVO.setLINK_RST_URL(menuUrl+"/"+shareLinkVO.getLINK_PL2()+"/"+shareLinkVO.getLINK_PL3()+"/"+shareLinkVO.getLINK_PL4()+".do");
					shareLinkVO.setLINK_RST_URL_M(menuUrl+"/m/"+shareLinkVO.getLINK_PL2()+"/"+shareLinkVO.getLINK_PL3()+"/"+shareLinkVO.getLINK_PL4()+".do");
				} else {
					shareLinkVO.setLINK_RST_URL(menuUrl+"/"+shareLinkVO.getLINK_PL2()+"/"+shareLinkVO.getLINK_PL3()+".do");
					shareLinkVO.setLINK_RST_URL_M(menuUrl+"/"+shareLinkVO.getLINK_PL2()+"/m/"+shareLinkVO.getLINK_PL3()+".do");
				}
				shareLinkService.set_all_sharelink_reg(shareLinkVO);
			} else {
				//기존요청
				LINK_URL = chkShareLinkVO.getLINK_URL();
				shareLinkService.set_all_sharelink_uphit(chkShareLinkVO);
			}
			
			String domainStr = request.getServerName();
			if(request.getServerPort()==8181) {
				domainStr += ":"+request.getServerPort();
			}
			if(request.isSecure()) {
				domainStr = "https://" + domainStr;
			} else {
				domainStr = "http://" + domainStr;
			}
			
			data.put("sharelink", domainStr+LINK_URL);
			data.put("error", "0");
			data.put("msg", "정상적으로 처리되었습니다.");
			
			//통계
			try {
				LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
				AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
				String now_address = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
				
				analyticsAdmVO.setvisitpagenm("게시물공유URL요청");
				if(loginVO != null){
					analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
				}
				analyticsAdmVO.setvisitip(request.getRemoteAddr());
				analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
				analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
				analyticsAdmVO.setvisiturl(now_address);
				analyticsAdmService.insertAnalytics(analyticsAdmVO);
			} catch(Exception e) {
				LOGGER.debug("[fail analytics record] "+e.toString());
			}
			// 통계 끝
			
		} catch(Exception e) {
			LOGGER.debug("error",e);
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	
}


