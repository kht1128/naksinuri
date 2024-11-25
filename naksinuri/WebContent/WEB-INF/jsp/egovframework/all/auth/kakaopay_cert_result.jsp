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
<%@page import="com.barocert.kakaocert.identity.IdentityStatus"%>

<%
	/*
	 * 본인인증 요청시 반환된 접수아이디를 통해 서명 상태를 확인합니다.
	 */

    // 이용기관코드, 파트너가 등록한 이용기관의 코드, (파트너 사이트에서 확인가능)
    String clientCode = kakaopay_client_code;

	String receiptID = (String)request.getAttribute("receiptID");
	
	
	
	
	
	
	
	
	
	
	System.out.println("receiptID:"+receiptID);


    JSONObject data = new JSONObject();	
        
    	
    /* session.setAttribute("vldVrfcAuthTempDataBirthDay", "");
	session.setAttribute("vldVrfcAuthTempDataName", "");
	session.setAttribute("vldVrfcAuthTempDataPhoneNo", ""); */
    
	ResultVerifyAuth resultVerifyAuth = null;
	
	IdentityStatus result = null;
	
	if(receiptID != null ) {
		try {
			result = kakaocertService.getIdentityStatus(clientCode, receiptID);   
               
			if(result!=null) {
   				
				JSONObject rawdata = new JSONObject();	
   					   				
				String _receiptID = (String)result.getReceiptID();//(접수아이디)
				
               	String _clientCode = (String)result.getClientCode();//(이용기관코드)
               	
               	System.out.println("_clientCode:"+_clientCode);
               	/* String _scheme = (String)result.getScheme();//(접수아이디) */
               	
               	/* String _clientName = (String)resultVerifyAuth.getClientName();//(이용기관명)
               	String _subClientName = (String)resultVerifyAuth.getSubClientName();//(별칭)
               	String _subClientCode = (String)resultVerifyAuth.getSubClientCode();//(별칭코드) */
               	
               	int _state = result.getState();//(상태)
               	
               /* 	int _expires_in = resultVerifyAuth.getExpires_in();//(인증요청 만료시간(초))
               	String _callCenterNum = (String)resultVerifyAuth.getCallCenterNum();//(고객센터 전화번호)
               	String _callCenterName = (String)resultVerifyAuth.getCallCenterName();//(고객센터명)
               	String _tmstitle = (String)resultVerifyAuth.getTmstitle();//(인증요청 메시지 제목)
               	String _tmsmessage = (String)resultVerifyAuth.getTmsmessage();//(인증요청 메시지 부가내용)
               	boolean _allowSimpleRegistYN = resultVerifyAuth.isAllowSimpleRegistYN();//(은행계좌 실명확인 생략여부)
               	boolean _verifyNameYN = resultVerifyAuth.isVerifyNameYN();//(수신자 실명확인 여부) */
               	
               	String _requestDT = (String)result.getRequestDT();//(카카오 인증서버 등록일시)
               	String _expireDT = (String)result.getExpireDT();//(인증 만료일시)
               	
               	/* String _regDT = (String)resultVerifyAuth.getRegDT();//(인증요청 등록일시) */
               	
               	String _viewDT = (String)result.getViewDT();//(수신자 카카오톡 인증메시지 확인일시)
               	String _completeDT = (String)result.getCompleteDT();//(수신자 카카오톡 전자서명 완료일시)
               	String _verifyDT = (String)result.getVerifyDT();//(서명 검증일시)
               	
               	/* String _payload = (String)resultVerifyAuth.getPayload();//(payload) */
			
               	String _mberBrdt = "";
               	String _mberCttpc = "";
               	String _mberNm = "";
               	String mberBrdt = (String)request.getAttribute("mberBrdt");
               	System.out.println("mberBrdt:"+mberBrdt);
            	String mberNm = (String)request.getAttribute("mberNm");
            	String mberCttpc = (String)request.getAttribute("mberCttpc");
            	
               	EgovStringUtil mEgovStringUtil = new EgovStringUtil();
               	mEgovStringUtil.AES128Util();
               	/* String parsePayload = mEgovStringUtil.removeScrtyKey(_payload);
               	if(parsePayload!=null && parsePayload.length()!=0) {
					String[] s = parsePayload.split("__");
					_mberBrdt = s[0];
                	_mberCttpc = s[1];
                	_mberNm = s[2];
               	} */
               
               	rawdata.put("receiptID",_receiptID);
               	rawdata.put("clientCode",_clientCode);
               	/* rawdata.put("scheme",_scheme); */
               	/* rawdata.put("clientName",_clientName);
               	rawdata.put("subClientName",_subClientName);
               	rawdata.put("subClientCode",_subClientCode); */
               	rawdata.put("state",_state);
               	/* rawdata.put("expires_in",_expires_in);
               	rawdata.put("callCenterNum",_callCenterNum);
               	rawdata.put("callCenterName",_callCenterName);
               	rawdata.put("tmstitle",_tmstitle);
               	rawdata.put("tmsmessage",_tmsmessage);
               	rawdata.put("allowSimpleRegistYN",_allowSimpleRegistYN);
               	rawdata.put("verifyNameYN",_verifyNameYN); */
               	rawdata.put("requestDT",_requestDT);
               	rawdata.put("expireDT",_expireDT);
               	/* rawdata.put("regDT",_regDT); */
               	rawdata.put("viewDT",_viewDT);
               	rawdata.put("completeDT",_completeDT);
               	rawdata.put("verifyDT",_verifyDT);
               	/* rawdata.put("payload",_payload); */
               
               	rawdata.put("mberBrdt",_mberBrdt);
               	rawdata.put("mberCttpc",_mberCttpc);
               	rawdata.put("mberNm",_mberNm);
               
               	session.setAttribute("AGE14_UNDER_MDL_TKN", _receiptID);
				session.setAttribute("login.certNum.rst", _receiptID);

				session.setAttribute("vldVrfcAuthTempDataBirthDay", _mberBrdt);
				session.setAttribute("vldVrfcAuthTempDataName", _mberNm);
				session.setAttribute("vldVrfcAuthTempDataPhoneNo", _mberCttpc);
                                       
				session.setAttribute("vldVrfcAuthTempDataRsltCd", "kakaopay"+_state);
				session.setAttribute("vldVrfcAuthTempDataRsltMsg", "");
				
				session.setAttribute("kcbOkcertJsonData", rawdata);
				
	           	data.put("error", "0");
	           	data.put("msg", "정상");
	           	data.put("rawdata", rawdata);
	           	System.out.println("rawdata:"+rawdata);
	           	System.out.println("rawdata2:"+data);
	           	System.out.println("_mberNm:"+_mberNm);
   			} else {
   				data.put("error", "1");
   		    	data.put("msg", "본인확인 정보 조회가 실패하였습니다.");
   		 		System.out.println("rawdata2:"+data);
   			}
               
		} catch(BarocertException ke) {
			
			System.out.println("rawdata3:"+data);
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
		System.out.println("rawdata4:"+data);
   	}
	   	    
    //System.out.println(data);
    response.setContentType("application/json");
	out.print(data);

%>