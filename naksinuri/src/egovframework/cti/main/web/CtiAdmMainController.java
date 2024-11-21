package egovframework.cti.main.web;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.cti.callhstry.service.CtiCallHistoryService;
import egovframework.cti.callhstry.service.CtiCallHistoryVO;
import egovframework.cti.main.service.CtiBoardVO;
import egovframework.cti.main.service.CtiMainService;
import egovframework.cti.member.service.CtiMemberService;
import egovframework.cti.member.service.CtiMemberVO;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.educenter.board.service.EduCenterBoardService;
import egovframework.educenter.board.service.EduCenterBoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;

/*import egovframework.cti.callhstry.service.CtiCallHistoryVO;
import egovframework.cti.consult.service.CtiConsultService;
import egovframework.cti.consult.service.CtiConsultVO;
import egovframework.cti.curriculum.service.CtiCurriculumService;
import egovframework.cti.mbrhstry.service.CtiMyHistoryService;
import egovframework.cti.mbrhstry.service.CtiMyHistoryVO;
import egovframework.cti.member.service.CtiMemberService;
import egovframework.cti.member.service.CtiMemberVO;*/

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@EnableWebMvc
public class CtiAdmMainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CtiAdmMainController.class);
	
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	@Resource(name = "ctiMemberService")
	private CtiMemberService ctiMemberService;
	
	@Resource(name = "eduMemberService")
	private EduMemberService eduMemberService;
	
	@Resource(name = "ctiCallHistoryService")
	private CtiCallHistoryService ctiCallHistoryService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	@Resource(name = "eduCategoryService")
	private EduCategoryService eduCategoryService;
	
	@Resource(name = "eduCenterBoardService")
	private EduCenterBoardService eduCenterBoardService;
	
	@Resource(name = "ctiMainService")
	private CtiMainService ctiMainService;

	
	//관리자(CTI) 메인 페이지 ------------------------------------------------
	@RequestMapping(value = {"/cti/index.do","/cti/center.do"} )
	//@RequestMapping("/cti/index.do") 
	public String cti_main(HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
		//스태프 정보
		CtiMemberVO ctiMemberVO = new CtiMemberVO();
		ctiMemberVO.setMBR_ID(loginVO.getMBR_ID());
		ctiMemberVO = ctiMemberService.get_cti_staff_info(ctiMemberVO);
		model.addAttribute("staff_member_info",ctiMemberVO);

		CtiMemberVO ctiStaffVO = new CtiMemberVO();
		ctiStaffVO.setUSE_ST("Y");
		List<CtiMemberVO> list_staff_mbr = ctiMemberService.get_cti_staff_list(ctiStaffVO);
		model.addAttribute("list_staff_mbr",list_staff_mbr);
		//End of 스태프 정보
		
		String ctiConnectKey = (String)request.getSession().getAttribute("ctiConnectKey");
		if(ctiConnectKey!=null && ctiConnectKey.length()!=0) {
			//이미 발급됨
		} else {
			request.getSession().setAttribute("ctiConnectKey",EgovFileScrty.encode(loginVO.getMBR_ID()+""+loginVO.getMBR_LAST_CON_TM()));	
		}
		LOGGER.debug("ctiConnectKey : " + ctiConnectKey);
		
		//교육기관목록
		{
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setUSE_ST("1");
			eduCategoryInsInfVO.setNotUsedPagination(true);
			List<EduCategoryInsInfVO> edu_category_ins_inf = eduCategoryService.get_edu_category_ins_inf_list(eduCategoryInsInfVO);
			model.addAttribute("list_ins_info_cd",edu_category_ins_inf);	
		}
		
		//교육그룹 코드 조회(활성화)
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00005");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_edu_grp_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_edu_grp_cd",list_edu_grp_cd);
		}
		
		//상담분류 1단계 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00011");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_category_gubun_1",list_mbr_cd);
		}
		
		model.addAttribute("MBR_ID",loginVO.getMBR_ID());
		
		return "cti/main/index";
	}
	
	

	//관리자(CTI) 접근권한없음 ------------------------------------------------
	@RequestMapping(value = "/cti/error/unauth.do")
	public String eduadm_error_unauth(HttpServletRequest request, ModelMap model) throws Exception {
		
		return "cti/error/unauth";
	}
	
	
	//관리자(CTI) 통합검색 리스트  ------------------------------------------------
	@RequestMapping(value = "/cti/main/list_total.do", method = RequestMethod.POST)
  	public ModelAndView cti_member_list(@ModelAttribute("ctiCallHistoryVO") CtiCallHistoryVO ctiCallHistoryVO,
  			HttpServletRequest request, ModelMap model) throws Exception {	
  		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/main/list_total");  		

  		CtiCallHistoryVO chkCtiCallHistoryVO = new CtiCallHistoryVO();
  		List<CtiMemberVO> memberList = null;
  		
  		if(ctiCallHistoryVO!=null && ctiCallHistoryVO.getHCALL_SN()!=null && ctiCallHistoryVO.getHCALL_SN().length()!=0) {
  			LOGGER.debug("콜이력번호로 조회함");  			
  			chkCtiCallHistoryVO.setHCALL_SN(ctiCallHistoryVO.getHCALL_SN());
  			chkCtiCallHistoryVO = ctiCallHistoryService.get_cti_callhstry_info(chkCtiCallHistoryVO);
  		} 
  		
  		if(ctiCallHistoryVO.getMBR_HP().length()!=0) {
  			LOGGER.debug("연락처로 모든 정보 조회");
			CtiMemberVO ctiMemberVO = new CtiMemberVO();
			ctiMemberVO.setSearchKeyword(ctiCallHistoryVO.getMBR_HP().replace("-", ""));
			//ctiMemberVO.setCTI_MBR_HP(ctiCallHistoryVO.getMBR_HP().replace("-", ""));
			//ctiMemberVO.setUSE_ST("Y");
			memberList = ctiMemberService.get_mbr_info_with_scan_all(ctiMemberVO);  	
	  		mModelAndView.addObject("member_list",memberList);
  		} else if(chkCtiCallHistoryVO!=null && chkCtiCallHistoryVO.getHCALL_SN()!=null && chkCtiCallHistoryVO.getHCALL_SN().length()!=0) {

			LOGGER.debug("콜이력내 연락처로 연락처 기준으로 조회");
			if(chkCtiCallHistoryVO.getMBR_HP()!=null) {
				CtiMemberVO ctiMemberVO = new CtiMemberVO();
				ctiMemberVO.setSearchKeyword(chkCtiCallHistoryVO.getMBR_HP().replace("-", ""));
				//ctiMemberVO.setCTI_MBR_HP(chkCtiCallHistoryVO.getMBR_HP().replace("-", ""));
				//ctiMemberVO.setIS_ONLY_CTI("Y");
				memberList = ctiMemberService.get_mbr_info_with_scan_all(ctiMemberVO);  	
		  		mModelAndView.addObject("member_list",memberList);
		  		
		  		ctiCallHistoryVO.setMBR_HP(chkCtiCallHistoryVO.getMBR_HP());
			} else {
				LOGGER.debug("콜이력내 연락처로 연락처 기준으로 조회:연락처가 없음");
			}	  		
	  	
  		} else {
  			LOGGER.debug("검색 가능한 입력값이 없어 신규게시물로 간주");
  			//검색 가능한 입력값이 없어 신규게시물로 간주
  		}
  		  		
  		//조회 된 회원목록
  		mModelAndView.addObject("member_list",memberList);
  		
  		  		
  		//상담(콜) 이력 정보
  		mModelAndView.addObject("cti_call_info",chkCtiCallHistoryVO);
 		  		
  		//스태프 정보
		CtiMemberVO ctiStaffMemberVO = new CtiMemberVO();
		ctiStaffMemberVO.setMBR_ID(loginVO.getMBR_ID());
		ctiStaffMemberVO = ctiMemberService.get_cti_staff_info(ctiStaffMemberVO);
		mModelAndView.addObject("member_staff_info",ctiStaffMemberVO);
		//End of 스태프 정보
		
		String HCALL_GUBUN_1 = "";
		//상담분류 1단계 코드 조회
		{
	  		CodeSetVO mCodeSetVO = new CodeSetVO();
	  		mCodeSetVO.setCD_MASTER_ID("CID00011");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_category_gubun_1",list_mbr_cd);
	  		
			if(chkCtiCallHistoryVO!=null && chkCtiCallHistoryVO.getHCALL_GUBUN_1()!=null && chkCtiCallHistoryVO.getHCALL_GUBUN_1().length()!=0) {
				HCALL_GUBUN_1 = chkCtiCallHistoryVO.getHCALL_GUBUN_1();
			} else {
				if(list_mbr_cd!=null && list_mbr_cd.size()>0)
					HCALL_GUBUN_1 = ((CodeSetVO)list_mbr_cd.get(0)).getCD_ID();
			}
		}
		//End of 상담분류 1단계 코드 조회
		
		if(ctiCallHistoryVO.getHCALL_IVR_CD()!=null && ctiCallHistoryVO.getHCALL_IVR_CD().length()!=0) {
			HCALL_GUBUN_1 = ctiCallHistoryVO.getHCALL_IVR_CD();
		}
		
		//상담분류 2단계 코드 조회		
		CodeSetVO mCodeSetVO = new CodeSetVO();
  		mCodeSetVO.setCD_MASTER_ID(HCALL_GUBUN_1);
  		mCodeSetVO.setHIDE_AT("N");
  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
  		model.addAttribute("list_category_gubun_2",list_mbr_cd);
		//End of 상담분류 2단계 코드 조회
		
  		mModelAndView.addObject("HCALL_IVR_CD", ctiCallHistoryVO.getHCALL_IVR_CD());
  		mModelAndView.addObject("CALL_MBR_HP", ctiCallHistoryVO.getCALL_MBR_HP());
		mModelAndView.addObject("MBR_HP", ctiCallHistoryVO.getMBR_HP());
		mModelAndView.addObject("HCALL_SN", ctiCallHistoryVO.getHCALL_SN());
		mModelAndView.addObject("CALL_CD", ctiCallHistoryVO.getCALL_CD());
		mModelAndView.addObject("CUSTOM_UNIQ_KEY", ctiCallHistoryVO.getCUSTOM_UNIQ_KEY());
		
  		return mModelAndView;
  	}
	
	
	//CTI전용 : 낚시전문교육-공지사항-FAQ 게시판 리스트  
  	@RequestMapping(value = "/cti/main/educenter/faq/list.do")
  	public String ajax_cti_main_naksizazu_list(@ModelAttribute("eduCenterBoardVO") EduCenterBoardVO eduCenterBoardVO,
		@RequestParam(value="CUSTOM_UNIQ_KEY",required=false) String CUSTOM_UNIQ_KEY,
		HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		
  		eduCenterBoardVO.setPageUnit(15);
  		eduCenterBoardVO.setBD_ID("board003");

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eduCenterBoardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eduCenterBoardVO.getPageUnit());
		paginationInfo.setPageSize(eduCenterBoardVO.getPageSize());

		eduCenterBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eduCenterBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eduCenterBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<EduCenterBoardVO> list = eduCenterBoardService.userBoardList(eduCenterBoardVO);		
		int totCnt = eduCenterBoardService.userBoardListTotCnt(eduCenterBoardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		model.addAttribute("CUSTOM_UNIQ_KEY", CUSTOM_UNIQ_KEY);		
  		
  		return "cti/main/ajax_educenter_faq_list";
  	}
  	
  	//CTI전용 : 낚시누리-커뮤니티-자주묻는 낚시질문 게시판 리스트  
  	@RequestMapping(value = "/cti/main/naksizazu/list.do")
  	public String ajax_cti_main_naksizazu_list(@ModelAttribute ("naksinuriZisikinVO") NaksinuriZisikinVO naksinuriZisikinVO,
		@RequestParam(value="CUSTOM_UNIQ_KEY",required=false) String CUSTOM_UNIQ_KEY,
		HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		
  		naksinuriZisikinVO.setPageUnit(15);
  		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(naksinuriZisikinVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(naksinuriZisikinVO.getPageUnit());
		paginationInfo.setPageSize(naksinuriZisikinVO.getPageSize());

		naksinuriZisikinVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		naksinuriZisikinVO.setLastIndex(paginationInfo.getLastRecordIndex());
		naksinuriZisikinVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<NaksinuriZisikinVO> list = ctiMainService.get_cti_zazu_list(naksinuriZisikinVO);
		
		int totCnt = ctiMainService.get_cti_zazu_list_totcnt(naksinuriZisikinVO);
		//naksinuriZisikinVO.setTotalRecordCount(totCnt);			
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		model.addAttribute("CUSTOM_UNIQ_KEY", CUSTOM_UNIQ_KEY);		
  		
  		return "cti/main/ajax_naksizazu_list";
  	}
	
	  	
  	//CTI전용 메뉴얼 리스트
  	@RequestMapping(value = "/cti/main/manual/list.do")
  	public ModelAndView ajax_cti_center_list(@ModelAttribute("ctiBoardVO") CtiBoardVO ctiBoardVO,
		@RequestParam(value="CUSTOM_UNIQ_KEY",required=false) String CUSTOM_UNIQ_KEY,
		HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {	
  		  		
  		ModelAndView mModelAndView = new ModelAndView();
  		mModelAndView.setViewName("cti/main/ajax_manual_list");
  		
		{//상담분류 1단계
			CodeSetVO mCodeSetVO = new CodeSetVO();
	  		//mCodeSetVO.setCD_MASTER_ID("CID00012");
	  		mCodeSetVO.setHIDE_AT("N");
	  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
	  		model.addAttribute("list_category_gubun_1",list_mbr_cd); 		
		}
		{//상담분류 2단계 코드 조회
			if(ctiBoardVO!=null && ctiBoardVO.getBD_CATEGORY_CD()!=null && ctiBoardVO.getBD_CATEGORY_CD().length()!=0) {
				CodeSetVO mCodeSetVO = new CodeSetVO();
		  		mCodeSetVO.setCD_MASTER_ID(ctiBoardVO.getBD_CATEGORY_CD());
		  		mCodeSetVO.setHIDE_AT("N");
		  		List<CodeSetVO> list_mbr_cd = codeSetService.get_codeset_list(mCodeSetVO);
		  		model.addAttribute("list_category_gubun_2",list_mbr_cd);
			}			
		}
		
		ctiBoardVO.setBD_LOCK_ST("N");
		ctiBoardVO.setBD_ID("board017");
		ctiBoardVO.setNotUsedPagination(true);
		List<CtiBoardVO> list = ctiMainService.get_cti_manual_list(ctiBoardVO);
		
		model.addAttribute("list",list);
		model.addAttribute("BD_CATEGORY_CD", ctiBoardVO.getBD_CATEGORY_CD());
		model.addAttribute("BD_CATEGORY_CD2", ctiBoardVO.getBD_CATEGORY_CD2());
		model.addAttribute("searchKeyword",ctiBoardVO.getSearchKeyword());
		model.addAttribute("CUSTOM_UNIQ_KEY", CUSTOM_UNIQ_KEY);		
  		
  		return mModelAndView;
  	}
  	
	
}


