package egovframework.naksinuri_original.com.cmm.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Properties;
/**
 *  Class Name : EgovProperties.java
 *  Description : properties값들을 파일로부터 읽어와   Globals클래스의 정적변수로 로드시켜주는 클래스로
 *   문자열 정보 기준으로 사용할 전역변수를 시스템 재시작으로 반영할 수 있도록 한다.
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.01.19    박지욱          최초 생성
 *	 2011.07.20    서준식 	      Globals파일의 상대경로를 읽은 메서드 추가
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  @author 공통 서비스 개발팀 박지욱
 *  @since 2009. 01. 19
 *  @version 1.0
 *  @see
 *
 */

public class EgovProperties{

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovProperties.class);

	//프로퍼티값 로드시 에러발생하면 반환되는 에러문자열
	public static final String ERR_CODE =" EXCEPTION OCCURRED";
	public static final String ERR_CODE_FNFE =" EXCEPTION(FNFE) OCCURRED";
	public static final String ERR_CODE_IOE =" EXCEPTION(IOE) OCCURRED";

	//파일구분자
    static final char FILE_SEPARATOR     = File.separatorChar;

    public static final String RELATIVE_PATH_PREFIX = EgovProperties.class.getResource("").getPath()
    + System.getProperty("file.separator") + ".." + System.getProperty("file.separator")
    + ".." + System.getProperty("file.separator") + ".." + System.getProperty("file.separator");

    public static final String GLOBALS_PROPERTIES_FILE
    = RELATIVE_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + "globals.properties";


	@SuppressWarnings("unused")
	public static ArrayList<Map<String, String>> loadPropertyFile(String property){

		ArrayList<Map<String, String>> keyList = new ArrayList<Map<String, String>>();

		String src = property.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		FileInputStream fis = null;
		try
		{

			File srcFile = new File(src);
			if (srcFile.exists()) {

				java.util.Properties props = new java.util.Properties();
				fis  = new FileInputStream(src);
				props.load(new java.io.BufferedInputStream(fis));
				fis.close();

				int i = 0;
				Enumeration<?> plist = props.propertyNames();
				if (plist != null) {
					while (plist.hasMoreElements()) {
						Map<String, String> map = new HashMap<String, String>();
						String key = (String)plist.nextElement();
						map.put(key, props.getProperty(key));
						keyList.add(map);
					}
				}
			}
		} catch (Exception ex){
			debug("EX:"+ex);
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (Exception ex) {
				debug("EX:"+ex);//ex.printStackTrace();
			}
		}

		return keyList;
	}

	/**
	 * 시스템 로그를 출력한다.
	 * @param obj Object
	 */
	private static void debug(Object obj) {
		if (obj instanceof java.lang.Exception) {
			LOGGER.debug("IGNORED: {}", ((Exception)obj).getMessage());
		}
	}
}

