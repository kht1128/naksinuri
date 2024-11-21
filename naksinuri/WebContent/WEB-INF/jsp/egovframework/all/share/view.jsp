<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="urllink" value="${info.LINK_RST_URL}"/>
<form id="sharelinkForm" method="post" action="${urllink}">
	<input type="hidden" name="selectedId" 	value="${info.LINK_SID}"/>
	<input type="hidden" name="bdsn" 		value="${info.LINK_SID}"/>
	<input type="hidden" name="BD_SN" 		value="${info.LINK_SID}"/>
	<input type="hidden" name="bo_sn" 		value="${info.LINK_SID}"/>
	<input type="hidden" name="sv_id" 		value="${info.LINK_SID}"/>
</form>
<script src="/common/js/jquery-1.10.2.js"></script>
<script>
$( document ).ready(function() {
	var form = document.getElementById('sharelinkForm');
	form.submit();
});
</script>