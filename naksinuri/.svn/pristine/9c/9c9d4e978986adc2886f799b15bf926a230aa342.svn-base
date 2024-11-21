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
<form method="post" name="codel" id="codel">

	<input type="hidden" id="bo_sn" name="bo_sn" value="${bo_sn}">
	<input type="hidden" id="table" name="table" value="${table}">
	<input type="hidden" id="column" name="column" value="${column}">
	<input type="hidden" id="fail" name="fail"	value="${fail}">
	
</form>
<script type="text/javascript">
	$(document).ready(function(){
		if($('#fail').val()==1){
			alert("비밀번호가 일치하지 않습니다.");
		}else{
			alert("삭제되었습니다.");
		}
		var nak_id = $("#nak_id").val();
		var table = $("#table").val();
		var column = $("#column").val();
		$("#codel").attr("action","/"+column+"/"+table+"/m/view.do");
		$("#codel").submit();
});
</script>
</html>