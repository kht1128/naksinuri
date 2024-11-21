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
.hidden {display: none;}
</style>

<script>
var rawMbrdata = new Array();//수신대상자 데이터 박스
</script>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off">
	<input type="hidden" name="chkedMbrNms" value="${chkedMbrNms}"/>
	<input type="hidden" name="chkedMbrHps" value="${chkedMbrHps}"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">메세지 보내기</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
           	<div class="col-md-12">
           		<h6>발신 번호</h6>
           		<input type="text" class="form-control disabled" placeholder="" autocomplete="off" value="${SEND_NUMBER}" disabled>
			</div>
		</div>
		<div class="form-group row">
           	<div class="col-md-12">
           		<h6>메세지 내용 <span class="red-600">*</span></h6>
           		<textarea class="form-control h-350 text-typing-key-up" id="SMS_MENT_CONT" name="MSG" placeholder="메세지 내용을 입력해주세요." row="5" required>${SMS_MENT_CONT }</textarea>
           		<div class="col-md-12 text-right p-0 text-right">
           			<div class="float-left">
	           			<span class="grey-500 font-size-12">SMS : 80 byte | MMS : 2,000 byte</span>
	           		</div>
           			<span class="badge badge-default mr-5 text-typing-label-byte">0 / 2000 byte</span><span class="badge badge-success text-typing-label-sms">SMS</span><span class="badge badge-danger text-typing-label-mms hidden">MMS</span>
           		</div>
           		<input type="hidden" id="text_typing_cnt" value="0">
           		<input type="hidden" id="SMS_MENT_DTL_CD" name="SMS_MENT_DTL_CD" value="">
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-12">
				<h6>메세지탬플릿 선택</h6>
           		<select class="form-control selectpicker_manual select-sms-ment" data-style="btn-outline btn-default" >
           			<option value="" >메세지 내용 직접 입력</option>
					<c:forEach var="item_category" items="${list_sms_ment}">
						<option value="${item_category.SMS_MENT_SN}" >${item_category.SMS_MENT_TITLE}</option>
					</c:forEach>	        			
     			</select>
			</div>
		</div>
		<%--
		<div class="form-group row">
			<div class="col-md-12">
				<h6>첨부 파일 (이미지)</h6>
           		
			</div>
		</div>
		--%>
		<div class="form-group row">
			<div class="col-md-12">
				<h6>발송 옵션 선택</h6>
				<%-- // 0:대기,1:발송완료,2:발송실패,3:결과리턴,4:발송실패(잔액부족),5:예약발송 // --%>
				<div class="col-lg-12 pl-0">
					<div class="radio-custom radio-default radio-inline ">
						<input type="radio" id="STAT_0" name="STAT" value="0" class="clk-radio-chk-resve" checked>
						<label for="STAT_0">즉시발송</label>
					</div>
					<div class="radio-custom radio-default radio-inline">
						<input type="radio" id="STAT_5" name="STAT" value="5" class="clk-radio-chk-resve" >
	  					<label for="STAT_5">예약발송</label>
					</div>
				</div>
				<div class="col-lg-12 row pl-0 mt-10 hidden" id="trg_send_date_form">
					<div class="col-lg-6">
						<div class="input-group form-icons">
							<i class="form-control-icon wb-calendar"></i>
							<input type="text" class="form-control datepickerSender text-center" id="SEND_DATE_DT" value="" placeholder="발송일자" >
						</div>
					</div>	
					<div class="col-lg-6">
						<div class="input-group form-icons">
							<i class="form-control-icon wb-time"></i>
		   					<input type="text" class="form-control timepickerSender text-center" id="SEND_DATE_TIME" value="" placeholder="발송시간" >
		   				</div>
	   				</div>
	   				<input type="hidden" id="SEND_DATE" name="SEND_DATE" value="">
   				</div>
			</div>
		</div>		
		<div class="form-group row">
			<div class="col-md-12">
				<h6>받으실 분들(수신 대상자 목록) <span class="font-weight-800 cyan-600" id="label_mbr_cnt"><c:if test="${not empty list_mbr}">총 ${fn:length(list_mbr)} 명</c:if></span></h6>
     			<c:forEach var="item" items="${list_mbr}">
	     			<script>
	     			{
						var rawObj = {
							value : '${item.MBR_ID}',
							label : '${item.MBR_HP} (${item.MBR_NM})',
						}
						rawMbrdata.push(rawObj);
	     			}
					</script>
				</c:forEach>
     			<input type="text" class="form-control tokenfield2_manual" name="chkedMbrIds" placeholder="" autocomplete="off" value="" >   
     			<input type="hidden" id="receive_mbr_cnt" value="${fn:length(list_mbr)}">   
			</div>
		</div>
		<div class="float-right">
        	<button type="button" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">보내기</button>
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
	
	//--------------------------------------
	//수신대상자목록 처리
	//--------------------------------------
	$('.tokenfield2_manual').tokenfield('setTokens', rawMbrdata);
	$('.tokenfield2_manual').on('tokenfield:removedtoken', function (event) {
		var cnt = $('.tokenfield2_manual').tokenfield('getTokens').length;
		$('#label_mbr_cnt').html('총 '+cnt+' 명');
		$('#receive_mbr_cnt').val(cnt);
	});
	
	chkTypingText($("#SMS_MENT_CONT").val());
});
$(".clk-radio-chk-resve").click(function() {
	var val = $(this).val();
	if(val == 5) {//예약발송
		$('#trg_send_date_form').slideDown().removeClass('hidden').css('display','');
	} else {
		$('#trg_send_date_form').slideUp(function(){
			$('#SEND_DATE_DT').val('');
			$('#SEND_DATE_TIME').val('');
			$(this).addClass('hide');
		});
	}
});
//--------------------------------------
//고정 멘트 선택
//--------------------------------------
$('.select-sms-ment').change(function() {
	var sn = $(this).val();
	if(sn=='') {
		$("#SMS_MENT_DTL_CD").val("");
		$("#SMS_MENT_CONT").val("");
		chkTypingText("");
		return;
	} else {
		$.ajax({
			type:"POST",
			url :"/adm/sms/ment/call.do",
			data:{SMS_MENT_SN:sn},
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				var obj = JSON.parse(data);
				$("#SMS_MENT_DTL_CD").val(obj.sms_ment_dtl_cd);
				$("#SMS_MENT_CONT").val(obj.sms_ment_cont);
				chkTypingText(data);
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
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
//--------------------------------------
$("#btn_submit").on("click", function() {
	
	//default
	if(Number($('#text_typing_cnt').val().replace(/,/gi, "")) > 2000) {
		alertify.alert('메세지 내용은 최대 2000 byte 를 넘을 수 없습니다.');
		return;
	}
	
	if(Number($('#receive_mbr_cnt').val()) <= 0) {
		alertify.alert('전송 하려면 연락처는 하나 이상 존재해야 합니다.');
		return;
	}
	
	var stat = $("input[name='STAT']:checked").val();
	if(stat == '5') {
		var send_date_dt = $('#SEND_DATE_DT').val();
		var send_date_time = $('#SEND_DATE_TIME').val();
		if(send_date_dt=='' || send_date_time=='') {
			alertify.alert('예약발송은 예약일자/시간을 지정해야 합니다.');
			return;
		}
		$("#SEND_DATE").val(send_date_dt+" "+send_date_time+":00");
	}
	
	$.ajax({
		type:"POST",
		url :'/adm/sms/send/write_act.do',
		data:$("#modal_action_form").serialize(),
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
						//document.listForm.submit();	
					});
				}
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
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);			
			alertify.alert('처리 중 오류가 발생하였습니다.<br>계속 될 경우 관리자에게 문의 해주세요.');
		}
	});
});
</script>