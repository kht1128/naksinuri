package egovframework.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rte.fdl.property.EgovPropertyService;

public class UrlActionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(UrlActionInterceptor.class);

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("*******************************************************");
		LOGGER.debug("preHandle ... ");
		PublicUtils.scanPrintInfoWithParameters(request);
		if(request!=null && request.getRequestURL()!=null && request.getRequestURL().length()!=0) {
			LOGGER.debug(">> 도메인검증");
			/*
			if(request.getRequestURL().toString().contains("m.sealife.go.kr")) {
				String rsturl = request.getRequestURL().toString().replace("m.sealife.go.kr", "www.sealife.go.kr");
				LOGGER.debug("변경해야할 도메인으로 접근함... 변경 > " + rsturl);
				response.sendRedirect(rsturl);
				return false;
			}
			*/
		}		
		LOGGER.debug("*******************************************************");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		if(modelAndView!=null) {
			LOGGER.debug("*******************************************************");
			LOGGER.debug("postHandle  : " + modelAndView.getViewName());
			/*
			//자주묻는질문
			if(modelAndView.getViewName().contains("/mobile/")) {
				LOGGER.debug("처리 > 자주묻는질문 리스트 조회");
				int faqLimitCnt = 4;
				SealifeMainVO sealifeMainVO = new SealifeMainVO();
				sealifeMainVO.setbdid("board003");	
				sealifeMainVO.setlimitCnt(faqLimitCnt);
				List<SealifeMainVO> list_faq = sealifeMainService.get_main_bbs_total_list(sealifeMainVO);
				modelAndView.addObject("list_faq",list_faq);
			} 
			//로그인api관련
			if(modelAndView.getViewName().contains("/member/login")
				|| modelAndView.getViewName().contains("/member/joincheck")
				|| modelAndView.getViewName().contains("/member/findid")
				|| modelAndView.getViewName().contains("/member/findpw")
				|| modelAndView.getViewName().contains("/mobile/member/login")
				|| modelAndView.getViewName().contains("/mobile/member/joincheck")
				|| modelAndView.getViewName().contains("/mobile/member/findid")
				|| modelAndView.getViewName().contains("/mobile/member/findpw")
			) {
				LOGGER.debug("처리 > 로그인api관련");
				modelAndView.addObject("kakao_jskey", propertiesService.getString("Social.api.kakao.jskey").trim());
				modelAndView.addObject("naver_clientid", propertiesService.getString("Social.api.naver.clientid").trim());
				modelAndView.addObject("naver_domain", propertiesService.getString("Social.api.naver.domain").trim());
				modelAndView.addObject("naver_callback", propertiesService.getString("Social.api.naver.callback").trim());
				modelAndView.addObject("recaptcha_sitekey", propertiesService.getString("recaptcha.googleapi.sitekey").trim());
			}
			*/
			LOGGER.debug("*******************************************************");
		}
		
	}
	
}
