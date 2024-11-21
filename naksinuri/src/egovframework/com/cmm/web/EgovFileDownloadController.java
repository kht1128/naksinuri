package egovframework.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.DwldConfimFileVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.utils.PublicUtils;

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
public class EgovFileDownloadController {

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileDownloadController.class);

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
	@RequestMapping(value = "/cmm/fms/FileDown.do")
	public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();
		String webDocumentPath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/jsp/egovframework";
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		String failMessage = "";
		String filename = "";
		
		String atchFileId = (String) commandMap.get("atchFileId");
		String fileSn = (String) commandMap.get("fileSn");

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO fvo = fileService.selectFileInf(fileVO);
				
		if(fvo!=null) {
			
			boolean isDelFile = false;
			boolean isConfirmFile = false;
			boolean isDownloadAllow = true;
			
			filename = fvo.getOrignlFileNm().replace(",", "_").replace(";", "_");
			
			File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
			long fSize = 0;
			if(uFile.exists()) {
				fSize = uFile.length();	
			} else {
				LOGGER.debug("[파일이 존재 하지 않음]");
				failMessage = "파일이 존재 하지 않습니다.";
				isDownloadAllow = false;
				filename = "";
			}
						
			DwldConfimFileVO chkDwldConfimFileVO = new DwldConfimFileVO();
			chkDwldConfimFileVO.setATCH_FILE_ID(atchFileId);
			chkDwldConfimFileVO.setFILE_SN(fileSn);
			chkDwldConfimFileVO = fileService.get_dwld_confim_info(chkDwldConfimFileVO);
			
			if(chkDwldConfimFileVO!=null && chkDwldConfimFileVO.getFILE_SN()!=null 
			&& chkDwldConfimFileVO.getFILE_MODE().equals("CONFIM")) {//승인제 파일
				LOGGER.debug("[승인제 파일 다운로드 시도]");
				isConfirmFile = true;
				if(loginVO!=null && loginVO.getMBR_ID()!=null && loginVO.getMBR_ID().length()!=0 && !loginVO.getMBR_LV_ID().equals("10")) {
					if(chkDwldConfimFileVO.getDWLD_WAIT_ST().equals("N")//다운로드 파일 생성 완료
					&& chkDwldConfimFileVO.getCONFIM_ST().equals("Y")//다운로드 승인 완료
					) {
						LOGGER.debug("[다운로드 파일 생성 완료 / 다운로드 승인 완료]");
						if(chkDwldConfimFileVO.getDWLD_CMPLT_ST().equals("Y")) {//이미 다운로드 받음
							LOGGER.debug("[이미 다운로드 받음]");
							if(chkDwldConfimFileVO.getDWLD_MAX_CNT().equals("0")) {//무제한
								LOGGER.debug("[다운로드 무제한]");
							} else {//다운로드 제한 파일
								LOGGER.debug("[다운로드 제한] 최대 : " + chkDwldConfimFileVO.getDWLD_MAX_CNT() + " , 현재 : " + chkDwldConfimFileVO.getDWLD_CNT());
								if(Integer.parseInt(chkDwldConfimFileVO.getDWLD_MAX_CNT()) <= Integer.parseInt(chkDwldConfimFileVO.getDWLD_CNT())+1) {
									LOGGER.debug("[다운로드 제한 초과]파일삭제");
									failMessage = "해당 파일은 더 이상 다운로드 받을 수 없습니다.";
									isDownloadAllow = false;
									isDelFile = true;
								}
							}
						} else {
							LOGGER.debug("[최초 다운로드]");
							if(chkDwldConfimFileVO.getDWLD_MAX_CNT().equals("0")) {//무제한
								LOGGER.debug("[다운로드 무제한]");
							} else {//다운로드 제한 파일
								LOGGER.debug("[다운로드 제한] 최대 : " + chkDwldConfimFileVO.getDWLD_MAX_CNT() + " , 현재 : " + chkDwldConfimFileVO.getDWLD_CNT());
								if(Integer.parseInt(chkDwldConfimFileVO.getDWLD_MAX_CNT()) <= Integer.parseInt(chkDwldConfimFileVO.getDWLD_CNT())+1) {
									LOGGER.debug("[다운로드 제한 초과]파일삭제");
									isDelFile = true;
								}
							}
						}
						if(chkDwldConfimFileVO.getDWLD_OTHBC_ST().equals("1")) { //신청자 한정 다운로드 가능
							LOGGER.debug("[신청자 한정 다운로드 가능]");
							if(!chkDwldConfimFileVO.getREQ_MBR_ID().equals(loginVO.getMBR_ID())) {//신청자가 아닌 경우
								LOGGER.debug("[다운로드 제한] 다른 사용자");
								failMessage = "해당 파일은 신청자만 다운로드 받을 수 있습니다.";
								isDownloadAllow = false;
								filename = "";
							}
						}
					} else {
						LOGGER.debug("[다운로드 파일 생성 전 또는 다운로드 미승인 상태]");
						isDownloadAllow = false;	
						failMessage = "해당 파일은 관리자 승인이 필요합니다.";
					}
				} else {
					LOGGER.debug("[비회원이 접근하여 차단]");
					isDownloadAllow = false;
					failMessage = "승인 된 사용자만 접근이 가능합니다.";
					filename = "";
				}
			}
						
			if(isDownloadAllow && fSize > 0) {
					
				if(isConfirmFile) {
					FileVO updateFvo = new FileVO();						
					updateFvo.setDWLD_CMPLT_ST("Y");
					updateFvo.setAtchFileId(chkDwldConfimFileVO.getATCH_FILE_ID());
					updateFvo.setFileSn(chkDwldConfimFileVO.getFILE_SN());
					updateFvo.setUPD_MBR_ID(loginVO.getMBR_ID());
					fileService.updateDetailFileInfForConfirm(updateFvo);
				}
				
				String mimetype = "application/x-msdownload";
				response.setContentType(mimetype);
				
				//response.setContentType("application/octet-stream; charset=utf-8");
				setDisposition(filename, request, response);
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
				
				if(isDelFile) {
					//uFile.delete();	
				}

			} else {
				LOGGER.debug(failMessage);

				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				
				String html = mPublicUtils.readFile(webDocumentPath+"/error.html").toString();
				html = html.replace("[[MESSAGE]]", failMessage);
				html = html.replace("[[MESSAGE2]]", filename);
				writer.println(html);
				
				writer.flush();
			}
		} else {
			LOGGER.debug(failMessage);
			
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			
			String html = mPublicUtils.readFile(webDocumentPath+"/error.html").toString();
			html = html.replace("[[MESSAGE]]", "비정상적인 접근입니다.");
			html = html.replace("[[MESSAGE2]]", "다음과 같은 이유로 다운로드가 불가합니다.");
			writer.println(html);
			
			writer.flush();
		}
		
	}
	
	
}

