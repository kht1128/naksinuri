package egovframework.all.codeset.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.cti.main.service.CtiBoardVO;
import egovframework.cti.main.service.CtiMainService;
import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.utils.PublicUtils;

@Controller
public class CodeSetController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeSetController.class);

	/** LogRecordService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	/** PopupService */
	@Resource(name="codeSetService")
	private CodeSetService codeSetService;
	
	/** ManualService*/
	@Resource(name = "ctiMainService")
	private CtiMainService ctiMainService;
	
	//코드 값 요청 ------------------------------------------------
	@RequestMapping(value = "/all/code.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_util_category(@ModelAttribute("CodeSetVO") CodeSetVO mCodeSetVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
  		List<CodeSetVO> list = codeSetService.get_codeset_list(mCodeSetVO);
		JSONObject data = new JSONObject();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			data.put("rawdata", mapper.writeValueAsString(list));
			data.put("error", "0");
			data.put("msg", "정상적으로 처리 되었습니다.");
		} catch(Exception e) {
			data.put("error", "1");
			data.put("msg", "올바르지 않은 요청입니다.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	
	//카테고리 관리 매니저 ------------------------------------------------
	@RequestMapping(value = "/cti/category/manager.do")
	public String ajax_seadm_board_category_manager (@ModelAttribute("CodeSetVO") CodeSetVO mCodeSetVO, 
		@ModelAttribute("ctiBoardVO") CtiBoardVO ctiBoardVO, @RequestParam(value="CUSTOM_UNIQ_KEY", required=false) 
		String CUSTOM_UNIQ_KEY, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		List<CodeSetVO> list_category = null;
		if(mCodeSetVO!=null && mCodeSetVO.getCD_ID()!=null && mCodeSetVO.getCD_ID().length()!=0) {
			
			CodeSetVO codeSetVO = new CodeSetVO();
			codeSetVO.setCD_MASTER_ID(mCodeSetVO.getCD_ID());
			list_category = codeSetService.get_codeset_list(codeSetVO);
			
			model.addAttribute("list_category",list_category);
			return "cti/category/ajax_category_manager";
			
		} else {
			
			CodeSetVO codeSetVO = new CodeSetVO();
			codeSetVO.setCD_MASTER_ID("CID00011");
			list_category = codeSetService.get_codeset_list(codeSetVO);
			
			model.addAttribute("list_category",list_category);
			
			{//메뉴얼 상담분류 1단계
				CodeSetVO mnCodeSetVO = new CodeSetVO();
		  		mnCodeSetVO.setCD_MASTER_ID("CID00011");
		  		mnCodeSetVO.setHIDE_AT("N");
		  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mnCodeSetVO);
		  		model.addAttribute("list_category_gubun_1",list_mbr_cd); 		
			}
			{//메뉴얼 상담분류 2단계 코드 조회
				if(ctiBoardVO!=null && ctiBoardVO.getBD_CATEGORY_CD()!=null && ctiBoardVO.getBD_CATEGORY_CD().length()!=0) {
					CodeSetVO mnCodeSetVO = new CodeSetVO();
			  		mnCodeSetVO.setCD_MASTER_ID(ctiBoardVO.getBD_CATEGORY_CD());
			  		mnCodeSetVO.setHIDE_AT("N");
			  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mnCodeSetVO);
			  		model.addAttribute("list_category_gubun_2",list_mbr_cd);
				}			
			}
			
			ctiBoardVO.setBD_LOCK_ST("N");
			ctiBoardVO.setBD_ID("board017");
			ctiBoardVO.setNotUsedPagination(true);
			List<CtiBoardVO> list = ctiMainService.get_cti_manual_list(ctiBoardVO);
			
			model.addAttribute("list",list);
			model.addAttribute("BD_CATEGORY_CD", ctiBoardVO.getBD_CATEGORY_CD());
			model.addAttribute("BD_CATEGORY_CD2", ctiBoardVO.getBD_CATEGORY_CD2());
			model.addAttribute("searchKeyword",ctiBoardVO.getSearchKeyword());
			model.addAttribute("CUSTOM_UNIQ_KEY", CUSTOM_UNIQ_KEY);		
			
			return "cti/category/category_manager";
		}
	}
	
	@RequestMapping(value = "/cti/category/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_category_write(@ModelAttribute("CodeSetVO") CodeSetVO codeSetVO, HttpServletRequest request, ModelMap model) throws Exception {	
		
		List<CodeSetVO> category = codeSetService.get_codeset_list(null);
		ModelAndView mModelAndView = new ModelAndView();		
		mModelAndView.setViewName("cti/category/category_write");		
		mModelAndView.addObject("CD_MASTER_ID",codeSetVO.getCD_MASTER_ID());
		mModelAndView.addObject("category",category);
		return mModelAndView;
	}
	
	@RequestMapping(value="/cti/category/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_seadm_board_category_write_act(@ModelAttribute("CodeSetVO") CodeSetVO codeSetVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[카테고리 추가]");
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			
			int last_rank = 0;
			String CD_MASTER_ID = "";
			String NEW_CD_ID = "";
			String CD_LEVEL = "";
			
			if(codeSetVO!=null && codeSetVO.getCD_MASTER_ID()!=null && codeSetVO.getCD_MASTER_ID().length()!=0) { //상담분류2
			
				CodeSetVO chkCodeSetVO = new CodeSetVO();
				chkCodeSetVO.setCD_MASTER_ID(codeSetVO.getCD_MASTER_ID());
				List<CodeSetVO> list_category = codeSetService.get_codeset_list(chkCodeSetVO);
				
				last_rank = list_category.size()+1;
				CD_MASTER_ID = codeSetVO.getCD_MASTER_ID();
				
				String subkey = CD_MASTER_ID.substring(CD_MASTER_ID.length() - 1,CD_MASTER_ID.length());
				
				NEW_CD_ID = "IVR_"+subkey+String.format("%03d", last_rank);
				
				CD_LEVEL = "3";
				
			} else { //상담분류1
				
				CodeSetVO chkCodeSetVO = new CodeSetVO();
				chkCodeSetVO.setCD_MASTER_ID("CID00011");
				List<CodeSetVO> list_category = codeSetService.get_codeset_list(chkCodeSetVO);
				
				last_rank =  list_category.size()+1;
				CD_MASTER_ID = "CID00011";
				NEW_CD_ID = "IMNUFIPA_"+String.format("%03d", last_rank);
				
				CD_LEVEL = "2";
			
			}			
			
			CodeSetVO insertCodeSetVO = new CodeSetVO();
			insertCodeSetVO.setREG_MBR_ID(loginVO.getMBR_ID());
			insertCodeSetVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			insertCodeSetVO.setCD_NM(codeSetVO.getCD_NM());
			insertCodeSetVO.setCD_ID(NEW_CD_ID);
			insertCodeSetVO.setCD_MASTER_ID(CD_MASTER_ID);
			insertCodeSetVO.setCD_ORD_NO(String.valueOf(last_rank));
			insertCodeSetVO.setCD_LEVEL(CD_LEVEL);
			codeSetService.set_codeset_insert(insertCodeSetVO);
			
			data.put("error", "0");
			data.put("msg", "카테고리가 생성되었습니다.");
						
		} catch(Exception e) {
			
			e.printStackTrace();
			
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(codeSetVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}			
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}	
	
	@RequestMapping(value="/cti/category/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_seadm_board_category_modify_act(@ModelAttribute("CodeSetVO") CodeSetVO codeSetVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[카테고리 변경]");
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
				CodeSetVO updateCodeSetVO = new CodeSetVO();
				if(!codeSetVO.getCD_ID().equals(codeSetVO.getCD_ID_CHG())){
					updateCodeSetVO.setCD_ID_CHG(codeSetVO.getCD_ID());																																																																																			
					updateCodeSetVO.setCD_ID(codeSetVO.getCD_ID_CHG());		
				} else{
					updateCodeSetVO.setCD_ID_CHG(codeSetVO.getCD_ID());																																								
					updateCodeSetVO.setCD_ID(codeSetVO.getCD_ID());																																								
				}
				updateCodeSetVO.setCD_MASTER_ID(codeSetVO.getCD_MASTER_ID());	
				updateCodeSetVO.setREG_MBR_ID(loginVO.getMBR_ID());
				updateCodeSetVO.setUPD_MBR_ID(loginVO.getMBR_ID());									
				updateCodeSetVO.setCD_NM(codeSetVO.getCD_NM());
				updateCodeSetVO.setCD_ORD_NO(String.valueOf(codeSetVO.getCD_ORD_NO()));
				
				codeSetService.set_codeset_mod(updateCodeSetVO);		
				
				if(codeSetVO.getCD_GB().equals("HCALL_GUBUN_1")){
					CodeSetVO changeCodeSetVO = new CodeSetVO();
					changeCodeSetVO.setCD_MASTER_ID(updateCodeSetVO.getCD_ID());//찾을값
					changeCodeSetVO.setCD_MASTER_ID_CHG(updateCodeSetVO.getCD_ID_CHG());//변경값
					codeSetService.set_codeset_mod_for_all_master(changeCodeSetVO);
				}
				
			data.put("error", "0");
			data.put("msg", "카테고리가 변경되었습니다.");
						
		} catch(Exception e) {
			
			e.printStackTrace();
			
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(codeSetVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}			
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}	
	
	@RequestMapping(value="/cti/category/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_seadm_board_category_delete_act(@ModelAttribute("CodeSetVO") CodeSetVO codeSetVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[카테고리 삭제]");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
	
		//검증
		CodeSetVO chkCodeSetVO = codeSetService.get_codeset_info(codeSetVO);
		if(chkCodeSetVO == null || chkCodeSetVO.getCD_ID() == null || chkCodeSetVO.getCD_ID().length() == 0) {
			log_msg.append("[존재하지않음]");
			log_dscrp.append("][처리실패:존재하지않는정보]");
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");
		} else {
			try {
				
				CodeSetVO deleteCodeSetVO = new CodeSetVO();
				deleteCodeSetVO.setCD_ID(codeSetVO.getCD_ID());
				deleteCodeSetVO.setCD_MASTER_ID(codeSetVO.getCD_MASTER_ID());					
				codeSetService.set_codeset_delete(deleteCodeSetVO);
			
				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");
			} catch(Exception e) {
				log_msg.append("[에러발생("+e.toString()+")]");
				log_dscrp.append("][처리실패]");
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkCodeSetVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	

	@RequestMapping(value="/cti/category/menual_write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_menual_write_act(@ModelAttribute("ctiBoardVO") CtiBoardVO ctiBoardVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[메뉴얼 추가]");
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			
			CtiBoardVO insertCtiBoardVO = new CtiBoardVO();
			insertCtiBoardVO.setBD_MEMBER_ID(loginVO.getMBR_ID());		
			insertCtiBoardVO.setBD_ID("board017");				
			insertCtiBoardVO.setBD_TITLE(ctiBoardVO.getBD_TITLE());
			insertCtiBoardVO.setBD_CONT(ctiBoardVO.getBD_CONT());
			insertCtiBoardVO.setBD_RANK(ctiBoardVO.getBD_RANK());
			insertCtiBoardVO.setBD_CATEGORY_CD(ctiBoardVO.getBD_CATEGORY_CD());
			insertCtiBoardVO.setBD_CATEGORY_CD2(ctiBoardVO.getBD_CATEGORY_CD2());
			ctiMainService.set_cti_manual_insert(insertCtiBoardVO);
			
			data.put("error", "0");
			data.put("msg", "메뉴얼이 생성되었습니다.");
						
		} catch(Exception e) {
			
			e.printStackTrace();
			
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(ctiBoardVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}			
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}	
	
	@RequestMapping(value="/cti/category/menual_modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_menual_modify_act(@ModelAttribute("ctiBoardVO") CtiBoardVO ctiBoardVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[메뉴얼 변경]");
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			CtiBoardVO updateCtiBoardVO = new CtiBoardVO();
				updateCtiBoardVO.setBD_ID("board017");
				updateCtiBoardVO.setBD_SN(ctiBoardVO.getBD_SN());
				updateCtiBoardVO.setBD_TITLE(ctiBoardVO.getBD_TITLE());
				updateCtiBoardVO.setBD_CONT(ctiBoardVO.getBD_CONT());
				updateCtiBoardVO.setBD_RANK(ctiBoardVO.getBD_RANK());				
				updateCtiBoardVO.setBD_CATEGORY_CD(ctiBoardVO.getBD_CATEGORY_CD());
				updateCtiBoardVO.setBD_CATEGORY_CD2(ctiBoardVO.getBD_CATEGORY_CD2());
				ctiMainService.set_cti_menual_mod(updateCtiBoardVO);		
				
			data.put("error", "0");
			data.put("msg", "메뉴얼이 변경되었습니다.");
						
		} catch(Exception e) {
			
			e.printStackTrace();
			
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(ctiBoardVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}			
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}	
	
	@RequestMapping(value="/cti/category/menual_delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_menual_delete_act(@ModelAttribute("ctiBoardVO") CtiBoardVO ctiBoardVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[메뉴얼 삭제]");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
	
			try {
				
				CtiBoardVO deleteCtiBoardVO = new CtiBoardVO();
				deleteCtiBoardVO.setBD_ID("board017");
				deleteCtiBoardVO.setBD_SN(ctiBoardVO.getBD_SN());				
				ctiMainService.set_cti_menual_delete(deleteCtiBoardVO);
			
				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");
			} catch(Exception e) {
				log_msg.append("[에러발생("+e.toString()+")]");
				log_dscrp.append("][처리실패]");
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(ctiBoardVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
		
	
}


