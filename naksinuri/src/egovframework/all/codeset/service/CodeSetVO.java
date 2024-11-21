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
package egovframework.all.codeset.service;

import java.io.Serializable;
import java.util.List;

/**
 * [공통 카테고리 코드값]
 * 테이블명 : ALL_CODE_SET_TB
 */
public class CodeSetVO implements Serializable {
	private String CD_SN;//bigint(20) NOT NULL일변번호
	private String CD_ID;//varchar(10) NULL코드아이디
	private String CD_MASTER_ID;//varchar(10) NULL코드마스터아이디
	private String CD_NM;//varchar(30) NULL코드명
	private String CD_DES;//varchar(255) NULL코드설명
	private String CD_CN;//text NULL코드내용
	private String CD_ORD_NO;//int(11) NULL코드값
	private String CD_LEVEL;//tinyint(4) NULL코드레벨	
	private String USE_AT;//enum('Y','N') NULLN:사용안함,Y:사용함
	private String DEL_AT;//enum('Y','N') NULLN:정상,Y:삭제(1단계)
	private String HIDE_AT;//enum('Y','N') NULLN:정상,Y:노출안함
	private String REG_DT;//datetime NULL작성일자
	private String UPD_DT;//datetime NULL변경일자
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	
	//디비외
	private String  CD_ID_CHG;//기존 코드 아이디
	private String  CD_GB; //카테고리 구분값
	private String  CD_MASTER_ID_CHG; //하위카테고리 변경값
	private String IS_CD_UP;//상위 카테고리 조회
	private String IS_CD_DOWN;//하위 카테고리 조회	
	private List<CodeSetVO> arrItem;	
	
	
	public String getCD_MASTER_ID_CHG() {
		return CD_MASTER_ID_CHG;
	}
	public void setCD_MASTER_ID_CHG(String cD_MASTER_ID_CHG) {
		CD_MASTER_ID_CHG = cD_MASTER_ID_CHG;
	}
	public String getCD_GB() {
		return CD_GB;
	}
	public void setCD_GB(String cD_GB) {
		CD_GB = cD_GB;
	}
	public String getCD_ID_CHG() {
		return CD_ID_CHG;
	}
	public void setCD_ID_CHG(String cD_ID_CHG) {
		CD_ID_CHG = cD_ID_CHG;
	}
	public List<CodeSetVO> getArrItem() {
		return arrItem;
	}
	public void setArrItem(List<CodeSetVO> arrItem) {
		this.arrItem = arrItem;
	}
	public String getIS_CD_UP() {
		return IS_CD_UP;
	}
	public void setIS_CD_UP(String iS_CD_UP) {
		IS_CD_UP = iS_CD_UP;
	}
	public String getIS_CD_DOWN() {
		return IS_CD_DOWN;
	}
	public void setIS_CD_DOWN(String iS_CD_DOWN) {
		IS_CD_DOWN = iS_CD_DOWN;
	}
	public String getCD_SN() {
		return CD_SN;
	}
	public void setCD_SN(String cD_SN) {
		CD_SN = cD_SN;
	}
	public String getCD_ID() {
		return CD_ID;
	}
	public void setCD_ID(String cD_ID) {
		CD_ID = cD_ID;
	}
	public String getCD_MASTER_ID() {
		return CD_MASTER_ID;
	}
	public void setCD_MASTER_ID(String cD_MASTER_ID) {
		CD_MASTER_ID = cD_MASTER_ID;
	}
	public String getCD_NM() {
		return CD_NM;
	}
	public void setCD_NM(String cD_NM) {
		CD_NM = cD_NM;
	}
	public String getCD_DES() {
		return CD_DES;
	}
	public void setCD_DES(String cD_DES) {
		CD_DES = cD_DES;
	}
	public String getCD_CN() {
		return CD_CN;
	}
	public void setCD_CN(String cD_CN) {
		CD_CN = cD_CN;
	}
	public String getCD_ORD_NO() {
		return CD_ORD_NO;
	}
	public void setCD_ORD_NO(String cD_ORD_NO) {
		CD_ORD_NO = cD_ORD_NO;
	}
	public String getCD_LEVEL() {
		return CD_LEVEL;
	}
	public void setCD_LEVEL(String cD_LEVEL) {
		CD_LEVEL = cD_LEVEL;
	}
	public String getUSE_AT() {
		return USE_AT;
	}
	public void setUSE_AT(String uSE_AT) {
		USE_AT = uSE_AT;
	}
	public String getDEL_AT() {
		return DEL_AT;
	}
	public void setDEL_AT(String dEL_AT) {
		DEL_AT = dEL_AT;
	}
	public String getHIDE_AT() {
		return HIDE_AT;
	}
	public void setHIDE_AT(String hIDE_AT) {
		HIDE_AT = hIDE_AT;
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
	
	
}
