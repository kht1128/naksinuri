package egovframework.utils;

/**
 * jstl(jsp)에서 아래와 같이 호출하여 사용.
 * 
 * 		<%@ taglib prefix="pfpu"   uri="tld/pfpu.tld"%>
 * 		
 * 		${pfpu:birthHypen(item.MBR_BIRTH)}
 * 		${pfpu:phoneHypen(item.MBR_BIRTH)}
 * 
 * */
public class PublicFormatPatternUtil {
	//생년월일에 하이픈 넣기
	public static String birthHypen(final String str) {
		String formatStr = "";
		String tmpStr = str;		
		if(tmpStr!=null && tmpStr.length()!=0) {
			tmpStr = tmpStr.replaceAll("-","");
			if (tmpStr.length()==13) { //
				formatStr = tmpStr.replaceAll("(\\d{6})(\\d{7})", "$1-$2");
			} else if(tmpStr.length() == 10) {
				formatStr = tmpStr.replaceAll("(\\d{3})(\\d{2})(\\d{5})", "$1-$2-$3");
			} else {
			    formatStr = tmpStr.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3");
			}
		}
		return formatStr;
	}
	//전화번호에 하이픈 넣기
	public static String phoneHypen(final String str) {
		String formatStr = "";
		String tmpStr = str;
		if(tmpStr!=null && tmpStr.length()!=0) {
			tmpStr = tmpStr.replaceAll("-","");
			if (tmpStr.length()==11) {
				formatStr = tmpStr.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
			} else if(tmpStr.length()==8) {
				formatStr = tmpStr.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
			} else {
			    if(tmpStr.indexOf("02")==0) {
			    	formatStr = tmpStr.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
				} else {
					formatStr = tmpStr.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
				}
			}
		}
		return formatStr;
	}
	//전화번호에 하이픈 넣기
	public static String phoneMaskHypen(final String str) {
		String formatStr = "";
		String tmpStr = str;
		if(tmpStr!=null && tmpStr.length()!=0) {
			tmpStr = tmpStr.replaceAll("-","");
			if (tmpStr.length()==11) {
				formatStr = tmpStr.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
			} else if(tmpStr.length()==8) {
				formatStr = tmpStr.replaceAll("(\\d{4})(\\d{4})", "$1-****");
			} else {
			    if(tmpStr.indexOf("02")==0) {
			    	formatStr = tmpStr.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-***-$3");
				} else {
					formatStr = tmpStr.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-***-$3");
				}
			}
		}
		return formatStr;
	}
}
