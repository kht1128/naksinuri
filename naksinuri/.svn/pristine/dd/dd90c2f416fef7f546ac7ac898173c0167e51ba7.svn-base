<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<%@ include file="../head.jsp" %>
<%@ include file="../left_menu.jsp" %>

<form:form commandName="eduMemberVO" id="IndvdlInfoViewForm" name="IndvdlInfoViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_HP" value=""/>
</form:form> 
 
<form:form commandName="CodeSetVO" id="searchFormSido" name="searchFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="searchUseYn" value="Y" />
	<input type="hidden" id="MBR_ID" name="MBR_ID" value=""/>
	<input type="hidden" id="MBR_NM" name="MBR_NM" value=""/>
	<input type="hidden" id="LOG_UPD_MSG" name="LOG_UPD_MSG" value=""/>
	
	
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
			<c:if test="${LoginVO.MBR_LV_ID eq '1'}">
	        	<a class="append-rows btn btn-primary btn-outline clk_add_data" href="javascript:void(0)" 
	        	data-crs-sn="" 
	        	data-type-str=""
	        	data-linkurl="/eduadm/member/write_adm.do">
	        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
	        		<span class="hidden-sm-down">관리자 신규 등록</span>
	        	</a> 
        	</c:if>
        </div>
      </div>
		
      <div class="page-content container-fluid">
         <div class="row">
          <div class="col-xl-12">
      
      		<div class="panel mb-20">
      			<!-- 
				<div class="panel-heading">
	    			<h3 class="panel-title">검색&nbsp;&nbsp;
	      				&nbsp;<small>검색1</small>
	    			</h3>
	  			</div>
	  			 -->
	  			<div class="panel-body">
					<div class="input-group col-lg-12 p-0">
						<div class="btn-group col-lg-2 p-0">
							<span class="text-help">권한별 조회 옵션</span>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_POSITION_CD" >
								<option value="" >관리자유형 전체</option>
								<c:forEach var="item_category" items="${list_position_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq MBR_POSITION_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach>	
		      				</select>	
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_sido" name="MBR_SIDO_CD" >
								<option value="" >시도 전체</option>
								<c:forEach var="item_category" items="${list_address_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq MBR_SIDO_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_signgu" name="MBR_SIGNGU_CD" >
								<option value="" >시군구 전체</option>
								<c:forEach var="item_category" items="${list_address_signgu_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq MBR_SIGNGU_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_TRGT_CD" >
								<option value="" >교육대상 전체</option>
								<c:forEach var="item_category" items="${list_mbr_trgt_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq MBR_TRGT_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach>	
		      				</select>	
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_EDU_INS_CD" >
								<option value="" >교육기관 전체</option>
								<c:forEach var="item_category" items="${list_ins_info_cd}">
									<option value="${item_category.CAT_INS_SN}" <c:if test="${item_category.CAT_INS_SN eq MBR_EDU_INS_CD }">selected</c:if> >${item_category.CAT_INS_NM}</option>
								</c:forEach>	
		      				</select>	
						</div>
					</div>
					
					<div class="input-group col-lg-12 p-0 mt-10">
						<div class="col-lg-3 p-0">
							
						</div>		
						<div class="col-lg-3 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_ST">
								<option value="" <c:if test="${empty MBR_ST}">selected</c:if> >회원상태 전체보기</option>    
								<option value="Y" <c:if test="${MBR_ST eq 'Y'}">selected</c:if> >활성화(로그인가능)</option>
								<option value="N" <c:if test="${MBR_ST eq 'N'}">selected</c:if> >비활성화(로그인제한)</option>
								<option value="R" <c:if test="${MBR_ST eq 'R'}">selected</c:if> >승인대기</option>
								<option value="F" <c:if test="${MBR_ST eq 'F'}">selected</c:if> >승인거부</option>            			
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
						<div class="col-lg-4 p-0 pl-10 text-right">
							<div class="btn-group w-p100">
								<input type="text" class="form-control input-auto-enter-key" name="searchKeyword" placeholder="이름,연락처,아이디,소속(부서),호칭 등을 입력하세요." autocomplete="off" value="${searchKeyword}">
								<button type="button" class="btn btn-primary clk_search_btn"><i class="icon wb-search" aria-hidden="true"></i></button>
							</div>
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
	          				<th class="text-middle text-center" rowspan="2">No</th>
		          			<th class="text-middle text-center" rowspan="2">이름<br>(아이디)</th>
							<th class="text-middle text-center" rowspan="2">소속기관<br>(소속부서)</th>
	           				<th class="text-middle text-center" rowspan="2">연락처<br>(휴대폰)<br>연락처<br>(일반)</th>
	           				<th class="text-middle text-center" colspan="5">권한</th>
	            			<th class="text-middle text-center" rowspan="2">비고</th>
            			</tr>        				
            			<tr>
	           				<th class="text-middle text-center">관리자유형</th>
	           				<th class="text-middle text-center">지역관리<br>권한</th>
	           				<th class="text-middle text-center">교육대상관리권한</th>
	           				<th class="text-middle text-center">교육과정(기관)<br>관리권한</th>
	           				<th class="text-middle text-center">CTI접근<br>권한</th>
            			</tr>
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="10"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">조회 가능한 회원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		     				<c:set var="isHideCti" value="false"/>       				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle text-center">${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}</td>	
				                <td class="text-middle">
				                	<c:choose>
										<c:when test="${empty item.MBR_ST or item.MBR_ST eq 'N'}">
											<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											<c:set var="isHideCti" value="true"/>
										</c:when>
										<c:when test="${item.MBR_ST eq 'R'}">
											<i class="site-menu-icon wb-user-add red-600" aria-hidden="true" data-content="현재 승인대기 중인 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											<c:set var="isHideCti" value="true"/>
										</c:when>
										<c:when test="${item.MBR_ST eq 'F'}">
											<i class="site-menu-icon wb-user-add blue-grey-400" aria-hidden="true" data-content="현재 승인거부 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											<c:set var="isHideCti" value="true"/>
										</c:when>
										<c:when test="${empty item.MBR_MOD_DT}">
											<i class="site-menu-icon  blue-600" aria-hidden="true" data-content="신규 생성된 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											<c:set var="isHideCti" value="true"/>
										</c:when>
										<c:otherwise>
											
										</c:otherwise>											
									</c:choose>
					                ${item.MBR_NM}
					                <br><span class="grey-400">( ${item.MBR_ID} )</span>
				                </td>
				                <td class="text-middle text-center">${item.MBR_NCNM}<br><span class="grey-400">( ${item.MBR_PSITN_NM} )</span></td>
				                <td class="text-middle text-center">${pfpu:phoneHypen(item.MBR_HP)}<br>${pfpu:phoneHypen(item.MBR_TEL)}</td>
				                <td class="text-middle text-center">
				                	<c:forEach var="item_category" items="${list_position_cd}">
										<c:if test="${item.MBR_POSITION_CD eq item_category.CD_ID}">${item_category.CD_DES}</c:if>
									</c:forEach>
									<%-- <c:if test="${item.MBR_LV_ID eq '1'}">
										<option value="${item.MBR_POSITION_CD}" selected>통합관리자</option>
									</c:if> --%>
				                </td>
				                <td class="text-middle text-center">
				                	<c:if test="${empty item.MBR_SIDO_CD}">시도 제한없음</c:if>
									<c:forEach var="item_category" items="${list_address_cd}">
										<c:if test="${item.MBR_SIDO_CD eq item_category.CD_ID}">${item_category.CD_NM}</c:if>
									</c:forEach>
									<br>
									<c:choose>
										<c:when test="${empty item.MBR_SIGNGU_CD}">시군구 제한없음</c:when>
										<c:otherwise>${item.SIGNGU_NM}</c:otherwise>
									</c:choose>
				                </td>
				                <td class="text-middle text-center">
				                	<c:if test="${empty item.MBR_TRGT_CD}">제한없음</c:if>
				                	<c:forEach var="item_category" items="${list_mbr_trgt_cd}">
										<c:if test="${item.MBR_TRGT_CD eq item_category.CD_ID}">${item_category.CD_DES}</c:if>
									</c:forEach>
				                </td>
				                <td class="text-middle text-center">
				                	<c:if test="${empty item.MBR_EDU_INS_CD}">제한없음</c:if>
				                	<c:forEach var="item_category" items="${list_ins_info_cd}">
										<c:if test="${item.MBR_EDU_INS_CD eq item_category.CAT_INS_SN}">${item_category.CAT_INS_NM}</c:if>
									</c:forEach>
				                </td>
				                <td class="text-middle text-center">
				                	<c:if test="${item.MBR_GRP_4_ST eq 'Y'}">사용가능</c:if>
				                </td>
				                <td class="text-middle text-center">
				                	<div>
				                		<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-mbr-id="${item.MBR_ID}" 
					                	data-linkurl="/eduadm/member/modify_adm.do">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
					                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
					                	data-mbr-id="${item.MBR_ID}"
					                	data-mbr-st="${item.MBR_ST}">
					                		<i class="icon wb-trash" aria-hidden="true"></i>
					                	</a>
				                	</div>
				                	<c:if test="${item.MBR_ST eq 'Y' and item.MBR_LV_ID ne '5'}">
					                	<div>
					                		<button type="button" class="btn btn-outline btn-default btn-xs"
					                		onclick="clk_indvdl_info_view('${item.MBR_ID}', '${item.MBR_HP }')">이용정보동의서</button>
					                	</div>
				                	</c:if>
				                	<c:if test="${isHideCti eq false}">
					                	<div>
					                		<button type="button" class="btn btn-outline btn-info btn-xs clk_cti_data"
					                		data-linkurl="/cti/member/manager.do"
					                		data-mbr-id="${item.MBR_ID}">CTI 관리</button>
					                	</div>			                
				                	</c:if>	
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

    <script>
    $('.input-auto-enter-key').keypress(function(key) {
    	if(key.keyCode == 13){
    		$('.clk_search_btn').click();
        }
    });
    $(function(){
    	$('.selectpicker_manual').selectpicker();
    });
    function fn_egov_link_page(pageNo){
    	document.listForm.pageIndex.value = pageNo;
    	document.listForm.action = "";
       	document.listForm.submit();
    }
    $(".clk_search_btn").click(function() {
    	var form = document.getElementById('listForm');
    	form.pageIndex.value = '1';
    	form.action = '';
    	form.submit();
    });   
    $("#search_sel_area_sido").change(function() {
    	if(this.value=='') {
    		$('#search_sel_area_signgu').html('<option value="">시군구 전체</option>').selectpicker('refresh');
    		return;
    	}  
    	var form = document.getElementById('searchFormSido');
    	form.CD_MASTER_ID.value = this.value;
    	form.action = ''; 
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
    $(".clk_add_data").click(function() {
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:null,
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#seaAdmEduPublicModal").html(data);
				$("#seaAdmEduPublicModal").modal('show');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	});
    $(".clk_mod_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.MBR_ID.value = $(this).attr('data-mbr-id');
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
				$("#seaAdmEduPublicModal").html(data);
				$("#seaAdmEduPublicModal").modal('show');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	});  
    $(".clk_cti_data").click(function() {
    	var form = document.getElementById('listForm');
    	form.MBR_ID.value = $(this).attr('data-mbr-id');
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
				$("#seaAdmEduPublicModal").html(data);
				$("#seaAdmEduPublicModal").modal('show');
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
    	var data_mbr_id = $(this).attr('data-mbr-id');
    	var data_mbr_st = $(this).attr('data-mbr-st');
    	var alert_message = "";
    	if(data_mbr_st == 'N') {
    		alert_message = "회원 데이터를 삭제합니다. 삭제시 복구가 불가능하며 <br>삭제하려는 사유를 입력해주세요.";
    	} else {
    		alert_message = "회원을 사용안함으로 변경하는 사유를 입력해주세요.";
    	}
    	//alertify.confirm(alert_message, function(){
    	alertify.prompt(alert_message,function(val) {
    		console.log("val : " + val);
    	
     		//ok
   			if(val.trim()=='') {
   				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
   				return;
   			} 	
    		
    		//ok
    		var form = document.getElementById('listForm');
    		form.MBR_ID.value = data_mbr_id;
    		form.LOG_UPD_MSG.value = val;
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/member/delete_act.do",
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
    						window.location.reload();	
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
    	}, function() {});//cancel 
   	});
    
  //이용정보동의서
    function clk_indvdl_info_view(MBR_ID, MBR_HP){
     	var form = document.getElementById('IndvdlInfoViewForm');
     	console.log(MBR_ID);
     	form.MBR_ID.value = MBR_ID;
     	form.MBR_HP.value = MBR_HP;
     /* var data-rmndr-crs-sn = $(this).attr('data-rmndr-crs-sn');
     	form.RMNDR_CRS_SN.value = data-rmndr-crs-sn; */
     	
     	$.ajax({
  			type:"POST",
  			url :'/eduadm/member/indvdl_info_view.do',
  			data:$('#IndvdlInfoViewForm').serialize(),
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
    	}
    </script>

<%@ include file="../tail.jsp" %>
