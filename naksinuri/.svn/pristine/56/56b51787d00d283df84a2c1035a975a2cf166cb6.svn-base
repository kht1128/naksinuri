<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="conference" />
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congressmbrsearch" />

<%@include file="../layout/head.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" id="idx"  name="idx" value=""/>
	<input type="hidden" id="bo_sn"  name="bo_sn" value=""/>
	<input type="hidden" id="mbr_group"  name="mbr_group" value=""/>
</form:form>	
<div id="fishjobList" class="content respon2">
	<section id="webzineList" class="list_box">
	<div id="btnArea" class="noupline" style="margin:20px;">
		<ul class="floats">
			<li>
				<a href="./mbrcheck.do" class="btn_list btn_gray">조회페이지로 이동</a>
			</li>
		</ul>
	</div>
	<div class="board_list">
		<table class="list_tbl">
			<colgroup>
				<col width="50"/>
				<col width="150"/>
				<col width="120" />	
				<col width="120" />
				<col width="140" />
				<col width="140" />
				<col width="170" />
				<col width="100" />
				<col width="100" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>대회명</th>
					<th>실참가자</th>
					<th>구명조끼 선택</th>
					<th>입금금액(원)</th>
					<th>입금예정일</th>
					<th>입금정보</th>
					<th>접수상태</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="item" items="${list}" varStatus="status">
						<tr>
							<td>${fn:length(list)-status.index}</td>
							<td>${item.bo_subject}</td>
							<td>${item.mbr_name }</td>
							<td>${item.jacket_offer}</td>
							<td>
								<fmt:formatNumber value="${item.deposit_amount}" groupingUsed="true"/>
							</td>
							<td>${fn:substring(item.depo_date,0,10)}</td>
							<td>${item.cg_account_name} <br/> ${item.cg_account}</td>
							<td>${item.mbr_status}</td>
							<td><a href="#;" onclick="detail_view('${item.idx}','${item.bo_sn}','${item.mbr_group}')">상세보기</a></td>
						</tr>
					</c:forEach>
					<c:if test="${list eq null }">
						<tr>
							<td colspan="9">작성된 글이 없습니다.</td>
						</tr>					
					</c:if>
			</tbody>
		</table>
		</div>
	</section>
</div>
<script>
	function detail_view(idx,bo_sn,mbr_group){
		if(!$.trim(idx)||!$.trim(bo_sn)){
			alert("해당 대회를 찾을 수 없습니다.");
			return false;
		}
		var form = document.getElementById('listform');
		var url = "/sosig/congress/mbr_detail.do";
		window.open("about:blank","mbr_detail","width=1200,height=800,scrollbars=yes");
		$('#bo_sn').val(bo_sn);
		$('#idx').val(idx);
		$('#mbr_group').val(mbr_group);
		form.action = url;
		form.method = "post";
		form.target="mbr_detail";
		form.submit();
	}
</script>
<%@include file="../layout/tail.jsp"%>