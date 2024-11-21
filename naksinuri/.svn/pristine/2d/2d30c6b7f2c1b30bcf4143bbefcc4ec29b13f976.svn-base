package egovframework.naksinuri_original.let.naksinuri.service.impl;


import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksiCongressMbrVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksiCongressOwnVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAdminVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriLogsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("NaksinuriDAO")
public class NaksinuriDAO extends EgovAbstractDAO{
	
	
	
	/**
     * 개인정보보호정책를(을) 등록한다.
     * @param naksinuriVO  개인정보보호정책 정보가 담김 VO
     * @throws Exception
     */
	public String insertNaksinuriInfo(NaksinuriVO naksinuriVO) throws Exception{
		return (String)insert("NaksinuriDAO.insertNaksinuriInfo_S",naksinuriVO); 
	}
	public String insertNaksinuriPreviewInfo(NaksinuriVO naksinuriVO) throws Exception{
		return (String)insert("NaksinuriDAO.insertNaksinuriPreviewInfo_S",naksinuriVO); 
	}	
	public String insertNaksinuriPreviewInfoP(NaksinuriVO naksinuriVO) throws Exception{
		return (String)insert("NaksinuriDAO.insertNaksinuriPreviewInfo_SP",naksinuriVO); 
	}	
	public String insertNaksinuriSanupPreviewInfo(NaksinuriSanupVO naksinuriSanupVO) throws Exception{
		return (String)insert("NaksinuriDAO.insertNaksinuriSanupPreviewInfo_S",naksinuriSanupVO); 
	}	

	/**
     * 기 등록된 사용자 중 검색조건에 맞는일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param co_nm,ceo_nm 로그인페이지에서 받아올 회사명, 대표자이름
     * @return NaksinuriVO 일반회원 상세정보
     */
    public NaksinuriVO selectNaksinuriInfo(NaksinuriVO naksinuriVO){
        return (NaksinuriVO) select("NaksinuriDAO.selectInfo_S", naksinuriVO);
    }
    
    public NaksinuriSanupVO selectNaksinuriSanupInfo(NaksinuriSanupVO naksinuriSanupVO){
        return (NaksinuriSanupVO) select("NaksinuriDAO.selectInfoSanup_S", naksinuriSanupVO);
    }
 
    /**
     * 화면에 조회된일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param naksinuriVO 낚시터 정보
     */
    
    public void updateInfo(NaksinuriVO naksinuriVO) throws Exception{
        update("NaksinuriDAO.updateInfo_S",naksinuriVO);
    }
    
    public NaksinuriVO searchCo_nm(NaksinuriVO naksinuriVO) throws Exception{
    	
    	return (NaksinuriVO) select("NaksinuriDAO.selectInfo_S",naksinuriVO);
    }
    
    public NaksinuriFileVO mimg(NaksinuriFileVO naksinurifileVO) throws Exception{
    	return (NaksinuriFileVO) select ("NaksinuriDAO.listFile_M",naksinurifileVO);
    }
    
    
    public List<NaksinuriFileVO> searchFile(NaksinuriFileVO naksinurifileVO) throws Exception{
    	
    	return (List<NaksinuriFileVO>) list("NaksinuriDAO.listFile_S",naksinurifileVO);
    }
    
    public NaksinuriFileVO admin_mimg(NaksinuriFileVO naksinurifileVO) throws Exception{
    	
    	return (NaksinuriFileVO) select ("NaksinuriDAO.listFile_admin_M",naksinurifileVO);
    }
    public NaksinuriFileVO adminPreview_mimg(NaksinuriFileVO naksinurifileVO) throws Exception{
    	
    	return (NaksinuriFileVO) select ("NaksinuriDAO.listFile_adminPreview_M",naksinurifileVO);
    }
        
    public List<NaksinuriFileVO> admin_searchFile(NaksinuriFileVO naksinurifileVO) throws Exception{
    	
    	return (List<NaksinuriFileVO>) list("NaksinuriDAO.listFile_admin_S",naksinurifileVO);
    }
    
    
    public NaksinuriVO selectNaksinuri_admin(NaksinuriVO naksinuriVO){
        return (NaksinuriVO) select("corpInfo2", naksinuriVO);

    }  
    public List<NaksinuriVO> getListNaksinuri_admin(NaksinuriVO naksinuriVO){
        return (List<NaksinuriVO>) list("corpInfo3", naksinuriVO);  
    }
 	/**
     * 기 등록된 사용자 중 검색조건에 맞는일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param co_nm,ceo_nm 로그인페이지에서 받아올 회사명, 대표자이름
     * @return NaksinuriVO 일반회원 상세정보
     */
    public NaksinuriVO selectNaksinuriInfo_admin(NaksinuriVO naksinuriVO){
        return (NaksinuriVO) select("NaksinuriDAO.selectInfo_admin", naksinuriVO);
    }   
    public NaksinuriVO selectNaksinuriInfoPreview_admin(NaksinuriVO naksinuriVO){
        return (NaksinuriVO) select("NaksinuriDAO.selectInfoPreview_admin", naksinuriVO);
    }   
    
    public List<NaksinuriFileVO> getListfishjob(NaksinuriFileVO naksinuriFileVO){
        return (List<NaksinuriFileVO>) list("Fishjob_list", naksinuriFileVO); 
    }
    public List<NaksinuriFileVO> getListFishCompany(NaksinuriFileVO naksinuriFileVO){
    	return (List<NaksinuriFileVO>) list("Fishcompany_list", naksinuriFileVO); 
    }
    public List<NaksinuriVO> getListOceanFshIdex(NaksinuriVO naksinuriVO){
    	return (List<NaksinuriVO>) list("OceanFshIdex_list", naksinuriVO); 
    }
    public List<NaksinuriFileVO> getMapListFishCompany(NaksinuriFileVO naksinuriFileVO){
    	return (List<NaksinuriFileVO>) list("Fishcompany_mapList", naksinuriFileVO); 
    }
    




