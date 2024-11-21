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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("eduCertificateService")
public class EduCertificateServiceImpl extends EgovAbstractServiceImpl implements EduCertificateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduCertificateServiceImpl.class);

	@Resource(name = "eduCertificateDAO")
	private EduCertificateDAO eduCertificateDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	@Override
	public List<EduCertificateVO> get_edu_certificate_list(EduCertificateVO eduCertificateVO) {
		return eduCertificateDAO.get_edu_certificate_list(eduCertificateVO);
	}

	@Override
	public int get_edu_certificate_list_totcnt(EduCertificateVO eduCertificateVO) {
		return eduCertificateDAO.get_edu_certificate_list_totcnt(eduCertificateVO);
	}

	@Override
	public List<EduCertificateVO> get_edu_certificate_dtl_list(EduCertificateVO eduCertificateVO) throws Exception {
		return eduCertificateDAO.get_edu_certificate_dtl_list(eduCertificateVO);
	}
	
	@Override
	public int get_edu_certificate_dtl_list_totcnt(EduCertificateVO eduCertificateVO) throws Exception {
		return eduCertificateDAO.get_edu_certificate_dtl_list_totcnt(eduCertificateVO);
	}
	
	@Override
	public EduCertificateVO get_edu_certificate_info(EduCertificateVO eduCertificateVO) throws Exception {
		return eduCertificateDAO.get_edu_certificate_info(eduCertificateVO);
	}
	
	@Override
	public EduCertificateVO get_edu_certificate_info_dtl(EduCertificateVO eduCertificateVO) throws Exception {
		return eduCertificateDAO.get_edu_certificate_info_dtl(eduCertificateVO);
	}


	@Override
	public void remove_edu_certificate(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.remove_edu_certificate(eduCertificateVO);
	}

	@Override
	public void del_edu_certificate(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.del_edu_certificate(eduCertificateVO);
	}

	@Override
	public void remove_edu_certificate_dtl(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.remove_edu_certificate_dtl(eduCertificateVO);
	}

	@Override
	public void del_edu_certificate_dtl(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.del_edu_certificate_dtl(eduCertificateVO);
	}

	@Override
	public void set_edu_certificate_mod(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.set_edu_certificate_mod(eduCertificateVO);
	}

	@Override
	public void set_edu_certificate_mod_dtl(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.set_edu_certificate_mod_dtl(eduCertificateVO);
	}

	@Override
	public void set_edu_certificate_mod_use_unlock(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.set_edu_certificate_mod_use_unlock(eduCertificateVO);
	}

	@Override
	public void set_edu_certificate_mod_use_lock(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.set_edu_certificate_mod_use_lock(eduCertificateVO);
	}

	@Override
	public String set_edu_certificate_reg(EduCertificateVO eduCertificateVO) throws Exception {
		return eduCertificateDAO.set_edu_certificate_reg(eduCertificateVO);
	}

	@Override
	public String set_edu_certificate_issue_record_dtl_reg(EduCertificateVO eduCertificateVO) throws Exception {
		return eduCertificateDAO.set_edu_certificate_issue_record_dtl_reg(eduCertificateVO);
	}

	@Override
	public void set_edu_certificate_issue_record_dtl_cmplt(EduCertificateVO eduCertificateVO) throws Exception {
		eduCertificateDAO.set_edu_certificate_issue_record_dtl_cmplt(eduCertificateVO);
	}

	@Override
	public List<EduCertificateVO> get_edu_certificate_excel_list(EduCertificateVO eduCertificateVO) throws Exception {
		return (List<EduCertificateVO>)eduCertificateDAO.get_edu_certificate_excel_list(eduCertificateVO);
	}

	@Override
	public List<EduCertificateVO> get_edu_certificate_api_list(EduCertificateVO eduCertificateVO) {
		return eduCertificateDAO.get_edu_certificate_api_list(eduCertificateVO);
	}
	
}
