<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form commandName="CodeSetVO" id="listFormSido" name="listFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<div class="modal-dialog modal-simple">
<form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/eduadm/member/write_adm_act.do">
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">신규회원등록</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >아이디 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<div class="btn-group w-p100">
           			<input type="text" class="form-control" name="MBR_ID" id="MBR_ID" placeholder="아이디를 입력해주세요." autocomplete="off" value="" required>
           			<button type="button" class="btn btn-primary clk_search_id_btn">중복조회</button>
           		</div>
           		<span class="text-help cyan-600 font-size-12 text-left">* 비밀번호는 본인인증 후 사용자가 직접 변경하여 접속합니다.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >소속 기관 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_NCNM" id="MBR_NCNM" placeholder="예)경상남도 사천시 " autocomplete="off" value="" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >소속 부서 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_PSITN_NM" id="MBR_PSITN_NM" placeholder="예)해양수산과" autocomplete="off" value="" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >담당자 이름 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_NM" id="MBR_NM" placeholder="이름을 입력해주세요." autocomplete="off" value="" required>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 이름을 입력해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >생년월일 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-birth-input-pattern" data-pattern-cnt="0" name="MBR_BIRTH" id="MBR_BIRTH" placeholder="예)19190301" autocomplete="off" value="" required >
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 생년월일 입력해주세요.<br>예) 19190301</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >연락처(휴대폰) <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white mbr-hp-input-pattern" data-pattern-cnt="0" name="MBR_HP" id="MBR_HP" placeholder="연락처를 입력해주세요." autocomplete="off" value="" required >
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 휴대폰번호를 입력해주세요.<br>예) 01012345678</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >연락처(일반) <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-tel-input-pattern" data-pattern-cnt="0" name="MBR_TEL" placeholder="내선번호 또는 전화번호를 입력해주세요." autocomplete="off" value="" required >
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" >이메일 </label>
			<div class="col-md-9">
					<input type="text" class="form-control" name="MBR_EMAIL" placeholder="이메일을 입력해주세요." autocomplete="off" value="" >
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">사용일자</label>
			<div class="col-md-9">
				<input type="hidden" name="MBR_USG_DT" id="MBR_USG_DT" value=""/>
				<div class="form-group row">
					<div class="col-lg-6"><input type="text" class="form-control" id="MBR_USG_DATE" name="MBR_USG_DATE" placeholder="yyyy-MM-dd" autocomplete="off"></div>
					<div class="col-lg-6"><input type="text" class="form-control timepickerHHMM " id="MBR_USG_TIME" name="MBR_USG_TIME" placeholder="hh:mm:ss" autocomplete="off"></div>
				</div>
   			</div>
		</div>
		<hr>
		<div class="form-group row">
			<div class="col-md-12 text-right">
				<span class="text-help red-600 font-size-12">* 권한설정은 선택 된 지역,대상,기관에 한하여 검색 및 관리가 가능합니다.</span>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">관리자 유형</legend>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-primary text-white" name="MBR_POSITION_CD" id="MBR_POSITION_CD" >   
					<c:forEach var="item" items="${list_position_cd}">
						<option value="${item.CD_ID}">${item.CD_DES}</option>
					</c:forEach>
	            </select>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">지역관리권한</legend>
			<div class="col-md-9">
				<select class="form-control selec2_manual" data-style="btn-dark text-white"  id="sel_area_sido" name="MBR_SIDO_CD" >   
					<option value="" >시도 전체</option>
					<c:forEach var="item" items="${list_address_cd}">
						<option value="${item.CD_ID}">${item.CD_NM}</option>
					</c:forEach>
	            </select>
	            <div class="mt-10">
		            <select class="form-control selec2_manual" data-style="btn-dark text-white"  id="sel_area_signgu" name="MBR_SIGNGU_CD" >   
						<option value="" >시군구 전체</option>
		            </select>
	            </div>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">교육대상관리권한</legend>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-primary text-white"  name="MBR_TRGT_CD" id="MBR_TRGT_CD" >
					<option value="" >제한없음</option>   
					<c:forEach var="item" items="${list_mbr_trgt_cd}">
						<option value="${item.CD_ID}" >${item.CD_DES}</option>
					</c:forEach>
	            </select>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">교육과정(기관)관리권한</legend>
			<div class="col-md-9">
				<select class="form-control selec2_manual" data-style="btn-dark text-white"  name="MBR_EDU_INS_CD" id="MBR_EDU_INS_CD" >
					<option value="" >제한없음</option>   
					<c:forEach var="item" items="${list_ins_info_cd}">
						<option value="${item.CAT_INS_SN}">${item.CAT_INS_NM}</option>
					</c:forEach>
	            </select>
			</div>
		</div>
		<hr>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" >접속 가능 아이피</label>
			<div class="col-md-9">
				<textarea class="form-control h-100" name="CONECT_POSBL_IP" placeholder="접속가능 아이피를 입력해주세요." row="5" ></textarea>
				<span class="text-help red-600 font-size-12"><b>다수 아이피</b> 등록시 <b>,</b> 로 구분해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">접속 가능 아이피 사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline col-md-4 m-0">
					<input type="radio" id="CONECT_POSBL_IP_USE_YN_Y" name="CONECT_POSBL_IP_USE_YN" value="Y">
  					<label for="CONECT_POSBL_IP_USE_YN_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline col-md-4">
					<input type="radio" id="CONECT_POSBL_IP_USE_YN_N" name="CONECT_POSBL_IP_USE_YN" value="N" checked>
  					<label for="CONECT_POSBL_IP_USE_YN_N">사용안함</label>
				</div>
			</div>
		</div>
		<hr>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">신청목적<span class="red-600">*</span></legend>
			<div class="col-md-9">
	       		<select class="form-control selectpicker_manual mt-10" id="MBR_PURPS_SELECT" name="" data-style="btn-outline btn-default">
	       			<option value="CHARGER">담당자 변경</option>
	       			<option value="JOB">담당 업무 변경</option>
	       			<option value="ETC">기타</option>
	       		</select>
	       		<input type="text" class="form-control mt-5" id="MBR_PREDCESR" name="MBR_PREDCESR" placeholder="전임자를 입력해주세요." autocomplete="off" required>
	       		<input type="hidden" class="form-control mt-5" id="MBR_PURPS" name="MBR_PURPS" placeholder="신청목적을 입력해주세요." autocomplete="off" required>
       		</div>
       	</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" >메모</label>
			<div class="col-md-9">
				<textarea class="form-control h-100" name="MBR_DSCRP" placeholder="메모(기타)" row="5" >${item.MBR_DSCRP}</textarea>
			</div>
		</div>
		<hr>
		<div class="form-group row">
			<div class="btn-group col-lg-12">
				<textarea class="form-control h-80" name="LOG_UPD_MSG" placeholder="변경사유(설명)" row="5" required></textarea>
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
.select2-container { width: 99.9% !important; }
.bootstrap-select.btn-group .dropdown-menu {z-index: 10060 !important;}
.ui-timepicker-wrapper{z-index:9999!important;}
</style>
<script>
$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
	$('#MBR_USG_DATE').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	$('.timepickerHHMM').timepicker({
	    'showDuration': true,
	    'timeFormat': 'H:i',
	});
	<%--
		//처리 로직은 tail.jsp 파일에 존재함.
		//공통 : 사용 할 대상(input)의 속성(attr)에 필히 추가 data-pattern-cnt="0"
	   	//파라미터 : target 			= keyup 이벤트를 받을 input text 의 jquery selector
	   	//파라미터 : default_pattern 	= 기본 패턴 형태값,자리수
	   	//파라미터 : over_pattern		= 기본 패턴의 자리수 이상인 경우 다음 단계 패턴 형태 및 해당 패턴의 자리수를 기입하며 , 해당 자리수 이상인 경우 다음 단계 패턴으로 전환 
	   	//파라미터 : automatch			= 화면 로딩시 해당 폼(input)에 입력값이 존재 할 경우 패턴을 자동 적용 할 것인지에 대한 설정(true:패턴 형태를 적용하여 노출)
	--%>
	formatterCustomMultiple([
       	{
			'target' : $('.mbr-birth-input-pattern'),
			'default_pattern' : ['{{9999}}-{{99}}-{{99}}',8],
			'over_pattern' : null,
			'automatch' : false,
		},
		{
			'target' : $('.mbr-hp-input-pattern'),
			'default_pattern' : ['{{999}}-{{9999}}-{{9999}}',11],
			'over_pattern' : null,
			'automatch' : false,
		},
		{
			'target' : $('.mbr-tel-input-pattern'),
			'default_pattern' : ['{{9999}}-{{9999}}',8],
			'over_pattern' : [['{{999}}-{{999}}-{{9999}}',10],['{{999}}-{{9999}}-{{9999}}',11]],
			'automatch' : false,
		},
	]);
});
$(".clk_search_id_btn").click(function() {
   	$.ajax({
		type:"POST",
		url :"/adm/member/searchId.do",
		data:$('#modal_action_form').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		cache: false,
		success: function(data, status, xhr) {
			if(data.error == '0') {
				alertify.alert(data.msg);
			} else {
				alertify.alert(data.msg);
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
		}
	});
});     
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();

	var form = document.getElementById('modal_action_form');
	if(form.MBR_BIRTH.value.trim().length < 10) {
		alertify.alert('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
	
	//신청목적
	var MBR_PURPS_SELECT = $("#MBR_PURPS_SELECT option:selected");
	var MBR_PURPS = $("#MBR_PURPS");
	if(MBR_PURPS_SELECT.val() == 'CHARGER' || MBR_PURPS_SELECT.val() == 'JOB') MBR_PURPS.val(MBR_PURPS_SELECT.text());
	
	//사용기간
	var mbr_usg_date = $("#MBR_USG_DATE").val();
	if(mbr_usg_date=='' && mbr_usg_date.length==0) {
		mbr_usg_date = '9999-12-31';	
	}
	var mbr_usg_time = $("#MBR_USG_TIME").val();
	if(mbr_usg_time=='' && mbr_usg_time.length==0) {
		mbr_usg_time = '23:59:59';
	} else {
		mbr_usg_time += ':59';
	}
	form.MBR_USG_DT.value = mbr_usg_date +' '+ mbr_usg_time;
	
	//교육대상자관리권한, 교육과정(기관)관리권한 (낚시명예감시원 일경우)
	if($("#MBR_POSITION_CD").val() == 'PCD0009'){
		$("#MBR_TRGT_CD").prop("disabled", false);
		$("#MBR_EDU_INS_CD").prop("disabled", false);
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
					$("#seaAdmEduPublicModal").modal('hide');
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
			$('.trg_btn_submit').prop('disabled', true);
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').prop('disabled', false);
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
$("#sel_area_sido").change(function() {
	if(this.value=='') {
		$('#sel_area_signgu').html('<option value="">시군구 전체</option>');
		return;
	}
	var form = document.getElementById('listFormSido');
	form.CD_MASTER_ID.value = this.value;
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/all/code.do",
		data:$('#listFormSido').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			//console.log(data);
			//console.log(data.rawdata);
			if(data.error == '1') {
				alertify.alert(data.msg);
			} else {
				var json = JSON.parse(data.rawdata);
				var htmlString = '<option value="">시군구 전체</option>';
				for (i=0; i<json.length; i++) {	
					var item = json[i];
					htmlString += '<option value="'+item.cd_id+'">'+item.cd_nm+'</option>';
				}
				//console.log(htmlString);
				$('#sel_area_signgu').html(htmlString);
			}
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
});

$("#MBR_PURPS_SELECT").on("change", function(){
	var value = $(this).val();
	
	var MBR_PURPS = $("#MBR_PURPS");
	var MBR_PREDCESR = $("#MBR_PREDCESR");
	
	switch(value){
		case "CHARGER": 
			MBR_PURPS.attr("type", "hidden");
			MBR_PURPS.attr("required", false);
			MBR_PREDCESR.attr("type", "text");
			MBR_PREDCESR.attr("required", true);
			break;
		case "JOB" : 
			MBR_PURPS.attr("type", "hidden"); 
			MBR_PURPS.attr("required", false);
			MBR_PREDCESR.attr("type", "hidden");
			MBR_PREDCESR.attr("required", false);
			MBR_PREDCESR.val('');
			break;
		case "ETC": 
			MBR_PURPS.attr("type", "text");
			MBR_PURPS.attr("required", true);
			MBR_PREDCESR.attr("type", "hidden");
			MBR_PREDCESR.attr("required", false);
			MBR_PREDCESR.val('');
			break;
		default : break;
	}	
		
});

$("#MBR_POSITION_CD").on("change", function(){
	
	var value = $(this).val();
	
	var MBR_TRGT_CD = $("#MBR_TRGT_CD");
	var MBR_EDU_INS_CD = $("#MBR_EDU_INS_CD");
	
	if(value == 'PCD0009'){
		MBR_TRGT_CD.append('<option value="NONE" class="opt_remove" selected>권한없음</option>');
		$('.selectpicker_manual').selectpicker('refresh');
		
		MBR_EDU_INS_CD.append('<option value="NONE" class="opt_remove" selected>권한없음</option>');
		$("#select2-MBR_EDU_INS_CD-container").text("권한없음");
		
		MBR_TRGT_CD.prop("disabled", true);
		MBR_EDU_INS_CD.prop("disabled", true);
		
	} else {
		$(".opt_remove").remove();
		
		MBR_TRGT_CD.prop("disabled", false);
		MBR_TRGT_CD.val("");
		$('.selectpicker_manual').selectpicker('refresh');
		
		MBR_EDU_INS_CD.prop("disabled", false);
		MBR_EDU_INS_CD.val("");
		$("#select2-MBR_EDU_INS_CD-container").text("제한없음");
	}
});
</script>
