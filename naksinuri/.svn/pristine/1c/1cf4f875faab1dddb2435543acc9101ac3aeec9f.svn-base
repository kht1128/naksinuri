package egovframework.eduadm.analytics.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.eduadm.analytics.service.EduAnalyticsAdmVO;
import egovframework.eduadm.analytics.service.EduAnalyticsDayVO;
import egovframework.eduadm.analytics.service.EduAnalyticsInfoVO;
import egovframework.eduadm.analytics.service.EduAnalyticsPageVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("EduAnalyticsAdmDAO")
public class EduAnalyticsAdmDAO extends EgovAbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<EduAnalyticsAdmVO> popularpageAdmList(EduAnalyticsAdmVO EduAnalyticsAdmVO){
        return (List<EduAnalyticsAdmVO>) list("eduAnalyticsAdmDAO.popularpageAdmList", EduAnalyticsAdmVO);
    }
	 
    public int popularpageAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
        try {
        	return (Integer)select("eduAnalyticsAdmDAO.popularpageAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}     	
    
    @SuppressWarnings("unchecked")
	public List<EduAnalyticsAdmVO> urlsAdmList(EduAnalyticsAdmVO EduAnalyticsAdmVO){
        return (List<EduAnalyticsAdmVO>) list("eduAnalyticsAdmDAO.urlsAdmList", EduAnalyticsAdmVO);
    }
	 
    public int urlsAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
        try {
        	return (Integer)select("eduAnalyticsAdmDAO.urlsAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}    
    
    public int visitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
        try {
        	return (Integer)select("eduAnalyticsAdmDAO.visitorcountAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
    }

	@SuppressWarnings("unchecked")
	public List<EduAnalyticsAdmVO> sitesummaryAdmList(EduAnalyticsAdmVO EduAnalyticsAdmVO){
        return (List<EduAnalyticsAdmVO>) list("eduAnalyticsAdmDAO.sitesummaryAdmList", EduAnalyticsAdmVO);
    }
	
	public int pageviewAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
        try {
        	return (Integer)select("eduAnalyticsAdmDAO.pageviewAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}     
	 
    public int sitesummaryAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
    	try {
    		return (Integer)select("eduAnalyticsAdmDAO.sitesummaryAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
    }     
	
	public String insertAnalytics(EduAnalyticsAdmVO EduAnalyticsAdmVO) throws Exception {
        return (String) insert("eduAnalyticsAdmDAO.insertAnalytics",EduAnalyticsAdmVO);
    }

	public int allvisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.allvisitorcountAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int revisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.revisitorcountAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public int newvisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.newvisitorcountAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int repvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.repvvisitorcountAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int newpvvisitorcountAdmListTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.newpvvisitorcountAdmListTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}
	
	public List<EduAnalyticsDayVO> visitorcountDaysAdmList(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		return (List<EduAnalyticsDayVO>) list("eduAnalyticsAdmDAO.visitorcountDaysAdmList", EduAnalyticsAdmVO);
	}

	public List<EduAnalyticsInfoVO> visitorChannelInputTypeList(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		return (List<EduAnalyticsInfoVO>) list("eduAnalyticsAdmDAO.visitorChannelInputTypeList", EduAnalyticsAdmVO);
	}

	public List<EduAnalyticsInfoVO> visitorDeviceTypeList(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		return (List<EduAnalyticsInfoVO>) list("eduAnalyticsAdmDAO.visitorDeviceTypeList", EduAnalyticsAdmVO);
	}

	public List<EduAnalyticsInfoVO> visitorChannelInputNameList(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		return (List<EduAnalyticsInfoVO>) list("eduAnalyticsAdmDAO.visitorChannelInputNameList", EduAnalyticsAdmVO);
	}

	public int visitorChannelInputTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.visitorChannelInputTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public int visitorChannelInputAllTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.visitorChannelInputAllTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public List<EduAnalyticsInfoVO> visitorOsAllList(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		return (List<EduAnalyticsInfoVO>) list("eduAnalyticsAdmDAO.visitorOsAllList", EduAnalyticsAdmVO);
	}

	public int visitorOsPcTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.visitorOsPcTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public int visitorOsMobileTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.visitorOsMobileTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public int visitorOsEtcTotCnt(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		try {
			return (Integer)select("eduAnalyticsAdmDAO.visitorOsEtcTotCnt", EduAnalyticsAdmVO);
		} catch(Exception e) {
			return 0;
		}
	}

	public List<EduAnalyticsInfoVO> visitorOsDetailAllList(EduAnalyticsAdmVO EduAnalyticsAdmVO) {
		return (List<EduAnalyticsInfoVO>) list("eduAnalyticsAdmDAO.visitorOsDetailAllList", EduAnalyticsAdmVO);
	}

	public List<EduAnalyticsPageVO> visitorPageTotList(EduAnalyticsPageVO EduAnalyticsPageVO) {
		return (List<EduAnalyticsPageVO>) list("eduAnalyticsAdmDAO.visitorPageTotList", EduAnalyticsPageVO);
	}
	
	
	

}