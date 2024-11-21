<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<style>
/* 메뉴 확장 고정 
.site-menubar-unfold .site-menu-item:not(.open)>.site-menu-sub {display:block;}
.site-menubar-unfold .site-menu .site-menu-sub {display:block;}
.site-menubar-unfold .site-menu-item:not(.open)>a .site-menu-arrow {transform: rotate(90deg);}
*/ 
/* 커스텀 라벨 */
.msel { font-weight: bold; color: #ff6600; }
</style>
<%
//현재 페이지 정보 가져오기
String cururl = request.getRequestURI().toString();
String pageName = cururl.substring(cururl.lastIndexOf("/") + 1, cururl.length()).replace(".jsp", "");
String[] parseUrl = cururl.split("/");
String urlpath0 = "";
String urlpath1 = "";
String urlpath2 = "";
String urlpath3 = "";
if(parseUrl.length == 7) {	
	urlpath0 = parseUrl[4];
	urlpath1 = parseUrl[5];
	urlpath2 = "";
	urlpath3 = parseUrl[6].replace(".jsp", "");
} else if(parseUrl.length >= 8) {	
	urlpath0 = parseUrl[4];
	urlpath1 = parseUrl[5];
	urlpath2 = parseUrl[6];
	urlpath3 = parseUrl[7].replace(".jsp", "");
} else {
	urlpath0 = parseUrl[4];
	urlpath1 = "";
	urlpath2 = "";
	urlpath3 = pageName;
}
%>
<c:set var="urlcontroller" value="<%=urlpath0%>"/>
<c:set var="urlmethodname" value="<%=urlpath1%>"/>
<c:set var="urlmethodsubname" value="<%=urlpath2%>"/>
<c:set var="urlpagename" value="<%=urlpath3%>"/>
<c:set var="addWebLink" value="${addWebLink}"/>
<c:set var="isOpenWindowPage" value="false"/>
<c:choose>
	
	<c:when test="${urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'index'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthName" value="CTI"/>
		<c:set var="pageNum" value="1"/>
		<c:set var="pageName" value="CTI"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
		<c:set var="isOpenWindowPage" value="true"/>
	</c:when>
	<c:when test="${urlmethodname eq 'analysis' and urlmethodsubname eq '' and urlpagename eq 'index'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthName" value="CTI"/>
		<c:set var="pageNum" value="2"/>
		<c:set var="pageName" value="통계"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
		<c:set var="isOpenWindowPage" value="false"/>
	</c:when>
	<c:when test="${urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'list'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthName" value="CTI"/>
		<c:set var="pageNum" value="3"/>
		<c:set var="pageName" value="로그"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
		<c:set var="isOpenWindowPage" value="false"/>
	</c:when>
	<c:when test="${urlmethodname eq 'main' and urlmethodsubname eq '' and urlpagename eq 'category_manager'}">
		<c:set var="depthNum" value="4"/>
		<c:set var="depthName" value="CTI"/>
		<c:set var="pageNum" value="4"/>
		<c:set var="pageName" value="카테고리 관리"/>
		<c:set var="pageUrl" value=""/>
		<c:set var="subpageNum" value=""/>
		<c:set var="subpageName" value=""/>
		<c:set var="isOpenWindowPage" value="false"/>
	</c:when>
	
</c:choose>

<div class="site-menubar">
	<div class="site-menubar-body">
		<div>
			<div>
				<ul class="site-menu" data-plugin="menu">
              		<li class="site-menu-category"></li>
              		<!-- //Menu -->
              		
              		<li class="site-menu-item">
						<a class="animsition-link" href="/cti/index.do" target="_self">
							<i class="site-menu-icon fa-headphones <c:if test="${depthNum eq '1'}">msel</c:if>" aria-hidden="true"></i>
							<span class="site-menu-title">CTI 메인화면</span>
							<div class="site-menu-label">
								<span class="label label-danger label-round"></span>
							</div>
						</a>
					</li>
					<li class="site-menu-item">
						<a class="animsition-link clk-side-menuber-item" href="javascript:void(0)" data-url="/cti/analysis/index.do">
							<i class="site-menu-icon fa-bar-chart <c:if test="${depthNum eq '2'}">msel</c:if>" aria-hidden="true"></i>
							<span class="site-menu-title">CTI 통계</span>
							<div class="site-menu-label">
								<span class="label label-danger label-round"></span>
							</div>
						</a>						
					</li>					
					<%-- <li class="site-menu-item">
						<a class="animsition-link clk-side-menuber-item" href="javascript:void(0)" data-url="/cti/log/list.do">
							<i class="site-menu-icon fa-clock-o <c:if test="${depthNum eq '3'}">msel</c:if>" aria-hidden="true"></i>
							<span class="site-menu-title">CTI 로그</span>
							<div class="site-menu-label">
								<span class="label label-danger label-round"></span>
							</div>
						</a>						
					</li> --%>
					<li class="site-menu-item">
						<a class="animsition-link clk-side-menuber-item" href="javascript:void(0)" data-url="/cti/category/manager.do">
							<i class="site-menu-icon fa-cogs <c:if test="${depthNum eq '4'}">msel</c:if>" aria-hidden="true"></i>
							<span class="site-menu-title">카테고리 관리</span>
							<div class="site-menu-label">
								<span class="label label-danger label-round"></span>
							</div>
						</a>						
					</li>
					<!-- //End Menu -->
			 	</ul>	 
			</div>
		</div>
	</div>
</div>    

<script>
var isOpenWindowPage = '${isOpenWindowPage}';
$('.clk-side-menuber-item').click(function(e){
	e.preventDefault();
	var url = $(this).attr('data-url');
	if(isOpenWindowPage == 'true') {
		window.open(url);	
	} else {
		location.href = url;	
	}
	setTimeout(function(){
		$('.site-menu-item').removeClass('active');
	},1000);
});
</script>

