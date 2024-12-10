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

<fmt:parseDate value="${fn:replace(info.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
<fmt:parseDate value="${fn:replace(info.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
<fmt:parseDate value="${fn:replace(info.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
<fmt:parseDate value="${fn:replace(info.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) HH시 mm분 부터" var="CRS_STR_DT"/>
<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH시 mm분 까지" var="CRS_END_DT"/>
<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd (E) HH시 mm분 부터" var="RECRUIT_STR_DT"/>
<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd (E) HH시 mm분 까지" var="RECRUIT_END_DT"/>
          		 		
<!-- 교육분류에 따른 표기 처리 : start -->
<c:choose>
	<c:when test="${info.CRS_TYPE eq 'fshsk_trnng'}">
		<c:set var="label_crs_time" value="개월" />
		<c:set var="is_allow_online" value="0" />
	</c:when>
	<c:otherwise>
		<c:set var="label_crs_time" value="시간" />
		<c:set var="is_allow_online" value="1" />
	</c:otherwise>											
</c:choose>
<!-- 교육분류에 따른 표기 처리 : end -->

<%-- 기본 데이터 처리 : start --%>
<c:set var="CRS_MBR_NM" value=""/>
<c:if test="${not empty info.CRS_MBR_CD}">
	<c:forEach var="item" items="${list_mbr_cd}">
		<c:if test="${info.CRS_MBR_CD eq item.CD_ID}">
			<c:set var="CRS_MBR_NM" value="${item.CD_DES}"/>
		</c:if>
	</c:forEach>
</c:if>
<%-- 기본 데이터 처리 : end --%>

<%-- <form:form commandName="myHistoryVO" id="crsEndDtForm" name="crsEndDtForm" method="post">
	<input type="hidden" name="CRS_END_DT" value="${CRS_END_DT}"/>
</form:form> --%>
<input type="hidden" name="crsEndDt" value="${CRS_END_DT}"/>

