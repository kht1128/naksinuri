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
<c:set var="pageName" value="qna" />


<%@include file="../layout/newHead.jsp"%>
	<div id="knowledge" class="content respon2">
	
	<!-- //바로가기 링크 관련 추가 2018.07.18 -->
	<input type="hidden" id="shortcutlink" value="${shortcutlink}"/>
	
	<form id="frm" name="frm" method="post">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
	<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
	<input type="hidden" name="qna_type" id="qna_type" value="${qna_type}"/>
	<input type="hidden" class="frm_select" id="s_pageUnit" name="s_pageUnit" value="10" onchange="fnSelectInfs(1);">
	<fmt:parseNumber var= "pages" integerOnly= "true" value= "${select_total/pageUnit+1}" />
		<div class="tabArea tab6 mbNone"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
						<li onclick="fnSelectInfs2('낚시관리및제도일반')"class="tab1 on"><a href="#;" ${category_type == "fishing_management_system" ? 'title="낚시관리및제도일반 선택됨"' : 'title="낚시관리및제도일반"'}>낚시관리및제도일반</a></li>
	 					<li onclick="fnSelectInfs2('낚시터')"class="tab2"><a href="#;" ${category_type == "fishing_hole" ? 'title="낚시터 선택됨"' : 'title="낚시터"'}>낚시터</a></li>
	 					<li onclick="fnSelectInfs2('낚시어선')"class="tab3"><a href="#;" ${category_type == "fishing_boats" ? 'title="낚시어선 선택됨"' : 'title="낚시어선"'}>낚시어선</a></li>
	 					<li onclick="fnSelectInfs2('낚시도구및미끼')"class="tab4"><a href="#;" ${category_type == "fishing_tackle_bait" ? 'title="낚시도구및미끼 선택됨"' : 'title="낚시도구및미끼"'}>낚시도구및미끼</a></li>
	 					<li onclick="fnSelectInfs2('유어장')"class="tab5"><a href="#;" ${category_type == "fishing_area" ? 'title="유어장 선택됨"' : 'title="유어장"'}>유어장</a></li>
	 					<li onclick="fnSelectInfs2('참고자료')"class="tab6"><a href="#;" ${category_type == "fishing_resources" ? 'title="참고자료 선택됨"' : 'title="참고자료"'}>참고자료</a></li>
			</ul>
		</div>
		
		<div class="tabArea2 mbShow"> <!-- 모바일 ui -->
			<div class="link_select">
				<dl>
					<dt><a href="#;" id="this_cate"> <span><i class="fa fa-angle-down" aria-hidden="true"></i></span></a></dt>
					<dd>
						<ul>
							<li onclick="fnSelectInfs2('낚시관리및제도일반')"><a href="#;">낚시관리및제도일반</a></li>
							<li onclick="fnSelectInfs2('낚시터')"><a href="#;">낚시터</a></li>
							<li onclick="fnSelectInfs2('낚시어선')"><a href="#;">낚시어선</a></li>
							<li onclick="fnSelectInfs2('낚시도구및미끼')"><a href="#;">낚시도구및미끼</a></li>
							<li onclick="fnSelectInfs2('유어장')"><a href="#;">유어장</a></li>
							<li onclick="fnSelectInfs2('참고자료')"><a href="#;">참고자료</a></li>
						</ul>
					</dd>
				</dl>
			</div>
		</div>
	
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
					<li>질의 <span style="font-size:12px">(이 회신집에 수록된 사항은 참고용입니다.)</span></li>
				</ul>
			</div>
			<c:forEach var="item"  items="${select_list }" varStatus="status">			
			<div class="faq_con" id="faq_con_${select_total-item.rn+1}" >
				<dl>
					<dt>
						<span class="num">${select_total-item.rn+1}</span><span class="type"></span>
						<a href="#;" title="${item.qna_ques} 상세보기">${item.qna_ques}</a>
					</dt>
					<dd>
						<div style="width:100%;" >${item.qna_answ}</div>
					</dd>
				</dl>
			</div>
			</c:forEach>			
		</section>
		<div style="text-align:center">
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
		</div>
		</form>
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
/* $(document).ready(function(){
	if($('#qna_type').val() == "낚시관리및제도일반"){
		$('#this_cate').append("낚시관리및제도일반");
	}else if($('#qna_type').val()=="낚시터"){
		$('#this_cate').append("낚시터");
	}else if($('#qna_type').val()=="낚시어선"){
		$('#this_cate').append("낚시어선");
	}else if($('#qna_type').val()=="낚시도구및미끼"){
		$('#this_cate').append("낚시도구및미끼");
	}else if($('#qna_type').val()=="유어장"){
		$('#this_cate').append("유어장");
	}else if($('#qna_type').val()=="참고자료"){
		$('#this_cate').append("참고자료");
	}

}) */

