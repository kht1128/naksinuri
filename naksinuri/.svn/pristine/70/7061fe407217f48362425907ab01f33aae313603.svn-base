<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<div id="btnArea" class="recommendBox">
	
	<div class="socialLinkBox">
		<a href="#;" onclick="shfabook(${info.mid})" class="btn_blue"><i class="fa fa-facebook" aria-hidden="true"></i></a>
		<a href="#;" onclick="shtwitter(${info.mid})" class="btn_blue"><i class="fa fa-twitter" aria-hidden="true"></i></a>
		<a href="javascript:printelem(document.getElementById('printthis').innerHTML)" class="btn_gray"><i class="fa fa-print" aria-hidden="true"></i></a>
	</div>
</div>

<script type="text/javascript">

	var href=location.href;
	href = href.replace("#;","");
	var href2 = href.split("?",1);

function shfabook(mid){
	window.open('http://www.facebook.com/sharer/sharer.php?u='+href2[0]+'?mid='+mid);
	
}

function shtwitter(mid){
	window.open('https://twitter.com/intent/tweet?text=[낚시누리]-${paName}&url='+href2[0]+'?mid='+mid);
}

conferenceView
var win=null;

function printelem(printThis){
	 win = window.open();
	    self.focus();
	    win.document.open();
	    win.document.write('<'+'html'+'><'+'head'+'><'+'style'+'>');
	    win.document.write('body, td { font-family: Verdana; font-size: 10pt;}');
	    win.document.write('<'+'/'+'style'+'><'+'/'+'head'+'><'+'body'+'>');
	    win.document.write(printThis);
	    win.document.write('<'+'/'+'body'+'><'+'/'+'html'+'>');
	    win.document.close();
	    win.print();
	    win.close();
}


</script>


