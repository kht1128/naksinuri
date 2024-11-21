<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%-- 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>
<script>
location.href = '/index.do';
</script>
<%-- End of 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>

<c:set var="pageMode" value="sosig" />
<c:set var="depthNum" value="4" />
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congress" />

<%@include file="../../layout/m/head.jsp"%>

<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sn" id="bo_sn"/>
</form:form>

<!-- //낚시대회 리뉴얼 - 2018.09.06 -->
<form:form commandName="searchform" name="searchform" id="searchform" method="post">
	<input type="hidden" name="search_str" id="search_str" />
	<input type="hidden" name="select_year" id="select_year" />
	<input type="hidden" name="select_month" id="select_month" />
</form:form>

<form action="" id="frm" name="frm" method="post">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
	<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
	<input type="hidden" name="gallery_list" id="gallery_list" value="${gallery_list}" />
	<c:set var="pagesize" value="${select_total/pageUnit}"/>

	<div id="conferenceList" class="content respon2">
		<section id="conferenceCalendar" class="floats">
			<div class="schedule_latest">
				<h3>날짜별 주요 <b class="colorBlue">낚시대회 일정</b></h3>
				<p>낚시누리는 회원님들의 편의를 위하여 날짜별 낚시대회 정보를<br />상세하게 제공하고 있습니다.</p>
				<ul class="floats">
					<c:forEach var="congress" items="${latest_list}">
					<li>			
					<c:choose>
						<c:when test="${congress.orignl_file_nm ne null }">
							<a href="#;" onclick="view2('${congress.bo_sn}')" class="pic">
								<!-- <img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${congress.bo_main_img}"/>&fileSn=<c:out value="${congress.file_sn }"/>'  width="100%" height="100%"  />  -->
								<div style="width:100%;height:170px;background:url('<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${congress.bo_main_img}"/>&fileSn=<c:out value="${congress.file_sn }"/>') top no-repeat;background-size:cover;"></div>
							</a>
						</c:when>
						<c:otherwise>
							<a href="#;" onclick="view2(${congress.bo_sn})" class="pic">
								<!-- <img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="no_image"/>'/>-->
								<div style="width:100%;height:170px;background:url('/naksinuri_original/common_main/img/noImage_big.png') top no-repeat;background-size:cover;"></div>	
							</a>
						</c:otherwise>
					</c:choose>
					</li>
					</c:forEach>
				</ul>
			</div>
		</section>
		<!-- //낚시대회 리뉴얼 - 2018.09.06 -->
		<section>	
			<div class="schedule_full">
				<div class="main_bx1_1_cal">
					<div class="cal_list_bx">
						<ul class="cal_month">
							<li class="cal_month0"><p class="cal_month2"><span id="label_year"></span>년</p>
								<p class="cal_month3">
									<span id="label_month"></span>월
								</p></li>
							<li class="cal_month1"><a href="#;" onclick="prevMon();"><i class="fa fa-angle-left" aria-hidden="true"></i></a></li>
							<li class="cal_month4"><a href="#;" onclick="nextMon();"><i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
						</ul>
						<ul class="cal_list">
							<div class="cal_list_item" id="cal_list_item">
								<c:forEach var="cal_list_item" items="${select_list}">
									<li>
										<a href="#;" onclick="view2('${cal_list_item.bo_sn}')"> <span class="cal_list_span0"><c:out value="${cal_list_item.bo_subject}" /></span>
											<br><span class="cal_list_span2">${cal_list_item.bo_start_dt} ~ ${cal_list_item.bo_end_dt}</span><br><span class="cal_list_span3">${cal_list_item.bo_name}</span>
										</a>
									</li>
								</c:forEach>
							</div>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<section id="boardList" class="list_box">
			<div class="tabArea tab"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
				<ul class="floats">
					<li><a href="./list.do">진행중인 행사</a></li>
					<li class="on"><a href="./endlist.do" >종료된 행사</a></li>
				</ul>
			</div>
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
				<a href="#;"  onclick="fnSelectInfs2('list')" class="change_type list on"><i class="fa fa-th-list" aria-hidden="true"></i></a>
				<a href="#;"  onclick="fnSelectInfs2('gallery')" class="change_type gallery"><i class="fa fa-th-large" aria-hidden="true"></i></a>
			</div>
			<div class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="130" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item2" items="${noticlist}">
							<tr>
								<td><span><b class="colorBlue">공지</b></span></td>
								<td class="subject align_left"><em><a class="colorBlue" href="#;"onclick="view2('${item2.bo_sn}')">${item2.bo_subject}</a></em>
								<span class="hit"><i class="fa fa-eye" aria-hidden="true">
								</i> <c:out value="${item2.bo_view}"></c:out> &nbsp;</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true">
								</i> ${fn:substring(item2.bo_insert_dt,0,10) }</span>
								</td>
							</tr>
						</c:forEach>
						<c:forEach var="item" items="${select_list}">
							<tr>
								<td>${select_total-item.rn+1}</td>
								<td class="subject align_left"><em><a href="#;" onclick="view2('${item.bo_sn}')">${item.bo_subject }</a></em>								
								<span class="hit"><i class="fa fa-eye" aria-hidden="true">
								</i> <c:out value="${item.bo_view}"></c:out> &nbsp;</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true">
								</i> ${fn:substring(item.bo_insert_dt,0,10) }</span>		
								</td>		
							</tr>
						</c:forEach>
					<c:if test="${select_list eq null }">
						<tr>
							<td colspan="5">등록된 게시물이 없습니다.</td>
						</tr>					
					</c:if>
					</tbody>
				</table>
			</div>
			<div class="gallery_list off">
				<ul class="floats">
					<c:forEach var="item" items="${select_list}">
					<li>
						<c:choose>
							<c:when test="${item.orignl_file_nm ne null }">
								<a href="#;" onclick="view2('${item.bo_sn}')" class="pic"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>'  width="100%"  /></a>
							</c:when>
							<c:otherwise>
								<a href="#;" onclick="view2(${item.bo_sn})" class="pic"><img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="낚시누리"/>'/></a>
							</c:otherwise>
						</c:choose>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="subject">
							<span class="cate">종료된 대회(${item.bo_cate})</span>
							<em style="height:40px">${fn:substring(item.bo_subject,0,22)}<c:if test="${fn:length(item.bo_subject) > 22 }">...</c:if></em>
							<span class="date"><b>대회기간 : </b> ${item.bo_start_dt} <c:if test="${item.bo_end_dt ne null}"> ~ ${item.bo_end_dt} </c:if></span>
						</a>
						<ul class="eyes_heart floats">
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> ${item.bo_view}</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> ${item.bo_like }</a></li>
						</ul>
					</li>
					</c:forEach>
					<c:if test="${select_list eq null }">
							<li>
								<a><em></em><span id="aaa" class="txt">정보가 없습니다.</span></a>
							</li>
					</c:if>
				</ul>
			</div>
			<div id="pagenation">
			<!-- Ctrl + Shift + T =  pagination 검색 -->
				<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
			</div>
			<div id="btnArea" class="noupline">
				<ul class="floats">
					<li class="fr">
						<a href="./write.do" class="btn_report btn_red">낚시대회 등록</a>
					</li>
				</ul>
			</div>
		</section>
	</div>
