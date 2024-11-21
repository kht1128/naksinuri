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
package egovframework.all.log.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.all.log.service.LogMemberModifyVO;
import egovframework.all.log.service.LogRecordCtiVO;
import egovframework.all.log.service.LogRecordVO;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("logRecordDAO")
public class LogRecordDAO extends EgovAbstractDAO {

	public String set_log_data(LogRecordVO mLogRecordVO) {
		return (String)insert("commLog.set_log_data",mLogRecordVO);
	}

	public List<LogRecordVO> get_log_list(LogRecordVO mLogRecordVO) {
		return (List<LogRecordVO>)list("commLog.get_log_list",mLogRecordVO);
	}

	public int get_log_list_totcnt(LogRecordVO mLogRecordVO) {
		return (int)select("commLog.get_log_list_totcnt",mLogRecordVO);
	}

	public void set_log_mbr_mod_data(LogMemberModifyVO mLogMemberModifyVO) {
		insert("commLog.set_log_mbr_mod_data",mLogMemberModifyVO);
	}

	public List<LogMemberModifyVO> get_log_mbr_mod_list(LogMemberModifyVO mLogMemberModifyVO) {
		return (List<LogMemberModifyVO>)list("commLog.get_log_mbr_mod_list",mLogMemberModifyVO);
	}

	public int get_log_mbr_mod_list_totcnt(LogMemberModifyVO mLogMemberModifyVO) {
		return (int)select("commLog.get_log_mbr_mod_list_totcnt",mLogMemberModifyVO);
	}

	public LogMemberModifyVO get_log_mbr_mod_view_detail(LogMemberModifyVO mLogMemberModifyVO) {
		return (LogMemberModifyVO)select("commLog.get_log_mbr_mod_view_detail",mLogMemberModifyVO);
	}

	public void set_log_cti_data(LogRecordCtiVO mLogRecordCtiVO) {
		insert("commLog.set_log_cti_data",mLogRecordCtiVO);
	}

	public List<LogRecordCtiVO> get_log_cti_list(LogRecordCtiVO mLogRecordCtiVO) {
		return (List<LogRecordCtiVO>)list("commLog.get_log_cti_list",mLogRecordCtiVO);
	}

	public int get_log_cti_list_totcnt(LogRecordCtiVO mLogRecordCtiVO) {
		return (int)select("commLog.get_log_cti_list_totcnt",mLogRecordCtiVO);
	}

	public String set_log_edu_data(LogRecordVO logRecordVO) {
		return (String)insert("commLog.set_log_edu_data", logRecordVO);
	}

	public String set_log_kcb_data(LogRecordVO mLogRecordVO) {
		return (String)insert("commLog.set_log_kcb_data",mLogRecordVO);
	}
	public void set_dpcn_log_edu_mod(LogRecordVO mLogRecordVO) {
		update("commLog.set_dpcn_log_edu_mod",mLogRecordVO);
	}

}
