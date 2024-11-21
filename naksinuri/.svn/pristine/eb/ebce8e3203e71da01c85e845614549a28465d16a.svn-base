<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${not empty info}">
	<c:set var="dir" value="${fn:replace(info.fileStreCours,'C:/programming/sources/spring_project/sealife/data/filelist','')}" />
	<c:set var="dir" value="${fn:replace(dir,'/data/sealife/filelist','')}" />
	<c:set var="linkurl" value="/cmwebfiles${dir}/${info.streFileNm}" />
	<c:choose>
		<c:when test="${info.fileExtsn eq 'pdf'}">
			<%--
			<c:out value="/cmm/fms/getImage.do?atchFileId=${info.atchFileId}&fileSn=${info.fileSn}" />
			어떻게 해야 할까?
			 --%>
			<c:out value="${linkurl}" />
		</c:when>
		<c:otherwise>
			<c:out value="${linkurl}" />
		</c:otherwise>
	</c:choose>
</c:if>
<%-- 사용법
<c:import url="/cmm/fms/selectFileUrlForPdf.do" >
	<c:param name="atchFileId" value="${item.bdfile}" />
	<c:param name="fileSn" value="1" />
</c:import>
 --%>