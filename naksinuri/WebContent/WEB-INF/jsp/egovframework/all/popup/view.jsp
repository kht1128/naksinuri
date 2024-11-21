<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="egovframework.utils.PublicUtils" %>
<%
//현재 페이지 정보 가져오기
String cururl = request.getRequestURI().toString();
PublicUtils mPublicUtils = new PublicUtils();
//boolean isMobile = cururl.contains("/mobile/");
boolean isMobile = false;
if(mPublicUtils.isMobileDevice(request)){
	isMobile = true;
}
String popupAreaClassName = "";
String BannerFileSN = "";
if(isMobile) {
	popupAreaClassName = "popupAreaM";
	BannerFileSN = "1";
} else {
	popupAreaClassName = "popupArea";
	BannerFileSN = "0";
	%>
	<style>
	#popupArea_10 {
		margin-left: -620px !important;
	}
	#popupArea_11 {
	   margin-left: 10px !important;
	}
	</style>
	<%
}
%>
<!-- // 팝업 영역 //-->
<c:set var="isMobile" value="<%=isMobile%>"/>
<c:set var="popupAreaClassName" value="<%=popupAreaClassName%>"/>
<c:if test="${not empty list_popup}">
	<c:forEach var="item" varStatus="status" items="${list_popup}">
		<c:set var="pp_link_url" value="${item.PP_LINK_URL}"/>
		<c:if test="${empty item.PP_LINK_URL}"><c:set var="pp_link_url" value="#;"/></c:if>		
		<div class="${popupAreaClassName}" id="popupArea_${item.PP_SN}" style="<c:if test="${isMobile}">top:20px;left:50%;display:none;z-index:${item.PP_RANK};</c:if><c:if test="${!isMobile}">top:${item.PP_TOP};left:${item.PP_LEFT};display:none;</c:if>">
			<div class="popupClosebtnArea">
				<input type="checkbox" class="popupAreaClose" id="popupClose_${item.PP_SN}" /><label for="popupClose_${item.PP_SN}"><span tabindex="1"></span>오늘 하루 열지 않기</label> 
				<a href="#;" class="popupAreaCloseBtn popupBtn" data-idnum="${item.PP_SN}" tabindex="2"><i class="fa fa-times" style="font-size: 15px;">닫기</i></a>		
			</div>
			<ul>
				<li>
					<a class="popupAreaContent" 
						<c:if test="${not empty item.PP_LINK_URL}">
							href="${item.PP_LINK_URL}" target="${item.PP_LINK_TARGET}"
						</c:if> 
						<c:if test="${empty item.PP_LINK_URL}">
							href="#;"
						</c:if>
						tabindex="3"><span class="skip">link</span>
						<img src="/cmm/fms/getImage.do?atchFileId=${item.PP_FILE}&fileSn=${BannerFileSN}" alt=""/>
					</a>
					
					<c:if test="${not empty item.PP_FILE2}">
						<c:set var="linkUrl" value='href="javascript:void(0)"'/>
						<c:if test="${not empty item.PP_LINK_URL2}">
							<c:set var="linkUrl" value='href="${item.PP_LINK_URL2}" target="${item.PP_LINK_TARGET}"'/>
						</c:if>
					
						<a ${linkUrl} class="popupAreaContent">
							<span class="skip">link</span>
							<img src="/cmm/fms/getImage.do?atchFileId=${item.PP_FILE2}&fileSn=${BannerFileSN}" alt=""/>
						</a>
					</c:if>
				</li>
			</ul>
		</div>	
		<script>
		$('document').ready(function(){
			var tidnum = "${item.PP_SN}";
			if(getCookie("notToday_"+tidnum)!="Y"){
				$("#popupArea_"+tidnum).show();
			}else{
				$("#popupArea_"+tidnum).hide();
			}
			
			$(".popupArea").draggable();
			
			$('#popupArea_'+tidnum).on('keypress', function (event) {
		        if (event.which === 13) {
		        	var isChecked = $('#popupClose_'+tidnum).is(':checked');
					if(isChecked){
						$('#popupClose_'+tidnum).prop('checked', false);
					} else {
						$('#popupClose_'+tidnum).prop("checked", true);
					}
		        }
		    });
			
		});
		</script>
	</c:forEach>
	<script>
	$(".popupAreaCloseBtn").click(function() {
		var tidnum = $(this).attr('data-idnum');
		if($('#popupClose_'+tidnum).prop("checked")){
			setCookie('notToday_'+tidnum,'Y', 1);
			$("#popupArea_"+tidnum).hide('fade');
		}else{
			$("#popupArea_"+tidnum).hide('fade');
		}
	});
	
	//오늘하루 열지않기 누르면 팝업창 꺼짐
	/* $(".popupAreaClose").click(function() {
		var tidnum = $(this).siblings('a').attr('data-idnum');
		setCookie('notToday_'+tidnum,'Y', 1);
		$("#popupArea_"+tidnum).hide('fade');
		
	}); */
	
	
	function setCookie(name, value, expiredays) {
		var today = new Date();
	    today.setDate(today.getDate() + expiredays);
	    document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';'
	}
	function getCookie(name) { 
		var cName = name + "="; 
		var x = 0; 
		while ( x <= document.cookie.length ) 
		{ 
		    var y = (x+cName.length); 
		    if ( document.cookie.substring( x, y ) == cName ) 
		    { 
		        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 
		            endOfCookie = document.cookie.length;
		        return unescape( document.cookie.substring( y, endOfCookie ) ); 
		    } 
		    x = document.cookie.indexOf( " ", x ) + 1;
		    
		    if ( x == 0 ) 
		        break; 
		} 
		return ""; 
	}
	</script>	
