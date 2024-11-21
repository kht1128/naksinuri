<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="${column}" />
<c:set var="pageName" value="${table}" />

<c:choose>
	<c:when test="${depthName eq 'lesson'}">
		<c:set var="depthNum" value="1"></c:set>
	</c:when>
	<c:when test="${depthName eq 'info'}">
		<c:set var="depthNum" value="2"></c:set>
	</c:when>
	<c:when test="${depthName eq 'share'}">
		<c:set var="depthNum" value="3"></c:set>
	</c:when>
	<c:when test="${depthName eq 'sosig'}">
		<c:set var="depthNum" value="4"></c:set>
	</c:when>
	<c:when test="${depthName eq 'promotion'}">
		<c:set var="depthNum" value="5"></c:set>
	</c:when>	
</c:choose>


<%@include file="../../layout/m/head.jsp"%>

<style>
.floats li{width:100%;}
</style>


<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
<input type="hidden" name="bo_cate" id="bo_cate" value="${bo_cate}"/>
<c:set var="pagesize" value="${select_total/pageUnit}"/>

	<div id="fishjobList" class="content respon2">
			
			<c:choose>
		
				<c:when test="${pageName eq 'junior'}">
					<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
						<ul class="floats">
							<li class="jucateriv on" onclick="fnSelectInfs3('민물')"><a href="#;" title="민물(붕어)낚시">민물(붕어)낚시</a></li>
							<li class="jucatesea" onclick="fnSelectInfs3('바다')"><a href="#;" title="바다낚시">바다낚시</a></li>
						</ul>
					</div>
				</c:when>
				<c:when test="${pageName eq 'gosu'}">
					<div class="tabArea tab3"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
						<ul class="floats">
							<li class="gocateriv on" onclick="fnSelectInfs3('민물')"><a href="#;" title="민물(붕어)낚시"="민물(붕어)낚시">민물(붕어)낚시</a></li>
							<li class="gocatesea" onclick="fnSelectInfs3('바다')"><a href="#;" title="바다낚시">바다낚시</a></li>
							<li class="gocateroo" onclick="fnSelectInfs3('루어')"><a href="#;" title="루어낚시">루어낚시</a></li>
						</ul>
					</div>
				</c:when>
				<c:when test="${pageName eq 'class'}">
					<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
						<ul class="floats">
							<li class="clacateriv on" onclick="fnSelectInfs3('민물')"><a href="#;" title="민물낚시">민물낚시</a></li>
							<li class="clacatesea" onclick="fnSelectInfs3('바다')"><a href="#;ㅣ" title="바다낚시">바다낚시</a></li>
						</ul>
					</div>
				</c:when>
			</c:choose>
			<section id="webzineList" class="list_box">
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
			
				
					<c:if test="${(pageName ne 'notice') and (pageName ne 'policy') and (pageName ne 'gosi') and (pageName ne 'info')}">
						<a href="#;" onclick="fnSelectInfs2('list')" class="change_type list"><i class="fa fa-th-list" aria-hidden="true"></i></a>
						<a href="#;" onclick="fnSelectInfs2('gallery')" class="change_type gallery on"><i class="fa fa-th-large" aria-hidden="true"></i></a>
					</c:if>	
			</div>
			
			<div class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="60" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
						
						</tr>
					</thead>
					<tbody>
					<c:if test="${(pageName eq 'notice') or (pageName eq 'policy') or(pageName eq 'gosi') or (pageName eq 'info')}">
						<c:forEach var="item2" items="${noticlist}">
							<tr>
								<td><span><b class="colorBlue">공지</b></span></td>
								<td class="subject align_left"><a class="colorBlue" href="#;"onclick="view2('${item2.bo_sn}')">
									<c:set var="TextValue" value="${item2.bo_subject}"/>
										<em>	${fn:substring(item2.bo_subject,0,24)}
											<c:if test="${fn:length(item2.bo_subject) > 25 }">...</c:if>
										</em></a>
									<span class="hit"><i class="fa fa-eye" aria-hidden="true">
									</i> <c:out value="${item2.bo_view }"></c:out> &nbsp;</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true">
									</i> ${fn:substring(item2.bo_insert_dt,0,10)}</span>
								</td>
								
							
							</tr>
						</c:forEach>
					</c:if>
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
						<c:choose>
							<c:when test="${(pageName eq 'notice') or (pageName eq 'policy') or (pageName eq 'gosi') or (pageName eq 'info')}">
								<td colspan="6">등록된 게시물이 없습니다.</td>
							</c:when>
							<c:otherwise>
								<td colspan="4">등록된 게시물이 없습니다.</td>
							</c:otherwise>
						</c:choose>
						</tr>					
					</c:if>
					</tbody>
				</table>
			</div>
			
			<div class="webzine_list">
				<ul class="floats">
					<c:forEach var="item" items="${select_list}">
						<li>
							<a href="#;" onclick="view2('${item.bo_sn}')" class="pic"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="0"/>' alt="${item.orignl_file_nm}"  width="100%" height="100%"  /></a>
							<a href="#;" onclick="view2('${item.bo_sn}')" class="subject">
							
								<em><c:choose><c:when test="${fn:length(item.bo_subject)>35 }">${fn:substring(item.bo_subject,0,36)}...</c:when><c:otherwise>${item.bo_subject}${isMobileDevice}</c:otherwise></c:choose></em>
								<jsp:scriptlet>
								    pageContext.setAttribute("cr", "\r");
								    pageContext.setAttribute("lf", "\n");
								    pageContext.setAttribute("crlf", "\r\n");
								</jsp:scriptlet> 
								<c:set var="boCont" value="${fn:replace(item.bo_content , crlf, '</br>' )}"/>
								<c:choose>
									<c:when test="${fn:length(boCont)>150 }">
										<span id="aaa" onclick="view2('${item.bo_sn}')" class="txt">자세히보기...</span>
									</c:when>
									<c:otherwise>
										<span id="aaa" onclick="view2('${item.bo_sn}')" class="txt">${boCont}</span>
									</c:otherwise>
								
								</c:choose>
							</a>
						
							<ul class="eyes_heart floats">
								<li class="name_date">
									<c:choose>
								<c:when test="${pageName eq 'travel' }">
									<span class="names">${item.bo_cate}</span>
								</c:when>
								<c:otherwise>
									<span class="names">${item.bo_name}</span>
								</c:otherwise>
							</c:choose>
								<c:if test="${(pageName eq 'issue') or (pageName eq 'column') or (pageName eq 'travel') or (pageName eq 'usage')}">
									| <span class="date">${fn:substring(item.bo_insert_dt,0,10)}</span>
									
								</c:if>
								</li>
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
			
			<div class="gallery_list">
				<ul class="floats">
					<c:forEach var="item" items="${select_list}">
					<li>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="pic"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="0"/>'  width="100%" height="100%"  /></a>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="subject">
							<span class="cate">종료된 이벤트</span>
							<c:set var="TextValue" value="${item.bo_subject}"/>
							<!--  이벤트 제목 textValue 13 이상 ... 처리 -->
					<!--  이벤트 제목 textValue 14 이상 ... 처리 -->
							<em>	${fn:substring(item.bo_subject,0,10)}
						
								<c:if test="${fn:length(item.bo_subject) > 10 }">...</c:if>
							</em>
							<span class="bestfish"><b>이벤트기간 : </b>2017.01.01 ~ 2017.01.023
							</span>
						</a>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="search_square"><i class="fa fa-search" aria-hidden="true"></i></a>
						<ul class="eyes_heart floats">
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> 3,541</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li>
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
			
			<c:if test="${(pageName eq 'travel') or (pageName eq 'usage') or (pageName eq 'column')}">
				<div id="btnArea" class="noupline">
					<ul class="floats">
						<li class="fr">
							<a href="./write.do" class="btn_report btn_red">글쓰기</a>
						</li>
					</ul>
				</div>
			</c:if>
		</section>
	</div>
