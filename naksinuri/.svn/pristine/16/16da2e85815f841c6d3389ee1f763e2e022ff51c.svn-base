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
package egovframework.adm.member.service;

import java.util.Arrays;

import egovframework.adm.main.service.AdmDefaultVO;

/**
 * [멤버테이블]
 * 테이블명 : MBR_TB
 */
public class AdmMemberVO extends AdmDefaultVO {
	private String MBR_SN;//bigint(20) NOT NULL일련번호
	private String MBR_ID;//varchar(20) NULL
	private String MBR_PWD;//varchar(50) NULL
	private String MBR_NM;//varchar(50) NULL이름
	private String MBR_NICKNM;//varchar(50) NULL닉네임
	private String MBR_TEL;//varchar(20) NULL전화번호
	private String MBR_HP;//varchar(14) NULL휴대폰
	private String MBR_EMAIL;//varchar(40) NULL이메일
	private String MBR_ADDR1;//varchar(100) NULL주소1
	private String MBR_ADDR2;//varchar(100) NULL주소2
	private String MBR_ZIPCD;//varchar(7) NULL우편번호
	private String MBR_LAST_CON_TM;//datetime NULL접속 시간
	private String MBR_LAST_CON_IPADDR;//varchar(30) NULL접속 아이피
	private String MBR_REG_DT;//datetime NULL가입일
	private String MBR_MOD_DT;//datetime NULL수정일
	private String MBR_POSITION_CD;//varchar(10) NULL직급코드
	private String MBR_TYPE_CD;//varchar(5) NULL유형코드
	private String MBR_GRP_ID;//int(11) NULL그룹아이디(0:전체,1:종합센터,2:교육센터,3:박람회,4:cti)
	private String MBR_BIRTH;//varchar(8) NULL생년월일
	private String MBR_SEX;//int(4) NULL1:남, 2:여
	private String MBR_HOPE_ZONE;//varchar(256) NULL희망지역
	private String MBR_HOPE_BUSINESS;//varchar(256) NULL희망업종
	private String MBR_ST;//enum('Y','N') NULLY:사용, N:삭제
	private String MBR_LV_ID;//int(11) NULL권한레벨
	private String MBR_GRP_1_ST;//enum('Y','N') NULL종합센터 권한허용여부
	private String MBR_GRP_2_ST;//enum('Y','N') NULL교육센터 권한허용여부
	private String MBR_GRP_3_ST;//enum('Y','N') NULL박랍회 권한허용여부
	private String MBR_GRP_4_ST;//enum('Y','N') NULLCTI 권한허용여부
	private String MBR_HMBR_ONLINE_SN;//char(30) NULL온라인교육코드
	private String MBR_HMBR_ONLINE_ST;//enum('Y','N') NULL온라인교육이수여부
	private String MBR_INDVDL_INFO_ST;//개인정보동의서(선택) Y:동의, N:미동의, 0:값없음
	private String MBR_PLEDGE_ST;//관리자 서약서 동의여부
	private String CONECT_POSBL_IP;//varchar(400) NULL접속 가능 아이피
	private String CONECT_POSBL_IP_USE_YN;//enum('Y','N') NULL접속 가능 아이피 사용여부
	
