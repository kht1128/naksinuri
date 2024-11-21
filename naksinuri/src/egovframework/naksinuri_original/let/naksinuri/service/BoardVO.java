package egovframework.naksinuri_original.let.naksinuri.service;

import org.springframework.stereotype.Component;

@Component
public class BoardVO extends CommonVO{

	private String	bo_sn;
	private String	bo_subject;
	private String	bo_content;
	private String	bo_type;
	private String	bo_cate;
	private String	bo_insert_dt;
	private String	bo_update_dt;
	private String  bo_pass;
	private String  bo_name;
	private String  searchText;
	private String  searchType;
	private String  gallery_list ="gallery";
	private String  bo_main_img;
	private String  bo_sub_img;
	private String  bo_atch_file;
	private String	bo_mov_file;
	private String	bo_pdf_img_file;//pdf 이미지 파일 슬라이더용 
	private String  file_sn;
	private String  file_stre_cours;
	private String  stre_file_nm;
	private String  orignl_file_nm;
	private String  file_extsn;
	private String  file_size;
	private String  bo_info;
	private String  bo_view;
	private String 	co_sn;
	private String 	co_comment;
	private String 	co_name;
	private String 	co_pass;
	private String 	co_insert_dt;
	private String  bo_notice_y;
	private String 	bo_start_dt;
	private String  bo_end_dt;
	private String 	board1;
	private String 	board2;
	private String  bo_datet;
	private String  bo_like;
	private String  bo_email;
	private String  bo_email1;
	private String  bo_email2;
	private String  bo_phone;
	private String  bo_phone1;
	private String  bo_phone2;
	private String  bo_phone3;
	private String  bo_sido;
	private String  bo_trash;
	private String  bo_youtubelink;
	private String 	bo_member_id;
	private int cnt;
	
	//참가자 관련 추가
	private String regit_num;
	private String organizer;
	private String host;
	private String cg_location;
	private String cg_account;
	private String cg_account_name;
	private String entry_start_dt;
	private String entry_end_dt;
	private int entry_cnt;
	private String is_entry_y;
	private String entry_notice;
	private String entry_cash;
	private String ci_type;//참여유형선택
	
	private String is_jacket_offer_y;//구명조끼 지참여부
	private String is_inflow_path_y;//유입경로
	private String is_attend_cause_y;//참가이유
	private String is_naksi_career_y;//낚시경력
	
	private String price_item_name;//요금항목 - 이름
	private String price_item_cash;//요금항목 - 금액
	
	private String is_autosend_sms_y;//문자자동발송허용여부(참가자신청시)
	
