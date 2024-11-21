/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.educenter.member.service;

import java.util.Arrays;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [멤버테이블]
 * 테이블명 : MBR_TB
 */
public class EduCenterMemberVO extends EduDefaultVO {
//public class EduMemberVO extends EduDefaultVO {
	private String MBR_SN;//bigint(20) NOT NULL일련번호
	private String MBR_CD;//varchar(20) NULL회원코드
	private String MBR_ID;//varchar(50) NULL아이디
	private String MBR_PWD;//varchar(250) NULLㅂㅁ
	private String MBR_NM;//varchar(50) NULL이름
	private String MBR_NCNM;//varchar(50) NULL닉네임
	private String MBR_TEL;//varchar(20) NULL전화번호
	private String MBR_HP;//varchar(14) NULL휴대폰
	private String MBR_EMAIL;//varchar(40) NULL이메일
	private String MBR_ADDR1;//varchar(100) NULL주소1
	private String MBR_ADDR2;//varchar(100) NULL주소2
	private String MBR_ZIPCD;//varchar(7) NULL우편번호
	private String MBR_LAST_CON_TM;//datetime NULL접속 시간
	private String MBR_LAST_CON_IPADDR;//varchar(30) NULL접속 아이피
	private String MBR_REG_DT;//datetime NULL가입일
	private String MBR_MOD_DT;//datetime NULL수정일
	private String MBR_TYPE_CD;//varchar(5) NULL유형코드
	private String MBR_BIRTH;//varchar(8) NULL생년월일
	private String MBR_SEX;//tinyint(4) NULL1:남, 2:여
	private String MBR_HOPE_ZONE;//varchar(256) NULL희망지역
	private String MBR_HOPE_BUSINESS;//varchar(256) NULL희망업종
	private String MBR_ST;//enum('Y','N') NULLY:사용, N:삭제
	private String MBR_LV_ID;//int(11) NULL권한레벨
	private String MBR_POSITION_CD;//varchar(10) NULL직급코드[CID00003]
	private String MBR_GRP_1_ST;//enum('Y','N') NULL낚시누리 권한허용여부
	private String MBR_GRP_2_ST;//enum('Y','N') NULL낚시전문교육 권한허용여부
	private String MBR_GRP_3_ST;//enum('Y','N') NULL빈 권한허용여부
	private String MBR_GRP_4_ST;//enum('Y','N') NULL빈 권한허용여부
	private String MBR_JOIN;//varchar(20) NULL회원가입타입
	private String MBR_HMBR_ONLINE_SN;//varchar(50) NULL온라인교육이수코드
	private String MBR_HMBR_ONLINE_ST;//enum('Y','N') NULL온라인교육이수여부
	private String MBR_RETRY_LOGIN_DT;//datetime NULL로그인시도일자
	private String MBR_RETRY_LOGIN_CNT;//int(11) NULL로그인시도횟수
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String MBR_PSITN_NM;//varchar(250) NULL소속명(관리자)
	private String MBR_SIDO_CD;//varchar(20) NULL지역제한코드(시도)
	private String MBR_SIGNGU_CD;//varchar(20) NULL지역제한코드(시군구)
	private String MBR_EDU_INS_CD;//varchar(20) NULL(관리자)교육기관 제한코드
	
