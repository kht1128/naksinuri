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
package egovframework.educenter.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.educenter.member.service.EduCenterMemberService;
import egovframework.educenter.member.service.EduCenterMemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eduCenterMemberService")
public class EduCenterMemberServiceImpl extends EgovAbstractServiceImpl implements EduCenterMemberService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterMemberServiceImpl.class);

	@Resource(name = "eduCenterMemberDAO")
	private EduCenterMemberDAO eduCenterMemberDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;


	@Override
	public EduCenterMemberVO get_edu_member_info(EduCenterMemberVO eduCenterMemberVO) {
		return eduCenterMemberDAO.get_edu_member_info(eduCenterMemberVO);
	}

	@Override
	public List<EduCenterMemberVO> get_edu_member_dtl_all_list(EduCenterMemberVO eduCenterMemberVO) throws Exception {
		return eduCenterMemberDAO.get_edu_member_dtl_all_list(eduCenterMemberVO);
	}

	@Override
	public String set_edu_member_target_reg(EduCenterMemberVO eduCenterMemberVO) throws Exception {
		return eduCenterMemberDAO.set_edu_member_target_reg(eduCenterMemberVO);
	}
	
	@Override
	public void set_edu_member_target_mod(EduCenterMemberVO eduCenterMemberVO) throws Exception {
		eduCenterMemberDAO.set_edu_member_target_mod(eduCenterMemberVO);
	}

	@Override
	public void set_edu_member_target_clear(EduCenterMemberVO eduCenterMemberVO) throws Exception {
		eduCenterMemberDAO.set_edu_member_target_clear(eduCenterMemberVO);
	}

	@Override
	public void set_mbr_edu_trgt_mod(EduCenterMemberVO eduCenterMemberVO) throws Exception {
		eduCenterMemberDAO.set_mbr_edu_trgt_mod(eduCenterMemberVO);
	}

}
