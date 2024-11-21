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
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value=""/>
<input type="hidden" id="CRTF_SN" name="CRTF_SN" value=""/>
</form:form>

<form:form commandName="myHistoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value=""/>
</form:form>

<form:form commandName="myHistoryVO" id="listForm" name="listForm" method="post">
<div id="fishjobList" class="content respon2">
	<section id="webzineList" class="list_box">
		<!-- <div class="list_searchbox">

		</div> -->
		<div class="">
			<c:set var="total_page" value="${(paginationInfo.totalRecordCount/paginationInfo.recordCountPerPage)}"/>
			<fmt:parseNumber var="total_page" integerOnly = "true" type = "number" value = "${total_page+(1-(total_page%1))%1}" />
			<div class="total_num">전체 <b class="colorSky">${paginationInfo.totalRecordCount}</b>건의 게시물이 있습니다.(<b class="colorSky">${paginationInfo.currentPageNo}</b>/${total_page})</div>
		</div>
		<div class="board_list">
			<table class="list_tbl">
				<caption>이수증내역</caption>
				<colgroup>
				</colgroup>
				<thead>
					<tr class="table-cell-blind"><th></th></tr>
				</thead>
				<tbody>
					<c:if test="${empty list}">
						<tr><td colspan="7" class="text-center table-active">조회 가능한 이수증내역이 없습니다.</td></tr>	  
	            	</c:if>
					<c:forEach var="item" varStatus="status" items="${list}">
	           		<tr>	
						<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd" scope="page"/>
					<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd" scope="page"/>
					<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
					<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
					<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) HH시 부터" var="CRS_STR_DT"/>
					<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH시 까지" var="CRS_END_DT"/>
					<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_STR_DT"/>
					<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_END_DT"/>
            		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy" var="TRGT_YEAR"/>
	           		
	           		<!-- 교육분류에 따른 표기 처리 : start -->
	           		<c:choose>
						<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">
							<c:set var="label_crs_time" value="개월" />
							<c:set var="is_allow_online" value="0" />
						</c:when>
						<c:otherwise>
							<c:set var="label_crs_time" value="시간" />
							<c:set var="is_allow_online" value="1" />
						</c:otherwise>											
					</c:choose>
					<!-- 교육분류에 따른 표기 처리 : end -->
					<c:choose>
						<c:when test="${item.TYPE_GB eq 'online'}">
							<c:set var="is_allow_online" value="1" />
						</c:when>
						<c:otherwise>
							<c:set var="is_allow_online" value="0" />
						</c:otherwise>											
					</c:choose>
					
					<!-- //학습률 계산// -->
	                <%-- <c:choose>
						<c:when test="${is_allow_online eq '1'}">
							<c:set var="calc" value="${ (item.HMBR_INPUT_TIME + (item.LRNNG_PRGRS*item.SUM_TOT_TIME)) / (item.CRS_TOT_TIME+item.SUM_TOT_TIME) }"/>
						</c:when>
						<c:otherwise>
							<c:set var="calc" value="${item.HMBR_INPUT_TIME/item.CRS_TOT_TIME}"/>
						</c:otherwise>											
					</c:choose>
					<c:if test="${calc == 'NaN'}">
						<c:set var="calc" value="0"/>
					</c:if> --%>
		            <!-- //학습률 계산// -->
					
					<td>
            			<p class="text-left font-size-18 line-height-22">${TRGT_YEAR}&nbsp;년도&nbsp;<c:forEach var="item_category" items="${list_mbr_cd}">
		        				<c:if test="${item.CRS_MBR_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
							</c:forEach>&nbsp;전문교육</p>
            			<p class="text-left line-height-22">
							<a href="#;" class="clk_modal_toggle"
		                	data-crs-sn="${item.CRS_SN}"
		                	data-linkurl="/educenter/m/trnng/view.do">
								<strong>${item.CRS_NM}</strong>
								<%-- <small>
									<c:choose>
										<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">[귀어창업기술교육]</c:when>
										<c:when test="${item.CRS_TYPE eq 'wknd_trnng'}">[주말교육]</c:when>
										<c:when test="${item.CRS_TYPE eq 'default'}">[종합교육]</c:when>
										<c:when test="${item.CRS_TYPE eq 'default_online'}">[온라인강좌]</c:when>
										<c:otherwise>[기타]</c:otherwise>																				
									</c:choose>
								</small> --%>
							</a>
						</p>
						<p class="text-left line-height-22">
							<c:forEach var="item_category" items="${list_edu_grp_cd}">
		        				<c:if test="${item.CRS_GRP_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
							</c:forEach>&nbsp;
							<span class="badge badge-outline badge-dark">총 ${item.CRS_TOT_TIME + item.SUM_TOT_TIME} ${label_crs_time}</span>
		                	<%--<span class="badge badge-outline badge-dark">총 ${item.CRS_TOT_POINT + item.SUM_TOT_POINT} 점</span> --%>
						</p>
						<p class="text-left line-height-22">${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}</p>
		                <p class="text-right line-height-22 mt-10">
		                	<c:choose>
		                		<c:when test="${item.LRNNG_CMPLT_ST eq 1 or item.LRNNG_CMPLT_ST eq 3}">
		                			<c:choose>
		                			<c:when test="${item.LRNNG_ST eq 1}">
			                			<!-- 이수완료 -->
			                		</c:when>
			                		<c:otherwise>
			                			교육중
			                		</c:otherwise>	
			                		</c:choose>
		                		</c:when>
		                		<c:otherwise></c:otherwise>				                	
		                	</c:choose>
		                	<c:choose>
		                		<c:when test="${(item.LRNNG_CMPLT_ST eq 1 or item.LRNNG_CMPLT_ST eq 3) and item.LRNNG_ST eq 1}">
		                			<button type="button" class="btn btn-outline btn-primary btn-xs" onclick="pageMove(this)" 
			                		data-crs-sn="${item.CRS_SN}"
			                		data-hmbr-sn="${item.HMBR_SN}"
			                		data-crtf-sn="${item.CRTF_SN}"    
			                		>열람하기</button>
		                		</c:when>
		                		<c:otherwise>
		                			
		                		</c:otherwise>				                	
		                	</c:choose>
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
function pageMove(obj) {
	var form = document.getElementById('listForm3');
	form.CRS_SN.value = $(obj).attr('data-crs-sn');
	form.HMBR_SN.value = $(obj).attr('data-hmbr-sn');
	form.CRTF_SN.value = $(obj).attr('data-crtf-sn');
	form.action = "/educenter/m/mbrhstry/listCrtfDtl.do";
	form.submit();
}
function fnLinkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "";
   	document.listForm.submit();
}
$(".clk_modal_toggle").click(function() {
	var form = document.getElementById('listForm2');
	form.CRS_SN.value = $(this).attr('data-crs-sn');
	form.HMBR_SN.value = '';
	form.action = '';
	var data_linkurl = $(this).attr('data-linkurl');
	$.ajax({
		type:"POST",
		url :data_linkurl,
		data:$('#listForm2').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#allPublicModal").html(data);
			$("#allPublicModal").modal('show');
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
});  
</script>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
