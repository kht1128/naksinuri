package egovframework.cti.curriculum.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.login.service.LoginVO;
import egovframework.cti.curriculum.service.CtiCurriculumService;
import egovframework.cti.curriculum.service.CtiCurriculumVO;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicUtils;

@Controller
@EnableWebMvc
public class CtiCurriculumController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(CtiCurriculumController.class);
	
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;

	@Resource(name = "ctiCurriculumService")
	private CtiCurriculumService ctiCurriculumService;
	
	@Resource(name = "eduCategoryService")
	private EduCategoryService eduCategoryService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	

	//CTI 교육커리큘럼 리스트 
	@RequestMapping(value = "/cti/curriculum/list_ajax.do")
	public ModelAndView ajax_cti_curriculum_list(@ModelAttribute("ctiCurriculumVO") CtiCurriculumVO ctiCurriculumVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/curriculum/list_ajax");
  		
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			mModelAndView.setViewName("cti/error/unauth");
		} 

		//교육그룹 코드 조회(전체)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_all_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(비활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("Y");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_prev_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_trgt_cd",list_mbr_cd);
		}
		//교육기관목록
		{
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setUSE_ST("1");
			eduCategoryInsInfVO.setNotUsedPagination(true);
			List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
			model.addAttribute("list_ins_info_cd",edu_category_ins_inf);	
		}
		//----------------------------------------------------------------
		// 공통조건
		//----------------------------------------------------------------
		model.addAttribute("LOCK_ST",ctiCurriculumVO.getLOCK_ST());
		
		String searchYear = "";
		if(ctiCurriculumVO.getSearchYear()!=null) {
			searchYear = ctiCurriculumVO.getSearchYear();
		} else {
			searchYear = mPublicUtils.currentTime("YYYY");
			ctiCurriculumVO.setSearchYear(searchYear);
		}
		//----------------------------------------------------------------
		List<CtiCurriculumVO> curriculum_list = null;

		ctiCurriculumVO.setPageUnit(5);
		
		//날짜 필터
		if(ctiCurriculumVO.getSearchRangeDate().length()!=0) {
  			String[] rangeDates = ctiCurriculumVO.getSearchRangeDate().split("~");
  			for(int i=0; i<rangeDates.length; i++) {
  				if(rangeDates[i]!=null) {
  					String d = rangeDates[i].trim();
  					if(i == 0) ctiCurriculumVO.setSearchStrDate(d + " 00:00:00");
  					if(i == 1) ctiCurriculumVO.setSearchEndDate(d + " 23:59:59");
  				}
  			}
  		}
		//End of 날짜 필터
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ctiCurriculumVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ctiCurriculumVO.getPageUnit());
		paginationInfo.setPageSize(ctiCurriculumVO.getPageSize());

		ctiCurriculumVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ctiCurriculumVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ctiCurriculumVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		curriculum_list = ctiCurriculumService.get_edu_curriculum_list(ctiCurriculumVO);
		
		int totCnt = ctiCurriculumService.get_edu_curriculum_list_totcnt(ctiCurriculumVO);
		ctiCurriculumVO.setTotalRecordCount(totCnt);			
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);	
		
		//model.addAttribute("frm_CRS_GRP_CD",ctiCurriculumVO.getCRS_GRP_CD());
		model.addAttribute("frm_CRS_GRP_CD",ctiCurriculumVO.getCRS_GRP_CD());
		//model.addAttribute("frm_CRS_GRP_CD_2",ctiCurriculumVO.getCRS_GRP_CD());
		model.addAttribute("searchYear",searchYear);
		model.addAttribute("list",curriculum_list);
		
		model.addAttribute("searchStrDate",ctiCurriculumVO.getSearchStrDate());
		model.addAttribute("searchEndDate",ctiCurriculumVO.getSearchEndDate());
		
		return mModelAndView;
	}
	
	
	//CTI 교육커리큘럼 리스트 - 교육신청 용
	@RequestMapping(value = "/cti/curriculum/list_ajax_popup.do")
	public ModelAndView ajax_cti_curriculum_list_popup(@ModelAttribute("ctiCurriculumVO") CtiCurriculumVO ctiCurriculumVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/curriculum/list_ajax_popup");
  		
		PublicUtils mPublicUtils = new PublicUtils();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || Integer.parseInt(loginVO.getMBR_LV_ID()) >= 10) {
			mModelAndView.setViewName("cti/error/unauth");
		} 
		
		//교육그룹 코드 조회(전체)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_all_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(비활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("Y");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_prev_edu_grp_cd",list_edu_grp_cd);
		}
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}
		//대상구분 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00002");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_mbr_trgt_cd",list_mbr_cd);
		}
		//교육기관목록
		{
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setUSE_ST("1");
			eduCategoryInsInfVO.setNotUsedPagination(true);
			List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
			model.addAttribute("list_ins_info_cd",edu_category_ins_inf);	
		}
		
		ctiCurriculumVO.setNotUsedPagination(true);
		List<CtiCurriculumVO> list = ctiCurriculumService.get_edu_curriculum_list(ctiCurriculumVO);
		
		model.addAttribute("list",list);
		
		model.addAttribute("CUSTOM_UNIQ_KEY", ctiCurriculumVO.getCUSTOM_UNIQ_KEY());	
		
		return mModelAndView;
	}
	
}


