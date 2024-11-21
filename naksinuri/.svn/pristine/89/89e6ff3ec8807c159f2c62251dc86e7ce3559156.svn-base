package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.BoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriEventVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("NaksinuriEventDAO")
public class NaksinuriEventDAO extends EgovAbstractDAO{
	
	public List<NaksinuriEventVO> getEventList(NaksinuriEventVO eventVO){
		return (List<NaksinuriEventVO>) list("event_list", eventVO);
	}
	
	public void insert_data(NaksinuriEventVO eventVO) {
		insert("event_insert",eventVO);
	}

	
	public void update_data(NaksinuriEventVO eventVO){
		 update("updateEvent",eventVO);
	}
	
	
//	이벤트 게시글 삭제
	public void deleteEvent(NaksinuriEventVO eventVO){
		delete("deleteEvent",eventVO);
	}
	
	public void uphit(NaksinuriEventVO eventVO) {
		
		update("uphit",eventVO);
		 
	}
	
	public void like_update(NaksinuriEventVO eventVO) {
		update("evnlike_update",eventVO);
		
	}

	public NaksinuriEventVO event_findCorp(NaksinuriEventVO eventVO) {
		 return (NaksinuriEventVO) select("event_findCorp", eventVO);
	}

	public List<NaksinuriEventVO> getendEventList(NaksinuriEventVO eventVO) {
		return (List<NaksinuriEventVO>) list("endevent_list", eventVO);
	}

	public List<NaksinuriEventVO> getancEventList(NaksinuriEventVO eventVO) {
		return (List<NaksinuriEventVO>) list("ancevent_list", eventVO);
	}

	public List<NaksinuriEventVO> getAllEventList(NaksinuriEventVO eventVO) {
		return (List<NaksinuriEventVO>) list("allevent_list",eventVO);
	}
	
	public NaksinuriEventVO select_next(NaksinuriEventVO eventVO) {
		 return (NaksinuriEventVO) select("event_next", eventVO);
	
	}

	public NaksinuriEventVO select_prev(NaksinuriEventVO eventVO) {
		 return (NaksinuriEventVO) select("event_prev", eventVO);
	}

	public List<NaksinuriEventVO> getEventView(NaksinuriEventVO eventVO) {
		 return (List<NaksinuriEventVO>) list("getevent_view",eventVO);
	}

		public List<NaksinuriEventVO> evn_file(NaksinuriEventVO eventVO){
			return (List<NaksinuriEventVO>) list("evn_file",eventVO);
    }

		public void eco_insert(NaksinuriEventVO eventVO) {
			insert("eco_insert",eventVO);
		}

		public void eco_update(NaksinuriEventVO eventVO) {
			update("eco_update",eventVO);
			
		}
		  		
		public void eco_delete(NaksinuriEventVO eventVO) {
			delete("eco_delete",eventVO);
			
		}
		public List<NaksinuriEventVO> select_event_comment(NaksinuriEventVO eventVO)  {
			return (List<NaksinuriEventVO>) list("select_event_comment", eventVO);
		}

		public List<NaksinuriEventVO> select_event_post(NaksinuriEventVO eventVO) {
			return (List<NaksinuriEventVO>) list("select_event_post",eventVO);
		}

		public List<NaksinuriEventVO> select_endevent_post(NaksinuriEventVO eventVO) {
			return (List<NaksinuriEventVO>) list("select_endevent_post",eventVO);
		}
		
		public NaksinuriEventVO esimg(NaksinuriEventVO eventVO) {
			return (NaksinuriEventVO) select("esimg",eventVO);
		}
		
		 public NaksinuriEventVO emimg(NaksinuriEventVO eventVO){
		    return (NaksinuriEventVO) select("emimg",eventVO);
		    }
		  
		 public List<NaksinuriEventVO> ea_file(NaksinuriEventVO eventVO){
			  return (List<NaksinuriEventVO>) list("ea_file",eventVO);
		  }

		public NaksinuriEventVO ecopass_find(NaksinuriEventVO eventVO) {
			 return (NaksinuriEventVO) select ("ecopass_find", eventVO);
		}

		public void trashEvent(NaksinuriEventVO eventVO) {
			update("gotrash_event", eventVO);
			
		}

		public List<NaksinuriEventVO> getAllEventTrash(NaksinuriEventVO eventVO) {
			return (List<NaksinuriEventVO>) list("allevent_trash",eventVO); 
		}

