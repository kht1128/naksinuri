<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../naksinuri_original/naksinuri/layout/newHead.jsp"%>

<style>
	.font14 {font-size: 14px;}
</style>

<form:form commandName="eduMyHistoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value=""/>
</form:form>

<form:form commandName="eduMyHistoryVO" id="cancelForm" name="cancelForm" method="post">
<input type="hidden" name="CRS_SN" value=""/>
<input type="hidden" name="HMBR_SN" value=""/>
</form:form>

<form:form commandName="myHistoryVO" id="surveyForm" name="surveyForm" method="post">
<input type="hidden" name="CRS_SN" value=""/>
<input type="hidden" name="HMBR_SN" value=""/>
</form:form>

<!-- 교육이수내역 > 수강현황 { -->
<form:form commandName="myHistoryVO" id="listForm" name="listForm" method="post">
	
	<div id="mbrhstryList" class="content respon2 pt-0">
		<section id="webzineList" class="list_box">
			<div class="list_searchbox font14">
			
				<c:set var="total_page" value="${(paginationInfo.totalRecordCount/paginationInfo.recordCountPerPage)}"/>
				<fmt:parseNumber var="total_page" integerOnly = "true" type = "number" value = "${total_page+(1-(total_page%1))%1}" />
				<div class="total_num">전체 <b class="colorSky">${paginationInfo.totalRecordCount}</b>건의 게시물이 있습니다.(<b class="colorSky">${paginationInfo.currentPageNo}</b>/${total_page})</div>
				
				<!-- <select class="basic_select" title="검색조건선택">
					<option>전체</option>
					<option>제목</option>
					<option>내용</option>
					<option>작성자</option>
				</select>
				<input type="text" class="basic_input" placeholder="검색어를 입력하세요" title="검색어입력"/>
				<button class="searchBtn"><i class="fa fa-search" aria-hidden="true" ></i></button> -->
				&nbsp;
				
			</div>
			<div class="board_list">
				<table class="list_tbl">
				<caption>나의신청내역</caption>
				<colgroup>
					<col width="130"/>
					<col />
					<col width="130"/>
					<col />
					<col />
					<col width="100"/>
					<col width="100"/>
				</colgroup>
				<thead>
					<tr>
						<th>교육대상년도</th>
						<th>교육지역</th>
						<th>교육대상</th>
						<th>교육과정명</th>
						<th>교육일시</th>
						<th>수료기준</th>
						<th>수강상태</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty list}">
						<tr style="justify-content: center;"><td colspan="7" class="text-center table-active noData" style="font-size: 14px;">현재 수강신청한 내역이 없습니다.</td></tr>	  
	            	</c:if>
	            	<c:forEach var="item" varStatus="status" items="${list}">

	            		<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
						<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
						<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
						<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
						<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) HH시 mm분 부터" var="CRS_STR_DT"/>
						<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH시 mm분 까지" var="CRS_END_DT"/>
						<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_STR_DT"/>
						<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd (E)" var="RECRUIT_END_DT"/>
	            		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy" var="TRGT_YEAR"/>
	            		
	            		<!-- 교육분류에 따른 표기 처리 : start -->
	            		<c:choose>
							<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">
								<c:set var="label_crs_time" value="개월" />
							</c:when>
							<c:otherwise>
								<c:set var="label_crs_time" value="시간" />
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
	            				            	
	            		<tr class="<c:if test="${status.index%2 eq 0}"></c:if>">
	            			<td class="text-center">${TRGT_YEAR}</td>
	            			<td class="text-center">
	            				<%-- <c:choose>
									<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">귀어창업기술교육</c:when>
									<c:when test="${item.CRS_TYPE eq 'wknd_trnng'}">주말교육</c:when>
									<c:when test="${item.CRS_TYPE eq 'default'}">종합교육</c:when>
									<c:when test="${item.CRS_TYPE eq 'default_online'}">온라인강좌</c:when>
									<c:otherwise>기타</c:otherwise>											
								</c:choose> --%>
								<c:forEach var="item_category" items="${list_edu_grp_cd}">
			        				<c:if test="${item.CRS_GRP_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
								</c:forEach>
	            			</td>
	            			<td class="text-center">
	            				<c:forEach var="item_category" items="${list_mbr_cd}">
			        				<c:if test="${item.CRS_MBR_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
								</c:forEach>
	            			</td>
			                <td class="text-center"><a href="#;" class="clk_modal_toggle"
			                	data-crs-sn="${item.CRS_SN}"
			                	data-linkurl="/educenter/trnng/view.do">${item.CRS_NM}</a></td>
			                	
			                <c:choose>
			                	<c:when test="${is_allow_online eq '1'}">
			                		<td class="text-center">상시</td>	
			                	</c:when>
			                	<c:otherwise>
			                		<td class="text-center">${CRS_STR_DT}&nbsp;<br>~&nbsp;${CRS_END_DT}</td>	
			                	</c:otherwise>			                
			                </c:choose>	
			                	
			                <%-- //학습률 계산// 
			                <c:choose>
								<c:when test="${is_allow_online eq '1'}">
									<c:set var="calc" value="${ (item.HMBR_INPUT_TIME + (item.LRNNG_PRGRS*item.SUM_TOT_TIME)) / (item.CRS_TOT_TIME+item.SUM_TOT_TIME) }"/>
								</c:when>
								<c:otherwise>
									<c:set var="calc" value="${item.HMBR_INPUT_TIME/item.CRS_TOT_TIME}"/>
								</c:otherwise>											
							</c:choose>
							<c:if test="${calc == 'NaN'}">
								<c:set var="calc" value="0"/>
							</c:if>--%>
							<%-- //학습률 계산// 
			                
			                <td class="text-center"><span class="badge badge-outline badge-danger"><fmt:formatNumber type="percent" value="${calc}"  pattern="0.00%"/></span></td>
			                --%>
			                <!-- 
			                <td class="text-center">
			                	<span class="badge badge-outline badge-dark">${item.HMBR_RCGNT_TIME} ${label_crs_time}</span>
			                	<br><span class="badge badge-outline badge-dark">${item.HMBR_RCGNT_POINT} 점</span>
			                </td>
			                -->
			                <td class="text-center">
			                	<span class="badge badge-outline badge-dark">총 ${item.CRS_TOT_TIME + item.SUM_TOT_TIME} ${label_crs_time}</span>
			                	<%--<span class="badge badge-outline badge-dark">총 ${item.CRS_TOT_POINT + item.SUM_TOT_POINT} 점</span> --%>
			                </td>
			                <td class="text-center">			                	
								<c:choose>
			                		<c:when test="${is_allow_online eq '1'}">
			                			<c:choose>
					                		<c:when test="${item.LRNNG_ST eq '0' or item.LRNNG_ST eq '1'}"><%-- 대기,승인 --%>
					                			<c:choose>
					                				<c:when test="${item.LRNNG_CMPLT_ST eq '1' and item.CRS_SV_ST eq '1' and item.HMBR_SV_ST eq '0' and item.TYPE_GB eq 'online'}">
					                					<span class="font-weight-bold red-600">설문조사 필요</span>
					                					<button type="button" class="btn btn-outline btn-info btn-xs mt-10" id="survey" 
					                					data-crs-sn="${item.CRS_SN}" data-hmbr-sn="${item.HMBR_SN}" onclick="moveSurvey();">설문조사</button>
					                				</c:when>
					                				<c:when test="${item.LRNNG_CMPLT_ST eq '1'}"><%-- 이수증발급완료 --%>
					                				     <span class="font-weight-bold red-600">이수완료</span>
					                				</c:when>
					                				<%-- <c:otherwise>
					                					<button type="button" class="btn btn-outline btn-primary btn-xs clk-mbrhstry-cancel"
									                		data-crs-sn="${item.CRS_SN}"
									                		data-hmbr-sn="${item.HMBR_SN}"    
									                		>신청취소</button>	
					                				</c:otherwise> --%>
					                			</c:choose>		
					                		</c:when>
					                		<c:when test="${item.LRNNG_ST eq '2'}"><%-- 취소 --%>
					                			<span class="font-weight-bold grey-400">취소</span>
					                		</c:when>
					                		<c:when test="${item.LRNNG_ST eq '4'}"><%-- 보류 --%>
					                			<span class="font-weight-bold grey-400">검토중</span>
					                		</c:when>
											<c:otherwise></c:otherwise>											
										</c:choose>	
			                			<button type="button" class="btn btn-outline btn-danger btn-xs mt-10 ml-0" onclick="pageMove(this)" 
				                		data-crs-sn="${item.CRS_SN}"
				                		data-hmbr-sn="${item.HMBR_SN}"  
				                		>강의실입장</button>
			                		</c:when>
			                		<c:otherwise>
			                			<c:choose>
					                		<c:when test="${item.LRNNG_ST eq '0'}"><%-- 대기(이수증외부출력불가) --%>
					                			<c:choose>
					                				<c:when test="${item.LRNNG_CMPLT_ST ne '1'}"><%-- 이수증발급완료 --%>
					                					<%-- <button type="button" class="btn btn-outline btn-primary btn-xs clk-mbrhstry-cancel"
									                		data-crs-sn="${item.CRS_SN}"
									                		data-hmbr-sn="${item.HMBR_SN}"    
									                		>신청취소</button> --%>              			
					                				</c:when>
					                				<c:otherwise>
					                					<span class="font-weight-bold ">신청완료</span>	
					                				</c:otherwise>
					                			</c:choose>		
					                		</c:when>
					                		<c:when test="${item.LRNNG_ST eq '2'}"><%-- 취소 --%>
					                			<span class="font-weight-bold grey-400">취소</span>
					                		</c:when>
					                		<c:when test="${item.LRNNG_ST eq '4'}"><%-- 보류 --%>
					                			<span class="font-weight-bold grey-400">검토중</span>
					                		</c:when>
											<c:otherwise><%-- 승인(이수증외부출력가능) --%>
												<span class="font-weight-bold red-600">이수완료</span>																
											</c:otherwise>											
										</c:choose>	
			                		</c:otherwise>
								</c:choose>														  
			                	<%--
			                	<c:choose>
			                		<c:when test="${item.LRNNG_ST eq '1'}">
			                			<c:choose>
											<c:when test="${item.PURCHASE_CMPLT_ST eq '1'}">
												<c:if test="${is_allow_online eq '1'}">
							                		<button type="button" class="btn btn-outline btn-primary btn-xs" onclick="pageMove(this)" 
							                		data-crs-sn="${item.CRS_SN}"
							                		data-hmbr-sn="${item.HMBR_SN}"  
							                		>강의실입장</button>
							                	</c:if>
							                	<c:if test="${is_allow_online eq '0'}">
							                		<span class="font-weight-bold blue-600">승인</span><!-- 온라인교육없음  -->
							                	</c:if>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-outline btn-info btn-xs" onclick="alert('준비중');" 
						                		data-crs-sn="${item.CRS_SN}"
						                		data-hmbr-sn="${item.HMBR_SN}"  
						                		>결제하기</button>
											</c:otherwise>											
										</c:choose>
			                		</c:when>
			                		<c:when test="${item.LRNNG_ST eq '4'}">
			                			<span class="font-weight-bold blue-600">대기자</span>
			                		</c:when>
									<c:otherwise>
										<span class="font-weight-bold blue-600">신청완료</span>																
									</c:otherwise>											
								</c:choose>	
								 --%>	                	
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
<!-- } 교육이수내역 > 수강현황 -->

