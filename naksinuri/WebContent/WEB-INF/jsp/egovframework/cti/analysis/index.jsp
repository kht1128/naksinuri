<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<link rel="stylesheet" href="/common/css/daterangepicker.min.css">
<script src="/common/js/moment.min.js"></script>
<script type="text/javascript" src="/common/js/jquery.daterangepicker.min.js"></script>

<style>
.w300px{width:300px;}
</style>

<!-- Page -->
<div class="page cti-contents">
	<div class="page-content container-fluid">
		<div class="row">
		    <form:form commandName="ctiCallHistoryVO" id="listForm_call_hist" name="listForm_call_hist" method="post">
		    	<input type="hidden" name="HCALL_STR_DT" value=""/>
		    	<input type="hidden" name="HCALL_END_DT" value=""/>
		    </form:form>
			<!--  { 통계 검색 조건 -->
			<div class="col-xl-12">
				<div class="panel mb-20">
	          		<div class="dash-title">
	          			통계 검색 조건
	          		</div>
		  			<div class="panel-body pb-0">
		  				<form:form commandName="ctiAnalysisVO" id="listForm_analysis" name="listForm_analysis" method="post">
							<input type="hidden" name="selectDays" value="${selectDays}"/>
							<div class="input-group float-right">
		           				<div class="input-group col-lg-12 pl-0 pr-0 text-right">
		           					<c:set var="strdtlabel" value="${str_dt}"/>
									<c:set var="enddtlabel" value="${end_dt}"/>
									<div class="input-group col-lg-4 pl-0 pr-0">
				    					<div class="input-group-addon">
						           			<span class="input-group-text">검색기간</span> 
						           		</div>
			            				<input class="form-control" id="date-range-analysis" name="searchRangeDate" value="${strdtlabel} ~ ${enddtlabel}" autocomplete="off">
		            				</div>
									<span class="input-group-append">
										<button type="button" class="btn btn-warning clk_search_btn_analysis"><i class="icon wb-search" aria-hidden="true"></i> 조회</button>
									</span>
								</div>
								<div class="col-lg-12 pl-0 pr-0 text-right">
									<button type="button" class="btn btn-outline btn-success w300px btn-act-excel-down" data-toggle="tooltip" data-original-title="엑셀 다운로드">
										<i class="icon wb-download m-0" aria-hidden="true"></i> 엑셀 다운로드
									</button>
								</div>
							</div>
							<input style="VISIBILITY: hidden; WIDTH: 0px">						
						</form:form>
		  			</div>
		    	</div>
		    </div>
			<!--  } 통계 검색 조건 -->
			
			<!--  { 상담사별 통화건 -->
			<div class="col-xl-12">
				<div class="panel mb-20">
	          		<div class="dash-title">
	          			상담사별 통화건 <span class="ml-10 font-weight-100 blue-grey-400" id="analysis_staff_report_title"></span>
	          		</div>
		  			<div class="panel-body pb-0">
		  				<!-- 리스트 -->
						<div class="panel" id="analysis_staff_report_ajax_body"></div>
						<!-- End of 리스트 -->
		  			</div>
				</div>
			</div>
			<!--  } 상담사별 통화건 -->
			
			<!--  { 상담분류별 -->
			<div class="col-xl-12">
				<div class="panel mb-20">
	          		<div class="dash-title">
	          			상담사별 상담분류건 <span class="ml-10 font-weight-100 blue-grey-400" id="analysis_ivr_report_title"></span>
	          		</div>
		  			<div class="panel-body pb-0">
		  				<!-- 리스트 -->
						<div class="panel" id="analysis_ivr_report_ajax_body"></div>
						<!-- End of 리스트 -->
		  			</div>
				</div>
			</div>
			<!--  } 상담분류별 -->
			
			<!--  { 상담사별 IVR 요청건 실 상담분류 처리 비교 -->
			<div class="col-xl-12">
				<div class="panel mb-20">
	          		<div class="dash-title">
	          			상담사별 IVR 요청건 대비 상담분류 처리 비교 <span class="ml-10 font-weight-100 blue-grey-400" id="analysis_ivr_compare_report_title"></span>
	          		</div>
		  			<div class="panel-body pb-0">
		  				<!-- 리스트 -->
						<div class="panel" id="analysis_ivr_compare_report_ajax_body"></div>
						<!-- End of 리스트 -->
		  			</div>
				</div>
			</div>
			<!--  } 상담사별 IVR 요청건 실 상담분류 처리 비교 -->
						
			<!--  { 상담사별 민원처리건 -->
			<div class="col-xl-12">
				<div class="panel mb-20">
	          		<div class="dash-title">
	          			상담사별 민원처리건 <span class="ml-10 font-weight-100 blue-grey-400" id="analysis_cvpl_report_title"></span>
	          		</div>
		  			<div class="panel-body pb-0">
		  				<!-- 리스트 -->
						<div class="panel" id="analysis_cvpl_report_ajax_body"></div>
						<!-- End of 리스트 -->
		  			</div>
				</div>
			</div>
			<!--  } 상담사별 민원처리건 -->							
				
		</div>
	</div>
		
