<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>

<form:form commandName="eduMemberVO" id="eduMemberVOFormTotal${CUSTOM_UNIQ_KEY}" name="eduMemberVOFormTotal${CUSTOM_UNIQ_KEY}" method="post">
<input type="hidden" name="CALL_MBR_HP" value=""/>
<input type="hidden" name="MBR_HP" value=""/>
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="IS_JOIN_MBR" value=""/>
<input type="hidden" name="CUSTOM_UNIQ_KEY" value="${CUSTOM_UNIQ_KEY}"/>
</form:form>

<form:form commandName="ctiCallHistoryVO" id="ctiCallhstryListForm${CUSTOM_UNIQ_KEY}" name="ctiCallhstryListForm${CUSTOM_UNIQ_KEY}" method="post">
<input type="hidden" name="MBR_HP" value=""/>
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="IS_JOIN_MBR" value=""/>
<input type="hidden" name="CUSTOM_UNIQ_KEY" value="${CUSTOM_UNIQ_KEY}"/>
<input type="hidden" name="pageIndex" value=""/>
</form:form>

<form:form commandName="ctiCallHistoryVO" id="ctiCurriculumListForm${CUSTOM_UNIQ_KEY}" name="ctiCurriculumListForm${CUSTOM_UNIQ_KEY}" method="post">
<input type="hidden" name="MBR_HP" value=""/>
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="IS_JOIN_MBR" value=""/>
<input type="hidden" name="LOCK_ST" value="0" />
<input type="hidden" name="USE_ST" value="1" />
<input type="hidden" name="CUSTOM_UNIQ_KEY" value="${CUSTOM_UNIQ_KEY}"/>
</form:form>


	<style>
	.custom_inputform_table.table th {
	    font-weight: bold;
	    background: #f0f4f5;
	}
	</style>
	
	<c:choose>
		<c:when test="${not empty HCALL_SN}"><%-- 변경하기 --%>
			<c:set var="panelStyleType1" value="panel-warning " />
			<c:set var="panelStyleType2" value="bg-orange-600" />
			<c:set var="panelStyleTypeST" value="1" />
			<c:if test="${empty CALL_MBR_HP}">
				<c:set var="CALL_MBR_HP" value="${MBR_HP}" />
			</c:if>
		</c:when>
		<c:when test="${empty HCALL_SN and not empty CALL_CD}"><%-- 통화건 신규 등록하기 --%>
			<c:set var="panelStyleType1" value="panel-success border border-success" />
			<c:set var="panelStyleType2" value="bg-green-600" />
			<c:set var="panelStyleTypeST" value="2" />
			<c:if test="${empty CALL_MBR_HP}">
				<c:set var="CALL_MBR_HP" value="${MBR_HP}" />
			</c:if>
		</c:when>
		<c:otherwise><%-- 직접등록하기 --%>
			<c:set var="panelStyleType1" value="panel-info border border-info" />
			<c:set var="panelStyleType2" value="bg-cyan-600" />
			<c:set var="panelStyleTypeST" value="3" />
			<c:set var="CALL_MBR_HP" value="" />
		</c:otherwise>
	</c:choose>
	
	<c:if test="${empty HCALL_SN}"></c:if><c:if test="${not empty HCALL_SN}"></c:if>
	
	

	<div class="panel panel-bordered ${panelStyleType1} mb-0 " style="width:900px;">
	  	<div class="panel-heading draggable-move cursor-move">
			<h3 class="panel-title pl-15 pt-15 pb-15 pr-15 col-md-12">
				<div class="display-inline-block text-top" style="margin-top:2px;">회원통합검색</div>
				<div class="col-md-5 display-inline-block">
					<div class="input-group pl-0 pr-0">
						<input type="text" class="form-control form-control-sm input-auto-enter-key-${CUSTOM_UNIQ_KEY}" id="search_label_input_key${CUSTOM_UNIQ_KEY}" placeholder="이름,연락처,아이디를 입력하세요." autocomplete="off" value="${MBR_HP}">
						<span class="input-group-append">
							<button type="button" class="btn btn-xs btn-default" id="clk_search_btn_mbr_total${CUSTOM_UNIQ_KEY}"><i class="icon wb-search" aria-hidden="true"></i> 검색</button>
						</span>
					</div>
				</div>
				<div class="col-md-4 display-inline-block text-top">
					<div class="btn-group btn-group-sm col-md-12">
						<button type="button" class="btn btn-dark col-md-4 btn-act-tab-${CUSTOM_UNIQ_KEY}" data-tab-num="1">메뉴얼</button>
			            <button type="button" class="btn btn-dark col-md-4 btn-act-tab-${CUSTOM_UNIQ_KEY}" data-tab-num="2">법령</button>
			            <button type="button" class="btn btn-dark col-md-4 btn-act-tab-${CUSTOM_UNIQ_KEY}" data-tab-num="3">FAQ</button>
			        </div>
			        <div id="body_act_tab_layer${CUSTOM_UNIQ_KEY}" style="position:relative;display:none;">
				        <div class="popover bs-popover-bottom" style="max-width:1000px;width:1000px;left:-200px;">
							<div class="arrow" id="arrow_act_tab_layer${CUSTOM_UNIQ_KEY}"></div>
							<h3 class="popover-header cursor-pointer" >
								<span id="title_act_tab_layer${CUSTOM_UNIQ_KEY}">탭제목</span>
								&nbsp;<i class="fa fa-refresh cursor-pointer btn-act-tab-${CUSTOM_UNIQ_KEY}" data-tab-num="0" aria-hidden="true"></i>
								<div class="float-right" id="btn_act_tab_close${CUSTOM_UNIQ_KEY}">
									<span class="font-size-12 blue-grey-400">이 영역을 클릭하시면 팝업을 닫을 수 있습니다.</span>
								</div>
							</h3>
							<div class="popover-body p-0 w-p100 scroll-y scroll-x" style="clear:both;position:relative;">
								<iframe class="m-0 p-0 border-0 w-p100 scroll-y scroll-x" id="iframe_act_tab_layer${CUSTOM_UNIQ_KEY}" src=""></iframe>
	                      	</div>
	                    </div>
                    </div>
				</div>
				<div class="float-right">
					<%-- <a class="panel-action icon wb-map panel-phome" aria-hidden="true" data-toggle="tooltip" data-original-title="원위치"></a> --%>
					<a class=" icon wb-close panel-close m-0 p-10 cursor-pointer btn-act-close-${CUSTOM_UNIQ_KEY}" style="margin-top:-5px !important;" aria-hidden="true" data-toggle="tooltip" data-original-title="닫기"></a>
				</div>
			</h3>		
		</div>
		<div class="panel-body bg-blue-grey-100 p-10 scroll-y panel-body-${CUSTOM_UNIQ_KEY}" >
			<div class="panel mb-20">
				<div class="">
					<h3 class="panel-title blue-grey-800 pb-0">
						회원조회
						<button type="button" class="btn btn-outline btn-xs btn-warning btn-round pl-10 pr-10 " id="clk_mbr_modify${CUSTOM_UNIQ_KEY}" style="display:none;">회원정보 수정하기</button>
						<button type="button" class="btn btn-outline btn-xs btn-warning btn-round pl-10 pr-10 " id="clk_cti_mbr_delete${CUSTOM_UNIQ_KEY}" style="display:none;">CTI저장회원 삭제하기</button>
					</h3>
				</div>	 
				<div class="panel-body pt-10 pb-10">
					<div class="nav-tabs-horizontal" data-plugin="tabs">
						<c:if test="${empty member_list}">
							<%-- <c:if test="${panelStyleTypeST eq '3'}">
								<span>아래 항목을 입력하시면 CTI전용회원으로 등록 할 수 있습니다.</span>
							</c:if>
							<c:if test="${panelStyleTypeST ne '3'}">
								<span>조회 가능한 회원정보가 없습니다.</span>
							</c:if> --%>
							<span>조회 정보가 없을 경우 아래 항목을 입력하시면 CTI전용회원으로 등록 할 수 있습니다.</span>
							<div class="new-form-layer-${CUSTOM_UNIQ_KEY}">
								<form id="formAjaxDetail${CUSTOM_UNIQ_KEY}" name="formAjaxDetail${CUSTOM_UNIQ_KEY}" method="post">
								<table class="table footable footable-paging footable-paging-center custom_inputform_table mt-10">
										<colgroup>
										<col width='20%' >
							        	<col width='30%'>
							        	<col width='20%'>
							        	<col width='30%'>
										</colgroup>
									<tbody>
									<tr>
										<th class="text-left text-middle">아이디</th>
							 			<td class="text-center text-middle">
							 				<input class="form-control " type="text" name="MBR_ID" value="" autocomplete="off" />
										</td>
										<th class="text-left text-middle">연락처</th>
										<td class="text-center text-middle">
											<input class="form-control " type="text" name="CTI_MBR_HP" value="${MBR_HP}" autocomplete="off" />
										</td>
									</tr>
									<tr>
										<th class="text-left text-middle">이름</th>
							 			<td class="text-center text-middle">
							 				<input class="form-control " type="text" name="CTI_MBR_NM" value="" autocomplete="off" />
										</td>
										<th class="text-left text-middle">생년월일</th>
										<td class="text-center text-middle">
											<input class="form-control " type="text" name="CTI_MBR_BIRTH" value="" autocomplete="off" />
										</td>
									</tr>
								</tbody>
								</table>
								</form>
							</div>
						</c:if>
						<c:if test="${not empty member_list}">
							<ul class="nav nav-tabs nav-tabs-line tabs-line-top" role="tablist">
								<c:set var="catchTabLabelRealIdx" value="" />
								<c:forEach var="item" varStatus="status" items="${member_list}">
									<c:set var="isCatchTabLabelFixed" value="false" />
									<c:if test="${item.CTI_MBR_HP eq CALL_MBR_HP or item.MBR_ID eq cti_call_info.MBR_ID}">
										<c:set var="isCatchTabLabelFixed" value="true" />
									</c:if>
									<c:set var="isCatchTabSelected" value="false" />
									<c:if test="${item.MBR_ID eq cti_call_info.MBR_ID}">
										<c:if test="${isCatchTabLabelFixed eq true and status.index != 0}">
											<c:set var="isCatchTabSelected" value="false" />
										</c:if>
										<c:if test="${isCatchTabLabelFixed eq false}">
											<c:set var="isCatchTabSelected" value="true" />
										</c:if>
									</c:if>
									<c:if test="${item.MBR_ID eq cti_call_info.MBR_ID}">
										<c:set var="catchTabLabelRealIdx" value="${status.index}" />	
									</c:if>
									<li class="nav-item cursor-pointer" role="presentation"><a class="nav-link sel-mbr-list-item-${CUSTOM_UNIQ_KEY} <c:if test="${isCatchTabSelected eq true}">active</c:if> <c:if test="${isCatchTabLabelFixed eq true}">font-weight-800 bg-grey-100</c:if>"
										data-toggle="tab" aria-controls="cardTab1" role="tab" aria-expanded="false" aria-selected="false"
										data-is-join-mbr="${item.IS_JOIN_MBR}" 
										data-mbr-sn="${item.MBR_SN}"
										data-mbr-id="<c:out value="${item.MBR_ID}"/>" 
										data-mbr-hp="${item.CTI_MBR_HP}"><c:if test="${item.IS_CTI_MBR eq 'Y'}"> [CTI저장회원] </c:if><c:out value="${item.CTI_MBR_NM}"/>(${item.CTI_MBR_BIRTH},<c:out value="${item.MBR_ID}"/><c:if test="${empty item.MBR_ID}">아이디없음</c:if>)</a></li>
								</c:forEach>
								<script>
								$(function(){
									<c:if test="${empty isCatchTabLabelRealIdx}">
										<c:if test="${isCatchTabSelected eq false}">
											$('.sel-mbr-list-item-${CUSTOM_UNIQ_KEY}').eq(0).addClass('active');
										</c:if>
										$('.sel-mbr-list-item-${CUSTOM_UNIQ_KEY}.active').eq(0).click();
									</c:if>
									<c:if test="${not empty catchTabLabelRealIdx}">
										$('.sel-mbr-list-item-${CUSTOM_UNIQ_KEY}').removeClass('active');
										$('.sel-mbr-list-item-${CUSTOM_UNIQ_KEY}').eq(parseInt('${catchTabLabelRealIdx}')).addClass('active');
										$('.sel-mbr-list-item-${CUSTOM_UNIQ_KEY}.active').eq(0).click();
									</c:if>
								});
								</script>
							</ul>
						</c:if>
					</div>
					<div id="ctiMbrInfoDetail${CUSTOM_UNIQ_KEY}">
						<c:if test="${not empty member_list}">
							<p class="blue-grey-400 font-size-12 pt-10">회원을 선택하시면 상세 정보를 보실 수 있습니다.</p>
						</c:if>
					</div>
				</div>
			</div>
			<c:if test="${not empty member_list}">
				<div class="panel mb-20">
					<div class="">
						<h3 class="panel-title blue-grey-800 pb-0">교육신청 <span class="badge badge-pill badge-warning badge-custom-warning orange-600">전체 <span id="ctiCurriculumListTotalLabel${CUSTOM_UNIQ_KEY}">..</span> 건</span></h3>
					</div>	 
					<div class="panel-body pt-10 pb-10">
						<div id="ctiCurriculumList${CUSTOM_UNIQ_KEY}"></div>
					</div>
				</div>
			</c:if>
			<div class="panel mb-20">
				<div class="">
					<h3 class="panel-title blue-grey-800 pb-0">회원상담</h3>
				</div>	 
				<div class="panel-body pt-10 pb-10">
					<form:form commandName="ctiCallHistoryVO" id="formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}" name="formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}" method="post">
					<input type="hidden" name="HCALL_SN" value="${HCALL_SN}"/>
					<input type="hidden" name="CALL_CD" value="${CALL_CD}"/>
					<input type="hidden" name="CUSTOM_UNIQ_KEY" value="${CUSTOM_UNIQ_KEY}"/>  
					<table class="table footable footable-paging footable-paging-center custom_inputform_table">
							<colgroup>
							<col width='20%' >
				        	<col width='30%'>
				        	<col width='20%'>
				        	<col width='30%'>
							</colgroup>
						<tbody>
						<tr>
							<th class="text-left text-middle">고객성향</th>
				 			<td class="text-center text-middle" colspan="3">
				 				<select class="form-control selectpicker_manual-${CUSTOM_UNIQ_KEY}" data-style="btn-outline btn-default" name="HCALL_IMPRT">
				 					<option value="1" <c:if test="${cti_call_info.HCALL_IMPRT eq '1'}">selected</c:if> >약</option>
				 					<option value="2" <c:if test="${cti_call_info.HCALL_IMPRT eq '2'}">selected</c:if> >중</option>
				 					<option value="3" <c:if test="${cti_call_info.HCALL_IMPRT eq '3'}">selected</c:if> >강</option>
				 				</select>
							</td>
						</tr>
						<tr>
							<th class="text-left text-middle">상담요청IVR</th>
				 			<td class="text-center text-middle" colspan="3">
				 				<c:forEach var="item_category" varStatus="status_category" items="${list_category_gubun_1}">
				 					<c:if test="${item_category.CD_ID eq cti_call_info.HCALL_IVR_CD or item_category.CD_ID eq HCALL_IVR_CD}">
				 						<input class="form-control bg-white border-0" type="text" value="${item_category.CD_NM }" disabled/>
				 					</c:if>
			 					</c:forEach>
							</td>
						</tr>
						<tr>
							<th class="text-left text-middle">상담분류</th>
				 			<td class="text-center text-middle" colspan="3">
				 				<div class="row col-md-12 w-p100 m-0 pl-0 pr-0">
	 								<div class="col-md-6 pl-0 pr-5">
					 					<select class="form-control selectpicker_manual-${CUSTOM_UNIQ_KEY}" id="HCALL_GUBUN_1_${CUSTOM_UNIQ_KEY}" data-style="btn-outline btn-default" name="HCALL_GUBUN_1">
					 						<option value="">항목을 선택해주세요.</option>
						 					<c:forEach var="item_category" varStatus="status_category" items="${list_category_gubun_1}">
						 						<option data-cd-sn="${item_category.CD_SN }" value="${item_category.CD_ID }" <c:if test="${item_category.CD_ID eq cti_call_info.HCALL_GUBUN_1 or item_category.CD_ID eq HCALL_IVR_CD}">selected</c:if> >${item_category.CD_NM }</option>
						 					</c:forEach>
						 				</select>
					 				</div>
					 				<div class="col-md-6 pl-5 pr-0">
						 				<select class="form-control selectpicker_manual-${CUSTOM_UNIQ_KEY}" id="HCALL_GUBUN_2_${CUSTOM_UNIQ_KEY}" data-style="btn-outline btn-default" name="HCALL_GUBUN_2">
						 					<option value="">항목을 선택해주세요.</option>
						 					<c:forEach var="item_category" varStatus="status_category" items="${list_category_gubun_2}">
						 						<option value="${item_category.CD_ID }" <c:if test="${item_category.CD_ID eq cti_call_info.HCALL_GUBUN_2}">selected</c:if> >${item_category.CD_NM }</option>
						 					</c:forEach>
						 				</select>
					 				</div>
				 				</div>
							</td>
						</tr>
						<tr>
	                      	<th>상담내용</th>
						  	<td class="text-left text-middle" colspan="3" >
						  		<textarea class="form-control" rows="5" name="HCALL_CONT" placeholder="상담내용 작성란">${cti_call_info.HCALL_CONT}</textarea>
						  	</td>
	                    </tr>
	                    <tr>
	                      	<th>특이사항</th>
						  	<td class="text-left text-middle" colspan="3" >
						  		<textarea class="form-control" rows="5" name="HCALL_MEMO" placeholder="특이사항 작성란">${cti_call_info.HCALL_MEMO }</textarea>
						  	</td>
	                    </tr>
	                    <c:if test="${panelStyleTypeST eq '1' or panelStyleTypeST eq '2'}">
	                    <tr>
	                      	<th>변경사유</th>
						  	<td class="text-left text-middle" colspan="3" >
						  		<textarea class="form-control" rows="5" name="LOG_UPD_MSG" placeholder="변경사유(설명)"></textarea>
						  		<span class="text-help red-600 font-size-12 mb-0">* 개인정보변경(연락처 수정)시에는 개인정보변경이력에 남길 코멘트로 사용됩니다.</span>
						  	</td>
	                    </tr>
	                    </c:if>
						</tbody>
					</table>
					</form:form>
				</div>
			</div>
			<div class="panel mb-20">
				<!-- 			
				<div class="">
					<h3 class="panel-title blue-grey-800 pb-0">법인사업장 팩스 신청서 </h3>
				</div>	
				  -->
				<div class="panel-body pt-10 pb-10">				
					<form id="formAjaxFile1${CUSTOM_UNIQ_KEY}" name="formAjaxFile1${CUSTOM_UNIQ_KEY}" method="post" enctype="multipart/form-data">
						<div class="input-group input-group-file" data-plugin="inputGroupFile">
							<input type="text" class="form-control" value="" placeholder="법인사업장 팩스 신청서 업로드" id="file_jegprsn_reqstdoc${CUSTOM_UNIQ_KEY}_label"/>
			     			<span class="input-group-btn">
			       				<span class="btn btn-success btn-file">
			         				<i class="icon wb-upload" aria-hidden="true"></i>
			         				<input type="file" id="file_jegprsn_reqstdoc${CUSTOM_UNIQ_KEY}" name="file_jegprsn_reqstdoc" multiple="false" value="" />
			       				</span>
			     			</span>
			   			</div>
				    </form>
				    <c:if test="${panelStyleTypeST eq '1'}">
				   		<c:import url="/cmm/fms/selectFileInfsForUpdateAjax.do" >
				   			<c:param name="param_request_type" value="cti"/>
				   			<c:param name="param_custom_uniq_key" value="${CUSTOM_UNIQ_KEY}"/>
				   			<c:param name="param_target_clk_id" value="clk_search_btn_mbr_total${CUSTOM_UNIQ_KEY}"/>
					    	<c:param name="param_atchFileId" value="${cti_call_info.HCALL_FILE_1}" />					    	
				    		<c:param name="param_fixFileSn" value="0" />
					    	<c:param name="param_lockFileSn" value="" />
					    	<c:param name="param_updateFlag" value="Y" />
						</c:import>
				    </c:if>
				</div>
			</div>
			<div class="panel mb-20">
				<!-- 			
				<div class="">
					<h3 class="panel-title blue-grey-800 pb-0">이수증발급 팩스 신청서 </h3>
				</div>	 
				 -->
				<div class="panel-body pt-10 pb-10">
					<form id="formAjaxFile2${CUSTOM_UNIQ_KEY}" name="formAjaxFile2${CUSTOM_UNIQ_KEY}" method="post" enctype="multipart/form-data">
						<div class="input-group input-group-file" data-plugin="inputGroupFile">
							<input type="text" class="form-control" readonly="" value="" placeholder="이수증발급 팩스 신청서 업로드"/>
			     			<span class="input-group-btn">
			       				<span class="btn btn-success btn-file">
			         				<i class="icon wb-upload" aria-hidden="true"></i>
			         				<input type="file" id="file_compl_reqstdoc${CUSTOM_UNIQ_KEY}" name="file_compl_reqstdoc" multiple="false" value="" />
			       				</span>
			     			</span>
			   			</div>
				    </form>	
				    <c:if test="${panelStyleTypeST eq '1'}">
				   		<c:import url="/cmm/fms/selectFileInfsForUpdateAjax.do" >
				   			<c:param name="param_request_type" value="cti"/>
				   			<c:param name="param_custom_uniq_key" value="${CUSTOM_UNIQ_KEY}"/>
				   			<c:param name="param_target_clk_id" value="clk_search_btn_mbr_total${CUSTOM_UNIQ_KEY}"/>
					    	<c:param name="param_atchFileId" value="${cti_call_info.HCALL_FILE_2}" />
					    	<c:param name="param_returnUrlType" value="cti"/>
				    		<c:param name="param_fixFileSn" value="0" />
					    	<c:param name="param_lockFileSn" value="" />
					    	<c:param name="param_updateFlag" value="Y" />
					    </c:import>
					</c:if>
				</div>
			</div>
			<c:if test="${not empty member_list}">
				<div class="panel ">
					<div class="">
						<h3 class="panel-title blue-grey-800 pb-0">지난상담 목록 <span class="badge badge-pill badge-warning badge-custom-warning orange-600">전체 <span id="ctiCallhstryListTotalLabel${CUSTOM_UNIQ_KEY}">..</span> 건</span></h3>
					</div>	 
					<div class="panel-body pt-10 pb-10">
						<div id="ctiCallhstryList${CUSTOM_UNIQ_KEY}"></div>
					</div>
				</div>
			</c:if>
		</div>
		<div class="panel-footer ${panelStyleType2} pt-0 pl-0 pr-0 pb-1 panel-footer-${CUSTOM_UNIQ_KEY} ">
			<div class="text-right bg-white p-10">
				<c:choose>
					<c:when test="${panelStyleTypeST eq '1'}">
						<span class="blue-grey-400 mr-20">( 이력코드 : ${HCALL_SN} )</span>
						<button type="button" class="btn btn-warning btn-outline btn-act-submit-${CUSTOM_UNIQ_KEY} btn-act-modify-callhstry-${CUSTOM_UNIQ_KEY}">변경하기</button>
					</c:when>
					<c:when test="${panelStyleTypeST eq '2'}">
						<span class="blue-grey-400 mr-20">( 콜코드 : ${CALL_CD} , 전화번호 : ${CALL_MBR_HP} )</span>
						<button type="button" class="btn btn-success btn-outline btn-act-submit-${CUSTOM_UNIQ_KEY} btn-act-add-callhstry-${CUSTOM_UNIQ_KEY}">통화건 신규 등록하기</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-primary btn-outline btn-act-submit-${CUSTOM_UNIQ_KEY} btn-act-direct-add-callhstry-${CUSTOM_UNIQ_KEY}">직접등록하기</button>
					</c:otherwise>
				</c:choose>
				<button type="button" class="btn btn-default btn-outline btn-act-submit-${CUSTOM_UNIQ_KEY} btn-act-close-${CUSTOM_UNIQ_KEY}">취소(닫기)</button>
			</div>
		</div>
	</div>


    <script>
    var panelStyleTypeST = '${panelStyleTypeST}';
    $('.btn-act-close-${CUSTOM_UNIQ_KEY}').click(function(){
    	if(panelStyleTypeST == '2') ctiCallDataRecordUnReady();
		removeAdmPublicPanelLayer('${CUSTOM_UNIQ_KEY}');
	});
    $(function(){
    	$(".selec2_manual-${CUSTOM_UNIQ_KEY}").select2({
    		width: 'auto',
			"language": {
			       "noResults": function(){
			           return "조회 된 항목이 없습니다.";
			       }
			   	},
			    escapeMarkup: function (markup) {
			        return markup;
			    }
			});
    	$('.selectpicker_manual-${CUSTOM_UNIQ_KEY}').selectpicker();
    	$('.input-auto-enter-key-${CUSTOM_UNIQ_KEY}').keypress(function(key) {
        	if(key.keyCode == 13){
        		$('#clk_search_btn_mbr_total${CUSTOM_UNIQ_KEY}').click();
            }
        });
    });
    //통합 검색 상단 탭메뉴 링크
    $('.btn-act-tab-${CUSTOM_UNIQ_KEY}').click(function(){
    	var tabon = $(this).hasClass('tabOn');
    	var tabnum = $(this).attr('data-tab-num');
    	var target = $(this);
    	if(tabnum == '0') { //현재 탭을 첫페이지로 새로고침
    		target = $('.btn-act-tab-${CUSTOM_UNIQ_KEY}.tabOn').eq(0);
    		tabnum = target.attr('data-tab-num');
    		$('.btn-act-tab-${CUSTOM_UNIQ_KEY}').removeClass('tabOn');
    		tabon = false;
    	}
    	var h = 0;
	  	if(document.body.clientHeight == 0) {
	  		h = window.innerHeight*0.75;
	  	} else {
	  		h = document.body.clientHeight*0.75;
	  	}
	  	$('#iframe_act_tab_layer${CUSTOM_UNIQ_KEY}').css('height',h);
    	if(tabnum == '1') { //메뉴얼
    		if(!tabon) {
	    		$('.btn-act-tab-${CUSTOM_UNIQ_KEY}').removeClass('tabOn');
	    		target.addClass('tabOn');
	    		$('#iframe_act_tab_layer${CUSTOM_UNIQ_KEY}').attr('src','/cti/main/manual/list.do?CUSTOM_UNIQ_KEY=${CUSTOM_UNIQ_KEY}');
    		}
    		$('#title_act_tab_layer${CUSTOM_UNIQ_KEY}').html('메뉴얼');
    		$('#arrow_act_tab_layer${CUSTOM_UNIQ_KEY}').css('left','253px');
    		$('#body_act_tab_layer${CUSTOM_UNIQ_KEY}').show();
    	} else if(tabnum == '2') { //법령 
    		if(!tabon) {
	    		$('.btn-act-tab-${CUSTOM_UNIQ_KEY}').removeClass('tabOn');
	    		target.addClass('tabOn');
	    		$('#iframe_act_tab_layer${CUSTOM_UNIQ_KEY}').attr('src','https://www.law.go.kr/%EB%B2%95%EB%A0%B9/%EB%82%9A%EC%8B%9C%EA%B4%80%EB%A6%AC%EB%B0%8F%EC%9C%A1%EC%84%B1%EB%B2%95');
    		}
    		$('#title_act_tab_layer${CUSTOM_UNIQ_KEY}').html('법령');
    		$('#arrow_act_tab_layer${CUSTOM_UNIQ_KEY}').css('left','328px');
    		$('#body_act_tab_layer${CUSTOM_UNIQ_KEY}').show();
    	} else if(tabnum == '3') { //FAQ
    		if(!tabon) {
	    		$('.btn-act-tab-${CUSTOM_UNIQ_KEY}').removeClass('tabOn');
	    		target.addClass('tabOn');
	    		$('#iframe_act_tab_layer${CUSTOM_UNIQ_KEY}').attr('src','/cti/main/educenter/faq/list.do?CUSTOM_UNIQ_KEY=${CUSTOM_UNIQ_KEY}');	    		
    		}
    		$('#title_act_tab_layer${CUSTOM_UNIQ_KEY}').html('FAQ');
    		$('#arrow_act_tab_layer${CUSTOM_UNIQ_KEY}').css('left','402px');
    		$('#body_act_tab_layer${CUSTOM_UNIQ_KEY}').show();
    	}
    });
    $('#btn_act_tab_close${CUSTOM_UNIQ_KEY}').click(function(){
    	$('#body_act_tab_layer${CUSTOM_UNIQ_KEY}').hide();
    });
    //회원정보 수정하기
    $('#clk_mbr_modify${CUSTOM_UNIQ_KEY}').click(function(){
    	var target = $('.sel-mbr-list-item-${CUSTOM_UNIQ_KEY}.active').eq(0);
    	var mbr_sn = $(target).attr('data-mbr-sn');
    	var mbr_id = $(target).attr('data-mbr-id');
    	var mbr_hp = $(target).attr('data-mbr-hp');
    	var is_join_mbr = $(target).attr('data-is-join-mbr');
    	var idkey = 'MBR_TB_MODIFY';
		if($("#admPublicPanelLayer"+idkey).length > 0) {
			alertify.confirm("이미 회원정보 변경 창이 열려 있습니다.<br>계속하시면 해당 화면은 갱신되며 입력내용은 초기화 됩니다.<br>계속하시겠습니까?",function() {    			
    	    	$.ajax({
    				type:"POST",
    				url :'/cti/member/modify_user.do',
    				data:{
    					MBR_ID					: mbr_id,
    					CUSTOM_UNIQ_KEY			: idkey, 
    					PARENT_CUSTOM_UNIQ_KEY	: '${CUSTOM_UNIQ_KEY}',
    				},
    				dataType: 'html',//"json",
    				contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    				async: true,
    				success: function(data, status, xhr) {
    					//console.log('success!');
    					newAdmPublicPanelLayer(idkey,data);
    				},
    				complete : function() {
    					//console.log('complete!');
    			    },
    				error: function(jqXHR, textStatus, errorThrown) {
    					//console.log('error!');
    					alertify.alert(textStatus);
    				}
    			});
        	});	
    	} else {
        	$.ajax({
    			type:"POST",
    			url :'/cti/member/modify_user.do',
    			data:{
    				MBR_ID					: mbr_id,
    				CUSTOM_UNIQ_KEY			: idkey, 
    				PARENT_CUSTOM_UNIQ_KEY	: '${CUSTOM_UNIQ_KEY}',
    			},
    			dataType: 'html',//"json",
    			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    			async: true,
    			success: function(data, status, xhr) {
    				//console.log('success!');
    				newAdmPublicPanelLayer(idkey,data);
    			},
    			complete : function() {
    				//console.log('complete!');
    		    },
    			error: function(jqXHR, textStatus, errorThrown) {
    				//console.log('error!');
    				alertify.alert(textStatus);
    			}
    		});
    	}
		
    	
    });
    //CTI전용회원 삭제하기
    $('#clk_cti_mbr_delete${CUSTOM_UNIQ_KEY}').click(function(){
    	var target = $('.sel-mbr-list-item-${CUSTOM_UNIQ_KEY}.active').eq(0);
    	var mbr_sn = $(target).attr('data-mbr-sn');
    	var mbr_id = $(target).attr('data-mbr-id');
    	var mbr_hp = $(target).attr('data-mbr-hp');
    	var is_join_mbr = $(target).attr('data-is-join-mbr');
    	
    	alertify.prompt('선택 된 CTI전용회원을 삭제합니다.<br>삭제 후 복구가 불가능하며<br><span class="red-600">처리 후 자동 재검색(화면갱신)이 진행되므로 입력하신 내용이 초기화 될 수 있습니다.</span><br>삭제하려는 사유를 입력해주세요.',function(val, e) {
    		//ok
    		if(val.trim()=='') {
    			alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    			return;
    		} 
    		//ok
	    	$.ajax({
	    		type:"POST",
	    		url :"/cti/member/delete_user_act.do",
	    		data:{
	    			CTI_MBR_SN	: mbr_sn,
	    			CTI_MBR_HP	: mbr_hp,
	    			LOG_UPD_MSG	: val,
	    		},
	    		dataType: "json",//'html'
	    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
	    		async: true,
	    		beforeSend : function(xhr, opts) {
	    			$('#clk_cti_mbr_delete${CUSTOM_UNIQ_KEY}').addClass('disabled');
	    		},
	    		success: function(data, status, xhr) {
	    			//console.log('success!');
	    			if(data.error == '1') {
	    				alertify.alert(data.msg);
	    			} else {
	    				notificationTopAlert('처리가 완료 되었습니다.');
	    				fn_egov_link_refresh_callhstry();
	    				$('#clk_search_btn_mbr_total${CUSTOM_UNIQ_KEY}').click(); 
	    			}
	    		},
	    		complete : function() {
	    			//console.log('complete!');
	    			$('#clk_cti_mbr_delete${CUSTOM_UNIQ_KEY}').removeClass('disabled');
	    	    },
	    		error: function(jqXHR, textStatus, errorThrown) {
	    			//console.log('error!');
	    			//console.log(jqXHR);
	    			//console.log(textStatus);
	    			//console.log(errorThrown);
	    			alertify.alert(textStatus);
	    		}
	    	});
    	});//End of alertify.prompt
    	
    });
    //회원 선택
    $('.sel-mbr-list-item-${CUSTOM_UNIQ_KEY}').click(function(){
    	
    	var mbr_id = $(this).attr('data-mbr-id');
    	var mbr_hp = $(this).attr('data-mbr-hp');
    	var is_join_mbr = $(this).attr('data-is-join-mbr');
    	
    	if(is_join_mbr=='Y') {
    		$('#clk_mbr_modify${CUSTOM_UNIQ_KEY}').show();
    		$('#clk_cti_mbr_delete${CUSTOM_UNIQ_KEY}').hide();
    	} else {
    		$('#clk_mbr_modify${CUSTOM_UNIQ_KEY}').hide();
    		$('#clk_cti_mbr_delete${CUSTOM_UNIQ_KEY}').show();
    	}
    	
    	var form = document.getElementById('eduMemberVOFormTotal${CUSTOM_UNIQ_KEY}');
    	form.MBR_ID.value = mbr_id;
    	form.MBR_HP.value = mbr_hp;
    	form.CALL_MBR_HP.value = '${CALL_MBR_HP}';
    	form.IS_JOIN_MBR.value = is_join_mbr;
    	form.action = '';
    	$.ajax({
    		type:"POST",
    		url :"/cti/member/ajax_detail.do",
    		data:$('#eduMemberVOFormTotal${CUSTOM_UNIQ_KEY}').serialize(),
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function(xhr, opts) {
    			$('.btn-act-submit-${CUSTOM_UNIQ_KEY}').addClass('disabled');
    			$("#ctiMbrInfoDetail${CUSTOM_UNIQ_KEY}").html(ajaxLoadingHtmlTag());
    	    },
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$("#ctiMbrInfoDetail${CUSTOM_UNIQ_KEY}").html(data);
    		},
    		complete : function() {
    			//console.log('complete!');
    			$('.btn-act-submit-${CUSTOM_UNIQ_KEY}').removeClass('disabled');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			//console.log(jqXHR);
    			//console.log(textStatus);
    			//console.log(errorThrown);
    			alertify.alert(textStatus);
    		}
    	});  
    	//지난상담 목록
    	{
	    	var form = document.getElementById('ctiCallhstryListForm${CUSTOM_UNIQ_KEY}');
	    	form.MBR_ID.value = mbr_id;
	    	form.MBR_HP.value = mbr_hp;
	    	form.IS_JOIN_MBR.value = is_join_mbr;
	    	form.pageIndex.value = 1;
	    	form.action = '';
	    	$.ajax({
	    		type:"POST",
	    		url :"/cti/callhstry/list_ajax_popup.do",
	    		data:$('#ctiCallhstryListForm${CUSTOM_UNIQ_KEY}').serialize(),
	    		dataType: 'html',//"json",
	    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
	    		async: true,
	    		beforeSend : function(xhr, opts) {
	    			$("#ctiCallhstryList${CUSTOM_UNIQ_KEY}").html(ajaxLoadingHtmlTag());
	    	    },
	    		success: function(data, status, xhr) {
	    			//console.log('success!');
	    			$("#ctiCallhstryList${CUSTOM_UNIQ_KEY}").html(data);
	    		},
	    		complete : function() {
	    			//console.log('complete!');
	    	    },
	    		error: function(jqXHR, textStatus, errorThrown) {
	    			//console.log('error!');
	    			//console.log(jqXHR);
	    			//console.log(textStatus);
	    			//console.log(errorThrown);
	    			alertify.alert(textStatus);
	    		}
	    	}); 
    	}
    	//End of 지난상담 목록
    	//교육신청가능목록 
    	{
	    	var form = document.getElementById('ctiCurriculumListForm${CUSTOM_UNIQ_KEY}');
	    	form.MBR_ID.value = mbr_id;
	    	form.MBR_HP.value = mbr_hp;
	    	form.IS_JOIN_MBR.value = is_join_mbr;
	    	form.action = '';
	    	$.ajax({
	    		type:"POST",
	    		url :"/cti/curriculum/list_ajax_popup.do",
	    		data:$('#ctiCurriculumListForm${CUSTOM_UNIQ_KEY}').serialize(),
	    		dataType: 'html',//"json",
	    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
	    		async: true,
	    		beforeSend : function(xhr, opts) {
	    			$("#ctiCurriculumList${CUSTOM_UNIQ_KEY}").html(ajaxLoadingHtmlTag());
	    	    },
	    		success: function(data, status, xhr) {
	    			//console.log('success!');
	    			$("#ctiCurriculumList${CUSTOM_UNIQ_KEY}").html(data);
	    		},
	    		complete : function() {
	    			//console.log('complete!');
	    	    },
	    		error: function(jqXHR, textStatus, errorThrown) {
	    			//console.log('error!');
	    			//console.log(jqXHR);
	    			//console.log(textStatus);
	    			//console.log(errorThrown);
	    			alertify.alert(textStatus);
	    		}
	    	});  
    	}
    	//End of 교육신청가능목록
    });
    
    //회원검색
    $('#clk_search_btn_mbr_total${CUSTOM_UNIQ_KEY}').click(function(){
    	var callnum = $('#search_label_input_key${CUSTOM_UNIQ_KEY}').val();
    	if(callnum=='') {
    		alertify.alert('검색어를 입력해주세요.'); 
    		return;
    	}
    	var idkey = '${CUSTOM_UNIQ_KEY}';
    	$.ajax({
    		type:"POST",
    		url :"/cti/main/list_total.do",
    		data:{
    			MBR_HP			: callnum,
    			CALL_CD			: '${CALL_CD}',
    			HCALL_SN		: '${HCALL_SN}',
    			CUSTOM_UNIQ_KEY	: idkey,
    			CALL_MBR_HP		: '${CALL_MBR_HP}',
    			HCALL_IVR_CD	: '${HCALL_IVR_CD}',
    		},
    		dataType: 'html',//"json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function(xhr, opts) {
    			$(".panel-body-${CUSTOM_UNIQ_KEY}").html(ajaxLoadingHtmlTag());
    			$(".panel-footer-${CUSTOM_UNIQ_KEY}").hide();
    		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			//$("#eduAdmEduPublicModal").html(data);
    			//$("#eduAdmEduPublicModal").modal('show');
    			newAdmPublicPanelLayer(idkey,data);			
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			alertify.alert(textStatus);
    		}
    	}); 
    });
    //End of 회원검색
    
    //변경하기 처리
    $('.btn-act-modify-callhstry-${CUSTOM_UNIQ_KEY}').click(function(){
    	var formMbr = document.getElementById('formAjaxDetail${CUSTOM_UNIQ_KEY}');
    	var formCallhstry = document.getElementById('formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}');
    	if(formCallhstry.HCALL_CONT.value.trim()=='') {
    		alertify.alert('상담내용을 입력해주세요.');
    		formCallhstry.HCALL_CONT.focus();
    		return;
    	}
    	var IS_CTI_MBR_HP_UPD = $("input:checkbox[id='CTI_MBR_HP_UPD${CUSTOM_UNIQ_KEY}']").is(":checked");
    	if(IS_CTI_MBR_HP_UPD) {
    		if(formCallhstry.LOG_UPD_MSG.value.length==0) {
    			alertify.alert('연락처를 변경할 때에는 변경사유를 작성해야 합니다.');
    			return;
        	}
    		formMbr.CTI_MBR_HP_UPD_YN.value = 'Y';
    	}
    	var formData = new FormData();
    	formData.append('file_jegprsn_reqstdoc', $('#file_jegprsn_reqstdoc${CUSTOM_UNIQ_KEY}')[0].files[0]);
    	formData.append('file_compl_reqstdoc', $('#file_compl_reqstdoc${CUSTOM_UNIQ_KEY}')[0].files[0]);
    	formData = addAllFormDataByFormId(formData,$('#formAjaxDetail${CUSTOM_UNIQ_KEY}'));
    	formData = addAllFormDataByFormId(formData,$('#formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}'));
    	$.ajax({
    		type:"POST",
    		enctype: 'multipart/form-data',
    		url :"/cti/callhstry/modify_act.do",
    		//data:$('#formAjaxDetail${CUSTOM_UNIQ_KEY}').serialize()+'&'+$('#formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}').serialize(),
    		//dataType: "json",//'html'
    		//contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		data:formData,
    		contentType: false,
			processData: false,
    		async: true,
    		cache: false,
    		timeout: 600000,
    		beforeSend : function(xhr, opts) {
    			$('.btn-act-submit-${CUSTOM_UNIQ_KEY}').addClass('disabled');
    		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			if(data.error == '1') {
    				alertify.alert(data.msg);
    			} else {
    				notificationTopAlert('변경하기 처리가 완료 되었습니다.');
    				fn_egov_link_refresh_callhstry();
    				removeAdmPublicPanelLayer('${CUSTOM_UNIQ_KEY}');
    			}
    		},
    		complete : function() {
    			//console.log('complete!');
    			$('.btn-act-submit-${CUSTOM_UNIQ_KEY}').removeClass('disabled');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			//console.log(jqXHR);
    			//console.log(textStatus);
    			//console.log(errorThrown);
    			alertify.alert(textStatus);
    		}
    	});        	
    	
    });
    //End of 변경하기 처리
    
    //직접등록하기
    $('.btn-act-direct-add-callhstry-${CUSTOM_UNIQ_KEY}').click(function(){
    	var formMbr = document.getElementById('formAjaxDetail${CUSTOM_UNIQ_KEY}');
    	var formCallhstry = document.getElementById('formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}');
    	if(formMbr.CTI_MBR_HP.value.trim()=='') {
    		alertify.alert('상담통화이력은 연락처 기준으로 처리되므로 임의값이라도 저장되어야 합니다. 연락처 키값이 자동생성되었습니다.');
    		formMbr.CTI_MBR_HP.value = getRandomHpIdkey();
    		return;
    	}
    	if(formCallhstry.HCALL_CONT.value.trim()=='') {
    		alertify.alert('상담내용을 입력해주세요.');
    		return;
    	}
    	var formData = new FormData();
    	formData.append('file_jegprsn_reqstdoc', $('#file_jegprsn_reqstdoc${CUSTOM_UNIQ_KEY}')[0].files[0]);
    	formData.append('file_compl_reqstdoc', $('#file_compl_reqstdoc${CUSTOM_UNIQ_KEY}')[0].files[0]);
    	formData = addAllFormDataByFormId(formData,$('#formAjaxDetail${CUSTOM_UNIQ_KEY}'));
    	formData = addAllFormDataByFormId(formData,$('#formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}'));
    	$.ajax({
    		type:"POST",
    		url :"/cti/callhstry/write_direct_act.do",
    		//data:$('#formAjaxDetail${CUSTOM_UNIQ_KEY}').serialize()+'&'+$('#formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}').serialize(),
    		//dataType: "json",//'html'
    		//contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		data:formData,
    		contentType: false,
			processData: false,
    		async: true,
    		cache: false,
    		timeout: 600000,
    		beforeSend : function(xhr, opts) {
    			$('.btn-act-submit-${CUSTOM_UNIQ_KEY}').addClass('disabled');
    		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			if(data.error == '1') {
    				alertify.alert(data.msg);
    			} else {
    				notificationTopAlert('직접 등록 건 처리가 완료 되었습니다.');
    				fn_egov_link_refresh_callhstry();
    				removeAdmPublicPanelLayer('${CUSTOM_UNIQ_KEY}');
    			}
    		},
    		complete : function() {
    			//console.log('complete!');
    			$('.btn-act-submit-${CUSTOM_UNIQ_KEY}').removeClass('disabled');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			//console.log(jqXHR);
    			//console.log(textStatus);
    			//console.log(errorThrown);
    			alertify.alert(textStatus);
    		}
    	});        	
    	
    });
    //End of 직접등록하기
    
    //통화건 신규 등록하기
    $('.btn-act-add-callhstry-${CUSTOM_UNIQ_KEY}').click(function(){
    	var formMbr = document.getElementById('formAjaxDetail${CUSTOM_UNIQ_KEY}');
    	var formCallhstry = document.getElementById('formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}');
    	var mbr_hp = formMbr.CTI_MBR_HP.value;
    	var IS_CTI_MBR_HP_UPD = $("input:checkbox[id='CTI_MBR_HP_UPD${CUSTOM_UNIQ_KEY}']").is(":checked");
    	if(IS_CTI_MBR_HP_UPD) {
    		if(formCallhstry.HCALL_MEMO.value.length==0) {
    			alertify.alert('연락처를 변경할 때에는 특이사항(변경이력로그)을 작성해야 합니다.');
    			return;
        	}
    		formMbr.CTI_MBR_HP_UPD_YN.value = 'Y';
    	}
    	var formData = new FormData();
    	formData.append('file_jegprsn_reqstdoc', $('#file_jegprsn_reqstdoc${CUSTOM_UNIQ_KEY}')[0].files[0]);
    	formData.append('file_compl_reqstdoc', $('#file_compl_reqstdoc${CUSTOM_UNIQ_KEY}')[0].files[0]);
    	formData = addAllFormDataByFormId(formData,$('#formAjaxDetail${CUSTOM_UNIQ_KEY}'));
    	formData = addAllFormDataByFormId(formData,$('#formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}'));
    	$.ajax({
    		type:"POST",
    		url :"/cti/callhstry/write_new_act.do",
    		//data:$('#formAjaxDetail${CUSTOM_UNIQ_KEY}').serialize()+'&'+$('#formAjaxAddCallhstry${CUSTOM_UNIQ_KEY}').serialize(),
    		//dataType: "json",//'html'
    		//contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		data:formData,
    		contentType: false,
			processData: false,
    		async: true,
    		cache: false,
    		timeout: 600000,
    		beforeSend : function(xhr, opts) {
    			$('.btn-act-submit-${CUSTOM_UNIQ_KEY}').addClass('disabled');
    		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			if(data.error == '1') {
    				alertify.alert(data.msg);
    			} else if(data.error == '2') {
    				alertify.alert('통화 중인 상담건은 이력관리를 위해 통화종료 후 반영이 가능하므로 <br>잠시 후 다시 시도해주세요.');
    			} else {
	    			notificationTopAlert(mbr_hp+' 통화 건 상담 반영이 완료 되었습니다.');
					fn_egov_link_refresh_callhstry();
					removeAdmPublicPanelLayer('${CUSTOM_UNIQ_KEY}');
    			}
    		},
    		complete : function() {
    			//console.log('complete!');
    			$('.btn-act-submit-${CUSTOM_UNIQ_KEY}').removeClass('disabled');
    			ctiCallDataRecordUnReady();
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			//console.log(jqXHR);
    			//console.log(textStatus);
    			//console.log(errorThrown);
    			ctiCallDataRecordUnReady();
    			alertify.alert(textStatus);
    		}
    	});        	
    	
    });
    //End of 통화건 신규 등록하기
    
  	//카테고리 상담분류
    $("#HCALL_GUBUN_1_${CUSTOM_UNIQ_KEY}").change(function() {
    	var cd_sn = $("#HCALL_GUBUN_1_${CUSTOM_UNIQ_KEY} option:selected").attr('data-cd-sn');
    	$.ajax({
    		type:"POST",
    		url :"/util/category.do",
    		data:{
    			CD_SN		: cd_sn,
    	    	CD_HIDE		: 'N',
    	    	IS_CD_UP	: 'N',
    	    	IS_CD_DOWN 	: 'Y',
    		},
    		dataType: "json",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			if(data.error == '1') {
    				alertify.alert(data.msg);
    			} else {
    				var json = JSON.parse(data.rawdata);
    				for (i=0; i<json.length; i++) {
    					var item = json[i];
    					$('#HCALL_GUBUN_2_${CUSTOM_UNIQ_KEY}').append('<option value="'+item.cd_id+'">'+item.cd_nm+'</option>');
    				}
    				$('#HCALL_GUBUN_2_${CUSTOM_UNIQ_KEY}').selectpicker('refresh');
    			}
    		},
    		beforeSend : function() {
    			$('#HCALL_GUBUN_2_${CUSTOM_UNIQ_KEY}').html('<option value="">항목을 선택해주세요.</option>');
    			$('#HCALL_GUBUN_2_${CUSTOM_UNIQ_KEY}').selectpicker('refresh');
    		},
    		complete : function() {
    			//console.log('complete!');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			alertify.alert(textStatus);
    		}
    	});
    });    
    //End of 카테고리 상담분류
    
    //회원 교육 신청
    $(document).off("click", ".clk-edu-add-${CUSTOM_UNIQ_KEY}");
	$(document).on("click",".clk-edu-add-${CUSTOM_UNIQ_KEY}",function() {
		
    	var target = $('#sel_ajax_curriculum_info_${CUSTOM_UNIQ_KEY} option:selected');
    	var crs_sn = target.val();
    	var form = document.getElementById('formAjaxDetail${CUSTOM_UNIQ_KEY}');
    	var mbr_id = form.MBR_ID.value;
    	$.ajax({
    		type:"POST",
    		url :"/cti/mbrhstry/write_act.do",
    		data:{
    			CRS_SN	: crs_sn,
    			MBR_ID 	: mbr_id,
    		},
    		dataType: "json",//'html'
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function(xhr, opts) {
    			$('.clk-edu-add-${CUSTOM_UNIQ_KEY}').addClass('disabled');
    		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			if(data.error == '1') {
    				alertify.alert(data.msg);
    			} else {
    				notificationTopAlert(data.msg);
    				//fn_egov_link_refresh_callhstry();
    				//removeAdmPublicPanelLayer('${CUSTOM_UNIQ_KEY}');
    			}
    		},
    		complete : function() {
    			//console.log('complete!');
    			$('.clk-edu-add-${CUSTOM_UNIQ_KEY}').removeClass('disabled');
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			//console.log(jqXHR);
    			//console.log(textStatus);
    			//console.log(errorThrown);
    			alertify.alert(textStatus);
    		}
    	});  
    	
    	
	});
    //End of 회원 교육 신청
    
    </script>
    <script defer="defer">
	//------------------------------------------------------
	// 브라우저에 맞게 세로 크기 조정
	//------------------------------------------------------
	$(function(){
		var h = 0;
	  	if(document.body.clientHeight == 0) {
	  		h = window.innerHeight-158;
	  		bh = window.innerHeight;
	  	} else {
	  		h = document.body.clientHeight-158;
	  		bh = document.body.clientHeight;
	  	}
	  	$('#admPublicPanelLayer${CUSTOM_UNIQ_KEY} .panel-body-${CUSTOM_UNIQ_KEY}').css('max-height',h-56);
	});
	//------------------------------------------------------
	//팝업 드래그 위치 변경 
	//------------------------------------------------------
    $("#admPublicPanelLayer${CUSTOM_UNIQ_KEY}").draggable({
		handle: ".draggable-move",
		stop: function() {
			return $(this).css({
				height: 'auto'
		    });
		},
		drag: function( event, ui){
			//드래그 오버 방지
			var w = 0;
			var h = 0;
		  	if(document.body.clientHeight == 0) {
		  		w = window.innerWidth;
		  		h = window.innerHeight;
		  	} else {
		  		w = document.body.clientWidth;
		  		h = document.body.clientHeight;
		  	}
			if(ui.position.top < 0) ui.position.top = 0;
			if(ui.position.top > h-60) ui.position.top = h-60;
			if(ui.position.left < -800) ui.position.left = -800;
			if(ui.position.left > w-100) ui.position.left = w-100;
	  	}
	});
    </script> 