<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- 교육카테고리용 -->
<form:form commandName="EduCategoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CAT_SN" name="CAT_SN" value=""/>
<input type="hidden" id="CAT_DTL_SN" name="CAT_DTL_SN" value=""/>
<input type="hidden" id="typeStr" name="typeStr" value=""/>
</form:form>

<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" method="post" enctype="multipart/form-data" autocomplete="off" 
	action="/eduadm/curriculum/modifyDtl_act.do">
	<input type="hidden" id="typeStr" name="typeStr" value="dtl"/>
	<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info.CRS_SN}"/>
	<input type="hidden" name="CAT_SN" value="${info.CAT_SN}"/>
	<input type="hidden" name="CAT_DTL_SN" value="${info.CAT_DTL_SN}"/>
	<input type="hidden" name="CRS_DTL_FILE" value="${info.CRS_DTL_FILE}" />
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">교과목 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >고유번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" name="CRS_DTL_SN" value="${info.CRS_DTL_SN}" readonly>
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
			<label class="col-md-3 form-control-label form-control-sm" >교육자료목록(자동)</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control tokenfield2_manual" name="TRN_SN" value="" placeholder=""/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >과목명</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control" name="CRS_DTL_NM" placeholder="교과목 명칭을 입력하세요." autocomplete="off" value="${info.CRS_DTL_NM}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">설명</label>
			<div class="col-md-9">
				<textarea class="form-control" name="CRS_DTL_DSCRP" placeholder="설명(요약)">${info.CRS_DTL_DSCRP}</textarea>
 			</div>
		</div>			
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_ORD">정렬순서</label>
			<div class="col-md-9">
				<input type="number" class="form-control" id="CRS_ORD" name="CRS_ORD" placeholder="기본값은 9999 입니다." autocomplete="off" value="${info.CRS_ORD}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm">순차진행여부(교과목)</label>
			<div class="col-md-9">
	            <select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="TYPE_GB" >   
					<option value="online" <c:if test="${info.TYPE_GB eq 'online'}">selected</c:if> >온라인 교육</option>
					<option value="offline" <c:if test="${info.TYPE_GB eq 'offline'}">selected</c:if> >오프라인 교육</option>
	            </select>          
            </div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >교육시간(배점)</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control" name="CRS_TOT_TIME" placeholder="교육시간을 입력하세요." autocomplete="off" value="${info.CRS_TOT_TIME}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >교육점수(배점)</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control" name="CRS_TOT_POINT" placeholder="교육점수를 입력하세요." autocomplete="off" value="${info.CRS_TOT_POINT}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm" >첨부파일<br/>(다운로드자료)</label>
           	<div class="col-md-9">		            	
            	<div class="input-group input-group-file" data-plugin="inputGroupFile">
	     			<input type="text" class="form-control" readonly="" value="" id="egovComFileUploader_label"/>
	     			<span class="input-group-btn">
	       				<span class="btn btn-success btn-file">
	         				<i class="icon wb-upload" aria-hidden="true"></i>
	         				<input type="file" multiple="false" value="" id="egovComFileUploader"/>
	       				</span>
	     			</span>
	   			</div>
	   			<div id="egovComFileList"></div>
	   			<input type="hidden" name="fileMaxCnt" value="10" />
				<input type="hidden" name="returnUrl" value="/eduadm/curriculum/modifyDtl.do">
				<input type="hidden" name="targetFormId" value="listForm">
				<input type="hidden" name="selectedId" value="" />
	   			<c:import url="/cmm/fms/selectFileInfsForUpdateAjax.do" >
			    	<c:param name="param_atchFileId" value="${info.CRS_DTL_FILE}" />
			    </c:import>
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
		<input type="file" name="hidefile" style="VISIBILITY: hidden; WIDTH: 0px"/>
		<div class="float-right">
        	<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">변경하기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<!-- //첨부파일 추가 커스텀 -->
<script type="text/javascript" src="/common/js/EgovMultiFile.js" ></script>
<script type="text/javascript">
var form = document.getElementById('modal_action_form');
var existFileNum = form.fileListCnt.value;
var maxFileNum = form.fileMaxCnt.value; 
var uploadableFileNum = maxFileNum - existFileNum; // 최대등록가능한 파일숫자에서 기존에 등록된 숫자를 뺀다.
if(uploadableFileNum<0) {
	uploadableFileNum = 0;
}
if(uploadableFileNum != 0){	 
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}	
$('#egovComFileUploader_label').val("첨부파일은 총 "+maxFileNum+" 개 까지 등록 가능합니다.");	
</script>
<!-- End //첨부파일 추가 커스텀 -->
<style>
.select2-container { width: 99.9% !important; }
</style>
<script>
$('.datepickerStr').datepicker({
    format: 'yyyy-mm-dd 00:00:00',
    startDate: '0d',
    autoclose: true,
});
$('.datepickerEnd').datepicker({
    format: 'yyyy-mm-dd 23:59:59',
    startDate: '0d',
    autoclose: true,
});
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
	$('.tokenfield2_manual').on('tokenfield:removetoken', function (e) {
		var me = $(this);
		var arrVal = me.val().split(",");
		console.log(arrVal);
		alertify.confirm('현재 교육과정의 교과목에 한하여 제외됩니다.<br>삭제 하시겠습니까?', function(){//ok
			var newVal = "";
			for ( var i in arrVal ) {
				if(arrVal[i].trim()!=e.attrs.value) {
					if(newVal.length!=0) newVal += ",";
					newVal += arrVal[i].trim();
				}
		   	}
			me.val(newVal);
			$(e.relatedTarget).remove();
		},function(){//cancel
			
		});
		return false;
	}).tokenfield();
	
	//교육자료 초기값 
	var rawdata = new Array();
	<c:if test="${not empty info}">
	<c:forEach var="item" items="${edu_training_data_list}">
		var rawObj = {
			value : '${item.TRN_SN}',
			label : '${item.TRN_NM}',
		}
		rawdata.push(rawObj);
	</c:forEach>
	</c:if>
	$('.tokenfield2_manual').tokenfield('setTokens', rawdata);
	
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
		//dataType: "json",
		//contentType: "application/x-www-form-urlencoded;charset=UTF-8",//application/json;charset=UTF-8
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
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
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
		beforeSend : function() {
			$('.tokenfield2_manual').tokenfield('setTokens', new Array());
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
});   
$("#trg_sel_cat_2").change(function() {
	var form = document.getElementById('listForm2');
	form.CAT_SN.value = $("#trg_sel_cat_1").val();
	form.CAT_DTL_SN.value = this.value;
	form.typeStr.value = '';
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/eduadm/util/category_to_edudata.do",
		data:$('#listForm2').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			if(data.error == '1') {
				alertify.alert(data.msg);
			} else {
				var json = JSON.parse(data.rawdata);
				var rawdata = new Array();
				for (i=0; i<json.length; i++) {
					var item = json[i];
					var rawObj = {
						value : item.trn_sn,
						label : item.trn_nm,
					}
					rawdata.push(rawObj);
				}
				$('.tokenfield2_manual').tokenfield('setTokens', rawdata);
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
