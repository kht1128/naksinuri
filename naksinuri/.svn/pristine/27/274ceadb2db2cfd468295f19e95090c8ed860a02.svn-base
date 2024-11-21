<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../naksinuri_original/naksinuri/layout/newHead.jsp"%>

<link rel="stylesheet" type="text/css" href="/naksinuri_original/css/fullcalendar-3.9.0/fullcalendar.min.css">
<!-- <link rel="stylesheet" type="text/css" href="/naksinuri_original/css/fullcalendar-3.9.0/fullcalendar.print.min.css"> -->
<link rel="stylesheet" type="text/css" href="/naksinuri_original/css/fullcalendar-3.9.0/custom.css?v=20200923">

<script src="/naksinuri_original/js/fullcalendar-3.9.0/moment.min.js"></script>
<!-- <script src="/naksinuri_original/js/fullcalendar-3.9.0/jquery.min.js"></script> -->
<script src="/naksinuri_original/js/fullcalendar-3.9.0/fullcalendar.js"></script>
<script src="/naksinuri_original/js/fullcalendar-3.9.0/locale/ko.js"></script>


<c:if test="${empty info.yearString}">
	<jsp:useBean id="toDay" class="java.util.Date" />
	<fmt:formatDate var="year" pattern="yyyy" value="${toDay}"/>
	<fmt:formatDate var="month" pattern="MM" value="${toDay}"/>
	<fmt:formatDate var="day" pattern="dd" value="${toDay}"/>
</c:if>
<c:if test="${not empty info.yearString}">
	<c:set var="year" value="${info.yearString}"/>
	<c:set var="month" value="${info.monthString}"/>
	<c:set var="day" value="${info.dayString}"/>
</c:if>

<form:form commandName="eduCenterMainVO" id="listForm" name="listForm" method="post">
	<input type="hidden" id="CRS_SN" name="CRS_SN" value=""/>
	<!-- 달력 -->
	<div class="content respon3" id="trnng">
		<section id="writeForm" class="write_box">
			<div class="list_searchbox">
				<form:form commandName="eduCenterMainVO" id="listForm" name="listForm" method="post">
					<c:set var="total_page" value="${(paginationInfo.totalRecordCount/paginationInfo.recordCountPerPage)}"/>
					<fmt:parseNumber var="total_page" integerOnly = "true" type = "number" value = "${total_page+(1-(total_page%1))%1}" />
					<div class="total_num">전체 <b class="colorSky">${paginationInfo.totalRecordCount}</b>건의 게시물이 있습니다.(<b class="colorSky">${paginationInfo.currentPageNo}</b>/${total_page})</div>
					<div class="display-inline-block float-left" id="trgOnlineEduBody"></div>
					<input type="hidden" id="yearString" name="yearString" value="${year}"/>
					<input type="hidden" id="monthString" name="monthString" value="${month}"/>
					<input type="hidden" id="dayString" name="dayString" value="${day}"/><input type="hidden" name="year" value="${year}"/>
					<select class="basic_select" title="교육대상 검색조건선택" name="CRS_MBR_CD">
						<option value="${item.CD_ID}">교육대상 전체보기</option>
						<c:forEach var="item" items="${list_mbr_trgt_cd}">
							<option value="${item.CD_ID}" <c:if test="${info.CRS_MBR_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
						</c:forEach>
					</select>
					<select class="basic_select basic_select2" title="교육그룹 검색조건선택" name="CRS_GRP_CD">
						<option value="${item.CD_ID}">교육그룹 전체보기</option>
						<c:forEach var="item" items="${list_edu_grp_cd}">
							<option value="${item.CD_ID}" <c:if test="${info.CRS_GRP_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
						</c:forEach>
					</select>
					<!-- <input type="text" class="basic_input" placeholder="검색어를 입력하세요" title="검색어입력"/> -->
					<button class="searchBtn" id="searchBtn"><span class="blind">교육일정검색</span><i class="fa fa-search" aria-hidden="true" ></i></button>
				</form:form>
			</div>
