package egovframework.eduadm.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.eduadm.board.service.EduBoardService;
import egovframework.eduadm.board.service.EduBoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("eduBoardService")
public class EduBoardServiceImpl extends EgovAbstractServiceImpl implements EduBoardService {
	
	/** EduBoardDAO */
	@Resource(name="eduBoardDAO")
	private EduBoardDAO eduBoardDAO;
	
	@Override
	public List<EduBoardVO> boardAdmList(EduBoardVO userSearchVO) {
		return eduBoardDAO.boardAdmList(userSearchVO);
	}
	
    @Override
	public int boardAdmListTotCnt(EduBoardVO userSearchVO) {
    	return eduBoardDAO.boardAdmListTotCnt(userSearchVO);
    }
    
    @Override
	public EduBoardVO boardAdmView(EduBoardVO boardAdmVO) {
		return eduBoardDAO.boardAdmView(boardAdmVO);
	}
    
    public String boardAdmInsert(EduBoardVO boardAdmVO) throws Exception  {
    	String result = eduBoardDAO.boardAdmInsert(boardAdmVO);
    	return result;
    }
    
    public String boardAdmReplyinsert(EduBoardVO boardAdmVO) throws Exception  {
    	String result = eduBoardDAO.boardAdmReplyinsert(boardAdmVO);
    	return result;
    }
    
    @Override
	public void boardAdmDelete(EduBoardVO boardAdmVO) {
    	eduBoardDAO.boardAdmDelete(boardAdmVO);
	}
    
    @Override
	public void boardAdmUpdate(EduBoardVO boardAdmVO) throws Exception {
    	eduBoardDAO.boardAdmUpdate(boardAdmVO);
	}

	@Override
	public void boardAdmRemove(EduBoardVO boardAdmVO) throws Exception {
		eduBoardDAO.boardAdmRemove(boardAdmVO);
	}


	
}
