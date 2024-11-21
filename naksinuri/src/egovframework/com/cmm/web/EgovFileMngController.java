package egovframework.com.cmm.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import egovframework.all.excel.AllExcelDownLoad;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.25  이삼섭          최초 생성
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * </pre>
 */
@Controller
public class EgovFileMngController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileMngController.class);
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	/**
	 * 첨부파일에 대한 목록을 조회한다.
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfs.do")
	public String selectFileInfs(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		String atchFileId = (String) commandMap.get("param_atchFileId");
		try {
			String fixFileSn = (String) commandMap.get("param_fixFileSn");
			if(fixFileSn!=null && fixFileSn.length()!=0) {
				fileVO.setSearchCnd("fixfilesn");
				fileVO.setFILE_SNs(fixFileSn.split(","));	
			}
		} catch(Exception e) {
			LOGGER.debug("empty fixFileSn");
		}
		try {
			String lockFileSn = (String) commandMap.get("param_lockFileSn");
			if(lockFileSn!=null && lockFileSn.length()!=0) {
				fileVO.setSearchCnd("lockfilesn");
				fileVO.setFILE_SNs(lockFileSn.split(","));	
			}
		} catch(Exception e) {
			LOGGER.debug("empty lockFileSn");
		}
		
		boolean isHideFileSize = false;
		try {
			isHideFileSize = ((String) commandMap.get("param_isHideFileSize")).equals("true");
		} catch(Exception e) {
			isHideFileSize = false;
		}
						
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("isHideFileSize", isHideFileSize);

		return "cmmn/EgovFileList";
	}
	
	@RequestMapping("/cmm/fms/selectUserFileInfs.do")
	public String selectUserFileInfs(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		String atchFileId = (String) commandMap.get("param_atchFileId");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "cmmn/EgovUserFileList";
	}
	
	/**
	 * 첨부파일 변경을 위한 수정페이지로 이동한다.
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfsForUpdate.do")
	public String selectFileInfsForUpdate(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");

		try {
			String fixFileSn = (String) commandMap.get("param_fixFileSn");
			if(fixFileSn!=null && fixFileSn.length()!=0) {
				fileVO.setSearchCnd("fixfilesn");
				fileVO.setFILE_SNs(fixFileSn.split(","));	
			}
		} catch(Exception e) {
			LOGGER.debug("empty fixFileSn");
		}	
		
		try {
			String lockFileSn = (String) commandMap.get("param_lockFileSn");
			if(lockFileSn!=null && lockFileSn.length()!=0) {
				fileVO.setSearchCnd("lockfilesn");
				fileVO.setFILE_SNs(lockFileSn.split(","));	
			}
		} catch(Exception e) {
			LOGGER.debug("empty lockfilesn");
		}	
		
		fileVO.setAtchFileId(atchFileId);

		List<FileVO> result = fileService.selectFileInfs(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "Y");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "cmmn/EgovFileList";
	}
	
	/**
	 * ajax 첨부파일 변경을 위한 수정페이지로 이동한다.
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfsForUpdateAjax.do")
	public String selectFileInfsForUpdateAjax(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");
		String request_type = (String) commandMap.get("param_request_type");
		String returnUrl = "";
		String custom_uniq_key = (String) commandMap.get("param_custom_uniq_key");
		String target_clk_id = (String) commandMap.get("param_target_clk_id");
		if(request_type!=null && request_type.length()!=0) {
			if(request_type.equals("cti")) {
				returnUrl = "cmmn/EgovFileListAjaxCti";				
			} else {
				returnUrl = "cmmn/EgovFileListAjax";
			}
		} else {
			returnUrl = "cmmn/EgovFileListAjax";
		}
			
	
		String updateFlag = "Y";

		try {
			String fixFileSn = (String) commandMap.get("param_fixFileSn");
			if(fixFileSn!=null && fixFileSn.length()!=0) {
				fileVO.setSearchCnd("fixfilesn");
				fileVO.setFILE_SNs(fixFileSn.split(","));	
			}			
		} catch(Exception e) {
			LOGGER.debug("empty fixFileSn");
		}	
		
		try {
			String lockFileSn = (String) commandMap.get("param_lockFileSn");
			if(lockFileSn!=null && lockFileSn.length()!=0) {
				fileVO.setSearchCnd("lockfilesn");
				fileVO.setFILE_SNs(lockFileSn.split(","));	
			}			
		} catch(Exception e) {
			LOGGER.debug("empty lockfilesn");
		}	
		
		try {
			String param_updateFlag = (String) commandMap.get("param_updateFlag");
			if(param_updateFlag!=null && param_updateFlag.length()!=0) {
				updateFlag = param_updateFlag;
			}
		} catch(Exception e) {
			LOGGER.debug("empty param_updateFlag");
		}
		
		fileVO.setAtchFileId(atchFileId);

		List<FileVO> result = fileService.selectFileInfs(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", updateFlag);
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("custom_uniq_key", custom_uniq_key);
		model.addAttribute("target_clk_id", target_clk_id);

		return returnUrl;
	}
	
	/**
	 * ajax 첨부파일에 대한 삭제를 처리한다.
	 *
	 * @param fileVO
	 * @param returnUrl
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/deleteFileInfsAjax.do")
	public @ResponseBody String deleteFileInfAjax(@ModelAttribute("searchVO") FileVO fileVO, 
			@RequestParam("returnUrl") String returnUrl, 
			@RequestParam("targetFormId") String targetFormId,
			HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		JSONObject rstdata = new JSONObject();
		rstdata.put("returnUrl", returnUrl);
		rstdata.put("targetFormId", targetFormId);
		try {
			fileService.deleteFileInf(fileVO);

			//--------------------------------------------
			// contextRoot가 있는 경우 제외 시켜야 함
			//--------------------------------------------
			////return "forward:/cmm/fms/selectFileInfs.do";
			//return "forward:" + returnUrl;
	/*
			if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
				return "forward:" + returnUrl;
			}
	
			if (returnUrl.startsWith(request.getContextPath())) {
				return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1));
			} else {
				return "forward:" + returnUrl;
			}
			*/
			////------------------------------------------
			rstdata.put("error", "0");
			rstdata.put("msg", "정상적으로 삭제 되었습니다.");
		} catch(Exception e) {
			rstdata.put("error", "1");
			rstdata.put("msg", "삭제 중 에러가 발생하였습니다.");
		}	
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(rstdata);
		return null;
	}

	/**
	 * 첨부파일에 대한 삭제를 처리한다.
	 *
	 * @param fileVO
	 * @param returnUrl
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/deleteFileInfs.do")
	public String deleteFileInf(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam("returnUrl") String returnUrl, HttpServletRequest request, ModelMap model)
			throws Exception {
		
		fileService.deleteFileInf(fileVO);

		//--------------------------------------------
		// contextRoot가 있는 경우 제외 시켜야 함
		//--------------------------------------------
		////return "forward:/cmm/fms/selectFileInfs.do";
		//return "forward:" + returnUrl;

		if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
			return "forward:" + returnUrl;
		}

		if (returnUrl.startsWith(request.getContextPath())) {
			return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1));
		} else {
			return "forward:" + returnUrl;
		}
		////------------------------------------------
	}

	/**
	 * 이미지 첨부파일에 대한 목록을 조회한다.
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectImageFileInfs.do")
	public String selectImageFileInfs(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("atchFileId");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectImageFileList(fileVO);
		
		

		model.addAttribute("fileList", result);

		return "cmmn/EgovImgFileList";
	}
	
	@RequestMapping("/cmm/fms/selectFilePrmov.do")
	public String selectFilePrmov(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		
		String atchFileId = (String) commandMap.get("atchFileId");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectMovFileList(fileVO);
		
		model.addAttribute("fileList", result);

		return "cmmn/EgovPrmovList";
	}
	
	@RequestMapping("/cmm/fms/selectFilePrmovMobile.do")
	public String selectFilePrmovMobile(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		
		String atchFileId = (String) commandMap.get("atchFileId");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectMovFileList(fileVO);
		
		model.addAttribute("fileList", result);

		return "cmmn/EgovPrmovListMobile";
	}
	
	@RequestMapping("/cmm/fms/selectFileSourceUrlForVideojs.do")
	public String selectFileSourceUrlForVideojs(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		
		String atchFileId = (String) commandMap.get("atchFileId");
		String fileSn = (String) commandMap.get("fileSn");

		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO info = fileService.selectFileInf(fileVO);
		
		model.addAttribute("info", info);

		return "cmmn/EgovMovSourceUrlForVideoJs";
	}
	
	
	@RequestMapping("/cmm/fms/selectFileUrlForPdf.do")
	public String selectFileUrlForPdf(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		//속도 개선을 위해 추가해 봤으나, 효과가 미비함 방법을 강구해야 할듯.
		
		String atchFileId = (String) commandMap.get("atchFileId");
		String fileSn = (String) commandMap.get("fileSn");

		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO info = fileService.selectFileInf(fileVO);
		
		model.addAttribute("info", info);

		return "cmmn/EgovPdfSourceUrlLink";
	}
	
}
