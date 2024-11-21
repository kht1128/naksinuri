/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.all.main.service;

import java.util.List;

import org.json.simple.JSONObject;

import egovframework.adm.sms.service.SmsManagerService;
import egovframework.all.codeset.service.CodeSetService;
import egovframework.rte.fdl.property.EgovPropertyService;

public interface KakaoAlimTalkService {
	
	JSONObject sendMessage(final KakaoAlimTalkVO kakaoAlimTalkVO, final EgovPropertyService propertyService, final CodeSetService codeSetServic, final SmsManagerService smsManagerService);

	public String set_kakao_alimtalk(KakaoAlimTalkVO kakaoAlimTalkVO);

	List<KakaoAlimTalkVO> get_alim_talk_list(KakaoAlimTalkVO kakaoAlimTalkVO);

	public void update_alim_talk_info(KakaoAlimTalkVO updateAlimTalkVO);
	
	public void update_alim_talk_all_info(KakaoAlimTalkVO updateAlimTalkVO);
	
	public void update_alim_talk_group_id(KakaoAlimTalkVO updateAlimTalkVO);

	int alim_talk_list_cnt(KakaoAlimTalkVO kakaoAlimTalkVO);
	

}
