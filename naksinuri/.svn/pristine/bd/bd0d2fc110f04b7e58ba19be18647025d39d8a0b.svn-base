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
    
    <title>낚시누리 관리자페이지</title>
	
    <link rel="apple-touch-icon" href="/common/seadm/assets/images/apple-touch-icon.png">
    <link rel="shortcut icon" href="/common/seadm/assets/images/favicon.ico">
    
    <!-- Stylesheets -->
    <!-- <link rel="stylesheet" href="/common/seadm/global/css/bootstrap.css">  -->
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
	<link rel="stylesheet" href="/common/seadm/global/vendor/webui-popover/webui-popover.min.css">
  	<link rel="stylesheet" href="/common/seadm/global/vendor/toolbar/toolbar.min.css">
	
	<!-- Plugins For This Page -->
	<link rel="stylesheet" href="/common/seadm/global/vendor/chartist/chartist.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/jvectormap/jquery-jvectormap.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/chartist-plugin-tooltip/chartist-plugin-tooltip.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/formvalidation/formValidation.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/ladda/ladda.min.css">	
	<link rel="stylesheet" href="/common/seadm/global/vendor/select2/select2.min.css">
	
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-bs4/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-fixedheader-bs4/dataTables.fixedheader.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-fixedcolumns-bs4/dataTables.fixedcolumns.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-rowgroup-bs4/dataTables.rowgroup.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-scroller-bs4/dataTables.scroller.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-select-bs4/dataTables.select.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-responsive-bs4/dataTables.responsive.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-buttons-bs4/dataTables.buttons.bootstrap4.min.css">
	
	<link rel="stylesheet" href="/common/seadm/global/vendor/alertify/alertify.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-tokenfield/bootstrap-tokenfield.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-select/bootstrap-select.min.css">
	
	<link rel="stylesheet" href="/common/seadm/global/vendor/magnific-popup/magnific-popup.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/c3/c3.min.css">
	
	
	<!-- Page -->
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/structure/ribbon.min.css">
    <link rel="stylesheet" href="/common/seadm/global/vendor/footable/footable.core.css" >
    <link rel="stylesheet" href="/common/seadm/assets/examples/css/forms/validation.min.css">
    <link rel="stylesheet" href="/common/seadm/assets/examples/css/layouts/panel-transition.min.css">
    <link rel="stylesheet" href="/common/seadm/assets/examples/css/uikit/buttons.min.css">
    
    <link rel="stylesheet" href="/common/seadm/assets/examples/css/tables/datatable.min.css">
    <link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/alertify.min.css">
    
    <link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/lightbox.min.css">
    
        
    <!-- Fonts -->
    <link rel="stylesheet" href="/common/seadm/global/fonts/font-awesome/font-awesome.css">
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
    
    <link rel="stylesheet" href="/common/seadm/custom/seadm.css">
    
