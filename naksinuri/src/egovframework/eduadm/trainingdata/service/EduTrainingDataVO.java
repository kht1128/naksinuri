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
package egovframework.eduadm.trainingdata.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [교육자료]
 * 테이블명 : EDU_TRNNG_DATA_TB
 */
public class EduTrainingDataVO extends EduDefaultVO {
	//교육자료
	private String TRN_SN;//int(11) NOT NULL교육자료번호
	private String TRN_NM;//varchar(250) NOT NULL교육자료명칭
	private String TRN_DSCRP;//varchar(250) NULL설명
	private String TRN_TYPE_ST;//varchar(50) NOT NULLvideo,etc,hwp,pdf ...
	private String CAT_SN;//int(11) NOT NULL교육카테고리1
	private String CAT_DTL_SN;//int(11) NOT NULL교육카테고리2
	private String REG_DT;//datetime NULL작성일자
	private String UPD_DT;//datetime NULL수정일자
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String USE_ST;//int(3) NULL0:사용안함,1:사용함
	private String DEL_ST;//int(3) NULL0:삭제안함,1:삭제함
	private String TRN_FILE_SN;//varchar(50) NULL첨부파일번호
	private String TRN_MAX_TIME;//int(11) NULL첨부자료(교육시간)
	//디비외
	private String USE_ST_CHK;//사용유무체크박스
	private String CAT_NM;//교육카테고리1
	private String CAT_DTL_NM;//교육카테고리2
	private String CAT_SN_CHNG;//교육카테고리1변경값
	private String CAT_DTL_SN_CHNG;//교육카테고리2변경값
	private String LRNNG_GB;//교육카테고리2상태값(online,offline)
	
	public String getTRN_MAX_TIME() {
		return TRN_MAX_TIME;
	}
	public void setTRN_MAX_TIME(String tRN_MAX_TIME) {
		TRN_MAX_TIME = tRN_MAX_TIME;
	}
	public String getLRNNG_GB() {
		return LRNNG_GB;
	}
	public void setLRNNG_GB(String lRNNG_GB) {
		LRNNG_GB = lRNNG_GB;
	}
	public String getCAT_SN_CHNG() {
		return CAT_SN_CHNG;
	}
	public void setCAT_SN_CHNG(String cAT_SN_CHNG) {
		CAT_SN_CHNG = cAT_SN_CHNG;
	}
	public String getCAT_DTL_SN_CHNG() {
		return CAT_DTL_SN_CHNG;
	}
	public void setCAT_DTL_SN_CHNG(String cAT_DTL_SN_CHNG) {
		CAT_DTL_SN_CHNG = cAT_DTL_SN_CHNG;
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
	public String getTRN_FILE_SN() {
		return TRN_FILE_SN;
	}
	public void setTRN_FILE_SN(String tRN_FILE_SN) {
		TRN_FILE_SN = tRN_FILE_SN;
	}
	public String getTRN_SN() {
		return TRN_SN;
	}
	public void setTRN_SN(String tRN_SN) {
		TRN_SN = tRN_SN;
	}
	public String getTRN_NM() {
		return TRN_NM;
	}
	public void setTRN_NM(String tRN_NM) {
		TRN_NM = tRN_NM;
	}
	public String getTRN_DSCRP() {
		return TRN_DSCRP;
	}
	public void setTRN_DSCRP(String tRN_DSCRP) {
		TRN_DSCRP = tRN_DSCRP;
	}
	public String getTRN_TYPE_ST() {
		return TRN_TYPE_ST;
	}
	public void setTRN_TYPE_ST(String tRN_TYPE_ST) {
		TRN_TYPE_ST = tRN_TYPE_ST;
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
	public String getUSE_ST_CHK() {
		return USE_ST_CHK;
	}
	public void setUSE_ST_CHK(String uSE_ST_CHK) {
		USE_ST_CHK = uSE_ST_CHK;
	}	
}
