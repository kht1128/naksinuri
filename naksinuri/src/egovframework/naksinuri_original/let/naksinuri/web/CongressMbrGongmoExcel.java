package egovframework.naksinuri_original.let.naksinuri.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksiCongressMbrVO;

public class CongressMbrGongmoExcel extends AbstractExcelView
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CongressMbrGongmoExcel.class);
	
  protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String sCurTime = null;
    sCurTime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(new Date());

    String searchStatus = (String)model.get("searchStatus");
    BoardVO info = (BoardVO)model.get("info");
    Sheet worksheet = null;
    Row row = null;
    String excelName = "";
    
	excelName = sCurTime+"_"+info.getBo_subject()+"_참가자리스트_"+searchStatus+"_엑셀다운로드.xls";
    CellStyle style = workbook.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_CENTER);

    List<NaksiCongressMbrVO> listExcel = (List<NaksiCongressMbrVO>)model.get("list");

    worksheet = workbook.createSheet("엑셀 목록");

    row = worksheet.createRow(0);

    int columnIndex = 0;
    while (columnIndex < 7)
    {
      if (columnIndex == 0)
        worksheet.setColumnWidth(columnIndex, 3000);
      else if (columnIndex == 1)
        worksheet.setColumnWidth(columnIndex, 4000);
      else if (columnIndex == 2)
        worksheet.setColumnWidth(columnIndex, 6000);
      else if (columnIndex == 3)
        worksheet.setColumnWidth(columnIndex, 4000);
      else if (columnIndex == 4)
        worksheet.setColumnWidth(columnIndex, 7000);
      else if (columnIndex == 5)
        worksheet.setColumnWidth(columnIndex, 7000);
      else if (columnIndex == 6)
        worksheet.setColumnWidth(columnIndex, 7000);
      else if (columnIndex == 7)
        worksheet.setColumnWidth(columnIndex, 7000);
      else if (columnIndex == 8)
        worksheet.setColumnWidth(columnIndex, 7000);
      else if (columnIndex == 9) 
        worksheet.setColumnWidth(columnIndex, 7000);
      else if (columnIndex == 10)
          worksheet.setColumnWidth(columnIndex, 7000);
      else if (columnIndex == 11)
          worksheet.setColumnWidth(columnIndex, 7000);
      
      columnIndex++;
    }

    row = worksheet.createRow(0);
    row.createCell(0).setCellValue("접수일자");
    row.createCell(1).setCellValue("접수번호");
    row.createCell(2).setCellValue("응모부문");
    row.createCell(3).setCellValue("성명");
    row.createCell(4).setCellValue("응모자구분");
    row.createCell(5).setCellValue("주소");
    row.createCell(6).setCellValue("연락처");
    row.createCell(7).setCellValue("이메일");
    row.createCell(8).setCellValue("작품제목");
    row.createCell(9).setCellValue("작품설명");
    row.createCell(10).setCellValue("첨부파일경로");
    row.createCell(11).setCellValue("접수상태");
    
    int rowIndex = 1;

    for (NaksiCongressMbrVO naksiCongressMbrVO : listExcel) {
      row = worksheet.createRow(rowIndex);
      row.createCell(0).setCellValue(naksiCongressMbrVO.getRegdate());
      row.createCell(1).setCellValue(naksiCongressMbrVO.getMbr_group());
      row.createCell(2).setCellValue(naksiCongressMbrVO.getMbr_cate());
      row.createCell(3).setCellValue(naksiCongressMbrVO.getMbr_name());
      row.createCell(4).setCellValue(naksiCongressMbrVO.getMbr_class());
      row.createCell(5).setCellValue(naksiCongressMbrVO.getMbr_address()+"/"+naksiCongressMbrVO.getMbr_address_jibun());
      row.createCell(6).setCellValue(naksiCongressMbrVO.getMbr_hp());
      row.createCell(7).setCellValue(naksiCongressMbrVO.getMbr_email());
      row.createCell(8).setCellValue(naksiCongressMbrVO.getWork_subject());
      row.createCell(9).setCellValue(naksiCongressMbrVO.getWork_summary());
      row.createCell(10).setCellValue("http://www.naksinuri.kr/cmm/fms/FileDown.do?atchFileId="+naksiCongressMbrVO.getBo_atch_file()+"&fileSn=0");
      row.createCell(11).setCellValue(naksiCongressMbrVO.getMbr_status());
      
      rowIndex++;
    }
    
    LOGGER.debug("엑셀 처리 완료!!");
    try
    {
      response.setHeader("Content-Disposition", "attachement; filename=\"" + URLEncoder.encode(excelName, "UTF-8") + "\";charset=\"UTF-8\"");
    }
    catch (UnsupportedEncodingException e)
    {
    	LOGGER.error("fail UnsupportedEncodingException");
    }
  }
  
}
