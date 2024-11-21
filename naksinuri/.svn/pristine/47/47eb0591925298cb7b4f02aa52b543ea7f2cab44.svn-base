<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form commandName="CodeSetVO" id="modalMemberViewFormSido" name="modalMemberViewFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<script>
function auto_view_sel_area_sido(target_id,sido_cd,signgu_cd) {
	var target = $('#'+target_id).parent().find(".sel_area_signgu");
	var val = $('#'+target_id).val();
	var form = document.getElementById('modalMemberViewFormSido');
	form.CD_MASTER_ID.value = val;
	form.action = '';
	if(val=='') {
		target.html('<option value="">시군구선택</option>');
	} else {
		$.ajax({
			type:"POST",
			url :"/all/code.do",
			data:$('#modalMemberViewFormSido').serialize(),
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

<div class="panel panel-dark card card-dark card-shadow border border-dark " style="width:800px;">
  	<div class="panel-heading panel-move  draggable-move cursor-grab ">
		<h3 class="panel-title">회원(교육대상자)정보보기</span>
			<div class="panel-actions pr-0">
				<a class="panel-action icon wb-close panel-close m-0 btn-panel-act-close" aria-hidden="true" data-toggle="tooltip" data-original-title="닫기"></a>
			</div>
		</h3>		
	</div>
	<div class="panel-body p-10 scroll-y " style="height:900px;">
		<form class="form-horizontal p-20" method="post" autocomplete="off" action="">
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >회원번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control " placeholder="" autocomplete="off" value="${info.MBR_SN}" disabled>
				<input type="hidden" value="${info.MBR_SN}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >아이디</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_ID}" disabled>
				<input type="hidden" value="${info.MBR_ID}">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left " >별명 또는 닉네임</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_NCNM}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >이름</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_NM}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >생년월일</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-birth-input-pattern" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info.MBR_BIRTH}" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >연락처(휴대폰번호)</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-hp-input-pattern" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info.MBR_HP}" disabled>
				<%--<p class="form-control-plaintext">${info.MBR_HP}</p>--%>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left" >연락처(일반전화)</label>
           	<div class="col-md-9">
           		<input type="text" class="form-control mbr-tel-input-pattern" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${info.MBR_TEL}" disabled>
				<%--<p class="form-control-plaintext">${info.MBR_HP}</p>--%>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" >이메일</label>
			<div class="col-md-9">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_EMAIL}" disabled>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" >우편번호</label>
			<div class="col-md-9">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_ZIPCD}" disabled>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" >주소</label>
			<div class="col-md-9">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_ADDR1}" disabled>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label text-left" >상세주소</label>
			<div class="col-md-9">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.MBR_ADDR2}" disabled>
			</div>
		</div>	
		<div class="form-group row">
			<legend class="col-md-3 form-control-label text-left">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" value="Y" <c:if test="${info.MBR_ST eq 'Y'}">checked</c:if> disabled>
					<label for="MBR_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" value="N" <c:if test="${info.MBR_ST eq 'N'}">checked</c:if> disabled>
  					<label for="MBR_ST_N">사용안함</label>
				</div>
			</div>
		</div>		
        <div class="form-group row">
        	<label class="col-md-3 form-control-label text-left">가입일자</label>
           	<div class="col-md-9">
           		<fmt:parseDate var="parseregdatestring" value="${info.MBR_REG_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate var="regdatestring" value="${parseregdatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${regdatestring} (${info.REG_MBR_ID})" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label text-left">변경일자</label>
			<div class="col-md-9">
				<fmt:parseDate var="parsemoddatestring" value="${info.MBR_MOD_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="moddatestring" value="${parsemoddatestring}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${moddatestring} (${info.UPD_MBR_ID})" disabled>
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
			<label class="col-md-3 form-control-label text-left">메모(교육기록)</label>
			<div class="col-md-9">
				<p class="text-help font-weight-800 blue-grey-800 border border-default p-10">${info.MBR_DSCRP}</p>
			</div>
		</div>
		<c:if test="${info.UNDER_AGE_14_ST eq 'Y'}">
			<div class="form-group row">
	 			<label class="col-md-3 form-control-label text-left">14세 미만 보호자 동의 정보</label>
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
			<label class="col-md-3 form-control-label text-left">부가정보 상세목록</label>
			<div class="col-md-12 mt-10 table-responsive" style="width:990px;">
				<table class="table /*table-hover footable footable-paging footable-paging-center*/" style="width:1900px;max-width:1900px;">
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
						<c:forEach var="row" items="${list_dtl}" varStatus="status">
							<tr>
								<td class="text-middle text-center">
									<select class="form-control " id="tmptarget${status.index}" data-style="btn-primary text-white"  disabled>   
										<option value="" >업종선택</option>
										<c:forEach var="item" items="${list_target_se_cd}">
											<option value="${item.CD_ID}" <c:if test="${row.DTL_CD eq item.CD_ID}">selected</c:if>>${item.CD_NM}</option>
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
							 		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${row.DTL_NM}" disabled>
								</td>
								<td class="text-middle text-center">
									<select class="form-control  sel_area_sido" data-style="btn-primary text-white" id="view_SIDO_CD_${row.DTL_SN}" disabled>
										<option value="" >시도선택</option>
										<c:forEach var="item" items="${list_address_cd}">
											<option value="${item.CD_ID}" <c:if test="${row.SIDO_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
										</c:forEach>
									</select>
									<select class="form-control  sel_area_signgu" data-style="btn-primary text-white " disabled>   
										<option value="" >시군구선택</option>
									</select>		
									<script>auto_view_sel_area_sido('view_SIDO_CD_${row.DTL_SN}','${row.SIDO_CD}','${row.SIGNGU_CD}')</script>							
								</td>
								<td class="text-top text-center">
							 		<input type="text" class="form-control" placeholder="" autocomplete="off" value="${row.YMD_NM}" disabled>
							 		<input type="text" class="form-control business-num-input-pattern-frm" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.BUSINESS_NUM}" disabled>
								</td>
								<td class="text-middle text-center">
									<select class="form-control " data-style="btn-primary text-white" disabled>   
										<c:forEach var="item" items="${list_license_se_cd}">
											<option value="${item.CD_ID}" <c:if test="${row.DTL_LICENSE_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
										</c:forEach>
									</select>
							 		<input type="text" class="form-control reg-num-cd-input-pattern" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.REG_NUM_CD}" disabled>
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
							 		<input type="text" class="form-control blue-600 ship-cd-input-pattern" data-pattern-cnt="0" placeholder="" autocomplete="off" value="${row.SHIP_CD}" disabled>
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
										<option value="" >업구분</option>   
										<c:forEach var="item_category" items="${list_fshlc_work_cd}">
											<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq row.FSHLC_WORK_CD }">selected</c:if> >${item_category.CD_NM}</option>
										</c:forEach> 			
				      				</select>
							 		<input type="text" class="form-control orange-600" placeholder="" autocomplete="off" value="${row.FSHLC_APPLC}" disabled>
								</td>
								<td class="text-top text-center">
							 		<input type="text" class="form-control orange-600" placeholder="" autocomplete="off" value="${row.DTL_ADDR}" disabled>
								</td>
								<td class="text-top text-left">
									<c:choose>
										<c:when test="${row.USE_AT eq 'Y'}"><span class="badge badge-outline badge-default">현재 사용중</span></c:when>
										<c:otherwise><span class="badge badge-outline badge-warning">현재 사용안함</span></c:otherwise>
									</c:choose>
									<br>
									<c:forEach var="item" items="${list_position_cd}">
										<c:if test="${row.REG_TYPE_CD eq item.CD_ID}"><span class="badge badge-outline badge-default">${item.CD_NM} 등록건</span></c:if>
									</c:forEach>
									<c:if test="${empty row.REG_TYPE_CD}"><span class="badge badge-outline badge-default">공단 등록건</span></c:if>
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
							<th class="text-middle text-center">상태</th>
							<th class="text-middle text-center">대상년도</th>
							<th class="text-middle text-center">교육그룹</th>
							<th class="text-middle text-center">교육과정명</th>
							<th class="text-middle text-center">교육기관</th>										
							<th class="text-middle text-center">비고</th>
						</tr>
				       	</thead>
					<tbody>	
						<c:forEach var="row" items="${list_edu_target}">
							<c:if test="${empty row.CAT_INS_NM}">
							<tr>
								<td class="text-middle text-center"><span class="badge badge-outline badge-default">신청내역없음</span></td>
								<td class="text-middle text-center">${row.TRGT_YEAR}</td>
								<td class="text-middle text-center" colspan="4">-</td>
							</tr>
							</c:if>
							<c:if test="${not empty row.CAT_INS_NM}">
							<tr>
								<td class="text-middle text-center">
									<c:choose>
										<c:when test="${row.CMPLT_ST eq '1'}">
											<span class="badge badge-outline badge-success">이수완료</span>
										</c:when>
										<c:otherwise>
											<span class="badge badge-outline badge-dark">미이수</span>
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
							</tr>
							</c:if>							
						</c:forEach>				
					</tbody>
					<tfoot>						
					</tfoot>				  
				</table>
			</div>
		</div>
		</form>
  	</div>
  	<div class="panel-footer p-10">
  		<div class="float-right">
           <button type="button" class="btn btn-default btn-outline btn-panel-act-close">취소(닫기)</button>
		</div>
	</div>
