<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>
<style>
.basic_tbl th, .basic_tbl td{border:0px;}
.basic_tbl tbody{border:1px;}
</style>
<form:form commandName="policyVO" id="modalForm" name="modalForm" method="post" onsubmit="return false">
	<input type="hidden" name="voc_no" id="voc_no" value="${info.voc_no}"/>
	<input type="hidden" name="voc_is_mber_ntt" id="voc_is_mber_ntt" value="${info.voc_is_mber_ntt}"/>
	<div class="modal-dialog" role="document">
		<div class="modal-content form-horizontal">
			<div class="modal-header">
				<h4 class="modal-title" tabindex="0" style="display: inline;">본인여부 확인</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   						<span aria-hidden="true">×</span>
 					</button>
			</div>
			<div class="modal-body pt-0 pl-30 pr-30">
				<div class="form-group" >
					<div class="modal-body pt-30 pl-30 pr-30">
						<div class="form-group row modal-view-table scroll-y">
							<table class="basic_tbl">
								<caption>본인여부 확인</caption>
								<colgroup>
									<col width="25%">
									<col width="*">
								</colgroup>
								<thead></thead>
								<tbody>
									<tr>
										<th><label class="table_label">비밀번호</label></th>
										<td>
											<input type="password" class="basic_input w100" placeholder="비밀번호를 입력하세요." name="voc_pwd" id="voc_pwd"/>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
			  		</div>
				</div>
				<div class="text-right">
					<button type="button" class="btn btn-primary btn-outline clk_btn_act">확인</button>
					<button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
		        </div>
			</div>
		</div>
	</div>
</form:form>	
	<script>
	$(".clk_btn_act").click(function() {
		var form = document.getElementById('modalForm');
		
		if(form.voc_pwd.value == '') {
			//allPublicModalMessage($(form.RMNDR_CRS_SN).attr('data-fail-message'));
			alert('비밀번호를 입력해주세요.');
			return;
		}

		$.ajax({
			type:"POST",
			url :"/policy/customer_sound/check_voc_pwd_act.do",
			data:$('#modalForm').serialize(),
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data.error == '0') {
					$("#listform").attr("action", data.returnUrl);
					$("#listform").submit();
				} else {
					alert(data.msg);
				}
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
</div>

