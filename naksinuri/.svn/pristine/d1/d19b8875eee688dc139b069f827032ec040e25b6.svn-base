<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="depthName" value="static" />
<c:set var="pageName" value="likepage" />

<%@include file="../static_header.jsp"%>
	<!-- 사이트통계 { -->
		<article id="siteCondition">
			<h1>인기페이지</h1>
			<div class="date_choice row">
				<div class="col-lg-6">
					<div class="btn-group btn-group-sm" role="group" aria-label="...">
						<button type="button" id="static_day" name="static_day" class="btn btn-default" onclick="statistics_day();" >최근 1일</button>
						<button type="button" id="static_week" name="static_week" class="btn btn-default" onclick="statistics_week();">7일</button>
						<button type="button" id="static_month" name="static_month" class="btn btn-primary" onclick="statistics_month()">30일</button>
						<button type="button" class="btn btn-default" onclick="type_myself()">직접입력</button>
						
					</div>
				</div>
				<form id="frm">
					<div class="col-lg-6">
						<input type="text" id="start_dt" name="start_dt" class="date_input" /> ~ <input type="text" id="end_dt" name="end_dt" class="date_input" /> <button type="button" onclick="statistics_myself()" class="btn btn-default btn-sm">조회</button>
					</div>
				</form>
			</div>
			<section id="top10page" class="conbox">
				<h2>인기페이지순 조회 </h2>
				<table class="alaly_tbl table table-bordered">
					<colgroup>
						<col width="50" />
						<col />
						<col width="120" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>순위</th>
							<th>페이지URL</th>
							<th>페이지뷰(PV)</th>
							<th>비율</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${board_top}" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${item.bo_name}</td>
							<td>${item.cnt }</td>
							<td>
								<div class="progress">
									<div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:${item.prcnt}%">
										<span class="sr-only">20% Complete</span>
									</div>
								</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</article>
	<!-- } 사이트통계 -->
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


function statistics_myself(){
	var form = document.getElementById("frm");
	if($('#start_dt').val()==""){
		alert('조회할 시작일을 입력해주세요.');
		return false;
	}
	if($('#end_dt').val()==""){
		alert('조회할 종료일을 입력해주세요.');
		return false;
	}
	
	form.action="/admin/static/likepageself/list.do";
	form.submit();
}

function statistics_day(){
	document.location.href="/admin/static/likepageday/list.do";
		
	
}

function statistics_week(){
	document.location.href="/admin/static/likepage/list.do";
}	

function statistics_month(){
	document.location.href="#;";
}


	
	</script>

</body>
</html>