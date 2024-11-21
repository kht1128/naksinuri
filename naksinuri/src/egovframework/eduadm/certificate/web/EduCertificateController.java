package egovframework.eduadm.certificate.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.main.service.EduCenterService;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class EduCertificateController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EduCertificateController.class);
	
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
	
	/** EduCertificateService */
	@Resource(name = "eduCertificateService")
	private EduCertificateService eduCertificateService;
	
	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	
	//관리자(교육센터) 이수증 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/certificate/list.do")
	public String edu_certificate_list(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO.getMBR_POSITION_CD().equals("PCD0003") ||  loginVO.getMBR_POSITION_CD().equals("PCD0002")){
		LOGGER.debug("교육센터 관리자페이지 - 접근권한 없음!!");
		return "redirect:/eduadm/error/unauth.do";
		} 
		
		List<EduCertificateVO> list = null;
		try {
			
			eduCertificateVO.setUSE_ST("0"); //관리자는 모두 보여야 하기 때문에.
			
			/** pageing setting */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(eduCertificateVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(eduCertificateVO.getPageUnit());
			paginationInfo.setPageSize(eduCertificateVO.getPageSize());

			eduCertificateVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			eduCertificateVO.setLastIndex(paginationInfo.getLastRecordIndex());
			eduCertificateVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			list = eduCertificateService.get_edu_certificate_list(eduCertificateVO);
			int totCnt = eduCertificateService.get_edu_certificate_list_totcnt(eduCertificateVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
		} catch(Exception e) {
			
		}
		
		//교육그룹 코드 조회(전체)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_all_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(전체)
		{
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setNotUsedPagination(true);
			List<EduCategoryInsInfVO> list_all_edu_ins_cd = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
	  		model.addAttribute("list_all_edu_ins_cd",list_all_edu_ins_cd);
		}
		
		model.addAttribute("list",list);
		model.addAttribute("searchCondition",eduCertificateVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduCertificateVO.getSearchCondition().length()==0?"":eduCertificateVO.getSearchKeyword());		
		return "eduadm/certificate/list";
	}	
	//관리자(교육센터) 이수증 리스트 글수정 ------------------------------------------------
	@RequestMapping(value = "/eduadm/certificate/modify.do", method = RequestMethod.POST)
	public ModelAndView ajax_edu_certificate_modify(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		
		EduCertificateVO info = null;
		if((eduCertificateVO.getCRTF_SN()!=null && eduCertificateVO.getCRTF_SN().length()!=0)
		//&& (eduCertificateVO.getMBR_ID()!=null && eduCertificateVO.getMBR_ID().length()!=0)
		) {
			info = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		}	
		ModelAndView mModelAndView = new ModelAndView();
		mModelAndView.setViewName("eduadm/certificate/modify");
		mModelAndView.addObject("info",info);
		return mModelAndView;
	}
	//관리자(교육센터) 이수증 리스트 수정 로직 ------------------------------------------------
	@RequestMapping(value="/eduadm/certificate/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_certificate_modify_act(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		//검증
		EduCertificateVO chkEduCertificateVO = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		if((chkEduCertificateVO.getMBR_ID()==null || chkEduCertificateVO.getMBR_ID().length()==0)
		&& (chkEduCertificateVO.getCRTF_SN()==null || chkEduCertificateVO.getCRTF_SN().length()==0)
		) {
			data.put("error", "1");
			data.put("msg", "존재하지 않는 정보입니다.");
		} else {
			try {				
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder log_msg = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();
				log_dscrp.append("[교육센터관리자-이수증발급정보수정-상위]");
				
				EduMemberVO eduMemberVO = new EduMemberVO();
				eduMemberVO.setMBR_ID(chkEduCertificateVO.getMBR_ID());
				eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
				log_dscrp.append("[이름"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+")]");
				
				if(eduCertificateVO.getUSE_ST_CHK().equals("Y")) {//사용함
					eduCertificateVO.setUSE_ST("1");
					eduCertificateVO.setDEL_ST("0");
					
					/*
					 * 이거는 하면 안될꺼 같은데??? 2018.12.14
					 * 관리자가 열람시 USE_ST 를 0 으로 하는데... 다시 복구 하면 안되지.
					 * 
					String DEL_ST = eduCertificateVO.getDEL_ST();
					if(DEL_ST.equals("1")) {//삭제 -> 사용함 변경시 하위데이터도 같이 변경
						EduCertificateVO cloneEduCertificateVO = new EduCertificateVO();
						cloneEduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
						cloneEduCertificateVO.setHMBR_SN(eduCertificateVO.getHMBR_SN());
						cloneEduCertificateVO.setMBR_ID(eduCertificateVO.getMBR_ID());
						cloneEduCertificateVO.setCRS_SN(eduCertificateVO.getCRS_SN());
						cloneEduCertificateVO.setUSE_ST("1");
						cloneEduCertificateVO.setDEL_ST("0");
						eduCertificateService.set_edu_certificate_mod_dtl(cloneEduCertificateVO);
						
						tbl_inf.append("EDU_CRTF_DTL_TB,");
						tbl_sn.append(eduCertificateVO.getCRTF_DTL_SN()+",");
						
					} 
					
					*/
					
				} else {//사용안함
					eduCertificateVO.setUSE_ST("0");
				}
				
				eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				eduCertificateService.set_edu_certificate_mod(eduCertificateVO);
				
				tbl_inf.append("EDU_CRTF_TB,");
				tbl_sn.append(eduCertificateVO.getCRTF_SN()+",");
				
				data.put("error", "0");
				data.put("msg", "이수증발급 정보가 수정되었습니다.");	
									
				try {	
					/**
					 * LOG RECORED (로그기록)
					 * */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCertificateVO));
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
												
			} catch(Exception e) {
				data.put("error", "1");
				data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			}
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	//관리자(교육센터) 이수증 리스트 삭제 로직 ------------------------------------------------
	@RequestMapping(value="/eduadm/certificate/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_certificate_delete_act(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();	
		try {
			//검증
			EduCertificateVO chkEduCertificateVO = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
			if((chkEduCertificateVO.getMBR_ID()==null || chkEduCertificateVO.getMBR_ID().length()==0)
			&& (chkEduCertificateVO.getCRTF_SN()==null || chkEduCertificateVO.getCRTF_SN().length()==0)
			) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {
				
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();
				
						
				String DEL_ST = chkEduCertificateVO.getDEL_ST();
				if(DEL_ST.equals("1")) {
					log_dscrp.append("[교육센터관리자-발급된이수증삭제-상하위-실제데이터삭제]");
					
				} else {
					log_dscrp.append("[교육센터관리자-발급된이수증삭제-상하위]");
				}
				
				EduMemberVO eduMemberVO = new EduMemberVO();
				eduMemberVO.setMBR_ID(chkEduCertificateVO.getMBR_ID());
				eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
				log_dscrp.append("[이름"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+")]");
				
				//메인
				eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				if(DEL_ST.equals("1")) {
					eduCertificateService.remove_edu_certificate(eduCertificateVO);
				} else {
					eduCertificateService.del_edu_certificate(eduCertificateVO);
				}
				
				tbl_inf.append("EDU_CRTF_TB,");
				tbl_sn.append(eduCertificateVO.getCRTF_SN()+",");
				
				//상세
				eduCertificateVO.setNotUsedPagination(true);
				List<EduCertificateVO> clildList = eduCertificateService.get_edu_certificate_dtl_list(eduCertificateVO);
				for(EduCertificateVO item : clildList) {
					EduCertificateVO cloneEduCertificateVO = item;
					cloneEduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
					if(DEL_ST.equals("1")) {
						eduCertificateService.remove_edu_certificate_dtl(cloneEduCertificateVO);
					} else {
						eduCertificateService.del_edu_certificate_dtl(cloneEduCertificateVO);
					}
					
					tbl_inf.append("EDU_CRTF_DTL_TB,");
					tbl_sn.append(eduCertificateVO.getCRTF_DTL_SN()+",");
					
				}
				
				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");
				
				try {	
					/**
					 * LOG RECORED (로그기록)
					 * */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(chkEduCertificateVO));
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
						
			}
		} catch(Exception e) {
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	//관리자(교육센터) 이수증 (새창)보기페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/certificate/popwin/view.do")
	public String edu_certificate_popwin_view(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		List<EduCertificateVO> list = new ArrayList<EduCertificateVO>();
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-이수증일괄(출력)열람하기]");		
				
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO == null) {
			return "eduadm/error/login";	
		}
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		
		String[] CRTF_SNs = eduCertificateVO.getCRTF_SNs().split(",", -1);
		String[] CRS_SNs = eduCertificateVO.getCRS_SNs().split(",", -1);
		String[] HMBR_SNs = eduCertificateVO.getHMBR_SNs().split(",", -1);
		String[] MBR_IDs = eduCertificateVO.getMBR_IDs().split(",", -1);
		if(CRS_SNs!=null || (CRS_SNs!=null && HMBR_SNs!=null)) { //처리가능
			int totalcnt = 0;
			if(CRTF_SNs!=null && CRTF_SNs.length > 0) totalcnt = CRTF_SNs.length;
			if(CRS_SNs!=null && CRS_SNs.length > 0) totalcnt = CRS_SNs.length;
			for(int i=0; i<totalcnt; i++) {
				String CRTF_SN = "";
				String CRS_SN = "";
				String HMBR_SN = "";
				String MBR_ID = "";
				try { CRTF_SN = CRTF_SNs[i]; } catch(Exception e) { }
				try { 
					CRS_SN = CRS_SNs[i]; 
					//교육과정정보
					EduCurriculumVO parentInfo = new EduCurriculumVO();
					parentInfo.setCRS_SN(CRS_SN);
					parentInfo = eduCurriculumService.get_edu_curriculum_info(parentInfo);
					log_dscrp.append("["+parentInfo.getCRS_NM()+","+parentInfo.getCAT_INS_NM()+"("+parentInfo.getCRS_STR_DT()+")]");
				} catch(Exception e) { }
				try { HMBR_SN = HMBR_SNs[i]; } catch(Exception e) { }
				try { MBR_ID = MBR_IDs[i]; } catch(Exception e) { }
				
				boolean isMakeCertificate = false;
				boolean isNewCertificate = false;//이수를 완료했으나 이수증정보가없어 강제로 신규발급함
				EduCertificateVO chkEduCertificateVO = new EduCertificateVO();
				chkEduCertificateVO.setCRTF_SN(CRTF_SN);
				chkEduCertificateVO.setCRS_SN(CRS_SN);
				chkEduCertificateVO.setHMBR_SN(HMBR_SN);
				chkEduCertificateVO.setMBR_ID(MBR_ID);
				EduCertificateVO info = eduCertificateService.get_edu_certificate_info(chkEduCertificateVO);
				if(info == null || info.getCRTF_SN() == null || info.getCRTF_SN().length() == 0) {
					//정보없음
					if(CRTF_SN!=null && CRTF_SN.length()!=0) {
						LOGGER.debug("이수증정보없음(CRTF_SN="+CRTF_SN+")");
						log_dscrp.append("[이수증정보없음(CRTF_SN="+CRTF_SN+")");
						isMakeCertificate = false;
					} else {
						LOGGER.debug("이수증정보없음(CRS_SN="+CRS_SN+",HMBR_SN="+HMBR_SN+",MBR_ID="+MBR_ID+")");
						log_dscrp.append("[이수증정보없음(CRS_SN="+CRS_SN+",HMBR_SN="+HMBR_SN+",MBR_ID="+MBR_ID+")");
						isNewCertificate = true;
						isMakeCertificate = true;
					}
				} else {
					LOGGER.debug("이수증정보있음(CRTF_SN="+CRTF_SN+")");
					isMakeCertificate = true;
				}
				
				if(isNewCertificate) {
					LOGGER.debug("이수증강제발행전검증..");
					EduMyHistoryVO chkEduMyHistoryVO = new EduMyHistoryVO();
					chkEduMyHistoryVO.setCRS_SN(CRS_SN);
					chkEduMyHistoryVO.setHMBR_SN(HMBR_SN);
					chkEduMyHistoryVO.setMBR_ID(MBR_ID);
					chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(chkEduMyHistoryVO);	
					if(chkEduMyHistoryVO == null || chkEduMyHistoryVO.getHMBR_SN() == null || chkEduMyHistoryVO.getHMBR_SN().length() == 0) {
						LOGGER.debug("이수교육정보를알수없어이수증강제발행거부(CRTF_SN="+CRTF_SN+")");
					} else {
						if(chkEduMyHistoryVO.getLRNNG_CMPLT_ST().equals("1")) { //이수가완료된사용자
							LOGGER.debug("[이수증강제발행승인]");							
							//이수증 발급 진행
							EduCertificateVO newCertificateVO = new EduCertificateVO();
							newCertificateVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
							newCertificateVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
							newCertificateVO.setMBR_ID(chkEduMyHistoryVO.getMBR_ID());
							newCertificateVO.setCRTF_CD(newCertificateVO.getUniqKey());
							newCertificateVO.setREG_MBR_ID(chkEduMyHistoryVO.getLRNNG_CMPLT_MBR_ID());
							newCertificateVO.setUPD_MBR_ID(MASTER_MBR_ID);
							newCertificateVO.setREG_DT(chkEduMyHistoryVO.getLRNNG_CMPLT_DT());
							eduCertificateService.set_edu_certificate_reg(newCertificateVO);
							info = eduCertificateService.get_edu_certificate_info(newCertificateVO);
						} else {
							LOGGER.debug("[이수증강제발행승인거부]");
							isMakeCertificate = false;//이수가 완료되지 않은 사용자는 이수증을 발급하지 않음
						}
					}
				} 
				
				if(isMakeCertificate) {
					LOGGER.debug("[이수증열람처리]");
					log_msg.append("[CRTF_SN="+info.getCRTF_SN()+",MBR_ID="+info.getMBR_ID());
					
					//이수증 데이터가 없을 경우 재발급한다.
					try {
						//회원정보
						EduMemberVO eduMemberVO = new EduMemberVO();
						eduMemberVO.setMBR_ID(info.getMBR_ID());
						eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
						log_dscrp.append("[이름"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+",이수증코드:"+info.getCRTF_SN()+")]");
						//회원부가상세정보
						EduMemberVO eduMemberDtlVO = new EduMemberVO();
						eduMemberDtlVO.setMBR_ID(info.getMBR_ID());
						eduMemberDtlVO.setUSE_AT("Y");
						eduMemberDtlVO.setHIDE_AT("N");
						List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
						//교육과정정보
						EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
						eduCurriculumVO.setCRS_SN(info.getCRS_SN());
						eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
						EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
						newInstanceItem.setCRS_SN(info.getCRS_SN());
						newInstanceItem.setHMBR_SN(info.getHMBR_SN());
						newInstanceItem.setLRNNG_CMPLT_ST("1");
						newInstanceItem.setUSE_ST("1");
						newInstanceItem.setDEL_ST("0");
						EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);			
						//점수 재계산
						/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
						chkEduMyHistoryVO.setHMBR_INPUT_TIME(eduCurriculumVO.getCRS_TOT_TIME());
						chkEduMyHistoryVO.setHMBR_INPUT_POINT(eduCurriculumVO.getCRS_TOT_POINT());
						/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
						int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());//교과목이수종합시간
						int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());//교과목이수종합점수
						int HMBR_INPUT_TIME = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_TIME());//최대인정교육시간
						int HMBR_INPUT_POINT = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_POINT());//최대인정교육점수
						int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
						int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
						chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
						chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
						/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
						newInstanceItem.setHMBR_INPUT_TIME(String.valueOf(HMBR_INPUT_TIME));
						newInstanceItem.setHMBR_INPUT_POINT(String.valueOf(HMBR_INPUT_POINT));
						newInstanceItem.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
						newInstanceItem.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
						/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
						//발급기관정보
						EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
						eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
						eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
						//새로 발급할 이수증
						if(info.getCRTF_CD()==null || info.getCRTF_CD().length()==0) {
							eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());	
						} else {
							eduCertificateVO.setCRTF_CD(info.getCRTF_CD());
						}
						eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());
						eduCertificateVO.setREG_MBR_ID(chkEduMyHistoryVO.getLRNNG_CMPLT_DT());
						eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
						//String insertId = eduCertificateVO.getCRTF_CD();
						info.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
								request,
								propertiesService,
								eduMemberVO,
								list_mbr_dtl,
								eduCertificateVO,
								chkEduMyHistoryVO,
								eduCurriculumVO,
								eduCategoryInsInfVO).toDocument());
						eduCertificateService.set_edu_certificate_mod(info);
						log_dscrp.append("|이수증데이터재발급:성공");
					} catch(Exception e) {
						log_dscrp.append("|이수증데이터재발급:에러");
						log_msg.append("|이수증데이터재발급:에러("+e.toString()+")");
					}
					//
					log_dscrp.append("|발급사유:" + eduCertificateVO.getCRTF_TYPE_ST());					

					list.add(info);
					
					try {
						//이수증 발급시 내역남기기
						EduCertificateVO newEduCertificateVO = info;
						newEduCertificateVO.setCRTF_TYPE_ST(eduCertificateVO.getCRTF_TYPE_ST());
						newEduCertificateVO.setCRTF_CMPLT_ST("1");//관리자는 열람시 완료
						newEduCertificateVO.setREG_MBR_ID(loginVO.getMBR_ID());
						newEduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
						newEduCertificateVO.setUSE_ST("0");//관리자가 발급열람시에는 사용자에 보여지지 않는다
						eduCertificateService.set_edu_certificate_issue_record_dtl_reg(newEduCertificateVO);
						//String insertId = eduCertificateService.set_edu_certificate_issue_record_dtl_reg(newEduCertificateVO);
						//tbl_sn.append(insertId+",");
						log_dscrp.append("|이수증발급내역기록함");		
					} catch(Exception e) {
						log_dscrp.append("|이수증발급내역기록:에러");
						log_msg.append("|이수증발급내역기록:에러("+e.toString()+")");
					}
					
				}
				log_dscrp.append("]");
			}			
		} else { //처리불가
			log_dscrp.append("[비정상적인접근으로거부됨]");		
		}
				
		
		try {
			model.addAttribute("html_header", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/header.html").toString());
		} catch(Exception e) {
			model.addAttribute("html_header","");
		}
		try {
			model.addAttribute("html_footer", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/footer.html").toString());
		} catch(Exception e) {
			model.addAttribute("html_footer","");
		}

		model.addAttribute("list", list);
		
		tbl_inf.append("EDU_CRTF_DTL_TB,");
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCertificateVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_MSG(eduCertificateVO.getLOG_UPD_MSG());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
		
		return "eduadm/certificate/popwin_view";
	}	
	//관리자(교육센터) 이수증 보기페이지 ------------------------------------------------
	@RequestMapping(value = "/eduadm/certificate/view.do")
	public String edu_certificate_view(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		EduCertificateVO info = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		model.addAttribute("info",info);
		
		if(info == null || info.getCRTF_SN() == null || info.getCRTF_SN().length() == 0) {
			//상위 정보가 없으면 일단 노출하지 않는다.
			return "eduadm/error/page_400";
		}
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[교육센터관리자-이수증열람하기-열람시생성]");		
		log_dscrp.append("|MBR_ID="+info.getMBR_ID());
		
		//이수증 데이터가 없을 경우 재발급한다.
		try {
			//회원정보
			EduMemberVO eduMemberVO = new EduMemberVO();
			eduMemberVO.setMBR_ID(info.getMBR_ID());
			eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
			log_dscrp.append("[이름"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+")]");
			//회원부가상세정보
			EduMemberVO eduMemberDtlVO = new EduMemberVO();
			eduMemberDtlVO.setMBR_ID(info.getMBR_ID());
			eduMemberDtlVO.setUSE_AT("Y");
			eduMemberDtlVO.setHIDE_AT("N");
			List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
			//교육과정정보
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(info.getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
			newInstanceItem.setCRS_SN(info.getCRS_SN());
			newInstanceItem.setHMBR_SN(info.getHMBR_SN());
			newInstanceItem.setLRNNG_CMPLT_ST("1");
			newInstanceItem.setUSE_ST("1");
			newInstanceItem.setDEL_ST("0");
			EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);			
			//점수 재계산
			/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
			chkEduMyHistoryVO.setHMBR_INPUT_TIME(eduCurriculumVO.getCRS_TOT_TIME());
			chkEduMyHistoryVO.setHMBR_INPUT_POINT(eduCurriculumVO.getCRS_TOT_POINT());
			/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
			int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());//교과목이수종합시간
			int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());//교과목이수종합점수
			int HMBR_INPUT_TIME = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_TIME());//최대인정교육시간
			int HMBR_INPUT_POINT = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_POINT());//최대인정교육점수
			int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
			int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
			chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
			chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
			/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
			newInstanceItem.setHMBR_INPUT_TIME(String.valueOf(HMBR_INPUT_TIME));
			newInstanceItem.setHMBR_INPUT_POINT(String.valueOf(HMBR_INPUT_POINT));
			newInstanceItem.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
			newInstanceItem.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
			/** 교육정보의 최대값 그대로 사용시 : 사용 안할시 주석 처리 */
			//발급기관정보
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
			eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
			//새로 발급할 이수증
			if(info.getCRTF_CD()==null || info.getCRTF_CD().length()==0) {
				eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());	
			} else {
				eduCertificateVO.setCRTF_CD(info.getCRTF_CD());
			}
			//eduCertificateVO.setREG_MBR_ID(loginVO.getMBR_ID());
			eduCertificateVO.setREG_MBR_ID(chkEduMyHistoryVO.getLRNNG_CMPLT_DT());
			eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			//String insertId = eduCertificateVO.getCRTF_CD();
			info.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
					request,
					propertiesService,
					eduMemberVO,
					list_mbr_dtl,
					eduCertificateVO,
					chkEduMyHistoryVO,
					eduCurriculumVO,
					eduCategoryInsInfVO).toDocument());
			eduCertificateService.set_edu_certificate_mod(info);
			log_dscrp.append("|이수증데이터재발급:성공");
		} catch(Exception e) {
			log_dscrp.append("|이수증데이터재발급:에러");
			log_msg.append("|이수증데이터재발급:에러("+e.toString()+")");
		}
		//
		log_dscrp.append("|발급사유:" + eduCertificateVO.getCRTF_TYPE_ST());
		
		
		//이수증 발급시 내역남기기
		EduCertificateVO newEduCertificateVO = info;
		newEduCertificateVO.setCRTF_TYPE_ST("관리자열람["+eduCertificateVO.getCRTF_TYPE_ST()+"]");
		newEduCertificateVO.setCRTF_CMPLT_ST("1");//관리자는 열람시 완료
		newEduCertificateVO.setREG_MBR_ID(loginVO.getMBR_ID());
		newEduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
		newEduCertificateVO.setUSE_ST("0");//관리자가 발급열람시에는 사용자에 보여지지 않는다
		String insertId = eduCertificateService.set_edu_certificate_issue_record_dtl_reg(newEduCertificateVO);
		
		tbl_inf.append("EDU_CRTF_DTL_TB,");
		tbl_sn.append(insertId+",");
		
		//CTI로그기록
		if(eduCertificateVO.getLOG_TYPE()!=null && eduCertificateVO.getLOG_TYPE().length()!=0) {
			if(eduCertificateVO.getLOG_TYPE().equals("cti")) {
				//CTI LOG 기록
  	  			logRecordService.set_log_cti_data("이수증출력", log_dscrp.toString(), log_msg.toString(), eduCertificateVO.getLOG_UPD_MSG(), info, eduCertificateVO, loginVO, request);
  	  			//End of CTI LOG 기록
			}
		}
		//End of CTI로그기록

		try {
			model.addAttribute("html_header", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/header.html").toString());
		} catch(Exception e) {
			model.addAttribute("html_header","");
		}
		try {
			model.addAttribute("html_footer", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/footer.html").toString());
		} catch(Exception e) {
			model.addAttribute("html_footer","");
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduCertificateVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_MSG(eduCertificateVO.getLOG_UPD_MSG());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record] "+e.toString());
		}
		
		return "eduadm/certificate/view";
	}	
	//관리자(교육센터) 이수증 (상세) 발급내역 리스트 ------------------------------------------------
	@RequestMapping(value = "/eduadm/certificate/listDtl.do")
	public String edu_certificate_listDtl(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO, 
			HttpServletRequest request, ModelMap model) throws Exception {
		
		List<EduCertificateVO> list = null;
		try {
			
			eduCertificateVO.setUSE_ST("0"); //관리자는 모두 보여야 하기 때문에.
			
			/** pageing setting */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(eduCertificateVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(eduCertificateVO.getPageUnit());
			paginationInfo.setPageSize(eduCertificateVO.getPageSize());

			eduCertificateVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			eduCertificateVO.setLastIndex(paginationInfo.getLastRecordIndex());
			eduCertificateVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			list = eduCertificateService.get_edu_certificate_dtl_list(eduCertificateVO);
			int totCnt = eduCertificateService.get_edu_certificate_dtl_list_totcnt(eduCertificateVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
		} catch(Exception e) {
			
		}
		model.addAttribute("list",list);
		model.addAttribute("searchCondition",eduCertificateVO.getSearchCondition());
		model.addAttribute("searchKeyword",eduCertificateVO.getSearchCondition().length()==0?"":eduCertificateVO.getSearchKeyword());		
		return "eduadm/certificate/list_dtl";
	}	
	//관리자(교육센터) 이수증 (상세) 발급내역 리스트 삭제 로직 ------------------------------------------------
	@RequestMapping(value="/eduadm/certificate/deleteDtl_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_edu_certificate_deleteDtl_act(@ModelAttribute("eduCertificateVO") EduCertificateVO eduCertificateVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();	
		try {
			//검증
			EduCertificateVO chkEduCertificateVO = eduCertificateService.get_edu_certificate_info_dtl(eduCertificateVO);
			if((chkEduCertificateVO.getMBR_ID()==null || chkEduCertificateVO.getMBR_ID().length()==0)
			&& (chkEduCertificateVO.getCRTF_DTL_SN()==null || chkEduCertificateVO.getCRTF_DTL_SN().length()==0)
			) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {
				
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();
				
				String DEL_ST = chkEduCertificateVO.getDEL_ST();
				if(DEL_ST.equals("1")) {
					log_dscrp.append("[교육센터관리자-이수증발급내역삭제-하위-실제데이터삭제]");
				} else {
					log_dscrp.append("[교육센터관리자-이수증발급내역삭제-하위]");
				}
				
				EduMemberVO eduMemberVO = new EduMemberVO();
				eduMemberVO.setMBR_ID(chkEduCertificateVO.getMBR_ID());
				eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
				log_dscrp.append("[이름"+eduMemberVO.getMBR_NM()+"(아이디:"+eduMemberVO.getMBR_ID()+")]");
				
				//메인
				eduCertificateVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				if(DEL_ST.equals("1")) {
					eduCertificateService.remove_edu_certificate_dtl(eduCertificateVO);
				} else {
					eduCertificateService.del_edu_certificate_dtl(eduCertificateVO);
				}
				
				tbl_inf.append("EDU_CRTF_DTL_TB,");
				tbl_sn.append(eduCertificateVO.getCRTF_DTL_SN()+",");
				
				data.put("error", "0");
				data.put("msg", "삭제되었습니다.");
				
				try {	
					/**
					 * LOG RECORED (로그기록)
					 * */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(chkEduCertificateVO));
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
				
			}
		} catch(Exception e) {
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
}


