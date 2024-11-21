<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<link rel="stylesheet" href="/common/css/daterangepicker.min.css">
<script src="/common/js/moment.min.js"></script>
<script type="text/javascript" src="/common/js/jquery.daterangepicker.min.js"></script>  

<c:set var="_today" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${_today}" pattern="yyyy" var="_year"/>
<fmt:parseNumber var="max_year" value="${_year }"/>
	

<form id="showCrtfForm" name="showCrtfForm" action="/eduadm/certificate/view.do" method="post">
	<input type="hidden" name="MBR_ID" value="">
	<input type="hidden" name="CRTF_SN" value="">
	<input type="hidden" name="CRTF_TYPE_ST" value="">
	<input type="hidden" name="LOG_TYPE" value="">
</form>


<!-- Page -->
<div class="page cti-contents">
	<div class="page-content container-fluid">
		<div class="row">
		    
		    <!-- //통계 정보 -->
		    <div class="col-xl-12">
		    	<div class="panel mb-20 border border-warning">
	          		<div class="dash-title">
	          			금일상담건
	          			<span onclick="fn_today_main_call_info()" class="blue-grey-400 font-size-12 cursor-pointer ml-10"><i class="fa fa-refresh" aria-hidden="true"></i> 상태정보가 갱신되지 않을 경우 새로고침을 진행해주세요.</span>
	          		</div>
		  			<div class="panel-body p-20 ">
		  				<div class="row">
							<div class="widget widget-shadow col-md-2">
								<div class="widget-content bg-white">
									<div class="content-text text-center margin-bottom-0">
										<span class="font-size-40 font-weight-600 blue-grey-600" id="ctiCallTodayCallTotalLabel">0</span>
										<p class="font-size-14 blue-grey-400 font-weight-300 margin-0">총건수</p>
	              					</div>
	            				</div>
	          				</div>	          				
	          				<div class="widget widget-shadow col-md-2">
								<div class="widget-content bg-white">
									<div class="content-text text-center margin-bottom-0">
										<span class="font-size-40 font-weight-600 blue-grey-600" id="ctiCallTodayCallRsponsLabel">0</span>
										<p class="font-size-14 blue-grey-400 font-weight-300 margin-0">대응건수</p>
	              					</div>
	            				</div>
	          				</div>	          				
	          				<div class="widget widget-shadow col-md-2">
								<div class="widget-content bg-white">
									<div class="content-text text-center margin-bottom-0">
										<span class="font-size-40 font-weight-600 blue-grey-600" id="ctiCallTodayCallDrscLabel">0</span>
										<p class="font-size-14 blue-grey-400 font-weight-300 margin-0">부재건수</p>
	              					</div>
	            				</div>
	          				</div>
	          				<div class="widget widget-shadow col-md-2">
								<div class="widget-content bg-white">
									<div class="content-text text-center margin-bottom-0">
										<span class="font-size-40 font-weight-600 blue-grey-600" id="ctiCallTodayCallCancelLabel">0</span>
										<p class="font-size-14 blue-grey-400 font-weight-300 margin-0">취소건수</p>
	              					</div>
	            				</div>
	          				</div>
	          				<div class="widget widget-shadow col-md-2">
								<div class="widget-content bg-white">
									<div class="content-text text-center margin-bottom-0">
										<span class="font-size-40 font-weight-600 blue-grey-600" id="ctiCallTodayCallDrscPtLabel">0</span>%
										<p class="font-size-14 blue-grey-400 font-weight-300 margin-0">응대율</p>
	              					</div>
	            				</div>
	          				</div>
		  				</div>
		  			</div>
		  		</div>		    
		    </div>
		    <!-- //End of 통계 정보 -->
		    
		    <!-- //상담원 상태 정보 -->
		    <div class="col-xl-12">
		    	<div class="panel mb-20">
	          		<div class="dash-title">
	          			상담원 상태 목록 
	          			<span class="badge badge-pill badge-warning badge-custom-warning orange-600">총 ${fn:length(list_staff_mbr)} 명</span>
	          			<span id="ctiManagerUiStatusRefresh" onclick="ctiwsReConnect()" class="ml-10 blue-grey-400 font-size-12 cursor-pointer"><i class="fa fa-refresh" aria-hidden="true"></i> 상태정보가 갱신되지 않을 경우 새로고침을 진행해주세요.</span>
	          		</div>
		  			<div class="panel-body p-20">
		  				<div class="input-group col-lg-12 p-0 ">
			  				<c:if test="${empty list_staff_mbr}">
								<p>선택 가능한 상담사가 없습니다.</p>
							</c:if>
							<c:set var="ctimanager_width_percent" value="20"/>
							<c:if test="${not empty list_staff_mbr}">
								<c:set var="ctimanager_width_percent" value="${100/fn:length(list_staff_mbr)}"/>
							</c:if>
							<c:forEach var="item" items="${list_staff_mbr}">							
								<div class="elementConbox text-center mr-10">
									<div class="elemenHeaderBox colorType1" id="ctiManagerUiStatusHeader${item.MBR_ID}">
										<span class="bold mr-0" id="ctiManagerUiStatusLabel${item.MBR_ID}">로그아웃</span>
										<span id="ctiManagerUiTimeLabel${item.MBR_ID}" style="display:none;">00:00:00</span>
									</div>									
									<div class="elementMiddleBox text-center">
										<span>${item.STAFF_NM}</span>
										<span>${item.CTI_TEL_NO}</span>
									</div>
								</div>
							</c:forEach>	
						</div>
		  			</div>
		  		</div>		    
		    </div>
		    <!-- //End of 상담원 상태 정보 -->
		    
		    <!--  { 접수중인 교육과정 -->
			  <div class="col-xl-12">
	          	<div class="panel mb-20">
	          		<div class="dash-title">
	          			접수중인 교육과정 
	          		</div>
		  			<div class="panel-body pb-0">
		  			
		  				<form:form commandName="ctiCallHistoryVO" id="listForm_curriculum" name="listForm_curriculum" method="post">
						<input type="hidden" name="pageIndex" value="1" />
						<input type="hidden" name="searchUseYn" value="Y" />	
						<input type="hidden" name="LOCK_ST" value="0" />
											
						<div class="input-group float-right">		  		
								
							<div class="input-group col-lg-2 pl-0 pr-2">
					            <select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CRS_GRP_CD">
									<option value="" <c:if test="${empty frm_CRS_GRP_CD}">selected</c:if> >교육그룹선택(전체보기)</option>
									<c:forEach var="item" items="${list_edu_grp_cd}">
				        				<option value="${item.CD_ID}" >${item.CD_NM}</option>
									</c:forEach>		        			
			      				</select>
				            </div>
    						<div class="input-group col-lg-3 pl-0 pr-2">
	    						<select class="form-control selec2_manual-curriculum w-p100" name="CAT_INS_SN" >   
									<c:if test="${empty list_ins_info_cd}">
										<option value="">선택 가능한 교육기관이 없습니다.</option>
									</c:if>   
									<c:if test="${not empty list_ins_info_cd}">
										<option value="">교육기관 전체보기</option>
									</c:if>
									<c:forEach var="item" items="${list_ins_info_cd}">
										<option value="${item.CAT_INS_SN}" >${item.CAT_INS_NM}</option>
									</c:forEach>
		 						</select>
				            </div>
	    					<div class="input-group col-lg-4 pl-0 pr-2">
		    					<div class="input-group-addon">
				           			<span class="input-group-text">검색기간</span> 
				           		</div>
	            				<input class="form-control" id="date-range-curriculum" name="searchRangeDate" value="" autocomplete="off">
	           				</div>
	           				<div class="input-group col-lg-3 pl-0 pr-0">
								<input type="text" class="form-control" name="searchKeyword" placeholder="검색어를 입력하세요." autocomplete="off" value="${searchKeyword}">
								<span class="input-group-append">
									<button type="button" class="btn btn-warning clk_search_btn_curriculum"><i class="icon wb-search" aria-hidden="true"></i> 검색</button>
								</span>
							</div>
						</div>	
						<input style="VISIBILITY: hidden; WIDTH: 0px">						
						</form:form>
						
		  			</div>
		  			<!-- 교육 리스트 -->
					<div class="panel" id="curriculum_list_ajax_body"></div>
					<!-- End of 교육 리스트 -->
		    	</div>
		    </div>
			<!--  } 접수중인 교육과정 -->
						
			<!--  { 상담 통화 이력 목록 -->
			<div class="col-xl-12 mb-20 mt-10 panel-info panel-line">
	          	<div class="panel mb-20">
	          		<div class="dash-title">
		          		상담 통화 이력 목록
	          		</div>
		  			<div class="panel-body pb-0">
		  				<!--  { 검색 -->
		  				<form:form commandName="ctiCallHistoryVO" id="listForm_callhstry" name="listForm_callhstry" method="post">
						<input type="hidden" name="pageUnit" value="15" />
						<input type="hidden" name="pageIndex" value="1" />
						<input type="hidden" name="searchUseYn" value="Y" />
		  				<div class="input-group float-right">
		  				
    						<div class="input-group col-lg-2 pl-0 pr-2">
	    						<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="STAFF_MBR_ID" >   
									<c:if test="${empty list_staff_mbr}">
										<option value="">선택 가능한 상담사가 없습니다.</option>
									</c:if>   
									<c:if test="${not empty list_staff_mbr}">
										<option value="">상담사 전체보기</option>
									</c:if>
									<c:forEach var="item" items="${list_staff_mbr}">
										<option value="${item.MBR_ID}" >${item.STAFF_NM}</option>
									</c:forEach>
					            </select>
				            </div>
				            
				            <div class="input-group col-lg-2 pl-0 pr-2">
	    						<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="HCALL_IVR_CD" >   
									<option value="" >상담요청IVR(전체)</option>
									<c:if test="${not empty list_category_gubun_1}">
										<c:forEach var="ivritem" items="${list_category_gubun_1}">
											<option value="${ivritem.CD_ID}" >${ivritem.CD_NM }</option>
										</c:forEach>
									</c:if>
					            </select>
				            </div>
	    					<div class="input-group col-lg-4 pl-0 pr-2">
		    					<div class="input-group-addon">
				           			<span class="input-group-text">검색기간</span> 
				           		</div>
	            				<input class="form-control" id="date-range-callhstry" name="searchRangeDate" value="" autocomplete="off">
	           				</div>
	           				<div class="input-group col-lg-4 pl-0 pr-0">
								<input type="text" class="form-control" name="searchKeyword" placeholder="이름,연락처,아이디를 입력하세요." autocomplete="off" value="${searchKeyword}">
								<span class="input-group-append">
									<button type="button" class="btn btn-warning clk_search_btn_callhstry"><i class="icon wb-search" aria-hidden="true"></i> 검색</button>
								</span>
							</div>
						</div>	
						<input style="VISIBILITY: hidden; WIDTH: 0px">
						</form:form>
						<!--  } 검색 -->
					</div>
					<!-- 자동갱신버튼 -->
					<div class="float-right" style="position:relative;">
						<div class="" style="position:absolute;right:15px;width:160px;z-index:1;">
							<div class="float-left mr-20">
							<label class="pt-3" for="inputBasicOn">목록자동갱신</label>
							</div>
							<input type="checkbox" id="IsAutoRefreshChecker" checked />
							<script>
							$(function(){
								var elem = document.querySelector('#IsAutoRefreshChecker');
								var switchery = new Switchery(elem,{
									color	: '#526069',
									size 	: 'default',
								});	
							});			
							</script>
						</div>
					</div>
					<!-- End of 자동갱신버튼 -->
					<!-- 통화 이력 리스트 -->
					<div class="panel" id="callhstry_list_ajax_body"></div>
					<!-- End of 통화 이력 리스트 -->
		             
		    	</div>
			</div>
		    <!--  } 상담 통화 이력 목록 -->
		</div>
	</div>
		
