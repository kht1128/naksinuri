<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<!-- Modal -->
	<div class="modal fade" id="admPublicModal" aria-hidden="false" aria-labelledby="admPublicModal" role="dialog">
	<div class="modal-dialog modal-simple">
	</div>
	</div>
	<!-- End Modal -->

   <!-- Footer -->
    <footer class="site-footer">
      <div class="site-footer-legal">© 2019 낚시누리 관리자</div>
      <div class="site-footer-right">
		<%--
        Crafted with <i class="red-600 wb wb-heart"></i> by <a href="https://themeforest.net/user/creation-studio">Creation Studio</a>
		--%>
      </div>
    </footer>
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
	<script src="/common/seadm/global/vendor/skycons/skycons.js"></script>
	<script src="/common/seadm/global/vendor/chartist/chartist.min.js"></script>
	<script src="/common/seadm/global/vendor/chartist-plugin-tooltip/chartist-plugin-tooltip.js"></script>
	<script src="/common/seadm/global/vendor/aspieprogress/jquery-asPieProgress.min.js"></script>
	<script src="/common/seadm/global/vendor/jvectormap/jquery-jvectormap.min.js"></script>
	<script src="/common/seadm/global/vendor/jvectormap/maps/jquery-jvectormap-au-mill-en.js"></script>
	<script src="/common/seadm/global/vendor/matchheight/jquery.matchHeight-min.js"></script>

	 <!-- Plugins For This Page -->
  	<script src="/common/seadm/global/vendor/formvalidation/formValidation.min.js"></script>
  	<script src="/common/seadm/global/vendor/formvalidation/framework/bootstrap4.min.js"></script>
  	<script src="/common/seadm/global/vendor/webui-popover/jquery.webui-popover.min.js"></script>
  	<script src="/common/seadm/global/vendor/toolbar/jquery.toolbar.js"></script>
	<script src="/common/seadm/global/vendor/ladda/spin.min.js"></script>
  	<script src="/common/seadm/global/vendor/ladda/ladda.min.js"></script>  	
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

	<script src="/common/seadm/global/vendor/alertify/alertify.js"></script>
	<script src="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script src="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.kr.min.js"></script>
	<script src="/common/seadm/global/vendor/bootstrap-tokenfield/bootstrap-tokenfield.min.js"></script>
	<script src="/common/seadm/global/vendor/bootstrap-select/bootstrap-select.min.js"></script>

 	<script src="/common/seadm/global/vendor/asprogress/jquery-asProgress.min.js"></script>
 
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
  	
  	<script src="/common/seadm/global/js/Plugin/alertify.js"></script>
  	<script src="/common/seadm/global/js/Plugin/bootstrap-select.js"></script>
  	
  	<script src="/common/seadm/global/js/Plugin/asprogress.js"></script>
  	  	
  	<script>
  	(function(document, window, $) {
      'use strict';

      var Site = window.Site;
      $(document).ready(function() {
        Site.run();
      });
    })(document, window, jQuery);
  	</script>
	 
        
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
    		location.href='/eduadm/member/actionLogout.do';
    	}
    }
    function admin_checkPwd() {//팝업호출    	
    	/* location.href='/eduadm/member/changePwd.do'; */
    	$.ajax({
    		type:"POST",
    		url :"/eduadm/member/change_pwd.do",
    		data:$('#mbrIdForm').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		success: function(data, status, xhr) {
    			$("#admPublicModal").html(data);
    			$("#admPublicModal").modal({
    				backdrop: 'static',
    			    keyboard: false
    			});
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    		}
    	});	
    }
    </script>
    
    <script src="/common/js/tail_adm.js"></script>
    
</body>
</html>