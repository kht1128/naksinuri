<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/common.css" />

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://use.fontawesome.com/e59ba62350.js"></script>
<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
<script src="/naksinuri_original/common_main/js/common.js"></script>

<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="evn_no" id="evn_no" value="${evn_no}" />
</form:form>

<style>
	.content{padding:20px}
	.list_tbl th,
	.list_tbl td{background-color:#fff}
	.list_tbl thead th{background-color:#444 !important;color:#fff;font-size:12px;}
</style>

<div class="content respon2">
	<section id="webzineList" class="list_box">			
		<div class="total_num">총 참여인원 <b class="colorSky">${total_count}</b>명</div>
		<div id="btnArea" class="noupline" style="text-align:right">
			<a href="#;" onclick="go_excel()" class="btn_orange">엑셀 다운로드</a>
		</div>
		<div class="board_list mgt10px">
			<table class="list_tbl">
				<colgroup>
					<col width="180" />
					<col width="180" />
					<col />
					<col width="130" />
					<col width="200" />
				</colgroup>
				<thead>
					<tr>
						<th>이름</th>
						<th>휴대폰번호</th>
						<th>업로드한 URL</th>					
						<th>참여일자</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody>
			
				<c:forEach var="item" items="${comment_list}">						
					<tr>
						<td class="date"><c:out value="${item.eco_name}"/></td>
						<td class="date"><c:out value="${item.eco_gongmo_hp}"/></td>
						<td class="date"><c:out value="${item.eco_gongmo_url}"/></td>
						<td class="date"><c:out value="${item.eco_insert_dt}"/></td>
						<td class="date"><c:out value="${item.eco_content}"/></td>
					</tr>
				</c:forEach>
				<c:if test="${comment_list eq null }">
					<tr>			
						<td colspan="5">참여한 인원이 없습니다.</td>
					</tr>					
				</c:if>
				</tbody>
			</table>
		</div>			
	</section>
</div>



<script>
function go_excel(){
	var form = document.getElementById("listform");
	form.action = "/gongmoEeventExcelDownload.do";
	form.submit();
}
</script>