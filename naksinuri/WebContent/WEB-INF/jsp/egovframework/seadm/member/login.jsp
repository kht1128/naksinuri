<%@ page import="kcb.org.json.*" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import ="java.util.*,java.text.SimpleDateFormat"%>
<%	
    //**************************************************************************
	// 파일명 : phone_popup2.jsp
	// - 팝업페이지
	// 휴대폰 본인확인 서비스 인증페이지 호출 화면
    //
    // ※주의
    // 	실제 운영시에는 화면에 보여지는 데이터를 삭제하여 주시기 바랍니다.
    // 	방문자에게 사이트데이터가 노출될 수 있습니다.
    //**************************************************************************
	
	//' UTF-8 환경의 경우 주석 해제 + 전체 페이지 상단 charset, pageEncoding 및 파일인코딩 변경 필요
	//request.setCharacterEncoding("UTF-8");
	
	/**************************************************************************
	 * okcert3 휴대폰 본인확인 서비스 파라미터
	 **************************************************************************/
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//' 회원사 사이트명, URL
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String SITE_NAME = (String)request.getAttribute("kcbokcert_sitenm");// 요청사이트명 
	String SITE_URL = (String)request.getAttribute("kcbokcert_siteurl");//

	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' KCB로부터 부여받은 회원사코드(아이디) 설정 (12자리) 
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String CP_CD = (String)request.getAttribute("kcbokcert_cpid");//request.getParameter("CP_CD");;	// 회원사코드
	session.setAttribute("PHONE_CP_CD", CP_CD);

	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 리턴 URL 설정
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//' opener(popup1)의 도메인과 일치하도록 설정해야 함. 
	//' (http://www.test.co.kr과 http://test.co.kr는 다른 도메인으로 인식하며, http 및 https도 일치해야 함)
	// String RETURN_URL = "http://"+request.getServerName()+":8080/phone_popup/phone_popup3.jsp";// 인증 완료 후 리턴될 URL (도메인 포함 full path)
	String RETURN_URL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/all/auth/kcbOkCertResult.do";// 인증 완료 후 리턴될 URL (도메인 포함 full path)
	//String RETURN_URL = "https://www.naksinuri.kr/all/auth/kcbOkCertResult.do";// 인증 완료 후 리턴될 URL (도메인 포함 full path)
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 인증요청사유코드 (가이드 문서 참조)
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String RQST_CAUS_CD = "00";
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//' 채널 코드 (공백가능. 필요한 회원사에서만 입력)
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//String CHNL_CD = request.getParameter("CHNL_CD");	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 리턴메시지 (공백가능. returnUrl에서 같이 전달받고자 하는 값이 있다면 설정.)
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	//String RETURN_MSG = "";
	
	
	//' ########################################################################
	//' # 타겟 및 팝업URL : 운영/테스트 전환시 변경 필요
	//' ########################################################################
	String target = "PROD";	// 테스트="TEST", 운영="PROD"
	//String popupUrl = "";	// 테스트 URL
	String popupUrl = "https://safe.ok-name.co.kr/CommonSvl";// 운영 URL
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 라이센스 파일
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String license = (String)request.getAttribute("kcbokcert_licensepath") + CP_CD + "_IDS_01_" + target + "_AES_license.dat";
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    //' 서비스명 (고정값)
    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	String svcName = "IDS_HS_POPUP_START";
	
	/**************************************************************************
	okcert3 요청 정보
	**************************************************************************/
	JSONObject reqJson = new JSONObject();
	reqJson.put("RETURN_URL", RETURN_URL);
	reqJson.put("SITE_NAME", SITE_NAME);
	reqJson.put("SITE_URL", SITE_URL);
	reqJson.put("RQST_CAUS_CD", RQST_CAUS_CD);
	
	//reqJson.put("CHNL_CD", CHNL_CD);
	//reqJson.put("RETURN_MSG", RETURN_MSG);
	
	//' 거래일련번호는 기본적으로 모듈 내에서 자동 채번되고 채번된 값을 리턴해줌.
	//'	회원사가 직접 채번하길 원하는 경우에만 아래 코드를 주석 해제 후 사용.
	//' 각 거래마다 중복 없는 String 을 생성하여 입력. 최대길이:20
	
	java.util.Random ran = new Random();
	
    //날짜 생성
    Calendar today = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String day = sdf.format(today.getTime());

    //랜덤 문자 길이
    int smsnumLength = 6;
    String smsrandomStr = "";

    for (int i = 0; i < smsnumLength; i++) {
        //0 ~ 9 랜덤 숫자 생성
        smsrandomStr += ran.nextInt(10);
    }

	//reqNum은 최대 40byte 까지 사용 가능
    String TX_SEQ_NO = day + smsrandomStr;
	
	reqJson.put("TX_SEQ_NO", TX_SEQ_NO); 
	
	String reqStr = reqJson.toString();
	
	/**************************************************************************
	okcert3 실행
	**************************************************************************/
	kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
	
	// '************ IBM JDK 사용 시, 주석 해제하여 호출 ************
	// okcert.setProtocol2type("22");
	// '객체 내 license를 리로드해야 될 경우에만 주석 해제하여 호출. (v1.1.7 이후 라이센스는 파일위치를 key로 하여 static HashMap으로 사용됨)
	// okcert.delLicense(license);
	
	//' callOkCert 메소드호출 : String license 파일 path로 라이센스 로드
	String resultStr = okcert.callOkCert(target, CP_CD, svcName, license,  reqStr);
	/*
	// 'OkCert3 내부에서 String license 파일 path로 라이센스를 못 읽어올 경우(Executable Jar 환경 등에서 발생),
	// '메소드 마지막 파라미터에 InputStream를 사용하여 라이센스 로드
	String resultStr = null;
	if ( ! okcert.containsLicense(license) ) {			// 로드된 라이센스 정보가 HashMap에 없는 경우
		java.io.InputStream is = new java.io.FileInputStream(license);	// 환경에 맞게 InputStream 로드
		resultStr = okcert.callOkCert(target, CP_CD, svcName, license,  reqStr, is);
	} else {											// 로드된 라이센스 정보가 HashMap에 있는 경우
		resultStr = okcert.callOkCert(target, CP_CD, svcName, license,  reqStr);
	}
	*/
	
	JSONObject resJson = new JSONObject(resultStr);

    String RSLT_CD =  resJson.getString("RSLT_CD");
    String RSLT_MSG = resJson.getString("RSLT_MSG");
    //if(resJson.has("TX_SEQ_NO")) String TX_SEQ_NO = resJson.getString("TX_SEQ_NO"); // 필요 시 거래 일련 번호 에 대하여 DB저장 등의 처리
    String MDL_TKN = "";
    
	boolean succ = false;
	
    if ("B000".equals(RSLT_CD) && resJson.has("MDL_TKN") ) {
		MDL_TKN = resJson.getString("MDL_TKN");
		succ = true;
    }
    
    session.setAttribute("login.certNum.req", TX_SEQ_NO);
       
