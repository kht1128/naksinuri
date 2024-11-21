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
package egovframework.eduadm.category.service;

import java.util.Arrays;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [교육카테고리]
 * 테이블명 : EDU_CAT_TB , EDU_CAT_DTL_TB
 */
public class EduCategoryVO extends EduDefaultVO {

	//교육카테고리2
	private String CAT_DTL_SN;//int(11) NOT NULL교육카테고리2번호
	private String CAT_DTL_NM;//varchar(250) NOT NULL교육카테고리2명칭
	private String CAT_DTL_DSCRP;//varchar(250) NULL교육카테고리2설명
	private String LRNNG_TIME;//int(11) NULL교육시간
	private String LRNNG_POINT;//int(11) NULL기본점수
	private String LRNNG_GB;//varchar(20) NULL교육구분(online,offline)
	
	//공통
	private String CAT_SN;//int(11) NOT NULL교육카테고리번호
	private String CAT_NM;//varchar(250) NOT NULL교육카테고리명
	private String CAT_DSCRP;//varchar(250) NULL설명
	private String USE_ST;//int(3) NULL0:사용안함,1:사용함
	private String DEL_ST;//int(3) NULL0:삭제안함,1:삭제함
	private String REG_DT;//datetime NULL작성일자
	private String UPD_DT;//datetime NULL수정일자
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String CAT_ORD;//int(11) NULL정렬순서(기본값9999)
	
	//DB외사용
	private String typeStr;//카테고리분류(null:대분류,dtl:하위)
	private String USE_ST_CHK;//사용유무체크박스
	private String CAT_SN_CHNG;//교육카테고리1변경값
	private String[] trn_sn_arr;//교육자료고유번호 모음 (교육자료조회시 사용)
	
	public String getCAT_DTL_SN() {
		return CAT_DTL_SN;
	}
	public void setCAT_DTL_SN(String cAT_DTL_SN) {
		CAT_DTL_SN = cAT_DTL_SN;
	}
	public String getCAT_DTL_NM() {
		return CAT_DTL_NM;
	}
	public void setCAT_DTL_NM(String cAT_DTL_NM) {
		CAT_DTL_NM = cAT_DTL_NM;
	}
	public String getCAT_DTL_DSCRP() {
		return CAT_DTL_DSCRP;
	}
	public void setCAT_DTL_DSCRP(String cAT_DTL_DSCRP) {
		CAT_DTL_DSCRP = cAT_DTL_DSCRP;
	}
	public String getLRNNG_TIME() {
		return LRNNG_TIME;
	}
	public void setLRNNG_TIME(String lRNNG_TIME) {
		LRNNG_TIME = lRNNG_TIME;
	}
	public String getLRNNG_POINT() {
		return LRNNG_POINT;
	}
	public void setLRNNG_POINT(String lRNNG_POINT) {
		LRNNG_POINT = lRNNG_POINT;
	}
	public String getLRNNG_GB() {
		return LRNNG_GB;
	}
	public void setLRNNG_GB(String lRNNG_GB) {
		LRNNG_GB = lRNNG_GB;
	}
	public String getCAT_SN() {
		return CAT_SN;
	}
	public void setCAT_SN(String cAT_SN) {
		CAT_SN = cAT_SN;
	}
	public String getCAT_NM() {
		return CAT_NM;
	}
	public void setCAT_NM(String cAT_NM) {
		CAT_NM = cAT_NM;
	}
	public String getCAT_DSCRP() {
		return CAT_DSCRP;
	}
	public void setCAT_DSCRP(String cAT_DSCRP) {
		CAT_DSCRP = cAT_DSCRP;
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
	public String getCAT_ORD() {
		return CAT_ORD;
	}
	public void setCAT_ORD(String cAT_ORD) {
		CAT_ORD = cAT_ORD;
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
	public String getCAT_SN_CHNG() {
		return CAT_SN_CHNG;
	}
	public void setCAT_SN_CHNG(String cAT_SN_CHNG) {
		CAT_SN_CHNG = cAT_SN_CHNG;
	}
	public String[] getTrn_sn_arr() {
		String[] ret = null;
		if(trn_sn_arr != null) {
			ret = new String[trn_sn_arr.length];
			for(int i=0; i<trn_sn_arr.length;i++) {
				ret[i] = trn_sn_arr[i];
			}
		}
		return ret;
	}
	public void setTrn_sn_arr(String[] trn_sn_arr) {
		//this.trn_sn_arr = trn_sn_arr;
		this.trn_sn_arr = Arrays.copyOf(trn_sn_arr, trn_sn_arr.length);		
	}
	
}
