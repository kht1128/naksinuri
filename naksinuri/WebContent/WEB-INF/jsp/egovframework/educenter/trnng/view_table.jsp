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

<style>
.bx-pager-item a:focus{border:3px solid #ff0000;} 
</style>

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
				<div class="tag">낚시어선업자 및 선원</div>
				<div class="clk_modal_eduinfo" data-crs-sn="${item.CRS_SN}" data-linkurl="/educenter/trnng/view.do" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$('.clk_modal_eduinfo').click();}" title="${item.CRS_PLACE} 상세보기">
					<p class="dates">${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}</p>
					<a href="javascript:void(0);" class="sbj" title="${item.CRS_PLACE}" data-crs-sn="${item.CRS_SN}"
	          		data-linkurl="/educenter/trnng/view.do" id="focusLine${status.index}">${item.CRS_PLACE}</a>
					<div class="cont">
						${item.CRS_ADDR}
					</div>
				</div>
			</div>
		</li>
	</c:forEach>
	<!--// 낚시어선업자 -->	
	</ul></li>
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
				<div class="tag">낚시터업자</div>
				<div class="clk_modal_eduinfo" data-crs-sn="${item.CRS_SN}" data-linkurl="/educenter/trnng/view.do" onkeyPress="if (event.keyCode==13 || event.keyCode==32){$('.clk_modal_eduinfo').click();}" title="${item.CRS_PLACE} 상세보기">
					<p class="dates">${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}</p>
					<a href="javascript:void(0)" title="${item.CRS_PLACE}" class="sbj" data-crs-sn="${item.CRS_SN}"
	          		data-linkurl="/educenter/trnng/view.do" id="focusLine${status.index}">${item.CRS_PLACE}</a>
					<div class="cont">
						${item.CRS_ADDR}
					</div>
				</div>
			</div>
		</li>		
	</c:forEach>
	<!--// 낚시터업자 -->
	</ul>	</li>
</ul>


<%--
<table class="table table-bordered">
	<caption>교육정보</caption>
	<thead>
		<tr>
			<th class="no_left text-center">구분</th>
			<th class="text-center">기수</th>
			<th class="text-center">교육일정</th>
			<th class="text-center">장소</th>
			<th class="text-center">인원</th>
		</tr>
	</thead>
	<tbody>
		<!-- // 종합교육 // -->
		<c:if test="${empty list_default}">
			<tr>
				<th class="text-center" >종합교육</th>
				<td class="text-center" colspan="4">
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
          		data-linkurl="/educenter/trnng/view.do">
				<c:if test="${status.first == true}">
					<th class="no_left text-center" ><a href="#;" class="clk_modal_eduinfo" data-crs-sn="${item.CRS_SN}" data-linkurl="/educenter/trnng/view.do" style="color:#444;">종합교육</a></th>
				</c:if>
				<td class="text-center">${item.CRS_NM}</td>
				<td class="text-center">${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}</td>
				<td class="text-center">${item.CRS_PLACE}</td>
				<td class="text-center">${item.MBR_MAX_CNT}명</td>
			</tr>
			
			
		</c:forEach>
		<!-- End // 종합교육 // -->
		<!-- // 주말교육 // -->
		<c:if test="${empty list_wknd}">
			<tr class="last_tr">
				<th class="no_left text-center">주말교육</th>
				<td class="text-center" colspan="4">
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
          		data-linkurl="/educenter/trnng/view.do">
				<th class="no_left text-center"><a href="#;" class="clk_modal_eduinfo" data-crs-sn="${item.CRS_SN}" data-linkurl="/educenter/trnng/view.do" style="color:#444;">주말교육</a></th>
				<td class="text-center">${item.CRS_NM}</td>
				<td class="text-center">${CRS_STR_DT}</td>
				<td class="text-center">${item.CRS_PLACE}</td>
				<td class="text-center">${item.MBR_MAX_CNT}명</td>
			</tr>
		</c:forEach>
		<!-- End // 주말교육 // -->
		<!-- 온라인교육 -->
			<tr class="clk_modal_eduinfo" 
          		data-crs-sn="CRS_1902132940182530"
          		data-linkurl="/educenter/trnng/view.do">
				<th class="no_left text-center"><a href="#;" class="clk_modal_eduinfo" data-crs-sn="${item.CRS_SN}" data-linkurl="/educenter/trnng/view.do" style="color:#444;">온라인교육</a></th>
				<td class="text-center">-</td>
				<td class="text-center">상시가능</td>
				<td class="text-center">-</td>
				<td class="text-center">무제한</td>
			</tr>
		<!-- End 온라인교육 -->
	</tbody>
</table>
--%>

<script>
var mainSlider1 = $('.grpedu_ul_1').bxSlider({
	auto: true,
	controls: false,
	pause: 3000,
	autoHover: true,
	stopAutoOnClick: true ,
	onSliderLoad: function(){

		$(".bx-clone").find("a").prop("tabIndex","-1");

	},
	onSlideAfter: function(){

		$(".grpedu_ul_1").children("li").each(function(){
			if($(this).attr("aria-hidden") == "false"){
				$(this).find("a").attr("tabIndex","0");
			}else{
				$(this).find("a").attr("tabIndex","-1");
			}
		});
	}
});

var mainSlider2 = $('.grpedu_ul_2').bxSlider({
	auto: true,
	controls: false,
	pause: 3000,
	autoHover: true,
	stopAutoOnClick: true ,
	onSliderLoad: function(){

		$(".bx-clone").find("a").prop("tabIndex","-1");

	},
	onSlideAfter: function(){

		$(".grpedu_ul_2").children("li").each(function(){
			if($(this).attr("aria-hidden") == "false"){
				$(this).find("a").attr("tabIndex","0");
			}else{
				$(this).find("a").attr("tabIndex","-1");
			}
		});
	}
});

$('.grpedu_ul_1 a').focusin(function () {
	//console.log('---스탑');
	mainSlider1.stopAuto();
});

$('.grpedu_ul_2 a').focusin(function () {
	//console.log('---스탑');
	mainSlider2.stopAuto();
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
			$("#focusLine").focus();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}); 
</script>
