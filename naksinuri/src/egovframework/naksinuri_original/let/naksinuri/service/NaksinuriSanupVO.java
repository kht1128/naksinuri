package egovframework.naksinuri_original.let.naksinuri.service;

import org.springframework.stereotype.Component;

@Component
public class NaksinuriSanupVO extends CommonVO{
	
	private String oid;
	private String pid;
	private String ptype;
	private String wsess;

	private String san_sn;
	private String san_divsion;
	private String san_aag;
	private String san_zogu;
	private String san_media;
	private String san_sell;
	private String san_chool;
	private String san_inprov;
	private String san_buisnessman;
	private String san_name;
	private String san_tel;
	private String san_content;
	private String san_img;

	private String san_simg;
	private String san_address;
	private String san_address1;
	private String san_address2;
	private String san_address3;
	private String san_item;
	private String san_phone;
	private String san_homepage;
	private String san_date;
	private String san_update;
	private String san_tel1;
	private String san_tel2;
	private String san_tel3;
	private String san_phone1;
	private String san_phone2;
	private String san_phone3;
	private String san_trash;
	private String san_status;	
	private int san_views;
	
	private String searchType;
	private String searchSido;
	private String searchGugun;
	private String searchText;
	private String searchText1;
	private String searchText2;
	private String file_sn;
	private String orignl_file_nm;
	private String file_size;
	private String searchDate1;
	private String searchDate2;
	
	private int cnt;
	private String save_status;
	private String client_ipaddr;
	private String atch_file_id;
	private String getFile_extsn;
	private String file_stre_cours;
	private String file_extsn;
	private String stre_file_nm;
	private String file_cn;
	private String file_hit;

	private String preg_pid;
	private String preg_sess;
	private String preg_stat;
	private String preg_date;
	private String san_list_yn;
	private String san_hardness;
	private String san_latitude;
	private String stat;
	private String Osan_sn;	
	
	private String p_file_sn;	
	private String p_atch_file_id;	
	private String p_orignl_file_nm;
	private String psan_sn;	
	private String makesan_sn;
	private String p_file_stre_cours;
	private String p_file_size;
	private String p_file_cn;
	private String p_file_extsn;
	private String p_stre_file_nm;	
	private String adres_la;
	private String adres_lo;
	private String cmpnm_nm;

	private String adres_la1;
	private String adres_lo1;
	private String adres_la2;
	private String adres_lo2;
	
	
	private boolean isSeachAdmin;//관리자페이지조회
	public boolean isSeachAdmin() {
		return isSeachAdmin;
	}
	public void setSeachAdmin(boolean isSeachAdmin) {
		this.isSeachAdmin = isSeachAdmin;
	}
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getWsess() {
		return wsess;
	}
	public void setWsess(String wsess) {
		this.wsess = wsess;
	}

	
	public int getSan_views() {
		return san_views;
	}

	public void setSan_views(int san_views) {
		this.san_views = san_views;
	}

	public String getSan_status() {
		return san_status;
	}

