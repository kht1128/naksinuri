<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>




<div class="reply_box">
	<h2>댓글 리스트</h2>
	<div class="reply_list">
	<c:forEach var="item" items="${comment_list}">
		<div class="reply_con">
			<span class="name">${item.co_name}</span>
			<span class="date">${fn:substring(item.co_insert_dt,0,20)}</span>
			
			<a href="#;" data-toggle="modal"  class="replyBtn" onclick="co_del()">[삭제]</a>
			<input type="hidden" id="co_sn4" value="${item.co_sn }"/>
			<p>${item.co_comment}</p>
		</div>
	</c:forEach>
	</div>
</div>

	

