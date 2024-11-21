package egovframework.educenter.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.educenter.board.service.EduCenterBoardVO;


@Repository("eduCenterBoardDAO")
public class EduCenterBoardDAO extends EgovAbstractDAO {

	public List<EduCenterBoardVO> userBoardList(EduCenterBoardVO boardVO) {
        return (List<EduCenterBoardVO>) list("educenter.userBoardList", boardVO);
    }
	
    public int userBoardListTotCnt(EduCenterBoardVO boardVO) {
        return (Integer)select("educenter.userBoardListTotCnt", boardVO);
    }     
    
    @SuppressWarnings("unchecked")
	public EduCenterBoardVO userBoardView(EduCenterBoardVO boardVO) {
    	insert("educenter.boardInsertref", boardVO);
        return (EduCenterBoardVO) select("educenter.userBoardView", boardVO);
    }
	

}