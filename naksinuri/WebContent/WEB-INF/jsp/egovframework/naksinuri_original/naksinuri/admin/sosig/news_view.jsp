<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>
<html lang="ko">
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="news" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="mid" id="mid" value="${info.mid}" />
<input type="hidden" name="returnUrl" value="<c:url value='/admin/${depthName}/${pageName}/board_findCorp.do'/>"/>


	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>낚시뉴스 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>							
							<tr>
								<th>글쓴이</th>
								<td><input type="text" name="writer_name" id="writer_name" class="frm_input" value="${info.writer_name}"  /></td>
							</tr>
							<tr>
								<th>뉴스 카테고리</th>
								<td>
								<select class="frm_select" id="category" name="category" value="${info.category }">
										<option value="전체" <c:if test="${info.category eq '전체'}">selected</c:if>>전체</option>
										<option value="TV연예" <c:if test="${info.category eq 'TV연예'}">selected</c:if>>TV연예</option>
										<option value="여행" <c:if test="${info.category eq '여행'}">selected</c:if>>여행</option>
										<option value="경제" <c:if test="${info.category eq '경제'}">selected</c:if>>경제</option>
										<option value="건강" <c:if test="${info.category eq '건강'}">selected</c:if>>건강</option>
										<option value="푸드" <c:if test="${info.category eq '푸드'}">selected</c:if>>푸드</option>
										<option value="서적" <c:if test="${info.category eq '서적'}">selected</c:if>>서적</option>
										<option value="테크" <c:if test="${info.category eq '테크'}">selected</c:if>>테크</option>
										<option value="문화" <c:if test="${info.category eq '문화'}">selected</c:if>>문화</option>
								  </select>
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input type="text" name="title" id="title" class="frm_input" value="${info.title}" style="WIDTH: 100%;"/></td>
							</tr>
							<tr>
								<th>url</th>
								<td><textarea name="link" id="link"  class="frm_input"  style="WIDTH: 100%">${info.link}</textarea></td>
							</tr>
				
							
						</tbody>
					</table>
	
						
				</div>
			</section>

			<div class="btn_area textcenter">
			
				<c:if test="${info.isdel eq '1' }">
					<a href="/admin/sosig/news/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				<c:if test="${info.isdel eq '0' && info.mid ne null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/admin/sosig/news/list.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
					<!-- 글생성할 때  -->
				<c:if test="${info.mid eq null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/admin/sosig/news/list.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>	
			</div>
		</div>
	</div>
</form:form>
	<!-- 하단 푸터 { -->
	<footer id="footer" class="floats">
		<div class="l_version">
			No Background Tasks <em>Version 4.4.0.5</em>
		</div>
		<div class="r_copyright">
			<b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.
		</div>
	</footer>
	<!-- } 하단 푸터 -->
</div>
</body>
</html>
<script type="text/javaScript">

    var depthName = "${depthName}";
    var pageName = "${pageName}";


    //전송버튼 클릭이벤트
	function submitContents() {
    	

    // 에디터의 내용에 대한 값 검증은 이곳에서
    if(document.getElementById("writer_name").value==''){
    	alert('작성자를 입력해주세요.');
    	return false;   	
    } 
    
    if(document.getElementById("title").value==''){
    	alert('제목 입력해주세요.');
    	return false;   	
    } 
    
    if(document.getElementById("link").value==''){
    	alert('링크를 입력해주세요.');
    	return false;   	
    } 
     try {
    	 if($('#mid').val()!=''){
				$("#imform").attr("action","/admin/sosig/news/update_data.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/sosig/news/insert_data.do");
				document.getElementById("imform").submit();				
			}
    	 
    } catch(e) {
     
    }
   
    
}






</script>
