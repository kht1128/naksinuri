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
package egovframework.eduadm.myhistory.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [나의이수내역]
 * 테이블명 : EDU_MBR_HSTRY_TB , EDU_MBR_HSTRY_DTL_TB
 */
public class EduMyHistoryVO extends EduDefaultVO {
	
	//나의이수내역
	private String HMBR_SN;//int(11) NOT NULL이수내역번호
	private String PURCHASE_CMPLT_ST;//int(3) NULL0:구매전,1:구매완료
	private String PURCHASE_CD;//varchar(50) NULL구매코드
	private String LRNNG_ST;//int(3) NULL0:대기,1:승인,2:취소,3:강제취소,4:보류
	private String LRNNG_TOT_TIME;//int(11) NULL교과목이수종합시간
	private String LRNNG_TOT_POINT;//int(11) NULL교과목이수종합점수
	private String HMBR_INPUT_TIME;//int(11) NULL직접입력교육시간
	private String HMBR_INPUT_POINT;//int(11) NULL직접입력교육점수
	private String HMBR_MAX_TIME;//int(11) NULL최대인정교육시간
	private String HMBR_MAX_POINT;//int(11) NULL최대인정교육점수
	private String HMBR_RCGNT_TIME;//int(11) NULL인정된 교육최종시간
	private String HMBR_RCGNT_POINT;//int(11) NULL인정된 교육최종점수
	private String LRNNG_CMPLT_CNT;//int(11) NULL교과목완료건수
	private String CAT_VISIT_SN;//varchar(50) NULL교육방문방법(사용자선택)
	private String HOPE_AREA;//varchar(50) NULL희망지역(사용자선택)
	private String HOPE_INDST;//varchar(50) NULL희망업종(사용자선택)
	private String HMBR_MBR_TYPE;//int(10) NULL0:비회원,1:회원
	private String TMP_MBR_NM;//varchar(25) NULL비회원 이름
	private String TMP_MBR_HP;//varchar(25) NULL비회원 연락처
	private String TMP_MBR_BIRTH;//varchar(10) NULL비회원 생년월일
	private String LRNNG_CMPLT_DT;//datetime NULL이수완료-일자
	private String TMPR_LRNNG_CMPLT_DT;//datetime NULL가이수완료-일자
	private String LRNNG_CMPLT_MBR_ID;//varchar(50) NULL이수완료-담당자아이디
	private String HMBR_SV_ST;	//설문조사 참여여부
	private String HMBR_FSHRBT_TYPE;//낚시어선 신규,재개자,기존 구분
	private String HMBR_SV_DT; //datetime NULL설문조사 참여일자
	
	//나의이수내역 - 상세
	private String HMBR_DTL_SN;//int(11) NOT NULL이수내역상세번호
	private String CRS_DTL_SN;//int(11) NOT NULL교육정보2단계번호
	private String LRNNG_CUR_TIME;//int(11) NULL나의교육시간
	private String LRNNG_MAX_TIME;//int(11) NULL총교육시간
	private String LRNNG_CUR_POINT;//int(11) NULL나의이수점수
	private String LRNNG_MAX_POINT;//int(11) NULL총교육점수
	private String LRNNG_CMPLT_DTL_ST;//int(45) NULL0:이수전,1:이수완료,2:이수취소,3:이수완료(관리자),4:이수취소(관리자)
	private String RCGNT_TIME;//int(11) NULL인정된 교육시간
	private String RCGNT_POINT;//int(11) NULL인정된 교육점수
	private String CAT_SN;//int(11) NOT NULL교육카테고리1
	private String CAT_DTL_SN;//int(11) NOT NULL교육카테고리2
	private String TRN_SN;//int(11) NOT NULL교육자료번호
	private String TRN_CUR_TIME;//int(11) NULL교육진행시간,이어보기구간(초단위)
	private String TRN_MAX_TIME;//int(11) NULL총교육시간(자료,초단위)
	private String HMBR_ORD;//int(11) NULL정렬순서
	
