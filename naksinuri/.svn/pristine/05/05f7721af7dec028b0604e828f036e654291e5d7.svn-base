<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="bootstrap admin template">
    <meta name="author" content="">
    
    <title>낚시누리 관리자</title>
    
    <link rel="apple-touch-icon" href="/common/seadm/assets/images/apple-touch-icon.png">
    <link rel="shortcut icon" href="/common/seadm/assets/images/favicon.ico">
    
    <!-- Stylesheets -->
    <link rel="stylesheet" href="/common/seadm/global/css/bootstrap.css">
    <link rel="stylesheet" href="/common/seadm/global/css/bootstrap-extend.css">
    <link rel="stylesheet" href="/common/seadm/assets/css/site.min.css">
	
	<!-- Plugins -->
	<link rel="stylesheet" href="/common/seadm/global/vendor/animsition/animsition.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/asscrollable/asScrollable.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/switchery/switchery.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/intro-js/introjs.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/slidepanel/slidePanel.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/flag-icon-css/flag-icon.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/alertify/alertify.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/toastr/toastr.min.css">
	
	<!-- Page -->
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/pages/register-v3.css">
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/alertify.min.css">
	<link rel="stylesheet" href="/common/seadm/assets/examples/css/advanced/toastr.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/select2/select2.min.css">
        
    <link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-bs4/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-fixedheader-bs4/dataTables.fixedheader.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-fixedcolumns-bs4/dataTables.fixedcolumns.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-rowgroup-bs4/dataTables.rowgroup.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-scroller-bs4/dataTables.scroller.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-select-bs4/dataTables.select.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-responsive-bs4/dataTables.responsive.bootstrap4.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/datatables.net-buttons-bs4/dataTables.buttons.bootstrap4.min.css">
	
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-select/bootstrap-select.min.css">
	<link rel="stylesheet" href="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.min.css">
	
    <!-- Fonts -->
    <link rel="stylesheet" href="/common/seadm/global/fonts/weather-icons/weather-icons.css">
    <link rel="stylesheet" href="/common/seadm/global/fonts/web-icons/web-icons.min.css">
    <link rel="stylesheet" href="/common/seadm/global/fonts/brand-icons/brand-icons.min.css">
    <link rel='stylesheet' href='/common/css/fontsgoogleapiscom.css?family=Roboto:300,400,500,300italic'>
	<script src="/common/js/fontawesome-all.js"></script>
	    
    <!--[if lt IE 9]>
    <script src="/common/seadm/global/vendor/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    
    <!--[if lt IE 10]>
    <script src="/common/seadm/global/vendor/media-match/media.match.min.js"></script>
    <script src="/common/seadm/global/vendor/respond/respond.min.js"></script>
    <![endif]-->
    
    <!-- Scripts -->
    <script src="/common/seadm/global/vendor/breakpoints/breakpoints.js"></script>
    <script>
      Breakpoints();
    </script>
    <script src="/common/js/jquery-1.10.2.js"></script>
    
    <script>
	$(document).ready(function(){
		setTimeout(function(){
			var alert_data_type = '${alert_data.type}';
			var message = '${alert_data.message}';
			var title = '${alert_data.title}';
			var closebtn = '${alert_data.closebtn}';
			var timer = '${alert_data.timer}'; 
			if(alert_data_type!=null && alert_data_type.length!=0 && alert_data_type == 'alert' ) {
				alertify.alert(message);
			} else {
				if(message!=null && message.length!=0) {
					toastr.info(message,title,{
						tapToDismiss: true,
						closeButton: closebtn=='Y'?true:false,
						debug: false,
						newestOnTop: true,
						progressBar: true,
						positionClass: "toast-top-full-width",
						preventDuplicates: false,
						onclick: false,
						//showDuration: "300",
						//hideDuration: "300",
						timeOut: timer,
						showEasing: "swing",
						hideEasing: "linear",
						showMethod: "slideDown",//fadeIn
						hideMethod: "fadeOut",
						allowHtml: true,
					});
				}
			}
		},500);
	});
	</script>
	
	<style>
	.page-register-v3 .panel {width:640px!important;}
	.hidden{display:none!important;}
	.page-register-v3 form a {margin: 0;}
	</style>
    
  </head>

  <body class="animsition page-register-v3 layout-full">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

	<form:form commandName="CodeSetVO" id="listFormSido" name="listFormSido" method="post">
	<input type="hidden" name="CD_MASTER_ID" value=""/>
	</form:form>

	<div class="page vertical-align text-center" data-animsition-in="fade-in" data-animsition-out="fade-out">
    	<div class="page-content vertical-align-middle animation-slide-top animation-duration-1">
        	<div class="panel">
	          	<div class="panel-body">
	          		<div class="brand">
	            		<h2 class="brand-text font-weight-800">관리자 권한 계정 신청서</h2>
	          		</div>
	          		<% 
	        			session.setAttribute("isChangePwd", true);
	        		%>	
					<form id="modal_action_form" name="modal_action_form" method="post" autocomplete="off" onsubmit="return formSubmit();">
					<input type="hidden" id="MBR_INDVDL_INFO_ST" name="MBR_INDVDL_INFO_ST" value="${MBR_INDVDL_INFO_ST }"/>
					<input type="hidden" id="MBR_PLEDGE_ST" name="MBR_PLEDGE_ST" value="${MBR_PLEDGE_ST }"/>
					
						<div class="form-group form-material floating">
							<div class="col-lg-12">
							<c:forEach var="item_category" items="${list_position_cd}" varStatus="status">
								<div class="radio-custom radio-default radio-inline ">
									<input type="radio" class="clk-sel-position" id="MBR_POSITION_CD_${status.count}" name="MBR_POSITION_CD" value="${item_category.CD_ID}" required>
				  					<label for="MBR_POSITION_CD_${status.count}" class="font-weight-800 blue-600">${item_category.CD_NM}</label>
								</div>
							</c:forEach>
							</div>
						</div>
						<div class="trg-group-option-frm hidden" id="trg_frm_sdsgg">
							<div class="form-group form-material floating input-group" >
								<div class="form-control col-md-6 p-0 mt-10">
									<select class="form-control selectpicker_manual select_target" data-style="btn-default "  id="sel_area_sido" name="MBR_SIDO_CD">   
										<option value="" >시도 전체</option>
										<c:forEach var="item_category" items="${list_address_cd}">
											<option value="${item_category.CD_ID}">${item_category.CD_NM}</option>
										</c:forEach>
						            </select>
					            </div>
					            <div class="form-control col-md-6 p-0 mt-10 ml-10">
						            <select class="form-control selectpicker_manual select_target" data-style="btn-default "  id="sel_area_signgu" name="MBR_SIGNGU_CD" >   
										<option value="" >시군구 전체</option>
						            </select>
					            </div>
					            <label class="floating-label">시도/시군구 선택 </label>
				            </div>
				            <div class="form-group form-material floating input-group" >
								<div class="form-control col-md-6 p-0 mt-10">
									<select class="form-control selectpicker_manual select_target" data-style="btn-default"  name="MBR_TRGT_CD" id="MBR_TRGT_CD" >
										<option value="" >어선업자/터업자/모두</option>   
										<c:forEach var="item_category" items="${list_mbr_trgt_cd}">
											<option value="${item_category.CD_ID}">${item_category.CD_NM}</option>
										</c:forEach>
						            </select>
					            </div>
					            <label class="floating-label">교육대상관리 선택 </label>
				            </div>
			            </div>
			            <div class="form-group form-material floating trg-group-option-frm hidden" id="trg_frm_eduinfo">
			            	<div class="form-control p-0 pt-10">
								<select class="form-control selec2_manual" data-style="btn-dark text-white"  name="MBR_EDU_INS_CD" id="MBR_EDU_INS_CD" >
									<option value="" >선택안함</option>   
									<c:forEach var="item_category" items="${list_ins_info_cd}">
										<option value="${item_category.CAT_INS_SN}">${item_category.CAT_INS_NM}</option>
									</c:forEach>
					            </select>
				            </div>
				            <label class="floating-label">교육기관 선택 </label>
						</div>
						
						<div class="form-group form-material floating">
		           			<input type="text" class="form-control" name="MBR_ID" id="MBR_ID" placeholder="아이디를 입력해주세요." autocomplete="off" value="" required>
		           			<label class="floating-label">아이디<span class="red-600">*</span></label>
		           			<span class="text-help cyan-600 font-size-12 text-left">* 아이디는 영문자 또는 숫자를 조합하여 작성해주세요.</span>
		            	</div>
						<div class="form-group form-material floating">
		              		<input type="password" class="form-control" name="MBR_PWD" id="MBR_PWD" placeholder="비밀번호를 입력해주세요." autocomplete="off" value="" required>
		              		<label class="floating-label">비밀번호<span class="red-600">*</span></label>
		              		<span class="text-help cyan-600 font-size-12 text-left">* 비밀번호는 공백없이 영문,숫자,특수문자를 혼합하여 10자리 ~ 20자리 이내로 입력해주세요.</span>
		            	</div>
						<div class="form-group form-material floating">
		              		<input type="password" class="form-control" name="MBR_PWD_CHK" id="MBR_PWD_CHK" placeholder="" autocomplete="off" value="" required>
		              		<label class="floating-label">비밀번호 확인<span class="red-600">*</span></label>
		            	</div>
		            	
						<div class="form-group form-material floating">
		              		<input type="text" class="form-control" name="MBR_NCNM" placeholder="예)경상남도 사천시 " autocomplete="off" value="" required>
		              		<label class="floating-label">소속 기관  <span class="red-600">*</span></label>
		            	</div>
		            	<div class="form-group form-material floating">
		              		<input type="text" class="form-control" name="MBR_PSITN_NM" placeholder="예)해양수산과" autocomplete="off" value="" required>
		              		<label class="floating-label">소속 부서 <span class="red-600">*</span></label>
		            	</div>
		            	<div class="form-group form-material floating">
		              		<input type="text" class="form-control" name="MBR_NM" id="MBR_NM" placeholder="이름을 입력해주세요." autocomplete="off" value="" required>
		              		<label class="floating-label">담당자 이름 <span class="red-600">*</span></label>
		              		<span class="text-help cyan-600 font-size-12 text-left">* 본인인증이 가능한 이름을 입력해주세요.</span>
		            	</div>
		            	<div class="form-group form-material floating">
		              		<input type="text" class="form-control mbr-birth-input-pattern" data-pattern-cnt="0" name="MBR_BIRTH" placeholder="예)19190301" autocomplete="off" value="" required >
		              		<label class="floating-label">생년월일 <span class="red-600">*</span></label>
		              		<span class="text-help cyan-600 font-size-12 text-left">* 본인인증이 가능한 생년월일을 입력해주세요.</span>
		            	</div>
		            	<div class="form-group form-material floating">
		            		<input type="text" class="form-control bg-white mbr-hp-input-pattern" data-pattern-cnt="0" name="MBR_HP" placeholder="예)01012345678" autocomplete="off" value="" required >
			              	<label class="floating-label">연락처(휴대폰) <span class="red-600">*</span></label>
			              	<span class="text-help cyan-600 font-size-12 text-left">* 본인인증이 가능한 휴대폰번호를 입력해주세요.</span>
		            	</div>
		            	<div class="form-group form-material floating">
		              		<input type="text" class="form-control mbr-tel-input-pattern" data-pattern-cnt="0" name="MBR_TEL" placeholder="내선번호 또는 전화번호를 입력해주세요." autocomplete="off" value="" required >
		              		<label class="floating-label">연락처(일반) <span class="red-600">*</span></label>
		            	</div>
		            	<c:if test="${agree eq '1'}">
		            	<div class="form-group form-material floating">
		              		<input type="text" class="form-control" name="MBR_EMAIL" placeholder="이메일을 입력해주세요." autocomplete="off" value="" >
		              		<label class="floating-label">이메일</label>
		            	</div>
		            	</c:if>
		            	<div class="form-group form-material floating">
		            		<select class="form-control selectpicker_manual mt-10" id="MBR_PURPS_SELECT" name="" data-style="btn-outline btn-default">
		            			<option value="CHARGER">담당자 변경</option>
		            			<option value="JOB">담당 업무 변경</option>
		            			<option value="ETC">기타</option>
		            		</select>
		            		<input type="text" class="form-control" id="MBR_PREDCESR" name="MBR_PREDCESR" placeholder="전임자를 입력해주세요." autocomplete="off" required>
		            		<input type="hidden" class="form-control" id="MBR_PURPS" name="MBR_PURPS" placeholder="신청목적을 입력해주세요." autocomplete="off" required>
		            		<label class="floating-label">신청목적 <span class="red-600">*</span></label>
		            	</div>
		            	<div class="form-group form-material floating">
		            		<input type="text" class="form-control datepicker_str mbr-date-input-pattern" name="MBR_USG_DT" placeholder="승인된 일자부터 사용할 사용기간을 입력해주세요. 승인은 신청일부터 1~2일 소요됩니다." autocomplete="off" value="" required>
		            		<label class="floating-label">사용기간 <span class="red-600">*</span></label>
		            	</div>
		            	<div class="form-group form-material floating">
		              		<textarea class="form-control" name="MBR_DSCRP" placeholder="">${item.MBR_DSCRP}</textarea>
		              		<label class="floating-label">메모(전달사항)</label>
		            	</div>
		            	<p class="red-600">* 최초 로그인 및 정기적인 비밀번호 변경을 위한 본인인증(휴대폰본인인증)정보를 정확히 입력하시기 바랍니다.<br><span class="font-weight-800">* 본인정보가 일치하지 않을 경우 로그인이 불가능합니다.</span></p>
		            	<button type="submit" id="btn_submit" class="btn btn-primary btn-block btn-lg margin-top-40 trg_btn_submit">신청하기</button>
					</form>
					<a class="btn btn-default btn-block" href="/adm/index.do">돌아가기</a>
				</div>
			</div>
			<footer class="page-copyright page-copyright-inverse">
	        	<p>© 2019 낚시누리 관리자</p>
	      	</footer>
		</div>
	</div>
  	<!-- End Page -->
  	
