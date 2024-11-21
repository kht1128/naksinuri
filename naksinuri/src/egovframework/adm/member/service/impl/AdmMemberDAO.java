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
package egovframework.adm.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.adm.member.service.AdmCntnAuthorIpVO;
import egovframework.adm.member.service.AdmMemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("admMemberDAO")
public class AdmMemberDAO extends EgovAbstractDAO {

	
	public AdmMemberVO get_adm_member_auth_info(AdmMemberVO mAdmMemberVO) {
		return (AdmMemberVO)select("Level.get_adm_member_auth_info",mAdmMemberVO);
	}

	public void set_adm_member_auth_mod(AdmMemberVO mAdmMemberVO) {
		update("Level.set_adm_member_auth_mod", mAdmMemberVO);
	}

	public List<AdmMemberVO> get_adm_member_auth_list(AdmMemberVO mAdmMemberVO) {
		return (List<AdmMemberVO>)list("Level.get_adm_member_auth_list",mAdmMemberVO);
	}

	public int get_adm_member_auth_list_totcnt(AdmMemberVO mAdmMemberVO) {
		return (int)select("Level.get_adm_member_auth_list_totcnt",mAdmMemberVO);
	}

	public AdmMemberVO get_member_info(AdmMemberVO mAdmMemberVO) {
		return (AdmMemberVO)select("admMember.get_member_info",mAdmMemberVO);
	}

	public AdmMemberVO get_member_all_info(AdmMemberVO mAdmMemberVO) {
		return (AdmMemberVO)select("admMember.get_member_all_info",mAdmMemberVO);
	}

	@SuppressWarnings("unchecked")
	public List<AdmMemberVO> get_member_all_info_list(AdmMemberVO admMemberVO) {
		return (List<AdmMemberVO>) list("get_member_all_info_list", admMemberVO);
	}

	public AdmCntnAuthorIpVO get_cntn_author_ip_info(AdmCntnAuthorIpVO admCntnAuthorIpVO) {
		return (AdmCntnAuthorIpVO)select("get_cntn_author_ip_info", admCntnAuthorIpVO);
	}
	
}