</div>
<style>
.select2-container { width: 99.9% !important; }
</style>
<script>
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
	/* 기존방식
	$('.mbr-birth-input-pattern').formatter({
		'pattern': '{{9999}}-{{99}}-{{99}}',
		'persistent': true
	});
	$('.mbr-hp-input-pattern').formatter({
		'pattern': '{{999}}-{{9999}}-{{9999}}',
		'persistent': true
	});
	$('.mbr-tel-input-pattern').formatter({
		'pattern': '{{999}}-{{999}}-{{9999}}',
		'persistent': true
	});
	$('.ship-cd-input').formatter({
	  	'pattern': '{{9999999}}-{{9999999}}',
	  	'persistent': true
	});
	$('.reg-num-cd-input-pattern').formatter({
	  	'pattern': '{{9999}}-{{999}}',
	  	'persistent': true
	});
	*/
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
			'target' : $('.business-num-input-pattern-frm'),
			"default_pattern" : ["{{999}}-{{99}}-{{99999}}",10],
			"over_pattern" : [['{{999999}}-{{9999999}}',13]],
			"automatch" : true,
		},
	]);

});
$('.btn-panel-act-close').click(function(){
	$("#admPublicPanelLayer").html('');
	$("#admPublicPanelLayer").fadeOut('200');
});
</script>
<script defer="defer">
//------------------------------------------------------
// 브라우저에 맞게 세로 크기 조정
//------------------------------------------------------
$(function(){
	var h = 0;
	if(document.body.clientHeight == 0) {
		h = window.innerHeight-158;
	} else {
		h = document.body.clientHeight-158;
	}
	$('#admPublicPanelLayer .panel-body').css('height',h);
});
//------------------------------------------------------
//팝업 드래그 위치 변경 
//------------------------------------------------------
$("#admPublicPanelLayer").draggable({
	handle: ".draggable-move",
 	drag: function(event,ui) {
 		var maxWidth = window.innerWidth;
		var maxHeight = window.innerHeight;
		if(isEmpty(maxWidth)) {
			maxWidth = document.body.offsetWidth;
		}
		if(isEmpty(maxHeight)) {
			maxHeight = document.body.offsetHeight;
		}
		var top = ui.position.top;
		var left = ui.position.left;
		if(top < 0) {
			top = 0;
		}
		if(top >= maxHeight) {
			top = maxHeight-61;
		}
		var boxW = 998;
		var boxWmargin = 130;
		if(left < -boxW) {
			left = boxW+boxWmargin;
		}
		if(left >= maxWidth) {
			left = maxWidth-boxWmargin;
		}
		ui.position={'top': top, 'left': left};	
    },
	stop: function() {
		return $(this).css({
			height: 'auto',
	    });
	}
});
function isEmpty(str){
	if(typeof str == "undefined" || str == null || str == "" || str == "0") return true;
    else return false ;
}
//----------------------------------------------------
</script>
