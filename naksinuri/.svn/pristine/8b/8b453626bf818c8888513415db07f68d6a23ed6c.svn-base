<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>
<html lang="ko">
<c:set var="depthName" value="info" />
<c:set var="pageName" value="report" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>

<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="report_sn" id="report_sn" value="${info.report_sn}" />
<input type="hidden" name="fileSn">
<input type="hidden" name="atchFileId">
<input type="hidden" name="fileListCnt">
	<div id="container">
		<div id="content">
			<section id="table-write" class="table-box">
				<h3>낚시터 잘못된 정보 신고</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>		
							<tr>
								<th>낚시터명</th>
								<td><input type="text" readonly name="co_nm" id="co_nm" class="frm_input" value="${info.co_nm}"/></td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input type="text" readonly name="report_subject" id="report_subject" class="frm_input" value="${info.report_subject}"  /></td>
							</tr>					
							<tr>
								<th>작성자</th>
								<td><input type="text" readonly name="report_writer" id="report_writer" class="frm_input" value="${info.report_writer}"  /></td>
							</tr>
							<%-- <tr>
								<th>이메일</th>
								<td><input type="text" readonly name="report_email" id="report_email" class="frm_input" value="${info.report_email}"  /></td>
							</tr>
							<tr>
								<th>연락처</th>
								<td><input type="text" readonly name="report_phone" id="report_phone" class="frm_input" value="${info.report_phone}"  /></td>
							</tr> --%>
							<tr>
								<th>내용</th>
								<td><textarea readonly name="report_cont" id="report_cont"  class="frm_input"  style="WIDTH: 100%">${info.report_cont}</textarea></td>
							</tr>	
							<tr>
								<th>첨부파일 목록</th>
								<td>
									<c:if test="${not empty info.report_atch_file}">	
										<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
			                 		 	  <c:param name="param_atchFileId" value="${info.report_atch_file}" />
			                			</c:import>
	               					</c:if>
								</td>
							</tr>					
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
				<c:if test="${info.report_trash eq '0' }">
					<a href="/admin/info/report/list.do" class="btn_size2 btn_bordergray">목록</a>
				</c:if>
				<c:if test="${info.report_trash eq '1' }">
					<a href="/admin/info/report/trash.do" class="btn_size2 btn_bordergray">목록</a>
				</c:if>
			</div>
		</div>
	</div>
</form:form>
	<!-- 하단 푸터 { -->
	<footer id="footer" class="floats">
		<div class="l_version">
			No Background Tasks <em>Version 4.4.0.5</em>
		</div>
		<div class="r_copyright">
			<b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.
		</div>
	</footer>
	<!-- } 하단 푸터 -->
</div>
</body>
</html>
<script type="text/javaScript">



</script>