	private boolean isSeachAdmin;//관리자페이지조회
	public boolean isSeachAdmin() {
		return isSeachAdmin;
	}
	public void setSeachAdmin(boolean isSeachAdmin) {
		this.isSeachAdmin = isSeachAdmin;
	}
	
	
	public String getBo_member_id() {
		return bo_member_id;
	}
	public void setBo_member_id(String bo_member_id) {
		this.bo_member_id = bo_member_id;
	}
	public String getCi_type() {
		return ci_type;
	}
	public void setCi_type(String ci_type) {
		this.ci_type = ci_type;
	}
	public String getIs_autosend_sms_y() {
		return is_autosend_sms_y;
	}
	public void setIs_autosend_sms_y(String is_autosend_sms_y) {
		this.is_autosend_sms_y = is_autosend_sms_y;
	}
	public String getPrice_item_name() {
		return price_item_name;
	}
	public void setPrice_item_name(String price_item_name) {
		this.price_item_name = price_item_name;
	}
	public String getPrice_item_cash() {
		return price_item_cash;
	}
	public void setPrice_item_cash(String price_item_cash) {
		this.price_item_cash = price_item_cash;
	}
	public String getIs_jacket_offer_y() {
		return is_jacket_offer_y;
	}
	public void setIs_jacket_offer_y(String is_jacket_offer_y) {
		this.is_jacket_offer_y = is_jacket_offer_y;
	}
	public String getIs_inflow_path_y() {
		return is_inflow_path_y;
	}
	public void setIs_inflow_path_y(String is_inflow_path_y) {
		this.is_inflow_path_y = is_inflow_path_y;
	}
	public String getIs_attend_cause_y() {
		return is_attend_cause_y;
	}
	public void setIs_attend_cause_y(String is_attend_cause_y) {
		this.is_attend_cause_y = is_attend_cause_y;
	}
	public String getIs_naksi_career_y() {
		return is_naksi_career_y;
	}
	public void setIs_naksi_career_y(String is_naksi_career_y) {
		this.is_naksi_career_y = is_naksi_career_y;
	}
	public String getEntry_cash() {
		return entry_cash;
	}
	public void setEntry_cash(String entry_cash) {
		this.entry_cash = entry_cash;
	}
	public String getRegit_num() {
		return regit_num;
	}
	public void setRegit_num(String regit_num) {
		this.regit_num = regit_num;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getCg_location() {
		return cg_location;
	}
	public void setCg_location(String cg_location) {
		this.cg_location = cg_location;
	}
	public String getCg_account() {
		return cg_account;
	}
	public void setCg_account(String cg_account) {
		this.cg_account = cg_account;
	}
	public String getCg_account_name() {
		return cg_account_name;
	}
	public void setCg_account_name(String cg_account_name) {
		this.cg_account_name = cg_account_name;
	}
	//낚시대회 달력 조건 검색
	private String search_str;	
	public String getSearch_str() {
		return search_str;
	}
	public void setSearch_str(String search_str) {
		this.search_str = search_str;
	}
	public String getBo_pdf_img_file() {
		return bo_pdf_img_file;
	}
	public void setBo_pdf_img_file(String bo_pdf_img_file) {
		this.bo_pdf_img_file = bo_pdf_img_file;
	}
	public int getEntry_cnt() {
		return entry_cnt;
	}
	public void setEntry_cnt(int entry_cnt) {
		this.entry_cnt = entry_cnt;
	}
	public String getEntry_start_dt() {
		return entry_start_dt;
	}
	public void setEntry_start_dt(String entry_start_dt) {
		this.entry_start_dt = entry_start_dt;
	}
	public String getEntry_end_dt() {
		return entry_end_dt;
	}
	public void setEntry_end_dt(String entry_end_dt) {
		this.entry_end_dt = entry_end_dt;
	}
	public String getIs_entry_y() {
		return is_entry_y;
	}
	public void setIs_entry_y(String is_entry_y) {
		this.is_entry_y = is_entry_y;
	}
	public String getEntry_notice() {
		return entry_notice;
	}
	public void setEntry_notice(String entry_notice) {
		this.entry_notice = entry_notice;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getBo_trash() {
		return bo_trash;
	}
	public void setBo_trash(String bo_trash) {
		this.bo_trash = bo_trash;
	}
	
	public String getBo_youtubelink() {
		return bo_youtubelink;
	}
	public void setBo_youtubelink(String bo_youtubelink) {
		this.bo_youtubelink = bo_youtubelink;
	}
	
	
	public String getBo_sido() {
		return bo_sido;
	}
	public void setBo_sido(String bo_sido) {
		this.bo_sido = bo_sido;
	}
	public String getBo_email2() {
		return bo_email2;
	}
	public void setBo_email2(String bo_email2) {
		this.bo_email2 = bo_email2;
	}
	public String getBo_phone1() {
		return bo_phone1;
	}
	public void setBo_phone1(String bo_phone1) {
		this.bo_phone1 = bo_phone1;
	}
	public String getBo_phone2() {
		return bo_phone2;
	}
	public void setBo_phone2(String bo_phone2) {
		this.bo_phone2 = bo_phone2;
	}
	public String getBo_phone3() {
		return bo_phone3;
	}
	public void setBo_phone3(String bo_phone3) {
		this.bo_phone3 = bo_phone3;
	}
	public String getBo_email1() {
		return bo_email1;
	}
	public void setBo_email1(String bo_email1) {
		this.bo_email1 = bo_email1;
	}
	
	public String getBo_email() {
		return bo_email;
	}
	public void setBo_email(String bo_email) {
		this.bo_email = bo_email;
	}
	public String getBo_phone() {
		return bo_phone;
	}
	public void setBo_phone(String bo_phone) {
		this.bo_phone = bo_phone;
	}
	public String getBo_like() {
		return bo_like;
	}
	public void setBo_like(String bo_like) {
		this.bo_like = bo_like;
	}
	public String getBo_view() {
		return bo_view;
	}
	public void setBo_view(String bo_view) {
		this.bo_view = bo_view;
	}
	public String getBo_update_dt() {
		return bo_update_dt;
	}
	public void setBo_update_dt(String bo_update_dt) {
		this.bo_update_dt = bo_update_dt;
	}
	
	public String getBo_sn() {
		return bo_sn;
	}
	public void setBo_sn(String bo_sn) {
		this.bo_sn = bo_sn;
	}
	public String getBo_subject() {
		return bo_subject;
	}
	public void setBo_subject(String bo_subject) {
		this.bo_subject = bo_subject;
	}
	public String getBo_content() {
		return bo_content;
	}
	public void setBo_content(String bo_content) {
		this.bo_content = bo_content;
	}
	public String getBo_type() {
		return bo_type;
	}
	public void setBo_type(String bo_type) {
		this.bo_type = bo_type;
	}
	public String getBo_cate() {
		return bo_cate;
	}
	public void setBo_cate(String bo_cate) {
		this.bo_cate = bo_cate;
	}
	public String getBo_insert_dt() {
		return bo_insert_dt;
	}
	public void setBo_insert_dt(String bo_insert_dt) {
		this.bo_insert_dt = bo_insert_dt;
	}
	public String getBo_pass() {
		return bo_pass;
	}
	public void setBo_pass(String bo_pass) {
		this.bo_pass = bo_pass;
	}
	public String getBo_name() {
		return bo_name;
	}
	public void setBo_name(String bo_name) {
		this.bo_name = bo_name;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getGallery_list() {
		return gallery_list;
	}
	public void setGallery_list(String gallery_list) {
		this.gallery_list = gallery_list;
	}
	public String getBo_main_img() {
		return bo_main_img;
	}
	public void setBo_main_img(String bo_main_img) {
		this.bo_main_img = bo_main_img;
	}
	public String getBo_sub_img() {
		return bo_sub_img;
	}
	public void setBo_sub_img(String bo_sub_img) {
		this.bo_sub_img = bo_sub_img;
	}
	public String getBo_atch_file() {
		return bo_atch_file;
	}
	public void setBo_atch_file(String bo_atch_file) {
		this.bo_atch_file = bo_atch_file;
	}
	public String getBo_mov_file() {
		return bo_mov_file;
	}
	public void setBo_mov_file(String bo_mov_file) {
		this.bo_mov_file = bo_mov_file;
	}
	public String getFile_sn() {
		return file_sn;
	}
	public void setFile_sn(String file_sn) {
		this.file_sn = file_sn;
	}
	public String getFile_stre_cours() {
		return file_stre_cours;
	}
	public void setFile_stre_cours(String file_stre_cours) {
		this.file_stre_cours = file_stre_cours;
	}
	public String getStre_file_nm() {
		return stre_file_nm;
	}
	public void setStre_file_nm(String stre_file_nm) {
		this.stre_file_nm = stre_file_nm;
	}
	public String getOrignl_file_nm() {
		return orignl_file_nm;
	}
	public void setOrignl_file_nm(String orignl_file_nm) {
		this.orignl_file_nm = orignl_file_nm;
	}
	public String getFile_extsn() {
		return file_extsn;
	}
	public void setFile_extsn(String file_extsn) {
		this.file_extsn = file_extsn;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getBo_info() {
		return bo_info;
	}
	public void setBo_info(String bo_info) {
		this.bo_info = bo_info;
	}
	public String getCo_sn() {
		return co_sn;
	}
	public void setCo_sn(String co_sn) {
		this.co_sn = co_sn;
	}
	public String getCo_comment() {
		return co_comment;
	}
	public void setCo_comment(String co_comment) {
		this.co_comment = co_comment;
	}
	public String getCo_name() {
		return co_name;
	}
	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}
	public String getCo_pass() {
		return co_pass;
	}
	public void setCo_pass(String co_pass) {
		this.co_pass = co_pass;
	}
	public String getCo_insert_dt() {
		return co_insert_dt;
	}
	public void setCo_insert_dt(String co_insert_dt) {
		this.co_insert_dt = co_insert_dt;
	}
	public String getBo_notice_y() {
		return bo_notice_y;
	}
	public void setBo_notice_y(String bo_notice_y) {
		this.bo_notice_y = bo_notice_y;
	}
	public String getBo_start_dt() {
		return bo_start_dt;
	}
	public void setBo_start_dt(String bo_start_dt) {
		this.bo_start_dt = bo_start_dt;
	}
	public String getBo_end_dt() {
		return bo_end_dt;
	}
	public void setBo_end_dt(String bo_end_dt) {
		this.bo_end_dt = bo_end_dt;
	}
	public void setBoard1(String board1) {
		this.board1 = board1;
	}
	public void setBoard2(String board2) {
		this.board2 = board2;
	}
	public void setBo_datet(String bo_datet) {
		this.bo_datet = bo_datet;
	}
	



}
