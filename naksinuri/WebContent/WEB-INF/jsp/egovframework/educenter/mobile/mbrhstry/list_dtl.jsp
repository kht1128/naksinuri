<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/head.jsp"%>

<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
pageContext.setAttribute("sp", " ");
</jsp:scriptlet> 

<style>
#counselGuide .timex_phone {
	padding:10px 20px;
}
</style>


<form:form commandName="eduMyHistoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${parentInfo2.HMBR_SN}"/>
<input type="hidden" id="HMBR_DTL_SN" name="HMBR_DTL_SN" value=""/>
</form:form>

	          		
<fmt:parseDate value="${fn:replace(parentInfo.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd" scope="page"/>
<fmt:parseDate value="${fn:replace(parentInfo.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd" scope="page"/>
<fmt:parseDate value="${fn:replace(parentInfo.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
<fmt:parseDate value="${fn:replace(parentInfo.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E)" var="CRS_STR_DT"/>
<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E)" var="CRS_END_DT"/>
<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_STR_DT"/>
<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_END_DT"/>
    		           	
<fmt:parseDate value="${fn:replace(parentInfo2.REG_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd" scope="page"/>
<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy-MM-dd (E)" var="REG_DT"/>   		           	
    		           	
    		           	
<!-- 교육분류에 따른 표기 처리 : start -->
<c:choose>
	<c:when test="${parentInfo.CRS_TYPE eq 'fshsk_trnng'}">
		<c:set var="label_crs_time" value="개월" />
		<c:set var="is_allow_online" value="0" />
	</c:when>
	<c:otherwise>
		<c:set var="label_crs_time" value="시간" />
		<c:set var="is_allow_online" value="1" />
	</c:otherwise>											
</c:choose>
<!-- 교육분류에 따른 표기 처리 : end -->	           		           	


<div id="honorObserver" class="content respon2 pb-10">
	<section id="conferenceCalendarEdu" class="mb-10">
		<h3>${parentInfo.CRS_NM}<%--  <span class="topspan">${parentInfo.CAT_INS_NM}</span> --%></h3>
		<div class="timex_phone floats mgt30px">
			<dl class="nopm">
				<dt>수강신청일자</dt>
				<dd class="label-css-type-1">${REG_DT}</dd>
			</dl>
		</div>
	</section>
	<section id="conferenceCalendarEdu" class="mb-10">
		<div class="timex_phone floats mg10px">
			<dl class="">
				<dt>이수완료(건) ( ${parentInfo2.LRNNG_CMPLT_CNT} / ${paginationInfo.totalRecordCount} )</dt>
				<dd>
					<div class="progress">
						<fmt:formatNumber var="calc1" type="percent" value="${parentInfo2.LRNNG_CMPLT_CNT/paginationInfo.totalRecordCount}"  pattern="0.00%"/>
						<div class="progress-bar" role="progressbar" style="width:${calc1};" aria-valuenow="${parentInfo2.LRNNG_CMPLT_CNT/paginationInfo.totalRecordCount*100}" aria-valuemin="0" aria-valuemax="100">${calc1}</div>
					</div>
				</dd>
				<dt>학습진행률(%)</dt>
				<dd>
					<div class="progress">
						<fmt:formatNumber var="calc2" type="percent" value="${parentInfo2.LRNNG_PRGRS}"  pattern="0.00%"/>
						<div class="progress-bar" role="progressbar" style="width:${calc2}" aria-valuenow="${parentInfo2.LRNNG_PRGRS*100}" aria-valuemin="0" aria-valuemax="100">${calc2}</div>
					</div>
				</dd>
			</dl>
		</div>
	</section>
	<section id="conferenceCalendarEdu" class="mb-10">
		<div class="timex_phone floats mgt10px">
			<div class="timex">
				<p>총 이수된 온라인 교육점수</p>
				<span class="counter-number">${parentInfo2.LRNNG_TOT_TIME}시간&nbsp;<span class="counter-number-sub">/ 총 ${parentInfo2.HMBR_MAX_TIME - parentInfo.CRS_TOT_TIME} 시간</span></span>
			</div>
		</div>
	</section>
</div>