	public void delete_list(NaksinuriVO naksinuriVO) {
		
		delete("delete_list",naksinuriVO);
		 
	}
    
	public String insert_data(BoardVO boardVO) {
		
		return (String)insert("insert_data",boardVO);
		 
	}
	
	// 게시판 글 리스트
    public List<BoardVO> select_list(BoardVO boardVO) throws Exception{
    	return (List<BoardVO>) list("select_list",boardVO);
    }
    // 게시판 글 리스트 - 승인제를 위한 관리자용
    public List<BoardVO> select_list_admin(BoardVO boardVO) throws Exception{
    	return (List<BoardVO>) list("select_list_admin",boardVO);
    }
    //공지사항 마크 달린 리스트
    public List<BoardVO> noticemark_list(BoardVO boardVO) throws Exception{
    	return (List<BoardVO>) list("noticemark_list",boardVO);
    }
    
    public BoardVO bmimg(BoardVO boardVO){
    	return (BoardVO) select("bmimg",boardVO);
    }
    
    public BoardVO bsimg(BoardVO boardVO){
    	return (BoardVO) select("bsimg",boardVO);
    }
    
    public List<BoardVO> ba_file(BoardVO boardVO){
    	return (List<BoardVO>) list("ba_file",boardVO);
    }
    
    public BoardVO movfile(BoardVO boardVO){
    	return (BoardVO) select("movfile",boardVO);
    }
    
    // PDF이미지 슬라이드 파일
    public List<BoardVO> pdfimgfile(BoardVO boardVO){
    	return (List<BoardVO>) list("pdfimgfile",boardVO);
    }
    
    
	public void delete_boardlist(BoardVO boardVO) {
		
		delete("delete_boardlist",boardVO);
		 
	}
    public BoardVO board_findCorp(BoardVO boardVO){
        return (BoardVO) select("board_findCorp", boardVO);
    }
    
	public BoardVO phone1_findCorp(BoardVO boardVO) {
		return (BoardVO) select("phone1_findCorp", boardVO);
	}

	public BoardVO phone2_findCorp(BoardVO boardVO) {
		return (BoardVO) select("phone2_findCorp", boardVO);
	}

	public BoardVO phone3_findCorp(BoardVO boardVO) {
		return (BoardVO) select("phone3_findCorp", boardVO);
	}

	public BoardVO email1_findCorp(BoardVO boardVO) {
		return (BoardVO) select("email1_findCorp", boardVO);
	}

	public BoardVO email2_findCorp(BoardVO boardVO) {
		return (BoardVO) select("email2_findCorp", boardVO);
	}
	public void update_data(BoardVO boardVO) {
		
		update("update_data",boardVO);
		 
	}
	public void view_update(BoardVO boardVO) {
		
		update("view_update",boardVO);
		 
	}
	
	public void like_update(BoardVO boardVO) {
		update("like_update",boardVO);
		
	}
	
	// 낚시누리 자주묻는 질문 리스트
	 public List<NaksinuriZisikinVO> getListZazu(NaksinuriZisikinVO naksinuriZisikinVO){
	        return (List<NaksinuriZisikinVO>) list("zazu_list", naksinuriZisikinVO); 
	    }
	 // 자주 묻는 질문 추가하기
	 public String insertZazu(NaksinuriZisikinVO naksinuriZisikinVO){
		 return (String) insert("insertZazu", naksinuriZisikinVO);
	 }
	 
	 // 자주 묻는 질문 수정하기
	 public String updateZazu(NaksinuriZisikinVO naksinuriZisikinVO){
		 return (String) insert("updateZazu", naksinuriZisikinVO);
	 }
	 //자주 묻는 질문 삭제하기
	 public void deleteZazu(NaksinuriZisikinVO naksinuriZisikinVO){
		 delete("deleteZazu",naksinuriZisikinVO);
	 }
	 
	 public void co_insert(BoardVO boardVO) {
		 
		 insert("co_insert",boardVO);
		 
	 }
	 public List<BoardVO> select_list_comment(BoardVO boardVO) throws Exception{
		 return (List<BoardVO>) list("select_list_comment",boardVO);
	 }
	 public List<BoardVO> select_list_post(BoardVO boardVO) throws Exception{
		 return (List<BoardVO>) list("select_list_post",boardVO);
	 }
	 
	 public List<BoardVO> select_list_bobo(BoardVO boardVO) throws Exception{
		 return (List<BoardVO>) list("select_list_bobo",boardVO);
	 }
	 
	 public BoardVO select_next(BoardVO boardVO){
		 return (BoardVO) select("select_next", boardVO);
	 } 
	 public BoardVO select_prev(BoardVO boardVO){
		 return (BoardVO) select("select_prev", boardVO);
	 } 

	public NaksinuriZisikinVO nuri_find(NaksinuriZisikinVO naksinuriZisikinVO) {
		 return (NaksinuriZisikinVO) select("nuri_find", naksinuriZisikinVO);
		
	}
	
	// 낚시누리 지식인 리스트
	public List<NaksinuriZisikinVO> getListZisik(NaksinuriZisikinVO naksinuriZisikinVO){
		return (List<NaksinuriZisikinVO>) list("zisik_list", naksinuriZisikinVO); 
	}
	

	//관리자 누리지식인 휴지통 리스트
	public List<NaksinuriZisikinVO> getTrashZisik(NaksinuriZisikinVO naksinuriZisikinVO) {
		return (List<NaksinuriZisikinVO>) list("zisik_trash",naksinuriZisikinVO);
	}

	//조회수증가
	public void zisik_view_count(NaksinuriZisikinVO naksinuriZisikinVO) {
		update("zisik_view_count", naksinuriZisikinVO);
	}
	
