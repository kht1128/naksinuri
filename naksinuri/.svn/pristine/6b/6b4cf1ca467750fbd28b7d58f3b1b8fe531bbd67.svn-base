package egovframework.naksinuri_original.com.cmm.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;

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
public class NaksinuriOriginalEgovFileMngController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NaksinuriOriginalEgovFileMngController.class);

	@Resource(name = "NaksinuriOriginalEgovFileMngService")
	private NaksinuriOriginalEgovFileMngService fileService;

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
	@RequestMapping("/naksinuri_original/cmm/fms/selectFileInfs.do")
	public String selectFileInfs(@ModelAttribute("searchVO") NaksinuriOriginalFileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		String atchFileId = (String) commandMap.get("param_atchFileId");

		fileVO.setAtchFileId(atchFileId);
		List<NaksinuriOriginalFileVO> result = fileService.selectFileInfs_naksinuri_original(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "naksinuri_original/cmm/fms/EgovFileList";
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
	@RequestMapping("/naksinuri_original/cmm/fms/selectFileInfsForUpdate.do")
	public String selectFileInfsForUpdate(@ModelAttribute("searchVO") NaksinuriOriginalFileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");

		fileVO.setAtchFileId(atchFileId);

		List<NaksinuriOriginalFileVO> result = fileService.selectFileInfs_naksinuri_original(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "Y");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "naksinuri_original/cmm/fms/EgovFileList";
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
	@RequestMapping("/naksinuri_original/cmm/fms/deleteFileInfs.do")
	public String deleteFileInf(@ModelAttribute("searchVO") NaksinuriOriginalFileVO fileVO, @RequestParam("returnUrl") String returnUrl, HttpServletRequest request, ModelMap model)
			throws Exception {

		fileService.deleteFileInf_naksinuri_original(fileVO);
		
		//}

		//--------------------------------------------
		// contextRoot가 있는 경우 제외 시켜야 함
		//--------------------------------------------
		////return "forward:/naksinuri_original/cmm/fms/selectFileInfs.do";
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
	@RequestMapping("/naksinuri_original/cmm/fms/selectImageFileInfs.do")
	public String selectImageFileInfs(@ModelAttribute("searchVO") NaksinuriOriginalFileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("atchFileId");

		fileVO.setAtchFileId(atchFileId);
		List<NaksinuriOriginalFileVO> result = fileService.selectImageFileList_naksinuri_original(fileVO);

		model.addAttribute("fileList", result);

		return "naksinuri_original/cmm/fms/EgovImgFileList";
	}
}
