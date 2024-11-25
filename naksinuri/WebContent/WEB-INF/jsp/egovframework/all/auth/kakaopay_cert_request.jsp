<%@page import="com.google.gson.JsonObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String kakaopay_link_id = (String)request.getAttribute("kakaopay_link_id");
	String kakaopay_secret_key = (String)request.getAttribute("kakaopay_secret_key");
	String kakaopay_client_code = (String)request.getAttribute("kakaopay_client_code");
%>
<%--
===================================================================================
* Kakaocert API Java SDK JSP Example
*
* Class Module for base module for Popbill API SDK. It include base functionality for
* RESTful web service request and parse json result. It uses Linkhub class module
* to accomplish authentication APIs.

Update date : 2022-04-06
===================================================================================
--%>

<%-- <jsp:useBean id="kakaocertService" scope="application" class="com.kakaocert.api.KakaocertServiceImp"/> --%>
<jsp:useBean id="kakaocertService" scope="application" class="com.barocert.kakaocert.KakaocertServiceImp"/>

<%-- 링크아이디 --%>
<jsp:setProperty name="kakaocertService" property="linkID" value="<%=kakaopay_link_id%>"/>

<%-- 비밀키, 사용자 인증에 사용되는 정보이므로 유출에 주의 --%>
<jsp:setProperty name="kakaocertService" property="secretKey" value="<%=kakaopay_secret_key%>"/>

<%-- 인증토큰 발급 IP 제한 On/Off, ture-제한기능 사용(기본값-권장),  false-제한기능 미사용 --%>
<jsp:setProperty name="kakaocertService" property="IPRestrictOnOff" value="true"/>

<%-- 카카오써트 API 서비스 고정 IP 사용여부, true-사용, false-미사용, 기본값(false) --%>
<jsp:setProperty name="kakaocertService" property="useStaticIP" value="false"/>

<%-- <%@page import="com.kakaocert.api.verifyauth.RequestVerifyAuth"%>
<%@page import="com.kakaocert.api.KakaocertException"%> --%>
<%@page import="java.util.*"%>
<%@page import="org.json.simple.*"%>
<%@page import="egovframework.utils.*"%>

<%@page import="com.barocert.BarocertException"%>
<%@page import="com.barocert.kakaocert.identity.Identity"%>
<%@page import="com.barocert.kakaocert.identity.IdentityReceipt"%>

<%@page import="com.fasterxml.jackson.core.JsonProcessingException"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>


<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.parser.ParseException"%>



<%
    /*
     * 카카오톡 사용자에게 본인인증 서명을 요청합니다.
     */

 	// 본인인증 요청 정보 객체
 	Identity identity = new Identity(); 
    
    String mberBrdt = (String)request.getAttribute("mberBrdt");
	String mberNm = (String)request.getAttribute("mberNm");
	String mberCttpc = (String)request.getAttribute("mberCttpc");
    
	// 이용기관코드, 파트너가 등록한 이용기관의 코드, (파트너 사이트에서 확인가능)
    String clientCode = kakaopay_client_code;
	
	// 수신자 휴대폰번호 - 11자 (하이픈 제외)
	identity.setReceiverHP(kakaocertService.encrypt(mberCttpc));
	// 수신자 성명 - 80자
	identity.setReceiverName(kakaocertService.encrypt(mberNm));
	// 수신자 생년월일 - 8자 (yyyyMMdd)
	identity.setReceiverBirthday(kakaocertService.encrypt(mberBrdt));
	// 인증요청 메시지 제목 - 최대 40자
	identity.setReqTitle("낚시전문교육 본인확인");
	// 커스텀 메시지 - 최대 500자
	identity.setExtraMessage(kakaocertService.encrypt("낚시전문교육 로그인을 위한 본인확인을 진행합니다."));
	// 인증요청 만료시간 - 최대 1,000(초)까지 입력 가능
	identity.setExpireIn(60);
	// 서명 원문 - 최대 40자 까지 입력가능
	/* identity.setToken(kakaocertService.encrypt("본인인증 요청 원문")); 아래에 있음 */
	// AppToApp 인증요청 여부
	// true - AppToApp 인증방식, false - Talk Message 인증방식
	identity.setAppUseYN(false);

    java.util.Random ran = new Random();
	//랜던 문자 길이
	int numLength = 16;
	String randomStr = "";
	for (int i = 0; i < numLength; i++) {
		//0 ~ 9 랜덤 숫자 생성
		randomStr += ran.nextInt(10);
	}
	String reqNum = randomStr;
    
    // Token 원문, 보안을 위해 1회용으로 생성
    // 인증완료시, getVerifyAuthResult API의 returnToken 항목값으로 반환
    /* requestObj.setToken(reqNum); */
    
    identity.setToken(kakaocertService.encrypt(reqNum));
	
	
    IdentityReceipt result = null;
	
     
    
