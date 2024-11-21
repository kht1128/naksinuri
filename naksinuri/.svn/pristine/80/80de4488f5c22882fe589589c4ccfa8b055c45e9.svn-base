package egovframework.seadm.analytics.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.seadm.analytics.service.AnalyticsAdmVO;
import egovframework.seadm.analytics.service.AnalyticsDayVO;
import egovframework.seadm.analytics.service.AnalyticsInfoVO;
import egovframework.seadm.analytics.service.AnalyticsPageVO;

@Repository("analyticsAdmDAO")
public class AnalyticsAdmDAO extends EgovAbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<AnalyticsAdmVO> popularpageAdmList(AnalyticsAdmVO analyticsAdmVO){
        return (List<AnalyticsAdmVO>) list("analyticsAdmDAO.popularpageAdmList", analyticsAdmVO);
    }
	 
    public int popularpageAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
        try {
        	return (Integer)select("analyticsAdmDAO.popularpageAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}     	
    
    @SuppressWarnings("unchecked")
	public List<AnalyticsAdmVO> urlsAdmList(AnalyticsAdmVO analyticsAdmVO){
        return (List<AnalyticsAdmVO>) list("analyticsAdmDAO.urlsAdmList", analyticsAdmVO);
    }
	 
    public int urlsAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
        try {
        	return (Integer)select("analyticsAdmDAO.urlsAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}    
    
    public int visitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
        try {
        	return (Integer)select("analyticsAdmDAO.visitorcountAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
    }

	@SuppressWarnings("unchecked")
	public List<AnalyticsAdmVO> sitesummaryAdmList(AnalyticsAdmVO analyticsAdmVO){
        return (List<AnalyticsAdmVO>) list("analyticsAdmDAO.sitesummaryAdmList", analyticsAdmVO);
    }
	
	public int pageviewAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
        try {
        	return (Integer)select("analyticsAdmDAO.pageviewAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}     
	 
    public int sitesummaryAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
    	try {
    		return (Integer)select("analyticsAdmDAO.sitesummaryAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
    }     
	
	public String insertAnalytics(AnalyticsAdmVO analyticsAdmVO) throws Exception {
        return (String) insert("AnalyticsAdmDAO.insertAnalytics",analyticsAdmVO);
    }

	public int allvisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.allvisitorcountAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int revisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.revisitorcountAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public int newvisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.newvisitorcountAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int repvvisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.repvvisitorcountAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int newpvvisitorcountAdmListTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.newpvvisitorcountAdmListTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public List<AnalyticsDayVO> visitorcountDaysAdmList(AnalyticsAdmVO analyticsAdmVO) {
		return (List<AnalyticsDayVO>) list("analyticsAdmDAO.visitorcountDaysAdmList", analyticsAdmVO);
	}

	public List<AnalyticsInfoVO> visitorChannelInputTypeList(AnalyticsAdmVO analyticsAdmVO) {
		return (List<AnalyticsInfoVO>) list("analyticsAdmDAO.visitorChannelInputTypeList", analyticsAdmVO);
	}

	public List<AnalyticsInfoVO> visitorDeviceTypeList(AnalyticsAdmVO analyticsAdmVO) {
		return (List<AnalyticsInfoVO>) list("analyticsAdmDAO.visitorDeviceTypeList", analyticsAdmVO);
	}

	public List<AnalyticsInfoVO> visitorChannelInputNameList(AnalyticsAdmVO analyticsAdmVO) {
		return (List<AnalyticsInfoVO>) list("analyticsAdmDAO.visitorChannelInputNameList", analyticsAdmVO);
	}

	public int visitorChannelInputTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.visitorChannelInputTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public int visitorChannelInputAllTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.visitorChannelInputAllTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public List<AnalyticsInfoVO> visitorOsAllList(AnalyticsAdmVO analyticsAdmVO) {
		return (List<AnalyticsInfoVO>) list("analyticsAdmDAO.visitorOsAllList", analyticsAdmVO);
	}

	public int visitorOsPcTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.visitorOsPcTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public int visitorOsMobileTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.visitorOsMobileTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public int visitorOsEtcTotCnt(AnalyticsAdmVO analyticsAdmVO) {
		try {
			return (Integer)select("analyticsAdmDAO.visitorOsEtcTotCnt", analyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public List<AnalyticsInfoVO> visitorOsDetailAllList(AnalyticsAdmVO analyticsAdmVO) {
		return (List<AnalyticsInfoVO>) list("analyticsAdmDAO.visitorOsDetailAllList", analyticsAdmVO);
	}

	public List<AnalyticsPageVO> visitorPageTotList(AnalyticsPageVO analyticsPageVO) {
		return (List<AnalyticsPageVO>) list("analyticsAdmDAO.visitorPageTotList", analyticsPageVO);
	}
	
	
	

}