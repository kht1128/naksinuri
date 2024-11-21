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
<style>
	.mgt50px{margin-top:50px}
	
	.write_box h2.championshopTit{height:auto;line-height:30px}
	.write_box h2.championshopTit span{display:block;font-size:16px;color:#999}
	.write_box h2.championshopTit span b{color:#666}
	
	.write_box dl dt{width:150px}
	.write_box dl dd{padding-top:20px;}
		
	.write_box .zipcodeBox{
		display:inline-block;height:40px;line-height:40px;padding:0 10px;margin:0 0 4px 5px;border:none;background-color:#626262;color:#fff;vertical-align:top;
		-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}
</style>
<div id="customerSound" class="content respon3">
		
		<section id="writeForm" class="write_box">
			<h2>참가자 정보</h2>
			<div class="client_box">
			<dl>
				<dt>대회정보</dt>
				<dd>
					<c:out value="${info.bo_subject}"/> (${info.bo_start_dt} ~ ${info.bo_end_dt})
				</dd>
			</dl>
			<dl>
				<dt>대회주최</dt>
				<dd>
					<c:out value="${info.host}"/>
				</dd>
			</dl>
			<dl>
				<dt>참가자명</dt>
				<dd>
					<c:out value="${info.mbr_name}"/> (<c:out value="${info.mbr_gender }"/>)
				</dd>
			</dl>
			<dl>
				<dt>전화번호</dt>
				<dd>
					<c:out value="${info.mbr_hp}"/>
				</dd>
			</dl>
			<dl>
				<dt>신청자와의 관계</dt> 
				<dd>	
					<c:out value="${info.mbr_relation}"/>
				</dd>                                                                                                                           
			</dl>
			<dl>
				<dt>소속</dt>
				<dd>
					<c:out value="${info.mbr_belongto}"/>
				</dd>
			</dl>
			<dl>
				<dt>생년월일</dt>
				<dd>
					<c:out value="${info.mbr_birth}"/>				
				</dd>
			</dl>
			<dl>
				<dt>주소</dt>
				<dd>
					<c:out value="${info.mbr_address}"/>			
				</dd>
			</dl>
			<dl>
				<dt>구명조끼 지참여부</dt>
				<dd>
					<c:out value="${info.jacket_offer}"/>				
				</dd>
			</dl>
			<!-- 
			<dl>
				<dt>유입경로</dt>
				<dd>
					${info.inflow_path}				
				</dd>
			</dl>
			<dl>
				<dt>참가이유</dt>
				<dd>
					${info.attend_cause}				
				</dd>
			</dl>
			-->
			<dl>
				<dt>낚시경력</dt>
				<dd>
					<c:out value="${info.naksi_career}"/> 년				
				</dd>
			</dl>
			<dl>
				<dt>참가비</dt>
				<dd>
					<fmt:formatNumber value="${info.deposit_amount}" groupingUsed="true"></fmt:formatNumber>원
				</dd>
			</dl>
			<dl>
				<dt>요청사항 및 의견</dt> 
				<dd>
					<c:out value="${info.etc}"/>
				</dd>                                                                                                                           
			</dl>	
			</div>		
			<h2 style="margin-top:30px;">신청자 정보</h2>
			<div class="owner_box">
				<dl>
					<dt>신청자(보호자)</dt>
					<dd>
						<c:out value="${info_own.own_name}"/>
					</dd>
				</dl>
				<dl>
					<dt>성별</dt>
					<dd>
						<c:out value="${info_own.own_gender}"/>
					</dd>
				</dl>
				<dl>
					<dt>연락처</dt>
					<dd>
						<c:out value="${info_own.own_hp}"/>
					</dd>
				</dl>
				<dl>
					<dt>이메일</dt>
					<dd>
						<c:out value="${info_own.own_email}"/>
					</dd>
				</dl>
				
				<c:if test="${detail eq '1'}">
				<dl>
					<dt>생년월일</dt>
					<dd>
						<c:out value="${info_own.own_birth}"/>
					</dd>
				</dl>
				<dl>
					<dt>주소</dt>
					<dd>
						<c:out value="${info_own.own_address}"/>
					</dd>
				</dl>
				<dl>
					<dt>낚시경력</dt>
					<dd>
						<c:out value="${info_own.own_naksi_career}"/> 년
					</dd>
				</dl>
				<dl>
					<dt>유입경로</dt>
					<dd>
						<c:out value="${info_own.own_inflow_path}"/>				
					</dd>
				</dl>
				<dl>
					<dt>참가이유</dt>
					<dd>
						<c:out value="${info_own.own_attend_cause}"/>				
					</dd>
				</dl>
				<dl>
					<dt>요청사항 및 의견</dt>
					<dd>
						<c:out value="${info_own.own_etc}"/>				
					</dd>
				</dl>
				</c:if>
				
				<dl>
					<dt>입금자명</dt>
					<dd>
						<c:out value="${info_own.own_repre_name}"/>
					</dd>
				</dl>
				<dl>
					<dt>입금예정일자</dt>
					<dd>
						${info_own.own_depo_date}
						<%--${fn:substring(info_own.own_depo_date,0,10)} --%>	
					</dd>
				</dl>
				<dl>
					<dt>총 입금액</dt>
					<dd>
						<fmt:formatNumber value="${info_own.own_deposit_amount}" groupingUsed="true"></fmt:formatNumber> 원
					</dd>
				</dl>
			</div>
		<div id="btnArea">
				<ul class="floats">
					<a href="#;" onclick="close_form()"  class="btn_prev btn_white">닫기</a></li>
				</ul>
			</div>
		</section>
	</div>
	
<script>
	function close_form(){
		window.close();
	}
</script>