var qna_type = "${qna_type}"


if(qna_type=='낚시관리및제도일반'){
	$('.tab1').addClass("on");
	$('.taball').removeClass("on");
	$('.tab2').removeClass("on");
	$('.tab3').removeClass("on");
	$('.tab4').removeClass("on");
	$('.tab5').removeClass("on");
	$('.tab6').removeClass("on");
	
}else if(qna_type=='낚시터'){
	$('.tab2').addClass("on");
	$('.taball').removeClass("on");
	$('.tab1').removeClass("on");
	$('.tab3').removeClass("on");
	$('.tab4').removeClass("on");
	$('.tab5').removeClass("on");
	$('.tab6').removeClass("on");
	
}else if(qna_type=='낚시어선'){
	$('.tab3').addClass("on");
	$('.taball').removeClass("on");
	$('.tab1').removeClass("on");
	$('.tab2').removeClass("on");
	$('.tab4').removeClass("on");
	$('.tab5').removeClass("on");
	$('.tab6').removeClass("on");
	
}else if(qna_type=='낚시도구및미끼'){
	$('.tab4').addClass("on");
	$('.taball').removeClass("on");
	$('.tab1').removeClass("on");
	$('.tab2').removeClass("on");
	$('.tab3').removeClass("on");
	$('.tab5').removeClass("on");
	$('.tab6').removeClass("on");
}else if(qna_type=='유어장'){
	$('.tab5').addClass("on");
	$('.taball').removeClass("on");
	$('.tab1').removeClass("on");
	$('.tab2').removeClass("on");
	$('.tab3').removeClass("on");
	$('.tab4').removeClass("on");
	$('.tab6').removeClass("on");
}else if(qna_type=='참고자료'){
	$('.tab6').addClass("on");
	$('.taball').removeClass("on");
	$('.tab1').removeClass("on");
	$('.tab2').removeClass("on");
	$('.tab3').removeClass("on");
	$('.tab4').removeClass("on");
	$('.tab5').removeClass("on");
}
	
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
		var idx= $('#s_pageUnit').val();
		$("#pageUnit").val(idx);
		$("#pageIndex").val(pageIndex);	
		$("#frm").attr("action", "./list.do");
		$("#frm").submit();
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}
	
	function fnSelectInfs2(qna_type) {
		var idx= $('#s_pageUnit').val();
		$("#pageUnit").val();
		$("#pageIndex").val(1);	
		$("#qna_type").val(qna_type);
		$("#frm").attr("action", "./list.do");
		$("#frm").submit();
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}
	
	$(function(){
		var idx= $('#pageUnit').val();
		$("#s_pageUnit").val(idx);
	});
	
	//바로가기 링크 관련 추가 2018.07.18
	function fnAutoSelected(targetid) {		
		if($('#'+targetid).hasClass('active')){
			$('.faq_con').removeClass('active');
			$('.faq_con dl dd').slideUp();
		}else{
			$('.faq_con').removeClass('active');
			$('#'+targetid).addClass('active');
			$('.faq_con dl dd').slideUp();
			$('#'+targetid+' dl dd').slideDown();
		}
	}
	var shortcutlink = $('#shortcutlink').val();
	if(shortcutlink.length!=0) {
		fnAutoSelected('faq_con_'+shortcutlink);
	}
	//
	
</script>


<%@include file="../layout/tail.jsp"%>