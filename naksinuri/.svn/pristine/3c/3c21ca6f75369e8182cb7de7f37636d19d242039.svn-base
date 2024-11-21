package egovframework.naksinuri_original.let.naksinuri.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.com.cmm.service.NaksinuriOriginalFileVO;
import egovframework.naksinuri_original.com.cmm.service.impl.NaksinuriOriginalFileManageDAO;
import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksiCongressMbrVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksiCongressOwnVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAdminVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriPolicyVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriVO;
import egovframework.naksinuri_original.let.naksinuri.service.SmsMngrVO;
import egovframework.naksinuri_original.let.naksinuri.service.SurveyVO;


@Service("NaksinuriService")
public class NaksinuriServiceImpl implements NaksinuriService {
	
	@Resource(name = "NaksinuriDAO")
    private NaksinuriDAO naksinuriDAO;
	
	@Resource(name="NaksinuriService")
	private NaksinuriService service;
	
	@Resource(name = "NaksinuriOriginalFileManageDAO")
	private NaksinuriOriginalFileManageDAO fileMngDAO;
	

	@Override
	public void insertCorp(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.insertNaksinuriInfo(naksinuriVO);		
	}
	@Override
	public void insertCorpPreview(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.insertNaksinuriPreviewInfo(naksinuriVO);		
	}	
	@Override
	public void insertCorpPreviewP(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.insertNaksinuriPreviewInfoP(naksinuriVO);		
	}	
	@Override
	public void insertCorpSanupPreview(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		naksinuriDAO.insertNaksinuriSanupPreviewInfo(naksinuriSanupVO);		
	}	
	@Override
	public void insert_lab_data(BoardVO boardVO) throws Exception {
		naksinuriDAO.insert_lab_data(boardVO);	
	}





	@Override
	public NaksinuriVO modifyCorp(NaksinuriVO naksinuriVO) throws Exception {
		
		NaksinuriVO naksinuriVO2 = naksinuriDAO.selectNaksinuriInfo(naksinuriVO); 
		
		if(naksinuriVO2 !=null && !naksinuriVO2.getCo_nm().equals("") && !naksinuriVO2.getCeo_nm().equals("")){
			return naksinuriVO2;
		}else{
			naksinuriVO2 = new NaksinuriVO();
		}
		return naksinuriVO2;
	}
	@Override
	public NaksinuriVO modify_fish_check(NaksinuriVO naksinuriVO) throws Exception {
		
		NaksinuriVO naksinuriVO2 = naksinuriDAO.modify_fish_check(naksinuriVO); 
		
		if(naksinuriVO2 !=null && !naksinuriVO2.getNak_id().equals("")){
			return naksinuriVO2;
		}else{
			naksinuriVO2 = new NaksinuriVO();
		}
		return naksinuriVO2;
	}	
	@Override
	public NaksinuriSanupVO modifyCorpSanup(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		
		NaksinuriSanupVO naksinuriVO2 = naksinuriDAO.selectNaksinuriSanupInfo(naksinuriSanupVO); 
		
		if(naksinuriVO2 !=null && !naksinuriVO2.getSan_buisnessman().equals("")){
			return naksinuriVO2;
		}else{
			naksinuriVO2 = new NaksinuriSanupVO();
		}
		return naksinuriVO2;
	}
	@Override
	public NaksinuriSanupVO modify_ind_check(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		
		NaksinuriSanupVO naksinuriVO2 = naksinuriDAO.modify_ind_check(naksinuriSanupVO); 
		
		if(naksinuriVO2 !=null && !naksinuriVO2.getSan_sn().equals("")){
			return naksinuriVO2;
		}else{
			naksinuriVO2 = new NaksinuriSanupVO();
		}
		return naksinuriVO2;
	}

	@Override
	public NaksinuriVO searchCo_nm(NaksinuriVO naksinuriVO) throws Exception {
		// 1. 회사명과 대표자명이 DB와 일치하는 정보를 조회
		NaksinuriVO naksinuriVO2 = naksinuriDAO.searchCo_nm(naksinuriVO);
		if(naksinuriVO2 !=null && !naksinuriVO2.getCo_nm().equals("")){
			return naksinuriVO2;				
		}else{
			naksinuriVO2 = new NaksinuriVO();
		}
		return naksinuriVO2;
	}


	@Override
	public void updateInfo(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.updateInfo(naksinuriVO);
	}


