<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<c:forEach var="item" items="${list_category}">
	<option value="${item.CD_ID }" data-cd-nm="${item.CD_NM}" data-cd-master-id="${item.CD_MASTER_ID}" data-cd-ord-no="${item.CD_ORD_NO}"><c:out value="${item.CD_NM }"></c:out></option>
</c:forEach>	
	

