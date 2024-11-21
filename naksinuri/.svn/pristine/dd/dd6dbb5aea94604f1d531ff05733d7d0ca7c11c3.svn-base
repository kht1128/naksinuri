<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../naksinuri_original/naksinuri/layout/head.jsp"%>
		
<div id="fishjobList" class="content respon2">

	<section id="writeForm" class="write_box">
		<h2>현재 <span class="font-weight-bold red-600">${fn:length(list)}</span> 건 이상의 회원정보가 발견되어 접속이 거부되었습니다.<br>고객센터(1833-7139)로 연락해주시기 바랍니다.</h2>
		<div class="agree_box">
			<p class="dottedbox text-center">&nbsp;</p>
		</div>
	</section>

	<section id="webzineList" class="list_box">
		<div class="board_list">
			<table class="list_tbl">
				<caption>조회리스트</caption>
				<colgroup>
					<%-- <col /> --%>
					<col />
					<col />
					<col />
					<col />
					<col />
				</colgroup>
				<thead>
					<tr>
						<!-- <th>일련번호</th> -->
						<th>이름<br>/&nbsp;닉네임(호칭)</th>
						<th>생년월일</th>
						<th>연락처</th>
						<th>주소</th>
						<th>최초가입일<br>/&nbsp;최종변경일</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty list}">
						<tr>
							<td class="no alignCenter" colspan="6">조회 된 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="status">
						<tr class="noti">
							
							<fmt:parseDate var="parse_mbr_reg_dt" value="${fn:replace(item.MBR_REG_DT, '.0', '')}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:parseDate var="parse_mbr_mod_dt" value="${fn:replace(item.MBR_REG_DT, '.0', '')}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate var="str_mbr_reg_dt" value="${parse_mbr_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate var="str_mbr_mod_dt" value="${parse_mbr_mod_dt}" pattern="yyyy-MM-dd HH:mm:ss" />
							
							<%-- <td class="date alignCenter"><c:out value="${item.MBR_SN}"/></td> --%>
							<td class="subject"><c:out value="${item.MBR_NM}"/><br><span style="color:#bbb;"><c:out value="${item.MBR_NCNM}"/></span></td>
							<td class="file alignCenter"><c:out value="${item.MBR_BIRTH}"/></td>
							<td class="file alignCenter"><c:out value="${item.MBR_HP}"/></td>
							<td class="writer alignCenter"><c:out value="${item.MBR_ADDR1} ${item.MBR_ADDR2}"/></td>
							<td class="writer alignCenter"><c:out value="${str_mbr_reg_dt}"/><br><c:out value="${str_mbr_mod_dt}"/></td>
							<td class="hit alignCenter">
								<c:choose>
									<c:when test="${item.MBR_ST eq 'Y'}">현재 사용중</c:when>
									<c:otherwise>현재 사용안함</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
</div>


<%@include file="../../naksinuri_original/naksinuri/layout/tail.jsp"%>

