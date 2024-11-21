package egovframework.naksinuri_original.let.naksinuri.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.educenter.web.EduCenterMainController;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngUtil;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.PublicUtils;

@Controller
public class NaksinuriPolicyController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterMainController.class);
	
	@Resource(name = "NaksinuriOriginalEgovFileMngService")
	private NaksinuriOriginalEgovFileMngService fileMngService;

    @Resource(name = "NaksinuriOriginalEgovFileMngUtil")
	private NaksinuriOriginalEgovFileMngUtil fileUtil;
    
    @Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	@Resource(name="NaksinuriPolicyService")
	private NaksinuriPolicyService service;
	
	@Resource(name="NaksinuriService")
	private NaksinuriService naksinuriService;

	@Autowired 
	private HttpServletRequest request;
	
//	


	
	@RequestMapping("/policy/customer_sound/insert_data.do")
	public String insert_voc(final MultipartHttpServletRequest multiRequest, 
			SessionStatus status, @ModelAttribute("policyVO") NaksinuriPolicyVO policyVO,
			BindingResult bindingResult, ModelMap model  ) throws Exception{
		
		VerifyRecaptcha.setSecretKey("6LflyDIUAAAAAB-PMisxT_D6bmIKkzyKVr_Qrd0P"); //secretKey 세팅
		String gRecaptchaResponse = request.getParameter("recaptcha"); //recapcha 파라미터 가져오기
			
	        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse); //리캡챠 인증 true,false 설공 실패 리턴
			String result = "fail";
	        if(verify){
	        	result = "success";
	        }
		
		
		String atchFileId ="";
		List<NaksinuriOriginalFileVO> result1 = null;
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()){
			result1 = fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs_naksinuri_original(result1);
		}
		
		LoginVO loginVO = (LoginVO) multiRequest.getSession().getAttribute("LoginVO");
		
		if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
			policyVO.setVoc_pwd(EgovFileScrty.encryptPassword(policyVO.getVoc_pwd(), loginVO.getMBR_ID()));
			policyVO.setVoc_name(loginVO.getMBR_NM());
			policyVO.setVoc_is_mber_ntt("Y");
			policyVO.setVoc_mber_id(loginVO.getMBR_ID());
		} else {
			policyVO.setVoc_pwd(EgovFileScrty.encryptPassword(policyVO.getVoc_pwd(), policyVO.getVoc_name()));
			policyVO.setVoc_is_mber_ntt("N");
		}
		
		policyVO.setVoc_atch_file(atchFileId);
		
		service.insertVOC(policyVO);
		
		return  "redirect:/policy/customer_sound_list.do";	
	}
	
	// 본인글 확인 
	@RequestMapping(value = "/policy/customer_sound/check_voc_pwd.do", method = RequestMethod.POST)
	public ModelAndView check_voc_pwd(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {
		JSONObject dataObj = new JSONObject();
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("naksinuri_original/naksinuri/policy/view_modal");
				
		NaksinuriPolicyVO info = naksinuriService.select_voc_view(policyVO);
		System.out.println(" ::::; >> " + info.getVoc_is_mber_ntt());
		model.addAttribute("info", info);
		
		LOGGER.debug(dataObj.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(dataObj);
		return mModelAndView;
	}
	
	@RequestMapping(value = "/policy/customer_sound/check_voc_pwd_act.do", method = RequestMethod.POST)
	public @ResponseBody String check_voc_pwd_act(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject dataObj = new JSONObject();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		String voc_isMberNtt = policyVO.getVoc_is_mber_ntt();
		NaksinuriPolicyVO info = naksinuriService.select_voc_view(policyVO);
		String input_pwd = "";
		
		if(voc_isMberNtt.equals("Y")){
			// 회원이 작성한 글.
			if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
				// 회원 -> 회원이 쓴 글을 볼 경우.
				if(loginVO.getMBR_ID().equals(info.getVoc_mber_id())){
					// 내가 쓴 글.
					input_pwd = EgovFileScrty.encryptPassword(policyVO.getVoc_pwd(), loginVO.getMBR_ID());
					System.out.println(" ::::; >> " + input_pwd);
					if(input_pwd.equals(info.getVoc_pwd())){
						// 성공
						dataObj.put("error", "0");
						if(mPublicUtils.isMobileDevice(request)){
							dataObj.put("msg", "인증성공(모바일)");
						}
						else {
							dataObj.put("msg", "인증성공(웹)");
						}
						dataObj.put("returnUrl", "/policy/customer_sound_view.do");
					} else {
						// ㅂㅁ번호 오류
						dataObj.put("error", "1");
						dataObj.put("msg", "비밀번호가 틀렸습니다.");
					}
				} else {
					// 내가 쓴 글 X
					dataObj.put("error", "1");
					dataObj.put("msg", "회원님이 작성한 글이 아닙니다.");
				}
			} else {
				// 비회원 -> 회원이 쓴 글을 볼 경우 :: 불가
				dataObj.put("error", "1");
				dataObj.put("msg", "해당 게시물을 열람할 수 없습니다.11");
			}
		} else {
			// 비회원이 작성한 글.
			if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
				// 회원 -> 비회원이 쓴 글을 볼 경우 :: 불가
				dataObj.put("error", "1");
				dataObj.put("msg", "해당 게시물을 열람할 수 없습니다.22");
			} else {
				input_pwd = EgovFileScrty.encryptPassword(policyVO.getVoc_pwd(), info.getVoc_name());
				if(input_pwd.equals(info.getVoc_pwd())){
					// 성공
					dataObj.put("error", "0");
					if(mPublicUtils.isMobileDevice(request)){
						dataObj.put("msg", "인증성공(모바일)");
					}
					else {
						dataObj.put("msg", "인증성공(웹)");
					}
					dataObj.put("returnUrl", "/policy/customer_sound_view.do");
				} else {
					// ㅂㅁ번호 오류
					dataObj.put("error", "1");
					dataObj.put("msg", "비밀번호가 틀렸습니다.");
				}
			}
		}
		LOGGER.debug(dataObj.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(dataObj);
		return null;
	}
	
	@RequestMapping(value = "/policy/customer_sound/view_modal.do", method = RequestMethod.POST)
	public ModelAndView check_voc_pwd_modal(@ModelAttribute("policyVO") NaksinuriPolicyVO policyVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("naksinuri_original/naksinuri/policy/view_modal");
		
		return mModelAndView;
	}

	@RequestMapping("/policy/customer_sound/m/insert_data.do")
	public String insert_voc_mobile(final MultipartHttpServletRequest multiRequest, 
			SessionStatus status, @ModelAttribute("policyVO") NaksinuriPolicyVO policyVO,
			BindingResult bindingResult, ModelMap model  ) throws Exception{
		
		VerifyRecaptcha.setSecretKey("6LflyDIUAAAAAB-PMisxT_D6bmIKkzyKVr_Qrd0P"); //secretKey 세팅
		String gRecaptchaResponse = request.getParameter("recaptcha"); //recapcha 파라미터 가져오기
			
	        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse); //리캡챠 인증 true,false 설공 실패 리턴
			String result = "fail";
	        if(verify){
	        	result = "success";
	        }
		
		
		String atchFileId ="";
		List<NaksinuriOriginalFileVO> result1 = null;
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()){
			
			result1 = fileUtil.parseFileInf_naksinuri_original(files, "NAK_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs_naksinuri_original(result1);
		}
		
		LoginVO loginVO = (LoginVO) multiRequest.getSession().getAttribute("LoginVO");

		if(loginVO != null && loginVO.getMBR_ID().length() != 0) {
			policyVO.setVoc_pwd(EgovFileScrty.encryptPassword(policyVO.getVoc_pwd(), loginVO.getMBR_NM()));
			policyVO.setVoc_name(loginVO.getMBR_NM());
		} else {
			policyVO.setVoc_pwd(EgovFileScrty.encryptPassword(policyVO.getVoc_pwd(), policyVO.getVoc_name()));
		}
		
		policyVO.setVoc_atch_file(atchFileId);
		
		service.insertVOC(policyVO);
		
		return  "redirect:/policy/m/customer_sound_list.do";	
	}
	
	
}
