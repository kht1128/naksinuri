<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="depthName" value="static" />
<c:set var="pageName" value="invit" />
<%@include file="../static_header.jsp"%>
	<!-- 사이트통계 { -->
	<form id="frm" method="post">
		<input type="hidden" id="start_dt1" name="start_dt1">
		<input type="hidden" id="end_dt1" name="end_dt1">
		<article id="siteCondition">
			<h1>사이트현황</h1>
			<div class="date_choice row">
				<div class="col-lg-6">
					<div class="btn-group btn-group-sm" role="group" aria-label="...">
						<button type="button" id="static_day" name="static_day" class="btn btn-default" onclick="statistics_day();" >최근 1일</button>
						<button type="button" id="static_week" name="static_week" class="btn btn-primary" onclick="statistics_week();">7일</button>
						<button type="button" id="static_month" name="static_month" class="btn btn-default" onclick="statistics_month()">30일</button>
						<button type="button" class="btn btn-default" onclick="type_myself()">직접입력</button>
					</div>
				</div>
				<div class="col-lg-6">
					<input type="text" id="start_dt" name="start_dt" class="date_input" value="${start_dt}"/> ~ <input type="text" id="end_dt" name="end_dt" class="date_input" value="${end_dt}" /> <button type="button" onclick="invitInfo_myself()" class="btn btn-default btn-sm">조회</button>
				</div>
			</div>
			<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
			<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
			<input type="hidden" id="searchType" value="${searchType}" />
			<div id="search_form">
				<div class="totalnum">총 <b>${select_total}</b>건</div>
				<div class="listnum">
					출력수 
					<select style="height:30px;" id="s_pageUnit" name="s_pageUnit" onchange="fnSelectInfs(1);">
						<option value="10">10</option>
						<option value="20">20</option>
						<option value="30">30</option>
						<option value="40">40</option>
						<option value="50">50</option>
					</select>				
					<select style="width:100px;height:30px;margin-left:350px;" name="searchType">					
						<option value="client_ip"<c:if test="${searchType eq 'client_ip'}">selected</c:if>>IP</option>
						<option value="browser"<c:if test="${searchType eq 'browser'}">selected</c:if>>브라우저</option>
						<option value="statistic_os"<c:if test="${searchType eq 'statistic_os'}">selected</c:if>>운영체제</option>											
					</select>
					<input type="text" style="height:30px;" name="searchText" value="${searchText}"/> 
					<button class="btn btn-default" type="button" onclick="fnSelectInfs(1);">검색</button>						
				</div>
			</div>
	</form>
			
			<section id="top10page" class="conbox">
				<br><h2>사용자 방문현황</h2><br>
				<table class="alaly_tbl table table-bordered">
					<colgroup>						
						<col width="45"/>
						<col width="150"/>
						<col width="90"/>
						<col width="90" />
						<col width="140"/>
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>IP</th>
							<th>브라우저</th>
							<th>OS</th>
							<th>일시</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${select_list}">
							<tr>
								<td>${select_total-item.rn + 1}</td>
								<td>${item.client_ip}</td>
								<td>${item.statistic_browser}</td>
								<td>${item.statistic_os}</td>
								<td>
									<fmt:parseDate value="${item.date_time}" var="f_data" pattern="yyyy-MM-dd HH:mm:ss.SSS" scope="page"/>
									<fmt:formatDate value="${f_data}" pattern="yyyy-MM-dd HH:mm:ss"/>		
								</td>					
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</article>
	<!-- } 사이트통계 -->
		
		<div id="pagenation">
			<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
		</div>	

	<!-- 하단 푸터 { -->
	<%@include file="../static_tail.jsp"%>
	<!-- } 하단 푸터 -->
<script>
$(document).ready(function(){
	$('#start_dt').datepicker({dateFormat : 'yy-mm-dd'});
	$('#end_dt').datepicker({dateFormat : 'yy-mm-dd'});
})

function type_myself(){
	$('#start_dt').focus();
	
}
function statistics_day(){
	document.location.href="/admin/static/invitday/list.do";
		
	
}

function statistics_week(){
	document.location.href="#;";
}	

function statistics_month(){
	document.location.href="/admin/static/invitmonth/list.do";
}
function invitInfo_myself(){
	var form = document.getElementById("frm");
	if($('#start_dt').val()==""){
		alert("조회할 시작일을 입력해주세요");
		return false;
	}
	
	if($('#end_dt').val()==""){
		alert('조회할 종료일을 입력해주세요');
		return false;
	}
	
	  if($('#start_dt').val()==$('#end_dt').val()){
		  alert('시작날짜가 종료날짜와 같습니다.');
		  return false;
	    }
	    
    var startDate = $( "input[name='start_dt']" ).val();
    var startDateArr = startDate.split('-');
     
    var endDate = $( "input[name='end_dt']" ).val();
    var endDateArr = endDate.split('-');
             
    var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
    var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
     
    if(startDateCompare.getTime() > endDateCompare.getTime()) {
        alert("기간의 시작날짜와 종료날짜를 확인해 주세요.");
    return false;
    }
    form.action="/admin/static/invitself/list.do";
    form.submit();
}

function fnSelectInfs(pageIndex) {
	var idx= $('#s_pageUnit').val();
	$("#pageUnit").val(idx);
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "./list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

$(function(){
	var idx= $('#pageUnit').val();
	$("#s_pageUnit").val(idx);
});
</script>
</body>
</html>