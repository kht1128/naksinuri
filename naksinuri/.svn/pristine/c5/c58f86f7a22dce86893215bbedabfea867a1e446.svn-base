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
</style>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/adm/sms/ment/modify_act.do">
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">메세지 탬플릿 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >탬플릿번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.SMS_MENT_SN}" disabled>
				<input type="hidden" name="SMS_MENT_SN" value="${info.SMS_MENT_SN}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >메세지구분 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="SMS_MENT_TYPE" id="SMS_MENT_TYPE" placeholder="메세지 구분을 선택해주세요." autocomplete="off" value="${info.SMS_MENT_TYPE}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >메세지대상</label>
           	<div class="col-md-9">
           		<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="SMS_MENT_DTL_CD">
           			<option value="" >메세지대상 선택</option>
					<c:forEach var="item_category" items="${list_target_se_cd}">
						<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq info.SMS_MENT_DTL_CD }">selected</c:if> >${item_category.CD_NM}</option>
					</c:forEach>	        			
     			</select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >교육 구분</label>
           	<div class="col-md-9">
           		<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="SMS_EDU_TYPE_GB">
           			<option value="default" <c:if test="${info.SMS_EDU_TYPE_GB eq 'default'}">selected</c:if>>구분없음</option>
           			<option value="online" <c:if test="${info.SMS_EDU_TYPE_GB eq 'online'}">selected</c:if>>온라인교육</option>
           			<option value="offline" <c:if test="${info.SMS_EDU_TYPE_GB eq 'offline'}">selected</c:if>>오프라인교육</option>
     			</select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >제목 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="SMS_MENT_TITLE" id="SMS_MENT_TITLE" placeholder="메세지 제목을 입력해주세요." autocomplete="off" value="${info.SMS_MENT_TITLE}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >내용 </label>
           	<div class="col-md-9">
           		<textarea class="form-control h-350" name="SMS_MENT_CONT" placeholder="메세지 내용을 입력해주세요." row="5" required>${info.SMS_MENT_CONT }</textarea>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label text-left">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="DEL_ST_Y" name="DEL_ST" value="0" <c:if test="${info.DEL_ST eq '0'}">checked</c:if>>
					<label for="DEL_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="DEL_ST_N" name="DEL_ST" value="1" <c:if test="${info.DEL_ST eq '1'}">checked</c:if>>
  					<label for="DEL_ST_N">삭제함</label>
				</div>
			</div>
		</div>		
        <div class="form-group row">
        	<label class="col-md-3 form-control-label text-left">작성일자</label>
           	<div class="col-md-9">
           		<fmt:parseDate var="parseregdatestring" value="${info.REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<div class="input-group">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring} (${info.REG_MBR_ID})" disabled>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left">변경일자</label>
			<div class="col-md-9">
				<fmt:parseDate var="parsemoddatestring" value="${info.UPD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="moddatestring" value="${parsemoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<div class="input-group">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${moddatestring} (${info.UPD_MBR_ID})" disabled>
				</div>
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
.tooltip.tooltip-top,
.tooltip.tooltip-bottom,
.tooltip.tooltip-left,
.tooltip.tooltip-right{z-index:100000!important;}
.select2-container {width:99.9%!important;}
</style>
<script>
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
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
					alertify.alert(data.msg,function(){
						$("#admPublicModal").modal('hide');
						document.listForm.submit();	
					});
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
