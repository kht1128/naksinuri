package egovframework.adm.member.service;

public class AdmCntnAuthorIpVO {

	/** CNTN_AUTHOR_IP_TB **/
	
	private String CNTN_AUTHOR_IP;		// 접속 권한 IP
	private String CNTN_AUTHOR_IP_NM;	// 접속 권한 IP 이름
	private String USE_YN;				// 사용여부
	private String REG_DT;				// 생성 일시
	private String REG_ID;				// 생성 아이디
	private String MDFCN_DT;			// 수정 일시
	private String MDFCN_ID;			// 수정 아이디
	
	
	public String getCNTN_AUTHOR_IP() {
		return CNTN_AUTHOR_IP;
	}
	public void setCNTN_AUTHOR_IP(String cNTN_AUTHOR_IP) {
		CNTN_AUTHOR_IP = cNTN_AUTHOR_IP;
	}
	public String getCNTN_AUTHOR_IP_NM() {
		return CNTN_AUTHOR_IP_NM;
	}
	public void setCNTN_AUTHOR_IP_NM(String cNTN_AUTHOR_IP_NM) {
		CNTN_AUTHOR_IP_NM = cNTN_AUTHOR_IP_NM;
	}
	public String getUSE_YN() {
		return USE_YN;
	}
	public void setUSE_YN(String uSE_YN) {
		USE_YN = uSE_YN;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getREG_ID() {
		return REG_ID;
	}
	public void setREG_ID(String rEG_ID) {
		REG_ID = rEG_ID;
	}
	public String getMDFCN_DT() {
		return MDFCN_DT;
	}
	public void setMDFCN_DT(String mDFCN_DT) {
		MDFCN_DT = mDFCN_DT;
	}
	public String getMDFCN_ID() {
		return MDFCN_ID;
	}
	public void setMDFCN_ID(String mDFCN_ID) {
		MDFCN_ID = mDFCN_ID;
	}
	
	
}