<script>
function pageMove(obj) {
	var form = document.getElementById('listForm2');
	form.CRS_SN.value = $(obj).attr('data-crs-sn');
	form.HMBR_SN.value = $(obj).attr('data-hmbr-sn');
	form.action = "/educenter/mbrhstry/listDtl.do";
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
//20240812 취약점 조치로 삭제처리 김현태
/* $(".clk-mbrhstry-cancel").click(function() {
	
	var confm = confirm('신청취소를 하시면 일부 이수(온라인교육 등)하셨던 해당 교육의 모든 내역이 삭제되며 복구가 불가능합니다.\n신청을 취소 하시겠습니까?');
	if(!confm) {
		return;
	}
	
	var form = document.getElementById('cancelForm');
	form.CRS_SN.value = $(this).attr('data-crs-sn');
	form.HMBR_SN.value = $(this).attr('data-hmbr-sn');
	form.action = '';	
	$.ajax({
		type:"POST",
		url :"/educenter/trnng/cancel.do",
		data:$('#cancelForm').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			if(data.errCnt > 0) {
				var pass = true;
				for(var key in data.errField) {
					$('#'+data.errField[key]).addClass('is-invalid');
					pass = false;
				}
				if(pass) {
					allPublicModalMessage(data.msg);
				}
			} else {
				if(data.error == '1') {
					allPublicModalMessage(data.msg);
				} else {
					alert(data.msg);
					location.href = '/educenter/mbrhstry/list.do';	
				}
			}
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
		},
		complete : function() {
			//console.log('complete!');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			clickRequestLockStop();
		}
	});
}); */

function moveSurvey(){
	var survey = document.getElementById('survey');
	var form = document.getElementById('surveyForm');
	form.CRS_SN.value  = survey.getAttribute('data-crs-sn');
	form.HMBR_SN.value  = survey.getAttribute('data-hmbr-sn');
	form.action = "/educenter/mbrhstry/survey_view.do";
	form.submit();
}
	

</script>

<%@include file="../../naksinuri_original/naksinuri/layout/tail.jsp"%>
