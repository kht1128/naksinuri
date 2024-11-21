<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<c:set var="_today" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${_today}" pattern="yyyy" var="_year"/>
<c:set var="selectYears" value="${_year }"/>

<form:form commandName="CodeSetVO" id="searchFormSido" name="searchFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>


	

	<!-- Page -->
	<div class="page">
		<div class="page-header">
			<c:choose>
			<c:when test="${not empty subpageNum}">
				<h1 class="page-title">${subpageName}</h1>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">${depthName}</li>
					<li class="breadcrumb-item">
						<a href="${pageUrl}">${pageName}</a>
					</li>
					<li class="breadcrumb-item active">${subpageName}</li>
				</ol>
			</c:when>
			<c:otherwise>
				<h1 class="page-title">${pageName}</h1>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">${depthName}</li>
					<li class="breadcrumb-item active">${pageName}</li>
				</ol>
			</c:otherwise>											
			</c:choose>
		</div>
		<div class="page-content container-fluid">
			<div class="row">
          		<div class="col-xl-12">   
          		<!-- //content -->
          		
          			<form id="listForm" name="listForm" method="post">
					<input type="hidden" name="excel_msg" value="" />
					<input type="hidden" name="excel_type" value="" />
					<input type="hidden" name="excel_label" value="" />          		
		          	<!-- 검색폼 -->
		          	<div class="panel mb-20">
			  			<div class="panel-body">			  			
				  			<div class="input-group col-lg-12 p-0">
				  				<div class="col-lg-2 p-0">
									<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_sido" name="MBR_SIDO_CD" >
										<option value="" >시도 전체</option>
										<c:forEach var="item" items="${list_address_cd}">
											<option value="${item.CD_ID}" <c:if test="${item.CD_ID eq MBR_SIDO_CD }">selected</c:if> >${item.CD_NM}</option>
										</c:forEach>	        			
				      				</select>
								</div>
								<div class="col-lg-2 p-0 pl-10">
									<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_signgu" name="MBR_SIGNGU_CD" >
										<option value="" >시군구 전체</option>		        			
				      				</select>
								</div>
								<div class="col-lg-2 p-0 pl-10">
									<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="searchYear">
									<fmt:parseNumber var="sel_year" value="${selectYears}"/>
									<option value="" <c:if test="${empty selectYears}">selected</c:if> >전체년도대상</option>
									<c:forEach var="i" begin="2016" end="${sel_year}">
				        				<option value="${i}" <c:if test="${i eq selectYears}">selected</c:if> >${i}</option>
									</c:forEach>		        			
				      				</select>
				      			</div>
				  				<div class="col-lg-2 p-0 pl-10">
				  					<div class="input-group">
					  					<input type="text" class="form-control blue-600 datepickerModalStrSearch" id="searchStrDate" name="searchStrDate" placeholder="기간조회(시작)" autocomplete="off" value="" data-toggle="tooltip" data-original-title="시작일자를 선택하세요.">
					  					<div class="input-group-append">
									    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="searchStrDate" aria-label="Close"></button></span>
										</div>
									</div>
				  				</div>
				  				<div class="col-lg-2 p-0 pl-10">
				  					<div class="input-group">
				  						<input type="text" class="form-control blue-600 datepickerModalEndSearch" id="searchEndDate" name="searchEndDate" placeholder="기간조회(종료)" autocomplete="off" value="" data-toggle="tooltip" data-original-title="종료일자를 선택하세요.">
				  						<div class="input-group-append">
									    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="searchEndDate" aria-label="Close"></button></span>
										</div>
									</div>
				  				</div>
				  				<div class="col-lg-2 p-0 pl-10">
				  				</div>
				  			</div>
			  				<div class="input-group col-lg-12 p-0 mt-10">
			  					<div class="col-lg-3 p-0">
									<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CRS_GRP_CD_1">
									<option value="" <c:if test="${empty frm_CRS_GRP_CD}">selected</c:if> >교육그룹선택(전체보기)</option>
									<c:forEach var="item" items="${list_edu_grp_cd}">
				        				<option value="${item.CD_ID}" <c:if test="${frm_CRS_GRP_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
									</c:forEach>		        			
				      				</select>
								</div>
								<div class="col-lg-1 p-0">
									<div class="form-group text-center text-middle">
					                	<span class="text-help grey-400">또는</span>
					                </div>
								</div>
				      			<div class="col-lg-3 p-0">
									<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CRS_GRP_CD_2">
									<option value="" <c:if test="${empty frm_CRS_GRP_CD}">selected</c:if> >과거 교육그룹선택(전체보기)</option>
									<c:forEach var="item" items="${list_prev_edu_grp_cd}">
				        				<option value="${item.CD_ID}" <c:if test="${frm_CRS_GRP_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
									</c:forEach>		        			
				      				</select>
								</div>
								<div class="col-lg-2 p-0">
									<%--
					  				<select class="form-control selectpicker_manual ml-10" data-style="btn-outline btn-default" name="CRS_MBR_CD" <c:if test="${not empty LoginVO.MBR_TRGT_CD}">disabled</c:if> >
										<option value="" >교육대상(업종)전체</option>
										<c:forEach var="item" items="${list_mbr_trgt_cd}">
											<option value="${item.CD_ID}" <c:if test="${item.CD_ID eq CRS_MBR_CD }">selected</c:if> >${item.CD_NM}</option>
										</c:forEach>	        			
				      				</select>
				      			 	--%>
			      				</div>
			      				<div class="col-lg-2 p-0">
			      				</div>
							</div>
			  			</div>
			  			<div class="panel-footer">
			  				<div class="col-lg-12 text-center">
								<span class="red-600 font-weight-600 font-size-12">* 위 선택하신 조건에 따라 보고서를 다운로드 받을 수 있습니다.</span>
							</div>
						</div>
					</div>
					<!-- End 검색폼 --> 					
					</form>
					
					<!-- 본문내용 -->
            		<div class="panel">
              			<div class="panel-body">
              				<h6>다운로드 가능 목록</h6>
              				<div class="col-lg-12 p-0">
								<a class="btn btn-block btn-default excel-down btn-act-external-excel-down" 
									data-excel-type="/eduadm/report/excel_1_1.do"
					        		data-excel-label="낚시전문교육_낚시터업자_이수증_발급대장_엑셀다운로드">낚시전문교육 낚시터업자 이수증 발급대장</a>
					        	<a class="btn btn-block btn-default excel-down btn-act-external-excel-down" 
									data-excel-type="/eduadm/report/excel_1_2.do"
					        		data-excel-label="낚시전문교육_낚시어선업자_이수증_발급대장_엑셀다운로드">낚시전문교육 낚시어선업자 이수증 발급대장</a>
								<a class="btn btn-block btn-default excel-down btn-act-external-excel-down" 
									data-excel-type="/eduadm/report/excel_2_1.do"
					        		data-excel-label="낚시전문교육_낚시터업자_결과보고서_엑셀다운로드">낚시전문교육 낚시터업자 결과보고서</a>
					        	<a class="btn btn-block btn-default excel-down btn-act-external-excel-down" 
									data-excel-type="/eduadm/report/excel_2_2.do"
					        		data-excel-label="낚시전문교육_낚시어선업자_결과보고서_엑셀다운로드">낚시전문교육 낚시어선업자 결과보고서</a>
								<%--
								<a class="btn btn-block btn-default excel-down btn-act-external-excel-down" 
									data-excel-type="/eduadm/report/excel_3_1.do"
					        		data-excel-label="낚시전문교육_낚시터업자_미이수자 명단 보고_엑셀다운로드">낚시전문교육 낚시터업자 미이수자 명단 보고</a>
					        	<a class="btn btn-block btn-default excel-down btn-act-external-excel-down" 
									data-excel-type="/eduadm/report/excel_3_2.do"
					        		data-excel-label="낚시전문교육_낚시어선업자_미이수자 명단 보고_엑셀다운로드">낚시전문교육 낚시어선업자 미이수자 명단 보고</a>
								<a class="btn btn-block btn-default excel-down btn-act-external-excel-down" 
									data-excel-type="/eduadm/report/excel_4_1.do"
					        		data-excel-label="낚시전문교육_낚시터업자_과태료_부과_현황_엑셀다운로드">낚시전문교육 낚시터업자 과태료 부과 현황</a>
					        	<a class="btn btn-block btn-default excel-down btn-act-external-excel-down" 
									data-excel-type="/eduadm/report/excel_4_2.do"
					        		data-excel-label="낚시전문교육_낚시어선업자_과태료_부과_현황_엑셀다운로드">낚시전문교육 낚시어선업자 과태료 부과 현황</a>
					        	--%>
				        	</div>
						</div>
					</div>
					<!-- End //본문내용 -->
					
				<!-- End //content -->
				</div>
			</div>
		</div>
	</div>
	<!-- End Page -->


