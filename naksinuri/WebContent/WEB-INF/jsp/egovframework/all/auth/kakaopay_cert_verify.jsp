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

<%@page import="com.kakaocert.api.verifyauth.ResultVerifyAuth"%>
<%@page import="com.kakaocert.api.KakaocertException"%>
<%@page import="java.util.*"%>
<%@page import="org.json.simple.*"%>
<%@page import="egovframework.utils.*"%>

<%@page import="com.barocert.BarocertException"%>
<%@page import="com.barocert.kakaocert.identity.IdentityResult"%>

<%
	/*
	 * 본인인증 요청시 반환된 접수아이디를 통해 서명 상태를 확인합니다.
	 */

    // 이용기관코드, 파트너가 등록한 이용기관의 코드, (파트너 사이트에서 확인가능)
    String clientCode = kakaopay_client_code;

	String receiptID = (String)request.getAttribute("receiptID");

    JSONObject data = new JSONObject();	
        
    
	IdentityResult result = null;
	
	if(receiptID != null ) {
		try {
			result = kakaocertService.verifyIdentity(clientCode, receiptID);
               
		} catch(BarocertException ke) {
			//throw ke;
			// 정보획득시 에러발생
			//System.out.println(ke.getCode());
			//System.out.println(ke.getMessage());
			data.put("error", "1");
			data.put("msg", ke.getMessage());
		}  
		
   	} else {
   		data.put("error", "1");
	    data.put("msg", "본인확인 요청이 실패하였습니다.");
   	}
	

%>