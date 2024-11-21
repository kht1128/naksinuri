<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"       uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<!doctype html>
<!-- 20240829 -->
<html lang="ko">
<head>
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-115620856-2"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag(){dataLayer.push(arguments);}
		gtag('js', new Date());
		gtag('config', 'UA-115620856-2');
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>낚시누리</title>
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/base.css?ver=20221212"/>
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/intro.css?ver=20221110" />
	
	<script src="/common/js/tail.js"></script>
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="https://use.fontawesome.com/e59ba62350.js"></script> 
	<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
	<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
	<script src="/naksinuri_original/common_main/js/common.js"></script>
	<script type="text/javascript" src="/naksinuri_original/js/jquery.slider2.js"></script>
</head>
<body>
<jsp:include page="/alert/popup.do" />
<c:set var="isMobileDevice" value="${isMobileDevice}"/>
<%--    <c:choose>
      <c:when test="${isMobileDevice eq 'N'}">
         <!-- 웹 -->
      </c:when>
      <c:otherwise>
         <!-- 모바일 -->
      </c:otherwise>
   </c:choose>  --%>
	<div class="wrapper">
		<div class="cs_box">
			<h2>교육문의 1833-7139</h2>
			<div class="time_info m_none">
				<span>평일 오전 09 : 00 ~ 11 : 30</span><br>
				<span>평일 오후 13 : 00 ~ 17 : 30</span><br>
				주말 및 공휴일 휴무
			</div>
		</div>
		
		<input type="hidden" id="MBR_ID" value="${MBR_ID}"/>   
		<div class="container">
			<section class="intro">
				<div class="intro_text">
					<a class="logo" href=""><img src="/naksinuri_original/common_main/img/intro/intro_logo.png" alt="낚시누리"></a>
					<p class="m_none">낚시정보 종합 포털 낚시누리에 오신 걸 환영합니다.</p>
				</div>
				
				<div class="intro_main">
					<div class="row">
						<div class="col-md-6">
							<div class="intro_con con_l">
								<a class="con_box first" href="/index.do" title="낚시 정보 및 소식 바로가기">
									<div class="bg" ></div>
									<img src="/naksinuri_original/common_main/img/intro/intro_ico3.png" alt="">
									<div class="text-box">
										<h4>낚시 정보 및 소식</h4>
										<span>
											낚시 관련 정보 및 소식을<br>
											얻을 수 있습니다.
										</span>
										<!-- <p class="go m_none" >바로가기</p> -->
										<p class="m_none" >바로가기</p>
									</div>
							      	<a class="con_box" href="/sosig/notice/list.do" title="공지사항 바로가기"><div class="bg"></div>공지사항</a>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="intro_con con_r">
								<a class="con_box first" href="/educenter/index.do" title="낚시전문교육 바로가기">
									<div class="bg"></div>
									<img src="/naksinuri_original/common_main/img/intro/intro_ico4.png" alt="">
									<div class="text-box">
										<h4>낚시전문교육</h4>
										<span>
											낚시 관련 교육을<br>
											수강 할 수 있습니다.
										</span>
										<!-- <p class="go m_none">바로가기</p> -->
										<p class="m_none">바로가기</p>
									</div>
									
								</a>
								<a class="con_box" href="/educenter/trnngoffline/fshhpList.do" title="낚시어선 신규 재개자 전문교육 바로가기"><div class="bg"></div>낚시어선 신규 · 재개자 전문교육</a>
								<div class="last_con">
									<a class="con_box"  href="javascript:void(0);" title="이수증 출력 바로가기" onclick="chkLogin(0)"><div class="bg"></div>이수증 출력</a>
									<a class="con_box" href="javascript:void(0);" title="온라인교육 신청 바로가기" onclick="chkLogin(1)"><div class="bg"></div>온라인교육 신청</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>
</html>

<!-- Modal -->
<div class="modal fade chart-pop chart01" tabindex="0" id="allPublicModal" role="dialog"></div>

<div class="modal fade" id="allPublicModalMessage" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-message" style="font-weight:bold;color:#ff0000;text-align:center;">메세지</div>
				<!-- 
				<div class="float-right">
					<button type="button" class="btn btn-default btn-outline" data-dismiss="modal">확인</button>
				</div>
				 -->
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="allPublicModalMessageLock" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">알림</h4>
			</div>
			<div class="modal-body">
				<div class="modal-message" style="font-weight:bold;color:#ff0000;text-align:center;">메세지</div>
			</div>
		</div>
	</div>
</div>
<!-- End Modal -->

<%-- 공통 로그인 팝업 --%>
<script>
var isMobileDevice = '${isMobileDevice}';
function chkLogin(type){
	var chkLoginId = '${LoginVO.MBR_ID}';
	if(chkLoginId != null && chkLoginId != ""){
		if(type=="0"){
			location.href = "/educenter/mbrhstry/listCrtf.do";
		} else {
			location.href = "/educenter/trnng/list.do";
		}
	} else {
		cmmnLoginPopup(type);
	}
}


function cmmnLoginPopup(type) {
	$.ajax({
		type:"POST",
		url :"/educenter/member/loginPopup.do",
  		data:{
  			loginPopupRequestType:type
		},	  
		dataType: "html",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			$("#allPublicModalMessage .modal-body").html(data);
			$("#allPublicModalMessage").modal({
				backdrop: 'static',
			    keyboard: false
			});
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
		},
		complete : function() {
			//console.log('complete!');
			clickRequestLockStop();

	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			clickRequestLockStop();
		}
	});

}
</script>
<%-- //End of 공통 로그인 팝업 --%>	
