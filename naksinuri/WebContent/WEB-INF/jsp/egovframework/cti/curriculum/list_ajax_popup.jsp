<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="listTotalCnt" value="0" />

<div class="panel-body p-0 pb-10">
	<div class="col-md-12 p-0 input-group">
		<div class="col-md-10 p-0 pr-5">
			<select class="form-control selec2_manual-${CUSTOM_UNIQ_KEY}" id="sel_ajax_curriculum_info_${CUSTOM_UNIQ_KEY}" data-style="btn-dark text-white" >
				<c:forEach var="item" varStatus="status" items="${list}">
					<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
		       		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
		       		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
		       		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
		       		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) " var="CRS_STR_DT"/>
		       		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH:mm" var="CRS_END_DT"/>
		       		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd" var="RECRUIT_STR_DT"/>
		       		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd" var="RECRUIT_END_DT"/>
		       		<fmt:formatDate value="${parse_crs_str_dt}" pattern="HH:mm" var="CRS_STR_TIME"/>
		       		<fmt:formatDate value="${parse_crs_end_dt}" pattern="HH:mm" var="CRS_END_TIME"/>
		       		
		       		<c:set var="isHiddenInfo" value="true" />
		       		<c:set var="addLabelInfo" value="[모집마감]" />
		       		<%-- //신청 버튼 활성화 로직 --%>					
					<c:set var="_today" value="<%=new java.util.Date()%>" />
					<fmt:formatDate value="${_today}" pattern="yyyy-MM-dd HH:mm:ss" var="CAL_TODAY"/>
					<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd 00:00:00" var="CAL_RECRUIT_STR_DT"/>
				    <fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd 23:59:59" var="CAL_RECRUIT_END_DT"/>
				    <c:if test="${CAL_RECRUIT_STR_DT  <= CAL_TODAY and CAL_TODAY <= CAL_RECRUIT_END_DT}">
				    	<c:set var="ispassed" value="true" />
				    	<c:set var="isHiddenInfo" value="false" />
				    	<c:set var="addLabelInfo" value="" />
				    	<%-- //신청 버튼 활성화 로직 : 모집인원마감 --%>
				    	<c:if test="${not empty item.MBR_MAX_CNT and item.MBR_MAX_CNT != 0 and item.MBR_CUR_CNT+1 > item.MBR_MAX_CNT}">
				    		<c:set var="ispassed" value="false" />
				    		<c:set var="isHiddenInfo" value="true" />
				    		<c:set var="addLabelInfo" value="[모집마감]" />				    		
				    	</c:if>			    	
					    <%-- //End of 신청 버튼 활성화 로직 : 모집인원마감 --%>
					    <c:if test="${ispassed eq true }">		
					    	<c:set var="listTotalCnt" value="${listTotalCnt+1}" />
					    </c:if>
				    </c:if>
				    <%-- End //신청 버튼 활성화 로직 --%>
				    
		       		<c:if test="${isHiddenInfo eq false }">		       		
			       		<option value="${item.CRS_SN}">
			       			<c:if test="${not empty addLabelInfo}">${addLabelInfo}</c:if>
			       			<c:forEach var="cate" items="${list_all_edu_grp_cd}">
			           			<c:if test="${item.CRS_GRP_CD eq cate.CD_ID}">${cate.CD_NM}</c:if>
							</c:forEach>
							| ${item.CRS_NM} 
							| ${CRS_STR_DT}&nbsp;${CRS_STR_TIME}&nbsp;~&nbsp;${CRS_END_TIME} <span class="grey-500">(${RECRUIT_STR_DT}&nbsp;~&nbsp;${RECRUIT_END_DT})</span>
							| <span class="font-size-12">${item.CRS_PLACE}</span> <span class="font-size-12 grey-500">${item.CRS_ADDR}</span>
							| <span class="badge badge-warning">${item.MBR_CUR_CNT}명</span>&nbsp;/&nbsp;
				              <span class="badge badge-info">
								<c:choose>
		                			<c:when test="${item.MBR_MAX_CNT eq 0}">무제한</c:when>
		                			<c:otherwise>${item.MBR_MAX_CNT}명</c:otherwise>				                	
			                	</c:choose>
			                  </span>
			       		</option>
		       		</c:if>
		       		
				</c:forEach>
			</select>
		</div>
		<div class="col-md-2 p-0 text-right">
			<button type="button" class="btn btn-warning clk-edu-add-${CUSTOM_UNIQ_KEY}">교육 신청하기</button>
		</div>
	</div>
</div>
<script>
$(function(){
	$('#ctiCurriculumListTotalLabel${CUSTOM_UNIQ_KEY}').html('${listTotalCnt}');
	$(".selec2_manual-${CUSTOM_UNIQ_KEY}").select2();
});
</script>

