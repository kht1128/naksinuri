package egovframework.eduadm.main.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/***edu_sms_requst_tb***/
public class EduAdmSmsRequstVO extends EduDefaultVO {
	
	private String ESR_SN;				//문자요청 고유번호        
	private String ESR_MBR_ID;    		//문자요청한 ID    
	private String ESR_MBR_HP;    		//문자요청한 핸드폰번호    
	private String ESR_MBR_NM;    		//문자요청한 이름    
	private String ESR_DTL_CD;    		//구분(낚시터:CIDN010200/어선:CIDN010300)=CRS_MBR_CD    
	private String ESR_DTL_LICENSE_CD;  //사업자구분코드(개인사업자 , 법인사업자)
	private String ESR_CRS_SN;			//교육정보1단계번호
	private String ESR_SIDO_CD;			//낚시어선 신고지 시도 코드
	private String ESR_SIGNGU_CD;		//낚시어선 신고지 시군구 코드
	private String ESR_SMS_ST;    		//문자발송여부(0: 실패, 1:성공)    
	private String ESR_REG_DT;    		//문자요청 신청일자    
	private String ESR_UPD_DT;    		//문자요청 수정일자    
	private String ESR_REG_MBR_ID;  	//문자요청 작성자 아이디
	private String ESR_UPD_MBR_ID;  	//문자요청 수정자 아이디
	private String ESR_USE_ST;    		//0:사용안함,1:사용함    
	private String ESR_DEL_ST;    		//0:삭제안함,1:삭제함  
	private String ESR_CMPLT_DT;		//문자 발송 완료일자
	private String ESR_TRGET;			//Y:기존 낚시어선업 신고자,N:20.2.21 이후 최초 낚시어선업 신고자, R:20.2.21 이후 영업정지명을 받은 후 재개하려는 자
	
	//
	private String RMNDR_CRS_NM;		//교육1 이름
	private String ESR_SIDO_NM;    		//낚시어선 신고지 시도  
	private String ESR_SIGNGU_NM;   	//낚시어선 신고지 시군구
	
	
	public String getESR_TRGET() {
		return ESR_TRGET;
	}
	public void setESR_TRGET(String eSR_TRGET) {
		ESR_TRGET = eSR_TRGET;
	}
	public String getESR_CMPLT_DT() {
		return ESR_CMPLT_DT;
	}
	public void setESR_CMPLT_DT(String eSR_CMPLT_DT) {
		ESR_CMPLT_DT = eSR_CMPLT_DT;
	}
	public String getESR_SIDO_CD() {
		return ESR_SIDO_CD;
	}
	public void setESR_SIDO_CD(String eSR_SIDO_CD) {
		ESR_SIDO_CD = eSR_SIDO_CD;
	}
	public String getESR_SIGNGU_CD() {
		return ESR_SIGNGU_CD;
	}
	public void setESR_SIGNGU_CD(String eSR_SIGNGU_CD) {
		ESR_SIGNGU_CD = eSR_SIGNGU_CD;
	}
	public String getESR_SIDO_NM() {
		return ESR_SIDO_NM;
	}
	public void setESR_SIDO_NM(String eSR_SIDO_NM) {
		ESR_SIDO_NM = eSR_SIDO_NM;
	}
	public String getESR_SIGNGU_NM() {
		return ESR_SIGNGU_NM;
	}
	public void setESR_SIGNGU_NM(String eSR_SIGNGU_NM) {
		ESR_SIGNGU_NM = eSR_SIGNGU_NM;
	}
	public String getRMNDR_CRS_NM() {
		return RMNDR_CRS_NM;
	}
	public void setRMNDR_CRS_NM(String rMNDR_CRS_NM) {
		RMNDR_CRS_NM = rMNDR_CRS_NM;
	}
	public String getESR_CRS_SN() {
		return ESR_CRS_SN;
	}
	public void setESR_CRS_SN(String eSR_CRS_SN) {
		ESR_CRS_SN = eSR_CRS_SN;
	}
	public String getESR_DTL_LICENSE_CD() {
		return ESR_DTL_LICENSE_CD;
	}
	public void setESR_DTL_LICENSE_CD(String eSR_DTL_LICENSE_CD) {
		ESR_DTL_LICENSE_CD = eSR_DTL_LICENSE_CD;
	}
	public String getESR_SN() {
		return ESR_SN;
	}
	public void setESR_SN(String eSR_SN) {
		ESR_SN = eSR_SN;
	}
	public String getESR_MBR_ID() {
		return ESR_MBR_ID;
	}
	public void setESR_MBR_ID(String eSR_MBR_ID) {
		ESR_MBR_ID = eSR_MBR_ID;
	}
	public String getESR_MBR_HP() {
		return ESR_MBR_HP;
	}
	public void setESR_MBR_HP(String eSR_MBR_HP) {
		ESR_MBR_HP = eSR_MBR_HP;
	}
	public String getESR_MBR_NM() {
		return ESR_MBR_NM;
	}
	public void setESR_MBR_NM(String eSR_MBR_NM) {
		ESR_MBR_NM = eSR_MBR_NM;
	}
	public String getESR_DTL_CD() {
		return ESR_DTL_CD;
	}
	public void setESR_DTL_CD(String eSR_DTL_CD) {
		ESR_DTL_CD = eSR_DTL_CD;
	}
	public String getESR_SMS_ST() {
		return ESR_SMS_ST;
	}
	public void setESR_SMS_ST(String eSR_SMS_ST) {
		ESR_SMS_ST = eSR_SMS_ST;
	}
	public String getESR_REG_DT() {
		return ESR_REG_DT;
	}
	public void setESR_REG_DT(String eSR_REG_DT) {
		ESR_REG_DT = eSR_REG_DT;
	}
	public String getESR_UPD_DT() {
		return ESR_UPD_DT;
	}
	public void setESR_UPD_DT(String eSR_UPD_DT) {
		ESR_UPD_DT = eSR_UPD_DT;
	}
	public String getESR_REG_MBR_ID() {
		return ESR_REG_MBR_ID;
	}
	public void setESR_REG_MBR_ID(String eSR_REG_MBR_ID) {
		ESR_REG_MBR_ID = eSR_REG_MBR_ID;
	}
	public String getESR_UPD_MBR_ID() {
		return ESR_UPD_MBR_ID;
	}
	public void setESR_UPD_MBR_ID(String eSR_UPD_MBR_ID) {
		ESR_UPD_MBR_ID = eSR_UPD_MBR_ID;
	}
	public String getESR_USE_ST() {
		return ESR_USE_ST;
	}
	public void setESR_USE_ST(String eSR_USE_ST) {
		ESR_USE_ST = eSR_USE_ST;
	}
	public String getESR_DEL_ST() {
		return ESR_DEL_ST;
	}
	public void setESR_DEL_ST(String eSR_DEL_ST) {
		ESR_DEL_ST = eSR_DEL_ST;
	}
	

}
