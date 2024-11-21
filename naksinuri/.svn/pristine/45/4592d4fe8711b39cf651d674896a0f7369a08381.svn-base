<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/eduadm/certificate/modify_act.do">
	
	<input type="hidden" id="typeStr" name="typeStr" value="dtl"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">이수증발급 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >이수증발급번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.CRTF_SN}" disabled>
				<input type="hidden" name="CRTF_SN" value="${info.CRTF_SN}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >교육과정명</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.CRS_NM}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >아이디</label>
           	<div class="col-md-9">
           		<c:choose>
           			<c:when test="${empty info.MBR_NM}">
           				<input type="text" class="form-control" placeholder="" autocomplete="off" value="(비회원)" disabled>
						<input type="hidden" name="MBR_ID" value="">
           			</c:when>
           			<c:otherwise>
           				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_ID}" disabled>
						<input type="hidden" name="MBR_ID" value="${info.MBR_ID}">
           			</c:otherwise>
           		</c:choose>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >이름</label>
           	<div class="col-md-9">
           		<c:choose>
           			<c:when test="${empty info.MBR_NM}">
           				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.TMP_MBR_NM}" disabled>
           			</c:when>
           			<c:otherwise>
           				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_NM}" disabled>
           			</c:otherwise>
           		</c:choose>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >연락처</label>
           	<div class="col-md-9">
           		<c:choose>
           			<c:when test="${empty info.MBR_HP}">
           				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.TMP_MBR_HP}" disabled>
           			</c:when>
           			<c:otherwise>
           				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_HP}" disabled>
           			</c:otherwise>
           		</c:choose>
			</div>
		</div>
		<c:choose>
   			<c:when test="${not empty info.TMP_MBR_BIRTH}">
   				<div class="form-group row">
		 			<label class="col-md-3 form-control-label" >생년월일</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.TMP_MBR_BIRTH}" disabled>
					</div>
				</div>
   			</c:when>
   			<c:otherwise>
   				<div class="form-group row">
		 			<label class="col-md-3 form-control-label" >이메일</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_EMAIL}" disabled>
					</div>
				</div>
   			</c:otherwise>
   		</c:choose>
		<%--
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" >이수증파일정보</label>
			<div class="col-md-9">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.CRTF_FILE_SN}" disabled>
			</div>
		</div>
		--%>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_Y" name="USE_ST_CHK" value="Y" <c:if test="${info.USE_ST eq '1'}">checked</c:if>>
					<label for="USE_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_N" name="USE_ST_CHK" value="N" <c:if test="${info.USE_ST eq '0'}">checked</c:if>>
  					<label for="USE_ST_N">사용안함</label>
				</div>
			</div>
		</div>	
        <div class="form-group row">
        	<label class="col-md-3 form-control-label">발급일자</label>
           	<div class="col-md-9">
           		<fmt:parseDate var="parseregdatestring" value="${info.REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">수정일자</label>
			<div class="col-md-9">
				<fmt:parseDate var="parsemoddatestring" value="${info.UPD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="moddatestring" value="${parsemoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${moddatestring}" disabled>
   			</div>
		</div>
		<div class="float-right">
        	<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">변경하기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<style>
.select2-container { width: 99.9% !important; }
</style>
<script>
$(function(){
	//$(".selec2_manual").select2();
	//$('.selectpicker_manual').selectpicker();
});
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();

	//default
	$.ajax({
		type:"POST",
		url :$(this).attr("action"),
		data:$(this).serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		cache: false,
		success: function(data, status, xhr) {
			if(data.errCnt > 0) {
				var pass = true;
				for(var key in data.errField) {
					$('#'+data.errField[key]).addClass('is-invalid');
					pass = false;
				}
				if(pass) {
					alertify.alert(data.msg);
				}
			} else {
				if(data.error == '1') {
					alertify.alert(data.msg);
				} else {
					$("#seaAdmEduPublicModal").modal('hide');
					document.listForm.submit();
				}
			}
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
			clickRequestLockStop();
		}
	});
});
</script>
