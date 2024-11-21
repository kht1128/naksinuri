<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/head.jsp"%>


<form:form commandName="myHistoryVO" id="listForm3" name="listForm3" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${parentInfo.HMBR_SN}"/>
<input type="hidden" id="CRTF_SN" name="CRTF_SN" value="${parentInfo.CRTF_SN}"/>
<input type="hidden" id="CRTF_DTL_SN" name="CRTF_DTL_SN" value=""/>
</form:form>

<form:form commandName="myHistoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${parentInfo.HMBR_SN}"/>
<input type="hidden" id="CRTF_SN" name="CRTF_SN" value="${parentInfo.CRTF_SN}"/>
</form:form>

<form:form commandName="myHistoryVO" id="listForm" name="listForm" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${parentInfo.HMBR_SN}"/>
<input type="hidden" id="CRTF_SN" name="CRTF_SN" value="${parentInfo.CRTF_SN}"/>

<div id="fishjobList" class="content respon2">
	<section id="webzineList" class="list_box">
		<p class="mb-60">'<b><span class="cyan-800">이수증발급요청</span>서 작성</b>'을 하시면 이수증을 출력 하실 수 있습니다.
			<button type="button" class="btn btn-outline btn-danger clk_crtf_write float-right mt-10">이수증발급요청</button>
		</p>
		<!-- <div class="list_searchbox">

		</div> -->
		<div class="">
			<c:set var="total_page" value="${(paginationInfo.totalRecordCount/paginationInfo.recordCountPerPage)}"/>
			<fmt:parseNumber var="total_page" integerOnly = "true" type = "number" value = "${total_page+(1-(total_page%1))%1}" />
			<div class="total_num">전체 <b class="colorSky">${paginationInfo.totalRecordCount}</b>건의 게시물이 있습니다.(<b class="colorSky">${paginationInfo.currentPageNo}</b>/${total_page})</div>
		</div>
		<div class="board_list">
			<table class="list_tbl">
				<caption>이수증발급내역</caption>
				<colgroup>
				</colgroup>
				<thead>
					<tr class="table-cell-blind"><th></th></tr>
				</thead>
				<tbody>
					<c:if test="${empty list}">
						<tr><td colspan="7" class="text-center table-active">조회 가능한 이수증 발급 내역이 없습니다.</td></tr>	  
	            	</c:if>
					<c:forEach var="item" varStatus="status" items="${list}">
	           		<tr>	
						<fmt:parseDate value="${fn:replace(item.REG_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
		           		<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy년 MM월 dd일 HH시 mm분(E)" var="REG_DT"/>
		           		<fmt:parseDate value="${fn:replace(item.UPD_DT, '.0', '')}" var="parse_upd_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
		           		<fmt:formatDate value="${parse_upd_dt}" pattern="yyyy년 MM월 dd일 HH시 mm분(E)" var="UPD_DT"/>
		           		
						<td>
	            			<p class="text-left font-size-18 line-height-22"><span class="font-weight-bold">${item.CRTF_TYPE_ST}</span>&nbsp;<span class="badge badge-outline badge-dark">${item.MBR_NM}</span></p>
	            			<p class="text-left line-height-22">발급일자 : ${REG_DT}</p>
	            			<p class="text-left line-height-22">열람일자 : 
	            				<c:if test="${item.CRTF_CMPLT_ST eq '0'}">
									<span class="brown-400">현재 이수증 출력이 가능합니다.</span>
								</c:if>
								<c:if test="${item.CRTF_CMPLT_ST eq '1'}">
			                		${UPD_DT}
			                	</c:if>
	            			</p>							
							<p class="text-right line-height-22">
	            				<c:if test="${item.CRTF_CMPLT_ST eq '0'}">
			                		<button type="button" class="btn btn-outline btn-primary btn-xs clk_modal_toggle"  
				                		data-crtf-dtl-sn="${item.CRTF_DTL_SN}"    
				                		>출력하기</button>
			                	</c:if>
			                	<c:if test="${item.CRTF_CMPLT_ST eq '1'}">
			                		<span class="badge badge-outline badge-default grey-600">출력완료</span>
			                	</c:if>
	            			</p>
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
function directReload() {//이수증 열람시 페이지 갱신을 위한 처리
	document.listForm.submit();  
}
function fnLinkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "";
   	document.listForm.submit();
}
$(".clk_crtf_write").click(function() {
	var form = document.getElementById('listForm2');
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/educenter/mbrhstry/issueCrtf.do",
		data:$('#listForm2').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		beforeSend : function(){
			var clkCrtfWrite = $(".clk_crtf_write");
			clkCrtfWrite.text("잠시만 기다려주세요.");
			clkCrtfWrite.attr("disabled", true);
		},
		success: function(data, status, xhr) {
			//console.log('success!');
			$(".clk_crtf_write").text("이수증발급요청");
			$("#allPublicModal").html(data);
			$("#allPublicModal").modal('show');
		},
		complete : function() {
			$(".clk_crtf_write").attr("disabled", false);
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
});  
$(".clk_modal_toggle").click(function() {
	var gsWin = window.open("about:blank","winCrtf");
	var form = document.getElementById('listForm3');
	form.CRTF_DTL_SN.value = $(this).attr('data-crtf-dtl-sn');
	form.action = "/educenter/mbrhstry/viewCrtf.do";
	form.target = "winCrtf";
	form.submit();
});  
</script>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
