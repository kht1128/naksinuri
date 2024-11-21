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
package egovframework.all.log.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.main.service.AdmDefaultVO;

/**
 * [사용자로그기록데이터]
 * 테이블명 : LOG_REC_TB
 */
public class LogRecordVO extends AdmDefaultVO {
	
	//from object to json
	public String encodingFromObjectToJson(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
		return mapper.writeValueAsString(obj).toString();
	}
	
	private String MBR_ID;//varchar(50) NULL유저아이디
	private String TBL_INF;//varchar(250) NULL테이블정보
	private String TBL_SN;//varchar(250) NULL게시물번호
	private String LOG_MSG;//text NULL로그기록
	private String LOG_DSCRP;//text NULL로그설명
	private String REG_DT;//datetime NULL로그일자
	private String MBR_IP;//varchar(50) NULL접속아이피
	private String MBR_LV;//int(11) NULL유저레벨
	private String MBR_MSG;//text NULL사용자기록사유(사용자수기입력시)
	private String LOG_TYPE;//varchar(50) NULL로그분류
	private String REQ_URL;//text NULL요청URL
	
	//디비외
	private String MBR_NM;//이름
	private String MBR_PSITN_NM;//소속
	private String MBR_NCNM;//호칭
	private String ADM_ACCESS_LOG;//관리자 접속기록 분기
	
	// log_edu_tb
	private String CRS_SN; 			// 교육 고유번호
	private String TRN_CUR_TIME;	// 교육진행시간
	private String HMBR_DTL_SN;		// 이수내역상세번호
	private String USER_AGENT;		// clientUserAgent
	private String MBR_MOD_ID;		// 데이터 통합 시 교체될 ID
	
	public String getMBR_MOD_ID() {
		return MBR_MOD_ID;
	}
	public void setMBR_MOD_ID(String mBR_MOD_ID) {
		MBR_MOD_ID = mBR_MOD_ID;
	}
	public String getUSER_AGENT() {
		return USER_AGENT;
	}
	public void setUSER_AGENT(String uSER_AGENT) {
		USER_AGENT = uSER_AGENT;
	}
	public String getTRN_CUR_TIME() {
		return TRN_CUR_TIME;
	}
	public void setTRN_CUR_TIME(String tRN_CUR_TIME) {
		TRN_CUR_TIME = tRN_CUR_TIME;
	}
	public String getHMBR_DTL_SN() {
		return HMBR_DTL_SN;
	}
	public void setHMBR_DTL_SN(String hMBR_DTL_SN) {
		HMBR_DTL_SN = hMBR_DTL_SN;
	}
	public String getCRS_SN() {
		return CRS_SN;
	}
	public void setCRS_SN(String cRS_SN) {
		CRS_SN = cRS_SN;
	}
	public String getADM_ACCESS_LOG() {
		return ADM_ACCESS_LOG;
	}
	public void setADM_ACCESS_LOG(String aDM_ACCESS_LOG) {
		ADM_ACCESS_LOG = aDM_ACCESS_LOG;
	}
	public String getMBR_PSITN_NM() {
		return MBR_PSITN_NM;
	}
	public void setMBR_PSITN_NM(String mBR_PSITN_NM) {
		MBR_PSITN_NM = mBR_PSITN_NM;
	}
	public String getMBR_NCNM() {
		return MBR_NCNM;
	}
	public void setMBR_NCNM(String mBR_NCNM) {
		MBR_NCNM = mBR_NCNM;
	}	
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getREQ_URL() {
		return REQ_URL;
	}
	public void setREQ_URL(String rEQ_URL) {
		REQ_URL = rEQ_URL;
	}
	public String getMBR_MSG() {
		return MBR_MSG;
	}
	public void setMBR_MSG(String mBR_MSG) {
		MBR_MSG = mBR_MSG;
	}
	public String getLOG_TYPE() {
		return LOG_TYPE;
	}
	public void setLOG_TYPE(String lOG_TYPE) {
		LOG_TYPE = lOG_TYPE;
	}
	public String getMBR_IP() {
		return MBR_IP;
	}
	public void setMBR_IP(String mBR_IP) {
		MBR_IP = mBR_IP;
	}
	public String getMBR_LV() {
		return MBR_LV;
	}
	public void setMBR_LV(String mBR_LV) {
		MBR_LV = mBR_LV;
	}
	public String getLOG_DSCRP() {
		return LOG_DSCRP;
	}
	public void setLOG_DSCRP(String lOG_DSCRP) {
		LOG_DSCRP = lOG_DSCRP;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getTBL_INF() {
		return TBL_INF;
	}
	public void setTBL_INF(String tBL_INF) {
		TBL_INF = tBL_INF;
	}
	public String getTBL_SN() {
		return TBL_SN;
	}
	public void setTBL_SN(String tBL_SN) {
		TBL_SN = tBL_SN;
	}
	public String getLOG_MSG() {
		return LOG_MSG;
	}
	public void setLOG_MSG(String lOG_MSG) {
		LOG_MSG = lOG_MSG;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
}
