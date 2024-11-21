<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%-- 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>
<script>
location.href = '/index.do';
</script>
<%-- End of 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>

<c:set var="pageMode" value="info"/>
<c:set var="depthNum" value="4"/>
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congressmbrcheck" />

<%@include file="../../layout/m/head.jsp"%>
	<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" id="bo_sn"  name="bo_sn" value=""/>
		<input type="hidden" id="mbr_name2"  name="mbr_name" value=""/>
		<input type="hidden" id="mbr_hp2"  name="mbr_hp" value=""/>
	</form:form>
 	<div style="padding:20px">
		<div class="agree_text">
			낚시대회에 신청할때 입력하신 <br/>이름/전화번호 항목으로 조회해주세요.</br></br>
		</div>
		<dl style="margin-bottom:3px;">
			<dt><span class="colorRed">*</span> 이름</dt>
			<dd><input type="text" class="write_input w100" placeholder="이름을 입력해주세요." id="mbr_name" name="mbr_name"/></dd>
		</dl>
		<dl style="margin-bottom:3px;">
			<dt><span class="colorRed">*</span> 전화번호</dt>
			<dd><input type="text" class="write_input w100" placeholder="전화번호를 입력해주세요. (ex:010-1234-1234)" id="mbr_hp" name="mbr_hp" /></dd>
		</dl>
		<div id="btnArea" class="noupline">
			<a href="#;" class="btn_blue" onclick="search_congress()">검색</a>
		</div>
	</div>


<script>

function autoHypenPhone(str){
	  str = str.replace(/[^0-9]/g, '');
	  var tmp = '';
	  if( str.length < 4){
	    return str;
	  }else if(str.length < 7){
	    tmp += str.substr(0, 3);
	    tmp += '-';
	    tmp += str.substr(3);
	    return tmp;
	  }else if(str.length < 11){
	    tmp += str.substr(0, 3);
	    tmp += '-';
	    tmp += str.substr(3, 3);
	    tmp += '-';
	    tmp += str.substr(6);
	    return tmp;
	  }else{        
	    tmp += str.substr(0, 3);
	    tmp += '-';
	    tmp += str.substr(3, 4);
	    tmp += '-';
	    tmp += str.substr(7);
	    return tmp;
	  }
	  return str;
	}
	
var cellPhone = document.getElementById('mbr_hp');
cellPhone.onkeyup = function(event){
        event = event || window.event;
        var _val = this.value.trim();
        this.value = autoHypenPhone(_val) ;
}

function search_congress(){
	
		if(!$.trim($('#mbr_name').val())){
			alert("이름을 입력해주세요");
			return false;
		}else{
			$('#mbr_name2').val($('#mbr_name').val());
		}
		
		if(!$.trim($('#mbr_hp').val())){
			alert("전화번호를 입력해주세요");
			return false;
		}else{
			$('#mbr_hp2').val($('#mbr_hp').val());
		}
		
		
		
		var form = document.getElementById('listform');
		form.action = "/sosig/congress/m/mbrsearch.do";
		form.submit();
}



</script>

<%@include file="../../layout/m/tail.jsp"%>