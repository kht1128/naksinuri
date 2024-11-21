<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form commandName="eduMemberVO" id="ajaxViewForm" name="ajaxViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>

<div class="modal-dialog modal-simple">
<form id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="/eduadm/mbrhstry/modify_act.do">
	<input type="hidden" name="LRNNG_CMPLT_ST" value=""/>
	
	<!-- 교육분류에 따른 표기 처리 : start -->
	<c:choose>
		<c:when test="${parentInfo.CRS_TYPE eq 'fshsk_trnng'}">
			<c:set var="label_crs_time" value="개월" />
		</c:when>
		<c:otherwise>
			<c:set var="label_crs_time" value="시간" />
		</c:otherwise>											
	</c:choose>
	<!-- 교육분류에 따른 표기 처리 : end -->
	<c:choose>
		<c:when test="${parentInfo.TYPE_GB eq 'online'}">
			<c:set var="is_allow_online" value="1" />
		</c:when>
		<c:otherwise>
			<c:set var="is_allow_online" value="0" />
		</c:otherwise>											
	</c:choose>	       
	
	<input type="hidden" id="typeStr" name="typeStr" value="dtl"/>
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">수강생 정보수정</h4>
	</div>
	<div class="modal-body">
		<!-- <div class="form-group row">
			<label class="col-md-3 form-control-label" >이수상태</label>
           	<div class="col-md-9">
            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-info" name="LRNNG_CMPLT_ST">
        			<option value="0" <c:if test="${info.LRNNG_CMPLT_ST eq '0'}">selected</c:if>>수강중</option>
        			<option value="1" <c:if test="${info.LRNNG_CMPLT_ST eq '1'}">selected</c:if>>이수완료</option>
        			<option value="2" <c:if test="${info.LRNNG_CMPLT_ST eq '2'}">selected</c:if>>이수취소</option>
      			</select>
			</div>
		</div> -->
		<!-- <div class="form-group row">
			<label class="col-md-3 form-control-label" ></label>
           	<div class="col-md-9">
            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-success" name="">
        			<option value="0" <c:if test="${info.LRNNG_ST eq '0'}">selected</c:if> >대기</option>
        			<option value="1" <c:if test="${info.LRNNG_ST eq '1'}">selected</c:if>>승인</option>
        			<option value="2" <c:if test="${info.LRNNG_ST eq '2' or info.LRNNG_ST eq '3'}">selected</c:if>>취소</option>
        			<!-- <option value="3" <c:if test="${info.LRNNG_ST eq '3'}">selected</c:if>>강제취소</option> 
        			<option value="4" <c:if test="${info.LRNNG_ST eq '4'}">selected</c:if>>보류</option>
      			</select>
			</div>
		</div> -->


		<div class="form-group row">
			<label class="col-md-3 form-control-label" >이수상태</label>
           	<div class="col-md-9">
            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-dark" name="LRNNG_CHECK_ST">
        			<option value="0" <c:if test="${(info.LRNNG_ST eq 0 && info.LRNNG_CMPLT_ST eq 0) || (info.LRNNG_ST eq 1 && info.LRNNG_CMPLT_ST eq 0)}">selected</c:if> >대기</option>
        			<option value="1" <c:if test="${info.LRNNG_ST eq 0 && info.LRNNG_CMPLT_ST eq 1}">selected</c:if> >가이수</option>
        			<option value="2" <c:if test="${info.LRNNG_ST eq 1 && info.LRNNG_CMPLT_ST eq 1}">selected</c:if> >이수완료</option>
        			<option value="3" <c:if test="${info.LRNNG_ST eq 2 || info.LRNNG_ST eq 3}">selected</c:if> >취소</option>
        			<option value="4" <c:if test="${info.LRNNG_ST eq 4 }">selected</c:if> >보류</option>
        			<option value="5" <c:if test="${info.LRNNG_CMPLT_ST eq 2}">selected</c:if> >이수취소</option>
      			</select>
			</div>
		</div>
		<!-- 
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >결제상태</label>
           	<div class="col-md-9">
            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="PURCHASE_CMPLT_ST">
        			<option value="0" <c:if test="${info.PURCHASE_CMPLT_ST eq '0'}">selected</c:if> >대기</option>
        			<option value="1" <c:if test="${info.PURCHASE_CMPLT_ST eq '1'}">selected</c:if>>완료</option>
      			</select>
			</div>
		</div>
		-->
		<c:if test="${not empty info}">
			<div class="form-group row">
				<label class="col-md-3 form-control-label" >수강고유번호</label>
				<div class="col-md-9">
					<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.HMBR_SN}" disabled>
					<input type="hidden" name="HMBR_SN" value="${info.HMBR_SN}">
				</div>
			</div>
			<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info.CRS_SN}"/>
			<input type="hidden" id="MBR_ID" name="MBR_ID" value="${info.MBR_ID}"/>
		</c:if>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >이름</label>
           	<div class="col-md-9">
           		<c:choose>
           			<c:when test="${empty info.MBR_NM}">
           				<input type="text" class="form-control " placeholder="" autocomplete="off" value="(비회원) ${info.TMP_MBR_NM} ${info.MBR_ID}" disabled>
           			</c:when>
           			<c:otherwise>
           				<input type="text" class="form-control " placeholder="" autocomplete="off" value="${info.MBR_NM}" disabled>
           			</c:otherwise>
           		</c:choose>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >연락처</label>
           	<div class="col-md-9">
           		<c:choose>
           			<c:when test="${empty info.MBR_HP}">
           				<input type="text" class="form-control " placeholder="" autocomplete="off" value="${info.TMP_MBR_HP}" disabled>
           			</c:when>
           			<c:otherwise>
           				<input type="text" class="form-control " placeholder="" autocomplete="off" value="${info.MBR_HP}" disabled>
           			</c:otherwise>
           		</c:choose>
				<%--<p class="form-control-plaintext">${info.MBR_HP}</p>--%>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >생년월일</label>
           	<div class="col-md-9">
           		<%--
           		<c:choose>
           			<c:when test="${empty info.MBR_BIRTH}">
           				<c:set var="mbrbirthstring" value="${info.TMP_MBR_BIRTH}" />
           			</c:when>
           			<c:otherwise>
           				<c:set var="mbrbirthstring" value="${info.MBR_BIRTH}" />
           			</c:otherwise>
           		</c:choose>
           		<fmt:parseDate var="parsembrbirthstring" value="${mbrbirthstring}" pattern="yyyyMMdd" />
           		<fmt:formatDate var="birthString" value="${parsembrbirthstring}" pattern="yyyy년 MM월 dd일" />
           		<input type="text" class="form-control bg-white" placeholder="" autocomplete="off" value="${birthString}" disabled>
           		--%>
           		<input type="text" class="form-control " placeholder="" autocomplete="off" value="${info.MBR_BIRTH}" disabled>
			</div>
		</div>
		<!-- 
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="HMBR_INPUT_TIME">이수교육시간<br>(오프라인)</label>
			<div class="col-md-9">
				<div class="input-group">
					<input type="text" class="form-control col-lg-3" id="HMBR_INPUT_TIME" name="HMBR_INPUT_TIME" placeholder="교육시간을 입력해주세요." autocomplete="off" value="${info.HMBR_INPUT_TIME}" required>
					<div class="input-group-append">
           				<span class="input-group-text">${label_crs_time}</span>
           				<span class="input-group-text bg-white">최대가능시간 : ${parentInfo.CRS_TOT_TIME} ${label_crs_time}</span>
           			</div>
				</div>
				<c:if test="${is_allow_online eq '1'}">
					<span class="text-help">현재 입력된 시간과 온라인교육 시간이 최종 합산됩니다.</span>
				</c:if>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="HMBR_INPUT_POINT">이수교육점수<br>(오프라인)</label>
			<div class="col-md-9">
				<div class="input-group">
					<input type="text" class="form-control col-lg-3" id="HMBR_INPUT_POINT" name="HMBR_INPUT_POINT" placeholder="교육점수를 입력해주세요." autocomplete="off" value="${info.HMBR_INPUT_POINT}" required>
					<div class="input-group-append">
           				<span class="input-group-text">점수</span>
           				<span class="input-group-text bg-white">최대가능점수 : ${parentInfo.CRS_TOT_POINT} 점</span>
           			</div> 
				</div>
				<c:if test="${is_allow_online eq '1'}">
					<span class="text-help">현재 입력된 점수와 온라인교육 점수가 최종 합산됩니다.</span>
				</c:if>
			</div>
		</div>
		 -->
		 <input type="hidden" class="form-control col-lg-3" id="HMBR_INPUT_TIME" name="HMBR_INPUT_TIME" placeholder="교육시간을 입력해주세요." autocomplete="off" value="${parentInfo.CRS_TOT_TIME}" readonly>
		 <input type="hidden" class="form-control col-lg-3" id="HMBR_INPUT_POINT" name="HMBR_INPUT_POINT" placeholder="교육점수를 입력해주세요." autocomplete="off" value="${parentInfo.CRS_TOT_POINT}" readonly>
		 
		 
		<c:if test="${is_allow_online eq '1'}">
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm" >이수교육시간<br>(온라인)</label>
           	<div class="col-md-9">
           		<div class="input-group">
           			<input type="text" class="form-control col-lg-3" value="${info.LRNNG_TOT_TIME}" disabled />
           			<div class="input-group-append">
           				<span class="input-group-text">시간</span>
           				<span class="input-group-text bg-white">최대가능시간 : ${info.HMBR_MAX_TIME - parentInfo.CRS_TOT_TIME} 시간</span>
           			</div> 
            	</div>
            	<span class="text-help">온라인 교육은 이수시 자동으로 반영됩니다.</span>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm" >이수교육점수<br>(온라인)</label>
           	<div class="col-md-9">
           		<div class="input-group">
            		<input type="text" class="form-control col-lg-3" value="${info.LRNNG_TOT_POINT}" disabled />
	            	<div class="input-group-append">
	           			<span class="input-group-text">점</span> 
	           			<span class="input-group-text bg-white">최대가능점수 : ${info.HMBR_MAX_POINT - parentInfo.CRS_TOT_POINT} 점</span>
	           		</div>
	           	</div> 
            	<span class="text-help">온라인 교육은 이수시 자동으로 반영됩니다.</span>
			</div>
		</div>
		</c:if>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_Y" name="USE_ST_CHK" value="Y" <c:if test="${empty info}">checked</c:if> <c:if test="${info.USE_ST eq '1'}">checked</c:if>>
					<label for="USE_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_N" name="USE_ST_CHK" value="N" <c:if test="${not empty info and info.USE_ST eq '0'}">checked</c:if>>
  					<label for="USE_ST_N">사용안함</label>
				</div>
			</div>
		</div>
		<c:if test="${parentInfo.CRS_MBR_CD eq 'CIDN010300' and parentInfo.TYPE_GB eq 'offline'}"><%-- //낚시어선, 오프라인 교육일 경우만 보임// --%>
			<div class="form-group row">
				<label class="col-md-3 form-control-label" >기존, 신규, 재개자</label>
	           	<div class="col-md-9">
	            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="HMBR_FSHRBT_TYPE">
	            		<option value="" <c:if test="${empty info.HMBR_FSHRBT_TYPE}">selected</c:if> >전체</option>
	        			<option value="legacy" <c:if test="${info.HMBR_FSHRBT_TYPE eq 'legacy'}">selected</c:if> >기존</option>
	        			<option value="new" <c:if test="${info.HMBR_FSHRBT_TYPE eq 'new'}">selected</c:if>>신규</option>
	        			<option value="resmpt" <c:if test="${info.HMBR_FSHRBT_TYPE eq 'resmpt'}">selected</c:if>>재개자</option>
	      			</select>
				</div>
			</div>
		</c:if>		
        <div class="form-group row">
        	<label class="col-md-3 form-control-label" for="REG_DT">교육신청일자</label>
           	<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.REG_DT} (${info.REG_MBR_ID})" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="UPD_DT">정보변경일자</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.UPD_DT} (${info.UPD_MBR_ID})" disabled>
   			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="UPD_DT">이수완료일자</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.LRNNG_CMPLT_DT} (${info.LRNNG_CMPLT_MBR_ID})" disabled>
   			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >회원상세정보</label>
			<div class="col-md-9">
				<c:choose>
           			<c:when test="${empty info.MBR_NM}">
           				<span class="text-help">회원정보가 없습니다.</span>
           			</c:when>
           			<c:otherwise>
           				<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_view_data('${info.MBR_ID}')" >자세히보기</a>
           			</c:otherwise>
           		</c:choose>	
			</div>
		</div>		
		<hr>
		<div class="form-group row">
			<div class="btn-group col-lg-12">
				<textarea class="form-control h-80" name="LOG_UPD_MSG" placeholder="변경사유(설명)" row="5" required></textarea>
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
/*
function matchStart(params, data) {
	
  // If there are no search terms, return all of the data
  if ($.trim(params.term) === '') {
    return data;
  }
  
	
  	var form = document.getElementById('listForm2');
	form.searchKeyword.value = params.term;
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/seadm/ajax_util_mbr_list.do",
		data:$('#listForm2').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log('success!');
			if(data.error == '1') {
				alertify.alert(data.msg);
			} else {
				var json = JSON.parse(data.rawdata);
				var htmlString = '';
				if(json.length > 0) {
					htmlString = '<option value="">회원을 선택해주세요.</option>';
				} else {
					htmlString = '<option value="">조회 된 회원이 없습니다.</option>';
				}
				for (i=0; i<json.length; i++) {
					var item = json[i];
					htmlString += '<option value="'+item.mbr_id+'">'+item.mbr_nm+'&nbsp;('+item.mbr_id+','+item.mbr_hp+')</option>';
				}
				$('#trg_sel_cat_1').html(htmlString);
			}
		},
		beforeSend : function() {
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			console.log('error!');
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
  

  // Return `null` if the term should not be displayed
  return null;
}
*/
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
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

	//default
	$.ajax({
		type:"POST",
		url :$(this).attr("action"),
		data:$(this).serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		cache: false,
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
					alertify.alert(data.msg);
				} else {
					//alertify.alert(data.msg,function(){
						$("#admPublicModal").modal('hide');
						window.location.reload(true);
						//document.modal_action_form.submit();
						//refreshListPage();
					//});
				}
			}
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
			$('.trg_btn_submit').attr('disabled', true);
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').attr('disabled', false);
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
	/*
	//enctype="multipart/form-data" 
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
				} else {
					$("#seaAdmEduPublicModal").modal('hide');
					window.location.reload();
				}
			}
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
	*/
});
function clk_view_data(mbr_id) {
	var form = document.getElementById('ajaxViewForm');
	form.MBR_ID.value = mbr_id;
	$.ajax({
		type:"POST",
		url :'/eduadm/member/view.do',
		data:$('#ajaxViewForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#admPublicPanelLayer").html(data);
			$("#admPublicPanelLayer").show();
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}
</script>
