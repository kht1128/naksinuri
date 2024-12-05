<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

	<c:if test="${pageMode ne 'indexpage'}">
		</div>
	</c:if>
	
	<!-- 하단푸터 { -->
	<%--
	<footer id="footer">
		<div id="footMnu">
			<div class="respon">
				<ul class="floats">
					<li><a href="/policy/customer_sound.do">고객의 소리</a></li>
					<li><a href="/manual/manual_intro.html" target="_blank">낚시누리 사용자매뉴얼</a></li>
					<!--<li><a href="/policy/privacy.do">개인정보처리방침</a></li> --!>
					<li><a href="/policy/copyright.do">저작권보호정책</a></li>
					<li><a href="/policy/agree.do">이용약관</a></li>
					<li><a href="http://m.mof.go.kr/content/htmlView.do?htmlKey=0803&menuKey=714" target="_blank">관련 앱다운로드</a></li>
				</ul>
				<ul class="foot_social floats">
					<li><a href="https://www.facebook.com/nurinaksi/" target="_blank"><i class="fa fa-facebook-square" aria-hidden="true"></i></a></li>
				</ul>
			</div>
		</div>
		<div id="footCon" class="respon">
			<ul class="floats">
				<li><em>주소 : </em><span>(08588) 서울특별시 금천구 가산디지털2로 53 한라시그마밸리 5층</span></li>
				<li><em>TEL : </em><span>1833-7139</span></li>
<!-- 			<li><em>FAX : </em><span>02)6098-0810</span></li>  -->
				<li><em>상호 : </em><span>한국어촌어항공단</span></li>
				<li><em>대표 : </em><span>최명용</span></li>
				<li><em>사업자번호 : </em><span>220-82-00065</span></li>
			</ul>
			<p class="copyright">Copyright(C) 2015 Naksi-Nuri. All rights reserved.</p>
			<div class="link_select">
				<dl>
					<dd>
						<ul>
							<li><a title="[새창열기]국가어도정보시스템" target="_blank" href="http://www.fishway.go.kr/home/mainPage.do">국가어도정보시스템</a></li>
							<li><a title="[새창열기]국가해양환경정보통합시스템" target="_blank" href="http://www.meis.go.kr">국가해양환경정보통합시스템</a></li>
							<li><a title="[새창열기]글로벌화물추적시스템" target="_blank" href="https://www.gcts.go.kr/index.do">글로벌화물추적시스템</a></li>
							<li><a title="[새창열기]바다생태정보나라" target="_blank" href="http://www.ecosea.go.kr">바다생태정보나라</a></li>
							<li><a title="[새창열기]바다여행" target="_blank" href="http://www.seantour.com">바다여행</a></li>
							<li><a title="[새창열기]수산정보포털" target="_blank" href="http://www.fips.go.kr">수산정보포털</a></li>
							<li><a title="[새창열기]연안포털" target="_blank" href="http://www.coast.kr">연안포털</a></li>
							<li><a title="[새창열기]어업자원포털" target="_blank" href="https://www.greensea.go.kr">어업자원포털</a></li>
							<li><a title="[새창열기]원양산업종합정보시스템" target="_blank" href="https://www.ofis.or.kr">원양산업종합정보시스템</a></li>
							<li><a title="[새창열기]정도관리시스템" target="_blank" href="http://www.marenqc.or.kr/index.jsf">정도관리시스템</a></li>
							<li><a title="[새창열기]해양수산통계시스템" target="_blank" href="http://www.mof.go.kr/statPortal">해양수산통계시스템</a></li>
							<li><a title="[새창열기]yes! U-port" target="_blank" href="https://www.yesport.go.kr:4433/main.jsp">yes! U-port</a></li>
						</ul>
					</dd>
					<dt><a href="#;">추천사이트 <span><i class="fa fa-angle-up" aria-hidden="true"></i></span></a></dt>
				</dl>
			</div>
		</div>
	</footer>
	--%>
	<footer id="footer">
		<div class="Grp">
			<div id="footMnu">
				<ul class="floats">
					<li><a href="/policy/privacy.do" class="font-size-16" title="개인정보처리방침">개인정보처리방침</a></li>
					<!-- <li><a href="/policy/customer_sound_list.do" title="고객의소리">고객의 소리</a></li> -->
					<!-- <li><a href="/naksinuri_original/manual/manual_intro.html" target="_blank" title="낚시누리사용자메뉴얼">낚시누리 사용자매뉴얼</a></li> -->
					<li><a href="/policy/copyright.do" title="저작권보호정책">저작권보호정책</a></li>
					<li><a href="/policy/agree.do" title="이용약관">이용약관</a></li>
				</ul>
			</div>
		</div>
	</footer>
	<!--### new footer ######-->
	<div class="nfooter">
		<div class="Grp">
			<div class="ft"><img src="/naksinuri_original/edu/ft.png" alt="낚시누리" /></div>
			<div class="copyright">
				<p>주소 : (08588) 서울특별시 금천구 가산디지털2로 53 한라시그마벨리 10층<span class="ml-20">TEL : 1833-7139</span></p>
				<p><span class="">상호 : 한국어촌어항공단</span><span class="ml-20">대표 : 홍종욱</span><span class="ml-20">사업자번호 : 220-82-00065</span></p>
				<p>COPYRIGHT(C) 2015 NAKSI-NURI. ALL RIGHTS RESERVED.</p>
			</div><!--//copyright//-->
			<div class="sns">
				<a href="https://www.facebook.com/nurinaksi/" target="_blank" title="낚시누리 페이스북 새창열림"><img src="/naksinuri_original/edu/facebook.png" alt="낚시누리 페이스북"/></a>
				<a href="http://www.wa.or.kr/board/list.asp?search=total&SearchString=%B3%AC%BD%C3%B4%A9%B8%AE&BoardID=0006" target="_blank" title="한국웹접근성인증평가원 새창열림"><img src="/naksinuri_original/edu/quality_mark.gif" alt="과학기술정보통신부 WA WEB접근성품질인증마크 한국웹접근성인증평가원" style="padding-top:5px;"/></a>
			</div><!--//sns//-->
		</div><!--//Grp//-->

	</div><!--//nfooter//-->
	<!--end.### new footer ###-->
	<!-- } 하단푸터 -->
	
	<!-- 상단 이동 버튼 -->
	<a id="top_btn" href="#" title="상단으로 이동"></a>
	<!-- 상단 이동 버튼 end -->
