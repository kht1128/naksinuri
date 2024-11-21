package egovframework.com.cmm.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**
 * FileVO 테이블 커스텀
 */
public class DwldConfimFileVO extends EduDefaultVO {
	
	private String ATCH_FILE_ID;//char(20) NOT NULL
	private String FILE_SN;//decimal(10,0) NOT NULL
	private String FILE_STRE_COURS;//varchar(2000) NOT NULL
	private String STRE_FILE_NM;//varchar(255) NOT NULL
	private String ORIGNL_FILE_NM;//varchar(255) NULL
	private String FILE_EXTSN;//varchar(20) NOT NULL
	private String FILE_CN;//mediumtext NULL
	private String FILE_SIZE;//bigint(20) NULL
	private String FILE_MODE;//varchar(10) NULL구분자
	private String USE_ST;//enum('Y','N') NULL사용여부
	private String REG_DT;//datetime NULL작성일자(파일요청일자)
	private String UPD_DT;//datetime NULL수정일자
	private String FILE_CMPLT_DT;//datetime NULL파일생성일자(만든날짜)
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String REQ_MBR_ID;//varchar(50) NULL요청자아이디
	private String REQ_MSG;//text NULL요청사유
	private String DWLD_OTHBC_ST;//int(3) NULL0:전체공개,1:대상자한정
	private String DWLD_WAIT_ST;//enum('Y','N') NULL파일다운로드가능여부
	private String DWLD_CMPLT_DT;//datetime NULL파일다운로드일자
	private String DWLD_CMPLT_ST;//enum('Y','N') NULL파일다운로드
	private String DWLD_CNT;//int(10) NULL다운로드횟수
	private String DWLD_AFTER_DEL_ST;//enum('Y','N') NULL다운로드후삭제여부
	private String DWLD_MAX_CNT;//int(10) NULL최대다운로드가능수량(0:무제한)
	private String CONFIM_ST;//enum('Y','N') NULL승인여부
	private String CONFIM_MBR_ID;//varchar(50) NULL승인자아이디
	private String CONFIM_DT;//datetime NULL승인일자
	private String CONFIM_MSG;//text NULL처리사유
	private String INDVDL_INFO_CNT;//int(10) NULL개인정보건수
		
	//디비외
	private String REQ_MBR_NM;//요청자이름
	private String REQ_MBR_HP;//요청자연락처 
	private String CONFIM_MBR_NM;//승인자이름
	private String CONFIM_MBR_HP;//승인자연락처
	private String RANGE_TIME_HOUR;//특정시간동안 다운로드시 승인제 전환:제한시간(시)
	private String RANGE_TIME_MIN;//특정시간동안 다운로드시 승인제 전환:제한시간(분)
	private String RANGE_TIME_SEC;//특정시간동안 다운로드시 승인제 전환:제한시간(초)
	private String REG_LIMIT_CNT;//특정시간동안 다운로드시 승인제 전환:초과횟수(회)
	private String UNLOCK_FILE_SN;//특정시간동안 다운로드시 승인제 전환 비교 해제 값

