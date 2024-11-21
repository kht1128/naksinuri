package egovframework.cti.mbrhstry.web;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsMentVO;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.cti.mbrhstry.service.CtiMyHistoryService;
import egovframework.cti.mbrhstry.service.CtiMyHistoryVO;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.eduadm.trainingdata.service.EduTrainingDataService;
import egovframework.eduadm.trainingdata.service.EduTrainingDataVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class CtiMyHistoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CtiMyHistoryController.class);
	
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	@Resource(name = "ctiMyHistoryService")
	private CtiMyHistoryService ctiMyHistoryService;
	
	@Resource(name = "eduCurriculumService")
	private EduCurriculumService eduCurriculumService;
	
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;
		
	@Resource(name = "eduTrainingDataService")
	private EduTrainingDataService eduTrainingDataService;
	
	@Resource(name = "smsManagerService")
	private SmsManagerService smsManagerService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	
	//교육이수내역
	@RequestMapping(value = "/cti/mbrhstry/ajax_list.do")
	public @ResponseBody String ajax_cti_history_list(boolean isExcelDownLoad, @ModelAttribute("ctiMyHistoryVO") CtiMyHistoryVO ctiMyHistoryVO, 
		SessionStatus status, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		JSONObject dataObj = new JSONObject();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			dataObj.put("error", "1");
			dataObj.put("msg", "접근 권한이 없습니다.");
		} else {
			
			//ctiMyHistoryVO.setLRNNG_ST("1");
			ctiMyHistoryVO.setLRNNG_CMPLT_ST("1");
			List<CtiMyHistoryVO> list = (List<CtiMyHistoryVO>) ctiMyHistoryService.get_edu_mbrhstry_list(ctiMyHistoryVO);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);			
			dataObj.put("error", "0");
			dataObj.put("msg", "정상");
			dataObj.put("rawdata",mapper.writeValueAsString(list));	
		}
		LOGGER.debug(dataObj.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(dataObj);
		return null;
	}
	
	//CTI전용 수강내역(회원) 등록
	@RequestMapping(value="/cti/mbrhstry/write_act.do", method = RequestMethod.POST)
	public @ResponseBody String ajax_cti_mbrhstry_write_act(@ModelAttribute("eduMyHistoryVO") EduMyHistoryVO eduMyHistoryVO,
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, ModelMap model) throws Exception {	
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();

		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder log_msg = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[CTI-수강내역-회원추가]");
		
		int insertCount = 0;
		int insertDtlCount = 0;
		int totcnt = 0;
		int failOverlapCnt = 0;
		int successcnt = 0;
		int failcnt = 0;
		boolean isRefuse = false;
		try {
			if(eduMyHistoryVO.getMBR_ID()!=null) {
				
				String MASTER_MBR_ID = loginVO.getMBR_ID();
  	  			String MASTER_MBR_LV_ID = loginVO.getMBR_LV_ID();
  	  			String MASTER_MBR_GRP_ID = loginVO.getMBR_GRP_ID();
  	  			String MASTER_MBR_POSITION_CD = loginVO.getMBR_POSITION_CD();
  	  			log_msg.append("[아이디:"+MASTER_MBR_ID+"]");
  	  			log_msg.append("[처리권한:"+MASTER_MBR_GRP_ID+"]");
  	  			log_msg.append("[처리레벨:"+MASTER_MBR_LV_ID+"]");
  	  			log_msg.append("[처리직급:"+MASTER_MBR_POSITION_CD+"]");
				
				//커리큘럼 메인
				String CRS_SN = eduMyHistoryVO.getCRS_SN();
				EduCurriculumVO parentInfo = new EduCurriculumVO();
				parentInfo.setCRS_SN(CRS_SN);
				parentInfo = eduCurriculumService.get_edu_curriculum_info(parentInfo);
				//커리큘럼 상세
				parentInfo.setNotUsedPagination(true);
				List<EduCurriculumVO> clildlist = eduCurriculumService.get_edu_curriculum_dtl_list(parentInfo);
				log_dscrp.append("["+parentInfo.getCRS_NM()+","+parentInfo.getCAT_INS_NM()+"("+parentInfo.getCRS_STR_DT()+")]");
				
				
				String[] mbrids = eduMyHistoryVO.getMBR_ID().replaceAll("\\s","").split(",");
				for(int i=0; i<mbrids.length; i++) {
					String traget_mbr_id = mbrids[i];
					LOGGER.debug("현재 회원 처리 중 : " + traget_mbr_id);
										
					EduMemberVO chkEduMemberVO = new EduMemberVO();
	  				chkEduMemberVO.setMBR_ID(traget_mbr_id);
	  				chkEduMemberVO = eduMemberService.get_edu_member_info(chkEduMemberVO);
	  				if(chkEduMemberVO==null || chkEduMemberVO.getMBR_ID()==null || chkEduMemberVO.getMBR_ID().length()==0) {
	  					log_dscrp.append("[존재하지않는회원(아이디:"+traget_mbr_id+")-수강생등록실패]");
	  					failcnt++;
	  				} else {
	  					
	  					log_dscrp.append("[이름:"+chkEduMemberVO.getMBR_NM()+"(아이디:"+traget_mbr_id+")");

	  					EduMyHistoryVO chkEduMyHistoryVO = new EduMyHistoryVO();
						chkEduMyHistoryVO.setCRS_SN(parentInfo.getCRS_SN());
						chkEduMyHistoryVO.setMBR_ID(traget_mbr_id);
						chkEduMyHistoryVO.setNotUsedPagination(true);
						List<EduMyHistoryVO> list_mbrhstry = eduMyHistoryService.get_edu_mbrhstry_list(chkEduMyHistoryVO);
						if(list_mbrhstry != null && list_mbrhstry.size() > 0 ) {
							log_msg.append(",이름:"+chkEduMemberVO.getMBR_NM()+"(아이디:"+traget_mbr_id+")|이미등록된수강생으로처리안함");
							log_dscrp.append("-이미등록된수강생으로처리안함");
							failOverlapCnt++;
						} else {
							
							//교육기간 검증
							PublicUtils.RETURN_COMPARE_TYPE dateStr = mPublicUtils.dateCompare(
									mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
									mPublicUtils.changePatternString(parentInfo.getRECRUIT_STR_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"
									);
							PublicUtils.RETURN_COMPARE_TYPE dateEnd = mPublicUtils.dateCompare(
									mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss",
									mPublicUtils.changePatternString(parentInfo.getRECRUIT_END_DT(), "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss" 
									);
							String status = "";
							if(parentInfo.getLOCK_ST().equals("1")) { //모집잠금해제상태 인 경우
								LOGGER.debug("모집 잠금!");
								status = "lock";
								log_msg.append("[처리불가:신청받지않음]");
								log_dscrp.append("[처리실패]");
								data.put("error", "1");
								data.put("msg", "해당 교육은 현재 모집이 중단 되었습니다.");
								isRefuse = true;
								failcnt++;
							} else if(parentInfo.getLOCK_ST().equals("0") //모집기간 확인
							&& !dateStr.equals(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02)
							&& !dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)
							) {
								LOGGER.debug("정상적인 교육신청 기간!");
								if(Integer.parseInt(parentInfo.getMBR_MAX_CNT())!=0 && Integer.parseInt(parentInfo.getMBR_CUR_CNT())+1 > Integer.parseInt(parentInfo.getMBR_MAX_CNT())) {
									LOGGER.debug("이미 접수 인원이 초과한 경우");
									status = "lock";
									log_msg.append("[처리불가:신청인원마감]");
									log_dscrp.append("[처리실패]");
									data.put("error", "1");
									data.put("msg", "해당 교육은 현재 신청인원이 초과되어 신청이 불가능 합니다.");
									isRefuse = true;
									failcnt++;									
								} else { 
									LOGGER.debug("접수 가능 인원!");
									status = "unlock";
								}
							} else {
								if(dateEnd.equals(PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02)) {
									LOGGER.debug("접수기간이 지난 경우");
									status = "lock";
									log_msg.append("[처리불가:신청받지않음]");
									log_dscrp.append("[처리실패]");
									data.put("error", "1");
									data.put("msg", "해당 교육은 현재 접수기간이 지나 신청이 불가능합니다.");
									isRefuse = true;
									failcnt++;
								} else {
									LOGGER.debug("현재 모집기간이 아니거나 모집이 중단 되었습니다.");
									status = "last";
									log_msg.append("[처리불가:신청받지않음]");
									log_dscrp.append("[처리실패]");
									data.put("error", "1");
									data.put("msg", "현재 교육은 접수기간이 아니거나 모집이 중단 되었습니다.");
									isRefuse = true;
									failcnt++;
								}
							}
							//End of 교육기간 검증
							
							if(!isRefuse) {
								log_dscrp.append("-수강생등록완료");							
			  					
								int HMBR_MAX_TIME = Integer.parseInt(parentInfo.getCRS_TOT_TIME()) + parentInfo.getSUM_TOT_TIME();
								int HMBR_MAX_POINT = Integer.parseInt(parentInfo.getCRS_TOT_POINT()) + parentInfo.getSUM_TOT_POINT();
								
								EduMyHistoryVO newEduMyHistoryVO = new EduMyHistoryVO();
								newEduMyHistoryVO.setPURCHASE_CMPLT_ST("1");//결제(구매) 처리는 현재 보류
								newEduMyHistoryVO.setCRS_SN(parentInfo.getCRS_SN());
								newEduMyHistoryVO.setMBR_ID(traget_mbr_id);
								newEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
								newEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
								newEduMyHistoryVO.setHMBR_MAX_TIME(String.valueOf(HMBR_MAX_TIME));
								newEduMyHistoryVO.setHMBR_MAX_POINT(String.valueOf(HMBR_MAX_POINT));
								
								String HMBR_SN = "";
								//중복 검증 구간
								{
									boolean isOk = false;
									do {
										HMBR_SN = newEduMyHistoryVO.getUniqKey("HMBR");
										isOk = eduMyHistoryService.get_edu_mbrhstry_dpcn_chk_ok_hmbr_sn(HMBR_SN);
									} while(!isOk);
								}
								//End of 중복 검증 구간
								newEduMyHistoryVO.setHMBR_SN(HMBR_SN);
								
								//메인 커리큘럼 정보 관련 나의이력 생성
								eduMyHistoryService.set_edu_mbrhstry_reg(newEduMyHistoryVO);
								
								tbl_inf.append("EDU_MBR_HSTRY_TB,");
								tbl_sn.append(HMBR_SN+",");
								
								//상세 커리큘럼 정보 만큼 나의상세이력생성
								int insertChildCount = 1;
								for(EduCurriculumVO crs_dtl : clildlist) {
									int insertSubCount = 0;
									String[] trnids = new String[1];
									if(crs_dtl!=null && crs_dtl.getTRN_SN()!=null && crs_dtl.getTRN_SN().length()!=0)
										trnids = crs_dtl.getTRN_SN().replaceAll("\\s","").split(",");
									for(String TRN_SN : trnids) {
										String CRS_TOT_TIME = crs_dtl.getCRS_TOT_TIME();
										String CRS_TOT_POINT = crs_dtl.getCRS_TOT_POINT();
										if(TRN_SN==null) continue;
										if(insertSubCount!=0) {
											CRS_TOT_TIME = "0";
											CRS_TOT_POINT = "0";
										}
										
										EduTrainingDataVO eduTrainingDataVO = new EduTrainingDataVO();
										eduTrainingDataVO.setTRN_SN(TRN_SN);
										EduTrainingDataVO tdata = eduTrainingDataService.get_edu_data_info(eduTrainingDataVO);
										
										EduMyHistoryVO newDtlEduMyHistoryVO = new EduMyHistoryVO();
										
										String HMBR_DTL_SN = "";
										//중복 검증 구간
										{
											boolean isOk = false;
											do {
												HMBR_DTL_SN = newDtlEduMyHistoryVO.getUniqKey("HMBRD");
												isOk = eduMyHistoryService.get_edu_mbrhstry_dpcn_chk_ok_hmbr_dtl_sn(HMBR_DTL_SN);
											} while(!isOk);
										}
										//End of 중복 검증 구간
										newDtlEduMyHistoryVO.setHMBR_ORD(String.valueOf(insertChildCount));
										newDtlEduMyHistoryVO.setTRN_MAX_TIME(tdata.getTRN_MAX_TIME());
										newDtlEduMyHistoryVO.setTRN_SN(TRN_SN);
										newDtlEduMyHistoryVO.setHMBR_SN(HMBR_SN);
										newDtlEduMyHistoryVO.setCRS_SN(crs_dtl.getCRS_SN());
										newDtlEduMyHistoryVO.setCRS_DTL_SN(crs_dtl.getCRS_DTL_SN());
										newDtlEduMyHistoryVO.setCAT_SN(crs_dtl.getCAT_SN());
										newDtlEduMyHistoryVO.setCAT_DTL_SN(crs_dtl.getCAT_DTL_SN());
										newDtlEduMyHistoryVO.setLRNNG_MAX_TIME(CRS_TOT_TIME);
										newDtlEduMyHistoryVO.setLRNNG_MAX_POINT(CRS_TOT_POINT);
										newDtlEduMyHistoryVO.setMBR_ID(traget_mbr_id);
										newDtlEduMyHistoryVO.setREG_MBR_ID(loginVO.getMBR_ID());
										newDtlEduMyHistoryVO.setUPD_MBR_ID(loginVO.getMBR_ID());
										newDtlEduMyHistoryVO.setHMBR_DTL_SN(HMBR_DTL_SN);
										eduMyHistoryService.set_edu_mbrhstry_reg_dtl(newDtlEduMyHistoryVO);
										
										tbl_inf.append("EDU_MBR_HSTRY_DTL_TB,");
										tbl_sn.append(HMBR_DTL_SN+",");
										
										insertSubCount++;
										insertDtlCount++;	
									}
									insertChildCount++;
								}
								//메인 커리큘럼에 회원 카운트 증가
								eduCurriculumService.set_edu_curriculum_mbr_cur_cnt_up(parentInfo);
								insertCount++;
								successcnt++;
								
								//연도별이수내역 체크
								String TRGT_YEAR = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0",""), "yyyy-MM-dd HH:mm:ss", "yyyy");
								
								//연도별 수강내역에 교육과정 업데이트
								boolean isExistTargetBean = false;
								boolean isExistTargetCrs = false;
								EduMemberVO updEduMemberVO = new EduMemberVO();
								updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
								updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
								updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
								updEduMemberVO.setCRS_SN_NULL("Y");
								List<EduMemberVO> chkTargetEduBeanList = eduMemberService.get_edu_member_target_all_list(updEduMemberVO);
								updEduMemberVO.setCRS_SN_NULL("");
								updEduMemberVO.setCRS_SN(newEduMyHistoryVO.getCRS_SN());
								updEduMemberVO.setHMBR_SN(newEduMyHistoryVO.getHMBR_SN());
								List<EduMemberVO> chkTargetEduList = eduMemberService.get_edu_member_target_all_list(updEduMemberVO);
								if(chkTargetEduBeanList==null || chkTargetEduBeanList.size() == 0) {
									isExistTargetBean = false;
								} else {
									isExistTargetBean = true;
								}
								if(chkTargetEduList==null || chkTargetEduList.size() == 0) {
									isExistTargetCrs = false;
								} else {
									isExistTargetCrs = true;
								}
								if(isExistTargetCrs) {
									log_dscrp.append("-기존연도별수강내역에업데이트");
									
									updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
									updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
									updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
									updEduMemberVO.setCRS_SN_NULL("");//CRS_SN null 일때
									updEduMemberVO.setCRS_SN_NOT_NULL("Y");//CRS_SN 일때
									updEduMemberVO.setCRS_SN(newEduMyHistoryVO.getCRS_SN());
									updEduMemberVO.setHMBR_SN(newEduMyHistoryVO.getHMBR_SN());
									
			  						eduMemberService.set_edu_member_target_mod(updEduMemberVO);
								} else {
									if(isExistTargetBean) {
										log_dscrp.append("-빈연도별수강내역에업데이트");
										
										updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
										updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
										updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
										updEduMemberVO.setCRS_SN_NULL("Y");//CRS_SN null 일때
										updEduMemberVO.setCRS_SN_NOT_NULL("");//CRS_SN 일때
										updEduMemberVO.setHMBR_SN_NULL("Y");//HMBR_SN null 일때
										updEduMemberVO.setCRS_SN(newEduMyHistoryVO.getCRS_SN());
										updEduMemberVO.setHMBR_SN(newEduMyHistoryVO.getHMBR_SN());
										
				  						eduMemberService.set_edu_member_target_mod(updEduMemberVO);
									} else {
										log_dscrp.append("-연도별수강내역에신규추가");
										
										updEduMemberVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
										updEduMemberVO.setMBR_CD(chkEduMemberVO.getMBR_CD());
										updEduMemberVO.setTRGT_YEAR(TRGT_YEAR);
										chkEduMemberVO.setREG_MBR_ID(MASTER_MBR_ID);
										updEduMemberVO.setUPD_MBR_ID(MASTER_MBR_ID);
										updEduMemberVO.setCRS_SN(newEduMyHistoryVO.getCRS_SN());
										updEduMemberVO.setHMBR_SN(newEduMyHistoryVO.getHMBR_SN());
										
				  						eduMemberService.set_edu_member_target_reg(updEduMemberVO);
									}
								}	
														
								//SMS자동발송(즉시)
								{
									String str_crs_dt = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
									String end_crs_dt = mPublicUtils.changePatternString(parentInfo.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
									String str_crs_str_time = mPublicUtils.changePatternString(parentInfo.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "HH:mm");
									String str_crs_end_time = mPublicUtils.changePatternString(parentInfo.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "HH:mm");
									
									String eduTime = "교육일시 : "+str_crs_dt+" "+str_crs_str_time+ " ~ " + end_crs_dt + " " + str_crs_end_time;
									String add_msg = "";
									String ment_msg = "";
									SmsMentVO smsMentVO = new SmsMentVO();
									if(parentInfo.getTYPE_GB().equals("offline")){//오프라인교육
										add_msg = "낚시전문교육 수강신청 접수완료 안내("+parentInfo.getCRS_NM()+")"+"\n\n"
												+ eduTime + "\n"
												+ "교육장소 : "+parentInfo.getCRS_PLACE()+"\n"
												+ "교육장주소지 : "+parentInfo.getCRS_ADDR()+"\n\n";
										if(parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) { //낚시어선 신규,재개자교육
											smsMentVO.setSMS_MENT_SN("32");	
										} else {
											if(parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
												smsMentVO.setSMS_MENT_SN("34");	
											} else {
												smsMentVO.setSMS_MENT_SN("34");
											}
										}	
									} else {
										add_msg = "낚시전문교육 수강신청 접수완료 안내("+parentInfo.getCRS_NM()+")"+"\n\n"
												+ eduTime + "\n"
												+ "교육명 : "+parentInfo.getCRS_PLACE()+"\n"
												+ "교육사이트주소 : "+parentInfo.getCRS_ADDR()+"\n\n";
										if(parentInfo.getCRS_LAW_TYPE().equals("CIDLAW002")) { //낚시어선 신규,재개자교육
											smsMentVO.setSMS_MENT_SN("32");	
										} else {
											if(parentInfo.getCRS_MBR_CD().equals("CIDN010200")) {
												smsMentVO.setSMS_MENT_SN("4");	
											} else {
												smsMentVO.setSMS_MENT_SN("5");
											}
										}
										smsMentVO.setSMS_MENT_DTL_CD(parentInfo.getCRS_MBR_CD());//낚시터=CIDN010200,낚시어선=CIDN010300
									}
									
									smsMentVO = smsManagerService.get_sms_ment_info(smsMentVO);	
									if(smsMentVO!=null && smsMentVO.getSMS_MENT_SN()!=null && smsMentVO.getSMS_MENT_SN().length()!=0 ) {
										ment_msg = smsMentVO.getSMS_MENT_CONT();
									}
									
									SmsSendVO newSmsSendVO = new SmsSendVO();
									//mSmsSendVO.setMSG_TYPE();//서비스에서 자동 처리
									//newSmsSendVO.setAPIKEY();//서비스에서 자동 처리
									//newSmsSendVO.setRSTKEY();//서비스에서 자동 처리
									newSmsSendVO.setMBR_ID(chkEduMemberVO.getMBR_ID());
									newSmsSendVO.setSMS_MENT_DTL_CD(parentInfo.getCRS_MBR_CD());
									newSmsSendVO.setMSG(add_msg+'\n'+ment_msg);							
									//newSmsSendVO.setSTAT();//예약발송일때만5
									newSmsSendVO.setS_PHONE(propertiesService.getString("Globals.SmsSenderNumber"));//발신번호
									newSmsSendVO.setR_PHONE(chkEduMemberVO.getMBR_HP());
									newSmsSendVO.setSUBMSG("낚시전문교육 수강신청 접수완료 안내");
									newSmsSendVO.setIMG_CNT(0);
									newSmsSendVO.setIMG_PATH("");
									newSmsSendVO.setREG_MBR_ID(MASTER_MBR_ID);
									newSmsSendVO.setUPD_MBR_ID(MASTER_MBR_ID);			
								    newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
									String rstMsg = smsManagerService.sendToMessage(newSmsSendVO);
									log_dscrp.append("-신청완료문자발송");
								}
								
								//CTI LOG 기록
								logRecordService.set_log_cti_data("교육수강신청", log_dscrp.toString(), log_msg.toString(), "", null, newEduMyHistoryVO, loginVO, request);
								//End of CTI LOG 기록
							}
						}
						log_dscrp.append("]");				
	  				}					
	  				totcnt++;
				}
				
				
				log_msg.append("[결과-전체:"+totcnt+"건,성공:"+successcnt+"건,실패:"+failcnt+"건,실패(중복):"+failOverlapCnt+"건]");
				log_msg.append("[신규데이터:"+insertCount+"건]");
				log_msg.append("[상세데이터:"+insertDtlCount+"건]");
				
				data.put("error", "0");
				
				if(failOverlapCnt > 0) {
					data.put("msg", "이미 신청 된 교육입니다.");
				} else if(failcnt > 0) {
					if(!isRefuse)
						data.put("msg", "신청이 실패하였습니다.<br>존재하지 않는 회원 이거나 실제 회원이라면 일시적인 통신 오류 일수도 있으니 잠시 뒤 다시 시도해주세요.");
				} else {
					data.put("msg", "수강신청이 완료되었습니다.");	
				}
				
			} else {
				log_msg.append("[처리불가:교육대상자미선택]");
				log_dscrp.append("[처리실패]");
				data.put("error", "1");
				data.put("msg", "교육대상자를 선택하지 않았습니다.");
			}
										
		} catch(Exception e) {
			LOGGER.debug("[fail process] " +e.toString());		
			log_msg.append("[에러발생:("+e.toString()+")]");
			log_dscrp.append("[처리실패]");
			data.put("error", "1");
			data.put("msg", "일부가 일시적으로 처리되지 못했습니다.<br>잠시후 다시 시도해주세요.<br>[결과-전체:"+totcnt+"건,성공:"+successcnt+"건,실패:"+failcnt+"건,실패(중복):"+failOverlapCnt+"건]의 수강생이 처리되었습니다.");
		}
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(eduMyHistoryVO));
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


