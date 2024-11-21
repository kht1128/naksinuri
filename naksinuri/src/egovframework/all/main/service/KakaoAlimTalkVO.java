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
package egovframework.all.main.service;

import egovframework.adm.main.service.AdmDefaultVO;

public class KakaoAlimTalkVO extends AdmDefaultVO {
	
	//알림톡
	private String no;//메세지번호(20)
	private String telNum;//받는 이 전화번호(20)
	private String reserveTime;//예약 발송 시 발송 시간(0000-00-00 00:00:00)
	private String customKey;//알림톡,SMS,LMS 성공 실패 여부 확인 시 키 ※ Webhook url을 루나소프트에 선등록 필수
	private String msgContent;//메세지 내용 (최대 1000자)
	private String smsContent;//메세지 내용 (최대 1000자)
	private String useSms;//실패시 sms 전송 여부 (1:전송,0:미전송)
	private String templateId;//템플릿 아이디	
	
	//이수증발급용
	private String complEdycIssuNo;//이수증발급번호
	private String cmpletNm;//성명
	private String cmpletBrdt;//생년월일
	private String cmpletAdres;//주소
	private String eduYmd;//교육일시
	private String eduStrDt;//교육시작일
	private String eduEndDt;//교육종료일
	private String eduNm;//교과과목명
	//교육신청용	
	private String eduUrl1;//동영상수강URL1
	private String eduUrl2;//동영상수강URL2
	private String eduUrl3;//동영상수강URL3
	private String eduUrl4;//동영상수강URL4
	private String eduUrl5;//동영상수강URL5
	private String eduUrl6;//동영상수강URL6
	
	private String surveyUrl; // 설문조사 url
	
	
	//알림톡 발송 동기화 여부
	private boolean isAsync;//true:비동기,false:동기
	
	//일반 문자 발송용
	private boolean isSendWithSMS;//일반문자 동시 발송여부
	private String mberId;//회원아이디
	private String mberIp;//회원접속아이피
	private String mberSendId;//문자발송회원아이디
	private String smsSj;//일반 문자 발송 제목
	private String smsTmplatCd;//문자메시지 템플릿 코드
	private String smsTmplatCn;//문자메세지 템플릿 내용
	
