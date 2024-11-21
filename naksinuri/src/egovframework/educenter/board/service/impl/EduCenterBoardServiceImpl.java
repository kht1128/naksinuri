package egovframework.educenter.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.educenter.board.service.EduCenterBoardService;
import egovframework.educenter.board.service.EduCenterBoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("eduCenterBoardService")
public class EduCenterBoardServiceImpl extends EgovAbstractServiceImpl implements EduCenterBoardService {
	
	@Resource(name="eduCenterBoardDAO")
	private EduCenterBoardDAO eduCenterBoardDAO;
	
	@Override
	public List<EduCenterBoardVO> userBoardList(EduCenterBoardVO boardVO) throws Exception  {
		return eduCenterBoardDAO.userBoardList(boardVO);
	}
	
    @Override
	public int userBoardListTotCnt(EduCenterBoardVO boardVO) throws Exception  {
    	return eduCenterBoardDAO.userBoardListTotCnt(boardVO);
    }
        
    @Override
	public EduCenterBoardVO userBoardView(EduCenterBoardVO boardVO) throws Exception  {
		return eduCenterBoardDAO.userBoardView(boardVO);
	}
    
}