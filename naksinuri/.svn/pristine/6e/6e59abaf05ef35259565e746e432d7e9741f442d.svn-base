package egovframework.naksinuri_original.com.cmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService;
import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovFileMngServiceImpl.java
 * @Description : 파일정보의 관리를 위한 구현 클래스
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
@Service("NaksinuriOriginalEgovFileMngService")
public class NaksinuriOriginalEgovFileMngServiceImpl extends EgovAbstractServiceImpl implements NaksinuriOriginalEgovFileMngService {

	@Resource(name = "NaksinuriOriginalFileManageDAO")
	private NaksinuriOriginalFileManageDAO fileMngDAO;
	
	
	//첨부파일 다운로드 횟수 업데이트
	@Override
	public void update_hit_naksinuri_original(NaksinuriOriginalFileVO fv) throws Exception {
		fileMngDAO.update_hit_naksinuri_original(fv);
	}
	

	/**
	 * 여러 개의 파일을 삭제한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#deleteFileInfs(java.util.List)
	 */
	@Override
	public void deleteFileInfs_naksinuri_original(List<?> fvoList) throws Exception {
		fileMngDAO.deleteFileInfs_naksinuri_original(fvoList);
	}
	@Override
	public void deleteFileInfsPreview_naksinuri_original(List<?> fvoList) throws Exception {
		fileMngDAO.deleteFileInfsPreview_naksinuri_original(fvoList);
	}

	

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#insertFileInf(egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO)
	 */
	@Override
	public String insertFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		String atchFileId = fvo.getAtchFileId();

		fileMngDAO.insertFileInf_naksinuri_original(fvo);

		return atchFileId;
	}
	@Override
	public String insertFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		String atchFileId = fvo.getAtchFileId();

		fileMngDAO.insertFileInfPreview_naksinuri_original(fvo);

		return atchFileId;
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#insertFileInfs(java.util.List)
	 */
	@Override
	public String insertFileInfs_naksinuri_original(List<?> fvoList) throws Exception {
		String atchFileId = "";

		if (fvoList.size() != 0) {
			atchFileId = fileMngDAO.insertFileInfs_naksinuri_original(fvoList);
		}
		if (atchFileId == "") {
			atchFileId = null;
		}
		return atchFileId;
	}
	@Override
	public String insertFileInfsPreview_naksinuri_original(List<?> fvoList) throws Exception {
		String atchFileId = "";

		if (fvoList.size() != 0) {
			atchFileId = fileMngDAO.insertFileInfsPreview_naksinuri_original(fvoList);
		}
		if (atchFileId == "") {
			atchFileId = null;
		}
		return atchFileId;
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#selectFileInfs(egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO)
	 */
	@Override
	public List<NaksinuriOriginalFileVO> selectFileInfs_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return fileMngDAO.selectFileInfs_naksinuri_original(fvo);
	}
	@Override
	public List<NaksinuriOriginalFileVO> selectFileInfsPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return fileMngDAO.selectFileInfsPreview_naksinuri_original(fvo);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#updateFileInfs(java.util.List)
	 */
	@Override
	public void updateFileInfs_naksinuri_original(List<?> fvoList) throws Exception {
		//Delete & Insert
		fileMngDAO.updateFileInfs_naksinuri_original(fvoList);
	}
	@Override
	public void updateFileInfsPreview_naksinuri_original(List<?> fvoList) throws Exception {
		//Delete & Insert
		fileMngDAO.updateFileInfsPreview_naksinuri_original(fvoList);
	}

	/**
	 * 하나의 파일을 삭제한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#deleteFileInf(egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO)
	 */
	@Override
	public void deleteFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		fileMngDAO.deleteFileInf_naksinuri_original(fvo);
	}
	@Override
	public void deleteFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		fileMngDAO.deleteFileInfPreview_naksinuri_original(fvo);
	}

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#selectFileInf(egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO)
	 */
	@Override
	public NaksinuriOriginalFileVO selectFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return fileMngDAO.selectFileInf_naksinuri_original(fvo);
	}
	@Override
	public NaksinuriOriginalFileVO selectFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return fileMngDAO.selectFileInfPreview_naksinuri_original(fvo);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#getMaxFileSN(egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO)
	 */
	@Override
	public int getMaxFileSN_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return fileMngDAO.getMaxFileSN_naksinuri_original(fvo);
	}
	@Override
	public int getMaxFileSNPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		return fileMngDAO.getMaxFileSNPreview_naksinuri_original(fvo);
	}

	/**
	 * 전체 파일을 삭제한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#deleteAllFileInf(egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO)
	 */
	@Override
	public void deleteAllFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		fileMngDAO.deleteAllFileInf_naksinuri_original(fvo);
	}
	@Override
	public void deleteAllFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		fileMngDAO.deleteAllFileInfPreview_naksinuri_original(fvo);
	}

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#selectFileListByFileNm(egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO)
	 */
	@Override
	public Map<String, Object> selectFileListByFileNm_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		List<NaksinuriOriginalFileVO> result = fileMngDAO.selectFileListByFileNm_naksinuri_original(fvo);
		int cnt = fileMngDAO.selectFileListCntByFileNm_naksinuri_original(fvo);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	@Override
	public Map<String, Object> selectFileListByFileNmPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception {
		List<NaksinuriOriginalFileVO> result = fileMngDAO.selectFileListByFileNmPreview_naksinuri_original(fvo);
		int cnt = fileMngDAO.selectFileListCntByFileNmPreview_naksinuri_original(fvo);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 *
	 * @see egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalEgovFileMngService#selectImageFileList(egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO)
	 */
	@Override
	public List<NaksinuriOriginalFileVO> selectImageFileList_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception {
		return fileMngDAO.selectImageFileList_naksinuri_original(vo);
	}
	@Override
	public List<NaksinuriOriginalFileVO> selectImageFileListPreview_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception {
		return fileMngDAO.selectImageFileListPreview_naksinuri_original(vo);
	}
}