	public NaksinuriZisikinVO zisik_find(NaksinuriZisikinVO naksinuriZisikinVO) {
		 return (NaksinuriZisikinVO) select("zisik_find", naksinuriZisikinVO);
	}
	
	//누리지식인 이전글, 다음글 가져오기
	 public NaksinuriZisikinVO zisik_next(NaksinuriZisikinVO naksinurizisikinVO){
		 return (NaksinuriZisikinVO) select("zisik_next", naksinurizisikinVO);
	 } 
	 public NaksinuriZisikinVO zisik_prev(NaksinuriZisikinVO naksinuriZisikinVO){
		 return (NaksinuriZisikinVO) select("zisik_prev", naksinuriZisikinVO);
	 } 
	 
	 // 지식인 질문하기
	 public String insert_zisik(NaksinuriZisikinVO naksinuriZisikinVO){
		 return (String) insert("insert_zisik", naksinuriZisikinVO);
	 }
	 
	 // 자주 묻는 질문 수정하기
	 public String update_zisik(NaksinuriZisikinVO naksinuriZisikinVO){
		 return (String) insert("update_zisik", naksinuriZisikinVO);
	 }
	 //자주 묻는 질문 삭제하기
	 public void delete_zisik(NaksinuriZisikinVO naksinuriZisikinVO){
		 delete("delete_zisik",naksinuriZisikinVO);
	 }
	 //지식인 글 ㅂㅁ가져오기
	 public NaksinuriZisikinVO zpass_find(NaksinuriZisikinVO naksinuriZisikinVO){
		 return (NaksinuriZisikinVO) select ("zpass_find", naksinuriZisikinVO);
	 }
	 
	 // 지식인 글에 맞는 답변 리스트
	 public List<NaksinuriZisikinVO> getListAnswer(NaksinuriZisikinVO naksinuriZisikinVO){
			return (List<NaksinuriZisikinVO>) list("answer_list", naksinuriZisikinVO);
	 }
	 // 답변 내용가져오기
	 public NaksinuriZisikinVO ans_find(NaksinuriZisikinVO naksinuriZisikinVO) {
		 return (NaksinuriZisikinVO) select("ans_find", naksinuriZisikinVO);
	}
	 
	 // 지식인 답변하기
	 public String insert_ans(NaksinuriZisikinVO naksinuriZisikinVO){
		 return (String) insert("insert_ans", naksinuriZisikinVO);
	 }
	 
	 // 자주 묻는 질문 수정하기
	 public String update_ans(NaksinuriZisikinVO naksinuriZisikinVO){
		 return (String) insert("update_ans", naksinuriZisikinVO);
	 }
	 //자주 묻는 질문 삭제하기
	 public void delete_ans(NaksinuriZisikinVO naksinuriZisikinVO){
		 delete("delete_ans",naksinuriZisikinVO);
	 }
	 
	
	//낚시누리 메인화면 뉴스와 공지사항 리스트,낚시조황 소식
	public List<BoardVO> select_main_news(BoardVO boardVO){
		return (List<BoardVO>) list("main_news",boardVO);
	}
	
	public List<BoardVO> select_main_notice(BoardVO boardVO){
		return (List<BoardVO>) list("main_notice",boardVO);
	}
	
	public List<BoardVO> river_anglingm(BoardVO boardVO) {
		return (List<BoardVO>) list("river_anglingm",boardVO);
	}

	public List<BoardVO> sea_anglingm(BoardVO boardVO) {
		return (List<BoardVO>) list("sea_anglingm",boardVO);
	}
	
	public List<BoardVO> river_angling(BoardVO boardVO){
		return (List<BoardVO>) list("river_angling",boardVO);
	}
	
	public List<BoardVO> sea_angling(BoardVO boardVO){
		return (List<BoardVO>) list("sea_angling",boardVO);
	}
	
	public List<NaksinuriVO> choo_job(NaksinuriVO naksinuriVO){
		return (List<NaksinuriVO>) list("choo_job",naksinuriVO);
	}

	public List<NaksinuriVO> boatfishing_job(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("boatfishing_job",naksinuriVO);
	}

	public List<NaksinuriVO> riverfishing_job(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("riverfishing_job",naksinuriVO);
	}

	public List<NaksinuriVO> seafishing_job(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("seafishing_job",naksinuriVO);
	}

	
	public List<NaksinuriGoneVO> getListGone(NaksinuriGoneVO GoneVO) throws Exception{
    	return (List<NaksinuriGoneVO>) list("getListGone",GoneVO);
    }
	
	public List<NaksinuriGoneVO> getListGonemobile(NaksinuriGoneVO GoneVO) throws Exception{
    	return (List<NaksinuriGoneVO>) list("getListGonemobile",GoneVO);
    }
	
	public String insert_data_gone(NaksinuriGoneVO GoneVO) {
		
		return (String)insert("insert_data_gone",GoneVO);
		 
	}
	
    public NaksinuriGoneVO promotion_findCorp(NaksinuriGoneVO GoneVO){
        return (NaksinuriGoneVO) select("promotion_findCorp", GoneVO);
    } 
	public void update_data_gone(NaksinuriGoneVO GoneVO) {
		
		update("update_data_gone",GoneVO);
		 
	}
	public void delete_list(NaksinuriGoneVO GoneVO) {
		
		delete("delete_list_p",GoneVO);
		 
	}
	public List<NaksinuriGoneVO> getListGone_admin(NaksinuriGoneVO GoneVO) throws Exception{
    	return (List<NaksinuriGoneVO>) list("getListGone_admin",GoneVO);
    }
	public List<NaksinuriSanupVO> getListIndustry(NaksinuriSanupVO SanupVO) throws Exception{
    	return (List<NaksinuriSanupVO>) list("getListIndustry",SanupVO);
    }
	public List<NaksinuriSanupVO> getMapListIndustry(NaksinuriSanupVO SanupVO) throws Exception{
		return (List<NaksinuriSanupVO>) list("getMapListIndustry",SanupVO);
	}
	public String ind_insert(NaksinuriSanupVO SanupVO) {
		return (String)insert("ind_insert",SanupVO);
	}
	public String ind_insert_Preview(NaksinuriSanupVO SanupVO) {
		return (String)insert("ind_insert_Preview",SanupVO);
	}
	public String ind_insert_PreviewP(NaksinuriSanupVO SanupVO) {
		return (String)insert("ind_insert_PreviewP",SanupVO);
	}
    public NaksinuriSanupVO ind_find(NaksinuriSanupVO SanupVO){
        return (NaksinuriSanupVO) select("ind_find", SanupVO);
    }
    public NaksinuriSanupVO ind_find_Preview(NaksinuriSanupVO SanupVO){
        return (NaksinuriSanupVO) select("ind_find_Preview", SanupVO);
    }
    
