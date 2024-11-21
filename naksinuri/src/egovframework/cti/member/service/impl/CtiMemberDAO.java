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

import org.springframework.stereotype.Repository;

import egovframework.cti.member.service.CtiMemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ctiMemberDAO")
public class CtiMemberDAO extends EgovAbstractDAO {

	public CtiMemberVO get_cti_staff_info(CtiMemberVO ctiMemberVO) {
		return (CtiMemberVO)select("ctiMemberDAO.get_cti_staff_info",ctiMemberVO);
	}

	public void remove_cti_staff(CtiMemberVO ctiMemberVO) {
		delete("ctiMemberDAO.remove_cti_staff",ctiMemberVO);
	}

	public String set_cti_staff_reg(CtiMemberVO ctiMemberVO) {
		return (String)insert("ctiMemberDAO.set_cti_staff_reg",ctiMemberVO);
	}

	public void set_cti_staff_mod(CtiMemberVO ctiMemberVO) {
		update("ctiMemberDAO.set_cti_staff_mod",ctiMemberVO);
	}
	
	public CtiMemberVO get_cti_mbr_info(CtiMemberVO ctiMemberVO) {
		return (CtiMemberVO)select("ctiMemberDAO.get_cti_mbr_info",ctiMemberVO);
	}
	
	public List<CtiMemberVO> get_cti_staff_list(CtiMemberVO ctiMemberVO) {
		return (List<CtiMemberVO>)list("ctiMemberDAO.get_cti_staff_list",ctiMemberVO);
	}

	public CtiMemberVO get_mbr_info_with_scan(CtiMemberVO ctiMemberVO) {
		return (CtiMemberVO)select("ctiMemberDAO.get_mbr_info_with_scan",ctiMemberVO);
	}
	
	public List<CtiMemberVO> get_mbr_info_with_scan_all(CtiMemberVO ctiMemberVO) {
		return (List<CtiMemberVO>)list("ctiMemberDAO.get_mbr_info_with_scan_all",ctiMemberVO);
	}
	
	public String set_cti_mbr_info_reg(CtiMemberVO ctiMemberVO) {
		return (String)insert("ctiMemberDAO.set_cti_mbr_info_reg",ctiMemberVO);
	}
	
	public void set_cti_mbr_info_mod(CtiMemberVO ctiMemberVO) {
		update("ctiMemberDAO.set_cti_mbr_info_mod",ctiMemberVO);
	}

	public void remove_cti_mbr(CtiMemberVO ctiMemberVO) {
		delete("ctiMemberDAO.remove_cti_mbr",ctiMemberVO);
	}
	
}