</head>
<body class="animsition" >
  
    <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

	<nav class="site-navbar navbar navbar-default navbar-fixed-top navbar-mega navbar-inverse" role="navigation">
		
		<div class="navbar-header" >
			<div class="navbar-brand navbar-brand-center site-gridmenu-toggle" style="width:100%; text-align: center; ">
          		<%--<img class="navbar-brand-logo" src="/common/img/logo.png" title="sealife" style="background-color:#fff;">--%>
          		<span class="navbar-brand-text hidden-xs-down"> 낚시누리</span>
        	</div>
        	<button type="button" class="navbar-toggler collapsed" data-target="#site-navbar-search" data-toggle="collapse">
          		<span class="sr-only">Toggle Search</span>
        		<i class="icon wb-search" aria-hidden="true"></i>
        	</button>
      	</div>
    
		<div class="navbar-container container-fluid">
        	<!-- Navbar Collapse
        		<div class="collapse navbar-collapse navbar-collapse-toolbar" id="site-navbar-collapse">
    	         Navbar Toolbar Right 
          			<ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
            			<li class="nav-item" id="toggleChat">
              				<a class="nav-link" href="javascript:go_home(); " title="go_home"  >
                				<i class="site-menu-icon fas fa-home fa-2x" aria-hidden="true" style="color: #0000ff; "></i>
              				</a>
            			</li>            
            			<li class="nav-item" id="toggleChat">
              				<a class="nav-link" href="javascript:admin_logout(); " title="admin_logout" >
                				<i class="site-menu-icon fas fa-power-off fa-2x" aria-hidden="true" style="color: #ff3300; "></i>
              				</a>
            			</li>            
          			</ul>
           			End Navbar Toolbar Right 
        		</div>
        		End Navbar Collapse -->
			<!-- Navbar Collapse -->
        	<div class="collapse navbar-collapse navbar-collapse-toolbar" id="site-navbar-collapse">
    	  		<!-- Navbar Toolbar -->
    	  		<ul class="nav navbar-toolbar">
    	  			<!-- 
    	  	  		<li class="nav-item hidden-float" id="toggleMenubar">
	            		<a class="nav-link" data-toggle="menubar" href="#" role="button">
	                		<i class="icon hamburger hamburger-arrow-left hided unfolded">
	                  			<span class="sr-only">Toggle menubar</span>
	                  			<span class="hamburger-bar"></span>
	                		</i>
	              		</a>
	          		</li> 
	          		<li class="nav-item hidden-sm-down" id="toggleFullscreen">
	            		<a class="nav-link icon icon-fullscreen" data-toggle="fullscreen" href="#" role="button">
	              			<span class="sr-only">Toggle fullscreen</span>
	            		</a>
	          		</li>
	          		 -->  
	          		<li class="nav-item dropdown">
		         		<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="true" role="button"><i class="icon wb-menu yellow-600" aria-hidden="true"></i> 관리자페이지 메뉴 이동</a>
		         		<div class="dropdown-menu" role="menu">
		           			<!-- <a class="dropdown-item admin-act-nav-link" href="javascript:void(0);" data-linkurl="/seadm/index.do"><b class="yellow-800">낚시누리</b> 관리자 페이지 이동</a> -->
				           	<a class="dropdown-item admin-act-nav-link" href="javascript:void(0);" data-linkurl="/eduadm/index.do"><b class="yellow-800">낚시전문교육</b> 관리자 페이지 이동</a>
				           	<a class="dropdown-item admin-act-nav-link" href="javascript:void(0);" data-linkurl="/cti/index.do"><b class="yellow-800">낚시전문교육 CTI</b> 페이지 이동</a> 
		         		</div>
		       		</li>
          		</ul>
          		<!-- Navbar Toolbar Right -->
          		<ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
          			<!--
          			<li class="nav-item"><a class="nav-link" href="javascript:go_home();"><i class="icon wb-home yellow-600" aria-hidden="true"></i> 낚시누리 홈페이지로 이동 <span class="sr-only">(current)</span> </a></li>
          			-->
           			<li class="nav-item dropdown">
              			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false"><i class="icon wb-user yellow-600" aria-hidden="true"></i> ${LoginVO.MBR_NM} 님</a>
              			<div class="dropdown-menu" role="menu">
               				<a class="dropdown-item" href="javascript:void(0)" role="menuitem" data-toggle="tooltip" data-original-title="아이디">아이디 : ${LoginVO.MBR_ID}</a>
               				<!-- 
               				<a class="dropdown-item" href="javascript:void(0)" role="menuitem" data-toggle="tooltip" data-original-title="권한레벨">Lv. ${LoginVO.MBR_LV_ID}</a>
               				<a class="dropdown-item" href="javascript:void(0)" role="menuitem" data-toggle="tooltip" data-original-title="그룹코드">Gp. ${LoginVO.MBR_GRP_ID}</a>
               				 
               				<a class="dropdown-item" href="javascript:void(0)" role="menuitem" data-toggle="tooltip" data-original-title="종합센터 관리권한">종합센터 : ${LoginVO.MBR_GRP_1_ST}</a>
               				<a class="dropdown-item" href="javascript:void(0)" role="menuitem" data-toggle="tooltip" data-original-title="교육센터 관리권한">교육센터 : ${LoginVO.MBR_GRP_2_ST}</a>
               				<a class="dropdown-item" href="javascript:void(0)" role="menuitem" data-toggle="tooltip" data-original-title="박람회 관리권한">박람회 : ${LoginVO.MBR_GRP_3_ST}</a>
               				<a class="dropdown-item" href="javascript:void(0)" role="menuitem" data-toggle="tooltip" data-original-title="CTI 관리권한">CTI : ${LoginVO.MBR_GRP_4_ST}</a>
               				-->
               				<div class="dropdown-divider"></div>
               				<a class="dropdown-item" href="javascript:admin_logout();" role="menuitem"><i class="icon wb-power red-600" aria-hidden="true"></i> 로그아웃</a>
              			</div>
            		</li>           
          		</ul>
          		<!-- End Navbar Toolbar Right -->
			</div>
        	<!-- End Navbar Collapse -->
			<!-- Site Navbar Seach -->
			<div class="collapse navbar-search-overlap" id="site-navbar-search">
          		<form role="search">
           		<div class="form-group">
             			<div class="input-search">
               			<i class="input-search-icon wb-search" aria-hidden="true"></i>
               			<input type="text" class="form-control" name="site-search" placeholder="Search...">
               			<button type="button" class="input-search-close icon wb-close" data-target="#site-navbar-search" data-toggle="collapse" aria-label="Close"></button>
             			</div>
           		</div>
          		</form>
        	</div>
			<!-- End Site Navbar Seach -->
      	</div>
	</nav>  
    