<c:choose>
	<c:when test="${empty info}">
		<c:if test="${not empty IS_TABLE_TR and IS_TABLE_TR eq 'Y'}"><%-- 모달이 아닌 경우 --%>
			<tr><td colspan="2">해당 교육이 존재하지 않거나 더 이상 사용하지 않는 교육정보 입니다.</td></tr>
		</c:if>
		<c:if test="${empty IS_TABLE_TR}">
			<div class="modal-dialog" role="document">
				<div class="modal-content form-horizontal">
					<div class="modal-header">
							<h4 class="modal-title" style="display: inline;">교육정보</h4>
			 				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				   			<span aria-hidden="true">×</span>
				 			</button>
				 			
					</div>
					<div class="modal-body pt-0 pl-30 pr-30">
						<div class="form-group" >
							<p class="pt-30 pb-30 pl-5 pr-5 text-center">해당 교육이 존재하지 않거나 더 이상 사용하지 않는 교육정보 입니다.</p>
						</div>
						<div class="text-right">
				            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
				        </div>
					</div>
				</div>
			</div>
		</c:if>
	</c:when>
	<c:otherwise>
	
		<c:if test="${not empty IS_TABLE_TR and IS_TABLE_TR eq 'Y'}"><%-- 모달이 아닌 경우 --%>
				
				<tr>
					<th>교육지역</th>
					<td>
						<c:forEach var="item" items="${list_edu_grp_cd}">
		       				<c:if test="${info.CRS_GRP_CD eq item.CD_ID}">${item.CD_NM}</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>교육대상</th>
					<td>
						<c:forEach var="item" items="${list_mbr_cd}">
		       				<c:if test="${info.CRS_MBR_CD eq item.CD_ID}">${item.CD_NM}</c:if>
						</c:forEach>
					</td>
				</tr>
				<%--
				<tr>
					<th>교육분류</th>
					<td>
						<c:choose>
							<c:when test="${info.CRS_TYPE eq 'fshsk_trnng'}"><c:set var="crs_type_str" value="귀어창업기술교육"/></c:when>
							<c:when test="${info.CRS_TYPE eq 'wknd_trnng'}"><c:set var="crs_type_str" value="주말교육"/></c:when>
							<c:when test="${info.CRS_TYPE eq 'default'}"><c:set var="crs_type_str" value="종합교육"/></c:when>
							<c:when test="${info.CRS_TYPE eq 'default_online'}"><c:set var="crs_type_str" value="온라인강좌"/></c:when>
							<c:otherwise><c:set var="crs_type_str" value="기타"/></c:otherwise>									
						</c:choose>
						<input type="text" class="basic_input w100 readonly" value="${crs_type_str}" disabled>
					</td>
				</tr>
				 --%>
				<tr>
					<th>교육과정명</th>
					<td>${info.CRS_NM}</td>
				</tr>
				<c:choose>
					<c:when test="${info.CRS_GRP_CD eq 'CIDE00000000000'}">
						<tr>
							<th>신청기간</th>
							<td>${RECRUIT_STR_DT} ~ ${RECRUIT_END_DT}</td>
							<%-- <td>${RECRUIT_STR_DT} ~ 집합교육 재개 시까지</td> --%>
						</tr>
						<tr>
							<th>교육일시</th>
							<td>${CRS_STR_DT} ~ ${CRS_END_DT}</td>
							<!-- <td>상시(집합교육 재개 시까지 한시 운영)</td> -->
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th>신청기간</th>
							<td>${RECRUIT_STR_DT} ~ ${RECRUIT_END_DT}</td>
						</tr>
						<tr>
							<th>교육일시</th>
							<td>${CRS_STR_DT} ~ ${CRS_END_DT}</td>
						</tr>
						<tr>
							<th>교육기관</th>
							<td>${info.CAT_INS_NM}</td>
						</tr>
						<tr>
							<th>교육장소</th>
							<td>${info.CRS_PLACE}</td>
						</tr>
						<tr>
							<th>교육장상세주소</th>
							<td>${info.CRS_ADDR}</td>
						</tr>
						<tr>
							<th>모집최대인원</th>
							<td>${info.MBR_MAX_CNT} 명</td>
						</tr>					
					</c:otherwise>									
				</c:choose>				
				<c:if test="${not empty info.CRS_DSCRP}">
				<tr>
					<th>안내사항</th>
					<td>
						${fn:replace(info.CRS_DSCRP, lf, "<br/>")}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty info.CRS_SCHDL_FILE_SN}">
				<tr>
					<th>교육시간표</th>
					<td>
						<img src="/cmm/fms/getImage.do?atchFileId=${info.CRS_SCHDL_FILE_SN}&fileSn=0" alt="교육시간표"/>
					</td>
				</tr>
				</c:if>	
				<c:if test="${info.CRS_LOCK_AREA_CD ne null and info.CRS_LOCK_AREA_ST eq '1'}">
				<tr>
					<th>신청지역제한</th>
					<td class="green-600 font-weight-bold">
						<c:forEach var="item" items="${list_lock_area_ship_grp_cd}">
							<c:if test="${info.CRS_LOCK_AREA_CD eq item.CD_ID}">${item.CD_NM}</c:if>
						</c:forEach>
						<c:forEach var="item" items="${list_lock_area_house_grp_cd}">
							<c:if test="${info.CRS_LOCK_AREA_CD eq item.CD_ID}">${item.CD_NM}</c:if>
						</c:forEach>	
						<p class="red-600 mt-10">위 지역 외에는 수강신청이 불가능 합니다. 본인의 신고지(추가상세정보에 기입되는)를 확인하시고 신청 해주시기 바랍니다.</p>	
						<p class="blue-grey-800 mt-10">(2019년도 교육 미이수자의 경우 지역제한 없이 수강신청이 가능합니다.)</p>
					</td>
				</tr>					
				</c:if>
		</c:if>
		<c:if test="${empty IS_TABLE_TR}">
		
			<style>
			.basic_tbl th, .basic_tbl td{border:0px;}
			</style>
		
			<form:form commandName="eduMyHistoryVO" id="modalForm1" name="modalForm2" method="post">
			<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info.CRS_SN}"/>
			</form:form>	
			
			<div class="modal-dialog" role="document">
			<form id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
				action="">
		
				<div class="modal-header">
						<h4 class="modal-title" style="display: inline;">교육정보</h4>
			 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			   			<span aria-hidden="true">×</span>
			 			</button>
			 			
				</div>
				<div class="modal-body pt-0 pl-30 pr-30">
					<div class="form-group row modal-view-table">
					<table class="basic_tbl">
						<caption>교육정보</caption>
						<colgroup>
							<col width="120">
							<col>
						</colgroup>
						<thead>
							<tr class="table-cell-blind"><th></th></tr>
						</thead>
						<tbody>
							<tr>
								<th>교육지역</th>
								<td>
									<c:forEach var="item" items="${list_edu_grp_cd}">
					       				<c:if test="${info.CRS_GRP_CD eq item.CD_ID}">${item.CD_NM}</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<th>교육대상</th>
								<td>
									<c:forEach var="item" items="${list_mbr_cd}">
					       				<c:if test="${info.CRS_MBR_CD eq item.CD_ID}">${item.CD_NM}</c:if>
									</c:forEach>
								</td>
							</tr>
							<%--
							<tr>
								<th>교육분류</th>
								<td>
									<c:choose>
										<c:when test="${info.CRS_TYPE eq 'fshsk_trnng'}"><c:set var="crs_type_str" value="귀어창업기술교육"/></c:when>
										<c:when test="${info.CRS_TYPE eq 'wknd_trnng'}"><c:set var="crs_type_str" value="주말교육"/></c:when>
										<c:when test="${info.CRS_TYPE eq 'default'}"><c:set var="crs_type_str" value="종합교육"/></c:when>
										<c:when test="${info.CRS_TYPE eq 'default_online'}"><c:set var="crs_type_str" value="온라인강좌"/></c:when>
										<c:otherwise><c:set var="crs_type_str" value="기타"/></c:otherwise>									
									</c:choose>
									<input type="text" class="basic_input w100 readonly" value="${crs_type_str}" disabled>
								</td>
							</tr>
							 --%>
							<tr>
								<th>교육과정명</th>
								<td>${info.CRS_NM}</td>
							</tr>
							<c:choose>
								<c:when test="${info.CRS_GRP_CD eq 'CIDE00000000000'}">
									<tr>
										<th>신청기간</th>
										<td>${RECRUIT_STR_DT} ~ ${RECRUIT_END_DT}</td>
										<%-- <td>${RECRUIT_STR_DT} ~ 집합교육 재개 시까지</td> --%>
									</tr>
									<tr>
										<th>교육일시</th>
										<td>${CRS_STR_DT} ~ ${CRS_END_DT}</td>
										<!-- <td>상시(집합교육 재개 시까지 한시 운영)</td> -->
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<th>신청기간</th>
										<td>${RECRUIT_STR_DT} ~ ${RECRUIT_END_DT}</td>
									</tr>
									<tr>
										<th>교육일시</th>
										<td>${CRS_STR_DT} ~ ${CRS_END_DT}</td>
									</tr>
									<tr>
										<th>교육기관</th>
										<td>${info.CAT_INS_NM}</td>
									</tr>
									<tr>
										<th>교육장소</th>
										<td>${info.CRS_PLACE}</td>
									</tr>
									<tr>
										<th>교육장상세주소</th>
										<td>${info.CRS_ADDR}</td>
									</tr>
									<tr>
										<th>모집최대인원</th>
										<td>${info.MBR_MAX_CNT} 명</td>
									</tr>					
								</c:otherwise>									
							</c:choose>					
							<c:if test="${not empty info.CRS_DSCRP}">
							<tr>
								<th>안내사항</th>
								<td>
									${fn:replace(info.CRS_DSCRP, lf, "<br/>")}
								</td>
							</tr>
							</c:if>
							<c:if test="${not empty info.CRS_SCHDL_FILE_SN}">
							<tr>
								<th>교육시간표</th>
								<td>
									<img src="/cmm/fms/getImage.do?atchFileId=${info.CRS_SCHDL_FILE_SN}&fileSn=0" alt="교육시간표"/>
								</td>
							</tr>
							</c:if>	
							<c:if test="${info.CRS_LOCK_AREA_ST eq '1'}">
							<tr>
								<th>신청지역제한</th>
								<td class="green-600 font-weight-bold">
									<c:forEach var="item" items="${list_lock_area_ship_grp_cd}">
										<c:if test="${info.CRS_LOCK_AREA_CD eq item.CD_ID}">${item.CD_NM}</c:if>
									</c:forEach>
									<c:forEach var="item" items="${list_lock_area_house_grp_cd}">
										<c:if test="${info.CRS_LOCK_AREA_CD eq item.CD_ID}">${item.CD_NM}</c:if>
									</c:forEach>	
									<p class="red-600 mt-10">(신고지에 따라 신청지역제한으로 수강신청이 불가능 할 수 있습니다.)</p>	
								</td>
							</tr>					
							</c:if>
						</tbody>
					</table>
					</div>
					<div class="text-right">
					
						<%-- //신청 버튼 활성화 로직 --%>
						<c:set var="isOpened" value="false" />
						<c:set var="_today" value="<%=new java.util.Date()%>" />
						<fmt:formatDate value="${_today}" pattern="yyyy-MM-dd HH:mm:ss" var="CAL_TODAY"/>
						<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd 00:00:00" var="CAL_RECRUIT_STR_DT"/>
					    <fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd 23:59:59" var="CAL_RECRUIT_END_DT"/>
					    <c:if test="${CAL_RECRUIT_STR_DT  <= CAL_TODAY and CAL_TODAY <= CAL_RECRUIT_END_DT}">
					    	<c:set var="isOpened" value="true" />
					    </c:if>
					    <%-- End //신청 버튼 활성화 로직 --%>
					    
					    <%-- //신청 버튼 활성화 로직 : 모집인원마감 --%>
					    <c:if test="${isOpened eq true}">
					    	<c:if test="${not empty info.MBR_MAX_CNT and info.MBR_MAX_CNT != 0 and info.MBR_CUR_CNT+1 > info.MBR_MAX_CNT}">
					    		<c:set var="isOpened" value="false" />
					    	</c:if>
					    </c:if>					    	
					    <%-- //End of 신청 버튼 활성화 로직 : 모집인원마감 --%>
					    					    
						<%-- <c:if test="${isOpened eq true and info.LOCK_ST eq '0'}">
							<button type="button" class="btn btn-primary btn-outline clk_btn_act">신청하기</button>
						</c:if> --%>
			            <button type="button" class="btn btn-default btn-outline" id="modal_add" data-dismiss="modal">취소(닫기)</button>
			        </div>
			  	</div>
			</form>
			
			<script>
			$(".clk_btn_act").click(function() {
				var form = document.getElementById('modalForm1');
				form.action = '/educenter/trnng/agree.do';
				form.submit();
			});
			
			</script>
			
			
			<script>
			$("#modal_add").click(function() {
				//alert($("#focusLine"));
				$(".eduinfo_Bx").focus();

			});
			
			</script>			
			
				
			</div><!-- /.modal-dialog -->
		
		</c:if>
		
	</c:otherwise>
</c:choose>