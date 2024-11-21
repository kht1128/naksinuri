<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/eduadm/mbrhstry/modifyDtl_act.do">
	
	<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${info.HMBR_SN}"/>
	<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info.CRS_SN}"/>
	<input type="hidden" id="CRS_DTL_SN" name="CRS_DTL_SN" value="${info.CRS_DTL_SN}"/>
	<input type="hidden" id="TRN_SN" name="TRN_SN" value="${info.TRN_SN}"/>
	
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">${info.MBR_NM} 님의 온라인 교과목 이수내역 수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >이수상태</label>
           	<div class="col-md-9">
            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="LRNNG_CMPLT_DTL_ST">
        			<option value="0" <c:if test="${info.LRNNG_CMPLT_DTL_ST eq '0'}">selected</c:if> >수강중</option>
        			<option value="1" <c:if test="${info.LRNNG_CMPLT_DTL_ST eq '1'}">selected</c:if>>이수완료</option>
      			</select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm" >이수내역고유번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.HMBR_DTL_SN}" disabled>
				<input type="hidden" name="HMBR_DTL_SN" value="${info.HMBR_DTL_SN}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >교과목명</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white" placeholder="" autocomplete="off" value="${info.CRS_DTL_NM}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >교육자료명</label>
           	<div class="col-md-9">
           		<div class="input-group">
           			<input type="text" class="form-control bg-white" placeholder="" autocomplete="off" value="${info.TRN_NM}" disabled>
           			<div class="input-group-append">
           				<span class="input-group-text">${info.TRN_TYPE_ST}</span>
           			</div>
           		</div>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="LRNNG_CUR_TIME">이수교육시간</label>
			<div class="col-md-9">
				<div class="input-group">
					<input type="text" class="form-control col-lg-3" id="LRNNG_CUR_TIME" name="LRNNG_CUR_TIME" placeholder="교육시간을 입력해주세요." autocomplete="off" value="${info.LRNNG_CUR_TIME}" required>
					<div class="input-group-append">
           				<span class="input-group-text">시간</span>
           				<span class="input-group-text bg-white">최대가능시간 : ${info.LRNNG_MAX_TIME} 시간</span>
           			</div>
				</div>
				<span class="text-help">최대가능시간 내에서 기입해주세요.</span>
				<span class="text-help text-danger small">같은 교과목명의 자료가 두개 이상인 경우 시간 분배에 주의 해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="LRNNG_CUR_POINT">이수교육점수</label>
			<div class="col-md-9">
				<div class="input-group">
					<input type="text" class="form-control col-lg-3" id="LRNNG_CUR_POINT" name="LRNNG_CUR_POINT" placeholder="교육점수를 입력해주세요." autocomplete="off" value="${info.LRNNG_CUR_POINT}" required>
					<div class="input-group-append">
           				<span class="input-group-text">점수</span>
           				<span class="input-group-text bg-white">최대가능점수 : ${info.LRNNG_MAX_POINT} 점</span>
           			</div> 
				</div>
				<span class="text-help">최대가능점수 내에서 기입해주세요.</span>
				<span class="text-help text-danger small">같은 교과목명의 자료가 두개 이상인 경우 점수 분배에 주의 해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="TRN_CUR_TIME">교육시청시간</label>
			<div class="col-md-9">
				<div class="input-group">
					<input type="text" class="form-control col-lg-3" id="TRN_CUR_TIME" name="TRN_CUR_TIME" placeholder="교육시간을 입력해주세요." autocomplete="off" value="${info.TRN_CUR_TIME}" >
					<div class="input-group-append">
           				<span class="input-group-text">시간(초단위)</span>
           				<span class="input-group-text bg-white">최대가능시간 : ${info.TRN_MAX_TIME} 초</span>
           			</div>
				</div>
				<span class="text-help text-danger small">필요한 경우가 아니라면 수정하지 마세요.</span>
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
        	<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">변경하기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<script>
$(function(){
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
					$("#seaAdmEduPublicModal").modal('hide');
					//window.location.reload(true);
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
