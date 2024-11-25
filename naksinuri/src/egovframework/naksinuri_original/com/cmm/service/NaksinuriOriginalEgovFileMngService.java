package egovframework.naksinuri_original.com.cmm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : EgovFileMngService.java
 * @Description : 파일정보의 관리를 위한 서비스 인터페이스
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
public interface NaksinuriOriginalEgovFileMngService {
	
	//첨부파일 다운로드 횟수 업데이트
	public void update_hit_naksinuri_original(NaksinuriOriginalFileVO fv) throws Exception;
	

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public List<NaksinuriOriginalFileVO> selectFileInfs_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;
    public List<NaksinuriOriginalFileVO> selectFileInfsPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvo
     * @throws Exception
     */
    public String insertFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;
    public String insertFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public String insertFileInfs_naksinuri_original(List<?> fvoList) throws Exception;
    public String insertFileInfsPreview_naksinuri_original(List<?> fvoList) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public void updateFileInfs_naksinuri_original(List<?> fvoList) throws Exception;
    public void updateFileInfsPreview_naksinuri_original(List<?> fvoList) throws Exception;

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public void deleteFileInfs_naksinuri_original(List<?> fvoList) throws Exception;
    public void deleteFileInfsPreview_naksinuri_original(List<?> fvoList) throws Exception;

    /**
     * 하나의 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;
    public void deleteFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public NaksinuriOriginalFileVO selectFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;
    public NaksinuriOriginalFileVO selectFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;
    public int getMaxFileSNPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;

    /**
     * 전체 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;
    public void deleteAllFileInfPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectFileListByFileNm_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;
    public Map<String, Object> selectFileListByFileNmPreview_naksinuri_original(NaksinuriOriginalFileVO fvo) throws Exception;

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<NaksinuriOriginalFileVO> selectImageFileList_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception;
    public List<NaksinuriOriginalFileVO> selectImageFileListPreview_naksinuri_original(NaksinuriOriginalFileVO vo) throws Exception;

}
