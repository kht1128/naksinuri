<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="isLock" value="true"/>
<c:choose><%-- 권한분류 --%>
	<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
	<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
		<c:set var="isLock" value="false"/>		
	</c:when>
	<%-- 해양경찰서 담당자 --%>
	<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
		<c:set var="isLock" value="true"/>
	</c:when>
	<%-- 지자체 담당자 --%>
	<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
		<c:set var="isLock" value="true"/>
	</c:when>
	<%-- 교육기관 담당자 --%>
	<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
		<c:set var="isLock" value="true"/>
	</c:when>
	<%-- 기타 거부 --%>
	<c:otherwise>
	
	</c:otherwise>
</c:choose><%-- End 권한분류 --%>

<div class="modal-dialog modal-simple" id="innerModalLayer">
<c:set var="_today" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${_today}" pattern="yyyy" var="_year"/>
<fmt:parseNumber var="max_year" value="${_year }"/>
<form id="modal_action_form" class="modal-content form-horizontal" method="post" enctype="multipart/form-data" autocomplete="off" 
	action="/eduadm/curriculum/modify_act.do">
	<div class="modal-header draggable-move cursor-grab">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">교육과정 정보수정</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">승인여부</legend>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary" name="CRS_ST">
        			<option value="0" <c:if test="${info.CRS_ST eq '0'}">selected</c:if> >대기(교육일정에 노출되지 않음)</option>
        			<option value="1" <c:if test="${info.CRS_ST eq '1'}">selected</c:if>>완료</option>
      			</select>
			</div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">교육 신청 알림<br>발송 여부</legend>
			<div class="col-md-9" style="margin-top:10px;">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="EDU_APLY_NTCN_SNDNG_YN_Y" name="EDU_APLY_NTCN_SNDNG_YN_CHK" value="Y" <c:if test="${info.EDU_APLY_NTCN_SNDNG_YN eq 'Y'}">checked</c:if>>
					<label for="EDU_APLY_NTCN_SNDNG_YN_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="EDU_APLY_NTCN_SNDNG_YN_N" name="EDU_APLY_NTCN_SNDNG_YN_CHK" value="N" <c:if test="${info.EDU_APLY_NTCN_SNDNG_YN eq 'N'}">checked</c:if>>
  					<label for="EDU_APLY_NTCN_SNDNG_YN_N">사용안함</label>
				</div>
				<!-- <span class="text-help red-600 font-size-12"><b>삭제</b>상태에서 <b>사용함</b>으로 저장하면 다시 복구 할 수 있습니다.</span> -->
			</div>
		</div>		
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >교육그룹</label>
           	<div class="col-md-9">
           		<div class="input-group">
           			<select class="form-control selectpicker_manual" data-style="btn-dark text-white"  name="CRS_GRP_CD" >   
						<c:forEach var="item" items="${list_edu_grp_cd}">
							<option value="${item.CD_ID}" <c:if test="${info.CRS_GRP_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
						</c:forEach>
		            </select>		            
	           	</div> 
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >교육대상구분</label>
           	<div class="col-md-9">
           		<div class="input-group">
           			<select class="form-control selectpicker_manual selbox-crs-mbr-cd" data-style="btn-dark text-white"  name="CRS_MBR_CD" >   
						<c:forEach var="item" items="${list_mbr_cd}">
							<option value="${item.CD_ID}" <c:if test="${info.CRS_MBR_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
						</c:forEach>
		            </select>
		            
	           	</div> 
			</div>
		</div>
		<!-- 
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >교육분류</label>
           	<div class="col-md-9">
           		<div class="input-group">
           			<%-- //변경가능 하게 할 것인가?? 
           			<select class="form-control selectpicker_manual" data-style="btn-dark text-white"  name="CRS_TYPE" >   
						<option value="" <c:if test="${empty info.CRS_TYPE or info.CRS_ST eq ''}">selected</c:if> >종합교육</option>
						<option value="wknd_trnng" <c:if test="${info.CRS_TYPE eq 'wknd_trnng'}">selected</c:if> >주말교육</option>
						<option value="fshsk_trnng" <c:if test="${info.CRS_TYPE eq 'fshsk_trnng'}">selected</c:if> >귀어창업기술교육</option>
		            </select>
		            --%>
		            <c:choose>
						<c:when test="${info.CRS_TYPE eq 'fshsk_trnng'}">
							<c:set var="crs_type_str" value="귀어창업기술교육" />
						</c:when>
						<c:when test="${info.CRS_TYPE eq 'wknd_trnng'}">
							<c:set var="crs_type_str" value="주말교육" />
						</c:when>
						<c:when test="${info.CRS_TYPE eq 'default_online'}">
							<c:set var="crs_type_str" value="온라인강좌" />
						</c:when>
						<c:otherwise>
							<c:set var="crs_type_str" value="종합교육" />
						</c:otherwise>							
					</c:choose>
					<input type="text" class="form-control" value="${crs_type_str}" disabled />		              	            	
	           	</div> 
			</div>
		</div>
		 -->
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm">교육타입</label>
			<div class="col-md-9">
	            <select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="TYPE_GB" >   
					<option value="online" <c:if test="${info.TYPE_GB eq 'online'}">selected</c:if> >온라인 교육</option>
					<option value="offline" <c:if test="${info.TYPE_GB eq 'offline'}">selected</c:if> >오프라인 교육</option>
	            </select>          
            </div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="CRS_SN">고유번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.CRS_SN}" disabled />
				<input type="hidden" name="CRS_SN" value="${info.CRS_SN}" />
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_YEAR">교육년도</label>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CRS_YEAR" required> 
					<c:forEach var="i" begin="2018" end="${max_year+1}">
        				<option value="${i}" <c:if test="${i eq info.CRS_YEAR}">selected</c:if> >${i}</option>
					</c:forEach> 						
	            </select>  
			</div>
		</div>	
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_MONTH">교육 월</label>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="CRS_MONTH" required> 
					<c:if test="${not empty CRS_MONTH}">
						<option value="">교육 월을 선택해주세요.</option>
					</c:if>
					<c:forEach var="item" begin="1" end="12" step="1">
						<option value="${item}" <c:if test="${info.CRS_MONTH eq item}">selected</c:if>>${item}월</option>
					</c:forEach>										
	            </select>  
			</div>
		</div>			
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="CRS_NM">교육과정명</label>
           	<div class="col-md-9">
            	<input type="text" class="form-control" id="CRS_NM" name="CRS_NM" placeholder="교육과정 명칭을 입력하세요." autocomplete="off" value="<c:out value="${info.CRS_NM}"/>" required>
            	
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">설명</label>
			<div class="col-md-9">
				<textarea class="form-control" id="CRS_DSCRP" name="CRS_DSCRP" placeholder="설명(요약)" row="5"><c:out value="${info.CRS_DSCRP}"/></textarea>
 			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">교육구분(이수증)</label>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-outline btn-default"  name="CRS_LAW_TYPE" >   
					<option value="default" <c:if test="${info.CRS_LAW_TYPE eq 'default'}">selected</c:if> >일반교육</option>
					<option value="CIDLAW002" <c:if test="${info.CRS_LAW_TYPE eq 'CIDLAW002'}">selected</c:if> >신규,재개자 교육</option>
	            </select>
 				<span class="text-help red-600 font-size-12"><b>신규,재개자</b> 교육은 교육대상구분이 <b>낚시어선업자</b>인 경우에 활성화 됩니다.</span>     
 			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">교육기간</label>
			<div class="col-md-9  ">
				
				<fmt:parseDate value="${fn:replace(info.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
           		<fmt:parseDate value="${fn:replace(info.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
           		<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd" var="CRS_STR_DT"/>
           		<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd" var="CRS_END_DT"/>
           		<fmt:formatDate value="${parse_crs_str_dt}" pattern="HH:mm" var="CRS_STR_TIME"/>
           		<fmt:formatDate value="${parse_crs_end_dt}" pattern="HH:mm" var="CRS_END_TIME"/>
							
				<div class="col-lg-12 row form-icons pl-0">
					<div class="col-lg-5">
						<div class="input-group">
							<i class="form-control-icon wb-calendar pl-12"></i>
							<input type="text" class="form-control datepickerStr" name="CRS_STR_DT" value="${CRS_STR_DT}" placeholder="시작일자" required>
						</div>
					</div>
					<div class="form-control-label col-lg-1">~</div>
					<div class="col-lg-5">
						<div class="input-group">
							<i class="form-control-icon wb-calendar pl-12"></i>
		   					<input type="text" class="form-control datepickerEnd" name="CRS_END_DT" value="${CRS_END_DT}" placeholder="종료일자" required>
		   				</div>
	   				</div>
   				</div>
   				<div class="col-lg-12 row form-icons pl-0">
					<div class="col-lg-5">
						<div class="input-group">
							<i class="form-control-icon wb-time pl-12"></i>
							<input type="text" class="form-control timepickerStr" name="CRS_STR_TIME" value="${CRS_STR_TIME}" placeholder="시작시간" required>
						</div>
					</div>
					<div class="form-control-label col-lg-1">~</div>
					<div class="col-lg-5">
						<div class="input-group">
							<i class="form-control-icon wb-time pl-12"></i>
		   					<input type="text" class="form-control timepickerEnd" name="CRS_END_TIME" value="${CRS_END_TIME}" placeholder="종료시간" required>
		   				</div>
	   				</div>
   				</div>
			</div>			
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">모집기간</label>
			<div class="col-md-9 ">
			
				<fmt:parseDate value="${fn:replace(info.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
           		<fmt:parseDate value="${fn:replace(info.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
           		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd" var="RECRUIT_STR_DT"/>
           		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd" var="RECRUIT_END_DT"/>
           		<fmt:formatDate value="${parse_recruit_str_dt}" pattern="HH:mm" var="RECRUIT_STR_TIME"/>
           		<fmt:formatDate value="${parse_recruit_end_dt}" pattern="HH:mm" var="RECRUIT_END_TIME"/>
			
				<div class="col-lg-12 row form-icons pl-0">
					<div class="col-lg-5">
						<div class="input-group">
							<i class="form-control-icon wb-calendar pl-12"></i>
							<input type="text" class="form-control datepickerRecruitStr" name="RECRUIT_STR_DT" value="${RECRUIT_STR_DT}" placeholder="시작일자" required>
						</div>
					</div>
					<div class="form-control-label col-lg-1">~</div>
					<div class="col-lg-5">
						<div class="input-group">
							<i class="form-control-icon wb-calendar pl-12"></i>
		   					<input type="text" class="form-control datepickerRecruitEnd" name="RECRUIT_END_DT" value="${RECRUIT_END_DT}" placeholder="종료일자" required>
		   				</div>
	   				</div>
	   			</div>
	   			<div class="col-lg-12 row form-icons pl-0">
					<div class="col-lg-5">
						<div class="input-group">
							<i class="form-control-icon wb-time pl-12"></i>
							<input type="text" class="form-control timepickerRecruitStr" name="RECRUIT_STR_TIME" value="${RECRUIT_STR_TIME}" placeholder="시작시간" required>
						</div>
					</div>
					<div class="form-control-label col-lg-1">~</div>
					<div class="col-lg-5">
						<div class="input-group">
							<i class="form-control-icon wb-time pl-12"></i>
		   					<input type="text" class="form-control timepickerRecruitEnd" name="RECRUIT_END_TIME" value="${RECRUIT_END_TIME}" placeholder="종료시간" required>
		   				</div>
	   				</div>
   				</div>	   			
	   			
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CAT_INS_SN">교육기관</label>
			<div class="col-md-9">
				<select class="form-control selec2_manual" name="CAT_INS_SN" required <c:if test="${isLock eq true}">disabled</c:if>>
					<c:if test="${empty edu_category_ins_inf}">
						<option value="">등록 된 교육기관 정보가 없습니다.</option>
					</c:if>   
					<c:if test="${not empty edu_category_ins_inf}">
						<option value="">교육기관을 선택해주세요.</option>
					</c:if>
					<c:forEach var="item" items="${edu_category_ins_inf}">
						<option value="${item.CAT_INS_SN}" <c:if test="${item.CAT_INS_SN eq info.CAT_INS_SN}">selected</c:if> >${item.CAT_INS_NM}</option>
					</c:forEach>
	            </select>  
			</div>
		</div>
		
		<c:choose><%-- 권한분류 --%>
			<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
				<div class="form-group row">
		 			<label class="col-md-3 form-control-label" for="EXTRL_INSTT_RLS_CD">외부기관 공개여부</label>
					<div class="col-md-9">
						<c:forEach var="item" items="${edu_category_ins_inf}" varStatus="status">
						<c:if test="${item.CAT_INS_SN eq '49'}"> 
							<div class="checkbox-custom checkbox-primary">
			     				<input type="checkbox" id="EXTRL_INSTT_RLS_CD_${status.index}" name="EXTRL_INSTT_RLS_CD" value="${item.CAT_INS_SN}"
			     				<c:if test="${not empty info.EXTRL_INSTT_RLS_CD}">
			     				<c:forEach var="subitem" items="${fn:split(info.EXTRL_INSTT_RLS_CD,',')}">
	     							<c:if test="${item.CAT_INS_SN eq subitem}">checked</c:if>
	     						</c:forEach>
	     						</c:if> >
			                  	<label for="EXTRL_INSTT_RLS_CD_${status.index}"><b>${item.CAT_INS_NM}</b> <span class="red-600">에서 관리 가능하도록 허용함</span></label>
			                </div>
						</c:if>	
						</c:forEach>	
					</div>
				</div>
			</c:when>
			<%-- 해양경찰서 담당자 --%>
			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
				<input type="hidden" name="EXTRL_INSTT_RLS_CD" value="${info.EXTRL_INSTT_RLS_CD}"/>
			</c:when>
			<%-- 지자체 담당자 --%>
			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
				<input type="hidden" name="EXTRL_INSTT_RLS_CD" value="${info.EXTRL_INSTT_RLS_CD}"/>
			</c:when>
			<%-- 교육기관 담당자 --%>
			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
				<input type="hidden" name="EXTRL_INSTT_RLS_CD" value="${info.EXTRL_INSTT_RLS_CD}"/>
			</c:when>
			<%-- 기타 거부 --%>
			<c:otherwise>
			
			</c:otherwise>
		</c:choose><%-- End 권한분류 --%> 
				
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_PLACE">교육장소명</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="CRS_PLACE" name="CRS_PLACE" placeholder="교육장소를 입력해주세요." autocomplete="off" value="<c:out value="${info.CRS_PLACE}"/>" required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_ADDR">교육장주소</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="CRS_ADDR" name="CRS_ADDR" placeholder="교육장 주소를 입력해주세요." autocomplete="off" value="<c:out value="${info.CRS_ADDR}"/>" required>
			</div>
		</div>		
		<div class="form-group row">
			<input type="hidden" name="CRS_SCHDL_FILE_SN" value="${info.CRS_SCHDL_FILE_SN}" /> 
			<legend class="col-md-3 form-control-label">교육시간표(일정)</legend>
			<div class="col-md-9">
				<div class="input-group input-group-file" data-plugin="inputGroupFile">
	     			<input type="text" class="form-control" readonly="" value="${info.CRS_SCHDL_FILE_SN}" id="egovComFileUploader_label"/>
	     			<span class="input-group-btn">
	       				<span class="btn btn-success btn-file">
	         				<i class="icon wb-upload" aria-hidden="true"></i>
	         				<input type="file" name="FILE_ATCH" id="egovComFileUploader" multiple="false" value="" />
	       				</span>
	     			</span>
	   			</div>
	   			<div id="egovComFileList"></div>
	   		</div>
	   	</div>
	   	<%-- <c:if test="${not empty info.CRS_SCHDL_FILE_SN}"> 음.. --%>
		   	<div class="form-group row">
	 			<label class="col-md-3 form-control-label" >첨부파일</label>
				<div class="col-md-9">
		   			<input type="hidden" name="fileMaxCnt" value="5" />
	   				<input type="hidden" name="returnUrl" value="/eduadm/curriculum/modify.do">
	   				<input type="hidden" name="targetFormId" value="listForm">
	   				<input type="hidden" name="selectedId" value="" />
					<c:import url="/cmm/fms/selectFileInfsForUpdateAjax.do" >
				    	<c:param name="param_atchFileId" value="${info.CRS_SCHDL_FILE_SN}" />
				    </c:import>		                
				</div>
	   		</div>
   		<%-- </c:if> --%>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_MBR">담당자</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="CRS_MBR" name="CRS_MBR" placeholder="교육 담당자를 입력해주세요." autocomplete="off" value="<c:out value="${info.CRS_MBR}"/>" required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_MBR_TEL">담당자연락처</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="CRS_MBR_TEL" name="CRS_MBR_TEL" placeholder="교육 담당자 연락처를 입력해주세요." autocomplete="off" value="<c:out value="${info.CRS_MBR_TEL}"/>" required>
			</div>
		</div>	
		<!-- 
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_CASH">교육비(수강비)</label>
			<div class="col-md-9">
				<input type="number" class="form-control" id="CRS_CASH" name="CRS_CASH" placeholder="교육비(수강비)를 입력해주세요." autocomplete="off" value="${info.CRS_CASH}" required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="CRS_DPST_ACNT">입금계좌번호</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="CRS_DPST_ACNT" name="CRS_DPST_ACNT" placeholder="입금계좌번호를 입력해주세요." autocomplete="off" value="${info.CRS_DPST_ACNT}" required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="CRS_DPST_BANK">입금은행명</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="CRS_DPST_BANK" name="CRS_DPST_BANK" placeholder="입금은행명을 입력해주세요." autocomplete="off" value="${info.CRS_DPST_BANK}" required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label form-control-sm" for="CRS_DPST_MBR">입금예금주</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="CRS_DPST_MBR" name="CRS_DPST_MBR" placeholder="입금예금주를 입력해주세요." autocomplete="off" value="${info.CRS_DPST_MBR}" required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_ORD">정렬순서</label>
			<div class="col-md-9">
				<input type="number" class="form-control" id="CRS_ORD" name="CRS_ORD" placeholder="기본값은 9999 입니다." autocomplete="off" value="${info.CRS_ORD}" required>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label form-control-sm">순차진행여부(교과목)</label>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="STEP_ST" >   
					<option value="" <c:if test="${empty info.STEP_ST}">selected</c:if> >기본(랜덤순)</option>
					<option value="sort" <c:if test="${info.STEP_ST eq 'sort'}">selected</c:if> >정렬순</option>
	            </select>            
            </div>
		</div>
		-->
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="MBR_MAX_CNT">모집최대인원</label>
			<div class="col-md-9">
				<input type="number" class="form-control" id="MBR_MAX_CNT" name="MBR_MAX_CNT" placeholder="기본값은 0 이며 무제한입니다." autocomplete="off" value="${info.MBR_MAX_CNT}" required>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_TOT_TIME">총 교육시간(배점)</label>
			<div class="col-md-9">
				<div class="input-group">
            		<input type="text" class="form-control" id="CRS_TOT_TIME" name="CRS_TOT_TIME" placeholder="총 교육시간을 입력해주세요." autocomplete="off" value="${info.CRS_TOT_TIME}" required>
	            	<div class="input-group-append">
	           			<span class="input-group-text">시간(개월)</span> 
	           		</div>
	           	</div>
	           	<span class="text-help">온라인교육을 제외한 오프라인 교육시간만 입력해주세요.</span>
			</div>
		</div>
		<div class="form-group row">
 			<label class="col-md-3 form-control-label" for="CRS_TOT_POINT">총 교육점수(배점)</label>
			<div class="col-md-9">
				<div class="input-group">
            		<input type="text" class="form-control" id="CRS_TOT_POINT" name="CRS_TOT_POINT" placeholder="총 교육점수를 입력해주세요." autocomplete="off" value="${info.CRS_TOT_POINT}" required>
	            	<div class="input-group-append">
	           			<span class="input-group-text">점</span> 
	           		</div>
	           	</div>
	           	<span class="text-help">온라인교육을 제외한 오프라인 교육점수만 입력해주세요.</span>
			</div>
		</div>
		<hr>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >신청지역제한</label>
           	<div class="col-md-9">
           		<div class="form-group input-group">           			 
           			<div class="col-md-8 pl-0 pr-0" id="trg_list_lock_area_ship" >
	           			<select class="form-control selectpicker_manual" data-style="btn-dark text-white"  name="CRS_LOCK_AREA_CD" id="trg_list_lock_area_ship_select" >   
	           				<option value="NULL" <c:if test="${empty info.CRS_LOCK_AREA_CD}">selected</c:if> >신청 지역제한 미설정</option>
							<c:forEach var="item" items="${list_lock_area_ship_grp_cd}">
								<option value="${item.CD_ID}" <c:if test="${info.CRS_LOCK_AREA_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
							</c:forEach>
			            </select>		      
		            </div> 
		            <div class="col-md-8 pl-0 pr-0" id="trg_list_lock_area_house" >
	           			<select class="form-control selectpicker_manual" data-style="btn-dark text-white"  name="CRS_LOCK_AREA_CD" id="trg_list_lock_area_house_select" >   
	           				<option value="NULL" <c:if test="${empty info.CRS_LOCK_AREA_CD}">selected</c:if> >신청 지역제한 미설정</option>
							<c:forEach var="item" items="${list_lock_area_house_grp_cd}">
								<option value="${item.CD_ID}" <c:if test="${info.CRS_LOCK_AREA_CD eq item.CD_ID}">selected</c:if> >${item.CD_NM}</option>
							</c:forEach>
			            </select>		      
		            </div> 		       
           			<div class="col-md-4 pr-0 text-center">
	           			<%-- <div class="checkbox-custom checkbox-primary">	           	
	           				<input type="checkbox" name="CRS_LOCK_AREA_ST" value="${item.CRS_LOCK_AREA_ST}">			
		     				<input type="checkbox" name="CRS_LOCK_AREA_ST_CHK" name="CRS_LOCK_AREA_ST_CHK" value="Y" <c:if test="${info.CRS_LOCK_AREA_ST eq '1'}">checked</c:if> >		                  	
		                  	<label for="CRS_LOCK_AREA_ST"></label> 적용하기
		                </div> --%>
		                <select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="CRS_LOCK_AREA_ST" >   
							<option value="0" <c:if test="${info.CRS_LOCK_AREA_ST eq 0}">selected</c:if> >적용안함</option>
							<option value="1" <c:if test="${info.CRS_LOCK_AREA_ST eq 1}">selected</c:if> >적용함</option>
			            </select>  
	                </div>     
	           	</div> 
			</div>
		</div>
		<c:if test="${info.TYPE_GB eq 'online'}">
		<div class="form-group row">
			<label class="col-md-3 form-control-label" >설문조사</label>
           	<div class="col-md-9">
           		<div class="form-group input-group">           			 
		            <div class="col-md-8 pl-0 pr-0" id="" >
	           			<select class="form-control selectpicker_manual" data-style="btn-outline btn-default"  name="CRS_SV_ID" id="CRS_SV_ID" >   
	           				<option value="" <c:if test="${empty info.CRS_SV_ID}">selected</c:if> >설문조사 선택안함</option>
							<c:forEach var="item" items="${survey_list}">
								<c:if test="${item.SV_LC eq 'online_edu'}">
									<option value="${item.sv_id}" <c:if test="${info.CRS_SV_ID eq item.sv_id}">selected</c:if> >${item.sv_subject }</option>
								</c:if>
							</c:forEach>
			            </select>		      
		            </div> 		       
           			<div class="col-md-4 pr-0 text-center">
		                <select class="form-control selectpicker_manual" data-style="btn-outline btn-default"  name="CRS_SV_ST" >   
							<option value="0" <c:if test="${info.CRS_SV_ST eq 0}">selected</c:if> >적용안함</option>
							<option value="1" <c:if test="${info.CRS_SV_ST eq 1}">selected</c:if> >적용함</option>
			            </select>  
	                </div>     
	           	</div> 
			</div>
		</div>
		</c:if>
		<div class="form-group row">
			<label class="col-md-3 form-control-label">모집인원잠금</label>
			<div class="col-md-9">
				<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="LOCK_ST" >   
					<option value="0" <c:if test="${info.LOCK_ST eq 0}">selected</c:if> >해제&nbsp;:&nbsp;모집 신청을 허용합니다.</option>
					<option value="1" <c:if test="${info.LOCK_ST eq 1}">selected</c:if> >잠금&nbsp;:&nbsp;모집 신청을 중단합니다.</option>
	            </select>            
            </div>
		</div>
		<div class="form-group row">
			<legend class="col-md-3 form-control-label">사용여부</legend>
			<div class="col-md-9">
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_Y" name="USE_ST_CHK" value="Y" <c:if test="${info.USE_ST eq '1'}">checked</c:if>>
					<label for="USE_ST_Y">사용함</label>
				</div>
				<div class="radio-custom radio-default radio-inline">
					<input type="radio" id="USE_ST_N" name="USE_ST_CHK" value="N" <c:if test="${info.USE_ST eq '0'}">checked</c:if>>
  					<label for="USE_ST_N">사용안함</label>
				</div>
				<span class="text-help red-600 font-size-12"><b>삭제</b>상태에서 <b>사용함</b>으로 저장하면 다시 복구 할 수 있습니다.</span>
			</div>
		</div>		
        <div class="form-group row">
        	<label class="col-md-3 form-control-label" for="REG_DT">작성정보</label>
           	<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.REG_DT} (${info.REG_MBR_ID})" disabled>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-md-3 form-control-label" for="UPD_DT">변경정보</label>
			<div class="col-md-9">
				<input type="text" class="form-control" placeholder="" autocomplete="off" value="${info.UPD_DT} (${info.UPD_MBR_ID})" disabled>
   			</div>
		</div>
		<div class="float-right">
        	<button type="submit" class="btn btn-primary btn-outline trg_btn_submit" id="btn_submit">변경하기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<!-- //첨부파일 추가 커스텀 -->
