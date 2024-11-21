<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <frameset rows="50, *">
	<frame name="order" src="diploma_order.asp?chkNum=<%=num%>" frameborder="0" style="border:0;overflow:hidden;" />
	<frame name="main" src="diploma.asp?chkNum=<%=num%>" frameborder="0" style="border:0;overflow:hidden;" />
</frameset>
 --%>
 
${html_header}
<c:forEach items="${list}" varStatus="stats" var="item">
	<c:if test="${not empty item.CRTF_HTML_DATA}">
		<c:if test="${stats.index != 0}">
			<div style='page-break-before:always'></div>
		</c:if>
		${fn:trim(item.CRTF_HTML_DATA)}
	</c:if>
</c:forEach>
${html_footer}

<script>
window.onload = function() {
	//window.opener.directReload();
	window.print();
}
</script>