</div>
<!-- End Page -->

	<style>
	.datepicker-dropdown{z-index:99999!important;}
	</style>

    <script>
    function notificationTopAlert(message) {
    	toastr.info(message,'알림',{
			tapToDismiss: true,
			closeButton: true,
			debug: false,
			newestOnTop: true,
			progressBar: true,
			positionClass: "toast-bottom-left",
			preventDuplicates: false,
			onclick: false,
			showDuration: "300",
			hideDuration: "300",
			timeOut: 3000,
			showEasing: "swing",
			hideEasing: "linear",
			showMethod: "slideDown",//fadeIn
			hideMethod: "fadeOut",
			allowHtml: true,
		});
    }
    
    $(function(){
    	$(".selec2_manual-curriculum").select2();
    	$('.selectpicker_manual').selectpicker();
    	/*
    	$(".selec2_manual_index").select2({
    		width: '100%',
    		"language": {
    	       "noResults": function(){
    	           return "선택 가능한 상담사가 없습니다.";
    	       }
    		},
    	    escapeMarkup: function (markup) {
    	        return markup;
    	    },
    	});
    	*/
    	//엔터키 처리부
    	$("#listForm_curriculum input[type=text]").keypress(function(e) { 
    	    if (e.keyCode == 13){
    	    	$('.clk_search_btn_curriculum').click();
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
    	//상담 통화 이력 목록 자동갱신 
    	setInterval(function(){
    		var form = document.getElementById('listForm_callhstry');
    		if($("input:checkbox[id='IsAutoRefreshChecker']").is(":checked")) {
    			fn_egov_link_page_callhstry(form.pageIndex.value);
    			fn_today_main_call_info();
    		}	
    	},1000*30);
    	//End of 상담 통화 이력 목록 자동갱신 
    });
    
    $('#date-range-callhstry').dateRangePicker({
    	autoClose: false,
    	format: 'YYYY-MM-DD',
    	language: 'ko',//'auto',
    	separator: ' ~ ',
    	startOfWeek: 'sunday',// or monday
    	/*
    	getValue: function()
    	{
    		return $(this).val();
    	},
    	*/
    	/*
    	setValue: function(s)
    	{
    		if(!$(this).attr('readonly') && !$(this).is(':disabled') && s != $(this).val())
    		{
    			$(this).val(s);
    		}
    	},
    	*/
    	//startDate: false,
    	//endDate: false,
    	time: {
    		enabled: false
    	},
    	minDays: 0,
    	maxDays: 365,
    	//showShortcuts: false,
    	/*
    	shortcuts:
    	{
    		//'prev-days': [1,3,5,7],
    		//'next-days': [3,5,7],
    		//'prev' : ['week','month','year'],
    		//'next' : ['week','month','year']
    	},
    	*/
    	//customShortcuts : [],
    	//inline:false,
    	//container:'body',
    	//alwaysOpen:false,
    	//singleDate:false,
    	//lookBehind: false,
    	//batchMode: false,
    	//duration: 200,
    	//stickyMonths: false,
    	//dayDivAttrs: [],
    	//dayTdAttrs: [],
    	//applyBtnClass: '',
    	//singleMonth: 'auto',
    	/*
    	hoveringTooltip: function(days, startTime, hoveringTime)
    	{
    		return days > 1 ? days + ' ' + lang('days') : '';
    	},
    	*/
    	showTopbar: true,
    	//swapTime: false,
    	//selectForward: false,
    	//selectBackward: false,
    	//showWeekNumbers: false,
    	/*
    	getWeekNumber: function(date) //date will be the first day of a week
    	{
    		return moment(date).format('w');
    	},
    	*/
    	monthSelect: true,
    	yearSelect: true
    });
    $('#date-range-curriculum').dateRangePicker({
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
    //이수증 출력 후 리턴함수
    function directReload() {
    	
    }
  	//상담이력 조회 및 검색 ajax
    $(".clk_search_btn_callhstry").click(function() {
       	var form = document.getElementById('listForm_callhstry');
       	form.pageIndex.value = '1';
       	form.action = '';
       	$.ajax({
       		type:"POST",
       		url :"/cti/callhstry/list_ajax.do",
       		data:$('#listForm_callhstry').serialize(),
       		dataType: 'html',//"json",
       		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
       		async: true,
       		beforeSend : function() {
       			//console.log('before!');
       			//$('.trg_btn_submit').addClass('disabled');
       			$("#callhstry_list_ajax_body").html(ajaxLoadingHtmlTag());
       		},
       		success: function(data, status, xhr) {
       			//console.log('success!');
       			$("#callhstry_list_ajax_body").html(data);
       		},
       		complete : function() {
       			//console.log('complete!');
       			//$('.trg_btn_submit').removeClass('disabled');
       	    },
       		error: function(jqXHR, textStatus, errorThrown) {
       			//console.log('error!');
       			//console.log(jqXHR);
       			//console.log(textStatus);
       			//console.log(errorThrown);
       		}
       	});
    });   
  	function fn_egov_link_refresh_callhstry() {
  		var form = document.getElementById('listForm_callhstry');
		fn_egov_link_page_callhstry(form.pageIndex.value);
		fn_today_main_call_info();
  	}
    function fn_egov_link_page_callhstry(pageNo){
    	var form = document.getElementById('listForm_callhstry');
    	form.pageIndex.value = pageNo;
    	form.action = '';
    	$.ajax({
    		type:"POST",
    		url :"/cti/callhstry/list_ajax.do",
    		data:$('#listForm_callhstry').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function() {
       			//console.log('before!');
       			//$('.trg_btn_submit').addClass('disabled');
       			$("#callhstry_list_ajax_body").html(ajaxLoadingHtmlTag());
       		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$("#callhstry_list_ajax_body").html(data);
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
  	//교육 조회 및 검색 ajax
    $(".clk_search_btn_curriculum").click(function() {
       	var form = document.getElementById('listForm_curriculum');
       	form.pageIndex.value = '1';
       	form.action = '';
       	$.ajax({
       		type:"POST",
       		url :"/cti/curriculum/list_ajax.do",
       		data:$('#listForm_curriculum').serialize(),
       		dataType: 'html',//"json",
       		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
       		async: true,
       		beforeSend : function() {
       			//console.log('before!');
       			//$('.trg_btn_submit').addClass('disabled');
       			$("#curriculum_list_ajax_body").html(ajaxLoadingHtmlTag());
       		},
       		success: function(data, status, xhr) {
       			//console.log('success!');
       			$("#curriculum_list_ajax_body").html(data);
       		},
       		complete : function() {
       			//console.log('complete!');
       			//$('.trg_btn_submit').removeClass('disabled');
       	    },
       		error: function(jqXHR, textStatus, errorThrown) {
       			//console.log('error!');
       			//console.log(jqXHR);
       			//console.log(textStatus);
       			//console.log(errorThrown);
       		}
       	});
    }); 
    function fn_egov_link_page_curriculum(pageNo){
    	var form = document.getElementById('listForm_curriculum');
    	form.pageIndex.value = pageNo;
    	form.action = '';
    	$.ajax({
    		type:"POST",
    		url :"/cti/curriculum/list_ajax.do",
    		data:$('#listForm_curriculum').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function() {
       			//console.log('before!');
       			//$('.trg_btn_submit').addClass('disabled');
       			$("#curriculum_list_ajax_body").html(ajaxLoadingHtmlTag());
       		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$("#curriculum_list_ajax_body").html(data);
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
    function fn_today_main_call_info() {
    	$.ajax({
    		type:"POST",
    		url :"/cti/analysis/call/ajax_main_today.do",
    		data:null,
    		dataType: "json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function() {
       			//console.log('before!');
       			/* var loadingbar = '<div class="loader vertical-align-middle loader-circle" data-type="default"></div>';
       			$('#ctiCallTodayCallTotalLabel').html(loadingbar);
				$('#ctiCallTodayCallDrscLabel').html(loadingbar);
				$('#ctiCallTodayCallRsponsLabel').html(loadingbar);
				$('#ctiCallTodayCallDrscPtLabel').html(loadingbar);
				$('#ctiCallTodayCallCancelLabel').html(loadingbar); */
       		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			if(data.error == '1') {
					htmlString = '';
				} else {
					var json = JSON.parse(data.rawdata);
					//console.log(json);
					$('#ctiCallTodayCallTotalLabel').html(json.call_total);
					$('#ctiCallTodayCallDrscLabel').html(json.call_drsc);
					$('#ctiCallTodayCallRsponsLabel').html(json.call_rspons);
					$('#ctiCallTodayCallDrscPtLabel').html(json.call_drsc_pt);
					$('#ctiCallTodayCallCancelLabel').html(json.call_cancel);
				}
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			console.log('error!');
    			console.log(jqXHR);
    			console.log(textStatus);
    			console.log(errorThrown);
    		}
    	});       	
    } 
    function fn_calling_mbr_total_info(callkey) {
    	
    	var target_obj = $('#ctiCallingMbrTotalInfoBody'+callkey);
    	var call_number = target_obj.attr('data-call-number'); 
    	
    	$.ajax({
    		type:"POST",
    		url :"/cti/callhstry/last_info.do",
    		data:{
    			MBR_HP : call_number,
    		},
    		dataType: "json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function() {
       			console.log('before!');
       		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			if(data.error == '1') {
					htmlString = '';
				} else {
					var json = JSON.parse(data.rawdata);
					
					console.log("IVR데이터확인:"+JSON.stringify(json));
					//console.log(json);
					if(json != null && typeof(json) != "undefined") {
						/*
						json.mbr_id
						json.mbr_hp										
						json.hcall_imprt 
						json.sido_cd_nm
						json.signgu_cd_nm
						*/
						var htmltag = '<div class="row col-md-12 mt-10 pr-0 pt-0 pb-0">';
						var dtl_cd_nm = json.dtl_cd_nm;
						if(dtl_cd_nm == '') {
							dtl_cd_nm = '(정보없음)';
						}
						var dtl_nm = json.dtl_nm;
						if(dtl_nm == '') {
							dtl_nm = '(정보없음)';
						}
						var hcall_ivr = json.hcall_ivr;
						if(hcall_ivr == '') {
							hcall_ivr = '(정보없음)';
						}
						var hcall_imprt_nm = '';
						if(json.hcall_imprt == '1') {
							hcall_imprt_nm = '약';
						} else if(json.hcall_imprt == '2') {
							hcall_imprt_nm = '중';
						} else if(json.hcall_imprt == '3') {
							hcall_imprt_nm = '강';
						}
						var last_call_type = json.hcall_type+'('+json.hcall_st+')';
						htmltag += '<div class="border-top border-right border-left border-bottom pl-10 pr-10 pt-5 pb-5 bg-white cyan-600 text-center font-size-12"><span class="badge badge-radius badge-info badge-sm">업종구분</span><br>' + dtl_cd_nm + '</div>';
						htmltag += '<div class="border-top border-right border-bottom pl-10 pr-10 pt-5 pb-5 text-center font-size-12"><span class="badge badge-radius badge-info badge-sm">어선/터명</span><br>' + dtl_nm + '</div>';
						htmltag += '<div class="border-top border-right border-bottom pl-10 pr-10 pt-5 pb-5 text-center font-size-12"><span class="badge badge-radius badge-info badge-sm">IVR</span><br>' + hcall_ivr + '</div>';
						htmltag += '<div class="border-top border-right border-bottom pl-10 pr-10 pt-5 pb-5 text-center font-size-12"><span class="badge badge-radius badge-info badge-sm">고객성향</span><br>' + hcall_imprt_nm + '</div>';
						htmltag += '<div class="border-top border-right border-bottom pl-10 pr-10 pt-5 pb-5 text-center font-size-12"><span class="badge badge-radius badge-info badge-sm">최근 콜상태</span><br>' + last_call_type + '</div>';
						htmltag += '</div>';
						
						target_obj.html(htmltag);
					} else {
						target_obj.html('<span class="font-size-12 blue-grey-300">조회 가능한 최신 상담정보가 없습니다.</span>');
					}
					
				}
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			console.log('error!');
    			console.log(jqXHR);
    			console.log(textStatus);
    			console.log(errorThrown);
    		}
    	});   
    }
    $(document).ready(function(){
    	fn_egov_link_page_curriculum(1);
    	fn_egov_link_page_callhstry(1);
    	fn_today_main_call_info();
    	//테스트용
    	//notification_show_call_state('전화받기','01051564735 번호로 전화가 왔습니다.( 테스트 )<br><div id="ctiCallingMbrTotalInfoBody123" data-call-number="01051564735"><div class="loader vertical-align-middle loader-ellipsis font-size-12 ml-10"></div></div>');
    	//fn_calling_mbr_total_info('123');
    	//End of 테스트용
    	/*
    	//모달 포커스 재정의
    	$("#eduAdmEduPublicModal2").on('hide.bs.modal', function () {
    		setTimeout(function(){
    			$("body").addClass("modal-open");
    		},500);
    	});
    	*/
    });
    </script>
        
<%@ include file="dialpad.jsp" %>
<%@ include file="../tail.jsp" %>
