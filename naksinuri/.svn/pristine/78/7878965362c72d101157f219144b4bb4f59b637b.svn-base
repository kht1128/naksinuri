<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../../head.jsp" %> 
<%@ include file="../../left_menu.jsp" %>


<form:form commandName="analyticsAdmVO" id="listForm" name="listForm" action="" method="post">
	<input type="hidden" name="selectDays" value="${selectDays}"/>
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
        <div class="page-header-actions">
		
        </div>
      </div>

      <div class="page-content container-fluid">
	      
      		<div class="panel">	      			
	  			<div class="panel-body">		  			
	    			<div class="input-group">
						<div class="btn-group" aria-label="Default button group" role="group">
							<c:set var="strdtlabel" value=""/>
							<c:set var="enddtlabel" value=""/>
							<c:set var="tab1" value="btn-default"/>
							<c:set var="tab2" value="btn-default"/>
							<c:set var="tab3" value="btn-default"/>
							<c:set var="tab4" value="btn-default"/>
							<c:choose>
								<c:when test="${selectDays eq '1'}"><c:set var="tab1" value="btn-primary"/></c:when>
								<c:when test="${selectDays eq '7'}"><c:set var="tab2" value="btn-primary"/></c:when>
								<c:when test="${selectDays eq '30'}"><c:set var="tab3" value="btn-primary"/></c:when>
								<c:when test="${selectDays eq 'direct'}">
									<c:set var="tab4" value="btn-primary"/>
									<c:set var="strdtlabel" value="${str_dt}"/>
									<c:set var="enddtlabel" value="${end_dt}"/>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							<button type="button" class="btn ${tab1}" type="submit" onclick="fnSearch(1); return false;">최근 1일</button>
							<button type="button" class="btn ${tab2}" type="submit" onclick="fnSearch(7); return false;">7일</button>
							<button type="button" class="btn ${tab3}" type="submit" onclick="fnSearch(30); return false;">30일</button>
							<%--<input type="text" class="form-control" placeholder="기간 직접 입력(일)"></button> --%>
							<button type="button" class="btn ${tab4}">직접입력</button>
						</div>
						<div class="ml-30 mr-15">
							<div class="row form-icons">
								<div class="mr-5">
									<div class="input-group">
										<i class="form-control-icon wb-calendar"></i>
										<input type="text" class="form-control datepickerStr" name="STR_DT" value="${strdtlabel}" placeholder=" ~ 부터" autocomplete="off" >
									</div>
								</div>	
								<%--<div class="form-control-label col-lg-1">~</div> --%>			
								<div class="">
									<div class="input-group">
										<i class="form-control-icon wb-calendar"></i>
					   					<input type="text" class="form-control datepickerEnd" name="END_DT" value="${enddtlabel}" placeholder=" ~ 까지" autocomplete="off" >
					   				</div>
								</div>
							</div>
						</div>	
						<span class="input-group-append">
							<button class="btn btn-primary" type="submit" onclick="fnSearch('direct'); return false;"><i class="icon wb-search" aria-hidden="true"></i></button>
						</span>	
		      		</div>
				</div>	
			</div> 		          
            <!-- Panel Editing Rows -->
            <h4 class="">검색유입 요약</h4>
            <div class="row">			
				<div class="col-lg-6">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	            			<!-- 
	              			<button type="button" class="btn btn-floating btn-sm btn-warning">
	                			<i class="icon wb-signal"></i>
	              			</button>
	              			 -->
	              			<span class="ml-15 font-weight-400">검색유입수</span>
	              			<div class="content-text text-center mb-0">
	                			<span class="font-size-40 font-weight-400"><fmt:formatNumber value="${visitchannelinputtotCnt}" pattern="#,###" /></span>
	                			<p class="blue-grey-400 font-weight-100 m-0">
	                				<c:choose>
		                				<c:when test="${visitchannelinputtotCnt - visitchannelinputtotCntPrev > 0}">
		                					<i class="text-success icon wb-triangle-up font-size-20 text-middle"></i>&nbsp;
		                					<c:out value="${(visitchannelinputtotCnt - visitchannelinputtotCntPrev)}"/>
		                					&nbsp;뷰 증가&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(visitchannelinputtotCnt-visitchannelinputtotCntPrev)/visitchannelinputtotCntPrev}"  pattern="0.00%"/>	
		                				</c:when>
		                				<c:when test="${visitchannelinputtotCnt - visitchannelinputtotCntPrev < 0}">
		                					<i class="text-danger icon wb-triangle-down font-size-20 text-middle"></i>&nbsp;
		                					<c:out value=" ${(visitchannelinputtotCnt - visitchannelinputtotCntPrev)*-1}"/>
		                					&nbsp;뷰 감소&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(visitchannelinputtotCntPrev-visitchannelinputtotCnt)/visitchannelinputtotCntPrev}"  pattern="0.00%"/>
		                				</c:when>
		                				<c:otherwise>&nbsp;-&nbsp;뷰</c:otherwise>
		                			</c:choose>
	                			</p>
	              			</div>
	            		</div>
	          		</div>
	        	</div>
				<div class="col-lg-6">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	            			<!-- 
	              			<button type="button" class="btn btn-floating btn-sm btn-warning">
	                			<i class="icon wb-signal"></i>
	              			</button>
	              			 -->
	              			<span class="ml-15 font-weight-400">전체유입수</span>
	              			<div class="content-text text-center mb-0">
	                			<span class="font-size-40 font-weight-400"><fmt:formatNumber value="${visitchannelinputalltotCnt}" pattern="#,###" /></span>
	                			<p class="blue-grey-400 font-weight-100 m-0">
	                				<c:choose>
		                				<c:when test="${visitchannelinputalltotCnt - visitchannelinputalltotCntPrev > 0}">
		                					<i class="text-success icon wb-triangle-up font-size-20 text-middle"></i>&nbsp;
		                					<c:out value="${(visitchannelinputalltotCnt - visitchannelinputalltotCntPrev)}"/>
		                					&nbsp;뷰 증가&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(visitchannelinputalltotCnt-visitchannelinputalltotCntPrev)/visitchannelinputalltotCntPrev}"  pattern="0.00%"/>	
		                				</c:when>
		                				<c:when test="${visitchannelinputalltotCnt - visitchannelinputalltotCntPrev < 0}">
		                					<i class="text-danger icon wb-triangle-down font-size-20 text-middle"></i>&nbsp;
		                					<c:out value=" ${(visitchannelinputalltotCnt - visitchannelinputalltotCntPrev)*-1}"/>
		                					&nbsp;뷰 감소&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(visitchannelinputalltotCntPrev-visitchannelinputalltotCnt)/visitchannelinputalltotCntPrev}"  pattern="0.00%"/>
		                				</c:when>
		                				<c:otherwise>&nbsp;-&nbsp;뷰</c:otherwise>
		                			</c:choose>
		                		</p>
	              			</div>
	            		</div>
	          		</div>
	        	</div>        	        	
			</div>
			<div class="col-lg-12 pt-10 pb-10">  
				<fmt:parseDate var="parseprevdatestring" value="${prev_dt}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="prevdatestring" value="${parseprevdatestring}" pattern="yyyy.MM.dd" />
				<fmt:parseDate var="parseprevenddatestring" value="${prev_end_dt}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="prevenddatestring" value="${parseprevenddatestring}" pattern="yyyy.MM.dd" />
				<fmt:parseNumber value="${parseprevdatestring.time / (1000*60*60*24)}" integerOnly="true" var="calcPrevDate"></fmt:parseNumber>
				<fmt:parseNumber value="${parseprevenddatestring.time / (1000*60*60*24)}" integerOnly="true" var="calcPrevEndDate"></fmt:parseNumber>
				<span class="font-weight-bold">등락 비교일</span>&nbsp;&nbsp;&nbsp;<span class="grey-600">${prevdatestring} ~ ${prevenddatestring} (${calcPrevEndDate-calcPrevDate}일)</span>
			</div>
			<h4 class="mt-30">검색채널 그래프</h4>     
            <div class="panel">
            	<!-- 
            	<header class="panel-heading">
					<h3 class="panel-title"></h3>
				</header>
				 -->
				<div class="panel-body">
					<!-- chart -->
			        <div class="h-250" id="daysvisitorchartlist"></div>
					<!-- End chart -->
              	</div>
			</div>
			<h4 class="mt-30">검색어 현황</h4>     
            <div class="panel"> 
            	<header class="panel-heading">
					<h3 class="panel-title">&nbsp;</h3>
					<div class="panel-actions">		    						
						<button type="button" class="btn btn-icon btn-dark btn-act-excel-down" data-toggle="tooltip" data-original-title="엑셀 다운로드"><i class="icon wb-download m-0" aria-hidden="true"></i> 엑셀 다운로드</button>
					</div>
				</header>				
				<div class="panel-body">
					<!-- table -->
					<table class="table table-hover dataTable table-striped w-full" data-plugin="dataTable" id="datalist">
					<colgroup>
						<col width="120px"/>
						<col />
						<col width="300px"/>
					</colgroup>
					<thead>
						<tr>
						    <th class="text-center text-middle">순위</th>
							<th class="text-center text-middle">검색어</th>
							<th class="text-center text-middle">검색횟수</th>
					  	</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${visitchannelinputnamelist}" varStatus="status">
						<tr>
							<td class="text-center text-middle"><c:out value="${status.count}"/></td>
							<td class="text-left text-middle"><c:out value="${item.VISIT_INPUT_NM}"/></td>
							<td class="text-center text-middle"><c:out value="${item.SEARCH_CNT}"/></td>
						</tr>
						</c:forEach>
					</tbody>
					</table>
					<!-- End table -->
              	</div>
			</div>
	         
		</div>
	</div>
    <!-- End Page -->