    public List<NaksinuriSanupVO> ind_fsimg(NaksinuriSanupVO sanupVO) throws Exception{
    	return (List<NaksinuriSanupVO>) list("ind_fsimg", sanupVO);
    }
    public List<NaksinuriSanupVO> ind_fsimg_Preview(NaksinuriSanupVO sanupVO) throws Exception{
    	return (List<NaksinuriSanupVO>) list("ind_fsimg_Preview", sanupVO);
    }
    
    public String ind_update(NaksinuriSanupVO sanupVO){
    	return (String)insert("ind_update", sanupVO);
    }
    

	public void insert_lab_data(BoardVO boardVO){
		insert("lab_insert_data",boardVO);
		
	}
	public void insert_survey_data(SurveyVO surveyVO) {
		insert("survey_insert_data",surveyVO);		
	}
	
	public void insert_survey_quest(SurveyVO surveyVO) {
		insert("survey_insert_quest",surveyVO);		
	}
	
	public void insert_survey_quest_item(SurveyVO surveyVO) {
		insert("survey_insert_quest_item",surveyVO);		
	}
	
	public void survey_insert_answer_info(SurveyVO surveyVO) {
		insert("survey_insert_answer_info",surveyVO);		
	}
	
	public void survey_insert_answer(SurveyVO surveyVO) {
		insert("survey_insert_answer",surveyVO);		
	}
    
	public void ind_delete(NaksinuriSanupVO sanupVO){
		delete("ind_delete",sanupVO);
	}
	
	public void fishInsertPreview(NaksinuriFileVO naksinuriFileVO) {
		insert("fishInsertPreview",naksinuriFileVO); 	
	}
	public void deletefishDataPreview(NaksinuriVO naksinuriVO) {
		delete("deletefishDataPreview",naksinuriVO);	
	}
	public void deletefishFilePreview(NaksinuriVO naksinuriVO) {
		delete("deletefishFilePreview",naksinuriVO);	
	}
	public void indInsertPreview(NaksinuriSanupVO SanupVO) {
		insert("indInsertPreview",SanupVO);	
	}
	public void deleteindDataPreview(NaksinuriSanupVO SanupVO) {
		delete("deleteindDataPreview",SanupVO);	
	}
	public void deleteindFilePreview(NaksinuriSanupVO SanupVO) {
		delete("deleteindFilePreview",SanupVO);	
	}
	
	
	//댓글 삭제
	public void co_delete(BoardVO boardVO) {
		delete("co_delete",boardVO);
	}
	public BoardVO copass_find(BoardVO boardVO){
		return (BoardVO) select ("copass_find", boardVO);
	}
	

	public BoardVO bopass_find(BoardVO boardVO)throws Exception {
		return (BoardVO) select ("bopass_find",boardVO);
	}

	public void user_file_delete(NaksinuriOriginalFileVO fileVO)throws Exception {
		delete("user_file_delete",fileVO);
	}

	public List<SurveyVO> survey_select_list(SurveyVO surveyVO) throws Exception {
		return (List<SurveyVO>) list("survey_select_list",surveyVO);
	}

	public List<SurveyVO> end_survey_select_list(SurveyVO surveyVO) throws Exception{
		return (List<SurveyVO>) list("end_survey_select_list",surveyVO);
	}

	public List<BoardVO> select_lab_list(BoardVO boardVO) throws Exception {
		return (List<BoardVO>) list("select_lab_list",boardVO);
	}

	public List<SurveyVO> survey_select_list_user(SurveyVO surveyVO) {
		return (List<SurveyVO>) list("survey_select_list_user",surveyVO);
	}

	public List<SurveyVO> end_survey_select_list_user(SurveyVO surveyVO) {
		return (List<SurveyVO>) list("end_survey_select_list_user",surveyVO);
	}
	public SurveyVO select_survey(SurveyVO surveyVO){
		return (SurveyVO) select("survey_find",surveyVO);
	}
	public SurveyVO check_survey_by_ip(SurveyVO surveyVO){
		return (SurveyVO) select("check_survey_by_ip",surveyVO);
	}
	
	
	public List<SurveyVO> select_surveyq_list(SurveyVO surveyVO){
		return (List<SurveyVO>) list ("survey_qfind",surveyVO);
	}
	
	public List<SurveyVO> select_surveyqi_list(SurveyVO surveyVO){
		return (List<SurveyVO>) list("survey_qifind",surveyVO);
	}
	public List<SurveyVO> survey_anscnt (SurveyVO surveyVO){
		return (List<SurveyVO>) list("survey_anscnt",surveyVO);
	}
	
	public List<SurveyVO> survey_answerole(SurveyVO surveyVO){		
		return (List<SurveyVO>) list("survey_answerole",surveyVO);
	}
	
	
	public SurveyVO select_survey_subject(SurveyVO surveyVO){
		return (SurveyVO) select("survey_find_subject",surveyVO);
	}
	
