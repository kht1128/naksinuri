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
package egovframework.cti.curriculum.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [교육과정]
 * 테이블명 : EDU_CURRICULUM_TB , EDU_CURRICULUM_DTL_TB
 */
public class CtiCurriculumVO extends EduDefaultVO {

	//교육과정1
	private String STEP_ST;//varchar(50) NOT NULL순차진행여부(sort,random)
	private String MBR_MAX_CNT;//int(11) NULL0:무제한
	private String MBR_CUR_CNT;//int(11) NULL신청자수
	private String MBR_CMPLT_CNT;//int(11) NULL이수완료자수
	private String CRS_STR_DT;//datetime NULL교육시작날짜
	private String CRS_END_DT;//datetime NULL교육종료날짜
	private String RECRUIT_STR_DT;//datetime NULL모집시작일자
	private String RECRUIT_END_DT;//datetime NULL모집종료일자
	private String CRS_PLACE;//varchar(250) NULL교육장소
	private String CRS_ADDR;//varchar(250) NULL교육장주소
	private String CRS_MBR;//varchar(50) NULL교육담당자
	private String CRS_MBR_TEL;//varchar(50) NULL교육담당자연락처
	private String CAT_INS_SN;//varchar(50) NULL교육기관
	private String CRS_SCHDL_FILE_SN;//varchar(50) NULL교육시간표(첨부파일)
	private String CRS_CASH;//int(11) NULL교육비(수강비)
	private String CRS_DPST_ACNT;//varchar(50) NULL입금계좌
	private String CRS_DPST_BANK;//varchar(50) NULL입금은행명
	private String CRS_DPST_MBR;//varchar(50) NULL입금계좌주
	private String CAT_VISIT_SN;//varchar(50) NULL교육방문방법설정
	private String CRS_ST;//int(5) NULL0:대기,1:승인
	private String CRS_TYPE;//varchar(50) NULL default:기본(종합교육),fshsk_trnng:귀어창업기술교육,wknd_trnng:주말교육
	private String CRS_GRP_CD;//varchar(10) NULL교육과정그룹코드
	private String CRS_MBR_CD;//varchar(10) NULL교육대상그룹코드
	private String CRS_LAW_TYPE;//varchar(250) NULL교육구분설명(법규 등)
	private String CRS_LOCK_AREA_CD;//varchar(20) NULL교육신청지역제한(CID00008)
	private String CRS_LOCK_AREA_ST;//int(3) NULL0:교육신청지역제한사용안함,1:교육신청지역제한사용함
	private String CRS_SV_ID;	//설문조사 고유번호
	private String CRS_SV_ST;	//설문조사 사용여부
	
	//교육과정2
	private String CRS_DTL_SN;//int(11) NOT NULL교육과정(중)
	private String CRS_DTL_NM;//varchar(250) NOT NULL교육과정(중)명칭
	private String CRS_DTL_DSCRP;//varchar(250) NULL교육과정(중)설명
	private String CRS_DTL_FILE;//varchar(255) NULL첨부파일(다운로드용)
	private String TYPE_GB;//varchar(50) NOT NULLonline,offline
	private String TRN_SN;//int(11) NOT NULL교육자료번호
	
	//공통
	private String CRS_ORD;//int(11) NULL우선순위(기본값:9999)
	private String CRS_SN;//int(11) NOT NULL교육과정번호
	private String CRS_NM;//varchar(250) NOT NULL교육과정명칭
	private String CRS_DSCRP;//varchar(250) NULL설명
	private String CAT_SN;//int(11) NOT NULL교육카테고리1
	private String CAT_DTL_SN;//int(11) NOT NULL교육카테고리2
	private String CRS_TOT_TIME;//int(11) NULL총교육시간
	private String CRS_TOT_POINT;//int(11) NULL총교육점수
	private String LOCK_ST;//int(3) NULL0:해제,1:잠금
	private String REG_DT;//datetime NULL작성일자
	private String UPD_DT;//datetime NULL수정일자
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String USE_ST;//int(3) NULL0:사용안함,1:사용함
	private String DEL_ST;//int(3) NULL0:삭제안함,1:삭제함
	
