<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.modal-dialog{
    position: relative;
    display: table; 
    overflow-y: auto;    
    overflow-x: auto;
    width: auto;
    min-width: 1000px;   
}
.border-b {border-bottom: 3px solid #e4eaec;}
.mgt20 {margin-top: 20px};
</style>

<form:form commandName="eduMemberVO" id="ajaxViewForm" name="ajaxViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>

<div class="modal-dialog modal-simple">
<form commandName="eduAdmCprBplcVO" id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off">
	<%-- <input type="hidden" name="writeType" value="rmndr"/> --%>
	<%-- <input type="hidden" name="RMNDR_SN" value="${info_cpr.ECB_SN}"/> --%>
	<input type="hidden" name="addConfirmSubmit" value="N"/>
	<input type="hidden" name="REG_TYPE_CD" value="PCD0008"/>
	<input type="hidden" name="MBR_ID" value=""/>
	<input type="hidden" name="ECB_SN" value="${info_cpr.ECB_SN}"/>
	
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">법인사업장 법인대표자(검증) 정보확인</h4>
	</div>
	<div class="modal-body">		
		<%-- <div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >아이디(자동생성) <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_ID" id="MBR_ID" placeholder="아이디를 입력해주세요." autocomplete="off" value="${info_cpr.MBR_ID}" required readonly enterDisabled>
			</div>
		</div> --%>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >법인대표자 성명 <span class="red-600">*</span></label>
           	<div class="col-md-3">
           		<input type="text" class="form-control" name="ECB_CPR_NM" id="ECB_CPR_NM" placeholder="성명을 입력해주세요." autocomplete="off" value="${info_cpr.ECB_CPR_NM}" required enterDisabled readonly>
			</div>
			<label class="col-md-3 form-control-label text-left" >법인대표자 생년월일 <span class="red-600">*</span></label>
           	<div class="col-md-3">
           		<input type="text" class="form-control mbr-birth-input-pattern" data-pattern-cnt="0" name="ECB_CPR_BRTHDY" id="ECB_CPR_BRTHDY" placeholder="예)19190301" autocomplete="off" value="${info_cpr.ECB_CPR_BRTHDY}" required enterDisabled readonly>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >법인대표자 신고 시.군.구<span class="red-600">*</span></label>
           	<div class="col-md-3">
           		<c:forEach var="addr" items="${list_address_cd }">
           			<c:if test="${addr.CD_ID eq  info_cpr.ECB_CPR_SIDO_CD}"><c:set var="SIDO" value="${addr.CD_NM }"/></c:if>
           		</c:forEach>
           		<input type="text" class="form-control" data-pattern-cnt="0" name="" id="" placeholder="" autocomplete="off" value="${SIDO} ${info_cpr.CD_SIGNGU_NM}" required enterDisabled readonly>
			</div>
			<label class="col-md-3 form-control-label text-left" >법인대표자 신고증번호<span class="red-600">*</span></label>
           	<div class="col-md-3">
           		<input type="text" class="form-control reg-num-cd-input-pattern" data-pattern-cnt="0" name="ECB_REG_NUM_CD" id="ECB_REG_NUM_CD" placeholder="" autocomplete="off" value="${info_cpr.ECB_REG_NUM_CD}" required enterDisabled readonly>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >법인대표자 낚시어선명<span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" data-pattern-cnt="0" name="ECB_CPR_DTL_NM" id="ECB_CPR_DTL_NM" placeholder="예)19190301" autocomplete="off" value="${info_cpr.ECB_CPR_DTL_NM}" required enterDisabled readonly>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >법인대표자 연락처 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control <%-- bg-white --%> mbr-hp-input-pattern" data-pattern-cnt="0" name="ECB_CPR_HP" id="ECB_CPR_HP" placeholder="연락처를 입력해주세요." autocomplete="off" value="${info_cpr.ECB_CPR_HP}" required enterDisabled readonly>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 휴대폰번호 번호인지 여부를 확인해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="ECB_CPR_ZIP">우편번호 <span class="red-600">*</span></label>
			<div class="col-md-9 row">
				<div class="col-md-4">
					<input type="text" class="form-control" id="ECB_CPR_ZIP" name="ECB_CPR_ZIP" placeholder="우편번호" autocomplete="off" value="${info_cpr.ECB_CPR_ZIP}" enterDisabled readonly>
				</div>
				<!-- <button type="button" onclick="zipcode('CPR_MBR_ZIPCD','CPR_MBR_ADDR1','CPR_MBR_ADDR2','target-addr-daumapi-modal');" class="btn btn-outline btn-default btn-sm">우편번호 찾기</button> -->
				<div class="col-md-12">
					<div id="target-addr-daumapi-modal" style="display:none;border:1px solid;width:100%;height:300px;margin-top:10px;position:relative">
					<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
					</div>
				</div>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="ECB_CPR_ADDR1">법인대표자 주소 <span class="red-600">*</span></label>
			<div class="col-md-9">
					<input type="text" class="form-control" id="ECB_CPR_ADDR1" name="ECB_CPR_ADDR1" placeholder="주소를 입력해주세요." autocomplete="off" value="${info_cpr.ECB_CPR_ADDR1}" enterDisabled readonly>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="ECB_CPR_ADDR2">법인대표자 상세주소</label>
			<div class="col-md-9">
					<input type="text" class="form-control" id="ECB_CPR_ADDR2" name="ECB_CPR_ADDR2" placeholder="상세주소를 입력해주세요." autocomplete="off" value="${info_cpr.ECB_CPR_ADDR2}" enterDisabled readonly>
			</div>
		</div>
		<div class="border-b"></div>
		<%-- 교육책임자 --%>
		<div class="form-group row mgt20">
			<label class="col-md-3 form-control-label text-left" >교육책임자 성명 <span class="red-600">*</span></label>
           	<div class="col-md-3">
           		<input type="text" class="form-control" name="ECB_EDU_NM" id="ECB_EDU_NM" placeholder="성명을 입력해주세요." autocomplete="off" value="${info_cpr.ECB_EDU_NM}" required enterDisabled readonly>
			</div>
			<label class="col-md-3 form-control-label text-left">교육책임자 생년월일 <span class="red-600">*</span></label>
           	<div class="col-md-3">
           		<input type="text" class="form-control mbr-birth-input-pattern" data-pattern-cnt="0" name="ECB_EDU_BRTHDY" id="ECB_EDU_BRTHDY" placeholder="예)19190301" autocomplete="off" value="${info_cpr.ECB_EDU_BRTHDY}" required enterDisabled readonly>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >교육책임자 직책<span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" data-pattern-cnt="0" name="ECB_EDU_POSIT" id="ECB_EDU_POSIT" placeholder="" autocomplete="off" value="${info_cpr.ECB_EDU_POSIT}" required enterDisabled readonly>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left">교육책임자 연락처 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-hp-input-pattern" data-pattern-cnt="0" name="ECB_EDU_HP" id="ECB_EDU_HP" placeholder="연락처를 입력해주세요." autocomplete="off" value="${info_cpr.ECB_CPR_HP}" required enterDisabled readonly>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 휴대폰번호 번호인지 여부를 확인해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="">우편번호 <span class="red-600">*</span></label>
			<div class="col-md-9 row">
				<div class="col-md-4">
					<input type="text" class="form-control" id="ECB_EDU_ZIP" name="ECB_EDU_ZIP" placeholder="우편번호" autocomplete="off" value="${info_cpr.ECB_EDU_ZIP}" enterDisabled readonly>
				</div>
				<!-- <button type="button" onclick="zipcode('MBR_ZIPCD','MBR_ADDR1','MBR_ADDR2','target-addr-daumapi-modal');" class="btn btn-outline btn-default btn-sm">우편번호 찾기</button> -->
				<div class="col-md-12">
					<div id="target-addr-daumapi-modal" style="display:none;border:1px solid;width:100%;height:300px;margin-top:10px;position:relative">
					<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
					</div>
				</div>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="ECB_EDU_ADDR1">교육책임자 주소 <span class="red-600">*</span></label>
			<div class="col-md-9">
					<input type="text" class="form-control" id="ECB_EDU_ADDR1" name="ECB_EDU_ADDR1" placeholder="주소를 입력해주세요." autocomplete="off" value="${info_cpr.ECB_EDU_ADDR1}" enterDisabled readonly>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="ECB_EDU_ADDR2">교육책임자 상세주소</label>
			<div class="col-md-9">
					<input type="text" class="form-control" id="ECB_EDU_ADDR2" name="ECB_EDU_ADDR2" placeholder="상세주소를 입력해주세요." autocomplete="off" value="${info_cpr.ECB_EDU_ADDR2}" enterDisabled readonly>
			</div>
		</div>	
	
		<div class="form-group row hidden" id="idChkOverlapBody">
			<div class="col-md-12 text-center">
				<div class="card card-inverse bg-warning mb-0">
					<div class="card-block">
						<span class="blue-grey-600 font-weight-800" id="labelOverlapNotice">법인대표자 정보와 유사한 정보를 가진 회원이 존재하여 확인이 필요합니다.
						<br>'현재정보로 교체 및 추가하기' 선택시 사용중이 아닌 회원은 사용중으로 자동 변경됩니다.</span>
					</div>
		        </div>
			</div>
			<div class="col-md-12 text-left">
				<span class="badge badge-outline badge-danger">이름,생년월일 중복건</span><span class="font-size-12 ml-10">이름과 생년월일이 정확히 일치하는 회원입니다.</span>
				<br><span class="badge badge-outline badge-info">이름 의심건</span><span class="font-size-12 ml-10">이름이 유사한 회원입니다.</span>
				<br><span class="badge badge-outline badge-success">생년월일 의심건</span><span class="font-size-12 ml-10">생년월일이 유사한 회원입니다.</span>
				<br><span class="badge badge-outline badge-primary">연락처 의심건</span><span class="font-size-12 ml-10">연락처가 유사한 회원입니다.</span>
			</div>
			<div class="col-md-12 mt-10" >
				<table class="table table-hover footable footable-paging footable-paging-center">
					<colgroup>
					</colgroup>
				   	<thead>
				   		<tr>
				   			<th class="text-middle text-center">알림</th>
				   			<th class="text-middle text-center">이름<br>닉네임(별명)</th>
				      		<th class="text-middle text-center">생년월일</th>
							<th class="text-middle text-center">연락처</th>
							<th class="text-middle text-center">사용여부</th>						
							<th class="text-middle text-center">비고</th>
						</tr>				      
				    </thead>
					<tbody id="script_member_overlap_info_list">					
					</tbody>
					<tfoot>						
					</tfoot>				  
				</table>
			</div>
			<hr>
			<div class="col-md-12 mt-10">
				<textarea class="form-control h-80" name="LOG_UPD_MSG" id="LOG_UPD_MSG" placeholder="변경사유(설명)" row="5" required disabled></textarea>
			</div>
		</div>
		<div class="float-right">
			<button type="submit" class="btn btn-outline btn-info trg_btn_submit ml-10" id="btn_chk_submit">검증 및 등록하기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<style>
