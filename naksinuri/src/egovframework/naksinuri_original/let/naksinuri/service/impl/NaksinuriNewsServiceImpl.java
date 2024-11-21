package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriNewsVO;

@Service("NaksinuriNewsService")
public class NaksinuriNewsServiceImpl implements NaksinuriNewsService {
	@Resource(name = "NaksinuriNewsDAO")
	private NaksinuriNewsDAO naksinuriNewsDAO;
	
	
	@Override
	public List<NaksinuriNewsVO> getNewsList(NaksinuriNewsVO newsVO) throws Exception {
		return naksinuriNewsDAO.getNewsList(newsVO);
	}
	
	@Override
	public List<NaksinuriNewsVO> getNewsList_admin(NaksinuriNewsVO newsVO) throws Exception  {
		return naksinuriNewsDAO.getNewsList_admin(newsVO);
	}
	
	@Override
	public void deleteNews(NaksinuriNewsVO newsVO) throws Exception {
		naksinuriNewsDAO.deleteNews(newsVO);
		
	}

	@Override
	public void insert_data(NaksinuriNewsVO newsVO) throws Exception {
		naksinuriNewsDAO.insertNews(newsVO);
		
	}

	@Override
	public void update_data(NaksinuriNewsVO newsVO) throws Exception {
		naksinuriNewsDAO.updateNews(newsVO);
		
	}

	@Override
	public NaksinuriNewsVO board_findCorp(NaksinuriNewsVO newsVO) {
		return naksinuriNewsDAO.board_find(newsVO);
		
	}

	@Override
	public void delete_boardlist(NaksinuriNewsVO newsVO) {
		naksinuriNewsDAO.deleteNews(newsVO);
		
	}

	@Override
	public List<NaksinuriNewsVO> select_main_news(NaksinuriNewsVO newsVO) {
		return naksinuriNewsDAO.select_main_news(newsVO);
	}

	@Override
	public void gotrash_boardlist(NaksinuriNewsVO newsVO) throws Exception {
		naksinuriNewsDAO.gotrash_boardlist(newsVO);
		
	}

	@Override
	public List<NaksinuriNewsVO> getNewstrash_admin(NaksinuriNewsVO newsVO) throws Exception {
		return naksinuriNewsDAO.getNewstrash_admin(newsVO);
	}

	

}
