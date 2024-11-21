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

<form:form commandName="CodeSetVO" id="searchFormSido" name="searchFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<form:form commandName="smsSendVO" id="ajaxSendSmsForm" name="ajaxSendSmsForm" method="post">
<input type="hidden" name="chkedMbrIds" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="ajaxDataUnityForm" name="ajaxDataUnityForm" method="post">
<input type="hidden" name="MBR_IDS" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="ajaxMbrMemoForm" name="ajaxMbrMemoForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_DSCRP" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="addForm" name="addForm" method="post">
<input type="hidden" name="searchYear" value="${addWebLink}"/>
<input type="hidden" name="modal_title" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="updateForm" name="updateForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_EDU_RSPNBER_TYPE" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="ajaxForm" name="ajaxForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_NM" value=""/>
<input type="hidden" name="LOG_UPD_MSG" value=""/>
<input type="hidden" name="DEL_NOW" value=""/>
<input type="hidden" name="searchYear" value="${addWebLink}"/>
<input type="hidden" name="GNRL_MBER" value="Y"/>
<input type="hidden" name="MBR_SCRTY_KEY" value=""/>
</form:form>
   
   <script defer="defer">
   function req_list_mbr_edu_target(targId,mbrId) {
		
	  	var frm = document.getElementById('ajaxForm');
	  	frm.MBR_ID.value = mbrId;
	  	frm.DEL_NOW.value = '';
	
	  	$.ajax({
	  		type:"POST",
	  		url :"/eduadm/member/ajaxEduTarget.do",
	  		data:$('#ajaxForm').serialize(),
	  		dataType: "json",
	  		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
	  		async: false,
	  		success: function(data, status, xhr) {
	  			//console.log('success!');
	  			if(data.error == '1') {
	  				alertify.alert(data.msg);
	  			} else {
	  				var json = JSON.parse(data.rawdata);
	  				var htmlString = '';
	  				for (i=0; i<json.length; i++) {
	  					var item = json[i];
	  					
	  					if(htmlString!='') htmlString += '<br>';
	  					
	  					if(data.isShow == '0'){
	  						if(item.mbr_lrnng_st == '1' && item.mbr_lrnng_cmplt_st == '1'){
	  							var year = item.crs_str_dt.substr(0,4);
		  						var month = item.crs_str_dt.substr(5,2);
		  						var day = item.crs_str_dt.substr(8,2);
		  						var hour_start = item.crs_str_dt.substr(11,2);
		  						var min_start = item.crs_str_dt.substr(14,2);
		  						var hour_end = item.crs_end_dt.substr(11,2);
		  						var min_end = item.crs_end_dt.substr(14,2);
		  						
		  						var crs_dt_str = year+'.'+month+'.'+day;
		  						var crs_dt_time_str = hour_start+':'+min_start;
		  						var crs_dt_time_end = hour_end+':'+min_end;
		  						
		  						htmlString += '['+item.cat_ins_nm+']'+item.crs_grp_nm+' '+item.crs_nm+' (교육정보 '+crs_dt_str +' '+ crs_dt_time_str + '~' + crs_dt_time_end+' , '+item.crs_place+')';
	  							htmlString += '&nbsp;<span class="badge badge-outline badge-success">이수완료</span>';
	  						}else if(item.mbr_lrnng_st == '0' && item.mbr_lrnng_cmplt_st == '1'){
	  							var year = item.crs_str_dt.substr(0,4);
		  						var month = item.crs_str_dt.substr(5,2);
		  						var day = item.crs_str_dt.substr(8,2);
		  						var hour_start = item.crs_str_dt.substr(11,2);
		  						var min_start = item.crs_str_dt.substr(14,2);
		  						var hour_end = item.crs_end_dt.substr(11,2);
		  						var min_end = item.crs_end_dt.substr(14,2);
		  						
		  						var crs_dt_str = year+'.'+month+'.'+day;
		  						var crs_dt_time_str = hour_start+':'+min_start;
		  						var crs_dt_time_end = hour_end+':'+min_end;
		  						
		  						htmlString += '['+item.cat_ins_nm+']'+item.crs_grp_nm+' '+item.crs_nm+' (교육정보 '+crs_dt_str +' '+ crs_dt_time_str + '~' + crs_dt_time_end+' , '+item.crs_place+')';
	  							htmlString += '&nbsp;<span class="badge badge-outline badge-info">가이수</span>';
	  						}else {
	  							if(item.cat_ins_nm == null) {
		  							htmlString += '신청내역없음';	
		  						} else {
		  							var lrnng_prgrs = (item.lrnng_prgrs*100) + "";
		  							lrnng_prgrs = lrnng_prgrs.substr(0, 4) + "%";
		  							
		  							htmlString += '['+item.cat_ins_nm+']'+item.crs_grp_nm+' '+item.crs_nm;
	  								htmlString += '&nbsp;<span class="badge badge-outline badge-dark">미이수</span>';
		  						}
	  						}
	  						
	  					}else if(data.isShow == '1'){
	  						if(item.mbr_lrnng_st == '1' && item.mbr_lrnng_cmplt_st == '1'){
	  							var year = item.crs_str_dt.substr(0,4);
		  						var month = item.crs_str_dt.substr(5,2);
		  						var day = item.crs_str_dt.substr(8,2);
		  						var hour_start = item.crs_str_dt.substr(11,2);
		  						var min_start = item.crs_str_dt.substr(14,2);
		  						var hour_end = item.crs_end_dt.substr(11,2);
		  						var min_end = item.crs_end_dt.substr(14,2);
		  						
		  						var crs_dt_str = year+'.'+month+'.'+day;
		  						var crs_dt_time_str = hour_start+':'+min_start;
		  						var crs_dt_time_end = hour_end+':'+min_end;
		  						
		  						htmlString += '['+item.cat_ins_nm+']'+item.crs_grp_nm+' '+item.crs_nm+' (교육정보 '+crs_dt_str +' '+ crs_dt_time_str + '~' + crs_dt_time_end+' , '+item.crs_place+')';
	  							htmlString += '&nbsp;<span class="badge badge-outline badge-success">이수완료</span>';
	  						}else if(item.mbr_lrnng_st == '0' && item.mbr_lrnng_cmplt_st == '1'){
	  							var year = item.crs_str_dt.substr(0,4);
		  						var month = item.crs_str_dt.substr(5,2);
		  						var day = item.crs_str_dt.substr(8,2);
		  						var hour_start = item.crs_str_dt.substr(11,2);
		  						var min_start = item.crs_str_dt.substr(14,2);
		  						var hour_end = item.crs_end_dt.substr(11,2);
		  						var min_end = item.crs_end_dt.substr(14,2);
		  						
		  						var crs_dt_str = year+'.'+month+'.'+day;
		  						var crs_dt_time_str = hour_start+':'+min_start;
		  						var crs_dt_time_end = hour_end+':'+min_end;
		  						
		  						htmlString += '['+item.cat_ins_nm+']'+item.crs_grp_nm+' '+item.crs_nm+'(교육정보 '+crs_dt_str +' '+ crs_dt_time_str + '~' + crs_dt_time_end+' , '+item.crs_place+')';
	  							htmlString += '&nbsp;<span class="badge badge-outline badge-info">가이수</span>';
	  						}else {
	  							if(item.cat_ins_nm == null) {
		  							htmlString += '신청내역없음';	
		  						} else {
		  							var lrnng_prgrs = (item.lrnng_prgrs*100) + "";
		  							lrnng_prgrs = lrnng_prgrs.substr(0, 4) + "%";
		  							
		  							htmlString += '['+item.cat_ins_nm+']'+item.crs_grp_nm+' '+item.crs_nm;
	  								htmlString += '&nbsp;<span class="badge badge-outline badge-dark">미이수</span>';
		  						}
	  						}
	  					}
	  					
	  					/* if(item.cmplt_st == '1') {
	  						
	  						var year = item.crs_str_dt.substr(0,4);
	  						var month = item.crs_str_dt.substr(5,2);
	  						var day = item.crs_str_dt.substr(8,2);
	  						var hour_start = item.crs_str_dt.substr(11,2);
	  						var min_start = item.crs_str_dt.substr(14,2);
	  						var hour_end = item.crs_end_dt.substr(11,2);
	  						var min_end = item.crs_end_dt.substr(14,2);
	  						
	  						var crs_dt_str = year+'.'+month+'.'+day;
	  						var crs_dt_time_str = hour_start+':'+min_start;
	  						var crs_dt_time_end = hour_end+':'+min_end;
	  						
	  						htmlString += '['+item.cat_ins_nm+']'+item.crs_grp_nm+' '+item.crs_nm+' (교육정보 '+crs_dt_str +' '+ crs_dt_time_str + '~' + crs_dt_time_end+' , '+item.crs_place+')';
	  						htmlString += '&nbsp;<span class="badge badge-outline badge-success">이수완료</span>';
	  					} else {
	  						
	  						if(item.cat_ins_nm == null) {
	  							htmlString += '신청내역없음';	
	  						} else {
	  							var lrnng_prgrs = (item.lrnng_prgrs*100) + "";
	  							lrnng_prgrs = lrnng_prgrs.substr(0, 4) + "%";
	  							
	  							htmlString += '['+item.cat_ins_nm+']'+item.crs_grp_nm+' '+item.crs_nm;
	  							htmlString += '&nbsp;<span class="badge badge-outline badge-dark">미이수 ' + lrnng_prgrs + '</span>';
	  						}
	  					} */
	  					
	  					/*
	  					htmlString += '<span class="badge badge-outline badge-dark">';
	  					
	  					if(item.use_at == 'N' && item.del_at == 'Y') {//완전삭제 사용안함
	  						htmlString += '<span style="text-decoration:line-through;color:#bbb;">(삭제)&nbsp;';
	  					} else if(item.use_at == 'N' && item.del_at == 'N') {//사용중 대기상태
	  						htmlString += '<span><span style="text-decoration:line-through;">(교육중)&nbsp;</span>';
	  					} else {
	  						htmlString += '<span>';
	  					}	  					
	  					htmlString += item.trgt_year+' | ';
	  					if(item.cmplt_st == '1') {
	  						htmlString += '이수완료 ( '+item.cmplt_dt+','+item.cmplt_mbr_id+' )';
	  					} else {
	  						htmlString += '미이수';
	  					}
	  					htmlString += ' | '+item.crs_grp_nm+' - '+ item.crs_nm+'('+item.cat_ins_nm+') | ';
	  					//htmlString += ' <span class="font-size-10">( '+item.crs_sn+' | '+item.hmbr_sn+' )</span> ';
	  					if(item.reg_type_cd !=null && item.reg_type_cd.length != 0) {
	  						htmlString += '지자체 등록건';
	  					} else {
	  						htmlString += '공단 등록건';
	  					}	  					
	  					htmlString += ' | '+item.reg_dt+' 등록됨('+item.reg_mbr_id+') | ';
	  					htmlString += '</span>';
	  					
	  					htmlString += '</span>';
	  					*/
	  					
	  				}   				
	  				$('#'+targId).html(htmlString);
	  			}
	  		},
	  		beforeSend : function() {
	  			$('#'+targId).html('');
	  		},
	  		complete : function() {
	  			//console.log('complete!');
	  	    },
	  		error: function(jqXHR, textStatus, errorThrown) {
	  			//console.log('error!');
	  		}
	  	});
   }
   function pad(n, width) {
		n = n + '';
		return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
 	}
   </script>    