<style>
.select2-container { width: 99.9% !important; }
.bootstrap-select.btn-group .dropdown-menu {z-index: 10060 !important;}
</style>
<script>
/* $('#btn_submit').click(function() {
	console.log(" ________________________ btn_submit");
	formSubmit();
}); */
function formSubmit() {
	console.log(" ________________________ formSubmit");
	var form = document.modal_action_form;	
	var id = form.MBR_ID.value;
	var idReg = /^[A-Za-z0-9+]*$/;
	if(!idReg.test(id)){
		alertify.alert("아이디는 영문자 또는 숫자이어야 합니다.");
		return false;
	}
	var pw = form.MBR_PWD.value;
	var pw2 = form.MBR_PWD_CHK.value;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);	
	if(pw.length < 10 || pw.length > 20){
		alertify.alert("비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}
	if(pw.search(/₩s/) != -1){
		alertify.alert("비밀번호는 공백없이 입력해주세요.");
		return false;
	} 
	if(num < 0 || eng < 0 || spe < 0 ){
		alertify.alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		return false;
	}
	if(pw != pw2) {
		alertify.alert('비밀번호와 비밀번호 확인이 다릅니다.');
		return false;
	}
	autoChkId();
}
function autoChkId() {
	console.log(" ________________________ autoChkId1 ");
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
				console.log(" ________________________ autoChkId2 ");
				document.modal_action_form.action = "/adm/member/join_adm_act.do";
				document.modal_action_form.submit();
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
}
$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});

