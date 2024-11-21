package egovframework.all.sharelink.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.all.sharelink.service.ShareLinkVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("shareLinkDAO")
public class ShareLinkDAO extends EgovAbstractDAO {

	public ShareLinkVO get_all_sharelink_info(ShareLinkVO shareLinkVO) {
		return (ShareLinkVO)select("get_all_sharelink_info",shareLinkVO);
	}

	public void set_all_sharelink_reg(ShareLinkVO shareLinkVO) {
		insert("set_all_sharelink_reg",shareLinkVO);
	}

	public void set_all_sharelink_uphit(ShareLinkVO shareLinkVO) {
		update("set_all_sharelink_uphit",shareLinkVO);
	}

	public ShareLinkVO allow_all_sharelink_info(ShareLinkVO shareLinkVO) {
		return (ShareLinkVO)select("allow_all_sharelink_info",shareLinkVO);
	}

}