	//디비 외
	private String typeStr;//카테고리분류(null:대분류,dtl:하위)
	private String USE_ST_CHK;//사용유무체크박스	
	private String CAT_INS_NM;//교육기관명
	private String CAT_NM;//카테고리명(대)
	private String CAT_DTL_NM;//카테고리명(중)
	private String LRNNG_GB;//교육카테고리2상태값(online,offline)
	private int SUM_TOT_TIME;//소속 교과목 총교육시간 합계
	private int SUM_TOT_POINT;//소속 교과목 총교육점수 합계
	
	
	public String getCRS_SV_ID() {
		return CRS_SV_ID;
	}
	public void setCRS_SV_ID(String cRS_SV_ID) {
		CRS_SV_ID = cRS_SV_ID;
	}
	public String getCRS_SV_ST() {
		return CRS_SV_ST;
	}
	public void setCRS_SV_ST(String cRS_SV_ST) {
		CRS_SV_ST = cRS_SV_ST;
	}
	public String getCRS_LOCK_AREA_CD() {
		return CRS_LOCK_AREA_CD;
	}
	public void setCRS_LOCK_AREA_CD(String cRS_LOCK_AREA_CD) {
		CRS_LOCK_AREA_CD = cRS_LOCK_AREA_CD;
	}
	public String getCRS_LOCK_AREA_ST() {
		return CRS_LOCK_AREA_ST;
	}
	public void setCRS_LOCK_AREA_ST(String cRS_LOCK_AREA_ST) {
		CRS_LOCK_AREA_ST = cRS_LOCK_AREA_ST;
	}
	public String getCRS_LAW_TYPE() {
		return CRS_LAW_TYPE;
	}
	public void setCRS_LAW_TYPE(String cRS_LAW_TYPE) {
		CRS_LAW_TYPE = cRS_LAW_TYPE;
	}
	public String getCRS_MBR_CD() {
		return CRS_MBR_CD;
	}
	public void setCRS_MBR_CD(String cRS_MBR_CD) {
		CRS_MBR_CD = cRS_MBR_CD;
	}
	public String getCRS_GRP_CD() {
		return CRS_GRP_CD;
	}
	public void setCRS_GRP_CD(String cRS_GRP_CD) {
		CRS_GRP_CD = cRS_GRP_CD;
	}
	public String getCRS_ADDR() {
		return CRS_ADDR;
	}
	public void setCRS_ADDR(String cRS_ADDR) {
		CRS_ADDR = cRS_ADDR;
	}
	public String getCRS_DTL_FILE() {
		return CRS_DTL_FILE;
	}
	public void setCRS_DTL_FILE(String cRS_DTL_FILE) {
		CRS_DTL_FILE = cRS_DTL_FILE;
	}
	public String getCRS_DPST_BANK() {
		return CRS_DPST_BANK;
	}
	public void setCRS_DPST_BANK(String cRS_DPST_BANK) {
		CRS_DPST_BANK = cRS_DPST_BANK;
	}
	public String getCRS_DPST_MBR() {
		return CRS_DPST_MBR;
	}
	public void setCRS_DPST_MBR(String cRS_DPST_MBR) {
		CRS_DPST_MBR = cRS_DPST_MBR;
	}
	public String getSTEP_ST() {
		return STEP_ST;
	}
	public void setSTEP_ST(String sTEP_ST) {
		STEP_ST = sTEP_ST;
	}
	public String getMBR_MAX_CNT() {
		return MBR_MAX_CNT;
	}
	public void setMBR_MAX_CNT(String mBR_MAX_CNT) {
		MBR_MAX_CNT = mBR_MAX_CNT;
	}
	public String getMBR_CUR_CNT() {
		return MBR_CUR_CNT;
	}
	public void setMBR_CUR_CNT(String mBR_CUR_CNT) {
		MBR_CUR_CNT = mBR_CUR_CNT;
	}
	public String getMBR_CMPLT_CNT() {
		return MBR_CMPLT_CNT;
	}
	public void setMBR_CMPLT_CNT(String mBR_CMPLT_CNT) {
		MBR_CMPLT_CNT = mBR_CMPLT_CNT;
	}
	public String getCRS_STR_DT() {
		return CRS_STR_DT;
	}
	public void setCRS_STR_DT(String cRS_STR_DT) {
		CRS_STR_DT = cRS_STR_DT;
	}
	public String getCRS_END_DT() {
		return CRS_END_DT;
	}
	public void setCRS_END_DT(String cRS_END_DT) {
		CRS_END_DT = cRS_END_DT;
	}
	public String getRECRUIT_STR_DT() {
		return RECRUIT_STR_DT;
	}
	public void setRECRUIT_STR_DT(String rECRUIT_STR_DT) {
		RECRUIT_STR_DT = rECRUIT_STR_DT;
	}
	public String getRECRUIT_END_DT() {
		return RECRUIT_END_DT;
	}
	public void setRECRUIT_END_DT(String rECRUIT_END_DT) {
		RECRUIT_END_DT = rECRUIT_END_DT;
	}
	public String getCRS_PLACE() {
		return CRS_PLACE;
	}
	public void setCRS_PLACE(String cRS_PLACE) {
		CRS_PLACE = cRS_PLACE;
	}
	public String getCRS_MBR() {
		return CRS_MBR;
	}
	public void setCRS_MBR(String cRS_MBR) {
		CRS_MBR = cRS_MBR;
	}
	public String getCRS_MBR_TEL() {
		return CRS_MBR_TEL;
	}
	public void setCRS_MBR_TEL(String cRS_MBR_TEL) {
		CRS_MBR_TEL = cRS_MBR_TEL;
	}
	public String getCAT_INS_SN() {
		return CAT_INS_SN;
	}
	public void setCAT_INS_SN(String cAT_INS_SN) {
		CAT_INS_SN = cAT_INS_SN;
	}
	public String getCRS_SCHDL_FILE_SN() {
		return CRS_SCHDL_FILE_SN;
	}
	public void setCRS_SCHDL_FILE_SN(String cRS_SCHDL_FILE_SN) {
		CRS_SCHDL_FILE_SN = cRS_SCHDL_FILE_SN;
	}
	public String getCRS_CASH() {
		return CRS_CASH;
	}
	public void setCRS_CASH(String cRS_CASH) {
		CRS_CASH = cRS_CASH;
	}
	public String getCRS_DPST_ACNT() {
		return CRS_DPST_ACNT;
	}
	public void setCRS_DPST_ACNT(String cRS_DPST_ACNT) {
		CRS_DPST_ACNT = cRS_DPST_ACNT;
	}
	public String getCAT_VISIT_SN() {
		return CAT_VISIT_SN;
	}
	public void setCAT_VISIT_SN(String cAT_VISIT_SN) {
		CAT_VISIT_SN = cAT_VISIT_SN;
	}
	public String getCRS_ST() {
		return CRS_ST;
	}
	public void setCRS_ST(String cRS_ST) {
		CRS_ST = cRS_ST;
	}
	public String getCRS_TYPE() {
		return CRS_TYPE;
	}
	public void setCRS_TYPE(String cRS_TYPE) {
		CRS_TYPE = cRS_TYPE;
	}
	public String getCRS_DTL_SN() {
		return CRS_DTL_SN;
	}
	public void setCRS_DTL_SN(String cRS_DTL_SN) {
		CRS_DTL_SN = cRS_DTL_SN;
	}
	public String getCRS_DTL_NM() {
		return CRS_DTL_NM;
	}
	public void setCRS_DTL_NM(String cRS_DTL_NM) {
		CRS_DTL_NM = cRS_DTL_NM;
	}
	public String getCRS_DTL_DSCRP() {
		return CRS_DTL_DSCRP;
	}
	public void setCRS_DTL_DSCRP(String cRS_DTL_DSCRP) {
		CRS_DTL_DSCRP = cRS_DTL_DSCRP;
	}
	public String getTYPE_GB() {
		return TYPE_GB;
	}
	public void setTYPE_GB(String tYPE_GB) {
		TYPE_GB = tYPE_GB;
	}
	public String getTRN_SN() {
		return TRN_SN;
	}
	public void setTRN_SN(String tRN_SN) {
		TRN_SN = tRN_SN;
	}
	public String getCRS_ORD() {
		return CRS_ORD;
	}
	public void setCRS_ORD(String cRS_ORD) {
		CRS_ORD = cRS_ORD;
	}
	public String getCRS_SN() {
		return CRS_SN;
	}
	public void setCRS_SN(String cRS_SN) {
		CRS_SN = cRS_SN;
	}
	public String getCRS_NM() {
		return CRS_NM;
	}
	public void setCRS_NM(String cRS_NM) {
		CRS_NM = cRS_NM;
	}
	public String getCRS_DSCRP() {
		return CRS_DSCRP;
	}
	public void setCRS_DSCRP(String cRS_DSCRP) {
		CRS_DSCRP = cRS_DSCRP;
	}
	public String getCAT_SN() {
		return CAT_SN;
	}
	public void setCAT_SN(String cAT_SN) {
		CAT_SN = cAT_SN;
	}
	public String getCAT_DTL_SN() {
		return CAT_DTL_SN;
	}
	public void setCAT_DTL_SN(String cAT_DTL_SN) {
		CAT_DTL_SN = cAT_DTL_SN;
	}
	public String getCRS_TOT_TIME() {
		return CRS_TOT_TIME;
	}
	public void setCRS_TOT_TIME(String cRS_TOT_TIME) {
		CRS_TOT_TIME = cRS_TOT_TIME;
	}
	public String getCRS_TOT_POINT() {
		return CRS_TOT_POINT;
	}
	public void setCRS_TOT_POINT(String cRS_TOT_POINT) {
		CRS_TOT_POINT = cRS_TOT_POINT;
	}
	public String getLOCK_ST() {
		return LOCK_ST;
	}
	public void setLOCK_ST(String lOCK_ST) {
		LOCK_ST = lOCK_ST;
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
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getUSE_ST_CHK() {
		return USE_ST_CHK;
	}
	public void setUSE_ST_CHK(String uSE_ST_CHK) {
		USE_ST_CHK = uSE_ST_CHK;
	}
	public String getCAT_INS_NM() {
		return CAT_INS_NM;
	}
	public void setCAT_INS_NM(String cAT_INS_NM) {
		CAT_INS_NM = cAT_INS_NM;
	}
	public String getCAT_NM() {
		return CAT_NM;
	}
	public void setCAT_NM(String cAT_NM) {
		CAT_NM = cAT_NM;
	}
	public String getCAT_DTL_NM() {
		return CAT_DTL_NM;
	}
	public void setCAT_DTL_NM(String cAT_DTL_NM) {
		CAT_DTL_NM = cAT_DTL_NM;
	}
	public String getLRNNG_GB() {
		return LRNNG_GB;
	}
	public void setLRNNG_GB(String lRNNG_GB) {
		LRNNG_GB = lRNNG_GB;
	}
	public int getSUM_TOT_TIME() {
		return SUM_TOT_TIME;
	}
	public void setSUM_TOT_TIME(int sUM_TOT_TIME) {
		SUM_TOT_TIME = sUM_TOT_TIME;
	}
	public int getSUM_TOT_POINT() {
		return SUM_TOT_POINT;
	}
	public void setSUM_TOT_POINT(int sUM_TOT_POINT) {
		SUM_TOT_POINT = sUM_TOT_POINT;
	}
	
}