		public List<NaksinuriEventVO> getEventTrash(NaksinuriEventVO eventVO) {
			return (List<NaksinuriEventVO>) list("event_trash",eventVO);
		}

		public List<NaksinuriEventVO> getendEventTrash(NaksinuriEventVO eventVO) {
			return (List<NaksinuriEventVO>) list("endevent_trash",eventVO);
		}

		public List<NaksinuriEventVO> getancEventTrash(NaksinuriEventVO eventVO) {
			return (List<NaksinuriEventVO>) list("ancevent_trash",eventVO);
		}

		public void restoreEvent(NaksinuriEventVO eventVO) {
			update("restore_event",eventVO);
			
		}


		public List<NaksinuriEventVO> getEventgongmoList(NaksinuriEventVO eventVO)
		  {
		    return (List<NaksinuriEventVO>) list("event_gongmo_list", eventVO);
		  }

		  public List<NaksinuriEventVO> getEventgongmoendList(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("endevent_gongmo_list", eventVO);
		  }

		  public List<NaksinuriEventVO> getEventgongmoancList(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("ancevent_gongmo_list", eventVO);
		  }

		  public List<NaksinuriEventVO> getAllEventgongmoList(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("allevent_gongmo_list", eventVO);
		  }

		  public List<NaksinuriEventVO> getendEventgongmoList(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("endevent_gongmo_list", eventVO);
		  }

		  public List<NaksinuriEventVO> getAllEventgongmoTrash(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("allevent_gongmo_trash", eventVO);
		  }

		  public List<NaksinuriEventVO> getEventgongmoTrash(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("event_gongmo_trash", eventVO);
		  }

		  public List<NaksinuriEventVO> getendEventgongmoTrash(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("endevent_gongmo_trash", eventVO);
		  }

		  public List<NaksinuriEventVO> getancEventgongmoTrash(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("ancevent_gongmo_trash", eventVO);
		  }

		  public void insert_data_gongmo(NaksinuriEventVO eventVO) {
		    insert("event_gongmo_insert", eventVO);
		  }

		  public NaksinuriEventVO event_gongmo_findCorp(NaksinuriEventVO eventVO)
		  {
		    return (NaksinuriEventVO)select("event_gongmo_findCorp", eventVO);
		  }

		  public NaksinuriEventVO esimg_gongmo(NaksinuriEventVO eventVO) {
		    return (NaksinuriEventVO)select("esimg_gongmo", eventVO);
		  }

		  public NaksinuriEventVO emimg_gongmo(NaksinuriEventVO eventVO) {
		    return (NaksinuriEventVO)select("emimg_gongmo", eventVO);
		  }

		  public List<NaksinuriEventVO> ea_file_gongmo(NaksinuriEventVO eventVO) {
		    return (List<NaksinuriEventVO>) list("ea_file_gongmo", eventVO);
		  }

		  public void deleteEvent_gongmo(NaksinuriEventVO eventVO) {
		    delete("deleteEvent_gongmo", eventVO);
		  }

		  public void restoreEvent_gongmo(NaksinuriEventVO eventVO)
		  {
		    update("restore_event_gongmo", eventVO);
		  }

		  public void trashEvent_gongmo(NaksinuriEventVO eventVO)
		  {
		    update("gotrash_event_gongmo", eventVO);
		  }

		  public List<NaksinuriEventVO> getEvent_gongmoView(NaksinuriEventVO eventVO)
		  {
		    return (List<NaksinuriEventVO>) list("getevent_gongmoview", eventVO);
		  }

		  public NaksinuriEventVO select_next_gongmo(NaksinuriEventVO eventVO) {
		    return (NaksinuriEventVO)select("event_next_gongmo", eventVO);
		  }

		  public NaksinuriEventVO select_prev_gongmo(NaksinuriEventVO eventVO) {
		    return (NaksinuriEventVO)select("event_prev_gongmo", eventVO);
		  }

		  public void like_update_gongmo(NaksinuriEventVO eventVO) {
		    update("gongmolike_update", eventVO);
		  }

		  public void uphit_gongmo(NaksinuriEventVO eventVO)
		  {
		    update("uphit_gongmo", eventVO);
		  }

		public void update_data_gongmo(NaksinuriEventVO eventVO) {
			update("event_gongmo_update",eventVO);
			
		}
		
		public List<NaksinuriEventVO> select_gongmo_event_post(NaksinuriEventVO eventVO) {
			return (List<NaksinuriEventVO>) list("select_gongmo_event_post",eventVO);
		}


	
		    
	
}
