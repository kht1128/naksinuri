<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
	input[type=radio] {height: 20px;width: 20px;margin-left: 10px;} 
	
	.excel-style-newAdd td {border-top: 3px solid #0bb2d4;}
	.excel-style-change td {border-top: 3px solid #eb6709;}
	.excel-style-add td {border-top: 3px solid #28a745;}
	.excel-style-delete td {border-top: 3px solid #526069;}
	/*
	.excel-style-newAdd {border: 1px solid #bee5eb;}
	.excel-style-change {border: 1px solid #ffeeba;}
	.excel-style-add {border: 1px solid #c3e6cb;}
	.excel-style-delete {border: 1px solid #76838F;}
	*/
</style>

<div class="example-tooltip">
	<div class="tooltip bs-tooltip-top tooltip-info" role="tooltip">
		<div class="arrow"></div>
		<div class="tooltip-inner">전체 ${list_size }건</div>
	</div>
</div>
<div class="float-right">
	<span class="ml-5 mr-5"><div class="inline-block bg-cyan-300 mr-2" style="width:10px;height:10px;"></div>신규대상자건</span>
	<span class="ml-5 mr-5"><div class="inline-block bg-red-300 mr-2" style="width:10px;height:10px;"></div>상세정보 삭제</span>
	<span class="ml-5 mr-5"><div class="inline-block bg-grey-300 mr-2" style="width:10px;height:10px;"></div>선택한 대상자상세정보에 추가 또는 변경시 교체 될 대상자상세정보</span>
	<br>
	<span class="ml-5 mr-5"><i class="fa-file-excel-o green-600 mr-2"></i>업로드한 엑셀자료</span>
	<span class="ml-5 mr-5"><i class="icon fa-exclamation-circle red-300 mr-2"></i>필수정보</span>
	<span class="ml-5 mr-5"><i class="icon fa-times red-300 mr-2"></i>누락된정보</span>
</div>
<!-- table:start -->
<form:form commandName="" id="uploadForm" name="uploadForm" method="post" action="/eduadm/member/loc_gov_request.do">
<input type="hidden" name="json_data">
<input type="hidden" name="EU_SN" value="${EU_SN}">

<table id="datalist" class="table table-hover footable footable-paging footable-paging-center">
	<colgroup>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
	</colgroup>
   	<thead>
   		<tr>
			<th class="text-middle text-center"></th>
			<th class="text-middle text-center">처리구분</th>
			<th class="text-middle text-center">낚시터/어선 구분<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">시도<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">시군구<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">허가(등록)/<br>신고번호</th>
			<th class="text-middle text-center">어선번호</th>
			<th class="text-middle text-center">낚시터명/어선명</th>
			<th class="text-middle text-center">대상구분(개인업자,법인업자,선원)<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">법인업자구분(대표자/교육책임자)</th>	
			<th class="text-middle text-center">해기사자격증</th>
			<th class="text-middle text-center">성명(법인명)<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">생년월일(법인등록번호)<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">휴대전화번호<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">우편번호<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">주소<br><i class="icon fa-exclamation-circle red-700"></i></th>
			<th class="text-middle text-center">전화번호</th>	
       	</tr>       				
   	</thead>
	<tbody>
		<c:set var="table_sel_cnt" value="17" />
		<c:if test="${empty list}">
			<tr><td colspan="${table_sel_cnt}" class="text-center table-active">현재 신청한 지자체명단이 없습니다.</td></tr>	  
       	</c:if>
		<c:set var="isChkIcon" value="false" />
       	<c:forEach var="item" varStatus="status" items="${list}">
			
			<c:choose>
				<c:when test="${item.MBR_JOIN_YN eq 'N'}">
					<c:set var="tr_style_class_name" value="excel-style-newAdd"/>
				</c:when>
				<c:when test="${item.CHANGE_INFO eq 'delete'}">
					<c:set var="tr_style_class_name" value="excel-style-delete"/>
				</c:when>
				<c:when test="${not empty item.resultList and fn:length(item.resultList)>0}">
					<c:set var="tr_style_class_name" value="excel-style-change"/>
				</c:when>
				<c:otherwise>
					<c:set var="tr_style_class_name" value="excel-style-none"/>
				</c:otherwise>
			</c:choose>

			<c:if test="${tr_style_class_name eq 'excel-style-newAdd' || tr_style_class_name eq 'excel-style-change' || tr_style_class_name eq 'excel-style-none' || tr_style_class_name eq ''}">
			<tr class="excel ${tr_style_class_name}"  data-idx="${status.index }">
				<td class="text-middle text-center" colspan="2"><i class="icon fa-file-excel-o green-600"></i></td>
				<c:choose>
					<c:when test="${empty item.DTL_CD}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center"  data-empty="">
		          	 		<c:if test="${item.DTL_CD eq 'CIDN010200'}">낚시터</c:if>
		              		<c:if test="${item.DTL_CD eq 'CIDN010300'}">낚시어선</c:if>
		          	 	</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.SIDO_NM}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.SIDO_NM }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.SIGNGU_NM}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.SIGNGU_NM }</td>		              							
					</c:otherwise>
				</c:choose>
		        <td class="text-middle text-center" data-empty="">${item.REG_NUM_CD }</td>		              							
				<td class="text-middle text-center" data-empty="">${item.SHIP_CD }</td>
				<td class="text-middle text-center" data-empty="">${item.DTL_NM }</td>
				<c:choose>
					<c:when test="${empty item.DTL_LICENSE_NM}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.DTL_LICENSE_NM }</td>		              							
					</c:otherwise>
				</c:choose>		              								              							
				<c:choose>
					<c:when test="${item.DTL_LICENSE_NM eq '법인업자' }">
						<c:choose>
							<c:when test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO' or item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER' }">
				          	 	<td class="text-middle text-center" data-empty="<c:if test="${empty item.MBR_EDU_RSPNBER_TYPE }">Y</c:if>"
				          	 		<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">대표자</c:if>
		              				<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">교육책임자</c:if>
				          	 	</td>		              												
							</c:when>
							<c:otherwise>
								<td class="text-middle text-center" data-empty=""><i class="icon fa-exclamation-circle red-700"></i></td>
								<c:set var="isChkIcon" value="true" />						
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<td class="text-middle text-center" data-empty=""></td>		
					</c:otherwise>
				</c:choose>
				<td class="text-middle text-center" data-empty="">${item.SHIP_LICENSE }</td>		              							
				<c:choose>
					<c:when test="${empty item.MBR_NM}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_NM }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_BIRTH}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_BIRTH }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_HP}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_HP }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_ZIPCD}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_ZIPCD }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_ADDR}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
						<c:set var="isChkIcon" value="true" />
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_ADDR }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_TEL}">
          				<td class="text-middle text-center" data-empty=""><!-- <i class="icon fa-times red-300"></i> --></td>		
          			</c:when>
          			<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_TEL }</td>		          						          				
          			</c:otherwise>
          		</c:choose>            							
			</tr>
			</c:if>
		
			<c:if test="${tr_style_class_name eq 'excel-style-delete'}" >
			<tr class="excel ${tr_style_class_name}"  data-idx="${status.index }">
				<td class="text-middle text-center"></td>
				<td class="text-middle text-center"></td>
				<c:choose>
					<c:when test="${empty item.DTL_CD}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">
		          	 		<c:if test="${item.DTL_CD eq 'CIDN010200'}">낚시터</c:if>
		              		<c:if test="${item.DTL_CD eq 'CIDN010300'}">낚시어선</c:if>
		          	 	</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.SIDO_NM}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.SIDO_NM }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.SIGNGU_NM}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.SIGNGU_NM }</td>		              							
					</c:otherwise>
				</c:choose>
				<td class="text-middle text-center" data-empty="">${item.REG_NUM_CD }</td>		              							
				<td class="text-middle text-center" data-empty="">${item.SHIP_CD }</td>
		        <td class="text-middle text-center" data-empty="">${item.DTL_NM }</td>	
		        <c:choose>
					<c:when test="${empty item.DTL_LICENSE_NM}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.DTL_LICENSE_NM }</td>		              							
					</c:otherwise>
				</c:choose>	              							
				<c:choose>
					<c:when test="${item.DTL_LICENSE_NM eq '법인업자' }">
						<c:choose>
							<c:when test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO' or item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER' }">
				          	 	<td class="text-middle text-center" data-empty="<c:if test="${empty item.MBR_EDU_RSPNBER_TYPE }">Y</c:if>"
				          	 		<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">대표자</c:if>
		              				<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">교육책임자</c:if>
				          	 	</td>		              												
							</c:when>
							<c:otherwise>
								<td class="text-middle text-center" data-empty=""><i class="icon fa-exclamation-circle red-700"></i></td>					
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<td class="text-middle text-center" data-empty=""></td>		
					</c:otherwise>
				</c:choose>
		        <td class="text-middle text-center" data-empty="">${item.SHIP_LICENSE }</td>		              							
				<c:choose>
					<c:when test="${empty item.MBR_NM}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_NM }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_BIRTH}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_BIRTH }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_HP}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_HP }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_ZIPCD}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_ZIPCD }</td>		              							
					</c:otherwise> 
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_ADDR}">
						<td class="text-middle text-center" data-empty="Y"><i class="icon fa-exclamation-circle red-700"></i></td>
					</c:when>
					<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_ADDR }</td>		              							
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty item.MBR_TEL}">
          				<td class="text-middle text-center" data-empty=""><!-- <i class="icon fa-times red-300"></i> --></td>		
          			</c:when>
          			<c:otherwise>
		          	 	<td class="text-middle text-center" data-empty="">${item.MBR_TEL }</td>		          						          				
          			</c:otherwise>
          		</c:choose>		              							
			</tr>
       		</c:if>
		    			
			<c:if test="${item.CHANGE_INFO eq 'delete' or fn:length(item.resultList)>0 or item.MBR_JOIN_YN eq 'N'}">
  				<tr class="list-group-item-danger bg-blue-grey-100">
  					<td></td>
  					<td class="text-middle" colspan="${table_sel_cnt}">
                   		<div class="radio-custom radio-default radio-inline">
                        	<input type="radio" class="btn-check-item" id="DEL_${status.index}_2" name="EUD_SN_D${status.index}" value="none"
	                          	data-eud-sn="${item.EUD_SN}" 
	                          	data-mbr-id="${item.MBR_ID }"
	                          	data-dtl-sn="${item.DTL_SN }" >
                          	<label for="DEL_${status.index}_2"><span class="font-weight-800">이 항목은 처리하지 않음222<c:if test="${item.CHANGE_INFO eq 'delete'}"> ( 전산정보를 그대로 유지함 )</c:if></span></label>
                        </div>
                  	</td>
  					<%-- <td class="text-middle text-center" colspan="${table_sel_cnt-2 }">
  						<!-- <span class="badge badge-outline badge-default">삭제하지 않음</span> -->&nbsp;
  					</td> --%>
  				</tr>	
			</c:if>
			
			<c:if test="${item.CHANGE_INFO eq 'delete'}">
				<tr class="list-group-item-danger">
  					<td></td>
  					<td class="text-middle" colspan="${table_sel_cnt}">
                     	<div class="radio-custom radio-default radio-inline">
                        	<input type="radio" class="btn-check-item" id="DEL_${status.index}_1" name="EUD_SN_D${status.index}" value="delete"
	                          	data-eud-sn="" 
	                          	data-mbr-id="${item.MBR_ID }" 
	                          	data-dtl-sn="${item.DTL_SN }" checked>
                          	<label for="DEL_${status.index}_1"><span class="font-weight-800">해당상세정보는 삭제함 ( 엑셀에서 포함되지 않은 정보가 전산상에 존재하므로 해당건은 전산에서 삭제함 )</span></label>
                        </div>
                  	</td>
  					<%-- <td class="text-middle text-left" colspan="${table_sel_cnt-2 }">
  						<span class="badge badge-outline badge-warning">삭제하기</span>
  					</td> --%>
  				</tr>	
  			</c:if>
			
			<!-- // start [지자체엑셀현행화:회원검증] 등록정보없음 // -->
       		<c:if test="${item.MBR_JOIN_YN eq 'N'}">
  				<tr class="list-group-item-info">
  					<td></td>
  					<td class="text-middle" colspan="${table_sel_cnt}">
                     	<div class="radio-custom radio-default radio-inline">
	                     	<input class="btn-check-newAdd btn-check-item" type="radio" id="NEW_${status.index}_1" name="EUD_SN_D${status.index}" value="newAdd"
	                          	data-eud-sn="${item.EUD_SN}" 
	                          	data-mbr-id="" 
	                          	data-dtl-sn="" checked>
	                        <label for="NEW_${status.index}_1"><span class="font-weight-800">해당정보는 신규대상자로 등록함</span></label>
                        </div>
                  	</td>
  					<%-- <td class="text-middle text-left" colspan="${table_sel_cnt-2 }">
  						<span class="badge badge-outline badge-info">신규대상자등록</span>
  					</td> --%>
  				</tr>	
			</c:if>
			<!-- // end [지자체엑셀현행화:회원검증] 등록정보없음 // -->
			
			<!-- // start [지자체엑셀현행화:회원인데 회원상세정보 없음] // -->
       		<c:if test="${item.CHANGE_INFO eq 'add'}">
       			<tr class="list-group-item-info bg-green-100">
  					<td></td>
  					<td class="text-middle" colspan="${table_sel_cnt}">
                     	<div class="radio-custom radio-default radio-inline">
	                     	<input class="btn-check-add btn-check-item" type="radio" id="ADD_${status.index}_1" name="EUD_SN_D${status.index}" value="add"
	                          	data-eud-sn="${item.EUD_SN}" 
	                          	data-mbr-id="${item.MBR_ID}" 
	                          	data-dtl-sn="" checked>
	                        <label for="ADD_${status.index}_1"><span class="font-weight-800">상세정보 추가함</span></label>
                        </div>
                  	</td>
  				</tr>
       		</c:if>
       		<!-- // end [지자체엑셀현행화:회원인데 회원상세정보 없음] // -->
       		
       		<c:forEach var="result" varStatus="status2" items="${item.resultList}">
       			<!-- 김현태  not empty result.MBR_HP 추가 -->
       			<c:if test="${not empty result.MBR_HP and 
                  ((result.MBR_NM eq item.MBR_NM and result.MBR_BIRTH eq item.MBR_BIRTH) or 
                  (result.MBR_NM eq item.MBR_NM and result.DTL_NM eq item.DTL_NM) or 
                  (result.MBR_BIRTH eq item.MBR_BIRTH and result.DTL_NM eq item.DTL_NM) or 
                  (result.MBR_HP eq item.MBR_HP))}">
       	
       				<c:choose>
       					<c:when test="${result.MBR_NM eq item.MBR_NM and result.MBR_BIRTH eq item.MBR_BIRTH and result.MBR_HP eq item.MBR_HP }">
       						<c:set var="badge_color" value="badge-danger"/>
       						<c:set var="badge_text" value="이름,생년월일,연락처 일치"/>
       					</c:when>
       					<c:when test="${result.MBR_NM eq item.MBR_NM and result.MBR_BIRTH eq item.MBR_BIRTH }">
       						<c:set var="badge_color" value="badge-danger"/>
       						<c:set var="badge_text" value="이름,생년월일 일치"/>
       					</c:when>
       					<c:when test="${result.MBR_NM eq item.MBR_NM and result.DTL_NM eq item.DTL_NM}">
       						<c:set var="badge_color" value="badge-info"/>
       						<c:set var="badge_text" value="이름 일치"/>
       					</c:when>
       					<c:when test="${result.MBR_BIRTH eq item.MBR_BIRTH and result.DTL_NM eq item.DTL_NM}">
       						<c:set var="badge_color" value="badge-success"/>
       						<c:set var="badge_text" value="생년월일 일치"/>
       					</c:when>
       					<c:when test="${result.MBR_HP eq item.MBR_HP}">
       						<c:set var="badge_color" value="badge-primary"/>
       						<c:set var="badge_text" value="연락처 일치"/>
       					</c:when>
       					<c:otherwise>
       						<c:set var="badge_color" value=""/>
       						<c:set var="badge_text" value="신규"/>
       					</c:otherwise>
       				</c:choose>
	       			
	       			<tr class="compare" data-parents-index="${status.index}" style="background-color:#eee;">
	       				<td class="text-middle text-center"></td>
	       				<td class="text-middle text-left">
	       					<div class="radio-custom radio-default ">
                            	<input class="btn-check-item" type="radio" id="CHNG_${status.index}_${status2.index}_1" name="EUD_SN_D${status.index}" value="change"
                            	data-eud-sn="${item.EUD_SN }" 
                            	data-mbr-id="${result.MBR_ID}" 
                            	data-dtl-sn="${result.DTL_SN}">
                            	<label for="CHNG_${status.index}_${status2.index}_1"><span class="badge font-weight-800 font-size-14">변경</span></label>
                          	</div>
                          	<div class="radio-custom radio-default ">
                            	<input class="btn-check-item" type="radio" id="CHNG_${status.index}_${status2.index}_2" name="EUD_SN_D${status.index}" value="add"
                            	data-eud-sn="${item.EUD_SN }" 
                            	data-mbr-id="${result.MBR_ID}"
                       			data-dtl-sn="" checked>
                            	<label for="CHNG_${status.index}_${status2.index}_2"><span class="badge font-weight-800 font-size-14">추가</span></label>
                          	</div>
                       	</td>                       	
		           
		          		<c:choose>
							<c:when test="${result.DTL_CD ne item.DTL_CD}">
								<td class="text-middle text-center red-800">
									<c:if test="${result.DTL_CD eq 'CIDN010200'}">낚시터</c:if>
				              		<c:if test="${result.DTL_CD eq 'CIDN010300'}">낚시어선</c:if>
								</td>
							</c:when>		 
							<c:otherwise>
				          	 	<td class="text-middle text-center">
				          	 		<c:if test="${result.DTL_CD eq 'CIDN010200'}">낚시터</c:if>
				              		<c:if test="${result.DTL_CD eq 'CIDN010300'}">낚시어선</c:if>
				          	 	</td>		              							
							</c:otherwise>
						</c:choose>
		          		<c:choose>
		          			<c:when test="${result.SIDO_NM ne item.SIDO_NM}">
				          	 	<td class="text-middle text-center red-800">${result.SIDO_NM }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.SIDO_NM }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		          		<c:choose>
		          			<c:when test="${result.SIGNGU_NM ne item.SIGNGU_NM}">
				          	 	<td class="text-middle text-center red-800">${result.SIGNGU_NM }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.SIGNGU_NM }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		          		<c:choose>
		          			<c:when test="${result.REG_NUM_CD ne item.REG_NUM_CD}">
				          	 	<td class="text-middle text-center red-800">${result.REG_NUM_CD }</td>		          						          						          				
		          			</c:when>
		          			<c:when test="${empty item.REG_NUM_CD}">
		          				<td class="text-middle text-center"><i class="icon fa-times red-300"></i></td>		
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.REG_NUM_CD }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		          		<c:choose>
		          			<c:when test="${result.SHIP_CD ne item.SHIP_CD}">
				          	 	<td class="text-middle text-center red-800">${result.SHIP_CD }</td>		          						          						          				
		          			</c:when>
		          			<c:when test="${empty item.SHIP_CD}">
		          				<td class="text-middle text-center"><i class="icon fa-times red-300"></i></td>		
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.SHIP_CD }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		          		<c:choose>
		          			<c:when test="${result.DTL_NM ne item.DTL_NM}">
				          	 	<td class="text-middle text-center red-800">${result.DTL_NM }</td>		          						          						          				
		          			</c:when>
		          			<c:when test="${empty item.DTL_NM}">
		          				<td class="text-middle text-center"><i class="icon fa-times red-300"></i></td>		
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.DTL_NM }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		              	<c:choose>
		          			<c:when test="${result.DTL_LICENSE_NM ne item.DTL_LICENSE_NM}">
				          	 	<td class="text-middle text-center red-800">${result.DTL_LICENSE_NM }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.DTL_LICENSE_NM }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		          		<c:choose>
		          			<c:when test="${result.MBR_EDU_RSPNBER_TYPE ne item.MBR_EDU_RSPNBER_TYPE}">
				          	 	<td class="text-middle text-center red-800">
				          	 		<c:if test="${result.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">대표자</c:if>
		              				<c:if test="${result.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">교육책임자</c:if>
				          	 	</td>		          						          						          				
		          			</c:when>
		          			<c:when test="${empty item.MBR_EDU_RSPNBER_TYPE}">
		          				<td class="text-middle text-center"><i class="icon fa-times red-300"></i></td>		
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">
				          	 		<c:if test="${result.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">대표자</c:if>
		              				<c:if test="${result.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">교육책임자</c:if>
				          	 	</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		              	<c:choose>
		          			<c:when test="${result.SHIP_LICENSE ne item.SHIP_LICENSE}">
				          	 	<td class="text-middle text-center red-800">${result.SHIP_LICENSE }</td>		          						          						          				
		          			</c:when>
		          			<c:when test="${empty item.SHIP_LICENSE}">
		          				<td class="text-middle text-center"><i class="icon fa-times red-300"></i></td>		
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.SHIP_LICENSE }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		              	<c:choose>
		          			<c:when test="${result.MBR_NM ne item.MBR_NM}">
				          	 	<td class="text-middle text-center red-800">${result.MBR_NM }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.MBR_NM }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		              	<c:choose>
		          			<c:when test="${result.MBR_BIRTH ne item.MBR_BIRTH}">
				          	 	<td class="text-middle text-center red-800">${result.MBR_BIRTH }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.MBR_BIRTH }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		              	<c:choose>
		          			<c:when test="${result.MBR_HP ne item.MBR_HP}">
				          	 	<td class="text-middle text-center red-800">${result.MBR_HP }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.MBR_HP }</td>
		          			</c:otherwise>
		          		</c:choose>
		              	<c:choose>
		          			<c:when test="${result.MBR_ZIPCD ne item.MBR_ZIPCD}">
				          	 	<td class="text-middle text-center red-800">${result.MBR_ZIPCD }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.MBR_ZIPCD }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		              	<c:choose>
		          			<c:when test="${result.MBR_ADDR ne item.MBR_ADDR}">
				          	 	<td class="text-middle text-center red-800">${result.MBR_ADDR }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.MBR_ADDR }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		              	<c:choose>
		          			<c:when test="${empty item.MBR_TEL}">
		          				<td class="text-middle text-center"><i class="icon fa-times red-300"></i></td>		
		          			</c:when>
		          			<c:when test="${result.MBR_TEL ne item.MBR_TEL}">
				          	 	<td class="text-middle text-center red-800">${result.MBR_TEL }</td>		          						          						          				
		          			</c:when>
		          			<c:otherwise>
				          	 	<td class="text-middle text-center">${result.MBR_TEL }</td>		          						          				
		          			</c:otherwise>
		          		</c:choose>
		       		</tr>
		       		<tr class="bg-grey-200">
		       			<td class="text-middle text-center border-top-0"></td>
	       				<td class="text-middle text-left border-top-0" colspan="${table_sel_cnt}">
	       					<span class="font-size-12 blue-grey-400">전산상에 </span><span class="badge badge-outline ${badge_color}">${badge_text}</span><span class="font-size-12 blue-grey-400"> 건이 확인되었습니다. 위 옵션 항목 선택시 전산정보에 엑셀자료를 추가(상세정보추가) 또는 변경(상세정보수정) 합니다.</span>  
	       				</td>
		       		</tr>
	       		</c:if>
       		</c:forEach>
       		
       		<c:if test="${item.CHANGE_INFO ne 'delete' and fn:length(item.resultList) <= 0}">
	       		<tr style="display:none;">
	       			<td colspan="${table_sel_cnt}">
		       			<input type="radio" class="btn-check-item" name="EUD_SN_DB${status.index}" value="db" 
	                       	data-eud-sn="${item.EUD_SN }" 
	                       	data-mbr-id="${item.MBR_ID }" 
	                       	data-dtl-sn="${item.DTL_SN }" checked>
                       </td>
	       		</tr>
       		</c:if>
       		
       	</c:forEach>
	</tbody>
