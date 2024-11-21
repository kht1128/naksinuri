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

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;

public class GongmoEventExcel extends AbstractExcelView
{
	private static final Logger LOGGER = LoggerFactory.getLogger(GongmoEventExcel.class);
	
  protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String sCurTime = null;
    sCurTime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(new Date());

    NaksinuriEventVO info = (NaksinuriEventVO)model.get("info");
    
    String excelName = sCurTime+"_"+info.getEvn_subject()+"_이벤트참여자_엑셀다운로드.xls";
    Sheet worksheet = null;
    Row row = null;

    CellStyle style = workbook.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_CENTER);

    List<NaksinuriEventVO> listExcel = (List<NaksinuriEventVO>)model.get("list");

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
      
      columnIndex++;
    }

    row = worksheet.createRow(0);
    row.createCell(0).setCellValue("이름");
    row.createCell(1).setCellValue("휴대폰번호");
    row.createCell(2).setCellValue("업로드한 URL");
    row.createCell(3).setCellValue("참여일자");
    row.createCell(4).setCellValue("비고");

    int rowIndex = 1;

    for (NaksinuriEventVO eventVO : listExcel) {
      row = worksheet.createRow(rowIndex);
      row.createCell(0).setCellValue(eventVO.getEco_name());
      row.createCell(1).setCellValue(eventVO.getEco_gongmo_hp());
      row.createCell(2).setCellValue(eventVO.getEco_gongmo_url());
      row.createCell(3).setCellValue(eventVO.getEco_insert_dt());
      row.createCell(4).setCellValue(eventVO.getEco_content());
      rowIndex++;
    }

    LOGGER.debug("공모전 댓글 이벤트 엑셀 다운 완료!");
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