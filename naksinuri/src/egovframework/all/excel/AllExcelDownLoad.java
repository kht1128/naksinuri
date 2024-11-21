package egovframework.all.excel;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.Form;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.excel.AllExcelDownLoadBundle.MERGE;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.DwldConfimFileVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.PublicFormatPatternUtil;
import egovframework.utils.PublicUtils;

public class AllExcelDownLoad extends AbstractExcelView {

	private static final Logger LOGGER = LoggerFactory.getLogger(AllExcelDownLoad.class);
	
	private FileVO saveFileVO = null;
	private EgovFileMngService fileMngService;
	private EgovPropertyService propertyService;
	private SmsManagerService smsManagerService;
	private EduMemberService eduMemberService;
	
	public AllExcelDownLoad(EgovPropertyService propertyService, EgovFileMngService fileMngService, 
			SmsManagerService smsManagerService, EduMemberService eduMemberService, FileVO saveFileVO) {
		this.fileMngService = fileMngService;
		this.saveFileVO = saveFileVO;
		this.propertyService = propertyService;
		this.smsManagerService = smsManagerService;
		this.eduMemberService = eduMemberService;
	}
	
	@Override
	protected Workbook createWorkbook() {
		return new HSSFWorkbook();
	}
	
	@Override
	protected Workbook createWorkbookXLSX() {
		return new XSSFWorkbook();
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		SXSSFWorkbook sxssfWorkbook = null;
		boolean isXLSX = false;
		String excel_type = (String)model.get("excel_type");
		String excel_name = (String)model.get("excel_name");
		String excel_uniquekey = (String)model.get("excel_uniquekey");
		boolean isMngrConfim = ((String)model.get("excel_mngr_confim")).equals("Y");
		String excelDownloadPath = "";
		int indvdlInfoMaxCnt = 100;//개인정보취급시 해제 최대값
		int indvdlInfoCnt = 0;//개인정보취급건수
		
		LOGGER.debug("엑셀생성시작 ##########################");
		if(excel_name!=null && excel_name.length()!=0) {
    		String ext = excel_name.substring( excel_name.lastIndexOf( "." ) + 1 );
    		if(ext.equalsIgnoreCase("XLSX")) {
    			isXLSX = true;	
    		} else {
    			isXLSX = false;
    		}
		} else {
			isXLSX = false;
		}
		
		Sheet worksheet = null;
	    Row row = null;
	    
	    CellStyle title_style = workbook.createCellStyle();
	    {
	    	//정렬
		    title_style.setAlignment(CellStyle.ALIGN_CENTER);
		    title_style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		    title_style.setWrapText(true);//줄바꿈 가능하도록 설정
		    //폰트 설정
		    Font fontOfGothic = workbook.createFont();
		    //fontOfGothic.setFontName("고딕");
		    if(isXLSX) {
		    	fontOfGothic.setColor(new XSSFColor(java.awt.Color.BLACK).getIndex());
		    } else {
		    	fontOfGothic.setColor(HSSFColor.BLACK.index);
		    }
		    fontOfGothic.setBold(true);
		    //fontOfGothic.setFontHeight((short)14);
		    title_style.setFont(fontOfGothic);
		    //배경색
		    if(isXLSX) {
		    	//title_style.setFillForegroundColor(new XSSFColor(java.awt.Color.WHITE).getIndex());
			    //title_style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		    } else {
			    title_style.setFillForegroundColor(HSSFColor.WHITE.index);
			    title_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    }
		    //	
	    }  
		CellStyle title_sub_style = workbook.createCellStyle();
		{    
			//정렬
			title_sub_style.setAlignment(CellStyle.ALIGN_LEFT);
			title_sub_style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			title_sub_style.setWrapText(true);//줄바꿈 가능하도록 설정
			//배경색
			if(isXLSX) {
				//title_style.setFillForegroundColor(new XSSFColor(java.awt.Color.WHITE).getIndex());
			    //title_style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			} else {
				title_sub_style.setFillForegroundColor(HSSFColor.WHITE.index);
				title_sub_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			}
		    //	
		}		
		CellStyle label_style = workbook.createCellStyle();
		{
		    //정렬
			label_style.setAlignment(CellStyle.ALIGN_CENTER);
		    label_style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//		    label_style.setWrapText(true);//줄바꿈 가능하도록 설정
		    //테두리 라인
		    if(isXLSX) {
		    	label_style.setBorderRight(XSSFCellStyle.BORDER_THIN);
			    label_style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			    label_style.setBorderTop(XSSFCellStyle.BORDER_THIN);
			    label_style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		    } else {
		    	label_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    label_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    label_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    label_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    }
		    //폰트 설정
		    Font fontOfGothic = workbook.createFont();
		    //fontOfGothic.setFontName("고딕");
		    if(isXLSX) {
		    	//fontOfGothic.setColor(new XSSFColor(java.awt.Color.BLACK).getIndex());
		    } else {
		    	fontOfGothic.setColor(HSSFColor.BLACK.index);
		    }
		    fontOfGothic.setBold(true);
		    label_style.setFont(fontOfGothic);
		    //배경색
		    if(isXLSX) {
		    	//label_style.setFillForegroundColor(new XSSFColor(java.awt.Color.WHITE).getIndex());
			    //label_style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		    } else {
			    label_style.setFillForegroundColor(HSSFColor.WHITE.index);
			    label_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    }
		    //	
		}			
		CellStyle style = workbook.createCellStyle();
		{
		    //정렬
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//		    style.setWrapText(true);//줄바꿈 가능하도록 설정
		    //테두리 라인
		    if(isXLSX) {
		    	style.setBorderRight(XSSFCellStyle.BORDER_THIN);
			    style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			    style.setBorderTop(XSSFCellStyle.BORDER_THIN);
			    style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		    } else {
			    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    }
		    /*
		    //폰트 설정
		    Font fontOfGothic = workbook.createFont();
		    //fontOfGothic.setFontName("고딕");
		    fontOfGothic.setColor(HSSFColor.BLACK.index);
		    style.setFont(fontOfGothic);
		    */
		    //배경색
		    if(isXLSX) {
		    	//style.setFillForegroundColor(new XSSFColor(java.awt.Color.WHITE).getIndex());
			    //style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		    } else {
		    	style.setFillForegroundColor(HSSFColor.WHITE.index);
			    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);	
		    }
		    //
		    //text포맷스타일지정
		    if(isXLSX) {
		    	XSSFDataFormat dataFormat = (XSSFDataFormat)workbook.createDataFormat();
		    	style.setDataFormat(dataFormat.getFormat("@"));
		    }
		}
		CellStyle summary_style = workbook.createCellStyle();
		{
		    //정렬
			summary_style.setAlignment(CellStyle.ALIGN_CENTER);
			summary_style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			summary_style.setWrapText(true);//줄바꿈 가능하도록 설정
		    //테두리 라인
		    if(isXLSX) {
		    	summary_style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		    	summary_style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		    	summary_style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		    	summary_style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		    } else {
		    	summary_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    summary_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    summary_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    summary_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    }

		    //폰트 설정
		    Font fontOfGothic = workbook.createFont();
		    //fontOfGothic.setFontName("고딕");
		    fontOfGothic.setColor(HSSFColor.RED.index);
		    summary_style.setFont(fontOfGothic);

		    //배경색
		    if(isXLSX) {
		    	//summary_style.setFillForegroundColor(new XSSFColor(java.awt.Color.WHITE).getIndex());
			    //summary_style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		    } else {
		    	summary_style.setFillForegroundColor(HSSFColor.WHITE.index);
		    	summary_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);	
		    }
		    //
		}
	    
		if(isXLSX) {
	    	sxssfWorkbook = new SXSSFWorkbook((XSSFWorkbook)workbook);
	    	worksheet = sxssfWorkbook.createSheet("엑셀데이터");
	    } else {
	    	worksheet = workbook.createSheet("엑셀데이터");
	    }
	    //눈금선 없애기
	    //worksheet.setDisplayGridlines(false);
	    //
	    
		if(excel_type==null || excel_type.length()==0) {
			LOGGER.debug("비정상적인 접근");					
		} else {
			
			int colMaxIndex = 0;
			int rowMaxIndex = 0;
			HashMap<String,int[]> arrMergeCell = new HashMap<String,int[]>();
			
			List<CodeSetVO> list_all_cd = (List<CodeSetVO>)model.get("list_all_cd");
			
			AllExcelDownLoadBundle.MERGE merge = (AllExcelDownLoadBundle.MERGE)model.get("AllExcelBundleMerge");
			Object[] format_title_obj = AllExcelDownLoadBundle.callTitle(merge);
			Object[] format_obj = AllExcelDownLoadBundle.call(merge);
			if(format_obj==null) {
				LOGGER.debug("처리 할 포맷타입이 존재하지 않음");	
				
			    row = worksheet.createRow(0);
			    row.createCell(0).setCellValue("처리가 불가능한 요청입니니다. 계속 될 경우 관리자에게 문의해주세요.");
				
			} else {
				
				if(isMngrConfim) {
					excelDownloadPath = saveFileVO.getFileStreCours();
					LOGGER.debug("엑셀다운로드 파일 첨부파일 목록에 등록 번호 :" + saveFileVO.getAtchFileId());
				}
				
				int format_title_number = Integer.parseInt((String)format_title_obj[0]);
				String[][] format_title_set = (String[][])format_title_obj[1];
				
				int format_number = Integer.parseInt((String)format_obj[0]);
				String[][] format_set = (String[][])format_obj[1];
				
				LOGGER.debug("조회 기준 값 : " + excel_uniquekey);
				LOGGER.debug("선택된 포맷타입 : " + merge.name());
				LOGGER.debug("선택된 포맷타이틀번호 : " + format_title_number);
				LOGGER.debug("선택된 포맷번호 : " + format_number);
				for(int i=0; i<format_title_set.length; i++) LOGGER.debug("선택된 포맷타이틀 : ["+i+"] = " + Arrays.toString(format_title_set[i]));
				for(int i=0; i<format_set.length; i++) LOGGER.debug("선택된 포맷 : ["+i+"] = " + Arrays.toString(format_set[i]));
			    colMaxIndex = format_set.length;
			    
				int content_index_number = 0;//행시작번호
			    LOGGER.debug("엑셀 생성 시작..");
			    /* style 주기
			    int columnIndex = 0;
			    while (columnIndex < 3) {
			    	if (columnIndex == 0) 		worksheet.setColumnWidth(columnIndex, 3000);
			    	else if (columnIndex == 1)	worksheet.setColumnWidth(columnIndex, 4000);
			    	else if (columnIndex == 2)	worksheet.setColumnWidth(columnIndex, 6000);
			    	columnIndex++;
			    }
			    */
			    LOGGER.debug("타이틀영역 행 생성..("+content_index_number+")");
			    //타이틀영역
			    for(int i=0;i<format_title_set.length;i++) {
			    	row = worksheet.createRow(content_index_number);
			    	String title = format_title_set[i][0];
			    	String label_key = format_title_set[i][1];
			    	if(label_key.length()!=0) {
			    		title = title.replace("[[LABEL]]", (String)model.get(label_key));
			    	}
			    	Cell cell = row.createCell(0);
			    	cell.setCellValue(title);
			    	if(i==0) {
			    		cell.setCellStyle(title_style);
			    		row.setHeight((short)1000);//1000 = 50 , (20 = 1 대응)
			    	} else {
			    		cell.setCellStyle(title_sub_style);
			    		row.setHeight((short)500);//500 = 25 , (20 = 1 대응)
			    	}
			    	worksheet.addMergedRegion(new CellRangeAddress(i,i,0,format_set.length-1));
			    	content_index_number++;
			    }
			    //End of 타이틀영역
			    LOGGER.debug("상단라벨영역 행 생성..("+content_index_number+")");
			    //상단라벨영역
			    row = worksheet.createRow(content_index_number);
			    for(int i=0;i<format_set.length;i++) {
			    	Cell cell = row.createCell(i);
			    	cell.setCellValue(format_set[i][0]);
			    	cell.setCellStyle(label_style);
			    	//LOGGER.debug("top i = " +i+ " , paremeter_key = " + format_set[i][0]);
			    	
			    	//주석라벨영역
				    if(merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_LOCGOV){
				    	LOGGER.debug("주석라벨영역 행 생성..("+content_index_number+")");
					   
					   	Object[] format_title_summary_obj = AllExcelDownLoadBundle.call(AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_LOCGOV_SUMMARY);
					   	String[][] format_summary_set = (String[][])format_title_summary_obj[1];
					   					   
						String replace_title_summary = format_summary_set[i][1].replace("[[BR]]", "BRBRBRBRBRBR");
						String[] parse_title_summary = replace_title_summary.split("BRBRBRBRBRBR");
						String title_summary = "";
						for(String s : parse_title_summary) {
							title_summary += s + "\n";
						}
						title_summary = title_summary.trim();
						if(title_summary!=null && title_summary.length()!=0) {
							Drawing drawing = cell.getSheet().createDrawingPatriarch();
							//메모크기(0,0,0,0,cell가로번호,cell세로번호,메모가로크기(셀갯수),메모세로크기(셀갯수))
							ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, i, content_index_number, i+3, content_index_number+6);
							Comment comment = drawing.createCellComment(anchor);
							comment.setVisible(false);//편집시 메모 노출 여부
							RichTextString textString = new XSSFRichTextString(title_summary);//메모내용
							comment.setString(textString);    	
						}
				    }
				    //End of 주석라벨영역
			    				    	
			    }
			    content_index_number++;
			    //End of 상단라벨영역
			    LOGGER.debug("본문영역 행 생성..("+content_index_number+")");
			    if(merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST 
	    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_SHIP 
	    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_STORE
	    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_MERGE 
	    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_SHIP_MERGE 
	    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_STORE_MERGE
	    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_LOCGOV	 
	    		) {
			    	LOGGER.debug("[낚시어선업자/낚시터업자]");
			    	int subListMaxCnt = 0;
			    	int rowNumberParentList = 1;
					int rowIndex = content_index_number;
					Map<String,List<Map<String,Object>>> list_sub = (Map<String,List<Map<String,Object>>>) model.get("list_dtl");
					Map<String,List<Map<String,Object>>> list_sub_2 = (Map<String,List<Map<String,Object>>>) model.get("list_mbr_trgt");
					List<Map<String,Object>> list = (List<Map<String,Object>>) model.get("list");
					indvdlInfoCnt = list.size();
				    for (Map<String,Object> item : list) {
				    	String uniquekey = item.get(excel_uniquekey).toString();
				    	List<Map<String,Object>> list_sub_data = (List<Map<String,Object>>)list_sub.get(uniquekey);
				    	subListMaxCnt = list_sub_data.size();
				    	//LOGGER.debug(MBR_NM+"("+MBR_ID+")"+" 회원의 조회 된 회원상세정보 수:"+list_dtl_item.size());
				    	if(list_sub.size() <= 0) { //회원상세정보가 없을 경우 회원기본정보만 표기한다.
				    		row = worksheet.createRow(rowIndex);
				    		for(int i=0;i<format_set.length;i++) {
								boolean isKey = false;//필드값
				    			String paremeter_key = format_set[i][1];
				    			if(paremeter_key!=null && paremeter_key.length()!=0) {
				    				isKey = true;
				    			}
				    			boolean isCodeKey = false;//코드값변환
				    			String paremeter_code = format_set[i][2];
				    			if(paremeter_code!=null && paremeter_code.length()!=0 && paremeter_code.equals("Y")) {
				    				isCodeKey = true;
				    			}
				    			boolean isBaseVal = false;//대체값
				    			String paremeter_base_value = format_set[i][4];
				    			if(paremeter_base_value!=null && paremeter_base_value.length()!=0) {
				    				isBaseVal = true;
				    			}
				    			boolean isSubQuery = false;//서브쿼리
				    			String paremeter_subqry = format_set[i][3];
				    			if(paremeter_subqry!=null && paremeter_subqry.length()!=0) {
				    				isSubQuery = true;
				    			}
				    			boolean isDropDown = false;//드롭다운
				    			String paremeter_dropdown = format_set[i][6];
				    			if(paremeter_dropdown!=null && paremeter_dropdown.length()!=0) {
				    				isDropDown = true;
				    			}
				    			Cell cell = row.createCell(i);
				    			if(isSubQuery) {
				    				cell.setCellValue("");				    				
				    			} else {
			    					if(!isKey) {
			    						cell.setCellValue("");
						    		} else {
						    			try {
						    				String cellValue = "";
						    				if(paremeter_key.contains(",")) {
						    					String merge_label = "";
						    					String[] merge_key = paremeter_key.split(",");
						    					for(String s : merge_key) {
						    						try {
														merge_label += item.get(s).toString();
													} catch (Exception e) {
														LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
													} 
						    					}
						    					cellValue = merge_label;
						    				} else {
						    					try { 
						    						if(paremeter_key.equals("ROW_NUM_INNER")) {
						    							if(merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST 
				    					    		    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_SHIP
				    					    		    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_STORE) {
				    										cellValue = String.valueOf(rowIndex);
				    							    	} else {
				    							    		cellValue = String.valueOf(rowNumberParentList);
				    							    	}
						    						} else {
						    							cellValue = item.get(paremeter_key).toString();
						    						}
						    					} catch(Exception e) {
						    						LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
						    					}
						    				}
						    				if(isCodeKey) {
						    					for(CodeSetVO item_code : list_all_cd) {
						    						if(item_code.getCD_ID().equals(cellValue)) cellValue = item_code.getCD_NM();
						    					}
						    				}
						    				if(isBaseVal) {
						    					if(paremeter_base_value.contains(",")) {
							    					String[] merge_key = paremeter_base_value.split(",");
							    					for(String s1 : merge_key) {
							    						String[] s2 = s1.split(":");
							    						String key = s2[0];
							    						String val = s2[1];
							    						if(key.equals(cellValue)) {
							    							cellValue = val;
						    							}
							    					}
						    					} else {
						    						if(cellValue.length()==0) {
						    							cellValue = paremeter_base_value;
						    						}
						    					}
						    				}
						    				cell.setCellValue(cellValue);
						    			} catch(Exception e) {
						    				LOGGER.debug("데이터처리 중 에러(1)" + e.toString());
						    				cell.setCellValue("");
						    			}
						    		}
			    				}
				    			cell.setCellStyle(style);
				    			//드롭다운 배치
				    			if(isDropDown) {
				    				String[] dropDownDataList = paremeter_dropdown.split(",");
				    				worksheet = addDropDown(worksheet, rowIndex, i, dropDownDataList);
				    			}
							    //End of 드롭다운 배치
							}
				    		worksheet.autoSizeColumn(rowIndex);
				    		//worksheet.setColumnWidth(rowIndex, (worksheet.getColumnWidth(rowIndex))+(short)1024);
				    		rowIndex++;
				    	} else { //회원상세정보를 보유한 경우
				    		int subrow = 0;
					    	for (Map<String,Object> list_sub_data_item : list_sub_data) {
								row = worksheet.createRow(rowIndex);
								for(int i=0;i<format_set.length;i++) {
									boolean isKey = false;
					    			String paremeter_key = format_set[i][1];
					    			if(paremeter_key!=null && paremeter_key.length()!=0) {
					    				isKey = true;
					    			}
					    			boolean isBaseVal = false;
					    			String paremeter_base_value = format_set[i][4];
					    			if(paremeter_base_value!=null && paremeter_base_value.length()!=0) {
					    				isBaseVal = true;
					    			}
					    			boolean isCodeKey = false;
					    			String paremeter_code = format_set[i][2];
					    			if(paremeter_code!=null && paremeter_code.length()!=0 && paremeter_code.equals("Y")) {
					    				isCodeKey = true;
					    			}
					    			boolean isSubQuery = false;
					    			String paremeter_subqry = format_set[i][3];
					    			if(paremeter_subqry!=null && paremeter_subqry.length()!=0) {
					    				isSubQuery = true;
					    			}
					    			boolean isFormat = false;//포맷타입
					    			String paremeter_format = format_set[i][5];
					    			if(paremeter_format!=null && paremeter_format.length()!=0) {
					    				isFormat = true;
					    			}
					    			boolean isDropDown = false;//드롭다운
					    			String paremeter_dropdown = format_set[i][6];
					    			if(paremeter_dropdown!=null && paremeter_dropdown.length()!=0) {
					    				isDropDown = true;
					    			}
					    			Cell cell = row.createCell(i);
					    			if(isSubQuery) {					    				
				    					if(!isKey) {
				    						cell.setCellValue("");
							    		} else {
							    			String tempLogMessage = "";
							    			try {
							    				String cellValue = "";
							    				if(paremeter_key.contains(",")) {
							    					tempLogMessage += "(1)";
							    					String merge_label = "";
							    					String[] merge_key = paremeter_key.split(",");
							    					for(String s : merge_key) {							    						
							    						if(paremeter_subqry.equals("1")) {
							    							try { 
							    								merge_label += list_sub_data_item.get(s).toString(); 
							    							} catch(Exception e) { 
							    								LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
							    							}
							    						} else if(paremeter_subqry.equals("2")) {
							    							//서브 2 인 경우 
							    						} else {//메인 인 경우
							    							try { 
							    								merge_label += item.get(s).toString(); 
							    							} catch(Exception e) { 
							    								LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
							    							}
							    						}
							    					}
							    					cellValue = merge_label;
							    					tempLogMessage += "(1-1)";
							    				} else {
							    					if(paremeter_subqry.equals("1")) {
							    						try { 
							    							if(paremeter_key.equals("ROW_NUM_INNER")) {
							    								tempLogMessage += "(2)";
								    							cellValue = String.valueOf(rowIndex);
								    						} else {
								    							tempLogMessage += "(2-1)";
								    							cellValue = list_sub_data_item.get(paremeter_key).toString();
								    						}
							    						} catch(Exception e) { }
							    						tempLogMessage += "(2-2)";
						    						} else if(paremeter_subqry.equals("2")) {
						    							tempLogMessage += "(3)";
						    							String substr = "";
						    							List<Map<String,Object>> list_sub_data_2 = (List<Map<String,Object>>)list_sub_2.get(uniquekey);
						    							if(list_sub_data_2.size() > 0) {
						    								tempLogMessage += "(3-1)";
						    								for (Map<String,Object> item_2 : list_sub_data_2) {
						    									if(item_2 == null || item_2.get("CRS_GRP_NM")==null || item_2.get("CRS_GRP_NM").toString()==null || item_2.get("CRS_GRP_NM").toString().length()==0) {
						    										continue;
						    									}
						    									if(item_2.get(paremeter_key)!=null) {
							    									String chkVal = item_2.get(paremeter_key).toString();
							    									if(isBaseVal) {
													    					if(paremeter_base_value.contains(",")) {
														    					String[] merge_key = paremeter_base_value.split(",");
														    					for(String s1 : merge_key) {
														    						String[] s2 = s1.split(":",-1);
														    						String key = s2[0];
														    						String val = s2[1];
														    						if(key.equals(chkVal)) {
														    							substr += val;
													    							} 
														    					}
													    					}
													    					String LRNNG_CMPLT_DT =(String) item_2.get("LRNNG_CMPLT_DT");
													    					if(LRNNG_CMPLT_DT != null && paremeter_key.equals("MBR_LRNNG_CMPLT_ST")){
													    						substr += "(" + LRNNG_CMPLT_DT + ")";
													    					}
													    					
													    					substr += " ["+item_2.get("CRS_GRP_NM").toString()+" "+item_2.get("CRS_NM").toString()+" "+item_2.get("CAT_INS_NM").toString()+" ("+item_2.get("CRS_PLACE").toString()+") ] ";                      
												    				} else {
												    					substr = chkVal;
												    				}	
						    									}
							    						    } 
						    								tempLogMessage += "(3-2)";
						    							}
						    							cellValue = substr.trim();
						    						} else {//메인 인 경우
						    							tempLogMessage += "(4)";
						    							try { 
						    								if(paremeter_key.equals("ROW_NUM_INNER")) {
						    									if(merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST 
						    					    		    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_SHIP
						    					    		    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_STORE) {
						    										tempLogMessage += "(4-1)";
						    										cellValue = String.valueOf(rowIndex);
						    							    	} else {
						    							    		tempLogMessage += "(4-2)";
						    							    		cellValue = String.valueOf(rowNumberParentList);
						    							    	}
								    						} else {
								    							tempLogMessage += "(4-3)";
								    							cellValue = item.get(paremeter_key).toString();
								    						}
						    							} catch(Exception e) { 
						    								LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
						    							}
						    						}
							    				}
							    				if(isCodeKey) {
							    					tempLogMessage += "(5)";
							    					for(CodeSetVO item_code : list_all_cd) {
							    						if(item_code.getCD_ID().equals(cellValue)) cellValue = item_code.getCD_NM();
							    					}
							    					tempLogMessage += "(5-1)";
							    				}
							    				if(isBaseVal) {
							    					if(paremeter_base_value.contains(",")) {
							    						tempLogMessage += "(6)";
								    					String[] merge_key = paremeter_base_value.split(",");
								    					for(String s1 : merge_key) {
								    						String[] s2 = s1.split(":",-1);
								    						String key = s2[0];
								    						String val = s2[1];
								    						if(key.equals(cellValue)) {
								    							tempLogMessage += "(6-1)";
								    							cellValue = val;
							    							}
								    					}
								    					tempLogMessage += "(6-2)";
							    					} else {
							    						tempLogMessage += "(7)";
							    						if(cellValue.length()==0) {
							    							tempLogMessage += "(7-1)";
							    							cellValue = paremeter_base_value;
							    						}
							    					}
							    				}
							    				
							    				if(isFormat){//포맷타입에 값넣기
							    					String str = item.get(paremeter_key).toString();
							    					String format_data = "";
							    					PublicFormatPatternUtil publicFormatPatternUtil = new PublicFormatPatternUtil();
							    					
							    					if(str != null && str.length() != 0){
								    					if(paremeter_format.equals("HP") || paremeter_format.equals("TEL")){
								    						format_data = publicFormatPatternUtil.phoneHypen(str);
								    					} else if(paremeter_format.equals("BIRTH")) {
								    						format_data = publicFormatPatternUtil.birthHypen(str);
								    					}
								    					cellValue = format_data;
							    					} 
							    				}
							    				
							    				cell.setCellValue(cellValue);
							    				LOGGER.debug("1구간 | cellValue : " + cellValue);
							    			} catch(Exception e) {
							    				LOGGER.debug("데이터처리 중 에러(2) ["+uniquekey+"] " + e.toString() + " , " + tempLogMessage);
							    				e.printStackTrace();
							    				cell.setCellValue("");
							    			}
							    			tempLogMessage = null;
							    		}
				    					cell.setCellStyle(style);
				    				} else {
				    					if(!isKey) {
				    						cell.setCellValue("");
							    		} else {
							    			try {
							    				String cellValue = "";
							    				if(paremeter_key.contains(",")) {
							    					String merge_label = "";
							    					String[] merge_key = paremeter_key.split(",");
							    					for(String s : merge_key) {
							    						//여기는 서브에 대해서 처리 할 수 없지 않을까?
							    						try { 
							    							merge_label += item.get(s).toString(); 
							    						} catch(Exception e) { 
							    							LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
							    						}
							    					}
							    					cellValue = merge_label;
							    				} else {
							    					try { 
							    						if(paremeter_key.equals("ROW_NUM_INNER")) {
							    							if(merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST 
					    					    		    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_SHIP
					    					    		    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_STORE) {
					    										cellValue = String.valueOf(rowIndex);
					    							    	} else {
					    							    		cellValue = String.valueOf(rowNumberParentList);
					    							    	}
							    						} else {	
							    							cellValue = item.get(paremeter_key).toString();
							    						}
							    						
							    						
							    						
							    					} catch(Exception e) { 
							    						LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
							    					}
							    				}
							    				if(isCodeKey) {
							    					for(CodeSetVO item_code : list_all_cd) {
							    						if(item_code.getCD_ID().equals(cellValue)) cellValue = item_code.getCD_NM();
							    					}
							    				}
							    				if(isBaseVal) {
							    					if(paremeter_base_value.contains(",")) {
								    					String[] merge_key = paremeter_base_value.split(",");
								    					for(String s1 : merge_key) {
								    						String[] s2 = s1.split(":",-1);
								    						String key = s2[0];
								    						String val = s2[1];
								    						if(key.equals(cellValue)) {
								    							cellValue = val;
							    							}
								    					}
							    					} else {
							    						if(cellValue.length()==0) {
							    							cellValue = paremeter_base_value;
							    						}
							    					}
							    				}
							    				
							    				if(isFormat){//포맷타입에 값넣기
							    					String str = item.get(paremeter_key).toString();
							    					String format_data = "";
							    					PublicFormatPatternUtil publicFormatPatternUtil = new PublicFormatPatternUtil();
							    					
							    					if(str != null && str.length() != 0){
								    					if(paremeter_format.equals("HP") || paremeter_format.equals("TEL")){
								    						format_data = publicFormatPatternUtil.phoneHypen(str);
								    					} else if(paremeter_format.equals("BIRTH")) {
								    						format_data = publicFormatPatternUtil.birthHypen(str);
								    					}
								    					cellValue = format_data;
							    					} 
							    				}
							    				cell.setCellValue(cellValue);
							    				LOGGER.debug("2구간 | cellValue : " + cellValue);
							    			} catch(Exception e) {
							    				LOGGER.debug("데이터처리 중 에러(3)" + e.toString());
							    				cell.setCellValue("");
							    			}							    			
							    		}
				    					cell.setCellStyle(style);
				    				}
					    			//병합을 위한 처리
					    			boolean isPass = false;
					    			if(merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST 
			    		    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_SHIP
			    		    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_STORE
			    		    		|| merge == AllExcelDownLoadBundle.MERGE.EDUADM_MEMBER_EDULIST_LOCGOV) {
					    				isPass = true;
					    			}
					    			if(!isSubQuery && subrow!=0 && !isPass) {
				    					cell.setCellValue("");
			    						int[] addI = null;
				    					int rowIndexKey = rowIndex-subrow;
				    					if(arrMergeCell.containsKey("rowIndex"+rowIndexKey)) {
				    						if(subListMaxCnt-1 == subrow) {
				    							boolean isAdd = true;
				    							int[] oriI = arrMergeCell.get("rowIndex"+rowIndexKey);
				    							for(int c=0; c<oriI.length; c++) {
				    								if(c!=0 && oriI[c] == i) isAdd = false;
					    						}
				    							if(isAdd) {
				    								addI = new int[oriI.length+1];
				    								for(int c=0; c<oriI.length; c++) {
						    							addI[c] = oriI[c];
						    						}
				    								addI[oriI.length] = i;
				    							} else {
				    								addI = oriI;
				    							}
				    						}
				    					} else {
				    						addI = new int[2];
				    						addI[0] = subListMaxCnt;
				    						addI[1] = i;
				    					}		
				    					if(addI!=null) arrMergeCell.put("rowIndex"+rowIndexKey, addI);				    					
				    				} 
					    			LOGGER.debug("1구간 | isSubQuery : " + isSubQuery+" , subrow : " + subrow +" , isPass : " + isPass + " , i : "+i);
					    			//
					    			//드롭다운 배치
					    			if(isDropDown) {
					    				String[] dropDownDataList = paremeter_dropdown.split(",");
					    				worksheet = addDropDown(worksheet, rowIndex, i, dropDownDataList);
					    			}
								    //End of 드롭다운 배치
								}
								worksheet.autoSizeColumn(rowIndex);
								//worksheet.setColumnWidth(rowIndex, (worksheet.getColumnWidth(rowIndex))+(short)1024);
								rowIndex++;
								subrow++;
					    	}
				    	}	
				    	rowNumberParentList++;
				    }
				    rowMaxIndex = rowIndex;
			    } else if(merge == AllExcelDownLoadBundle.MERGE.EDUADM_CERTIFICATE_LIST_STORE
			    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_CERTIFICATE_LIST_SHIP
			    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_MBRHSTRY_LIST
			    || merge == AllExcelDownLoadBundle.MERGE.EDUADM_MBRHSTRY_LIST_NONE_MERGE 
			    ) {
			    	LOGGER.debug("[보고서-이수증발급대장][수강자목록]");
			    	int subListMaxCnt = 0; 
				    int rowIndex = content_index_number;
				    List<Map<String,Object>> list = (List<Map<String,Object>>) model.get("list");
				    indvdlInfoCnt = list.size();
				    Map<String,List<Map<String,Object>>> list_sub = (Map<String,List<Map<String,Object>>>) model.get("list_dtl");
				    for (Map<String,Object> item : list) {
				    	List<Map<String,Object>> list_sub_data = null;
				    	if(list_sub!=null) {
				    		String uniquekey = item.get(excel_uniquekey).toString();
				    		list_sub_data = (List<Map<String,Object>>)list_sub.get(uniquekey);
				    		subListMaxCnt = list_sub_data.size();
				    	}
				    	if(list_sub_data!=null && subListMaxCnt > 0) { //추가 정보가 있는 경우
				    		int subrow = 0;
			    			for (Map<String,Object> list_sub_data_item : list_sub_data) {
				    			row = worksheet.createRow(rowIndex);	
				    			for(int i=0;i<format_set.length;i++) {
						    		boolean isKey = false;
					    			String paremeter_key = format_set[i][1];
					    			if(paremeter_key!=null && paremeter_key.length()!=0) {
					    				isKey = true;
					    			}
					    			boolean isBaseVal = false;
					    			String paremeter_base_value = format_set[i][4];
					    			if(paremeter_base_value!=null && paremeter_base_value.length()!=0) {
					    				isBaseVal = true;
					    			}
					    			boolean isCodeKey = false;
					    			String paremeter_code = format_set[i][2];
					    			if(paremeter_code!=null && paremeter_code.length()!=0 && paremeter_code.equals("Y")) {
					    				isCodeKey = true;
					    			}
					    			boolean isSubQuery = false;
					    			String paremeter_subqry = format_set[i][3];
					    			if(paremeter_subqry!=null && paremeter_subqry.length()!=0) {
					    				isSubQuery = true;
					    			}
					    			boolean isFormat = false;//포맷타입
					    			String paremeter_format = format_set[i][5];
					    			if(paremeter_format!=null && paremeter_format.length()!=0) {
					    				isFormat = true;
					    			}
					    			boolean isDropDown = false;//드롭다운
					    			String paremeter_dropdown = format_set[i][6];
					    			if(paremeter_dropdown!=null && paremeter_dropdown.length()!=0) {
					    				isDropDown = true;
					    			}
					    			Cell cell = row.createCell(i);
						    		if(!isKey) {
						    			cell.setCellValue("");
						    		} else {
						    			String cellValue = "";		
						    			try {		    				
						    				if(paremeter_key.contains(",")) {
						    					String merge_label = "";
						    					String[] merge_key = paremeter_key.split(",");
						    					for(String s : merge_key) {
						    						if(paremeter_subqry.equals("1")) {
						    							try { 
						    								merge_label += list_sub_data_item.get(s).toString(); 
						    							} catch(Exception e) { 
						    								LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
						    							}
						    						} else {
						    							try {
						    								merge_label += item.get(s).toString(); 
						    							} catch(Exception e) { 
						    								LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
						    							}
						    						}						    						
						    					}
						    					cellValue = merge_label;
						    											    					
						    				} else {
						    					if(paremeter_subqry.equals("1")) {
						    						try { 
						    							cellValue = list_sub_data_item.get(paremeter_key).toString(); 
					    							} catch(Exception e) { 
					    								LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
					    							}
					    						} else {
					    							try { 
					    								cellValue = item.get(paremeter_key).toString(); 
				    								} catch(Exception e) { 
				    									LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
				    								}
					    						}
						    				}
						    				if(isCodeKey) {
						    					for(CodeSetVO item_code : list_all_cd) {
						    						if(item_code.getCD_ID().equals(cellValue)) cellValue = item_code.getCD_NM();
						    					}
						    				}
						    				if(isBaseVal) {
						    					if(paremeter_base_value.contains(",")) {
							    					String[] merge_key = paremeter_base_value.split(",");
							    					for(String s1 : merge_key) {
							    						String[] s2 = s1.split(":",-1);
							    						String key = s2[0];
							    						String val = s2[1];
							    						if(key.equals(cellValue)) {
							    							cellValue = val;
						    							}
							    					}
						    					} else {
						    						if(cellValue.length()==0) {
						    							cellValue = paremeter_base_value;
						    						}
						    					}
						    				}
						    				
						    				if(isFormat){//포맷타입에 값넣기
						    					String str = item.get(paremeter_key).toString();
						    					String format_data = "";
						    					PublicFormatPatternUtil publicFormatPatternUtil = new PublicFormatPatternUtil();
						    					
						    					if(str != null && str.length() != 0){
							    					if(paremeter_format.equals("HP") || paremeter_format.equals("TEL")){
							    						format_data = publicFormatPatternUtil.phoneHypen(str);
							    					} else if(paremeter_format.equals("BIRTH")) {
							    						format_data = publicFormatPatternUtil.birthHypen(str);
							    					}
							    					cellValue = format_data;
						    					} 
						    				}
						    				cell.setCellValue(cellValue);
						    			} catch(Exception e) {
						    				LOGGER.debug("데이터처리 중 에러(7)" + e.toString());
						    				cell.setCellValue("");
						    			}
						    			//병합을 위한 처리
						    			boolean isPass = false;
						    			if(merge == AllExcelDownLoadBundle.MERGE.EDUADM_MBRHSTRY_LIST_NONE_MERGE) {
						    				isPass = true;
						    			}
						    			if(!isSubQuery && subrow!=0 && !isPass) {
					    					cell.setCellValue("");
				    						int[] addI = null;
					    					int rowIndexKey = rowIndex-subrow;
					    					if(arrMergeCell.containsKey("rowIndex"+rowIndexKey)) {
					    						if(subListMaxCnt-1 == subrow) {
					    							boolean isAdd = true;
					    							int[] oriI = arrMergeCell.get("rowIndex"+rowIndexKey);
					    							for(int c=0; c<oriI.length; c++) {
					    								if(c!=0 && oriI[c] == i) isAdd = false;
						    						}
					    							if(isAdd) {
					    								addI = new int[oriI.length+1];
					    								for(int c=0; c<oriI.length; c++) {
							    							addI[c] = oriI[c];
							    						}
					    								addI[oriI.length] = i;
					    							} else {
					    								addI = oriI;
					    							}
					    						}
					    					} else {
					    						addI = new int[2];
					    						addI[0] = subListMaxCnt;
					    						addI[1] = i;
					    					}		
					    					if(addI!=null) arrMergeCell.put("rowIndex"+rowIndexKey, addI);				    					
					    				} 
						    			//
						    		}
						    		cell.setCellStyle(style);
						    		//드롭다운 배치
						    		if(isDropDown) {
						    			String[] dropDownDataList = paremeter_dropdown.split(",");
						    			worksheet = addDropDown(worksheet, rowIndex, i, dropDownDataList);
						    		}
								    //End of 드롭다운 배치
							    }
						    	subrow++;
						    	worksheet.autoSizeColumn(rowIndex);
						    	//worksheet.setColumnWidth(rowIndex, (worksheet.getColumnWidth(rowIndex))+(short)1024);
						    	rowIndex++;
				    		}
				    	} else { //추가 정보가 없는 경우
				    		row = worksheet.createRow(rowIndex);
					    	for(int i=0;i<format_set.length;i++) {
					    		boolean isKey = false;
				    			String paremeter_key = format_set[i][1];
				    			if(paremeter_key!=null && paremeter_key.length()!=0) {
				    				isKey = true;
				    			}
				    			boolean isBaseVal = false;
				    			String paremeter_base_value = format_set[i][4];
				    			if(paremeter_base_value!=null && paremeter_base_value.length()!=0) {
				    				isBaseVal = true;
				    			}
				    			boolean isCodeKey = false;
				    			String paremeter_code = format_set[i][2];
				    			if(paremeter_code!=null && paremeter_code.length()!=0 && paremeter_code.equals("Y")) {
				    				isCodeKey = true;
				    			}
				    			/*
				    			boolean isSubQuery = false;
				    			String paremeter_subqry = format_set[i][3];
				    			if(paremeter_subqry!=null && paremeter_subqry.length()!=0) {
				    				isSubQuery = true;
				    			}
				    			*/
				    			boolean isDropDown = false;//드롭다운
				    			String paremeter_dropdown = format_set[i][6];
				    			if(paremeter_dropdown!=null && paremeter_dropdown.length()!=0) {
				    				isDropDown = true;
				    			}
				    			Cell cell = row.createCell(i);
					    		if(!isKey) {
					    			cell.setCellValue("");
					    		} else {
					    			try {
					    				String cellValue = "";				    				
					    				if(paremeter_key.contains(",")) {
					    					String merge_label = "";
					    					String[] merge_key = paremeter_key.split(",");
					    					for(String s : merge_key) {
					    						try { 
					    							merge_label += item.get(s).toString(); 
					    						} catch(Exception e) { 
					    							LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
					    						}
					    					}
					    					cellValue = merge_label;
					    				} else {
					    					try { 
					    						cellValue = item.get(paremeter_key).toString(); 
					    					} catch(Exception e) { 
					    						LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
					    					}
					    				}
					    				if(isCodeKey) {
					    					for(CodeSetVO item_code : list_all_cd) {
					    						if(item_code.getCD_ID().equals(cellValue)) cellValue = item_code.getCD_NM();
					    					}
					    				}
					    				if(isBaseVal) {
					    					if(paremeter_base_value.contains(",")) {
						    					String[] merge_key = paremeter_base_value.split(",");
						    					for(String s1 : merge_key) {
						    						String[] s2 = s1.split(":",-1);
						    						String key = s2[0];
						    						String val = s2[1];
						    						if(key.equals(cellValue)) {
						    							cellValue = val;
					    							}
						    					}
					    					} else {
					    						if(cellValue.length()==0) {
					    							cellValue = paremeter_base_value;
					    						}
					    					}
					    				}					    				
					    				cell.setCellValue(cellValue);
					    			} catch(Exception e) {
					    				LOGGER.debug("데이터처리 중 에러(6)" + e.toString());
					    				cell.setCellValue("");
					    			}
					    		}
					    		cell.setCellStyle(style);
					    		//드롭다운 배치
					    		if(isDropDown) {
					    			String[] dropDownDataList = paremeter_dropdown.split(",");
					    			worksheet = addDropDown(worksheet, rowIndex, i, dropDownDataList);
					    		}
							    //End of 드롭다운 배치
						    }
					    	worksheet.autoSizeColumn(rowIndex);
					    	//worksheet.setColumnWidth(rowIndex, (worksheet.getColumnWidth(rowIndex))+(short)1024);
					    	rowIndex++;
				    	}//END else 				    	
				    }
				    rowMaxIndex = rowIndex;
				} else { //분류없는경우
					LOGGER.debug("[분류없는경우]");
					int rowIndex = content_index_number;
				    List<Map<String,Object>> list = (List<Map<String,Object>>) model.get("list");
				    indvdlInfoCnt = list.size();
				    int row_num = list.size();
				    for (Map<String,Object> item : list) {
				    	row = worksheet.createRow(rowIndex);
				    	for(int i=0;i<format_set.length;i++) {
				    		boolean isKey = false;
			    			String paremeter_key = format_set[i][1];
			    			if(paremeter_key!=null && paremeter_key.length()!=0) {
			    				isKey = true;
			    			}
			    			boolean isBaseVal = false;
			    			String paremeter_base_value = format_set[i][4];
			    			if(paremeter_base_value!=null && paremeter_base_value.length()!=0) {
			    				isBaseVal = true;
			    			}
			    			boolean isCodeKey = false;
			    			String paremeter_code = format_set[i][2];
			    			if(paremeter_code!=null && paremeter_code.length()!=0 && paremeter_code.equals("Y")) {
			    				isCodeKey = true;
			    			}
			    			boolean isFormat = false;//포맷타입
			    			String paremeter_format = format_set[i][5];
			    			if(paremeter_format!=null && paremeter_format.length()!=0) {
			    				isFormat = true;
			    			}
			    			/*
			    			boolean isSubQuery = false;
			    			String paremeter_subqry = format_set[i][3];
			    			if(paremeter_subqry!=null && paremeter_subqry.length()!=0 && paremeter_subqry.equals("Y")) {
			    				isSubQuery = true;
			    			}
			    			*/
			    			boolean isDropDown = false;//드롭다운
			    			String paremeter_dropdown = format_set[i][6];
			    			if(paremeter_dropdown!=null && paremeter_dropdown.length()!=0) {
			    				isDropDown = true;
			    			}
			    			Cell cell = row.createCell(i);
				    		if(!isKey) {
				    			cell.setCellValue("");
				    		} else {
				    			try {
				    				String cellValue = "";				    				
				    				if(paremeter_key.contains(",")) {
				    					String merge_label = "";
				    					String[] merge_key = paremeter_key.split(",");
				    					for(String s : merge_key) {
				    						try { 
				    							merge_label += item.get(s).toString(); 
				    						} catch(Exception e) { 
				    							LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
				    						}
				    					}
				    					cellValue = merge_label;
				    				} else {
				    					try { 
				    						if(paremeter_key.equals("ROW_NUM_INNER")) {
				    							cellValue = String.valueOf(row_num);
				    						} else {
				    							cellValue = item.get(paremeter_key).toString();
				    						}
				    					} catch(Exception e) { 
				    						LOGGER.debug("존재하지 않는 null건은 추가하지 않음");
				    					}
				    				}
				    				if(isCodeKey) {
				    					for(CodeSetVO item_code : list_all_cd) {
				    						if(item_code.getCD_ID().equals(cellValue)) cellValue = item_code.getCD_NM();
				    					}
				    				}
				    				if(isBaseVal) {
				    					if(paremeter_base_value.contains(",")) {
					    					String[] merge_key = paremeter_base_value.split(",");
					    					for(String s1 : merge_key) {
					    						String[] s2 = s1.split(":",-1);
					    						String key = s2[0];
					    						String val = s2[1];
					    						if(key.equals(cellValue)) {
					    							cellValue = val;
				    							}
					    					}
				    					} else {
				    						if(cellValue.length()==0) {
				    							cellValue = paremeter_base_value;
				    						}
				    					}
				    				}				    				
				    				if(isFormat){//포맷타입에 값넣기
				    					String str = item.get(paremeter_key).toString();
				    					String format_data = "";
				    					PublicFormatPatternUtil publicFormatPatternUtil = new PublicFormatPatternUtil();
				    					
				    					if(str != null && str.length() != 0){
					    					if(paremeter_format.equals("HP") || paremeter_format.equals("TEL")){
					    						format_data = publicFormatPatternUtil.phoneHypen(str);
					    					} else if(paremeter_format.equals("BIRTH")) {
					    						format_data = publicFormatPatternUtil.birthHypen(str);
					    					}
					    					cellValue = format_data;
				    					} 
				    				}
				    				cell.setCellValue(cellValue);
				    			} catch(Exception e) {
				    				LOGGER.debug("데이터처리 중 에러(4)" + e.toString());
				    				cell.setCellValue("");
				    			}
				    		}
				    		cell.setCellStyle(style);
				    		//드롭다운 배치
				    		if(isDropDown) {
				    			String[] dropDownDataList = paremeter_dropdown.split(",");
				    			worksheet = addDropDown(worksheet, rowIndex, i, dropDownDataList);
				    		}
						    //End of 드롭다운 배치
					    }
				    	row.setHeight((short)600);//600 = 30 , (20 = 1 대응)
				    	worksheet.setDefaultColumnWidth(25);//(1 = 1 대응)
				    	worksheet.autoSizeColumn(rowIndex);
				    	//worksheet.setColumnWidth(rowIndex, (worksheet.getColumnWidth(rowIndex))+(short)1024);
				    	rowIndex++;
				    	row_num--;
				    }	
				    rowMaxIndex = rowIndex;
				}	
			}
			//병합처리 - 행시작, 행종료, 열시작, 열종료
			//LOGGER.debug("rowMaxIndex : " + rowMaxIndex);
			LOGGER.debug("병합처리건 ... " + arrMergeCell.size());
			for(int i=0; i<rowMaxIndex; i++) {
				if(arrMergeCell.containsKey("rowIndex"+i)) {
					int[] mergeData = arrMergeCell.get("rowIndex"+i);
					int strIndex = i;
					int endIndex = i+(mergeData[0]-1);
					LOGGER.debug("["+i+"행 추출("+strIndex+" ~ "+endIndex+")] " + Arrays.toString(mergeData));
					for(int c=1; c<mergeData.length; c++) {
						int cellIndex = mergeData[c];
						LOGGER.debug("[열 "+cellIndex+"]");
						worksheet.addMergedRegion(new CellRangeAddress(strIndex,endIndex,cellIndex,cellIndex));
					}
				}
			}
			LOGGER.debug("셀 가로폭 사이즈 자동 조정 ... ");
			//셀 가로폭 사이즈 자동 조정 처리 구간
			for(int i=0; i<colMaxIndex; i++) {
				try {
					worksheet.autoSizeColumn(i);
					worksheet.setColumnWidth(i, Math.min(255 * 256, worksheet.getColumnWidth(i)+1024));
				} catch(Exception e) {
					LOGGER.debug("(처리불가) ["+i+"] " + e.toString());
					e.printStackTrace();
				}
			}
			//End of 셀 가로폭 사이즈 자동 조정 처리 구간			
			LOGGER.debug("엑셀생성종료 ##########################");	
			if(isXLSX) {
				LOGGER.debug("SXSS 처리 ##########################");	
				((SXSSFSheet)worksheet).flushRows(rowMaxIndex);
			}
		}	
		if(isMngrConfim) { //엑셀 다운로드 승인제
			JSONObject rstdata = new JSONObject();
			FileOutputStream outStream = null;
			try {
				File saveFolder = new File(excelDownloadPath);
				if(!saveFolder.exists()) {
				    if(saveFolder.mkdirs()) {
				    	LOGGER.debug("폴더 생성 완료");
				    } else {
				    	LOGGER.debug("폴더 생성 실패");
				    }
				}
				outStream = new FileOutputStream(excelDownloadPath+"/"+saveFileVO.getStreFileNm());
				if(isXLSX) {
					LOGGER.debug("SXSS 내려받기 및 초기화 ##########################");	
					sxssfWorkbook.write(outStream);
					sxssfWorkbook.dispose();
				} else {	
					LOGGER.debug("일반 내려받기 및 초기화 ##########################");	
					workbook.write(outStream);
					//workbook.close();//해제 없이도 상관 없는지는 테스트가 필요
				}
				//엑셀다운로드 정보 변경				
				long _size = new File(excelDownloadPath+"/"+saveFileVO.getStreFileNm()).length();
				
				boolean isConfirmLockDate = false;
				boolean isConfirmLockLimit = false;
				String rangeTime = "";
				String rangeTimeHour = "";
				String rangeTimeMin = "";
				String rangeTimeSec = "";
    			String reqLimitCnt = "";
    			String lockDateList = "";
    			String workTimeStr = "";
    			String workTimeEnd = "";
				List<CodeSetVO> list_sys_cd = (List<CodeSetVO>)model.get("list_sys_cd");
				for(CodeSetVO item_code : list_sys_cd) {
					if(item_code.getCD_ID().equals("SYS00001")) {//근무시간(시작)
						workTimeStr = item_code.getCD_DES();
					} else if(item_code.getCD_ID().equals("SYS00002")) {//근무시간(종료)
						workTimeEnd = item_code.getCD_DES();
					} else if(item_code.getCD_ID().equals("SYS00003")) {//별도공휴일지정
						lockDateList = item_code.getCD_DES();
					} else if(item_code.getCD_ID().equals("SYS00004")) {//제한시간(시:분:초)
						rangeTime = item_code.getCD_DES();
						rangeTimeHour = rangeTime.split(":")[0].replace("00", "");
						rangeTimeMin = rangeTime.split(":")[1].replace("00", "");
						rangeTimeSec = rangeTime.split(":")[2].replace("00", "");
					} else if(item_code.getCD_ID().equals("SYS00005")) {//초과횟수(제한시간 내)
						reqLimitCnt = item_code.getCD_DES();
					}
				}
				System.out.println(lockDateList);
				System.out.println(lockDateList);
				System.out.println(lockDateList);
				System.out.println(lockDateList);
				System.out.println(lockDateList);
				System.out.println(lockDateList);
    			//공휴일 또는 지정날짜 인 경우 , 근무시간 외
    			if(mPublicUtils.getCurrentTimeDayToString().equals("토") || mPublicUtils.getCurrentTimeDayToString().equals("일")
				|| lockDateList.contains(mPublicUtils.currentTime("yyyy-MM-dd"))
    			|| mPublicUtils.dateCompare(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", 
    					mPublicUtils.currentTime("yyyy-MM-dd")+" "+workTimeStr, "yyyy-MM-dd HH:mm:ss") == PublicUtils.RETURN_COMPARE_TYPE.SMALL_THAN_DATE02
    			|| mPublicUtils.dateCompare(mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", 
    					mPublicUtils.currentTime("yyyy-MM-dd")+" "+workTimeEnd, "yyyy-MM-dd HH:mm:ss") == PublicUtils.RETURN_COMPARE_TYPE.LARGE_THAN_DATE02
    			) {
    				isConfirmLockDate = true;
				} else {
					DwldConfimFileVO chkLockDwldConfimFileVO = new DwldConfimFileVO();
	    			chkLockDwldConfimFileVO.setREG_LIMIT_CNT(reqLimitCnt);
	    			chkLockDwldConfimFileVO.setRANGE_TIME_HOUR(rangeTimeHour);
	    			chkLockDwldConfimFileVO.setRANGE_TIME_MIN(rangeTimeMin);
	    			chkLockDwldConfimFileVO.setRANGE_TIME_SEC(rangeTimeSec);
	    			chkLockDwldConfimFileVO.setREQ_MBR_ID(loginVO.getMBR_ID());
	    			chkLockDwldConfimFileVO.setATCH_FILE_ID("EXCEL_00000000000000");
	    			chkLockDwldConfimFileVO.setUNLOCK_FILE_SN("");
	    			boolean chkLock = fileMngService.get_dwld_req_lock(chkLockDwldConfimFileVO);
	    			if(chkLock) {
	    				isConfirmLockLimit = true;
	    			}
				}
				//
    			FileVO updateFileDwldConfimVO = new FileVO();
				updateFileDwldConfimVO.setFILE_CMPLT_DT("update!");//빈값아니면갱신
				updateFileDwldConfimVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				updateFileDwldConfimVO.setAtchFileId(saveFileVO.getAtchFileId());
				updateFileDwldConfimVO.setFileSn(saveFileVO.getFileSn());
				updateFileDwldConfimVO.setFileMg(Long.toString(_size));
				updateFileDwldConfimVO.setINDVDL_INFO_CNT(String.valueOf(indvdlInfoCnt));
				if((!isConfirmLockDate && !isConfirmLockLimit && indvdlInfoCnt < indvdlInfoMaxCnt)	//100명 이하 인 경우 제한 해제
						|| excel_type.equals("/adm/log/listRecEduadm.do")) {	// 관리자접속기록_엑셀다운로드는 자동승인
					updateFileDwldConfimVO.setCONFIM_ST("Y");
					updateFileDwldConfimVO.setCONFIM_MBR_ID("system");
					updateFileDwldConfimVO.setCONFIM_MSG("[자동승인]");
				} else {
					/*
					EduMemberVO masterMbrInfo = new EduMemberVO();
					masterMbrInfo.setMBR_ID("kjw");
					masterMbrInfo = eduMemberService.get_edu_member_info(masterMbrInfo);
					//승인자에게 문자 발송
					DwldConfimFileVO chkDwldConfimFileVO = new DwldConfimFileVO();
					chkDwldConfimFileVO.setATCH_FILE_ID(saveFileVO.getAtchFileId());
					chkDwldConfimFileVO.setFILE_SN(saveFileVO.getFileSn());
					chkDwldConfimFileVO = fileMngService.get_dwld_confim_info(chkDwldConfimFileVO);
					SmsSendVO newSmsSendVO = new SmsSendVO();
					newSmsSendVO.setMBR_ID(chkDwldConfimFileVO.getREQ_MBR_ID());
					String addComment = "";
					if(isConfirmLockDate) {
						updateFileDwldConfimVO.setREQ_MSG("[공휴일 또는 근무시간 외 요청건]\n"+chkDwldConfimFileVO.getREQ_MSG());
						addComment = "\n(공휴일 또는 근무시간 외 다운로드 요청으로 승인제로 전환)";
					}
					if(isConfirmLockLimit) {
						updateFileDwldConfimVO.setREQ_MSG("["+mPublicUtils.getHMSLabel(rangeTime,"HH:mm:ss")+" 동안 "+reqLimitCnt+"회 이상 요청으로 제한됨	]\n"+chkDwldConfimFileVO.getREQ_MSG());
						addComment = "\n("+mPublicUtils.getHMSLabel(rangeTime,"HH:mm:ss")+" 동안 "+reqLimitCnt+"회 이상 요청건)";
					}
					newSmsSendVO.setMSG(chkDwldConfimFileVO.getREQ_MBR_NM()+"님께서\n\n"+chkDwldConfimFileVO.getORIGNL_FILE_NM()+"\n\n위 파일의 다운로드 승인을 요청하셨습니다."+addComment);
					newSmsSendVO.setS_PHONE(propertyService.getString("Globals.SmsSenderNumber"));//발신번호
					newSmsSendVO.setR_PHONE(masterMbrInfo.getMBR_HP());
					newSmsSendVO.setSUBMSG("낚시전문교육");
					newSmsSendVO.setIMG_CNT(0);
					newSmsSendVO.setIMG_PATH("");
					newSmsSendVO.setREG_MBR_ID(loginVO.getMBR_ID());
					newSmsSendVO.setUPD_MBR_ID(loginVO.getMBR_ID());			
				    newSmsSendVO.setIP(mPublicUtils.getClientIpAddr(request));
					String rstMsg = smsManagerService.sendToMessage(newSmsSendVO);
					LOGGER.debug("승인자에게요청문자발송");
					*/
					//End of 승인자에게 문자 발송
				}
				fileMngService.updateDetailFileInfForConfirm(updateFileDwldConfimVO);
				//End of 엑셀다운로드 정보 변경
				rstdata.put("error", "0");
				rstdata.put("msg", "ok");				
			} catch (Exception e){
				LOGGER.debug("ERROR : " + e.toString());
				rstdata.put("error", "1");
				rstdata.put("msg", "fail");
			} finally {
				if(outStream!=null) outStream.close();
			}			
			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    outputStream.print(rstdata.toString());
		} else {
			response.setHeader("Content-Disposition", "attachement; filename=\"" + URLEncoder.encode(excel_name, "UTF-8") + "\";charset=\"UTF-8\"");
			if(request!=null) {
				String clientBrowserName = mPublicUtils.getClientBrowserName(request);
				if("IE".equalsIgnoreCase(clientBrowserName)) {
					response.setHeader("Content-Type","application/vnd.ms-excel");		
				}	
			}
			if(isXLSX) {
				LOGGER.debug("SXSS 내려받기 및 초기화 ##########################");	
				sxssfWorkbook.write(response.getOutputStream());
				sxssfWorkbook.dispose();
			}
		}
	}

	private Sheet addDropDown(Sheet worksheet, int rowIndex, int colIndex, String[] dropDownList) {
		DataValidationHelper dvHelper = worksheet.getDataValidationHelper();	
		//(int firstRow, int lastRow, int firstCol, int lastCol) 0,5,0,0
	    CellRangeAddressList addressList = new  CellRangeAddressList(rowIndex,rowIndex+1,colIndex,colIndex);
	    DataValidationConstraint dvConstraint =dvHelper.createExplicitListConstraint(dropDownList);
	    DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
	    validation.setSuppressDropDownArrow(true);      
	    worksheet.addValidationData(validation);
	    return worksheet;
	}
	
	
	/* vo를 map형식으로 변환해서 반환
	 * @param vo VO
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> domainToMap(Object vo) throws Exception {
	    return domainToMapWithExcept(vo, null);
	}

	/**
	 * 특정 변수를 제외해서 vo를 map형식으로 변환해서 반환.
	 * @param vo VO
	 * @param arrExceptList 제외할 property 명 리스트
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> domainToMapWithExcept(Object vo, String[] arrExceptList) throws Exception {
	    Map<String, Object> result = new HashMap<String, Object>();
	    BeanInfo info = Introspector.getBeanInfo(vo.getClass());
	    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
	        Method reader = pd.getReadMethod();
	        if (reader != null) {
	            if(arrExceptList != null && arrExceptList.length > 0 && isContain(arrExceptList, pd.getName())) continue;
	            result.put(pd.getName(), reader.invoke(vo));
	        }
	    }
	    return result;
	}
	public Boolean isContain(String[] arrList, String name) {
	    for (String arr : arrList) {
	        if (StringUtils.contains(arr, name))
	            return true;
	    }
	    return false;
	}
	
}