	public SurveyVO select_surveyq_subject(SurveyVO surveyVO){
		return (SurveyVO) select("survey_qfind_subject",surveyVO);
	}
	
	public SurveyVO survey_answfind(SurveyVO surveyVO){
		return (SurveyVO) select("survey_answfind",surveyVO);
		
	}
	
	

//	낚시대회 시작일 종료일 full calendar 에 출력하기 위한 쿼리
	public List<BoardVO> date_list(BoardVO boardVO) {
		return (List<BoardVO>) list("date_list",boardVO);
	}
	
	public List<BoardVO> date_list_condition(BoardVO boardVO) {
		return (List<BoardVO>) list("date_list_condition",boardVO);
	}

	public List<BoardVO> latest_list(BoardVO boardVO) {
		return (List<BoardVO>) list("latest_list",boardVO);
	}

	public List<BoardVO> congress_list(BoardVO boardVO) {
		return (List<BoardVO>) list("congress_list",boardVO);
	}

	public List<BoardVO> congress_endlist(BoardVO boardVO) {
		return (List<BoardVO>) list("congress_endlist",boardVO);
	}

	public List<NaksinuriVO> getListNaksinuri_latest(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("fishjoblist_latest",naksinuriVO);
	}

	public List<NaksinuriVO> getListNaksinuri_Kor(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("fishjoblist_Kor",naksinuriVO);
	}

	public BoardVO getbo_sido(BoardVO boardVO) {
		return (BoardVO) select ("getbo_sido",boardVO);
	}

	public BoardVO getbo_gugun(BoardVO boardVO) {
		return (BoardVO) select ("getbo_gugun",boardVO);
	}

	public List<BoardVO> trashBoard_list(BoardVO boardVO) {
		return (List<BoardVO>) list ("trashBoard_list",boardVO);
	}

	public List<BoardVO> trashNotice_list(BoardVO boardVO) {
		return (List<BoardVO>) list ("trashNotice_list",boardVO);
	}

	
//	낚시업 정렬 
	public List<NaksinuriVO> trashBoatfishing_list(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("trashBoatfishing_list",naksinuriVO);
	}

	public List<NaksinuriVO> trashBoatlatest_list(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("trashBoatlatest_list",naksinuriVO);
	}

	public List<NaksinuriVO> trashBoatKor_list(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("trashBoatKor_list",naksinuriVO);
	}


	
//	일반게시판에서 휴지통으로 이동
	public void gotrashfishing_list(NaksinuriVO naksinuriVO) {
		update("gotrash_fishinglist",naksinuriVO);
		
	}

	public void gotrash_boardlist(BoardVO boardVO) {
	     update("gotrash_boardlist",boardVO);
	}

	public void gotrash_indlist(NaksinuriSanupVO sanupVO) {
		update("gotrash_indlist",sanupVO);
		
	}

	public void gotrash_zisik(NaksinuriZisikinVO zisikinVO) {
		update("gotrash_zisik",zisikinVO);
		
	}
	
	public void gotrashZazu(NaksinuriZisikinVO naksinuriZisikinVO) {
		update("gotrash_zazu",naksinuriZisikinVO);
		
	}
	public void trash_list(NaksinuriGoneVO goneVO) {
		update("gotrash_plocation",goneVO);
		
	}
	// 설문리스트 휴지통 보내기, 복원하기 
	public void gotrash_survey(SurveyVO surveyVO){
		update("gotrash_survey", surveyVO);
	}
	
	public void delete_survey(SurveyVO surveyVO){
		delete("delete_survey", surveyVO);
	}

	
	
	public void restore_survey(SurveyVO surveyVO){
		update("restore_survey", surveyVO);
	}
	public void restore_boardlist(BoardVO boardVO) {
		update("restore_boardlist",boardVO);
		
	}
	public void restore_list(NaksinuriVO naksinuriVO) {
		update("restore_fishjob",naksinuriVO);
		
	}
	public void ind_restore(NaksinuriSanupVO sanupVO) {
		update("restore_ind",sanupVO);
		
	}
	public void restore_zisik(NaksinuriZisikinVO zisikinVO) {
		update("restore_zisik",zisikinVO);
		
	}
	public void restoreZazu(NaksinuriZisikinVO naksinuriZisikinVO) {
		update("restore_zazu",naksinuriZisikinVO);
		
	}
	public void restore_plocation(NaksinuriGoneVO goneVO) {
		update("restore_plocation",goneVO);
		
	}

	public List<NaksinuriSanupVO> get_trashListIndustry(NaksinuriSanupVO sanupVO) {
		return (List<NaksinuriSanupVO>) list("get_trashListIndustry",sanupVO);
	}

	public List<NaksinuriZisikinVO> getTrashZazu(NaksinuriZisikinVO naksinuriZisikinVO) {
		return (List<NaksinuriZisikinVO>) list("getTrashZazu",naksinuriZisikinVO);
	}

	public List<BoardVO> congress_trash(BoardVO boardVO) {
		return (List<BoardVO>) list("congress_trash",boardVO);
	}

	public List<BoardVO> congress_endtrash(BoardVO boardVO) {
		return(List<BoardVO>) list("congress_endtrash",boardVO);
	}

	public List<NaksinuriGoneVO> getTrashGone_admin(NaksinuriGoneVO goneVO) {
		return (List<NaksinuriGoneVO>) list("getTrashGone",goneVO);
	}

	public List<SurveyVO> end_survey_select_trash(SurveyVO surveyVO) {
		return (List<SurveyVO>) list("endsurvey_trash",surveyVO);
	}

	public List<SurveyVO> survey_select_trash(SurveyVO surveyVO) {
		return (List<SurveyVO>) list ("survey_trash", surveyVO);
	}


