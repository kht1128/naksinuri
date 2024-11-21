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
<style>
.svq_listAtt{padding:15px 0px;}
</style>
<body oncontextmenu="return false;">
<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data">
<input type="hidden" name="sv_id" id="sv_id" value="${info.sv_id}" />
	<div id="container">
		<div id="content">
			<section id="suveyPage" class="table-box">
				<h3>설문조사 수정하기</h3>
				<c:forEach var="result" items="${aan}" varStatus="status" begin="0" end="0">
<%-- 					<h3>총 응답자 수  : ${result.survey_anscnt} 명</h3><br><br>	 --%>
					<input type="hidden" name="survey_anscnt" id="survey_anscnt" value="${result.survey_anscnt}" />
				</c:forEach>
				<div class="padding_box">
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
									<c:forEach var="item" items="${quest}">
										<div class="svq_listAtt">
											<input type="hidden" name="sq_id${anID}" value="${item.sq_id}">
											<input type="hidden" name="anID" class="anID" value="${anID}">
											<select name="svq_type${anID}" id="svq${anID}" onchange="typeChange(this)">
												<option value="S" <c:if test="${item.svq_type eq 'S' }">selected</c:if>>주관식</option>
												<option value="O" <c:if test="${item.svq_type eq 'O' }">selected</c:if>>객관식</option>
											</select>
											<input type="text" name="svq_subject${anID}" value="${item.svq_subject }" class="frm_input" size="70" title="" placeholder="">
											<c:if test="${item.svq_mxcnt gt 1 }">최대 중복 선택수 : ${item.svq_mxcnt }개 </c:if>	
											<a href="#;" class="btn_frmline curPoin btn_violet" onclick="removeQ(this)">항목삭제</a>
											<a href="#;" class="btn_frmline curPoin" onclick="preQ(this)">△</a>
											<a href="#;" class="btn_frmline curPoin" onclick="nextQ(this)">▽</a>
											<!-- 주관식일때 텍스트 에리어 생성 -->
											<c:if test="${item.svq_type eq 'S' }">
												<br>
												<textarea rows="10" cols="80%"><c:forEach var="answerole" items="${answerole}"><c:if test="${answerole.sq_id eq item.sq_id}">${answerole.sva_txt}
												</c:if></c:forEach></textarea><br><br>
											</c:if>
											<c:if test="${item.svq_type eq 'O' }">
												<div class="svqi_list">
													<a href="#;" id="btn_add_svq1" class="btn_frmline btn_violet"  onclick="SubAddQ(this)">응답 추가</a>
													최대 중복 선택수 
													<select name="svqi_mxsel${anID}" class="svqi_mxsel">
															<option value=1>1개</option>					
													</select>
													<ul class="subList">
														<c:forEach var="item2" items="${qitem}">
															<c:if test="${item2.sq_id eq item.sq_id }">
																<li>
																	<input type="hidden" name="svq_svqi_id${anID}" value="${item2.sqi_id }" class="frm_input" size="100">
																	<input type="hidden" name="svq_svqi_etc${anID}" value="9" class="svq_svqi_etc" size="100">
																	<input type="text" name="svq_svqi_txt${anID}" value="${item2.svq_item_txt }" class="frm_input" size="100"><br>
																	<label for="etck${item2.svq_item_num}">기타의견 작성</label>	<input type="checkbox" onclick="etcCk(this)" id="etck${item2.svq_item_num}" <c:if test="${item2.sqi_etc eq 0 }">checked</c:if>>
																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="SubRemoveQ(this)">항목삭제</a>
																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubPreQ(this)">△</a>
 																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubNextQ(this)">▽</a> &nbsp;&nbsp;${item2.selector }명 선택 
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
															<input type="hidden" name="svq_svqi_id${anID}" value="${item2.sqi_id }" class="frm_input" size="100">
															<input type="hidden" name="svq_svqi_etc${anID}" value="9" class="svq_svqi_etc" size="100">
															<input type="text" name="svq_svqi_txt${anID}" value="${item2.svq_item_txt }" class="frm_input" size="100"><br>
										
															<label for="etck${anID}">기타의견 작성</label>	<input type="checkbox" onclick="etcCk(this)" id="etck${anID}">
															<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="SubRemoveQ(this)">항목삭제</a>
															<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubPreQ(this)">△</a>
															<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubNextQ(this)">▽</a><br><br>															
														</li>				
													</ul>
												</div>
											</c:if>
										</div>
										<c:set var="anID" value="${anID+1}" />
									</c:forEach>
								</td>
							</tr>	
						</tbody>
					</table>
				</div>
			</section>
			
			<div class="btn_area textcenter">
				<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
				<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
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
		try {
			if ($('#survey_anscnt').val() == '0') {
				$("#imform").attr(
						"action",
						"/admin/" + depthName + "/" + pageName+ "/update_act.do");
				document.getElementById("imform").submit();
			} else {
				alert('응답자가 있을 경우 수정이 불가합니다.');
				location.href = "/admin/" + depthName + "/" + pageName+ "/list.do";
			}

		} catch (e) {

		}
	}

	$(document).ready(function() {
		$('#sv_strt_dt').datepicker({
			dateFormat : 'yy-mm-dd',
			showOn : 'focus',
			changeMonth : true,
			changeYear : true,
			showButtonPanel : true,
			yearRange : 'c-99:c+99',
			//minDate: 0
			onClose : function(selectedDate) {
				// 시작일(fromDate) datepicker가 닫힐때
				// 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
				$("#sv_end_dt").datepicker("option", "minDate", selectedDate);
			}
		});
		$('#sv_end_dt').datepicker({
			dateFormat : 'yy-mm-dd',
			showOn : 'focus',
			changeMonth : true,
			changeYear : true,
			showButtonPanel : true,
			yearRange : 'c-99:c+99',
			//minDate: 0
			onClose : function(selectedDate) {
				// 종료일(toDate) datepicker가 닫힐때
				// 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
				$("#sv_strt_dt").datepicker("option", "maxDate", selectedDate);
			}
		});

	});
	//문제추가
	function addQ(obj) {
		anID++;
		$.ajax({
			url : '/admin/survey/survey_update_ajax.do',
			data : {
				Type : "main",
				anID : anID
			},
			type : 'POST',
			success : function(result) {
				$(".svq_list").append(result);
			},
			error : function(result) {
				alert('서비스 오류가 발생했습니다. 페이지를 새로고침 해주세요!');
			}
		});
	}

	function removeQ(obj) {
		if ($(".svq_listAtt").size() == "1") {
			alert('설문항목은 최소 1개 이상 있어야 합니다.');
			return false;
		} else {
			$(obj).parent().remove();
		}
	}//설문 항목 지우기

	function preQ(obj) {
		var obIndex = $(obj).parent().index();

		if (obIndex != 0) {
			$(".svq_listAtt").eq(obIndex).insertBefore(
					$(".svq_listAtt").eq(obIndex - 1));
		}

	}
	function nextQ(obj) {
		var obIndex = $(obj).parent().index();
		var obIndexSize = $(".svq_listAtt").size();

		if ((obIndex + 1) != obIndexSize) {
			$(".svq_listAtt").eq(obIndex).insertAfter(
					$(".svq_listAtt").eq(obIndex + 1));
		}
	}
	function dupOptionAdd(obj) {
		var subListSize = obj.find(".subList").find("li").size();
		var createOption = "";
		var subListSelect = obj.find(".svqi_mxsel");

		for (var i = 1; i <= subListSize + 1; i++) {
			if (subListSelect.val() == i) {
				createOption += '<option value="'+i+'" selected>' + i
						+ '개</option>';
			} else {
				createOption += '<option value="'+i+'" >' + i + '개</option>';
			}
		}
		subListSelect.html(createOption);
	}

	function SubAddQ(obj) {

		/* if($(obj).parents(".svq_listAtt").find(".subList li").length>4){
			alert('객관식 응답은 최대 5개 까지만 생성할 수 있습니다.');
			return false;
		} */

		$.ajax({
			url : '/admin/survey/survey_ajax.do',
			data : {
				Type : "sub",
				anID : $(obj).parents(".svq_listAtt").find(".anID").val()
			},
			type : 'POST',
			success : function(result) {
				$(obj).parent().find(".subList").append(result);
			},
			error : function(result) {
				alert('서비스 오류가 발생했습니다. 페이지를 새로고침 해 주세요!');
			}
		});
		dupOptionAdd($(obj).parent());

	}

	function SubRemoveQ(obj) {
		var thisRev = $(obj).parents().parents(".svqi_list");
		var suqQsize = thisRev.find(".subList").find("li").size();
		if (suqQsize > 1) {
			$(obj).parent().remove();
			dupOptionAdd(thisRev);
		} else {
			alert("최소 문항은 1개 이상 있어야 합니다.");
		}
	}
	function SubPreQ(obj) {
		var obIndex = $(obj).parent().index();
		var thisRev = $(obj).parents().parents(".svqi_list");
		var suqQsize = thisRev.find(".subList").find("li").size();
		thisRev.find("li").eq(obIndex).insertBefore(
				thisRev.find("li").eq(obIndex - 1));

	}
	function SubNextQ(obj) {
		var obIndex = $(obj).parent().index();
		var thisRev = $(obj).parents().parents(".svqi_list");
		var suqQsize = thisRev.find(".subList").find("li").size();
		if ((obIndex + 1) != suqQsize) {
			thisRev.find("li").eq(obIndex).insertAfter(
					thisRev.find("li").eq(obIndex + 1));
		}

	}

	function etcCk(obj) {
		if ($(obj).is(":checked")) {
			$(obj).parent().find(".svq_svqi_etc").val("0");
		} else {
			$(obj).parent().find(".svq_svqi_etc").val("9");
		}
	}

	function typeChange(obj) {
		var select_type = $(obj).val();
		if (select_type == "O") {
			$(obj).parent().find(".svqi_list").show();
		}
		if (select_type == "S") {
			$(obj).parent().find(".svqi_list").hide();
		}
	}
</script>