<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
${html_header}
<c:choose>
	<c:when test="${not empty info.CRTF_FILE_SN}">
		<c:if test="${empty info.CRTF_FILE_SN}">
		<p><b>정상적인 접근이 아니거나 발급된 이수증가 없습니다.</b></p>
		</c:if>
		<c:if test="${not empty info.CRTF_FILE_SN}">
		<img src="/cmm/fms/getImage.do?atchFileId=${info.CRTF_FILE_SN}&fileSn=0" style="width:100%;"/>
		<script>
		window.onload = function() {
			window.opener.directReload();
			window.print();
		}
		</script>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${empty info.CRTF_HTML_DATA}">
		<p><b>정상적인 접근이 아니거나 발급된 이수증가 없습니다.</b></p>
		</c:if>
		<c:if test="${not empty info.CRTF_HTML_DATA}">
		${info.CRTF_HTML_DATA}
		<script>
		window.onload = function() {
			window.opener.directReload();
			window.print();
		}
		</script>
		</c:if>
	</c:otherwise>
</c:choose>
${html_footer}