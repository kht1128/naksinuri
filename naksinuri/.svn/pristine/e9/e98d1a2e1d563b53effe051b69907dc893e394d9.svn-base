package egovframework.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Class Name : SealifeUtils.java
 * @Description : SealifeUtils Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.-  	 김정하         최초생성
 * @ 2019.01.18  김정하        수정
 *
 * @author 개발팀
 * @since 2018. 12.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by jhkim All right reserved.
 */
public class PublicUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PublicUtils.class);
		
	public static void scanPrintInfoWithParameters(HttpServletRequest request) {
		try {
			LOGGER.debug("########## scanPrintInfoWithParameters ##########");
			LOGGER.debug("URL 경로 : " + request.getRequestURI());
			LOGGER.debug("URL 도메인 : " + request.getRequestURL());
			LOGGER.debug("파라미터  ------------------");
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
		    LOGGER.debug("getClientIpAddr : " + getClientIpAddr(request));
			LOGGER.debug("getClientBrowser : " + getClientBrowser(request));
			LOGGER.debug("getClientOS : " + getClientOS(request));
			LOGGER.debug("isMobileDevice : " + (isMobileDevice(request)?"yes":"no"));
		} catch(Exception e) {
			LOGGER.debug("[fail scan HttpServletRequest] "+e.toString());
		}
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
	
	public static String getClientIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");	 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }	 
	    return ip;
	}
	
	public static String getClientBrowser(HttpServletRequest request) {
		String browser = "";		
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0) {
			browser = "IE";
		} else if (userAgent.indexOf("Edge") > 0) {
			browser = "Edge";
		} else if (userAgent.indexOf("Presto") > 0) {
			browser = "Opera";
		} else if (userAgent.indexOf("Firefox") > 0) {
			browser = "Firefox";
		} else if (userAgent.indexOf("Nokia") > 0) {
			browser = "NokiaBrowser";
		} else if (userAgent.indexOf("Safari") > 0) {
		 if (userAgent.indexOf("Chrome") > 0) {
			  browser = "Chrome";
			 } else {
			  browser = "Safari";
			 }
		}else{
			browser ="etc";
		}
		return browser;
	}
	
	public static String getClientOS(HttpServletRequest request) {
		String os = "";		
		String user_os = request.getHeader("User-Agent");
		if (user_os.indexOf("iPhone") > 0 || user_os.indexOf("iPad") > 0) {
			os = "Mobile_IOS";
		} else if (user_os.indexOf("Android") > 0) {
			os = "Mobile_Android";
		} else if (user_os.indexOf("Windows Phone") > 0) {
			os = "Mobile_Window";
		} else if (user_os.indexOf("RIM") > 0) {
			os = "Mobile_RIM";
		} else if (user_os.indexOf("Mac") > 0) {
			os = "Mac";
		} else if (user_os.indexOf("Ubuntu") > 0) {
			os = "Ubuntu";
		} else if (user_os.indexOf("Nokia") > 0) {
			os = "MeeGo";
		} 
		return os;
	}
	
	public static String getclientUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
	
	
	public String getClientBrowserName(HttpServletRequest request) {
		String browser = "";		
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0) {
			browser = "ie";
		} else if (userAgent.indexOf("Edge") > 0) {
			browser = "edge";
		} else if (userAgent.indexOf("Presto") > 0) {
			browser = "opera";
		} else if (userAgent.indexOf("Firefox") > 0) {
			browser = "firefox";
		} else if (userAgent.indexOf("Nokia") > 0) {
			browser = "nokiabrowser";
		} else if (userAgent.indexOf("Safari") > 0) {
			if (userAgent.indexOf("Chrome") > 0) {
				browser = "chrome";
			} else {
				browser = "safari";
			}
		}else{
			browser ="etc";
		}
		return browser;
	}
	
	
	/**
	 * @param TIME : change time
	 * @param CHANGE_PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 */
	public long changeGetTime(String TIME, String PATTERN) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
			Date date = sdf.parse(TIME);
			return date.getTime();
		} catch (ParseException e) {
			return -1;
		}
	}
	
	/**
	 * @param TIME : change time
	 * @param PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 * */
	public Calendar changeGetCalendar(String TIME, String PATTERN) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
			Date date = formatter.parse(TIME);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		} catch(Exception e) {			
			return null;
		}
	}	
	
	/**
	 * @param TIME : change time
	 * @param PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 * */
	public Date changeGetDateTime(String TIME, String PATTERN) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
			Date date = formatter.parse(TIME);
			return date;
		} catch(Exception e) {			
			return null;
		}
	}
	/**
	 * @param PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 * */
	public String currentTime(String PATTERN) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);//"yyyyMMddHHmmss"
		return dateFormat.format(calendar.getTime());
	}	
	/**
	 * @param TIME : change time
	 * @param CHANGE_PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 * */
	public String changePatternString(String TIME, String CHANGE_PATTERN) {
		SimpleDateFormat format = new SimpleDateFormat(CHANGE_PATTERN);
		try {
			Date day = format.parse(TIME);
			return format.format(day);
		} catch (ParseException e) {			
			return "ERROR";
		}
	}
	/**
	 * @param calendar : Calendar
	 * @param PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 * */
	public String changeCalendarToString(Calendar calendar,String PATTERN) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
		return dateFormat.format(calendar.getTime());
	}
	/**
	 * @param TIME : change time
	 * @param CURRENT_PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 * @param CHANGE_PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 * */
	public String changePatternString(String TIME, String CURRENT_PATTERN, String CHANGE_PATTERN) {
		SimpleDateFormat format = new SimpleDateFormat(CURRENT_PATTERN);
		SimpleDateFormat format_new = new SimpleDateFormat(CHANGE_PATTERN);
		try {
			Date day = format.parse(TIME);
			return format_new.format(day);
		} catch (ParseException e) {
			return "ERROR";
		}
	}
	/**
	 * 지정날짜 부터 n달 이후 구하기
	 * 입력한 날짜로 부터 n달 이후 날짜시간 구하기
	 * */
	public String getCurrentPositionToAfterTargetMonth(String TIME, int afterTargetMonth) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day = null;
		try {
			day = format.parse(TIME);
		} catch (ParseException e) {
			return "ERROR";
		}
		Date aftermonth = new Date ( day.getTime() + ((long) ( 1000 * 60 * 60 * 24 ) * (afterTargetMonth * 30)) );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(aftermonth);
	}
	/**
	 * 지정날짜 부터 n달 이전 구하기
	 * 입력한 날짜로 부터 n달 이전 날짜시간 구하기
	 * */
	public String getCurrentPositionToBeforeTargetMonth(String TIME, int afterTargetMonth) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day = null;
		try {
			day = format.parse(TIME);
		} catch (ParseException e) {
			return "ERROR";
		}
		Date aftermonth = new Date ( day.getTime() - ((long) ( 1000 * 60 * 60 * 24 ) * (afterTargetMonth * 30)) );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(aftermonth);
	}
	/**
	 * 지정날짜 부터 일주일전 구하기
	 * 입력한 날짜로 부터 일주일전 날짜시간 구하기
	 * */
	public String getCurrentPositionToBeforeWeek(String TIME) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day = null;
		try {
			day = format.parse(TIME);
		} catch (ParseException e) {
			return "ERROR";
		}
		Date beforeweek = new Date ( day.getTime() + ((long) ( 1000 * 60 * 60 * 24 ) * -7) );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(beforeweek) + " 23:59:59";
	}
	/**
	 * 지정날짜 부터 일주일뒤 구하기
	 * 입력한 날짜로 부터 일주일뒤 날짜시간 구하기
	 * */
	public String getCurrentPositionToAfterWeek(String TIME) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day = null;
		try {
			day = format.parse(TIME);
		} catch (ParseException e) {
			return "ERROR";
		}
		Date afterweek = new Date ( day.getTime() + ((long) ( 1000 * 60 * 60 * 24 ) * 7) );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(afterweek) + " 23:59:59";
	}
	/**
	 * 지정날짜 부터 일수 전 구하기
	 * 입력한 날짜로 부터 일수 전 날짜시간 구하기
	 * */
	public String getCurrentPositionToBeforeDay(String TIME, int days) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day = null;
		try {
			day = format.parse(TIME);
		} catch (ParseException e) {
			return "ERROR";
		}
		Date beforeweek = new Date ( day.getTime() + ((long) ( 1000 * 60 * 60 * 24 ) * -days) );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(beforeweek) + " 23:59:59";
	}
	/**
	 * 지정날짜 부터 일수 후 구하기
	 * 입력한 날짜로 부터 일수 후 날짜시간 구하기
	 * */
	public String getCurrentPositionToAfterDay(String TIME, int days) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day = null;
		try {
			day = format.parse(TIME);
		} catch (ParseException e) {
			return "ERROR";
		}
		Date afterweek = new Date ( day.getTime() + ((long) ( 1000 * 60 * 60 * 24 ) * days) );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(afterweek) + " 23:59:59";
	}
	/**
	 * 두 입력날짜를 비교하기
	 * @param DATE01 : string time
	 * @param DATE02 : string time
	 * @param PATTERN01 : yyyy-MM-dd HH(hh):mm:ss
     * @param PATTERN02 : yyyy-MM-dd HH(hh):mm:ss
	 * @return RETURN_COMPARE_TYPE //ERROR,SAME,LARGE_THAN_DATE02,SMALL_THAN_DATE02,
	 * */
	public static enum RETURN_COMPARE_TYPE {
		ERROR,
		SAME,
		LARGE_THAN_DATE02,
		SMALL_THAN_DATE02,
	}
	public RETURN_COMPARE_TYPE dateCompare(String DATE01, String PATTERN01, String DATE02, String PATTERN02) {

		SimpleDateFormat format1 = new SimpleDateFormat(PATTERN01);
		SimpleDateFormat format2 = new SimpleDateFormat(PATTERN02);
		Date day1 = null;
		Date day2 = null;

		try {
			day1 = format1.parse(DATE01);
			day2 = format2.parse(DATE02);
		} catch (ParseException e) {
			LOGGER.debug("fail ParseException");
		}

		if(day1==null || day2==null)
			return RETURN_COMPARE_TYPE.ERROR;

		int compare = day1.compareTo(day2);
		if ( compare > 0 )
		{
			return RETURN_COMPARE_TYPE.LARGE_THAN_DATE02;
		}
		else if ( compare < 0 )
		{
			return RETURN_COMPARE_TYPE.SMALL_THAN_DATE02;
		}
		else
		{
			return RETURN_COMPARE_TYPE.SAME;
		}

	}
	/**
	  * 현재 시간 기준 요일을 구함(일 ~ 토)
	  * @param null
	  * @return String
	  * @throws Exception
	  */
	public String getCurrentTimeDayToString() {
		String day = "" ;
		Calendar cal = Calendar.getInstance();
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);

		switch (dayNum) {
			case 1:
				day = "일";
				break;
			case 2:
				day = "월";
				break;
			case 3:
				day = "화";
				break;
			case 4:
				day = "수";
				break;
			case 5:
				day = "목";
				break;
			case 6:
				day = "금";
				break;
			case 7:
				day = "토";
				break;

		}
		return day;
	}
	/**
	  * 특정 날짜에 대하여 요일을 구함(일 ~ 토)
	 	입력한 날짜의 요일 구하기
	  * @param date
	  * @param dateType
	  * @return
	  * @throws Exception
	  */
	public String getDateDayToString(String date, String dateType) {
		String day = "" ;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
			Date nDate = dateFormat.parse(date);

			Calendar cal = Calendar.getInstance();
			cal.setTime(nDate);

			int dayNum = cal.get(Calendar.DAY_OF_WEEK);

			switch (dayNum) {
				case 1:
					day = "일";
					break;
				case 2:
					day = "월";
					break;
				case 3:
					day = "화";
					break;
				case 4:
					day = "수";
					break;
				case 5:
					day = "목";
					break;
				case 6:
					day = "금";
					break;
				case 7:
					day = "토";
					break;

			}
		} catch (Exception e) {
			e.printStackTrace();
			day = "ERROR";
		}
		return day ;
	}
	
	/**
	 * 입력한 날짜를 시분초 라벨링
	 * @param TIME : string time
	 * @param CURRENT_PATTERN : yyyy-MM-dd HH(hh):mm:ss
	 * */
	public String getHMSLabel(String TIME, String CURRENT_PATTERN) {
		String label = "";
		SimpleDateFormat format = new SimpleDateFormat(CURRENT_PATTERN);
		try {
			Date d = format.parse(TIME);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			int HOUR = calendar.get(Calendar.HOUR_OF_DAY);
			int MINUTE = calendar.get(Calendar.MINUTE);
			int SECOND = calendar.get(Calendar.SECOND);
			if(HOUR > 0) {
				label += HOUR+"시간";
			}
			if(MINUTE > 0) {
				if(label.length()!=0) label += " ";
				label += MINUTE+"분";			
			}
			if(SECOND > 0) {
				if(label.length()!=0) label += " ";
				label += SECOND+"초";			
			}
			//String.format("%0"+length+"d", value);
		} catch (ParseException e) {
			//e.printStackTrace();
			label = "unkown";
		}
		return label;
	}
	
	
	/**
     * 만나이 계산
     * @param sDate:1919,03,01
     * @return
     */
    public int getAge(int birthYear, int birthMonth, int birthDay) {
    	LOGGER.debug("[만나이계산처리(타입1)] : "+birthYear+","+birthMonth+","+birthDay);
    	return processAge(birthYear, birthMonth, birthDay, true);
    }
	
    /**
     * 만나이 계산
     * @param sDate:19190301
     * @return
     */
    public int getAge(String birth) {
    	LOGGER.debug("[만나이계산처리(타입2)] : "+birth);
    	int birthYear = 0;
    	int birthMonth = 0;
    	int birthDay = 0;
    	try { 
    		birthYear = Integer.parseInt(birth.substring(0,4));
    	} catch(NumberFormatException e1) {
    		LOGGER.debug("[fail][NumberFormatException] birthYear");
    		birthYear = 0;
    	} catch(Exception e) { 
    		LOGGER.debug("[fail] birthYear "+e.toString());
    		birthYear = 0;
    	}
    	try { 
    		birthMonth = Integer.parseInt(birth.substring(4,6));
    	} catch(NumberFormatException e1) {
    		LOGGER.debug("[fail][NumberFormatException] birthMonth");
    		birthMonth = 0;
    	} catch(Exception e) {
    		LOGGER.debug("[fail] birthMonth "+e.toString());
    		birthMonth = 0;
    	}
    	try { 
    		birthDay = Integer.parseInt(birth.substring(6,8));
    	} catch(NumberFormatException e1) {
    		LOGGER.debug("[fail][NumberFormatException] birthDay");
    		birthDay = 0;
    	} catch(Exception e) { 
    		LOGGER.debug("[fail] birthDay "+e.toString());
    		birthDay = 0;
    	}
    	return processAge(birthYear, birthMonth, birthDay, true);
    }
    
    private int processAge(int birthYear, int birthMonth, int birthDay, boolean iskorea) {
		Calendar current = Calendar.getInstance();
		int currentYear  = current.get(Calendar.YEAR);
		int currentMonth = current.get(Calendar.MONTH) + 1;
		int currentDay   = current.get(Calendar.DAY_OF_MONTH);
		int age = currentYear - birthYear;
		LOGGER.debug("currentYear : "+currentYear+" , currentMonth : "+currentMonth+" , currentDay : "+currentDay);
		LOGGER.debug("birthYear : "+birthYear+" , birthMonth : "+birthMonth+" , birthDay : "+birthDay);
		LOGGER.debug("age : "+age);
		// 생일 안 지난 경우 -1
		if (iskorea && (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay))  
		    age--;   
		LOGGER.debug("[나이계산처리(결과)] : "+age);
		return age;
    }
    
    public int getAgeReal(String birth) {
    	LOGGER.debug("[나이계산처리(입력)] : "+birth);
    	int birthYear = 0;
    	int birthMonth = 0;
    	int birthDay = 0;
    	try { 
    		birthYear = Integer.parseInt(birth.substring(0,4));
    	} catch(NumberFormatException e1) {
    		LOGGER.debug("[fail][NumberFormatException] birthYear");
    		birthYear = 0;
    	} catch(Exception e) { 
    		LOGGER.debug("[fail] birthYear "+e.toString());
    		birthYear = 0;
    	}
    	try { 
    		birthMonth = Integer.parseInt(birth.substring(4,6));
    	} catch(NumberFormatException e1) {
    		LOGGER.debug("[fail][NumberFormatException] birthMonth");
    		birthMonth = 0;
    	} catch(Exception e) { 
    		LOGGER.debug("[fail] birthMonth "+e.toString());
    		birthMonth = 0;
    	}
    	try { 
    		birthDay = Integer.parseInt(birth.substring(6,8));
    	} catch(NumberFormatException e1) {
    		LOGGER.debug("[fail][NumberFormatException] birthDay");
    		birthDay = 0;
    	} catch(Exception e) { 
    		LOGGER.debug("[fail] birthDay "+e.toString());
    		birthDay = 0;
    	}
    	return processAge(birthYear, birthMonth, birthDay, false);
    }
    
    
    /**
	 * XSS 방지 처리
	 *
	 * @param data
	 * @return
	 */
	public String unscript(String data) {
		if (data == null || data.trim().equals("")) {
			return "";
		}

		String ret = data;

		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
		ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");

		ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
		ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");

		ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
		ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");

		ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");

		ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

		
		
		
		return ret;
	}
	
	/**
     * 라인별 파일 읽는 메소드
     * 
     * @param filePath
     * @throws IOException
     */
    public StringBuffer readFile(String filePath) {
    	BufferedReader br = null;
        StringBuffer sb = new StringBuffer(); // 테스트용 변수
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = null;
            
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
 
        } catch (IOException ioe) {
            LOGGER.debug("error IOException");
        } finally {
            try { 
                if (br!=null) 
                    br.close(); 
            } catch (Exception e) {
            	LOGGER.debug("[fail close] " + e.toString());
            }
        }         
        return sb;
    }
    /**
     * StringBuilder 의 replaceAll 처리
     * 
     * @param filePath
     */
    public static void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        while (index != -1)
        {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }
    
    /**
     * 0 채우기
     * String.format : %s, %d, %x, %o, %f
     */
    //해당 값의 왼쪽에 자리수만큼 0을 붙이기
	public String convertFillZeroToLeft(int length, int value) {
		return String.format("%0"+length+"d", value);
	}
	//해당 값의 오른쪽에 자리수만큼 0을 붙이기
	public String convertFillZeroToRight(int length, int value) {
		return String.format("%-0"+length+"d", value);
	}
    
	
	/**
	 * List VO to List Map
	 * */
	public Map<String, Object> convertToMap(Object element) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Field[] fieldList = element.getClass().getDeclaredFields();
		if (fieldList != null && fieldList.length > 0) {
			try {
				for (int i = 0; i < fieldList.length; i++) {
					String curInsName = fieldList[i].getName();
					Field field = element.getClass().getDeclaredField(curInsName);
					field.setAccessible(true);
					Object targetValue = field.get(element);
					resultMap.put(curInsName, targetValue);
				}				
			} catch (Exception e) {
				LOGGER.debug("[fail process] " + e.toString());
			}
		}		
		return resultMap;
	}
	
	/**
	 * List VO to List Map
	 * */
	public <T> List<Map<String, Object>> convertListToMap(Collection<T> target) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (T element : target) {
			Map<String,Object> resultMap = new HashMap<String,Object>();
			Field[] fieldList = element.getClass().getDeclaredFields();
			if (fieldList != null && fieldList.length > 0) {
				try {
					for (int i = 0; i < fieldList.length; i++) {
						String curInsName = fieldList[i].getName();
						Field field = element.getClass().getDeclaredField(curInsName);
						field.setAccessible(true);
						Object targetValue = field.get(element);
						resultMap.put(curInsName, targetValue);
					}
					resultList.add(resultMap);					
				} catch (Exception e) {
					LOGGER.debug("[fail process] " + e.toString());
				}
			}
		}
		return resultList;
	}
    
}
