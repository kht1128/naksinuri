package egovframework.seadm.analytics.web;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.seadm.analytics.service.AnalyticsAdmService;
import egovframework.seadm.analytics.service.AnalyticsAdmVO;
import egovframework.seadm.analytics.service.AnalyticsDayVO;
import egovframework.seadm.analytics.service.AnalyticsInfoVO;
import egovframework.seadm.analytics.service.AnalyticsPageVO;
import egovframework.seadm.member.service.MemberService;
import egovframework.utils.EgovDateUtil;
import egovframework.utils.PublicUtils;

@Controller
public class AnalyticsAdmController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsAdmController.class);
	
	/** memberService */
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "AnalyticsAdmService")
	private AnalyticsAdmService analyticsAdmService;
	
	
	//통계관리   - 사이트현황  ------------------------------------------------
	@RequestMapping(value = "/seadm/analytics/summary/sitesummary.do")
	public String sitesummaryAdmList(@ModelAttribute("analyticsAdmVO") AnalyticsAdmVO analyticsAdmVO,
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = analyticsAdmVO.getSTR_DT();
		String end_dt = analyticsAdmVO.getEND_DT();
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
		analyticsAdmVO.setSTR_DT(str_dt);
	    analyticsAdmVO.setEND_DT(end_dt);
        
	    //인기페이지
	  	analyticsAdmVO.setPageUnit(10);
  		analyticsAdmVO.setFirstIndex(0);
  		List<AnalyticsAdmVO> popularpageList = analyticsAdmService.popularpageAdmList(analyticsAdmVO);
  		int populartotcnt = 0;
  		for (AnalyticsAdmVO item : popularpageList) {
  			populartotcnt += Integer.parseInt(item.getvisitcount());
  		}
  		
		//방문자수
		int visittotCnt = analyticsAdmService.visitorcountAdmListTotCnt(analyticsAdmVO);
		//방문횟수
		int allvisittotCnt = analyticsAdmService.allvisitorcountAdmListTotCnt(analyticsAdmVO);
		//페이지뷰
		int totPageViewCnt = analyticsAdmService.pageviewAdmListTotCnt(analyticsAdmVO);
		
		//일별 방문 분포
		List<AnalyticsDayVO> visitdaytotlist = analyticsAdmService.visitorcountDaysAdmList(analyticsAdmVO);
		
	    //검색채널별 검색유입
	    List<AnalyticsInfoVO> visitchannelinputtypelist = analyticsAdmService.visitorChannelInputTypeList(analyticsAdmVO);
	    
	    //접속환경별 방문자수
	    List<AnalyticsInfoVO> visitdevicetypelist = analyticsAdmService.visitorDeviceTypeList(analyticsAdmVO);
	    	    
	    //비교자료
	    	//공통
		    analyticsAdmVO.setSTR_DT("");
	        analyticsAdmVO.setEND_DT("");
			analyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        analyticsAdmVO.setPREV_DT(prev_dt);
	        //방문자수
	  		int visittotCntPrev = analyticsAdmService.visitorcountAdmListTotCnt(analyticsAdmVO);
	  		//방문횟수
	  		int allvisittotCntPrev = analyticsAdmService.allvisitorcountAdmListTotCnt(analyticsAdmVO);
	  		//페이지뷰
	  		int totPageViewCntPrev = analyticsAdmService.pageviewAdmListTotCnt(analyticsAdmVO);
		
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
				
		return "seadm/analytics/summary/sitesummary";
	}
	
	//통계관리   - 방문현황(UV)  ------------------------------------------------
	@RequestMapping(value = "/seadm/analytics/visit/uv.do")
	public String uvAdmList(@ModelAttribute("analyticsAdmVO") AnalyticsAdmVO analyticsAdmVO, 
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = analyticsAdmVO.getSTR_DT();
		String end_dt = analyticsAdmVO.getEND_DT();
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
		analyticsAdmVO.setSTR_DT(str_dt);
	    analyticsAdmVO.setEND_DT(end_dt);
        
		//방문자수
		int visittotCnt = analyticsAdmService.visitorcountAdmListTotCnt(analyticsAdmVO);
		//방문횟수
		int allvisittotCnt = analyticsAdmService.allvisitorcountAdmListTotCnt(analyticsAdmVO);
		//페이지뷰
		int totPageViewCnt = analyticsAdmService.pageviewAdmListTotCnt(analyticsAdmVO);
		//신규방문자수
		int newvisittotCnt = analyticsAdmService.newvisitorcountAdmListTotCnt(analyticsAdmVO);
		//재방문자수
		int revisittotCnt = analyticsAdmService.revisitorcountAdmListTotCnt(analyticsAdmVO);
		//일별 방문 분포
		List<AnalyticsDayVO> visitdaytotlist = analyticsAdmService.visitorcountDaysAdmList(analyticsAdmVO);
				
	    //비교자료
	    	//공통
		    analyticsAdmVO.setSTR_DT("");
	        analyticsAdmVO.setEND_DT("");
			analyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        analyticsAdmVO.setPREV_DT(prev_dt);
	        //방문자수
	  		int visittotCntPrev = analyticsAdmService.visitorcountAdmListTotCnt(analyticsAdmVO);
	  		//방문횟수
	  		int allvisittotCntPrev = analyticsAdmService.allvisitorcountAdmListTotCnt(analyticsAdmVO);
	  		//페이지뷰
	  		int totPageViewCntPrev = analyticsAdmService.pageviewAdmListTotCnt(analyticsAdmVO);
	  		//신규방문자수
			int newvisittotCntPrev = analyticsAdmService.newvisitorcountAdmListTotCnt(analyticsAdmVO);
			//재방문자수
			int revisittotCntPrev = analyticsAdmService.revisitorcountAdmListTotCnt(analyticsAdmVO);
		
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
		
		return "seadm/analytics/visit/uv";
	}
	
	//통계관리   - 페이지뷰(PV) ------------------------------------------------
	@RequestMapping(value = "/seadm/analytics/visit/pv.do")
	public String pvAdmList(@ModelAttribute("analyticsAdmVO") AnalyticsAdmVO analyticsAdmVO, 
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = analyticsAdmVO.getSTR_DT();
		String end_dt = analyticsAdmVO.getEND_DT();
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
		analyticsAdmVO.setSTR_DT(str_dt);
	    analyticsAdmVO.setEND_DT(end_dt);
        
		//방문횟수
		int allvisittotCnt = analyticsAdmService.allvisitorcountAdmListTotCnt(analyticsAdmVO);
		//페이지뷰
		int totPageViewCnt = analyticsAdmService.pageviewAdmListTotCnt(analyticsAdmVO);
		//신규방문자 PV
		int newpvvisittotCnt = analyticsAdmService.newpvvisitorcountAdmListTotCnt(analyticsAdmVO);
		//재방문자 PV
		int repvvisittotCnt = analyticsAdmService.repvvisitorcountAdmListTotCnt(analyticsAdmVO);
		//일별 방문 분포
		List<AnalyticsDayVO> visitdaytotlist = analyticsAdmService.visitorcountDaysAdmList(analyticsAdmVO);
				
	    //비교자료
	    	//공통
		    analyticsAdmVO.setSTR_DT("");
	        analyticsAdmVO.setEND_DT("");
			analyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        analyticsAdmVO.setPREV_DT(prev_dt);
	        //방문횟수
	  		int allvisittotCntPrev = analyticsAdmService.allvisitorcountAdmListTotCnt(analyticsAdmVO);
	  		//페이지뷰
	  		int totPageViewCntPrev = analyticsAdmService.pageviewAdmListTotCnt(analyticsAdmVO);
	  		//신규방문자 PV
			int newpvvisittotCntPrev = analyticsAdmService.newpvvisitorcountAdmListTotCnt(analyticsAdmVO);
			//재방문자 PV
			int repvvisittotCntPrev = analyticsAdmService.repvvisitorcountAdmListTotCnt(analyticsAdmVO);
		
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
				
		return "seadm/analytics/visit/pv";
	}
	
	//통계관리   - 검색유입현황  ------------------------------------------------
	@RequestMapping(value = "/seadm/analytics/inflow/searchdashboard.do")
	public String searchdashboardAdmList(@ModelAttribute("analyticsAdmVO") AnalyticsAdmVO analyticsAdmVO,
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = analyticsAdmVO.getSTR_DT();
		String end_dt = analyticsAdmVO.getEND_DT();
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
		analyticsAdmVO.setSTR_DT(str_dt);
	    analyticsAdmVO.setEND_DT(end_dt);
        
		//검색유입수
		int visitchannelinputtotCnt = analyticsAdmService.visitorChannelInputTotCnt(analyticsAdmVO);
		//전체유입수
		int visitchannelinputalltotCnt = analyticsAdmService.visitorChannelInputAllTotCnt(analyticsAdmVO);
		
		//검색채널별 검색유입
	    List<AnalyticsInfoVO> visitchannelinputtypelist = analyticsAdmService.visitorChannelInputTypeList(analyticsAdmVO);
	    //검색어 현황
	    List<AnalyticsInfoVO> visitchannelinputnamelist = analyticsAdmService.visitorChannelInputNameList(analyticsAdmVO);
				
	    //비교자료
	    	//공통
		    analyticsAdmVO.setSTR_DT("");
	        analyticsAdmVO.setEND_DT("");
			analyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        analyticsAdmVO.setPREV_DT(prev_dt);
	        //검색유입수
	  		int visitchannelinputtotCntPrev = analyticsAdmService.visitorChannelInputTotCnt(analyticsAdmVO);
	  		//전체유입수
	  		int visitchannelinputalltotCntPrev = analyticsAdmService.visitorChannelInputAllTotCnt(analyticsAdmVO);
	  		
		
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
				
		return "seadm/analytics/inflow/searchdashboard";
	}
	
	
	//통계관리   - 운영체제 분석  ------------------------------------------------
	@RequestMapping(value = "/seadm/analytics/environment/osdashboard.do")
	public String osdashboardAdmList(@ModelAttribute("analyticsAdmVO") AnalyticsAdmVO analyticsAdmVO, 
			@RequestParam(value="selectDays",required=false) String selectDays,
			ModelMap model) throws Exception {
		
		PublicUtils sealifeUtils = new PublicUtils();
		
		//기간설정
		String cur_dt = sealifeUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = analyticsAdmVO.getSTR_DT();
		String end_dt = analyticsAdmVO.getEND_DT();
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
		analyticsAdmVO.setSTR_DT(str_dt);
	    analyticsAdmVO.setEND_DT(end_dt);
        
		//PC웹 점유율
		int visitospctotCnt = analyticsAdmService.visitorOsPcTotCnt(analyticsAdmVO);
		//모바일 점유율
		int visitosmobiletotCnt = analyticsAdmService.visitorOsMobileTotCnt(analyticsAdmVO);
		//기타 점유율
		int visitosetctotCnt = analyticsAdmService.visitorOsEtcTotCnt(analyticsAdmVO);
		
		//운영체제별 그래프
	    List<AnalyticsInfoVO> visitosalllist = analyticsAdmService.visitorOsAllList(analyticsAdmVO);
	    
	    //운영체제별 상세
	    List<AnalyticsInfoVO> visitosdetailalllist = analyticsAdmService.visitorOsDetailAllList(analyticsAdmVO);
	    		
	    //비교자료
	    	//공통
		    analyticsAdmVO.setSTR_DT("");
	        analyticsAdmVO.setEND_DT("");
			analyticsAdmVO.setPREV_END_DT(prev_end_dt);
	        analyticsAdmVO.setPREV_DT(prev_dt);
	        //검색유입수
	  		int visitospctotCntPrev = analyticsAdmService.visitorOsPcTotCnt(analyticsAdmVO);
	  		//전체유입수
	  		int visitosmobiletotCntPrev = analyticsAdmService.visitorOsMobileTotCnt(analyticsAdmVO);
	  		//기타 점유율
			int visitosetctotCntPrev = analyticsAdmService.visitorOsEtcTotCnt(analyticsAdmVO);
	  		
		
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
				
		return "seadm/analytics/environment/osdashboard";
	}		
	
	
	
	//통계관리   - 페이지 종합 통계  ------------------------------------------------
	@RequestMapping(value = "/seadm/analytics/inflow/urls.do")
	public String urlsAdmList(@ModelAttribute("analyticsPageVO") AnalyticsPageVO analyticsPageVO, 
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
		analyticsPageVO.setVALUE_YEAR(selectYears);
	    
	    List<AnalyticsPageVO> visitorpagetotlist = analyticsAdmService.visitorPageTotList(analyticsPageVO);

	    model.addAttribute("visitorpagetotlist", visitorpagetotlist);		
		model.addAttribute("selectYears", selectYears);
				
		return "seadm/analytics/inflow/urls";
	}
	
	
}


