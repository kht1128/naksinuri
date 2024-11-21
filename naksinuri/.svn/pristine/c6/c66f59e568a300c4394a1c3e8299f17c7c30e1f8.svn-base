package egovframework.eduadm.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.eduadm.board.service.EduBoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("eduBoardDAO")
public class EduBoardDAO extends EgovAbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<EduBoardVO> boardAdmList(EduBoardVO userSearchVO){
        return (List<EduBoardVO>) list("EduAdmBoard.boardAdmList", userSearchVO);
    }
	 
    public int boardAdmListTotCnt(EduBoardVO userSearchVO) {
        return (Integer)select("EduAdmBoard.boardAdmListTotCnt", userSearchVO);
    }     
    
    @SuppressWarnings("unchecked")
	public EduBoardVO boardAdmView(EduBoardVO boardAdmVO){
    	update("EduAdmBoard.boardViewCount", boardAdmVO);
        return (EduBoardVO) select("EduAdmBoard.boardAdmView", boardAdmVO);
    }
    
    public String boardAdmInsert(EduBoardVO boardAdmVO){
        return (String)insert("EduAdmBoard.boardAdmInsert", boardAdmVO);
    }
    
    public String boardAdmReplyinsert(EduBoardVO boardAdmVO){
        return (String)insert("EduAdmBoard.boardAdmReplyinsert", boardAdmVO);
    }
    
    public void boardAdmDelete(EduBoardVO boardAdmVO){
    	update("EduAdmBoard.boardAdmDelete",boardAdmVO);   
    }
    
    public void boardAdmUpdate(EduBoardVO boardAdmVO){
        update("EduAdmBoard.boardAdmUpdate",boardAdmVO);
    }

	public void boardAdmRemove(EduBoardVO boardAdmVO) {
		delete("EduAdmBoard.boardAdmRemove", boardAdmVO);
	}
    

}