	@Override
	public List<NaksinuriFileVO> searchFile(NaksinuriFileVO naksinurifileVO) throws Exception {
		return naksinuriDAO.searchFile(naksinurifileVO);
	}


	@Override
	public NaksinuriFileVO mimg(NaksinuriFileVO naksinurifileVO) throws Exception {
		NaksinuriFileVO naksinuriFileVO2 = naksinuriDAO.mimg(naksinurifileVO);
		return naksinuriFileVO2;
	}
	@Override
	public NaksinuriVO selectNaksinuri_admin(NaksinuriVO naksinuriVO) throws Exception {
		NaksinuriVO data = naksinuriDAO.selectNaksinuri_admin(naksinuriVO);
		return data;
	}


	@Override
	public List<NaksinuriVO> getListNaksinuri_admin(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin(naksinuriVO);
	}


	@Override
	public NaksinuriVO modifyCorp_admin(NaksinuriVO naksinuriVO) throws Exception {
		NaksinuriVO naksinuriVO2 = naksinuriDAO.selectNaksinuriInfo_admin(naksinuriVO); 
		return naksinuriVO2;
	}
	@Override
	public NaksinuriVO modifyCorpPreview_admin(NaksinuriVO naksinuriVO) throws Exception {
		NaksinuriVO naksinuriVO2 = naksinuriDAO.selectNaksinuriInfoPreview_admin(naksinuriVO); 
		return naksinuriVO2;
	}	
	@Override
	public List<NaksinuriFileVO> admin_searchFile(NaksinuriFileVO naksinurifileVO) throws Exception {
		return naksinuriDAO.admin_searchFile(naksinurifileVO);
	}


	@Override
	public NaksinuriFileVO admin_mimg(NaksinuriFileVO naksinurifileVO) throws Exception {
		NaksinuriFileVO naksinuriFileVO2 = naksinuriDAO.admin_mimg(naksinurifileVO);
		return naksinuriFileVO2;
	}
	@Override
	public NaksinuriFileVO adminPreview_mimg(NaksinuriFileVO naksinurifileVO) throws Exception {
		NaksinuriFileVO pnaksinuriFileVO2 = naksinuriDAO.adminPreview_mimg(naksinurifileVO);
		return pnaksinuriFileVO2;
	}

	@Override
	public List<NaksinuriFileVO> getListFishJob(NaksinuriFileVO naksinurifileVO) throws Exception {
		return naksinuriDAO.getListfishjob(naksinurifileVO);
	
	}
	@Override
	public List<NaksinuriFileVO> getListFishCompany(NaksinuriFileVO naksinurifileVO) throws Exception {
		return naksinuriDAO.getListFishCompany(naksinurifileVO);
	}
	@Override
	public List<NaksinuriVO> getListOceanFshIdex(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.getListOceanFshIdex(naksinuriVO);
	}
	@Override
	public List<NaksinuriFileVO> getMapListFishCompany(NaksinuriFileVO naksinurifileVO) throws Exception {
		return naksinuriDAO.getMapListFishCompany(naksinurifileVO);
	}

	@Override
	public void delete_list(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.delete_list(naksinuriVO);
	}


	@Override
	public void insert_data(BoardVO boardVO) throws Exception {
		naksinuriDAO.insert_data(boardVO);
		
	}

	@Override
	public void insert_survey_data(SurveyVO surveyVO) throws Exception {
		naksinuriDAO.insert_survey_data(surveyVO);
		
	}
	
	@Override
	public void insert_survey_quest(SurveyVO surveyVO) throws Exception {
		naksinuriDAO.insert_survey_quest(surveyVO);
		
	}
	
	@Override
	public void insert_survey_quest_item(SurveyVO surveyVO) throws Exception {
		naksinuriDAO.insert_survey_quest_item(surveyVO);
		
	}
	
	@Override
	public void survey_insert_answer_info(SurveyVO surveyVO) throws Exception{
		naksinuriDAO.survey_insert_answer_info(surveyVO);
	}
	

	
	@Override
	public void survey_insert_answer(SurveyVO surveyVO) throws Exception{
		naksinuriDAO.survey_insert_answer(surveyVO);
	}

