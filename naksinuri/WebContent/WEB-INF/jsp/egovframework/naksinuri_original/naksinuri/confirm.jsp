<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
	<meta name="viewport" content="device-width,initial-scale=1.0,minimum-scale=0,maximum-scale=10,user-scalable=yes">
	<title>낚시누리</title>
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/common.css" />

	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="https://use.fontawesome.com/e59ba62350.js"></script>
	<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
	<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
	<script src="/naksinuri_original/common_main/js/common.js""></script>
<form method="post" name="nak" id="nak">
	<input type="hidden" id="nak_id" name="nak_id" value="${test}">

</form>
<script type="text/javascript">

	$(document).ready(function(){
		
	    var uAgent = navigator.userAgent.toLowerCase();
	    
	    //< 모바일 기기 추가시 등록
	    var mobilePhones = new Array('iphone', 'ipod', 'android', 'blackberry', 'windows ce', 'nokia', 'webos', 'opera mini', 'sonyericsson', 'opera mobi', 'iemobile', 'windows phone');
	     
	    var isMobile = false;
	     
	    for( var i = 0 ; i < mobilePhones.length ; ++i )
	    {
	        if( uAgent.indexOf(mobilePhones[i]) > -1)
	        {
	            isMobile = true;
	             
	            break;
	        }
	    }
	    var uAgent = navigator.userAgent.toLowerCase();
	    
	    //< 모바일 기기 추가시 등록
	    var mobilePhones = new Array('iphone', 'ipod', 'android', 'blackberry', 'windows ce', 'nokia', 'webos', 'opera mini', 'sonyericsson', 'opera mobi', 'iemobile', 'windows phone');
	     
	    var isMobile = false;
	     
	    for( var i = 0 ; i < mobilePhones.length ; ++i )
	    {
	        if( uAgent.indexOf(mobilePhones[i]) > -1)
	        {
	            isMobile = true;
	             
	            break;
	        }
	    }
	var nak_id = $("#nak_id").val();
	if(confirm("등록된 정보를 확인하시겠습니까?")==true){
		$("#nak").attr("action","/info/fishjob/view.do");
		$("#nak").submit();
	}else{
		location.href="/info/login.do";
	
	}
});
</script>
</html>