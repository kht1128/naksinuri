<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:scriptlet>
	pageContext.setAttribute("cr", "\r");
	pageContext.setAttribute("lf", "\n");
	pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>

<%@ include file="../head.jsp" %> 
<%@ include file="../left_menu.jsp" %>


<form:form commandName="DoctorRequestVO" method="post" action="/seadm/homestay/modifyRequestAction.do" onsubmit="return frm_submit()" > 
	<input type="hidden" name="hs_sn" id="hs_sn" value="${homestay_request_info.hssn}" />


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
		<div class="page-header-actions"></div>
	</div>
      
	<div class="page-content container-fluid">	
	<!-- page-content -->
	
		<div class="panel">
			<div class="panel-body">
				<div class="form-group row">
					<h4>운영자 정보</h4>	
				</div>	
				
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >운영자이름</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control " value="${homestay_request_info.hsnm}" disabled>
					</div>
				</div>	
				
			</div>
		</div>
		<div class="panel">
			<div class="panel-body">
				<div class="form-group row">
					<h4>신청자 정보</h4>	
				</div>	
				
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >이름</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control " value="${homestay_request_info.hsmbrnm}" disabled>
					</div>
				</div>				
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >연락처</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control " value="${homestay_request_info.hshp}" disabled>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >신청기간</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control " value="${homestay_request_info.hsstartdt} ~ ${homestay_request_info.hsenddt}" disabled>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >신청인원</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control " value="${homestay_request_info.hspersoncnt}" disabled>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >요청사항</label>
		           	<div class="col-md-10">
		           		<textarea class="form-control" rows="5" disabled>${homestay_request_info.hsintext}</textarea>		            	
					</div>
				</div>
				
				<div class="form-group row">
		        	<label class="col-md-2 form-control-label" for="REG_DT">등록일</label>
		           	<div class="col-md-10">
						<input type="text" class="form-control" placeholder="" autocomplete="off" value="${homestay_request_info.hsregdt}" disabled>
					</div>
				</div>
				
			</div>
		</div>
		<div class="panel">
			<div class="panel-body">
				<div class="form-group row">
					<h4>처리상태</h4>	
				</div>	
				
				<div class="form-group row">
					<label class="col-md-2 form-control-label">답변</label>
					<div class="col-md-10">
						<textarea class="form-control" id="hs_re_text" name="hs_re_text" rows="10" placeholder="답변을 입력해주세요.">${homestay_request_info.hsretext}</textarea>
		 			</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" for="UPD_DT">답변일</label>
					<div class="col-md-10">
						<input type="text" class="form-control" placeholder="" autocomplete="off" value="${homestay_request_info.hsprocessdt}" disabled>
		   			</div>
				</div>	
								
				<div class="text-center">
					<button class="append-rows btn btn-warning btn-outline" type="button" href="#" onclick="listact()" >
		        		<i class="site-menu-icon fas fa-bars" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">목록</span>
		        	</button>
		        	&nbsp;
		  			<button class="append-rows btn btn-primary btn-outline" type="submit"  >
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">수정</span>
		        	</button>
		        	&nbsp;
		  			<button class="append-rows btn btn-danger btn-outline" type="button" href="#" onclick="delact(${homestay_request_info.hssn})" >
		        		<i class="site-menu-icon fas fa-minus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">삭제</span>
		        	</button>
				</div>
		        
			</div>
		</div>		
	<!-- page-content -->
	</div>
</div>
<!-- End Page -->
    
</form:form>

	<script>
	$(function(){
		$(".selec2_manual").select2({
			"language": {
		       "noResults": function(){
		           return "조회 된 항목이 없습니다.";
		       }
		   	},
		    escapeMarkup: function (markup) {
		        return markup;
		    }
		});
		$('.selectpicker_manual').selectpicker();
	});
	function listact(obj) {
		location.href="/seadm/homestay/rqlist.do";
	}
	function delact(obj) {
		if(confirm('정말 삭제하시겠습니까?')) {
			location.href="/seadm/homestay/delRequestAction.do?sn="+obj;		
		}
	}
	function frm_submit() {
		$("#frm").submit();
		return true;
		
	}	
	</script>
	

<%@ include file="../tail.jsp" %>
      