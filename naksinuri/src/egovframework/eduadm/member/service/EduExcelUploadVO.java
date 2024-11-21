package egovframework.eduadm.member.service;

import java.util.List;

import egovframework.eduadm.main.service.EduDefaultVO;

public class EduExcelUploadVO extends EduDefaultVO {
	
	/**edu_excel_upload_tb**/
	private String EU_SN;				//엑셀업로드 리스트 고유번호  
	private String REG_MBR_ID;          //생성아이디                         
	private String UPD_MBR_ID;          //수정아이디                         
	private String REG_DT;          	//생성일
	private String CONFM_ST;			//승인여부
	private String CONFM_REG_DT;        //승인신청일
	private String CONFM_UPD_DT;        //승인일
	private String EU_RANDOM_SN;		//엑셀업로드 리스트 고유 랜덤키
	private String EU_DTL_ST;
	
	/**edu_excel_upload_dtl_tb**/
	private String EUD_SN;				//엑셀업로드상세테이블 고유번호                                                                                        
	private String SIDO_NM;         	//시도 이름                                                                                                                
	private String SIGNGU_NM;       	//시군구 이름                                                                                                             
	private String REG_NUM_CD;         	//신고번호(어선) / 허가(등록)번호(낚시터)                                                                             
	private String SHIP_CD;            	//어선부가정보-어선번호                                                                                                    
	private String DTL_NM;             	//어선명 / 낚시터명                                                                                                           
	private String DTL_LICENSE_NM;  	//사업자구분이름(낚시어선업자 선주/선원 , 개인사업자 , 법인사업자 , 해기사면허 소지 여부)  
	private String SHIP_LICENSE;       	//어선부가정보-해기사면허,낚시터부가정보-업구분                                                                 
	private String MBR_NM;             	//이름                                                                                                                             
	private String MBR_BIRTH;          	//생년월일                                                                                                                       
	private String MBR_HP;             	//휴대폰                                                                                                                          
	private String MBR_ADDR;			//주소                                                                                                                         
	private String MBR_ZIPCD;			//우편번호                                                                                                                       
	private String MBR_TEL;            	//전화번호
	private String MBR_ID;				//아이디
	private String CHANGE_INFO;			//변경정보
	private String DTL_CD;				//구분(낚시터:CIDN010200/어선:CIDN010300)
	private String EUD_CONFM_ST;		//승인시정보반영여부
	private String MBR_EDU_RSPNBER_TYPE;//법인업자 대표자, 교육책임자 구분
	
