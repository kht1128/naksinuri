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
</style>

<form:form commandName="eduMemberVO" id="ajaxViewForm" name="ajaxViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>

<form:form commandName="CodeSetVO" id="modalMemberWriteFormSido" name="modalMemberWriteFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<div class="modal-dialog modal-simple">
<form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/eduadm/member/write_act.do">
	<input type="hidden" name="addConfirmSubmit" value="N"/>
	<input type="hidden" name="CRS_SN" value="${info.CRS_SN}"/>
	<input type="hidden" name="MBR_SCRTY_KEY" value=""/>
	<input type="hidden" name="writeType">
	
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">신규회원(교육대상자)등록</h4>
	</div>
	<div class="modal-body">		
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >아이디 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_ID" id="MBR_ID" placeholder="아이디를 입력해주세요." autocomplete="off" value="${info.MBR_ID}" required readonly enterDisabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >별명 또는 닉네임 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_NCNM" id="MBR_NCNM" placeholder="예) 낚시터사장님" autocomplete="off" value="" required enterDisabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >이름 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_NM" id="MBR_NM" placeholder="이름을 입력해주세요." autocomplete="off" value="" required enterDisabled>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 이름을 입력해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >생년월일 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-birth-input-pattern" data-pattern-cnt="0" name="MBR_BIRTH" id="MBR_BIRTH" placeholder="예)19190301" autocomplete="off" value="" required enterDisabled>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 생년월일을 입력해주세요.<br>예) 19190301</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >연락처(휴대폰번호) <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white mbr-hp-input-pattern" data-pattern-cnt="0" name="MBR_HP" id="MBR_HP" placeholder="연락처를 입력해주세요." autocomplete="off" value="" required  enterDisabled>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 휴대폰번호를 입력해주세요.<br>예) 01012345678</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >연락처(일반전화)</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white mbr-tel-input-pattern" data-pattern-cnt="0" name="MBR_TEL" id="MBR_TEL" placeholder="연락처를 입력해주세요." autocomplete="off" value=""  enterDisabled>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="MBR_EMAIL">이메일</label>
			<div class="col-md-9">
					<input type="text" class="form-control" name="MBR_EMAIL" placeholder="이메일을 입력해주세요." autocomplete="off" value="" enterDisabled>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="MBR_ADDR1">우편번호 <span class="red-600">*</span></label>
			<div class="col-md-9 row">
				<div class="col-md-4">
					<input type="text" class="form-control readonly bg-blue-grey-100" id="MBR_ZIPCD" name="MBR_ZIPCD" placeholder="우편번호" autocomplete="off" value="" enterDisabled  required>
				</div>
				<button type="button" onclick="zipcode('MBR_ZIPCD','MBR_ADDR1','MBR_ADDR2','target-addr-daumapi-modal');" class="btn btn-outline btn-default btn-sm">우편번호 찾기</button>
				<div class="col-md-12">
					<div id="target-addr-daumapi-modal" style="display:none;border:1px solid;width:100%;height:300px;margin-top:10px;position:relative">
					<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
					</div>
				</div>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="MBR_ADDR1">주소 <span class="red-600">*</span></label>
			<div class="col-md-9">
					<input type="text" class="form-control readonly bg-blue-grey-100" id="MBR_ADDR1" name="MBR_ADDR1" placeholder="주소를 입력해주세요." autocomplete="off" value="" enterDisabled required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="MBR_ADDR2">상세주소 </label>
			<div class="col-md-9">
					<input type="text" class="form-control" id="MBR_ADDR2" name="MBR_ADDR2" placeholder="상세주소를 입력해주세요." autocomplete="off" value="" enterDisabled >
			</div>
		</div>	
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="MBR_ADDR2">적용할 교육대상년도</label>
			<div class="col-md-9">
				<c:if test="${empty info.searchYear}"> 
					<c:set var="_today" value="<%=new java.util.Date()%>" />
					<fmt:formatDate value="${_today}" pattern="yyyy" var="CAL_TODAY"/>
					<fmt:formatNumber value="${CAL_TODAY}" type="number" var="_curYear" pattern="0000" />
					<select class="form-control selectpicker_manual col-md-3 " data-style="btn-info text-white" name="searchYear">
						<c:forEach var="item" begin="${_curYear-2}" end="${_curYear+1}" step="1">
							<option value="${item}" <c:if test="${item eq (_curYear)}">selected</c:if> >${item}</option>
						</c:forEach>
					</select> 
					<span class="text-help font-size-14 teal-600 font-weight-800">등록이 완료되면 해당 년도 교육대상자에 자동반영 되며 이후 조회가 가능합니다.</span>
				</c:if> 
				<c:if test="${not empty info.searchYear}">
					<input type="hidden" name="searchYear" value="${info.searchYear}"/>
					<span class="text-help font-size-14 teal-600 font-weight-800">등록이 완료되면 ${info.searchYear} 년도 교육대상자에 자동반영 되며 이후 조회가 가능합니다.</span>
				</c:if>
			</div>
		</div>		
		<div class="form-group row">
			<div class="col-md-12 text-left">
				<a class="btn btn-round btn-outline btn-dark btn-sm clk_add_member_dtl_data" id="clk_add_member_dtl_data" href="javascript:void(0)" >
	        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
	        		<span class="hidden-sm-down">상세 정보 등록</span>
	        	</a> 
	        	<span class="font-size-12 red-600 ml-10"></span>
			</div>
			<div class="col-md-12 mt-10 table-responsive" style="width:990px;">
				<table class="table table-hover footable footable-paging footable-paging-center" style="width:1900px;max-width:1900px;">
					<colgroup>
					</colgroup>
				   	<thead>
				   		<tr>
				      		<th class="text-middle text-center" colspan="5">공통정보</th>				      		
				           	<th class="text-middle text-center blue-600" colspan="3">낚시어선정보</th>
							<th class="text-middle text-center orange-600" colspan="2">낚시터정보</th>							
							<th class="text-middle text-center" rowspan="3">비고</th>
						</tr>
				      	<tr>
				      		<th class="text-middle text-center">업종구분 <span class="red-600">*</span></th>				      		
				           	<th class="text-middle text-center">시도 <span class="red-600">*</span></th>
							<th class="text-middle text-center">읍면동</th>
							<th class="text-middle text-center">대상구분 <span class="red-600">*</span></th>
							<th class="text-middle text-center">유효기간시작일자</th>		
							<th class="text-middle text-center blue-600">어선번호</th>
							<th class="text-middle text-center blue-600">선적항</th>
							<th class="text-middle text-center blue-600">해기사면허 <span class="red-600">*</span></th>					
							<th class="text-middle text-center orange-600" rowspan="2">적용수면</th>
							<th class="text-middle text-center orange-600" rowspan="2">사업장주소</th>	
						</tr>
						<tr>
							<th class="text-middle text-center">어선명<br>/낚시터명</th>
							<th class="text-middle text-center">시군구 <span class="red-600">*</span></th>
							<th class="text-middle text-center">사업자번호<br>(법인번호)</th>
							<th class="text-middle text-center">신고번호(어선)<br>/허가(등록)번호(낚시터)</th>
							<th class="text-middle text-center">유효기간만료일자</th>
							<th class="text-middle text-center blue-600">총톤수</th>
							<th class="text-middle text-center blue-600">최대승객수</th>
							<th class="text-middle text-center blue-600">최대선원수</th>
						</tr>
				       	</thead>
					<tbody id="script_member_detail_info_list">					
					</tbody>
					<tfoot>						
					</tfoot>				  
				</table>
				<p class="orange-600 " style="font-size:11px;">ㆍ추가 상세정보를 하나 이상 등록시 해당 내용의 항목 중 <b>(*)표시 항목</b>은 필수로 입력해야 하는 정보이므로 미입력시 등록이 되지 않으며 (*)표시가 없는 항목은 미입력이 가능합니다.</p>
				<p class="orange-600 " style="font-size:11px;">ㆍ법인업자는 사업자번호에 법인번호를 입력하시면 됩니다.</p>
			</div>
		</div>
		<div class="form-group row hidden" id="idChkOverlapBody">
			<div class="col-md-12 text-center">
				<div class="card card-inverse bg-warning mb-0">
					<div class="card-block">
						<span class="blue-grey-600 font-weight-800" id="labelOverlapNotice">중복이 의심되는 회원이 존재하여 확인이 필요합니다.
						<br>'현재정보로 교체 및 추가하기' 또는 '무시하고 신규등록하기'를 선택해주세요.
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
		</div>
		<hr>
		<div class="form-group row">
			<div class="btn-group col-lg-12">
				<textarea class="form-control h-80" name="LOG_UPD_MSG" id="LOG_UPD_MSG" placeholder="변경사유(설명)" row="5" required disabled></textarea>
			</div>
		</div>
		<div class="float-right">
			<button type="submit" class="btn btn-outline btn-danger trg_btn_submit hidden" id="btn_force_submit" >무시하고 신규등록하기</button>
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
	{//추가상세정보 체크 및 빈칸 생성
		var cntData = $('#script_member_detail_info_list > tr').length;
		if(cntData < 1) {
			make_member_dtl_data();
		}
	}
});
//업종선택 색상 변경
function changeSelectBoxDtlCdColor(obj) {
	var targetval = $(obj).val();
	if(targetval == 'CIDN010200') {//낚시터 , #dc7b09
		$(obj).css('background-color','#dc7b09').css('color','#fff');
	} else if(targetval == 'CIDN010300') {//낚시어선 , #0178cd
		$(obj).css('background-color','#0178cd').css('color','#fff');
	}
}
$("#btn_chk_submit").click(function() {
	$('#LOG_UPD_MSG').attr('disabled',true).hide();
	var form = document.getElementById('modal_action_form');
	form.addConfirmSubmit.value = 'N';
});
$("#btn_force_submit").click(function() {
	$('#LOG_UPD_MSG').attr('disabled',true).hide();
	var form = document.getElementById('modal_action_form');
	form.addConfirmSubmit.value = 'Y';
});
$("#modal_action_form").on("submit", function(event) {
	event.preventDefault();
	
	var form = document.getElementById('modal_action_form');
	if(form.MBR_BIRTH.value.trim().length < 10) {
		alertify.alert('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
	
	//낚시어선은 해기사면허 검증
	var isLockShipLicense = false;
	var dtl_cd_length = $('#modal_action_form').find('[name=DTL_CD]').length;
	if(dtl_cd_length > 0) {		
		if(dtl_cd_length > 1) {
			for(var i = 0; i < dtl_cd_length; i++){
				var DTL_CD = form.DTL_CD[i].value;
				var SHIP_LICENSE = form.SHIP_LICENSE[i].value;
				if(DTL_CD =='CIDN010300' && SHIP_LICENSE == '') {//낚시어선 인 경우 필수
					isLockShipLicense = true;
				}				
				
			}
			/* form.DTL_CD.forEach(function(element,index){
				var DTL_CD = element.value;
				var SHIP_LICENSE = form.SHIP_LICENSE[index].value;
				if(DTL_CD =='CIDN010300' && SHIP_LICENSE == '') {//낚시어선 인 경우 필수
					isLockShipLicense = true;
				}				
			}); */	
		} else {
			if(form.DTL_CD.value =='CIDN010300' && form.SHIP_LICENSE.value == '') {//낚시어선 인 경우 필수
				isLockShipLicense = true;
			}		
		}		
	} else {
		//상세정보등록 0개
	}
	if(isLockShipLicense) {
		alertify.alert('낚시어선업자는 해기사면허를 선택해주세요.');
		return;
	}
	//End of 낚시어선은 해기사면허 검증
	
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
						htmlString += '		<a class="btn btn-squared btn-outline btn-default btn-sm" onclick="clk_confirm_submit(\''+item.mbr_id+'\',\''+item.mbr_scrty_key+'\')">';
						htmlString += '			<i class="site-menu-icon icon wb-loop" aria-hidden="true"></i>현재정보로 교체 및 추가하기';
						htmlString += '		</a>';
						htmlString += '	</td>';
						htmlString += '</tr>';
						
					}
					$("#script_member_overlap_info_list").html(htmlString);
					
				} else {
					$("#admPublicModal").modal('hide');
					alertify.alert(data.msg+"<br>대상자를 다시 검색하여 확인해주세요.",function(){
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
			$('.trg_btn_submit').prop('disabled', true);
			var form = document.getElementById('modal_action_form');
			form.addConfirmSubmit.value = 'Y';
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
$(".clk_add_member_dtl_data").click(function() {
	make_member_dtl_data();
});
function make_member_dtl_data() {
	//var cntData = $('#script_member_detail_info_list > tr').length;
	var htmlTag = '';
	htmlTag += '<tr>';
	htmlTag += ' 	<input type="hidden" name="DTL_SN" value="">';
	htmlTag += ' 	<input type="hidden" name="USE_AT" value="">';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += '		<select class="form-control selectpicker_manual" onchange="changeSelectBoxDtlCdColor(this)" data-style="btn-primary text-white"  name="DTL_CD" required>';   
	htmlTag += '			<option value="" >업종선택</option>';
	htmlTag += '			<c:forEach var="item" items="${list_target_se_cd}">';
	htmlTag += '				<option value="${item.CD_ID}">${item.CD_NM}</option>';
	htmlTag += '			</c:forEach>';
	htmlTag += '		</select>';
	htmlTag += ' 		<input type="text" class="form-control" name="DTL_NM" placeholder="사업장명" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += '		<select class="form-control selectpicker_manual sel_area_sido" data-style="btn-primary text-white"  name="SIDO_CD" required>';
	htmlTag += '			<option value="" >시도선택</option>';
	htmlTag += '			<c:forEach var="item" items="${list_address_cd}">';
	htmlTag += '				<option value="${item.CD_ID}">${item.CD_NM}</option>';
	htmlTag += '			</c:forEach>';
	htmlTag += '		</select>';
	htmlTag += '		<select class="form-control selectpicker_manual sel_area_signgu" data-style="btn-primary text-white "  name="SIGNGU_CD" required>';   
	htmlTag += '			<option value="" >시군구선택</option>';
	htmlTag += '		</select>';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-top text-center">';
	htmlTag += ' 		<input type="text" class="form-control" name="YMD_NM" placeholder="읍면동" autocomplete="off" value="" >';
	htmlTag += ' 		<input type="text" class="form-control business-num-input-pattern-write" data-pattern-cnt="0" name="BUSINESS_NUM" placeholder="사업자번호" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += '		<select class="form-control selectpicker_manual" data-style="btn-primary text-white"  name="DTL_LICENSE_CD" required>';  
	htmlTag += '			<option value="">대상구분선택</option>';
	htmlTag += '			<c:forEach var="item" items="${list_license_se_cd}">';
	htmlTag += '				<option value="${item.CD_ID}">${item.CD_NM}</option>';
	htmlTag += '			</c:forEach>';
	htmlTag += '		</select>';
	htmlTag += ' 		<input type="text" class="form-control reg-num-cd-input-pattern-write" data-pattern-cnt="0" name="REG_NUM_CD" placeholder="신고번호" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += ' 		<input type="text" class="form-control datepickerModalStr" name="SHIP_LICENSE_STR_DT" placeholder="유효기간시작일자" autocomplete="off" value="" >';
	htmlTag += ' 		<input type="text" class="form-control datepickerModalEnd" name="SHIP_LICENSE_END_DT" placeholder="유효기간종료일자" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += ' 		<input type="text" class="form-control blue-600 ship-cd-input-pattern-write" data-pattern-cnt="0" name="SHIP_CD" placeholder="어선번호" autocomplete="off" value="" >';
	htmlTag += ' 		<input type="text" class="form-control blue-600" name="SHIP_GRTG" placeholder="총톤수" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += ' 		<input type="text" class="form-control blue-600" name="SHIP_PRLOAD" placeholder="선적항" autocomplete="off" value="" >';
	htmlTag += ' 		<input type="text" class="form-control blue-600" name="SHIP_MAX_PSNGER" placeholder="최대승객수" autocomplete="off" value="" >';
	htmlTag += '	</td>';	
	htmlTag += '	<td class="text-middle text-center">';
	//htmlTag += ' 		<input type="text" class="form-control blue-600" name="SHIP_LICENSE" placeholder="해기사면허" autocomplete="off" value="" >';
	htmlTag += '		<select class="form-control " data-style="btn-outline btn-info"  name="SHIP_LICENSE" >';   
	htmlTag += '			<option value="">해기사면허</option>';
	htmlTag += '			<option value="유">유</option>';
	htmlTag += '			<option value="무">무</option>';
	htmlTag += '		</select>';
	htmlTag += ' 		<input type="text" class="form-control blue-600" name="SHIP_MAX_CREW" placeholder="최대선원수" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-top text-center">';
	htmlTag += '		<select class="form-control " data-style="btn-outline btn-info" name="FSHLC_WORK_CD" >';
	htmlTag += '			<option value="" >업구분</option>   ';
	htmlTag += '			<c:forEach var="item_category" items="${list_fshlc_work_cd}">';
	htmlTag += '				<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq FSHLC_WORK_CD }">selected</c:if> >${item_category.CD_NM}</option>';
	htmlTag += '			</c:forEach>';
	htmlTag += '		</select>';
	htmlTag += ' 		<input type="text" class="form-control cyan-600" name="FSHLC_APPLC" placeholder="적용수면" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-top text-center">';
	htmlTag += ' 		<input type="text" class="form-control cyan-600" name="DTL_ADDR" placeholder="사업장주소" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-top text-left">';
	htmlTag += '		<a class="btn btn-squared btn-outline btn-default btn-sm " onclick="clk_del_member_dtl_data(this)"><i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>삭제</a>';
	htmlTag += '	</td>';
	htmlTag += '</tr>';
	//htmlTag += '<script>$(".ship-cd-input-pattern-write").formatter({"pattern": "{{9999999}}-{{9999999}}","persistent": true});<\/script>';
	htmlTag += '<script>formatterCustomMultiple([';
	htmlTag += '		{';
	htmlTag += '        	"target" : $(".ship-cd-input-pattern-write"),';
	htmlTag += '	     	"default_pattern" : ["{{9999999}}-{{9999999}}",14],';
	htmlTag += '	    	"over_pattern" : null,';
	htmlTag += '	    	"automatch" : false,';
	htmlTag += '		},';
	htmlTag += '		{';
	htmlTag += '        	"target" : $(".reg-num-cd-input-pattern-write"),';
	htmlTag += '	     	"default_pattern" : ["{{9999}}-{{999}}",7],';
	htmlTag += '	    	"over_pattern" : null,';
	htmlTag += '	    	"automatch" : false,';
	htmlTag += '		},';
	htmlTag += '		{';
	htmlTag += '        	"target" : $(".business-num-input-pattern-write"),';
	htmlTag += '	     	"default_pattern" : ["{{999}}-{{99}}-{{99999}}",10],';
	htmlTag += '	    	"over_pattern" : [["{{999999}}-{{9999999}}",13]],';
	htmlTag += '	    	"automatch" : false,';
	htmlTag += '		},';
	htmlTag += ']);<\/script>';
	htmlTag += '<script>$(document).find(".datepickerModalStr").removeClass("hasDatepicker").datepicker({format: "yyyy-mm-dd",autoclose: true,language: "kr"});<\/script>';
	htmlTag += '<script>$(document).find(".datepickerModalEnd").removeClass("hasDatepicker").datepicker({format: "yyyy-mm-dd",autoclose: true,language: "kr"});<\/script>';
	$("#script_member_detail_info_list").append(htmlTag);
}
function clk_del_member_dtl_data(obj) {
	$(obj).parent().parent().remove();
}
$(document).on("change",".sel_area_sido",function() {
	var target = $(this).parent().find(".sel_area_signgu");
	var val = $(this).val();
	var form = document.getElementById('modalMemberWriteFormSido');
	form.CD_MASTER_ID.value = val;
	form.action = '';	
	if(val=='') {
		target.html('<option value="">시군구선택</option>');
	} else {
		$.ajax({
			type:"POST",
			url :"/all/code.do",
			data:$('#modalMemberWriteFormSido').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				if(data.error == '1') {
					alertify.alert(data.msg);
				} else {
					var json = JSON.parse(data.rawdata);
					var htmlString = '<option value="">시군구선택</option>';
					for (i=0; i<json.length; i++) {	
						var item = json[i];
						htmlString += '<option value="'+item.cd_id+'">'+item.cd_nm+'</option>';
					}
					target.html(htmlString);
				}
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
function clk_confirm_submit(mbr_id, mbr_scrty_key) {
	$('#LOG_UPD_MSG').removeAttr('disabled').show();
	if($('#LOG_UPD_MSG').val().trim()=='') {
		alertify.alert('처리 사유를 입력해주세요.');
		$('#LOG_UPD_MSG').focus();
		return;
	} 
	alertify.confirm("선택한 회원의 정보가 입력된 정보로 교체되어 저장되며<br>상세정보는 추가되어 등록됩니다.<br>현재 정보로 반영 하시겠습니까?", function(){
		var form = document.getElementById('modal_action_form');
		form.MBR_ID.value = mbr_id;
		form.MBR_SCRTY_KEY.value = mbr_scrty_key;
		form.addConfirmSubmit.value = 'Y';
		form.writeType.value= "eduTargetAdd";
		
		$.ajax({
			type:"POST",
			url :'/eduadm/member/modify_act.do',
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
						$("#admPublicModal").modal('hide');
						document.listForm.submit();
					}
				}
			},
			beforeSend : function() {
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

