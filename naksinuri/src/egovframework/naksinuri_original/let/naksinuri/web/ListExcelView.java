package egovframework.naksinuri_original.let.naksinuri.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;

public class ListExcelView extends AbstractExcelView {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ListExcelView.class);

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sCurTime = null;
        sCurTime = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date());
     
        String excelName = sCurTime + "_샘플엑셀다운로드.xls";
        Sheet worksheet = null;
        Sheet worksheet1 = null;
        Row row = null;
        Row row1 = null;
        CellStyle style = workbook.createCellStyle(); // 셀 스타일을 위한 변수
        style.setAlignment(CellStyle.ALIGN_CENTER); // 글 위치를 중앙으로 설정
         
        List<SurveyVO> listExcel = (List<SurveyVO>) model.get("list");
        List<SurveyVO> Q_list = (List<SurveyVO>) model.get("q_list");
        //List<SurveyVO> quest = (List<SurveyVO>) model.get("quest");
        List<SurveyVO> first_row = (List<SurveyVO>) model.get("first_row"); 
        List<SurveyVO> ann = (List<SurveyVO>) model.get("ann");
        // 새로운 sheet를 생성한다.
        worksheet = workbook.createSheet("객관식");
        worksheet1 = workbook.createSheet("주관식");
        
        // 가장 첫번째 줄에 제목을 만든다.
        row = worksheet.createRow(0);

        int columnIndex = 0;
        while (columnIndex < 3) {
             
            if(columnIndex == 0) {
                
            } else if (columnIndex == 1) {
            	worksheet.setColumnWidth(columnIndex, 20000);
            } else if (columnIndex == 2) {
            	worksheet.setColumnWidth(columnIndex, 3000);
            } else if (columnIndex == 3) {
                worksheet.setColumnWidth(columnIndex, 3000);
            }
            
            
            columnIndex++;
        }
        
        row1=worksheet1.createRow(0);
       
        int cidx=0;
        while (cidx<6){
        	if(cidx == 0) {
                worksheet1.setColumnWidth(cidx, 10000);
            } else if (cidx == 1) {
                worksheet1.setColumnWidth(cidx, 6000);
            } else if (cidx == 2) {
                worksheet1.setColumnWidth(cidx, 10000);
            } else if (cidx == 3) {
                worksheet1.setColumnWidth(cidx, 10000);
            } else if (cidx == 4) {
                worksheet1.setColumnWidth(cidx, 10000);
            }else if (cidx == 5) {
                worksheet1.setColumnWidth(cidx, 10000);
            }
        	cidx++;
        }
     // 헤더 설정
        
        row = worksheet.createRow(0);
        row.createCell(0).setCellValue("순서");
        row.createCell(1).setCellValue("문제");
        row.createCell(2).setCellValue("퍼센트");
        row.createCell(3).setCellValue("인원");
        
        row1 = worksheet1.createRow(0);
        row1.createCell(0).setCellValue("주관식 문항");
        row1.createCell(1).setCellValue("ip");
        row1.createCell(2).setCellValue("이름");
        row1.createCell(3).setCellValue("이메일");
        row1.createCell(4).setCellValue("전화번호");
        row1.createCell(5).setCellValue("내용");
        
        
        
        int rowIndex2=1;
        for(SurveyVO surveyVO : listExcel){
        	row1 = worksheet1.createRow(rowIndex2);
        	row1.createCell(0).setCellValue(surveyVO.getSv_subject());
        	row1.createCell(1).setCellValue(surveyVO.getSva_mb_ip());
        	row1.createCell(2).setCellValue(surveyVO.getAs_name());
        	row1.createCell(3).setCellValue(surveyVO.getAs_email());
        	row1.createCell(4).setCellValue(surveyVO.getAs_tel());
        	row1.createCell(5).setCellValue(surveyVO.getSva_txt());
        	
        	rowIndex2++;
        }


       double tot_cnt = Double.parseDouble(ann.get(0).getSurvey_anscnt());
  
        int count_per = Q_list.size();
        int idx=0;
        int idx2=1;
        int rowIndex = 1;
        for(SurveyVO surveyVO : first_row) {
        	if(idx2 > 1) {
            	rowIndex++;
            }
        	row = worksheet.createRow(rowIndex);
        	row.createCell(0).setCellValue(" "+idx2);
        	row.createCell(1).setCellValue(surveyVO.getSvq_subject());
            
        	//row.createCell(0).setCellStyle(style);
        	
        	rowIndex++;            
            for(SurveyVO surveyVOAnswer : Q_list) {
            	if(!surveyVOAnswer.getSq_id().equals(surveyVO.getSq_id())) continue;
    
            	double selector = Double.parseDouble(surveyVOAnswer.getSelector());
            	double result = selector/tot_cnt*100;
            	String percent = String.format("%.2f", result);
            	
            	row = worksheet.createRow(rowIndex);
            	row.createCell(1).setCellValue(surveyVOAnswer.getSvq_item_txt());
	            //row.createCell(2).setCellValue(percent+"%test3");
            	row.createCell(2).setCellValue(surveyVOAnswer.getPercentage());
            	//System.out.println("surveyVOAnswer.getPercentage():"+surveyVOAnswer.getPercentage());
            
	            row.createCell(3).setCellValue(selector);
	            
	            //row.createCell(2).setCellStyle(style);
	            //row.createCell(3).setCellStyle(style);
	            
	            rowIndex++;	            
            }
            /*
            row.createCell(0).setCellValue(surveyVO.getItem1());
            if(idx<count_per&&surveyVO.getItem1()!=null){
                row.createCell(1).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector())*100/tot_cnt+"%");
                row.createCell(2).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector()));
                idx++;
            }
           
            
           
            rowIndex++;
            row = worksheet.createRow(rowIndex);
            row.createCell(0).setCellValue(surveyVO.getItem2());
            
            if(idx<count_per&&surveyVO.getItem2()!=null){
                row.createCell(1).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector())*100/tot_cnt+"%");
                row.createCell(2).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector()));
                idx++;
            }
           
           
            rowIndex++;
            row = worksheet.createRow(rowIndex);
            row.createCell(0).setCellValue(surveyVO.getItem3());
            
            if(idx<count_per&&surveyVO.getItem3()!=null){
                row.createCell(1).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector())*100/tot_cnt+"%");
                row.createCell(2).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector()));
                idx++;
            }
            
           
            rowIndex++;
            row = worksheet.createRow(rowIndex);
            row.createCell(0).setCellValue(surveyVO.getItem4());
            
            if(idx<count_per&&surveyVO.getItem4()!=null){
                row.createCell(1).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector())*100/tot_cnt+"%");
                row.createCell(2).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector()));
                idx++;
            }
           
            
            rowIndex++;
            row = worksheet.createRow(rowIndex);
            row.createCell(0).setCellValue(surveyVO.getItem5());
            
            if(idx<count_per&&surveyVO.getItem5()!=null){
                row.createCell(1).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector())*100/tot_cnt+"%");
                row.createCell(2).setCellValue(Integer.parseInt(Q_list.get(idx).getSelector()));
                idx++;
            }
            */
            
            //rowIndex++;
            idx2++;
        }
        
        LOGGER.debug("엑셀 처리 완료!!");
       
        try {
            response.setHeader("Content-Disposition", "attachement; filename=\""+ java.net.URLEncoder.encode(excelName, "UTF-8") + "\";charset=\"UTF-8\"");
          } catch (UnsupportedEncodingException e) {
            LOGGER.error("fail UnsupportedEncodingException");
          }
		
	}

	





}
