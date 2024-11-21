package egovframework.naksinuri_original.let.naksinuri.service;

import org.springframework.stereotype.Component;

@Component
public class NaksinuriLogsVO extends CommonVO{
	private String idx;
	private String reg_date;
	private String conn_ip;
	private String user_id;
	private String actions;
	private String searchType;
	private String searchText;
	
	private String date_from;
	private String date_to;
	
	
	private int cnt;


	public String getIdx() {
		return idx;
	}


	public void setIdx(String idx) {
		this.idx = idx;
	}


	public String getReg_date() {
		return reg_date;
	}


	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}


	public String getConn_ip() {
		return conn_ip;
	}


	public void setConn_ip(String conn_ip) {
		this.conn_ip = conn_ip;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getActions() {
		return actions;
	}


	public void setActions(String actions) {
		this.actions = actions;
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


	public String getDate_from() {
		return date_from;
	}


	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}


	public String getDate_to() {
		return date_to;
	}


	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}


	public int getCnt() {
		return cnt;
	}


	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
	
	
}
