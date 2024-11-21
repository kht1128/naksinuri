<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>
<style>
.basic_tbl th, .basic_tbl td{border:0px;}
.basic_tbl tbody{border:1px;}
</style>
<form:form commandName="eduCenterMainVO" id="modalForm" name="modalForm" method="post">
	<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info2.CRS_SN}"/>
</form:form>
<div class="modal-dialog" role="document">
	<div class="modal-content form-horizontal">
		<div class="modal-header">
			<h4 class="modal-title" tabindex="0" style="display: inline;">교육신청 알림</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   				<span aria-hidden="true">×</span>
 			</button>
		</div>
		<div class="modal-body pt-0 pl-30 pr-30">
			<div class="form-group" >
				<div class="modal-body pt-30 pl-30 pr-30">
					<div class="form-group row modal-view-table">
					<table class="basic_tbl">
						<caption>교육정보</caption>
						<colgroup>
							<col width="120">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th colspan="2">${msg}</th>
							</tr>
						</thead>
						<tbody class="scroll-y">
							<c:if test="${alertType ne 'application'}">
								<c:forEach var="item" items="${info}">
									<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
									<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
									<fmt:parseDate value="${fn:replace(item.REG_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
									<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) HH시 mm분 부터" var="CRS_STR_DT"/>
									<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH시 mm분 까지" var="CRS_END_DT"/>
									<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy-MM-dd (E) HH:mm" var="REG_DT"/>
										<tr>
											<th>교육명</th>
											<td>${item.CRS_NM }</td>
										</tr>
										<tr>
											<th>이전 신청일</th>
											<td>${REG_DT}</td>
										</tr>
										<tr>
											<th>교육일시</th>
											<td>${CRS_STR_DT} ${CRS_END_DT}</td>
										</tr>
										<tr>
											<th class="pt-0 pb-1" style="background-color:#000; height:1px;" colspan="2"></th>
										</tr>
								</c:forEach>
							</c:if>	
							<c:if test="${alertType eq 'application'}">
								<fmt:parseDate value="${fn:replace(info2.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
								<fmt:parseDate value="${fn:replace(info2.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
								<fmt:parseDate value="${fn:replace(info2.REG_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
								<fmt:formatDate value="${parse_crs_str_dt}" pattern="[yyyy-MM-dd (E) HH시 mm분] 부터" var="CRS_STR_DT"/>
								<fmt:formatDate value="${parse_crs_end_dt}" pattern="[yyyy-MM-dd (E) HH시 mm분] 까지" var="CRS_END_DT"/>
								<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy-MM-dd (E) HH시 mm분" var="REG_DT"/>
								<tr>
									<th>교육명</th>
									<td>${info2.CRS_NM }</td>
								</tr>
								<tr>
									<th>교육 대상</th>
									<%-- <td>${info2.CRS_MBR_CD}</td> --%>
									<c:forEach var="item" items="${list_mbr_cd}">
					       				<c:if test="${info2.CRS_MBR_CD eq item.CD_ID}">
					       					<td>${item.CD_NM}</td>
					       				</c:if>
									</c:forEach>
								</tr>
								<tr>
									<th>교육일시</th>
									<td>${CRS_STR_DT} ${CRS_END_DT}</td>
								</tr>
								<tr>
									<th class="pt-0 pb-1" style="background-color:#000; height:1px;" colspan="2"></th>
								</tr>
							</c:if>
						</tbody>
					</table>
					</div>
			  	</div>
			</div>
			<div class="text-right">
				<c:if test="${alertType ne 'alert'}">
					<c:if test="${alertType eq 'confirm2' }">						
						<button type="button" class="btn btn-primary btn-outline clk_btn_act2">확인</button>
					</c:if>
					<c:if test="${alertType eq 'confirm' or alertType eq 'application'}">
						<button type="button" class="btn btn-primary btn-outline clk_btn_act">확인</button>
					</c:if>
				</c:if>
	            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
	        </div>
		</div>
	</div>
	
	<script>
	$(function(){
		var checkLogin = '${error}';
		if(checkLogin == '99'){
			setTimeout(function() {
				location.href = '/educenter/index.do';
			}, 5000);
		}
	});
	$(".clk_btn_act").click(function() {
		var form = document.getElementById('modalForm');
		form.action = '/educenter/m/trnng/agree.do';
		form.submit();
	});
	$(".clk_btn_act2").click(function() {
		var crsSn = '${info2.CRS_SN}';
		deleteAct(crsSn);
	});
	
	</script>
</div>