$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
	$('.datepicker_str').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
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
			'automatch' : true,
		},
		{
			'target' : $('.mbr-hp-input-pattern'),
			'default_pattern' : ['{{999}}-{{9999}}-{{9999}}',11],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.mbr-tel-input-pattern'),
			'default_pattern' : ['{{9999}}-{{9999}}',8],
			'over_pattern' : [['{{999}}-{{999}}-{{9999}}',10],['{{999}}-{{9999}}-{{9999}}',11]],
			'automatch' : true,
		},
		{
			'target' : $('.mbr-date-input-pattern'),
			'default_pattern' : ['{{9999}}-{{99}}-{{99}}',8],
			'over_pattern' : null,
			'automatch' : true,
		},
	]);	
});
$('.clk-sel-position').click(function() {
	var target = $(this).val(); 
	$('.trg-group-option-frm').addClass('hidden');
	$('.selec2_manual').attr("disabled",true);
	$('.select_target').attr("disabled",true);
	if(target=='PCD0003') {
		$('#trg_frm_sdsgg').removeClass('hidden');
		$('.select_target').removeAttr("disabled"); 
	} else if(target=='PCD0004') {
		$('#trg_frm_eduinfo').removeClass('hidden');
		$('.selec2_manual').removeAttr("disabled"); 
	} else {
		$('#trg_frm_default').removeClass('hidden');
	}
});
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();
	var form = document.getElementById('modal_action_form');
	var MBR_POSITION_CD = $("input[name='MBR_POSITION_CD']:checked").val();
	if(MBR_POSITION_CD == 'PCD0003') {//지자체
		if(form.MBR_SIDO_CD.value.trim().length == 0) {
			alertify.alert('시도를 선택해주세요.');
			return;
		}
		/*
		if(form.MBR_SIGNGU_CD.value.trim().length == 0) {
			alertify.alert('시군구를 선택해주세요.');
			return;
		}
		*/
		/*********************************
		*2020.06.10 낚시누리 요청
		if(form.MBR_TRGT_CD.value.trim().length == 0) {
			alertify.alert('교육대상관리를 선택해주세요.');
			return;
		} 
		**********************************/
	} else if(MBR_POSITION_CD == 'PCD0004') {//교육기관
		if(form.MBR_EDU_INS_CD.value.trim().length == 0) {
			alertify.alert('교육기관을 선택해주세요.');
			return;
		}
	} else if(MBR_POSITION_CD == 'PCD0002') {//해경

	}
	
	if(form.MBR_BIRTH.value.trim().length < 10) {
		alertify.alert('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
		
	var agree = '${agree}';
	var MBR_INDVDL_INFO_ST = "";
	if(agree == '2'){
		MBR_INDVDL_INFO_ST = 'N';
	} else {
		MBR_INDVDL_INFO_ST = 'Y';
	}
	form.MBR_INDVDL_INFO_ST.value = MBR_INDVDL_INFO_ST;
	
	//신청목적
	var MBR_PURPS_SELECT = $("#MBR_PURPS_SELECT option:selected");
	var MBR_PURPS = $("#MBR_PURPS");
	if(MBR_PURPS_SELECT.val() == 'CHARGER' || MBR_PURPS_SELECT.val() == 'JOB') MBR_PURPS.val(MBR_PURPS_SELECT.text());
	
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
						location.href = '/adm/index.do';
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
			$('.trg_btn_submit').attr('disabled', true);
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').attr('disabled', false);
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
		$('#sel_area_signgu').html('<option value="">시군구 전체</option>').selectpicker('refresh');
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
				//$('#sel_area_signgu').html(htmlString);
				$('#sel_area_signgu').html(htmlString).selectpicker('refresh');
			}
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
		},
		complete : function() {
			//console.log('complete!');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			clickRequestLockStop();
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
</script>


	<!-- Core  -->
    <script src="/common/seadm/global/vendor/babel-external-helpers/babel-external-helpers.js"></script>
    <script src="/common/seadm/global/vendor/jquery/jquery.js"></script>
    <script src="/common/seadm/global/vendor/popper-js/umd/popper.min.js"></script>
    <script src="/common/seadm/global/vendor/bootstrap/bootstrap.js"></script>
    <script src="/common/seadm/global/vendor/animsition/animsition.js"></script>
    <script src="/common/seadm/global/vendor/mousewheel/jquery.mousewheel.js"></script>
    <script src="/common/seadm/global/vendor/asscrollbar/jquery-asScrollbar.js"></script>
    <script src="/common/seadm/global/vendor/asscrollable/jquery-asScrollable.js"></script>
    <script src="/common/seadm/global/vendor/ashoverscroll/jquery-asHoverScroll.js"></script>
    
    <!-- Plugins -->
    <script src="/common/seadm/global/vendor/switchery/switchery.js"></script>
    <script src="/common/seadm/global/vendor/intro-js/intro.js"></script>
    <script src="/common/seadm/global/vendor/screenfull/screenfull.js"></script>
    <script src="/common/seadm/global/vendor/slidepanel/jquery-slidePanel.js"></script>
	<script src="/common/seadm/global/vendor/jquery-placeholder/jquery.placeholder.js"></script>
	<script src="/common/seadm/global/vendor/alertify/alertify.js"></script>
    <script src="/common/seadm/global/vendor/toastr/toastr.min.js"></script>
    <script src="/common/seadm/global/vendor/select2/select2.full.min.js"></script>
    
    <script src="/common/seadm/global/vendor/datatables.net/jquery.dataTables.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-fixedheader/dataTables.fixedHeader.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-fixedcolumns/dataTables.fixedColumns.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-rowgroup/dataTables.rowGroup.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-scroller/dataTables.scroller.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-responsive/dataTables.responsive.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-responsive-bs4/responsive.bootstrap4.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/dataTables.buttons.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/buttons.html5.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/buttons.flash.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/buttons.print.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons/buttons.colVis.min.js"></script>
	<script src="/common/seadm/global/vendor/datatables.net-buttons-bs4/buttons.bootstrap4.min.js"></script>

	<script src="/common/seadm/global/vendor/bootstrap-select/bootstrap-select.min.js"></script>
	<script src="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script src="/common/seadm/global/vendor/bootstrap-datepicker/bootstrap-datepicker.kr.min.js"></script>
	<script src="/common/seadm/global/vendor/formatter/jquery.formatter.js"></script>

    <!-- Scripts -->
    <script src="/common/seadm/global/js/Component.js"></script>
    <script src="/common/seadm/global/js/Plugin.js"></script>
    <script src="/common/seadm/global/js/Base.js"></script>
    <script src="/common/seadm/global/js/Config.js"></script>
    
    <script src="/common/seadm/assets/js/Section/Menubar.js"></script>
    <script src="/common/seadm/assets/js/Section/GridMenu.js"></script>
    <script src="/common/seadm/assets/js/Section/Sidebar.js"></script>
    <script src="/common/seadm/assets/js/Section/PageAside.js"></script>
    <script src="/common/seadm/assets/js/Plugin/menu.js"></script>
    
    <script src="/common/seadm/global/js/config/colors.js"></script>
    <script src="/common/seadm/assets/js/config/tour.js"></script>
    <script>Config.set('assets', '/common/seadm/assets');</script>
    
    <!-- Page -->
    <script src="/common/seadm/assets/js/Site.js"></script>
    <script src="/common/seadm/global/js/Plugin/asscrollable.js"></script>
    <script src="/common/seadm/global/js/Plugin/slidepanel.js"></script>
    <script src="/common/seadm/global/js/Plugin/switchery.js"></script>
	<script src="/common/seadm/global/js/Plugin/jquery-placeholder.js"></script>
    <script src="/common/seadm/global/js/Plugin/material.js"></script>
    
    <script src="/common/seadm/global/js/Plugin/bootstrap-select.js"></script>
  	<script src="/common/seadm/global/js/Plugin/formatter.js"></script>
  	  	
  	<script>
  	(function(document, window, $) {
      'use strict';

      var Site = window.Site;
      $(document).ready(function() {
        Site.run();
      });
    })(document, window, jQuery);
  	</script>
    
    <script src="/common/js/tail.js"></script>
    
</body>
</html>