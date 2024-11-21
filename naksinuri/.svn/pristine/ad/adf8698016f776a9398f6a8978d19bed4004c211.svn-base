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
package egovframework.eduadm.certificate.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [인증서 관리 및 발급 관련]
 * 테이블명 : EDU_CRTF_TB , EDU_CRTF_DTL_TB
 */
public class EduCertificateVO extends EduDefaultVO {
	
	//유니크 코드 생성
	public String getUniqKey() {
		Date today = new Date();
	    SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMdd");
	    SimpleDateFormat date2 = new SimpleDateFormat("HHmmss");
	    return date1.format(today) + getRandomString(4) + date2.format(today);
	}
	private String getRandomString(int length) {
	    StringBuffer buffer = new StringBuffer();
	    Random random = new Random();
	    random.setSeed(System.nanoTime());
	    String[] chars = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
	    for (int i = 0; i < length; i++) {
	      buffer.append(chars[random.nextInt(chars.length)]);
	    }
	    return buffer.toString();
	}
	
	//메인
	private String CRTF_FILE_SN;//varchar(50) NULL발급된인증서파일
	private String CRTF_HTML_DATA;//text NULL발급된인증서데이터파일
	
	//상세
	private String CRTF_DTL_SN;//int(11) NOT NULL인증서발급번호
	private String CRTF_TYPE_ST;//varchar(250) NULL발급용도
	private String CRTF_CMPLT_ST;//int(3) NULL0:사용전,1:발급완료(사용후)
	
	//공통
	private String CRTF_SN;//int(11) NULL인증서번호
	private String CRTF_CD;//varchar(250) NULL인증서코드
	private String CRS_SN;//int(11) NULL교육정보번호(커리큘럼)
	private String HMBR_SN;//int(11) NULL인증서대상(이수내역번호)
	private String MBR_ID;//varchar(50) NULL회원아이디
	private String REG_MBR_ID;//varchar(50) NULL인증서생성아이디
	private String UPD_MBR_ID;//varchar(50) NULL인증서변경아이디
	private String REG_DT;//datetime NULL인증서발급일자
	private String UPD_DT;//varchar(50) NULL인증서발급아이디
	private String USE_ST;//int(3) NULL0:사용안함,1:사용함
	private String DEL_ST;//int(3) NULL0:삭제안함,1:삭제함
	private String CRS_GRP_CD;//varchar(20) NULL교육과정그룹코드
	private String CAT_INS_SN;//varchar(50) NULL교육기관
	
	//디비 외
	private String MBR_NM;//회원이름
	private String MBR_NCNM;//회원 닉네임
	private String MBR_HP;//회원연락처
	private String MBR_EMAIL;//회원이메일
	private String MBR_BIRTH;//회원생년월일
	private String CRTF_DTL_CNT;//회원당발급내역수
	private String CRS_NM;//교육정보명
	private String CRS_TYPE;//교육분류
	private String USE_ST_CHK;//사용유무체크박스
	private String CRS_MBR_CD;//교육대상분류코드(낚시터업자,낚시어선업자 등)
	private String CRS_STR_DT;//교육일자(시작)
	private String CRS_END_DT;//교육일자(종료)
	private String CRS_STR_DT_YEAR;//교육일자(시작) 연도
	private String LRNNG_CMPLT_ST; //이수여부 0:미이수, 1:이수, 2:이수취소ㄲ
	
	private String MBR_SIDO_CD;
	private String MBR_SIGNGU_CD;
	
	private String TMP_MBR_NM;//varchar(25) NULL비회원 이름
	private String TMP_MBR_HP;//varchar(25) NULL비회원 연락처
	private String TMP_MBR_BIRTH;//varchar(10) NULL비회원 생년월일
	
	/** 넘버 라벨링  : 공통으로 빼야 하나, 엑셀 추출시 EgovMap 타입에서 읽지 못해 어쩔수없이 선언함. */
	private String ROW_NUM;
	public String getROW_NUM() {
		return ROW_NUM;
	}
	public void setROW_NUM(String rOW_NUM) {
		ROW_NUM = rOW_NUM;
	}
	
