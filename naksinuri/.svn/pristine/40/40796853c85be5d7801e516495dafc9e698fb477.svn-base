<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../../head.jsp" %> 
<%@ include file="../../left_menu.jsp" %>


<form:form commandName="analyticsPageVO" id="listForm" name="listForm" action="" method="post">
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
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="selectYears">
							<fmt:parseNumber var="sel_year" value="${selectYears}"/>
							<c:forEach var="i" begin="2019" end="${sel_year}">
		        				<option value="${i}" <c:if test="${i eq selectYears}">selected</c:if> >${i}</option>
							</c:forEach>		        			
		      				</select>
						</div>
						<span class="input-group-append">
							<button class="btn btn-primary" type="submit" onclick="fnSearch('direct'); return false;"><i class="icon wb-search" aria-hidden="true"></i></button>
						</span>		
		      		</div>
				</div>	
			</div> 		          
            <!-- Panel Editing Rows -->            
			<h4 class="mt-30">${selectYears}년도 페이지뷰 현황표</h4>     
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
						<col width="8%"/>
					</colgroup>
					<thead>
						<tr>
						    <th class="text-center text-middle">순위</th>
							<th class="text-center text-middle">메뉴</th>
							<th class="text-center text-middle">1월</th>
							<th class="text-center text-middle">2월</th>
							<th class="text-center text-middle">3월</th>
							<th class="text-center text-middle">4월</th>
							<th class="text-center text-middle">5월</th>
							<th class="text-center text-middle">6월</th>
							<th class="text-center text-middle">7월</th>
							<th class="text-center text-middle">8월</th>
							<th class="text-center text-middle">9월</th>
							<th class="text-center text-middle">10월</th>
							<th class="text-center text-middle">11월</th>
							<th class="text-center text-middle">12월</th>
							<th class="text-center text-middle">합계</th>
					  	</tr>
					</thead>
					<tbody>
						<c:set var="m1t" value="0"/>
						<c:set var="m2t" value="0"/>
						<c:set var="m3t" value="0"/>
						<c:set var="m4t" value="0"/>
						<c:set var="m5t" value="0"/>
						<c:set var="m6t" value="0"/>
						<c:set var="m7t" value="0"/>
						<c:set var="m8t" value="0"/>
						<c:set var="m9t" value="0"/>
						<c:set var="m10t" value="0"/>
						<c:set var="m11t" value="0"/>
						<c:set var="m12t" value="0"/>
						<c:set var="mtt" value="0"/>						
						<c:forEach var="item" items="${visitorpagetotlist}" varStatus="status">
						<tr>
							<td class="text-center text-middle"><c:out value="${status.count}"/></td>
							<td class="text-center text-middle"><c:out value="${item.VISIT_PAGE_NM}"/></td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m1_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m1_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m1t" value="${item.m1_PV_CNT+m1t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m2_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m2_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m2t" value="${item.m2_PV_CNT+m2t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m3_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m3_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m3t" value="${item.m3_PV_CNT+m3t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m4_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m4_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m4t" value="${item.m4_PV_CNT+m4t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m5_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m5_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m5t" value="${item.m5_PV_CNT+m5t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m6_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m6_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m6t" value="${item.m6_PV_CNT+m6t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m7_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m7_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m7t" value="${item.m7_PV_CNT+m7t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m8_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m8_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m8t" value="${item.m8_PV_CNT+m8t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m9_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m9_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m9t" value="${item.m9_PV_CNT+m9t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m10_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m10_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m10t" value="${item.m10_PV_CNT+m10t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m11_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m11_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m11t" value="${item.m11_PV_CNT+m11t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.m12_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.m12_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="m12t" value="${item.m12_PV_CNT+m12t}"/>
							</td>
							<td class="text-center text-middle">
								<c:choose>
									<c:when test="${item.MT_PV_CNT eq null}">0</c:when>
									<c:otherwise><fmt:formatNumber value="${item.MT_PV_CNT}" pattern="#,###" /></c:otherwise>
								</c:choose>
								<c:set var="mtt" value="${item.MT_PV_CNT+mtt}"/>
							</td>							
						</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td class="text-center text-middle" colspan="2">합계</td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m1t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m2t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m3t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m4t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m5t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m6t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m7t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m8t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m9t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m10t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m11t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${m12t}" pattern="#,###" /></td>
							<td class="text-center text-middle"><fmt:formatNumber value="${mtt}" pattern="#,###" /></td>
						</tr>
					</tfoot>
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
	document.listForm.action = "<c:url value='/seadm/analytics/inflow/urls.do'/>";
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

