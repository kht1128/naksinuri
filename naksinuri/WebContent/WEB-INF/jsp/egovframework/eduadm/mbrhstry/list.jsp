<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<style>
.w300px{width:300px !important;}
.w250px{width:250px !important;}
.w150px{width:150px !important;}
</style>
<c:set var="isLock" value="false"/>
	
<form:form commandName="EduCertificateVO" id="printForm" name="printForm" method="post">
<input type="hidden" name="LOG_UPD_MSG" value="" />
<input type="hidden" name="CRTF_TYPE_ST" value="" />
<input type="hidden" name="CRTF_SNs" value=""/>
<input type="hidden" name="CRS_SNs" value=""/>
<input type="hidden" name="HMBR_SNs" value=""/>
<input type="hidden" name="MBR_IDs" value=""/>
</form:form>
	
<form:form commandName="CodeSetVO" id="searchFormSido" name="searchFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value="${HMBR_SIDO_CD}"/>
<input type="hidden" name="HMBR_SIGNGU_CD" value="${HMBR_SIGNGU_CD}"/>
</form:form>

<form:form commandName="smsSendVO" id="ajaxSendSmsForm" name="ajaxSendSmsForm" method="post">
<input type="hidden" name="chkedMbrIds" value=""/>
</form:form>

<form:form commandName="myHistoryVO" id="ajaxSendCertificateSmsForm" name="ajaxSendCertificateSmsForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
</form:form>

<form:form commandName="eduMyHistoryVO" id="ajaxViewKakaoForm" name="ajaxViewKakaoForm" method="post">
<input type="hidden" name="MBR_IDS" value=""/>
<input type="hidden" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" name="CRS_MBR_CD" value="${parentInfo.CRS_MBR_CD}"/>
<input type="hidden" name="CRS_LAW_TYPE" value="${parentInfo.CRS_LAW_TYPE}"/>
<input type="hidden" name="TEMPLATE_ID">
</form:form>
	
<form:form commandName="eduMemberVO" id="ajaxMbrForm" name="ajaxMbrForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_DSCRP" value=""/>
<input type="hidden" name="MBR_SCRTY_KEY" value=""/>
</form:form>	

<form:form commandName="eduMyHistoryVO" id="surveyForm" name="surveyForm" method="post">
<input type="hidden" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" name="CRS_SV_ID" value="${parentInfo.CRS_SV_ID}"/>
</form:form>
	
<form:form commandName="eduMyHistoryVO" id="clearForm" name="clearForm" method="post">
<input type="hidden" name="LOG_UPD_MSG" value="" />
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value=""/>
</form:form>

