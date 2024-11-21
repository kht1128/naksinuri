<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="pageMode" value="copyright"/>
<c:set var="depthNum" value="0" />
<c:set var="depthName" value="사이트이용안내" />
<c:set var="pageName" value="저작권보호정책" />


<%@include file="../layout/newHead.jsp"%>

	<div id="copyright" class="content respon3">
		<h2>낚시누리 사이트 저작권보호 정책</h2>
		<section id="copyrightCon">
			<p>
				우리 부 사이트의 내용은 저작권법에 의한 보호를 받는 저작물로서, 이에 대한 무단 복제 및 배포를 원칙적으로 금합니다. 이를 무단 복제 · 배포하는 경우 저작권법 제136조, 137조, 138조에 의한 권리의침해죄, 부정발행등의죄, 출처명시위반의죄 등에 해당될 수 있습니다.<br /><br />
				우리 부 사이트에서 제공하는 자료로 수익을 얻거나 이에 상응하는 혜택을 누리고자 하는 경우에는 우리 부와 사전에 별도의 협의를 하거나 허락을 얻어야 하며, 협의 또는 허락에 의한 경우에도 출처가 해양수산부임을 반드시 명시하여야 합니다.<br /><br />
				우리 사이트의 자료를 적법한 절차에 따라 다른 인터넷 사이트에 게재하는 경우에도 단순한 오류 정정 이외에 내용의 무단변경을 금지하며, 이를 위반할 때에는 형사 처벌을 받을 수 있습니다. 또한, 다른 인터넷 사이트에서 우리 부 사이트로 링크하는 경우에도 링크 사실을 우리 부에 반드시 통지하여야 합니다.
			</p>
			<a href="http://www.law.go.kr/lsInfoP.do?lsiSeq=213873&efYd=20200805&ancYnChk=0#0000" target="_blank">저작권법 보기</a>
		</section>
	</div>

<%@include file="../layout/tail.jsp"%>