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
<c:set var="pageName" value="congresscheck" />

<%@include file="../layout/head.jsp"%>



<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" id="bo_sn"  name="bo_sn" value=""/>
	<input type="hidden" id="regit_num" name="regit_num" value=""/>
	<input type="hidden" id="bo_email"  name="bo_email" value=""/>
</form:form>	
<div id="fishjobList" class="content respon2">
	<section id="webzineList" class="list_box">
	<div style="padding:20px;" align="center;">
		<a href="${pageContext.request.contextPath}/sosig/congress/check.do;" class="btn btn-primary">조회 페이지로</a>
	</div>
	<div class="board_list">
		<table class="list_tbl">
			<colgroup>
				<col  />
				<col width="100"/>
				<col width="140" />
				<col width="140" />
				<col width="140" />
				<col width="140" />
			</colgroup>
			<thead>
				<tr>
					<th>대회명</th>
					<th>진행상태</th>
					<th>등록신청일</th>
					<th>등록적용일</th>
					<th>바로가기</th>
					<th>신청자현황</th>
				</tr>
			</thead>
			<tbody id="apnd">
				<c:forEach var="item" items="${list}">
						<tr>
							<td><span><c:out value="${item.bo_subject}"/></span></td>
							<td class="subject align_left">
								<c:if test="${item.bo_trash eq '1' }">등록완료</c:if>
								<c:if test="${item.bo_trash eq '0' }">대기중</c:if>
								<c:if test="${item.bo_trash eq '3' }">미승인</c:if>
							</td>
							<td>
								${fn:substring(item.bo_insert_dt,0,10)}
							</td>
							<td>
								<c:if test="${item.bo_trash eq '1' }">${fn:substring(item.bo_update_dt,0,10)}</c:if>
								<c:if test="${item.bo_trash eq '0' }">--</c:if>
								<c:if test="${item.bo_trash eq '3' }">--</c:if>
							</td>
							<td><a href="#;" onclick="go_link('${item.bo_sn}','${item.regit_num}','${item.bo_email}')">수정</a></td>
							<td>${item.cnt} 명 <a href="#;" onclick="show_application('${item.bo_sn}','${item.regit_num}','${item.bo_email}')">[보기]</a></td>
						</tr>
					</c:forEach>
					<c:if test="${list eq null }">
						<tr>
							<td colspan="6">작성된 글이 없습니다.</td>
						</tr>					
					</c:if>
			</tbody>
		</table>
		</div>
	</section>
</div>

<script>
function show_application(bo_sn,regit_num,bo_email){
	if(!$.trim(bo_sn)){
		alert("해당 대회를 찾을 수 없습니다.");
		return false;
	}
	var form = document.getElementById('listform');
	var url = "/sosig/congress/list_show.do";
	window.open("about:blank","popwin","width=1400,height=800,scrollbars=yes");
	$('#bo_sn').val(bo_sn);
	$('#regit_num').val(regit_num);
	$('#bo_email').val(bo_email);
	form.action = url;
	form.method = "post";
	form.target="popwin";
	form.bo_sn = bo_sn;
	form.submit();
	
}

function go_link(bo_sn,regit_num,bo_email){
	if(!$.trim(bo_sn)){
		alert("해당 대회를 찾을 수 없습니다.");
		return false;
	}
	
	var form = document.getElementById('listform');
	$('#bo_sn').val(bo_sn);
	$('#regit_num').val(regit_num);
	$('#bo_email').val(bo_email);
	form.action="./write2.do";
	form.submit();
}
</script>

<%@include file="../layout/tail.jsp"%>