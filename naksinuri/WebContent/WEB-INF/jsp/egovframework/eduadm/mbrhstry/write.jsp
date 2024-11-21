<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.modal-dialog{
    position: relative;
    display: table; 
    overflow-y: auto;    
    overflow-x: auto;
    width: auto;
    min-width: 1000px;   
}
</style>

<div class="modal-dialog modal-simple">
<form:form commandName="eduMemberVO" id="listForm2" name="listForm2" class="modal-content form-horizontal" method="post">
	<input type="hidden" name="addedMbrIds" value="${addedMbrIds}" />
	<input type="hidden" name="pageIndex" value="1" />
	<input type="hidden" name="searchUseYn" value="Y" />
	<input type="hidden" name="CRS_SN" value="${parentInfo.CRS_SN}" />
	<input type="hidden" name="searchYear" value="${parentInfo.searchYear}" />
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">수강생 추가하기</h4>
	</div>
	<div class="modal-body">
		<div class="input-group col-md-12 float-right mb-10">
			<input type="text" class="form-control mr-10" name="searchKeyword" placeholder="이름,생년월일,연락처,아이디를 입력하세요." autocomplete="off">
			<span class="input-group-btn">
 				<button type="button" class="btn btn-primary clk_search_btn_modal trg_btn_submit"><i class="icon wb-search" aria-hidden="true"></i></button>
			</span>
		</div>
		<!-- ajax_member_list:start -->
		<div id="ajax_member_list"></div>
		<input style="VISIBILITY: hidden; WIDTH: 0px">
		<div class="float-right">
			<button type="button" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">선택된 교육대상자 모두추가</button>
			<button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
		</div>
  	</div>
</form:form>
</div>
<script>
$(function(){
	$('#listForm2 input[type=text]').keypress(function(key) {
		if(key.keyCode == 13){
			$('.clk_search_btn_modal').click();
	    }
	});
});
function fn_egov_link_page_2(pageNo){
	var form = document.getElementById('listForm2');
	form.pageIndex.value = pageNo;
	form.action = '';
   	$.ajax({
		type:"POST",
		url :"/eduadm/util/member/list.do",
		data:$('#listForm2').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#ajax_member_list").html(data);
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
   	
}
$(".clk_search_btn_modal").click(function() {
	var form = document.getElementById('listForm2');
	form.pageIndex.value = '1';
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/eduadm/util/member/list.do",
		data:$('#listForm2').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#ajax_member_list").html(data);
		},
		beforeSend : function() {
			//console.log('before!');
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);
		}
	});
});   
$("#btn_submit").click(function() {
	$.ajax({
		type:"POST",
		url :"/eduadm/mbrhstry/write_act.do",
		data:$('#listForm2').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
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
					alertify.alert(data.msg, function(){ 
						$("#admPublicModal").modal('hide');
						document.listForm.submit();	
					});
				} else {
					alertify.alert(data.msg, function(){ 
						$("#admPublicModal").modal('hide');
						document.listForm.submit();	
					});
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
