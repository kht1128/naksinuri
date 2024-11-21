<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info" />
<c:set var="depthName" value="info" />
<c:set var="pageName" value="fishjob" />

<%@include file="../layout/newHead.jsp"%>

<article id="fishinfoRegister" class="content respon2">
	<section class="fir_login">
		<h1>
			<em class="colorSky">낚시터 <b>정보등록</b></em>
			<span>
				해양수산부와 한국어촌어항공단가 운영하는 낚시정보종합포털 "낚시누리"에서 귀하의 사업장 정보를 무료로 홍보해 드립니다.<br />
				선상낚시, 바다낚시터, 민물낚시터, 낚시공원, 낚시카페, 낚시체험 등
			</span>
		</h1>
		<div class="fir_login_area">
			<input type="hidden" name="message" value="${message}" />
			<p class="comment">해양수산부에서 조사된 이전 정보 등록 여부 확인 및 수정<p>
			<dl class="table_style mgt30px">
				<dt>상호명</dt>
				<dd><input type="text" id="co_nm_s" name="co_nm_s" class="naksi_input w100" placeholder="예)낚시누리호. 낚시누리낚시터, 낚시누리수상좌대" title="상호명"/></dd>
			</dl>
			<dl class="table_style">
				<dt>대표자</dt>
				<dd><input type="text" id="ceo_nm_s" name="ceo_nm_s" class="naksi_input w100" placeholder="예)홍길동" title="대표자"/></dd>
			</dl>
			<div class="btnArea">
				<a href="#" class="btns submitBtn bgBlue" id="mod" >이미 등록된 정보 조회</a>
			</div>
		</div>
		<div class="fir_login_area">
			<p class="comment">이전 등록된 정보가 없으면 신규 등록 부탁드립니다.</p>
			<div class="btnArea">
				<a href="/info/boatfishing.do" class="btns submitBtn bgBlue">신규등록 바로가기</a>
			</div>
		</div>
	</section>
</article>

<div id="search_result_box" class="imsimodal_shadow">
	<div class="imsimodal">
		<p class="comment">
			등록된 정보가 없습니다.<br />
			신규 등록을 진행하시겠습니까?
		</p>
		<div class="btnArea">
			<a href="/info/boatfishing.do" class="btns submitBtn bgBlue">신규등록 바로가기</a>
			<a href="javascript:location.href='/info/fishjob/login.do';" class="btns submitBtn bgBlue mgt10px">나가기</a>
		</div>
		<a href="javascript:location.href='/info/fishjob/login.do';" class="closebtn"><img src="/naksinuri_original/common_main/img/ico_close.png" /></a>
	</div>
</div>
<form name="veiw_go" id="veiw_go" action ="<c:url value='/info/fishjob/fish_viewS.do'/>" method="post">
	<input type="hidden" id="nak_id" name="nak_id" value="" />
</form>

	<!--
	<div class="respon">
		<h1>
			<em>해양수산부와 한국어촌어항공단가 운영하는 낚시정보종합포털 "낚시누리"에서 귀하의 사업장 정보를 무료로 홍보해 드립니다.</em>
			<span>낚시터 <b>정보 등록</b></span>
			<em>선상낚시, 바다낚시터, 민물낚시터, 낚시공원, 낚시카페, 낚시체험 등</em>
		</h1>
		<section id="login" class="content">
			<div class="btnArea" style="margin-top:-0.5%;padding-bottom:20px">
				2012년 해양수산부에서 조사된 이전 정보 등록 여부 확인 및 수정
			</div>
			
			 <input type="hidden" name="message" value="${message}" />

			<div class="writeBox">
				<dl>
					<dt>상호명</dt>
					<dd><input type="text" id="co_nm_s" name="co_nm_s" class="naksi_input w100" placeholder="예)낚시누리호. 낚시누리낚시터, 낚시누리수상좌대" /></dd>
				</dl>
				<dl>
					<dt>대표자</dt>
					<dd><input type="text" id="ceo_nm_s" name="ceo_nm_s" class="naksi_input w100" placeholder="예)홍길동" /></dd>
				</dl>
			</div>
			<div class="btnArea">
				<a href="#" class="submitBtn bgBlue" id="mod" onclick="ajax_search_corp()">이미 등록된 정보 조회</a>
			</div>
		</section>
	</div>
	<div class="respon">
		<section id="login" class="content">
			<div class="btnArea" style="margin-top:-0.5%;padding-bottom:20px">
				<a href="/info/boatfishing.do" class="submitBtn bgBlue">신규등록 바로가기</a>
			</div>
		</section>
	</div>
	-->
	<!--
	<div id="search_result_box" style="display: none; width: 500px; height: 400px; ">
		<div style="border: solid 3px red; padding: 50px;">
			<div id="search_result">
			등록된 정보가 없습니다.
			<br><br>
			신규 등록을 진행하시겠습니까?
			<br><br>
			<a href="/info/boatfishing.do" class="submitBtn bgBlue">신규등록 바로가기</a>
			<br><br>
			<a href="javascript:location.href='/info/fishjob/login.do';" class="submitBtn bgBlue">나가기</a>
			
			</div>
		</div>
	</div>
	-->
	
<script type="text/javaScript" language="javascript">
/*
function fnInit() {
    var message = document.loginForm.message.value;
    if (message != "") {
        alert(message);
    }
    
    getid(document.loginForm);
}
$('#mod').click(function(){
	if(document.findForm.co_nm.value==""){
		alert("상호명을 입력하세요.");
	}else if(document.findForm.ceo_nm.value==""){
		alert("대표자명을 입력하세요.");
	}else{		
	document.findForm.submit();
	}
});
*/

$('#mod').click(function(){
	var co_nm = $('#co_nm_s').val();
	var ceo_nm =  $('#ceo_nm_s').val();
	if(!$.trim(co_nm)) { alert('상호명을 입력해 주세요.'); return false; }
	if(!$.trim(ceo_nm)) { alert('대표자를 입력해 주세요.'); return false; }
	$.ajax({
		url: "/info/fishjob/fish_search.do",
		type: "POST",
		dataType:'text',  //json, text
		data: { co_nm:co_nm, ceo_nm:ceo_nm  },
		async : true, 
		success: function(res){
			//alert(res);
			var dataobj = $.parseJSON(res);
			var is_data = dataobj.is_data;
			
			if(is_data == "N") {
				$('#search_result_box').show();
			} else {
				$('#nak_id').val(is_data);
				$('#veiw_go').submit();
			}
		}
	});
});

/* 
fnInit();
 */
</script>


<%@include file="../layout/tail.jsp"%>