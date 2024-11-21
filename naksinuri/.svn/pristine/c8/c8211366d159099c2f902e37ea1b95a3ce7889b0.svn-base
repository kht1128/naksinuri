<%-- <%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<form method="post" action="" id="loginVO" name="loginVO" autocomplete="off">
	
</form>
	
<div class="page vertical-align text-center" data-animsition-in="fade-in" data-animsition-out="fade-out">
	<div class="page-content vertical-align-middle animation-slide-top animation-duration-1 mt-75">
		<div class="panel">
      		<div class="panel-body">
        		<div class="brand">
        			<h1 class="blue-600">비밀번호 변경</h1>
        		</div>
        		<% 
        			session.setAttribute("isChangePwd", true);
        		%>	 
        		<form method="post" action="" id="modal_action_form" name="modal_action_form" autocomplete="off" onsubmit="return formSubmit();">
	        		<input type="hidden" id="MBR_ID" name="MBR_ID" value="${loginVO.MBR_ID}"/>
					<input type="hidden" id="MBR_SN" name="MBR_SN" value="${loginVO.MBR_SN}"/>
					
		       		<div class="form-group form-material floating">
	              		<input type="password" class="form-control" name="MBR_PWD" id="MBR_PWD" placeholder="비밀번호를 입력해주세요." autocomplete="off" value="" required>
	              		<label class="floating-label">비밀번호<span class="red-600">*</span></label>
	              		<span class="text-help cyan-600 font-size-12 text-left">* 비밀번호는 공백없이 영문,숫자,특수문자를 혼합하여 10자리 ~ 20자리 이내로 입력해주세요.</span>
	            	</div>
	            	<div class="form-group form-material floating">
	              		<input type="password" class="form-control" name="MBR_PWD_CHK" id="MBR_PWD_CHK" placeholder="비밀번호(확인)를 입력해주세요." autocomplete="off" value="" required>
	              		<label class="floating-label">비밀번호 확인<span class="red-600">*</span></label>
	            	</div> 
		       		<button type="submit" class="btn btn-primary btn-block trg_btn_submit">변경완료</button>
		       	</form>
      		</div>
    	</div>
	</div>
</div>
<script>
function formSubmit() {
	var form = document.modal_action_form;	
	var pw = form.MBR_PWD.value;
	var pw2 = form.MBR_PWD_CHK.value;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	
	if(pw.length < 10 || pw.length > 20){
		alertify.alert("비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}
	if(pw.search(/₩s/) != -1){
		alertify.alert("비밀번호는 공백없이 입력해주세요.");
		return false;
	} 
	if(num < 0 || eng < 0 || spe < 0 ){
		alertify.alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		return false;
	}
	if(pw != pw2) { 
		alertify.alert('비밀번호와 비밀번호 확인이 다릅니다.');
		return false;
	}
	autoChkId();
	return false;
}
function autoChkId() {
	$.ajax({
		type:"POST",
		url :"/adm/member/modify_adm_pwd.do",
		data:$('#modal_action_form').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		cache: false,
		success: function(data, status, xhr) {
			if(data.error=='0'){
				// 성공시
				document.modal_action_form.submit();
			} else {
				// 실패시
				alertify.alert(data.msg);
			}
		},
		beforeSend : function() {
			//console.log('before!');
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}
</script>

<%@ include file="../tail.jsp" %>
 --%>
 
 
 
 
 
 
 
 
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>
<style>
.basic_tbl th, .basic_tbl td{border:0px;}
.basic_tbl tbody{border:1px;}
</style>
<div class="modal-dialog" role="document">
	<div class="modal-content form-horizontal">
		<div class="modal-header">
			<h4 class="modal-title" tabindex="0" style="display: inline;">비밀번호 변경</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   				<span aria-hidden="true">×</span>
 			</button>
		</div>
		<div class="modal-body pt-0 pl-30 pr-30">
			<div class="form-group" >
				<div class="modal-body pt-30 pl-30 pr-30">
					<div class="form-group row modal-view-table scroll-y">
						<% 
		        			session.setAttribute("isChangePwd", true);
		        		%>
						<form method="post" action="" id="modal_action_form" name="modal_action_form" autocomplete="off" onsubmit="return formSubmit();">
			        		<input type="hidden" id="MBR_ID" name="MBR_ID" value="${loginVO.MBR_ID}"/>
							<input type="hidden" id="MBR_SN" name="MBR_SN" value="${loginVO.MBR_SN}"/>
							<input type="hidden" id="MBR_SCRTY_KEY" name="MBR_SCRTY_KEY" value="${MBR_SCRTY_KEY}"/>
							
				       		<div class="form-group form-material floating">
			              		<input type="password" class="form-control" name="MBR_PWD" id="MBR_PWD" placeholder="비밀번호를 입력해주세요." autocomplete="off" value="" required>
			              		<label class="floating-label">비밀번호<span class="red-600">*</span></label>
			              		<span class="text-help cyan-600 font-size-12 text-left">* 비밀번호는 공백없이 영문,숫자,특수문자를 혼합하여 10자리 ~ 20자리 이내로 입력해주세요.</span>
			            	</div>
			            	<div class="form-group form-material floating">
			              		<input type="password" class="form-control" name="MBR_PWD_CHK" id="MBR_PWD_CHK" placeholder="비밀번호(확인)를 입력해주세요." autocomplete="off" value="" required>
			              		<label class="floating-label">비밀번호 확인<span class="red-600">*</span></label>
			            	</div> 
				       		<button type="submit" class="btn btn-primary btn-block trg_btn_submit">변경완료</button>
				       	</form>	
					</div>
			  	</div>
			</div>
		</div>
	</div>
	
<script>
function formSubmit() {
	var form = document.modal_action_form;	
	var pw = form.MBR_PWD.value;
	var pw2 = form.MBR_PWD_CHK.value;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	
	if(pw.length < 10 || pw.length > 20){
		alertify.alert("비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}
	if(pw.search(/₩s/) != -1){
		alertify.alert("비밀번호는 공백없이 입력해주세요.");
		return false;
	} 
	if(num < 0 || eng < 0 || spe < 0 ){
		alertify.alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		return false;
	}
	if(pw != pw2) { 
		alertify.alert('비밀번호와 비밀번호 확인이 다릅니다.');
		return false;
	}
	autoChkId();
	return false;
}
function autoChkId() {
	$.ajax({
		type:"POST",
		url :"/eduadm/member/change_pwd_act.do",
		data:$('#modal_action_form').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		cache: false,
		success: function(data, status, xhr) {
			if(data.error=='0'){
				// 성공시
				$("#admPublicModal").modal('hide');
			} else {
				// 실패시
				alertify.alert(data.msg);
			}
		},
		beforeSend : function() {
			//console.log('before!');
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}
</script>
</div>