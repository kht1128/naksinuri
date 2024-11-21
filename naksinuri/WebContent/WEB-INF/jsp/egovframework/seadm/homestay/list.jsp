<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../head.jsp" %> 
<%@ include file="../left_menu.jsp" %>


<form id="viewForm" name="viewForm" action="" method="post">
<input name="selectedId" type="hidden" />
</form>

<form:form commandName="homestayVO" name="listForm" action="/seadm/homestay/list.do" method="post">
<input name="selectedId" type="hidden" />
<input name="checkedIdForDel" type="hidden" />

    <!-- Page -->
    <div class="page">
      <div class="page-header">
        <c:choose>
			<c:when test="${not empty subpageNum}">
				<h1 class="page-title">${subpageName}</h1>
		        <ol class="breadcrumb">
		        	<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item">
		        		<a href="${pageUrl}">${pageName}</a>
		        	</li>
		        	<li class="breadcrumb-item active">${subpageName}</li>
		      	</ol>
			</c:when>
			<c:otherwise>
				<h1 class="page-title">${pageName}</h1>
		        <ol class="breadcrumb">
		       		<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item active">${pageName}</li>
		      	</ol>
			</c:otherwise>											
		</c:choose>
        <div class="page-header-actions">
        	<a class="append-rows btn btn-primary btn-outline" href="/seadm/homestay/write.do" >
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">홈스테이 신규등록하기</span>
        	</a> 
        </div>
      </div>

      <div class="page-content container-fluid">

      	<div class="row">
			<div class="col-xl-12">
	      		<div class="panel mb-20">	      			
		  			<div class="panel-body">		  			
		    			<div class="input-group col-lg-12 float-right">
		    				<div class="mr-10">
								<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="hs_zone_sel" id="hs_zone_sel">
									<option value="0" <c:if test="${empty s_1 or s_1 eq '0' }"> selected </c:if> >지역</option>
									<option value="1" <c:if test="${s_1 eq '1' }"> selected </c:if> >부산</option>
									<option value="2" <c:if test="${s_1 eq '2' }"> selected </c:if> >인천</option>
									<option value="3" <c:if test="${s_1 eq '3' }"> selected </c:if> >울산</option>
									<option value="4" <c:if test="${s_1 eq '4' }"> selected </c:if> >경기</option>
									<option value="5" <c:if test="${s_1 eq '5' }"> selected </c:if> >강원</option>
									<option value="6" <c:if test="${s_1 eq '6' }"> selected </c:if> >충남</option>
									<option value="7" <c:if test="${s_1 eq '7' }"> selected </c:if> >전북</option>
									<option value="8" <c:if test="${s_1 eq '8' }"> selected </c:if> >전남</option>
									<option value="9" <c:if test="${s_1 eq '9' }"> selected </c:if> >경북</option>
									<option value="10" <c:if test="${s_1 eq '10' }"> selected </c:if> >경남</option>
									<option value="11" <c:if test="${s_1 eq '11' }"> selected </c:if> >제주</option>
								</select>
							</div>
							<div class="mr-10">
								<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="hs_item_sel" id="hs_item_sel">
									<option value="0" <c:if test="${s_2 eq '0' }"> selected </c:if> >업종</option>
									<option value="1" <c:if test="${s_2 eq '1' }"> selected </c:if> >양식</option>
									<option value="2" <c:if test="${s_2 eq '2' }"> selected </c:if> >어선어업</option>
									<option value="3" <c:if test="${s_2 eq '3' }"> selected </c:if> >가공공장</option>
									<option value="4" <c:if test="${s_2 eq '4' }"> selected </c:if> >염전</option>
									<option value="5" <c:if test="${s_2 eq '5' }"> selected </c:if> >어촌관광</option>
									<option value="6" <c:if test="${s_2 eq '6' }"> selected </c:if> >해양수산레저</option>
									<option value="7" <c:if test="${s_2 eq '7' }"> selected </c:if> >기타</option>
								</select>
							</div>
							<div class="mr-10">
								<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="hs_carrer_sel" id="hs_carrer_sel">
									<option value="0" <c:if test="${s_3 eq '0' }"> selected </c:if> >어업경력</option>
									<option value="1" <c:if test="${s_3 eq '1' }"> selected </c:if> >3년 미만</option>
									<option value="2" <c:if test="${s_3 eq '2' }"> selected </c:if> >3~5년</option>
									<option value="3" <c:if test="${s_3 eq '3' }"> selected </c:if> >5~7년</option>
									<option value="4" <c:if test="${s_3 eq '4' }"> selected </c:if> >7~10년</option>
									<option value="5" <c:if test="${s_3 eq '5' }"> selected </c:if> >10년 이상</option>
								</select>
							</div>
							<div class="mr-10">
								<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="hs_home_sel" id="hs_home_sel">
									<option value="0"  <c:if test="${s_4 eq '0' }"> selected </c:if> >주거형태</option>
									<c:forEach var="item_home_type_list" varStatus="status" items="${home_type_list}">
									<option value="${item_home_type_list.cdid}"  <c:if test="${s_3 eq item_home_type_list.cdid }"> selected </c:if> >${item_home_type_list.cdnm}</option>
									</c:forEach>				
								</select>
							</div>
							<input name="skeyw" id="skeyw" class="form-control" type="text" placeholder="검색어를 입력해주세요." value="<c:out value="${skeyw}"/>" autocomplete="off">
							<span class="input-group-append">
								<button class="btn btn-primary" type="submit" onclick="fnSearch(); return false;"><i class="icon wb-search" aria-hidden="true"></i></button>
							</span>
						</div>						
		  			</div>
				</div> 		          
	            <!-- Panel Editing Rows -->
	            <div class="panel">
	              	<div class="panel-body">
	              		<div class="example-tooltip">
							<div class="tooltip bs-tooltip-top tooltip-info" role="tooltip">
								<div class="arrow"></div>
								<div class="tooltip-inner">전체 ${paginationInfo.totalRecordCount} 건</div>
							</div>
						</div>
	              		<!-- table:start -->
		      			<table id="datalist" class="table table-hover footable footable-paging footable-paging-center ">
		          		<colgroup>
		          			<col width='5%' >
		                  	<col width='10%'>
		                  	<col width='10%'>
		                  	<col width='5%'>
		                  	<col width='10%'>
		                  	<col width='*'>
		                  	<col width='4%'>
		                  	<col width='4%'>
		                  	<col width='4%'>
		                  	<col width='4%'>
		                  	<col width='4%'>
		                  	<col width='4%'>
		                  	<col width='5%'>
		                  	<col width='8%'>
		                  	<col width='5%'>
		          		</colgroup>
		          		<thead>
		          			<tr>		            			
		            			<th class="text-middle text-center">번호</th>
								<th class="text-middle text-center">성명</th>
								<th class="text-middle text-center">생년월일</th>
								<th class="text-middle text-center">지역</th>
								<th class="text-middle text-center">연락처/이메일</th>
								<th class="text-middle text-center">영어분야(품목)</th>
								<th class="text-middle text-center">양식</th>
								<th class="text-middle text-center">어선<br>어업</th>
								<th class="text-middle text-center">가공<br>공장</th>
								<th class="text-middle text-center">염전</th>
								<th class="text-middle text-center">어촌<br>광광</th>
								<th class="text-middle text-center">해양<br>수산<br>레저</th>
								<th class="text-middle text-center">기타</th>
								<th class="text-middle text-center">어업경력<br>주거형태</th>
								<th class="text-middle text-center">관리</th>
	            			</tr>        				
		             	</thead>
						<tbody>
      						<c:if test="${empty list_total}">
								<tr><td colspan="15" class="text-center table-active">등록 된 홈스테이가 없습니다.</td></tr>	  
			            	</c:if>
			            	<c:forEach var="item" varStatus="status" items="${list_total}">
			            		<c:set var="hs_addr_set" value="${item.hsaddr1}"/>
								<c:set var="hs_addr_set_array" value="${fn:split(hs_addr_set, ' ') }"/>
							<tr>
	                  			<td class="text-middle text-center">${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}</td>
								<td class="text-middle text-center">
									<c:if test="${item.hsst eq 'N'}">
										<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 사용하지 않는 홈스테이 입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
			                   		<%--
			                   		<a href="javascript:fnView('${item.hssn}');"><c:out value="${item.hsnm}"/></a>
									--%>
									<c:out value="${item.hsnm}"/>
								</td>
								<td class="text-middle text-center">${item.hsbirth}</td>
								<td class="text-middle text-center">${hs_addr_set_array[0]}<br/>${hs_addr_set_array[1]}</td>
								<td class="text-middle text-center">${item.hshp}<br>${item.hsemail}</td>
								<td class="text-middle text-center">${item.hsitem}</td>
								<td class="text-middle text-center"><c:if test="${!empty item.hsitemfarm }"><i class="fas fa-check"></i></c:if></td>
								<td class="text-middle text-center"><c:if test="${!empty item.hsitemship }"><i class="fas fa-check"></i></c:if></td>
								<td class="text-middle text-center"><c:if test="${!empty item.hsitemfactory }"><i class="fas fa-check"></i></c:if></td>
								<td class="text-middle text-center"><c:if test="${!empty item.hsitemsolt }"><i class="fas fa-check"></i></c:if></td>
								<td class="text-middle text-center"><c:if test="${!empty item.hsitemtraval }"><i class="fas fa-check"></i></c:if></td>
								<td class="text-middle text-center"><c:if test="${!empty item.hsitemleisure }"><i class="fas fa-check"></i></c:if></td>
								<td class="text-middle text-center"><c:if test="${!empty item.hsitemetc}"><i class="fas fa-check"></i></c:if></td>
								<td class="text-middle text-center">${item.hscarrerfish}년/${item.hshometypenm}</td>
								<td class="text-center">
									<a href="#;" onclick="javascript:fnModify('${item.hssn}'); return false;"><i class="site-menu-icon fas fa-pen" aria-hidden="true"></i></a>
			                      	<a href="#" onclick="fnDelete(${item.hssn}); return false;"><i class="site-menu-icon fas fa-trash" aria-hidden="true"></i></a>
								</td>  
		                    </tr>
			            	</c:forEach>
						</tbody>
		 				<tfoot>
		 					<tr class="footable-paging">
		 						<td colspan="15">
		 							<div class="footable-pagination-wrapper">
		 								<ul class="pagination">
		 									<ui:pagination paginationInfo = "${paginationInfo}" type="list" jsFunction="fnLinkPage" />
		 									<form:hidden path="pageIndex" />
		 								</ul>	 								
		 							</div>
		 						</td>
		 					</tr>
		 				</tfoot>				  
						</table>
	              		<!-- table:end -->
	              	</div>
				</div>
			</div>
		</div>
		<!-- End row -->
	</div>
	<!-- End page-content -->