	//공통
	private String MBR_ID;//varchar(50) NOT NULL회원아이디
	private String CRS_SN;//int(11) NOT NULL교육정보1단계번호
	private String LRNNG_PRGRS;//int(11) NULL학습진행률
	private String LRNNG_CMPLT_ST;//int(45) NULL0:이수전,1:이수완료,2:이수취소,3:이수완료(관리자),4:이수취소(관리자)
	private String REG_DT;//datetime NULL신청일자
	private String UPD_DT;//datetime NULL변경일자
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String USE_ST;//int(3) NULL0:사용안함,1:사용함
	private String DEL_ST;//int(3) NULL0:삭제안함,1:삭제함
	
	//디비 외
	private String DEL_ST_CHK;//삭제유무체크박스
	private String USE_ST_CHK;//사용유무체크박스
	private String MBR_NM;//회원이름
	private String MBR_NCNM;//회원호칭
	private String MBR_HP;//회원연락처
	private String MBR_BIRTH;//회원생년월일
	private String MBR_SEX;//성별
	private String MBR_ADDR1;//주소1
	private String MBR_ADDR2;//주소2
	private String MBR_DSCRP;//설명
	private String CRS_DTL_NM;//교육정보2단계이름	
	private String TRN_NM;//교육자료명칭
	private String TRN_TYPE_ST;//첨부파일타입
	private String HMBR_DTL_CNT;//상세리스트 갯수
	private String CRS_NM;//커리큘럼이름
	private String CRS_STR_DT;//교육시작일자
	private String CRS_END_DT;//교육종료일자
	private String RECRUIT_STR_DT;//모집시작일자
	private String RECRUIT_END_DT;//모집시작일자
	private String CRS_TYPE;//default:기본(종합교육),fshsk_trnng:귀어창업기술교육,wknd_trnng:주말교육
	private String LOCK_ST;//모집중단여부 - 0:해제,1:잠금
	private String CRS_ST;//승인여부상태 - 0:대기,1:승인
	private String CAT_INS_NM;//교육기관명
	private String CRS_TOT_TIME;//총교육시간(오프라인)
	private String CRS_TOT_POINT;//총교육점수(오프라인)
	private int SUM_TOT_TIME;//총교육시간(온라인)
	private int SUM_TOT_POINT;//총교육시간(온라인)
	private String CRS_SV_ID;	//설문조사 고유번호
	private String LRNNG_CHECK_ST;//이수상태 ( 신청상태, 이수상태 조합용 )
	
	//검색용조건(공통)
	private String HMBR_YMD_NM;
	private String MBR_ADDR_MERGE;
	private String MBR_TEL;
	private String MBR_REG_TYPE_CD;
	private String HMBR_DTL_NM;
	private String HMBR_REG_NUM_CD;
	private String HMBR_BUSINESS_NUM;
	private String HMBR_SHIP_LICENSE_STR_DT;
	private String HMBR_SHIP_LICENSE_END_DT;
	private String HMBR_MBR_TRGT_CD;//=DTL_CD
	private String HMBR_SIDO_CD;
	private String HMBR_SIGNGU_CD;
	private String REG_MBR_ID_ST;
	//검색용조건(낚시터용)
	private String HMBR_SHIP_LICENSE;
	private String HMBR_DTL_ADDR;
	private String HMBR_FSHLC_WORK_CD;
	private String HMBR_FSHLC_APPLC;				
	//검색용조건(낚시어선용)
	private String HMBR_SHIP_CD;
	private String HMBR_SHIP_GRTG;
	private String HMBR_SHIP_PRLOAD;
	private String HMBR_SHIP_MAX_PSNGER;
	private String HMBR_SHIP_MAX_CREW;
	private String HMBR_DTL_LICENSE_CD;
	
	private String MBR_SCRTY_KEY;//웹취약점 대응 변수
	
	private String MBR_IDS;//
	private String TEMPLATE_ID;//템플릿 아이디
	
	
	