<script type="text/javascript" src="/common/js/EgovMultiFile.js" ></script>
<script type="text/javascript">
var form = document.getElementById('modal_action_form');
var existFileNum = form.fileListCnt.value;
var maxFileNum = form.fileMaxCnt.value; 
var uploadableFileNum = maxFileNum - existFileNum; // 최대등록가능한 파일숫자에서 기존에 등록된 숫자를 뺀다.
if(uploadableFileNum<0) {
	uploadableFileNum = 0;
}
if(uploadableFileNum != 0){	 
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}	
$('#egovComFileUploader_label').val("첨부파일은 총 "+maxFileNum+" 개 까지 등록 가능합니다.");	
</script>
<!-- End //첨부파일 추가 커스텀 -->
<style>
.select2-container { width: 99.9% !important; }
.ui-timepicker-wrapper{z-index:9999!important;}
</style>
<script>
$(function(){
	$(".selec2_manual").select2();
	$('.selectpicker_manual').selectpicker();
	var crs_mbr_cd = '${info.CRS_MBR_CD}';
	if(crs_mbr_cd == 'CIDN010300') {//낚시어선
		$('#trg_list_lock_area_ship').show();
		$('#trg_list_lock_area_house').hide();	
		$('#trg_list_lock_area_ship_select').removeAttr("disabled");
		$('#trg_list_lock_area_house_select').attr('disabled','disabled');
	} else if(crs_mbr_cd == 'CIDN010200') {//낚시터
		$('#trg_list_lock_area_ship').hide();
		$('#trg_list_lock_area_house').show();
		$('#trg_list_lock_area_ship_select').attr('disabled','disabled');
		$('#trg_list_lock_area_house_select').removeAttr("disabled");
	} else {
		$('#trg_list_lock_area_ship').hide();
		$('#trg_list_lock_area_house').hide();	
		$('#trg_list_lock_area_ship_select').attr('disabled','disabled');
		$('#trg_list_lock_area_house_select').attr('disabled','disabled');
	}
});
/*
$('.datepickerStr10').datepicker({
    format: 'yyyy-mm-dd 10:00:00',
    //startDate: '0d',
    autoclose: true,
    language: "kr",
});
$('.datepickerEnd24').datepicker({
    format: 'yyyy-mm-dd 23:59:59',
    //startDate: '0d',
    autoclose: true,
    language: "kr",
});
*/
//모집기간
$('.datepickerRecruitStr').datepicker({
    format: 'yyyy-mm-dd',
    //startDate: '0d',
    autoclose: true,
    language: "kr",
});
$('.datepickerRecruitEnd').datepicker({
    format: 'yyyy-mm-dd',
    //startDate: '0d',
    autoclose: true,
    language: "kr",
});
$('.timepickerRecruitStr').timepicker({
    'showDuration': true,
    'timeFormat': 'H:i',
});
$('.timepickerRecruitEnd').timepicker({
    'showDuration': true,
    'timeFormat': 'H:i',
});
//교육기간
$('.datepickerStr').datepicker({
    format: 'yyyy-mm-dd',
    //startDate: '0d',
    autoclose: true,
    language: "kr",
});
$('.datepickerEnd').datepicker({
    format: 'yyyy-mm-dd',
    //startDate: '0d',
    autoclose: true,
    language: "kr",
});
$('.timepickerStr').timepicker({
    'showDuration': true,
    'timeFormat': 'H:i',
});
$('.timepickerEnd').timepicker({
    'showDuration': true,
    'timeFormat': 'H:i',
});
/*
 익스(8이하?) 하위버전에서 작동이 안될경우 현재 주석을 해제하고 로직을 구축하자. 아래 스크립트 링크 필수
 -> /common/js/jquery.form.js 
$('#modal_action_form').ajaxForm({
	url :$(this).attr("action"),
    beforeSubmit: function (data,form,option) {
    	console.log(data);
    	console.log(form);
        $('.trg_btn_submit').addClass('disabled');
        return true;
    },
    success: function(data){
    	console.log('complete!');
		$('.trg_btn_submit').removeClass('disabled');
		console.log(data);
    },
    error: function(err){
    	$('.trg_btn_submit').removeClass('disabled');
        console.log('error!');
        console.log(err);
    }
 });
 */
