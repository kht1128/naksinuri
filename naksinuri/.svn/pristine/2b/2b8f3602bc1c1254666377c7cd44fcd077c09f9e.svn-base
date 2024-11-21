<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../../head.jsp" %> 
<%@ include file="../../left_menu.jsp" %>

<form:form commandName="CodeSetVO" id="writeFormSido" name="writeFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="eduCategoryInsInfVO" id="listForm" name="listForm" method="post" enctype="multipart/form-data" onsubmit="return frm_submit()" 
	action="/eduadm/category/academy/write_act.do"> 
	<input type="hidden" name="CAT_INS_SN" value="${info.CAT_INS_SN}" />
			
<!-- Page -->
<div class="page">
	<div class="page-header">
		<c:choose>
			<c:when test="${not empty subpageNum}">
				<h1 class="page-title">${subpageName}</h1>
		        <ol class="breadcrumb">
		        	<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item">
		        		<a href="${pageUrl}">${pageName}</a>
		        	</li>
		        	<li class="breadcrumb-item active">${subpageName}</li>
		      	</ol>
			</c:when>
			<c:otherwise>
				<h1 class="page-title">${pageName}</h1>
		        <ol class="breadcrumb">
		       		<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item active">${pageName}</li>
		      	</ol>
			</c:otherwise>											
		</c:choose>
		<div class="page-header-actions"></div>
	</div>
      
	<div class="page-content container-fluid">	
	<!-- page-content -->
	
		<div class="panel">
			<div class="panel-body">
			
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >시도 <span class="red-600">*</span></label>
		           	<div class="col-md-10">
		            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_sido" name="SIDO_CD" required>
							<option value="" >시도 전체</option>
							<c:forEach var="item_category" items="${list_address_cd}">
								<option value="${item_category.CD_ID}">${item_category.CD_NM}</option>
							</c:forEach>	        			
	      				</select>
					</div>
				</div>	
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >시군구 <span class="red-600">*</span></label>
		           	<div class="col-md-10">
		            	<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_signgu" name="SIGNGU_CD" required>
							<option value="" >시군구 전체</option>
	      				</select>
					</div>
				</div>	
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >교육기관명 <span class="red-600">*</span></label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="CAT_INS_NM" placeholder="교육기관명을 입력하세요."  value="" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >대표자직책 <span class="red-600">*</span></label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="CAT_INS_PSTN" placeholder="대표자직책을 입력하세요." value="" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >대표자</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="CAT_INS_CEO" placeholder="대표자를 입력하세요." value="" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >대표번호</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="CAT_INS_TEL" placeholder="대표번호를 입력하세요." value="" numberOnly>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >주소</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="CAT_INS_ADDR" placeholder="주소정보를 입력하세요." value="" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label">설명</label>
					<div class="col-md-10">
						<textarea class="form-control h-100" name="CAT_INS_DSCRP" placeholder="설명(요약)" row="5" ></textarea>
					</div>
				</div>
		   		<div class="form-group row">
					<label class="col-md-2 form-control-label">로고 이미지</label>
					<div class="col-md-6">
						<textarea class="form-control h-p100" id="CAT_INS_LOGO" name="CAT_INS_LOGO" placeholder="로고이미지의 바이너리 코드값을 입력하세요." row="5" ></textarea>
					</div>
					<div class="col-md-4">
						<a href="javascript:void(0)" class="btn btn-outline btn-warning clk_confirm_img_code mr-10" 
						data-target-img-id="PREVIEW_CAT_INS_LOGO"
						data-target-img-code-id="CAT_INS_LOGO">검증</a>
						<img src="" id="PREVIEW_CAT_INS_LOGO" style="max-height:50px!important;"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label">직인 이미지 <span class="red-600">*</span></label>
					<div class="col-md-6">
						<textarea class="form-control h-p100" id="CAT_INS_STAMP" name="CAT_INS_STAMP" placeholder="직인이미지의 바이너리 코드값을 입력하세요." row="5" ></textarea>
					</div>
					<div class="col-md-4">
						<a href="javascript:void(0)" class="btn btn-outline btn-warning clk_confirm_img_code mr-10" 
						data-target-img-id="PREVIEW_CAT_INS_STAMP"
						data-target-img-code-id="CAT_INS_STAMP">검증</a>
						<img src="" id="PREVIEW_CAT_INS_STAMP" style="max-height:50px!important;"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label">교육장약도 이미지</label>
					<div class="col-md-6">
						<textarea class="form-control h-p100" id="CAT_INS_OTLNMAP" name="CAT_INS_OTLNMAP" placeholder="교육장약도이미지의 바이너리 코드값을 입력하세요." row="5" ></textarea>
					</div>
					<div class="col-md-4">
						<a href="javascript:void(0)" class="btn btn-outline btn-warning clk_confirm_img_code mr-10" 
						data-target-img-id="PREVIEW_CAT_INS_OTLNMAP"
						data-target-img-code-id="CAT_INS_OTLNMAP">검증</a>
						<img src="" id="PREVIEW_CAT_INS_OTLNMAP" style="max-height:50px!important;"/>
					</div>
				</div>
				<%--
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >첨부파일</label>
		           	<div class="col-md-10">		            	
		            	<div class="input-group input-group-file" data-plugin="inputGroupFile">
			     			<input type="text" class="form-control" readonly="" value="" id="egovComFileUploader_label"/>
			     			<span class="input-group-btn">
			       				<span class="btn btn-success btn-file">
			         				<i class="icon wb-upload" aria-hidden="true"></i>
			         				<input type="file" name="FILE_ATCH" multiple="false" value="" id="egovComFileUploader"/>
			       				</span>
			     			</span>
			   			</div>
			   			<div id="egovComFileList"></div>
			   			<input type="hidden" name="fileMaxCnt" value="5" />
						<input type="hidden" name="returnUrl" value="/eduadm/category/academy/modify.do">
						<input type="hidden" name="targetFormId" value="">
						<input type="hidden" name="selectedId" value="${info.BD_SN}" />
			   			<c:import url="/cmm/fms/selectFileInfsForUpdate.do" >
					    	<c:param name="param_atchFileId" value="${info.BD_FILE}" />
					    </c:import>
					</div>
				</div>		
				 --%>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" for="USE_ST">사용여부</label>
					<div class="col-md-10">
						<div class="radio-custom radio-default radio-inline">
							<input type="radio" id="USE_ST_Y" name="USE_ST" value="1" checked>
							<label for="USE_ST_Y">사용함</label>
						</div>
						<div class="radio-custom radio-default radio-inline">
							<input type="radio" id="USE_ST_N" name="USE_ST" value="0" >
		  					<label for="USE_ST_N">사용안함</label>
						</div>						
					</div>
				</div>
				<div class="text-center /*float-middle*/">
		        	<button class="append-rows btn btn-primary btn-outline" type="submit" >
						<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
						<span class="hidden-sm-down">등록</span>
					</button>
					&nbsp;
					<button class="append-rows btn btn-warning btn-outline" type="button" href="#" onclick="history.back();" >
						<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
						<span class="hidden-sm-down">취소</span>
					</button>
		        </div>
		        
			</div>
		</div>
		
	<!-- page-content -->
	</div>
