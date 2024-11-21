<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<!-- Modal -->
	<div class="modal fade" id="seaAdmEduPublicModal" aria-hidden="false" aria-labelledby="seaAdmEduPublicModal" role="dialog">
	<div class="modal-dialog modal-simple">
	</div>
	</div>
	<!-- End Modal -->
	
   <!-- Footer -->
    <footer class="site-footer">
      <div class="site-footer-legal">© 2018 <a href="/admin/sosig/notice/list.do">어장산업팀 관리자</a></div>
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
 	<script src="/common/seadm/global/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>
 	
 	<script src="/common/seadm/global/vendor/d3/d3.min.js"></script>
 	<script src="/common/seadm/global/vendor/c3/c3.min.js"></script>
 	  
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
  	<script src="/common/seadm/assets/examples/js/advanced/lightbox.js"></script>
	  	
  	  	
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
    		location.href='/';
    	}
    }
    function ready() {
    	alert('페이지를 준비중입니다.');
    	return false;
    }
    function admin_logout() {
    	if(confirm('관리자페이지에서 로그아웃 하시겠습니까?')) {
    		location.href='/member/actionLogout.do';
    	}
    }
    </script>
	<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
	<script>
	    // 우편번호 찾기 찾기 화면을 넣을 element
	    var element_wrap = document.getElementById('zipcode_layer');
	
	    function foldDaumPostcode() {
	        // iframe을 넣은 element를 안보이게 한다.
	        element_wrap.style.display = 'none';
	    }
	
	    function zipcode(zipcode, addr1, addr2) {
	        // 현재 scroll 위치를 저장해놓는다.
	        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var fullAddr = data.address; // 최종 주소 변수
	                var extraAddr = ''; // 조합형 주소 변수
	
	                // 기본 주소가 도로명 타입일때 조합한다.
	                if(data.addressType === 'R'){
	                    //법정동명이 있을 경우 추가한다.
	                    if(data.bname !== ''){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있을 경우 추가한다.
	                    if(data.buildingName !== ''){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById(zipcode).value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById(addr1).value = fullAddr;
	                document.getElementById(addr2).focus();
	
	                // iframe을 넣은 element를 안보이게 한다.
	                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
	                element_wrap.style.display = 'none';
	
	                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
	                document.body.scrollTop = currentScroll;
	            },
	            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
	            onresize : function(size) {
	                element_wrap.style.height = size.height+'px';
	            },
	            width : '100%',
	            height : '100%',
	            maxSuggestItems : '10'
	        }).embed(element_wrap);
	
	        // iframe을 넣은 element를 보이게 한다.
	        element_wrap.style.display = 'block';
	    }
	</script>
	
	<script src="/common/js/tail_adm.js"></script>
	
</body>
</html>