package egovframework.naksinuri_original.com.cmm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;

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
@Repository("NaksinuriOriginalFileManageDAO")
public class NaksinuriOriginalFileManageDAO extends EgovComAbstractDAO {
	
	//첨부파일 다운로드 횟수 업데이트
	public void update_hit_naksinuri_original(NaksinuriOriginalFileVO fv) throws Exception {
		update("filedownload_hit_up",fv);
	}
	

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param fileList
	 * @return
	 * @throws Exception
	 */
	public String insertFileInfs_naksinuri_original(List<?> fileList) throws Exception {
		NaksinuriOriginalFileVO vo = (NaksinuriOriginalFileVO) fileList.get(0);
		String atchFileId = vo.getAtchFileId();

		insert("NaksinuriOriginalFileManageDAO.insertFileMaster", vo);
		insert("NaksinuriOriginalFileManageDAO.insertFileMaster2", vo);

		Iterator<?> iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (NaksinuriOriginalFileVO) iter.next();

			insert("NaksinuriOriginalFileManageDAO.insertFileDetail", vo);
			insert("NaksinuriOriginalFileManageDAO.insertFileDetail2", vo);
		}

		return atchFileId;
	}
	public String insertFileInfsPreview_naksinuri_original(List<?> fileList) throws Exception {
		NaksinuriOriginalFileVO vo = (NaksinuriOriginalFileVO) fileList.get(0);
		String atchFileId = vo.getAtchFileId();

		insert("NaksinuriOriginalFileManageDAO.insertFileMasterPreview", vo);

		Iterator<?> iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (NaksinuriOriginalFileVO) iter.next();

			insert("NaksinuriOriginalFileManageDAO.insertFileDetailPreview", vo);
		}

		return atchFileId;
	}
	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param vo
	 * @throws Exception
	 */
	public void insertFileInf_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception {
		insert("NaksinuriOriginalFileManageDAO.insertFileMaster", vo);
		insert("NaksinuriOriginalFileManageDAO.insertFileDetail", vo);
	}
	public void insertFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception {
		insert("NaksinuriOriginalFileManageDAO.insertFileMasterPreview", vo);
		insert("NaksinuriOriginalFileManageDAO.insertFileDetailPreview", vo);
	}
	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 *
	 * @param fileList
	 * @throws Exception
	 */
	public void updateFileInfs_naksinuri_original(List<?> fileList) throws Exception {
		NaksinuriOriginalFileVO vo;
		Iterator<?> iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (NaksinuriOriginalFileVO) iter.next();

			insert("NaksinuriOriginalFileManageDAO.insertFileDetail", vo);
		}
	}
	public void updateFileInfsPreview_naksinuri_original(List<?> fileList) throws Exception {
		NaksinuriOriginalFileVO vo;
		Iterator<?> iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (NaksinuriOriginalFileVO) iter.next();

			insert("NaksinuriOriginalFileManageDAO.insertFileDetailPreview", vo);
		}
	}
	/**
	 * 여러 개의 파일을 삭제한다.
	 *
	 * @param fileList
	 * @throws Exception
	 */
	public void deleteFileInfs_naksinuri_original(List<?> fileList) throws Exception {
		Iterator<?> iter = fileList.iterator();
		NaksinuriOriginalFileVO vo;
		while (iter.hasNext()) {
			vo = (NaksinuriOriginalFileVO) iter.next();

			delete("NaksinuriOriginalFileManageDAO.deleteFileDetail", vo);
		}
	}
	public void deleteFileInfsPreview_naksinuri_original(List<?> fileList) throws Exception {
		Iterator<?> iter = fileList.iterator();
		NaksinuriOriginalFileVO vo;
		while (iter.hasNext()) {
			vo = (NaksinuriOriginalFileVO) iter.next();

			delete("NaksinuriOriginalFileManageDAO.deleteFileDetailPreview", vo);
		}
	}

	/**
	 * 하나의 파일을 삭제한다.
	 *
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		delete("NaksinuriOriginalFileManageDAO.deleteFileDetail", fvo);
	}
	public void deleteFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		delete("NaksinuriOriginalFileManageDAO.deleteFileDetailPreview", fvo);
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<NaksinuriOriginalFileVO> selectFileInfs_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception {
		return (List<NaksinuriOriginalFileVO>) list("NaksinuriOriginalFileManageDAO.selectFileList", vo);
	}
	public List<NaksinuriOriginalFileVO> selectFileInfsPreview_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception {
		return (List<NaksinuriOriginalFileVO>) list("NaksinuriOriginalFileManageDAO.selectFileListPreview", vo);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int getMaxFileSN_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return (Integer) select("NaksinuriOriginalFileManageDAO.getMaxFileSN", fvo);
	}
	public int getMaxFileSNPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return (Integer) select("NaksinuriOriginalFileManageDAO.getMaxFileSNPreview", fvo);
	}
	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public NaksinuriOriginalFileVO selectFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return (NaksinuriOriginalFileVO) select("NaksinuriOriginalFileManageDAO.selectFileInf", fvo);
	}
	public NaksinuriOriginalFileVO selectFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return (NaksinuriOriginalFileVO) select("NaksinuriOriginalFileManageDAO.selectFileInfPreview", fvo);
	}
	/**
	 * 전체 파일을 삭제한다.
	 *
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteAllFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		update("NaksinuriOriginalFileManageDAO.deleteCOMTNFILE", fvo);
	}
	public void deleteAllFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		update("NaksinuriOriginalFileManageDAO.deleteCOMTNFILEPreview", fvo);
	}
	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<NaksinuriOriginalFileVO> selectFileListByFileNm_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return (List<NaksinuriOriginalFileVO>) list("NaksinuriOriginalFileManageDAO.selectFileListByFileNm", fvo);
	}
	public List<NaksinuriOriginalFileVO> selectFileListByFileNmPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return (List<NaksinuriOriginalFileVO>) list("NaksinuriOriginalFileManageDAO.selectFileListByFileNmPreview", fvo);
	}
	/**
	 * 파일명 검색에 대한 목록 전체 건수를 조회한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int selectFileListCntByFileNm_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return (Integer) select("NaksinuriOriginalFileManageDAO.selectFileListCntByFileNm", fvo);
	}
	public int selectFileListCntByFileNmPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return (Integer) select("NaksinuriOriginalFileManageDAO.selectFileListCntByFileNmPreview", fvo);
	}
	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<NaksinuriOriginalFileVO> selectImageFileList_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception {
		return (List<NaksinuriOriginalFileVO>) list("NaksinuriOriginalFileManageDAO.selectImageFileList", vo);
	}
	public List<NaksinuriOriginalFileVO> selectImageFileListPreview_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception {
		return (List<NaksinuriOriginalFileVO>) list("NaksinuriOriginalFileManageDAO.selectImageFileListPreview", vo);
	}
}
