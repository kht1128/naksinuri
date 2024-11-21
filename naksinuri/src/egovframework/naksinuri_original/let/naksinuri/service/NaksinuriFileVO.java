package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NaksinuriFileVO extends CommonVO{
	
	private String oid;
	private String pid;
	private String ptype;
	private String wsess;
	private String co_nm;
	private String ceo_nm;
	private String co_mimgsrc;
	private String atch_file_id;
	private String atch_filePreview_id;
	private String co_addr_2;
	private String co_fish;
	private String co_sido;
	private int nak_id;
	private String file_sn;
	private String file_cn;
	private String file_stre_cours;
	private String stre_file_nm;
	private String orignl_file_nm;
	private String file_extsn;
	private String file_size;
	private String searchSido;
	private String searchGugun;
	private String searchText;
	private String searchText1;
	private String searchText2;
	private String co_addr2_2;
	private String searchType;
	private String searchBoat;
	private String searchRiver;
	private String searchSea;
	private String search_info;
	private int co_views;
	private List<String> search_info2;
	private String file_hit;
	private String stat;
	private int onak_id;

	private int pnak_id;
	
	private String p_orignl_file_nm;
	private String p_file_sn;
	private String p_atch_file_id;
	private String p_file_stre_cours;
	private String p_file_size;
	private String p_file_cn;
	private String p_file_extsn;
	private String p_stre_file_nm;
	
	private String fishing_type;
	private String co_ship_num1;
	private String co_ship_num2;	
	private String co_hphone;
	private String co_2hphone;
	private String co_stm;
	private String co_etm;
	private String bo_size;
	private String bo_spd;
	private String bo_psg;
	private String co_credit;
	private String co_web;
	private String co_fct;
	private String upd_date;
	private String reg_date;
	private String adres_la;
	private String adres_lo;
	private String cmpnm_nm;
	
	private String adres_la1;
	private String adres_lo1;
	private String adres_la2;
	private String adres_lo2;
	
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
	
	public int getCo_views() {
		return co_views;
	}
	public void setCo_views(int co_views) {
		this.co_views = co_views;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getCo_addr_2() {
		return co_addr_2;
	}
	public void setCo_addr_2(String co_addr_2) {
		this.co_addr_2 = co_addr_2;
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
	public String getCo_sido() {
		return co_sido;
	}
	public void setCo_sido(String co_sido) {
		this.co_sido = co_sido;
	}
	public String getAddr_2() {
		return co_addr_2;
	}
	public void setAddr_2(String co_addr_2) {
		this.co_addr_2 = co_addr_2;
	}
	public String getCo_fish() {
		return co_fish;
	}
	public void setCo_fish(String co_fish) {
		this.co_fish = co_fish;
	}
	public String getCo_nm() {
		return co_nm;
	}
	public void setCo_nm(String co_nm) {
		this.co_nm = co_nm;
	}
	public String getCeo_nm() {
		return ceo_nm;
	}
	public void setCeo_nm(String ceo_nm) {
		this.ceo_nm = ceo_nm;
	}	
	public String getCo_mimgsrc() {
		return co_mimgsrc;
	}
	public void setCo_mimgsrc(String co_mimgsrc) {
		this.co_mimgsrc = co_mimgsrc;
	}
	public String getAtch_file_id() {
		return atch_file_id;
	}
	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
	public String getAtch_filePreview_id() {
		return atch_file_id;
	}
	public void setAtch_filePreview_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
	public int getOnak_id() {
		return onak_id;
	}
	public void setOnak_id(int onak_id) {
		this.onak_id = onak_id;
	}
	public int getPnak_id() {
		return pnak_id;
	}
	public void setPnak_id(int pnak_id) {
		this.pnak_id = pnak_id;
	}
	
	public String getFile_sn() {
		return file_sn;
	}
	public void setFile_sn(String file_sn) {
		this.file_sn = file_sn;
	}
	public String getFile_cn() {
		return file_cn;
	}
	public void setFile_cn(String file_cn) {
		this.file_cn = file_cn;
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
	public String getCo_addr2_2() {
		return co_addr2_2;
	}
	public void setCo_addr2_2(String co_addr2_2) {
		this.co_addr2_2 = co_addr2_2;
	}
	public String getSearchBoat() {
		return searchBoat;
	}
	public void setSearchBoat(String searchBoat) {
		this.searchBoat = searchBoat;
	}
	public String getSearchRiver() {
		return searchRiver;
	}
	public void setSearchRiver(String searchRiver) {
		this.searchRiver = searchRiver;
	}
	public String getSearchSea() {
		return searchSea;
	}
	public void setSearchSea(String searchSea) {
		this.searchSea = searchSea;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearch_info() {
		return search_info;
	}
	public void setSearch_info(String search_info) {
		this.search_info = search_info;
	}
	public String getFile_hit() {
		return file_hit;
	}
	public void setFile_hit(String file_hit) {
		this.file_hit = file_hit;
	}
	public List<String> getSearch_info2() {
		return search_info2;
	}
	public void setSearch_info2(List<String> search_info2) {
		this.search_info2 = search_info2;
	}

	public String getP_atch_file_id() {
		return p_atch_file_id;
	}
	public void setP_atch_file_id(String p_atch_file_id) {
		this.p_atch_file_id = p_atch_file_id;
	}
	public String getP_file_sn() {
		return p_file_sn;
	}
	public void setP_file_sn(String p_file_sn) {
		this.p_file_sn = p_file_sn;
	}
	public String getP_orignl_file_nm() {
		return p_orignl_file_nm;
	}
	public void setP_orignl_file_nm(String p_orignl_file_nm) {
		this.p_orignl_file_nm = p_orignl_file_nm;
	}
	public int getNak_id() {
		return nak_id;
	}
	public void setNak_id(int nak_id) {
		this.nak_id = nak_id;
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
	public String getP_file_stre_cours() {
		return p_file_stre_cours;
	}
	public void setP_file_stre_cours(String p_file_stre_cours) {
		this.p_file_stre_cours = p_file_stre_cours;
	}
	public String getFishing_type() {
		return fishing_type;
	}
	public void setFishing_type(String fishing_type) {
		this.fishing_type = fishing_type;
	}
	public String getCo_ship_num1() {
		return co_ship_num1;
	}
	public void setCo_ship_num1(String co_ship_num1) {
		this.co_ship_num1 = co_ship_num1;
	}
	public String getCo_ship_num2() {
		return co_ship_num2;
	}
	public void setCo_ship_num2(String co_ship_num2) {
		this.co_ship_num2 = co_ship_num2;
	}
	public String getCo_hphone() {
		return co_hphone;
	}
	public void setCo_hphone(String co_hphone) {
		this.co_hphone = co_hphone;
	}
	public String getCo_2hphone() {
		return co_2hphone;
	}
	public void setCo_2hphone(String co_2hphone) {
		this.co_2hphone = co_2hphone;
	}
	public String getCo_stm() {
		return co_stm;
	}
	public void setCo_stm(String co_stm) {
		this.co_stm = co_stm;
	}
	public String getCo_etm() {
		return co_etm;
	}
	public void setCo_etm(String co_etm) {
		this.co_etm = co_etm;
	}
	public String getBo_size() {
		return bo_size;
	}
	public void setBo_size(String bo_size) {
		this.bo_size = bo_size;
	}
	public String getBo_spd() {
		return bo_spd;
	}
	public void setBo_spd(String bo_spd) {
		this.bo_spd = bo_spd;
	}
	public String getBo_psg() {
		return bo_psg;
	}
	public void setBo_psg(String bo_psg) {
		this.bo_psg = bo_psg;
	}
	public String getCo_credit() {
		return co_credit;
	}
	public void setCo_credit(String co_credit) {
		this.co_credit = co_credit;
	}
	public String getCo_web() {
		return co_web;
	}
	public void setCo_web(String co_web) {
		this.co_web = co_web;
	}
	public String getCo_fct() {
		return co_fct;
	}
	public void setCo_fct(String co_fct) {
		this.co_fct = co_fct;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
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
