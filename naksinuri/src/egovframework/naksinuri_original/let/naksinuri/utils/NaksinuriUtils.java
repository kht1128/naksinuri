package egovframework.naksinuri_original.let.naksinuri.utils;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.naksinuri_original.let.naksinuri.service.FunnelsVO;

public class NaksinuriUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NaksinuriUtils.class);
	
	public static String[] checkFunnels(String url, List<FunnelsVO> funnels) {
		//0 : label , 1 : url , 2 : seach text
		String[] returnStr = new String[3];
		try {
			if(url==null || url.length()==0) {
				returnStr = null;
			} else {			
				returnStr[0] = "etc";
				returnStr[1] = url;
				returnStr[2] = "unknown";
				for(FunnelsVO item : funnels) {
					if(url.contains(item.getUrl_pattern())) {
						returnStr[0] = item.getLabel();
						returnStr[1] = url;
						String[] params = url.split("&");
						for (String param: params) {
						    String key = param.substring(0, param.indexOf("="));
						    if(key.equals(item.getKeyword())) {
						    	String val = param.substring(param.indexOf('=') + 1);
						    	String parseVal = "";
						    	String type_decode = item.getType_decode();
						    	if(type_decode!=null && type_decode.length()!=0) {
						    		parseVal = URLDecoder.decode(val,item.getType_decode());
						    	} else {
						    		parseVal = val;
						    	}
						    	returnStr[2] = parseVal;
						    }
						}
					}
				}
			}
			return returnStr;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static void scanParameters(HttpServletRequest request) {
		LOGGER.debug("- 파라미터 목록 --------------");
	    Enumeration e = request.getParameterNames();
	    String[] data;
	    while (e.hasMoreElements()) {
	      String key = (String)e.nextElement();
	      data = request.getParameterValues(key);
	      if (data != null) {
	        for (String eachdata : data) {
	        	LOGGER.debug(key + " = " + eachdata);
	        }
	      }
	    }
	    LOGGER.debug("------------------------ ");
	}
	
	
	public static boolean isMobileDevice(HttpServletRequest request) {
		boolean isok = false;		
		String user_os = request.getHeader("User-Agent");
		if (user_os.indexOf("iPhone") > 0 || user_os.indexOf("iPad") > 0) {
			isok = true;
		} else if (user_os.indexOf("Android") > 0) {
			isok = true;
		} else if (user_os.indexOf("Windows Phone") > 0) {
			isok = true;
		} else if (user_os.indexOf("Nokia") > 0) {
			isok = true;
		} else {
			isok = false;
		}
		return isok;
	}
	
	
	
}