	// DB
	private String NTCN_TRSM_SN;
	private String NTCN_TRSM_GROUP_ID;
	private String NTCN_TRSM_TEL;
	private String NTCN_TRSM_TEMPLATE_ID;
	private String NTCN_TRSM_EDU_CD;
	private String NTCN_TRSM_EDU_URL;
	private String NTCN_TRSM_ASYNC;
	private String TRSM_PROGRS_STTUS;
	private String NTCN_TRSM_RESULT_STTUS;
	private String NTCN_TRSM_CNT;
	private String REG_DT;
	private String REG_ID;
	private String NTCN_TRSM_ERROR_MSG;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public String getCustomKey() {
		return customKey;
	}
	public void setCustomKey(String customKey) {
		this.customKey = customKey;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public String getUseSms() {
		return useSms;
	}
	public void setUseSms(String useSms) {
		this.useSms = useSms;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getComplEdycIssuNo() {
		return complEdycIssuNo;
	}
	public void setComplEdycIssuNo(String complEdycIssuNo) {
		this.complEdycIssuNo = complEdycIssuNo;
	}
	public String getCmpletNm() {
		return cmpletNm;
	}
	public void setCmpletNm(String cmpletNm) {
		this.cmpletNm = cmpletNm;
	}
	public String getCmpletBrdt() {
		return cmpletBrdt;
	}
	public void setCmpletBrdt(String cmpletBrdt) {
		this.cmpletBrdt = cmpletBrdt;
	}
	public String getCmpletAdres() {
		return cmpletAdres;
	}
	public void setCmpletAdres(String cmpletAdres) {
		this.cmpletAdres = cmpletAdres;
	}
	public String getEduYmd() {
		return eduYmd;
	}
	public void setEduYmd(String eduYmd) {
		this.eduYmd = eduYmd;
	}
	public String getEduStrDt() {
		return eduStrDt;
	}
	public void setEduStrDt(String eduStrDt) {
		this.eduStrDt = eduStrDt;
	}
	public String getEduEndDt() {
		return eduEndDt;
	}
	public void setEduEndDt(String eduEndDt) {
		this.eduEndDt = eduEndDt;
	}
	public String getEduNm() {
		return eduNm;
	}
	public void setEduNm(String eduNm) {
		this.eduNm = eduNm;
	}
	public String getEduUrl1() {
		return eduUrl1;
	}
	public void setEduUrl1(String eduUrl1) {
		this.eduUrl1 = eduUrl1;
	}
	public String getEduUrl2() {
		return eduUrl2;
	}
	public void setEduUrl2(String eduUrl2) {
		this.eduUrl2 = eduUrl2;
	}
	public String getEduUrl3() {
		return eduUrl3;
	}
	public void setEduUrl3(String eduUrl3) {
		this.eduUrl3 = eduUrl3;
	}
	public String getEduUrl4() {
		return eduUrl4;
	}
	public void setEduUrl4(String eduUrl4) {
		this.eduUrl4 = eduUrl4;
	}
	public String getEduUrl5() {
		return eduUrl5;
	}
	public void setEduUrl5(String eduUrl5) {
		this.eduUrl5 = eduUrl5;
	}
	public String getEduUrl6() {
		return eduUrl6;
	}
	public void setEduUrl6(String eduUrl6) {
		this.eduUrl6 = eduUrl6;
	}
	public String getSurveyUrl() {
		return surveyUrl;
	}
	public void setSurveyUrl(String surveyUrl) {
		this.surveyUrl = surveyUrl;
	}
	public boolean isAsync() {
		return isAsync;
	}
	public void setAsync(boolean isAsync) {
		this.isAsync = isAsync;
	}
	public boolean isSendWithSMS() {
		return isSendWithSMS;
	}
	public void setSendWithSMS(boolean isSendWithSMS) {
		this.isSendWithSMS = isSendWithSMS;
	}
	public String getMberId() {
		return mberId;
	}
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	public String getMberIp() {
		return mberIp;
	}
	public void setMberIp(String mberIp) {
		this.mberIp = mberIp;
	}
	public String getMberSendId() {
		return mberSendId;
	}
	public void setMberSendId(String mberSendId) {
		this.mberSendId = mberSendId;
	}
	public String getSmsSj() {
		return smsSj;
	}
	public void setSmsSj(String smsSj) {
		this.smsSj = smsSj;
	}
	public String getSmsTmplatCd() {
		return smsTmplatCd;
	}
	public void setSmsTmplatCd(String smsTmplatCd) {
		this.smsTmplatCd = smsTmplatCd;
	}
	public String getSmsTmplatCn() {
		return smsTmplatCn;
	}
	public void setSmsTmplatCn(String smsTmplatCn) {
		this.smsTmplatCn = smsTmplatCn;
	}
	public String getNTCN_TRSM_SN() {
		return NTCN_TRSM_SN;
	}
	public void setNTCN_TRSM_SN(String nTCN_TRSM_SN) {
		NTCN_TRSM_SN = nTCN_TRSM_SN;
	}
	public String getNTCN_TRSM_GROUP_ID() {
		return NTCN_TRSM_GROUP_ID;
	}
	public void setNTCN_TRSM_GROUP_ID(String nTCN_TRSM_GROUP_ID) {
		NTCN_TRSM_GROUP_ID = nTCN_TRSM_GROUP_ID;
	}
	public String getNTCN_TRSM_TEL() {
		return NTCN_TRSM_TEL;
	}
	public void setNTCN_TRSM_TEL(String nTCN_TRSM_TEL) {
		NTCN_TRSM_TEL = nTCN_TRSM_TEL;
	}
	public String getNTCN_TRSM_TEMPLATE_ID() {
		return NTCN_TRSM_TEMPLATE_ID;
	}
	public void setNTCN_TRSM_TEMPLATE_ID(String nTCN_TRSM_TEMPLATE_ID) {
		NTCN_TRSM_TEMPLATE_ID = nTCN_TRSM_TEMPLATE_ID;
	}
	public String getNTCN_TRSM_EDU_CD() {
		return NTCN_TRSM_EDU_CD;
	}
	public void setNTCN_TRSM_EDU_CD(String nTCN_TRSM_EDU_CD) {
		NTCN_TRSM_EDU_CD = nTCN_TRSM_EDU_CD;
	}
	public String getNTCN_TRSM_EDU_URL() {
		return NTCN_TRSM_EDU_URL;
	}
	public void setNTCN_TRSM_EDU_URL(String nTCN_TRSM_EDU_URL) {
		NTCN_TRSM_EDU_URL = nTCN_TRSM_EDU_URL;
	}
	public String getNTCN_TRSM_ASYNC() {
		return NTCN_TRSM_ASYNC;
	}
	public void setNTCN_TRSM_ASYNC(String nTCN_TRSM_ASYNC) {
		NTCN_TRSM_ASYNC = nTCN_TRSM_ASYNC;
	}
	public String getTRSM_PROGRS_STTUS() {
		return TRSM_PROGRS_STTUS;
	}
	public void setTRSM_PROGRS_STTUS(String tRSM_PROGRS_STTUS) {
		TRSM_PROGRS_STTUS = tRSM_PROGRS_STTUS;
	}
	public String getNTCN_TRSM_RESULT_STTUS() {
		return NTCN_TRSM_RESULT_STTUS;
	}
	public void setNTCN_TRSM_RESULT_STTUS(String nTCN_TRSM_RESULT_STTUS) {
		NTCN_TRSM_RESULT_STTUS = nTCN_TRSM_RESULT_STTUS;
	}
	public String getNTCN_TRSM_CNT() {
		return NTCN_TRSM_CNT;
	}
	public void setNTCN_TRSM_CNT(String nTCN_TRSM_CNT) {
		NTCN_TRSM_CNT = nTCN_TRSM_CNT;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getREG_ID() {
		return REG_ID;
	}
	public void setREG_ID(String rEG_ID) {
		REG_ID = rEG_ID;
	}
	public String getNTCN_TRSM_ERROR_MSG() {
		return NTCN_TRSM_ERROR_MSG;
	}
	public void setNTCN_TRSM_ERROR_MSG(String nTCN_TRSM_ERROR_MSG) {
		NTCN_TRSM_ERROR_MSG = nTCN_TRSM_ERROR_MSG;
	}
}
