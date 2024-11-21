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
import egovframework.naksinuri_original.let.naksinuri.service.NaksiCongressOwnVO;

public class CongressMbrExcel extends AbstractExcelView
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CongressMbrExcel.class);
	
  protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String sCurTime = null;
    sCurTime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(new Date());

    String searchStatus = (String)model.get("searchStatus");
    BoardVO info = (BoardVO)model.get("info");
    String owner = (String)model.get("owner");
    Sheet worksheet = null;
    Row row = null;
    String excelName = "";
    if(owner.equals("Y")) {
    	excelName = sCurTime+"_"+info.getBo_subject()+"_참가자리스트_"+searchStatus+"_엑셀다운로드.xls";
    	
    	CellStyle style = workbook.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	
	    List<NaksiCongressOwnVO> listExcel = (List<NaksiCongressOwnVO>)model.get("list");
	
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
	        worksheet.setColumnWidth(columnIndex, 3000);
	      else if (columnIndex == 6)
	        worksheet.setColumnWidth(columnIndex, 3000);
	      else if (columnIndex == 7)
	        worksheet.setColumnWidth(columnIndex, 3000);
	      else if (columnIndex == 8)
	        worksheet.setColumnWidth(columnIndex, 3000);
	      else if (columnIndex == 9) {
	        worksheet.setColumnWidth(columnIndex, 3000);
	      }
	
	      columnIndex++;
	    }
	
	    row = worksheet.createRow(0);
	    row.createCell(0).setCellValue("신청자명");
	    row.createCell(1).setCellValue("성별");
	    row.createCell(2).setCellValue("생년월일");
	    row.createCell(3).setCellValue("휴대폰번호");
	    row.createCell(4).setCellValue("주소");
	    row.createCell(5).setCellValue("이메일");
	    //row.createCell(6).setCellValue("구명조끼지참여부");//현재 안씀
	    row.createCell(6).setCellValue("입금금액");
	    row.createCell(7).setCellValue("입금자명");
	    row.createCell(8).setCellValue("입금일자");
	    row.createCell(9).setCellValue("낚시경력");
	    row.createCell(10).setCellValue("유입경로");
	    row.createCell(11).setCellValue("참가이유");
	    row.createCell(12).setCellValue("등록일");
	    row.createCell(13).setCellValue("요청사항 및 의견");
	    row.createCell(14).setCellValue("총신청인원");
	    row.createCell(15).setCellValue("신청그룹코드");
	
	    int rowIndex = 1;
	
	    for (NaksiCongressOwnVO naksiCongressOwnVO : listExcel) {
	      row = worksheet.createRow(rowIndex);
	      row.createCell(0).setCellValue(naksiCongressOwnVO.getOwn_name());
	      row.createCell(1).setCellValue(naksiCongressOwnVO.getOwn_gender());
	      row.createCell(2).setCellValue(naksiCongressOwnVO.getOwn_birth());
	      row.createCell(3).setCellValue(naksiCongressOwnVO.getOwn_hp());
	      row.createCell(4).setCellValue(naksiCongressOwnVO.getOwn_address());
	      row.createCell(5).setCellValue(naksiCongressOwnVO.getOwn_email());
	      row.createCell(6).setCellValue(naksiCongressOwnVO.getOwn_deposit_amount());
	      row.createCell(7).setCellValue(naksiCongressOwnVO.getOwn_repre_name());
	      row.createCell(8).setCellValue(naksiCongressOwnVO.getOwn_depo_date());
	      row.createCell(9).setCellValue(naksiCongressOwnVO.getOwn_naksi_career());
	      row.createCell(10).setCellValue(naksiCongressOwnVO.getOwn_inflow_path());
	      row.createCell(11).setCellValue(naksiCongressOwnVO.getOwn_attend_cause());
	      row.createCell(12).setCellValue(naksiCongressOwnVO.getRegdate());
	      row.createCell(13).setCellValue(naksiCongressOwnVO.getOwn_etc());
	      row.createCell(14).setCellValue(naksiCongressOwnVO.getMbr_total_count());
	      row.createCell(15).setCellValue(naksiCongressOwnVO.getMbr_group());
	      
	      rowIndex++;
	    }
    	
    } else {
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
	        worksheet.setColumnWidth(columnIndex, 3000);
	      else if (columnIndex == 6)
	        worksheet.setColumnWidth(columnIndex, 3000);
	      else if (columnIndex == 7)
	        worksheet.setColumnWidth(columnIndex, 3000);
	      else if (columnIndex == 8)
	        worksheet.setColumnWidth(columnIndex, 3000);
	      else if (columnIndex == 9) {
	        worksheet.setColumnWidth(columnIndex, 3000);
	      }
	
	      columnIndex++;
	    }
	
	    row = worksheet.createRow(0);
	    row.createCell(0).setCellValue("참가자명");
	    row.createCell(1).setCellValue("입금자명");
	    row.createCell(2).setCellValue("입금일자");
	    row.createCell(3).setCellValue("입금금액");
	    row.createCell(4).setCellValue("관계");
	    row.createCell(5).setCellValue("휴대폰번호");
	    row.createCell(6).setCellValue("주소");
	    row.createCell(7).setCellValue("소속");
	    row.createCell(8).setCellValue("구명조끼지참여부");
	    row.createCell(9).setCellValue("낚시경력");
	    row.createCell(10).setCellValue("생년월일");
	    row.createCell(11).setCellValue("성별");
	    row.createCell(12).setCellValue("요청사항 및 의견");
	    //row.createCell(10).setCellValue("유입경로");
	    //row.createCell(11).setCellValue("참가동기");
	    row.createCell(13).setCellValue("접수상태");
	    row.createCell(14).setCellValue("신청그룹코드");
	    row.createCell(14).setCellValue("참여유형");
	
	    int rowIndex = 1;
	
	    for (NaksiCongressMbrVO naksiCongressMbrVO : listExcel) {
	      row = worksheet.createRow(rowIndex);
	      row.createCell(0).setCellValue(naksiCongressMbrVO.getMbr_name());
	      row.createCell(1).setCellValue(naksiCongressMbrVO.getRepre_name());
	      row.createCell(2).setCellValue(naksiCongressMbrVO.getDepo_date().substring(0, 16));
	      row.createCell(3).setCellValue(naksiCongressMbrVO.getDeposit_amount());
	      row.createCell(4).setCellValue(naksiCongressMbrVO.getMbr_relation());
	      row.createCell(5).setCellValue(naksiCongressMbrVO.getMbr_hp());
	      row.createCell(6).setCellValue(naksiCongressMbrVO.getMbr_address());
	      row.createCell(7).setCellValue(naksiCongressMbrVO.getMbr_belongto());
	      row.createCell(8).setCellValue(naksiCongressMbrVO.getJacket_offer());
	      row.createCell(9).setCellValue(naksiCongressMbrVO.getNaksi_career());
	      row.createCell(10).setCellValue(naksiCongressMbrVO.getMbr_birth());	      
	      row.createCell(11).setCellValue(naksiCongressMbrVO.getMbr_gender());
	      row.createCell(12).setCellValue(naksiCongressMbrVO.getEtc());
	      //row.createCell(10).setCellValue(naksiCongressMbrVO.getInflow_path());
	      //row.createCell(11).setCellValue(naksiCongressMbrVO.getAttend_cause());
	      row.createCell(13).setCellValue(naksiCongressMbrVO.getMbr_status());
	      row.createCell(14).setCellValue(naksiCongressMbrVO.getMbr_group());
	      row.createCell(14).setCellValue(naksiCongressMbrVO.getCi_type());
	
	      rowIndex++;
	    }
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
