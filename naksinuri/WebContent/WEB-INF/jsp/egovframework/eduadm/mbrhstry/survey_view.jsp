<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>
<%-- <%@ taglib prefix="validator" 	uri="http://www.springmodules.org/tags/commons-validator" --%>

<c:set var="depthNum" value="3" />
<c:set var="pageNum" value="1" />

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<style>
	/* .suveybox{padding:20px;} */
	.panel-body {padding-top: 0;}
	.suveybox dl{padding-top:20px;margin-top:20px;border-top:1px dashed #dcdddd}
	.suveybox dl:first-child{padding-top:0;margin-top:0px;border-top:0}
	.suveybox dl dt{display:table;margin-bottom:10px;font-size:17px;}
	.suveybox dl dt em.num{display:table-cell;width:40px;font-size:22px;}
	.suveybox dl dt span.subject{display:table-cell;font-weight:bold}
	.suveybox dl dt span.subject span.type{font-size:16px;font-weight:normal;color:#888}
	.suveybox dl dd{padding-left:40px;color:#666}
	.suveybox dl dd .btns{display:inline-block;height:26px;line-height:26px;padding:0 10px;background-color:#0099cc;border-radius:3px;color:#fff;font-size:14px;font-weight:normal}
	.suveybox dl dd .subjective{border:1px solid #e0e0e0}
	.suveybox dl dd .subjective .subjective_con{padding:5px;border-top:1px dashed #dcdddd}
	.suveybox dl dd .subjective .subjective_con:first-child{border-top:none}
	.suveybox dl dd .subjective .subjective_con:nth-child(2n){background-color:#fafafa}
	.suveybox dl dd ul {list-style: none;padding-left: 0px;}
	.suveybox dl dd ul.multiple > li{margin-top:10px}
	.suveybox dl dd ul.multiple > li .multiple_subject{font-weight:bold;font-size:15px}
	.suveybox dl dd ul.multiple > li .progress{position:relative;overflow:hidden;height:20px;background-color:#f7f7f7;margin-top:5px;border-radius:10px;}
	.suveybox dl dd ul.multiple > li .progress .prograss-bar{height:20px;line-height:20px;background-color:#66cccc}
	.suveybox dl dd ul.multiple > li .progress .percent{position:absolute;top:0px;right:20px;line-height:20px;font-weight:bold}
	.suveybox dl dd ul.multiple > li .subjective{margin-left:30px;margin-top:5px}
	
</style>


<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data">
<div class="page">
<input type="hidden" name="sv_id" id="sv_id" value="${info.sv_id}" />
<input type="hidden" name="ETC_1" id="ETC_1" value="${CRS_SN}" />
<input type="hidden" name="ASI_CRS_SN" id="ASI_CRS_SN" value="${CRS_SN}" />

	
<c:set var="subpageNum" value="3"/>
<c:set var="depthName" value="교육관리"/>
<c:set var="pageName" value="교육목록"/>
<c:set var="subpageName" value="설문조사"/>
<c:set var="pageUrl" value=""/>
<!-- URL 추가 해야 하나? -->
	
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
      </div>

	<div class="page-content container-fluid">
	<!-- <section id="suveyPage" class="table-box"> -->
		<div class="panel">
			<%-- <c:if test="${info.sv_strt_dt ne null }"> --%>
			<c:choose>
				<c:when test="${empty info}">
					<div class="panel-heading"><h4 class="panel-title">진행중인 설문조사가 없습니다.</h4></div>
				</c:when>
				<c:otherwise>
				<div class="panel-heading">
					<c:forEach var="result" items="${aan}" varStatus="status" begin="0" end="0">
						<h4 class="panel-title">${info.sv_subject} <span class="date">(${info.sv_strt_dt} ~ ${info.sv_end_dt} / 총 응답자 수 : <span class="red-600">${result.survey_anscnt}</span>명)</span> [<c:if test="${info.sv_show eq '0'}">기간내+기간만료 포함공개</c:if><c:if test="${info.sv_show eq '1'}">기간만료 후 공개</c:if><c:if test="${info.sv_show eq '2'}">비공개</c:if>]</h4>
						<c:if test="${result.survey_anscnt eq '0'}">
							<c:set var="survey_people" value="1" />
						</c:if>
						<c:if test="${result.survey_anscnt ne '0' }">
							<c:set var="survey_people" value="${result.survey_anscnt}" />
						</c:if>
					</c:forEach>
				</div>
				<div class="panel-body suveybox">
					<c:forEach var="item" items="${quest}">
						<c:if test="${item.svq_type eq 'S' }">
							<dl>
								<dt><em class="num"><fmt:formatNumber var="no" minIntegerDigits="2" value="${item.svq_num}" type="number"/>${no}.</em><span class="subject">${item.svq_subject}<span class="type">(주관식)</span></span></dt>
								<dd>
									<div class="subjective">
										<c:forEach var="answerole" items="${answerole}">
											<c:if test="${answerole.sq_id eq item.sq_id}">
												<div class="subjective_con">
													<%-- <c:if test="${answerole.as_name ne null}">
													[이름:${answerole.as_name}][이메일:${answerole.as_email}][연락처:${answerole.as_tel}]
													</c:if>
													<c:if test="${answerole.as_name eq null}">
														<b>익명</b>
													</c:if>
													<br/><br/> --%>
													${answerole.sva_txt}
												</div>
											</c:if>
										</c:forEach>
									</div>
								</dd>
							</dl>
						</c:if>
						<c:if test="${item.svq_type eq 'O' }">
							<dl>
								<dt><em class="num"><fmt:formatNumber var="no" minIntegerDigits="2" value="${item.svq_num}" type="number"/>${no}.</em><span class="subject">${item.svq_subject}<span class="type">(객관식<c:if test="${item.svq_mxcnt gt 1 }"> - 중복선택 ${item.svq_mxcnt}개 이하</c:if>)</span></span></dt>
								<dd>
									<ul class="multiple">
										<c:forEach var="item2" items="${qitem}">
											<c:if test="${item2.sq_id eq item.sq_id }">
												<li>
													<div class="multiple_subject">${item2.svq_item_txt } (${item2.selector }명 선택)</div>
													<div class="progress">
														<div class="prograss-bar" style="width:${item2.selector/survey_people*100}%"></div>
														<span class="percent"><fmt:formatNumber value="${item2.selector/survey_people*100}" pattern="0.00"/>%</span>
													</div>
													<c:if test="${item2.sqi_etc eq 0 }">
														<div class="subjective">
															<c:forEach var="answerole" items="${answerole}">
																<c:if test="${answerole.sq_id eq item.sq_id}">
																	<div class="subjective_con">${answerole.sva_txt}</div>
																</c:if>
															</c:forEach>
														</div>
													</c:if>
												</li>
											</c:if>
										</c:forEach>													
									</ul>
								</dd>
							</dl>
						</c:if>
					</c:forEach>
					<dl>
						<dd>
						<a href="#;" onclick="go_excel()" class="btns">엑셀 다운로드</a>
						</dd>
					</dl>
					
				</div>
				</c:otherwise>
			</c:choose>
		</div>
			<%-- </c:if> --%>
	<!-- </section> -->

		<%-- 	<div class="btn_area textcenter">
				<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.bo_trash eq '1' }">
					<a href="/admin/${depthName}/${pageName}/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.bo_trash eq '0' && info.sv_id ne null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">목록</a>
<!-- 					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" /> -->
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.sv_id eq null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>		
		</div> --%>
	</div>
</div>
</form:form>


<script type="text/javaScript" language="javascript">


function go_excel(){
	var form = document.getElementById("imform");
	
	form.action = "/admin/listExcelDownload.do";
	form.submit();
}

$("#chk_private").change(function() {
	 if(this.checked) {
		  $('#sv_private_info').val(1);
	  }else{
		  $('#sv_private_info').val(0);
	  }
	});

	var depthName = "${depthName}";
	var pageName = "${pageName}";
	var anID = "${anID}";

	
      //전송버튼 클릭이벤트
	function submitContents() {
    	

    // 에디터의 내용에 대한 값 검증은 이곳에서
     try {
    	 if($('#sv_id').val()!=''){
				$("#imform").attr("action","/admin/"+depthName+"/"+pageName+"/update_data.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/"+depthName+"/"+pageName+"/insert_data.do");				
				document.getElementById("imform").submit();					
			}
    	 
    } catch(e) {
     
    }
   
    
}



/* $(document).ready(function(){
	$('#sv_strt_dt').datepicker({
		dateFormat : 'yy-mm-dd', 
		showOn: 'focus',
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true,
		yearRange: 'c-99:c+99',
		//minDate: 0
		onClose: function( selectedDate ) {    
			// 시작일(fromDate) datepicker가 닫힐때
			// 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
			$("#sv_end_dt").datepicker( "option", "minDate", selectedDate );
		}    });
	$('#sv_end_dt').datepicker({
		dateFormat: 'yy-mm-dd',
		showOn: 'focus',
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true,
		yearRange: 'c-99:c+99',
		//minDate: 0
		onClose: function( selectedDate ) {    
			// 종료일(toDate) datepicker가 닫힐때
			// 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
			$("#sv_strt_dt").datepicker( "option", "maxDate", selectedDate );
		}    
	});
	 
		

 }); */
 //문제추가
 function addQ(obj){
	 anID++;
	 $.ajax({
		url: '/admin/survey/survey_ajax.do',
		data: {Type:"main",anID:anID},
		type: 'POST',
		success: function(result){
			$(".svq_list").append(result);
		},
		error:function(result){
			alert('서비스 오류가 발생했습니다. 페이지를 새로고침 해주세요!');
		}
	 });
 }
 
 function removeQ(obj){
	 if($(".svq_listAtt").size()=="1"){
		 alert('설문항목은 최소 1개 이상 있어야 합니다.');
		 return false;
	 }else{
		 $(obj).parent().remove();
	 }
 }//설문 항목 지우기
 
 function preQ(obj)
 {
 	var obIndex=$(obj).parent().index();
 	
 	if(obIndex!=0){	
 		$(".svq_listAtt").eq(obIndex).insertBefore($(".svq_listAtt").eq(obIndex-1));	
 	}

 }
 function nextQ(obj)
 {
 	var obIndex=$(obj).parent().index();
 	var obIndexSize=$(".svq_listAtt").size();

 	if((obIndex+1)!=obIndexSize){	
 		$(".svq_listAtt").eq(obIndex).insertAfter($(".svq_listAtt").eq(obIndex+1));	
 	}
 }
 function dupOptionAdd(obj){
	 var subListSize = obj.find(".subList").find("li").size();
	 var createOption = "";
	 var subListSelect = obj.find(".svqi_mxsel");
	 
	 for(var i=1;i<=subListSize+1;i++){
		 if(subListSelect.val()==i){
			 	createOption+='<option value="'+i+'" selected>'+i+'개</option>';
			}else{
				createOption+='<option value="'+i+'" >'+i+'개</option>';
			}
		}
		subListSelect.html(createOption);
	}

 function SubAddQ(obj)
 {
	
	if($(obj).parents(".svq_listAtt").find(".subList li").length>4){
		alert('객관식 응답은 최대 5개 까지만 생성할 수 있습니다.');
		return false;
	}else{
		
 	$.ajax({
 		url: '/admin/survey/survey_ajax.do',
 		data: {Type:"sub",anID:$(obj).parents(".svq_listAtt").find(".anID").val()},
 		type: 'POST',
 		success: function(result) {			
 			$(obj).parent().find(".subList").append(result);				
 		}, 
 		error: function(result) {
 			alert('서비스 오류가 발생했습니다. 페이지를 새로고침 해 주세요!');
 		}
 	});	
 	dupOptionAdd($(obj).parent());
	}
}
 
 function SubRemoveQ(obj)
 {	
 	var thisRev=$(obj).parents().parents(".svqi_list");
 	var suqQsize=thisRev.find(".subList").find("li").size();
 	if(suqQsize>1){
 		$(obj).parent().remove();
 		dupOptionAdd(thisRev);
 	}else{
 		alert("최소 문항은 1개 이상 있어야 합니다.");
 	}
 }
 function SubPreQ(obj)
 {
 	var obIndex=$(obj).parent().index();
 	var thisRev=$(obj).parents().parents(".svqi_list");
 	var suqQsize=thisRev.find(".subList").find("li").size();
 	thisRev.find("li").eq(obIndex).insertBefore(thisRev.find("li").eq(obIndex-1));
 	
 }
 function SubNextQ(obj)
 {
 	var obIndex=$(obj).parent().index();
 	var thisRev=$(obj).parents().parents(".svqi_list");
 	var suqQsize=thisRev.find(".subList").find("li").size();
 	if((obIndex+1)!=suqQsize){	
 		thisRev.find("li").eq(obIndex).insertAfter(thisRev.find("li").eq(obIndex+1));
 	}


 }
 
 function etcCk(obj){
		if($(obj).is(":checked")){
			$(obj).parent().find(".svq_svqi_etc").val("0");
		}else{
			$(obj).parent().find(".svq_svqi_etc").val("9");
		}
	}
 
 function typeChange(obj){
		var select_type=$(obj).val();
		if(select_type=="O"){
			$(obj).parent().find(".svqi_list").show();
		}
		if(select_type=="S"){
			$(obj).parent().find(".svqi_list").hide();
		}
	}

</script>
<%@ include file="../tail.jsp" %>