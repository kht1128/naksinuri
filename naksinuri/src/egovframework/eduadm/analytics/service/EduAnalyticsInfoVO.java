package egovframework.eduadm.analytics.service;

import egovframework.seadm.member.service.UserDefaultVO;

public class EduAnalyticsInfoVO extends UserDefaultVO {

	private String VALUE_YEAR;//int(4) NULL통계기록:년
	private String VALUE_MONTH;//int(2) NULL통계기록:월
	private String VALUE_DAY;//int(2) NULL통계기록:일
	private String VALUE_DATE;//datetime NULL통계기록:일자
	private String VISIT_FLATFORM;//varchar(250) NULL통계기록:플랫폼
	private String VISIT_BROWSER;//varchar(250) NULL통계기록:브라우저
	private String VISIT_MOBILE;//varchar(250) NULL통계기록:모바일
	private String VISIT_MOBILE_ST;//enum('Y','N') NULL통계기록:모바일여부
	private String VISIT_ROBOT;//varchar(250) NULL통계기록:로봇
	private String VISIT_ROBOT_ST;//enum('Y','N') NULL통계기록:로봇여부
	private String VISIT_INPUT_TB;//varchar(250) NULL통계기록:유입타입
	private String VISIT_INPUT_NM;//varchar(250) NULL통계기록:유입검색어
	private String REG_DT;//datetime NULL작성일자
	private String UPD_DT;//datetime NULL수정일자
	private String USE_ST;//enum('Y','N') NULL통계반영여부
	
	//디비외
	private String SEARCH_DT;
	private String SEARCH_CNT;
	private String SEARCH_TOTCNT;
	private String SEARCH_PC_CNT;
	private String SEARCH_MOBILE_CNT;
	private String SEARCH_ROBOT_CNT;
	
	
	public String getSEARCH_TOTCNT() {
		return SEARCH_TOTCNT;
	}
	public void setSEARCH_TOTCNT(String sEARCH_TOTCNT) {
		SEARCH_TOTCNT = sEARCH_TOTCNT;
	}
	public String getSEARCH_DT() {
		return SEARCH_DT;
	}
	public void setSEARCH_DT(String sEARCH_DT) {
		SEARCH_DT = sEARCH_DT;
	}
	public String getSEARCH_PC_CNT() {
		return SEARCH_PC_CNT;
	}
	public void setSEARCH_PC_CNT(String sEARCH_PC_CNT) {
		SEARCH_PC_CNT = sEARCH_PC_CNT;
	}
	public String getSEARCH_MOBILE_CNT() {
		return SEARCH_MOBILE_CNT;
	}
	public void setSEARCH_MOBILE_CNT(String sEARCH_MOBILE_CNT) {
		SEARCH_MOBILE_CNT = sEARCH_MOBILE_CNT;
	}
	public String getSEARCH_ROBOT_CNT() {
		return SEARCH_ROBOT_CNT;
	}
	public void setSEARCH_ROBOT_CNT(String sEARCH_ROBOT_CNT) {
		SEARCH_ROBOT_CNT = sEARCH_ROBOT_CNT;
	}
	public String getSEARCH_CNT() {
		return SEARCH_CNT;
	}
	public void setSEARCH_CNT(String sEARCH_CNT) {
		SEARCH_CNT = sEARCH_CNT;
	}
	public String getVALUE_YEAR() {
		return VALUE_YEAR;
	}
	public void setVALUE_YEAR(String vALUE_YEAR) {
		VALUE_YEAR = vALUE_YEAR;
	}
	public String getVALUE_MONTH() {
		return VALUE_MONTH;
	}
	public void setVALUE_MONTH(String vALUE_MONTH) {
		VALUE_MONTH = vALUE_MONTH;
	}
	public String getVALUE_DAY() {
		return VALUE_DAY;
	}
	public void setVALUE_DAY(String vALUE_DAY) {
		VALUE_DAY = vALUE_DAY;
	}
	public String getVALUE_DATE() {
		return VALUE_DATE;
	}
	public void setVALUE_DATE(String vALUE_DATE) {
		VALUE_DATE = vALUE_DATE;
	}
	public String getVISIT_FLATFORM() {
		return VISIT_FLATFORM;
	}
	public void setVISIT_FLATFORM(String vISIT_FLATFORM) {
		VISIT_FLATFORM = vISIT_FLATFORM;
	}
	public String getVISIT_BROWSER() {
		return VISIT_BROWSER;
	}
	public void setVISIT_BROWSER(String vISIT_BROWSER) {
		VISIT_BROWSER = vISIT_BROWSER;
	}
	public String getVISIT_MOBILE() {
		return VISIT_MOBILE;
	}
	public void setVISIT_MOBILE(String vISIT_MOBILE) {
		VISIT_MOBILE = vISIT_MOBILE;
	}
	public String getVISIT_MOBILE_ST() {
		return VISIT_MOBILE_ST;
	}
	public void setVISIT_MOBILE_ST(String vISIT_MOBILE_ST) {
		VISIT_MOBILE_ST = vISIT_MOBILE_ST;
	}
	public String getVISIT_ROBOT() {
		return VISIT_ROBOT;
	}
	public void setVISIT_ROBOT(String vISIT_ROBOT) {
		VISIT_ROBOT = vISIT_ROBOT;
	}
	public String getVISIT_ROBOT_ST() {
		return VISIT_ROBOT_ST;
	}
	public void setVISIT_ROBOT_ST(String vISIT_ROBOT_ST) {
		VISIT_ROBOT_ST = vISIT_ROBOT_ST;
	}
	public String getVISIT_INPUT_TB() {
		return VISIT_INPUT_TB;
	}
	public void setVISIT_INPUT_TB(String vISIT_INPUT_TB) {
		VISIT_INPUT_TB = vISIT_INPUT_TB;
	}
	public String getVISIT_INPUT_NM() {
		return VISIT_INPUT_NM;
	}
	public void setVISIT_INPUT_NM(String vISIT_INPUT_NM) {
		VISIT_INPUT_NM = vISIT_INPUT_NM;
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
	 
}
