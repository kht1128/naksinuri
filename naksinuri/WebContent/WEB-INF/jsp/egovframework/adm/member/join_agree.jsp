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
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/pages/register-v3.css">
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/alertify.min.css">
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/toastr.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/select2/select2.min.css">
        
    <link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-bs4/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-fixedheader-bs4/dataTables.fixedheader.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-fixedcolumns-bs4/dataTables.fixedcolumns.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-rowgroup-bs4/dataTables.rowgroup.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-scroller-bs4/dataTables.scroller.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-select-bs4/dataTables.select.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-responsive-bs4/dataTables.responsive.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-buttons-bs4/dataTables.buttons.bootstrap4.min.css">
	
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-select/bootstrap-select.min.css">
	
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
	
	<style>
	.page-register-v3 .panel {width:1050px!important;}
	.hidden{display:none!important;}
	
	.form-material.floating+.form-material.floating {margin-top: 30px;}
	
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

  <body class="animsition page-register-v3 layout-full">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

	<form:form id="agreeForm" name="agreeForm" method="post">
		<input type="hidden" name="MBR_INDVDL_INFO_ST" id="MBR_INDVDL_INFO_ST">
	</form:form>
	

	<div class="page vertical-align text-center" data-animsition-in="fade-in" data-animsition-out="fade-out">
    	<div class="page-content vertical-align-middle animation-slide-top animation-duration-1">
        	<div class="panel">
	          	<div class="panel-body">
	          		<div class="brand">
	            		<h2 class="brand-text font-weight-800">접근권한 부여를 위한 개인정보 수집·이용 동의서</h2>
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
									<td colspan="2" class="text-left">귀하는 위와 같은 개인정보 수집 및 이용에 동의를 거부할 권리가 있습니다. 필수 항목에 대한 동의 거부 시 시스템 접근이 제한되며, 선택항목 제공에 대한 동의 거부시 시스템 접근은 가능하나 원활한 업무 내용 전달에 어려움이 있을 수 있음을 알려드립니다.</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="form-group form-material floating">
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
					<div class="form-group form-material floating">
						<label class="floating-label">(선택)개인정보 수집 및 이용에</label>
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
	            	<p class="font-weight-800">『개인정보보호법』등 관련 법규에 의거하여 상기 본인은 위와 같이 개인정보 수집 및 이용에 동의함.</p>
	            	<button type="button" class="btn btn-primary btn-block btn-lg mt-30 clk_btn_act">다음단계</button>
	            	<a class="btn btn-default btn-block btn-lg mt-20" href="/adm/index.do">돌아가기</a>
				</div>
			</div>
			<footer class="page-copyright page-copyright-inverse">
	        	<p>© 2019 낚시누리 관리자</p>
	      	</footer>
		</div>
	</div>
  	<!-- End Page -->
  	

<script>
$(".clk_btn_act").click(function() {
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
	/* var agree = '';
	if(approval1=='Y' && approval2=='Y') {
		agree = '1';
	} else if(approval1=='Y' && approval2=='N') {
		agree = '2';
	} */
	var form = document.getElementById('agreeForm');
	form.action = "/adm/member/pledge_agree.do";
	form.MBR_INDVDL_INFO_ST.value = approval2;
	form.submit();
});
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
	<script src="/common/seadm/global/vendor/jquery-placeholder/jquery.placeholder.js"></script>
	<script src="/common/seadm/global/vendor/alertify/alertify.js"></script>
    <script src="/common/seadm/global/vendor/toastr/toastr.min.js"></script>
    <script src="/common/seadm/global/vendor/select2/select2.full.min.js"></script>
    
    <script src="/common/seadm/global/vendor/datatables.net/jquery.dataTables.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-fixedheader/dataTables.fixedHeader.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-fixedcolumns/dataTables.fixedColumns.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-rowgroup/dataTables.rowGroup.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-scroller/dataTables.scroller.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-responsive/dataTables.responsive.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-responsive-bs4/responsive.bootstrap4.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/dataTables.buttons.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/buttons.html5.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/buttons.flash.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/buttons.print.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/buttons.colVis.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons-bs4/buttons.bootstrap4.min.js"></script>

	<script src="/common/seadm/global/vendor/bootstrap-select/bootstrap-select.min.js"></script>

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
    
    <script src="/common/seadm/global/js/config/colors.js"></script>
    <script src="/common/seadm/assets/js/config/tour.js"></script>
    <script>Config.set('assets', '/common/seadm/assets');</script>
    
    <!-- Page -->
    <script src="/common/seadm/assets/js/Site.js"></script>
    <script src="/common/seadm/global/js/Plugin/asscrollable.js"></script>
    <script src="/common/seadm/global/js/Plugin/slidepanel.js"></script>
    <script src="/common/seadm/global/js/Plugin/switchery.js"></script>
	<script src="/common/seadm/global/js/Plugin/jquery-placeholder.js"></script>
    <script src="/common/seadm/global/js/Plugin/material.js"></script>
    
    <script src="/common/seadm/global/js/Plugin/bootstrap-select.js"></script>
  	  	
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