	//디비외
	private String[] MBR_IDS;//회원아이디s
	private String[] MBR_ADDED_IDS;//이미 추가된 회원아이디s
	private String HMBR_CNT;//이수중인교육정보
	
	
	public String getCONECT_POSBL_IP() {
		return CONECT_POSBL_IP;
	}
	public void setCONECT_POSBL_IP(String cONECT_POSBL_IP) {
		CONECT_POSBL_IP = cONECT_POSBL_IP;
	}
	public String getCONECT_POSBL_IP_USE_YN() {
		return CONECT_POSBL_IP_USE_YN;
	}
	public void setCONECT_POSBL_IP_USE_YN(String cONECT_POSBL_IP_USE_YN) {
		CONECT_POSBL_IP_USE_YN = cONECT_POSBL_IP_USE_YN;
	}
	public String getMBR_PLEDGE_ST() {
		return MBR_PLEDGE_ST;
	}
	public void setMBR_PLEDGE_ST(String mBR_PLEDGE_ST) {
		MBR_PLEDGE_ST = mBR_PLEDGE_ST;
	}
	public String getMBR_INDVDL_INFO_ST() {
		return MBR_INDVDL_INFO_ST;
	}
	public void setMBR_INDVDL_INFO_ST(String mBR_INDVDL_INFO_ST) {
		MBR_INDVDL_INFO_ST = mBR_INDVDL_INFO_ST;
	}
	public String getMBR_NICKNM() {
		return MBR_NICKNM;
	}
	public void setMBR_NICKNM(String mBR_NICKNM) {
		MBR_NICKNM = mBR_NICKNM;
	}
	public String getMBR_HMBR_ONLINE_SN() {
		return MBR_HMBR_ONLINE_SN;
	}
	public void setMBR_HMBR_ONLINE_SN(String mBR_HMBR_ONLINE_SN) {
		MBR_HMBR_ONLINE_SN = mBR_HMBR_ONLINE_SN;
	}
	public String getMBR_HMBR_ONLINE_ST() {
		return MBR_HMBR_ONLINE_ST;
	}
	public void setMBR_HMBR_ONLINE_ST(String mBR_HMBR_ONLINE_ST) {
		MBR_HMBR_ONLINE_ST = mBR_HMBR_ONLINE_ST;
	}
	public String getMBR_GRP_1_ST() {
		return MBR_GRP_1_ST;
	}
	public void setMBR_GRP_1_ST(String mBR_GRP_1_ST) {
		MBR_GRP_1_ST = mBR_GRP_1_ST;
	}
	public String getMBR_GRP_2_ST() {
		return MBR_GRP_2_ST;
	}
	public void setMBR_GRP_2_ST(String mBR_GRP_2_ST) {
		MBR_GRP_2_ST = mBR_GRP_2_ST;
	}
	public String getMBR_GRP_3_ST() {
		return MBR_GRP_3_ST;
	}
	public void setMBR_GRP_3_ST(String mBR_GRP_3_ST) {
		MBR_GRP_3_ST = mBR_GRP_3_ST;
	}
	public String getMBR_GRP_4_ST() {
		return MBR_GRP_4_ST;
	}
	public void setMBR_GRP_4_ST(String mBR_GRP_4_ST) {
		MBR_GRP_4_ST = mBR_GRP_4_ST;
	}
		
	public String getMBR_LV_ID() {
		return MBR_LV_ID;
	}
	public void setMBR_LV_ID(String mBR_LV_ID) {
		MBR_LV_ID = mBR_LV_ID;
	}
	public String getHMBR_CNT() {
		return HMBR_CNT;
	}
	public void setHMBR_CNT(String hMBR_CNT) {
		HMBR_CNT = hMBR_CNT;
	}
	public String[] getMBR_ADDED_IDS() {
		String[] ret = null;
		if(MBR_ADDED_IDS != null) {
			ret = new String[MBR_ADDED_IDS.length];
			for(int i=0; i<MBR_ADDED_IDS.length;i++) {
				ret[i] = MBR_ADDED_IDS[i];
			}
		}
		return ret;
	}
	public void setMBR_ADDED_IDS(String[] mBR_ADDED_IDS) {
		//MBR_ADDED_IDS = mBR_ADDED_IDS;
		MBR_ADDED_IDS = Arrays.copyOf(mBR_ADDED_IDS, mBR_ADDED_IDS.length);
	}
	public String[] getMBR_IDS() {
		String[] ret = null;
		if(MBR_IDS != null) {
			ret = new String[MBR_IDS.length];
			for(int i=0; i<MBR_IDS.length;i++) {
				ret[i] = MBR_IDS[i];
			}
		}
		return ret;
	}
	public void setMBR_IDS(String[] mBR_IDS) {
		//MBR_IDS = mBR_IDS;
		MBR_IDS = Arrays.copyOf(mBR_IDS, mBR_IDS.length);
	}
	
