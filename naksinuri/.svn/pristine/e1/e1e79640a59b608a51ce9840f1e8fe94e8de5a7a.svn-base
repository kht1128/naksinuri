<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../../head.jsp" %> 
<%@ include file="../../left_menu.jsp" %>

<form:form commandName="eduBoardVO" id="listForm" name="listForm" method="post" enctype="multipart/form-data" onsubmit="return frm_submit()" 
	action="/eduadm/board/faq/modify_act.do"> 
	<input type="hidden" name="BD_SN" value="${info.BD_SN}" />
			
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
					<label class="col-md-2 form-control-label" >우선순위</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control" id="BD_RANK" name="BD_RANK" placeholder="우선순위를 입력하세요." autocomplete="off" value="${info.BD_RANK}" maxlength="4" required>
						<span class="text-help red-600 font-size-12">숫자가 작을수록 상위에 노출됩니다.(기본값 9999)</span>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >제목</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control" id="BD_TITLE" name="BD_TITLE" placeholder="제목을 입력하세요." autocomplete="off" value="${info.BD_TITLE}" required>
					</div>
				</div>	
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
						<input type="hidden" name="returnUrl" value="/eduadm/board/faq/modify.do">
						<input type="hidden" name="targetFormId" value="">
						<input type="hidden" name="selectedId" value="${info.BD_SN}" />
			   			<c:import url="/cmm/fms/selectFileInfsForUpdate.do" >
					    	<c:param name="param_atchFileId" value="${info.BD_FILE}" />
					    </c:import>
					</div>
				</div>			
				<div class="form-group row">
					<label class="col-md-2 form-control-label" for="CRS_NM">내용</label>
		           	<div class="col-md-10">
		            	<textarea name="BD_CONT" id="BD_CONT" rows="10" cols="100" style="display:none;"><c:out value="${info.BD_CONT}"/></textarea>
					</div>
				</div>
				<c:if test="${info.BD_ST eq 'N'}">
				<div class="form-group row">
					<label class="col-md-2 form-control-label" for="BD_ST">사용여부</label>
					<div class="col-md-10">
						<div class="radio-custom radio-default radio-inline">
							<input type="radio" id="BD_ST_Y" name="BD_ST" value="Y" <c:if test="${info.BD_ST eq 'Y'}">checked</c:if>>
							<label for="BD_ST_Y">복구함</label>
						</div>
						<div class="radio-custom radio-default radio-inline">
							<input type="radio" id="BD_ST_N" name="BD_ST" value="N" <c:if test="${info.BD_ST eq 'N'}">checked</c:if>>
		  					<label for="BD_ST_N">복구안함</label>
						</div>
						<span class="text-help red-600 font-size-12">* 현재 삭제 된 게시물 (${info.BD_DEL_DT}) </span>
					</div>
				</div>		
				</c:if>
				<div class="text-center /*float-middle*">
		        	<button class="append-rows btn btn-warning btn-outline" type="button" href="#" onclick="listact()" >
		        		<i class="site-menu-icon fas fa-bars" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">목록</span>
		        	</button>
		        	&nbsp;
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
<!-- End //스마트에디터 커스텀 -->

<script>
function listact(obj) {
	location.href="/eduadm/board/faq/list.do";
}
function frm_submit() {
	if(!$.trim($('#BD_TITLE').val())) { 
		alert("제목을 입력하여 주세요."); 	
		$('#BD_TITLE').focus(); 
		
		return false; 
	}
	oEditors.getById["BD_CONT"].exec("UPDATE_CONTENTS_FIELD", []); $("#frm").submit();
	return true;
	
}	
</script>

<%@ include file="../../tail.jsp" %>
      