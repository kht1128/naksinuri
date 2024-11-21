package egovframework.cti.main.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.all.login.service.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;

public class CtiAdmLoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CtiAdmLoginInterceptor.class);
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("*******************************************************");
		
		/** 
		 * MBR_LV_ID 		: 회원레벨			( 1:통합관리자,2:총관리자,3:센터운영자,4:외부운영자,10:일반사용자 )   
		 * MBR_GRP_1_ST 	: 종합센터 권한허용여부 	('Y','N') 
		 * MBR_GRP_2_ST 	: 교육센터 권한허용여부 	('Y','N') 
		 * MBR_GRP_3_ST 	: 박랍회 권한허용여부 	('Y','N') 
		 * MBR_GRP_4_ST 	: CTI 권한허용여부	('Y','N')
		 * MBR_POSITION_CD 	: 직급코드			(0:기본,1:홈스테이운영자,2:귀어닥터운영자) 
		 */
	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			LOGGER.debug("CTI 관리자페이지는 무조건 로그인이 필요한 서비스!!");
			response.sendRedirect("/cti/member/login.do");
			return false;
		} else {
			LOGGER.debug("아이디 : " + loginVO.getMBR_ID());
			LOGGER.debug("권한레벨 : " + loginVO.getMBR_LV_ID());
			LOGGER.debug("그룹 : " + loginVO.getMBR_GRP_ID());
			LOGGER.debug("직급코드 : " + loginVO.getMBR_POSITION_CD());
			LOGGER.debug("낚시누리 권한허용여부 : " + loginVO.getMBR_GRP_1_ST());
			LOGGER.debug("낚시전문교육 권한허용여부 : " + loginVO.getMBR_GRP_2_ST());
			LOGGER.debug("CTI 권한허용여부 : " + loginVO.getMBR_GRP_4_ST());
			//권한 및 그룹체크
			if((loginVO.getMBR_LV_ID().equals("1")) //최상위등급,그룹제한없음 = 통합관리자
			|| ( loginVO.getMBR_LV_ID().equals("2") && loginVO.getMBR_GRP_4_ST().equals("Y") ) //1등급 = 총관리자
			|| ( loginVO.getMBR_LV_ID().equals("3") && loginVO.getMBR_GRP_4_ST().equals("Y") ) //2등급 = 센터운영자
			|| ( loginVO.getMBR_LV_ID().equals("4") && loginVO.getMBR_GRP_4_ST().equals("Y") ) //3등급 = 타기관운영자
			) {
				LOGGER.debug("CTI 관리자페이지 - 접근 허용!!");	
			} else {
				/*
				if(loginVO.getMBR_GRP_1_ST().equals("Y")) {
					response.sendRedirect("/adm/index.do");
					return false;
				} 
				*/
				LOGGER.debug("CTI 관리자페이지 - 접근할 수 있는 권한이 없음!!");
				//request.getSession().setAttribute("LoginVO", null);
				response.sendRedirect("/cti/error/unauth.do");
				return false;
				
			}
		}
		LOGGER.debug("*******************************************************");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		if(modelAndView!=null) {
			LOGGER.debug("*******************************************************");
			modelAndView.addObject("cti_call_api_url", propertiesService.getString("cti.call.api.url").trim());
			modelAndView.addObject("cti_rec_api_url", propertiesService.getString("cti.rec.api.url").trim());
			modelAndView.addObject("cti_rec_file_url", propertiesService.getString("cti.rec.file.url").trim());
			LOGGER.debug("*******************************************************");
		}		
	}
	
}