	//DB 외
	private List<EduExcelUploadVO> resultList;
	private String MBR_REG_TYPE_CD;
	private String MBR_SIDO_CD;
	private String MBR_SIGNGU_CD;
	private String MBR_ST;
	private String DTL_SN;				//회원상세정보 일련번호
	private String DTL_LICENSE_CD;		//사업자구분코드(낚시어선업자 선주/선원 , 개인사업자 , 법인사업자 , 해기사면허 소지 여부)
	private String MBR_JOIN_YN;			//회원여부
	private String MBR_TRGT_CD;			//(관리자)교육대상 제한코드
	private String MBR_CNT;				//총 신청자 수
	private String APRV_CNT;			//승인된 신청자 수
	
	
	public String getMBR_EDU_RSPNBER_TYPE() {
		return MBR_EDU_RSPNBER_TYPE;
	}
	public void setMBR_EDU_RSPNBER_TYPE(String mBR_EDU_RSPNBER_TYPE) {
		MBR_EDU_RSPNBER_TYPE = mBR_EDU_RSPNBER_TYPE;
	}
	public String getEUD_CONFM_ST() {
		return EUD_CONFM_ST;
	}
	public void setEUD_CONFM_ST(String eUD_CONFM_ST) {
		EUD_CONFM_ST = eUD_CONFM_ST;
	}
	public String getDTL_CD() {
		return DTL_CD;
	}
	public void setDTL_CD(String dTL_CD) {
		DTL_CD = dTL_CD;
	}
	public String getMBR_TRGT_CD() {
		return MBR_TRGT_CD;
	}
	public void setMBR_TRGT_CD(String mBR_TRGT_CD) {
		MBR_TRGT_CD = mBR_TRGT_CD;
	}
	public String getMBR_JOIN_YN() {
		return MBR_JOIN_YN;
	}
	public void setMBR_JOIN_YN(String mBR_JOIN_YN) {
		MBR_JOIN_YN = mBR_JOIN_YN;
	}
	public String getDTL_LICENSE_CD() {
		return DTL_LICENSE_CD;
	}
	public void setDTL_LICENSE_CD(String dTL_LICENSE_CD) {
		DTL_LICENSE_CD = dTL_LICENSE_CD;
	}
	public String getDTL_SN() {
		return DTL_SN;
	}
	public void setDTL_SN(String dTL_SN) {
		DTL_SN = dTL_SN;
	}
	public String getCHANGE_INFO() {
		return CHANGE_INFO;
	}
	public void setCHANGE_INFO(String cHANGE_INFO) {
		CHANGE_INFO = cHANGE_INFO;
	}
	public String getMBR_REG_TYPE_CD() {
		return MBR_REG_TYPE_CD;
	}
	public void setMBR_REG_TYPE_CD(String mBR_REG_TYPE_CD) {
		MBR_REG_TYPE_CD = mBR_REG_TYPE_CD;
	}
	public String getMBR_SIDO_CD() {
		return MBR_SIDO_CD;
	}
	public void setMBR_SIDO_CD(String mBR_SIDO_CD) {
		MBR_SIDO_CD = mBR_SIDO_CD;
	}
	public String getMBR_SIGNGU_CD() {
		return MBR_SIGNGU_CD;
	}
	public void setMBR_SIGNGU_CD(String mBR_SIGNGU_CD) {
		MBR_SIGNGU_CD = mBR_SIGNGU_CD;
	}
	public String getMBR_ST() {
		return MBR_ST;
	}
	public void setMBR_ST(String mBR_ST) {
		MBR_ST = mBR_ST;
	}
	public String getCONFM_ST() {
		return CONFM_ST;
	}
	public void setCONFM_ST(String cONFM_ST) {
		CONFM_ST = cONFM_ST;
	}
	public String getCONFM_REG_DT() {
		return CONFM_REG_DT;
	}
	public void setCONFM_REG_DT(String cONFM_REG_DT) {
		CONFM_REG_DT = cONFM_REG_DT;
	}
	public String getCONFM_UPD_DT() {
		return CONFM_UPD_DT;
	}
	public void setCONFM_UPD_DT(String cONFM_UPD_DT) {
		CONFM_UPD_DT = cONFM_UPD_DT;
	}
	public List<EduExcelUploadVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<EduExcelUploadVO> resultList) {
		this.resultList = resultList;
	}
	public String getEUD_SN() {
		return EUD_SN;
	}
	public void setEUD_SN(String eUD_SN) {
		EUD_SN = eUD_SN;
	}
	public String getEU_RANDOM_SN() {
		return EU_RANDOM_SN;
	}
	public void setEU_RANDOM_SN(String eU_RANDOM_SN) {
		EU_RANDOM_SN = eU_RANDOM_SN;
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
	public String getEU_SN() {
		return EU_SN;
	}
	public void setEU_SN(String eU_SN) {
		EU_SN = eU_SN;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getDTL_LICENSE_NM() {
		return DTL_LICENSE_NM;
	}
	public void setDTL_LICENSE_NM(String dTL_LICENSE_NM) {
		DTL_LICENSE_NM = dTL_LICENSE_NM;
	}
	public String getSIDO_NM() {
		return SIDO_NM;
	}
	public void setSIDO_NM(String sIDO_NM) {
		SIDO_NM = sIDO_NM;
	}
	public String getSIGNGU_NM() {
		return SIGNGU_NM;
	}
	public void setSIGNGU_NM(String sIGNGU_NM) {
		SIGNGU_NM = sIGNGU_NM;
	}
	public String getMBR_ADDR() {
		return MBR_ADDR;
	}
	public void setMBR_ADDR(String mBR_ADDR) {
		MBR_ADDR = mBR_ADDR;
	}
	public String getMBR_ZIPCD() {
		return MBR_ZIPCD;
	}
	public void setMBR_ZIPCD(String mBR_ZIPCD) {
		MBR_ZIPCD = mBR_ZIPCD;
	}
	public String getREG_NUM_CD() {
		return REG_NUM_CD;
	}
	public void setREG_NUM_CD(String rEG_NUM_CD) {
		REG_NUM_CD = rEG_NUM_CD;
	}
	public String getSHIP_CD() {
		return SHIP_CD;
	}
	public void setSHIP_CD(String sHIP_CD) {
		SHIP_CD = sHIP_CD;
	}
	public String getDTL_NM() {
		return DTL_NM;
	}
	public void setDTL_NM(String dTL_NM) {
		DTL_NM = dTL_NM;
	}
	public String getSHIP_LICENSE() {
		return SHIP_LICENSE;
	}
	public void setSHIP_LICENSE(String sHIP_LICENSE) {
		SHIP_LICENSE = sHIP_LICENSE;
	}
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMBR_BIRTH() {
		return MBR_BIRTH;
	}
	public void setMBR_BIRTH(String mBR_BIRTH) {
		MBR_BIRTH = mBR_BIRTH;
	}
	public String getMBR_HP() {
		return MBR_HP;
	}
	public void setMBR_HP(String mBR_HP) {
		MBR_HP = mBR_HP;
	}
	public String getMBR_TEL() {
		return MBR_TEL;
	}
	public void setMBR_TEL(String mBR_TEL) {
		MBR_TEL = mBR_TEL;
	}
	public String getEU_DTL_ST() {
		return EU_DTL_ST;
	}
	public void setEU_DTL_ST(String eU_DTL_ST) {
		EU_DTL_ST = eU_DTL_ST;
	}
	public String getMBR_CNT() {
		return MBR_CNT;
	}
	public void setMBR_CNT(String mBR_CNT) {
		MBR_CNT = mBR_CNT;
	}
	public String getAPRV_CNT() {
		return APRV_CNT;
	}
	public void setAPRV_CNT(String aPRV_CNT) {
		APRV_CNT = aPRV_CNT;
	}
	
}