	//캠페인 리스트
    public List<BoardVO> campaign_list(BoardVO boardVO) throws Exception{
    	return (List<BoardVO>) list("campaign_list",boardVO);
    }
  //종료 캠페인 리스트
    public List<BoardVO> campaign_endlist(BoardVO boardVO) throws Exception{
    	return (List<BoardVO>) list("campaign_endlist",boardVO);
    }

	public List<BoardVO> campaignTrash(BoardVO boardVO) {
		return (List<BoardVO>) list("campaign_trash",boardVO);
	}

	public List<BoardVO> campaignendTrash(BoardVO boardVO) {
		return (List<BoardVO>) list("campaign_endtrash",boardVO);
	}

	public NaksinuriVO select_next(NaksinuriVO naksinuriVO) {
		return (NaksinuriVO) select("fishjob_next",naksinuriVO);
	}

	public NaksinuriVO select_prev(NaksinuriVO naksinuriVO) {
		return (NaksinuriVO) select("fishjob_prev",naksinuriVO);
	}

	public List<SurveyVO> get_Excelitem(SurveyVO surveyVO) {
		return (List<SurveyVO>) list("survey_get_item",surveyVO);
	}

	public List<SurveyVO> survey_answerole_list2(SurveyVO surveyVO) {
		return (List<SurveyVO>) list ("survey_answerole2",surveyVO);
	}

	public List<NaksinuriFileVO> admin_searchFile1(NaksinuriFileVO naksinurifileVO) {
		return (List<NaksinuriFileVO>) list ("admin_searchFile1",naksinurifileVO);
	}
	public List<NaksinuriFileVO> adminPreview_searchFile1(NaksinuriFileVO naksinurifileVO) {
		return (List<NaksinuriFileVO>) list ("adminPreview_searchFile1",naksinurifileVO);
	}
	public List<NaksinuriVO> Preview_searchFile(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list ("Preview_searchFile",naksinuriVO);
	}

	public NaksinuriFileVO admin_mimg2(NaksinuriFileVO naksinurifileVO) {
		return (NaksinuriFileVO) select ("NaksinuriDAO.listFile_admin_M2",naksinurifileVO);
	}

	public void up_hit(NaksinuriFileVO naksinurifileVO) {
		 update("fishjob_uphit",naksinurifileVO);
	}

	public void industry_uphit(NaksinuriSanupVO sanupVO) {
		update("industry_uphit",sanupVO);
		
	}

	public void congress_insert_data(BoardVO boardVO) {
		insert("congress_insert_data",boardVO);
	}

	public List<BoardVO> user_congress_list(BoardVO boardVO) {
		return (List<BoardVO>) list ("user_congress_list",boardVO);
	}
	



	  public void congressmbr_insert_data(NaksiCongressMbrVO congressmbrVO) {
	    insert("congressmbr_insert_data", congressmbrVO);
	  }

	  public List<BoardVO> registered_congress_search(BoardVO boardVO)
	  {
	    return (List<BoardVO>) list("registered_congress_search", boardVO);
	  }

	  public List<NaksiCongressMbrVO> getmbr_list(NaksiCongressMbrVO congressVO) {
	    return (List<NaksiCongressMbrVO>) list("getmbr_list", congressVO);
	  }

	  public void mbrstatus_update(NaksiCongressMbrVO congressVO) {
	    update("update_mbrstatus", congressVO);
	  }

	  public List<BoardVO> select_endcongress(BoardVO boardvo)
	  {
	    return (List<BoardVO>) list("select_endcongress", boardvo);
	  }

	  public void delete_mbrinfo(NaksiCongressMbrVO congressvo) {
	    delete("delete_mbrinfo", congressvo);
	  }

	  public List<NaksiCongressMbrVO> participated_congress_list(NaksiCongressMbrVO congressVO)
	  {
	    return (List<NaksiCongressMbrVO>) list("participated_congress_list", congressVO);
	  }

	  public NaksiCongressMbrVO mbr_detail(NaksiCongressMbrVO congressVO) {
	    return (NaksiCongressMbrVO)select("mbr_detail", congressVO);
	  }

	  public NaksinuriAdminVO adminset_findCorp(NaksinuriAdminVO adminVO) {
	    return (NaksinuriAdminVO)select("adminset_findCorp", adminVO);
	  }

	  public void adminset_update(NaksinuriAdminVO adminVO) {
	    update("adminset_update", adminVO);
	  }

	  public BoardVO congress_findCorp(BoardVO boardVO) {
	    return (BoardVO)select("congress_findCorp", boardVO);
	  }

	public void congressOwn_insert_data(NaksiCongressOwnVO congressOwnVO) {
		insert("congressOwn_insert_data", congressOwnVO);
	}

	public NaksiCongressOwnVO own_detail(NaksiCongressOwnVO congressOwnVO) {
		return (NaksiCongressOwnVO)select("own_detail", congressOwnVO);
	}

	public List<NaksiCongressMbrVO> getmbr_all_list(NaksiCongressMbrVO congressVO) {
		return (List<NaksiCongressMbrVO>) list("getmbr_all_list", congressVO);
	}
	
	public List<NaksiCongressOwnVO> getmbr_own_all_list(NaksiCongressOwnVO congressOwnVO) {
		return (List<NaksiCongressOwnVO>) list("getmbr_own_all_list", congressOwnVO);
	}



	
	//공모전
	public List<BoardVO> gongmo_list(BoardVO boardVO) {
		return (List<BoardVO>) list("gongmo_list",boardVO);
	}
	
	public List<BoardVO> gongmo_endlist(BoardVO boardVO) {
		return (List<BoardVO>) list("gongmo_endlist",boardVO);
	}
	
	public List<BoardVO> gongmo_trash(BoardVO boardVO) {
		return (List<BoardVO>) list("gongmo_trash",boardVO);
	}

	public List<BoardVO> gongmo_endtrash(BoardVO boardVO) {
		return(List<BoardVO>) list("gongmo_endtrash",boardVO);
	}
	
