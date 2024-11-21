package egovframework.naksinuri_original.let.naksinuri.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class CommonVO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonVO.class);
	
	protected ModelMap model;
	protected PaginationInfo paginationInfo = new PaginationInfo();

	protected final static int PAGE_UNIT = 10;
	protected final static int PAGE_SIZE = 10;
	
	/** 레코드 번호 */
    private int rn = 0;
    
	/** 현재 페이지*/
	private int pageIndex= 1;
	
	/** 페이지사이즈*/
	private int pageSize= 10;
	
	/** 첫페이지*/
	private int firstIndex= 1;
	
	private int lastIndex= 1;
	
	/** 페이지당 레코드 갯수*/
	private int recordCountPerPage= 10;
	
	/** 페이지 총 갯수*/
	private int tot_cnt = 0;
	
    /** 페이지갯수 */
    private int pageUnit= 10;
    
	/** 페이징 기능 처리 쿼리 사용안함  */
	private boolean isNotUsedPagination;

    
	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getTot_cnt() {
		return tot_cnt;
	}

	public void setTot_cnt(int tot_cnt) {
		this.tot_cnt = tot_cnt;
	}
	/**
	 * 페이지정보 Setting
	 */
	public void setPageInfo(ModelMap model){
		try {
			this.model = model;
			
			// 페이지 네비게이션 value
			paginationInfo.setCurrentPageNo(this.getPageIndex());
			paginationInfo.setPageSize(this.getPageSize() == 0 ? this.PAGE_SIZE : this.getPageSize()); 
			paginationInfo.setRecordCountPerPage(this.getPageUnit() == 0 ? this.PAGE_UNIT : this.getPageUnit());
	    	
	    	this.setFirstIndex(paginationInfo.getFirstRecordIndex());
	    	this.setLastIndex(paginationInfo.getLastRecordIndex());
	    	this.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	    	model.addAttribute("paginationInfo", paginationInfo);
	    	model.addAttribute("pageUnit", paginationInfo.getRecordCountPerPage());
	    	
		} catch(Exception e){
			LOGGER.debug("[fail set pagination] "+e.toString());
		}
	}

	/**
	 * 페이지 총 개수(totalPage)와
	 * 결과 총 ROW수(resultCnt) Setting
	 * @param totCnt - 총 개수
	 */
//	@Autowired
	public void setTotalPage(int totCnt) throws Exception{
		try {
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("totalPage", paginationInfo.getTotalPageCount());
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("pageIndex", paginationInfo.getCurrentPageNo());
			
		}catch(Exception e){
			LOGGER.debug("[fail TotalPage 설정 오류] "+e.toString());
		}
	}

	public boolean isNotUsedPagination() {
		return isNotUsedPagination;
	}

	public void setNotUsedPagination(boolean isNotUsedPagination) {
		this.isNotUsedPagination = isNotUsedPagination;
	}
	
}
