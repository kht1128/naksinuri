<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../../head.jsp" %> 
<%@ include file="../../left_menu.jsp" %>

<form:form commandName="eduBoardVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="BD_SN" value="" />
</form:form>

<!-- Page -->
<div class="page">
	<div class="page-header">
		<c:choose>
			<c:when test="${not empty subpageNum}">
				<h1 class="page-title">${subpageName}</h1>
		        <ol class="breadcrumb">
		        	<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item">
		        		<a href="${pageUrl}">${pageName}</a>
		        	</li>
		        	<li class="breadcrumb-item active">${subpageName}</li>
		      	</ol>
			</c:when>
			<c:otherwise>
				<h1 class="page-title">${pageName}</h1>
		        <ol class="breadcrumb">
		       		<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item active">${pageName}</li>
		      	</ol>
			</c:otherwise>											
		</c:choose>
	</div>
	<div class="page-content container-fluid">	
	<!-- page-content -->
	
		<div class="panel">
			<div class="panel-body">
			
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >제목</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" value="${info.BD_TITLE}" disabled>
					</div>
				</div>	
				<!-- 
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >첨부파일</label>
		           	<div class="col-md-10">
			   			
			   			<div class="input-group input-group-file" data-plugin="inputGroupFile">
			     			<input type="text" class="form-control" readonly="" value="${info.BD_FILE}" id="egovComFileUploader_label"/>
			     			<span class="input-group-btn">
			       				<span class="btn btn-success btn-file">
			         				<i class="icon wb-upload" aria-hidden="true"></i>
			         				<input type="file" name="FILE_ATCH" multiple="false" value="" id="egovComFileUploader"/>
			       				</span>
			     			</span>
			   			</div>
			   			<div id="egovComFileList"></div>
			   			<input type="hidden" name="fileMaxCnt" value="5" />
			   			
					</div>
				</div>
				 -->
				<div class="form-group row">
		 			<label class="col-md-2 form-control-label" >첨부파일</label>
					<div class="col-md-10">
			   			<input type="hidden" name="fileMaxCnt" value="5" />
		   				<input type="hidden" name="returnUrl" value="/eduadm/curriculum/modify.do">
		   				<input type="hidden" name="targetFormId" value="listForm">
						<c:import url="/cmm/fms/selectFileInfs.do" >
					    	<c:param name="param_atchFileId" value="${info.BD_FILE}" />
					    </c:import>		                
					</div>
		   		</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >내용</label>
		           	<div class="col-md-10">
		            	<jsp:scriptlet>
						    pageContext.setAttribute("cr", "\r");
						    pageContext.setAttribute("lf", "\n");
						    pageContext.setAttribute("crlf", "\r\n");
						</jsp:scriptlet> 
						${fn:replace(info.BD_CONT , crlf, '</br>' )}
					</div>
				</div>
				<c:if test="${info.BD_ST eq 'N'}">
				<div class="form-group row">
					<label class="col-md-2 form-control-label" for="BD_ST">사용여부</label>
					<div class="col-md-10">
						<span class="text-help red-600 font-size-12">* 현재 삭제 된 게시물 (${info.BD_DEL_DT}) </span>
					</div>
				</div>		
				</c:if>
				<div class="text-center /*float-middle*">
					<button class="append-rows btn btn-warning btn-outline" type="button" href="#" onclick="listact()" >
		        		<i class="site-menu-icon fas fa-bars" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">목록</span>
		        	</button>
		        	&nbsp;
		  			<button class="append-rows btn btn-primary btn-outline" type="button" href="#" onclick="fnModify('${info.BD_SN}')" >
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">수정</span>
		        	</button>
		        	&nbsp;
		  			<button class="append-rows btn btn-danger btn-outline" type="button" href="#" onclick="fnDelete('${info.BD_SN}','${info.BD_ST}')" >
		        		<i class="site-menu-icon fas fa-minus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">삭제</span>
		        	</button>
				</div>
			
			</div>
		</div>
		
	<!-- End page-content -->		
	</div>
</div>

   
<script>
function listact(obj) {
	location.href="/eduadm/board/faq/list.do";
}
function fnModify(bdsn) {
	document.viewForm.BD_SN.value = bdsn;
    document.viewForm.action = "<c:url value='/eduadm/board/faq/modify.do'/>";
    document.viewForm.submit();
}
function fnDelete(bdsn,bdst) {
	var data_del_st = bdst;
	var alert_message = "";
	if(data_del_st == 'N') {
		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
	} else {
		alert_message = "삭제 하시겠습니까?";
	}
	var data_crs_sn = $(this).attr('data-crs-sn');
	alertify.confirm(alert_message, function(){ 
		document.viewForm.BD_SN.value = bdsn;
	    document.viewForm.action = "<c:url value='/eduadm/board/faq/delete_act.do'/>";
	    document.viewForm.submit();
	});
}	
</script>

<%@ include file="../../tail.jsp" %>
