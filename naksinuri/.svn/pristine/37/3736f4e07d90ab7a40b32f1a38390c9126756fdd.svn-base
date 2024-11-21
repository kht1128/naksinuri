<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-dialog modal-simple">
<form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="">
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">CTI 권한 및 계정관리</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-4 form-control-label text-left" >아이디(CTI권한부여대상)</label>
           	<div class="col-md-8">
           		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_ID}" disabled>
				<input type="hidden" name="MBR_ID" value="${info.MBR_ID}">
			</div>
		</div>
		<c:if test="${not empty ctiinfo.STAFF_SN and info.MBR_GRP_4_ST eq 'Y'}">
			<div class="form-group row">
				<label class="col-md-4 form-control-label text-left" >CTI관리번호</label>
				<div class="col-md-8">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${ctiinfo.STAFF_SN}" disabled>
					<input type="hidden" name="STAFF_SN" value="${ctiinfo.STAFF_SN}">
				</div>
			</div>	
		</c:if>			
		<c:set var="STAFF_NM" value="${info.MBR_NM}"/>
		<c:if test="${not empty ctiinfo.STAFF_SN and not empty ctiinfo.STAFF_NM}">
			<c:set var="STAFF_NM" value="${ctiinfo.STAFF_NM}"/>
		</c:if>
		<div class="form-group row">
			<label class="col-md-4 form-control-label text-left" >CTI사용자명 <span class="red-600">*</span></label>
           	<div class="col-md-8">
           		<input type="text" class="form-control" name="STAFF_NM" id="STAFF_NM" placeholder="이름 또는 닉네임" autocomplete="off" value="${STAFF_NM}" required enterDisabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-4 form-control-label text-left" >CTI단말기 ID <span class="red-600">*</span></label>
           	<div class="col-md-8">
           		<input type="text" class="form-control bg-white" name="CTI_ID" placeholder="예) 1722" autocomplete="off" value="${ctiinfo.CTI_ID}" required enterDisabled>
				<span class="text-help red-600 font-size-12">* 할당 받은 단말기 ID를 입력하세요.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-4 form-control-label text-left" >CTI단말기 내선번호 <span class="red-600">*</span></label>
           	<div class="col-md-8">
           		<input type="text" class="form-control bg-white" name="CTI_TEL_NO" placeholder="예) 1722" autocomplete="off" value="${ctiinfo.CTI_TEL_NO}" required enterDisabled>
				<span class="text-help red-600 font-size-12">* 할당 받은 단말기 내선번호를 입력하세요.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-4 form-control-label text-left" >조직명(구분) </label>
           	<div class="col-md-8">
           		<input type="text" class="form-control bg-white " name="ORGNZ_NM" placeholder="예) 어장산업팀" autocomplete="off" value="${ctiinfo.ORGNZ_NM}" enterDisabled>
			</div>
		</div>
		<%-- <div class="form-group row">
			<legend class="col-md-4 form-control-label text-left">사용여부</legend>
			<div class="col-md-8">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_Y" name="USE_ST" value="Y" <c:if test="${ctiinfo.USE_ST eq 'Y'}">checked</c:if>>
					<label for="USE_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_N" name="USE_ST" value="N" <c:if test="${empty ctiinfo.USE_ST or ctiinfo.USE_ST eq 'N'}">checked</c:if>>
  					<label for="USE_ST_N">사용안함</label>
				</div>
			</div>
		</div>	 --%>	
		<c:if test="${not empty ctiinfo.STAFF_SN and info.MBR_GRP_4_ST eq 'Y'}">
        <div class="form-group row">
        	<label class="col-md-4 form-control-label text-left">생성일자</label>
           	<div class="col-md-8">
           		<fmt:parseDate var="parseregdatestring" value="${ctiinfo.REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-4 form-control-label text-left">변경일자</label>
			<div class="col-md-8">
				<fmt:parseDate var="parsemoddatestring" value="${ctiinfo.UPD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="moddatestring" value="${parsemoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${moddatestring}" disabled>					
   			</div>
		</div>		
		</c:if>
		<hr>
		<div class="form-group row">
			<div class="btn-group col-lg-12">
				<textarea class="form-control h-80" name="LOG_UPD_MSG" placeholder="처리사유(설명)" row="5" required></textarea>
			</div>
		</div>
		<div class="float-right">
			<c:choose>
				<c:when test="${not empty ctiinfo.STAFF_SN and info.MBR_GRP_4_ST eq 'Y'}">
					<button type="submit" class="btn btn-warning btn-outline trg_btn_submit mr-10 clk_remove_auth_cti" id="btn_delete">권한삭제</button>
					<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_modify">변경하기</button>					
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_write">권한부여</button>
				</c:otherwise>
			</c:choose>
        	
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
$(".readonly").keydown(function(e){
    e.preventDefault();
});
$("input:text[enterDisabled]").on("keydown", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
	};
});
$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
	$('.datepickerModalStr').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	$('.datepickerModalEnd').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
});
var isActionLoading = false;
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();
	var form = document.getElementById('modal_action_form');
	/* if(form.MBR_BIRTH.value.trim().length < 10) {
		alertify.alert('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
	*/
	var targetObj = $(this);
	var clk_target = $(this).find("button[type=submit]:focus").attr('id');
	if(clk_target == 'btn_delete') {
		form.action = '/cti/member/delete_act.do';
		alertify.confirm("권한을 삭제하시겠습니까?",function() {
			process(targetObj);
		});
	} else if(clk_target == 'btn_write') {
		form.action = '/cti/member/write_act.do';
		process(targetObj);
	} else if(clk_target == 'btn_modify') {
		form.action = '/cti/member/modify_act.do';
		process(targetObj);
	} else {
		return;
	}
});
	
function process(obj) {

	$.ajax({
		type:"POST",
		url :obj.attr("action"),
		data:obj.serialize(),
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
				if(data.error == '0') {
					alertify.alert(data.msg,function(){
						$("#seaAdmEduPublicModal").modal('hide');
						document.listForm.submit();	
					});
				} else {
					alertify.alert(data.msg);
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
	
}

</script>
