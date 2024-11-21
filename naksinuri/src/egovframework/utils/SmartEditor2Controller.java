package egovframework.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * @Class Name : SmartEditor2Controller.java
 * @Description : SmartEditor2Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.12.28  김정하         최초생성
 * @ 2019.01.01  김정하         insertSE2 수정
 *
 * @author 개발팀
 * @since 2018. 12.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by jhkim All right reserved.
 */

@Controller
public class SmartEditor2Controller {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmartEditor2Controller.class);

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	
	 
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	/** boardAdmService */
	//@Resource(name = "BoardAdmService")
	//private BoardAdmService boardAdmService;
	
	
	//스마트에디터 사진 업로드 팝업 ------------------------------------------------
	@RequestMapping(value = "/se2/photo_uploader/popup/index.do")
	public String mainIndex(HttpServletRequest request, ModelMap model) throws Exception {
		LOGGER.debug("사진 업로드 팝업 호출");
		return "cmmn/smart_editor2_imageupload_popup";
	}
	
	//스마트에디터 사진 업로드 로직 ------------------------------------------------
	@RequestMapping(value = "/se2/photo_uploader/popup/insert.do") 
	public @ResponseBody String insertSE2(//@ModelAttribute("userSearchVO") BoardAdmVO userSearchVO,
			MultipartHttpServletRequest multiRequest, HttpServletRequest request, 
			HttpServletResponse response, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		/*
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		JSONObject data = new JSONObject();
		JSONArray objArr = new JSONArray();
		LOGGER.debug("스마트에디터2 업로드 시작");
		String[] allowFileExts = {//허용할 업로드 파일 확장자
			"jpg","jpge","png","gif","bmp", //image
		};
		int allowFileMaxSize = 0;//허용할 업로드 파일 사이즈
		String _atchFileIdLabel = "SE2_";//저장시 적용할 첨부파일 라벨링
		String _storePath = "Webeditor.fileStorePath";//저장 파일 위치
		String bdsn = (String)request.getSession().getAttribute("se2.bdsn");
		BoardAdmVO boardAdmVO = boardAdmService.boardAdmView(bdsn);
		String _atchFileIdOri = (String)request.getSession().getAttribute("se2.atchFileId");
		if(boardAdmVO==null || boardAdmVO.getbdsn()==null || boardAdmVO.getbdsn().length()==0) {//글쓰기
			LOGGER.debug("글쓰기 모드");
			if(_atchFileIdOri==null || _atchFileIdOri.length()==0) {
				_atchFileIdOri = userSearchVO.getUniqKey("FSE2");
				fileMngService.insertFileMasterInf(_atchFileIdOri);
			}
		} else {//글수정
			LOGGER.debug("글수정 모드");
			if(_atchFileIdOri==null || _atchFileIdOri.length()==0) {
				_atchFileIdOri = boardAdmVO.getBD_FILE_SE2();
				if(_atchFileIdOri==null || _atchFileIdOri.length()==0) {
					_atchFileIdOri = userSearchVO.getUniqKey("FSE2");
					fileMngService.insertFileMasterInf(_atchFileIdOri);
				}
			} 
		}
		request.getSession().setAttribute("se2.atchFileId", _atchFileIdOri);
		LOGGER.debug("_atchFileIdOri : " + _atchFileIdOri);
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		boolean isException = false;
		
		
		//** 첨부파일 아이디는 글쓰기 모드나 글수정 모드나 비어 있을 경우 최초 아이디는 생성되어 내려옴! 
		//_atchFileIdOri is not null
		
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(_atchFileIdOri);
		int _maxFileSn = fileMngService.getMaxFileSN(fvo);
		List<FileVO> _result = fileUtil.parseFileInf(files, _atchFileIdLabel, _maxFileSn, _atchFileIdOri, _storePath);
		for(FileVO f : _result) {
			//FileVO f = _result.get(0);
			if (f.atchFileId.equals("ext_error")) {
				isException = true;
				LOGGER.debug("파일 검증 실패");
			} else {
				List<String> allowExtList = Arrays.asList(allowFileExts);
				if(allowExtList.size() > 0) {
					if(allowExtList.contains(f.getFileExtsn().toLowerCase().toString())) {
						//허용
					} else {
						isException = true;
						LOGGER.debug("허용되지 않는 첨부파일");
					}	
				}
				if(allowFileMaxSize <= 0) {
					//무제한
				} else {
					//사이즈 필터
					File uFile = new File(f.getFileStreCours(), f.getStreFileNm());
					if(uFile.length() <= allowFileMaxSize) {
						//허용
					} else {
						isException = true;
						LOGGER.debug("허용되지 않는 파일크기");
					}
				}
			}			
			if(!isException) { //파일 중 하나라도 이상이 있는 경우 업로드 하지 않는다. - 이건 추후 개선
				//fileMngService.updateFileInfs(_result); 아래 구문 처리로 주석
				JSONObject obj = new JSONObject();
				obj.put("_atchFileId", f.getAtchFileId());
				obj.put("_atchFileSn", f.getFileSn());
				obj.put("type", f.getFileExtsn());
				obj.put("name", f.getOrignlFileNm());
				obj.put("url", "/cmm/fms/getImage.do?atchFileId="+f.getAtchFileId()+"&fileSn="+f.getFileSn());
				obj.put("width", "110px");
				obj.put("height", "106px");
				objArr.add(obj);
			}
		}
		if(isException) { //파일 중 하나라도 이상이 있는 경우 업로드 하지 않는다. - 이건 추후 개선
			objArr.clear();
		} else {
			fileMngService.updateFileInfs(_result);
		}
		
		//} - 무조건 아이디가 있는 상황
		
		data.put("files", objArr);
		LOGGER.debug("스마트에디터2 업로드 종료");		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		*/
		return null;
	}
	
	
	@RequestMapping(value = "/se2/photo_uploader/popup/delete.do") 
	public @ResponseBody String updateSE2(@RequestParam("fid") String _atchFileIdOri, @RequestParam("fsn") String _atchFileSnOri,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		JSONObject data = new JSONObject();
		LOGGER.debug("스마트에디터2 파일 삭제 시작");
		
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(_atchFileIdOri);
		fvo.setFileSn(_atchFileSnOri);
		fvo = fileMngService.selectFileInf(fvo);
		fileMngService.deleteFileInf(fvo);
		
		LOGGER.debug("실제파일삭제 시작");
		LOGGER.debug("getAtchFileId : " + fvo.getAtchFileId());
		LOGGER.debug("getFileSn : " + fvo.getFileSn());
		LOGGER.debug("getStreFileNm : " + fvo.getStreFileNm());
		LOGGER.debug("getFileStreCours : " + fvo.getFileStreCours());
		LOGGER.debug("getOrignlFileNm : " + fvo.getOrignlFileNm());
		String fileDeletePath = fvo.getFileStreCours()+"/"+fvo.getStreFileNm();
		LOGGER.debug("fileDeletePath : " + fileDeletePath);
		
		/*
		 * 왜 삭제가 안되지???
		File delFile = new File(fvo.getFileStreCours(),fvo.getStreFileNm());
		if(delFile.exists()) {
			LOGGER.debug("존재하는 파일 삭제");
			delFile.delete();
		} else {
			LOGGER.debug("존재하지 않는 파일");
		}
		*/
		LOGGER.debug("스마트에디터2 파일 삭제 종료");		
		LOGGER.debug(data.toString());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(data);
		return null;
	}
	
	
	
	/**
	 * 본문 XSS 취약성 보완 필터링 - 2019.06.07
	 * 
	 * 아래 처리가 최적인가?
	 * 
	 * SealifeUtils - unscript 와 병행..	 * 
	 * 
	 **/
	public static String xssCleanFilteringContents(String content) {
		String xss_clean_str = "";
		xss_clean_str = Jsoup.clean(content
				,"http://1.217.88.66:8181"
				,Whitelist.relaxed().preserveRelativeLinks(true)
					.addAttributes("span","style")
					.addAttributes("table","style")
					.addAttributes("tr","style")
					.addAttributes("td","style")
					.addAttributes("th","style")
					.addAttributes("tbody","style")
					.addAttributes("thead","style")
					.addAttributes("tfoot","style")
				,new Document.OutputSettings().prettyPrint(false)
				);		
		return xss_clean_str;
	}
	
	
}


