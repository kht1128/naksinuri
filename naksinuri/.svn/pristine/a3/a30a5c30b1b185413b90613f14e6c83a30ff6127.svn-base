package egovframework.com.cmm.service;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Class Name : FileVO.java
 * @Description : 파일정보 처리를 위한 VO 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
public class FileVO implements Serializable {
	
    /**
     * 첨부파일 아이디
     */
    public String atchFileId = "";
    /**
     * 생성일자
     */
    public String creatDt = "";
    /**
     * 파일내용
     */
    public String fileCn = "";
    /**
     * 파일확장자
     */
    public String fileExtsn = "";
    /**
     * 파일크기
     */
    public String fileMg = "";
    /**
     * 파일연번
     */
    public String fileSn = "";
    /**
     * 파일저장경로
     */
    public String fileStreCours = "";
    /**
     * 원파일명
     */
    public String orignlFileNm = "";
    /**
     * 저장파일명
     */
    public String streFileNm = "";
    /**
     * 조회 타입 : 2018.12.28 jhkim
     * */
    public String searchCnd = "";
    public String searchWrd = "";
    public String recordCountPerPage = "";
    public String getSearchCnd() { return searchCnd; }
	public void setSearchCnd(String searchCnd) { this.searchCnd = searchCnd; }	
	public String getSearchWrd() { return searchWrd; }
	public void setSearchWrd(String searchWrd) { this.searchWrd = searchWrd; }
	public String getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(String recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	/**
     * 첨부파일 보이기/안보이기 예외 처리 : 2019.01.08 jhkim
     * */
	private String[] FILE_SNs;	
	public String[] getFILE_SNs() {
		String[] ret = null;
		if(FILE_SNs != null) {
			ret = new String[FILE_SNs.length];
			for(int i=0; i<FILE_SNs.length;i++) {
				ret[i] = FILE_SNs[i];
			}
		}
		return ret;
	}
	public void setFILE_SNs(String[] fILE_SNs) {
		//FILE_SNs = fILE_SNs;
		FILE_SNs = Arrays.copyOf(fILE_SNs, fILE_SNs.length);		
	}
	//
	
	
	/**
	 * 승인제 파일 다운로드를 위한 추가
	 * */
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
	public String getDWLD_MAX_CNT() {
		return DWLD_MAX_CNT;
	}
	public void setDWLD_MAX_CNT(String dWLD_MAX_CNT) {
		DWLD_MAX_CNT = dWLD_MAX_CNT;
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
	/**
     * atchFileId attribute를 리턴한다.
     * 
     * @return the atchFileId
     */
    public String getAtchFileId() {
	return atchFileId;
    }

    /**
     * atchFileId attribute 값을 설정한다.
     * 
     * @param atchFileId
     *            the atchFileId to set
     */
    public void setAtchFileId(String atchFileId) {
	this.atchFileId = atchFileId;
    }

    /**
     * creatDt attribute를 리턴한다.
     * 
     * @return the creatDt
     */
    public String getCreatDt() {
	return creatDt;
    }

    /**
     * creatDt attribute 값을 설정한다.
     * 
     * @param creatDt
     *            the creatDt to set
     */
    public void setCreatDt(String creatDt) {
	this.creatDt = creatDt;
    }

    /**
     * fileCn attribute를 리턴한다.
     * 
     * @return the fileCn
     */
    public String getFileCn() {
	return fileCn;
    }

    /**
     * fileCn attribute 값을 설정한다.
     * 
     * @param fileCn
     *            the fileCn to set
     */
    public void setFileCn(String fileCn) {
	this.fileCn = fileCn;
    }

    /**
     * fileExtsn attribute를 리턴한다.
     * 
     * @return the fileExtsn
     */
    public String getFileExtsn() {
	return fileExtsn;
    }

    /**
     * fileExtsn attribute 값을 설정한다.
     * 
     * @param fileExtsn
     *            the fileExtsn to set
     */
    public void setFileExtsn(String fileExtsn) {
	this.fileExtsn = fileExtsn;
    }

    /**
     * fileMg attribute를 리턴한다.
     * 
     * @return the fileMg
     */
    public String getFileMg() {
	return fileMg;
    }

    /**
     * fileMg attribute 값을 설정한다.
     * 
     * @param fileMg
     *            the fileMg to set
     */
    public void setFileMg(String fileMg) {
	this.fileMg = fileMg;
    }

    /**
     * fileSn attribute를 리턴한다.
     * 
     * @return the fileSn
     */
    public String getFileSn() {
	return fileSn;
    }

    /**
     * fileSn attribute 값을 설정한다.
     * 
     * @param fileSn
     *            the fileSn to set
     */
    public void setFileSn(String fileSn) {
	this.fileSn = fileSn;
    }

    /**
     * fileStreCours attribute를 리턴한다.
     * 
     * @return the fileStreCours
     */
    public String getFileStreCours() {
	return fileStreCours;
    }

    /**
     * fileStreCours attribute 값을 설정한다.
     * 
     * @param fileStreCours
     *            the fileStreCours to set
     */
    public void setFileStreCours(String fileStreCours) {
	this.fileStreCours = fileStreCours;
    }

    /**
     * orignlFileNm attribute를 리턴한다.
     * 
     * @return the orignlFileNm
     */
    public String getOrignlFileNm() {
	return orignlFileNm;
    }

    /**
     * orignlFileNm attribute 값을 설정한다.
     * 
     * @param orignlFileNm
     *            the orignlFileNm to set
     */
    public void setOrignlFileNm(String orignlFileNm) {
	this.orignlFileNm = orignlFileNm;
    }

    /**
     * streFileNm attribute를 리턴한다.
     * 
     * @return the streFileNm
     */
    public String getStreFileNm() {
	return streFileNm;
    }

    /**
     * streFileNm attribute 값을 설정한다.
     * 
     * @param streFileNm
     *            the streFileNm to set
     */
    public void setStreFileNm(String streFileNm) {
	this.streFileNm = streFileNm;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
	
}
