package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;
import java.util.Map;

import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;



public interface NaksinuriService {
	
	public void insertCorp(NaksinuriVO naksinuriVO) throws Exception;
	
	public void insertCorpPreview(NaksinuriVO naksinuriVO) throws Exception;
	public void insertCorpPreviewP(NaksinuriVO naksinuriVO) throws Exception;
	public void insertCorpSanupPreview(NaksinuriSanupVO naksinuriSanupVO) throws Exception;

	public void insert_data(BoardVO boardVO) throws Exception;
	public void co_insert(BoardVO boardVO) throws Exception;
	
	
	/**
	 *  낚시 회사명 대표자명 입력 시 처리
	 * @return NaksinuriVO
	 * @param vo    NaksinuriVO
	 * @exception Exception Exception 
	 */
	public NaksinuriVO modifyCorp(NaksinuriVO naksinuriVO) throws Exception;
	public NaksinuriSanupVO modifyCorpSanup(NaksinuriSanupVO naksinuriSanupVO) throws Exception;
	public NaksinuriVO modify_fish_check(NaksinuriVO naksinuriVO) throws Exception;
	public NaksinuriSanupVO modify_ind_check(NaksinuriSanupVO naksinuriSanupVO) throws Exception;
	
	/**
	 * 회사명을 찾는다.
	 * @return NaksinuriVO
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public NaksinuriVO searchCo_nm(NaksinuriVO naksinuriVO) throws Exception;


	/**
	 * 회사 한 건 수정
	 * @param naksinuriVO
	 * @throws Exception
	 */
	public void updateInfo(NaksinuriVO naksinuriVO) throws Exception;
	
	/**
	 * 이미지 파일정보들
	 * @param naksinuriVO
	 * @throws Exception
	 *  
	 */
	public NaksinuriFileVO mimg(NaksinuriFileVO naksinurifileVO) throws Exception;
	
	public List<NaksinuriFileVO> searchFile(NaksinuriFileVO naksinurifileVO) throws Exception;
	
	
	/**
	 * 어드민 리스트.
	 * @return NaksinuriVO
	 * @param vo    LoginVO
	 * @exception Exception Exception
	 */
	public NaksinuriVO selectNaksinuri_admin(NaksinuriVO naksinuriVO) throws Exception;
	public List<NaksinuriVO> getListNaksinuri_admin(NaksinuriVO naksinuriVO) throws Exception;
	/**
	 *  어드민 게시물 상세 정보 
	 * @return NaksinuriVO
	 * @param vo    NaksinuriVO
	 * @exception Exception Exception 
	 */
	public NaksinuriVO modifyCorp_admin(NaksinuriVO naksinuriVO) throws Exception;
	public NaksinuriVO modifyCorpPreview_admin(NaksinuriVO naksinuriVO) throws Exception;

	public NaksinuriFileVO admin_mimg(NaksinuriFileVO naksinurifileVO) throws Exception;
	public NaksinuriFileVO adminPreview_mimg(NaksinuriFileVO naksinurifileVO) throws Exception;
	
	public List<NaksinuriFileVO> admin_searchFile(NaksinuriFileVO naksinurifileVO) throws Exception;
	/**
	 *  fishjob 리스트 정보 
	 * @return NaksinuriVO
	 * @param vo    NaksinuriVO
	 * @exception Exception Exception 
	 */
	public List<NaksinuriFileVO> getListFishJob(NaksinuriFileVO naksinurifileVO) throws Exception;
	public List<NaksinuriFileVO> getListFishCompany(NaksinuriFileVO naksinurifileVO) throws Exception;
	public List<NaksinuriVO> getListOceanFshIdex(NaksinuriVO naksinuriVO) throws Exception;
	public List<NaksinuriFileVO> getMapListFishCompany(NaksinuriFileVO naksinurifileVO) throws Exception;
	