%>	 

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="bootstrap admin template">
    <meta name="author" content="">
    
    <title>낚시누리 관리자</title>
    
    <link rel="apple-touch-icon" href="/common/seadm/assets/images/apple-touch-icon.png">
    <link rel="shortcut icon" href="/common/seadm/assets/images/favicon.ico">
    
    <!-- Stylesheets -->
    <link rel="stylesheet" href="/common/seadm/global/css/bootstrap.css">
    <link rel="stylesheet" href="/common/seadm/global/css/bootstrap-extend.css">
    <link rel="stylesheet" href="/common/seadm/assets/css/site.min.css">
	
	<!-- Plugins -->
	<link rel="stylesheet" href="/common/seadm/global/vendor/animsition/animsition.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/asscrollable/asScrollable.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/switchery/switchery.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/intro-js/introjs.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/slidepanel/slidePanel.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/flag-icon-css/flag-icon.min.css">
		<link rel="stylesheet" href="/common/seadm/global/vendor/alertify/alertify.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/toastr/toastr.min.css">

	<!-- Page -->
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/pages/login-v3.min.css">
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/alertify.min.css">
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/toastr.min.css">
        
    <!-- Fonts -->
    <link rel="stylesheet" href="/common/seadm/global/fonts/weather-icons/weather-icons.css">
    <link rel="stylesheet" href="/common/seadm/global/fonts/web-icons/web-icons.min.css">
    <link rel="stylesheet" href="/common/seadm/global/fonts/brand-icons/brand-icons.min.css">
    <link rel='stylesheet' href='/common/css/fontsgoogleapiscom.css?family=Roboto:300,400,500,300italic'>
	<script src="/common/js/fontawesome-all.js"></script>
	    
    <!--[if lt IE 9]>
    <script src="/common/seadm/global/vendor/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    
    <!--[if lt IE 10]>
    <script src="/common/seadm/global/vendor/media-match/media.match.min.js"></script>
    <script src="/common/seadm/global/vendor/respond/respond.min.js"></script>
    <![endif]-->
    
    <!-- Scripts -->
    <script src="/common/seadm/global/vendor/breakpoints/breakpoints.js"></script>
    <script>
      Breakpoints();
    </script>
    
    <script src="/common/js/jquery-1.10.2.js"></script>
    
  </head>

	<script>
	$(document).ready(function(){
		setTimeout(function(){
			var alert_data_type = '${alert_data.type}';
			var message = '${alert_data.message}';
			var title = '${alert_data.title}';
			var closebtn = '${alert_data.closebtn}';
			var timer = '${alert_data.timer}'; 
			if(alert_data_type!=null && alert_data_type.length!=0 && alert_data_type == 'alert' ) {
				alertify.alert(message);
			} else {
				if(message!=null && message.length!=0) {
					toastr.info(message,title,{
						tapToDismiss: true,
						closeButton: closebtn=='Y'?true:false,
						debug: false,
						newestOnTop: true,
						progressBar: true,
						positionClass: "toast-top-full-width",
						preventDuplicates: false,
						onclick: false,
						//showDuration: "300",
						//hideDuration: "300",
						timeOut: timer,
						showEasing: "swing",
						hideEasing: "linear",
						showMethod: "slideDown",//fadeIn
						hideMethod: "fadeOut",
						allowHtml: true,
					});
				}
			}
		},500);
	});
	</script>

  <body class="animsition page-login-v3 layout-full">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
        
