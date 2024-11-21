<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>

<!DOCTYPE html>
<html class="" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="bootstrap admin template">
    <meta name="author" content="">
    
    <title>낚시전문교육 CTI</title>
    
    <!-- Stylesheets -->
    <link rel="stylesheet" href="/common/seadm/global/css/bootstrap.min.css">
    <link rel="stylesheet" href="/common/seadm/global/css/bootstrap-extend.min.css">
    <link rel="stylesheet" href="/common/seadm/assets/css/site.min.css">
    
    <!-- Plugins -->
	<link rel="stylesheet" href="/common/seadm/global/vendor/switchery/switchery.min.css">
    <link rel="stylesheet" href="/common/seadm/global/vendor/timepicker/jquery-timepicker.min.css">
    
	<!-- Plugins For This Page -->
	<link rel="stylesheet" href="/common/seadm/global/vendor/select2/select2.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/toastr/toastr.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/alertify/alertify.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-select/bootstrap-select.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-tokenfield/bootstrap-tokenfield.min.css">
		
	<!-- Page -->
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/toastr.min.css">
    <link rel="stylesheet" href="/common/seadm/global/vendor/footable/footable.core.min.css" >
    <link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/alertify.min.css">
    <link rel="stylesheet" href="/common/seadm/assets/examples/css/tables/basic.css">
     
    <!-- Fonts -->
	<script src="/common/seadm/global/vendor/jquery/jquery.min.js"></script>
    <script src="/common/seadm/global/vendor/jquery-ui/jquery-ui.min.js"></script>
    
    <link rel="stylesheet" href="/common/seadm/custom/cti.css">    
	    
    <!--[if lt IE 9]>
    <script src="/common/seadm/global/vendor/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    
    <!--[if lt IE 10]>
    <script src="/common/seadm/global/vendor/media-match/media.match.min.js"></script>
    <script src="/common/seadm/global/vendor/respond/respond.min.js"></script>
    <![endif]-->
    
    <!-- Scripts -->
    <script src="/common/seadm/global/vendor/breakpoints/breakpoints.min.js"></script>
    <script>
      Breakpoints();
    </script> 
    
  </head>
  
  <body class="p-0 m-0" id="body${CUSTOM_UNIQ_KEY}">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

<div class="page bg-white">
	<div class="page-content p-10">
		
		<form:form commandName="eduCenterBoardVO" id="ctiListFormZazu${CUSTOM_UNIQ_KEY}" name="ctiListFormZazu${CUSTOM_UNIQ_KEY}" method="post">
		<div class="panel">
		
			<div class="panel-title pl-0 pr-0 pt-5 pb-10">
				<span class="badge badge-pill badge-warning white">전체 ${paginationInfo.totalRecordCount} 건</span>
				
				<div class="float-right">
					<ul class="pagination pagination-sm pagination-gap">									
						<ui:pagination paginationInfo = "${paginationInfo}" type="admin_top" jsFunction="fn_egov_link_page_faq${CUSTOM_UNIQ_KEY}" />
						<form:hidden path="pageIndex" />
					</ul>	
				</div>
			</div>
				
			<div class="panel-body pl-0 pr-0 pt-0 pb-0">
				<!-- table:start -->
				<table class="table table-hover footable footable-paging footable-paging-center ">
			    	<colgroup>
			    		<col width="80px"/>
			    		<col />
			    	</colgroup>
			    	<thead>
			        <tr>
						<th class="text-middle text-center">번호</th>
						<th class="text-middle text-center">질문</th>
					</tr>
					</thead>
					<c:set var="table_cell_cnt" value="2"/>
					<c:if test="${empty list}">
		           		<tbody><tr><td colspan="${table_cell_cnt}" class="text-center table-active">등록 된 자료가 없습니다.</td></tr></tbody>  
		           	</c:if>
		           	<c:forEach var="item" varStatus="status" items="${list}">
			           	<tbody class="table-section" data-plugin="tableSection">
							<tr>
								<td class="text-middle text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td>
								<td class="text-middle text-center">${item.BD_TITLE}</td>
							</tr>
						</tbody>
						<tbody>
						 	<tr>
								<td class="text-middle text-center" colspan="2">${item.BD_CONT}</td>
							</tr>
						</tbody>
					</c:forEach>
					<%-- <tfoot>
						<tr class="footable-paging">
							<td colspan="${table_cell_cnt}">
								<div class="footable-pagination-wrapper">
									<ul class="pagination">									
										<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page_zazu${CUSTOM_UNIQ_KEY}" />
										<form:hidden path="pageIndex" />
									</ul>	 								
								</div>
							</td>
						</tr>
					</tfoot>	 --%>			  
				</table>
				<!-- table:end -->
				
			</div>
		</div>
		</form:form>
	
	</div>
