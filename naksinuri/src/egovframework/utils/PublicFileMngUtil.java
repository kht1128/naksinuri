package egovframework.utils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;

/**
 * @Class Name : SealifeFileMngUtil.java
 * @Description : SealifeFileMngUtil Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.12.28  김정하         최초생성
 * @ 2019.01.18  김정하         수정
 *
 * @author 개발팀
 * @since 2018. 12.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by jhkim All right reserved.
 */
public class PublicFileMngUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublicFileMngUtil.class);

	private EgovFileMngService fileMngService = null;
	private EgovFileMngUtil fileUtil = null;
	
	public PublicFileMngUtil(final EgovFileMngService fileMngService, final EgovFileMngUtil fileUtil) {
		this.fileMngService = fileMngService;
		this.fileUtil = fileUtil;
	}
	
	private void delete(String _atchFileIdOri) throws Exception {
		LOGGER.debug("파일 삭제 : " + _atchFileIdOri);
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(_atchFileIdOri);
		fileMngService.deleteFileInf(fvo);
	}
	
	private void delete(String _atchFileIdOri, String _atchFileSnOri) throws Exception {
		LOGGER.debug("파일 삭제 : " + _atchFileIdOri + " , " +_atchFileSnOri);
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(_atchFileIdOri);
		fvo.setFileSn(_atchFileSnOri);
		fileMngService.deleteFileInf(fvo);
	}
	public String chkFileCount(String _atchFileIdOri) throws Exception {
		LOGGER.debug("첨부파일 갯수 확인");
		LOGGER.debug("_atchFileIdOri : " + _atchFileIdOri);
		String _atchFileId = "";
		if(_atchFileIdOri == null || _atchFileIdOri.length() == 0) { //첨부파일 아이디 없음
			//해당없음
			_atchFileId = _atchFileIdOri;
			LOGGER.debug("첨부파일 정보가 없으므로 검증안함.");
		} else { //기존에 첨부파일이 존재함. - 갯수 확인
			try {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(_atchFileIdOri);
				int _maxFileCnt = fileMngService.getMaxFileCnt(fvo);
				LOGGER.debug("첨부파일 갯수 : " + _maxFileCnt);
				if(_maxFileCnt > 0) {
					LOGGER.debug("첨부파일 존재 삭제 처리 안함");
					_atchFileId = _atchFileIdOri;
				} else {
					//파일 삭제 처리
					LOGGER.debug("첨부파일 삭제처리 진행");
					//fileMngService.deleteAllDetailFileInfs(_atchFileIdOri);//이건 하위 아이들 다 지움.
					fileMngService.deleteAllFileInf(fvo);//마스터 정보 N 처리됨.
					_atchFileId = "";
				}
			} catch(Exception e) {
				LOGGER.debug("[첨부파일 검증시 에러발생] "+e.toString());
			}
		}
		LOGGER.debug("첨부파일 갯수 확인 완료 : " + _atchFileId);
		return _atchFileId;
	}
	
	
	/**
	 * 특정 input name 파일을 제거한다.
	 */
	public Map<String, MultipartFile> removeinputFileName(MultipartHttpServletRequest multiRequest, String[] fileInputName) {
		LOGGER.debug("특정 input name 파일을 제거한다.");
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(fileInputName!=null) {
			for(String f : fileInputName) {
				LOGGER.debug("대상 INPUT FILE NAME : " + f);
				if (files.get(f) != null) {
					LOGGER.debug("-> 정상적으로 제거됨.");
					files.remove(f);	
			    }	
			}
		}
		LOGGER.debug("추출된 all FORM name 값 : " + Arrays.toString(files.keySet().toArray()));
		return files;
	}
	/**
	 * 특정 input name 파일만 추출한다.
	 */
	public Map<String, MultipartFile> extractinputFileName(MultipartHttpServletRequest multiRequest, String[] fileInputName) {
		LOGGER.debug("특정 input name 파일만 추출한다.");
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		Map<String, MultipartFile> rstfiles = multiRequest.getFileMap();
		if(fileInputName!=null) {
			rstfiles.clear();
			for(String f : fileInputName) {
				LOGGER.debug("대상 INPUT FILE NAME : " + f);
				if (files.get(f) != null) {
					LOGGER.debug("-> 정상적으로 추출됨.");
					rstfiles.put(f, files.get(f));	
			    }	
			}
		}
		LOGGER.debug("추출된 all FORM name 값 : " + Arrays.toString(rstfiles.keySet().toArray()));
		return rstfiles;
	}
	
	
	/**
	 * 단일 첨부파일 chkFileUpdate 메소드
	 * @param files Map 업로드한 파일 목록
	 * @param _atchFileIdOri string 해당 게시판 ATCH_FILE_ID
	 * @param _atchFileSnOri string 해당 게시판 FILE_SN
	 * @param _atchFileIdLabel string 저장시 적용할 첨부파일 라벨링(기본값:NAK_)
	 * @param 사용안함 : _atchFileIdLabel string 해당 게시판에 첨부파일이 하나도 없을 경우에만 입력한 라벨이 붙는다. 기본값:NAK_
	 * @param allowFileExts string[] 허용할 파일 확장자. !!소문자!! 로 만 입력하세요
	 * @param allowFileMaxSize int 업로드 가능 최대 파일 사이즈. 0:시스템최대치
	 * @param storePath 파일 저장 위치 (기본값:Globals.fileStorePath)
	 * @return (Map<String,String>) , error = 0:정상,1:정상거부,2:에러발생 , msg = 메세지 , atchFileId = 첨부파일 ATCH_FILE_ID	 * 
	 * */
	//@param inputFileName string FORM 에서 전달된 name 값 
	//"file_1", //업로드시 form input name
	public Map<String,String> chkFileUpdate(/*String inputFileName, */Map<String, MultipartFile> files, 
			String _atchFileIdOri, String _atchFileSnOri, String _atchFileIdLabel, 
			String[] allowFileExts, int allowFileMaxSize, String _storePath) throws Exception {
		LOGGER.debug("파일 업데이트 시작");
		//LOGGER.debug("지정 FORM name 값 : " + inputFileName);
		LOGGER.debug("업로드 한 all FORM name 값 : " + Arrays.toString(files.keySet().toArray()));
		LOGGER.debug("업로드한 파일 갯수 : " + files.size());
		LOGGER.debug("해당 게시판 ATCH_FILE_ID : " + _atchFileIdOri);
		if(_atchFileIdOri==null) {
			LOGGER.debug("해당 게시판 ATCH_FILE_ID == null 빈값대체");
			_atchFileIdOri = "";
		}
		LOGGER.debug("해당 게시판 FILE_SN : " + _atchFileSnOri);
		LOGGER.debug("첨부파일라벨링 : " + _atchFileIdLabel);
		LOGGER.debug("파일저장위치 : " + _storePath);
		if(allowFileExts != null && allowFileExts.length > 0) {
			LOGGER.debug("허용할 파일 확장자 : " + Arrays.toString(allowFileExts));
		} else {
			LOGGER.debug("허용할 파일 확장자 : 무제한");
		}
		LOGGER.debug("허용할 파일 사이즈 : " + allowFileMaxSize);
		Map<String,String> map = new HashMap<String, String>();
		boolean isException = false;
		String _error = "0";
		String _atchFileExt = "";
		String _atchFileId = "";
		String _message = "";
		if(files.isEmpty()) { //업로드 파일이 없을때
			_atchFileId = _atchFileIdOri;
			_error = "0";
			_message = "업로드 한 파일이 없음";
		} else { //업로드 파일 있음
			if(_atchFileIdOri == null || _atchFileIdOri.length() == 0) { //기존에 첨부파일이 존재하지 않음
				LOGGER.debug("기존에 첨부파일이 존재하지 않음");
				try {
					LOGGER.debug("첨부파일 신규 1");
					_message = "첨부파일 신규";
					if(_atchFileIdLabel == null || _atchFileIdLabel.length()==0) {
						_atchFileIdLabel = "NAK_";
					}
					int tempFileSn = 0;
					if(_atchFileSnOri!=null && _atchFileSnOri.length()!=0) {//특정 파일
						tempFileSn = Integer.parseInt(_atchFileSnOri);
					}
					List<FileVO> _result = fileUtil.parseFileInf(files, _atchFileIdLabel, tempFileSn, "", _storePath);
					for(FileVO f : _result) {
						if (f.atchFileId.equals("ext_error")) {
							isException = true;
							_error = "2";
							_message = "파일 검증 실패";
							LOGGER.debug("파일 검증 실패");
						} else {
							List<String> allowExtList = Arrays.asList(allowFileExts);
							if(allowExtList.size() > 0) {
								if(allowExtList.contains(f.getFileExtsn().toLowerCase().toString())) {
									//허용
								} else {
									isException = true;
									_error = "1";
									_message = "허용되지 않는 첨부파일";
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
									_error = "1";
									_message = "허용되지 않는 파일크기";
									LOGGER.debug("허용되지 않는 파일크기");
								}
							}
							if(f.getFileExtsn().equalsIgnoreCase("jpge") || f.getFileExtsn().equalsIgnoreCase("jpg") || f.getFileExtsn().equalsIgnoreCase("png") 
							|| f.getFileExtsn().equalsIgnoreCase("gif") || f.getFileExtsn().equalsIgnoreCase("bmp")
							) {
								_atchFileExt = "IMG";
							} else if(f.getFileExtsn().equalsIgnoreCase("mp4") || f.getFileExtsn().equalsIgnoreCase("avi") || f.getFileExtsn().equalsIgnoreCase("wmv")
							) {
								_atchFileExt = "MOV";
							} else if(f.getFileExtsn().equalsIgnoreCase("hwp") || f.getFileExtsn().equalsIgnoreCase("pdf") || f.getFileExtsn().equalsIgnoreCase("doc")
							|| f.getFileExtsn().equalsIgnoreCase("xls") || f.getFileExtsn().equalsIgnoreCase("xlsx") || f.getFileExtsn().equalsIgnoreCase("csv")
							|| f.getFileExtsn().equalsIgnoreCase("txt")
							) {
								_atchFileExt = "DOC";
							} else if(f.getFileExtsn().equalsIgnoreCase("zip")) {
								_atchFileExt = "ZIP";
							} else {
								_atchFileExt = "ETC";
							}
						}
					}
					if(!isException) { 
						_atchFileId = fileMngService.insertFileInfs(_result);//파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
					}
				} catch(Exception e) {
					LOGGER.debug("[파일 업로드 에러] "+e.toString());
					isException = true;
					_error = "2";
					_message = "파일 업로드 에러";
				}
			} else { //기존 첨부파일이 존재함
				LOGGER.debug("기존 첨부파일이 존재함");
				try {
					_atchFileId = _atchFileIdOri;
					boolean isUpdateFile = false;
					boolean isDeleteFile = false;
					_atchFileIdLabel = _atchFileIdOri.substring(0,_atchFileIdOri.lastIndexOf("_")+1);
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(_atchFileIdOri);
					int _maxFileSn = fileMngService.getMaxFileSN(fvo);
					if(_atchFileSnOri!=null && _atchFileSnOri.length()!=0) {//특정 파일
						LOGGER.debug("지정 파일이 있음");
						isDeleteFile = true;
						isUpdateFile = true;
					} else {//특정한 파일 없음
						LOGGER.debug("지정 파일이 없음");
						isDeleteFile = false;
						isUpdateFile = false;
					}
					/*
					MultipartFile mf = files.get(inputFileName);
					FileVO chkVO = new FileVO();
					chkVO.setAtchFileId(_atchFileIdOri);
					chkVO.setSearchWrd(mf.getOriginalFilename());
					chkVO.setSearchCnd("orignlFileNm");
					Map<String, Object> slist = fileMngService.selectFileListByFileNm(chkVO);
					int resultCnt = Integer.parseInt(slist.get("resultCnt").toString());
					if(resultCnt > 0) { //같은 파일이 있음
						if(_atchFileSnOri!=null && _atchFileSnOri.length()!=0) {//특정 파일
							LOGGER.debug("파일 있음 - 같은 파일이 있음 - 특정 파일");
							isDeleteFile = true;
							isUpdateFile = true;
						} else {//특정한 파일 없음
							LOGGER.debug("파일 있음 - 같은 파일이 있음");
							isDeleteFile = false;
							isUpdateFile = false;
						}
					} else { //같은 파일이 없음
						if(_atchFileSnOri!=null && _atchFileSnOri.length()!=0) {//특정 파일
							LOGGER.debug("파일 있음 - 같은 파일이 없음 - 특정 파일");
							isDeleteFile = true;
							isUpdateFile = true;
						} else {//특정한 파일 없음
							LOGGER.debug("파일 있음 - 같은 파일이 없음");
							isDeleteFile = false;
							isUpdateFile = false;
						}
					}	
					*/
					if(isUpdateFile) { //교체
						LOGGER.debug("첨부 파일 교체 2");
						_message = "첨부파일 교체";
						List<FileVO> _result = fileUtil.parseFileInf(files, _atchFileIdLabel, Integer.parseInt(_atchFileSnOri), _atchFileIdOri, _storePath);
						for(FileVO f : _result) {
							if (f.atchFileId.equals("ext_error")) {
								isException = true;
								_error = "2";
								_message = "파일 검증 실패";
								LOGGER.debug("파일 검증 실패");
							} else {
								List<String> allowExtList = Arrays.asList(allowFileExts);
								if(allowExtList.size() > 0) {
									if(allowExtList.contains(f.getFileExtsn().toLowerCase().toString())) {
										//허용
									} else {
										isException = true;
										_error = "1";
										_message = "허용되지 않는 첨부파일";
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
										_error = "1";
										_message = "허용되지 않는 파일크기";
										LOGGER.debug("허용되지 않는 파일크기");
									}
								}
								if(f.getFileExtsn().equalsIgnoreCase("jpge") || f.getFileExtsn().equalsIgnoreCase("jpg") || f.getFileExtsn().equalsIgnoreCase("png") 
								|| f.getFileExtsn().equalsIgnoreCase("gif") || f.getFileExtsn().equalsIgnoreCase("bmp")
								) {
									_atchFileExt = "IMG";
								} else if(f.getFileExtsn().equalsIgnoreCase("mp4") || f.getFileExtsn().equalsIgnoreCase("avi") || f.getFileExtsn().equalsIgnoreCase("wmv")
								) {
									_atchFileExt = "MOV";
								} else if(f.getFileExtsn().equalsIgnoreCase("hwp") || f.getFileExtsn().equalsIgnoreCase("pdf") || f.getFileExtsn().equalsIgnoreCase("doc")
								|| f.getFileExtsn().equalsIgnoreCase("xls") || f.getFileExtsn().equalsIgnoreCase("xlsx") || f.getFileExtsn().equalsIgnoreCase("csv")
								|| f.getFileExtsn().equalsIgnoreCase("txt")
								) {
									_atchFileExt = "DOC";
								} else if(f.getFileExtsn().equalsIgnoreCase("zip")) {
									_atchFileExt = "ZIP";
								} else {
									_atchFileExt = "ETC";
								}
							}
						}
						if(_result.size() <= 0) {
							isException = true;
							_error = "1";
							_message = "첨부 된 파일이 없음";
							LOGGER.debug("첨부 된 파일이 하나도 없음");
						}
						if(!isException) {
							if(isDeleteFile) {
								delete(_atchFileIdOri,_atchFileSnOri);
							}
							fileMngService.updateFileInfs(_result);
						}
					} else { //신규
						LOGGER.debug("첨부 파일 신규 2");
						_message = "첨부파일 신규";
						List<FileVO> _result = fileUtil.parseFileInf(files, _atchFileIdLabel, _maxFileSn, _atchFileIdOri, _storePath);
						for(FileVO f : _result) {
							if (f.atchFileId.equals("ext_error")) {
								isException = true;
								_error = "2";
								_message = "파일 검증 실패";
								LOGGER.debug("파일 검증 실패");
							} else {
								List<String> allowExtList = Arrays.asList(allowFileExts);
								if(allowExtList.size() > 0) {
									if(allowExtList.contains(f.getFileExtsn().toLowerCase().toString())) {
										//허용
									} else {
										isException = true;
										_error = "1";
										_message = "허용되지 않는 첨부파일";
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
										_error = "1";
										_message = "허용되지 않는 파일크기";
										LOGGER.debug("허용되지 않는 파일크기");
									}
								}
								if(f.getFileExtsn().equalsIgnoreCase("jpge") || f.getFileExtsn().equalsIgnoreCase("jpg") || f.getFileExtsn().equalsIgnoreCase("png") 
								|| f.getFileExtsn().equalsIgnoreCase("gif") || f.getFileExtsn().equalsIgnoreCase("bmp")
								) {
									_atchFileExt = "IMG";
								} else if(f.getFileExtsn().equalsIgnoreCase("mp4") || f.getFileExtsn().equalsIgnoreCase("avi") || f.getFileExtsn().equalsIgnoreCase("wmv")
								) {
									_atchFileExt = "MOV";
								} else if(f.getFileExtsn().equalsIgnoreCase("hwp") || f.getFileExtsn().equalsIgnoreCase("pdf") || f.getFileExtsn().equalsIgnoreCase("doc")
								|| f.getFileExtsn().equalsIgnoreCase("xls") || f.getFileExtsn().equalsIgnoreCase("xlsx") || f.getFileExtsn().equalsIgnoreCase("csv")
								|| f.getFileExtsn().equalsIgnoreCase("txt")
								) {
									_atchFileExt = "DOC";
								} else if(f.getFileExtsn().equalsIgnoreCase("zip")) {
									_atchFileExt = "ZIP";
								} else {
									_atchFileExt = "ETC";
								}
							}
						}
						if(!isException) {
							if(isDeleteFile) {
								delete(_atchFileIdOri,_atchFileSnOri);
							}
							fileMngService.updateFileInfs(_result);
						}
					}
				} catch(Exception e) {
					LOGGER.debug("[파일 업로드 에러] "+e.toString());
					isException = true;
					_error = "2";
					_message = "파일 업로드 에러";
					_atchFileId = _atchFileIdOri;
				}
			}
		}
		//파일 갯수 검증
		_atchFileId = chkFileCount(_atchFileId);
		//
		LOGGER.debug("error : " + _error);
		LOGGER.debug("msg : " + _message);
		LOGGER.debug("atchFileId : " + _atchFileId);
		LOGGER.debug("atchFileExt : " + _atchFileExt);
		map.put("error", _error);
		map.put("msg", _message);
		map.put("atchFileId", _atchFileId==null?_atchFileIdOri:_atchFileId);
		map.put("atchFileExt", _atchFileExt);
		return map;		
	}
	
	
	/**
	 * 단일 첨부파일 chkFileUpdate 메소드
	 * @param bd_file_se2 string 스마트에디터 첨부파일 아이디
	 * @param htmltag 스마트에디터 컨텐츠 내용
	 * @return string 스마트에디터 첨부파일 아이디, 모두 삭제 한 경우 빈값
	 * */
	public String chkFileUpdateSe2(String bd_file_se2, String htmltag) throws Exception {
		LOGGER.debug("스마트에디터 첨부파일 검증 시작");
		LOGGER.debug("bd_file_se2 : " + bd_file_se2);
		Document doc = Jsoup.parseBodyFragment(htmltag); 
		Element body = doc.body();
		List<Element> se2imgs = body.getElementsByClass("se2img");
		String fid = "";
		if(se2imgs.isEmpty()) {
			LOGGER.debug("스마트에디터 내용안 첨부파일 없음");
			if(bd_file_se2==null || bd_file_se2.length()==0) {
				LOGGER.debug("삭제 할 파일 없음");
			} else {
				LOGGER.debug("저장 된 첨부파일 모두 삭제");
				fileMngService.deleteAllDetailFileInfs(bd_file_se2);
			}
		} else {
			bd_file_se2 = fid = body.getElementsByClass("se2img").get(0).attr("data-id");
			FileVO fvo = new FileVO();
			fvo.setAtchFileId(fid);
			List<FileVO> flist = fileMngService.selectFileInfs(fvo);
			for(FileVO f : flist) {
				boolean isExist = false;
				for(Element el : body.getElementsByClass("se2img")) {
					String fsn = el.attr("data-sn");
					LOGGER.debug("검증중["+fid+"] => filesn : " + f.getFileSn() + " , fsn : " + fsn);
					if(fsn.equals(f.getFileSn())) {
						isExist = true;
					}	
				}
				if(isExist) {
					LOGGER.debug("첨부파일이 내용에 있음");
				} else {
					LOGGER.debug("첨부파일이 내용에서 없으므로 삭제 처리 , fsn : " + f.getFileSn());
					FileVO dVo = new FileVO();
					dVo.setAtchFileId(fid);
					dVo.setFileSn(f.getFileSn());
					fileMngService.deleteFileInf(dVo);
				}
			}
		}		
		LOGGER.debug("스마트에디터 첨부파일 검증 종료");
		//파일 갯수 검증
		return chkFileCount(bd_file_se2);
	}
	
	
}
