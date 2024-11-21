package egovframework.all.log.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.main.service.AdmDefaultVO;

public class LogRecordCtiVO extends AdmDefaultVO {
	
	//from object to json
	public String encodingFromObjectToJson(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
		return mapper.writeValueAsString(obj).toString();
	}
	
	private String REG_DT;//datetime NULL로그일자
	private String MBR_IP;//varchar(50) NULL접속아이피
	private String MBR_LV;//int(11) NULL유저레벨
	private String MBR_ID;//varchar(50) NULL유저아이디
	private String URL;//varchar(250) NULL접근URL주소
	private String LOG_DSCRP;//text NULL로그설명
	private String LOG_MSG;//text NULL로그기록
	private String LOG_TYPE;//varchar(50) NULL로그분류
	private String OLD_DATA;//text NULL이전데이터
	private String NEW_DATA;//text NULL신규데이터
	private String REQ_URL;//text NULL요청URL
	private String LOG_UPD_MSG;//text NULL사용자기록사유(사용자수기입력시)
	private String LOG_INFO_USER_AGNET;//text NULL접속자정보:USER_AGENT
	
	//디비외
	private String MBR_NM;//이름
	private String MBR_PSITN_NM;//소속
	private String MBR_NCNM;//호칭
	private String TARGET_IDKEY;//로그분류에 다른 조회용
	
	
	public String getTARGET_IDKEY() {
		return TARGET_IDKEY;
	}
	public void setTARGET_IDKEY(String tARGET_IDKEY) {
		TARGET_IDKEY = tARGET_IDKEY;
	}
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMBR_PSITN_NM() {
		return MBR_PSITN_NM;
	}
	public void setMBR_PSITN_NM(String mBR_PSITN_NM) {
		MBR_PSITN_NM = mBR_PSITN_NM;
	}
	public String getMBR_NCNM() {
		return MBR_NCNM;
	}
	public void setMBR_NCNM(String mBR_NCNM) {
		MBR_NCNM = mBR_NCNM;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getMBR_IP() {
		return MBR_IP;
	}
	public void setMBR_IP(String mBR_IP) {
		MBR_IP = mBR_IP;
	}
	public String getMBR_LV() {
		return MBR_LV;
	}
	public void setMBR_LV(String mBR_LV) {
		MBR_LV = mBR_LV;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getLOG_DSCRP() {
		return LOG_DSCRP;
	}
	public void setLOG_DSCRP(String lOG_DSCRP) {
		LOG_DSCRP = lOG_DSCRP;
	}	
	public String getLOG_MSG() {
		return LOG_MSG;
	}
	public void setLOG_MSG(String lOG_MSG) {
		LOG_MSG = lOG_MSG;
	}
	public String getLOG_TYPE() {
		return LOG_TYPE;
	}
	public void setLOG_TYPE(String lOG_TYPE) {
		LOG_TYPE = lOG_TYPE;
	}
	public String getOLD_DATA() {
		return OLD_DATA;
	}
	public void setOLD_DATA(String oLD_DATA) {
		OLD_DATA = oLD_DATA;
	}
	public String getNEW_DATA() {
		return NEW_DATA;
	}
	public void setNEW_DATA(String nEW_DATA) {
		NEW_DATA = nEW_DATA;
	}
	public String getREQ_URL() {
		return REQ_URL;
	}
	public void setREQ_URL(String rEQ_URL) {
		REQ_URL = rEQ_URL;
	}
	public String getLOG_UPD_MSG() {
		return LOG_UPD_MSG;
	}
	public void setLOG_UPD_MSG(String lOG_UPD_MSG) {
		LOG_UPD_MSG = lOG_UPD_MSG;
	}
	public String getLOG_INFO_USER_AGNET() {
		return LOG_INFO_USER_AGNET;
	}
	public void setLOG_INFO_USER_AGNET(String lOG_INFO_USER_AGNET) {
		LOG_INFO_USER_AGNET = lOG_INFO_USER_AGNET;
	}
	
}
