package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.educenter.board.service.EduCenterBoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriAnglingVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriFileVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriGoneVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriQnAVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSanupVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriTotalSearch;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.naksinuri_original.let.naksinuri.web.NaksinuriController;

@Service("NaksinuriTotalSearch")
public class NaksinuriTotalSeachImpl implements NaksinuriTotalSearch{
	@Resource(name = "NaksinuriTotalSearchDAO")
	private NaksinuriTotalSearchDAO dao;
	
	@Override
	public BoardVO edu_total(BoardVO boardVO) {
		return dao.eduTotal(boardVO);
	}
	@Override
	public List<BoardVO> edulist(BoardVO boardVO) {
		return dao.edulist(boardVO);
		
	}

	@Override
	public NaksinuriVO co_total(NaksinuriVO naksiVO) {
		return dao.co_total(naksiVO);
	}
	@Override
	public List<NaksinuriFileVO> co_list(NaksinuriFileVO nakVO) {
		return dao.co_list(nakVO);
	}

	@Override
	public NaksinuriSanupVO san_total(NaksinuriSanupVO sanupVO) {
		return dao.san_total(sanupVO);
	}
	@Override
	public List<NaksinuriSanupVO> san_list(NaksinuriSanupVO sanupVO) {
		return dao.san_list(sanupVO);
	}

	@Override
	public NaksinuriAnglingVO ang_total(NaksinuriAnglingVO anglingVO) {
		return dao.ang_total(anglingVO);
	}
	@Override
	public List<NaksinuriAnglingVO> ang_list(NaksinuriAnglingVO anglingVO) {
		return dao.ang_list(anglingVO);
	}

	@Override
	public BoardVO gosu_total(BoardVO boardVO) {
		return dao.gosu_total(boardVO);
	}
	@Override
	public List<BoardVO> gosu_list(BoardVO boardVO) {
		return dao.gosu_list(boardVO);
	}

	@Override
	public BoardVO sense_total(BoardVO boardVO) {
		return dao.sense_total(boardVO);
	}
	@Override
	public List<BoardVO> sense_list(BoardVO boardVO) {
		return dao.sense_list(boardVO);
	}

	@Override
	public BoardVO binding_total(BoardVO boardVO) {
		return dao.binding_total(boardVO);
	}
	@Override
	public List<BoardVO> binding_list(BoardVO boardVO) {
		return dao.binding_list(boardVO);
	}

	@Override
	public BoardVO class_total(BoardVO boardVO) {
		return dao.class_total(boardVO);
	}
	@Override
	public List<BoardVO> class_list(BoardVO boardVO) {
		return dao.class_list(boardVO);
	}

	@Override
	public BoardVO lab_total(BoardVO boardVO) {
		return dao.lab_total(boardVO);
	}
	@Override
	public List<BoardVO> lab_list(BoardVO boardVO) {
		return dao.lab_list(boardVO);
	}

	@Override
	public NaksinuriNewsVO news_total(NaksinuriNewsVO newsVO) {
		return dao.news_total(newsVO);
	}
	@Override
	public List<NaksinuriNewsVO> news_list(NaksinuriNewsVO newsVO) {
		return dao.news_list(newsVO);
	}

	@Override
	public BoardVO notice_total(BoardVO boardVO) {
		return dao.notice_total(boardVO);
	}
	@Override
	public List<BoardVO> notice_list(BoardVO boardVO) {
		return dao.notice_list(boardVO);
	}

	@Override
	public BoardVO congress_total(BoardVO boardVO) {
		return dao.congress_total(boardVO);
	}
	@Override
	public List<BoardVO> congress_list(BoardVO boardVO) {
		return dao.congress_list(boardVO);
	}

	@Override
	public NaksinuriEventVO event_total(NaksinuriEventVO eventVO) {
		return dao.event_total(eventVO);
	}
	@Override
	public List<NaksinuriEventVO> event_list(NaksinuriEventVO eventVO) {
		return dao.event_list(eventVO);
	}

