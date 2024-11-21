<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<%
//현재 페이지 정보 가져오기
String urlpath1 = "";
String urlpath2 = "";
String urlpath3 = "";
String urlpath4 = "";
if(request!=null && request.getRequestURI()!=null) {
	String cururl = request.getRequestURI().toString();
	String[] parseUrl = cururl.split("/");
	if(parseUrl.length == 8) {	
		urlpath1 = parseUrl[4];
		urlpath2 = parseUrl[5];
		urlpath3 = parseUrl[6];
		urlpath4 = parseUrl[7].replace(".jsp", "");
	} else if(parseUrl.length >= 9) {	
		urlpath1 = parseUrl[4];
		urlpath2 = parseUrl[6];
		urlpath3 = parseUrl[7];
		urlpath4 = parseUrl[8].replace(".jsp", "");
	}
}
//파라미터 정보 
String selectedId = request.getParameter("selectedId");
if(selectedId!=null && selectedId.trim().length()==0) {
	selectedId = selectedId.replaceAll("<", "").replaceAll(">", "");
}
%>
<c:set var="PL1" value="<%=urlpath1%>"/>
<c:set var="PL2" value="<%=urlpath2%>"/>
<c:set var="PL3" value="<%=urlpath3%>"/>
<c:set var="PL4" value="<%=urlpath4%>"/>
<c:set var="SID" value="<%=selectedId%>"/>
<a href="#;" class="tooltipCustom btnSharelink" title="게시물 공유 URL주소"
	data-pl1="${PL1}"
	data-pl2="${PL2}"
	data-pl3="${PL3}"
	data-pl4="${PL4}"
	data-sid="${SID}">
	<i class="fa fa-link"></i>
</a>&nbsp;<span id="sharelinkurlprint" class="red-600"></span>
<script>
$('.btnSharelink').click(function() {
	var sid = $(this).attr('data-sid');
	var pl1 = $(this).attr('data-pl1');
	var pl2 = $(this).attr('data-pl2');
	var pl3 = $(this).attr('data-pl3');
	var pl4 = $(this).attr('data-pl4');
	$.ajax({
		type:"POST",
		url :"/share/link/copy.do",
		data:{
			LINK_PL1:pl1,
			LINK_PL2:pl2,
			LINK_PL3:pl3,
			LINK_PL4:pl4,
			LINK_SID:sid,
		},
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {	
			if(data.error == '1') {
				alert(data.msg);
			} else {
				//$('#sharelinkurlprint').html(data.sharelink);
				try { 
					var str = data.sharelink;
					if( window.clipboardData && clipboardData.setData ) {
					    clipboardData.setData("Text", str);
					    alert("복사되었습니다.");
					} else {
						prompt("Ctrl+C를 눌러 복사하세요.", str);
					}
				} catch (err) { 
					alert('이 브라우저는 지원하지 않습니다.'); 
				}
			}			
		},
		beforeSend : function() {
			//console.log('before!');
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);			
		}
	});
});
</script>