	//세부정보
	private String DTL_SN;//bigint(20) NOT NULL상세정보일련번호   
	//private String MBR_CD;//varchar(20) NULL회원코드
	//private String MBR_ID;//varchar(50) NULL아이디
	//private String MBR_NM;//varchar(50) NULL이름
	private String DTL_CD;//varchar(10) NULL구분(낚시터:CIDN010200/어선:CIDN010300)
	private String DTL_NM;//varchar(100) NULL어선명 / 낚시터명
	private String REG_TYPE_CD;//varchar(20) NULL등록구분코드(공단,지자체)
	private String DTL_LICENSE_CD;//varchar(30) NULL사업자구분코드(낚시어선업자 선주/선원 , 개인사업자 , 법인사업자 , 해기사면허 소지 여부)
	private String SIDO_CD;//varchar(20) NULL시도코드
	private String SIGNGU_CD;//varchar(20) NULL시군구코드
	private String YMD_NM;//varchar(100) NULL읍면동이름
	private String DTL_ADDR;//varchar(200) NULL소재지
	private String REG_NUM_CD;//varchar(50) NULL신고번호(어선) / 허가(등록)번호(낚시터)
	private String FSHLC_WORK_CD;//varchar(50) NULL낚시터부가정보-업구분(허가:NSID00001,등록:NSID00002)
	private String FSHLC_APPLC;//varchar(50) NULL낚시터부가정보-적용수면
	private String SHIP_CD;//varchar(50) NULL어선부가정보-어선번호
	private String SHIP_GRTG;//varchar(10) NULL어선부가정보-총톤수
	private String SHIP_PRLOAD;//varchar(30) NULL어선부가정보-선적항
	private String SHIP_MAX_PSNGER;//varchar(10) NULL어선부가정보-최대승객
	private String SHIP_MAX_CREW;//varchar(10) NULL어선부가정보-최대선원
	private String SHIP_LICENSE;//varchar(20) NULL어선부가정보-해기사면허
	private String SHIP_LICENSE_STR_DT;//datetime NULL어선부가정보-해기사면허 유효시작일자
	private String SHIP_LICENSE_END_DT;//datetime NULL어선부가정보-해기사면허 유효만료일자
	private String USE_AT;//enum('Y','N') NULLN:사용안함,Y:사용함
	private String DEL_AT;//enum('Y','N') NULLN:정상,Y:삭제(1단계)
	private String HIDE_AT;//enum('Y','N') NULLN:정상,Y:노출안함
	private String REG_DT;//datetime NULL등록일자
	private String UPD_DT;//datetime NULL변경일자
	//private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	//private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	
	//교육정보
	//private String MBR_CD;//varchar(20) NULL회원코드
	//private String MBR_ID;//varchar(50) NULL회원아이디
	//private String MBR_NM;//varchar(50) NULL이름
	//private String REG_TYPE_CD;//varchar(30) NULL등록구분코드(공단,지자체)
	private String TRGT_YEAR;//varchar(4) NULL대상년도
	private String CMPLT_ST;//int(3) NULL0:미이수,1:이수완료,
	private String CMPLT_DT;//datetime NULL이수완료일
	private String CMPLT_MBR_ID;//varchar(50) NULL이수완료처리아이디
	private String CRS_SN;//char(30) NULL교육정보1단계번호
	private String HMBR_SN;//char(30) NULL이수내역번호
	//private String USE_AT;//enum('Y','N') NULLN:사용안함,Y:사용함
	//private String DEL_AT;//enum('Y','N') NULLN:정상,Y:삭제(1단계)
	//private String HIDE_AT;//enum('Y','N') NULLN:정상,Y:노출안함
	//private String REG_DT;//datetime NULL등록일자
	//private String UPD_DT;//datetime NULL변경일자
	//private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	//private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	
	