</div>
<!-- 본문 끝 -->
</div>
<script type="text/javascript" src="//wcs.naver.net/wcslog.js"></script> <script type="text/javascript"> if(!wcs_add) var wcs_add = {}; wcs_add["wa"] = "77f0b76febfbc8"; wcs_do(); </script>

<!-- Modal -->
<div class="modal fade chart-pop chart01" tabindex="0" id="allPublicModal" role="dialog"></div>

<div class="modal fade" id="allPublicModalMessage" role="dialog" style="z-index:9999;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-message" style="font-weight:bold;color:#ff0000;text-align:center;">메세지</div>
				<!-- 
				<div class="float-right">
					<button type="button" class="btn btn-default btn-outline" data-dismiss="modal">확인</button>
				</div>
				 -->
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="allPublicModalMessageLock" role="dialog" style="z-index:9999;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">알림</h4>
			</div>
			<div class="modal-body">
				<div class="modal-message" style="font-weight:bold;color:#ff0000;text-align:center;">메세지</div>
			</div>
		</div>
	</div>
</div>
<!-- End Modal -->

<script defer="defer">
$(function(){
	/* $('.gnb1ul').find('li.gnb1li').focusin(function(){
		console.log('들어옴..');
		//$('#header').addClass('open');
	}).focusout(function(){
		console.log('안들어옴..');
		//$('#header').removeClass('open');
	}); */
	
	$('.gnb1ul').find('li.gnb1li').on('focusin',function(event){
		event.preventDefault ();
		//console.log('들어옴..');
		$('#header #allMnuBox #gnb').css('height','400px');
		$('#header').addClass('open');
	}).focusout(function(){
		$('#header #allMnuBox #gnb').css('height','60px');
		$('#header').removeClass('open');
	}); 
	
	
	$('.gallery_list > ul > li').on("focusin",function(e){
	     $(this).children('.search_square').css({	
	         "top": "80px",
	         "background-color": "rgba(38,110,179,0.7)"
	       });
	}).focusout(function(){
		$(this).children('.search_square').css({
	         "top": "-70px"
	       });
	});
	
	
	
});
var depthName="";
var pageName="";

var spairDepth="";
var spairPage="";
var tabName="";
var urlpagename="";

