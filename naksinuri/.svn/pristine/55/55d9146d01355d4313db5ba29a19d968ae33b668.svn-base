<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>

	<style>
	.custom_inputform_table.table th {
	    font-weight: bold;
	    background: #f0f4f5;
	}
	</style>
	
	<c:set var="IS_ONLY_CTI_MBR" value="false" />
	<c:choose>
		<c:when test="${IS_JOIN_MBR eq 'Y'}">
			<c:set var="MBR_BIRTH" value="${info.MBR_BIRTH}" />		
			<c:set var="MBR_NCNM" value="(${info.MBR_NCNM})" />
			<c:set var="MBR_NM" value="${info.MBR_NM}" />	
			<c:set var="MBR_HP" value="${info.MBR_HP}" />
			<c:set var="MBR_ID" value="${info.MBR_ID}" />
		</c:when>
		<c:when test="${IS_JOIN_MBR eq 'N' and not empty info}">
			<c:set var="MBR_BIRTH" value="${info.MBR_BIRTH}" />		
			<c:set var="MBR_NCNM" value="(${info.MBR_NCNM})" />
			<c:set var="MBR_NM" value="${info.MBR_NM}" />	
			<c:set var="MBR_HP" value="${info.MBR_HP}" />
			<c:set var="MBR_ID" value="${info.MBR_ID}" />
		</c:when>
		<c:otherwise>
			<c:set var="MBR_BIRTH" value="${cti_info.CTI_MBR_BIRTH}" />		
			<c:set var="MBR_NCNM" value="" />
			<c:set var="MBR_NM" value="${cti_info.CTI_MBR_NM}" />	
			<c:set var="MBR_HP" value="${cti_info.CTI_MBR_HP}" />
			<c:set var="MBR_ID" value="${cti_info.MBR_ID}" />
			<c:set var="IS_ONLY_CTI_MBR" value="true" />
		</c:otherwise>
	</c:choose>
	
	<h4>
		<c:set var="isEmptyDetailInfo" value="false"/>
		<c:if test="${empty list}">
			<span class="red-600 font-size-14">해당 계정은 낚시터,낚시어선 등 상세정보가 등록되어 있지 않습니다.</span>
			<c:set var="isEmptyDetailInfo" value="true"/>
		</c:if>
		<c:if test="${not empty list}">
			<select class="form-control selectpicker_manual-${CUSTOM_UNIQ_KEY} sel-ajax-detail-info-${CUSTOM_UNIQ_KEY}" data-style="btn-outline btn-default">
			<c:forEach var="item" varStatus="status" items="${list}">
				<option
					data-dtl-nm="${item.DTL_NM}" 
				    data-dtl-cd-nm="${item.DTL_CD_NM}" 
					data-sido-cd-nm="${item.SIDO_CD_NM}"
					data-signgu-cd-nm="${item.SIGNGU_CD_NM}"
					data-dtl-addr="${item.DTL_ADDR}"
					value="${item.DTL_SN}">${item.DTL_CD_NM} | ${item.DTL_NM } | ${item.SIDO_CD_NM } | ${item.SIGNGU_CD_NM }</option>
			</c:forEach>
			</select>
		</c:if>
	</h4>
	<form id="formAjaxDetail${CUSTOM_UNIQ_KEY}" name="formAjaxDetail${CUSTOM_UNIQ_KEY}" method="post">
	<input type="hidden" name="IS_ONLY_CTI_MBR" value="${IS_ONLY_CTI_MBR}" />
	<table class="table footable footable-paging footable-paging-center custom_inputform_table">
			<colgroup>
			<col width='20%' >
        	<col width='30%'>
        	<col width='20%'>
        	<col width='30%'>
			</colgroup>
		<tbody>
		<tr>
			<th class="text-left text-middle">시도</th>
 			<td class="text-center text-middle">
 				<input class="form-control bg-white border-0" type="text" name="SIDO_CD_NM" value="" disabled/>
			</td>
			<th class="text-left text-middle">시군구</th>
			<td class="text-center text-middle">
				<input class="form-control bg-white border-0" type="text" name="SIGNGU_CD_NM" value="" disabled/>
			</td>
		</tr>
		<tr>
			<th class="text-left text-middle">업구분</th>
 			<td class="text-center text-middle">
 				<input class="form-control bg-white border-0" type="text" name="DTL_CD_NM" value="" disabled/>
			</td>
			<th class="text-left text-middle">사업장명</th>
			<td class="text-center text-middle">
				<input class="form-control bg-white border-0" type="text" name="DTL_NM" value="" disabled/>
			</td>
		</tr>
		<tr>
			<th class="text-left text-middle">아이디</th>
 			<td class="text-center text-middle">
 				<c:if test="${IS_ONLY_CTI_MBR eq true}">
 					<input class="form-control " type="text" name="MBR_ID" value="<c:out value="${MBR_ID}"/>" autocomplete="off" />
 				</c:if>
 				<c:if test="${IS_ONLY_CTI_MBR eq false}">
					<span class="input-group-append">
						<input class="form-control bg-white border-0" type="text" value="<c:out value="${MBR_ID}"/>" disabled/>
						<input type="hidden" name="MBR_ID" value="<c:out value="${MBR_ID}"/>" />
						<a href="javascript:void(0)" class="btn btn-outline btn-default btn-sm clk-mbr-view${CUSTOM_UNIQ_KEY}" data-mbr-id="<c:out value="${MBR_ID}"/>" >자세히보기</a>
					</span>
				</c:if>
			</td>
			<th class="text-left text-middle">생년월일</th>
			<td class="text-center text-middle">
				<c:if test="${IS_ONLY_CTI_MBR eq true}">
 					<input class="form-control " type="text" name="CTI_MBR_BIRTH" value="${pfpu:birthHypen(MBR_BIRTH)}" autocomplete="off" />
 				</c:if>
 				<c:if test="${IS_ONLY_CTI_MBR eq false}">
					<input class="form-control bg-white border-0" type="text" value="${pfpu:birthHypen(MBR_BIRTH)}" disabled/>
					<input type="hidden" name="CTI_MBR_BIRTH" value="${MBR_BIRTH}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<th class="text-left text-middle">이름(닉네임)</th>
 			<td class="text-center text-middle">
 				<c:if test="${IS_ONLY_CTI_MBR eq true}">
 					<input class="form-control " type="text" name="CTI_MBR_NM" value="<c:out value="${MBR_NM}"/>" autocomplete="off" />
 				</c:if>
 				<c:if test="${IS_ONLY_CTI_MBR eq false}">
 					<input class="form-control bg-white border-0" type="text" value="<c:out value="${MBR_NM}"/> <c:out value="${MBR_NCNM}"/>" disabled/>
 					<input type="hidden" name="CTI_MBR_NM" value="<c:out value="${MBR_NM}"/>" />
 				</c:if> 				
			</td>
			<th class="text-left text-middle">연락처</th>
			<td class="text-center text-middle">
				<c:if test="${IS_ONLY_CTI_MBR eq true}">
 					<input class="form-control " type="text" name="CTI_MBR_HP" value="${pfpu:phoneHypen(MBR_HP)}" autocomplete="off" />
 				</c:if>
 				<c:if test="${IS_ONLY_CTI_MBR eq false}">
 					<c:choose>
						<c:when test="${IS_JOIN_MBR eq 'Y'}">
							<c:choose>
								<c:when test="${empty CALL_MBR_HP}">
									<input class="form-control bg-white border-0" type="text" value="${pfpu:phoneHypen(MBR_HP)}" disabled/>
									<input type="hidden" name="CTI_MBR_HP" value="${MBR_HP}" />
									<input type="hidden" name="CTI_MBR_HP_UPD_YN" value="N" />
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${MBR_HP eq CALL_MBR_HP}">
											<input class="form-control bg-white border-0" type="text" value="${pfpu:phoneHypen(MBR_HP)}" disabled/>
											<input type="hidden" name="CTI_MBR_HP" value="${MBR_HP}" />
										</c:when>
										<c:otherwise>
											<div class="col-md-12 text-left ">
												<div class="radio-custom radio-default radio-inline">
													<input type="radio" id="CTI_MBR_HP_CHK_Y${CUSTOM_UNIQ_KEY}" name="CTI_MBR_HP_CHK" value="${MBR_HP}" checked />
													<label for="CTI_MBR_HP_CHK_Y${CUSTOM_UNIQ_KEY}">${pfpu:phoneHypen(MBR_HP)} (현재)</label>
												</div>
											</div>
											<div class="col-md-12 text-left ">
												<div class="radio-custom radio-default radio-inline">
													<input type="radio" id="CTI_MBR_HP_CHK_N${CUSTOM_UNIQ_KEY}" name="CTI_MBR_HP_CHK" value="${CALL_MBR_HP}" />
								  					<label for="CTI_MBR_HP_CHK_N${CUSTOM_UNIQ_KEY}" class="font-weight-800 green-600">${pfpu:phoneHypen(CALL_MBR_HP)}</label>
												</div>
											</div>
											<div class="col-md-12 text-left ">
												<div class="checkbox-custom checkbox-warning">
								     				<input type="checkbox" id="CTI_MBR_HP_UPD${CUSTOM_UNIQ_KEY}" name="CTI_MBR_HP_UPD" value="Y" >
								                  	<label for="CTI_MBR_HP_UPD${CUSTOM_UNIQ_KEY}" class="font-weight-bold orange-600 font-size-12">회원정보에 연락처를 선택한 번호로 변경합니다.</label>                  	
								                </div>
							                </div>
											<input type="hidden" name="CTI_MBR_HP" value="${MBR_HP}" />
											<input type="hidden" name="CTI_MBR_HP_UPD_YN" value="N" />
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>						
						</c:when>
						<c:otherwise>
							<input class="form-control bg-white border-0" type="text" value="${pfpu:phoneHypen(MBR_HP)}" disabled/>
							<input type="hidden" name="CTI_MBR_HP" value="${MBR_HP}" />
							<input type="hidden" name="CTI_MBR_HP_UPD_YN" value="N" />
						</c:otherwise>
					</c:choose>					
				</c:if>
			</td>
		</tr>
		<tr>
			<th class="text-left text-middle">주소</th>
 			<td class="text-center text-middle" colspan="3">
 				<input class="form-control bg-white border-0" type="text" name="DTL_ADDR" value="" disabled/>
			</td>
		</tr>
		<tr>
			<th class="text-left text-middle">교육 이수 이력</th>
 			<td class="text-center text-middle" colspan="3">
 				<div class="row col-md-12 w-p100 m-0 pl-0 pr-0">
	 				<div class="col-md-10 pl-0 pr-10 div-selec2-inner${CUSTOM_UNIQ_KEY}">
		 				<select class="form-control selec2_manual-${CUSTOM_UNIQ_KEY}" id="sel_ajax_mbrhstry_detail_info_${CUSTOM_UNIQ_KEY}" data-style="btn-dark text-white" >
		 				</select>
		 			</div>	
		 			<div class="col-md-2 pl-0 pr-0 text-right">
		 				<button type="button" class="btn btn-outline btn-warning btn-round pl-10 pr-10" id="clk_show_crtf_${CUSTOM_UNIQ_KEY}">이수증 출력하기</button>
	 				</div>
 				</div>
			</td>
		</tr>
		</tbody>
		<tfoot>
		<tr>
			<td class="text-left" colspan="4" >
				<span class="blue-grey-400 font-size-12">* 회원인 경우 회원 상세 정보에 대한 추가 및 변경은 상단 <b>회원조회</b> 타이틀 옆 [회원정보 수정하기] 버튼을 이용해주세요.</span>
			</td>
		</tr>
		</tfoot>
	</table>
	</form>
	
	<style>
	/* div.div-selec2-inner${CUSTOM_UNIQ_KEY} .select2-container { z-index:1700 !important; } */
	.select2-container { z-index:1700 !important; }
	</style>
	<script>
	$(function(){
		$(".selec2_manual-${CUSTOM_UNIQ_KEY}").select2();
    	$('.selectpicker_manual-${CUSTOM_UNIQ_KEY}').selectpicker();
    	//$("div.div-selec2-inner${CUSTOM_UNIQ_KEY} .select2-container").css('z-index','1700!important');
    });
	$('.clk-mbr-view${CUSTOM_UNIQ_KEY}').click(function(){
		var mbr_id = $(this).attr('data-mbr-id');
	   	$.ajax({
	   		type:"POST",
	   		url :'/eduadm/member/view.do',
	   		data:{
	   			MBR_ID:mbr_id,
	   		},
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
	});
	$('#clk_show_crtf_${CUSTOM_UNIQ_KEY}').click(function(){
		var crtf_sn = $('#sel_ajax_mbrhstry_detail_info_${CUSTOM_UNIQ_KEY} option:selected').attr('data-crtf-sn');
		var crtf_on = $('#sel_ajax_mbrhstry_detail_info_${CUSTOM_UNIQ_KEY} option:selected').attr('data-crtf-on');
		if(typeof crtf_sn == "undefined" || crtf_sn.length=='') {
			alertify.alert('이수 완료된 교육을 선택해주세요.');
			return;
		}
		if(typeof crtf_on != "undefined" && crtf_on.length!='' && crtf_on == 'N') {
			alertify.alert('현재 가이수 상태로 출력이 불가합니다.');
			return;
		}
		alertify.prompt('이수증 발급 사유를 입력해주세요.',function(val, e) {
    		//ok
    		if(val.trim()=='') {
   				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
   				return;
   			} 
    		//console.log('crtf_sn : '+crtf_sn);
    		var gsWin = window.open("about:blank","winCrtf");
        	var form = document.getElementById('showCrtfForm');
        	form.CRTF_SN.value = crtf_sn;
        	form.MBR_ID.value = '<c:out value="${MBR_ID}"/>';
        	form.CRTF_TYPE_ST.value = val;
        	form.LOG_TYPE.value = 'cti';
        	form.action = "/eduadm/certificate/view.do";
        	form.target = "winCrtf";
        	form.submit();         	
    	});   
	});
	$(function(){
		$.ajax({
    		type:"POST",
    		url :"/cti/mbrhstry/ajax_list.do",
    		data:{
    			MBR_ID:'<c:out value="${MBR_ID}"/>',
    		},
    		dataType: "json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function(xhr, opts) {
    			$('#sel_ajax_mbrhstry_detail_info_${CUSTOM_UNIQ_KEY}').html('<option value="">교육이수이력없음</option>');
    	    },
    		success: function(data, status, xhr) {
    			//console.log('success!');    			
    			if(data.error == '1') {
					alertify.alert(data.msg);
				} else {
					var json = JSON.parse(data.rawdata);
					var htmlString = '';
					if(json.length == 0) htmlString = '<option value="">교육이수이력없음</option>';
					for (i=0; i<json.length; i++) {	
						var item = json[i];
						htmlString += '<option value="'+item.hmbr_sn+'" data-crtf-sn="'+item.crtf_sn+'" data-crtf-on="'+(item.lrnng_st=='1'?'Y':'N')+'">'+item.lrnng_cmplt_dt_str+' / '+item.crs_nm+' / '+item.crs_place+'</option>';
					}
					$('#sel_ajax_mbrhstry_detail_info_${CUSTOM_UNIQ_KEY}').html(htmlString);
				}
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			//console.log(jqXHR);
    			//console.log(textStatus);
    			//console.log(errorThrown);
    		}
    	});   
	});
	var isEmptyDetailInfo = '${isEmptyDetailInfo}'=='true'?true:false;
	$('.sel-ajax-detail-info-${CUSTOM_UNIQ_KEY}').change(function(){
		var target = $(this).find('option:selected');
		updateUiDetailInfo(target);
	});
	function updateUiDetailInfo(obj) {
		var form = document.getElementById('formAjaxDetail${CUSTOM_UNIQ_KEY}');
		form.SIDO_CD_NM.value = obj.attr('data-sido-cd-nm');
		form.SIGNGU_CD_NM.value = obj.attr('data-signgu-cd-nm');
		form.DTL_CD_NM.value = obj.attr('data-dtl-cd-nm');
		form.DTL_NM.value = obj.attr('data-dtl-nm');
		form.DTL_ADDR.value = obj.attr('data-dtl-addr');		
	}
	if(!isEmptyDetailInfo) updateUiDetailInfo($('.sel-ajax-detail-info-${CUSTOM_UNIQ_KEY} option:selected'));
	</script>