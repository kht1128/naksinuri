<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="bootstrap admin template">
    <meta name="author" content="">
    
    <title>관리자 설정</title>
    
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
					toastr.warning(message,title,{
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
      
  </head>

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
        			<h1 class="blue-600">정보변경</h1>
        		</div>
        		<% 
        			session.setAttribute("isChangePwd", true);
        			session.setAttribute("MBR_SN", (String)request.getAttribute("MBR_SN"));
        		%>	 
        		<form method="post" action="" id="modal_action_form" name="modal_action_form" autocomplete="off" onsubmit="return formSubmit();">
        			<c:if test="${info.MBR_PWD_ST eq '1'}">
        				<div class="form-group">
			          		<label class="sr-only" for="MBR_ID">아이디</label>
			          		<input type="text" class="form-control" name="MBR_ID" id="MBR_ID" placeholder="아이디를 입력해주세요." autocomplete="off" value="" required>
			          		<span class="text-help cyan-600 font-size-12 text-left">* 아이디는 영문자 또는 숫자를 조합하여 작성해주세요.</span>
			          		<input type="hidden" id="MBR_ID_" value="N" />
			        	</div>
		        	</c:if>
		        	<div class="form-group">
		          		<label class="sr-only" for="MBR_PWD">비밀번호</label>
		          		<input type="password" class="form-control" name="MBR_PWD" id="MBR_PWD" placeholder="비밀번호를 입력해주세요." autocomplete="off" required>
		          		<span class="text-help cyan-600 font-size-12 text-left">* 비밀번호는 공백없이 영문,숫자,특수문자를 혼합하여 10자리 ~ 20자리 이내로 입력해주세요.</span>
		       		</div>
		       		<div class="form-group">
		          		<label class="sr-only" for="MBR_PWD">비밀번호 확인</label>
		          		<input type="password" class="form-control" name="MBR_PWD_CHK" id="MBR_PWD_CHK" placeholder="비밀번호(확인)를 입력해주세요." autocomplete="off" required>
		       		</div>
		       		<button type="submit" class="btn btn-primary btn-block trg_btn_submit">변경완료</button>
		       	</form>
      		</div>
    	</div>
    	<footer class="page-copyright page-copyright-inverse">
     		<p>© 2019 낚시누리 관리자</p>
   		</footer>
	</div>
</div>
<!-- End Page -->

<script>
function formSubmit() {
	
	var form = document.modal_action_form;	
	
	<c:if test="${info.MBR_PWD_ST eq '1'}">
	var id = form.MBR_ID.value;
	var idReg = /^[A-Za-z0-9+]*$/;
	if(!idReg.test(id)){
		alertify.alert("아이디는 영문자 또는 숫자이어야 합니다.");
		return false;
	}
	</c:if>	
	
	var pw = form.MBR_PWD.value;
	var pw2 = form.MBR_PWD_CHK.value;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);	
	if(pw.length < 10 || pw.length > 20){
		alertify.alert("비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}
	if(pw.search(/₩s/) != -1){
		alertify.alert("비밀번호는 공백없이 입력해주세요.");
		return false;
	} 
	if(num < 0 || eng < 0 || spe < 0 ){
		alertify.alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		return false;
	}
	if(pw != pw2) { 
		alertify.alert('비밀번호와 비밀번호 확인이 다릅니다.');
		return false;
	}	
	
	if(id == '${info.MBR_ID}') {
		/*
		alertify.confirm('현재 아이디를 유지하시겠습니까?',function(){
			autoChkId();
		});
		*/
		alertify.alert('기존에 임시 발급 된 아이디는 등록할 수 없습니다.<br>규칙을 참고하여 아이디를 변경해주세요.');
		return false;
	}
	
	<c:if test="${info.MBR_PWD_ST eq '1'}">
		alertify.confirm('아이디는 최초 등록 후 변경이 불가능합니다.<br>등록 할 아이디 : <b>'+id+'</b><br>등록 하시겠습니까?',function(){
			autoChkId();
		});
	</c:if>	
	<c:if test="${info.MBR_PWD_ST ne '1'}">
		autoChkId();
	</c:if>	
	
	return false;
}
function autoChkId() {
	
	$.ajax({
		type:"POST",
		url :"/adm/member/searchId.do",
		data:$('#modal_action_form').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		cache: false,
		success: function(data, status, xhr) {
			if(data.error == '0') {
				document.modal_action_form.submit();
			} else {
				alertify.alert(data.msg);
			}
		},
		beforeSend : function() {
			//console.log('before!');
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
		}
	});
}
</script>


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