<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.bootstrap-select>.dropdown-toggle.bs-placeholder:active, 
.bootstrap-select>.dropdown-toggle.bs-placeholder:focus, 
.bootstrap-select>.dropdown-toggle.bs-placeholder:hover{
	color: #fff !important;
}
</style>
<form:form commandName="ctiCallHistoryVO" id="dialForm" name="dialForm" method="post">
<input type="hidden" id="HCALL_R_TEL" name="HCALL_R_TEL" value=""/>
<input type="hidden" id="HCALL_S_TEL" name="HCALL_S_TEL" value=""/>
<input type="hidden" id="CALL_CD" name="CALL_CD" value=""/>
<input type="hidden" id="EXTNO" name="EXTNO" value=""/>
<input type="hidden" id="HCALL_TYPE" name="HCALL_TYPE" value=""/>
<input type="hidden" id="HCALL_ST" name="HCALL_ST" value=""/>
<input type="hidden" id="HCALL_DT" name="HCALL_DT" value=""/>
<input type="hidden" id="HCALL_TIME" name="HCALL_TIME" value=""/>
<input type="hidden" id="HCALL_READY_TIME" name="HCALL_READY_TIME" value=""/>
<input type="hidden" id="REC_FILE_NM" name="REC_FILE_NM" value=""/>
<input type="hidden" id="CALL_LOG_MSG" name="CALL_LOG_MSG" value=""/>
<input type="hidden" id="MBR_HP" name="MBR_HP" value=""/>
<input type="hidden" id="HCALL_AGREE_1" name="HCALL_AGREE_1" value=""/>
<input type="hidden" id="HCALL_AGREE_2" name="HCALL_AGREE_2" value=""/>
<input type="hidden" id="HCALL_IVR" name="HCALL_IVR" value=""/>
<input type="hidden" id="HCALL_IVR_CD" name="HCALL_IVR_CD" value=""/>
<input type="hidden" id="HCALL_DTL_TYPE" name="HCALL_DTL_TYPE">
</form:form>


<!-- Site Action -->
<div class="site-action active">
	<button type="button" class="site-action-toggle btn-raised btn btn-primary btn-floating btn-dark dialpad-toggle-act"
		data-toggle="tooltip" data-original-title="다이얼화면">
		<i class="icon fa fa-phone animation-scale-up" aria-hidden="true"></i>
	</button>