<style>
.alertify .alert .msg, .alertify .dialog .msg {padding:0px!important;padding-left:2px!important;}
</style>

<script>
$(function(){
	$('.selectpicker_manual').selectpicker();
	$('.datepickerModalStrSearch').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	$('.datepickerModalEndSearch').datepicker({
	    format: 'yyyy-mm-dd',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
});
$(".clk-clear-input").click(function() {
	var target = $(this).attr('for');
	$('#'+target).val("");
});
$("#search_sel_area_sido").change(function() {
	if(this.value=='') {
		$('#search_sel_area_signgu').html('<option value="">시군구 전체</option>').selectpicker('refresh');
		return;
	}  
	var form = document.getElementById('searchFormSido');
	form.CD_MASTER_ID.value = this.value;
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/all/code.do",
		data:$('#searchFormSido').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			if(data.error == '1') {
				alertify.alert(data.msg);
			} else {
				var json = JSON.parse(data.rawdata);
				var htmlString = '<option value="">시군구 전체</option>';
				for (i=0; i<json.length; i++) {	
					var item = json[i];
					htmlString += '<option value="'+item.cd_id+'">'+item.cd_nm+'</option>';
				}
				$('#search_sel_area_signgu').html(htmlString).selectpicker('refresh');
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
//엑셀다운로드 
$(".btn-act-external-excel-down").click( function() {
	var excel_type = $(this).attr('data-excel-type');
	var excel_label = $(this).attr('data-excel-label');
	alertify.prompt('엑셀 다운로드 사유를 입력해주세요.',function(val, e) {
		//ok
			if(val.trim()=='') {
				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
				return;
			} 
			alertify.alert('엑셀 다운로드가 완료 될 때까지 잠시 기다려 주세요.<br>데이터처리량에 따라 수분이 소요 될 수도 있습니다.',function(){
				document.listForm.action = "";
			});
	    	var form = document.getElementById('listForm');
	    	form.excel_label.value = excel_label;
	    	form.excel_type.value = excel_type;
	    	form.excel_msg.value = val;
	    	form.action = "/all/main/excel/down.do";
	    	form.submit();
		} ,function() { 
		//cancel    			
	});
});
</script>


<%@ include file="../tail.jsp" %>
