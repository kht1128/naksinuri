<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="share" />
<c:set var="pageName" value="survey" />
<c:set var="depthNum" value="3" />
<c:set var="urlPath" value="survey" />



<%@include file="../../layout/m/head.jsp"%>


<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="sv_id" id="sv_id"/>
</form:form>
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
<c:set var="pagesize" value="${select_total/pageUnit}"/>

	<div id="fishjobList" class="content respon2">
		
		<section id="webzineList" class="list_box">
			<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
				<ul class="floats">
					<li class="survey_now" ><a href="./list.do">진행중인 설문조사</a></li>
					<li class="survey_end on" ><a href="#;">종료된 설문조사</a></li>
				</ul>
			</div>
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
			
			</div>
			
			<div class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="60" />
						<col />
<%-- 						<col width="60" /> --%>
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
<!-- 							<th>링크</th> -->
						</tr>
					</thead>
					<tbody>
					<c:if test="${select_list eq null or empty select_list}">
						<tr>
							<td colspan="2">등록된 게시물이 없습니다.</td>
						</tr>					
					</c:if>
					<c:forEach var="item" items="${select_list}">
						<c:if test="${item.SV_LC eq 'naksinuri'}">
							<tr>
								<td><span>${select_total-item.rn+1}</span></td>
								<td class="subject align_left"><a href="#;"onclick="view2('${item.sv_id}')">
								<c:set var="TextValue" value="${item.sv_subject}"/>
									<em>${fn:substring(item.sv_subject,0,44)}
										<c:if test="${fn:length(item.sv_subject) > 45 }">...</c:if>
									</em></a>
								<span class="date"><i class="fa fa-clock-o" aria-hidden="true">
								</i>${fn:substring(item.sv_strt_dt,0,10)} ~ ${fn:substring(item.sv_end_dt,0,10)}</span>
								<a href="#;" onclick="chk_result('${item.sv_id}')">결과보기</a>
								</td>
<%-- 								<td>
									<a href="#;" class="tooltipCustom btnSharelink" title="게시물 공유 URL주소"
										data-pl1="${urlPath}"
										data-pl2="${pageName}"
										data-pl3="view"
										data-pl4=""
										data-sid="${item.sv_id}">
											<i class="fa fa-link"></i>
									</a>&nbsp;<span id="sharelinkurlprint" class="red-600"></span>
								</td> --%>								
							</tr>
						</c:if>	
					</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div id="pagenation">
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
		</section>
	</div>
</form>
<script>
var pageName = "${pageName}";
var bo_cate = "${bo_cate}";






function fnSelectInfs(pageIndex) {

$("#pageUnit").val();
$("#pageIndex").val(pageIndex);
$("#gallery_list").val();
$("#bo_cate").val();		
$("#frm").attr("action", "${pageContext.request.contextPath}/${column}/${table}/m/list.do");
$("#frm").submit();
//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}



function view2(sv_id){
	var form = document.getElementById('listform');
	$('#sv_id').val(sv_id);
	
	form.action="./view.do";
	form.submit();
};	

function chk_result(sv_id){
	var form = document.getElementById('listform');
	$('#sv_id').val(sv_id);
	
	form.action="/survey/survey/m/result.do";
	form.submit();
	
};

$('.btnSharelink').click(function() {
	var sid = $(this).attr('data-sid');
	var pl1 = $(this).attr('data-pl1');
	var pl2 = $(this).attr('data-pl2');
	var pl3 = $(this).attr('data-pl3');
	var pl4 = $(this).attr('data-pl4');
	$.ajax({
		type:"POST",
		url :"/share/link/copy.do",
		data:{
			LINK_PL1:pl1,
			LINK_PL2:pl2,
			LINK_PL3:pl3,
			LINK_PL4:pl4,
			LINK_SID:sid,
		},
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {	
			if(data.error == '1') {
				alert(data.msg);
			} else {
				//$('#sharelinkurlprint').html(data.sharelink);
				try { 
					var str = data.sharelink;
					if( window.clipboardData && clipboardData.setData ) {
					    clipboardData.setData("Text", str);
					    alert("복사되었습니다.");
					} else {
						prompt("Ctrl+C를 눌러 복사하세요.", str);
					}
				} catch (err) { 
					alert('이 브라우저는 지원하지 않습니다.'); 
				}
			}			
		},
		beforeSend : function() {
			//console.log('before!');
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);			
		}
	});
});	
</script>

<%@include file="../../layout/m/tail.jsp"%>