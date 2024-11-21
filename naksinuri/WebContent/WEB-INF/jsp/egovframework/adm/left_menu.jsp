<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
/* 메뉴 확장 고정 */ 
.site-menubar-unfold .site-menu-item:not(.open)>.site-menu-sub {display:block;}
.site-menubar-unfold .site-menu .site-menu-sub {display:block;}
.site-menubar-unfold .site-menu-item:not(.open)>a .site-menu-arrow {transform: rotate(90deg);}
/* 커스텀 라벨 */
.msel { font-weight: bold; color: #ff6600; }
</style>

<%
//현재 페이지 정보 가져오기
String cururl = request.getRequestURI().toString();
String pageName = cururl.substring(cururl.lastIndexOf("/") + 1, cururl.length()).replace(".jsp", "");
String[] parseUrl = cururl.split("/");
String urlpath1 = "";
String urlpath2 = "";
if(parseUrl.length == 7) {	
	urlpath1 = parseUrl[5];
	urlpath2 = parseUrl[6].replace(".jsp", "");
} else if(parseUrl.length >= 8) {	
	urlpath1 = parseUrl[6];
	urlpath2 = parseUrl[7].replace(".jsp", "");
} else {
	urlpath1 = "";
	urlpath2 = pageName;
}
%>
<c:set var="SiteSet" value="educenter"/>
<c:set var="pageMode1" value="<%=urlpath1%>"/>
<c:set var="pageMode2" value="<%=urlpath2%>"/>
<c:choose>
	<c:when test="${pageMode1 eq 'member' and pageMode2 eq 'list_lv'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="회원관리"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="권한관리"/>
		<c:set var="pageUrl" value="/adm/member/list_lv.do"/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
	</c:when>	
</c:choose>

<div class="site-menubar">
	<div class="site-menubar-body">
		<div>
			<div>
				<ul class="site-menu" data-plugin="menu">
              		<li class="site-menu-category"></li>
              		<li class="site-menu-item has-sub <c:if test="${depthNum eq '1'}">active open</c:if>">
	               		<a href="javascript:void(0)">
		                	<i class="site-menu-icon fa fa-users"></i>
							<span class="site-menu-title <c:if test="${depthNum eq '1'}">msel</c:if>">회원관리</span>
							<span class="site-menu-arrow"></span>
		                </a>
		                <ul class="site-menu-sub">
	                  		<li class="site-menu-item is-shown <c:if test="${depthNum eq '1' and pageNum eq '1'}">active</c:if>">
	                    		<a class="animsition-link" href="/adm/member/list_lv.do" >
	                    			<span class="site-menu-title <c:if test="${depthNum eq '1' and pageNum eq '1'}">msel</c:if>">권한관리</span>
	                    		</a>
	                  		</li>
	                  	</ul>
					</li>					
			 	</ul>	 
			</div>
		</div>
	</div>
</div>    
