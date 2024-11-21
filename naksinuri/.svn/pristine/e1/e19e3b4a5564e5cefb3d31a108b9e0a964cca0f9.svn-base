<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="pageMode" value="info"/>
<c:set var="depthNum" value="2" />
<c:set var="depthName" value="info" />
<c:set var="pageName" value="lab" />




<%@include file="../../layout/m/head.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<c:set var="pagesize" value="${select_total/pageUnit}"/>

	<div class="content respon2">
		<section id="webzineList" class="list_box">
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
			</div>
			
			<div class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="130" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
						
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${select_list}">
						<tr>
							<td><span>${select_total-item.rn+1}</span></td>
							<td class="subject align_left"><a href="#;"onclick="view2('${item.bo_sn}')">
							
							<c:set var="TextValue" value="${item.bo_subject}"/>
								<em>${fn:substring(item.bo_subject,0,44)}
									<c:if test="${fn:length(item.bo_subject) > 45 }">...</c:if>
								</em></a>
							<span class="hit"><i class="fa fa-eye" aria-hidden="true">
							</i> <c:out value="${item.bo_view }"></c:out> &nbsp;</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true">
							</i> ${fn:substring(item.bo_insert_dt,0,10)}</span>
							</td>
							
						</tr>
					</c:forEach>
					<c:if test="${select_list eq null }">
						<tr>				
							<td colspan="4">등록된 게시물이 없습니다.</td>				
						</tr>					
					</c:if>
					</tbody>
				</table>
			</div>
			
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
			
		</section>
	</div>
</form>
<script type="text/javascript">
	// 게시판 리스트 or 갤러리 토글버튼
	var pageName = "${pageName}";

	

	function fnSelectInfs(pageIndex) {

		$("#pageUnit").val();
		$("#pageIndex").val(pageIndex);
		$("#gallery_list").val();
		$("#bo_cate").val();		
		$("#frm").attr("action", "${pageContext.request.contextPath}/sosig/lab/m/list.do");
		$("#frm").submit();
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
		}
		function fnSelectInfs2(gallery_list) {

		$("#pageUnit").val();
		$("#pageIndex").val();
		$("#gallery_list").val(gallery_list);
		$("#frm").attr("action", "${pageContext.request.contextPath}/sosig/lab/m/list.do");

		$("#frm").submit();
		}
	
		function view2(bo_sn){
			var form = document.getElementById('listform');
			$('#bo_sn').val(bo_sn);
			
			form.action="./view.do";
			form.submit();
		};	
	
	
		
</script>

<%@include file="../../layout/m/tail.jsp"%>