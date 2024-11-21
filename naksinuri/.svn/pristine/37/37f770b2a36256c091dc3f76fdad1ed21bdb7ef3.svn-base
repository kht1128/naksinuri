<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<div id="btnArea" class="recommendBox">
	<c:if test="${(pageName eq 'column') or (pageName eq 'usage') or (pageName eq 'travel')}">	
	 <a href="#;" data-toggle="modal" data-target="#updateCheckPassword"  class="btn_list btn_gray">수정</a>
	 <a href="#;" data-toggle="modal" data-target="#deleteCheckPassword"  class="btn_list btn_gray">삭제</a>
	</c:if>
	<a href="#;" class="btn_red"  onclick="like_up(${info.bo_sn})"><i class="fa fa-heart" aria-hidden="true" title="추천"></i> 추천</a>
	<div class="socialLinkBox">
		<a href="#;" onclick="shnaver(${info.bo_sn})" class="btn_naver"><img src="/naksinuri_original/common_main/img/ico_social_naver.png" alt="네이버"/></a>
		<a href="#;" onclick="shkakaostory(${info.bo_sn})" class="btn_kakao"><img src="/naksinuri_original/common_main/img/ico_social_ks.png" alt="네이버"/></a>
		<a href="#;" onclick="shfabook(${info.bo_sn})" class="btn_blue"><i class="fa fa-facebook" aria-hidden="true"></i></a>
		<a href="#;" onclick="shtwitter(${info.bo_sn})" class="btn_blue"><i class="fa fa-twitter" aria-hidden="true"></i></a>
		<!-- <a href="javascript:printelem(document.getElementById('printthis').innerHTML)" class="btn_gray"><i class="fa fa-print" aria-hidden="true"></i></a> -->
	</div>
</div>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">

var href=location.href;
href = href.replace("#;","");
var href2 = href.split("?",1);
var mergeurl = href2[0].replace("/m/","/");

function shkakaostory(bo_sn) {
	var url = mergeurl+'?bo_sn='+bo_sn;//encodeURI(encodeURIComponent(href2[0]+'?bo_sn='+bo_sn));
	var title = '[낚시누리]-${paName}';//encodeURI('[낚시누리]-${paName}');
	//카카오스토리 api
	Kakao.init('6c7b44a36ad2f2409b29411a65ec6aa7');
	//Kakao.Story.share({
	Kakao.Story.open({
      url: url,
      text: title
    });
}
	
function shnaver(bo_sn) {
	
	var url = encodeURI(encodeURIComponent(mergeurl+'?bo_sn='+bo_sn));
    var title = encodeURI('[낚시누리]-${paName}');
    //var shareURL = "http://blog.naver.com/openapi/share?url="+url+"&title="+title;
    var shareURL =  "https://share.naver.com/web/shareView.nhn?url="+url+"&title="+title;
    //document.location = shareURL;
    window.open(shareURL);
}	
	
	
function shfabook(bo_sn){
	window.open('http://www.facebook.com/sharer/sharer.php?u='+mergeurl+'?bo_sn='+bo_sn);
	
}

function shtwitter(bo_sn){
	window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+mergeurl+'?bo_sn='+bo_sn);
}

	
	

function update_travel_info(bo_sn){
	var form = document.getElementById('listform');
	var bo_sn = $("#spbo_sn").val();
	var bo_pass = $("#udt_bo_pass").val();
	$('#bo_sn').val(bo_sn);
	$('#bo_pass').val(bo_pass);
	form.action="./update_passcheck.do";
	form.submit(); 
}

//게시글 삭제
function delete_travel_info(bo_sn){

	var form = document.getElementById('listform');
	var bo_pass = $("#del_bo_pass").val();
	$('#bo_sn').val(bo_sn);
	$('#bo_pass').val(bo_pass);
	form.action="./delete_passcheck.do";
	form.submit();		
			
		}

	
	



function like_up(bo_sn){
	var form = document.getElementById('listform');
	$('#bo_sn').val(bo_sn);
	form.action="./like.do";
	form.submit();
}
</script>


