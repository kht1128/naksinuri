package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.all.login.service.LoginVO;
import egovframework.naksinuri_original.let.naksinuri.service.FunnelsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriStatisticVO;
import egovframework.seadm.analytics.service.AnalyticsAdmService;
import egovframework.seadm.analytics.service.AnalyticsAdmVO;
import egovframework.utils.PublicUtils;

@Service("NaksinuriStaticService")
public class NaksinuriStatisticServiceImpl implements NaksinuriStatisticService{

	private static final Logger LOGGER = LoggerFactory.getLogger(NaksinuriStatisticServiceImpl.class);
	
	@Resource(name = "NaksinuriStatisticDAO")
	private NaksinuriStatisticDAO dao;
	
	@Resource(name = "AnalyticsAdmService")
	private AnalyticsAdmService analyticsAdmService;
	
	
	//유입 경로 관련
	@Override
	public List<FunnelsVO> get_funnels() {
		return dao.get_funnels();
	}
	
	

	@Override
	public void get_statisticInfo(NaksinuriStatisticVO staticVO, final HttpServletRequest request) {
		LOGGER.debug("통계반영 ------------------------- !!");
		LOGGER.debug("getClient_ip : "+staticVO.getClient_ip());
		LOGGER.debug("getStatistic_os : "+staticVO.getStatistic_os());
		LOGGER.debug("getBrowser : "+staticVO.getBrowser());
		LOGGER.debug("getBo_type : "+staticVO.getBo_type());
		LOGGER.debug("getBo_name : "+staticVO.getBo_name());
		LOGGER.debug("getPath : "+staticVO.getPath());
		LOGGER.debug("getCategory_type : "+staticVO.getCategory_type());
		LOGGER.debug("getCategory_name : "+staticVO.getCategory_name());
		LOGGER.debug("getPath_type : "+staticVO.getPath_type());
		LOGGER.debug("getCategory_group_type : "+staticVO.getCategory_group_type());
		LOGGER.debug("getCategory_group_name : "+staticVO.getCategory_group_name());
		LOGGER.debug("---------------------------------");
		dao.insert_staticInfo(staticVO);
		
		//-----------------------------------------------------------
		// 접속 통계 반영
		//-----------------------------------------------------------
		try {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			AnalyticsAdmVO analyticsAdmVO = new AnalyticsAdmVO();
			String pagename = "(낚시누리)";
			if(staticVO!=null && staticVO.getCategory_group_name()!=null && staticVO.getCategory_group_name().length()!=0) {
				pagename += ""+staticVO.getCategory_group_name();
			}
			if(staticVO!=null && staticVO.getBo_name()!=null && staticVO.getBo_name().length()!=0) {
				if(staticVO.getCategory_group_name()!=null && staticVO.getCategory_group_name().length()!=0) {
					pagename += "-"+staticVO.getBo_name();
				} else {
					pagename += ""+staticVO.getBo_name();
				}
			}
			if(staticVO!=null && staticVO.getCategory_name()!=null && staticVO.getCategory_name().length()!=0) {
				pagename += "-"+staticVO.getCategory_name();
			}			
			analyticsAdmVO.setvisitpagenm(pagename);
			if(loginVO != null){
				analyticsAdmVO.setvisitid(loginVO.getMBR_ID());
			}
			analyticsAdmVO.setvisitip(PublicUtils.getClientIpAddr(request));
			analyticsAdmVO.setvisitbrowser(request.getHeader("User-Agent"));
			analyticsAdmVO.setvisitrefer(request.getHeader("REFERER"));
			analyticsAdmVO.setvisiturl(request.getRequestURL().toString());
			analyticsAdmService.insertAnalytics(analyticsAdmVO);
		} catch(Exception e) { 
			LOGGER.debug("[fail analytics record] "+e.toString());
		}
		//-----------------------------------------------------------
		
	}


	@Override
	public NaksinuriStatisticVO get_visitcnt(NaksinuriStatisticVO staticVO) {
		return dao.get_visitcnt(staticVO);
	}


