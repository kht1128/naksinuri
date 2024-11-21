package egovframework.seadm.member.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDefaultVO  implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건-회원상태     (0, A, D, P)*/
    private String sbscrbSttus = "0";
	
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** Category */
    private String categoryId = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;
    
    /** 모바일 페이지갯수 */
    private int mobilepageUnit = 10;
    
    /** 모바일 페이지사이즈 */
    private int mobilepageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
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
		return intValue1+""+intValue2+""+intValue3;
		*/
		random.setSeed(System.nanoTime());
		int intValue = random.nextInt(9999);
		return String.format("%03d", intValue);
	}
	

	/**
	 * sbscrbSttus attribute 값을  리턴한다.
	 * @return String
	 */
	public String getSbscrbSttus() {
		return sbscrbSttus;
	}

	/**
	 * sbscrbSttus attribute 값을 설정한다.
	 * @param sbscrbSttus String
	 */
	public void setSbscrbSttus(String sbscrbSttus) {
		this.sbscrbSttus = sbscrbSttus;
	}

	/**
	 * searchCondition attribute 값을  리턴한다.
	 * @return String
	 */
	public String getSearchCondition() {
		return searchCondition;
	}

	/**
	 * searchCondition attribute 값을 설정한다.
	 * @param searchCondition String
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * categoryId attribute 값을  리턴한다.
	 * @return String
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * categoryId attribute 값을 설정한다.
	 * @param categoryId String
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * searchKeyword attribute 값을  리턴한다.
	 * @return String
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * searchKeyword attribute 값을 설정한다.
	 * @param searchKeyword String
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * searchUseYn attribute 값을  리턴한다.
	 * @return String
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}

	/**
	 * searchUseYn attribute 값을 설정한다.
	 * @param searchUseYn String
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	/**
	 * pageIndex attribute 값을  리턴한다.
	 * @return int
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * pageIndex attribute 값을 설정한다.
	 * @param pageIndex int
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * pageUnit attribute 값을  리턴한다.
	 * @return int
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * pageUnit attribute 값을 설정한다.
	 * @param pageUnit int
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	/**
	 * mobilepageUnit attribute 값을  리턴한다.
	 * @return int
	 */
	public int getMobilePageUnit() {
		return mobilepageUnit;
	}

	/**
	 * mobilepageUnit attribute 값을 설정한다.
	 * @param mobilepageUnit int
	 */
	public void setMobilePageUnit(int mobilepageUnit) {
		this.mobilepageUnit = mobilepageUnit;
	}

	/**
	 * pageSize attribute 값을  리턴한다.
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * pageSize attribute 값을 설정한다.
	 * @param pageSize int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * mobilepageSize attribute 값을  리턴한다.
	 * @return int
	 */
	public int getMobilePageSize() {
		return mobilepageSize;
	}

	/**
	 * pageSize attribute 값을 설정한다.
	 * @param pageSize int
	 */
	public void setMobilePageSize(int mobilepageSize) {
		this.mobilepageSize = mobilepageSize;
	}

	/**
	 * firstIndex attribute 값을  리턴한다.
	 * @return int
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * firstIndex attribute 값을 설정한다.
	 * @param firstIndex int
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * lastIndex attribute 값을  리턴한다.
	 * @return int
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * lastIndex attribute 값을 설정한다.
	 * @param lastIndex int
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * recordCountPerPage attribute 값을  리턴한다.
	 * @return int
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * recordCountPerPage attribute 값을 설정한다.
	 * @param recordCountPerPage int
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
    
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

}
