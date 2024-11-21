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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.adm.member.service.AdmCntnAuthorIpVO;
import egovframework.adm.member.service.AdmMemberService;
import egovframework.adm.member.service.AdmMemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("admMemberService")
public class AdmMemberServiceImpl extends EgovAbstractServiceImpl implements AdmMemberService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdmMemberServiceImpl.class);

	@Resource(name = "admMemberDAO")
	private AdmMemberDAO admMemberDAO;


	@Override
	public AdmMemberVO get_adm_member_auth_info(AdmMemberVO mAdmMemberVO) throws Exception {
		return admMemberDAO.get_adm_member_auth_info(mAdmMemberVO);		
	}

	@Override
	public void set_adm_member_auth_mod(AdmMemberVO mAdmMemberVO) throws Exception {
		admMemberDAO.set_adm_member_auth_mod(mAdmMemberVO);
	}

	@Override
	public List<AdmMemberVO> get_adm_member_auth_list(AdmMemberVO mAdmMemberVO) throws Exception {
		return admMemberDAO.get_adm_member_auth_list(mAdmMemberVO);
	}

	@Override
	public int get_adm_member_auth_list_totcnt(AdmMemberVO mAdmMemberVO) throws Exception {
		return admMemberDAO.get_adm_member_auth_list_totcnt(mAdmMemberVO);
	}

	@Override
	public AdmMemberVO get_member_info(AdmMemberVO mAdmMemberVO) {
		return (AdmMemberVO)admMemberDAO.get_member_info(mAdmMemberVO);
	}

	@Override
	public AdmMemberVO get_member_all_info(AdmMemberVO mAdmMemberVO) throws Exception {
		return (AdmMemberVO)admMemberDAO.get_member_all_info(mAdmMemberVO);
	}

	@Override
	public List<AdmMemberVO> get_member_all_info_list(AdmMemberVO admMemberVO) throws Exception {
		return admMemberDAO.get_member_all_info_list(admMemberVO);
	}

	@Override
	public AdmCntnAuthorIpVO get_cntn_author_ip_info(AdmCntnAuthorIpVO admCntnAuthorIpVO) throws Exception {
		return admMemberDAO.get_cntn_author_ip_info(admCntnAuthorIpVO);
	}
	
}
