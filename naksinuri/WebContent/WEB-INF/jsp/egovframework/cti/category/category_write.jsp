<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" autocomplete="off" action="/cti/category/write_act.do">
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">카테고리 신규추가</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="CD_NM">명칭</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control" id="CD_NM" name="CD_NM" placeholder="카테고리 명칭을 입력하세요." autocomplete="off" value="" required>
			</div>
		</div>
		
		<div class="float-right">
        	<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" >생성하기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<script>

$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();
	var cd_nm = '';
	var cd_master_id = '${CD_MASTER_ID}';
	cd_nm = $('#CD_NM').val();
	
	$.ajax({
		type:"POST",
		url :$(this).attr("action"),
		data:{
			CD_NM : cd_nm,
			CD_MASTER_ID : cd_master_id,
		},
		dataType: "html",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//application/json;charset=UTF-8
		async: true,
		cache: false,
		success: function(data, status, xhr) {
			//console.log('success!');
			if(data.errCnt > 0) {
				for(var key in data.errField) {
					$('#'+data.errField[key]).addClass('is-invalid');	
				}
			} else {
				if(data.error == '1') {
					alertify.alert(data.msg);
				}
				$("#eduAdmEduPublicModal").modal('hide');
				window.location.reload();
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