	public String getREG_MBR_ID_ST() {
		return REG_MBR_ID_ST;
	}
	public void setREG_MBR_ID_ST(String rEG_MBR_ID_ST) {
		REG_MBR_ID_ST = rEG_MBR_ID_ST;
	}
	public String getMBR_SCRTY_KEY() {
		return MBR_SCRTY_KEY;
	}
	public void setMBR_SCRTY_KEY(String mBR_SCRTY_KEY) {
		MBR_SCRTY_KEY = mBR_SCRTY_KEY;
	}
	public String getHMBR_FSHRBT_TYPE() {
		return HMBR_FSHRBT_TYPE;
	}
	public void setHMBR_FSHRBT_TYPE(String hMBR_FSHRBT_TYPE) {
		HMBR_FSHRBT_TYPE = hMBR_FSHRBT_TYPE;
	}
	public String getCRS_SV_ID() {
		return CRS_SV_ID;
	}
	public void setCRS_SV_ID(String cRS_SV_ID) {
		CRS_SV_ID = cRS_SV_ID;
	}
	public String getLRNNG_CHECK_ST() {
		return LRNNG_CHECK_ST;
	}
	public void setLRNNG_CHECK_ST(String lRNNG_CHECK_ST) {
		LRNNG_CHECK_ST = lRNNG_CHECK_ST;
	}
	public String getHMBR_SV_ST() {
		return HMBR_SV_ST;
	}
	public void setHMBR_SV_ST(String hMBR_SV_ST) {
		HMBR_SV_ST = hMBR_SV_ST;
	}
	public String getHMBR_SV_DT() {
		return HMBR_SV_DT;
	}
	public void setHMBR_SV_DT(String hMBR_SV_DT) {
		HMBR_SV_DT = hMBR_SV_DT;
	}
	public String getHMBR_BUSINESS_NUM() {
		return HMBR_BUSINESS_NUM;
	}
	public void setHMBR_BUSINESS_NUM(String hMBR_BUSINESS_NUM) {
		HMBR_BUSINESS_NUM = hMBR_BUSINESS_NUM;
	}
	public String getMBR_DSCRP() {
		return MBR_DSCRP;
	}
	public void setMBR_DSCRP(String mBR_DSCRP) {
		MBR_DSCRP = mBR_DSCRP;
	}
	public String getHMBR_FSHLC_WORK_CD() {
		return HMBR_FSHLC_WORK_CD;
	}
	public void setHMBR_FSHLC_WORK_CD(String hMBR_FSHLC_WORK_CD) {
		HMBR_FSHLC_WORK_CD = hMBR_FSHLC_WORK_CD;
	}
	public String getHMBR_SIDO_CD() {
		return HMBR_SIDO_CD;
	}
	public void setHMBR_SIDO_CD(String hMBR_SIDO_CD) {
		HMBR_SIDO_CD = hMBR_SIDO_CD;
	}
	public String getHMBR_SIGNGU_CD() {
		return HMBR_SIGNGU_CD;
	}
	public void setHMBR_SIGNGU_CD(String hMBR_SIGNGU_CD) {
		HMBR_SIGNGU_CD = hMBR_SIGNGU_CD;
	}
	public String getHMBR_MBR_TRGT_CD() {
		return HMBR_MBR_TRGT_CD;
	}
	public void setHMBR_MBR_TRGT_CD(String hMBR_MBR_TRGT_CD) {
		HMBR_MBR_TRGT_CD = hMBR_MBR_TRGT_CD;
	}
	public String getHMBR_YMD_NM() {
		return HMBR_YMD_NM;
	}
	public void setHMBR_YMD_NM(String hMBR_YMD_NM) {
		HMBR_YMD_NM = hMBR_YMD_NM;
	}
	public String getMBR_ADDR_MERGE() {
		return MBR_ADDR_MERGE;
	}
	public void setMBR_ADDR_MERGE(String mBR_ADDR_MERGE) {
		MBR_ADDR_MERGE = mBR_ADDR_MERGE;
	}
	public String getMBR_TEL() {
		return MBR_TEL;
	}
	public void setMBR_TEL(String mBR_TEL) {
		MBR_TEL = mBR_TEL;
	}
	public String getMBR_REG_TYPE_CD() {
		return MBR_REG_TYPE_CD;
	}
	public void setMBR_REG_TYPE_CD(String mBR_REG_TYPE_CD) {
		MBR_REG_TYPE_CD = mBR_REG_TYPE_CD;
	}
	public String getHMBR_DTL_NM() {
		return HMBR_DTL_NM;
	}
	public void setHMBR_DTL_NM(String hMBR_DTL_NM) {
		HMBR_DTL_NM = hMBR_DTL_NM;
	}
	public String getHMBR_REG_NUM_CD() {
		return HMBR_REG_NUM_CD;
	}
	public void setHMBR_REG_NUM_CD(String hMBR_REG_NUM_CD) {
		HMBR_REG_NUM_CD = hMBR_REG_NUM_CD;
	}
	public String getHMBR_SHIP_LICENSE_STR_DT() {
		return HMBR_SHIP_LICENSE_STR_DT;
	}
	public void setHMBR_SHIP_LICENSE_STR_DT(String hMBR_SHIP_LICENSE_STR_DT) {
		HMBR_SHIP_LICENSE_STR_DT = hMBR_SHIP_LICENSE_STR_DT;
	}
	public String getHMBR_SHIP_LICENSE_END_DT() {
		return HMBR_SHIP_LICENSE_END_DT;
	}
	public void setHMBR_SHIP_LICENSE_END_DT(String hMBR_SHIP_LICENSE_END_DT) {
		HMBR_SHIP_LICENSE_END_DT = hMBR_SHIP_LICENSE_END_DT;
	}
	public String getHMBR_SHIP_LICENSE() {
		return HMBR_SHIP_LICENSE;
	}
	public void setHMBR_SHIP_LICENSE(String hMBR_SHIP_LICENSE) {
		HMBR_SHIP_LICENSE = hMBR_SHIP_LICENSE;
	}
	public String getHMBR_DTL_ADDR() {
		return HMBR_DTL_ADDR;
	}
	public void setHMBR_DTL_ADDR(String hMBR_DTL_ADDR) {
		HMBR_DTL_ADDR = hMBR_DTL_ADDR;
	}
	public String getHMBR_FSHLC_APPLC() {
		return HMBR_FSHLC_APPLC;
	}
	public void setHMBR_FSHLC_APPLC(String hMBR_FSHLC_APPLC) {
		HMBR_FSHLC_APPLC = hMBR_FSHLC_APPLC;
	}
	public String getHMBR_SHIP_CD() {
		return HMBR_SHIP_CD;
	}
	public void setHMBR_SHIP_CD(String hMBR_SHIP_CD) {
		HMBR_SHIP_CD = hMBR_SHIP_CD;
	}
	public String getHMBR_SHIP_GRTG() {
		return HMBR_SHIP_GRTG;
	}
	public void setHMBR_SHIP_GRTG(String hMBR_SHIP_GRTG) {
		HMBR_SHIP_GRTG = hMBR_SHIP_GRTG;
	}
	public String getHMBR_SHIP_PRLOAD() {
		return HMBR_SHIP_PRLOAD;
	}
	public void setHMBR_SHIP_PRLOAD(String hMBR_SHIP_PRLOAD) {
		HMBR_SHIP_PRLOAD = hMBR_SHIP_PRLOAD;
	}
	public String getHMBR_SHIP_MAX_PSNGER() {
		return HMBR_SHIP_MAX_PSNGER;
	}
	public void setHMBR_SHIP_MAX_PSNGER(String hMBR_SHIP_MAX_PSNGER) {
		HMBR_SHIP_MAX_PSNGER = hMBR_SHIP_MAX_PSNGER;
	}
	public String getHMBR_SHIP_MAX_CREW() {
		return HMBR_SHIP_MAX_CREW;
	}
	public void setHMBR_SHIP_MAX_CREW(String hMBR_SHIP_MAX_CREW) {
		HMBR_SHIP_MAX_CREW = hMBR_SHIP_MAX_CREW;
	}
	public String getHMBR_DTL_LICENSE_CD() {
		return HMBR_DTL_LICENSE_CD;
	}
	public void setHMBR_DTL_LICENSE_CD(String hMBR_DTL_LICENSE_CD) {
		HMBR_DTL_LICENSE_CD = hMBR_DTL_LICENSE_CD;
	}
	public String getLRNNG_CMPLT_DT() {
		return LRNNG_CMPLT_DT;
	}
	public void setLRNNG_CMPLT_DT(String lRNNG_CMPLT_DT) {
		LRNNG_CMPLT_DT = lRNNG_CMPLT_DT;
	}
	public String getLRNNG_CMPLT_MBR_ID() {
		return LRNNG_CMPLT_MBR_ID;
	}
	public void setLRNNG_CMPLT_MBR_ID(String lRNNG_CMPLT_MBR_ID) {
		LRNNG_CMPLT_MBR_ID = lRNNG_CMPLT_MBR_ID;
	}
	public String getMBR_SEX() {
		return MBR_SEX;
	}
	public void setMBR_SEX(String mBR_SEX) {
		MBR_SEX = mBR_SEX;
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
	public String getMBR_BIRTH() {
		return MBR_BIRTH;
	}
	public void setMBR_BIRTH(String mBR_BIRTH) {
		MBR_BIRTH = mBR_BIRTH;
	}
	public String getHMBR_MBR_TYPE() {
		return HMBR_MBR_TYPE;
	}
	public void setHMBR_MBR_TYPE(String hMBR_MBR_TYPE) {
		HMBR_MBR_TYPE = hMBR_MBR_TYPE;
	}
	public String getTMP_MBR_NM() {
		return TMP_MBR_NM;
	}
	public void setTMP_MBR_NM(String tMP_MBR_NM) {
		TMP_MBR_NM = tMP_MBR_NM;
	}
	public String getMBR_NCNM() {
		return MBR_NCNM;
	}
	public void setMBR_NCNM(String mBR_NCNM) {
		MBR_NCNM = mBR_NCNM;
	}
	public String getTMP_MBR_HP() {
		return TMP_MBR_HP;
	}
	public void setTMP_MBR_HP(String tMP_MBR_HP) {
		TMP_MBR_HP = tMP_MBR_HP;
	}
	public String getTMP_MBR_BIRTH() {
		return TMP_MBR_BIRTH;
	}
	public void setTMP_MBR_BIRTH(String tMP_MBR_BIRTH) {
		TMP_MBR_BIRTH = tMP_MBR_BIRTH;
	}
	public String getCAT_VISIT_SN() {
		return CAT_VISIT_SN;
	}
	public void setCAT_VISIT_SN(String cAT_VISIT_SN) {
		CAT_VISIT_SN = cAT_VISIT_SN;
	}
	public String getHOPE_AREA() {
		return HOPE_AREA;
	}
	public void setHOPE_AREA(String hOPE_AREA) {
		HOPE_AREA = hOPE_AREA;
	}
	public String getHOPE_INDST() {
		return HOPE_INDST;
	}
	public void setHOPE_INDST(String hOPE_INDST) {
		HOPE_INDST = hOPE_INDST;
	}
	public String getHMBR_ORD() {
		return HMBR_ORD;
	}
	public void setHMBR_ORD(String hMBR_ORD) {
		HMBR_ORD = hMBR_ORD;
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
	public String getCAT_INS_NM() {
		return CAT_INS_NM;
	}
	public void setCAT_INS_NM(String cAT_INS_NM) {
		CAT_INS_NM = cAT_INS_NM;
	}
	public String getCRS_ST() {
		return CRS_ST;
	}
	public void setCRS_ST(String cRS_ST) {
		CRS_ST = cRS_ST;
	}
	public String getLOCK_ST() {
		return LOCK_ST;
	}
	public void setLOCK_ST(String lOCK_ST) {
		LOCK_ST = lOCK_ST;
	}
	public String getCRS_TYPE() {
		return CRS_TYPE;
	}
	public void setCRS_TYPE(String cRS_TYPE) {
		CRS_TYPE = cRS_TYPE;
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
	public String getCRS_NM() {
		return CRS_NM;
	}
	public void setCRS_NM(String cRS_NM) {
		CRS_NM = cRS_NM;
	}
	public String getHMBR_DTL_CNT() {
		return HMBR_DTL_CNT;
	}
	public void setHMBR_DTL_CNT(String hMBR_DTL_CNT) {
		HMBR_DTL_CNT = hMBR_DTL_CNT;
	}
	public String getLRNNG_CMPLT_CNT() {
		return LRNNG_CMPLT_CNT;
	}
	public void setLRNNG_CMPLT_CNT(String lRNNG_CMPLT_CNT) {
		LRNNG_CMPLT_CNT = lRNNG_CMPLT_CNT;
	}
	public String getTRN_CUR_TIME() {
		return TRN_CUR_TIME;
	}
	public void setTRN_CUR_TIME(String tRN_CUR_TIME) {
		TRN_CUR_TIME = tRN_CUR_TIME;
	}
	public String getTRN_MAX_TIME() {
		return TRN_MAX_TIME;
	}
	public void setTRN_MAX_TIME(String tRN_MAX_TIME) {
		TRN_MAX_TIME = tRN_MAX_TIME;
	}
	public String getTRN_NM() {
		return TRN_NM;
	}
	public void setTRN_NM(String tRN_NM) {
		TRN_NM = tRN_NM;
	}
	public String getTRN_TYPE_ST() {
		return TRN_TYPE_ST;
	}
	public void setTRN_TYPE_ST(String tRN_TYPE_ST) {
		TRN_TYPE_ST = tRN_TYPE_ST;
	}
	public String getCRS_DTL_NM() {
		return CRS_DTL_NM;
	}
	public void setCRS_DTL_NM(String cRS_DTL_NM) {
		CRS_DTL_NM = cRS_DTL_NM;
	}
	public String getTRN_SN() {
		return TRN_SN;
	}
	public void setTRN_SN(String tRN_SN) {
		TRN_SN = tRN_SN;
	}
	public String getHMBR_SN() {
		return HMBR_SN;
	}
	public void setHMBR_SN(String hMBR_SN) {
		HMBR_SN = hMBR_SN;
	}
	public String getPURCHASE_CMPLT_ST() {
		return PURCHASE_CMPLT_ST;
	}
	public void setPURCHASE_CMPLT_ST(String pURCHASE_CMPLT_ST) {
		PURCHASE_CMPLT_ST = pURCHASE_CMPLT_ST;
	}
	public String getPURCHASE_CD() {
		return PURCHASE_CD;
	}
	public void setPURCHASE_CD(String pURCHASE_CD) {
		PURCHASE_CD = pURCHASE_CD;
	}
	public String getLRNNG_ST() {
		return LRNNG_ST;
	}
	public void setLRNNG_ST(String lRNNG_ST) {
		LRNNG_ST = lRNNG_ST;
	}
	public String getLRNNG_TOT_TIME() {
		return LRNNG_TOT_TIME;
	}
	public void setLRNNG_TOT_TIME(String lRNNG_TOT_TIME) {
		LRNNG_TOT_TIME = lRNNG_TOT_TIME;
	}
	public String getLRNNG_TOT_POINT() {
		return LRNNG_TOT_POINT;
	}
	public void setLRNNG_TOT_POINT(String lRNNG_TOT_POINT) {
		LRNNG_TOT_POINT = lRNNG_TOT_POINT;
	}
	public String getHMBR_INPUT_TIME() {
		return HMBR_INPUT_TIME;
	}
	public void setHMBR_INPUT_TIME(String hMBR_INPUT_TIME) {
		HMBR_INPUT_TIME = hMBR_INPUT_TIME;
	}
	public String getHMBR_INPUT_POINT() {
		return HMBR_INPUT_POINT;
	}
	public void setHMBR_INPUT_POINT(String hMBR_INPUT_POINT) {
		HMBR_INPUT_POINT = hMBR_INPUT_POINT;
	}
	public String getHMBR_MAX_TIME() {
		return HMBR_MAX_TIME;
	}
	public void setHMBR_MAX_TIME(String hMBR_MAX_TIME) {
		HMBR_MAX_TIME = hMBR_MAX_TIME;
	}
	public String getHMBR_MAX_POINT() {
		return HMBR_MAX_POINT;
	}
	public void setHMBR_MAX_POINT(String hMBR_MAX_POINT) {
		HMBR_MAX_POINT = hMBR_MAX_POINT;
	}
	public String getHMBR_RCGNT_TIME() {
		return HMBR_RCGNT_TIME;
	}
	public void setHMBR_RCGNT_TIME(String hMBR_RCGNT_TIME) {
		HMBR_RCGNT_TIME = hMBR_RCGNT_TIME;
	}
	public String getHMBR_RCGNT_POINT() {
		return HMBR_RCGNT_POINT;
	}
	public void setHMBR_RCGNT_POINT(String hMBR_RCGNT_POINT) {
		HMBR_RCGNT_POINT = hMBR_RCGNT_POINT;
	}
	public String getHMBR_DTL_SN() {
		return HMBR_DTL_SN;
	}
	public void setHMBR_DTL_SN(String hMBR_DTL_SN) {
		HMBR_DTL_SN = hMBR_DTL_SN;
	}
	public String getCRS_DTL_SN() {
		return CRS_DTL_SN;
	}
	public void setCRS_DTL_SN(String cRS_DTL_SN) {
		CRS_DTL_SN = cRS_DTL_SN;
	}
	public String getLRNNG_CUR_TIME() {
		return LRNNG_CUR_TIME;
	}
	public void setLRNNG_CUR_TIME(String lRNNG_CUR_TIME) {
		LRNNG_CUR_TIME = lRNNG_CUR_TIME;
	}
	public String getLRNNG_MAX_TIME() {
		return LRNNG_MAX_TIME;
	}
	public void setLRNNG_MAX_TIME(String lRNNG_MAX_TIME) {
		LRNNG_MAX_TIME = lRNNG_MAX_TIME;
	}
	public String getLRNNG_CUR_POINT() {
		return LRNNG_CUR_POINT;
	}
	public void setLRNNG_CUR_POINT(String lRNNG_CUR_POINT) {
		LRNNG_CUR_POINT = lRNNG_CUR_POINT;
	}
	public String getLRNNG_MAX_POINT() {
		return LRNNG_MAX_POINT;
	}
	public void setLRNNG_MAX_POINT(String lRNNG_MAX_POINT) {
		LRNNG_MAX_POINT = lRNNG_MAX_POINT;
	}
	public String getLRNNG_CMPLT_DTL_ST() {
		return LRNNG_CMPLT_DTL_ST;
	}
	public void setLRNNG_CMPLT_DTL_ST(String lRNNG_CMPLT_DTL_ST) {
		LRNNG_CMPLT_DTL_ST = lRNNG_CMPLT_DTL_ST;
	}
	public String getRCGNT_TIME() {
		return RCGNT_TIME;
	}
	public void setRCGNT_TIME(String rCGNT_TIME) {
		RCGNT_TIME = rCGNT_TIME;
	}
	public String getRCGNT_POINT() {
		return RCGNT_POINT;
	}
	public void setRCGNT_POINT(String rCGNT_POINT) {
		RCGNT_POINT = rCGNT_POINT;
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
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getCRS_SN() {
		return CRS_SN;
	}
	public void setCRS_SN(String cRS_SN) {
		CRS_SN = cRS_SN;
	}
	public String getLRNNG_PRGRS() {
		return LRNNG_PRGRS;
	}
	public void setLRNNG_PRGRS(String lRNNG_PRGRS) {
		LRNNG_PRGRS = lRNNG_PRGRS;
	}
	public String getLRNNG_CMPLT_ST() {
		return LRNNG_CMPLT_ST;
	}
	public void setLRNNG_CMPLT_ST(String lRNNG_CMPLT_ST) {
		LRNNG_CMPLT_ST = lRNNG_CMPLT_ST;
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
	public String getDEL_ST_CHK() {
		return DEL_ST_CHK;
	}
	public void setDEL_ST_CHK(String dEL_ST_CHK) {
		DEL_ST_CHK = dEL_ST_CHK;
	}
	public String getUSE_ST_CHK() {
		return USE_ST_CHK;
	}
	public void setUSE_ST_CHK(String uSE_ST_CHK) {
		USE_ST_CHK = uSE_ST_CHK;
	}
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMBR_HP() {
		return MBR_HP;
	}
	public void setMBR_HP(String mBR_HP) {
		MBR_HP = mBR_HP;
	}
	public String getMBR_IDS() {
		return MBR_IDS;
	}
	public void setMBR_IDS(String mBR_IDS) {
		MBR_IDS = mBR_IDS;
	}
	public String getTEMPLATE_ID() {
		return TEMPLATE_ID;
	}
	public void setTEMPLATE_ID(String tEMPLATE_ID) {
		TEMPLATE_ID = tEMPLATE_ID;
	}
	public String getTMPR_LRNNG_CMPLT_DT() {
		return TMPR_LRNNG_CMPLT_DT;
	}
	public void setTMPR_LRNNG_CMPLT_DT(String tMPR_LRNNG_CMPLT_DT) {
		TMPR_LRNNG_CMPLT_DT = tMPR_LRNNG_CMPLT_DT;
	}
	
	
}