	public List<BoardVO> gongmo_all_list(BoardVO boardVO) {
		return (List<BoardVO>) list("gongmo_all_list",boardVO);
	}
	public List<NaksiCongressMbrVO> mbr_confirm(NaksiCongressMbrVO congressVO) {
		return (List<NaksiCongressMbrVO>) list("mbr_confirm", congressVO);
	}
	public void congressmbr_update_data(NaksiCongressMbrVO congressmbrVO) {
		update("congressmbr_update_data", congressmbrVO);
	}
	

   public List<NaksinuriVO> getListNaksinuri_admin_preview_fish_list(NaksinuriVO naksinuriVO){
        return (List<NaksinuriVO>) list("getListNaksinuri_admin_preview_fish_list", naksinuriVO);  
    }
   public NaksinuriVO getListNaksinuri_admin_preview_fish_detail(NaksinuriVO naksinuriVO) {
	   return (NaksinuriVO) select("getListNaksinuri_admin_preview_fish_detail", naksinuriVO);  
   }
   public NaksinuriVO getListNaksinuri_admin_fish_detail(NaksinuriVO naksinuriVO) {
	   return (NaksinuriVO) select("getListNaksinuri_admin_fish_detail", naksinuriVO);  
   }
   public List<NaksinuriSanupVO> getListNaksinuri_admin_preview_ind_list(NaksinuriSanupVO naksinuriSanupVO){
       return (List<NaksinuriSanupVO>) list("getListNaksinuri_admin_preview_ind_list", naksinuriSanupVO);  
   }
   public NaksinuriSanupVO getListNaksinuri_admin_ind_detail(NaksinuriSanupVO naksinuriSanupVO) {
	   return (NaksinuriSanupVO) select("getListNaksinuri_admin_ind_detail", naksinuriSanupVO);  
   }
   public NaksinuriSanupVO getListNaksinuri_admin_preview_ind_detail(NaksinuriSanupVO naksinuriSanupVO) {
	   return (NaksinuriSanupVO) select("getListNaksinuri_admin_preview_ind_detail", naksinuriSanupVO);  
   }
   public List<NaksinuriSanupVO> Preview_searchFile2(NaksinuriSanupVO naksinuriSanupVO) {
	   return (List<NaksinuriSanupVO>) list("Preview_searchFile2", naksinuriSanupVO);  
   }

   public NaksinuriSanupVO modify_ind_check(NaksinuriSanupVO naksinuriSanupVO) {
	   return (NaksinuriSanupVO) select("modify_ind_check", naksinuriSanupVO); 
   }
	public NaksinuriVO modify_fish_check(NaksinuriVO naksinuriVO) {
		return (NaksinuriVO) select("modify_fish_check", naksinuriVO); 
	}

	
	
	
	public void indPreviewSave(NaksinuriSanupVO SanupVO) {
		update("indPreviewSave",SanupVO);	
	}
	public void indPreviewSaveI(NaksinuriSanupVO SanupVO) {
		insert("indPreviewSaveI",SanupVO);	
	}
	public void indfileOrgSave(NaksinuriSanupVO SanupVO) {
		insert("indfileOrgSave",SanupVO);	
	}
	public void indfileOrgdelete(NaksinuriSanupVO SanupVO) {
		delete("indfileOrgdelete",SanupVO);	
	}
	public void delindpreviewData(NaksinuriSanupVO SanupVO) {
		update("delindpreviewData",SanupVO);	
	}
	
	public void deleteindPreview(NaksinuriSanupVO SanupVO) {
		delete("deleteindPreview",SanupVO);	
	}
	
	public void delindpreviewfile(NaksinuriSanupVO SanupVO) {
		update("delindpreviewfile",SanupVO);	
	}

	public void fishPreviewSave(NaksinuriVO naksinuriVO) {
		update("fishPreviewSave",naksinuriVO);	
	}
	public void fishPreviewSaveI(NaksinuriVO naksinuriVO) {
		insert("fishPreviewSaveI",naksinuriVO);	
	}
	public void fishfileOrgdelete(NaksinuriVO naksinuriVO) {
		delete("fishfileOrgdelete",naksinuriVO);	
	}
	public void delfishpreviewData(NaksinuriVO naksinuriVO) {
		update("delfishpreviewData",naksinuriVO);	
	}
	public void delfishpreviewfile(NaksinuriVO naksinuriVO) {
		update("delfishpreviewfile",naksinuriVO);	
	}
	public NaksinuriSanupVO adminPreview_mimg(NaksinuriSanupVO naksinuriSanupVO) {
		return (NaksinuriSanupVO) select("admin_preview_indm_img", naksinuriSanupVO);  
	}
	public NaksinuriVO getListNaksinuri_admin_fish_detail1(NaksinuriVO naksinuriVO) {
		return (NaksinuriVO) select("getListNaksinuri_admin_fish_detail", naksinuriVO);  
	}
	public NaksinuriVO adminPreview_fish_mimg(NaksinuriVO naksinuriVO) {
		return (NaksinuriVO) select("admin_preview_fishm_img", naksinuriVO);  
	}
	public void fishfileOrgSave(NaksinuriFileVO naksinuriFileVO) {
		insert("fishfileOrgSave",naksinuriFileVO);	
	}
	public void delpreview_ind_data(NaksinuriSanupVO naksinuriSanupVO) {
		delete("delpreview_ind_data",naksinuriSanupVO);	
	}
	public void delpreview_ind_file(NaksinuriSanupVO naksinuriSanupVO) {
		delete("delpreview_ind_file",naksinuriSanupVO);	
	}
	public void delpreview_fish_data(NaksinuriVO naksinuriVO) {
		delete("delpreview_fish_data",naksinuriVO);	
	}
	public void delpreview_fish_file(NaksinuriVO naksinuriVO) {
		delete("delpreview_fish_file",naksinuriVO);	
	}
	public NaksinuriVO preview_fish_list(NaksinuriVO naksinuriVO) {
		return (NaksinuriVO) select("preview_fish_list", naksinuriVO);  
	}
	public List<NaksinuriVO> previewlist(NaksinuriVO naksinuriVO) {
		return (List<NaksinuriVO>) list("preview_fish_list", naksinuriVO);  
	}
	public List<NaksinuriSanupVO> previewlist_ind(NaksinuriSanupVO naksinuriSanupVO) {
		return (List<NaksinuriSanupVO>) list("preview_ind_list", naksinuriSanupVO);  
	}
	//메인화면 - 낚시대회 불러오기
	public List<BoardVO> main_naksi_congress(BoardVO boardVO) {
		return (List<BoardVO>) list("main_naksi_congress",boardVO);
	}
	