</form>
<script text="text/javascript">
	
	//게시판 리스트 or 갤러리 토글버튼
	var pageName = "${pageName}";
	var gallery_list= "${gallery_list}";
	
	// 게시판 리스트 or 갤러리 토글버튼
	$('.change_type.list').click(function(){
		$(this).addClass("on");
		$('.change_type.gallery').removeClass("on");
		$('.board_list').removeClass("off");
		$('.gallery_list').addClass("off");
		
		// 아작스로 페이지 유닛 바꿔야됨.
		var url="/sosig/congress/list.do"
	});

	$('.change_type.gallery').click(function(){
		$(this).addClass("on");
		$('.change_type.list').removeClass("on");
		$('.board_list').addClass("off");
		$('.gallery_list').removeClass("off");
	});
	
	
	if(gallery_list == 'list'){
		$('.change_type.list').addClass("on");
		$('.change_type.gallery').removeClass("on");
		$('.board_list').removeClass("off");
		$('.gallery_list').addClass("off");
	}
	if(gallery_list == 'gallery'){
		$('.change_type.gallery').addClass("on");
		$('.change_type.list').removeClass("on");
		$('.gallery_list').removeClass("off");
		$('.board_list').addClass("off");
	}
	function fnSelectInfs(pageIndex) {
		
		$("#pageUnit").val();
		$("#pageIndex").val(pageIndex);
		
		$("#frm").attr("action", "/sosig/congress/m/endlist.do");
		$("#frm").submit();
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}
	function fnSelectInfs2(gallery_list) {
		
		$("#pageUnit").val();
		$("#pageIndex").val();
		$("#gallery_list").val(gallery_list);
		$("#frm").attr("action", "/sosig/congress/m/endlist.do");
		
		$("#frm").submit();
		
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}	
		

	function view2(bo_sn){
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};	
	
	
	
	//낚시대회 리뉴얼 - 2018.09.06		
	$(function(){
		$(document).ready(function(){
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			
			$('#label_year').html(y);
			$('#label_month').html(m+1);
			$("#select_year").val(y);
			$("#select_month").val(m+1);
			
			$('#btn_mon'+(m+1)).addClass("on");
			$('#btn_mon'+(m+1)).parent().addClass("on");
			
		})
	})
	

	//낚시대회 리뉴얼 - 2018.09.06
	function prevMon() {
		var month = parseInt($("#select_month").val()) - 1;
		if(month < 1) {
			var year = parseInt($("#select_year").val()) - 1;
			$("#select_year").val(year);
			month = 12;
		}
		$("#select_month").val(month);
		search_cal_data(month);
	}
	//낚시대회 리뉴얼 - 2018.09.06
	function nextMon() {
		var month = parseInt($("#select_month").val()) + 1;
		if(month > 12) {
			var year = parseInt($("#select_year").val()) + 1;
			$("#select_year").val(year);
			month = 1;
		} 
		$("#select_month").val(month);
		search_cal_data(month);
	}
	//낚시대회 리뉴얼 - 2018.09.06
	function search_cal_data(month) {
		var year =$("#select_year").val();
		if(month == '0') {
			month = $("#select_month").val();
		}
		$("#select_year").val(year);
		$("#select_month").val(month);
		$("#search_str").val(year+"-"+month);
		$.ajax({
			type:"GET",
			url:"/sosig/congress/conditioncal.do",
			dataType:'json',
			data:$("#searchform").serialize(),
			contentType : "application/json;charset=UTF-8",
			success:function(data,status,xhr){
				var jsonData = JSON.stringify(data);
				if(data.length==0) {
					$('#cal_list_item').append('<li>등록된 대회 일정이 없습니다.</li>');
				} else {
					for(var i=0; i<data.length; i++) {
						$('#cal_list_item').append('<li><a href="#;" onclick="view2(\''+data[i].bo_sn+'\')"> <span class="cal_list_span0">'+data[i].bo_subject+'</span><br><span class="cal_list_span2">'+data[i].bo_start_dt+' ~ '+data[i].bo_end_dt+'</span><br><span class="cal_list_span3">'+data[i].bo_name+'</span></a></li>');
					}
				}
			},	
			beforeSend:function() {
				$('#cal_list_item').html('');
			},
			complete : function () {  
				$('#label_year').html(year);
				$('#label_month').html(month);
			}
		});
	}	

</script>

<%@include file="../../layout/m/tail.jsp"%>