package egovframework.eduadm.category.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduCategoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EduCategoryController.class);
	
	/** EgovLogService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	/** EgovEducenterService */
	@Resource(name = "eduCenterService")
	private EduCenterService eduCenterService;
	
	/** EduCategoryService */
	@Resource(name = "eduCategoryService")
	private EduCategoryService eduCategoryService;
	
	/** EduCurriculumService */
	@Resource(name = "eduCurriculumService")
	private EduCurriculumService eduCurriculumService;
	
	/** EduMyHistoryService */
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;
	
	/** EduTrainingDataService */
	@Resource(name = "eduTrainingDataService")
	private EduTrainingDataService eduTrainingDataService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	

	//관리자(교육센터) 교육카테고리 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/list.do")
	public String edu_category(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		List<EduCategoryVO> list1 = eduCategoryService.get_edu_category_list_1(eduCategoryVO);
		HashMap<String,Object> list2 = new HashMap<String,Object>();
		for(EduCategoryVO item : list1) {
			List<EduCategoryVO> rowlist = eduCategoryService.get_edu_category_list_2(item);
			list2.put(String.valueOf(item.getCAT_SN()),(List<EduCategoryVO>)rowlist);
		}
		
		model.addAttribute("edu_catetory_1",list1);
		model.addAttribute("edu_catetory_2",list2);
		
		return "eduadm/category/list";
	}	
	//관리자(교육센터) 교육카테고리 글쓰기 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_category_write(HttpServletRequest request, ModelMap model) throws Exception {	
		
		List<EduCategoryVO> edu_category_1 = eduCategoryService.get_edu_category_list_1(null);
		ModelAndView mModelAndView = new ModelAndView();		
		mModelAndView.setViewName("eduadm/category/write");
		mModelAndView.addObject("edu_category_1",edu_category_1);
		return mModelAndView;
	}
	//관리자(교육센터) 교육카테고리 글수정 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/modify.do", method = RequestMethod.POST)
	public ModelAndView ajax_category_modify(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, HttpServletRequest request, ModelMap model) throws Exception {	
		
		EduCategoryVO info = eduCategoryService.get_edu_category_info(eduCategoryVO);
		List<EduCategoryVO> edu_category_1 = eduCategoryService.get_edu_category_list_1(null);
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/category/modify");
		mModelAndView.addObject("info",info);
		mModelAndView.addObject("edu_category_1",edu_category_1);
		return mModelAndView;
	}
	//관리자(교육센터) 교육카테고리 상세 글쓰기 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/writeDtl.do", method = RequestMethod.POST)
	//@ResponseBody //데이터값을 전달하는 경우에 사용
	public ModelAndView ajax_category_writeDtl(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, HttpServletRequest request, ModelMap model) throws Exception {	
		
		List<EduCategoryVO> edu_category_1 = eduCategoryService.get_edu_category_list_1(null);
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/category/write_dtl");
		mModelAndView.addObject("CAT_SN",eduCategoryVO.getCAT_SN());
		mModelAndView.addObject("edu_category_1",edu_category_1);
		return mModelAndView;
	}
	//관리자(교육센터) 교육카테고리 상세 글수정 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/modifyDtl.do", method = RequestMethod.POST)
	//@ResponseBody //데이터값을 전달하는 경우에 사용
	public ModelAndView ajax_category_modifyDtl(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, HttpServletRequest request, ModelMap model) throws Exception {	
		
		EduCategoryVO info = eduCategoryService.get_edu_category_dtl_info(eduCategoryVO);
		List<EduCategoryVO> edu_category_1 = eduCategoryService.get_edu_category_list_1(null);
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/category/modify_dtl");
		mModelAndView.addObject("info",info);
		mModelAndView.addObject("edu_category_1",edu_category_1);
		return mModelAndView;
	}
	//관리자(교육센터) 교육카테고리 등록 ------------------------------------------------
	//@ResponseBody 으로 데이터만 던지면 406 에러를 해결하지 못하여 아래와 같이 처리함... 
	@RequestMapping(value="/eduadm/category/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_category_write_act(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-교육카테코리생성-상위]");
		tbl_inf.append("EDU_CAT_TB,");
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			
			eduCategoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCategoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			if(eduCategoryVO.getUSE_ST_CHK().equals("Y")) {
				eduCategoryVO.setUSE_ST("1");
				eduCategoryVO.setDEL_ST("0");
			} else {
				eduCategoryVO.setUSE_ST("0");
			}
			String insertId = eduCategoryService.set_edu_category_reg_1(eduCategoryVO);
			tbl_sn.append(insertId+",");
			log_dscrp.append("[게시물:"+eduCategoryVO.getCAT_NM()+"(일련번호:신규]");
			
			data.put("error", "0");
			data.put("msg", "카테고리가 생성되었습니다.");
						
		} catch(Exception e) {
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
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCategoryVO));
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
	//관리자(교육센터) 교육카테고리 상세 등록 ------------------------------------------------
	@RequestMapping(value="/eduadm/category/writeDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_category_writeDtl_act(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-교육카테코리생성-하위]");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			eduCategoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCategoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			if(eduCategoryVO.getUSE_ST_CHK().equals("Y")) {
				eduCategoryVO.setUSE_ST("1");
				eduCategoryVO.setDEL_ST("0");
			} else {
				eduCategoryVO.setUSE_ST("0");
			}
			String insertId = eduCategoryService.set_edu_category_reg_2(eduCategoryVO);
			log_dscrp.append("[게시물:"+eduCategoryVO.getCAT_DTL_NM()+"(일련번호:신규]");
			tbl_inf.append("EDU_CAT_DTL_TB,");
			tbl_sn.append(insertId+",");
			
			data.put("error", "0");
			data.put("msg", "카테고리가 생성되었습니다.");
		} catch(Exception e) {
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
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCategoryVO));
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
	//관리자(교육센터) 교육카테고리 수정 ------------------------------------------------
	@RequestMapping(value="/eduadm/category/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_category_modify_act(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-교육카테코리수정-상위]");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			
			eduCategoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCategoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			if(eduCategoryVO.getUSE_ST_CHK().equals("Y")) {
				eduCategoryVO.setUSE_ST("1");
				eduCategoryVO.setDEL_ST("0");
			} else {
				eduCategoryVO.setUSE_ST("0");
			}
			eduCategoryService.set_edu_category_mod_1(eduCategoryVO);
			
			log_dscrp.append("[게시물:"+eduCategoryVO.getCAT_NM()+"(일련번호:"+eduCategoryVO.getCAT_SN()+")]");
			tbl_inf.append("EDU_CAT_TB,");
			tbl_sn.append(eduCategoryVO.getCAT_SN()+",");
				
			
			data.put("error", "0");
			data.put("msg", "카테고리가 수정되었습니다.");
		} catch(Exception e) {
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
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCategoryVO));
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
	//관리자(교육센터) 교육카테고리 상세 수정 ------------------------------------------------
	@RequestMapping(value="/eduadm/category/modifyDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_category_modifyDtl_act(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-교육카테코리수정-하위]");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		try {
			
			eduCategoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCategoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			if(eduCategoryVO.getUSE_ST_CHK().equals("Y")) {
				eduCategoryVO.setUSE_ST("1");
				eduCategoryVO.setDEL_ST("0");
			} else {
				eduCategoryVO.setUSE_ST("0");
			}
			eduCategoryService.set_edu_category_mod_2(eduCategoryVO);
			
			log_dscrp.append("[게시물:"+eduCategoryVO.getCAT_DTL_NM()+"(일련번호:"+eduCategoryVO.getCAT_DTL_SN()+")]");
			tbl_inf.append("EDU_CAT_DTL_TB,");
			tbl_sn.append(eduCategoryVO.getCAT_DTL_SN()+",");
			
			data.put("error", "0");
			data.put("msg", "카테고리가 수정되었습니다.");
		} catch(Exception e) {
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
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCategoryVO));
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
	//관리자(교육센터) 교육카테고리 삭제 ------------------------------------------------
	@RequestMapping(value="/eduadm/category/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_category_delete_act(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-교육카테코리삭제-상하위");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
	
		//검증
		EduCategoryVO chkEduCategoryVO = eduCategoryService.get_edu_category_info(eduCategoryVO);
		if(chkEduCategoryVO == null || chkEduCategoryVO.getCAT_SN() == null || chkEduCategoryVO.getCAT_SN().length() == 0) {
			log_msg.append("[존재하지않음]");
			log_dscrp.append("][처리실패:존재하지않는정보]");
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");
		} else {
			try {
								
				String DEL_ST = chkEduCategoryVO.getDEL_ST();
				if(DEL_ST.equals("1")) {
					log_dscrp.append("-실제데이터삭제]");
					
					eduCategoryService.remove_edu_category_1(eduCategoryVO);
					eduCategoryService.remove_edu_category_2(eduCategoryVO);
					
					log_dscrp.append("[게시물:"+eduCategoryVO.getCAT_NM()+"(일련번호:"+eduCategoryVO.getCAT_SN()+")]");
					tbl_inf.append("EDU_CAT_TB,EDU_CAT_DTL_TB");
					tbl_sn.append(eduCategoryVO.getCAT_SN()+",all");
					
				} else {
					log_dscrp.append("-상하위]");
					
					eduCategoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					eduCategoryService.del_edu_category_1(eduCategoryVO);
					eduCategoryService.del_edu_category_2(eduCategoryVO);
				
					log_dscrp.append("[게시물:"+eduCategoryVO.getCAT_NM()+"(일련번호:"+eduCategoryVO.getCAT_SN()+")]");
					tbl_inf.append("EDU_CAT_TB,EDU_CAT_DTL_TB");
					tbl_sn.append(eduCategoryVO.getCAT_SN()+",all");
				}
				
					
				
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
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduCategoryVO));
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
	//관리자(교육센터) 교육카테고리 상세 삭제 ------------------------------------------------
	@RequestMapping(value="/eduadm/category/deleteDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_category_deleteDtl_act(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-교육카테코리삭제-하위-");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		//검증
		EduCategoryVO chkEduCategoryVO = eduCategoryService.get_edu_category_dtl_info(eduCategoryVO);
		if(chkEduCategoryVO == null || chkEduCategoryVO.getCAT_DTL_SN() == null || chkEduCategoryVO.getCAT_DTL_SN().length() == 0) {
			log_msg.append("[존재하지않음]");
			log_dscrp.append("][처리실패:존재하지않는정보]");
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");
		} else {
			try {
				
				
				String DEL_ST = chkEduCategoryVO.getDEL_ST();
				if(DEL_ST.equals("1")) {
					log_dscrp.append("-실제데이터삭제]");					
					eduCategoryService.remove_edu_category_2(eduCategoryVO);
					
					log_dscrp.append("[게시물:"+eduCategoryVO.getCAT_NM()+"(일련번호:"+eduCategoryVO.getCAT_SN()+")]");
					tbl_inf.append("EDU_CAT_DTL_TB");
					tbl_sn.append(eduCategoryVO.getCAT_DTL_SN()+",");					
				} else {
					log_dscrp.append("-하위]");
					eduCategoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					eduCategoryService.del_edu_category_2(eduCategoryVO);
					
					log_dscrp.append("[게시물:"+eduCategoryVO.getCAT_NM()+"(일련번호:"+eduCategoryVO.getCAT_SN()+")]");
					tbl_inf.append("EDU_CAT_DTL_TB");
					tbl_sn.append(eduCategoryVO.getCAT_SN()+",");					
				}				
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
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduCategoryVO));
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
	
	
	
	/** 교육기관 **/
	//관리자(교육센터) 교육기관 카테고리 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/academy/list.do")
	public String eduadm_category_academy_list(@ModelAttribute("eduCategoryInsInfVO") EduCategoryInsInfVO eduCategoryInsInfVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		
		/** EgovPropertyService */
		eduCategoryInsInfVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eduCategoryInsInfVO.setPageSize(propertiesService.getInt("pageSize")); 
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCategoryInsInfVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCategoryInsInfVO.getPageUnit());
		paginationInfo.setPageSize(eduCategoryInsInfVO.getPageSize());

		eduCategoryInsInfVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCategoryInsInfVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCategoryInsInfVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
				
		List<EduCategoryInsInfVO> list = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);		
		int totCnt = eduCategoryService.get_edu_category_ins_inf_list_totcnt(eduCategoryInsInfVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		String USE_ST = eduCategoryInsInfVO.getUSE_ST();
//		if(eduCategoryInsInfVO.getSearchUseYn() == null || eduCategoryInsInfVO.getSearchUseYn().length()==0) {
//			USE_ST = "1";
//		}
		model.addAttribute("USE_ST",USE_ST);
		model.addAttribute("searchKeyword",eduCategoryInsInfVO.getSearchKeyword());
		
		return "eduadm/category/academy/list";
	}	
	
	//관리자(교육센터) - 교육기관 카테고리 상세 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/academy/view.do")
	public String eduadm_category_academy_view(@ModelAttribute("eduCategoryInsInfVO") EduCategoryInsInfVO eduCategoryInsInfVO, 
			HttpServletRequest request, Model model) throws Exception {
		
		EduCategoryInsInfVO info = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
		if(info==null || info.getCAT_INS_SN()==null || info.getCAT_INS_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		model.addAttribute("info", info);
		
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		//지역 코드 조회 - 시군구
		List<CodeSetVO> list_address_signgu_cd = null;			
		if(info!=null && info.getSIDO_CD()!=null && info.getSIDO_CD().length()!=0){
			CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID(eduCategoryInsInfVO.getSIDO_CD());
	  		list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		model.addAttribute("list_address_signgu_cd",list_address_signgu_cd);
		
		return "eduadm/category/academy/view";
	}
	
	//관리자(교육센터) - 교육기관 카테고리 글쓰기 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/academy/write.do")
	public String eduadm_board_notice_write(@ModelAttribute("eduCategoryInsInfVO") EduCategoryInsInfVO eduCategoryInsInfVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", "");
		request.getSession().setAttribute("se2.atchFileId", "");
		//
		
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		
		return "eduadm/category/academy/write";
	}
	
	//관리자(교육센터) - 교육기관 카테고리 수정 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/academy/modify.do")
	public String eduadm_category_academy_modify(@ModelAttribute("eduCategoryInsInfVO") EduCategoryInsInfVO eduCategoryInsInfVO, 
			HttpServletRequest request, Model model) throws Exception {
		
		EduCategoryInsInfVO info = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
		if(info==null || info.getCAT_INS_SN()==null || info.getCAT_INS_SN().length()==0) {
			LOGGER.debug("존재 하지 않는 게시물을 요청");
			model.addAttribute("page_back_cnt", "-1");
			return "/eduadm/error/page_back";
		}
		
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", info.getCAT_INS_SN());
		//request.getSession().setAttribute("se2.atchFileId", info.getBD_FILE_SE2());
		//
		
		//첨부파일 갯수 확인 후 필요시 정보 업데이트
		/*
		String _atchFileId = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getBD_FILE());
		if(info.getBD_FILE()!=null && !info.getBD_FILE().equals(_atchFileId)) {
			info.setBD_FILE(_atchFileId);
			eduBoardService.boardAdmUpdate(info);	
		}
		*/
		//
		
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		//지역 코드 조회 - 시군구
		List<CodeSetVO> list_address_signgu_cd = null;			
		if(info!=null && info.getSIDO_CD()!=null && info.getSIDO_CD().length()!=0){
			CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID(eduCategoryInsInfVO.getSIDO_CD());
	  		list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		model.addAttribute("list_address_signgu_cd",list_address_signgu_cd);
		
		model.addAttribute("info", info);
		
		return "eduadm/category/academy/modify";
	}
	
	
	//관리자(교육센터) - 교육기관 카테고리 글쓰기 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/academy/write_act.do")
	public String eduadm_category_academy_write_act(@ModelAttribute("eduCategoryInsInfVO") EduCategoryInsInfVO eduCategoryInsInfVO, 
			MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
			
		log_dscrp.append("[교육센터관리자-교육기관카테고리-글작성]");
		tbl_inf.append("EDU_CAT_INS_INF_TB");
		
		/*
		String bd_file_se2 = eduCategoryInsInfVO.getBD_FILE_SE2();
		bd_file_se2 = new PublicFileMngUtil(fileMngService, fileUtil).chkFileUpdateSe2(
				bd_file_se2, //스마트에디터 첨부파일 아이디 
				eduCategoryInsInfVO.getBD_CONT() //스마트에디터 컨텐츠 내용(html)
				);
		eduCategoryInsInfVO.setBD_FILE_SE2(bd_file_se2);	
		*/
		/*
		String _atchFileId = "";
		final Map<String, MultipartFile> files = multiRequest.getFileMap();			
		Map fresult = new PublicFileMngUtil(fileMngService,fileUtil).chkFileUpdate(
				files, //업로드 파일 목록
				_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
				"", //첨부파일아이디 번호(FILE_SN)
				"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
				new String[]{
					"jpg","jpge","png","gif","bmp", //image
					"mp4","avi","wmv", //video
					"hwp","doc","xls","xlsx","csv","txt","pdf", //document
					"mp3","zip", //etc
				} , //허용할 업로드 파일 확장자 
				0, //허용할 업로드 파일 사이즈
				"" //파일저장위치(기본값:Globals.fileStorePath)
				);
		if(fresult.get("error").equals("1")) {
			LOGGER.debug("정상적인 거부");
		} else if(fresult.get("error").equals("2")) {
			LOGGER.debug("파일 검증 실패");
			model.addAttribute("page_back_cnt", "-3");
			return "/eduadm/error/page_back";
		} else { //정상적으로 처리됨.
			_atchFileId = fresult.get("atchFileId").toString();	
		}
		
		eduCategoryInsInfVO.setBD_FILE(_atchFileId);
		*/
		
		eduCategoryInsInfVO.setREG_MBR_ID(MASTER_MBR_ID);
		eduCategoryInsInfVO.setUPD_MBR_ID(MASTER_MBR_ID);
		
		Enumeration enumetation = request.getParameterNames();
		 while (enumetation.hasMoreElements()) {
			 EgovStringUtil mEgovStringUtil = new EgovStringUtil();
		      
			 String key = (String)enumetation.nextElement();
			 String[] data = request.getParameterValues(key);
		     if (data != null) {
				 switch(key){
					 case "CAT_INS_NM" : eduCategoryInsInfVO.setCAT_INS_NM(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					 case "CAT_INS_PSTN" : eduCategoryInsInfVO.setCAT_INS_PSTN(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					 case "CAT_INS_CEO" : eduCategoryInsInfVO.setCAT_INS_CEO(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					 case "CAT_INS_ADDR" : eduCategoryInsInfVO.setCAT_INS_ADDR(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					 case "CAT_INS_DSCRP" : eduCategoryInsInfVO.setCAT_INS_DSCRP(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					 case "CAT_INS_STAMP" : eduCategoryInsInfVO.setCAT_INS_STAMP(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					 case "CAT_INS_TEL" : eduCategoryInsInfVO.setCAT_INS_TEL(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
				 }
		     } 
	    }
		
		eduCategoryService.set_edu_category_ins_inf_reg(eduCategoryInsInfVO);
		
		log_dscrp.append("[게시물:"+eduCategoryInsInfVO.getCAT_INS_NM()+"(일련번호:신규)]");
		tbl_inf.append("ALL_BOARD_DATA_TB");

		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCategoryInsInfVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}		
		
		return "redirect:/eduadm/category/academy/list.do";
	}
	
	
	//관리자(교육센터) - 교육기관 카테고리 수정 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/academy/modify_act.do") 
	public String eduadm_category_academy_modify_act(@ModelAttribute("eduCategoryInsInfVO") EduCategoryInsInfVO eduCategoryInsInfVO, 
			MultipartHttpServletRequest multiRequest, HttpServletRequest request, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		
		String return_url = "";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		EduCategoryInsInfVO info = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
		if(info==null || info.getCAT_INS_SN()==null || info.getCAT_INS_SN().length()==0) {
			LOGGER.debug("존재하지 않는 게시물 진입..");
			model.addAttribute("page_back_cnt", "-3");
			return_url = "/eduadm/error/page_back";
		} else {
			LOGGER.debug("정상적인 접근.");
						
			log_dscrp.append("[교육센터관리자-교육기관카테고리-글수정]");
			tbl_inf.append("EDU_CAT_INS_INF_TB");
			log_dscrp.append("[게시물:"+info.getCAT_INS_NM()+"(일련번호:"+info.getCAT_INS_SN()+")]");
			
			/*
			String bd_file_se2 = eduCategoryInsInfVO.getBD_FILE_SE2();
			bd_file_se2 = new PublicFileMngUtil(fileMngService, fileUtil).chkFileUpdateSe2(
					bd_file_se2, //스마트에디터 첨부파일 아이디 
					eduCategoryInsInfVO.getBD_CONT() //스마트에디터 컨텐츠 내용(html)
					);
			eduCategoryInsInfVO.setBD_FILE_SE2(bd_file_se2);	
			*/
			/*
			String _atchFileId = eduCategoryInsInfVO.getBD_FILE();
			PublicFileMngUtil mSealifeFileMngUtil = new PublicFileMngUtil(fileMngService,fileUtil);
			Map<String, MultipartFile> mainImgfiles = mSealifeFileMngUtil.extractinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_main_img",
					});
			Map<String, MultipartFile> atchfiles = mSealifeFileMngUtil.removeinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_main_img",
					});
			{//메인이미지
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						mainImgfiles, //업로드 파일 목록
						_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
						"0", //첨부파일아이디 번호(FILE_SN)
						"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							"mp4","avi","wmv", //video
							"hwp","doc","xls","xlsx","csv","txt","pdf", //document
							"mp3","zip", //etc
						} , //허용할 업로드 파일 확장자 
						0, //허용할 업로드 파일 사이즈
						"" //파일저장위치(기본값:Globals.fileStorePath)
						);
				if(fresult.get("error").equals("1")) {
					LOGGER.debug("정상적인 거부");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					model.addAttribute("page_back_cnt", "-3");
					return "/seadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId = fresult.get("atchFileId").toString();	
				}
			}
			{//첨부파일
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						atchfiles, //업로드 파일 목록
						_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
						"", //첨부파일아이디 번호(FILE_SN)
						"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							"mp4","avi","wmv", //video
							"hwp","doc","xls","xlsx","csv","txt","pdf", //document
							"mp3","zip", //etc
						} , //허용할 업로드 파일 확장자 
						0, //허용할 업로드 파일 사이즈
						"" //파일저장위치(기본값:Globals.fileStorePath)
						);
				if(fresult.get("error").equals("1")) {
					LOGGER.debug("정상적인 거부");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					model.addAttribute("page_back_cnt", "-3");
					return "/seadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId = fresult.get("atchFileId").toString();	
				}
			}
			eduCategoryInsInfVO.setBD_FILE(_atchFileId);	
			*/
			 
			eduCategoryInsInfVO.setUPD_MBR_ID(MASTER_MBR_ID);
			Enumeration enumetation = request.getParameterNames();
			while (enumetation.hasMoreElements()) {
				EgovStringUtil mEgovStringUtil = new EgovStringUtil();
				
				String key = (String)enumetation.nextElement();
				String[] data = request.getParameterValues(key);
				if (data != null) {
					switch(key){
					case "CAT_INS_NM" : eduCategoryInsInfVO.setCAT_INS_NM(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					case "CAT_INS_PSTN" : eduCategoryInsInfVO.setCAT_INS_PSTN(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					case "CAT_INS_CEO" : eduCategoryInsInfVO.setCAT_INS_CEO(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					case "CAT_INS_ADDR" : eduCategoryInsInfVO.setCAT_INS_ADDR(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					case "CAT_INS_DSCRP" : eduCategoryInsInfVO.setCAT_INS_DSCRP(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					case "CAT_INS_STAMP" : eduCategoryInsInfVO.setCAT_INS_STAMP(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					case "CAT_INS_TEL" : eduCategoryInsInfVO.setCAT_INS_TEL(mEgovStringUtil.getHtmlStrCnvr(request.getParameter(key))); break;
					}
				} 
			}
			eduCategoryService.set_edu_category_ins_inf_mod(eduCategoryInsInfVO);
			
			return_url = "redirect:/eduadm/category/academy/list.do";
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCategoryInsInfVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
		
		return return_url;
	}
	
	//관리자(교육센터)  - 교육기관 카테고리 삭제 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/eduadm/category/academy/delete_act.do")
	public String eduadm_category_academy_delete_act(@ModelAttribute("eduCategoryInsInfVO") EduCategoryInsInfVO eduCategoryInsInfVO, 
			HttpServletRequest request, Model model) throws Exception {
		String return_url = "";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		log_dscrp.append("[교육센터관리자-교육기관카테고리-글삭제]");
		
		EduCategoryInsInfVO info = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
		if(info==null || info.getCAT_INS_SN()==null || info.getCAT_INS_SN().length()==0) {
			LOGGER.debug("존재하지 않는 게시물 진입..");
			model.addAttribute("page_back_cnt", "-3");
			return_url = "/eduadm/error/page_back";
		} else {
			LOGGER.debug("정상적인 접근.");
						
			tbl_inf.append("EDU_CAT_INS_INF_TB");
			
			log_dscrp.append("[게시물:"+info.getCAT_INS_NM()+"(일련번호:"+info.getCAT_INS_SN()+")]");
			
			eduCategoryInsInfVO.setUPD_MBR_ID(MASTER_MBR_ID);
			
			String DEL_ST = info.getDEL_ST();
			if(DEL_ST.equals("1")) { //완전 삭제
				log_dscrp.append("[실제데이터삭제]");
				
				eduCategoryService.remove_edu_category_ins_inf(eduCategoryInsInfVO);
				/* 파일첨부 삭제시
				if(eduCategoryInsInfVO.getBD_FILE()!=null && eduCategoryInsInfVO.getBD_FILE().length()!=0) {
					fileMngService.deleteAllDetailFileInfs(eduCategoryInsInfVO.getBD_FILE());
					fileMngService.deleteAllFileInf(eduCategoryInsInfVO.getBD_FILE());
				}
				if(eduCategoryInsInfVO.getBD_FILE_SE2()!=null && eduCategoryInsInfVO.getBD_FILE_SE2().length()!=0) {
					fileMngService.deleteAllDetailFileInfs(eduCategoryInsInfVO.getBD_FILE_SE2());
					fileMngService.deleteAllFileInf(eduCategoryInsInfVO.getBD_FILE_SE2());
				}
				*/
			} else {//처음 삭제
				log_dscrp.append("[1단계삭제]");
				eduCategoryService.del_edu_category_ins_inf(eduCategoryInsInfVO);
			}
						
			return_url = "redirect:/eduadm/category/academy/list.do";
		}	
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCategoryInsInfVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
		
		return return_url;
	}
	
}