/* 
    // 본인인증 요청 정보 Object
    RequestVerifyAuth requestObj = new RequestVerifyAuth();

    // 고객센터 전화번호, 카카오톡 인증메시지 중 "고객센터" 항목에 표시
    requestObj.setCallCenterNum("1833-7139");
    
    // 고객센터명, 카카오톡 인증메시지 중 "고객센터명" 항목에 표시
    requestObj.setCallCenterName("낚시전문교육");

    // 인증요청 만료시간(초), 최대값 : 1000,	인증요청 만료시간(초) 내에 미인증시, 만료 상태로 처리됨
    requestObj.setExpires_in(60);

    // 수신자 생년월일, 형식 : YYYYMMDD
    requestObj.setReceiverBirthDay(mberBrdt);

    // 수신자 휴대폰번호
    requestObj.setReceiverHP(mberCttpc);

    // 수신자 성명
    requestObj.setReceiverName(mberNm);

    // 인증요청 메시지 부가내용, 카카오톡 인증메시지 중 상단에 표시
    requestObj.setTMSMessage("낚시전문교육 로그인을 위한 본인확인을 진행합니다.");

    // 별칭코드, 이용기관이 생성한 별칭코드 (파트너 사이트에서 확인가능)
    // 카카오톡 인증메시지 중 "요청기관" 항목에 표시
    // 별칭코드 미 기재시 이용기관의 이용기관명이 "요청기관" 항목에 표시
    //requestObj.setSubClientID("한국어촌어항공단");

    // 인증요청 메시지 제목, 카카오톡 인증메시지 중 "요청구분" 항목에 표시
    requestObj.setTMSTitle("낚시전문교육 본인확인"); */


    /*
     * 은행계좌 실명확인 생략여부
     * true : 은행계좌 실명확인 절차를 생략
     * false : 은행계좌 실명확인 절차를 진행
     *
     * - 인증메시지를 수신한 사용자가 카카오인증 비회원일 경우,
     *   카카오인증 회원등록 절차를 거쳐 은행계좌 실명확인 절차이후 전자서명 가능
     */
    /* requestObj.setAllowSimpleRegistYN(false); */

    /*
     * 수신자 실명확인 여부
     * true : 카카오페이가 본인인증을 통해 확보한 사용자 실명과 ReceiverName 값을 비교
     * false : 카카오페이가 본인인증을 통해 확보한 사용자 실명과 RecevierName 값을 비교하지 않음.
     */
    /* requestObj.setVerifyNameYN(true); */

    // PayLoad, 이용기관이 API 요청마다 생성한 payload(메모) 값
    /* EgovStringUtil mEgovStringUtil = new EgovStringUtil();
    mEgovStringUtil.AES128Util(); */
    /* String _payLoad = mEgovStringUtil.makeScrtyKey(mberBrdt+"__"+mberCttpc+"__"+mberNm); */
    
    /* requestObj.setPayLoad(_payLoad); */

    JSONObject data = new JSONObject();	
    
 	//String receiptID = null; 
    
    
    try {
    	
    	result = kakaocertService.requestIdentity(clientCode, identity);
    	
    	 
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	String resultJson = objectMapper.writeValueAsString(result);
     
        JSONParser jsonParser = new JSONParser();
        
        //3. To Object
        Object obj = jsonParser.parse(resultJson);
        
        //4. To JsonObject
        JSONObject jsonObj = (JSONObject) obj;		
        
        jsonObj.put("mberBrdt", mberBrdt);
        jsonObj.put("mberNm", mberNm);
        jsonObj.put("mberCttpc", mberCttpc);
        
    
    	
    	if(result != null ) {    		
	    	data.put("error", "0");
	    	data.put("msg", "정상");
	    	data.put("rawdata", jsonObj); 	
	    	
	   	} else {
	   		data.put("error", "1");
		    data.put("msg", "본인확인 요청이 실패하였습니다.");
	   	}
    	
    } catch(BarocertException ke) {
        //throw ke;
    	// 요청시 에러발생
    	//System.out.println(ke.getCode());
    	//System.out.println(ke.getMessage());
    	data.put("error", "1");
    	data.put("msg", ke.getMessage());
  	}
    
    //System.out.println(data);
    response.setContentType("application/json");
	out.print(data);

%>