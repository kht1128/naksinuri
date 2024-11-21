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
	action="/adm/sms/ment/write_act.do">
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">메세지 탬플릿 신규등록</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >메세지구분 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="SMS_MENT_TYPE" id="SMS_MENT_TYPE" placeholder="메세지 구분을 선택해주세요." autocomplete="off" value="" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >메세지대상</label>
           	<div class="col-md-9">
           		<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="SMS_MENT_DTL_CD">
           			<option value="" >메세지대상 선택</option>
					<c:forEach var="item_category" items="${list_target_se_cd}">
						<option value="${item_category.CD_ID}" >${item_category.CD_NM}</option>
					</c:forEach>	        			
     			</select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >교육 구분</label>
           	<div class="col-md-9">
           		<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="SMS_EDU_TYPE_GB">
           			<option value="default">구분없음</option>
           			<option value="online">온라인교육</option>
           			<option value="offline">오프라인교육</option>
     			</select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >제목 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="SMS_MENT_TITLE" id="SMS_MENT_TITLE" placeholder="메세지 제목을 입력해주세요." autocomplete="off" value="" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >내용 </label>
           	<div class="col-md-9">
           		<textarea class="form-control h-350" name="SMS_MENT_CONT" placeholder="메세지 내용을 입력해주세요." row="5" required></textarea>
			</div>
		</div>
		<div class="float-right">
        	<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">등록하기</button>
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