</table>
</form:form>

<%-- <c:choose> --%>
<%-- 	<c:when test="${isChkIcon eq true}"> --%>
<!-- 		<div class="text-middle text-center"> -->
<!-- 			<h5><i class="icon fa-exclamation-circle red-700"></i>필수 입력사항을 확인 후 재업로드 해주세요.</h5>			 -->
<!-- 		</div>	 -->
<!-- 		<div class="text-center"> -->
<!-- 			<button type="button" id="btn-upload" onclick="location.href='/eduadm/member/loc_gov_upload.do'" class="btn btn-primary">재업로드 하러가기</button> -->
<!-- 		</div> -->
<%-- 	</c:when> --%>
<%-- 	<c:otherwise> --%>
<!-- 		<div class="text-center"> -->
<!-- 			<button type="button" id="btn-submit" class="btn btn-primary">승인요청</button> -->
<!-- 		</div> -->
<%-- 	</c:otherwise> --%>
<%-- </c:choose> --%>

	<div id="btn_area_upload" style="<c:if test="${isChkIcon eq false}">display:none</c:if>">
		<div class="text-middle text-center">
			<h5><i class="icon fa-exclamation-circle red-700"></i>필수 입력사항을 확인 후 재업로드 해주세요.</h5>			
		</div>	
		<div class="text-center">
			<button type="button" id="btn-upload" onclick="location.href='/eduadm/member/loc_gov_upload.do'" class="btn btn-primary">재업로드 하러가기</button>
		</div>
	</div>
	
	<div id="btn_area_submit" style="<c:if test="${isChkIcon eq true}">display:none</c:if>">
		<div class="text-center">
			<button type="button" id="btn-submit" class="btn btn-primary">승인요청</button>
		</div>
	</div>

