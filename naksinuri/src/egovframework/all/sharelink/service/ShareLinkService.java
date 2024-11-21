package egovframework.all.sharelink.service;

public interface ShareLinkService {

	ShareLinkVO get_all_sharelink_info(ShareLinkVO shareLinkVO) throws Exception;
	void set_all_sharelink_reg(ShareLinkVO shareLinkVO) throws Exception;
	void set_all_sharelink_uphit(ShareLinkVO shareLinkVO) throws Exception;
	ShareLinkVO allow_all_sharelink_info(ShareLinkVO shareLinkVO) throws Exception;
	
}