	private String CRTF_SNs;//인증서번호 n개이상
	private String CRS_SNs;//교육정보번호(커리큘럼) n개이상
	private String HMBR_SNs;//인증서대상(이수내역번호) n개이상
	private String MBR_IDs;//회원아이디 n개이상
	
	
	public String getMBR_NCNM() {
		return MBR_NCNM;
	}
	public void setMBR_NCNM(String mBR_NCNM) {
		MBR_NCNM = mBR_NCNM;
	}
	public String getCRTF_SNs() {
		return CRTF_SNs;
	}
	public void setCRTF_SNs(String cRTF_SNs) {
		CRTF_SNs = cRTF_SNs;
	}
	public String getCRS_SNs() {
		return CRS_SNs;
	}
	public void setCRS_SNs(String cRS_SNs) {
		CRS_SNs = cRS_SNs;
	}
	public String getHMBR_SNs() {
		return HMBR_SNs;
	}
	public void setHMBR_SNs(String hMBR_SNs) {
		HMBR_SNs = hMBR_SNs;
	}
	public String getMBR_IDs() {
		return MBR_IDs;
	}
	public void setMBR_IDs(String mBR_IDs) {
		MBR_IDs = mBR_IDs;
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
	public String getCRS_STR_DT_YEAR() {
		return CRS_STR_DT_YEAR;
	}
	public void setCRS_STR_DT_YEAR(String cRS_STR_DT_YEAR) {
		CRS_STR_DT_YEAR = cRS_STR_DT_YEAR;
	}
	public String getMBR_BIRTH() {
		return MBR_BIRTH;
	}
	public void setMBR_BIRTH(String mBR_BIRTH) {
		MBR_BIRTH = mBR_BIRTH;
	}
	public String getCRS_MBR_CD() {
		return CRS_MBR_CD;
	}
	public void setCRS_MBR_CD(String cRS_MBR_CD) {
		CRS_MBR_CD = cRS_MBR_CD;
	}
	public String getCAT_INS_SN() {
		return CAT_INS_SN;
	}
	public void setCAT_INS_SN(String cAT_INS_SN) {
		CAT_INS_SN = cAT_INS_SN;
	}
	public String getCRS_GRP_CD() {
		return CRS_GRP_CD;
	}
	public void setCRS_GRP_CD(String cRS_GRP_CD) {
		CRS_GRP_CD = cRS_GRP_CD;
	}
	public String getTMP_MBR_NM() {
		return TMP_MBR_NM;
	}
	public void setTMP_MBR_NM(String tMP_MBR_NM) {
		TMP_MBR_NM = tMP_MBR_NM;
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
	public String getCRTF_TYPE_ST() {
		return CRTF_TYPE_ST;
	}
	public void setCRTF_TYPE_ST(String cRTF_TYPE_ST) {
		CRTF_TYPE_ST = cRTF_TYPE_ST;
	}
	public String getCRTF_CMPLT_ST() {
		return CRTF_CMPLT_ST;
	}
	public void setCRTF_CMPLT_ST(String cRTF_CMPLT_ST) {
		CRTF_CMPLT_ST = cRTF_CMPLT_ST;
	}
	public String getCRTF_HTML_DATA() {
		return CRTF_HTML_DATA;
	}
	public void setCRTF_HTML_DATA(String cRTF_HTML_DATA) {
		CRTF_HTML_DATA = cRTF_HTML_DATA;
	}
	public String getUSE_ST_CHK() {
		return USE_ST_CHK;
	}
	public void setUSE_ST_CHK(String uSE_ST_CHK) {
		USE_ST_CHK = uSE_ST_CHK;
	}
	public String getCRS_TYPE() {
		return CRS_TYPE;
	}
	public void setCRS_TYPE(String cRS_TYPE) {
		CRS_TYPE = cRS_TYPE;
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
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getCRTF_DTL_CNT() {
		return CRTF_DTL_CNT;
	}
	public void setCRTF_DTL_CNT(String cRTF_DTL_CNT) {
		CRTF_DTL_CNT = cRTF_DTL_CNT;
	}
	public String getCRS_NM() {
		return CRS_NM;
	}
	public void setCRS_NM(String cRS_NM) {
		CRS_NM = cRS_NM;
	}
	public String getCRTF_FILE_SN() {
		return CRTF_FILE_SN;
	}
	public void setCRTF_FILE_SN(String cRTF_FILE_SN) {
		CRTF_FILE_SN = cRTF_FILE_SN;
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
	public String getCRTF_DTL_SN() {
		return CRTF_DTL_SN;
	}
	public void setCRTF_DTL_SN(String cRTF_DTL_SN) {
		CRTF_DTL_SN = cRTF_DTL_SN;
	}
	public String getCRTF_SN() {
		return CRTF_SN;
	}
	public void setCRTF_SN(String cRTF_SN) {
		CRTF_SN = cRTF_SN;
	}
	public String getCRTF_CD() {
		return CRTF_CD;
	}
	public void setCRTF_CD(String cRTF_CD) {
		CRTF_CD = cRTF_CD;
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
	
	public String getLRNNG_CMPLT_ST() {
		return LRNNG_CMPLT_ST;
	}
	public void setLRNNG_CMPLT_ST(String lRNNG_CMPLT_ST) {
		LRNNG_CMPLT_ST = lRNNG_CMPLT_ST;
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
		
}
