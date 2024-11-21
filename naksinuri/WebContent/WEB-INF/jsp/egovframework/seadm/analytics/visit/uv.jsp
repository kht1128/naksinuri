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
            <h4 class="">방문현황 요약</h4>
            <div class="row">			
				<div class="col-lg-3">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	            			<!-- 
	              			<button type="button" class="btn btn-floating btn-sm btn-warning">
	                			<i class="icon wb-signal"></i>
	              			</button>
	              			 -->
	              			<span class="ml-15 font-weight-400">방문자수</span>
	              			<div class="content-text text-center mb-0">
	                			<span class="font-size-40 font-weight-400"><fmt:formatNumber value="${visittotCnt}" pattern="#,###" /></span>
	                			<p class="blue-grey-400 font-weight-100 m-0">
		                			<c:choose>
		                				<c:when test="${visittotCnt - visittotCntPrev > 0}">
		                					<i class="text-success icon wb-triangle-up font-size-20 text-middle"></i>&nbsp;
		                					<c:out value="${(visittotCnt - visittotCntPrev)}"/>
		                					&nbsp;명 증가&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(visittotCnt-visittotCntPrev)/visittotCntPrev}"  pattern="0.00%"/>	
		                				</c:when>
		                				<c:when test="${visittotCnt - visittotCntPrev < 0}">
		                					<i class="text-danger icon wb-triangle-down font-size-20 text-middle"></i>&nbsp;
		                					<c:out value=" ${(visittotCnt - visittotCntPrev)*-1}"/>
		                					&nbsp;명 감소&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(visittotCntPrev-visittotCnt)/visittotCntPrev}"  pattern="0.00%"/>
		                				</c:when>
		                				<c:otherwise>&nbsp;-&nbsp;명</c:otherwise>
		                			</c:choose>
	                			</p>
	              			</div>
	            		</div>
	          		</div>
	        	</div>
				<div class="col-lg-3">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	            			<!-- 
	              			<button type="button" class="btn btn-floating btn-sm btn-warning">
	                			<i class="icon wb-signal"></i>
	              			</button>
	              			 -->
	              			<span class="ml-15 font-weight-400">방문횟수</span>
	              			<div class="content-text text-center mb-0">
	                			<span class="font-size-40 font-weight-400"><fmt:formatNumber value="${allvisittotCnt}" pattern="#,###" /></span>
	                			<p class="blue-grey-400 font-weight-100 m-0">
	                				<c:choose>
		                				<c:when test="${allvisittotCnt - allvisittotCntPrev > 0}">
		                					<i class="text-success icon wb-triangle-up font-size-20 text-middle"></i>&nbsp;
		                					<c:out value="${(allvisittotCnt - allvisittotCntPrev)}"/>
		                					&nbsp;회 증가&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(allvisittotCnt-allvisittotCntPrev)/allvisittotCntPrev}"  pattern="0.00%"/>	
		                				</c:when>
		                				<c:when test="${allvisittotCnt - allvisittotCntPrev < 0}">
		                					<i class="text-danger icon wb-triangle-down font-size-20 text-middle"></i>&nbsp;
		                					<c:out value=" ${(allvisittotCnt - allvisittotCntPrev)*-1}"/>
		                					&nbsp;회 감소&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(allvisittotCntPrev-allvisittotCnt)/allvisittotCntPrev}"  pattern="0.00%"/>
		                				</c:when>
		                				<c:otherwise>&nbsp;-&nbsp;회</c:otherwise>
		                			</c:choose>
		                		</p>
	              			</div>
	            		</div>
	          		</div>
	        	</div>
	        	<div class="col-lg-3">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	            			<!-- 
	              			<button type="button" class="btn btn-floating btn-sm btn-warning">
	                			<i class="icon wb-signal"></i>
	              			</button>
	              			-->
	              			<span class="ml-15 font-weight-400">신규방문자수</span>
	              			<div class="content-text text-center mb-0">
	                			<span class="font-size-40 font-weight-400"><fmt:formatNumber value="${newvisittotCnt}" pattern="#,###" /></span>
	                			<p class="blue-grey-400 font-weight-100 m-0">
	                				<c:choose>
		                				<c:when test="${newvisittotCnt - newvisittotCntPrev > 0}">
		                					<i class="text-success icon wb-triangle-up font-size-20 text-middle"></i>&nbsp;
		                					<c:out value="${(newvisittotCnt - newvisittotCntPrev)}"/>
		                					&nbsp;명 증가&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(newvisittotCnt-newvisittotCntPrev)/newvisittotCntPrev}"  pattern="0.00%"/>	
		                				</c:when>
		                				<c:when test="${newvisittotCnt - newvisittotCntPrev < 0}">
		                					<i class="text-danger icon wb-triangle-down font-size-20 text-middle"></i>&nbsp;
		                					<c:out value=" ${(newvisittotCnt - newvisittotCntPrev)*-1}"/>
		                					&nbsp;명 감소&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(newvisittotCntPrev-newvisittotCnt)/newvisittotCntPrev}"  pattern="0.00%"/>
		                				</c:when>
		                				<c:otherwise>&nbsp;-&nbsp;명</c:otherwise>
		                			</c:choose>
	                			</p>
	              			</div>
	            		</div>
	          		</div>
	        	</div>
	        	<div class="col-lg-3">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	            			<!-- 
	              			<button type="button" class="btn btn-floating btn-sm btn-warning">
	                			<i class="icon wb-signal"></i>
	              			</button>
	              			-->
	              			<span class="ml-15 font-weight-400">재방문자수</span>
	              			<div class="content-text text-center mb-0">
	                			<span class="font-size-40 font-weight-400"><fmt:formatNumber value="${revisittotCnt}" pattern="#,###" /></span>
	                			<p class="blue-grey-400 font-weight-100 m-0">
	                				<c:choose>
		                				<c:when test="${revisittotCnt - revisittotCntPrev > 0}">
		                					<i class="text-success icon wb-triangle-up font-size-20 text-middle"></i>&nbsp;
		                					<c:out value="${(revisittotCnt - revisittotCntPrev)}"/>
		                					&nbsp;명 증가&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(revisittotCnt-revisittotCntPrev)/revisittotCntPrev}"  pattern="0.00%"/>	
		                				</c:when>
		                				<c:when test="${revisittotCnt - revisittotCntPrev < 0}">
		                					<i class="text-danger icon wb-triangle-down font-size-20 text-middle"></i>&nbsp;
		                					<c:out value=" ${(revisittotCnt - revisittotCntPrev)*-1}"/>
		                					&nbsp;명 감소&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(revisittotCntPrev-revisittotCnt)/revisittotCntPrev}"  pattern="0.00%"/>
		                				</c:when>
		                				<c:otherwise>&nbsp;-&nbsp;명</c:otherwise>
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
			<h4 class="mt-30">방문현황 그래프</h4>     
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
			<h4 class="mt-30">방문현황 상세정보</h4>     
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
					<thead>
						<tr>
						    <th class="text-center text-middle">날짜</th>
							<th class="text-center text-middle">방문자수</th>
							<th class="text-center text-middle">전일대비</th>
							<th class="text-center text-middle">방문횟수</th>
							<th class="text-center text-middle">신규방문자수</th>                      
							<th class="text-center text-middle">재방문자수</th>
					  	</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${visitdaytotlist}" varStatus="status">
						<tr>
							<td class="text-center text-middle">
								<fmt:parseDate var="parsedatestring" value="${item.VALUE_DATE}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate var="datestring" value="${parsedatestring}" pattern="yyyy.MM.dd (E)" />
								<c:out value="${datestring}"/>
							</td>
							<td class="text-center text-middle"><c:out value="${item.VISIT_MBR_CNT}"/></td>
							<td class="text-center text-middle">
								<c:choose>
	                				<c:when test="${item.VISIT_MBR_CNT - item.VISIT_MBR_CNT_PREV > 0}">
	                					<i class="text-success icon wb-triangle-up font-size-20 text-middle"></i>&nbsp;<c:out value="${(item.VISIT_MBR_CNT - item.VISIT_MBR_CNT_PREV)}"/>
	                					<span class="d-none">&nbsp;증가</span>	
	                				</c:when>
	                				<c:when test="${item.VISIT_MBR_CNT - item.VISIT_MBR_CNT_PREV < 0}">
	                					<i class="text-danger icon wb-triangle-down font-size-20 text-middle"></i>&nbsp;<c:out value=" ${(item.VISIT_MBR_CNT - item.VISIT_MBR_CNT_PREV)*-1}"/>
	                					<span class="d-none">&nbsp;감소</span>
	                				</c:when>
	                				<c:otherwise>-</c:otherwise>
	                			</c:choose>							
							</td>
							<td class="text-center text-middle"><c:out value="${item.VISIT_CNT}"/></td>
							<td class="text-center text-middle"><c:out value="${item.VISIT_N_MBR_CNT}"/></td>
							<td class="text-center text-middle"><c:out value="${item.VISIT_RE_CNT}"/></td>
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
	var visitdaytotlistjson = JSON.parse('${visitdaytotlistJson}');
	var labelsdata = new Array();
	var seriesdata = new Array();
	var seriesdataitem1 = new Array();
	var seriesdataitem2 = new Array();
	var seriesdataitem3 = new Array();
	var seriesdataitem4 = new Array();
		labelsdata.push('x');
		seriesdataitem1.push('방문자수');
		seriesdataitem2.push('방문횟수');
		seriesdataitem3.push('신규방문자수');
		seriesdataitem4.push('재방문자수');
	for (i=0; i<visitdaytotlistjson.length; i++) {
		var item = visitdaytotlistjson[i];
		labelsdata.push(item.search_dt);
		seriesdataitem1.push(item.visit_mbr_cnt);
		seriesdataitem2.push(item.visit_cnt);
		seriesdataitem3.push(item.visit_n_mbr_cnt);
		seriesdataitem4.push(item.visit_re_cnt);
	}
	seriesdata.push(labelsdata);
	seriesdata.push(seriesdataitem1);
	seriesdata.push(seriesdataitem2);
	seriesdata.push(seriesdataitem3);
	seriesdata.push(seriesdataitem4);
	var time_series_chart = c3.generate({
		bindto: '#daysvisitorchartlist',
		data: {
			x: 'x',
			columns: seriesdata//[['x', '2013-01-01', '2013-01-02', '2013-01-03', '2013-01-04', '2013-01-05', '2013-01-06'], ['data1', 80, 125, 100, 220, 80, 160], ['data2', 40, 85, 45, 155, 50, 65]]
		},
		/*
		color: {
	        pattern: [Config.colors("green", 600)]//[Config.colors("primary", 600), Config.colors("green", 600), Config.colors("red", 500)]
		},
		*/
		padding: {
	        right: 40
		},
		axis: {
	        x: {
	          type: 'timeseries',
	          tick: {
	            outer: false,
	            format: '%Y-%m-%d'
	          }
			},
	        y: {
	          //max: 300,
	          min: 0,
	          tick: {
	        	outer: false,
	            //count: 7,
	            //values: [0, 50, 100, 150, 200, 250, 300]
	          }
	        }
		},
		grid: {
	        x: {
	          show: false
	        },
	        y: {
	          show: true
	        }
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
	document.listForm.action = "<c:url value='/seadm/analytics/visit/uv.do'/>";
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

