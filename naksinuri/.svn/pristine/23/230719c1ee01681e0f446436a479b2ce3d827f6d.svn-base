<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="share"/>
<c:set var="depthName" value="share" />
<c:set var="pageName" value="nuri" />


<%@include file="../layout/newHead.jsp"%>

	<div id="knowledge" class="content respon2">
	
		<!-- <div class="tabArea tab2"> 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐
			<ul class="floats">
				<li><a href="/share/zisik/list.do">누리지식인</a></li>
				<li class="on"><a href="/share/nuri/list.do" title="자주묻는낚시질문">자주묻는낚시질문</a></li>
			</ul>
		</div> -->
	
	
	<!-- 
		<div class="tabArea2">
			<ul class="floats">
				<li><a href="#;">낚시관련문의</a></li>
				<li class="active"><a href="#;">무엇관련</a></li>
				<li><a href="#;">이벤트</a></li>
				<li><a href="#;">낚시용품관련</a></li>
				<li><a href="#;">바다/강</a></li>
			</ul>
		</div>
	 -->
		<section class="faq_list">
			<div class="faq_head">
				<ul class="floats">
					<li class="num">번호</li>
					<li class="type"></li>
					<li>질문</li>
				</ul>
			</div>
			<c:forEach var="item"  items="${zazu_list }" varStatus="status">			
			<div class="faq_con">
				<dl>
					<dt>
						<span class="num">${zazu_gun+1-status.count}</span><span class="type"></span>
						<a href="#;" title="${item.zazu_ques} 상세보기">${item.zazu_ques}</a>
					</dt>
					<dd>
						<div style="width:100%;" >${item.zazu_answ}</div>
					</dd>
				</dl>
			</div>
			</c:forEach>			
		</section>
<!--
		<div id="btnArea" class="noupline">
			<ul class="floats">
				<li class="fr">
					<a href="/share/nuri/write.do" class="btn_blue">질문하기</a>
				</li>
			</ul>
		</div>

		<div style="text-align:center">
			<ul class="pagination">
				<li>
					<a href="#;" aria-label="Previous">
						<i class="fa fa-angle-left" aria-hidden="true"></i>
					</a>
				</li>
				<li class="active"><a href="#;">1</a></li>
				<li><a href="#;">2</a></li>
				<li><a href="#;">3</a></li>
				<li><a href="#;">4</a></li>
				<li><a href="#;">5</a></li>
				<li>
					<a href="#;" aria-label="Next">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</a>
				</li>
			</ul>
		</div>
	</div>
-->
<script>
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
</script>


<%@include file="../layout/tail.jsp"%>