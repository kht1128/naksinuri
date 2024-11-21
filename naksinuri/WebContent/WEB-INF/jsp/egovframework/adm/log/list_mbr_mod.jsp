<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:choose>
	<c:when test="${not empty addWebLink and addWebLink eq 'Seadm'}">
		<%@ include file="../../seadm/head.jsp" %> 
		<%@ include file="../../seadm/left_menu.jsp" %>
	</c:when>
	<c:when test="${not empty addWebLink and addWebLink eq 'Eduadm'}">
		<%@ include file="../../eduadm/head.jsp" %> 
		<%@ include file="../../eduadm/left_menu.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="../head.jsp" %> 
		<%@ include file="../left_menu.jsp" %>
	</c:otherwise>
</c:choose>

<script>
/*
var logTableCellMbrData = [
 	{label:'회원일련번호',id:'mbr_sn',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'이름',id:'mbr_nm',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'닉네임(호칭)',id:'mbr_ncnm',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'연락처(일반)',id:'mbr_tel',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'연락처(휴대폰)',id:'mbr_hp',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'이메일',id:'mbr_email',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'주소',id:'mbr_addr1',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'상세주소',id:'mbr_addr2',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'우편번호',id:'mbr_zipcd',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'마지막접속시간',id:'mbr_last_con_tm',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'유형코드',id:'mbr_type_cd',code:'y',adm:'n',inputtype:'',gubun:''},
 	{label:'생년월일',id:'mbr_birth',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'직급코드',id:'mbr_position_cd',code:'y',adm:'n',inputtype:'',gubun:''}, 	
 	{label:'사용여부',id:'mbr_st',code:'n',adm:'n',inputtype:'',gubun:'N:비활성,Y:사용중,R:승인대기'},
 	{label:'등록일자',id:'mbr_reg_dt',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'변경일자',id:'mbr_mod_dt',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'작성자아이디',id:'reg_mbr_id',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'수정자아이디',id:'upd_mbr_id',code:'n',adm:'n',inputtype:'',gubun:''},
 	{label:'관리주체',id:'mbr_reg_type_cd',code:'y',adm:'n',inputtype:'',gubun:''},
 	{label:'메모기록',id:'mbr_dscrp',code:'n',adm:'n',inputtype:'textarea',gubun:''},
 	//관리자용
 	{label:'소속명',id:'mbr_psitn_nm',code:'n',adm:'y',inputtype:'',gubun:''},
 	{label:'지역제한(시도)',id:'mbr_sido_cd',code:'y',adm:'y',inputtype:'',gubun:''},
 	{label:'지역제한(시군구)',id:'mbr_signgu_cd',code:'y',adm:'y',inputtype:'',gubun:''},
 	{label:'교육기관제한',id:'mbr_edu_ins_cd',code:'n',adm:'y',inputtype:'',gubun:''},
 	{label:'교육대상제한',id:'mbr_trgt_cd',code:'y',adm:'y',inputtype:'',gubun:''},
];
var logTableCellMbrDtlData = [
	{label:'상세정보일련번호',id:'dtl_sn',code:'n',inputtype:'',gubun:''},
	{label:'이름',id:'mbr_nm',code:'n',inputtype:'',gubun:''},
	{label:'대상구분',id:'dtl_cd',code:'y',inputtype:'',gubun:''},
	{label:'어선명<br>/낚시터명',id:'dtl_nm',code:'n',inputtype:'',gubun:''},
	{label:'관리주체',id:'reg_type_cd',code:'y',inputtype:'',gubun:''},
	{label:'대상구분',id:'dtl_license_cd',code:'y',inputtype:'',gubun:''},
	{label:'시도',id:'sido_cd',code:'y',inputtype:'',gubun:''},
	{label:'시군구',id:'signgu_cd',code:'y',inputtype:'',gubun:''},
	{label:'읍면동명',id:'ymd_nm',code:'n',inputtype:'',gubun:''},
	{label:'소재지',id:'dtl_addr',code:'n',inputtype:'',gubun:''},
	{label:'신고번호(어선)<br>/허가(등록)번호(낚시터)',id:'reg_num_cd',code:'n',inputtype:'',gubun:''},
	{label:'낚시터 업구분',id:'fshlc_work_cd',code:'y',inputtype:'',gubun:''},
	{label:'낚시터 적용수면',id:'fshlc_applc',code:'n',inputtype:'',gubun:''},
	{label:'낚시어선 어선번호',id:'ship_cd',code:'n',inputtype:'',gubun:''},
	{label:'낚시어선 총톤수',id:'ship_grtg',code:'n',inputtype:'',gubun:''},
	{label:'낚시어선 선적항',id:'ship_prload',code:'n',inputtype:'',gubun:''},
	{label:'낚시어선 최대승객',id:'ship_max_psnger',code:'n',inputtype:'',gubun:''},
	{label:'낚시어선 최대선원',id:'ship_max_crew',code:'n',inputtype:'',gubun:''},
	{label:'낚시어선 해기사면허',id:'ship_license',code:'n',inputtype:'',gubun:''},
	{label:'유효시작일자',id:'ship_license_str_dt',code:'n',inputtype:'',gubun:''},
	{label:'유효만료일자',id:'ship_license_end_dt',code:'n',inputtype:'',gubun:''},
	{label:'사용여부',id:'use_at',code:'n',inputtype:'',gubun:'0:사용안함,1:사용중'},
	{label:'삭제여부',id:'del_at',code:'n',inputtype:'',gubun:'0:정상,1:삭제됨'},
	{label:'등록일자',id:'reg_dt',code:'n',inputtype:'',gubun:''},
	{label:'변경일자',id:'upd_dt',code:'n',inputtype:'',gubun:''},
	{label:'작성자아이디',id:'reg_mbr_id',code:'n',inputtype:'',gubun:''},
	{label:'수정자아이디',id:'upd_mbr_id',code:'n',inputtype:'',gubun:''},
];
var logTableCellMbrHstryData = [
	{label:'이수내역번호',id:'hmbr_sn',code:'n',inputtype:'',gubun:''},
	{label:'회원아이디',id:'mbr_id',code:'n',inputtype:'',gubun:''},
	{label:'교육정보1단계번호',id:'crs_sn',code:'n',inputtype:'',gubun:''},
	{label:'신청상태',id:'lrnng_st',code:'n',inputtype:'',gubun:'0:대기,1:승인(이수증외부출력가능),2:취소,3:강제취소,4:보류'},
	{label:'학습진행률(%)',id:'lrnng_prgrs',code:'n',inputtype:'',gubun:''},
	{label:'이수상태',id:'lrnng_cmplt_st',code:'n',inputtype:'',gubun:'0:이수전,1:이수완료(이수증발급완료),2:이수취소'},
	{label:'이수완료-일자',id:'lrnng_cmplt_dt',code:'n',inputtype:'',gubun:''},
	{label:'이수완료-담당자아이디',id:'lrnng_cmplt_mbr_id',code:'n',inputtype:'',gubun:''},
	{label:'생성일자',id:'reg_dt',code:'n',inputtype:'',gubun:''},
	{label:'변경일자',id:'upd_dt',code:'n',inputtype:'',gubun:''},
	{label:'작성자아이디',id:'reg_mbr_id',code:'n',inputtype:'',gubun:''},
	{label:'수정자아이디',id:'upd_mbr_id',code:'n',inputtype:'',gubun:''},
	{label:'사용여부',id:'ues_st',code:'n',inputtype:'',gubun:'0:사용안함,1:사용중'},
	{label:'삭제여부',id:'del_st',code:'n',inputtype:'',gubun:'0:정상,1:삭제됨'},
	{label:'총교육시간',id:'hmbr_rcgnt_time',code:'n',inputtype:'',gubun:''},
];
var logDataOld = new Array();
var logDataNew = new Array();
var CodeCategory = new Array();
*/
</script>
<%--
<c:forEach var="item_category" items="${list_all_cd}">
<script> CodeCategory['${item_category.CD_ID}'] = '${item_category.CD_NM}'; </script>
</c:forEach>
--%>
<form:form commandName="eduMemberVO" id="ajaxMbrViewForm" name="ajaxMbrViewForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
</form:form>

