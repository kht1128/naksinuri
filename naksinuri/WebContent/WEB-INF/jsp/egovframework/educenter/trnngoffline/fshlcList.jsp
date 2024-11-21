<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../naksinuri_original/naksinuri/layout/newHead.jsp"%>

<style>
.board_list .list_tbl tr th, .board_list .list_tbl tr td{
	padding:8px;
    border-top: 1px solid #e0e0e0;
    border-left: 1px solid #e0e0e0;
    text-align: center;
    font-size: 14px;
}
</style>

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
	<input type="hidden" id="TYPE_GB" name="TYPE_GB" value="offline"/>
	<input type="hidden" id="CRS_MBR_CD" name="CRS_MBR_CD" value="CIDN010200"/>
	<div class="content respon3" id="trnngoff">
		<section id="writeForm" class="write_box">
			<div class="list_searchbox">
				<c:set var="total_page" value="${(paginationInfo.totalRecordCount/paginationInfo.recordCountPerPage)}"/>
				<fmt:parseNumber var="total_page" integerOnly = "true" type = "number" value = "${total_page+(1-(total_page%1))%1}" />
				<div class="total_num" style="font-size:14px;">전체 <b class="colorSky">${paginationInfo.totalRecordCount}</b>건의 낚시터 오프라인 교육이 있습니다.(<b class="colorSky">${paginationInfo.currentPageNo}</b>/${total_page})</div>
				<select class="basic_select" title="검색조건선택" name="basic_select">
					<option value="select_total">전체</option>
					<option value="select_crs_nm">교육명</option>
					<option value="select_crs_addr">교육장소</option>
				</select>
				<input type="text" class="basic_input" placeholder="검색어를 입력하세요" title="검색어입력" name="search_word" value="${search_word}"/>
				<button class="searchBtn"><span class="blind">검색</span><i class="fa fa-search" aria-hidden="true" ></i></button>
			</div>
			
			<div class="board_list type1">
				<table class="list_tbl">
					<caption>교육수강일정(오프라인)</caption>	
					<colgroup>
						<col width="5%"/> 
						<col width="18%"/>
						<col width="20%"/>
						<col width="20%"/>
						<col width="23%"/>
						<col width="7%"/>
						<col width="7%"/>
					</colgroup>
					<thead>
						<tr>
							<th>교육<br>유형</th>
							<th>교육명</th>
							<th>교육일시</th>
							<th>교육장주소</th>
							<th>교육장소명</th>
							<th>신청<br>인원</th>
							<th>상태</th>                                                                                                                                                                                                                                                                                                                                          </th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty list}">
							<tr><td colspan="7" class="text-center table-active">현재 신청가능한 오프라인 교육이 없습니다.</td></tr>
							<!-- <tr><td colspan="5" class="text-center table-active">현재 수강신청한 내역이 없습니다.</td></tr> -->	  
			           	</c:if>
			           	<c:forEach var="item" varStatus="status" items="${list}">
			           		<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
							<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
							<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
							<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
							<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd(E) HH시mm분부터" var="CRS_STR_DT"/>
							<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd(E) HH시mm분까지" var="CRS_END_DT"/>
							<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd HH:mm:ss" var="RECRUIT_STR_DT"/>
							<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd HH:mm:ss" var="RECRUIT_END_DT"/>
		            		
							<tr>
								<td class="text-center">오프<br>라인</td>
								<td class="text-center">${item.CRS_NM}</td>
								<td class="text-center">${CRS_STR_DT}<br>${CRS_END_DT}</td>
								<td class="text-center">${item.CRS_ADDR}</td>
								<td class="text-center">${item.CRS_PLACE}</td>
								<td class="text-center">${item.MBR_CUR_CNT} / ${item.MBR_MAX_CNT}</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${item.eventClassName eq 'lock'}">
											<a href="javascript:allPublicModalMessage('해당 교육은 모집이 마감되었습니다.');" class="a-lock btn btn-xs btn-warning" title="모집마감">모집<br>마감</a>
										</c:when>
										<c:when test="${item.eventClassName eq 'unlock'}">
											<a href="javascript:checkEdu('${item.CRS_SN}')" class="a-unlock btn btn-xs btn-default" title="신청하기">신청<br>하기</a>
										</c:when>
										<c:when test="${item.eventClassName eq 'mbrover'}">
											<a href="javascript:pageChkMove(${item.CRS_SN})" class="a-unlock btn btn-xs btn-default wp60" title="대기자등록">대기자등록</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:allPublicModalMessage('해당 교육은 준비중입니다.');" class="a-lock btn btn-xs btn-warning wp60" title="준비중" style="width:100% !important;">준비중</a>
										</c:otherwise>
									</c:choose>
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

<form:form commandName="eduCenterMainVO" id="deleteForm" name="deleteForm" method="post">
	<input type="hidden" id="CRS_SN" name="CRS_SN" value=""/>
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
$(function(){
	//searchOnlineEdu();//2020.03.29 상단 온라인링크해제
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

function deleteAct(idx){
	var form = document.getElementById('deleteForm');
	form.CRS_SN.value = idx;
	$.ajax({
		type:"POST",
		url :"/educenter/trnng/remove_act.do",
		data:$('#deleteForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			if(data.error == '0'){
				writeAct();
			} else if(data.error == '0'){
				alert('기존데이터를 삭제하는데 실패하였습니다.');
			}
		},
		complete : function() {

	    },
		error: function(jqXHR, textStatus, errorThrown) {

		}
	});
}


function writeAct(){
	var form = document.getElementById('deleteForm');
	form.action = '/educenter/trnng/agree.do';
	form.submit();
}

function fnLinkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/educenter/trnngoffline/fshlcList.do'/>";
    document.listForm.submit();
}
</script>

<%@include file="../../naksinuri_original/naksinuri/layout/tail.jsp"%>