</div>
<!-- End Page -->

	<style>
	.datepicker-dropdown{z-index:99999!important;}
	</style>

    <script>
    $(function(){
    	$(".selec2_manual-curriculum").select2();
    	$('.selectpicker_manual').selectpicker();
    	//엔터키 처리부
    	$("#listForm_analysis input[type=text]").keypress(function(e) { 
    	    if (e.keyCode == 13){
    	    	$('.clk_search_btn_analysis').click();
    	    }    
    	});
    	//End of 엔터키 처리부
    	//엔터키 처리부
    	$("#listForm_callhstry input[type=text]").keypress(function(e) { 
    	    if (e.keyCode == 13){
    	    	$('.clk_search_btn_callhstry').click();
    	    }    
    	});
    	//End of 엔터키 처리부
    });

    $('#date-range-analysis').dateRangePicker({
    	autoClose: false,
    	format: 'YYYY-MM-DD',
    	language: 'ko',//'auto',
    	separator: ' ~ ',
    	startOfWeek: 'sunday',// or monday    	
    	time: {
    		enabled: false
    	},
    	minDays: 0,
    	maxDays: 365,    	
    	showTopbar: true,    	
    	monthSelect: true,
    	yearSelect: true
    });


  	//교육 조회 및 검색 ajax
    $(".clk_search_btn_analysis").click(function() {
    	fn_analysis_staff_report();
    	fn_analysis_ivr_report();
    	fn_analysis_ivr_compare_report();
    	fn_analysis_cvpl_report();
    }); 
    function fn_analysis_staff_report(){
    	var form = document.getElementById('listForm_analysis');
    	form.action = '';
    	$.ajax({
    		type:"POST",
    		url :"/cti/analysis/staff/report_ajax.do",
    		data:$('#listForm_analysis').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function() {
       			//console.log('before!');
       			//$('.trg_btn_submit').addClass('disabled');
       			$("#analysis_staff_report_ajax_body").html(ajaxLoadingHtmlTag());
       		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$("#analysis_staff_report_ajax_body").html(data);
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
    }
    function fn_analysis_ivr_report(){
    	var form = document.getElementById('listForm_analysis');
    	form.action = '';
    	$.ajax({
    		type:"POST",
    		url :"/cti/analysis/ivr/report_ajax.do",
    		data:$('#listForm_analysis').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function() {
       			//console.log('before!');
       			//$('.trg_btn_submit').addClass('disabled');
       			$("#analysis_ivr_report_ajax_body").html(ajaxLoadingHtmlTag());
       		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$("#analysis_ivr_report_ajax_body").html(data);
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
    } 
    function fn_analysis_ivr_compare_report(){
    	var form = document.getElementById('listForm_analysis');
    	form.action = '';
    	$.ajax({
    		type:"POST",
    		url :"/cti/analysis/ivr_compare/report_ajax.do",
    		data:$('#listForm_analysis').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function() {
       			//console.log('before!');
       			//$('.trg_btn_submit').addClass('disabled');
       			$("#analysis_ivr_compare_report_ajax_body").html(ajaxLoadingHtmlTag());
       		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$("#analysis_ivr_compare_report_ajax_body").html(data);
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
    } 
    function fn_analysis_cvpl_report(){
    	var form = document.getElementById('listForm_analysis');
    	form.action = '';
    	$.ajax({
    		type:"POST",
    		url :"/cti/analysis/cvpl/report_ajax.do",
    		data:$('#listForm_analysis').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function() {
       			//console.log('before!');
       			//$('.trg_btn_submit').addClass('disabled');
       			$("#analysis_cvpl_report_ajax_body").html(ajaxLoadingHtmlTag());
       		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$("#analysis_cvpl_report_ajax_body").html(data);
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
    } 
    $(document).ready(function(){
    	fn_analysis_staff_report();
    	fn_analysis_ivr_report();
    	fn_analysis_ivr_compare_report();
    	fn_analysis_cvpl_report();
    });
    $(".btn-act-excel-down").click(function() {
    	fn_dwld_excel_report();
    });
    function fn_dwld_excel_report(){
    	var form = document.getElementById('listForm_analysis');
    	var form2 = document.getElementById('listForm_call_hist');
    	//form.action = '';
    	var rangeDate = form.searchRangeDate.value.split(" ~ ");
    	form2.HCALL_STR_DT.value = rangeDate[0];
    	form2.HCALL_END_DT.value = rangeDate[1];
    	console.log(rangeDate[0]);
    	console.log(rangeDate[1]);
    	form2.action = "/cti/analysis/dwld/excel/report_ajax.do";
    	form2.submit();
    }
    </script>
        
<%@ include file="../tail.jsp" %>
