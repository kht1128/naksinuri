package egovframework.all.main.web;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.adm.main.web.AdmMainController;
import egovframework.adm.popup.service.PopupService;
import egovframework.adm.popup.service.PopupVO;
import egovframework.adm.sms.service.SmsManagerService;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.error.service.ErrorVO;
import egovframework.all.excel.AllExcelDownLoad;
import egovframework.all.excel.AllExcelDownLoadBundle;
import egovframework.all.log.service.LogMemberModifyVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.DwldConfimFileVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.eduadm.board.web.EduBoardController;
import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduAdmSmsRequstVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.member.service.LogAdmAuthVO;
import egovframework.eduadm.member.web.EduMemberController;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.eduadm.myhistory.web.EduMyHistoryController;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.utils.EgovDateUtil;
import egovframework.utils.PublicUtils;
import egovframework.utils.PublicUtils.RETURN_COMPARE_TYPE;


/**
 * @Class Name : AllMainController.java
 * @Description : AllMainController Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019.12.17  김정하         최초생성
 *
 * @author 개발팀
 * @since 2019.12.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by jhkim All right reserved.
 */

@Controller
public class AllMainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AllMainController.class);
	
	@Resource(name="popupService")
	private PopupService popupService;
		
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	@Resource(name = "eduCertificateService")
	private EduCertificateService eduCertificateService;
	
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;
	
	//Controller
	@Resource(name = "eduMemberController")
	private EduMemberController mEduMemberController;
	
	@Resource(name = "eduMyHistoryController")
	private EduMyHistoryController mEduMyHistoryController;
	
	@Resource(name = "admMainController")
	private AdmMainController mAdmMainController;
	
	@Resource(name = "eduBoardController")
	private EduBoardController mEduBoardController;	
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	@Resource(name = "smsManagerService")
	private SmsManagerService smsManagerService;
		
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	
	
	
	//로그인여부 확인 후 리턴하기
	@RequestMapping(value = "/all/chk/login.do")
	public @ResponseBody String ajaxMoveUrlForChkLoginTo(@RequestParam(value="mvf",required=false) String mvf, @RequestParam(value="mvs",required=false) String mvs,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		JSONObject rstdata = new JSONObject();
		request.getSession().removeAttribute("ajaxMoveUrlForChkLoginToSuccess");
		request.getSession().removeAttribute("ajaxMoveUrlForChkLoginToFail");
		if(mvf!=null && !mvf.trim().equals("") && mvf.trim().length()!=0 && mvs!=null && !mvs.trim().equals("") && mvs.trim().length()!=0) {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			if(loginVO!=null && loginVO.getMBR_ID()!=null && loginVO.getMBR_ID().length()!=0) {
				rstdata.put("oklogin", "1");
				rstdata.put("error", "0");
				rstdata.put("msg", "로그인이 되어 있습니다.");
			} else {
				rstdata.put("oklogin", "0");
				rstdata.put("error", "0");
				rstdata.put("msg", "로그인이 되어 있지 않습니다.");
				request.getSession().setAttribute("ajaxMoveUrlForChkLoginToSuccess",mvs);
				request.getSession().setAttribute("ajaxMoveUrlForChkLoginToFail",mvf);
			}
		} else {
			rstdata.put("error", "1");
			rstdata.put("msg", "비정상적인 접근입니다.");
		}
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(rstdata);
		return null;
	}
	
	//전역 페이지에 팝업 출력
	@RequestMapping(value = "/alert/popup.do")
	public String popupNotice(@ModelAttribute("popupVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception {
		List<PopupVO> list_popup = null;
		List<PopupVO> list_banner = null;
		if(request!=null) {
			String urlstring = request.getRequestURI().toString();
			LOGGER.debug("urlstring : " + urlstring);
			if(urlstring!=null && ( 
			   urlstring.contains("/naksinuri_original/main/index")
			|| urlstring.contains("/naksinuri_original/main/m/index")
			|| urlstring.contains("/educenter/index")
			|| urlstring.contains("/educenter/mobile/index")			
			|| urlstring.contains("/naksinuri_original/main/intro")			
			)) {				
				if( urlstring.contains("/naksinuri_original/main/index") || urlstring.contains("/naksinuri_original/main/m/index") ) {
					popupVO.setPP_SEA("Y");
				}
				if( urlstring.contains("/educenter/index") || urlstring.contains("/educenter/mobile/index")) {
					popupVO.setPP_EDU("Y");
				}
				if( urlstring.contains("/naksinuri_original/main/intro") ) {
					popupVO.setPP_EXPO("Y");
				}
				/*
				if( urlstring.contains("/expo/index") || urlstring.contains("/expo/mobile/index") ) {
					popupVO.setPP_EXPO("Y");
				}
				*/				
				
				//기간 종료일 체크 처리 로직
				popupService.chkBoardAutoEnded();
				//End 기간 종료일 체크 처리 로직
				
				popupVO.setPP_HIDE_ST("N");
				popupVO.setChk_show_allow_poup(true);
				popupVO.setNotUsedPagination(true);
				
				popupVO.setPP_TYPE("popup");
				list_popup = popupService.get_seadm_popup_list(popupVO);
							
				popupVO.setPP_TYPE("banner_top");
				list_banner = popupService.get_seadm_popup_list(popupVO);
			}
				
		}
		
		model.addAttribute("list_popup",list_popup);
		model.addAttribute("list_banner",list_banner);
		
		return "all/popup/view";
	}
	
	
	@RequestMapping(value = "/all/main/excel/chkDown.do")
	public Object listExcelDownloadCheck(@RequestParam("excel_msg") String excel_msg,@RequestParam("excel_type") String excel_type,@RequestParam("excel_label") String excel_label,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		PublicUtils mPublicUtils = new PublicUtils();
		JSONObject data = new JSONObject();
		String error = "";
		String errorCode = "";
		String msg = "";
		if(loginVO==null || loginVO.getMBR_LV_ID()==null || loginVO.getMBR_LV_ID().equals("10")) {
			error = "1";
			errorCode = "1";
			msg = "다운로드진행불가:접근권한이없음";
    	} else {
			if(excel_type==null || excel_type.length()==0 || excel_label==null || excel_label.length()==0) {
				error = "0";
				errorCode = "0";
				msg = "다운로드진행불가:정보없음";
			} else {
				boolean isConfimLock = false;
				if(excel_type.equals("/eduadm/member/eduList.do")//전체보기_전체_엑셀다운로드 (구버전)
		    	|| excel_type.equals("/eduadm/member/eduListNew.do")//전체보기_전체_엑셀다운로드
		    	|| excel_type.equals("/eduadm/member/eduListLocgov.do")//지자체_전체보기_전체_엑셀다운로드
		    	) {	    
					isConfimLock = true;
	    			error = "0";
	    			errorCode = "0";
	    			msg = "100건 미만의 자료는 자동승인 됩니다.";	
		    	} else if(excel_type.equals("/eduadm/mbrhstry/list.do")//수강자목록(수강내역페이지)
		    	|| excel_type.equals("/eduadm/mbrhstry/list_prev.do")//수강자목록(수강내역페이지) 구버전 , 라인을 병합하지 않음
		    	) { 
		    		isConfimLock = true;
		    		error = "0";
		    		errorCode = "0";
		    		msg = "100건 미만의 자료는 자동승인 됩니다.";	
		    	} else if(excel_type.equals("/eduadm/report/excel_1_1.do") || excel_type.equals("/eduadm/report/excel_1_2.do")) { //보고서 - 이수증발급대장 
		    		error = "0";
		    		errorCode = "0";
	    			msg = "다운로드진행";
		    	} else if(excel_type.equals("/eduadm/report/excel_2_1.do") || excel_type.equals("/eduadm/report/excel_2_2.do")) { //보고서 - 결과보고서
		    		error = "0";
		    		errorCode = "0";
	    			msg = "다운로드진행";
		    	} else if(excel_type.equals("/adm/log/listRecEduadm.do")) { //접속자 로그 기록
		    		error = "0";
		    		errorCode = "0";
	    			msg = "다운로드진행";
		    	} else if(excel_type.equals("/adm/log/listSysEduadm.do")) { //시스템 로그 기록
		    		error = "0";
		    		errorCode = "0";
	    			msg = "다운로드진행";
		    	} else if(excel_type.equals("/adm/log/listMbrModEduadm.do")) { //회원정보수정 로그 기록
		    		error = "0";
		    		errorCode = "0";
	    			msg = "다운로드진행";
		    	} else if(excel_type.equals("/eduadm/board/esrequest/list.do")) { //교육대상자관리 - 문자신청리스트
		    		error = "0";
		    		errorCode = "0";
	    			msg = "다운로드진행";
		    	} else if(excel_type.equals("/eduadm/member/author_log.do")) { //관리자계정 리스트
		    		error = "0";
		    		errorCode = "0";
	    			msg = "다운로드진행";
		    	} else if(excel_type.equals("/eduadm/member/access_log.do")){ //관리자접속기록 리스트
		    		error = "0";
		    		errorCode = "0";
	    			msg = "다운로드진행";
		    	} else { //알수없는요청
		    		error = "1";
		    		errorCode = "2";
	    			msg = "다운로드진행불가:알수없는요청";
				}	
				if(isConfimLock) {
					List<CodeSetVO> list_sys_cd  = null;
			    	//코드값정보	
					{
						CodeSetVO mCodeSetVO = new CodeSetVO();
						mCodeSetVO.setCD_MASTER_ID("CID00010");
						list_sys_cd = codeSetService.get_codeset_list(mCodeSetVO);
					}	
					String rangeTime = "";
	    			String rangeTimeHour = "";
					String rangeTimeMin = "";
					String rangeTimeSec = "";
	    			String reqLimitCnt = "";
	    			String lockDateList = "";
	    			String workTimeStr = "";
	    			String workTimeEnd = "";
	    			for(CodeSetVO item_code : list_sys_cd) {
						if(item_code.getCD_ID().equals("SYS00001")) {//근무시간(시작)
							workTimeStr = item_code.getCD_DES();
						} else if(item_code.getCD_ID().equals("SYS00002")) {//근무시간(종료)
							workTimeEnd = item_code.getCD_DES();
						} else if(item_code.getCD_ID().equals("SYS00003")) {//별도공휴일지정
							lockDateList = item_code.getCD_DES();
						} else if(item_code.getCD_ID().equals("SYS00004")) {//제한시간(시:분:초)
							rangeTime = item_code.getCD_DES();
							rangeTimeHour = rangeTime.split(":")[0].replace("00", "");
							rangeTimeMin = rangeTime.split(":")[1].replace("00", "");
							rangeTimeSec = rangeTime.split(":")[2].replace("00", "");
						} else if(item_code.getCD_ID().equals("SYS00005")) {//초과횟수(제한시간 내)
							reqLimitCnt = item_code.getCD_DES();
						}
					}
	    			System.out.println(lockDateList);
					System.out.println(lockDateList);
					System.out.println(lockDateList);
					System.out.println(lockDateList);
					System.out.println(lockDateList);
					System.out.println(lockDateList);
	    			//공휴일 또는 지정날짜 인 경우 , 근무시간 외
	    			if(mPublicUtils.getCurrentTimeDayToString().equals("토") || mPublicUtils.getCurrentTimeDayToString().equals("일")
	    			|| lockDateList.contains(mPublicUtils.currentTime("yyyy-MM-dd"))
	    			|| mPublicUtils.dateCompare(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", 
	    					mPublicUtils.currentTime("yyyy-MM-dd")+" "+workTimeStr, "yyyy-MM-dd HH:mm:ss") == PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02
	    			|| mPublicUtils.dateCompare(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", 
	    					mPublicUtils.currentTime("yyyy-MM-dd")+" "+workTimeEnd, "yyyy-MM-dd HH:mm:ss") == PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02
	    			) {
	    				error = "0";
		    			errorCode = "3";
		    			msg = "관리자 승인은 공휴일 등을 제외한 근무시간내에 진행됩니다.";
					} else {
						DwldConfimFileVO chkLockDwldConfimFileVO = new DwldConfimFileVO();
		    			chkLockDwldConfimFileVO.setREG_LIMIT_CNT(reqLimitCnt);
		    			chkLockDwldConfimFileVO.setRANGE_TIME_HOUR(rangeTimeHour);
		    			chkLockDwldConfimFileVO.setRANGE_TIME_MIN(rangeTimeMin);
		    			chkLockDwldConfimFileVO.setRANGE_TIME_SEC(rangeTimeSec);
		    			chkLockDwldConfimFileVO.setREQ_MBR_ID(loginVO.getMBR_ID());
		    			chkLockDwldConfimFileVO.setATCH_FILE_ID("EXCEL_00000000000000");
		    			chkLockDwldConfimFileVO.setUNLOCK_FILE_SN("");
		    			boolean chkLock = fileMngService.get_dwld_req_lock(chkLockDwldConfimFileVO);
		    			if(chkLock) {
		    				error = "0";
			    			errorCode = "3";
			    			msg = mPublicUtils.getHMSLabel(rangeTime,"HH:mm:ss")+" 동안 "+reqLimitCnt+"회 이상 요청시 다운로드가 승인제로 전환됩니다.";
		    			}
					}
	    			
				}
			}		
    	}
		data.put("error", error);
		data.put("errorCode", errorCode);
		data.put("msg", msg);
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);    	
    	return null;    	
	}
	
	@RequestMapping(value = "/all/main/excel/down.do")
	public Object listExcelDownload(@RequestParam("excel_msg") String excel_msg,@RequestParam("excel_type") String excel_type,@RequestParam("excel_label") String excel_label,
			@RequestParam(value="CRS_GRP_CD_1",required=false) String CRS_GRP_CD_1,@RequestParam(value="CRS_GRP_CD_2",required=false) String CRS_GRP_CD_2,
			@ModelAttribute("eduMemberVO") EduMemberVO eduMemberVO,
			@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,@ModelAttribute("eduCurriculumVO") EduCurriculumVO eduCurriculumVO,
			@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO,
			@ModelAttribute("errorVO") ErrorVO errorVO, @ModelAttribute("logRecordVO") LogRecordVO logRecordVO,@ModelAttribute("logMemberModifyVO") LogMemberModifyVO mLogMemberModifyVO,
			@ModelAttribute("eduAdmSmsRequstVO") EduAdmSmsRequstVO eduAdmSmsRequstVO,
			@ModelAttribute("logAdmAuthVO") LogAdmAuthVO logAdmAuthVO,
			SessionStatus status,  HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		PublicUtils mPublicUtils = new PublicUtils();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder mbr_msg = new StringBuilder();
    	StringBuilder tbl_inf = new StringBuilder();
    	StringBuilder tbl_sn = new StringBuilder();
    	    	
    	FileVO saveFileVO = null;
    	boolean isMngrConfm = false;//관리자 승인 필수여부
    	boolean isFail = false;
    	boolean isChkComfirmMbrLv = false;	// 접근권한 허용여부 (false : 아무나, true : 관리자)
    	mbr_msg.append(excel_msg);
    	log_dscrp.append("[엑셀다운로드]");
		if(excel_type==null || excel_type.length()==0 || excel_label==null || excel_label.length()==0) {
			log_dscrp.append("[실패:비정상적인접근제한]");
			isFail = true;
		} else {
			try {
				boolean isExcelDownLoad = true;
	    		String currentTime = mPublicUtils.currentTime("yyyyMMddHHmmss");
		    	String excel_name = excel_label+"_"+currentTime+".xlsx";
		    	String excel_uniquekey = "";
		    	List<CodeSetVO> list_all_cd  = null;
		    	//코드값정보	
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
			  		list_all_cd = codeSetService.get_codeset_list(mCodeSetVO);
			  		model.addAttribute("list_all_cd",list_all_cd);
				}	
				//시스템코드값
				{
					CodeSetVO mCodeSetVO = new CodeSetVO();
					mCodeSetVO.setCD_MASTER_ID("CID00010");
					List<CodeSetVO> list_sys_cd = codeSetService.get_codeset_list(mCodeSetVO);
			  		model.addAttribute("list_sys_cd",list_sys_cd);
				}	
		    	if(excel_type.equals("/eduadm/member/eduList.do")//전체보기_전체_엑셀다운로드 (구버전)
		    	|| excel_type.equals("/eduadm/member/eduListNew.do")//전체보기_전체_엑셀다운로드
		    	|| excel_type.equals("/eduadm/member/eduListLocgov.do")//지자체명단_현행화_전체_엑셀다운로드
		    	) {
		    		log_dscrp.append("[승인요청]");
		    		isMngrConfm = true;		    		
		    		isChkComfirmMbrLv = true;
		    		excel_uniquekey = "MBR_ID";
		    		ModelMap modelData = (ModelMap)mEduMemberController.edu_member_edu_list(eduMemberVO, isExcelDownLoad, eduMemberVO.getSearchYear(),status, request, model);
		    		//승인제를 위한 처리
		    		saveFileVO = addDownloadConFirmFileInfo(excel_name,loginVO.getMBR_ID(),excel_msg,request);
		    		//End of 승인제를 위한 처리
		    		String year = eduMemberVO.getSearchYear();
		    		List<EduMemberVO> list = (List<EduMemberVO>)modelData.get("list");
		    		//회원상세정보조회를 위한 구간 , 회원교육이수여부를 위한 구간
		    		Map<String,List<Map<String,Object>>> list_dtl = new HashMap<String, List<Map<String,Object>>>();//회원상세정보
		    		Map<String,List<Map<String,Object>>> list_mbr_trgt = new HashMap<String, List<Map<String,Object>>>();//교육이수정보
		    		for(EduMemberVO item : list) {
		    			EduMemberVO chkEduMemberVO = new EduMemberVO();
		    			chkEduMemberVO.setMBR_ID(item.getMBR_ID());
		    			chkEduMemberVO.setUSE_AT(item.getUSE_AT());
		    			chkEduMemberVO.setMBR_ST(item.getMBR_ST());
		    			list_dtl.put(item.getMBR_ID(),mPublicUtils.convertListToMap(eduMemberService.get_edu_member_dtl_list(chkEduMemberVO)));
		    			chkEduMemberVO.setTRGT_YEAR(year);
		    			chkEduMemberVO.setUSE_AT("Y");
		    			list_mbr_trgt.put(item.getMBR_ID(), mPublicUtils.convertListToMap(eduMemberService.get_edu_member_target_all_list(chkEduMemberVO)));
		    		}
		    		
		    		//
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));
		    		model.addAttribute("list_dtl",list_dtl);
		    		model.addAttribute("list_mbr_trgt",list_mbr_trgt);
		    		if(!excel_type.equals("/eduadm/member/eduListLocgov.do")) {//지자체현행화 명단이 아니면
			    		if(modelData.get("MBR_TRGT_CD").equals("CIDN010200")) {//낚시터
			    			if(excel_type.equals("/eduadm/member/eduListNew.do")) {
			    				model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_STORE_MERGE);
			    			} else {
			    				model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_STORE);
			    			}
			    			log_dscrp.append("[낚시터업자요청건]");
			    			//excel_name = "낚시터업자_" + excel_name;
			    		} else if(modelData.get("MBR_TRGT_CD").equals("CIDN010300")) {//낚시어선
			    			if(excel_type.equals("/eduadm/member/eduListNew.do")) {
			    				model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_SHIP_MERGE);
			    			} else {
			    				model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_SHIP);
			    			}
			    			log_dscrp.append("[낚시어선업자요청건]");
			    			//excel_name = "낚시어선업자_" + excel_name;
			    		} else {
			    			if(excel_type.equals("/eduadm/member/eduListNew.do")) {
			    				model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_MERGE);
			    			} else {
			    				model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST);
			    			}
			    			log_dscrp.append("[교육업종전체요청건]");
			    		}
			    		
		    		} else {
	    				model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_LOCGOV);
	    				log_dscrp.append("[지제체명단현행화전체요청건]");
		    		}
		    	} else if(excel_type.equals("/eduadm/mbrhstry/list.do")//수강자목록(수강내역페이지)
		    	|| excel_type.equals("/eduadm/mbrhstry/list_prev.do")//수강자목록(수강내역페이지) 구버전 , 라인을 병합하지 않음
		    	) { 
		    		log_dscrp.append("[승인요청]");
		    		isMngrConfm = true;		
		    		isChkComfirmMbrLv = true;
		    		excel_uniquekey = "";
		    		ModelMap modelData = (ModelMap)mEduMyHistoryController.edu_history_list(isExcelDownLoad, eduMyHistoryVO, eduCurriculumVO, status, request, model);
		    		//승인제를 위한 처리
		    		saveFileVO = addDownloadConFirmFileInfo(excel_name,loginVO.getMBR_ID(),excel_msg,request);
		    		//End of 승인제를 위한 처리
		    		List<EduMyHistoryVO> list = (List<EduMyHistoryVO>)modelData.get("list");
		    		Map<String,List<Map<String,Object>>> list_dtl = new HashMap<String, List<Map<String,Object>>>();//회원상세정보
		    		for(EduMyHistoryVO item : list) {
		    			EduMemberVO chkEduMemberVO = new EduMemberVO();
		    			chkEduMemberVO.setMBR_ID(item.getMBR_ID());
		    			chkEduMemberVO.setUSE_AT("Y");
		    			list_dtl.put(item.getMBR_ID(),mPublicUtils.convertListToMap(eduMemberService.get_edu_member_dtl_list(chkEduMemberVO)));
		    		}
		    		excel_uniquekey = "MBR_ID";
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));
		    		model.addAttribute("list_dtl",list_dtl);
		    		if(excel_type.equals("/eduadm/mbrhstry/list_prev.do")) {
		    			model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MBRHSTRY_LIST_NONE_MERGE);
		    		} else {
		    			model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MBRHSTRY_LIST);
		    		}
		    		log_dscrp.append("["+excel_label+"_수강자목록건]");
		    	} else if(excel_type.equals("/eduadm/report/excel_1_1.do") || excel_type.equals("/eduadm/report/excel_1_2.do")) { //보고서 - 이수증발급대장 
		    		isChkComfirmMbrLv = true;
		    		String CRS_MBR_CD = "";
		    		if(excel_type.equals("/eduadm/report/excel_1_1.do")) {
		    			CRS_MBR_CD = "CIDN010200";
		    			model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_CERTIFICATE_LIST_STORE);
		    		} else {
		    			CRS_MBR_CD = "CIDN010300";
		    			model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_CERTIFICATE_LIST_SHIP);
		    		}		    		
		    		excel_uniquekey = "MBR_ID";
		    		eduCertificateVO.setCRS_MBR_CD(CRS_MBR_CD);
		    		eduCertificateVO.setNotUsedPagination(true);
		    		eduCertificateVO.setMBR_SIDO_CD(eduMemberVO.getMBR_SIDO_CD());
		    		eduCertificateVO.setMBR_SIGNGU_CD(eduMemberVO.getMBR_SIGNGU_CD());
		    		
		    		if(CRS_GRP_CD_1 != null && !CRS_GRP_CD_1.equals("")){
		    			eduCertificateVO.setCRS_GRP_CD(CRS_GRP_CD_1);
		    		} else {
		    			if(CRS_GRP_CD_2 != null && !CRS_GRP_CD_2.equals("")){
		    				eduCertificateVO.setCRS_GRP_CD(CRS_GRP_CD_2);	
		    			}
		    		}
		    		
		    		List<EduCertificateVO> list = eduCertificateService.get_edu_certificate_excel_list(eduCertificateVO);
		    		Map<String,List<Map<String,Object>>> list_dtl = new HashMap<String, List<Map<String,Object>>>();//회원상세정보
		    		for(EduCertificateVO item : list) {
		    			EduMemberVO chkEduMemberVO = new EduMemberVO();
		    			chkEduMemberVO.setMBR_ID(item.getMBR_ID());
		    			chkEduMemberVO.setUSE_AT("Y");
		    			chkEduMemberVO.setDTL_CD(CRS_MBR_CD);
		    			list_dtl.put(item.getMBR_ID(),mPublicUtils.convertListToMap(eduMemberService.get_edu_member_dtl_list(chkEduMemberVO)));
		    		}
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));
		    		model.addAttribute("list_dtl",list_dtl);
		    		model.addAttribute("searchYear",eduCertificateVO.getSearchYear());
		    		log_dscrp.append("[이수증발급대장]");
		    	} else if(excel_type.equals("/eduadm/report/excel_2_1.do") || excel_type.equals("/eduadm/report/excel_2_2.do")) { //보고서 - 결과보고서
		    		isChkComfirmMbrLv = true;
		    		String CRS_MBR_CD = "";
		    		if(excel_type.equals("/eduadm/report/excel_2_1.do")) {
		    			CRS_MBR_CD = "CIDN010200";
		    			model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MBRHSTRY_AREA_RESULT_STORE);
		    		} else {
		    			CRS_MBR_CD = "CIDN010300";
		    			model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MBRHSTRY_AREA_RESULT_SHIP);
		    		}		
		    		EgovMap prameters = new EgovMap();
		    		prameters.put("crsMbrCd", CRS_MBR_CD);
		    		prameters.put("searchYear", eduMemberVO.getSearchYear());
		    		List<EgovMap> list = (List<EgovMap>) eduMyHistoryService.get_edu_mbrhstry_area_result(prameters);
		    		model.addAttribute("list",list);	
		    		model.addAttribute("searchYear",eduMemberVO.getSearchYear());
		    		log_dscrp.append("[결과보고서]");
		    		
		    	} else if(excel_type.equals("/adm/log/listRecEduadm.do")) { //접속자 로그 기록
		    		
		    		log_dscrp.append("[승인요청]");
		    		isMngrConfm = true;		    		
		    		isChkComfirmMbrLv = true;
		    		//excel_uniquekey = "";
		    		//승인제를 위한 처리
		    		saveFileVO = addDownloadConFirmFileInfo(excel_name,loginVO.getMBR_ID(),excel_msg,request);
		    		//End of 승인제를 위한 처리
		    		
		    		model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.LOG_REC_LIST);
		    		String addWebLink = "Eduadm";
		    		ModelMap modelData = (ModelMap)mAdmMainController.adm_log_list_rec(isExcelDownLoad, logRecordVO, addWebLink, request, model);
		    		List<LogRecordVO> list = (List<LogRecordVO>)modelData.get("list");
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));	
		    		log_dscrp.append("[접속자로그기록]");
		    		
		    	} else if(excel_type.equals("/adm/log/listSysEduadm.do")) { //시스템 로그 기록
		    		isChkComfirmMbrLv = true;
		    		model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.LOG_SYS_LIST);
		    		String addWebLink = "Eduadm";
		    		ModelMap modelData = (ModelMap)mAdmMainController.adm_log_list_sys(isExcelDownLoad, errorVO, addWebLink, request, model);
		    		List<ErrorVO> list = (List<ErrorVO>)modelData.get("list");
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));	
		    		log_dscrp.append("[시스템로그기록]");
		    		
		    	} else if(excel_type.equals("/adm/log/listMbrModEduadm.do")) { //회원정보수정 로그 기록
		    		isChkComfirmMbrLv = true;
		    		model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.LOG_MBR_MOD_LIST);
		    		String addWebLink = "Eduadm";
		    		ModelMap modelData = (ModelMap)mAdmMainController.adm_log_list_mbr_mod(isExcelDownLoad, mLogMemberModifyVO, addWebLink, request, model);
		    		List<LogMemberModifyVO> list = (List<LogMemberModifyVO>)modelData.get("list");
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));	
		    		log_dscrp.append("[회원정보수정로그기록]");
		    		
		    	} else if(excel_type.equals("/eduadm/board/esrequest/list.do")) { //교육대상자관리 - 문자신청리스트
		    		model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_BOARD_ESREQUEST_LIST);
		    		ModelMap modelData = (ModelMap)mEduBoardController.eduadm_board_esrequest_list(isExcelDownLoad, eduAdmSmsRequstVO, "", model);
		    		List<EduAdmSmsRequstVO> list = (List<EduAdmSmsRequstVO>)modelData.get("list");
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));	
		    		log_dscrp.append("[회원정보수정로그기록]");
		    		
		    	} else if(excel_type.equals("/eduadm/member/author_log.do")) { //관리자권한기록 리스트
		    		isChkComfirmMbrLv = true;
		    		model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_AUTHOR_LOG);
		    		ModelMap modelData = (ModelMap)mEduMemberController.edu_member_author_log(logAdmAuthVO, isExcelDownLoad, request, model);
		    		List<LogAdmAuthVO> list = (List<LogAdmAuthVO>)modelData.get("list");
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));	
		    		log_dscrp.append("[관리자권한기록 리스트]");
		    	} else if(excel_type.equals("/eduadm/member/access_log.do")){
		    		isChkComfirmMbrLv = true;
		    		model.addAttribute("AllExcelBundleMerge",AllExcelDownLoadBundle.MERGE.LOG_REC_LIST);
		    		ModelMap modelData = (ModelMap)mEduMemberController.edu_member_acces_log(isExcelDownLoad, logRecordVO, request, model);
		    		List<LogRecordVO> list = (List<LogRecordVO>)modelData.get("list");
		    		model.addAttribute("list",mPublicUtils.convertListToMap(list));	
		    		log_dscrp.append("[관리자접속기록 리스트]");
		    		
		    	} else {
					log_dscrp.append("[알수없는요청]");
					isFail = true;
				}	
		    	model.addAttribute("excel_uniquekey",excel_uniquekey);
				model.addAttribute("excel_type",excel_type);
				model.addAttribute("excel_name",excel_name);
				model.addAttribute("excel_mngr_confim",isMngrConfm?"Y":"N");
				LOGGER.debug("[excel_type : " + excel_type+"]");
				LOGGER.debug("[excel_name : " + excel_name+"]");
				log_dscrp.append("[파일명:"+excel_name+"]");
								
			} catch(Exception e) {
	    		log_dscrp.append("[실패:처리오류]");
	    		log_msg.append("[실패:처리오류("+e.toString()+")]");
	    	}	    	
		}
		
		if(isChkComfirmMbrLv) {
			if(loginVO==null || loginVO.getMBR_LV_ID()==null || loginVO.getMBR_LV_ID().equals("10")) {
				LOGGER.debug("접근권한이없음");
				log_dscrp.append("[실패:접근권한이없음]");
				isFail = true;
	    	}
		}
		
    		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			//mEduLogRecordVO.encodingFromObjectToJson(resultVO);
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setMBR_MSG(mbr_msg.toString());
			mEduLogRecordVO.setLOG_TYPE("EXCEL_DOWN_LOAD");
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("faile log record");
		}
		
    	if(isFail) {
    		model.addAttribute("page_back_cnt", "-1");
    		return "adm/error/page_back";
    	} else {
    		try {    			
    			return new AllExcelDownLoad(propertyService,fileMngService,smsManagerService,eduMemberService,saveFileVO);
    		} catch(Exception el) {
    			
    			log_dscrp.append("[실패:엑셀파일생성오류]");
    			
    			try {	
    				/**
    				 * LOG RECORED (로그기록)
    				 * */
    				LogRecordVO mEduLogRecordVO = new LogRecordVO();
    				//mEduLogRecordVO.encodingFromObjectToJson(resultVO);
    				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
    				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
    				mEduLogRecordVO.setMBR_MSG(mbr_msg.toString());
    				mEduLogRecordVO.setLOG_TYPE("EXCEL_DOWN_LOAD");
    				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
    				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
    				mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
    				mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
    				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
    				logRecordService.set_log_data(mEduLogRecordVO,request);
    			} catch(Exception e) {
    				LOGGER.debug("faile log record");
    			}	
    			
    			model.addAttribute("page_back_cnt", "-1");
        		return "adm/error/page_back";
    		}
    	}
    	
	}
	
	//승인제 파일 정보 등록
	private FileVO addDownloadConFirmFileInfo(String filename, String regMbrId, String reqMsg, HttpServletRequest request) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		String atchFileStreFileNmLabel = "EXCEL_";
		String atchFileId = "EXCEL_00000000000000";
		FileVO saveFileVO = new FileVO();
		saveFileVO.setAtchFileId(atchFileId);
		int atchFileSn = fileMngService.getMaxFileSN(saveFileVO);
		saveFileVO = fileUtil.makeFileInfForConfirm(
				atchFileId,//파일코드
				atchFileStreFileNmLabel,//저장시라벨 
				atchFileSn,//파일번호
				"Globals.excelDownloadPath",//저장경로
				filename,//파일명 
				regMbrId,//신청자
				reqMsg,//신청사유 
				"1",//파일다운로드횟수제한(0:무제한)
				"1",//신청자한정파일다운로드
				(Map<String,Object>)request.getParameterMap()//조회조건기록용
				);
		fileMngService.insertDetailFileInfForConfirm(saveFileVO);
		LOGGER.debug("승인제 파일 정보 등록 완료 :" + saveFileVO.getAtchFileId());
		return saveFileVO;
	}
	

}


