package egovframework.naksinuri_original.let.naksinuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsLogmmsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsLogsmsVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsService;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriSmsVO;
import egovframework.naksinuri_original.let.naksinuri.service.SmsMngrVO;

@Service("NaksinuriSmsService")
public class NaksinuriSmsServiceImpl implements NaksinuriSmsService {

	@Resource(name = "NaksinuriSmsDAO")
	private NaksinuriSmsDAO dao;
	
	@Override
	public List<NaksinuriSmsVO> getReadySmsQueueData() {
		return dao.getReadySmsQueueData();
	}
	
	@Override
	public List<NaksinuriSmsVO> getCheckSmsQueueData() {
		return dao.getCheckSmsQueueData();
	}

	@Override
	public boolean sendSms(NaksinuriSmsVO data) throws Exception {
		return dao.sendSms(data);
	}

	@Override
	public boolean sendMms(NaksinuriSmsVO data) throws Exception {
		return dao.sendMms(data);
	}

	@Override
	public void successSmsQueueData(NaksinuriSmsVO data) throws Exception {
		dao.successSmsQueueData(data);
	}

	@Override
	public void failSmsQueueData(NaksinuriSmsVO data) throws Exception {
		dao.failSmsQueueData(data);		
	}

	@Override
	public NaksinuriSmsLogsmsVO getSmsSendLogData(NaksinuriSmsVO data) throws Exception {
		return dao.getSmsSendLogData(data);
	}

	@Override
	public NaksinuriSmsLogmmsVO getMmsSendLogData(NaksinuriSmsVO data) throws Exception {
		return dao.getMmsSendLogData(data);
	}

	@Override
	public void updateSmsQueueInfo(NaksinuriSmsVO data) throws Exception {
		dao.updateSmsQueueInfo(data);
	}

	@Override
	public boolean sendToSmsMessage(NaksinuriSmsVO data) throws Exception {
		return dao.sendToSmsMessage(data);
	}
	


	@Override
	public void refreshSmsQueueInfo(NaksinuriSmsVO data) throws Exception {
		dao.refreshSmsQueueInfo(data);
	}

	
	@Override
	public void sendToCongressCustomer(String phone, String bo_title, String depositAmount, String depositName,
			String cg_account_name, String cg_account, String adminPhone, int mbr_count, String ip) throws Exception {
	      
		NaksinuriSmsVO smsData = new NaksinuriSmsVO();
		smsData.setMsg_type("MMS");
		smsData.setMsg("["+ bo_title + "] 참가자신청 대기자 등록이 완료되었습니다.\n 총 참가인원 : "+mbr_count+" 명에 대한 " + depositAmount + "원을 "+ depositName +" 님 성함으로 "+cg_account+"("+cg_account_name+") 입금 해주셔야 확인 후 신청이 최종 완료됩니다.\n" + "문의전화 : " + adminPhone);
		smsData.setSubmsg("낚시누리 알림");
		smsData.setImg_cnt(0);
		smsData.setImg_path("");
		smsData.setM_point(0);
		smsData.setC_point(0);
		smsData.setApikey("");//사용안할듯.
		smsData.setRstkey(smsData.getUniqRstKey());
		smsData.setR_phone(phone);
		smsData.setS_phone(smsData.getSmsSendNumber());
		smsData.setIp(ip);
		sendToSmsMessage(smsData);
	    sendMms(smsData);
	}

	@Override
	public void sendToCongressAdmin(String phone, String bo_title, String customers, int mbr_count, String ip) throws Exception {
		
		NaksinuriSmsVO smsData = new NaksinuriSmsVO();
	    smsData.setMsg_type("MMS");
	    smsData.setMsg("[" + bo_title + "] 총 참가신청인원 : "+mbr_count+" 명 ( " + customers + " ) 님이 참가자 신청을 하셨습니다.\n낚시누리 사이트에 접속하여 정보를 확인해주세요.");
	    smsData.setSubmsg("낚시누리 알림");
	    smsData.setImg_cnt(0);
	    smsData.setImg_path("");
	    smsData.setM_point(0);
	    smsData.setC_point(0);
	    smsData.setApikey("");//사용안할듯.
	    smsData.setRstkey(smsData.getUniqRstKey());
	    smsData.setR_phone(phone);
	    smsData.setS_phone(smsData.getSmsSendNumber());
	    smsData.setIp(ip);
	    sendToSmsMessage(smsData);
	    sendMms(smsData);
	}
	
	@Override
	public List<SmsMngrVO> group_list(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.group_list(smsMngrVO);
	}
	
	@Override
	public SmsMngrVO group_select(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.group_select(smsMngrVO);
	}
	
	@Override
	public boolean group_insert(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.group_insert(smsMngrVO);
	}
	
	@Override
	public boolean group_update(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.group_update(smsMngrVO);
	}
	
	@Override
	public boolean group_delete(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.group_delete(smsMngrVO);
	}
	
	
	@Override
	public List<SmsMngrVO> contact_list(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.contact_list(smsMngrVO);
	}
	
	@Override
	public SmsMngrVO contact_select(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.contact_select(smsMngrVO);
	}
	
	
	
	@Override
	public boolean contact_insert(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.contact_insert(smsMngrVO);
	}
	
	@Override
	public boolean contact_update(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.contact_update(smsMngrVO);
	}
	
	@Override
	public boolean contact_delete(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.contact_delete(smsMngrVO);
	}
	
	@Override
	public List<SmsMngrVO> ment_list(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.ment_list(smsMngrVO);
	}
	
	@Override
	public boolean ment_insert(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.ment_insert(smsMngrVO);
	}
	
	@Override
	public boolean ment_update(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.ment_update(smsMngrVO);
	}
	
	@Override
	public boolean ment_delete(SmsMngrVO smsMngrVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.ment_delete(smsMngrVO);
	}
	
	@Override
	public boolean sendToSmsMngr(NaksinuriSmsVO data) throws Exception {		
		String msg = data.getMsg();
		
		int msg_length = msg.getBytes().length;
		
		if ( msg_length > 79) {
			data.setMsg_type("MMS");
			sendMms(data);
			
		} else {
			data.setMsg_type("SMS");
			sendSms(data);
			
		}
		
		
	
		
		return dao.sendToSmsMessage(data);
			
	}
	
	
	
	
	
	
	
	

}
