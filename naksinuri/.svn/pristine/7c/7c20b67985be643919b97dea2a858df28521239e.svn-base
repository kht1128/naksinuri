package egovframework.eduadm.analytics.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.eduadm.analytics.service.EduAnalyticsAdmService;
import egovframework.eduadm.analytics.service.EduAnalyticsAdmVO;
import egovframework.eduadm.analytics.service.EduAnalyticsDayVO;
import egovframework.eduadm.analytics.service.EduAnalyticsInfoVO;
import egovframework.eduadm.analytics.service.EduAnalyticsPageVO;
import egovframework.utils.EgovDateUtil;
import egovframework.utils.PublicUtils;

@Controller
public class EduAnalyticsAdmController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EduAnalyticsAdmController.class);
			
	@Resource(name = "EduAnalyticsAdmService")
	private EduAnalyticsAdmService analyticsAdmService;
	
	
	//통계관리   - 사이트현황  ------------------------------------------------
	@RequestMapping(value = "/eduadm/analytics/summary/sitesummary.do")
	public String sitesummaryAdmList(@ModelAttribute("EduAnalyticsAdmVO") EduAnalyticsAdmVO EduAnalyticsAdmVO,
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = EduAnalyticsAdmVO.getSTR_DT();
		String end_dt = EduAnalyticsAdmVO.getEND_DT();
		if(selectDays==null || selectDays.length()==0) {//최초진입
			selectDays = "7";
			end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
			str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
		} else {
			if(selectDays.equals("direct")) {//직접입력 인 경우
				if(str_dt==null || str_dt.length()==0) {
					str_dt = "2019-01-01 00:00:00";
				}
				if(end_dt==null || end_dt.length()==0) {
					end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				}
			} else {
				end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
			}
		}
		int days = EgovDateUtil.getDaysDiff(sealifeUtils.changePatternString(str_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"), sealifeUtils.changePatternString(end_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar prevendcal = Calendar.getInstance();
        prevendcal.setTime(dateFormat.parse(sealifeUtils.getCurrentPositionToBeforeDay(str_dt, 1)));
        String prev_end_dt = sdf.format(prevendcal.getTime()) + " 23:59:59";//구간지정
        prevendcal.add(Calendar.DATE, -days);
        String prev_dt = sdf.format(prevendcal.getTime()) + " 00:00:00";//7일전 일자
        
        LOGGER.debug("검색 조회 타입 : " + selectDays);
        LOGGER.debug("검색 조회 오늘 일자 : " + cur_dt);
        LOGGER.debug("검색 조회 기간 일수 : " + days);
        LOGGER.debug("검색 조회 기간 시작 : " + str_dt);
		LOGGER.debug("검색 조회 기간 종료 : " + end_dt);
		LOGGER.debug("검색 조회 기간 이전시작 : " + prev_dt);
		LOGGER.debug("검색 조회 기간 이전종료 : " + prev_end_dt);
		
		//공통
		EduAnalyticsAdmVO.setSTR_DT(str_dt);
	    EduAnalyticsAdmVO.setEND_DT(end_dt);
        
	    //인기페이지
	  	EduAnalyticsAdmVO.setPageUnit(10);
  		EduAnalyticsAdmVO.setFirstIndex(0);
  		List<EduAnalyticsAdmVO> popularpageList = analyticsAdmService.popularpageAdmList(EduAnalyticsAdmVO);
  		int populartotcnt = 0;
  		for (EduAnalyticsAdmVO item : popularpageList) {
  			populartotcnt += Integer.parseInt(item.getvisitcount());
  		}
  		
		//방문자수
		int visittotCnt = analyticsAdmService.visitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//방문횟수
		int allvisittotCnt = analyticsAdmService.allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//페이지뷰
		int totPageViewCnt = analyticsAdmService.pageviewAdmListTotCnt(EduAnalyticsAdmVO);
		
		//일별 방문 분포
		List<EduAnalyticsDayVO> visitdaytotlist = analyticsAdmService.visitorcountDaysAdmList(EduAnalyticsAdmVO);
		
	    //검색채널별 검색유입
	    List<EduAnalyticsInfoVO> visitchannelinputtypelist = analyticsAdmService.visitorChannelInputTypeList(EduAnalyticsAdmVO);
	    
	    //접속환경별 방문자수
	    List<EduAnalyticsInfoVO> visitdevicetypelist = analyticsAdmService.visitorDeviceTypeList(EduAnalyticsAdmVO);
	    	    
	    //비교자료
	    	//공통
		    EduAnalyticsAdmVO.setSTR_DT("");
	        EduAnalyticsAdmVO.setEND_DT("");
			EduAnalyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        EduAnalyticsAdmVO.setPREV_DT(prev_dt);
	        //방문자수
	  		int visittotCntPrev = analyticsAdmService.visitorcountAdmListTotCnt(EduAnalyticsAdmVO);
	  		//방문횟수
	  		int allvisittotCntPrev = analyticsAdmService.allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
	  		//페이지뷰
	  		int totPageViewCntPrev = analyticsAdmService.pageviewAdmListTotCnt(EduAnalyticsAdmVO);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
				
		model.addAttribute("popularpageList", popularpageList);
		model.addAttribute("populartotcnt", populartotcnt);
		model.addAttribute("visittotCnt", visittotCnt);
		model.addAttribute("allvisittotCnt", allvisittotCnt);
		model.addAttribute("totPageViewCnt", totPageViewCnt);
		model.addAttribute("visittotCntPrev", visittotCntPrev);
		model.addAttribute("allvisittotCntPrev", allvisittotCntPrev);
		model.addAttribute("totPageViewCntPrev", totPageViewCntPrev);
		model.addAttribute("visitdaytotlist", visitdaytotlist);
		model.addAttribute("visitdaytotlistJson", mapper.writeValueAsString(visitdaytotlist));
		model.addAttribute("visitchannelinputtypelist", visitchannelinputtypelist);
		model.addAttribute("visitchannelinputtypelistJson", mapper.writeValueAsString(visitchannelinputtypelist));
		model.addAttribute("visitdevicetypelist", visitdevicetypelist);
		model.addAttribute("visitdevicetypelistJson", mapper.writeValueAsString(visitdevicetypelist));
		
		model.addAttribute("selectDays", selectDays);
		model.addAttribute("str_dt", str_dt);
		model.addAttribute("end_dt", end_dt);
		model.addAttribute("prev_dt", prev_dt);
		model.addAttribute("prev_end_dt", prev_end_dt);
				
		return "eduadm/analytics/summary/sitesummary";
	}
	
	//통계관리   - 방문현황(UV)  ------------------------------------------------
	@RequestMapping(value = "/eduadm/analytics/visit/uv.do")
	public String uvAdmList(@ModelAttribute("EduAnalyticsAdmVO") EduAnalyticsAdmVO EduAnalyticsAdmVO, 
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = EduAnalyticsAdmVO.getSTR_DT();
		String end_dt = EduAnalyticsAdmVO.getEND_DT();
		if(selectDays==null || selectDays.length()==0) {//최초진입
			selectDays = "7";
			end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
			str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
		} else {
			if(selectDays.equals("direct")) {//직접입력 인 경우
				if(str_dt==null || str_dt.length()==0) {
					str_dt = "2019-01-01 00:00:00";
				}
				if(end_dt==null || end_dt.length()==0) {
					end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				}
			} else {
				end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
			}
		}
		int days = EgovDateUtil.getDaysDiff(sealifeUtils.changePatternString(str_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"), sealifeUtils.changePatternString(end_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar prevendcal = Calendar.getInstance();
        prevendcal.setTime(dateFormat.parse(sealifeUtils.getCurrentPositionToBeforeDay(str_dt, 1)));
        String prev_end_dt = sdf.format(prevendcal.getTime()) + " 23:59:59";//구간지정
        prevendcal.add(Calendar.DATE, -days);
        String prev_dt = sdf.format(prevendcal.getTime()) + " 00:00:00";//7일전 일자
        
        LOGGER.debug("검색 조회 타입 : " + selectDays);
        LOGGER.debug("검색 조회 오늘 일자 : " + cur_dt);
        LOGGER.debug("검색 조회 기간 일수 : " + days);
        LOGGER.debug("검색 조회 기간 시작 : " + str_dt);
		LOGGER.debug("검색 조회 기간 종료 : " + end_dt);
		LOGGER.debug("검색 조회 기간 이전시작 : " + prev_dt);
		LOGGER.debug("검색 조회 기간 이전종료 : " + prev_end_dt);
		
		//공통
		EduAnalyticsAdmVO.setSTR_DT(str_dt);
	    EduAnalyticsAdmVO.setEND_DT(end_dt);
        
		//방문자수
		int visittotCnt = analyticsAdmService.visitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//방문횟수
		int allvisittotCnt = analyticsAdmService.allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//페이지뷰
		int totPageViewCnt = analyticsAdmService.pageviewAdmListTotCnt(EduAnalyticsAdmVO);
		//신규방문자수
		int newvisittotCnt = analyticsAdmService.newvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//재방문자수
		int revisittotCnt = analyticsAdmService.revisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//일별 방문 분포
		List<EduAnalyticsDayVO> visitdaytotlist = analyticsAdmService.visitorcountDaysAdmList(EduAnalyticsAdmVO);
				
	    //비교자료
	    	//공통
		    EduAnalyticsAdmVO.setSTR_DT("");
	        EduAnalyticsAdmVO.setEND_DT("");
			EduAnalyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        EduAnalyticsAdmVO.setPREV_DT(prev_dt);
	        //방문자수
	  		int visittotCntPrev = analyticsAdmService.visitorcountAdmListTotCnt(EduAnalyticsAdmVO);
	  		//방문횟수
	  		int allvisittotCntPrev = analyticsAdmService.allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
	  		//페이지뷰
	  		int totPageViewCntPrev = analyticsAdmService.pageviewAdmListTotCnt(EduAnalyticsAdmVO);
	  		//신규방문자수
			int newvisittotCntPrev = analyticsAdmService.newvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
			//재방문자수
			int revisittotCntPrev = analyticsAdmService.revisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
				
		model.addAttribute("visittotCnt", visittotCnt);
		model.addAttribute("allvisittotCnt", allvisittotCnt);
		model.addAttribute("totPageViewCnt", totPageViewCnt);
		model.addAttribute("newvisittotCnt", newvisittotCnt);
		model.addAttribute("revisittotCnt", revisittotCnt);
		
		model.addAttribute("visittotCntPrev", visittotCntPrev);
		model.addAttribute("allvisittotCntPrev", allvisittotCntPrev);
		model.addAttribute("totPageViewCntPrev", totPageViewCntPrev);
		model.addAttribute("newvisittotCntPrev", newvisittotCntPrev);
		model.addAttribute("revisittotCntPrev", revisittotCntPrev);
		
		model.addAttribute("visitdaytotlist", visitdaytotlist);
		model.addAttribute("visitdaytotlistJson", mapper.writeValueAsString(visitdaytotlist));
		
		model.addAttribute("selectDays", selectDays);
		model.addAttribute("str_dt", str_dt);
		model.addAttribute("end_dt", end_dt);
		model.addAttribute("prev_dt", prev_dt);
		model.addAttribute("prev_end_dt", prev_end_dt);
		
		return "eduadm/analytics/visit/uv";
	}
	
	//통계관리   - 페이지뷰(PV) ------------------------------------------------
	@RequestMapping(value = "/eduadm/analytics/visit/pv.do")
	public String pvAdmList(@ModelAttribute("EduAnalyticsAdmVO") EduAnalyticsAdmVO EduAnalyticsAdmVO, 
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = EduAnalyticsAdmVO.getSTR_DT();
		String end_dt = EduAnalyticsAdmVO.getEND_DT();
		if(selectDays==null || selectDays.length()==0) {//최초진입
			selectDays = "7";
			end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
			str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
		} else {
			if(selectDays.equals("direct")) {//직접입력 인 경우
				if(str_dt==null || str_dt.length()==0) {
					str_dt = "2019-01-01 00:00:00";
				}
				if(end_dt==null || end_dt.length()==0) {
					end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				}
			} else {
				end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
			}
		}
		int days = EgovDateUtil.getDaysDiff(sealifeUtils.changePatternString(str_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"), sealifeUtils.changePatternString(end_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar prevendcal = Calendar.getInstance();
        prevendcal.setTime(dateFormat.parse(sealifeUtils.getCurrentPositionToBeforeDay(str_dt, 1)));
        String prev_end_dt = sdf.format(prevendcal.getTime()) + " 23:59:59";//구간지정
        prevendcal.add(Calendar.DATE, -days);
        String prev_dt = sdf.format(prevendcal.getTime()) + " 00:00:00";//7일전 일자
        
        LOGGER.debug("검색 조회 타입 : " + selectDays);
        LOGGER.debug("검색 조회 오늘 일자 : " + cur_dt);
        LOGGER.debug("검색 조회 기간 일수 : " + days);
        LOGGER.debug("검색 조회 기간 시작 : " + str_dt);
		LOGGER.debug("검색 조회 기간 종료 : " + end_dt);
		LOGGER.debug("검색 조회 기간 이전시작 : " + prev_dt);
		LOGGER.debug("검색 조회 기간 이전종료 : " + prev_end_dt);
		
		//공통
		EduAnalyticsAdmVO.setSTR_DT(str_dt);
	    EduAnalyticsAdmVO.setEND_DT(end_dt);
        
		//방문횟수
		int allvisittotCnt = analyticsAdmService.allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//페이지뷰
		int totPageViewCnt = analyticsAdmService.pageviewAdmListTotCnt(EduAnalyticsAdmVO);
		//신규방문자 PV
		int newpvvisittotCnt = analyticsAdmService.newpvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//재방문자 PV
		int repvvisittotCnt = analyticsAdmService.repvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		//일별 방문 분포
		List<EduAnalyticsDayVO> visitdaytotlist = analyticsAdmService.visitorcountDaysAdmList(EduAnalyticsAdmVO);
				
	    //비교자료
	    	//공통
		    EduAnalyticsAdmVO.setSTR_DT("");
	        EduAnalyticsAdmVO.setEND_DT("");
			EduAnalyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        EduAnalyticsAdmVO.setPREV_DT(prev_dt);
	        //방문횟수
	  		int allvisittotCntPrev = analyticsAdmService.allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
	  		//페이지뷰
	  		int totPageViewCntPrev = analyticsAdmService.pageviewAdmListTotCnt(EduAnalyticsAdmVO);
	  		//신규방문자 PV
			int newpvvisittotCntPrev = analyticsAdmService.newpvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
			//재방문자 PV
			int repvvisittotCntPrev = analyticsAdmService.repvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
				
		model.addAttribute("allvisittotCnt", allvisittotCnt);
		model.addAttribute("totPageViewCnt", totPageViewCnt);
		model.addAttribute("newpvvisittotCnt", newpvvisittotCnt);
		model.addAttribute("repvvisittotCnt", repvvisittotCnt);
		
		model.addAttribute("allvisittotCntPrev", allvisittotCntPrev);
		model.addAttribute("totPageViewCntPrev", totPageViewCntPrev);
		model.addAttribute("newpvvisittotCntPrev", newpvvisittotCntPrev);
		model.addAttribute("repvvisittotCntPrev", repvvisittotCntPrev);
		
		model.addAttribute("visitdaytotlist", visitdaytotlist);
		model.addAttribute("visitdaytotlistJson", mapper.writeValueAsString(visitdaytotlist));
		
		model.addAttribute("selectDays", selectDays);
		model.addAttribute("str_dt", str_dt);
		model.addAttribute("end_dt", end_dt);
		model.addAttribute("prev_dt", prev_dt);
		model.addAttribute("prev_end_dt", prev_end_dt);
				
		return "eduadm/analytics/visit/pv";
	}
	
	//통계관리   - 검색유입현황  ------------------------------------------------
	@RequestMapping(value = "/eduadm/analytics/inflow/searchdashboard.do")
	public String searchdashboardAdmList(@ModelAttribute("EduAnalyticsAdmVO") EduAnalyticsAdmVO EduAnalyticsAdmVO,
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = EduAnalyticsAdmVO.getSTR_DT();
		String end_dt = EduAnalyticsAdmVO.getEND_DT();
		if(selectDays==null || selectDays.length()==0) {//최초진입
			selectDays = "7";
			end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
			str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
		} else {
			if(selectDays.equals("direct")) {//직접입력 인 경우
				if(str_dt==null || str_dt.length()==0) {
					str_dt = "2019-01-01 00:00:00";
				}
				if(end_dt==null || end_dt.length()==0) {
					end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				}
			} else {
				end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
			}
		}
		int days = EgovDateUtil.getDaysDiff(sealifeUtils.changePatternString(str_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"), sealifeUtils.changePatternString(end_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar prevendcal = Calendar.getInstance();
        prevendcal.setTime(dateFormat.parse(sealifeUtils.getCurrentPositionToBeforeDay(str_dt, 1)));
        String prev_end_dt = sdf.format(prevendcal.getTime()) + " 23:59:59";//구간지정
        prevendcal.add(Calendar.DATE, -days);
        String prev_dt = sdf.format(prevendcal.getTime()) + " 00:00:00";//7일전 일자
        
        LOGGER.debug("검색 조회 타입 : " + selectDays);
        LOGGER.debug("검색 조회 오늘 일자 : " + cur_dt);
        LOGGER.debug("검색 조회 기간 일수 : " + days);
        LOGGER.debug("검색 조회 기간 시작 : " + str_dt);
		LOGGER.debug("검색 조회 기간 종료 : " + end_dt);
		LOGGER.debug("검색 조회 기간 이전시작 : " + prev_dt);
		LOGGER.debug("검색 조회 기간 이전종료 : " + prev_end_dt);
		
		//공통
		EduAnalyticsAdmVO.setSTR_DT(str_dt);
	    EduAnalyticsAdmVO.setEND_DT(end_dt);
        
		//검색유입수
		int visitchannelinputtotCnt = analyticsAdmService.visitorChannelInputTotCnt(EduAnalyticsAdmVO);
		//전체유입수
		int visitchannelinputalltotCnt = analyticsAdmService.visitorChannelInputAllTotCnt(EduAnalyticsAdmVO);
		
		//검색채널별 검색유입
	    List<EduAnalyticsInfoVO> visitchannelinputtypelist = analyticsAdmService.visitorChannelInputTypeList(EduAnalyticsAdmVO);
	    //검색어 현황
	    List<EduAnalyticsInfoVO> visitchannelinputnamelist = analyticsAdmService.visitorChannelInputNameList(EduAnalyticsAdmVO);
				
	    //비교자료
	    	//공통
		    EduAnalyticsAdmVO.setSTR_DT("");
	        EduAnalyticsAdmVO.setEND_DT("");
			EduAnalyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        EduAnalyticsAdmVO.setPREV_DT(prev_dt);
	        //검색유입수
	  		int visitchannelinputtotCntPrev = analyticsAdmService.visitorChannelInputTotCnt(EduAnalyticsAdmVO);
	  		//전체유입수
	  		int visitchannelinputalltotCntPrev = analyticsAdmService.visitorChannelInputAllTotCnt(EduAnalyticsAdmVO);
	  		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
				
		model.addAttribute("visitchannelinputtotCnt", visitchannelinputtotCnt);
		model.addAttribute("visitchannelinputalltotCnt", visitchannelinputalltotCnt);
		
		model.addAttribute("visitchannelinputtotCntPrev", visitchannelinputtotCntPrev);
		model.addAttribute("visitchannelinputalltotCntPrev", visitchannelinputalltotCntPrev);
		
		model.addAttribute("visitchannelinputtypelist", visitchannelinputtypelist);
		model.addAttribute("visitchannelinputtypelistJson", mapper.writeValueAsString(visitchannelinputtypelist));
		
		model.addAttribute("visitchannelinputnamelist", visitchannelinputnamelist);
		
		model.addAttribute("selectDays", selectDays);
		model.addAttribute("str_dt", str_dt);
		model.addAttribute("end_dt", end_dt);
		model.addAttribute("prev_dt", prev_dt);
		model.addAttribute("prev_end_dt", prev_end_dt);
				
		return "eduadm/analytics/inflow/searchdashboard";
	}
	
	
	//통계관리   - 운영체제 분석  ------------------------------------------------
	@RequestMapping(value = "/eduadm/analytics/environment/osdashboard.do")
	public String osdashboardAdmList(@ModelAttribute("EduAnalyticsAdmVO") EduAnalyticsAdmVO EduAnalyticsAdmVO, 
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = EduAnalyticsAdmVO.getSTR_DT();
		String end_dt = EduAnalyticsAdmVO.getEND_DT();
		if(selectDays==null || selectDays.length()==0) {//최초진입
			selectDays = "7";
			end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
			str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
		} else {
			if(selectDays.equals("direct")) {//직접입력 인 경우
				if(str_dt==null || str_dt.length()==0) {
					str_dt = "2019-01-01 00:00:00";
				}
				if(end_dt==null || end_dt.length()==0) {
					end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				}
			} else {
				end_dt = sealifeUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				str_dt = sealifeUtils.changePatternString(sealifeUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
			}
		}
		int days = EgovDateUtil.getDaysDiff(sealifeUtils.changePatternString(str_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"), sealifeUtils.changePatternString(end_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar prevendcal = Calendar.getInstance();
        prevendcal.setTime(dateFormat.parse(sealifeUtils.getCurrentPositionToBeforeDay(str_dt, 1)));
        String prev_end_dt = sdf.format(prevendcal.getTime()) + " 23:59:59";//구간지정
        prevendcal.add(Calendar.DATE, -days);
        String prev_dt = sdf.format(prevendcal.getTime()) + " 00:00:00";//7일전 일자
        
        LOGGER.debug("검색 조회 타입 : " + selectDays);
        LOGGER.debug("검색 조회 오늘 일자 : " + cur_dt);
        LOGGER.debug("검색 조회 기간 일수 : " + days);
        LOGGER.debug("검색 조회 기간 시작 : " + str_dt);
		LOGGER.debug("검색 조회 기간 종료 : " + end_dt);
		LOGGER.debug("검색 조회 기간 이전시작 : " + prev_dt);
		LOGGER.debug("검색 조회 기간 이전종료 : " + prev_end_dt);
		
		//공통
		EduAnalyticsAdmVO.setSTR_DT(str_dt);
	    EduAnalyticsAdmVO.setEND_DT(end_dt);
        
		//PC웹 점유율
		int visitospctotCnt = analyticsAdmService.visitorOsPcTotCnt(EduAnalyticsAdmVO);
		//모바일 점유율
		int visitosmobiletotCnt = analyticsAdmService.visitorOsMobileTotCnt(EduAnalyticsAdmVO);
		//기타 점유율
		int visitosetctotCnt = analyticsAdmService.visitorOsEtcTotCnt(EduAnalyticsAdmVO);
		
		//운영체제별 그래프
	    List<EduAnalyticsInfoVO> visitosalllist = analyticsAdmService.visitorOsAllList(EduAnalyticsAdmVO);
	    
	    //운영체제별 상세
	    List<EduAnalyticsInfoVO> visitosdetailalllist = analyticsAdmService.visitorOsDetailAllList(EduAnalyticsAdmVO);
	    		
	    //비교자료
	    	//공통
		    EduAnalyticsAdmVO.setSTR_DT("");
	        EduAnalyticsAdmVO.setEND_DT("");
			EduAnalyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        EduAnalyticsAdmVO.setPREV_DT(prev_dt);
	        //검색유입수
	  		int visitospctotCntPrev = analyticsAdmService.visitorOsPcTotCnt(EduAnalyticsAdmVO);
	  		//전체유입수
	  		int visitosmobiletotCntPrev = analyticsAdmService.visitorOsMobileTotCnt(EduAnalyticsAdmVO);
	  		//기타 점유율
			int visitosetctotCntPrev = analyticsAdmService.visitorOsEtcTotCnt(EduAnalyticsAdmVO);
	  		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
				
		model.addAttribute("visitospctotCnt", visitospctotCnt);
		model.addAttribute("visitosmobiletotCnt", visitosmobiletotCnt);
		model.addAttribute("visitosetctotCnt", visitosetctotCnt);
		
		model.addAttribute("visitospctotCntPrev", visitospctotCntPrev);
		model.addAttribute("visitosalltotCntPrev", visitosmobiletotCntPrev);
		model.addAttribute("visitosetctotCntPrev", visitosetctotCntPrev);
		
		model.addAttribute("visitosalllist", visitosalllist);
		model.addAttribute("visitosalllistJson", mapper.writeValueAsString(visitosalllist));
		
		model.addAttribute("visitosdetailalllist", visitosdetailalllist);
		
		model.addAttribute("selectDays", selectDays);
		model.addAttribute("str_dt", str_dt);
		model.addAttribute("end_dt", end_dt);
		model.addAttribute("prev_dt", prev_dt);
		model.addAttribute("prev_end_dt", prev_end_dt);
				
		return "eduadm/analytics/environment/osdashboard";
	}		
	
	
	
	//통계관리   - 페이지 종합 통계  ------------------------------------------------
	@RequestMapping(value = "/eduadm/analytics/inflow/urls.do")
	public String urlsAdmList(@ModelAttribute("EduAnalyticsPageVO") EduAnalyticsPageVO EduAnalyticsPageVO, 
			@RequestParam(value="selectYears",required=false) String selectYears,
			ModelMap model) throws Exception {
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_year = sealifeUtils.currentTime("yyyy");
		if(selectYears==null || selectYears.length()==0) {//최초진입
			selectYears = cur_year;
		} 
        LOGGER.debug("검색 조회 년도 : " + selectYears);

		//공통
		EduAnalyticsPageVO.setVALUE_YEAR(selectYears);
	    
	    List<EduAnalyticsPageVO> visitorpagetotlist = analyticsAdmService.visitorPageTotList(EduAnalyticsPageVO);

	    model.addAttribute("visitorpagetotlist", visitorpagetotlist);		
		model.addAttribute("selectYears", selectYears);
				
		return "eduadm/analytics/inflow/urls";
	}
	
	
}