</form>
<script>
var pageName = "${pageName}";
var gallery_list= "${gallery_list}";
var bo_cate = "${bo_cate}";



// 게시판 리스트 or 갤러리 토글버튼
$('.change_type.list').click(function(){
	$(this).addClass("on");
	$('.change_type.gallery').removeClass("on");
	$('.board_list').removeClass("off");
	$('.gallery_list').addClass("off");
	$('.webzine_list').addClass("off");
});

$('.change_type.gallery').click(function(){
	$(this).addClass("on");
	$('.change_type.list').removeClass("on");
	$('.webzine_list').removeClass("off");
	$('.gallery_list').addClass("off");		
	$('.board_list').addClass("off");
});

if(gallery_list == 'list'){
	$('.change_type.list').addClass("on");
	$('.change_type.gallery').removeClass("on");
	$('.board_list').removeClass("off");
	$('.gallery_list').addClass("off");
	$('.webzine_list').addClass("off");
}
if(gallery_list == 'gallery'){
	$('.change_type.gallery').addClass("on");
	$('.change_type.list').removeClass("on");
	$('.board_list').addClass("off");
	$('.gallery_list').addClass("off");
	$('.webzine_list').removeClass("off");
}

if(pageName =='event' && gallery_list == 'gallery'){
	$('.change_type.list').removeClass("on");
	$('.change_type.gallery').addClass("on");
	$('.board_list').addClass("off");
	$('.gallery_list').removeClass("off");
	$('.webzine_list').addClass("off");
}

