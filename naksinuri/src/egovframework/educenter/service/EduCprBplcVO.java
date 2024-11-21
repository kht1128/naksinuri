package egovframework.educenter.service;

import egovframework.eduadm.main.service.EduDefaultVO;

/**edu_cpr_bplc_tb**/
public class EduCprBplcVO extends EduDefaultVO {
	
	private String ECB_SN;           	//교육책임자 지정 고유
	private String ECB_DTL_CD;       	//구분(낚시터:CIDN010200/어선:CIDN010300)=CRS_MBR_CD
	private String ECB_DTL_LICENSE_CD;	//사업자구분코드(낚시어선업자 선주/선원 , 개인사업자 , 법인사업자 , 해기사면허 소지 여부)
	private String ECB_CPR_NM;      	//법인대표자 이름
	private String ECB_CPR_BRTHDY;   	//법인대표자 생년월일 
	private String ECB_CPR_SIDO_CD;  	//법인대표자 시도코드
	private String ECB_CPR_SIGNGU_CD;	//법인대표자 시군구코드
	private String ECB_REG_NUM_CD;   	//신고번호(어선) / 허가(등록)번호(낚시터)
	private String ECB_CPR_DTL_NM;   	//법인대표자 어선명 / 낚시터명
	private String ECB_CPR_HP;       	//법인대표자 핸드폰번호
	private String ECB_CPR_ZIP;      	//법인대표자 우편번호
	private String ECB_CPR_ADDR1;    	//법인대표자 주소
	private String ECB_CPR_ADDR2;    	//법인대표자 상세주소
	private String ECB_EDU_NM;       	//교육책임자 이름
	private String ECB_EDU_BRTHDY;   	//교육책임자 생년월일
	private String ECB_EDU_POSIT;    	//교육책임자 직책,자격     
	private String ECB_EDU_HP;       	//교육책임자 핸드폰번호
	private String ECB_EDU_ZIP;      	//교육책임자 우편번호
	private String ECB_EDU_ADDR1;    	//교육책임자 주소
	private String ECB_EDU_ADDR2;    	//교육책임자 상세주소 
	private String ECB_REG_DT;       	//교육책임자 지정 확인일자
	private String ECB_UPD_DT;       	//교육책임자 지정 수정일자
	private String ECB_REG_ID;       	//교육책임자 지정 확인자 아이디
	private String ECB_UPD_ID;		 	//교육책임자 지정 수정자 아이디
	private String ECB_INDVDL_INFO_ST;	//개인정보동의서(선택) Y:동의, N:미동의
	
	
	public String getECB_DTL_LICENSE_CD() {
		return ECB_DTL_LICENSE_CD;
	}
	public void setECB_DTL_LICENSE_CD(String eCB_DTL_LICENSE_CD) {
		ECB_DTL_LICENSE_CD = eCB_DTL_LICENSE_CD;
	}
	public String getECB_INDVDL_INFO_ST() {
		return ECB_INDVDL_INFO_ST;
	}
	public void setECB_INDVDL_INFO_ST(String eCB_INDVDL_INFO_ST) {
		ECB_INDVDL_INFO_ST = eCB_INDVDL_INFO_ST;
	}
	public String getECB_SN() {
		return ECB_SN;
	}
	public void setECB_SN(String eCB_SN) {
		ECB_SN = eCB_SN;
	}
	public String getECB_DTL_CD() {
		return ECB_DTL_CD;
	}
	public void setECB_DTL_CD(String eCB_DTL_CD) {
		ECB_DTL_CD = eCB_DTL_CD;
	}
	public String getECB_CPR_NM() {
		return ECB_CPR_NM;
	}
	public void setECB_CPR_NM(String eCB_CPR_NM) {
		ECB_CPR_NM = eCB_CPR_NM;
	}
	public String getECB_CPR_BRTHDY() {
		return ECB_CPR_BRTHDY;
	}
	public void setECB_CPR_BRTHDY(String eCB_CPR_BRTHDY) {
		ECB_CPR_BRTHDY = eCB_CPR_BRTHDY;
	}
	public String getECB_CPR_SIDO_CD() {
		return ECB_CPR_SIDO_CD;
	}
	public void setECB_CPR_SIDO_CD(String eCB_CPR_SIDO_CD) {
		ECB_CPR_SIDO_CD = eCB_CPR_SIDO_CD;
	}
	public String getECB_CPR_SIGNGU_CD() {
		return ECB_CPR_SIGNGU_CD;
	}
	public void setECB_CPR_SIGNGU_CD(String eCB_CPR_SIGNGU_CD) {
		ECB_CPR_SIGNGU_CD = eCB_CPR_SIGNGU_CD;
	}
	public String getECB_REG_NUM_CD() {
		return ECB_REG_NUM_CD;
	}
	public void setECB_REG_NUM_CD(String eCB_REG_NUM_CD) {
		ECB_REG_NUM_CD = eCB_REG_NUM_CD;
	}
	public String getECB_CPR_DTL_NM() {
		return ECB_CPR_DTL_NM;
	}
	public void setECB_CPR_DTL_NM(String eCB_CPR_DTL_NM) {
		ECB_CPR_DTL_NM = eCB_CPR_DTL_NM;
	}
	public String getECB_CPR_HP() {
		return ECB_CPR_HP;
	}
	public void setECB_CPR_HP(String eCB_CPR_HP) {
		ECB_CPR_HP = eCB_CPR_HP;
	}
	public String getECB_CPR_ZIP() {
		return ECB_CPR_ZIP;
	}
	public void setECB_CPR_ZIP(String eCB_CPR_ZIP) {
		ECB_CPR_ZIP = eCB_CPR_ZIP;
	}
	public String getECB_CPR_ADDR1() {
		return ECB_CPR_ADDR1;
	}
	public void setECB_CPR_ADDR1(String eCB_CPR_ADDR1) {
		ECB_CPR_ADDR1 = eCB_CPR_ADDR1;
	}
	public String getECB_CPR_ADDR2() {
		return ECB_CPR_ADDR2;
	}
	public void setECB_CPR_ADDR2(String eCB_CPR_ADDR2) {
		ECB_CPR_ADDR2 = eCB_CPR_ADDR2;
	}
	public String getECB_EDU_NM() {
		return ECB_EDU_NM;
	}
	public void setECB_EDU_NM(String eCB_EDU_NM) {
		ECB_EDU_NM = eCB_EDU_NM;
	}
	public String getECB_EDU_BRTHDY() {
		return ECB_EDU_BRTHDY;
	}
	public void setECB_EDU_BRTHDY(String eCB_EDU_BRTHDY) {
		ECB_EDU_BRTHDY = eCB_EDU_BRTHDY;
	}
	public String getECB_EDU_POSIT() {
		return ECB_EDU_POSIT;
	}
	public void setECB_EDU_POSIT(String eCB_EDU_POSIT) {
		ECB_EDU_POSIT = eCB_EDU_POSIT;
	}
	public String getECB_EDU_HP() {
		return ECB_EDU_HP;
	}
	public void setECB_EDU_HP(String eCB_EDU_HP) {
		ECB_EDU_HP = eCB_EDU_HP;
	}
	public String getECB_EDU_ZIP() {
		return ECB_EDU_ZIP;
	}
	public void setECB_EDU_ZIP(String eCB_EDU_ZIP) {
		ECB_EDU_ZIP = eCB_EDU_ZIP;
	}
	public String getECB_EDU_ADDR1() {
		return ECB_EDU_ADDR1;
	}
	public void setECB_EDU_ADDR1(String eCB_EDU_ADDR1) {
		ECB_EDU_ADDR1 = eCB_EDU_ADDR1;
	}
	public String getECB_EDU_ADDR2() {
		return ECB_EDU_ADDR2;
	}
	public void setECB_EDU_ADDR2(String eCB_EDU_ADDR2) {
		ECB_EDU_ADDR2 = eCB_EDU_ADDR2;
	}
	public String getECB_REG_DT() {
		return ECB_REG_DT;
	}
	public void setECB_REG_DT(String eCB_REG_DT) {
		ECB_REG_DT = eCB_REG_DT;
	}
	public String getECB_UPD_DT() {
		return ECB_UPD_DT;
	}
	public void setECB_UPD_DT(String eCB_UPD_DT) {
		ECB_UPD_DT = eCB_UPD_DT;
	}
	public String getECB_REG_ID() {
		return ECB_REG_ID;
	}
	public void setECB_REG_ID(String eCB_REG_ID) {
		ECB_REG_ID = eCB_REG_ID;
	}
	public String getECB_UPD_ID() {
		return ECB_UPD_ID;
	}
	public void setECB_UPD_ID(String eCB_UPD_ID) {
		ECB_UPD_ID = eCB_UPD_ID;
	}

}
