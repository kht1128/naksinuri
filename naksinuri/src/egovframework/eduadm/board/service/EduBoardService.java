package egovframework.eduadm.board.service;

import java.util.List;

public interface EduBoardService {

	public List<EduBoardVO> boardAdmList(EduBoardVO userSearchVO) throws Exception;
	
    public int boardAdmListTotCnt(EduBoardVO userSearchVO) throws Exception;
    
    public EduBoardVO boardAdmView(EduBoardVO boardAdmVO) throws Exception;
    
    public String boardAdmInsert(EduBoardVO boardAdmVO) throws Exception;
    
    public String boardAdmReplyinsert(EduBoardVO boardAdmVO) throws Exception;
    
    public void boardAdmDelete(EduBoardVO boardAdmVO) throws Exception;
    
    public void boardAdmUpdate(EduBoardVO boardAdmVO) throws Exception;

	public void boardAdmRemove(EduBoardVO boardAdmVO) throws Exception;
    
}
