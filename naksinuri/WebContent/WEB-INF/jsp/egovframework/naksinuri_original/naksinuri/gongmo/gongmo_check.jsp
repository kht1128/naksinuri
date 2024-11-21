<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="gongmo"/>
<c:set var="pageName" value="check" />
<%@include file="../layout/head.jsp"%>

<style>
	.mgt50px{margin-top:50px}
	
	.write_box h2.championshopTit{height:auto;line-height:30px}
	.write_box h2.championshopTit span{display:block;font-size:16px;color:#999;margin-top:5px;}
	.write_box h2.championshopTit span b{color:#666}
	
	.write_box dl dt{width:200px}
	.write_box dl dd{}
		
	.write_box .zipcodeBox{
		display:inline-block;height:40px;line-height:40px;padding:0 10px;margin:0 0 4px 5px;border:none;background-color:#626262;color:#fff;vertical-align:top;
		-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}
	.board_list .list_tbl tr th, .board_list .list_tbl tr td {
		padding:10px;
	}		
		
</style>
	
	<div id="customerSound" class="content respon3">
	
		<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
				<li class="on"><a href="/gongmo/gongmo/check.do">공모전 접수/신청</a></li>
				<li><a href="/gongmo/gongmo/search.do" >접수 확인</a></li>
			</ul>
		</div>
			
		<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >		
		<section id="writeForm" class="write_box">				
			<h2>공모전 선택</h2>
			<div class="owner_box">
				<div id="owner_box">
				<dl>
					<dd>
						<select class="write_input wp100" onchange="change_gongmo(this);" name="bo_sn">		
							<option value="">참가하고자 하는 공모전을 선택해주세요.</option>
							<c:forEach var="item2" items="${list}">
								<option value="${item2.bo_sn}" <c:if test="${info ne null and item2.bo_sn eq info.bo_sn}">selected</c:if> >${item2.bo_subject}</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				</div>				
			</div>				
			<!-- // 공모전 선택시 : start // -->			
			<c:if test="${info ne null}">
				<section id="viewContent">
					<jsp:useBean id="now" class="java.util.Date" />	 
					<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss" var="nowDate1" /> 
					<c:set var = "bostart" value = "${fn:replace(info.bo_start_dt, '-', '')}000000" />
					<c:set var = "boend" value = "${fn:replace(info.bo_end_dt, '-', '')}235959" />				 
					<c:choose>
						<c:when test="${info.entry_start_dt ne null }">
							<c:set var = "entrystart" value = "${fn:replace(info.entry_start_dt, '-', '')}000000" />
							<c:set var = "entryend" value = "${fn:replace(info.entry_end_dt, '-', '')}235959" />
						</c:when>
						<c:otherwise>
							<c:set var = "entrystart" value = "20180101000000" />
							<c:set var = "entryend" value = "20180101235959" />
						</c:otherwise>
					</c:choose>
					<fmt:parseDate value="${nowDate1}" var="nowDate" pattern="yyyyMMddHHmmss"/>
					<fmt:parseDate value="${bostart}" var="bo_start" pattern="yyyyMMddHHmmss"/>
					<fmt:parseDate value="${boend}" var="bo_end" pattern="yyyyMMddHHmmss"/>
					<fmt:parseDate value="${entrystart}" var="entry_start" pattern="yyyyMMddHHmmss"/>
					<fmt:parseDate value="${entryend}" var="entry_end" pattern="yyyyMMddHHmmss"/>
					<fmt:parseNumber value="${bo_start.time / (1000*60*60*24)}" integerOnly="true" var="calcStrDate"></fmt:parseNumber>
					<fmt:parseNumber value="${bo_end.time / (1000*60*60*24)}" integerOnly="true" var="calcEndDate"></fmt:parseNumber>
					<fmt:parseNumber value="${entry_start.time / (1000*60*60*24)}" integerOnly="true" var="calcEntStrDate"></fmt:parseNumber>
					<fmt:parseNumber value="${entry_end.time / (1000*60*60*24)}" integerOnly="true" var="calcEntEndDate"></fmt:parseNumber>
				   <div id="printthis">		
					<div class="left_contents" style="top: 0px; bottom: auto; left: 0px; right: auto;">
						<div class="header_coninfo">
							<div class="con_box">
								<div class="poster">
									<c:choose>
									<c:when test="${(info.bo_main_img ne null) and not empty fn:trim(info.bo_main_img) }">
										<div class="thumbs crop imgLiquid_bgSize imgLiquid_ready" style="background-image: url('<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>'); background-size: cover; background-position: center center; background-repeat: no-repeat;">
										<img src="<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>" alt="" style="display: none;">
										<%-- 
										<button type="button" onclick="open_porster('<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>')" title="크게보기" class="openpop focustop"><span class="sp_btn">크게보기</span></button>
										--%>
										<button type="button" onclick="open_porster('<c:url value='/show/image.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>')" title="크게보기" class="openpop focustop"><span class="sp_btn">크게보기</span></button>
									</c:when>
									<c:otherwise>
										<div class="thumbs crop imgLiquid_bgSize imgLiquid_ready" style="background-image: url('/naksinuri_original/common_main/img/noImage_big.png'); background-size: cover; background-position: center center; background-repeat: no-repeat;">
										<img src="/naksinuri_original/common_main/img/noImage_big.png" alt="" style="display: none;">
									</c:otherwise>
									</c:choose>
								</div>
							</div>					
								<div class="info">								
									<div class="wrp_lst">
										<table class="lst_board type8">
											<colgroup>
												<col style="width:100px">
												<col>
											</colgroup>
											<tbody>
												<tr>
													<th scope="col">명칭</th>
													<td>${info.bo_subject}</td>
												</tr>
												<tr>
													<th scope="col">주최</th>
													<td>${info.organizer}</td>
												</tr>
												<tr>
													<th scope="col">주관</th>
													<td>${info.host}</td>
												</tr>
												<tr>
													<th scope="col">담당자</th>
													<td>${info.bo_name}<%--(${info.bo_phone}) --%></td>
												</tr>
												<tr>
													<th scope="col">공모기간</th>
													<td><fmt:formatDate value="${bo_start}" pattern="yyyy-MM-dd (E)"/> ~ <fmt:formatDate value="${bo_end}" pattern="yyyy-MM-dd (E)"/> (총 ${calcEndDate-calcStrDate}일)</td>
												</tr>
												<c:if test="${info.is_entry_y == '1'}">
												<tr>
													<th scope="col">접수기간</th>
													<td><fmt:formatDate value="${entry_start}" pattern="yyyy-MM-dd (E)"/> ~ <fmt:formatDate value="${entry_end}" pattern="yyyy-MM-dd (E)"/> (총 ${calcEntEndDate-calcEntStrDate}일 )</td>
												</tr>
												<tr>
													<th scope="col">유의사항</th>
													<td>${info.entry_notice}</td>
												</tr>
												</c:if>
											</tbody>
										</table>
									</div>
									
								</div>
							</div>
						</div>
					</div>
					<div class="agree_box" style="line-height:22px;margin-top:30px;">
						<h3>개인정보 수집 및 이용에 대한 고지사항</h3>
						<div class="agree_text" >
							가. 개인정보 수집•이용 목적<br>
							&nbsp;&nbsp;&nbsp;낚시안전 콘텐츠 공모전에서 수집되는 개인정보는 정보주체의 동의를 얻어, 공모전의 참여 및 시상 및 낚시안전 캠페인 사업의 결과보고 및 설문조사 결과보고의 운영을 목적으로 합니다.<br>
							나. 개인정보 수집 항목<br>
							&nbsp;&nbsp;&nbsp;성명, 생년월일, 연락처, 이메일, 주소, 개별설문조사, 개인식별 및 공모 접수 확인 및 시상을 위한 공지사항의 전달, 사업운영결과 보고 등<br>
							다. 개인정보의 보유 및 이용기간 <br>
							&nbsp;&nbsp;&nbsp;개인정보를 제공한 날로부터 24개월 후 삭제(별도 요청시 시상식 이후 2개월 후 삭제)<br>
							라. 동의 거부 권리안내<br>
							&nbsp;&nbsp;&nbsp;본 개인정보 수집에 대한 동의를 거부하실 수 있으며, 이 경우 참가신청이 일부 제한 될 수 있습니다.
						</div><br/>
						<div class="agree_yesorno">
							개인정보 수집 및 활용에 대하여 
							<input type="radio" id="agreeing1y" name="agreeing1" value="동의"/><label for="agreeing1y"><span></span>동의합니다.</label>
							<input type="radio" id="agreeing1n" name="agreeing1" value="비동의"/><label for="agreeing1n"><span></span>동의하지 않습니다.</label>                                                                                                                  
						</div>
					</div>
					<div class="agree_box" style="line-height:22px;">
						<h3>저작권 관련사항 안내</h3>
						<div class="agree_text" >
							1. 본인이 제출한 작품들은 국내외에서 미발표한 작품이며, 수상한 사실이 없음을 확인합니다.<br>
							2. 접수내용(포트폴리오, 초상권, 저작권, 명혜훼손, 제작과 포함)이 타인/타사의 저작권 침해 및 허위로 판명될 경우 이로 인해 발생할 수 있는 일체의 책임은 본인에게 있음을 확인합니다.<br>
							3. 본인은 공모요강과 참가신청서의 모든 내용을 읽고 이해하였으며 이에 동의합니다.<br>
							4. 참가자의 귀책사유로 발생하는 모든 문제에 대해서는 참가자에게 책임이 있습니다.<br>
							5. 응모작이 심사기준에 부합하지 않을 경우에는 누락될 수 있습니다.<br>
							6. 수상작품의 저작권은 참가자에게 있으며, 다만 수상작에 한하여, 수상작을 활용하여, 낚시안전 캠페인의 홍보를 위해 사용될 수 있다.<br>
							7. 공모전에 참가하고 시상된 수상작에 한하여, 수상작의 활용에 있어 필요한 범위(2차 수정)안에서 작품의 전체나 일부를 변경하여 이용하는 것을 동의하는 것으로 간주합니다.<br> 
							8. 응모자의 실수로 인한 재생오류와 파손 등으로 발생되는 모든 책임은 응모자에게 있습니다.

						</div><br/>
						<div class="agree_yesorno">
							저작권 관련사항에 대하여 
							<input type="radio" id="agreeing2y" name="agreeing2" value="동의"/><label for="agreeing2y"><span></span>동의합니다.</label>
							<input type="radio" id="agreeing2n" name="agreeing2" value="비동의"/><label for="agreeing2n"><span></span>동의하지 않습니다.</label>                                                                                                                  
						</div>
					</div>
					<c:if test="${entry_start < nowDate && entry_end > nowDate}">
				 		<div id="btnArea" class="noupline">
							<a href="#;" class="btn_request btn_blue" onclick="submitContents()">접수하기</a>
						</div>
					</c:if>
				</section>
			</c:if>
			<!-- // 공모전 선택시 : end // -->
		</section>
		</form:form>
	</div>

<%@include file="../layout/tail.jsp"%>


<script type="text/javascript">

//공모전 선택
function change_gongmo(obj) {
	$("#imform").attr("action", "/gongmo/gongmo/check.do");
	$("#imform").submit();
}


//전송버튼 클릭이벤트
function submitContents() {

	var ischk1 = false;
	var ischk2 = false;
	var chk1 = document.getElementsByName("agreeing1");
	var chk2 = document.getElementsByName("agreeing2");
	for(var i=0;i<chk1.length;i++){
		if(chk1[i].checked == true){
			ischk1 = true;
			break;	
		}
	}
	for(var i=0;i<chk2.length;i++){
		if(chk2[i].checked == true){
			ischk2 = true;
			break;	
		}
	}		
	if(!ischk1){
		alert("개인정보 수집 및 활동 동의가 필요합니다.")
		return false;
	}
	if(!ischk2){
		alert("저작권 관련사항에 대한 동의가 필요합니다.")
		return false;
	}
	
	if($("input[type=radio][name=agreeing1]:checked").val() == "비동의"){
		alert("개인정보 수집 및 활동 동의가 필요합니다.");
		return false;
	}	
	if($("input[type=radio][name=agreeing2]:checked").val() == "비동의"){
		alert("저작권 관련사항에 대한 동의가 필요합니다.");
		return false;
	}
	
	
	$("#imform").attr("action", "/gongmo/gongmo/mbrwrite.do");
	$("#imform").submit();

}
	
function open_porster(url) {
	window.open(url);
}

</script>