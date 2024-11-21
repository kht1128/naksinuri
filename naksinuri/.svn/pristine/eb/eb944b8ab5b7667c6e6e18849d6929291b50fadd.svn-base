<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<c:set var="_today" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${_today}" pattern="yyyy" var="_year"/>
<fmt:parseNumber var="max_year" value="${_year }"/>
	
<form:form commandName="eduCurriculumVO" id="listForm" name="listForm" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value=""/>
<input type="hidden" id="CRS_GRP_CD" name="CRS_GRP_CD" value=""/>

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
        	<c:choose><%-- 권한분류 --%>
				<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
					<a class="append-rows btn btn-primary btn-outline clk_add_data" href="javascript:void(0)" 
		        	data-crs-sn="" 
		        	data-type-str=""
		        	data-linkurl="/eduadm/curriculum/write.do">
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">새로운 교육과정 등록하기</span>
		        	</a> 
				</c:when>
				<%-- 해양경찰서 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
					
				</c:when>
				<%-- 지자체 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
				1
				</c:when>
				<%-- 교육기관 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
					<a class="append-rows btn btn-primary btn-outline clk_add_data" href="javascript:void(0)" 
		        	data-crs-sn="" 
		        	data-type-str=""
		        	data-linkurl="/eduadm/curriculum/write.do">
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">새로운 교육과정 등록하기</span>
		        	</a> 
				</c:when>
				<%-- 기타 거부 --%>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose><%-- End 권한분류 --%>        	
        </div>
      </div>

      <div class="page-content container-fluid">
         <div class="row">                
          <div class="col-xl-12">
			<c:choose><%-- 권한분류 --%>
				<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
					<div role="alert" class="alert dark alert-warning alert-dismissible">
						<button aria-label="Close" data-dismiss="alert" class="close" type="button">
							<span aria-hidden="true">×</span>
						</button>
						<h4><i class="icon wb-bell" aria-hidden="true"></i> Notice</h4>
						<p>교육과정이 최종 <b>승인</b>이 되면 모집기간에 신청 접수가 시작됩니다.<br>수강인원이 1명 이상인 경우 교과목을 추가하거나 수정할 수 없으므로 승인전에 필히 교과목 상태를 확인해주세요.
							<br>교육과정을 <b>승인대기</b> 상태로 변경 후 수강인원을 모두 삭제하시면 교과목을 다시 설정 하실 수 있습니다. 
						</p>
					</div>
				</c:when>
				<%-- 해양경찰서 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
					
				</c:when>
				<%-- 지자체 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
					
				</c:when>
				<%-- 교육기관 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
					<div role="alert" class="alert dark alert-warning alert-dismissible">
						<button aria-label="Close" data-dismiss="alert" class="close" type="button">
							<span aria-hidden="true">×</span>
						</button>
						<h4><i class="icon wb-bell" aria-hidden="true"></i> Notice</h4>
						<p>교육과정의 상태가 최종 <b>승인</b>이 되면 모집기간에 신청 접수가 시작됩니다.</p>
					</div>
				</c:when>
				<%-- 기타 거부 --%>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose><%-- End 권한분류 --%>			
          	<div class="panel mb-20">
	  			<div class="panel-body">
	  				
	  				<div class="input-group col-lg-12 p-0 ">
						<div class="col-lg-3 p-0 ">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="searchYear">							
							<option value="" <c:if test="${empty searchYear}">selected</c:if> >전체년도대상</option>
							<c:forEach var="i" begin="2016" end="${max_year+1}">
		        				<option value="${i}" <c:if test="${i eq searchYear}">selected</c:if> >${i}</option>
							</c:forEach>		        			
		      				</select>
						</div>
						<div class="col-lg-4 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CRS_GRP_CD_1">
							<option value="" <c:if test="${empty frm_CRS_GRP_CD}">selected</c:if> >교육그룹선택(전체보기)</option>
							<c:forEach var="item" items="${list_edu_grp_cd}">
		        				<option value="${item.CD_ID}" <c:if test="${frm_CRS_GRP_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
							</c:forEach>		        			
		      				</select>
						</div>
						<c:choose><%-- 권한분류 --%>
							<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
								<div class="col-lg-1 p-0 pl-10">
									<span class="input-group-text bg-white border-0">또는</span>
								</div>
								<div class="col-lg-4 p-0 pl-10">								
									<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CRS_GRP_CD_2">
									<option value="" <c:if test="${empty frm_CRS_GRP_CD}">selected</c:if> >과거 교육그룹선택(전체보기)</option>
									<c:forEach var="item" items="${list_prev_edu_grp_cd}">
				        				<option value="${item.CD_ID}" <c:if test="${frm_CRS_GRP_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
									</c:forEach>		        			
				      				</select>									
								</div>
							</c:when>
							<%-- 해양경찰서 담당자 --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
								<div class="col-lg-5 p-0 pl-10">
									<input type="hidden" name="CRS_GRP_CD_2" value=""/>
								</div>
							</c:when>
							<%-- 지자체 담당자 --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
								<div class="col-lg-5 p-0 pl-10">
									<input type="hidden" name="CRS_GRP_CD_2" value=""/>
								</div>
							</c:when>
							<%-- 교육기관 담당자 --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
								<div class="col-lg-5 p-0 pl-10">
									<input type="hidden" name="CRS_GRP_CD_2" value=""/>
								</div>
							</c:when>
							<%-- 기타 거부 --%>
							<c:otherwise>
								<div class="col-lg-5 p-0 pl-10">
								</div>
							</c:otherwise>
						</c:choose><%-- End 권한분류 --%>		
					</div>		
					<div class="input-group col-lg-12 p-0 mt-10">	
						<div class="col-lg-3 p-0 ">
							<select class="form-control selectpicker_manual " data-style="btn-outline btn-default" name="CRS_MBR_CD" <c:if test="${not empty LoginVO.MBR_TRGT_CD}">disabled</c:if> >
								<option value="" >교육대상(업종)전체</option>
								<c:forEach var="item" items="${list_mbr_trgt_cd}">
									<option value="${item.CD_ID}" <c:if test="${item.CD_ID eq CRS_MBR_CD }">selected</c:if> >${item.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="col-lg-3 p-0 pl-10">
							<select class="form-control selectpicker_manual " data-style="btn-outline btn-default" name="CAT_INS_SN" <c:if test="${not empty LoginVO.MBR_EDU_INS_CD}">disabled</c:if> >
								<option value="" >교육기관 전체</option>
								<c:forEach var="item_category" items="${list_ins_info_cd}">
									<option value="${item_category.CAT_INS_SN}" <c:if test="${item_category.CAT_INS_SN eq CAT_INS_SN }">selected</c:if> >${item_category.CAT_INS_NM}</option>
								</c:forEach>        			
		      				</select>
						</div>
						<div class="col-lg-3 p-0 pl-10">
							<div class="input-group">
								<input type="text" class="form-control datepickerStrSearch " id="datepickerStrSearch" name="searchStrDate" placeholder="교육시작일자" autocomplete="off" value="${searchStrDate}" data-toggle="tooltip" data-original-title="교육시작일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="datepickerStrSearch" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<div class="col-lg-3 p-0 pl-10">
							<div class="input-group">
								<input type="text" class="form-control datepickerEndSearch " id="datepickerEndSearch" name="searchEndDate" placeholder="교육종료일자" autocomplete="off" value="${searchEndDate}" data-toggle="tooltip" data-original-title="교육종료일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="datepickerEndSearch" aria-label="Close"></button></span>
								</div>
							</div>
						</div>						
					</div>	  
					<div class="input-group col-lg-12 p-0 mt-10">	  	
						<div class="col-lg-6 p-0">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual " data-style="btn-outline btn-default" name="LOCK_ST">
								<option value="" >모집상태</option>
			        			<option value="0" <c:if test="${LOCK_ST eq '0'}">selected</c:if> >모집중</option>
			        			<option value="1" <c:if test="${LOCK_ST eq '1'}">selected</c:if> >모집완료</option>
		      				</select>
						</div>	
						<div class="col-lg-2 p-0 pl-10">
							<div class="btn-group w-p100">
								<div class="input-group-prepend">
									<span class="input-group-text">출력수</span>
								</div>
								<input type="text" class="form-control input-auto-enter-key" name="pageUnit" placeholder="" autocomplete="off" value="${paginationInfo.recordCountPerPage}" data-toggle="tooltip" data-original-title="페이지당 화면에 출력할 게시물 수량을 입력하세요.">
							</div>
						</div>
						<div class="col-lg-2 p-0 pl-10 text-right">
							<%-- <input type="hidden" class="form-control" name="searchKeyword" placeholder="이름,연락처,아이디를 입력하세요." autocomplete="off" value="2020"> --%>
							<button type="button" class="btn btn-primary clk_search_btn w-p100"><i class="icon wb-search" aria-hidden="true"></i></button>
						</div>	
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
	          		</colgroup>
	          		<thead>
			            <tr>
			            	<th class="text-middle text-center">No</th>
			            	<th class="text-middle text-center">교육그룹</th>
							<th class="text-middle text-center">교육과정명<br>교육대상구분</th>
							<th class="text-middle text-center">교육기관명</th>
		                	<th class="text-middle text-center">교육기간<br>(모집기간)</th>
		                	<th class="text-middle text-center">교육정보</th>
		                	<th class="text-middle text-center">신청자수(명)/<br>모집최대인원(명)</th>
		                	<th class="text-middle text-center">이수완료(명)</th>
		                	<th class="text-middle text-center">상태</th>
		                	<th class="text-middle text-center">관리</th>
        				</tr>
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="10"/>
						<c:if test="${empty list}">
		            		<tr><td colspan="${table_cell_cnt}" class="text-center table-active">등록 된 자료가 없습니다.</td></tr>  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            	
		            		<fmt:parseDate value="${fn:replace(item.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
		            		<fmt:parseDate value="${fn:replace(item.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
		            		<fmt:parseDate value="${fn:replace(item.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
		            		<fmt:parseDate value="${fn:replace(item.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
		            		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) " var="CRS_STR_DT"/>
		            		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH:mm" var="CRS_END_DT"/>
		            		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd" var="RECRUIT_STR_DT"/>
		            		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd" var="RECRUIT_END_DT"/>
			           		<fmt:formatDate value="${parse_crs_str_dt}" pattern="HH:mm" var="CRS_STR_TIME"/>
			           		<fmt:formatDate value="${parse_crs_end_dt}" pattern="HH:mm" var="CRS_END_TIME"/>
			           		
		            		
		            		<!-- 교육분류에 따른 표기 처리 : start -->
		            		<c:choose>
								<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">
									<c:set var="label_crs_time" value="개월" />
									<%-- <c:set var="is_allow_online" value="0" /> --%>
								</c:when>
								<c:otherwise>
									<c:set var="label_crs_time" value="시간" />
									<%-- <c:set var="is_allow_online" value="1" /> --%>
								</c:otherwise>											
							</c:choose>
							<c:choose>
								<c:when test="${item.CRS_GRP_CD eq 'CIDE00000000000'}">
									<c:set var="is_allow_online" value="1" />
								</c:when>
								<c:otherwise>
									<c:set var="is_allow_online" value="0" />
								</c:otherwise>
							</c:choose>
							<!-- 교육분류에 따른 표기 처리 : end -->
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle text-center">${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}</td>
		            			<!-- 
		            			<td class="text-middle">
		            				<c:choose>
										<c:when test="${item.CRS_TYPE eq 'fshsk_trnng'}">귀어창업기술교육</c:when>
										<c:when test="${item.CRS_TYPE eq 'wknd_trnng'}">주말교육</c:when>
										<c:when test="${item.CRS_TYPE eq 'default'}">종합교육</c:when>
										<c:when test="${item.CRS_TYPE eq 'default_online'}">온라인강좌</c:when>
										<c:otherwise>기타</c:otherwise>											
									</c:choose>
		            			</td>
		            			 -->
		            			<td class="text-middle">
		            				<c:forEach var="cate" items="${list_all_edu_grp_cd}">
		            					<c:if test="${item.CRS_GRP_CD eq cate.CD_ID}">${cate.CD_NM}</c:if>
									</c:forEach>
		            			</td>
				                <td class="text-middle">
				                	<c:choose>
										<c:when test="${item.DEL_ST eq '1'}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 교육과정입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.USE_ST ne '1'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 교육과정입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
				               		<c:if test="${item.LOCK_ST eq '1' or item.CRS_ST eq '2'}">
										<i class="site-menu-icon wb-lock" aria-hidden="true" data-content="현재 모집이 중단되었습니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
									</c:if>
					                <%--
					                <button type="button" class="btn btn-link" onclick="pageMove(this)" data-crs-sn="${item.CRS_SN}" data-type-str="dtl">${item.CRS_NM}</button>
					                --%>
					                <c:out value="${item.CRS_NM}"/>
					                <br>
					                <c:forEach var="item_category" items="${list_mbr_trgt_cd}">
										<c:if test="${item_category.CD_ID eq item.CRS_MBR_CD }"><span class="grey-500">${item_category.CD_NM}</span></c:if>
									</c:forEach>	     
				                </td>
				                <td class="text-middle text-center">${item.CAT_INS_NM}</td>
				                <td class="text-middle text-center">${CRS_STR_DT}&nbsp;${CRS_STR_TIME}&nbsp;~&nbsp;${CRS_END_TIME}<br><span class="grey-500">(${RECRUIT_STR_DT}&nbsp;~&nbsp;${RECRUIT_END_DT})</span></td>
				                <td class="text-middle text-center">
				                	<%-- <span class="badge badge-outline badge-dark">총 ${item.CRS_TOT_TIME + item.SUM_TOT_TIME} ${label_crs_time} 교육</span>
				                	<span class="badge badge-outline badge-dark">총 ${item.CRS_TOT_POINT + item.SUM_TOT_POINT} 점 이수</span> --%>
				                	<span class="font-size-12"><c:out value="${item.CRS_PLACE}"/></span> 
				                	<br><span class="font-size-12 grey-500"><c:out value="${item.CRS_ADDR}"/></span> 
				                </td>
				                <td class="text-middle text-center">	
				                	<span class="badge badge-warning">${item.MBR_CUR_CNT}명</span>
				                	&nbsp;/&nbsp;
				                	<span class="badge badge-info">
				                	<c:choose>
				                		<c:when test="${item.MBR_MAX_CNT eq 0}">무제한</c:when>
				                		<c:otherwise>${item.MBR_MAX_CNT}명</c:otherwise>				                	
					                </c:choose>
					                </span>
				                </td>
				                <td class="text-middle text-center">
				                	<span class="badge badge-success">${item.MBR_CMPLT_CNT}명</span>	
				                </td>
		                 		<td class="text-middle text-center">
		                 			<c:choose>
										<c:when test="${item.DEL_ST eq '1'}">
											
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${item.CRS_ST eq '1'}"><span class="badge badge-outline badge-primary">승인완료</span></c:when>
												<c:otherwise><span class="badge badge-outline badge-dark">승인대기</span></c:otherwise>											
											</c:choose>
											<br>
											<c:choose>
												<c:when test="${item.CRS_ST eq '0'}"><span class="badge badge-dark">신청대기중</span></c:when>
												<c:otherwise>
													<c:choose>
								                		<c:when test="${item.CRS_ST eq '2'}"><span class="badge badge-dark">신청받지않음</span></c:when>
								                		<c:otherwise>
								                			<c:choose>
										                		<c:when test="${item.LOCK_ST eq 0}"><span class="badge badge-primary">신청접수중</span></c:when>
										                		<c:otherwise><span class="badge badge-danger">신청마감</span></c:otherwise>				                	
										                	</c:choose>	
								                		</c:otherwise>				                	
								                	</c:choose>										
												</c:otherwise>
											</c:choose>
										</c:otherwise>											
									</c:choose>           	
									<br>
									<c:choose>
										<c:when test="${item.EDU_APLY_NTCN_SNDNG_YN eq 'Y'}">
											<span class="badge badge-outline badge-primary">교육 알림 전송</span>
										</c:when>
										<c:otherwise>
											<span class="badge badge-outline badge-danger">교육 알림 미전송</span>
										</c:otherwise>
									</c:choose>									
				                </td>
				                <td class="text-middle text-center">
				                <c:choose><%-- 권한분류 --%>
									<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
										<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-crs-sn="${item.CRS_SN}" 
					                	data-type-str=""
					                	data-linkurl="/eduadm/curriculum/modify.do">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
										<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
										data-crs-sn="${item.CRS_SN}"
										data-del-st="${item.DEL_ST}" 
										data-type-str=""
										data-linkurl="/eduadm/curriculum/delete_act.do">
					                		<i class="icon wb-trash" aria-hidden="true"></i>
					                	</a>
										<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_copy_data" data-toggle="tooltip" data-original-title="복사" 
										data-crs-sn="${item.CRS_SN}" 
										data-type-str=""
										data-linkurl="/eduadm/curriculum/copy.do">
					                		<i class="icon wb-add-file" aria-hidden="true"></i>
					                	</a>
					                	<c:if test="${is_allow_online eq '1'}">
					                		<br><button type="button" class="btn btn-outline btn-dark btn-xs" onclick="pageMove(this)" data-crs-sn="${item.CRS_SN}" data-type-str="dtl">교과목관리 이동</button>
					                	</c:if>
					                	<br><button type="button" class="btn btn-outline btn-success btn-xs" onclick="pageMoveMbr(this)" data-crs-sn="${item.CRS_SN}" data-type-str="">수강자목록 이동</button>
									</c:when>
									<%-- 해양경찰서 담당자 --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
										
									</c:when>
									<%-- 지자체 담당자 --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
										
									</c:when>
									<%-- 교육기관 담당자 --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
										<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-crs-sn="${item.CRS_SN}" 
					                	data-type-str=""
					                	data-linkurl="/eduadm/curriculum/modify.do">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
										<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
										data-crs-sn="${item.CRS_SN}"
										data-del-st="${item.DEL_ST}" 
										data-type-str=""
										data-linkurl="/eduadm/curriculum/delete_act.do">
					                		<i class="icon wb-trash" aria-hidden="true"></i>
					                	</a>
										<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_copy_data" data-toggle="tooltip" data-original-title="복사" 
										data-crs-sn="${item.CRS_SN}" 
										data-type-str=""
										data-linkurl="/eduadm/curriculum/copy.do">
					                		<i class="icon wb-add-file" aria-hidden="true"></i>
					                	</a>
					                	<c:if test="${is_allow_online eq '1'}">
					                		<br><button type="button" class="btn btn-outline btn-dark btn-xs" onclick="pageMove(this)" data-crs-sn="${item.CRS_SN}" data-type-str="dtl">교과목관리 이동</button>
					                	</c:if>
										<button type="button" class="btn btn-outline btn-success btn-xs" onclick="pageMoveMbr(this)" data-crs-sn="${item.CRS_SN}" data-type-str="">수강자목록 이동</button>
									</c:when>
									<%-- 기타 거부 --%>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose><%-- End 권한분류 --%>
				                </td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging">
	 						<td colspan="${table_cell_cnt}">
	 							<div class="footable-pagination-wrapper">
	 								<ul class="pagination">
	 									<ui:pagination paginationInfo = "${paginationInfo}" type="admin" jsFunction="fn_egov_link_page" />
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
      </div>
    </div>
    <!-- End Page -->
