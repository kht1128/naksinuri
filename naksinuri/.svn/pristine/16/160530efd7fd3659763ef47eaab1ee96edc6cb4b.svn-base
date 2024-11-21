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
package egovframework.educenter.myhistory.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.educenter.myhistory.service.MyHistoryVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("myHistoryDAO")
public class MyHistoryDAO extends EgovAbstractDAO {

	public List<MyHistoryVO> get_educenter_mbrhstry_list(MyHistoryVO myHistoryVO) {
		return (List<MyHistoryVO>) list("get_educenter_mbrhstry_list",myHistoryVO);
	}

	public int get_educenter_mbrhstry_list_totcnt(MyHistoryVO myHistoryVO) {
		return (int) select("get_educenter_mbrhstry_list_totcnt",myHistoryVO);
	}

	public List<MyHistoryVO> get_educenter_mbrhstry_dtl_list(MyHistoryVO myHistoryVO) {
		return (List<MyHistoryVO>) list("get_educenter_mbrhstry_dtl_list",myHistoryVO);
	}

	public int get_educenter_mbrhstry_dtl_list_totcnt(MyHistoryVO myHistoryVO) {
		return (int) select("get_educenter_mbrhstry_dtl_list_totcnt",myHistoryVO);
	}

	public MyHistoryVO get_educenter_mbrhstry_info(MyHistoryVO myHistoryVO) {
		return (MyHistoryVO) select("get_educenter_mbrhstry_info",myHistoryVO);
	}
	
	public List<MyHistoryVO> get_educenter_mbrhstry_info_list(MyHistoryVO myHistoryVO) {
		return (List<MyHistoryVO>) list("get_educenter_mbrhstry_info",myHistoryVO);
	}

	public MyHistoryVO get_educenter_mbrhstry_dtl_info(MyHistoryVO myHistoryVO) {
		return (MyHistoryVO) select("get_educenter_mbrhstry_dtl_info",myHistoryVO);
	}

	public void set_educenter_chng_continue_time(MyHistoryVO myHistoryVO) {
		update("set_educenter_chng_continue_time",myHistoryVO);
	}

	public void set_educenter_mbrhstry_dtl_cmplt(MyHistoryVO myHistoryVO) {
		update("set_educenter_mbrhstry_dtl_cmplt",myHistoryVO);
	}

	public void set_educenter_mbrhstry_cur_cmplt_up(MyHistoryVO myHistoryVO) {
		update("set_educenter_mbrhstry_cur_cmplt_up",myHistoryVO);
	}

	public void set_educenter_mbrhstry_mod(MyHistoryVO myHistoryVO) {
		update("set_educenter_mbrhstry_mod",myHistoryVO);
	}

	public String set_educenter_mbrhstry_reg(MyHistoryVO myHistoryVO) {
		return (String) insert("set_educenter_mbrhstry_reg",myHistoryVO);
	}

	public String set_educenter_mbrhstry_reg_dtl(MyHistoryVO myHistoryVO) {
		return (String) insert("set_educenter_mbrhstry_reg_dtl",myHistoryVO);
	}

	public void set_educenter_mbrhstry_cancel(MyHistoryVO myHistoryVO) {
		update("set_educenter_mbrhstry_cancel",myHistoryVO);
	}

	public void set_educenter_mbrhstry_playlockcode_update(MyHistoryVO myHistoryVO) {
		update("set_educenter_mbrhstry_playlockcode_update",myHistoryVO);
	}

	public int get_educenter_mbrhstry_now_cnt(MyHistoryVO myHistoryVO) {
		return (int) select("get_educenter_mbrhstry_now_cnt", myHistoryVO);
	}

}
