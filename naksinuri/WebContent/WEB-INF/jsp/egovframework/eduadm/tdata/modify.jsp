<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- 교육카테고리용 -->
<form:form commandName="EduCategoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CAT_SN" name="CAT_SN" value=""/>
<input type="hidden" id="CAT_DTL_SN" name="CAT_DTL_SN" value=""/>
<input type="hidden" id="typeStr" name="typeStr" value=""/>
</form:form>

<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" method="post" enctype="multipart/form-data" autocomplete="off" action="/eduadm/tdata/modify_act.do">
	<input type="hidden" name="TRN_FILE_SN" value="${info.TRN_FILE_SN}" />
	<input type="hidden" name="CAT_SN" value="${info.CAT_SN}"/>
	<input type="hidden" name="CAT_DTL_SN" value="${info.CAT_DTL_SN}"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">교육자료 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="TRN_SN">자료고유번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" name="TRN_SN" value="${info.TRN_SN}" readonly>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">자료타입</label>
			<div class="col-md-9">
				<select class="form-control selec2_manual" name="TRN_TYPE_ST" >   
					<option value="MOV" <c:if test="${info.TRN_TYPE_ST eq 'MOV'}">selected</c:if> >동영상</option>
					<option value="DOC" <c:if test="${info.TRN_TYPE_ST eq 'DOC'}">selected</c:if> >슬라이드</option>
	            </select>            
            </div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm">교육카테고리(상위)</label>
			<div class="col-md-9">
				<select class="form-control selec2_manual" id="trg_sel_cat_1" name="CAT_SN_CHNG" required >   
					<c:if test="${empty edu_category_1}">
						<option value="">등록 된 상위카테고리가 없습니다.</option>
					</c:if>   
					<c:if test="${not empty edu_category_1}">
						<option value="">교육카테고리를 선택해주세요.</option>
					</c:if>
					<c:forEach var="item" items="${edu_category_1}">
						<option value="${item.CAT_SN}" <c:if test="${item.CAT_SN eq info.CAT_SN}">selected</c:if> >${item.CAT_NM}</option>
					</c:forEach>
	            </select>            
            </div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm">교육카테고리(하위)</label>
			<div class="col-md-9">
				<select class="form-control selec2_manual" id="trg_sel_cat_2" name="CAT_DTL_SN_CHNG" required >
					<c:if test="${empty edu_category_2}">
						<option value="">상위카테고리를 선택해주세요.</option>	 
					</c:if>   
					<c:if test="${not empty edu_category_2}">
						<option value="">교육카테고리를 선택해주세요.</option>
					</c:if>
					<c:forEach var="item" items="${edu_category_2}">
						<option value="${item.CAT_DTL_SN}" <c:if test="${item.CAT_DTL_SN eq info.CAT_DTL_SN}">selected</c:if> >
						${item.CAT_DTL_NM}&nbsp;<c:if test="${item.LRNNG_GB eq 'online'}">(온라인교육)</c:if>
						</option>
					</c:forEach>                
	            </select>            
            </div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="TRN_NM">교육자료명</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control" id="TRN_NM" name="TRN_NM" placeholder="교육자료 명칭을 입력하세요." autocomplete="off" value="${info.TRN_NM}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">설명</label>
			<div class="col-md-9">
				<textarea class="form-control" id="TRN_DSCRP" name="TRN_DSCRP" placeholder="설명(요약)">${info.TRN_DSCRP}</textarea>
 			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_Y" name="USE_ST_CHK" value="Y" <c:if test="${info.USE_ST eq '1'}">checked</c:if>>
					<label for="USE_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_N" name="USE_ST_CHK" value="N" <c:if test="${info.USE_ST eq '0'}">checked</c:if>>
  					<label for="USE_ST_N">사용안함</label>
				</div>
				<span class="text-help red-600 font-size-12"><b>삭제</b>상태에서 <b>사용함</b>으로 저장하면 다시 복구 할 수 있습니다.</span>
			</div>
		</div>
		<div class="form-group row"> 
			<legend class="col-md-3 form-control-label">강의자료(표지)</legend>
			<div class="col-md-9">
				<div class="input-group input-group-file" data-plugin="inputGroupFile">
					<input type="text" class="form-control" readonly="" value="" placeholder="표지이미지 업로드"/>
	     			<span class="input-group-btn">
	       				<span class="btn btn-success btn-file">
	         				<i class="icon wb-upload" aria-hidden="true"></i>
	         				<input type="file" name="file_main_img" multiple="false" value="" />
	       				</span>
	     			</span>
	   			</div>
   			</div>
   		</div>
   		<div class="form-group row">
 			<label class="col-md-3 form-control-label" ></label>
			<div class="col-md-9">
	   			<c:import url="/cmm/fms/selectFileInfsForUpdateAjax.do" >
			    	<c:param name="param_atchFileId" value="${info.TRN_FILE_SN}" />
		    		<c:param name="param_fixFileSn" value="1" />
			    	<c:param name="param_lockFileSn" value="0" />
			    	<c:param name="param_updateFlag" value="N" />
			    </c:import>		                
			</div>
   		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">강의자료</legend>
			<div class="col-md-9">
				<div class="input-group input-group-file" data-plugin="inputGroupFile">
					<input type="text" class="form-control" readonly="" value="" placeholder="강의자료 업로드"/>
	     			<span class="input-group-btn">
	       				<span class="btn btn-success btn-file">
	         				<i class="icon wb-upload" aria-hidden="true"></i>
	         				<input type="file" name="file_0" multiple="false" value="" />
	       				</span>
	     			</span>
	   			</div>
   			</div>
   		</div>
   		<div class="form-group row">
 			<label class="col-md-3 form-control-label" ></label>
			<div class="col-md-9">
	   			<c:import url="/cmm/fms/selectFileInfsForUpdateAjax.do" >
			    	<c:param name="param_atchFileId" value="${info.TRN_FILE_SN}" />
		    		<c:param name="param_fixFileSn" value="0" />
			    	<c:param name="param_lockFileSn" value="1" />
			    	<c:param name="param_updateFlag" value="N" />
			    </c:import>		                
			</div>
   		</div>
   		<div class="form-group row">
			<label class="col-md-3 form-control-label" >강의자료(총시간)</label>
			<div class="col-md-9">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="" autocomplete="off" name="TRN_MAX_TIME" value="${info.TRN_MAX_TIME}">
					<div class="input-group-append">
	           			<span class="input-group-text">시간(초단위)</span> 
	           		</div>
				</div>
			</div>
		</div>
        <div class="form-group row">
        	<label class="col-md-3 form-control-label" for="REG_DT">작성정보</label>
           	<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.REG_DT} (${info.REG_MBR_ID})" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="UPD_DT">변경정보</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.UPD_DT} (${info.UPD_MBR_ID})" disabled>
   			</div>
		</div>
		<div class="float-right">
        	<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">변경하기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<style>
