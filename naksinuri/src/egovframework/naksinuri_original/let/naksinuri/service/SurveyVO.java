package egovframework.naksinuri_original.let.naksinuri.service;

public class SurveyVO extends CommonVO{
	
	
	private int Percentage;
	
	public int getPercentage() {
		return Percentage;
	}
	public void setPercentage(int percentage) {
		Percentage = percentage;
	}
	
private int selector2;
	
	public int getSelector2() {
		return selector2;
	}
	public void setSelector2(int selector2) {
		this.selector2 = selector2;
	}
	
	// 설문 메인
	private String sv_id;
	private String sv_subject;
	private String sv_strt_dt;
	private String sv_end_dt;
	private String sv_item_cnt;
	private String sv_show;
	private String sv_private_info;
	private String SV_LC;	//설문조사 띄울 위치
	
	// 등록일자
	private String reg_date;
	private String reg_mb_id;
	
	// 수정일자
	private String mod_date;
	private String mod_mb_id;
	private String bo_trash;
	
	// 질문사항
	private String sq_id;
	private String sq_order;
	private String svq_num;
	private String svq_mxcnt;
	private String svq_type;
	private String svq_subject;
	private String svq_cnt;
	private String nDelCode;
	
	// 질문 아이템
	private String sqi_id;
	private String svq_item_num;
	private String svq_item_txt;
	private String svq_ck_cnt;
	private String sqi_etc;
	
	// 답변 정보
	private String asi_id;
	private String sva_date;
	private String sva_ip;
	private String as_name;
	private String as_email;
	private String as_tel;
	private String HMBR_SN;	//이수내역번호
	private String ETC_1;	//CRS_SN
	private String ETC_2;	//기타2
	private String ETC_3;	//기타3
	private String ASI_CRS_SN;	//naksinuri_survey_answer_info 교육정보1단계번호
	private String ASI_HMBR_SN;	//naksinuri_survey_answer_info 이수내역번호
	
	// 답변
	private String sva_txt;
	private String sva_mb_ip;
	
	private String searchType;
	private String searchText;
	
	private String survey_anscnt;
	private String selector;
	
	//ip로 체크 설문유무
	private int used;
	
	//엑셀 데이터용
	private String item1;
	private String item2;
	private String item3;
	private String item4;
	private String item5;
	
	
	
