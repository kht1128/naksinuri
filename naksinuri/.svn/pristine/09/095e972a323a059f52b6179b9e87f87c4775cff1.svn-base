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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Class Name : SampleDefaultVO.java
 * @Description : SampleDefaultVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class EduDefaultVO implements Serializable {

	/**
	 *  serialVersion UID
	 */
	private static final long serialVersionUID = -858838578081269359L;
		
	/** 검색조건 */
	private String searchCondition = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지갯수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordCountPerPage */
	private int recordCountPerPage = 10;
	
	/** 게시물전체갯수 */
	private int totalRecordCount = 0;
	
	/** 페이징 기능 처리 쿼리 사용안함  */
	private boolean isNotUsedPagination;
	
	/** 별도 조건 변수 **/
	private String overlapType;
	
	/** 날짜범위검색 */
	private String searchRangeDate;
	private String searchStrDate;
	private String searchEndDate;
	private String searchYear;
	private String searchMonth;
	private String searchDay;
	
	/** 단일파일첨부시(공통) */
	private String ATCH_FILE_ID;//char(20) NOT NULL
	private String FILE_SN;//decimal(10,0) NOT NULL
	private String FILE_STRE_COURS;//varchar(2000) NOT NULL
	private String STRE_FILE_NM;//varchar(255) NOT NULL
	private String ORIGNL_FILE_NM;//varchar(255) NULL
	private String FILE_EXTSN;//varchar(20) NOT NULL
	private String FILE_CN;//mediumtext NULL
	private String FILE_SIZE;//decimal(8,0) NULL
	private String CREAT_DT;//datetime NOT NULL
	private String USE_AT;//char(1) NULL
	
	/** CTI 용 */
	private String IS_JOIN_MBR;//회원가입여부(Y,N)
	private String IS_CTI_MBR;//상담회원여부(Y,N)
		
	/** 정렬방법 */
	private String searchOrderBy = "";
	
	/** 고유코드추출(공통) 
	 * @param label : 최대 4글자
	 * */
	private String CUSTOM_UNIQ_KEY;	
	public String getCUSTOM_UNIQ_KEY() {
		return CUSTOM_UNIQ_KEY;
	}
	public void setCUSTOM_UNIQ_KEY(String cUSTOM_UNIQ_KEY) {
		CUSTOM_UNIQ_KEY = cUSTOM_UNIQ_KEY;
	}
	public String getUniqKey(String label) {
		Date today = new Date();
	    SimpleDateFormat date1 = new SimpleDateFormat("yyMMdd");
	    SimpleDateFormat date2 = new SimpleDateFormat("HHmmss");
	    return label+"_"+date1.format(today)+getRandomString()+date2.format(today);
	}
	private String getRandomString() {
		Random random = new Random();
		//random.setSeed(System.currentTimeMillis());
		//int intValue1 = random.nextInt(10);
		//random.setSeed(System.currentTimeMillis());
		//int intValue2 = random.nextInt(10);
		//random.setSeed(System.currentTimeMillis());
		//int intValue3 = random.nextInt(10);
		//random.setSeed(System.currentTimeMillis());
		//int intValue4 = random.nextInt(10);
		//return intValue1+""+intValue2+""+intValue3+""+intValue4;
		random.setSeed(System.nanoTime());
		int intValue4 = random.nextInt(9999);
		return String.format("%04d", intValue4);
	}
	
	//로그용변수(변경이력설명)
	private String LOG_UPD_MSG;
	private String LOG_TYPE;
	
	
	public String getLOG_TYPE() {
		return LOG_TYPE;
	}
	public void setLOG_TYPE(String lOG_TYPE) {
		LOG_TYPE = lOG_TYPE;
	}
	public String getIS_JOIN_MBR() {
		return IS_JOIN_MBR;
	}
	public void setIS_JOIN_MBR(String iS_JOIN_MBR) {
		IS_JOIN_MBR = iS_JOIN_MBR;
	}
	public String getIS_CTI_MBR() {
		return IS_CTI_MBR;
	}
	public void setIS_CTI_MBR(String iS_CTI_MBR) {
		IS_CTI_MBR = iS_CTI_MBR;
	}
	public String getLOG_UPD_MSG() {
		return LOG_UPD_MSG;
	}
	public void setLOG_UPD_MSG(String lOG_UPD_MSG) {
		LOG_UPD_MSG = lOG_UPD_MSG;
	}
	public String getOverlapType() {
		return overlapType;
	}
	public void setOverlapType(String overlapType) {
		this.overlapType = overlapType;
	}
	public String getSearchMonth() {
		return searchMonth;
	}
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	public String getSearchDay() {
		return searchDay;
	}
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}
	public String getSearchYear() {
		return searchYear;
	}
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	public String getSearchOrderBy() {
		return searchOrderBy;
	}
	public void setSearchOrderBy(String searchOrderBy) {
		this.searchOrderBy = searchOrderBy;
	}
	public String getCREAT_DT() {
		return CREAT_DT;
	}
	public void setCREAT_DT(String cREAT_DT) {
		CREAT_DT = cREAT_DT;
	}
	public String getUSE_AT() {
		return USE_AT;
	}
	public void setUSE_AT(String uSE_AT) {
		USE_AT = uSE_AT;
	}
	public String getATCH_FILE_ID() {
		return ATCH_FILE_ID;
	}
	public void setATCH_FILE_ID(String aTCH_FILE_ID) {
		ATCH_FILE_ID = aTCH_FILE_ID;
	}
	public String getFILE_SN() {
		return FILE_SN;
	}
	public void setFILE_SN(String fILE_SN) {
		FILE_SN = fILE_SN;
	}
	public String getFILE_STRE_COURS() {
		return FILE_STRE_COURS;
	}
	public void setFILE_STRE_COURS(String fILE_STRE_COURS) {
		FILE_STRE_COURS = fILE_STRE_COURS;
	}
	public String getSTRE_FILE_NM() {
		return STRE_FILE_NM;
	}
	public void setSTRE_FILE_NM(String sTRE_FILE_NM) {
		STRE_FILE_NM = sTRE_FILE_NM;
	}
	public String getORIGNL_FILE_NM() {
		return ORIGNL_FILE_NM;
	}
	public void setORIGNL_FILE_NM(String oRIGNL_FILE_NM) {
		ORIGNL_FILE_NM = oRIGNL_FILE_NM;
	}
	public String getFILE_EXTSN() {
		return FILE_EXTSN;
	}
	public void setFILE_EXTSN(String fILE_EXTSN) {
		FILE_EXTSN = fILE_EXTSN;
	}
	public String getFILE_CN() {
		return FILE_CN;
	}
	public void setFILE_CN(String fILE_CN) {
		FILE_CN = fILE_CN;
	}
	public String getFILE_SIZE() {
		return FILE_SIZE;
	}
	public void setFILE_SIZE(String fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSearchStrDate() {
		return searchStrDate;
	}
	public void setSearchStrDate(String searchStrDate) {
		this.searchStrDate = searchStrDate;
	}
	public String getSearchEndDate() {
		return searchEndDate;
	}
	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	public String getSearchRangeDate() {
		return searchRangeDate;
	}
	public void setSearchRangeDate(String searchRangeDate) {
		this.searchRangeDate = searchRangeDate;
	}
	public boolean isNotUsedPagination() {
		return isNotUsedPagination;
	}
	public void setNotUsedPagination(boolean isNotUsedPagination) {
		this.isNotUsedPagination = isNotUsedPagination;
	}
	
	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