.select2-container { width: 99.9% !important; }
</style>
<script>
$(function(){
	$(".selec2_manual").select2();
});
/*
 익스(8이하?) 하위버전에서 작동이 안될경우 현재 주석을 해제하고 로직을 구축하자. 아래 스크립트 링크 필수
 -> /common/js/jquery.form.js 
$('#modal_action_form').ajaxForm({
	url :$(this).attr("action"),
    beforeSubmit: function (data,form,option) {
    	console.log(data);
    	console.log(form);
        $('.trg_btn_submit').addClass('disabled');
        return true;
    },
    success: function(data){
    	console.log('complete!');
		$('.trg_btn_submit').removeClass('disabled');
		console.log(data);
    },
    error: function(err){
    	$('.trg_btn_submit').removeClass('disabled');
        console.log('error!');
        console.log(err);
    }
 });
 */
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();

	var form = $(this)[0];
	var formData = new FormData(form);
	$.ajax({
		type:"POST",
		enctype: 'multipart/form-data',
		url :$(this).attr("action"),
		data:formData,
		contentType: false,
		processData: false,
		async: true,
		cache: false,
		timeout: 600000,
		success: function(data, status, xhr) {
			//console.log('success!');
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
				}
				$("#seaAdmEduPublicModal").modal('hide');
				//window.location.reload();
				document.listForm.submit();
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
//유틸리티 : 교육카테고리 요청
$("#trg_sel_cat_1").change(function() {
	var form = document.getElementById('listForm2');
	form.CAT_SN.value = this.value;
	form.CAT_DTL_SN.value = '';
	form.typeStr.value = 'dtl';
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/eduadm/util/category.do",
		data:$('#listForm2').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			if(data.error == '1') {
				alertify.alert(data.msg);
			} else {
				var json = JSON.parse(data.rawdata);
				var htmlString = '<option value="">하위카테고리를 선택해주세요.</option>';
				for (i=0; i<json.length; i++) {
					var item = json[i];
					htmlString += '<option value="'+item.cat_dtl_sn+'">'+item.cat_dtl_nm+'&nbsp;'+(item.lrnng_gb == 'online'?"(온라인교육)":"")+'</option>';
				}
				$('#trg_sel_cat_2').html(htmlString);
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