	public void setSan_status(String san_status) {
		this.san_status = san_status;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchDate1() {
		return searchDate1;
	}

	public void setSearchDate1(String searchDate1) {
		this.searchDate1 = searchDate1;
	}

	public String getSearchDate2() {
		return searchDate2;
	}

	public void setSearchDate2(String searchDate2) {
		this.searchDate2 = searchDate2;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getSan_trash() {
		return san_trash;
	}
	public void setSan_trash(String san_trash) {
		this.san_trash = san_trash;
	}

	public String getSan_aag() {
		return san_aag;
	}
	public void setSan_aag(String san_aag) {
		this.san_aag = san_aag;
	}

	public String getSan_sn() {
		return san_sn;
	}
	public void setSan_sn(String san_sn) {
		this.san_sn = san_sn;
	}

	public String getSan_divsion() {
		return san_divsion;
	}
	public void setSan_divsion(String san_divsion) {
		this.san_divsion = san_divsion;
	}
	
	public String getSan_buisnessman() {
		return san_buisnessman;
	}
	public void setSan_buisnessman(String san_buisnessman) {
		this.san_buisnessman = san_buisnessman;
	}
	public String getSan_img() {
		return san_img;
	}
	public void setSan_img(String san_img) {
		this.san_img = san_img;
	}
	public String getSan_simg() {
		return san_simg;
	}
	public void setSan_simg(String san_simg) {
		this.san_simg = san_simg;
	}
	public String getSan_name() {
		return san_name;
	}
	public void setSan_name(String san_name) {
		this.san_name = san_name;
	}
	public String getSan_tel() {
		return san_tel;
	}
	public void setSan_tel(String san_tel) {
		this.san_tel = san_tel;
	}
	public String getSan_content() {
		return san_content;
	}
	public void setSan_content(String san_content) {
		this.san_content = san_content;
	}
	public String getSan_address() {
		return san_address;
	}
	public void setSan_address(String san_address) {
		this.san_address = san_address;
	}
	public String getSan_address1() {
		return san_address1;
	}
	public void setSan_address1(String san_address1) {
		this.san_address1 = san_address1;
	}
	public String getSan_address2() {
		return san_address2;
	}
	public void setSan_address2(String san_address2) {
		this.san_address2 = san_address2;
	}
	public String getSan_address3() {
		return san_address3;
	}
	public void setSan_address3(String san_address3) {
		this.san_address3 = san_address3;
	}
	public String getSan_item() {
		return san_item;
	}
	public void setSan_item(String san_item) {
		this.san_item = san_item;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchSido() {
		return searchSido;
	}
	public void setSearchSido(String searchSido) {
		this.searchSido = searchSido;
	}
	public String getSearchGugun() {
		return searchGugun;
	}
	public void setSearchGugun(String searchGugun) {
		this.searchGugun = searchGugun;
	}
	
	public String getSearchText1() {
		return searchText1;
	}

	public void setSearchText1(String searchText1) {
		this.searchText1 = searchText1;
	}

	public String getSearchText2() {
		return searchText2;
	}
	public void setSearchText2(String searchText2) {
		this.searchText2 = searchText2;
	}
	public String getSan_phone() {
		return san_phone;
	}
	public void setSan_phone(String san_phone) {
		this.san_phone = san_phone;
	}
	public String getSan_homepage() {
		return san_homepage;
	}
	public void setSan_homepage(String san_homepage) {
		this.san_homepage = san_homepage;
	}
	public String getSan_zogu() {
		return san_zogu;
	}
	public void setSan_zogu(String san_zogu) {
		this.san_zogu = san_zogu;
	}
	public String getSan_media() {
		return san_media;
	}
	public void setSan_media(String san_media) {
		this.san_media = san_media;
	}
	public String getSan_sell() {
		return san_sell;
	}
	public void setSan_sell(String san_sell) {
		this.san_sell = san_sell;
	}
	public String getSan_chool() {
		return san_chool;
	}
	public void setSan_chool(String san_chool) {
		this.san_chool = san_chool;
	}
	public String getSan_inprov() {
		return san_inprov;
	}
	public void setSan_inprov(String san_inprov) {
		this.san_inprov = san_inprov;
	}
	public void setFile_sn(String file_sn) {
		this.file_sn = file_sn;
	}
	public String getFile_sn() {
		return file_sn;
	}
	public String getSan_date() {
		return san_date;
	}
	public void setSan_date(String san_date) {
		this.san_date = san_date;
	}
	public String getSan_update() {
		return san_update;
	}
	public void setSan_update(String san_update) {
		this.san_update = san_update;
	}
	public String getSan_tel1() {
		return san_tel1;
	}
	public void setSan_tel1(String san_tel1) {
		this.san_tel1 = san_tel1;
	}
	public String getSan_tel2() {
		return san_tel2;
	}
	public void setSan_tel2(String san_tel2) {
		this.san_tel2 = san_tel2;
	}
	public String getSan_tel3() {
		return san_tel3;
	}
	public void setSan_tel3(String san_tel3) {
		this.san_tel3 = san_tel3;
	}
	public String getSan_phone1() {
		return san_phone1;
	}
	public void setSan_phone1(String san_phone1) {
		this.san_phone1 = san_phone1;
	}
	public String getSan_phone2() {
		return san_phone2;
	}
	public void setSan_phone2(String san_phone2) {
		this.san_phone2 = san_phone2;
	}
	public String getSan_phone3() {
		return san_phone3;
	}
	public void setSan_phone3(String san_phone3) {
		this.san_phone3 = san_phone3;
	}
	public String getFile_size() {
		return file_size;
	}

	public void setSave_status(String save_status) {
		this.save_status = save_status;
	}
	public String getSave_status() {
		return save_status;
	}
	public void setClient_ipaddr(String client_ipaddr) {
		this.client_ipaddr = client_ipaddr;
	}
	public String getClient_ipaddr() {
		return client_ipaddr;
	}

	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
	public String getAtch_file_id() {
		return atch_file_id;
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
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	
	public void setStre_file_nm(String stre_file_nm) {
		this.stre_file_nm = stre_file_nm;
	}
	public String getStre_file_nm() {
		return stre_file_nm;
	}

	public void setFile_cn(String file_cn) {
		this.file_cn = file_cn;
	}
	public String getFile_cn() {
		return file_cn;
	}
	public void setFile_hit(String file_hit) {
		this.file_hit = file_hit;
	}
	public String getFile_hit() {
		return file_hit;
	}

	public String getPreg_pid() {
		return preg_pid;
	}
	public void setPreg_pid(String preg_pid) {
		this.preg_pid = preg_pid;
	}
	public String getPreg_sess() {
		return preg_sess;
	}
	public void setPreg_sess(String preg_sess) {
		this.preg_sess = preg_sess;
	}
	public String getPreg_stat() {
		return preg_stat;
	}
	public void setPreg_stat(String preg_stat) {
		this.preg_stat = preg_stat;
	}
	public String getPreg_date() {
		return preg_date;
	}
	public void setPreg_date(String preg_date) {
		this.preg_date = preg_date;
	}	

	public String getSan_latitude() {
		return san_latitude;
	}
	public void setSan_latitude(String san_latitude) {
		this.san_latitude = san_latitude;
	}	
	public String getSan_hardness() {
		return san_hardness;
	}
	public void setSan_hardness(String san_hardness) {
		this.san_hardness = san_hardness;
	}	
	public String getSan_list_yn() {
		return san_list_yn;
	}
	public void setSan_list_yn(String san_list_yn) {
		this.san_list_yn = san_list_yn;
	}

	public void setOsan_sn(String Osan_sn) {
		this.Osan_sn = Osan_sn;
	}	
	public String getOsan_sn() {
		return Osan_sn;
	}

	public void setP_file_sn(String p_file_sn) {
		this.p_file_sn = p_file_sn;
	}
	public String getP_file_sn() {
		return p_file_sn;
	}
	public String getP_atch_file_id() {
		return p_atch_file_id;
	}
	public void setP_atch_file_id(String p_atch_file_id) {
		this.p_atch_file_id = p_atch_file_id;
	}

	public String getP_orignl_file_nm() {
		return p_orignl_file_nm;
	}
	public void setP_orignl_file_nm(String p_orignl_file_nm) {
		this.p_orignl_file_nm = p_orignl_file_nm;
	}	

	public String getPsan_sn() {
		return psan_sn;
	}
	public void setPsan_sn(String psan_sn) {
		this.psan_sn = psan_sn;
	}

	
	public String getP_file_stre_cours() {
		return p_file_stre_cours;
	}
	public void setP_file_stre_cours(String p_file_stre_cours) {
		this.p_file_stre_cours = p_file_stre_cours;
	}
	public String getP_stre_file_nm() {
		return p_stre_file_nm;
	}
	public void setP_stre_file_nm(String p_stre_file_nm) {
		this.p_stre_file_nm = p_stre_file_nm;
	}
	public String getP_file_extsn() {
		return p_file_extsn;
	}
	public void setP_file_extsn(String p_file_extsn) {
		this.p_file_extsn = p_file_extsn;
	}
	public String getP_file_cn() {
		return p_file_cn;
	}
	public void setP_file_cn(String p_file_cn) {
		this.p_file_cn = p_file_cn;
	}
	public String getP_file_size() {
		return p_file_size;
	}
	public void setP_file_size(String p_file_size) {
		this.p_file_size = p_file_size;
	}
	public String getAdres_la() {
		return adres_la;
	}
	public void setAdres_la(String adres_la) {
		this.adres_la = adres_la;
	}
	public String getAdres_lo() {
		return adres_lo;
	}
	public void setAdres_lo(String adres_lo) {
		this.adres_lo = adres_lo;
	}
	public String getCmpnm_nm() {
		return cmpnm_nm;
	}
	public void setCmpnm_nm(String cmpnm_nm) {
		this.cmpnm_nm = cmpnm_nm;
	}
	public String getAdres_la1() {
		return adres_la1;
	}
	public void setAdres_la1(String adres_la1) {
		this.adres_la1 = adres_la1;
	}
	public String getAdres_lo1() {
		return adres_lo1;
	}
	public void setAdres_lo1(String adres_lo1) {
		this.adres_lo1 = adres_lo1;
	}
	public String getAdres_la2() {
		return adres_la2;
	}
	public void setAdres_la2(String adres_la2) {
		this.adres_la2 = adres_la2;
	}
	public String getAdres_lo2() {
		return adres_lo2;
	}
	public void setAdres_lo2(String adres_lo2) {
		this.adres_lo2 = adres_lo2;
	}
	
	

}
