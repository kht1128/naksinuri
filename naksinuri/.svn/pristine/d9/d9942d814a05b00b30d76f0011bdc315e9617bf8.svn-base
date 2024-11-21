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
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congress" />

<%@include file="../layout/newHead.jsp"%>

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
			<h3>날짜별 주요 <b class="colorBlue">낚시대회 일정</b></h3>
			<p>낚시누리는 회원님들의 편의를 위하여 날짜별 낚시대회 정보를 상세하게 제공하고 있습니다.</p>
			<div class="schedule_latest">
				<ul class="floats">
					<c:forEach var="congress" items="${latest_list}">
						<li>			
						<c:choose>
							<c:when test="${congress.orignl_file_nm ne null }">
								<!-- 
								<a href="#;" onclick="view2('${congress.bo_sn}')" class="pic"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${congress.bo_main_img}"/>&fileSn=<c:out value="${congress.file_sn }"/>'  width="100%" height="100%"  /></a>
								-->
								<div style="width:100%;height:170px;background:url('<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${congress.bo_main_img}"/>&fileSn=<c:out value="${congress.file_sn }"/>') top no-repeat;background-size:cover;"></div>
							</c:when>
							<c:otherwise>
								<a href="#;" onclick="view2(${congress.bo_sn})" class="pic"><img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="낚시누리"/>'/></a>
							</c:otherwise>
						</c:choose>
							
								
						</li>
				  	</c:forEach>
				
				</ul>
			</div>
				<!-- 
				<div id="calendar" class ="calendar_box" style="padding-right:10px;"></div>
				-->
			<!-- //낚시대회 리뉴얼 - 2018.09.06 -->
			<div class="schedule_full">
				<div class="main_bx1_1_cal">
					<div class="cal_list_bx">
						<ul class="cal_month">
							<li class="cal_month1"><a href="#;" onclick="prevMon();" title="이전달로 이동"><i class="fa fa-angle-left" aria-hidden="true" title="이전달로 이동"></i></a></li>
							<li><p class="cal_month2">
									<!-- <span id="label_year"></span>년  -->
									<select name="year" id="year" onchange="search_cal_data('0')" title="낚시대회 일정 년도선택옵션(2018,2019,2020)">
									<option value="2018">2018</option>
									<option value="2019">2019</option>
									<option value="2020">2020</option>
									</select>								
								</p>
								<p class="cal_month3">
									<span id="label_month"></span>월
								</p></li>
							<li class="cal_month4"><a href="#;" onclick="nextMon();" title="다음달로 이동"><i class="fa fa-angle-right" aria-hidden="true" title="다음달로 이동"></i></a></li>
						</ul>
						<ul class="cal_menu">
							<li><a href="#;" id="btn_mon1" onclick="search_cal_data('1')" title="1월">1월</a></li>
							<li><a href="#;" id="btn_mon2" onclick="search_cal_data('2')" title="2월">2월</a></li>
							<li><a href="#;" id="btn_mon3" onclick="search_cal_data('3')" title="3월">3월</a></li>
							<li><a href="#;" id="btn_mon4" onclick="search_cal_data('4')" title="4월">4월</a></li>
							<li><a href="#;" id="btn_mon5" onclick="search_cal_data('5')" title="5월">5월</a></li>
							<li><a href="#;" id="btn_mon6" onclick="search_cal_data('6')" title="6월">6월</a></li>
							<li><a href="#;" id="btn_mon7" onclick="search_cal_data('7')" title="7월">7월</a></li>
							<li><a href="#;" id="btn_mon8" onclick="search_cal_data('8')" title="8월">8월</a></li>
							<li><a href="#;" id="btn_mon9" onclick="search_cal_data('9')" title="9월">9월</a></li>
							<li><a href="#;" id="btn_mon10" onclick="search_cal_data('10')" title="10월">10월</a></li>
							<li><a href="#;" id="btn_mon11" onclick="search_cal_data('11')" title="11월">11월</a></li>
							<li class="last"><a href="#;" id="btn_mon12" onclick="search_cal_data('12')" title="12월">12월</a></li>
						</ul>
						<ul class="cal_list">
							<div class="cal_list_item" id="cal_list_item">
								<c:forEach var="cal_list_item" items="${select_list}">
									<li>
										<a href="#;" onclick="view2('${cal_list_item.bo_sn}')" title="${cal_list_item.bo_subject} 상세보기"> <c:out value="${cal_list_item.bo_subject}" />
											<br><span class="cal_list_span2">${cal_list_item.bo_start_dt} ~ ${cal_list_item.bo_end_dt}<br>${cal_list_item.bo_name}</span>
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
					<li><a href="./list.do" title="진행중인 행사(대회/박람회)">진행중인 행사(대회/박람회)</a></li>
					<li class="on"><a href="./endlist.do" title="종료된 행사(대회/박람회) 선택됨">종료된 행사(대회/박람회)</a></li>
				</ul>
			</div>
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num" style="margin-top: 20px;">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
				<select class="basic_select" name="searchType" title="검색조건선택">
					<option value="bo_subcon" <c:if test="${searchType eq 'bo_subcon'}">selected</c:if>>제목+내용</option>
					<option value="bo_subject" <c:if test="${searchType eq 'bo_subject'}">selected</c:if>>제목</option>
					<option value="bo_subname" <c:if test="${searchType eq 'bo_content'}">selected</c:if>>내용</option>
				</select>
				<input type="text" class="basic_input" id="searchText2" name="searchText" value="${searchText}" title="검색어"/>
				<button class="searchBtn" onclick="fnSelectInfs(1)"><span class="blind">종료된 행사(대회/박람회)검색</span><i class="fa fa-search" aria-hidden="true"></i></button>
				<a href="#;"  onclick="fnSelectInfs2('list')" class="change_type list on"><i class="fa fa-th-list" aria-hidden="true" ${gallery_list eq 'list' ? 'title="리스트형 선택됨"' : 'title="리스트형"'}></i></a>
				<a href="#;"  onclick="fnSelectInfs2('gallery')" class="change_type gallery"><i class="fa fa-th-large" aria-hidden="true" ${gallery_list eq 'gallery' ? 'title="갤러리형 선택됨"' : 'title="갤러리형"'}></i></a>
			</div>
			<div class="board_list">
				<table class="list_tbl">
				<caption>종료된 행사(대회/박람회) 리스트</caption>
					<colgroup>
						<col width="130" />
						<col />
						<col width="140" class="mbNone" />
						<col width="180" class="mbNone" />
						<col width="140" class="mbNone" />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th class="mbNone">파일</th>
							<th class="mbNone">등록일</th>
							<th class="mbNone">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item2" items="${noticlist}">
							<tr>
								<td><span><b class="colorBlue">공지</b></span></td>
								<td class="subject align_left">
									<a class="colorBlue" href="#;"onclick="view2('${item2.bo_sn}')">${item2.bo_subject}</a>
									<div class="mbShow">
										<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i><c:out value="${item2.bo_view }"></c:out></span>
										<span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i>${fn:substring(item2.bo_insert_dt,0,10)}</span>
									</div>
								</td>
								<td class="mbNone"><c:if test="${item2.orignl_file_nm ne null}"><img alt="첨부파일" src="/naksinuri_original/common_main/img/icon_file.gif""></c:if></td>
								<td class="date mbNone">${fn:substring(item2.bo_insert_dt,0,10)}</td>
								<td class="mbNone"><c:out value="${item2.bo_view }"></c:out></td>
							</tr>
						</c:forEach>
						<c:forEach var="item" items="${select_list}">
							<tr>
								<td>${select_total-item.rn+1}</td>
								<td class="subject align_left">
									<a href="#;" onclick="view2('${item.bo_sn}')">${item.bo_subject }</a>
									<div class="mbShow">
										<span class="hit"><i class="fa fa-eye" aria-hidden="true"></i><c:out value="${item.bo_view }"></c:out></span>
										<span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i>${fn:substring(item.bo_insert_dt,0,10)}</span>
									</div>
								</td>
								<td class="mbNone"><c:if test="${item.orignl_file_nm ne null}"><img alt="첨부파일" src="/naksinuri_original/common_main/img/icon_file.gif""></c:if></td>
								<td class="date mbNone">${fn:substring(item.bo_insert_dt,0,10) }</td>
								<td class="mbNone"><c:out value="${item.bo_view}"></c:out></td>
							</tr>
						</c:forEach>
					<c:if test="${select_list eq null }">
						<tr>
							<td class="noData" colspan="5">등록된 게시물이 없습니다.</td>
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
								<a href="#;" onclick="view2('${item.bo_sn}')" class="pic" title="${item.bo_subject} 상세보기"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>'  alt="${item.bo_subject}" width="100%"  /></a>
							</c:when>
							<c:otherwise>
								<a href="#;" onclick="view2(${item.bo_sn})" class="pic" title="${item.bo_subject} 상세보기"><img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="낚시누리"/>'/></a>
							</c:otherwise>
						</c:choose>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="subject" title="${item.bo_subject} 상세보기">
							<span class="cate">종료된 대회(${item.bo_cate})</span>
							<em>${fn:substring(item.bo_subject,0,13)}<c:if test="${fn:length(item.bo_subject) > 12 }">...</c:if></em>
							<span class="date"><b>대회기간 : </b> ${item.bo_start_dt} <c:if test="${item.bo_end_dt ne null}"> ~ ${item.bo_end_dt} </c:if></span>
						</a>
						<a href="#;" onclick="view2('${item.bo_sn}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${item.bo_subject} 상세보기"></i></a>
						<ul class="eyes_heart floats">
							<li><em><i class="fa fa-eye" aria-hidden="true" title="조회수"></i></em> ${item.bo_view}</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true" title="좋아요 수"></i></em> ${item.bo_like }</a></li>
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
<!--  full calendar api -->
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.6.1/fullcalendar.min.js">
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.6.1/fullcalendar.min.css">
	
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.12.0/moment.min.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.6.1/fullcalendar.min.js"></script>
	

	<!-- full calendar api -->