<!-- table:end -->

<script>
$("#btn-submit").on("click", function(){
	
	var radio = $(".btn-check-item");
	var check = "";//true, false
	
	var jsonObj = {
		newAdd:[],
		change:[],
		add:[],
		del:[],
		db:[],
		changeNone:[],
	};
	var dataArry = [];
	
	for(var i = 0; i < radio.length; i++){

		check = radio.eq(i).is(":checked");
				
		if(check){
			//console.log("value : " + radio.eq(i).val());
			
			var dataObj = {};
			dataObj.eud_sn = radio.eq(i).attr("data-eud-sn");
			dataObj.mbr_id = radio.eq(i).attr("data-mbr-id");
			dataObj.dtl_sn = radio.eq(i).attr("data-dtl-sn");
			
			switch(radio.eq(i).val()){
				case "newAdd": 
					jsonObj.newAdd.push(dataObj); break;
				case "change":
					jsonObj.change.push(dataObj); break;
				case "add": 
					jsonObj.add.push(dataObj); break;
				case "delete": 
					jsonObj.del.push(dataObj); break;
				case "db": 
					jsonObj.db.push(dataObj); break;
				case "none": 
					jsonObj.changeNone.push(dataObj); break;
				default : break;
			}
			
			var json_data = JSON.stringify(jsonObj);
			//console.log(json_data);
		}
	}
	
	var form = document.getElementById("uploadForm");
	//console.log(uploadForm);
	form.action.value = "/eduadm/member/loc_gov_request.do";
	form.json_data.value = json_data;
	
	
	$.ajax({
		type: "POST",
		url : "/eduadm/member/loc_gov_request.do",
		data: $('#uploadForm').serialize(),
		//dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: false,
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
		}
	})
	.done(function(data, status, xhr){
		
		if(data.error == 0){
			alertify.alert(data.msg, function(){
				location.reload();
			});
		} else{
			alertify.alert(data.msg);
		}
	})
	.always(function(){
		//console.log("always");
		clickRequestLockStop();
	})
	.fail(function(){
		//console.log("fail");
		
	})
	
	
});

