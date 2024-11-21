<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<% 	

	//Plugin 속도 개선을 위한 분기 처리 추가 ( 2020.07.23 )
  	boolean isChangeCustomFooter = false;
	String currentLoadPagePath2 = null;
	currentLoadPagePath2 = request.getRequestURI().toString();
	String pagePathInnerFooter = "";		
	if(currentLoadPagePath2!=null && currentLoadPagePath2.length()!=0) {
		String pageName2 = currentLoadPagePath2.substring(currentLoadPagePath2.lastIndexOf("/") + 1, currentLoadPagePath2.length()).replace(".jsp", "");
		String pagePathName2 = currentLoadPagePath2.substring(0, currentLoadPagePath2.lastIndexOf("/"));
		String pagePathServerReal2 = request.getSession().getServletContext().getRealPath("/");
		pagePathInnerFooter = pagePathName2 + "/footer/" + pageName2 + ".jsp";
		if(new File(pagePathServerReal2+pagePathInnerFooter).exists()) {
			System.out.println(":: exist inner footer ::");
			isChangeCustomFooter = true;
		} else {
			System.out.println(":: not exist inner footer ::");
			isChangeCustomFooter = false;
		}
	}
	//End of Plugin 속도 개선을 위한 분기 처리 추가 
%>

	<!-- Modal -->
	<div class="modal fade" id="eduAdmEduPublicModal" aria-hidden="false" aria-labelledby="seaAdmEduPublicModal" role="dialog">
	<div class="modal-dialog modal-simple">
	</div>
	</div>
	<!-- End Modal -->
	
	<!-- Modal -->
	<div class="modal fade" id="admPublicModal" aria-hidden="false" aria-labelledby="admPublicModal" role="dialog">
	<div class="modal-dialog modal-simple">
	</div>
	</div>
	<!-- End Modal -->
	<!-- Modal -->
	<div class="modal fade" id="admPublicMultiModal" aria-hidden="false" aria-labelledby="admPublicMultiModal" role="dialog">
	<div class="modal-dialog modal-simple">
	</div>
	</div>
	<!-- End Modal -->
	<!-- Panel -->
	<div id="admPublicPanelLayerBody">
	</div>
	<!-- End Panel -->
	<!-- Panel -->
	<div id="admPublicPanelLayer" class="admPublicPanelLayer hidden">
	</div>
	<!-- End Panel -->
		

   <!-- Footer -->
    <footer class="site-footer">
      <div class="site-footer-legal">© 2019 낚시전문교육 CTI</div>
      <div class="site-footer-right">
		<%--
        Crafted with <i class="red-600 wb wb-heart"></i> by <a href="https://themeforest.net/user/creation-studio">Creation Studio</a>
		--%>
      </div>
    </footer>
    
    <% if(isChangeCustomFooter) { %>
	    <c:import url="<%=pagePathInnerFooter%>"/>
	<% } else { %>
        
	    <!-- Core  -->
	    <script src="/common/seadm/global/vendor/babel-external-helpers/babel-external-helpers.js"></script>
	    <!-- <script src="/common/seadm/global/vendor/jquery/jquery.js"></script> drag 사용시 주석 .. ? -->
	    <script src="/common/seadm/global/vendor/popper-js/umd/popper.min.js"></script>
	    <script src="/common/seadm/global/vendor/bootstrap/bootstrap.min.js"></script>
	    <script src="/common/seadm/global/vendor/animsition/animsition.min.js"></script>
	    <script src="/common/seadm/global/vendor/mousewheel/jquery.mousewheel.js"></script>
	    <script src="/common/seadm/global/vendor/asscrollbar/jquery-asScrollbar.min.js"></script>
	    <script src="/common/seadm/global/vendor/asscrollable/jquery-asScrollable.min.js"></script>
	    <script src="/common/seadm/global/vendor/ashoverscroll/jquery-asHoverScroll.min.js"></script>
	
	    <!-- Plugins -->
	    <script src="/common/seadm/global/vendor/switchery/switchery.min.js"></script>
	    <script src="/common/seadm/global/vendor/intro-js/intro.js"></script>
	    <script src="/common/seadm/global/vendor/screenfull/screenfull.js"></script>
	    <script src="/common/seadm/global/vendor/slidepanel/jquery-slidePanel.js"></script>
		<script src="/common/seadm/global/vendor/skycons/skycons.js"></script>
		<script src="/common/seadm/global/vendor/chartist/chartist.min.js"></script>
		<script src="/common/seadm/global/vendor/chartist-plugin-tooltip/chartist-plugin-tooltip.js"></script>
		<script src="/common/seadm/global/vendor/aspieprogress/jquery-asPieProgress.min.js"></script>
		<script src="/common/seadm/global/vendor/jvectormap/jquery-jvectormap.min.js"></script>
		<script src="/common/seadm/global/vendor/jvectormap/maps/jquery-jvectormap-au-mill-en.js"></script>
		<script src="/common/seadm/global/vendor/matchheight/jquery.matchHeight-min.js"></script>
		<script src="/common/seadm/global/vendor/formatter/jquery.formatter.js"></script>
		
		<!-- Plugins For This Page -->
	  	<script src="/common/seadm/global/vendor/formvalidation/formValidation.min.js"></script>
	  	<script src="/common/seadm/global/vendor/formvalidation/framework/bootstrap4.min.js"></script>
	  	<script src="/common/seadm/global/vendor/webui-popover/jquery.webui-popover.min.js"></script>
	  	<script src="/common/seadm/global/vendor/toolbar/jquery.toolbar.js"></script>
		<script src="/common/seadm/global/vendor/ladda/spin.min.js"></script>
	  	<script src="/common/seadm/global/vendor/ladda/ladda.min.js"></script>  	
	  	<script src="/common/seadm/global/vendor/select2/select2.full.min.js"></script>
	  	 
		<script src="/common/seadm/global/vendor/datatables.net/jquery.dataTables.js"></script>
		<!--
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
	 -->
		<script src="/common/seadm/global/vendor/alertify/alertify.js"></script>
		<script src="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
		<script src="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.kr.min.js"></script>
		<script src="/common/seadm/global/vendor/bootstrap-tokenfield/bootstrap-tokenfield.min.js"></script>
		<script src="/common/seadm/global/vendor/bootstrap-select/bootstrap-select.min.js"></script>
	
	 	<script src="/common/seadm/global/vendor/asprogress/jquery-asProgress.min.js"></script>
	 	<script src="/common/seadm/global/vendor/timepicker/jquery.timepicker.min.js"></script>
	 	
	 	<script src="/common/seadm/global/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>
	 	<script src="/common/seadm/global/vendor/d3/d3.min.js"></script>
	 	<script src="/common/seadm/global/vendor/c3/c3.min.js"></script>
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
		<script src="/common/seadm/global/js/Plugin/matchheight.js"></script>
		<script src="/common/seadm/global/js/Plugin/jvectormap.js"></script>
		<script src="/common/seadm/assets/examples/js/forms/validation.js"></script>
		<script src="/common/seadm/global/js/Plugin/animate-list.js"></script>
		<script src="/common/seadm/global/js/Plugin/panel.js"></script>
	  	<!-- <script src="/common/seadm/assets/examples/js/layouts/panel-transition.js"></script> -->
	  	<script src="/common/seadm/global/js/Plugin/webui-popover.js"></script>
	  	<script src="/common/seadm/global/js/Plugin/toolbar.js"></script>
	  	<script src="/common/seadm/assets/examples/js/uikit/tooltip-popover.js"></script>
	  	<script src="/common/seadm/global/js/Plugin/loading-button.js"></script>
	  	<script src="/common/seadm/global/js/Plugin/more-button.js"></script>
	  	<script src="/common/seadm/global/js/Plugin/ladda.js"></script>  	
	  	<!-- <script src="/common/seadm/global/js/Plugin/select2.js"></script> ajax 에서 사용시에는 불가능. -->
	  	<script src="/common/seadm/global/js/Plugin/input-group-file.js"></script>
	  	
	  	<script src="/common/seadm/global/js/Plugin/datatables.js"></script>
	  	<script src="/common/seadm/assets/examples/js/tables/datatable.js"></script>
	  	
	  	<!-- <script src="/common/seadm/global/js/Plugin/alertify.js"></script> -->
	  	<!-- <script src="/common/seadm/global/js/Plugin/bootstrap-select.js"></script> -->
	  	
	  	<!-- <script src="/common/seadm/global/js/Plugin/asprogress.js"></script>
	  	<script src="/common/seadm/global/js/Plugin/jt-timepicker.js"></script>
	  	
	  	<script src="/common/seadm/global/js/Plugin/formatter.js"></script>
	  	<script src="/common/seadm/global/js/Plugin/toastr.js"></script> -->
	  	  	
	  	<script>
	  	(function(document, window, $) {
	      'use strict';
	
	      var Site = window.Site;
	      $(document).ready(function() {
	        Site.run();
	      });
	    })(document, window, jQuery);
	  	</script>
  	
  	<% } %>
	 
        
    <script>
    $('.admin-act-nav-link').click(function() {
    	location.href=$(this).attr('data-linkurl');
    });
    function go_home() {
    	if(confirm('해당 사이트로 이동하시겠습니까?')) {
    		location.href='/educenter/index.do';
    	}
    }
    function ready() {
    	alert('페이지를 준비중입니다.');
    	return false;
    }
    function admin_logout() {
    	if(confirm('관리자페이지에서 로그아웃 하시겠습니까?')) {
    		location.href='/cti/member/actionLogout.do';
    	}
    }
    function getRandomHpIdkey() {
    	var jbRandom = Math.random();
    	var d = new Date();
        var currentDate = "TMP" + d.getSeconds() + "" + d.getFullYear() + "" + ( d.getMonth() + 1 ) + "" + d.getDate();
        var currentTime = d.getHours();
    	return currentDate+Math.floor(jbRandom*10)+currentTime+Math.floor(jbRandom*10);
    }
    function getAdmPublicPanelLayerIdkey() {
    	var jbRandom = Math.random();
    	var d = new Date();
        var currentDate = d.getFullYear() + "" + ( d.getMonth() + 1 ) + "" + d.getDate();
        var currentTime = d.getHours() + "" + d.getMinutes() + "" + d.getSeconds();
    	return currentDate+Math.floor(jbRandom*10)+currentTime+Math.floor(jbRandom*10);
    }
    function newAdmPublicPanelLayer(idkey,htmldata) {
    	if($("#admPublicPanelLayer"+idkey).length == 0) {
    		$("#admPublicPanelLayerBody").append('<div id="admPublicPanelLayer'+idkey+'" class="admPublicPanelLayer hidden" data-idkey="'+idkey+'"></div>');
    	}    	
    	$("#admPublicPanelLayer"+idkey).html(htmldata);
		$("#admPublicPanelLayer"+idkey).fadeIn('200',function(){
			//
		});
    }
    function removeAdmPublicPanelLayer(idkey) {
    	$("#admPublicPanelLayer"+idkey).fadeOut('200',function(){
    		$(this).remove();
    	})
    }
    function removeAllAdmPublicPanelLayer() {
    	$("#admPublicPanelLayerBody").html('');
    }
    function ajaxLoadingHtmlTag() {
    	return '<div class="panel-body p-10 text-center font-size-12"><div class="loader vertical-align-middle loader-ellipsis"></div></div>';
    }
    function addAllFormDataByFormId(frmdata,frm) {
		var x = frm.serializeArray();
		$.each(x, function(i, field){
			frmdata.append(field.name,field.value);
			//console.log(field.name+":"+field.value);
		});
    	return frmdata;
    }
    </script>

    <script src="/common/js/tail_adm.js?time=36000"></script>
    <script src="/common/js/tail.js?v=202007241443"></script>
    
</body>
</html>