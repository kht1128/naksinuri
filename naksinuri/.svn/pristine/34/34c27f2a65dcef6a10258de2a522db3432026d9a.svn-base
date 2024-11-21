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
<c:set var="pageName" value="angling" />


<%@include file="../../layout/m/head.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="mid" id="mid"/>
</form:form>

<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="category" id="category" value="${category}" />

	<div id="knowledge" class="content respon2">
		<div class="tabArea tab4"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
				<li class="cate_all on" onclick="fnSelectInfs3('')"><a href="#;">FTV</a></li>
<!-- 				<li class="cate_river" onclick="fnSelectInfs3('민물')"><a href="#;">민물낚시</a></li> -->
<!-- 				<li class="cate_sea" onclick="fnSelectInfs3('바다')"><a href="#;">바다낚시</a></li> -->
<!-- 				<li class="cate_lure" onclick="fnSelectInfs3('루어')"><a href="#;">루어낚시</a></li> -->
			</ul>
		</div>
		<section class="faq_list">
			<div class="faq_head">
				<ul>
					<li class="num">번호</li>
					<li>제목</li>	
				</ul>
			</div>
			<c:forEach var="item"  items="${select_list}" varStatus="status">			
				<div class="faq_con">
					<dl>
						<dt>
							<span class="num">${select_total-item.rn+1}</span>
							<a><span class="type">${item.category}</span>${item.title}</a>
						</dt>
						<dd>
							<div style="width:100%;" >${item.body}</div>
						</dd>
					</dl>
				</div>
			</c:forEach>			
		</section>	
		<div id="pagenation">
			<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
		</div>
</div>
</form>
<script type="text/javascript">

$('.faq_con dl dt a').click(function() {
	$('.faq_con dl dd').slideUp();
	$(this).parent().next().slideDown();

	$('.faq_con').removeClass('active');
	$(this).parent().parent().parent().addClass('active');
});

	var pageName = "${pageName}";
	var category = "${category}";


function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);
	
	$("#frm").attr("action", "${pageContext.request.contextPath}/info/angling/m/list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}


	function fnSelectInfs3(category){
		$("#pageUnit").val();
		$("#pageIndex").val(1);
		$("#category").val(category);
		
		if(category == '민물'){
			$("#frm").attr("action","${pageContext.request.contextPath}/info/angling/m/riverlist.do");
				}else if(category == '바다'){
			$("#frm").attr("action","${pageContext.request.contextPath}/info/angling/m/sealist.do");
				}else if(category == '루어'){
			$("#frm").attr("action","${pageContext.request.contextPath}/info/angling/m/lurelist.do");
				}else if(category == ''){
			$("#frm").attr("action", "${pageContext.request.contextPath}/info/angling/m/list.do");
				}
		$("#frm").submit();
	}

$(document).ready(function(){
// 	if($('#category').val() == ""){
// 		$('#this_cate').append("FTV 실시간 조황정보");
// 	}else if($('#category').val()=="민물"){
// 		$('#this_cate').append("민물");
// 	}else if($('#category').val()=="바다"){
// 		$('#this_cate').append("바다");
// 	}else if($('#category').val()=="루어"){
// 		$('#this_cate').append("루어");
// 	}

	var category = $('#category').val();

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
})
	
	

	
</script>

<%@include file="../../layout/m/tail.jsp"%>