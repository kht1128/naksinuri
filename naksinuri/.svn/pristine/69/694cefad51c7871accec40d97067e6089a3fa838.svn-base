package egovframework.all.auth.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * 일반회원관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *
 * </pre>
 */
@Controller
public class IpinController {
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    @RequestMapping("/ipin/ipinRequest.do")
    public String sendIpinRequest(
    		HttpServletRequest req,
    		HttpServletResponse res,
            ModelMap model
            )throws Exception {
    	    	
    	req.setAttribute("cid", propertiesService.getString("Ipin.customerId"));
    	req.setAttribute("svcNo", propertiesService.getString("Ipin.serviceNo"));
    	req.setAttribute("host", getHostname(req));
    	
        return "ipin/ipinRequest";
    } 
    
    @RequestMapping(value="/ipin/ipinResult.do")
    public String getInpinResult(
            Model model
            )throws Exception {
        return "ipin/ipinResult";
    }
    
    @RequestMapping("/ipin/ipinPopup.do")
    public String popIpinResult(
    		HttpServletRequest req,
    		Model model
    		)throws Exception {
    	
    	req.setAttribute("host", getHostname(req));
    	return "ipin/ipinPopup";
    }
    
    public String getHostname(HttpServletRequest req) {
    	StringBuffer rUrl= req.getRequestURL();
    	int pos1 = rUrl.indexOf("//");
    	int pos2 = rUrl.indexOf("/", pos1 + 2);
    	
		String host = rUrl.substring(0, pos2);
		
		return host;
    }
}