<form:form commandName="eduMyHistoryVO" id="listForm" name="listForm" method="post">
<input type="hidden" name="LOG_UPD_MSG" value="" />
<input type="hidden" name="excel_msg" value="" />
<input type="hidden" name="excel_type" value="" />
<input type="hidden" name="excel_label" value="" />
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value=""/>
<input type="hidden" id="typeStr" name="typeStr" value="${parentInfo.typeStr}"/>
<input type="hidden" id="addedMbrIds" name="addedMbrIds" value="${addedMbrIds}"/>
<input type="hidden" id="chkedHMbrIds" name="chkedHMbrIds" value=""/>
<input type="hidden" id="TEMPLATE_ID" name="TEMPLATE_ID" value=""/>

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
      	<c:if test="${isLock eq 'false'}">
        <div class="page-header-actions">
        	<%--
        	<a class="append-rows btn btn-primary btn-outline clk_add_data" href="javascript:void(0)" 
        	data-crs-sn="${parentInfo.CRS_SN}" 
        	data-hmbr-sn="" 
        	data-typeStr="add"
        	data-linkurl="/eduadm/mbrhstry/write_excel.do">
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">새로운 수강생 추가하기(엑셀업로드)</span>
        	</a> 
        	--%>
        	<a class="append-rows btn btn-info clk_add_data" href="javascript:void(0)" 
        	data-crs-sn="${parentInfo.CRS_SN}" 
        	data-hmbr-sn="" 
        	data-typeStr="add"
        	data-linkurl="/eduadm/mbrhstry/write.do">
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">교육대상자 조회 및 수강등록</span>
        	</a> 
        	<a class="append-rows btn btn-warning btn-outline clk_addnew_data" href="javascript:void(0)" 
        	data-crs-sn="" 
        	data-type-str=""
        	data-linkurl="/eduadm/member/write.do">
        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
        		<span class="hidden-sm-down">교육대상자 등록하기</span>
        	</a> 
        </div>
        </c:if>
      </div>
      
      <div class="page-content container-fluid">
         <div class="row">
          <div class="col-xl-12">
          
          	<c:if test="${not empty parentInfo}">
				
				<c:if test="${isLock eq true}">
					<div role="alert" class="alert dark alert-warning alert-dismissible">
						<button aria-label="Close" data-dismiss="alert" class="close" type="button">
							<span aria-hidden="true">×</span>
						</button>
		      			<h4><i class="icon wb-bell" aria-hidden="true"></i> Notice</h4>
						<p>현재 수강생 등록은 불가능합니다.</p>
					</div>
				</c:if>
				<c:if test="${parentInfo.DEL_ST eq '1'}">
					<div role="alert" class="alert dark alert-danger alert-dismissible">
						<button aria-label="Close" data-dismiss="alert" class="close" type="button">
							<span aria-hidden="true">×</span>
						</button>
		      			<h4><i class="icon wb-bell" aria-hidden="true"></i> Notice</h4>
						<p>현재 삭제 된 상태의 교육과정입니다.</p>
					</div>
				</c:if>			
				
	          	<div class="panel">
	          		
	          		<fmt:parseDate value="${fn:replace(parentInfo.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
	           		<fmt:parseDate value="${fn:replace(parentInfo.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
	           		<fmt:parseDate value="${fn:replace(parentInfo.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
	           		<fmt:parseDate value="${fn:replace(parentInfo.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
	           		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) HH시 mm분" var="CRS_STR_DT"/>
	           		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH시 mm분" var="CRS_END_DT"/>
	           		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd (E) HH시 mm분" var="RECRUIT_STR_DT"/>
	           		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd (E) HH시 mm분" var="RECRUIT_END_DT"/>
	           		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy" var="CRS_STR_DT_YEAR"/>
	           		           	
					<input type="hidden" name="searchYear" value="${CRS_STR_DT_YEAR}" />	           		           	
	           		           	
					<!-- 교육분류에 따른 표기 처리 : start -->
            		<c:choose>
						<c:when test="${parentInfo.CRS_TYPE eq 'fshsk_trnng'}">
							<c:set var="label_crs_time" value="개월" />
						</c:when>
						<c:otherwise>
							<c:set var="label_crs_time" value="시간" />							
						</c:otherwise>											
					</c:choose>
					<!-- 교육분류에 따른 표기 처리 : end -->
					<c:choose>
						<c:when test="${parentInfo.TYPE_GB eq 'online'}">
							<c:set var="is_allow_online" value="1" />
						</c:when>
						<c:otherwise>
							<c:set var="is_allow_online" value="0" />
						</c:otherwise>											
					</c:choose>	           		           	
	           		           		
					<div class="panel-heading">
		    			<h3 class="panel-title">${parentInfo.CRS_NM}&nbsp;&nbsp;
		      				<small>${parentInfo.CAT_INS_NM}</small>
		      				<c:forEach var="item" items="${list_mbr_trgt_cd}">
								<c:if test="${item.CD_ID eq parentInfo.CRS_MBR_CD }"><span class="badge badge-outline badge-info">${item.CD_NM }</span></c:if>
							</c:forEach>	
		    			</h3>
		  			</div>
		  			<div class="panel-body">
		    			<p>
			    			교육기간 : ${CRS_STR_DT}&nbsp;~&nbsp;${CRS_END_DT}
			    			<br>
			    			모집기간 : ${RECRUIT_STR_DT}&nbsp;~&nbsp;${RECRUIT_END_DT}
			    			<br>
			    			<br>
			    			교육장소 : ${parentInfo.CRS_PLACE}
			    			<br>
			    			교육장주소 : ${parentInfo.CRS_ADDR}
						</p>
		  			</div>
		  		</div> 
	  			
	  			<div class="row">
	  				<div class="col-xl-3 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
	              				<div class="counter text-left">
           							<div class="counter-label">상태</div>
           							<div class="counter-number-group">
             							<span class="counter-number font-size-16">
             								<c:choose>
												<c:when test="${parentInfo.DEL_ST eq '1'}">
													<i class="icon wb-trash red-600" aria-hidden="true" data-toggle="tooltip" data-original-title="삭제"></i>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${parentInfo.CRS_ST eq '1'}"><span class="badge badge-outline badge-primary">승인완료</span></c:when>
														<c:otherwise><span class="badge badge-outline badge-dark">승인대기</span></c:otherwise>											
													</c:choose>
													<c:choose>
														<c:when test="${parentInfo.CRS_ST eq '0'}"><span class="badge badge-dark">신청대기중</span></c:when>
														<c:otherwise>
															<c:choose>
										                		<c:when test="${parentInfo.CRS_ST eq '2'}"><span class="badge badge-dark">신청받지않음</span></c:when>
										                		<c:otherwise>
										                			<c:choose>
												                		<c:when test="${parentInfo.LOCK_ST eq 0}"><span class="badge badge-primary">신청접수중</span></c:when>
												                		<c:otherwise><span class="badge badge-danger">신청마감</span></c:otherwise>				                	
												                	</c:choose>	
										                		</c:otherwise>				                	
										                	</c:choose>										
														</c:otherwise>
													</c:choose>
												</c:otherwise>											
											</c:choose>    
             							</span>
           							</div>
         						</div>
	            			</div>
	          			</div>
	        		</div>	        		
					<div class="col-xl-3 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
	              				<div class="counter text-left">
           							<div class="counter-label">제한인원(최대)</div>
           							<div class="counter-number-group">
            							<c:if test="${empty parentInfo.MBR_MAX_CNT || parentInfo.MBR_MAX_CNT == 0}">
            								<c:set var="label_mbr_max_cnt" value="(무제한)"/>
            							</c:if>
             							<span class="counter-number">${parentInfo.MBR_MAX_CNT}</span>
             							<span class="counter-number-related">명</span>
             							&nbsp;${label_mbr_max_cnt}
           							</div>
         						</div>
	            			</div>
	          			</div>
	        		</div>
	        		<div class="col-xl-3 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
	              				<div class="counter text-left">
           							<div class="counter-label">수강신청인원</div>
           							<div class="counter-number-group">
             							<span class="counter-number"><%-- ${parentInfo.MBR_CUR_CNT} --%>${paginationInfo.totalRecordCount}</span><%-- 이 부분은 임의로 수정 MBR_CUR_CNT 로직 살펴봐야 하나 여쭤보기 --%>
             							<span class="counter-number-related">명</span>
           							</div>
         						</div>
	            			</div>
	          			</div>
	        		</div>
	        		<div class="col-xl-3 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
	              				<div class="counter text-left">
           							<div class="counter-label">이수완료인원</div>
           							<div class="counter-number-group">
             							<span class="counter-number">${parentInfo.MBR_CMPLT_CNT}</span>
             							<span class="counter-number-related">명</span>
           							</div>
         						</div>
	            			</div>
	          			</div>
	        		</div>	 
	        	</div>
	  			
	  			<div class="row">
					<div class="col-xl-12 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
	              				<div class="counter text-left">
           							<div class="counter-label">총 교육시간</div>
           							<div class="counter-number-group">
             							<span class="counter-number">${parentInfo.CRS_TOT_TIME + parentInfo.SUM_TOT_TIME}</span>
             							<span class="counter-number-related">${label_crs_time}
             							<c:if test="${is_allow_online eq '1'}">
             							&nbsp;<span class="blue-grey-400 font-size-14">/ (오프라인) ${parentInfo.CRS_TOT_TIME} 시간 / (온라인) ${parentInfo.SUM_TOT_TIME} 시간</span>
             							</c:if>
             							</span>
           							</div>
         						</div>
	            			</div>
	          			</div>
	        		</div>	        
	        		<!-- 		
	        		<div class="col-xl-6 col-md-6 info-panel">
						<div class="card card-shadow">
							<div class="card-block bg-white p-20">
	              				<div class="counter text-left">
           							<div class="counter-label">총 교육점수</div>
           							<div class="counter-number-group">
             							<span class="counter-number">${parentInfo.CRS_TOT_POINT + parentInfo.SUM_TOT_POINT}</span>
             							<span class="counter-number-related">점
             							<c:if test="${is_allow_online eq '1'}">
             							&nbsp;<span class="blue-grey-400 font-size-14">/ (오프라인) ${parentInfo.CRS_TOT_POINT} 점 / (온라인) ${parentInfo.SUM_TOT_POINT} 점</span>
             							</c:if>
             							</span>
           							</div>
         						</div>
	            			</div>
	          			</div>
	        		</div>
	        		 -->
	        	</div>
		        		
			</c:if>         
          
          	<!-- 검색폼 -->
          	<input type="hidden" name="searchUseYn" value="Y" />
          	<div class="panel mb-20">
          		<div class="panel-heading">
	    			<h3 class="panel-title">수강생검색&nbsp;&nbsp;
	      				&nbsp;<small>공통조건</small>
	    			</h3>
	  			</div>
	  			<div class="panel-body">
					<div class="input-group col-lg-12 p-0">
						<div class="btn-group col-lg-2 p-0">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_sido" name="HMBR_SIDO_CD" <c:if test="${not empty LoginVO.MBR_SIDO_CD}">disabled</c:if> >
								<option value="" >시도 전체</option>
								<c:forEach var="item" items="${list_address_cd}">
									<option value="${item.CD_ID}" <c:if test="${item.CD_ID eq HMBR_SIDO_CD }">selected</c:if> >${item.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_signgu" name="HMBR_SIGNGU_CD" <c:if test="${not empty LoginVO.MBR_SIGNGU_CD}">disabled</c:if> >
								<option value="" >시군구 전체</option>
								<c:forEach var="item" items="${list_address_signgu_cd}">
									<option value="${item.CD_ID}" <c:if test="${item.CD_ID eq HMBR_SIGNGU_CD }">selected</c:if> >${item.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="HMBR_YMD_NM" placeholder="읍면동" autocomplete="off" value="${HMBR_YMD_NM}" data-toggle="tooltip" data-original-title="읍면동을 입력하세요.">
						</div>
					</div>
					<div class="input-group col-lg-12 p-0 mt-10">
						<div class="btn-group col-lg-2 p-0">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="HMBR_MBR_TRGT_CD" <c:if test="${not empty LoginVO.MBR_TRGT_CD}">disabled</c:if> >
								<option value="" >교육대상(업종)전체</option>
								<c:forEach var="item" items="${list_mbr_trgt_cd}">
									<option value="${item.CD_ID}" <c:if test="${item.CD_ID eq HMBR_MBR_TRGT_CD }">selected</c:if> >${item.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual " data-style="btn-outline btn-default" name="">
	    						<option value="" <c:if test="${empty HMBR_FSHRBT_TYPE}">selected</c:if> >전체</option>
	    						<option value="legacy" <c:if test="${HMBR_FSHRBT_TYPE eq 'legacy'}">selected</c:if>>기존</option>
	    						<option value="new" <c:if test="${HMBR_FSHRBT_TYPE eq 'new'}">selected</c:if>>신규</option>
	    						<option value="resmpt" <c:if test="${HMBR_FSHRBT_TYPE eq 'resmpt'}">selected</c:if>>재개자</option>
	    					</select>
		      			</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual " data-style="btn btn-success text-white" name="LRNNG_ST">
	    						<option value="" <c:if test="${empty fs_LRNNG_ST}">selected</c:if>>신청상태</option>
	    						<option value="0" <c:if test="${fs_LRNNG_ST eq '0'}">selected</c:if>>대기</option>
	    						<option value="1" <c:if test="${fs_LRNNG_ST eq '1'}">selected</c:if>>승인 (이수증외부출력가능)</option>
	    						<option value="2" <c:if test="${fs_LRNNG_ST eq '2' or fs_LRNNG_ST eq '3'}">selected</c:if>>취소</option>
	    						<option value="4" <c:if test="${fs_LRNNG_ST eq '4'}">selected</c:if>>보류</option>
	    					</select>
		      			</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual " data-style="btn btn-info text-white" name="LRNNG_CMPLT_ST">
	    						<option value="" <c:if test="${empty fs_LRNNG_CMPLT_ST}">selected</c:if>>이수상태</option>
	    						<option value="0" <c:if test="${fs_LRNNG_CMPLT_ST eq '0'}">selected</c:if>>수강중</option>
	    						<option value="1" <c:if test="${fs_LRNNG_CMPLT_ST eq '1'}">selected</c:if>>이수완료 (이수증발급완료)</option>
	    						<option value="2" <c:if test="${fs_LRNNG_CMPLT_ST eq '2'}">selected</c:if>>이수취소</option>
	    					</select>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual " data-style="btn-outline btn-default" name="REG_MBR_ID_ST">
	    						<option value="" <c:if test="${empty REG_MBR_ID_ST}">selected</c:if>>등록방식</option>
	    						<option value="0" <c:if test="${REG_MBR_ID_ST eq '0'}">selected</c:if>>직접등록</option>
	    						<option value="1" <c:if test="${REG_MBR_ID_ST eq '1'}">selected</c:if>>관리자등록</option>
	    					</select>
						</div>						
					</div>
					<div class="input-group col-lg-12 p-0 mt-10">
						<div class="col-lg-2 p-0 ">
							<input type="text" class="form-control input-auto-enter-key" name="MBR_NM" placeholder="이름,닉네임" autocomplete="off" value="${MBR_NM}" data-toggle="tooltip" data-original-title="이름을 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key mbr-birth-input-pattern-frm" data-pattern-cnt="0" name="MBR_BIRTH" placeholder="생년월일" autocomplete="off" value="${MBR_BIRTH}" data-toggle="tooltip" data-original-title="생년월일을 입력하세요." >
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="MBR_ADDR_MERGE" placeholder="주소" autocomplete="off" value="${MBR_ADDR_MERGE}" data-toggle="tooltip" data-original-title="주소를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key mbr-tel-input-pattern-frm" data-pattern-cnt="0" name="MBR_TEL" placeholder="연락처(일반)" autocomplete="off" value="${MBR_TEL}" data-toggle="tooltip" data-original-title="연락처(일반전화)를 입력하세요." >
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key mbr-hp-input-pattern-frm" data-pattern-cnt="0" name="MBR_HP" placeholder="연락처(휴대폰)" autocomplete="off" value="${MBR_HP}" data-toggle="tooltip" data-original-title="연락처(휴대전화)를 입력하세요." >							
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_REG_TYPE_CD" <c:if test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">disabled</c:if> >
								<option value="" >관리주체선택</option>
								<option value="1" <c:if test="${MBR_REG_TYPE_CD eq '1'}">selected</c:if> >공단 등록건</option>
								<option value="PCD0003" <c:if test="${MBR_REG_TYPE_CD eq 'PCD0003'}">selected</c:if> >지자체 등록건</option>        			
		      				</select>		
						</div>
					</div>
					<div class="input-group col-lg-12 p-0 mt-10">
						<div class="col-lg-2 p-0 ">
							<input type="text" class="form-control input-auto-enter-key" name="HMBR_DTL_NM" placeholder="낚시터/어선명" autocomplete="off" value="${HMBR_DTL_NM}" data-toggle="tooltip" data-original-title="낚시터 또는 어선명을 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key reg-num-cd-input-pattern-frm" data-pattern-cnt="0" name="HMBR_REG_NUM_CD" placeholder="허가(등록)번호/신고번호" autocomplete="off" value="${HMBR_REG_NUM_CD}" data-toggle="tooltip" data-original-title="낚시터는 허가(등록)번호,낚시어선은 신고번호를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key business-num-input-pattern-frm" data-pattern-cnt="0" name="HMBR_BUSINESS_NUM" placeholder="사업자번호" autocomplete="off" value="${HMBR_BUSINESS_NUM}" data-toggle="tooltip" data-original-title="사업자번호를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<div class="input-group">
								<input type="text" class="form-control datepickerModalStrSearch " id="HMBR_SHIP_LICENSE_STR_DT" name="HMBR_SHIP_LICENSE_STR_DT" placeholder="유효시작일자" autocomplete="off" value="${HMBR_SHIP_LICENSE_STR_DT}" data-toggle="tooltip" data-original-title="시작일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="HMBR_SHIP_LICENSE_STR_DT" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<div class="input-group">
								<input type="text" class="form-control datepickerModalEndSearch " id="HMBR_SHIP_LICENSE_END_DT" name="HMBR_SHIP_LICENSE_END_DT" placeholder="유효만료일자" autocomplete="off" value="${HMBR_SHIP_LICENSE_END_DT}" data-toggle="tooltip" data-original-title="만료일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="HMBR_SHIP_LICENSE_END_DT" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="HMBR_DTL_LICENSE_CD" >
								<option value="" >대상구분 전체보기</option>
								<c:forEach var="item_category" items="${list_license_se_cd}">
									<%-- <c:if test="${item_category.CD_ID ne 'CIDL00001' and item_category.CD_ID ne 'CIDL00002'}"> --%>
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq HMBR_DTL_LICENSE_CD }">selected</c:if> >${item_category.CD_NM}</option>
									<%-- </c:if> --%>
								</c:forEach>	
		      				</select>	
						</div>
					</div>
					<hr><h6>낚시터 추가 조건</h6>
					<div class="input-group col-lg-12 p-0 mt-10 /*hidden*/ trg_search_store_detail_layer">
						<div class="col-lg-2 p-0">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="HMBR_FSHLC_WORK_CD" >
								<option value="" >업구분</option>   
								<c:forEach var="item_category" items="${list_fshlc_work_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq HMBR_FSHLC_WORK_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach> 			
		      				</select>	
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="HMBR_DTL_ADDR" placeholder="소재지(주소)" autocomplete="off" value="${HMBR_DTL_ADDR}" data-toggle="tooltip" data-original-title="소재지(구:낚시위치)를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="HMBR_FSHLC_APPLC" placeholder="적용수면" autocomplete="off" value="${HMBR_FSHLC_APPLC}" data-toggle="tooltip" data-original-title="적용수면을 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							
						</div>
						<div class="col-lg-2 p-0 pl-10">
							
						</div>
						<div class="col-lg-2 p-0 pl-10">
							
						</div>
					</div>
					<hr><h6>낚시어선 추가 조건</h6>
					<div class="input-group col-lg-12 p-0 mt-10 /*hidden*/ trg_search_ship_detail_layer">
						<div class="col-lg-2 p-0">
							<input type="text" class="form-control input-auto-enter-key ship-cd-input-pattern-frm" data-pattern-cnt="0" name="HMBR_SHIP_CD" placeholder="어선번호" autocomplete="off" value="${HMBR_SHIP_CD}" data-toggle="tooltip" data-original-title="어선번호를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="HMBR_SHIP_GRTG" placeholder="총톤수" autocomplete="off" value="${HMBR_SHIP_GRTG}" data-toggle="tooltip" data-original-title="어선의 총톤수를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="HMBR_SHIP_PRLOAD" placeholder="선적항" autocomplete="off" value="${HMBR_SHIP_PRLOAD}" data-toggle="tooltip" data-original-title="어선의 선적항을 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="HMBR_SHIP_MAX_PSNGER" placeholder="최대승객" autocomplete="off" value="${HMBR_SHIP_MAX_PSNGER}" data-toggle="tooltip" data-original-title="어선의 최대승객수를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="HMBR_SHIP_MAX_CREW" placeholder="최대선원" autocomplete="off" value="${HMBR_SHIP_MAX_CREW}" data-toggle="tooltip" data-original-title="어선의 최대선원수를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="HMBR_SHIP_LICENSE">
								<option value="" >해기사면허 유무</option>    
								<option value="유" <c:if test="${HMBR_SHIP_LICENSE eq '유'}">selected</c:if> >유</option>
								<option value="무" <c:if test="${HMBR_SHIP_LICENSE eq '무'}">selected</c:if> >무</option>      			
		      				</select>
						</div>
					</div>
					<hr>
					<div class="input-group col-lg-12 p-0 mt-30">
						<div class="col-lg-6 p-0">
							<!-- 
							<button type="button" class="btn btn-outline btn-warning clk_search_store_detail mr-5"><i class="icon wb-plus" aria-hidden="true"></i>낚시터 추가조건</button>
							<button type="button" class="btn btn-outline btn-info clk_search_ship_detail"><i class="icon wb-plus" aria-hidden="true"></i>낚시어선 추가조건</button> 
							-->
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn btn-outline btn-default" name="searchOrderBy">
	    						<option value="" <c:if test="${empty searchOrderBy}">selected</c:if>>신청순</option>
	    						<option value="1" <c:if test="${searchOrderBy eq '1'}">selected</c:if>>이수순</option>
	    						<option value="2" <c:if test="${searchOrderBy eq '2'}">selected</c:if>>이름순</option>
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
							<input type="hidden" class="form-control" name="searchKeyword" placeholder="이름,연락처,아이디를 입력하세요." autocomplete="off" value="${searchKeyword}">
							<button type="button" class="btn btn-primary clk_search_btn w-p100"><i class="icon wb-search" aria-hidden="true"></i></button>
						</div>
					</div>
										
	  			</div>
			</div>
			<!-- End 검색폼 --> 
          
            <!-- Panel Editing Rows -->
            <div class="panel">
              <div class="panel-body">
              		<!-- table:checkbox -->
              		<div class="row">
              			<div class="col-lg-12 text-right">
              				<c:choose><%-- 권한분류 --%>
								<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
									<div class="float-left">
				             			<a class="btn btn-outline btn-success btn-act-print" href="javascript:void(0);" 
				             					data-toggle="tooltip" data-original-title="이수증외부출력가능여부와 관계없이 관리자는 선택 한 수강생(이수증발급완료 된) 모두를 출력 할 수 있습니다.">
							        		<i class="site-menu-icon wb-print" aria-hidden="true"></i>
							        		<span class="hidden-sm-down">이수증 프린트출력(관리자)</span>
							        	</a>
							        	<a class="btn btn-outline btn-success btn-attendance-book-act-print" href="javascript:void(0);" 
				             					data-toggle="tooltip" data-original-title="현재 교육과정의 모든 수강생들의 출석부를 출력 할 수 있습니다.">
							        		<i class="site-menu-icon wb-print" aria-hidden="true"></i>
							        		<span class="hidden-sm-down">출석부 프린트출력(관리자)</span>
							        	</a>
						        	</div>
			             			<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down w300px" href="javascript:void(0);" 
			             				data-excel-type="/eduadm/mbrhstry/list.do"
						        		data-excel-label="${fn:trim(parentInfo.CRS_NM)}_{{LABEL}}수강자목록전체_엑셀다운로드">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">(총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드</span>
						        	</a>
						        	<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down w300px" href="javascript:void(0);"
			             				data-excel-type="/eduadm/mbrhstry/list_prev.do"
						        		data-excel-label="${fn:trim(parentInfo.CRS_NM)}_{{LABEL}}수강자목록전체(구버전)_엑셀다운로드">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">(총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드(구버전)</span>
						        	</a> 
								</c:when>
								<%-- 해양경찰서 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
								
								</c:when>
								<%-- 지자체 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
									
								</c:when>
								<%-- 교육기관 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
									<div class="float-left">
				             			<a class="btn btn-outline btn-success btn-act-print" href="javascript:void(0);" 
				             					data-toggle="tooltip" data-original-title="이수증외부출력가능여부와 관계없이 관리자는 선택 한 수강생(이수증발급완료 된) 모두를 출력 할 수 있습니다.">
							        		<i class="site-menu-icon wb-print" aria-hidden="true"></i>
							        		<span class="hidden-sm-down">이수증 프린트출력(관리자)</span>
							        	</a>
							        	<a class="btn btn-outline btn-success btn-attendance-book-act-print" href="javascript:void(0);" 
				             					data-toggle="tooltip" data-original-title="현재 교육과정의 모든 수강생들의 출석부를 출력 할 수 있습니다.">
							        		<i class="site-menu-icon wb-print" aria-hidden="true"></i>
							        		<span class="hidden-sm-down">출석부 프린트출력(관리자)</span>
							        	</a>
						        	</div>
								</c:when>
								<%-- 기타 거부 --%>
								<c:otherwise>
								
								</c:otherwise>
							</c:choose><%-- End 권한분류 --%>  
				        </div>
				  </div>     
				  <div class="row mt-10">
				  	<c:choose>
				  		<c:when test="${is_allow_online eq '1'}">
				  			<c:choose><%-- 권한분류 --%>
								<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
									<div class="col-lg-4">
										<button type="button" class="btn btn-outline-secondary w150px" onclick="survey_view();">설문조사 확인</button>
									</div>
				  					<div class="col-lg-8 text-right">
								</c:when>
								<%-- 해양경찰서 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
									<div class="col-lg-12 text-right">
								</c:when>
								<%-- 지자체 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
									<div class="col-lg-12 text-right">
								</c:when>
								<%-- 교육기관 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
									<div class="col-lg-12 text-right">
								</c:when>
								<%-- 기타 거부 --%>
								<c:otherwise>
									<div class="col-lg-12 text-right">
								</c:otherwise>
							</c:choose><%-- End 권한분류 --%>
				  		</c:when>
				  		<c:otherwise><div class="col-lg-12 text-right"></c:otherwise>
				  	</c:choose>
					  	<a class="btn btn-default btn-all-non-mbr-clear" href="javascript:void(0);">
							<i class="site-menu-icon wb-trash" aria-hidden="true"></i>
							<span class="hidden-sm-down">삭제된 회원(비회원) 일괄정리</span>
						</a>	
              			    <c:choose><%-- 권한분류 --%>
								<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
									<!-- <a class="btn btn-danger btn-act-send-sms w150px" href="javascript:void(0);">
						        		<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">문자보내기</span>
						        	</a> -->
						        	<div class="btn-group">
										<button class="btn btn-danger dropdown-toggle w250px" type="button" id="dropdownMenuButton3" data-toggle="dropdown" aria-expanded="false">
											<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>문자보내기
										</button>
										<ul class="dropdown-menu w250px" aria-labelledby="dropdownMenuButton3" style="left:-90px !important;">
											<li><a class="dropdown-item btn-act-send-sms" href="javascript:void(0);">문자보내기</a></li>
											<li><a class="dropdown-item btn-act-send-certificate-sms" href="javascript:void(0);">이수증 이미지 문자보내기</a></li>
										</ul>
									</div>
									<c:choose>
										<c:when test="${is_allow_online eq '1'}">
											<c:if test="${parentInfo.CRS_YEAR eq '2022'}">
									        	<div class="btn-group">
													<button class="btn btn-warning dropdown-toggle w250px" type="button" id="dropdownMenuButton1" data-toggle="dropdown" aria-expanded="false">
														<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>전체인원 알림톡 보내기
													</button>
													<ul class="dropdown-menu w250px" aria-labelledby="dropdownMenuButton1" style="left:-90px !important;">
														<li><a class="dropdown-item btn-act-all-view-kakao" href="javascript:void(0);" data-template-id="KKO_000001" data-totalCnt="${paginationInfo.totalRecordCount}">개별 동영상 수강링크 발송</a></li>
														<li><a class="dropdown-item btn-act-all-view-kakao" href="javascript:void(0);" data-template-id="KKO_000008" data-totalCnt="${paginationInfo.totalRecordCount}">신청자 중 강의 미이수자 독려</a></li>
														<li><a class="dropdown-item btn-act-all-view-kakao" href="javascript:void(0);" data-template-id="KKO_000011" data-totalCnt="${paginationInfo.totalRecordCount}">신청자 중 설문조사 미완료자 독려</a></li>
														<li><a class="dropdown-item btn-act-all-view-kakao" href="javascript:void(0);" data-template-id="KKO_000013" data-totalCnt="${paginationInfo.totalRecordCount}">12월 31일 미이수자 독려</a></li>
														<li><a class="dropdown-item btn-act-all-view-kakao" href="javascript:void(0);" data-template-id="KKO_000017" data-totalCnt="${paginationInfo.totalRecordCount}">12월 31일 설문조사 미완료자 독려</a></li>
													</ul>
												</div>
											</c:if>
											<c:if test="${parentInfo.CRS_YEAR eq '2023'}">
												<div class="btn-group">
													<button class="btn btn-warning dropdown-toggle w250px" type="button" id="dropdownMenuButton1" data-toggle="dropdown" aria-expanded="false">
														<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>전체인원 알림톡 보내기
													</button>
													<ul class="dropdown-menu w250px" aria-labelledby="dropdownMenuButton1" style="left:-90px !important;">
														<li><a class="dropdown-item btn-act-all-view-kakao" href="javascript:void(0);" data-template-id="KKO_000019" data-totalCnt="${paginationInfo.totalRecordCount}">개별 동영상 수강링크 발송</a></li>
														<li><a class="dropdown-item btn-act-all-view-kakao" href="javascript:void(0);" data-template-id="KKO_000022" data-totalCnt="${paginationInfo.totalRecordCount}">신청자 중 강의 미이수자 독려</a></li>
														<li><a class="dropdown-item btn-act-all-view-kakao" href="javascript:void(0);" data-template-id="KKO_000024" data-totalCnt="${paginationInfo.totalRecordCount}">신청자 중 설문조사 미완료자 독려</a></li>
													</ul>
												</div>
											</c:if>
										</c:when>
										<c:otherwise>
										
										</c:otherwise>
									</c:choose>
						        	
								</c:when>
								<%-- 해양경찰서 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
									
								</c:when>
								<%-- 지자체 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
								
								</c:when>
								<%-- 교육기관 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
								
								</c:when>
								<%-- 기타 거부 --%>
								<c:otherwise>
								
								</c:otherwise>
							</c:choose><%-- End 권한분류 --%>
				        </div>
              		</div>
              		<div class="row mt-10 ">
              			<div class="col-lg-12 text-left ">
	             			<button type="button" class="btn btn-outline btn-info btn-act-all-modify" data-linkUrl="/eduadm/mbrhstry/modify_all_cmplt_act.do"
	             				data-toggle="tooltip" data-original-title="이 버튼을 눌러 이수증을 발급하면 관리자는 이수증을 출력 할 수 있습니다.">
				        		<i class="site-menu-icon wb-check-circle" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">가이수처리(이수증발급가능)</span>
				        	</button>
				        	<button type="button" class="btn btn-outline btn-warning btn-act-all-modify" data-linkUrl="/eduadm/mbrhstry/modify_all_cmplt_cancel_act.do"
				        		data-toggle="tooltip" data-original-title="이 버튼은 이미 발급 된 이수증을 회수(제거)합니다.">
				        		<i class="site-menu-icon wb-check-circle" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">가이수취소처리(이수증발급불가)</span>
				        	</button>
				        	<div class="float-right">
					        	<button type="button" class="btn btn-outline btn-success btn-act-all-modify w150px" data-linkUrl="/eduadm/mbrhstry/modify_all_st_act.do"
					        		data-toggle="tooltip" data-original-title="이 버튼은 교육대상자가 외부에서 출력 할 수 있도록 승인합니다. 교육이 종료 된 후 처리해주세요.">
					        		<i class="site-menu-icon wb-check-circle" aria-hidden="true"></i>
					        		<span class="hidden-sm-down">이수완료처리</span>
					        	</button>
					        	<c:choose>
									<c:when test="${is_allow_online eq '1'}">
										<c:if test="${parentInfo.CRS_YEAR eq '2022'}">
											<div class="btn-group">
												<button class="btn btn-warning dropdown-toggle w250px" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-expanded="false">
													<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>알림톡 보내기
												</button>
												<ul class="dropdown-menu w250px" aria-labelledby="dropdownMenuButton2" style="left:-90px !important;">
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000001" href="javascript:void(0);">개별 동영상 수강링크 발송</a></li>
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000008" href="javascript:void(0);">신청자 중 강의 미이수자 독려</a></li>
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000011" href="javascript:void(0);">신청자 중 설문조사 미완료자 독려</a></li>
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000013" href="javascript:void(0);">12월31일 미이수자 독려</a></li>
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000017" href="javascript:void(0);">12월 31일 설문조사 미완료자 독려</a></li>
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000005" href="javascript:void(0);">이수증 알림톡 재전송</a></li>
												</ul>
											</div>
										</c:if>
										<c:if test="${parentInfo.CRS_YEAR eq '2023'}">
											<div class="btn-group">
												<button class="btn btn-warning dropdown-toggle w250px" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-expanded="false">
													<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>알림톡 보내기
												</button>
												<ul class="dropdown-menu w250px" aria-labelledby="dropdownMenuButton2" style="left:-90px !important;">
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000019" href="javascript:void(0);">개별 동영상 수강링크 발송</a></li>
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000022" href="javascript:void(0);">신청자 중 강의 미이수자 독려</a></li>
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000024" href="javascript:void(0);">신청자 중 설문조사 미완료자 독려</a></li>
													<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000005" href="javascript:void(0);">이수증 알림톡 재전송</a></li>
												</ul>
											</div>
										</c:if>
									</c:when>
									<c:otherwise>
										<div class="btn-group">
											<button class="btn btn-warning dropdown-toggle w250px" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-expanded="false">
												<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>알림톡 보내기
											</button>
											<ul class="dropdown-menu w250px" aria-labelledby="dropdownMenuButton2" style="left:-90px !important;">
												<li><a class="dropdown-item btn-act-view-kakao" data-template-id="KKO_000005" href="javascript:void(0);">이수증 알림톡 재전송</a></li>
											</ul>
										</div>
									</c:otherwise>
								</c:choose>
				        	</div>
				        </div>
	              	</div>
              		<!-- End table:checkbox -->
              		<div class="example-tooltip">
						<div class="tooltip bs-tooltip-top tooltip-info" role="tooltip">
							<div class="arrow"></div>
							<div class="tooltip-inner">전체 ${paginationInfo.totalRecordCount} 건</div>
						</div>
					</div>
					<!-- table:start -->
	      			<table id="datalist" class="table footable footable-paging footable-paging-center ">
	          		<colgroup>
	          			<col width="40px"/>
	          		</colgroup>
	          		<thead>
	          			<c:choose>
							<c:when test="${is_allow_online eq '1'}">
								<tr>
									<th class="text-middle text-center hide-cell-exceldown">
										<span class="checkbox-custom checkbox-primary">
		                              		<input class="selectable-item btn-check-item-all" type="checkbox" data-status=""><label></label>
		                            	</span>
									</th>
									<th class="text-middle text-center">No</th>
			          				<th class="text-middle text-center">이름</th>
			          				<th class="text-middle text-center">생년월일</th>
			          				<th class="text-middle text-center">연락처</th>
									<th class="text-middle text-center hide-cell-exceldown">이수상태</th>
									<th class="text-middle text-center hide-cell-exceldown">이수일자<br>(가이수일자)</th>
									<!-- <th class="text-middle text-center">결제상태</th>  -->
									<th class="text-middle text-center hide-cell-exceldown">신청자</th>
									<!-- 
									<th class="text-middle text-center hide-cell-exceldown">현재상태<br>(종합)</th>							
									<th class="text-middle text-center hide-cell-exceldown">현재상태<br>(오프라인)</th>
									<th class="text-middle text-center hide-cell-exceldown">현재상태<br>(온라인)</th>
									 -->
									<th class="text-middle text-center hide-cell-exceldown">온라인 이수완료율<br>(학습진행률)</th>
									<th class="text-middle text-center">메모<br><span class="font-size-11 blue-grey-400">(입력 후 3초뒤 자동저장)</span></th>
				                	<th class="text-middle text-center hide-cell-exceldown">관리</th>
			          			</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<th class="text-middle text-center hide-cell-exceldown">
										<span class="checkbox-custom checkbox-primary">
		                              		<input class="selectable-item btn-check-item-all" type="checkbox" data-status=""><label></label>
		                            	</span>
									</th>
									<th class="text-middle text-center">No</th>
									<th class="text-middle text-center">이름</th>
									<th class="text-middle text-center">생년월일</th>
									<th class="text-middle text-center">연락처</th>
									<th class="text-middle text-center hide-cell-exceldown">이수상태</th>
									<th class="text-middle text-center hide-cell-exceldown">이수일자<br>(가이수일자)</th>
									<!--  <th class="text-middle text-center">결제상태</th>  -->
									<!-- <th class="text-middle text-center hide-cell-exceldown">현재상태<br>(종합)</th> -->
									<th class="text-middle text-center hide-cell-exceldown">신청자</th>
									<th class="text-middle text-center">메모<br><span class="font-size-11 blue-grey-400">(입력 후 3초뒤 자동저장)</span></th>		
				                	<th class="text-middle text-center hide-cell-exceldown">관리</th>
			                	</tr>
							</c:otherwise>											
						</c:choose>        				
	             	</thead>
					<tbody>
						<c:choose>
							<c:when test="${is_allow_online eq '1'}">
								<c:set var="table_cell_cnt" value="11"/>
							</c:when>
							<c:otherwise>
								<c:set var="table_cell_cnt" value="10"/>
							</c:otherwise>											
						</c:choose>	  
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">현재 수강신청한 인원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle hide-cell-exceldown">
			            			<span class="checkbox-custom checkbox-primary">
	                              		<input class="selectable-item btn-check-item" type="checkbox" name="checkbox_item" value="${item.HMBR_SN}" data-sms-id="${item.MBR_ID }"data-lrnng-st="${item.LRNNG_ST }"data-lrnng-cmplt-st="${item.LRNNG_CMPLT_ST }"><label></label>
	                            	</span>
                            	</td>
                            	<td class="text-middle text-center">${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}</td>
				                <td class="text-middle text-center">
				                	<c:choose>
										<c:when test="${empty item.MBR_NM}">
											(비회원) ${item.TMP_MBR_NM}
										</c:when>
										<c:when test="${item.DEL_ST eq '1'}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.USE_ST ne '1'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
					                ${item.MBR_NM}
					                <br><span class="grey-400">${item.MBR_NCNM}</span>
				                </td>
				                <td class="text-middle text-center">
					                <c:choose>
					           			<c:when test="${empty item.MBR_BIRTH}">
					           				<c:set var="mbrbirthstring" value="${item.TMP_MBR_BIRTH}" />
					           			</c:when>
					           			<c:otherwise>
					           				<c:set var="mbrbirthstring" value="${item.MBR_BIRTH}" />
					           			</c:otherwise>
					           		</c:choose>
					           		<c:catch var="signalException1">
					           			<fmt:parseDate var="parsembrbirthstring" value="${mbrbirthstring}" pattern="yyyyMMdd" />
					           			<fmt:formatDate var="birthString" value="${parsembrbirthstring}" pattern="yyyy년 MM월 dd일" />
									</c:catch>
									<c:choose>
										<c:when test="${signalException1 != null}">확인불가</c:when>
										<c:otherwise>${birthString}</c:otherwise>
									</c:choose>
					           	</td>
					           	<td class="text-middle text-center">${pfpu:phoneHypen(item.MBR_HP)}</td>
				                <!-- <td class="text-middle text-center hide-cell-exceldown">
				                	<c:choose>
				                		<c:when test="${item.LRNNG_ST eq 1}"><span class="badge badge-outline badge-dark font-size-12">승인</span>&nbsp;<span class="badge badge-outline badge-success font-size-12">이수증외부출력가능</span></c:when>
				                		<c:when test="${item.LRNNG_ST eq 2 or item.LRNNG_ST eq 3}"><span class="badge badge-outline badge-dark font-size-12">취소</span>&nbsp;<span class="badge badge-outline badge-danger font-size-12">이수증외부출력불가</span></c:when>
				                		<c:when test="${item.LRNNG_ST eq 4}"><span class="badge badge-outline badge-dark font-size-12">보류</span>&nbsp;<span class="badge badge-outline badge-danger font-size-12">이수증외부출력불가</span></c:when>
				                		<c:otherwise><span class="badge badge-outline badge-dark font-size-12">대기</span>&nbsp;<span class="badge badge-outline badge-danger font-size-12">이수증외부출력불가</span></c:otherwise>				                	
				                	</c:choose>
				                </td> -->
				                <!-- 
				                <td class="text-middle text-center">
				                	<c:choose>
				                		<c:when test="${item.PURCHASE_CMPLT_ST eq 1}">결제완료</c:when>
				                		<c:when test="${item.PURCHASE_CMPLT_ST eq 2}">결제중</c:when>
				                		<c:otherwise>결제대기</c:otherwise>				                	
				                	</c:choose>
				                </td>
				                 -->			                
		                 		<!-- <td class="text-middle text-center hide-cell-exceldown">		                 			
				                	<c:choose>
				                		<c:when test="${item.LRNNG_CMPLT_ST eq 1}"><span class="badge badge-outline badge-dark font-size-12">이수완료</span>&nbsp;<span class="badge badge-info font-size-14">이수증발급완료</span></c:when>
				                		<c:when test="${item.LRNNG_CMPLT_ST eq 2}"><span class="badge badge-outline badge-dark font-size-12">이수취소</span></c:when>
				                		<c:otherwise><span class="badge badge-outline badge-dark font-size-12">수강중</span></c:otherwise>				                	
				                	</c:choose>
				                </td> -->
				                <td class="text-middle text-center hide-cell-exceldown">		                 			
				                <!-- LRNNG_ST		==> 0 : 대기, 1 : 승인, 2 : 취소, 3 : 강제취소, 4 : 보류 -->
				                <!-- LRNNG_CMPLT_ST ==> 0 : 이수전, 1 : 이수완료, 2 : 이수취소 -->
				                	<c:choose>
				                		<c:when test="${(item.LRNNG_ST eq 0 && item.LRNNG_CMPLT_ST eq 0) || (item.LRNNG_ST eq 1 && item.LRNNG_CMPLT_ST eq 0)}"><span class="badge badge-outline badge-dark font-size-12">대기</span></c:when>
				                		<c:when test="${item.LRNNG_ST eq 0 && item.LRNNG_CMPLT_ST eq 1}"><span class="badge badge-outline badge-info font-size-12">가이수</span></c:when>        	
				                		<c:when test="${item.LRNNG_ST eq 1 && item.LRNNG_CMPLT_ST eq 1}"><span class="badge badge-outline badge-success font-size-12">이수완료</span></c:when>
				                		<c:when test="${item.LRNNG_ST eq 2 || item.LRNNG_ST eq 3}"><span class="badge badge-outline badge-danger font-size-12"">취소</span></c:when>
				                		<c:when test="${item.LRNNG_ST eq 4}"><span class="badge badge-outline badge-warning font-size-12">보류</span></c:when>
				                		<c:otherwise><span class="badge badge-outline badge-danger font-size-12">이수취소</span></c:otherwise>
				                	</c:choose>
				                </td>
				                <!-- 설문조사 -->
				                <td class="text-middle text-center hide-cell-exceldown">
		                			<fmt:parseDate value="${fn:replace(item.LRNNG_CMPLT_DT, '.0', '')}" var="parse_lrnng_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
		                			<fmt:parseDate value="${fn:replace(item.TMPR_LRNNG_CMPLT_DT, '.0', '')}" var="parse_tmpr_lrnng_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
				           			<fmt:formatDate value="${parse_lrnng_dt}" pattern="yyyy-MM-dd HH:mm:ss" var="LRNNG_CMPLT_DT"/>
				           			<fmt:formatDate value="${parse_tmpr_lrnng_dt}" pattern="yyyy-MM-dd HH:mm:ss" var="TMPR_LRNNG_CMPLT_DT"/>
				           			<c:if test="${item.LRNNG_ST eq 0 && item.LRNNG_CMPLT_ST eq 1}">
				           			-
				           			<br>
				           			<c:out value="(${TMPR_LRNNG_CMPLT_DT})"/><!-- 가이수일자 -->
				           			</c:if>
				           			<c:if test="${item.LRNNG_ST eq 1 && item.LRNNG_CMPLT_ST eq 1}">
				           			${LRNNG_CMPLT_DT}
				           			<br>
				           			<c:out value="(${TMPR_LRNNG_CMPLT_DT})"/><!-- 가이수일자 -->
				           			</c:if>		
				                </td>
				                <td class="text-middle text-center hide-cell-exceldown">
				                	<c:choose>
				                		<c:when test="${item.REG_MBR_ID eq item.MBR_ID}"><span class="font-size-12">직접</span></c:when>
				                		<c:otherwise><span class="font-size-12">${item.REG_MBR_ID}</span></c:otherwise>				                	
				                	</c:choose>				                	
				                </td>
			                <c:if test="${is_allow_online eq '1'}">
			                	<%--
			                	<td class="text-middle text-center hide-cell-exceldown">
				                	<span class="badge badge-outline badge-dark">${item.HMBR_RCGNT_TIME} ${label_crs_time}</span>
				                	<!-- <br><span class="badge badge-outline badge-dark">${item.HMBR_RCGNT_POINT} 점</span> -->
				                </td>
			                	<td class="text-middle text-center hide-cell-exceldown">
				                	<span class="badge badge-outline badge-dark">${item.HMBR_INPUT_TIME} ${label_crs_time}</span>
				                	<br><span class="badge badge-outline badge-dark">${item.HMBR_INPUT_POINT} 점</span>
				                </td>
				                <td class="text-middle text-center hide-cell-exceldown">
				                	<span class="badge badge-outline badge-dark">${item.LRNNG_TOT_TIME} 시간</span>
				                	<br><span class="badge badge-outline badge-dark">${item.LRNNG_TOT_POINT} 점</span>
				                </td>
				                 --%>
				                <td class="text-middle text-center hide-cell-exceldown">
				                	<c:if test="${item.HMBR_DTL_CNT > 0}">
				                		<fmt:formatNumber type="percent" value="${item.LRNNG_CMPLT_CNT/item.HMBR_DTL_CNT}"  pattern="0.00%"/>
				                	</c:if>
				                	<c:if test="${item.HMBR_DTL_CNT <=0 }">
				                		<fmt:formatNumber type="percent" value="0"  pattern="0.00%"/>
				                	</c:if>
				                	<br>( <fmt:formatNumber type="percent" value="${item.LRNNG_PRGRS}"  pattern="0.00%"/> )
				               	</td>
			                </c:if>
			                	<td class="text-middle text-center">
			                		<textarea class="form-control typing-text-memo" placeholder="" row="5" data-mbr-id="${item.MBR_ID}">${item.MBR_DSCRP}</textarea>
			                	</td>
				                <td class="text-middle text-center hide-cell-exceldown">
				                	<c:if test="${isLock eq false}">
					                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-crs-sn="${item.CRS_SN}" 
					                	data-hmbr-sn="${item.HMBR_SN}" 
					                	data-typeStr=""
					                	data-linkurl="/eduadm/mbrhstry/modify.do">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
					                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
					                	data-crs-sn="${item.CRS_SN}" 
					                	data-hmbr-sn="${item.HMBR_SN}"
					                	data-typeStr="" 
					                	data-del-st="${item.DEL_ST}">
					                		<i class="icon wb-trash" aria-hidden="true"></i>
					                	</a>
					                	<c:if test="${is_allow_online eq '1'}">
					                		<br><button type="button" class="btn btn-outline btn-dark btn-xs" onclick="pageMove(this)" 
					                		data-crs-sn="${item.CRS_SN}" 
					                		data-hmbr-sn="${item.HMBR_SN}"
					                		>온라인교육내역상세</button>
					                	</c:if>
					                	<br><button type="button" class="btn btn-outline btn-success btn-xs clk_mod_mbr_data"  
					                		data-mbr-id="${item.MBR_ID}" 
					                		data-mbr-scrty-key="${item.MBR_SCRTY_KEY}"
					                		data-linkurl="/eduadm/member/modify.do">회원정보수정</button>
				                	</c:if>
				                </td>
				                <td class="d-none">
				                	<c:choose>
				                		<c:when test="${item.DEL_ST eq '0'}">
						                	<c:choose>
						                		<c:when test="${item.LRNNG_ST eq '0'}">대기</c:when>
						                		<c:when test="${item.LRNNG_ST eq '1'}">승인</c:when>
						                		<c:when test="${item.LRNNG_ST eq '2' or item.LRNNG_ST eq '3'}">취소</c:when>
						                		<c:when test="${item.LRNNG_ST eq '4'}">보류</c:when>
						                		<c:otherwise></c:otherwise>				                	
						                	</c:choose>
					                	</c:when>
					                	<c:when test="${item.DEL_ST eq '1'}">삭제됨</c:when>
					                	<c:otherwise></c:otherwise>
					                </c:choose>
				                </td>
				                <td class="d-none">
				                	<c:choose>
				                		<c:when test="${item.LRNNG_CMPLT_ST eq '0'}">수강중</c:when>
				                		<c:when test="${item.LRNNG_CMPLT_ST eq '1'}">수강완료</c:when>
				                		<c:otherwise></c:otherwise>				                	
				                	</c:choose>
				                </td>
				                <td class="d-none">
				                	<c:choose>
				                		<c:when test="${item.LRNNG_CMPLT_ST eq '0'}">수강중</c:when>
				                		<c:when test="${item.LRNNG_CMPLT_ST eq '1'}">수강완료</c:when>
				                		<c:otherwise></c:otherwise>				                	
				                	</c:choose>
				                </td>
				                <td class="d-none">${item.HMBR_RCGNT_TIME}</td>
				                <td class="d-none">${item.MBR_ID}</td>
				                <td class="d-none">
				                	<c:choose>
										<c:when test="${empty item.MBR_NM}">${pfpu:phoneHypen(item.TMP_MBR_HP)}&nbsp;</c:when>
										<c:otherwise>${pfpu:phoneHypen(item.MBR_HP)}&nbsp;</c:otherwise>											
									</c:choose>
				                </td>
				                <td class="d-none">
				                	<c:choose>
				                		<c:when test="${item.MBR_SEX eq '1'}">남</c:when>
				                		<c:when test="${item.MBR_SEX eq '2'}">여</c:when>
					                	<c:otherwise></c:otherwise>
					                </c:choose>
				                </td>
				                <td class="d-none">${item.MBR_ADDR1} ${item.MBR_ADDR2}</td>
				                <td class="d-none">${item.HOPE_INDST}</td>
		              		</tr> 
		            	</c:forEach>
					</tbody>
	 				<tfoot>
	 					<tr class="footable-paging hide-cell-exceldown">
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
	.alertify .alert .msg, .alertify .dialog .msg {padding:0px!important;padding-left:2px!important;}
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
    $("input:text[numberOnly]").on("keyup", function() {
        $(this).val($(this).val().replace(/[^0-9]/g,""));
    });
    $(".clk-clear-input").click(function() {
    	var target = $(this).attr('for');
    	$('#'+target).val("");
    });
    $(function(){
    	$('.selectpicker_manual').selectpicker();
    	$('.datepickerModalStrSearch').datepicker({
    	    format: 'yyyy-mm-dd',
    	    //startDate: '0d',
    	    autoclose: true,
    	    language: "kr",
    	});
    	$('.datepickerModalEndSearch').datepicker({
    	    format: 'yyyy-mm-dd',
    	    //startDate: '0d',
    	    autoclose: true,
    	    language: "kr",
    	});
    	<%--
			//처리 로직은 tail.jsp 파일에 존재함.
			//공통 : 사용 할 대상(input)의 속성(attr)에 필히 추가 data-pattern-cnt="0"
		   	//파라미터 : target 			= keyup 이벤트를 받을 input text 의 jquery selector
		   	//파라미터 : default_pattern 	= 기본 패턴 형태값,자리수
		   	//파라미터 : over_pattern		= 기본 패턴의 자리수 이상인 경우 다음 단계 패턴 형태 및 해당 패턴의 자리수를 기입하며 , 해당 자리수 이상인 경우 다음 단계 패턴으로 전환 
		   	//파라미터 : automatch			= 화면 로딩시 해당 폼(input)에 입력값이 존재 할 경우 패턴을 자동 적용 할 것인지에 대한 설정(true:패턴 형태를 적용하여 노출)
		--%>
		formatterCustomMultiple([
		    {
				'target' : $('.mbr-birth-input-pattern-frm'),
				'default_pattern' : ['{{9999}}-{{99}}-{{99}}',8],
				'over_pattern' : null,
				'automatch' : true,
			},
			{
				'target' : $('.mbr-hp-input-pattern-frm'),
				'default_pattern' : ['{{999}}-{{9999}}-{{9999}}',11],
				'over_pattern' : null,
				'automatch' : true,
			},
			{
				'target' : $('.mbr-tel-input-pattern-frm'),
				'default_pattern' : ['{{9999}}-{{9999}}',8],
				'over_pattern' : [['{{999}}-{{999}}-{{9999}}',10],['{{999}}-{{9999}}-{{9999}}',11]],
				'automatch' : true,
			},
			{
				'target' : $('.ship-cd-input-pattern-frm'),
				'default_pattern' : ['{{9999999}}-{{9999999}}',14],
				'over_pattern' : null,
				'automatch' : true,
			},
			{
				'target' : $('.reg-num-cd-input-pattern-frm'),
				'default_pattern' : ['{{9999}}-{{999}}',7],
				'over_pattern' : null,
				'automatch' : true,
			},
			{
				'target' : $('.business-num-input-pattern-frm'),
				"default_pattern" : ["{{999}}-{{99}}-{{99999}}",10],
				"over_pattern" : [['{{999999}}-{{9999999}}',13]],
				"automatch" : true,
			},
		]);
		/* 기존방식
    	$('.mbr-birth-input-pattern').formatter({
    		'pattern': '{{9999}}-{{99}}-{{99}}',
    		'persistent': false
    	});
    	$('.mbr-hp-input-pattern').formatter({
    		'pattern': '{{999}}-{{9999}}-{{9999}}',
    		'persistent': false
    	});
    	$('.mbr-tel-input-pattern').formatter({
    		'pattern': '{{999}}-{{999}}-{{9999}}',
    		'persistent': false
    	});
    	$('.ship-cd-input-pattern').formatter({
    	  	'pattern': '{{9999999}}-{{9999999}}',
    	  	'persistent': false
    	});
    	*/
    });
    $("#search_sel_area_sido").change(function() {
    	if(this.value=='') {
    		$('#search_sel_area_signgu').html('<option value="">시군구 전체</option>').selectpicker('refresh');
    		return;
    	}  
    	var form = document.getElementById('searchFormSido');
    	form.CD_MASTER_ID.value = this.value;
    	form.action = '';
    	form.target = "";
    	$.ajax({
    		type:"POST",
    		url :"/all/code.do",
    		data:$('#searchFormSido').serialize(),
    		dataType: "json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			//console.log(data);
    			//console.log(data.rawdata);
    			if(data.error == '1') {
    				alertify.alert(data.msg);
    			} else {
    				var json = JSON.parse(data.rawdata);
    				var htmlString = '<option value="">시군구 전체</option>';
    				for (i=0; i<json.length; i++) {	
    					var item = json[i];
    					htmlString += '<option value="'+item.cd_id+'">'+item.cd_nm+'</option>';
    				}
    				//console.log(htmlString);
    				$('#search_sel_area_signgu').html(htmlString).selectpicker('refresh');
    			}
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    		}
    	});
    });   
    $('.btn-check-item-all').click( function() {
		var data_status = $(this).attr("data-status");
		if(data_status == 'uncheck') {
			$(this).attr("data-status","");
			$("input[name=checkbox_item]").prop("checked", false);
		} else {
			$(this).attr("data-status","uncheck");
			$("input[name=checkbox_item]").prop("checked", true);
		}
	});
	$(".btn-act-all-modify").click( function() {
		
		var btn_this = $(this);
		
		var chkedHMbrIds = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedHMbrIds.length!=0) chkedHMbrIds+=",";
			chkedHMbrIds+=$(this).val();
		});
		if(chkedHMbrIds.length==0) {
			alertify.alert("처리 할 대상자를 선택해주세요.");
			return;
		}
		var linkUrl = $(this).attr('data-linkUrl');
		alertify.prompt('처리 사유를 입력해주세요.',function(val, e) {
    		//ok
   			if(val.trim()=='') {
   				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
   				return;
   			}
  
   			var form = document.getElementById('listForm');
   	    	form.chkedHMbrIds.value = chkedHMbrIds;
   	    	form.LOG_UPD_MSG.value = val;
   			form.action="";
   			
   			$.ajax({
   				type:"POST",
   				url :linkUrl,
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
   							alertify.alert(data.msg,function(){
   								document.listForm.submit();
   								//refreshListPage()
   							});
   						}
   					}
   				},
   				beforeSend : function(xhr, opts) {
   					//console.log('before!');
   					if(isClickRequestLocked()) {
   						xhr.abort();
   						return;
   					}
   					btn_this.prop("disabled", true);
   					ajaxLoadingToastAppend();
   			    },
   				complete : function() {
   					btn_this.prop("disabled", false);
   					ajaxLoadingToastRemoved();
   					clickRequestLockStop();
   					//console.log('complete!');
   			    },
   				error: function(jqXHR, textStatus, errorThrown) {
   					ajaxLoadingToastRemoved();
   					clickRequestLockStop();
   					//console.log('error!');
   				}
   			});	
		});		
	});
	$(".btn-act-send-sms").click(function() {
    	var chkedMbrIds = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedMbrIds.length!=0) chkedMbrIds+=",";
			chkedMbrIds+=$(this).attr('data-sms-id');
		});
		if(chkedMbrIds.length==0) {
			alertify.alert("문자를 발송 할 대상자를 선택해주세요.");
			return;
		}
		var form = document.getElementById('ajaxSendSmsForm');
    	form.chkedMbrIds.value = chkedMbrIds;
    	$.ajax({
			type:"POST",
			url :"/adm/sms/send/write.do",
			data:$('#ajaxSendSmsForm').serialize(),
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#admPublicModal").html(data);
				$("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
			},
			beforeSend : function(xhr, opts) {
				ajaxLoadingToastAppend();
		    },
			complete : function() {
				ajaxLoadingToastRemoved();
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				ajaxLoadingToastRemoved();
				//console.log('error!');
			}
		});
   	}); 
	$(".btn-act-send-certificate-sms").click(function() {
    	var chkedMbrIds = "";
    	var lrnngCmpltCnt = 0;
    	var mbrIdsCnt = 1;
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedMbrIds.length!=0) {
				chkedMbrIds+=",";
				mbrIdsCnt++;
			}
			chkedMbrIds+=$(this).attr('data-sms-id');
		});
		if(chkedMbrIds.length==0) {
			alertify.alert("이수증 이미지 문자를 발송 할 대상자를 선택해주세요.");
			return;
		}
		if(mbrIdsCnt > 1) {
			alertify.alert("이수증 이미지 문자를 발송 할 대상자를 1명만 선택해주세요.");
			return;
		}
		$("input[name=checkbox_item]:checked").each(function() {
            if($(this).attr('data-lrnng-st') == 1 && $(this).attr('data-lrnng-cmplt-st') == 1)
               lrnngCmpltCnt++;
         });
         if(mbrIdsCnt != lrnngCmpltCnt){
            alertify.alert("이수증 이미지 문자를 발송은 이수완료 대상자를 선택해주세요.");
            return;
         }
		console.log(chkedMbrIds);
		console.log(chkedMbrIds.includes(','));
		var form = document.getElementById('ajaxSendCertificateSmsForm');
    	form.MBR_ID.value = chkedMbrIds;
    	$.ajax({
			type:"POST",
			url :"/adm/sms/send/certificate/write.do",
			data:$('#ajaxSendCertificateSmsForm').serialize(),
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#admPublicModal").html(data);
	            $("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
			},
			beforeSend : function(xhr, opts) {
				ajaxLoadingToastAppend();
		    },
			complete : function() {
				ajaxLoadingToastRemoved();
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				ajaxLoadingToastRemoved();
				//console.log('error!');
			}
		});
   	}); 
	$(".btn-act-view-kakao").click(function() {
		var chkedMbrIds = "";
		var mbrIdsCnt = 1;
		var lrnngCmpltCnt = 0;
		var templateId = $(this).attr('data-template-id');
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedMbrIds.length!=0) {
				chkedMbrIds+=",";
				mbrIdsCnt++;
			}
			chkedMbrIds+=$(this).attr('data-sms-id');
		});
		if(chkedMbrIds.length==0) {
			alertify.alert("알림톡을 발송 할 대상자를 선택해주세요.");
			return;
		}
		if(templateId == 'KKO_000005'){
			$("input[name=checkbox_item]:checked").each(function() {
				if($(this).attr('data-lrnng-st') == 1 && $(this).attr('data-lrnng-cmplt-st') == 1)
					lrnngCmpltCnt++;
			});
			if(mbrIdsCnt != lrnngCmpltCnt){
				alertify.alert("이수증 재발급은 이수완료 대상자를 선택해주세요.");
				return;
			}
		}
		var form = document.getElementById('ajaxViewKakaoForm');
		form.MBR_IDS.value = chkedMbrIds;
		if(templateId == 'KKO_000001'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000001';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000003';
		} else if(templateId == 'KKO_000019'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000019';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000021';
		} else if(templateId == 'KKO_000008'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000008';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000010';
		} else if(templateId == 'KKO_000022'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000022';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000023';
		} else if(templateId == 'KKO_000013'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000013';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000015';
		} else if(templateId == 'KKO_000011'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000011';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000012';
		} else if(templateId == 'KKO_000024'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000024';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000025';
		} else if(templateId == 'KKO_000005'){
			if(form.CRS_LAW_TYPE.value == 'CIDLAW002'){
				form.TEMPLATE_ID.value = 'KKO_000007';
			} else {
				if(form.CRS_MBR_CD.value == 'CIDN010300')
					form.TEMPLATE_ID.value = 'KKO_000005';
				else if(form.CRS_MBR_CD.value == 'CIDN010200')
					form.TEMPLATE_ID.value = 'KKO_000006';
			}
		} else if(templateId == 'KKO_000017'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000017';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000018';
		}
		$.ajax({
			type:"POST",
			url :"/eduadm/mbrhstry/send/kakao/write.do",
			data:$('#ajaxViewKakaoForm').serialize(),
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				$("#admPublicModal").html(data);
				$("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
			},
			beforeSend : function(xhr, opts) {
				//ajaxLoadingToastAppend();
			},
			complete : function() {
				//ajaxLoadingToastRemoved();
			//console.log('complete!');
			},
			error: function(jqXHR, textStatus, errorThrown) {
				//ajaxLoadingToastRemoved();
			//console.log('error!');
			}
		});
	});
	
	
	$(".btn-act-all-view-kakao").click(function() {
    	var mbrIdsCnt = $(this).attr('data-totalCnt');
		
		var form = document.getElementById('ajaxViewKakaoForm');
		form.MBR_IDS.value  = mbrIdsCnt;
		var templateId = $(this).attr('data-template-id');
		console.log(templateId);
		if(templateId == 'KKO_000001'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
	    		form.TEMPLATE_ID.value = 'KKO_000001';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
	    		form.TEMPLATE_ID.value = 'KKO_000003';
		} else if(templateId == 'KKO_000019'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
	    		form.TEMPLATE_ID.value = 'KKO_000019';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
	    		form.TEMPLATE_ID.value = 'KKO_000021';
		} else if(templateId == 'KKO_000008'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
	    		form.TEMPLATE_ID.value = 'KKO_000008';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
	    		form.TEMPLATE_ID.value = 'KKO_000010';
		} else if(templateId == 'KKO_000022'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
	    		form.TEMPLATE_ID.value = 'KKO_000022';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
	    		form.TEMPLATE_ID.value = 'KKO_000023';
		} else if(templateId == 'KKO_000011'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
	    		form.TEMPLATE_ID.value = 'KKO_000011';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
	    		form.TEMPLATE_ID.value = 'KKO_000012';
		} else if(templateId == 'KKO_000024'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
	    		form.TEMPLATE_ID.value = 'KKO_000024';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
	    		form.TEMPLATE_ID.value = 'KKO_000025';
		} else if(templateId == 'KKO_000013'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000013';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000015';
		} else if(templateId == 'KKO_000017'){
			if(form.CRS_MBR_CD.value == 'CIDN010300')
				form.TEMPLATE_ID.value = 'KKO_000017';
			else if(form.CRS_MBR_CD.value == 'CIDN010200')
				form.TEMPLATE_ID.value = 'KKO_000018';
		}
    	$.ajax({
			type:"POST",
			url :"/eduadm/mbrhstry/all/send/kakao/write.do",
			data:$('#ajaxViewKakaoForm').serialize(),
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#admPublicModal").html(data);
				$("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
			},
			beforeSend : function(xhr, opts) {
				//ajaxLoadingToastAppend();
		    },
			complete : function() {
				//ajaxLoadingToastRemoved();
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//ajaxLoadingToastRemoved();
				//console.log('error!');
			}
		});
   	});
	/*
	$(".clk_search_ship_detail").click(function() {
    	if($('.trg_search_ship_detail_layer').hasClass('hidden')) {
    		$('.trg_search_ship_detail_layer').removeClass('hidden');
    		$(this).html('<i class="icon wb-minus" aria-hidden="true"></i>낚시어선 추가조건');
    	} else {
    		$('.trg_search_ship_detail_layer').addClass('hidden');
    		$(this).html('<i class="icon wb-plus" aria-hidden="true"></i>낚시어선 추가조건');
    	}
    });
    $(".clk_search_store_detail").click(function() {
    	if($('.trg_search_store_detail_layer').hasClass('hidden')) {
    		$('.trg_search_store_detail_layer').removeClass('hidden');
    		$(this).html('<i class="icon wb-minus" aria-hidden="true"></i>낚시터 추가조건');
    	} else {
    		$('.trg_search_store_detail_layer').addClass('hidden');
    		$(this).html('<i class="icon wb-plus" aria-hidden="true"></i>낚시터 추가조건');
    	}
    });
    */
    function pageMove(obj) {
    	var form = document.getElementById('listForm');
    	form.CRS_SN.value = $(obj).attr('data-crs-sn');
    	form.HMBR_SN.value = $(obj).attr('data-hmbr-sn');
    	form.typeStr.value = '';
    	form.action = "/eduadm/mbrhstry/listDtl.do";
    	form.target = "";
    	form.pageIndex.value = '1';
    	form.submit();
    }
    function fn_egov_link_page(pageNo){
    	document.listForm.pageIndex.value = pageNo;
    	document.listForm.action = "";
    	document.listForm.target = "";
       	document.listForm.submit();
    }
    $(".clk_search_btn").click(function() {
    	
    	var form = document.getElementById('listForm');
    	form.pageIndex.value = '1';
    	form.action = '';
    	form.target = "";
    	
    	form.MBR_BIRTH.value = form.MBR_BIRTH.value.replace(/\-/g,'').trim();
    	form.MBR_HP.value = form.MBR_HP.value.replace(/\-/g,'').trim();
    	form.MBR_TEL.value = form.MBR_TEL.value.replace(/\-/g,'').trim();
    	form.HMBR_SHIP_CD.value = form.HMBR_SHIP_CD.value.replace(/\-/g,'').trim();
    	form.HMBR_REG_NUM_CD.value = form.HMBR_REG_NUM_CD.value.replace(/\-/g,'').trim();
    	form.HMBR_BUSINESS_NUM.value = form.HMBR_BUSINESS_NUM.value.replace(/\-/g,'').trim();
    	
    	form.submit();
    }); 
    $(".clk_add_data,.clk_mod_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.CRS_SN.value = $(this).attr('data-crs-sn');
    	form.HMBR_SN.value = $(this).attr('data-hmbr-sn');
    	form.typeStr.value = $(this).attr('data-typeStr');
    	form.action = '';
    	form.target = "";
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
				$("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	});  
    $(".btn-all-non-mbr-clear").click(function() {
        
    	alertify.prompt('삭제 후 복구가 불가능합니다.<br>삭제하려는 사유를 입력해주세요.',function(val, e) {
    		//ok
    			if(val.trim()=='') {
    				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    				return;
    			} 
    			//ok

	    		var form = document.getElementById('clearForm');
	        	form.LOG_UPD_MSG.value = val;
		    	form.action = '';
	        	$.ajax({
	    			type:"POST",
	    			url :"/eduadm/mbrhstry/all_clear_act.do",
	    			data:$('#clearForm').serialize(),
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
	    						alertify.alert(data.msg,function(){
	    							document.listForm.submit();	
	    							//refreshListPage();
	    						});
	    					}
	    				}
	    			},
	    			beforeSend : function(xhr, opts) {
	    				//console.log('before!');
	    				if(isClickRequestLocked()) {
	    					xhr.abort();
	    					return;
	    				}
	    				ajaxLoadingToastAppend();
	    			},
	    			complete : function() {
	    				//console.log('complete!');
	    				ajaxLoadingToastRemoved();
	    				clickRequestLockStop();
	    		    },
	    			error: function(jqXHR, textStatus, errorThrown) {
	    				//console.log('error!');
	    				ajaxLoadingToastRemoved();
	    				clickRequestLockStop();
	    			}
	    		}); 
    		} ,function() { 
    		//cancel    			
    	});
   	}); 
    $(".clk_del_data").click(function() {
    	var data_crs_sn = $(this).attr('data-crs-sn');
    	var data_hmbr_sn = $(this).attr('data-hmbr-sn');
    	var data_typeStr = $(this).attr('data-typeStr');
    	
    	alertify.prompt('삭제 후 복구가 불가능합니다.<br>삭제하려는 사유를 입력해주세요.',function(val, e) {
    		//ok
    			if(val.trim()=='') {
    				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    				return;
    			} 
    			//ok

	    		var form = document.getElementById('listForm');
	    		form.CRS_SN.value = data_crs_sn;
	        	form.HMBR_SN.value = data_hmbr_sn;
	        	form.typeStr.value = data_typeStr;
	        	form.LOG_UPD_MSG.value = val;
		    	form.action = '';
		    	form.target = "";
	        	$.ajax({
	    			type:"POST",
	    			url :"/eduadm/mbrhstry/delete_act.do",
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
	    						alertify.alert(data.msg,function(){
	    							document.listForm.submit();	
	    							//refreshListPage();
	    						});
	    					}
	    				}
	    			},
	    			beforeSend : function(xhr, opts) {
	    				//console.log('before!');
	    				if(isClickRequestLocked()) {
	    					xhr.abort();
	    					return;
	    				}
	    				ajaxLoadingToastAppend();
	    			},
	    			complete : function() {
	    				//console.log('complete!');
	    				ajaxLoadingToastRemoved();
	    				clickRequestLockStop();
	    		    },
	    			error: function(jqXHR, textStatus, errorThrown) {
	    				//console.log('error!');
	    				ajaxLoadingToastRemoved();
	    				clickRequestLockStop();
	    			}
	    		}); 
    		} ,function() { 
    		//cancel    			
    	});
    	
    	/*
    	var data_del_st = $(this).attr('data-del-st');
    	
    	var alert_message = "";
    	if(data_del_st == '1') {
    		alert_message = "실제 데이터를 삭제합니다.<br>그래도 삭제 하시겠습니까?";
    	} else {
    		alert_message = "삭제 하시겠습니까?";
    	}
    	alertify.confirm(alert_message, function(){ 
    		//ok
    		var form = document.getElementById('listForm');
    		form.CRS_SN.value = data_crs_sn;
        	form.HMBR_SN.value = data_hmbr_sn;
        	form.typeStr.value = data_typeStr;
	    	form.action = '';
	    	form.target = "";
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/mbrhstry/delete_act.do",
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
    						document.listForm.submit();	
    					}
    				}
    			},
    			complete : function() {
    				//console.log('complete!');
    		    },
    			error: function(jqXHR, textStatus, errorThrown) {
    				//console.log('error!');
    			}
    		});    		 
    	});
    	*/
   	}); 
    $(".btn-act-print").click( function() {
    	var chkedCrsSns = "";
		var chkedHMbrIds = "";
		var chkedMbrIds = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedHMbrIds.length!=0) chkedHMbrIds+=",";
			chkedHMbrIds+=$(this).val();
			if(chkedCrsSns.length!=0) chkedCrsSns+=",";
			chkedCrsSns+="${parentInfo.CRS_SN}";
			if(chkedMbrIds.length!=0) chkedMbrIds+=",";
			chkedMbrIds+=$(this).attr('data-sms-id');
		});
		if(chkedHMbrIds.length==0) {
			alertify.alert("이수증을 출력 할 대상을 선택해 주세요.");
			return;
		}
		alertify.prompt('출력 사유를 입력해주세요.',function(val, e) {
    		//ok
    			if(val.trim()=='') {
    				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    				return;
    			}
    		
    			$(".btn-act-print").attr("disabled", true);
    			//var gsWin = window.open("about:blank","winCrtf","scrollbars=yes,toolbar=no,location=no,resizable=no,status=no,menubar=no,width=750,height=900");
    			var gsWin = window.open("about:blank","winCrtf");
    	    	var form = document.getElementById('printForm');
    	    	form.CRTF_SNs.value = "";
    	    	form.CRS_SNs.value = chkedCrsSns;
    	    	form.HMBR_SNs.value = chkedHMbrIds;
    	    	form.MBR_IDs.value = chkedMbrIds;
    	    	form.CRTF_TYPE_ST.value = val;
    	    	form.LOG_UPD_MSG.value = val;
    	    	form.action = "/eduadm/certificate/popwin/view.do";
    	    	form.target = "winCrtf";
    	    	form.submit();   
		    	
    		} ,function() { 
    		//cancel    			
    	});
    });	
    $(".btn-attendance-book-act-print").click( function() {
		alertify.prompt('출력 사유를 입력해주세요.',function(val, e) {
    		//ok
    			if(val.trim()=='') {
    				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    				return;
    			}     			
    			//var gsWin = window.open("about:blank","winCrtf","scrollbars=yes,toolbar=no,location=no,resizable=no,status=no,menubar=no,width=750,height=900");
    			var gsWin = window.open("about:blank","winBook");
    	    	var form = document.getElementById('listForm');
    	    	form.LOG_UPD_MSG.value = val;
    	    	form.action = "/eduadm/mbrhstry/attendancebook/view.do";
    	    	form.target = "winBook";
    	    	form.submit();   
		    	
    		} ,function() { 
    		//cancel    			
    	});
    });	
    $(".clk_addnew_data").click(function() {
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
				$("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	});
    //엑셀다운로드 
    $(".btn-act-external-excel-down").click( function() {
    	var excel_type = $(this).attr('data-excel-type');
    	var excel_label = $(this).attr('data-excel-label');
    	//엑셀 파일명 조합
    	var addLabel = '';
    	{
    		
    	}
    	excel_label = excel_label.replace('{{LABEL}}',addLabel);
    	alertify.prompt('엑셀 다운로드 사유를 입력해주세요.',function(val, e) {
    		//ok
    			if(val.trim()=='') {
    				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    				return;
    			} 
    			/* alertify.alert('엑셀 다운로드가 완료 될 때까지 잠시 기다려 주세요.<br>데이터처리량에 따라 수분이 소요 될 수도 있습니다.</span>',function(){
    				document.listForm.action = "";
    				document.listForm.target = "";
    			}); */
		    	var form = document.getElementById('listForm');
		    	form.excel_label.value = excel_label;
		    	form.excel_type.value = excel_type;
		    	form.excel_msg.value = val;
		    	form.action = "";
    	    	form.target = "";
		    	//form.submit();
    			$.ajax({
            		type:"POST",
            		url :'/all/main/excel/chkDown.do',
            		data:$('#listForm').serialize(),
            		//dataType: "html",
            		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
            		async: true,
            		success: function(data, status, xhr) {
            			console.log('success!');
						console.log(data);
						if(data.error == 1) {
							alertify.alert(data.msg,function(){
								
							});
						} else {							
							var rstMsg = '엑셀 다운로드 신청이 완료되었습니다.<br>다운로드는 관리자 승인 후  <span class="bg-blue-grey-800 orange-600 font-weight-600" style=""><i class="site-menu-icon fa-print mr-5" aria-hidden="true"></i>출력관리-엑셀다운로드</span> 에서 받으실 수 있습니다.<br><span class="blue-600 font-weight-600">'+data.msg+'</span><br><br><span class="red-600">회원상세정보(부가정보)를 포함하기 때문에 데이터는 총 조회 ${paginationInfo.totalRecordCount}건 보다 많을 수 있습니다.</span>';
	            			alertify.alert(rstMsg,function(){
	            				$.ajax({
	                        		type:"POST",
	                        		url :'/all/main/excel/down.do',
	                        		data:$('#listForm').serialize(),
	                        		//dataType: "html",
	                        		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
	                        		async: true,
	                        		success: function(data, status, xhr) {
	                        			console.log('success!');
	            						//console.log(data);            			
	                        		},
	                        		beforeSend : function(xhr, opts) {
	                        			//console.log('before!');
	                        			if(isClickRequestLocked()) {
	                        				xhr.abort();
	                        				return;
	                        			}
	                        			//$('.btn-act-external-excel-down').addClass('disabled');
	                        		},
	                        		complete : function() {
	                        			//console.log('complete!');
	                        			//$('.btn-act-external-excel-down').removeClass('disabled');
	                        			clickRequestLockStop();
	                        	    },
	                        		error: function(jqXHR, textStatus, errorThrown) {
	                        			console.log(errorThrown);
	                        			//console.log('error!');
	                        			//$('.btn-act-external-excel-down').removeClass('disabled');
	                        			clickRequestLockStop();
	                        		}
	                        	});
	            			});
						}
            		},
            		beforeSend : function() {
            			//console.log('before!');
            			//$('.btn-act-external-excel-down').addClass('disabled');
            		},
            		complete : function() {
            			//console.log('complete!');
            			//$('.btn-act-external-excel-down').removeClass('disabled');
            	    },
            		error: function(jqXHR, textStatus, errorThrown) {
            			//console.log(errorThrown);
            			//console.log('error!');
            			//$('.btn-act-external-excel-down').removeClass('disabled');
            		}
            	});
    		} ,function() { 
    		//cancel    			
    	});
    });
    $(".clk_mod_mbr_data").click(function() {
    	var form = document.getElementById('ajaxMbrForm');
    	form.MBR_ID.value = $(this).attr('data-mbr-id');
    	form.MBR_SCRTY_KEY.value = $(this).attr('data-mbr-scrty-key');
    	form.action = '';
    	form.target = "";
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#ajaxMbrForm').serialize(),
			dataType: 'html',
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#admPublicModal").html(data);
				$("#admPublicModal").modal({backdrop: 'static', keyboard: false},'show');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	});  
    //메모저장
    var typingTimer = new Array();              
	var doneTypingInterval = 3000;
	$('.typing-text-memo').keyup(function(){
		var mbr_id = $(this).attr('data-mbr-id');
		if(!typingTimer.hasOwnProperty(mbr_id)) {
			var dummry = new Array();
			dummry['TIMER'] = null;
			typingTimer[mbr_id] = dummry;//최초초기화용
		}
		clearTimeout(typingTimer[mbr_id]['TIMER']);
		var target = $(this);
		typingTimer[mbr_id]['TIMER'] = setTimeout(function(){
			doneTyping(target);
		}, doneTypingInterval);		
	});
	function doneTyping(obj) {
		var form = document.getElementById('ajaxMbrForm');
    	form.MBR_DSCRP.value = $(obj).val();
    	var mbr_id = form.MBR_ID.value = $(obj).attr('data-mbr-id');
    	form.action = '';
    	form.target = "";
		$.ajax({
			type:"POST",
			url :"/eduadm/member/memo/update_act.do",
			data:$('#ajaxMbrForm').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				if(data.errCnt > 0) {
   					alertify.alert(data.msg);
   				} else {
   					if(data.error == '1') {
   						alertify.alert(data.msg);
   					} else {
   						toastr.info(data.msg,"",{
   							icon:false,
   							tapToDismiss: false,
   							closeButton: false,
   							debug: false,
   							newestOnTop: false,
   							progressBar: false,
   							positionClass: "toast-bottom-right",
   							preventDuplicates: false,
   							onclick: null,
   							showDuration: "300",
   						  	hideDuration: "300",
   							allowHtml: false,
   							onShown:function(){
   								
   							},
   							onHidden:function(){
   								
   							},
   							onclick:function(){
   								
   							},
   							onCloseClick:function(){
   								
   							}
   						});   						
   					}
   				}
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
	}
	
	function survey_view(){
		var form = document.getElementById('surveyForm');
		form.setAttribute('action','/eduadm/mbrhstry/survey_view.do');			
	 	form.submit();
	}
	
	/* $(".btn-act-send-kakao").click(function() {
    	var chkedMbrIds = "";
    	var mbrIdsCnt = 1;
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedMbrIds.length!=0) {
				chkedMbrIds+=",";
				mbrIdsCnt++;
			}
			chkedMbrIds+=$(this).attr('data-sms-id');
		});
		if(chkedMbrIds.length==0) {
			alertify.alert("알림톡을 발송 할 대상자를 선택해주세요.");
			return;
		}
		
		var chkConfirm = confirm(mbrIdsCnt + "명에게 알림톡을 보내시겠습니까?");
		
		if(chkConfirm){
			var form = document.getElementById('ajaxSendKakaoForm');
	    	form.MBR_IDS.value = chkedMbrIds;
	    	form.TEMPLATE_ID.value = 'KKO_000001';
	    	$.ajax({
				type:"POST",
				url :"/eduadm/mbrhstry/sendKakao.do",
				data:$('#ajaxSendKakaoForm').serialize(),
				dataType: "json",
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
				async: false,
				success: function(data, status, xhr) {
					if(data.error == '0') {
						alertify.alert(data.msg);
					} else {
						alertify.alert("전송 실패");
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
		} else {
			alert(" 알림톡 전송을 취소하셨습니다. ");
		}
   	}); */ 
	
	/* $(".btn-act-all-send-kakao").click(function() {
		var chkConfirm = confirm("알림톡을 보내시겠습니까?");
		
		if(chkConfirm){
			var form = document.getElementById('listForm');
			var form2 = document.getElementById('searchFormSido');
	    	form.action = '';
	    	form.target = "";
	    	
	    	form.MBR_BIRTH.value = form.MBR_BIRTH.value.replace(/\-/g,'').trim();
	    	form.MBR_HP.value = form.MBR_HP.value.replace(/\-/g,'').trim();
	    	form.MBR_TEL.value = form.MBR_TEL.value.replace(/\-/g,'').trim();
	    	
	    	form.HMBR_SIDO_CD.value = form2.CD_MASTER_ID.value;
	    	form.HMBR_SIGNGU_CD.value = form2.HMBR_SIGNGU_CD.value;
	    	
	    	$.ajax({
				type:"POST",
				url :"/eduadm/mbrhstry/allSendKakao.do",
				data:$('#listForm').serialize(),
				dataType: "json",
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
				beforeSend : function(xhr, opts) {
					console.log('beforeSend!');
					$("#page-loding").removeClass("d-none");
					if(isClickRequestLocked()) {
    					xhr.abort();
    					return;
    				}
			    },
    			complete : function() {
    				console.log('complete!');
    				clickRequestLockStop();
    		    },
				error: function(jqXHR, textStatus, errorThrown) {
					console.log('error!');
					clickRequestLockStop();
				}
			})
			.done(function(data, status, xhr){
				console.log('done!');
				$("#page-loding").addClass("d-none");
				if(data.error == '0') {
					alertify.alert(data.msg);
				} else {
					alertify.alert("전송 실패");
				}
			});
			
		} else {
			alert(" 알림톡 전송을 취소하셨습니다. ");
		}
   	}); */
    </script>
    
<%@ include file="../tail.jsp" %>

	<!-- // 엑셀다운로드(tail 뒤에 위치해야함.) // -->
    <script src="/common/js/jquery.table2excel.min.js"></script>
    <script>
    $(".btn-act-excel-down").click( function() {
    	var today = new Date();
    	var d = today.getDay();
    	var m = today.getMonth();
    	var y = today.getFullYear();
    	var hh = today.getHours();
    	var mm = today.getMinutes();
    	var ss = today.getSeconds();
    	var timestr = y+""+m+""+d+""+hh+""+mm+""+ss;
    	$('#datalist').table2excel({
    		name: "${parentInfo.CRS_NM}",
    		filename: "${parentInfo.CRS_NM}_"+timestr+".xls",
    		//fileext: ".xls",
    		exclude_img: true,
    		exclude_links: true,
    		exclude_inputs: true,
    		exclude: ".hide-cell-exceldown",
    	});
    });
    </script>
    <!-- End // 엑셀다운로드 // -->  