var urlcontroller="";
$(document).ready(function (){

	var groupHome="<c:out value="${groupName}" />";
	if(groupHome=='') {
		groupHome = '홈';
	}
	
	depthName=$('#gnb_1dul > .gnb_1dli.select >a').text();
	pageName=$('#menu >ul >li.select').text();
	
	spairDepth="<c:out value="${depthName}" />";
	spairPage="<c:out value="${pageName}" />";
	
	urlpagename = "<c:out value="${urlpagename}" />";
	urlcontroller = "<c:out value="${urlcontroller}" />";
	if(depthName!='' && pageName!=''){
		
		$('#select_name').text(groupHome+" > "+depthName+" > "+pageName);

		if(pageName == '낚시터정보 > 낚시터 정보등록')
			$('#select_pageName').text('낚시터 정보등록');
		else
			$('#select_pageName').text(pageName);
		
		tabName = $('.tabArea').children('ul').children('li.on').children('a').text();
		//console.log("탭메뉴이름1 : " + $('.tabArea').children('ul').children('li.on').children('a').html());
		if( isEmpty(tabName) ){
			console.log('1');
			document.title = "낚시누리  > "+depthName+" > "+pageName;
		}else{
			console.log('2');
			
			var trimTabName = tabName.replace(/\s/gi, "");
			if( trimTabName == '오시는길' ){
				document.title = "낚시누리  > "+depthName+" > "+pageName;
			}else{
				document.title = "낚시누리  > "+depthName+" > "+pageName+" > "+tabName;
			}
			
		}
		
	}else{
		$('#select_name').text(groupHome+" > "+spairDepth+" > "+spairPage);
		$('#select_pageName').text(spairPage);
		
		if( isEmpty(spairDepth) ){
			console.log('3');
			
			if( urlcontroller.indexOf("educenter") != -1 ){
				document.title = "낚시누리 > 낚시전문교육";
			}else{
				document.title = "낚시누리";	
			}
			
		}else{
			console.log('4');
			tabName = $('.tabArea').children('ul').children('li.on').children('a').text();
			if( isEmpty(tabName) ){
				if ( urlpagename.indexOf("write") != -1 ){
					document.title = "낚시누리  > "+spairDepth+" > "+spairPage+" > 글쓰기";	
				}else{
					document.title = "낚시누리  > "+spairDepth+" > "+spairPage;	
				}
				
			}else{
				document.title = "낚시누리  > "+spairDepth+" > "+spairPage+" > "+tabName;	
			}
			
		}
		
	}

});
</script>

<script>
function fn_egov_all_document_fileDownload(atchFileId, fileSn){
	window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
}
function fn_egov_all_pdf_fileShow(atchFileId, fileSn) {
	window.open("<c:url value='/cmm/fms/getPdf.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
}
function fn_egov_all_img_fileShow(atchFileId, fileSn) {
	window.open("<c:url value='/cmm/fms/getImage.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
}
</script>


<!-- 로그인여부 확인 후 지정 페이지이동 -->
<form id="ajaxMoveUrlForChkLoginForm" name="ajaxMoveUrlForChkLoginForm" method="post">
<input type="hidden" name="mvf" value=""/>
<input type="hidden" name="mvs" value=""/>
</form>
<script>
$(".btn-act-chk-login-to").click(function() {
	var act = 'fail';
	var mvf = $(this).attr('data-linkurl-fail');
	var mvs = $(this).attr('data-linkurl-success');
	
	var form = document.getElementById('ajaxMoveUrlForChkLoginForm');
	form.mvf.value = mvf;
	form.mvs.value = mvs;
	form.action = '';
	form.target = '';	
	$.ajax({
		type:"POST",
		url :"/all/chk/login.do",
		data:$('#ajaxMoveUrlForChkLoginForm').serialize(),
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			if(data.error == '1') {
				alert(data.msg);
			} else {
				if(data.oklogin == '1') {
					//alert(data.msg);
					location.href = mvs;
				} else {
					//alert(data.msg);
					kcbOkCertAct();
				}
			}		
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});  
});

$(document).off('keydown','.containerFocus',null);
$(document).on('keydown','.containerFocus',function (e) {
	setTimeout(function(){
		console.log(" containerFocus " + $('#header').hasClass('scrollOn') + e.keyCode);
		if (e.keyCode == 13 || e.keyCode == 9) { 
			console.log(" ::::::::::::::::: up");
			
			if($('#header').hasClass('scrollOn')) {
				
				$('#containerWrap').css('padding-top','100px');
			} else {
				$('#containerWrap').css('padding-top','');
			}
		} 
	}, 50);
});
$('.containerFocus').focus(function(){
	$('#containerWrap').css('padding-top','');
});
$('.gnbFocus').focus(function(){
	$('#containerWrap').css('padding-top','');
});



</script>
<!-- End//로그인여부 확인 후 지정 페이지이동 -->

<script src="/common/js/formatter-js/jquery.formatter.js"></script>
<!-- <script src="/common/js/formatter-js/formatter.js"></script> --> 

<script src="/common/js/tail.js"></script>

<%@include file="tail.sub.jsp"%>