$('.selbox-crs-mbr-cd').change(function(){
	var crs_mbr_cd = $(this).val();
	if(crs_mbr_cd == 'CIDN010300') {//낚시어선
		$('#trg_list_lock_area_ship').show();
		$('#trg_list_lock_area_house').hide();	
		$('#trg_list_lock_area_ship_select').removeAttr("disabled");
		$('#trg_list_lock_area_house_select').attr('disabled','disabled');
	} else if(crs_mbr_cd == 'CIDN010200') {//낚시터
		$('#trg_list_lock_area_ship').hide();
		$('#trg_list_lock_area_house').show();
		$('#trg_list_lock_area_ship_select').attr('disabled','disabled');
		$('#trg_list_lock_area_house_select').removeAttr("disabled");
	} else {
		$('#trg_list_lock_area_ship').hide();
		$('#trg_list_lock_area_house').hide();	
		$('#trg_list_lock_area_ship_select').attr('disabled','disabled');
		$('#trg_list_lock_area_house_select').attr('disabled','disabled');
	}
});
$("#modal_action_form").bind("submit", function(event) {
	event.preventDefault();
	
	var form = $(this)[0];
	//시간 검증을 위한 구간
	var CRS_STR_DT = form.CRS_STR_DT.value+' '+form.CRS_STR_TIME.value+':00';
	var CRS_END_DT = form.CRS_END_DT.value+' '+form.CRS_END_TIME.value+':00';
	var RECRUIT_STR_DT = form.RECRUIT_STR_DT.value+' '+form.RECRUIT_STR_TIME.value+':00';
	var RECRUIT_END_DT = form.RECRUIT_END_DT.value+' '+form.RECRUIT_END_TIME.value+':00';
	var date_crs_str_dt = new Date(Date.parse(CRS_STR_DT));
	var date_crs_end_dt = new Date(Date.parse(CRS_END_DT));
	var date_recruit_str_dt = new Date(Date.parse(RECRUIT_STR_DT));
	var date_recruit_end_dt = new Date(Date.parse(RECRUIT_END_DT));
	/* if(date_crs_str_dt >= date_crs_end_dt) {
		alertify.alert("교육기간 내에서 시작일이 종료일 보다 클 수 없습니다.");
		return false;
	}
	if(date_recruit_str_dt >= date_recruit_end_dt) {
		alertify.alert("모집기간 내에서 시작일이 종료일 보다 클 수 없습니다.");
		return false;
	}
	if(date_recruit_end_dt >= date_crs_str_dt) {
		alertify.alert("모집기간은 교육기간(시작일)보다 같거나 클 수 없습니다.");
		return false;
	} */
	//
	form.CRS_STR_DT.value = form.CRS_STR_DT.value+' '+form.CRS_STR_TIME.value+':00';
	form.CRS_END_DT.value = form.CRS_END_DT.value+' '+form.CRS_END_TIME.value+':00';
	form.RECRUIT_STR_DT.value = form.RECRUIT_STR_DT.value+' '+form.RECRUIT_STR_TIME.value+':00';
	form.RECRUIT_END_DT.value = form.RECRUIT_END_DT.value+' '+form.RECRUIT_END_TIME.value+':00';
	
	var formData = new FormData(form);
	formData.append("a","1");
	$.ajax({
		type:"POST",
		enctype: 'multipart/form-data',
		url :$(this).attr("action"),
		data:formData,
		contentType: false,
		processData: false,
		async: true,
		cache: false,
		timeout: 600000,
		success: function(data, status, xhr) {
			//console.log('success!');
			if(data.errCnt > 0) {
				var pass = true;
				for(var key in data.errField) {
					$('#'+data.errField[key]).addClass('is-invalid');
					pass = false;
				}
				if(pass) {
					alertify.alert(data.msg);
				}
			} else {
				if(data.error == '1') {
					alertify.alert(data.msg);
				}
				$("#admPublicModal").modal('hide');
				//window.location.reload();
				document.listForm.submit();
				//refreshListPage();
			}
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
			$('.trg_btn_submit').addClass('disabled');
		},
		complete : function() {
			//console.log('complete!');
			$('.trg_btn_submit').removeClass('disabled');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			//console.log(jqXHR);
			//console.log(textStatus);
			//console.log(errorThrown);		
			clickRequestLockStop();
		}
	});		
});
</script>


<script defer="defer">
//------------------------------------------------------
//팝업 드래그 위치 변경 
//------------------------------------------------------
$("#innerModalLayer").draggable({
	handle: ".draggable-move",
 	drag: function(event,ui) {
 		var maxWidth = window.innerWidth;
		var maxHeight = window.innerHeight;
		if(isEmpty(maxWidth)) {
			maxWidth = document.body.offsetWidth;
		}
		if(isEmpty(maxHeight)) {
			maxHeight = document.body.offsetHeight;
		}
		var top = ui.position.top;
		var left = ui.position.left;
		if(top < 0) {
			top = 0;
		}
		if(top >= maxHeight) {
			top = maxHeight-61;
		}
		var boxW = 998;
		var boxWmargin = 130;
		if(left < -boxW) {
			left = boxW+boxWmargin;
		}
		if(left >= maxWidth) {
			left = maxWidth-boxWmargin;
		}
		ui.position={'top': top, 'left': left};	
    },
	stop: function() {
		return $(this).css({
			height: 'auto',
	    });
	}
});
function isEmpty(str){
	if(typeof str == "undefined" || str == null || str == "" || str == "0") return true;
    else return false ;
}
//----------------------------------------------------
</script>