</form:form>

<script defer>
$(function(){
	$('.selectpicker_manual').selectpicker();
	$('.datepickerStr').datepicker({
	    format: 'yyyy-mm-dd 00:00:00',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	$('.datepickerEnd').datepicker({
	    format: 'yyyy-mm-dd 23:59:59',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	//--------------------------------------------------
	// 차트(그래프) 영역
	//--------------------------------------------------
	var listjson1 = JSON.parse('${visitchannelinputtypelistJson}');
	var seriesdata = new Array();
	var labelsdata = new Array();
	var labelsdataitem1 = new Array();
		labelsdata.push('x');
		labelsdataitem1.push("유입건수");
	for (i=0; i<listjson1.length; i++) {
		var item = listjson1[i];
		if(item.visit_input_tb!='etc') {
			labelsdata.push(item.visit_input_tb);
			labelsdataitem1.push(item.search_cnt);
		}
	}
	seriesdata.push(labelsdata);
	seriesdata.push(labelsdataitem1);
	var time_series_chart = c3.generate({
		bindto: '#daysvisitorchartlist',
		data: {
			type: 'bar',
			x: 'x',
			columns: seriesdata,
	        /*
			json: listjson1,
	        keys: {
                x: 'visit_input_tb',
                value: ["search_cnt"],
            },
            */
            color: function(color, d){
                var lst = ['#3e8ef7', '#0bb2d4', '#17b3a3', '#eb6709', '#ff4c52', '#9463f7', '#94CC39', '#FFCD17', '#82675F', '#4A7800', '#526069', '#B8D7FF']
                return(lst[d.index]);            
          	},
      	},
     	axis: {
            x: {
              type: 'category',
              tick: { centered: true},
            },            
		},
      	bar: {
	        // width: {
	        //  ratio: 0.55 // this makes bar width 55% of length between ticks
	        // }
	        width: {
	          max: 20
	        }
      	},
      	/*
      	color: {
        	pattern: [Config.colors("red", 400), Config.colors("blue-grey", 400), Config.colors("blue-grey", 200)]
      	},
      	*/      	
      	grid: {
        	y: {
          	show: true
        	},
        	x: {
          	show: false
        	}
      	},
      	legend: {
            show: false
       	}
	});
	/* 동적추가
    setTimeout(function () {
      time_series_chart.load({
        columns: [['data3', 210, 180, 260, 290, 250, 240]]
      });
    }, 1000);
	*/
	//--------------------------------------------------
	// 데이터 리스트
	//--------------------------------------------------
	 var lang_kor = {
        "decimal" : "",
        "emptyTable" : "데이터가 없습니다.",
        "info" : "_START_ - _END_ (총 _TOTAL_ 건)",
        "infoEmpty" : "0건",
        "infoFiltered" : "(전체 _MAX_ 건 중 검색결과)",
        "infoPostFix" : "",
        "thousands" : ",",
        "lengthMenu" : "_MENU_ 개씩 보기",
        "loadingRecords" : "로딩중...",
        "processing" : "처리중...",
        "search" : "검색 : ",
        "zeroRecords" : "검색된 데이터가 없습니다.",
        "paginate" : {
            "first" : "<<",
            "last" : ">>",
            "next" : ">",
            "previous" : "<"
        },
        "aria" : {
            "sortAscending" : " :  오름차순 정렬",
            "sortDescending" : " :  내림차순 정렬"
        }
    };
	/*
	$('#datalist').DataTable( {
        //data: dataSet,
        //columns: col_kor, //or col_eng
        language : lang_kor //or lang_eng
    });
	*/
	$.extend(true, $.fn.DataTable.defaults, {
		 "language" : lang_kor
	});
});
function fnSearch(days){
	//document.listForm.pageIndex.value = 1;
	document.listForm.selectDays.value = days;
	document.listForm.action = "<c:url value='/seadm/analytics/inflow/searchdashboard.do'/>";
    document.listForm.submit();
}
</script>

<%@ include file="../../tail.jsp" %>


	<!-- // 엑셀다운로드(tail 뒤에 위치해야함.) // -->
    <script src="/common/js/jquery.table2excel.min.js"></script>
    <script>
    $(".btn-act-excel-down").click( function() {
    	var today = new Date();
    	var d = today.getDay();
    	var m = today.getMonth();
    	var y = today.getFullYear();
    	var hh = today.getHours();
    	var mm = today.getMinutes();
    	var ss = today.getSeconds();
    	var timestr = y+""+m+""+d+""+hh+""+mm+""+ss;
    	$('#datalist').table2excel({
    		name: "${pageName}",
    		filename: "${pageName}_"+timestr+".xls",
    		//fileext: ".xls",
    		exclude_img: true,
    		exclude_links: true,
    		exclude_inputs: true,
    		exclude: ".hide-cell-exceldown",
    	});
    });
    </script>
    <!-- End // 엑셀다운로드 // -->

