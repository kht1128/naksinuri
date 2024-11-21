package egovframework.eduadm.trainingdata.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.category.service.EduCategoryVO;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicFileMngUtil;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduTrainingDataController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EduTrainingDataController.class);
	
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
	

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	
	/** multipart */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;
	

	
	//관리자(교육센터) 교육자료 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/tdata/list.do")
	public String edu_data(@ModelAttribute("eduTrainingDataVO") EduTrainingDataVO eduTrainingDataVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		List<EduTrainingDataVO> trn_data_list = null;

		/** pageing setting */
		//eduTrainingDataVO.setPageUnit(2);
		//eduTrainingDataVO.setPageSize(5);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduTrainingDataVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduTrainingDataVO.getPageUnit());
		paginationInfo.setPageSize(eduTrainingDataVO.getPageSize());

		eduTrainingDataVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduTrainingDataVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduTrainingDataVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		trn_data_list = eduTrainingDataService.get_edu_data_list(eduTrainingDataVO);
		
		int totCnt = eduTrainingDataService.get_edu_data_list_totcnt(eduTrainingDataVO);
		eduTrainingDataVO.setTotalRecordCount(totCnt);			
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("trn_data_list",trn_data_list);
		return "eduadm/tdata/list";
	}	
	//관리자(교육센터) 교육자료 글쓰기 ------------------------------------------------
	@RequestMapping(value = "/eduadm/tdata/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_data_write(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, 
			@ModelAttribute("eduTrainingDataVO") EduTrainingDataVO eduTrainingDataVO, 
			HttpServletRequest request, ModelMap model) throws Exception {	
		
		eduCategoryVO.setSearchKeyword("use_st");
		eduCategoryVO.setUSE_ST("1");
		List<EduCategoryVO> list1 = eduCategoryService.get_edu_category_list_1(eduCategoryVO);
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/tdata/write");
		mModelAndView.addObject("edu_category_1",list1);
		return mModelAndView;
	}
	//관리자(교육센터) 교육자료 글수정 ------------------------------------------------
	@RequestMapping(value = "/eduadm/tdata/modify.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_data_modify(@ModelAttribute("eduCategoryVO") EduCategoryVO eduCategoryVO, 
			@ModelAttribute("eduTrainingDataVO") EduTrainingDataVO eduTrainingDataVO, 
			HttpServletRequest request, ModelMap model) throws Exception {	
		/*
		//파일삭제시 다시 불림
		LOGGER.debug("첨부파일 삭제 검증시작 -------------------------------");	
		String _atchFileId = eduTrainingDataVO.getATCH_FILE_ID();
		if(_atchFileId!=null && _atchFileId.length()!=0) {
			LOGGER.debug("> 삭제 할 파일 있음..");
			FileVO delfileVO = new FileVO();
			delfileVO.setAtchFileId(_atchFileId);
			delfileVO.setFileSn(eduTrainingDataVO.getFILE_SN());
			this.fileMngService.deleteFileInf(delfileVO);
			//정보 업데이트
			eduTrainingDataVO.setTRN_FILE_SN("");
			eduTrainingDataVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			eduTrainingDataService.set_edu_data_mod(eduTrainingDataVO);			
		} else {
			LOGGER.debug("> 통과..");
		}
		LOGGER.debug("첨부파일 삭제 검증종료 -------------------------------");
		*/	
		eduCategoryVO.setSearchKeyword("use_st");
		eduCategoryVO.setUSE_ST("1");
		EduTrainingDataVO info = eduTrainingDataService.get_edu_data_info(eduTrainingDataVO);
		
		//첨부파일 갯수 확인 후 필요시 정보 업데이트
		String _atchFileId = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getTRN_FILE_SN());
		if(info.getTRN_FILE_SN()!=null && !info.getTRN_FILE_SN().equals(_atchFileId)) {
			info.setTRN_FILE_SN(_atchFileId);
			EduTrainingDataVO updEduTrainingDataVO = new EduTrainingDataVO();
			updEduTrainingDataVO.setTRN_SN(info.getTRN_SN());
			updEduTrainingDataVO.setTRN_FILE_SN(_atchFileId.length()==0?"NULL":_atchFileId);
			eduTrainingDataService.set_edu_data_mod(updEduTrainingDataVO);
		}
		//
		
		
		List<EduCategoryVO> list1 = eduCategoryService.get_edu_category_list_1(eduCategoryVO);
		eduCategoryVO.setCAT_SN(info.getCAT_SN());
		List<EduCategoryVO> list2 = eduCategoryService.get_edu_category_list_2(eduCategoryVO);
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/tdata/modify");
		mModelAndView.addObject("info",info);
		mModelAndView.addObject("edu_category_1",list1);
		mModelAndView.addObject("edu_category_2",list2);
		return mModelAndView;
	}
	//관리자(교육센터) 교육자료 등록 ------------------------------------------------
	@RequestMapping(value="/eduadm/tdata/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_data_write_act(@ModelAttribute("eduTrainingDataVO") EduTrainingDataVO eduTrainingDataVO,
			HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiRequest, 
			BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육자료등록]");
		
		try {
			LOGGER.debug("첨부파일 검증시작 -------------------------------");		

			String _atchFileId = "";
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
						"1", //첨부파일아이디 번호(FILE_SN)
						"TRN_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
						"0", //첨부파일아이디 번호(FILE_SN)
						"TRN_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
			}								
			eduTrainingDataVO.setTRN_FILE_SN(_atchFileId);
			
			if(_atchFileId==null || _atchFileId.length()==0) {
				LOGGER.debug("업로드 할 파일이 없거나 실패한 경우");
				log_msg.append("[처리실패:업로드 할 파일이 없거나 실패한 경우]");
				log_dscrp.append("[처리실패]");
				data.put("msg", "업로드 할 파일이 없거나 파일이 선택되지 않았습니다.");
				data.put("error", "1");
				data.put("errCnt", 1);
			} else {
				eduTrainingDataVO.setTRN_FILE_SN(_atchFileId);
				//eduTrainingDataVO.setTRN_TYPE_ST(_atchFileExt);
				
				if(eduTrainingDataVO.getUSE_ST_CHK().equals("Y")) {
					eduTrainingDataVO.setUSE_ST("1");
					eduTrainingDataVO.setDEL_ST("0");
				} else {
					eduTrainingDataVO.setUSE_ST("0");
				}
				eduTrainingDataVO.setREG_MBR_ID(loginVO.getMBR_ID());
				eduTrainingDataVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				
				//XSS 취약점
				EgovStringUtil esu = new EgovStringUtil();
				eduTrainingDataVO.setTRN_NM(esu.getHtmlStrCnvr(eduTrainingDataVO.getTRN_NM()));//교육자료명
				eduTrainingDataVO.setTRN_DSCRP(esu.getHtmlStrCnvr(eduTrainingDataVO.getTRN_DSCRP()));//설명
				
				String insertId = eduTrainingDataService.set_edu_data_reg(eduTrainingDataVO);
				
				tbl_inf.append("EDU_TRNNG_DATA_TB,");
				tbl_sn.append(insertId+",");
				
				log_dscrp.append("[게시물"+eduTrainingDataVO.getTRN_NM()+":일련번호("+insertId+")]");
				
				data.put("error", "0");
				data.put("msg", "정상적으로 등록되었습니다.");
				data.put("errCnt", 0);
			}
		} catch(NullPointerException e) {
			LOGGER.debug("[fail process] Multipart 에러 발생 , "+e.toString());
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "Multipart 에러 발생");
		} catch(Exception e) {
			LOGGER.debug("[fail process] Multipart 에러 발생 , "+e.toString());
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "Multipart 에러 발생");
		}
		try {	
			/**
			 *  LOG RECORED (로그기록)
			 */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduTrainingDataVO));
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
	//관리자(교육센터) 교육자료 수정 ------------------------------------------------
	@RequestMapping(value="/eduadm/tdata/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_data_modify_act(@ModelAttribute("eduTrainingDataVO") EduTrainingDataVO eduTrainingDataVO,
			HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiRequest, 
			BindingResult bindingResult, ModelMap model) throws Exception {	
		
		EduTrainingDataVO chkEduTrainingDataVO = new EduTrainingDataVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-교육자료수정]");
		
		try {
		
			chkEduTrainingDataVO = eduTrainingDataService.get_edu_data_info(eduTrainingDataVO);
							
			//String _atchFileExt = chkEduTrainingDataVO.getTRN_TYPE_ST();
			String _atchFileId = chkEduTrainingDataVO.getTRN_FILE_SN();
			
			
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
						"1", //첨부파일아이디 번호(FILE_SN)
						"TRN_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
			}
			{//첨부파일
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						atchfiles, //업로드 파일 목록
						_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
						"0", //첨부파일아이디 번호(FILE_SN)
						"TRN_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
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
			}								
			eduTrainingDataVO.setTRN_FILE_SN(_atchFileId);
								
			if(eduTrainingDataVO.getCAT_SN_CHNG()!=null && eduTrainingDataVO.getCAT_SN_CHNG().length()!=0) {
				eduTrainingDataVO.setCAT_SN(eduTrainingDataVO.getCAT_SN_CHNG());
			}
			if(eduTrainingDataVO.getCAT_DTL_SN_CHNG()!=null && eduTrainingDataVO.getCAT_DTL_SN_CHNG().length()!=0) {
				eduTrainingDataVO.setCAT_DTL_SN(eduTrainingDataVO.getCAT_DTL_SN_CHNG());
			}
			
			if(eduTrainingDataVO.getUSE_ST_CHK().equals("Y")) {
				eduTrainingDataVO.setUSE_ST("1");
				eduTrainingDataVO.setDEL_ST("0");
			} else {
				eduTrainingDataVO.setUSE_ST("0");
			}
			eduTrainingDataVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			
			//XSS 취약점
			EgovStringUtil esu = new EgovStringUtil();
			eduTrainingDataVO.setTRN_NM(esu.getHtmlStrCnvr(eduTrainingDataVO.getTRN_NM()));//교육자료명
			eduTrainingDataVO.setTRN_DSCRP(esu.getHtmlStrCnvr(eduTrainingDataVO.getTRN_DSCRP()));//설명
			
			eduTrainingDataService.set_edu_data_mod(eduTrainingDataVO);
			
			log_dscrp.append("[게시물"+chkEduTrainingDataVO.getTRN_NM()+":일련번호("+eduTrainingDataVO.getTRN_SN()+")]");
			tbl_inf.append("EDU_TRNNG_DATA_TB,");
			tbl_sn.append(eduTrainingDataVO.getTRN_SN()+",");
			
			data.put("error", "0");
			data.put("msg", "정상적으로 등록되었습니다.");
			data.put("errCnt", 0);
		} catch(NullPointerException e1) {
			LOGGER.debug("[fail process] "+e1.toString());
			log_msg.append("[에러발생("+e1.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "업데이트 실패가 실패하였습니다.");
		} catch(Exception e) {
			LOGGER.debug("[fail process] "+e.toString());
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "업데이트 실패가 실패하였습니다.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduTrainingDataVO));
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduTrainingDataVO));
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
	//관리자(교육센터) 교육자료 삭제 ------------------------------------------------
	@RequestMapping(value="/eduadm/tdata/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_data_delete_act(@ModelAttribute("eduTrainingDataVO") EduTrainingDataVO eduTrainingDataVO, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		
		EduTrainingDataVO chkEduTrainingDataVO = new EduTrainingDataVO();
		try {			
			chkEduTrainingDataVO = eduTrainingDataService.get_edu_data_info(eduTrainingDataVO);
			if(chkEduTrainingDataVO == null || chkEduTrainingDataVO.getTRN_SN() == null || chkEduTrainingDataVO.getTRN_SN().length() == 0) {
				data.put("error", "1");
				data.put("msg", "처리 가능한 데이터가 없습니다.");
			} else {
												
				String DEL_ST = chkEduTrainingDataVO.getDEL_ST();
				if(DEL_ST.equals("1")) { //완전 삭제
					log_dscrp.append("[교육센터관리자-교육자료삭제-실제데이터삭제]");
					log_dscrp.append("[게시물"+chkEduTrainingDataVO.getTRN_NM()+":일련번호("+chkEduTrainingDataVO.getTRN_SN()+")]");
					eduTrainingDataVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					eduTrainingDataService.remove_edu_data(eduTrainingDataVO);
					
					if(eduTrainingDataVO.getTRN_FILE_SN()!=null && eduTrainingDataVO.getTRN_FILE_SN().length()!=0) {
						fileMngService.deleteAllDetailFileInfs(eduTrainingDataVO.getTRN_FILE_SN());
						fileMngService.deleteAllFileInf(eduTrainingDataVO.getTRN_FILE_SN());
					}
					
					tbl_inf.append("EDU_TRNNG_DATA_TB,");
					tbl_sn.append(eduTrainingDataVO.getTRN_SN()+",");
					
				} else {//처음 삭제
					log_dscrp.append("[교육센터관리자-교육자료삭제]");
					log_dscrp.append("[게시물"+chkEduTrainingDataVO.getTRN_NM()+":일련번호("+chkEduTrainingDataVO.getTRN_SN()+")]");
					eduTrainingDataVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					eduTrainingDataService.del_edu_data(eduTrainingDataVO);
					
					tbl_inf.append("EDU_TRNNG_DATA_TB,");
					tbl_sn.append(eduTrainingDataVO.getTRN_SN()+",");
				}
				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");
								
			}	
		} catch(NullPointerException e) {
			LOGGER.debug("[fail process] " +e.toString());
			log_msg.append("[에러발생("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		} catch(Exception e) {
			LOGGER.debug("[fail process] " +e.toString());
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
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkEduTrainingDataVO));
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduTrainingDataVO));
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


