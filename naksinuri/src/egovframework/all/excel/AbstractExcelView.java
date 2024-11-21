package egovframework.all.excel;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

public abstract class AbstractExcelView extends AbstractView {
    
	private static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public AbstractExcelView(){}

    @Override
    protected boolean generatesDownloadContent(){
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(Map<String,Object> model , HttpServletRequest request , HttpServletResponse response) throws Exception{
        boolean isXLSX = false;
        String excel_name = (String)model.get("excel_name");
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
    	
        Workbook workbook = null;
        if(isXLSX) {
        	workbook = createWorkbookXLSX();
        	setContentType(CONTENT_TYPE_XLSX);
        } else {
        	workbook = createWorkbook();
        }

        buildExcelDocument(model , workbook , request , response);

        response.setContentType(getContentType());

        ServletOutputStream out = response.getOutputStream();
        out.flush();
        workbook.write(out);
        out.flush();
        
        if(workbook instanceof SXSSFWorkbook){
            ((SXSSFWorkbook) workbook).dispose();
        }
    }
    
    protected abstract Workbook createWorkbook();
    protected abstract Workbook createWorkbookXLSX();

    protected abstract void buildExcelDocument(Map<String,Object> model, Workbook workbook , HttpServletRequest request , HttpServletResponse response) throws Exception;
}