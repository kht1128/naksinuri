<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../../head.jsp" %> 
<%@ include file="../../left_menu.jsp" %>

<form:form commandName="eduCategoryInsInfVO" id="viewForm" name="viewForm" method="post">
<input type="hidden" name="CAT_INS_SN" value="" />
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
					<label class="col-md-2 form-control-label" >시도</label>
		           	<div class="col-md-10">
		            	<c:forEach var="item_category" items="${list_address_cd}">
							<c:if test="${item_category.CD_ID eq info.SIDO_CD }"><input type="text" class="form-control bg-white" value="${item_category.CD_NM}" disabled></c:if>
						</c:forEach>	   
					</div>
				</div>	
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >시군구</label>
		           	<div class="col-md-10">
		            	<c:forEach var="item_category" items="${list_address_signgu_cd}">
							<c:if test="${item_category.CD_ID eq info.SIGNGU_CD }"><input type="text" class="form-control bg-white" value="${item_category.CD_NM}" disabled></c:if>
						</c:forEach>	
					</div>
				</div>	
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >교육기관명</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" value="${info.CAT_INS_NM}" disabled>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >대표자직책</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" value="${info.CAT_INS_PSTN}" disabled>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >대표자</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" value="${info.CAT_INS_CEO}" disabled>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >대표번호</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" value="${info.CAT_INS_TEL}" disabled>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >주소</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" value="${info.CAT_INS_ADDR}" disabled>
					</div>
				</div>
				<%-- 
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
		   		--%>
		   		<div class="form-group row">
					<label class="col-md-2 form-control-label">설명</label>
					<div class="col-md-10">
						<input type="text" class="form-control bg-white" value="${info.CAT_INS_DSCRP}" disabled>
					</div>
				</div>
		   		<div class="form-group row">
					<label class="col-md-2 form-control-label">로고 이미지</label>
					<div class="col-md-10">
						<c:choose>
							<c:when test="${not empty info.CAT_INS_LOGO}"><img src="${info.CAT_INS_LOGO}"/></c:when>
							<c:otherwise><span class="text-help">등록된 로고가 없습니다.</span></c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label">직인 이미지</label>
					<div class="col-md-10">
						<c:choose>
							<c:when test="${not empty info.CAT_INS_STAMP}"><img src="${info.CAT_INS_STAMP}"/></c:when>
							<c:otherwise><span class="text-help">등록된 직인이 없습니다.</span></c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label">교육장약도 이미지</label>
					<div class="col-md-10">
						<c:choose>
							<c:when test="${not empty info.CAT_INS_OTLNMAP}"><img class="w-p100" src="${info.CAT_INS_OTLNMAP}"/></c:when>
							<c:otherwise><span class="text-help">등록된 소개이미지가 없습니다.</span></c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" for="BD_ST">사용여부</label>
					<div class="col-md-10">
						<c:choose>
							<c:when test="${info.DEL_ST eq '1'}">
								<span class="text-help red-600 font-size-12">* 현재 삭제 된 게시물 (${info.UPD_DT})</span>
							</c:when>
							<c:when test="${info.USE_ST eq '0'}">
								<span class="text-help">* 사용하지 않는 게시물 (${info.UPD_DT})</span>
							</c:when>
							<c:otherwise><span class="text-help">현재 사용중</span></c:otherwise>
						</c:choose>						
					</div>
				</div>
				<div class="text-center /*float-middle*">
					<button class="append-rows btn btn-warning btn-outline" type="button" href="#" onclick="listact()" >
		        		<i class="site-menu-icon fas fa-bars" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">목록</span>
		        	</button>
		        	&nbsp;
		  			<button class="append-rows btn btn-primary btn-outline" type="button" href="#" onclick="fnModify('${info.CAT_INS_SN}')" >
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">수정</span>
		        	</button>
		        	&nbsp;
		  			<button class="append-rows btn btn-danger btn-outline" type="button" href="#" onclick="fnDelete('${info.CAT_INS_SN}','${info.DEL_ST}')" >
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
	location.href="/eduadm/category/academy/list.do";
}
function fnModify(cat_ins_sn) {
	document.viewForm.CAT_INS_SN.value = cat_ins_sn;
    document.viewForm.action = "/eduadm/category/academy/modify.do";
    document.viewForm.submit();
}
function fnDelete(cat_ins_sn,del_st) {
	var data_del_st = del_st;
	var alert_message = "";
	if(data_del_st == '1') {
		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
	} else {
		alert_message = "삭제 하시겠습니까?";
	}
	var data_crs_sn = $(this).attr('data-crs-sn');
	alertify.confirm(alert_message, function(){ 
		document.viewForm.CAT_INS_SN.value = cat_ins_sn;
	    document.viewForm.action = "/eduadm/category/academy/delete_act.do";
	    document.viewForm.submit();
	});
}	
</script>

<%@ include file="../../tail.jsp" %>
