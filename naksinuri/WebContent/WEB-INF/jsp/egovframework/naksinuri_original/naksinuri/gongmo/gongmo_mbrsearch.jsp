<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info" />
<c:set var="depthName" value="gongmo" />
<c:set var="pageName" value="search" />

<%@include file="../layout/head.jsp"%>


<div id="customerSound" class="content respon3">

		<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
				<li><a href="/gongmo/gongmo/check.do">공모전 접수/신청</a></li>
				<li class="on"><a href="#;" >접수 확인</a></li>
			</ul>
		</div>
	
	<section id="writeForm" class="write_box">
	
		<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	  	<h2>공모전 선택</h2>
		<div class="owner_box">
			<div id="owner_box">
			<dl>
				<dd>
					<select class="write_input wp100" onchange="change_gongmo(this);" name="bo_sn">		
						<option value="">참가하고자 하는 공모전을 선택해주세요.</option>
						<c:forEach var="item2" items="${list}">
							<option value="${item2.bo_sn}" <c:if test="${info ne null and item2.bo_sn eq info.bo_sn}">selected</c:if> >${item2.bo_subject}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			</div>				
		</div>				
		<!-- // 공모전 선택시 : start // -->			
		<c:if test="${info ne null}">
					
			<div class="agree_text">
				공모전 접수시 등록한 이름/연락처/암호를 입력하면 조회가 가능합니다.</br></br>
			</div>
			<dl>
				<dt><span class="colorRed">*</span> 이름</dt>
				<dd><input type="text" size="50" class="write_input" placeholder="이름을 입력해주세요." id="mbr_name" name="mbr_name" autocomplete="off" /></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 연락처</dt>
				<dd><input type="text" size="50" class="write_input" placeholder="전화번호를 입력해주세요. (ex:010-1234-1234)" id="mbr_hp" name="mbr_hp" autocomplete="off" /></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 암호</dt>
				<dd>
					<input type="password" size="50" class="write_input" placeholder="암호를 입력해주세요." id="mbr_pwd" name="mbr_pwd" autocomplete="off"/>
					<span class="txt_red">
					<br>* 비밀번호를 분실하신 분은 운영사무국(010-7578-2297)으로 문의해주십시요.
					</span>	
				</dd>
			</dl>
			<div id="btnArea" class="noupline">
				<a href="#;" class="btn_blue" onclick="search_gongmo()">조회하기</a>
			</div>
			
	    </c:if>
		</form:form>  	

	</section>
	
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

function search_gongmo(){
	
		if(!$.trim($('#mbr_name').val())){
			alert("이름을 입력해주세요");
			return false;
		}		
		if(!$.trim($('#mbr_hp').val())){
			alert("연락처를 입력해주세요");
			return false;
		}		
		if(!$.trim($('#mbr_pwd').val())){
			alert("암호를 입력해주세요");
			return false;
		}		
		var form = document.getElementById('listform');
		form.action = "/gongmo/gongmo/mbrconfirm.do";
		form.submit();
}

//공모전 선택
function change_gongmo(obj) {
	$("#listform").attr("action", "/gongmo/gongmo/search.do");
	$("#listform").submit();
}

</script>
<%@include file="../layout/tail.jsp"%>