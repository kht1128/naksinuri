<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="sosig"/>
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="event" />

<%@include file="../layout/head.jsp"%>
		<!--  댓글에서 뷰로 들고 갈 값 -->
	


	
<script type="text/javascript">

$(document).ready(function(){
	var evn_no = $("#evn_no").val();
	if(confirm("정말 삭제하시겠습니까?") == true){
		$("#ecom").attr("action","/sosig/event/view.do");	
		}else{
		alert('비밀번호를 확인하세요');
		return false;
	}

});




		
</script>