<!-- 			<div class="agree_box pt-20 pb-20">
				<span class="pl-10 pr-10 mr-5 ml-30" style="background-color:#098199;"></span>
				<span class="font-weight-bold">낚시어선업자</span>
				<span class="pl-10 pr-10 mr-5" style="background-color:#CB4D03;"></span>
				<span class="font-weight-bold text-middle">낚시터업자</span>
				<span class="pl-10 pr-10 mr-5" style="background-color:#A57AFA;"></span>
				<span class="font-weight-bold text-middle">신규/재개자</span>			
			</div> -->
	<!-- 		<div class="agree_box">
				<div id="fullCalendar"></div>
			</div>
			<script>
				$(function(){
					var fullCalendar = $('#fullCalendar').fullCalendar({
						defaultDate: '${year}-${month}-${day}',	//날짜지정
						displayEventTime: false,
						editable: false,
						height: 'auto',
						header: {
							left: 'prev',
							center: 'title',
							right: 'next'
						},
						eventRender: function(event, element, view) {
							console.log(event);
							var month = view.intervalStart.format('MM');
						    var year = view.intervalStart.format('YYYY');
						    $('#yearString').val(year);
						    $('#monthString').val(month);
							if( event.className[1] == 'lock' ) {
								$(element).attr('href','javascript:popupInfo(\''+event.crs_sn+'\');');
								$(element).attr('tabindex','1');
								$(element).append('<span class="blind">교육명:'+event.title+',장소:'+event.subtitle+',교육장소:'+event.subtitle2+',신청기간:'+event.subinfo+'~'+event.subinfo2+'</span>');
								$(element).append('<span class="fc-content"> <span class="fc-subtitle">'+event.subtitle+'</span><br><span class="font-size-12">'+event.subtitle2+'</span></span>');
								$(element).append('<span class="fc-content"> <span class="fc-subtitle">'+event.subinfo+'부터'+' '+event.subinfo2+'까지'+'</span></span>');
								$(element).append('<a href="javascript:allPublicModalMessage(\'해당 교육은 모집이 마감되었습니다.\');" class="a-lock btn btn-xs btn-warning wp60" title="모집마감">모집마감</a>');
							} else if( event.className[1] == 'unlock' ) {
								$(element).attr('href','javascript:popupInfo(\''+event.crs_sn+'\');');
								$(element).attr('tabindex','1');
								$(element).append('<span class="blind">교육명:'+event.title+',장소:'+event.subtitle+',교육장소:'+event.subtitle2+',신청기간:'+event.subinfo+'~'+event.subinfo2+'</span>');
								$(element).append('<span class="fc-content"> <span class="fc-subtitle">'+event.subtitle+'</span><br><span class="font-size-12">'+event.subtitle2+'</span></span>');
								$(element).append('<span class="fc-content"> <span class="fc-subtitle">'+event.subinfo+'부터'+' '+event.subinfo2+'까지'+'</span></span>');
								$(element).append('<a href="javascript:checkEdu(\''+event.crs_sn+'\')" class="a-unlock btn btn-xs btn-default wp60" title="신청하기">신청하기</a>');
							} else if( event.className[1] == 'mbrover' ) {
								$(element).attr('href','javascript:popupInfo(\''+event.crs_sn+'\');');
								$(element).attr('tabindex','1');
								$(element).append('<span class="blind">교육명:'+event.title+',장소:'+event.subtitle+',교육장소:'+event.subtitle2+',신청기간:'+event.subinfo+'~'+event.subinfo2+'</span>');
								$(element).append('<span class="fc-content"> <span class="fc-subtitle">'+event.subtitle+'</span><br><span class="font-size-12">'+event.subtitle2+'</span></span>');
								$(element).append('<span class="fc-content"> <span class="fc-subtitle">'+event.subinfo+'부터'+' '+event.subinfo2+'까지'+'</span></span>');
								$(element).append('<a href="javascript:pageChkMove(\''+event.crs_sn+'\')" class="a-unlock btn btn-xs btn-default wp60" title="대기자등록">대기자등록</a>');
							} else {
								$(element).attr('href','javascript:popupInfo(\''+event.crs_sn+'\');');
								$(element).attr('tabindex','1');
								$(element).append('<span class="blind">교육명:'+event.title+',장소:'+event.subtitle+',교육장소:'+event.subtitle2+',신청기간:'+event.subinfo+'~'+event.subinfo2+'</span>');
								$(element).append('<span class="fc-content"> <span class="fc-subtitle">'+event.subtitle+'</span><br><span class="font-size-12">'+event.subtitle2+'</span></span>');
								$(element).append('<span class="fc-content"> <span class="fc-subtitle">'+event.subinfo+'부터'+' '+event.subinfo2+'까지'+'</span></span>');
								$(element).append('<a href="javascript:allPublicModalMessage(\'해당 교육은 준비중입니다.\');" class="a-lock btn btn-xs btn-warning wp60" title="준비중">준비중</a>');
							}
						},
						events: {
						    url: '/educenter/trnng/ajax_calendar_trnng.do',
						    type: 'POST',
						    data: {
						      	CRS_TYPE : '',
						      	TYPE_GB : 'online',
						      	CRS_GRP_CD : '${info.CRS_GRP_CD}',
						      	CRS_MBR_CD : '${info.CRS_MBR_CD}',
						    },
						    error: function(e) {
						      	//console.log(e);
						    },
						    //color: 'yellow',   // a non-ajax option
						    //textColor: 'black' // a non-ajax option
						}
					}); // EO fullCalendar
				}); // EO $(function())
			</script> -->
			<div class="board_list">
				<table class="list_tbl">
					<caption>온라인 교육 일정 리스트</caption>
					<colgroup>
						<col width="5%"/> 
						<col width="48%"/>
						<col width="30%"/>
						<col width="17%"/>
					</colgroup>
					<thead>
						<tr>
							<th>NO</th>
							<th>교육과정명</th>
							<th>신청기간</th>
							<th>수강신청</th>  
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty list}">
							<tr><td colspan="4" class="text-center table-active">현재 신청가능한 온라인 교육이 없습니다.</td></tr>
			           	</c:if>
						<c:forEach var="item" varStatus="status" items="${list}">
							<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
							<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
							<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy.MM.dd HH:mm 부터" var="RECRUIT_STR_DT"/>
							<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy.MM.dd HH:mm 까지" var="RECRUIT_END_DT"/>
							<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
							<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
							<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.MM.dd(E)" var="CRS_STR_DT"/>
							<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy.MM.dd(E)" var="CRS_END_DT"/>
							
							<tr>
								<td class="text-center">
									${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}
								</td>
								<td class="text-center">
									<div class="text-box">
										<span class="crsYear">${item.CRS_YEAR}년도 교육</span>
										<h4>${item.CRS_NM}</h4>
										<span>온라인 교육</span>
										<p>교육기간<br>${CRS_STR_DT} ~ ${CRS_END_DT}</p>
									</div>
								</td>
								<td class="text-center">${RECRUIT_STR_DT}<br>${RECRUIT_END_DT}</td>
								<td class="text-center">
									<c:if test="${item.CRS_YEAR eq loginVO.TRGT_YEAR or loginVO eq null}">
										<c:choose>
											<c:when test="${item.eventClassName eq 'lock'}">
												<a href="javascript:allPublicModalMessage('해당 교육은 모집이 마감되었습니다.');" class="deadline" title="모집마감">모집마감</a>
											</c:when>
											<c:when test="${item.eventClassName eq 'unlock'}">
												<a class="registerButton" href="javascript:void(0);" onclick="javascript:checkEdu('${item.CRS_SN}')" title="신청하기">
													<span>신청하기</span>
												</a>
											</c:when>
											<c:when test="${item.eventClassName eq 'mbrover'}">
												<a href="javascript:pageChkMove(${item.CRS_SN})" class="a-unlock btn btn-xs btn-default wp60" title="대기자등록">대기자등록</a>
											</c:when>
											<c:otherwise>
												<a class="registerButton" href="javascript:allPublicModalMessage('해당 교육은 준비중입니다.');" title="준비중">
													<span>준비중</span>
												</a>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${loginVO ne null and item.CRS_YEAR ne loginVO.TRGT_YEAR}">
										<a href="javascript:allPublicModalMessage('해당 교육은 신청이 불가능합니다..');" class="deadline" title="신청불가">신청불가</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<nav aria-label="Page navigation" class="text-center">
				<ul class="pagination">
					<ui:pagination paginationInfo = "${paginationInfo}" type="list" jsFunction="fnLinkPage" />
					<form:hidden path="pageIndex" />
				</ul>
			</nav>						
		</section>
	</div> 
