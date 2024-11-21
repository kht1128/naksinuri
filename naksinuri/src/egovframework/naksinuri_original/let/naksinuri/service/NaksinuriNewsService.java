package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;

public interface NaksinuriNewsService {
	
	/**뉴스 리스트 불러오기*/
	public List<NaksinuriNewsVO> getNewsList(NaksinuriNewsVO newsVO) throws Exception;
	
	/**뉴스 삭제하기 상태값 0에서 1로 변경*/
	public void deleteNews(NaksinuriNewsVO newsVO) throws Exception;
	
	
	public void insert_data(NaksinuriNewsVO newsVO) throws Exception;
	

	public void update_data(NaksinuriNewsVO eventVO) throws Exception;

	public NaksinuriNewsVO board_findCorp(NaksinuriNewsVO newsVO) throws Exception;

	public void delete_boardlist(NaksinuriNewsVO newsVO) throws Exception;

	public List<NaksinuriNewsVO> getNewsList_admin(NaksinuriNewsVO newsVO) throws Exception ;

	public List<NaksinuriNewsVO> select_main_news(NaksinuriNewsVO newsVO) throws Exception; 

	public void gotrash_boardlist(NaksinuriNewsVO newsVO) throws Exception;

	public List<NaksinuriNewsVO> getNewstrash_admin(NaksinuriNewsVO newsVO) throws Exception;
}
