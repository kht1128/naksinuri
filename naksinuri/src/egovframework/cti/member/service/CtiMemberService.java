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
package egovframework.cti.member.service;

import java.util.List;

public interface CtiMemberService {

	CtiMemberVO get_cti_staff_info(CtiMemberVO ctiMemberVO) throws Exception;
	CtiMemberVO get_cti_mbr_info(CtiMemberVO ctiMemberVO) throws Exception;
	List<CtiMemberVO> get_cti_staff_list(CtiMemberVO ctiMemberVO) throws Exception;

	String set_cti_mbr_info_reg(CtiMemberVO ctiMemberVO) throws Exception;
	void remove_cti_staff(CtiMemberVO ctiMemberVO) throws Exception;
	String set_cti_staff_reg(CtiMemberVO ctiMemberVO) throws Exception;
	void set_cti_staff_mod(CtiMemberVO ctiMemberVO) throws Exception;
	CtiMemberVO get_mbr_info_with_scan(CtiMemberVO ctiMemberVO) throws Exception;
	List<CtiMemberVO> get_mbr_info_with_scan_all(CtiMemberVO ctiMemberVO) throws Exception;
	void set_cti_mbr_info_mod(CtiMemberVO ctiMemberVO) throws Exception;
	void remove_cti_mbr(CtiMemberVO ctiMemberVO) throws Exception;
	
}
