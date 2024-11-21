<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<style>
.modal-dialog{
    min-width: 800px;   
}
</style>

<form:form commandName="CodeSetVO" id="listFormSido" name="listFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="logMemberModifyVO" id="ajaxLogMbrModListFormFirst" name="ajaxLogMbrModListFormFirst" method="post">
<input type="hidden" name="MBR_ID" value="${info.MBR_ID}"/>
<input type="hidden" name="pageIndex" value="1"/>
<input type="hidden" name="pageUnit" value="5"/>
</form:form>

<div class="modal-dialog modal-simple">
<form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/eduadm/member/modify_adm_act.do">
	
	<input type="hidden" id="typeStr" name="typeStr" value="dtl"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">관리자 정보 수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >회원번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_SN}" disabled>
				<input type="hidden" name="MBR_SN" value="${info.MBR_SN}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >아이디</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_ID}" disabled>
				<input type="hidden" name="MBR_ID" value="${info.MBR_ID}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >소속 기관 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_NCNM" id="MBR_NCNM" placeholder="예)경상남도 사천시 " autocomplete="off" value="${info.MBR_NCNM}" required enterDisabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >소속 부서 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_PSITN_NM" id="MBR_PSITN_NM" placeholder="예)해양수산과" autocomplete="off" value="${info.MBR_PSITN_NM}" required enterDisabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >담당자 이름 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_NM" placeholder="이름을 입력하세요." autocomplete="off" value="${info.MBR_NM}" required enterDisabled>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 이름을 입력해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >생년월일 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-birth-input-pattern" data-pattern-cnt="0" name="MBR_BIRTH" placeholder="예)19190301" autocomplete="off" value="${info.MBR_BIRTH}" required  enterDisabled>
				<span class="text-help red-600 font-size-12">* 본인인증이 가능한 생년월일을 입력해주세요.<br>예) 19190301</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >연락처(휴대폰) <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white mbr-hp-input-pattern" data-pattern-cnt="0" name="MBR_HP" id="MBR_HP" placeholder="연락처를 입력해주세요." autocomplete="off" value="${info.MBR_HP}" required  enterDisabled>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 휴대폰번호를 입력해주세요.<br>예) 01012345678</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >연락처(일반) <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-tel-input-pattern" data-pattern-cnt="0" name="MBR_TEL" placeholder="내선번호 또는 전화번호를 입력해주세요." autocomplete="off" value="${info.MBR_TEL}" required enterDisabled>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="MBR_EMAIL">이메일</label>
			<div class="col-md-9">
					<input type="text" class="form-control" name="MBR_EMAIL" placeholder="이메일을 입력해주세요." autocomplete="off" value="${info.MBR_EMAIL}" enterDisabled>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline col-md-4">
					<input type="radio" id="MBR_ST_Y" name="MBR_ST" value="Y" <c:if test="${info.MBR_ST eq 'Y'}">checked</c:if>>
					<label for="MBR_ST_Y">사용함(로그인가능)</label>
				</div>
				<div class="radio-custom radio-default radio-inline col-md-4">
					<input type="radio" id="MBR_ST_N" name="MBR_ST" value="N" <c:if test="${info.MBR_ST eq 'N'}">checked</c:if>>
  					<label for="MBR_ST_N">사용안함(로그인제한)</label>
				</div>
				<div class="radio-custom radio-default radio-inline col-md-4 m-0">
					<input type="radio" id="MBR_ST_R" name="MBR_ST" value="R" <c:if test="${info.MBR_ST eq 'R'}">checked</c:if>>
  					<label for="MBR_ST_R">승인대기</label>
				</div>
				<div class="radio-custom radio-default radio-inline col-md-4">
					<input type="radio" id="MBR_ST_F" name="MBR_ST" value="F" <c:if test="${info.MBR_ST eq 'F'}">checked</c:if>>
  					<label for="MBR_ST_F">승인거부</label>
				</div>
				<span class="text-help red-600 font-size-12"><b>삭제</b>상태에서 <b>사용함</b>으로 저장하면 다시 복구 할 수 있습니다.</span>
			</div>
		</div>	
		<div class="form-group row">
        	<label class="col-md-3 form-control-label">가입일자</label>
           	<div class="col-md-9">
           		<fmt:parseDate var="parseregdatestring" value="${info.MBR_REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">변경일자</label>
			<div class="col-md-9">
				<fmt:parseDate var="parsemoddatestring" value="${info.MBR_MOD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="moddatestring" value="${parsemoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${moddatestring}" disabled>
   			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">사용일자</label>
			<div class="col-md-9">
				<fmt:parseDate var="parseusgdtstring" value="${info.MBR_USG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="usgdtstring" value="${parseusgdtstring}" pattern="yyyy-MM-dd" />
				<fmt:formatDate var="usgdttimestring" value="${parseusgdtstring}" pattern="HH:mm" />
				<input type="hidden" name="MBR_USG_DT" id="MBR_USG_DT" value=""/>
				<div class="form-group row">
					<div class="col-lg-6"><input type="text" class="form-control" id="MBR_USG_DATE" placeholder="yyyy-MM-dd" autocomplete="off" value="${usgdtstring}"></div>
					<div class="col-lg-6"><input type="text" class="form-control timepickerHHMM " id="MBR_USG_TIME" placeholder="hh:mm:ss" autocomplete="off" value="${usgdttimestring}"></div>
				</div>
   			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">비밀번호초기화</legend>
			<div class="col-md-9">
				<c:choose>
					<c:when test="${not empty info.MBR_PWD}">
						<div class="checkbox-custom checkbox-warning">
		     				<input type="checkbox" id="MBR_PWD_CLEAR" name="MBR_PWD_CLEAR" value="Y" >
		                  	<label for="MBR_PWD_CLEAR" class="font-weight-bold orange-600">비밀번호을 초기화 하고 본인인증을 활성화 합니다.</label>                  	
		                </div>
					</c:when>
					<c:otherwise>
						<span class="text-help grey-500 font-weigth-bold">현재 비밀번호가 초기화 된 상태의 사용자 입니다.</span>	
					</c:otherwise>
				</c:choose>
			</div>
		</div>	
		<hr>
		<c:if test="${LoginVO.MBR_LV_ID ne '1'}">
			<div class="form-group row">
				<div class="col-md-12 text-center">
					<span class="text-help red-600 font-size-12">* 권한설정(변경)은 지정 된 관리자만 가능합니다.</span>
				</div>
			</div>
		</c:if>
		<c:if test="${LoginVO.MBR_LV_ID eq '1'}">
			<div class="form-group row">
				<div class="col-md-12 text-right">
					<span class="text-help red-600 font-size-12">* 권한설정은 선택 된 지역,대상,기관에 한하여 검색 및 관리가 가능합니다.</span>
				</div>
			</div>
			<div class="form-group row">
				<legend class="col-md-3 form-control-label">관리자 유형</legend>
				<div class="col-md-9">
					<select class="form-control selectpicker_manual" data-style="btn-primary text-white"  name="MBR_POSITION_CD" id="MBR_POSITION_CD" >   
						<c:forEach var="item_category" items="${list_position_cd}">
							<option value="${item_category.CD_ID}" <c:if test="${info.MBR_POSITION_CD eq item_category.CD_ID}">selected</c:if> >${item_category.CD_DES}</option>
						</c:forEach>
						<c:if test="${info.MBR_LV_ID eq '1'}">
							<option value="${info.MBR_POSITION_CD}" selected>통합관리자</option>
						</c:if>
		            </select>
				</div>
			</div>
			<div class="form-group row">
				<legend class="col-md-3 form-control-label">지역관리권한</legend>
				<div class="col-md-9">
					<select class="form-control selec2_manual" data-style="btn-dark text-white"  id="sel_area_sido" name="MBR_SIDO_CD" >   
						<option value="" >시도 전체</option>
						<c:forEach var="item_category" items="${list_address_cd}">
							<option value="${item_category.CD_ID}" <c:if test="${info.MBR_SIDO_CD eq item_category.CD_ID}">selected</c:if> >${item_category.CD_NM}</option>
						</c:forEach>
		            </select>
		            <div class="mt-10">
			            <select class="form-control selec2_manual" data-style="btn-dark text-white"  id="sel_area_signgu" name="MBR_SIGNGU_CD" >   
							<option value="" >시군구 전체</option>
							<c:forEach var="item_category" items="${list_address_signgu_cd}">
								<option value="${item_category.CD_ID}" <c:if test="${info.MBR_SIGNGU_CD eq item_category.CD_ID}">selected</c:if> >${item_category.CD_NM}</option>
							</c:forEach>
			            </select>
		            </div>
				</div>
			</div>
			<div class="form-group row">
				<legend class="col-md-3 form-control-label">교육대상관리권한</legend>
				<div class="col-md-9">
					<select class="form-control selectpicker_manual" data-style="btn-primary text-white"  name="MBR_TRGT_CD" id="MBR_TRGT_CD" >
						<option value="" <c:if test="${empty info.MBR_TRGT_CD}">selected</c:if> >제한없음</option>   
						<c:forEach var="item_category" items="${list_mbr_trgt_cd}">
							<option value="${item_category.CD_ID}" <c:if test="${info.MBR_TRGT_CD eq item_category.CD_ID}">selected</c:if> >${item_category.CD_DES}</option>
						</c:forEach>
		            </select>
				</div>
			</div>
			<div class="form-group row">
				<legend class="col-md-3 form-control-label">교육과정(기관)관리권한</legend>
				<div class="col-md-9">
					<select class="form-control selec2_manual" data-style="btn-dark text-white"  name="MBR_EDU_INS_CD" id="MBR_EDU_INS_CD" >
						<option value="" <c:if test="${empty info.MBR_EDU_INS_CD}">selected</c:if> >제한없음</option>   
						<c:forEach var="item_category" items="${list_ins_info_cd}">
							<option value="${item_category.CAT_INS_SN}" <c:if test="${info.MBR_EDU_INS_CD eq item_category.CAT_INS_SN}">selected</c:if> >${item_category.CAT_INS_NM}</option>
						</c:forEach>
		            </select>
				</div>
			</div>
		</c:if>
		<hr>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" >접속 가능 아이피</label>
			<div class="col-md-9">
				<textarea class="form-control h-100" name="CONECT_POSBL_IP" placeholder="접속가능 아이피를 입력해주세요." row="5" >${info.CONECT_POSBL_IP}</textarea>
				<span class="text-help red-600 font-size-12"><b>다수 아이피</b> 등록시 <b>,</b> 로 구분해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">접속 가능 아이피 사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline col-md-4 m-0">
					<input type="radio" id="CONECT_POSBL_IP_USE_YN_Y" name="CONECT_POSBL_IP_USE_YN" value="Y" <c:if test="${info.CONECT_POSBL_IP_USE_YN eq 'Y'}">checked</c:if>>
  					<label for="CONECT_POSBL_IP_USE_YN_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline col-md-4">
					<input type="radio" id="CONECT_POSBL_IP_USE_YN_N" name="CONECT_POSBL_IP_USE_YN" value="N" <c:if test="${info.CONECT_POSBL_IP_USE_YN eq 'N'}">checked</c:if>>
  					<label for="CONECT_POSBL_IP_USE_YN_N">사용안함</label>
				</div>
			</div>
		</div>
		<hr>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" >신청목적</label>
			<div class="col-md-9">
	       		<c:choose>
	       			<c:when test="${info.MBR_PURPS eq '담당자 변경'}"><c:set var="MBR_PURPS" value="담당자 변경"/></c:when>
	       			<c:when test="${info.MBR_PURPS eq '담당 업무 변경'}"><c:set var="MBR_PURPS" value="담당 업무 변경"/></c:when>
	       			<c:otherwise><c:set var="MBR_PURPS" value="기타"/></c:otherwise>
	       		</c:choose>
	       		<input type="text" class="form-control" value="${MBR_PURPS}" disabled>
				<c:if test="${MBR_PURPS eq '기타'}"><textarea class="form-control mt-5" name="MBR_PURPS" disabled>${info.MBR_PURPS}</textarea></c:if>
				<c:if test="${MBR_PURPS eq '담당자 변경'}"><input type="text" class="form-control mt-5" value="${info.MBR_PREDCESR}" disabled></c:if>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" >메모</label>
			<div class="col-md-9">
				<textarea class="form-control h-100" name="MBR_DSCRP" placeholder="메모(기타)" row="5" >${info.MBR_DSCRP}</textarea>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-12 mt-10 " id="trgt_list_log_mbr_mod_data"></div>
		</div>
		<hr>
		<div class="form-group row">
			<div class="btn-group col-lg-12">
				<textarea class="form-control h-80" name="LOG_UPD_MSG" placeholder="변경사유(설명)" row="5" required></textarea>
			</div>
		</div>
		<div class="text-right">
			<fmt:formatNumber value="${LoginVO.MBR_LV_ID}" type="number" var="my_mbr_lv_id" />
        	<fmt:formatNumber value="${info.MBR_LV_ID}" type="number" var="mbr_lv_id" />
			<c:choose>
        		<c:when test="${info.MBR_ST eq 'R' or info.MBR_ST eq 'F' }">
        			<c:choose>
        				<c:when test="${LoginVO.MBR_LV_ID eq '1'}">
        					<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">변경하기</button>
        				</c:when>
        				<c:otherwise>
        					<div class="badge badge-outline badge-warning text-middle">회원정보변경권한이없음<br>(승인처리는 지정 된 관리자만 가능)</div>
        				</c:otherwise>
        			</c:choose>
        		</c:when>
        		<c:otherwise>
        			<c:choose>
        				<c:when test="${my_mbr_lv_id <= mbr_lv_id}">
        					<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">변경하기</button>
        				</c:when>
        				<c:otherwise>
        					<div class="badge badge-outline badge-warning text-middle">회원정보변경권한이없음<br>(본인 보다 높은 등급의 관리자 계정)</div>
        				</c:otherwise>
        			</c:choose>        			
        		</c:otherwise>
        	</c:choose>
        	<button type="button" class="btn btn-default btn-outline ml-5" data-dismiss="modal">취소(닫기)</button>
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
	]);
	
	if($("#MBR_POSITION_CD").val() == 'PCD0009'){
		
		var MBR_TRGT_CD = $("#MBR_TRGT_CD");
		var MBR_EDU_INS_CD = $("#MBR_EDU_INS_CD");
		
		MBR_TRGT_CD.append('<option value="NONE" class="opt_remove" selected>권한없음</option>');
		$('.selectpicker_manual').selectpicker('refresh');
		
		MBR_EDU_INS_CD.append('<option value="NONE" class="opt_remove" selected>권한없음</option>');
		$("#select2-MBR_EDU_INS_CD-container").text("권한없음");
		
		MBR_TRGT_CD.prop("disabled", true);
		MBR_EDU_INS_CD.prop("disabled", true);
	}
	
});

String.prototype.insert_at=function(index, string){   
  return this.substr(0, index) + string + this.substr(index);
}

$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();

	var form = document.getElementById('modal_action_form');
	if(form.MBR_BIRTH.value.trim().length < 10) {
		alertify.alert('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		clickRequestLockStop();
		return;
	}
	
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
					alertify.alert(data.msg,function(){
						$("#seaAdmEduPublicModal").modal('hide');
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
function trgtListLogMbrModData(frmid) {
	$.ajax({
		type:"POST",
		url :'/adm/log/ajaxListMbrMod.do',
		data:$('#'+frmid).serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#trgt_list_log_mbr_mod_data").html(data);
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}
trgtListLogMbrModData('ajaxLogMbrModListFormFirst');

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
