package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.com.cmm.service.impl.NaksinuriOriginalFileManageDAO;
import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;

@Service("NaksinuriEventService")
public class NaksinuriEventServiceimpl implements NaksinuriEventService {
	@Resource(name = "NaksinuriEventDAO")
	private NaksinuriEventDAO naksinuriEventDAO; 
	
	@Resource(name="NaksinuriEventService")
	private NaksinuriEventService service;
	
	@Resource(name = "NaksinuriOriginalFileManageDAO")
	private NaksinuriOriginalFileManageDAO fileMngDAO;
	
	@Override
	public List<NaksinuriEventVO> getEventList(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.getEventList(eventVO);
	}

	

	@Override
	public void deleteEvent(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.deleteEvent(eventVO);
		
	}

	@Override
	public void uphit(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.uphit(eventVO);
		
	}

	@Override
	public void like_update(NaksinuriEventVO eventVO) {
		naksinuriEventDAO.like_update(eventVO);
		
	}

	@Override
	public NaksinuriEventVO event_findCorp(NaksinuriEventVO eventVO) {
		return naksinuriEventDAO.event_findCorp(eventVO);
	}

	@Override
	public List<NaksinuriEventVO> getendEventList(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.getendEventList(eventVO);
	}

	/**
	 * 이벤트 당첨자 발표 게시리스트 출력
	 */
	@Override
	public List<NaksinuriEventVO> getancEventList(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.getancEventList(eventVO);
	}

	@Override
	public NaksinuriEventVO event_next(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.select_next(eventVO);
	}


	@Override
	public NaksinuriEventVO event_prev(NaksinuriEventVO eventVO) {
		return naksinuriEventDAO.select_prev(eventVO);
	}

	@Override
	public List<NaksinuriEventVO> getEventView(NaksinuriEventVO eventVO) {
		return naksinuriEventDAO.getEventView(eventVO);
	}

	@Override
	public List<NaksinuriEventVO> evn_file(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.evn_file(eventVO);
	}

	@Override
	public void eco_insert(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.eco_insert(eventVO);
		
	}
	
	@Override
	public void eco_update(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.eco_update(eventVO);
		
	}
	
	@Override
	public void eco_delete(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.eco_delete(eventVO);
		
	}




	@Override
	public List<NaksinuriEventVO> select_event_comment(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.select_event_comment(eventVO);
	}

	@Override
	public List<NaksinuriEventVO> select_event_post(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.select_event_post(eventVO);
	}

	@Override
	public List<NaksinuriEventVO> select_gongmo_event_post(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.select_gongmo_event_post(eventVO);
	}

	@Override
	public List<NaksinuriEventVO> select_endevent_post(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.select_endevent_post(eventVO);
	}

	
	@Override
	public List<NaksinuriEventVO> getAllEventList(NaksinuriEventVO eventVO) throws Exception{
		return naksinuriEventDAO.getAllEventList(eventVO);
	}

	@Override
	public void insert_data(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.insert_data(eventVO);
	}

	@Override
	public void update_data(NaksinuriEventVO eventVO) {
		naksinuriEventDAO.update_data(eventVO);
		
	}



	@Override
	public NaksinuriEventVO esimg(NaksinuriEventVO eventVO) {
		return naksinuriEventDAO.esimg(eventVO);
	}



	@Override
	public NaksinuriEventVO emimg(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.emimg(eventVO);
	}



	@Override
	public List<NaksinuriEventVO> ea_file(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.ea_file(eventVO);
	}



	@Override
	public NaksinuriEventVO ecopass_find(NaksinuriEventVO eventVO) {
		return naksinuriEventDAO.ecopass_find(eventVO);
	}



	@Override
	public void trashEvent(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.trashEvent(eventVO);
		
	}



	@Override
	public List<NaksinuriEventVO> getAllEventTrash(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.getAllEventTrash(eventVO);
	}



	@Override
	public List<NaksinuriEventVO> getEventTrash(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.getEventTrash(eventVO);
	}



	@Override
	public List<NaksinuriEventVO> getendEventTrash(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.getendEventTrash(eventVO);
	}



