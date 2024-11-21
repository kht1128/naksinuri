package egovframework.naksinuri_original.let.naksinuri.service;

public class NaksinuriEventVO extends CommonVO {
	private String  evn_no;
	private String  evn_pass;
	private String  evn_writer;
	private String  evn_startdate;
	private String  evn_enddate;
	private String  evn_ancdate;
	private String  evn_viewhit;
	private String  evn_img;
	private String  evn_sta;
	private String  evn_reply_writer;
	private String  evn_reply_pass;
	private String  evn_reply;
	private String  evn_subject;
	private String  evn_insert_dt;
	private String  evn_update_dt;
	private String  evn_view;
	private String  evn_atch_file;
	private String  evn_content;
	private String  evn_sub_img;
	private String  evn_main_img;
	private String  file_sn;
	private String  file_stre_cours;
	private String  orignl_file_nm;
	private String  file_extsn;
	private String  file_size;
	private String  evn_info;
	private String  evn_like;
	private String  searchText;
	private String  searchType;
	private String  gallery_list ="gallery";
//	이벤트댓글 => event+comment = eco 
	private String  eco_no;
	private String  eco_content;
	private String  eco_name;
	private String  eco_pass;
	private String  eco_insert_dt;
	private String  eco_update_dt;
	private String  evn_trash;
	private int cnt;
	//공모전 댓글 이벤트
	private String eco_gongmo_hp;
	private String eco_gongmo_url;
	//댓글 이벤트 구분자
	private String eco_cate;
	
			
	public String getEco_cate() {
		return eco_cate;
	}
	public void setEco_cate(String eco_cate) {
		this.eco_cate = eco_cate;
	}
	public String getEco_gongmo_hp() {
		return eco_gongmo_hp;
	}
	public void setEco_gongmo_hp(String eco_gongmo_hp) {
		this.eco_gongmo_hp = eco_gongmo_hp;
	}
	public String getEco_gongmo_url() {
		return eco_gongmo_url;
	}
	public void setEco_gongmo_url(String eco_gongmo_url) {
		this.eco_gongmo_url = eco_gongmo_url;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getEvn_trash() {
		return evn_trash;
	}
	public void setEvn_trash(String evn_trash) {
		this.evn_trash = evn_trash;
	}
	public String getEvn_like() {
		return evn_like;
	}
	public void setEvn_like(String evn_like) {
		this.evn_like = evn_like;
	}
	public String getEco_update_dt() {
		return eco_update_dt;
	}
	public void setEco_update_dt(String eco_update_dt) {
		this.eco_update_dt = eco_update_dt;
	}
	public String getGallery_list() {
		return gallery_list;
	}
	public void setGallery_list(String gallery_list) {
		this.gallery_list = gallery_list;
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
	public String getEvn_update_dt() {
		return evn_update_dt;
	}
	public void setEvn_update_dt(String evn_update_dt) {
		this.evn_update_dt = evn_update_dt;
	}
	public String getEvn_pass() {
		return evn_pass;
	}
	public void setEvn_pass(String evn_pass) {
		this.evn_pass = evn_pass;
	}
	public String getEvn_main_img() {
		return evn_main_img;
	}
	public void setEvn_main_img(String evn_main_img) {
		this.evn_main_img = evn_main_img;
	}

	
	public String getEco_content() {
		return eco_content;
	}
	public void setEco_content(String eco_content) {
		this.eco_content = eco_content;
	}
	public String getEco_name() {
		return eco_name;
	}
	public void setEco_name(String eco_name) {
		this.eco_name = eco_name;
	}
	public String getEco_pass() {
		return eco_pass;
	}
	public void setEco_pass(String eco_pass) {
		this.eco_pass = eco_pass;
	}
	public String getEco_insert_dt() {
		return eco_insert_dt;
	}
	public void setEco_insert_dt(String eco_insert_dt) {
		this.eco_insert_dt = eco_insert_dt;
	}
	public String getEvn_sub_img() {
		return evn_sub_img;
	}
	public void setEvn_sub_img(String evn_sub_img) {
		this.evn_sub_img = evn_sub_img;
	}
	public String getEvn_info() {
		return evn_info;
	}
	public void setEvn_info(String evn_info) {
		this.evn_info = evn_info;
	}
	public String getEvn_content() {
		return evn_content;
	}
	public void setEvn_content(String evn_content) {
		this.evn_content = evn_content;
	}
	public String getEvn_atch_file() {
		return evn_atch_file;
	}
	public void setEvn_atch_file(String evn_atch_file) {
		this.evn_atch_file = evn_atch_file;
	}
	public String getEvn_view() {
		return evn_view;
	}
	public void setEvn_view(String evn_view) {
		this.evn_view = evn_view;
	}
	public String getEvn_insert_dt() {
		return evn_insert_dt;
	}
	public void setEvn_insert_dt(String evn_insert_dt) {
		this.evn_insert_dt = evn_insert_dt;
	}
	public String getEvn_subject() {
		return evn_subject;
	}
	public void setEvn_subject(String evn_subject) {
		this.evn_subject = evn_subject;
	}

	public String getEvn_no() {
		return evn_no;
	}
	public void setEvn_no(String strboIds) {
		this.evn_no = strboIds;
	}
	public String getEvn_writer() {
		return evn_writer;
	}
	public void setEvn_writer(String evn_writer) {
		this.evn_writer = evn_writer;
	}
	public String getEvn_startdate() {
		return evn_startdate;
	}
	public void setEvn_startdate(String evn_startdate) {
		this.evn_startdate = evn_startdate;
	}
	public String getEvn_enddate() {
		return evn_enddate;
	}
	public void setEvn_enddate(String evn_enddate) {
		this.evn_enddate = evn_enddate;
	}
	public String getEvn_ancdate() {
		return evn_ancdate;
	}
	public void setEvn_ancdate(String evn_ancdate) {
		this.evn_ancdate = evn_ancdate;
	}
	public String getEvn_viewhit() {
		return evn_viewhit;
	}
	public void setEvn_viewhit(String evn_viewhit) {
		this.evn_viewhit = evn_viewhit;
	}
	public String getEvn_img() {
		return evn_img;
	}
	public void setEvn_img(String evn_img) {
		this.evn_img = evn_img;
	}
	public String getEvn_sta() {
		return evn_sta;
	}
	public void setEvn_sta(String evn_sta) {
		this.evn_sta = evn_sta;
	}
	public String getEvn_reply_writer() {
		return evn_reply_writer;
	}
	public void setEvn_reply_writer(String evn_reply_writer) {
		this.evn_reply_writer = evn_reply_writer;
	}
	public String getEvn_reply_pass() {
		return evn_reply_pass;
	}
	public void setEvn_reply_pass(String evn_reply_pass) {
		this.evn_reply_pass = evn_reply_pass;
	}
	public String getEvn_reply() {
		return evn_reply;
	}
	public void setEvn_reply(String evn_reply) {
		this.evn_reply = evn_reply;
	}
	public String getEco_no() {
		return eco_no;
	}
	public void setEco_no(String eco_no) {
		this.eco_no = eco_no;
	}
	
	
	
	
} 