	@Override
	public List<BoardVO> select_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_list(boardVO);
		
	}
	
	@Override
	public List<BoardVO> select_list_admin(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_list_admin(boardVO);
		
	}
	
	@Override
	public List<BoardVO> noticemark_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.noticemark_list(boardVO);
		
	}


	@Override
	public void delete_boardlist(BoardVO boardVO) throws Exception {
		naksinuriDAO.delete_boardlist(boardVO);
		
	}


	@Override
	public BoardVO board_findCorp(BoardVO boardVO) throws Exception {
		
		
		return naksinuriDAO.board_findCorp(boardVO);
	}

	@Override
	public BoardVO phone1_findCorp(BoardVO boardVO) throws Exception {
		return naksinuriDAO.phone1_findCorp(boardVO);
	}


	@Override
	public BoardVO phone2_findCorp(BoardVO boardVO) throws Exception {
		return naksinuriDAO.phone2_findCorp(boardVO);
	}


	@Override
	public BoardVO phone3_findCorp(BoardVO boardVO) throws Exception {
		return naksinuriDAO.phone3_findCorp(boardVO);
	}


	@Override
	public BoardVO email1_findCorp(BoardVO boardVO) throws Exception {
		return naksinuriDAO.email1_findCorp(boardVO);
	}


	@Override
	public BoardVO email2_findCorp(BoardVO boardVO) throws Exception {
		return naksinuriDAO. email2_findCorp(boardVO);
	}

	@Override
	public void update_data(BoardVO boardVO) throws Exception {
		naksinuriDAO.update_data(boardVO);
		
	}


	@Override
	public void view_update(BoardVO boardVO) throws Exception {
		naksinuriDAO.view_update(boardVO);
		
	}

	
	@Override
	public void like_update(BoardVO boardVO) throws Exception {
		naksinuriDAO.like_update(boardVO);
		
	}
	


	@Override
	public void co_insert(BoardVO boardVO) throws Exception {
		naksinuriDAO.co_insert(boardVO);
		
	}


	@Override
	public List<BoardVO> select_list_comment(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_list_comment(boardVO);
	}


	@Override
	public List<BoardVO> select_list_post(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_list_post(boardVO);
	}

	@Override
	public List<BoardVO> select_list_bobo(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_list_bobo(boardVO);
	}


	@Override
	public BoardVO select_next(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_next(boardVO);
	}


	@Override
	public BoardVO select_prev(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_prev(boardVO);
	}


	@Override
	public List<BoardVO> select_main_news(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_main_news(boardVO);
	}


	@Override
	public List<BoardVO> select_main_notice(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_main_notice(boardVO);
	}

	@Override
	public List<BoardVO> river_anglingm(BoardVO boardVO) throws Exception {
		return naksinuriDAO.river_anglingm(boardVO);
	}


	@Override
	public List<BoardVO> sea_anglingm(BoardVO boardVO) throws Exception {
		return naksinuriDAO.sea_anglingm(boardVO);
	}
	
	
	@Override
	public List<BoardVO> river_angling(BoardVO boardVO) throws Exception {
		return naksinuriDAO.river_angling(boardVO);
	}


	@Override
	public List<BoardVO> sea_angling(BoardVO boardVO) throws Exception {
		return naksinuriDAO.sea_angling(boardVO);
	}


	@Override
	public List<NaksinuriVO> choo_job(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.choo_job(naksinuriVO);
	}


	@Override
	public List<NaksinuriVO> boatfishing_job(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.boatfishing_job(naksinuriVO);
	}


	@Override
	public List<NaksinuriVO> riverfishing_job(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.riverfishing_job(naksinuriVO);
	}


	@Override
	public List<NaksinuriVO> seafishing_job(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.seafishing_job(naksinuriVO);
	}


	
	@Override
	public BoardVO bmimg(BoardVO boardVO) throws Exception {
		return naksinuriDAO.bmimg(boardVO);
	}


	@Override
	public BoardVO bsimg(BoardVO boardVO) throws Exception {
		return naksinuriDAO.bsimg(boardVO);
	}



	@Override
	public List<BoardVO> ba_file(BoardVO boardVO) throws Exception {
		return naksinuriDAO.ba_file(boardVO);
	}
	
	@Override
	public BoardVO movfile(BoardVO boardVO) throws Exception {
		return naksinuriDAO.movfile(boardVO);
	}
	
	
	@Override // PDF이미지 슬라이드 파일
	public List<BoardVO> pdfimgfile(BoardVO boardVO) throws Exception {
		return naksinuriDAO.pdfimgfile(boardVO);
	}


	@Override
	public void co_delete(BoardVO boardVO) throws Exception {
			naksinuriDAO.co_delete(boardVO);
		
	}


	@Override
	public BoardVO copass_find(BoardVO boardVO) throws Exception {
		return naksinuriDAO.copass_find(boardVO);
	}


	


	@Override
	public BoardVO bopass_find(BoardVO boardVO) throws Exception {
		return naksinuriDAO.bopass_find(boardVO);
	}


	@Override
	public void user_file_delete(NaksinuriOriginalFileVO fileVO) throws Exception {
		naksinuriDAO.user_file_delete(fileVO);
		

	}

	@Override
	public List<SurveyVO> survey_select_list(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.survey_select_list(surveyVO);
	}

	@Override
	public List<SurveyVO> end_survey_select_list(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.end_survey_select_list(surveyVO);
	}

	@Override
	public List<SurveyVO> survey_select_list_user(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.survey_select_list_user(surveyVO);
	}


	@Override
	public List<SurveyVO> end_survey_select_list_user(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.end_survey_select_list_user(surveyVO);
	}

	@Override
	public SurveyVO select_survey(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.select_survey(surveyVO);
	}
	@Override
	public SurveyVO check_survey_by_ip(SurveyVO surveyVO) throws Exception{
		return naksinuriDAO.check_survey_by_ip(surveyVO);
	}
	
	@Override
	public List<SurveyVO> survey_answerole_list(SurveyVO surveyVO) throws Exception{
		return naksinuriDAO.survey_answerole(surveyVO);
	}
	
	@Override
	public List<SurveyVO> select_surveyq_list(SurveyVO surveyVO) throws Exception{
		return naksinuriDAO.select_surveyq_list (surveyVO);
	}
	
	@Override
	public List<SurveyVO> select_surveyqi_list(SurveyVO surveyVO) throws Exception{
		return naksinuriDAO.select_surveyqi_list(surveyVO);
	}
	
	@Override
	public SurveyVO select_survey_subject(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.select_survey_subject(surveyVO);
	}
	
	@Override
	public SurveyVO select_surveyq_subject(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.select_surveyq_subject(surveyVO);
	}
	
	public SurveyVO survey_answfind(SurveyVO surveyVO) throws Exception{
		return naksinuriDAO.survey_answfind(surveyVO);
	}
	
	@Override
	public List<SurveyVO> survey_anscnt(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.survey_anscnt(surveyVO);
	}

	
	@Override
	public List<BoardVO> select_lab_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.select_lab_list(boardVO);
	}


	@Override
	public List<BoardVO> date_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.date_list(boardVO);
	}
	
	
	@Override
	public List<BoardVO> date_list_condition(BoardVO boardVO) throws Exception {
		return naksinuriDAO.date_list_condition(boardVO);
	}


	@Override
	public List<BoardVO> latest_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.latest_list(boardVO);
	}


	@Override
	public List<BoardVO> congress_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.congress_list(boardVO);
	}


	@Override
	public List<BoardVO> congress_endlist(BoardVO boardVO) throws Exception {
		return naksinuriDAO.congress_endlist(boardVO);
	}


	@Override
	public List<NaksinuriVO> getListNaksinuri_latest(NaksinuriVO naksinuriVO) {
		return naksinuriDAO.getListNaksinuri_latest(naksinuriVO);
	}


	@Override
	public List<NaksinuriVO> getListNaksinuri_Kor(NaksinuriVO naksinuriVO) {
		return naksinuriDAO.getListNaksinuri_Kor(naksinuriVO);
	}


	@Override
	public BoardVO getbo_sido(BoardVO boardVO) throws Exception {
		return naksinuriDAO.getbo_sido(boardVO);
	}


	@Override
	public BoardVO getbo_gugun(BoardVO boardVO) throws Exception {
		return naksinuriDAO.getbo_gugun(boardVO);
	}

