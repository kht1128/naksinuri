<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="customer_sound"/>
<c:set var="depthName" value="사이트이용안내"/>
<c:set var="pageName" value="고객의 소리"/>

<%@include file="../../layout/m/head.jsp"%>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="voc_no" id="voc_no"/>
</form:form>
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<%-- <input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
<input type="hidden" name="bo_sido" id="bo_sido" value="${bo_sido}"/> --%>
<c:set var="pagesize" value="${select_total/pageUnit}"/>

	<div id="fishjobList" class="content respon2">
		<section id="webzineList" class="list_box">
			<div class="list_searchbox">
				<!-- 토탈페이징  -->
				<fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
			</div>
			
			<div class="board_list">
				<table class="list_tbl">
				<caption>고객의 소리</caption>
					<colgroup>
						<col width='10%'/>
						<col width='*' />
						<col width='10%'/>
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>답변여부</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty voc_list}">
							<tr><td colspan="5" class="text-center table-active">등록 된 게시물이 없습니다.</td></tr>
						</c:if>
						<c:forEach var="item" varStatus="status" items="${voc_list}">
							<tr>
								<td>${select_total-item.rn+1}</td>
								<td class="subject align_left">
									<c:choose>
										<c:when test="${item.voc_issecret eq '0'}">
												<a href="#;"onclick="view('${item.voc_no}')">
													<i class="fa fa-lock" style="color:#ff0000;"></i> 
													<c:set var="TextValue" value="${item.voc_subject}"/>
													<em>${fn:substring(item.voc_subject,0,44)}
														<c:if test="${fn:length(item.voc_subject) > 45 }">...</c:if>
													</em>
												</a>
										</c:when>
										<c:otherwise>
												<a href="#;"onclick="view1('${item.voc_no}')">
													<c:set var="TextValue" value="${item.voc_subject}"/>
													<em>${fn:substring(item.voc_subject,0,44)}
														<c:if test="${fn:length(item.voc_subject) > 45 }">...</c:if>
													</em>
												</a>
										</c:otherwise>
									</c:choose>
									<span class="date">
										<i class="fa fa-tag" aria-hidden="true"></i>&nbsp;${item.voc_type}
									</span>
									&nbsp;
									<span class="hit">
										<i class="fa fa-user" aria-hidden="true"></i>&nbsp; 
										<c:if test="${item.voc_name ne ''}">
											<c:choose>
												<c:when test="${fn:length(item.voc_name) < 2}">
													*${item.voc_name}*
												</c:when>
												<c:when test="${fn:length(item.voc_name) < 3}">
													${fn:substring(item.voc_name,0,1)}*
												</c:when>
												<c:otherwise>
													${fn:substring(item.voc_name,0,1)}<c:forEach begin="2" end="${fn:length(item.voc_name)-1}" step="1">*</c:forEach>${fn:substring(item.voc_name,fn:length(item.voc_name)-1,fn:length(item.voc_name))}
												</c:otherwise>
											</c:choose>
										</c:if>
									</span>
									&nbsp;
									<span class="date">
										<i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;${fn:substring(item.voc_insert_dt,0,10)}
									</span>
								</td>
								<td>
									<c:choose>
										<c:when test="${item.voc_isanswer eq '0'}">
											<span class="label label-outline label-warning label-lg">X</span>																		
										</c:when>
										<c:otherwise>
											<span class="label label-outline label-info label-lg">O</span>
										</c:otherwise>
									</c:choose>
								</td>
								<!-- <td>1</td>
								<td>제~~~~~목</td>
								<td>작성자1</td>
								<td>2021.02.26 18:30</tdb>
								<td></td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
			
			<div id="btnArea" class="noupline">
				<ul class="floats">
					<li class="fr">
						<a href="/policy/m/customer_sound_write.do" class="btn_report btn_red">글쓰기</a>
					</li>
				</ul>
			</div>
		</section>
	</div>
</form>
<script type="text/javascript" defer >
	// 게시판 리스트 or 갤러리 토글버튼
	var pageName = "${pageName}";
	

	

function fnSelectInfs(pageIndex) {
	$("#pageIndex").val(pageIndex);
	$("#voc_type").val();		
	$("#frm").attr("action", "/policy/m/customer_sound_list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

function view(voc_no){
	// 비밀글일 경우 ( 회원 )
	var form = document.getElementById('listform');
	$('#voc_no').val(voc_no);

	$.ajax({
		type:"POST",
		url :"/policy/customer_sound/check_voc_pwd.do",
		data:$('#listform').serialize(),
		dataType: 'html',
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			if(data.error == '0') {
				$("#listform").attr("action", "/policy/m/customer_sound_view.do");
				$("#listform").submit();
			} else if(data.error == '1') {
				$("#allPublicModal").html(data);
				$("#allPublicModal").modal('show');
			} else {
				$("#allPublicModal").html(data);
				$("#allPublicModal").modal('show');
			}
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}

function view1(voc_no){
	// 비밀글일 경우 ( 비회원 )
	var form = document.getElementById('listform');
	$('#voc_no').val(voc_no);
	$("#listform").attr("action", "/policy/m/customer_sound_view.do");
	$("#listform").submit();
}	
</script>

<%@include file="../../layout/m/tail.jsp"%>