</form:form>
<script>
function pageChkMove(idx) {//확인이동
	if(!confirm('대기자 접수신청 입니다.\n접수하시겠습니까?')) {
		return;
	}
	var form = document.getElementById('listForm');
	form.CRS_SN.value = idx;
	form.action = "/educenter/trnng/agree.do";
	form.submit();
}
function pageMove(idx) {//약관이동
	var form = document.getElementById('listForm');
	form.CRS_SN.value = idx;
	form.action = "/educenter/trnng/agree.do";
	form.submit();
}
function fnLinkPage(idx){
	var form = document.getElementById('listForm');
    form.pageIndex.value = idx;
    form.action = "/educenter/trnng/list.do";
    form.submit();
}
function popupInfo(idx) {//팝업호출
	var form = document.getElementById('listForm');
	form.CRS_SN.value = idx;
	form.action = "";
	
	$.ajax({
		type:"POST",
		url :"/educenter/trnng/view.do",
		data:$('#listForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#allPublicModal").html(data);
			$("#allPublicModal").modal({
				backdrop: 'static',
			    keyboard: false
			});
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});	
}
//온라인 교육 과정 조회
function searchOnlineEdu() {
	console.log(" ::::::::::::::::::: //온라인 교육 과정 조회 ")
	var form = document.getElementById('listForm');
	form.action = "";
	$.ajax({
		type:"POST",
		url :"/educenter/trnng/onlineEdu.do",
		data:$('#listForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			var htmlString = '';
			if(data.error == '1') {
				htmlString = '';
			} else {
				var json = JSON.parse(data.rawdata);
				for (i=0; i<json.length; i++) {	
					var item = json[i];
					var btncolor = '';
					if(item.crs_mbr_cd == 'CIDN010300') {
						btncolor = 'btn-ship';	
					} else {
						btncolor = 'btn-house';
					}
					htmlString += '<a href="javascript:pageMove(\''+item.crs_sn+'\');" class="btn btn-xs '+btncolor+'  pt-10 pb-10 pl-10 pr-10 ">'+item.crs_nm+' 신청하기</a>';
				}
			}
			$("#trgOnlineEduBody").html(htmlString);
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});	
}
$(function(){
	//searchOnlineEdu();//2020.03.29 상단 온라인링크해제
	$(".fc-icon-left-single-arrow").append('<span style="position: absolute;left: -10000px;top: auto;width: 1px;height: 1px;overflow: hidden;">이전달</span>');
	$(".fc-icon-right-single-arrow").append('<span style="position: absolute;left: -10000px;top: auto;width: 1px;height: 1px;overflow: hidden;">다음달</span>');
});
function checkEdu(idx){
	var form = document.getElementById('listForm');
	form.CRS_SN.value = idx;
	form.action = "";
	$.ajax({
		type:"POST",
		url :"/educenter/trnng/checkEdu.do",
		data:$('#listForm').serialize(),
		dataType: "html",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			$("#allPublicModal").html(data);
			$("#allPublicModal").modal({
					backdrop: 'static',
				    keyboard: false
				});
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}  

//768px 이하에서 사용
if (matchMedia("screen and (max-width: 768px)").matches) {
	$('.list_searchbox .basic_select2 option:eq(0)').text('전체보기');
} 
</script>

<%@include file="../../naksinuri_original/naksinuri/layout/tail.jsp"%>
