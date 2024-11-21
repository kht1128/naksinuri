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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.educenter.myhistory.service.MyHistoryService;
import egovframework.educenter.myhistory.service.MyHistoryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("myHistoryService")
public class MyHistoryServiceImpl extends EgovAbstractServiceImpl implements MyHistoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyHistoryServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "myHistoryDAO")
	private MyHistoryDAO myHistoryDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	@Override
	public List<MyHistoryVO> get_educenter_mbrhstry_list(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.get_educenter_mbrhstry_list(myHistoryVO);
	}

	@Override
	public int get_educenter_mbrhstry_list_totcnt(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.get_educenter_mbrhstry_list_totcnt(myHistoryVO);
	}

	@Override
	public List<MyHistoryVO> get_educenter_mbrhstry_dtl_list(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.get_educenter_mbrhstry_dtl_list(myHistoryVO);
	}

	@Override
	public int get_educenter_mbrhstry_dtl_list_totcnt(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.get_educenter_mbrhstry_dtl_list_totcnt(myHistoryVO);
	}

	@Override
	public MyHistoryVO get_educenter_mbrhstry_info(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.get_educenter_mbrhstry_info(myHistoryVO);
	}
	
	@Override
	public List<MyHistoryVO> get_educenter_mbrhstry_info_list(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.get_educenter_mbrhstry_info_list(myHistoryVO);
	}

	@Override
	public MyHistoryVO get_educenter_mbrhstry_dtl_info(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.get_educenter_mbrhstry_dtl_info(myHistoryVO);
	}

	@Override
	public void set_educenter_chng_continue_time(MyHistoryVO myHistoryVO) throws Exception {
		myHistoryDAO.set_educenter_chng_continue_time(myHistoryVO);
	}

	@Override
	public void set_educenter_mbrhstry_dtl_cmplt(MyHistoryVO myHistoryVO) throws Exception {
		myHistoryDAO.set_educenter_mbrhstry_dtl_cmplt(myHistoryVO);
	}

	@Override
	public void set_educenter_mbrhstry_cur_cmplt_up(MyHistoryVO myHistoryVO) throws Exception {
		myHistoryDAO.set_educenter_mbrhstry_cur_cmplt_up(myHistoryVO);
	}

	@Override
	public void set_educenter_mbrhstry_mod(MyHistoryVO myHistoryVO) throws Exception {
		myHistoryDAO.set_educenter_mbrhstry_mod(myHistoryVO);
	}

	@Override
	public String set_educenter_mbrhstry_reg(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.set_educenter_mbrhstry_reg(myHistoryVO);
	}

	@Override
	public String set_educenter_mbrhstry_reg_dtl(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.set_educenter_mbrhstry_reg_dtl(myHistoryVO);
	}

	@Override
	public void set_educenter_mbrhstry_cancel(MyHistoryVO myHistoryVO) throws Exception {
		myHistoryDAO.set_educenter_mbrhstry_cancel(myHistoryVO);
	}

	@Override
	public void set_educenter_mbrhstry_playlockcode_update(MyHistoryVO myHistoryVO) throws Exception {
		myHistoryDAO.set_educenter_mbrhstry_playlockcode_update(myHistoryVO);
	}

	@Override
	public int get_educenter_mbrhstry_now_cnt(MyHistoryVO myHistoryVO) throws Exception {
		return myHistoryDAO.get_educenter_mbrhstry_now_cnt(myHistoryVO);
	}


	

}
