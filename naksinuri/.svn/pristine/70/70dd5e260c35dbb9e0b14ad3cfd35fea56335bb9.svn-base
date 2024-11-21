<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form commandName="CodeSetVO" id="modalMemberViewFormSido"
	name="modalMemberViewFormSido" method="post">
	<input type="hidden" name="CD_MASTER_ID" value="" />
</form:form>

<form:form commandName="eduMemberVO" id="ajaxMbrForm" name="ajaxMbrForm"
	method="post">
	<input type="hidden" name="MBR_ID" value="" />
	<input type="hidden" name="MBR_DSCRP" value="" />
</form:form>

<form:form commandName="eduMemberVO" id="dataUnityModelForm"
	name="dataUnityModelForm" method="post">
	<input type="hidden" name="MBR_IDS" value="" />
	<input type="hidden" name="MBR_NM" value="" />
	<input type="hidden" name="MBR_NCNM" value="" />
	<input type="hidden" name="MBR_TEL" value="" />
	<input type="hidden" name="MBR_HP" value="" />
	<input type="hidden" name="MBR_ADDR1" value="" />
	<input type="hidden" name="MBR_ADDR2" value="" />
	<input type="hidden" name="MBR_ZIPCD" value="" />
	<input type="hidden" name="MBR_BIRTH" value="" />
	<input type="hidden" name="MBR_DSCRP" value="" />
</form:form>

<form:form commandName="eduMemberVO" id="dataUnityForm"
	name="dataUnityForm" method="post">
	<input type="hidden" name="MBR_IDS" value="" />
	<input type="hidden" name="MBR_NM" value="" />
	<input type="hidden" name="MBR_NMs" value="" />
	<input type="hidden" name="MBR_NCNM" value="" />
	<input type="hidden" name="MBR_TEL" value="" />
	<input type="hidden" name="MBR_HP" value="" />
	<input type="hidden" name="MBR_EMAIL" value="" />
	<input type="hidden" name="MBR_ADDR1" value="" />
	<input type="hidden" name="MBR_ADDR2" value="" />
	<input type="hidden" name="MBR_ZIPCD" value="" />
	<input type="hidden" name="MBR_BIRTH" value="" />
	<input type="hidden" name="MBR_DSCRP" value="" />
	<input type="hidden" name="LOG_UPD_MSG" value="" />

	<input type="hidden" name="TO_MBR_DTL" value="" />
	<input type="hidden" name="TO_MBR_DTL_SN" value="" />
	<input type="hidden" name="TO_EDU_HSTRY" value="" />
	<input type="hidden" name="TO_EDU_HSTRY_SN" value="" />
	<input type="hidden" name="MBR_ST" value="" />
	<input type="hidden" name="FROM_MBR_DTL" value="" />
	<input type="hidden" name="FROM_MBR_DTL_SN" value="" />
	<input type="hidden" name="FROM_EDU_HSTRY" value="" />
	<input type="hidden" name="FROM_EDU_HSTRY_SN" value="" />
</form:form>