</div>
<!-- End Site Action -->
<!-- dialpad -->
<div class="panel draggable-content-act panel-primary mb-0" id="trgDialPad" >
	
  	<div class="panel-heading panel-move">
		<h3 class="panel-title" style="padding: 10px 30px;">
			<span class="colorWhite vertical-align-middle bold font-size-16">상담 통화 <span id="trg_label_calling_number"></span></span>
			<div class="panel-actions">
				<a class="panel-action icon wb-help panel-helper" aria-hidden="true" data-toggle="tooltip" data-original-title="도움말"></a>
				<a class="panel-action icon wb-map panel-phome" aria-hidden="true" data-toggle="tooltip" data-original-title="원위치"></a>
				<a class="panel-action icon wb-close panel-close m-0" aria-hidden="true" data-toggle="tooltip" data-original-title="닫기"></a>
			</div>
			<!-- //도움말 레이어 영역 -->
			<div id="body_act_tab_layer_helper" style="position:relative;display:none;">
				<div class="popover bs-popover-top" style="max-width:430px;width:430px;left:-30px;top:-178px;">
					<div class="arrow" style="left:350px;"></div>
					<h3 class="popover-header cursor-pointer" >
						<span>도움말</span>
						<div class="float-right" id="btn_act_tab_close_helper">
							<span class="font-size-12 blue-grey-400">이 영역을 클릭하시면 팝업을 닫을 수 있습니다.</span>
						</div>
					</h3>
					<div class="popover-body p-10 w-p100 h-100 scroll-y scroll-x blue-grey-800" style="clear:both;position:relative;">
						* 관리자페이지 상단에 이름 클릭 후 두번재 CTI_ID 와 세번째 내선번호를 확인해주세요.
						<br>* 전화 플로팅 아이콘이 검정색이면 단말기와의 통신이 되지 않은 상태입니다.
						<br>* 연결에 실패하는 경우에는 상태창의 새로고침 버튼을 눌러 다시 시도해주세요.
						<br>* 상담통화 화면에 표시되는 시간은 로그인 부터 현재까지 연결 된 누적시간을 표시합니다.
						<br><span class="red-600">* 로그인 후 단말기(전화기)의 인터넷 연결이 끊기거나 종료 되었을 경우 화면을 새로고침 해주세요.</span>
						<br>* 보류(홀드)는 통화 상대방에게 보류 해제 전까지 배경음악을 깔고 수화기는 음소거 됩니다.
						<br>* 상담통화이력목록은 20초마다 자동갱신되며 자동갱신을 원하지 않으시면 OFF상태로 놓으시면 됩니다.
						<br>* 녹취 파일은 통화 종료 후 저장까지 수분(약 5분내)이 소요 될 수 있습니다.
						<br>* 녹취 파일 확인 페이지는 오디오 장치(스피커 또는 이어폰)가 없으면 작동 되지 않을 수 있습니다.
						<br>* 회원통합검색은 색상으로 구분되어 있습니다. ( <span class="green-600">초록색 : 통화중 상담신규등록</span> , <span class="cyan-600">파란색 : 직접신규등록</span> , <span class="orange-600">주황색 : 상담수정</span> )
						<br>* 통화중에 작성중인 상담(통화중 상담신규등록)건은 이력관리를 위해 통화가 종료 된 후 저장 할 수 있습니다. 
						<br>* 상담원 상태 목록이 갱신되지 않는 경우 일시적 접속오류 일 수 있으므로 페이지를 새로고침 하거나 재연결을 위한 새로고침 아이콘을 클릭해주세요.  
					</div>
				</div>
			</div>
			<!-- //End of 도움말 레이어 영역 -->
		</h3>		
	</div>
	<div class="panel-body" style="padding: 0px !important;">
		<div class="card border border-primary">
		
			<div class="card-text input-group mt-15 pb-15" style="border-bottom: 1px solid #dcdcdc;">
				<div class="col-lg-4">
 					<select class="form-control selectpicker_manual sel-call-status" data-style="btn-outline btn-primary" >
 						<option value="call-resume">상담대기</option>
 						<option value="call-lock">후처리</option>
 						<option value="call-pause">자리비움</option> 
					</select>
	            </div>
	            
	            <div class="col-lg-8 text-right form-control-plaintext">
					<span id="cti_conn_status">...</span>
					<div class="cti-conn-timer-str" style="display:none;">
						<span class="ml-5">|</span>
						<span class="ml-5">
							<span id="ex_hour">00</span>:<span id="ex_min">00</span>:<span id="ex_sec">00</span>
						</span>
					</div>		    		
	            </div>
	      		
			</div>
			
			<div class="card-text input-group mt-20" >
				<div class="input-group col-lg-12">
					<input type="number" class="form-control border-primary" id="trg_input_call_number" placeholder="전화번호,내선번호를 입력하세요." style="display: inline;" autocomplete="off" value="">
					<span class="input-group-append">
						<button type="button" class="btn btn-primary clk_search_dialpad_number"><i class="icon wb-search" aria-hidden="true"></i> 검색</button>	
					</span>
				</div>
			</div>
			
			<div class="card-text input-group mt-20 pb-15 dialpadMenuBtn">
				<div class="input-group">
					<div class="col-lg-6">
						<button type="button" id="btnCtiCallSend" class="btn btn-block btn-outline-default btn-tagged" onclick="ctiCallSend()">
							<span class="btn-tag bg-blue-grey-100"><img src="/common/img/dialpadBtn1.png" class="dialpadBtnImg"/></i></span>전화걸기</button>
					</div>
					<div class="col-lg-6"><button type="button" id="btnCtiCallReceive" class="btn btn-block btn-outline-default btn-tagged" onclick="ctiCallReceive()">
							<span class="btn-tag bg-blue-grey-100"><img src="/common/img/dialpadBtn2.png" class="dialpadBtnImg"/></i></span>전화받기</button>
					</div>
				</div>
				
				<div class="input-group mt-10">
					<div class="col-lg-6"><button type="button" id="btnCtiCallTransfer" class="btn btn-block btn-outline-default btn-tagged" onclick="ctiCallTransfer()">
							<span class="btn-tag bg-blue-grey-100"><img src="/common/img/dialpadBtn5.png" class="dialpadBtnImg"/></i></span>통화중 전달하기</button>
					</div>
					<div class="col-lg-6"><button type="button" id="btnCtiCallCut" class="btn btn-block btn-outline-default btn-tagged" onclick="ctiCallCut()">
							<span class="btn-tag bg-blue-grey-100"><img src="/common/img/dialpadBtn4.png" class="dialpadBtnImg"/></i></span>전화끊기</button>
					</div>
				</div>
				
				<div class="input-group mt-10">
					<div class="col-lg-6"><button type="button" id="btnCtiCallHold" class="btn btn-block btn-outline-default btn-tagged" onclick="ctiCallHold()">
							<span class="btn-tag bg-blue-grey-100"><img src="/common/img/dialpadBtn6.png" class="dialpadBtnImg"/></i></span>보류(홀드)</button>
					</div>
					<div class="col-lg-6"><button type="button" id="btnCtiCallUnHold" class="btn btn-block btn-outline-default btn-tagged" onclick="ctiCallUnHold()">
							<span class="btn-tag bg-blue-grey-100"><img src="/common/img/dialpadBtn3.png" class="dialpadBtnImg"/></i></span>보류(홀드)해제</button>
					</div>
				</div>
				
				
				<div class="input-group mt-10">
				
					<div class="col-lg-6"><button type="button" id="btnCtiCallUnHold" class="btn btn-block btn-outline-default btn-tagged" onclick="ctiCallTransfer_S()">
							<span class="btn-tag bg-blue-grey-100"><img src="/common/img/dialpadBtn5.png" class="dialpadBtnImg"/></i></span>성희롱 안내</button>
					</div>				
					<div class="col-lg-6"><button type="button" id="btnCtiCallHold" class="btn btn-block btn-outline-default btn-tagged" onclick="ctiCallTransfer_V()">
							<span class="btn-tag bg-blue-grey-100"><img src="/common/img/dialpadBtn5.png" class="dialpadBtnImg"/></i></span>폭언욕설 안내</button>
					</div>

				</div>				
				
			</div>

		</div>					
	</div>
</div>
<!-- End dialpad -->

<style>
#toast-container {
	min-width:500px;
}
</style>
<script>