	@Override
	public List<NaksinuriEventVO> getancEventTrash(NaksinuriEventVO eventVO) throws Exception {
		return naksinuriEventDAO.getancEventTrash(eventVO);
	}



	@Override
	public void restoreEvent(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.restoreEvent(eventVO);
		
	}



	  public List<NaksinuriEventVO> getEventgongmoList(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.getEventgongmoList(eventVO);
	  }

	  public List<NaksinuriEventVO> getEventgongmoendList(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.getEventgongmoendList(eventVO);
	  }

	  public List<NaksinuriEventVO> getancEventgongmoList(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.getEventgongmoancList(eventVO);
	  }

	  public List<NaksinuriEventVO> getAllEventgongmoList(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.getAllEventgongmoList(eventVO);
	  }

	  public List<NaksinuriEventVO> getendEventgongmoList(NaksinuriEventVO eventVO) throws Exception
	  {
	    return this.naksinuriEventDAO.getendEventgongmoList(eventVO);
	  }

	  public List<NaksinuriEventVO> getAllEventgongmoTrash(NaksinuriEventVO eventVO) throws Exception
	  {
	    return this.naksinuriEventDAO.getAllEventgongmoTrash(eventVO);
	  }

	  public List<NaksinuriEventVO> getEventgongmoTrash(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.getEventgongmoTrash(eventVO);
	  }

	  public List<NaksinuriEventVO> getendEventgongmoTrash(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.getendEventgongmoTrash(eventVO);
	  }

	  public List<NaksinuriEventVO> getancEventgongmoTrash(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.getancEventgongmoTrash(eventVO);
	  }

	  public void insert_data_gongmo(NaksinuriEventVO eventVO)
	  {
	    this.naksinuriEventDAO.insert_data_gongmo(eventVO);
	  }

	  public NaksinuriEventVO event_gongmo_findCorp(NaksinuriEventVO eventVO)
	  {
	    return this.naksinuriEventDAO.event_gongmo_findCorp(eventVO);
	  }

	  public NaksinuriEventVO esimg_gongmo(NaksinuriEventVO eventVO)
	  {
	    return this.naksinuriEventDAO.esimg_gongmo(eventVO);
	  }

	  public NaksinuriEventVO emimg_gongmo(NaksinuriEventVO eventVO)
	  {
	    return this.naksinuriEventDAO.emimg_gongmo(eventVO);
	  }

	  public List<NaksinuriEventVO> ea_file_gongmo(NaksinuriEventVO eventVO)
	  {
	    return this.naksinuriEventDAO.ea_file_gongmo(eventVO);
	  }

	  public void deleteEvent_gongmo(NaksinuriEventVO eventVO)
	  {
	    this.naksinuriEventDAO.deleteEvent_gongmo(eventVO);
	  }

	  public void restoreEvent_gongmo(NaksinuriEventVO eventVO)
	  {
	    this.naksinuriEventDAO.restoreEvent_gongmo(eventVO);
	  }

	  public void trashEvent_gongmo(NaksinuriEventVO eventVO)
	  {
	    this.naksinuriEventDAO.trashEvent_gongmo(eventVO);
	  }

	  public List<NaksinuriEventVO> getEvent_gongmoView(NaksinuriEventVO eventVO)
	  {
	    return this.naksinuriEventDAO.getEvent_gongmoView(eventVO);
	  }

	  public NaksinuriEventVO event_next_gongmo(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.select_next_gongmo(eventVO);
	  }

	  public NaksinuriEventVO event_prev_gongmo(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    return this.naksinuriEventDAO.select_prev_gongmo(eventVO);
	  }

	  public void like_update_gongmo(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    this.naksinuriEventDAO.like_update_gongmo(eventVO);
	  }

	  public void uphit_gongmo(NaksinuriEventVO eventVO)
	    throws Exception
	  {
	    this.naksinuriEventDAO.uphit_gongmo(eventVO);
	  }



	public void update_data_gongmo(NaksinuriEventVO eventVO) throws Exception {
		naksinuriEventDAO.update_data_gongmo(eventVO);
	}














	

	

	

	

}