	//디비외
	private String MBR_GRP_ID;
	private String[] MBR_IDS;//회원아이디s
	private String[] MBR_ADDED_IDS;//이미 추가된 회원아이디s
	private String HMBR_CNT;//이수중인교육정보
	private String CRS_NM;//교육정보 1단계 명칭
	private String CRS_GRP_NM;//교육과정그룹(코드)명
	private String CAT_INS_NM;//교육기관명
	private String SIDO_NM;//시도코드 명
	private String SIGNGU_NM;//시군구코드 명
	private String CRS_SN_NULL;//연도별교육대상 교육정보 빈값 찾기
	private String CRS_SN_NOT_NULL;//연도별교육대상 교육정보 빈값 아닌것 찾기
	private String DTL_CNT;//상세정보갯수
	private String addConfirmSubmit;//회원가입시 강제등록승인여부
		
	
	public String getFSHLC_WORK_CD() {
		return FSHLC_WORK_CD;
	}
	public void setFSHLC_WORK_CD(String fSHLC_WORK_CD) {
		FSHLC_WORK_CD = fSHLC_WORK_CD;
	}
	public String getDTL_SN() {
		return DTL_SN;
	}
	public void setDTL_SN(String dTL_SN) {
		DTL_SN = dTL_SN;
	}
	public String getAddConfirmSubmit() {
		return addConfirmSubmit;
	}
	public void setAddConfirmSubmit(String addConfirmSubmit) {
		this.addConfirmSubmit = addConfirmSubmit;
	}
	public String getDTL_CNT() {
		return DTL_CNT;
	}
	public void setDTL_CNT(String dTL_CNT) {
		DTL_CNT = dTL_CNT;
	}
	public String getCRS_SN_NOT_NULL() {
		return CRS_SN_NOT_NULL;
	}
	public void setCRS_SN_NOT_NULL(String cRS_SN_NOT_NULL) {
		CRS_SN_NOT_NULL = cRS_SN_NOT_NULL;
	}
	public String getCRS_SN_NULL() {
		return CRS_SN_NULL;
	}
	public void setCRS_SN_NULL(String cRS_SN_NULL) {
		CRS_SN_NULL = cRS_SN_NULL;
	}
	public String getSIDO_NM() {
		return SIDO_NM;
	}
	public void setSIDO_NM(String sIDO_NM) {
		SIDO_NM = sIDO_NM;
	}
	public String getSIGNGU_NM() {
		return SIGNGU_NM;
	}
	public void setSIGNGU_NM(String sIGNGU_NM) {
		SIGNGU_NM = sIGNGU_NM;
	}
	public String getMBR_EDU_INS_CD() {
		return MBR_EDU_INS_CD;
	}
	public void setMBR_EDU_INS_CD(String mBR_EDU_INS_CD) {
		MBR_EDU_INS_CD = mBR_EDU_INS_CD;
	}
	public String getCAT_INS_NM() {
		return CAT_INS_NM;
	}
	public void setCAT_INS_NM(String cAT_INS_NM) {
		CAT_INS_NM = cAT_INS_NM;
	}
	public String getCRS_GRP_NM() {
		return CRS_GRP_NM;
	}
	public void setCRS_GRP_NM(String cRS_GRP_NM) {
		CRS_GRP_NM = cRS_GRP_NM;
	}
	public String getCRS_NM() {
		return CRS_NM;
	}
	public void setCRS_NM(String cRS_NM) {
		CRS_NM = cRS_NM;
	}
	public String getCRS_SN() {
		return CRS_SN;
	}
	public void setCRS_SN(String cRS_SN) {
		CRS_SN = cRS_SN;
	}
	public String getHMBR_SN() {
		return HMBR_SN;
	}
	public void setHMBR_SN(String hMBR_SN) {
		HMBR_SN = hMBR_SN;
	}
	public String getREG_TYPE_CD() {
		return REG_TYPE_CD;
	}
	public void setREG_TYPE_CD(String rEG_TYPE_CD) {
		REG_TYPE_CD = rEG_TYPE_CD;
	}
	public String getDTL_LICENSE_CD() {
		return DTL_LICENSE_CD;
	}
	public void setDTL_LICENSE_CD(String dTL_LICENSE_CD) {
		DTL_LICENSE_CD = dTL_LICENSE_CD;
	}
	public String getSIDO_CD() {
		return SIDO_CD;
	}
	public void setSIDO_CD(String sIDO_CD) {
		SIDO_CD = sIDO_CD;
	}
	public String getSIGNGU_CD() {
		return SIGNGU_CD;
	}
	public void setSIGNGU_CD(String sIGNGU_CD) {
		SIGNGU_CD = sIGNGU_CD;
	}
	public String getYMD_NM() {
		return YMD_NM;
	}
	public void setYMD_NM(String yMD_NM) {
		YMD_NM = yMD_NM;
	}
	public String getREG_NUM_CD() {
		return REG_NUM_CD;
	}
	public void setREG_NUM_CD(String rEG_NUM_CD) {
		REG_NUM_CD = rEG_NUM_CD;
	}
	public String getSHIP_GRTG() {
		return SHIP_GRTG;
	}
	public void setSHIP_GRTG(String sHIP_GRTG) {
		SHIP_GRTG = sHIP_GRTG;
	}
	public String getSHIP_LICENSE_STR_DT() {
		return SHIP_LICENSE_STR_DT;
	}
	public void setSHIP_LICENSE_STR_DT(String sHIP_LICENSE_STR_DT) {
		SHIP_LICENSE_STR_DT = sHIP_LICENSE_STR_DT;
	}
	public String getSHIP_LICENSE_END_DT() {
		return SHIP_LICENSE_END_DT;
	}
	public void setSHIP_LICENSE_END_DT(String sHIP_LICENSE_END_DT) {
		SHIP_LICENSE_END_DT = sHIP_LICENSE_END_DT;
	}
	public String getTRGT_YEAR() {
		return TRGT_YEAR;
	}
	public void setTRGT_YEAR(String tRGT_YEAR) {
		TRGT_YEAR = tRGT_YEAR;
	}
	public String getCMPLT_ST() {
		return CMPLT_ST;
	}
	public void setCMPLT_ST(String cMPLT_ST) {
		CMPLT_ST = cMPLT_ST;
	}
	public String getCMPLT_DT() {
		return CMPLT_DT;
	}
	public void setCMPLT_DT(String cMPLT_DT) {
		CMPLT_DT = cMPLT_DT;
	}
	public String getCMPLT_MBR_ID() {
		return CMPLT_MBR_ID;
	}
	public void setCMPLT_MBR_ID(String cMPLT_MBR_ID) {
		CMPLT_MBR_ID = cMPLT_MBR_ID;
	}
	public String getDTL_CD() {
		return DTL_CD;
	}
	public void setDTL_CD(String dTL_CD) {
		DTL_CD = dTL_CD;
	}
	public String getDTL_NM() {
		return DTL_NM;
	}
	public void setDTL_NM(String dTL_NM) {
		DTL_NM = dTL_NM;
	}
	public String getDTL_ADDR() {
		return DTL_ADDR;
	}
	public void setDTL_ADDR(String dTL_ADDR) {
		DTL_ADDR = dTL_ADDR;
	}	
	public String getFSHLC_APPLC() {
		return FSHLC_APPLC;
	}
	public void setFSHLC_APPLC(String fSHLC_APPLC) {
		FSHLC_APPLC = fSHLC_APPLC;
	}
	public String getSHIP_CD() {
		return SHIP_CD;
	}
	public void setSHIP_CD(String sHIP_CD) {
		SHIP_CD = sHIP_CD;
	}	
	public String getSHIP_PRLOAD() {
		return SHIP_PRLOAD;
	}
	public void setSHIP_PRLOAD(String sHIP_PRLOAD) {
		SHIP_PRLOAD = sHIP_PRLOAD;
	}
	public String getSHIP_MAX_PSNGER() {
		return SHIP_MAX_PSNGER;
	}
	public void setSHIP_MAX_PSNGER(String sHIP_MAX_PSNGER) {
		SHIP_MAX_PSNGER = sHIP_MAX_PSNGER;
	}
	public String getSHIP_MAX_CREW() {
		return SHIP_MAX_CREW;
	}
	public void setSHIP_MAX_CREW(String sHIP_MAX_CREW) {
		SHIP_MAX_CREW = sHIP_MAX_CREW;
	}
	public String getSHIP_LICENSE() {
		return SHIP_LICENSE;
	}
	public void setSHIP_LICENSE(String sHIP_LICENSE) {
		SHIP_LICENSE = sHIP_LICENSE;
	}
	public String getUSE_AT() {
		return USE_AT;
	}
	public void setUSE_AT(String uSE_AT) {
		USE_AT = uSE_AT;
	}
	public String getDEL_AT() {
		return DEL_AT;
	}
	public void setDEL_AT(String dEL_AT) {
		DEL_AT = dEL_AT;
	}
	public String getHIDE_AT() {
		return HIDE_AT;
	}
	public void setHIDE_AT(String hIDE_AT) {
		HIDE_AT = hIDE_AT;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getUPD_DT() {
		return UPD_DT;
	}
	public void setUPD_DT(String uPD_DT) {
		UPD_DT = uPD_DT;
	}
	public String getMBR_CD() {
		return MBR_CD;
	}
	public void setMBR_CD(String mBR_CD) {
		MBR_CD = mBR_CD;
	}
	public String getMBR_NCNM() {
		return MBR_NCNM;
	}
	public void setMBR_NCNM(String mBR_NCNM) {
		MBR_NCNM = mBR_NCNM;
	}
	public String getMBR_SEX() {
		return MBR_SEX;
	}
	public void setMBR_SEX(String mBR_SEX) {
		MBR_SEX = mBR_SEX;
	}
	public String getMBR_JOIN() {
		return MBR_JOIN;
	}
	public void setMBR_JOIN(String mBR_JOIN) {
		MBR_JOIN = mBR_JOIN;
	}
	public String getMBR_RETRY_LOGIN_DT() {
		return MBR_RETRY_LOGIN_DT;
	}
	public void setMBR_RETRY_LOGIN_DT(String mBR_RETRY_LOGIN_DT) {
		MBR_RETRY_LOGIN_DT = mBR_RETRY_LOGIN_DT;
	}
	public String getMBR_RETRY_LOGIN_CNT() {
		return MBR_RETRY_LOGIN_CNT;
	}
	public void setMBR_RETRY_LOGIN_CNT(String mBR_RETRY_LOGIN_CNT) {
		MBR_RETRY_LOGIN_CNT = mBR_RETRY_LOGIN_CNT;
	}
	public String getMBR_GRP_ID() {
		return MBR_GRP_ID;
	}
	public void setMBR_GRP_ID(String mBR_GRP_ID) {
		MBR_GRP_ID = mBR_GRP_ID;
	}
	public String getMBR_PSITN_NM() {
		return MBR_PSITN_NM;
	}
	public void setMBR_PSITN_NM(String mBR_PSITN_NM) {
		MBR_PSITN_NM = mBR_PSITN_NM;
	}
	public String getMBR_SIDO_CD() {
		return MBR_SIDO_CD;
	}
	public void setMBR_SIDO_CD(String mBR_SIDO_CD) {
		MBR_SIDO_CD = mBR_SIDO_CD;
	}
	public String getMBR_SIGNGU_CD() {
		return MBR_SIGNGU_CD;
	}
	public void setMBR_SIGNGU_CD(String mBR_SIGNGU_CD) {
		MBR_SIGNGU_CD = mBR_SIGNGU_CD;
	}
	public String getREG_MBR_ID() {
		return REG_MBR_ID;
	}
	public void setREG_MBR_ID(String rEG_MBR_ID) {
		REG_MBR_ID = rEG_MBR_ID;
	}
	public String getUPD_MBR_ID() {
		return UPD_MBR_ID;
	}
	public void setUPD_MBR_ID(String uPD_MBR_ID) {
		UPD_MBR_ID = uPD_MBR_ID;
	}
	public String getMBR_HMBR_ONLINE_SN() {
		return MBR_HMBR_ONLINE_SN;
	}
	public void setMBR_HMBR_ONLINE_SN(String mBR_HMBR_ONLINE_SN) {
		MBR_HMBR_ONLINE_SN = mBR_HMBR_ONLINE_SN;
	}
	public String getMBR_HMBR_ONLINE_ST() {
		return MBR_HMBR_ONLINE_ST;
	}
	public void setMBR_HMBR_ONLINE_ST(String mBR_HMBR_ONLINE_ST) {
		MBR_HMBR_ONLINE_ST = mBR_HMBR_ONLINE_ST;
	}
	public String getMBR_GRP_1_ST() {
		return MBR_GRP_1_ST;
	}
	public void setMBR_GRP_1_ST(String mBR_GRP_1_ST) {
		MBR_GRP_1_ST = mBR_GRP_1_ST;
	}
	public String getMBR_GRP_2_ST() {
		return MBR_GRP_2_ST;
	}
	public void setMBR_GRP_2_ST(String mBR_GRP_2_ST) {
		MBR_GRP_2_ST = mBR_GRP_2_ST;
	}
	public String getMBR_GRP_3_ST() {
		return MBR_GRP_3_ST;
	}
	public void setMBR_GRP_3_ST(String mBR_GRP_3_ST) {
		MBR_GRP_3_ST = mBR_GRP_3_ST;
	}
	public String getMBR_GRP_4_ST() {
		return MBR_GRP_4_ST;
	}
	public void setMBR_GRP_4_ST(String mBR_GRP_4_ST) {
		MBR_GRP_4_ST = mBR_GRP_4_ST;
	}
		
	public String getMBR_LV_ID() {
		return MBR_LV_ID;
	}
	public void setMBR_LV_ID(String mBR_LV_ID) {
		MBR_LV_ID = mBR_LV_ID;
	}
	public String getHMBR_CNT() {
		return HMBR_CNT;
	}
	public void setHMBR_CNT(String hMBR_CNT) {
		HMBR_CNT = hMBR_CNT;
	}
	public String[] getMBR_ADDED_IDS() {
		String[] ret = null;
		if(MBR_ADDED_IDS != null) {
			ret = new String[MBR_ADDED_IDS.length];
			for(int i=0; i<MBR_ADDED_IDS.length;i++) {
				ret[i] = MBR_ADDED_IDS[i];
			}
		}
		return ret;
	}
	public void setMBR_ADDED_IDS(String[] mBR_ADDED_IDS) {
		//MBR_ADDED_IDS = mBR_ADDED_IDS;
		MBR_ADDED_IDS = Arrays.copyOf(mBR_ADDED_IDS, mBR_ADDED_IDS.length);		
	}
	public String[] getMBR_IDS() {
		String[] ret = null;
		if(MBR_IDS != null) {
			ret = new String[MBR_IDS.length];
			for(int i=0; i<MBR_IDS.length;i++) {
				ret[i] = MBR_IDS[i];
			}
		}
		return ret;
	}
	public void setMBR_IDS(String[] mBR_IDS) {
		//MBR_IDS = mBR_IDS;
		MBR_IDS = Arrays.copyOf(mBR_IDS, mBR_IDS.length);		
	}
	
	public String getMBR_SN() {
		return MBR_SN;
	}
	public void setMBR_SN(String mBR_SN) {
		MBR_SN = mBR_SN;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getMBR_PWD() {
		return MBR_PWD;
	}
	public void setMBR_PWD(String mBR_PWD) {
		MBR_PWD = mBR_PWD;
	}
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMBR_TEL() {
		return MBR_TEL;
	}
	public void setMBR_TEL(String mBR_TEL) {
		MBR_TEL = mBR_TEL;
	}
	public String getMBR_HP() {
		return MBR_HP;
	}
	public void setMBR_HP(String mBR_HP) {
		MBR_HP = mBR_HP;
	}
	public String getMBR_EMAIL() {
		return MBR_EMAIL;
	}
	public void setMBR_EMAIL(String mBR_EMAIL) {
		MBR_EMAIL = mBR_EMAIL;
	}
	public String getMBR_ADDR1() {
		return MBR_ADDR1;
	}
	public void setMBR_ADDR1(String mBR_ADDR1) {
		MBR_ADDR1 = mBR_ADDR1;
	}
	public String getMBR_ADDR2() {
		return MBR_ADDR2;
	}
	public void setMBR_ADDR2(String mBR_ADDR2) {
		MBR_ADDR2 = mBR_ADDR2;
	}
	public String getMBR_ZIPCD() {
		return MBR_ZIPCD;
	}
	public void setMBR_ZIPCD(String mBR_ZIPCD) {
		MBR_ZIPCD = mBR_ZIPCD;
	}
	public String getMBR_LAST_CON_TM() {
		return MBR_LAST_CON_TM;
	}
	public void setMBR_LAST_CON_TM(String mBR_LAST_CON_TM) {
		MBR_LAST_CON_TM = mBR_LAST_CON_TM;
	}
	public String getMBR_LAST_CON_IPADDR() {
		return MBR_LAST_CON_IPADDR;
	}
	public void setMBR_LAST_CON_IPADDR(String mBR_LAST_CON_IPADDR) {
		MBR_LAST_CON_IPADDR = mBR_LAST_CON_IPADDR;
	}
	public String getMBR_REG_DT() {
		return MBR_REG_DT;
	}
	public void setMBR_REG_DT(String mBR_REG_DT) {
		MBR_REG_DT = mBR_REG_DT;
	}
	public String getMBR_MOD_DT() {
		return MBR_MOD_DT;
	}
	public void setMBR_MOD_DT(String mBR_MOD_DT) {
		MBR_MOD_DT = mBR_MOD_DT;
	}
	public String getMBR_POSITION_CD() {
		return MBR_POSITION_CD;
	}
	public void setMBR_POSITION_CD(String mBR_POSITION_CD) {
		MBR_POSITION_CD = mBR_POSITION_CD;
	}
	public String getMBR_TYPE_CD() {
		return MBR_TYPE_CD;
	}
	public void setMBR_TYPE_CD(String mBR_TYPE_CD) {
		MBR_TYPE_CD = mBR_TYPE_CD;
	}	
	public String getMBR_BIRTH() {
		return MBR_BIRTH;
	}
	public void setMBR_BIRTH(String mBR_BIRTH) {
		MBR_BIRTH = mBR_BIRTH;
	}	
	public String getMBR_HOPE_ZONE() {
		return MBR_HOPE_ZONE;
	}
	public void setMBR_HOPE_ZONE(String mBR_HOPE_ZONE) {
		MBR_HOPE_ZONE = mBR_HOPE_ZONE;
	}
	public String getMBR_HOPE_BUSINESS() {
		return MBR_HOPE_BUSINESS;
	}
	public void setMBR_HOPE_BUSINESS(String mBR_HOPE_BUSINESS) {
		MBR_HOPE_BUSINESS = mBR_HOPE_BUSINESS;
	}
	public String getMBR_ST() {
		return MBR_ST;
	}
	public void setMBR_ST(String mBR_ST) {
		MBR_ST = mBR_ST;
	}
	
}
