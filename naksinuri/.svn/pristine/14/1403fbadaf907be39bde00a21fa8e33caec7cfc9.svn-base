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
package egovframework.eduadm.certificate.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("eduCertificateDAO")
public class EduCertificateDAO extends EgovAbstractDAO {

	public List<EduCertificateVO> get_edu_certificate_list(EduCertificateVO eduCertificateVO) {
		return (List<EduCertificateVO>) list("get_edu_certificate_list",eduCertificateVO);
	}

	public int get_edu_certificate_list_totcnt(EduCertificateVO eduCertificateVO) {
		return (int) select("get_edu_certificate_list_totcnt",eduCertificateVO);
	}

	public List<EduCertificateVO> get_edu_certificate_dtl_list(EduCertificateVO eduCertificateVO) {
		return (List<EduCertificateVO>) list("get_edu_certificate_dtl_list",eduCertificateVO);
	}

	public int get_edu_certificate_dtl_list_totcnt(EduCertificateVO eduCertificateVO) {
		return (int) select("get_edu_certificate_dtl_list_totcnt",eduCertificateVO);
	}

	public EduCertificateVO get_edu_certificate_info(EduCertificateVO eduCertificateVO) {
		return (EduCertificateVO) select("get_edu_certificate_info",eduCertificateVO);
	}
	
	public EduCertificateVO get_edu_certificate_info_dtl(EduCertificateVO eduCertificateVO) {
		return (EduCertificateVO) select("get_edu_certificate_info_dtl",eduCertificateVO);
	}

	public void remove_edu_certificate(EduCertificateVO eduCertificateVO) {
		delete("remove_edu_certificate",eduCertificateVO);
	}

	public void del_edu_certificate(EduCertificateVO eduCertificateVO) {
		update("del_edu_certificate",eduCertificateVO);
	}

	public void remove_edu_certificate_dtl(EduCertificateVO eduCertificateVO) {
		delete("remove_edu_certificate_dtl",eduCertificateVO);
	}

	public void del_edu_certificate_dtl(EduCertificateVO eduCertificateVO) {
		update("del_edu_certificate_dtl",eduCertificateVO);
	}

	public void set_edu_certificate_mod(EduCertificateVO eduCertificateVO) {
		update("set_edu_certificate_mod",eduCertificateVO);
	}

	public void set_edu_certificate_mod_dtl(EduCertificateVO eduCertificateVO) {
		update("set_edu_certificate_mod_dtl",eduCertificateVO);
	}

	public void set_edu_certificate_mod_use_unlock(EduCertificateVO eduCertificateVO) {
		update("set_edu_certificate_mod_use_unlock",eduCertificateVO);
	}
	
	public void set_edu_certificate_mod_use_lock(EduCertificateVO eduCertificateVO) {
		update("set_edu_certificate_mod_use_lock",eduCertificateVO);
	}

	public String set_edu_certificate_reg(EduCertificateVO eduCertificateVO) {
		return (String) insert("set_edu_certificate_reg",eduCertificateVO);
	}

	public String set_edu_certificate_issue_record_dtl_reg(EduCertificateVO eduCertificateVO) {
		return (String) insert("set_edu_certificate_issue_record_dtl_reg",eduCertificateVO);
	}

	public void set_edu_certificate_issue_record_dtl_cmplt(EduCertificateVO eduCertificateVO) {
		update("set_edu_certificate_issue_record_dtl_cmplt",eduCertificateVO);
	}

	public List<EduCertificateVO> get_edu_certificate_excel_list(EduCertificateVO eduCertificateVO) {
		return (List<EduCertificateVO>) list("get_edu_certificate_excel_list",eduCertificateVO);
	}

	public List<EduCertificateVO> get_edu_certificate_api_list(EduCertificateVO eduCertificateVO) {
		return (List<EduCertificateVO>) list("get_edu_certificate_api_list", eduCertificateVO);
	}
}
