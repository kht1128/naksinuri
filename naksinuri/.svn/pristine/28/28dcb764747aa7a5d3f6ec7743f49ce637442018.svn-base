package egovframework.all.log.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.adm.main.service.AdmDefaultVO;

public class LogMemberModifyVO extends AdmDefaultVO {
	
	//from object to json
	public String encodingFromObjectToJson(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
		return mapper.writeValueAsString(obj).toString();
	}
	
	private String LOG_SN;//bigint(20) NULL로그일련번호
	private String LOG_INFO_IP;//varchar(50) NULL접속자정보:IP
	private String REG_DT;//datetime NULL로그작성일자
	private String REG_MBR_ID;//varchar(50) NULL로그작성자아이디
	private String LOG_DSCRP;//text NULL로그기록(이력)
	private String LOG_TYPE;//varchar(50) NULL로그구분자
	private String MBR_ID;//varchar(50) NULL대상아이디
	private String TMP_MBR_NM;//varchar(50) NULL대상이름(회원삭제시출력)
	private String LOG_UPD_MSG;//text NULL변경이력(사용자수기입력 등)
	private String OLD_DATA;//text NULL이전데이터
	private String NEW_DATA;//text NULL신규데이터
	private String LOG_INFO_USER_AGNET;//text NULL접속자정보:USER_AGENT
	private String MASTER_DATA;//text NULL접속자정보:USER_AGENT
	//디비외
	private String MBR_NM;
	private String MBR_NCNM;
	private String MASTER_MBR_NM;
	private String MASTER_MBR_PSITN_NM;
	private String MASTER_MBR_NCNM;
	
	
	public String getMBR_NCNM() {
		return MBR_NCNM;
	}
	public void setMBR_NCNM(String mBR_NCNM) {
		MBR_NCNM = mBR_NCNM;
	}
	public String getTMP_MBR_NM() {
		return TMP_MBR_NM;
	}
	public void setTMP_MBR_NM(String tMP_MBR_NM) {
		TMP_MBR_NM = tMP_MBR_NM;
	}
	public String getMASTER_MBR_NM() {
		return MASTER_MBR_NM;
	}
	public void setMASTER_MBR_NM(String mASTER_MBR_NM) {
		MASTER_MBR_NM = mASTER_MBR_NM;
	}
	public String getMASTER_MBR_PSITN_NM() {
		return MASTER_MBR_PSITN_NM;
	}
	public void setMASTER_MBR_PSITN_NM(String mASTER_MBR_PSITN_NM) {
		MASTER_MBR_PSITN_NM = mASTER_MBR_PSITN_NM;
	}
	public String getMASTER_MBR_NCNM() {
		return MASTER_MBR_NCNM;
	}
	public void setMASTER_MBR_NCNM(String mASTER_MBR_NCNM) {
		MASTER_MBR_NCNM = mASTER_MBR_NCNM;
	}
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMASTER_DATA() {
		return MASTER_DATA;
	}
	public void setMASTER_DATA(String mASTER_DATA) {
		MASTER_DATA = mASTER_DATA;
	}
	public String getLOG_SN() {
		return LOG_SN;
	}
	public void setLOG_SN(String lOG_SN) {
		LOG_SN = lOG_SN;
	}
	public String getLOG_INFO_IP() {
		return LOG_INFO_IP;
	}
	public void setLOG_INFO_IP(String lOG_INFO_IP) {
		LOG_INFO_IP = lOG_INFO_IP;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getREG_MBR_ID() {
		return REG_MBR_ID;
	}
	public void setREG_MBR_ID(String rEG_MBR_ID) {
		REG_MBR_ID = rEG_MBR_ID;
	}
	public String getLOG_DSCRP() {
		return LOG_DSCRP;
	}
	public void setLOG_DSCRP(String lOG_DSCRP) {
		LOG_DSCRP = lOG_DSCRP;
	}
	public String getLOG_TYPE() {
		return LOG_TYPE;
	}
	public void setLOG_TYPE(String lOG_TYPE) {
		LOG_TYPE = lOG_TYPE;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getLOG_UPD_MSG() {
		return LOG_UPD_MSG;
	}
	public void setLOG_UPD_MSG(String lOG_UPD_MSG) {
		LOG_UPD_MSG = lOG_UPD_MSG;
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
	public String getLOG_INFO_USER_AGNET() {
		return LOG_INFO_USER_AGNET;
	}
	public void setLOG_INFO_USER_AGNET(String lOG_INFO_USER_AGNET) {
		LOG_INFO_USER_AGNET = lOG_INFO_USER_AGNET;
	}
}