if(pageName =='notice' || pageName=='policy' || pageName=='info'){
	$('.change_type.list').addClass("on");
	$('.change_type.gallery').removeClass("on");
	$('.board_list').removeClass("off");
	$('.webzine_list').addClass("off");
}

if(bo_cate=='민물'){
	$('.jucatesea').removeClass("on");
	$('.gocatesea').removeClass("on");
	$('.gocateroo').removeClass("on");
	$('.clacatesea').removeClass("on");
	$('.jucateriv').addClass("on");
	$('.gocateriv').addClass("on");
	$('.clacateriv').addClass("on");
}
if(bo_cate=='바다'){
	$('.jucateriv').removeClass("on");
	$('.gocateriv').removeClass("on");
	$('.gocateroo').removeClass("on");
	$('.clacateriv').removeClass("on");
	$('.jucatesea').addClass("on");
	$('.gocatesea').addClass("on");
	$('.clacatesea').addClass("on");
}
if(bo_cate=='루어'){
	$('.jucateriv').removeClass("on");
	$('.gocateriv').removeClass("on");
	$('.clacateriv').removeClass("on");
	$('.jucatesea').removeClass("on");
	$('.gocatesea').removeClass("on");
	$('.clacatesea').removeClass("on");
	$('.gocateroo').addClass("on");
}


function fnSelectInfs(pageIndex) {

$("#pageUnit").val();
$("#pageIndex").val(pageIndex);
$("#gallery_list").val();
$("#bo_cate").val();		
$("#frm").attr("action", "${pageContext.request.contextPath}/${column}/${table}/m/list.do");
$("#frm").submit();
//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}
function fnSelectInfs2(gallery_list) {

$("#pageUnit").val();
$("#pageIndex").val();
$("#gallery_list").val(gallery_list);
$("#frm").attr("action", "${pageContext.request.contextPath}/${column}/${table}/m/list.do");

$("#frm").submit();

//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}	
function fnSelectInfs3(bo_cate){
$("#pageUnit").val();
$("#pageIndex").val(1);
$("#bo_cate").val(bo_cate);	
$("#frm").attr("action", "${pageContext.request.contextPath}/${column}/${table}/m/list.do");

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