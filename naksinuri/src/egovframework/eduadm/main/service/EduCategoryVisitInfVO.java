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
package egovframework.eduadm.main.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * [교육기관정보]
 * 테이블명 : EDU_CAT_VISIT_INF_TB
 */
public class EduCategoryVisitInfVO extends EduDefaultVO {
	
	private String CAT_VISIT_SN;//int(11) NOT NULL기관코드
	private String CAT_VISIT_NM;//varchar(250) NOT NULL기관명
	private String CAT_VISIT_DSCRP;//text NULL설명
	private String USE_ST;//int(3) NULL0:사용안함,1:사용함
	private String DEL_ST;//int(3) NULL0:삭제안함,1:삭제함
	private String REG_DT;//datetime NULL작성일자
	private String UPD_DT;//datetime NULL수정일자
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String CAT_VISIT_ORD;//int(11) NULL정렬순서(기본값9999)
	public String getCAT_VISIT_SN() {
		return CAT_VISIT_SN;
	}
	public void setCAT_VISIT_SN(String cAT_VISIT_SN) {
		CAT_VISIT_SN = cAT_VISIT_SN;
	}
	public String getCAT_VISIT_NM() {
		return CAT_VISIT_NM;
	}
	public void setCAT_VISIT_NM(String cAT_VISIT_NM) {
		CAT_VISIT_NM = cAT_VISIT_NM;
	}
	public String getCAT_VISIT_DSCRP() {
		return CAT_VISIT_DSCRP;
	}
	public void setCAT_VISIT_DSCRP(String cAT_VISIT_DSCRP) {
		CAT_VISIT_DSCRP = cAT_VISIT_DSCRP;
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
	public String getCAT_VISIT_ORD() {
		return CAT_VISIT_ORD;
	}
	public void setCAT_VISIT_ORD(String cAT_VISIT_ORD) {
		CAT_VISIT_ORD = cAT_VISIT_ORD;
	}
	
}