	/**
	 * 진행중인 설문조사 게시판 리스트 불러오기
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public List<SurveyVO> survey_select_list(SurveyVO surveyVO) throws Exception;
	
	public List<SurveyVO> survey_select_list_user(SurveyVO surveyVO) throws Exception;
	/**
	 * 종료된 설문조사 게시판 리스트 불러오기
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public List<SurveyVO> end_survey_select_list(SurveyVO surveyVO) throws Exception;
	
	public List<SurveyVO> end_survey_select_list_user(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 설문조사 선택글 불러오기
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public SurveyVO select_survey(SurveyVO surveyVO) throws Exception;
	
	public SurveyVO check_survey_by_ip(SurveyVO surveyVO) throws Exception;
	/**
	 * 설문조사 응답 모음 주관식, 기타 항목
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public List<SurveyVO> survey_answerole_list(SurveyVO surveyVO) throws Exception;
	
	public List<SurveyVO> select_surveyq_list(SurveyVO surveyVO) throws Exception;
	
	public List<SurveyVO> select_surveyqi_list(SurveyVO surveyVO) throws Exception;
	/**
	 * 제목으로 설문번호 가져오기
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public SurveyVO select_survey_subject(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 제목으로 설문지질문번호 가져오기
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */	
	public SurveyVO select_surveyq_subject(SurveyVO surveyVO) throws Exception;
	
	/**
	 * ip로 설문답변 최신 글 번호 가져오기
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public SurveyVO survey_answfind(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 설문 총 참여자
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public List<SurveyVO> survey_anscnt(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 설문조사 메인정보 수정
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public void survey_update_data(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 설문조사 질문사항 수정
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public void survey_update_quest(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 설문조사 질문아이템 수정
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public void survey_update_quest_item(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 설문조사 질문 삭제
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public void delete_survey_quest(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 설문조사 질문아이템 삭제
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	public void delete_survey_questitem(SurveyVO surveyVO) throws Exception;
	
	/**
	 * 낚시연구소 게시판 등록
	 * @param boardVO
	 * @throws Exception
	 */
	public void insert_lab_data(BoardVO boardVO) throws Exception;
	
