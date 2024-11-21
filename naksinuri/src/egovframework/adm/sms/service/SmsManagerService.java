package egovframework.adm.sms.service;

import java.util.List;

public interface SmsManagerService {
	//문자발송
	public boolean sendSms(SmsSendVO data) throws Exception;
	public boolean sendMms(SmsSendVO data) throws Exception;
	public String sendToMessage(SmsSendVO data) throws Exception;
	//문자멘트관리
	public List<SmsMentVO> get_sms_ment_list(SmsMentVO smsMentVO) throws Exception;
	public int get_sms_ment_list_totcnt(SmsMentVO smsMentVO) throws Exception;
	public SmsMentVO get_sms_ment_info(SmsMentVO smsMentVO) throws Exception;
	public void remove_sms_ment(SmsMentVO smsMentVO) throws Exception;
	public void del_sms_ment(SmsMentVO smsMentVO) throws Exception;
	public void set_sms_ment_info_mod(SmsMentVO smsMentVO) throws Exception;
	public String set_sms_ment_info_reg(SmsMentVO smsMentVO) throws Exception;
	//문자예약관리
	public List<SmsMentVO> get_sms_resve_list(SmsSendVO smsSendVO) throws Exception;
	public int get_sms_resve_list_totcnt(SmsSendVO smsSendVO) throws Exception;
	public SmsSendVO get_sms_resve_info(SmsSendVO smsSendVO) throws Exception;
	public void remove_sms_resve(SmsSendVO smsSendVO) throws Exception;
	public void del_sms_resve(SmsSendVO smsSendVO) throws Exception;
	public void set_sms_resve_info_mod(SmsSendVO smsSendVO) throws Exception;
	public void remove_sc_tran_edu_resve(SmsSendVO smsSendVO) throws Exception;
	public void set_sc_tran_edu_resve_mod(SmsSendVO smsSendVO) throws Exception;
	public void set_mms_msg_edu_resve_mod(SmsSendVO smsSendVO) throws Exception;
	public void remove_mms_msg_edu_resve(SmsSendVO smsSendVO) throws Exception;
	//문자로그관리
	public List<SmsSendVO> get_sms_log_list(SmsSendVO smsSendVO) throws Exception;
	public int get_sms_log_list_totcnt(SmsSendVO smsSendVO) throws Exception;
	
	
}