<script>
function auto_view_sel_area_sido(target_id, sido_cd, signgu_cd) {
	var target = $('#' + target_id).parent().find(".sel_area_signgu");
	var val = $('#' + target_id).val();
	var form = document.getElementById('modalMemberViewFormSido');
	
	form.CD_MASTER_ID.value = val;
	form.action = '';
	
	if (val == '') {
		target.html('<option value="">시군구선택</option>');
	} else {
		$.ajax({
			type : "POST",
			url : "/all/code.do",
			data : $('#modalMemberViewFormSido').serialize(),
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async : true,
			success : function(data, status, xhr) {
				//console.log('success!');
				if (data.error == '1') {
					//alertify.alert(data.msg);
				} else {
					var json = JSON.parse(data.rawdata);
					var htmlString = '<option value="">시군구선택</option>';
					for (i = 0; i < json.length; i++) {
						var item = json[i];
						htmlString += '<option value="'
								+ item.cd_id
								+ '" '
								+ (item.cd_id == signgu_cd ? 'selected'
										: '') + ' >' + item.cd_nm
								+ '</option>';
					}
					target.html(htmlString);
				}
			},
			complete : function() {
				//console.log('complete!');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
	}
}
</script>
<style>
.modal-dialog {
	max-width: 2300px;
}
</style>

<div class="modal-dialog modal-simple" id="mbr_data_list">
	<form:form commandName="eduMemberVO" id="dataUnityListForm" name="dataUnityListForm" method="post" class="modal-content form-horizontal" action="/eduadm/dataUnity/list.do">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<div class="col">
				<h4 class="modal-title">데이터 통합하기</h4>
			</div>
		</div>
		<div class="modal-body scroll-y">
			<div class="col-lg-12">
				<colgroup>
					<col width="40%" />
					<col width="20%" />
					<col width="40%" />
				</colgroup>
				<div class="col p-0" style="float: left;border: solid 4px #37b859;width:45%">
					<p class="bg-grey-200 p-10 text-center" style="font-size:15px;color:black;">
						<strong>받는 대상자</strong>
					</p>

					<div class="p-10 mainData scroll-y" style="height:1200px;overflow-x:hidden;">

						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">회원번호</label>
							<div class="col-md-9">
								<input type="text" class="form-control " placeholder="" autocomplete="off" value="${info1.MBR_SN}" disabled> 
								<input type="hidden" value="${info1.MBR_SN}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">아이디</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info1.MBR_ID}" disabled> 
								<input type="hidden" value="${info1.MBR_ID}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left ">별명 또는 닉네임</label>
							<div class="col-md-9">
								<input type="text" id="MBR_NCNM" class="form-control" placeholder="" autocomplete="off" value="${info1.MBR_NCNM}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">이름</label>
							<div class="col-md-9">
								<input type="text" id="MBR_NM" class="form-control" placeholder="" autocomplete="off" value="${info1.MBR_NM}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">생년월일</label>
							<div class="col-md-9">
								<input type="text" id="MBR_BIRTH" class="form-control mbr-birth-input-pattern-unt1" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info1.MBR_BIRTH}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">연락처(휴대폰번호)</label>
							<div class="col-md-9">
								<input type="text" id="MBR_HP" class="form-control mbr-hp-input-pattern-unt1" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info1.MBR_HP}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">연락처(일반전화)</label>
							<div class="col-md-9">
								<input type="text" id="MBR_TEL" class="form-control mbr-tel-input-pattern-unt1" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info1.MBR_TEL}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">이메일</label>
							<div class="col-md-9">
								<input type="text" id="MBR_EMAIL" class="form-control" placeholder="" autocomplete="off" value="${info1.MBR_EMAIL}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">우편번호</label>
							<div class="col-md-9 row">
								<div class="col-md-4">
									<input type="text" class="form-control readonly bg-blue-grey-100" id="MBR_ZIPCD" name="MBR_ZIPCD" placeholder="우편번호" autocomplete="off" value="${info1.MBR_ZIPCD}">
								</div>

								<div id="zipcodeSearchBtn">
									<button type="button" onclick="zipcode('MBR_ZIPCD','MBR_ADDR1','MBR_ADDR2','target-addr-daumapi-modal');" class="btn btn-outline btn-default btn-sm ">우편번호 찾기</button>
								</div>
								<div class="col-md-12">
									<div id="target-addr-daumapi-modal"
										style="display: none; border: 1px solid; width: 100%; height: 300px; margin-top: 10px; position: relative">
										<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer; position:absolute; right:0px; top:-1px; z-index:1"
											onclick="foldDaumPostcode()" alt="접기 버튼">
									</div>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">주소</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="MBR_ADDR1" name="MBR_ADDR1" placeholder="" autocomplete="off" value="${info1.MBR_ADDR1}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">상세주소</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="MBR_ADDR2" name="MBR_ADDR2" placeholder="" autocomplete="off" value="${info1.MBR_ADDR2}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">사용여부</label>
							<div class="col-md-9">
								<div class="radio-custom radio-default radio-inline">
									<input type="radio" value="Y" checked disabled> 
									<label for="MBR_ST_Y">사용함</label>
								</div>
								<div class="radio-custom radio-default radio-inline">
									<input type="radio" value="N" disabled> 
									<label for="MBR_ST_N">사용안함</label>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">가입일자</label>
							<div class="col-md-9">
								<fmt:parseDate var="parseregdatestring" value="${info1.MBR_REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring} (${info1.REG_MBR_ID})" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">변경일자</label>
							<div class="col-md-9">
								<fmt:parseDate var="parsemoddatestring" value="${info1.MBR_MOD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate var="moddatestring" value="${parsemoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${moddatestring} (${info1.UPD_MBR_ID})" disabled>
								<c:choose>
									<c:when test="${not empty info1.MBR_REG_TYPE_CD}">
										<c:forEach var="item_category" items="${list_position_cd}">
											<c:if test="${info1.MBR_REG_TYPE_CD eq item_category.CD_ID}">
												<span class="badge badge-outline badge-default">${item_category.CD_NM} 등록건</span>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<span class="badge badge-outline badge-info">공단 등록건</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">메모(교육기록)</label>
							<div class="col-md-9">
								<textarea class="form-control" id="typing-text-memo" placeholder="" row="5" data-mbr-id="${info1.MBR_ID}">${info1.MBR_DSCRP}</textarea>
							</div>
						</div>
						<c:if test="${info1.UNDER_AGE_14_ST eq 'Y'}">
							<div class="form-group row">
								<label class="col-md-3 form-control-label text-left">14세 미만 보호자 동의 정보</label>
								<div class="col-md-9">
									<div class="col-md-12 border-top border-bottom border-default">
										<label class="col-md-3 form-control-label text-left">보호자 이름</label> <label class="form-control-label text-left">${info1.PARNTS_MBR_NM}</label>
									</div>
									<div class="col-md-12 border-top border-bottom border-default">
										<label class="col-md-3 form-control-label text-left">보호자 연락처</label> <label class="form-control-label text-left">${info1.PARNTS_MBR_HP}</label>
									</div>
									<div class="col-md-12 border-top border-bottom border-default">
										<label class="col-md-3 form-control-label text-left">보호자 생년월일</label> <label class="form-control-label text-left">${info1.PARNTS_MBR_BIRTH}</label>
									</div>
									<div class="col-md-12 border-top border-bottom border-default">
										<label class="col-md-3 form-control-label text-left">관계</label>
										<label class="form-control-label text-left">${info1.PARNTS_MBR_RELATIONSHIP}</label>
									</div>
								</div>
							</div>
						</c:if>


						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">부가정보 상세목록</label>
							<div class="col-md-12 mt-10 table-responsive" style="width: 990px;">
								<table class="table /*table-hover footable footable-paging footable-paging-center*/" style="width: 1900px; max-width: 1900px;">
									<colgroup>
									</colgroup>
									<thead>
										<tr>
											<th class="text-middle text-center hideItem" rowspan="3">데이터 선택</th>
											<th class="text-middle text-center" colspan="5">공통정보</th>
											<th class="text-middle text-center blue-600" colspan="3">낚시어선정보</th>
											<th class="text-middle text-center orange-600" colspan="2">낚시터정보</th>
											<th class="text-middle text-center" rowspan="3">비고</th>
										</tr>
										<tr>
											<th class="text-middle text-center">업종구분</th>
											<th class="text-middle text-center">시도</th>
											<th class="text-middle text-center">읍면동</th>
											<th class="text-middle text-center">대상구분</th>
											<th class="text-middle text-center">유효기간시작일자</th>
											<th class="text-middle text-center blue-600">어선번호</th>
											<th class="text-middle text-center blue-600">선적항</th>
											<th class="text-middle text-center blue-600">해기사면허</th>
											<th class="text-middle text-center orange-600" rowspan="2">적용수면</th>
											<th class="text-middle text-center orange-600" rowspan="2">사업장주소</th>
										</tr>
										<tr>
											<th class="text-middle text-center">어선명<br>/낚시터명</th>
											<th class="text-middle text-center">시군구</th>
											<th class="text-middle text-center">사업자번호<br>(법인번호)</th>
											<th class="text-middle text-center">신고번호(어선)<br>/허가(등록)번호(낚시터)</th>
											<th class="text-middle text-center">유효기간종료일자</th>
											<th class="text-middle text-center blue-600">총톤수</th>
											<th class="text-middle text-center blue-600">최대승객수</th>
											<th class="text-middle text-center blue-600">최대선원수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row" items="${list_dtl_1}" varStatus="status">
											<tr class="orginl_mainData_mbrDtl">
												<td class="text-top text-right hideItem">
													<div class="text-center">
														<div class="radio-custom radio-default radio-inline">
															<input class="to-mbr-dtl-radio-item btn-check-item" type="radio" id="TO_SEL_DTL_DATA_${status.index}_1" 
																name="TO_SEL_DTL_DATA_${status.index}" value="1" data-dtl-sn="${row.DTL_SN}" checked> 
															<label for="TO_SEL_DTL_DATA_${status.index}_1">유지</label>
														</div>
														<br>
														<div class="radio-custom radio-default radio-inline">
															<input class="to-mbr-dtl-radio-item btn-check-item" type="radio" id="TO_SEL_DTL_DATA_${status.index}_2"
																name="TO_SEL_DTL_DATA_${status.index}" value="3" data-dtl-sn="${row.DTL_SN}"> 
															<label for="TO_SEL_DTL_DATA_${status.index}_2">삭제</label>
														</div>
													</div>
												</td>
												<td class="text-middle text-center">
													<select class="form-control " id="tmptarget${status.index}_1" data-style="btn-primary text-white" disabled>
														<option value="">업종선택</option>
														<c:forEach var="item" items="${list_target_se_cd_1}">
															<option value="${item.CD_ID}"
																<c:if test="${row.DTL_CD eq item.CD_ID}">selected</c:if>>${item.CD_NM}</option>
															<script>
																<c:if test="${row.DTL_CD eq item.CD_ID}">
																var tmptarget = '${item.CD_ID}';
																var tmpcolor = '';
																if (tmptarget == 'CIDN010200') {
																	tmpcolor = '#dc7b09';
																} else if (tmptarget == 'CIDN010300') {
																	tmpcolor = '#0178cd';
																}
																$("#tmptarget${status.index}_1").css('background-color',tmpcolor).css('color','#fff');
																</c:if>
															</script>
														</c:forEach>
													</select> 
													<input type="text" class="form-control" placeholder="" autocomplete="off" value="${row.DTL_NM}" disabled>
												</td>
												<td class="text-middle text-center">
													<select class="form-control  sel_area_sido" data-style="btn-primary text-white" id="view_SIDO_CD_${row.DTL_SN}" disabled>
														<option value="">시도선택</option>
														<c:forEach var="item" items="${list_address_cd_1}">
															<option value="${item.CD_ID}"
																<c:if test="${row.SIDO_CD eq item.CD_ID}">selected</c:if>>${item.CD_NM}</option>
														</c:forEach>
													</select> 
													<select class="form-control  sel_area_signgu" data-style="btn-primary text-white " disabled>
														<option value="">시군구선택</option>
													</select> 
													<script>
														auto_view_sel_area_sido('view_SIDO_CD_${row.DTL_SN}', '${row.SIDO_CD}', '${row.SIGNGU_CD}')
													</script>
												</td>
												<td class="text-top text-center"><input type="text" class="form-control" placeholder="" autocomplete="off" value="${row.YMD_NM}" disabled> 
													<input type="text" class="form-control business-num-input-pattern-unt1" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.BUSINESS_NUM}" disabled>
												</td>
												<td class="text-middle text-center">
													<select class="form-control" data-style="btn-primary text-white" disabled>
														<c:forEach var="item" items="${list_license_se_cd_1}">
															<option value="${item.CD_ID}" <c:if test="${row.DTL_LICENSE_CD eq item.CD_ID}">selected</c:if>>${item.CD_NM}</option>
														</c:forEach>
													</select> 
													<input type="text" class="form-control reg-num-cd-input-pattern-unt1" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.REG_NUM_CD}" disabled>
												</td>
												<td class="text-middle text-center">
													<fmt:parseDate var="parse_ship_license_str_dt" value="${row.SHIP_LICENSE_STR_DT}" pattern="yyyy-MM-dd HH:mm:ss"/> 
													<fmt:formatDate var="ship_license_str_dt" value="${parse_ship_license_str_dt}" pattern="yyyy-MM-dd"/>
														<input type="text" class="form-control datepickerModalStr" placeholder="" autocomplete="off" value="${ship_license_str_dt}" disabled> 
													<fmt:parseDate var="parse_ship_license_end_dt" value="${row.SHIP_LICENSE_END_DT}" pattern="yyyy-MM-dd HH:mm:ss"/> 
													<fmt:formatDate var="ship_license_end_dt" value="${parse_ship_license_end_dt}" pattern="yyyy-MM-dd" />
														<input type="text" class="form-control datepickerModalEnd" placeholder="" autocomplete="off" value="${ship_license_end_dt}" disabled>
												</td>
												<td class="text-middle text-center">
													<input type="text" class="form-control blue-600 ship-cd-input-pattern-unt1" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.SHIP_CD}" disabled> 
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_GRTG}" disabled>
												</td>
												<td class="text-middle text-center">
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_PRLOAD}" disabled>
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_MAX_PSNGER}" disabled>
												</td>
												<td class="text-middle text-center">
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_LICENSE}" disabled>
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_MAX_CREW}" disabled>
												</td>
												<td class="text-top text-center">
													<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="FSHLC_WORK_CD" disabled>
														<option value="">업구분</option>
														<c:forEach var="item_category" items="${list_fshlc_work_cd_1}">
															<option value="${item_category.CD_ID}"
																<c:if test="${item_category.CD_ID eq row.FSHLC_WORK_CD }">selected</c:if>>${item_category.CD_NM}
															</option>
														</c:forEach>
													</select> 
													<input type="text" class="form-control orange-600" placeholder="" autocomplete="off" value="${row.FSHLC_APPLC}" disabled>
												</td>
												<td class="text-top text-center">
													<input type="text" class="form-control orange-600" placeholder="" autocomplete="off" value="${row.DTL_ADDR}" disabled>
												</td>
												<td class="text-top text-left">
													<c:choose>
														<c:when test="${row.USE_AT eq 'Y'}">
															<span class="badge badge-outline badge-default">현재 사용중</span>
														</c:when>
														<c:otherwise>
															<span class="badge badge-outline badge-warning">현재 사용안함</span>
														</c:otherwise>
													</c:choose> 
													<br> 
													<c:forEach var="item" items="${list_position_cd_1}">
														<c:if test="${row.REG_TYPE_CD eq item.CD_ID}">
															<span class="badge badge-outline badge-default">${item.CD_NM} 등록건</span>
														</c:if>
													</c:forEach> 
													<c:if test="${empty row.REG_TYPE_CD}">
													<span class="badge badge-outline badge-default">공단 등록건</span>
													</c:if> 
													<span class="badge badge-outline badge-default">${row.UPD_DT}</span>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
									</tfoot>
								</table>
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
											<th class="text-middle text-center hideItem">데이터 선택</th>
											<th class="text-middle text-center">상태</th>
											<th class="text-middle text-center">대상년도</th>
											<th class="text-middle text-center">교육그룹</th>
											<th class="text-middle text-center">교육과정명</th>
											<th class="text-middle text-center">교육기관</th>
											<th class="text-middle text-center">비고</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" varStatus="status" items="${list_edu_target_1}">
											<c:if test="${empty item.CAT_INS_NM}">
												<tr>
													<td class="hideItem"></td>
													<td class="text-middle text-center"><span class="badge badge-outline badge-default">신청내역없음</span></td>
													<td class="text-middle text-center">${item.TRGT_YEAR}</td>
													<td class="text-middle text-center" colspan="4">-</td>
												</tr>
											</c:if>
											<c:if test="${not empty item.CAT_INS_NM}">
												<tr class="orginl_mainData_eduHstry">
													<td class="hideItem">
														<div class="text-center">
															<div class="radio-custom radio-default radio-inline">
																<input class="to-edu-hstry-radio-item btn-check-item" type="radio" id="TO_SEL_HSTRY_DATA_${status.index}_1"
																	name="TO_SEL_HSTRY_DATA_${status.index}" value="1" data-hmbr-sn="${item.HMBR_SN}" data-trgt-year="${item.TRGT_YEAR}" checked> 
																<label for="TO_SEL_HSTRY_DATA_${status.index}_1">유지</label>
															</div>
															<br>
															<div class="radio-custom radio-default radio-inline">
																<input class="to-edu-hstry-radio-item btn-check-item" type="radio" id="TO_SEL_HSTRY_DATA_${status.index}_2"
																	name="TO_SEL_HSTRY_DATA_${status.index}" value="3" data-hmbr-sn="${item.HMBR_SN}" data-trgt-year="${item.TRGT_YEAR}"> 
																<label for="TO_SEL_HSTRY_DATA_${status.index}_2">삭제</label>
															</div>
														</div>
													</td>
													<td class="text-middle text-center">
														<c:choose>
															<c:when test="${item.MBR_LRNNG_ST eq 1 && item.MBR_LRNNG_CMPLT_ST eq 1 }">
																<span class="badge badge-outline badge-success">이수완료</span>
															</c:when>
															<c:when test="${item.MBR_LRNNG_ST eq 0 && item.MBR_LRNNG_CMPLT_ST eq 1 }">
																<span class="badge badge-outline badge-info">가이수</span>
															</c:when>
															<c:otherwise>
																<span class="badge badge-outline badge-dark">미이수</span>
															</c:otherwise>
														</c:choose>
													</td>
													<td class="text-middle text-center">${item.TRGT_YEAR}</td>
													<td class="text-middle text-center">${item.CRS_GRP_NM}</td>
													<td class="text-middle text-center">
														<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page" /> 
														<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page" /> 
														<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.MM.dd" var="CRS_DT" /> 
														<fmt:formatDate value="${parse_crs_str_dt}" pattern="HH:mm" var="CRS_DT_TIME_STR" /> 
														<fmt:formatDate value="${parse_crs_end_dt}" pattern="HH:mm" var="CRS_DT_TIME_END" /> 
														${item.CRS_NM}<br>(교육정보 ${CRS_DT} ${CRS_DT_TIME_STR}~${CRS_DT_TIME_END} , ${item.CRS_PLACE })</td>
													<td class="text-middle text-center">${item.CAT_INS_NM}</td>
													<td class="text-middle text-center">
														<c:choose>
															<c:when test="${item.USE_AT eq 'N' && item.DEL_AT == 'Y'}">
																<span class="badge badge-outline badge-danger">삭제됨</span>
															</c:when>
															<c:when test="${item.USE_AT eq 'N' && item.DEL_AT == 'N'}">
																<span class="badge badge-outline badge-warning">사용안함</span>
															</c:when>
															<c:otherwise>
																<%-- <c:if test="${row.CMPLT_ST ne '1'}"> <span class="badge badge-outline badge-default">교육중</span></c:if> --%>
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
					</div>
				</div>

				<div class="col p-10" style="float: left; height: 1200px; width: 10%">
					<div class="row p-10">
						<button type="button" class="btn btn-block btn-info p-2" onclick="changeModel()">
							<i class="icon fa-refresh" aria-hidden="true"></i> 통합 대상자 변경하기
						</button>
					</div>
					<div class="row">
						<div class="icondemo vertical-align-middle tect-center font-size-80" style="margin: auto;">
							<i class="icon fa-chevron-left" aria-hidden="true"></i>
						</div>
					</div>
				</div>

				<div class="col p-0" style="float: left;border: solid 4px #f26555; width: 45%">

					<p class="bg-grey-200 p-10 text-center" style="font-size: 15px; color: black;">
						<strong>비워질 대상자</strong>
					</p>

					<div class="p-10 subData scroll-y" style="height: 1200px; overflow-x: hidden;">
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">회원번호</label>
							<div class="col-md-9">
								<input type="text" class="form-control " placeholder="" autocomplete="off" value="${info2.MBR_SN}" disabled> 
								<input type="hidden" value="${info2.MBR_SN}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">아이디</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info2.MBR_ID}" disabled> 
								<input type="hidden" value="${info2.MBR_ID}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left ">별명 또는 닉네임</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info2.MBR_NCNM}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">이름</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info2.MBR_NM}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">생년월일</label>
							<div class="col-md-9">
								<input type="text" class="form-control mbr-birth-input-pattern-unt2" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info2.MBR_BIRTH}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">연락처(휴대폰번호)</label>
							<div class="col-md-9">
								<input type="text" class="form-control mbr-hp-input-pattern-unt2" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info2.MBR_HP}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">연락처(일반전화)</label>
							<div class="col-md-9">
								<input type="text" class="form-control mbr-tel-input-pattern-unt2" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info2.MBR_TEL}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">이메일</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info2.MBR_EMAIL}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">우편번호</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info2.MBR_ZIPCD}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">주소</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info2.MBR_ADDR1}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">상세주소</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info2.MBR_ADDR2}" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">
								<span class="red-600"><b>* 사용여부</b></span>
							</label>
							<div class="col-md-9">
								<span class="checkbox-custom checkbox-primary">
									<div class="col-md-9 row">
										<div class="col-md-4">
											<input type="checkbox" class="sub-st" id="MBR_ST_D" name="MBR_ST" value="D"> 
											<label for="MBR_ST_D"><span class="red-600"><b>완전삭제</b></span></label>
										</div>
										<div>
											<input type="checkbox" class="sub-st" id="MBR_ST_N" name="MBR_ST" value="N"> 
											<label for="MBR_ST_N">사용안함</label>
										</div>
										<div class="col-md-12 row" style="font-size: 11px;">
											<span class="red-600">* 완전삭제 : 회원이 삭제됩니다</span>
										</div>
										<div class="col-md-12 row" style="font-size: 11px;">
											<span class="red-600">* 사용안함 : 회원이 비활성됩니다</span>
										</div>
									</div>

								</span>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">가입일자</label>
							<div class="col-md-9">
								<fmt:parseDate var="parseregdatestring" value="${info2.MBR_REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
									<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring} (${info2.REG_MBR_ID})" disabled>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">변경일자</label>
							<div class="col-md-9">
								<fmt:parseDate var="parsemoddatestring" value="${info2.MBR_MOD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate var="moddatestring" value="${parsemoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
									<input type="text" class="form-control" placeholder="" autocomplete="off" value="${moddatestring} (${info2.UPD_MBR_ID})" disabled>
								<c:choose>
									<c:when test="${not empty info2.MBR_REG_TYPE_CD}">
										<c:forEach var="item_category" items="${list_position_cd_2}">
											<c:if test="${info2.MBR_REG_TYPE_CD eq item_category.CD_ID}">
												<span class="badge badge-outline badge-default">${item_category.CD_NM} 등록건</span>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<span class="badge badge-outline badge-info">공단 등록건</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">메모(교육기록)</label>
							<div class="col-md-9">
								<p class="text-help font-weight-800 blue-grey-800 border border-default p-10">${info2.MBR_DSCRP}</p>
							</div>
						</div>
						<c:if test="${info2.UNDER_AGE_14_ST eq 'Y'}">
							<div class="form-group row">
								<label class="col-md-3 form-control-label text-left">14세 미만 보호자 동의 정보</label>
								<div class="col-md-9">
									<div class="col-md-12 border-top border-bottom border-default">
										<label class="col-md-3 form-control-label text-left">보호자 이름</label> 
										<label class="form-control-label text-left">${info2.PARNTS_MBR_NM}</label>
									</div>
									<div class="col-md-12 border-top border-bottom border-default">
										<label class="col-md-3 form-control-label text-left">보호자 연락처</label> 
										<label class="form-control-label text-left">${info2.PARNTS_MBR_HP}</label>
									</div>
									<div class="col-md-12 border-top border-bottom border-default">
										<label class="col-md-3 form-control-label text-left">보호자 생년월일</label> 
										<label class="form-control-label text-left">${info2.PARNTS_MBR_BIRTH}</label>
									</div>
									<div class="col-md-12 border-top border-bottom border-default">
										<label class="col-md-3 form-control-label text-left">관계</label>
										<label class="form-control-label text-left">${info2.PARNTS_MBR_RELATIONSHIP}</label>
									</div>
								</div>
							</div>
						</c:if>



						<div class="form-group row">
							<label class="col-md-3 form-control-label text-left">부가정보 상세목록</label>
							<div class="col-md-12 mt-10 table-responsive" style="width: 990px;">
								<table class="table /*table-hover footable footable-paging footable-paging-center*/" style="width: 1900px; max-width: 1900px;">
									<colgroup>
									</colgroup>
									<thead>
										<tr>
											<th class="text-middle text-center" rowspan="3">데이터 선택</th>
											<th class="text-middle text-center" colspan="5">공통정보</th>
											<th class="text-middle text-center blue-600" colspan="3">낚시어선정보</th>
											<th class="text-middle text-center orange-600" colspan="2">낚시터정보</th>
											<th class="text-middle text-center" rowspan="3">비고</th>
										</tr>
										<tr>
											<th class="text-middle text-center">업종구분</th>
											<th class="text-middle text-center">시도</th>
											<th class="text-middle text-center">읍면동</th>
											<th class="text-middle text-center">대상구분</th>
											<th class="text-middle text-center">유효기간시작일자</th>
											<th class="text-middle text-center blue-600">어선번호</th>
											<th class="text-middle text-center blue-600">선적항</th>
											<th class="text-middle text-center blue-600">해기사면허</th>
											<th class="text-middle text-center orange-600" rowspan="2">적용수면</th>
											<th class="text-middle text-center orange-600" rowspan="2">사업장주소</th>
										</tr>
										<tr>
											<th class="text-middle text-center">어선명<br>/낚시터명</th>
											<th class="text-middle text-center">시군구</th>
											<th class="text-middle text-center">사업자번호<br>(법인번호)</th>
											<th class="text-middle text-center">신고번호(어선)<br>/허가(등록)번호(낚시터)</th>
											<th class="text-middle text-center">유효기간종료일자</th>
											<th class="text-middle text-center blue-600">총톤수</th>
											<th class="text-middle text-center blue-600">최대승객수</th>
											<th class="text-middle text-center blue-600">최대선원수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row" items="${list_dtl_2}" varStatus="status">
											<tr>
												<td class="text-top text-right hideItem">
													<div class="text-center">
														<span class="checkbox-custom checkbox-primary"> 
															<input class="from-mbr-dtl-checkbox-item btn-check-item" type="checkbox" id="FROM_SEL_DTL_DATA_${status.index}"
																name="FROM_SEL_DTL_DATA_${status.index}" value="2" data-dtl-sn="${row.DTL_SN}"> 
															<label for="FROM_SEL_DTL_DATA_${status.index}">통합</label>
														</span>
													</div>
												</td>
												<td class="text-middle text-center">
													<select class="form-control " id="tmptarget${status.index}_2" data-style="btn-primary text-white" disabled>
														<option value="">업종선택</option>
														<c:forEach var="item" items="${list_target_se_cd_2}">
															<option value="${item.CD_ID}"<c:if test="${row.DTL_CD eq item.CD_ID}">selected</c:if>>${item.CD_NM}</option>
															<script>
																<c:if test="${row.DTL_CD eq item.CD_ID}">
																	var tmptarget = '${item.CD_ID}';
																	var tmpcolor = '';
																	if (tmptarget == 'CIDN010200') {
																		tmpcolor = '#dc7b09';
																	} else if (tmptarget == 'CIDN010300') {
																		tmpcolor = '#0178cd';
																	}
																	$("#tmptarget${status.index}_2").css('background-color',tmpcolor).css('color','#fff');
																</c:if>
															</script>
														</c:forEach>
													</select> 
													<input type="text" class="form-control" placeholder="" autocomplete="off" value="${row.DTL_NM}" disabled>
												</td>
												<td class="text-middle text-center">
													<select class="form-control  sel_area_sido" data-style="btn-primary text-white" id="view_SIDO_CD_${row.DTL_SN}" disabled>
														<option value="">시도선택</option>
														<c:forEach var="item" items="${list_address_cd_2}">
															<option value="${item.CD_ID}" <c:if test="${row.SIDO_CD eq item.CD_ID}">selected</c:if>>${item.CD_NM}</option>
														</c:forEach>
													</select> 
													<select class="form-control sel_area_signgu" data-style="btn-primary text-white " disabled>
														<option value="">시군구선택</option>
													</select> 
													<script>
														auto_view_sel_area_sido('view_SIDO_CD_${row.DTL_SN}', '${row.SIDO_CD}', '${row.SIGNGU_CD}')
													</script>
												</td>
												<td class="text-top text-center">
													<input type="text" class="form-control" placeholder="" autocomplete="off" value="${row.YMD_NM}" disabled> 
													<input type="text" class="form-control business-num-input-pattern-unt2" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.BUSINESS_NUM}" disabled>
												</td>
												<td class="text-middle text-center">
													<select class="form-control" data-style="btn-primary text-white" disabled>
														<c:forEach var="item" items="${list_license_se_cd_2}">
															<option value="${item.CD_ID}" <c:if test="${row.DTL_LICENSE_CD eq item.CD_ID}">selected</c:if>>${item.CD_NM}</option>
														</c:forEach>
													</select> 
													<input type="text" class="form-control reg-num-cd-input-pattern-unt2" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.REG_NUM_CD}" disabled>
												</td>
												<td class="text-middle text-center">
													<fmt:parseDate var="parse_ship_license_str_dt" value="${row.SHIP_LICENSE_STR_DT}" pattern="yyyy-MM-dd HH:mm:ss" /> 
													<fmt:formatDate var="ship_license_str_dt" value="${parse_ship_license_str_dt}" pattern="yyyy-MM-dd" />
														<input type="text" class="form-control datepickerModalStr" placeholder="" autocomplete="off" value="${ship_license_str_dt}" disabled> 
													<fmt:parseDate var="parse_ship_license_end_dt" value="${row.SHIP_LICENSE_END_DT}" pattern="yyyy-MM-dd HH:mm:ss" /> 
													<fmt:formatDate var="ship_license_end_dt" value="${parse_ship_license_end_dt}" pattern="yyyy-MM-dd" />
														<input type="text" class="form-control datepickerModalEnd" placeholder="" autocomplete="off" value="${ship_license_end_dt}" disabled>
												</td>
												<td class="text-middle text-center">
													<input type="text" class="form-control blue-600 ship-cd-input-pattern-unt2" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.SHIP_CD}" disabled> 
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_GRTG}" disabled>
												</td>
												<td class="text-middle text-center">
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_PRLOAD}" disabled>
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_MAX_PSNGER}" disabled>
												</td>
												<td class="text-middle text-center">
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_LICENSE}" disabled>
													<input type="text" class="form-control blue-600" placeholder="" autocomplete="off" value="${row.SHIP_MAX_CREW}" disabled>
												</td>
												<td class="text-top text-center">
													<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="FSHLC_WORK_CD" disabled>
														<option value="">업구분</option>
														<c:forEach var="item_category" items="${list_fshlc_work_cd_2}">
															<option value="${item_category.CD_ID}"
																<c:if test="${item_category.CD_ID eq row.FSHLC_WORK_CD }">selected</c:if>>${item_category.CD_NM}</option>
														</c:forEach>
													</select> 
													<input type="text" class="form-control orange-600" placeholder="" autocomplete="off" value="${row.FSHLC_APPLC}" disabled>
												</td>
												<td class="text-top text-center">
													<input type="text" class="form-control orange-600" placeholder="" autocomplete="off" value="${row.DTL_ADDR}" disabled>
												</td>
												<td class="text-top text-left">
													<c:choose>
														<c:when test="${row.USE_AT eq 'Y'}">
															<span class="badge badge-outline badge-default">현재 사용중</span>
														</c:when>
														<c:otherwise>
															<span class="badge badge-outline badge-warning">현재 사용안함</span>
														</c:otherwise>
													</c:choose> 
													<br> 
													<c:forEach var="item" items="${list_position_cd_2}">
														<c:if test="${row.REG_TYPE_CD eq item.CD_ID}">
															<span class="badge badge-outline badge-default">${item.CD_NM} 등록건</span>
														</c:if>
													</c:forEach> 
													<c:if test="${empty row.REG_TYPE_CD}">
														<span class="badge badge-outline badge-default">공단 등록건</span>
													</c:if> 
													<span class="badge badge-outline badge-default">${row.UPD_DT}</span>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
									</tfoot>
								</table>
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
											<th class="text-middle text-center">데이터 </br> 선택 </th>
											<th class="text-middle text-center">상태</th>
											<th class="text-middle text-center">대상년도</th>
											<th class="text-middle text-center">교육그룹</th>
											<th class="text-middle text-center">교육과정명</th>
											<th class="text-middle text-center">교육기관</th>
											<th class="text-middle text-center">비고</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" varStatus="status" items="${list_edu_target_2}">
											<c:if test="${empty item.CAT_INS_NM}">
												<tr>
													<td></td>
													<td class="text-middle text-center">
														<span class="badge badge-outline badge-default">신청내역없음</span>
													</td>
													<td class="text-middle text-center">${item.TRGT_YEAR}</td>
													<td class="text-middle text-center" colspan="4">-</td>
												</tr>
											</c:if>
											<c:if test="${not empty item.CAT_INS_NM}">
												<tr>
													<td class="hideItem">
														<div class="text-center">
															<span class="checkbox-custom checkbox-primary"> 
																<input class="from-edu-hstry-checkbox-item btn-check-item" type="checkbox" id="FROM_SEL_HSTRY_DATA_${status.index}"
																	name="FROM_SEL_HSTRY_DATA_${status.index}" value="2" data-hmbr-sn="${item.HMBR_SN}" data-trgt-year="${item.TRGT_YEAR}"> 
																<label for="FROM_SEL_HSTRY_DATA_${status.index}">통합</label>
															</span>
														</div>
													</td>
													<td class="text-middle text-center">
														<c:choose>
															<c:when test="${item.MBR_LRNNG_ST eq 1 && item.MBR_LRNNG_CMPLT_ST eq 1 }">
																<span class="badge badge-outline badge-success">이수완료</span>
															</c:when>
															<c:when test="${item.MBR_LRNNG_ST eq 0 && item.MBR_LRNNG_CMPLT_ST eq 1 }">
																<span class="badge badge-outline badge-info">가이수</span>
															</c:when>
															<c:otherwise>
																<span class="badge badge-outline badge-dark">미이수</span>
															</c:otherwise>
														</c:choose>
													</td>
													<td class="text-middle text-center">${item.TRGT_YEAR}</td>
													<td class="text-middle text-center">${item.CRS_GRP_NM}</td>
													<td class="text-middle text-center">
														<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/> 
														<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page" />
														<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.MM.dd" var="CRS_DT" /> 
														<fmt:formatDate value="${parse_crs_str_dt}" pattern="HH:mm" var="CRS_DT_TIME_STR" /> 
														<fmt:formatDate value="${parse_crs_end_dt}" pattern="HH:mm" var="CRS_DT_TIME_END" />
														 ${item.CRS_NM}<br>(교육정보 ${CRS_DT} ${CRS_DT_TIME_STR}~${CRS_DT_TIME_END} , ${item.CRS_PLACE })</td>
													<td class="text-middle text-center">${item.CAT_INS_NM}</td>
													<td class="text-middle text-center">
														<c:choose>
															<c:when
																test="${item.USE_AT eq 'N' && item.DEL_AT == 'Y'}">
																<span class="badge badge-outline badge-danger">삭제됨</span>
															</c:when>
															<c:when
																test="${item.USE_AT eq 'N' && item.DEL_AT == 'N'}">
																<span class="badge badge-outline badge-warning">사용안함</span>
															</c:when>
															<c:otherwise>
																<%-- <c:if test="${row.CMPLT_ST ne '1'}"><span class="badge badge-outline badge-default">교육중</span></c:if> --%>
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
					</div>
				</div>
			</div>
			
			<div class="modal-footer m-10 p-10">
				<textarea class="form-control h-80 p-10" id="LOG_UPD_MSG_FORM" placeholder="변경사유(설명)" row="5" required></textarea>
			</div>
			
			<div class="float-right btn-menu-group">
				<a href="#;" class="btn btn-outline btn-default btn-sm" onclick="clk_mbr_view('${info1.MBR_ID}')">미리보기</a>
				<button type="submit" class="btn btn-primary btn-outline btn-act-unity-data trg_btn_submit" id="btn_submit">통합하기</button>
				<button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
			</div>
		</div>
	</form:form>
