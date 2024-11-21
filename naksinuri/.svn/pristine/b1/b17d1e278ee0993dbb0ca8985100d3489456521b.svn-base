<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" autocomplete="off" action="/eduadm/category/modifyDtl_act.do">
	<input type="hidden" name="CAT_SN" value="${info.CAT_SN}"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">하위카테고리 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="CAT_DTL_SN">코드값</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" name="CAT_DTL_SN" value="${info.CAT_DTL_SN}" readonly>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm">교육카테고리(상위)</label>
			<div class="col-md-9">
				<select class="form-control selec2_manual" id="trg_sel_cat_1" name="CAT_SN_CHNG" required >   
					<c:if test="${empty edu_category_1}">
						<option value="">등록 된 상위카테고리가 없습니다.</option>
					</c:if>   
					<c:if test="${not empty edu_category_1}">
						<option value="">교육카테고리를 선택해주세요.</option>
					</c:if>
					<c:forEach var="item" items="${edu_category_1}">
						<option value="${item.CAT_SN}" <c:if test="${item.CAT_SN eq info.CAT_SN}">selected</c:if> >${item.CAT_NM}</option>
					</c:forEach>
	            </select>            
            </div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="CAT_DTL_NM">명칭</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control" id="CAT_DTL_NM" name="CAT_DTL_NM" placeholder="카테고리 명칭을 입력하세요." autocomplete="off" value="${info.CAT_DTL_NM}" required>
            	
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">설명</label>
			<div class="col-md-9">
				<textarea class="form-control" id="CAT_DTL_DSCRP" name="CAT_DTL_DSCRP" placeholder="설명(요약)">${info.CAT_DTL_DSCRP}</textarea>
 			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="LRNNG_TIME">교육시간</label>
			<div class="col-md-9">
				<input type="number" class="form-control" id="LRNNG_TIME" name="LRNNG_TIME" placeholder="교육시간을 입력하세요." autocomplete="off" value="${info.LRNNG_TIME}" required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="LRNNG_POINT">기본점수</label>
			<div class="col-md-9">
				<input type="number" class="form-control" id="LRNNG_POINT" name="LRNNG_POINT" placeholder="기본점수을 입력하세요." autocomplete="off" value="${info.LRNNG_POINT}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm">교육타입</label>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="LRNNG_GB" >   
					<option value="online" <c:if test="${info.LRNNG_GB eq 'online'}">selected</c:if> >온라인 교육</option>
					<option value="offline" <c:if test="${info.LRNNG_GB eq 'offline'}">selected</c:if> >오프라인 교육</option>
	            </select>            
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
<style>
.select2-container { width: 99.9% !important; }
</style>
<script>
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
});	
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();	

	$.ajax({
		type:"POST",
		url :$(this).attr("action"),
		data:$(this).serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//application/json;charset=UTF-8
		async: true,
		cache: false,
		success: function(data, status, xhr) {
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
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			$('.trg_btn_submit').removeClass('disabled');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {		
			clickRequestLockStop();
		}
	});		
});
</script>