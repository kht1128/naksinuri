package egovframework.naksinuri_original.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
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
 *   2009.3.25  이삼섭          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class NaksinuriOriginalEgovFileDownloadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NaksinuriOriginalEgovFileDownloadController.class);
	
	@Resource(name = "NaksinuriOriginalEgovFileMngService")
	private NaksinuriOriginalEgovFileMngService fileService;

	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	
	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/naksinuri_original/cmm/fms/FileDown.do")
	public void cvplFileDownload(@ModelAttribute BoardVO boardVO, @RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String atchFileId = (String) commandMap.get("atchFileId");
		String fileSn = (String) commandMap.get("fileSn");

		
		//Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		//if (isAuthenticated) {

			NaksinuriOriginalFileVO fileVO = new NaksinuriOriginalFileVO();
			fileVO.setAtchFileId(atchFileId);
			fileVO.setFileSn(fileSn);
			
			//첨부파일 다운로드 횟수 업데이트
			try {
				fileService.update_hit_naksinuri_original(fileVO);
			} catch (Exception e) {
				LOGGER.debug("fail update "+e.toString());
			}
			//
			
			NaksinuriOriginalFileVO fvo = fileService.selectFileInf_naksinuri_original(fileVO);

			File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
			long fSize = uFile.length();

			if (fSize > 0) {
				String mimetype = "application/x-msdownload";

				response.setContentType(mimetype);
				setDisposition(fvo.getOrignlFileNm(), request, response);
				//response.setContentLength(fSize);

				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (Exception ex) {
					LOGGER.debug("IGNORED: {}", ex.getMessage());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception ignore) {
							LOGGER.debug("IGNORED: {}", ignore.getMessage());
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (Exception ignore) {
							LOGGER.debug("IGNORED: {}", ignore.getMessage());
						}
					}
				}

			} else {
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");
				printwriter.flush();
				printwriter.close();
			}
		//}
	}
}