<!-- 교육이수내역 > 수강현황 > 교과목리스트 { -->
<form:form commandName="myHistoryVO" id="listForm" name="listForm" method="post">
<input type="hidden" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" name="HMBR_SN" value="${parentInfo2.HMBR_SN}"/>


<div id="fishjobList" class="content respon2">
	<section id="webzineList" class="list_box">
		<div class="">
			<c:set var="total_page" value="${(paginationInfo.totalRecordCount/paginationInfo.recordCountPerPage)}"/>
			<fmt:parseNumber var="total_page" integerOnly = "true" type = "number" value = "${total_page+(1-(total_page%1))%1}" />
			<div class="total_num">전체 <b class="colorSky">${paginationInfo.totalRecordCount}</b>건의 게시물이 있습니다.(<b class="colorSky">${paginationInfo.currentPageNo}</b>/${total_page})</div>
		</div>
		<div class="board_list">
			<table class="list_tbl">
				<caption>조회리스트</caption>
				<colgroup>
					<col />
				</colgroup>
				<thead>
					<tr>
						<th>교과목정보</th>
					</tr>
				</thead>
				<tbody>
				
					<c:if test="${empty curriculum_dtl_list}">
						<tr>
							<td class="no alignCenter" >현재 수강가능한 교과목리스트가 없습니다.</td>
						</tr>	  
		           	</c:if>           	
		           	<c:forEach var="item" varStatus="status" items="${curriculum_dtl_list}">
						<tr class="noti">
						<td class="pl-0 pt-0 pb-0 pr-0">
							<ul class="lecture_ul nopm">
							<li>
								<div class="subject">
									<a href="#;">
										<strong>${item.CRS_DTL_NM}</strong>
										<small>교육시간 : ${item.CRS_TOT_TIME}</small>
									</a>
								</div>
								<div class="desc">
									<span class="badge badge-outline badge-dark">
				                		<fmt:formatNumber type="percent" value="${item.HMBR_LRNNG_PRGRS}"  pattern="0.00%"/>
				                	</span>
				                	<span class="badge badge-outline badge-dark">
					                	<c:choose>
					                		<c:when test="${item.HMBR_LRNNG_PRGRS >= 1.0}">이수완료</c:when>
					                		<c:otherwise>미완료</c:otherwise>				                	
					                	</c:choose>
					                </span>
				                	<span class="room">
				                		<button type="button" class="btn btn-outline btn-primary btn-xs btn-act-more-dtl-open" 
				                			id="btn_act_more_dtl_open_${status.count}" 
					                		data-cnt="${status.count}">상세보기 열기</button>
				                		<button type="button" class="btn btn-outline btn-primary btn-xs btn-act-more-videolist-open" 
					                		data-cnt="${status.count}">수강하기 열기</button>
					                </span>
								</div>
			           			<div id="div_act_more_dtl_open_${status.count}" style="display:none;">
									<pre class="pgt10px pgb10px pgl10px pgr10px font-base">${item.CRS_DTL_DSCRP}</pre>
								</div>
								<div id="div_act_more_videolist_open_${status.count}" style="display:none;">
									
									<ul class="lecture_ul nopm" style="border-top:2px solid #22be94;border-bottom:1px solid #22be94;">
										<c:set var="recnt" value="1"/>
										<c:forEach var="rowitem" varStatus="rowstatus" items="${list}">
											<c:if test="${item.CRS_DTL_SN eq rowitem.CRS_DTL_SN}">
											<li style="background:#fafafa;">
												<%--
												<em class="date"><small>${rowstatus.count}</small></em>
												 --%>
												<div class="subject">
													<a href="#;">
														<strong>${rowitem.TRN_NM}</strong>
													</a>
												</div>
												<div class="desc">
													<span class="badge badge-outline badge-dark">
								                		<fmt:formatNumber type="percent" value="${rowitem.LRNNG_PRGRS}"  pattern="0.00%"/>
								                	</span>
								                	<span class="badge badge-outline badge-dark">
									                	<c:choose>
									                		<c:when test="${rowitem.LRNNG_CMPLT_DTL_ST eq 1}">이수완료</c:when>
									                		<c:otherwise>미완료</c:otherwise>				                	
									                	</c:choose>
									                </span>
													<span class="room">
														<a href="#;" class="colorBlue clk_modal_toggle"
														data-hmbr-dtl-sn="${rowitem.HMBR_DTL_SN}"
														data-linkurl="/educenter/m/mbrhstry/videoplayer.do">
															<span class="secretico red-800"><i class="fa fa-video-camera"></i></span>
														</a>
													</span>
												</div>
											</li>
											<c:set var="recnt" value="${recnt+1}"/>
											</c:if>
							           	</c:forEach>
									</ul>
									
								</div>
							</li>
							</ul>
						</td>
						</tr>
					</c:forEach>
				
				</tbody>
			</table>
		</div>
		<%-- <nav aria-label="Page navigation" class="alignCenter" style="display:none;">
			<ul class="pagination">
				<ui:pagination paginationInfo = "${paginationInfo}" type="default" jsFunction="fn_egov_link_page" />
    			<form:hidden path="pageIndex" />
			</ul>
		</nav> --%>
	</section>
	
</div>
</form:form>
<!-- } 교육이수내역 > 수강현황 -->