	// logs
	
	public List<NaksinuriLogsVO> getListLogs(NaksinuriLogsVO LogsVO){
        return (List<NaksinuriLogsVO>) list("logs_list", LogsVO);  
    }


	public void deleteLogs(NaksinuriLogsVO LogsVO){
		delete("logs_delete",LogsVO);	
	}

	public void insertLogs(NaksinuriLogsVO LogsVO){
		delete("logs_insert",LogsVO);	
	}
	//설문조사 메인정보 수정
	public void survey_update_data(SurveyVO surveyVO) {
		update("survey_update_data",surveyVO);	
	}
	//설문조사 질문사항 수정
	public void survey_update_quest(SurveyVO surveyVO) {
		update("survey_update_quest",surveyVO);
	}
	//설문조사 질문아이템 수정
	public void survey_update_quest_item(SurveyVO surveyVO) {
		update("survey_update_quest_item",surveyVO);
	}
	//설문조사 질문 삭제
	public void delete_survey_quest(SurveyVO surveyVO) {
		delete("delete_survey_quest",surveyVO);	
	}
	//설문조사 질문아이템 삭제
	public void delete_survey_questitem(SurveyVO surveyVO) {
		delete("delete_survey_questitem",surveyVO);	
	}
	public List<NaksinuriPolicyVO> select_voc_list(NaksinuriPolicyVO policyVO) {
		return (List<NaksinuriPolicyVO>) list("select_voc_list",policyVO);
	}
	public NaksinuriPolicyVO select_voc_view(NaksinuriPolicyVO policyVO) {
		return (NaksinuriPolicyVO) select("select_voc_view",policyVO);
	}
	public NaksinuriFileVO getInfoFishCompany(NaksinuriFileVO naksinuriFileVO) {
		// TODO Auto-generated method stub
		return (NaksinuriFileVO) select("Fishcompany_info", naksinuriFileVO);
	}
	public NaksinuriSanupVO getInfoIndustry(NaksinuriSanupVO naksinuriSanupVO) {
		// TODO Auto-generated method stub
		return (NaksinuriSanupVO) select("getInfoIndustry", naksinuriSanupVO);
	}

	public List<NaksinuriVO> get_yr_fshtb_sttus(){
		return(List<NaksinuriVO>) list("get_yr_fshtb_sttus");
	}
	public List<NaksinuriVO> get_yr_fshtb_user_sttus(){
		return(List<NaksinuriVO>) list("get_yr_fshtb_user_sttus");
	}
	public List<NaksinuriVO> get_area_fshtb_sttus(NaksinuriVO naksinuriVO){
		return(List<NaksinuriVO>) list("get_area_fshtb_sttus", naksinuriVO);
	}
	public List<NaksinuriVO> get_each_area_fshtb_sttus(NaksinuriVO naksinuriVO){
		return(List<NaksinuriVO>) list("get_each_area_fshtb_sttus", naksinuriVO);
	}
	public List<NaksinuriVO> get_fshtb_year_list(){
		return(List<NaksinuriVO>) list("get_fshtb_year_list");
	}
	public List<NaksinuriVO> get_tot_area_fshtb_list(){
		return(List<NaksinuriVO>) list("get_tot_area_fshtb_list");
	}
	public List<NaksinuriVO> get_fshtb_user_year_list(){
		return(List<NaksinuriVO>) list("get_fshtb_user_year_list");
	}
	public List<NaksinuriVO> get_each_area_fshtb_user_sttus(NaksinuriVO naksinuriVO){
		return(List<NaksinuriVO>) list("get_each_area_fshtb_user_sttus", naksinuriVO);
	}
	public List<NaksinuriVO> get_sido_fshtb_before_year_sttus(NaksinuriVO naksinuriVO){
		return(List<NaksinuriVO>) list("get_sido_fshtb_before_year_sttus", naksinuriVO);
	}
	public List<NaksinuriVO> get_sido_fshtb_before_year_list(NaksinuriVO naksinuriVO){
		return(List<NaksinuriVO>) list("get_sido_fshtb_before_year_list", naksinuriVO);
	}
	public void insert_ocean_fsh_idex(NaksinuriVO naksinuriVO){
		insert("insert_ocean_fsh_idex",naksinuriVO);
	}
	public String get_ocean_fsh_idex_reg_date(){
		return (String) select("get_ocean_fsh_idex_reg_date");
	}
	public void delete_ocean_fsh_idex(NaksinuriVO naksinuriVO){
		delete("delete_ocean_fsh_idex",naksinuriVO);
	}
	public NaksinuriVO getInfoOceanFshIdex(NaksinuriVO naksinuriVO){
		return (NaksinuriVO) select("getInfoOceanFshIdex",naksinuriVO);
	}
	public List<NaksinuriVO> get_ocean_fsh_idex_area_list(NaksinuriVO naksinuriVO){
		return (List<NaksinuriVO>) list("get_ocean_fsh_idex_area_list",naksinuriVO);
	}
}
