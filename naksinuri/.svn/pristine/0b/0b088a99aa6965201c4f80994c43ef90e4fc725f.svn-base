<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../head.jsp" %> 
<%@ include file="../left_menu.jsp" %>

<style>
/**
	간혹 datepicker Ui가 뒤로 밀리는 경우가 있음, 앞으로 땡김 
*/
.datepicker.dropdown-menu {
    position: fixed;
    z-index: 9999 !important;
}
</style>

<form:form commandName="popupVO" id="listForm" name="listForm" method="post" enctype="multipart/form-data" onsubmit="return frm_submit()" 
	action="/seadm/popup/modify_act.do"> 
	<input type="hidden" name="PP_SN" value="${info.PP_SN}" />
	<input type="hidden" name="PP_CONT" value=""/>
	
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
		
		<h4 class="blue-600">공통항목</h4>
		<div class="panel">
			<div class="panel-body">
				<div class="form-group row">
					<label class="col-md-2 form-control-label">노출여부</label>
					<div class="col-md-10">
						<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="PP_HIDE_ST">
		        			<option value="Y" <c:if test="${info.PP_HIDE_ST eq 'Y'}">selected</c:if>>노출안함</option>
		        			<option value="N" <c:if test="${info.PP_HIDE_ST eq 'N'}">selected</c:if>>노출함</option>
		      			</select>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >노출순서</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control" id="PP_RANK" name="PP_RANK" placeholder="기본값:9999, 작은 수 일수록 먼저 노출됩니다." autocomplete="off" value="${info.PP_RANK}">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label">구분(종류선택)</label>
					<div class="col-md-10">
						<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="PP_TYPE">
		        			<option value="popup" <c:if test="${empty info.PP_TYPE or info.PP_TYPE eq 'popup'}">selected</c:if>>팝업</option>
		        			<option value="banner_top" <c:if test="${info.PP_TYPE eq 'banner_top'}">selected</c:if>>배너(상단)</option>
		        			<option value="banner_center" <c:if test="${info.PP_TYPE eq 'banner_center'}">selected</c:if>>배너(중앙)</option>
		      			</select>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >제목</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control" id="PP_TITLE" name="PP_TITLE" placeholder="제목을 입력하세요." autocomplete="off" value="${info.PP_TITLE}" required>
					</div>
				</div>				
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >노출기간</label>
		           	<div class="col-md-10">		            	
		            	<div class="col-md-9 row form-icons">
							<div class="col-lg-5 pl-0">
								<div class="input-group">
									<i class="form-control-icon wb-calendar pl-12"></i>
									<input type="text" class="form-control datepicker_manual_1" name="PP_STR_DT" value="${info.PP_STR_DT}" placeholder="시작일자" autocomplete="off" required>
								</div>
							</div>
							<div class="form-control-label col-lg-1">~</div>
							<div class="col-lg-5 pl-0">
								<div class="input-group">
									<i class="form-control-icon wb-calendar pl-12"></i>
				   					<input type="text" class="form-control datepicker_manual_2" name="PP_END_DT" value="${info.PP_END_DT}" placeholder="종료일자" autocomplete="off" required>
				   				</div>
			   				</div>
						</div>			            	
					</div>
				</div>	
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >URL주소 1</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control" id="PP_LINK_URL" name="PP_LINK_URL" placeholder="URL주소가 없을 경우 입력하지 마세요" autocomplete="off" value="${info.PP_LINK_URL}">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >URL주소 2</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control" id="PP_LINK_URL2" name="PP_LINK_URL2" placeholder="URL주소가 없을 경우 입력하지 마세요" autocomplete="off" value="${info.PP_LINK_URL2}">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label">URL주소클릭시</label>
					<div class="col-md-10">
						<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="PP_LINK_TARGET">
		        			<option value="_blank" <c:if test="${info.PP_LINK_TARGET eq '_blank'}">selected</c:if>>새창열기</option>
		        			<option value="_self" <c:if test="${info.PP_LINK_TARGET eq '_self'}">selected</c:if>>새로고침 (현재 페이지에서 이동)</option>
		      			</select>
					</div>
				</div>
				<div class="form-group row">
		 			<label class="col-md-2 form-control-label" for="CAT_VISIT_SN">노출항목</label>
					<div class="col-md-10">
						<div class="checkbox-custom checkbox-primary">
		     				<input type="checkbox" id="PP_SEA" name="PP_SEA" value="Y" <c:if test="${not empty info.PP_SEA and info.PP_SEA eq 'Y'}">checked</c:if> >
		                  	<label for="PP_SEA">낚시누리 메인사이트</label>
		                </div>				
		                <div class="checkbox-custom checkbox-primary">
		     				<input type="checkbox" id="PP_EDU" name="PP_EDU" value="Y" <c:if test="${not empty info.PP_EDU and info.PP_EDU eq 'Y'}">checked</c:if> >
		                  	<label for="PP_EDU">낚시전문교육 메인사이트</label>
		                </div>
		                <div class="checkbox-custom checkbox-primary">
		     				<input type="checkbox" id="PP_EXPO" name="PP_EXPO" value="Y" <c:if test="${not empty info.PP_EXPO and info.PP_EXPO eq 'Y'}">checked</c:if> >
		                  	<label for="PP_EXPO">랜딩페이지</label>
		                </div>
		                <%-- <div class="checkbox-custom checkbox-primary">
		     				<input type="checkbox" id="PP_EXPO" name="PP_EXPO" value="Y" <c:if test="${not empty info.PP_EXPO and info.PP_EXPO eq 'Y'}">checked</c:if> >
		                  	<label for="PP_EXPO">박람회 메인사이트</label>
		                </div> --%>
		                <%--
		                <div class="checkbox-custom checkbox-primary">
		     				<input type="checkbox" id="PP_CTI" name="PP_CTI" value="${info.PP_CTI}" <c:if test="${not empty info.PP_CTI and info.PP_CTI eq 'Y'}">checked</c:if> >
		                  	<label for="PP_CTI">CTI</label>
		                </div>
		                 --%>
					</div>
				</div>
			</div>
		</div>				
		<div class="panel">
			<div class="panel-body">
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >이미지(PC)<br>( 팝업:이미지크기반영 )<br>( 배너:1200 * 380 권장 )</label>
		           	<div class="col-md-10">
		           		<div class="input-group input-group-file" data-plugin="inputGroupFile">
							<input type="text" class="form-control bg-grey-200" value="" disabled/>
			     			<span class="input-group-btn">
			       				<span class="btn btn-success btn-file">
			         				<i class="icon wb-upload" aria-hidden="true"></i>
			         				<input name="file_pc" type="file" />
			       				</span>
			     			</span>
			   			</div>
			   			<form:form commandName="a">
				   			<input type="hidden" name="returnUrl" value="/seadm/popup/modify.do">
							<input type="hidden" name="targetFormId" value="">
							<input type="hidden" name="selectedId" value="${info.PP_SN}" />
				   			<c:import url="/cmm/fms/selectFileInfsForUpdate.do" >
						    	<c:param name="param_atchFileId" value="${info.PP_FILE}" />
						    	<c:param name="param_fixFileSn" value="0" />
						    	<c:param name="param_lockFileSn" value="1" />
						    </c:import>
					    </form:form>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >이미지(모바일)<br>( 팝업:이미지크기반영 )<br>( 배너:600 * 380 권장 )</label>
		           	<div class="col-md-10">
		           		<div class="input-group input-group-file" data-plugin="inputGroupFile">
							<input type="text" class="form-control bg-grey-200" value="" disabled/>
			     			<span class="input-group-btn">
			       				<span class="btn btn-success btn-file">
			         				<i class="icon wb-upload" aria-hidden="true"></i>
			         				<input name="file_mobile" type="file" />
			       				</span>
			     			</span>
			   			</div>
			   			<form:form commandName="a">
				   			<input type="hidden" name="returnUrl" value="/seadm/popup/modify.do">
							<input type="hidden" name="targetFormId" value="">
							<input type="hidden" name="selectedId" value="${info.PP_SN}" />
				   			<c:import url="/cmm/fms/selectFileInfsForUpdate.do" >
						    	<c:param name="param_atchFileId" value="${info.PP_FILE}" />
						    	<c:param name="param_fixFileSn" value="1" />
						    	<c:param name="param_lockFileSn" value="0" />
						    </c:import>
					    </form:form>
					</div>
				</div>
				
				
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >이미지(PC) 2<br>( 팝업:이미지크기반영 )<br>( 배너:1200 * 380 권장 )</label>
		           	<div class="col-md-10">
		           		<div class="input-group input-group-file" data-plugin="inputGroupFile">
							<input type="text" class="form-control bg-grey-200" value="" disabled/>
			     			<span class="input-group-btn">
			       				<span class="btn btn-success btn-file">
			         				<i class="icon wb-upload" aria-hidden="true"></i>
			         				<input name="file_pc_2" type="file" />
			       				</span>
			     			</span>
			   			</div>
			   			<form:form commandName="a">
				   			<input type="hidden" name="returnUrl" value="/seadm/popup/modify.do">
							<input type="hidden" name="targetFormId" value="">
							<input type="hidden" name="selectedId" value="${info.PP_SN}" />
				   			<c:import url="/cmm/fms/selectFileInfsForUpdate.do" >
						    	<c:param name="param_atchFileId" value="${info.PP_FILE2}" />
						    	<c:param name="param_fixFileSn" value="0" />
						    	<c:param name="param_lockFileSn" value="1" />
						    </c:import>
					    </form:form>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >이미지(모바일) 2<br>( 팝업:이미지크기반영 )<br>( 배너:600 * 380 권장 )</label>
		           	<div class="col-md-10">
		           		<div class="input-group input-group-file" data-plugin="inputGroupFile">
							<input type="text" class="form-control bg-grey-200" value="" disabled/>
			     			<span class="input-group-btn">
			       				<span class="btn btn-success btn-file">
			         				<i class="icon wb-upload" aria-hidden="true"></i>
			         				<input name="file_mobile_2" type="file" />
			       				</span>
			     			</span>
			   			</div>
			   			<form:form commandName="a">
				   			<input type="hidden" name="returnUrl" value="/seadm/popup/modify.do">
							<input type="hidden" name="targetFormId" value="">
							<input type="hidden" name="selectedId" value="${info.PP_SN}" />
				   			<c:import url="/cmm/fms/selectFileInfsForUpdate.do" >
						    	<c:param name="param_atchFileId" value="${info.PP_FILE2}" />
						    	<c:param name="param_fixFileSn" value="1" />
						    	<c:param name="param_lockFileSn" value="0" />
						    </c:import>
					    </form:form>
					</div>
				</div>
				
				
			</div>
		</div>
			
		<h4 class="blue-600">팝업전용</h4>	
		<div class="panel">
			<div class="panel-body">
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >팝업위치설정</label>
		           	<div class="col-md-10">
		           		<div class="input-group col-md-6 pl-0">
		            		<input type="text" class="form-control" id="PP_TOP" name="PP_TOP" placeholder="상단으로 부터" autocomplete="off" value="${info.PP_TOP}" >
		            		<div class="input-group-append">
			           			<span class="input-group-text">상단 (기본값:20px)</span> 
			           		</div>
		            	</div>
		            	<div class="input-group col-md-6 mt-2 pl-0">
		            		<input type="text" class="form-control" id="PP_LEFT" name="PP_LEFT" placeholder="좌측으로 부터" autocomplete="off" value="${info.PP_LEFT}" >
		            		<div class="input-group-append">
			           			<span class="input-group-text">좌측 (기본값:50%)</span> 
			           		</div>
		            	</div>
		            	<br/><code class="white border-0 bg-blue-600">팝업위치설정은 배너 카테고리를 선택하면 기입하지 않아도 됩니다.</code>
					</div>
				</div>
			</div>
		</div>		
				
		<div class="panel">
			<div class="panel-body">				
				
				<div class="form-group row">
					<label class="col-md-2 form-control-label">사용여부</label>
					<div class="col-md-10">
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
				<div class="text-center /*float-middle*">
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
$(function(){
	$('.selectpicker_manual').selectpicker();
});
$(function(){
	$('.datepicker_manual_1').datepicker({
	    format: 'yyyy-mm-dd 00:00:00',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
	$('.datepicker_manual_2').datepicker({
	    format: 'yyyy-mm-dd 23:59:59',
	    //startDate: '0d',
	    autoclose: true,
	    language: "kr",
	});
});
function frm_submit() {
	if(!$.trim($('#PP_TITLE').val())) { 
		alert("제목을 입력하여 주세요."); 	
		$('#PP_TITLE').focus(); 
		return false; 
	}
	oEditors.getById["PP_CONT"].exec("UPDATE_CONTENTS_FIELD", []); $("#frm").submit();
	return true;
}	
</script>

<%@ include file="../tail.jsp" %>
      