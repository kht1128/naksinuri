<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-dialog modal-simple">
<form:form commandName="eduMemberVO" id="listForm2" name="listForm2" class="modal-content form-horizontal" method="post" enctype="multipart/form-data">
	<input type="hidden" name="CRS_SN" value="${parentInfo.CRS_SN}" />
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">수강생 추가하기(엑셀업로드)</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">첨부파일</legend>
			<div class="col-md-9">
				<div class="input-group input-group-file" data-plugin="inputGroupFile">
	     			<input type="text" class="form-control" readonly="" value="" />
	     			<span class="input-group-btn">
	       				<span class="btn btn-success btn-file">
	         				<i class="icon wb-upload" aria-hidden="true"></i>
	         				<input type="file" name="UPLOAD_FILE_ATCH" multiple="false" value="" />
	       				</span>
	     			</span>
	   			</div>
   			</div>
   		</div>				
		<div class="float-right">
			<button type="button" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">회원업로드</button>
			<button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
		</div>
  	</div>
</form:form>
</div>
<script>
$("#btn_submit").click(function() {
	var form = $('#listForm2')[0];
	var formData = new FormData(form);
	$.ajax({
		type:"POST",
		enctype: 'multipart/form-data',
		url :"/eduadm/mbrhstry/write_excel_act.do",
		data:formData,
		contentType: false,
		processData: false,
		async: true,
		cache: false,
		timeout: 600000,
		success: function(data, status, xhr) {
			console.log('success!');
			if(data.errCnt > 0) {
				var pass = true;
				for(var key in data.errField) {
					$('#'+data.errField[key]).addClass('is-invalid');
					pass = false;
				}
				if(pass) {
					alertify.alert(data.msg);
				}
			} else {
				if(data.error == '1') {
					alertify.alert(data.msg);
				} else {
					$("#seaAdmEduPublicModal").modal('hide');
					document.listForm.submit();
				}
			}
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
			clickRequestLockStop();
		}
	});
});
</script>
