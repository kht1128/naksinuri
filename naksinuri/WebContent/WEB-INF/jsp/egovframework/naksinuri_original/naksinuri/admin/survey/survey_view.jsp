<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="depthName" value="survey" />
<c:set var="pageName" value="survey" />
<c:set var="anID" value="0" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">
<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data">
<input type="hidden" name="sv_id" id="sv_id" value="${info.sv_id}" />
	<div id="container">
		<div id="content">
			<section id="suveyPage" class="table-box">
				<c:if test="${info.sv_strt_dt ne null }">
					<h3>설문조사 글보기</h3>
				</c:if>
				<c:if test="${info.sv_strt_dt eq null }">
					<h3>설문조사 등록하기</h3>
				</c:if>
				<div class="padding_box">
					<c:if test="${info.sv_strt_dt ne null }">
						<div class="survey_view">
							<c:forEach var="result" items="${aan}" varStatus="status" begin="0" end="0">
								<h4>${info.sv_subject} <span class="date">(${info.sv_strt_dt} ~ ${info.sv_end_dt} / 총 응답자 수 : <span style="color:yellow">${result.survey_anscnt}</span>명)</span> [<c:if test="${info.sv_show eq '0'}">기간내+기간만료 포함공개</c:if><c:if test="${info.sv_show eq '1'}">기간만료 후 공개</c:if><c:if test="${info.sv_show eq '2'}">비공개</c:if>]</h4>
								<c:if test="${result.survey_anscnt eq '0'}">
									<%-- <c:set var="survey_people" value="1" /> --%>
									
								</c:if>
								<c:if test="${result.survey_anscnt ne '0' }">
								<%-- 	<c:set var="survey_people" value="${result.survey_anscnt}" /> --%>
									<c:set var="survey_people" value="${result.survey_anscnt+300}" />
									
								</c:if>
							</c:forEach>
							<div class="suveybox">
								<c:forEach var="item" items="${quest}">
									<c:if test="${item.svq_type eq 'S' }">
										<dl>
											<dt><em class="num"><fmt:formatNumber var="no" minIntegerDigits="2" value="${item.svq_num}" type="number"/>${no}.</em><span class="subject">${item.svq_subject}<span class="type">(주관식)</span></span></dt>
											<dd>
												<div class="subjective">
													<c:forEach var="answerole" items="${answerole}">
														<c:if test="${answerole.sq_id eq item.sq_id}">
															<div class="subjective_con">
																<c:if test="${answerole.as_name ne null}">
																[이름:${answerole.as_name}][이메일:${answerole.as_email}][연락처:${answerole.as_tel}]
																</c:if>
																<c:if test="${answerole.as_name eq null}">
																	<b>익명</b>
																</c:if>
																<br/><br/>
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
																<div class="multiple_subject">${item2.svq_item_txt } (${item2.selector }명 선택${survey_people})</div>
																<div class="progress">
																	<div class="prograss-bar" style="width:${item2.selector/survey_people*100}%"></div>
																	<c:out value="${survey_people}" />
													
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
						</div>
					</c:if>
					<c:if test="${info.sv_strt_dt eq null }">
						<table class="t_write" id="ftable">
							<colgroup>
								<col width="150" />
								<col />
							</colgroup>
							<tbody>							
								<tr>
									<th>글쓴이</th>
									<td><input type="text" name="reg_mb_id" id="reg_mb_id" class="frm_input" value="${info.reg_mb_id}"  /></td>
								</tr>							
								<tr>
									<th>설문시작일</th>
									<td><input type="text" name="sv_strt_dt" id="sv_strt_dt" class="frm_input" value="${info.sv_strt_dt}"/>~
										<input type="text" name="sv_end_dt" id="sv_end_dt" class="frm_input" value="${info.sv_end_dt}"/>
									</td>
								</tr>
								<tr>
									<th>사용자 공개여부</th>
									<td>
										<select id="sv_show" name="sv_show" value="${info.sv_show}">
											<option value="0"<c:if test="${info.sv_show eq '0'}">selected</c:if>>기간내+기간만료 포함공개</option>
											<option value="1"<c:if test="${info.sv_show eq '1'}">selected</c:if>>기간만료후 공개</option>
											<option value="2"<c:if test="${info.sv_show eq '2'}">selected</c:if>>비공개</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>설문 구분</th>
									<td>
										<select id="SV_LC" name="SV_LC">
											<option value="naksinuri">낚시누리</option>
											<option value="online_edu">온라인교육이수</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>정보수집 여부</th>
									<td>
										<input type="checkbox" id="chk_private" />
										<label for="chk_private">사용자의 정보를 수집합니다.</label>
										<input type="hidden" id="sv_private_info" name="sv_private_info" value="0"/>
									</td>
								</tr>
								<tr>
									<th>설문제목</th>
									<td><input type="text" name="sv_subject" id="sv_subject" class="frm_input" value="${info.sv_subject}" style="WIDTH: 100%;"/></td>
								</tr>
								<tr>
									<th align="top">설문항목<br/><br/>
										<a href="#;" onclick="addQ(this)" class="btn_size btn_violet">항목추가</a>
									</th>
									<td class="svq_list">
									<c:forEach var="result" items="${aan}" varStatus="status" begin="0" end="0">
											총 응답자 수  : ${result.survey_anscnt} 명<br><br>	
									</c:forEach>
																
										<c:forEach var="item" items="${quest}">
											<div class="svq_listAtt">
												<input type="hidden" name="sq_id" value="${item.sq_id}">
												<input type="hidden" name="anID" class="anID" value="${anID}">
												<select name="svq_type${item.sq_id}" id="svq${item.sq_id}" onchange="typeChange(this)">
													<option value="S" <c:if test="${item.svq_type eq 'S' }">selected</c:if>>주관식</option>
													<option value="O" <c:if test="${item.svq_type eq 'O' }">selected</c:if>>객관식</option>
												</select>
												<input type="text" name="svq_subject${item.sq_id}" value="${item.svq_subject }" class="frm_input" size="70" title="" placeholder="">
												<c:if test="${item.svq_mxcnt gt 1 }">최대 중복 선택수 : ${item.svq_mxcnt }개 </c:if>	
	<!-- 											<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="removeQ(this)">항목삭제</a> -->
	<!-- 											<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="preQ(this)">△</a> -->
	<!-- 											<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="nextQ(this)">▽</a><br> -->
												<!-- 주관식일때 텍스트 에리어 생성 -->
												<c:if test="${item.svq_type eq 'S' }">
													<br>
													<textarea rows="10" cols="80%"><c:forEach var="answerole" items="${answerole}"><c:if test="${answerole.sq_id eq item.sq_id}">${answerole.sva_txt}
													</c:if></c:forEach></textarea><br><br>
												</c:if>
												<c:if test="${item.svq_type eq 'O' }">
													<div class="svqi_list">
	<!-- 													<a href="#;" id="btn_add_svq1" class="btn_frmline btn_violet"  onclick="SubAddQ(this)">응답 추가</a> -->
	<!-- 													최대 중복 선택수  -->
	<%-- 													<select name="svqi_mxsel${anID}" class="svqi_mxsel"> --%>
	<!-- 															<option value=1>1개</option>					 -->
	<!-- 													</select> -->
														<ul class="subList">
															<c:forEach var="item2" items="${qitem}">
																<c:if test="${item2.sq_id eq item.sq_id }">
																	<li>
																		<input type="hidden" name="svq_svqi_id${item2.svq_item_num}" value="" class="frm_input" size="100">
																		<input type="hidden" name="svq_svqi_etc${item2.svq_item_num}" value="9" class="svq_svqi_etc" size="100">
																		<input type="text" name="svq_svqi_txt${item2.svq_item_num}" value="${item2.svq_item_txt }" class="frm_input" size="100"><br>
																		<label for="etck${item2.svq_item_num}">기타의견 작성</label>	<input type="checkbox" onclick="etcCk(this)" id="etck${item2.svq_item_num}" <c:if test="${item2.sqi_etc eq 0 }">checked</c:if>>
	<!-- 																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="SubRemoveQ(this)">항목삭제</a> -->
	<!-- 																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubPreQ(this)">△</a> -->
	<%-- 																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubNextQ(this)">▽</a>--%> &nbsp;&nbsp;${item2.selector }명 선택 
																		<br><br>
																		<c:if test="${item2.sqi_etc eq 0 }">
																			<textarea rows="10" cols="80%"><c:forEach var="answerole" items="${answerole}"><c:if test="${answerole.sq_id eq item.sq_id}">${answerole.sva_txt}
																			</c:if></c:forEach></textarea><br><br>
																		</c:if>
																																	
																	</li>				
																</c:if>
															</c:forEach>
														</ul>
													</div>
												</c:if>
												<c:if test="${item.svq_type eq 'S' }">
													<div class="svqi_list" style="display:none">
														<a href="#;" id="btn_add_svq1" class="btn_frmline btn_violet"  onclick="SubAddQ(this)">응답 추가</a>
														최대 중복 선택수 
														<select name="svqi_mxsel${anID}" class="svqi_mxsel">
																<option value=1>1개</option>					
														</select>
														<ul class="subList" id="multiple_choice">
															<li>
																<input type="hidden" name="svq_svqi_id${anID}" value="" class="frm_input" size="100">
																<input type="hidden" name="svq_svqi_etc${anID}" value="9" class="svq_svqi_etc" size="100">
																<input type="text" name="svq_svqi_txt${anID}" value="" class="frm_input" size="100"><br>
											
																<label for="etck${anID}">기타의견 작성</label>	<input type="checkbox" onclick="etcCk(this)" id="etck${anID}">
																<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="SubRemoveQ(this)">항목삭제</a>
																<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubPreQ(this)">△</a>
																<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubNextQ(this)">▽</a><br><br>															
															</li>				
														</ul>
													</div>
												</c:if>
											</div><br><br>
										</c:forEach>
									</td>
								</tr>	
							</tbody>
						</table>
					</c:if>	
				</div>
			</section>

			<div class="btn_area textcenter">
				<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.bo_trash eq '1' }">
					<a href="/admin/${depthName}/${pageName}/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.bo_trash eq '0' && info.sv_id ne null}">
					<a href="#" onclick="clk_update_data('${info.sv_id}')"class="btn_size2 btn_bordergray"}">수정</a>
					<a href="#;" class="btn_size2 btn_bordergray clk_copy_data" data-sv-id="${info.sv_id}" data-linkurl="/admin/${depthName}/${pageName}/copy_data.do">복사</a>
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">목록</a>
<!-- 				<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" /> -->
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.sv_id eq null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="insertInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>		
			</div>
		</div>
	</div>