	@Override
	public BoardVO campaign_total(BoardVO boardVO) {
		return dao.campaign_total(boardVO);
	}
	@Override
	public List<BoardVO> campaign_list(BoardVO boardVO) {
		return dao.campaign_list(boardVO);
	}

	@Override
	public NaksinuriGoneVO plocation_total(NaksinuriGoneVO goneVO) {
		return dao.plocation_total(goneVO);
	}
	@Override
	public List<NaksinuriGoneVO> plocation_list(NaksinuriGoneVO goneVO) {
		return dao.plocation_list(goneVO);
	}

	@Override
	public NaksinuriGoneVO llocation_total(NaksinuriGoneVO goneVO) {
		return dao.llocation_total(goneVO);
	}
	@Override
	public List<NaksinuriGoneVO> llocation_list(NaksinuriGoneVO goneVO) {
		return dao.llocation_list(goneVO);
	}

	@Override
	public BoardVO policy_total(BoardVO boardVO) {
		return dao.policy_total(boardVO);
	}
	@Override
	public List<BoardVO> policy_list(BoardVO boardVO) {
		return dao.policy_list(boardVO);
	}

	@Override
	public NaksinuriQnAVO qna_total(NaksinuriQnAVO qnaVO) {
		return dao.qna_total(qnaVO);
	}
	@Override
	public List<NaksinuriQnAVO> qna_list(NaksinuriQnAVO qnaVO) {
		return dao.qna_list(qnaVO);
	}

	@Override
	public BoardVO travel_total(BoardVO boardVO) {
		return dao.travel_total(boardVO);
				
	}
	@Override
	public List<BoardVO> travel_list(BoardVO boardVO) {
		return dao.travel_list(boardVO);
	}

	@Override
	public NaksinuriZisikinVO zisik_total(NaksinuriZisikinVO zisikVO) {
		return dao.zisik_total(zisikVO);
	}
	@Override
	public List<NaksinuriZisikinVO> zisik_list(NaksinuriZisikinVO zisikVO) {
		return dao.zisik_list(zisikVO);
	}

	@Override
	public NaksinuriZisikinVO zazu_total(NaksinuriZisikinVO zisikVO) {
		return dao.zazu_total(zisikVO);
	}
	@Override
	public List<NaksinuriZisikinVO> zazu_list(NaksinuriZisikinVO zisikVO) {
		return dao.zazu_list(zisikVO);
	}

	@Override
	public BoardVO usage_total(BoardVO boardVO) {
		return dao.usage_total(boardVO);
	}
	@Override
	public List<BoardVO> usage_list(BoardVO boardVO) {
		return dao.usage_list(boardVO);
	}

	@Override
	public BoardVO column_total(BoardVO boardVO) {
		return dao.column_total(boardVO);
	}
	@Override
	public List<BoardVO> column_list(BoardVO boardVO) {
		return dao.column_list(boardVO);
	}

	
	@Override
	public int edu_faq_total(EduCenterBoardVO eduCenterBoardVO) {
		return dao.edu_faq_total(eduCenterBoardVO);
	}
	@Override
	public List<EduCenterBoardVO> edu_faq_list(EduCenterBoardVO eduCenterBoardVO) {
		return dao.edu_faq_list(eduCenterBoardVO);
	}
	
	@Override
	public int edu_file_total(EduCenterBoardVO eduCenterBoardVO) {
		return dao.edu_file_total(eduCenterBoardVO);
	}
	@Override
	public List<EduCenterBoardVO> edu_file_list(EduCenterBoardVO eduCenterBoardVO) {
		return dao.edu_file_list(eduCenterBoardVO);
	}
	@Override
	public int edu_notice_total(EduCenterBoardVO eduCenterBoardVO) {
		return dao.edu_notice_total(eduCenterBoardVO);
	}
	@Override
	public List<EduCenterBoardVO> edu_notice_list(EduCenterBoardVO eduCenterBoardVO) {
		return dao.edu_notice_list(eduCenterBoardVO);
	}

	
	
}
