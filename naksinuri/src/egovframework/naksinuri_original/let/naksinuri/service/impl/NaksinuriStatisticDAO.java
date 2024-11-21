package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.FunnelsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriStatisticDAO")
public class NaksinuriStatisticDAO extends EgovAbstractDAO{
	
	//유입 경로 관련
	public List<FunnelsVO> get_funnels() {
		return (List<FunnelsVO>) list("get_funnels");
	}
	
	

	public void insert_staticInfo(NaksinuriStatisticVO staticVO) {
		insert("insert_staticInfo",staticVO);
		
	}

	public NaksinuriStatisticVO get_visitcnt(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_visitcnt",staticVO);
	
	}

	public NaksinuriStatisticVO get_pagecnt(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_pagecnt",staticVO);
	}

	public List<NaksinuriStatisticVO> static_boardcnt(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) select("get_boardcnt",staticVO);
	}

	public List<NaksinuriStatisticVO> get_boardtop10(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("get_boardtop10",staticVO);
	}

	public NaksinuriStatisticVO get_visitcnt_day(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_visitcnt_day",staticVO);
	}

	public NaksinuriStatisticVO get_pagecnt_day(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_pagecnt_day",staticVO);
	}

	public List<NaksinuriStatisticVO> get_boardtop10_day(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("get_boardtop10_day",staticVO);
	}

	public NaksinuriStatisticVO get_visitcnt_month(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_visitcnt_month",staticVO);
	}

	public NaksinuriStatisticVO get_pagecnt_month(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_pagecnt_month");
	}

	public List<NaksinuriStatisticVO> get_boardtop10_month(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("get_boardtop10_month",staticVO);
	}

	
//	직접입력
	public NaksinuriStatisticVO get_visitcnt_myself(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_visitcnt_myself",staticVO);
	}

	public NaksinuriStatisticVO get_pagecnt_myself(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_pagecnt_myself",staticVO);
	}

	public List<NaksinuriStatisticVO> get_boardtop10_self(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("get_boardtop10_self",staticVO);
	}

	public List<NaksinuriStatisticVO> getBrowserInfo(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getBrowserInfo",staticVO);
	}

	public List<NaksinuriStatisticVO> getBrowserInfoday(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getBrowserInfoday",staticVO);
	}

	public List<NaksinuriStatisticVO> getBrowserInfomonth(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getBrowserInfomonth",staticVO);
	}

	public List<NaksinuriStatisticVO> getBrowserInfomyself(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getBrowserInfomyself",staticVO);
	}

	public List<NaksinuriStatisticVO> getfishCntInfo(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getfishCntInfo",staticVO);
	}

	public List<NaksinuriStatisticVO> getlessonCntInfo(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getlessonCntInfo",staticVO);
	}

	public List<NaksinuriStatisticVO> getshareCntInfo(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getshareCntInfo",staticVO);
	}

	public List<NaksinuriStatisticVO> getsosigCntInfo(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsosigCntInfo",staticVO);
	}

	public List<NaksinuriStatisticVO> getpolicyCntInfo(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getpolicyCntInfo",staticVO);
	}

	public List<NaksinuriStatisticVO> getsurveyCntInfo(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsurveyCntInfo",staticVO);
	}

	public List<NaksinuriStatisticVO> getsurveyCntInfoday(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsurveyCntInfoday",staticVO);
	}

	public List<NaksinuriStatisticVO> getpolicyCntInfoday(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getpolicyCntInfoday",staticVO);
	}

	public List<NaksinuriStatisticVO> getsosigCntInfoday(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsosigCntInfoday",staticVO);
	}

	public List<NaksinuriStatisticVO> getshareCntInfoday(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getshareCntInfoday",staticVO);
	}

	public List<NaksinuriStatisticVO> getlessonCntInfoday(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getlessonCntInfoday",staticVO);
	}

	public List<NaksinuriStatisticVO> getfishCntInfoday(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getfishCntInfoday",staticVO);
	}
//	30일
	public List<NaksinuriStatisticVO> getfishCntInfomonth(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getfishCntInfomonth",staticVO);
	}

	public List<NaksinuriStatisticVO> getlessonCntInfomonth(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getlessonCntInfomonth",staticVO);
	}

	public List<NaksinuriStatisticVO> getshareCntInfomonth(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getshareCntInfomonth",staticVO);
	}

	public List<NaksinuriStatisticVO> getsosigCntInfomonth(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsosigCntInfomonth",staticVO);
	}

	public List<NaksinuriStatisticVO> getpolicyCntInfomonth(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getpolicyCntInfomonth",staticVO);
	}

	public List<NaksinuriStatisticVO> getsurveyCntInfomonth(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsurveyCntInfomonth",staticVO);
	}

	
