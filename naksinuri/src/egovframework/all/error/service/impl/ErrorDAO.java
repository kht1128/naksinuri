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
package egovframework.all.error.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.all.error.service.ErrorVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("errorDAO")
public class ErrorDAO extends EgovAbstractDAO {

	public void set_error_reg(ErrorVO errorVO) {
		insert("commError.set_error_reg",errorVO);
	}

	public List<ErrorVO> get_error_list(ErrorVO errorVO) {
		return (List<ErrorVO>)list("commError.get_error_list",errorVO);
	}

	public int get_error_list_totcnt(ErrorVO errorVO) {
		return (int)select("commError.get_error_list_totcnt",errorVO);
	}


}
