<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%-- 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>
<script>
location.href = '/index.do';
</script>
<%-- End of 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>

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
					<li class="on"><a href="/sosig/endevent/m/list.do" id="endevent">종료된 이벤트</a></li>
					<li><a href="/sosig/ancevent/m/list.do" id="ancevent">당첨자 발표</a></li>				
				</ul>
			</div>
				
		<section id="webzineList" class="list_box">
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${event_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
			
					<a href="#;" onclick="fnSelectInfs2('list')" class="change_type list"><i class="fa fa-th-list" aria-hidden="true"></i></a>
					<a href="#;" onclick="fnSelectInfs2('gallery')" class="change_type gallery on"><i class="fa fa-th-large" aria-hidden="true"></i></a>
				
			</div>
			
			<div class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="130" />
						<col />				
						<col width="250" />			
							
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>				
							<th>이벤트 기간</th>
						
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
							</a>
							<span class="hit"><i class="fa fa-eye" aria-hidden="true">
							</i> <c:out value="${item.evn_viewhit }"></c:out> &nbsp;</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true">
							</i> ${fn:substring(item.evn_insert_dt,0,10)}</span>
							</td>
							<td><span>${item.evn_startdate } ~ ${item.evn_enddate }</span></td>
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
			
			<div class="gallery_list">
				<ul class="floats">
					<c:forEach var="item" items="${event_list}">
					<li>
							<c:choose>
							<c:when test="${item.orignl_file_nm ne null }">
								<a href="#;" onclick="view2('${item.evn_no}')" class="pic"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.evn_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>'  width="100%" height="100%"  /></a>
							</c:when>
							<c:otherwise>
								<a href="#;" onclick="view2(${item.evn_no})" class="pic"><img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="낚시누리"/>'/></a>
							</c:otherwise>
						</c:choose>

						<a href="#;" onclick="view2('${item.evn_no}')" class="subject">
							<span class="cate">종료된 이벤트</span>
							<c:set var="TextValue" value="${item.evn_subject}"/>
							<!--  이벤트 제목 textValue 13 이상 ... 처리 -->
					<!--  이벤트 제목 textValue 14 이상 ... 처리 -->
							<em>	${fn:substring(item.evn_subject,0,10)}
						
								<c:if test="${fn:length(item.evn_subject) > 10 }">...</c:if>
							</em>
							<span class="bestfish"><b>이벤트기간 : </b>${item.evn_startdate } ~ ${item.evn_enddate }</span>
						</a>
<%-- 						<a href="#;" onclick="view2('${item.evn_no}')" class="search_square"><i class="fa fa-search" aria-hidden="true"></i></a> --%>
						<ul class="eyes_heart floats">
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>  ${item.evn_viewhit }</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> ${item.evn_like }</a></li>
						</ul>
					</li>
					</c:forEach>
					<c:if test="${event_list eq null }">
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

<script>
	var gallery_list= "${gallery_list}";
	var pageName = "${pageName}";
	// 게시판 리스트 or 갤러리 토글버튼
	// 게시판 리스트 or 갤러리 토글버튼
	
$('.change_type.list').click(function(){
	$(this).addClass("on");
	$('.change_type.gallery').removeClass("on");
	$('.board_list').removeClass("off");
	$('.gallery_list').addClass("off");
});

$('.change_type.gallery').click(function(){
	$(this).addClass("on");
	$('.change_type.list').removeClass("on");
	$('.gallery_list').addClass("off");		
	$('.board_list').addClass("off");
});

if(gallery_list == 'list'){
	$('.change_type.list').addClass("on");
	$('.change_type.gallery').removeClass("on");
	$('.board_list').removeClass("off");
	$('.gallery_list').addClass("off");

}
if(gallery_list == 'gallery'){
	$('.change_type.gallery').addClass("on");
	$('.change_type.list').removeClass("on");
	$('.board_list').addClass("off");
	$('.gallery_list').addClass("off");
}

if(pageName =='event' && gallery_list == 'gallery'){
	$('.change_type.list').removeClass("on");
	$('.change_type.gallery').addClass("on");
	$('.board_list').addClass("off");
	$('.gallery_list').removeClass("off");
	$('.webzine_list').addClass("off");
}
	
	function fnSelectInfs(pageIndex) {
		
		$("#pageUnit").val();
		$("#pageIndex").val(pageIndex);
		
		$("#frm").attr("action", "/sosig/endevent/m/list.do");
		$("#frm").submit();
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}
	function fnSelectInfs2(gallery_list) {
		
		$("#pageUnit").val();
		$("#pageIndex").val(1);
		$("#gallery_list").val(gallery_list);
		$("#frm").attr("action", "/sosig/endevent/m/list.do");
		
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