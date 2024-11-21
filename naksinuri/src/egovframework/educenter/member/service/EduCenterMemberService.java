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
package egovframework.educenter.member.service;

import java.util.List;

public interface EduCenterMemberService {
	
	EduCenterMemberVO get_edu_member_info(EduCenterMemberVO eduCenterMemberVO);//회원정보 가져오기
	List<EduCenterMemberVO> get_edu_member_dtl_all_list(EduCenterMemberVO eduCenterMemberVO) throws Exception;//회원추가정보 목록
	String set_edu_member_target_reg(EduCenterMemberVO chkEduCenterMemberVO) throws Exception;	//해당 년도 교육대상자 추가
	void set_edu_member_target_mod(EduCenterMemberVO eduCenterMemberVO) throws Exception;		//해당 년도 교육대상자 정보에 교육과정 업데이트
	void set_edu_member_target_clear(EduCenterMemberVO eduCenterMemberVO) throws Exception;		//해당 교육에 교육대상자 정보만 삭제
	void set_mbr_edu_trgt_mod(EduCenterMemberVO eduCenterMemberVO) throws Exception;			//설문조사 후 자동 이수 처리

}
