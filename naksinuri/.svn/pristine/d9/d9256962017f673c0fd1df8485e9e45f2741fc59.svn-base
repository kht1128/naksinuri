<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
/** 닫기버튼 숨김 */
.mfp-close-btn-in .mfp-close{display:none;}
.mfp-container{padding:0px;}
.se2img{width:100%;}
a {display: block;}
</style>
<c:if test="${info.PP_TYPE eq 'popup'}">

	<div class="popupArea" style="top:${info.PP_TOP};left:${info.PP_LEFT};">
		<ul>
			<li>
				<a <c:if test="${not empty info.PP_LINK_URL}">
					href="${info.PP_LINK_URL}" target="_blank"
				</c:if> 
				<c:if test="${empty info.PP_LINK_URL}">
					href="#;"
				</c:if> >
					<%--
					<jsp:scriptlet>
					    pageContext.setAttribute("cr", "\r");
					    pageContext.setAttribute("lf", "\n");
					    pageContext.setAttribute("crlf", "\r\n");
					</jsp:scriptlet> 
					${fn:replace(info.PP_CONT , crlf, '</br>' )}
					 --%>
					<img src="/cmm/fms/getImage.do?atchFileId=${info.PP_FILE}&fileSn=0" /><br/>
					<img src="/cmm/fms/getImage.do?atchFileId=${info.PP_FILE}&fileSn=1" />
				</a>
				<c:if test="${not empty info.PP_FILE2}">
					<c:set var="linkUrl" value='href="javascript:void(0)"'/>
					<c:if test="${not empty info.PP_LINK_URL2}">
						<c:set var="linkUrl" value='href="${info.PP_LINK_URL2}" target="${info.PP_LINK_TARGET}"'/>
					</c:if>
				
					<a ${linkUrl}>
						<img src="/cmm/fms/getImage.do?atchFileId=${info.PP_FILE2}&fileSn=0"/><br>
						<img src="/cmm/fms/getImage.do?atchFileId=${info.PP_FILE2}&fileSn=1"/>
					</a>
				</c:if>
			</li>
		</ul>
		<div class="popupClosebtnArea">
			<input type="checkbox" class="popupAreaClose" /><label for="popupClose1"><span></span>오늘 하루 열지 않기</label> 
			<a href="#;" class="popupAreaCloseBtn"><i class="fa fa-times"></i></a>		
		</div>
	</div>	

</c:if>
<c:if test="${info.PP_TYPE eq 'banner_top'}">
	
	<div class="topBanner">
		<a <c:if test="${not empty info.PP_LINK_URL}">
				href="${info.PP_LINK_URL}" target="_blank"
			</c:if> 
			<c:if test="${empty info.PP_LINK_URL}">
				href="#;"
			</c:if> class="goLink">
			<%--
			<jsp:scriptlet>
			    pageContext.setAttribute("cr", "\r");
			    pageContext.setAttribute("lf", "\n");
			    pageContext.setAttribute("crlf", "\r\n");
			</jsp:scriptlet> 
			${fn:replace(info.PP_CONT , crlf, '</br>' )}
			--%>
			<img src="/cmm/fms/getImage.do?atchFileId=${info.PP_FILE}&fileSn=0" /><br/>
			<img src="/cmm/fms/getImage.do?atchFileId=${info.PP_FILE}&fileSn=1" />
		</a>
		<a href="#;" class="topBannerCloseBtn" ><img src="/common/img/topBanner_close.png" alt="닫기버튼" /></a>
	</div>

</c:if>
<c:if test="${info.PP_TYPE eq 'banner_center'}">
	
	<div class="popupArea vbanner">
		<ul>
			<li><a <c:if test="${not empty info.PP_LINK_URL}">
				href="${info.PP_LINK_URL}" target="_blank"
			</c:if> 
			<c:if test="${empty info.PP_LINK_URL}">
				href="#;"
			</c:if> >
				<%--
				<jsp:scriptlet>
				    pageContext.setAttribute("cr", "\r");
				    pageContext.setAttribute("lf", "\n");
				    pageContext.setAttribute("crlf", "\r\n");
				</jsp:scriptlet> 
				${fn:replace(info.PP_CONT , crlf, '</br>' )}
				--%>
				<img src="/cmm/fms/getImage.do?atchFileId=${info.PP_FILE}&fileSn=0" /><br/>
				<img src="/cmm/fms/getImage.do?atchFileId=${info.PP_FILE}&fileSn=1" />
			</a></li>
		</ul>
	</div>	
	
</c:if>
