package egovframework.eduadm.analytics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.eduadm.analytics.service.EduAnalyticsAdmService;
import egovframework.eduadm.analytics.service.EduAnalyticsAdmVO;
import egovframework.eduadm.analytics.service.EduAnalyticsDayVO;
import egovframework.eduadm.analytics.service.EduAnalyticsInfoVO;
import egovframework.eduadm.analytics.service.EduAnalyticsPageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("EduAnalyticsAdmService")
public class EduAnalyticsAdmServiceImpl extends EgovAbstractServiceImpl implements EduAnalyticsAdmService {

	/** boardDAO */
	@Resource(name="EduAnalyticsAdmDAO")
	private EduAnalyticsAdmDAO analyticsAdmDAO;
	
	
	@Override
	public List<EduAnalyticsAdmVO> popularpageAdmList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.popularpageAdmList(EduAnalyticsAdmVO);
	}
	
    @Override
	public int popularpageAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.popularpageAdmListTotCnt(EduAnalyticsAdmVO);
    }
    
    @Override
	public List<EduAnalyticsAdmVO> urlsAdmList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.urlsAdmList(EduAnalyticsAdmVO);
	}
	
    @Override
	public int urlsAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.urlsAdmListTotCnt(EduAnalyticsAdmVO);
    }
    
    @Override
	public int visitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.visitorcountAdmListTotCnt(EduAnalyticsAdmVO);
    }
    
    @Override
	public int revisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.revisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
    }
    
    @Override
	public int allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
    }

    @Override
	public List<EduAnalyticsAdmVO> sitesummaryAdmList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.sitesummaryAdmList(EduAnalyticsAdmVO);
	}
	
    @Override
	public int sitesummaryAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.sitesummaryAdmListTotCnt(EduAnalyticsAdmVO);
    }
    
    @Override
	public int pageviewAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.pageviewAdmListTotCnt(EduAnalyticsAdmVO);
    }

	public String insertAnalytics(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception  {
		/*
		try {
			ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		    HttpServletRequest request = sra.getRequest();
			EduAnalyticsAdmVO.setVISIT_SSKEY(request.getSession().getId());
		} catch(Exception e) {
			
		}
		*/
    	String result = analyticsAdmDAO.insertAnalytics(EduAnalyticsAdmVO);
    	return result;
    }

	@Override
	public List<EduAnalyticsDayVO> visitorcountDaysAdmList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorcountDaysAdmList(EduAnalyticsAdmVO);
	}

	@Override
	public List<EduAnalyticsInfoVO> visitorChannelInputTypeList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorChannelInputTypeList(EduAnalyticsAdmVO);
	}

	@Override
	public List<EduAnalyticsInfoVO> visitorDeviceTypeList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorDeviceTypeList(EduAnalyticsAdmVO);
	}

	@Override
	public int newvisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.newvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
	}

	@Override
	public int newpvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.newpvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
	}

	@Override
	public int repvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.repvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO);
	}

	@Override
	public List<EduAnalyticsInfoVO> visitorChannelInputNameList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorChannelInputNameList(EduAnalyticsAdmVO);
	}

	@Override
	public int visitorChannelInputTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorChannelInputTotCnt(EduAnalyticsAdmVO);
	}

	@Override
	public int visitorChannelInputAllTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorChannelInputAllTotCnt(EduAnalyticsAdmVO);
	}

	@Override
	public List<EduAnalyticsInfoVO> visitorOsAllList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsAllList(EduAnalyticsAdmVO);
	}


	@Override
	public int visitorOsPcTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsPcTotCnt(EduAnalyticsAdmVO);
	}

	@Override
	public int visitorOsMobileTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsMobileTotCnt(EduAnalyticsAdmVO);
	}

	@Override
	public int visitorOsEtcTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsEtcTotCnt(EduAnalyticsAdmVO);
	}

	@Override
	public List<EduAnalyticsInfoVO> visitorOsDetailAllList(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsDetailAllList(EduAnalyticsAdmVO);
	}

	@Override
	public List<EduAnalyticsPageVO> visitorPageTotList(EduAnalyticsPageVO EduAnalyticsPageVO) throws Exception {
		return analyticsAdmDAO.visitorPageTotList(EduAnalyticsPageVO);
	}

	
}