	@Override
	public NaksinuriStatisticVO get_pagecnt(NaksinuriStatisticVO staticVO) {
		return dao.get_pagecnt(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> static_boardcnt(NaksinuriStatisticVO staticVO) {
		return dao.static_boardcnt(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> get_boardtop10(NaksinuriStatisticVO staticVO) {
		return dao.get_boardtop10(staticVO);
	}


	@Override
	public NaksinuriStatisticVO get_visitcnt_day(NaksinuriStatisticVO staticVO) {
		return dao.get_visitcnt_day(staticVO);
	}


	@Override
	public NaksinuriStatisticVO get_pagecnt_day(NaksinuriStatisticVO staticVO) {
		return dao.get_pagecnt_day(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> get_boardtop10_day(NaksinuriStatisticVO staticVO) {
		return dao.get_boardtop10_day(staticVO);
	}


	@Override
	public NaksinuriStatisticVO get_visitcnt_month(NaksinuriStatisticVO staticVO) {
		return dao.get_visitcnt_month(staticVO);
	}


	@Override
	public NaksinuriStatisticVO get_pagecnt_month(NaksinuriStatisticVO staticVO) {
		return dao.get_pagecnt_month(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> get_boardtop10_month(NaksinuriStatisticVO staticVO) {
		return dao.get_boardtop10_month(staticVO);

	}


	@Override
	public NaksinuriStatisticVO get_visitcnt_myself(NaksinuriStatisticVO staticVO) {
		return dao.get_visitcnt_myself(staticVO);
	}


	@Override
	public NaksinuriStatisticVO get_pagecnt_myself(NaksinuriStatisticVO staticVO) {
		return dao.get_pagecnt_myself(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> get_boardtop10_self(NaksinuriStatisticVO staticVO) {
		return dao.get_boardtop10_self(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getBrowserInfo(NaksinuriStatisticVO staticVO) {
		return dao.getBrowserInfo(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getBrowserInfoday(NaksinuriStatisticVO staticVO) {
		return dao.getBrowserInfoday(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getBrowserInfomonth(NaksinuriStatisticVO staticVO) {
		return dao.getBrowserInfomonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getBrowserInfomyself(NaksinuriStatisticVO staticVO) {
		return dao.getBrowserInfomyself(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getfishCntInfo(NaksinuriStatisticVO staticVO) {
		return dao.getfishCntInfo(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getlessonCntInfo(NaksinuriStatisticVO staticVO) {
		return dao.getlessonCntInfo(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getshareCntInfo(NaksinuriStatisticVO staticVO) {
		return dao.getshareCntInfo(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsosigCntInfo(NaksinuriStatisticVO staticVO) {
		return dao.getsosigCntInfo(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpolicyCntInfo(NaksinuriStatisticVO staticVO) {
		return dao.getpolicyCntInfo(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsurveyCntInfo(NaksinuriStatisticVO staticVO) {
		return dao.getsurveyCntInfo(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsurveyCntInfoday(NaksinuriStatisticVO staticVO) {
		return dao.getsurveyCntInfoday(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpolicyCntInfoday(NaksinuriStatisticVO staticVO) {
		return dao.getpolicyCntInfoday(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsosigCntInfoday(NaksinuriStatisticVO staticVO) {
		return dao.getsosigCntInfoday(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getshareCntInfoday(NaksinuriStatisticVO staticVO) {
		return dao.getshareCntInfoday(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getlessonCntInfoday(NaksinuriStatisticVO staticVO) {
		return dao.getlessonCntInfoday(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getfishCntInfoday(NaksinuriStatisticVO staticVO) {
		return dao.getfishCntInfoday(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getfishCntInfomonth(NaksinuriStatisticVO staticVO) {
		return dao.getfishCntInfomonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getlessonCntInfomonth(NaksinuriStatisticVO staticVO) {
		return dao.getlessonCntInfomonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getshareCntInfomonth(NaksinuriStatisticVO staticVO) {
		return dao.getshareCntInfomonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsosigCntInfomonth(NaksinuriStatisticVO staticVO) {
		return dao.getsosigCntInfomonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpolicyCntInfomonth(NaksinuriStatisticVO staticVO) {
		return dao.getpolicyCntInfomonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsurveyCntInfomonth(NaksinuriStatisticVO staticVO) {
		return dao.getsurveyCntInfomonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getfishCntInfoself(NaksinuriStatisticVO staticVO) {
		return dao.getfishCntInfoself(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getlessonCntInfoself(NaksinuriStatisticVO staticVO) {
		return dao.getlessonCntInfoself(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getshareCntInfoself(NaksinuriStatisticVO staticVO) {
		return dao.getshareCntInfoself(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsosigCntInfoself(NaksinuriStatisticVO staticVO) {
		return dao.getsosigCntInfoself(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpolicyCntInfoself(NaksinuriStatisticVO staticVO) {
		return dao.getpolicyCntInfoself(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsurveyCntInfoself(NaksinuriStatisticVO staticVO) {
		return dao.getsurveyCntInfoself(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmobile_prcnt(NaksinuriStatisticVO staticVO) {
		return dao.getmobile_prcnt(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getpc_prcnt(NaksinuriStatisticVO staticVO) {
		return dao.getpc_prcnt(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmobile_prcntself(NaksinuriStatisticVO staticVO) {
		return dao.getmobile_prcntself(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getpc_prcntself(NaksinuriStatisticVO staticVO) {
		return dao.getpc_prcntself(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmost_pc(NaksinuriStatisticVO staticVO) {
		return dao.getmost_pc(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmost_mobile(NaksinuriStatisticVO staticVO) {
		return dao.getmost_mobile(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpcOStop5(NaksinuriStatisticVO staticVO) {
		return dao.getpcOStop5(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getmobileOStop5(NaksinuriStatisticVO staticVO) {
		return dao.getmobileOStop5(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmost_pcself(NaksinuriStatisticVO staticVO) {
		return dao.getmost_pcself(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmost_mobileself(NaksinuriStatisticVO staticVO) {
		return dao.getmost_mobileself(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmost_mobilemonth(NaksinuriStatisticVO staticVO) {
		return dao.getmost_mobilemonth(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmost_pcmonth(NaksinuriStatisticVO staticVO) {
		return dao.getmost_pcmonth(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmost_pcday(NaksinuriStatisticVO staticVO) {
		return dao.getmost_pcday(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmost_mobileday(NaksinuriStatisticVO staticVO) {
		return dao.getmost_mobileday(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmobile_prcntday(NaksinuriStatisticVO staticVO) {
		return dao.getmobile_prcntday(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getpc_prcntday(NaksinuriStatisticVO staticVO) {
		return dao.getpc_prcntday(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getmobile_prcntmonth(NaksinuriStatisticVO staticVO) {
		return dao.getmobile_prcntmonth(staticVO);
	}


	@Override
	public NaksinuriStatisticVO getpc_prcntmonth(NaksinuriStatisticVO staticVO) {
		return dao.getpc_prcntmonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpcOStop5day(NaksinuriStatisticVO staticVO) {
		return dao.getpcOStop5day(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getmobileOStop5day(NaksinuriStatisticVO staticVO) {
		return dao.getmobileOStop5day(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpcOStop5month(NaksinuriStatisticVO staticVO) {
		return dao.getpcOStop5month(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getmobileOStop5month(NaksinuriStatisticVO staticVO) {
		return dao.getmobileOStop5month(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpcOStop5self(NaksinuriStatisticVO staticVO) {
		return dao.getpcOStop5self(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getmobileOStop5self(NaksinuriStatisticVO staticVO) {
		return dao.getmobileOStop5self(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getOSInfoweek(NaksinuriStatisticVO staticVO) {
		return dao.getOSInfoweek(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getOSInfoself(NaksinuriStatisticVO staticVO) {
		return dao.getOSInfoself(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getOSInfomonth(NaksinuriStatisticVO staticVO) {
		return dao.getOSInfomonth(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getOSInfday(NaksinuriStatisticVO staticVO) {
		return dao.getOSInfday(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> invitlist_week(NaksinuriStatisticVO staticVO) {
		return dao.invitlist_week(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> invitlist_day(NaksinuriStatisticVO staticVO) {
		return dao.invitlist_day(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> invitlist_month(NaksinuriStatisticVO staticVO) {
		return dao.invitlist_month(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> invitlist_self(NaksinuriStatisticVO staticVO) {
		return dao.invitlist_self(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> board_top(NaksinuriStatisticVO staticVO) {
		return dao.board_top(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> boardday_top(NaksinuriStatisticVO staticVO) {
		return dao.boardday_top(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> boardmonth_top(NaksinuriStatisticVO staticVO) {
		return dao.boardmonth_top(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> boardself_top(NaksinuriStatisticVO staticVO) {
		return dao.boardself_top(staticVO);
	}


	@Override
	public NaksinuriStatisticVO get_compareviews(NaksinuriStatisticVO staticVO) {
		return dao.get_compareviews(staticVO);
	}


	@Override
	public NaksinuriStatisticVO get_compareviews2(NaksinuriStatisticVO staticVO) {
		return dao.get_compareviews2(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getfishCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return dao.getfishCntInfoself_time(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getlessonCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return dao.getlessonCntInfoself_time(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getshareCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return dao.getshareCntInfoself_time(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsosigCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return dao.getsosigCntInfoself_time(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getpolicyCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return dao.getpolicyCntInfoself_time(staticVO);
	}


	@Override
	public List<NaksinuriStatisticVO> getsurveyCntInfoself_time(NaksinuriStatisticVO staticVO) {
		return dao.getsurveyCntInfoself_time(staticVO);
	}







	

	

	

	



}
