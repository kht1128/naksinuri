<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

	<form:form commandName="EduCategoryVO" id="listForm" name="listForm" method="post">
	<input type="hidden" id="CAT_SN" name="CAT_SN" value=""/>
	<input type="hidden" id="CAT_DTL_SN" name="CAT_DTL_SN" value=""/>
	<input type="hidden" id="typeStr" name="typeStr" value=""/>
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
        <div class="page-header-actions">
        	<a class="append-rows btn btn-primary btn-outline clk_add_category" href="javascript:void(0)" 
        		data-cat-sn="" 
        		data-cat-dtl-sn="" 
        		data-typeStr=""
        		data-linkurl="/eduadm/category/write.do">
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">새로운 교육카테고리 추가하기</span>
        	</a> 
        </div>
      </div>
      
		<div id="exampleTransition" class="page-content container-fluid" data-plugin="animateList">
			
			<c:if test="${empty edu_catetory_1}">
			<blockquote class="blockquote custom-blockquote blockquote-warning">
     			<p class="mb-0">교육카테고리를 추가하세요.</p>
     			<footer class="blockquote-footer">
       				등록된 데이터가 없습니다.
     			</footer>
   			</blockquote>
   			</c:if>
		
			<ul class="blocks-sm-100 blocks-lg-2 blocks-xxl-3">
				<c:forEach var="cate" items="${edu_catetory_1}">
				<li>
					<div class="panel panel-bordered <c:choose><c:when test="${cate.USE_ST ne '1'}">panel-light</c:when><c:otherwise>panel-dark</c:otherwise></c:choose>">
						<div class="panel-heading">
							<h3 class="panel-title">
								<c:choose>
									<c:when test="${cate.DEL_ST eq '1'}">
										<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 카테고리입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:when>
									<c:otherwise>
										<c:if test="${cate.USE_ST ne '1'}">
											<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 카테고리 입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:if>
									</c:otherwise>											
								</c:choose>
								${cate.CAT_NM}</h3>
							<div class="panel-actions">
								<c:if test="${cate.USE_ST eq '1'}">
									<button type="button" class="btn btn-warning btn-xs clk_add_category_dtl" 
									data-cat-sn="${cate.CAT_SN}" 
									data-cat-dtl-sn="" 
									data-typeStr="dtl"
									data-linkurl="/eduadm/category/writeDtl.do">하위카테고리추가</button>
								</c:if>
								<a class="panel-action icon wb-wrench clk_mod_category" aria-hidden="true" data-toggle="tooltip" data-original-title="수정" 
								data-cat-sn="${cate.CAT_SN}" 
								data-cat-dtl-sn="" 
								data-typeStr=""
								data-linkurl="/eduadm/category/modify.do"></a>
								<a class="panel-action icon wb-trash clk_del_category" aria-hidden="true" data-toggle="tooltip" data-original-title="삭제" 
								data-cat-sn="${cate.CAT_SN}"
								data-del-st="${cate.DEL_ST}"  
								data-cat-dtl-sn="" 
								data-typeStr="" 
								data-linkurl="/eduadm/category/delete_act.do"></a>
							</div>
						</div>
						<div class="panel-body">
							<!-- //카테고리2// -->
							<table class="table table-bordered">
							<tbody>
								<c:set var="catekey">${cate.CAT_SN}</c:set>
								<c:if test="${empty edu_catetory_2[catekey]}">
									<tr><td class="text-middle text-center" colspan="2">등록 된 항목이 없습니다.</td></tr>
								</c:if>	
								<c:forEach var="cate2" items="${edu_catetory_2[catekey]}">
									<tr>
										<td class="col-10 text-middle" >
											<c:choose>
												<c:when test="${cate2.DEL_ST eq '1'}">
													<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 카테고리입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
												</c:when>
												<c:otherwise>
													<c:if test="${cate2.USE_ST ne '1'}">
														<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 카테고리 입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
													</c:if>
												</c:otherwise>											
											</c:choose>
											<c:if test="${cate2.LRNNG_GB eq 'online'}">
												<i class="site-menu-icon wb-desktop" aria-hidden="true" data-content="온라인 교육용 카테고리 입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
											${cate2.CAT_DTL_NM}</td>
										<td class="text-nowrap text-center">
											<button type="button" class="btn btn-sm btn-icon btn-flat btn-default clk_mod_category_dtl" data-toggle="tooltip" data-original-title="수정" 
											data-cat-dtl-sn="${cate2.CAT_DTL_SN}" 
											data-cat-sn="${cate.CAT_SN}" 
											data-typeStr="dtl"
											data-linkurl="/eduadm/category/modifyDtl.do">
												<i class="icon wb-wrench text-dark" aria-hidden="true"></i>
											</button>
											<button type="button" class="btn btn-sm btn-icon btn-flat btn-default clk_del_category_dtl" data-toggle="tooltip" data-original-title="삭제" 
											data-cat-dtl-sn="${cate2.CAT_DTL_SN}" 
											data-cat-sn="${cate.CAT_SN}" 
											data-del-st="${cate2.DEL_ST}"
											data-typeStr="dtl"
											data-linkurl="/eduadm/category/deleteDtl_act.do">
												<i class="icon wb-close text-dark" aria-hidden="true"></i>
											</button>
										</td>
									</tr>										
								</c:forEach>									
							</tbody>
							</table>
							<!-- //카테고리2// -->
						</div>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>

    </div>
    <!-- End Page -->
    
    <script>
    $(".clk_del_category,.clk_del_category_dtl").click(function() {    	
    	var data_cat_sn = $(this).attr('data-cat-sn');
    	var data_cat_dtl_sn = $(this).attr('data-cat-dtl-sn');
    	var data_typeStr = $(this).attr('data-typeStr');
    	var data_linkurl = $(this).attr('data-linkurl');    	
    	var data_del_st = $(this).attr('data-del-st');
    	var alert_message = "";
    	if(data_del_st == '1') {
    		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
    	} else {
    		alert_message = "삭제 하시겠습니까?";
    	}
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('listForm');
        	form.CAT_SN.value = data_cat_sn;
        	form.CAT_DTL_SN.value = data_cat_dtl_sn;
        	form.typeStr.value = data_typeStr;
        	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :data_linkurl,
    			data:$('#listForm').serialize(),
    			dataType: 'json',
    			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    			async: true,
    			success: function(data, status, xhr) {
    				if(data.errCnt > 0) {
    					alertify.alert(data.msg);
    				} else {
    					if(data.error == '1') {
    						alertify.alert(data.msg);
    					} else {	
    						document.listForm.submit();
    					}
    				}
    			},
    			complete : function() {
    				//console.log('complete!');
    				clickRequestLockStop();
    		    },
    			error: function(jqXHR, textStatus, errorThrown) {
    				//console.log('error!');
    				clickRequestLockStop();
    			}
    		});  		 
    	});    	  	
   	});
    $(".clk_add_category,.clk_mod_category,.clk_add_category_dtl,.clk_mod_category_dtl").click(function() {
    	var form = document.getElementById('listForm');
    	form.CAT_SN.value = $(this).attr('data-cat-sn');
    	form.CAT_DTL_SN.value = $(this).attr('data-cat-dtl-sn');
    	form.typeStr.value = $(this).attr('data-typeStr');
    	form.action = '';
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#listForm').serialize(),
			dataType: 'html',
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#seaAdmEduPublicModal").html(data);
				$("#seaAdmEduPublicModal").modal('show');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	});   
    </script>
    

<%@ include file="../tail.jsp" %>
