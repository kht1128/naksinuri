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
	.page-register-v3 .panel {width:1000px!important;}
	.hidden{display:none!important;}
	
	.text-underline{text-decoration:underline;}
	.font-weight-bold{font-weight:bold !important}
	.font-size-18{font-size:18px !important}
	.line-height-22{line-height: 22px; !important}
	.mgt20px {margin-top: 20px;}
		
	.floats{zoom:1}
	.floats:after{content:'';display:block;clear:both}
	.floats > li{float:left}
	.floats > li.fr{float:right}
	
	.form-material.floating {margin: 30px 0;padding: 0 60px;}
	
	.form-material .floating-label {left: 65px;}
	</style>
    
  </head>

  <body class="animsition page-register-v3 layout-full">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

	<form id="agreeForm" name="agreeForm" method="post">
		<input type="hidden" id="MBR_INDVDL_INFO_ST" name="MBR_INDVDL_INFO_ST" value="${MBR_INDVDL_INFO_ST }">
		<input type="hidden" id="MBR_PLEDGE_ST" name="MBR_PLEDGE_ST">
	</form>
	

	<div class="page vertical-align text-center" data-animsition-in="fade-in" data-animsition-out="fade-out">
    	<div class="page-content vertical-align-middle animation-slide-top animation-duration-1">
        	<div class="panel">
	          	<div class="panel-body">
	          		<div class="brand">
	            		<h2 class="brand-text font-weight-800">개인정보 취급자 개인정보 보호 서약서</h2>
						<p class="font-weight-800 mgt20px">본인은 <span class="text-underline">낚시전문교육 교육대상자 명단</span>의 개인정보취급자로서 개인정보 보호를 위하여 다음사항을 준수할 것을 엄숙히 서약합니다.</p>
	          		</div>
					<div class="form-group form-material floating">
		              	<p class="text-left">1. 업무상 알게 된 개인정보를 허가 없이 제3자에게 제공하거나 수집 목적 외로 이용하지 않는다.</p>
		              	<p class="text-left">2. 개인정보 관련 업무 중 알게 된 개인정보와 관련된 내용이 직무상 보호할 대상임을 인정한다.</p>
		              	<p class="text-left">3. 개인정보를 누설함이 정보주체의 권리와 이익에 위해가 될 수 있음을 인식하여 업무수행 중 지득한 개인정보를 일체 누설하거나 공개하지 아니한다.</p>
		              	<p class="text-left">4. 본인에게 할당된 사용자 ID, 패스워드, 개인정보 처리시스템을 타인과 공동 사용하거나 관련정보를 누설하지 아니한다.</p>
		              	<p class="text-left">5. 명백히 허가 받지 않은 정보에 접근하지 않는다.</p>
		              	<p class="text-left">6. 업무와 관련한 개인정보의 수집, 생성, 기록, 저장, 보유, 가공, 편집, 검색, 출력, 정정, 복구, 이용, 제공, 공개, 파기 및 그 밖에 이와 유사한 일체의 행위에 대하여 공단의 규정과 통제절차를 준수한다.</p>
		              	<p class="text-left">7. 인터넷 홈페이지, P2P, 공유설정, 공개된 무선망 등을 통해 개인정보가 노출되거나 유출되지 않도록 PC, 모바일 기기 등에 보호조치를 취한다.</p>
		              	<p class="text-left">8. 모바일 기기를 통해 개인정보를 처리하는 경우 분실·도난 등으로 개인정보가 유출되지 않도록 해당 모바일 기기에 비밀번호 설정 등의 보호조치를 취한다.</p>
		              	<p class="text-left">9. 전보 및 퇴직으로 업무를 담당하지 않게 될 경우 즉시 공단에 알려 권한에 대한 삭제 요청을 할 것이며, 전보 및 퇴직 후에도 업무상 알게 된 모든 개인정보에 대하여는 일체 누설하지 아니한다.</p>
					</div>
					
					<p class="">상기 사항을 숙지하고 이를 성실히 준수할 것을 동의하며 서약서의 보안사항을 위반하였을 경우에는<br>
					<span class="font-weight-800"> “개인정보 보호법”, “정보통신망이용촉진 및 정보보호 등에 관한 법률”</span> 등 관련법령에 의한 민/형사상의 책임 이외에도,<br> 
					 공단 관련 규정에 따라 어떠한 불이익도 감수할 것이며 공단에 끼친 손해에 대해 지체 없이 변상/복구할 것을 서약합니다.</p>
					
					<div class="form-group form-material floating">
						<label class="floating-label">(필수) 개인정보 취급자 개인정보 보호 서약서에</label>
						<div class="col-lg-12 text-right">
							<div class="radio-custom radio-default radio-inline ">
								<input type="radio" class="clk-sel-position" id="approval1Y" name="pledge" value="Y" required>
			  					<label for="approval1Y">동의함</label>
							</div>
							<div class="radio-custom radio-default radio-inline ">
								<input type="radio" class="clk-sel-position" id="approval1N" name="pledge" value="N" >
			  					<label for="approval1N">동의하지않음</label>
							</div>
						</div>
					</div>
					
	            	<!-- <p class="font-weight-800">『개인정보보호법』등 관련 법규에 의거하여 상기 본인은 위와 같이 개인정보 수집 및 이용에 동의함.</p> -->
	            	<button type="button" class="btn btn-primary btn-block btn-lg mt-30 clk_btn_act">다음단계</button>
	            	<a class="btn btn-default btn-block btn-lg mt-20" href="javascript:history.back()">돌아가기</a>
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
	var pledge = $(':radio[name="pledge"]:checked').val();
	if(!pledge || pledge == 'N'){
		alert('(필수)개인정보 취급자 개인정보 보호 서약서에 동의해야 합니다.');
		return;
	} 
	
	var form = document.getElementById('agreeForm');
	form.action = "/adm/member/join.do";
	form.MBR_PLEDGE_ST.value = pledge;
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