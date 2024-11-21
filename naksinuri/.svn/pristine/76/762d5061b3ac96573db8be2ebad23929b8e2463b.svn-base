package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriNewsDAO")
public class NaksinuriNewsDAO extends EgovAbstractDAO {

	public List<NaksinuriNewsVO> getNewsList(NaksinuriNewsVO newsVO) {
		return (List<NaksinuriNewsVO>) list("news_list",newsVO);
	}
	
	public List<NaksinuriNewsVO> getNewsList_admin(NaksinuriNewsVO newsVO) {
		return (List<NaksinuriNewsVO>) list("news_list_admin",newsVO);
	}
	
	public void deleteNews(NaksinuriNewsVO newsVO) {
		delete("deleteNews",newsVO);
		
	}

	public void insertNews(NaksinuriNewsVO newsVO) {
		insert("insertNews",newsVO);
		
	}

	public void updateNews(NaksinuriNewsVO newsVO) {
		update("updateNews",newsVO);
		
	}

	public NaksinuriNewsVO board_find(NaksinuriNewsVO newsVO) {
		return (NaksinuriNewsVO) select("news_findCorp",newsVO);
	}

	public List<NaksinuriNewsVO> select_main_news(NaksinuriNewsVO newsVO) {
		return (List<NaksinuriNewsVO>) list("main_news",newsVO);
	}

	public void gotrash_boardlist(NaksinuriNewsVO newsVO) {
		update("gotrash_newslist",newsVO);
		
	}

	public List<NaksinuriNewsVO> getNewstrash_admin(NaksinuriNewsVO newsVO) {
		return (List<NaksinuriNewsVO>) list("news_trash_admin",newsVO);
	}
	

}
