package egovframework.naksinuri_original.let.naksinuri.service;

import org.springframework.stereotype.Component;

@Component
public class NaksinuriZisikinVO extends CommonVO {
	
	private String  searchText;
	private String  searchType;
	
	private String nuri_q_num;
	private int nuri_q_like;
	private int nuri_q_count;
	private String nuri_q_subject;
	private String nuri_q_content;
	private String nuri_q_date;
	private String nuri_q_writer;
	private String nuri_q_pass;
	private int nuri_view_count;
	
	private int nuri_a_count;
	private int nuri_a_num;
	private int nuri_a_like;
	private String nuri_a_writer;
	private String nuri_a_subject;
	private String nuri_a_content;
	private String nuri_a_date;
	private String nuri_a_pass;
	
	private String zazu_num;
	private String zazu_type;
	private String zazu_ques;
	private String zazu_answ;
	private String zazu_insert_date;
	private String zazu_update_date;
		
	private String zazu_trash;
	private String nuri_q_trash;
	
	private int cnt;
	
	
	
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getNuri_q_trash() {
		return nuri_q_trash;
	}
	public void setNuri_q_trash(String nuri_q_trash) {
		this.nuri_q_trash = nuri_q_trash;
	}
	public String getZazu_trash() {
		return zazu_trash;
	}
	public void setZazu_trash(String zazu_trash) {
		this.zazu_trash = zazu_trash;
	}
	public String getNuri_q_num() {
		return nuri_q_num;
	}
	public void setNuri_q_num(String nuri_q_num) {
		this.nuri_q_num = nuri_q_num;
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

	public int getNuri_q_like() {
		return nuri_q_like;
	}
	public void setNuri_q_like(int nuri_q_like) {
		this.nuri_q_like = nuri_q_like;
	}
	public int getNuri_q_count() {
		return nuri_q_count;
	}
	public void setNuri_q_count(int nuri_q_count) {
		this.nuri_q_count = nuri_q_count;
	}
	public String getNuri_q_subject() {
		return nuri_q_subject;
	}
	public void setNuri_q_subject(String nuri_q_subject) {
		this.nuri_q_subject = nuri_q_subject;
	}
	public String getNuri_q_content() {
		return nuri_q_content;
	}
	public void setNuri_q_content(String nuri_q_content) {
		this.nuri_q_content = nuri_q_content;
	}
	public String getNuri_q_date() {
		return nuri_q_date;
	}
	public void setNuri_q_date(String nuri_q_date) {
		this.nuri_q_date = nuri_q_date;
	}
	public String getNuri_q_writer() {
		return nuri_q_writer;
	}
	public void setNuri_q_writer(String nuri_q_writer) {
		this.nuri_q_writer = nuri_q_writer;
	}
	public String getNuri_q_pass() {
		return nuri_q_pass;
	}
	public void setNuri_q_pass(String nuri_q_pass) {
		this.nuri_q_pass = nuri_q_pass;
	}
	public int getNuri_a_num() {
		return nuri_a_num;
	}
	public void setNuri_a_num(int nuri_a_num) {
		this.nuri_a_num = nuri_a_num;
	}
	public int getNuri_a_like() {
		return nuri_a_like;
	}
	public void setNuri_a_like(int nuri_a_like) {
		this.nuri_a_like = nuri_a_like;
	}
	public String getNuri_a_writer() {
		return nuri_a_writer;
	}
	public void setNuri_a_writer(String nuri_a_writer) {
		this.nuri_a_writer = nuri_a_writer;
	}
	public String getNuri_a_subject() {
		return nuri_a_subject;
	}
	public void setNuri_a_subject(String nuri_a_subject) {
		this.nuri_a_subject = nuri_a_subject;
	}
	public String getNuri_a_content() {
		return nuri_a_content;
	}
	public void setNuri_a_content(String nuri_a_content) {
		this.nuri_a_content = nuri_a_content;
	}
	public String getNuri_a_date() {
		return nuri_a_date;
	}
	public void setNuri_a_date(String nuri_a_date) {
		this.nuri_a_date = nuri_a_date;
	}
	public String getNuri_a_pass() {
		return nuri_a_pass;
	}
	public void setNuri_a_pass(String nuri_a_pass) {
		this.nuri_a_pass = nuri_a_pass;
	}
	public String getZazu_num() {
		return zazu_num;
	}
	public void setZazu_num(String strboIds) {
		this.zazu_num = strboIds;
	}
	public String getZazu_ques() {
		return zazu_ques;
	}
	public void setZazu_ques(String zazu_ques) {
		this.zazu_ques = zazu_ques;
	}
	public String getZazu_answ() {
		return zazu_answ;
	}
	public void setZazu_answ(String zazu_answ) {
		this.zazu_answ = zazu_answ;
	}
	public String getZazu_insert_date() {
		return zazu_insert_date;
	}
	public void setZazu_insert_date(String zazu_insert_date) {
		this.zazu_insert_date = zazu_insert_date;
	}
	public String getZazu_update_date() {
		return zazu_update_date;
	}
	public void setZazu_update_date(String zazu_update_date) {
		this.zazu_update_date = zazu_update_date;
	}
	public String getZazu_type() {
		return zazu_type;
	}
	public void setZazu_type(String zazu_type) {
		this.zazu_type = zazu_type;
	}
	public int getNuri_a_count() {
		return nuri_a_count;
	}
	public int getNuri_view_count() {
		return nuri_view_count;
	}
	public void setNuri_view_count(int nuri_view_count) {
		this.nuri_view_count = nuri_view_count;
	}
}
