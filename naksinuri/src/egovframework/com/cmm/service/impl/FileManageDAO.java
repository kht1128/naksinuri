package egovframework.com.cmm.service.impl;

import java.util.Iterator;
import java.util.List;

import egovframework.com.cmm.service.DwldConfimFileVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @Class Name : EgovFileMngDAO.java
 * @Description : 파일정보 관리를 위한 데이터 처리 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭    최초생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@Repository("FileManageDAO")
public class FileManageDAO extends EgovComAbstractDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileManageDAO.class);
	
	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param fileList
	 * @return
	 * @throws Exception
	 */
	public String insertFileInfs(List<?> fileList) throws Exception {
		FileVO vo = (FileVO) fileList.get(0);
		String atchFileId = vo.getAtchFileId();

		insert("FileManageDAO.insertFileMaster", vo);

		Iterator<?> iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (FileVO) iter.next();

			insert("FileManageDAO.insertFileDetail", vo);
		}

		return atchFileId;
	}

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param vo
	 * @throws Exception
	 */
	public void insertFileInf(FileVO vo) throws Exception {
		insert("FileManageDAO.insertFileMaster", vo);
		insert("FileManageDAO.insertFileDetail", vo);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 *
	 * @param fileList
	 * @throws Exception
	 */
	public void updateFileInfs(List<?> fileList) throws Exception {
		FileVO vo;
		Iterator<?> iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (FileVO) iter.next();

			insert("FileManageDAO.insertFileDetail", vo);
		}
	}

	/**
	 * 여러 개의 파일을 삭제한다.
	 *
	 * @param fileList
	 * @throws Exception
	 */
	public void deleteFileInfs(List<?> fileList) throws Exception {
		Iterator<?> iter = fileList.iterator();
		FileVO vo;
		while (iter.hasNext()) {
			vo = (FileVO) iter.next();

			delete("FileManageDAO.deleteFileDetail", vo);
		}
	}

	/**
	 * 하나의 파일을 삭제한다.
	 *
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteFileInf(FileVO fvo) throws Exception {
		delete("FileManageDAO.deleteFileDetail", fvo);
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectFileInfs(FileVO vo) throws Exception {
		return (List<FileVO>) list("FileManageDAO.selectFileList", vo);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int getMaxFileSN(FileVO fvo) throws Exception {
		return (Integer) select("FileManageDAO.getMaxFileSN", fvo);
	}

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public FileVO selectFileInf(FileVO fvo) throws Exception {
		return (FileVO) select("FileManageDAO.selectFileInf", fvo);
	}

	/**
	 * 전체 파일을 삭제한다.
	 *
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteAllFileInf(FileVO fvo) throws Exception {
		update("FileManageDAO.deleteCOMTNFILE", fvo);
	}

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectFileListByFileNm(FileVO fvo) throws Exception {
		return (List<FileVO>) list("FileManageDAO.selectFileListByFileNm", fvo);
	}

	/**
	 * 파일명 검색에 대한 목록 전체 건수를 조회한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int selectFileListCntByFileNm(FileVO fvo) throws Exception {
		return (Integer) select("FileManageDAO.selectFileListCntByFileNm", fvo);
	}

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
		return (List<FileVO>) list("FileManageDAO.selectImageFileList", vo);
	}
	
	/**
	 * 동영상 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectMovFileList(FileVO vo) throws Exception {
		return (List<FileVO>) list("FileManageDAO.selectMovFileList", vo);
	}

	/**
	 * 파일의 총 갯수를 구한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int getMaxFileCnt(FileVO vo) throws Exception {
		return (int) select("FileManageDAO.getMaxFileCnt", vo);
	}

	/**
	 * 파일 마스터 값을 임의 생성한다.
	 *
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public void insertFileMasterInf(String atchFileId) {
		insert("FileManageDAO.insertFileMasterInf",atchFileId);
	}
	
	
	/**
	 * 모든 하위 파일을 삭제한다.
	 *
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public void deleteAllDetailFileInfs(String atchFileId) {
		delete("FileManageDAO.deleteAllDetailFileInfs",atchFileId);
	}
	
	/**
	 * 전체 파일을 삭제한다.
	 * 위 , deleteAllFileInf(FileVO fvo) 와 같으나 변수를 다르게 받음.
	 *
	 * @param atchFileId
	 * @throws Exception
	 */
	public void deleteAllFileInf(String atchFileId) throws Exception {
		update("FileManageDAO.deleteAllFileInf", atchFileId);
	}


	/**
	 * 승인제 파일 처리를 위한 메소드
	 * @param vo
	 * @throws Exception
	 */
	public void insertDetailFileInfForConfirm(FileVO vo) throws Exception {
		FileVO rfvo = (FileVO)select("FileManageDAO.getExistAtchFileId", vo);
		if(rfvo!=null && rfvo.getAtchFileId()!=null && rfvo.getAtchFileId().length()!=0) {
			insert("FileManageDAO.insertDetailFileInfForConfirm", vo);
		} else {
			LOGGER.debug("해당 키값이 존재 하지 않아 생성함");
			insert("FileManageDAO.insertFileMaster", vo);	
			insert("FileManageDAO.insertDetailFileInfForConfirm", vo);
		}
	}

	public void updateDetailFileInfForConfirm(FileVO vo) throws Exception {
		update("FileManageDAO.updateDetailFileInfForConfirm", vo);
	}
	
	public List<DwldConfimFileVO> get_dwld_confim_list(DwldConfimFileVO dwldConfimFileVO) {
		return (List<DwldConfimFileVO>) list("FileManageDAO.get_dwld_confim_list", dwldConfimFileVO);
	}

	public int get_dwld_confim_list_totcnt(DwldConfimFileVO dwldConfimFileVO) {
		return (int) select("FileManageDAO.get_dwld_confim_list_totcnt", dwldConfimFileVO);
	}

	public DwldConfimFileVO get_dwld_confim_info(DwldConfimFileVO dwldConfimFileVO) {
		return (DwldConfimFileVO) select("FileManageDAO.get_dwld_confim_info", dwldConfimFileVO);
	}

	public boolean get_dwld_req_lock(DwldConfimFileVO dwldConfimFileVO) {
		String cnt = (String) select("FileManageDAO.get_dwld_req_lock", dwldConfimFileVO);
		return cnt!=null && Integer.parseInt(cnt)>0?true:false;
	}
	
}
