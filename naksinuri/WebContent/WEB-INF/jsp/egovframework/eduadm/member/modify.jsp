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

<form:form commandName="CodeSetVO" id="modalMemberWriteFormSido" name="modalMemberWriteFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="logMemberModifyVO" id="ajaxLogMbrModListFormFirst" name="ajaxLogMbrModListFormFirst" method="post">
<input type="hidden" name="MBR_ID" value="${info.MBR_ID}"/>
<input type="hidden" name="pageIndex" value="1"/>
<input type="hidden" name="pageUnit" value="5"/>
</form:form>

<script>
function auto_sel_area_sido(target_id,sido_cd,signgu_cd) {
	var target = $('#'+target_id).parent().find(".sel_area_signgu");
	var val = $('#'+target_id).val();
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
					//alertify.alert(data.msg);
				} else {
					var json = JSON.parse(data.rawdata);
					var htmlString = '<option value="">시군구선택</option>';
					for (i=0; i<json.length; i++) {	
						var item = json[i];
						htmlString += '<option value="'+item.cd_id+'" '+(item.cd_id == signgu_cd?'selected':'')+' >'+item.cd_nm+'</option>';
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
}
</script>

<form:form commandName="eduMyHistoryVO" id="listForm2" name="listForm2" class="modal-content form-horizontal" method="post">
	<input type="hidden" name="MBR_ID" value="${info.MBR_ID}">
	<input type="hidden" name="CRS_SN">
</form:form>

<form:form commandName="eduCertificateVO" id="crtfForm" name="crtfForm" method="post">
	<input type="hidden" id="MBR_ID" name="MBR_ID" value="${info.MBR_ID}"/>
	<input type="hidden" id="CRTF_SN" name="CRTF_SN" value=""/>
	<input type="hidden" name="CRTF_TYPE_ST" value=""/>
	<input type="hidden" name="LOG_UPD_MSG" value="" />
</form:form>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/eduadm/member/modify_act.do">
	<input type="hidden" name="searchYear" value="${info.searchYear}"/>
	<input type="hidden" id="typeStr" name="typeStr" value="dtl"/>
	<input type="hidden" id="MHC_SN" name="MHC_SN" value="${MHC_SN }"/>
	<input type="hidden" name="MBR_SCRTY_KEY" value="${MBR_SCRTY_KEY }"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">회원(교육대상자)정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >회원번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_SN}" disabled>
				<input type="hidden" name="MBR_SN" value="${info.MBR_SN}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >아이디</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_ID}" disabled>
				<input type="hidden" name="MBR_ID" value="${info.MBR_ID}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >별명 또는 닉네임 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_NCNM" id="MBR_NCNM" placeholder="예) 낚시터사장님" autocomplete="off" value="${info.MBR_NCNM}" required enterDisabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >이름 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" name="MBR_NM" placeholder="" autocomplete="off" value="${info.MBR_NM}" >
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 이름을 입력해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >생년월일 <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-birth-input-pattern" data-pattern-cnt="0" name="MBR_BIRTH" placeholder="" autocomplete="off" value="${info.MBR_BIRTH}" required enterDisabled>
           		<span class="text-help red-600 font-size-12">* 본인인증이 가능한 생년월일을 입력해주세요.<c:if test="${fn:length(info.MBR_BIRTH)>8}">&nbsp;(<span class="blue-grey-600">기존값 : ${info.MBR_BIRTH}</span>)</c:if><br>예) 19190301</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >연락처(휴대폰번호) <span class="red-600">*</span></label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white mbr-hp-input-pattern" data-pattern-cnt="0" name="MBR_HP" placeholder="" autocomplete="off" value="${info.MBR_HP}" required enterDisabled>
				<%--<p class="form-control-plaintext">${info.MBR_HP}</p>--%>
				<span class="text-help red-600 font-size-12">* 본인인증이 가능한 휴대폰번호를 입력해주세요.<br>예) 01012345678</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >연락처(일반전화) </label>
           	<div class="col-md-9">
           		<input type="text" class="form-control bg-white mbr-tel-input-pattern" data-pattern-cnt="0" name="MBR_TEL" placeholder="" autocomplete="off" value="${info.MBR_TEL}" enterDisabled>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="MBR_EMAIL">이메일</label>
			<div class="col-md-9">
					<input type="text" class="form-control" name="MBR_EMAIL" placeholder="이메일을 입력해주세요." autocomplete="off" value="${info.MBR_EMAIL}" enterDisabled>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="MBR_ADDR1">우편번호 <span class="red-600">*</span></label>
			<div class="col-md-9 row">
				<div class="col-md-4">
					<input type="text" class="form-control readonly bg-blue-grey-100" id="MBR_ZIPCD" name="MBR_ZIPCD" placeholder="우편번호" autocomplete="off" value="${info.MBR_ZIPCD}" enterDisabled required>
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
					<input type="text" class="form-control readonly bg-blue-grey-100" id="MBR_ADDR1" name="MBR_ADDR1" placeholder="주소를 입력해주세요." autocomplete="off" value="${info.MBR_ADDR1}" enterDisabled required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" for="MBR_ADDR2">상세주소</label>
			<div class="col-md-9">
					<input type="text" class="form-control" id="MBR_ADDR2" name="MBR_ADDR2" placeholder="상세주소를 입력해주세요." autocomplete="off" value="${info.MBR_ADDR2}" enterDisabled >
			</div>
		</div>	
		<div class="form-group row">
			<legend class="col-md-3 form-control-label text-left">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="MBR_ST_Y" name="MBR_ST" value="Y" <c:if test="${info.MBR_ST eq 'Y'}">checked</c:if>>
					<label for="MBR_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="MBR_ST_N" name="MBR_ST" value="N" <c:if test="${info.MBR_ST eq 'N'}">checked</c:if>>
  					<label for="MBR_ST_N">사용안함</label>
				</div>
				<span class="text-help red-600 font-size-12"><b>삭제</b>상태에서 <b>사용함</b>으로 저장하면 다시 복구 할 수 있습니다.</span>
			</div>
		</div>		
        <div class="form-group row">
        	<label class="col-md-3 form-control-label text-left">가입일자</label>
           	<div class="col-md-9">
           		<fmt:parseDate var="parseregdatestring" value="${info.MBR_REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left">변경일자</label>
			<div class="col-md-9">
				<fmt:parseDate var="parsemoddatestring" value="${info.MBR_MOD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="moddatestring" value="${parsemoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${moddatestring}" disabled>
				<c:choose>
					<c:when test="${not empty info.MBR_REG_TYPE_CD}">
						<c:forEach var="item_category" items="${list_position_cd}">
							<c:if test="${info.MBR_REG_TYPE_CD eq item_category.CD_ID}">
								<span class="badge badge-outline badge-default">${item_category.CD_NM} 등록건</span>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise><span class="badge badge-outline badge-info">공단 등록건</span></c:otherwise>
				</c:choose>			
   			</div>
		</div>
		
		<div class="form-group row">
        	<label class="col-md-3 form-control-label text-left">정보등록일자</label>
           	<div class="col-md-9">
           		<fmt:parseDate var="parsergovmoddatestring" value="${info.GOV_MOD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate var="govmoddatestring" value="${parsergovmoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${govmoddatestring}" disabled>
			</div>
		</div>
		<div class="form-group row">
        	<label class="col-md-3 form-control-label text-left">정보수집이용동의일자</label>
           	<div class="col-md-9 input-daterange">
           		<fmt:parseDate var="parserindvdlinfodatestring" value="${info.MBR_INDVDL_INFO_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<input type="hidden" class="form-control" name="MBR_INDVDL_INFO_DT_BEFORE" value="${info.MBR_INDVDL_INFO_DT}" >
				<fmt:formatDate value="${parserindvdlinfodatestring}" pattern="yyyy-MM-dd" var="MBR_INDVDL_INFO_DT_DATE"/>
				<fmt:formatDate value="${parserindvdlinfodatestring}" pattern="HH:mm:ss" var="MBR_INDVDL_INFO_DT_TIME"/>
				<div class="input-group">
					<span class="input-group-text">
						<i class="form-control-icon wb-calendar pl-12"></i>	
					</span>
					<input type="text" class="form-control datepickerStr" name="INDVDL_INFO_DT_DATE" value="${MBR_INDVDL_INFO_DT_DATE}" placeholder="날짜">
				</div>
					<div class="input-group pl-5">
						<span class="input-group-text">
							<i class="form-control-icon wb-time pl-12"></i>
						</span>
	   					<input type="text" class="form-control timepickerStr" name="INDVDL_INFO_DT_TIME" value="${MBR_INDVDL_INFO_DT_TIME}" placeholder="시간">
	   				</div>
				<input type="hidden" class="form-control" name="MBR_INDVDL_INFO_DT" value="" >
			</div>
		</div>
		
		<c:if test="${info.UNDER_AGE_14_ST eq 'Y'}">
			<div class="form-group row">
	 			<label class="col-md-3 form-control-label text-left">14세 미만 보호자 동의 정보 <span class="red-600">*</span></label>
				<div class="col-md-9">
					<div class="col-md-12 border-top border-bottom border-default">
						<label class="col-md-3 form-control-label text-left">보호자 이름</label>
						<label class="form-control-label text-left">${info.PARNTS_MBR_NM}</label>
					</div>
					<div class="col-md-12 border-top border-bottom border-default">
						<label class="col-md-3 form-control-label text-left">보호자 연락처</label>
						<label class="form-control-label text-left">${info.PARNTS_MBR_HP}</label>
					</div>
					<div class="col-md-12 border-top border-bottom border-default">
						<label class="col-md-3 form-control-label text-left">보호자 생년월일</label>
						<label class="form-control-label text-left">${info.PARNTS_MBR_BIRTH}</label>
					</div>
					<div class="col-md-12 border-top border-bottom border-default">
						<label class="col-md-3 form-control-label text-left">관계</label>
						<label class="form-control-label text-left">${info.PARNTS_MBR_RELATIONSHIP}</label>
					</div>
				</div>
			</div>	
		</c:if>		
		<div class="form-group row">
			<div class="col-md-12 text-left">
				<a class="btn btn-round btn-outline btn-dark btn-sm clk_add_member_dtl_data" href="javascript:void(0)" >
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
							<th class="text-middle text-center" rowspan="3" colspan="2">비고</th>
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
						<c:forEach var="row" items="${list_dtl}" varStatus="status">
							<tr>
							 	<input type="hidden" name="DTL_SN" value="${row.DTL_SN}">
								<td class="text-middle text-center">
									<select class="form-control " id="tmptarget${status.index}" onchange="changeSelectBoxDtlCdColor(this)" data-style="btn-primary text-white"  name="DTL_CD" required>   
										<option value="" >업종선택</option>
										<c:forEach var="item" items="${list_target_se_cd}">
											<option value="${item.CD_ID}" <c:if test="${row.DTL_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
											<script>
												<c:if test="${row.DTL_CD eq item.CD_ID}">
													var tmptarget = '${item.CD_ID}';
													var tmpcolor = '';
													if(tmptarget == 'CIDN010200') {
														tmpcolor = '#dc7b09';
													} else if(tmptarget == 'CIDN010300') {
														tmpcolor = '#0178cd';
													}
													$("#tmptarget${status.index}").css('background-color',tmpcolor).css('color','#fff');
												</c:if>
											</script>
										</c:forEach>
									</select>
							 		<input type="text" class="form-control" name="DTL_NM" placeholder="사업장명" autocomplete="off" value="${row.DTL_NM}" >
								</td>
								<td class="text-middle text-center">
									<select class="form-control  sel_area_sido" data-style="btn-primary text-white"  name="SIDO_CD" id="SIDO_CD_${row.DTL_SN}" required>
										<option value="" >시도선택</option>
										<c:forEach var="item" items="${list_address_cd}">
											<option value="${item.CD_ID}" <c:if test="${row.SIDO_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
										</c:forEach>
									</select>
									<select class="form-control  sel_area_signgu" data-style="btn-primary text-white "  name="SIGNGU_CD" required>   
										<option value="" >시군구선택</option>
									</select>		
									<script>auto_sel_area_sido('SIDO_CD_${row.DTL_SN}','${row.SIDO_CD}','${row.SIGNGU_CD}')</script>							
								</td>
								<td class="text-top text-center">
							 		<input type="text" class="form-control" name="YMD_NM" placeholder="읍면동" autocomplete="off" value="${row.YMD_NM}" >
							 		<input type="text" class="form-control business-num-input-pattern" data-pattern-cnt="0" name="BUSINESS_NUM" placeholder="사업자번호" autocomplete="off" value="${row.BUSINESS_NUM}" >
								</td>
								<td class="text-middle text-center">
									<select class="form-control " data-style="btn-primary text-white"  name="DTL_LICENSE_CD" >   
										<c:forEach var="item" items="${list_license_se_cd}">
											<option value="${item.CD_ID}" <c:if test="${row.DTL_LICENSE_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
										</c:forEach>
									</select>
							 		<input type="text" class="form-control reg-num-cd-input-pattern" data-pattern-cnt="0" name="REG_NUM_CD" placeholder="신고번호" autocomplete="off" value="${row.REG_NUM_CD}" >
								</td>
								<td class="text-middle text-center">
									<fmt:parseDate var="parse_ship_license_str_dt" value="${row.SHIP_LICENSE_STR_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           							<fmt:formatDate var="ship_license_str_dt" value="${parse_ship_license_str_dt}" pattern="yyyy-MM-dd" />
							 		<input type="text" class="form-control datepickerModalStr" name="SHIP_LICENSE_STR_DT" placeholder="유효기간시작일자" autocomplete="off" value="${ship_license_str_dt}" >
							 		<fmt:parseDate var="parse_ship_license_end_dt" value="${row.SHIP_LICENSE_END_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           							<fmt:formatDate var="ship_license_end_dt" value="${parse_ship_license_end_dt}" pattern="yyyy-MM-dd" />
							 		<input type="text" class="form-control datepickerModalEnd" name="SHIP_LICENSE_END_DT" placeholder="유효기간종료일자" autocomplete="off" value="${ship_license_end_dt}" >
								</td>
								<td class="text-middle text-center">
							 		<input type="text" class="form-control blue-600 ship-cd-input-pattern" data-pattern-cnt="0" name="SHIP_CD" placeholder="어선번호" autocomplete="off" value="${row.SHIP_CD}" >
							 		<input type="text" class="form-control blue-600" name="SHIP_GRTG" placeholder="총톤수" autocomplete="off" value="${row.SHIP_GRTG}" >
								</td>
								<td class="text-middle text-center">
							 		<input type="text" class="form-control blue-600" name="SHIP_PRLOAD" placeholder="선적항" autocomplete="off" value="${row.SHIP_PRLOAD}" >
							 		<input type="text" class="form-control blue-600" name="SHIP_MAX_PSNGER" placeholder="최대승객수" autocomplete="off" value="${row.SHIP_MAX_PSNGER}" >
								</td>	
								<td class="text-middle text-center">
							 		<%--<input type="text" class="form-control blue-600" name="SHIP_LICENSE" placeholder="해기사면허" autocomplete="off" value="${row.SHIP_LICENSE}" >--%>
							 		<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="SHIP_LICENSE" >   
										<c:set var="ship_license_Y_selected" value=""/>
										<c:choose>
											<c:when test="${row.SHIP_LICENSE eq ''}"><c:set var="ship_license_Y_selected" value="1"/></c:when>
											<c:when test="${row.SHIP_LICENSE eq '유'}"><c:set var="ship_license_Y_selected" value="2"/></c:when>
											<c:otherwise><c:set var="ship_license_Y_selected" value="3"/></c:otherwise>
										</c:choose>
										<option value="" <c:if test="${ship_license_Y_selected eq '1'}">selected</c:if> >해기사면허</option>
										<option value="유" <c:if test="${ship_license_Y_selected eq '2'}">selected</c:if>>유</option>
										<option value="무" <c:if test="${ship_license_Y_selected eq '3'}">selected</c:if>>무</option>
									</select>
							 		<input type="text" class="form-control blue-600" name="SHIP_MAX_CREW" placeholder="최대선원수" autocomplete="off" value="${row.SHIP_MAX_CREW}" >
								</td>
								<td class="text-top text-center">
									<select class="form-control selectpicker_manual" data-style="btn-outline btn-warning" name="FSHLC_WORK_CD" >
										<option value="" >업구분</option>   
										<c:forEach var="item_category" items="${list_fshlc_work_cd}">
											<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq row.FSHLC_WORK_CD }">selected</c:if> >${item_category.CD_NM}</option>
										</c:forEach> 			
				      				</select>
							 		<input type="text" class="form-control orange-600" name="FSHLC_APPLC" placeholder="적용수면" autocomplete="off" value="${row.FSHLC_APPLC}" >
								</td>
								<td class="text-top text-center">
							 		<input type="text" class="form-control orange-600" name="DTL_ADDR" placeholder="사업장주소" autocomplete="off" value="${row.DTL_ADDR}" >
								</td>
								<td class="text-top text-left">
									<select class="form-control " data-style="btn-primary text-white"  name="USE_AT" >   
										<option value="Y" <c:if test="${row.USE_AT eq 'Y'}">selected</c:if> >사용중</option>
										<option value="N" <c:if test="${row.USE_AT eq 'N'}">selected</c:if> >사용안함</option>
									</select>
									<a class="btn btn-squared btn-outline btn-default btn-sm " onclick="clk_del_member_dtl_data(this)"><i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>삭제</a>
								</td>
								<td class="text-top text-left">
									<c:choose>
										<c:when test="${not empty row.REG_TYPE_CD}">
											<c:forEach var="item_category" items="${list_position_cd}">
												<c:if test="${row.REG_TYPE_CD eq item_category.CD_ID}">
													<span class="badge badge-outline badge-default">${item_category.CD_NM} 등록건</span>
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise><span class="badge badge-outline badge-info">공단 등록건</span></c:otherwise>
									</c:choose>		
								</td>
							</tr>
						</c:forEach>				
					</tbody>
					<tfoot>						
					</tfoot>				  
				</table>
				<p class="orange-600 " style="font-size:11px;">ㆍ추가 상세정보를 하나 이상 등록시 해당 내용의 항목 중 <b>(*)표시 항목</b>은 필수로 입력해야 하는 정보이므로 미입력시 등록이 되지 않으며 (*)표시가 없는 항목은 미입력이 가능합니다.</p>
				<p class="orange-600 " style="font-size:11px;">ㆍ법인업자는 사업자번호에 법인번호를 입력하시면 됩니다.</p>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left">교육정보 상세목록</label>
			<div class="col-md-12 mt-10 table-responsive">
				<table class="table /*table-hover footable footable-paging footable-paging-center*/">
					<colgroup>
					</colgroup>
				   	<thead>
						<tr>
							<th class="text-middle text-center">상태</th>
							<th class="text-middle text-center">대상년도</th>
							<th class="text-middle text-center">교육그룹</th>
							<th class="text-middle text-center">교육과정명</th>
							<th class="text-middle text-center">교육기관</th>										
							<th class="text-middle text-center">비고</th>
							<th class="text-middle text-center">이수증</th>
						</tr>
				       	</thead>
					<tbody>	
						<c:forEach var="row" items="${list_edu_target}">
							<c:if test="${empty row.CAT_INS_NM}">
							<tr>
								<td class="text-middle text-center"><span class="badge badge-outline badge-default">신청내역없음</span></td>
								<td class="text-middle text-center">${row.TRGT_YEAR}</td>
								<td class="text-middle text-center" colspan="5">-</td>
							</tr>
							</c:if>
							<c:if test="${not empty row.CAT_INS_NM}">
							<tr>
								<td class="text-middle text-center">
									<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
									<c:choose>
										<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
											<c:choose>
												<c:when test="${row.MBR_LRNNG_ST eq 1 && row.MBR_LRNNG_CMPLT_ST eq 1 }">
													<span class="badge badge-outline badge-success">이수완료</span>
												</c:when>
												<c:when test="${row.MBR_LRNNG_ST eq 0 && row.MBR_LRNNG_CMPLT_ST eq 1 }">
													<span class="badge badge-outline badge-info">가이수</span>
												</c:when>
												<c:otherwise>
													<span class="badge badge-outline badge-dark">미이수</span>
												</c:otherwise>
											</c:choose>
										</c:when>
										<%-- 해양경찰서 담당자 --%>
										<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
											
										</c:when>
										<%-- 지자체 담당자 --%>
										<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
											<c:choose>
												<c:when test="${row.MBR_LRNNG_ST eq 1 && row.MBR_LRNNG_CMPLT_ST eq 1 }">
													<span class="badge badge-outline badge-success">이수완료</span>
												</c:when>
												<c:otherwise>
													<span class="badge badge-outline badge-dark">미이수</span>
												</c:otherwise>
											</c:choose>
										</c:when>
										<%-- 교육기관 담당자 --%>
										<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
											<c:choose>
												<c:when test="${row.MBR_LRNNG_ST eq 1 && row.MBR_LRNNG_CMPLT_ST eq 1 }">
													<span class="badge badge-outline badge-success">이수완료</span>
												</c:when>
												<c:otherwise>
													<span class="badge badge-outline badge-dark">미이수</span>
												</c:otherwise>
											</c:choose>
										</c:when>
										<%-- 기타  --%>
										<c:otherwise>
										
										</c:otherwise>
									</c:choose>
								</td>
								<td class="text-middle text-center">${row.TRGT_YEAR}</td>
								<td class="text-middle text-center">${row.CRS_GRP_NM}</td>
								<td class="text-middle text-center">
									<fmt:parseDate value="${fn:replace(row.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
									<fmt:parseDate value="${fn:replace(row.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
									<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.MM.dd" var="CRS_DT"/>
									<fmt:formatDate value="${parse_crs_str_dt}" pattern="HH:mm" var="CRS_DT_TIME_STR"/>
									<fmt:formatDate value="${parse_crs_end_dt}" pattern="HH:mm" var="CRS_DT_TIME_END"/>
									${row.CRS_NM}<br>(교육정보 ${CRS_DT} ${CRS_DT_TIME_STR}~${CRS_DT_TIME_END} , ${row.CRS_PLACE })
								</td>
								<td class="text-middle text-center">${row.CAT_INS_NM}</td>	
								<td class="text-middle text-center">
									<c:choose>
										<c:when test="${row.USE_AT eq 'N' && row.DEL_AT == 'Y'}"><span class="badge badge-outline badge-danger">삭제됨</span></c:when>
										<c:when test="${row.USE_AT eq 'N' && row.DEL_AT == 'N'}"><span class="badge badge-outline badge-warning">사용안함</span></c:when>
										<c:otherwise>
											<%-- <c:if test="${row.CMPLT_ST ne '1'}">
												<span class="badge badge-outline badge-default">교육중</span>
											</c:if> --%>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="text-middle text-center">
									<c:choose>
										<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
										<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
											<c:choose>
												<c:when test="${row.MBR_LRNNG_ST eq 1 && row.MBR_LRNNG_CMPLT_ST eq 1 }">
													<button type="button" class="btn btn-info btn-sm" data-crtf-sn="${row.CRTF_SN }" onClick="showCrtf(this)">이수증 출력하기</button>
												</c:when>
												<c:when test="${row.MBR_LRNNG_ST eq 0 && row.MBR_LRNNG_CMPLT_ST eq 1 }">
													<button type="button" class="btn btn-info btn-sm" data-crtf-sn="${row.CRTF_SN }" onClick="showCrtf(this)">이수증 출력하기</button>
												</c:when>
											</c:choose>
										</c:when>
										<%-- 해양경찰서 담당자 --%>
										<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
											
										</c:when>
										<%-- 지자체 담당자 --%>
										<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
											<c:choose>
												<c:when test="${row.MBR_LRNNG_ST eq 1 && row.MBR_LRNNG_CMPLT_ST eq 1 }">
													<button type="button" class="btn btn-info btn-sm" data-crtf-sn="${row.CRTF_SN }" onClick="showCrtf(this)">이수증 출력하기</button>
												</c:when>
											</c:choose>
										</c:when>
										<%-- 교육기관 담당자 --%>
										<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
											<c:if test="${isKoreafcaMember eq true}">
												<c:choose>
													<c:when test="${row.MBR_LRNNG_ST eq 1 && row.MBR_LRNNG_CMPLT_ST eq 1 }">
														<button type="button" class="btn btn-info btn-sm" data-crtf-sn="${row.CRTF_SN }" onClick="showCrtf(this)">이수증 출력하기</button>
													</c:when>
												</c:choose>
											</c:if>
										</c:when>
										<%-- 기타  --%>
										<c:otherwise>
										
										</c:otherwise>
									</c:choose>
								</td>						
							</tr>
							</c:if>							
						</c:forEach>				
					</tbody>
					<tfoot>						
					</tfoot>				  
				</table>
			</div>
		</div>
		
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left">교육신청 <span class="badge badge-pill badge-warning orange-600 badge-outline">전체  ${edu_list_totcnt }건</span></label>
			<div class="col-md-12 p-0 input-group table-responsive">
				<div class="col-md-10">
				<select class="form-control selec2_manual" id="curriculum_info">
					<c:forEach var="item" varStatus="status" items="${edu_list}">
						<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
			       		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
			       		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
			       		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
			       		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) " var="CRS_STR_DT"/>
			       		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH:mm" var="CRS_END_DT"/>
			       		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd" var="RECRUIT_STR_DT"/>
			       		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd" var="RECRUIT_END_DT"/>
			       		<fmt:formatDate value="${parse_crs_str_dt}" pattern="HH:mm" var="CRS_STR_TIME"/>
			       		<fmt:formatDate value="${parse_crs_end_dt}" pattern="HH:mm" var="CRS_END_TIME"/>
			       		
			       		<option value="${item.CRS_SN}">
			       			<c:forEach var="cate" items="${list_all_edu_grp_cd}">
			           			<c:if test="${item.CRS_GRP_CD eq cate.CD_ID}">${cate.CD_NM}</c:if>
							</c:forEach>
							| ${item.CRS_NM} 
							| ${CRS_STR_DT}&nbsp;${CRS_STR_TIME}&nbsp;~&nbsp;${CRS_END_TIME} <span class="grey-500">(${RECRUIT_STR_DT}&nbsp;~&nbsp;${RECRUIT_END_DT})</span>
							| <span class="font-size-12">${item.CRS_PLACE}</span> <span class="font-size-12 grey-500">${item.CRS_ADDR}</span>
							| <span class="badge badge-warning">${item.MBR_CUR_CNT}명</span>&nbsp;/&nbsp;
				              <span class="badge badge-info">
								<c:choose>
		                			<c:when test="${item.MBR_MAX_CNT eq 0}">무제한</c:when>
		                			<c:otherwise>${item.MBR_MAX_CNT}명</c:otherwise>				                	
			                	</c:choose>
			                  </span>
			       		</option>
			       		
					</c:forEach>
				</select>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-warning clk-edu-add" id="mbrhstry_write_submit">교육 신청하기</button>
				</div>
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
	$('.datepickerStr').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	$('.timepickerStr').timepicker({
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
			'target' : $('.business-num-input-pattern'),
			"default_pattern" : ["{{999}}-{{99}}-{{99999}}",10],
			"over_pattern" : [['{{999999}}-{{9999999}}',13]],
			"automatch" : true,
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

$("#modal_action_form").on("submit", function(event) {
	event.preventDefault();
	var form = document.getElementById('modal_action_form');
	if(form.MBR_BIRTH.value.trim().length < 10) {
		alertify.alert('생년월일 형식에 맞게 8자리(19190301)를 입력해주세요.');
		return;
	}
	
	//낚시어선은 해기사면허 검증
	var isLockShipLicense = false;
	var dtl_cd_length = $(this).find('[name=DTL_CD]').length;
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
			});	 */
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
	
	// 정보수집이용동의일자
	if(form.INDVDL_INFO_DT_DATE.value != '' && form.INDVDL_INFO_DT_TIME.value != ''){
		form.MBR_INDVDL_INFO_DT.value = form.INDVDL_INFO_DT_DATE.value + ' ' + form.INDVDL_INFO_DT_TIME.value + ':00';
	}else{
		// 둘중하나라도 값이 없을 경우
		form.MBR_INDVDL_INFO_DT.value = form.MBR_INDVDL_INFO_DT_BEFORE.value;
	}
	
	// End of 정보수집이용동의일자
	
	//if(isClickRequestLocked()) {
	//	return;
	//}
		
	//default
	$.ajax({
		type:"POST",
		url :"/eduadm/member/modify_act.do",
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
			$('#btn_submit').prop('disabled', true);
		},
		complete : function() {
			//console.log('complete!');
			$('#btn_submit').prop('disabled', false);
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
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += '		<select class="form-control " onchange="changeSelectBoxDtlCdColor(this)" data-style="btn-primary text-white"  name="DTL_CD" required>';  
	htmlTag += '			<option value="" >업종선택</option>';
	htmlTag += '			<c:forEach var="item" items="${list_target_se_cd}">';
	htmlTag += '				<option value="${item.CD_ID}">${item.CD_NM}</option>';
	htmlTag += '			</c:forEach>';
	htmlTag += '		</select>';
	htmlTag += ' 		<input type="text" class="form-control" name="DTL_NM" placeholder="사업장명" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += '		<select class="form-control s sel_area_sido" data-style="btn-primary text-white"  name="SIDO_CD" required>';
	htmlTag += '			<option value="" >시도선택</option>';
	htmlTag += '			<c:forEach var="item" items="${list_address_cd}">';
	htmlTag += '				<option value="${item.CD_ID}">${item.CD_NM}</option>';
	htmlTag += '			</c:forEach>';
	htmlTag += '		</select>';
	htmlTag += '		<select class="form-control  sel_area_signgu" data-style="btn-primary text-white "  name="SIGNGU_CD" required>';   
	htmlTag += '			<option value="" >시군구선택</option>';
	htmlTag += '		</select>';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-top text-center">';
	htmlTag += ' 		<input type="text" class="form-control" name="YMD_NM" placeholder="읍면동" autocomplete="off" value="" >';
	htmlTag += ' 		<input type="text" class="form-control business-num-input-pattern-write" data-pattern-cnt="0" name="BUSINESS_NUM" placeholder="사업자번호" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += '		<select class="form-control " data-style="btn-primary text-white"  name="DTL_LICENSE_CD" required>';
	htmlTag += '			<option value="">대상구분선택</option>';
	htmlTag += '			<c:forEach var="item" items="${list_license_se_cd}">';
	htmlTag += '				<option value="${item.CD_ID}">${item.CD_NM}</option>';
	htmlTag += '			</c:forEach>';
	htmlTag += '		</select>';
	htmlTag += ' 		<input type="text" class="form-control reg-num-cd-input-pattern-write" data-pattern-cnt="0" name="REG_NUM_CD" placeholder="신고번호" autocomplete="off" value="" >';
	htmlTag += '	</td>';
	htmlTag += '	<td class="text-middle text-center">';
	htmlTag += ' 		<input type="text" class="form-control datepickerStr" name="SHIP_LICENSE_STR_DT" placeholder="유효기간시작일자" autocomplete="off" value="" >';
	htmlTag += ' 		<input type="text" class="form-control datepickerEnd" name="SHIP_LICENSE_END_DT" placeholder="유효기간종료일자" autocomplete="off" value="" >';
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
	htmlTag += '		<select class="form-control blue-600" data-style="btn-default text-white"  name="SHIP_LICENSE" >';   
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
	/*
	htmlTag += '		<select class="form-control " data-style="btn-primary text-white"  name="USE_AT" >';   
	htmlTag += '			<option value="1" <c:if test="${item.USE_AT eq '1'}">selected</c:if> >사용함</option>';
	htmlTag += '			<option value="0" <c:if test="${item.USE_AT eq '0'}">selected</c:if> >사용안함</option>';
	htmlTag += '		</select>';
	*/
	htmlTag += '		<input type="hidden" name="USE_AT" value="">';
	htmlTag += '		<a class="btn btn-squared btn-outline btn-default btn-sm " onclick="clk_del_member_dtl_data(this)"><i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>삭제</a>';
	htmlTag += '	</td>';
	htmlTag += '</tr>';
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
	htmlTag += '<script>$(document).find(".datepickerStr").removeClass("hasDatepicker").datepicker({format: "yyyy-mm-dd",autoclose: true,language: "kr"});<\/script>';
	htmlTag += '<script>$(document).find(".datepickerEnd").removeClass("hasDatepicker").datepicker({format: "yyyy-mm-dd",autoclose: true,language: "kr"});<\/script>';
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

$("#mbrhstry_write_submit").click(function() {
	
	var form = document.getElementById('listForm2');
	form.CRS_SN.value = $("#curriculum_info option:selected").val();
	
	alertify.confirm("수강생으로 등록하시겠습니까?", function(){
	
		$.ajax({
			type:"POST",
			url :"/eduadm/mbrhstry/write_act.do",
			data:$('#listForm2').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data.error == '1') {
					alertify.alert(data.msg, function(){ 
						//$("#admPublicModal").modal('hide');
						//document.listForm.submit();	
					});
				} else {
					alertify.alert(data.msg, function(){ 
						//$("#admPublicModal").modal('hide');
						//document.listForm.submit();	
					});
				}
			},
			beforeSend : function() {
				//console.log('before!');
				$('#mbrhstry_write_submit').prop('disabled', true);
			},
			complete : function() {
				//console.log('complete!');
				$('#mbrhstry_write_submit').prop('disabled', false);
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
				//console.log(jqXHR);
				//console.log(textStatus);
				//console.log(errorThrown);
			}
		});
	},
	function(){});
});
//이수증 발급하기
function showCrtf(obj) {
	/*
	alertify.prompt('이수증 발급 사유를 입력해주세요.',function(val, e) {
		//ok
		if(val.trim()=='') {
				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
				return;
			} 
		var gsWin = window.open("about:blank","winCrtf");
    	var form = document.getElementById('crtfForm');
    	form.CRTF_SN.value = $(obj).attr('data-crtf-sn');
    	//form.MBR_ID.value = $(obj).attr('data-mbr-id');
    	form.CRTF_TYPE_ST.value = val;
    	form.LOG_UPD_MSG.value = val;
    	form.action = "/eduadm/certificate/view.do";
    	form.target = "winCrtf";
    	form.submit();         	
	});
	*/
	var val = prompt('이수증 발급 사유를 입력해주세요.','');
	console.log(val);
	if(val==null || val.trim()=='') {
		alertify.alert('사유를 정확히 입력해주셔야 합니다.');
		return;
	} 
	var gsWin = window.open("about:blank","winCrtf");
   	var form = document.getElementById('crtfForm');
   	form.CRTF_SN.value = $(obj).attr('data-crtf-sn');
   	form.CRTF_TYPE_ST.value = val;
   	form.LOG_UPD_MSG.value = val;
   	form.action = "/eduadm/certificate/view.do";
   	form.target = "winCrtf";
   	form.submit(); 

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
