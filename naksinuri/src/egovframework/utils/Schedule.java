package egovframework.utils;

import java.util.ArrayList;
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
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import egovframework.adm.sms.service.SmsSendVO;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.all.main.service.KakaoAlimTalkService;
import egovframework.all.main.service.KakaoAlimTalkVO;
import egovframework.rte.fdl.property.EgovPropertyService;

public class Schedule {


	@Resource(name = "kakaoAlimTalkService")
	private KakaoAlimTalkService kakaoAlimTalkService;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	@Resource(name = "codeSetService")
    protected CodeSetService codeSetService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Schedule.class);

	public void executeJob(){
		PublicUtils mPublicUtils = new PublicUtils();
		String kakaoAlimTalkUrl = propertyService.getString("kakao.alimtalk.url");
		String kakaoAlimTalkApiKey = propertyService.getString("kakao.alimtalk.apikey");
		String kakaoAlimTalkUserid = propertyService.getString("kakao.alimtalk.userid");

		KakaoAlimTalkVO kakaoAlimTalkVO = new KakaoAlimTalkVO();
		String ntcnTrsmGroupId = kakaoAlimTalkVO.getUniqKey("KKO");
		kakaoAlimTalkVO.setTRSM_PROGRS_STTUS("0");
		// 실행할 데이터 존재 확인
		int countList = kakaoAlimTalkService.alim_talk_list_cnt(kakaoAlimTalkVO);
//		int countList = 0;
		if(countList == 0)
			return;
		kakaoAlimTalkVO.setNTCN_TRSM_GROUP_ID(ntcnTrsmGroupId);
		// 그룹ID 지정
		kakaoAlimTalkService.update_alim_talk_group_id(kakaoAlimTalkVO);
		
		List<KakaoAlimTalkVO> list = kakaoAlimTalkService.get_alim_talk_list(kakaoAlimTalkVO);
		for(int i = 0 ; i < list.size() ; i++){
			try {
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
				mCodeSetVO.setCD_ID(list.get(i).getNTCN_TRSM_TEMPLATE_ID());
				CodeSetVO templ = codeSetService.get_codeset_info(mCodeSetVO);
				reqdata.put("template_id", "500" + templ.getCD_ORD_NO());
				document.append(templ.getCD_CN());
				if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000001")) {
					CodeSetVO mCodeSetVO1 = new CodeSetVO();
					mCodeSetVO1.setCD_MASTER_ID("KKO_TMPLAT");
					mCodeSetVO1.setCD_ID("KKO_000002");
					CodeSetVO templ1 = codeSetService.get_codeset_info(mCodeSetVO1);
					reqdata2.put("template_id", "500" + templ1.getCD_ORD_NO());
					document2.append(templ1.getCD_CN());
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000003")) {
					CodeSetVO mCodeSetVO1 = new CodeSetVO();
					mCodeSetVO1.setCD_MASTER_ID("KKO_TMPLAT");
					mCodeSetVO1.setCD_ID("KKO_000004");
					CodeSetVO templ1 = codeSetService.get_codeset_info(mCodeSetVO1);
					reqdata2.put("template_id", "500" + templ1.getCD_ORD_NO());
					document2.append(templ1.getCD_CN());
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000008")) {
					CodeSetVO mCodeSetVO1 = new CodeSetVO();
					mCodeSetVO1.setCD_MASTER_ID("KKO_TMPLAT");
					mCodeSetVO1.setCD_ID("KKO_000009");
					CodeSetVO templ1 = codeSetService.get_codeset_info(mCodeSetVO1);
					reqdata2.put("template_id", "500" + templ1.getCD_ORD_NO());
					document2.append(templ1.getCD_CN());
				}
				String[] externalVideoUrl = list.get(i).getNTCN_TRSM_EDU_URL().split(",");
				//문구 교체
				if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000005") || list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000006")) { //이수증발급
					mPublicUtils.replaceAll(document, "[COMPL_EDYC_ISSU_NO]", kakaoAlimTalkVO.getComplEdycIssuNo());//이수증발급번호
					mPublicUtils.replaceAll(document, "[CMPLET_NM]", kakaoAlimTalkVO.getCmpletNm());//성명
					mPublicUtils.replaceAll(document, "[CMPLET_BRDT]", kakaoAlimTalkVO.getCmpletBrdt());//생년월일
					mPublicUtils.replaceAll(document, "[CMPLET_ADRES]", kakaoAlimTalkVO.getCmpletAdres());//주소
					mPublicUtils.replaceAll(document, "[EDU_YMD]", kakaoAlimTalkVO.getEduYmd());//교육일시
					mPublicUtils.replaceAll(document, "[EDU_NM]", kakaoAlimTalkVO.getEduNm());//교과과정명
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000005")) { //이수증발급 신규재개자 전문교육
					mPublicUtils.replaceAll(document, "[COMPL_EDYC_ISSU_NO]", kakaoAlimTalkVO.getComplEdycIssuNo());//이수증발급번호
					mPublicUtils.replaceAll(document, "[CMPLET_NM]", kakaoAlimTalkVO.getCmpletNm());//성명
					mPublicUtils.replaceAll(document, "[CMPLET_BRDT]", kakaoAlimTalkVO.getCmpletBrdt());//생년월일
					mPublicUtils.replaceAll(document, "[CMPLET_ADRES]", kakaoAlimTalkVO.getCmpletAdres());//주소
					mPublicUtils.replaceAll(document, "[EDU_STR_DT]", kakaoAlimTalkVO.getEduYmd());//교육일시
					mPublicUtils.replaceAll(document, "[EDU_END_DT]", kakaoAlimTalkVO.getEduYmd());//교육일시
					mPublicUtils.replaceAll(document, "[EDU_NM]", kakaoAlimTalkVO.getEduNm());//교과과정명
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000001")) {
					mPublicUtils.replaceAll(document2, "[EDU_URL1]", externalVideoUrl[0]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL2]", externalVideoUrl[1]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL3]", externalVideoUrl[2]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL4]", externalVideoUrl[3]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL5]", externalVideoUrl[4]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL6]", externalVideoUrl[5]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL7]", externalVideoUrl[6]);	//설문조사URL
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000003")) {
					mPublicUtils.replaceAll(document2, "[EDU_URL1]", externalVideoUrl[0]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL2]", externalVideoUrl[1]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL3]", externalVideoUrl[2]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL4]", externalVideoUrl[3]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL5]", externalVideoUrl[4]);	//설문조사URL
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000008")) {
					mPublicUtils.replaceAll(document, "[EDU_URL1]", externalVideoUrl[0]);	//동영상수강URL
					mPublicUtils.replaceAll(document, "[EDU_URL2]", externalVideoUrl[1]);	//동영상수강URL
					mPublicUtils.replaceAll(document, "[EDU_URL3]", externalVideoUrl[2]);	//동영상수강URL
					
					mPublicUtils.replaceAll(document2, "[EDU_URL4]", externalVideoUrl[3]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL5]", externalVideoUrl[4]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL6]", externalVideoUrl[5]);	//동영상수강URL
					mPublicUtils.replaceAll(document2, "[EDU_URL7]", externalVideoUrl[6]);	//설문조사URL
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000010")) {
					mPublicUtils.replaceAll(document, "[EDU_URL1]", externalVideoUrl[0]);	//동영상수강URL
					mPublicUtils.replaceAll(document, "[EDU_URL2]", externalVideoUrl[1]);	//동영상수강URL
					mPublicUtils.replaceAll(document, "[EDU_URL3]", externalVideoUrl[2]);	//동영상수강URL
					mPublicUtils.replaceAll(document, "[EDU_URL4]", externalVideoUrl[3]);	//동영상수강URL
					mPublicUtils.replaceAll(document, "[EDU_URL5]", externalVideoUrl[4]);	//설문조사URL
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000011")) {
					mPublicUtils.replaceAll(document, "[SURVEY_URL]", externalVideoUrl[6]);	//설문조사URL
				} else if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000012")) {
					mPublicUtils.replaceAll(document, "[SURVEY_URL]", externalVideoUrl[4]);	//설문조사URL
				}
				reqMessagedata.put("no", list.get(i).getUniqKey("KKO"));
				reqMessagedata.put("tel_num", list.get(i).getNTCN_TRSM_TEL());
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
				
				LOGGER.debug("------------------------ 알림톡 전송 요청 ------------------------");
				LOGGER.debug(reqdata.toString());	
				HttpHeaders headers = new HttpHeaders();
				HttpEntity param= new HttpEntity(reqdata, headers);

				ListenableFuture<ResponseEntity<Map>> result = null;
    			
				if(list.get(i).getNTCN_TRSM_ASYNC().equals("T")) { //비동기
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
				
				KakaoAlimTalkVO updateAlimTalkVO = new KakaoAlimTalkVO();
				updateAlimTalkVO.setNTCN_TRSM_SN(list.get(i).getNTCN_TRSM_SN());
				updateAlimTalkVO.setNTCN_TRSM_GROUP_ID(ntcnTrsmGroupId);
				if(code.equals("0")){ // 전송성공!
					updateAlimTalkVO.setNTCN_TRSM_RESULT_STTUS("S");
				} else if(code.equals("-2")){	// 파라미터 오류
					updateAlimTalkVO.setTRSM_PROGRS_STTUS("1");
					updateAlimTalkVO.setNTCN_TRSM_RESULT_STTUS("F");
					updateAlimTalkVO.setNTCN_TRSM_ERROR_MSG(result.get().getBody().toString());
				} else if(code.equals("-1")) {	// 메시지 데이터 오류
					updateAlimTalkVO.setNTCN_TRSM_RESULT_STTUS("F");
					updateAlimTalkVO.setTRSM_PROGRS_STTUS("1");
					updateAlimTalkVO.setNTCN_TRSM_ERROR_MSG(result.get().getBody().toString());
				} else {
					updateAlimTalkVO.setNTCN_TRSM_RESULT_STTUS("E");
					updateAlimTalkVO.setTRSM_PROGRS_STTUS("0");
					updateAlimTalkVO.setNTCN_TRSM_ERROR_MSG(result.get().getBody().toString());
					updateAlimTalkVO.setNTCN_TRSM_GROUP_ID(null);
				}
    			
				if(list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000001") || list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000003") || list.get(i).getNTCN_TRSM_TEMPLATE_ID().equals("KKO_000008")) {
					Thread.sleep(5000);
					
					reqMessagedata2.put("no", kakaoAlimTalkVO.getUniqKey("KKO"));
					reqMessagedata2.put("tel_num", list.get(i).getNTCN_TRSM_TEL());
					reqMessagedata2.put("reserve_time", mPublicUtils.currentTime("yyyy-MM-dd HH:mm:ss"));
					reqMessagedata2.put("custom_key", "fipa");
					reqMessagedata2.put("msg_content", document2.toString());
					reqMessagedata2.put("sms_content", document2.toString());
					reqMessagedata2.put("use_sms", "1");

					reqMessagedataBtn2.put("url_pc", "https://www.naksinuri.kr/educenter/index.do");
					reqMessagedataBtn2.put("url_mobile", "https://www.naksinuri.kr/educenter/index.do");
					reqMessageArrdataBtn2.add(reqMessagedataBtn2);
					
					reqMessagedata2.put("btn_url", reqMessageArrdataBtn);
					reqMessageArrdata2.add(reqMessagedata2);
					
					reqdata2.put("userid", kakaoAlimTalkUserid);
					reqdata2.put("api_key", kakaoAlimTalkApiKey);
					
					reqdata2.put("messages", reqMessageArrdata2);
					
					LOGGER.debug("------------------------ 알림톡 전송 요청 ------------------------");
					LOGGER.debug(reqdata2.toString());	
					HttpHeaders headers2 = new HttpHeaders();
					HttpEntity param2= new HttpEntity(reqdata2, headers2);

					ListenableFuture<ResponseEntity<Map>> result2 = null;
	    			
					if(list.get(i).getNTCN_TRSM_ASYNC().equals("T")) { //비동기
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
				
				if(i == list.size() - 1){
					kakaoAlimTalkService.update_alim_talk_all_info(updateAlimTalkVO);
				} else {
					if(!code.equals("0")){
						kakaoAlimTalkService.update_alim_talk_info(updateAlimTalkVO);
					}
				}
			} catch (Exception e){
				LOGGER.debug(e.toString());
				e.printStackTrace();
				KakaoAlimTalkVO updateAlimTalkVO = new KakaoAlimTalkVO();
				updateAlimTalkVO.setNTCN_TRSM_SN(list.get(i).getNTCN_TRSM_SN());
				updateAlimTalkVO.setNTCN_TRSM_RESULT_STTUS("F");
				updateAlimTalkVO.setTRSM_PROGRS_STTUS("1");
				updateAlimTalkVO.setNTCN_TRSM_ERROR_MSG(e.toString());
				kakaoAlimTalkService.update_alim_talk_info(updateAlimTalkVO);
    		}
    	}
    }
}