/*
 var beforeChecked = -1;
$(".btn-check-item").on("click", function(){//라디오 버튼 해제
	
	var name = $(this).attr("name");
	var radio = $("input:radio[name='" + name + "']");
    
    var index = radio.index(this);
    if(beforeChecked == index) {
	    beforeChecked = -1;
	    $(this).prop("checked", false);
    } else {
   	 	beforeChecked = index;
    }
	
});
*/

$(".btn-check-item").on("click", function(){
	var isUploadLock = false;
	var value = $(this).val();	
	$(".excel-style-delete").each(function(idx){
		var delObj = $(this);
		var idxObj = delObj.attr('data-idx');
		var tdsObj = $(this).find('td');
		var isEmpty = false;
		for(var i=2; i<tdsObj.length; i++){
			var tdObj = tdsObj.eq(i);
			//if(tdObj.attr('data-empty')) isEmpty = true;
		}
		var check = $('#DEL_'+idxObj+'_2').is(":checked");
		if(isEmpty && check) {
			isUploadLock=true;
		}
		
	});	
	$(".excel-style-change").each(function(idx){
		var chgObj = $(this);
		var idxObj = chgObj.attr('data-idx');
		var tdsObj = $(this).find('td');
		var isEmpty = false;
		for(var i=2; i<tdsObj.length; i++){
			var tdObj = tdsObj.eq(i);
			if(tdObj.attr('data-empty')) isEmpty = true;
		}
		var check = $('#DEL_'+idxObj+'_2').is(":checked");
		if(isEmpty && !check) {
			isUploadLock=true;
		}
	});	
	$(".excel-style-newAdd").each(function(idx){
		var chgObj = $(this);
		var idxObj = chgObj.attr('data-idx');
		var tdsObj = $(this).find('td');
		var isEmpty = false;
		for(var i=2; i<tdsObj.length; i++){
			var tdObj = tdsObj.eq(i);
			if(tdObj.attr('data-empty')) isEmpty = true;
		}
		var check = $('#DEL_'+idxObj+'_2').is(":checked");
		if(isEmpty && !check) {
			isUploadLock=true;
		}
	}); 
	$(".excel-style-none").each(function(idx){
		var chgObj = $(this);
		var idxObj = chgObj.attr('data-idx');
		var tdsObj = $(this).find('td');
		var isEmpty = false;
		for(var i=2; i<tdsObj.length; i++){
			var tdObj = tdsObj.eq(i);
			if(tdObj.attr('data-empty')) isEmpty = true;
		}
		if(isEmpty) {
			isUploadLock=true;
		}
	}); 	
	if(!isUploadLock){
		$("#btn_area_submit").show();
 		$("#btn_area_upload").hide();
	} else {
		$("#btn_area_upload").show();
		$("#btn_area_submit").hide();
	}	
});


</script>
