<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="promotion"/>
<c:set var="depthNum" value="5" />
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="campaign" />


<%@include file="../../layout/m/head.jsp"%>	

<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>

<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
<c:set var="pagesize" value="${select_total/pageUnit}"/>

	<div id="fishjobList" class="content respon2">
		<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
					<li class="on"><a href="/promotion/campaign/m/list.do">진행중인 캠페인</a></li>
					<li><a href="/promotion/campaign/m/endlist.do">종료된 캠페인</a></li>
			</ul>
		</div>

		<section id="webzineList" class="list_box">
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
					<a href="#;" onclick="fnSelectInfs2('list')" class="change_type list"><i class="fa fa-th-list" aria-hidden="true" <c:if test="${gallery_list eq 'list'}">title="리스트형 선택됨"</c:if>></i></a>
					<a href="#;" onclick="fnSelectInfs2('gallery')" class="change_type gallery on"><i class="fa fa-th-large" aria-hidden="true" <c:if test="${gallery_list eq 'gallery'}">title="갤러리형 선택됨"</c:if>></i></a>
			
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
								<td>${select_total-item.rn+1}</td>
								<td class="subject align_left"><em><a href="#;" onclick="view2('${item.bo_sn}')">${item.bo_subject }</a></em>								
								<span class="hit"><i class="fa fa-eye" aria-hidden="true">
								</i> <c:out value="${item.bo_view}"></c:out> &nbsp;</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true">
								</i> ${fn:substring(item.bo_insert_dt,0,10) }</span>
								
								</td>
								
							</tr>
						</c:forEach>
					<c:if test="${select_list eq null }">
						<tr>
							<td colspan="5">등록된 게시물이 없습니다.</td>
						</tr>					
					</c:if>
					</tbody>
				</table>
			</div>
			<div class="gallery_list">
				<ul class="floats">
					<c:forEach var="item" items="${select_list}">
					<li>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="pic"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="0"/>' alt="${item.orignl_file_nm }"  width="100%" height="100%"  /></a>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="subject">
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
						<a href="#;" onclick="view2('${item.bo_sn}')" class="search_square"><i class="fa fa-search" aria-hidden="true"></i></a>
						<ul class="eyes_heart floats">
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> ${item.bo_view}</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> ${item.bo_like }</a></li>
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

if(pageName =='campaign' && gallery_list == 'gallery'){
$('.change_type.list').removeClass("on");
$('.change_type.gallery').addClass("on");
$('.board_list').addClass("off");
$('.gallery_list').removeClass("off");
$('.webzine_list').addClass("off");
}

function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);
	
	$("#frm").attr("action", "${pageContext.request.contextPath}/promotion/campaign/m/list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}
function fnSelectInfs2(gallery_list) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(1);
	$("#gallery_list").val(gallery_list);
	$("#frm").attr("action", "${pageContext.request.contextPath}/promotion/campaign/m/list.do");
	
	$("#frm").submit();
	
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}	
	

	function view2(bo_sn){
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};	


	
</script>


<%@include file="../../layout/m/tail.jsp"%>