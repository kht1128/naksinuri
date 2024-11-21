<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="share"/>
<c:set var="depthNum" value="3" />
<c:set var="depthName" value="share" />
<c:set var="pageName" value="zisik" />



<%@include file="../../layout/m/head.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="nuri_q_num" id="nuri_q_num"/>
</form:form>
	<div id="qnaList" class="content respon2">
		<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
<!-- 				<li class="on"><a href="/share/zisik/m/list.do">누리지식인</a></li> -->
				<li><a href="/share/nuri/m/list.do">자주묻는낚시질문</a></li>
			</ul>
		</div>
	<form id="frm" name="frm" method="post">
		<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
		<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
		<fmt:parseNumber var= "pages" integerOnly= "true" value= "${zisik_total/pageUnit+1}" />
				
		<section class="list_box">
			<div class="list_searchbox">
				<div class="total_num">전체 <b class="colorSky"><c:out value="${zisik_total}"></c:out></b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${pages })</div>
				
				<select class="basic_select" name="searchType">
					<option value="nuri_q_subcon">제목+내용</option>
					<option value="nuri_q_subject">제목</option>
					<option value="nuri_q_content">내용</option>
				</select>
				<input type="text" class="basic_input" id="searchText" name="searchText" />
				<button class="searchBtn nomargin"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
			<div class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="120" />
						<col />
						<col width="150" />
						<col width="150" />
						<col width="130" />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>답변여부</th>
							<th>작성일</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${zisik_list }">
							<tr>
								<td>${item.tot_cnt - item.rn + 1}</td>
								<td class="subject align_left"><a href="#"onclick="view2('${item.nuri_q_num}')">
								
							
									<c:set var="TextValue" value="${item.nuri_q_subject }"/>
											${fn:substring(item.nuri_q_subject,0,27)}
											<c:if test="${fn:length(item.nuri_q_subject) > 28 }">...</c:if>																		
								</a></td>
								
								<c:choose>
									<c:when test="${item.nuri_a_count eq 0 }">
										<td class="state"><span class="wait">답변대기</span></td>
									</c:when>
									<c:otherwise>
										<td class="state"><span class="complete">답변완료</span></td>
									</c:otherwise>
								</c:choose>
								<td class="date">${item.nuri_q_date }</td>
								<td class="name">${item.nuri_q_writer }</td>
							</tr>
						</c:forEach>
						<c:if test="${zisik_list eq null}">
							<tr>
								<td colspan="5">등록된 질문이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</section>		

		<div id="btnArea" class="noupline">
			<ul class="floats">
				<li class="fr">
					<a href="/share/zisik/write.do" class="btn_blue">질문하기</a>
				</li>
			</ul>
		</div>
		<div style="text-align:center">
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
		</div>
	</form>
	</div>
	<script type="text/javascript">
		function view2(nuri_q_num){
			var form = document.getElementById('listform');
			$('#nuri_q_num').val(nuri_q_num);
			
			form.action="./view.do";
			form.submit();
		}
		
		function fnSelectInfs(pageIndex) {
			
			$("#pageUnit").val();
			$("#pageIndex").val(pageIndex);
			
			$("#frm").attr("action", "${pageContext.request.contextPath}/share/zisik/m/list.do");
			$("#frm").submit();
			//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
		}
	
	</script>

<%@include file="../../layout/m/tail.jsp"%>