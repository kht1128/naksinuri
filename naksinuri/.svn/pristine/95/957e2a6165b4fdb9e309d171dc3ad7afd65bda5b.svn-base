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
package egovframework.eduadm.main.service;

/**
 * [MBR_RMNDR_TB]
 * 테이블명 : 
 */
public class EduMbrRemindersVO extends EduDefaultVO {
	
	private String RMNDR_SN;//bigint(20) NOT NULL수강(검토)신청:등록번호
	private String RMNDR_MBR_DSCRP;//text NULL수강(검토)신청:요청사항
	private String RMNDR_MBR_NM;//varchar(50) NULL비회원 이름
	private String RMNDR_MBR_HP;//varchar(50) NULL비회원 연락처
	private String RMNDR_MBR_TEL;//varchar(50) NULL비회원 일반전화
	private String RMNDR_MBR_BIRTH;//varchar(10) NULL비회원 생년월일
	private String RMNDR_MBR_ADDR1;//text NULL비회원 주소
	private String RMNDR_MBR_ADDR2;//varchar(250) NULL비회원 상세주소
	private String RMNDR_MBR_ZIP;//varchar(50) NULL비회원 우편번호
	private String RMNDR_MBR_ID;//varchar(50) NULL접수자아이디(비회원인경우기본값)
	private String RMNDR_DTL_CD;//varchar(20) NULL구분(낚시터:CIDN010200/어선:CIDN010300)=CRS_MBR_CD
	private String RMNDR_CRS_SN;//char(30) NULL교육과정(대)코드
	private String RMNDR_DTL_LICENSE_CD;//varchar(30) NULL사업자구분코드(낚시어선업자 선주/선원 , 개인사업자 , 법인사업자 , 해기사면허 소지 여부)
	private String RMNDR_DTL_NM;//varchar(100) NULL어선명 / 낚시터명
	private String RMNDR_REG_NUM_CD;//varchar(50) NULL신고번호(어선) / 허가(등록)번호(낚시터)
	private String RMNDR_BUSINESS_NUM;//varchar(50) NULL사업자번호
	private String RMNDR_SIDO_CD;//varchar(20) NULL시도코드
	private String RMNDR_SIGNGU_CD;//varchar(20) NULL시군구코드
	private String RMNDR_YMD_NM;//varchar(100) NULL읍면동이름
	private String RMNDR_SHIP_CD;//varchar(50) NULL어선부가정보-어선번호
	private String RMNDR_SHIP_LICENSE;//varchar(20) NULL어선부가정보-해기사면허
	private String RMNDR_SHIP_LICENSE_STR_DT;//datetime NULL어선부가정보-해기사면허 유효시작일자
	private String RMNDR_SHIP_LICENSE_END_DT;//datetime NULL어선부가정보-해기사면허 유효만료일자
	private String REG_DT;//datetime NULL생성일자
	private String UPD_DT;//datetime NULL변경일자
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String USE_ST;//int(3) NULL0:사용안함,1:사용함
	private String DEL_ST;//int(3) NULL0:삭제안함,1:삭제함
	private String CMPLT_ST;//enum('Y','N') NULLN:미처리,Y:처리완료
	private String INDVDL_INFO_ST;	//개인정보동의서(선택)0:값없음
	private String PARNTS_MBR_NM;//varchar(50) NULL보호자 이름
	private String PARNTS_MBR_HP;//varchar(50) NULL보호자 연락처
	private String PARNTS_MBR_BIRTH;//varchar(10) NULL보호자 생년월일
	private String PARNTS_MBR_RELATIONSHIP;//varchar(100) NULL보호자가 신청자와의 관계
	private String UNDER_AGE_14_ST;//enum('Y','N') NULL14세미만여부
	private String TYPE_GB; //online,offline
	private String MBR_FSHRBT_TYPE;//낚시어선 신규,재개자,기존 구분
	private String RMNDR_DSCRP;//메모
	private String CRS_LAW_TYPE;//교육구분설명(법규 등) 
	
	//디비외
	private String RMNDR_CRS_NM;//교육과정명