</form:form>

	<style>
	.datepicker-dropdown{z-index:99999!important;}
	</style>

    <script>
    function refreshListPage() {
    	$('.clk_search_btn').click();
    }
    $('.input-auto-enter-key').keypress(function(key) {
    	if(key.keyCode == 13){
    		$('.clk_search_btn').click();
        }
    });
    $(".clk-clear-input").click(function() {
    	var target = $(this).attr('for');
    	$('#'+target).val("");
    });
    $(function(){
    	$('.selectpicker_manual').selectpicker();
    	$('.datepickerStrSearch').datepicker({
		    format: 'yyyy-mm-dd',
		    //startDate: '0d',
		    autoclose: true,
		    language: "kr",
		});
		$('.datepickerEndSearch').datepicker({
		    format: 'yyyy-mm-dd',
		    //startDate: '0d',
		    autoclose: true,
		    language: "kr",
		});
    });
    $(".clk_search_btn").click(function() {
    	var form = document.getElementById('listForm');
    	
    	var crs_grp_cd = form.CRS_GRP_CD_1.value;
    	var crs_grp_cd_2 = form.CRS_GRP_CD_2.value;
    	if(crs_grp_cd.length!=0 && crs_grp_cd_2.length!=0) {
    		alertify.confirm('교육그룹은 하나만 선택 가능합니다.<br>첫번째를 선택하시려면 OK 버튼을<br>두번째를 선택하시려면 CANCEL 버튼을 눌러주세요.',
    			function(){ //ok
    				form.CRS_GRP_CD_2.value = '';	
    				
    				form.pageIndex.value = '1';
	    	    	form.CRS_GRP_CD.value = crs_grp_cd;
	    	    	form.action = '';
	    	    	form.submit();
    			},function(){ //false
	    			crs_grp_cd = crs_grp_cd_2;
	    	    	form.CRS_GRP_CD_1.value = '';
	    	    	
	    	    	form.pageIndex.value = '1';
	    	    	form.CRS_GRP_CD.value = crs_grp_cd;
	    	    	form.action = '';
	    	    	form.submit();
    		});
    	} else {
    		form.pageIndex.value = '1';
        	form.CRS_GRP_CD.value = crs_grp_cd;
        	form.action = '';
        	form.submit();
    	}
    });  
    
    function pageMove(obj) {
    	var form = document.getElementById('listForm');
    	form.pageIndex.value = 1;
    	form.CRS_SN.value = $(obj).attr('data-crs-sn');
    	form.action = "/eduadm/curriculum/listDtl.do";
    	form.submit();
    }
    function pageMoveMbr(obj) {
    	var form = document.getElementById('listForm');
    	form.pageIndex.value = 1;
    	form.CRS_SN.value = $(obj).attr('data-crs-sn');
    	form.action = "/eduadm/mbrhstry/list.do";
    	form.submit();
    }
    function fn_egov_link_page(pageNo){
    	var crs_grp_cd = document.listForm.CRS_GRP_CD_1.value;
    	var crs_grp_cd_2 = document.listForm.CRS_GRP_CD_2.value;
    	if(crs_grp_cd_2.length!=0) {
    		crs_grp_cd = crs_grp_cd_2;
    		document.listForm.CRS_GRP_CD_1.value = '';
    	}
    	document.listForm.CRS_GRP_CD.value = crs_grp_cd;
    	document.listForm.pageIndex.value = pageNo;
    	document.listForm.action = "";
       	document.listForm.submit();
    }
    $(".clk_copy_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.CRS_SN.value = $(this).attr('data-crs-sn');
    	form.action = '';
    	var data_crs_sn = $(this).attr('data-crs-sn');
    	var data_linkurl = $(this).attr('data-linkurl');
    	alertify.confirm('해당 교육을 복제(복사) 하시겠습니까? 회원정보를 제외한 교육정보 및 교과목정보만 복제 됩니다.', function(){ 
    		$.ajax({
    			type:"POST",
    			url :data_linkurl,
    			data:$('#listForm').serialize(),
    			dataType: 'json',
    			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    			async: true,
    			success: function(data, status, xhr) {
    				if(data.errCnt > 0) {
    					alertify.alert(data.msg);
    				} else {
    					if(data.error == '1') {
    						alertify.alert(data.msg);
    					} else {
    						//window.location.reload();
    						document.listForm.submit();
    						//refreshListPage();
    					}
    				}
    			},
    			beforeSend : function(xhr, opts) {
    				//console.log('before!');
    				if(isClickRequestLocked()) {
    					xhr.abort();
    					return;
    				}
    			},
    			complete : function() {
    				//console.log('complete!');
    				clickRequestLockStop();
    		    },
    			error: function(jqXHR, textStatus, errorThrown) {
    				//console.log('error!');
    				clickRequestLockStop();
    			}
    		});
    	});
   	});  
    $(".clk_add_data,.clk_mod_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.CRS_SN.value = $(this).attr('data-crs-sn');
    	form.action = '';
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#listForm').serialize(),
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#admPublicModal").html(data);
				$("#admPublicModal").modal('show');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	});   
    $(".clk_del_data").click(function() {
    	var data_del_st = $(this).attr('data-del-st');
    	var data_linkurl = $(this).attr('data-linkurl');
    	var alert_message = "";
    	if(data_del_st == '1') {
    		alert_message = "실제 데이터를 완전히 삭제합니다.<br>그래도 삭제 하시겠습니까?";
    	} else {
    		alert_message = "삭제 하시겠습니까?";
    	}
    	var data_crs_sn = $(this).attr('data-crs-sn');
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('listForm');
	    	form.CRS_SN.value = data_crs_sn;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :data_linkurl,
    			data:$('#listForm').serialize(),
    			dataType: 'json',
    			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    			async: true,
    			success: function(data, status, xhr) {
    				if(data.errCnt > 0) {
    					//alert(data.msg);
    					alertify.alert(data.msg);
    				} else {
    					if(data.error == '1') {
    						//alert(data.msg);
    						alertify.alert(data.msg);
    					} else {
    						//window.location.reload();
    						document.listForm.submit();
    						//refreshListPage();
    					}
    				}
    			},
    			beforeSend : function(xhr, opts) {
    				//console.log('before!');
    				if(isClickRequestLocked()) {
    					xhr.abort();
    					return;
    				}
    			},
    			complete : function() {
    				//console.log('complete!');
    				clickRequestLockStop();
    		    },
    			error: function(jqXHR, textStatus, errorThrown) {
    				//console.log('error!');
    				clickRequestLockStop();
    			}
    		});    		 
    	});
   	});  
    </script>
    
<script>
   var MBR_PLEDGE_ST = '${MBR_PLEDGE_ST}';
   if(MBR_PLEDGE_ST == 'N'){
      $.ajax({
         type:"POST",
         url :"/adm/member/pledge_agree_ajax.do",
         data:null,
         dataType: 'html',//"json",
         contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
         async: true
      }) 
      .done(function(data, status, xhr) {
         //console.log('success!');
         $("#admPublicModal").html(data);
         //$("#admPublicModal").modal('show');
         $("#admPublicModal").modal({backdrop: 'static', keyboard: false});
      })
      .always(function() {
         //console.log('complete!');
       })
      .fail(function(jqXHR, textStatus, errorThrown) {
         //console.log('error!');
      });
   } 
</script>
        

<%@ include file="../tail.jsp" %>
