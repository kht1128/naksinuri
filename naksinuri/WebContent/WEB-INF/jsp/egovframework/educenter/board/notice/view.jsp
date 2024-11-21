<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../naksinuri_original/naksinuri/layout/newHead.jsp"%>

<%
String url = request.getHeader("REFERER"); 
String lastUrl = url.substring(url.lastIndexOf("/"));
%>
<c:set var="url" value="<%=lastUrl %>"/>

<!-- 교육센터 > 커뮤니티 > 공지사항 > 상세보기 { -->
<div id="conferenceView" class="content respon2">
	<article id="boardView" class="floats">
		<section id="viewContent" class="wp100">
			<div  id="printthis">
				<div class="viewTit">
					<h3 class="tit">${info.BD_TITLE}</h3>
					<div class="name_date">
						<span class="name"><c:out value="${info.MBR_NCNM}"/></span>
						<span class="date"><c:out value="${info.BD_REG_DT}"/></span>
					</div>
					<ul class="eyes_heart floats">
						<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${info.BD_VIEW_CNT}</li>
						<li>
							<jsp:include page="../../../all/share/link.jsp" flush="false">
								<jsp:param name="selectedId" value="${info.BD_SN}"/>
							</jsp:include>
						</li>
					</ul><br>
					<c:if test="${not empty info.BD_FILE}">
						<div class="filelink">
							<ul class="nopm">
								<c:import url="/cmm/fms/selectUserFileInfs.do" >
								    <c:param name="param_atchFileId" value="${info.BD_FILE}" />
								</c:import>
							</ul>
						</div>
					</c:if>
				</div>		
				<div class="content">	
					<jsp:scriptlet>
					    pageContext.setAttribute("cr", "\r");
					    pageContext.setAttribute("lf", "\n");
					    pageContext.setAttribute("crlf", "\r\n");
					</jsp:scriptlet> 
					${fn:replace(info.BD_CONT , crlf, '</br>' )}
				</div>
				<div id="btnArea" class="noupline">
					<ul class="floats">
						<li class="fr">
							<c:choose>
								<c:when test="${url eq 'list.do'}"><a href="javascript:history.back()" class="btn_list btn_gray">목록보기</a></c:when>
								<c:otherwise><a href="/educenter/board/notice/list.do" class="btn_list btn_gray">목록보기</a></c:otherwise>
							</c:choose>
						</li>
					</ul>
				</div>
			</div>
		</section>
	</article>
</div>
<!-- } 교육센터 > 커뮤니티 > 공지사항 > 상세보기 -->

<%@include file="../../../naksinuri_original/naksinuri/layout/tail.jsp"%>