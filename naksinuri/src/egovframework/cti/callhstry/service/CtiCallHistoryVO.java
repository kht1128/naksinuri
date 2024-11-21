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
package egovframework.cti.callhstry.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [나의상담내역]
 * 테이블명 : CTI_CALL_HSTRY_TB
 */
public class CtiCallHistoryVO extends EduDefaultVO {
	
	private String HCALL_SN;//int(11) NOT NULL콜이력번호
	private String HCALL_TYPE;//varchar(50) NULL콜분류
	private String HCALL_ST;//varchar(10) NULL콜상태
	private String HCALL_DT;//datetime NULL통화일시
	private String HCALL_TIME;//varchar(50) NULL실제통화시간
	private String HCALL_READY_TIME;//varchar(50) NULL전화벨대기시간
	private String STAFF_MBR_ID;//varchar(50) NULL상담사아이디
	private String MBR_HP;//varchar(20) NULL회원연락처
	private String MBR_ID;//varchar(50) NULL회원아이디
	private String CALL_CD;//varchar(250) NULL콜이력코드
	private String REC_FILE_NM;//text NULL녹취파일명
	private String EXTNO;//varchar(50) NULL내선번호
	private String HCALL_R_TEL;//varchar(50) NULL수신번호
	private String HCALL_S_TEL;//varchar(50) NULL발신번호
	private String USE_ST;//int(3) NULL0:사용안함,1:사용함
	private String DEL_ST;//int(3) NULL0:삭제안함,1:삭제함
	private String REG_DT;//datetime NULL작성일자
	private String UPD_DT;//datetime NULL수정일자
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String CALL_LOG_MSG;//text NULL콜기록
	private String HCALL_AGREE_1;//enum('Y','N','NONE') NULL개인정보수집이용동의
	private String HCALL_AGREE_2;//enum('Y','N','NONE') NULL개인정보제3자제공동의
	private String HCALL_IVR;//varchar(50) NULLIVR정보
	private String HCALL_IVR_CD;//varchar(40) NULLIVR코드값
	private String HCALL_CONT;//text NULL상담내용
	private String HCALL_MEMO;//text NULL특이사항
	private String HCALL_IMPRT;//int(3) NULL상:3,중:2,하:1
	private String HCALL_GUBUN_1;//varchar(20) NULL상담분류 1단계
	private String HCALL_GUBUN_2;//varchar(20) NULL상담분류 2단계
	private String HCALL_FILE_1;//varchar(255) NULL법인사업자팩스신청서첨부파일
	private String HCALL_FILE_2;//varchar(255) NULL이수증발급팩스신청서첨부파일
	private String HCALL_DTL_TYPE;	// 콜 상세 분류
	
	//디비외
	private String IS_JOIN_MBR;//회원가입여부(Y,N)
	private String IS_CTI_MBR;//상담회원여부(Y,N)
	private String STAFF_MBR_NM;//상담사 이름
	
	private String CNSLT_MBR_NM;//회원명-상담회원정보
	private String CNSLT_MBR_HP;//회원연락처-상담회원정보
	private String JOIN_MBR_ID;//회원아이디-회원가입정보
	private String JOIN_MBR_NM;//회원명-회원가입정보
	private String JOIN_MBR_HP;//회원연락처-회원가입정보
	private String CTI_MBR_NM;//회원명-cti회원가입정보
	private String CTI_MBR_HP;//회원연락처-cti회원가입정보
	private String JOIN_HP_MBR_ID;//연락처로조회된-회원아이디
	private String JOIN_HP_MBR_NM;//연락처로조회된-회원이름
	
	private String HCALL_GUBUN_1_NM;//상담분류 1단계 명
	private String HCALL_GUBUN_2_NM;//상담분류 2단계 명
	
	private boolean FORCE_UPD_MBR_ID;//아이디 강제 업데이트 진행
	private String CALL_MBR_HP;//초기 연락처 번호 기억값
	
