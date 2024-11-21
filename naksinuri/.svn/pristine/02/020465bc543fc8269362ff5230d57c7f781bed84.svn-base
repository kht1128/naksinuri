<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.modal-dialog{
/*
    position: relative;
    display: table; 
    overflow-y: auto;    
    overflow-x: auto;
    width: auto;
    min-width: 1000px;
    */   
}
.hidden {display: none;}
.selectMent{
	color: #fff;
	background-color: #6c757d;
	border-color: #6c757d;
}
.selectMent:hover{
	color: #6c757d;
	background-color: #ffffff;
	border-color: #6c757d;
}
</style>

<script>
var rawMbrdata = new Array();//수신대상자 데이터 박스
</script>


<div class="modal-dialog modal-simple">
	<%-- <form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off"> --%>
	<form:form commandName="eduMyHistoryVO" id="ajaxSendKakaoForm" name="ajaxSendKakaoForm" method="post" class="modal-content form-horizontal">
	<input type="hidden" name="MBR_IDS" value="${MBR_IDS}"/>
	<input type="hidden" name="CRS_SN" value="${CRS_SN}"/>
	<input type="hidden" name="TEMPLATE_ID" value="${TEMPLATE_ID}">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title">알림톡 보내기</h4>
		</div>
		<div class="modal-body">
			<div class="form-group row">
				<div class="col-md-12">
					<h6>알림톡 내용 (알림톡 
						<c:choose>
							<c:when test="${TEMPLATE_ID eq 'KKO_000001' or TEMPLATE_ID eq 'KKO_000003' or TEMPLATE_ID eq 'KKO_000008' or TEMPLATE_ID eq 'KKO_000013' or TEMPLATE_ID eq 'KKO_000015' or TEMPLATE_ID eq 'KKO_000019' or TEMPLATE_ID eq 'KKO_000021' or TEMPLATE_ID eq 'KKO_000022'}">
								<span class="red-600"> 2</span>
							</c:when>
							<c:otherwise>
								<span class="red-600"> 1</span>
							</c:otherwise>
						</c:choose>
						개발송)
					</h6>
					<c:choose>
						<c:when test="${TEMPLATE_ID eq 'KKO_000001' or TEMPLATE_ID eq 'KKO_000003' or TEMPLATE_ID eq 'KKO_000008' or TEMPLATE_ID eq 'KKO_000013' or TEMPLATE_ID eq 'KKO_000015' or TEMPLATE_ID eq 'KKO_000019' or TEMPLATE_ID eq 'KKO_000021' or TEMPLATE_ID eq 'KKO_000022'}">
							<pre class="kakaoMainMentCont" contenteditable="false" style="white-space: pre-line;overflow-y: scroll;height: 500px;color:#37474f;">
								${KAKAO_MAIN_MENT_CONT}
							</pre>
							<pre class="kakaoSubMentCont" contenteditable="false" style="white-space: pre-line;overflow-y: scroll;height: 500px;display:none;color:#37474f;">
								${KAKAO_SUB_MENT_CONT}
							</pre>
							<div class="col-lg-12 pb-10" style="color:#37474f;text-align:center;">
								<input type="button" id="selectMent1" value="&lt" class="btn btn-outline-secondary selectMent" >
								<input type="button" id="selectMent2" value="&gt" class="btn btn-outline-secondary" >
							</div>
						</c:when>
						<c:otherwise>
							<pre class="kakaoMainMentCont" contenteditable="false" style="white-space: pre-line;overflow-y: scroll;height: 500px;color:#37474f;">
								${KAKAO_MAIN_MENT_CONT}
							</pre>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-md-12">
					<h6>받으실 분들 <span class="font-weight-800 cyan-600" id="label_mbr_cnt">총 ${mbrTotCnt} 명</span></h6>
				</div>
			</div>
			<div class="float-right">
				<button type="button" class="btn btn-primary btn-outline btn-act-all-send-kakao">보내기</button>
				<button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
			</div>
		</div>
	</form:form>
</div>
<style>
.tooltip.tooltip-top,
.tooltip.tooltip-bottom,
.tooltip.tooltip-left,
.tooltip.tooltip-right{z-index:100000!important;}
.select2-container {width:99.9%!important;}
.ui-timepicker-wrapper{z-index:9999!important;}
.token-label{max-width:100%!important;}
</style>
<script>
$("#selectMent1").click(function(){
	if($(this).hasClass("selectMent")){
		
	} else {
		$("#selectMent1").addClass("selectMent");
		$("#selectMent2").removeClass("selectMent");
	}
	$('.kakaoMainMentCont').css('display','block');
	$('.kakaoSubMentCont').css('display','none');
});
$("#selectMent2").click(function(){
	if($(this).hasClass("selectMent")){
		
	} else {
		$("#selectMent1").removeClass("selectMent");
		$("#selectMent2").addClass("selectMent");
	}
	$('.kakaoMainMentCont').css('display','none');
	$('.kakaoSubMentCont').css('display','block');
});
//--------------------------------------
$(".btn-act-all-send-kakao").click(function() {
	//var form = document.getElementById('ajaxSendKakaoForm');
	//var chkedMbrIds = form.MBR_IDS.value;
	var btn_this = $(this);
	var chkConfirm = confirm("알림톡을 보내시겠습니까?");
	var form = document.getElementById('listForm');
	var form2 = document.getElementById('ajaxSendKakaoForm');
	form.TEMPLATE_ID.value = form2.TEMPLATE_ID.value;
	if(chkConfirm){
		btn_this.prop("disabled", true);
		ajaxLoadingToastAppend();
		$.ajax({
			type:"POST",
			url :"/eduadm/mbrhstry/allSendKakao.do",
			data:$('#listForm').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: false,
			success: function(data, status, xhr) {
				ajaxLoadingToastRemoved();
				if(data.error == '0') {
					alertify.alert(data.msg);
					$("#admPublicModal").modal('hide');
				} else {
					alertify.alert("전송 실패");
				}
			},
			beforeSend : function(xhr, opts) {
				//ajaxLoadingToastAppend();
				//console.log('before!');
			},
			complete : function() {
				//console.log('complete!');
				ajaxLoadingToastRemoved();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
				ajaxLoadingToastRemoved();
			}
		});
	} else {
		alert(" 알림톡 전송을 취소하셨습니다. ");
	}
});
</script>