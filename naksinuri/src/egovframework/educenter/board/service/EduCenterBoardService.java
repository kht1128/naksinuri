package egovframework.educenter.board.service;

import java.util.List;

public interface EduCenterBoardService {

	public List<EduCenterBoardVO> userBoardList(EduCenterBoardVO boardVO) throws Exception;
	
    public int userBoardListTotCnt(EduCenterBoardVO boardVO) throws Exception;
    	    
    public EduCenterBoardVO userBoardView(EduCenterBoardVO boardVO) throws Exception;
    

}
