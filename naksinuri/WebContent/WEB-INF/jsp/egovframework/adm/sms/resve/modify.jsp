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
<form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/adm/sms/resve/modify_act.do">
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">메세지 예약건 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >예약번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MID}" disabled>
				<input type="hidden" name="MID" value="${info.MID}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >내용 </label>
           	<div class="col-md-9">
           		<textarea class="form-control h-350 text-typing-key-up" id="MSG" name="MSG" placeholder="메세지 내용을 입력해주세요." row="5" required>${info.MSG }</textarea>
           		<div class="col-md-12 text-right p-0 text-right">
           			<div class="float-left">
	           			<span class="grey-500 font-size-12">SMS : 80 byte | MMS : 2,000 byte</span>
	           		</div>
           			<span class="badge badge-default mr-5 text-typing-label-byte">0 / 2000 byte</span><span class="badge badge-success text-typing-label-sms">SMS</span><span class="badge badge-danger text-typing-label-mms hidden">MMS</span>
           		</div>
           		<input type="hidden" id="text_typing_cnt" value="0">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >발송 옵션 선택 </label>
           	<div class="col-md-9">
				<%-- // 0:대기,1:발송완료,2:발송실패,3:결과리턴,4:발송실패(잔액부족),5:예약발송 // --%>
				<div class="col-lg-9 pl-0">
					<div class="radio-custom radio-default radio-inline ">
						<input type="radio" id="STAT_0" name="STAT" value="0" class="clk-radio-chk-resve" <c:if test="${info.STAT ne '5'}">checked</c:if> >
						<label for="STAT_0">즉시발송</label>
					</div>
					<div class="radio-custom radio-default radio-inline">
						<input type="radio" id="STAT_5" name="STAT" value="5" class="clk-radio-chk-resve" <c:if test="${info.STAT eq '5'}">checked</c:if> >
	  					<label for="STAT_5">예약발송</label>
					</div>
				</div>
				<div class="col-lg-9 row pl-0 mt-10 <c:if test="${info.STAT ne '5'}">hidden</c:if>" id="trg_send_date_form">
				
					<c:set var="SEND_DATE_DT" value=""/>
					<c:set var="SEND_DATE_TIME" value=""/>
					<c:if test="${info.STAT eq '5'}">
						<fmt:parseDate value="${fn:replace(info.SEND_DATE, '.0', '')}" var="parse_send_date" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
		           		<fmt:formatDate value="${parse_send_date}" pattern="yyyy-MM-dd" var="SEND_DATE_DT"/>
		           		<fmt:formatDate value="${parse_send_date}" pattern="HH:mm" var="SEND_DATE_TIME"/>	
					</c:if>
								
					<div class="col-lg-6">
						<div class="input-group form-icons">
							<i class="form-control-icon wb-calendar"></i>
							<input type="text" class="form-control datepickerSender text-center " id="SEND_DATE_DT" value="${SEND_DATE_DT}" placeholder="발송일자" >
						</div>
					</div>	
					<div class="col-lg-6">
						<div class="input-group form-icons">
							<i class="form-control-icon wb-time"></i>
		   					<input type="text" class="form-control timepickerSender text-center " id="SEND_DATE_TIME" value="${SEND_DATE_TIME}" placeholder="발송시간" >
		   				</div>
	   				</div>
	   				<input type="hidden" id="SEND_DATE" name="SEND_DATE" value="">
   				</div>
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
        	<label class="col-md-3 form-control-label text-left">등록일자</label>
           	<div class="col-md-9">
           		<fmt:parseDate var="parseregdatestring" value="${info.REG_DATE}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<div class="input-group">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring} (${info.REG_MBR_ID})" disabled>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left">변경일자</label>
			<div class="col-md-9">
				<fmt:parseDate var="parsemoddatestring" value="${info.UPD_DATE}" pattern="yyyy-MM-dd HH:mm:ss" />
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
.ui-timepicker-wrapper{z-index:9999!important;}
.token-label{max-width:100%!important;}
</style>
<script>
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
	$('.tokenfield2_manual').tokenfield();	
	$('.datepickerSender').datepicker({
	    format: 'yyyy-mm-dd',
	    startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	$('.timepickerSender').timepicker({
	    'showDuration': true,
	    'timeFormat': 'H:i',
	});
});
$(".clk-radio-chk-resve").click(function() {
	var val = $(this).val();
	if(val == 5) {
		$('#trg_send_date_form').slideDown().removeClass('hidden').css('display','');
	} else {
		$('#trg_send_date_form').slideUp(function(){
			$('#SEND_DATE_DT').val('');
			$('#SEND_DATE_TIME').val('');
			$(this).addClass('hidden');
		});
	}
});
//--------------------------------------
//문자 길이 체크
//--------------------------------------
$('.text-typing-key-up').keyup(function() {
	var msg = $(this).val();
	chkTypingText(msg);
});
function chkTypingText(msg) {
	var onechar;
	var tcount = 0;
	var tmpStr = new String(msg);
	var tmpStrlength = tmpStr.length;
	for (t=0;t<tmpStrlength;t++){
		onechar = tmpStr.charAt(t);
		if (escape(onechar) =='%0D') { } else if (escape(onechar).length > 4) { tcount += 2; } else { tcount++; }
	}
	var cnt = String(tcount).replace(/(\d)(?=(?:\d{3})+(?!\d))/g,'$1,');
	$('#text_typing_cnt').val(cnt);
	$('.text-typing-label-byte').html(cnt+" / 2000 byte").removeClass('red-600');
	if(tcount > 80) {
		$('.text-typing-label-sms').hide();
		$('.text-typing-label-mms').show();
		if(tcount > 2000) {
			$('.text-typing-label-byte').addClass('red-600');
		}
		return false;
	} else {
		/* 파일첨부시 mms로 바꿔야함.
		if($file.val() != ''){
			$('#text-typing-label-sms').hide();
			$('#text-typing-label-mms').show();
		} else {
			$('#text-typing-label-sms').show();
			$('#text-typing-label-mms').hide();
		}
		*/
		$('.text-typing-label-sms').show();
		$('.text-typing-label-mms').hide();
		return false;
	}
}
$(document).ready(function(){
	var msg = $('.text-typing-key-up').val();
	chkTypingText(msg);	
});
//--------------------------------------
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();

	if(Number($('#text_typing_cnt').val().replace(/,/gi, "")) > 2000) {
		alertify.alert('메세지 내용은 최대 2000 byte 를 넘을 수 없습니다.');
		return;
	}
	
	var stat = document.modal_action_form.STAT.value;
	if(stat == '5') {
		var send_date_dt = $('#SEND_DATE_DT').val();
		var send_date_time = $('#SEND_DATE_TIME').val();
		if(send_date_dt=='' || send_date_time=='') {
			alertify.alert('예약발송은 예약일자/시간을 지정해야 합니다.');
			return;
		}
		$(this).find("input[name=SEND_DATE]").val(send_date_dt+" "+send_date_time+":00");
	}
	
	
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
