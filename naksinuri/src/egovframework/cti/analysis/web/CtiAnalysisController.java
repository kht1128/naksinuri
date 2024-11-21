package egovframework.cti.analysis.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.login.service.LoginVO;
import egovframework.cti.analysis.service.CtiAnalysisService;
import egovframework.cti.analysis.service.CtiAnalysisVO;
import egovframework.cti.callhstry.service.CtiCallHistoryService;
import egovframework.cti.callhstry.service.CtiCallHistoryVO;
import egovframework.cti.member.service.CtiMemberService;
import egovframework.cti.member.service.CtiMemberVO;
import egovframework.utils.EgovDateUtil;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class CtiAnalysisController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CtiAnalysisController.class);
	
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	@Resource(name = "ctiAnalysisService")
	private CtiAnalysisService ctiAnalysisService;
	
	@Resource(name = "ctiMemberService")
	private CtiMemberService ctiMemberService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "ctiCallHistoryService")
	private CtiCallHistoryService ctiCallHistoryService;
		
	//금일상담건 실시간 통계 정보
  	@RequestMapping(value="/cti/analysis/call/ajax_main_today.do", method = RequestMethod.POST)
  	public @ResponseBody String ajax_cti_analysis_call_main_today(@ModelAttribute("ctiAnalysisVO") CtiAnalysisVO ctiAnalysisVO,
  			HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {	
  		  		
  		JSONObject data = new JSONObject();
  		try {
  			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
			
  			int call_total = ctiAnalysisService.get_call_main_today_call_total();//총건수
  			int call_drsc = ctiAnalysisService.get_call_main_today_call_drsc();//부재중건
  			int call_rspons = ctiAnalysisService.get_call_main_today_call_rspons();//응대건수
  			int call_cancel = ctiAnalysisService.get_call_main_today_call_cancel();//취소건수
  			
  			ctiAnalysisVO.setCALL_TOTAL(call_total);
  			ctiAnalysisVO.setCALL_DRSC(call_drsc);
  			ctiAnalysisVO.setCALL_RSPONS(call_rspons);
  			ctiAnalysisVO.setCALL_CANCEL(call_cancel);
  			if(call_total > 0) {
  				float pt = ((float)call_rspons/(float)call_total)*100f;
  				LOGGER.debug("응대율 : " + pt);
  				ctiAnalysisVO.setCALL_DRSC_PT(String.format("%.1f", pt));
  			} else {
  				LOGGER.debug("총건수가 0 또는 보다 작음");
  				ctiAnalysisVO.setCALL_DRSC_PT("0");
  			}
  			
			data.put("rawdata",mapper.writeValueAsString(ctiAnalysisVO));
  			data.put("error", "0");
  			data.put("msg", "정상적으로 처리되었습니다.");
  		} catch(Exception e) {
  			data.put("error", "1");
  			data.put("msg", "처리 중 오류가 발생하였습니다.");	
  		}
  		
  		LOGGER.debug(data.toString());
  		response.setContentType("application/json;charset=utf-8");
  		response.getWriter().print(data);
  		return null;
  	}	
  	
  	//통계 종합 화면
  	@RequestMapping(value = "/cti/analysis/index.do")
  	public String eduadm_error_unauth(HttpServletRequest request, ModelMap model) throws Exception {
  		PublicUtils mPublicUtils = new PublicUtils();
  		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
  		
  		//스태프 정보
		CtiMemberVO ctiMemberVO = new CtiMemberVO();
		ctiMemberVO.setMBR_ID(loginVO.getMBR_ID());
		ctiMemberVO = ctiMemberService.get_cti_staff_info(ctiMemberVO);
		model.addAttribute("staff_member_info",ctiMemberVO);
		//End of 스태프 정보
		
		//기간설정
		String selectDays = "";
		String selectYears = mPublicUtils.currentTime("yyyy");
		String cur_dt = mPublicUtils.currentTime("yyyy-MM-dd 23:59:59");
		String str_dt = "";
		String end_dt = "";
		if(selectDays.length()==0) {//최초진입
			selectDays = "7";
			end_dt = mPublicUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
			str_dt = mPublicUtils.changePatternString(mPublicUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
		} else {
			if(selectDays.equals("direct")) {//직접입력 인 경우
				if(str_dt==null || str_dt.length()==0) {
					str_dt = "2019-01-01 00:00:00";
				}
				if(end_dt==null || end_dt.length()==0) {
					end_dt = mPublicUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				}
			} else {
				end_dt = mPublicUtils.getCurrentPositionToBeforeDay(cur_dt, 1);
				str_dt = mPublicUtils.changePatternString(mPublicUtils.getCurrentPositionToBeforeDay(end_dt, Integer.parseInt(selectDays)-1),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd 00:00:00");
			}
		}
		int days = EgovDateUtil.getDaysDiff(mPublicUtils.changePatternString(str_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"), mPublicUtils.changePatternString(end_dt,"yyyy-MM-dd HH:mm:ss","yyyyMMdd"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar prevendcal = Calendar.getInstance();
        prevendcal.setTime(dateFormat.parse(mPublicUtils.getCurrentPositionToBeforeDay(str_dt, 1)));
        String prev_end_dt = sdf.format(prevendcal.getTime()) + " 23:59:59";//구간지정
        prevendcal.add(Calendar.DATE, -days);
        String prev_dt = sdf.format(prevendcal.getTime()) + " 00:00:00";//7일전 일자
        
        LOGGER.debug("검색 조회 타입 : " + selectDays);
        LOGGER.debug("검색 조회 년도 : " + selectYears);
        LOGGER.debug("검색 조회 오늘 일자 : " + cur_dt);
        LOGGER.debug("검색 조회 기간 일수 : " + days);
        LOGGER.debug("검색 조회 기간 시작 : " + str_dt);
		LOGGER.debug("검색 조회 기간 종료 : " + end_dt);
		LOGGER.debug("검색 조회 기간 이전시작 : " + prev_dt);
		LOGGER.debug("검색 조회 기간 이전종료 : " + prev_end_dt);
		
		model.addAttribute("selectDays", selectDays);
		model.addAttribute("selectYears", selectYears);
		model.addAttribute("str_dt", str_dt);
		model.addAttribute("end_dt", end_dt);
		model.addAttribute("prev_dt", prev_dt);
		model.addAttribute("prev_end_dt", prev_end_dt);
		//End of 기간설정
		
		
  		return "cti/analysis/index";
  	}


  	//관리자(CTI) 메인 상담 및 통화이력 리스트  ------------------------------------------------
  	@RequestMapping(value = "/cti/analysis/{analysisWebLink}/report_ajax.do", method = RequestMethod.POST)
  	public ModelAndView ajax_cti_analysis_repoart_data(@ModelAttribute("ctiAnalysisVO") CtiAnalysisVO ctiAnalysisVO,
  			@PathVariable("analysisWebLink") String analysisWebLink,
  			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		  		
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/analysis/ajax_report_"+analysisWebLink);
  		  		
  		if(ctiAnalysisVO.getSearchKeyword().length()==0 && ctiAnalysisVO.getSearchRangeDate().length()==0) {
  			//초기 화면
  		} else {
  			
  		}
  		
  		if(ctiAnalysisVO.getSearchRangeDate().length()!=0) {
  			String[] rangeDates = ctiAnalysisVO.getSearchRangeDate().split("~");
  			for(int i=0; i<rangeDates.length; i++) {
  				if(rangeDates[i]!=null) {
  					String d = rangeDates[i].trim();
  					if(i == 0) {
  						if(d.length()<=10) ctiAnalysisVO.setSearchStrDate(d + " 00:00:00");
  						else ctiAnalysisVO.setSearchStrDate(d);
  					}
  					if(i == 1) {
  						if(d.length()<=10) ctiAnalysisVO.setSearchEndDate(d + " 23:59:59");
  						else ctiAnalysisVO.setSearchEndDate(d);
  					}
  				}
  			}
  		}
  		if(analysisWebLink!=null) {
  			if(analysisWebLink.equals("staff")) {
  				List<CtiAnalysisVO> list = ctiAnalysisService.get_report_staff_call_total(ctiAnalysisVO);
  				model.addAttribute("list",list);	
	  		} else if(analysisWebLink.equals("ivr")) {
	  			List<CtiAnalysisVO> list = new ArrayList<CtiAnalysisVO>();
	  			//상담분류 1단계
	  			CodeSetVO mCodeSetVO = new CodeSetVO();
		  		mCodeSetVO.setCD_MASTER_ID("CID00011");
		  		mCodeSetVO.setHIDE_AT("N");
		  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
		  		model.addAttribute("list_mbr_cd", list_mbr_cd);
		  		//End of 상담분류 1단계
		  		//상담사 리스트
		  		CtiMemberVO ctiStaffVO = new CtiMemberVO();
				ctiStaffVO.setUSE_ST("Y");
				List<CtiMemberVO> list_staff_mbr = ctiMemberService.get_cti_staff_list(ctiStaffVO);
		  		//End of 상담사 리스트
				for(CtiMemberVO mbr : list_staff_mbr) {
					CtiAnalysisVO info = new CtiAnalysisVO();
					info.setSearchStrDate(ctiAnalysisVO.getSearchStrDate());
	  				info.setSearchEndDate(ctiAnalysisVO.getSearchEndDate());
	  				info.setSTAFF_MBR_ID(mbr.getMBR_ID());
	  				info.setSTAFF_NM(mbr.getSTAFF_NM());
	  				Map<String,Object> mapItem = new HashMap<String, Object>();
	  				for(CodeSetVO item : list_mbr_cd) {
	  					info.setHCALL_GUBUN_1(item.getCD_ID());
	  					int cnt = ctiAnalysisService.get_report_ivr_call_total_by_gubun_1(info);
	  					mapItem.put(item.getCD_ID(), cnt);
			  		}
	  				{
	  					info.setHCALL_GUBUN_1("NONE");
	  					int cnt = ctiAnalysisService.get_report_ivr_call_total_by_gubun_1(info);
	  					mapItem.put("NONE", cnt);
	  				}
	  				info.setMapItem(mapItem);
		  			list.add(info);	
	  			}			  			  		
  				model.addAttribute("list",list);	  			
	  		} else if(analysisWebLink.equals("ivr_compare")) {
	  			List<CtiAnalysisVO> list = new ArrayList<CtiAnalysisVO>();
	  			//상담분류 1단계
	  			CodeSetVO mCodeSetVO = new CodeSetVO();
		  		mCodeSetVO.setCD_MASTER_ID("CID00011");
		  		mCodeSetVO.setHIDE_AT("N");
		  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
		  		//End of 상담분류 1단계
	  			//상담사 리스트
		  		CtiMemberVO ctiStaffVO = new CtiMemberVO();
				ctiStaffVO.setUSE_ST("Y");
				List<CtiMemberVO> list_staff_mbr = ctiMemberService.get_cti_staff_list(ctiStaffVO);
		  		//End of 상담사 리스트
				for(CtiMemberVO mbr : list_staff_mbr) {
					CtiAnalysisVO info = new CtiAnalysisVO();
					info.setSearchStrDate(ctiAnalysisVO.getSearchStrDate());
	  				info.setSearchEndDate(ctiAnalysisVO.getSearchEndDate());
	  				info.setSTAFF_MBR_ID(mbr.getMBR_ID());
	  				info.setSTAFF_NM(mbr.getSTAFF_NM());
	  			
	  				List<CtiAnalysisVO> list_mbr = ctiAnalysisService.get_report_ivr_compare_call_total_by_mbr_id(info);
	  				info.setArrItem(list_mbr);
	  				
  					List<CtiAnalysisVO> list_mbr_ivr = ctiAnalysisService.get_report_ivr_compare_call_total_detail_ivr_by_mbr_id(info);
  					info.setArrItem2(list_mbr_ivr);
  				
		  			list.add(info);	
	  			}			  			  		
  				model.addAttribute("list",list);
	  		} else if(analysisWebLink.equals("cvpl")) {
	  			List<CtiAnalysisVO> list = new ArrayList<CtiAnalysisVO>();
	  			//상담사 리스트
		  		CtiMemberVO ctiStaffVO = new CtiMemberVO();
				ctiStaffVO.setUSE_ST("Y");
				List<CtiMemberVO> list_staff_mbr = ctiMemberService.get_cti_staff_list(ctiStaffVO);
				//End of 상담사 리스트
				for(CtiMemberVO mbr : list_staff_mbr) {
					CtiAnalysisVO info = new CtiAnalysisVO();
					info.setSearchStrDate(ctiAnalysisVO.getSearchStrDate());
	  				info.setSearchEndDate(ctiAnalysisVO.getSearchEndDate());
	  				info.setSTAFF_MBR_ID(mbr.getMBR_ID());
	  				info.setSTAFF_NM(mbr.getSTAFF_NM());
	  				
	  				List<CtiAnalysisVO> list_mbr = ctiAnalysisService.get_report_cvpl_call_total_by_mbr_id(info);
	  				info.setArrItem(list_mbr);
	  				
	  				list.add(info);	
				}
				model.addAttribute("list",list);
	  		}
  		}
		
  		model.addAttribute("searchStrDate",ctiAnalysisVO.getSearchStrDate());
  		model.addAttribute("searchEndDate",ctiAnalysisVO.getSearchEndDate());
  		
		return mModelAndView;
		
  	}
  	
	//관리자(CTI) 통계부분 엑셀다운로드 ------------------------------------------------------------
	@RequestMapping(value = "/cti/analysis/dwld/excel/report_ajax.do", method = RequestMethod.POST)
	public void ajax_cti_dwld_excel_report_data(@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		JSONObject data = new JSONObject();
  		
		@SuppressWarnings("resource")
		SXSSFWorkbook wb = new SXSSFWorkbook();
		SXSSFSheet sheet = null;
		SXSSFRow row = null;
		String excelName = "";
		Cell cell = null;
		Cell cell2 = null;
  	    
		PublicUtils mPublicUtils = new PublicUtils();
  	    
		try{
			List<CtiCallHistoryVO> list = ctiCallHistoryService.get_cti_callhstry_excel_list(ctiCallHistoryVO);
  	    	excelName = "낚시정보종합포털(" + mPublicUtils.changePatternString(ctiCallHistoryVO.getHCALL_STR_DT(), "yyyy-MM-dd", "yyyyMMdd") + "-" 
  	    			+ mPublicUtils.changePatternString(ctiCallHistoryVO.getHCALL_END_DT(), "yyyy-MM-dd", "yyyyMMdd") + ").xlsx";
  	    	
  	    	CellStyle style = wb.createCellStyle();
  		    style.setAlignment(CellStyle.ALIGN_CENTER);
  		    
  		    // headerStyle
  		    CellStyle headerStyle = wb.createCellStyle();
  		    CellStyle headerStyle2 = wb.createCellStyle();
  		    
  		    Font font = wb.createFont();
  		    font.setColor(HSSFColor.BLACK.index);
  		    font.setBold(true);	
  		    headerStyle.setFont(font);
  		    //테두리 라인
  		    headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
  		    headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
  		    headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
  		    headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
  		    headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

  		    headerStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
  		    headerStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
  		    headerStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
  		    headerStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
  		    headerStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
  		    //배경색
  		    //headerStyle.setFillForegroundColor(HSSFColor.AQUA.index);  
  		    headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
  		    
  		    sheet = wb.createSheet("CTI 통계");
  		    int rowIndex = 0;
  			row = sheet.createRow(rowIndex);
  			int col_index = 0;
  			
  			String[] col_name = {"회원명","회원 전화번호","회원 생년월일","콜상태","분류","발신번호","상담일자","통화시간(초)","상담사", "상담요청 IVR", "상담분류1", "상담분류2", "상담내용"};
  			
  			for(int i = 0; i < col_name.length; i++) {
  				cell = row.createCell(col_index++);
  				cell.setCellValue(col_name[i]);
  				cell.setCellStyle(headerStyle);
  			}
  			
  		    rowIndex++;
  			
  			for (CtiCallHistoryVO items : list) {
  		    	col_index = 0;
  		    	row = sheet.createRow(rowIndex);
  		    			    	
  		    	//엑셀에 들어가는 값
  		    	String[] value = {
  		    		items.getMBR_NM(), items.getMBR_TEL(), items.getMBR_BIRTH(), items.getHCALL_ST(), items.getHCALL_TYPE(), items.getHCALL_S_TEL(), items.getHCALL_DT(), items.getHCALL_TIME(), items.getSTAFF_NM(), items.getHCALL_IVR(), items.getCD_NM1(), items.getCD_NM2(), items.getHCALL_CONT()
  		    	};
  		    	
  		    	for(int i = 0; i < value.length; i++) {
  		    		cell2 = row.createCell(col_index);
  		    		cell2.setCellValue(value[i]);
  		    		cell2.setCellStyle(headerStyle2);
  		    		col_index++;
  		    	}
  		      	rowIndex++;
  		    }
  			
  			sheet.setColumnWidth(0, 3000);
  			sheet.setColumnWidth(1, 4000);
  			sheet.setColumnWidth(2, 4000);
  			sheet.setColumnWidth(3, 2000);
  			sheet.setColumnWidth(4, 3000);
  			sheet.setColumnWidth(5, 5000);
  			sheet.setColumnWidth(6, 6000);
  			sheet.setColumnWidth(7, 5000);
  			sheet.setColumnWidth(8, 3000);
  			sheet.setColumnWidth(9, 9000);
  			sheet.setColumnWidth(10, 9000);
  			sheet.setColumnWidth(11, 9000);
  			sheet.setColumnWidth(12, 20000);
  	    } catch(Exception e){
  	    	e.printStackTrace();
  			data.put("error", "1"+e.toString());
  			data.put("msg", "일시적으로 처리되지 못했습니다.\n잠시후 다시 시도해주세요.");
  	    }
  	    LOGGER.debug("엑셀 처리 완료");
  		
  	    response.setContentType("Application/Msexcel");
  	    response.setHeader("Content-Disposition", "attachement; filename=\"" + URLEncoder.encode(excelName, "UTF-8") + "\";charset=\"UTF-8\"");
  	
  		OutputStream fileOut  = response.getOutputStream();
  		wb.write(fileOut);
  		
  		response.getOutputStream().flush();
  		fileOut.close();
    	}
}