</form:form>
	<!-- 하단 푸터 { -->
	<footer id="footer" class="floats">
		<div class="l_version">
			No Background Tasks <em>Version 4.4.0.5</em>
		</div>
		<div class="r_copyright">
			<b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.
		</div>
	</footer>
	<!-- } 하단 푸터 -->
</div>
</body>
</html>

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
    	 if($('#sv_id').val() ==''){
				$("#imform").attr("action","/admin/"+depthName+"/"+pageName+"/insert_data.do");				
				document.getElementById("imform").submit();					
			}
    	 
    } catch(e) {
     
    }
}

function clk_update_data(sv_id){
	var form = document.getElementById('imform');
	$('#sv_id').val(sv_id);
	
	form.action="/admin/${depthName}/${pageName}/update_data.do";
	form.submit();
};      
      
$(".clk_copy_data").click(function() {
	var form = document.getElementById('imform');
	form.sv_id.value = $(this).attr('data-sv-id');
	form.action = '';
	var data_sv_id = $(this).attr('data-sv-id');
	var data_linkurl = $(this).attr('data-linkurl');
	
	$.ajax({
		type:"POST",
		url :data_linkurl,
		data:$('#imform').serialize(),
		dataType: 'html',
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr){
				alert("해당 설문조사가 복제(복사)되었습니다.");
				window.location.href = '/admin/${depthName}/${pageName}/list.do';
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			window.location.href = '/admin/${depthName}/${pageName}/list.do';
		}
	});
});
 

$(document).ready(function(){
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
	 
		

 });
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
	
	/* if($(obj).parents(".svq_listAtt").find(".subList li").length>4){
		alert('객관식 응답은 최대 5개 까지만 생성할 수 있습니다.');
		return false;
	} */
		
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
