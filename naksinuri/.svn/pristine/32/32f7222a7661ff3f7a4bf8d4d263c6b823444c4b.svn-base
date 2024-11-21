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
package egovframework.educenter.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EduVO {

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
	
	/** 날짜:년 **/
	private String yearString;
	/** 날짜:월 **/
	private String monthString;
	/** 날짜:일 **/
	private String dayString;
		
	/** 정렬조건 **/
	private String orderByStr;
	
	
	/** 고유코드추출(공통) 
	 * @param label : 최대 4글자
	 * */
	public String getUniqKey(String label) {
		Date today = new Date();
	    SimpleDateFormat date1 = new SimpleDateFormat("yyMMdd");
	    SimpleDateFormat date2 = new SimpleDateFormat("HHmmss");
	    return label+"_"+date1.format(today)+getRandomString()+date2.format(today);
	}
	private String getRandomString() {
		Random random = new Random();
		/*
		random.setSeed(System.currentTimeMillis());
		int intValue1 = random.nextInt(10);
		random.setSeed(System.currentTimeMillis());
		int intValue2 = random.nextInt(10);
		random.setSeed(System.currentTimeMillis());
		int intValue3 = random.nextInt(10);
		random.setSeed(System.currentTimeMillis());
		int intValue4 = random.nextInt(10);
		return intValue1+""+intValue2+""+intValue3+""+intValue4;
		*/
		random.setSeed(System.nanoTime());
		int intValue4 = random.nextInt(9999);
		return String.format("%04d", intValue4);
	}
	
	//로그용변수(변경이력설명)
	private String LOG_UPD_MSG;
	
	
	public String getLOG_UPD_MSG() {
		return LOG_UPD_MSG;
	}
	public void setLOG_UPD_MSG(String lOG_UPD_MSG) {
		LOG_UPD_MSG = lOG_UPD_MSG;
	}
	public String getOrderByStr() {
		return orderByStr;
	}
	public void setOrderByStr(String orderByStr) {
		this.orderByStr = orderByStr;
	}
	public String getYearString() {
		return yearString;
	}
	public void setYearString(String yearString) {
		this.yearString = yearString;
	}
	public String getMonthString() {
		return monthString;
	}
	public void setMonthString(String monthString) {
		this.monthString = monthString;
	}
	public String getDayString() {
		return dayString;
	}
	public void setDayString(String dayString) {
		this.dayString = dayString;
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