</c:if>	
<!-- End // 팝업 영역 //-->
<!-- // 배너 영역 //-->
<c:set var="BannerFileSN" value="<%=BannerFileSN%>"/>
<c:if test="${not empty list_banner}">
	<c:forEach var="item" varStatus="status" items="${list_banner}">
		<c:set var="pp_link_url" value="${item.PP_LINK_URL}"/>
		<c:if test="${empty item.PP_LINK_URL}"><c:set var="pp_link_url" value="#;"/></c:if>		

		<div class="topBanner" id="topBannerArea_${item.PP_SN}" style="display:none;">
			<a <c:if test="${not empty item.PP_LINK_URL}">
					href="${item.PP_LINK_URL}" target="${item.PP_LINK_TARGET}"
				</c:if> 
				<c:if test="${empty item.PP_LINK_URL}">
					href="#;"
				</c:if> class="goLink">
				<%--
				<jsp:scriptlet>
				    pageContext.setAttribute("cr", "\r");
				    pageContext.setAttribute("lf", "\n");
				    pageContext.setAttribute("crlf", "\r\n");
				</jsp:scriptlet> 
				${fn:replace(item.PP_CONT , crlf, '</br>' )}
				--%>
				<img src="/cmm/fms/getImage.do?atchFileId=${item.PP_FILE}&fileSn=${BannerFileSN}" style="width:100%;" alt="${item.PP_CONT}"/>
			</a>
			<div class="topCloseBtn">
				<input type="checkbox" class="topBannerClose" id="topBannerClose_${item.PP_SN}" /><label for="topBannerClose_${item.PP_SN}"><span></span>오늘 하루 열지 않기</label>
				<a href="#;" class="topBannerCloseBtn" data-idnum="${item.PP_SN}"><img src="/naksinuri_original/common_main/img/top_ico_close.png" alt="닫기버튼" /></a>
			</div>
		</div>
		<script>
		$('document').ready(function(){
			var tidnum = "${item.PP_SN}";
			if(getCookieBanner("notToday_"+tidnum)!="Y"){
				$("#topBannerArea_"+tidnum).show();
			}else{
				$("#topBannerArea_"+tidnum).hide();
			}
		});
		</script>
	</c:forEach>
	<script>
	$(".topBannerCloseBtn").click(function() {
		var tidnum = $(this).attr('data-idnum');
		//$("#topBannerArea_"+tidnum).slideUp();
		if($('#topBannerClose_'+tidnum).prop("checked")){
			setCookieBanner('notToday_'+tidnum,'Y', 1);
			$("#topBannerArea_"+tidnum).slideUp();
		}else{
			$("#topBannerArea_"+tidnum).slideUp();
		}
	});
	function setCookieBanner(name, value, expiredays) {
		var today = new Date();
	    today.setDate(today.getDate() + expiredays);
	    document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';'
	}
	function getCookieBanner(name) { 
		var cName = name + "="; 
		var x = 0; 
		while ( x <= document.cookie.length ) 
		{ 
		    var y = (x+cName.length); 
		    if ( document.cookie.substring( x, y ) == cName ) 
		    { 
		        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 
		            endOfCookie = document.cookie.length;
		        return unescape( document.cookie.substring( y, endOfCookie ) ); 
		    } 
		    x = document.cookie.indexOf( " ", x ) + 1;
		    
		    if ( x == 0 ) 
		        break; 
		} 
		return ""; 
	}
	
	</script>	
</c:if>
<!-- End // 배너 영역 //-->