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
package egovframework.adm.member.service;

import java.util.List;

public interface AdmMemberService {

	//권한관리 - 회원조회(대상조회)
	public AdmMemberVO get_adm_member_auth_info(AdmMemberVO mAdmMemberVO) throws Exception;
	//권한관리 - 회원수정(권한부여)
	public void set_adm_member_auth_mod(AdmMemberVO mAdmMemberVO) throws Exception;
 	//권한관리 - 회원목록 조회
	public List<AdmMemberVO> get_adm_member_auth_list(AdmMemberVO mAdmMemberVO) throws Exception;
	public int get_adm_member_auth_list_totcnt(AdmMemberVO mAdmMemberVO) throws Exception;
	//회원 정보 조회
	AdmMemberVO get_member_info(AdmMemberVO mAdmMemberVO) throws Exception;
	public AdmMemberVO get_member_all_info(AdmMemberVO mAdmMemberVO) throws Exception;
	//중복될수있는 조건으로 회원 정보 조회
	public List<AdmMemberVO> get_member_all_info_list(AdmMemberVO admMemberVO) throws Exception;
	public AdmCntnAuthorIpVO get_cntn_author_ip_info(AdmCntnAuthorIpVO admCntnAuthorIpVO) throws Exception;
		
}
