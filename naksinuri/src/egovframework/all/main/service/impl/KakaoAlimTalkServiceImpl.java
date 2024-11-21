package egovframework.all.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import egovframework.adm.sms.service.SmsManagerService;
import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.main.service.KakaoAlimTalkService;
import egovframework.all.main.service.KakaoAlimTalkVO;
import egovframework.eduadm.board.service.impl.EduBoardDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.PublicUtils;

@Service("kakaoAlimTalkService")
public class KakaoAlimTalkServiceImpl extends EgovAbstractServiceImpl implements KakaoAlimTalkService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KakaoAlimTalkService.class);
	/** EduBoardDAO */
	@Resource(name="kakaoAlimTalkDAO")
	private KakaoAlimTalkDAO kakaoAlimTalkDAO;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	@Resource(name = "codeSetService")
    protected CodeSetService codeSetService;
		
	@Override
	public JSONObject sendMessage(final KakaoAlimTalkVO kakaoAlimTalkVO, final EgovPropertyService propertyService, final CodeSetService codeSetService, 
			final SmsManagerService smsManagerService) {
		JSONObject rstdata = new JSONObject();
		try {
			PublicUtils mPublicUtils = new PublicUtils();
			String kakaoAlimTalkUrl = propertyService.getString("kakao.alimtalk.url");
			String kakaoAlimTalkApiKey = propertyService.getString("kakao.alimtalk.apikey");
			String kakaoAlimTalkUserid = propertyService.getString("kakao.alimtalk.userid");
			StringBuilder document = new StringBuilder();
			StringBuilder document2 = new StringBuilder();
			
			JSONObject reqdata = new JSONObject();
			JSONArray reqMessageArrdata = new JSONArray();
			JSONArray reqMessageArrdataBtn = new JSONArray();
			JSONObject reqMessagedata = new JSONObject();
			JSONObject reqMessagedataBtn = new JSONObject();
			
			JSONObject reqdata2 = new JSONObject();
			JSONArray reqMessageArrdata2 = new JSONArray();
			JSONArray reqMessageArrdataBtn2 = new JSONArray();
			JSONObject reqMessagedata2 = new JSONObject();
			JSONObject reqMessagedataBtn2 = new JSONObject();
			
			CodeSetVO mCodeSetVO = new CodeSetVO();
			mCodeSetVO.setCD_MASTER_ID("KKO_TMPLAT");
			mCodeSetVO.setCD_ID(kakaoAlimTalkVO.getTemplateId());
			CodeSetVO templ = codeSetService.get_codeset_info(mCodeSetVO);
			reqdata.put("template_id", "500" + templ.getCD_ORD_NO());
			document.append(templ.getCD_CN());
			if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000001")) {
				CodeSetVO mCodeSetVO1 = new CodeSetVO();
				mCodeSetVO1.setCD_MASTER_ID("KKO_TMPLAT");
				mCodeSetVO1.setCD_ID("KKO_000002");
				CodeSetVO templ1 = codeSetService.get_codeset_info(mCodeSetVO1);
				reqdata2.put("template_id", "500" + templ1.getCD_ORD_NO());
				document2.append(templ1.getCD_CN());	
			} else if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000003")) {
				CodeSetVO mCodeSetVO1 = new CodeSetVO();
				mCodeSetVO1.setCD_MASTER_ID("KKO_TMPLAT");
				mCodeSetVO1.setCD_ID("KKO_000004");
				CodeSetVO templ1 = codeSetService.get_codeset_info(mCodeSetVO1);
				reqdata2.put("template_id", "500" + templ1.getCD_ORD_NO());
				document2.append(templ1.getCD_CN());
			} else if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000008")) {
				CodeSetVO mCodeSetVO1 = new CodeSetVO();
				mCodeSetVO1.setCD_MASTER_ID("KKO_TMPLAT");
				mCodeSetVO1.setCD_ID("KKO_000009");
				CodeSetVO templ1 = codeSetService.get_codeset_info(mCodeSetVO1);
				reqdata2.put("template_id", "500" + templ1.getCD_ORD_NO());
				document2.append(templ1.getCD_CN());
			}

	  		//문구 교체
			if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000005") || kakaoAlimTalkVO.getTemplateId().equals("KKO_000006") ) {//이수증발급 낚시터, 낚시어선 전문교육
				mPublicUtils.replaceAll(document, "[COMPL_EDYC_ISSU_NO]", kakaoAlimTalkVO.getComplEdycIssuNo());//이수증발급번호
				mPublicUtils.replaceAll(document, "[CMPLET_NM]", kakaoAlimTalkVO.getCmpletNm());//성명
				mPublicUtils.replaceAll(document, "[CMPLET_BRDT]", kakaoAlimTalkVO.getCmpletBrdt());//생년월일
				mPublicUtils.replaceAll(document, "[CMPLET_ADRES]", kakaoAlimTalkVO.getCmpletAdres());//주소
				mPublicUtils.replaceAll(document, "[EDU_YMD]", kakaoAlimTalkVO.getEduYmd());//교육일시
				mPublicUtils.replaceAll(document, "[EDU_NM]", kakaoAlimTalkVO.getEduNm());//교과과정명
			} else if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000007")) {//이수증발급 신규재개자 전문교육
				mPublicUtils.replaceAll(document, "[COMPL_EDYC_ISSU_NO]", kakaoAlimTalkVO.getComplEdycIssuNo());//이수증발급번호
				mPublicUtils.replaceAll(document, "[CMPLET_NM]", kakaoAlimTalkVO.getCmpletNm());//성명
				mPublicUtils.replaceAll(document, "[CMPLET_BRDT]", kakaoAlimTalkVO.getCmpletBrdt());//생년월일
				mPublicUtils.replaceAll(document, "[CMPLET_ADRES]", kakaoAlimTalkVO.getCmpletAdres());//주소
				mPublicUtils.replaceAll(document, "[EDU_STR_DT]", kakaoAlimTalkVO.getEduStrDt());//교육시작일시 
				mPublicUtils.replaceAll(document, "[EDU_END_DT]", kakaoAlimTalkVO.getEduEndDt());//교육종료일시
				mPublicUtils.replaceAll(document, "[EDU_NM]", kakaoAlimTalkVO.getEduNm());//교과과정명
			} else if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000001")) {
				mPublicUtils.replaceAll(document2, "[EDU_URL1]", kakaoAlimTalkVO.getEduUrl1());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL2]", kakaoAlimTalkVO.getEduUrl2());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL3]", kakaoAlimTalkVO.getEduUrl3());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL4]", kakaoAlimTalkVO.getEduUrl4());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL5]", kakaoAlimTalkVO.getEduUrl5());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL6]", kakaoAlimTalkVO.getEduUrl6());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL7]", kakaoAlimTalkVO.getSurveyUrl());	//동영상수강URL
			} else if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000003")) {
				mPublicUtils.replaceAll(document2, "[EDU_URL1]", kakaoAlimTalkVO.getEduUrl1());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL2]", kakaoAlimTalkVO.getEduUrl2());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL3]", kakaoAlimTalkVO.getEduUrl3());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL4]", kakaoAlimTalkVO.getEduUrl4());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL5]", kakaoAlimTalkVO.getSurveyUrl());	//설문조사URL
			} else if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000008")) {
				mPublicUtils.replaceAll(document, "[EDU_URL1]", kakaoAlimTalkVO.getEduUrl1());	//동영상수강URL
				mPublicUtils.replaceAll(document, "[EDU_URL2]", kakaoAlimTalkVO.getEduUrl2());	//동영상수강URL
				mPublicUtils.replaceAll(document, "[EDU_URL3]", kakaoAlimTalkVO.getEduUrl3());	//동영상수강URL
				
				mPublicUtils.replaceAll(document2, "[EDU_URL4]", kakaoAlimTalkVO.getEduUrl4());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL5]", kakaoAlimTalkVO.getEduUrl5());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL6]", kakaoAlimTalkVO.getEduUrl6());	//동영상수강URL
				mPublicUtils.replaceAll(document2, "[EDU_URL7]", kakaoAlimTalkVO.getSurveyUrl());	//설문조사URL
			} else if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000010")) {
				mPublicUtils.replaceAll(document, "[EDU_URL]", kakaoAlimTalkVO.getEduUrl1());	//동영상수강URL
				mPublicUtils.replaceAll(document, "[EDU_URL2]", kakaoAlimTalkVO.getEduUrl2());	//동영상수강URL
				mPublicUtils.replaceAll(document, "[EDU_URL3]", kakaoAlimTalkVO.getEduUrl3());	//동영상수강URL
				mPublicUtils.replaceAll(document, "[EDU_URL4]", kakaoAlimTalkVO.getEduUrl4());	//동영상수강URL
				mPublicUtils.replaceAll(document, "[EDU_URL5]", kakaoAlimTalkVO.getSurveyUrl());	//설문조사URL
			} else if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000011") || kakaoAlimTalkVO.getTemplateId().equals("KKO_000012")) {
				mPublicUtils.replaceAll(document, "[SURVEY_URL]", kakaoAlimTalkVO.getSurveyUrl());	// 설문조사URL
			}
			
			//End of 문구 교체
			reqMessagedata.put("no", kakaoAlimTalkVO.getUniqKey("KKO"));
			reqMessagedata.put("tel_num", kakaoAlimTalkVO.getTelNum().replace("-", ""));
			reqMessagedata.put("reserve_time", mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
			reqMessagedata.put("custom_key", "fipa");
			reqMessagedata.put("msg_content", document.toString());
			reqMessagedata.put("sms_content", document.toString());
			reqMessagedata.put("use_sms", "1");

			reqMessagedataBtn.put("url_pc", "https://www.naksinuri.kr/educenter/index.do");
			reqMessagedataBtn.put("url_mobile", "https://www.naksinuri.kr/educenter/index.do");
			reqMessageArrdataBtn.add(reqMessagedataBtn);
			
			reqMessagedata.put("btn_url", reqMessageArrdataBtn);
			reqMessageArrdata.add(reqMessagedata);
			
			reqdata.put("userid", kakaoAlimTalkUserid);
			reqdata.put("api_key", kakaoAlimTalkApiKey);

			reqdata.put("messages", reqMessageArrdata);
			
			LOGGER.debug("------------------------ 일반 문자 전송?  ------------------------" + kakaoAlimTalkVO.isSendWithSMS());
			if(kakaoAlimTalkVO.isSendWithSMS()) {
				LOGGER.debug("------------------------ 일반 문자 전송  ------------------------");
				SmsSendVO newSmsSendVO = new SmsSendVO();
				newSmsSendVO.setMBR_ID(kakaoAlimTalkVO.getMberId());
				newSmsSendVO.setSMS_MENT_DTL_CD(kakaoAlimTalkVO.getSmsTmplatCd());
				newSmsSendVO.setMSG(document.toString());							
				newSmsSendVO.setS_PHONE(propertyService.getString("Globals.SmsSenderNumber"));//발신번호
				newSmsSendVO.setR_PHONE(kakaoAlimTalkVO.getTelNum().replace("-", ""));
				newSmsSendVO.setSUBMSG(kakaoAlimTalkVO.getSmsSj());
				newSmsSendVO.setIMG_CNT(0);
				newSmsSendVO.setIMG_PATH("");
				newSmsSendVO.setREG_MBR_ID(kakaoAlimTalkVO.getMberId());
				newSmsSendVO.setUPD_MBR_ID(kakaoAlimTalkVO.getMberId());			
			    newSmsSendVO.setIP(kakaoAlimTalkVO.getMberIp());
				smsManagerService.sendToMessage(newSmsSendVO);
				LOGGER.debug("------------------------ 일반 문자 전송 처리 완료 ------------------------");
			} else {
				LOGGER.debug("------------------------ 일반 문자 전송 안함 ------------------------");
			}
			LOGGER.debug("------------------------ 알림톡 전송 요청 ------------------------");
			LOGGER.debug(reqdata.toString());
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity param= new HttpEntity(reqdata, headers);
			ListenableFuture<ResponseEntity<Map>> result = null;
			if(kakaoAlimTalkVO.isAsync()) { //비동기
				LOGGER.debug("------------------------ 처리 중(비동기) ------------------------");
				AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
				result = (ListenableFuture<ResponseEntity<Map>>) asyncRestTemplate.postForEntity(kakaoAlimTalkUrl, param, Map.class);
			} else { //동기
				LOGGER.debug("------------------------ 처리 중(동기) ------------------------");
				RestTemplate restTemplate = new RestTemplate();		    
				result = (ListenableFuture<ResponseEntity<Map>>) restTemplate.postForObject(kakaoAlimTalkUrl, param, Map.class);	
			}
			LOGGER.debug("------------------------ 알림톡 전송 결과 ------------------------");
			LOGGER.debug(result.get().getBody().toString());
		    
			String code = result.get().getBody().get("code").toString();
			if(code.equals("0")){
				rstdata.put("error", "0");
		    	rstdata.put("msg", "알림톡을 정상적으로 전송했습니다.");
			} else if(code.equals("-2")){	// 파라미터 오류
		    	rstdata.put("error", "1");
		    	rstdata.put("msg", "파라미터 오류입니다. 관리자에게 문의바랍니다.");
		    } else if(code.equals("-1")) {	// 메시지 데이터 오류
		    	rstdata.put("error", "1");
		    	rstdata.put("msg", "메시지 데이터 오류 입니다. 관리자에게 문의바랍니다.");
		    }
			if(kakaoAlimTalkVO.getTemplateId().equals("KKO_000001") || kakaoAlimTalkVO.getTemplateId().equals("KKO_000003") || kakaoAlimTalkVO.getTemplateId().equals("KKO_000008")) {
				Thread.sleep(5000);
				
				reqMessagedata2.put("no", kakaoAlimTalkVO.getUniqKey("KKO"));
				reqMessagedata2.put("tel_num", kakaoAlimTalkVO.getTelNum().replace("-", ""));
				reqMessagedata2.put("reserve_time", mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
				reqMessagedata2.put("custom_key", "fipa");
				reqMessagedata2.put("msg_content", document2.toString());
				reqMessagedata2.put("sms_content", document2.toString());
				reqMessagedata2.put("use_sms", "1");

				reqMessagedataBtn2.put("url_pc", "https://www.naksinuri.kr/educenter/index.do");
				reqMessagedataBtn2.put("url_mobile", "https://www.naksinuri.kr/educenter/index.do");
				reqMessageArrdataBtn2.add(reqMessagedataBtn2);
				
				reqMessagedata2.put("btn_url", reqMessageArrdataBtn2);
				reqMessageArrdata2.add(reqMessagedata2);
				
				reqdata2.put("userid", kakaoAlimTalkUserid);
				reqdata2.put("api_key", kakaoAlimTalkApiKey);

				reqdata2.put("messages", reqMessageArrdata2);
				
				LOGGER.debug("------------------------ 일반 문자 전송?  ------------------------" + kakaoAlimTalkVO.isSendWithSMS());
				if(kakaoAlimTalkVO.isSendWithSMS()) {
					LOGGER.debug("------------------------ 일반 문자 전송  ------------------------");
					SmsSendVO newSmsSendVO = new SmsSendVO();
					newSmsSendVO.setMBR_ID(kakaoAlimTalkVO.getMberId());
					newSmsSendVO.setSMS_MENT_DTL_CD(kakaoAlimTalkVO.getSmsTmplatCd());
					newSmsSendVO.setMSG(document2.toString());							
					newSmsSendVO.setS_PHONE(propertyService.getString("Globals.SmsSenderNumber"));//발신번호
					newSmsSendVO.setR_PHONE(kakaoAlimTalkVO.getTelNum().replace("-", ""));
					newSmsSendVO.setSUBMSG(kakaoAlimTalkVO.getSmsSj());
					newSmsSendVO.setIMG_CNT(0);
					newSmsSendVO.setIMG_PATH("");
					newSmsSendVO.setREG_MBR_ID(kakaoAlimTalkVO.getMberId());
					newSmsSendVO.setUPD_MBR_ID(kakaoAlimTalkVO.getMberId());			
				    newSmsSendVO.setIP(kakaoAlimTalkVO.getMberIp());
					smsManagerService.sendToMessage(newSmsSendVO);
					LOGGER.debug("------------------------ 일반 문자 전송 처리 완료 ------------------------");
				} else {
					LOGGER.debug("------------------------ 일반 문자 전송 안함 ------------------------");
				}
				LOGGER.debug("------------------------ 알림톡 전송 요청 ------------------------");
				LOGGER.debug(reqdata2.toString());
				
				HttpHeaders headers2 = new HttpHeaders();
				headers2.setContentType(MediaType.APPLICATION_JSON);

				HttpEntity param2= new HttpEntity(reqdata2, headers2);
				ListenableFuture<ResponseEntity<Map>> result2 = null;
				
				if(kakaoAlimTalkVO.isAsync()) { //비동기
					LOGGER.debug("------------------------ 처리 중(비동기) ------------------------");
					AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
					result2 = (ListenableFuture<ResponseEntity<Map>>) asyncRestTemplate.postForEntity(kakaoAlimTalkUrl, param2, Map.class);	
				} else { //동기
					LOGGER.debug("------------------------ 처리 중(동기) ------------------------");
					RestTemplate restTemplate = new RestTemplate();
					result2 = (ListenableFuture<ResponseEntity<Map>>) restTemplate.postForObject(kakaoAlimTalkUrl, param2, Map.class);	
				}
				LOGGER.debug("------------------------ 알림톡 전송 결과 ------------------------");
				LOGGER.debug(result2.get().getBody().toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.debug(e.toString());
			rstdata.put("error", "1");
			rstdata.put("msg", "알림톡 전송이 실패하였습니다. 서버와의 통신이 올바르지 않습니다.");
		}
		return rstdata;
	}

	public String set_kakao_alimtalk(KakaoAlimTalkVO kakaoAlimTalkVO) {
    	return kakaoAlimTalkDAO.set_kakao_alimtalk(kakaoAlimTalkVO);
	}

	@Override
	public List<KakaoAlimTalkVO> get_alim_talk_list(KakaoAlimTalkVO kakaoAlimTalkVO) {
		return kakaoAlimTalkDAO.get_alim_talk_list(kakaoAlimTalkVO);
	}

	@Override
	public void update_alim_talk_info(KakaoAlimTalkVO updateAlimTalkVO) {
		kakaoAlimTalkDAO.update_alim_talk_info(updateAlimTalkVO);
	}
	
	@Override
	public void update_alim_talk_all_info(KakaoAlimTalkVO updateAlimTalkVO) {
		kakaoAlimTalkDAO.update_alim_talk_all_info(updateAlimTalkVO);
	}
	
	@Override
	public void update_alim_talk_group_id(KakaoAlimTalkVO updateAlimTalkVO) {
		kakaoAlimTalkDAO.update_alim_talk_group_id(updateAlimTalkVO);
	}

	@Override
	public int alim_talk_list_cnt(KakaoAlimTalkVO kakaoAlimTalkVO) {
		return kakaoAlimTalkDAO.alim_talk_list_cnt(kakaoAlimTalkVO);
	}
	
}