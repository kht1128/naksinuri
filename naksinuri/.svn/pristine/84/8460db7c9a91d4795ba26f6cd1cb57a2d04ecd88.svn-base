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
package egovframework.cti.main.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.cti.main.service.CtiBoardVO;
import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ctiMainDAO")
public class CtiMainDAO extends EgovAbstractDAO {

	public List<NaksinuriZisikinVO> get_cti_zazu_list(NaksinuriZisikinVO naksinuriZisikinVO) {
		return (List<NaksinuriZisikinVO>) list("ctiMainDAO.get_cti_zazu_list",naksinuriZisikinVO);
	}

	public int get_cti_zazu_list_totcnt(NaksinuriZisikinVO naksinuriZisikinVO) {
		return (int) select("ctiMainDAO.get_cti_zazu_list_totcnt",naksinuriZisikinVO);
	}

	public List<CtiBoardVO> get_cti_manual_list(CtiBoardVO ctiBoardVO) {
		return (List<CtiBoardVO>) list("ctiMainDAO.get_cti_manual_list",ctiBoardVO);
	}

	public void set_cti_manual_insert(CtiBoardVO insertCtiBoardVO) {
		insert("set_cti_manual_insert",insertCtiBoardVO);
		
	}

	public void set_cti_menual_mod(CtiBoardVO updateCtiBoardVO) {
		update("set_cti_menual_mod",updateCtiBoardVO);
		
	}

	public void set_cti_menual_delete(CtiBoardVO deleteCtiBoardVO) {
		delete("set_cti_menual_delete",deleteCtiBoardVO);
		
	}
	
}
