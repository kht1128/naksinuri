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

<!-- Page -->
<div class="page cti-contents">
	<div class="page-content container-fluid">
		<div class="row">
		
		
		    <!-- //IVR 및 상담분류 카테고리 관리 -->
		    <div class="col-xl-12">
		    	<div class="panel mb-20">
	          		<div class="dash-title">
	          			메뉴얼 카테고리 관리
	          		</div>
		  			<div class="panel-body p-20">

						<div class="row p-0 m-0 mb-10 ">
							<div class="col-md-6 pr-5 h-100">
								<select class="form-control" multiple="" id="HCALL_GUBUN_1" size="6">
				                   	<c:forEach var="item" items="${list_category}">
										<option value="${item.CD_ID }" data-cd-nm="${item.CD_NM}" data-cd-master-id="${item.CD_MASTER_ID}" data-cd-ord-no="${item.CD_ORD_NO}"><c:out value="${item.CD_NM }"></c:out></option>
									</c:forEach>					                   	
				                </select>
				                
				                <input type="hidden" id="TRG_CD_ID_1_CHG" value="" disabled/>
				                
				                <div class="col-md-12 p-0 m-0">
				                	<div class="input-group mt-5">
										<div class="input-group-addon">
		    								<span class="input-group-text">명칭</span> 
		            					</div>
		           						<input class="form-control" type="text" id="TRG_CD_NM_1" value="" placeholder="명칭을 입력해주세요."/>
		         					</div>
		         					<div class="input-group mt-5">
										<div class="input-group-addon">
		    								<span class="input-group-text">코드 아이디</span> 
		            					</div>
		           						<input class="form-control" type="text" id="TRG_CD_ID_1" value="" placeholder="코드 아이디를 입력해주세요."/>
		         					</div>
		         					<div class="input-group mt-5" hidden>
										<div class="input-group-addon">
		    								<span class="input-group-text">상위 카테고리 아이디</span> 
		            					</div>
		           						<input class="form-control" type="text" id="TRG_CD_MASTER_ID_1" value="" placeholder="상위 카테고리 아이디를 입력해주세요."/>
		         					</div>
		         							         					<div class="input-group mt-5">
										<div class="input-group-addon">
		    								<span class="input-group-text">정렬순서</span> 
		            					</div>
		           						<input class="form-control" type="text" id="TRG_CD_ORD_NO_1" value="" placeholder="정렬순서를 입력해주세요."/>
		         					</div>
				                </div>
				                
				                 <div class="col-md-12 p-0 m-0 text-right">
				                 	<div class="mt-10">
					                 	<button class="btn btn-primary btn-outline trg_btn_add" data-type="HCALL_GUBUN_1">추가
					                 	</button>
					                 	<button class="btn btn-info btn-outline trg_btn_mod" data-type="HCALL_GUBUN_1">변경</button>
					                 	<button class="btn btn-warning btn-outline trg_btn_del" data-type="HCALL_GUBUN_1">삭제</button>
					                 </div>
				                 </div>
				                
			                 </div>
			                 <div class="col-md-6 pl-5">
				                 <select class="form-control " multiple="" id="HCALL_GUBUN_2" size="6">
				                    <option value="">상담분류1을 선택해주세요.</option>
				                  </select>
				                  
				                  <div class="col-md-12 p-0 m-0">
				                	<div class="input-group mt-5">
										<div class="input-group-addon">
		    								<span class="input-group-text">명칭</span> 
		            					</div>
		           						<input class="form-control" type="text" id="TRG_CD_NM_2" value="" placeholder="명칭을 입력해주세요."/>
		         					</div>
		         					<div class="input-group mt-5">
										<div class="input-group-addon">
		    								<span class="input-group-text">코드 아이디</span> 
		            					</div>
		           						<input class="form-control" type="text" id="TRG_CD_ID_2" value="" placeholder="코드 아이디를 입력해주세요."/>
		         					</div>
		         					<div class="input-group mt-5">
										<div class="input-group-addon">
		    								<span class="input-group-text">상위 카테고리 아이디</span> 
		            					</div>
		           						<input class="form-control" type="text" id="TRG_CD_MASTER_ID_2" value="" placeholder="상위 카테고리 아이디를 입력해주세요."/>
		         					</div>
		         					<div class="input-group mt-5">
										<div class="input-group-addon">
		    								<span class="input-group-text">정렬순서</span> 
		            					</div>
		           						<input class="form-control" type="text" id="TRG_CD_ORD_NO_2" value="" placeholder="정렬순서를 입력해주세요."/>
		         					</div>
				                </div>	
				                
				                 <div class="col-md-12 p-0 m-0 text-right">
				                 	<div class="mt-10">
					                 	<button class="btn btn-primary btn-outline trg_btn_add" data-type="HCALL_GUBUN_2">추가</button>
					                 	<button class="btn btn-info btn-outline trg_btn_mod" data-type="HCALL_GUBUN_2">변경</button>
					                 	<button class="btn btn-warning btn-outline trg_btn_del" data-type="HCALL_GUBUN_2">삭제</button>
					                 </div>
				                 </div>		                  
				                  
			                  </div>
		                 </div>
					</div>
				</div>
		    </div>
		    <!-- //End of IVR 및 상담분류 카테고리 관리 -->
			
			 <!-- //메뉴얼 카테고리 관리 -->
		    <div class="col-xl-12">
		    	<div class="panel mb-20">
	          		<div class="dash-title">
	          			메뉴얼 항목 관리
	          			<br><span class="blue-grey-400 font-size-12 pl-10 mt-5">* 신규추가는 대분류, 소분류를 먼저 선택하여 <b>검색</b> 한 후 추가 할 수 있습니다.</span>
	          		</div>
		  			<div class="panel-body p-20">
					<div class="panel">
						
						<form:form commandName="ctiBoardVO" id="ctiListFormBoard${CUSTOM_UNIQ_KEY}" name="ctiListFormBoard${CUSTOM_UNIQ_KEY}" method="post">
						<input type="hidden" name="searchUseYn" value="Y" />	
						<div class="panel-title pl-0 pr-0 pt-5 pb-5">
							<div class="row col-md-12 w-p100 m-0 pl-0 pr-0">
								<div class="col-md-4 pl-0 pr-5">
				 					<select class="form-control selectpicker_manual-tab-${CUSTOM_UNIQ_KEY}" id="BD_CATEGORY_CD_${CUSTOM_UNIQ_KEY}" data-style="btn-outline btn-default" name="BD_CATEGORY_CD">
					 					<option value="">대분류(전체)</option>
					 					<c:forEach var="item_category" varStatus="status_category" items="${list_category_gubun_1}">
					 						<option data-cd-sn="${item_category.CD_SN }" value="${item_category.CD_ID }" <c:if test="${item_category.CD_ID eq BD_CATEGORY_CD or item_category.CD_ID eq HCALL_IVR_CD}">selected</c:if> >${item_category.CD_NM }</option>
					 					</c:forEach>
					 				</select>
				 				</div>
				 				<div class="col-md-4 pl-5 pr-0">
					 				<select class="form-control selectpicker_manual-tab-${CUSTOM_UNIQ_KEY}" id="BD_CATEGORY_CD2_${CUSTOM_UNIQ_KEY}" data-style="btn-outline btn-default" name="BD_CATEGORY_CD2">
					 					<option value="">소분류(전체)</option>
					 					<c:forEach var="item_category" varStatus="status_category" items="${list_category_gubun_2}">
					 						<option value="${item_category.CD_ID }" <c:if test="${item_category.CD_ID eq BD_CATEGORY_CD2}">selected</c:if> >${item_category.CD_NM }</option>
					 					</c:forEach>
					 				</select>
				 				</div>
				 				<div class="col-md-4 pl-5 pr-0 input-group">
				 					<input type="text" class="form-control input-tab-auto-enter-key-${CUSTOM_UNIQ_KEY}" name="searchKeyword" placeholder="검색어를 입력하세요." autocomplete="off" value="${searchKeyword}">
									<span class="input-group-append">
										<button type="button" class="btn btn-xs btn-default" id="clk_search_btn_tab_manual_${CUSTOM_UNIQ_KEY}">검색</button>
									</span>
				 				</div>
			 				</div>
						</div>
						</form:form>
							
						<div class="panel-body pl-0 pr-0 pt-0 pb-0">
							<!-- table:start -->
							<table class="table table-hover footable footable-paging footable-paging-center " id="manual_table">
						    	<colgroup>
						    		<col />
						    		<col />
						    		<col width="100px"/>
						    		<col width="180px"/>
						    	</colgroup>
						    	<thead>
						        <tr>
									<th class="text-middle text-center">안내</th>
									<th class="text-middle text-center">주의사항</th>
									<th class="text-middle text-center rank_th">정렬순서</th>
									<th class="text-middle text-center">관리</th>
								</tr>
								</thead>
								<tbody>
								<c:set var="table_cell_cnt" value="2"/>
								<c:if test="${empty list}">
						           		<tr><td colspan="${table_cell_cnt}" class="text-center table-active">등록 된 자료가 없습니다.</td></tr>  
						           	</c:if>
						           	<c:forEach var="item" varStatus="status" items="${list}">
							           	<tr >
											<td class="text-middle text-center">
												<input type="text" class="form-control bg-white border-0 disabled" id="trg_input_title_${status.index}" name="" placeholder="" autocomplete="off" value="${item.BD_TITLE}"  disabled>
											</td>
											<td class="text-middle text-center">
												<input type="text" class="form-control bg-white border-0 disabled" id="trg_input_cont_${status.index}" name="" placeholder="" autocomplete="off" value="${item.BD_CONT}" disabled>
											</td>
											<td class="text-middle text-center">
												<input type="text" class="form-control bg-white border-0 disabled rank_hide" id="trg_input_rank_${status.index}" name="" placeholder="" autocomplete="off" value="${item.BD_RANK}" disabled>
											</td>
											<td class="text-middle text-center">
												<div id="aaaa${status.index}" style="display:none;">
													<button class="btn btn-info btn-outline mn_btn_save" data-idx="${status.index}" data-bdsn="${item.BD_SN}">저장</button>
													<button class="btn btn-defalut btn-outline mn_btn_cancel" data-idx="${status.index}">취소</button>
												</div>
												<div id="bbbb${status.index}">
													<button class="btn btn-info btn-outline mn_btn_mod" data-idx="${status.index}">변경</button>
							                 		<button class="btn btn-warning btn-outline mn_btn_del" data-bdsn="${item.BD_SN}">삭제</button>
							                 	</div>											
											</td>
										</tr>								
									</c:forEach>
										<tr id="menual_add" style="display:none;">
											<td class="text-middle text-center"><input type="text" class="form-control" id="BD_TITLE_ADD" name="BD_TITLE_ADD" placeholder="내용을 입력하세요." autocomplete="off"  ></td>
											<td class="text-middle text-center"><input type="text" class="form-control" id="BD_CONT_ADD" name="BD_CONT_ADD" placeholder="내용을 입력하세요." autocomplete="off" ></td>
											<td class="text-middle text-center"><input type="text" class="form-control rank_hide" id="BD_RANK_ADD" name="BD_RANK_ADD" placeholder="정렬순서" autocomplete="off" ></td>
											<td class="text-middle text-center">
												<button class="btn btn-primary btn-outline mn_btn_add" >추가</button>									
											</td>
										</tr>
								</tbody>				  
							</table>
							<!-- table:end -->
						</div>
					</div>
					
				</div>
			</div>
		</div>
  		<!-- /메뉴얼 카테고리 관리 -->
		</div>
	</div>	
