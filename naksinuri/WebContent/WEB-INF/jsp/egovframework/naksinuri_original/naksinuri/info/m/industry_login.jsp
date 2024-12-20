<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="info" />
<c:set var="pageName" value="industry" />

<%@include file="../../layout/m/head.jsp"%>

<article id="fishinfoRegister" class="content">
	<section class="fir_login">
		<h1>
			<em class="colorSky">낚시산업 <b>정보등록</b></em>
			<span>
				해양수산부와 한국어촌어항공단가 운영하는 낚시정보종합포털 "낚시누리"에서 귀하의 사업장 정보를 무료로 홍보해 드립니다.<br />
				낚시관련 조구업체. 협회/기관/단체, 미디어, 판매점, 출조점, 낚시정보제공업 등
			</span>
		</h1>
		<div class="fir_login_area">
			<%--<form name="findForm" id="findForm" action ="<c:url value='/info/findCorp.do'/>" method="post">--%>
			<input type="hidden" name="message" value="${message}" />
			<p class="comment">해양수산부에서 조사된 이전 정보 등록 여부 확인 및 수정<p>
			<dl class="table_style mgt30">
				<dt>상호명</dt>
				<dd><input type="text" id="san_name_s" name="san_name_s" class="naksi_input w100" placeholder="예)낚시누리" /></dd>
			</dl>
			<dl class="table_style">
				<dt>대표자</dt>
				<dd><input type="text" id="san_buisnessman_s" name="san_buisnessman_s" class="naksi_input w100" placeholder="예)홍길동" /></dd>
			</dl>
			<div class="btnArea">
				<a href="#" class="btns submitBtn bgBlue" id="mod" onclick="ajax_search_corp()">이미 등록된 정보 조회</a>
			</div>
		</div>
		<div class="fir_login_area">
			<p class="comment">이전 등록된 정보가 없으면 신규 등록 부탁드립니다.</p>
			<div class="btnArea">
				<a href="/info/industry/m/write.do" class="btns submitBtn bgBlue">신규등록 바로가기</a>
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
			<a href="/info/industry/m/write.do" class="btns submitBtn bgBlue">신규등록 바로가기</a>
			<a href="javascript:location.href='/info/m/industry/login.do';" class="btns submitBtn bgBlue mgt10">나가기</a>
		</div>
		<a href="javascript:location.href='/info/m/industry/login.do';" class="closebtn"><img src="/naksinuri_original/common_main/img/ico_close.png" /></a>
	</div>
</div>
<form name="veiw_go" id="veiw_go" action ="<c:url value='/info/m/industry/ind_viewS.do'/>" method="post">
	<input type="hidden" id="san_sn" name="san_sn" value="" />
</form>

<%--
<div id="wrapper">
	<header id="header">
		<h1>
			<em>해양수산부와 한국어촌어항공단가 운영하는 낚시정보종합포털 "낚시누리"에서 귀하의 사업장 정보를 무료로 홍보해 드립니다.</em>
			<span>낚시산업 <b>정보 등록</b></span>
			<em>낚시관련 조구업체. 협회/기관/단체, 미디어, 판매점, 출조점, 낚시정보제공업 등</em>
		</h1>
	</header>

	<div id="container" class="respon">
		<section id="login" class="content">
			<div class="btnArea" style="margin-top:-0.5%;padding-bottom:20px">
				2012년 해양수산부에서 조사된 이전 정보 등록 여부 확인 및 수정
			</div>
			
			 <input type="hidden" name="message" value="${message}" />

			<div class="writeBox">
				<dl>
					<dt>상호명</dt>
					<dd><input type="text" id="san_name_s" name="san_name_s" class="naksi_input w100" placeholder="예)낚시누리" /></dd>
				</dl>
				<dl>
					<dt>대표자</dt>
					<dd><input type="text" id="san_buisnessman_s" name="san_buisnessman_s" class="naksi_input w100" placeholder="예)홍길동" /></dd>
				</dl>
			</div>
			<div class="btnArea">
				<a href="#" class="submitBtn bgBlue" id="mod" onclick="ajax_search_corp()">이미 등록된 정보 조회</a>
			</div>
		</section>
	</div>
	<div id="container" class="respon">
		<section id="login" class="content">
			<div class="btnArea" style="margin-top:-0.5%;padding-bottom:20px">
				<a href=/info/industry/m/write.do" class="submitBtn bgBlue">신규등록 바로가기</a>
			</div>
		</section>
	</div>
	
	<div id="search_result_box" style="display: none; width: 500px; height: 400px; ">
		<div style="border: solid 3px red; padding: 50px;">
			<div id="search_result">
			등록된 정보가 없습니다.
			<br><br>
			신규 등록을 진행하시겠습니까?
			<br><br>
			<a href="/info/industry/m/write.do" class="submitBtn bgBlue">신규등록 바로가기</a>
			<br><br>
			<a href="javascript:location.href='/info/m/industry/login.do';" class="submitBtn bgBlue">나가기</a>
			
			</div>
		</div>
	</div>
	<form name="veiw_go" id="veiw_go" action ="<c:url value='/info/m/industry/ind_viewS.do'/>" method="post">
		<input type="hidden" id="san_sn" name="san_sn" value="" />
	</form>
--%>
	
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
/*fnInit();*/

$('#mod').click(function(){
	var san_name = $('#san_name_s').val();
	var san_buisnessman =  $('#san_buisnessman_s').val();
	if(!$.trim(san_name)) { alert('상호명을 입력해 주세요.'); return false; }
	if(!$.trim(san_buisnessman)) { alert('대표자를 입력해 주세요.'); return false; }
	$.ajax({
		url: "/info/industry/ind_search.do",
		type: "POST",
		dataType:'text',  //json, text
		data: { san_name:san_name, san_buisnessman:san_buisnessman  },
		async : true, 
		success: function(res){
			//alert(res);
			var dataobj = $.parseJSON(res);
			var is_data = dataobj.is_data;
			
			if(is_data == "N") {
				$('#search_result_box').show();
			} else {
				//view(${item.san_sn})
				$('#san_sn').val(is_data);
				$('#veiw_go').submit();
			}
		}
	});
});

</script>


<%@include file="../../layout/m/tail.jsp"%>