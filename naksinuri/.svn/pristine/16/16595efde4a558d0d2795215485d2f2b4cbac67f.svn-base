<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>
	
<form:form commandName="eduCurriculumVO" id="listForm" name="listForm" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value=""/>

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
			<div class="page-header-actions">
	        	<a class="append-rows btn btn-primary btn-outline clk_add_data" href="javascript:void(0)" 
	        	data-crs-sn="" 
	        	data-type-str=""
	        	data-linkurl="/eduadm/curriculum/write.do">
	        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
	        		<span class="hidden-sm-down">새로운 교육과정 등록하기</span>
	        	</a> 
	        </div>
		</div>
	
		<div class="page-content container-fluid">
			<div class="row">
				<div class="col-xl-12">
					<div role="alert" class="alert dark alert-warning alert-dismissible">
						<button aria-label="Close" data-dismiss="alert" class="close" type="button">
							<span aria-hidden="true">×</span>
						</button>
	   					<h4><i class="icon wb-bell" aria-hidden="true"></i> Notice</h4>
						<p>교육과정이 최종 <b>승인</b>이 되면 모집기간에 신청 접수가 시작됩니다.<br>수강인원이 1명 이상인 경우 교과목을 추가하거나 수정할 수 없으므로 승인전에 필히 교과목 상태를 확인해주세요.<br>교육과정을 <b>승인대기</b> 상태로 변경 후 수강인원을 모두 삭제하시면 교과목을 다시 설정 하실 수 있습니다.</p>
					</div>
					<!-- Panel Editing Rows -->
					<div class="panel">
	          			<div class="panel-body">
	          		
	          			</div>
	        		</div>
	      		</div>
			</div>
		</div>
	</div>
	<!-- End Page -->
	
</form:form>

<%@ include file="../tail.jsp" %>