</div>
<!-- End Page -->

</form:form>

<script>
$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});
$(function(){
	$('.selectpicker_manual').selectpicker();
});
$(".clk_confirm_img_code").click(function() {
	var target_img_id = $(this).attr('data-target-img-id');
	var target_img_code_id = $(this).attr('data-target-img-code-id');
	$('#'+target_img_id).attr('src',$('#'+target_img_code_id).val());
});
$("#search_sel_area_sido").change(function() {
	if(this.value=='') {
		$('#search_sel_area_signgu').html('<option value="">시군구 전체</option>').selectpicker('refresh');
		return;
	}  
	var form = document.getElementById('writeFormSido');
	form.CD_MASTER_ID.value = this.value;
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/all/code.do",
		data:$('#writeFormSido').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			//console.log(data);
			//console.log(data.rawdata);
			if(data.error == '1') {
				alertify.alert(data.msg);
			} else {
				var json = JSON.parse(data.rawdata);
				var htmlString = '<option value="">시군구 전체</option>';
				for (i=0; i<json.length; i++) {	
					var item = json[i];
					htmlString += '<option value="'+item.cd_id+'">'+item.cd_nm+'</option>';
				}
				//console.log(htmlString);
				$('#search_sel_area_signgu').html(htmlString).selectpicker('refresh');
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
<%--
<!-- //첨부파일 추가 커스텀 -->
<script type="text/javascript" src="/common/js/EgovMultiFile.js" ></script>
<script type="text/javascript">
var form = document.getElementById('listForm');
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
<!-- //스마트에디터 커스텀 -->
<script type="text/javascript" src="/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
<script type="text/javascript">
var oEditors = [];
// 추가 글꼴 목록
//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "BD_CONT",
	sSkinURI: "/se2/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["BD_CONT"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});
function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["BD_CONT"].exec("PASTE_HTML", [sHTML]);
}
function showHTML() {
	var sHTML = oEditors.getById["BD_CONT"].getIR();
	alert(sHTML);
}	
function submitContents(elClickedObj) {
	oEditors.getById["BD_CONT"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("BD_CONT").value를 이용해서 처리하면 됩니다.
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}
function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["BD_CONT"].setDefaultFont(sDefaultFont, nFontSize);
}
</script>
 --%>
<!-- End //스마트에디터 커스텀 -->

<script>
function frm_submit() {
	//oEditors.getById["BD_CONT"].exec("UPDATE_CONTENTS_FIELD", []); 
    document.listForm.submit();
}	
</script>

<%@ include file="../../tail.jsp" %>
      