	public String getRANGE_TIME_HOUR() {
		return RANGE_TIME_HOUR;
	}
	public void setRANGE_TIME_HOUR(String rANGE_TIME_HOUR) {
		RANGE_TIME_HOUR = rANGE_TIME_HOUR;
	}
	public String getRANGE_TIME_SEC() {
		return RANGE_TIME_SEC;
	}
	public void setRANGE_TIME_SEC(String rANGE_TIME_SEC) {
		RANGE_TIME_SEC = rANGE_TIME_SEC;
	}
	public String getUNLOCK_FILE_SN() {
		return UNLOCK_FILE_SN;
	}
	public void setUNLOCK_FILE_SN(String uNLOCK_FILE_SN) {
		UNLOCK_FILE_SN = uNLOCK_FILE_SN;
	}
	public String getRANGE_TIME_MIN() {
		return RANGE_TIME_MIN;
	}
	public void setRANGE_TIME_MIN(String rANGE_TIME_MIN) {
		RANGE_TIME_MIN = rANGE_TIME_MIN;
	}
	public String getREG_LIMIT_CNT() {
		return REG_LIMIT_CNT;
	}
	public void setREG_LIMIT_CNT(String rEG_LIMIT_CNT) {
		REG_LIMIT_CNT = rEG_LIMIT_CNT;
	}
	public String getINDVDL_INFO_CNT() {
		return INDVDL_INFO_CNT;
	}
	public void setINDVDL_INFO_CNT(String iNDVDL_INFO_CNT) {
		INDVDL_INFO_CNT = iNDVDL_INFO_CNT;
	}
	public String getREQ_MSG() {
		return REQ_MSG;
	}
	public void setREQ_MSG(String rEQ_MSG) {
		REQ_MSG = rEQ_MSG;
	}
	public String getCONFIM_MSG() {
		return CONFIM_MSG;
	}
	public void setCONFIM_MSG(String cONFIM_MSG) {
		CONFIM_MSG = cONFIM_MSG;
	}
	public String getREQ_MBR_NM() {
		return REQ_MBR_NM;
	}
	public void setREQ_MBR_NM(String rEQ_MBR_NM) {
		REQ_MBR_NM = rEQ_MBR_NM;
	}
	public String getREQ_MBR_HP() {
		return REQ_MBR_HP;
	}
	public void setREQ_MBR_HP(String rEQ_MBR_HP) {
		REQ_MBR_HP = rEQ_MBR_HP;
	}
	public String getCONFIM_MBR_NM() {
		return CONFIM_MBR_NM;
	}
	public void setCONFIM_MBR_NM(String cONFIM_MBR_NM) {
		CONFIM_MBR_NM = cONFIM_MBR_NM;
	}
	public String getCONFIM_MBR_HP() {
		return CONFIM_MBR_HP;
	}
	public void setCONFIM_MBR_HP(String cONFIM_MBR_HP) {
		CONFIM_MBR_HP = cONFIM_MBR_HP;
	}
	public String getDWLD_MAX_CNT() {
		return DWLD_MAX_CNT;
	}
	public void setDWLD_MAX_CNT(String dWLD_MAX_CNT) {
		DWLD_MAX_CNT = dWLD_MAX_CNT;
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
	public String getFILE_MODE() {
		return FILE_MODE;
	}
	public void setFILE_MODE(String fILE_MODE) {
		FILE_MODE = fILE_MODE;
	}
	public String getUSE_ST() {
		return USE_ST;
	}
	public void setUSE_ST(String uSE_ST) {
		USE_ST = uSE_ST;
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
	public String getFILE_CMPLT_DT() {
		return FILE_CMPLT_DT;
	}
	public void setFILE_CMPLT_DT(String fILE_CMPLT_DT) {
		FILE_CMPLT_DT = fILE_CMPLT_DT;
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
	public String getREQ_MBR_ID() {
		return REQ_MBR_ID;
	}
	public void setREQ_MBR_ID(String rEQ_MBR_ID) {
		REQ_MBR_ID = rEQ_MBR_ID;
	}
	public String getDWLD_OTHBC_ST() {
		return DWLD_OTHBC_ST;
	}
	public void setDWLD_OTHBC_ST(String dWLD_OTHBC_ST) {
		DWLD_OTHBC_ST = dWLD_OTHBC_ST;
	}
	public String getDWLD_WAIT_ST() {
		return DWLD_WAIT_ST;
	}
	public void setDWLD_WAIT_ST(String dWLD_WAIT_ST) {
		DWLD_WAIT_ST = dWLD_WAIT_ST;
	}
	public String getDWLD_CMPLT_DT() {
		return DWLD_CMPLT_DT;
	}
	public void setDWLD_CMPLT_DT(String dWLD_CMPLT_DT) {
		DWLD_CMPLT_DT = dWLD_CMPLT_DT;
	}
	public String getDWLD_CMPLT_ST() {
		return DWLD_CMPLT_ST;
	}
	public void setDWLD_CMPLT_ST(String dWLD_CMPLT_ST) {
		DWLD_CMPLT_ST = dWLD_CMPLT_ST;
	}
	public String getDWLD_CNT() {
		return DWLD_CNT;
	}
	public void setDWLD_CNT(String dWLD_CNT) {
		DWLD_CNT = dWLD_CNT;
	}
	public String getDWLD_AFTER_DEL_ST() {
		return DWLD_AFTER_DEL_ST;
	}
	public void setDWLD_AFTER_DEL_ST(String dWLD_AFTER_DEL_ST) {
		DWLD_AFTER_DEL_ST = dWLD_AFTER_DEL_ST;
	}
	public String getCONFIM_ST() {
		return CONFIM_ST;
	}
	public void setCONFIM_ST(String cONFIM_ST) {
		CONFIM_ST = cONFIM_ST;
	}
	public String getCONFIM_MBR_ID() {
		return CONFIM_MBR_ID;
	}
	public void setCONFIM_MBR_ID(String cONFIM_MBR_ID) {
		CONFIM_MBR_ID = cONFIM_MBR_ID;
	}
	public String getCONFIM_DT() {
		return CONFIM_DT;
	}
	public void setCONFIM_DT(String cONFIM_DT) {
		CONFIM_DT = cONFIM_DT;
	}
	
}