<script>
//상세보기 버튼
$(document).on('click',".btn-act-more-dtl-open",function() {
	var targetBtn = $(this);
	var datacnt = $(this).attr('data-cnt');
	var isshow = $('#div_act_more_dtl_open_'+datacnt).is(':visible');
	toggleActDtlShow(isshow,datacnt,targetBtn);
});
function toggleActDtlShow(isshow,datacnt,targetBtn) {
	if(isshow) { //닫기
		$('#div_act_more_dtl_open_'+datacnt).slideToggle(300,function(){
			$('#tr_act_more_dtl_open_'+datacnt).hide();
			$(targetBtn).text('상세보기 열기');
		});
	} else {//열기
		$('#tr_act_more_dtl_open_'+datacnt).show();
		$('#div_act_more_dtl_open_'+datacnt).slideToggle(300,function(){
			$(targetBtn).text('상세보기 닫기');
		});
	}
}
//플레이리스트 버튼
$(document).on('click',".btn-act-more-videolist-open",function() {
	var targetBtn = $(this);
	var datacnt = $(this).attr('data-cnt');
	var isdtlshow = $('#div_act_more_dtl_open_'+datacnt).is(':visible');
	var isshow = $('#div_act_more_videolist_open_'+datacnt).is(':visible');
	toggleActPlayList(isshow,isdtlshow,datacnt,targetBtn);
});
function toggleActPlayList(isshow,isdtlshow,datacnt,targetBtn) {
	if(isshow) { //닫기
		$('#div_act_more_videolist_open_'+datacnt).slideToggle(300,function(){
			$('#tr_act_more_videolist_open_'+datacnt).hide();
			$(targetBtn).text('수강하기 열기');
		});
	} else {//열기
		if(isdtlshow) {
			var targetDtlBtn = $('#btn_act_more_dtl_open_'+datacnt);
			toggleActDtlShow(isdtlshow,datacnt,targetDtlBtn);
		}
		$('#tr_act_more_videolist_open_'+datacnt).show();
		$('#div_act_more_videolist_open_'+datacnt).slideToggle(300,function(){
			$(targetBtn).text('수강하기 닫기');
		});
	}
}
function pageMove(obj) {
	var form = document.getElementById('listForm2');
	form.CRS_SN.value = $(obj).attr('data-crs-sn');
	form.HMBR_SN.value = $(obj).attr('data-hmbr-sn');
	form.typeStr.value = '';
	form.action = "/educenter/m/mbrhstry/listDtl.do";
	form.submit();
}
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "";
   	document.listForm.submit();
}
$(".clk_modal_toggle").click(function() {
/* 	
	if(!confirm('다른 화면 또는 브라우저에서 이미 동영상을 시청중인 경우 취소버튼을 눌러 닫으시기 바랍니다.\n(하나 이상의 플레이어가 실행 되면 중복실행으로 감지되어 종료 될 수 있습니다.)\n계속 하시겠습니까?')) {
		return;
	} */
	
	var form = document.getElementById('listForm2');
	form.HMBR_DTL_SN.value = $(this).attr('data-hmbr-dtl-sn');
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
			$("#allPublicModal").modal({backdrop: 'static', keyboard: false});
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

<link href="/common/js/video-js/video.js_7.3.0.css" rel="stylesheet">
<script src="/common/js/video-js/video.js_7.3.0.js"></script>	

<%@include file="../../../naksinuri_original/naksinuri/layout/m/tail.jsp"%>
