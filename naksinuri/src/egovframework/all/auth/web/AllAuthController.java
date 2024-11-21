package egovframework.all.auth.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.all.auth.utils.KCBCertVO;
import egovframework.all.auth.utils.KakaopayCertVO;
import egovframework.all.login.service.AuthipinsmsVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.EgovStringUtil;


/**
 * @Class Name : AllAuthController.java
 * @Description : AllAuthController Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019.12.08  김정하         최초생성
 *
 * @author 개발팀
 * @since 2019. 12. 08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by jhkim All right reserved.
 */

@Controller
public class AllAuthController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AllAuthController.class);
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	//ipin 인증
  	@RequestMapping(value = "/all/auth/ipinresult.do")
  	public String all_auth_ipinresult(@ModelAttribute("authipinsmsVO") AuthipinsmsVO authipinsmsVO,HttpServletRequest request, ModelMap model) throws Exception {

  		return "all/auth/ipinresult";
  		
  	}
  	//KMC 모바일 인증
  	@RequestMapping(value = "/all/auth/idtfyresult.do")
  	public String all_auth_idtfyresult(@ModelAttribute("authipinsmsVO") AuthipinsmsVO authipinsmsVO, HttpServletRequest request, ModelMap model) throws Exception {
  		
  		return "all/auth/idtfyresult";
  		
  	}
    	
  	//KCB OKCert 휴대폰 인증
  	@RequestMapping(value = "/all/auth/kcbOkCertResult.do")
  	public String all_auth_kcbokcert_result(HttpServletRequest request, ModelMap model) throws Exception {
  		model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid").toString());
  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath").toString());
  		return "all/auth/kcb_okcert_result";
  		
  	}
  	  	
  	//KCB 카드 본인확인 요청
  	@RequestMapping(value = "/all/auth/kcbCardCertRequest.do")
  	public @ResponseBody String all_auth_kcbcardcert_request(@ModelAttribute("KCBCertVO") KCBCertVO kCBCertVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
  		boolean isOk = false;  		
  		JSONObject data = new JSONObject();  		
  		EgovStringUtil mEgovStringUtil = new EgovStringUtil();  
  		if(EgovStringUtil.isEmpty(kCBCertVO.getMberNm())) {
  			request.getSession().setAttribute("certRequestKCBCardTempDataName", "");
  			data.put("error", "1");
			data.put("msg", "이름을 입력해주세요.");
  		} else {
  			request.getSession().setAttribute("certRequestKCBCardTempDataName", mEgovStringUtil.getHtmlStrCnvr(kCBCertVO.getMberNm().trim()));
  			if(EgovStringUtil.isEmpty(kCBCertVO.getMberBrdt())) {
  	  			request.getSession().setAttribute("certRequestKCBCardTempDataBirthDay", "");
  	  			data.put("error", "1");
  				data.put("msg", "생년월일을 입력해주세요.");
  	  		} else {
  	  			request.getSession().setAttribute("certRequestKCBCardTempDataBirthDay", mEgovStringUtil.getHtmlStrCnvr(kCBCertVO.getMberBrdt().trim()));
	  	  		if(EgovStringUtil.isEmpty(kCBCertVO.getMberCttpc1()) || EgovStringUtil.isEmpty(kCBCertVO.getMberCttpc2())) {
	  	  			request.getSession().setAttribute("certRequestKCBCardTempDataPhoneNo", "");
	  	  			data.put("error", "1");
	  				data.put("msg", "휴대폰번호을 입력해주세요.");
	  	  		} else {
	  	  			request.getSession().setAttribute("certRequestKCBCardTempDataPhoneNo", mEgovStringUtil.getHtmlStrCnvr(kCBCertVO.getMberCttpc1().trim())+mEgovStringUtil.getHtmlStrCnvr(kCBCertVO.getMberCttpc2().trim()));
	  	  			isOk = true;
	  	  		} 
  	  		}
  		}	
  		  		
  		if(isOk) {
  			data.put("error", "0");
			data.put("msg", "정상");
  		} 
  		
  		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;  		
  	}
  	
  	//KCB 카드 본인확인 결과
  	@RequestMapping(value = "/all/auth/kcbCardCertResult.do")
  	public String all_auth_kcbcardcert_result(HttpServletRequest request, ModelMap model) throws Exception {
  		model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid").toString());
  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath").toString());
  		return "all/auth/kcb_cardcert_result";
  		
  	}
  	
  	
  	//KCB 아이핀 본인확인 요청
  	@RequestMapping(value = "/all/auth/kcbIpinCertRequest.do")
  	public @ResponseBody String all_auth_kcbipincert_request(@ModelAttribute("KCBCertVO") KCBCertVO kCBCertVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
  		boolean isOk = false;  		
  		JSONObject data = new JSONObject();  		
  		EgovStringUtil mEgovStringUtil = new EgovStringUtil();  
  		if(EgovStringUtil.isEmpty(kCBCertVO.getMberNm())) {
  			request.getSession().setAttribute("certRequestKCBIpinTempDataName", "");
  			data.put("error", "1");
			data.put("msg", "이름을 입력해주세요.");
  		} else {
  			request.getSession().setAttribute("certRequestKCBIpinTempDataName", mEgovStringUtil.getHtmlStrCnvr(kCBCertVO.getMberNm().trim()));
  			if(EgovStringUtil.isEmpty(kCBCertVO.getMberBrdt())) {
  	  			request.getSession().setAttribute("certRequestKCBIpinTempDataBirthDay", "");
  	  			data.put("error", "1");
  				data.put("msg", "생년월일을 입력해주세요.");
  	  		} else {
  	  			request.getSession().setAttribute("certRequestKCBIpinTempDataBirthDay", mEgovStringUtil.getHtmlStrCnvr(kCBCertVO.getMberBrdt().trim()));
	  	  		if(EgovStringUtil.isEmpty(kCBCertVO.getMberCttpc1()) || EgovStringUtil.isEmpty(kCBCertVO.getMberCttpc2())) {
	  	  			request.getSession().setAttribute("certRequestKCBIpinTempDataPhoneNo", "");
	  	  			data.put("error", "1");
	  				data.put("msg", "휴대폰번호을 입력해주세요.");
	  	  		} else {
	  	  			request.getSession().setAttribute("certRequestKCBIpinTempDataPhoneNo", mEgovStringUtil.getHtmlStrCnvr(kCBCertVO.getMberCttpc1().trim())+mEgovStringUtil.getHtmlStrCnvr(kCBCertVO.getMberCttpc2().trim()));
	  	  			isOk = true;
	  	  		} 
  	  		}
  		}	
  		  		
  		if(isOk) {
  			data.put("error", "0");
			data.put("msg", "정상");
  		} 
  		
  		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;  		
  	}
  	
  	//KCB 아이핀 본인확인 결과
  	@RequestMapping(value = "/all/auth/kcbIpinCertResult.do")
  	public String all_auth_kcbipincert_result(HttpServletRequest request, ModelMap model) throws Exception {
  		model.addAttribute("kcbokcert_cpid",propertiesService.getString("KcbOkCert.cpid").toString());
  		model.addAttribute("kcbokcert_licensepath",propertiesService.getString("KcbOkCert.licensePath").toString());
  		return "all/auth/kcb_ipincert_result";
  		
  	}
  
  	//카카오페이 본인확인 요청
  	@RequestMapping(value = "/all/auth/kakaopayCertRequest.do")
  	public String all_auth_kakaopaycert_request(@ModelAttribute("KakaopayCertVO") KakaopayCertVO kakaopayCertVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

  		model.addAttribute("kakaopay_link_id", propertiesService.getString("kakaopay.link.id"));
  		model.addAttribute("kakaopay_secret_key", propertiesService.getString("kakaopay.secret.key"));
  		model.addAttribute("kakaopay_client_code", propertiesService.getString("kakaopay.client.code"));
  		
  		
  		EgovStringUtil mEgovStringUtil = new EgovStringUtil();  	
  		if(EgovStringUtil.isEmpty(kakaopayCertVO.getMberBrdt())) {
  			model.addAttribute("mberBrdt", "");
  		} else {
  			model.addAttribute("mberBrdt", mEgovStringUtil.getHtmlStrCnvr(kakaopayCertVO.getMberBrdt().trim()));
  		}
  		if(EgovStringUtil.isEmpty(kakaopayCertVO.getMberCttpc1()) && EgovStringUtil.isEmpty(kakaopayCertVO.getMberCttpc2())) {
  			model.addAttribute("mberCttpc", "");
  		} else {
  			model.addAttribute("mberCttpc", mEgovStringUtil.getHtmlStrCnvr(kakaopayCertVO.getMberCttpc1().trim())+mEgovStringUtil.getHtmlStrCnvr(kakaopayCertVO.getMberCttpc2().trim()));
  		}
  		if(EgovStringUtil.isEmpty(kakaopayCertVO.getMberNm())) {
  			model.addAttribute("mberNm", "");
  		} else {
  			model.addAttribute("mberNm", mEgovStringUtil.getHtmlStrCnvr(kakaopayCertVO.getMberNm().trim()));
  		}

  		return "all/auth/kakaopay_cert_request";
  	}
  	
  	//카카오페이 본인확인 결과 
  	@RequestMapping(value = "/all/auth/kakaopayCertResult.do")
  	public String all_auth_kakaopaycert_result(@ModelAttribute("KakaopayCertVO") KakaopayCertVO kakaopayCertVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

  		model.addAttribute("kakaopay_link_id", propertiesService.getString("kakaopay.link.id"));
  		model.addAttribute("kakaopay_secret_key", propertiesService.getString("kakaopay.secret.key"));
  		model.addAttribute("kakaopay_client_code", propertiesService.getString("kakaopay.client.code"));
  		System.out.println("kakaopayCertVO.getReceiptID():"+kakaopayCertVO.getReceiptID());
  		EgovStringUtil mEgovStringUtil = new EgovStringUtil();  		
		model.addAttribute("receiptID", mEgovStringUtil.getHtmlStrCnvr(kakaopayCertVO.getReceiptID()));
		
		return "all/auth/kakaopay_cert_result";
  	}
  	
  	@RequestMapping(value = "/all/auth/kakaopayCertVerify.do")
  	public String all_auth_kakaopaycert_verify(@ModelAttribute("KakaopayCertVO") KakaopayCertVO kakaopayCertVO, 
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

  		model.addAttribute("kakaopay_link_id", propertiesService.getString("kakaopay.link.id"));
  		model.addAttribute("kakaopay_secret_key", propertiesService.getString("kakaopay.secret.key"));
  		model.addAttribute("kakaopay_client_code", propertiesService.getString("kakaopay.client.code"));
  		  		
  		EgovStringUtil mEgovStringUtil = new EgovStringUtil();  		
		model.addAttribute("receiptID", mEgovStringUtil.getHtmlStrCnvr(kakaopayCertVO.getReceiptID()));
		
		return "all/auth/kakaopay_cert_verify";
  	}
  	
}


