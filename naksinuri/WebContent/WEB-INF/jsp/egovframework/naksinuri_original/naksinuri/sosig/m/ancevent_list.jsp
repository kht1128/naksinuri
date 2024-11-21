<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="sosig"/>
<c:set var="depthNum" value="4" />
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="event" />


<%@include file="../../layout/m/head.jsp"%>

	<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="evn_no" id="evn_no"/>
</form:form>
	<form action="" id="frm" name="frm" method="post">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
	<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
	<input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
	<c:set var="pagesize" value="${event_total/pageUnit}"/>

		<div id="fishjobList" class="content respon2">
	
			<div class="tabArea tab3">
				<ul class="floats">
					<li><a href="/sosig/event/m/list.do" id="event" >진행중인 이벤트</a></li>
					<li><a href="/sosig/endevent/m/list.do" id="endevent">종료된 이벤트</a></li>
					<li class="on"><a href="/sosig/ancevent/m/list.do" id="ancevent">당첨자 발표</a></li>				
				</ul>
			</div>
				
		<section id="webzineList" class="list_box">
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${event_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
			
				
			</div>
			
			<div class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="70" />
						<col />
						<!-- 
						<col width="130" />
						<col />					
						<col width="140" />
						<col width="140" />
						 -->
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<!-- 
							<th>조회수</th>
							<th>등록일</th>
							 -->
						</tr>
					</thead>
					<tbody>
				
					<c:forEach var="item" items="${event_list}">
						<tr>
							<td><span>${item.rn}</span></td>
							<td class="subject align_left"><a href="#;"onclick="view2('${item.evn_no}')">
								<c:set var="TextValue" value="${item.evn_subject}"/>
						
									<em>	${fn:substring(item.evn_subject,0,25)}
						
										<c:if test="${fn:length(item.evn_subject) > 26 }">...</c:if>
									</em>
							</a></td>
							<%--
							<td><c:out value="${item.evn_viewhit }"></c:out></td>
							<td class="date">${fn:substring(item.evn_insert_dt,0,10)}</td>
							 --%>
						</tr>
					</c:forEach>
					<c:if test="${event_list eq null }">
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

<script>

	var pageName = "${pageName}";
	


	

	
	function fnSelectInfs(pageIndex) {
		
		$("#pageUnit").val();
		$("#pageIndex").val(pageIndex);
		
		$("#frm").attr("action", "${pageContext.request.contextPath}/sosig/ancevent/m/list.do");
		$("#frm").submit();
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}
	function fnSelectInfs2(gallery_list) {
		
		$("#pageUnit").val();
		$("#pageIndex").val(1);
		$("#gallery_list").val(gallery_list);
		$("#frm").attr("action", "${pageContext.request.contextPath}/sosig/ancevent/m/list.do");
		
		$("#frm").submit();
		
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}	
	
	function view2(evn_no){
		var form = document.getElementById('listform');
		$('#evn_no').val(evn_no);
		
		form.action="/sosig/event/m/view.do";
		form.submit();
	};	

		
</script>

<%@include file="../../layout/m/tail.jsp"%>