</div>
<!-- End Page -->

	<style>
	.datepicker-dropdown{z-index:99999!important;}
	</style>

    <script>
    $(function(){
    	$(".selec2_manual-curriculum").select2();
    	$('.selectpicker_manual').selectpicker();
    	
    	var BD_CATEGORY_CD = '${BD_CATEGORY_CD}';
    	var BD_CATEGORY_CD2 = '${BD_CATEGORY_CD2}';
    	if(BD_CATEGORY_CD!=null&BD_CATEGORY_CD!=''&&BD_CATEGORY_CD2!=null&BD_CATEGORY_CD2!='') {
    		$('#menual_add').show();
    	}else{
     		$('.rank_th').html('');
//      		$('.rank_hide').val('');
     		$('.rank_hide').hide();
     		
    	}
    	
    });
    
    $('#HCALL_GUBUN_1').change(function(){
    	var target = $("#HCALL_GUBUN_1 option:selected");
    	var cd_id = target.val();
    	var cd_nm = target.attr('data-cd-nm');
    	var cd_master_id = target.attr('data-cd-master-id');
    	var cd_ord_no = target.attr('data-cd-ord-no');
    	
    	$('#TRG_CD_NM_1').val(cd_nm);
    	$('#TRG_CD_ID_1').val(cd_id);
    	$('#TRG_CD_MASTER_ID_1').val(cd_master_id);
    	$('#TRG_CD_MASTER_ID_2').val(cd_id);
    	$('#TRG_CD_ORD_NO_1').val(cd_ord_no); 
    	$('#TRG_CD_ID_1_CHG').val(cd_id);
    	
    	$.ajax({
    		type:"POST",
    		url :"/cti/category/manager.do",
    		data:{
    			CD_ID : cd_id,
    		},
    		dataType: 'html',
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function(xhr, opts) {
    			
    		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			$('#HCALL_GUBUN_2').html(data);
    		},
    		complete : function() {
    			//console.log('complete!');
    			
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			console.log(jqXHR);
    			console.log(textStatus);
    			console.log(errorThrown);
    			alertify.alert(textStatus);
    		}
    	});  
    	
    });
    
    $('#HCALL_GUBUN_2').change(function(){
    	var target = $("#HCALL_GUBUN_2 option:selected");
    	var cd_id = target.val();
    	var cd_nm = target.attr('data-cd-nm');
    	var cd_master_id = target.attr('data-cd-master-id');
    	var cd_ord_no = target.attr('data-cd-ord-no');
    	
    	$('#TRG_CD_NM_2').val(cd_nm);
    	$('#TRG_CD_ID_2').val(cd_id);
    	$('#TRG_CD_MASTER_ID_2').val(cd_master_id);    	
    	$('#TRG_CD_ORD_NO_2').val(cd_ord_no);    	

    });
      
 $('.trg_btn_add').click(function(){
	 
	  	var cd_master_id = '';
		var type = $(this).attr('data-type');
		if(type == 'HCALL_GUBUN_1') {
			cd_master_id = '';
		} else if(type == 'HCALL_GUBUN_2') {
			cd_master_id = $('#TRG_CD_ID_1').val();
			if(cd_master_id.trim()=='' || cd_master_id.trim().length==0) {
				alertify.alert('상담분류2를 추가하려면 상담분류1을 먼저 선택해주세요.');
				return;
			}
		}
	 
		$.ajax({
    		type:"POST",
    		url :"/cti/category/write.do",
    		data:{
    			CD_MASTER_ID : cd_master_id,
    		},
    		dataType: 'html',
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		success: function(data, status, xhr) {
    			$("#eduAdmEduPublicModal").html(data);
				$("#eduAdmEduPublicModal").modal('show');
    		},
    		complete : function() {
    			//console.log('complete!');
    			
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			console.log(jqXHR);
    			console.log(textStatus);
    			console.log(errorThrown);
    			alertify.alert(textStatus);
    		}
    	});  
	
    });
    
    $('.trg_btn_mod').click(function(){
    	
    	var cd_nm = '';
    	var cd_id = '';
    	var cd_master_id = '';
    	var cd_ord_no = '';
    	var cd_id_chg = '';
    	var alert_message = "변경하시겠습니까?";
    	
    	var type = $(this).attr('data-type');
    	if(type == 'HCALL_GUBUN_1') {
    		cd_nm = $('#TRG_CD_NM_1').val();
    		cd_id = $('#TRG_CD_ID_1').val();
    		cd_master_id = $('#TRG_CD_MASTER_ID_1').val();
    		cd_ord_no = $('#TRG_CD_ORD_NO_1').val();
    		cd_id_chg = $('#TRG_CD_ID_1_CHG').val();
    	} else if(type == 'HCALL_GUBUN_2') {
    		cd_nm = $('#TRG_CD_NM_2').val();
    		cd_id = $('#TRG_CD_ID_2').val();
    		cd_master_id = $('#TRG_CD_MASTER_ID_2').val();
    		cd_ord_no = $('#TRG_CD_ORD_NO_2').val();
    	}
    	
    	alertify.confirm(alert_message, function(){
    	$.ajax({
    		type:"POST",
    		url :"/cti/category/modify_act.do",
    		data:{
    			CD_NM : cd_nm,
    			CD_ID : cd_id,
    			CD_MASTER_ID : cd_master_id,
    			CD_ORD_NO : cd_ord_no,
    			CD_ID_CHG : cd_id_chg,
    			CD_GB : type,  
    		},
    		dataType: 'html',
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		beforeSend : function(xhr, opts) {
    			
    		},
    		success: function(data, status, xhr) {
    			//console.log('success!');
    			//alertify.alert(textStatus);
    			window.location.reload();
    		},
    		complete : function() {
    			//console.log('complete!');
    			
    	    },
    		error: function(jqXHR, textStatus, errorThrown) {
    			//console.log('error!');
    			console.log(jqXHR);
    			console.log(textStatus);
    			console.log(errorThrown);
    			alertify.alert(textStatus);
    		}
    	});  
      });
    });
    
    $(".trg_btn_del").click(function() {
    		var cd_id = '';
    		var alert_message = "";
    		
	    	var type = $(this).attr('data-type');
	    	if(type == 'HCALL_GUBUN_1') {
	    		cd_id = $('#TRG_CD_ID_1').val();
	    		cd_master_id = $('#TRG_CD_MASTER_ID_2').val();
	    		alert_message = "하위 상담분류 카테고리 일괄 삭제 됩니다.<br>삭제 후 복구되지 않습니다.<br>삭제 하시겠습니까?";
	    	} else if(type == 'HCALL_GUBUN_2') {
	    		cd_id = $('#TRG_CD_ID_2').val();
	    		cd_master_id = '';
	    		alert_message = "삭제 후 복구되지 않습니다.<br>삭제 하시겠습니까?";
	    	}
	    	
	    	alertify.confirm(alert_message, function(){
		    	$.ajax({
		    		type:"POST",
		    		url :"/cti/category/delete_act.do",
		    		data:{
		    			CD_ID : cd_id,
		    			CD_MASTER_ID : cd_master_id,
		    		},
		    		dataType: 'html',
		    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		    		async: true,
		    		beforeSend : function(xhr, opts) {
		    			
		    		},
		    		success: function(data, status, xhr) {
		    			//console.log('success!');
		    			//alertify.alert(data.msg);
		    			window.location.reload();
		    		},
		    		complete : function() {
		    			//console.log('complete!');
		    			
		    	    },
		    		error: function(jqXHR, textStatus, errorThrown) {
		    			//console.log('error!');
		    			console.log(jqXHR);
		    			console.log(textStatus);
		    			console.log(errorThrown);
		    			//alertify.alert(textStatus);
		    		}
		    	});  	
	    	});   	
	  });
    
    $(function(){
    	$('.selectpicker_manual-tab-${CUSTOM_UNIQ_KEY}').selectpicker();
    	$('.input-tab-auto-enter-key-${CUSTOM_UNIQ_KEY}').keypress(function(key) {
        	if(key.keyCode == 13){
        		$('#clk_search_btn_tab_manual_${CUSTOM_UNIQ_KEY}').click();
            }
        });
    });
   
    	//검색버튼
    	$('#clk_search_btn_tab_manual_${CUSTOM_UNIQ_KEY}').click(function(){
    		var form = document.getElementById("ctiListFormBoard${CUSTOM_UNIQ_KEY}");
    			form.submit();
    	});
    	
    	//카테고리 상담분류
        $("#BD_CATEGORY_CD_${CUSTOM_UNIQ_KEY}").change(function() {
        	var cd_sn = $("#BD_CATEGORY_CD_${CUSTOM_UNIQ_KEY} option:selected").attr('data-cd-sn');
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
        					$('#BD_CATEGORY_CD2_${CUSTOM_UNIQ_KEY}').append('<option value="'+item.cd_id+'">'+item.cd_nm+'</option>');
        				}
        				$('#BD_CATEGORY_CD2_${CUSTOM_UNIQ_KEY}').selectpicker('refresh');
        			}
        		},
        		beforeSend : function() {
        			$('#BD_CATEGORY_CD2_${CUSTOM_UNIQ_KEY}').html('<option value="">소분류(전체)</option>');
        			$('#BD_CATEGORY_CD2_${CUSTOM_UNIQ_KEY}').selectpicker('refresh');
        		},
        		complete : function() {
        			//console.log('complete!');
        	    },
        		error: function(jqXHR, textStatus, errorThrown) {
        			//console.log('error!');
        		}
        	});
        });  
    	
        $('.mn_btn_add').click(function(){
       	 
    	  	var bd_title = '';
    	  	var bd_cont = '';
    	  	bd_catagory_cd = '${BD_CATEGORY_CD}';
    	  	bd_catagory_cd2 = '${BD_CATEGORY_CD2}';
    	  	bd_title = $('#BD_TITLE_ADD').val();
    	  	bd_cont = $('#BD_CONT_ADD').val();
    	  	bd_rank = $('#BD_RANK_ADD').val();
    	 	
    	  	if(bd_title=='' || bd_title.length==0) {
    	  		alertify.alert('내용을 입력하세요.');
    	  		return;
    	  	}
    	  	
//     	  	if(bd_cont=='' || bd_cont.length==0) {
//     	  		alertify.alert('내용을 입력하세요.');
//     	  		return;
//     	  	}
    	  	
    	  	if(bd_rank=='' || bd_rank.length==0) {
    	  		alertify.alert('정렬순서를 입력하세요.');
    	  		return;
    	  	}
    	  	    	  	
    		$.ajax({
        		type:"POST",
        		url :"/cti/category/menual_write_act.do",
        		data:{
        			BD_TITLE : bd_title,
        			BD_CONT : bd_cont,
        			BD_CATEGORY_CD : bd_catagory_cd,
        			BD_CATEGORY_CD2 : bd_catagory_cd2,
        			BD_RANK : bd_rank,
        		},
        		dataType: 'html',
        		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
        		async: true,
        		success: function(data, status, xhr) {
        			//console.log('success!');
        			//alertify.alert(textStatus);
        			$('#clk_search_btn_tab_manual_${CUSTOM_UNIQ_KEY}').trigger('click');
        		},
        		complete : function() {
        			//console.log('complete!');
        			
        	    },
        		error: function(jqXHR, textStatus, errorThrown) {
        			//console.log('error!');
        			console.log(jqXHR);
        			console.log(textStatus);
        			console.log(errorThrown);
        			alertify.alert(textStatus);
        		}
        	});  
    	
        });
        
        
        $('.mn_btn_mod').click(function(){
        	
        	var idx = $(this).attr('data-idx');
        	$('#aaaa'+idx).show();
        	$('#bbbb'+idx).hide();
        	$('#trg_input_title_'+idx).removeClass('bg-white border-0 disabled').attr('disabled',false);
        	$('#trg_input_cont_'+idx).removeClass('bg-white border-0 disabled').attr('disabled',false);
        	$('#trg_input_rank_'+idx).removeClass('bg-white border-0 disabled').attr('disabled',false);
        });
        
     	$('.mn_btn_save').click(function(){
        	
        	var idx = $(this).attr('data-idx');
        	$('#aaaa'+idx).hide();
        	$('#bbbb'+idx).show();
        	$('#trg_input_title_'+idx).addClass('bg-white border-0 disabled').attr('disabled',true);
        	$('#trg_input_cont_'+idx).addClass('bg-white border-0 disabled').attr('disabled',true);	
        	$('#trg_input_rank_'+idx).addClass('bg-white border-0 disabled').attr('disabled',true);	
        	
        	var bd_title = '';
    	  	var bd_cont = '';
    	  	var bd_rank = '';
    	  	var bd_catagory_cd = '${BD_CATEGORY_CD}';
    	  	var bd_catagory_cd2 = '${BD_CATEGORY_CD2}';
 		  	var  bd_sn = $(this).attr('data-bdsn');
    	  	bd_title = $('#trg_input_title_'+idx).val();
    	  	bd_cont = $('#trg_input_cont_'+idx).val();
    	  	bd_rank = $('#trg_input_rank_'+idx).val();
    	  	
        	var alert_message = "변경하시겠습니까?";
        	
        	alertify.confirm(alert_message, function(){
        	$.ajax({
        		type:"POST",
        		url :"/cti/category/menual_modify_act.do",
        		data:{
        			BD_SN : bd_sn,
        			BD_TITLE : bd_title,
        			BD_CONT : bd_cont,
        			BD_CATEGORY_CD : bd_catagory_cd,
        			BD_CATEGORY_CD2 : bd_catagory_cd2, 
        			BD_RANK : bd_rank,
        		},
        		dataType: 'html',
        		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
        		async: true,
        		beforeSend : function(xhr, opts) {
        			
        		},
        		success: function(data, status, xhr) {
        			//console.log('success!');
        			//alertify.alert(textStatus);
        			window.location.reload();
        		},
        		complete : function() {
        			//console.log('complete!');
        			
        	    },
        		error: function(jqXHR, textStatus, errorThrown) {
        			//console.log('error!');
        			console.log(jqXHR);
        			console.log(textStatus);
        			console.log(errorThrown);
        			alertify.alert(textStatus);
        		}
        	});  
          });
        });
     	
     	$('.mn_btn_cancel').click(function(){
        	
        	var idx = $(this).attr('data-idx');
        	$('#aaaa'+idx).hide();
        	$('#bbbb'+idx).show();
        	$('#trg_input_title_'+idx).addClass('bg-white border-0 disabled').attr('disabled',true);
        	$('#trg_input_cont_'+idx).addClass('bg-white border-0 disabled').attr('disabled',true);	
        });
     	
        $(".mn_btn_del").click(function() {
  
        	var  bd_sn = $(this).attr('data-bdsn');
    		var alert_message = "삭제 후 복구되지 않습니다.<br>삭제 하시겠습니까?";;
        	
        	alertify.confirm(alert_message, function(){
    	    	$.ajax({
    	    		type:"POST",
    	    		url :"/cti/category/menual_delete_act.do",
    	    		data:{
    	    			BD_SN : bd_sn,
    	    		},
    	    		dataType: 'html',
    	    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    	    		async: true,
    	    		beforeSend : function(xhr, opts) {
    	    			
    	    		},
    	    		success: function(data, status, xhr) {
    	    			//console.log('success!');
    	    			//alertify.alert(data.msg);
    	    			window.location.reload();
    	    		},
    	    		complete : function() {
    	    			//console.log('complete!');
    	    			
    	    	    },
    	    		error: function(jqXHR, textStatus, errorThrown) {
    	    			//console.log('error!');
    	    			console.log(jqXHR);
    	    			console.log(textStatus);
    	    			console.log(errorThrown);
    	    			//alertify.alert(textStatus);
    	    		}
    	    	});  	
        	});   	
      });
    	

    </script>
        
<%@ include file="../tail.jsp" %>
