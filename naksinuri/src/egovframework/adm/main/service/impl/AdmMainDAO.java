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
package egovframework.adm.main.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("admMainDAO")
public class AdmMainDAO extends EgovAbstractDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdmMainDAO.class);

	public void adm_master_dummy_excute(EgovMap mEgovMap) {
		try {
			String sqlmap_id = mEgovMap.get("sqlmap_id").toString();
			if(sqlmap_id.equals("AdmMain.set_master_pwd")) {
				update(sqlmap_id);
			}
		} catch(Exception e) {
			LOGGER.error("ERROR SQL QUERY");
		}
	}


}
