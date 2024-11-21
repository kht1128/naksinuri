package egovframework.eduadm.main.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.poifs.filesystem.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.member.service.AdmMemberVO;
import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogMemberModifyVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.DwldConfimFileVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.com.cmm.service.FileVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.member.service.EduExcelUploadVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.educenter.myhistory.service.MyHistoryService;
import egovframework.educenter.myhistory.service.MyHistoryVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduCenterAdmController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterAdmController.class);
	
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
	
	@Resource(name = "smsManagerService")
	private SmsManagerService smsManagerService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	
	
	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	@Resource(name = "myHistoryService")
	private MyHistoryService myHistoryService;
	
	//관리자(교육센터) 접근권한없음 ------------------------------------------------
	@RequestMapping(value = "/eduadm/error/unauth.do")
	public String eduadm_error_unauth(HttpServletRequest request, ModelMap model) throws Exception {
		
		return "eduadm/error/unauth";
	}
	
	//관리자(교육센터) 메인페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/index.do" )
	public String edu_main(HttpServletRequest request, ModelMap model) throws Exception {
		String returnUrl = "";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {//비로그인 및 제한거부자
			returnUrl = "redirect:/eduadm/curriculum/list.do";
		} else {
			if(loginVO.getMBR_POSITION_CD().equals("PCD0002")) { //해양경찰서
				returnUrl = "redirect:/eduadm/member/eduList.do";	
			} else if(loginVO.getMBR_POSITION_CD().equals("PCD0003")) { //지자체
				returnUrl = "redirect:/eduadm/member/eduList.do";
			//} else if(loginVO.getMBR_POSITION_CD().equals("PCD0004")) { //교육기관
			//	returnUrl = "redirect:/eduadm/curriculum/list.do";
			} else {
				returnUrl = "redirect:/eduadm/curriculum/list.do";
			}
		}
		return returnUrl;
	}
	
	//관리자(교육센터) 보고서 다운로드 페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/main/report/list.do")
	public String edu_buy(Map<String,Object> params, HttpServletRequest request, ModelMap model) throws Exception {		
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO.getMBR_POSITION_CD().equals("PCD0004") || loginVO.getMBR_POSITION_CD().equals("PCD0003") ||  loginVO.getMBR_POSITION_CD().equals("PCD0002")){
			LOGGER.debug("교육센터 관리자페이지 - 접근권한 없음!!");
			return "redirect:/eduadm/error/unauth.do";
		} 
		
		//지역 코드 조회 - 시도
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		List<CodeSetVO> list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_address_cd",list_address_cd);
		}
		//교육그룹 코드 조회(전체)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_all_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(비활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("Y");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_prev_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}		
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_trgt_cd",list_mbr_cd);
		}
		return "eduadm/report/list";
	}
	
	
	//교육센터  - 승인제 파일 다운로드 ------------------------------------------------
	@RequestMapping(value = "/eduadm/main/dwldConfim/list.do")
	public Object eduadm_board_esrequest_list(@ModelAttribute("dwldConfimFileVO") DwldConfimFileVO dwldConfimFileVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String returnUrl = "";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO.getMBR_LV_ID().equals("1")) {//최고관리자는 모든 리스트 허용
			returnUrl = "eduadm/dwldConfim/list_adm";
		} else {
			returnUrl = "eduadm/dwldConfim/list";
			dwldConfimFileVO.setREQ_MBR_ID(loginVO.getMBR_ID());
			dwldConfimFileVO.setSearchOrderBy("2");
		}

		// 교육기관 > 엑셀다운로드 접근 불가
		if(loginVO.getMBR_POSITION_CD().equals("PCD0004")){
			String[] koreafcaMbrIds = propertiesService.getString("koreafca.mbrIds").trim().split(",");
			boolean isKoreafcaMember = false;
			if(koreafcaMbrIds!=null && koreafcaMbrIds.length!=0) {
				for (String mbrid : koreafcaMbrIds) {
					if(loginVO.getMBR_ID().equals(mbrid)) {
						isKoreafcaMember = true;
						break;
					}
				}
			}
			if(isKoreafcaMember) {
				LOGGER.debug("엑셀다운로드 - 특정 계정은 예외처리 !!");
			} else {
				LOGGER.debug("엑셀다운로드 - 접근권한 없음!!");
				return "redirect:/eduadm/error/unauth.do";
			}
		} 
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(dwldConfimFileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(dwldConfimFileVO.getPageUnit());
		paginationInfo.setPageSize(dwldConfimFileVO.getPageSize());

		dwldConfimFileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		dwldConfimFileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		dwldConfimFileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<DwldConfimFileVO> list = fileMngService.get_dwld_confim_list(dwldConfimFileVO);		
		int totCnt = fileMngService.get_dwld_confim_list_totcnt(dwldConfimFileVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("CUR_MBR_ID",loginVO.getMBR_ID());
		model.addAttribute("CONFIM_ST",dwldConfimFileVO.getCONFIM_ST());
		model.addAttribute("searchOrderBy", dwldConfimFileVO.getSearchOrderBy());
		//model.addAttribute("searchKeyword",dwldConfimFileVO.getSearchCondition().length()==0?"":dwldConfimFileVO.getSearchKeyword());
		model.addAttribute("searchKeyword",dwldConfimFileVO.getSearchKeyword());
		//model.addAttribute("frm_CMPLT_ST",frm_CMPLT_ST);
		return returnUrl;	
	}
	
	//교육센터  - 승인제 파일 다운로드 - 승인처리 ------------------------------------------------
	@RequestMapping(value="/eduadm/main/dwldConfim/modify_all_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_mbr_rmndr_modify_all_act(@ModelAttribute("dwldConfimFileVO") DwldConfimFileVO dwldConfimFileVO,
			@RequestParam(value="confirm_type",required=false) String confirm_type,
			@RequestParam(value="chkedFilekeys",required=false) String chkedFilekeys,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
				
		LogRecordVO logtemp = new LogRecordVO();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-파일다운로드-선택회원일괄처리]");
		tbl_inf.append("COMTNFILEDETAIL");
		log_msg.append(logtemp.encodingFromObjectToJson(dwldConfimFileVO)+"]");
		
		if(loginVO.getMBR_LV_ID().equals("1")) {
			if(chkedFilekeys != null) {
				String addMsg = "";
				String[] praseChkedFilekeys = chkedFilekeys.replaceAll("\\s","").split(",");
				for(String file_key : praseChkedFilekeys) {
					if(file_key==null) {
						log_msg.append("[비정상적인데이터:file_key="+file_key+"]");
						continue;					
					}
					String[] parseFileKeys = file_key.replaceAll("\\s","").split(";");
					String file_id = parseFileKeys[0];
					String file_sn = parseFileKeys[1];
					//검증
					DwldConfimFileVO chkDwldConfimFileVO = new DwldConfimFileVO();
					chkDwldConfimFileVO.setATCH_FILE_ID(file_id);
					chkDwldConfimFileVO.setFILE_SN(file_sn);
					chkDwldConfimFileVO = fileMngService.get_dwld_confim_info(chkDwldConfimFileVO);
					if(chkDwldConfimFileVO.getFILE_SN()==null || chkDwldConfimFileVO.getFILE_SN().length()==0) {
						log_dscrp.append("[정보없음(일련번호:"+file_key+")-처리불가]");
						log_msg.append("[정보없음:file_key="+file_key+"]");
						continue;
					} else {
						String resultStr = "";
						if(confirm_type.equals("cancel")) { //승인취소
							resultStr = "승인취소";
							if(chkDwldConfimFileVO.getDWLD_WAIT_ST().equals("Y")) {
								log_dscrp.append("[이름:"+chkDwldConfimFileVO.getREQ_MBR_NM()+"(아이디:"+chkDwldConfimFileVO.getREQ_MBR_ID()+",일련번호:"+file_key+",파일명:"+chkDwldConfimFileVO.getORIGNL_FILE_NM()+")-처리실패]");
								log_msg.append("[처리실패:file_key="+file_key+"]");
								addMsg += "<br>항목 중 '자료준비중'건은 승인대기 시에만 처리가 가능합니다.";
							} else {
								FileVO fvo = new FileVO();						
								fvo.setCONFIM_ST("CANCEL");
								fvo.setAtchFileId(file_id);
								fvo.setFileSn(file_sn);
								fvo.setUPD_MBR_ID(loginVO.getMBR_ID());
								fvo.setCONFIM_MBR_ID(loginVO.getMBR_ID());
								fvo.setCONFIM_MSG(dwldConfimFileVO.getCONFIM_MSG());
								fileMngService.updateDetailFileInfForConfirm(fvo);
								log_dscrp.append("[이름:"+chkDwldConfimFileVO.getREQ_MBR_NM()+"(아이디:"+chkDwldConfimFileVO.getREQ_MBR_ID()+",일련번호:"+file_key+",파일명:"+chkDwldConfimFileVO.getORIGNL_FILE_NM()+")-"+resultStr+"]");
								log_msg.append("[처리완료:file_key="+file_key+"]");
								log_msg.append(logtemp.encodingFromObjectToJson(chkDwldConfimFileVO)+"]");
							}
						} else { //승인처리
							resultStr = "승인완료";
							if(chkDwldConfimFileVO.getDWLD_WAIT_ST().equals("Y")) {
								log_dscrp.append("[이름:"+chkDwldConfimFileVO.getREQ_MBR_NM()+"(아이디:"+chkDwldConfimFileVO.getREQ_MBR_ID()+",일련번호:"+file_key+",파일명:"+chkDwldConfimFileVO.getORIGNL_FILE_NM()+")-처리실패]");
								log_msg.append("[처리실패:file_key="+file_key+"]");
								addMsg += "<br>항목 중 '자료준비중'건은 승인대기 시에만 처리가 가능합니다.";
							} else {
								FileVO fvo = new FileVO();						
								fvo.setCONFIM_ST("Y");
								fvo.setAtchFileId(file_id);
								fvo.setFileSn(file_sn);
								fvo.setUPD_MBR_ID(loginVO.getMBR_ID());
								fvo.setCONFIM_MBR_ID(loginVO.getMBR_ID());
								fvo.setCONFIM_MSG(dwldConfimFileVO.getCONFIM_MSG());
								fileMngService.updateDetailFileInfForConfirm(fvo);
								log_dscrp.append("[이름:"+chkDwldConfimFileVO.getREQ_MBR_NM()+"(아이디:"+chkDwldConfimFileVO.getREQ_MBR_ID()+",일련번호:"+file_key+",파일명:"+chkDwldConfimFileVO.getORIGNL_FILE_NM()+")-"+resultStr+"]");
								log_msg.append("[처리완료:file_key="+file_key+"]");
								log_msg.append(logtemp.encodingFromObjectToJson(chkDwldConfimFileVO)+"]");
								//요청자에게 문자 발송
								//String reg_date = mPublicUtils.changePatternString(chkDwldConfimFileVO.getREG_DT().replace(".0", ""), "yyyy-MM-dd HH(hh):mm:ss", "yyyy년 MM월 dd일 HH시 mm분");
								SmsSendVO newSmsSendVO = new SmsSendVO();
								newSmsSendVO.setMBR_ID(chkDwldConfimFileVO.getREQ_MBR_ID());
								newSmsSendVO.setMSG(chkDwldConfimFileVO.getREQ_MBR_NM()+"님 "+chkDwldConfimFileVO.getREG_DT().replace(".0", "")+" 에 신청하신 파일 다운로드가 승인되었습니다.\n\n파일명 : "+chkDwldConfimFileVO.getORIGNL_FILE_NM()+"\n\n낚시전문교육 관리자페이지-출력관리-엑셀다운로드 에서 확인 하실 수 있습니다.");
								newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));//발신번호
								newSmsSendVO.setR_PHONE(chkDwldConfimFileVO.getREQ_MBR_HP());
								newSmsSendVO.setSUBMSG("낚시전문교육");
								newSmsSendVO.setIMG_CNT(0);
								newSmsSendVO.setIMG_PATH("");
								newSmsSendVO.setREG_MBR_ID(loginVO.getMBR_ID());
								newSmsSendVO.setUPD_MBR_ID(loginVO.getMBR_ID());			
							    newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
								String rstMsg = smsManagerService.sendToMessage(newSmsSendVO);
								log_dscrp.append("[승인문자발송]");
								//End of 요청자에게 문자 발송
							}
						}
					}
				}
				data.put("error", "0");
				data.put("msg", "선택항목에 대해 완료 처리되었습니다."+addMsg);				
			} else {
				log_dscrp.append("[처리실패]");
				log_msg.append("[처리실패 null]");
			}
		} else {
			log_dscrp.append("[처리실패:권한없음]");
			log_msg.append("[처리실패:권한없음]");
			data.put("error", "1");
			data.put("msg", "처리 할 권한이 없습니다.");		
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			mEduLogRecordVO.setMBR_MSG(dwldConfimFileVO.getCONFIM_MSG());
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}	
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
				
	
	/**
	 * 유틸리티 
	 * */
	//교육카테고리정보 요청 ------------------------------------------------
	@RequestMapping(value = "/eduadm/util/category.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_util_category(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		List<EduCategoryVO> list = null;
		eduCategoryVO.setSearchKeyword("use_st");
		eduCategoryVO.setUSE_ST("1");
		if(eduCategoryVO.getTypeStr().equals("dtl")) {			
			list = eduCategoryService.get_edu_category_list_2(eduCategoryVO);
		} else {
			list = eduCategoryService.get_edu_category_list_1(eduCategoryVO);
		}
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
	//교육카테고리정보 요청 ------------------------------------------------
	@RequestMapping(value = "/eduadm/util/category_to_edudata.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_util_category_to_edudata(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		List<EduTrainingDataVO> list = null;
		eduCategoryVO.setSearchKeyword("use_st");
		eduCategoryVO.setUSE_ST("1");
		list = eduTrainingDataService.get_edu_data_list(eduCategoryVO);
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
	
	@RequestMapping(value = "/eduadm/dataUnity/list.do", method = RequestMethod.POST)
	public ModelAndView ajax_eduadm_data_unity_list(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@RequestParam (value="MBR_IDS", required=false) String MBR_IDS,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		ModelAndView mModelAndView = new ModelAndView();
		EduMemberVO info1 = null;
		EduMemberVO info2 = null;
		List<EduMemberVO> list_dtl_1 = null;
		List<EduMemberVO> list_dtl_2 = null;
		List<EduMemberVO> list_edu_target_1 = null;
		List<EduMemberVO> list_edu_target_2 = null;
		
		EduMemberVO eduMemberVO1 = new EduMemberVO();
		EduMemberVO eduMemberVO2 = new EduMemberVO();
		
		eduMemberVO1.setMBR_ID(eduMemberVO.getMBR_IDS()[0]);
		eduMemberVO1 = eduMemberService.get_edu_member_info(eduMemberVO1);
		
		eduMemberVO2.setMBR_ID(eduMemberVO.getMBR_IDS()[1]);
		eduMemberVO2 = eduMemberService.get_edu_member_info(eduMemberVO2);
			
		
		if(eduMemberVO1!=null && eduMemberVO1.getMBR_ID()!=null && eduMemberVO1.getMBR_ID().length()!=0) {
			info1 = eduMemberService.get_edu_member_info(eduMemberVO1);
			list_dtl_1 = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO1);
			list_edu_target_1 = eduMemberService.get_edu_member_target_all_list(eduMemberVO1);
			
			{			
				info1.setSearchYear(eduMemberVO1.getSearchYear());
				//지역 코드 조회 - 시도
				{
			  		CodeSetVO mCodeSetVO1 = new CodeSetVO();
			  		mCodeSetVO1.setCD_MASTER_ID("CID00004");
			  		List<CodeSetVO> list_address_cd_1 = codeSetService.get_codeset_list(mCodeSetVO1);
			  		mModelAndView.addObject("list_address_cd_1",list_address_cd_1);
				}
				//지역 코드 조회 - 시군구
				List<CodeSetVO> list_address_signgu_cd_1 = null;
				if(info1.getMBR_SIDO_CD()!=null && info1.getMBR_SIDO_CD().length()!=0) {
			  		CodeSetVO mCodeSetVO1 = new CodeSetVO();
			  		mCodeSetVO1.setCD_MASTER_ID(info1.getMBR_SIDO_CD());
			  		list_address_signgu_cd_1 = codeSetService.get_codeset_list(mCodeSetVO1);
				}
				mModelAndView.addObject("list_address_signgu_cd_1",list_address_signgu_cd_1);
				//
				//회원추가정보구분자
				{
			  		CodeSetVO mCodeSetVO1 = new CodeSetVO();
			  		mCodeSetVO1.setCD_MASTER_ID("CID00002");
			  		List<CodeSetVO> list_target_se_cd_1 = codeSetService.get_codeset_list(mCodeSetVO1);
			  		mModelAndView.addObject("list_target_se_cd_1",list_target_se_cd_1);
				}
				//사업자구분코드
				{
			  		CodeSetVO mCodeSetVO1 = new CodeSetVO();
			  		mCodeSetVO1.setCD_MASTER_ID("CID00006");
			  		List<CodeSetVO> list_license_se_cd_1 = codeSetService.get_codeset_list(mCodeSetVO1);
			  		mModelAndView.addObject("list_license_se_cd_1",list_license_se_cd_1);
				}
				//낚시터업구분코드
				{
			  		CodeSetVO mCodeSetVO1 = new CodeSetVO();
			  		mCodeSetVO1.setCD_MASTER_ID("CID00007");
			  		mCodeSetVO1.setHIDE_AT("N");
			  		List<CodeSetVO> list_fshlc_work_cd_1 = codeSetService.get_codeset_list(mCodeSetVO1);
			  		model.addAttribute("list_fshlc_work_cd_1",list_fshlc_work_cd_1);
				}	
				//직급 코드 조회
				{
			  		CodeSetVO mCodeSetVO1 = new CodeSetVO();
			  		mCodeSetVO1.setCD_MASTER_ID("CID00003");
			  		List<CodeSetVO> list_position_cd_1 = codeSetService.get_codeset_list(mCodeSetVO1);
			  		mModelAndView.addObject("list_position_cd_1",list_position_cd_1);
				}
			}
		}
		
			if(eduMemberVO2!=null && eduMemberVO2.getMBR_ID()!=null && eduMemberVO2.getMBR_ID().length()!=0) {
				info2 = eduMemberService.get_edu_member_info(eduMemberVO2);
				list_dtl_2 = eduMemberService.get_edu_member_dtl_all_list(eduMemberVO2);
				list_edu_target_2 = eduMemberService.get_edu_member_target_all_list(eduMemberVO2);
				
				{			
					info2.setSearchYear(eduMemberVO2.getSearchYear());
					//지역 코드 조회 - 시도
					{
				  		CodeSetVO mCodeSetVO2 = new CodeSetVO();
				  		mCodeSetVO2.setCD_MASTER_ID("CID00004");
				  		List<CodeSetVO> list_address_cd_2 = codeSetService.get_codeset_list(mCodeSetVO2);
				  		mModelAndView.addObject("list_address_cd_2",list_address_cd_2);
					}
					//지역 코드 조회 - 시군구
					List<CodeSetVO> list_address_signgu_cd_2 = null;
					if(info2.getMBR_SIDO_CD()!=null && info2.getMBR_SIDO_CD().length()!=0) {
				  		CodeSetVO mCodeSetVO2 = new CodeSetVO();
				  		mCodeSetVO2.setCD_MASTER_ID(info2.getMBR_SIDO_CD());
				  		list_address_signgu_cd_2 = codeSetService.get_codeset_list(mCodeSetVO2);
					}
					mModelAndView.addObject("list_address_signgu_cd_2",list_address_signgu_cd_2);
					//
					//회원추가정보구분자
					{
				  		CodeSetVO mCodeSetVO2 = new CodeSetVO();
				  		mCodeSetVO2.setCD_MASTER_ID("CID00002");
				  		List<CodeSetVO> list_target_se_cd_2 = codeSetService.get_codeset_list(mCodeSetVO2);
				  		mModelAndView.addObject("list_target_se_cd_2",list_target_se_cd_2);
					}
					//사업자구분코드
					{
				  		CodeSetVO mCodeSetVO2 = new CodeSetVO();
				  		mCodeSetVO2.setCD_MASTER_ID("CID00006");
				  		List<CodeSetVO> list_license_se_cd_2 = codeSetService.get_codeset_list(mCodeSetVO2);
				  		mModelAndView.addObject("list_license_se_cd_2",list_license_se_cd_2);
					}
					//낚시터업구분코드
					{
				  		CodeSetVO mCodeSetVO2 = new CodeSetVO();
				  		mCodeSetVO2.setCD_MASTER_ID("CID00007");
				  		mCodeSetVO2.setHIDE_AT("N");
				  		List<CodeSetVO> list_fshlc_work_cd_2 = codeSetService.get_codeset_list(mCodeSetVO2);
				  		model.addAttribute("list_fshlc_work_cd_2",list_fshlc_work_cd_2);
					}	
					//직급 코드 조회
					{
				  		CodeSetVO mCodeSetVO2 = new CodeSetVO();
				  		mCodeSetVO2.setCD_MASTER_ID("CID00003");
				  		List<CodeSetVO> list_position_cd_2 = codeSetService.get_codeset_list(mCodeSetVO2);
				  		mModelAndView.addObject("list_position_cd_2",list_position_cd_2);
					}
				}
			}
				
			mModelAndView.setViewName("eduadm/member/data_unity_list");	
			mModelAndView.addObject("info1",info1);
			mModelAndView.addObject("info2",info2);
			mModelAndView.addObject("list_dtl_1",list_dtl_1);
			mModelAndView.addObject("list_dtl_2",list_dtl_2);
			mModelAndView.addObject("list_edu_target_1",list_edu_target_1);
			mModelAndView.addObject("list_edu_target_2",list_edu_target_2);
		
			return mModelAndView;
	}
	
	@RequestMapping(value = "/eduadm/dataUnity/unity_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_data_unity_act(@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String main_mbr_id = eduMemberVO.getMBR_IDS()[0];
		String sub_mbr_id = eduMemberVO.getMBR_IDS()[1];
		String main_mbr_nm = eduMemberVO.getMBR_NMs()[0];
		String sub_mbr_nm = eduMemberVO.getMBR_NMs()[1];
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-중복데이터확인]");
		
		log_dscrp.append("[통합아이디:" + main_mbr_id + "]");
		log_dscrp.append("[변경아이디:" + sub_mbr_id + "]");

		EduMemberVO eduMemberVO1 = new EduMemberVO();
		eduMemberVO1.setMBR_ID(main_mbr_id);
		EduMemberVO chkEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO1);
		
		
		// TO_MBR_DTL_TB 수정
		if(eduMemberVO.getTO_MBR_DTL() != null){
			for (int i=0; i < eduMemberVO.getTO_MBR_DTL().length; i++) {
				String ChkMbrDtl = eduMemberVO.getTO_MBR_DTL()[i];
				String mbrDtlSn = eduMemberVO.getTO_MBR_DTL_SN()[i];
				
				if(ChkMbrDtl.equals("1")){
					// 데이터 유지
					log_dscrp.append("[DTL_SN:"+mbrDtlSn+"-회원상세정보유지]");
				} else if(ChkMbrDtl.equals("3")){
					// 데이터 삭제
					EduMemberVO delEduMemberVO = new EduMemberVO();
					delEduMemberVO.setMBR_ID(main_mbr_id);
					delEduMemberVO.setDTL_SN(mbrDtlSn);
					delEduMemberVO = eduMemberService.get_edu_member_dtl_info(delEduMemberVO);
					//사용자사유로그기록
					{
						logRecordService.set_log_mbr_mod_data("MBR_DTL_TB","삭제","데이터통합 - 상세정보 삭제 : "+ eduMemberVO.getLOG_UPD_MSG(),main_mbr_id,main_mbr_nm,delEduMemberVO,null,loginVO,request);
					}
					eduMemberService.remove_edu_member_dtl(delEduMemberVO);					

					log_dscrp.append("[DTL_SN:"+mbrDtlSn+"-회원상세정보삭제]");
					tbl_inf.append("MBR_DTL_TB,");
				}

			}
		}
		
		// TO_EDU_MBR_HSTRY_TB 수정
		if(eduMemberVO.getTO_EDU_HSTRY() != null){
			for (int i=0; i < eduMemberVO.getTO_EDU_HSTRY().length; i++) {
				
				String ChkEduHstryDtl = eduMemberVO.getTO_EDU_HSTRY()[i];
				String hmbrSn = eduMemberVO.getTO_EDU_HSTRY_SN()[i];
				
				if(ChkEduHstryDtl.equals("1")){
					// 데이터 유지
					log_dscrp.append("[HMBR_SN:"+hmbrSn+"-교육정보유지]");
				} else if(ChkEduHstryDtl.equals("3")){
					// 데이터 삭제
					EduMemberVO delEduMemberVO = new EduMemberVO();
					delEduMemberVO.setMBR_ID(main_mbr_id);
					delEduMemberVO.setHMBR_SN(hmbrSn);
					// MBR_EDU_TRGT_TB 삭제
					eduMemberService.remove_edu_member_target(delEduMemberVO);
					
					// tbl_inf.append("MBR_EDU_TRGT_TB,");
					if(hmbrSn != null && !hmbrSn.equals("")){
						// EDU_MBR_HSTRY_TB 삭제
						eduMemberService.remove_edu_member_hstry(delEduMemberVO);
						// EDU_MBR_HSTRY_DTL_TB 삭제
						eduMemberService.remove_edu_member_hstry_dtl(delEduMemberVO);
						// EDU_CRTF_TB 삭제
						eduMemberService.remove_edu_crtf(delEduMemberVO);
						// EDU_CRTF_DTL_TB 삭제
						eduMemberService.remove_edu_crtf_dtl(delEduMemberVO);
						//사용자사유로그기록
						{
							logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB","삭제","데이터통합 - 교육정보 삭제 : " + eduMemberVO.getLOG_UPD_MSG(), main_mbr_id,main_mbr_nm,delEduMemberVO,null,loginVO,request);
						}
						tbl_inf.append("EDU_MBR_HSTRY_TB,EDU_MBR_HSTRY_DTL_TB,EDU_CRTF_TB,EDU_CRTF_DTL_TB");
					}
					log_dscrp.append("[HMBR_SN:"+hmbrSn+"-교육정보삭제]");
				}
			}	
		}
		
		// FROM_MBR_DTL_TB 수정
		if(eduMemberVO.getFROM_MBR_DTL() != null){
			for (int i=0; i < eduMemberVO.getFROM_MBR_DTL().length; i++) {
				
				String ChkMbrDtl = eduMemberVO.getFROM_MBR_DTL()[i];
				String mbrDtlSn = eduMemberVO.getFROM_MBR_DTL_SN()[i];
				
				if(ChkMbrDtl.equals("2")){
					// 데이터 통합
					// MBR_DTL_TB -> MBR_CD, MBR_ID, UPD_MBR_ID, UPD_DT 값 변경
					EduMemberVO unityEduMemberVO = new EduMemberVO();
					unityEduMemberVO.setMBR_CD(main_mbr_id);
					unityEduMemberVO.setMBR_ID(main_mbr_id);
					unityEduMemberVO.setMBR_NM(main_mbr_nm);
					unityEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					unityEduMemberVO.setDTL_SN(mbrDtlSn);
					unityEduMemberVO.setMBR_MOD_ID(sub_mbr_id);
					
					EduMemberVO oldEduMemberVO = new EduMemberVO();
					oldEduMemberVO.setMBR_ID(sub_mbr_id);
					oldEduMemberVO.setDTL_SN(mbrDtlSn);
					EduMemberVO realOldEduMemberVO = eduMemberService.get_edu_member_dtl_info(oldEduMemberVO);
					
					eduMemberService.set_dpcn_member_dtl_mod(unityEduMemberVO);
					
					EduMemberVO newEduMemberVO = new EduMemberVO();
					newEduMemberVO.setMBR_ID(main_mbr_id);
					newEduMemberVO.setDTL_SN(mbrDtlSn);
					newEduMemberVO = eduMemberService.get_edu_member_dtl_info(unityEduMemberVO);
					//사용자사유로그기록
					{
						logRecordService.set_log_mbr_mod_data("MBR_DTL_TB","추가","데이터통합 - 상세정보 추가 : "+ eduMemberVO.getLOG_UPD_MSG(),main_mbr_id,main_mbr_nm, realOldEduMemberVO,newEduMemberVO,loginVO,request);
					}
					log_dscrp.append("[DTL_SN:"+mbrDtlSn+"-회원상세정보통합]");
					tbl_inf.append("MBR_DTL_TB,");
					
					eduMemberService.set_dpcn_member_dtl_mod(unityEduMemberVO);
				} else if (ChkMbrDtl.equals("3")){
					// 데이터 삭제
					EduMemberVO delEduMemberVO = new EduMemberVO();
					delEduMemberVO.setMBR_ID(sub_mbr_id);
					delEduMemberVO.setDTL_SN(mbrDtlSn);
					delEduMemberVO = eduMemberService.get_edu_member_dtl_info(delEduMemberVO);
					eduMemberService.remove_edu_member_dtl(delEduMemberVO);	
					
					//사용자사유로그기록
					{
						logRecordService.set_log_mbr_mod_data("MBR_DTL_TB","삭제","데이터통합 - 상세정보 삭제 : "+ eduMemberVO.getLOG_UPD_MSG(),sub_mbr_id,sub_mbr_nm,delEduMemberVO,null,loginVO,request);
					}
					log_dscrp.append("[DTL_SN:"+mbrDtlSn+"-회원상세정보삭제]");
					tbl_inf.append("MBR_DTL_TB,");
					
					
				}

			}
		}
		
		// FROM_EDU_MBR_HSTRY_TB 수정
		if(eduMemberVO.getFROM_EDU_HSTRY() != null){
			
			for (int i=0; i < eduMemberVO.getFROM_EDU_HSTRY().length; i++) {
				
				String ChkEduHstryDtl = eduMemberVO.getFROM_EDU_HSTRY()[i];
				String hmbrSn = eduMemberVO.getFROM_EDU_HSTRY_SN()[i];
				
				if(ChkEduHstryDtl.equals("2")){
					// 데이터 통합
					EduMemberVO unityEduMemberVO = new EduMemberVO();
					unityEduMemberVO.setMBR_CD(main_mbr_id);
					unityEduMemberVO.setMBR_ID(main_mbr_id);
					unityEduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					unityEduMemberVO.setHMBR_SN(hmbrSn);
					unityEduMemberVO.setMBR_MOD_ID(sub_mbr_id);

					MyHistoryVO BeforeyHistoryVO = new MyHistoryVO();
					BeforeyHistoryVO.setMBR_ID(sub_mbr_id);
					BeforeyHistoryVO.setHMBR_SN(hmbrSn);
					MyHistoryVO oldMyHistoryVO = myHistoryService.get_educenter_mbrhstry_info(BeforeyHistoryVO);
					
					LogRecordVO mLogRecordVO = new LogRecordVO();
					mLogRecordVO.setMBR_ID(main_mbr_id);
					mLogRecordVO.setMBR_MOD_ID(sub_mbr_id);
					
					// MBR_EDU_TRGT_TB -> MBR_CD, MBR_ID, UPD_MBR_ID, UPD_DT 값 변경
					eduMemberService.set_dpcn_member_edu_trgt_mod(unityEduMemberVO);
					tbl_inf.append("MBR_EDU_TRGT_TB,");
					if(hmbrSn != null && !hmbrSn.equals("")){
						
						// EDU_MBR_HSTRY_TB -> MBR_ID, UPD_MBR_ID, UPD_DT 값 변경
						eduMemberService.set_dpcn_member_edu_hstry_mod(unityEduMemberVO);
						// EDU_MBR_HSTRY_DTL_TB -> MBR_ID, UPD_MBR_ID, UPD_DT 값 변경
						eduMemberService.set_dpcn_member_edu_hstry_dtl_mod(unityEduMemberVO);
						// EDU_CRTF_TB -> MBR_ID, UPD_MBR_ID, UPD_DT 값 변경
						eduMemberService.set_dpcn_member_edu_crtf_mod(unityEduMemberVO);
						// EDU_CRTF_DTL_TB -> MBR_ID, UPD_MBR_ID, UPD_DT 값 변경
						eduMemberService.set_dpcn_member_edu_crtf_dtl_mod(unityEduMemberVO);
						// NAKSINURI_SURVEY_ANSWER -> ETC_2 값 변경
						eduMemberService.set_dpcn_member_survey_answer_mod(unityEduMemberVO);
						// LOG_EDU_TB -> MBR_ID 값 변경
						logRecordService.set_dpcn_log_edu_mod(mLogRecordVO);
						
						MyHistoryVO afterHistoryVO = new MyHistoryVO();
						afterHistoryVO.setMBR_ID(main_mbr_id);
						afterHistoryVO.setHMBR_SN(hmbrSn);
						MyHistoryVO newMyHistoryVO = myHistoryService.get_educenter_mbrhstry_info(afterHistoryVO);
						
						//사용자사유로그기록
						{
							logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB", "추가", "데이터통합 - 교육정보 추가 : "+ eduMemberVO.getLOG_UPD_MSG(),
									newMyHistoryVO.getMBR_ID(), newMyHistoryVO.getMBR_NM(), oldMyHistoryVO, newMyHistoryVO,
									loginVO, request);
						}
						tbl_inf.append("EDU_MBR_HSTRY_TB,EDU_MBR_HSTRY_DTL_TB,EDU_CRTF_TB,EDU_CRTF_DTL_TB");
					}
					log_dscrp.append("[HMBR_SN:"+hmbrSn+"-교육정보통합]");
					
					
				} else if(ChkEduHstryDtl.equals("3")){
					// 데이터 삭제
					EduMemberVO delEduMemberVO = new EduMemberVO();
					delEduMemberVO.setMBR_ID(sub_mbr_id);
					delEduMemberVO.setHMBR_SN(hmbrSn);
					
					MyHistoryVO BeforeyHistoryVO = new MyHistoryVO();
					BeforeyHistoryVO.setMBR_ID(sub_mbr_id);
					BeforeyHistoryVO.setHMBR_SN(hmbrSn);
					MyHistoryVO oldMyHistoryVO = myHistoryService.get_educenter_mbrhstry_info(BeforeyHistoryVO);
					
					// MBR_EDU_TRGT_TB 삭제
					eduMemberService.remove_edu_member_target(delEduMemberVO);
						
					tbl_inf.append("MBR_EDU_TRGT_TB,");
					if(hmbrSn != null && !hmbrSn.equals("")){
						// EDU_MBR_HSTRY_TB 삭제
						eduMemberService.remove_edu_member_hstry(delEduMemberVO);
						// EDU_MBR_HSTRY_DTL_TB 삭제
						eduMemberService.remove_edu_member_hstry_dtl(delEduMemberVO);
						// EDU_CRTF_TB 삭제
						eduMemberService.remove_edu_crtf(delEduMemberVO);
						// EDU_CRTF_DTL_TB 삭제
						eduMemberService.remove_edu_crtf_dtl(delEduMemberVO);
						
						//사용자사유로그기록
						{
							logRecordService.set_log_mbr_mod_data("EDU_MBR_HSTRY_TB", "삭제", "데이터통합 - 교육정보 삭제 : "+ eduMemberVO.getLOG_UPD_MSG(),
									oldMyHistoryVO.getMBR_ID(), oldMyHistoryVO.getMBR_NM(), oldMyHistoryVO, null,
									loginVO, request);
						}
						
						tbl_inf.append("EDU_MBR_HSTRY_TB,EDU_MBR_HSTRY_DTL_TB,EDU_CRTF_TB,EDU_CRTF_DTL_TB");
					}
					log_dscrp.append("[HMBR_SN:"+hmbrSn+"-교육정보삭제]");
				}
			}	
		}
		
		// 통합된 데이터 대상을 비활성 할 경우 처리
		if (eduMemberVO.getMBR_ST().equals("N")) {
			EduMemberVO eduMemberVO2 = new EduMemberVO();
			eduMemberVO2.setMBR_ST("N");
			eduMemberVO2.setUPD_MBR_ID(loginVO.getMBR_ID());
			eduMemberVO2.setMBR_ID(sub_mbr_id);
			EduMemberVO chkEduMemberVO2 = eduMemberService.get_edu_member_info(eduMemberVO2);
			eduMemberService.set_edu_member_mod(eduMemberVO2);
			EduMemberVO realEduMemberVO2 = eduMemberService.get_edu_member_info(eduMemberVO2);
			//사용자사유로그기록
			{
				logRecordService.set_log_mbr_mod_data("MBR_TB","수정","데이터통합 - 회원상태 비활성 : " + eduMemberVO.getLOG_UPD_MSG(),sub_mbr_id,sub_mbr_nm,chkEduMemberVO2, realEduMemberVO2,loginVO,request);
			}
			log_dscrp.append("[" + sub_mbr_id + ":사용안함처리]");
		} 
		// 통합된 데이터 대상을 완전삭제할 경우 
		else if (eduMemberVO.getMBR_ST().equals("D")) {
			EduMemberVO eduMemberVO3 = new EduMemberVO();
			eduMemberVO3.setUPD_MBR_ID(loginVO.getMBR_ID());
			eduMemberVO3.setMBR_ID(sub_mbr_id);
			EduMemberVO chkEduMemberVO3 = eduMemberService.get_edu_member_info(eduMemberVO3);
			
			//사용자사유로그기록
			{
				log_dscrp.append("[교육센터관리자-회원관리-데이터통합하기-회원삭제-실제데이터삭제]");
				log_dscrp.append("[이름:"+eduMemberVO.getMBR_NMs()[1]+"(아이디:"+eduMemberVO.getMBR_IDS()[1]+")]");
				logRecordService.set_log_mbr_mod_data("MBR_TB","실제데이터삭제","데이터통합 - 회원상태 완전삭제 : " + eduMemberVO.getLOG_UPD_MSG(),sub_mbr_id,sub_mbr_nm,chkEduMemberVO3,null,loginVO,request);
				
			}
			eduMemberService.remove_edu_member(chkEduMemberVO3);
		}
				
		if( Objects.equals(chkEduMemberVO.getMBR_NM(), eduMemberVO.getMBR_NM()) || Objects.equals(chkEduMemberVO.getMBR_NCNM(), eduMemberVO.getMBR_NCNM()) || Objects.equals(chkEduMemberVO.getMBR_TEL(), eduMemberVO.getMBR_TEL())
				|| Objects.equals(chkEduMemberVO.getMBR_HP(), eduMemberVO.getMBR_HP()) || Objects.equals(chkEduMemberVO.getMBR_ADDR1(), eduMemberVO.getMBR_ADDR1()) || Objects.equals(chkEduMemberVO.getMBR_ADDR2(), eduMemberVO.getMBR_ADDR2())
					|| Objects.equals(chkEduMemberVO.getMBR_ZIPCD(), eduMemberVO.getMBR_ZIPCD()) || Objects.equals(chkEduMemberVO.getMBR_BIRTH(), eduMemberVO.getMBR_BIRTH()) || Objects.equals(chkEduMemberVO.getMBR_DSCRP(), eduMemberVO.getMBR_DSCRP()) ) {
			// MBR_TB 변경값 있음.
			eduMemberVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			eduMemberVO.setMBR_ID(main_mbr_id);
			eduMemberVO.setMBR_ST("Y");
			eduMemberService.set_edu_member_mod(eduMemberVO);
			
			EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO1);
			//사용자사유로그기록
			{
				logRecordService.set_log_mbr_mod_data("MBR_TB","수정","데이터통합 - 회원상태 유지 및 정보수정 : " + eduMemberVO.getLOG_UPD_MSG(),main_mbr_id,main_mbr_nm,chkEduMemberVO,realEduMemberVO,loginVO,request);
			}
			log_dscrp.append("[" + main_mbr_id + ":데이터수정]");
			
		}
		tbl_inf.append("MBR_TB,");
		
		
		log_msg.append("[아이디:"+loginVO.getMBR_ID()+"]");
		log_msg.append("[처리권한:"+loginVO.getMBR_GRP_ID()+"]");
		log_msg.append("[처리레벨:"+loginVO.getMBR_LV_ID()+"]");
		log_msg.append("[처리직급:"+loginVO.getMBR_POSITION_CD()+"]");
		log_msg.append("[교육대상:"+loginVO.getMBR_TRGT_CD()+"]");
		try {	
			/**
			 * LOG RECORED (로그기록)
			 */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMemberVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.debug("[fail log record] "+e.toString());
		}
		
		data.put("error", "0");
		data.put("msg", "정상적으로 데이터를 통합했습니다.");
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		
		return null;
		
	}
	
}