//------------------------------------------------------
// 온라인 경과시간 타이머
//------------------------------------------------------
var timer = null;
function fnStopWatch($h,$m,$s ,h,m,s){
	var hour = h;
	var min = m;
	var sec = s;
	var $h = $h;
	var $m = $m;
	var $s = $s;
	var tmep = "";
	timer = setInterval(function(){
   		//밀리초증가
  		tmep = "00"; mSec = 0; sec++;//초증가
	 	if(sec < 10) { tmep = "0"+sec; } 
	 	else if(sec > 59) { tmep = "00"; sec = 0; min++; //분증가
	    	if(min < 10) { tmep = "0"+min; } 
	    	else if(min > 59) { tmep = "00"; min = 0; hour++;//시간증가
	       		if(hour < 10) { tmep = "0"+hour; } else if(hour > 59) { tmep = "00"; hour = 0; } else { tmep = hour; } $h.text(tmep);
	       	} else { tmep = min; }
	    	$m.text(tmep);
	 	} else { tmep = sec; }
	 	$s.text(tmep);
	},1000);
}
//------------------------------------------------------
// toastr custom lib
//------------------------------------------------------
//기타
var toastr_call_alert = null;
function notification_show_call_alert(msg_title,msg_content_html) {
	//error:pink info:blue success:green warning:orange
	toastr_call_alert = toastr.error(msg_content_html,msg_title,{
			tapToDismiss: false,
			closeButton: true,
			debug: false,
			newestOnTop: true,
			progressBar: false,
			positionClass: "toast-bottom-left",//""toast-top-center","toast-top-right","toast-top-full-width"
			preventDuplicates: false,
			onclick: true,
			showDuration: "300",
			hideDuration: "300",
			timeOut: "0",
			extendedTimeOut: "0",
			showEasing: "swing",
			hideEasing: "linear",
			showMethod: "slideDown",//fadeIn
			hideMethod: "fadeOut",
			allowHtml: true,
			onShown:function(){
				
			},
			onHidden:function(){
				
			},
			onclick:function(){
				
			},
			onCloseClick:function(){
				
			}
		});
}
function notification_hide_call_alert() {
	if(toastr_call_alert!=null)
		toastr.clear(toastr_call_alert);
}
//전화용
var toastr_call_state = null;
function notification_show_call_state(msg_title,msg_content_html) {
	//error:pink info:blue success:green warning:orange
	toastr_call_state = toastr.info(msg_content_html,msg_title,{
			tapToDismiss: false,
			closeButton: true,
			debug: false,
			newestOnTop: true,
			progressBar: false,
			positionClass: "toast-bottom-left",//""toast-top-center","toast-top-right","toast-top-full-width"
			preventDuplicates: false,
			onclick: true,
			showDuration: "300",
			hideDuration: "300",
			timeOut: "0",
			extendedTimeOut: "0",
			showEasing: "swing",
			hideEasing: "linear",
			showMethod: "slideDown",//fadeIn
			hideMethod: "fadeOut",
			allowHtml: true,
			onShown:function(){
				
			},
			onHidden:function(){
				
			},
			onclick:function(){
				
			},
			onCloseClick:function(){
				
			}
		});
}
function notification_hide_call_state() {
	if(toastr_call_state!=null)
		toastr.clear(toastr_call_state);
}
//정보용
var toastr_call_info = null;
function notification_show_call_info(msg_title,msg_content_html) {
	toastr_call_info = toastr.success(msg_content_html,msg_title,{
			tapToDismiss: false,
			closeButton: true,
			debug: false,
			newestOnTop: true,
			progressBar: false,
			positionClass: "toast-bottom-left",
			preventDuplicates: false,
			onclick: false,
			showDuration: "300",
			hideDuration: "300",
			timeOut: "0",
			extendedTimeOut: "0",
			showEasing: "swing",
			hideEasing: "linear",
			showMethod: "slideDown",//fadeIn
			hideMethod: "fadeOut",
			allowHtml: true,
		});
}
function notification_hide_call_info() {
	if(toastr_call_info!=null)
		toastr.clear(toastr_call_info);
}
//알림용
function notification_show_alert(msg_title,msg_content_html) {
	toastr.warning(msg_content_html,msg_title,{
			tapToDismiss: false,
			closeButton: false,
			debug: false,
			newestOnTop: true,
			progressBar: true,
			positionClass: "toast-bottom-left",
			preventDuplicates: false,
			onclick: false,
			showDuration: "300",
			hideDuration: "300",
			timeOut: "10000",
			extendedTimeOut: "0",
			showEasing: "swing",
			hideEasing: "linear",
			showMethod: "slideDown",
			hideMethod: "fadeOut",
			allowHtml: true,
		});
}
//------------------------------------------------------
// 회원통합검색
//------------------------------------------------------
$(document).off("click", ".btn-act-dial-call-number");
$(document).on("click",".btn-act-dial-call-number",function() {
	var number = $(this).attr('data-call-number');
	$('#trg_input_call_number').val(number);
	alertify.confirm("전화 연결을 진행하시겠습니까?",function() {
		ctiCallSend();
	});	
	$('#trgDialPad').show();
});
function saveCallLog(obj) {
	//console.log("saveCallLog ..");
	//console.log("saveCallLog:"+JSON.stringify(obj));
	//개인정보동의여부
	/*
	if(obj.isUsedCallAgreeStatus) {
		if(obj.iscallAgree1) {
			$('#HCALL_AGREE_1').val('Y');
		} else {
			$('#HCALL_AGREE_1').val('N');	
		}
		if(obj.iscallAgree2) {
			$('#HCALL_AGREE_2').val('Y');
		} else {
			$('#HCALL_AGREE_2').val('N');
		}
	}
	*/
	//End of 개인정보동의여부	
	var form = document.getElementById('dialForm');	
	if(obj.isreceivecall) {
		form.CALL_CD.value = obj.callkey;
		form.HCALL_R_TEL.value = obj.ctitelno;//받는분
		form.HCALL_S_TEL.value = obj.callnum;//거신분
		form.HCALL_TYPE.value = '수신';
		form.HCALL_DTL_TYPE.value = '수신';
	} else if(obj.issendcall) {
		form.CALL_CD.value = obj.callkey;
		form.HCALL_R_TEL.value = obj.callnum;//받는분
		form.HCALL_S_TEL.value = obj.ctitelno;//거신분
		form.HCALL_TYPE.value = '발신';
		form.HCALL_DTL_TYPE.value = '발신';
	} else if(obj.isretransfercall) {//전달건
		form.CALL_CD.value = obj.callkey;
		form.HCALL_R_TEL.value = obj.ctitelno;//받는분
		form.HCALL_S_TEL.value = obj.callnum;//거신분
		form.HCALL_TYPE.value = '수신';
		form.HCALL_DTL_TYPE.value = '전달수신';
	} else if(obj.istransfercall) {	// 교환,전달(발신하기)
		form.CALL_CD.value = obj.callkey;
		form.HCALL_R_TEL.value = obj.ctitelno;//받는분
		form.HCALL_S_TEL.value = obj.callnum;//거신분
		form.HCALL_TYPE.value = '발신';
		form.HCALL_DTL_TYPE.value = '전달발신';
	} else {
		if(obj.callkey) form.CALL_CD.value = obj.callkey;
		if(obj.ctitelno) form.HCALL_R_TEL.value = obj.ctitelno;//받는분
		if(obj.callnum) form.HCALL_S_TEL.value = obj.callnum;//거신분
		if(obj.callnum.length == 4){
			form.HCALL_TYPE.value = '발신';
			form.HCALL_DTL_TYPE.value = '발신';
		} else {  
			form.HCALL_TYPE.value = '수신';
			form.HCALL_DTL_TYPE.value = '수신';
		}
	}

	form.HCALL_IVR.value = obj.ivrinfolabel;
	form.HCALL_IVR_CD.value = obj.ivrinfocd;
	//console.log("HCALL_IVR:"+obj.ivrinfolabel);
	//console.log("HCALL_IVR_CD:"+obj.ivrinfocd);
	
	form.EXTNO.value = obj.ctitelno;
	if(obj.iscallcatch) {
		form.HCALL_ST.value = obj.callst+''+obj.label_sub;//응답여부(응답,취소,부재중)
		form.REC_FILE_NM.value = 'recId='+ctiObj.vlgRecordID+'&recTableName='+ctiObj.vlgTableName;
	} else {
		form.HCALL_ST.value = obj.callst;//응답여부(응답,취소,부재중)
	}
	form.MBR_HP.value = obj.callnum;//대상번호
	form.HCALL_DT.value = obj.calldt;
	form.HCALL_TIME.value = obj.callTime;
	form.HCALL_READY_TIME.value = obj.bellTime;
	
	$("#trg_dialpad_phone_num").html(obj.callnum);
	$("#trg_dialpad_key_num").html(obj.callkey);	
	//$("#trg_dialpad_last_call_info").show();//마지막 통화 정보 출력
	form.CALL_LOG_MSG.value = JSON.stringify(obj);

	$.ajax({
		
		type:"POST",
		url :"/cti/callhstry/write_act.do",
		data:$('#dialForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: false,
		
		success: function(data, status, xhr) {
			if(data.error == '1') {
				
			} else {
				fn_egov_link_page_callhstry(1);
			}	
			form.HCALL_R_TEL.value = '';
			form.HCALL_S_TEL.value = '';	
			form.CALL_CD.value = '';	
			form.EXTNO.value = '';
			form.HCALL_TYPE.value = '';
			form.HCALL_DTL_TYPE.value = '';
			form.HCALL_ST.value = '';
			form.HCALL_DT.value = '';
			form.HCALL_TIME.value = '';
			form.HCALL_READY_TIME.value = '';
			form.REC_FILE_NM.value = '';
			form.HCALL_AGREE_1.value = '';
			form.HCALL_AGREE_2.value = '';
			form.CALL_LOG_MSG.value = '';
			form.HCALL_IVR.value = '';
			form.HCALL_IVR_CD.value = '';
		},
		beforeSend : function() {
					
		},
		complete : function() {			
			//console.log('complete');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
		}
	});
	console.log("saveCallLog end ..")
}
//------------------------------------------------------
// 다이얼 div
//------------------------------------------------------
$(".draggable-content-act").draggable({
	handle: ".panel-move",
	stop: function() {
		return $(this).css({
			height: 'auto',
			bottom: 'unset'
	    });
	},
	drag: function( event, ui){
		//드래그 오버 방지
		var w = 0;
		var h = 0;
	  	if(document.body.clientHeight == 0) {
	  		w = window.innerWidth;
	  		h = window.innerHeight;
	  	} else {
	  		w = document.body.clientWidth;
	  		h = document.body.clientHeight;
	  	}
		if(ui.position.top < 0) ui.position.top = 0;
		if(ui.position.top > h-40) ui.position.top = h-40;
		if(ui.position.left < -200) ui.position.left = -200;
		if(ui.position.left > w-100) ui.position.left = w-100;
  	}
});
$(".clk_search_dialpad_number").click(function() {
	var callnum = $('#trg_input_call_number').val();
	if(callnum=='') {
		alertify.alert('검색 할 연락처를 입력해주세요.'); 
		return;
	}
	var idkey = getAdmPublicPanelLayerIdkey();
	$.ajax({
		type:"POST",
		url :"/cti/main/list_total.do",
		data:{
			MBR_HP:callnum,
			CALL_CD:'',
			HCALL_SN:'',
			CUSTOM_UNIQ_KEY:idkey,
		},
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			//$("#eduAdmEduPublicModal").html(data);
			//$("#eduAdmEduPublicModal").modal('show');
			newAdmPublicPanelLayer(idkey,data);			
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	}); 
});
$(".panel-phome").click(function() {
	$(".draggable-content-act").css("bottom","120px").css("right","18px").css("top","").css("left",""); 
});
$(".panel-close").click(function() {
	//$(".draggable-content-act").css("bottom","120px").css("right","18px").css("top","").css("left",""); 
	clearInterval(timer);
	$(".draggable-content-act").toggle();
});
$('.panel-helper').click(function(){
	$('#body_act_tab_layer_helper').show();
});
$('#btn_act_tab_close_helper').click(function(){
	$('#body_act_tab_layer_helper').hide();
});
$(".dialpad-toggle-act").click(function() {
	//$(".draggable-content-act").css("bottom","120px").css("right","18px").css("top","").css("left","");
	$(".draggable-content-act").toggle();
});
$(".sel-call-status").change(function(){
	var val = $(this).val();
	if(val == 'call-resume') {
		Avail();
	} else if(val == 'call-lock') {
		Work('0');
	} else if(val == 'call-pause') {
		Unavail('0');
	}
});
function updateCallstatus() {
	var val = $(".sel-call-status option:selected").val();
	if(val == 'call-resume') {
		Avail();
	} else if(val == 'call-lock') {
		Work('0');
	} else if(val == 'call-pause') {
		Unavail('0');
	}
}
</script>
<%
String localWebSocketUrl = request.getServerName()+":"+request.getServerPort();//request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
String localWebSocketScheme = "ws";
if(request.getScheme().contains("https")) {
	localWebSocketScheme = "wss";
}
localWebSocketUrl = localWebSocketScheme+"://"+localWebSocketUrl;
%>
<c:set var="local_ws_url" value="<%=localWebSocketUrl%>"/>
<!-- //telper_api// -->
<script>var MBR_ID = '${MBR_ID}'</script>
<script>var cti_call_api_url = '${cti_call_api_url}';</script>
<script>var cti_rec_api_url = '${cti_rec_api_url}';</script>
<script>var local_ws_url = '${local_ws_url}';</script>
<script>
var CTI_BACK_UP_KEY = '<%=(String)session.getAttribute("ctiConnectKey")%>';
var CTI_ID = '${staff_member_info.CTI_ID}';
var CTI_TEL_NO = '${staff_member_info.CTI_TEL_NO}';
</script>
<!-- //CTI IVR 정보 현행화 -->
<script>var CTI_IVR_INFO_DATA = [];</script>
<c:if test="${not empty list_category_gubun_1}">
	<c:forEach var="ivritem" items="${list_category_gubun_1}">
		<script>
		CTI_IVR_INFO_DATA.push({
				CD : '${ivritem.CD_ID}',
				NM : '${ivritem.CD_NM}',
		});
		</script>
	</c:forEach>
</c:if>
<!-- //End of CTI IVR 정보 현행화 -->
<!-- //CTI 상담원 정보 갱신용 -->
<script>
var ctiwswk;
var isCtiwswkRefresh = false;
function ctiwsOpenConnectSocket() {
    if(typeof(Worker) == "undefined") {
		ctiwsResponse("ctiws Sorry, your browser does not support Web Workers..");
		return;
	}		
	if(typeof(ctiwswk) == "undefined") {
		ctiwswk = new Worker("/telper_api/ctiws.js");
	}
	ctiwsSendMessage('req','connect',local_ws_url);
	ctiwswk.onmessage=function(event){
    	ctiwsResponse(event.data);
    };
    ctiwswk.onclose=function(event){
    	ctiwsResponse("ctiws closed..");
    }
}
ctiwsOpenConnectSocket();
function ctiwsSendMessage(con,act,msg){
	var obj = {
	    	'mbr_id':MBR_ID,
	    	'cti_id':CTI_ID,
	    	'cti_telno':CTI_TEL_NO,
	    	'sid':'',
	    	'controll':con,
	    	'action':act,
	    	'msg':msg,
	    };
	ctiwswk.postMessage(obj);
}
function ctiwsResponse(data){
	//console.log(data);
	if(data.controll == 'call') {
		if(data.action == 'online') {
			$('#ctiManagerUiStatusHeader'+data.mbr_id).removeClass('bg-green-600 bg-red-600 bg-blue-grey-600 bg-orange-600').addClass('bg-green-600');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).removeClass('blue-grey-800 white').addClass('white');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).html('대기중');
		} else if(data.action == 'offline') {
			$('#ctiManagerUiStatusHeader'+data.mbr_id).removeClass('bg-green-600 bg-red-600 bg-blue-grey-600 bg-orange-600');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).removeClass('blue-grey-800 white').addClass('blue-grey-800');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).html('로그아웃');
		} else if(data.action == 'ing') {
			$('#ctiManagerUiStatusHeader'+data.mbr_id).removeClass('bg-green-600 bg-red-600 bg-blue-grey-600 bg-orange-600').addClass('bg-red-600');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).removeClass('blue-grey-800 white').addClass('white');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).html('상담중');
		} else if(data.action == 'end') {
			$('#ctiManagerUiStatusHeader'+data.mbr_id).removeClass('bg-green-600 bg-red-600 bg-blue-grey-600 bg-orange-600').addClass('bg-green-600');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).removeClass('blue-grey-800 white').addClass('white');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).html('대기중');
		} else if(data.action == 'lock') {
			$('#ctiManagerUiStatusHeader'+data.mbr_id).removeClass('bg-green-600 bg-red-600 bg-blue-grey-600 bg-orange-600').addClass('bg-blue-grey-600');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).removeClass('blue-grey-800 white').addClass('white');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).html('자리비움');
		} else if(data.action == 'work') {
			$('#ctiManagerUiStatusHeader'+data.mbr_id).removeClass('bg-green-600 bg-red-600 bg-blue-grey-600 bg-orange-600').addClass('bg-orange-600');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).removeClass('blue-grey-800 white').addClass('white');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).html('후처리');
		} else if(data.action == 'unlock') {
			$('#ctiManagerUiStatusHeader'+data.mbr_id).removeClass('bg-green-600 bg-red-600 bg-blue-grey-600 bg-orange-600').addClass('bg-green-600');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).removeClass('blue-grey-800 white').addClass('white');
			$('#ctiManagerUiStatusLabel'+data.mbr_id).html('대기중');
		}
	} else if(data.controll == 'disconnected' || data.controll == 'error') {
		$('#ctiManagerUiStatusRefresh').show();
	} else if(data.controll == 'connected') {
		$('#ctiManagerUiStatusRefresh').hide();
		if(isCtiwswkRefresh) {
			ctiwsCallOn();
			isCtiwswkRefresh = false;	
		}
	}
}
function ctiwsReConnect() {
	isCtiwswkRefresh = true;
	ctiwsOpenConnectSocket();
}
function ctiwsCalling() {
	ctiwsSendMessage('call','ing','');
	fn_today_main_call_info();
}
function ctiwsCallend() {
	ctiwsSendMessage('call','end','');
	fn_today_main_call_info();
}
function ctiwsLock() {
	ctiwsSendMessage('call','lock','');
	fn_today_main_call_info();
}
function ctiwsWork() {
	ctiwsSendMessage('call','work','');
	fn_today_main_call_info();
}
function ctiwsUnLock() {
	ctiwsSendMessage('call','unlock','');
	fn_today_main_call_info();
}
function ctiwsCallOn() {
	ctiwsSendMessage('call','online','');
	fn_today_main_call_info();
}
function ctiwsCallOff() {
	ctiwsSendMessage('call','offline','');
	fn_today_main_call_info();
}
</script>
<!-- //End of CTI 상담원 정보 갱신용 -->
<script>
//응답 : 녹취 api
function ctiVlgResError(msg) {
	console.log('cti ctiVlgResError : ' + msg);	
}
function ctiVlgResConnected() {
	console.log('cti ctiVlgResConnected .. ');
	fn_vlgLogin();
}
function ctiVlgResOnReady() {
	console.log('cti ctiVlgResOnReady .. ');
}
function ctiVlgResOnReadyFail() {
	console.log('cti ctiVlgResOnReadyFail .. ');
}
function ctiVlgResRecording(obj) {
	console.log('cti ctiVlgResRecording .. ');
	//console.log(obj);
}
//응답
function ctiResError(msg) {
	console.log('cti ctiResError : ' + msg);	
}
function ctiResConnStatus(str,isNoti) {
	if(isNoti) {
		$('.dialpad-toggle-act').removeClass('btn-dark').addClass('btn-dark');
		$('.cti-conn-timer-str').hide();	
		
		$('#btnCtiCallSend').addClass('disabled');
		$('#btnCtiCallReceive').addClass('disabled');
		$('#btnCtiCallTransfer').addClass('disabled');
		$('#btnCtiCallCut').addClass('disabled');
		$('#btnCtiCallHold').addClass('disabled');
		$('#btnCtiCallUnHold').addClass('disabled');
		
		$(".sel-call-status").attr('disabled','disabled').selectpicker('refresh');
				
	} else {
		$('.dialpad-toggle-act').removeClass('btn-dark');
		$('.cti-conn-timer-str').css('display','inline-block');	
		
		$('#btnCtiCallSend').removeClass('disabled');
		$('#btnCtiCallReceive').removeClass('disabled');
		$('#btnCtiCallTransfer').removeClass('disabled');
		$('#btnCtiCallCut').removeClass('disabled');
		$('#btnCtiCallHold').removeClass('disabled');
		$('#btnCtiCallUnHold').removeClass('disabled');
		
		$(".sel-call-status").removeAttr('disabled').selectpicker('refresh');
		
	}
	$('#cti_conn_status').html(str);
}
function ctiResConnected() {
	console.log('cti Connected .. ');
	notification_show_alert('알림','CTI 서버와 연결되었습니다.');
	vlgConnectServer();//녹취 서버 접속
	Login();	
}
function ctiResDisConnected() {
	console.log('cti disConnected .. ');
	fn_vlgLogout();//녹취 서버 로그아웃
	vlgDisconnectServer();//녹취 서버 종료
	clearInterval(timer);
	notification_show_alert('알림','CTI 서버와 연결이 종료되었습니다.');
	$('.dialpad-toggle-act').removeClass('btn-dark');
	ctiwsCallOff();
}
function ctiResOnReady() {
	console.log('cti ready ok .. ');
	fnStopWatch($("#ex_hour"),$("#ex_min"),$("#ex_sec") ,$("#ex_hour").text(), $("#ex_min").text(),$("#ex_sec").text());
	notification_show_alert('알림','현재 콜 대기중입니다.');
	$('#btnCtiCallSend').addClass('btn-info');
	updateCallstatus();
	ctiwsCallOn();
}
function ctiResCallHold(obj) {
	console.log('cti ctiResCallHold .. ');
	$('#btnCtiCallHold').removeClass('btn-warning').addClass('disabled');
	$('#btnCtiCallHold > .btn-tag').eq(0).addClass('bg-yellow-300').removeClass('bg-blue-grey-100');
	$('#btnCtiCallUnHold').addClass('btn-warning');
}
function ctiResCallUnHold(obj) {
	console.log('cti ctiResCallUnHold .. ');
	$('#btnCtiCallHold').addClass('btn-warning').removeClass('disabled');
	$('#btnCtiCallHold > .btn-tag').eq(0).removeClass('bg-yellow-300').addClass('bg-blue-grey-100');
	$('#btnCtiCallUnHold').removeClass('btn-warning');
}
function ctiResCallResume(obj) {
	console.log('cti ctiResCallResume .. ');
	$('.dialpad-toggle-act').removeClass('btn-warning');
	notification_hide_call_alert();
	//notification_show_call_alert('상담대기','수신전화를 받을 수 있는 상태가 되었습니다.(클릭해서 닫기)');
	ctiwsUnLock();
}
function ctiResCallPause(obj) {
	console.log('cti ctiResCallPause .. ');
	$('.dialpad-toggle-act').addClass('btn-warning');
	notification_hide_call_alert();
	notification_show_call_alert('자리비움','수신전화를 받을 수 없는 상태입니다.<br/>(내선전화 또는 다이렉트콜을 제외 한 외부 수신건에 한함)<br>* 발신은 가능하며 이미 배정된 수신건은 해제되지 않습니다.(클릭해서 닫기))');
	ctiwsLock();
}
function ctiResCallPauseFail() {
	alertify.alert('CTI서버 또는 로그인이 정상 상태인지 확인해주세요.');	
}
function ctiResCallWork(obj) {
	console.log('cti ctiResCallWork .. ');
	//console.log(obj);
	ctiwsWork();
}
function ctiResCallWorkFail() {
	alertify.alert('CTI서버 또는 로그인이 정상 상태인지 확인해주세요.!');
}
function ctiResCallSend(obj) {
	console.log('cti ctiResCallSend .. ');
	notification_show_call_state('전화걸기',obj.callnum + ' 번호로 전화를 거는 중입니다.<br><div id="ctiCallingMbrTotalInfoBodySender" data-call-number="'+obj.callnum+'" class="pl-10" style="width:130%;"><div class="loader vertical-align-middle loader-ellipsis font-size-12"></div></div>');
	$('#btnCtiCallSend').removeClass('btn-info');
	$('#btnCtiCallCut').addClass('btn-danger');
	fn_calling_mbr_total_info('Sender');
}
function ctiResCallReceive(obj) {
	console.log('cti ctiResCallReceive .. ');
	//console.log("수신전화데이터확인:"+JSON.stringify(obj));
	var call_number = obj.callnum;
	
	//고광훈 추가 - 발신자 정보를 못받아와서 임의로 추가 
	var call_name ='';
	$.ajax({
		type:"POST",
		url :"/cti/callhstry/default_info.do",
		data:{
			MBR_HP : call_number,
		},
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		beforeSend : function() {
   			console.log('before!');
   		},
		success: function(data, status, xhr) {

			var json = JSON.parse(data.rawdata);
			
			console.log("수신자정보:"+JSON.stringify(json));
			
			if(json.mbr_ncnm!=''){
				
				call_name = json.mbr_nm+' / '+json.mbr_ncnm;
				
				console.log("call_name1:"+call_name);
				
			}else if(json.mbr_ncnm==''&&json.mbr_nm==''){
				
				call_name = '등록정보없음';
				
				console.log("call_name2:"+call_name);
				
				
			}else{
				
				call_name = json.mbr_nm;
				
				console.log("call_name3:"+call_name);
				
			}
			
			if(data.error == '1') {
			
			} else {

			}
		},
		complete : function() {
			//고광훈 추가 - 수신자 전화 로직
			
			notification_show_call_state('전화받기',obj.callnum + ' 번호로 전화가 왔습니다.( '+call_name+' )<br><div id="ctiCallingMbrTotalInfoBody'+obj.callkey+'" data-call-number="'+obj.callnum+'" class="pl-10" style="width:130%;"><div class="loader vertical-align-middle loader-ellipsis font-size-12"></div></div>');
			//notification_show_call_state('전화받기',obj.callnum + ' 번호로 전화가 왔습니다.<br><div id="ctiCallingMbrTotalInfoBody'+obj.callkey+'" data-call-number="'+obj.callnum+'" class="pl-10" style="width:130%;"><div class="loader vertical-align-middle loader-ellipsis font-size-12"></div></div>');
			//ui update
			$('#btnCtiCallSend').removeClass('btn-info');
			$('#btnCtiCallReceive').addClass('btn-success');
			//End of ui update
			$('#trgDialPad').show();
			fn_calling_mbr_total_info(obj.callkey);				
			
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			console.log('error!');
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	}); 	
	
	
	

	
}
function ctiResCallBell(obj) {
	console.log('cti ctiResCallBell .. ');
	console.log()
	//notification_show_call_state('전화받기',obj.callnum + ' 번호로 전화가 왔습니다.( '+obj.ivrinfolabel+' )<br><div id="ctiCallingMbrTotalInfoBody'+obj.callkey+'" data-call-number="'+obj.callnum+'" class="pl-10" style="width:130%;"><div class="loader vertical-align-middle loader-ellipsis font-size-12"></div></div>');
	notification_show_call_state('전화받기',obj.callnum + ' 번호로 전화가 왔습니다.()<br><div id="ctiCallingMbrTotalInfoBody'+obj.callkey+'" data-call-number="'+obj.callnum+'" class="pl-10" style="width:130%;"><div class="loader vertical-align-middle loader-ellipsis font-size-12"></div></div>');
	//ui update
	$('#btnCtiCallSend').removeClass('btn-info');
	$('#btnCtiCallReceive').addClass('btn-success');
	//End of ui update
	$('#trgDialPad').show();
	fn_calling_mbr_total_info(obj.callkey);
}
function ctiResCalling(obj) {
	console.log('cti ctiResCalling .. ');	
	notification_hide_call_state();		
	notification_hide_call_info();
	var ivrinfolabel = '';
	if(obj.ivrinfolabel!='') {
		ivrinfolabel = '<br/>( '+obj.ivrinfolabel+' )';
	}
	notification_show_call_info('통화중',obj.callnum + ' 통화중입니다.<br/>해당 사용자의 통합정보를 보시려면 아래 버튼을 클릭하세요.'+ivrinfolabel+'<br/><br/><button class="btn btn-dark btn-act-dial-tot-search" type="button" data-call-number="'+obj.callnum+'" data-call-cd="'+obj.callkey+'" data-call-sn="" data-call-ivr-cd="'+obj.ivrinfocd+'">회원통합검색</button>');
	$('#trg_label_calling_number').html('( 통화중 : '+obj.callnum+' )');
	$('#trg_input_call_number').val('');
	//ui update
	$('#btnCtiCallReceive').removeClass('btn-success');
	$('#btnCtiCallTransfer').addClass('btn-warning');
	$('#btnCtiCallCut').addClass('btn-danger');
	$('#btnCtiCallHold').addClass('btn-warning');
	$('.dialpad-toggle-act').addClass('btn-success');
	//End of ui update
	ctiwsCalling();
}
function ctiResCallEnd(obj) {
	console.log('cti ctiResCallEnd .. ');
	console.log("ctiResCallEnd:"+JSON.stringify(obj));
	//console.log(obj);
	notification_hide_call_state();
	notification_hide_call_info();
	notification_show_alert(obj.label+' '+obj.label_sub,obj.callnum);
	//save db
	if(obj.iserror) {
		console.log('정상적인 완료건이 아니므로 저장 안함.');
		alertify.alert('정상적인 완료건이 아니므로 서버에 전송하지 못했습니다.('+obj.actlabel+')'); 
	} else {
		console.log('정상적인 완료.');
	}
	saveCallLog(obj);
	ctiCallClear();	
	//End of save db
	//ui update
	$('#trg_label_calling_number').html('');
	$('#btnCtiCallSend').addClass('btn-info');
	$('#btnCtiCallReceive').removeClass('btn-success');
	$('#btnCtiCallTransfer').removeClass('btn-warning');
	$('#btnCtiCallCut').removeClass('btn-danger');
	$('#btnCtiCallHold').removeClass('btn-warning').removeClass('disabled');
	$('#btnCtiCallHold > .btn-tag').eq(0).removeClass('bg-yellow-300').removeClass('bg-blue-grey-100').addClass('bg-blue-grey-100');
	$('#btnCtiCallUnHold').removeClass('btn-warning');
	$('.dialpad-toggle-act').removeClass('btn-warning').removeClass('btn-success');
	//End of ui update
	//set status
	updateCallstatus();
	//End of set status
	ctiwsCallend();
	//init cti work
	if(obj.iscallcatch) {
		ctiCallDataRecordReady();
	}		
	//End of init cti work
}
//요청
function ctiCallSend() {
	console.log('cti ctiCallSend .. ');
	var callnum = $('#trg_input_call_number').val();
	if(callnum=='') {
		alertify.alert('연결 할 번호를 입력해주세요.'); 
		return;
	}
	MakeCall(callnum);
}
function ctiCallReceive() {
	console.log('cti ctiCallReceive .. ');
	AnswerCall();
}
function ctiCallCut() {
	console.log('cti ctiCallCut .. ');
	DisconnectCall();
}
function ctiCallTransfer() {
	console.log('cti ctiCallTransfer .. ');
	var callnum = $('#trg_input_call_number').val();
	if(callnum=='') {
		alertify.alert('연결 할 번호를 입력해주세요.'); 
		return;
	}
	SingleStepTransferCall(callnum);
}


function ctiCallTransfer_S() {
	console.log('cti ctiCallTransfer_S .. ');
	var callnum = '2109';
	SingleStepTransferCall(callnum);
}

function ctiCallTransfer_V() {
	console.log('cti ctiCallTransfer_V .. ');
	var callnum = '2110';
	SingleStepTransferCall(callnum);
}



function ctiCallHold() {
	console.log('cti ctiCallHold .. ');
	HoldCall();
}
function ctiCallUnHold() {
	console.log('cti ctiCallUnHold .. ');
	RetrieveCall();
}
function ctiCallClear() {
	console.log('cti ctiCallClear .. ');
	$('#trg_input_call_number').val('');
	clearCtiBackup();
}
//기타
function ctiCallDataRecordReady() {
	$(".sel-call-status").val("call-lock").prop("selected", true);
	$(".sel-call-status").selectpicker('refresh');
	updateCallstatus();
}
function ctiCallDataRecordUnReady() {
	$(".sel-call-status").val("call-resume").prop("selected", true);
	$(".sel-call-status").selectpicker('refresh');
	updateCallstatus();
}
</script>
<script type="text/javascript" src="/telper_api/json2.js"></script>
<script type="text/javascript" src="/telper_api/manager.js"></script>
<!-- //End of telper_api// -->