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
package egovframework.all.log.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.all.login.service.LoginVO;

public interface LogRecordService {

	//log_rec_tb
	String set_log_data(LogRecordVO mLogRecordVO, final HttpServletRequest request) throws Exception;
	List<LogRecordVO> get_log_list(LogRecordVO mLogRecordVO) throws Exception;
	int get_log_list_totcnt(LogRecordVO mLogRecordVO) throws Exception;
	
	//log_mbr_mod_tb
	void set_log_mbr_mod_data(final String logType, final String logDscrp, final String logUpdMsg,
			final String MBR_ID, final String MBR_NM, final Object oldData, final Object newData, 
			final LoginVO loginVO, final HttpServletRequest request) throws Exception;
	List<LogMemberModifyVO> get_log_mbr_mod_list(LogMemberModifyVO mLogMemberModifyVO) throws Exception;
	int get_log_mbr_mod_list_totcnt(LogMemberModifyVO mLogMemberModifyVO) throws Exception;
	LogMemberModifyVO get_log_mbr_mod_view_detail(LogMemberModifyVO mLogMemberModifyVO);
	
	//log_cti_tb
	void set_log_cti_data(final String logType, final String logDscrp, final String logMsg, final String logUpdMsg, 
			final Object oldData,final Object newData, 
			final LoginVO loginVO, final HttpServletRequest request) throws Exception;
	List<LogRecordCtiVO> get_log_cti_list(LogRecordCtiVO mLogRecordCtiVO) throws Exception;
	int get_log_cti_list_totcnt(LogRecordCtiVO mLogRecordCtiVO) throws Exception;
	
	// log_edu_tb
	String set_log_edu_data(LogRecordVO logRecordVO, final HttpServletRequest request) throws Exception;
	void set_dpcn_log_edu_mod(LogRecordVO mLogRecordVO);
	
	// log_kcb_tb
	String set_log_kcb_data(LogRecordVO mLogRecordVO, final HttpServletRequest request) throws Exception;
}