//	직접입력
	public List<NaksinuriStatisticVO> getsurveyCntInfoself(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsurveyCntInfoself",staticVO);
	}

	public List<NaksinuriStatisticVO> getpolicyCntInfoself(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getpolicyCntInfoself",staticVO);
	}

	public List<NaksinuriStatisticVO> getsosigCntInfoself(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsosigCntInfoself",staticVO);
	}

	public List<NaksinuriStatisticVO> getshareCntInfoself(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getshareCntInfoself",staticVO);
	}

	public List<NaksinuriStatisticVO> getlessonCntInfoself(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getlessonCntInfoself",staticVO);
	}

	public List<NaksinuriStatisticVO> getfishCntInfoself(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getfishCntInfoself",staticVO);
	}

	public NaksinuriStatisticVO getmobile_prcnt(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("getmobile_prcnt",staticVO);
	}

	public NaksinuriStatisticVO getpc_prcnt(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("getpc_prcnt",staticVO);
	}


	public NaksinuriStatisticVO getpc_prcntself(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("getpc_prcntself",staticVO);
	}

	public NaksinuriStatisticVO getmobile_prcntself(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("getmobile_prcntself",staticVO);
	}

	public NaksinuriStatisticVO getmost_pc(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("getmost_pc",staticVO);
	}

	public NaksinuriStatisticVO getmost_mobile(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("getmost_mobile",staticVO);
	}

	public List<NaksinuriStatisticVO> getpcOStop5(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getpcOStop5",staticVO);
	}

	public List<NaksinuriStatisticVO> getmobileOStop5(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getmobileOStop5",staticVO);
	}

	public NaksinuriStatisticVO getmost_pcself(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getmost_pcself",staticVO);
	}

	public NaksinuriStatisticVO getmost_mobileself(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getmost_mobileself",staticVO);
	}

	public NaksinuriStatisticVO getmost_mobilemonth(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getmost_mobilemonth",staticVO);
	}

	public NaksinuriStatisticVO getmost_pcmonth(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getmost_pcmonth",staticVO);
	}

	public NaksinuriStatisticVO getmost_pcday(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getmost_pcday",staticVO);
	}

	public NaksinuriStatisticVO getmost_mobileday(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getmost_mobileday",staticVO);
	}

	public NaksinuriStatisticVO getmobile_prcntday(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getmobile_prcntday",staticVO);
	}

	public NaksinuriStatisticVO getpc_prcntday(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getpc_prcntday",staticVO);
	}

	public NaksinuriStatisticVO getmobile_prcntmonth(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getmobile_prcntmonth",staticVO);
	}

	public NaksinuriStatisticVO getpc_prcntmonth(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select ("getpc_prcntmonth",staticVO);
	}

	public List<NaksinuriStatisticVO> getpcOStop5day(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list ("getpcOStop5day",staticVO);
	}

	public List<NaksinuriStatisticVO> getmobileOStop5day(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list ("getmobileOStop5day",staticVO);
	}

	public List<NaksinuriStatisticVO> getpcOStop5month(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list ("getpcOStop5month",staticVO);
	}

	public List<NaksinuriStatisticVO> getmobileOStop5month(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list ("getmobileOStop5month",staticVO);
	}

	public List<NaksinuriStatisticVO> getpcOStop5self(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list ("getpcOStop5self",staticVO);
	}

	public List<NaksinuriStatisticVO> getmobileOStop5self(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list ("getmobileOStop5self",staticVO);
	}

	public List<NaksinuriStatisticVO> getOSInfoweek(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getOSstatic",staticVO);
	}

	public List<NaksinuriStatisticVO> getOSInfoself(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getOSstatic_self",staticVO);
	}

	public List<NaksinuriStatisticVO> getOSInfomonth(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getOSstatic_month",staticVO);
	}

	public List<NaksinuriStatisticVO> getOSInfday(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getOSstatic_day",staticVO);
	}

	public List<NaksinuriStatisticVO> invitlist_week(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("invitlist_week",staticVO);
	}

	public List<NaksinuriStatisticVO> invitlist_day(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("invitlist_day",staticVO);
	}

	public List<NaksinuriStatisticVO> invitlist_month(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("invitlist_month",staticVO);
	}

	public List<NaksinuriStatisticVO> invitlist_self(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("invitlist_self",staticVO);
	}

	public List<NaksinuriStatisticVO> board_top(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("get_board_desc",staticVO);
	}

	public List<NaksinuriStatisticVO> boardday_top(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("get_boardday_desc",staticVO);
	}

	public List<NaksinuriStatisticVO> boardmonth_top(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("get_boardmonth_desc",staticVO);
	}

	public List<NaksinuriStatisticVO> boardself_top(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("get_boardself_desc",staticVO);
	}

	public NaksinuriStatisticVO get_compareviews(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_compareviews",staticVO);
	}

	public NaksinuriStatisticVO get_compareviews2(NaksinuriStatisticVO staticVO) {
		return (NaksinuriStatisticVO) select("get_compareviews2",staticVO);
	}

	public List<NaksinuriStatisticVO> getfishCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getfishCntInfoself_time",staticVO);
	}

	public List<NaksinuriStatisticVO> getlessonCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getlessonCntInfoself_time",staticVO);
	}

	public List<NaksinuriStatisticVO> getshareCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getshareCntInfoself_time",staticVO);
	}

	public List<NaksinuriStatisticVO> getsosigCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsosigCntInfoself_time",staticVO);
	}

	public List<NaksinuriStatisticVO> getpolicyCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getpolicyCntInfoself_time",staticVO);
	}

	public List<NaksinuriStatisticVO> getsurveyCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return (List<NaksinuriStatisticVO>) list("getsurveyCntInfoself_time",staticVO);
	}



	



	

	
	
}
