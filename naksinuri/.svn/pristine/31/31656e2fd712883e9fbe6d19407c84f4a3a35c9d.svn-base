<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="depthName" value="${column}" />
<c:set var="pageName" value="${table}" />

<form:form commandName="imform" id="imform" method="post">
<input type="hidden" id="spbo_sn" value="${info.bo_sn}" />
<input type="hidden" name="co_sn" id="co_sn"/>
<input type="hidden" id="table" name="table" value="${table}">
<input type="hidden" id="column" name="column" value="${column}">

	<c:set var="bo_sn" value="${info.bo_sn }"/>
						<c:choose>
							<c:when test="${bo_sn eq null }">
								
							</c:when>
							<c:when test="${bo_sn ne null }">
								<%@include file="./admin_reply.jsp"%>
							</c:when>
							
						</c:choose>
</form:form>

<script type="text/javascript">

function co_del(){
	var table = $("#table").val();
	var column = $("#column").val();
	var form = document.getElementById('imform');
	var bo_sn = $("#spbo_sn").val();
	var co_sn4	=$("#co_sn4").val();
	$('#bo_sn').val(bo_sn);
	$('#co_sn').val(co_sn4);
	

	if(confirm("댓글을 정말 삭제하시겠습니까") == true){
	form.action = "/admin/"+column+"/"+table+"/co_delete.do";
	form.submit();
		
	}else{
		return false;
	}
	
}
</script>
					
					
					