<!-- Page -->
<div class="page vertical-align text-center" data-animsition-in="fade-in" data-animsition-out="fade-out">>
	<div class="page-content vertical-align-middle animation-slide-top animation-duration-1">
		<div class="panel">
      		<div class="panel-body">
        		<div class="brand">
        			<h1 class="blue-600">낚시누리</h1>
          			<!-- <img class="brand-img" src="/common/img/logo_edu.png" alt="...">  -->
          			<h2 class="brand-text font-size-14 mt-30 mb-10">시스템관리는 로그인이 필요한 서비스입니다.</h2>
        		</div>
        		
        		<form id="smsAuthForm" name="smsAuthForm" onsubmit="return go_step();" method="post">
					<input type="hidden" name="page_type" value="" />
					<input type="hidden" name="per_auth_type" id="per_auth_type" value="" />
					<input type="hidden" name="per_auth" id="per_auth" value="" />			
					<input type="hidden" name="name" id="name" value="" />			
					<input type="hidden" name="phoneNo" id="phoneNo" value="" />			
					<input type="hidden" name="birthDay" id="birthDay" value="" />
					<input type="hidden" name="gender" id="gender" value="" />
					<input type="hidden" name="admin_page_name" value="seadm">
					
					<c:choose>
						<c:when test="${not empty isCheck and isCheck eq true}">
							<button type="button" onclick="kcbOkCertAct()" class="btn btn-primary btn-block" title="새창열림">본인인증</button>
						</c:when>
						<c:otherwise>
							<div class="form-group">
				          		<label class="sr-only" for="MBR_ID">아이디</label>
				          		<input type="text" class="form-control" name="MBR_ID" id="MBR_ID" placeholder="아이디를 입력해주세요." autocomplete="off" required>
				        	</div>
				        	<div class="form-group">
				          		<label class="sr-only" for="MBR_PWD">비밀번호</label>
				          		<input type="password" class="form-control" name="MBR_PWD" id="MBR_PWD" placeholder="비밀번호를 입력해주세요." autocomplete="off" required>
				       		</div>
				       		<button type="submit" class="btn btn-primary btn-block">로그인</button>
				       	</c:otherwise>
				    </c:choose>   		
				</form>
				<p>계정이 없는 경우 다음을 클릭하세요. <a href="/adm/member/agree.do">신청서작성</a></p>
      		</div>
    	</div>
    	<footer class="page-copyright page-copyright-inverse">
     		<p>© 2019 낚시누리 관리자</p>
   		</footer>
	</div>
