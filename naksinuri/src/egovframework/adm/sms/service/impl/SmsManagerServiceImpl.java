package egovframework.adm.sms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsMentVO;
import egovframework.adm.sms.service.SmsSendVO;

@Service("smsManagerService")
public class SmsManagerServiceImpl implements SmsManagerService {

	@Resource(name = "smsManagerDAO")
	private SmsManagerDAO dao;
	
	
	@Override
	public boolean sendSms(SmsSendVO data) throws Exception {
		return dao.sendSms(data);
	}

	@Override
	public boolean sendMms(SmsSendVO data) throws Exception {
		return dao.sendMms(data);
	}

	
	@Override
	public String sendToMessage(SmsSendVO data) throws Exception {
		StringBuilder rstMsg = new StringBuilder();
		data.setRSTKEY(data.getUniqRstKey());
		data.setAPIKEY(data.getRSTKEY());
		rstMsg.append("(문자발송처리:"+data.getRSTKEY());
		rstMsg.append("|발신번호:"+data.getS_PHONE());
		rstMsg.append("|수신번호:"+data.getR_PHONE());
		try {
			String msg = data.getMSG().trim();
			int msg_length = 0;
			if(msg.length() > 0) {
				msg_length = msg.getBytes().length;
			}
			rstMsg.append("|문자내용크기:"+msg_length+"byte");
			boolean isOnlyMMS = false;
			if(data.getIMG_CNT() > 0) {
				rstMsg.append("|이미지첨부("+data.getIMG_CNT()+"):"+data.getIMG_PATH());
				isOnlyMMS = true;
			} else {
				rstMsg.append("|이미지첨부없음");
			}
			if ( msg_length > 79 || isOnlyMMS ) {
				rstMsg.append("|문자타입:MMS");
				data.setMSG_TYPE("MMS");
				sendMms(data);
			} else {
							
				rstMsg.append("|문자타입:SMS");
				data.setMSG_TYPE("SMS");
				sendSms(data);
			}
			dao.set_sms_queue_reg(data);
		} catch(Exception e) {
			rstMsg.append("|실패:오류("+e.toString()+"))");
		}
		rstMsg.append(")");
		return rstMsg.toString();
			
	}

	@Override
	public List<SmsMentVO> get_sms_ment_list(SmsMentVO smsMentVO) throws Exception {
		return (List<SmsMentVO>)dao.get_sms_ment_list(smsMentVO);
	}

	@Override
	public int get_sms_ment_list_totcnt(SmsMentVO smsMentVO) throws Exception {
		return (int)dao.get_sms_ment_list_totcnt(smsMentVO);
	}

	@Override
	public SmsMentVO get_sms_ment_info(SmsMentVO smsMentVO) throws Exception {
		return (SmsMentVO)dao.get_sms_ment_info(smsMentVO);
	}

	@Override
	public void remove_sms_ment(SmsMentVO smsMentVO) throws Exception {
		dao.remove_sms_ment(smsMentVO);
	}

	@Override
	public void del_sms_ment(SmsMentVO smsMentVO) throws Exception {
		dao.del_sms_ment(smsMentVO);
	}

	@Override
	public void set_sms_ment_info_mod(SmsMentVO smsMentVO) throws Exception {
		dao.set_sms_ment_info_mod(smsMentVO);
	}

	@Override
	public String set_sms_ment_info_reg(SmsMentVO smsMentVO) throws Exception {
		return dao.set_sms_ment_info_reg(smsMentVO);
	}

	@Override
	public List<SmsMentVO> get_sms_resve_list(SmsSendVO smsSendVO) throws Exception {
		return (List<SmsMentVO>)dao.get_sms_resve_list(smsSendVO);
	}

	@Override
	public int get_sms_resve_list_totcnt(SmsSendVO smsSendVO) throws Exception {
		return (int)dao.get_sms_resve_list_totcnt(smsSendVO);
	}

	@Override
	public SmsSendVO get_sms_resve_info(SmsSendVO smsSendVO) throws Exception {
		return (SmsSendVO)dao.get_sms_resve_info(smsSendVO);
	}

	@Override
	public void remove_sms_resve(SmsSendVO smsSendVO) throws Exception {
		dao.remove_sms_resve(smsSendVO);
	}

	@Override
	public void del_sms_resve(SmsSendVO smsSendVO) throws Exception {
		dao.del_sms_resve(smsSendVO);
	}

	@Override
	public void set_sms_resve_info_mod(SmsSendVO smsSendVO) throws Exception {
		dao.set_sms_resve_info_mod(smsSendVO);
	}

	@Override
	public List<SmsSendVO> get_sms_log_list(SmsSendVO smsSendVO) throws Exception {
		return dao.get_sms_log_list(smsSendVO);
	}

	@Override
	public int get_sms_log_list_totcnt(SmsSendVO smsSendVO) throws Exception {
		return (int)dao.get_sms_log_list_totcnt(smsSendVO);
	}

	@Override
	public void remove_sc_tran_edu_resve(SmsSendVO smsSendVO) throws Exception {
		dao.remove_sc_tran_edu_resve(smsSendVO);
	}

	@Override
	public void set_sc_tran_edu_resve_mod(SmsSendVO smsSendVO) throws Exception {
		dao.set_sc_tran_edu_resve_mod(smsSendVO);
	}

	@Override
	public void set_mms_msg_edu_resve_mod(SmsSendVO smsSendVO) throws Exception {
		dao.set_mms_msg_edu_resve_mod(smsSendVO);
	}

	@Override
	public void remove_mms_msg_edu_resve(SmsSendVO smsSendVO) throws Exception {
		dao.remove_mms_msg_edu_resve(smsSendVO);
	}
	

}
