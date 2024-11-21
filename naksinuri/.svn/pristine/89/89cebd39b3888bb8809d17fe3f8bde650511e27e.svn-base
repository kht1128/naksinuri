<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.basic_tbl th, .basic_tbl td{border:0px;}
</style>

<div class="modal-dialog" role="document">
<form:form commandName="eduCertificateVO" id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="">	
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">이수증 발급 요청서</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row modal-view-table">
		<table class="basic_tbl">
			<caption>발급요청</caption>
			<colgroup>
				<col width="150">
				<col>
			</colgroup>
			<thead><tr class="table-cell-blind"><th></th></tr></thead>
			<tbody>
				<c:if test="${empty info}">
					<tr>
						<td colspan="2" class="text-center">정상적인 접근이 아니거나 발급요청을 할 수 없는 상태입니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty info}">
				<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info.CRS_SN}"/>
				<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${info.HMBR_SN}"/>
				<input type="hidden" id="CRTF_SN" name="CRTF_SN" value="${info.CRTF_SN}"/>
				<tr>
					<th>발급용도</th>
					<td>
						<input type="text" class="basic_input w100" id="CRTF_TYPE_ST" name="CRTF_TYPE_ST" value="" title="발급용도">
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		</div>
		<div class="text-right">
			<c:if test="${not empty info}">
				<button type="submit" class="btn btn-primary btn-outline clk_btn_act">완료(생성)</button>
			</c:if>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form:form>

<script>
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();
	
	var form = document.getElementById('modal_action_form');
	form.action = '';
	
	if(form.CRTF_TYPE_ST.value=='') {
		allPublicModalMessage('발급용도를 작성해주세요.');
		clickRequestLockStop();
		return;
	}
	
	$.ajax({
		type:"POST",
		url :"/educenter/mbrhstry/issueCrtf_act.do",
		data:$('#modal_action_form').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: false,
		success: function(data, status, xhr) {
			//console.log(data);
			if(data.error == '1') {
				//alert(data.msg);
				allPublicModalMessage(data.msg);
			} else {
				if(data.refresh == '1') {
					document.listForm.submit();
				}
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
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
		}
	});
	
});
</script>
	
</div><!-- /.modal-dialog -->