	//전화수신(벨울림)시 최신 상담 이력 정보 조회에 사용
	private String DTL_NM;//명칭
	private String DTL_CD_NM;//낚시터,낚시어선 구분명
	private String SIDO_CD_NM;//시도명
	private String SIGNGU_CD_NM;//시군구명
	//End of 전화수신(벨울림)시 최신 상담 이력 정보 조회에 사용
	private String MBR_NM;
	private String MBR_BIRTH;
	private String MBR_TEL;
	private String STAFF_NM;
	private String CD_NM1;
	private String CD_NM2;
	private String HCALL_STR_DT;
	private String HCALL_END_DT;
	
	//고광훈 추가 - 회원 추가이름
	private String MBR_NCNM;
	
	public String getCALL_MBR_HP() {
		return CALL_MBR_HP;
	}
	public String getHCALL_DTL_TYPE() {
		return HCALL_DTL_TYPE;
	}
	public void setHCALL_DTL_TYPE(String hCALL_DTL_TYPE) {
		HCALL_DTL_TYPE = hCALL_DTL_TYPE;
	}
	public String getHCALL_FILE_1() {
		return HCALL_FILE_1;
	}
	public void setHCALL_FILE_1(String hCALL_FILE_1) {
		HCALL_FILE_1 = hCALL_FILE_1;
	}
	public String getHCALL_FILE_2() {
		return HCALL_FILE_2;
	}
	public void setHCALL_FILE_2(String hCALL_FILE_2) {
		HCALL_FILE_2 = hCALL_FILE_2;
	}
	public String getDTL_NM() {
		return DTL_NM;
	}
	public void setDTL_NM(String dTL_NM) {
		DTL_NM = dTL_NM;
	}
	public String getDTL_CD_NM() {
		return DTL_CD_NM;
	}
	public void setDTL_CD_NM(String dTL_CD_NM) {
		DTL_CD_NM = dTL_CD_NM;
	}
	public String getSIDO_CD_NM() {
		return SIDO_CD_NM;
	}
	public void setSIDO_CD_NM(String sIDO_CD_NM) {
		SIDO_CD_NM = sIDO_CD_NM;
	}
	public String getSIGNGU_CD_NM() {
		return SIGNGU_CD_NM;
	}
	public void setSIGNGU_CD_NM(String sIGNGU_CD_NM) {
		SIGNGU_CD_NM = sIGNGU_CD_NM;
	}
	public void setCALL_MBR_HP(String cALL_MBR_HP) {
		CALL_MBR_HP = cALL_MBR_HP;
	}
	public boolean isFORCE_UPD_MBR_ID() {
		return FORCE_UPD_MBR_ID;
	}
	public void setFORCE_UPD_MBR_ID(boolean fORCE_UPD_MBR_ID) {
		FORCE_UPD_MBR_ID = fORCE_UPD_MBR_ID;
	}
	public String getHCALL_GUBUN_1_NM() {
		return HCALL_GUBUN_1_NM;
	}
	public void setHCALL_GUBUN_1_NM(String hCALL_GUBUN_1_NM) {
		HCALL_GUBUN_1_NM = hCALL_GUBUN_1_NM;
	}
	public String getHCALL_GUBUN_2_NM() {
		return HCALL_GUBUN_2_NM;
	}
	public void setHCALL_GUBUN_2_NM(String hCALL_GUBUN_2_NM) {
		HCALL_GUBUN_2_NM = hCALL_GUBUN_2_NM;
	}
	public String getHCALL_GUBUN_1() {
		return HCALL_GUBUN_1;
	}
	public void setHCALL_GUBUN_1(String hCALL_GUBUN_1) {
		HCALL_GUBUN_1 = hCALL_GUBUN_1;
	}
	public String getHCALL_GUBUN_2() {
		return HCALL_GUBUN_2;
	}
	public void setHCALL_GUBUN_2(String hCALL_GUBUN_2) {
		HCALL_GUBUN_2 = hCALL_GUBUN_2;
	}
	public String getHCALL_IMPRT() {
		return HCALL_IMPRT;
	}
	public void setHCALL_IMPRT(String hCALL_IMPRT) {
		HCALL_IMPRT = hCALL_IMPRT;
	}
	public String getHCALL_CONT() {
		return HCALL_CONT;
	}
	public void setHCALL_CONT(String hCALL_CONT) {
		HCALL_CONT = hCALL_CONT;
	}
	public String getHCALL_MEMO() {
		return HCALL_MEMO;
	}
	public void setHCALL_MEMO(String hCALL_MEMO) {
		HCALL_MEMO = hCALL_MEMO;
	}
	public String getJOIN_MBR_ID() {
		return JOIN_MBR_ID;
	}
	public void setJOIN_MBR_ID(String jOIN_MBR_ID) {
		JOIN_MBR_ID = jOIN_MBR_ID;
	}
	public String getJOIN_HP_MBR_ID() {
		return JOIN_HP_MBR_ID;
	}
	public void setJOIN_HP_MBR_ID(String jOIN_HP_MBR_ID) {
		JOIN_HP_MBR_ID = jOIN_HP_MBR_ID;
	}
	public String getJOIN_HP_MBR_NM() {
		return JOIN_HP_MBR_NM;
	}
	public void setJOIN_HP_MBR_NM(String jOIN_HP_MBR_NM) {
		JOIN_HP_MBR_NM = jOIN_HP_MBR_NM;
	}
	public String getHCALL_IVR_CD() {
		return HCALL_IVR_CD;
	}
	public void setHCALL_IVR_CD(String hCALL_IVR_CD) {
		HCALL_IVR_CD = hCALL_IVR_CD;
	}
	public String getHCALL_IVR() {
		return HCALL_IVR;
	}
	public void setHCALL_IVR(String hCALL_IVR) {
		HCALL_IVR = hCALL_IVR;
	}
	public String getHCALL_AGREE_1() {
		return HCALL_AGREE_1;
	}
	public void setHCALL_AGREE_1(String hCALL_AGREE_1) {
		HCALL_AGREE_1 = hCALL_AGREE_1;
	}
	public String getHCALL_AGREE_2() {
		return HCALL_AGREE_2;
	}
	public void setHCALL_AGREE_2(String hCALL_AGREE_2) {
		HCALL_AGREE_2 = hCALL_AGREE_2;
	}
	public String getCALL_LOG_MSG() {
		return CALL_LOG_MSG;
	}
	public void setCALL_LOG_MSG(String cALL_LOG_MSG) {
		CALL_LOG_MSG = cALL_LOG_MSG;
	}
	public String getHCALL_READY_TIME() {
		return HCALL_READY_TIME;
	}
	public void setHCALL_READY_TIME(String hCALL_READY_TIME) {
		HCALL_READY_TIME = hCALL_READY_TIME;
	}
	public String getCTI_MBR_NM() {
		return CTI_MBR_NM;
	}
	public void setCTI_MBR_NM(String cTI_MBR_NM) {
		CTI_MBR_NM = cTI_MBR_NM;
	}
	public String getCTI_MBR_HP() {
		return CTI_MBR_HP;
	}
	public void setCTI_MBR_HP(String cTI_MBR_HP) {
		CTI_MBR_HP = cTI_MBR_HP;
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
	public String getSTAFF_MBR_NM() {
		return STAFF_MBR_NM;
	}
	public void setSTAFF_MBR_NM(String sTAFF_MBR_NM) {
		STAFF_MBR_NM = sTAFF_MBR_NM;
	}
	public String getCNSLT_MBR_NM() {
		return CNSLT_MBR_NM;
	}
	public void setCNSLT_MBR_NM(String cNSLT_MBR_NM) {
		CNSLT_MBR_NM = cNSLT_MBR_NM;
	}
	public String getCNSLT_MBR_HP() {
		return CNSLT_MBR_HP;
	}
	public void setCNSLT_MBR_HP(String cNSLT_MBR_HP) {
		CNSLT_MBR_HP = cNSLT_MBR_HP;
	}
	public String getJOIN_MBR_NM() {
		return JOIN_MBR_NM;
	}
	public void setJOIN_MBR_NM(String jOIN_MBR_NM) {
		JOIN_MBR_NM = jOIN_MBR_NM;
	}
	public String getJOIN_MBR_HP() {
		return JOIN_MBR_HP;
	}
	public void setJOIN_MBR_HP(String jOIN_MBR_HP) {
		JOIN_MBR_HP = jOIN_MBR_HP;
	}
	public String getHCALL_SN() {
		return HCALL_SN;
	}
	public void setHCALL_SN(String hCALL_SN) {
		HCALL_SN = hCALL_SN;
	}
	public String getHCALL_TYPE() {
		return HCALL_TYPE;
	}
	public void setHCALL_TYPE(String hCALL_TYPE) {
		HCALL_TYPE = hCALL_TYPE;
	}
	public String getHCALL_ST() {
		return HCALL_ST;
	}
	public void setHCALL_ST(String hCALL_ST) {
		HCALL_ST = hCALL_ST;
	}
	public String getHCALL_DT() {
		return HCALL_DT;
	}
	public void setHCALL_DT(String hCALL_DT) {
		HCALL_DT = hCALL_DT;
	}
	public String getHCALL_TIME() {
		return HCALL_TIME;
	}
	public void setHCALL_TIME(String hCALL_TIME) {
		HCALL_TIME = hCALL_TIME;
	}
	public String getSTAFF_MBR_ID() {
		return STAFF_MBR_ID;
	}
	public void setSTAFF_MBR_ID(String sTAFF_MBR_ID) {
		STAFF_MBR_ID = sTAFF_MBR_ID;
	}
	public String getMBR_HP() {
		return MBR_HP;
	}
	public void setMBR_HP(String mBR_HP) {
		MBR_HP = mBR_HP;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getCALL_CD() {
		return CALL_CD;
	}
	public void setCALL_CD(String cALL_CD) {
		CALL_CD = cALL_CD;
	}
	public String getREC_FILE_NM() {
		return REC_FILE_NM;
	}
	public void setREC_FILE_NM(String rEC_FILE_NM) {
		REC_FILE_NM = rEC_FILE_NM;
	}
	public String getEXTNO() {
		return EXTNO;
	}
	public void setEXTNO(String eXTNO) {
		EXTNO = eXTNO;
	}	
	public String getHCALL_R_TEL() {
		return HCALL_R_TEL;
	}
	public void setHCALL_R_TEL(String hCALL_R_TEL) {
		HCALL_R_TEL = hCALL_R_TEL;
	}
	public String getHCALL_S_TEL() {
		return HCALL_S_TEL;
	}
	public void setHCALL_S_TEL(String hCALL_S_TEL) {
		HCALL_S_TEL = hCALL_S_TEL;
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
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMBR_BIRTH() {
		return MBR_BIRTH;
	}
	public void setMBR_BIRTH(String mBR_BIRTH) {
		MBR_BIRTH = mBR_BIRTH;
	}
	public String getMBR_TEL() {
		return MBR_TEL;
	}
	public void setMBR_TEL(String mBR_TEL) {
		MBR_TEL = mBR_TEL;
	}
	public String getSTAFF_NM() {
		return STAFF_NM;
	}
	public void setSTAFF_NM(String sTAFF_NM) {
		STAFF_NM = sTAFF_NM;
	}
	public String getCD_NM1() {
		return CD_NM1;
	}
	public void setCD_NM1(String cD_NM1) {
		CD_NM1 = cD_NM1;
	}
	public String getCD_NM2() {
		return CD_NM2;
	}
	public void setCD_NM2(String cD_NM2) {
		CD_NM2 = cD_NM2;
	}
	public String getHCALL_STR_DT() {
		return HCALL_STR_DT;
	}
	public void setHCALL_STR_DT(String hCALL_STR_DT) {
		HCALL_STR_DT = hCALL_STR_DT;
	}
	public String getHCALL_END_DT() {
		return HCALL_END_DT;
	}
	public void setHCALL_END_DT(String hCALL_END_DT) {
		HCALL_END_DT = hCALL_END_DT;
	}
	public String getMBR_NCNM() {
		return MBR_NCNM;
	}
	public void setMBR_NCNM(String mBR_NCNM) {
		MBR_NCNM = mBR_NCNM;
	}
	
}
