<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="promotion"/>
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="campaign" />


<%@include file="../layout/newHead.jsp"%>

<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>

<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
<c:set var="pagesize" value="${select_total/pageUnit}"/>

	<div id="fishjobList" class="content respon2">
		<div class="tabArea"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
				<li class="on"><a href="/promotion/campaign/list.do" title="진행중인 캠페인 선택됨">진행중인 캠페인</a></li>
				<li><a href="/promotion/campaign/endlist.do" title="종료된 캠페인">종료된 캠페인</a></li>
			</ul>
		</div>

		<section id="webzineList" class="list_box">
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
				<select class="basic_select" name="searchType" title="옵션선택(제목+내용,제목,내용)">
					<option value="bo_subcon">제목+내용</option>
					<option value="bo_subject">제목</option>
					<option value="bo_content">내용</option>
				</select>
				<input type="text" class="basic_input" name="searchText" title="검색어"/>
				<button class="searchBtn" onclick="fnSelectInfs(1)"><span class="blind">진행중인 캠페인 검색</span><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
			
			<div class="gallery_list">
				<ul class="floats">
					<c:forEach var="item" items="${select_list}">
					<li>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="pic" title="${item.bo_subject} 상세보기"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="0"/>' alt="${item.bo_subject }"  width="100%" height="100%"/></a>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="subject" title="${item.bo_subject} 상세보기">
							<span class="cate">진행중인 캠페인</span>
							<c:set var="TextValue" value="${item.bo_subject}"/>
							<!--  이벤트 제목 textValue 13 이상 ... 처리 -->
					<!--  이벤트 제목 textValue 14 이상 ... 처리 -->
							<em>	${fn:substring(item.bo_subject,0,13)}
						
								<c:if test="${fn:length(item.bo_subject) > 12 }">...</c:if>
							</em>
							<span class="bestfish"><b>캠페인기간 : </b>${item.bo_start_dt } <c:if test="${item.bo_end_dt ne null }">~ ${item.bo_end_dt }</c:if>
							</span>
						</a>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${item.bo_subject} 상세보기"></i></a>
						<ul class="eyes_heart floats">
							<li><em><i class="fa fa-eye" aria-hidden="true" title="조회수"></i></em> ${item.bo_view}</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true" title="좋아요 수"></i></em> ${item.bo_like }</a></li>
						</ul>
					</li>
					</c:forEach>
					<c:if test="${select_list eq null }">
							<li>
								<a><em></em><span id="aaa" class="txt">정보가 없습니다.</span></a>
							</li>
					</c:if>		
					
				</ul>
			</div>
			
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
		</section>
	</div>
</form>
<script type="text/javascript">
function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);
	
	$("#frm").attr("action", "${pageContext.request.contextPath}/promotion/campaign/list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}
function fnSelectInfs2(gallery_list) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(1);
	$("#gallery_list").val(gallery_list);
	$("#frm").attr("action", "${pageContext.request.contextPath}}/promotion/campaign/list.do");
	
	$("#frm").submit();
	
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}	
	

	function view2(bo_sn){
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};	
	
	// 모바일에서 검색창 숨김
	$(document).ready(function (){
		$('.list_searchbox .search_group').addClass('mbNone');
	});
	
</script>


<%@include file="../layout/tail.jsp"%>