<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../naksinuri_original/naksinuri/layout/newHead.jsp"%>
	
	<style>
	.btn_custom_link1 {
		display:inline-block;width:100%;max-width:450px;padding:15px 10px;background:#5492e9;color:#fff;text-align:center;font-size:18px;margin-top:10px;
	}
	.btn_custom_link2 {
		display:inline-block;width:100%;max-width:450px;padding:15px 10px;background:#2f69ba;color:#fff;text-align:center;font-size:18px;margin-top:10px;
	}
	@media (max-width: 768px) {
		.btn_custom_link1, .btn_custom_link2 {font-size:16px;padding:10px;}
	}
	</style>
	
	<div id="honorObserver" class="content respon2">
		<section id="observerCon">
			<ol>
				<li>
					<h4>관계법령</h4>
					<dl>
						<dt>○ 낚시 관리 및 육성법</dt>
						<dd>
							<a href="http://www.law.go.kr/법령/낚시관리및육성법" target="_blank" class="btn_custom_link1">낚시 관리 및 육성법 바로가기</a>
						</dd>
					</dl>
					<dl>
						<dt>○ 낚시전문교육 및 교육기관 지정에 관한 고시</dt>
						<dd>
							<a href="https://www.law.go.kr/admRulLsInfoP.do?admRulSeq=2100000191740" target="_blank"  class="btn_custom_link2">낚시전문교육 및 교육기관 지정에 관한 고시 바로가기</a>
						</dd>
					</dl>
				</li>
			</ol>
		</section>
	</div>
	
<script>
//1024px 이하에서 적용
if (matchMedia("screen and (max-width: 768px)").matches) {
	$('.btn_custom_link1, .btn_custom_link2').text('링크 바로가기');
} 
</script>
	
<%@include file="../../naksinuri_original/naksinuri/layout/tail.jsp"%>

