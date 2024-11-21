<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="panel panel-dark card card-dark card-shadow border border-dark " >
  	<div class="panel-heading panel-move  draggable-move cursor-grab ">
		<h3 class="panel-title">회원(교육대상자)정보보기</span>
			<div class="panel-actions pr-0">
				<a class="panel-action icon wb-close panel-close m-0 btn-panel-act-close" aria-hidden="true" data-toggle="tooltip" data-original-title="닫기"></a>
			</div>
		</h3>		
	</div>
	<div class="panel-body p-10 scroll-y h-900">
		<form class="form-horizontal p-20" method="post" autocomplete="off" action="">
		<div class="form-group row">
			<span class="help-text font-weight-800 font-size-18 red-600">해당 아이디 ${info.MBR_ID} 는 삭제 된 회원입니다.<br>삭제 된 정보의 확인은 [회원정보수정로그기록-변경데이터확인] 항목의 조회 버튼을 이용해주세요.</span>
		</div>
		</form>
  	</div>
  	<div class="panel-footer p-10">
  		<div class="float-right">
           <button type="button" class="btn btn-default btn-outline btn-panel-act-close">취소(닫기)</button>
		</div>
	</div>
</div>
<script>
$('.btn-panel-act-close').click(function(){
	$("#admPublicPanelLayer").html('');
	$("#admPublicPanelLayer").fadeOut('200');
});
</script>