<form:form commandName="eduMemberVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="excel_msg" value="" />
	<input type="hidden" name="excel_type" value="" />
	<input type="hidden" name="excel_label" value="" />
	<input type="hidden" name="searchUseYn" value="Y" />
	<!-- 
	<input type="hidden" id="MBR_ID" name="MBR_ID" value=""/>
	<input type="hidden" id="MBR_NM" name="MBR_NM" value=""/>
	 -->
	<input type="hidden" name="searchYear" value="${addWebLink}"/>
	
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
				<c:if test="${empty pageName}">
					<script>
						location.href = '/incorrect_url.do';
					</script>
				</c:if>
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
					<a class="append-rows btn btn-info btn-outline" href="/eduadm/member/loc_gov_upload.do">
						<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
						<span class="hidden-sm-down">지자체명단 현행화 업로드하기</span>
					</a>
					<a class="append-rows btn btn-success btn-outline" href="/eduadm/member/loc_gov_adm_upload.do">
						<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
						<span class="hidden-sm-down">지자체명단 현행화 신청 리스트</span>
					</a>
					<a class="append-rows btn btn-warning btn-outline clk_addnew_data" href="javascript:void(0)"
					data-linkurl="/eduadm/member/write.do">
						<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
						<span class="hidden-sm-down">교육대상자 등록하기</span>
					</a>
					<c:if test="${not empty addWebLink}">
						<a class="append-rows btn btn-primary btn-outline clk_addedu_data" href="javascript:void(0)"
						data-title="기존회원 ${addWebLink}년도 교육대상자 추가하기" 
						data-linkurl="/eduadm/member/edu/add.do">
							<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
							<span class="hidden-sm-down">기존회원을 ${addWebLink}년도 교육대상자로 추가하기</span>
						</a>
					</c:if>
				</c:when>
				<%-- 해양경찰서 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
				
				</c:when>
				<%-- 지자체 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
					<a class="append-rows btn btn-success btn-outline" href="/eduadm/member/loc_gov_upload.do">
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">지자체명단 현행화 업로드하기</span>
		        	</a>
					<a class="append-rows btn btn-warning btn-outline clk_addnew_data" href="javascript:void(0)"
		        	data-linkurl="/eduadm/member/write.do">
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">교육대상자 등록하기</span>
		        	</a>
		        	<c:if test="${not empty addWebLink}">
			        	<a class="append-rows btn btn-primary btn-outline clk_addedu_data" href="javascript:void(0)" 
			        	data-title="기존회원 ${addWebLink}년도 교육대상자 추가하기" 
			        	data-linkurl="/eduadm/member/edu/add.do">
			        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
			        		<span class="hidden-sm-down">기존회원을 ${addWebLink}년도 교육대상자로 추가하기</span>
			        	</a>
		        	</c:if>
				</c:when>
				<%-- 교육기관 담당자 --%>
				<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
					<c:if test="${isKoreafcaMember eq true}">
						<a class="append-rows btn btn-warning btn-outline clk_addnew_data" href="javascript:void(0)"
			        	data-linkurl="/eduadm/member/write.do">
			        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
			        		<span class="hidden-sm-down">교육대상자 등록하기</span>
			        	</a>
			        	<c:if test="${not empty addWebLink}">
				        	<a class="append-rows btn btn-primary btn-outline clk_addedu_data" href="javascript:void(0)" 
				        	data-title="기존회원 ${addWebLink}년도 교육대상자 추가하기" 
				        	data-linkurl="/eduadm/member/edu/add.do">
				        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">기존회원을 ${addWebLink}년도 교육대상자로 추가하기</span>
				        	</a>
			        	</c:if>
		        	</c:if>
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
      
      		<div class="panel mb-20">
				<div class="panel-heading">
	    			<h3 class="panel-title">대상자검색&nbsp;&nbsp;
	      				&nbsp;<small>공통조건</small>
	    			</h3>
	  			</div>
	  			<div class="panel-body">
	  				<div class="input-group col-lg-12 p-0">
						<div class="btn-group col-lg-2 p-0">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_sido" name="MBR_SIDO_CD" <c:if test="${not empty LoginVO.MBR_SIDO_CD}">disabled</c:if> >
								<option value="" >시도 전체</option>
								<c:forEach var="item" items="${list_address_cd}">
									<option value="${item.CD_ID}" <c:if test="${item.CD_ID eq MBR_SIDO_CD }">selected</c:if> >${item.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="search_sel_area_signgu" name="MBR_SIGNGU_CD" <c:if test="${not empty LoginVO.MBR_SIGNGU_CD}">disabled</c:if> >
								<option value="" >시군구 전체</option>
								<c:forEach var="item" items="${list_address_signgu_cd}">
									<option value="${item.CD_ID}" <c:if test="${item.CD_ID eq MBR_SIGNGU_CD }">selected</c:if> >${item.CD_NM}</option>
								</c:forEach>	        			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="YMD_NM" placeholder="읍면동" autocomplete="off" value="${YMD_NM}" data-toggle="tooltip" data-original-title="읍면동을 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual " data-style="btn-outline btn-default" name="MBR_JOIN">
	    						<option value="" <c:if test="${empty MBR_JOIN}">selected</c:if>>등록방식</option>
	    						<option value="auto" <c:if test="${MBR_JOIN eq 'auto'}">selected</c:if>>직접등록</option>
	    						<option value="default" <c:if test="${MBR_JOIN eq 'default'}">selected</c:if>>관리자등록</option>
	    					</select>
						</div>	
					</div>
					<div class="input-group col-lg-12 p-0 mt-10">
						<div class="btn-group col-lg-2 p-0">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" id="MBR_TRGT_CD" name="MBR_TRGT_CD" <c:if test="${not empty LoginVO.MBR_TRGT_CD}">disabled</c:if> >
								<option value="" >교육대상(업종)전체</option>
								<c:forEach var="item_category" items="${list_mbr_trgt_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq MBR_TRGT_CD }">selected</c:if> >${item_category.CD_NM}만</option>
								</c:forEach>
						  	</select>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual " data-style="btn-outline btn-default" id="MBR_FSHRBT_TYPE" name="MBR_FSHRBT_TYPE">
	    						<option value="" <c:if test="${empty MBR_FSHRBT_TYPE}">selected</c:if> >전체</option>
	    						<option value="legacy" <c:if test="${MBR_FSHRBT_TYPE eq 'legacy'}">selected</c:if>>기존</option>
	    						<option value="new" <c:if test="${MBR_FSHRBT_TYPE eq 'new'}">selected</c:if>>신규</option>
	    						<option value="resmpt" <c:if test="${MBR_FSHRBT_TYPE eq 'resmpt'}">selected</c:if>>재개자</option>
	    					</select>
		      			</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_LRNNG_CMPLT_ST" >
								<option value="" <c:if test="${empty MBR_LRNNG_CMPLT_ST}">selected</c:if> >교육이수여부 전체</option>     
								<option value="1" <c:if test="${MBR_LRNNG_CMPLT_ST eq '1'}">selected</c:if> >이수</option>    
								<option value="0" <c:if test="${MBR_LRNNG_CMPLT_ST eq '0'}">selected</c:if> >미이수</option>       			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="TYPE_GB" >
								<option value="" <c:if test="${empty TYPE_GB}">selected</c:if> >교육타입</option>     
								<option value="online" <c:if test="${TYPE_GB eq 'online'}">selected</c:if> >온라인 교육</option>    
								<option value="offline" <c:if test="${TYPE_GB eq 'offline'}">selected</c:if> >현장 교육</option>       			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_LRNNG_ST" >
								<option value="" <c:if test="${empty MBR_LRNNG_ST}">selected</c:if> >교육신청여부</option>    
								<option value="0" <c:if test="${MBR_LRNNG_ST eq '0'}">selected</c:if> >신청(대기)</option>
								<option value="1" <c:if test="${MBR_LRNNG_ST eq '1'}">selected</c:if> >참석(승인)</option>    
								<option value="4" <c:if test="${MBR_LRNNG_ST eq '2' or MBR_LRNNG_ST eq '4'}">selected</c:if> >불참(보류)</option>      			
		      				</select>
						</div>
						<div class="btn-group col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_EDU_RSPNBER_TYPE" >
								<option value="" <c:if test="${empty MBR_EDU_RSPNBER_TYPE}">selected</c:if> >교육책임자선택</option>    
								<option value="CEO" <c:if test="${MBR_EDU_RSPNBER_TYPE eq 'CEO'}">selected</c:if> >대표자</option>
								<option value="EDU_RSPNBER" <c:if test="${MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">selected</c:if> >교육책임자</option>    
		      				</select>
						</div>
					</div>
					<div class="input-group col-lg-12 p-0 mt-10">
						<div class="col-lg-2 p-0">
							<input type="text" class="form-control input-auto-enter-key" name="MBR_NM" placeholder="이름,닉네임" autocomplete="off" value="${MBR_NM}" data-toggle="tooltip" data-original-title="이름 또는 닉네임을 입력하세요.">
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
							<input type="text" class="form-control input-auto-enter-key" name="DTL_NM" placeholder="낚시터/어선명" autocomplete="off" value="${DTL_NM}" data-toggle="tooltip" data-original-title="낚시터 또는 어선명을 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key reg-num-cd-input-pattern-frm" data-pattern-cnt="0" name="REG_NUM_CD" placeholder="허가(등록)번호/신고번호" autocomplete="off" value="${REG_NUM_CD}" data-toggle="tooltip" data-original-title="낚시터는 허가(등록)번호,낚시어선은 신고번호를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key business-num-input-pattern-frm" data-pattern-cnt="0" name="BUSINESS_NUM" placeholder="사업자번호" autocomplete="off" value="${BUSINESS_NUM}" data-toggle="tooltip" data-original-title="사업자번호를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<div class="input-group">
								<input type="text" class="form-control datepickerModalStrSearch" id="SHIP_LICENSE_STR_DT" name="SHIP_LICENSE_STR_DT" placeholder="유효시작일자" autocomplete="off" value="${SHIP_LICENSE_STR_DT}" data-toggle="tooltip" data-original-title="시작일자를 선택하세요.">
								<div class="input-group-append">
								    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="SHIP_LICENSE_STR_DT" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<div class="input-group">
								<input type="text" class="form-control datepickerModalEndSearch" id="SHIP_LICENSE_END_DT" name="SHIP_LICENSE_END_DT" placeholder="유효만료일자" autocomplete="off" value="${SHIP_LICENSE_END_DT}" data-toggle="tooltip" data-original-title="만료일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="SHIP_LICENSE_END_DT" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="DTL_LICENSE_CD" >
								<option value="" >대상구분 전체보기</option>
								<c:forEach var="item_category" items="${list_license_se_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq DTL_LICENSE_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach>	
		      				</select>	
						</div>
					</div>
					<%-- <div class="input-group col-lg-12 p-0 mt-10">
						<div class="col-lg-2 p-0">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="TYPE_GB" >
								<option value="" <c:if test="${empty TYPE_GB}">selected</c:if> >교육구분</option>    
								<option value="online" <c:if test="${TYPE_GB eq 'online'}">selected</c:if> >온라인교육</option>
								<option value="offline" <c:if test="${TYPE_GB eq 'offline'}">selected</c:if> >집합교육</option>    
		      				</select>
						</div>
					</div> --%>
					<hr><h6>낚시터 추가 조건</h6>
					<div class="input-group col-lg-12 p-0 mt-10 /*hidden*/ trg_search_store_detail_layer">
						<div class="col-lg-2 p-0">
		      				<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="FSHLC_WORK_CD" >
								<option value="" >업구분</option>   
								<c:forEach var="item_category" items="${list_fshlc_work_cd}">
									<option value="${item_category.CD_ID}" <c:if test="${item_category.CD_ID eq FSHLC_WORK_CD }">selected</c:if> >${item_category.CD_NM}</option>
								</c:forEach> 			
		      				</select>	
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="DTL_ADDR" placeholder="소재지(주소)" autocomplete="off" value="${DTL_ADDR}" data-toggle="tooltip" data-original-title="소재지(구:낚시위치)를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="FSHLC_APPLC" placeholder="적용수면" autocomplete="off" value="${FSHLC_APPLC}" data-toggle="tooltip" data-original-title="적용수면을 입력하세요.">
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
							<input type="text" class="form-control input-auto-enter-key ship-cd-input-pattern-frm" data-pattern-cnt="0" name="SHIP_CD" placeholder="어선번호" autocomplete="off" value="${SHIP_CD}" data-toggle="tooltip" data-original-title="어선번호를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="SHIP_GRTG" placeholder="총톤수" autocomplete="off" value="${SHIP_GRTG}" data-toggle="tooltip" data-original-title="어선의 총톤수를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="SHIP_PRLOAD" placeholder="선적항" autocomplete="off" value="${SHIP_PRLOAD}" data-toggle="tooltip" data-original-title="어선의 선적항을 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="SHIP_MAX_PSNGER" placeholder="최대승객" autocomplete="off" value="${SHIP_MAX_PSNGER}" data-toggle="tooltip" data-original-title="어선의 최대승객수를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<input type="text" class="form-control input-auto-enter-key" name="SHIP_MAX_CREW" placeholder="최대선원" autocomplete="off" value="${SHIP_MAX_CREW}" data-toggle="tooltip" data-original-title="어선의 최대선원수를 입력하세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="SHIP_LICENSE">
								<option value="" >해기사면허 유무</option>    
								<option value="유" <c:if test="${SHIP_LICENSE eq '유'}">selected</c:if> >유</option>
								<option value="무" <c:if test="${SHIP_LICENSE eq '무'}">selected</c:if> >무</option>      			
		      				</select>
						</div>
					</div>
					<hr>
					<div class="input-group col-lg-12 p-0 mt-30">
						<div class="col-lg-4 p-0">
							<!-- 
							<button type="button" class="btn btn-outline btn-warning clk_search_store_detail mr-5"><i class="icon wb-plus" aria-hidden="true"></i>낚시터 추가조건</button>
							<button type="button" class="btn btn-outline btn-info clk_search_ship_detail"><i class="icon wb-plus" aria-hidden="true"></i>낚시어선 추가조건</button>
							 -->
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<select class="form-control selectpicker_manual" data-style="btn-outline btn-default" name="MBR_ST">
								<option value="ALL" <c:if test="${empty MBR_ST}">selected</c:if> >회원상태 전체</option>    
								<option value="Y" <c:if test="${MBR_ST eq 'Y'}">selected</c:if> >활성화(사용함)</option>
								<option value="N" <c:if test="${MBR_ST eq 'N'}">selected</c:if> >비활성화(사용안함)</option>      			
		      				</select>
		      			</div>
		      			<div class="col-lg-2 p-0 pl-10">
							<div class="btn-group">
								<div class="input-group-prepend">
									<span class="input-group-text">출력수</span>
								</div>
								<input type="text" class="form-control input-auto-enter-key" name="pageUnit" placeholder="" autocomplete="off" value="${paginationInfo.recordCountPerPage}" data-toggle="tooltip" data-original-title="페이지당 화면에 출력할 게시물 수량을 입력하세요.">
							</div>
						</div>
						<div class="col-lg-2 p-0 pl-10 ">
							<input type="text" class="form-control input-auto-enter-key" name="searchKeyword" placeholder="검색어 입력" autocomplete="off" value="${searchKeyword}" data-toggle="tooltip" data-original-title="회원아이디 등을 입력해주세요.">
						</div>
						<div class="col-lg-2 p-0 pl-10 text-right">
							<button type="button" class="form-control btn btn-primary clk_search_btn"><i class="icon wb-search" aria-hidden="true"></i></button>
						</div>
					</div>						
	  			</div>
			</div> 
          
            <!-- Panel Editing Rows -->
            <div class="panel">
              <div class="panel-body">
              		
              		<div class="row">
	              		<div class="col-lg-6 text-left">
	              			<c:choose><%-- 권한분류 --%>
								<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
									<a class="btn btn-danger btn-act-send-sms" href="javascript:void(0);">
						        		<i class="site-menu-icon wb-envelope" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">문자보내기</span>
						        	</a> 
									<a class="btn btn-danger btn-act-data-unity" href="javascript:void(0);">
						        		<i class="site-menu-icon fa-user-secret" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">데이터 통합하기</span>
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
								
								</c:when>
								<%-- 기타 거부 --%>
								<c:otherwise>
								
								</c:otherwise>
							</c:choose><%-- End 권한분류 --%>
	              		</div>	
	              		<div class="col-lg-6 text-right">
	              			<c:choose><%-- 권한분류 --%>
								<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
									<%-- 
		              				<a class="btn btn-outline btn-info excel-down btn-act-excel-down" href="javascript:void(0);">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">엑셀다운로드</span>
						        	</a> 
						        	--%>
						        	<a class="btn btn-outline btn-success excel-down btn-act-external-excel-down" href="javascript:void(0);" 
						        		data-excel-type="/eduadm/member/eduListLocgov.do"
						        		data-excel-label="${fn:trim(pageName)}_{{LABEL}}지자체_명단_현행화_엑셀다운로드">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">${pageName} 지자체명단 현행화 (총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드 요청하기</span>
						        	</a>
						        	<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down" href="javascript:void(0);" 
						        		data-excel-type="/eduadm/member/eduListNew.do"
						        		data-excel-label="${fn:trim(pageName)}_{{LABEL}}전체_엑셀다운로드">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">${pageName} (총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드 요청하기</span>
						        	</a>
						        	<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down" href="javascript:void(0);" 
						        		data-excel-type="/eduadm/member/eduList.do"
						        		data-excel-label="${fn:trim(pageName)}_{{LABEL}}전체(구버전)_엑셀다운로드">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">${pageName} (총 ${paginationInfo.totalRecordCount}건 모두) 구버전 엑셀다운로드 요청하기</span>
						        	</a>  
								</c:when>
								<%-- 해양경찰서 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
									
								</c:when>
								<%-- 지자체 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
									<a class="btn btn-outline btn-success excel-down btn-act-external-excel-down" href="javascript:void(0);" 
						        		data-excel-type="/eduadm/member/eduListLocgov.do"
						        		data-excel-label="${fn:trim(pageName)}_{{LABEL}}지자체_명단_현행화_엑셀다운로드">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">${pageName} 지자체명단 현행화 (총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드 요청하기</span>
						        	</a>
						        	<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down mt-5" href="javascript:void(0);" 
						        		data-excel-type="/eduadm/member/eduListNew.do"
						        		data-excel-label="${fn:trim(pageName)}_{{LABEL}}전체_엑셀다운로드">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">${pageName} (총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드 요청하기</span>
						        	</a>
						        	<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down mt-5" href="javascript:void(0);" 
						        		data-excel-type="/eduadm/member/eduList.do"
						        		data-excel-label="${fn:trim(pageName)}_{{LABEL}}전체(구버전)_엑셀다운로드">
						        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
						        		<span class="hidden-sm-down">${pageName} (총 ${paginationInfo.totalRecordCount}건 모두) 구버전 엑셀다운로드 요청하기</span>
						        	</a>  
								</c:when>
								<%-- 교육기관 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
									<c:if test="${isKoreafcaMember eq true}">
										<a class="btn btn-outline btn-success excel-down btn-act-external-excel-down" href="javascript:void(0);" 
							        		data-excel-type="/eduadm/member/eduListLocgov.do"
							        		data-excel-label="${fn:trim(pageName)}_{{LABEL}}지자체_명단_현행화_엑셀다운로드">
							        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
							        		<span class="hidden-sm-down">${pageName} 지자체명단 현행화 (총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드 요청하기</span>
							        	</a>
						        	</c:if>
								</c:when>
								<%-- 기타 거부 --%>
								<c:otherwise>
								
								</c:otherwise>
							</c:choose><%-- End 권한분류 --%>	              			
	              		</div>
	              	</div>              
              
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
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			<col />
	          			
	          			<c:choose><%-- 권한분류 --%>
							<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
								<col width="130px"/>		
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
							<%-- 기타 --%>
							<c:otherwise>
							
							</c:otherwise>
						</c:choose><%-- End 권한분류 --%>	 
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center hide-cell-exceldown">
								<span class="checkbox-custom checkbox-primary">
                              		<input class="selectable-item btn-check-item-all" type="checkbox" data-status=""><label></label>
                            	</span>
							</th>
	          				<th class="text-middle text-center">No</th>
	          				<th class="text-middle text-center">시도</th>
	          				<th class="text-middle text-center">시군구</th>
	          				<th class="text-middle text-center">낚시터명/어선명</th>
	          				<th class="text-middle text-center">낚시터/어선 구분</th>
	          				<th class="text-middle text-center">대상 구분</th>
		          			<th class="text-middle text-center">이름<br>호칭</th>
							<th class="text-middle text-center">생년월일</th>
	           				<th class="text-middle text-center">연락처</th>
	           				<th class="text-middle text-center">주소</th>
	           				<th class="text-middle text-center">교육이수여부</th>
	            			<th class="text-middle text-center">비고</th>
	            			
	            			
	            			<c:choose><%-- 권한분류 --%>
								<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
									<th class="text-middle text-center">법인사업장<br>교육책임자</th>
									<th class="text-middle text-center">메모<br><span class="font-size-11 blue-grey-400">(입력 후 3초뒤 자동저장)</span></th>
								</c:when>
								<%-- 해양경찰서 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
									
								</c:when>
								<%-- 지자체 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
									<th class="text-middle text-center">법인사업장<br>교육책임자</th>
								</c:when>
								<%-- 교육기관 담당자 --%>
								<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
								
								</c:when>
								<%-- 기타 --%>
								<c:otherwise>
								
								</c:otherwise>
							</c:choose><%-- End 권한분류 --%>	            			
	            				            			
            			</tr>        				
	             	</thead>
					<tbody>
						<c:choose><%-- 권한분류 --%>
							<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
								<c:set var="table_cell_cnt" value="15"/>
							</c:when>
							<%-- 해양경찰서 담당자 --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
								<c:set var="table_cell_cnt" value="13"/>
							</c:when>
							<%-- 지자체 담당자 --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
								<c:set var="table_cell_cnt" value="14"/>
							</c:when>
							<%-- 교육기관 담당자 --%>
							<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
								<c:set var="table_cell_cnt" value="13"/>
							</c:when>
							<%-- 기타  --%>
							<c:otherwise>
								<c:set var="table_cell_cnt" value="14"/>
							</c:otherwise>
						</c:choose><%-- End 권한분류 --%>					
						
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">조회 가능한 회원이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle hide-cell-exceldown">
			            			<span class="checkbox-custom checkbox-primary">
	                              		<input class="selectable-item btn-check-item" type="checkbox" name="checkbox_item" value="${item.MBR_ID}"><label></label>
	                            	</span>
                            	</td>
		            			<td class="text-middle text-center">${(paginationInfo.totalRecordCount-((paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)) - status.index}</td>
				               	<c:choose>
				               		<c:when test="${not empty item.DTL_SN}">
					               		<td class="text-middle text-center">${item.SIDO_CD_NM}</td>
						                <td class="text-middle text-center">${item.SIGNGU_CD_NM}</td>
						                <td class="text-middle text-center">${item.DTL_NM}</td>
						                <td class="text-middle text-center">${item.DTL_CD}</td>
						                <td class="text-middle text-center">${item.DTL_LICENSE_CD_NM}</td>
				               		</c:when>
				               		<c:otherwise>
				               			<td class="text-middle text-center font-size-12 blue-grey-400" colspan="5">상세정보없음</td>	
				               		</c:otherwise>
				               	</c:choose>
				                <td class="text-middle">
				                	<c:choose>
										<c:when test="${empty item.MBR_NM}">
											<i class="site-menu-icon wb-trash" aria-hidden="true" data-content="현재 삭제 된 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
										</c:when>
										<c:otherwise>
											<c:if test="${item.MBR_ST ne 'Y'}">
												<i class="site-menu-icon wb-eye-close" aria-hidden="true" data-content="현재 사용하지 않는 회원입니다."  data-original-title="알림" data-trigger="hover" data-toggle="popover" tabindex="0"></i>
											</c:if>
										</c:otherwise>											
									</c:choose>
					                ${item.MBR_NM}
					                <br><span class="grey-400">${item.MBR_NCNM}</span>
				                </td>
				                <td class="text-middle text-center">${pfpu:birthHypen(item.MBR_BIRTH)}</td>
				                <td class="text-middle text-center">${pfpu:phoneHypen(item.MBR_HP)}</td>
				                <td class="text-middle text-left">${item.MBR_ADDR1} ${item.MBR_ADDR2}</td>
				                <td class="text-middle text-center" id="id_list_mbr_edu_target_${item.MBR_ID}">
				                	<a href="#;" class="btn btn-round btn-outline btn-sm btn-default clk_req_edu_data"  
				                	onclick="req_list_mbr_edu_target('id_list_mbr_edu_target_${item.MBR_ID}','${item.MBR_ID}');">조회</a>
				                	<!-- <script>req_list_mbr_edu_target('id_list_mbr_edu_target_${item.MBR_ID}','${item.MBR_ID}');</script> -->
				                </td>
				                <td class="text-middle text-center">
				                <c:choose><%-- 권한분류 --%>
			              			<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
			              			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
					              		<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-mbr-id="${item.MBR_ID}" 
					                	data-mbr-scrty-key="${item.MBR_SCRTY_KEY}"
					                	data-linkurl="/eduadm/member/modify.do">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
					                	<c:if test="${not empty addWebLink}">
						                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_deledu_data" data-toggle="tooltip" data-original-title="교육대상삭제" 
						                	data-mbr-id="${item.MBR_ID}"
						                	data-mbr-scrty-key="${item.MBR_SCRTY_KEY}"
						                	data-linkurl="/eduadm/member/edu/delete_act.do">
						                		<i class="icon wb-trash" aria-hidden="true"></i>
						                	</a>
					                	</c:if>
					                	<c:if test="${empty addWebLink}">
						                	<button type="button" class="btn btn-outline btn-danger btn-xs clk_del_data" data-toggle="tooltip" data-original-title="회원삭제" 
						                		data-mbr-id="${item.MBR_ID}">회원삭제</button>
					                	</c:if>
					                	<!-- 
				                		<br>
				                		<button type="button" class="btn btn-outline btn-success btn-xs" onclick="pageMove(this)" 
				                		data-mbr-id="${item.MBR_ID}"
				                		data-mbr-nm="${item.MBR_NM}"
				                		>수강내역 관리 이동</button>
				                		<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
				                		 -->
				                		<br>
					              		<c:choose>
											<c:when test="${not empty item.MBR_REG_TYPE_CD}">
												<c:forEach var="item_category" items="${list_position_cd}">
													<c:if test="${item.MBR_REG_TYPE_CD eq item_category.CD_ID}">
														<span class="badge badge-outline badge-default">${item_category.CD_NM} 등록건</span>
													</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise><span class="badge badge-outline badge-info">공단 등록건</span></c:otherwise>
										</c:choose>	
			              			</c:when>
			              			<%-- 해양경찰서 담당자 --%>
			              			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
			              				<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
							       	</c:when>
			              			<%-- 지자체 담당자 --%>
			              			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
			              				<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
					                	data-mbr-id="${item.MBR_ID}" 
					                	data-mbr-scrty-key="${item.MBR_SCRTY_KEY}"
					                	data-linkurl="/eduadm/member/modify.do">
					                		<i class="icon wb-wrench" aria-hidden="true"></i>
					                	</a>
					                	<c:if test="${not empty addWebLink}">
						                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_deledu_data" data-toggle="tooltip" data-original-title="교육대상삭제" 
						                	data-mbr-id="${item.MBR_ID}"
						                	data-mbr-scrty-key="${item.MBR_SCRTY_KEY}"
						                	data-linkurl="/eduadm/member/edu/delete_act.do">
						                		<i class="icon wb-trash" aria-hidden="true"></i>
						                	</a>
					                	</c:if>
					                	<c:choose>
											<c:when test="${not empty item.MBR_REG_TYPE_CD}">
												<c:forEach var="item_category" items="${list_position_cd}">
													<c:if test="${item.MBR_REG_TYPE_CD eq item_category.CD_ID}">
														<span class="badge badge-outline badge-default">${item_category.CD_NM} 등록건</span>
													</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise><span class="badge badge-outline badge-info">공단 등록건</span></c:otherwise>
										</c:choose><!-- <span class="grey-400 font-size-10">(추후 지자체건만 노출하며 표식없앨예정)</span> -->
			              			</c:when>
			              			<%-- 교육기관 담당자 --%>
			              			<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
			              				<c:if test="${isKoreafcaMember eq true}">
			              					<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default edit-row clk_mod_data" data-toggle="tooltip" data-original-title="수정" 
						                	data-mbr-id="${item.MBR_ID}" 
						                	data-mbr-scrty-key="${item.MBR_SCRTY_KEY}"
						                	data-linkurl="/eduadm/member/modify.do">
						                		<i class="icon wb-wrench" aria-hidden="true"></i>
						                	</a>
						                	<c:if test="${not empty addWebLink}">
							                	<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_deledu_data" data-toggle="tooltip" data-original-title="교육대상삭제" 
							                	data-mbr-id="${item.MBR_ID}"
							                	data-mbr-scrty-key="${item.MBR_SCRTY_KEY}"
							                	data-linkurl="/eduadm/member/edu/delete_act.do">
							                		<i class="icon wb-trash" aria-hidden="true"></i>
							                	</a>
						                	</c:if>
						                	<c:if test="${empty addWebLink}">
							                	<button type="button" class="btn btn-outline btn-danger btn-xs clk_del_data" data-toggle="tooltip" data-original-title="회원삭제" 
							                		data-mbr-id="${item.MBR_ID}">회원삭제</button>
						                	</c:if>
			              				</c:if>
			              			</c:when>
			              			<%-- 기타 거부 --%>
			              			<c:otherwise>
			              				
			              			</c:otherwise>
			              		</c:choose><%-- End 권한분류 --%>
			                	</td>
			                	
								
								<c:choose><%-- 권한분류 --%>
									<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
										<td class="text-middle text-center">
					                		<c:if test="${item.DTL_LICENSE_CD eq 'CIDL00002'}">
												<select class="form-control selectpicker_manual" id="edu_rspnber_${item.MBR_ID }" 
												data-mbr-id="${item.MBR_ID}" data-style="btn-outline btn-default">
													<option value="" <c:if test="${empty item.MBR_EDU_RSPNBER_TYPE}">selected</c:if>>선택</option>
													<option value="CEO" <c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">selected</c:if>>대표자</option>
													<option value="EDU_RSPNBER" <c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">selected</c:if>>교육책임자</option>
												</select>
												<button type="button" class="btn btn-xs btn-primary btn-outline mt-5 btn_change">변경하기</button>
											</c:if>
										</td>
									</c:when>
									<%-- 해양경찰서 담당자 --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0002'}">
										
									</c:when>
									<%-- 지자체 담당자 --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0003'}">
										<td class="text-middle text-center">
					                		<c:if test="${item.DTL_LICENSE_CD eq 'CIDL00002'}">
												<select class="form-control selectpicker_manual" id="edu_rspnber_${item.MBR_ID }" 
												data-mbr-id="${item.MBR_ID}" data-style="btn-outline btn-default">
													<option value="" <c:if test="${empty item.MBR_EDU_RSPNBER_TYPE}">selected</c:if>>선택</option>
													<option value="CEO" <c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'CEO'}">selected</c:if>>대표자</option>
													<option value="EDU_RSPNBER" <c:if test="${item.MBR_EDU_RSPNBER_TYPE eq 'EDU_RSPNBER'}">selected</c:if>>교육책임자</option>
												</select>
												<button type="button" class="btn btn-xs btn-primary btn-outline mt-5 btn_change">변경하기</button>
											</c:if>
										</td>
									</c:when>
									<%-- 교육기관 담당자 --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0004'}">
										
									</c:when>
									<%-- 기타  --%>
									<c:otherwise>
										
									</c:otherwise>
								</c:choose><%-- End 권한분류 --%>
								
								<c:choose><%-- 권한분류 --%>
									<%-- 공단 관리자 (PCD0005:낚시누리 운영자,PCD0006:낚시전문교육 운영자,PCD0007:통합관리자) --%>
									<c:when test="${LoginVO.MBR_POSITION_CD eq 'PCD0005' or LoginVO.MBR_POSITION_CD eq 'PCD0006' or LoginVO.MBR_POSITION_CD eq 'PCD0007'}">
										<td class="text-middle text-center">
					                		<textarea class="form-control typing-text-memo" placeholder="" row="5" data-mbr-id="${item.MBR_ID}">${item.MBR_DSCRP}</textarea>
					                	</td>
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
									<%-- 기타  --%>
									<c:otherwise>
										
									</c:otherwise>
								</c:choose><%-- End 권한분류 --%>
								
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
.alertify .alert .msg, .alertify .dialog .msg {padding:0px!important;padding-left:2px!important;}
</style>

<script>
	$('.input-auto-enter-key').keypress(function(key) {
    	if(key.keyCode == 13){
    		$('.clk_search_btn').click();
        }
    });
    $(".clk-clear-input").click(function() {
    	var target = $(this).attr('for');
    	$('#'+target).val("");
    });
    $("input:text[numberOnly]").on("keyup", function() {
        $(this).val($(this).val().replace(/[^0-9]/g,""));
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
    /*
    function pageMove(obj) {
    	var form = document.getElementById('listForm');
    	form.MBR_ID.value = $(obj).attr('data-mbr-id');
    	form.MBR_NM.value = $(obj).attr('data-mbr-nm');
    	form.pageIndex.value = '1';
    	form.action = "/eduadm/member/listDtl.do";
    	form.submit();
    }
    */
    function fn_egov_link_page(pageNo){
    	document.listForm.pageIndex.value = pageNo;
    	document.listForm.action = "";
       	document.listForm.submit();
    }
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
    $(".btn-act-data-unity").click(function() {
    	var chkedMbrIds = "";
    	var chkedMbrIdCnt = 1;
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedMbrIds.length!=0) {
				chkedMbrIdCnt++;
				chkedMbrIds+=",";
			}
			chkedMbrIds+=$(this).val();
		});
		if(chkedMbrIds.length==0) {
			alertify.alert("통합 할 대상자를 선택해주세요.");
			return;
		}
		if(chkedMbrIdCnt !== 2){
			alertify.alert("통합 할 대상자(2개)를 선택해주세요.");
			return;
		}
		var form = document.getElementById('ajaxDataUnityForm');
		form.MBR_IDS.value = chkedMbrIds;
		$.ajax({
			type:"POST",
			url :"/eduadm/dataUnity/list.do",
			data:$('#ajaxDataUnityForm').serialize(),
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
    $(".btn-act-send-sms").click(function() {
    	var chkedMbrIds = "";
		$("input[name=checkbox_item]:checked").each(function() {
			if(chkedMbrIds.length!=0) chkedMbrIds+=",";
			chkedMbrIds+=$(this).val();
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
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
   	}); 
    $(".clk_search_btn").click(function() {
    	var form = document.getElementById('listForm');
    	form.pageIndex.value = '1';
    	form.action = '';
    	
    	form.MBR_BIRTH.value = form.MBR_BIRTH.value.replace(/\-/g,'').trim();
    	form.MBR_HP.value = form.MBR_HP.value.replace(/\-/g,'').trim();
    	form.MBR_TEL.value = form.MBR_TEL.value.replace(/\-/g,'').trim();
    	form.SHIP_CD.value = form.SHIP_CD.value.replace(/\-/g,'').trim();
    	form.REG_NUM_CD.value = form.REG_NUM_CD.value.replace(/\-/g,'').trim();
    	form.BUSINESS_NUM.value = form.BUSINESS_NUM.value.replace(/\-/g,'').trim();
    	
    	form.submit();
    });
    $(".clk_addedu_data").click(function() {
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#addForm').serialize(),
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
    $(".clk_addnew_data").click(function() {
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#addForm').serialize(),
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
    $(".clk_mod_data").click(function() {
    	var form = document.getElementById('ajaxForm');
    	form.MBR_ID.value = $(this).attr('data-mbr-id');
    	form.MBR_SCRTY_KEY.value = $(this).attr('data-mbr-scrty-key');
    	form.DEL_NOW.value = '';
    	form.action = '';
    	var data_linkurl = $(this).attr('data-linkurl');
    	$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#ajaxForm').serialize(),
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
    $(".clk_deledu_data").click(function() {
    	var data_mbr_id = $(this).attr('data-mbr-id');
    	var data_linkurl = $(this).attr('data-linkurl');
    	var data_mbr_scrty_key = $(this).attr('data-mbr-scrty-key');
    	alertify.confirm("교육대상자에서 제거 하시겠습니까?", function(){ 
    		//ok
    		var form = document.getElementById('ajaxForm');
    		form.MBR_ID.value = data_mbr_id;
    		form.MBR_SCRTY_KEY.value = data_mbr_scrty_key;
    		form.DEL_NOW.value = '';
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :data_linkurl,
    			data:$('#ajaxForm').serialize(),
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
    						//document.listForm.submit();
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
    $(".clk_del_data").click(function() {
    	var data_mbr_id = $(this).attr('data-mbr-id');
    	alertify.prompt('삭제 후 복구가 불가능합니다.<br>삭제하려는 사유를 입력해주세요.',function(val, e) {
    		//ok
   			if(val.trim()=='') {
   				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
   				return;
   			} 
   			//ok
    		var form = document.getElementById('ajaxForm');
    		form.MBR_ID.value = data_mbr_id;
        	form.LOG_UPD_MSG.value = val;
        	form.DEL_NOW.value = 'Y';
	    	form.action = '';
        	$.ajax({
    			type:"POST",
    			url :"/eduadm/member/delete_act.do",
    			data:$('#ajaxForm').serialize(),
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
    		} ,function() { 
    		//cancel    			
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
  	//엑셀다운로드 
    $(".btn-act-external-excel-down").click( function() {
    	var excel_type = $(this).attr('data-excel-type');
    	var excel_label = $(this).attr('data-excel-label');
    	//엑셀 파일명 조합
    	var addLabel = '';
    	{
    		var form = document.getElementById('listForm');
    		if(form.MBR_SIDO_CD.value!='') {
    			addLabel += form.MBR_SIDO_CD.options[form.MBR_SIDO_CD.selectedIndex].text+'_';    			
    		}
    		if(form.MBR_SIGNGU_CD.value!='') {
    			addLabel += form.MBR_SIGNGU_CD.options[form.MBR_SIGNGU_CD.selectedIndex].text+'_';
    		}
    		if(form.MBR_TRGT_CD.value!='') {
    			addLabel += form.MBR_TRGT_CD.options[form.MBR_TRGT_CD.selectedIndex].text+'_';
    		}
    		if(form.MBR_LRNNG_CMPLT_ST.value!='') {
    			addLabel += form.MBR_LRNNG_CMPLT_ST.options[form.MBR_LRNNG_CMPLT_ST.selectedIndex].text+'_';
    		}
    	}
    	excel_label = excel_label.replace('{{LABEL}}',addLabel);
    	//End of 엑셀 파일명 조합
    	alertify.prompt('엑셀 다운로드 사유를 입력해주세요.',function(val, e) {
    			//ok
    			if(val.trim()=='') {
    				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    				return;
    			} 
    			/*
    			alertify.alert('엑셀 다운로드가 완료 될 때까지 잠시 기다려 주세요.<br>데이터처리량에 따라 수분이 소요 될 수도 있습니다.<br><br><span class="red-600">회원상세정보(부가정보)를 포함하기 때문에 데이터는 총 조회 ${paginationInfo.totalRecordCount}건 보다 많을 수 있습니다.</span>',function(){
    				document.listForm.action = "";
    			});
    			*/
    			var form = document.getElementById('listForm');
		    	form.excel_label.value = excel_label;
		    	form.excel_type.value = excel_type;
		    	form.excel_msg.value = val;
		    	form.action = "/all/main/excel/down.do";
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
						//console.log(data);
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
            			console.log(errorThrown);
            			//console.log('error!');
            			//$('.btn-act-external-excel-down').removeClass('disabled');
            		}
            	});
    		} ,function() { 
    		//cancel    			
    	});
    	/*
    	alertify.confirm('엑셀을 다운로드 하시겠습니까?<br>엑셀 다운로드가 완료 될 때까지 데이터처리량에 따라 수분이 소요될수도 있습니다.',function(){
    		$.ajax({
        		type:"POST",
        		url :'/all/main/excel/down.do',
        		data:$('#listForm').serialize(),
        		//dataType: "html",
        		//contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
        		async: true,
        		success: function(data, status, xhr) {
        			console.log('success!');
        			//$("#admPublicPanelLayer").html(data);
        			//$("#admPublicPanelLayer").show();
        			
        			//var win = window.open("", "ExcelPopupWin", "width=500,height=600");
        			//win.setTimeout(function(){
        			//	win.document.write(data);
        			//},800);
        			
        		},
        		beforeSend : function() {
        			//console.log('before!');
        			$('.excel-down').addClass('disabled');
        		},
        		complete : function() {
        			//console.log('complete!');
        			$('.excel-down').removeClass('disabled');
        	    },
        		error: function(jqXHR, textStatus, errorThrown) {
        			console.log(errorThrown);
        			//console.log('error!');
        			$('.excel-down').removeClass('disabled');
        		}
        	});
    	});
    	*/
    	
    });
  	
	//낚시어선업자 일때만 신규/기존/재개자 선택가능
	/* $("#MBR_FSHRBT_TYPE").on("change", function(){
		var val = $("#MBR_TRGT_CD").val();
		if(val == 'CIDN010200') {//낚시터
			$("#MBR_FSHRBT_TYPE").attr('disabled', true);
		} else {
			$("#MBR_FSHRBT_TYPE").attr('disabled', false);
		}
	}); */
	
	$(".btn_change").on("click", function(){
		
		var select_box = $(this).prev().children('button');
		var data_id = select_box.attr('data-id');
		var val = $("#" + data_id).val();
		
		if(!val) {
			alertify.alert("선택된 값이 없습니다.");
			return;
		}
		
		var form = document.getElementById('updateForm');
    	form.MBR_ID.value = data_id.substr(12);
    	form.MBR_EDU_RSPNBER_TYPE.value = val;
			
		$.ajax({
    		type:"POST",
    		url :'/eduadm/member/rspnber_type_update.do',
    		data:$('#updateForm').serialize(),
    		//dataType: "html",
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
    		async: true,
    		success: function(data, status, xhr) {
    			console.log('success!');
				//console.log(data);
				if(data.error == 1) {
					alertify.alert(data.msg,function(){
						
					});
				} else {
					alertify.alert("변경되었습니다",function(){
						location.reload();
					});
				}
					
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
    			//console.log(errorThrown);
    			//console.log('error!');
    			//$('.btn-act-external-excel-down').removeClass('disabled');
    			clickRequestLockStop();
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
		var form = document.getElementById('ajaxMbrMemoForm');
    	form.MBR_DSCRP.value = $(obj).val();
    	var mbr_id = form.MBR_ID.value = $(obj).attr('data-mbr-id');
    	form.action = '';
    	form.target = "";
		$.ajax({
			type:"POST",
			url :"/eduadm/member/memo/update_act.do",
			data:$('#ajaxMbrMemoForm').serialize(),
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
	//End of 메모저장
</script>

<%@ include file="../tail.jsp" %>

	<!-- // 엑셀다운로드 는(tail 뒤에 위치해야함.) // -->
	<!-- ### 설명 ###
		@ table id 가 datalist 인지 확인
		@ th,td 등 class 에 [ hide-cell-exceldown ] 가 포함되어 있으면 엑셀다운로드시 내용을 제거한다.
		@ 표에는 노출하지 않고 다운로드시에 포함하고 싶으면 class 에 [ d-none ] 추가한다.
	// -->
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
    		name: "${depthName}_${pageName}",
    		filename: "${depthName}_${pageName}_"+timestr+".xls",
    		//fileext: ".xls",
    		exclude_img: true,
    		exclude_links: true,
    		exclude_inputs: true,
    		exclude: ".hide-cell-exceldown",
    	});
    });
    </script>
    <!-- End // 엑셀다운로드 // -->  