</div>
	

<script>
//페이징을 위한 함수명 처리
var fn_script = '<script>																									';
fn_script += '	function fn_egov_link_page_faq${CUSTOM_UNIQ_KEY}(pageNo){													';
fn_script += '		var form = document.getElementById("ctiListFormZazu${CUSTOM_UNIQ_KEY}"); 								';
fn_script += '		form.pageIndex.value = pageNo;																			';
fn_script += '		form.action = "";																						';
fn_script += '		form.submit();																							';    	
fn_script += '	} 																											';
fn_script += ' <\/script>																									'; 
$('#body${CUSTOM_UNIQ_KEY}').append(fn_script);
//End of 페이징을 위한 함수명 처리
</script>
</script>


		<!-- Core  -->
		<script src="/common/seadm/global/vendor/babel-external-helpers/babel-external-helpers.js"></script>
		<script src="/common/seadm/global/vendor/asscrollbar/jquery-asScrollbar.min.js"></script>
	    <script src="/common/seadm/global/vendor/asscrollable/jquery-asScrollable.min.js"></script>
	    <script src="/common/seadm/global/vendor/ashoverscroll/jquery-asHoverScroll.min.js"></script>
		<script src="/common/seadm/global/vendor/popper-js/umd/popper.min.js"></script>
	    <script src="/common/seadm/global/vendor/bootstrap/bootstrap.min.js"></script>
	    
	    <!-- Plugins -->
		<script src="/common/seadm/global/vendor/switchery/switchery.min.js"></script>
		<script src="/common/seadm/global/vendor/formatter/jquery.formatter.js"></script>
		
		<!-- Plugins For This Page -->
		<script src="/common/seadm/global/vendor/webui-popover/jquery.webui-popover.min.js"></script>
		<script src="/common/seadm/global/vendor/select2/select2.full.min.js"></script>
	 	<script src="/common/seadm/global/vendor/toastr/toastr.min.js"></script>
		<script src="/common/seadm/global/vendor/timepicker/jquery.timepicker.min.js"></script>
		<script src="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
		<script src="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.kr.min.js"></script>
		<script src="/common/seadm/global/vendor/bootstrap-tokenfield/bootstrap-tokenfield.min.js"></script>
		<script src="/common/seadm/global/vendor/alertify/alertify.js"></script>
		<script src="/common/seadm/global/vendor/select2/select2.full.min.js"></script>
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
	    
	    <!-- Config -->
	    <script src="/common/seadm/global/js/config/colors.js"></script>
	    <script src="/common/seadm/assets/js/config/tour.js"></script>
	    <script>Config.set('assets', '/common/seadm/assets');</script>
	  
	 	<!-- Page -->
	 	<script src="/common/seadm/assets/js/Site.js"></script>
	 	<script src="/common/seadm/global/js/Plugin/table.js"></script>
	  	<script src="/common/seadm/global/js/Plugin/asscrollable.js"></script>
	    <script src="/common/seadm/global/js/Plugin/slidepanel.js"></script>
	 	<script src="/common/seadm/assets/examples/js/uikit/tooltip-popover.js"></script>
	 	<script src="/common/seadm/global/js/Plugin/switchery.js"></script>
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
	  	
	  	
</body>
</html>
