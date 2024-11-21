package egovframework.all.sharelink.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * 단축URL링크 테이블
 * 
 * 테이블명 : ALL_SHARE_LINK_TB
 * */
public class ShareLinkVO extends EduDefaultVO {
	//링크주소생성
	public String getUniqLinkUrlKey() {
		Date today = new Date();
	    SimpleDateFormat date1 = new SimpleDateFormat("yyMMdd");
	    SimpleDateFormat date2 = new SimpleDateFormat("HHmmss");
	    Random random = new Random();
	    random.setSeed(System.nanoTime());
		int intValue1 = random.nextInt(10);
		random.setSeed(System.nanoTime());
		int intValue2 = random.nextInt(10);
		random.setSeed(System.nanoTime());
		int intValue3 = random.nextInt(10);
		random.setSeed(System.nanoTime());
		int intValue4 = random.nextInt(10);
		return intValue1+intValue2+date1.format(today)+intValue3+date2.format(today)+intValue4;
	}
	private String LINK_UNIQ_ID;//varchar(100) NOT NULL고유번호
	private String LINK_URL;//varchar(250) NULL단축url
	private String LINK_RST_URL;//varchar(250) NULL실제리턴url
	private String LINK_RST_URL_M;//varchar(250) NULL실제리턴url(모바일)
	private String LINK_PL1;//varchar(50) NULLurl구분자1(센터)
	private String LINK_PL2;//varchar(50) NULLurl구분자2(메뉴명)
	private String LINK_PL3;//varchar(50) NULLurl구분자3(폴더명)
	private String LINK_PL4;//varchar(50) NULLurl구분자4(페이지명)
	private String LINK_SID;//varchar(50) NULL게시물번호
	private String LINK_TYPE;//varchar(20) NULL링크타입(미사용)
	private String LINK_HIT;//int(11) NULL요청횟수(조회수)	
	private String USE_ST;//enum('Y','N') NULL사용여부(Y:사용,N:사용안함)
	private String LOCK_ST;//enum('Y','N') NULL허용여부(Y:허용안함,N:허용함)
	public String getUSE_ST() {
		return USE_ST;
	}
	public void setUSE_ST(String uSE_ST) {
		USE_ST = uSE_ST;
	}
	public String getLOCK_ST() {
		return LOCK_ST;
	}
	public void setLOCK_ST(String lOCK_ST) {
		LOCK_ST = lOCK_ST;
	}
	public String getLINK_UNIQ_ID() {
		return LINK_UNIQ_ID;
	}
	public void setLINK_UNIQ_ID(String lINK_UNIQ_ID) {
		LINK_UNIQ_ID = lINK_UNIQ_ID;
	}
	public String getLINK_HIT() {
		return LINK_HIT;
	}
	public void setLINK_HIT(String lINK_HIT) {
		LINK_HIT = lINK_HIT;
	}
	public String getLINK_URL() {
		return LINK_URL;
	}
	public void setLINK_URL(String lINK_URL) {
		LINK_URL = lINK_URL;
	}
	public String getLINK_RST_URL() {
		return LINK_RST_URL;
	}
	public void setLINK_RST_URL(String lINK_RST_URL) {
		LINK_RST_URL = lINK_RST_URL;
	}
	public String getLINK_RST_URL_M() {
		return LINK_RST_URL_M;
	}
	public void setLINK_RST_URL_M(String lINK_RST_URL_M) {
		LINK_RST_URL_M = lINK_RST_URL_M;
	}
	public String getLINK_PL1() {
		return LINK_PL1;
	}
	public void setLINK_PL1(String lINK_PL1) {
		LINK_PL1 = lINK_PL1;
	}
	public String getLINK_PL2() {
		return LINK_PL2;
	}
	public void setLINK_PL2(String lINK_PL2) {
		LINK_PL2 = lINK_PL2;
	}
	public String getLINK_PL3() {
		return LINK_PL3;
	}
	public void setLINK_PL3(String lINK_PL3) {
		LINK_PL3 = lINK_PL3;
	}
	public String getLINK_PL4() {
		return LINK_PL4;
	}
	public void setLINK_PL4(String lINK_PL4) {
		LINK_PL4 = lINK_PL4;
	}
	public String getLINK_SID() {
		return LINK_SID;
	}
	public void setLINK_SID(String lINK_SID) {
		LINK_SID = lINK_SID;
	}
	public String getLINK_TYPE() {
		return LINK_TYPE;
	}
	public void setLINK_TYPE(String lINK_TYPE) {
		LINK_TYPE = lINK_TYPE;
	}
}