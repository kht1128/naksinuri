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
				<span class="name">${item.eco_name}</span>
				<span class="date">${fn:substring(item.eco_insert_dt,0,20)}</span>
				
				<a href="#;" data-toggle="modal"  class="replyBtn" onclick="eco_del('${item.eco_no }')">[삭제]</a>
				<p style="font-weight:bold;color:#f00;">${item.eco_gongmo_hp}</p>
				<p>${item.eco_content}</p>
			</div>
		</c:forEach>
		</div>
	</div>

	

