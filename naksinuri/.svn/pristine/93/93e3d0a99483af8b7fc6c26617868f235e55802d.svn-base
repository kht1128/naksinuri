package egovframework.com.cmm.service;

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
public interface EgovFileMngService {
	
    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectFileInfs(FileVO fvo) throws Exception;

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvo
     * @throws Exception
     */
    public String insertFileInf(FileVO fvo) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public String insertFileInfs(List<?> fvoList) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public void updateFileInfs(List<?> fvoList) throws Exception;

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public void deleteFileInfs(List<?> fvoList) throws Exception;

    /**
     * 하나의 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(FileVO fvo) throws Exception;

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception;

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(FileVO fvo) throws Exception;

    /**
     * 전체 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(FileVO fvo) throws Exception;

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectFileListByFileNm(FileVO fvo) throws Exception;

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception;
    
    /**
     * 동영상 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectMovFileList(FileVO vo) throws Exception;

    /**
     * 파일의 총 갯수를 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
	public int getMaxFileCnt(FileVO fvo) throws Exception;


    /**
     * 파일 마스터 값을 임의 생성한다.
     *
     * @param string
     * @return
     * @throws Exception
     */
	public void insertFileMasterInf(String _atchFileId) throws Exception;

	
	/**
     * 모든 하위 파일을 삭제한다. deleteAllFileInf 와 다름.
     *
     * @param string
     * @return
     * @throws Exception
     */
	public void deleteAllDetailFileInfs(String atchFileId) throws Exception;
	
	/**
     * 파일 마스터 값을 사용안함 처리한다.
     * 위 , deleteAllFileInf(FileVO fvo) 와 같으나 변수를 다르게 받음.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(String atchFileId) throws Exception;

    /**
     * [승인제] 요청한 파일을 생성하여 저장한다.
     *
     * @param fvo
     * @return String
     * @throws Exception
     */
	public String insertDetailFileInfForConfirm(FileVO vo) throws Exception;

	/**
     * [승인제] 파일의 정보를 갱신한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
	public void updateDetailFileInfForConfirm(FileVO vo) throws Exception;
	
	/**
     * [승인제] 파일 목록
     *
     * @param fvo
     * @return
     * @throws Exception
     */
	public List<DwldConfimFileVO> get_dwld_confim_list(DwldConfimFileVO dwldConfimFileVO) throws Exception;
	
	/**
     * [승인제] 파일 목록 총 갯수
     *
     * @param fvo
     * @return Integer
     * @throws Exception
     */
	public int get_dwld_confim_list_totcnt(DwldConfimFileVO dwldConfimFileVO) throws Exception;

	/**
     * [승인제] 파일 정보 조회
     *
     * @param fvo
     * @return 
     * @throws Exception
     */
	public DwldConfimFileVO get_dwld_confim_info(DwldConfimFileVO dwldConfimFileVO);

	/**
     * [승인제] 단시간 다운로드 제한 여부
     *
     * @param fvo
     * @return boolean
     * @throws Exception
     */
	public boolean get_dwld_req_lock(DwldConfimFileVO dwldConfimFileVO);
    
}
