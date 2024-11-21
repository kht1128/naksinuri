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
package egovframework.all.error.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [에러 기록 테이블]
 * 테이블명 : LOG_ERR_TB
 */
public class ErrorVO extends EduDefaultVO {	
	private String ERR_DT;//datetime NULL로그일자
	private String MBR_ID;//varchar(50) NULL유저아이디
	private String ERR_IP;//varchar(50) NULL접속아이피
	private String 	ERR_STAT_CD;//varchar(50) NULLerror status_code
	private String ERR_EXP_TYPE;//varchar(250) NULLerror exception_type
	private String ERR_EXP_MSG;//text NULLerror message
	private String ERR_REQ_URI;//varchar(250) NULL요청url
	private String ERR_EXP;//text NULLerror exception
	private String ERR_SERVLET_NM;//varchar(250) NULLerror servlet_name	
	private String ERR_TYPE;//varchar(50) NULLerror type	
	
	//디비외
	private String MBR_NM;//이름	
	private String MBR_PSITN_NM;//소속
	private String MBR_NCNM;//호칭
	
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
	public String getERR_TYPE() {
		return ERR_TYPE;
	}
	public void setERR_TYPE(String eRR_TYPE) {
		ERR_TYPE = eRR_TYPE;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getERR_DT() {
		return ERR_DT;
	}
	public void setERR_DT(String eRR_DT) {
		ERR_DT = eRR_DT;
	}
	public String getERR_IP() {
		return ERR_IP;
	}
	public void setERR_IP(String eRR_IP) {
		ERR_IP = eRR_IP;
	}
	public String getERR_STAT_CD() {
		return ERR_STAT_CD;
	}
	public void setERR_STAT_CD(String eRR_STAT_CD) {
		ERR_STAT_CD = eRR_STAT_CD;
	}
	public String getERR_EXP_TYPE() {
		return ERR_EXP_TYPE;
	}
	public void setERR_EXP_TYPE(String eRR_EXP_TYPE) {
		ERR_EXP_TYPE = eRR_EXP_TYPE;
	}
	public String getERR_EXP_MSG() {
		return ERR_EXP_MSG;
	}
	public void setERR_EXP_MSG(String eRR_EXP_MSG) {
		ERR_EXP_MSG = eRR_EXP_MSG;
	}
	public String getERR_REQ_URI() {
		return ERR_REQ_URI;
	}
	public void setERR_REQ_URI(String eRR_REQ_URI) {
		ERR_REQ_URI = eRR_REQ_URI;
	}
	public String getERR_EXP() {
		return ERR_EXP;
	}
	public void setERR_EXP(String eRR_EXP) {
		ERR_EXP = eRR_EXP;
	}
	public String getERR_SERVLET_NM() {
		return ERR_SERVLET_NM;
	}
	public void setERR_SERVLET_NM(String eRR_SERVLET_NM) {
		ERR_SERVLET_NM = eRR_SERVLET_NM;
	}	
}
