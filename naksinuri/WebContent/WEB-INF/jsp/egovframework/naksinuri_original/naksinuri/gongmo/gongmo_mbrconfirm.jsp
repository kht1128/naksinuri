<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info" />
<c:set var="depthName" value="gongmo" />
<c:set var="pageName" value="mbrconfirm" />

<%@include file="../layout/head.jsp"%>

<style>
.write_box h2 {
display:inline-block;
width:100%;
line-height:36px;
font-size:22px;
}
.write_box dl dt {
padding: 0px 0;
}
.write_box dl dd {
padding: 10px 0;
}
</style>


<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="bo_sn" id="bo_sn" />
<input type="hidden" name="mbr_group" id="mbr_group" />
<input type="hidden" name="idx" id="idx" />
</form:form>	

<div id="customerSound" class="content respon3">
		<c:if test="${info ne null}">
		<section id="writeForm" class="write_box">
			<h2>공모전 정보</h2>
			<div class="client_box">
				<dl>
					<dt>명칭</dt>
					<dd>
						${info.bo_subject}
					</dd>
				</dl>				
				<dl>
					<dt>공모기간</dt>
					<dd>
						${info.bo_start_dt} ~ ${info.bo_end_dt}
					</dd>
				</dl>
				<dl>
					<dt>모집기간</dt>
					<dd>
						${info.entry_start_dt} ~ ${info.entry_end_dt}
					</dd>
				</dl>
			</div>
			
			<h2 style="margin-top:30px;">접수내역</h2>
			<c:if test="${list eq null or fn:length(list) < 1 }">
			<section id="writeForm" class="write_box">
				<h2>
					<span class="txt_red">
					접수내역이 없습니다.<br>입력한 정보가 올바른지 다시 한번 확인해보시기 바랍니다.<br><br>올바른 정보 임에도 조회가 안되는 경우에는 운영사무국으로 문의해주십시오.<br>( 010-7578-2297 )
					</span>
				</h2>
			</section>
			</c:if>	
				
			<c:if test="${list ne null}">
				<c:forEach var="item" items="${list}">
					<div class="client_box">
					<!-- //본섭에서 날짜 포맷이 맞지 않아 오류 발생, 개선방안을 찾자// -->
						<%--
						<fmt:parseDate value="${item.regdate}" var="parse_regdate" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate var="str_regdate" value="${parse_regdate}" pattern="yyyy년 MM월 dd일 HH시 mm분"/>
						 --%>
						<dl>
							<dt>작품명</dt>
							<dd>
								<c:out value="${item.work_subject}"/>
							</dd>
						</dl>
						<dl>
							<dt>공모부문</dt>
							<dd>
								<c:out value="${item.mbr_cate}"/>
							</dd>
						</dl>
						<dl>
							<dt>접수일자</dt>
							<dd>
								<c:out value="${item.regdate}"/>
							</dd>
						</dl>
						<dl>
							<dt>등록파일</dt>
							<dd>
								<c:if test="${not empty item.bo_atch_file}">				
									<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${item.bo_atch_file}" />
							        </c:import>
			               		</c:if>
							</dd>
						</dl>
						<dl>
							<dt>접수상태</dt>
							<dd>
								<span class="txt_red"><b><c:out value="${item.mbr_status}"/></b></span>
							</dd>
						</dl>
						<dl>
							<dt>비고</dt>
							<dd>
								<input type="button" class="btn lgrey" onclick="click_modify('${item.bo_sn}','${item.mbr_group}','${item.idx}')" value="수정하기" />
							</dd>
						</dl>					
					</div>
					<br><br><br>	
				</c:forEach>
			</c:if>
			
			<div id="btnArea" class="noupline" >
				<a href="#;" class="btn_blue" onclick="back()">확인</a>
			</div>
			
		</section>
		</c:if>

</div>	

				
<script>
function back(){
	$("#listform").attr("action", "/gongmo/gongmo/search.do");
	$("#bo_sn").val("");
	$("#mbr_group").val("");
	$("#idx").val("");
	document.getElementById("listform").submit();
}
function click_modify(bo_sn,mbr_group,idx){
	$("#listform").attr("action", "/gongmo/gongmo/mbrmodify.do");
	$("#bo_sn").val(bo_sn);
	$("#mbr_group").val(mbr_group);
	$("#idx").val(idx);
	document.getElementById("listform").submit();
}
</script>
<%@include file="../layout/tail.jsp"%>