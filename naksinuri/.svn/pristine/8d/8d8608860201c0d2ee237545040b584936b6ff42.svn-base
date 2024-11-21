package egovframework.all.sharelink.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.all.sharelink.service.ShareLinkService;
import egovframework.all.sharelink.service.ShareLinkVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("shareLinkService")
public class ShareLinkServiceImpl extends EgovAbstractServiceImpl implements ShareLinkService {
	
	@Resource(name="shareLinkDAO")
	private ShareLinkDAO shareLinkDAO;

	@Override
	public ShareLinkVO get_all_sharelink_info(ShareLinkVO shareLinkVO) throws Exception {
		return shareLinkDAO.get_all_sharelink_info(shareLinkVO);
	}

	@Override
	public void set_all_sharelink_reg(ShareLinkVO shareLinkVO) throws Exception {
		shareLinkDAO.set_all_sharelink_reg(shareLinkVO);
	}

	@Override
	public void set_all_sharelink_uphit(ShareLinkVO shareLinkVO) throws Exception {
		shareLinkDAO.set_all_sharelink_uphit(shareLinkVO);
	}

	@Override
	public ShareLinkVO allow_all_sharelink_info(ShareLinkVO shareLinkVO) throws Exception {
		return shareLinkDAO.allow_all_sharelink_info(shareLinkVO);
	}	

		
}