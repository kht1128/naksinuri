package egovframework.seadm.main.web;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;

@Controller
public class MainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
	/** MainService */
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	//공통 카테고리 요청 ------------------------------------------------
	@RequestMapping(value = "/util/category.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_util_category_codeset(@ModelAttribute("codeSetVO") CodeSetVO codeSetVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		JSONObject data = new JSONObject();
		if(codeSetVO==null || codeSetVO.getCD_SN()==null || codeSetVO.getCD_SN().length()==0) {
			data.put("error", "0");
			data.put("msg", "올바르지 않은 요청입니다.");
			data.put("rawdata", "{}");
		} else {
			String CD_HIDE = codeSetVO.getHIDE_AT();
			CodeSetVO existCodeSetVO = codeSetService.get_codeset_info(codeSetVO);
			if(existCodeSetVO==null || existCodeSetVO.getCD_SN()==null || existCodeSetVO.getCD_SN().length()==0) {
				data.put("error", "0");
				data.put("msg", "올바르지 않은 요청입니다.");
				data.put("rawdata", "{}");
			} else {
				boolean isList = false;
				CodeSetVO callCodeSetVO = new CodeSetVO();
				if(codeSetVO.getIS_CD_UP().equals("Y")) {
					callCodeSetVO.setCD_ID(existCodeSetVO.getCD_MASTER_ID());
					callCodeSetVO.setHIDE_AT(CD_HIDE);
					isList = true;
				} else if(codeSetVO.getIS_CD_DOWN().equals("Y")) {
					callCodeSetVO.setCD_MASTER_ID(existCodeSetVO.getCD_ID());
					callCodeSetVO.setHIDE_AT(CD_HIDE);
					isList = true;
				} else {
					isList = false;
				}
				try {
					ObjectMapper mapper = new ObjectMapper();
					mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
					mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
					if(isList) {
						List<CodeSetVO> list = codeSetService.get_codeset_list(callCodeSetVO);
						data.put("rawdata", mapper.writeValueAsString(list));
					} else {
						data.put("rawdata", mapper.writeValueAsString(existCodeSetVO));
					}
					data.put("error", "0");
					data.put("msg", "정상적으로 처리 되었습니다.");
				} catch(Exception e) {
					data.put("error", "1");
					data.put("msg", "올바르지 않은 요청입니다.");
				}
			}
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	

	//관리자(CTI) 접근권한없음 ------------------------------------------------ 2018.12.28 추가
	@RequestMapping(value = "/seadm/error/unauth.do")
	public String eduadm_error_unauth(HttpServletRequest request, ModelMap model) throws Exception {
		
		return "seadm/error/unauth";
	}
	
	//관리자 로그인페이지 ------------------------------------------------
	@RequestMapping(value = "/seadm/index.do")
	public String mainIndex(ModelMap model) throws Exception {

		return "redirect:/admin/sosig/notice/list.do";
	}
	
	//관리자 메인페이지 ------------------------------------------------
	@RequestMapping(value = "/seadm/main.do")
	public String main(ModelMap model) throws Exception {
		
		//return "seadm/main";
		return "redirect:/seadm/member/list.do";
	
	}
	
	//관리자 로그아웃 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_logout.do")
	public String admin_logout(ModelMap model) throws Exception {
		
		return "seadm/admin_logout";
	
	}
	
	
	//관리자 코드관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_code.do")
	public String admin_code(ModelMap model) throws Exception {
		
		return "seadm/admin_code";
	}
	//관리자 직급관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_position.do")
	public String admin_position(ModelMap model) throws Exception {
		
		return "seadm/admin_position";
	}

	//관리자 회원유형관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_member_type.do")
	public String admin_member_type(ModelMap model) throws Exception {
		
		return "seadm/admin_member_type";
	}
	//관리자 멘토관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_mento.do")
	public String admin_mento(ModelMap model) throws Exception {
		
		return "seadm/admin_mento";
	}
	//관리자 멘티관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_menti.do")
	public String admin_menti(ModelMap model) throws Exception {
		
		return "seadm/admin_menti";
	}
	//관리자 팝업존관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_popupzone.do")
	public String admin_popupzone(ModelMap model) throws Exception {
		
		return "seadm/admin_popupzone";
	}

	
	//관리자 희망품종 수요조사관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_research.do")
	public String admin_research(ModelMap model) throws Exception {
		
		return "seadm/admin_research";
	}
	//관리자 접솔로그관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_log.do")
	public String admin_log(ModelMap model) throws Exception {
		
		return "seadm/admin_log";
	}
	//관리자 페이지관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_page.do")
	public String admin_page(ModelMap model) throws Exception {
		
		return "seadm/admin_page";
	}
	//관리자 접속통계관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_stats.do")
	public String admin_stats(ModelMap model) throws Exception {
		
		return "seadm/admin_stats";
	}
	//관리자 환경설정관리 ------------------------------------------------
	@RequestMapping(value = "/seadm/admin_config.do")
	public String admin_config(ModelMap model) throws Exception {
		
		return "seadm/admin_config";
	}
	
}