<form:form commandName="logMemberModifyVO" id="ajaxLogMbrModViewForm" name="ajaxLogMbrModViewForm" method="post">
<input type="hidden" name="LOG_SN" value=""/>
</form:form>


<form:form commandName="logMemberModifyVO" id="listForm" name="listForm" method="post">
	<input type="hidden" name="excel_msg" value="" />
	<input type="hidden" name="excel_type" value="" />
	<input type="hidden" name="excel_label" value="" />
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
        	<a class="btn btn-outline btn-info excel-down btn-act-external-excel-down" href="javascript:void(0);" 
        		data-excel-type="/adm/log/listMbrModEduadm.do"
        		data-excel-label="${fn:trim(pageName)}_엑셀다운로드">
        		<i class="site-menu-icon wb-download" aria-hidden="true"></i>
        		<span class="hidden-sm-down">${pageName} (총 ${paginationInfo.totalRecordCount}건 모두) 엑셀다운로드</span>
        	</a>  
        </div>
      </div>
      
      <div class="page-content container-fluid">
         <div class="row">
          <div class="col-xl-12">
          
          	<!-- 검색폼 -->
          	<div class="panel mb-20">
	  			<div class="panel-body">  			
	  				
	  				<div class="input-group col-lg-12 p-0 ">
	  					<div class="col-lg-2 p-0">
							<div class="input-group">
								<input type="text" class="form-control datepickerStrSearch " id="datepickerStrSearch" name="searchStrDate" placeholder="조회기간시작일자" autocomplete="off" value="${searchStrDate}" data-toggle="tooltip" data-original-title="조회시작일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="datepickerStrSearch" aria-label="Close"></button></span>
								</div>
							</div>
						</div>
						<div class="col-lg-2 p-0 pl-10">
							<div class="input-group">
								<input type="text" class="form-control datepickerEndSearch " id="datepickerEndSearch" name="searchEndDate" placeholder="조회기간종료일자" autocomplete="off" value="${searchEndDate}" data-toggle="tooltip" data-original-title="조회종료일자를 선택하세요.">
								<div class="input-group-append">
							    	<span class="input-group-text bg-white"><button type="button" class="input-search-close icon wb-close clk-clear-input" for="datepickerEndSearch" aria-label="Close"></button></span>
								</div>
							</div>
						</div>	
						<div class="col-lg-2 p-0">

						</div>
		      			<div class="col-lg-2 p-0 pl-10">
							<div class="btn-group">
								<div class="input-group-prepend">
									<span class="input-group-text">출력수</span>
								</div>
								<input type="text" class="form-control input-auto-enter-key" name="pageUnit" placeholder="" autocomplete="off" value="${paginationInfo.recordCountPerPage}" data-toggle="tooltip" data-original-title="페이지당 화면에 출력할 게시물 수량을 입력하세요.">
							</div>
						</div>
						<div class="col-lg-4 p-0 pl-10 text-right">
							<div class="btn-group w-p100">
								<input type="text" class="form-control input-auto-enter-key" name="searchKeyword" placeholder="검색어를 입력하세요." autocomplete="off" value="${searchKeyword}" data-toggle="tooltip" data-original-title="로그이력,사용자변경이력,회원아이디,이름,연락처 등을 입력해주세요.">
								<button type="button" class="btn btn-primary clk_search_btn"><i class="icon wb-search" aria-hidden="true"></i></button>
							</div>
						</div>
					</div>	
							
	  			</div>
			</div>
			<!-- End 검색폼 --> 
          
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
	          			<col width="75px"/>
	          			<col width="130px"/>
	          			<col width="150px"/>
	          			<col width="130px"/>
	          			<col width="130px"/>
	          			<col />
	          			<col width="100px"/>
	          			<col width="100px"/>
	          		</colgroup>
	          		<thead>
	          			<tr>
	          				<th class="text-middle text-center ">No</th>
							<th class="text-middle text-center ">로그일자</th>
							<th class="text-middle text-center ">처리담당자<br>/접속IP</th>
							<th class="text-middle text-center ">로그구분</th>
							<th class="text-middle text-center ">로그이력</th>
							<th class="text-middle text-center ">사용자변경이력</th>
							<th class="text-middle text-center ">회원정보</th>
							<th class="text-middle text-center ">변경데이터 확인</th>
	                	</tr>       				
	             	</thead>
					<tbody>
						<c:set var="table_cell_cnt" value="8"/>
						<c:if test="${empty list}">
							<tr><td colspan="${table_cell_cnt}" class="text-center table-active">조회 된 내용이 없습니다.</td></tr>	  
		            	</c:if>
		            	<c:forEach var="item" varStatus="status" items="${list}">
		            				            	
		            		<tr class="<c:if test="${status.index%2 eq 0}">table-active</c:if>">
		            			<td class="text-middle text-center"><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index)}"/></td>
		            			<td class="text-middle text-center">
									<fmt:parseDate value="${fn:replace(item.REG_DT, '.0', '')}" var="parse_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
									<fmt:formatDate value="${parse_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss" var="REG_DT"/>
									<c:out value="${REG_DT}"/>
								</td>
		            			<td class="text-middle text-center">${item.MASTER_MBR_NCNM }<br>(${item.MASTER_MBR_NM })<br><span class="grey-400">${item.LOG_INFO_IP }</span></td>
		            			<td class="text-middle text-center">
		            				<c:choose>
		            					<c:when test="${item.LOG_TYPE eq 'EDU_MBR_HSTRY_TB'}">교육수강생</c:when>
		            					<c:when test="${item.LOG_TYPE eq 'MBR_DTL_TB'}">회원추가상세정보</c:when>
		            					<c:otherwise>회원기본정보</c:otherwise>
		            				</c:choose>
		            			</td>
		            			<td class="text-middle text-center">${item.LOG_DSCRP }</td>
		            			<td class="text-middle text-center">${item.LOG_UPD_MSG }</td>
		            			<td class="text-middle text-center">
		            				<c:choose>
		            					<c:when test="${not empty item.MBR_NM }">${item.MBR_NM }</c:when>
		            					<c:otherwise>${item.TMP_MBR_NM }</c:otherwise>
		            				</c:choose>
		            				<br>
		            				<a href="#;" class="btn btn-outline btn-default btn-sm "onclick="clk_mbr_view('${item.MBR_ID}')">자세히보기</a>
		            			</td>
		            			<td class="text-middle text-center">		            	
		            				<a href="#;" class="btn btn-default btn-sm clk_data_view clk_data_view2" data-log-sn2="${item.LOG_SN}" data-log-sn="LOG_SN_${item.LOG_SN}" data-log-type="${item.LOG_TYPE}">조회</a>
		            				<%--
		            				<c:choose>
		            					<c:when test="${not empty item.OLD_DATA}">
		            						<script> logDataOld['LOG_SN_${item.LOG_SN}'] = JSON.parse('${item.OLD_DATA}'); </script>	
		            					</c:when>
		            					<c:otherwise>
		            						<script> logDataOld['LOG_SN_${item.LOG_SN}'] = null; </script>
		            					</c:otherwise>
		            				</c:choose>
		            				<c:choose>
		            					<c:when test="${not empty item.NEW_DATA}">
		            						<script> logDataNew['LOG_SN_${item.LOG_SN}'] = JSON.parse('${item.NEW_DATA}'); </script>	
		            					</c:when>
		            					<c:otherwise>
		            						<script> logDataNew['LOG_SN_${item.LOG_SN}'] = null; </script>
		            					</c:otherwise>
		            				</c:choose>
		            				--%>
		            			</td>
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
.alertify .alert>*, .alertify .dialog>* {width:800px!important;}
.datepicker-dropdown{z-index:99999!important;}
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
function pageMove(obj) {
	var form = document.getElementById('listForm');
	form.action = "/adm/log/listMbr{addWebLink}.do";
	form.submit();
}
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
$(".clk_data_view").click(function() {
	var log_sn2 = $(this).attr('data-log-sn2');
	var form = document.getElementById('ajaxLogMbrModViewForm');
	form.LOG_SN.value = log_sn2;
	$.ajax({
		type:"POST",
		url :'/adm/log/viewMbrMod.do',
		data:$('#ajaxLogMbrModViewForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			alertify.alert(data);
			var h = 0;
			if(document.body.clientHeight == 0) {
				h = window.innerHeight-158;
			} else {
				h = document.body.clientHeight-158;
			}
			$('.alertify-ajax-scroll-body').css('height',h);
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
$(".clk_data_view").click(function() {
	var log_sn = $(this).attr('data-log-sn');
	var log_type = $(this).attr('data-log-type'); 
	var old_data = logDataOld[log_sn];
	var new_data = logDataNew[log_sn];
	var html_data = '';
	html_data += '<table class="w-p100">';
	html_data += '	<colgroup>';
	html_data += '		<col width="24%"/>';
	html_data += '		<col width="38%"/>';
	html_data += '		<col width="38%"/>';
	html_data += '	</colgroup>';
	html_data += '	<thead>';
	html_data += '		<tr>';
	html_data += '			<td class="text-center border border-default bg-grey-200">항목</td>';
	html_data += '			<td class="text-center border border-default bg-grey-200">변경 전</td>';
	html_data += '			<td class="text-center border border-default bg-grey-200">변경 후</td>';
	html_data += '		</tr>';
	html_data += '</thead>';
	html_data += '<tbody>';	
	if(log_type == 'MBR_DTL_TB') {
		for(var i=0; i<logTableCellMbrDtlData.length; i++) {
			var id = logTableCellMbrDtlData[i].id;
			var label = logTableCellMbrDtlData[i].label;
			var code = logTableCellMbrDtlData[i].code;
			var inputtype = logTableCellMbrDtlData[i].inputtype;
			var gubun = logTableCellMbrDtlData[i].gubun;
			html_data += mergeHtmlData(id,label,code,inputtype,gubun,old_data,new_data);
		}
	} else if(log_type == 'MBR_TB') {
		for(var i=0; i<logTableCellMbrData.length; i++) {
			var id = logTableCellMbrData[i].id;
			var label = logTableCellMbrData[i].label;
			var code = logTableCellMbrData[i].code;
			var inputtype = logTableCellMbrData[i].inputtype;
			var gubun = logTableCellMbrData[i].gubun;
			var adm = logTableCellMbrData[i].adm;						
			if(adm=='y' && old_data['mbr_lv_id']=='10') continue;//일반회원은 표시하지 않음
			html_data += mergeHtmlData(id,label,code,inputtype,gubun,old_data,new_data);
		}
	} else if(log_type == 'EDU_MBR_HSTRY_TB') {
		for(var i=0; i<logTableCellMbrHstryData.length; i++) {
			var id = logTableCellMbrHstryData[i].id;
			var label = logTableCellMbrHstryData[i].label;
			var code = logTableCellMbrHstryData[i].code;
			var inputtype = logTableCellMbrHstryData[i].inputtype;
			var gubun = logTableCellMbrHstryData[i].gubun;
			html_data += mergeHtmlData(id,label,code,inputtype,gubun,old_data,new_data);
		}
	} else {
		html_data += '		<tr class="">';
		html_data += '			<td class="text-center border border-default" colspan="3">해당되는 정보가 없습니다.</td>';
		html_data += '		</tr>';
	}
	html_data += '</body>';
	html_data += '</table>';
	alertify.alert(html_data);
});
function mergeHtmlData(id,label,code,inputtype,gubun,old_data,new_data) {
	var html_data = '';
	var tmpStrOld = "";
	var tmpStrNew = "";
	html_data += '		<tr class="">';
	html_data += '			<td class="text-center border border-default">'+label+'</td>';
							if(old_data==null) {
	html_data += '			<td class="text-center border border-default"></td>';
							} else if(code == 'y'){
								tmpStrOld = CodeCategory[old_data[id]];
								if(typeof tmpStrOld == "undefined" || tmpStrOld == null) {
									tmpStrOld = "";
								}
								if(gubun!='' && tmpStrOld!='') {
									var rows = gubun.split(',');
									for(var r=0; r<rows.length; r++) {
										var items = rows[r].split(':');
										if(items[0] == tmpStrOld) {
											tmpStrOld = items[1];
										}
									}
								}
								var text = "";
								if(inputtype=='textarea') {
									text = '<textarea class="form-control bg-white border-0 " disabled>'+tmpStrOld+'</textarea>';
								} else {
									text = tmpStrOld;
								}
	html_data += '			<td class="text-center border border-default">'+text+'</td>';
							} else {
								tmpStrOld = old_data[id];
								if(typeof tmpStrOld == "undefined" || tmpStrOld == null) {
									tmpStrOld = "";
								}
								if(gubun!='' && tmpStrOld!='') {
									var rows = gubun.split(',');
									for(var r=0; r<rows.length; r++) {
										var items = rows[r].split(':');
										if(items[0] == tmpStrOld) {
											tmpStrOld = items[1];
										}
									}
								}
								var text = "";
								if(inputtype=='textarea') {
									text = '<textarea class="form-control bg-white border-0 " disabled>'+tmpStrOld+'</textarea>';
								} else {
									text = tmpStrOld;
								}
	html_data += '			<td class="text-center border border-default">'+text+'</td>';							
							}
							if(new_data==null) {
	html_data += '			<td class="text-center border border-default"></td>';
							} else if(code == 'y'){
								tmpStrNew = CodeCategory[new_data[id]];
								if(typeof tmpStrNew == "undefined" || tmpStrNew == null) {
									tmpStrNew = "";
								}
								if(gubun!='' && tmpStrOld!='') {
									var rows = gubun.split(',');
									for(var r=0; r<rows.length; r++) {
										var items = rows[r].split(':');
										if(items[0] == tmpStrNew) {
											tmpStrNew = items[1];
										}
									}
								}
								var text = "";
								if(inputtype=='textarea') {
									text = '<textarea class="form-control bg-white '+(tmpStrOld!=tmpStrNew?"border-danger":"border-0")+' " disabled>'+tmpStrNew+'</textarea>';
								} else {
									text = tmpStrNew;
								}
	html_data += '			<td class="text-center border border-default '+(tmpStrOld!=tmpStrNew?"red-600":"")+' ">'+text+'</td>';							
							} else {
								tmpStrNew = new_data[id];
								if(typeof tmpStrNew == "undefined" || tmpStrNew == null) {
									tmpStrNew = "";
								}
								if(gubun!='' && tmpStrOld!='') {
									var rows = gubun.split(',');
									for(var r=0; r<rows.length; r++) {
										var items = rows[r].split(':');
										if(items[0] == tmpStrNew) {
											tmpStrNew = items[1];
										}
									}
								}
								var text = "";
								if(inputtype=='textarea') {
									text = '<textarea class="form-control bg-white '+(tmpStrOld!=tmpStrNew?"border-danger":"border-0")+' " disabled>'+tmpStrNew+'</textarea>';
								} else {
									text = tmpStrNew;
								}
	html_data += '			<td class="text-center border border-default '+(tmpStrOld!=tmpStrNew?"red-600":"")+' ">'+text+'</td>';
							}
	html_data += '		</tr>';	
	return html_data;
}
*/
//회원상세정보보기
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

	<script>
	//엑셀다운로드 
    $(".btn-act-external-excel-down").click( function() {
    	var excel_type = $(this).attr('data-excel-type');
    	var excel_label = $(this).attr('data-excel-label');
    	alertify.prompt('엑셀 다운로드 사유를 입력해주세요.',function(val, e) {
    		//ok
    			if(val.trim()=='') {
    				alertify.alert('사유를 정확히 입력해주셔야 합니다.');
    				return;
    			} 
    			alertify.alert('엑셀 다운로드가 완료 될 때까지 잠시 기다려 주세요.<br>데이터처리량에 따라 수분이 소요 될 수도 있습니다.</span>',function(){
    				document.listForm.action = "";
    			});
		    	var form = document.getElementById('listForm');
		    	form.excel_label.value = excel_label;
		    	form.excel_type.value = excel_type;
		    	form.excel_msg.value = val;
		    	form.action = "/all/main/excel/down.do";
		    	form.submit();
    		} ,function() { 
    		//cancel    			
    	});
    });
    </script>


<c:choose>
	<c:when test="${not empty addWebLink and addWebLink eq 'Seadm'}">
		<%@ include file="../../seadm/tail.jsp" %>
	</c:when>
	<c:when test="${not empty addWebLink and addWebLink eq 'Eduadm'}">
		<%@ include file="../../eduadm/tail.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="../tail.jsp" %>
	</c:otherwise>
</c:choose>

