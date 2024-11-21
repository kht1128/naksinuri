package egovframework.eduadm.certificate.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.all.log.service.LogRecordVO;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.PublicUtils;
import egovframework.utils.PublicUtils.RETURN_COMPARE_TYPE;

public class CreateCertificateToHtmlData {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateCertificateToHtmlData.class);
	
	private StringBuilder document = new StringBuilder();
	
	public CreateCertificateToHtmlData(
			HttpServletRequest request,
			EgovPropertyService propertiesService,
			EduMemberVO eduMemberVO,
			List<EduMemberVO> eduMemberDtlList,
			EduCertificateVO eduCertificateVO,
			EduMyHistoryVO eduMyHistoryVO, 
			EduCurriculumVO eduCurriculumVO,
			EduCategoryInsInfVO eduCategoryInsInfVO
	) {
		PublicUtils mPublicUtils = new PublicUtils();
		
		LOGGER.debug("[이수증발급시작]");
		
		String htmlStyleGubun = "";
		if(request!=null) {
			String clientBrowserName = mPublicUtils.getClientBrowserName(request);
			if("chrome".equals(clientBrowserName)) {
				htmlStyleGubun = "_"+clientBrowserName;
			}	
		}
		LOGGER.debug("[HTML스타일구분] " + htmlStyleGubun);
		
		//교육분류에 따른 표기 처리
		String label_crs_time = "";
		if(eduCurriculumVO.getCRS_TYPE().equals("fshsk_trnng")) {
			label_crs_time = "개월";
		} else {
			label_crs_time = "시간";
		}
		LOGGER.debug("[교육분류에 따른 표기 처리] "+label_crs_time);
		
		boolean isOnlineEdu = false;
		if(eduCurriculumVO.getCRS_GRP_CD().equals("CIDE00000000000")) {
			 isOnlineEdu = true;
		}
		LOGGER.debug("[온라인교육여부] "+isOnlineEdu);
		
		String fileStorePath = propertiesService.getString("Globals.fileCommonPath").toString();

		String today = mPublicUtils.currentTime("yyyy 년  MM 월  dd 일");//발급일자
		String CRTF_CD = eduCertificateVO.getCRTF_CD();//이수증발급번호
		String MBR_BIRTH = eduMemberVO.getMBR_BIRTH();//교육이수자 생년월일
		String MBR_NM = eduMemberVO.getMBR_NM();//교육이수자 이름
		String CRS_STR_DT = mPublicUtils.changePatternString(eduCurriculumVO.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");//교육시작일자
		String CRS_END_DT = mPublicUtils.changePatternString(eduCurriculumVO.getCRS_END_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");//교육종료일자
		String CRS_STR_YEAR = mPublicUtils.changePatternString(eduCurriculumVO.getCRS_STR_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy");//이수증 년도
		int INT_TIME =  Integer.parseInt(eduCurriculumVO.getCRS_TOT_TIME()) + eduCurriculumVO.getSUM_TOT_TIME();
		String TOTAL_EDU_TIME = Integer.toString(INT_TIME);
		//int HMBR_MAX_TIME = Integer.parseInt(eduMyHistoryVO.getHMBR_MAX_TIME());//교육이수 최대시간
		//int HMBR_RCGNT_TIME = 0;//인정된 교육이수시간
		/*try {
			HMBR_RCGNT_TIME = Integer.parseInt(eduMyHistoryVO.getHMBR_RCGNT_TIME());
		} catch(NumberFormatException e1) {
			LOGGER.debug("NumberFormatException HMBR_RCGNT_TIME");
		} catch(Exception e) {
			LOGGER.debug("empty HMBR_RCGNT_TIME");
		}*/
		//String CRS_NM = eduCurriculumVO.getCRS_NM();//교육과정명
		String CAT_INS_NM = eduCategoryInsInfVO.getCAT_INS_NM();//발급기관명
		//String CAT_INS_LOGO = eduCategoryInsInfVO.getCAT_INS_LOGO();//발급기관로그
		String CAT_INS_STAMP = eduCategoryInsInfVO.getCAT_INS_STAMP();//발급자 직인
		String CAT_INS_PSTN = eduCategoryInsInfVO.getCAT_INS_PSTN();//발급기관 대표자 직급
		//String CAT_INS_CEO = eduCategoryInsInfVO.getCAT_INS_CEO();//발급기관 대표자명
		
		if(eduCategoryInsInfVO.getCAT_INS_SN().equals("52")) { //한국어촌어항공단 낚시전문교육
			
			LOGGER.debug("[한국어촌어항공단 낚시전문교육]");
			
			String CRS_MBR_CD = eduCurriculumVO.getCRS_MBR_CD();
			if(CRS_MBR_CD.equals("CIDN010200")) { //낚시터
				LOGGER.debug("[낚시터업자]");
				
				document.append(mPublicUtils.readFile(fileStorePath+"/certificate/fipa/fipa_1"+htmlStyleGubun+".html").toString());
				int makeCnt = 0;
				for(int i=0; i<eduMemberDtlList.size(); i++) {
					if(makeCnt > 3) continue;
					EduMemberVO row = (EduMemberVO)eduMemberDtlList.get(i);
					if(!row.getDTL_CD().equals(CRS_MBR_CD)) continue; 
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_1]]", row.getSIDO_CD_NM());//시도
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_2]]", row.getSIGNGU_CD_NM());//시군구
					if(row.getDTL_NM() != null && row.getDTL_NM().length() != 0){
						mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_3]]", row.getDTL_NM());//명칭 - 낚시터
					} else {
						mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_3]]", "");//명칭 - 낚시터
					}
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_4]]", row.getDTL_LICENSE_CD_NM());//개인/법인업자 구분
					//mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_4]]", row.getSHIP_LICENSE());//구분 - 면허여부 , 허가여부
					makeCnt++;
					LOGGER.debug("[명칭:"+row.getDTL_NM()+"]");
				}
				
			} else if(CRS_MBR_CD.equals("CIDN010300")) { //낚시어선				
				LOGGER.debug("[낚시어선업자]");
				
				document.append(mPublicUtils.readFile(fileStorePath+"/certificate/fipa/fipa_3"+htmlStyleGubun+".html").toString());
				
				//교육구분에 따른 법규 항목 추가
				String htmltagString = "";
				if(eduCurriculumVO.getCRS_LAW_TYPE()!=null && eduCurriculumVO.getCRS_LAW_TYPE().equals("default")) {							
					htmltagString += "<li><span class=\"blt_dot\"></span><span class=\"tit stretch\">교육구분</span><span class=\"colon\">:</span><span class=\"con stretch\">■ 제47조제1항에 따른 교육</span></li>";
					htmltagString += "<li><span class=\"blt_dot\"></span><span class=\"tit stretch\">&nbsp;</span><span class=\"colon\">&nbsp;</span><span class=\"con stretch\">□ 제47조제2항에 따른 교육</span></li>";
							
					mPublicUtils.replaceAll(document, "[[CRS_LAW_TYPE]]", htmltagString);//교육구분
				} else {
					htmltagString += "<li><span class=\"blt_dot\"></span><span class=\"tit stretch\">교육구분</span><span class=\"colon\">:</span><span class=\"con stretch\">□ 제47조제1항에 따른 교육</span></li>";
					htmltagString += "<li><span class=\"blt_dot\"></span><span class=\"tit stretch\">&nbsp;</span><span class=\"colon\">&nbsp;</span><span class=\"con stretch\">■ 제47조제2항에 따른 교육</span></li>";

					mPublicUtils.replaceAll(document, "[[CRS_LAW_TYPE]]", htmltagString);//교육구분
				}
				//
				
				int makeCnt = 0;
				for(int i=0; i<eduMemberDtlList.size(); i++) {
					if(makeCnt > 3) continue;
					EduMemberVO row = (EduMemberVO)eduMemberDtlList.get(i);
					if(!row.getDTL_CD().equals(CRS_MBR_CD)) continue;
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_1]]", row.getSIDO_CD_NM());//시도
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_2]]", row.getSIGNGU_CD_NM());//시군구
					if(row.getDTL_NM() != null && row.getDTL_NM().length() != 0){
						mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_3]]", row.getDTL_NM());//명칭 - 낚시어선
					} else {
						mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_3]]", "");//명칭 - 낚시어선
					}
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_4]]", row.getDTL_LICENSE_CD_NM());//구분 - 면허여부
					makeCnt++;
					LOGGER.debug("[명칭:"+row.getDTL_NM()+"]");
				}	
								
			} else { //기타					
				LOGGER.debug("[알수없음]");
				
			} 
			
			if(MBR_BIRTH!=null && MBR_BIRTH.length()!=0) {
				String regEx_birth = "(\\d{4})(\\d{2})(\\d{2})";
				String regEx_etc_1 = "(\\d{3})(\\d{2})(\\d{5})";
				String regEx_etc_2 = "(\\d{7})(\\d{7})";
				if(MBR_BIRTH.length() <= 8) {
					MBR_BIRTH = MBR_BIRTH.replaceAll(regEx_birth, "$1-$2-$3");
				} else if(8 < MBR_BIRTH.length() && MBR_BIRTH.length() <= 10) {
					MBR_BIRTH = MBR_BIRTH.replaceAll(regEx_etc_1, "$1-$2-$3");
				} else {
					MBR_BIRTH = MBR_BIRTH.replaceAll(regEx_etc_2, "$1-$2");
				}
			}
			
			mPublicUtils.replaceAll(document, "[[CRTF_CD]]", CRTF_CD);//이수증코드
			mPublicUtils.replaceAll(document, "[[MBR_NM]]", MBR_NM);//대상자 이름
			mPublicUtils.replaceAll(document, "[[MBR_BIRTH]]", MBR_BIRTH);//대상자 생년월일
			mPublicUtils.replaceAll(document, "[[CAT_INS_NM]]", CAT_INS_NM);//발급기관명
			
			if(isOnlineEdu) {//온라인교육은 교육일자를 수강일로 표기		
				String LRNNG_CMPLT_DT = "";
				if(eduMyHistoryVO.getTMPR_LRNNG_CMPLT_DT() == null || eduMyHistoryVO.getTMPR_LRNNG_CMPLT_DT().equals(""))
					LRNNG_CMPLT_DT = mPublicUtils.changePatternString(eduMyHistoryVO.getLRNNG_CMPLT_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
				else
					LRNNG_CMPLT_DT = mPublicUtils.changePatternString(eduMyHistoryVO.getTMPR_LRNNG_CMPLT_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
				try {
					//출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함
					if(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02 == mPublicUtils.dateCompare(mPublicUtils.currentTime("yyyy-MM-dd"), "yyyy-MM-dd", LRNNG_CMPLT_DT, "yyyy-MM-dd")) {
						today = mPublicUtils.changePatternString(LRNNG_CMPLT_DT, "yyyy-MM-dd", "yyyy년 MM월 dd일");
						LOGGER.debug("[출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함]");
					}
					//End of 출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함
					
				} catch(Exception e) {
					LOGGER.debug("[온라인교육은교육일자를수강일로표기]");
				}
								
				mPublicUtils.replaceAll(document, "[[CRS_STR_DT]]", LRNNG_CMPLT_DT);//교육일자
			} else {
				try {
					//today = mPublicUtils.changePatternString(eduMyHistoryVO.getLRNNG_CMPLT_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy 년 MM 월 dd 일");
					if(eduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")){//신규,재개자 교육이면
						CRS_STR_DT = CRS_STR_DT + " ~ " + CRS_END_DT;	
					} 
					
					//출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함
					if(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02 == mPublicUtils.dateCompare(mPublicUtils.currentTime("yyyy-MM-dd"), "yyyy-MM-dd", CRS_STR_DT, "yyyy-MM-dd")) {
						today = mPublicUtils.changePatternString(CRS_STR_DT, "yyyy-MM-dd", "yyyy년 MM월 dd일");
						LOGGER.debug("[출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함]");
					}
					//End of 출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함
					
				} catch(Exception e) {
					e.printStackTrace();
					LOGGER.debug("[이수완료정보업데이트전호출로당일처리]");
				}
				
				mPublicUtils.replaceAll(document, "[[CRS_STR_DT]]", CRS_STR_DT);//교육일자
			}
			
			//교육구분(이수증) 
			if(eduCurriculumVO.getCRS_LAW_TYPE().equals("CIDLAW002")){//신규,재개자 교육이면
				mPublicUtils.replaceAll(document, "[[CRS_NM]]", "낚시어선 신규·재개자 교육과정");
			} else {
				mPublicUtils.replaceAll(document, "[[CRS_NM]]", "낚시어선업자 및 선원 교육과정");
			}
			
			mPublicUtils.replaceAll(document, "[[CAT_INS_PSTN]]", CAT_INS_PSTN);//발급자 직급
			mPublicUtils.replaceAll(document, "[[CAT_INS_STAMP]]", CAT_INS_STAMP);//발급자 직인
			
			mPublicUtils.replaceAll(document, "[[TODAY]]", today);//이수증생성일자
			mPublicUtils.replaceAll(document, "[[CRS_STR_YEAR]]", CRS_STR_YEAR);//이수증 년도
			mPublicUtils.replaceAll(document, "[[TOTAL_EDU_TIME]]", TOTAL_EDU_TIME);//이수증 년도
			
			LOGGER.debug("[코드값정리]");
			
			//코드비우기
			for(int i=1; i<=3; i++) {
				mPublicUtils.replaceAll(document, "[[DATA_"+i+"_1]]", "");//시도
				mPublicUtils.replaceAll(document, "[[DATA_"+i+"_2]]", "");//시군구
				mPublicUtils.replaceAll(document, "[[DATA_"+i+"_3]]", "");//명칭 - 낚시터
				mPublicUtils.replaceAll(document, "[[DATA_"+i+"_4]]", "");//구분 - 면허여부 , 허가여부
			}
						
		} else { //그외 기관
			
			LOGGER.debug("[외부기관 낚시전문교육("+CAT_INS_NM+")]");
						
			String CRS_MBR_CD = eduCurriculumVO.getCRS_MBR_CD();
			if(CRS_MBR_CD.equals("CIDN010200")) { //낚시터
				LOGGER.debug("[낚시터업자]");
				
				document.append(mPublicUtils.readFile(fileStorePath+"/certificate/fipa/fipa_1"+htmlStyleGubun+".html").toString());
				int makeCnt = 0;
				for(int i=0; i<eduMemberDtlList.size(); i++) {
					if(makeCnt > 3) continue;
					EduMemberVO row = (EduMemberVO)eduMemberDtlList.get(i);
					if(!row.getDTL_CD().equals(CRS_MBR_CD)) continue;
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_1]]", row.getSIDO_CD_NM());//시도
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_2]]", row.getSIGNGU_CD_NM());//시군구
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_3]]", row.getDTL_NM());//명칭 - 낚시터
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_4]]", row.getDTL_LICENSE_CD_NM());//개인/법인업자 구분
					//mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_4]]", row.getSHIP_LICENSE());//구분 - 면허여부 , 허가여부
					makeCnt++;
					LOGGER.debug("[명칭:"+row.getDTL_NM()+"]");
				}
				
			} else if(CRS_MBR_CD.equals("CIDN010300")) { //낚시어선
				LOGGER.debug("[낚시어선업자]");
				
				document.append(mPublicUtils.readFile(fileStorePath+"/certificate/fipa/fipa_4"+htmlStyleGubun+".html").toString());
				
				//교육구분에 따른 법규 항목 추가
				String htmltagString = "";
				if(eduCurriculumVO.getCRS_LAW_TYPE()!=null && eduCurriculumVO.getCRS_LAW_TYPE().equals("default")) {							
					htmltagString += "<li><span class=\"blt_dot\"></span><span class=\"tit stretch\">교육구분</span><span class=\"colon\">:</span><span class=\"con stretch\">■ 제47조제1항에 따른 교육</span></li>";
					htmltagString += "<li><span class=\"blt_dot\"></span><span class=\"tit stretch\">&nbsp;</span><span class=\"colon\">&nbsp;</span><span class=\"con stretch\">□ 제47조제2항에 따른 교육</span></li>";
							
					mPublicUtils.replaceAll(document, "[[CRS_LAW_TYPE]]", htmltagString);//교육구분
				} else {
					htmltagString += "<li><span class=\"blt_dot\"></span><span class=\"tit stretch\">교육구분</span><span class=\"colon\">:</span><span class=\"con stretch\">□ 제47조제1항에 따른 교육</span></li>";
					htmltagString += "<li><span class=\"blt_dot\"></span><span class=\"tit stretch\">&nbsp;</span><span class=\"colon\">&nbsp;</span><span class=\"con stretch\">■ 제47조제2항에 따른 교육</span></li>";

					mPublicUtils.replaceAll(document, "[[CRS_LAW_TYPE]]", htmltagString);//교육구분
				}
				//
				
				int makeCnt = 0;
				for(int i=0; i<eduMemberDtlList.size(); i++) {
					if(makeCnt > 3) continue;
					EduMemberVO row = (EduMemberVO)eduMemberDtlList.get(i);	
					if(!row.getDTL_CD().equals(CRS_MBR_CD)) continue;
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_1]]", row.getSIDO_CD_NM());//시도
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_2]]", row.getSIGNGU_CD_NM());//시군구
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_3]]", row.getDTL_NM());//명칭 - 낚시어선
					mPublicUtils.replaceAll(document, "[[DATA_"+(makeCnt+1)+"_4]]", row.getDTL_LICENSE_CD_NM());//구분 - 면허여부					
					makeCnt++;
					LOGGER.debug("[명칭:"+row.getDTL_NM()+"]");
				}		
				
			} else { //기타
				LOGGER.debug("[알수없음]");
						
			} 
			
			if(MBR_BIRTH!=null && MBR_BIRTH.length()!=0) {
				String regEx_birth = "(\\d{4})(\\d{2})(\\d{2})";
				String regEx_etc_1 = "(\\d{3})(\\d{2})(\\d{5})";
				String regEx_etc_2 = "(\\d{7})(\\d{7})";
				if(MBR_BIRTH.length() <= 8) {
					MBR_BIRTH = MBR_BIRTH.replaceAll(regEx_birth, "$1-$2-$3");
				} else if(8 < MBR_BIRTH.length() && MBR_BIRTH.length() <= 10) {
					MBR_BIRTH = MBR_BIRTH.replaceAll(regEx_etc_1, "$1-$2-$3");
				} else {
					MBR_BIRTH = MBR_BIRTH.replaceAll(regEx_etc_2, "$1-$2");
				}
			}
			
			mPublicUtils.replaceAll(document, "[[CRTF_CD]]", CRTF_CD);//이수증코드
			mPublicUtils.replaceAll(document, "[[MBR_NM]]", MBR_NM);//대상자 이름
			mPublicUtils.replaceAll(document, "[[MBR_BIRTH]]", MBR_BIRTH);//대상자 생년월일
			CAT_INS_NM = "한국어촌어항공단";
			mPublicUtils.replaceAll(document, "[[CAT_INS_NM]]", CAT_INS_NM);//발급기관명
								
			if(isOnlineEdu) {//온라인교육은 교육일자를 수강일로 표기		
				String LRNNG_CMPLT_DT = mPublicUtils.changePatternString(eduMyHistoryVO.getTMPR_LRNNG_CMPLT_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");	
				try {
					
					//출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함
					if(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02 == mPublicUtils.dateCompare(mPublicUtils.currentTime("yyyy-MM-dd"), "yyyy-MM-dd", LRNNG_CMPLT_DT, "yyyy-MM-dd")) {
						today = mPublicUtils.changePatternString(LRNNG_CMPLT_DT, "yyyy-MM-dd", "yyyy년 MM월 dd일");
						LOGGER.debug("[출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함]");
					}
					//End of 출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함
					
				} catch(Exception e) {
					LOGGER.debug("[온라인교육은교육일자를수강일로표기]");
				}
				mPublicUtils.replaceAll(document, "[[CRS_STR_DT]]", LRNNG_CMPLT_DT);//교육일자
			} else {
				try {
					//today = mPublicUtils.changePatternString(eduMyHistoryVO.getLRNNG_CMPLT_DT().replace(".0", ""), "yyyy-MM-dd HH:mm:ss", "yyyy 년 MM 월 dd 일");
					
					//출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함
					if(PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02 == mPublicUtils.dateCompare(mPublicUtils.currentTime("yyyy-MM-dd"), "yyyy-MM-dd", CRS_STR_DT, "yyyy-MM-dd")) {
						today = mPublicUtils.changePatternString(CRS_STR_DT, "yyyy-MM-dd", "yyyy년 MM월 dd일");
						LOGGER.debug("[출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함]");
					}
					//End of 출력일자가 교육일자보다 이전날인 경우 출력일자를 교육일자로 출력되도록 함
					
				} catch(Exception e) {
					LOGGER.debug("[이수완료정보업데이트전호출로당일처리]");
				}
				mPublicUtils.replaceAll(document, "[[CRS_STR_DT]]", CRS_STR_DT);//교육일자
			}
			
			mPublicUtils.replaceAll(document, "[[CAT_INS_PSTN]]", CAT_INS_PSTN);//발급자 직급
			mPublicUtils.replaceAll(document, "[[CAT_INS_STAMP]]", CAT_INS_STAMP);//발급자 직인
			mPublicUtils.replaceAll(document, "[[TODAY]]", today);//이수증생성일자
			mPublicUtils.replaceAll(document, "[[CRS_STR_YEAR]]", CRS_STR_YEAR);//이수증 년도
			mPublicUtils.replaceAll(document, "[[TOTAL_EDU_TIME]]", TOTAL_EDU_TIME);//이수증 년도
			
			LOGGER.debug("[코드값정리]");
			
			//코드비우기
			for(int i=1; i<=3; i++) {
				mPublicUtils.replaceAll(document, "[[DATA_"+i+"_1]]", "");//시도
				mPublicUtils.replaceAll(document, "[[DATA_"+i+"_2]]", "");//시군구
				mPublicUtils.replaceAll(document, "[[DATA_"+i+"_3]]", "");//명칭 - 낚시터
				mPublicUtils.replaceAll(document, "[[DATA_"+i+"_4]]", "");//구분 - 면허여부 , 허가여부
			}
									
		}
		LOGGER.debug("[이수증발급종료]");
	}
	
	public String toDocument() {
		return document.toString();
	}
	
}
