<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-dialog modal-simple">
	<div class="modal-content form-horizontal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title">${TITLE}</h4>
		</div>
		
		<div class="modal-body">
			<c:if test="${empty CD_MASTER_ID}">
				<p>비정상적인 접근입니다.</p>
			</c:if>
			<c:if test="${not empty CD_MASTER_ID}">
				<form name="updateCategoryFrmModal" id="updateCategoryFrmModal" method="post" autocomplete="off" action="">
					<input type="hidden" name="CD_MASTER_ID" value="${CD_MASTER_ID}" />
					<c:forEach var="item" items="${list_category}">
						<div class="form-group row">
				 			<label class="col-md-4 form-control-label">${item.CD_NM}</label>
							<div class="col-md-8">
								<c:choose>
									<c:when test="${item.CD_ID eq 'SYS00001' or item.CD_ID eq 'SYS00002' or item.CD_ID eq 'SYS00004'}">
										<div class="input-group">
						            		<input type="text" class="form-control timepickerStr" id="${item.CD_ID}" name="${item.CD_ID}" placeholder="${item.CD_NM}" autocomplete="off" value="${item.CD_DES}" >
							           	</div>									
										<span class="text-help red-800 font-size-12">*시간은 00:00:00 유형으로 기입해주세요.</span>
									</c:when>
									<c:when test="${item.CD_ID eq 'SYS00003'}">
										<div class="input-group">
						            		<textarea class="form-control" id="${item.CD_ID}" name="${item.CD_ID}" placeholder="${item.CD_NM}" autocomplete="off" row="5">${item.CD_DES}</textarea>
							           	</div>	
										<span class="text-help red-800 font-size-12">*날짜는 ,를 앞에 붙여 0000-00-00 유형으로 추가해주세요.</span>
									</c:when>
									<c:when test="${item.CD_ID eq 'SYS00005'}">
										<div class="input-group">
						            		<input type="text" class="form-control" id="${item.CD_ID}" name="${item.CD_ID}" placeholder="${item.CD_NM}" autocomplete="off" value="${item.CD_DES}" >
							           	</div>	
										<span class="text-help red-800 font-size-12">*숫자만 입력하시면 됩니다.</span>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</div>
						</div>
	           		</c:forEach>	
					<hr>
					<div class="form-group row">
						<div class="col-lg-12">
							<div class="float-right">
								<button type="button" class="btn btn-info btn-outline mr-10 btn-submit ">현재 내용 적용하기</button>
								<button type="button" class="btn btn-default mr-10 " data-dismiss="modal">적용하지 않고 닫기</button>
							</div>
						</div>
					</div>
				</form>
			</c:if>						
		</div>
	</div>
	
	<style>
	.select2-container { width: 99.9% !important; }
	.ui-timepicker-wrapper{z-index:9999!important;}
	</style>
	<script>
		$('.timepickerStr').timepicker({
		    'showDuration': true,
		    'timeFormat': 'H:i:s',
		});
		$(".btn-submit").on("click", function(){			
			$.ajax({
				type:"POST",
				url :"/adm/category/manager/update_act.do",
				data:$('#updateCategoryFrmModal').serialize(),
				dataType: "json",
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
				async: false,
				success: function(data, status, xhr) {
					if(data.error == '0') {
						alertify.alert(data.msg, function(){
							$("#admPublicModal").modal('hide');
						});
					} else {
						alertify.alert(data.msg);
					}
				},
				beforeSend : function() {
					//console.log('before!');
				},
				complete : function() {
					//console.log('complete!');
			    },
				error: function(jqXHR, textStatus, errorThrown) {
					//console.log('error!');
				}
			});
		})
	</script>
	
</div>