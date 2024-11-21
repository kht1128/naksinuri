package egovframework.cti.callhstry.web;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginService;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.cti.callhstry.service.CtiCallHistoryService;
import egovframework.cti.callhstry.service.CtiCallHistoryVO;
import egovframework.cti.member.service.CtiMemberService;
import egovframework.cti.member.service.CtiMemberVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicFileMngUtil;
import egovframework.utils.PublicUtils;


@Controller
@EnableWebMvc
public class CtiCallHistoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CtiCallHistoryController.class);
		
	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	@Resource(name = "ctiMemberService")
	private CtiMemberService ctiMemberService;
	
	@Resource(name = "ctiCallHistoryService")
	private CtiCallHistoryService ctiCallHistoryService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	
	
	//해당 연락처의 최신 상담 이력 1건 조회
  	@RequestMapping(value="/cti/callhstry/last_info.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_callhstry_last_info(@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
  			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {	
  		  		
  		JSONObject data = new JSONObject();
  		try {
  			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			
			ctiCallHistoryVO = ctiCallHistoryService.get_cti_callhstry_last_info(ctiCallHistoryVO);
  			
			data.put("rawdata",mapper.writeValueAsString(ctiCallHistoryVO));
  			data.put("error", "0");
  			data.put("msg", "정상적으로 처리되었습니다.");
  		} catch(Exception e) {
  			data.put("error", "1");
  			data.put("msg", "처리 중 오류가 발생하였습니다.");	
  		}
  		
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
  	
  	
  	@RequestMapping(value="/cti/callhstry/default_info.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_callhstry_default_info(@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
  			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {	
  		  		
  		JSONObject data = new JSONObject();
  		try {
  			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			
			ctiCallHistoryVO = ctiCallHistoryService.get_cti_callhstry_default_info(ctiCallHistoryVO);
  			
			data.put("rawdata",mapper.writeValueAsString(ctiCallHistoryVO));
  			data.put("error", "0");
  			data.put("msg", "정상적으로 처리되었습니다.");
  		} catch(Exception e) {
  			data.put("error", "1");
  			data.put("msg", "처리 중 오류가 발생하였습니다.");	
  		}
  		
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}	  	
	
	
  	//관리자(CTI) 메인 상담 및 통화이력 리스트  ------------------------------------------------
  	@RequestMapping(value = "/cti/callhstry/list_ajax.do", method = RequestMethod.POST)
  	public ModelAndView ajax_cti_callhstry_list(@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		  		
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/callhstry/list_ajax");
  		
  		if(ctiCallHistoryVO.getSearchKeyword().length()==0 && ctiCallHistoryVO.getSearchRangeDate().length()==0) {
  			//초기 화면
  		} else {
  			
  		}
  		
  		if(ctiCallHistoryVO.getSearchRangeDate().length()!=0) {
  			String[] rangeDates = ctiCallHistoryVO.getSearchRangeDate().split("~");
  			for(int i=0; i<rangeDates.length; i++) {
  				if(rangeDates[i]!=null) {
  					String d = rangeDates[i].trim();
  					if(i == 0) ctiCallHistoryVO.setSearchStrDate(d + " 00:00:00");
  					if(i == 1) ctiCallHistoryVO.setSearchEndDate(d + " 23:59:59");
  				}
  			}
  		}
  		
		PaginationInfo paginationInfo = new PaginationInfo();	
		paginationInfo.setCurrentPageNo(ctiCallHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ctiCallHistoryVO.getPageUnit());
		paginationInfo.setPageSize(ctiCallHistoryVO.getPageSize());
		
		ctiCallHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ctiCallHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ctiCallHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<CtiCallHistoryVO> list = ctiCallHistoryService.get_cti_callhstry_list(ctiCallHistoryVO);		
		int totCnt = ctiCallHistoryService.get_cti_callhstry_list_totcnt(ctiCallHistoryVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
  		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		return mModelAndView;
		
  	}
  	
  	//관리자(CTI) 메인 상담 및 통화이력 리스트  ------------------------------------------------
  	@RequestMapping(value = "/cti/callhstry/list_ajax_popup.do", method = RequestMethod.POST)
  	public ModelAndView ajax_cti_callhstry_list_for_total(@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		  		
  		ctiCallHistoryVO.setPageUnit(5);
  		
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/callhstry/list_ajax_popup");
  		
		PaginationInfo paginationInfo = new PaginationInfo();	
		paginationInfo.setCurrentPageNo(ctiCallHistoryVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ctiCallHistoryVO.getPageUnit());
		paginationInfo.setPageSize(ctiCallHistoryVO.getPageSize());
		
		ctiCallHistoryVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ctiCallHistoryVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ctiCallHistoryVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<CtiCallHistoryVO> list = ctiCallHistoryService.get_cti_callhstry_list(ctiCallHistoryVO);		
		int totCnt = ctiCallHistoryService.get_cti_callhstry_list_totcnt(ctiCallHistoryVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
  		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("MBR_ID",ctiCallHistoryVO.getMBR_ID());
		model.addAttribute("MBR_HP",ctiCallHistoryVO.getMBR_HP());
		model.addAttribute("CUSTOM_UNIQ_KEY",ctiCallHistoryVO.getCUSTOM_UNIQ_KEY());
		
		return mModelAndView;
		
  	}
  	
  	//CTI 통화이력 등록 로직
  	@RequestMapping(value="/cti/callhstry/write_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_data_write_act(@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
  			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		System.out.println("인서트확인");
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		String LOG_UPD_MSG = "";
		/** End of LOG RECORED (로그기록) 선언부 */
		log_dscrp.append("[CTI-통화이력-신규]");
  		
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
  		JSONObject data = new JSONObject();
  		try {
  			//스태프 정보
  			CtiMemberVO staff = new CtiMemberVO();
  			staff.setMBR_ID(loginVO.getMBR_ID());
  			staff = ctiMemberService.get_cti_staff_info(staff);
  			
  			if(ctiCallHistoryVO.getMBR_HP().equals("")){
  				log_dscrp.append("[전화번호 없음]");
  			} else {
  			
  				//연락처 조회 및 이력 세팅
	  			CtiMemberVO ctiMemberVO = new CtiMemberVO();
	  			ctiMemberVO.setCTI_MBR_HP(ctiCallHistoryVO.getMBR_HP().trim().replace("-", ""));
	  			ctiMemberVO = ctiMemberService.get_mbr_info_with_scan(ctiMemberVO);  			
	  			if(ctiMemberVO!=null) {
	  				LOGGER.debug("일치하는 정보가 있음");
	  				log_dscrp.append("[일치하는정보있음]");
	  				if(ctiMemberVO.getIS_JOIN_MBR().equals("Y")) {//회원테이블존재
	  					
	  					if(ctiMemberVO.getCTI_MBR_HP() != null && ctiMemberVO.getCTI_MBR_HP().length() != 0){
	  						ctiCallHistoryVO.setMBR_ID(ctiMemberVO.getMBR_ID());
		  					log_dscrp.append("[회원정보있음]");
	  					} else {
	  						LOGGER.debug("회원정보 있으나 전화번호가 없음 ");
	  						log_dscrp.append("[회원정보있음-전화번호 없음]");
	  					}
	  					
	  					
	  				} else {
	  					if(ctiMemberVO.getIS_CTI_MBR().equals("Y")) {//CTI테이블존재
	  						log_dscrp.append("[CTI에만존재:유지]");  		  				  						
	  	  				} else {
	  	  					log_dscrp.append("[CTI에도정보없음:신규추가가필요함("+ctiCallHistoryVO.getMBR_HP()+"):통화종료후상담기록시작성됨]");
	  	  				}
	  				}
	  			} else {
	  				LOGGER.debug("존재하는 정보가 없음");
	  				log_dscrp.append("[일치하는정보없음:CTI신규추가가필요함("+ctiCallHistoryVO.getMBR_HP()+"):통화종료후상담기록시작성됨]");
	  			} 
  			
  			}
  			  			
  			ctiCallHistoryVO.setMBR_HP(ctiCallHistoryVO.getMBR_HP().trim().replace("-", ""));
  			ctiCallHistoryVO.setHCALL_GUBUN_1(ctiCallHistoryVO.getHCALL_IVR_CD());
  			ctiCallHistoryVO.setSTAFF_MBR_ID(staff.getMBR_ID());
  			ctiCallHistoryVO.setREG_MBR_ID(staff.getMBR_ID());
  			ctiCallHistoryVO.setUPD_MBR_ID(staff.getMBR_ID());
  			ctiCallHistoryVO.setEXTNO(staff.getCTI_TEL_NO());
  			System.out.println("인서트확인2");
  			String insertId = ctiCallHistoryService.set_cti_callhstry_reg(ctiCallHistoryVO);
  			
  			System.out.println("인서트확인3");
  			
  			
  			tbl_inf.append("CTI_CALL_HSTRY_TB");
  	  		tbl_sn.append("");
	  	  	log_msg.append(mLogRecordVO.encodingFromObjectToJson(ctiCallHistoryVO));
	  	  	LOG_UPD_MSG = "[통화이력자동저장]";
	  	  
  			data.put("error", "0");
  			data.put("msg", "정상적으로 처리되었습니다.");
  		} catch(Exception e) {
  			data.put("error", "1");
  			data.put("msg", "처리 중 오류가 발생하였습니다.");	
  		}
  		
  		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			mLogRecordVO.setMBR_MSG(LOG_UPD_MSG);
			logRecordService.set_log_data(mLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
  		
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}	
  	
  	
  	//CTI 통화이력 직접등록 로직 
  	@RequestMapping(value="/cti/callhstry/write_direct_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_data_write_direct_act(@RequestParam(value="IS_ONLY_CTI_MBR",required=false) Boolean IS_ONLY_CTI_MBR,
		@ModelAttribute("ctiMemberVO") CtiMemberVO ctiMemberVO,
		@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
		MultipartHttpServletRequest multiRequest,
		HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		
  		PublicUtils mPublicUtils = new PublicUtils();
  		
  		System.out.println("고광훈확인-cti/callhstry/write_direct_act.do");
  		
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		String LOG_UPD_MSG = "";
		/** End of LOG RECORED (로그기록) 선언부 */
		log_dscrp.append("[CTI-통화이력-직접등록]");
  		
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
  		JSONObject data = new JSONObject();
  		try {
	  			
  	  		//스태프 정보
  	  		CtiMemberVO ctiStaffMemberVO = new CtiMemberVO();
  	  		ctiStaffMemberVO.setMBR_ID(loginVO.getMBR_ID());
  	  		ctiStaffMemberVO = ctiMemberService.get_cti_staff_info(ctiStaffMemberVO);
  	  		//End of 스태프 정보
  	  			  	  			
  	  		CtiCallHistoryVO updCtiCallHistoryVO = new CtiCallHistoryVO();
		  	updCtiCallHistoryVO.setHCALL_IMPRT(ctiCallHistoryVO.getHCALL_IMPRT());
		  	updCtiCallHistoryVO.setHCALL_GUBUN_1(ctiCallHistoryVO.getHCALL_GUBUN_1());
		  	updCtiCallHistoryVO.setHCALL_GUBUN_2(ctiCallHistoryVO.getHCALL_GUBUN_2());
		  	updCtiCallHistoryVO.setHCALL_CONT(ctiCallHistoryVO.getHCALL_CONT());
		  	updCtiCallHistoryVO.setHCALL_MEMO(ctiCallHistoryVO.getHCALL_MEMO());
		  	updCtiCallHistoryVO.setREG_MBR_ID(MASTER_MBR_ID);
		  	updCtiCallHistoryVO.setUPD_MBR_ID(MASTER_MBR_ID);
		  	updCtiCallHistoryVO.setMBR_ID(ctiMemberVO.getMBR_ID());
		  	updCtiCallHistoryVO.setMBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
		  	updCtiCallHistoryVO.setHCALL_R_TEL(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
		  	updCtiCallHistoryVO.setHCALL_S_TEL(ctiStaffMemberVO.getCTI_TEL_NO());
		  	updCtiCallHistoryVO.setHCALL_TYPE("직접등록");
		  	updCtiCallHistoryVO.setHCALL_ST("");//콜상태
		  	updCtiCallHistoryVO.setHCALL_DT(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
		  	updCtiCallHistoryVO.setCALL_CD(ctiCallHistoryVO.getCUSTOM_UNIQ_KEY());
		  	updCtiCallHistoryVO.setSTAFF_MBR_ID(ctiStaffMemberVO.getMBR_ID());
		  	updCtiCallHistoryVO.setEXTNO(ctiStaffMemberVO.getCTI_TEL_NO());
		  	updCtiCallHistoryVO.setHCALL_TIME(ctiCallHistoryVO.getHCALL_TIME());
		  	updCtiCallHistoryVO.setHCALL_READY_TIME(ctiCallHistoryVO.getHCALL_READY_TIME());
		  	updCtiCallHistoryVO.setCALL_LOG_MSG(ctiCallHistoryVO.getCALL_LOG_MSG());
		  	updCtiCallHistoryVO.setHCALL_AGREE_1(ctiCallHistoryVO.getHCALL_AGREE_1());
		  	updCtiCallHistoryVO.setHCALL_AGREE_2(ctiCallHistoryVO.getHCALL_AGREE_2());
		  	updCtiCallHistoryVO.setHCALL_IVR(ctiCallHistoryVO.getHCALL_IVR());
		  	
		  	//첨부 파일 검증 및 저장
	  	  	String _atchFileId1 = "";
			PublicFileMngUtil mPublicFileMngUtil = new PublicFileMngUtil(fileMngService,fileUtil);
	  	  	Map<String, MultipartFile> mainImgfile1 = mPublicFileMngUtil.extractinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_jegprsn_reqstdoc",
					});
	  	  	Map<String, MultipartFile> mainImgfile2 = mPublicFileMngUtil.extractinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_compl_reqstdoc",
					});
	  	  	{//법인사업장 팩스 신청서
				Map fresult = mPublicFileMngUtil.chkFileUpdate(
						mainImgfile1, //업로드 파일 목록
						_atchFileId1, //첨부파일아이디(ATCH_FILE_ID) 
						"0", //첨부파일아이디 번호(FILE_SN)
						"CTI_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
					log_dscrp.append("[법인사업장팩스신청서첨부:실패]");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					log_dscrp.append("[법인사업장팩스신청서첨부:파일오류]");
				} else { //정상적으로 처리됨.
					_atchFileId1 = fresult.get("atchFileId").toString();	
					log_dscrp.append("[법인사업장팩스신청서첨부:완료]");
				}
			}
	  	  	String _atchFileId2 = "";
	  	  	{//이수증발급 팩스 신청서
				Map fresult = mPublicFileMngUtil.chkFileUpdate(
						mainImgfile2, //업로드 파일 목록
						_atchFileId2, //첨부파일아이디(ATCH_FILE_ID) 
						"0", //첨부파일아이디 번호(FILE_SN)
						"CTI_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
					log_dscrp.append("[이수증발급팩스신청서첨부:실패]");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					log_dscrp.append("[이수증발급팩스신청서첨부:파일오류]");
				} else { //정상적으로 처리됨.
					_atchFileId2 = fresult.get("atchFileId").toString();	
					log_dscrp.append("[이수증발급팩스신청서첨부:완료]");
				}
			}
	  	  	LOGGER.debug("법인사업장 팩스 신청서 첨부파일 번호 : " + _atchFileId1);
	  	  	LOGGER.debug("이수증발급 팩스 신청서 첨부파일 번호 : " + _atchFileId2);
	  	  	updCtiCallHistoryVO.setHCALL_FILE_1(_atchFileId1);
	  	  	updCtiCallHistoryVO.setHCALL_FILE_2(_atchFileId2);
	  	  	//End of 첨부 파일 검증 저장
	  	  	
  	  		ctiCallHistoryService.set_cti_callhstry_reg(updCtiCallHistoryVO);
  	  			
	  	  	tbl_inf.append("CTI_CALL_HSTRY_TB");
	  		tbl_sn.append("");
	  		log_msg.append(mLogRecordVO.encodingFromObjectToJson(ctiMemberVO));
	  		log_msg.append(mLogRecordVO.encodingFromObjectToJson(ctiCallHistoryVO));
	  		log_msg.append(mLogRecordVO.encodingFromObjectToJson(updCtiCallHistoryVO));
	  		LOG_UPD_MSG = "[통화상담이력직접등록]";
	  		
  	  		//CTI전용회원 등록 여부 검증
  	  		boolean isCtiNewMember = false;
  	  		boolean isCtiUpdMember = false;
	  	  	CtiMemberVO chkCtiMemberVO = new CtiMemberVO();
	  	  	chkCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
	  	  	chkCtiMemberVO = ctiMemberService.get_mbr_info_with_scan(chkCtiMemberVO);  			
  			if(chkCtiMemberVO!=null) {
  				if(chkCtiMemberVO.getIS_JOIN_MBR().equals("Y")) {
  					//회원테이블존재
  					LOGGER.debug("회원테이블존재");
  					log_dscrp.append("[낚시누리회원]");
  				} else {
  					if(chkCtiMemberVO.getIS_CTI_MBR().equals("Y")) {
  						//CTI테이블존재
  						LOGGER.debug("CTI테이블존재");
	  					log_dscrp.append("[CTI전용회원]");
	  					if(IS_ONLY_CTI_MBR) {
	  						isCtiUpdMember = true;	
	  					}
  					} else {
  						LOGGER.debug("CTI전용회원신규등록");
  						log_dscrp.append("[CTI전용회원으로등록함]");
  						isCtiNewMember = true;
  					}
  				}
  			} else {
  				log_dscrp.append("[CTI전용회원으로등록함]");
  				isCtiNewMember = true;
  			}
  	  		if(isCtiNewMember) {
	  			CtiMemberVO newCtiMemberVO = new CtiMemberVO();
				newCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
  				newCtiMemberVO.setMBR_ID(ctiMemberVO.getMBR_ID());
  				newCtiMemberVO.setCTI_MBR_NM(ctiMemberVO.getCTI_MBR_NM());
  				newCtiMemberVO.setCTI_MBR_BIRTH(ctiMemberVO.getCTI_MBR_BIRTH());
  				newCtiMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
  				newCtiMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
	  			ctiMemberService.set_cti_mbr_info_reg(newCtiMemberVO);
	  			
	  			tbl_inf.append(",CTI_MBR_TB");
	  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(newCtiMemberVO));
	  			
  	  		}
  	  		if(isCtiUpdMember) {
  	  			CtiMemberVO updCtiMemberVO = new CtiMemberVO();
				updCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
			  	updCtiMemberVO.setMBR_ID(ctiMemberVO.getMBR_ID());
			  	updCtiMemberVO.setCTI_MBR_NM(ctiMemberVO.getCTI_MBR_NM());
				updCtiMemberVO.setCTI_MBR_BIRTH(ctiMemberVO.getCTI_MBR_BIRTH());
				updCtiMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
				ctiMemberService.set_cti_mbr_info_mod(updCtiMemberVO);
				
				tbl_inf.append(",CTI_MBR_TB");
	  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(updCtiMemberVO));
  	  		}
  	  		//End of CTI전용회원 등록 여부 검증
  	  		
	  	  	
	  	  	log_dscrp.append("[처리완료]");
	  	  	data.put("error", "0");
	  	  	data.put("msg", "정상적으로 처리되었습니다.");	  	 
  	  		  			
  		} catch(Exception e) {
  			e.toString();
  			log_dscrp.append("[에러:처리실패("+e.toString()+")]");
  			data.put("error", "1");
  			data.put("msg", "처리 중 오류가 발생하였습니다.");	
  		}
  		
  		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mLogRecordVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
			mLogRecordVO.setMBR_MSG(LOG_UPD_MSG);
			logRecordService.set_log_data(mLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
  		
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}


  	//CTI 통화이력 통화건 등록 로직 
  	@RequestMapping(value="/cti/callhstry/write_new_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_data_write_new_act(@RequestParam(value="IS_ONLY_CTI_MBR",required=false) Boolean IS_ONLY_CTI_MBR,
  		@ModelAttribute("ctiMemberVO") CtiMemberVO ctiMemberVO,
		@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
		MultipartHttpServletRequest multiRequest,
		HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		
  		System.out.println("통화이력등록");
  		
  		PublicUtils mPublicUtils = new PublicUtils();
  		
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		String LOG_UPD_MSG = "";
		/** End of LOG RECORED (로그기록) 선언부 */
		log_dscrp.append("[CTI-통화이력-통화건등록]");
  		
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
  		JSONObject data = new JSONObject();
  		try {
  			CtiCallHistoryVO chkCtiCallHistoryVO = new CtiCallHistoryVO();
  	  		if(ctiCallHistoryVO!=null && ctiCallHistoryVO.getCALL_CD()!=null && ctiCallHistoryVO.getCALL_CD().length()!=0) {
  	  			chkCtiCallHistoryVO.setCALL_CD(ctiCallHistoryVO.getCALL_CD());
  	  			chkCtiCallHistoryVO = ctiCallHistoryService.get_cti_callhstry_info(chkCtiCallHistoryVO);
  	  			
	  	  		if(chkCtiCallHistoryVO!=null && chkCtiCallHistoryVO.getCALL_CD()!=null && chkCtiCallHistoryVO.getCALL_CD().length()!=0) {
		  	  		
		  	  		tbl_inf.append("CTI_CALL_HSTRY_TB");
		  	  		tbl_sn.append("");
			  	  	log_msg.append(mLogRecordVO.encodingFromObjectToJson(ctiCallHistoryVO));
	  	  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(chkCtiCallHistoryVO));
	  	  			LOG_UPD_MSG = "[통화매칭건상담이력반영]";
	  	  			
		  	  		//스태프 정보
		  	  		CtiMemberVO ctiStaffMemberVO = new CtiMemberVO();
		  	  		ctiStaffMemberVO.setMBR_ID(loginVO.getMBR_ID());
		  	  		ctiStaffMemberVO = ctiMemberService.get_cti_staff_info(ctiStaffMemberVO);
		  	  		//End of 스태프 정보
		  	  		
		  	  		//회원정보에 연락처 갱신 여부 처리
		  	  		boolean isJoinUpdMember = false;
			  	  	if(ctiMemberVO.getCTI_MBR_HP_UPD_YN()!=null && ctiMemberVO.getCTI_MBR_HP_UPD_YN().equals("Y")) {
			  	  		log_dscrp.append("[회원정보연락처변경처리진행]");
			  	  		EduMemberVO chkEduMemberVO = new EduMemberVO();
			  	  		chkEduMemberVO.setMBR_ID(ctiMemberVO.getMBR_ID());
			  			chkEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);
			  			if(chkEduMemberVO.getMBR_ID()==null || chkEduMemberVO.getMBR_ID().length()==0) {
			  				log_dscrp.append("[존재하지 않는 회원정보를 요청함]");
			  			} else {
			  				if(chkEduMemberVO.getMBR_HP().trim().replace("-", "").equals(ctiMemberVO.getCTI_MBR_HP_CHK().trim().replace("-", ""))) {
			  					log_dscrp.append("[회원정보에연락처가동일하여반영안함]");
			  				} else {
			  					isJoinUpdMember = true;
				  				EduMemberVO updEduMemberVO = new EduMemberVO();
				  				updEduMemberVO.setMBR_HP(ctiMemberVO.getCTI_MBR_HP_CHK().trim().replace("-", ""));
				  				updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
				  				updEduMemberVO.setMBR_ADDR2(chkEduMemberVO.getMBR_ADDR2());
				  				updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
								eduMemberService.set_edu_member_mod(updEduMemberVO);
								
								EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);
				  				//사용자사유로그기록
								{
									logRecordService.set_log_mbr_mod_data("MBR_TB","수정",ctiCallHistoryVO.getLOG_UPD_MSG(),realEduMemberVO.getMBR_ID(),realEduMemberVO.getMBR_NM(),chkEduMemberVO,realEduMemberVO,loginVO,request);
								}
				  				log_dscrp.append("[회원정보에연락처를갱신함]");
				  				//CTI LOG 기록
				  	  			{
				  	  				logRecordService.set_log_cti_data("연락처변경", log_dscrp.toString(), log_msg.toString(), ctiCallHistoryVO.getLOG_UPD_MSG(), chkEduMemberVO, realEduMemberVO, loginVO, request);
				  	  			}
				  	  			//End of CTI LOG 기록
			  				}
			  			}
			  	  	}
			  	  	//End of 회원정보에 연락처 갱신 여부 처리
			  	  	
			  	  	//CTI전용회원 등록 여부 검증
		  	  		boolean isCtiNewMember = false;
		  	  		boolean isCtiUpdMember = false;
			  	  	CtiMemberVO chkCtiMemberVO = new CtiMemberVO();
			  	  	chkCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
			  	  	chkCtiMemberVO = ctiMemberService.get_mbr_info_with_scan(chkCtiMemberVO);  			
		  			if(chkCtiMemberVO!=null) {
		  				if(chkCtiMemberVO.getIS_JOIN_MBR().equals("Y")) {
		  					//회원테이블존재
		  					LOGGER.debug("회원테이블존재");
		  					log_dscrp.append("[낚시누리회원]");
		  				} else {
		  					if(chkCtiMemberVO.getIS_CTI_MBR().equals("Y")) {
		  						//CTI테이블존재
		  						LOGGER.debug("CTI테이블존재");
			  					log_dscrp.append("[CTI전용회원]");
			  					if(IS_ONLY_CTI_MBR) {
			  						isCtiUpdMember = true;	
			  					}
		  					} else {
		  						LOGGER.debug("CTI전용회원신규등록");
		  						log_dscrp.append("[CTI전용회원으로등록함]");
		  						isCtiNewMember = true;
		  					}
		  				}
		  			} else {
		  				log_dscrp.append("[CTI전용회원으로등록함]");
		  				isCtiNewMember = true;
		  			}
		  	  		if(isCtiNewMember) {
			  			CtiMemberVO newCtiMemberVO = new CtiMemberVO();
	  					newCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
		  				newCtiMemberVO.setMBR_ID(ctiMemberVO.getMBR_ID());
		  				newCtiMemberVO.setCTI_MBR_NM(ctiMemberVO.getCTI_MBR_NM());
		  				newCtiMemberVO.setCTI_MBR_BIRTH(ctiMemberVO.getCTI_MBR_BIRTH());
		  				newCtiMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
		  				newCtiMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
			  			ctiMemberService.set_cti_mbr_info_reg(newCtiMemberVO);
			  			
			  			tbl_inf.append(",CTI_MBR_TB");
			  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(newCtiMemberVO));
		  	  		}
			  	  	if(isCtiUpdMember) {
		  	  			CtiMemberVO updCtiMemberVO = new CtiMemberVO();
						updCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
					  	updCtiMemberVO.setMBR_ID(ctiMemberVO.getMBR_ID());
					  	updCtiMemberVO.setCTI_MBR_NM(ctiMemberVO.getCTI_MBR_NM());
						updCtiMemberVO.setCTI_MBR_BIRTH(ctiMemberVO.getCTI_MBR_BIRTH());
						updCtiMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
						ctiMemberService.set_cti_mbr_info_mod(updCtiMemberVO);
						
						tbl_inf.append(",CTI_MBR_TB");
			  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(updCtiMemberVO));
		  	  		}
		  	  		//End of CTI전용회원 등록 여부 검증
		  	  			  	  			
		  	  		CtiCallHistoryVO updCtiCallHistoryVO = new CtiCallHistoryVO();
			  	  	updCtiCallHistoryVO.setCALL_CD(ctiCallHistoryVO.getCALL_CD());//key
				  	updCtiCallHistoryVO.setHCALL_IMPRT(ctiCallHistoryVO.getHCALL_IMPRT());
				  	updCtiCallHistoryVO.setHCALL_GUBUN_1(ctiCallHistoryVO.getHCALL_GUBUN_1());
				  	updCtiCallHistoryVO.setHCALL_GUBUN_2(ctiCallHistoryVO.getHCALL_GUBUN_2());
				  	updCtiCallHistoryVO.setHCALL_CONT(ctiCallHistoryVO.getHCALL_CONT());
				  	updCtiCallHistoryVO.setHCALL_MEMO(ctiCallHistoryVO.getHCALL_MEMO());
				  	updCtiCallHistoryVO.setUPD_MBR_ID(MASTER_MBR_ID);
				  	updCtiCallHistoryVO.setMBR_ID(ctiMemberVO.getMBR_ID());
				  	String CTI_MBR_HP = "";
				  	if(isJoinUpdMember) {
				  		CTI_MBR_HP = ctiMemberVO.getCTI_MBR_HP_CHK().trim().replace("-", "");
				  	} else {
				  		CTI_MBR_HP = ctiMemberVO.getCTI_MBR_HP().trim().replace("-", "");	
				  	}
				  	updCtiCallHistoryVO.setMBR_HP(CTI_MBR_HP);
				  	if(chkCtiCallHistoryVO.getHCALL_TYPE().equals("발신")) {
				  		updCtiCallHistoryVO.setHCALL_R_TEL(CTI_MBR_HP);	
				  	} else if(chkCtiCallHistoryVO.getHCALL_TYPE().equals("수신")) {
				  		updCtiCallHistoryVO.setHCALL_S_TEL(CTI_MBR_HP);	
				  	} else {
				  		updCtiCallHistoryVO.setHCALL_R_TEL(CTI_MBR_HP);
				  	}
				  	//updCtiCallHistoryVO.setSTAFF_MBR_ID(ctiStaffMemberVO.getMBR_ID());
				  	//updCtiCallHistoryVO.setSTAFF_MBR_NM(ctiStaffMemberVO.getCTI_MBR_NM());
				  	
				  //첨부 파일 검증 및 저장
			  	  	String _atchFileId1 = chkCtiCallHistoryVO.getHCALL_FILE_1();
					PublicFileMngUtil mPublicFileMngUtil = new PublicFileMngUtil(fileMngService,fileUtil);
			  	  	Map<String, MultipartFile> mainImgfile1 = mPublicFileMngUtil.extractinputFileName(
							multiRequest,
							new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
								"file_jegprsn_reqstdoc",
							});
			  	  	Map<String, MultipartFile> mainImgfile2 = mPublicFileMngUtil.extractinputFileName(
							multiRequest,
							new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
								"file_compl_reqstdoc",
							});
			  	  	{//법인사업장 팩스 신청서
						Map fresult = mPublicFileMngUtil.chkFileUpdate(
								mainImgfile1, //업로드 파일 목록
								_atchFileId1, //첨부파일아이디(ATCH_FILE_ID) 
								"0", //첨부파일아이디 번호(FILE_SN)
								"CTI_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
							log_dscrp.append("[법인사업장팩스신청서첨부:실패]");
						} else if(fresult.get("error").equals("2")) {
							LOGGER.debug("파일 검증 실패");
							log_dscrp.append("[법인사업장팩스신청서첨부:파일오류]");
						} else { //정상적으로 처리됨.
							_atchFileId1 = fresult.get("atchFileId").toString();
							log_dscrp.append("[법인사업장팩스신청서첨부:완료]");
						}
					}
			  	  	String _atchFileId2 = chkCtiCallHistoryVO.getHCALL_FILE_2();
			  	  	{//이수증발급 팩스 신청서
						Map fresult = mPublicFileMngUtil.chkFileUpdate(
								mainImgfile2, //업로드 파일 목록
								_atchFileId2, //첨부파일아이디(ATCH_FILE_ID) 
								"0", //첨부파일아이디 번호(FILE_SN)
								"CTI_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
							log_dscrp.append("[이수증발급팩스신청서첨부:실패]");
						} else if(fresult.get("error").equals("2")) {
							LOGGER.debug("파일 검증 실패");
							log_dscrp.append("[이수증발급팩스신청서첨부:파일오류]");
						} else { //정상적으로 처리됨.
							_atchFileId2 = fresult.get("atchFileId").toString();
							log_dscrp.append("[이수증발급팩스신청서첨부:완료]");
						}
					}
			  	  	LOGGER.debug("법인사업장 팩스 신청서 첨부파일 번호 : " + _atchFileId1);
			  	  	LOGGER.debug("이수증발급 팩스 신청서 첨부파일 번호 : " + _atchFileId2);
			  	  	updCtiCallHistoryVO.setHCALL_FILE_1(_atchFileId1);
			  	  	updCtiCallHistoryVO.setHCALL_FILE_2(_atchFileId2);
			  	  	//End of 첨부 파일 검증 저장
			  	  	
		  	  		ctiCallHistoryService.set_cti_callhstry_info_mod(updCtiCallHistoryVO);
		  	  		  	  	
			  	  	
			  	  	log_dscrp.append("[처리완료]");
			  	  	data.put("error", "0");
		  			data.put("msg", "정상적으로 처리되었습니다.");
		  	  				  	  		
	  	  		} else {
	  	  			log_dscrp.append("[에러:존재하지않는정보를요청함]");
		  	  		data.put("error", "2");
		  			data.put("msg", "존재하지 않는 정보입니다.");
	  	  		}
  	  			
  	  		} else {
  	  			log_dscrp.append("[에러:파라미터부족상태로요청함]");
	  	  		data.put("error", "1");
	  			data.put("msg", "검증 정보가 부족합니다.");
  	  		}
  	  		  			
  		} catch(Exception e) {
  			log_dscrp.append("[에러:처리실패("+e.toString()+")]");
  			data.put("error", "1");
  			data.put("msg", "처리 중 오류가 발생하였습니다.");	
  		}
  		
  		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mLogRecordVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
			mLogRecordVO.setMBR_MSG(LOG_UPD_MSG);
			logRecordService.set_log_data(mLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
  		
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
  	
  	
  	//CTI 통화이력 수정 로직 
  	@RequestMapping(value="/cti/callhstry/modify_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_data_modify_act(@RequestParam(value="IS_ONLY_CTI_MBR",required=false) Boolean IS_ONLY_CTI_MBR,
  		@ModelAttribute("ctiMemberVO") CtiMemberVO ctiMemberVO,
		@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
		MultipartHttpServletRequest multiRequest, 
		HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
   		
  		PublicUtils mPublicUtils = new PublicUtils();
  		
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		String LOG_UPD_MSG = "";
		/** End of LOG RECORED (로그기록) 선언부 */
		log_dscrp.append("[CTI-통화이력-수정]");
  		
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
  		JSONObject data = new JSONObject();
  		try {
  			CtiCallHistoryVO chkCtiCallHistoryVO = new CtiCallHistoryVO();
  	  		if(ctiCallHistoryVO!=null && ctiCallHistoryVO.getHCALL_SN()!=null && ctiCallHistoryVO.getHCALL_SN().length()!=0) {
  	  			chkCtiCallHistoryVO.setHCALL_SN(ctiCallHistoryVO.getHCALL_SN());
  	  			chkCtiCallHistoryVO = ctiCallHistoryService.get_cti_callhstry_info(chkCtiCallHistoryVO);
  	  			
	  	  		if(chkCtiCallHistoryVO!=null && chkCtiCallHistoryVO.getHCALL_SN()!=null && chkCtiCallHistoryVO.getHCALL_SN().length()!=0) {
	  	  		
	  	  			tbl_inf.append("CTI_CALL_HSTRY_TB");
		  	  		tbl_sn.append(ctiCallHistoryVO.getHCALL_SN());
			  	  	log_msg.append(mLogRecordVO.encodingFromObjectToJson(ctiCallHistoryVO));
	  	  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(chkCtiCallHistoryVO));
	  	  			LOG_UPD_MSG = "[통화상담이력내용수정]";
	  	  			
		  	  		//스태프 정보
		  	  		CtiMemberVO ctiStaffMemberVO = new CtiMemberVO();
		  	  		ctiStaffMemberVO.setMBR_ID(loginVO.getMBR_ID());
		  	  		ctiStaffMemberVO = ctiMemberService.get_cti_staff_info(ctiStaffMemberVO);
		  	  		//End of 스태프 정보
		  	  		
		  	  		//회원정보에 연락처 갱신 여부 처리
		  	  		boolean isJoinUpdMember = false;
			  	  	if(ctiMemberVO.getCTI_MBR_HP_UPD_YN()!=null && ctiMemberVO.getCTI_MBR_HP_UPD_YN().equals("Y")) {
			  	  		log_dscrp.append("[회원정보연락처변경처리진행]");
			  	  		EduMemberVO chkEduMemberVO = new EduMemberVO();
			  	  		chkEduMemberVO.setMBR_ID(ctiMemberVO.getMBR_ID());
			  			chkEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);
			  			if(chkEduMemberVO.getMBR_ID()==null || chkEduMemberVO.getMBR_ID().length()==0) {
			  				log_dscrp.append("[존재하지 않는 회원정보를 요청함]");
			  			} else {
			  				if(chkEduMemberVO.getMBR_HP().trim().replace("-", "").equals(ctiMemberVO.getCTI_MBR_HP_CHK().trim().replace("-", ""))) {
			  					log_dscrp.append("[회원정보에연락처가동일하여반영안함]");
			  				} else {
			  					isJoinUpdMember = true;
				  				EduMemberVO updEduMemberVO = new EduMemberVO();
				  				updEduMemberVO.setMBR_HP(ctiMemberVO.getCTI_MBR_HP_CHK().trim().replace("-", ""));
				  				updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
				  				updEduMemberVO.setMBR_ADDR2(chkEduMemberVO.getMBR_ADDR2());
				  				updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
								eduMemberService.set_edu_member_mod(updEduMemberVO);
								
								EduMemberVO realEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);
				  				//사용자사유로그기록
								{
									logRecordService.set_log_mbr_mod_data("MBR_TB","수정",ctiCallHistoryVO.getLOG_UPD_MSG(),realEduMemberVO.getMBR_ID(),realEduMemberVO.getMBR_NM(),chkEduMemberVO,realEduMemberVO,loginVO,request);
								}
				  				log_dscrp.append("[회원정보에연락처를갱신함]");
				  				//CTI LOG 기록
				  	  			{
				  	  				logRecordService.set_log_cti_data("연락처변경", log_dscrp.toString(), log_msg.toString(), ctiCallHistoryVO.getLOG_UPD_MSG(), chkEduMemberVO, realEduMemberVO, loginVO, request);
				  	  			}
				  	  			//End of CTI LOG 기록
			  				}
			  			}
			  	  	}
			  	  	//End of 회원정보에 연락처 갱신 여부 처리
			  	  			  	  			  	 
		  	  		//CTI전용회원 등록 여부 검증
		  	  		boolean isCtiNewMember = false;
		  	  		boolean isCtiUpdMember = false;
			  	  	CtiMemberVO chkCtiMemberVO = new CtiMemberVO();
			  	  	chkCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
			  	  	chkCtiMemberVO = ctiMemberService.get_mbr_info_with_scan(chkCtiMemberVO);  			
		  			if(chkCtiMemberVO!=null) {
		  				if(chkCtiMemberVO.getIS_JOIN_MBR().equals("Y")) {
		  					//회원테이블존재
		  					LOGGER.debug("회원테이블존재");
		  					log_dscrp.append("[낚시누리회원]");
		  				} else {
		  					if(chkCtiMemberVO.getIS_CTI_MBR().equals("Y")) {
		  						//CTI테이블존재
		  						LOGGER.debug("CTI테이블존재");
			  					log_dscrp.append("[CTI전용회원]");
			  					if(IS_ONLY_CTI_MBR) {
			  						isCtiUpdMember = true;	
			  					}
		  					} else {
		  						LOGGER.debug("CTI전용회원신규등록");
		  						log_dscrp.append("[CTI전용회원으로등록함]");
		  						isCtiNewMember = true;
		  					}
		  				}
		  			} else {
		  				log_dscrp.append("[CTI전용회원으로등록함]");
		  				isCtiNewMember = true;
		  			}
		  	  		if(isCtiNewMember) {
			  			CtiMemberVO newCtiMemberVO = new CtiMemberVO();
	  					newCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
		  				newCtiMemberVO.setMBR_ID(ctiMemberVO.getMBR_ID());
		  				newCtiMemberVO.setCTI_MBR_NM(ctiMemberVO.getCTI_MBR_NM());
		  				newCtiMemberVO.setCTI_MBR_BIRTH(ctiMemberVO.getCTI_MBR_BIRTH());
		  				newCtiMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
		  				newCtiMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
			  			ctiMemberService.set_cti_mbr_info_reg(newCtiMemberVO);
			  			
			  			tbl_inf.append(",CTI_MBR_TB");
			  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(newCtiMemberVO));
		  	  		}
			  	  	if(isCtiUpdMember) {
		  	  			CtiMemberVO updCtiMemberVO = new CtiMemberVO();
						updCtiMemberVO.setCTI_MBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
					  	updCtiMemberVO.setMBR_ID(ctiMemberVO.getMBR_ID());
					  	updCtiMemberVO.setCTI_MBR_NM(ctiMemberVO.getCTI_MBR_NM());
						updCtiMemberVO.setCTI_MBR_BIRTH(ctiMemberVO.getCTI_MBR_BIRTH());
						updCtiMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
						ctiMemberService.set_cti_mbr_info_mod(updCtiMemberVO);
						
						tbl_inf.append(",CTI_MBR_TB");
			  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(updCtiMemberVO));
		  	  		}
		  	  		//End of CTI전용회원 등록 여부 검증
		  	  		
		  	  		CtiCallHistoryVO updCtiCallHistoryVO = new CtiCallHistoryVO();
		  	  		updCtiCallHistoryVO.setHCALL_SN(ctiCallHistoryVO.getHCALL_SN());//key
			  	  	//updCtiCallHistoryVO.setCALL_CD(ctiCallHistoryVO.getCALL_CD());//hcall만으로 충분
				  	updCtiCallHistoryVO.setHCALL_IMPRT(ctiCallHistoryVO.getHCALL_IMPRT());
				  	updCtiCallHistoryVO.setHCALL_GUBUN_1(ctiCallHistoryVO.getHCALL_GUBUN_1());
				  	updCtiCallHistoryVO.setHCALL_GUBUN_2(ctiCallHistoryVO.getHCALL_GUBUN_2());
				  	updCtiCallHistoryVO.setHCALL_CONT(ctiCallHistoryVO.getHCALL_CONT());
				  	updCtiCallHistoryVO.setHCALL_MEMO(ctiCallHistoryVO.getHCALL_MEMO());
				  	updCtiCallHistoryVO.setUPD_MBR_ID(MASTER_MBR_ID);
				  	updCtiCallHistoryVO.setMBR_ID(ctiMemberVO.getMBR_ID());
				  	
				  	//updCtiCallHistoryVO.setMBR_HP(ctiMemberVO.getCTI_MBR_HP().trim().replace("-", ""));
				  	String CTI_MBR_HP = "";
				  	if(isJoinUpdMember) {
				  		CTI_MBR_HP = ctiMemberVO.getCTI_MBR_HP_CHK().trim().replace("-", "");
				  	} else {
				  		CTI_MBR_HP = ctiMemberVO.getCTI_MBR_HP().trim().replace("-", "");	
				  	}
				  	updCtiCallHistoryVO.setMBR_HP(CTI_MBR_HP);
				  	
				  	if(chkCtiCallHistoryVO.getHCALL_TYPE().equals("발신")) {
				  		updCtiCallHistoryVO.setHCALL_R_TEL(CTI_MBR_HP);	
				  	} else if(chkCtiCallHistoryVO.getHCALL_TYPE().equals("수신")) {
				  		updCtiCallHistoryVO.setHCALL_S_TEL(CTI_MBR_HP);	
				  	} else {
				  		updCtiCallHistoryVO.setHCALL_R_TEL(CTI_MBR_HP);
				  	}
				  	if(isCtiUpdMember) {
				  		updCtiCallHistoryVO.setFORCE_UPD_MBR_ID(true);
				  	}
				  	//updCtiCallHistoryVO.setSTAFF_MBR_ID(ctiStaffMemberVO.getMBR_ID());
				  	//updCtiCallHistoryVO.setSTAFF_MBR_NM(ctiStaffMemberVO.getCTI_MBR_NM());
				  	
				  	//첨부 파일 검증 및 저장
			  	  	String _atchFileId1 = chkCtiCallHistoryVO.getHCALL_FILE_1();
					PublicFileMngUtil mPublicFileMngUtil = new PublicFileMngUtil(fileMngService,fileUtil);
			  	  	Map<String, MultipartFile> mainImgfile1 = mPublicFileMngUtil.extractinputFileName(
							multiRequest,
							new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
								"file_jegprsn_reqstdoc",
							});
			  	  	Map<String, MultipartFile> mainImgfile2 = mPublicFileMngUtil.extractinputFileName(
							multiRequest,
							new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
								"file_compl_reqstdoc",
							});
			  	  	{//법인사업장 팩스 신청서
						Map fresult = mPublicFileMngUtil.chkFileUpdate(
								mainImgfile1, //업로드 파일 목록
								_atchFileId1, //첨부파일아이디(ATCH_FILE_ID) 
								"0", //첨부파일아이디 번호(FILE_SN)
								"CTI_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
							log_dscrp.append("[법인사업장팩스신청서첨부:실패]");
						} else if(fresult.get("error").equals("2")) {
							LOGGER.debug("파일 검증 실패");
							log_dscrp.append("[법인사업장팩스신청서첨부:파일오류]");
						} else { //정상적으로 처리됨.
							_atchFileId1 = fresult.get("atchFileId").toString();
							log_dscrp.append("[법인사업장팩스신청서첨부:완료]");
						}
					}
			  	  	String _atchFileId2 = chkCtiCallHistoryVO.getHCALL_FILE_2();
			  	  	{//이수증발급 팩스 신청서
						Map fresult = mPublicFileMngUtil.chkFileUpdate(
								mainImgfile2, //업로드 파일 목록
								_atchFileId2, //첨부파일아이디(ATCH_FILE_ID) 
								"0", //첨부파일아이디 번호(FILE_SN)
								"CTI_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
							log_dscrp.append("[이수증발급팩스신청서첨부:실패]");
						} else if(fresult.get("error").equals("2")) {
							LOGGER.debug("파일 검증 실패");
							log_dscrp.append("[이수증발급팩스신청서첨부:파일오류]");
						} else { //정상적으로 처리됨.
							_atchFileId2 = fresult.get("atchFileId").toString();
							log_dscrp.append("[이수증발급팩스신청서첨부:완료]");
						}
					}
			  	  	LOGGER.debug("법인사업장 팩스 신청서 첨부파일 번호 : " + _atchFileId1);
			  	  	LOGGER.debug("이수증발급 팩스 신청서 첨부파일 번호 : " + _atchFileId2);
			  	  	updCtiCallHistoryVO.setHCALL_FILE_1(_atchFileId1);
			  	  	updCtiCallHistoryVO.setHCALL_FILE_2(_atchFileId2);
			  	  	//End of 첨부 파일 검증 저장
			  	  	
		  	  		ctiCallHistoryService.set_cti_callhstry_info_mod(updCtiCallHistoryVO);
		  	  					  	  	
			  	  	log_dscrp.append("[처리완료]");
			  	  	data.put("error", "0");
		  			data.put("msg", "정상적으로 처리되었습니다.");
		  	  				  	  		
	  	  		} else {
	  	  			log_dscrp.append("[에러:존재하지않는정보를요청함]");
		  	  		data.put("error", "1");
		  			data.put("msg", "존재하지 않는 정보입니다.");
	  	  		}
  	  			
  	  		} else {
  	  			log_dscrp.append("[에러:파라미터부족상태로요청함]");
	  	  		data.put("error", "1");
	  			data.put("msg", "검증 정보가 부족합니다.");
  	  		}
  	  		  			
  		} catch(Exception e) {
  			log_dscrp.append("[에러:처리실패("+e.toString()+")]");
  			data.put("error", "1");
  			data.put("msg", "처리 중 오류가 발생하였습니다.");	
  		}
  		
  		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mLogRecordVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
			mLogRecordVO.setMBR_MSG(LOG_UPD_MSG);
			logRecordService.set_log_data(mLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
  		
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
  	
  	
  	//CTI 통화이력 삭제 로직 
  	@RequestMapping(value="/cti/callhstry/delete_act.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_data_delete_act(@ModelAttribute("ctiMemberVO") CtiMemberVO ctiMemberVO,
		@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
		HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
  		
  		PublicUtils mPublicUtils = new PublicUtils();
  		
  		/** LOG RECORED (로그기록) 선언부 */
  		LogRecordVO mLogRecordVO = new LogRecordVO();
  		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		String LOG_UPD_MSG = ctiCallHistoryVO.getLOG_UPD_MSG();
		/** End of LOG RECORED (로그기록) 선언부 */
		log_dscrp.append("[CTI-통화이력-삭제]");
  		
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		String MASTER_MBR_ID = loginVO.getMBR_ID();		
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
  		JSONObject data = new JSONObject();
  		try {
  			CtiCallHistoryVO chkCtiCallHistoryVO = new CtiCallHistoryVO();
  	  		if(ctiCallHistoryVO!=null && ctiCallHistoryVO.getHCALL_SN()!=null && ctiCallHistoryVO.getHCALL_SN().length()!=0) {
  	  			chkCtiCallHistoryVO.setHCALL_SN(ctiCallHistoryVO.getHCALL_SN());
  	  			chkCtiCallHistoryVO = ctiCallHistoryService.get_cti_callhstry_info(chkCtiCallHistoryVO);  	  			
	  	  		if(chkCtiCallHistoryVO!=null && chkCtiCallHistoryVO.getHCALL_SN()!=null && chkCtiCallHistoryVO.getHCALL_SN().length()!=0) {
	  	  		
	  	  			tbl_inf.append("CTI_CALL_HSTRY_TB");
		  	  		tbl_sn.append(ctiCallHistoryVO.getHCALL_SN());
		  	  		log_msg.append(mLogRecordVO.encodingFromObjectToJson(ctiCallHistoryVO));
	  	  			log_msg.append(mLogRecordVO.encodingFromObjectToJson(chkCtiCallHistoryVO));
	  	  			
	  	  			//스태프 정보
		  	  		CtiMemberVO ctiStaffMemberVO = new CtiMemberVO();
		  	  		ctiStaffMemberVO.setMBR_ID(loginVO.getMBR_ID());
		  	  		ctiStaffMemberVO = ctiMemberService.get_cti_staff_info(ctiStaffMemberVO);
		  	  		//End of 스태프 정보
		  	  		
		  	  		//첨부 파일 삭제
			  	  	if(chkCtiCallHistoryVO.getHCALL_FILE_1()!=null && chkCtiCallHistoryVO.getHCALL_FILE_1().length()!=0) {
						fileMngService.deleteAllDetailFileInfs(chkCtiCallHistoryVO.getHCALL_FILE_1());
						fileMngService.deleteAllFileInf(chkCtiCallHistoryVO.getHCALL_FILE_1());
					}
			  	 	if(chkCtiCallHistoryVO.getHCALL_FILE_2()!=null && chkCtiCallHistoryVO.getHCALL_FILE_2().length()!=0) {
						fileMngService.deleteAllDetailFileInfs(chkCtiCallHistoryVO.getHCALL_FILE_2());
						fileMngService.deleteAllFileInf(chkCtiCallHistoryVO.getHCALL_FILE_2());
					}
			  	  	//End of 첨부 파일 삭제		  	  		
		  	  				  	  		
		  	  		CtiCallHistoryVO updCtiCallHistoryVO = new CtiCallHistoryVO();
		  	  		updCtiCallHistoryVO.setHCALL_SN(ctiCallHistoryVO.getHCALL_SN());
		  	  		ctiCallHistoryService.remove_cti_callhstry_info(updCtiCallHistoryVO);
		  	  		
			  	  	log_dscrp.append("[처리완료]");
			  	  	data.put("error", "0");
		  			data.put("msg", "정상적으로 처리되었습니다.");
		  	  				  	  		
	  	  		} else {
	  	  			log_dscrp.append("[에러:존재하지않는정보를요청함]");
		  	  		data.put("error", "1");
		  			data.put("msg", "존재하지 않는 정보입니다.");
	  	  		}
  	  			
  	  		} else {
  	  			log_dscrp.append("[에러:파라미터부족상태로요청함]");
	  	  		data.put("error", "1");
	  			data.put("msg", "검증 정보가 부족합니다.");
  	  		}
  	  		  			
  		} catch(Exception e) {
  			log_dscrp.append("[에러:처리실패("+e.toString()+")]");
  			data.put("error", "1");
  			data.put("msg", "처리 중 오류가 발생하였습니다.");	
  		}
  		
  		/** LOG RECORED (로그기록) 처리부 */
  		try {
			mLogRecordVO.setLOG_MSG(log_msg.toString());
			mLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mLogRecordVO.setTBL_INF(tbl_inf.toString());
			mLogRecordVO.setTBL_SN(tbl_sn.toString());
			mLogRecordVO.setMBR_ID(MASTER_MBR_ID);
			mLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
			mLogRecordVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
			mLogRecordVO.setMBR_MSG(LOG_UPD_MSG);
			logRecordService.set_log_data(mLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
  		/** End of LOG RECORED (로그기록) 처리부 */
  		
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}
  	
}


