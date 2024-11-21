package egovframework.adm.popup.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.adm.popup.service.PopupService;
import egovframework.adm.popup.service.PopupVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.utils.PublicFileMngUtil;
import egovframework.utils.PublicUtils;

@Controller
public class PopupController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PopupController.class);

	/** LogRecordService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;
	
	/** PopupService */
	@Resource(name="popupService")
	private PopupService popupService;
	
	/**
	 * 파일첨부
	 * */
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	
	//종합센터(관리자) - 팝업 리스트 페이지 -------------------------------------------------
	@RequestMapping(value = "/seadm/popup/list.do")
	public String popup_list(@ModelAttribute("popupVO") PopupVO popupVO, ModelMap model) throws Exception {
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(popupVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(popupVO.getPageUnit());
		paginationInfo.setPageSize(popupVO.getPageSize());

		popupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		popupVO.setLastIndex(paginationInfo.getLastRecordIndex());
		popupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());		
		List<PopupVO> list = popupService.get_seadm_popup_list(popupVO);		
		int totCnt = popupService.get_seadm_popup_list_totcnt(popupVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list",list);
		
		model.addAttribute("searchCondition",popupVO.getSearchCondition());
		model.addAttribute("searchKeyword",popupVO.getSearchCondition().length()==0?"":popupVO.getSearchKeyword());
		model.addAttribute("PP_TYPE",popupVO.getPP_TYPE());
					
		return "seadm/popup/list";
	}
	
	
	//종합센터(관리자) - 팝업 상세보기(미리보기) 페이지 ------------------------------------------------
	@RequestMapping(value = "/seadm/popup/view.do")
	public String popup_view(@ModelAttribute("popupVO") PopupVO popupVO,
			HttpServletRequest request, ModelMap model) throws Exception {
		PopupVO info = popupService.get_seadm_poup_info(popupVO);		
		model.addAttribute("info", info);
		return "seadm/popup/view";
	}
	
		
	//종합센터(관리자) - 팝업 글쓰기 페이지 ------------------------------------------------
	@RequestMapping(value = "/seadm/popup/write.do")
	public String popup_write(HttpServletRequest request, ModelMap model) throws Exception {
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", "");
		request.getSession().setAttribute("se2.atchFileId", "");
		//
		return "seadm/popup/write";
	}
	
	//종합센터(관리자) - 팝업 글쓰기 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/seadm/popup/write_act.do")
	public String popup_write_act(@ModelAttribute("popupVO") PopupVO popupVO, 
			MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");

		//스마트에디터 첨부파일 처리부
		String bd_file_se2 = popupVO.getPP_FILE_SE2();
		bd_file_se2 = new PublicFileMngUtil(fileMngService, fileUtil).chkFileUpdateSe2(
				bd_file_se2, //스마트에디터 첨부파일 아이디 
				popupVO.getPP_CONT() //스마트에디터 컨텐츠 내용(html)
				);
		popupVO.setPP_FILE_SE2(bd_file_se2);
		//
		
		
		String _atchFileId = "";
		PublicFileMngUtil mSealifeFileMngUtil = new PublicFileMngUtil(fileMngService,fileUtil);
		Map<String, MultipartFile> file_pc = mSealifeFileMngUtil.extractinputFileName(
				multiRequest,
				new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
					"file_pc",
				});
		Map<String, MultipartFile> file_mobile = mSealifeFileMngUtil.extractinputFileName(
				multiRequest,
				new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
					"file_mobile",
				});
		{//배너이미지(pc)
			Map fresult = mSealifeFileMngUtil.chkFileUpdate(
					file_pc, //업로드 파일 목록
					_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
					"0", //첨부파일아이디 번호(FILE_SN)
					"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
					new String[]{
						"jpg","jpge","png","gif","bmp", //image
						//"mp4","avi","wmv", //video
						//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
						//"mp3","zip", //etc
					} , //허용할 업로드 파일 확장자 
					0, //허용할 업로드 파일 사이즈
					"" //파일저장위치(기본값:Globals.fileStorePath)
					);
			if(fresult.get("error").equals("1")) {
				LOGGER.debug("정상적인 거부");
			} else if(fresult.get("error").equals("2")) {
				LOGGER.debug("파일 검증 실패");
				model.addAttribute("page_back_cnt", "-3");
				return "/seadm/error/page_back";
			} else { //정상적으로 처리됨.
				_atchFileId = fresult.get("atchFileId").toString();	
			}
		}
		{//배너이미지(모바일)
			Map fresult = mSealifeFileMngUtil.chkFileUpdate(
					file_mobile, //업로드 파일 목록
					_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
					"1", //첨부파일아이디 번호(FILE_SN)
					"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
					new String[]{
						"jpg","jpge","png","gif","bmp", //image
						//"mp4","avi","wmv", //video
						//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
						//"mp3","zip", //etc
					} , //허용할 업로드 파일 확장자 
					0, //허용할 업로드 파일 사이즈
					"" //파일저장위치(기본값:Globals.fileStorePath)
					);
			if(fresult.get("error").equals("1")) {
				LOGGER.debug("정상적인 거부");
			} else if(fresult.get("error").equals("2")) {
				LOGGER.debug("파일 검증 실패");
				model.addAttribute("page_back_cnt", "-3");
				return "/seadm/error/page_back";
			} else { //정상적으로 처리됨.
				_atchFileId = fresult.get("atchFileId").toString();	
			}
		}
		popupVO.setPP_FILE(_atchFileId);
		
		/*** 이미지2 ***/
		String _atchFileId2 = "";
		Map<String, MultipartFile> file_pc_2 = mSealifeFileMngUtil.extractinputFileName(
				multiRequest,
				new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
					"file_pc_2",
				});
		Map<String, MultipartFile> file_mobile_2 = mSealifeFileMngUtil.extractinputFileName(
				multiRequest,
				new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
					"file_mobile_2",
				});
		{//배너이미지(pc)
			Map fresult = mSealifeFileMngUtil.chkFileUpdate(
					file_pc_2, //업로드 파일 목록
					_atchFileId2, //첨부파일아이디(ATCH_FILE_ID) 
					"0", //첨부파일아이디 번호(FILE_SN)
					"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
					new String[]{
						"jpg","jpge","png","gif","bmp", //image
						//"mp4","avi","wmv", //video
						//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
						//"mp3","zip", //etc
					} , //허용할 업로드 파일 확장자 
					0, //허용할 업로드 파일 사이즈
					"" //파일저장위치(기본값:Globals.fileStorePath)
					);
			if(fresult.get("error").equals("1")) {
				LOGGER.debug("정상적인 거부");
			} else if(fresult.get("error").equals("2")) {
				LOGGER.debug("파일 검증 실패");
				model.addAttribute("page_back_cnt", "-3");
				return "/seadm/error/page_back";
			} else { //정상적으로 처리됨.
				_atchFileId2 = fresult.get("atchFileId").toString();	
			}
		}
		{//배너이미지(모바일)
			Map fresult = mSealifeFileMngUtil.chkFileUpdate(
					file_mobile_2, //업로드 파일 목록
					_atchFileId2, //첨부파일아이디(ATCH_FILE_ID) 
					"1", //첨부파일아이디 번호(FILE_SN)
					"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
					new String[]{
						"jpg","jpge","png","gif","bmp", //image
						//"mp4","avi","wmv", //video
						//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
						//"mp3","zip", //etc
					} , //허용할 업로드 파일 확장자 
					0, //허용할 업로드 파일 사이즈
					"" //파일저장위치(기본값:Globals.fileStorePath)
					);
			if(fresult.get("error").equals("1")) {
				LOGGER.debug("정상적인 거부");
			} else if(fresult.get("error").equals("2")) {
				LOGGER.debug("파일 검증 실패");
				model.addAttribute("page_back_cnt", "-3");
				return "/seadm/error/page_back";
			} else { //정상적으로 처리됨.
				_atchFileId2 = fresult.get("atchFileId").toString();	
			}
		}
		popupVO.setPP_FILE2(_atchFileId2);
		/*** 이미지2 end ***/
		
		
		popupVO.setREG_MBR_ID(loginVO.getMBR_ID());
		popupVO.setUPD_MBR_ID(loginVO.getMBR_ID());
		String insertId = popupService.set_seadm_pupup_info_reg(popupVO);
		
		StringBuilder log_dscrp = new StringBuilder();
		StringBuilder tbl_inf = new StringBuilder();
		StringBuilder tbl_sn = new StringBuilder();
		log_dscrp.append("[낚시누리 관리자-팝업-글쓰기]");
		log_dscrp.append("[게시물:"+popupVO.getPP_TITLE()+"]");
		tbl_inf.append("SL_POPUP_TB");
		tbl_sn.append(insertId);
		try {	
			/**
			 * LOG RECORED (로그기록)
			 * */
			LogRecordVO mEduLogRecordVO = new LogRecordVO();
			mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(popupVO));
			mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			logRecordService.set_log_data(mEduLogRecordVO,request);
		} catch(Exception e) {
			LOGGER.debug("[fail log record]"+e.toString());
		}
		
		return "redirect:/seadm/popup/list.do";
	}
		
	//종합센터(관리자) - 팝업 글쓰기 페이지 ------------------------------------------------
	@RequestMapping(value = "/seadm/popup/modify.do")
	public String popup_modify(@ModelAttribute("popupVO") PopupVO popupVO, @RequestParam(value="selectedId",required=false) String selectedId,
			HttpServletRequest request, ModelMap model) throws Exception {

		if(selectedId!=null && selectedId.length()!=0) {
			popupVO.setPP_SN(selectedId);
		}
		
		PopupVO info = popupService.get_seadm_poup_info(popupVO);		
		//스마트에디터2용 atchFileId 저장
		request.getSession().setAttribute("se2.bdsn", info.getPP_SN());
		request.getSession().setAttribute("se2.atchFileId", info.getPP_FILE_SE2());
		//				
		//첨부파일 갯수 확인 후 필요시 정보 업데이트
		String _atchFileId = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getPP_FILE());
		if(info.getPP_FILE()!=null && !info.getPP_FILE().equals(_atchFileId)) {
			info.setPP_FILE(_atchFileId);
			popupService.set_seadm_pupup_info_mod(info);
		}
		//
		//첨부파일 갯수 확인 후 필요시 정보 업데이트 2
		String _atchFileId2 = new PublicFileMngUtil(fileMngService,fileUtil).chkFileCount(info.getPP_FILE2());
		if(info.getPP_FILE2()!=null && !info.getPP_FILE2().equals(_atchFileId2)) {
			info.setPP_FILE2(_atchFileId2);
			popupService.set_seadm_pupup_info_mod(info);
		}
		//
		model.addAttribute("info", info);
		
		return "seadm/popup/modify";
	}
	
	//종합센터(관리자) - 팝업 글수정 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/seadm/popup/modify_act.do") 
	public String popup_modify_act(@ModelAttribute("popupVO") PopupVO popupVO, 
			MultipartHttpServletRequest multiRequest, HttpServletRequest request, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");		
		PopupVO chkPopupVO = popupService.get_seadm_poup_info(popupVO);
		if(chkPopupVO==null || chkPopupVO.getPP_SN()==null || chkPopupVO.getPP_SN().length()==0) {
			LOGGER.debug("존재하지 않는 게시물 진입..");
			model.addAttribute("page_back_cnt", "-3");
			return "/seadm/error/page_back";
		} else {
			LOGGER.debug("정상적인 접근.");
			StringBuilder log_msg = new StringBuilder();
			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();
			
			//스마트에디터 첨부파일 처리
			String bd_file_se2 = chkPopupVO.getPP_FILE_SE2();
			bd_file_se2 = new PublicFileMngUtil(fileMngService, fileUtil).chkFileUpdateSe2(
					bd_file_se2, //스마트에디터 첨부파일 아이디 
					popupVO.getPP_CONT() //스마트에디터 컨텐츠 내용(html)
					);
			popupVO.setPP_FILE_SE2(bd_file_se2);
			
			//첨부파일 처리
			String _atchFileId = chkPopupVO.getPP_FILE();
			PublicFileMngUtil mSealifeFileMngUtil = new PublicFileMngUtil(fileMngService,fileUtil);
			Map<String, MultipartFile> file_pc = mSealifeFileMngUtil.extractinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_pc",
					});
			Map<String, MultipartFile> file_mobile = mSealifeFileMngUtil.extractinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_mobile",
					});
			{//배너이미지(pc)
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						file_pc, //업로드 파일 목록
						_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
						"0", //첨부파일아이디 번호(FILE_SN)
						"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							//"mp4","avi","wmv", //video
							//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
							//"mp3","zip", //etc
						} , //허용할 업로드 파일 확장자 
						0, //허용할 업로드 파일 사이즈
						"" //파일저장위치(기본값:Globals.fileStorePath)
						);
				if(fresult.get("error").equals("1")) {
					LOGGER.debug("정상적인 거부");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					model.addAttribute("page_back_cnt", "-3");
					return "/seadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId = fresult.get("atchFileId").toString();	
				}
			}
			{//배너이미지(모바일)
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						file_mobile, //업로드 파일 목록
						_atchFileId, //첨부파일아이디(ATCH_FILE_ID) 
						"1", //첨부파일아이디 번호(FILE_SN)
						"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							//"mp4","avi","wmv", //video
							//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
							//"mp3","zip", //etc
						} , //허용할 업로드 파일 확장자 
						0, //허용할 업로드 파일 사이즈
						"" //파일저장위치(기본값:Globals.fileStorePath)
						);
				if(fresult.get("error").equals("1")) {
					LOGGER.debug("정상적인 거부");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					model.addAttribute("page_back_cnt", "-3");
					return "/seadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId = fresult.get("atchFileId").toString();	
				}
			}
			popupVO.setPP_FILE(_atchFileId);
			
			
			//첨부파일 처리 2
			String _atchFileId2 = chkPopupVO.getPP_FILE2();
			Map<String, MultipartFile> file_pc_2 = mSealifeFileMngUtil.extractinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_pc_2",
					});
			Map<String, MultipartFile> file_mobile_2 = mSealifeFileMngUtil.extractinputFileName(
					multiRequest,
					new String[]{//추출하거나 제거하고 싶은 FILE INPUT NAME 값을 계속 추가한다.
						"file_mobile_2",
					});
			{//배너이미지(pc)
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						file_pc_2, //업로드 파일 목록
						_atchFileId2, //첨부파일아이디(ATCH_FILE_ID) 
						"0", //첨부파일아이디 번호(FILE_SN)
						"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							//"mp4","avi","wmv", //video
							//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
							//"mp3","zip", //etc
						} , //허용할 업로드 파일 확장자 
						0, //허용할 업로드 파일 사이즈
						"" //파일저장위치(기본값:Globals.fileStorePath)
						);
				if(fresult.get("error").equals("1")) {
					LOGGER.debug("정상적인 거부");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					model.addAttribute("page_back_cnt", "-3");
					return "/seadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId2 = fresult.get("atchFileId").toString();	
				}
			}
			{//배너이미지(모바일)
				Map fresult = mSealifeFileMngUtil.chkFileUpdate(
						file_mobile_2, //업로드 파일 목록
						_atchFileId2, //첨부파일아이디(ATCH_FILE_ID) 
						"1", //첨부파일아이디 번호(FILE_SN)
						"BBS_", //저장시 적용할 첨부파일 라벨링(기본값:NAK_)
						new String[]{
							"jpg","jpge","png","gif","bmp", //image
							//"mp4","avi","wmv", //video
							//"hwp","doc","xls","xlsx","csv","txt","pdf", //document
							//"mp3","zip", //etc
						} , //허용할 업로드 파일 확장자 
						0, //허용할 업로드 파일 사이즈
						"" //파일저장위치(기본값:Globals.fileStorePath)
						);
				if(fresult.get("error").equals("1")) {
					LOGGER.debug("정상적인 거부");
				} else if(fresult.get("error").equals("2")) {
					LOGGER.debug("파일 검증 실패");
					model.addAttribute("page_back_cnt", "-3");
					return "/seadm/error/page_back";
				} else { //정상적으로 처리됨.
					_atchFileId2 = fresult.get("atchFileId").toString();	
				}
			}
			popupVO.setPP_FILE2(_atchFileId2);
			
			
			
			if(popupVO.getUSE_ST_CHK().equals("Y")) {
				popupVO.setUSE_ST("1");
				popupVO.setDEL_ST("0");
			} else {
				popupVO.setUSE_ST("0");
			}

			popupVO.setUPD_MBR_ID(loginVO.getMBR_ID());
			popupService.set_seadm_pupup_info_mod(popupVO);
			
			log_dscrp.append("[낚시누리 관리자-팝업-글수정]");
			log_dscrp.append("[게시물:"+popupVO.getPP_TITLE()+"]");
			tbl_inf.append("SL_POPUP_TB");
			tbl_sn.append(chkPopupVO.getPP_SN());
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkPopupVO));
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(popupVO));
				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
				mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO,request);
			} catch(Exception e) {
				LOGGER.debug("[fail log record]"+e.toString());
			}
			
			return "redirect:/seadm/popup/list.do";
		}
		 
	}
	
	
	//종합센터(관리자) - 팝업 글삭제 처리 로직 ------------------------------------------------
	@RequestMapping(value = "/seadm/popup/delete_act.do")
	public String popup_delete_act(@ModelAttribute("popupVO") PopupVO popupVO, 
			HttpServletRequest request, Model model) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		PopupVO chkPopupVO = popupService.get_seadm_poup_info(popupVO);
		if(chkPopupVO==null || chkPopupVO.getPP_SN()==null || chkPopupVO.getPP_SN().length()==0) {
			LOGGER.debug("존재하지 않는 게시물 진입..");
			model.addAttribute("page_back_cnt", "-3");
			return "/seadm/error/page_back";
		} else {
			LOGGER.debug("정상적인 접근.");
			StringBuilder log_msg = new StringBuilder();
			StringBuilder log_dscrp = new StringBuilder();
			StringBuilder tbl_inf = new StringBuilder();
			StringBuilder tbl_sn = new StringBuilder();
			tbl_inf.append("SL_POPUP_TB");
						
			String DEL_ST = chkPopupVO.getDEL_ST();
			if(DEL_ST.equals("1")) {
				log_dscrp.append("[낚시누리 관리자-팝업-글삭제-실제디비삭제]");
				popupService.remove_seadm_popup(chkPopupVO);
				if(chkPopupVO.getPP_FILE_SE2()!=null && chkPopupVO.getPP_FILE_SE2().length()!=0) {
					fileMngService.deleteAllDetailFileInfs(chkPopupVO.getPP_FILE_SE2());
					fileMngService.deleteAllFileInf(chkPopupVO.getPP_FILE_SE2());
				}
			} else {
				log_dscrp.append("[낚시누리 관리자-팝업-글삭제]");
				chkPopupVO.setUPD_MBR_ID(loginVO.getMBR_ID());
				popupService.del_seadm_popup(chkPopupVO);
			}
			
			log_dscrp.append("[게시물:"+chkPopupVO.getPP_TITLE()+"]");
			
			try {	
				/**
				 * LOG RECORED (로그기록)
				 * */
				LogRecordVO mEduLogRecordVO = new LogRecordVO();
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(chkPopupVO));
				log_msg.append(mEduLogRecordVO.encodingFromObjectToJson(popupVO));
				mEduLogRecordVO.setLOG_MSG(log_msg.toString());
				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
				mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
				mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
				logRecordService.set_log_data(mEduLogRecordVO,request);
			} catch(Exception e) {
				LOGGER.debug("[fail log record]"+e.toString());
			}
			
		}		
		return "redirect:/seadm/popup/list.do";
	}
	
}


