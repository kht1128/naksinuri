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
package egovframework.cti.member.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [멤버테이블]
 * 테이블명 : SL_MEMBER_TB
 */
public class CtiMemberVO extends EduDefaultVO {
	
	//공통
	private String MBR_ID;//varchar(50) NULL회원아이디
	private String REG_DT;//datetime NULL생성일자
	private String UPD_DT;//datetime NULL변경일자
	private String REG_MBR_ID;//varchar(50) NULL생성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL변경자아이디
	private String USE_ST;//enum('Y','N') NULLY:사용안함,N:사용함
	private String DEL_ST;//enum('Y','N') NULLN:정상,Y:삭제함
	
	//cti_staff_tb
	private String STAFF_SN;//int(11) NOT NULL스태프번호
	private String STAFF_NM;//varchar(20) NULL스태프닉네임
	private String CTI_ID;//varchar(50) NULLCtiID
	private String CTI_TEL_NO;//varchar(50) NULLDestTelNo
	private String ORGNZ_NM;//varchar(20) NULL조직명
	private String LST_LOGIN_DT;//datetime NULL마지막로그인
	
	//cti_mbr_tb
	private String CTI_MBR_SN;//int(11) NOT NULL회원번호
	private String CTI_MBR_HP;//varchar(20) NULL회원연락처
	private String CTI_MBR_NM;//varchar(50) NULL회원이름
	private String CTI_MBR_BIRTH;//varchar(20) NULL생년월일
	
	//디비외
	private String LOG_UPD_MSG;//변경이력(사용자수기입력 등)
	private String MBR_AGE;//나이계산값
	private String CTI_MBR_AGE;//나이계산값(CTI)
	private String IS_JOIN_MBR;//회원가입여부(Y,N)
	private String IS_CTI_MBR;//상담회원여부(Y,N)
	
	private String IS_ONLY_CTI;//CTI정보만추출
	private String CTI_MBR_HP_UPD_YN;//CTI통화건 연락처로 교체 여부
	private String CTI_MBR_HP_CHK;//CTI통화건 교체할 연락처
	
	private String MBR_SN;//회원번호
	
	
	public String getMBR_SN() {
		return MBR_SN;
	}
	public void setMBR_SN(String mBR_SN) {
		MBR_SN = mBR_SN;
	}
	public String getCTI_MBR_HP_CHK() {
		return CTI_MBR_HP_CHK;
	}
	public void setCTI_MBR_HP_CHK(String cTI_MBR_HP_CHK) {
		CTI_MBR_HP_CHK = cTI_MBR_HP_CHK;
	}
	public String getCTI_MBR_HP_UPD_YN() {
		return CTI_MBR_HP_UPD_YN;
	}
	public void setCTI_MBR_HP_UPD_YN(String cTI_MBR_HP_UPD_YN) {
		CTI_MBR_HP_UPD_YN = cTI_MBR_HP_UPD_YN;
	}
	public String getIS_ONLY_CTI() {
		return IS_ONLY_CTI;
	}
	public void setIS_ONLY_CTI(String iS_ONLY_CTI) {
		IS_ONLY_CTI = iS_ONLY_CTI;
	}
	public String getIS_JOIN_MBR() {
		return IS_JOIN_MBR;
	}
	public void setIS_JOIN_MBR(String iS_JOIN_MBR) {
		IS_JOIN_MBR = iS_JOIN_MBR;
	}
	public String getIS_CTI_MBR() {
		return IS_CTI_MBR;
	}
	public void setIS_CTI_MBR(String iS_CTI_MBR) {
		IS_CTI_MBR = iS_CTI_MBR;
	}
	public String getMBR_AGE() {
		return MBR_AGE;
	}
	public void setMBR_AGE(String mBR_AGE) {
		MBR_AGE = mBR_AGE;
	}
	public String getCTI_MBR_AGE() {
		return CTI_MBR_AGE;
	}
	public void setCTI_MBR_AGE(String cTI_MBR_AGE) {
		CTI_MBR_AGE = cTI_MBR_AGE;
	}
	public String getCTI_MBR_SN() {
		return CTI_MBR_SN;
	}
	public void setCTI_MBR_SN(String cTI_MBR_SN) {
		CTI_MBR_SN = cTI_MBR_SN;
	}
	public String getCTI_MBR_HP() {
		return CTI_MBR_HP;
	}
	public void setCTI_MBR_HP(String cTI_MBR_HP) {
		CTI_MBR_HP = cTI_MBR_HP;
	}
	public String getCTI_MBR_NM() {
		return CTI_MBR_NM;
	}
	public void setCTI_MBR_NM(String cTI_MBR_NM) {
		CTI_MBR_NM = cTI_MBR_NM;
	}
	public String getCTI_MBR_BIRTH() {
		return CTI_MBR_BIRTH;
	}
	public void setCTI_MBR_BIRTH(String cTI_MBR_BIRTH) {
		CTI_MBR_BIRTH = cTI_MBR_BIRTH;
	}
	public String getLOG_UPD_MSG() {
		return LOG_UPD_MSG;
	}
	public void setLOG_UPD_MSG(String lOG_UPD_MSG) {
		LOG_UPD_MSG = lOG_UPD_MSG;
	}
	public String getSTAFF_SN() {
		return STAFF_SN;
	}
	public void setSTAFF_SN(String sTAFF_SN) {
		STAFF_SN = sTAFF_SN;
	}
	public String getSTAFF_NM() {
		return STAFF_NM;
	}
	public void setSTAFF_NM(String sTAFF_NM) {
		STAFF_NM = sTAFF_NM;
	}
	public String getCTI_ID() {
		return CTI_ID;
	}
	public void setCTI_ID(String cTI_ID) {
		CTI_ID = cTI_ID;
	}
	public String getCTI_TEL_NO() {
		return CTI_TEL_NO;
	}
	public void setCTI_TEL_NO(String cTI_TEL_NO) {
		CTI_TEL_NO = cTI_TEL_NO;
	}
	public String getORGNZ_NM() {
		return ORGNZ_NM;
	}
	public void setORGNZ_NM(String oRGNZ_NM) {
		ORGNZ_NM = oRGNZ_NM;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
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
	public String getLST_LOGIN_DT() {
		return LST_LOGIN_DT;
	}
	public void setLST_LOGIN_DT(String lST_LOGIN_DT) {
		LST_LOGIN_DT = lST_LOGIN_DT;
	}
		
}