.select2-container { width: 99.9% !important; }
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
			'target' : $('.ship-cd-input-pattern'),
			'default_pattern' : ['{{9999999}}-{{9999999}}',14],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.reg-num-cd-input-pattern'),
			'default_pattern' : ['{{9999}}-{{999}}',7],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			"target" : $('.business-num-input-pattern'),
			"default_pattern" : ["{{999}}-{{99}}-{{99999}}",10],
			"over_pattern" : [['{{999999}}-{{9999999}}',13]],
			"automatch" : true,
		},
	]);
	
});

$("#btn_chk_submit").click(function() {
	$('#LOG_UPD_MSG').attr('disabled',true).hide();
	var form = document.getElementById('modal_action_form');
	form.addConfirmSubmit.value = 'N';
});

$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();

	//default	
	$.ajax({
		type:"POST",
		url :"/eduadm/board/cpredu/write_act.do",
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
				} else if(data.error == '2') {
					$('#idChkOverlapBody').show();
					//$('#labelOverlapNotice').html(data.msg);
					$('#btn_chk_submit').text('다시 검증하기');
					$('#btn_force_submit').show();
					var json = JSON.parse(data.rawdata);
					var htmlString = '';
					for (i=0; i<json.length; i++) {	
						var item = json[i];
						var tdaddspan = '';
						if(item.overlaptype == '1') {
							tdaddspan = '<span class="badge badge-outline badge-danger">이름,생년월일 중복건</span>';
						} else if(item.overlaptype == '2') {
							tdaddspan = '<span class="badge badge-outline badge-info">이름 의심건</span>';
						} else if(item.overlaptype == '3') {
							tdaddspan = '<span class="badge badge-outline badge-success">생년월일 의심건</span>';
						} else if(item.overlaptype == '4') {
							tdaddspan = '<span class="badge badge-outline badge-primary">연락처 의심건</span>';
						}	
						htmlString += '<tr>';
						htmlString += '	<td class="text-middle text-center">'+tdaddspan+'</td>';
						htmlString += '	<td class="text-middle text-center">'+item.mbr_nm+'<br><span class="grey-400">'+item.mbr_ncnm+'</span></td>';
						htmlString += '	<td class="text-middle text-center">'+item.mbr_birth+'</td>';
						//htmlString += '	<td class="text-middle text-center">'+item.mbr_id+'</td>';
						htmlString += '	<td class="text-middle text-center">'+item.mbr_hp+'</td>';
						htmlString += '	<td class="text-middle text-center">'+(item.mbr_st=='Y'?'사용중':'<span class="red-600">사용안함</span>')+'</td>';
						htmlString += '	<td class="text-middle text-center">';
						htmlString += '		<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_view_data(\''+item.mbr_id+'\')" ';
						htmlString += '				>자세히보기</a>';
						htmlString += '		<a class="btn btn-squared btn-outline btn-default btn-sm" onclick="clk_confirm_submit(\''+item.mbr_id+'\')">';
						htmlString += '			<i class="site-menu-icon icon wb-loop" aria-hidden="true"></i>현재정보로 교체 및 추가하기';
						htmlString += '		</a>';
						htmlString += '	</td>';
						htmlString += '</tr>';
						
					}
					$("#script_member_overlap_info_list").html(htmlString);
				
				} else if(data.error == '3') {
					alertify.alert(data.msg);
				} else {
					$("#admPublicModal").modal('hide');
					alertify.alert(data.msg+"<br>대상자를 다시 검색하여 확인해주세요.");
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
			var form = document.getElementById('modal_action_form');
			form.addConfirmSubmit.value = 'Y';
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

function clk_confirm_submit(mbr_id) {
	$('#LOG_UPD_MSG').removeAttr('disabled').show();
	if($('#LOG_UPD_MSG').val().trim()=='') {
		alertify.alert('처리 사유를 입력해주세요.');
		$('#LOG_UPD_MSG').focus();
		return;
	} 
	alertify.confirm("선택한 회원의 정보가 입력된 정보로 교체되어 저장되며<br>상세정보는 추가되어 등록됩니다.<br>현재 정보로 반영 하시겠습니까?", function(){ 
		var form = document.getElementById('modal_action_form');
		form.MBR_ID.value = mbr_id;
		form.addConfirmSubmit.value = 'Y';

		$.ajax({
			type:"POST",
			url :'/eduadm/board/cpredu/modify_act.do',
			data:$('#modal_action_form').serialize(),
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
}
function clk_view_data(mbr_id) {
	var form = document.getElementById('ajaxViewForm');
	form.MBR_ID.value = mbr_id;
	$.ajax({
		type:"POST",
		url :'/eduadm/member/view.do',
		data:$('#ajaxViewForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#admPublicPanelLayer").html(data);
			$("#admPublicPanelLayer").show();
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}
$(document).ready(function(){
	setTimeout(function(){
		//$('#btn_chk_submit').click();자동화시	
	},1000);
});
</script>

<!-- //다음주소찾기// -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	// 우편번호 찾기 찾기 화면을 넣을 element
	var element_wrap = null;
	
	function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }
	function zipcode(zipcode, addr1, addr2, zipcode_layer) {
		element_wrap = document.getElementById(zipcode_layer);
		var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById(zipcode).value = extraAddr;
                
                } else {
                	document.getElementById(zipcode).value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById(zipcode).value = data.zonecode; 
                document.getElementById(addr1).value = addr;
                
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById(addr2).focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>
<!-- End //다음주소찾기// -->
