package egovframework.adm.sms.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.member.service.AdmMemberService;
import egovframework.adm.member.service.AdmMemberVO;
import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsMentVO;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.certificate.web.CreateCertificateToHtmlData;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.educenter.myhistory.service.MyHistoryService;
import egovframework.educenter.myhistory.service.MyHistoryVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class SmsManagerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsManagerController.class);
	
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "smsManagerService")
	private SmsManagerService smsManagerService;
	
	@Resource(name = "admMemberService")
	private AdmMemberService admMemberService;
	
	/** EduCategoryService */
	@Resource(name = "myHistoryService")
	private MyHistoryService myHistoryService;
	
	/** EduCertificateService */
	@Resource(name = "eduCertificateService")
	private  EduCertificateService eduCertificateService;

	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	/** EduCurriculumService */
	@Resource(name = "eduCurriculumService")
	private EduCurriculumService eduCurriculumService;
	
	/** EduMyHistoryService */
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;

	/** EduCategoryService */
	@Resource(name = "eduCategoryService")
	private EduCategoryService eduCategoryService;
	
	
	//관리자(공통) 문자 메세지 보내기 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/send/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_adm_sms_send_write(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,
			@RequestParam(value="chkedMbrIds",required=false) String chkedMbrIds,
			@RequestParam(value="chkedMbrHps",required=false) String chkedMbrHps,
			@RequestParam(value="chkedMbrNms",required=false) String chkedMbrNms,
			@RequestParam(value="chkedHMbrDtlSns",required=false) String chkedHMbrDtlSns,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		PublicUtils mPublicUtils = new PublicUtils();
		
		String returnUrl = "";
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();		
		log_dscrp.append("[관리자(SMS관리)-메시지보내기-준비화면]");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_LV_ID()=="10") {
			log_dscrp.append("[실퍠:권한이없으며비정상적인접근]");
			returnUrl = "adm/error/modal_400";
		}
		ModelAndView mModelAndView = new ModelAndView();		
		try {
			List<AdmMemberVO> list_mbr = new ArrayList<AdmMemberVO>();
			
			if(chkedMbrIds != null && chkedMbrIds.length() != 0) {
				String[] praseMbrIds = chkedMbrIds.replaceAll("\\s","").split(",");
				for(String mbr_id : praseMbrIds) {
					if(mbr_id==null) {
						log_dscrp.append("[비정상적인아이디("+mbr_id+")이므로|처리불가]");
						continue;					
					}
					//검증
					AdmMemberVO chkAdmMemberVO = new AdmMemberVO();
					chkAdmMemberVO.setMBR_ID(mbr_id);
					chkAdmMemberVO.setMBR_ST("Y");
					chkAdmMemberVO = admMemberService.get_member_all_info(chkAdmMemberVO);
					if(chkAdmMemberVO==null || chkAdmMemberVO.getMBR_ID()==null || chkAdmMemberVO.getMBR_ID().length()==0) {
						log_dscrp.append("|존재하지 않는 회원(아이디:"+mbr_id+")");
					} else {
						list_mbr.add(chkAdmMemberVO);
						log_dscrp.append("|"+chkAdmMemberVO.getMBR_NM()+"(아이디:"+chkAdmMemberVO.getMBR_ID()+")");
					}
					log_dscrp.append("]");
				}
			}
			
			if(chkedMbrHps != null && chkedMbrHps.length() != 0) {
				String[] parseMbrHps = chkedMbrHps.replaceAll("\\s","").split(",");
				String[] parseMbrNms = chkedMbrNms.replaceAll("\\s","").split(",");
				for(int i = 0; i < parseMbrHps.length; i++){
					String mbr_hp = parseMbrHps[i];
					String mbr_nm = parseMbrNms[i];
					
					if(mbr_hp==null) {
						log_dscrp.append("[비정상적인 연락처("+mbr_hp+")이므로|처리불가]");
						continue;					
					}
					
					AdmMemberVO chkAdmMemberVO = new AdmMemberVO();
					chkAdmMemberVO.setMBR_HP(mbr_hp);
					chkAdmMemberVO.setMBR_ID(mbr_nm);
					chkAdmMemberVO.setMBR_NM(mbr_nm);
					list_mbr.add(chkAdmMemberVO);
					log_dscrp.append("|"+ mbr_nm + "(연락처 : "+ mbr_hp + ")");
				}
			}
			
			//동영상외부링크 용 문자발송 처리
			if(chkedHMbrDtlSns != null && chkedHMbrDtlSns.length() != 0) {
				StringBuilder messageStr = new StringBuilder();
				String domainUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
				String[] parseHMbrDtlSn = chkedHMbrDtlSns.replaceAll("\\s","").split(",");
				String surveykey = "";
				for(String hmbr_dtl_sn : parseHMbrDtlSn) {
					if(hmbr_dtl_sn==null) {
						log_dscrp.append("[비정상적인교과목이수번호("+hmbr_dtl_sn+")이므로|처리불가]");
						continue;					
					}
					MyHistoryVO chkDtlMyHistoryVO = new MyHistoryVO();
					chkDtlMyHistoryVO.setHMBR_DTL_SN(hmbr_dtl_sn);
					chkDtlMyHistoryVO = myHistoryService.get_educenter_mbrhstry_dtl_info(chkDtlMyHistoryVO);
					if(chkDtlMyHistoryVO!=null && chkDtlMyHistoryVO.getHMBR_DTL_SN()!=null && chkDtlMyHistoryVO.getHMBR_DTL_SN().length()!=0) {
						messageStr.append("[ "+chkDtlMyHistoryVO.getCRS_DTL_NM()+ " ] [ " + chkDtlMyHistoryVO.getTRN_NM()+" ] 동영상을 수강하시려면 아래 링크를 눌러주세요."+"\n");
						messageStr.append(domainUrl+"/educenter/mbrhstry/external/play.do?key="+EgovFileScrty.encode(hmbr_dtl_sn)+"\n\n");
						surveykey = EgovFileScrty.encode(chkDtlMyHistoryVO.getHMBR_SN()+","+chkDtlMyHistoryVO.getCRS_SN());
					} else {
						log_dscrp.append("[알수없는교과목이수번호("+hmbr_dtl_sn+")이므로|처리불가]");
					}
				}
				
				messageStr.append("모든 동영상을 수강 하신 뒤 아래 링크를 눌러 설문조사를 완료해야 이수완료 처리됩니다."+"\n");
				messageStr.append(domainUrl+"/educenter/mbrhstry/external/survey.do?key="+surveykey+"\n\n");
				
				mModelAndView.addObject("SMS_MENT_CONT",messageStr.toString());
				LOGGER.debug(messageStr.toString());
			}
			//
						
			mModelAndView.addObject("list_mbr",list_mbr);
			mModelAndView.addObject("chkedMbrHps",chkedMbrHps);
			mModelAndView.addObject("chkedMbrNms",chkedMbrNms);
			
			//템플릿 정보 조회
			SmsMentVO mSmsMentVO = new SmsMentVO();
			mSmsMentVO.setUSE_ST("1");
			mSmsMentVO.setNotUsedPagination(true);
			List<SmsMentVO> list_sms_ment = smsManagerService.get_sms_ment_list(mSmsMentVO);
			mModelAndView.addObject("list_sms_ment",list_sms_ment);
			//
			
			mModelAndView.addObject("SEND_NUMBER",propertiesService.getString("Globals.SmsSenderNumber"));
			
			returnUrl = "adm/sms/send/write";
			
		} catch(Exception e) {
			log_dscrp.append("[실패:처리오류발생]");
			LOGGER.debug("[실패:처리오류발생("+e.toString()+")]");
			returnUrl = "adm/error/modal_400";
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
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record]"+e.toString());
		}	
		
		mModelAndView.setViewName(returnUrl);
		return mModelAndView;
	}

	//관리자(공통) 문자 메세지 보내기 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/send/certificate/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_adm_sms_send_certificate_write(@ModelAttribute("myHistoryVO") MyHistoryVO myHistoryVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		PublicUtils mPublicUtils = new PublicUtils();
		
		String returnUrl = "";
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();		
		log_dscrp.append("[관리자(SMS관리)-메시지보내기-준비화면]");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_LV_ID()=="10") {
			log_dscrp.append("[실퍠:권한이없으며비정상적인접근]");
			returnUrl = "adm/error/modal_400";
		}
		ModelAndView mModelAndView = new ModelAndView();		
		try {
			myHistoryVO.setMBR_ID(myHistoryVO.getMBR_ID());	//필수값 ID, CRS_SN
			myHistoryVO = myHistoryService.get_educenter_mbrhstry_info(myHistoryVO);
			
			EduCertificateVO eduCertificateVO = new EduCertificateVO();
		    eduCertificateVO.setCRS_SN(myHistoryVO.getCRS_SN());
		    eduCertificateVO.setHMBR_SN(myHistoryVO.getHMBR_SN());
		    eduCertificateVO.setMBR_ID(myHistoryVO.getMBR_ID());
		    eduCertificateVO = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
		    
		    // 회원정보
			EduMemberVO eduMemberVO = new EduMemberVO();
			eduMemberVO.setMBR_ID(myHistoryVO.getMBR_ID());
			eduMemberVO = eduMemberService.get_edu_member_info(eduMemberVO);
			
			// 회원부가상세정보
			EduMemberVO eduMemberDtlVO = new EduMemberVO();
			eduMemberDtlVO.setMBR_ID(myHistoryVO.getMBR_ID());
			eduMemberDtlVO.setUSE_AT("Y");
			eduMemberDtlVO.setHIDE_AT("N");
			List<EduMemberVO> list_mbr_dtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
			
			//교육과정정보
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(myHistoryVO.getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			EduMyHistoryVO newInstanceItem = new EduMyHistoryVO();
			newInstanceItem.setCRS_SN(myHistoryVO.getCRS_SN());
			newInstanceItem.setHMBR_SN(myHistoryVO.getHMBR_SN());
			newInstanceItem.setLRNNG_CMPLT_ST("1");
			newInstanceItem.setUSE_ST("1");
			newInstanceItem.setDEL_ST("0");
			EduMyHistoryVO chkEduMyHistoryVO = eduMyHistoryService.get_edu_mbrhstry_info(newInstanceItem);
			
			//발급기관정보
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
			eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
		    
		    eduCertificateVO.setCRTF_HTML_DATA(new CreateCertificateToHtmlData(
					request,
					propertiesService,
					eduMemberVO,
					list_mbr_dtl,
					eduCertificateVO,
					chkEduMyHistoryVO,
					eduCurriculumVO,
					eduCategoryInsInfVO).toDocument());
		    
		    mModelAndView.addObject("item",eduCertificateVO);
		    
		    try {
		    	mModelAndView.addObject("html_header", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/header.html").toString());
			} catch(Exception e) {
				mModelAndView.addObject("html_header","");
			}
			try {
				mModelAndView.addObject("html_footer", mPublicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/footer.html").toString());
			} catch(Exception e) {
				mModelAndView.addObject("html_footer","");
			}
			
			mModelAndView.addObject("SEND_NUMBER",propertiesService.getString("Globals.SmsSenderNumber"));
			
			returnUrl = "adm/sms/send/certificate_write";
			
		} catch(Exception e) {
			log_dscrp.append("[실패:처리오류발생]");
			LOGGER.debug("[실패:처리오류발생("+e.toString()+")]");
			returnUrl = "adm/error/modal_400";
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
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record]"+e.toString());
		}	
		
		mModelAndView.setViewName(returnUrl);
		return mModelAndView;
	}
	
	//관리자(공통) 문자 메세지 보내기 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/send/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_sms_send_write_act(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,
			@RequestParam(value="chkedMbrIds",required=false) String chkedMbrIds,
			@RequestParam(value="chkedMbrHps",required=false) String chkedMbrHps,
			@RequestParam(value="chkedMbrNms",required=false) String chkedMbrNms,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
		JSONObject data = new JSONObject();	
		try {
		
			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder log_msg = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();
			
			log_dscrp.append("[관리자(SMS관리)-메세지보내기-문자발송]");
			tbl_inf.append("NAKSINURI_SMS_QUEUE");
			
			if(chkedMbrHps != null && chkedMbrHps.length() != 0) {
				String[] parseMbrHps = chkedMbrHps.replaceAll("\\s","").split(",");
				String[] parseMbrNms = chkedMbrNms.replaceAll("\\s","").split(",");

				for(int i = 0; i < parseMbrHps.length; i++){
					String mbr_hp = parseMbrHps[i];
					String mbr_nm = parseMbrNms[i];
					
					if(mbr_hp==null) {
						log_dscrp.append("[비정상적인 연락처("+mbr_hp+")이므로|처리불가]");
						continue;					
					}
				
					SmsSendVO newSmsSendVO = new SmsSendVO();
					//mSmsSendVO.setMSG_TYPE();//서비스에서 자동 처리
					//newSmsSendVO.setAPIKEY();//서비스에서 자동 처리
					//newSmsSendVO.setRSTKEY();//서비스에서 자동 처리
					newSmsSendVO.setMBR_ID("");
					newSmsSendVO.setSMS_MENT_DTL_CD(smsSendVO.getSMS_MENT_DTL_CD());
					newSmsSendVO.setMSG(smsSendVO.getMSG());
					if(smsSendVO.getSTAT()==5) {
						newSmsSendVO.setSEND_DATE(smsSendVO.getSEND_DATE());
					}
					newSmsSendVO.setSTAT(smsSendVO.getSTAT());
					newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));//발신번호
					newSmsSendVO.setR_PHONE(mbr_hp);
					newSmsSendVO.setSUBMSG("낚시전문교육");
					newSmsSendVO.setIMG_CNT(0);
					newSmsSendVO.setIMG_PATH("");
					newSmsSendVO.setREG_MBR_ID(MASTER_MBR_ID);
					newSmsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);			
				    newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
					String rstMsg = smsManagerService.sendToMessage(newSmsSendVO);
					log_msg.append(rstMsg);
					log_dscrp.append("|발송처리완료]");
					log_dscrp.append("["+ mbr_nm +"(연락처:"+ mbr_hp +")발송완료");
				
					log_dscrp.append("]");
				}
			}
			
			if(chkedMbrIds != null && chkedMbrIds.length() != 0) { 
				String[] praseMbrIds = chkedMbrIds.replaceAll("\\s","").split(",");
				for(String mbr_id : praseMbrIds) {
					if(mbr_id==null) {
						log_dscrp.append("[비정상적인아이디("+mbr_id+")이므로|발송처리불가]");
						continue;					
					}
					//검증
					AdmMemberVO chkAdmMemberVO = new AdmMemberVO();
					chkAdmMemberVO.setMBR_ID(mbr_id);
					chkAdmMemberVO.setMBR_ST("Y");
					chkAdmMemberVO = admMemberService.get_member_all_info(chkAdmMemberVO);
					if(chkAdmMemberVO==null || chkAdmMemberVO.getMBR_ID()==null || chkAdmMemberVO.getMBR_ID().length()==0) {
						log_dscrp.append("[존재하지않는회원(아이디:"+mbr_id+")");
						log_msg.append("|(아이디:"+mbr_id+")유효하지않은회원으로발송처리실패");
					} else {
						SmsSendVO newSmsSendVO = new SmsSendVO();
						//mSmsSendVO.setMSG_TYPE();//서비스에서 자동 처리
						//newSmsSendVO.setAPIKEY();//서비스에서 자동 처리
						//newSmsSendVO.setRSTKEY();//서비스에서 자동 처리
						newSmsSendVO.setMBR_ID(chkAdmMemberVO.getMBR_ID());
						newSmsSendVO.setSMS_MENT_DTL_CD(smsSendVO.getSMS_MENT_DTL_CD());
						newSmsSendVO.setMSG(smsSendVO.getMSG());
						if(smsSendVO.getSTAT()==5) {
							newSmsSendVO.setSEND_DATE(smsSendVO.getSEND_DATE());
						}
						newSmsSendVO.setSTAT(smsSendVO.getSTAT());
						newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));//발신번호
						newSmsSendVO.setR_PHONE(chkAdmMemberVO.getMBR_HP());
						newSmsSendVO.setSUBMSG("낚시전문교육");
						newSmsSendVO.setIMG_CNT(0);
						newSmsSendVO.setIMG_PATH("");
						newSmsSendVO.setREG_MBR_ID(MASTER_MBR_ID);
						newSmsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);			
					    newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
						String rstMsg = smsManagerService.sendToMessage(newSmsSendVO);
						log_msg.append(rstMsg);
						log_dscrp.append("|발송처리완료]");
						log_dscrp.append("["+chkAdmMemberVO.getMBR_NM()+"(아이디:"+chkAdmMemberVO.getMBR_ID()+")발송완료");
					}
					log_dscrp.append("]");
				}
			}
			
			
			data.put("error", "0");
			if(smsSendVO.getSTAT()==5) {
				data.put("msg", "예약발송으로 등록 되었습니다.");
			} else {
				data.put("msg", "발송 되었습니다.");
			}
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(smsSendVO));
				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(MASTER_MBR_ID);
				mEduLogRecordVO.setMBR_LV(MASTER_MBR_LV_ID);
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO,request);
			} catch(Exception e) {
				LOGGER.debug("[fail log record]"+e.toString());
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
	
	
	//관리자(공통) 문자 예약 관리 목록 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/resve{addWebLink}/list.do")
	public String adm_sms_resve_list(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,
			@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smsSendVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smsSendVO.getPageUnit());
		paginationInfo.setPageSize(smsSendVO.getPageSize());
		
		smsSendVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smsSendVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smsSendVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<SmsMentVO> list = smsManagerService.get_sms_resve_list(smsSendVO);		
		int totCnt = smsManagerService.get_sms_resve_list_totcnt(smsSendVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",smsSendVO.getSearchCondition());
		model.addAttribute("SMS_MENT_DTL_CD",smsSendVO.getSMS_MENT_DTL_CD());
		model.addAttribute("searchKeyword",smsSendVO.getSearchCondition().length()==0?"":smsSendVO.getSearchKeyword());
				
		model.addAttribute("addWebLink",addWebLink);
	
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_target_se_cd",list_target_se_cd);
		}
		
		//지역 코드 조회 - 시도
		List<CodeSetVO> list_address_cd  = null;
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		//지역 코드 조회 - 시군구
		List<CodeSetVO> list_address_signgu_cd = null;
		if(smsSendVO!=null && smsSendVO.getSIDO_CD()!=null && smsSendVO.getSIDO_CD().length()!=0){
			CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID(smsSendVO.getSIDO_CD());
	  		list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		model.addAttribute("SIDO_CD",smsSendVO.getSIDO_CD());
		model.addAttribute("SIGNGU_CD",smsSendVO.getSIGNGU_CD());
		model.addAttribute("list_address_cd",list_address_cd);
		model.addAttribute("list_address_signgu_cd",list_address_signgu_cd);
		
		model.addAttribute("MBR_NM",smsSendVO.getMBR_NM());
		model.addAttribute("MBR_HP",smsSendVO.getMBR_HP());
		model.addAttribute("MBR_BIRTH",smsSendVO.getMBR_BIRTH());
		model.addAttribute("DTL_NM",smsSendVO.getDTL_NM());
		
		model.addAttribute("SEND_DATE_STR",smsSendVO.getSEND_DATE_STR());
		model.addAttribute("SEND_DATE_END",smsSendVO.getSEND_DATE_END());
		
		return "adm/sms/resve/list";
	}
	
	
	//관리자(공통) 문자 예약 관리 글수정 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/resve/modify.do", method = RequestMethod.POST)
	public ModelAndView ajax_sms_resve_modify(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		String returnUrl = "";
		ModelAndView mModelAndView = new ModelAndView();
		SmsSendVO info = smsManagerService.get_sms_resve_info(smsSendVO);
		if(info==null || info.getMID()==null || info.getMID().length()==0) {
			returnUrl = "adm/error/modal_400";
		} else {
			returnUrl = "adm/sms/resve/modify";	
		}
		//회원추가정보구분자
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_target_se_cd",list_target_se_cd);
		}
		mModelAndView.setViewName(returnUrl);
		mModelAndView.addObject("info",info);		
		return mModelAndView;
	}
	
	
	//관리자(공통) 문자 예약 관리 수정 로직 ------------------------------------------------
	@RequestMapping(value="/adm/sms/resve/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_sms_resve_modify_act(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		JSONObject data = new JSONObject();	
		log_dscrp.append("[관리자(SMS관리)-메세지예약관리-수정]");
		try {
			//검증
			SmsSendVO chkSmsSendVO = new SmsSendVO();
			chkSmsSendVO.setMID(smsSendVO.getMID());
			chkSmsSendVO = smsManagerService.get_sms_resve_info(chkSmsSendVO);
			if(chkSmsSendVO==null || chkSmsSendVO.getMID()==null || chkSmsSendVO.getMID().length()==0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
				log_dscrp.append("[실패:비정상적인접근]");
			} else {
				log_dscrp.append("[예약번호:"+chkSmsSendVO.getMID()+",연락처:"+chkSmsSendVO.getR_PHONE()+"]");
				smsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);
				if(chkSmsSendVO.getSTAT()!=smsSendVO.getSTAT()) {
					if(smsSendVO.getSTAT()==5) {
						log_dscrp.append("[예약발송으로전환]");
					} else {
						log_dscrp.append("[즉시발송으로전환]");
					}
				}
				if(!chkSmsSendVO.getSEND_DATE().replace(".0", "").equals(smsSendVO.getSEND_DATE())) {
					log_dscrp.append("[발송시간변경("+chkSmsSendVO.getSEND_DATE()+"=>"+smsSendVO.getSEND_DATE()+")]");
				}
				smsManagerService.set_sms_resve_info_mod(smsSendVO);
				
				if(chkSmsSendVO.getMSG().getBytes().length > 80
				&& chkSmsSendVO.getTR_NUM() != null && chkSmsSendVO.getTR_NUM().length() != 0){
					smsSendVO.setTR_MSG(smsSendVO.getMSG());
					smsSendVO.setTR_SENDDATE(smsSendVO.getSEND_DATE());
					smsSendVO.setTR_NUM(chkSmsSendVO.getTR_NUM());
					smsManagerService.set_sc_tran_edu_resve_mod(smsSendVO);	// sms 실제 문자 발송 테이블에서 수정
					tbl_inf.append("SC_TRAN_EDU");
				} else {
					smsSendVO.setREQDATE(smsSendVO.getSEND_DATE());
					smsSendVO.setMSGKEY(chkSmsSendVO.getMSGKEY());
					smsManagerService.set_mms_msg_edu_resve_mod(smsSendVO);	// mms 실제 문자 발송 테이블에서 수정
					tbl_inf.append("MMS_MSG_EDU");
				}
				
				data.put("error", "0");
				data.put("msg", "수정 되었습니다.");
				
				tbl_inf.append(",NAKSINURI_SMS_QUEUE");
				tbl_sn.append(smsSendVO.getMID());
								
			}
		} catch(Exception e) {
			log_dscrp.append("[실패:처리오류]");
			log_msg.append("[실패:처리오류("+e.toString()+")]");
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(smsSendVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record]"+e.toString());
		}	
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	
	//관리자(공통) 문자 예약 관리 삭제 로직 ------------------------------------------------
	@RequestMapping(value="/adm/sms/resve/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_sms_resve_delete_act(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
					
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		JSONObject data = new JSONObject();
		
		log_dscrp.append("[관리자(SMS관리)-메세지예약관리-삭제");
		tbl_inf.append("NAKSINURI_SMS_QUEUE");
		
		try {
			//검증
			SmsSendVO chkSmsSendVO = new SmsSendVO();
			chkSmsSendVO.setMID(smsSendVO.getMID());
			chkSmsSendVO = smsManagerService.get_sms_resve_info(chkSmsSendVO);
			
			if(chkSmsSendVO==null || chkSmsSendVO.getMID()==null || chkSmsSendVO.getMID().length()==0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
				log_dscrp.append("대상자번호:"+chkSmsSendVO.getR_PHONE()+"][실패:비정상적인접근]");
			} else {
				smsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);
				String DEL_ST = chkSmsSendVO.getDEL_ST();
				if(DEL_ST.equals("1")) {
					log_dscrp.append("-실제데이터삭제]");
					data.put("msg", "삭제 되었습니다.");
					smsManagerService.remove_sms_resve(smsSendVO);
				} else {
					log_dscrp.append("-1단계]");
					data.put("msg", "삭제 되었습니다.<br>한번 더 삭제하시면 복구가 불가능합니다.");
					smsManagerService.del_sms_resve(smsSendVO);
					
					if(chkSmsSendVO.getMSG().getBytes().length > 80 
					&& chkSmsSendVO.getTR_NUM() != null && chkSmsSendVO.getTR_NUM().length() != 0){
						smsSendVO.setTR_NUM(chkSmsSendVO.getTR_NUM());
						smsManagerService.remove_sc_tran_edu_resve(smsSendVO);	// sms 실제 문자 발송 테이블에서 삭제
						tbl_inf.append(",SC_TRAN_EDU");
					} else {
						smsSendVO.setMSGKEY(chkSmsSendVO.getMSGKEY());
						smsManagerService.remove_mms_msg_edu_resve(smsSendVO);	// mms 실제 문자 발송 테이블에서 수정
						tbl_inf.append(",MMS_MSG_EDU");
					}
				}
				data.put("error", "0");
				log_dscrp.append("[대상자번호:"+chkSmsSendVO.getR_PHONE()+" 처리완료]");
				tbl_sn.append(smsSendVO.getMID());
			}
		} catch(Exception e) {
			data.put("error", "1");
			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
			log_dscrp.append("][실패:처리오류발생]");
			log_msg.append("[실패:처리오류발생("+e.toString()+")]");
		}
		
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(smsSendVO));
			mEduLogRecordVO.setLOG_MSG(log_msg.toString());
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record]"+e.toString());
		}
		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	
	//관리자(공통) 문자 멘트 정보 호출 ------------------------------------------------
	@RequestMapping(value="/adm/sms/ment/call.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_sms_ment_call(@ModelAttribute("smsMentVO") SmsMentVO smsMentVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		SmsMentVO rstSmsMentVO = new SmsMentVO();
		try {
			smsMentVO = smsManagerService.get_sms_ment_info(smsMentVO);
			if(smsMentVO!=null && smsMentVO.getSMS_MENT_SN()!=null && smsMentVO.getSMS_MENT_SN().length()!=0 ) {
				rstSmsMentVO = smsMentVO;
			}
		} catch(Exception e) {
			LOGGER.debug("[요청실패("+e.toString()+")]");
		}		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
	
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(mapper.writeValueAsString(rstSmsMentVO));
		return null;
	}
	
	
	//관리자(공통) 문자 멘트 목록 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/ment{addWebLink}/list.do")
	public String adm_sms_ment_list(@ModelAttribute("smsMentVO") SmsMentVO smsMentVO,
			@PathVariable("addWebLink") String addWebLink,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smsMentVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smsMentVO.getPageUnit());
		paginationInfo.setPageSize(smsMentVO.getPageSize());
		
		smsMentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smsMentVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smsMentVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<SmsMentVO> list = smsManagerService.get_sms_ment_list(smsMentVO);		
		int totCnt = smsManagerService.get_sms_ment_list_totcnt(smsMentVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",smsMentVO.getSearchCondition());
		model.addAttribute("searchKeyword",smsMentVO.getSearchCondition().length()==0?"":smsMentVO.getSearchKeyword());
				
		model.addAttribute("addWebLink",addWebLink);
		
		
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_target_se_cd",list_target_se_cd);
		}
		
		return "adm/sms/ment/list";
	}
	
	
	//관리자(공통) 문자 멘트 신규 글작성 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/ment/write.do", method = RequestMethod.POST)
	public ModelAndView ajax_sms_ment_write(@ModelAttribute("smsMentVO") SmsMentVO smsMentVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		ModelAndView mModelAndView = new ModelAndView();
		//회원추가정보구분자
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_target_se_cd",list_target_se_cd);
		}
		mModelAndView.setViewName("adm/sms/ment/write");
		return mModelAndView;
	}
	
	
	//관리자(공통) 문자 멘트 글수정 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/ment/modify.do", method = RequestMethod.POST)
	public ModelAndView ajax_sms_ment_modify(@ModelAttribute("smsMentVO") SmsMentVO smsMentVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
		String returnUrl = "";
		ModelAndView mModelAndView = new ModelAndView();
		SmsMentVO info = smsManagerService.get_sms_ment_info(smsMentVO);
		if(info==null || info.getSMS_MENT_SN()==null || info.getSMS_MENT_SN().length()==0) {
			returnUrl = "adm/error/modal_400";
		} else {
			returnUrl = "adm/sms/ment/modify";	
		}
		//회원추가정보구분자
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		mModelAndView.addObject("list_target_se_cd",list_target_se_cd);
		}
		mModelAndView.setViewName(returnUrl);
		mModelAndView.addObject("info",info);		
		return mModelAndView;
	}
	
	
	
	//관리자(공통) 문자 멘트 추가 로직 ------------------------------------------------
	@RequestMapping(value="/adm/sms/ment/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_sms_ment_write_act(@ModelAttribute("smsMentVO") SmsMentVO smsMentVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		JSONObject data = new JSONObject();	
		try {
			
			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();
			
			log_dscrp.append("[관리자(SMS관리)-메시지탬플릿-추가]");
			log_dscrp.append("[게시물:"+smsMentVO.getSMS_MENT_TITLE()+"]");
			
			tbl_inf.append("ALL_SMS_MENT");
			
			smsMentVO.setREG_MBR_ID(MASTER_MBR_ID);
			smsMentVO.setUPD_MBR_ID(MASTER_MBR_ID);
					
			smsManagerService.set_sms_ment_info_reg(smsMentVO);
			
			data.put("error", "0");
			data.put("msg", "등록 되었습니다.");
							
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(smsMentVO));
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
				mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO,request);
			} catch(Exception e) {
				LOGGER.debug("[fail log record]"+e.toString());
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
	
	
	//관리자(공통) 문자 멘트 수정 로직 ------------------------------------------------
	@RequestMapping(value="/adm/sms/ment/modify_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_sms_ment_modify_act(@ModelAttribute("smsMentVO") SmsMentVO smsMentVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		JSONObject data = new JSONObject();	
		try {
			//검증
			SmsMentVO chkSmsMentVO = new SmsMentVO();
			chkSmsMentVO.setSMS_MENT_SN(smsMentVO.getSMS_MENT_SN());
			chkSmsMentVO = smsManagerService.get_sms_ment_info(chkSmsMentVO);
			if(chkSmsMentVO==null || chkSmsMentVO.getSMS_MENT_SN()==null || chkSmsMentVO.getSMS_MENT_SN().length()==0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {
				
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();
				
				log_dscrp.append("[관리자(SMS관리)-메시지탬플릿-수정]");
				log_dscrp.append("[게시물:"+smsMentVO.getSMS_MENT_TITLE()+"(게시물번호:"+smsMentVO.getSMS_MENT_SN()+")]");
				smsMentVO.setUPD_MBR_ID(MASTER_MBR_ID);
				
				smsManagerService.set_sms_ment_info_mod(smsMentVO);
				
				data.put("error", "0");
				data.put("msg", "수정 되었습니다.");
				
				tbl_inf.append("ALL_SMS_MENT");
				tbl_sn.append(smsMentVO.getSMS_MENT_SN());
								
				try {	
					/**
					 * LOG RECORED (로그기록)
					 * */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(smsMentVO));
					mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
					mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
					mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
					mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
					mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
					mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
					logRecordService.set_log_data(mEduLogRecordVO,request);
				} catch(Exception e) {
					LOGGER.debug("[fail log record]"+e.toString());
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
	
	
	//관리자(공통) 문자 멘트 삭제 로직 ------------------------------------------------
	@RequestMapping(value="/adm/sms/ment/delete_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_sms_ment_delete_act(@ModelAttribute("smsMentVO") SmsMentVO smsMentVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String MASTER_MBR_ID = loginVO.getMBR_ID();
		JSONObject data = new JSONObject();	
		try {
			//검증
			SmsMentVO chkSmsMentVO = new SmsMentVO();
			chkSmsMentVO.setSMS_MENT_SN(smsMentVO.getSMS_MENT_SN());
			chkSmsMentVO = smsManagerService.get_sms_ment_info(chkSmsMentVO);
			if(chkSmsMentVO==null || chkSmsMentVO.getSMS_MENT_SN()==null || chkSmsMentVO.getSMS_MENT_SN().length()==0) {
				data.put("error", "1");
				data.put("msg", "존재하지 않는 정보입니다.");
			} else {
				
				StringBuilder log_dscrp = new StringBuilder();
				StringBuilder tbl_inf = new StringBuilder();
				StringBuilder tbl_sn = new StringBuilder();
						
				smsMentVO.setUPD_MBR_ID(MASTER_MBR_ID);
				
				String DEL_ST = chkSmsMentVO.getDEL_ST();
				if(DEL_ST.equals("1")) {
					log_dscrp.append("[관리자(SMS관리)-메시지탬플릿-삭제-실제데이터삭제]");
				} else {
					log_dscrp.append("[관리자(SMS관리)-메세지탬플릿-삭제]");
				}
				log_dscrp.append("[게시물:"+smsMentVO.getSMS_MENT_TITLE()+"(게시물번호:"+smsMentVO.getSMS_MENT_SN()+")]");
				if(DEL_ST.equals("1")) {
					smsManagerService.remove_sms_ment(smsMentVO);
				} else {
					smsManagerService.del_sms_ment(smsMentVO);
				}
				
				data.put("error", "0");
				data.put("msg", "삭제 되었습니다.");
				
				tbl_inf.append("ALL_SMS_MENT");
				tbl_sn.append(smsMentVO.getSMS_MENT_SN());
								
				try {	
					/**
					 * LOG RECORED (로그기록)
					 * */
					LogRecordVO mEduLogRecordVO = new LogRecordVO();
					mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(smsMentVO));
					mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
					mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
					mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
					mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
					mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
					mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
					logRecordService.set_log_data(mEduLogRecordVO,request);
				} catch(Exception e) {
					LOGGER.debug("[fail log record]"+e.toString());
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
	
	
	//관리자(공통) 문자 이력 목록 페이지 ------------------------------------------------
	@RequestMapping(value = "/adm/sms/log{addWebLink}/list.do")
	public String adm_sms_log_list(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,
			@PathVariable("addWebLink") String addWebLink, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smsSendVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smsSendVO.getPageUnit());
		paginationInfo.setPageSize(smsSendVO.getPageSize());
		
		smsSendVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smsSendVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smsSendVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<SmsSendVO> list = smsManagerService.get_sms_log_list(smsSendVO);		
		int totCnt = smsManagerService.get_sms_log_list_totcnt(smsSendVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		//취약점 대응을 위한 처리
		for(SmsSendVO item : list) {
			item.setMBR_SCRTY_KEY(EgovFileScrty.security(item.getMBR_ID(), loginVO.getMBR_ID()));
		}
		//End of 취약점 대응을 위한 처리		
		model.addAttribute("list",list);
		model.addAttribute("searchCondition",smsSendVO.getSearchCondition());
		model.addAttribute("searchKeyword",smsSendVO.getSearchKeyword());
				
		model.addAttribute("addWebLink",addWebLink);
	
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_target_se_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_target_se_cd",list_target_se_cd);
		}
		
		//지역 코드 조회 - 시도
		List<CodeSetVO> list_address_cd  = null;
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00004");
	  		list_address_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		//지역 코드 조회 - 시군구
		List<CodeSetVO> list_address_signgu_cd = null;
		if(smsSendVO!=null && smsSendVO.getSIDO_CD()!=null && smsSendVO.getSIDO_CD().length()!=0){
			CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID(smsSendVO.getSIDO_CD());
	  		list_address_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
		}
		model.addAttribute("SIDO_CD",smsSendVO.getSIDO_CD());
		model.addAttribute("SIGNGU_CD",smsSendVO.getSIGNGU_CD());
		model.addAttribute("list_address_cd",list_address_cd);
		model.addAttribute("list_address_signgu_cd",list_address_signgu_cd);
		
		//model.addAttribute("MBR_NM",smsSendVO.getMBR_NM());
		model.addAttribute("R_PHONE",smsSendVO.getR_PHONE());
		//model.addAttribute("MBR_BIRTH",smsSendVO.getMBR_BIRTH());
		model.addAttribute("SMS_MENT_DTL_CD",smsSendVO.getSMS_MENT_DTL_CD());
		
		model.addAttribute("SEND_DATE_STR",smsSendVO.getSEND_DATE_STR());
		model.addAttribute("SEND_DATE_END",smsSendVO.getSEND_DATE_END());
		
		return "adm/sms/log/list";
	}
	
}