	public void delete_list(NaksinuriVO naksinuriVO) throws Exception;
	
	
	/**
	 * 게시판 정보
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> select_list(BoardVO boardVO) throws Exception;
	public List<BoardVO> select_list_admin(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> noticemark_list(BoardVO boardVO) throws Exception;
	
	// 메인이미지
	public BoardVO bmimg(BoardVO boardVO) throws Exception;
	
	//사용자 자신이 등록한 글 수정페이지에서 연락처와 이메일 값 가져오기.
	public BoardVO phone1_findCorp(BoardVO boardVO) throws Exception;
	public BoardVO phone2_findCorp(BoardVO boardVO) throws Exception;
	public BoardVO phone3_findCorp(BoardVO boardVO) throws Exception;
	public BoardVO email1_findCorp(BoardVO boardVO) throws Exception;
	public BoardVO email2_findCorp(BoardVO boardVO) throws Exception;
	
	// 작성자이미지
	public BoardVO bsimg(BoardVO boardVO) throws Exception;
	
	// 첨부파일
	public List<BoardVO> ba_file(BoardVO boardVO) throws Exception;
	
	// 영상파일
	public BoardVO movfile(BoardVO boardVO) throws Exception;
		
	// PDF이미지 슬라이드 파일
	public List<BoardVO> pdfimgfile(BoardVO boardVO) throws Exception;
	
	public void delete_boardlist(BoardVO boardVO) throws Exception;
	
	public BoardVO board_findCorp(BoardVO boardVO) throws Exception;
	
	public void update_data(BoardVO boardVO) throws Exception;
	
	public void view_update(BoardVO boardVO) throws Exception;
	
	public void like_update(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> select_list_comment(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> select_list_post(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> select_list_bobo(BoardVO boardVO) throws Exception;
	
	public BoardVO select_next(BoardVO boardVO) throws Exception;
	
	public BoardVO select_prev(BoardVO boardVO) throws Exception;
	
	//메인 인덱스에서 불러올 뉴스와 공지사항, 낚시조황 소식 , 민물바다선상낚시터
	public List<BoardVO> select_main_news(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> select_main_notice(BoardVO boardVO) throws Exception;
	//여행 모바일 버전 메인에 2개만 보여줌
	public List<BoardVO> river_anglingm(BoardVO boardVO) throws Exception;
	//여행 피시버전 메인에 2개만 보여줌
	public List<BoardVO> river_angling(BoardVO boardVO) throws Exception;
	
	//핫이슈 모바일 버전 메인에 2개만 보여줌
	public List<BoardVO> sea_anglingm(BoardVO boardVO) throws Exception;
    //핫이슈 피시버전 메인에 4개 보여줌
	public List<BoardVO> sea_angling(BoardVO boardVO) throws Exception;
	
	public List<NaksinuriVO> choo_job(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> boatfishing_job(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> riverfishing_job(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> seafishing_job(NaksinuriVO naksinuriVO) throws Exception;
	
	public void co_delete(BoardVO boardVO) throws Exception;
	
	public BoardVO copass_find(BoardVO boardVO) throws Exception;
	
	public BoardVO bopass_find(BoardVO boardVO) throws Exception;
	
	
//	공유게시판 사용자 파일삭제
	public void user_file_delete(NaksinuriOriginalFileVO fileVO) throws Exception;
	
	public List<BoardVO> select_lab_list(BoardVO boardVO) throws Exception;
	
	public void insert_survey_data(SurveyVO surveyVO) throws Exception;
	
	public void insert_survey_quest(SurveyVO surveyVO) throws Exception;
	
	public void insert_survey_quest_item(SurveyVO surveyVO) throws Exception;
	
	public void survey_insert_answer_info(SurveyVO surveyVO) throws Exception;
	
	public void survey_insert_answer(SurveyVO surveyVO) throws Exception;
	
	//낚시대회 제목과 시작일 종료일 날짜 조회
	public List<BoardVO> date_list(BoardVO boardVO) throws Exception;
	//낚시대회 조건검색추가
	public List<BoardVO> date_list_condition(BoardVO boardVO) throws Exception;
	//상단 최신 대회게시글 리스트에 출력
	public List<BoardVO> latest_list(BoardVO boardVO) throws Exception;
	//대회
	public List<BoardVO> congress_list(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> congress_endlist(BoardVO boardVO) throws Exception;
	
	//관리자 낚시업정보 선상낚시 최신순 리스트 정렬
	public List<NaksinuriVO> getListNaksinuri_latest(NaksinuriVO naksinuriVO);
	//관리자 낚시업정보 선상낚시 한글순으로 리스트 정렬
	public List<NaksinuriVO> getListNaksinuri_Kor(NaksinuriVO naksinuriVO);
	
	public BoardVO getbo_sido(BoardVO boardVO) throws Exception;
	
	public BoardVO getbo_gugun(BoardVO boardVO) throws Exception;
	
	
	
	
//	관리자 boardVO와 연결되어있는 게시판들 리스트
	public List<BoardVO> trashBoard_list(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> trashNotice_list(BoardVO boardVO) throws Exception;
	
	
	//관리자 선상낚시 휴지통 리스트들
	public List<NaksinuriVO> trashBoatfishing_list(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> trashBoatlatest_list(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> trashBoatKor_list(NaksinuriVO naksinuriVO) throws Exception;
	
	
	
	//관리자 일반게시판에서 휴지통으로 이동 시킬때쓰는 서비스
	public void gotrash_boardlist(BoardVO boardVO) throws Exception;
	
	public void gotrashfishing_list(NaksinuriVO naksinuriVO) throws Exception;
	
	public void gotrash_survey(SurveyVO surveyVO) throws Exception;
	
	public List<BoardVO> congress_trash(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> congress_endtrash(BoardVO boardVO) throws Exception;
	
	public List<SurveyVO> end_survey_select_trash(SurveyVO surveyVO) throws Exception;
	
	public List<SurveyVO> survey_select_trash(SurveyVO surveyVO) throws Exception;
	
	public void restore_survey(SurveyVO surveyVO) throws Exception;
	
	public void delete_survey(SurveyVO surveyVO) throws Exception;
	
	public void restore_boardlist(BoardVO boardVO) throws Exception;
	
	public void restore_list(NaksinuriVO naksinuriVO) throws Exception;
	
	
	// 캠페인 글 리스트
    public List<BoardVO> campaign_list(BoardVO boardVO) throws Exception;
    // 종료 캠페인 글 리스트
    public List<BoardVO> campaign_endlist(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> campaignTrash(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> campaignendTrash(BoardVO boardVO) throws Exception;
	
	public NaksinuriVO select_next(NaksinuriVO naksinuriVO) throws Exception;
	
	public NaksinuriVO select_prev(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<SurveyVO> get_Excelitem(SurveyVO surveyVO) throws Exception;
	
	public List<SurveyVO> survey_answerole_list2(SurveyVO surveyVO) throws Exception;
	
	public List<NaksinuriFileVO> admin_searchFile1(NaksinuriFileVO naksinurifileVO) throws Exception;
	public List<NaksinuriFileVO> adminPreview_searchFile1(NaksinuriFileVO naksinurifileVO) throws Exception;


	public NaksinuriFileVO admin_mimg2(NaksinuriFileVO naksinurifileVO);
	
	public void up_hit(NaksinuriFileVO naksinurifileVO) throws Exception;
	
	public void congress_insert_data(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> user_congress_list(BoardVO boardVO) throws Exception;
	
	public void congressmbr_insert_data(NaksiCongressMbrVO paramNaksiCongressMbrVO) throws Exception;
	
	public List<BoardVO> registered_congress_list(BoardVO paramBoardVO) throws Exception;
	
	public List<NaksiCongressMbrVO> getmbr_list(NaksiCongressMbrVO paramNaksiCongressMbrVO) throws Exception;
	
	public void update_mbr_status(NaksiCongressMbrVO paramNaksiCongressMbrVO) throws Exception;
	
	public List<BoardVO> select_endcongress(BoardVO paramBoardVO) throws Exception;
	
	public void delete_mbrinfo(NaksiCongressMbrVO paramNaksiCongressMbrVO) throws Exception;
	
	public List<NaksiCongressMbrVO> participated_congress_list(NaksiCongressMbrVO paramNaksiCongressMbrVO) throws Exception;
	
	public NaksiCongressMbrVO mbr_detail(NaksiCongressMbrVO paramNaksiCongressMbrVO) throws Exception;
	
	public NaksinuriAdminVO adminset_findCorp(NaksinuriAdminVO paramNaksinuriAdminVO) throws Exception;
	
	public void adminset_update(NaksinuriAdminVO paramNaksinuriAdminVO) throws Exception;
	
	public BoardVO congress_findCorp(BoardVO paramBoardVO) throws Exception;
	
	public void congressOwn_insert_data(NaksiCongressOwnVO congressOwnVO) throws Exception;
	
	public NaksiCongressOwnVO own_detail(NaksiCongressOwnVO congressOwnVO) throws Exception;
	
	public List<NaksiCongressMbrVO> getmbr_all_list(NaksiCongressMbrVO congressVO) throws Exception;
	
	public List<NaksiCongressOwnVO> getmbr_own_all_list(NaksiCongressOwnVO congressOwnVO) throws Exception;
	
	
	//공모전 리스트
	public List<BoardVO> gongmo_list(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> gongmo_endlist(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> gongmo_trash(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> gongmo_endtrash(BoardVO boardVO) throws Exception;

	public List<BoardVO> gongmo_all_list(BoardVO boardVO) throws Exception;

	public List<NaksiCongressMbrVO> mbr_confirm(NaksiCongressMbrVO congressVO);

	public void congressmbr_update_data(NaksiCongressMbrVO congressmbrVO);


	
	public List getListNaksinuri_admin_preview_fish_list(NaksinuriVO naksinuriVO) throws Exception;
	public NaksinuriVO getListNaksinuri_admin_preview_fish_detail(NaksinuriVO naksinuriVO) throws Exception;
	
	public List getListNaksinuri_admin_preview_ind_list(NaksinuriSanupVO naksinuriSanupVO) throws Exception;
	public NaksinuriSanupVO getListNaksinuri_admin_preview_ind_detail(NaksinuriSanupVO naksinuriSanupVO) throws Exception;
	public NaksinuriSanupVO getListNaksinuri_admin_ind_detail(NaksinuriSanupVO naksinuriSanupVO) throws Exception;

	
	public void fishInsertPreview(NaksinuriFileVO naksinuriFileVO) throws Exception; 
	public void deletefishDataPreview(NaksinuriVO naksinuriVO) throws Exception; 
	public void deletefishFilePreview(NaksinuriVO naksinuriVO) throws Exception;

	public void indInsertPreview(NaksinuriSanupVO SanupVO) throws Exception; 
	public void deleteindDataPreview(NaksinuriSanupVO SanupVO) throws Exception; 
	public void deleteindFilePreview(NaksinuriSanupVO SanupVO) throws Exception;

	public void  indPreviewSave(NaksinuriSanupVO SanupVO)throws Exception;
	public void  indPreviewSaveI(NaksinuriSanupVO SanupVO)throws Exception;
	
	public void  indfileOrgSave(NaksinuriSanupVO SanupVO)throws Exception;
	public void  indfileOrgdelete(NaksinuriSanupVO SanupVO)throws Exception;
	
	public void  fishPreviewSave(NaksinuriVO naksinuriVO)throws Exception;
	public void  fishPreviewSaveI(NaksinuriVO naksinuriVO)throws Exception;
	
	public void  fishfileOrgdelete(NaksinuriVO naksinuriVO)throws Exception;

	public void delindpreviewfile(NaksinuriSanupVO SanupVO) throws Exception;
	public void delindpreviewData(NaksinuriSanupVO delpreview) throws Exception;
	
	public void deleteindPreview(NaksinuriSanupVO delpreview) throws Exception;
	

	public void delfishpreviewfile(NaksinuriVO naksinuriVO) throws Exception;
	public void delfishpreviewData(NaksinuriVO naksinuriVO) throws Exception;

	public List Preview_searchFile(NaksinuriVO naksinuriVO)throws Exception;
	public List Preview_searchFile2(NaksinuriSanupVO SanupVO) throws Exception;

	public NaksinuriSanupVO adminPreview_mimg(NaksinuriSanupVO SanupVO) throws Exception;

	public NaksinuriVO getListNaksinuri_admin_fish_detail(NaksinuriVO naksinuriVO) throws Exception;
	public NaksinuriVO adminPreview_fish_mimg(NaksinuriVO naksinuriVO) throws Exception;

	public void fishfileOrgSave(NaksinuriFileVO naksinuriFileVO);

	public void delpreview_fish_data(NaksinuriVO naksinuriVO)throws Exception;
	public void delpreview_fish_file(NaksinuriVO naksinuriVO)throws Exception;

	public NaksinuriVO preview_fish_list(NaksinuriVO getdata)throws Exception;

	public List<NaksinuriVO> previewlist(NaksinuriVO naksuniriVO) throws Exception;

	//메인화면 낚시대회 불러오기
	public List<BoardVO> main_naksi_congress(BoardVO boardVO) throws Exception;
	
	public List<NaksinuriPolicyVO> select_voc_list(NaksinuriPolicyVO policyVO) throws Exception;
	public NaksinuriPolicyVO select_voc_view(NaksinuriPolicyVO policyVO)throws Exception;

	public NaksinuriFileVO getInfoFishCompany(NaksinuriFileVO naksinuriFileVO);

	public NaksinuriSanupVO getInfoIndustry(NaksinuriSanupVO naksinuriSanupVO);

	public List<NaksinuriVO> get_yr_fshtb_sttus() throws Exception;

	public List<NaksinuriVO> get_yr_fshtb_user_sttus() throws Exception;
	
	public List<NaksinuriVO> get_area_fshtb_sttus(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> get_each_area_fshtb_sttus(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> get_fshtb_year_list() throws Exception;

	public List<NaksinuriVO> get_tot_area_fshtb_list() throws Exception;
	
	public List<NaksinuriVO> get_fshtb_user_year_list() throws Exception;
	
	public List<NaksinuriVO> get_sido_fshtb_before_year_sttus(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> get_each_area_fshtb_user_sttus(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> get_sido_fshtb_before_year_list(NaksinuriVO naksinuriVO) throws Exception;
	
	public void insert_ocean_fsh_idex(NaksinuriVO naksinuriVO) throws Exception;

	public String get_ocean_fsh_idex_reg_date() throws Exception;
	
	public void delete_ocean_fsh_idex(NaksinuriVO naksinuriVO) throws Exception;
	
	public NaksinuriVO getInfoOceanFshIdex(NaksinuriVO naksinuriVO) throws Exception;
	
	public List<NaksinuriVO> get_ocean_fsh_idex_area_list(NaksinuriVO naksinuriVO) throws Exception;
} 