</div>
<!-- End Page -->

	<script>
		var kcbOkCert_window;
		function kcbOkCertAct() {
			kcbOkCert_window = window.open("", "auth_popup", "width=430,height=640,scrollbar=yes");
			document.reqKCBOKCERTForm.target = "auth_popup";
			document.reqKCBOKCERTForm.action = "https://safe.ok-name.co.kr/CommonSvl";
			document.reqKCBOKCERTForm.submit();
		}
		function go_step() {
			var frm = document.getElementById('smsAuthForm');
			
			var isCheck = '${isCheck}';
			if(isCheck){
				frm.action = '/adm/member/login_crtfc_act.do';
			} else {
				frm.action = '/seadm/member/login_act.do';
			}
			
			frm.submit();
			return false;
		}
	</script>
	
	<form name="reqKCBOKCERTForm" method="get" action="#">
		<input type="hidden" name="tc" value="kcb.oknm.online.safehscert.popup.cmd.P931_CertChoiceCmd" />	<!-- 변경불가-->
		<input type="hidden" name="cp_cd" value="<%=CP_CD%>">	<!-- 회원사코드 -->
		<input type="hidden" name="mdl_tkn" value="<%=MDL_TKN%>">	<!-- 토큰 -->
		<input type="hidden" name="target_id" value="">
	</form>

	<!-- Core  -->
    <script src="/common/seadm/global/vendor/babel-external-helpers/babel-external-helpers.js"></script>
    <script src="/common/seadm/global/vendor/jquery/jquery.js"></script>
    <script src="/common/seadm/global/vendor/popper-js/umd/popper.min.js"></script>
    <script src="/common/seadm/global/vendor/bootstrap/bootstrap.js"></script>
    <script src="/common/seadm/global/vendor/animsition/animsition.js"></script>
    <script src="/common/seadm/global/vendor/mousewheel/jquery.mousewheel.js"></script>
    <script src="/common/seadm/global/vendor/asscrollbar/jquery-asScrollbar.js"></script>
    <script src="/common/seadm/global/vendor/asscrollable/jquery-asScrollable.js"></script>
    <script src="/common/seadm/global/vendor/ashoverscroll/jquery-asHoverScroll.js"></script>

    <!-- Plugins -->
    <script src="/common/seadm/global/vendor/switchery/switchery.js"></script>
    <script src="/common/seadm/global/vendor/intro-js/intro.js"></script>
    <script src="/common/seadm/global/vendor/screenfull/screenfull.js"></script>
    <script src="/common/seadm/global/vendor/slidepanel/jquery-slidePanel.js"></script>
    <script src="/common/seadm/global/vendor/alertify/alertify.js"></script>
    <script src="/common/seadm/global/vendor/toastr/toastr.min.js"></script>
    
    <!-- Scripts -->
    <script src="/common/seadm/global/js/Component.js"></script>
    <script src="/common/seadm/global/js/Plugin.js"></script>
    <script src="/common/seadm/global/js/Base.js"></script>
    <script src="/common/seadm/global/js/Config.js"></script>
    
    <script src="/common/seadm/assets/js/Section/Menubar.js"></script>
    <script src="/common/seadm/assets/js/Section/GridMenu.js"></script>
    <script src="/common/seadm/assets/js/Section/Sidebar.js"></script>
    <script src="/common/seadm/assets/js/Section/PageAside.js"></script>
    <script src="/common/seadm/assets/js/Plugin/menu.js"></script>
    
    <!-- Config -->
    <script src="/common/seadm/global/js/config/colors.js"></script>
    <script src="/common/seadm/assets/js/config/tour.js"></script>
    <script>Config.set('assets', '/common/seadm/assets');</script>
 
 	<!-- Page -->
 	<script src="/common/seadm/assets/js/Site.js"></script>
  	<script src="/common/seadm/global/js/Plugin/asscrollable.js"></script>
    <script src="/common/seadm/global/js/Plugin/slidepanel.js"></script>
    <script src="/common/seadm/global/js/Plugin/switchery.js"></script>
  	  	
  	<script>
  	(function(document, window, $) {
      'use strict';

      var Site = window.Site;
      $(document).ready(function() {
        Site.run();
      });
    })(document, window, jQuery);
  	</script>
    
</body>
</html>