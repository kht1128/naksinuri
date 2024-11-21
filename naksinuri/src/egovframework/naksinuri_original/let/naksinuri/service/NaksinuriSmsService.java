package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;

public interface NaksinuriSmsService {

	public List<NaksinuriSmsVO> getReadySmsQueueData() throws Exception;

	public boolean sendSms(NaksinuriSmsVO data) throws Exception;
	
	public boolean sendMms(NaksinuriSmsVO data) throws Exception;

	public void successSmsQueueData(NaksinuriSmsVO data) throws Exception;

	public void failSmsQueueData(NaksinuriSmsVO data) throws Exception;

	public List<NaksinuriSmsVO> getCheckSmsQueueData() throws Exception;

	public NaksinuriSmsLogsmsVO getSmsSendLogData(NaksinuriSmsVO data) throws Exception;

	public NaksinuriSmsLogmmsVO getMmsSendLogData(NaksinuriSmsVO data) throws Exception;

	public void updateSmsQueueInfo(NaksinuriSmsVO data) throws Exception;
		
	public boolean sendToSmsMessage(NaksinuriSmsVO data) throws Exception;

	public void refreshSmsQueueInfo(NaksinuriSmsVO smsqueueData) throws Exception;
	
	
	//낚시대회 참가자에게 문자보내기
	/**
	 * @param phone //받는사람 번호
	 * @param bo_title //대회명
	 * @param depositAmount //입금금액
	 * @param depositName //입금자명
	 * @param cg_account //입금계좌
	 * @param cg_account_name //은행명
	 * @param adminPhone //담당자 연락처
	 * @param mbr_count //총 참가신청인원
	 * @param ip //발송시도 ip
	 * */
	public void sendToCongressCustomer(String phone, String bo_title, String depositAmount, String depositName, String cg_account_name, String cg_account, String adminPhone, int mbr_count, String ip) throws Exception;
	
	//낚시대회 담당자에게 문자보내기
	/**
	 * @param phone //받는사람 번호
	 * @param bo_title //대회명
	 * @param customers //참가신청자 목록
	 * @param mbr_count //총 참가신청인원
	 * @param ip //발송시도 ip
	 * */
	public void sendToCongressAdmin(String phone, String bo_title, String customers, int mbr_count, String ip) throws Exception;
	
	public List<SmsMngrVO> group_list(SmsMngrVO smsMngrVO) throws Exception;
	public SmsMngrVO group_select(SmsMngrVO smsMngrVO) throws Exception;
	public boolean group_insert(SmsMngrVO smsMngrVO) throws Exception;
	public boolean group_update(SmsMngrVO smsMngrVO) throws Exception;
	public boolean group_delete(SmsMngrVO smsMngrVO) throws Exception;
	
	public List<SmsMngrVO> contact_list(SmsMngrVO smsMngrVO) throws Exception;
	public SmsMngrVO contact_select(SmsMngrVO smsMngrVO) throws Exception;
	
	public boolean contact_insert(SmsMngrVO smsMngrVO) throws Exception;
	public boolean contact_update(SmsMngrVO smsMngrVO) throws Exception;
	public boolean contact_delete(SmsMngrVO smsMngrVO) throws Exception;
	
	public List<SmsMngrVO> ment_list(SmsMngrVO smsMngrVO) throws Exception;
	public boolean ment_insert(SmsMngrVO smsMngrVO) throws Exception;
	public boolean ment_update(SmsMngrVO smsMngrVO) throws Exception;
	public boolean ment_delete(SmsMngrVO smsMngrVO) throws Exception;
	
	public boolean sendToSmsMngr(NaksinuriSmsVO data) throws Exception;	
	
}
