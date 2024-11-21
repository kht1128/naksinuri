package egovframework.naksinuri_original.let.naksinuri.service;

import org.springframework.stereotype.Component;

@Component
public class NaksinuriShortcutLinkVO extends CommonVO {
	private String uniq_key;
	private String url_web;
	private String url_mobile;
	private String bo_sn;
	private String qna_type;
	private int is_board_type;
	private int page_num;
	
	
	
	public int getPage_num() {
		return page_num;
	}
	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}
	public int getIs_board_type() {
		return is_board_type;
	}
	public void setIs_board_type(int is_board_type) {
		this.is_board_type = is_board_type;
	}
	public String getUniq_key() {
		return uniq_key;
	}
	public void setUniq_key(String uniq_key) {
		this.uniq_key = uniq_key;
	}
	
	public String getUrl_web() {
		return url_web;
	}
	public void setUrl_web(String url_web) {
		this.url_web = url_web;
	}
	public String getUrl_mobile() {
		return url_mobile;
	}
	public void setUrl_mobile(String url_mobile) {
		this.url_mobile = url_mobile;
	}
	public String getBo_sn() {
		return bo_sn;
	}
	public void setBo_sn(String bo_sn) {
		this.bo_sn = bo_sn;
	}
	public String getQna_type() {
		return qna_type;
	}
	public void setQna_type(String qna_type) {
		this.qna_type = qna_type;
	}
	
}
