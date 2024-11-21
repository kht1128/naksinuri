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
    <meta name="description" c
    
    
    ontent="bootstrap admin template">
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

	<style>
	.page-login-v3 .panel{width:800px!important;}
	
	/** 테이블용 */	
	ol,ul,li,dl{list-style:none;}
	table{width:100%;border-spacing:0;border-collapse:collapse;table-layout:fixed;}
	
	.basic_tbl{background-color:#fff}
	.basic_tbl th,
	.basic_tbl td{padding:10px;border:1px solid #e0e0e0;}
	.basic_tbl thead th{background-color:#666;color:#fff}
	
	.text-underline{text-decoration:underline;}
	.font-weight-bold{font-weight:bold !important}
	.font-size-18{font-size:18px !important}
	.line-height-22{line-height: 22px; !important}
	.table-cell-blind {
		position: absolute;
	    left: -10000px;
	    top: auto;
	    width: 1px;
	    height: 1px;
	    overflow: hidden;
	}
	
	.floats{zoom:1}
	.floats:after{content:'';display:block;clear:both}
	.floats > li{float:left}
	.floats > li.fr{float:right}
	</style>
      
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
      			<% 
        			session.setAttribute("isChangeInfo", true);
      				session.setAttribute("MBR_SN", (String)request.getAttribute("MBR_SN"));
        		%>	
        		<div class="brand">
        			<h1 class="blue-600">본인인증을 위한 개인정보 변경 요청</h1>
          			<!-- <img class="brand-img" src="/common/img/logo_edu.png" alt="...">  -->
          			<h2 class="brand-text font-size-18 mt-30 mb-10">${info.MBR_ID} 계정의 정보를 변경합니다.</h2>
        		</div>
        		<p class="mt-20 red-600">휴대폰 본인인증을 위한 이름,생년월일,연락처(휴대폰번호) 정보로 변경을 부탁드립니다.<br>최초 1회만 변경이 가능하며 잘못 입력하신 경우 추후 비밀번호변경 및 본인인증을 통한<br>로그인이 불가능 할 수 있습니다.</p>
        		<form method="post" action="" id="modal_action_form" name="modal_action_form" autocomplete="off">
        			<input type="hidden" name="changeInfo" value=""/>
        			<input type="hidden" name="agree" value=""/>
        			<div class="form-group row">
		          		<label class="form-control-label" for="MBR_NM">이름 <span class="red-600">*</span></label>
		          		<input type="text" class="form-control border border-primary" name="MBR_NM" placeholder="이름을 입력해주세요." autocomplete="off" value="" required>
		          		<span class="text-help cyan-600 font-size-12 text-left">* 본인인증이 가능한 이름을 입력해주세요.</span>
		        	</div>
		        	<div class="form-group row">
		          		<label class="form-control-label" for="MBR_BIRTH">생년월일 <span class="red-600">*</span></label>
		          		<input type="text" class="form-control border border-primary mbr-birth-input-pattern" data-pattern-cnt="0" name="MBR_BIRTH" placeholder="생년월일을 입력해주세요." autocomplete="off" value="" required >
		          		<span class="text-help cyan-600 font-size-12 text-left">* 본인인증이 가능한 생년월일을 입력해주세요.</span>
		        	</div>
		        	<div class="form-group row">
		          		<label class="form-control-label" for="MBR_HP">연락처(휴대폰번호) <span class="red-600">*</span></label>
		          		<input type="text" class="form-control border border-primary mbr-hp-input-pattern" data-pattern-cnt="0" name="MBR_HP" placeholder="연락처를 입력해주세요." autocomplete="off" value="" required >
		          		<span class="text-help cyan-600 font-size-12 text-left">* 본인인증이 가능한 연락처를 입력해주세요.</span>
		        	</div>
		        	<div class="form-group row">
		          		<label class="form-control-label" for="MBR_PSITN_NM">소속(부서) <span class="red-600">*</span></label>
		          		<input type="text" class="form-control border border-primary" name="MBR_PSITN_NM" placeholder="예)해양수산과" autocomplete="off" value="${info.MBR_PSITN_NM}" required >
		        	</div>
		        	<div class="form-group row">
		          		<label class="form-control-label" for="MBR_NCNM">담당자 호칭 </label>
		          		<input type="text" class="form-control border border-primary" name="MBR_NCNM" placeholder="예)경상남도 사천시" autocomplete="off" value="${info.MBR_NCNM}"  >
		        	</div>		
		        	<div class="form-group row">
		          		<label class="form-control-label" for="MBR_TEL">연락처(일반) <span class="red-600">*</span></label>
		          		<input type="text" class="form-control border border-primary mbr-tel-input-pattern" data-pattern-cnt="0" name="MBR_TEL" placeholder="내선번호 또는 일반전화번호를 입력해주세요." autocomplete="off" value="${info.MBR_TEL}" required >
		        	</div>	
		        	<div class="form-group row">
		          		<label class="form-control-label" for="MBR_EMAIL">이메일</label>
		          		<input type="text" class="form-control border border-primary" name="MBR_EMAIL" placeholder="이메일을 입력해주세요." autocomplete="off" value="${info.MBR_EMAIL}" >
		        	</div>
		        	<div class="form-group row">
		          		<label class="form-control-label" for="MBR_DSCRP">메모(전달사항)</label>
		          		<textarea class="form-control h-100" name="MBR_DSCRP" placeholder="" row="5" >${item.MBR_DSCRP}</textarea>
		        	</div>
			       	<div class="form-group form-material floating">
						<p class="text-left">한국어촌어항공단이 해양수산부로부터 위탁받아 운영 중인 낚시전문교육(www.naksiedu.kr)은 시스템 관리 권한 부여를 위한 개인정보 수집·이용을 위하여 『개인정보보호법 제15조 및 제22조』에 따라 귀하의 동의를 받고자 합니다.</p>
		              	<table class="basic_tbl mt-10">
							<caption class="table-cell-blind">개인정보 수집·이용</caption>
							<colgroup>
								<col />
								<col width="80px"/>
								<col />
							</colgroup>
							<thead>
								<tr class="table-cell-blind"><th></th></tr>
							</thead>
							<tbody>
								<tr>
									<td class="text-left">개인정보의 수집 및 이용 목적</td>
									<td colspan="2" class="text-left text-underline font-weight-bold ">(필수)관리 권한 부여, (선택)원활한 업무 전달</td>
								</tr>
								<tr>
									<td class="text-left" rowspan="2">수집하는 개인정보 항목</td>
									<td class="">필수</td>
									<td class="text-left text-underline  font-weight-bold " >성명, 생년월일, 휴대전화번호, 소속, 전화번호</td>
								</tr>
								<tr>
									<td class="">선택</td>
									<td class="text-left text-underline  font-weight-bold ">이메일</td>
								</tr>
								<tr>
									<td class="text-left">개인정보의 보유 및 이용 기간</td>
									<td class="text-left" colspan="2"><span class="font-size-18 blue-600 text-underline font-weight-bold">보유 목적 달성시</span>(업무 담당 기간)</td>
								</tr>
								<tr>
									<td class="text-left">동의 거부 권리 및 동의 거부에 따른 불이익 내용 또는 제한사항</td>
									<td colspan="2" class="text-left">귀하는 위와 같은 개인정보 수집 및 이용에 동의를 거부할 권리가 있습니다. 필수 항목에 대한 동의 거부 시 <span class="font-size-18 blue-600 text-underline font-weight-bold">시스템 접근</span>이 제한되며, 선택항목 제공에 대한 동의 거부시 시스템 접근은 가능하나 원활한 업무 내용 전달에 어려움이 있을 수 있음을 알려드립니다.</td>
								</tr>
							</tbody>
						</table>
					</div>
			       	<div class="form-group form-material floating mt-10">
						<label class="floating-label">(필수) 개인정보 수집 및 이용에</label>
						<div class="col-lg-12 text-right">
							<div class="radio-custom radio-default radio-inline ">
								<input type="radio" class="clk-sel-position" id="approval1Y" name="approval1" value="Y" >
			  					<label for="approval1Y">동의함</label>
							</div>
							<div class="radio-custom radio-default radio-inline ">
								<input type="radio" class="clk-sel-position" id="approval1N" name="approval1" value="N" >
			  					<label for="approval1N">동의하지않음</label>
							</div>
						</div>
					</div>
					<div class="form-group form-material floating mb-40">
						<label class="floating-label">(선택) 개인정보 수집 및 이용에</label>
						<div class="col-lg-12 text-right">
							<div class="radio-custom radio-default radio-inline ">
								<input type="radio" class="clk-sel-position" id="approval2Y" name="approval2" value="Y" >
			  					<label for="approval2Y">동의함</label>
							</div>
							<div class="radio-custom radio-default radio-inline ">
								<input type="radio" class="clk-sel-position" id="approval2N" name="approval2" value="N" >
			  					<label for="approval2N">동의하지않음</label>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary btn-block">정보변경완료</button>
				</form>
				<a class="btn btn-default btn-block" href="/adm/index.do">변경하지않음</a>
      		</div>
    	</div>
    	<footer class="page-copyright page-copyright-inverse">
     		<p>© 2019 낚시누리 관리자</p>
   		</footer>
	</div>
</div>
<!-- End Page -->

<script>
$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});
$(function(){
	<%--
	//처리 로직은 tail.jsp 파일에 존재함.
	//공통 : 사용 할 대상(input)의 속성(attr)에 필히 추가 data-pattern-cnt="0"
   	//파라미터 : target 			= keyup 이벤트를 받을 input text 의 jquery selector
   	//파라미터 : default_pattern 	= 기본 패턴 형태값,자리수
   	//파라미터 : over_pattern		= 기본 패턴의 자리수 이상인 경우 다음 단계 패턴 형태 및 해당 패턴의 자리수를 기입하며 , 해당 자리수 이상인 경우 다음 단계 패턴으로 전환 
   	//파라미터 : automatch			= 화면 로딩시 해당 폼(input)에 입력값이 존재 할 경우 패턴을 자동 적용 할 것인지에 대한 설정(true:패턴 형태를 적용하여 노출)
	--%>
	formatterCustomMultiple([
	    {
			'target' : $('.mbr-birth-input-pattern'),
			'default_pattern' : ['{{9999}}-{{99}}-{{99}}',8],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.mbr-hp-input-pattern'),
			'default_pattern' : ['{{999}}-{{9999}}-{{9999}}',11],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.mbr-tel-input-pattern'),
			'default_pattern' : ['{{9999}}-{{9999}}',8],
			'over_pattern' : [['{{999}}-{{999}}-{{9999}}',10],['{{999}}-{{9999}}-{{9999}}',11]],
			'automatch' : true,
		},
	]);
});
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();

	var approval1 = $(':radio[name="approval1"]:checked').val();
	var approval2 = $(':radio[name="approval2"]:checked').val();
	var islockall = false;
	var islockapproval1 = false;
	var islockapproval2 = false;
	if ((typeof approval1 == "undefined") && (typeof approval2 == "undefined")) {
		islockall = true;
	} else if (typeof approval1 == "undefined") {
		islockapproval1 = true;
	} else {
		if(approval1 == 'N') {
			islockapproval1 = true;
		} else {
			if(typeof approval2 == "undefined") {
				islockapproval2 = true;
			}
		}
	}
	if(islockall) {
		alert('필수 및 선택 개인정보 수집·이용에 동의해야 합니다.');
		$('#approval1Y').focus();
		return false;
	}
	if(islockapproval1) {
		alert('(필수)개인정보 수집·이용 동의 여부를 확인 해주세요.');
		$('#approval1Y').focus();
		return false;
	}
	if(islockapproval2) {
		alert('(선택)개인정보 수집·이용 동의 여부를 확인 해주세요.');
		$('#approval2Y').focus();
		return false;
	}
	
	var form = document.getElementById('modal_action_form');
	if(form.MBR_BIRTH.value.trim().length < 10) {
		alertify.alert('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return false;
	}
	
	var agree = '';
	if(approval1=='Y' && approval2=='Y') {
		agree = '1';
	} else if(approval1=='Y' && approval2=='N') {
		agree = '2';
	}
		
	chkAdmOverlay(agree);
	
});
function chkAdmOverlay(agree) {
	$.ajax({
		type:"POST",
		url :"/adm/member/searchAuthOverlayInfo.do",
		data:$('#modal_action_form').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		cache: false,
		success: function(data, status, xhr) {
			if(data.error == '0') {
				var form = document.getElementById('modal_action_form');
				form.action = "/adm/member/modifyAdmInfo.do";
				form.changeInfo.value = 'ok';
				form.agree.value = agree;
				form.submit();
			} else {
				alertify.alert("입력하신 <span class=\"red-600\">이름</span>,<span class=\"red-600\">생년월일</span>,<span class=\"red-600\">연락처(휴대폰번호)</span>가 중복되는 회원정보가 존재합니다.<br>관리자에게 별도 문의를 해주시기 바랍니다.");
			}
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
			clickRequestLockStop();
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
    <script src="/common/seadm/global/vendor/formatter/jquery.formatter.js"></script>
    
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
    
    <script src="/common/seadm/global/js/Plugin/formatter.js"></script>
  	<script src="/common/seadm/global/js/Plugin/toastr.js"></script>
  	  	
  	<script>
  	(function(document, window, $) {
      'use strict';

      var Site = window.Site;
      $(document).ready(function() {
        Site.run();
      });
    })(document, window, jQuery);
  	</script>
  	
  	<script src="/common/js/tail.js"></script>
    
</body>
</html>