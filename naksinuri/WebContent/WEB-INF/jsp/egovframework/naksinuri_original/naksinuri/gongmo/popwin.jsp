<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/common.css" />

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://use.fontawesome.com/e59ba62350.js"></script>
<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
<script src="/naksinuri_original/common_main/js/common.js"></script>

<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" id="idx"  name="idx" value=""/>
	<input type="hidden" id="bo_sn"  name="bo_sn" value="${bo_sn}"/>
	<input type="hidden" id="detail"  name="detail" value="1"/>
</form:form>

<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit}" />
<input type="hidden" name="bo_sn" id="bo_sn" value="${bo_sn}" />
<c:set var="pagesize" value="${select_total/pageUnit}"/>

<style>
	.content{padding:20px}
	.list_tbl th,
	.list_tbl td{background-color:#fff}
	.list_tbl thead th{background-color:#444 !important;color:#fff;font-size:12px;}
</style>

	<div class="content respon2">
		<section id="webzineList" class="list_box">
			
			<div class="list_searchbox">
			<!-- 토탈페이징  -->
			  <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${pagesize+(1-(pagesize%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${select_total}</b>건의 게시물이 있습니다.(<b class="colorSky">${pageIndex}</b>/${i})</div>
				
				<select class="basic_select" name="searchStatus" onchange="select_status(this)" title="접수상태검색조건선택">
					<option value="" <c:if test="${searchStatus eq '' }">selected</c:if>>접수상태(전체)</option>
					<option value="접수대기" <c:if test="${searchStatus eq '접수대기' }">selected</c:if>>접수대기</option>
					<option value="접수보류" <c:if test="${searchStatus eq '접수보류' }">selected</c:if>>접수보류</option>
					<option value="접수완료" <c:if test="${searchStatus eq '접수완료' }">selected</c:if>>접수완료</option>
					<option value="접수숨김" <c:if test="${searchStatus eq '접수숨김' }">selected</c:if>>접수숨김</option>
					<option value="접수삭제" <c:if test="${searchStatus eq '접수삭제' }">selected</c:if>>접수삭제</option>									
				</select>
				
				<select class="basic_select" name="searchType" title="검색조건선택">
					<option value="mbr_name" <c:if test="${searchType eq 'mbr_name'}">selected</c:if>>이름</option>
					<option value="repre_name" <c:if test="${searchType eq 'repre_name'}">selected</c:if>>입금자명</option>
				</select>
				<input type="text" class="basic_input" id ="searchText2" name="searchText" value="${searchText}" title="검색어"/>
				<button class="searchBtn" type="button" onclick="fnSelectInfs(1)"><span class="blind">검색</span><i class="fa fa-search" aria-hidden="true" ></i></button>
			</div>
			<div id="btnArea" class="noupline" style="text-align:right">
				<a href="#;" onclick="go_excel()" class="btn_orange" title="공모전 신청자리스트 엑셀 다운로드"><span id="excel_down_type">전체</span> 공모전 신청자리스트 엑셀 다운로드</a>
			</div>
			<div class="board_list mgt10px">
				<table class="list_tbl">
					<caption>공모전 접수 입력 테이블</caption>
					<colgroup>
						<col width="80" />
						<col width="160" />
						<col width="120" />
						<col width="120" />
						<col width="130" />
						<col width="160" />
						<col />
						<col width="150" />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>접수일자<br>(접수번호)</th>
							<th>응모부문</th>
							<th>응모자구분</th>
							<th>이름</th>
							<th>휴대폰번호</th>
							<th>작품제목/첨부파일</th>
							<th>접수상태</th>		
						</tr>
					</thead>
					<tbody>
				
					<c:forEach var="item" items="${select_list}">
						
						<tr>
							<td><span>${select_total-item.rn+1}</span></td>
							<td class="date"><c:out value="${item.regdate}"/><br>( <c:out value="${item.mbr_group}"/> )</td>
							<td class="date"><c:out value="${item.mbr_cate}"/></td>
							<td class="date"><c:out value="${item.mbr_class}"/></td>
							<td class="date"><a href="#;" onclick="open_detail('${item.mbr_group}','${item.idx}')"><b><c:out value="${item.mbr_name}"/></a></b></td>
							<td class="date"><c:out value="${item.mbr_hp}"/></td>
							<td class="date"><b><c:out value="${item.work_subject}"/></b>
								<br><br>
								<c:if test="${not empty item.bo_atch_file}">				
									<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${item.bo_atch_file}" />
							        </c:import>
				                </c:if>
							</td>
							<td>
								<select id="status_${item.idx}" onchange="change_status(${item.idx})" title="접수상태검색조건선택">
									<option value="접수대기" <c:if test="${item.mbr_status eq '접수대기' }">selected</c:if>>접수대기</option>
									<option value="접수보류" <c:if test="${item.mbr_status eq '접수보류' }">selected</c:if>>접수보류</option>
									<option value="접수완료" <c:if test="${item.mbr_status eq '접수완료' }">selected</c:if>>접수완료</option>
									<option value="접수숨김" <c:if test="${item.mbr_status eq '접수숨김' }">selected</c:if>>접수숨김</option>
									<option value="접수삭제" <c:if test="${item.mbr_status eq '접수삭제' }">selected</c:if>>접수삭제</option>									
								</select>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${select_list eq null }">
						<tr>			
							<td colspan="8">등록된 게시물이 없습니다.</td>
						</tr>					
					</c:if>
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
function change_status(idx){
	if(!idx){
		alert("참여자 데이터를 불러올 수 없습니다.");
		return false;
	}
	
	var isRefresh = false;
	var update_status=$('#status_'+idx).val();
	if(update_status == '접수삭제') {
		if(!confirm('삭제 후에는 복구가 불가능합니다.\n삭제 하시겠습니까?')) {
			return;
		}	
		isRefresh = true;
	} else if(update_status == '접수숨김') {
		isRefresh = true;
	}
	$.ajax({
		type:"GET",
		url :"/mbrstatus_change.do",
		data:{"idx":idx,"status":update_status},
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			
		},
		complete : function() {
			/*
			if(isRefresh) {
				self.location.reload();
			}
			*/
			self.location.reload();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			 
		}
	});
	
}

function go_excel(){
	var form = document.getElementById("frm");
	form.action = "/congressMbrGongmoExcelDownload.do";
	form.submit();
}

function fnSelectInfs(pageIndex) {
	
	$("#pageUnit").val();
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "/gongmo/gongmo/list_show.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

function open_detail(gkey,idx) {
	if(!$.trim(idx)){
		alert("해당 대회를 찾을 수 없습니다.");
		return false;
	}
	var form = document.getElementById('listform');
	var url = "/gongmo/gongmo/mbr_detail.do";
	window.open("about:blank","mbr_detail","width=1200,height=800,scrollbars=yes");
	$('#idx').val(idx);
	form.action = url;
	form.method = "post";
	form.target = "mbr_detail";
	form.submit();
}


function select_status(obj) {
	var v = '';
	if(obj.value == '') {
		v = '전체';
	} else {
		v = obj.value;
	}
	$('#excel_down_type').html(v);
}


</script>