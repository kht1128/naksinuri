package egovframework.cti.main.websocket;

public class CtiWebSocketVO {
	private String MBR_ID;
	private String CTI_ID;
	private String CTI_TELNO;
	private String SID;
	private String CONTROLL;
	private String ACTION;
	private String MSG;
	
	public String getCONTROLL() {
		return CONTROLL;
	}
	public void setCONTROLL(String cONTROLL) {
		CONTROLL = cONTROLL;
	}
	public String getACTION() {
		return ACTION;
	}
	public void setACTION(String aCTION) {
		ACTION = aCTION;
	}
	public String getMSG() {
		return MSG;
	}
	public void setMSG(String mSG) {
		MSG = mSG;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getCTI_ID() {
		return CTI_ID;
	}
	public void setCTI_ID(String cTI_ID) {
		CTI_ID = cTI_ID;
	}
	public String getCTI_TELNO() {
		return CTI_TELNO;
	}
	public void setCTI_TELNO(String cTI_TELNO) {
		CTI_TELNO = cTI_TELNO;
	}
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	
}