	public String getASI_CRS_SN() {
		return ASI_CRS_SN;
	}
	public void setASI_CRS_SN(String aSI_CRS_SN) {
		ASI_CRS_SN = aSI_CRS_SN;
	}
	public String getASI_HMBR_SN() {
		return ASI_HMBR_SN;
	}
	public void setASI_HMBR_SN(String aSI_HMBR_SN) {
		ASI_HMBR_SN = aSI_HMBR_SN;
	}
	public String getHMBR_SN() {
		return HMBR_SN;
	}
	public void setHMBR_SN(String hMBR_SN) {
		HMBR_SN = hMBR_SN;
	}
	public String getETC_1() {
		return ETC_1;
	}
	public void setETC_1(String eTC_1) {
		ETC_1 = eTC_1;
	}
	public String getETC_2() {
		return ETC_2;
	}
	public void setETC_2(String eTC_2) {
		ETC_2 = eTC_2;
	}
	public String getETC_3() {
		return ETC_3;
	}
	public void setETC_3(String eTC_3) {
		ETC_3 = eTC_3;
	}
	public String getSV_LC() {
		return SV_LC;
	}
	public void setSV_LC(String sV_LC) {
		SV_LC = sV_LC;
	}
	public String getItem5() {
		return item5;
	}
	public void setItem5(String item5) {
		this.item5 = item5;
	}
	public String getItem1() {
		return item1;
	}
	public void setItem1(String item1) {
		this.item1 = item1;
	}
	public String getItem2() {
		return item2;
	}
	public void setItem2(String item2) {
		this.item2 = item2;
	}
	public String getItem3() {
		return item3;
	}
	public void setItem3(String item3) {
		this.item3 = item3;
	}
	public String getItem4() {
		return item4;
	}
	public void setItem4(String item4) {
		this.item4 = item4;
	}
	public String getAs_name() {
		return as_name;
	}
	public void setAs_name(String as_name) {
		this.as_name = as_name;
	}
	public String getAs_email() {
		return as_email;
	}
	public void setAs_email(String as_email) {
		this.as_email = as_email;
	}
	public String getAs_tel() {
		return as_tel;
	}
	public void setAs_tel(String as_tel) {
		this.as_tel = as_tel;
	}
	public String getSv_private_info() {
		return sv_private_info;
	}
	public void setSv_private_info(String sv_private_info) {
		this.sv_private_info = sv_private_info;
	}
	public String getSv_show() {
		return sv_show;
	}
	public void setSv_show(String sv_show) {
		this.sv_show = sv_show;
	}
	public String getSv_id() {
		return sv_id;
	}
	public void setSv_id(String sv_id) {
		this.sv_id = sv_id;
	}	
	public String getSv_subject() {
		return sv_subject;
	}
	public void setSv_subject(String sv_subject) {
		this.sv_subject = sv_subject;
	}
	public String getSv_strt_dt() {
		return sv_strt_dt;
	}
	public void setSv_strt_dt(String sv_strt_dt) {
		this.sv_strt_dt = sv_strt_dt;
	}
	public String getSv_end_dt() {
		return sv_end_dt;
	}
	public void setSv_end_dt(String sv_end_dt) {
		this.sv_end_dt = sv_end_dt;
	}
	public String getSv_item_cnt() {
		return sv_item_cnt;
	}
	public void setSv_item_cnt(String sv_item_cnt) {
		this.sv_item_cnt = sv_item_cnt;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getReg_mb_id() {
		return reg_mb_id;
	}
	public void setReg_mb_id(String reg_mb_id) {
		this.reg_mb_id = reg_mb_id;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public String getMod_mb_id() {
		return mod_mb_id;
	}
	public void setMod_mb_id(String mod_mb_id) {
		this.mod_mb_id = mod_mb_id;
	}
	
	public String getBo_trash() {
		return bo_trash;
	}
	public void setBo_trash(String bo_trash) {
		this.bo_trash = bo_trash;
	}
	public String getSq_id() {
		return sq_id;
	}
	public void setSq_id(String sq_id) {
		this.sq_id = sq_id;
	}
	public String getSq_order() {
		return sq_order;
	}
	public void setSq_order(String sq_order) {
		this.sq_order = sq_order;
	}
	public String getSvq_num() {
		return svq_num;
	}
	public void setSvq_num(String svq_num) {
		this.svq_num = svq_num;
	}
	public String getSvq_mxcnt() {
		return svq_mxcnt;
	}
	public void setSvq_mxcnt(String svq_mxcnt) {
		this.svq_mxcnt = svq_mxcnt;
	}
	public String getSvq_type() {
		return svq_type;
	}
	public void setSvq_type(String svq_type) {
		this.svq_type = svq_type;
	}
	public String getSvq_subject() {
		return svq_subject;
	}
	public void setSvq_subject(String svq_subject) {
		this.svq_subject = svq_subject;
	}
	public String getSvq_cnt() {
		return svq_cnt;
	}
	public void setSvq_cnt(String svq_cnt) {
		this.svq_cnt = svq_cnt;
	}
	public String getnDelCode() {
		return nDelCode;
	}
	public void setnDelCode(String nDelCode) {
		this.nDelCode = nDelCode;
	}
	public String getSqi_id() {
		return sqi_id;
	}
	public void setSqi_id(String sqi_id) {
		this.sqi_id = sqi_id;
	}
	public String getSvq_item_num() {
		return svq_item_num;
	}
	public void setSvq_item_num(String svq_item_num) {
		this.svq_item_num = svq_item_num;
	}
	public String getSvq_item_txt() {
		return svq_item_txt;
	}
	public void setSvq_item_txt(String svq_item_txt) {
		this.svq_item_txt = svq_item_txt;
	}
	public String getSvq_ck_cnt() {
		return svq_ck_cnt;
	}
	public void setSvq_ck_cnt(String svq_ck_cnt) {
		this.svq_ck_cnt = svq_ck_cnt;
	}
	public String getSqi_etc() {
		return sqi_etc;
	}
	public void setSqi_etc(String sqi_etc) {
		this.sqi_etc = sqi_etc;
	}
	public String getAsi_id() {
		return asi_id;
	}
	public void setAsi_id(String asi_id) {
		this.asi_id = asi_id;
	}
	public String getSva_date() {
		return sva_date;
	}
	public void setSva_date(String sva_date) {
		this.sva_date = sva_date;
	}
	public String getSva_ip() {
		return sva_ip;
	}
	public void setSva_ip(String sva_ip) {
		this.sva_ip = sva_ip;
	}
	public String getSva_txt() {
		return sva_txt;
	}
	public void setSva_txt(String sva_txt) {
		this.sva_txt = sva_txt;
	}
	public String getSva_mb_ip() {
		return sva_mb_ip;
	}
	public void setSva_mb_ip(String sva_mb_ip) {
		this.sva_mb_ip = sva_mb_ip;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getSurvey_anscnt() {
		return survey_anscnt;
	}
	public void setSurvey_anscnt(String survey_anscnt) {
		this.survey_anscnt = survey_anscnt;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	
	


	
	
	
	
	
	

}