	public String getCRS_LAW_TYPE() {
		return CRS_LAW_TYPE;
	}
	public void setCRS_LAW_TYPE(String cRS_LAW_TYPE) {
		CRS_LAW_TYPE = cRS_LAW_TYPE;
	}
	public String getRMNDR_DSCRP() {
		return RMNDR_DSCRP;
	}
	public void setRMNDR_DSCRP(String rMNDR_DSCRP) {
		RMNDR_DSCRP = rMNDR_DSCRP;
	}
	public String getMBR_FSHRBT_TYPE() {
		return MBR_FSHRBT_TYPE;
	}
	public void setMBR_FSHRBT_TYPE(String mBR_FSHRBT_TYPE) {
		MBR_FSHRBT_TYPE = mBR_FSHRBT_TYPE;
	}
	public String getTYPE_GB() {
		return TYPE_GB;
	}
	public void setTYPE_GB(String tYPE_GB) {
		TYPE_GB = tYPE_GB;
	}
	public String getPARNTS_MBR_RELATIONSHIP() {
		return PARNTS_MBR_RELATIONSHIP;
	}
	public void setPARNTS_MBR_RELATIONSHIP(String pARNTS_MBR_RELATIONSHIP) {
		PARNTS_MBR_RELATIONSHIP = pARNTS_MBR_RELATIONSHIP;
	}
	public String getINDVDL_INFO_ST() {
		return INDVDL_INFO_ST;
	}
	public void setINDVDL_INFO_ST(String iNDVDL_INFO_ST) {
		INDVDL_INFO_ST = iNDVDL_INFO_ST;
	}
	public String getPARNTS_MBR_NM() {
		return PARNTS_MBR_NM;
	}
	public void setPARNTS_MBR_NM(String pARNTS_MBR_NM) {
		PARNTS_MBR_NM = pARNTS_MBR_NM;
	}
	public String getPARNTS_MBR_HP() {
		return PARNTS_MBR_HP;
	}
	public void setPARNTS_MBR_HP(String pARNTS_MBR_HP) {
		PARNTS_MBR_HP = pARNTS_MBR_HP;
	}
	public String getPARNTS_MBR_BIRTH() {
		return PARNTS_MBR_BIRTH;
	}
	public void setPARNTS_MBR_BIRTH(String pARNTS_MBR_BIRTH) {
		PARNTS_MBR_BIRTH = pARNTS_MBR_BIRTH;
	}
	public String getUNDER_AGE_14_ST() {
		return UNDER_AGE_14_ST;
	}
	public void setUNDER_AGE_14_ST(String uNDER_AGE_14_ST) {
		UNDER_AGE_14_ST = uNDER_AGE_14_ST;
	}
	public String getRMNDR_BUSINESS_NUM() {
		return RMNDR_BUSINESS_NUM;
	}
	public void setRMNDR_BUSINESS_NUM(String rMNDR_BUSINESS_NUM) {
		RMNDR_BUSINESS_NUM = rMNDR_BUSINESS_NUM;
	}
	public String getRMNDR_MBR_ZIP() {
		return RMNDR_MBR_ZIP;
	}
	public void setRMNDR_MBR_ZIP(String rMNDR_MBR_ZIP) {
		RMNDR_MBR_ZIP = rMNDR_MBR_ZIP;
	}
	public String getRMNDR_CRS_NM() {
		return RMNDR_CRS_NM;
	}
	public void setRMNDR_CRS_NM(String rMNDR_CRS_NM) {
		RMNDR_CRS_NM = rMNDR_CRS_NM;
	}
	public String getRMNDR_DTL_CD() {
		return RMNDR_DTL_CD;
	}
	public void setRMNDR_DTL_CD(String rMNDR_DTL_CD) {
		RMNDR_DTL_CD = rMNDR_DTL_CD;
	}
	public String getRMNDR_MBR_TEL() {
		return RMNDR_MBR_TEL;
	}
	public void setRMNDR_MBR_TEL(String rMNDR_MBR_TEL) {
		RMNDR_MBR_TEL = rMNDR_MBR_TEL;
	}
	public String getRMNDR_MBR_ADDR1() {
		return RMNDR_MBR_ADDR1;
	}
	public void setRMNDR_MBR_ADDR1(String rMNDR_MBR_ADDR1) {
		RMNDR_MBR_ADDR1 = rMNDR_MBR_ADDR1;
	}
	public String getRMNDR_MBR_ADDR2() {
		return RMNDR_MBR_ADDR2;
	}
	public void setRMNDR_MBR_ADDR2(String rMNDR_MBR_ADDR2) {
		RMNDR_MBR_ADDR2 = rMNDR_MBR_ADDR2;
	}
	public String getRMNDR_CRS_SN() {
		return RMNDR_CRS_SN;
	}
	public void setRMNDR_CRS_SN(String rMNDR_CRS_SN) {
		RMNDR_CRS_SN = rMNDR_CRS_SN;
	}
	public String getRMNDR_DTL_LICENSE_CD() {
		return RMNDR_DTL_LICENSE_CD;
	}
	public void setRMNDR_DTL_LICENSE_CD(String rMNDR_DTL_LICENSE_CD) {
		RMNDR_DTL_LICENSE_CD = rMNDR_DTL_LICENSE_CD;
	}
	public String getRMNDR_DTL_NM() {
		return RMNDR_DTL_NM;
	}
	public void setRMNDR_DTL_NM(String rMNDR_DTL_NM) {
		RMNDR_DTL_NM = rMNDR_DTL_NM;
	}
	public String getRMNDR_REG_NUM_CD() {
		return RMNDR_REG_NUM_CD;
	}
	public void setRMNDR_REG_NUM_CD(String rMNDR_REG_NUM_CD) {
		RMNDR_REG_NUM_CD = rMNDR_REG_NUM_CD;
	}
	public String getRMNDR_SIDO_CD() {
		return RMNDR_SIDO_CD;
	}
	public void setRMNDR_SIDO_CD(String rMNDR_SIDO_CD) {
		RMNDR_SIDO_CD = rMNDR_SIDO_CD;
	}
	public String getRMNDR_SIGNGU_CD() {
		return RMNDR_SIGNGU_CD;
	}
	public void setRMNDR_SIGNGU_CD(String rMNDR_SIGNGU_CD) {
		RMNDR_SIGNGU_CD = rMNDR_SIGNGU_CD;
	}
	public String getRMNDR_YMD_NM() {
		return RMNDR_YMD_NM;
	}
	public void setRMNDR_YMD_NM(String rMNDR_YMD_NM) {
		RMNDR_YMD_NM = rMNDR_YMD_NM;
	}
	public String getRMNDR_SHIP_CD() {
		return RMNDR_SHIP_CD;
	}
	public void setRMNDR_SHIP_CD(String rMNDR_SHIP_CD) {
		RMNDR_SHIP_CD = rMNDR_SHIP_CD;
	}
	public String getRMNDR_SHIP_LICENSE() {
		return RMNDR_SHIP_LICENSE;
	}
	public void setRMNDR_SHIP_LICENSE(String rMNDR_SHIP_LICENSE) {
		RMNDR_SHIP_LICENSE = rMNDR_SHIP_LICENSE;
	}
	public String getRMNDR_SHIP_LICENSE_STR_DT() {
		return RMNDR_SHIP_LICENSE_STR_DT;
	}
	public void setRMNDR_SHIP_LICENSE_STR_DT(String rMNDR_SHIP_LICENSE_STR_DT) {
		RMNDR_SHIP_LICENSE_STR_DT = rMNDR_SHIP_LICENSE_STR_DT;
	}
	public String getRMNDR_SHIP_LICENSE_END_DT() {
		return RMNDR_SHIP_LICENSE_END_DT;
	}
	public void setRMNDR_SHIP_LICENSE_END_DT(String rMNDR_SHIP_LICENSE_END_DT) {
		RMNDR_SHIP_LICENSE_END_DT = rMNDR_SHIP_LICENSE_END_DT;
	}
	public String getCMPLT_ST() {
		return CMPLT_ST;
	}
	public void setCMPLT_ST(String cMPLT_ST) {
		CMPLT_ST = cMPLT_ST;
	}
	public String getRMNDR_SN() {
		return RMNDR_SN;
	}
	public void setRMNDR_SN(String rMNDR_SN) {
		RMNDR_SN = rMNDR_SN;
	}
	public String getRMNDR_MBR_DSCRP() {
		return RMNDR_MBR_DSCRP;
	}
	public void setRMNDR_MBR_DSCRP(String rMNDR_MBR_DSCRP) {
		RMNDR_MBR_DSCRP = rMNDR_MBR_DSCRP;
	}
	public String getRMNDR_MBR_NM() {
		return RMNDR_MBR_NM;
	}
	public void setRMNDR_MBR_NM(String rMNDR_MBR_NM) {
		RMNDR_MBR_NM = rMNDR_MBR_NM;
	}
	public String getRMNDR_MBR_HP() {
		return RMNDR_MBR_HP;
	}
	public void setRMNDR_MBR_HP(String rMNDR_MBR_HP) {
		RMNDR_MBR_HP = rMNDR_MBR_HP;
	}
	public String getRMNDR_MBR_BIRTH() {
		return RMNDR_MBR_BIRTH;
	}
	public void setRMNDR_MBR_BIRTH(String rMNDR_MBR_BIRTH) {
		RMNDR_MBR_BIRTH = rMNDR_MBR_BIRTH;
	}
	public String getRMNDR_MBR_ID() {
		return RMNDR_MBR_ID;
	}
	public void setRMNDR_MBR_ID(String rMNDR_MBR_ID) {
		RMNDR_MBR_ID = rMNDR_MBR_ID;
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
	public String getUSE_ST() {
		return USE_ST;
	}
	public void setUSE_ST(String uSE_ST) {
		USE_ST = uSE_ST;
	}
	public String getDEL_ST() {
		return DEL_ST;
	}
	public void setDEL_ST(String dEL_ST) {
		DEL_ST = dEL_ST;
	}	
}