	public String getMBR_SN() {
		return MBR_SN;
	}
	public void setMBR_SN(String mBR_SN) {
		MBR_SN = mBR_SN;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getMBR_PWD() {
		return MBR_PWD;
	}
	public void setMBR_PWD(String mBR_PWD) {
		MBR_PWD = mBR_PWD;
	}
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMBR_TEL() {
		return MBR_TEL;
	}
	public void setMBR_TEL(String mBR_TEL) {
		MBR_TEL = mBR_TEL;
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
	public String getMBR_ZIPCD() {
		return MBR_ZIPCD;
	}
	public void setMBR_ZIPCD(String mBR_ZIPCD) {
		MBR_ZIPCD = mBR_ZIPCD;
	}
	public String getMBR_LAST_CON_TM() {
		return MBR_LAST_CON_TM;
	}
	public void setMBR_LAST_CON_TM(String mBR_LAST_CON_TM) {
		MBR_LAST_CON_TM = mBR_LAST_CON_TM;
	}
	public String getMBR_LAST_CON_IPADDR() {
		return MBR_LAST_CON_IPADDR;
	}
	public void setMBR_LAST_CON_IPADDR(String mBR_LAST_CON_IPADDR) {
		MBR_LAST_CON_IPADDR = mBR_LAST_CON_IPADDR;
	}
	public String getMBR_REG_DT() {
		return MBR_REG_DT;
	}
	public void setMBR_REG_DT(String mBR_REG_DT) {
		MBR_REG_DT = mBR_REG_DT;
	}
	public String getMBR_MOD_DT() {
		return MBR_MOD_DT;
	}
	public void setMBR_MOD_DT(String mBR_MOD_DT) {
		MBR_MOD_DT = mBR_MOD_DT;
	}
	public String getMBR_POSITION_CD() {
		return MBR_POSITION_CD;
	}
	public void setMBR_POSITION_CD(String mBR_POSITION_CD) {
		MBR_POSITION_CD = mBR_POSITION_CD;
	}
	public String getMBR_TYPE_CD() {
		return MBR_TYPE_CD;
	}
	public void setMBR_TYPE_CD(String mBR_TYPE_CD) {
		MBR_TYPE_CD = mBR_TYPE_CD;
	}
	public String getMBR_GRP_ID() {
		return MBR_GRP_ID;
	}
	public void setMBR_GRP_ID(String mBR_GRP_ID) {
		MBR_GRP_ID = mBR_GRP_ID;
	}
	public String getMBR_BIRTH() {
		return MBR_BIRTH;
	}
	public void setMBR_BIRTH(String mBR_BIRTH) {
		MBR_BIRTH = mBR_BIRTH;
	}
	public String getMBR_SEX() {
		return MBR_SEX;
	}
	public void setMBR_SEX(String mBR_SEX) {
		MBR_SEX = mBR_SEX;
	}
	public String getMBR_HOPE_ZONE() {
		return MBR_HOPE_ZONE;
	}
	public void setMBR_HOPE_ZONE(String mBR_HOPE_ZONE) {
		MBR_HOPE_ZONE = mBR_HOPE_ZONE;
	}
	public String getMBR_HOPE_BUSINESS() {
		return MBR_HOPE_BUSINESS;
	}
	public void setMBR_HOPE_BUSINESS(String mBR_HOPE_BUSINESS) {
		MBR_HOPE_BUSINESS = mBR_HOPE_BUSINESS;
	}
	public String getMBR_ST() {
		return MBR_ST;
	}
	public void setMBR_ST(String mBR_ST) {
		MBR_ST = mBR_ST;
	}
	
}
