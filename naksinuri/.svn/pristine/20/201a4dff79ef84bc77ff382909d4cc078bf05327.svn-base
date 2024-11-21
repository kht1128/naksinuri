<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>


<form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>

<style>
	/* .table td {border-top: 1px solid black !important;} */
	.list-group-item-info {border-left: 1px solid #bee5eb; border-right: 1px solid #bee5eb;}
	.list-group-item-success {border-left: 1px solid #c3e6cb; border-right: 1px solid #c3e6cb;}
	.list-group-item-warning {border-left: 1px solid #ffeeba; border-right: 1px solid #ffeeba;}
	/* .list-group-item-info td {border-bottom: 1px solid black;} */
	
	/* $(".list-group-item-info").prev().css("border", "1px solid #bee5eb");
	$(".list-group-item-info").prev().children("td").css("border-top", "3px solid #bee5eb");
	
	$(".list-group-item-success").prev().css("border", "1px solid #c3e6cb");
	$(".list-group-item-success").prev().children("td").css("border-top", "3px solid #c3e6cb");
	
	$(".list-group-item-warning").prev().css("border", "1px solid #ffeeba");
	$(".list-group-item-warning").prev().children("td").css("border-top", "3px solid #ffeeba"); */
	
	.excel-style-newAdd td {border-top: 3px solid #0bb2d4;}
	.excel-style-change td {border-top: 3px solid #eb6709;}
	.excel-style-add td {border-top: 3px solid #28a745;}
	.excel-style-delete td {border-top: 3px solid #526069;}
	
	.excel-style-newAdd {border: 1px solid #bee5eb;}
	.excel-style-change {border: 1px solid #ffeeba;}
	.excel-style-add {border: 1px solid #c3e6cb;}
	.excel-style-delete {border: 1px solid #76838F;}
	
	.compare.list-group-item-info td._bottom {border-bottom: 3px solid #0bb2d4;}
	.compare.list-group-item-warning td._bottom {border-bottom: 3px solid #eb6709;}
	.compare.list-group-item-success td._bottom {border-bottom: 3px solid #28a745;}
	.compare.list-group-item-danger td._bottom {border-bottom: 3px solid #526069; background-color:#A3AFB7;}
</style>

<div class="example-tooltip">
	<div class="tooltip bs-tooltip-top tooltip-info" role="tooltip">
		<div class="arrow"></div>
		<div class="tooltip-inner">전체 ${list_size }건</div>
	</div>
</div>

<c:choose>
	<c:when test="${MASTER_MBR_POSITION_CD eq 'PCD0005' or MASTER_MBR_POSITION_CD eq 'PCD0006' or MASTER_MBR_POSITION_CD eq 'PCD0007'}">
		<c:set var="isMaster" value="true"/>
		<c:set var="addOptionCheckBox" value=""/>
		<c:if test="${eduExcelUploadVO.CONFM_ST eq 'Y'}">
			<c:set var="addOptionCheckBox" value="disabled"/>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:set var="isMaster" value="false"/>
		<c:set var="addOptionCheckBox" value="disabled"/>
	</c:otherwise>
</c:choose>

<!-- table:start -->
<form:form commandName="" id="uploadForm" name="uploadForm">
<input type="hidden" name="json_data">
<input type="hidden" name="chkedIds" value="" />
<input type="hidden" name="chkedMbrModifyIds" value="" />
<input type="hidden" name="EU_SN" value="${EU_SN}">

<table id="datalist" class="table table-hover footable footable-paging footable-paging-center">
	<colgroup>
		<col width="40px"/>
		<col width=""/>
		<col width="6%"/>
		<col width="100"/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width="10%"/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
	</colgroup>
   	<thead>
   		<tr>
   			<th class="text-middle text-center hide-cell-exceldown">
				<span class="checkbox-custom checkbox-primary">
					<input class="selectable-item btn-check-item-all" type="checkbox" data-status="" ${addOptionCheckBox} ><label></label>
				</span>
			</th>
			<th class="text-middle text-center" colspan="2">처리구분</th>
			<th class="text-middle text-center">낚시터/어선 구분</th>
			<th class="text-middle text-center">시도</th>
			<th class="text-middle text-center">시군구</th>
			<th class="text-middle text-center">허가(등록)/<br>신고번호</th>
			<th class="text-middle text-center">어선번호</th>
			<th class="text-middle text-center">낚시터명/어선명</th>
			<th class="text-middle text-center">대상구분(개인업자,법인업자,선원)</th>
			<th class="text-middle text-center">법인업자구분(대표자/교육책임자)</th>	
			<th class="text-middle text-center">해기사자격증</th>
			<th class="text-middle text-center">성명(법인명)</th>
			<th class="text-middle text-center">생년월일(법인등록번호)</th>
			<th class="text-middle text-center">휴대전화번호</th>
			<th class="text-middle text-center">우편번호</th>
			<th class="text-middle text-center">주소</th>
			<th class="text-middle text-center">전화번호</th>
			<th class="text-middle text-center">비고</th>	
       	</tr>       				
   	</thead>
	<tbody>
		<c:set var="table_cell_count" value="19"/>
		<c:if test="${empty list}">
			<tr><td colspan="${table_cell_count }" class="text-center table-active">현재 신청한 지자체명단이 없습니다.</td></tr>	  
       	</c:if>
       	<c:forEach var="item" varStatus="status" items="${list}">
       		
	       	<c:choose>
	       		<c:when test="${item.CHANGE_INFO eq 'newAdd'}"><c:set var="tr_style_class_name" value="excel-style-newAdd"/></c:when>
	   			<c:when test="${item.CHANGE_INFO eq 'change'}"><c:set var="tr_style_class_name" value="excel-style-change"/></c:when>
	   			<c:when test="${item.CHANGE_INFO eq 'add'}"><c:set var="tr_style_class_name" value="excel-style-add"/></c:when>
	   			<c:when test="${item.CHANGE_INFO eq 'delete'}"><c:set var="tr_style_class_name" value="excel-style-delete"/></c:when>
	   			<c:otherwise><c:set var="tr_style_class_name" value=""/></c:otherwise>
	   		</c:choose>
       	
       	<%-- <input type="hidden" name="MBR_NM_${status.index}" value="${item.MBR_NM }"> --%>
       		
			<tr class="excel ${tr_style_class_name }">
				<td class="text-middle hide-cell-exceldown">
					<c:if test="${item.CHANGE_INFO ne 'none'}">
						<c:set var="isAutoChecker" value=""/>
						<c:choose>
							<c:when test="${not empty item.EUD_CONFM_ST and item.EUD_CONFM_ST eq 'Y'}">
								<c:set var="isAutoChecker" value="checked"/>
							</c:when>
							<c:otherwise>
								<%-- <c:if test="${isMaster eq true and item.CHANGE_INFO ne 'newAdd' and item.CHANGE_INFO ne 'change' and item.CHANGE_INFO ne 'add' and item.CHANGE_INFO ne 'delete'}">
									<c:set var="isAutoChecker" value="checked"/>
								</c:if> --%>
								<c:if test="${isMaster eq true}">
									<c:set var="isAutoChecker" value="checked"/>
								</c:if>
							</c:otherwise>						
						</c:choose>
						<span class="checkbox-custom checkbox-primary">
							<input class="selectable-item btn-check-item" type="checkbox" name="checkbox_item" value="${item.EUD_SN}" ${isAutoChecker} ${addOptionCheckBox} ><label></label>
						</span>
					</c:if>
             	</td>
             	<td class="text-middle text-center">
             		<i class="icon fa-file-excel-o green-600"></i>
             	</td>
             	<td class="text-middle text-center">
             		<c:choose>
		       			<c:when test="${item.CHANGE_INFO eq 'newAdd'}">
		       				<span class="badge badge-outline badge-info">신규회원등록건</span>
		       			</c:when>
		       			<c:when test="${item.CHANGE_INFO eq 'change'}">
		       				<span class="badge badge-outline badge-warning">회원상세정보 변경건</span>	
		       			</c:when>
		       			<c:when test="${item.CHANGE_INFO eq 'add'}">
		       				<span class="badge badge-outline badge-success">회원상세정보 신규건</span>
		       			</c:when>
		       			<c:when test="${item.CHANGE_INFO eq 'delete'}">       				
		       				<span class="badge badge-outline badge-danger">회원상세정보 삭제건</span>
		       			</c:when>
		       			<c:when test="${item.CHANGE_INFO eq 'none'}">       				
		       				<span class="badge blue-grey-400">취소건(처리안함)</span> 
		       			</c:when>
		       			<c:otherwise>
		       				<span class="badge badge-outline badge-dark">기존과 동일함</span>
		       			</c:otherwise>
		       		</c:choose>
             	</td>
             	<td class="text-middle text-center">
             		<c:if test="${item.DTL_CD eq 'CIDN010200'}">낚시터</c:if>
		            <c:if test="${item.DTL_CD eq 'CIDN010300'}">낚시어선</c:if>
             	</td>
             	<td class="text-middle text-center">${item.SIDO_NM }</td>
             	<td class="text-middle text-center">${item.SIGNGU_NM }</td>
         		<td class="text-middle text-center">${item.REG_NUM_CD }</td>
         		<td class="text-middle text-center">${item.SHIP_CD }</td>
         	 	<td class="text-middle text-center">${item.DTL_NM }</td>
         		<td class="text-middle text-center">${item.DTL_LICENSE_NM }</td>					           	
             	<td class="text-middle text-center">
             		<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">대표자</c:if>
		            <c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">교육책임자</c:if>
             	</td>
             	<td class="text-middle text-center">${item.SHIP_LICENSE }</td>
             	<td class="text-middle text-center">${item.MBR_NM }</td>
             	<td class="text-middle text-center">${item.MBR_BIRTH }</td>
             	<td class="text-middle text-center">${item.MBR_HP }</td>
             	<td class="text-middle text-center">${item.MBR_ZIPCD }</td>
             	<td class="text-middle text-center">${item.MBR_ADDR }</td>
             	<td class="text-middle text-center">${item.MBR_TEL }</td>
             	<td class="text-middle text-center">
             		<c:choose>
		       			<c:when test="${item.CHANGE_INFO eq 'newAdd'}">
		       			</c:when>
		       			<c:when test="${item.CHANGE_INFO eq 'change'}">	
		       			</c:when>
		       			<c:when test="${item.CHANGE_INFO eq 'add'}">
		       			</c:when>
		       			<c:when test="${item.CHANGE_INFO eq 'delete'}">       				
		       			</c:when>
		       			<c:when test="${item.CHANGE_INFO eq 'none'}">       		
		       			</c:when>
		       			<c:otherwise>
		       				<a href="#;" class="btn btn-dark btn-xs "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
		       			</c:otherwise>
		       		</c:choose>
             	</td>
      		</tr>
      		
       		<c:choose>
       			<c:when test="${item.CHANGE_INFO eq 'newAdd'}">
				
       			</c:when>
       			<c:when test="${item.CHANGE_INFO eq 'change'}">
       				<c:forEach var="result" varStatus="status2" items="${item.resultList}">
	       				<tr class="compare list-group-item-warning" data-parents-index="${status.index}" data-change-info="change">
			              	<td class="text-middle text-center" >&nbsp;</td>
			              	<td class="text-middle text-center" >&nbsp;</td>	
			              	<td class="text-middle text-center" >
			              		<span class="badge badge-outline badge-dark">기존회원상세정보</span>
			              		<!-- <br><span class="font-size-12 red-600">현재 전산정보가 위 엑셀자료로 변경 됩니다.</span>  -->
			              	</td>
			             	<td class="text-middle text-center">
             					<c:if test="${item.DTL_CD eq 'CIDN010200'}">낚시터</c:if>
		           				<c:if test="${item.DTL_CD eq 'CIDN010300'}">낚시어선</c:if>
             				</td>
			              	<td class="text-middle text-center <c:if test="${item.SIDO_NM ne result.SIDO_NM}">red-600</c:if>">${result.SIDO_NM }</td>
			              	<td class="text-middle text-center <c:if test="${item.SIGNGU_NM ne result.SIGNGU_NM}">red-600</c:if>">${result.SIGNGU_NM }</td>
			          		<td class="text-middle text-center <c:if test="${item.REG_NUM_CD ne result.REG_NUM_CD}">red-600</c:if>">${result.REG_NUM_CD }</td>
			          		<td class="text-middle text-center <c:if test="${item.SHIP_CD ne result.SHIP_CD}">red-600</c:if>">${result.SHIP_CD }</td>
			          	 	<td class="text-middle text-center <c:if test="${item.DTL_NM ne result.DTL_NM}">red-600</c:if>">${result.DTL_NM }</td>
			          		<td class="text-middle text-center <c:if test="${item.DTL_LICENSE_CD ne result.DTL_LICENSE_CD}">red-600</c:if>">
			          			<c:choose>
			          				<c:when test="${result.DTL_LICENSE_CD eq 'CIDL00001'}">개인업자</c:when>
			          				<c:when test="${result.DTL_LICENSE_CD eq 'CIDL00002'}">법인업자</c:when>
			          				<c:when test="${result.DTL_LICENSE_CD eq 'CIDL00003'}">해기사면허 소지 선원</c:when>
			          				<c:when test="${result.DTL_LICENSE_CD eq 'CIDL00004'}">해기사면허 미소지 선원</c:when>
			          				<c:when test="${result.DTL_LICENSE_CD eq 'CIDL00005'}">해기사면허 소지 선장</c:when>
			          				<c:when test="${result.DTL_LICENSE_CD eq 'CIDL00006'}">해기사면허 미소지 선장</c:when>
			          				<c:when test="${result.DTL_LICENSE_CD eq 'CIDL00008'}">해기사면허 소지 낚시어선업자</c:when>
			          				<c:when test="${result.DTL_LICENSE_CD eq 'CIDL00009'}">해기사면허 미소지 낚시어선업자</c:when>
			          				<c:otherwise></c:otherwise>
			          			</c:choose>
			          		<td class="text-middle text-center">
             					<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">대표자</c:if>
		            			<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">교육책임자</c:if>
             				</td>
			              	<td class="text-middle text-center <c:if test="${item.SHIP_LICENSE ne result.SHIP_LICENSE}">red-600</c:if>">${result.SHIP_LICENSE }</td>
			              	<td class="text-middle text-center <c:if test="${item.MBR_NM ne result.MBR_NM}">red-600</c:if>">${result.MBR_NM }</td>
			              	<td class="text-middle text-center <c:if test="${item.MBR_BIRTH ne result.MBR_BIRTH}">red-600</c:if>">${result.MBR_BIRTH }</td>
			              	<td class="text-middle text-center <c:if test="${item.MBR_HP ne result.MBR_HP}">red-600</c:if>">${result.MBR_HP }</td>
			              	<td class="text-middle text-center <c:if test="${item.MBR_ZIPCD ne result.MBR_ZIPCD}">red-600</c:if>">${result.MBR_ZIPCD }</td>
			              	<td class="text-middle text-center <c:if test="${item.MBR_ADDR ne result.MBR_ADDR}">red-600</c:if>">${result.MBR_ADDR }</td>
			              	<td class="text-middle text-center <c:if test="${item.MBR_TEL ne result.MBR_TEL}">red-600</c:if>">${result.MBR_TEL }</td>
			              	<td class="text-middle text-center">
			              		<a href="#;" class="btn btn-dark btn-xs "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
			              	</td> 
			       		</tr>
			       		<tr class="compare list-group-item-warning">
			       			<td class="text-middle text-center _bottom border-top-0" colspan="2">&nbsp;</td>
			       			<td class="text-middle text-left _bottom border-top-0" colspan="17">			
			       				<span class="font-size-12 red-600">위 항목을 체크하시면 현재 <span class="badge badge-outline badge-dark">기존회원정보</span>가 위 <span class="badge badge-outline badge-warning">회원상세정보 변경건</span> 정보로 변경(교체) 됩니다.</span>
			       				<span class="checkbox-custom checkbox-primary">
				       				<input class="ml-20 btn-check-item-mbrmodify" type="checkbox" id="MBR_CHNG_${status.index}_${status2.index}" name="checkbox_item_mbrmodify" value="${item.EUD_SN}" ${addOptionCheckBox}>
			                        <label for="MBR_CHNG_${status.index}_${status2.index}"><span class="font-size-12 font-weight-800">이 항목을 선택하면 변경시 회원정보(이름,생년월일,연락처,주소,우편번호,일반전화)도 함께 반영됩니다.</span></label>
		                        </span>
			              	</td>
			       		</tr>
		       		</c:forEach>
       			</c:when>
       			<c:when test="${item.CHANGE_INFO eq 'add'}">
       				<c:forEach var="result" varStatus="status2" items="${item.resultList}">
	       				<tr class="compare list-group-item-success" data-parents-index="${status.index}" data-change-info="add">
			       			<td class="text-middle text-center" >&nbsp;</td>	
			              	<td class="text-middle text-center" colspan="2">
			              		<span class="badge badge-outline badge-dark">기존회원정보</span>	
			              		<!-- <br><span class="font-size-12 red-600">현재 전산정보에 위 엑셀자료가 추가 됩니다.</span> -->
			              	</td>
			              	<td class="text-middle text-center" colspan="5">&nbsp;</td>
			              	<td class="text-middle text-center">${result.DTL_NM }</td>
			          		<td class="text-middle text-center">${result.DTL_LICENSE_NM }</td>					           	
			              	<td class="text-middle text-center">
			              		<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">대표자</c:if>
		            			<c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">교육책임자</c:if>
			              	</td>
			              	<td class="text-middle text-center">${result.SHIP_LICENSE }</td>
			              	<c:choose>
			          			<c:when test="${result.MBR_NM ne item.MBR_NM}">
					          	 	<td class="text-middle text-center red-800">${result.MBR_NM }</td>		          						          						          				
			          			</c:when>
			          			<c:otherwise>
					          	 	<td class="text-middle text-center">${result.MBR_NM } </td>
			          			</c:otherwise>
			          		</c:choose>
			              	<c:choose>
			          			<c:when test="${result.MBR_BIRTH ne item.MBR_BIRTH}">
					          	 	<td class="text-middle text-center red-800">${result.MBR_BIRTH }</td>		          						          						          				
			          			</c:when>
			          			<c:otherwise>
					          	 	<td class="text-middle text-center">${result.MBR_BIRTH } </td>
			          			</c:otherwise>
			          		</c:choose>
			              	<c:choose>
			          			<c:when test="${result.MBR_HP ne item.MBR_HP}">
					          	 	<td class="text-middle text-center red-800">${result.MBR_HP }</td>		          						          						          				
			          			</c:when>
			          			<c:otherwise>
					          	 	<td class="text-middle text-center">${result.MBR_HP } </td>
			          			</c:otherwise>
			          		</c:choose>
			              	<c:choose>
			          			<c:when test="${result.MBR_ZIPCD ne item.MBR_ZIPCD}">
					          	 	<td class="text-middle text-center red-800">${result.MBR_ZIPCD }</td>		          						          						          				
			          			</c:when>
			          			<c:otherwise>
					          	 	<td class="text-middle text-center">${result.MBR_ZIPCD } </td>
			          			</c:otherwise>
			          		</c:choose>
			              	<c:choose>
			          			<c:when test="${result.MBR_ADDR ne item.MBR_ADDR}">
					          	 	<td class="text-middle text-center red-800">${result.MBR_ADDR }</td>		          						          						          				
			          			</c:when>
			          			<c:otherwise>
					          	 	<td class="text-middle text-center">${result.MBR_ADDR } </td>
			          			</c:otherwise>
			          		</c:choose>
			              	<c:choose>
			          			<c:when test="${result.MBR_TEL ne item.MBR_TEL}">
					          	 	<td class="text-middle text-center red-800">${result.MBR_TEL }</td>		          						          						          				
			          			</c:when>
			          			<c:otherwise>
					          	 	<td class="text-middle text-center">${result.MBR_TEL } </td>
			          			</c:otherwise>
			          		</c:choose>
			              	<td class="text-middle text-center">
			              		<a href="#;" class="btn btn-dark btn-xs "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
			              	</td>
			       		</tr>
			       		<tr class="compare list-group-item-success">
			       			<td class="text-middle text-center _bottom border-top-0" colspan="2">&nbsp;</td>
			       			<td class="text-middle text-left _bottom border-top-0" colspan="17">			              		
			              		<span class="font-size-12 red-600">위 항목을 체크하시면 <span class="badge badge-outline badge-success">회원상세정보 신규건</span>정보가 <span class="badge badge-outline badge-dark">기존회원정보</span>에 회원상세정보로 추가 됩니다.</span>
			              		<span class="checkbox-custom checkbox-primary">
				       				<input class="ml-20 btn-check-item-mbrmodify" type="checkbox" id="MBR_CHNG_${status.index}_${status2.index}" name="checkbox_item_mbrmodify" value="${item.EUD_SN}" ${addOptionCheckBox}>
			                        <label for="MBR_CHNG_${status.index}_${status2.index}"><span class="font-size-12 font-weight-800">이 항목을 선택하면 추가시 회원정보(이름,생년월일,연락처,주소,우편번호,일반전화)도 함께 반영됩니다.</span></label>
		                        </span>
			              	</td>
			       		</tr>
		       		</c:forEach>
       			</c:when>
       			<c:when test="${item.CHANGE_INFO eq 'delete'}">       				
					<tr class="compare list-group-item-danger">
		       			<td class="text-middle text-center _bottom border-top-0" colspan="2">&nbsp;</td>
		       			<td class="text-middle text-left _bottom border-top-0" colspan="17">			
		       				<span class="font-size-12 red-600">회원상세정보가 하나도 없을 경우 회원정보는 사용안함 처리됩니다.</span> 
		              	</td>
		       		</tr>
       			</c:when>
       			<c:when test="${item.CHANGE_INFO eq 'none'}">
       			
       			</c:when>
       			<c:otherwise>

       			</c:otherwise>
       		</c:choose>
       		
       	</c:forEach>
	</tbody>
</table>
</form:form>

<c:if test="${eduExcelUploadVO.CONFM_ST eq 'N' and MASTER_MBR_POSITION_CD ne 'PCD0003'}">
	<div class="text-center p-10">
		<p class="text-center red-600 font-weight-800 font-size-14">
			* 선택 된 항목의 정보에 한하여 추가,삭제,변경 됩니다.
			<br>* 선택하지 않은 항목은 반영되지 않습니다.		
		</p>
		<button type="button" id="btn-submit" class="btn btn-primary">선택한 항목 모두 적용하기</button>
	</div>
</c:if>
<!-- table:end -->

<script>
$(function(){
	/* $(".list-group-item-info").prev().css("border", "1px solid #bee5eb");
	$(".list-group-item-info").prev().children("td").css("border-top", "3px solid #bee5eb");
	
	$(".list-group-item-success").prev().css("border", "1px solid #c3e6cb");
	$(".list-group-item-success").prev().children("td").css("border-top", "3px solid #c3e6cb");
	
	$(".list-group-item-warning").prev().css("border", "1px solid #ffeeba");
	$(".list-group-item-warning").prev().children("td").css("border-top", "3px solid #ffeeba"); */
})
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
$("#btn-submit").on("click", function(){
	var chkcnt = 0;
	var chkedIds = "";
	var chkedMbrModifyIds = "";
	$("input[name=checkbox_item]:checked").each(function() {
		if(chkedIds.length!=0) chkedIds+=",";
		chkedIds+=$(this).val();
		chkcnt++;
	});
	if(chkedIds.length==0) {
		alertify.alert("적용할 항목을 선택하세요.");
		return;
	}
	
	$("input[name=checkbox_item_mbrmodify]:checked").each(function() {
		if(chkedMbrModifyIds.length!=0) chkedMbrModifyIds+=",";
		chkedMbrModifyIds+=$(this).val();
	});	
	
	
	alertify.confirm("선택한 " + chkcnt+" 개 항목을 반영하시겠습니까?", function(){
		
		var form = document.getElementById("uploadForm");
		form.chkedIds.value = chkedIds;
		form.chkedMbrModifyIds.value = chkedMbrModifyIds;
		form.action = '';
		
		$.ajax({
			type: "POST",
			url : "/eduadm/member/loc_gov_request_act.do",
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
			},
		})
		.done(function(data, status, xhr){
			if(data.error == 0){
				alertify.alert(data.msg, function(){
					//location.reload();
					location.href = '/eduadm/member/loc_gov_adm_upload.do';
				});
			} else {
				alertify.alert(data.msg,function(){
					//document.dtlListForm.submit();
					location.href = '/eduadm/member/loc_gov_adm_upload.do';
				});
			}
		})
		.always(function(){
			//console.log("always");
			clickRequestLockStop();
		})
		.fail(function(){
			//console.log("fail");
			clickRequestLockStop();
		})
	});
});
function clk_mbr_view(mbr_id) {
	var form = document.getElementById('ajaxMbrViewForm');
	form.MBR_ID.value = mbr_id;
	$.ajax({
		type:"POST",
		url :'/eduadm/member/view.do',
		data:$('#ajaxMbrViewForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#admPublicPanelLayer").html(data);
			$("#admPublicPanelLayer").show();
		},
		complete : function() {
			//console.log('complete!');
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});
}
</script>
