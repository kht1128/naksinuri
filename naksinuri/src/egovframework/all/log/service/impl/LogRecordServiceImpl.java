package egovframework.all.log.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.all.log.service.LogMemberModifyVO;
import egovframework.all.log.service.LogRecordCtiVO;
import egovframework.all.log.service.LogRecordService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.eduadm.main.service.impl.EduCenterServiceImpl;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.utils.PublicUtils;

@Service("logRecordService")
public class LogRecordServiceImpl extends EgovAbstractServiceImpl implements LogRecordService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterServiceImpl.class);

	@Resource(name = "logRecordDAO")
	private LogRecordDAO logRecordDAO;


	@Override
	public String set_log_data(LogRecordVO mLogRecordVO, final HttpServletRequest request) {
		if(request!=null) {
			mLogRecordVO.setREQ_URL(request.getRequestURI());
		}
		return logRecordDAO.set_log_data(mLogRecordVO);
	}

	@Override
	public List<LogRecordVO> get_log_list(LogRecordVO mLogRecordVO) throws Exception {
		return logRecordDAO.get_log_list(mLogRecordVO);
	}

	@Override
	public int get_log_list_totcnt(LogRecordVO mLogRecordVO) throws Exception {
		return logRecordDAO.get_log_list_totcnt(mLogRecordVO);
	}

	@Override
	public void set_log_mbr_mod_data(final String logType, final String logDscrp, final String logUpdMsg,  
			final String MBR_ID, final String MBR_NM, final Object oldData, final Object newData, final LoginVO loginVO,
			final HttpServletRequest request) throws Exception {
		try {
			LogMemberModifyVO mLogMemberModifyVO = new LogMemberModifyVO();
			mLogMemberModifyVO.setLOG_TYPE(logType);
			mLogMemberModifyVO.setLOG_UPD_MSG(logUpdMsg);
			mLogMemberModifyVO.setLOG_DSCRP(logDscrp);
			mLogMemberModifyVO.setREG_MBR_ID(loginVO.getMBR_ID());
			mLogMemberModifyVO.setMBR_ID(MBR_ID);
			mLogMemberModifyVO.setTMP_MBR_NM(MBR_NM);
			if(oldData!=null) mLogMemberModifyVO.setOLD_DATA(mLogMemberModifyVO.encodingFromObjectToJson(oldData));
			if(newData!=null) mLogMemberModifyVO.setNEW_DATA(mLogMemberModifyVO.encodingFromObjectToJson(newData));
			if(loginVO!=null) mLogMemberModifyVO.setMASTER_DATA(mLogMemberModifyVO.encodingFromObjectToJson(loginVO));
			mLogMemberModifyVO.setLOG_INFO_IP(PublicUtils.getClientIpAddr(request));
			mLogMemberModifyVO.setLOG_INFO_USER_AGNET(PublicUtils.getclientUserAgent(request));
			logRecordDAO.set_log_mbr_mod_data(mLogMemberModifyVO);
		} catch(Exception e) {
			LOGGER.debug("errror set_log_mbr_mod("+e.toString()+")");
		}
	}

	@Override
	public List<LogMemberModifyVO> get_log_mbr_mod_list(LogMemberModifyVO mLogMemberModifyVO) throws Exception {
		return logRecordDAO.get_log_mbr_mod_list(mLogMemberModifyVO);
	}

	@Override
	public int get_log_mbr_mod_list_totcnt(LogMemberModifyVO mLogMemberModifyVO) throws Exception {
		return logRecordDAO.get_log_mbr_mod_list_totcnt(mLogMemberModifyVO);
	}

	@Override
	public LogMemberModifyVO get_log_mbr_mod_view_detail(LogMemberModifyVO mLogMemberModifyVO) {
		return logRecordDAO.get_log_mbr_mod_view_detail(mLogMemberModifyVO);
	}

	@Override
	public void set_log_cti_data(final String logType, final String logDscrp, final String logMsg, final String logUpdMsg,
			final Object oldData,final Object newData, 
			final LoginVO loginVO, final HttpServletRequest request) throws Exception {
		PublicUtils mPublicUtils = new PublicUtils();		
		LogRecordCtiVO mLogRecordCtiVO = new LogRecordCtiVO();
		mLogRecordCtiVO.setMBR_IP(mPublicUtils.getClientIpAddr(request));
		mLogRecordCtiVO.setMBR_LV(loginVO.getMBR_LV_ID());
		mLogRecordCtiVO.setMBR_ID(loginVO.getMBR_ID());
		mLogRecordCtiVO.setLOG_DSCRP(logDscrp);
		mLogRecordCtiVO.setLOG_MSG(logMsg);
		mLogRecordCtiVO.setLOG_TYPE(logType);
		if(request!=null) {
			mLogRecordCtiVO.setREQ_URL(request.getRequestURI());
		}
		mLogRecordCtiVO.setLOG_UPD_MSG(logUpdMsg);
		mLogRecordCtiVO.setLOG_INFO_USER_AGNET(mPublicUtils.getclientUserAgent(request));		
		if(oldData!=null) mLogRecordCtiVO.setOLD_DATA(mLogRecordCtiVO.encodingFromObjectToJson(oldData));
		if(newData!=null) mLogRecordCtiVO.setNEW_DATA(mLogRecordCtiVO.encodingFromObjectToJson(newData));
		logRecordDAO.set_log_cti_data(mLogRecordCtiVO);
	}

	@Override
	public List<LogRecordCtiVO> get_log_cti_list(LogRecordCtiVO mLogRecordCtiVO) throws Exception {
		return logRecordDAO.get_log_cti_list(mLogRecordCtiVO);
	}

	@Override
	public int get_log_cti_list_totcnt(LogRecordCtiVO mLogRecordCtiVO) throws Exception {
		return logRecordDAO.get_log_cti_list_totcnt(mLogRecordCtiVO);
	}

	@Override
	public String set_log_edu_data(LogRecordVO logRecordVO, HttpServletRequest request) throws Exception {
		if(request!=null) {
			logRecordVO.setREQ_URL(request.getRequestURI());
		}
		return logRecordDAO.set_log_edu_data(logRecordVO);
	}
	
	@Override
	public String set_log_kcb_data(LogRecordVO mLogRecordVO, final HttpServletRequest request) {
		if(request!=null) {
			mLogRecordVO.setREQ_URL(request.getRequestURI());
		}
		return logRecordDAO.set_log_kcb_data(mLogRecordVO);
	}

	@Override
	public void set_dpcn_log_edu_mod(LogRecordVO mLogRecordVO) {
		logRecordDAO.set_dpcn_log_edu_mod(mLogRecordVO);
	}
}
