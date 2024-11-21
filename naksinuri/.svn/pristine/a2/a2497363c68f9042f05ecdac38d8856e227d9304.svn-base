package egovframework.adm.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.adm.sms.service.SmsMentVO;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;



@Repository("smsManagerDAO")
public class SmsManagerDAO extends EgovAbstractDAO {
	
	//멘트관리
	public List<SmsMentVO> get_sms_ment_list(SmsMentVO smsMentVO) {
		return (List<SmsMentVO>)list("admSms.get_sms_ment_list",smsMentVO);
	}
	public int get_sms_ment_list_totcnt(SmsMentVO smsMentVO) {
		return (int)select("admSms.get_sms_ment_list_totcnt",smsMentVO);
	}
	public SmsMentVO get_sms_ment_info(SmsMentVO smsMentVO) {
		return (SmsMentVO)select("admSms.get_sms_ment_info",smsMentVO);
	}
	public void remove_sms_ment(SmsMentVO smsMentVO) {
		delete("admSms.remove_sms_ment",smsMentVO);
	}
	public void del_sms_ment(SmsMentVO smsMentVO) {
		update("admSms.del_sms_ment",smsMentVO);
	}
	public void set_sms_ment_info_mod(SmsMentVO smsMentVO) {
		update("admSms.set_sms_ment_info_mod",smsMentVO);
	}
	public String set_sms_ment_info_reg(SmsMentVO smsMentVO) {
		return (String)insert("admSms.set_sms_ment_info_reg",smsMentVO);
	}
	
	public boolean sendSms(SmsSendVO data) {
		try {
			insert("admSms.insert_sms",data);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public boolean sendMms(SmsSendVO data) {
		try {
			insert("admSms.insert_mms",data);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	public boolean set_sms_queue_reg(SmsSendVO data) {
		try {
			insert("admSms.set_sms_queue_reg",data);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	public List<SmsMentVO> get_sms_resve_list(SmsSendVO smsSendVO) {
		return (List<SmsMentVO>)list("admSms.get_sms_resve_list",smsSendVO);
	}
	public int get_sms_resve_list_totcnt(SmsSendVO smsSendVO) {
		return (int)select("admSms.get_sms_resve_list_totcnt",smsSendVO);
	}
	public SmsSendVO get_sms_resve_info(SmsSendVO smsSendVO) {
		return (SmsSendVO)select("admSms.get_sms_resve_info",smsSendVO);
	}
	public void remove_sms_resve(SmsSendVO smsSendVO) {
		delete("admSms.remove_sms_resve",smsSendVO);
	}
	public void del_sms_resve(SmsSendVO smsSendVO) {
		update("admSms.del_sms_resve",smsSendVO);
	}
	public void set_sms_resve_info_mod(SmsSendVO smsSendVO) {
		update("admSms.set_sms_resve_info_mod",smsSendVO);
	}
	
	public List<SmsSendVO> get_sms_log_list(SmsSendVO smsSendVO) {
		return (List<SmsSendVO>) list("admSms.get_sms_log_list", smsSendVO);
	}
	public int get_sms_log_list_totcnt(SmsSendVO smsSendVO) {
		return (int) select("admSms.get_sms_log_list_totcnt", smsSendVO);
	}
	public void remove_sc_tran_edu_resve(SmsSendVO smsSendVO) {
		delete("admSms.remove_sc_tran_edu_resve", smsSendVO);
	}
	public void set_sc_tran_edu_resve_mod(SmsSendVO smsSendVO) {
		update("admSms.set_sc_tran_edu_resve_mod", smsSendVO);
	}
	public void set_mms_msg_edu_resve_mod(SmsSendVO smsSendVO) {
		update("admSms.set_mms_msg_edu_resve_mod", smsSendVO);
	}
	public void remove_mms_msg_edu_resve(SmsSendVO smsSendVO) {
		delete("admSms.remove_mms_msg_edu_resve", smsSendVO);
	}
	
}