<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="info" />
<c:set var="pageName" value="angling" />


<%@include file="../layout/newHead.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="mid" id="mid"/>
</form:form>

<form id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="category" id="category" value="${category}" />


<div id="fishjobList" class="content respon2">
	<section id="webzineList" class="list_box">	
			<!-- <div class="tabArea tab4"> 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐
				<ul class="floats">
					<li class="cate_all on" onclick="fnSelectInfs3('')"><a href="#;" title="FTV 실시간 조황정보">FTV 실시간 조황정보</a></li>
					<li class="cate_river" onclick="fnSelectInfs3('민물')"><a href="#;">민물낚시</a></li>
					<li class="cate_sea" onclick="fnSelectInfs3('바다')"><a href="#;">바다낚시</a></li>
					<li class="cate_lure" onclick="fnSelectInfs3('루어')"><a href="#;">루어낚시</a></li>
				</ul>
			</div> -->
		
		<div class="list_searchbox">
			<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${Math.round(select_total/pageUnit)+1})</div>
			<select class="basic_select" name="searchType" title="검색조건선택">
				<option value="title">제목</option>
				<option value="writer_name">글쓴이</option>
			</select>
			<input type="text" class="basic_input" id="searchText2" name="searchText" title="검색어"/>
			<button class="searchBtn" type="button" onclick="fnSelectInfs(1)"><span class="blind">FTV 실시간 조황정보 검색</span><i class="fa fa-search" aria-hidden="true" ></i></button>
		</div>
	

		<div class="board_list">
		

						<section class="faq_list">
								<div class="faq_head">
									<ul class="floats">
										<li class="num">번호</li>
										<li class="type">구분</li>
										<li>제목</li>	
									</ul>
								</div>
								<c:forEach var="item"  items="${select_list }" varStatus="status">			
								<div class="faq_con">
									<dl>
										<dt>
											<%-- <span class="num">${select_total-item.rn+1}</span><span style="width:70px;"></span> --%>
											<span class="num">${select_total-item.rn+1}</span>
											<span class="type">${item.category}</span>
											<a href="#;"><span class="type mbShow">${item.category}</span>${item.title}</a>
										</dt>
										<dd>
											<div style="width:100%;" >${item.body}</div>
										</dd>
									</dl>
								</div>
								</c:forEach>			
						</section>		
				
			</div>
		
		<div id="pagenation">
			<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
		</div>
	</section>
</div>
</form>
<script type="text/javascript">

	var pageName = "${pageName}";
	var category = "${category}";
	

	$('.faq_con dl dt a').click(function() {
		if($(this).parent().parent().parent().hasClass('active')){
			$('.faq_con').removeClass('active');
			$('.faq_con dl dd').slideUp();
		}else{
			$('.faq_con').removeClass('active');
			$(this).parent().parent().parent().addClass('active');
			$('.faq_con dl dd').slideUp();
			$(this).parent().next().slideDown();
		}
	});

	
function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);
	
	$("#frm").attr("action", "${pageContext.request.contextPath}/info/angling/list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

	

// 	function view2(mid){
// 		var form = document.getElementById('listform');
// 		$('#mid').val(mid);
		
// 		form.action="./view.do";
// 		form.submit();
// 	};	

	if(category==''){
		$('.cate_all').addClass("on");
		$('.cate_lure').removeClass("on");
		$('.cate_sea').removeClass("on");
		$('.cate_river').removeClass("on");
	}
	

	if(category=='민물'){
		$('.cate_all').removeClass("on");
		$('.cate_lure').removeClass("on");
		$('.cate_sea').removeClass("on");
		$('.cate_river').addClass("on");
	}
	if(category=='바다'){
		$('.cate_all').removeClass("on");
		$('.cate_lure').removeClass("on");
		$('.cate_sea').addClass("on");
		$('.cate_river').removeClass("on");
		
	}
	if(category=='루어'){
		$('.cate_all').removeClass("on");
		$('.cate_sea').removeClass("on");
		$('.cate_river').removeClass("on");
		$('.cate_lure').addClass("on");
		
	}

function fnSelectInfs3(category){
	$("#pageUnit").val();
	$("#pageIndex").val(1);
	$("#category").val(category);
	
	if(category == '민물'){
	$("#frm").attr("action","${pageContext.request.contextPath}/info/angling/riverlist.do");
		}else if(category == '바다'){
	$("#frm").attr("action","${pageContext.request.contextPath}/info/angling/sealist.do");
		}else if(category == '루어'){
	$("#frm").attr("action","${pageContext.request.contextPath}/info/angling/lurelist.do");
		}else if(category == ''){
	$("#frm").attr("action", "${pageContext.request.contextPath}/info/angling/list.do");
		}
	$("#frm").submit();
}

//모바일에서 검색창 숨김
$(document).ready(function (){
	$('.list_searchbox .search_group').addClass('mbNone');
});

	

	
</script>

<%@include file="../layout/tail.jsp"%>