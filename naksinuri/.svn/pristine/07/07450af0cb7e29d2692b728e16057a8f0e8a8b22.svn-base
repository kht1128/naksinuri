<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form:form commandName="logMemberModifyVO" id="ajaxLogMbrModViewForm" name="ajaxLogMbrModViewForm" method="post">
<input type="hidden" name="LOG_SN" value=""/>
</form:form>
 

	<label class="col-md-3 form-control-label text-left ml--15">회원정보수정 로그 기록</label>
	<span class="badge badge-round badge-success float-right mt-10">전체 ${paginationInfo.totalRecordCount} 건</span>
	
	<form:form commandName="logMemberModifyVO" id="ajaxLogMbrListForm" name="ajaxLogMbrListForm" method="post">
		<input type="hidden" name="MBR_ID" value="${MBR_ID}"/>
		<input type="hidden" name="pageUnit" value="${pageUnit}"/>	
	
		<!-- table:start -->
		<table id="datalist" class="table table-hover footable footable-paging footable-paging-center ">
			<colgroup>
				<%-- <col /> --%>
				<col width="130px"/>
				<col width="130px"/>
				<col width="130px"/>
				<col />
				<col />
				<col width="130px"/>
				<col />
				<col />
			</colgroup>
			<thead>
				<tr>
			    	<!-- <th class="text-middle text-center ">No</th> -->
					<th class="text-middle text-center ">로그일자</th>
					<th class="text-middle text-center ">처리담당자<br>/접속IP</th>
					<th class="text-middle text-center ">로그구분</th>
					<th class="text-middle text-center ">로그이력</th>
					<th class="text-middle text-center ">사용자변경이력</th>
					<th class="text-middle text-center ">변경데이터 확인</th>
		        </tr>       				
			</thead>
			<tbody>
				<c:set var="table_cell_cnt" value="8"/>
				<c:if test="${empty list}">
					<tr><td colspan="${table_cell_cnt}" class="text-center table-active">조회 된 이력이 없습니다.</td></tr>	  
		         </c:if>
		         <c:forEach var="item" varStatus="status" items="${list}">	         				            	
	         		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
	         			<%-- <td class="text-middle text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td> --%>
	         			<td class="text-middle text-center">${item.REG_DT }</td>
	         			<td class="text-middle text-center">${item.MASTER_MBR_NCNM }<br>(${item.MASTER_MBR_NM })<br><span class="grey-400">${item.LOG_INFO_IP }</span></td>
	         			<td class="text-middle text-center">
	         				<c:choose>
	         					<c:when test="${item.LOG_TYPE eq 'EDU_MBR_HSTRY_TB'}">교육수강생</c:when>
	         					<c:when test="${item.LOG_TYPE eq 'MBR_DTL_TB'}">회원추가상세정보</c:when>
	         					<c:otherwise>회원기본정보</c:otherwise>
	         				</c:choose>
	         			</td>
	         			<td class="text-middle text-center">${item.LOG_DSCRP }</td>
	         			<td class="text-middle text-center">${item.LOG_UPD_MSG }</td>
	         			<td class="text-middle text-center">		            	
	         				<a href="#;" class="btn btn-default btn-sm clk_data_view clk_data_view2" data-log-sn2="${item.LOG_SN}" data-log-sn="LOG_SN_${item.LOG_SN}" data-log-type="${item.LOG_TYPE}">조회</a>
	         			</td>
	           		</tr> 
		         </c:forEach>
			</tbody>
			<tfoot>
				<tr class="footable-paging hide-cell-exceldown">
					<td colspan="${table_cell_cnt}">
						<div class="footable-pagination-wrapper">
							<ul class="pagination">
								<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page_ajax" />
		        			<form:hidden path="pageIndex" />
							</ul>	 								
						</div>
					</td>
				</tr>
			</tfoot>				  
		</table>
		<!-- table:end -->
	</form:form>
	
<style>
.alertify .alert>*, .alertify .dialog>* {width:800px!important;}
</style>
<script>
function fn_egov_link_page_ajax(pageNo){
	document.ajaxLogMbrListForm.pageIndex.value = pageNo;
	document.ajaxLogMbrListForm.action = "";
   	//document.ajaxLogMbrListForm.submit();
   	trgtListLogMbrModData('ajaxLogMbrListForm');
}
$(".clk_data_view").click(function() {
	var log_sn2 = $(this).attr('data-log-sn2');
	var form = document.getElementById('ajaxLogMbrModViewForm');
	form.LOG_SN.value = log_sn2;
	$.ajax({
		type:"POST",
		url :'/adm/log/viewMbrMod.do',
		data:$('#ajaxLogMbrModViewForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			alertify.alert(data); 
			var h = 0;
			if(document.body.clientHeight == 0) {
				h = window.innerHeight-158;
			} else {
				h = document.body.clientHeight-158;
			}
			$('.alertify-ajax-scroll-body').css('height',h);
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