//	boardVO와 연결되어있는 게시판들 리스트정렬 
	@Override
	public List<BoardVO> trashBoard_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.trashBoard_list(boardVO);
	}

//	boardVO와 연결되어있는 게시판들중 board_notice_y = 1 인 값들 리스트정렬
	@Override
	public List<BoardVO> trashNotice_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.trashNotice_list(boardVO);
	}


//  선상낚시 휴지통에서 최신순 정렬
	@Override
	public List<NaksinuriVO> trashBoatfishing_list(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.trashBoatfishing_list(naksinuriVO);
	}

//	선상낚시 휴지통에서 등록순 정렬
	@Override
	public List<NaksinuriVO> trashBoatlatest_list(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.trashBoatlatest_list(naksinuriVO);
	}

//	선상낚시 휴지통에서 한글순으로 리스트정렬 
	@Override
	public List<NaksinuriVO> trashBoatKor_list(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.trashBoatKor_list(naksinuriVO);
	}
	



//	boardVO와 연결되어있는 게시판들중 삭제했을때 휴지통으로 이동
//	bo_sn 휴지통으로 보내기
	@Override
	public void gotrash_boardlist(BoardVO boardVO) throws Exception {
		naksinuriDAO.gotrash_boardlist(boardVO);
		
	}

//	nak_id 휴지통으로 보내기
	@Override
	public void gotrashfishing_list(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.gotrashfishing_list(naksinuriVO);
		
	}
	
	// 설문조사 휴지통으로 보내기
	public void gotrash_survey(SurveyVO surveyVO) throws Exception{
		naksinuriDAO.gotrash_survey(surveyVO);
	}


	@Override
	public List<BoardVO> congress_trash(BoardVO boardVO) throws Exception {
		return naksinuriDAO.congress_trash(boardVO);
	}


	@Override
	public List<BoardVO> congress_endtrash(BoardVO boardVO) throws Exception {
		return naksinuriDAO.congress_endtrash(boardVO);
	}


	@Override
	public List<SurveyVO> end_survey_select_trash(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.end_survey_select_trash(surveyVO);
	}


	@Override
	public List<SurveyVO> survey_select_trash(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.survey_select_trash(surveyVO);
	}


	@Override
	public void restore_survey(SurveyVO surveyVO) throws Exception{
		naksinuriDAO.restore_survey(surveyVO);
	}
	
	@Override
	public void restore_boardlist(BoardVO boardVO) throws Exception {
		naksinuriDAO.restore_boardlist(boardVO);
		
	}
	
	@Override
	public void delete_survey(SurveyVO surveyVO)throws Exception{
		naksinuriDAO.delete_survey(surveyVO);
	}


	@Override
	public void restore_list(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.restore_list(naksinuriVO);
		
	}



	@Override
	public List<BoardVO> campaign_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.campaign_list(boardVO);
	}



	@Override
	public List<BoardVO> campaign_endlist(BoardVO boardVO) throws Exception {
		return naksinuriDAO.campaign_endlist(boardVO);
	}



	@Override
	public List<BoardVO> campaignTrash(BoardVO boardVO) throws Exception {
		return naksinuriDAO.campaignTrash(boardVO);
	}


	@Override
	public List<BoardVO> campaignendTrash(BoardVO boardVO) throws Exception {
		return naksinuriDAO.campaignendTrash(boardVO);
	}


	@Override
	public NaksinuriVO select_next(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.select_next(naksinuriVO);
	}


	@Override
	public NaksinuriVO select_prev(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.select_prev(naksinuriVO);
	}


	@Override
	public List<SurveyVO> get_Excelitem(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.get_Excelitem(surveyVO);
	}


	@Override
	public List<SurveyVO> survey_answerole_list2(SurveyVO surveyVO) throws Exception {
		return naksinuriDAO.survey_answerole_list2(surveyVO);
	}


	@Override
	public List<NaksinuriFileVO> admin_searchFile1(NaksinuriFileVO naksinurifileVO) throws Exception {
		return naksinuriDAO.admin_searchFile1(naksinurifileVO);
	}
	@Override
	public List<NaksinuriFileVO> adminPreview_searchFile1(NaksinuriFileVO naksinurifileVO) throws Exception {
		return naksinuriDAO.adminPreview_searchFile1(naksinurifileVO);
	}
	@Override
	public List<NaksinuriVO> Preview_searchFile(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.Preview_searchFile(naksinuriVO);
	}
	@Override
	public List<NaksinuriSanupVO> Preview_searchFile2(NaksinuriSanupVO SanupVO) throws Exception {
		return naksinuriDAO.Preview_searchFile2(SanupVO);
	}
	

	@Override
	public NaksinuriFileVO admin_mimg2(NaksinuriFileVO naksinurifileVO) {
		return naksinuriDAO.admin_mimg2(naksinurifileVO);
	}


	@Override
	public void up_hit(NaksinuriFileVO naksinurifileVO) throws Exception {
		 naksinuriDAO.up_hit(naksinurifileVO);
		
	}


	@Override
	public void congress_insert_data(BoardVO boardVO) throws Exception {
		naksinuriDAO.congress_insert_data(boardVO);
		
	}


	@Override
	public List<BoardVO> user_congress_list(BoardVO boardVO) throws Exception {
		return naksinuriDAO.user_congress_list(boardVO);
	}



	@Override
	  public void congressmbr_insert_data(NaksiCongressMbrVO congressmbrVO)
	  {
	    this.naksinuriDAO.congressmbr_insert_data(congressmbrVO);
	  }

	@Override
	  public List<BoardVO> registered_congress_list(BoardVO boardVO)
	    throws Exception
	  {
	    return this.naksinuriDAO.registered_congress_search(boardVO);
	  }

	@Override
	  public List<NaksiCongressMbrVO> getmbr_list(NaksiCongressMbrVO congressVO)
	    throws Exception
	  {
	    return this.naksinuriDAO.getmbr_list(congressVO);
	  }

	@Override
	  public void update_mbr_status(NaksiCongressMbrVO congressVO)
	  {
	    this.naksinuriDAO.mbrstatus_update(congressVO);
	  }

	@Override
	  public List<BoardVO> select_endcongress(BoardVO boardvo)
	  {
	    return this.naksinuriDAO.select_endcongress(boardvo);
	  }

	@Override
	  public void delete_mbrinfo(NaksiCongressMbrVO congressvo)
	  {
	    this.naksinuriDAO.delete_mbrinfo(congressvo);
	  }

	@Override
	  public List<NaksiCongressMbrVO> participated_congress_list(NaksiCongressMbrVO congressVO)
	    throws Exception
	  {
	    return this.naksinuriDAO.participated_congress_list(congressVO);
	  }

	@Override
	  public NaksiCongressMbrVO mbr_detail(NaksiCongressMbrVO congressVO)
	  {
	    return this.naksinuriDAO.mbr_detail(congressVO);
	  }

	@Override
	  public NaksinuriAdminVO adminset_findCorp(NaksinuriAdminVO adminVO)
	  {
	    return this.naksinuriDAO.adminset_findCorp(adminVO);
	  }

	@Override
	  public void adminset_update(NaksinuriAdminVO adminVO)
	  {
	    this.naksinuriDAO.adminset_update(adminVO);
	  }

	@Override
	  public BoardVO congress_findCorp(BoardVO boardVO)
	  {
	    return this.naksinuriDAO.congress_findCorp(boardVO);
	  }


	@Override
	public void congressOwn_insert_data(NaksiCongressOwnVO congressOwnVO) throws Exception {
		this.naksinuriDAO.congressOwn_insert_data(congressOwnVO);
	}


	@Override
	public NaksiCongressOwnVO own_detail(NaksiCongressOwnVO congressOwnVO) throws Exception {
		return this.naksinuriDAO.own_detail(congressOwnVO);
	}


	@Override
	public List<NaksiCongressMbrVO> getmbr_all_list(NaksiCongressMbrVO congressVO) throws Exception {
		return this.naksinuriDAO.getmbr_all_list(congressVO);
	}


	@Override
	public List<NaksiCongressOwnVO> getmbr_own_all_list(NaksiCongressOwnVO congressOwnVO) throws Exception {
		return this.naksinuriDAO.getmbr_own_all_list(congressOwnVO);
	}
	@Override
	public List<BoardVO> gongmo_list(BoardVO boardVO) throws Exception {
		return this.naksinuriDAO.gongmo_list(boardVO);
	}
	@Override
	public List<BoardVO> gongmo_endlist(BoardVO boardVO) throws Exception {
		return this.naksinuriDAO.gongmo_endlist(boardVO);
	}
	@Override
	public List<BoardVO> gongmo_trash(BoardVO boardVO) throws Exception {
		return this.naksinuriDAO.gongmo_trash(boardVO);
	}
	@Override
	public List<BoardVO> gongmo_endtrash(BoardVO boardVO) throws Exception {
		return this.naksinuriDAO.gongmo_endtrash(boardVO);
	}
	@Override
	public List<BoardVO> gongmo_all_list(BoardVO boardVO) throws Exception {
		return this.naksinuriDAO.gongmo_all_list(boardVO);
	}
	@Override
	public List<NaksiCongressMbrVO> mbr_confirm(NaksiCongressMbrVO congressVO) {
		return this.naksinuriDAO.mbr_confirm(congressVO);
	}
	@Override
	public void congressmbr_update_data(NaksiCongressMbrVO congressmbrVO) {
		this.naksinuriDAO.congressmbr_update_data(congressmbrVO);
	}


	@Override
	public List<NaksinuriVO> getListNaksinuri_admin_preview_fish_list(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_preview_fish_list(naksinuriVO);
	}	
	@Override
	public NaksinuriVO getListNaksinuri_admin_preview_fish_detail(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_preview_fish_detail(naksinuriVO); 
	}	
	@Override
	public List<NaksinuriSanupVO> getListNaksinuri_admin_preview_ind_list(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_preview_ind_list(naksinuriSanupVO);
	}	
	@Override
	public NaksinuriSanupVO getListNaksinuri_admin_preview_ind_detail(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_preview_ind_detail(naksinuriSanupVO); 
	}	 
	@Override
	public NaksinuriSanupVO getListNaksinuri_admin_ind_detail(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_ind_detail(naksinuriSanupVO); 
	}	 
	
	@Override
	public void fishInsertPreview(NaksinuriFileVO naksinuriFileVO) throws Exception{
		naksinuriDAO.fishInsertPreview(naksinuriFileVO); 
	}
	@Override
	public void deletefishDataPreview(NaksinuriVO naksinuriVO) throws Exception{
		naksinuriDAO.deletefishDataPreview(naksinuriVO); 
	}
	@Override
	public void deletefishFilePreview(NaksinuriVO naksinuriVO) throws Exception{
		naksinuriDAO.deletefishFilePreview(naksinuriVO); 
	}
	@Override
	public void indInsertPreview(NaksinuriSanupVO SanupVO) throws Exception{
		naksinuriDAO.indInsertPreview(SanupVO); 
	}
	@Override
	public void deleteindDataPreview(NaksinuriSanupVO SanupVO) throws Exception{
		naksinuriDAO.deleteindDataPreview(SanupVO); 
	}
	@Override
	public void deleteindFilePreview(NaksinuriSanupVO SanupVO) throws Exception{
		naksinuriDAO.deleteindFilePreview(SanupVO); 
	}

	
	
	
	@Override
	public void indPreviewSave(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.indPreviewSave(SanupVO);
	}
	public void indPreviewSaveI(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.indPreviewSaveI(SanupVO);
	}
	@Override
	public void indfileOrgSave(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.indfileOrgSave(SanupVO);
	}
	@Override
	public void indfileOrgdelete(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.indfileOrgdelete(SanupVO);
	}	
	@Override
	public void delindpreviewData(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.delindpreviewData(SanupVO);
	}
	@Override
	public void delindpreviewfile(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.delindpreviewfile(SanupVO);
	}
	@Override
	public void deleteindPreview(NaksinuriSanupVO SanupVO) throws Exception {
		naksinuriDAO.deleteindPreview(SanupVO);
	}
	
	
	
	
	@Override
	public void fishPreviewSave(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.fishPreviewSave(naksinuriVO);
	}
	@Override
	public void fishPreviewSaveI(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.fishPreviewSaveI(naksinuriVO);
	}
	@Override
	public void fishfileOrgdelete(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.fishfileOrgdelete(naksinuriVO);
	}	
	@Override
	public void delfishpreviewData(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.delfishpreviewData(naksinuriVO);
	}
	@Override
	public void  delfishpreviewfile(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.delfishpreviewfile(naksinuriVO);
	}
	@Override
	public NaksinuriSanupVO adminPreview_mimg(NaksinuriSanupVO naksinuriSanupVO) throws Exception {
		return naksinuriDAO.adminPreview_mimg(naksinuriSanupVO);
	}
	@Override
	public NaksinuriVO getListNaksinuri_admin_fish_detail(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.getListNaksinuri_admin_fish_detail(naksinuriVO);
	}
	@Override
	public NaksinuriVO adminPreview_fish_mimg(NaksinuriVO naksinuriVO) throws Exception {
		return naksinuriDAO.adminPreview_fish_mimg(naksinuriVO);
	}
	@Override
	public void fishfileOrgSave(NaksinuriFileVO naksinuriFileVO) {
		naksinuriDAO.fishfileOrgSave(naksinuriFileVO);
	}

	@Override
	public void delpreview_fish_data(NaksinuriVO naksinuriVO) throws Exception {
		naksinuriDAO.delpreview_fish_data(naksinuriVO);
	}
	@Override
	public void delpreview_fish_file(NaksinuriVO naksinuriVO) {
		naksinuriDAO.delpreview_fish_file(naksinuriVO);
	}
	@Override
	public NaksinuriVO preview_fish_list(NaksinuriVO naksinuriVO) {
		return naksinuriDAO.preview_fish_list(naksinuriVO);
	}
	@Override
	public List<NaksinuriVO> previewlist(NaksinuriVO naksinuriVO) {
		return naksinuriDAO.previewlist(naksinuriVO);
	}
	@Override
	public List<BoardVO> main_naksi_congress(BoardVO boardVO) throws Exception {
		return naksinuriDAO.main_naksi_congress(boardVO);
	}
	@Override
	public void survey_update_data(SurveyVO surveyVO) throws Exception {
		naksinuriDAO.survey_update_data(surveyVO);
	}
	@Override
	public void survey_update_quest(SurveyVO surveyVO) throws Exception {
		naksinuriDAO.survey_update_quest(surveyVO);
		
	}
	@Override
	public void survey_update_quest_item(SurveyVO surveyVO) throws Exception {
		naksinuriDAO.survey_update_quest_item(surveyVO);
	}
	@Override
	public void delete_survey_quest(SurveyVO surveyVO) throws Exception {
		naksinuriDAO.delete_survey_quest(surveyVO);
	}
	@Override
	public void delete_survey_questitem(SurveyVO surveyVO) throws Exception {
		naksinuriDAO.delete_survey_questitem(surveyVO);
	}
	@Override
	public List<NaksinuriPolicyVO> select_voc_list(NaksinuriPolicyVO policyVO) throws Exception {
		return naksinuriDAO.select_voc_list(policyVO);
	}
	@Override
	public NaksinuriPolicyVO select_voc_view(NaksinuriPolicyVO policyVO) throws Exception {
		return naksinuriDAO.select_voc_view(policyVO);
	}
	@Override
	public NaksinuriFileVO getInfoFishCompany(NaksinuriFileVO naksinuriFileVO) {
		// TODO Auto-generated method stub
		return naksinuriDAO.getInfoFishCompany(naksinuriFileVO);
	}
	@Override
	public NaksinuriSanupVO getInfoIndustry(NaksinuriSanupVO naksinuriSanupVO) {
		// TODO Auto-generated method stub
		return naksinuriDAO.getInfoIndustry(naksinuriSanupVO);
	}
	@Override
	public List<NaksinuriVO> get_yr_fshtb_sttus() throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_yr_fshtb_sttus();
	}
	@Override
	public List<NaksinuriVO> get_yr_fshtb_user_sttus() throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_yr_fshtb_user_sttus();
	}
	@Override
	public List<NaksinuriVO> get_area_fshtb_sttus(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_area_fshtb_sttus(naksinuriVO);
	}
	@Override
	public List<NaksinuriVO> get_each_area_fshtb_sttus(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_each_area_fshtb_sttus(naksinuriVO);
	}
	@Override
	public List<NaksinuriVO> get_fshtb_year_list() throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_fshtb_year_list();
	}
	@Override
	public List<NaksinuriVO> get_tot_area_fshtb_list() throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_tot_area_fshtb_list();
	}
	@Override
	public List<NaksinuriVO> get_fshtb_user_year_list() throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_fshtb_user_year_list();
	}
	@Override
	public List<NaksinuriVO> get_each_area_fshtb_user_sttus(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_each_area_fshtb_user_sttus(naksinuriVO);
	}
	@Override
	public List<NaksinuriVO> get_sido_fshtb_before_year_sttus(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_sido_fshtb_before_year_sttus(naksinuriVO);
	}
	@Override
	public List<NaksinuriVO> get_sido_fshtb_before_year_list(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_sido_fshtb_before_year_list(naksinuriVO);
	}
	@Override
	public void insert_ocean_fsh_idex(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		naksinuriDAO.insert_ocean_fsh_idex(naksinuriVO);
	}
	@Override
	public String get_ocean_fsh_idex_reg_date() throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_ocean_fsh_idex_reg_date();
	}
	@Override
	public void delete_ocean_fsh_idex(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		naksinuriDAO.delete_ocean_fsh_idex(naksinuriVO);
	}
	@Override
	public NaksinuriVO getInfoOceanFshIdex(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.getInfoOceanFshIdex(naksinuriVO);
	}
	@Override
	public List<NaksinuriVO> get_ocean_fsh_idex_area_list(NaksinuriVO naksinuriVO) throws Exception {
		// TODO Auto-generated method stub
		return naksinuriDAO.get_ocean_fsh_idex_area_list(naksinuriVO);
	}
	
	 
}