</div>


<style>
.tooltip.tooltip-top, .tooltip.tooltip-bottom, .tooltip.tooltip-left,
	.tooltip.tooltip-right {
	z-index: 100000 !important;
}
.select2-container {
	width: 99.9% !important;
}
.ui-timepicker-wrapper {
	z-index: 9999 !important;
}
.token-label {
	max-width: 100% !important;
}
</style>
<script>
// 스크롤바 동기화
$(".mainData").scroll(function() {
	$(".subData").scrollTop($(".mainData").scrollTop());
	$(".subData").scrollLeft($(".mainData").scrollLeft());
});
$(".subData").scroll(function() {
	$(".mainData").scrollTop($(".subData").scrollTop());
	$(".mainData").scrollLeft($(".subData").scrollLeft());
});

// 발신 사용여부 체크박스
// radio버튼처럼 checkbox name값 설정
$('input[type="checkbox"][name="MBR_ST"]').on("click", function() {
	if ($(this).prop('checked')) {
		$('input[type="checkbox"][name="MBR_ST"]').prop('checked', false);
		$(this).prop('checked', true);
	}
});

// 통합대상자 변경하기
function changeModel() {
	var form = document.getElementById('dataUnityModelForm');
	form.MBR_IDS.value = [ '${info2.MBR_ID}', '${info1.MBR_ID}' ];

	$.ajax({
		type : "POST",
		url : "/eduadm/dataUnity/list.do",
		data : $('#dataUnityModelForm').serialize(),
		dataType : 'html',//"json",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async : true,
		success : function(data, status, xhr) {
			//console.log('success!');
			$("#mbr_data_list").html(data);
		},
		complete : function() {
			//console.log('complete!');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}

// 미리보기
function clk_mbr_view(mbr_id) {
	let a = '<div class="panel panel-dark card card-dark card-shadow border border-dark " style="height:1200px;width:900px;"><div class="panel-heading panel-move  draggable-move cursor-grab "><h3 class="panel-title">통합 전 확인하기</span><div class="panel-actions pr-0"><a class="panel-action icon wb-close panel-close m-0 btn-panel-act-close" aria-hidden="true" data-toggle="tooltip" data-original-title="닫기"></a></div>	</h3></div><div class="panel-body p-30 scroll-y " style="height:1200px;">';
	let b = '<button type="button" class="float-right btn btn-default btn-outline btn-panel-act-close" data-dismiss="modal">취소(닫기)</button>';
	let c = '</div></div>';

	$("#admPublicPanelLayer").html(a + $('.mainData').html() + b + c);
	$("#admPublicPanelLayer").show();

	var mbr_ncnm = $('#MBR_NCNM').val();
	$("#admPublicPanelLayer").find('input[id="MBR_NCNM"]').attr('value',mbr_ncnm);
	var mbr_nm = $('#MBR_NM').val();
	$("#admPublicPanelLayer").find('input[id="MBR_NM"]').attr('value',mbr_nm);
	var mbr_birth = $('#MBR_BIRTH').val();
	$("#admPublicPanelLayer").find('input[id="MBR_BIRTH"]').attr('value',mbr_birth);
	var mbr_hp = $('#MBR_HP').val();
	$("#admPublicPanelLayer").find('input[id="MBR_HP"]').attr('value',mbr_hp);
	var mbr_tel = $('#MBR_TEL').val();
	$("#admPublicPanelLayer").find('input[id="MBR_TEL"]').attr('value',mbr_tel);
	var mbr_email = $('#MBR_EMAIL').val();
	$("#admPublicPanelLayer").find('input[id="MBR_EMAIL"]').attr('value',mbr_email);
	var mbr_zipcd = $('#MBR_ZIPCD').val();
	$("#admPublicPanelLayer").find('input[id="MBR_ZIPCD"]').attr('value',mbr_zipcd);
	var mbr_addr1 = $('#MBR_ADDR1').val();
	$("#admPublicPanelLayer").find('input[id="MBR_ADDR1"]').attr('value',mbr_addr1);
	var mbr_addr2 = $('#MBR_ADDR2').val();
	$("#admPublicPanelLayer").find('input[id="MBR_ADDR2"]').attr('value',mbr_addr2);
	var mbr_memo = $('#typing-text-memo').val();
	$("#admPublicPanelLayer").find('#typing-text-memo').html(mbr_memo);

	// 1. 통합 시 발신 dtl, hstry 노출 o / +
	// toDtl
	var fromDtlCheckbox = $(".from-mbr-dtl-checkbox-item");

	fromDtlCheckbox.each(function(i) {
		var fromDtlcheck = $(".from-mbr-dtl-checkbox-item").eq(i).is(":checked");

		if (fromDtlcheck) {
			var tr = fromDtlCheckbox.parent().parent().parent().parent().eq(i).clone();
			var temp = $('#admPublicPanelLayer').find('.orginl_mainData_mbrDtl');

			$("#admPublicPanelLayer").find('tr[class=orginl_mainData_mbrDtl]:last').after(tr);
			temp.next().attr('class', 'subData_mbrDtl');
		}
	});
	
	// fromDtl
	var fromHstryCheckbox = $(".from-edu-hstry-checkbox-item");

	fromHstryCheckbox.each(function(i) {
		var fromDtlcheck = $(".from-edu-hstry-checkbox-item").eq(i).is(":checked");

		if (fromDtlcheck) {
			var tr = fromHstryCheckbox.parent().parent().parent().parent().eq(i).clone();
			var temp = $('#admPublicPanelLayer').find('.orginl_mainData_eduHstry');

			$("#admPublicPanelLayer").find('tr[class=orginl_mainData_eduHstry]:last').after(tr);
			temp.next().attr('class', 'subData_eduHstry');
		}
	});

	// 2. 삭제 시 수신 dtl, hstry 미리보기 노출 x / -
	// 모달창에서 선택한 옵션이 미리보기창에 그대로 옮겨가도록
	var toDtlRadioTemp = $(".modal-dialog").find(".to-mbr-dtl-radio-item");

	toDtlRadioTemp.each(function(i) {
		var toDtlCheckTemp = toDtlRadioTemp.eq(i).is(":checked");

		if (toDtlCheckTemp) {
			var toDtlValTemp = toDtlRadioTemp.eq(i).val();

			if (toDtlValTemp == "3") {
				var toDtlRadioTemp2 = $("#admPublicPanelLayer").find(".to-mbr-dtl-radio-item").eq(i);
				toDtlRadioTemp2.val("3").prop('checked', 'checked');
			}
		}
	});

	var toHstryRadioTemp = $(".modal-dialog").find(".to-edu-hstry-radio-item");

	toHstryRadioTemp.each(function(i) {
		var toHstryCheckTemp = toHstryRadioTemp.eq(i).is(":checked");

		if (toHstryCheckTemp) {
			var toHstryValTemp = toHstryRadioTemp.eq(i).val();

			if (toHstryValTemp == "3") {
				var toHstryRadioTemp2 = $("#admPublicPanelLayer").find(".to-edu-hstry-radio-item").eq(i);
				toHstryRadioTemp2.val("3").prop('checked', 'checked');
			}
		}
	});

	// 노출처리 
	// toDtl
	var toDtlRadio = $("#admPublicPanelLayer").find(".to-mbr-dtl-radio-item");

	toDtlRadio.each(function(i) {

		var toDtlCheck = toDtlRadio.eq(i).is(":checked");

		if (toDtlCheck) {
			var toDtlVal = toDtlRadio.eq(i).val();

			if (toDtlVal == "3") {
				var tr = toDtlRadio.eq(i)
				var trIndex = tr.parent().parent().parent().parent();
				$("#admPublicPanelLayer").find(trIndex).remove();
			}
		}
	});
	
	// toHstry
	var toHstryRadio = $("#admPublicPanelLayer").find(".to-edu-hstry-radio-item");

	toHstryRadio.each(function(i) {

		var toHstryCheck = toHstryRadio.eq(i).is(":checked");

		if (toHstryCheck) {
			var toHstryVal = toHstryRadio.eq(i).val();

			if (toHstryVal == "3") {
				var tr = toHstryRadio.eq(i)
				var trIndex = tr.parent().parent().parent().parent();
				$("#admPublicPanelLayer").find(trIndex).remove();
			}
		}
	});

	$("#admPublicPanelLayer").find("input").attr("disabled", true);
	$("#admPublicPanelLayer").find("textarea").attr("disabled", true);
	$("#admPublicPanelLayer").find("#zipcodeSearchBtn").remove();
	$("#admPublicPanelLayer").find(".hideItem").remove();
	$("#admPublicPanelLayer").find("#header").remove();
	
	$('.btn-panel-act-close').click(function() {
		$("#admPublicPanelLayer").html('');
		$("#admPublicPanelLayer").fadeOut('200');
	});
}

// 통합하기
$("#dataUnityListForm").on("submit", function(event) {
	event.preventDefault();

	var mbrstCheck = $('input:checkbox[name="MBR_ST"]').is(':checked');
	var val = $('input[type="checkbox"][name="MBR_ST"]').val();

	if (!mbrstCheck) {
		alertify.alert('삭제될 데이터의 사용여부를 선택해주세요.');
		return;
	}

	var form = document.getElementById('dataUnityForm');
	var form2 = document.getElementById('dataUnityListForm');

	var chkConfirm = confirm("데이터를 통합하시겠습니까?");
	if (chkConfirm) {

		form.MBR_IDS.value = [ '${info1.MBR_ID}','${info2.MBR_ID}' ];
		form.MBR_NM.value = $('#MBR_NM').val();
		form.MBR_NMs.value = [ '${info1.MBR_NM}','${info2.MBR_NM}' ];
		form.MBR_NCNM.value = $('#MBR_NCNM').val();
		form.MBR_ADDR1.value = $('#MBR_ADDR1').val();
		form.MBR_ADDR2.value = $('#MBR_ADDR2').val();
		form.MBR_ZIPCD.value = $('#MBR_ZIPCD').val();
		form.MBR_EMAIL.value = $('#MBR_EMAIL').val();

		var mbrTel = $('#MBR_TEL').val();
		form.MBR_TEL.value = mbrTel.replaceAll('-', '');

		var mbrHp = $('#MBR_HP').val();
		form.MBR_HP.value = mbrHp.replaceAll('-', '');

		var mbrBirth = $('#MBR_BIRTH').val();
		form.MBR_BIRTH.value = mbrBirth.replaceAll('-', '');

		var mbrMemo = $('#typing-text-memo').val();
		form.MBR_DSCRP.value = $('#typing-text-memo').val();

		// MBR_DTL_TB
		// to-mbr-dtl
		var toMbrDtlRadio = $(".to-mbr-dtl-radio-item");
		var toMbrDtlCheck = "";
		var toMbrDtlVal = [];
		var toMbrDtlSn = [];

		if (toMbrDtlRadio.length != 0) {
			for (var i = 0; i < toMbrDtlRadio.length; i++) {
				toMbrDtlCheck = toMbrDtlRadio.eq(i).is(":checked");
				if (toMbrDtlCheck) {
					toMbrDtlVal.push(toMbrDtlRadio.eq(i).val());
					toMbrDtlSn.push(toMbrDtlRadio.eq(i).attr("data-dtl-sn"));
				}
			}
		} else {
			toMbrDtlVal[0] = "0";
			toMbrDtlSn[0] = "0";
		}
		form.TO_MBR_DTL.value = toMbrDtlVal;
		form.TO_MBR_DTL_SN.value = toMbrDtlSn;

		// to-edu-hstry
		var toEduHstryRadio = $(".to-edu-hstry-radio-item");
		var toEduHstryCheck = "";
		var toEduHstryVal = [];
		var toEduHstrySn = [];

		if (toEduHstryRadio.length != 0) {
			for (var i = 0; i < toEduHstryRadio.length; i++) {
				toEduHstryCheck = toEduHstryRadio.eq(i).is(":checked");
				if (toEduHstryCheck) {
					toEduHstryVal.push(toEduHstryRadio.eq(i).val());
					toEduHstrySn.push(toEduHstryRadio.eq(i).attr("data-hmbr-sn"));
				}
			}
		} else {
			toEduHstryVal[0] = "0";
			toEduHstrySn[0] = "0";
		}
		form.TO_EDU_HSTRY.value = toEduHstryVal;
		form.TO_EDU_HSTRY_SN.value = toEduHstrySn;

		// from 체크박스 값
		// from-mbr-dtl
		var fromMbrDtlCheckbox = $(".from-mbr-dtl-checkbox-item");
		var fromMbrDtlCheck = "";//true, false
		var fromMbrDtlVal = [];
		var fromMbrDtlSn = [];

		if (fromMbrDtlCheckbox.length != 0) {
			for (var i = 0; i < fromMbrDtlCheckbox.length; i++) {
				fromMbrDtlCheck = fromMbrDtlCheckbox.eq(i).is(":checked");
				if (fromMbrDtlCheck) {
					fromMbrDtlVal.push(fromMbrDtlCheckbox.eq(i).val());
					fromMbrDtlSn.push(fromMbrDtlCheckbox.eq(i).attr("data-dtl-sn"));
				} else {
					fromMbrDtlVal.push("3");
					fromMbrDtlSn.push(fromMbrDtlCheckbox.eq(i).attr("data-dtl-sn"));
				}
			}
		} else {
			fromMbrDtlVal[0] = "0";
			fromMbrDtlSn[0] = "0";
		}
		form.FROM_MBR_DTL.value = fromMbrDtlVal;
		form.FROM_MBR_DTL_SN.value = fromMbrDtlSn;
		
		// from-edu-hstry
		var fromMbrEduHstryCheckbox = $(".from-edu-hstry-checkbox-item");
		var fromMbrEduHstryCheck = "";
		var fromMbrEduHstryVal = [];
		var fromMbrEduHstrySn = [];

		if (fromMbrEduHstryCheckbox.length != 0) {
			for (var i = 0; i < fromMbrEduHstryCheckbox.length; i++) {
				fromMbrEduHstryCheck = fromMbrEduHstryCheckbox.eq(i).is(":checked");

				if (fromMbrEduHstryCheck) {
					fromMbrEduHstryVal.push(fromMbrEduHstryCheckbox.eq(i).val());
					fromMbrEduHstrySn.push(fromMbrEduHstryCheckbox.eq(i).attr("data-hmbr-sn"));
				} else {
					fromMbrEduHstryVal.push("3");
					fromMbrEduHstrySn.push(fromMbrEduHstryCheckbox.eq(i).attr("data-hmbr-sn"));
				}
			}
		} else {
			fromMbrEduHstryVal[0] = "0";
			fromMbrEduHstrySn[0] = "0";
		}
		form.FROM_EDU_HSTRY.value = fromMbrEduHstryVal;
		form.FROM_EDU_HSTRY_SN.value = fromMbrEduHstrySn;

		// subData mbrst 값 
		var mbrStCheck = $('input:checkbox[id="MBR_ST_N"]').is(":checked");
		var mbrStVal = $('input:checkbox[id="MBR_ST_N"]').val();
		if (mbrStCheck) {
		} else {
			mbrStVal = "D";
		}
		form.MBR_ST.value = mbrStVal;

		var logUpdMsgVal = $('#LOG_UPD_MSG_FORM').val();
		form.LOG_UPD_MSG.value = logUpdMsgVal;

		var btn_this = $(".btn-act-unity-data");

		$.ajax({
			type : "POST",
			url : "/eduadm/dataUnity/unity_act.do",
			data : $('#dataUnityForm').serialize(),
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async : true,
			success : function(data, status, xhr) {
				console.log('success!');
				if(data.errCnt > 0) {
					$('.btn-menu-group').show();
   					alertify.alert(data.msg);
   				} else {
   					if(data.error == '1') {
   						$('.btn-menu-group').show();
   						alertify.alert(data.msg);
   					} else {
   						alertify.alert(data.msg,function(){
   							//location.reload();
   							$("#admPublicModal").modal('hide');
   							$('.clk_search_btn').click();
   						});
   					}
   				}
			},
			beforeSend : function(xhr, opts) {
				console.log('beforeSend!');
				if (isClickRequestLocked()) {
					xhr.abort();
					return;
				}
				$('.btn-menu-group').hide();
				btn_this.prop("disabled", true);
				ajaxLoadingToastAppend();
			},
			complete : function() {
				console.log('complete!');
				ajaxLoadingToastRemoved();
				clickRequestLockStop();
			},
			error : function(jqXHR, textStatus,errorThrown) {
				console.log('error!');
				$('.btn-menu-group').show();
				ajaxLoadingToastRemoved();
				clickRequestLockStop();
			}
		});
	} else {
		alert("데이터 통합을 취소하셨습니다.");
	}
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
			'target' : $('.mbr-birth-input-pattern-unt1'),
			'default_pattern' : ['{{9999}}-{{99}}-{{99}}',8],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.mbr-birth-input-pattern-unt2'),
			'default_pattern' : ['{{9999}}-{{99}}-{{99}}',8],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.mbr-hp-input-pattern-unt1'),
			'default_pattern' : ['{{999}}-{{9999}}-{{9999}}',11],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.mbr-hp-input-pattern-unt2'),
			'default_pattern' : ['{{999}}-{{9999}}-{{9999}}',11],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.mbr-tel-input-pattern-unt1'),
			'default_pattern' : ['{{9999}}-{{9999}}',8],
			'over_pattern' : [['{{999}}-{{999}}-{{9999}}',10],['{{999}}-{{9999}}-{{9999}}',11]],
			'automatch' : true,
		},
		{
			'target' : $('.mbr-tel-input-pattern-unt2'),
			'default_pattern' : ['{{9999}}-{{9999}}',8],
			'over_pattern' : [['{{999}}-{{999}}-{{9999}}',10],['{{999}}-{{9999}}-{{9999}}',11]],
			'automatch' : true,
		},
		{
			'target' : $('.ship-cd-input-pattern-unt1'),
			'default_pattern' : ['{{9999999}}-{{9999999}}',14],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.ship-cd-input-pattern-unt2'),
			'default_pattern' : ['{{9999999}}-{{9999999}}',14],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.reg-num-cd-input-pattern-unt1'),
			'default_pattern' : ['{{9999}}-{{999}}',7],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.reg-num-cd-input-pattern-unt2'),
			'default_pattern' : ['{{9999}}-{{999}}',7],
			'over_pattern' : null,
			'automatch' : true,
		},
		{
			'target' : $('.business-num-input-pattern-unt1'),
			"default_pattern" : ["{{999}}-{{99}}-{{99999}}",10],
			"over_pattern" : [['{{999999}}-{{9999999}}',13]],
			"automatch" : true,
		},
		{
			'target' : $('.business-num-input-pattern-unt2'),
			"default_pattern" : ["{{999}}-{{99}}-{{99999}}",10],
			"over_pattern" : [['{{999999}}-{{9999999}}',13]],
			"automatch" : true,
		},
	]);
});
$('.btn-panel-act-close').click(function() {
	$("#admPublicPanelLayer").html('');
	$("#admPublicPanelLayer").fadeOut('200');
});
</script>

<script defer="defer">
	//------------------------------------------------------
	// 브라우저에 맞게 세로 크기 조정
	//------------------------------------------------------
	$(function() {
		var h = 0;
		if (document.body.clientHeight == 0) {
			h = window.innerHeight - 158;
		} else {
			h = document.body.clientHeight - 158;
		}
		$('#admPublicPanelLayer .panel-body').css('height', h);
	});
	//------------------------------------------------------
	//팝업 드래그 위치 변경 
	//------------------------------------------------------
	$("#admPublicPanelLayer").draggable({
		handle : ".draggable-move",
		drag : function(event, ui) {
			var maxWidth = window.innerWidth;
			var maxHeight = window.innerHeight;
			if (isEmpty(maxWidth)) {
				maxWidth = document.body.offsetWidth;
			}
			if (isEmpty(maxHeight)) {
				maxHeight = document.body.offsetHeight;
			}
			var top = ui.position.top;
			var left = ui.position.left;
			if (top < 0) {
				top = 0;
			}
			if (top >= maxHeight) {
				top = maxHeight - 61;
			}
			var boxW = 998;
			var boxWmargin = 130;
			if (left < -boxW) {
				left = boxW + boxWmargin;
			}
			if (left >= maxWidth) {
				left = maxWidth - boxWmargin;
			}
			ui.position = {
				'top' : top,
				'left' : left
			};
		},
		stop : function() {
			return $(this).css({
				height : 'auto',
			});
		}
	});
	function isEmpty(str) {
		if (typeof str == "undefined" || str == null || str == "" || str == "0")
			return true;
		else
			return false;
	}
	//----------------------------------------------------
</script>


<!-- //다음주소찾기// -->
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	// 우편번호 찾기 찾기 화면을 넣을 element
	var element_wrap = null;

	function foldDaumPostcode() {
		// iframe을 넣은 element를 안보이게 한다.
		element_wrap.style.display = 'none';
	}
	function zipcode(zipcode, addr1, addr2, zipcode_layer) {
		element_wrap = document.getElementById(zipcode_layer);
		var currentScroll = Math.max(document.body.scrollTop,document.documentElement.scrollTop);

		new daum.Postcode({
			oncomplete : function(data) {
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
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
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
				element_wrap.style.height = size.height + 'px';
			},
			width : '100%',
			height : '100%'
		}).embed(element_wrap);

		// iframe을 넣은 element를 보이게 한다.
		element_wrap.style.display = 'block';
	}
</script>