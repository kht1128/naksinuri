package egovframework.seadm.analytics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.seadm.analytics.service.AnalyticsAdmService;
import egovframework.seadm.analytics.service.AnalyticsAdmVO;
import egovframework.seadm.analytics.service.AnalyticsDayVO;
import egovframework.seadm.analytics.service.AnalyticsInfoVO;
import egovframework.seadm.analytics.service.AnalyticsPageVO;

@Service("AnalyticsAdmService")
public class AnalyticsAdmServiceImpl extends EgovAbstractServiceImpl implements AnalyticsAdmService {

	/** boardDAO */
	@Resource(name="analyticsAdmDAO")
	private AnalyticsAdmDAO analyticsAdmDAO;
	
	
	@Override
	public List<AnalyticsAdmVO> popularpageAdmList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.popularpageAdmList(analyticsAdmVO);
	}
	
    @Override
	public int popularpageAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.popularpageAdmListTotCnt(analyticsAdmVO);
    }
    
    @Override
	public List<AnalyticsAdmVO> urlsAdmList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.urlsAdmList(analyticsAdmVO);
	}
	
    @Override
	public int urlsAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.urlsAdmListTotCnt(analyticsAdmVO);
    }
    
    @Override
	public int visitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.visitorcountAdmListTotCnt(analyticsAdmVO);
    }
    
    @Override
	public int revisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.revisitorcountAdmListTotCnt(analyticsAdmVO);
    }
    
    @Override
	public int allvisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.allvisitorcountAdmListTotCnt(analyticsAdmVO);
    }

    @Override
	public List<AnalyticsAdmVO> sitesummaryAdmList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.sitesummaryAdmList(analyticsAdmVO);
	}
	
    @Override
	public int sitesummaryAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.sitesummaryAdmListTotCnt(analyticsAdmVO);
    }
    
    @Override
	public int pageviewAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
    	return analyticsAdmDAO.pageviewAdmListTotCnt(analyticsAdmVO);
    }

	public String insertAnalytics(AnalyticsAdmVO analyticsAdmVO) throws Exception  {
		/*
		try {
			ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		    HttpServletRequest request = sra.getRequest();
			analyticsAdmVO.setVISIT_SSKEY(request.getSession().getId());
		} catch(Exception e) {
			
		}
		*/
    	String result = analyticsAdmDAO.insertAnalytics(analyticsAdmVO);
    	return result;
    }

	@Override
	public List<AnalyticsDayVO> visitorcountDaysAdmList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorcountDaysAdmList(analyticsAdmVO);
	}

	@Override
	public List<AnalyticsInfoVO> visitorChannelInputTypeList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorChannelInputTypeList(analyticsAdmVO);
	}

	@Override
	public List<AnalyticsInfoVO> visitorDeviceTypeList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorDeviceTypeList(analyticsAdmVO);
	}

	@Override
	public int newvisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.newvisitorcountAdmListTotCnt(analyticsAdmVO);
	}

	@Override
	public int newpvvisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.newpvvisitorcountAdmListTotCnt(analyticsAdmVO);
	}

	@Override
	public int repvvisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.repvvisitorcountAdmListTotCnt(analyticsAdmVO);
	}

	@Override
	public List<AnalyticsInfoVO> visitorChannelInputNameList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorChannelInputNameList(analyticsAdmVO);
	}

	@Override
	public int visitorChannelInputTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorChannelInputTotCnt(analyticsAdmVO);
	}

	@Override
	public int visitorChannelInputAllTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorChannelInputAllTotCnt(analyticsAdmVO);
	}

	@Override
	public List<AnalyticsInfoVO> visitorOsAllList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsAllList(analyticsAdmVO);
	}


	@Override
	public int visitorOsPcTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsPcTotCnt(analyticsAdmVO);
	}

	@Override
	public int visitorOsMobileTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsMobileTotCnt(analyticsAdmVO);
	}

	@Override
	public int visitorOsEtcTotCnt(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsEtcTotCnt(analyticsAdmVO);
	}

	@Override
	public List<AnalyticsInfoVO> visitorOsDetailAllList(AnalyticsAdmVO analyticsAdmVO) throws Exception {
		return analyticsAdmDAO.visitorOsDetailAllList(analyticsAdmVO);
	}

	@Override
	public List<AnalyticsPageVO> visitorPageTotList(AnalyticsPageVO analyticsPageVO) throws Exception {
		return analyticsAdmDAO.visitorPageTotList(analyticsPageVO);
	}

	
}
