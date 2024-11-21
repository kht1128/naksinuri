<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" autocomplete="off" action="/eduadm/category/modify_act.do">
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">상위카테고리 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="CAT_SN">코드값</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" name="CAT_SN" value="${info.CAT_SN}" readonly>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="CAT_NM">명칭</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control" id="CAT_NM" name="CAT_NM" placeholder="카테고리 명칭을 입력하세요." autocomplete="off" value="${info.CAT_NM}" required>
            	
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">설명</label>
			<div class="col-md-9">
				<textarea class="form-control" id="CAT_DSCRP" name="CAT_DSCRP" placeholder="설명(요약)">${info.CAT_DSCRP}</textarea>
 			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CAT_ORD">정렬순서</label>
			<div class="col-md-9">
				<input type="number" class="form-control" id="CAT_ORD" name="CAT_ORD" placeholder="기본값은 9999 입니다." autocomplete="off" value="${info.CAT_ORD}" required>
			</div>
		</div>
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
				<span class="text-help red-600 font-size-12"><b>삭제</b>상태에서 <b>사용함</b>으로 저장하면 다시 복구 할 수 있습니다.</span>
			</div>
		</div>
        <div class="form-group row">
        	<label class="col-md-3 form-control-label" for="REG_DT">작성정보</label>
           	<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.REG_DT} (${info.REG_MBR_ID})" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="UPD_DT">변경정보</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.UPD_DT} (${info.UPD_MBR_ID})" disabled>
   			</div>
		</div>
		<div class="float-right">
        	<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" >변경하기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<script>
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();

	$.ajax({
		type:"POST",
		url :$(this).attr("action"),
		data:$(this).serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		cache: false,
		success: function(data, status, xhr) {
			//console.log('success!');
			if(data.errCnt > 0) {
				for(var key in data.errField) {
					$('#'+data.errField[key]).addClass('is-invalid');	
				}
			} else {
				if(data.error == '1') {
					alertify.alert(data.msg);
				}
				$("#seaAdmEduPublicModal").modal('hide');
				window.location.reload();
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