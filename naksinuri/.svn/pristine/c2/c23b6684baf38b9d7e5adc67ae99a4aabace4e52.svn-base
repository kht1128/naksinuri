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
package egovframework.cti.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.cti.member.service.CtiMemberService;
import egovframework.cti.member.service.CtiMemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ctiMemberService")
public class CtiMemberServiceImpl extends EgovAbstractServiceImpl implements CtiMemberService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CtiMemberServiceImpl.class);

	@Resource(name = "ctiMemberDAO")
	private CtiMemberDAO ctiMemberDAO;

	/*@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;*/

	@Override
	public CtiMemberVO get_cti_staff_info(CtiMemberVO ctiMemberVO) throws Exception {
		return ctiMemberDAO.get_cti_staff_info(ctiMemberVO);
	}

	@Override
	public void remove_cti_staff(CtiMemberVO ctiMemberVO) throws Exception {
		ctiMemberDAO.remove_cti_staff(ctiMemberVO);
	}

	@Override
	public String set_cti_staff_reg(CtiMemberVO ctiMemberVO) throws Exception {
		return ctiMemberDAO.set_cti_staff_reg(ctiMemberVO);
	}

	@Override
	public void set_cti_staff_mod(CtiMemberVO ctiMemberVO) throws Exception {
		ctiMemberDAO.set_cti_staff_mod(ctiMemberVO);
	}
	
	@Override
	public CtiMemberVO get_cti_mbr_info(CtiMemberVO ctiMemberVO) throws Exception {
		return ctiMemberDAO.get_cti_mbr_info(ctiMemberVO);
	}

	@Override
	public List<CtiMemberVO> get_cti_staff_list(CtiMemberVO ctiMemberVO) throws Exception {
		return ctiMemberDAO.get_cti_staff_list(ctiMemberVO);
	}

	@Override
	public CtiMemberVO get_mbr_info_with_scan(CtiMemberVO ctiMemberVO) throws Exception {
		return ctiMemberDAO.get_mbr_info_with_scan(ctiMemberVO);
	}
	
	@Override
	public List<CtiMemberVO> get_mbr_info_with_scan_all(CtiMemberVO ctiMemberVO) throws Exception {
		return ctiMemberDAO.get_mbr_info_with_scan_all(ctiMemberVO);
	}
	
	@Override
	public String set_cti_mbr_info_reg(CtiMemberVO ctiMemberVO) throws Exception {
		return ctiMemberDAO.set_cti_mbr_info_reg(ctiMemberVO);
	}
	
	@Override
	public void set_cti_mbr_info_mod(CtiMemberVO ctiMemberVO) throws Exception {
		ctiMemberDAO.set_cti_mbr_info_mod(ctiMemberVO);
	}

	@Override
	public void remove_cti_mbr(CtiMemberVO ctiMemberVO) throws Exception {
		ctiMemberDAO.remove_cti_mbr(ctiMemberVO);
	}
	
	
}