</div>
<!-- End Page -->
 
</form:form>

    
    <script>
    $(function(){
    	$('.selectpicker_manual').selectpicker();
    });
    function fnModify(bdsn) {
    	document.viewForm.selectedId.value = bdsn;
        document.viewForm.action = "<c:url value='/seadm/homestay/modify.do'/>";
        document.viewForm.submit();
    }
    function fnView(bdsn) {
    	document.viewForm.selectedId.value = bdsn;
        document.viewForm.action = "<c:url value='/seadm/homestay/view.do'/>";
        document.viewForm.submit();
    }
    function fnLinkPage(pageNo){
        document.listForm.pageIndex.value = pageNo;
        document.listForm.action = "<c:url value='/seadm/homestay/list.do'/>";
        document.listForm.submit();
    }
	function fnDelete(obj) {
		if(confirm('정말 삭제하시겠습니까?')) {
			document.viewForm.selectedId.value = obj;
		    document.viewForm.action = "<c:url value='/seadm/homestay/delete_act.do'/>";
		    document.viewForm.submit();
		}
	}  
	function fnSearch(){
		document.listForm.pageIndex.value = 1;
		document.listForm.action = "<c:url value='/seadm/homestay/list.do'/>";
	    document.listForm.submit();
	}
    </script>    

<%@ include file="../tail.jsp" %>
