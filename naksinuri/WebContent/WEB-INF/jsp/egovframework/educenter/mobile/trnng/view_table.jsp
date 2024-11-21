<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
	
<form:form commandName="eduMyHistoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value=""/>
</form:form>	


<ul class="grpedu_ul">
	<li><ul class="grpedu_ul_1">
	<!-- 낚시어선업자 //-->
	<c:if test="${empty list_default_1}">
		<li>
			<div class="grpedu_Pn">
				<span class="tag">낚시어선업자 및 선원</span>
				<a href="#;">
					<p class="dates">준비된 교육이 없습니다.</p>
				</a>
			</div>
		</li>
	</c:if>
	<c:forEach var="item" varStatus="status" items="${list_default_1}" >			
		<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-mm-dd" scope="page"/>
		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-mm-dd" scope="page"/>
		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-mm-dd" scope="page"/>
		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-mm-dd" scope="page"/>
		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.mm.dd" var="CRS_STR_DT"/>
		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy.mm.dd" var="CRS_END_DT"/>
		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy.mm.dd" var="RECRUIT_STR_DT"/>
		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy.mm.dd" var="RECRUIT_END_DT"/>
		<li>
			<div class="grpedu_Pn">
				<span class="tag">낚시어선업자 및 선원</span>
				<a href="#;" class="clk_modal_eduinfo" 
	          		data-crs-sn="${item.CRS_SN}"
	          		data-linkurl="/educenter/m/trnng/view.do">
					<p class="dates">${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}</p>
					<p class="sbj">${item.CRS_PLACE}</p>
					<div class="cont">
						${item.CRS_ADDR}    
					</div>
				</a>
			</div>
		</li>
	</c:forEach>
	<!--// 낚시어선업자 -->	
	</li></ul>
	<li><ul class="grpedu_ul_2">
	<!-- 낚시터업자 //-->
	<c:if test="${empty list_default_2}">
		<li>
			<div class="grpedu_Pn">
				<span class="tag">낚시터업자</span>
				<a href="#;">
					<p class="dates">준비된 교육이 없습니다.</p>
				</a>
			</div>
		</li>
	</c:if>
	<c:forEach var="item" varStatus="status" items="${list_default_2}" >			
		<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-mm-dd" scope="page"/>
		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-mm-dd" scope="page"/>
		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-mm-dd" scope="page"/>
		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-mm-dd" scope="page"/>
		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.mm.dd" var="CRS_STR_DT"/>
		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy.mm.dd" var="CRS_END_DT"/>
		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy.mm.dd" var="RECRUIT_STR_DT"/>
		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy.mm.dd" var="RECRUIT_END_DT"/>
		<li>
			<div class="grpedu_Pn">
				<span class="tag">낚시터업자</span>
				<a href="#;" class="clk_modal_eduinfo" 
	          		data-crs-sn="${item.CRS_SN}"
	          		data-linkurl="/educenter/m/trnng/view.do">
					<p class="dates">${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}</p>
					<p class="sbj">${item.CRS_PLACE}</p>
					<div class="cont">
						${item.CRS_ADDR}      
					</div>
				</a>
			</div>
		</li>		
	</c:forEach>
	<!--// 낚시터업자 -->
	</li></ul>	
</ul>

<%--
<table class="table table-bordered">
	<caption>교육정보</caption>
	<colgroup>
		<col width="100px;">
	</colgroup>
	<tbody>
		<!-- // 종합교육 // -->
		<c:if test="${empty list_default}">
			<tr>
				<th class="text-center" >종합교육</th>
				<td class="text-center" >
					조회 가능한 교육과정이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:forEach var="item" varStatus="status" items="${list_default}" begin="0" end="0">			
			<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-mm-dd" scope="page"/>
       		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-mm-dd" scope="page"/>
       		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-mm-dd" scope="page"/>
       		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-mm-dd" scope="page"/>
       		<fmt:formatDate value="${parse_crs_str_dt}" pattern="mm.dd" var="CRS_STR_DT"/>
       		<fmt:formatDate value="${parse_crs_end_dt}" pattern="mm.dd" var="CRS_END_DT"/>
       		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="mm.dd" var="RECRUIT_STR_DT"/>
       		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="mm.dd" var="RECRUIT_END_DT"/>
       		
			<tr class="clk_modal_eduinfo" 
          		data-crs-sn="${item.CRS_SN}"
          		data-linkurl="/educenter/m/trnng/view.do">
				<c:if test="${status.first == true}">
					<th class="no_left text-center" >종합교육</th>
				</c:if>
				<td class="text-left">
					<b>${item.CRS_NM}</b><br/>
					교육일정 : ${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}<br/>
					장소 : ${item.CRS_PLACE}<br/>
					인원 : ${item.MBR_MAX_CNT}명
				</td>
			</tr>
			
		</c:forEach>
		<!-- End // 종합교육 // -->
		<!-- // 주말교육 // -->
		<c:if test="${empty list_wknd}">
			<tr class="last_tr">
				<th class="no_left text-center">주말교육</th>
				<td class="text-center" >
					조회 가능한 교육과정이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:forEach var="item" varStatus="status" items="${list_wknd}" begin="0" end="0">			
			<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-mm-dd" scope="page"/>
          		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-mm-dd" scope="page"/>
          		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-mm-dd" scope="page"/>
          		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-mm-dd" scope="page"/>
          		<fmt:formatDate value="${parse_crs_str_dt}" pattern="mm.dd" var="CRS_STR_DT"/>
          		<fmt:formatDate value="${parse_crs_end_dt}" pattern="mm.dd" var="CRS_END_DT"/>
          		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="mm.dd" var="RECRUIT_STR_DT"/>
          		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="mm.dd" var="RECRUIT_END_DT"/>
			<tr class="clk_modal_eduinfo" 
          		data-crs-sn="${item.CRS_SN}"
          		data-linkurl="/educenter/m/trnng/view.do">
				<th class="no_left text-center">주말교육</th>
				<td class="text-left">
					<b>${item.CRS_NM}</b><br/>
					교육일정 : ${CRS_STR_DT}<br/>
					장소 : ${item.CRS_PLACE}<br/>
					인원 : ${item.MBR_MAX_CNT}명
				</td>
			</tr>
		</c:forEach>
		<!-- End // 주말교육 // -->
		<!-- 온라인교육 -->
			<tr class="clk_modal_eduinfo" 
          		data-crs-sn="CRS_1902132940182530"
          		data-linkurl="/educenter/m/trnng/view.do">
				<th class="no_left text-center">온라인교육</th>
				<td class="text-left">
					<--<b>-</b><br/> -->
					교육일정 : 상시가능<br/>
					장소 : -<br/>
					인원 : 무제한
				</td>
			</tr>
		<!-- End 온라인교육 -->
	</tbody>
</table>
--%>

<script>
$('.grpedu_ul_1 , .grpedu_ul_2').bxSlider({
	auto: true,
	controls: false,
	pause: 3000
});
$(".clk_modal_eduinfo").click(function() {
	var form = document.getElementById('listForm2');
	form.CRS_SN.value = $(this).attr('data-crs-sn');
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
