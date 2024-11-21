package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsLogmmsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsLogsmsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsVO;
import egovframework.naksinuri_original.let.naksinuri.service.SmsMngrVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;



@Repository("NaksinuriSmsDAO")
public class NaksinuriSmsDAO extends EgovAbstractDAO {
	//데이터베이스 쿼리 연동

	public List<NaksinuriSmsVO> getReadySmsQueueData() {
		List<NaksinuriSmsVO> list = (List<NaksinuriSmsVO>) list("get_ready_sms_data");
		if(!list.isEmpty()) {
			List<Integer> midList = new LinkedList();
			for(NaksinuriSmsVO sms : list) {
				midList.add(sms.getMid());
			}
			update("update_ready_sms_data",midList);
		}
		return list;
	}
	
	public List<NaksinuriSmsVO> getCheckSmsQueueData() {
		List<NaksinuriSmsVO> list = (List<NaksinuriSmsVO>) list("get_check_sms_data");
		if(!list.isEmpty()) {
			List<Integer> midList = new LinkedList();
			for(NaksinuriSmsVO sms : list) {
				midList.add(sms.getMid());
			}
			update("update_ready_sms_data",midList);
		}
		return list;
	}

	public boolean sendSms(NaksinuriSmsVO data) {
		try {
			insert("insert_sms",data);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public boolean sendMms(NaksinuriSmsVO data) {
		try {
			insert("insert_mms",data);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public void successSmsQueueData(NaksinuriSmsVO data) {
		delete("success_sms_data",data);
	}

	public void failSmsQueueData(NaksinuriSmsVO data) {
		update("faile_sms_data",data);
	}

	public NaksinuriSmsLogsmsVO getSmsSendLogData(NaksinuriSmsVO data) {
		return (NaksinuriSmsLogsmsVO) select("get_sms_log_data",data);
	}

	public NaksinuriSmsLogmmsVO getMmsSendLogData(NaksinuriSmsVO data) {
		return (NaksinuriSmsLogmmsVO) select("get_mms_log_data",data);
	}

	public void updateSmsQueueInfo(NaksinuriSmsVO data) {
		update("update_info_sms_data",data);
	}

	
	public boolean sendToSmsMessage(NaksinuriSmsVO data) {
		try {
			insert("insert_sms_queue",data);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public void refreshSmsQueueInfo(NaksinuriSmsVO data) {
		update("update_refresh_sms_data",data);
	}
	
	public List<SmsMngrVO> group_list(SmsMngrVO smsMngrVO){
		List<SmsMngrVO> list = (List<SmsMngrVO>) list("selectGroup_S", smsMngrVO);
		
        return list;
    }
	
	public SmsMngrVO group_select(SmsMngrVO smsMngrVO){
		SmsMngrVO list = (SmsMngrVO) select("selectGroup_Single", smsMngrVO);
		
        return list;
    }
	
	public boolean group_insert(SmsMngrVO smsMngrVO){
		insert("insertGroup_S", smsMngrVO);
		
        return true;
    }
	
	public boolean group_update(SmsMngrVO smsMngrVO){
		update("updateGroup_S", smsMngrVO);
		
        return true;
    }
	
	public boolean group_delete(SmsMngrVO smsMngrVO){
		delete("deleteGroup_S", smsMngrVO);
		
        return true;
    }
	
	public List<SmsMngrVO> contact_list(SmsMngrVO smsMngrVO){
		List<SmsMngrVO> list = (List<SmsMngrVO>) list("selectContact_S", smsMngrVO);
		
        return list;
    }
	
	public SmsMngrVO contact_select(SmsMngrVO smsMngrVO){
		SmsMngrVO list = (SmsMngrVO) select("selectContact_Single", smsMngrVO);
		
        return list;
    }
	
	
	
	public boolean contact_insert(SmsMngrVO smsMngrVO){
		insert("insertContact_S", smsMngrVO);
		
        return true;
    }
	
	public boolean contact_update(SmsMngrVO smsMngrVO){
		update("updateContact_S", smsMngrVO);
		
        return true;
    }
	
	public boolean contact_delete(SmsMngrVO smsMngrVO){
		delete("deleteContact_S", smsMngrVO);
		
        return true;
    }
	
	public List<SmsMngrVO> ment_list(SmsMngrVO smsMngrVO){
		List<SmsMngrVO> list = (List<SmsMngrVO>) list("selectMent_S", smsMngrVO);
		
        return list;
    }
	
	public boolean ment_insert(SmsMngrVO smsMngrVO){
		insert("insertMent_S", smsMngrVO);
		
        return true;
    }
	
	public boolean ment_update(SmsMngrVO smsMngrVO){
		update("updateMent_S", smsMngrVO);
		
        return true;
    }
	
	public boolean ment_delete(SmsMngrVO smsMngrVO){
		delete("deleteMent_S", smsMngrVO);
		
        return true;
    }
	
	
}