<script text="text/javascript">
	

$(document).ready(function(){
			

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	$.ajax({
		type:"POST",
		url:"/sosig/congress/fullcal.do",
		dataType:'json',
		contentType : "application/json;charset=UTF-8",
		async:false,
		success:function(data,status,xhr){
			
			
			
			var eventData = [];
			for(var i=0;i<data.length;i++){
				eventData.push({
					title : data[i].bo_subject,
					start : data[i].bo_start_dt,
					end   : data[i].bo_end_dt
				})
				
				
			}
//				출력확인
			var jsonData = JSON.stringify(eventData);

			
			
	
	
	
	$('#calendar').fullCalendar({
		
		header:{
			
			left: 'prev,next today',
			center :'title',
			right: 'month'
		},
		titleFormat:{
			month:'YYYY년 MM월'
		},
		columnFormat:{
			month:'ddd',
			week:'M/d ddd',
			day:'M월d일 dddd'
		},
		allDayTest:'시간',
		axisFormat:'tt hh',
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames:['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
		dayNamesShort:['일','월','화','수','목','금','토'],
		buttonText:{
			today:'오늘',
			month:'월간',
			week:'주간',
			day:'일간'
		},
		selectable : true,
		editable: false,
		eventLimit: true,
			events :eventData 
					
			
		
			
			})
		}
	
	})
	

//월 변경 버튼 클릭시 해당 월일의 값을 받아오는 이벤트
$("button.fc-prev-button").click(function(){
var date = $("#calendar").fullCalendar("getDate");
converDate(date);
});
$("button.fc-next-button").click(function(){
var date = $("#calendar").fullCalendar("getDate");
converDate(date)
});

function convertDate(date){
var date = new Date(date);
alert(date.yyyymmdd());
}

//받은 날짜값을 yyyy-mm-dd형태로 출력하기 위한 함수
Date.prototype.yyyymmdd = function(){
var yyyy = this.getFullYear().toString();
var mm = (this.getMonth() +1).toString();
var dd = this.getDate().toString();
return yyyy+ "-" +(mm[1]?mm:"0" +mm[0]) + "-"+ (dd[1]? dd:"0"+dd[0]);
}
});
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
		
		$("#frm").attr("action", "/sosig/congress/endlist.do");
		$("#frm").submit();
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}
	function fnSelectInfs2(gallery_list) {
		
		$("#pageUnit").val();
		$("#pageIndex").val();
		$("#gallery_list").val(gallery_list);
		$("#frm").attr("action", "/sosig/congress/endlist.do");
		
		$("#frm").submit();
		
		//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
	}	
		

	function view2(bo_sn){
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};	
	
	
	$(function(){
		$(document).ready(function(){
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			
			//낚시대회 리뉴얼 - 2018.09.06
			//$('#label_year').html(y);
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
		if(month < 1) 
			return; 
		$("#select_month").val(month);
		search_cal_data(month);
	}
	//낚시대회 리뉴얼 - 2018.09.06
	function nextMon() {
		var month = parseInt($("#select_month").val()) + 1;
		if(month > 12) 
			return; 
		$("#select_month").val(month);
		search_cal_data(month);
	}
	//낚시대회 리뉴얼 - 2018.09.06
	function search_cal_data(month) {
		var year = $('#year').val();
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
						$('#cal_list_item').append('<li><a href="#;" onclick="view2(\''+data[i].bo_sn+'\')"> '+data[i].bo_subject+'<br><span class="cal_list_span2">'+data[i].bo_start_dt+' ~ '+data[i].bo_end_dt+'<br>'+data[i].bo_name+'</span></a></li>');
					}
				}
			},	
			beforeSend:function() {
				$('#cal_list_item').html('');
			},
			complete : function () {  
				for(var i=1; i<=12; i++) {
					$('#btn_mon'+i).removeClass("on");
					$('#btn_mon'+i).parent().removeClass("on");
				}
				if(month!='0') {
					$('#btn_mon'+month).addClass("on");
					$('#btn_mon'+month).parent().addClass("on");
				}
				//$('#label_year').html(year);
				$('#label_month').html(month);
			}
		});
	}
		
		
		
	// 모바일에서 검색창 숨김
	$(document).ready(function (){
		$('.list_searchbox .search_group').addClass('mbNone');
	});
</script>

<%@include file="../layout/tail.jsp"%>