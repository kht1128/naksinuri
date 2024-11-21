<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-dialog" role="document">
<form:form commandName="myHistoryVO" id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="">	
	<input type="hidden" id="HMBR_MBR_TYPE" name="HMBR_MBR_TYPE" value="0"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">수강 내역 조회</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row modal-view-table">
		<table class="basic_tbl">
			<caption>수강내역조회</caption>
			<colgroup>
				<col width="150">
				<col>
			</colgroup>
			<thead><tr style="display:none;"><th></th></tr></thead>
			<tbody>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" class="basic_input w100" id="TMP_MBR_NM" name="TMP_MBR_NM" value="" placeholder="예) 홍길동" title="이름">
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="text" class="basic_input w100" id="TMP_MBR_BIRTH" name="TMP_MBR_BIRTH" value="" placeholder="예) 19190301" title="생년월일">
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="float-right">
			<button type="submit" class="btn btn-primary btn-outline clk_btn_act">완료(조회)</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form:form>

<script>
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();
	
	var form = document.getElementById('modal_action_form');
	form.action = '';
	
	if(form.TMP_MBR_NM.value=='') {
		eduCenterPublicModalMessage('이름을 입력해주세요.');
		clickRequestLockStop();
		return;
	}
	if(form.TMP_MBR_BIRTH.value=='') {
		eduCenterPublicModalMessage('생년월일을 입력해주세요.');
		clickRequestLockStop();
		return;
	}
	
	$.ajax({
		type:"POST",
		url :"/educenter/m/mbrhstry/searchCrtf_act.do",
		data:$('#modal_action_form').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$('#ajaxBodyCrtfSearch').html(data);
			$("#eduCenterPublicModal").modal('hide');
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
