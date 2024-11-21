package egovframework.utils;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the ";License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS"; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EgovStringUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovStringUtil.class);

    /**
     * 빈 문자열 <code>""</code>.
     */
    public static final String EMPTY = "";

    /**
     * <p>Padding을 할 수 있는 최대 수치</p>
     */
    // private static final int PAD_LIMIT = 8192;
    /**
     * <p>An array of <code>String</code>s used for padding.</p>
     * <p>Used for efficient space padding. The length of each String expands as needed.</p>
     */
    /*
	private static final String[] PADDING = new String[Character.MAX_VALUE];

	static {
		// space padding is most common, start with 64 chars
		PADDING[32] = "                                                                ";
	}
     */

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + output;
            } else
                returnVal = source;
        }
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * @param source 원본 문자열 배열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else
                result = source;
        }
        return result;
    }

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     *
     * <pre>
     *  StringUtil.isEmpty(null)      = true
     *  StringUtil.isEmpty("")        = true
     *  StringUtil.isEmpty(" ")       = false
     *  StringUtil.isEmpty("bob")     = false
     *  StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @param remove  입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    /**
     * <p>문자열 내부의 콤마 character(,)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str 입력받는 기준 문자열
     * @return " , "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }

    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }


    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    /*
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        while (srcStr.indexOf(subject) >= 0) {
            preStr = srcStr.substring(0, srcStr.indexOf(subject));
            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }
    */

    /**
     * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(preStr).append(object).append(nextStr);
            return rtnStr.toString();
        } else {
            return source;
        }
    }

    /**
     * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
     *
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceChar(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        char chA;

        for (int i = 0; i < subject.length(); i++) {
            chA = subject.charAt(i);

            if (srcStr.indexOf(chA) >= 0) {
                preStr = srcStr.substring(0, srcStr.indexOf(chA));
                nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
                srcStr = rtnStr.append(preStr).append(object).append(nextStr).toString();
            }
        }

        return srcStr;
    }

    /**
     * <p><code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.</p>
     *
     * <p>입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.</p>
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  검색 문자열
     * @param searchStr  검색 대상문자열
     * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이 없거나 null인 경우 -1
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }


    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>defaultStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * StringUtil.decode(null, null, "foo", "bar")= "foo"
     * StringUtil.decode("", null, "foo", "bar") = "bar"
     * StringUtil.decode(null, "", "foo", "bar") = "bar"
     * StringUtil.decode("하이", "하이", null, "bar") = null
     * StringUtil.decode("하이", "하이  ", "foo", null) = null
     * StringUtil.decode("하이", "하이", "foo", "bar") = "foo"
     * StringUtil.decode("하이", "하이  ", "foo", "bar") = "bar"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @param defaultStr sourceStr와 compareStr의 값이 다를 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 defaultStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr) {
        if (sourceStr == null && compareStr == null) {
            return returnStr;
        }

        if (sourceStr == null && compareStr != null) {
            return defaultStr;
        }

        if (sourceStr.trim().equals(compareStr)) {
            return returnStr;
        }

        return defaultStr;
    }

    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>sourceStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * StringUtil.decode(null, null, "foo") = "foo"
     * StringUtil.decode("", null, "foo") = ""
     * StringUtil.decode(null, "", "foo") = null
     * StringUtil.decode("하이", "하이", "foo") = "foo"
     * StringUtil.decode("하이", "하이 ", "foo") = "하이"
     * StringUtil.decode("하이", "바이", "foo") = "하이"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 sourceStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr) {
        return decode(sourceStr, compareStr, returnStr, sourceStr);
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * @param object 원본 객체
     * @return resultVal 문자열
     */
    public static String isNullToString(Object object) {
        String string = "";

        if (object != null) {
            string = object.toString().trim();
        }

        return string;
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(Object src) {
	//if (src != null && src.getClass().getName().equals("java.math.BigDecimal")) {
	if (src != null && src instanceof java.math.BigDecimal) {
	    return ((BigDecimal)src).toString();
	}

	if (src == null || src.equals("null")) {
	    return "";
	} else {
	    return ((String)src).trim();
	}
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(String src) {

	if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
	    return "";
	} else {
	    return src.trim();
	}
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;0&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;0&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(Object src) {

	if (src == null || src.equals("null")) {
	    return 0;
	} else {
	    return Integer.parseInt(((String)src).trim());
	}
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(String src) {

	if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
	    return 0;
	} else {
	    return Integer.parseInt(src.trim());
	}
    }

    /**
     * <p>문자열에서 {@link Character#isWhitespace(char)}에 정의된
     * 모든 공백문자를 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeWhitespace(null)         = null
     * StringUtil.removeWhitespace("")           = ""
     * StringUtil.removeWhitespace("abc")        = "abc"
     * StringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  공백문자가 제거도어야 할 문자열
     * @return the 공백문자가 제거된 문자열, null이 입력되면 <code>null</code>이 리턴
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }

        return new String(chs, 0, count);
    }

    /**
     * Html 코드가 들어간 문서를 표시할때 태그에 손상없이 보이기 위한 메서드
     *
     * @param strString
     * @return HTML 태그를 치환한 문자열
     */
	public static String checkHtmlView(String strString) {
		String strNew = "";

		StringBuffer strTxt = new StringBuffer("");

		char chrBuff;
		int len = strString.length();

		for (int i = 0; i < len; i++) {
			chrBuff = (char) strString.charAt(i);

			switch (chrBuff) {
				case '<':
					strTxt.append("&lt;");
					break;
				case '>':
					strTxt.append("&gt;");
					break;
				case '"':
					strTxt.append("&quot;");
					break;
				case 10:
					strTxt.append("<br>");
					break;
				case ' ':
					strTxt.append("&nbsp;");
					break;
				//case '&' :
				//strTxt.append("&amp;");
				//break;
				default:
					strTxt.append(chrBuff);
			}
		}

		strNew = strTxt.toString();
		return strNew;
	}


    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
	/*
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);

        return returnVal;
    }
    */

    /**
     * <p>{@link String#toLowerCase()}를 이용하여 소문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 소문자로 변환되어야 할 문자열
     * @return 소문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }

    /**
     * <p>{@link String#toUpperCase()}를 이용하여 대문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 대문자로 변환되어야 할 문자열
     * @return 대문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toUpperCase();
    }

    /**
     * <p>입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripStart(null, *)          = null
     * StringUtil.stripStart("", *)            = ""
     * StringUtil.stripStart("abc", "")        = "abc"
     * StringUtil.stripStart("abc", null)      = "abc"
     * StringUtil.stripStart("  abc", null)    = "abc"
     * StringUtil.stripStart("abc  ", null)    = "abc  "
     * StringUtil.stripStart(" abc ", null)    = "abc "
     * StringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }

        return str.substring(start);
    }


    /**
     * <p>입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripEnd(null, *)          = null
     * StringUtil.stripEnd("", *)            = ""
     * StringUtil.stripEnd("abc", "")        = "abc"
     * StringUtil.stripEnd("abc", null)      = "abc"
     * StringUtil.stripEnd("  abc", null)    = "  abc"
     * StringUtil.stripEnd("abc  ", null)    = "abc"
     * StringUtil.stripEnd(" abc ", null)    = " abc"
     * StringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }

        return str.substring(0, end);
    }

    /**
     * <p>입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.strip(null, *)          = null
     * StringUtil.strip("", *)            = ""
     * StringUtil.strip("abc", null)      = "abc"
     * StringUtil.strip("  abc", null)    = "abc"
     * StringUtil.strip("abc  ", null)    = "abc"
     * StringUtil.strip(" abc ", null)    = "abc"
     * StringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String strip(String str, String stripChars) {
	if (isEmpty(str)) {
	    return str;
	}

	String srcStr = str;
	srcStr = stripStart(srcStr, stripChars);

	return stripEnd(srcStr, stripChars);
    }

    /**
     * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @param arraylength 배열 길이
     * @return 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
        String[] returnVal = new String[arraylength];
        int cnt = 0;
        int index0 = 0;
        int index = source.indexOf(separator);
        while (index >= 0 && cnt < (arraylength - 1)) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        if (cnt < (arraylength - 1)) {
            for (int i = cnt + 1; i < arraylength; i++) {
                returnVal[i] = "";
            }
        }

        return returnVal;
    }

    /**
     * 문자열 A에서 Z사이의 랜덤 문자열을 구하는 기능을 제공 시작문자열과 종료문자열 사이의 랜덤 문자열을 구하는 기능
     *
     * @param startChr
     *            - 첫 문자
     * @param endChr
     *            - 마지막문자
     * @return 랜덤문자
     * @exception MyException
     * @see
     */
	public static String getRandomStr(char startChr, char endChr) {

		int randomInt;
		String randomStr = null;

		// 시작문자 및 종료문자를 아스키숫자로 변환한다.
		int startInt = Integer.valueOf(startChr);
		int endInt = Integer.valueOf(endChr);

		// 시작문자열이 종료문자열보가 클경우
		if (startInt > endInt) {
			throw new IllegalArgumentException("Start String: " + startChr + " End String: " + endChr);
		}

		// 랜덤 객체 생성
		SecureRandom rnd = new SecureRandom();

		do {
			// 시작문자 및 종료문자 중에서 랜덤 숫자를 발생시킨다.
			randomInt = rnd.nextInt(endInt + 1);
		} while (randomInt < startInt); // 입력받은 문자 'A'(65)보다 작으면 다시 랜덤 숫자 발생.

		// 랜덤 숫자를 문자로 변환 후 스트링으로 다시 변환
		randomStr = (char) randomInt + "";

		// 랜덤문자열를 리턴
		return randomStr;
	}

    /**
     * 문자열을 다양한 문자셋(EUC-KR[KSC5601],UTF-8..)을 사용하여 인코딩하는 기능 역으로 디코딩하여 원래의 문자열을
     * 복원하는 기능을 제공함 String temp = new String(문자열.getBytes("바꾸기전 인코딩"),"바꿀 인코딩");
     * String temp = new String(문자열.getBytes("8859_1"),"KSC5601"); => UTF-8 에서
     * EUC-KR
     *
     * @param srcString
     *            - 문자열
     * @param srcCharsetNm
     *            - 원래 CharsetNm
     * @param charsetNm
     *            - CharsetNm
     * @return 인(디)코딩 문자열
     * @exception MyException
     * @see
     */
    public static String getEncdDcd(String srcString, String srcCharsetNm, String cnvrCharsetNm) {

	String rtnStr = null;

	if (srcString == null)
	    return null;

	try {
	    rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
	} catch (UnsupportedEncodingException e) {
	    rtnStr = null;
	}

	return rtnStr;
    }

/**
     * 특수문자를 웹 브라우저에서 정상적으로 보이기 위해 특수문자를 처리('<' -> & lT)하는 기능이다
     * @param 	srcString 		- '<'
     * @return 	변환문자열('<' -> "&lt"
     * @exception MyException
     * @see
     */
    public static String getSpclStrCnvr(String srcString) {

	String rtnStr = null;

	try {
	    StringBuffer strTxt = new StringBuffer("");

	    char chrBuff;
	    int len = srcString.length();

	    for (int i = 0; i < len; i++) {
		chrBuff = (char)srcString.charAt(i);

		switch (chrBuff) {
		case '<':
		    strTxt.append("&lt;");
		    break;
		case '>':
		    strTxt.append("&gt;");
		    break;
		case '&':
		    strTxt.append("&amp;");
		    break;
		default:
		    strTxt.append(chrBuff);
		}
	    }

	    rtnStr = strTxt.toString();

	} catch (Exception e) {
		LOGGER.debug("{}", e);
	}

	return rtnStr;
    }

    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String getTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
    }

    /**
     * html의 특수문자를 표현하기 위해
     *
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public String getHtmlStrCnvr(String srcString) {

    	String tmpString = srcString;

//    	tmpString = tmpString.replaceAll("<", "&lt;");
//        tmpString = tmpString.replaceAll(">", "&gt;");
        
        
        /*tmpString = tmpString.replaceAll("(?i)&", "&amp;");*/
//        tmpString = tmpString.replaceAll("(?i) ", "&nbsp;");
        
//        tmpString = tmpString.replaceAll("(?i)\'", "&apos;");
//        tmpString = tmpString.replaceAll("(?i)\"", "&quot;");
        /*tmpString = tmpString.replaceAll("(?i)\'", "&apos;");
        tmpString = tmpString.replaceAll("(?i)\"", "&quot;");*/
        
//        tmpString = tmpString.replaceAll("(?i)<p>", "&lt;p&gt;");
//        tmpString = tmpString.replaceAll("(?i)</p>", "&lt;/p&gt;");
//        tmpString = tmpString.replaceAll("(?i)<P>", "&lt;P&gt;");
//        tmpString = tmpString.replaceAll("(?i)</P>", "&lt;/P&gt;");
//        tmpString = tmpString.replaceAll("(?i)<br>", "&lt;br&gt;");
//        tmpString = tmpString.replaceAll("(?i)<BR>", "&lt;BR&gt;");
        
//        tmpString = tmpString.replaceAll("(?i)<", "&lt;");
//        tmpString = tmpString.replaceAll("(?i)>", "&gt;");
        /*tmpString = tmpString.replaceAll("(?i)<", "&lt;");
        tmpString = tmpString.replaceAll("(?i)>", "&gt;");*/
		
//		tmpString = tmpString.toLowerCase();
        
        tmpString = tmpString.replaceAll("(?i)location", "x-lo-ca-tion");
        tmpString = tmpString.replaceAll("(?i)reload", "x-re-lo-ad");
        tmpString = tmpString.replaceAll("(?i)window", "x-w-i-ndow");
        tmpString = tmpString.replaceAll("(?i)open", "x-o-pen");
		tmpString = tmpString.replaceAll("(?i)javascript", "x-j-avascript");
		tmpString = tmpString.replaceAll("(?i)script", "x-s-cript");
		tmpString = tmpString.replaceAll("(?i)vbscript", "x-v-bs-cript");
		tmpString = tmpString.replaceAll("(?i)binding", "x-bi-ndi-ng");
		tmpString = tmpString.replaceAll("(?i)expression", "x-ex-pression");
		tmpString = tmpString.replaceAll("(?i)applet", "x-a-pplet");
		tmpString = tmpString.replaceAll("(?i)meta", "x-m-eta");
		tmpString = tmpString.replaceAll("(?i)xml", "x-x-ml");
		tmpString = tmpString.replaceAll("(?i)link", "x-l-ink");
		//tmpString = tmpString.replaceAll("(?i)style", "x-s-tyle");
		tmpString = tmpString.replaceAll("(?i)embed", "x-e-mbed");
		tmpString = tmpString.replaceAll("(?i)object", "x-o-bject");
		tmpString = tmpString.replaceAll("(?i)frame", "x-f-rame");
		tmpString = tmpString.replaceAll("(?i)iframe", "x-i-f-rame");
		tmpString = tmpString.replaceAll("(?i)background", "x-b-ack-gr-ound");
		tmpString = tmpString.replaceAll("(?i)layer", "x-l-ayer");
		tmpString = tmpString.replaceAll("(?i)base", "x-b-ase");
		tmpString = tmpString.replaceAll("(?i)eval", "x-ev-al");
		tmpString = tmpString.replaceAll("(?i)innerHTML", "x-i-nn-er-HT-ML");
		tmpString = tmpString.replaceAll("(?i)charset", "x-c-harset");
		tmpString = tmpString.replaceAll("(?i)refresh", "x-r-ef-resh");
		tmpString = tmpString.replaceAll("(?i)string", "x-s-tring");
		tmpString = tmpString.replaceAll("(?i)void", "x-v-oid");
		tmpString = tmpString.replaceAll("(?i)create", "x-c-reate");
		tmpString = tmpString.replaceAll("(?i)append", "x-a-ppend");
		tmpString = tmpString.replaceAll("(?i)alert", "x-a-lert");
		tmpString = tmpString.replaceAll("(?i)msgbox", "x-m-sgbox");
		tmpString = tmpString.replaceAll("(?i)document", "x-d-ocument");
		tmpString = tmpString.replaceAll("(?i)cookies", "x-c-ookies");
		tmpString = tmpString.replaceAll("(?i)href", "x-h-r-ef");
		tmpString = tmpString.replaceAll("(?i)nabort", "x-n-abort");
		tmpString = tmpString.replaceAll("(?i)@import", "x-@-i-mport");
		tmpString = tmpString.replaceAll("(?i)http-equiv", "x-ht-tp-eq-ui-v");
		tmpString = tmpString.replaceAll("(?i)fromcharcode", "x-f-rom-c-har-c-ode");
		tmpString = tmpString.replaceAll("(?i)firefoxurl", "x-f-irefoxu-rl");
		tmpString = tmpString.replaceAll("(?i)wvs-xss", "x-w-vs-x-ss");
		tmpString = tmpString.replaceAll("(?i)acunetix_wvs", "x-a-cunet-ix_w-vs");
		tmpString = tmpString.replaceAll("(?i)lowsrc", "x-l-ows-rc");
		tmpString = tmpString.replaceAll("(?i)dynsrc", "x-d-yns-rc");
		tmpString = tmpString.replaceAll("(?i)behavior", "x-b-ehavior");
		tmpString = tmpString.replaceAll("(?i)onactive", "x-o-na-ctive");
		tmpString = tmpString.replaceAll("(?i)onafterprint", "x-o-na-fterp-rint");
		tmpString = tmpString.replaceAll("(?i)onafterupdate", "x-o-na-fteru-pdate");
		tmpString = tmpString.replaceAll("(?i)onbeforeactive", "x-o-nb-eforea-ctive");
		tmpString = tmpString.replaceAll("(?i)onbeforecopy", "x-o-nb-eforec-opy");
		tmpString = tmpString.replaceAll("(?i)onbeforecut", "x-o-nb-eforec-ut");
		tmpString = tmpString.replaceAll("(?i)onbeforedeactivate", "x-o-nb-efored-ea-ctivate");
		tmpString = tmpString.replaceAll("(?i)onbeforeeditfocus", "x-o-nb-eforee-ditf-ocus");
		tmpString = tmpString.replaceAll("(?i)onbeforepaste", "x-o-nb-eforep-aste");
		tmpString = tmpString.replaceAll("(?i)onbeforeprint", "x-o-nb-eforep-rint");
		tmpString = tmpString.replaceAll("(?i)onbeforeunload", "x-o-nb-eforeu-nload");
		tmpString = tmpString.replaceAll("(?i)onbeforeupdate", "x-o-nb-eforeu-pdate");
		tmpString = tmpString.replaceAll("(?i)onbefore", "x-o-nb-efore");
		tmpString = tmpString.replaceAll("(?i)onblur", "x-o-nblur");
		tmpString = tmpString.replaceAll("(?i)onbounce", "x-o-nb-ounce");
		tmpString = tmpString.replaceAll("(?i)oncellchange", "x-o-nc-ellc-hange");
		tmpString = tmpString.replaceAll("(?i)onchange", "x-o-nc-hange");
		tmpString = tmpString.replaceAll("(?i)onclick", "x-o-nc-lick");
		tmpString = tmpString.replaceAll("(?i)oncontextmenu", "x-o-nc-ontextm-enu");
		tmpString = tmpString.replaceAll("(?i)oncontrolselect", "x-o-nc-ontrols-elect");
		tmpString = tmpString.replaceAll("(?i)oncopy", "x-o-nc-opy");
		tmpString = tmpString.replaceAll("(?i)oncut", "x-o-nc-ut");
		tmpString = tmpString.replaceAll("(?i)ondataavailable", "x-o-nd-ataa-vailable");
		tmpString = tmpString.replaceAll("(?i)ondatasetchanged", "x-o-nd-atasetc-hanged");
		tmpString = tmpString.replaceAll("(?i)ondatasetcomplete", "x-o-nd-atasetc-omplete");
		tmpString = tmpString.replaceAll("(?i)ondblclick", "x-o-ndblc-lick");
		tmpString = tmpString.replaceAll("(?i)ondeactivate", "x-o-nd-ea-ctivate");
		tmpString = tmpString.replaceAll("(?i)ondrag", "x-o-nd-rag");
		tmpString = tmpString.replaceAll("(?i)ondrop", "x-o-nd-rop");
		tmpString = tmpString.replaceAll("(?i)onerror", "x-o-ne-rror");
		tmpString = tmpString.replaceAll("(?i)onfilterchange", "x-o-nf-ilterc-hange");
		tmpString = tmpString.replaceAll("(?i)onfinish", "x-o-nf-inish");
		tmpString = tmpString.replaceAll("(?i)onfocus", "x-o-nf-ocus");
		tmpString = tmpString.replaceAll("(?i)onhelp", "x-o-nh-elp");
		tmpString = tmpString.replaceAll("(?i)onkeydown", "x-o-nk-eyd-own");
		tmpString = tmpString.replaceAll("(?i)onkeypress", "x-o-nk-eyp-ress");
		tmpString = tmpString.replaceAll("(?i)onkeyup", "x-o-nk-eyu-p");
		tmpString = tmpString.replaceAll("(?i)onlayoutcomplete", "x-o-nl-ayoutc-omplete");
		tmpString = tmpString.replaceAll("(?i)onload", "x-o-nl-oad");
		tmpString = tmpString.replaceAll("(?i)onlosecapture", "x-o-nl-osec-apture");
		tmpString = tmpString.replaceAll("(?i)onmouse", "x-o-nm-ouse");
		tmpString = tmpString.replaceAll("(?i)onpaste", "x-o-np-aste");
		tmpString = tmpString.replaceAll("(?i)onpropertychange", "x-o-np-ropertyc-hange");
		tmpString = tmpString.replaceAll("(?i)onreadystatechange", "x-o-nr-eadys-tatec-hange");
		
		tmpString = tmpString.replaceAll("(?i)onreset","x-o-nr-eset");
		tmpString = tmpString.replaceAll("(?i)onresize","x-o-nr-esize");
		tmpString = tmpString.replaceAll("(?i)onresizeend","x-onresizeend");
		tmpString = tmpString.replaceAll("(?i)onresizestart","x-o-nr-esizes-tart");
		tmpString = tmpString.replaceAll("(?i)onrowenter","x-o-nr-owe-nter");
		tmpString = tmpString.replaceAll("(?i)onrowexit","x-o-nr-owe-xit");
		tmpString = tmpString.replaceAll("(?i)onrowsdelete","x-o-nr-owsd-elete");
		tmpString = tmpString.replaceAll("(?i)onrowsinserted","x-o-nr-owsi-nserted");
		tmpString = tmpString.replaceAll("(?i)onscroll","x-o-ns-croll");
		tmpString = tmpString.replaceAll("(?i)onselect","x-o-ns-elect");
		tmpString = tmpString.replaceAll("(?i)onselectionchange","x-o-ns-electionc-hange");
		tmpString = tmpString.replaceAll("(?i)onselectstart","x-o-ns-elects-tart");
		tmpString = tmpString.replaceAll("(?i)onstart","x-o-ns-tart");
		tmpString = tmpString.replaceAll("(?i)onstop","x-o-ns-top");
		tmpString = tmpString.replaceAll("(?i)onsubmit","x-o-ns-ubmit");
		tmpString = tmpString.replaceAll("(?i)onunload","x-o-nu-nload");
		tmpString = tmpString.replaceAll("(?i)onMessage","x-o-nM-essage");
		tmpString = tmpString.replaceAll("(?i)onRowDelete","x-o-nR-owD-elete");
		tmpString = tmpString.replaceAll("(?i)onOffline","x-o-nO-ffl-ine");
		tmpString = tmpString.replaceAll("(?i)onRowInserted","x-o-nR-owI-nserted");
		tmpString = tmpString.replaceAll("(?i)FSCommand","x-F-SC-ommand");
		tmpString = tmpString.replaceAll("(?i)onOnline","x-o-nO-nline");
		tmpString = tmpString.replaceAll("(?i)onSeek","x-o-nS-eek");
		tmpString = tmpString.replaceAll("(?i)onAbort","x-o-nA-bort");
		tmpString = tmpString.replaceAll("(?i)onOutOfSync","x-o-nO-utO-fS-ync");
		tmpString = tmpString.replaceAll("(?i)onStorage","x-o-nS-torage");
		tmpString = tmpString.replaceAll("(?i)onActivate","x-o-nA-ctivate");
		tmpString = tmpString.replaceAll("(?i)onPause","x-o-nP-ause");
		tmpString = tmpString.replaceAll("(?i)onSyncRestored","x-o-nS-yncR-estored");
		tmpString = tmpString.replaceAll("(?i)onBegin","x-o-nB-egin");
		tmpString = tmpString.replaceAll("(?i)onPopState","x-o-nP-opS-tate");
		tmpString = tmpString.replaceAll("(?i)onTimeError","x-o-nT-imeE-rror");
		tmpString = tmpString.replaceAll("(?i)onDragDrop","x-o-nD-ragD-rop");
		tmpString = tmpString.replaceAll("(?i)onProgress","x-o-nP-rogress");
		tmpString = tmpString.replaceAll("(?i)onTrackChange","x-o-nT-rackC-hange");
		tmpString = tmpString.replaceAll("(?i)onEnd","x-o-nE-nd");
		tmpString = tmpString.replaceAll("(?i)onRedo","x-o-nR-edo");
		tmpString = tmpString.replaceAll("(?i)onUndo","x-o-nU-ndo");
		tmpString = tmpString.replaceAll("(?i)onHashChange","x-o-nH-ashC-hange");
		tmpString = tmpString.replaceAll("(?i)onRepeat","x-o-nR-epeat");
		tmpString = tmpString.replaceAll("(?i)onURLFlip","x-o-nU-RLF-lip");
		tmpString = tmpString.replaceAll("(?i)onInput","x-o-nI-nput");
		tmpString = tmpString.replaceAll("(?i)onResume","x-o-nR-esume");
		tmpString = tmpString.replaceAll("(?i)seekSegmentTime","x-s-eekS-egmentT-ime");
		tmpString = tmpString.replaceAll("(?i)onMediaComplete","x-o-nM-ediaC-omplete");
		tmpString = tmpString.replaceAll("(?i)onReverse","x-o-nR-everse");
		tmpString = tmpString.replaceAll("(?i)onRowsEnter","x-o-nR-owsE-nter");
		tmpString = tmpString.replaceAll("(?i)onMediaError","x-o-nM-ediaE-rror");
		tmpString = tmpString.replaceAll("(?i)java.lang.Runtime","x-j-ava.l-ang.R-untime");
		tmpString = tmpString.replaceAll("(?i)getRuntime","x-g-etR-untime");
		tmpString = tmpString.replaceAll("(?i)onwheel","x-o-nw-heel");
		tmpString = tmpString.replaceAll("(?i)onsearch","x-o-ns-earch");
		tmpString = tmpString.replaceAll("(?i)onpagehide","x-o-np-ageh-ide");
		tmpString = tmpString.replaceAll("(?i)onpageshow","x-o-np-ages-how");
		tmpString = tmpString.replaceAll("(?i)oninvalid","x-o-ni-nv-alid");
		tmpString = tmpString.replaceAll("(?i)oncanplay","x-o-nc-anp-lay");
		tmpString = tmpString.replaceAll("(?i)oncanplaythrough","x-o-nc-anp-layt-hrough");
		tmpString = tmpString.replaceAll("(?i)oncuechange","x-o-nc-uec-hange");
		tmpString = tmpString.replaceAll("(?i)ondurationchange","x-o-nd-urationc-hange");
		tmpString = tmpString.replaceAll("(?i)onloadedmetadata","x-o-nl-oadedm-etad-ata");
		tmpString = tmpString.replaceAll("(?i)onloadstart","x-o-nl-oads-tart");
		tmpString = tmpString.replaceAll("(?i)onseeked","x-o-ns-eeked");
		tmpString = tmpString.replaceAll("(?i)onplay","x-o-np-lay");
		tmpString = tmpString.replaceAll("(?i)onplaying","x-o-np-laying");
		tmpString = tmpString.replaceAll("(?i)onratechange","x-o-nr-atec-hange");
		tmpString = tmpString.replaceAll("(?i)onseeking","x-o-ns-eeking");
		tmpString = tmpString.replaceAll("(?i)onstalled","x-o-ns-talled");
		tmpString = tmpString.replaceAll("(?i)onsuspend","x-o-ns-uspend");
		tmpString = tmpString.replaceAll("(?i)ontimeupdate","x-o-nt-imeu-pdate");
		tmpString = tmpString.replaceAll("(?i)onvolumechange","x-o-nv-olumec-hange");
		tmpString = tmpString.replaceAll("(?i)onwating","x-o-nw-ating");
		
		tmpString = tmpString.replaceAll("(?i)onemptied","x-o-ne-mptied");
		tmpString = tmpString.replaceAll("(?i)ontoggle","x-o-nt-oggle");
		tmpString = tmpString.replaceAll("(?i)video","x-v-ideo");
		tmpString = tmpString.replaceAll("(?i)audio","x-a-udio");
		tmpString = tmpString.replaceAll("(?i)details","x-d-etails");
		tmpString = tmpString.replaceAll("(?i)onended","x-o-ne-nded");
		tmpString = tmpString.replaceAll("(?i)onloadeddata","x-o-nl-oadedd-ata");
		tmpString = tmpString.replaceAll("(?i)onwaiting","x-o-nw-aiting");

		tmpString = tmpString.replaceAll("(?i)grameset", "x-g-rames-et");
		tmpString = tmpString.replaceAll("(?i)bgsound", "x-b-gs-ound");
	  	tmpString = tmpString.replaceAll("(?i)enerror", "x-e-ne-rror");
	  	tmpString = tmpString.replaceAll("(?i)prompt", "x-pr-ompt");
	  	tmpString = tmpString.replaceAll("(?i)content", "x-con-te-nt");
	  	tmpString = tmpString.replaceAll("(?i)url", "x-u-r-l");
	  	tmpString = tmpString.replaceAll("(?i)body", "x-bo-dy");
	  	tmpString = tmpString.replaceAll("(?i)confirm", "x-con-fi-rm");
	  	tmpString = tmpString.replaceAll("(?i)textarea", "x-te-xt-ar-ea");

		return  tmpString;

	}
    public String getHtmlStrCnvrEditer(String srcString) {
    	
    	String tmpString = srcString;
    	  	
    	tmpString = tmpString.replaceAll("(?i)&amp;", "&");
//    	tmpString = tmpString.replaceAll("(?i)&nbsp;", " ");
    	
        tmpString = tmpString.replaceAll("(?i)&apos;", "\'");
        tmpString = tmpString.replaceAll("(?i)&quot;", "\"");
    	
        tmpString = tmpString.replaceAll("(?i)&lt;", "<");
        tmpString = tmpString.replaceAll("(?i)&gt;", ">");
    	
//    	tmpString = tmpString.replaceAll("(?i)&", "&amp;");
//    	tmpString = tmpString.replaceAll("(?i) ", "&nbsp;");
//    	tmpString = tmpString.replaceAll("(?i)\'", "");
//        tmpString = tmpString.replaceAll("(?i)\"", "");
//    	tmpString = tmpString.replaceAll("(?i)<", "&lt;");
//    	tmpString = tmpString.replaceAll("(?i)>", "&gt;");
    	//tmpString = tmpString.toLowerCase();

        tmpString = tmpString.replaceAll("(?i)window", "x-w-i-ndow");
        tmpString = tmpString.replaceAll("(?i)open", "x-o-pen");
    	tmpString = tmpString.replaceAll("(?i)javascript", "x-j-avascript");
    	tmpString = tmpString.replaceAll("(?i)script", "x-s-cript");
    	tmpString = tmpString.replaceAll("(?i)vbscript", "x-v-bs-cript");
    	tmpString = tmpString.replaceAll("(?i)binding", "x-bi-ndi-ng");
    	tmpString = tmpString.replaceAll("(?i)expression", "x-ex-pression");
    	tmpString = tmpString.replaceAll("(?i)applet", "x-a-pplet");
    	tmpString = tmpString.replaceAll("(?i)meta", "x-m-eta");
    	tmpString = tmpString.replaceAll("(?i)xml", "x-x-ml");
    	tmpString = tmpString.replaceAll("(?i)link", "x-l-ink");
//    	tmpString = tmpString.replaceAll("(?i)style", "x-s-tyle");
    	tmpString = tmpString.replaceAll("(?i)embed", "x-e-mbed");
    	tmpString = tmpString.replaceAll("(?i)object", "x-o-bject");
    	tmpString = tmpString.replaceAll("(?i)frame", "x-f-rame");
    	tmpString = tmpString.replaceAll("(?i)iframe", "x-i-f-rame");
//    	tmpString = tmpString.replaceAll("(?i)background", "x-b-ack-gr-ound");
    	tmpString = tmpString.replaceAll("(?i)layer", "x-l-ayer");
    	tmpString = tmpString.replaceAll("(?i)base", "x-b-ase");
    	tmpString = tmpString.replaceAll("(?i)eval", "x-ev-al");
    	tmpString = tmpString.replaceAll("(?i)innerHTML", "x-i-nn-er-HT-ML");
    	tmpString = tmpString.replaceAll("(?i)charset", "x-c-harset");
    	tmpString = tmpString.replaceAll("(?i)refresh", "x-r-ef-resh");
    	tmpString = tmpString.replaceAll("(?i)string", "x-s-tring");
    	tmpString = tmpString.replaceAll("(?i)void", "x-v-oid");
    	tmpString = tmpString.replaceAll("(?i)create", "x-c-reate");
    	tmpString = tmpString.replaceAll("(?i)append", "x-a-ppend");
    	tmpString = tmpString.replaceAll("(?i)alert", "x-a-lert");
    	tmpString = tmpString.replaceAll("(?i)msgbox", "x-m-sgbox");
    	tmpString = tmpString.replaceAll("(?i)document", "x-d-ocument");
    	tmpString = tmpString.replaceAll("(?i)cookies", "x-c-ookies");
    	tmpString = tmpString.replaceAll("(?i)href", "x-h-r-ef");
    	tmpString = tmpString.replaceAll("(?i)nabort", "x-n-abort");
    	tmpString = tmpString.replaceAll("(?i)@import", "x-@-i-mport");
    	tmpString = tmpString.replaceAll("(?i)http-equiv", "x-ht-tp-eq-ui-v");
    	tmpString = tmpString.replaceAll("(?i)fromcharcode", "x-f-rom-c-har-c-ode");
    	tmpString = tmpString.replaceAll("(?i)firefoxurl", "x-f-irefoxu-rl");
    	tmpString = tmpString.replaceAll("(?i)wvs-xss", "x-w-vs-x-ss");
    	tmpString = tmpString.replaceAll("(?i)acunetix_wvs", "x-a-cunet-ix_w-vs");
    	tmpString = tmpString.replaceAll("(?i)lowsrc", "x-l-ows-rc");
    	tmpString = tmpString.replaceAll("(?i)dynsrc", "x-d-yns-rc");
    	tmpString = tmpString.replaceAll("(?i)behavior", "x-b-ehavior");
    	tmpString = tmpString.replaceAll("(?i)onactive", "x-o-na-ctive");
    	tmpString = tmpString.replaceAll("(?i)onafterprint", "x-o-na-fterp-rint");
    	tmpString = tmpString.replaceAll("(?i)onafterupdate", "x-o-na-fteru-pdate");
		tmpString = tmpString.replaceAll("(?i)onbeforeactive", "x-o-nb-eforea-ctive");
		tmpString = tmpString.replaceAll("(?i)onbeforecopy", "x-o-nb-eforec-opy");
		tmpString = tmpString.replaceAll("(?i)onbeforecut", "x-o-nb-eforec-ut");
		tmpString = tmpString.replaceAll("(?i)onbeforedeactivate", "x-o-nb-efored-ea-ctivate");
		tmpString = tmpString.replaceAll("(?i)onbeforeeditfocus", "x-o-nb-eforee-ditf-ocus");
		tmpString = tmpString.replaceAll("(?i)onbeforepaste", "x-o-nb-eforep-aste");
		tmpString = tmpString.replaceAll("(?i)onbeforeprint", "x-o-nb-eforep-rint");
		tmpString = tmpString.replaceAll("(?i)onbeforeunload", "x-o-nb-eforeu-nload");
		tmpString = tmpString.replaceAll("(?i)onbeforeupdate", "x-o-nb-eforeu-pdate");
		tmpString = tmpString.replaceAll("(?i)onbefore", "x-o-nb-efore");
    	tmpString = tmpString.replaceAll("(?i)onblur", "x-o-nblur");
    	tmpString = tmpString.replaceAll("(?i)onbounce", "x-o-nb-ounce");
    	tmpString = tmpString.replaceAll("(?i)oncellchange", "x-o-nc-ellc-hange");
    	tmpString = tmpString.replaceAll("(?i)onchange", "x-o-nc-hange");
    	tmpString = tmpString.replaceAll("(?i)onclick", "x-o-nc-lick");
    	tmpString = tmpString.replaceAll("(?i)oncontextmenu", "x-o-nc-ontextm-enu");
    	tmpString = tmpString.replaceAll("(?i)oncontrolselect", "x-o-nc-ontrols-elect");
    	tmpString = tmpString.replaceAll("(?i)oncopy", "x-o-nc-opy");
    	tmpString = tmpString.replaceAll("(?i)oncut", "x-o-nc-ut");
    	tmpString = tmpString.replaceAll("(?i)ondataavailable", "x-o-nd-ataa-vailable");
    	tmpString = tmpString.replaceAll("(?i)ondatasetchanged", "x-o-nd-atasetc-hanged");
    	tmpString = tmpString.replaceAll("(?i)ondatasetcomplete", "x-o-nd-atasetc-omplete");
    	tmpString = tmpString.replaceAll("(?i)ondblclick", "x-o-ndblc-lick");
    	tmpString = tmpString.replaceAll("(?i)ondeactivate", "x-o-nd-ea-ctivate");
    	tmpString = tmpString.replaceAll("(?i)ondrag", "x-o-nd-rag");
    	tmpString = tmpString.replaceAll("(?i)ondrop", "x-o-nd-rop");
    	tmpString = tmpString.replaceAll("(?i)onerror", "x-o-ne-rror");
    	tmpString = tmpString.replaceAll("(?i)onfilterchange", "x-o-nf-ilterc-hange");
    	tmpString = tmpString.replaceAll("(?i)onfinish", "x-o-nf-inish");
    	tmpString = tmpString.replaceAll("(?i)onfocus", "x-o-nf-ocus");
    	tmpString = tmpString.replaceAll("(?i)onhelp", "x-o-nh-elp");
    	tmpString = tmpString.replaceAll("(?i)onkeydown", "x-o-nk-eyd-own");
    	tmpString = tmpString.replaceAll("(?i)onkeypress", "x-o-nk-eyp-ress");
    	tmpString = tmpString.replaceAll("(?i)onkeyup", "x-o-nk-eyu-p");
    	tmpString = tmpString.replaceAll("(?i)onlayoutcomplete", "x-o-nl-ayoutc-omplete");
    	tmpString = tmpString.replaceAll("(?i)onload", "x-o-nl-oad");
    	tmpString = tmpString.replaceAll("(?i)onlosecapture", "x-o-nl-osec-apture");
    	tmpString = tmpString.replaceAll("(?i)onmouse", "x-o-nm-ouse");
    	tmpString = tmpString.replaceAll("(?i)onpaste", "x-o-np-aste");
    	tmpString = tmpString.replaceAll("(?i)onpropertychange", "x-o-np-ropertyc-hange");
    	tmpString = tmpString.replaceAll("(?i)onreadystatechange", "x-o-nr-eadys-tatec-hange");
    	
    	tmpString = tmpString.replaceAll("(?i)onreset","x-o-nr-eset");
    	tmpString = tmpString.replaceAll("(?i)onresize","x-o-nr-esize");
    	tmpString = tmpString.replaceAll("(?i)onresizeend","x-onresizeend");
    	tmpString = tmpString.replaceAll("(?i)onresizestart","x-o-nr-esizes-tart");
    	tmpString = tmpString.replaceAll("(?i)onrowenter","x-o-nr-owe-nter");
    	tmpString = tmpString.replaceAll("(?i)onrowexit","x-o-nr-owe-xit");
    	tmpString = tmpString.replaceAll("(?i)onrowsdelete","x-o-nr-owsd-elete");
    	tmpString = tmpString.replaceAll("(?i)onrowsinserted","x-o-nr-owsi-nserted");
    	tmpString = tmpString.replaceAll("(?i)onscroll","x-o-ns-croll");
    	tmpString = tmpString.replaceAll("(?i)onselect","x-o-ns-elect");
    	tmpString = tmpString.replaceAll("(?i)onselectionchange","x-o-ns-electionc-hange");
    	tmpString = tmpString.replaceAll("(?i)onselectstart","x-o-ns-elects-tart");
    	tmpString = tmpString.replaceAll("(?i)onstart","x-o-ns-tart");
    	tmpString = tmpString.replaceAll("(?i)onstop","x-o-ns-top");
    	tmpString = tmpString.replaceAll("(?i)onsubmit","x-o-ns-ubmit");
    	tmpString = tmpString.replaceAll("(?i)onunload","x-o-nu-nload");
    	tmpString = tmpString.replaceAll("(?i)onMessage","x-o-nM-essage");
    	tmpString = tmpString.replaceAll("(?i)onRowDelete","x-o-nR-owD-elete");
    	tmpString = tmpString.replaceAll("(?i)onOffline","x-o-nO-ffl-ine");
    	tmpString = tmpString.replaceAll("(?i)onRowInserted","x-o-nR-owI-nserted");
    	tmpString = tmpString.replaceAll("(?i)FSCommand","x-F-SC-ommand");
    	tmpString = tmpString.replaceAll("(?i)onOnline","x-o-nO-nline");
    	tmpString = tmpString.replaceAll("(?i)onSeek","x-o-nS-eek");
    	tmpString = tmpString.replaceAll("(?i)onAbort","x-o-nA-bort");
    	tmpString = tmpString.replaceAll("(?i)onOutOfSync","x-o-nO-utO-fS-ync");
    	tmpString = tmpString.replaceAll("(?i)onStorage","x-o-nS-torage");
    	tmpString = tmpString.replaceAll("(?i)onActivate","x-o-nA-ctivate");
    	tmpString = tmpString.replaceAll("(?i)onPause","x-o-nP-ause");
    	tmpString = tmpString.replaceAll("(?i)onSyncRestored","x-o-nS-yncR-estored");
    	tmpString = tmpString.replaceAll("(?i)onBegin","x-o-nB-egin");
    	tmpString = tmpString.replaceAll("(?i)onPopState","x-o-nP-opS-tate");
    	tmpString = tmpString.replaceAll("(?i)onTimeError","x-o-nT-imeE-rror");
    	tmpString = tmpString.replaceAll("(?i)onDragDrop","x-o-nD-ragD-rop");
    	tmpString = tmpString.replaceAll("(?i)onProgress","x-o-nP-rogress");
    	tmpString = tmpString.replaceAll("(?i)onTrackChange","x-o-nT-rackC-hange");
    	tmpString = tmpString.replaceAll("(?i)onEnd","x-o-nE-nd");
    	tmpString = tmpString.replaceAll("(?i)onRedo","x-o-nR-edo");
    	tmpString = tmpString.replaceAll("(?i)onUndo","x-o-nU-ndo");
    	tmpString = tmpString.replaceAll("(?i)onHashChange","x-o-nH-ashC-hange");
    	tmpString = tmpString.replaceAll("(?i)onRepeat","x-o-nR-epeat");
    	tmpString = tmpString.replaceAll("(?i)onURLFlip","x-o-nU-RLF-lip");
    	tmpString = tmpString.replaceAll("(?i)onInput","x-o-nI-nput");
    	tmpString = tmpString.replaceAll("(?i)onResume","x-o-nR-esume");
    	tmpString = tmpString.replaceAll("(?i)seekSegmentTime","x-s-eekS-egmentT-ime");
    	tmpString = tmpString.replaceAll("(?i)onMediaComplete","x-o-nM-ediaC-omplete");
    	tmpString = tmpString.replaceAll("(?i)onReverse","x-o-nR-everse");
    	tmpString = tmpString.replaceAll("(?i)onRowsEnter","x-o-nR-owsE-nter");
    	tmpString = tmpString.replaceAll("(?i)onMediaError","x-o-nM-ediaE-rror");
    	tmpString = tmpString.replaceAll("(?i)java.lang.Runtime","x-j-ava.l-ang.R-untime");
    	tmpString = tmpString.replaceAll("(?i)getRuntime","x-g-etR-untime");
    	tmpString = tmpString.replaceAll("(?i)onwheel","x-o-nw-heel");
    	tmpString = tmpString.replaceAll("(?i)onsearch","x-o-ns-earch");
    	tmpString = tmpString.replaceAll("(?i)onpagehide","x-o-np-ageh-ide");
    	tmpString = tmpString.replaceAll("(?i)onpageshow","x-o-np-ages-how");
    	tmpString = tmpString.replaceAll("(?i)oninvalid","x-o-ni-nv-alid");
    	tmpString = tmpString.replaceAll("(?i)oncanplay","x-o-nc-anp-lay");
    	tmpString = tmpString.replaceAll("(?i)oncanplaythrough","x-o-nc-anp-layt-hrough");
    	tmpString = tmpString.replaceAll("(?i)oncuechange","x-o-nc-uec-hange");
    	tmpString = tmpString.replaceAll("(?i)ondurationchange","x-o-nd-urationc-hange");
    	tmpString = tmpString.replaceAll("(?i)onloadedmetadata","x-o-nl-oadedm-etad-ata");
    	tmpString = tmpString.replaceAll("(?i)onloadstart","x-o-nl-oads-tart");
    	tmpString = tmpString.replaceAll("(?i)onseeked","x-o-ns-eeked");
    	tmpString = tmpString.replaceAll("(?i)onplay","x-o-np-lay");
    	tmpString = tmpString.replaceAll("(?i)onplaying","x-o-np-laying");
    	tmpString = tmpString.replaceAll("(?i)onratechange","x-o-nr-atec-hange");
    	tmpString = tmpString.replaceAll("(?i)onseeking","x-o-ns-eeking");
    	tmpString = tmpString.replaceAll("(?i)onstalled","x-o-ns-talled");
    	tmpString = tmpString.replaceAll("(?i)onsuspend","x-o-ns-uspend");
    	tmpString = tmpString.replaceAll("(?i)ontimeupdate","x-o-nt-imeu-pdate");
    	tmpString = tmpString.replaceAll("(?i)onvolumechange","x-o-nv-olumec-hange");
    	tmpString = tmpString.replaceAll("(?i)onwating","x-o-nw-ating");
    	
    	tmpString = tmpString.replaceAll("(?i)onemptied","x-o-ne-mptied");
    	tmpString = tmpString.replaceAll("(?i)ontoggle","x-o-nt-oggle");
    	tmpString = tmpString.replaceAll("(?i)video","x-v-ideo");
    	tmpString = tmpString.replaceAll("(?i)audio","x-a-udio");
    	tmpString = tmpString.replaceAll("(?i)details","x-d-etails");
    	tmpString = tmpString.replaceAll("(?i)onended","x-o-ne-nded");
    	tmpString = tmpString.replaceAll("(?i)onloadeddata","x-o-nl-oadedd-ata");
    	tmpString = tmpString.replaceAll("(?i)onwaiting","x-o-nw-aiting");
    	
    	tmpString = tmpString.replaceAll("(?i)grameset", "x-g-rames-et");
    	tmpString = tmpString.replaceAll("(?i)bgsound", "x-b-gs-ound");
    	tmpString = tmpString.replaceAll("(?i)enerror", "x-e-ne-rror");
    	tmpString = tmpString.replaceAll("(?i)prompt", "x-pr-ompt");
    	tmpString = tmpString.replaceAll("(?i)content", "x-con-te-nt");
    	tmpString = tmpString.replaceAll("(?i)url", "x-u-r-l");
    	tmpString = tmpString.replaceAll("(?i)body", "x-bo-dy");
    	tmpString = tmpString.replaceAll("(?i)confirm", "x-con-fi-rm");
    	tmpString = tmpString.replaceAll("(?i)textarea", "x-te-xt-ar-ea");
    	
    	
    	
    	return  tmpString;
    	
    }
    

    /**
     * <p>날짜 형식의 문자열 내부에 마이너스 character(-)를 추가한다.</p>
     *
     * <pre>
     *   StringUtil.addMinusChar("20100901") = "2010-09-01"
     * </pre>
     *
     * @param date  입력받는 문자열
     * @return " - "가 추가된 입력문자열
     */
	public static String addMinusChar(String date) {
		if(date.length() == 8)
		    return date.substring(0,4).concat("-").concat(date.substring(4,6)).concat("-").concat(date.substring(6,8));
		else return "";
	}
	
	/** 비공개키 암복호화를 위한 기능 추가 */
   private String iv;
   private Key keySpec;
   private static final String SECURITY_KEY = "naksinuriApi2022";

	public EgovStringUtil AES128Util() {
		try { 
			this.iv = SECURITY_KEY.substring(0, 16);
			byte[] keyBytes = new byte[16];
			byte[] b = SECURITY_KEY.getBytes("UTF-8");
			int len = b.length;
			if (len > keyBytes.length) {
				len = keyBytes.length;
			}
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			this.keySpec = keySpec;
		} catch (Exception e) {
			this.keySpec = null;
		}
		return this;
	}
   
	//encrypt
	public String makeScrtyKey(final String str) {
		if(keySpec!=null) {
			try {
				Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
				c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
				byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
				String enStr = new String(Base64.encodeBase64(encrypted));
				return URLEncoder.encode(enStr,"utf-8");
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}

	//decrypt
	public String removeScrtyKey(final String str) {
		if(keySpec!=null) {
			try {
				String destr = URLDecoder.decode(str, "utf-8");
				Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
				c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
				byte[] byteStr = Base64.decodeBase64(destr.getBytes());
				return new String(c.doFinal(byteStr), "UTF-8");
			} catch (Exception e) {
				return "";
			}
		} else {
			return "";
		}
	}
}
