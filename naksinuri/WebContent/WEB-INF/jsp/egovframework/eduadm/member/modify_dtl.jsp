<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/eduadm/member/modifyDtl_act.do">
	
	<!-- 교육분류에 따른 표기 처리 : start -->
	<c:choose>
		<c:when test="${parentInfo.CRS_TYPE eq 'fshsk_trnng'}">
			<c:set var="label_crs_time" value="개월" />
			<c:set var="is_allow_online" value="0" />
		</c:when>
		<c:otherwise>
			<c:set var="label_crs_time" value="시간" />
			<c:set var="is_allow_online" value="1" />
		</c:otherwise>											
	</c:choose>
	<!-- 교육분류에 따른 표기 처리 : end -->	       
	
	<input type="hidden" id="typeStr" name="typeStr" value="dtl"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">수강내역 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >이수상태</label>
           	<div class="col-md-9">
            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="LRNNG_CMPLT_ST">
        			<option value="0" <c:if test="${info.LRNNG_CMPLT_ST eq '0'}">selected</c:if> >수강중</option>
        			<option value="1" <c:if test="${info.LRNNG_CMPLT_ST eq '1'}">selected</c:if>>이수완료</option>
        			<option value="2" <c:if test="${info.LRNNG_CMPLT_ST eq '2'}">selected</c:if>>이수취소</option>
      			</select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >신청상태</label>
           	<div class="col-md-9">
            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="LRNNG_ST">
        			<option value="0" <c:if test="${info.LRNNG_ST eq '0'}">selected</c:if> >대기</option>
        			<option value="1" <c:if test="${info.LRNNG_ST eq '1'}">selected</c:if>>승인</option>
        			<option value="2" <c:if test="${info.LRNNG_ST eq '2' or info.LRNNG_ST eq '3'}">selected</c:if>>취소</option>
        			<!--<option value="3" <c:if test="${info.LRNNG_ST eq '3'}">selected</c:if>>강제취소</option>-->
        			<option value="4" <c:if test="${info.LRNNG_ST eq '4'}">selected</c:if>>보류</option>
      			</select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >결제상태</label>
           	<div class="col-md-9">
            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="PURCHASE_CMPLT_ST">
        			<option value="0" <c:if test="${info.PURCHASE_CMPLT_ST eq '0'}">selected</c:if> >대기</option>
        			<option value="1" <c:if test="${info.PURCHASE_CMPLT_ST eq '1'}">selected</c:if>>완료</option>
      			</select>
			</div>
		</div>
		<c:if test="${not empty info}">
			<div class="form-group row">
				<label class="col-md-3 form-control-label" >수강고유번호</label>
				<div class="col-md-9">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.HMBR_SN}" disabled>
					<input type="hidden" name="HMBR_SN" value="${info.HMBR_SN}">
				</div>
			</div>
			<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info.CRS_SN}"/>
			<input type="hidden" id="CRS_SN" name="MBR_ID" value="${info.MBR_ID}"/>
		</c:if>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >이름</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white" placeholder="" autocomplete="off" value="${info.MBR_NM}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >연락처</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white" placeholder="" autocomplete="off" value="${info.MBR_HP}" disabled>
				<%--<p class="form-control-plaintext">${info.MBR_HP}</p>--%>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="HMBR_INPUT_TIME">이수교육시간<br>(오프라인)</label>
			<div class="col-md-9">
				<div class="input-group">
					<input type="text" class="form-control col-lg-3" id="HMBR_INPUT_TIME" name="HMBR_INPUT_TIME" placeholder="교육시간을 입력해주세요." autocomplete="off" value="${info.HMBR_INPUT_TIME}" required>
					<div class="input-group-append">
           				<span class="input-group-text">${label_crs_time}</span>
           				<span class="input-group-text bg-white">최대가능시간 : ${parentInfo.CRS_TOT_TIME} ${label_crs_time}</span>
           			</div>
				</div>
				<c:if test="${is_allow_online eq '1'}">
					<span class="text-help">현재 입력된 시간과 온라인교육 시간이 최종 합산됩니다.</span>
				</c:if>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="HMBR_INPUT_POINT">이수교육점수<br>(오프라인)</label>
			<div class="col-md-9">
				<div class="input-group">
					<input type="text" class="form-control col-lg-3" id="HMBR_INPUT_POINT" name="HMBR_INPUT_POINT" placeholder="교육점수를 입력해주세요." autocomplete="off" value="${info.HMBR_INPUT_POINT}" required>
					<div class="input-group-append">
           				<span class="input-group-text">점수</span>
           				<span class="input-group-text bg-white">최대가능점수 : ${parentInfo.CRS_TOT_POINT} 점</span>
           			</div> 
				</div>
				<c:if test="${is_allow_online eq '1'}">
					<span class="text-help">현재 입력된 점수와 온라인교육 점수가 최종 합산됩니다.</span>
				</c:if>
			</div>
		</div>
		<c:if test="${is_allow_online eq '1'}">
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm" >이수교육시간<br>(온라인)</label>
           	<div class="col-md-9">
           		<div class="input-group">
           			<input type="text" class="form-control col-lg-3" value="${info.LRNNG_TOT_TIME}" disabled />
           			<div class="input-group-append">
           				<span class="input-group-text">시간</span>
           				<span class="input-group-text bg-white">최대가능시간 : ${info.HMBR_MAX_TIME - parentInfo.CRS_TOT_TIME} 시간</span>
           			</div> 
            	</div>
            	<span class="text-help">온라인 교육은 이수시 자동으로 반영됩니다.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm" >이수교육점수<br>(온라인)</label>
           	<div class="col-md-9">
           		<div class="input-group">
            		<input type="text" class="form-control col-lg-3" value="${info.LRNNG_TOT_POINT}" disabled />
	            	<div class="input-group-append">
	           			<span class="input-group-text">점</span> 
	           			<span class="input-group-text bg-white">최대가능점수 : ${info.HMBR_MAX_POINT - parentInfo.CRS_TOT_POINT} 점</span>
	           		</div>
	           	</div> 
            	<span class="text-help">온라인 교육은 이수시 자동으로 반영됩니다.</span>
			</div>
		</div>
		</c:if>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_Y" name="USE_ST_CHK" value="Y" <c:if test="${empty info}">checked</c:if> <c:if test="${info.USE_ST eq '1'}">checked</c:if>>
					<label for="USE_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_N" name="USE_ST_CHK" value="N" <c:if test="${not empty info and info.USE_ST eq '0'}">checked</c:if>>
  					<label for="USE_ST_N">사용안함</label>
				</div>
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
					pageMovePrev();
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
