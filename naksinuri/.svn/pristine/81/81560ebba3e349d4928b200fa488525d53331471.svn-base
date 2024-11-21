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
								<%--<div class="form-control-label col-lg-1">~</div>		 --%>	
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
				<div class="col-lg-4">
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
				<div class="col-lg-4">
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
	        	<div class="col-lg-4">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	            			<!-- 
	              			<button type="button" class="btn btn-floating btn-sm btn-warning">
	                			<i class="icon wb-signal"></i>
	              			</button>
	              			-->
	              			<span class="ml-15 font-weight-400">페이지뷰</span>
	              			<div class="content-text text-center mb-0">
	                			<span class="font-size-40 font-weight-400"><fmt:formatNumber value="${totPageViewCnt}" pattern="#,###" /></span>
	                			<p class="blue-grey-400 font-weight-100 m-0">
	                				<c:choose>
		                				<c:when test="${totPageViewCnt - totPageViewCntPrev > 0}">
		                					<i class="text-success icon wb-triangle-up font-size-20 text-middle"></i>&nbsp;
		                					<c:out value="${(totPageViewCnt - totPageViewCntPrev)}"/>
		                					&nbsp;뷰 증가&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(totPageViewCnt-totPageViewCntPrev)/totPageViewCntPrev}"  pattern="0.00%"/>	
		                				</c:when>
		                				<c:when test="${totPageViewCnt - totPageViewCntPrev < 0}">
		                					<i class="text-danger icon wb-triangle-down font-size-20 text-middle"></i>&nbsp;
		                					<c:out value=" ${(totPageViewCnt - totPageViewCntPrev)*-1}"/>
		                					&nbsp;뷰 감소&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<fmt:formatNumber type="percent" value="${(totPageViewCntPrev-totPageViewCnt)/totPageViewCntPrev}"  pattern="0.00%"/>
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
			<h4 class="mt-30">일별 방문현황</h4>     
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
			<h4 class="mt-30">유입현황</h4>     
            <div class="row">
            	<div class="col-lg-6">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	              			<span class="ml-15 font-weight-400">검색채널별 검색유입</span>
	              			<div class="content-text text-center mb-0">
	                			<div id="channelinputtypechart"></div>
	              			</div>
	            		</div>
	          		</div>
	        	</div>
	        	<div class="col-lg-6">
	          		<div class="card card-shadow mb-0">
	            		<div class="card-block bg-white p-20">
	              			<span class="ml-15 font-weight-400">접속환경별 방문자수</span>
	              			<div class="content-text text-center mb-0">
	                			<div id="devicetypechart"></div>
	              			</div>
	            		</div>
	          		</div>
	        	</div>
			</div>
			<h4 class="mt-30">TOP10 인기페이지</h4>     
            <div class="panel">
            	<!-- 
            	<header class="panel-heading">
					<h3 class="panel-title"></h3>
				</header>
				 -->
				<div class="panel-body">
					<!-- table -->
					<table id="exampleFooEditing" class="table table-hover footable footable-paging footable-paging-center ">
					<colgroup>
						<col width='5%' >
						<col width='25%'>
						<col width='25%'>
						<col width='20'>
						<col width='25%'>
					</colgroup>
					<thead>
					  	<tr>
						    <th class="text-center text-middle">순위</th>
							<th class="text-center text-middle">페이지URL</th>
							<th class="text-center text-middle">페이지 이름</th>
							<th class="text-center text-middle">페이지뷰</th>
							<th class="text-center text-middle">비율</th>                      
					  	</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${popularpageList}" varStatus="status">
						<tr>
					  		<td class="text-center text-middle"><c:out value="${((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)+1}"/></td>
							<td class="text-left text-middle"><c:out value="${result.visiturl}"/></td>
							<td class="text-center text-middle"><c:out value="${result.visitpagenm}"/></td>
							<td class="text-center text-middle"><c:out value="${result.visitcount}"/></td>
							<td class="text-right text-middle">
								<%--
								<div class="progress progress-xs mb-0">
						      		<div class="progress-bar progress-bar-info bg-blue-600" style="width: <c:out value="${result.visitcount/populartotcnt*100}"/>%" aria-valuemax="100"
										aria-valuemin="0" aria-valuenow="<c:out value="${result.visitcount/populartotcnt*100}"/>" role="progressbar">
						  			</div>
								</div>
								<fmt:formatNumber var="percent" value="${result.visitcount/populartotcnt*100}" pattern="0.00" />
								<span class="progress-percent">
									<c:out value="${percent}"/>%
						  		</span>
						  		 --%>
						  		<fmt:formatNumber var="percent" value="${result.visitcount/populartotcnt*100}" pattern="0.00" />
						  		<div class="m-20 /*contextual-progress*/">
	                        		<div class="clearfix">
	                          			<div class="progress-title"></div>
	                          			<div class="progress-label"></div>
	                        		</div>
	                        		<div class="progress progress-lg" data-labeltype="percentage" data-goal="-40" data-plugin="progress">
		                          		<div class="progress-bar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="${percent}"
		                            		role="progressbar">
		                            		<span class="progress-label"></span>
		                          		</div>
	                        		</div>
	                      		</div>
					  		</td>
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
		labelsdata.push('x');
		seriesdataitem1.push('방문자수');
		seriesdataitem2.push('방문횟수');
		seriesdataitem3.push('페이지뷰');
	for (i=0; i<visitdaytotlistjson.length; i++) {
		var item = visitdaytotlistjson[i];
		labelsdata.push(item.search_dt);
		seriesdataitem1.push(item.visit_mbr_cnt);
		seriesdataitem2.push(item.visit_cnt);
		seriesdataitem3.push(item.visit_pv_cnt);
	}
	seriesdata.push(labelsdata);
	seriesdata.push(seriesdataitem1);
	seriesdata.push(seriesdataitem2);
	seriesdata.push(seriesdataitem3);
	var time_series_chart = c3.generate({
		bindto: '#daysvisitorchartlist',
		data: {
			x: 'x',
			columns: seriesdata//[['x', '2013-01-01', '2013-01-02', '2013-01-03', '2013-01-04', '2013-01-05', '2013-01-06'], ['data1', 80, 125, 100, 220, 80, 160], ['data2', 40, 85, 45, 155, 50, 65]]
		},
		color: {
	        pattern: [Config.colors("primary", 600), Config.colors("green", 600), Config.colors("red", 500)]
		},
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
	// 차트(파이) 영역
	//--------------------------------------------------
	var visitchannelinputtypelistjson = JSON.parse('${visitchannelinputtypelistJson}');
	var data1 = new Array();
	for (i=0; i<visitchannelinputtypelistjson.length; i++) {
		var item = visitchannelinputtypelistjson[i];
		var rowdata = new Array();
		rowdata.push(item.visit_input_tb);
		rowdata.push(item.search_cnt);
		data1.push(rowdata);
	}
	var pie_chart1 = c3.generate({
		bindto: '#channelinputtypechart',
		data: {
			// iris data from R
	        columns: data1,
	        type: 'pie'
		},
		/*
		color: {
	        pattern: [Config.colors("primary", 500), Config.colors("blue-grey", 200)]
		},
		*/
		legend: {
	        position: 'right'
		},
		pie: {
	        label: {
	          show: false
	        },
	        onclick: function onclick(d, i) {},
	        onmouseover: function onmouseover(d, i) {},
	        onmouseout: function onmouseout(d, i) {}
		}
	});
	var visitdevicetypelistjson = JSON.parse('${visitdevicetypelistJson}');
	var data2 = new Array();
		var item = visitdevicetypelistjson[0];
			var rowdata1 = new Array();
			rowdata1.push('PC');
			rowdata1.push(item.search_pc_cnt);
			data2.push(rowdata1);
			var rowdata2 = new Array();
			rowdata2.push('모바일');
			rowdata2.push(item.search_mobile_cnt);
			data2.push(rowdata2);
			var rowdata3 = new Array();
			rowdata3.push('기타');
			rowdata3.push(item.search_robot_cnt);
			data2.push(rowdata3);
	var pie_chart2 = c3.generate({
		bindto: '#devicetypechart',
		data: {
			// iris data from R
	        columns: data2,
	        type: 'pie'
		},
		/*
		color: {
	        pattern: [Config.colors("primary", 500), Config.colors("blue-grey", 200)]
		},
		*/
		legend: {
	        position: 'right',
	        show: true
		},
		pie: {
	        label: {
	          show: false
	        },
	        onclick: function onclick(d, i) {},
	        onmouseover: function onmouseover(d, i) {},
	        onmouseout: function onmouseout(d, i) {}
		}
	});	
});
function fnSearch(days){
	//document.listForm.pageIndex.value = 1;
	document.listForm.selectDays.value = days;
	document.listForm.action = "<c:url value='/eduadm/analytics/summary/sitesummary.do'/>";
    document.listForm.submit();
}
</script>

<%@ include file="../../tail.jsp" %>


