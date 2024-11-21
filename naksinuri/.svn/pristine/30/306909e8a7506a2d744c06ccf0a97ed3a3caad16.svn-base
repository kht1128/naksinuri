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

import org.springframework.stereotype.Repository;

import egovframework.educenter.member.service.EduCenterMemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("eduCenterMemberDAO")
public class EduCenterMemberDAO extends EgovAbstractDAO {

	public EduCenterMemberVO get_edu_member_info(EduCenterMemberVO eduCenterMemberVO) {
		return (EduCenterMemberVO) select("eduCenterMemberDAO.get_edu_member_info",eduCenterMemberVO);
	}

	public List<EduCenterMemberVO> get_edu_member_dtl_all_list(EduCenterMemberVO eduCenterMemberVO) {
		return (List<EduCenterMemberVO>) list("eduCenterMemberDAO.get_edu_member_dtl_all_list",eduCenterMemberVO);
	}
	
	public String set_edu_member_target_reg(EduCenterMemberVO eduCenterMemberVO) {
		return (String) insert("eduCenterMemberDAO.set_edu_member_target_reg", eduCenterMemberVO);
	}
	
	public void set_edu_member_target_mod(EduCenterMemberVO eduCenterMemberVO) {
		update("eduCenterMemberDAO.set_edu_member_target_mod",eduCenterMemberVO);
	}

	public void set_edu_member_target_clear(EduCenterMemberVO eduCenterMemberVO) {
		delete("eduCenterMemberDAO.set_edu_member_target_clear",eduCenterMemberVO);
	}

	public void set_mbr_edu_trgt_mod(EduCenterMemberVO eduCenterMemberVO) {
		update("set_mbr_edu_trgt_mod", eduCenterMemberVO);
	}
	
}
