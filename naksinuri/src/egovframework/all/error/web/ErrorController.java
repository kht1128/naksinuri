package egovframework.all.error.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.all.error.service.ErrorService;
import egovframework.all.error.service.ErrorVO;
import egovframework.all.login.service.LoginVO;
import egovframework.utils.PublicUtils;


/**
 * @Class Name : ErrorController.java
 * @Description : ErrorController Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019.01.23  김정하         최초생성
 *
 * @author 개발팀
 * @since 2019. 01.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by jhkim All right reserved.
 */

@Controller
public class ErrorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
	
	/** ErrorService */
	@Resource(name = "errorService")
	private ErrorService errorService;
	
	//에러 기록
	@RequestMapping(value = "/common/error.do")
	public String mainIndex(@ModelAttribute("errorVO") ErrorVO errorVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		LOGGER.debug("에러 컨트롤 - ");
		String ERR_STAT_CD = "";
		String ERR_EXP_TYPE = "";
		String ERR_EXP_MSG = "";
		String ERR_REQ_URI = "";
		String ERR_EXP = "";
		String ERR_SERVLET_NM = "";
		String MBR_ID = "";
		String ERR_TYPE = "system";
		try {
			ERR_STAT_CD = request.getAttribute("javax.servlet.error.status_code").toString();
		} catch(Exception e) {
			ERR_STAT_CD = "none";
		}
		try {
			ERR_EXP_TYPE = request.getAttribute("javax.servlet.error.exception_type").toString();
		} catch(Exception e) {
			ERR_EXP_TYPE = "none";
		}
		try {
			ERR_EXP_MSG = request.getAttribute("javax.servlet.error.message").toString();
		} catch(Exception e) {
			ERR_EXP_MSG = "none";
		}
		try {
			ERR_REQ_URI = request.getAttribute("javax.servlet.error.request_uri").toString();
			String fileNameWithoutExtn = ERR_REQ_URI.substring(ERR_REQ_URI.lastIndexOf('.'), ERR_REQ_URI.length()).toLowerCase();
			if(fileNameWithoutExtn.equals(".png") || fileNameWithoutExtn.equals(".jpg") || fileNameWithoutExtn.equals(".gif") || fileNameWithoutExtn.equals(".jpeg") 
			|| fileNameWithoutExtn.equals(".bmp")
			//video
			|| fileNameWithoutExtn.equals(".mp3") || fileNameWithoutExtn.equals(".avi") || fileNameWithoutExtn.equals(".mp4")
			//file
			|| fileNameWithoutExtn.equals(".eot") || fileNameWithoutExtn.equals(".js") || fileNameWithoutExtn.equals(".css") || fileNameWithoutExtn.equals(".less")  
			) {
				ERR_TYPE = "resource";
			} else if(fileNameWithoutExtn.equals(".pdf") || fileNameWithoutExtn.equals(".xml") || fileNameWithoutExtn.equals(".json")
			|| fileNameWithoutExtn.equals(".bak") || fileNameWithoutExtn.equals(".swp") || fileNameWithoutExtn.equals(".hwp")
			){
				ERR_TYPE = "file";
			} 
		} catch(Exception e) {
			ERR_REQ_URI = "none";
		}
		try {
			ERR_EXP = request.getAttribute("javax.servlet.error.exception").toString();			
		} catch(Exception e) {
			ERR_EXP = "none";
		}
		try {
			ERR_SERVLET_NM = request.getAttribute("javax.servlet.error.servlet_name").toString();			
		} catch(Exception e) {
			ERR_SERVLET_NM = "none";
		}
		try {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			MBR_ID = loginVO.getMBR_ID();
		} catch(Exception e) {
			MBR_ID = "";
		}
		LOGGER.debug("ERR_STAT_CD : " + ERR_STAT_CD);
		LOGGER.debug("ERR_EXP_TYPE : " + ERR_EXP_TYPE);
		LOGGER.debug("ERR_EXP_MSG : " + ERR_EXP_MSG);
		LOGGER.debug("ERR_REQ_URI : " + ERR_REQ_URI);
		LOGGER.debug("ERR_EXP : " + ERR_EXP);
		LOGGER.debug("ERR_SERVLET_NM : " + ERR_SERVLET_NM);
		LOGGER.debug("MBR_ID : " + MBR_ID);
		errorVO.setERR_STAT_CD(ERR_STAT_CD);
		errorVO.setERR_EXP_TYPE(ERR_EXP_TYPE);
		errorVO.setERR_EXP_MSG(ERR_EXP_MSG);
		errorVO.setERR_REQ_URI(ERR_REQ_URI);
		errorVO.setERR_EXP(ERR_EXP.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
		errorVO.setERR_SERVLET_NM(ERR_SERVLET_NM);
		errorVO.setERR_IP(PublicUtils.getClientIpAddr(request));
		errorVO.setMBR_ID(MBR_ID);
		errorVO.setERR_TYPE(ERR_TYPE);
		if(ERR_REQ_URI.contains(".map")) {
			LOGGER.debug("해당 URL은 에러 로그 기록을 하지 않음.");
		} else {
			LOGGER.debug("에러 로그 기록");
			errorService.set_error_reg(errorVO);			
		}
		return "cmmn/error";
	}
	
}


