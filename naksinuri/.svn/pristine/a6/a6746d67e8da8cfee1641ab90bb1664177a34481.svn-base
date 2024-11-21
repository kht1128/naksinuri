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
package egovframework.all.main.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.all.main.service.KakaoAlimTalkVO;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("kakaoAlimTalkDAO")
public class KakaoAlimTalkDAO extends EgovAbstractDAO {
	
	public String set_kakao_alimtalk(KakaoAlimTalkVO kakaoAlimTalkVO) {
		return (String) insert("admSms.set_kakao_alimtalk",kakaoAlimTalkVO);
	}

	public List<KakaoAlimTalkVO> get_alim_talk_list(KakaoAlimTalkVO kakaoAlimTalkVO) {
		return (List<KakaoAlimTalkVO>) list("admSms.get_alim_talk_list",kakaoAlimTalkVO);
	}

	public void update_alim_talk_info(KakaoAlimTalkVO updateAlimTalkVO) {
		update("admSms.update_alim_talk_info",updateAlimTalkVO);
	}
	
	
	public void update_alim_talk_all_info(KakaoAlimTalkVO updateAlimTalkVO) {
		update("admSms.update_alim_talk_all_info",updateAlimTalkVO);
	}
	
	public void update_alim_talk_group_id(KakaoAlimTalkVO updateAlimTalkVO) {
		update("admSms.update_alim_talk_group_id",updateAlimTalkVO);
	}

	public int alim_talk_list_cnt(KakaoAlimTalkVO kakaoAlimTalkVO) {
		return (int) select("admSms.alim_talk_list_cnt", kakaoAlimTalkVO);
	}


}
